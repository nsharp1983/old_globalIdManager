package com.ats.apixpdq.impl.v2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ats.aempi.AuthenticationException;
import com.ats.aempi.model.ExtendForPerson;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.actorconfig.net.ConnectionFactory;
import com.ats.aexchange.actorconfig.net.IConnection;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
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
import com.ats.aexchange.utils.ExceptionUtil;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.app.ApplicationException;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.datatype.CE;
import ca.uhn.hl7v2.model.v231.datatype.CX;
import ca.uhn.hl7v2.model.v231.datatype.ELD;
import ca.uhn.hl7v2.model.v231.datatype.ST;
import ca.uhn.hl7v2.model.v231.group.ADT_A39_PIDPD1MRGPV1;
import ca.uhn.hl7v2.model.v231.message.ACK;
import ca.uhn.hl7v2.model.v231.message.ADT_A01;
import ca.uhn.hl7v2.model.v231.message.ADT_A02;
import ca.uhn.hl7v2.model.v231.message.ADT_A03;
import ca.uhn.hl7v2.model.v231.message.ADT_A04;
import ca.uhn.hl7v2.model.v231.message.ADT_A05;
import ca.uhn.hl7v2.model.v231.message.ADT_A06;
import ca.uhn.hl7v2.model.v231.message.ADT_A08;
import ca.uhn.hl7v2.model.v231.message.ADT_A11;
import ca.uhn.hl7v2.model.v231.message.ADT_A12;
import ca.uhn.hl7v2.model.v231.message.ADT_A13;
import ca.uhn.hl7v2.model.v231.message.ADT_A39;
import ca.uhn.hl7v2.model.v231.segment.MRG;
import ca.uhn.hl7v2.model.v231.segment.PID;
import ca.uhn.hl7v2.parser.PipeParser;

import com.ats.apixpdq.api.IPixManagerAdapter;
import com.ats.apixpdq.api.IPixUpdateNotificationRequest;
import com.ats.apixpdq.api.MessageStore;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.AssigningAuthorityUtil;
import com.ats.apixpdq.common.BaseHandler;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.PixPdqException;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.common.PixUpdateNotifier;
import com.ats.apixpdq.impl.v2.hl7.HL7Channel;
import com.ats.apixpdq.impl.v2.hl7.HL7Header;
import com.ats.apixpdq.impl.v2.hl7.HL7v231;
import com.ats.apixpdq.impl.v2.hl7.HL7v231ToBaseConvertor;
import com.misys.hieportal.sysmon.IJMXEventNotifier;

/**
 * PIX Handler，注册与更新消息的实际处理者
 * 
 * @author Hansen
 * @see com.ats.apixpdq.impl.v2.PixManager
 * @see com.ats.aempi.apixpdqadapter.PixManagerAdapter
 */
class PixFeedHandler extends BaseHandler implements Application
{

    private static Logger log = Logger.getLogger(PixFeedHandler.class);
    private PixManager actor = null;

    private IPixManagerAdapter pixAdapter = null;
    /** Keep an instance of v25 handler for message redirection */
    private PixFeedHandlerV25 handlerV25 = null;
    private Connection hl7Connection = null;
    private IJMXEventNotifier eventBean = null;

    /**
     * Constructor
     * 
     * @param actor the {@link PixManager} actor
     */
    PixFeedHandler(PixManager actor)
    {
        super();
        this.actor = actor;
        this.pixAdapter = PixPdqFactory.getPixManagerAdapter();
        this.handlerV25 = new PixFeedHandlerV25(actor);
        this.eventBean = actor.getPixEvent();
        assert this.pixAdapter != null;
    }

