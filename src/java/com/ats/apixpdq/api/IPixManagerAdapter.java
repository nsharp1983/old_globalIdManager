package com.ats.apixpdq.api;

import java.util.List;

import javax.naming.NamingException;

import ca.uhn.hl7v2.model.Message;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aempi.model.ExtendForPerson;

/** 
 * This PIXManager Adapter is the bridge between IHE PIX Manager 
 * actor and underneath eMPI. OpenPIXPDQ, according to the IHE 
 * PIX specifications, invokes the methods in this Adapter, whose 
 * implementation is provided by the underneath eMPI.  
 *
 * @author Wenzhi Li
 * @version 1.0, Oct 21, 2008
 */
public interface IPixManagerAdapter extends IPixPdqAdapter {

    /**
     * Whether the given patient is a valid patient in the eMPI database.
     *
     * @param pid the {@link PatientIdentifier} to be checked
	 * @param header the <code>MessageHeader</code> from the incoming PIX client message
     * @return <code>true</code> if the patient id is valid; <code>false</code> otherwise.
     */
    public boolean isValidPatient(PatientIdentifier pid, MessageHeader header) throws PixManagerException;

    /**
     * Finds from the underneath eMPI all patient ids cross all patient 
     * domains (assigning authorities) given a patient id in a particular domain.
     * All retrieved patient ids must represent the same logic patient, 
     * though they may exist in different patient id domains.  
     *
     * @param header the <code>MessageHeader</code> of the incoming PIX Query message
     * @return A list of PatientIdentifier which does not include the request patient id.
     *         Return an empty list instead of null if no matching is found.
     * @throws PixManagerException when there is trouble cross finding all patients
     */
    public List<PatientIdentifier> findPatientIds(PatientIdentifier pid, MessageHeader header) throws PixManagerException;
    
	/**
	 * Creates a new patient in the eMPI database.  This method 
	 * sends the patient demographics contained
	 * in the <code>Patient</code> to the underneath eMPI.
	 * <p>
	 * 
	 * @param patient the demographics of the patient to be created
     * @param header the <code>MessageHeader</code> of the incoming PIX Feed message
     * @return a list of new matching {@link PatientIdentifier}s of this patient 
     *           as a result of creating this patient. OpenPIXPDQ will send a PIX 
     *           Update Notification message for this list to those PIX Consumers
     *           that have subscribed to PIX Update Notification.
     *           <p>
     *           If PIX Update Notification is not supported, or if there 
     *           is no matching (i.e, the patient is registered for the first time,
     *           no need to send PIX Update Notification Message), an empty list or null
     *           can be returned.
	 * @throws PixManagerException When there is trouble creating the patient
	 * @throws ApplicationException 
	 */
	public List<PatientIdentifier> createPatient(Patient patient, MessageHeader header) 
	throws PixManagerException, ApplicationException;

	/**
	 * Updates the patient's demographics in the eMPI's database. This 
	 * method sends the updated patient demographics contained
	 * in the <code>Patient</code> to the underneath eMPI.
	 * 
	 * @param patient the new demographics of the patient to be updated
     * @param header the <code>MessageHeader</code> of the incoming PIX Update message
     * @return a list of list of updated matching {@link PatientIdentifier}s
     *           as a result of updating this patient. The outer list is used to 
     *           store different logic patients, while each inner list represents 
     *           the same logic patient with matching patient ids across patient id domains.
     *           For each inner list (matching list), OpenPIXPDQ will send a PIX Update 
     *           Notification message to those PIX Consumers that have subscribed 
     *           to PIX Update Notification. 
     *           <p>
     *           For example, if patient(A)'s address is 
     *           updated, and this results in an un-matching of originally matched 
     *           patients (A, B, C & D), two lists are created, one list representing 
     *           updated matching patients (A, E & F); the other one representing updated 
     *           un-matching patients (B, C & D). 
     *           <p>
     *           If PIX Update Notification is not supported, or if there is no update 
     *           on the patient matching list, just return an empty list or null.  
     *            
	 * @throws PixManagerException when there is trouble updating the patient
	 */
	public List<List<PatientIdentifier>> updatePatient(Patient patient, MessageHeader header) 
	throws PixManagerException;

