package com.ats.aexchange.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The class represents the default implementation of loading Spring 
 * configuration, and provides a facade to get the spring application
 * context. 
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 * @see PropertyFacade
 */
public class DefaultSpringConfig implements SpringConfig {

	private final static Log log = LogFactory.getLog(DefaultSpringConfig.class);
	
	private ApplicationContext applicationContext = null;

	public DefaultSpringConfig() throws ConfigurationException {
		initializeSpring(null);
	}
	
	public DefaultSpringConfig(String[] configLocations) throws ConfigurationException {
		initializeSpring(configLocations);
	}
	
	@Override
	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	private void initializeSpring(String[] configLocations) throws ConfigurationException {
			if (configLocations != null)
				this.applicationContext = new ClassPathXmlApplicationContext(configLocations);
			else
				this.applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
				
			
			//add a shutdown hook for the above context... 
			((AbstractApplicationContext)this.applicationContext).registerShutdownHook();
	}
	
	protected String[] getConfigLocations() {
        return new String[] {
               "classpath*:/applicationContext.xml" // for modular projects
            };
    }

}
