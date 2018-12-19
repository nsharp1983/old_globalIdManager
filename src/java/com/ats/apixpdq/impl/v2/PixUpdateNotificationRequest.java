package com.ats.apixpdq.impl.v2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.audit.ActiveParticipant;
import com.ats.aexchange.audit.ParticipantObject;
import com.ats.aexchange.audit.TypeValuePair;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.utils.DateUtil;

import com.ats.apixpdq.api.IPixUpdateNotificationRequest;
import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.BaseHandler;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.impl.v2.hl7.HL7Channel;
import com.ats.apixpdq.impl.v2.hl7.HL7Header;
import com.ats.apixpdq.impl.v2.hl7.HL7Util;
import com.ats.apixpdq.impl.v2.hl7.HL7v231;
import com.ats.apixpdq.impl.v2.hl7.HL7v25;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.ELD;
import ca.uhn.hl7v2.model.v25.datatype.ST;
import ca.uhn.hl7v2.model.v25.datatype.XPN;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.ADT_A05;
import ca.uhn.hl7v2.model.v25.segment.EVN;
import ca.uhn.hl7v2.model.v25.segment.PID;


/**
* The request class of PIX update notification, which is used to notify 
* subscribed PIX Consumers when there is change in a
* set of cross reference patient identifiers for any of the patient
* identifiers belonging to patient identifier domains of interest to 
* the PIX consumers.
* 
* @author Rasakannu Palaniyandi
* @author Wenzhi Li
* @version Dec 24, 2008
*  
*/
class PixUpdateNotificationRequest implements IPixUpdateNotificationRequest {
	
    private static Logger log = Logger.getLogger(PixUpdateNotificationRequest.class);

    /** The connection description of the actor for this handler */
	private IConnectionDescription connection;
	
	private PixManager actor = null;
    private List<PatientIdentifier> pids = null;
    
    /**
     * Constructor
     * 
     * @param actor the PIX Manager actor for this PIX Update Notification request
     * @param pids a list of patient identifiers to be notified
     */
	PixUpdateNotificationRequest(PixManager actor, List<PatientIdentifier> pids) {
		super();
		this.actor = actor;
		this.pids = pids;
	}
 
	/**
	 * Executes {@link IPixUpdateNotificationRequest}. It creates and sends 
	 * the update notification message to subscribed PIX Consumers.
	 * 
     * @param pids the list of patient ids to to be sent
     */
	public void execute(){		
		if (pids==null || pids.size()==0)
			return ;

		for(IConnectionDescription connection : actor.getPixConsumerConnections()){
            boolean doNotNotify = Boolean.parseBoolean(connection.getProperty("DoNotNotify"));
            if (doNotNotify)
            	continue ;

			List<PatientIdentifier> idsToBeUpdated = new ArrayList<PatientIdentifier>();
			Set<Identifier> defaultDomains = Configuration.getAllDomains(actor.getActorDescription());
			Set<Identifier> domainsOfInterest = PixPdqFactory.getPixManagerAdapter().getDomainIdentifiers(defaultDomains);
			for (PatientIdentifier pid :pids) {
				//if (domainsOfInterest.contains(pid.getAssigningAuthority())) {
					idsToBeUpdated.add(pid);
				//}				
			}
			if (idsToBeUpdated.size() <= 0) 
				continue;
			
			MessageStore store = null;
			try{
				Message updateMessage = createHL7UpdateNotificationMessage(idsToBeUpdated, new Date(), connection);
				store = MessageStoreHelper.initMessageStore(updateMessage, actor.getStoreLogger(), false);
				//Populate message store header
				HL7Header header = new HL7Header(updateMessage);
				
				header.populateMessageStore(store);
				
				
				
	            log.warn("Sending PIX Update Notification to "+ connection.getDescription());	
	            
				HL7Channel channel = new HL7Channel(connection);
				Message reply = channel.sendMessage(updateMessage);

				if (store != null) {  
					store.setInMessage(HL7Util.encodeMessage(reply));			
				}		
				
				if(reply!=null)
				{
					boolean ok = processPixUpdateNotificationResponse(reply, connection);

					if (ok) auditLog(header, idsToBeUpdated, connection);
				}
			    
			} catch(Exception e) {
				//Do not re-throw exception to let notification message continue to send to other PIX Consumers.
				String errorMsg = "Cannot send patient update notification to PIX Consumer: " + connection.getDescription();
				errorMsg += " Error Message:"+e.getMessage();
				log.error(errorMsg);
				if (store != null) {
					store.setErrorMessage( errorMsg );
				}							
			} finally {
				//Persist the message
				if (actor.getStoreLogger() != null && store != null) {  
					actor.getStoreLogger().saveLog(store);			
				}						
			}
		}
	}