	/**
	 * WHEN CHANGE THE PATIENT ,SAVE THE HISTORY RECORD
	 * 
	 * @param patient the new demographics of the patient to be updated
     * @param header the <code>MessageHeader</code> of the incoming PIX Update message
     * @return a list of list of changed matching {@link PatientIdentifier}s
     *           as a result of updating this patient. The outer list is used to 
     *           store different logic patients, while each inner list represents 
     *           the same logic patient with matching patient ids across patient id domains.
     *           For each inner list (matching list), OpenPIXPDQ will send a PIX Update 
     *           Notification message to those PIX Consumers that have subscribed 
     *           to PIX Update Notification. 
     *           <p>
     *           For example, if patient(A)'s address is 
     *           updated, and this results in an un-matching of originally matched 
     *           patients (A, B, C & D), two lists are created, one list representing 
     *           updated matching patients (A, E & F); the other one representing updated 
     *           un-matching patients (B, C & D). 
     *           <p>
     *           If PIX Change Notification is not supported, or if there is no change
     *           on the patient matching list, just return an empty list or null.  
     *            
	 * @throws PixManagerException when there is trouble changing the patient
	 */
	public void ChangePatient(Patient patient, MessageHeader header) throws PixManagerException;
	
	public void TransPatient(Patient patient, MessageHeader header,Message msgIn) throws PixManagerException;
	
	/**
	 * DELETE THE PATIENT
	 * @throws PixManagerException when there is trouble changing the patient
	 */
	public void DeletePatient(Patient patient, MessageHeader header) throws PixManagerException;
	
	
	/**
	 * Merges two patients together because they have been found to be
	 * the same patient.  The first argument describes the surviving patient 
	 * demographics; the second argument represents the patient to be merged
	 * with the surviving patient. This method sends the surviving and merged
	 * patients to the underneath eMPI.
	 * 
	 * @param patientMain the surviving patient
	 * @param patientOld the patient to be replaced, and merged with the surviving patient
     * @param header the <code>MessageHeader</code> of the incoming PIX Merge message
     * @return a list of list of updated matching {@link PatientIdentifier}s
     *           as a result of merging patients. The outer list is used to 
     *           store different logic patients, while each inner list represents 
     *           the same logic patient with matching patient ids across patient id domains.
     *           For each inner list (matching list), OpenPIXPDQ will send a PIX Update 
     *           Notification message to those PIX Consumers that have subscribed 
     *           to PIX Update Notification. 
     *           <p>
     *           If PIX Update Notification is not supported, or if there is no update 
     *           on the patient matching list, just return an empty list or null.  
     * 
	 * @throws PixManagerException when there is trouble merging the patients
	 * @throws ApplicationException 
	 * @throws NamingException 
	 * @throws AuthenticationException 
	 */
	public void mergePatients(Patient patientMain,Patient patientOld, MessageHeader header) throws PixManagerException, ApplicationException, AuthenticationException, NamingException;
	
	public void changePatientID(Patient patientMain,Patient patientOld, MessageHeader header) throws PixManagerException;
	
	public void ArtificialMergePatientID(Patient patientMain,Patient patientOld, MessageHeader header) throws PixManagerException, ApplicationException;
	
	public void ArtificialCancelMergePatientID(Patient patientMain,Patient patientOld, MessageHeader header) throws PixManagerException, ApplicationException;
	
	/**
	 * 根据EXTENDFORPERSON表的配置设置扩展字段对应的身份识别信息的保存
	 * 扩展字段包括：门诊号、门诊流水号、住院号、住院流水号、等等
	 * 扩展字段统一使用PID4段来扩充完成
	 * 
	 * @param 获取存放扩展的身份识别信息字段，如门诊号、门诊流水号、住院号、住院流水号
     * @return EXTENNDFORPERSON表的对应扩展字段存放处  
     * 
	 * @throws 获取数据错误
	 */
	public List<ExtendForPerson> ExtendFieldForPatient() throws PixManagerException;


	/**
	 * 修改返回值---病人注册接口   yrh   20180929
	 * @param patient
	 * @return
	 * @throws PixManagerException
	 * @throws ApplicationException
	 */
	public Person registPatient(Patient patient) throws PixManagerException, ApplicationException;


	/**
	 * 查询患者ADT信息-----20181213
	 * @param person
	 * @return
	 */
	public PatientVisit queryPatientVisit(Person person)throws Exception;



}
