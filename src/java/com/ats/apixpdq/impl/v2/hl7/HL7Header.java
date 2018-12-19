package com.ats.apixpdq.impl.v2.hl7;

import org.apache.log4j.Logger;

import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.utils.DateUtil;

import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.common.PixPdqException;

import com.globalmentor.log.Log;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
/**
 * This class contains common utility for Message Header Processing.
    	
 * @author Rasakannu Palaniyandi
 * @version Dec 01, 2008
 */

public class HL7Header {
	ca.uhn.hl7v2.model.v231.segment.MSH msh231;
	ca.uhn.hl7v2.model.v25.segment.MSH msh25;
	Message messageIn;
	Identifier idSendingApplication;
	Identifier idSendingFacility;
	Identifier idReceivingApplication;
	Identifier idReceivingFacility;
	String messageControlId;
	String messageCode;
	String triggerEvent;	
	String messageStructure;
	String messageDate;
	private static Logger log = Logger.getLogger(HL7Header.class);
	/**
	 * Constructor
	 * 
	 * @param msgIn the incoming message
	 * @throws ApplicationException
	 */
	public HL7Header(Message msgIn) throws PixPdqException {
		messageIn = msgIn;
		
		//System.out.println("HL7字段:" + "\n" + messageIn);
		
		
		
		try {
			if (messageIn.getVersion().equals("2.3.1"))
			{				
				msh231 = (ca.uhn.hl7v2.model.v231.segment.MSH)msgIn.get("MSH");
				idSendingApplication   = new Identifier(msh231.getSendingApplication().getNamespaceID().getValue(), msh231.getSendingApplication().getUniversalID().getValue(), msh231.getSendingApplication().getUniversalIDType().getValue());
				idSendingFacility= new Identifier(msh231.getSendingFacility().getNamespaceID().getValue(), msh231.getSendingFacility().getUniversalID().getValue(), msh231.getSendingFacility().getUniversalIDType().getValue());
				idReceivingApplication   = new Identifier(msh231.getReceivingApplication().getNamespaceID().getValue(), msh231.getReceivingApplication().getUniversalID().getValue(), msh231.getReceivingApplication().getUniversalIDType().getValue());
				idReceivingFacility= new Identifier(msh231.getReceivingFacility().getNamespaceID().getValue(), msh231.getReceivingFacility().getUniversalID().getValue(), msh231.getReceivingFacility().getUniversalIDType().getValue());
				messageControlId=msh231.getMessageControlID().getValue();
				messageCode =msh231.getMessageType().getMessageType().getValue();
				triggerEvent = msh231.getMessageType().getTriggerEvent().getValue();
				messageStructure =msh231.getMessageType().getMessageStructure().getValue();
				messageDate =msh231.getDateTimeOfMessage().getTimeOfAnEvent().getValue();
				
				//System.out.println(messageIn.getVersion());
				//System.out.println(msh231.getDateTimeOfMessage());
				//System.out.println(msh231.getDateTimeOfMessage().getTimeOfAnEvent());
				//System.out.println(msh231.getDateTimeOfMessage().getTimeOfAnEvent().getValue());
			}
			else
			{
				msh25 = (ca.uhn.hl7v2.model.v25.segment.MSH)msgIn.get("MSH");
				idSendingApplication   = new Identifier(msh25.getSendingApplication().getNamespaceID().getValue(), msh25.getSendingApplication().getUniversalID().getValue(), msh25.getSendingApplication().getUniversalIDType().getValue());
				idSendingFacility= new Identifier(msh25.getSendingFacility().getNamespaceID().getValue(), msh25.getSendingFacility().getUniversalID().getValue(), msh25.getSendingFacility().getUniversalIDType().getValue() );
				idReceivingApplication   = new Identifier(msh25.getReceivingApplication().getNamespaceID().getValue(), msh25.getReceivingApplication().getUniversalID().getValue(), msh25.getReceivingApplication().getUniversalIDType().getValue());
				idReceivingFacility= new Identifier(msh25.getReceivingFacility().getNamespaceID().getValue(), msh25.getReceivingFacility().getUniversalID().getValue(), msh25.getReceivingFacility().getUniversalIDType().getValue() );
				messageControlId =msh25.getMessageControlID().getValue();
				triggerEvent = msh25.getMessageType().getTriggerEvent().getValue();
				messageCode =msh25.getMessageType().getMessageCode().getValue();
				messageStructure =msh25.getMessageType().getMessageStructure().getValue();
				
				//System.out.println(msh25.getDateTimeOfMessage());
				//System.out.println(msh25.getDateTimeOfMessage().getTime());
				//System.out.println(msh25.getDateTimeOfMessage().getTime().getValue());
				messageDate =msh25.getDateTimeOfMessage().getTime().getValue();
			}	
		}
		catch (HL7Exception e) 
		{
			throw new PixPdqException(e);
		}		
	}
	