	/**
	 * Audits this PIX Update Notification message request.
	 * 
	 * @param pids the list of patient identifiers to notify the subscribed PIX Consumers.
	 * @param connection the connection description
	 */
	private void auditLog(HL7Header header, List<PatientIdentifier> pids, IConnectionDescription connection) {
		if (actor.getAuditTrail() == null) return;
		
		try {
			ActiveParticipant destination = new ActiveParticipant();
			Identifier receivingApplication = Configuration.getIdentifier(connection, "ReceivingApplication", true);
			Identifier receivingFacility = Configuration.getIdentifier(connection, "ReceivingFacility", true);
			destination.setUserId(receivingFacility.getAuthorityNameString()+"|"+receivingApplication.getAuthorityNameString());
			destination.setAccessPointId(Configuration.getPropertyValue(connection, "hostname", true));
			ParticipantObject patientObject = new ParticipantObject();
			patientObject.setId(pids);
			patientObject.addDetail(new TypeValuePair("MSH-10", header.getMessageControlId()));
			actor.getAuditTrail().logPixUpdateNotification(destination, patientObject);		
		} catch(Exception e) {
			log.error("Fail to audit log Pix Update Notification: " +e.getMessage());
		}
		
	}

    /**
     * Creates an HL7 Patient Update Notification message.
     * 
  	 * @param patient the patient demographics to be sent
  	 * @param eventTime the time this patient event took place: typically now
  	 * @param authority the assigning Authority
  	 * @return a HL7 ADT^A31 Update Notification message to send  
  	 * @throws IheConfigurationException When this connection is not properly configured to encode messages
  	 * @throws PixManagerException When required patient information is missing
  	 * @throws HL7Exception When the patient information cannot be encoded properly into HL7
     */
  	private Message createHL7UpdateNotificationMessage(List<PatientIdentifier> pids, Date eventTime,
               IConnectionDescription connection)throws IheConfigurationException, PixManagerException, HL7Exception {
		ADT_A05 message = new ADT_A05();
		// Populate the MSH segment
		HL7v25.populateMSH(message.getMSH(), "ADT", "A31","ADT_A05", BaseHandler.getMessageControlId(), connection);
		// Populate the EVN segment
		populateEVN(message.getEVN(), "A31", eventTime);
		// Populate the PID segment
		populatePID(message.getPID(), pids);
		// Populate the PV1 segment
		message.getPV1().getPatientClass().setValue("N");
		return message;
  	}

  	
      /**
	   * Checks the response to the patient identity feed to ensure that it was
	   * a success.
	   * 
	   * @param response the response from the patient identity consumer
	   * @return <code>true</code> if the PIX Update Notification message was accepted
	   */
	   private boolean processPixUpdateNotificationResponse(Message response, IConnectionDescription connection) throws PixManagerException {
			// Make sure the response is the right type of message
			ACK message = null;
			if (response instanceof ACK) {
				message = (ACK) response;
			} else {
				actor.logHL7MessageError(log, message, "Unexpected response from Patient Identity Consumer");
				throw new PixManagerException("Unexpected response from Patient Identity consumer \"" + connection.getDescription());
			}
			// Check the MSA segment ...
			String status = message.getMSA().getAcknowledgmentCode().getValue();
			if ((status == null) || (!status.equalsIgnoreCase("AA") && !status.equalsIgnoreCase("CA"))) {
				// The server has rejected our request, or generated an error
				String mtext = message.getMSA().getTextMessage().getValue();
				String code = message.getMSA().getErrorCondition().getIdentifier().getValue();
				String etext = message.getMSA().getErrorCondition().getText().getValue();
				String error = null;
				if (code != null) error = "(" + code + ") " + HL7v231.getErrorString(code);
				if (mtext != null) error = error + " - " + mtext;
				if (etext != null) error = " [" + etext + "]";
	            if (error == null) {
	            	message.getERR();
	            	ca.uhn.hl7v2.model.v25.segment.ERR err =message.getERR();
                    if (err != null) {
	                  // Message = err.getMessage().get;
	                   try {
	                       ELD eld = err.getErrorCodeAndLocation(0);
	                       if (eld != null) {
	                           CE ce = eld.getCodeIdentifyingError();
	                           if (ce != null) {
	                               ST errorcode = ce.getIdentifier();
	                               if (errorcode != null) {
	                                   error = "(" + errorcode.getValue() + ") " + HL7v231.getErrorString(errorcode.getValue());
	                               }
	                               ST text = ce.getText();
	                               if (text != null) error = error + "-" + text.getValue();
	                           }
	                       }
	                   } catch (Exception e) { //do nothing if we cannot get anything from ERR.
	                   }
	               }
	            }
	            if (error == null) error ="Unspecified error";
	            
				error = "Error response from Patient Identity Consumer \"" + connection.getDescription() + "\": " + error;
				actor.logHL7MessageError(log, message, error);
				throw new PixManagerException(error);
			}
			// Okay, we're good
			return true;
		}
  	
  	
  	/**
	 * Populates the EVN segment of the Patient Update Notification.
	 * @param evn the EVN segment of the message
	 * @param event the message event type: "A31", etc.
	 * @param eventTime the time this patient event took place: typically now
	 * @throws DataTypeException When the EVN values cannot be properly encoded into HL7
	 */
	private void populateEVN(EVN evn, String event, Date eventTime) throws DataTypeException, IheConfigurationException {
        // EVN-1
        evn.getEventTypeCode().setValue(event);
        // EVN-2
        String recordedTimeString = DateUtil.formatDateTime(new Date(), null);
        evn.getRecordedDateTime().getTime().setValue(recordedTimeString);
        // EVN-6, event time, if known
        if (eventTime != null) {
            String eventTimeString = DateUtil.formatDateTime(eventTime, null);
            evn.getEventOccurred().getTime().setValue(eventTimeString);
        }
    }
	
