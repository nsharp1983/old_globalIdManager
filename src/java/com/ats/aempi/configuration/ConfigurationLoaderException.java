package com.ats.aempi.configuration;

public class ConfigurationLoaderException extends Exception
{
	private static final long serialVersionUID = -8377338224501969315L;

	public ConfigurationLoaderException() {
	}

	public ConfigurationLoaderException(String message) {
		super(message);
	}

	public ConfigurationLoaderException(Throwable cause) {
		super(cause);
	}

	public ConfigurationLoaderException(String message, Throwable cause) {
		super(message, cause);
	}
}
