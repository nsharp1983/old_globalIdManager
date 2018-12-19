package com.ats.aexchange.config;

import org.springframework.context.ApplicationContext;

/**
 * This interface defines the method such as getApplicationContext
 * related to the given spring configuration. 
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 * @see PropertyConfig
 */
public interface SpringConfig {

    /**
     * Gets the application context.
     * 
     * @return the application context
     */
	public ApplicationContext getApplicationContext();
}
