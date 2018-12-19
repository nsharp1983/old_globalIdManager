package com.ats.apixpdq.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.web.action.MessageStoreAction;
import com.ats.apixpdq.web.service.MessageStoreService;
import com.ats.apixpdq.web.beans.MessageStoreBean;

public class MessageStoreAction extends AtsAction {

	private static final long serialVersionUID = 1L;
	private String ip = null;
	private String messageDate = "DD-MM-YYYY";
	private String messageId = null;
	private String errorMessage = null;
	private String messageType = null;
	private String sendingFacility = null;
	private String sendingApplication = null;
	private String receivingFacility = null;
	private String receivingApplication = null;
	private String action = null;

	/**
	 * 
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @return messageDate
	 */
	public String getMessageDate() {
		return messageDate;
	}

	/**
	 * 
	 * @param messageDate
	 */
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}

	/**
	 * 
	 * @return messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * 
	 * @param messageId
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * 
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 
	 * @return sendingFacility
	 */
	public String getSendingFacility() {
		return sendingFacility;
	}

	/**
	 * 
	 * @param sendingFacility
	 */
	public void setSendingFacility(String sendingFacility) {
		this.sendingFacility = sendingFacility;
	}

	/**
	 * 
	 * @return sendingApplication
	 */
	public String getSendingApplication() {
		return sendingApplication;
	}

	/**
	 * 
	 * @param sendingApplication
	 */
	public void setSendingApplication(String sendingApplication) {
		this.sendingApplication = sendingApplication;
	}

	/**
	 * 
	 * @return receivingFacility
	 */
	public String getReceivingFacility() {
		return receivingFacility;
	}

	/**
	 * 
	 * @param receivingFacility
	 */
	public void setReceivingFacility(String receivingFacility) {
		this.receivingFacility = receivingFacility;
	}

	/**
	 * 
	 * @return receivingApplication
	 */
	public String getReceivingApplication() {
		return receivingApplication;
	}

	/**
	 * 
	 * @param receivingApplication
	 */
	public void setReceivingApplication(String receivingApplication) {
		this.receivingApplication = receivingApplication;
	}

	/**
	 * 
	 * @return action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 
	 * @return messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * 
	 * @param messageType
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	private static Logger log = Logger.getLogger(MessageStoreAction.class);

	public String execute() throws Exception {
		Collection<MessageStore> personlist = null;
		MessageStoreBean messageForm = new MessageStoreBean();
		messageForm.setIp(ip);
		messageForm.setAction(action);
		messageForm.setErrorMessage(errorMessage);
		messageForm.setMessageId(messageId);
		messageForm.setMessageType(messageType);
		messageForm.setMessageDate(messageDate);
		messageForm.setReceivingApplication(receivingApplication);
		messageForm.setReceivingFacility(receivingFacility);
		messageForm.setSendingApplication(sendingApplication);
		messageForm.setSendingFacility(sendingFacility);
		
		if (messageForm.getAction() == null || messageForm.getAction().equals("")) {
			personlist = new ArrayList<MessageStore>();
			request.setAttribute("personlist", personlist);
			return "success";
		}
		if (messageForm != null && messageForm.getAction() != null && messageForm.getAction().equalsIgnoreCase("Search")) {

			MessageStore messageLog = new MessageStore();
			if (messageForm.getIp() != null && messageForm.getIp() != "")
				messageLog.setIp(messageForm.getIp());
			if (messageForm.getErrorMessage() != null && messageForm.getErrorMessage() != "")
				messageLog.setErrorMessage(messageForm.getErrorMessage());
			if (messageForm.getMessageDate() != null && !messageForm.getMessageDate().equalsIgnoreCase("mm/dd/yyyy"))
				messageLog.setMessageDate(_convertStringToDate(messageForm.getMessageDate()));
			if (messageForm.getMessageId() != null && messageForm.getMessageId() != "")
				messageLog.setMessageId(messageForm.getMessageId());
			if (messageForm.getSendingFacility() != null && messageForm.getSendingFacility() != "")
				messageLog.setSendingFacility(messageForm.getSendingFacility());
			if (messageForm.getSendingApplication() != null && messageForm.getSendingApplication() != "")
				messageLog.setSendingApplication(messageForm.getSendingApplication());
			if (messageForm.getReceivingFacility() != null && messageForm.getReceivingFacility() != "")
				messageLog.setReceivingFacility(messageForm.getReceivingFacility());
			if (messageForm.getReceivingApplication() != null && messageForm.getReceivingApplication() != "")
				messageLog.setReceivingApplication(messageForm.getReceivingApplication());

			MessageStoreService messageservice = new MessageStoreService();
			personlist = messageservice.searchLog(messageLog);
			if (personlist != null) {
				request.setAttribute("personlist", personlist);
			} else {
				personlist = new ArrayList<MessageStore>();
				request.setAttribute("personlist", personlist);
			}
		}
		return "success";
	}

	/**
	 * This method converts String type to Date type.
	 * 
	 * @param String
	 *            date
	 * @return Date
	 */
	private Date _convertStringToDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		if (date == null)
			return null;

		try {
			Date date1 = null;
			date1 = dateFormat.parse(date);

			return date1;
		} catch (ParseException pex) {
			return null;
		}
	}

}
