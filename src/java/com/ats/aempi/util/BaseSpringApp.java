package com.ats.aempi.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.context.Context;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class BaseSpringApp
{
	private String[] DEFAULT_CONTEXT_RESOURCES = {
	        "/applicationContext-resources.xml", "classpath:/applicationContext-dao.xml",
	        "/applicationContext-service.xml", "classpath*:/**/applicationContext.xml"
		};
    protected final Log log = LogFactory.getLog(getClass());

	
	private GenericApplicationContext applicationContext;

	public void startup() {
		Context.startup();
		Context.authenticate("admin", "admin");
	}
	
	public void startup(String[] locations) {
		Context.startup();
		Context.authenticate("admin", "admin");
	}
	
	public final ConfigurableApplicationContext getApplicationContext() {
		// lazy load, in case startup() has not yet been called.
		if (applicationContext == null) {
			try {
				startup(DEFAULT_CONTEXT_RESOURCES);
			}
			catch (Exception e) {
				if (log.isDebugEnabled()) {
					log.debug("Caught exception while retrieving the ApplicationContext for application ["
							+ getClass().getName() + "].", e);
				}
			}
		}
		return this.applicationContext;
	}
}
