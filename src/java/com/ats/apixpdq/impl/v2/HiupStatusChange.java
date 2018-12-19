package com.ats.apixpdq.impl.v2;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ats.aempi.model.ExtendForPerson;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.config.PropertyFacade;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.utils.ExceptionUtil;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.datatype.CX;
import ca.uhn.hl7v2.model.v231.group.ADT_A39_PIDPD1MRGPV1;
import ca.uhn.hl7v2.model.v231.message.ACK;
import ca.uhn.hl7v2.model.v231.message.ADT_A02;
import ca.uhn.hl7v2.model.v231.message.ADT_A03;
import ca.uhn.hl7v2.model.v231.message.ADT_A06;
import ca.uhn.hl7v2.model.v231.message.ADT_A07;
import ca.uhn.hl7v2.model.v231.message.ADT_A09;
import ca.uhn.hl7v2.model.v231.message.ADT_A10;
import ca.uhn.hl7v2.model.v231.message.ADT_A11;
import ca.uhn.hl7v2.model.v231.message.ADT_A12;
import ca.uhn.hl7v2.model.v231.message.ADT_A13;
import ca.uhn.hl7v2.model.v231.message.ADT_A21;
import ca.uhn.hl7v2.model.v231.message.ADT_A22;
import ca.uhn.hl7v2.model.v231.message.ADT_A29;
import ca.uhn.hl7v2.model.v231.message.ADT_A32;
import ca.uhn.hl7v2.model.v231.message.ADT_A33;
import ca.uhn.hl7v2.model.v231.message.ADT_A39;
import ca.uhn.hl7v2.model.v231.message.ADT_A47;
import ca.uhn.hl7v2.model.v231.message.ADT_A50;
import ca.uhn.hl7v2.model.v231.segment.MRG;
import ca.uhn.hl7v2.model.v231.segment.PID;
import ca.uhn.hl7v2.model.v25.message.ADT_A52;

import com.ats.apixpdq.api.IPixManagerAdapter;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.AssigningAuthorityUtil;
import com.ats.apixpdq.common.BaseHandler;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.PixPdqException;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.impl.v2.hl7.HL7Header;
import com.ats.apixpdq.impl.v2.hl7.HL7v231;
import com.ats.apixpdq.impl.v2.hl7.HL7v231ToBaseConvertor;



class HiupStatusChange extends BaseHandler implements Application {

    private static Logger log = Logger.getLogger(HiupStatusChange.class);
	private HiupStatusManager actor = null;
	
	private IPixManagerAdapter pixAdapter = null;

	private Connection hl7Connection = null;
	//private IJMXEventNotifier eventBean = null;

	
	HiupStatusChange(HiupStatusManager actor) {
		super();
		this.actor = actor;
		this.pixAdapter = PixPdqFactory.getPixManagerAdapter(); 
		assert this.pixAdapter != null;
	}

    /**
     * Whether a incoming message can be processed by this handler.
     * 
     * @return <code>true</code> if the incoming message can be processed;
     * otherwise <code>false</code>.
     */
	public boolean canProcess(Message theIn) {
		if (theIn instanceof ADT_A02 || theIn instanceof ADT_A03 || 
			theIn instanceof ADT_A06 || theIn instanceof ADT_A07 ||
			theIn instanceof ADT_A09 || theIn instanceof ADT_A10 ||
		    theIn instanceof ADT_A11 || theIn instanceof ADT_A12 ||
		    theIn instanceof ADT_A13 || theIn instanceof ADT_A21 ||
		    theIn instanceof ADT_A22 || theIn instanceof ADT_A29 ||
		    theIn instanceof ADT_A32 || theIn instanceof ADT_A33 ||
		    theIn instanceof ADT_A47 || theIn instanceof ADT_A52)
			return true;
		else
			return false;
	}
	 
