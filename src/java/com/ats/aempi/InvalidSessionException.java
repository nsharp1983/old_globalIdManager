package com.ats.aempi;

public class InvalidSessionException extends RuntimeException
{
	private static final long serialVersionUID = -3736209758912312935L;
	
	public InvalidSessionException() {
		super();		
	}

	public InvalidSessionException(String message) {
		super(message);
	}

	public InvalidSessionException(Throwable exception) {
		super(exception);
	}

	public InvalidSessionException(String message, Throwable exception) {
		super(message, exception);
	}
}