	/**
	 * Populates the PID segment of the Patient update notification.
	 * @param pid the PID segment to be populated
	 * @param pids the list of patient identifiers
	 * @throws IheConfigurationException When this connection is not properly configured to encode messages
	 * @throws PixManagerException When required patient information is missing
	 * @throws HL7Exception When the patient information cannot be encoded properly into HL7
	 */
	private void populatePID(PID pid, List<PatientIdentifier> pids) 
	throws IheConfigurationException, PixManagerException, HL7Exception {
		
		// PID-3 - Preferred ID 
		for (int i=0; i<pids.size(); i++) {
			PatientIdentifier id = pids.get(i);
			populateCX(pid.getPatientIdentifierList(i), id);
		}
		// PID-5 - Patient legal name, 
		// Return a single space charter in PID-5, see ITI-vol2 Section 3.10.4.1.2.3
		XPN xpn = pid.getPatientName(0);
		xpn.getFamilyName().getSurname().setValue(" ");
	}
	
	/**
	 * Populates a CX component with an ID and assigning authority.
	 * 
	 * @param cx the CX component to populate
	 * @param pid the patient identifier 	 
	 * @throws DataTypeException When the component cannot be encoded in HL7
	 * @throws IheConfigurationException When this connection is not properly configured to translate Patient Feed messages
	 */
	private void populateCX(CX cx, PatientIdentifier pid) throws DataTypeException, IheConfigurationException {
		// PID 3.1 -- The id
		cx.getIDNumber().setValue(pid.getId());
		// PID 3.4 -- The assigning authority		
		Identifier aa = pid.getAssigningAuthority();
		if (aa != null){
			if (aa.getNamespaceId() != null)
				cx.getAssigningAuthority().getNamespaceID().setValue(aa.getNamespaceId());
			if (aa.getUniversalId() != null)
				cx.getAssigningAuthority().getUniversalID().setValue(aa.getUniversalId());
			if (aa.getUniversalIdType() != null)
				cx.getAssigningAuthority().getUniversalIDType().setValue(aa.getUniversalIdType());
		}

		cx.getIdentifierTypeCode().setValue("PI");
	}


}
