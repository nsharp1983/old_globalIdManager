package com.ats.apixpdq.api;


/**
 * This interface logger logs a message to be persistent.
 * 
 * @author Wenzhi Li
 * @version 1.0, Dec 15, 2008
 */
public interface IMessageStoreLogger {
	/**
	 * Method to save the MessageStore object.
	 *  
	 * @param messageLog a {@link MessageStore}
	 */
	public void saveLog(MessageStore messageLog);

}