    /**
     * Whether a incoming message can be processed by this handler.
     * 
     * @return <code>true</code> if the incoming message can be processed;
     *         otherwise <code>false</code>.
     */
    public boolean canProcess(Message theIn)
    {
        if (theIn instanceof ADT_A01 || theIn instanceof ADT_A04 || theIn instanceof ADT_A05 || theIn instanceof ADT_A08 || theIn instanceof ADT_A39
                || theIn instanceof ca.uhn.hl7v2.model.v25.message.ADT_A01 || theIn instanceof ca.uhn.hl7v2.model.v25.message.ADT_A05)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 处理以下消息： 新增：ADT^A01, ADT^A04, ADT^A05 
     * 更新：ADT^A08 
     * 合并：ADT^A40
     * 
     * @param msgIn输入消息
     * @throws IOException
     * @throws IOException
     */
    public Message processMessage(Message msgIn) throws ApplicationException, HL7Exception
    {
        Message retMessage = null;

        try
        {

            log.fatal("\n" + "收到HL7消息:" + "\n" + msgIn);

            if (msgIn instanceof ADT_A01 || // 患者入院
                    msgIn instanceof ADT_A04 || // 患者门诊挂号
                    msgIn instanceof ADT_A05) // 患者预约 
            {
                // 新增患者信息
                // Patient+PatientDomain相同，PatientVisitId+PatientVisitDomain不同时，可以注册
                // Patient+PatientDomain相同，PatientVisitId+PatientVisitDomain相同时，不可以注册
                retMessage = processCreate(msgIn);
            }
            else if (msgIn instanceof ADT_A08)
            {
                // 更新患者信息
                retMessage = processUpdate(msgIn);
            }
            else if (msgIn instanceof ADT_A39)
            {
                // 合并患者信息
                retMessage = processMerge(msgIn);
            }
            else if (msgIn instanceof ca.uhn.hl7v2.model.v25.message.ADT_A01 || // 患者入院
                    msgIn instanceof ca.uhn.hl7v2.model.v25.message.ADT_A05) //患者预约
            {
                // 新增患者信息
                retMessage = handlerV25.processCreate(msgIn);
            }
            else
            {
                // 未知消息
                String errorMsg = "Unexpected request to PIX Manager server. " + 
                "Valid message types are ADT^A01,ADT^A04,ADT^108 AND ADT^A40";

                throw new ApplicationException(errorMsg);
            }

            // 推送
            String enabletrans = PropertyFacade.getString(PixPdqConstants.ENABLE_TRANS);
            if (enabletrans.equalsIgnoreCase("true") && retMessage.toString().indexOf("AA") > 0)
            {
                processTrans(msgIn);
            }

        }
        catch (PixPdqException e)
        {
            throw new ApplicationException(ExceptionUtil.strip(e.getMessage()), e);
        }
        catch (HL7Exception e)
        {
            throw new HL7Exception(ExceptionUtil.strip(e.getMessage()), e);
        }
        catch (com.ats.aempi.ApplicationException e)
        {
            e.printStackTrace();
        }
        catch (AuthenticationException e)
        {
            e.printStackTrace();
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (eventBean != null)
                eventBean.notifyMessageReceived();
        }
        return retMessage;
    }

    /**
     * Processes PIX Feed Create Patient message in HL72.3.1.
     * 
     * @param msgIn the PIX Feed request message
     * @return a response message for PIX Feed
     * @throws PixPdqException If Application has trouble
     * @throws HL7Exception if something is wrong with HL7 message
     */
    private Message processCreate(Message msgIn) throws PixPdqException, HL7Exception
    {

        assert msgIn instanceof ADT_A01 || msgIn instanceof ADT_A04 || msgIn instanceof ADT_A05;

        HL7Header hl7Header = new HL7Header(msgIn);

        // If it is for A08, we redirect it to processUpdate.
        if (hl7Header.getTriggerEvent().equals("A08"))
        {
            return processUpdate(msgIn);
        }

        // Create Acknowledgment and its Header
        ACK reply = initAcknowledgment(hl7Header);

        // Validate incoming message first
        PID pid = (PID) msgIn.get("PID");

        PatientIdentifier patientId = getPatientIdentifiers(pid);

        boolean isValidMessage = validateMessage(reply, hl7Header, patientId, null, true);

        if (!isValidMessage)
        {
            log.fatal("\n" + "返回HL7消息：" + "\n" + reply);

            return reply;
        }

        // Invoke eMPI function
        MessageHeader header = hl7Header.toMessageHeader();

        Patient patient = PixFeedHandlerHelper.getPatient(pixAdapter,actor,msgIn);

        List<PatientIdentifier> temppatientidentifier = new ArrayList<PatientIdentifier>();

        temppatientidentifier = getPatientIdList(msgIn);

        int matchingSize = 0;

        try
        {
            @SuppressWarnings("unused")
            List<PatientIdentifier> matching = null;

            if (!actor.isNotification())
            {
                try
                {
                    if (temppatientidentifier.size() > 0)
                    {
                        for (int i = 0; i < temppatientidentifier.size(); i++)
                        {
                            Patient mypatient = new Patient();

                            List<PatientIdentifier> mypatientidentifier = new ArrayList<PatientIdentifier>();

                            mypatient = patient;

                            if (temppatientidentifier.get(i) != null)
                            {
                                mypatientidentifier.add(temppatientidentifier.get(i));
                            }

                            mypatient.getPatientIds().clear();

                            mypatient.setPatientIds(mypatientidentifier);

                            matching = pixAdapter.createPatient(mypatient, header);

                            if (matching == null)
                            {
                                matchingSize = matchingSize + 0;
                            }
                            else if (matching != null)
                            {
                                matchingSize = matchingSize + matching.size();
                            }
                        }
                    }
                }
                catch (com.ats.aempi.ApplicationException e)
                {
                    e.printStackTrace();
                }
            }

            /*
             * //Send PIX Update Notification 
             * //yrh-2012-11-01
             * //非必要时关闭，IHE测试时可以打开推送 
             * if (matching != null && matching.size() >0) 
             * { 
             *     IPixUpdateNotificationRequest request = new PixUpdateNotificationRequest(actor, matching);
             *     PixUpdateNotifier.getInstance().accept(request); 
             * }
             */
            matching = null;

        }
        catch (PixManagerException e)
        {
        	/*20170526-0035156-start */
        	// 异常取得
        	Throwable cause = e.getCause();
        	// 符合条件的异常
			if (cause.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				// 异常信息取得
				String errMsg = cause.getCause().getCause().getMessage();
				// 异常是ORA-00001的场合、返回错误消息信息
				if (StringUtils.isNotBlank(errMsg)
						&& errMsg.indexOf("ORA-00001") != -1) {
					HL7v231.populateMSA(reply.getMSA(), "AE",
							hl7Header.getMessageControlId());
					HL7v231.populateERR(reply.getERR(), null, null, null, null,
							null, null, "病人记录已经存在，重复注册");
					log.fatal("\n" + "返回HL7消息：" + "\n" + reply);
					hl7Header = null;
					return reply;
				}
			}
			/*20170526-0035156-end */
        	  	
            throw new PixPdqException(e);
        }

        if (matchingSize > 0)
        {
            HL7v231.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());
        }
        else
        {
            HL7v231.populateMSA(reply.getMSA(), "AE", hl7Header.getMessageControlId());

            HL7v231.populateERR(reply.getERR(), null, null, null, null, null, null, "可能是病人记录及病人就诊记录都已经存在，重复注册,也可能是PID.4.1与PV1.19.1的流水号不匹配");
        }
        // Forward this PIX Feed (Merge) message to the XDS Registry
        // forwardToXdsRegistry(msgIn, patientId);

        // Finally, Audit Log PIX Feed Success
        // auditLog(hl7Header, patient,
        // AuditCodeMappings.EventActionCode.Create);

        log.fatal("\n" + "返回HL7消息：" + "\n" + reply);

        hl7Header = null;

        return reply;
    }

