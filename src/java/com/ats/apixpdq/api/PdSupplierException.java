package com.ats.apixpdq.api;

/**
 * This exception is generated when there is a problem
 * with PdSupplier operations
 * 
 * @author Wenzhi Li
 * @version 1.0 - Oct 21, 2008
 */
public class PdSupplierException extends Exception {

   private static final long serialVersionUID = -5589454490756195834L;
	/**
	 * Create a new PdSupplierException.
	 * 
	 * @param string A description of the problem
	 */
	public PdSupplierException(String string) {
		super(string);
	}

    public PdSupplierException(String msg, Throwable cause){
        super(msg, cause);
    }

    public PdSupplierException(Throwable cause) {
        super(cause);
    }
}
