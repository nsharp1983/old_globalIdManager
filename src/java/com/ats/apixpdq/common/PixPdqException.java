package com.ats.apixpdq.common;

/**
 * This exception is generated when there is a problem
 * with PixManager operations
 * 
 * @author Wenzhi Li
 * @version 1.0 - Oct 21, 2008
 */
public class PixPdqException extends Exception {

	private static final long serialVersionUID = 6855812592011807164L;
	/**
	 * Create a new PixManagerException.
	 * 
	 * @param string A description of the problem
	 */
	public PixPdqException(String string) {
		super(string);
	}

    public PixPdqException(String msg, Throwable cause){
        super(msg, cause);
    }

    public PixPdqException(Throwable cause) {
        super(cause);
    }
}