    /**
     * Processes PIX Feed Update Patient message.
     * 
     * @param msgIn
     *            the PIX Feed request message
     * @return a response message for PIX Feed
     * @throws PixPdqException
     *             If Application has trouble
     * @throws HL7Exception
     *             if something is wrong with HL7 message
     */

    private Message processUpdate(Message msgIn) throws PixPdqException, HL7Exception
    {
        assert msgIn instanceof ADT_A01 || msgIn instanceof ADT_A08;

        HL7Header hl7Header = new HL7Header(msgIn);

        // Create Acknowledgment and its Header
        ACK reply = initAcknowledgment(hl7Header);

        // Validate incoming message first
        PID pid = (PID) msgIn.get("PID");

        PatientIdentifier patientId = getPatientIdentifiers(pid);

        boolean isValidMessage = validateMessage(reply, hl7Header, patientId, null, true);

        if (!isValidMessage)
        {
            log.fatal("\n" + "返回HL7消息：" + "\n" + reply);

            return reply;
        }

        // Invoke eMPI function
        MessageHeader header = hl7Header.toMessageHeader();
        Patient patient = PixFeedHandlerHelper.getPatient(pixAdapter,actor,msgIn);
        try
        {
            // Update Patient
            List<List<PatientIdentifier>> matchingList = null;

            if (!actor.isNotification())
            {
                matchingList = pixAdapter.updatePatient(patient, header);
            }

            /*
             * //PIX Update Notification to PIX consumers 
             * //yrh-2012-11-01
             * 非必要时关闭，IHE测试时可以打开推送 
             * if (matchingList != null) { 
             * for(List<PatientIdentifier> matching : matchingList) 
             * {
             *     IPixUpdateNotificationRequest matchingRequest = new PixUpdateNotificationRequest(actor, matching);
             *     PixUpdateNotifier.getInstance().accept(matchingRequest); 
             * } 
             * }
             */
        }
        catch (PixManagerException e)
        {
            throw new PixPdqException(e);
        }

        HL7v231.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());

        // Forward this PIX Feed (Merge) message to the XDS Registry
        // forwardToXdsRegistry(msgIn, patientId);

        // Finally, Audit Log PIX Feed Success
        // auditLog(hl7Header, patient,
        // AuditCodeMappings.EventActionCode.Update);

