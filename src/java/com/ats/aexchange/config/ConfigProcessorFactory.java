package com.ats.aexchange.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The factory class to get Config Processor.
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class ConfigProcessorFactory {
	private static final Log log = LogFactory.getLog(ConfigProcessorFactory.class);

	/**The instance*/
	public static ConfigProcessor processor = null;
	
	/**
	 * Gets the config processor.
	 * 
	 * @return
	 */
	public synchronized static ConfigProcessor getConfigProcessor() {
		if (processor == null) {
			String configProcessorClass = BootStrapProperties.getConfigProcessorClass();
			try {
				Class clazz = Class.forName(configProcessorClass);

				if (log.isInfoEnabled()) {
					log.info("Loading ConfigProcessor from " + configProcessorClass);
				}
				
				processor = (ConfigProcessor)clazz.newInstance();
			} catch (Exception e) {
				String message = "Failed to load ConfigProcessor. Nested message is " + e.getMessage();
				log.error(message, e);
			}
		}
		return processor;
	}
}

