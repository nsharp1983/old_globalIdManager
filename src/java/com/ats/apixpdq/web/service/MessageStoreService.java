package com.ats.apixpdq.web.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ats.apixpdq.api.IMessageStoreLogger;
import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.web.dao.MessageStoreDAO;
import com.ats.apixpdq.web.dao.MessageStoreDAOImpl;
import com.ats.apixpdq.web.service.MessageStoreService;


/**
 * 
 * @author Anil kumar
 * @date Nov 25, 2008
 */

public class MessageStoreService implements IMessageStoreLogger {

	private static Logger log = Logger.getLogger(MessageStoreService.class);

	private MessageStoreDAO messagelogdao = new MessageStoreDAOImpl();
	
	public void saveLog(MessageStore messageLog)  {
		if (messageLog.getInMessage() != null) {
			String messagein=messageLog.getInMessage().replace("\r", "<br>");;
			messageLog.setInMessage(messagein);
		}
		if (messageLog.getOutMessage() != null) {
			String messageout=messageLog.getOutMessage().replace("\r", "<br>");
			messageLog.setOutMessage(messageout);
		}
		messagelogdao.saveLog(messageLog);
	}

	public List<MessageStore> searchLog(MessageStore messageLog)
		 {
		return messagelogdao.searchLog(messageLog);

	}
}
