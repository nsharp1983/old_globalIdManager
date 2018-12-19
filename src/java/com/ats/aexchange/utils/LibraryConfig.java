package com.ats.aexchange.utils;


/**
 * Configure the connection library with necessary elements
 *
 * @author Josh Flachsbart
 * @version 2.0, Mar 13, 2007
 *
 */
public class LibraryConfig {
	static LibraryConfig lc;
	
	private ILogContext logContext;

	
	public static synchronized LibraryConfig getInstance() {
		if (lc == null) {
			lc = new LibraryConfig();
		}
		return lc;
	}

	/**
	 * Get the LogContext to be used for auditing
	 * @return logContext the LogContext
	 */
	public ILogContext getLogContext() {
		return logContext;
	}
	
	/**
	 * Set the LogContext to be used for auditing
	 * @param logContext the LogContext
	 */
	public void setLogContext(ILogContext logContext) {
		this.logContext = logContext;
	}

	
	public interface ILogContext {
		String getUserId();
		String getClientAddress();
		String getUserSystem();
		String getUserName();
	}

}
