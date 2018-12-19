package com.ats.apixpdq.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aexchange.config.ConfigurationException;
import com.ats.aexchange.config.SpringFacade;
import org.springframework.context.ApplicationContext;

import com.ats.apixpdq.api.IPdSupplierAdapter;
import com.ats.apixpdq.api.IPixManagerAdapter;

/**
 * This class manages each module Spring initialization.
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 *
 */
public class PixPdqFactory {
	
	private static final Log log = LogFactory.getLog(PixPdqFactory.class);

	private static final PixPdqFactory SINGLETON = new PixPdqFactory();

	private ApplicationContext applicationContext;

	private PixPdqFactory() {
		super();
		try {
			SpringFacade.loadSpringConfig( getConfigLocations() );
			applicationContext = SpringFacade.getApplicationContext();
		}catch(ConfigurationException e){
			e.printStackTrace();
		}
	}

	/**
	 * Singleton 
	 *
	 * @return the Singleton of this Factory
	 */
	public static PixPdqFactory getInstance() {
		return SINGLETON;
	}
	
	private String[] getConfigLocations() {
        return new String[] {
               "classpath*:/*applicationContext.xml" // for modular projects
            };
    }

	/**
	 * Gets spring beans object of the given bean name.
	 * 
	 * @param beanName the bean name
	 * @return the bean object
	 */
	public Object getBean(String beanName) {
		return this.applicationContext.getBean(beanName);
	}

	/**
	 * The factory method to get {@link IPixManagerAdapter}
	 * 
	 * @return the singleton {@link IPixManagerAdapter} instance
	 */
	public static IPixManagerAdapter getPixManagerAdapter() {
		return (IPixManagerAdapter)getInstance().getBean("pixManagerAdapter");
	}

	/**
	 * The factory method to get {@link IPdSupplierAdapter}
	 * 
	 * @return the singleton {@link IPdSupplierAdapter} instance
	 */
	public static IPdSupplierAdapter getPdSupplierAdapter() {
		return (IPdSupplierAdapter)getInstance().getBean("pdSupplierAdapter");
	}
}
