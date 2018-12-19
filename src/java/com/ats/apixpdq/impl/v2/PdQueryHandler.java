package com.ats.apixpdq.impl.v2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ats.aempi.model.ExtendForPerson;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.audit.ActiveParticipant;
import com.ats.aexchange.audit.ParticipantObject;
import com.ats.aexchange.audit.TypeValuePair;
import com.ats.aexchange.config.PropertyFacade;
import com.ats.aexchange.datamodel.Address;
import com.ats.aexchange.datamodel.ExtendFields;
import com.ats.aexchange.datamodel.DriversLicense;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.datamodel.PersonName;
import com.ats.aexchange.datamodel.PhoneNumber;
import com.ats.aexchange.datamodel.SharedEnums;
import com.ats.aexchange.datamodel.SharedEnums.PhoneType;
import com.ats.aexchange.utils.DateUtil;
import com.ats.aexchange.utils.ExceptionUtil;
import com.ats.aexchange.utils.StringUtil;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.GenericComposite;
import ca.uhn.hl7v2.model.GenericPrimitive;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.Varies;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.HD;
import ca.uhn.hl7v2.model.v25.datatype.XAD;
import ca.uhn.hl7v2.model.v25.datatype.XPN;
import ca.uhn.hl7v2.model.v25.datatype.XTN;
import ca.uhn.hl7v2.model.v25.group.RSP_K21_QUERY_RESPONSE;
import ca.uhn.hl7v2.model.v25.group.RSP_K31_RESPONSE;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.message.QCN_J01;
import ca.uhn.hl7v2.model.v25.message.RSP_K21;
import ca.uhn.hl7v2.model.v25.message.RSP_K31;
import ca.uhn.hl7v2.model.v25.segment.DSC;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.model.v25.segment.PV1;
import ca.uhn.hl7v2.model.v25.segment.QID;
import ca.uhn.hl7v2.model.v25.segment.QPD;
import ca.uhn.hl7v2.model.v25.segment.RCP;
import ca.uhn.hl7v2.parser.EncodingCharacters;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;

import com.ats.apixpdq.api.IPdSupplierAdapter;
import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.api.PatientException;
import com.ats.apixpdq.api.PdSupplierException;
import com.ats.apixpdq.api.PdqQuery;
import com.ats.apixpdq.api.PdqResult;
import com.ats.apixpdq.common.AssigningAuthorityUtil;
import com.ats.apixpdq.common.BaseHandler;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.ContinuationPointer;
import com.ats.apixpdq.common.PixPdqException;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.impl.v2.hl7.HL7Header;
import com.ats.apixpdq.impl.v2.hl7.HL7Util;
import com.ats.apixpdq.impl.v2.hl7.HL7v25;
import com.misys.hieportal.sysmon.IJMXEventNotifier;

/**
 * This class processes PDQ query message in HL7 v2.5 format. It handles the PDQ transaction of the PDQ profile. The supported message type includes QBP^Q22 and
 * QCN^J01|QBP^ZV1.
 * 
 * @author yrh
 * @version 2012-08-10
 * 
 */
class PdQueryHandler extends BaseHandler implements Application 
{
	/* Logger for problems */
	private static Logger log = Logger.getLogger(PdSupplier.class);

	/** The connection description of the actor for this handler */
	private IConnectionDescription connection;

	private PdSupplier actor = null;
	
	private IPdSupplierAdapter pdqAdapter = null;
	
	private IJMXEventNotifier eventBean = null;
	
	/** Used to store continuation pointer <String(pointer), ContinuationPointer> */
	private static Hashtable<String, ContinuationPointer> dscMap = new Hashtable<String, ContinuationPointer>();

	/**
	 * Constructor
	 * 
	 * @param actor
	 *            the {@link PdSupplier} actor
	 */
	PdQueryHandler(PdSupplier actor) 
	{
		super();
		
		this.actor = actor;
		
		this.pdqAdapter = PixPdqFactory.getPdSupplierAdapter();
		
		this.eventBean = actor.getPixEvent();
		
		this.connection = actor.getConnection();
		
		assert this.connection != null;
		
		assert this.pdqAdapter != null;
	}

	/**
	 * Whether a incoming message can be processed by this handler.
	 * 
	 * @return <code>true</code> if the incoming message can be processed; otherwise <code>false</code>.
	 */
	public boolean canProcess(Message theIn) 
	{
		if (theIn instanceof QBP_Q21 || theIn instanceof QCN_J01)
			return true;
		else
			return false;
	}

	/**
	 * Processes the incoming PDQ Query Message. Valid messages are QBP^Q22 and QCN^J01 and QBP^ZV1.
	 * 
	 * @param msgIn
	 *            the incoming PDQ query message
	 */
	public Message processMessage(Message msgIn) throws ApplicationException, HL7Exception 
	{
		//ATNA,信息保存
		Message retMessage = null;
		
		try {
			HL7Header hl7Header = new HL7Header(msgIn);

			log.fatal("\n" + "收到HL7消息:" + "\n" + msgIn);
			

			//根据查询控制字段调取对应查询,支持的查询为QBP^ZV1,QBP^Q21,QBP^JO1
			if (msgIn instanceof QBP_Q21 && hl7Header.getTriggerEvent().toString().equals("Q22")) 
			{
				retMessage = processQuery(msgIn);
				
			} else if (msgIn instanceof QBP_Q21 && hl7Header.getTriggerEvent().toString().equals("ZV1")) 
			{
				  retMessage = processVisitQuery(msgIn);		
				  
			} else if (msgIn instanceof QCN_J01) 
			{
				retMessage = processQCN_J01((QCN_J01) msgIn);
				
			} else 
			{
				throw new ApplicationException("Unexpected request to PD Supplier server. Valid messages are QBP^Q22 and QCN^J01 and QBP^ZV1");
			}

		} 
		catch (PixPdqException e) 
		{
			
			throw new ApplicationException(ExceptionUtil.strip(e.getMessage()), e);
			
		} catch (HL7Exception e) 
		{
			
			throw new HL7Exception(ExceptionUtil.strip(e.getMessage()), e);
			
		} catch (PdSupplierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.ats.aempi.ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally 
		{
			
			if (eventBean != null)
				eventBean.notifyMessageReceived();
		}
		
		return retMessage;
	}
	
	/**
	 * Main logics to process QBP^ZV1 patient demographics query.
	 * 
	 * @param msgIn
	 *            the incoming PDQ Visit Query message
	 * @return the response message associated with the incoming visit query message
	 * @throws PixPdqException
	 *             if anything wrong with the application
	 * @throws HL7Exception
	 *             if anything wrong with the HL7 message format
	 * @throws com.ats.aempi.ApplicationException 
	 * @throws PdSupplierException 
	 */
	private Message processVisitQuery(Message msgIn) throws PixPdqException, HL7Exception, PdSupplierException, com.ats.aempi.ApplicationException 
	{

		HL7Header hl7Header = new HL7Header(msgIn);

		RSP_K31 reply = new RSP_K31();
		
		processMSH(hl7Header, reply.getMSH(), "RSP", "ZV2", "RSP_ZV2", getServerApplication(connection), getServerFacility(connection));

		// Now, it must be QBP_Q21 message
		QBP_Q21 message = (QBP_Q21) msgIn;
		
		// Process QPD
		QPD qpd = message.getQPD();

		String queryTag = qpd.getQueryTag().getValue();
		
		Terser inTerser = new Terser(message);
		
		Terser outTerser = new Terser(reply);

		// Validate the incoming message first
		boolean isValidMessage = validateVisitMessage(reply, hl7Header, queryTag, inTerser, outTerser);
		
		if (!isValidMessage)
			return reply;

		// Process ID in the following cenarios:See ITI Technical Framework Section 3.21.4.2.2.8
		// 0. If use DSC (Continuation pointer), we retrieve it from the memory
		// 1. Validate the return domains. If there is a domain not recognized, return an error message.
		// (Case #3 The Patient Demographics Supplier Actor does not recognize one or more of the
		// domains in QPD-8-What Domains Returned.)
		// 2. If the return domain (QPD-8) is not specified, return all found records. If it exceeds the max num needed,
		// then just return that number, and construct a DSC segment (Case #1).
		// 3. If one or more domains are specified, and there is at least one patient record found, each found
		// patient (the PID segment) will have the PID-3 field containing each id repetition for each domain. If an
		// identifier does not exist for a domain that was specified on QPD-8, nothing is returned in the Id list.
		// (Case #2).

		// 1. validate the return domains
		List<Identifier> returnDomains = getVisitReturnDomain(qpd, reply, hl7Header.getMessageControlId(), queryTag, outTerser, inTerser);
		// If the returnDomains is null, the reply will contain error message
		if (returnDomains == null)
			return reply;

		// Process RCP segment
		int recordRequestNumber = getRecordRequestNumber(message);
		// Process DSC
		String pointer = getContinuationPointer(message);

		// 2. and 3.
		List<List<Patient>> finalPatients = new ArrayList<List<Patient>>();
		
		String newPointer = null;
		
		PdqResult pdqResult;
		
		int totalNumber = -1;
		
		int remainingNumber = -1;
		
		if (isContinuationQueryByAPixPdq()) 
		{
			if (StringUtil.goodString(pointer)) 
			{
				// Get the patients from Cache Pointer
				if (!dscMap.containsKey(pointer)) 
				{
					HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
					
					HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
					
					HL7Util.echoQPD(outTerser, inTerser);
					
					// segmentId=DSC, sequence=1, fieldPosition=1, fieldRepetition=1, componentNubmer has to be empty
					HL7v25.populateERR(reply.getERR(), "DSC", "1", "1", "1", null, null, "Unknown Continuation Pointer");
					
					return reply;
				}
				
				ContinuationPointer cp = dscMap.get(pointer);
				
				totalNumber = cp.getTotalRecords();
				
				returnDomains = cp.getReturnDomain();
				
				List<List<Patient>> allPatients = cp.getPatients();

				if (recordRequestNumber < 0 || recordRequestNumber >= allPatients.size()) 
				{
					finalPatients = allPatients;
					
					// remove continuation pointer if no more patients available
					synchronized (dscMap) 
					{
						dscMap.remove(pointer);
					}
					
					remainingNumber = 0;
				} else 
				{
					finalPatients = getSubList(0, recordRequestNumber, allPatients);
					
					List<List<Patient>> remainingPatients = getSubList(recordRequestNumber, allPatients.size(), allPatients);
					
					cp.setPatients(remainingPatients);
					
					cp.setLastRequestTime(System.currentTimeMillis());
					
					newPointer = pointer;
					
					remainingNumber = remainingPatients.size();
				}
			} 
			else 
			{ // This is the first time query, so get patients directly from EMPI.
				List<List<Patient>> allPatients = new ArrayList<List<Patient>>();
				
				pdqResult = getPdqVisitResult(qpd, reply, hl7Header, outTerser, inTerser, pointer, recordRequestNumber, false, returnDomains);
				
				if (pdqResult == null)
					return reply;

				// Filter out patients by return domains.
				allPatients = getPatientList(pdqResult, returnDomains);
				
				totalNumber = allPatients.size();
				
				if (recordRequestNumber <= 0 || recordRequestNumber >= allPatients.size()) 
				{
					finalPatients = allPatients;
					
					remainingNumber = 0;
				} 
				else 
				{
					finalPatients = getSubList(0, recordRequestNumber, allPatients);
					
					// add continuation pointer
					List<List<Patient>> remainingPatients = getSubList(recordRequestNumber, allPatients.size(), allPatients);
					
					String pointerControlId = queryTag + ":" + getMessageControlId();
					
					ContinuationPointer cp = new ContinuationPointer();
					
					cp.setPointer(pointerControlId);
					
					cp.setReturnDomain(returnDomains);
					
					cp.setPatients(remainingPatients);
					
					cp.setLastRequestTime(System.currentTimeMillis());
					
					cp.setTotalRecords(allPatients.size());
					
					cp.setQueryTag(queryTag);
					
					synchronized (dscMap) 
					{
						dscMap.put(pointerControlId, cp);
					}
					
					newPointer = pointerControlId;
					
					remainingNumber = remainingPatients.size();
				}
			}
		} 
		else 
		{
			// Otherwise Handle Continuation Query by EMPI
			pdqResult = getPdqVisitResult(qpd, reply, hl7Header, outTerser, inTerser, pointer, recordRequestNumber, true, returnDomains);
			
			if (pdqResult == null)
				return reply;
			
			finalPatients = getPatientList(pdqResult, returnDomains);
			
			newPointer = pdqResult.getContinuationPointer();
		}

		HL7v25.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());
		
		if (finalPatients.size() == 0) 
		{
			HL7v25.populateQAK(reply.getQAK(), queryTag, "NF");
		} 
		else 
		{
			if (totalNumber == -1 || remainingNumber == -1)
				HL7v25.populateQAK(reply.getQAK(), queryTag, "OK");
			else
				HL7v25.populateQAK(reply.getQAK(), queryTag, "OK", totalNumber, finalPatients.size(), remainingNumber);
		}

		HL7Util.echoQPD(outTerser, inTerser);

		if (finalPatients.size() >= 1) 
		{
			
			popluateVisitPIDGroup(reply, finalPatients, returnDomains);

		}// end if found patient

		// Populate DSC segment if appropriate
		if (newPointer != null)
			HL7v25.populateDSC(reply.getDSC(), newPointer);

		// Audit Log
		//auditLog(finalPatients, hl7Header, queryTag, message);

		//System.out.print(reply);
		
		log.fatal("\n" + "返回HL7消息：" + "\n" +reply);
		
		return reply;
	}

	/**
	 * Main logics to process QBP^Q22 patient demographics query.
	 * 
	 * @param msgIn
	 *            the incoming PDQ message
	 * @return the response message associated with the incoming query message
	 * @throws PixPdqException
	 *             if anything wrong with the application
	 * @throws HL7Exception
	 *             if anything wrong with the HL7 message format
	 * @throws com.ats.aempi.ApplicationException 
	 * @throws PdSupplierException 
	 */
	private Message processQuery(Message msgIn) throws PixPdqException, HL7Exception, PdSupplierException, com.ats.aempi.ApplicationException 
	{

		HL7Header hl7Header = new HL7Header(msgIn);

		RSP_K21 reply = new RSP_K21();
		
		processMSH(hl7Header, reply.getMSH(), "RSP", "K21", "RSP_K21", getServerApplication(connection), getServerFacility(connection));

		// Now, it must be QBP_Q21 message
		QBP_Q21 message = (QBP_Q21) msgIn;
		
		// Process QPD
		QPD qpd = message.getQPD();

		String queryTag = qpd.getQueryTag().getValue();
		
		Terser inTerser = new Terser(message);
		
		Terser outTerser = new Terser(reply);

		// Validate the incoming message first
		boolean isValidMessage = validateMessage(reply, hl7Header, queryTag, inTerser, outTerser);
		
		if (!isValidMessage)
			return reply;

		// Process ID in the following cenarios:See ITI Technical Framework Section 3.21.4.2.2.8
		// 0. If use DSC (Continuation pointer), we retrieve it from the memory
		// 1. Validate the return domains. If there is a domain not recognized, return an error message.
		// (Case #3 The Patient Demographics Supplier Actor does not recognize one or more of the
		// domains in QPD-8-What Domains Returned.)
		// 2. If the return domain (QPD-8) is not specified, return all found records. If it exceeds the max num needed,
		// then just return that number, and construct a DSC segment (Case #1).
		// 3. If one or more domains are specified, and there is at least one patient record found, each found
		// patient (the PID segment) will have the PID-3 field containing each id repetition for each domain. If an
		// identifier does not exist for a domain that was specified on QPD-8, nothing is returned in the Id list.
		// (Case #2).

		// 1. validate the return domains
		List<Identifier> returnDomains = getReturnDomain(qpd, reply, hl7Header.getMessageControlId(), queryTag, outTerser, inTerser);
		
		// If the returnDomains is null, the reply will contain error message
		if (returnDomains == null)
			return reply;

		// Process RCP segment
		int recordRequestNumber = getRecordRequestNumber(message);
		
		// Process DSC
		String pointer = getContinuationPointer(message);

		// 2. and 3.
		List<List<Patient>> finalPatients = new ArrayList<List<Patient>>();
		
		String newPointer = null;
		
		PdqResult pdqResult;
		
		int totalNumber = -1;
		
		int remainingNumber = -1;
		
		if (isContinuationQueryByAPixPdq())
		{
			if (StringUtil.goodString(pointer)) 
			{
				// Get the patients from Cache Pointer
				if (!dscMap.containsKey(pointer)) 
				{
					HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
					
					HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
					
					HL7Util.echoQPD(outTerser, inTerser);
					
					// segmentId=DSC, sequence=1, fieldPosition=1, fieldRepetition=1, componentNubmer has to be empty
					HL7v25.populateERR(reply.getERR(), "DSC", "1", "1", "1", null, null, "Unknown Continuation Pointer");
					
					return reply;
				}
				ContinuationPointer cp = dscMap.get(pointer);
				
				totalNumber = cp.getTotalRecords();
				
				returnDomains = cp.getReturnDomain();
				
				List<List<Patient>> allPatients = cp.getPatients();

				if (recordRequestNumber < 0 || recordRequestNumber >= allPatients.size()) 
				{
					finalPatients = allPatients;
					
					// remove continuation pointer if no more patients available
					synchronized (dscMap) 
					{
						dscMap.remove(pointer);
					}
					remainingNumber = 0;
				} 
				else 
				{
					finalPatients = getSubList(0, recordRequestNumber, allPatients);
					
					List<List<Patient>> remainingPatients = getSubList(recordRequestNumber, allPatients.size(), allPatients);
					
					cp.setPatients(remainingPatients);
					
					cp.setLastRequestTime(System.currentTimeMillis());
					
					newPointer = pointer;
					
					remainingNumber = remainingPatients.size();
				}
			} 
			else 
			{ // This is the first time query, so get patients directly from EMPI.
				List<List<Patient>> allPatients = new ArrayList<List<Patient>>();
				
				pdqResult = getPdqResult(qpd, reply, hl7Header, outTerser, inTerser, pointer, recordRequestNumber, false, returnDomains);
				
				if (pdqResult == null)
					return reply;

				// Filter out patients by return domains.
				allPatients = getPatientList(pdqResult, returnDomains);
				
				totalNumber = allPatients.size();
				
				if (recordRequestNumber <= 0 || recordRequestNumber >= allPatients.size()) 
				{
					finalPatients = allPatients;
					
					remainingNumber = 0;
				} 
				else 
				{
					finalPatients = getSubList(0, recordRequestNumber, allPatients);
					
					// add continuation pointer
					List<List<Patient>> remainingPatients = getSubList(recordRequestNumber, allPatients.size(), allPatients);
					
					String pointerControlId = queryTag + ":" + getMessageControlId();
					
					ContinuationPointer cp = new ContinuationPointer();
					
					cp.setPointer(pointerControlId);
					
					cp.setReturnDomain(returnDomains);
					
					cp.setPatients(remainingPatients);
					
					cp.setLastRequestTime(System.currentTimeMillis());
					
					cp.setTotalRecords(allPatients.size());
					
					cp.setQueryTag(queryTag);
					
					synchronized (dscMap) 
					{
						dscMap.put(pointerControlId, cp);
					}
					
					newPointer = pointerControlId;
					
					remainingNumber = remainingPatients.size();
				}
			}
		} 
		else 
		{
			// Otherwise Handle Continuation Query by EMPI
			pdqResult = getPdqResult(qpd, reply, hl7Header, outTerser, inTerser, pointer, recordRequestNumber, true, returnDomains);
			
			if (pdqResult == null)
				return reply;
			
			finalPatients = getPatientList(pdqResult, returnDomains);
			
			newPointer = pdqResult.getContinuationPointer();
		}

		HL7v25.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());
		
