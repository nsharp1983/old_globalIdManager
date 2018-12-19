package com.ats.aempi;

public class ApplicationException extends Exception
{
	private static final long serialVersionUID = 5094839624770398803L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable exception) {
		super(exception);
	}

	public ApplicationException(String message, Throwable exception) {
		super(message, exception);
	}

}
