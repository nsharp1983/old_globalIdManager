package com.ats.aempi.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import com.ats.aempi.EMPIConstants;
import com.ats.aempi.InitializationException;
import com.ats.aempi.blocking.BlockingService;
import com.ats.aempi.configuration.Component.ComponentType;
import com.ats.aempi.configuration.Component.ExtensionInterface;
import com.ats.aempi.configuration.xml.BlockingConfigurationType;
import com.ats.aempi.configuration.xml.CustomFields;
import com.ats.aempi.configuration.xml.FileLoaderConfigurationType;
import com.ats.aempi.configuration.xml.MatchingConfigurationType;
import com.ats.aempi.configuration.xml.MpiConfigDocument;
import com.ats.aempi.configuration.xml.mpicomponent.ExtensionType;
import com.ats.aempi.configuration.xml.mpicomponent.ExtensionType.Interface.Enum;
import com.ats.aempi.configuration.xml.mpicomponent.MpiComponentDefinitionDocument;
import com.ats.aempi.configuration.xml.mpicomponent.MpiComponentType;
import com.ats.aempi.context.Context;
import com.ats.aempi.loader.configuration.FileLoaderConfigurationService;
import com.ats.aempi.matching.MatchingService;
import com.ats.aempi.service.impl.BaseServiceImpl;
import com.ats.aempi.util.ConvertUtil;
import com.ats.aexchange.config.PropertyFacade;

public class EMPIConfiguration extends BaseServiceImpl implements ConfigurationRegistry
{
	protected static final Log log = LogFactory.getLog(EMPIConfiguration.class);
	
	private String configFile;
	private MpiConfigDocument configuration;
	private GlobalIdentifier globalIdentifier;
	private List<CustomField> customFields;
	private Map<String,CustomField> customFieldsByName;
	private AdminConfiguration adminConfiguration;
	
	private Map<String, Object> configurationRegistry;
	private Map<String, Component> extensionRegistry;
	
	public void init() {
		configureLoggingEnvironment();
		configurationRegistry = new HashMap<String, Object>();
		extensionRegistry = new HashMap<String, Component>();
		try {
			configuration = loadConfigurationFromSource();
			validateConfiguration(configuration);
			processConfiguration(configuration);
			log.info("System configuration: " + this.toString());
		} catch (Exception e) {
			log.error("Failed while locating and parsing the configuration file. System is shutting down due to: " + e, e);
			throw new RuntimeException("Failed while locating and parsing the configuration file. System is shutting down.");
		}
	}
	
	private void configureLoggingEnvironment() {
		String aEmpiHome = Context.getAEmpiHome();
		if (aEmpiHome != null && aEmpiHome.length() > 0) {
			String loggingConfigurationFile = aEmpiHome + "/log4j.properties";
			PropertyConfigurator.configure(loggingConfigurationFile);
			log.info("Set the logging configuration file to " + loggingConfigurationFile);
		}
	}
	
	public FileLoaderConfigurationType getFileLoaderConfiguration() {
		return configuration.getMpiConfig().getFileLoaderConfiguration();
	}
	
	public FileLoaderConfigurationType saveFileLoaderConfiguration(FileLoaderConfigurationType fileLoaderConfig) {
		configuration.getMpiConfig().setFileLoaderConfiguration(fileLoaderConfig);
		return configuration.getMpiConfig().getFileLoaderConfiguration();
	}
	
	public BlockingConfigurationType getBlockingConfiguration() {
		return configuration.getMpiConfig().getBlockingConfiguration();
	}
	
	public BlockingConfigurationType saveBlockingConfiguration(BlockingConfigurationType blocking) {
		configuration.getMpiConfig().setBlockingConfiguration(blocking);
		return configuration.getMpiConfig().getBlockingConfiguration();
	}
	
	private void processConfiguration(MpiConfigDocument configuration) {
		globalIdentifier = processGlobalIdentifier(configuration);
		processFileLoaderConfiguration(configuration);
		customFields = processCustomFields(configuration);
		processBlockingConfiguration(configuration);
		processMatchConfiguration(configuration);
		adminConfiguration = processAdminConfiguration(configuration);
	}

	private AdminConfiguration processAdminConfiguration(MpiConfigDocument configuration) {
		adminConfiguration = new AdminConfiguration();
		if (configuration.getMpiConfig().getAdminConfiguration() != null) {
			adminConfiguration.setConfigFileDirectory(configuration.getMpiConfig().getAdminConfiguration().getFileRepositoryDirectory());
		}
		if (adminConfiguration.getConfigFileDirectory() == null) {
			adminConfiguration.setConfigFileDirectory(EMPIConstants.DEFAULT_FILE_REPOSITORY_DIRECTORY);
		}
		return adminConfiguration;
	}

