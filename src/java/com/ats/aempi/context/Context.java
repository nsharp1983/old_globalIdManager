package com.ats.aempi.context;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.EMPIConstants;
import com.ats.aempi.blocking.BlockingService;
import com.ats.aempi.configuration.EMPIConfiguration;
import com.ats.aempi.loader.configuration.FileLoaderConfigurationService;
import com.ats.aempi.matching.MatchingService;
import com.ats.aempi.model.AppUser;
import com.ats.aempi.service.AuditEventService;
import com.ats.aempi.service.PersonManagerService;
import com.ats.aempi.service.PersonQueryService;
import com.ats.aempi.service.UserManager;
import com.ats.aempi.service.ValidationService;
import com.ats.aempi.stringcomparison.StringComparisonService;
import com.ats.aempi.transformation.TransformationService;
import com.ats.aempi.util.PropertiesUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Context implements ApplicationContextAware
{
	protected static final Log log = LogFactory.getLog(Context.class);
	
	private static final ThreadLocal<Object[] /* UserContext */> userContextHolder = new ThreadLocal<Object[] /* UserContext */>();
	private static ApplicationContext applicationContext;
	private static UserManager userManager;
	private static PersonManagerService personService;
	private static PersonQueryService personQueryService;
	private static ValidationService validationService;
	private static EMPIConfiguration configuration;
	private static MatchingService matchingService;
	private static BlockingService blockingService;
	private static AuditEventService auditEventService;
	private static StringComparisonService stringComparisonService;
	private static TransformationService transformationService;
	private static FileLoaderConfigurationService fileLoaderConfigurationService;

	public static void startup() {
		ArrayList<String> configFiles = getConfigLocations();
		try {
			applicationContext = new ClassPathXmlApplicationContext((String[]) configFiles.toArray(new String[]{}));
			Object o = applicationContext.getBean("context");
//			log.info("全局service设置"+o.toString());
		} catch (Throwable t) {
			log.error("Failed while setting up the context for AEMPI: " + t, t);
		}
	}

	public static ArrayList<String> getConfigLocations() {
		ArrayList<String> configFiles = generateConfigFileList();
		
		addExtensionContextsFromPropertiesFile(configFiles);
		addExtensionContextsFromSystemProperty(configFiles);
		return configFiles;
	}

	public static String getAEmpiHome() {
		String aEmpiHome = EMPIConstants.AEMPI_HOME_ENV_VALUE;
		if (aEmpiHome == null || aEmpiHome.length() == 0) {
			aEmpiHome = EMPIConstants.AEMPI_HOME_VALUE;
		} else {
			System.setProperty(EMPIConstants.AEMPI_HOME, aEmpiHome);
		}
		//log.debug("AEMPI_HOME is set to " + aEmpiHome);
		return aEmpiHome;
	}
	
	/*
	public String getAEmpiHome()
	{
		String AEmpiHome = this.getClass().getClassLoader().getResource("/").getPath();
		return AEmpiHome;
	}
	*/

	private static ArrayList<String> generateConfigFileList() {
		ArrayList<String> configFiles = new ArrayList<String>();
		String aEmpiHome = getAEmpiHome();
		if (aEmpiHome != null && aEmpiHome.length() > 0) {
			configFiles.add("file:" + aEmpiHome + "/conf/applicationContext-resources.xml");
			configFiles.add("file:" + aEmpiHome + "/conf/applicationContext-dao.xml");
			configFiles.add("file:" + aEmpiHome + "/conf/applicationContext-service.xml");			
		} else {
			configFiles.add("classpath:/applicationContext-resources.xml");
			configFiles.add("classpath:/applicationContext-dao.xml");
			configFiles.add("classpath:/applicationContext-service.xml");
		}
		return configFiles;
	}

	private static void addExtensionContextsFromSystemProperty(ArrayList<String> configFiles) {
		String extensionContexts = System.getProperty(EMPIConstants.AEMPI_EXTENSION_CONTEXTS);
		addExtensionContextsFromCommaSeparatedList(configFiles, extensionContexts);
	}

	private static void addExtensionContextsFromPropertiesFile(ArrayList<String> configFiles) {
		Properties props;
		try {
			String filename = getAEmpiHome() + "/conf/" + EMPIConstants.AEMPI_EXTENSION_CONTEXTS_PROPERTY_FILENAME;
			//log.debug("Attempting to load extension contexts from " + filename);
			props = PropertiesUtils.load(new File(filename));
		} catch (Exception e) {
			log.warn("Unable to load the extension contexts properties file; will resort to System property. Error: " + e, e);
			return;
		}
		String extensionContexts = props.getProperty(EMPIConstants.AEMPI_EXTENSION_CONTEXTS);
		addExtensionContextsFromCommaSeparatedList(configFiles, extensionContexts);
	}

	private static void addExtensionContextsFromCommaSeparatedList(ArrayList<String> configFiles, String extensionContexts) {
		if (extensionContexts != null && extensionContexts.length() > 0) {
			String[] extContexts = extensionContexts.split(",");
			for (String extContext : extContexts) {
				//log.debug("Adding extension application context from location: " + extContext);
				configFiles.add(extContext);
			}
		}
	}
	
	public static String authenticate(String username, String password)
			throws AuthenticationException {
		return getUserContext().authenticate(username, password);
	}
	
	public static AppUser authenticate(String sessionKey)
			throws AuthenticationException {
		return getUserContext().authenticate(sessionKey);
	}

	public static UserContext getUserContext() {
		UserContext userContext = null;
		Object[] arr = userContextHolder.get();
		if (arr == null) {
			log.trace("userContext is null. Creating new userContext");
			userContext = new UserContext();
			userContext.setUserManager(userManager);
			setUserContext(userContext);
		}
		return (UserContext) userContextHolder.get()[0];
	}

	public static void setUserContext(UserContext ctx) {
		log.trace("Setting user context " + ctx);
		Object[] arr = new Object[] { ctx };
		userContextHolder.set(arr);
	}

	public static PersonManagerService getPersonManagerService() {
		return personService;
	}
	
	public void setPersonManagerService(PersonManagerService personService) {
		Context.personService = personService;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) {
		Context.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static UserManager getUserManager() {
		return Context.userManager;
	}
	
	public void setUserManager(UserManager userManager) {
	    Context.userManager = userManager;
	}

	public static EMPIConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(EMPIConfiguration configuration) {
		Context.configuration = configuration;
	}

	public static ValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(ValidationService validationService) {
		Context.validationService = validationService;
	}

	public synchronized static MatchingService getMatchingService() {
		if (matchingService != null && !matchingService.isInitialized()) {
			matchingService.init();
		}
		return matchingService;
	}

	public static void registerCustomMatchingService(MatchingService matchingService) {
		Context.matchingService = matchingService;
	}
	
	public void setMatchingService(MatchingService matchingService) {
		Context.matchingService = matchingService;
	}

	public static void registerCustomBlockingService(BlockingService blockingService) {
		Context.blockingService = blockingService;
	}
	
	public static BlockingService getBlockingService() {
		return blockingService;
	}

	public void setBlockingService(BlockingService blockingService) {
		Context.blockingService = blockingService;
	}

	public static PersonQueryService getPersonQueryService() {
		return personQueryService;
	}

	public void setPersonQueryService(PersonQueryService personQueryService) {
		Context.personQueryService = personQueryService;
	}
	
	public static StringComparisonService getStringComparisonService() {
		return stringComparisonService;
	}
	
	public void setStringComparisonService(StringComparisonService stringComparisonService) {
		Context.stringComparisonService = stringComparisonService;
	}

	public static TransformationService getTransformationService() {
		return transformationService;
	}
	
	public void setTransformationService(TransformationService transformationService) {
		Context.transformationService = transformationService;
	}

	public static AuditEventService getAuditEventService() {
		return auditEventService;
	}

	public void setAuditEventService(AuditEventService auditEventService) {
		Context.auditEventService = auditEventService;
	}
	public static void registerCustomFileLoaderConfigurationService(FileLoaderConfigurationService fileLoaderConfigurationService) {
		Context.fileLoaderConfigurationService = fileLoaderConfigurationService;
	}
	
	public static FileLoaderConfigurationService getFileLoaderConfigurationService() {
		return fileLoaderConfigurationService;
	}

	public void setFileLoaderConfigurationService(FileLoaderConfigurationService fileLoaderConfigurationService) {
		Context.fileLoaderConfigurationService = fileLoaderConfigurationService;
	}
}