    /**
     * Processes the incoming PIX Feed Message. Valid messages 
     * are ADT^A02, ADT^A03, ADT^A06, ADT^A07, ADT^A09,ADT^A10,ADT^A11,ADT^A12,ADT^A13,ADT^A21,ADT^A22,ADT^A29,ADT^A32,ADT^A33,ADT^A47,ADT^A52.
     * 
     * @param msgIn the incoming message
     * @throws IOException 
     * @throws IOException 
     */
	public Message processMessage(Message msgIn) throws ApplicationException,HL7Exception 
	{		
		Message retMessage = null;

		try {
			
			log.fatal("\n" + "收到HL7消息:" + "\n" + msgIn);

			if (msgIn instanceof ADT_A02 || //患者转移，可以使转科，转床，转病区
					   msgIn instanceof ADT_A03 || //出院/终止就诊
					   msgIn instanceof ADT_A06 || //门诊转住院
					   msgIn instanceof ADT_A07 || //住院转门诊
					   msgIn instanceof ADT_A09 || //患者暂离追踪
					   msgIn instanceof ADT_A10 || //患者到达追踪
					   msgIn instanceof ADT_A12 || //取消转移
					   msgIn instanceof ADT_A13 || //取消出院/终止就诊
					   msgIn instanceof ADT_A21 || //患者请假
					   msgIn instanceof ADT_A22 || //患者请假后返回
					   msgIn instanceof ADT_A32 || //取消患者到达追踪
					   msgIn instanceof ADT_A33 || //取消患者暂离追踪
					   msgIn instanceof ADT_A52){  //取消患者请假
				retMessage = processStatusChange(msgIn);
			} else if (msgIn instanceof ADT_A29 || //删除患者记录
					   msgIn instanceof ADT_A11){  //取消入院   
				retMessage = processDelete(msgIn);
			} else if (msgIn instanceof ADT_A47 || //改变患者标识符
					   msgIn instanceof ADT_A50){  //改变患者就诊号
				retMessage = processChangeID(msgIn);
			} else {
				String errorMsg = "Unexpected request to PIX Manager server. " 
					+ "Valid message types are ADT^A02,ADT^A03,ADT^A06,ADT^A07,ADT^A09,ADT^A10,ADT^A11,ADT^A12,ADT^A13,ADT^A21,ADT^A22,ADT^A32,ADT^A33,ADT^A52 and ADT^A53";
				throw new ApplicationException(errorMsg);
			}	
			//推送
			String enabletrans = PropertyFacade.getString(PixPdqConstants.ENABLE_TRANS);
			
			//System.out.println(retMessage.toString().indexOf("AA"));
			
			if (enabletrans.equalsIgnoreCase("true") && retMessage.toString().indexOf("AA")>0)
			{
				processTrans(msgIn);
			}
			
		} catch (PixPdqException e) {
			throw new ApplicationException(ExceptionUtil.strip(e.getMessage()), e);		
		} catch (HL7Exception e) {
			throw new HL7Exception(ExceptionUtil.strip(e.getMessage()), e);
		} finally {

		}
		return retMessage;
	}
	
	
	private Message processChangeID(Message msgIn) throws PixPdqException, HL7Exception 
	{

		assert msgIn instanceof ADT_A47 ||
	           msgIn instanceof ADT_A50;
		
		HL7Header hl7Header = new HL7Header(msgIn);

		ACK reply = initAcknowledgment(hl7Header);
		

		PID TempPid = (PID) msgIn.get("PID");
		
		MRG TempMrg = (MRG) msgIn.get("MRG");
		
		PatientIdentifier patientId = getPatientIdentifiers(TempPid);
		
		PatientIdentifier mrgPatientId = getMrgPatientIdentifiers(TempMrg);
		
		boolean isValidMessage = validateMessage(reply, hl7Header, patientId, null, false);
		if (!isValidMessage) return reply;


		MessageHeader header = hl7Header.toMessageHeader();
		
		Patient patient = getPatient(msgIn);
		
		Patient mrgPatient = getMrgPatient(msgIn);
		
		try 
		{
            if (!actor.isNotification())
            {
                pixAdapter.changePatientID(patient, mrgPatient, header);
            }

		}
		catch (PixManagerException e)
		{
			throw new PixPdqException(e);
		}

		HL7v231.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());

	    log.fatal("\n" + "返回HL7消息：" + "\n" +reply);
	    
