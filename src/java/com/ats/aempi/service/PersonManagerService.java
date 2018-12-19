package com.ats.aempi.service;

import java.util.List;

import javax.naming.NamingException;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.model.*;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.apixpdq.common.PixPdqException;

import com.ats.apixpdq.web.beans.PixManagerBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Primary interface for the OpenEMPI. It provides access to most of the functionality
 * that is typically available by an EMPI.
 * 
 * @author Odysseas Pentakalos
 * @version $Revision:  $ $Date:  $
 */
public interface PersonManagerService
{
	/**
	 * 从EXTENDFORPERSON表查询字段
	 * @param ExtendForPerson
	 * @throws HL7Exception 
	 * @throws PixPdqException 
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ExtendForPerson> FindExtendField(int PidCount) throws ApplicationException;
	
	/**
	 * 从EXTENDFORPERSON表查询字段
	 * @param ExtendForPerson
	 * @throws HL7Exception 
	 * @throws PixPdqException 
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<ExtendForPerson> ExtendField() throws ApplicationException;
	
	
	/**
	 * Adds a person record to the EMPI. The system will first check to see if a user with the same identifier is already known to the system. If the person
	 * is known already then nothing further will be done. If the person record is new, then the system will first add the person to the system and will
	 * then invoke the matching algorithm to determine if the person is already known through other aliases.
	 * 
	 * @param person
	 * @throws HL7Exception 
	 * @throws PixPdqException 
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Person addPerson(Person person,PatientVisit patientvisit,List<Contactperson> contactperson) throws ApplicationException, PixPdqException, HL7Exception;
	
	/**
	 * Updates the attributes maintained in the EMPI about the person. The system will locate the person record using the person identifiers as
	 * search criteria. If the record is not found, an exception is thrown. The attributes provided by the caller are used to update the person's record
	 * and then the matching algorithm is invoked to adjust the associations between person records based on the modifications that were made to the
	 * person's attributes.
	 * 
	 * @param person
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updatePerson(Person foundPerson,Person person,PatientVisit foundPatientVisit,PatientVisit patientvisit,List<String> PersonInfo,List<String> OldLinks,int ControlID) throws ApplicationException;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updatePersonID(Person person,PersonIdentifierEMPI changeIdentifier) throws ApplicationException;
	
	/**
	 * Delete the PatientVisit
	 * 
	 * @param person
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void DeletePatientVisit(Person person,PatientVisit patientvisit,List<String> PersonInfo) throws ApplicationException;
	
	/**
	 * Delete the Person Record
	 * 
	 * @param person
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void DeletePersonRecord(Person person) throws ApplicationException;
	
	
	/**
	 * Change the person
	 * 
	 * @param person
	 * @throws HL7Exception 
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void TransPerson(Person person,PatientVisit patientvisit,List<Contactperson> contactperson,MessageHeader messageHeader,Message msgIn) throws ApplicationException, HL7Exception;
	
	
	/**
	 * Change the person
	 * 
	 * @param person
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void ChangePerson(Person person,PatientVisit patientvisit,PatientVisit visitFound,List<String> PersonInfo) throws ApplicationException;
	/**
	 * Deletes a person from the EMPI. The system locates the person record using the person identifiers as search criteria. If the record is not
	 * found an unchecked exception is thrown to notify the caller that this record does not exist in the system. If the record is found, the record
	 * is voided from the system rather than deleted to preserve a history.
	 *  
	 *  @param personIdentifier
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void deletePerson(PersonIdentifierEMPI personIdentifier) throws ApplicationException;
	
	/**
	 * Merges a person into another person record. This operation is usually the result of the creation of two duplicate records in the EMPI by one of the
	 * participating identifier domains. Once the error is identified at the source, it is corrected by merging the two patient records into a single
	 * record.
	 * @param retiredIdentifier to be retired
	 * @param survivingIdentifer to be preserved
	 * @throws NamingException 
	 * @throws AuthenticationException 
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void mergePersons(PersonIdentifierEMPI retiredIdentifier, PersonIdentifierEMPI survivingIdentifer) throws ApplicationException, AuthenticationException, NamingException;
	
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void ArtificiamergePersons(Person leftperson,Person rightperson) throws ApplicationException;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void CancelmergePersons(Person leftperson,Person rightperson,List<String> MergeLiks) throws ApplicationException;
	
	/**
	 * Locates a person record using the person's identifier(s)
	 * 
	 * @param person
	 */
	public Person getPerson(List<PersonIdentifierEMPI> personIdentifiers);
	
