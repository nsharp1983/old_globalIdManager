package com.ats.apixpdq.impl.v2;

import java.util.List;

import org.apache.log4j.Logger;

import com.ats.aexchange.audit.ActiveParticipant;
import com.ats.aexchange.audit.AuditCodeMappings;
import com.ats.aexchange.audit.ParticipantObject;
import com.ats.aexchange.audit.TypeValuePair;
import com.ats.aexchange.audit.AuditCodeMappings.EventActionCode;
import com.ats.aexchange.config.PropertyFacade;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;

import com.ats.apixpdq.api.IPixManagerAdapter;
import com.ats.apixpdq.api.IPixUpdateNotificationRequest;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.AssigningAuthorityUtil;
import com.ats.apixpdq.common.BaseHandler;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.PixPdqException;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.common.PixUpdateNotifier;
import com.ats.apixpdq.impl.v2.hl7.HL7Header;
import com.ats.apixpdq.impl.v2.hl7.HL7v25;
import com.ats.apixpdq.impl.v2.hl7.HL7v25ToBaseConvertor;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.model.v25.message.ADT_A05;
import ca.uhn.hl7v2.model.v25.segment.PID;

/**
 * PixFeedHandlerV25 is a message handler similar to 
 * {@link PixFeedHandler}. However, it processes
 * HL7v2.5 PIX Feed message instead of HL7v2.3.1.
 *  
 * @author Wenzhi Li
 * @version 1.0 Jan 22, 2009
 * @see PixFeedHandler
 */
class PixFeedHandlerV25 extends BaseHandler {
	
	private PixManager actor = null;

	private IPixManagerAdapter pixAdapter = null;
	
	private static Logger log = Logger.getLogger(PixFeedHandlerV25.class);

	/**
	 * Constructor
	 * 
	 * @param actor the {@link PixManager} actor
	 */
	PixFeedHandlerV25(PixManager actor) {
		super();
		this.actor = actor;
		this.pixAdapter = PixPdqFactory.getPixManagerAdapter(); 
		assert this.pixAdapter != null;
	}

	/**
	 * Processes PIX Feed Create Patient message in HL7v2.5.
	 * 
	 * @param msgIn the PIX Feed request message
	 * @return a response message for PIX Feed
	 * @throws ApplicationException If Application has trouble
	 * @throws HL7Exception if something is wrong with HL7 message 
	 * @throws com.ats.aempi.ApplicationException 
	 */
	Message processCreate(Message msgIn) 
	throws PixPdqException, HL7Exception, com.ats.aempi.ApplicationException {
        //ADT^A04 message is processed by ADT^A01
		assert msgIn instanceof ADT_A01 || 
			   msgIn instanceof ADT_A05;
	
		HL7Header hl7Header = new HL7Header(msgIn);
		
		log.fatal("\n" + "收到HL7消息:" + "\n" + msgIn);
		//Create Acknowledgment and its Header
		ACK reply = initAcknowledgment(hl7Header);

		//Validate incoming message first
		PID pid = (PID)msgIn.get("PID");
		PatientIdentifier patientId = getPatientIdentifiers(pid);				
		boolean isValidMessage = validateMessage(reply, hl7Header, patientId, true);
		if (!isValidMessage) return reply;
		
		//Invoke eMPI function
		MessageHeader header = hl7Header.toMessageHeader();
		Patient patient = getPatient(msgIn);
		try {
			List<PatientIdentifier> matching = pixAdapter.createPatient(patient, header);
			
			//Send PIX Update Notification
			if (matching != null && matching.size() > 0) {
				IPixUpdateNotificationRequest request = 
					new PixUpdateNotificationRequest(actor, matching);
				PixUpdateNotifier.getInstance().accept(request);
			}			
		} catch (PixManagerException e) {
			throw new PixPdqException(e);
		} 

		HL7v25.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());

		//Finally, Audit Log PIX Feed Success 
	    auditLog(hl7Header, patient, AuditCodeMappings.EventActionCode.Create);

	    log.warn("\n" + "返回HL7消息：" + "\n" +reply);
	    