		return reply;
	}
	
	
	private Message processDelete(Message msgIn) throws PixPdqException,HL7Exception 
	{
		assert msgIn instanceof ADT_A29 ||
		       msgIn instanceof ADT_A11;
		
		HL7Header hl7Header = new HL7Header(msgIn);
		
		ACK reply = initAcknowledgment(hl7Header);

		PID pid = (PID)msgIn.get("PID");
		
		PatientIdentifier patientId = getPatientIdentifiers(pid);	
		
		boolean isValidMessage = validateMessage(reply, hl7Header, patientId, null, false);
		
		if (!isValidMessage) return reply;
		
		MessageHeader header = hl7Header.toMessageHeader();
		
		Patient patient = getPatient(msgIn);

        if (!actor.isNotification())
           {
               try 
               {
				pixAdapter.DeletePatient(patient, header);
               } 
               catch (PixManagerException e) 
               {
            	   throw new PixPdqException(e);
               }
            }			


		HL7v231.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());
 
	    log.fatal("\n" + "返回HL7消息：" + "\n" +reply);
	    
    	return reply;
	}
	
	private Message processStatusChange(Message msgIn) throws PixPdqException,HL7Exception 
	{
		assert msgIn instanceof ADT_A02 ||
		       msgIn instanceof ADT_A03 ||
		       msgIn instanceof ADT_A06 ||
		       msgIn instanceof ADT_A07 ||
		       msgIn instanceof ADT_A09 ||
		       msgIn instanceof ADT_A10 ||
		       msgIn instanceof ADT_A12 ||
		       msgIn instanceof ADT_A13 ||
		       msgIn instanceof ADT_A21 ||
		       msgIn instanceof ADT_A22 ||
		       msgIn instanceof ADT_A32 ||
		       msgIn instanceof ADT_A33 ||
		       msgIn instanceof ADT_A52;
		
		HL7Header hl7Header = new HL7Header(msgIn);
		
		ACK reply = initAcknowledgment(hl7Header);

		PID pid = (PID)msgIn.get("PID");
		
		PatientIdentifier patientId = getPatientIdentifiers(pid);	
		
		boolean isValidMessage = validateMessage(reply, hl7Header, patientId, null, true);
		
		if (!isValidMessage) 
		{
			log.fatal("\n" + "返回HL7消息：" + "\n" +reply);
			
			return reply;
		}
		
		MessageHeader header = hl7Header.toMessageHeader();
		
		Patient patient = getPatient(msgIn);

        if (!actor.isNotification())
           {
               try 
               {
				pixAdapter.ChangePatient(patient, header);
               } 
               catch (PixManagerException e) 
               {
            	   throw new PixPdqException(e);
               }
            }			


		HL7v231.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());
 
	    log.fatal("\n" + "返回HL7消息：" + "\n" +reply);
	    
    	return reply;
	}

	private void processTrans(Message msgIn) throws PixPdqException,HL7Exception, ApplicationException 
	{
	
		HL7Header hl7Header = new HL7Header(msgIn);
		
		//Invoke eMPI function
		MessageHeader header = hl7Header.toMessageHeader();
		
		Patient patient = getPatient(msgIn);

        try 
        {
			pixAdapter.TransPatient(patient, header,msgIn);
        } 
        catch (PixManagerException e) 
        {
			e.printStackTrace();
        }
       		
	}

	/**
	 * Initiates an acknowledgment instance for the incoming message.
	 * 
	 * @param hl7Header the message header of the incoming message
	 * @return an {@link ACK} instance
	 * @throws HL7Exception if something is wrong with HL7 message 
	 * @throws PixPdqException If Application has trouble
	 */
	private ACK initAcknowledgment(HL7Header hl7Header) throws HL7Exception, 
		PixPdqException {
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
		
		try {
			String event = hl7Header.getTriggerEvent();
			HL7v231.populateMSH(reply.getMSH(), "ACK", event, getMessageControlId(), 
				serverApplication, serverFacility, sendingApplication, sendingFacility);
		} catch (IheConfigurationException e) {
			throw new PixPdqException("Error populating MSH segment", e);
		}
		
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
			HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			//segmentId=PID, sequence=1, fieldPosition=3, fieldRepetition=1,componentNubmer=4
			HL7v231.populateERR(reply.getERR(), "PID", "1", "3", "1", "4",
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
	 * @throws PixPdqException if something is wrong with the application
	 */
	private boolean validateReceivingFacilityApplication(ACK reply, Identifier receivingApplication,
			Identifier receivingFacility, Identifier expectedApplication, Identifier expectedFacility,
			String incomingMessageId) 
		    throws HL7Exception, PixPdqException
	{
		//Validate ReceivingApplication and ReceivingFacility.
		//Currently we are not validating SendingApplication and SendingFacility
		boolean validateApplication = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_APPLICATION);
		if (validateApplication && !receivingApplication.equals(expectedApplication)) {
			HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			//segmentId=MSH, sequence=1, fieldPosition=5, fieldRepetition=1, componentNubmer=1
			HL7v231.populateERR(reply.getERR(), "MSH", "1", "5", "1", "1",
					null, "Unknown Receiving Application");
			return false;
		}
		
		boolean validateFacility = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_FACILITY);
		if (validateFacility && !receivingFacility.equals(expectedFacility)) {
			HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			//segmentId=MSH, sequence=1, fieldPosition=6, fieldRepetition=1, componentNubmer=1
			HL7v231.populateERR(reply.getERR(), "MSH", "1", "6", "1", "1",
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
	 * <li> Validate merge patient Id if applicable<li> 
	 * </ul>
	 * 
     * @param reply the reply message to be populated if any validation is failed
	 * @param hl7Header the message header of the incoming message
	 * @param patientId the id of the patient to be validated
	 * @param mrgPatientId the id of the patient to be merged
	 * @param isPixCreate Whether this validation is for PIX patient creation
	 * @return <code>true</code> if the message is correct; <code>false</code>otherwise.
	 * @throws HL7Exception if something is wrong with HL7 message 
	 * @throws PixPdqException if something is wrong with the application
	 */
	private boolean validateMessage(ACK reply, HL7Header hl7Header, PatientIdentifier patientId, PatientIdentifier mrgPatientId, boolean isPixCreate) 
	throws HL7Exception, PixPdqException {
		Identifier serverApplication = getServerApplication(actor.getConnection());
		Identifier serverFacility = getServerFacility(actor.getConnection());
		Identifier receivingApplication = hl7Header.getReceivingApplication();
		Identifier receivingFacility = hl7Header.getReceivingFacility();
		String incomingMessageId = hl7Header.getMessageControlId();
		//1. validate receiving facility and receiving application
		boolean isValidFacilityApplication = validateReceivingFacilityApplication(reply, 
				receivingApplication, receivingFacility, 
				serverApplication, serverFacility, incomingMessageId);
		if (!isValidFacilityApplication) return false;		
		
		//2.validate the domain
		boolean isValidDomain = validateDomain(reply, patientId, incomingMessageId);
		if (!isValidDomain) return false;
		
		//3. validate ID itself 
		if (!isPixCreate) { 
			//Do not valid patient id for PIX patient creation
			boolean isValidPid = validatePatientId(reply, patientId, hl7Header.toMessageHeader(), false, incomingMessageId);
			if (!isValidPid) return false;
		}
		
		//4. validate mrgPatientId
		if (mrgPatientId != null) {
			boolean isValidMrgPid = validatePatientId(reply, mrgPatientId, hl7Header.toMessageHeader(), true, incomingMessageId);
			if (!isValidMrgPid) return false;
		}
		
		//Finally, it must be true when it reaches here
		return true;
	}

	/**
	 * Checks the given whether the given patient id is a valid patient id.
	 * 
     * @param reply the reply message to be populated if any validation is failed
	 * @param patientId the patient id to be checked
	 * @param header the incoming message header 
	 * @param isMrgPatientId whether the patient id to be checked is a merge patient id.
	 * @param incomingMessageId the incoming message id.
	 * @return <code>true</code> if the patientId is valid; otherwise <code>false</code>.
	 * @throws HL7Exception if something is wrong with HL7 message 
	 * @throws PixPdqException if something is wrong with the application
	 */
	private boolean validatePatientId(ACK reply, PatientIdentifier patientId, 
			MessageHeader header, boolean isMrgPatientId, String incomingMessageId)
	throws HL7Exception, PixPdqException{
		boolean validPatient;
		try {
			validPatient = pixAdapter.isValidPatient(patientId, header);
		} catch (PixManagerException e) {
			throw new PixPdqException(e);
		}
		if (!validPatient) {
			HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
			if (isMrgPatientId){
				//segmentId=MRG, sequence=1, fieldPosition=1, fieldRepetition=1, componentNubmer=1
				HL7v231.populateERR(reply.getERR(), "MRG", "1", "1", "1", "1",
						"204", "查找不到与" + patientId.getId() + "相符合的记录");
			} else {
				//segmentId=PID, sequence=1, fieldPosition=3, fieldRepetition=1, componentNubmer=1
				HL7v231.populateERR(reply.getERR(), "PID", "1", "3", "1", "1",
						"204", "查找不到与" + patientId.getId() + "相符合的记录");
			}
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
	private Patient getPatient(Message msgIn) throws PixPdqException,HL7Exception 
	{
		HL7v231ToBaseConvertor convertor = null;
		
		//如果是2.3.1版本 NEW个HL7变量
		if (msgIn.getVersion().equals("2.3.1")) 
		{
			convertor = new HL7v231ToBaseConvertor(msgIn, actor.getActorDescription(), pixAdapter);
			
		} else 
		{
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
		
		//身份识别信息PID4的特殊处理
		@SuppressWarnings("unused")
		List<ExtendForPerson>  ExtendList = new ArrayList<ExtendForPerson>();
		List<PatientIdentifier> PatientList = new ArrayList<PatientIdentifier>();
		
		try 
		{
			ExtendList = pixAdapter.ExtendFieldForPatient();
			
			int AlternatPatientIDCount = convertor.getAlternatePatientIds().size();
			
			if(AlternatPatientIDCount>0)
			{
				PatientList = convertor.getAlternatePatientIds();
				
				for(ExtendForPerson mylist:ExtendList)
				{
					if(mylist.getExtendfieldname()!=null && mylist.getPid4fields()!=null)
					{
						if(mylist.getPid4fields()>0 && mylist.getPid4fields() <20)
						{
							int ExtendField = mylist.getPid4fields()-1;
						
							//System.out.println(ExtendField + " " + AlternatPatientIDCount);
						
							if(ExtendField < AlternatPatientIDCount)
							{
								if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM6"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom6(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom6(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM7"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom7(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom7(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM8"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom8(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom8(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM9"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom9(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom9(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM13"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom13(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom13(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM14"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom14(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom14(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM15"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom15(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom15(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM18"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom18(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom18(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM19"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom19(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom19(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM20"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom20(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom20(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM21"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom21(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom21(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM22"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom22(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom22(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM23"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom23(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom23(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM24"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom24(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom24(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM25"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom25(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom25(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM26"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom26(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom26(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM27"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom27(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom27(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM28"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom28(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom28(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM29"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom29(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom29(PatientList.get(ExtendField).getId());
										}
									}
								}
								else if(mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM30"))
								{
									if(PatientList.get(ExtendField).getId()!=null)
									{
										if(PatientList.get(ExtendField).getAssigningAuthority().getUniversalId()!=null)
										{
											patientDesc.setCustom30(PatientList.get(ExtendField).getId() + "^" + 
																   PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
										}
										else
										{
											patientDesc.setCustom30(PatientList.get(ExtendField).getId());
										}
									}
								}
							}
						}
					}
			}
			}
			ExtendList=null;
			PatientList=null;
			
		} catch (PixManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//中文姓名
		patientDesc.setPatientName(convertor.getPatientName());
		
		patientDesc.setSickName(convertor.getPatientName());
		
		//出生日期
		patientDesc.setBirthDateTime(convertor.getBirthDate());
		
		//出生所在地的省
		patientDesc.setBirthProvince(convertor.getBirthProvince());
		
		//出生所在地的市
		patientDesc.setBirthCity(convertor.getBirthCity());
		
		//出生所在区县
		patientDesc.setBirthCounty(convertor.getBirthCounty());
		
		//出生地 
		patientDesc.setBirthPlace(convertor.getBirthAddress());
		
		//出生地所在邮编
		patientDesc.setBirthZip(convertor.getBirhtZip());
		
		//多胞胎
		patientDesc.setMulitpleBirthIndicator(convertor.getPID_24());
		
		//出生次序
		patientDesc.setBirthOrder(convertor.getBirthOrder());
		
		//母亲娘家姓
		patientDesc.setMothersMaidenName(convertor.getMotherMaidenName());
		
		//社会保险号
		patientDesc.setSsn(convertor.getSsn());
		
		//身份证号
		patientDesc.setIdentityNO(convertor.getDriversLicense().getLicenseNumber());
		
		//市民卡号
		if(patientDesc.getCustom7()!=null) patientDesc.setCitizenCard(patientDesc.getCustom7());
		
		//医疗证号
		if(patientDesc.getCustom8()!=null) patientDesc.setMedicalCertificate(patientDesc.getCustom8());
		
		//医保个人编号
		if(patientDesc.getCustom9()!=null) patientDesc.setMeicarePerson(patientDesc.getCustom9());
		
		//老人证号 
		if (patientDesc.getCustom13()!=null) patientDesc.setElderCertificate(patientDesc.getCustom13());
		
		//病历号
		if (patientDesc.getCustom14()!=null) patientDesc.setOpcaseno(patientDesc.getCustom14());
		
		//其它
		if (patientDesc.getCustom15()!=null) patientDesc.setCustom21(patientDesc.getCustom15());
		
		if (patientDesc.getCustom18()!=null) patientDesc.setCustom22(patientDesc.getCustom18());
		
		if (patientDesc.getCustom19()!=null) patientDesc.setCustom23(patientDesc.getCustom19());
		
		if (patientDesc.getCustom20()!=null) patientDesc.setCustom24(patientDesc.getCustom20());
		
		
		//医疗保险号 
		patientDesc.setInsuranceNO(convertor.getMothersId().getId());
		
		//医疗保险类型、医疗保险名称
		//待定 
		
		//性别编码
		patientDesc.setAdministrativeSex(convertor.getSexType());
		
		//性别编码名称
		patientDesc.setGenderName(convertor.getGenderName());
		
		//性别编码系统
		patientDesc.setGenderDomain(convertor.getGenderDomain());
		
		//民族编码
		patientDesc.setEthnicGroup(convertor.getEthnicGroup());
		
		//民族编码名称
		patientDesc.setEthnicName(convertor.getEthnicGroupName());
		
		//民族编码系统
		patientDesc.setEthincDomain(convertor.getEthnicGroupDomain());
		
		//种族编码
		patientDesc.setRace(convertor.getRace());
		
		//种族编码名称
		patientDesc.setRaceName(convertor.getRaceName());
		
		//种族编码系统
		patientDesc.setRaceDomain(convertor.getRaceDomain());
		
		//国籍编码
		patientDesc.setNationality(convertor.getNationality());
		
		//国籍编码名称
		patientDesc.setNationalityName(convertor.getNationalityName());
		
		//国籍编码系统
		patientDesc.setNationalityDomain(convertor.getNationalityDomain());
		
		//语言编码
		patientDesc.setPrimaryLanguage(convertor.getPrimaryLanguage());
		
		//宗教编码
		patientDesc.setReligion(convertor.getReligion());
		
		//婚姻编码
		patientDesc.setMaritalStatus(convertor.getMaritalStatus());
		
		//婚姻编码名称
		patientDesc.setMaritalStatusName(convertor.getMaritalStatusName());
		
		//婚姻编码系统
		patientDesc.setMaritalDomain(convertor.getMaritalStatusDomain());
		
		if(patientDesc.getPatientName()!=null)
		{
			//教育程度编码
			patientDesc.setDegree(convertor.getPatientName().getDegree());
			
			//教育程度编码名称
			patientDesc.setDegreeName(convertor.getPatientName().getDegreeName());
			
			//教育编码系统
			patientDesc.setDegreeDomain(convertor.getPatientName().getDegreeDomain());
					 
		}
		
		//邮件地址 
		patientDesc.setEmail(convertor.getEmail());
		
		//居住地所在地省 
		patientDesc.setHomeProvince(convertor.getHomeProvince());
		
		//居住地所在地市
		patientDesc.setHomeCity(convertor.getHomeCity());
			
		//居住地所在地区县 
		patientDesc.setHomeCounty(convertor.getHomeCounty());
		
		//居住地所在地邮编
		patientDesc.setHomeZip(convertor.getHomeZip());
		
		//居住地址
		patientDesc.setHomeAddress(convertor.getHomeAddress());
		
		//户口所在地省
		patientDesc.setRegisteredProvince(convertor.getRegisteredProvince());
		
		//户口所在地市
		patientDesc.setRegisteredCity(convertor.getRegisteredCity());
		
		//户口所在地区县
		patientDesc.setRegisteredCounty(convertor.getRegisteredCounty());
		
		//户口所在地邮编
		patientDesc.setRegisteredZip(convertor.getRegisteredZip());
		
		//户口地址
		patientDesc.setRegisteredAddress(convertor.getRegisteredAddress());
		
		//籍贯所在地省 
		patientDesc.setNativeProvince(convertor.getNativeProvince());
		
		//籍贯所在地市
		patientDesc.setNativeCity(convertor.getNativeCity());
		
		//职业编码
		patientDesc.setProfession(convertor.getPID12());
		
		//职业名称
		patientDesc.setProfessionName(convertor.getProfessionName());
		
		//职业编码系统
		patientDesc.setProfessionDomain(convertor.getProfessionDomain());
		
		//工作单位
		patientDesc.setCompany(convertor.getCompany());
		
		//工作邮编 
		patientDesc.setWorkZip(convertor.getWorkZip());
		
		//工作地址
		patientDesc.setWorkAddress(convertor.getWorkAddress());
		
		//家庭电话
		patientDesc.setHomePhone(convertor.getHomePhone());
		
		//私人电话
		patientDesc.setPrivateNumber(convertor.getPrivatePhone());
		
		//单位电话
		patientDesc.setWorkNumber(convertor.getWorkPhone());
		
		//监护人
		patientDesc.setGuardianPerson(convertor.getPID9());
		
		//保密级别
		patientDesc.setVip(convertor.getVip());

		//死亡时间
		patientDesc.setDeathDate(convertor.getDeathDate());
		
		//死亡标志
		patientDesc.setDeathIndicator(convertor.getDeathIndicator());
		
		//其它 
		patientDesc.setPatientAliases(convertor.getPatientAliases());
		
		patientDesc.setPatientAccountNumber(convertor.getpatientAccountNumber());
		
		patientDesc.setCitizenship(convertor.getCitizenShip());
		
		patientDesc.setVisits(convertor.getVisitList());
		
		patientDesc.setVipIndicator(convertor.getVipIndicator());
		
        patientDesc.setNextOfKin(convertor.getNextOfKin());
        
        patientDesc.setInsuranceType(convertor.getPid21_2_1());
        
        patientDesc.setInsuranceName(convertor.getPid21_3_1());
        
        patientDesc.setAccountLocked(convertor.getPid21_4_1());
        
        patientDesc.setAccountLockedDate(convertor.getPid21_5_1());
        
        patientDesc.setBirthTime(convertor.getPid21_6_1());
        
        patientDesc.setCardType(convertor.getCardType());
        
        try {
			patientDesc.setAllergy(convertor.getAllergy());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
        
        patientDesc.setPatIpTimes(convertor.getPV1_13_1());
        
        patientDesc.setPatAdmissionSource(convertor.getPV1_14_1());
        
        //离院处置名称
        patientDesc.setDischargeName(convertor.getPV1_15_1());
        
        //离院处置编码系统
        patientDesc.setDischargeDomain(convertor.getPV1_15_2_1());
        
        //入院时情况名称
        patientDesc.setAdmissionName(convertor.getPV1_15_3_1());
        
        //入院时情况编码系统
        patientDesc.setAdmissionDomain(convertor.getPV1_15_4_1());
        
        //病人住院状态名称
        patientDesc.setIpStatusName(convertor.getPV1_15_5_1());
        
        //病人住院状态编码系统
        patientDesc.setIpStatusDomain(convertor.getPV1_15_6_1());
        
        //病例分型名称
        patientDesc.setDificultyName(convertor.getPV1_15_7_1());
        
        //病例分型编码系统
        patientDesc.setDificultyDomain(convertor.getPV1_15_8_1());
        
        //入院途径名称
        patientDesc.setAdmissionSourceName(convertor.getPV1_15_9_1());
        
        //入院途径编码系统
        patientDesc.setAdmissionSourceDomain(convertor.getPV1_15_10_1());
        
        //支付方式名称
        patientDesc.setAccountStatusName(convertor.getPV1_15_11_1());
        
        //支付方式编码系统
        patientDesc.setAccountStatusDomain(convertor.getPV1_15_12_1());
        
        //患者类别编码
        patientDesc.setPatCategoryName(convertor.getPV1_15_13_1());
        
        //患者类别编码系统
        patientDesc.setPatCategorySystem(convertor.getPV1_15_14_1());
        
       //患者类型编码
        patientDesc.setPatientClassName(convertor.getPV1_15_15_1());
        
        //患者类型编码系统
        patientDesc.setPatientClassDomain(convertor.getPV1_15_16_1());
        
        //病人来源
        patientDesc.setPatientSourceName(convertor.getPV1_15_17_1());
        
        //病人再次入院标识
        patientDesc.setPatReAdmission(convertor.getPV1_15_18_1());
        
      //是否急疹转住院
        patientDesc.setIsEmergency(convertor.getPV1_15_19_1());
        
        patientDesc.setDiagnoseIcd(convertor.getPV1_15_20_1());
        
        patientDesc.setDiagnoseName(convertor.getPV1_15_21_1());
        
        patientDesc.setPatVip(convertor.getPV1_16_1());
        
        patientDesc.setPatAdmissionDoctors(convertor.getPV1_17_2());
        
        patientDesc.setPatAdmissionDoctorsId(convertor.getPV1_17_1());
        
        patientDesc.setPatientClass(convertor.getPV1_18());
        
        patientDesc.setPatientId(convertor.getPV1_19());
        
        //流水域名
        patientDesc.setCustom2(convertor.getPV1_19_4_1());
        
        patientDesc.setCustom3(convertor.getPV1_19_4_2());
        
        patientDesc.setCustom4(convertor.getPV1_19_4_3());
        
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
        
        //限额
        String LimitAmount = convertor.getPV1_42_1();
        
        if(LimitAmount!=null && LimitAmount.length()>0)
        {
        	String[] Amount=LimitAmount.split(",");
        	
//        	if (Amount[0].length()>0) patientDesc.setMedicinelimitamount(new BigDecimal(Amount[0]));
//        	
//        	if (Amount[1].length()>0) patientDesc.setSickbedlimitamount(new BigDecimal(Amount[1]));
//        	
//        	if (Amount[2].length()>0) patientDesc.setExaminelimitamount(new BigDecimal(Amount[2]));
//        	
//        	if (Amount[3].length()>0) patientDesc.setCurelimitamount(new BigDecimal(Amount[3]));
        }
        
        //前缀
        patientDesc.setPrefix(convertor.getPV1_42_2());
        
        patientDesc.setPatDeterBed(convertor.getPV1_42_3());
        
        patientDesc.setPatDeterDep(convertor.getPV1_42_4());
        
        //护理
        patientDesc.setTend(convertor.getPV1_42_5());
        
        //护士ID
        patientDesc.setPatNurseCode(convertor.getPV1_42_6());
        
        //护士姓名
        patientDesc.setPatNurseName(convertor.getPV1_42_7());
        
        //操作员
        patientDesc.setOperCode(convertor.getPV1_42_8());
        
        //操作日期
        patientDesc.setOperDate(convertor.getPV1_42_9());
        
        patientDesc.setPatForTempPointOfCare(convertor.getPV1_43_1());
        
        patientDesc.setPatForTempRoom(convertor.getPV1_43_2());
        
        patientDesc.setPatForTempBed(convertor.getPV1_43_3());
        
        patientDesc.setPatForTempDep(convertor.getPV1_43_4());
        
        patientDesc.setPatForTempPositionStatus(convertor.getPV1_43_5());
        
        patientDesc.setPatForTempPositionType(convertor.getPV1_43_6());
        
        patientDesc.setPatForTempBuilding(convertor.getPV1_43_7());
        
        patientDesc.setPatForTempFloor(convertor.getPV1_43_8());
        
        patientDesc.setPatForTempDescription(convertor.getPV1_43_9());
        
        patientDesc.setAdmitDate(convertor.getPV1_44());
        
        patientDesc.setDischargeDate(convertor.getPV1_45());
        
        patientDesc.setPatDifference(convertor.getPV1_46());
        
        patientDesc.setPatTotalCost(convertor.getPV1_47());
        
        patientDesc.setPatTotalDispatch(convertor.getPV1_48());
        
        patientDesc.setPatTotalAmountPayable(convertor.getPV1_49());
        
        //婴儿标志
        patientDesc.setBabyFlag(convertor.getPV1_50());
        
        //入院体重
        patientDesc.setAdmitWeight(convertor.getPV1_50_3());
        
        //入院体重单位
        patientDesc.setAdmitWeightUnit(convertor.getPV1_50_4());
        
        //出生体重
        patientDesc.setBirthWeight(convertor.getPV1_50_5());
        
        //出生体重单位
        patientDesc.setBabyWeightUnit(convertor.getPV1_50_6());
        
        patientDesc.setPatVisitLogo(convertor.getPV1_51());
        
        patientDesc.setOldPatientId(convertor.getPV1_52_1_1());
        
        patientDesc.setOldPatientDomain(convertor.getPV1_52_2_1());
        
        patientDesc.setOldVisitFlowId(convertor.getPV1_52_3_1());
        
        patientDesc.setOldVisitFlowDomain(convertor.getPV1_52_4_1());
        
        patientDesc.setOldStatus(convertor.getPV1_52_5_1());
        
        patientDesc.setOldInfo(convertor.getPV1_52_6_1());
        
        patientDesc.setNoonCode(convertor.getPV1_52_1_1_1());
        
        patientDesc.setPaykindCode(convertor.getPV1_52_1_2_1());
        
        patientDesc.setPaykindName(convertor.getPV1_52_1_3_1());
        
        patientDesc.setSchemaNo(convertor.getPV1_52_1_4_1());
        
        patientDesc.setOrderNo(convertor.getPV1_52_1_5_1());
        
        patientDesc.setSeeNo(convertor.getPV1_52_1_6_1());
        
        patientDesc.setBeginTime(convertor.getPV1_52_2_1_1());
        
        patientDesc.setEndTime(convertor.getPV1_52_2_2_1());
        
        patientDesc.setYnBook(convertor.getPV1_52_2_3_1());
        
        patientDesc.setYnfr(convertor.getPV1_52_2_4_1());
        
        patientDesc.setAppendFlag(convertor.getPV1_52_2_5_1());
        
        patientDesc.setYnSee(convertor.getPV1_52_2_6_1());
        
        patientDesc.setSeeDate(convertor.getPV1_52_3_1_1());
              
        patientDesc.setTriageFlag(convertor.getPV1_52_3_2_1());
        
        patientDesc.setTriageOpcd(convertor.getPV1_52_3_3_1());
        
        patientDesc.setTriageDate(convertor.getPV1_52_3_4_1());
        
        patientDesc.setSeeDpcd(convertor.getPV1_52_3_5_1());
        
        patientDesc.setSeeDocd(convertor.getPV1_52_3_6_1());
        
        patientDesc.setOutPatientStatusA(convertor.getPV1_52_4_1_1());
        
        patientDesc.setOutPatientStatusB(convertor.getPV1_52_4_2_1());
        
        patientDesc.setOutPatientStatusC(convertor.getPV1_52_4_3_1());
        
        patientDesc.setInPatientStatusA(convertor.getPV1_52_4_4_1());
        
        patientDesc.setInPatientStatusB(convertor.getPV1_52_4_5_1());
        
        patientDesc.setInPatientStatusC(convertor.getPV1_52_4_6_1());
  
		return patientDesc;
	}

	/**
	 * Gets the patient identifier from a Patient PID segment.
	 * 
	 * @param pid the PID segment
	 * @return a {@link PatientIdentifier}
	 */
	private PatientIdentifier getPatientIdentifiers(PID pid) 
	{
		PatientIdentifier identifier = new PatientIdentifier();
		
		CX[] cxs = pid.getPatientIdentifierList();
		
		for (CX cx : cxs) 
		{
			Identifier assignAuth = new Identifier(cx.getAssigningAuthority()
					.getNamespaceID().getValue(), cx.getAssigningAuthority()
					.getUniversalID().getValue(), cx.getAssigningAuthority()
					.getUniversalIDType().getValue());
			
			Identifier assignFac = new Identifier(cx.getAssigningFacility()
					.getNamespaceID().getValue(), cx.getAssigningFacility()
					.getUniversalID().getValue(), cx.getAssigningFacility()
					.getUniversalIDType().getValue());
			
			identifier.setAssigningAuthority(AssigningAuthorityUtil.reconcileIdentifier(assignAuth, actor.getActorDescription(), pixAdapter));
			
			identifier.setAssigningFacility(assignFac);
			
			identifier.setId(cx.getID().getValue());
			
			identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
		}
		
		return identifier;
		
	}

	/**
	 * Gets the merge patient identifier out of a MRG segment.
	 * 
	 * @param MRG segment the merge segment
	 * @return a {@link PatientIdentifier} 
	 */
	private PatientIdentifier getMrgPatientIdentifiers(MRG mrg) 
	{
		PatientIdentifier identifier = new PatientIdentifier();
		
		CX[] cxs = mrg.getPriorPatientIdentifierList();
		
		for (CX cx : cxs) 
		{
			Identifier assignAuth = new Identifier(cx.getAssigningAuthority()
					.getNamespaceID().getValue(), cx.getAssigningAuthority()
					.getUniversalID().getValue(), cx.getAssigningAuthority()
					.getUniversalIDType().getValue());
			
			Identifier assignFac = new Identifier(cx.getAssigningFacility()
					.getNamespaceID().getValue(), cx.getAssigningFacility()
					.getUniversalID().getValue(), cx.getAssigningFacility()
					.getUniversalIDType().getValue());
			
			identifier.setAssigningAuthority(AssigningAuthorityUtil.reconcileIdentifier(assignAuth, actor.getActorDescription(), pixAdapter));
			
			identifier.setAssigningFacility(assignFac);
			
			identifier.setId(cx.getID().getValue());
			
			identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
		}
		
		return identifier;
	}
	
	private Patient getMrgPatient(Message msgIn) throws HL7Exception
	{
		HL7v231ToBaseConvertor convertor = null;
		
		convertor = new HL7v231ToBaseConvertor(msgIn, actor.getActorDescription(), pixAdapter);
		
		Patient patientDesc = new Patient();
		
		patientDesc.setPatientIds(convertor.getMrgPatientIds());
		
		patientDesc.setPatientName(convertor.getMrgPatientName());
		
		patientDesc.setPatientAccountNumber(convertor.getMrgpatientAccountNumber());
		
		patientDesc.setVisits(convertor.getMrgVisitList());
		
		return patientDesc;
	}
}