	/**
     * This method returns the sending Application Identifier
     * 
     * @return the sending application of this message header
     */
	public Identifier getSendingApplication(){		
		return idSendingApplication;
	}
	
	/**
     * This method returns the sending Facility Identifier
     * 
     * @return the sending facility of this message header
     */
	public Identifier getSendingFacility(){		
		return idSendingFacility;
	}
	
	/**
     * This method returns the Receiving Application Identifier
     * 
     * @return the receiving application of this message header
     */
	public Identifier getReceivingApplication(){				
		return idReceivingApplication;
	}
	
	/**
     * This method returns the Receiving Facility Identifier
     * 
     * @return the receiving facility of this message header
     */
	public Identifier getReceivingFacility(){		
		return idReceivingFacility;		
	}
	
	/**
     * This method returns the MessageControl Id
     * 
     * @return the message control id of this message header
     */
	public String getMessageControlId(){
		return messageControlId;
	}

	/**
     * This method returns the Message date
     * 
     * @return the message date
     */
	
	public String getMessagedate(){
		return messageDate;
	}	
	
	/**
	 * Populates <code>MessageStore</code> from this HL7Header. 
	 * This message should be invoked after this HL7Header 
	 * object is fully initialized. 
	 *  
	 * @param store the <code>MessageStore</code> to be populated 
	 * @return the same <code>MessageStore</code> object with populated data
	 */
	public MessageStore populateMessageStore(MessageStore store) {
        if (store == null)  return null;
        
    	store.setMessageId( this.getMessageControlId() );
    	store.setSendingFacility( this.getSendingFacility()==null? null : this.getSendingFacility().getAuthorityNameString() );
    	store.setSendingApplication( this.getSendingApplication()==null? null : this.getSendingApplication().getAuthorityNameString() );
    	store.setReceivingFacility( this.getReceivingFacility()==null ? null : this.getReceivingFacility().getAuthorityNameString() );
    	store.setReceivingApplication( this.getReceivingApplication()==null? null : this.getReceivingApplication().getAuthorityNameString() );
    	store.setMessageCode(this.getMessageCode());
    	store.setTriggerEvent(this.getTriggerEvent());
    	store.setMessageStructure(this.getMessageStructure());
    	store.setMessageDate( DateUtil.convertHL7Date(this.getMessagedate()) );
    	return store;
	}

	/**
	 * Populates <code>MessageHeader</code> from this HL7Header. 
	 * This message should be invoked after this HL7Header 
	 * object is fully initialized. 
	 *  
	 * @return the same <code>MessageHeader</code> object with populated data
	 */
	public MessageHeader toMessageHeader() {
		MessageHeader header = new MessageHeader();
		header.setSendingFacility(this.getSendingFacility());
		header.setSendingApplication(this.getSendingApplication());
		header.setReceivingFacility(this.getReceivingApplication());
		header.setReceivingApplication(this.getReceivingFacility());
		header.setMessageCode(this.getMessageCode());
		header.setTriggerEvent(this.getTriggerEvent());
		header.setMessageStructure(this.getMessageStructure());
		header.setMessgeDate(DateUtil.convertHL7DateToCalender(this
				.getMessagedate()));
		return header;
	}

	/**
	 * Gets the message code
	 * 
	 * @return the message code
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * Gets the trigger event
	 * 
	 * @return the trigger event
	 */
	public String getTriggerEvent() {
		return triggerEvent;
	}

	/**
	 * Gets the message structure
	 * 
	 * @return the message structure
	 */
	public String getMessageStructure() {
		return messageStructure;
	}
}
