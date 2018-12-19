package com.ats.apixpdq.api;

/**
 * This exception is generated when there is a problem
 * manipulating patient IDs.
 * 
 * @author Jim Firby
 * @version 2.0 - Oct 22, 2005
 */
public class PatientException extends Exception {

	//TODO Figure out what serialVersionUID is for
	private static final long serialVersionUID = -393466255655735140L;

	/**
	 * Create a new PatientIdException.
	 * 
	 * @param string A description of the problem
	 */
	public PatientException(String string) {
		super(string);
	}

    public PatientException(String msg, Throwable cause){
        super(msg, cause);
    }

    public PatientException(Throwable cause) {
        super(cause);
    }




}
