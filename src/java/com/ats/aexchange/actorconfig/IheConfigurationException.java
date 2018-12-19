package com.ats.aexchange.actorconfig;

/**
 * The exception is thrown when a connection is not configured properly
 * and information cannot be translated to IHE form.
 * 
 * @author Jim Firby
 */
public class IheConfigurationException extends Exception {

	private static final long serialVersionUID = 4506446482932000759L;

	public IheConfigurationException(String message) {
		super(message);
	}
	
	public IheConfigurationException(String message, Throwable e) {
		super(message, e);
	}
}
