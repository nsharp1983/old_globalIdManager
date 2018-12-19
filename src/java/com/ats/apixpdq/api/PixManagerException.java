package com.ats.apixpdq.api;

/**
 * This exception is generated when there is a problem
 * with PixManager operations
 * 
 * @author Wenzhi Li
 * @version 1.0 - Oct 21, 2008
 */
public class PixManagerException extends Exception {

	private static final long serialVersionUID = 4496513837046385313L;
	/**
	 * Create a new PixManagerException.
	 * 
	 * @param string A description of the problem
	 */
	public PixManagerException(String string) {
		super(string);
	}

    public PixManagerException(String msg, Throwable cause){
        super(msg, cause);
    }

    public PixManagerException(Throwable cause) {
        super(cause);
    }
}