	/**
	 * Imports a person record to the EMPI. The system will first check to see if a user with the same identifier is already known to the system. If the person
	 * is known already then nothing further will be done. If the person record is new, then the system will add the person to the system.
	 * Unlike the addPerson method, the importPerson method does not invoke the matching logic and is intended for use by 
	 * data loaders that initialize the EMPI with records from another MPI before identifying duplicates.
	 *  
	 * @param person
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Person importPerson(Person person) throws ApplicationException;
	
	/**
	 * This method generates a unique identifier domain identifier within the given universalIdentifierTypeCode. This method
	 * is intended for cases where new affinity domains are registering with OpenEMPI to join the group of affinity domains
	 * for which OpenEMPI collects person identity information for.
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public IdentifierDomainDict obtainUniqueIdentifierDomain(String universalIdentifierTypeCode);
	
	/**
	 * The addIdentifierDomainAttribute method allows the caller to associate with a given identifier
	 * domain an attribute. The attribute consists of a name-value pair. This functionality is useful
	 * when OpenEMPI is used to support a Record Locator Service-type EHR and then each identifier domain
	 * corresponds to a site that provides patient services so it is useful to be able to associate
	 * arbitrary attributes to an identifier domain. Those attributes can be used to provide
	 * more user-friendly information about a health care provider site or institution.
	 *  
	 * @param identifierDomain This must be an instance of an identifier domain that has been obtained
	 * either from a prior call to getIdentifierDomains() or via an association between an identifier
	 * domain and a person identifier. The only attribute that must be present is the identifierDomainId
	 * attribute which is only useful internally to an OpenEMPI instance.
	 * 
	 * @param attributeName The name portion of the attribute
	 * @param attributeValue The value portion of the attribute
	 * @return Returns an instance of an IdentifierDomainAttribute object that was persisted after a successful
	 * add operation completion or null if the add operation fails.
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public IdentifierDomainAttribute addIdentifierDomainAttribute(IdentifierDomainDict identifierDomain, String attributeName, String attributeValue);
	
	/**
	 * This method updates an existing identifier domain attribute with the name and value specified.
	 * 
	 * @param identifierDomainAttribute
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute);

	/**
	 * This method removes an existing identifier domain attribute. The identifier domain attribute id
	 * attribute must be populated.
	 * 
	 * @param identifierDomainAttribute
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void removeIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute);
	
	/**
	 * This method invokes the underlying matching algorithm of the system and requests that
	 * all associations between records that have been point to the same physical entity should
	 * be deleted and new ones should be defined again from scratch. This operation is time
	 * consuming since it will cause the matching algorithm to attempt to match all the duplicate
	 * records in the repository.
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void initializeRepository() throws ApplicationException;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public List<String> getPersonLinks(String personid);


	/**
	 * 根据病人id查询该病人是否已经注册----2018yrh
	 * @param identifier
	 * @return
	 */
	public Person findByPatientId(PersonIdentifierEMPI identifier);
	/**
	 * 根据病人id查询该病人是否已经注册----2018yrh
	 * @param person
	 * @return
	 */
	public List<NewPerson> queryPatients(Person person) throws Exception;

	/**
	 * 查询患者ADT信息-----20181213
	 * @param person
	 * @return
	 */
	public PatientVisit queryPatientVisit(Person person)throws Exception;



}