        log.fatal("\n" + "返回HL7消息：" + "\n" + reply);

        return reply;
    }

    /**
     * Processes PIX Feed Merge Patient message.
     * 
     * @param msgIn
     *            the PIX Feed request message
     * @return a response message for PIX Feed
     * @throws PixPdqException
     *             If Application has trouble
     * @throws HL7Exception
     *             if something is wrong with HL7 message
     * @throws com.ats.aempi.ApplicationException
     * @throws NamingException
     * @throws AuthenticationException
     */
    private Message processMerge(Message msgIn) throws PixPdqException, HL7Exception, com.ats.aempi.ApplicationException, AuthenticationException, NamingException
    {

        assert msgIn instanceof ADT_A39;

        HL7Header hl7Header = new HL7Header(msgIn);

        // Create Acknowledgment and its Header
        ACK reply = initAcknowledgment(hl7Header);

        // Validate incoming message first
        ADT_A39_PIDPD1MRGPV1 requestId = ((ADT_A39) msgIn).getPIDPD1MRGPV1();

        PatientIdentifier patientId = getPatientIdentifiers(requestId.getPID());

        // PatientIdentifier mrgPatientId =
        // getMrgPatientIdentifiers(requestId.getMRG());

        boolean isValidMessage = validateMessage(reply, hl7Header, patientId, null, true);

        if (!isValidMessage)
        {
            log.fatal("\n" + "返回HL7消息：" + "\n" + reply);

            return reply;
        }

        // Invoke eMPI function
        MessageHeader header = hl7Header.toMessageHeader();

        Patient patient = PixFeedHandlerHelper.getPatient(pixAdapter,actor,msgIn);

        Patient mrgPatient = getMrgPatient(msgIn);

        try
        {

            if (!actor.isNotification())
            {
                String ContrlID = hl7Header.getMessageControlId();

                if (ContrlID.equalsIgnoreCase("0"))
                {
                    pixAdapter.mergePatients(patient, mrgPatient, header);
                }
                else if (ContrlID.equalsIgnoreCase("1"))
                {
                    pixAdapter.ArtificialMergePatientID(patient, mrgPatient, header);
                }
                else if (ContrlID.equalsIgnoreCase("2"))
                {
                    pixAdapter.ArtificialCancelMergePatientID(patient, mrgPatient, header);
                }
            }
            // //PIX Update Notification to PIX consumers
            // if (matchingList != null) {
            // for (List<PatientIdentifier> matching : matchingList) {
            // IPixUpdateNotificationRequest matchingRequest =
            // new PixUpdateNotificationRequest(actor, matching);
            // PixUpdateNotifier.getInstance().accept(matchingRequest);
            // }
            // }
        }
        catch (PixManagerException e)
        {
            throw new PixPdqException(e);
        }

        HL7v231.populateMSA(reply.getMSA(), "AA", hl7Header.getMessageControlId());

        // //Forward this PIX Feed (Merge) message to the XDS Registry
        // forwardToXdsRegistry(msgIn, patientId);

        // Finally, Audit Log PIX Feed Success
        // auditLog(hl7Header, patient,
        // AuditCodeMappings.EventActionCode.Update);
        // auditLog(hl7Header, mrgPatient,
        // AuditCodeMappings.EventActionCode.Delete);

        log.fatal("\n" + "返回HL7消息：" + "\n" + reply);

        return reply;
    }

    private void processTrans(Message msgIn) throws PixPdqException, HL7Exception, ApplicationException
    {

        HL7Header hl7Header = new HL7Header(msgIn);

        // Invoke eMPI function
        MessageHeader header = hl7Header.toMessageHeader();

        Patient patient = PixFeedHandlerHelper.getPatient(pixAdapter,actor,msgIn);

        try
        {
            pixAdapter.TransPatient(patient, header, msgIn);
        }
        catch (PixManagerException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Audit Logging of PIX Feed message.
     * 
     * @param hl7Header
     *            the header message from the source application
     * @param patient
     *            the patient to create, update or merged
     * @param eventActionCode
     *            the {@link EventActionCode}
     */
    private void auditLog(HL7Header hl7Header, Patient patient, AuditCodeMappings.EventActionCode eventActionCode)
    {
        if (actor.getAuditTrail() == null)
            return;

        String userId = hl7Header.getSendingFacility().getNamespaceId() + "|" + hl7Header.getSendingApplication().getNamespaceId();
        String messageId = hl7Header.getMessageControlId();
        // TODO: Get the ip address of the source application
        String sourceIp = "127.0.0.1";

        ActiveParticipant source = new ActiveParticipant(userId, messageId, sourceIp);

        ParticipantObject patientObj = new ParticipantObject(patient);
        patientObj.addDetail(new TypeValuePair("MSH-10", hl7Header.getMessageControlId()));

        actor.getAuditTrail().logPixFeed(source, patientObj, eventActionCode);
    }

    /**
     * Initiates an acknowledgment instance for the incoming message.
     * 
     * @param hl7Header
     *            the message header of the incoming message
     * @return an {@link ACK} instance
     * @throws HL7Exception
     *             if something is wrong with HL7 message
     * @throws PixPdqException
     *             If Application has trouble
     */
    private ACK initAcknowledgment(HL7Header hl7Header) throws HL7Exception, PixPdqException
    {
        // Send Response
        ACK reply = new ACK();

        // For the response message, the ReceivingApplication and
        // ReceivingFacility
        // will become the sendingApplication and sendingFacility;
        // Also the sendingApplication and sendingFacility will become the
        // receivingApplication and receivingFacility.
        Identifier serverApplication = getServerApplication(actor.getConnection());
        Identifier serverFacility = getServerFacility(actor.getConnection());
        Identifier sendingApplication = hl7Header.getSendingApplication();
        Identifier sendingFacility = hl7Header.getSendingFacility();

        try
        {
            String event = hl7Header.getTriggerEvent();
            HL7v231.populateMSH(reply.getMSH(), "ACK", event, getMessageControlId(), serverApplication, serverFacility, sendingApplication, sendingFacility);
        }
        catch (IheConfigurationException e)
        {
            throw new PixPdqException("Error populating MSH segment", e);
        }

        return reply;
    }

    /**
     * Validates a patient identifier domain, namely, assigning authority.
     * 
     * @param reply
     *            the reply message to be populated if the validation fails
     * @param patientId
     *            the patient id
     * @param incomingMessageId
     *            the incoming message id
     * @return <code>true</code> if the patient domain is validated
     *         successfully; otherwise <code>false</code>.
     * @throws HL7Exception
     *             if something is wrong with HL7 message
     */
    private boolean validateDomain(ACK reply, PatientIdentifier patientId, String incomingMessageId) throws HL7Exception
    {
        Identifier domain = patientId.getAssigningAuthority();
        boolean domainOk = AssigningAuthorityUtil.validateDomain(domain, actor.getActorDescription(), pixAdapter);
        if (!domainOk)
        {
            HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
            // segmentId=PID, sequence=1, fieldPosition=3,
            // fieldRepetition=1,componentNubmer=4
            HL7v231.populateERR(reply.getERR(), "PID", "1", "3", "1", "4", "204", "机构名或者机构域ID配置错误，请检查输入是否有误，或者配置文件有误");
            return false;
        }
        return true;
    }

    /**
     * Validates the receiving facility and receiving application of an incoming
     * message.
     * 
     * @param reply
     *            the reply message to be populated if any validation is failed
     * @param receivingApplication
     *            the receiving application of the incoming message
     * @param receivingFacility
     *            the receiving facility of the incoming message
     * @param expectedApplication
     *            the expected receiving application
     * @param expectedFacility
     *            the expected receiving facility
     * @param incomingMessageId
     *            the incoming message
     * @return <code>true</code> if validation is passed; otherwise
     *         <code>false</code>.
     * @throws HL7Exception
     *             if something is wrong with HL7 message
     * @throws PixPdqException
     *             if something is wrong with the application
     */
    private boolean validateReceivingFacilityApplication(ACK reply, Identifier receivingApplication, Identifier receivingFacility, Identifier expectedApplication, Identifier expectedFacility,
            String incomingMessageId) throws HL7Exception, PixPdqException
    {
        // Validate ReceivingApplication and ReceivingFacility.
        // Currently we are not validating SendingApplication and
        // SendingFacility
        boolean validateApplication = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_APPLICATION);
        if (validateApplication && !receivingApplication.equals(expectedApplication))
        {
            HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
            // segmentId=MSH, sequence=1, fieldPosition=5, fieldRepetition=1,
            // componentNubmer=1
            HL7v231.populateERR(reply.getERR(), "MSH", "1", "5", "1", "1", null, "Unknown Receiving Application");
            return false;
        }

        boolean validateFacility = PropertyFacade.getBoolean(PixPdqConstants.VALIDATE_RECEIVING_FACILITY);
        if (validateFacility && !receivingFacility.equals(expectedFacility))
        {
            HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
            // segmentId=MSH, sequence=1, fieldPosition=6, fieldRepetition=1,
            // componentNubmer=1
            HL7v231.populateERR(reply.getERR(), "MSH", "1", "6", "1", "1", null, "Unknown Receiving Facility");
            return false;
        }

        return true;
    }

    /**
     * Validates the incoming Message in this order:
     * 
     * <ul>
     * <li>Validate Receiving Facility and Receiving Application</li>
     * <li>Validate Domain</li>
     * <li>Validate patient Id
     * <li>
     * <li>Validate merge patient Id if applicable
     * <li>
     * </ul>
     * 
     * @param reply
     *            the reply message to be populated if any validation is failed
     * @param hl7Header
     *            the message header of the incoming message
     * @param patientId
     *            the id of the patient to be validated
     * @param mrgPatientId
     *            the id of the patient to be merged
     * @param isPixCreate
     *            Whether this validation is for PIX patient creation
     * @return <code>true</code> if the message is correct; <code>false</code>
     *         otherwise.
     * @throws HL7Exception
     *             if something is wrong with HL7 message
     * @throws PixPdqException
     *             if something is wrong with the application
     */
    private boolean validateMessage(ACK reply, HL7Header hl7Header, PatientIdentifier patientId, PatientIdentifier mrgPatientId, boolean isPixCreate) throws HL7Exception, PixPdqException
    {
        Identifier serverApplication = getServerApplication(actor.getConnection());
        Identifier serverFacility = getServerFacility(actor.getConnection());
        Identifier receivingApplication = hl7Header.getReceivingApplication();
        Identifier receivingFacility = hl7Header.getReceivingFacility();
        String incomingMessageId = hl7Header.getMessageControlId();
        // 1. validate receiving facility and receiving application
        boolean isValidFacilityApplication = validateReceivingFacilityApplication(reply, receivingApplication, receivingFacility, serverApplication, serverFacility, incomingMessageId);
        if (!isValidFacilityApplication)
            return false;

        // 2.validate the domain
        boolean isValidDomain = validateDomain(reply, patientId, incomingMessageId);
        if (!isValidDomain)
            return false;

        // 3. validate ID itself
        if (!isPixCreate)
        {
            // Do not valid patient id for PIX patient creation
            boolean isValidPid = validatePatientId(reply, patientId, hl7Header.toMessageHeader(), false, incomingMessageId);
            if (!isValidPid)
                return false;
        }

        // 4. validate mrgPatientId
        if (mrgPatientId != null)
        {
            boolean isValidMrgPid = validatePatientId(reply, mrgPatientId, hl7Header.toMessageHeader(), true, incomingMessageId);
            if (!isValidMrgPid)
                return false;
        }

        // Finally, it must be true when it reaches here
        return true;
    }

    /**
     * Checks the given whether the given patient id is a valid patient id.
     * 
     * @param reply
     *            the reply message to be populated if any validation is failed
     * @param patientId
     *            the patient id to be checked
     * @param header
     *            the incoming message header
     * @param isMrgPatientId
     *            whether the patient id to be checked is a merge patient id.
     * @param incomingMessageId
     *            the incoming message id.
     * @return <code>true</code> if the patientId is valid; otherwise
     *         <code>false</code>.
     * @throws HL7Exception
     *             if something is wrong with HL7 message
     * @throws PixPdqException
     *             if something is wrong with the application
     */
    private boolean validatePatientId(ACK reply, PatientIdentifier patientId, MessageHeader header, boolean isMrgPatientId, String incomingMessageId) throws HL7Exception, PixPdqException
    {
        boolean validPatient;
        try
        {
            validPatient = pixAdapter.isValidPatient(patientId, header);
        }
        catch (PixManagerException e)
        {
            throw new PixPdqException(e);
        }
        if (!validPatient)
        {
            HL7v231.populateMSA(reply.getMSA(), "AE", incomingMessageId);
            if (isMrgPatientId)
            {
                // segmentId=MRG, sequence=1, fieldPosition=1,
                // fieldRepetition=1, componentNubmer=1
                HL7v231.populateERR(reply.getERR(), "MRG", "1", "1", "1", "1", "204", "Unknown Key Identifier");
            }
            else
            {
                // segmentId=PID, sequence=1, fieldPosition=3,
                // fieldRepetition=1, componentNubmer=1
                HL7v231.populateERR(reply.getERR(), "PID", "1", "3", "1", "1", "204", "病人ID不存在");
            }
        }
        return validPatient;
    }

    private List<PatientIdentifier> getPatientIdList(Message msgIn) throws PixPdqException, HL7Exception
    {
        HL7v231ToBaseConvertor convertor = null;

        // 如果是2.3.1版本 NEW个HL7变量
        if (msgIn.getVersion().equals("2.3.1"))
        {
            convertor = new HL7v231ToBaseConvertor(msgIn, actor.getActorDescription(), pixAdapter);

        }
        else
        {
            throw new PixPdqException("Unexpected HL7 version");
        }

        return convertor.getPatientIds();
    }

   
    /**
     * Extracts the merge patient out of a PIX Merge Patient message.
     * 
     * @param msgIn
     *            the incoming PIX Merge message
     * @return a {@link Patient} object that represents the merge patient
     * @throws HL7Exception
     *             if something is wrong with the application
     */
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

    /**
     * Gets the patient identifier from a Patient PID segment.
     * 
     * @param pid
     *            the PID segment
     * @return a {@link PatientIdentifier}
     */
    private PatientIdentifier getPatientIdentifiers(PID pid)
    {
        PatientIdentifier identifier = new PatientIdentifier();
        CX[] cxs = pid.getPatientIdentifierList();
        for (CX cx : cxs)
        {
            Identifier assignAuth = new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(), cx.getAssigningAuthority().getUniversalID().getValue(), cx.getAssigningAuthority()
                    .getUniversalIDType().getValue());
            Identifier assignFac = new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(), cx.getAssigningFacility().getUniversalID().getValue(), cx.getAssigningFacility()
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
     * @param MRG
     *            segment the merge segment
     * @return a {@link PatientIdentifier}
     */
    private PatientIdentifier getMrgPatientIdentifiers(MRG mrg)
    {
        PatientIdentifier identifier = new PatientIdentifier();
        CX[] cxs = mrg.getPriorPatientIdentifierList();
        for (CX cx : cxs)
        {
            Identifier assignAuth = new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(), cx.getAssigningAuthority().getUniversalID().getValue(), cx.getAssigningAuthority()
                    .getUniversalIDType().getValue());
            Identifier assignFac = new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(), cx.getAssigningFacility().getUniversalID().getValue(), cx.getAssigningFacility()
                    .getUniversalIDType().getValue());
            identifier.setAssigningAuthority(AssigningAuthorityUtil.reconcileIdentifier(assignAuth, actor.getActorDescription(), pixAdapter));
            identifier.setAssigningFacility(assignFac);
            identifier.setId(cx.getID().getValue());
            identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
        }
        return identifier;
    }

    /**
     * Forwards this PIX Feed message to the XDS Registry in the affinity
     * domain. The XDS registry in the affinity domain is interested in patient
     * IDs in only the global (master) assigning authority (domain). So messages
     * for non-global patient IDs are filtered out. Also, be sure to configure
     * XDS Registry connection in the Actor configuration. See the relevant
     * actor configuration documentation.
     * 
     * @param msgIn
     *            the incoming PIX Feed message to be forwarded to the XDS
     *            Registry
     * @param patientId
     *            the ID of the patient of PIX Feed. For patient creation and
     *            update, it is the main patient ID; for patient merge, it is
     *            the surviving patient ID.
     */
    private void forwardToXdsRegistry(Message msgIn, PatientIdentifier patientId)
    {
        // Ignore it if XDS registry is not configured
        IConnectionDescription registryConnection = actor.getXdsRegistryConnection();
        if (registryConnection == null)
            return;

        MessageStore store = null;
        try
        {
            // Forward to XDS Registry only those messages associated with
            // global patients
            Identifier defaultGlobalDomain = Configuration.getGlobalDomain(actor.getActorDescription(), false);
            Identifier globalDomain = pixAdapter.getGlobalDomainIdentifier(defaultGlobalDomain);

            // System.out.println(patientId.getAssigningAuthority().toString());

            if (!patientId.getAssigningAuthority().equals(globalDomain))
                return;

            log.warn("Forward the PIX Feed to the XDS Registry" + registryConnection);

            store = MessageStoreHelper.initMessageStore(msgIn, actor.getStoreLogger(), false);
            HL7Header header = new HL7Header(msgIn);
            header.populateMessageStore(store);

            HL7Channel channel = new HL7Channel(registryConnection);
            Message ack = channel.sendMessage(msgIn);

            // System.out.println("推送消息:" + msgIn);

            log.fatal("推送消息:" + msgIn);
            boolean ok = processPixFeedResponse(ack, registryConnection);

        }
        catch (Exception e)
        {
            String errorMsg = "Cannot send PIX Feed to XDS Registry: " + registryConnection.getDescription();
            errorMsg += " Error Message:" + e.getMessage();
            log.error(errorMsg);
            if (store != null)
            {
                store.setErrorMessage(errorMsg);
            }
        }
        finally
        {
            // Persist the message
            if (actor.getStoreLogger() != null && store != null)
            {
                actor.getStoreLogger().saveLog(store);
            }
        }
    }

    /**
     * Checks the response to the patient identity feed to ensure that it was a
     * success.
     * 
     * @param response
     *            the response from the patient identity feed consumer
     * @param connection
     *            the connection from which the response is from
     * @return <code>true</code> if the PIX Feed message was accepted
     */
    private boolean processPixFeedResponse(Message response, IConnectionDescription connection) throws PixManagerException
    {
        // Make sure the response is the right type of message
        ACK message = null;
        if (response instanceof ACK)
        {
            message = (ACK) response;
        }
        else
        {
            actor.logHL7MessageError(log, message, "Unexpected response");
            throw new PixManagerException("Unexpected response from \"" + connection.getDescription());
        }
        // Check the MSA segment ...
        String status = message.getMSA().getAcknowledgementCode().getValue();
        if ((status == null) || (!status.equalsIgnoreCase("AA") && !status.equalsIgnoreCase("CA")))
        {
            // The server has rejected our request, or generated an error
            String mtext = message.getMSA().getTextMessage().getValue();
            String code = message.getMSA().getErrorCondition().getIdentifier().getValue();
            String etext = message.getMSA().getErrorCondition().getText().getValue();
            String error = null;
            if (code != null)
                error = "(" + code + ") " + HL7v231.getErrorString(code);
            if (mtext != null)
                error = error + " - " + mtext;
            if (etext != null)
                error = " [" + etext + "]";
            if (error == null)
            {
                message.getERR();
                ca.uhn.hl7v2.model.v231.segment.ERR err = message.getERR();
                if (err != null)
                {
                    // Message = err.getMessage().get;
                    try
                    {
                        ELD eld = err.getErrorCodeAndLocation(0);
                        if (eld != null)
                        {
                            CE ce = eld.getCodeIdentifyingError();
                            if (ce != null)
                            {
                                ST errorcode = ce.getIdentifier();
                                if (errorcode != null)
                                {
                                    error = "(" + errorcode.getValue() + ") " + HL7v231.getErrorString(errorcode.getValue());
                                }
                                ST text = ce.getText();
                                if (text != null)
                                    error = error + "-" + text.getValue();
                            }
                        }
                    }
                    catch (Exception e)
                    { // do nothing if we cannot get
                        // anything from ERR.
                    }
                }
            }
            if (error == null)
                error = "Unspecified error";

            error = "Error response from \"" + connection.getDescription() + "\": " + error;
            actor.logHL7MessageError(log, message, error);
            throw new PixManagerException(error);
        }
        // Okay, we're good
        return true;
    }

    /**
     * Send an HL7 message over this channel to the XDS Registry and return the
     * response.
     * 
     * @param message
     *            The HL7 message to be sent
     * @return The HL7 message that came back as a response
     * @throws IOException
     *             When there is a problem communicating with the server
     */
    public Message sendMessage(Message message) throws IOException
    {
        // Open the XDS Registry TCP connection
        IConnection conn = ConnectionFactory.getConnection(actor.getXdsRegistryConnection());
        if (!conn.isConnectionValid())
        {
            throw new IOException("Cannot open XDS Registry connection to \"" + actor.getXdsRegistryConnection().getDescription() + "\"");
        }
        // Create the HL7 connection using this TCP connection
        try
        {
            PipeParser parser = new PipeParser();
            parser.setValidationContext(new MessageValidation());
            hl7Connection = new Connection(parser, new MinLowerLayerProtocol(), conn.getSocket());
        }
        catch (LLPException e)
        {
            // Error in HAPI configuration
            log.error("Cannot find HL7 low level protocol implementation", e);
            conn.closeConnection();
            return null;
        }
        catch (IOException e)
        {
            // Error communicating over socket
            conn.closeConnection();
            throw e;
        }
        // Get an initator to send the message
        Initiator initiator = hl7Connection.getInitiator();
        // Okay, send the message
        Message response = null;
        try
        {
            response = initiator.sendAndReceive(message);
        }
        catch (HL7Exception e)
        {
            // Improper HL7 message formatting (on send or receive)

            log.error("Improper HL7 message formatting", e);
            hl7Connection.close();
            hl7Connection = null;
            return null;
        }
        catch (LLPException e)
        {
            // Can't use Lower Level Protocol for some reason
            log.error("HL7 protocol error", e);
            hl7Connection.close();
            hl7Connection = null;
            return null;
        }
        catch (IOException e)
        {
            // Can't communicate over socket
            hl7Connection.close();
            hl7Connection = null;
            throw e;
        }
        hl7Connection.close();
        hl7Connection = null;
        return response;
    }

}
