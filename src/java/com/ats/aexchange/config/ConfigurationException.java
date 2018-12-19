package com.ats.aexchange.config;

/**
 * This exception is generated when there is a problem
 * with the property and spring context configuration
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 *
 */
public class ConfigurationException extends Exception {
	
	private static final long serialVersionUID = 4508000542617148696L;
				
	/**
	 * Creates a new ConfigurationException.
	 * 
	 * @param msg a description of the problem
	 */
	public ConfigurationException(String msg) {
		super(msg);
	}

	/**
	 * Creates a new ConfigurationException.
	 * 
	 * @param msg a description of the problem
	 * @param cause the embedded Throwable
	 */
    public ConfigurationException(String msg, Throwable cause){
        super(msg, cause);
    }

    
	/**
	 * Creates a new ConfigurationException.
	 * 
	 * @param cause the embedded Throwable
	 */
    public ConfigurationException(Throwable cause) {
        super(cause);
    }

}