	    //System.out.println("HL7-2.5-返回字段:" + reply);
		return reply; 
	}

	/**
	 * Audit Logging of PIX Feed message.
	 * 
	 * @param hl7Header the header message from the source application
	 * @param patient the patient to create, update or merged
	 * @param eventActionCode the {@link EventActionCode}
	 */
	private void auditLog(HL7Header hl7Header, Patient patient, AuditCodeMappings.EventActionCode eventActionCode) {
		if (actor.getAuditTrail() == null)
			return;
		
		String userId = hl7Header.getSendingFacility().getNamespaceId() + "|" +
						hl7Header.getSendingApplication().getNamespaceId();
		String messageId = hl7Header.getMessageControlId();
		//TODO: Get the ip address of the source application
		String sourceIp = "127.0.0.1";
		ActiveParticipant source = new ActiveParticipant(userId, messageId, sourceIp);
		
		ParticipantObject patientObj = new ParticipantObject(patient);
		patientObj.addDetail(new TypeValuePair("MSH-10", hl7Header.getMessageControlId()));
		
		actor.getAuditTrail().logPixFeed(source, patientObj, eventActionCode);		
	}
	
	/**
	 * Initiates an acknowledgment instance for the incoming message.
	 * 
	 * @param hl7Header the message header of the incoming message
	 * @return an {@link ACK} instance
	 * @throws HL7Exception if something is wrong with HL7 message 
	 * @throws PixPdqException If Application has trouble
	 */
	private ACK initAcknowledgment(HL7Header hl7Header) 
	throws HL7Exception, PixPdqException {
		//Send Response
		ACK reply = new ACK();
		
		//For the response message, the ReceivingApplication and ReceivingFacility 
		//will become the sendingApplication and sendingFacility;
		//Also the sendingApplication and sendingFacility will become the 
		//receivingApplication and receivingFacility.
		Identifier serverApplication = getServerApplication(actor.getConnection());
	    Identifier serverFacility = getServerFacility(actor.getConnection());
		Identifier sendingApplication = hl7Header.getSendingApplication();
		Identifier sendingFacility = hl7Header.getSendingFacility();

		String event = hl7Header.getTriggerEvent();
		HL7v25.populateMSH(reply.getMSH(), "ACK", event, null, getMessageControlId(), 
 			serverApplication, serverFacility, sendingApplication, sendingFacility);
		
		
		return reply;
	}
	
	/**
	 * Validates a patient identifier domain, namely, assigning authority.
	 * 
	 * @param reply the reply message to be populated if the validation fails
	 * @param patientId the patient id
	 * @param incomingMessageId the incoming message id
	 * @return <code>true</code> if the patient domain is validated successfully;
	 *         otherwise <code>false</code>.
	 * @throws HL7Exception if something is wrong with HL7 message 
	 */
	private boolean validateDomain(ACK reply, PatientIdentifier patientId, String incomingMessageId) 
	throws HL7Exception {
		Identifier domain = patientId.getAssigningAuthority();
		boolean domainOk = AssigningAuthorityUtil.validateDomain(
				domain, actor.getActorDescription(), pixAdapter);
		if (!domainOk) {
			HL7v25.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			//segmentId=PID, sequence=1, fieldPosition=3, fieldRepetition=1,componentNubmer=4
			HL7v25.populateERR(reply.getERR(), "PID", "1", "3", "1", "4",
					"204", "Unknown Key Identifier");
			return false;
		}
		return true;
	}
	
	
	/**
     * Validates the receiving facility and receiving application of an incoming message.
	 * 
     * @param reply the reply message to be populated if any validation is failed
	 * @param receivingApplication the receiving application of the incoming message
	 * @param receivingFacility the receiving facility of the incoming message
	 * @param expectedApplication the expected receiving application
	 * @param expectedFacility the expected receiving facility
	 * @param incomingMessageId the incoming message
	 * @return <code>true</code> if validation is passed;
	 *         otherwise <code>false</code>.
	 * @throws HL7Exception if something is wrong with HL7 message 
	 */
	private boolean validateReceivingFacilityApplication(ACK reply, Identifier receivingApplication,
			Identifier receivingFacility, Identifier expectedApplication, Identifier expectedFacility,
		    String incomingMessageId) 
		    throws HL7Exception
	{
		//Validate ReceivingApplication and ReceivingFacility.
		//Currently we are not validating SendingApplication and SendingFacility
		boolean validateApplication = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_APPLICATION);
		if (validateApplication && !receivingApplication.equals(expectedApplication)) {
			HL7v25.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			//segmentId=MSH, sequence=1, fieldPosition=5, fieldRepetition=1, componentNubmer=1
			HL7v25.populateERR(reply.getERR(), "MSH", "1", "5", "1", "1",
					null, "Unknown Receiving Application");
			return false;
		}

		boolean validateFacility = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_FACILITY);
		if (validateFacility && !receivingFacility.equals(expectedFacility)) {
			HL7v25.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			//segmentId=MSH, sequence=1, fieldPosition=6, fieldRepetition=1, componentNubmer=1
			HL7v25.populateERR(reply.getERR(), "MSH", "1", "6", "1", "1",
					null, "Unknown Receiving Facility");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Validates the incoming Message in this order:
	 * 
	 * <ul>
	 * <li> Validate Receiving Facility and Receiving Application</li>
	 * <li> Validate Domain </li>
	 * <li> Validate patient Id <li>
	 * </ul>
	 * 
     * @param reply the reply message to be populated if any validation is failed
	 * @param hl7Header the message header of the incoming message
	 * @param patientId the id of the patient to be validated
	 * @param isPixCreate Whether this validation is for pix patient creation
	 * @return <code>true</code> if the message is correct; <code>false</code>otherwise.
	 * @throws HL7Exception if something is wrong with HL7 message 
	 * @throws PixPdqException if something is wrong with the application
	 */
	private boolean validateMessage(ACK reply, HL7Header hl7Header, PatientIdentifier patientId, boolean isPixCreate) 
	throws HL7Exception, PixPdqException {
		
		Identifier serverApplication = getServerApplication(actor.getConnection());
		Identifier serverFacility = getServerFacility(actor.getConnection());
		Identifier receivingApplication = hl7Header.getReceivingApplication();
		Identifier receivingFacility = hl7Header.getReceivingFacility();

		String incomingMessageId = hl7Header.getMessageControlId();
		//1. validate receiving facility and receiving application
		boolean isValidFacilityApplication = validateReceivingFacilityApplication(reply, 
				receivingApplication, receivingFacility, 
				serverApplication, serverFacility, incomingMessageId );
		if (!isValidFacilityApplication) return false;		
		
		//2.validate the domain
		boolean isValidDomain = validateDomain(reply, patientId, incomingMessageId);
		if (!isValidDomain) return false;
		
		//3. validate ID itself 
		if (!isPixCreate) { 
			//Do not valid patient id for PIX patient creation
			boolean isValidPid = validatePatientId(reply, patientId, hl7Header.toMessageHeader(), incomingMessageId);
			if (!isValidPid) return false;
		}
		
		//Finally, it must be true when it reaches here
		return true;
	}
	
	/**
	 * Gets the patient identifier from a Patient PID segment.
	 * 
	 * @param pid the PID segment
	 * @return a {@link PatientIdentifier}
	 */
	private PatientIdentifier getPatientIdentifiers(PID pid) {
		PatientIdentifier identifier = new PatientIdentifier();
		CX[] cxs = pid.getPatientIdentifierList();
		for (CX cx : cxs) {
			Identifier assignAuth = new Identifier(cx.getAssigningAuthority()
					.getNamespaceID().getValue(), cx.getAssigningAuthority()
					.getUniversalID().getValue(), cx.getAssigningAuthority()
					.getUniversalIDType().getValue());
			Identifier assignFac = new Identifier(cx.getAssigningFacility()
					.getNamespaceID().getValue(), cx.getAssigningFacility()
					.getUniversalID().getValue(), cx.getAssigningFacility()
					.getUniversalIDType().getValue());
			identifier.setAssigningAuthority(assignAuth);
			identifier.setAssigningFacility(assignFac);
			identifier.setId(cx.getIDNumber().getValue());
			identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode()
					.getValue());
		}
		return identifier;
	}
	
	/**
	 * Checks the given whether the given patient id is a valid patient id.
	 * 
     * @param reply the reply message to be populated if any validation is failed
	 * @param patientId the patient id to be checked
	 * @param header the incoming message header 
	 * @param incomingMessageId the incoming message id.
	 * @return <code>true</code> if the patientId is valid; otherwise <code>false</code>.
	 * @throws HL7Exception if something is wrong with HL7 message 
	 * @throws PixPdqException if something is wrong with the application
	 */
	private boolean validatePatientId(ACK reply, PatientIdentifier patientId, MessageHeader header, String incomingMessageId)
	throws HL7Exception, PixPdqException{
		boolean validPatient;
		try {
			validPatient = pixAdapter.isValidPatient(patientId, header);
		} catch (PixManagerException e) {
			throw new PixPdqException(e);
		}
		if (!validPatient) {
			HL7v25.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			//segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=1, componentNubmer=1
			HL7v25.populateERR(reply.getERR(), "PID", "1", "3", "1", "1",
					"204", "Unknown Key Identifier");
		}
		return validPatient;
	}
	
	/**
	 * Converts a PIX Feed Patient message to a {@link Patient} object.
	 * 
	 * @param msgIn the incoming PIX Feed message
	 * @return a {@link Patient} object
	 * @throws PixPdqException if something is wrong with the application
	 */
	private Patient getPatient(Message msgIn) throws PixPdqException,HL7Exception {
		HL7v25ToBaseConvertor convertor = null;
		if (msgIn.getVersion().equals("2.5")) 
		{
			
			//System.out.println(actor.getActorDescription());
			
			convertor = new HL7v25ToBaseConvertor(msgIn, actor.getActorDescription(), pixAdapter);
		} else {
			throw new PixPdqException("Unexpected HL7 version");
		}

		//填充PID病人基本信息字段.包含
		//PID注释:
		   //PID-1:Set ID
		   //PID-2:Patient Identifier LIST.ID NUMBER
		   //PID-3:Patient Identifier List.Id NUMBER
		   //PID-3.4 Patient Identifier List.Assigning Authority.Namespace ID
		   //PID-3.4.2 Patient Identifier List.Assigning Authority.Universal ID
		   //PID-3.4.3 Patient Identifier List.Assigning.Universal ID Type
		   //PID-3.5 Patient Identifier List.Identifier Type Code
		   //PID-4 Alternate Patient ID
		   //PID-5 Patient Name Family Name
		   //PID-5.2 Patient Name Given Name
		   //PID-5.6 Patient Name Degree
		   //PID-5.7 Patient Name Type Code
		   //PID-6 Mother's Maiden Name
		   //PID-7 Date/Time Of Birth
		   //PID-8 Administrative Sex
		   //PID-9 Patient Alis Family Name.Surname
		   //PID-10 Race Identifier
		   //PID-11 Patient Address.Street Address.Street or Mailing Address
		   //PID-11.2 Patient Address.Other Designation
		   //PID-11.3 Patient Address.City
		   //PID-11.4 Patient Address.State or Province
		   //PID-11.5 Patient Address.Zip or Postal Code
		   //PID-12 Country Code
		   //PID-13 Phone Number.Home Telephone Number
		   //PID-14 Phone Number.Business.Telephone Number
		   //PID-15 Primary Language.Identifier
		   //PID-16 Marital Status.Identifier
		   //PID-17 Religion Identifier
		   //PID-18 Patient Account Number.ID Number
		   //PID-19 SSN NUMBER    											社会保险号(自定)
		   //PID-20 Driver's License Number-Patient.License Number          身份证件号(自定)
		   //PID-21 Mother's Identifier.ID Number                           医疗保险号(自定)
		   //PID-22 Ethnic Group.Identifier
		   //PID-23 Birth Place
		   //PID-28 Nationality.Identifier
		   //PID-32 Identity Reliability Code 
		Patient patientDesc = new Patient();

		patientDesc.setPatientIds(convertor.getPatientIds());//病人id
		
		patientDesc.setPatientName(convertor.getPatientName());
		
		//patientDesc.setSickName(convertor.getPatientName());
		
		patientDesc.setMothersMaidenName(convertor.getMotherMaidenName());
		
		patientDesc.setBirthDateTime(convertor.getBirthDate());
		
		patientDesc.setAdministrativeSex(convertor.getSexType());
		
		patientDesc.setPatientAliases(convertor.getPatientAliases());
		
		//patientDesc.setRace(convertor.getRace());
		
		patientDesc.setPrimaryLanguage(convertor.getPrimaryLanguage());
		
		patientDesc.setMaritalStatus(convertor.getMaritalStatus());
		
		patientDesc.setReligion(convertor.getReligion());
		
		patientDesc.setPatientAccountNumber(convertor.getpatientAccountNumber());
		
		patientDesc.setSsn(convertor.getSsn());
			
		patientDesc.setIdentityNO(convertor.getDriversLicense().getLicenseNumber());
		
		patientDesc.setInsuranceNO(convertor.getMothersId().getId());
		
		patientDesc.setEthnicGroup(convertor.getEthnicGroup());
		
		patientDesc.setBirthPlace(convertor.getBirthPlace());
		
		patientDesc.setBirthOrder(convertor.getBirthOrder());
		
		patientDesc.setCitizenship(convertor.getCitizenShip());
		
		patientDesc.setDeathDate(convertor.getDeathDate());
		
		patientDesc.setDeathIndicator(convertor.getDeathIndicator());
		
		patientDesc.setHomePhone(convertor.getHomePhone());
		
		patientDesc.setBusinessPhone(convertor.getBusinessHome());
		
		patientDesc.setAddresses(convertor.getAddressList());
		
		patientDesc.setVisits(convertor.getVisitList());
		
		patientDesc.setVipIndicator(convertor.getVipIndicator());
		
        patientDesc.setNextOfKin(convertor.getNextOfKin());
        
        //PV1注释:
        //PV1-2:就诊类型
        //PV1-3:患者现位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
        //PV1-6:患者前位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
        //PV1-7:接诊医生
        //PV1-8:转诊医生
        //PV1-9:会诊医生
        //PV1-10:医院服务
        //PV1-11:患者临时位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
        //PV1-18:病人类型
        //PV1-44:入院日期
        //PV1-45:出院日期
        patientDesc.setPatCategory(convertor.getPV1_2());
        
        patientDesc.setPatCurrentPointOfCare(convertor.getPV1_3_1());
        
        patientDesc.setPatCurrentRoom(convertor.getPV1_3_2());
        
        patientDesc.setPatCurrentBed(convertor.getPV1_3_3());
        
        patientDesc.setPatCuurentDep(convertor.getPV1_3_4());
        
        patientDesc.setPatCurrentPositionStatus(convertor.getPV1_3_5());
        
        patientDesc.setPatCurrentPositionType(convertor.getPV1_3_6());
        
        patientDesc.setPatCurrentBuilding(convertor.getPV1_3_7());
        
        patientDesc.setPatCurrentFloor(convertor.getPV1_3_8());
        
        patientDesc.setPatCuurentDescription(convertor.getPV1_3_9());
        
        patientDesc.setPatAdmissionType(convertor.getPV1_4_1());
        
        patientDesc.setPatAdmissionNumber(convertor.getPV1_5_1());
        
        patientDesc.setPatFormerPointOfCare(convertor.getPV1_6_1());
        
        patientDesc.setPatFormerRoom(convertor.getPV1_6_2());
        
        patientDesc.setPatFormerBed(convertor.getPV1_6_3());
        
        patientDesc.setPatFormerDep(convertor.getPV1_6_4());
        
        patientDesc.setPatFormerPositionStatus(convertor.getPV1_6_5());
        
        patientDesc.setPatFormerPositionType(convertor.getPV1_6_6());
        
        patientDesc.setPatFormerBuilding(convertor.getPV1_6_7());
        
        patientDesc.setPatFormerFloor(convertor.getPV1_6_8());
        
        patientDesc.setPatFormerDescription(convertor.getPV1_6_9());
        
        patientDesc.setAdmissionsDoctor(convertor.getPV1_7_2());
        
        patientDesc.setAdmissionsDoctorId(convertor.getPV1_7_1());
        
        patientDesc.setReferringDoctorId(convertor.getPV1_8_1());
        
        patientDesc.setReferringDoctor(convertor.getPV1_8_2());
        
        patientDesc.setConsultationDoctorId(convertor.getPV1_9_1());
        
        patientDesc.setConsultationDoctor(convertor.getPV1_9_2());
        
        patientDesc.setHospitalService(convertor.getPV1_10());
        
        patientDesc.setPatTempPointOfCare(convertor.getPV1_11_1());
        
        patientDesc.setPatTempRoom(convertor.getPV1_11_2());
        
        patientDesc.setPatTempBed(convertor.getPV1_11_3());
        
        patientDesc.setPatTempDep(convertor.getPV1_11_4());
        
        patientDesc.setPatTempPositionStatus(convertor.getPV1_11_5());
        
        patientDesc.setPatTempPositionType(convertor.getPV1_11_6());
        
        patientDesc.setPatTempBuilding(convertor.getPV1_11_7());
        
        patientDesc.setPatTempFloor(convertor.getPV1_11_8());
        
        patientDesc.setPatTempDescription(convertor.getPV1_11_9());
        
        patientDesc.setPatAdmissionTest(convertor.getPV1_12_1());
        
        patientDesc.setPatReAdmission(convertor.getPV1_13_1());
        
        patientDesc.setPatAdmissionSource(convertor.getPV1_14_1());
        
        patientDesc.setPatAmbulatoryStatus(convertor.getPV1_15_1());
        
        patientDesc.setPatVip(convertor.getPV1_16_1());
        
        patientDesc.setPatAdmissionDoctors(convertor.getPV1_17_2());
        
        patientDesc.setPatAdmissionDoctorsId(convertor.getPV1_17_1());
        
        patientDesc.setPatientClass(convertor.getPV1_18());
        
        patientDesc.setPatientId(convertor.getPV1_19());
        
        patientDesc.setPatFinancialClass(convertor.getPV1_20());
        
        patientDesc.setRoomBedCostPrice(convertor.getPV1_21());
        
        patientDesc.setCourtesyCode(convertor.getPV1_22());
        
        patientDesc.setCreditRating(convertor.getPV1_23());
        
        patientDesc.setContractCode(convertor.getPV1_24());
        
        patientDesc.setContractCreateDate(convertor.getPV1_25());
        
        patientDesc.setContractPrice(convertor.getPV1_26());
        
        patientDesc.setContractTime(convertor.getPV1_27());
        
        patientDesc.setInterestRateCode(convertor.getpv1_28());
        
        patientDesc.setBadDebts(convertor.getPV1_29());
        
        patientDesc.setBadDebtsCreateDate(convertor.getPV1_30());
        
        patientDesc.setBadDebtsCode(convertor.getPV1_31());
        
        patientDesc.setBadDebtsPrice(convertor.getPV1_32());
        
        patientDesc.setBadDebtsRestorePrice(convertor.getPV1_33());
        
        patientDesc.setPatAccountVoided(convertor.getPV1_34());
        
        patientDesc.setPatAccountVoidedDate(convertor.getPV1_35());
        
        patientDesc.setPatDischargeDisposition(convertor.getPV1_36());
        
        patientDesc.setPatDischargeLocation(convertor.getPV1_37());
        
        patientDesc.setPatDietType(convertor.getPV1_38());
        
        patientDesc.setPatServiceAgencies(convertor.getPV1_39());
        
        patientDesc.setPatBedStatus(convertor.getPV1_40());
        
        patientDesc.setPatAccountStatus(convertor.getPV1_41());
        
        patientDesc.setPatDeterPointOfCare(convertor.getPV1_42_1());
        
        patientDesc.setPatDeterRoom(convertor.getPV1_42_2());
        
        patientDesc.setPatDeterBed(convertor.getPV1_42_3());
        
        patientDesc.setPatDeterDep(convertor.getPV1_42_4());
        
        patientDesc.setPatDeterPositionStatus(convertor.getPV1_42_5());
        
        patientDesc.setPatDeterPositionType(convertor.getPV1_42_6());
        
        patientDesc.setPatDeterBuilding(convertor.getPV1_42_7());
        
        patientDesc.setPatDeterFloor(convertor.getPV1_42_8());
        
        patientDesc.setPatDeterDescription(convertor.getPV1_42_9());
        
        patientDesc.setPatForTempPointOfCare(convertor.getPV1_43_1());
        
        patientDesc.setPatForTempRoom(convertor.getPV1_43_2());
        
        patientDesc.setPatForTempBed(convertor.getPV1_43_3());
        
        patientDesc.setPatForTempDep(convertor.getPV1_43_4());
        
        patientDesc.setPatForTempPositionStatus(convertor.getPV1_43_5());
        
        patientDesc.setPatForTempPositionType(convertor.getPV1_43_6());
        
        patientDesc.setPatForTempBuilding(convertor.getPV1_43_7());
        
        patientDesc.setPatForTempFloor(convertor.getPV1_43_8());
        
        patientDesc.setPatForTempDescription(convertor.getPV1_43_9());
        
        //patientDesc.setAdmitDate(convertor.getPV1_44());
        
       // patientDesc.setDischargeDate(convertor.getPV1_45());
        
        patientDesc.setPatDifference(convertor.getPV1_46());
        
        patientDesc.setPatTotalCost(convertor.getPV1_47());
        
        patientDesc.setPatTotalDispatch(convertor.getPV1_48());
        
        patientDesc.setPatTotalAmountPayable(convertor.getPV1_49());
        
        patientDesc.setPatSpareId(convertor.getPV1_50());
        
        patientDesc.setPatVisitLogo(convertor.getPV1_51());
        
        patientDesc.setOtherMedicalInstitutions(convertor.getPV1_52());
        
		return patientDesc;
	}
	
}