	private GlobalIdentifier processGlobalIdentifier(MpiConfigDocument configuration) {
		globalIdentifier = new GlobalIdentifier();
		if (!configuration.getMpiConfig().isSetGlobalIdentifier()) {
			globalIdentifier.setAssignGlobalIdentifier(false);
			return globalIdentifier;
		}
		globalIdentifier.setAssignGlobalIdentifier(configuration.getMpiConfig().getGlobalIdentifier().getAssignGlobalIdentifier());
		globalIdentifier.setNamespaceIdentifier(configuration.getMpiConfig().getGlobalIdentifier().getNamespaceIdentifier());
		globalIdentifier.setUniversalIdentifier(configuration.getMpiConfig().getGlobalIdentifier().getUniversalIdentifier());
		globalIdentifier.setUniversalIdentifierType(configuration.getMpiConfig().getGlobalIdentifier().getUniversalIdentifierType());
		return globalIdentifier;
	}
	
	private void processBlockingConfiguration(MpiConfigDocument configuration) {
		checkConfiguration(configuration);
		
		BlockingConfigurationType obj = configuration.getMpiConfig().getBlockingConfiguration();
		
		log.debug("Object is of type: " + obj.getDomNode().getNamespaceURI());
		String namespaceUriStr = obj.getDomNode().getNamespaceURI();
		URI namespaceURI = getNamespaceURI(namespaceUriStr);

		String resourcePath = generateComponentResourcePath(namespaceURI);
		Component component = loadAndRegisterComponentFromNamespaceUri(resourcePath);
		
		String configurationLoaderBean = getExtensionBeanNameFromComponent(component);
		
		ConfigurationLoader loader = (ConfigurationLoader) Context.getApplicationContext().getBean(configurationLoaderBean);
		loader.loadAndRegisterComponentConfiguration(this, obj);
		
		Component.Extension extension = component.getExtensionByExtensionInterface(ExtensionInterface.IMPLEMENTATION);
		if (extension == null) {
			log.error("Encountered a custom blocking component with no implementation extension: " + component);
			throw new InitializationException("Unable to locate an implementation component for custom blocking component " + component.getName());
		}
		log.debug("Registering implementation of blocking component named " + extension.getName() + " and implementation key " +
				extension.getImplementationKey());
		BlockingService blockingService = (BlockingService) 
			Context.getApplicationContext().getBean(extension.getImplementationKey());
		Context.registerCustomBlockingService(blockingService);
	}
	
	public MatchingConfigurationType saveMatchingConfiguration(MatchingConfigurationType matchingConfiguration) {
		configuration.getMpiConfig().setMatchingConfiguration(matchingConfiguration);
		return configuration.getMpiConfig().getMatchingConfiguration();
	}
	
	private void processMatchConfiguration(MpiConfigDocument configuration) {
		
		MatchingConfigurationType obj = configuration.getMpiConfig().getMatchingConfiguration();
		
		log.debug("Object is of type: " + obj.getDomNode().getNamespaceURI());
		String namespaceUriStr = obj.getDomNode().getNamespaceURI();
		URI namespaceURI = getNamespaceURI(namespaceUriStr);

		String resourcePath = generateComponentResourcePath(namespaceURI);
		Component component = loadAndRegisterComponentFromNamespaceUri(resourcePath);
		
		String configurationLoaderBean = getExtensionBeanNameFromComponent(component);
		
		ConfigurationLoader loader = (ConfigurationLoader) Context.getApplicationContext().getBean(configurationLoaderBean);
		loader.loadAndRegisterComponentConfiguration(this, obj);
		
		Component.Extension extension = component.getExtensionByExtensionInterface(ExtensionInterface.IMPLEMENTATION);
		if (extension == null) {
			log.error("Encountered a custom matching component with no implementation extension: " + component);
			throw new InitializationException("Unable to locate an implementation component for custom matching component " + component.getName());
		}
		log.debug("Registering implementation of matching component named " + extension.getName() + " and implementation key " +
				extension.getImplementationKey());
		MatchingService matchingService = (MatchingService) 
			Context.getApplicationContext().getBean(extension.getImplementationKey());
		Context.registerCustomMatchingService(matchingService);		
	}
	
