package com.ats.apixpdq.common;

public class PixPdqErrorCodeException extends Exception{
	String errorCode;

	public PixPdqErrorCodeException(String reason, String errorCode) {
		super(reason);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	

}
