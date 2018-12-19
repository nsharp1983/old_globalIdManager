package com.ats.apixpdq.impl.v2;

import com.ats.apixpdq.api.IMessageStoreLogger;
import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.impl.v2.hl7.HL7Util;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

/**
 * The data container for message persistence.
 * 
 * @author Anil kumar
 * @date Nov 25, 2008
 */
public class MessageStoreHelper {
	
	/**
	 * Initiates a <code>MessageStore</code> instance, and log the 
	 * initial message, either in-bound message or out-bound message.
	 * 
	 * @param message the initial message to log
	 * @param storeLogger the {@link IMessageStoreLogger}
	 * @param isInbound whether the message is an in-bound message or out-bound message 
	 * @return a <code>MessageStore</code>
	 * @throws HL7Exception
	 */
	public static MessageStore initMessageStore(Message message, IMessageStoreLogger storeLogger, boolean isInbound) 
	throws HL7Exception {
		if (storeLogger == null)
			return null;
		
		MessageStore ret = new MessageStore(); 
		if (message != null) {
			String encodedMessage = HL7Util.encodeMessage(message);
		    if (isInbound)
		    	ret.setInMessage( encodedMessage );
		    else 
		    	ret.setOutMessage( encodedMessage );
		}
	    return ret;
	}
	
	/**
	 * Persists the <code>MessageStore</code> log, and save the return message
	 * which could be either in-bound or out-bound.
	 * 
	 * @param message the last message to save and log
	 * @param storeLogger the {@link IMessageStoreLogger}
	 * @param isInbound whether the message is an in-bound message or out-bound message 
	 * @param msgStore the <code>MessageStore</code> instance to hold the log data
	 * @throws HL7Exception if the message could not be encoded 
	 */
	public static void saveMessageStore(Message message, IMessageStoreLogger storeLogger, boolean isInbound, MessageStore msgStore) 
	throws HL7Exception {
		if (msgStore == null || storeLogger == null )
			return;
		
	    if (message != null) {
		    String encodedMessage = HL7Util.encodeMessage(message);
		    if (isInbound) 
			    msgStore.setInMessage( encodedMessage );
		    else
		    	msgStore.setOutMessage( encodedMessage );
	    }		
	    storeLogger.saveLog( msgStore );
	
	}

}