	private void processFileLoaderConfiguration(MpiConfigDocument configuration2) {
		checkConfiguration(configuration);
		
		FileLoaderConfigurationType obj = configuration.getMpiConfig().getFileLoaderConfiguration();
		
		log.debug("Object is of type: " + obj.getDomNode().getNamespaceURI());
		String namespaceUriStr = obj.getDomNode().getNamespaceURI();
		URI namespaceURI = getNamespaceURI(namespaceUriStr);

		String resourcePath = generateComponentResourcePath(namespaceURI);
		Component component = loadAndRegisterComponentFromNamespaceUri(resourcePath);
		
		String configurationLoaderBean = getExtensionBeanNameFromComponent(component);
		
		ConfigurationLoader loader = (ConfigurationLoader) Context.getApplicationContext().getBean(configurationLoaderBean);
		loader.loadAndRegisterComponentConfiguration(this, obj);
		
		Component.Extension extension = component.getExtensionByExtensionInterface(ExtensionInterface.IMPLEMENTATION);
		if (extension == null) {
			log.error("Encountered a custom file loader component with no implementation extension: " + component);
			throw new InitializationException("Unable to locate an implementation component for custom file loader component " + component.getName());
		}
		log.debug("Registering implementation of file loader component named " + extension.getName() + " and implementation key " +
				extension.getImplementationKey());
		FileLoaderConfigurationService fileLoaderConfigurationService = (FileLoaderConfigurationService) 
			Context.getApplicationContext().getBean(extension.getImplementationKey());
		Context.registerCustomFileLoaderConfigurationService(fileLoaderConfigurationService);
	}

	public String getExtensionBeanNameFromComponent(Component component) {
		Component.Extension extension = component.getExtensionByExtensionInterface(ExtensionInterface.CONFIGURATION_LOADER);
		if (extension == null) {
			log.error("Encountered a custom component with no configuration loader extension: " + component);
			throw new InitializationException("Unable to load configuration for custom component " + component.getName());
		}
		return extension.getImplementationKey();
	}

	public Component lookupExtensionComponentByComponentType(Component.ComponentType type) {
		if (type == null || type.componentTypeName() == null || type.componentTypeName().length() == 0) {
			log.warn("Looked up extension component with blank type name: " + type);
			return null;
		}
		
		log.debug("Looking up extension component of type: " + type.componentTypeName());
		return extensionRegistry.get(type.componentTypeName());
	}
	
	private Component loadAndRegisterComponentFromNamespaceUri(String resourcePath) {
		Component component;
		try {
			InputStream stream = EMPIConfiguration.class.getResourceAsStream(resourcePath);
			MpiComponentDefinitionDocument componentDoc = MpiComponentDefinitionDocument.Factory.parse(stream);
			MpiComponentType componentXml = componentDoc.getMpiComponentDefinition().getMpiComponent();
			component = buildComponentFromXml(componentXml);
			log.debug("Loaded component: " + component);
			extensionRegistry.put(component.getComponentType().componentTypeName(), component);
			return component;
		} catch (IOException e) {
			log.error("Failed while loading component configuration file: " + resourcePath, e);
			throw new InitializationException("Failed while loading component configuration file " + resourcePath);
		} catch (XmlException e) {
			log.error("Failed while parsing component configuration file: " + resourcePath, e);
			throw new InitializationException("Failed while parsing component configuration file " + resourcePath);
		}
	}

	private Component buildComponentFromXml(MpiComponentType componentXml) {
		Component component = new Component(componentXml.getName());
		if (componentXml.getDescription() != null) {
			component.setDescription(componentXml.getDescription());
		}
		
		if (componentXml.getComponentType().intValue() == MpiComponentType.ComponentType.INT_BLOCKING) {
			component.setComponentType(ComponentType.BLOCKING);
		} else if (componentXml.getComponentType().intValue() == MpiComponentType.ComponentType.INT_MATCHING) {
			component.setComponentType(ComponentType.MATCHING);
		} else if (componentXml.getComponentType().intValue() == MpiComponentType.ComponentType.INT_FILELOADER) {
			component.setComponentType(ComponentType.FILELOADER);
		}

		log.debug("Component configuration: " + component.getName() + " of type " + component.getComponentType());
		for (int i=0; i < componentXml.getExtensions().sizeOfExtensionArray(); i++) {
			ExtensionType extension = componentXml.getExtensions().getExtensionArray(i);
			log.debug("Extension definition is " + extension);
			component.addExtension(extension.getName(), extension.getImplementation(), getExtensionInterfaceTypeById(extension.getInterface()));
		}
		return component;
	}