		if (finalPatients.size() == 0) 
		{
			HL7v25.populateQAK(reply.getQAK(), queryTag, "NF");
		} 
		else 
		{
			if (totalNumber == -1 || remainingNumber == -1)
				HL7v25.populateQAK(reply.getQAK(), queryTag, "OK");
			else
				HL7v25.populateQAK(reply.getQAK(), queryTag, "OK", totalNumber, finalPatients.size(), remainingNumber);
		}

		HL7Util.echoQPD(outTerser, inTerser);

		if (finalPatients.size() >= 1) {

			popluatePIDGroup(reply, finalPatients, returnDomains);

		}// end if found patient

		// Populate DSC segment if appropriate
		if (newPointer != null)
			HL7v25.populateDSC(reply.getDSC(), newPointer);

		// Audit Log
		auditLog(finalPatients, hl7Header, queryTag, message);

		//System.out.print(reply);
		
		log.fatal("\n" + "返回HL7消息：" + "\n" +reply);
		
		return reply;
	}

	/**
	 * Processes QCN_J01 cancel query message.
	 * 
	 * @param qcnMsg
	 *            the incoming QCN query cancel message
	 * @return Message the acknowledge to the incoming QCN message
	 * @throws DataTypeException
	 *             if there is any data type error
	 * @throws PixPdqException
	 *             if there is any application error
	 */
	private Message processQCN_J01(QCN_J01 qcnMsg) throws DataTypeException, PixPdqException 
	{

		HL7Header hl7Header = new HL7Header(qcnMsg);

		ACK reply = new ACK();
		
		MSH replyMsh = reply.getMSH();
		
		processMSH(hl7Header, replyMsh, "ACK", "J01", "ACK", getServerApplication(connection), getServerFacility(connection));

		String messageControlId = hl7Header.getMessageControlId();
		
		HL7v25.populateMSA(reply.getMSA(), "AA", messageControlId);
		
		// Remove the query result for this query tag
		QID qid = qcnMsg.getQID();
		
		String queryTag = qid.getQueryTag().getValue();

		String messageQueryName = qid.getMessageQueryName().getIdentifier().getValue();
		
		if (isContinuationQueryByAPixPdq()) 
		{
			long timeout = 600000; // defaults to 600000 millieseconds (10 minutes)
			
			try 
			{
				timeout = Long.parseLong(Configuration.getPropertySetValue(connection, "QueryProperties", "ContinuationPointerTimeout", false));
			} 
			catch (IheConfigurationException e) 
			{
				throw new PixPdqException(e);
			}

			List<String> removeList = new ArrayList<String>();
			
			synchronized (dscMap) 
			{
				Set<String> keys = dscMap.keySet();
				
				for (String key : keys) 
				{
					ContinuationPointer cp = dscMap.get(key);
					
					// Cancel the query matched by the queryTag
					if (cp == null || queryTag.equals(cp.getQueryTag())) 
					{
						// Remove the key with invalid value as well as
						// the key whose query to be canceled
						removeList.add(key);
						
						continue;
					}

					// Also, clean up timed out entries. Set time out to be 10 minutes.
					long lastTime = cp.getLastRequestTime();
					
					if ((System.currentTimeMillis() - lastTime) > timeout)
						removeList.add(key);
				}
				if (removeList.size() > 0)
				{
					for (String key : removeList) 
					{
						if (dscMap.containsKey(key))
							dscMap.remove(key);
					}
				}
			}
		} 
		else 
		{
			try 
			{
				pdqAdapter.cancelQuery(queryTag, messageQueryName);
			} 
			catch (PdSupplierException e) 
			{
				throw new PixPdqException(e);
			}
		}
		
		return reply;
	}

	/**
	 * Validates the incoming Message. It validates in this order:
	 * <p>
	 * <ul>
	 * <li>Validate Triggering Event</li>
	 * <li>Validate Receiving Facility and Receiving Application</li>
	 * </ul>
	 * 
	 * @param reply
	 * @param hl7Header
	 * @param queryTag
	 * @throws HL7Exception
	 * @throws PixPdqException
	 */
	private boolean validateMessage(RSP_K21 reply, HL7Header hl7Header, String queryTag, Terser inTerser, Terser outTerser) throws HL7Exception,PixPdqException 
	{
		// 1. Valid triggering event:Q22 is for PDQ Query
		if (!hl7Header.getTriggerEvent().equals("Q22") && !hl7Header.getTriggerEvent().equals("ZV1")) 
		{
			HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
			
			HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
			
			HL7Util.echoQPD(outTerser, inTerser);
			
			HL7v25.populateERR(reply.getERR(), "MSH", "1", "9", "1", "2", "201", "Unsupported event code");
			
			return false;
		}
		
		// 2. Validate receiving facility and receiving application
		boolean isValid = validateReceivingFacilityApplication(reply, hl7Header, queryTag);
		
		if (!isValid) 
		{
			HL7Util.echoQPD(outTerser, inTerser);
			return false;
		}

		// Finally, it must be true when it reaches here
		return true;
	}
	
	private boolean validateVisitMessage(RSP_K31 reply, HL7Header hl7Header, String queryTag, Terser inTerser, Terser outTerser) throws HL7Exception,PixPdqException 
	{
		// 1. Valid triggering event:Q22 is for PDQ Query
		if (!hl7Header.getTriggerEvent().equals("Q22") && !hl7Header.getTriggerEvent().equals("ZV1")) 
		{
			HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
			
			HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
			
			HL7Util.echoQPD(outTerser, inTerser);
			
			HL7v25.populateERR(reply.getERR(), "MSH", "1", "9", "1", "2", "201", "Unsupported event code");
			
			return false;
		}
		
		// 2. Validate receiving facility and receiving application
		boolean isValid = validateVisitReceivingFacilityApplication(reply, hl7Header, queryTag);
		
		if (!isValid) 
		{
			HL7Util.echoQPD(outTerser, inTerser);
			return false;
		}

		// Finally, it must be true when it reaches here
		return true;
	}
		

	/**
	 * Creates a PID group for each matching patient which will be returned back to the PDQ consumer.
	 * 
	 * @param reply
	 *            the PDQ response message
	 * @param finalPatients
	 *            the final list of patients to be returned
	 * @param returnDomains
	 *            the patient domains the PDQ consumer is interested
	 * @throws HL7Exception
	 *             if any HL7 related message is wrong
	 * @throws PixPdqException
	 *             if application encounter any problem
	 */
	private void popluatePIDGroup(RSP_K21 reply, List<List<Patient>> finalPatients, List<Identifier> returnDomains) throws HL7Exception, PixPdqException 
	{
		for (int patientIndex = 0; patientIndex < finalPatients.size(); patientIndex++) 
		{
			List<Patient> patientRecord = finalPatients.get(patientIndex);
			
			// We grab the first patient descriptor as the patient demograhpics.			
			Patient patient = patientRecord.get(0);
			
			RSP_K21_QUERY_RESPONSE qr = reply.insertQUERY_RESPONSE(0);

			// RSP_K21 only contains one PID, so we need to add our own PID segment if more than one are needed
			PID pid = null;
			
			pid = qr.getPID();
			
			try 
			{
				int idIndex = populatePID(pid, patient, patientIndex+1, returnDomains);
				
				// For subsequent patient record, we only retrieve its patient id
				for (int i = 1; i < patientRecord.size(); i++) 
				{ // has to start with the second one, the first was handled above
					Patient pd = (Patient) patientRecord.get(i);
					
					List<PatientIdentifier> patientIds = pd.getPatientIds();
					
					for (PatientIdentifier patientId : patientIds) 
					{
						String id = patientId.getId();
						
						Identifier assigningAuthority = patientId.getAssigningAuthority();
						
						assigningAuthority = AssigningAuthorityUtil.reconcileIdentifier(assigningAuthority, actor.getActorDescription(), pdqAdapter);
						
						if (returnDomains.size() >= 1 && !returnDomains.contains(assigningAuthority))
							continue;
						
						// PID-3 - Preferred ID
						populateCX(pid.getPatientIdentifierList(idIndex++), id, assigningAuthority);
					}
				}

			} catch (IheConfigurationException e) 
			{
				throw new PixPdqException("Failed to populate PID segment", e);
				
			} catch (PatientException e) 
			{
				throw new PixPdqException("Failed to populate PID segment", e);
			}
		}
	}

	
	private void popluateVisitPIDGroup(RSP_K31 reply, List<List<Patient>> finalPatients, List<Identifier> returnDomains) throws HL7Exception, PixPdqException 
	{
		for (int patientIndex = 0; patientIndex < finalPatients.size(); patientIndex++) 
		{
			
			List<Patient> patientRecord = finalPatients.get(patientIndex);
			
			for(int i=0;i<patientRecord.get(0).getFpatientvisitlist().size();i++)
			{
				// We grab the first patient descriptor as the patient demograhpics.			
				Patient patient = patientRecord.get(0);
			
				RSP_K31_RESPONSE qr = reply.insertRESPONSE(0);

				// RSP_K21 only contains one PID, so we need to add our own PID segment if more than one are needed
				PID pid = null;
			
				PV1 pv1 = null;

				pid = qr.getPATIENT().getPID();
			
				pv1 = qr.getPATIENT().getPATIENT_VISIT().getPV1();
			
				try 
				{
					//填充PID段
					int idIndex = populatePID(pid, patient, patientIndex+1, returnDomains);
				
					//填充PV1段
					populateVisitPV1(pv1, patient, i+1, returnDomains);
					//pv1.getAdmitSource().setValue("23423423");

					// For subsequent patient record, we only retrieve its patient id
					for (int i1 = 1; i1 < patientRecord.size(); i1++) 
					{ // has to start with the second one, the first was handled above
						Patient pd = (Patient) patientRecord.get(i1);
					
						List<PatientIdentifier> patientIds = pd.getPatientIds();
					
						for (PatientIdentifier patientId : patientIds) 
						{
							String id = patientId.getId();
						
							Identifier assigningAuthority = patientId.getAssigningAuthority();
						
							assigningAuthority = AssigningAuthorityUtil.reconcileIdentifier(assigningAuthority, actor.getActorDescription(), pdqAdapter);
						
							if (returnDomains.size() >= 1 && !returnDomains.contains(assigningAuthority))
							continue;
						
							// PID-3 - Preferred ID
							populateCX(pid.getPatientIdentifierList(idIndex++), id, assigningAuthority);
						}
					}

				} catch (IheConfigurationException e) 
				{
					throw new PixPdqException("Failed to populate PID segment", e);
				
				} catch (PatientException e) 
				{
					throw new PixPdqException("Failed to populate PID segment", e);
				}
			}
		}
		
	}
	
	/**
	 * Audit Logging of PDQ Query Message.
	 * 
	 * @param patients
	 *            the patients returned
	 * @param hl7Header
	 *            the message header from the request
	 * @param queryTag
	 *            the query tag from the MSA segment of the PDQ request
	 * @param qpd
	 *            the QPD segment of the PDQ request
	 */
	private void auditLog(List<List<Patient>> patients, HL7Header hl7Header, String queryTag, QBP_Q21 message) 
	{
		if (actor.getAuditTrail() == null)
			return;

		// Source Application
		String userId = hl7Header.getSendingFacility().getNamespaceId() + "|" + hl7Header.getSendingApplication().getNamespaceId();
		
		String messageId = hl7Header.getMessageControlId();
		
		String sourceIp = "127.0.0.1";
		
		ActiveParticipant source = new ActiveParticipant(userId, messageId, sourceIp);

		// Patient Info
		Collection<ParticipantObject> pos = new ArrayList<ParticipantObject>();
		
		for (Collection<Patient> patientList : patients) 
		{
			for (Patient patient : patientList) 
			{
				ParticipantObject patientObj = new ParticipantObject(patient);
				
				pos.add(patientObj);
			}
		}
		
		// Query Info
		ParticipantObject queryObj = new ParticipantObject();
		
		queryObj.setId(queryTag);
		
		try 
		{
			queryObj.setQuery(PipeParser.encode(message, new EncodingCharacters('|', "^~\\&")));
		} 
		catch (HL7Exception e) 
		{
			log.warn("Failed to encode PDQ query for auditting", e);
		}
		queryObj.addDetail(new TypeValuePair("MSH-10", messageId));

		// Finally Log it.
		actor.getAuditTrail().logPdqQuery(source, pos, queryObj);
	}

	/**
	 * Processes the MSH segment.
	 * 
	 * @param requestMsh
	 *            the request MSH segment
	 * @param replyMsh
	 *            the reply MSH segment
	 * @param messageType
	 *            the message type
	 * @param event
	 *            the event id
	 * @param receivingApplication
	 *            it is expected to be the application of this PDQ server
	 * @param receivingFacility
	 *            it is expected to be the facility of this PDQ server
	 * @throws DataTypeException
	 */
	private void processMSH(HL7Header requestMsh, MSH replyMsh, String messageType, String event, String structure, Identifier receivingApplication,Identifier receivingFacility) throws DataTypeException 
	{
		Identifier idSendingApplication = requestMsh.getSendingApplication();
		
		Identifier idSendingFacility = requestMsh.getSendingFacility();
		
		// For the response message, the ReceivingApplication and ReceivingFacility will become the sendingApplication and sendingFacility,
		// Also the sendingApplication and sendingFacility will become the receivingApplication and receivingFacility.
		HL7v25.populateMSH(replyMsh, messageType, event, structure, getMessageControlId(), receivingApplication, receivingFacility, idSendingApplication,idSendingFacility);

	}

	/**
	 * Validates the receiving facility and receiving application of an incoming message.
	 * 
	 * @param reply
	 *            the reply message to be populated if any validation is failed
	 * @param inMsg
	 *            the incoming message
	 * @param queryTag
	 *            the query tag of the incoming PDQ query message
	 * @return <code>true</code> if validation is passed; otherwise <code>false</code>.
	 * @throws HL7Exception
	 *             if anything is wrong with the HL7 message format
	 * @throws PixPdqException
	 *             if anything is wrong the application
	 */
	private boolean validateReceivingFacilityApplication(RSP_K21 reply, HL7Header inMsg, String queryTag) throws HL7Exception, PixPdqException 
	{
		// Validate ReceivingApplication and ReceivingFacility.
		// Currently we are not validating SendingApplication and SendingFacility
		Identifier idReceivingApplication = inMsg.getReceivingApplication();
		
		Identifier idReceivingFacility = inMsg.getReceivingFacility();

		boolean validateApplication = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_APPLICATION);
		
		if (validateApplication && !idReceivingApplication.equals(getServerApplication(connection))) 
		{
			String inMsgControlId = inMsg.getMessageControlId();
			
			HL7v25.populateMSA(reply.getMSA(), "AE", inMsgControlId);
			
			HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");

			HL7v25.populateERR(reply.getERR(), "MSH", "1", "5", "1", "1", "204", "Unknown Receiving Application");
			
			return false;
		}

		boolean validateFacility = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_FACILITY);
		
		if (validateFacility && !idReceivingFacility.equals(getServerFacility(connection))) 
		{
			String inMsgControlId = inMsg.getMessageControlId();
			
			HL7v25.populateMSA(reply.getMSA(), "AE", inMsgControlId);
			
			HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");

			HL7v25.populateERR(reply.getERR(), "MSH", "1", "6", "1", "1", "204", "Unknown Receiving Facility");
			
			return false;
		}
		return true;
	}
	
	private boolean validateVisitReceivingFacilityApplication(RSP_K31 reply, HL7Header inMsg, String queryTag) throws HL7Exception, PixPdqException 
	{
		// Validate ReceivingApplication and ReceivingFacility.
		// Currently we are not validating SendingApplication and SendingFacility
		Identifier idReceivingApplication = inMsg.getReceivingApplication();
		
		Identifier idReceivingFacility = inMsg.getReceivingFacility();

		boolean validateApplication = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_APPLICATION);
		
		if (validateApplication && !idReceivingApplication.equals(getServerApplication(connection))) 
		{
			String inMsgControlId = inMsg.getMessageControlId();
			
			HL7v25.populateMSA(reply.getMSA(), "AE", inMsgControlId);
			
			HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");

			HL7v25.populateERR(reply.getERR(), "MSH", "1", "5", "1", "1", "204", "Unknown Receiving Application");
			
			return false;
		}

		boolean validateFacility = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_FACILITY);
		
		if (validateFacility && !idReceivingFacility.equals(getServerFacility(connection))) 
		{
			String inMsgControlId = inMsg.getMessageControlId();
			
			HL7v25.populateMSA(reply.getMSA(), "AE", inMsgControlId);
			
			HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");

			HL7v25.populateERR(reply.getERR(), "MSH", "1", "6", "1", "1", "204", "Unknown Receiving Facility");
			
			return false;
		}
		return true;
	}	
	

	/**
	 * Gets the record request number from RCP segment.
	 * 
	 * @param message
	 *            the QBP_Q21 request message
	 * @return the record number for the request. <code>-1</code> if there is no record number limit.
	 */
	private int getRecordRequestNumber(QBP_Q21 message)
	{
		RCP rcp = message.getRCP();
		
		String number = rcp.getQuantityLimitedRequest().getQuantity().getValue();
		
		int recordRequestNumber = -1; // -1 indicates no limit
		
		if (StringUtil.goodString(number)) 
		{
			try
			{
				recordRequestNumber = Integer.parseInt(number);
			} 
			catch (NumberFormatException e) 
			{
				log.warn("Invalid RCP record request number");
			}
		}
		return recordRequestNumber;
	}

	/**
	 * Gets the continuation pointer from DSC segment.
	 * 
	 * @param message
	 *            the QBP_Q21 request message
	 * @return the continuation pointer
	 */
	private String getContinuationPointer(QBP_Q21 message) 
	{
		DSC dsc = message.getDSC();
		
		String pointer = dsc.getContinuationPointer().getValue();
		
		return pointer;
	}

	/**
	 * Gets the value of a given component
	 * 
	 * @param data
	 *            the {@link GenericComposite}
	 * @param componentNum
	 *            the component number in the composite data
	 * @return
	 * @throws DataTypeException
	 */
	private Varies getComponentValue(GenericComposite data, int componentNum) throws DataTypeException {
		return (Varies) data.getComponent(componentNum);
	}

	/**
	 * Gets the return domain from the original PDQ request.
	 * 
	 * @param qpd
	 *            the request QPD segment
	 * @param reply
	 *            the response message
	 * @param messageControlId
	 *            the messageControlId for the response message
	 * @param queryTag
	 *            the queryTag of the request message
	 * @param outTerser
	 *            the out response message terser
	 * @param inTerser
	 *            the in request message terser
	 * @return return a list of returnDomain if not error. Otherwise, return null if there is an invalid domain or request syntax cannot be recognized.
	 * @throws HL7Exception
	 *             if there is anything wrong with the HL7 message format
	 */
	private List<Identifier> getReturnDomain(QPD qpd, RSP_K21 reply, String messageControlId, String queryTag, Terser outTerser, Terser inTerser)throws HL7Exception 
	{
		// 1. validate the return domains.
		// Get the return domains
		Type[] typeDomains = qpd.getField(8);
		
		List<Identifier> returnDomains = new ArrayList<Identifier>();
		
		for (int i = 0; i < typeDomains.length; i++) 
		{
			Varies domain = (Varies) typeDomains[i];
			
			Type data = domain.getData();
			
			if (data instanceof GenericComposite) 
			{
				Identifier aa = HL7v25.extractAssigningAuthority((GenericComposite) data);
				
				boolean validDomain = AssigningAuthorityUtil.validateDomain(aa, actor.getActorDescription(), pdqAdapter);
				
				if (validDomain) 
				{
					Identifier reconciledDomain = AssigningAuthorityUtil.reconcileIdentifier(aa, actor.getActorDescription(), pdqAdapter);
					
					returnDomains.add(reconciledDomain);
				} else 
				{
					// if a domain is invalid, return an error message
					HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
					
					HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
					
					HL7Util.echoQPD(outTerser, inTerser);
					
					// segmentId=QPD, sequence=1, fieldPosition=8, fieldRepetition=i+1, componentNubmer has to be empty
					HL7v25.populateERR(reply.getERR(), "QPD", "1", "8", Integer.toString(i + 1), null, "204", "域ID不在有效的域配置范围内");
					
					return null;
				}
			} else 
			{
				// return "Data type error"
				HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
				
				HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
				
				HL7Util.echoQPD(outTerser, inTerser);
				
				// segmentId=QPD, sequence=1, fieldPosition=8, fieldRepetition=null, componentNubmer=null
				HL7v25.populateERR(reply.getERR(), "QPD", "1", "8", null, null, "102", "Data type error");
				
				return null;
			}
		}
		return returnDomains;
	}
	
	private List<Identifier> getVisitReturnDomain(QPD qpd, RSP_K31 reply, String messageControlId, String queryTag, Terser outTerser, Terser inTerser)throws HL7Exception 
	{
		// 1. validate the return domains.
		// Get the return domains
		Type[] typeDomains = qpd.getField(8);
		
		List<Identifier> returnDomains = new ArrayList<Identifier>();
		
		for (int i = 0; i < typeDomains.length; i++) 
		{
			Varies domain = (Varies) typeDomains[i];
			
			Type data = domain.getData();
			
			if (data instanceof GenericComposite) 
			{
				Identifier aa = HL7v25.extractAssigningAuthority((GenericComposite) data);
				
				boolean validDomain = AssigningAuthorityUtil.validateDomain(aa, actor.getActorDescription(), pdqAdapter);
				
				if (validDomain) 
				{
					Identifier reconciledDomain = AssigningAuthorityUtil.reconcileIdentifier(aa, actor.getActorDescription(), pdqAdapter);
					
					returnDomains.add(reconciledDomain);
				} else 
				{
					// if a domain is invalid, return an error message
					HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
					
					HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
					
					HL7Util.echoQPD(outTerser, inTerser);
					
					// segmentId=QPD, sequence=1, fieldPosition=8, fieldRepetition=i+1, componentNubmer has to be empty
					HL7v25.populateERR(reply.getERR(), "QPD", "1", "8", Integer.toString(i + 1), null, "204", "Unknown Key Identifier");
					
					return null;
				}
			} else 
			{
				// return "Data type error"
				HL7v25.populateMSA(reply.getMSA(), "AE", messageControlId);
				
				HL7v25.populateQAK(reply.getQAK(), queryTag, "AE");
				
				HL7Util.echoQPD(outTerser, inTerser);
				
				// segmentId=QPD, sequence=1, fieldPosition=8, fieldRepetition=null, componentNubmer=null
				HL7v25.populateERR(reply.getERR(), "QPD", "1", "8", null, null, "102", "Data type error");
				
				return null;
			}
		}
		return returnDomains;
	}	
	
	

	/**
	 * Gets raw {@link PdqResult} from the EMPI.
	 * 
	 * @param qpd
	 *            the request QPD segment
	 * @param reply
	 *            the response message
	 * @param hl7Header
	 *            the incoming message header
	 * @param outTerser
	 *            the out response message terser
	 * @param inTerser
	 *            the in request message terser
	 * @param pointer
	 *            continuation pointer of the matching patient
	 * @param requestNo
	 *            the number of patient record to be returned for each request
	 * @param isContinatuation
	 *            <code>true</code> - if continuation query is handled by APIXPDQ; <code>false</code> - if continuation query is handled by EMPI.
	 * @param returnDomains
	 *            the domains in which patients are returned
	 * @return return a {@link PdqResult} object that contains a list of patients if not error. Otherwise, return null if the request syntax cannot be
	 *         recognized.
	 * @throws HL7Exception
	 * @throws PixPdqException
	 * @throws com.ats.aempi.ApplicationException 
	 * @throws PdSupplierException 
	 */
	private PdqResult getPdqResult(QPD qpd, RSP_K21 reply, HL7Header hl7Header, Terser outTerser, Terser inTerser, String pointer, int requestNo,boolean isContinuation, List<Identifier> returnDomains) throws PixPdqException, HL7Exception, PdSupplierException, com.ats.aempi.ApplicationException 
	{
		// First, process query parameters
		Type[] queries = qpd.getField(3); // The third one is the query parameters
		
		//System.out.println(queries[0].getMessage().toString().lastIndexOf("@PV1"));
		
		if (queries.length == 0 || queries[0].getMessage().toString().lastIndexOf("@PV1")>0) 
		{
			HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
			
			HL7v25.populateQAK(reply.getQAK(), qpd.getQueryTag().getValue(), "AE");
			
			HL7Util.echoQPD(outTerser, inTerser);
			
			// segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=null, componentNubmer=null
			HL7v25.populateERR(reply.getERR(), "QPD", "1", "3", null, null, "102", "Data type error");
			
			return null;
		}

		HashMap<String, String> parameters = new HashMap<String, String>();
		
		for (int i = 0; i < queries.length; i++) 
		{
			Varies parameter = (Varies) queries[i];
			
			Type data = parameter.getData();
			
			boolean validData = false;
			
			if (data instanceof GenericComposite) 
			{
				// The first component is parameter name
				Varies field = getComponentValue((GenericComposite) data, 0);
				
				String sField = ((GenericPrimitive) field.getData()).getValue();
				
				// The second component is parameter value
				Varies value = getComponentValue((GenericComposite) data, 1);
				
				String sValue = ((GenericPrimitive) value.getData()).getValue();
				
				if (sField != null) 
				{
					parameters.put(sField, sValue);
					
					validData = true;
				}
			}

			if (!validData) 
			{
				// return "Data type error"
				HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
				
				HL7v25.populateQAK(reply.getQAK(), qpd.getQueryTag().getValue(), "AE");
				
				HL7Util.echoQPD(outTerser, inTerser);
				
				// segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=null, componentNubmer=null
				
				HL7v25.populateERR(reply.getERR(), "QPD", "1", "3", null, null, "102", "Data type error");
				
				return null;
			}
		}

		PdqQuery query = processQuery(parameters);
		
		query.setQueryName(qpd.getMessageQueryName().getText().getValue());
		
		query.setQueryTag(qpd.getQueryTag().getValue());
		
		query.addReturnDomains(returnDomains);
		
		if (isContinuation) 
		{
			query.setContinuationPointer(pointer);
			
			query.setHowMany(requestNo);
		}
		
		MessageHeader header = hl7Header.toMessageHeader();
		
		PdqResult pdqResult = null;
		
		try 
		{
			pdqResult = pdqAdapter.findPatients(query, header);
			
		} catch (PdSupplierException e) 
		{
			log.error(e.getMessage());
			
			throw new PixPdqException(e);
		}

		if (pdqResult == null || pdqResult.getPatients() == null) 
		{
			log.error("Failed to get valid PDQ result.");
			
			throw new PixPdqException("Failed to get valid PDQ result.");
		}

		return pdqResult;
	}

	private PdqResult getPdqVisitResult(QPD qpd, RSP_K31 reply, HL7Header hl7Header, Terser outTerser, Terser inTerser, String pointer, int requestNo,boolean isContinuation, List<Identifier> returnDomains) throws PixPdqException, HL7Exception, PdSupplierException, com.ats.aempi.ApplicationException 
	{
		// First, process query parameters
		Type[] queries = qpd.getField(3); // The third one is the query parameters
		
		if (queries.length == 0) 
		{
			HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
			
			HL7v25.populateQAK(reply.getQAK(), qpd.getQueryTag().getValue(), "AE");
			
			HL7Util.echoQPD(outTerser, inTerser);
			
			// segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=null, componentNubmer=null
			HL7v25.populateERR(reply.getERR(), "QPD", "1", "3", null, null, "102", "Data type error");
			
			return null;
		}

		HashMap<String, String> parameters = new HashMap<String, String>();
		
		for (int i = 0; i < queries.length; i++) 
		{
			Varies parameter = (Varies) queries[i];
			
			Type data = parameter.getData();
			
			boolean validData = false;
			
			if (data instanceof GenericComposite) 
			{
				// The first component is parameter name
				Varies field = getComponentValue((GenericComposite) data, 0);
				
				String sField = ((GenericPrimitive) field.getData()).getValue();
				
				// The second component is parameter value
				Varies value = getComponentValue((GenericComposite) data, 1);
				
				String sValue = ((GenericPrimitive) value.getData()).getValue();
				
				if (sField != null) 
				{
					parameters.put(sField, sValue);
					
					validData = true;
				}
			}

			if (!validData) 
			{
				// return "Data type error"
				HL7v25.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());
				
				HL7v25.populateQAK(reply.getQAK(), qpd.getQueryTag().getValue(), "AE");
				
				HL7Util.echoQPD(outTerser, inTerser);
				
				// segmentId=QPD, sequence=1, fieldPosition=3, fieldRepetition=null, componentNubmer=null
				
				HL7v25.populateERR(reply.getERR(), "QPD", "1", "3", null, null, "102", "Data type error");
				
				return null;
			}
		}

		PdqQuery query = processQuery(parameters);
		
		query.setQueryName(qpd.getMessageQueryName().getText().getValue());
		
		query.setQueryTag(qpd.getQueryTag().getValue());
		
		query.addReturnDomains(returnDomains);
		
		if (isContinuation) 
		{
			query.setContinuationPointer(pointer);
			
			query.setHowMany(requestNo);
		}
		
		MessageHeader header = hl7Header.toMessageHeader();
		
		PdqResult pdqResult = null;
		
		try 
		{
			pdqResult = pdqAdapter.findPatients(query, header);
			
		} catch (PdSupplierException e) 
		{
			log.error(e.getMessage());
			
			throw new PixPdqException(e);
		}

		if (pdqResult == null || pdqResult.getPatients() == null) 
		{
			log.error("Failed to get valid PDQ result.");
			
			throw new PixPdqException("Failed to get valid PDQ result.");
		}

		return pdqResult;
	}
	/**
	 * Populates {@link PdqQuery} object with the query request parameters.
	 * 
	 * @param parameters
	 *            the PDQ query parameters
	 * @return the {@link PdqQuery} object
	 * @throws com.ats.aempi.ApplicationException 
	 * @throws PdSupplierException 
	 */
	@SuppressWarnings("null")
	private PdqQuery processQuery(HashMap<String, String> parameters) throws PixPdqException, PdSupplierException, com.ats.aempi.ApplicationException 
	{
		PdqQuery ret = new PdqQuery();
		
		try 
		{
			ret.setPrefix(Configuration.getPropertySetValue(connection, "QueryProperties", "WildcardBefore", false));
			
			ret.setSuffix(Configuration.getPropertySetValue(connection, "QueryProperties", "WildcardAfter", false));
		} 
		catch (IheConfigurationException e) 
		{
			throw new PixPdqException(e);
		}
		
		Address address = null;
		
		PhoneNumber phone = null;
		
		PersonName personName = null;
		
		PersonName maidenName = null;
		
		DriversLicense driversLicense = null;
		
		PatientIdentifier patientAccountNumber = null;
		
		PatientIdentifier patientIdentifier = null;

		Set<String> keys = parameters.keySet();
		
		@SuppressWarnings("unused")
		ExtendFields MyExtendValue=new ExtendFields();
		List<ExtendForPerson> ExtendList = new ArrayList<ExtendForPerson>();
		
		for (String key : keys)
		{
			String value = parameters.get(key);
			
			// PID-3 - Patient Identifier List
			if (key.equalsIgnoreCase("@PID.3.1")) 
			{
				if (patientIdentifier == null)
					patientIdentifier = new PatientIdentifier();
				    patientIdentifier.setId(value);
			} else if (key.equalsIgnoreCase("@PID.3.4")) 
			{
				if (patientIdentifier == null)
					patientIdentifier = new PatientIdentifier();
				patientIdentifier.setAssigningAuthority(new Identifier(value, null, null));
			} else if (key.equalsIgnoreCase("@PID.3.4.1")) 
			{
				if (patientIdentifier == null)
					patientIdentifier = new PatientIdentifier();
				Identifier aa = patientIdentifier.getAssigningAuthority();
				if (aa == null) 
				{
					aa = new Identifier(value, null, null);
				} else {
					aa.setNamespaceId(value);
				}
				patientIdentifier.setAssigningAuthority(aa);
			} else if (key.equalsIgnoreCase("@PID.3.4.2")) 
			{
				if (patientIdentifier == null)
					patientIdentifier = new PatientIdentifier();
				Identifier aa = patientIdentifier.getAssigningAuthority();
				if (aa == null) {
					aa = new Identifier(null, value, null);
				} else {
					aa.setUniversalId(value);
				}
				patientIdentifier.setAssigningAuthority(aa);
			} else if (key.equalsIgnoreCase("@PID.3.4.3")) 
			{
				if (patientIdentifier == null)
					patientIdentifier = new PatientIdentifier();
				Identifier aa = patientIdentifier.getAssigningAuthority();
				if (aa == null) {
					aa = new Identifier(null, null, value);
				} else {
					aa.setUniversalIdType(value);
				}
				patientIdentifier.setAssigningAuthority(aa);
			} else if (key.equalsIgnoreCase("@PID.3.5")) 
			{
				if (patientIdentifier == null)
					patientIdentifier = new PatientIdentifier();
				patientIdentifier.setIdentifierTypeCode(value);
			} else if (key.equalsIgnoreCase("@PID.3.6")) 
			{
				if (patientIdentifier == null)
					patientIdentifier = new PatientIdentifier();
				patientIdentifier.setAssigningFacility(new Identifier(value, null, null));
			}
			//PID-4 - 扩展身份识别信息，组嵌套方式
			else if(key.equalsIgnoreCase("@PID.4.1.1") || key.equalsIgnoreCase("@PID.4.1") || key.equalsIgnoreCase("@PID.4[1].1.1") || key.equalsIgnoreCase("@PID.4[1].1"))
			{
				MyExtendValue.setFields1(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[2].1.1") || key.equalsIgnoreCase("@PID.4[2].1"))
			{
				MyExtendValue.setFields2(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[3].1.1") || key.equalsIgnoreCase("@PID.4[3].1"))
			{
				MyExtendValue.setFields3(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[4].1.1") || key.equalsIgnoreCase("@PID.4[4].1"))
			{
				MyExtendValue.setFields4(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[5].1.1") || key.equalsIgnoreCase("@PID.4[5].1"))
			{
				MyExtendValue.setFields5(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[6].1.1") || key.equalsIgnoreCase("@PID.4[6].1"))
			{
				MyExtendValue.setFields6(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[7].1.1") || key.equalsIgnoreCase("@PID.4[7].1"))
			{
				MyExtendValue.setFields7(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[8].1.1") || key.equalsIgnoreCase("@PID.4[8].1"))
			{
				MyExtendValue.setFields8(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[9].1.1") || key.equalsIgnoreCase("@PID.4[9].1"))
			{
				MyExtendValue.setFields9(value);
			}
			else if(key.equalsIgnoreCase("@PID.4[10].1.1") || key.equalsIgnoreCase("@PID.4[10].1"))
			{
				MyExtendValue.setFields10(value);
			}
			// PID-5 - Patient Name
			/*
			 * Subcomponents for Family Name (FN): <Surname (ST)> & <Own Surname Prefix (ST)> & <Own Surname (ST)> & <Surname Prefix From Partner/Spouse (ST)> &
			 * <Surname From Partner/Spouse (ST)>
			 */
			else if (key.equalsIgnoreCase("@PID.5.1") || key.equalsIgnoreCase("@PID.5.1.1")) {
				if (personName == null)
					personName = new PersonName();
				personName.setLastName(value);
			} else if (key.equalsIgnoreCase("@PID.5.2") || key.equalsIgnoreCase("@PID.5.2.1")) {
				if (personName == null)
					personName = new PersonName();
				personName.setFirstName(value);
			} else if (key.equalsIgnoreCase("@PID.5.3") || key.equalsIgnoreCase("@PID.5.3.1")) {
				if (personName == null)
					personName = new PersonName();
				personName.setDegreeName(value);
			} else if (key.equalsIgnoreCase("@PID.5.4") || key.equalsIgnoreCase("@PID.5.4.1")) {
				if (personName == null)
					personName = new PersonName();
				personName.setSuffix(value);
			} else if (key.equalsIgnoreCase("@PID.5.5") || key.equalsIgnoreCase("@PID.5.5.1")) {
				if (personName == null)
					personName = new PersonName();
				personName.setPrefix(value);
			} else if (key.equalsIgnoreCase("@PID.5.6") || key.equalsIgnoreCase("@PID.5.6.1")) {
				if (personName == null)
					personName = new PersonName();
				personName.setDegree(value);
			} else if (key.equalsIgnoreCase("@PID.5.8") || key.equalsIgnoreCase("@PID.5.8.1")) {
				if (personName == null)
					personName = new PersonName();
				personName.setDegreeDomain(value);
			}
			// PID-6 - Maiden Name
			else if (key.equalsIgnoreCase("@PID.6.1") || key.equalsIgnoreCase("@PID.6")) {
				if (maidenName == null)
					maidenName = new PersonName();
				maidenName.setLastName(value);
			} else if (key.equalsIgnoreCase("@PID.6.2")) {
				if (maidenName == null)
					maidenName = new PersonName();
				maidenName.setFirstName(value);
			} else if (key.equalsIgnoreCase("@PID.6.3")) {
				if (maidenName == null)
					maidenName = new PersonName();
				maidenName.setSecondName(value);
			} else if (key.equalsIgnoreCase("@PID.6.4")) {
				if (maidenName == null)
					maidenName = new PersonName();
				maidenName.setSuffix(value);
			} else if (key.equalsIgnoreCase("@PID.6.5")) {
				if (maidenName == null)
					maidenName = new PersonName();
				maidenName.setPrefix(value);
			}
			// PID-7 - Birth date

			else if (key.equalsIgnoreCase("@PID.7.1") || key.equalsIgnoreCase("@PID.7.1.1")) {
				try {
					String birthdateFormat = HL7v25.birhtdateFormat;
					String formatFromProperty = Configuration.getPropertySetValue(connection, "DateTimeFormat", "Birthdate", false);
					if (StringUtil.goodString(formatFromProperty)) {
						birthdateFormat = formatFromProperty;
					}
					Calendar birthdate = DateUtil.parseCalendar(value, birthdateFormat);
					ret.setBirthDate(birthdate);
				} catch (IheConfigurationException e) {
					throw new PixPdqException(e);
				} catch (ParseException e) {
					throw new PixPdqException(e);
				}
			}
			// PID-8 - Gender 性别
			else if (key.equalsIgnoreCase("@PID.8.1") || key.equalsIgnoreCase("@PID.8.1.1")){
				ret.setSex(SharedEnums.SexType.getByString(value));
			//PID -9.1.1 监护人姓名
			}else if (key.equalsIgnoreCase("@PID.9.1") || key.equalsIgnoreCase("@PID.9.1.1")){
				ret.setGuardianPerson(value);
			// PID-10.1.1 - 种族编码
			}else if (key.equalsIgnoreCase("@PID.10.1") || key.equalsIgnoreCase("@PID.10.1.1")){
				ret.setRace(value);
			// PID-10.2.1 - 种族名称
			}else if (key.equalsIgnoreCase("@PID.10.2") || key.equalsIgnoreCase("@PID.10.2.1")){
				ret.setRaceName(value);
			// PID-10.3.1 - 种族编码系统
			}else if (key.equalsIgnoreCase("@PID.10.3") || key.equalsIgnoreCase("@PID.10.3.1")){
				ret.setRaceDomain(value);
			// PID-11.1.1居住家庭地址
			}else if (key.equalsIgnoreCase("@PID.11.1") || key.equalsIgnoreCase("@PID.11.1.1")){
				ret.setHomeAddress(value);
			// PID-11.3.1居住所在地市
			}else if (key.equalsIgnoreCase("@PID.11.3") || key.equalsIgnoreCase("@PID.11.3.1")){
				ret.setHomeCity(value);
			//PID-11.4.1居住所在地省
			}else if (key.equalsIgnoreCase("@PID.11.4") || key.equalsIgnoreCase("@PID.11.4.1")){
				ret.setHomeProvince(value);
			//PID-11.5.1居住所在地邮编
			}else if (key.equalsIgnoreCase("@PID.11.5") || key.equalsIgnoreCase("@PID.11.5.1")){
				ret.setHomeZip(value);
			//PID-11.9.1居住所在地区县
			}else if (key.equalsIgnoreCase("@PID.11.9") || key.equalsIgnoreCase("@PID.11.9.1")){
				ret.setHomeCounty(value);
			//PID-11[2].1.1户口地址
			}else if (key.equalsIgnoreCase("@PID.11[2].1") || key.equalsIgnoreCase("@PID.11[2].1.1")){
				ret.setRegisteredAddress(value);
			//PID-11[2].3.1户口所在地市
			}else if (key.equalsIgnoreCase("@PID.11[2].3") || key.equalsIgnoreCase("@PID.11[2].3.1")){
				ret.setRegisteredCity(value);
			//PID-11[2].4.1户口所在地省
			}else if (key.equalsIgnoreCase("@PID.11[2].4") || key.equalsIgnoreCase("@PID.11[2].4.1")){
				ret.setRegisteredProvince(value);
			//PID-11[2].5.1户口所在地邮编
			}else if (key.equalsIgnoreCase("@PID.11[2].5") || key.equalsIgnoreCase("@PID.11[2].5.1")){
				ret.setRegisteredZip(value);
			//PID-11[2].9.1户口所在地区县
			}else if (key.equalsIgnoreCase("@PID.11[2].9") || key.equalsIgnoreCase("@PID.11[5].9.1")){
				ret.setRegisteredCounty(value);
			//PID-11[3].1.1出生地址
			}else if (key.equalsIgnoreCase("@PID.11[3].1") || key.equalsIgnoreCase("@PID.11[3].1.1")){
				ret.setBirthPlace(value);
			//PID-11[3].3.1出生地址所在地市
			}else if (key.equalsIgnoreCase("@PID.11[30].3") || key.equalsIgnoreCase("@PID.11[3].3.1")){
				ret.setBirthCity(value);
			//PID-11[3].4.1出生地所在地省
			}else if (key.equalsIgnoreCase("@PID.11[3].4") || key.equalsIgnoreCase("@PID.11[3].4.1")){
				ret.setBirthCounty(value);
			//PID-11[3].5.1出生地所在地邮编
			}else if (key.equalsIgnoreCase("@PID.11[3].5") || key.equalsIgnoreCase("@PID.11[3].5.1")){
				ret.setBirthZip(value);
			//PID-11[3].9.1出生地所在地区县
			}else if (key.equalsIgnoreCase("@PID.11[3].9") || key.equalsIgnoreCase("@PID.11[3].9.1")){
				ret.setBirthCounty(value);
			//PID-11[4].3.1籍贯所在地市
			}else if (key.equalsIgnoreCase("@PID.11[4].3") || key.equalsIgnoreCase("@PID.11[4].3.1")){
				ret.setNativeCity(value);
			//PID-11[4].4.1籍贯所在地省
			}else if (key.equalsIgnoreCase("@PID.11[4].4") || key.equalsIgnoreCase("@PID.11[4].4.1")){
				ret.setNativeCity(value);
			//PID-11[5].1单位地址
			}else if (key.equalsIgnoreCase("@PID.11[5].1") || key.equalsIgnoreCase("@PID.11[5].1.1")){
				ret.setWorkAddress(value);
			//PID-11[5].2工作单位
			}else if (key.equalsIgnoreCase("@PID.11[5].2") || key.equalsIgnoreCase("@PID.11[5].2.1")){
				ret.setCompany(value);
			//PID-12.1职业编码
			}else if (key.equalsIgnoreCase("@PID.12.1") || key.equalsIgnoreCase("@PID.12.1.1")){
				ret.setProfession(value);
			//PID-13.1家庭电话
			}else if (key.equalsIgnoreCase("@PID.13.1") || key.equalsIgnoreCase("@PID.13.1.1")){
				ret.setHomeNumber(value);
			//PID-13.1邮箱地址
			}else if (key.equalsIgnoreCase("@PID.13.4") || key.equalsIgnoreCase("@PID.13.4.1")){
				ret.setEmail(value);
			//PID-13[2].1私人电话
			}else if (key.equalsIgnoreCase("@PID.13[2].1") || key.equalsIgnoreCase("@PID.13[2].1.1")){
				ret.setPrivateNumber(value);
			//PID-13[2].1单位电话
			}else if (key.equalsIgnoreCase("@PID.13[3].1") || key.equalsIgnoreCase("@PID.13[3].1.1")){
				ret.setWorkNumber(value);
			//PID-14.1职业名称
			}else if (key.equalsIgnoreCase("@PID.14.1") || key.equalsIgnoreCase("@PID.14.1.1")){
				ret.setProfessionName(value);
			//PID-14[2].1.1职业编码系统
			}else if (key.equalsIgnoreCase("@PID.14[2].1") || key.equalsIgnoreCase("@PID.14[2].1.1")){
				ret.setProfessionDomain(value);
			//PID-14[3].1.1性别名称
			}else if (key.equalsIgnoreCase("@PID.14[3].1") || key.equalsIgnoreCase("@PID.14[3].1.1")){
				ret.setGenderName(value);
			//PID-14[4].1.1性别编码系统
			}else if (key.equalsIgnoreCase("@PID.14[4].1") || key.equalsIgnoreCase("@PID.14[4].1.1")){
				ret.setGenderDomain(value);
			//PID-16.1.1婚姻状况编码
			}else if (key.equalsIgnoreCase("@PID.16.1") || key.equalsIgnoreCase("@PID.16.1.1")){
				ret.setMaritalStatus(value);
			//PID-16.2.1婚姻状况名称
			}else if (key.equalsIgnoreCase("@PID.16.2") || key.equalsIgnoreCase("@PID.16.2.1")){
				ret.setMaritalStatusName(value);
			//PID-16.3.1婚姻状况编码系统
			}else if (key.equalsIgnoreCase("@PID.16.3") || key.equalsIgnoreCase("@PID.16.3.1")){
				ret.setMaritalDomain(value);
			//PID-19.1.1社保卡号
			}else if (key.equalsIgnoreCase("@PID.19.1") || key.equalsIgnoreCase("@PID.19.1.1")){
				ret.setSsn(value);
			//PID-20.1.1身份证件号
			}else if (key.equalsIgnoreCase("@PID.20.1") || key.equalsIgnoreCase("@PID.20.1.1")){
				ret.setIdentityNO(value);
			//PID-21.1.1医保卡号
			}else if (key.equalsIgnoreCase("@PID.21.1") || key.equalsIgnoreCase("@PID.21.1.1")){
				ret.setInsuranceNO(value);
			//PID-22.1.1民族编码
			}else if (key.equalsIgnoreCase("@PID.22.1") || key.equalsIgnoreCase("@PID.22.1.1")){
				ret.setEthnicGroup(value);
			//PID-22.2.1民族名称
			}else if (key.equalsIgnoreCase("@PID.22.2") || key.equalsIgnoreCase("@PID.22.2.1")){
				ret.setEthnicName(value);
			//PID-22.3.1民族编码系统
			}else if (key.equalsIgnoreCase("@PID.22.3") || key.equalsIgnoreCase("@PID.22.3.1")){
				ret.setEthincDomain(value);
			//PID-28.1.1国籍编码
			}else if (key.equalsIgnoreCase("@PID.28.1") || key.equalsIgnoreCase("@PID.28.1.1")){
				ret.setNationality(value);
			//PID-28.2.1国籍名称
			}else if (key.equalsIgnoreCase("@PID.28.2") || key.equalsIgnoreCase("@PID.28.2.1")){
				ret.setNationalityName(value);
			//PID-28.3.1国籍编码系统
			}else if (key.equalsIgnoreCase("@PID.28.3") || key.equalsIgnoreCase("@PID.28.3.1")){
				ret.setNationalityDomain(value);
			//PID-30.1.1保密
			}else if (key.equalsIgnoreCase("@PID.30.1") || key.equalsIgnoreCase("@PID.30.1.1")){
				ret.setVip(value);
			//PID
			}else if (key.equalsIgnoreCase("@PID.18.1")) {
				if (patientAccountNumber == null)
					patientAccountNumber = new PatientIdentifier();
				patientAccountNumber.setId(value);
			} else if (key.equalsIgnoreCase("@PID.18.4")) {
				if (patientAccountNumber == null)
					patientAccountNumber = new PatientIdentifier();
				patientAccountNumber.setAssigningAuthority(new Identifier(value, null, null));
			} else if (key.equalsIgnoreCase("@PID.18.4.1")) {
				if (patientAccountNumber == null)
					patientAccountNumber = new PatientIdentifier();
				Identifier aa = patientAccountNumber.getAssigningAuthority();
				if (aa == null) {
					aa = new Identifier(value, null, null);
				} else {
					aa.setNamespaceId(value);
				}
				patientAccountNumber.setAssigningAuthority(aa);
			} else if (key.equalsIgnoreCase("@PID.18.4.2")) {
				if (patientAccountNumber == null)
					patientAccountNumber = new PatientIdentifier();
				Identifier aa = patientAccountNumber.getAssigningAuthority();
				if (aa == null) {
					aa = new Identifier(null, value, null);
				} else {
					aa.setUniversalId(value);
				}
				patientAccountNumber.setAssigningAuthority(aa);
			} else if (key.equalsIgnoreCase("@PID.18.4.3")) {
				if (patientAccountNumber == null)
					patientAccountNumber = new PatientIdentifier();
				Identifier aa = patientAccountNumber.getAssigningAuthority();
				if (aa == null) {
					aa = new Identifier(null, null, value);
				} else {
					aa.setUniversalIdType(value);
				}
				patientAccountNumber.setAssigningAuthority(aa);
			} else if (key.equalsIgnoreCase("@PID.18.5")) {
				if (patientAccountNumber == null)
					patientAccountNumber = new PatientIdentifier();
				patientAccountNumber.setIdentifierTypeCode(value);
			} else if (key.equalsIgnoreCase("@PID.18.6")) {
				if (patientAccountNumber == null)
					patientAccountNumber = new PatientIdentifier();
				patientAccountNumber.setAssigningFacility(new Identifier(value, null, null));
			}
			// PID-24 Multiple Birth Indicator
			else if (key.equalsIgnoreCase("@PID.24.1") || key.equalsIgnoreCase("@PID.24")){
				ret.setMultipleBirthIndicator(value);
			}
			//PID-25 Birth Order
			else if (key.equalsIgnoreCase("@PID.25.1") || key.equalsIgnoreCase("@PID.25")){
				ret.setBirthOrder(Integer.parseInt(value));
			}	
			// PID-20 Driver's License
			else if (key.equalsIgnoreCase("@PID.20") || key.equalsIgnoreCase("@PID.20.1")) {
				if (driversLicense == null)
					driversLicense = new DriversLicense();
				driversLicense.setLicenseNumber(value);
			} else if (key.equalsIgnoreCase("@PID.20.2")) {
				if (driversLicense == null)
					driversLicense = new DriversLicense();
				driversLicense.setIssuingState(value);
			} else if (key.equalsIgnoreCase("@PID.20.3")) {
				try 
				{
					String birthdateFormat = HL7v25.birhtdateFormat;
					Calendar birthdate = DateUtil.parseCalendar(value, birthdateFormat);
					if (driversLicense == null)
						driversLicense = new DriversLicense();
					driversLicense.setExpirationDate(birthdate);
				} catch (ParseException e) 
				{
					throw new PixPdqException(e);
				}			
			}
			//pv1-信息,
			else if(key.equalsIgnoreCase("@PV1.2.1") || key.equalsIgnoreCase("@PV1.2")){
				 ret.setPatCategory(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.1") || key.equalsIgnoreCase("@PV1.3.1.1")){
				 ret.setPatCurrentPointOfCare(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.2") || key.equalsIgnoreCase("@PV1.3.2.1")){
				 ret.setPatCurrentRoom(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.3") || key.equalsIgnoreCase("@PV1.3.3.1")){
				 ret.setPatCurrentBed(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.4") || key.equalsIgnoreCase("@PV1.3.4.1")){
				 ret.setPatCuurentDep(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.5") || key.equalsIgnoreCase("@PV1.3.5.1")){
				 ret.setPatCurrentPositionStatus(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.6") || key.equalsIgnoreCase("@PV1.3.6.1")){
				 ret.setPatCurrentPositionType(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.7") || key.equalsIgnoreCase("@PV1.3.7.1")){
				 ret.setPatCurrentBuilding(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.8") || key.equalsIgnoreCase("@PV1.3.8.1")){
				 ret.setPatCurrentFloor(value);
			}
			else if(key.equalsIgnoreCase("@PV1.3.9") || key.equalsIgnoreCase("@PV1.3.9.1")){
				 ret.setPatCuurentDescription(value);
			}
			else if(key.equalsIgnoreCase("@PV1.4.1") || (key.equalsIgnoreCase("@PV1.4.1.1"))){
				 ret.setPatAdmissionType(value);
			}
			else if(key.equalsIgnoreCase("@PV1.5.1") || (key.equalsIgnoreCase("@PV1.5.1.1"))){
				 ret.setPatAdmissionNumber(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.1") || key.equalsIgnoreCase("@PV1.6.1.1")){
				 ret.setPatFormerPointOfCare(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.2") || key.equalsIgnoreCase("@PV1.6.2.1")){
				 ret.setPatFormerRoom(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.3") || key.equalsIgnoreCase("@PV1.6.3.1")){
				 ret.setPatFormerBed(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.4") || key.equalsIgnoreCase("@PV1.6.4.1")){
				 ret.setPatFormerDep(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.5") || key.equalsIgnoreCase("@PV1.6.5.1")){
				 ret.setPatFormerPositionStatus(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.6") || key.equalsIgnoreCase("@PV1.6.6.1")){
				 ret.setPatFormerPositionType(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.7") || key.equalsIgnoreCase("@PV1.6.7.1")){
				 ret.setPatFormerBuilding(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.8") || key.equalsIgnoreCase("@PV1.6.8.1")){
				 ret.setPatFormerFloor(value);
			}
			else if(key.equalsIgnoreCase("@PV1.6.9") || key.equalsIgnoreCase("@PV1.6.9.1")){
				 ret.setPatFormerDescription(value);
			}
			else if(key.equalsIgnoreCase("@PV1.7.1") || key.equalsIgnoreCase("@PV1.7.1.1")){
				 ret.setAdmissionsDoctorId(value);
			}
			else if(key.equalsIgnoreCase("@PV1.7.2") || key.equalsIgnoreCase("@PV1.7.2.1")){
				 ret.setAdmissionsDoctor(value);
			}
			else if(key.equalsIgnoreCase("@PV1.8.1") || key.equalsIgnoreCase("@PV1.8.1.1")){
				 ret.setReferringDoctorId(value);
			}
			else if(key.equalsIgnoreCase("@PV1.8.2") || key.equalsIgnoreCase("@PV1.8.2.1")){
				 ret.setReferringDoctor(value);
			}
			else if(key.equalsIgnoreCase("@PV1.9.1") || key.equalsIgnoreCase("@PV1.9.1.1")){
				 ret.setConsultationDoctorId(value);
			}
			else if(key.equalsIgnoreCase("@PV19.2") || key.equalsIgnoreCase("@PV1.9.2.1")){
				 ret.setConsultationDoctor(value);
			}
			else if(key.equalsIgnoreCase("@PV1.10") || (key.equalsIgnoreCase("@PV1.10.1"))){
				 ret.setHospitalService(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.1") || key.equalsIgnoreCase("@PV1.11.1.1")){
				 ret.setPatTempPointOfCare(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.2") || key.equalsIgnoreCase("@PV1.11.2.1")){
				 ret.setPatTempRoom(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.3") || key.equalsIgnoreCase("@PV1.11.3.1")){
				 ret.setPatTempBed(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.4") || key.equalsIgnoreCase("@PV1.11.4.1")){
				 ret.setPatDeterDep(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.5") || key.equalsIgnoreCase("@PV1.11.5.1")){
				 ret.setPatTempPositionStatus(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.6") || key.equalsIgnoreCase("@PV1.11.6.1")){
				 ret.setPatTempPositionType(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.7") || key.equalsIgnoreCase("@PV1.11.7.1")){
				 ret.setPatTempBuilding(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.8") || key.equalsIgnoreCase("@PV1.11.8.1")){
				 ret.setPatTempFloor(value);
			}
			else if(key.equalsIgnoreCase("@PV1.11.9") || key.equalsIgnoreCase("@PV1.11.9.1")){
				 ret.setPatTempDescription(value);
			}
			else if(key.equalsIgnoreCase("@PV1.12.1") || (key.equalsIgnoreCase("@PV1.12"))){
				 ret.setPatAdmissionTest(value);
			}
			else if(key.equalsIgnoreCase("@PV1.13.1") || (key.equalsIgnoreCase("@PV1.13"))){
				 ret.setPatReAdmission(value);
			}
			else if(key.equalsIgnoreCase("@PV1.14.1") || (key.equalsIgnoreCase("@PV1.14"))){
				 ret.setPatAdmissionSource(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15.1") || (key.equalsIgnoreCase("@PV1.15"))){
				 ret.setDischargeName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[2].1") || (key.equalsIgnoreCase("@PV1.15[2].1.1"))){
				 ret.setDischargeDomain(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[3].1") || (key.equalsIgnoreCase("@PV1.15[3].1.1"))){
				 ret.setAdmissionName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[4].1") || (key.equalsIgnoreCase("@PV1.15[4].1.1"))){
				 ret.setAdmissionDomain(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[5].1") || (key.equalsIgnoreCase("@PV1.15[5].1.1"))){
				 ret.setIpStatusName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[6].1") || (key.equalsIgnoreCase("@PV1.15[6].1.1"))){
				 ret.setIpStatusDomain(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[7].1") || (key.equalsIgnoreCase("@PV1.15[7].1.1"))){
				 ret.setDificultyName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[8].1") || (key.equalsIgnoreCase("@PV1.15[8].1.1"))){
				 ret.setDificultyDomain(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[9].1") || (key.equalsIgnoreCase("@PV1.15[9].1.1"))){
				 ret.setAdmissionSourceName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[10].1") || (key.equalsIgnoreCase("@PV1.15[10].1.1"))){
				 ret.setAdmissionSourceDomain(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[11].1") || (key.equalsIgnoreCase("@PV1.15[11].1.1"))){
				 ret.setAccountStatusName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[12].1") || (key.equalsIgnoreCase("@PV1.15[12].1.1"))){
				 ret.setAccountStatusDomain(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[13].1") || (key.equalsIgnoreCase("@PV1.15[13].1.1"))){
				 ret.setPatCategoryName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.15[14].1") || (key.equalsIgnoreCase("@PV1.15[14].1.1"))){
				 ret.setPatCategorySystem(value);
			}
			else if(key.equalsIgnoreCase("@PV1.16.1") || (key.equalsIgnoreCase("@PV1.16"))){
				 ret.setPatVip(value);
			}
			else if(key.equalsIgnoreCase("@PV1.17.1") || key.equalsIgnoreCase("@PV1.17.1.1")){
				 ret.setPatAdmissionDoctorsId(value);
			}
			else if(key.equalsIgnoreCase("@PV1.17.2") || key.equalsIgnoreCase("@PV1.17.2.1")){
				 ret.setPatAdmissionDoctors(value);
			}
			else if(key.equalsIgnoreCase("@PV1.18.1") || (key.equalsIgnoreCase("@PV1.18.1.1"))){
				 ret.setPatientClass(value);
			}
			else if(key.equalsIgnoreCase("@PV1.19.1") || (key.equalsIgnoreCase("@PV1.19.1.1"))){
				 ret.setPatientId(value);
			}
			else if(key.equalsIgnoreCase("@PV1.20.1") || (key.equalsIgnoreCase("@PV1.20.1.1"))){
				 ret.setPatFinancialClass(value);
			}
			else if(key.equalsIgnoreCase("@PV1.21.1") || (key.equalsIgnoreCase("@PV1.21.1.1"))){
				 ret.setRoomBedCostPrice(value);
			}
			else if(key.equalsIgnoreCase("@PV1.22.1") || (key.equalsIgnoreCase("@PV1.22.1.1"))){
				 ret.setCourtesyCode(value);
			}
			else if(key.equalsIgnoreCase("@PV1.23.1") || (key.equalsIgnoreCase("@PV1.23.1.1"))){
				 ret.setCreditRating(value);
			}
			else if(key.equalsIgnoreCase("@PV1.24.1") || (key.equalsIgnoreCase("@PV1.24.1.1"))){
				 ret.setContractCode(value);
			}
			else if(key.equalsIgnoreCase("@PV1.25.1") || (key.equalsIgnoreCase("@PV1.25.1.1"))){
				 ret.setContractCreateDate(value);
			}
			else if(key.equalsIgnoreCase("@PV1.26.1") || (key.equalsIgnoreCase("@PV1.26.1.1"))){
				 ret.setContractPrice(value);
			}
			else if(key.equalsIgnoreCase("@PV1.27.1") || (key.equalsIgnoreCase("@PV1.27.1.1"))){
				 ret.setContractTime(value);
			}
			else if(key.equalsIgnoreCase("@PV1.28.1") || (key.equalsIgnoreCase("@PV1.28.1.1"))){
				 ret.setInterestRateCode(value);
			}
			else if(key.equalsIgnoreCase("@PV1.29.1") || (key.equalsIgnoreCase("@PV1.29.1.1"))){
				 ret.setBadDebts(value);
			}
			else if(key.equalsIgnoreCase("@PV1.30.1") || (key.equalsIgnoreCase("@PV1.30.1.1"))){
				 ret.setBadDebtsCreateDate(value);
			}
			else if(key.equalsIgnoreCase("@PV1.31.1") || (key.equalsIgnoreCase("@PV1.31.1.1"))){
				 ret.setBadDebtsCode(value);
			}
			else if(key.equalsIgnoreCase("@PV1.32.1") || (key.equalsIgnoreCase("@PV1.32.1.1"))){
				 ret.setBadDebtsPrice(value);
			}
			else if(key.equalsIgnoreCase("@PV1.33.1") || (key.equalsIgnoreCase("@PV1.33.1.1"))){
				 ret.setBadDebtsRestorePrice(value);
			}
			else if(key.equalsIgnoreCase("@PV1.34.1") || (key.equalsIgnoreCase("@PV1.34.1.1"))){
				 ret.setPatAccountVoided(value);
			}
			else if(key.equalsIgnoreCase("@PV1.35.1") || (key.equalsIgnoreCase("@PV1.35.1.1"))){
				 ret.setPatAccountVoidedDate(value);
			}
			else if(key.equalsIgnoreCase("@PV1.36.1") || (key.equalsIgnoreCase("@PV1.36.1.1"))){
				 ret.setPatDischargeDisposition(value);
			}
			else if(key.equalsIgnoreCase("@PV1.37.1") || (key.equalsIgnoreCase("@PV1.37.1.1"))){
				 ret.setPatDischargeLocation(value);
			}
			else if(key.equalsIgnoreCase("@PV1.38.1") || (key.equalsIgnoreCase("@PV1.38.1.1"))){
				 ret.setPatDietType(value);
			}
			else if(key.equalsIgnoreCase("@PV1.39.1") || (key.equalsIgnoreCase("@PV1.39.1.1"))){
				 ret.setPatServiceAgencies(value);
			}
			else if(key.equalsIgnoreCase("@PV1.40.1") || (key.equalsIgnoreCase("@PV1.40.1.1"))){
				 ret.setPatBedStatus(value);
			}
			else if(key.equalsIgnoreCase("@PV1.41.1") || (key.equalsIgnoreCase("@PV1.41.1.1"))){
				 ret.setPatAccountStatus(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.1") || key.equalsIgnoreCase("@PV1.42.1.1")){
				 ret.setPatDeterPointOfCare(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.2") || key.equalsIgnoreCase("@PV1.42.2.1")){
				 ret.setPatDeterRoom(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.3") || key.equalsIgnoreCase("@PV1.42.3.1")){
				 ret.setPatDeterBed(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.4") || key.equalsIgnoreCase("@PV1.42.4.1")){
				 ret.setPatDeterDep(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.5") || key.equalsIgnoreCase("@PV1.42.5.1")){
				 ret.setTend(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.6") || key.equalsIgnoreCase("@PV1.42.6.1")){
				 ret.setPatNurseCode(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.7") || key.equalsIgnoreCase("@PV1.42.7.1")){
				 ret.setPatNurseName(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.8") || key.equalsIgnoreCase("@PV1.42.8.1")){
				 ret.setOperCode(value);
			}
			else if(key.equalsIgnoreCase("@PV1.42.9") || key.equalsIgnoreCase("@PV1.42.9.1")){
				ret.setOperDate(DateUtil.convertHL7Date(value));
			}
			else if(key.equalsIgnoreCase("@PV1.43.1") || key.equalsIgnoreCase("@PV1.43.1.1")){
				 ret.setPatForTempPointOfCare(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.2") || key.equalsIgnoreCase("@PV1.43.2.1")){
				 ret.setPatForTempRoom(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.3") || key.equalsIgnoreCase("@PV1.43.3.1")){
				 ret.setPatForTempBed(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.4") || key.equalsIgnoreCase("@PV1.43.4.1")){
				 ret.setPatForTempDep(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.5") || key.equalsIgnoreCase("@PV1.43.5.1")){
				 ret.setPatForTempPositionStatus(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.6") || key.equalsIgnoreCase("@PV1.43.6.1")){
				 ret.setPatForTempPositionType(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.7") || key.equalsIgnoreCase("@PV1.43.7.1")){
				 ret.setPatForTempBuilding(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.8") || key.equalsIgnoreCase("@PV1.43.8.1")){
				 ret.setPatForTempFloor(value);
			}
			else if(key.equalsIgnoreCase("@PV1.43.9") || key.equalsIgnoreCase("@PV1.43.9.1")){
				 ret.setPatForTempDescription(value);
			}
			else if(key.equalsIgnoreCase("@PV1.44.1") || (key.equalsIgnoreCase("@PV1.44.1.1"))){
				 ret.setAdmitDate(value);
			}
			else if(key.equalsIgnoreCase("@PV1.45.1") || (key.equalsIgnoreCase("@PV1.45.1.1"))){
				 ret.setDischargeDate(value);
			}
			else if(key.equalsIgnoreCase("@PV1.46.1") || (key.equalsIgnoreCase("@PV1.46.1.1"))){
				 ret.setPatDifference(value);
			}
			else if(key.equalsIgnoreCase("@PV1.47.1") || (key.equalsIgnoreCase("@PV1.47.1.1"))){
				 ret.setPatTotalCost(value);
			}
			else if(key.equalsIgnoreCase("@PV1.48.1") || (key.equalsIgnoreCase("@PV1.48.1.1"))){
				 ret.setPatTotalDispatch(value);
			}
			else if(key.equalsIgnoreCase("@PV1.49.1") || (key.equalsIgnoreCase("@PV1.49.1.1"))){
				 ret.setPatTotalAmountPayable(value);
			}
			else if(key.equalsIgnoreCase("@PV1.50.1") || (key.equalsIgnoreCase("@PV1.50.1.1"))){
				 ret.setBabyFlag(value);
			}
			else if(key.equalsIgnoreCase("@PV1.50.3") || (key.equalsIgnoreCase("@PV1.50.3.1"))){
				 ret.setAdmitWeight(value);
			}
			else if(key.equalsIgnoreCase("@PV1.50.4") || (key.equalsIgnoreCase("@PV1.50.4.1"))){
				 ret.setAdmitWeightUnit(value);
			}
			else if(key.equalsIgnoreCase("@PV1.50.5") || (key.equalsIgnoreCase("@PV1.50.5.1"))){
				 ret.setBirthWeight(value);
			}
			else if(key.equalsIgnoreCase("@PV1.50.6") || (key.equalsIgnoreCase("@PV1.50.6.1"))){
				 ret.setBabyWeightUnit(value);
			}
			else if(key.equalsIgnoreCase("@PV1.51.1.1") || (key.equalsIgnoreCase("@PV1.51.1"))){
				 ret.setPatVisitLogo(value);
			}
			else if(key.equalsIgnoreCase("@PV1.52.1.1") || (key.equalsIgnoreCase("@PV1.52.1"))){
				 ret.setOtherMedicalInstitutions(value);
			}
		} // end for
		if (patientIdentifier != null)
			ret.setPatientIdentifier(patientIdentifier);
		if (patientAccountNumber != null)
			ret.setPatientAccountNumber(patientAccountNumber);
		if (personName != null)
			ret.setPersonName(personName);
		if (maidenName != null)
			ret.setMotherMaidenName(maidenName);
		if (address != null)
			ret.setAddress(address);
		if (phone != null)
			ret.setPhone(phone);
		if (driversLicense != null)
		{
			ret.setDriversLicense(driversLicense);
		   
		    ret.setIdentityNO(driversLicense.getLicenseNumber());
		}
		
		if(MyExtendValue.getFields1()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(1);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields1());
			}
		}
		
		if(MyExtendValue.getFields2()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(2);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields2());
			}
		}
		
		if(MyExtendValue.getFields3()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(3);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields3());
			}
		}
		
		if(MyExtendValue.getFields4()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(4);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields4());
			}
		}
		
		if(MyExtendValue.getFields5()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(5);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields5());
			}
		}
		
		if(MyExtendValue.getFields6()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(6);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields6());
			}
		}
		
		if(MyExtendValue.getFields7()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(7);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields7());
			}
		}
		
		if(MyExtendValue.getFields8()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(8);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields8());
			}
		}
		
		if(MyExtendValue.getFields9()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(9);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields9());
			}
		}
		
		if(MyExtendValue.getFields10()!=null)
		{
			ExtendList=pdqAdapter.findExtendFields(10);
			
			if(ExtendList.get(0).getExtendfieldname()!=null)
			{
				ret=SetExtendFields(ret,ExtendList.get(0).getExtendfieldname(),MyExtendValue.getFields10());
			}
		}
		
		MyExtendValue = null;
		
		ExtendList = null;
		
		return ret;
	}
	
	private PdqQuery SetExtendFields(PdqQuery temp,String myextendfields,String value)
	{
		if(myextendfields.equalsIgnoreCase("CUSTOM6"))
		{
			temp.setCustom6(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM7"))
		{
			temp.setCustom7(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM8"))
		{
			temp.setCustom8(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM9"))
		{
			temp.setCustom9(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM13"))
		{
			temp.setCustom13(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM14"))
		{
			temp.setCustom14(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM15"))
		{
			temp.setCustom15(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM18"))
		{
			temp.setCustom18(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM19"))
		{
			temp.setCustom19(value);
		}
		if(myextendfields.equalsIgnoreCase("CUSTOM20"))
		{
			temp.setCustom20(value);
		}
		return temp;
	}

	/**
	 * Populates the PID PV1 segment of the Patient Demographics Query Response.
	 * <p>
	 * See IHE ITI-TF vol 2, section 3.21.4.2.2.5 and HL7 v2.5 chapter 3
	 * 
	 * @param pid pv1
	 *            the PID PV1 segment to be populated
	 * @param patient
	 *            the patient demographic data
	 * @param returnDomains
	 *            a list of domains whose patient record to be returned
	 * @return the number of matching IDs in this PID segment
	 * @throws IheConfigurationException
	 *             When this connection is not properly configured to encode messages
	 * @throws PatientException
	 *             When required patient information is missing
	 * @throws HL7Exception
	 *             When the patient information cannot be encoded properly into HL7
	 */
	private int populatePID(PID pid, Patient patient, int setId, List<Identifier> returnDomains) throws HL7Exception, IheConfigurationException,PatientException 
	{
		int numberOfPatientId = 0;

		List<PatientIdentifier> pids = patient.getPatientIds();
		
		// PID-1 Set ID - PID (SI) 00104
		if (setId >= 1)
		{
			pid.getSetIDPID().setValue(Integer.toString(setId));
		}
		
		// PID-2 - Deprecated ID, our authority makes this ID > 20 characters, the allowed limit
		//populateCX(pid.getPatientID(), patientId, authority);
		
		// PID-3 - Preferred ID
		for (int i = 0; i < pids.size(); i++) 
		{
			PatientIdentifier id = pids.get(i);
			
			Identifier authority = id.getAssigningAuthority();
			
			authority = AssigningAuthorityUtil.reconcileIdentifier(authority, actor.getActorDescription(), pdqAdapter);
			
			if (returnDomains.size() >= 1 && !returnDomains.contains(authority))
				continue;
			
			populateCX(pid.getPatientIdentifierList(numberOfPatientId++), id.getId(), authority);
		}
		
		// PID-5 - Patient legal name
		XPN xpn = pid.getPatientName(0);
		
		populateXPN(xpn, patient.getPatientName());
		
		// PID-6 - Mother's maiden name, we have only last name
		if (null != patient.getMothersMaidenName()) 
		{
			xpn = pid.getMotherSMaidenName(0);
			
			PersonName name = patient.getMothersMaidenName();
			
			populateXPN(xpn, name);
		}
		
		// PID-7 - Birth date
		if (patient.getBirthDateTime() != null) 
		{
			String birthdateFormat = HL7v25.birhtdateFormat;
			
			String formatFromProperty = Configuration.getPropertySetValue(connection, "DateTimeFormat", "Birthdate", false);
			
			if (StringUtil.goodString(formatFromProperty)) 
			{
				birthdateFormat = formatFromProperty;
			}
			
			String date = new SimpleDateFormat(birthdateFormat).format(patient.getBirthDateTime().getTime());
			
			pid.getDateTimeOfBirth().getTime().setValue(date);
		}
		
		// PID-8 - Gender
		String gender = "U";
		
		if (patient.getAdministrativeSex() == SharedEnums.SexType.MALE)
		{
			gender = "M";
		}
		else if (patient.getAdministrativeSex() == SharedEnums.SexType.FEMALE)
		{
			gender = "F";
		}
		else if (patient.getAdministrativeSex() == SharedEnums.SexType.OTHER)
		{
			gender = "O";
		}
		
		pid.getAdministrativeSex().setValue(gender);
		
		int i = 0;
		
		// PID-9 - Patient name aliases
		if (patient.getGuardianPerson() != null) 
		{
				pid.getPatientAlias(0).getXpn1_FamilyName().getFn1_Surname().setValue(patient.getGuardianPerson());	
		}
		
		//PID-10 race
		if(patient.getRaceDict()!=null)
		{		
			pid.getPid10_Race(0).getCe1_Identifier().setValue(patient.getRaceDict());		
		}
		
		if(patient.getRaceName()!=null)
		{
			pid.getPid10_Race(0).getCe2_Text().setValue(patient.getRaceName());
		}
		
		if(patient.getRaceDomain()!=null)
		{
			pid.getPid10_Race(0).getCe3_NameOfCodingSystem().setValue(patient.getRaceDomain());
		}
		
		
		
		// PID-11.1居住地址
		if(patient.getHomeAddress()!=null)
		{
			pid.getPid11_PatientAddress(0).getXad1_StreetAddress().getSad1_StreetOrMailingAddress().setValue(patient.getHomeAddress());
		}
		
		//居住所在地市
		if(patient.getHomeCity()!=null)
		{
			pid.getPid11_PatientAddress(0).getXad3_City().setValue(patient.getHomeCity());
		}
		
		//居住所在地省
		if(patient.getHomeProvince()!=null)
		{
			pid.getPid11_PatientAddress(0).getXad4_StateOrProvince().setValue(patient.getHomeProvince());
		}
		
		//居住所在地邮编
		if(patient.getHomeZip()!=null)
		{
			pid.getPid11_PatientAddress(0).getXad5_ZipOrPostalCode().setValue(patient.getHomeZip());
		}
		
		//居住所在地区县
		if(patient.getHomeCounty()!=null)
		{
			pid.getPid11_PatientAddress(0).getXad9_CountyParishCode().setValue(patient.getHomeCounty());
		}
		
		//PID-11[2].1户口地址
		if(patient.getRegisteredAddress()!=null)
		{
			pid.getPid11_PatientAddress(1).getXad1_StreetAddress().getSad1_StreetOrMailingAddress().setValue(patient.getRegisteredAddress());
		}
		
		//户口所在地市
		if(patient.getRegisteredCity()!=null)
		{
			pid.getPid11_PatientAddress(1).getXad3_City().setValue(patient.getRegisteredCity());
		}
		
		//户口所在地省
		if(patient.getRegisteredProvince()!=null)
		{
			pid.getPid11_PatientAddress(1).getXad4_StateOrProvince().setValue(patient.getRegisteredProvince());
		}
		
		//户口所在地邮编
		if(patient.getRegisteredZip()!=null)
		{
			pid.getPid11_PatientAddress(1).getXad5_ZipOrPostalCode().setValue(patient.getRegisteredZip());
		}
		
		//户口所在地区县
		if(patient.getRegisteredCounty()!=null)
		{
			pid.getPid11_PatientAddress(1).getXad9_CountyParishCode().setValue(patient.getRegisteredCounty());
		}
		
		//出生地址
		if(patient.getBirthPlace()!=null)
		{
			pid.getPid11_PatientAddress(2).getXad1_StreetAddress().getSad1_StreetOrMailingAddress().setValue(patient.getBirthPlace());
		}
		
		//出生所在地市
		if(patient.getBirthCity()!=null)
		{
			pid.getPid11_PatientAddress(2).getXad3_City().setValue(patient.getBirthCity());
		}
		
		//出生所在地省
		if(patient.getBirthProvince()!=null)
		{
			pid.getPid11_PatientAddress(2).getXad4_StateOrProvince().setValue(patient.getBirthProvince());
		}
		
		//出生所在地邮编
		if(patient.getBirthZip()!=null)
		{
			pid.getPid11_PatientAddress(2).getXad5_ZipOrPostalCode().setValue(patient.getBirthZip());
		}
		
		//出生所在地区县
		 if(patient.getBirthCounty()!=null)
		 {
			 pid.getPid11_PatientAddress(2).getXad9_CountyParishCode().setValue(patient.getBirthCounty());
		 }
		 
		 //籍贯地址
		 if(patient.getNativeCity()!=null)
		 {
			 pid.getPid11_PatientAddress(3).getXad3_City().setValue(patient.getNativeCity());
		 }
		 
		 //籍贯所在地省
		 if(patient.getNativeProvince()!=null)
		 {
			 pid.getPid11_PatientAddress(3).getXad4_StateOrProvince().setValue(patient.getNativeProvince());
		 }
		 
		 //单位地址
		 if(patient.getWorkAddress()!=null)
		 {
			 pid.getPid11_PatientAddress(4).getXad1_StreetAddress().getSad1_StreetOrMailingAddress().setValue(patient.getWorkAddress());
		 }
		 
		 //工作单位
		 if(patient.getCompany()!=null)
		 {
			 pid.getPid11_PatientAddress(4).getXad2_OtherDesignation().setValue(patient.getCompany());
		 }
		 
		 //PID-12职业编码
		 if(patient.getProfession()!=null)
		 {
			 pid.getPid12_CountyCode().setValue(patient.getProfession());
		 }
		 
		 //PID-13.1家庭电话
		 if(patient.getHomeNumber()!=null)
		 {
			 pid.getPid13_PhoneNumberHome(0).getXtn1_TelephoneNumber().setValue(patient.getHomeNumber());
		 }
		 
		 //邮件地址
		 if(patient.getEmail()!=null)
		 {
			 pid.getPid13_PhoneNumberHome(0).getXtn4_EmailAddress().setValue(patient.getEmail());
		 }
		 
		 //私人电话
		 if(patient.getPrivateNumber()!=null)
		 {
			 pid.getPid13_PhoneNumberHome(1).getXtn1_TelephoneNumber().setValue(patient.getPrivateNumber());
		 }
		
		 //单位电话
		 if(patient.getWorkNumber()!=null)
		 {
			 pid.getPid13_PhoneNumberHome(2).getXtn1_TelephoneNumber().setValue(patient.getWorkNumber());
		 }
		 
//		 //PID-14职业名称
//		 if(patient.getProfessionName()!=null)
//		 {
//			 pid.getPid14_PhoneNumberBusiness(0).getXtn1_TelephoneNumber().setValue(patient.getProfessionName());
//		 }
//		 
//		 //职业编码系统
//		 if(patient.getProfessionDomain()!=null)
//		 {
//			// pid.getPid14_PhoneNumberBusiness(1).getXtn1_TelephoneNumber().setValue(patient.getProfessionDomain());
//		 }
//		 
//		 //性别名称pa
//		 if(patient.getGenderName()!=null)
//		 {
//			 pid.getPid14_PhoneNumberBusiness(2).getXtn1_TelephoneNumber().setValue(patient.getGenderName());
//		 }
//		 
//		 //性别编码系统
//		 if(patient.getGenderDomain()!=null)
//		 {
//			 pid.getPid14_PhoneNumberBusiness(3).getXtn1_TelephoneNumber().setValue(patient.getGenderDomain());
//		 }
//		 
		//PID-15 - Primary Language
		if(patient.getPrimaryLanguage()!=null)
		{
			pid.getPrimaryLanguage().getCe3_NameOfCodingSystem().setValue(patient.getPrimaryLanguage());
		}
			
		// PID-16 - Marital Status
		if (patient.getMaritalStatus() != null)
		{
			populateCE(pid.getMaritalStatus(), patient.getMaritalStatus());
		}
		
		if(patient.getMaritalStatusName()!=null)
		{
			pid.getPid16_MaritalStatus().getCe2_Text().setValue(patient.getMaritalStatusName());
		}
		
		//婚姻编码系统
		if(patient.getMaritalDomain()!=null)
		{
			pid.getPid16_MaritalStatus().getCe3_NameOfCodingSystem().setValue(patient.getMaritalDomain());
		}
		
		// PID-18 Patient account number
		// not known from PatientDescriptor
		PatientIdentifier accountNumber = patient.getPatientAccountNumber();
		
		if (accountNumber != null) 
		{
			if (accountNumber.getAssigningAuthority() == null)
			{
				pid.getPatientAccountNumber().getIDNumber().setValue(accountNumber.getId());
			}
			else
			{
				populateCX(pid.getPatientAccountNumber(), accountNumber.getId(), accountNumber.getAssigningAuthority());
			}
		}

		// PID-19 - SSN
		if (patient.getSsn() != null)
		{
			pid.getSSNNumberPatient().setValue(clip(patient.getSsn(), 16));
		}
		
		// PID-20 - Driver's License
		if (patient.getDriversLicense() != null) 
		{
			DriversLicense license = patient.getDriversLicense();
			
			if (license.getIssuingState() != null)
			{
				pid.getDriverSLicenseNumberPatient().getIssuingStateProvinceCountry().setValue(license.getIssuingState());
			}
			if (license.getLicenseNumber() != null)
			{
				pid.getDriverSLicenseNumberPatient().getLicenseNumber().setValue(clip(license.getLicenseNumber(), 25));
			}
			if (license.getExpirationDate() != null) 
			{
				Calendar expDate = license.getExpirationDate();
				
				int year = expDate.get(Calendar.YEAR);
				
				int month = expDate.get(Calendar.MONTH) + 1;
				
				int date = expDate.get(Calendar.DATE);
				
				pid.getDriverSLicenseNumberPatient().getExpirationDate().setYearMonthDayPrecision(year, month, date);
			}
		}
		
		//PID-20- 身份证件号
		if(patient.getIdentityNO()!=null)
		{
			pid.getPid20_DriverSLicenseNumberPatient().getDln1_LicenseNumber().setValue(patient.getIdentityNO());
		}
		
		//PID-21-医疗保险号
		if(patient.getInsuranceNO()!=null)
		{
			pid.getPid21_MotherSIdentifier(0).getCx1_IDNumber().setValue(patient.getInsuranceNO());
		}
		
		//PID-22民族编码
		if(patient.getEthnicGroup()!=null)
		{
			pid.getPid22_EthnicGroup(0).getCe1_Identifier().setValue(patient.getEthnicGroup());
		}
		
		//民族名称
		if(patient.getEthnicName()!=null)
		{
			pid.getPid22_EthnicGroup(0).getCe2_Text().setValue(patient.getEthnicName());
		}
		
		//民族编码系统
		 if(patient.getEthincDomain()!=null)
		 {
			 pid.getPid22_EthnicGroup(0).getCe3_NameOfCodingSystem().setValue(patient.getEthincDomain());
		 }

		//pid-24 -多胞胎标记
		if(patient.getMulitpleBirthIndicator()!=null)
		{
			pid.getPid24_MultipleBirthIndicator().setValue(patient.getMulitpleBirthIndicator());
		}
		
		//pid-25 -出生顺序
		if(String.valueOf(patient.getBirthOrder())!=null)
		{
			pid.getPid25_BirthOrder().setValue(String.valueOf(patient.getBirthOrder()));
		}
		
		// PID-26 - Citizenship Info
		if(patient.getCitizenship()!=null)
		{
			pid.getCitizenship(0).getIdentifier().setValue(patient.getCitizenship());
		}
		
		//PID-28国籍编码
		if(patient.getNationality()!=null)
		{
			pid.getPid28_Nationality().getCe1_Identifier().setValue(patient.getNationality());
		}
		
		//国籍名称
		if(patient.getNationalityName()!=null)
		{
			pid.getPid28_Nationality().getCe2_Text().setValue(patient.getNationalityName());
		}
		
		//国籍编码系统
		 if(patient.getNationalityDomain()!=null)
		 {
			 pid.getPid28_Nationality().getCe3_NameOfCodingSystem().setValue(patient.getNationalityDomain());
		 }

		// PID-29 - Date of death / death indicator
		if (patient.getDeathDate() != null) 
		{
			String birthdateFormat = HL7v25.birhtdateFormat;
			
			String formatFromProperty = Configuration.getPropertySetValue(connection, "DateTimeFormat", "Birthdate", false);
			
			if (StringUtil.goodString(formatFromProperty)) 
			{
				birthdateFormat = formatFromProperty;
			}
			
			String date = new SimpleDateFormat(birthdateFormat).format(patient.getDeathDate().getTime());
			
			pid.getPatientDeathDateAndTime().getTime().setValue(date);
		}
		
		// PID-30 - Death indicator
		if(patient.getVip()!=null)
		{
			pid.getPatientDeathIndicator().setValue(patient.getVip());
		}
		
		//扩展字段PID-4
		@SuppressWarnings("unused")
		List<ExtendForPerson> MyExtendList = new ArrayList<ExtendForPerson>();
		
//		try 
//		{
//			MyExtendList = pdqAdapter.findAllFields();
//			
//			for(ExtendForPerson TempExtend:MyExtendList)
//			{
//				if(TempExtend.getExtendfieldname()!=null && TempExtend.getPid4fields()>0 && TempExtend.getPid4fields() <30)
//				{
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM6"))
//					{
//						if(patient.getCustom6()!=null)
//						{
//							int TempInt=patient.getCustom6().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom6().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(0).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom6().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(0).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(0).getCx1_IDNumber().setValue(patient.getCustom6());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM7"))
//					{
//						if(patient.getCustom7()!=null)
//						{
//							int TempInt=patient.getCustom7().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom7().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(1).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom7().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(1).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(1).getCx1_IDNumber().setValue(patient.getCustom7());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM8"))
//					{
//						if(patient.getCustom8()!=null)
//						{
//							int TempInt=patient.getCustom8().indexOf("^");
//							
//							if(TempInt>0)
//							{				
//								String TempStr1=patient.getCustom8().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(2).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom8().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(2).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(2).getCx1_IDNumber().setValue(patient.getCustom8());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM9"))
//					{
//						if(patient.getCustom9()!=null)
//						{
//							int TempInt=patient.getCustom9().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom9().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(3).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom9().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(3).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(3).getCx1_IDNumber().setValue(patient.getCustom9());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM13"))
//					{
//						if(patient.getCustom13()!=null)
//						{
//							int TempInt=patient.getCustom13().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom13().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(4).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom13().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(4).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(4).getCx1_IDNumber().setValue(patient.getCustom13());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM14"))
//					{
//						if(patient.getCustom14()!=null)
//						{
//							int TempInt=patient.getCustom14().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom14().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(5).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom14().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(5).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(5).getCx1_IDNumber().setValue(patient.getCustom14());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM15"))
//					{
//						if(patient.getCustom15()!=null)
//						{
//							int TempInt=patient.getCustom15().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom15().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(6).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom15().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(6).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(6).getCx1_IDNumber().setValue(patient.getCustom15());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM18"))
//					{
//						if(patient.getCustom18()!=null)
//						{
//							int TempInt=patient.getCustom18().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom18().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(7).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom18().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(7).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(7).getCx1_IDNumber().setValue(patient.getCustom18());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM19"))
//					{
//						if(patient.getCustom19()!=null)
//						{
//							int TempInt=patient.getCustom19().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom19().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(8).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom19().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(8).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(8).getCx1_IDNumber().setValue(patient.getCustom19());
//							}
//						}
//
//					}
//					
//					if(TempExtend.getExtendfieldname().equalsIgnoreCase("CUSTOM20"))
//					{
//						if(patient.getCustom20()!=null)
//						{
//							int TempInt=patient.getCustom20().indexOf("^");
//							
//							if(TempInt>0)
//							{
//							
//								String TempStr1=patient.getCustom20().substring(0,TempInt);
//
//								pid.getAlternatePatientIDPID(9).getCx1_IDNumber().setValue(TempStr1);
//							
//								TempStr1=patient.getCustom20().substring(TempInt+1);
//							
//								pid.getAlternatePatientIDPID(9).getCx4_AssigningAuthority().getHd2_UniversalID().setValue(TempStr1);
//							
//								TempInt=0;
//							
//								TempStr1=null;
//							}
//							else
//							{
//								pid.getAlternatePatientIDPID(9).getCx1_IDNumber().setValue(patient.getCustom20());
//							}
//						}
//
//					}
//					
//					
//				}
//			}
//			
//		} 
//		catch (PdSupplierException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (com.ats.aempi.ApplicationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//if(patient.getcustom6)

		return numberOfPatientId;
	}
	
	private void populateVisitPV1(PV1 pv1, Patient patient, int setId, List<Identifier> returnDomains) throws HL7Exception, IheConfigurationException,PatientException 
	{
		// PV1-1 Set ID - PV1 (SI) 00104
		if (setId >= 1)
		{
			pv1.getPv11_SetIDPV1().setValue(String.valueOf(setId));
		
		//PV1-2 patient class
		if(patient.getFpatientvisitlist().get(setId-1).getPatCategory()!=null)
		{
			pv1.getPv12_PatientClass().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCategory());
		}
		
		//PV1-3.1:PAT_CURRENT_POINT_OF_CARE
		if(patient.getFpatientvisitlist().get(setId-1).getPatCurrentPointOfCare()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl1_PointOfCare().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCurrentPointOfCare());
		}
		
		//PV1-3.2 PAT_CURRENT_ROOM
		if(patient.getFpatientvisitlist().get(setId-1).getPatCurrentRoom()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl2_Room().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCurrentRoom());
		}
		
		//PV1-3.3 PAT_CURRENT_BED
		if(patient.getFpatientvisitlist().get(setId-1).getPatCurrentBed()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl3_Bed().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCurrentBed());
		}
		
		//PV1-3.4 PAT_CURRENT_DEP
		if(patient.getFpatientvisitlist().get(setId-1).getPatCuurentDep()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getFacility().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCuurentDep());
		}
		
		//PV1-3.5: PAT_CURRENT_POSITION_STATUS
		if(patient.getFpatientvisitlist().get(setId-1).getPatCurrentPositionStatus()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl5_LocationStatus().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCurrentPositionStatus());
		}
		
		//PV1-3.6:PAT_CURRENT_POSITION_TYPE
		if(patient.getFpatientvisitlist().get(setId-1).getPatCurrentPositionType()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl6_PersonLocationType().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCurrentPositionType());
		}
		
		//PV1-3.7:PAT_CURRENT_BUILDING
		if(patient.getFpatientvisitlist().get(setId-1).getPatCurrentBuilding()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl7_Building().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCurrentBuilding());
		}
		
		//PV1-3.8:PAT_CURRENT_FLOOR
		if(patient.getFpatientvisitlist().get(setId-1).getPatCurrentFloor()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl8_Floor().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCurrentFloor());
		}
		
		//PV1-3.9:PAT_CURRENT_DESCRIPTION
		if(patient.getFpatientvisitlist().get(setId-1).getPatCuurentDescription()!=null)
		{
			pv1.getPv13_AssignedPatientLocation().getPl9_LocationDescription().setValue(patient.getFpatientvisitlist().get(setId-1).getPatCuurentDescription());
		}
		
		//PV1-4:PAT_ADMISSION_TYPE
		if(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionType()!=null)
		{
			pv1.getPv14_AdmissionType().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionType());
		}
		
		//PV1-5:PAT_ADMISSION_NUMBER
		if(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionNumber()!=null)
		{
			pv1.getPv15_PreadmitNumber().getCx1_IDNumber().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionNumber());
		}
		
		//PV1-6.1:PAT_FORMER_POINT_OF_CARE
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerPointOfCare()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl1_PointOfCare().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerPointOfCare());
		}
		
		//PV1-6.2:PAT_FORMER_ROOM
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerRoom()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl2_Room().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerRoom());
		}
		
		//PV1-6.3:PAT_FORMER_BED
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerBed()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl3_Bed().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerBed());
		}
		
		//PV1-6.4:PAT_FORMER_DEP
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerDep()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl4_Facility().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerDep());
		}
		
		//PV1-6.5:PAT_FORMER_POSITION_STATUS
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerPositionStatus()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl5_LocationStatus().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerPositionStatus());
		}
		
		//PV1-6.6:PAT_FORMER_POSITION_TYPE
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerPositionType()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl6_PersonLocationType().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerPositionType());
		}
		
		//PV1-6.7:PAT_FORMER_BUILDING
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerBuilding()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl7_Building().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerBuilding());
		}
		
		//PV1-6.8:PAT_FORMER_FLOOR
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerFloor()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl8_Floor().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerFloor());
		}
		
		//PV1-6.9:PAT_FORMER_DESCRIPTION
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerDescription()!=null)
		{
			pv1.getPv16_PriorPatientLocation().getPl9_LocationDescription().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerDescription());
		}
		//PV1-7.1 接诊医生ID
		if(patient.getFpatientvisitlist().get(setId-1).getAdmissionsDoctorId()!=null)
		{
			pv1.getPv17_AttendingDoctor(0).getXcn1_IDNumber().setValue(patient.getFpatientvisitlist().get(setId-1).getAdmissionsDoctorId());
		}
		
		//PV1-7.2 接诊医生
		if(patient.getFpatientvisitlist().get(setId-1).getAdmissionsDoctor()!=null)
		{
			pv1.getPv17_AttendingDoctor(0).getXcn2_FamilyName().getFn1_Surname().setValue(patient.getFpatientvisitlist().get(setId-1).getAdmissionsDoctor());
		}
		
		//PV1-8.1 转诊医生ID
		if(patient.getFpatientvisitlist().get(setId-1).getReferringDoctorId()!=null)
		{
			pv1.getPv18_ReferringDoctor(0).getXcn1_IDNumber().setValue(patient.getFpatientvisitlist().get(setId-1).getReferringDoctorId());
		}
		
		//PV1-8.2 转诊医生
		if(patient.getFpatientvisitlist().get(setId-1).getReferringDoctor()!=null)
		{
			pv1.getPv18_ReferringDoctor(0).getXcn2_FamilyName().getFn1_Surname().setValue(patient.getFpatientvisitlist().get(setId-1).getReferringDoctor());
		}
		
		//PV1-9 会诊医生 ID
		if(patient.getFpatientvisitlist().get(setId-1).getConsultationDoctorId()!=null)
		{
			pv1.getPv19_ConsultingDoctor(0).getXcn1_IDNumber().setValue(patient.getFpatientvisitlist().get(setId-1).getConsultationDoctorId());
		}
		
		//PV1-9.2 会诊医生
		if(patient.getFpatientvisitlist().get(setId-1).getConsultationDoctor()!=null)
		{
			pv1.getPv19_ConsultingDoctor(0).getXcn2_FamilyName().getFn1_Surname().setValue(patient.getFpatientvisitlist().get(setId-1).getConsultationDoctor());
		}
		
		//PV1-10 医院服务
		if(patient.getFpatientvisitlist().get(setId-1).getHospitalService()!=null)
		{
			pv1.getPv110_HospitalService().setValue(patient.getFpatientvisitlist().get(setId-1).getHospitalService());
		}
		
		//PV1-11.1:PAT_TEMP_POINT_OF_CARE
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempPointOfCare()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl1_PointOfCare().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempPointOfCare());
		}
		
		//PV1-11.2:PAT_TEMP_ROOM
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempRoom()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl2_Room().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempRoom());
		}
		
		//PV1-11.3:PAT_TEMP_BED
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempBed()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl3_Bed().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempBed());
		}
		
		//PV1-11.4:PAT_TEMP_DEP
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempDep()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl4_Facility().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempDep());
		}
		
		//PV1-11.5:PAT_TEMP_POSITION_STATUS
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempPositionStatus()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl5_LocationStatus().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempPositionStatus());
		}
		
		//PV1-11.6:PAT_TEMP_POSITION_TYPE
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempPositionType()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl6_PersonLocationType().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempPositionType());
		}
		
		//PV1-11.7:PAT_TEMP_BUILDING
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempBuilding()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl7_Building().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempBuilding());
		}
		
		//PV1-11.8:PAT_TEMP_FLOOR
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempFloor()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl8_Floor().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempFloor());
		}
		
		//PV1-11.9:PAT_TEMP_DESCRIPTION
		if(patient.getFpatientvisitlist().get(setId-1).getPatTempDescription()!=null)
		{
			pv1.getPv111_TemporaryLocation().getPl9_LocationDescription().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTempDescription());
		}
		
		//PV1-12:PAT_ADMISSION_TEST
		if(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionTest()!=null)
		{
			pv1.getPv112_PreadmitTestIndicator().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionTest());
		}
		
		//PV1-13:PAT_RE_ADMISSION
		if(patient.getFpatientvisitlist().get(setId-1).getPatReAdmission()!=null)
		{
			pv1.getPv113_ReAdmissionIndicator().setValue(patient.getFpatientvisitlist().get(setId-1).getPatReAdmission());
		}
		
		//PV1-14:PAT_ADMISSION_SOURCE
		if(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionSource()!=null)
		{
			pv1.getPv114_AdmitSource().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionSource());
		}
		
		//PV1-15:PAT_AMBULATORY_STATUS
		if(patient.getFpatientvisitlist().get(setId-1).getDischargeName()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(0).setValue(patient.getFpatientvisitlist().get(setId-1).getDischargeName());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getDischargeDomain()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(1).setValue(patient.getFpatientvisitlist().get(setId-1).getDischargeDomain());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getAdmissionName()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(2).setValue(patient.getFpatientvisitlist().get(setId-1).getAdmissionName());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getAdmissionDomain()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(3).setValue(patient.getFpatientvisitlist().get(setId-1).getAdmissionDomain());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getIpStatusName()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(4).setValue(patient.getFpatientvisitlist().get(setId-1).getIpStatusName());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getIpStatusDomain()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(5).setValue(patient.getFpatientvisitlist().get(setId-1).getIpStatusDomain());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getDificultyName()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(6).setValue(patient.getFpatientvisitlist().get(setId-1).getDificultyName());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getDificultyDomain()!=null)
		{
			pv1.getPv115_AmbulatoryStatus(7).setValue(patient.getFpatientvisitlist().get(setId-1).getDificultyDomain());
		}
		
		String TempStr = null;
		
		if(patient.getFpatientvisitlist().get(setId-1).getAdmissionSourceName()!=null)
		{
			TempStr = TempStr + "+" + patient.getFpatientvisitlist().get(setId-1).getAdmissionSourceName();
		}

		if(patient.getFpatientvisitlist().get(setId-1).getAdmissionSourceDomain()!=null)
		{
			TempStr = TempStr + "+" + patient.getFpatientvisitlist().get(setId-1).getAdmissionSourceDomain();
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getAccountStatusName()!=null)
		{
			TempStr = TempStr + "+"  + patient.getFpatientvisitlist().get(setId-1).getAccountStatusName();
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getAccountStatusDomain()!=null)
		{
			TempStr = TempStr + "+"  + patient.getFpatientvisitlist().get(setId-1).getAccountStatusDomain();
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getPatCategoryName()!=null)
		{
			TempStr = TempStr + "+"  + patient.getFpatientvisitlist().get(setId-1).getPatCategoryName();
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getPatCategorySystem()!=null)
		{
			TempStr = TempStr + patient.getFpatientvisitlist().get(setId-1).getPatCategorySystem();
		}
		
		pv1.getPv115_AmbulatoryStatus(7).setValue(TempStr);
		
		//PV1-16:PAT_VIP
		if(patient.getFpatientvisitlist().get(setId-1).getPatVip()!=null)
		{
			pv1.getPv116_VIPIndicator().setValue(patient.getFpatientvisitlist().get(setId-1).getPatVip());
		}
		
		//PV1-17:PAT_ADMISSION_DOCTORS
		if(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionDoctors()!=null)
		{
			pv1.getPv117_AdmittingDoctor(0).getXcn2_FamilyName().getFn1_Surname().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionDoctors());
		}
		
		//PV1-17:PAT_ADMISSION_DOCTORS_ID
		if(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionDoctorsId()!=null)
		{
			pv1.getPv117_AdmittingDoctor(0).getXcn1_IDNumber().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAdmissionDoctorsId());
		}
		//PV1-18:PATIENT CLASS
		if(patient.getFpatientvisitlist().get(setId-1).getPatientClass()!=null)
		{
			pv1.getPv118_PatientType().setValue(patient.getFpatientvisitlist().get(setId-1).getPatientClass());
		}
		
		//PV1-19:PATIENT_ID
		if(patient.getFpatientvisitlist().get(setId-1).getVisitFlowId()!=null)
		{
			pv1.getPv119_VisitNumber().getCx1_IDNumber().setValue(patient.getFpatientvisitlist().get(setId-1).getVisitFlowId());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getCustom2()!=null)
		{
			pv1.getPv119_VisitNumber().getCx4_AssigningAuthority().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getCustom2());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getCustom3()!=null)
		{
			pv1.getPv119_VisitNumber().getCx4_AssigningAuthority().getHd2_UniversalID().setValue(patient.getFpatientvisitlist().get(setId-1).getCustom3());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getCustom4()!=null)
		{
			pv1.getPv119_VisitNumber().getCx4_AssigningAuthority().getHd3_UniversalIDType().setValue(patient.getFpatientvisitlist().get(setId-1).getCustom4());
		}
		
		//PV1-20:PAT_FINANCIAL_CLASS
		if(patient.getFpatientvisitlist().get(setId-1).getPatFinancialClass()!=null)
		{
			pv1.getPv120_FinancialClass(0).getFc1_FinancialClassCode().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFinancialClass());
		}
		
		//PV1-21:ROOM_BED_COST_PRICE
		if(patient.getFpatientvisitlist().get(setId-1).getRoomBedCostPrice()!=null)
		{
			pv1.getPv121_ChargePriceIndicator().setValue(patient.getFpatientvisitlist().get(setId-1).getRoomBedCostPrice());
		}
		
		//PV1-22:COURTESY_CODE
		if(patient.getFpatientvisitlist().get(setId-1).getCourtesyCode()!=null)
		{
			pv1.getPv122_CourtesyCode().setValue(patient.getFpatientvisitlist().get(setId-1).getCourtesyCode());
		}
		
		//pv1-23:credit_rating
		if(patient.getFpatientvisitlist().get(setId-1).getCreditRating()!=null)
		{
			pv1.getPv123_CreditRating().setValue(patient.getFpatientvisitlist().get(setId-1).getCreditRating());
		}
		
		//PV1-24:CONTRACT_CODE
		if(patient.getFpatientvisitlist().get(setId-1).getContractCode()!=null)
		{
			pv1.getPv124_ContractCode(0).setValue(patient.getFpatientvisitlist().get(setId-1).getContractCode());
		}
		
		//PV1-25:CONTRACT_CREATE_DATE
		if(patient.getFpatientvisitlist().get(setId-1).getContractCreateDate()!=null)
		{
			pv1.getPv125_ContractEffectiveDate(0).setValue(patient.getFpatientvisitlist().get(setId-1).getContractCreateDate());
		}
		
		//PV1-26:CONTRACT_PRICE
		if(patient.getFpatientvisitlist().get(setId-1).getContractPrice()!=null)
		{
			pv1.getPv126_ContractAmount(0).setValue(patient.getFpatientvisitlist().get(setId-1).getContractPrice());
		}
		
		//PV1-27:CONTRACT_TIME
		if(patient.getFpatientvisitlist().get(setId-1).getContractTime()!=null)
		{
			pv1.getPv127_ContractPeriod(0).setValue(patient.getFpatientvisitlist().get(setId-1).getContractTime());
		}
		
		//PV1-28:INTEREST_RATE_CODE
		if(patient.getFpatientvisitlist().get(setId-1).getInterestRateCode()!=null)
		{
			pv1.getPv128_InterestCode().setValue(patient.getFpatientvisitlist().get(setId-1).getInterestRateCode());
		}
		
		//PV1-29:bad_debts
		if(patient.getFpatientvisitlist().get(setId-1).getBadDebts()!=null)
		{
			pv1.getPv129_TransferToBadDebtCode().setValue(patient.getFpatientvisitlist().get(setId-1).getBadDebts());
		}
		
		//PV1-30:BAD_DEBTS_CREATE_DATE
		if(patient.getFpatientvisitlist().get(setId-1).getBadDebtsCreateDate()!=null)
		{
			pv1.getPv130_TransferToBadDebtDate().setValue(patient.getFpatientvisitlist().get(setId-1).getBadDebtsCreateDate());
		}
		
		//PV1-31:BAD_DEBTS_CODE
		if(patient.getFpatientvisitlist().get(setId-1).getBadDebtsCode()!=null)
		{
			pv1.getPv131_BadDebtAgencyCode().setValue(patient.getFpatientvisitlist().get(setId-1).getBadDebtsCode());
		}
		
		//PV1-32:BAD_DEBTS_PRICE
		if(patient.getFpatientvisitlist().get(setId-1).getBadDebtsPrice()!=null)
		{
			pv1.getPv132_BadDebtTransferAmount().setValue(patient.getFpatientvisitlist().get(setId-1).getBadDebtsPrice());
		}
		
		//PV1-33:BAD_DEBTS_RESTORE_PRICE
		if(patient.getFpatientvisitlist().get(setId-1).getBadDebtsRestorePrice()!=null)
		{
			pv1.getPv133_BadDebtRecoveryAmount().setValue(patient.getFpatientvisitlist().get(setId-1).getBadDebtsRestorePrice());
		}
		
		//PV1-34:PAT_ACCOUNT_VOIDED
		if(patient.getFpatientvisitlist().get(setId-1).getPatAccountVoided()!=null)
		{
			pv1.getPv134_DeleteAccountIndicator().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAccountVoided());
		}
		
		//PV1-35:PAT_ACCOUNT_VOIDED_DATE
		if(patient.getFpatientvisitlist().get(setId-1).getPatAccountVoidedDate()!=null)
		{
			pv1.getPv135_DeleteAccountDate().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAccountVoidedDate());
		}
		
		//PV1-36:PAT_DISCHARGE_DISPOSITION
		if(patient.getFpatientvisitlist().get(setId-1).getPatDischargeDisposition()!=null)
		{
			pv1.getPv136_DischargeDisposition().setValue(patient.getFpatientvisitlist().get(setId-1).getPatDischargeDisposition());
		}
		
		//PV1-37:PAT_DISCHARGE_LOCATION
		if(patient.getFpatientvisitlist().get(setId-1).getPatDischargeLocation()!=null)
		{
			pv1.getPv137_DischargedToLocation().getDld1_DischargeLocation().setValue(patient.getFpatientvisitlist().get(setId-1).getPatDischargeLocation());
		}
		
		//PV1-38:PAT_DIET_TYPE
		if(patient.getFpatientvisitlist().get(setId-1).getPatDietType()!=null)
		{
			pv1.getPv138_DietType().getCe1_Identifier().setValue(patient.getFpatientvisitlist().get(setId-1).getPatDietType());
		}
		
		//PV1-39:PAT_SERVICE_AGENCIES
		if(patient.getFpatientvisitlist().get(setId-1).getPatServiceAgencies()!=null)
		{
			pv1.getPv139_ServicingFacility().setValue(patient.getFpatientvisitlist().get(setId-1).getPatServiceAgencies());
		}
		
		//PV1-40:PAT__BED_STATUS
		if(patient.getFpatientvisitlist().get(setId-1).getPatBedStatus()!=null)
		{
			pv1.getPv140_BedStatus().setValue(patient.getFpatientvisitlist().get(setId-1).getPatBedStatus());
		}
		
		//PV1-41:PAT_ACCOUNT_STATUS
		if(patient.getFpatientvisitlist().get(setId-1).getPatAccountStatus()!=null)
		{
			pv1.getPv141_AccountStatus().setValue(patient.getFpatientvisitlist().get(setId-1).getPatAccountStatus());
		}
		
		//PV1-42.1:PAT_DETER_POINT_OF_CARE
		if(patient.getFpatientvisitlist().get(setId-1).getMedicinelimitamount()!=null)
		{
			pv1.getPv142_PendingLocation().getPl1_PointOfCare().setValue(patient.getFpatientvisitlist().get(setId-1).getMedicinelimitamount().toString()
					                                             + "," + patient.getFpatientvisitlist().get(setId-1).getSickbedlimitamount().toString()
					                                             + "," + patient.getFpatientvisitlist().get(setId-1).getExaminelimitamount().toString()
					                                             + "," + patient.getFpatientvisitlist().get(setId-1).getCurelimitamount().toString());
		}
		
		//PV1-42.2:PAT_DETER_ROOM
		if(patient.getFpatientvisitlist().get(setId-1).getPrefix()!=null)
		{
			pv1.getPv142_PendingLocation().getPl2_Room().setValue(patient.getFpatientvisitlist().get(setId-1).getPrefix());
		}
		
		//PV1-42.3:PAT_DETER_BED
		if(patient.getFpatientvisitlist().get(setId-1).getPatDeterBed()!=null)
		{
			pv1.getPv142_PendingLocation().getPl3_Bed().setValue(patient.getFpatientvisitlist().get(setId-1).getPatDeterBed());
		}
		
		//PV1-42.4:PAT_DETER_DEP
		if(patient.getFpatientvisitlist().get(setId-1).getPatDeterDep()!=null)
		{
			pv1.getPv142_PendingLocation().getPl4_Facility().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getPatDeterDep());
		}
		
		//PV1-42.5:PAT_DETER_position_status
		if(patient.getFpatientvisitlist().get(setId-1).getTend()!=null)
		{
			pv1.getPv142_PendingLocation().getPl5_LocationStatus().setValue(patient.getFpatientvisitlist().get(setId-1).getTend());
		}
		
		//PV1-42.6:PAT_DETER_POSITION_TYPE
		if(patient.getFpatientvisitlist().get(setId-1).getPatNurseCode()!=null)
		{
			pv1.getPv142_PendingLocation().getPl6_PersonLocationType().setValue(patient.getFpatientvisitlist().get(setId-1).getPatNurseCode());
		}
		
		//PV1-42.7:PAT_DETER_BUILDING
		if(patient.getFpatientvisitlist().get(setId-1).getPatNurseName()!=null)
		{
			pv1.getPv142_PendingLocation().getPl7_Building().setValue(patient.getFpatientvisitlist().get(setId-1).getPatNurseName());
		}
		
		//PV1-42.8:PAT_DETER_FLOOR
		if(patient.getFpatientvisitlist().get(setId-1).getOperCode()!=null)
		{
			pv1.getPv142_PendingLocation().getPl8_Floor().setValue(patient.getFpatientvisitlist().get(setId-1).getOperCode());
		}
		
		//PV1-42.9:PAT_DETER_DESCRIPTION
		if(patient.getFpatientvisitlist().get(setId-1).getOperDate()!=null)
		{
			pv1.getPv142_PendingLocation().getPl9_LocationDescription().setValue(patient.getFpatientvisitlist().get(setId-1).getOperDate().toString());
		}
		
		//PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
		if(patient.getFpatientvisitlist().get(setId-1).getPatForTempPointOfCare()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl1_PointOfCare().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempDescription());
		}
		
		//PV1-43.2:PAT_FOR_TEMP_ROOM
		if(patient.getFpatientvisitlist().get(setId-1).getPatForTempRoom()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl2_Room().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempRoom());
		}
		
		//PV1-43.3:PAT_FOR_TEMP_BED
		if(patient.getFpatientvisitlist().get(setId-1).getPatForTempBed()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl3_Bed().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempBed());
		}
		
		//PV1-43.4:PAT_FOR_TEMP_DEP
		if(patient.getFpatientvisitlist().get(setId-1).getPatForTempDep()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl4_Facility().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempDep());
		}
		
		//PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
		if(patient.getFpatientvisitlist().get(setId-1).getPatForTempPositionStatus()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl5_LocationStatus().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempPositionStatus());
		}
		
		//pv1-43.6:pat_for_temp_position_type
		if(patient.getFpatientvisitlist().get(setId-1).getPatForTempPositionType()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl6_PersonLocationType().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempPositionType());
		}
		
		//PV1-43.7:PAT_FOR_TEMP_BUILDING
		if(patient.getFpatientvisitlist().get(setId-1).getPatForTempBuilding()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl7_Building().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempBuilding());
		}
		
		//PV1-43.8:PAT_FOR_TEMP_FLOOR
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerFloor()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl8_Floor().setValue(patient.getFpatientvisitlist().get(setId-1).getPatForTempFloor());
		}
		
		//PV1-43.9:PAT_FOR_TEMP_DESCRITION
		if(patient.getFpatientvisitlist().get(setId-1).getPatFormerDescription()!=null)
		{
			pv1.getPv143_PriorTemporaryLocation().getPl9_LocationDescription().setValue(patient.getFpatientvisitlist().get(setId-1).getPatFormerDescription());
		}
		
		//PV1-44 住院日期
		if(patient.getFpatientvisitlist().get(setId-1).getAdmitDate()!=null)
		{
			String birthdateFormat = HL7v25.birhtdateFormat;
			
			String formatFromProperty = Configuration.getPropertySetValue(connection, "DateTimeFormat", "Birthdate", false);
			
			if (StringUtil.goodString(formatFromProperty)) 
			{
				birthdateFormat = formatFromProperty;
			}
			
			String date = new SimpleDateFormat(birthdateFormat).format(patient.getFpatientvisitlist().get(setId-1).getAdmitDate());
			
			pv1.getPv144_AdmitDateTime().getTime().setValue(date);
		}
		
		//PV1-45 出院日期
		if(patient.getFpatientvisitlist().get(setId-1).getDischargeDate()!=null)
		{
			String birthdateFormat = HL7v25.birhtdateFormat;
			
			String formatFromProperty = Configuration.getPropertySetValue(connection, "DateTimeFormat", "Birthdate", false);
			
			if (StringUtil.goodString(formatFromProperty)) 
			{
				birthdateFormat = formatFromProperty;
			}
			
			String date = new SimpleDateFormat(birthdateFormat).format(patient.getFpatientvisitlist().get(setId-1).getDischargeDate());
			
			pv1.getPv145_DischargeDateTime(0).getTime().setValue(date);
		}
		
		//PV1-46:PAT_DIFERENCE
		if(patient.getFpatientvisitlist().get(setId-1).getPatDifference()!=null)
		{
			pv1.getPv146_CurrentPatientBalance().setValue(patient.getFpatientvisitlist().get(setId-1).getPatDifference());
		}
		
		//PV1-47:PAT_TOTAL_COST
		if(patient.getFpatientvisitlist().get(setId-1).getPatTotalCost()!=null)
		{
			pv1.getPv147_TotalCharges().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTotalCost());
		}
		
		//PV1-48:PAL_TOTAL_DISPATCH
		if(patient.getFpatientvisitlist().get(setId-1).getPatTotalDispatch()!=null)
		{
			pv1.getPv148_TotalAdjustments().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTotalDispatch());
		}
		
		//pv1-49:PAL_TOTAL_AMOUNT_PAYABLE
		if(patient.getFpatientvisitlist().get(setId-1).getPatTotalAmountPayable()!=null)
		{
			pv1.getPv149_TotalPayments().setValue(patient.getFpatientvisitlist().get(setId-1).getPatTotalAmountPayable());
		}
		
		//PV1-50:PAT_SPARE_ID
		if(patient.getFpatientvisitlist().get(setId-1).getBabyFlag()!=null)
		{
			pv1.getPv150_AlternateVisitID().getCx1_IDNumber().setValue(patient.getFpatientvisitlist().get(setId-1).getBabyFlag());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getAdmitWeight()!=null)
		{
			pv1.getPv150_AlternateVisitID().getCx3_CheckDigitScheme().setValue(patient.getFpatientvisitlist().get(setId-1).getAdmitWeight());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getAdmitWeightUnit()!=null)
		{
			pv1.getPv150_AlternateVisitID().getCx4_AssigningAuthority().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getAdmitWeightUnit());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getBirthWeight()!=null)
		{
			pv1.getPv150_AlternateVisitID().getCx5_IdentifierTypeCode().setValue(patient.getFpatientvisitlist().get(setId-1).getBirthWeight());
		}
		
		if(patient.getFpatientvisitlist().get(setId-1).getBabyWeightUnit()!=null)
		{
			pv1.getPv150_AlternateVisitID().getCx6_AssigningFacility().getHd1_NamespaceID().setValue(patient.getFpatientvisitlist().get(setId-1).getBabyWeightUnit());
		}
		
		//PV1-51:PAT_VISIT_LOGO
		if(patient.getFpatientvisitlist().get(setId-1).getPatVisitLogo()!=null)
		{
			pv1.getPv151_VisitIndicator().setValue(patient.getFpatientvisitlist().get(setId-1).getPatVisitLogo());
		}
		
		//PV1-52:OTHER_MEDICAL_INSTITUTIONS
		if(patient.getFpatientvisitlist().get(setId-1).getOtherMedicalInstitutions()!=null)
		{
			pv1.getPv152_OtherHealthcareProvider(0).getXcn2_FamilyName().getFn1_Surname().setValue(patient.getFpatientvisitlist().get(setId-1).getOtherMedicalInstitutions());
		}
		}
	}
	

	/**
	 * Populates a CX component with an ID and assigning authority.
	 * 
	 * @param cx
	 *            the CX component to populate
	 * @param id
	 *            the id to put in
	 * @param authority
	 *            the assigning authority for that id
	 * @throws DataTypeException
	 *             When the component cannot be encoded in HL7
	 * @throws IheConfigurationException
	 *             When this connection is not properly configured to translate Patient Feed messages
	 */
	private void populateCE(CE ce, String id) throws DataTypeException, IheConfigurationException {
		// PID 15.1 -- The id
		ce.getIdentifier().setValue(id);
	}

	/**
	 * Populates a CX component with an ID and assigning authority.
	 * 
	 * @param cx
	 *            the CX component to populate
	 * @param id
	 *            the id to put in
	 * @param authority
	 *            the assigning authority for that id
	 * @throws DataTypeException
	 *             When the component cannot be encoded in HL7
	 * @throws IheConfigurationException
	 *             When this connection is not properly configured to translate Patient Feed messages
	 */
	private void populateCX(CX cx, String id, Identifier authority) throws DataTypeException, IheConfigurationException {
		// PID 3.1 -- The id
		cx.getIDNumber().setValue(id);
		// PID 3.4 -- The assigning authority
		boolean okay = false;
		HD hd = cx.getAssigningAuthority();
		if (authority.getNamespaceId() != null) {
			hd.getNamespaceID().setValue(authority.getNamespaceId());
			okay = true;
		}
		if (authority.getUniversalId() != null) {
			hd.getUniversalID().setValue(authority.getUniversalId());
			if (authority.getUniversalIdType() != null) {
				hd.getUniversalIDType().setValue(authority.getUniversalIdType());
				okay = true;
			}
		}
		// If the assigning authority does not enough pieces, throw an exception
		if (!okay)
			throw new IheConfigurationException("Invalid Assigning Authority identifer encountered by connection \"" + connection.getDescription() + "\"");
		// PID 3.5 -- The id type code, see HL7 table 0203 - "PI" stands for Patient Internal PersonIdentifier
		cx.getIdentifierTypeCode().setValue("PI");
	}

	/**
	 * Populates an XPN component with a patient name.
	 * 
	 * @param xpn
	 *            the XPN component to populate
	 * @param patientName
	 *            the patient name
	 * @throws PatientException
	 *             If this patient has no name
	 * @throws DataTypeException
	 *             When the name cannot be encoded into valid HL7
	 */
	private void populateXPN(XPN xpn, PersonName patientName) throws DataTypeException, PatientException 
	{
		if ((patientName.getFirstName() == null) && (patientName.getSecondName() == null) && (patientName.getLastName() == null))
			throwPatientException("Patient has no name.");
		
		if (patientName.getLastName() != null)
			xpn.getGivenName().setValue(patientName.getLastName());
			xpn.getFamilyName().getSurname().setValue(patientName.getLastName());
		
		if (patientName.getSecondName() != null)
			xpn.getSecondAndFurtherGivenNamesOrInitialsThereof().setValue(patientName.getSecondName());
		
		if (patientName.getFirstName() != null)
			xpn.getFamilyName().getSurname().setValue(patientName.getFirstName());
		
		if (patientName.getPrefix() != null)
			xpn.getPrefixEgDR().setValue(patientName.getPrefix());
		
		if (patientName.getSuffix() != null)
			xpn.getSuffixEgJRorIII().setValue(patientName.getSuffix());
		
		if (patientName.getNameTypeCode() != null)
			xpn.getNameTypeCode().setValue(patientName.getNameTypeCode());
		
		if (patientName.getDegree()!=null)
			xpn.getXpn6_DegreeEgMD().setValue(patientName.getDegree());
		
		if (patientName.getDegreeName()!=null)
			xpn.getXpn3_SecondAndFurtherGivenNamesOrInitialsThereof().setValue(patientName.getDegreeName());
		
		if (patientName.getDegreeDomain()!=null)
			xpn.getXpn8_NameRepresentationCode().setValue(patientName.getDegreeDomain());
	}

	/**
	 * Populates an XAD component with an address.
	 * 
	 * @param xad
	 *            the XAD component to populate
	 * @param address
	 *            the address to get the data from
	 * @throws DataTypeException
	 *             When the component cannot be encoded in HL7
	 */
	private void populateXAD(XAD xad, Address address) throws DataTypeException {
		// Fill in all the address parts
		if (address.getAddLine1() != null) {
			xad.getStreetAddress().getStreetOrMailingAddress().setValue(address.getAddLine1());
		}
		if (address.getAddLine2() != null) {
			xad.getOtherDesignation().setValue(address.getAddLine2());
		}
		if (address.getAddCity() != null) {
			xad.getCity().setValue(address.getAddCity());
		}
		if (address.getAddState() != null) {
			xad.getStateOrProvince().setValue(address.getAddState());
		}
		if (address.getAddZip() != null) {
			xad.getZipOrPostalCode().setValue(address.getAddZip());
		}
		if (address.getAddCountry() != null) {
			xad.getCountry().setValue(address.getAddCountry());
		}
		if (address.getAddCounty() != null) {
			xad.getCountyParishCode().setValue(address.getAddCounty());
		}
		// The address type is optional
		if (address.getAddType() != null) {
			xad.getAddressType().setValue(address.getAddType().getHL7Value());
		}
	}

	/**
	 * Populates an XTN component with a phone number.
	 * <p>
	 * This method uses a combined interpretation of the v2.3.1 and the v2.5 spec to require only digits in phone numbers and impose limits on the field
	 * lengths.
	 * 
	 * @param xtn
	 *            the XTN component to populate
	 * @param phone
	 *            the phone number to put into the component
	 * @throws DataTypeException
	 *             When the component cannot be encoded in HL7
	 */
	private void populateXTN(XTN xtn, PhoneNumber phone) throws DataTypeException {
		// First the separate pieces
		String country = null;
		if (phone.getCountryCode() != null) {
			country = clipNumber(phone.getCountryCode(), 3);
			xtn.getCountryCode().setValue(country);
		}
		String area = null;
		if (phone.getAreaCode() != null) {
			area = clipNumber(phone.getAreaCode(), 5);
			xtn.getAreaCityCode().setValue(area);
		}
		String number = null;
		if (phone.getNumber() != null) {
			number = clipNumber(phone.getNumber(), 9);
			xtn.getLocalNumber().setValue(number);
		}
		String extension = null;
		if (phone.getExtension() != null) {
			extension = clipNumber(phone.getExtension(), 5);
			xtn.getExtension().setValue(extension);
		}
		if (phone.getNote() != null)
			xtn.getAnyText().setValue(phone.getNote());
		// Next the telecommunications types (adapted fom our ENUM types)
		if (phone.getType() == SharedEnums.PhoneType.HOME) {
			xtn.getTelecommunicationUseCode().setValue("PRN");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == SharedEnums.PhoneType.WORK) {
			xtn.getTelecommunicationUseCode().setValue("WPN");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == SharedEnums.PhoneType.CELL) {
			xtn.getTelecommunicationUseCode().setValue("PRN");
			xtn.getTelecommunicationEquipmentType().setValue("CP");
		} else if (phone.getType() == SharedEnums.PhoneType.EMERGENCY) {
			xtn.getTelecommunicationUseCode().setValue("EMR");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == SharedEnums.PhoneType.SERVICE) {
			xtn.getTelecommunicationUseCode().setValue("ASN");
			xtn.getTelecommunicationEquipmentType().setValue("PH");
		} else if (phone.getType() == SharedEnums.PhoneType.FAX) {
			xtn.getTelecommunicationUseCode().setValue("WPN");
			xtn.getTelecommunicationEquipmentType().setValue("FX");
		}
		if (phone.getEmail() != null) {
			xtn.getEmailAddress().setValue(phone.getEmail());
		}
		if (phone.getRawValue() != null) {
			xtn.getTelephoneNumber().setValue(phone.getRawValue());
		}
	}

	/**
	 * Extracts only digits from a string and then clip it to a specific length.
	 * 
	 * @param value
	 *            the string to make numeric and clip
	 * @param size
	 *            the maximum length allowed
	 * @return The clipped string
	 */
	private String clipNumber(String value, int size) {
		try {
			Integer.parseInt(value);
			return clip(value, size);
		} catch (NumberFormatException e) {
			log.warn("Extracting digits from non-numeric value '" + value + "'");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				if (Character.isDigit(c))
					sb.append(c);
			}
			return sb.toString();
		}
	}

	/**
	 * Clips a string to a specific length.
	 * 
	 * @param value
	 *            the string to clip
	 * @param size
	 *            the maximum length allowed
	 * @return The clipped string
	 */
	private String clip(String value, int size) {
		if (value == null)
			return null;
		if (value.length() <= size)
			return value;
		log.warn("Clipping the value '" + value + "' to length " + size);
		return value.substring(0, size);
	}

	/**
	 * Throws a new patient exception and log it as well.
	 * 
	 * @param message
	 *            the exception message to log
	 * @throws PatientException
	 */
	private void throwPatientException(String message) throws PatientException {
		log.error(message);
		throw new PatientException(message);
	}

	/*
	 * Whether to handle Pagination (or Continuation Query) by APIXPDQ or EMPI.
	 * 
	 * @return <code>true</code> if continuation query is processed by APIXPDQ; otherwise <code>false</code>. Defaults to true (namely, by APIXPDQ).
	 * 
	 * @throw IheConfigureException if anything wrong with configuration
	 */
	private boolean isContinuationQueryByAPixPdq() {
		boolean pagingByAPixPdq = true; // defaults to true(by APIXPDQ).
		try {
			String pagination = Configuration.getPropertySetValue(connection, "QueryProperties", "ContinuationQueryByAPIXPDQ", false);
			pagingByAPixPdq = Boolean.parseBoolean(pagination);
		} catch (IheConfigurationException e) {
			System.out.println(e);
		}
		return pagingByAPixPdq;
	}

	/*
	 * Return the patient list for specified return domain otherwise it return all patient list.
	 * 
	 * @param pdqresult contains all matching patients
	 * 
	 * @param returnDomains the domains whose patient demographics to be returned to the PDQ consumer
	 * 
	 * @return List<List<Patient>> a list of filtered patient by return domains. The first list for different logic patients, while the second list is for the
	 * same logic patient in different domains.
	 */
	private List<List<Patient>> getPatientList(PdqResult pdqResult, List<Identifier> returnDomains) {
		List<List<Patient>> finalPatients = new ArrayList<List<Patient>>();

		// pdqResult can never be null, otherwise exception would be thrown.
		List<List<Patient>> allPatients = pdqResult.getPatients();
		// List<List<Patient>> finalPatients = new ArrayList<List<Patient>>();
		if (returnDomains.size() == 0) {
			// If no return domain is specified, we consider all patients
			finalPatients = allPatients;
		} else {
			// Find a list of final patients that have ids in the return domain.
			for (List<Patient> lpatients : allPatients) {
				List<Patient> filteredPatients = new ArrayList<Patient>();
				for (Patient patient : lpatients) {
					List<PatientIdentifier> pids = patient.getPatientIds();
					for (PatientIdentifier pid : pids) {
						Identifier authority = pid.getAssigningAuthority();
						// authority might be partial (either namespaceId or universalId),
						// so need to map to the one used in the configuration.
						authority = AssigningAuthorityUtil.reconcileIdentifier(authority, actor.getActorDescription(), pdqAdapter);
						if (returnDomains.contains(authority)) {
							filteredPatients.add(patient);
							break;
						}
					}
				}
				// We don't want an empty list of patient
				if (filteredPatients.size() > 0)
					finalPatients.add(filteredPatients);
			}
		}
		return finalPatients;
	}

	/**
	 * Gets the sub list from the parent list.
	 * 
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @param parentList
	 *            the parent list
	 * @return a sublit with the given start and end indexes
	 */
	private List<List<Patient>> getSubList(int start, int end, List<List<Patient>> parentList) {
		List<List<Patient>> returnList = new ArrayList<List<Patient>>();
		for (int j = start; j < end; j++) {
			List<Patient> list = parentList.get(j);
			returnList.add(list);
		}
		return returnList;
	}
}
