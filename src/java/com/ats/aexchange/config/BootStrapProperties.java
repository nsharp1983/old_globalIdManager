package com.ats.aexchange.config;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ats.aexchange.utils.ExceptionUtil;
import com.ats.aexchange.utils.StringUtil;

/**
 * The class to define the properties to bootstrap the application. If
 * nothing is defined in the bootstrap.properties, the default ones 
 * will be used. The most important two properties are: 1. property.config.class -
 * which specifies the class to handle property configuration. 
 * 2. spring.config.class - which specifies the class to handle spring 
 * container configuration.
 *  
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 *
 */
public class BootStrapProperties {
	private final static Log log = LogFactory.getLog(BootStrapProperties.class);
	
	/**The parameter for the Java Class that handles the property configuration*/
	public final static String PROPERTY_CONFIG_CLASS_PARAM = "property.config.class";
	/**The parameter for the Java Class that handles the spring configuration*/
	public final static String SPRING_CONFIG_CLASS_PARAM = "spring.config.class";
	/**The parameter for the Java Class of config processor*/
	public final static String CONFIG_PROCESSOR_CLASS_PARAM = "config.process.class";
	/**The parameter for the property file names*/
	public final static String PROPERTY_FILES_PARAM = "property.files";

	/**The parameter for JMX Service URL */
	public final static String JMX_SERVICE_URL_PARAM = "jmx.service.url";
	/**The parameter for Spring Container Id */
	public final static String SPRING_CONTAINER_PARAM = "springContainerId";
	
	public final static String DEFAULT_PROPERTY_CONFIG_CLASS = "com.ats.aexchange.config.DefaultPropertyConfig";
	public final static String DEFAULT_SPRING_CONFIG_CLASS = "com.ats.aexchange.config.DefaultSpringConfig";
	public final static String DEFAULT_CONFIG_PROCESSOR_CLASS = "com.ats.aexchange.config.DefaultConfigProcessor";
	public final static String DEFAULT_SPRING_CONTAINER_ID   = "openxdsSpringContainer";
	
	private final static String BOOT_STRAP_PROPERTIES_FILE = "bootstrap.properties";
	private static Properties properties = null;

	static {  
		InputStream is = BootStrapProperties.class.getClassLoader().getResourceAsStream(BOOT_STRAP_PROPERTIES_FILE);
		
		if (is == null) { 
			log.fatal("Cannot load " + BOOT_STRAP_PROPERTIES_FILE );
		}
		
		properties = new java.util.Properties();
		
		try {
			properties.load(is);
		}
		catch (Exception e) {
			log.fatal(ExceptionUtil.getExceptionDetails(e));
		}
	}
	
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * Gets the property files to be loaded in the application.
	 *  
	 * @param defaults the default array of property files
	 * @return an array of property file names
	 */
	public static String[] getPropertyFiles(String[] defaults) {
		String fileList = properties.getProperty(PROPERTY_FILES_PARAM);

		if (!StringUtil.goodString(fileList)) {
			return defaults;
		}

		String[] ret = fileList.split("\\s*[,:;]\\s*");
		return ret;
	}
	
	public static String getPropertyConfigClass() {
		String ret = properties.getProperty(PROPERTY_CONFIG_CLASS_PARAM);
		
		//Use the default property config class
		if (!StringUtil.goodString(ret)) {
			ret = DEFAULT_PROPERTY_CONFIG_CLASS;
		}
		return ret;
	}

	public static String getSpringConfigClass() {
		String ret = properties.getProperty(SPRING_CONFIG_CLASS_PARAM);
		
		//Use the default spring config class
		if (!StringUtil.goodString(ret)) {
			ret = DEFAULT_SPRING_CONFIG_CLASS;
		}
		return ret;
	}

	public static String getConfigProcessorClass() {
		String ret = properties.getProperty(CONFIG_PROCESSOR_CLASS_PARAM);
		
		//Use the default spring config class
		if (!StringUtil.goodString(ret)) {
			ret = DEFAULT_CONFIG_PROCESSOR_CLASS;
		}
		return ret;
	}
	
	public static String getJMXServiceURL() {
		return properties.getProperty(JMX_SERVICE_URL_PARAM);
	}

	public static String getSpringContainerId() {
		return properties.getProperty(SPRING_CONTAINER_PARAM);
	}
}