	private ExtensionInterface getExtensionInterfaceTypeById(Enum extensionInterface) {
		if (extensionInterface.intValue() == ExtensionType.Interface.INT_CONFIGURATION_LOADER) {
			return ExtensionInterface.CONFIGURATION_LOADER;
		} else if (extensionInterface.intValue() == ExtensionType.Interface.INT_CONFIGURATION_GUI) {
			return ExtensionInterface.CONFIGURATION_GUI;
		} else if (extensionInterface.intValue() == ExtensionType.Interface.INT_IMPLEMENTATION) {
			return ExtensionInterface.IMPLEMENTATION;
		}
		log.error("Unknown extension interface type encountered: " + extensionInterface);
		throw new RuntimeException("Unknown extension interface type encountered: " + extensionInterface);
	}

	private String generateComponentResourcePath(URI namespaceURI) {
        String resourcePath = "/META-INF" + namespaceURI.getPath() + "-aempi.xml";
//        File resourceFile = new File(resourcePath);
//        if (!resourceFile.exists() || !resourceFile.canRead()) {
//        	log.error("Unable to load component configuration file: " + resourcePath);
//        	throw new RuntimeException("Component configuration file " + resourcePath + " must be readable and present in the classpath");
//        }
//        String baseURI = resourceFile.getParent().replace('\\', '/');
        log.debug("Will locate configuration information for namespace from: " + resourcePath);
        return resourcePath;
	}

	private URI getNamespaceURI(String namespaceUriStr) {
		log.debug("Generating namespace URI for namespace " + namespaceUriStr);
		try {
			URI namespaceURI = new URI(namespaceUriStr);
			return namespaceURI;
		} catch (URISyntaxException e) {
			log.error("Failed to construct a namespace URI for namespace " + namespaceUriStr, e);
			throw new InitializationException("Unable to parse extended config namespace URI '" + namespaceUriStr + "'.", e);
		}
	}

	/**
	 * TODO Need to implement validation of the custom field names. The convention is that custom fields can range
	 * from custom1 to custom5 but currently there is no code here to check and make sure that the fields specified follow
	 * the convention and there is no duplication (custom1 specified twice for example).
	 * 
	 * @param customFieldXml
	 * @return
	 */
	private CustomField buildCustomFieldFromXml(com.ats.aempi.configuration.xml.CustomField customFieldXml) {
		CustomField customField = new CustomField();
		String fieldName = customFieldXml.getFieldName();
		customField.setFieldName(fieldName);
		if (!ConvertUtil.isValidCustomFieldName(fieldName)) {
			log.error("Not valid custom field name: " + fieldName);
			throw new InitializationException("Not valid custom field name: " + fieldName);
		}
		if (customFieldsByName.containsKey(fieldName)) {
			log.error("Duplicate custom field name in configuration: " + fieldName);
			throw new InitializationException("Duplicate custom field name in configuration: " + fieldName);
		}
		customField.setSourceFieldName(customFieldXml.getSourceFieldName());
		String transformationFunctionName = customFieldXml.getTransformationFunctionName();
		customField.setTransformationFunctionName(transformationFunctionName);
		return customField;
	}

	private List<CustomField> processCustomFields(MpiConfigDocument configuration) {
		checkConfiguration(configuration);
		
		customFieldsByName = new HashMap<String,CustomField>();
		registerConfigurationEntry(ConfigurationRegistry.CUSTOM_FIELDS_MAP, customFieldsByName);

		ArrayList<CustomField> list = new ArrayList<CustomField>();
		registerConfigurationEntry(ConfigurationRegistry.CUSTOM_FIELDS_LIST, list);
		
		CustomFields customFields = configuration.getMpiConfig().getCustomFields();
		if (customFields == null) {
			log.warn("No custom fields have been specified in the configuration.");
			return list;
		}
		
		for (int i=0; i < customFields.sizeOfCustomFieldArray(); i++) {
			com.ats.aempi.configuration.xml.CustomField customFieldXml = customFields.getCustomFieldArray(i);
			CustomField customField = buildCustomFieldFromXml(customFieldXml);
			if (customField != null) {
				customFieldsByName.put(customField.getFieldName(), customField);
				list.add(customField);
			}
		}
		return list;
	}

	public void saveAndRegisterCustomFieldsConfiguration(List<CustomField> customFieldsConfiguration) {
		customFields = customFieldsConfiguration;
		customFieldsByName.clear();
		for (CustomField customField : customFields)
			customFieldsByName.put(customField.getFieldName(), customField);
		com.ats.aempi.configuration.xml.CustomFields customFieldsType =
			buildCustomFieldsFragment(customFields);
		configuration.getMpiConfig().setCustomFields(customFieldsType);
		saveConfiguration();
		registerConfigurationEntry(ConfigurationRegistry.CUSTOM_FIELDS_MAP, customFieldsByName);
		registerConfigurationEntry(ConfigurationRegistry.CUSTOM_FIELDS_LIST, customFields);
	}

