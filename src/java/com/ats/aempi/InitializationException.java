package com.ats.aempi;

public class InitializationException extends RuntimeException
{
	private static final long serialVersionUID = -3892027771172929962L;
	
	public InitializationException() {
		super();
	}

	public InitializationException(String message) {
		super(message);
	}

	public InitializationException(Throwable exception) {
		super(exception);
	}

	public InitializationException(String message, Throwable exception) {
		super(message, exception);
	}
}