	private com.ats.aempi.configuration.xml.CustomFields buildCustomFieldsFragment(List<CustomField> customFieldsConfiguration) {
		com.ats.aempi.configuration.xml.CustomFields customFieldsType =
			com.ats.aempi.configuration.xml.CustomFields.Factory.newInstance();
		for (CustomField customField : customFieldsConfiguration) {
			com.ats.aempi.configuration.xml.CustomField customFieldXml =
				customFieldsType.addNewCustomField();
			customFieldXml.setFieldName(customField.getFieldName());
			customFieldXml.setSourceFieldName(customField.getSourceFieldName());
			customFieldXml.setTransformationFunctionName(customField.getTransformationFunctionName());
		}
		return customFieldsType;
	}

	private void checkConfiguration(MpiConfigDocument configuration) {
		if (configuration == null) {
			log.error("The configuration of the system has not been initialized.");
			throw new RuntimeException("The configuration of the system has not been properly initialized.");
		}
	}
	
	private void validateConfiguration(MpiConfigDocument configuration) {

		// Set up the validation error listener.
		ArrayList<XmlError> validationErrors = new ArrayList<XmlError>();
		XmlOptions validationOptions = new XmlOptions();
		validationOptions.setErrorListener(validationErrors);

		// During validation, errors are added to the ArrayList for
		// retrieval and printing by the printErrors method.
		boolean isValid = configuration.validate(validationOptions);

		// Print the errors if the XML is invalid.
		if (!isValid)
		{
		    java.util.Iterator<XmlError> iter = validationErrors.iterator();
		    StringBuffer sb = new StringBuffer("MPI Configuration validation errors:\n");
		    while (iter.hasNext())
		    {
		    	sb.append(">> ").append(iter.next()).append("\n");
		    }
		}			
	}

	private MpiConfigDocument loadConfigurationFromSource() throws XmlException, IOException {
		File file = getDefaultConfigurationFile();
		log.debug("Checking for presence of the configuration in file: " + file.getAbsolutePath());
		if (file.exists() && file.isFile()) {
			log.info("Loading configuration from file: " + file.getAbsolutePath());
			return MpiConfigDocument.Factory.parse(file);
		}

		URL fileUrl = EMPIConfiguration.class.getResource(configFile);
		if (fileUrl != null) {
			log.info("Loading configuration from URL: " + fileUrl);
			return MpiConfigDocument.Factory.parse(fileUrl);
		}
		
		log.error("Unable to load configuration information." + configFile);
		throw new RuntimeException("Unable to load configuration information." + configFile);
	}

	public void saveConfiguration() {
		File file = getDefaultConfigurationFile();
		log.debug("Storing current configuration in file: " + file.getAbsolutePath());
		try {
			XmlOptions opts = new XmlOptions();
			opts.setSavePrettyPrint();
			opts.setSavePrettyPrintIndent(4);
			configuration.save(file, opts);
		} catch (IOException e) {
			log.error("Unable to save the updated configuration in file: " + file.getAbsolutePath());
			throw new RuntimeException("Unable to save the updated configuration: " + e.getMessage());
		}
	}

	private File getDefaultConfigurationFile() {
		File dir = new File(Context.getAEmpiHome() + "/conf");
		
		//Hansen
		log.info("AEMPI_HOME is " + Context.getAEmpiHome());
		//String a = PropertyFacade.getString("new.auto.merge.empi.sql");
		//log.info("new.auto.merge.empi.sql is" + a);
		
		File file = new File(dir, configFile);
		return file;
	}
	
	public Object lookupConfigurationEntry(String key) {
		log.debug("Looking up configuration entry with key " + key);
		return configurationRegistry.get(key);
	}

	public void registerConfigurationEntry(String key, Object entry) {
		log.debug("Registering configuration entry " + entry + " with key " + key);
		configurationRegistry.put(key, entry);
	}
	
	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public List<CustomField> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}

	public AdminConfiguration getAdminConfiguration() {
		return adminConfiguration;
	}

	public void setAdminConfiguration(AdminConfiguration adminConfiguration) {
		this.adminConfiguration = adminConfiguration;
	}

	public GlobalIdentifier getGlobalIdentifier() {
		return globalIdentifier;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("customFields", customFields)
			.toString();
	}
}
