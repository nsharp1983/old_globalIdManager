package com.ats.aempi.service;

import java.util.List;

import com.ats.aempi.model.GenderDict;
import com.ats.aempi.model.IdentifierDomainDict;
import com.ats.aempi.model.IdentifierDomainAttribute;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.model.RaceDict;

public interface PersonQueryService
{
	
	/**
	 * Returns the list of identifier domains known by the system
	 * 
	 * @return
	 */
	public List<IdentifierDomainDict> getIdentifierDomains();
	
	/**
	 * Returns the list of distinct identifier domain type codes
	 * 
	 */
	public List<String> getIdentifierDomainTypeCodes();
	
	/**
	 * Returns an instance of an IdentifierDomainAttribute associated with the identifier domain passed in and with the 
	 * name passed in as attributeName.
	 * 
	 * @param identifierDomain This must be an instance of an identifier domain that has been obtained
	 * either from a prior call to getIdentifierDomains() or via an association between an identifier
	 * domain and a person identifier. The only attribute that must be present is the identifierDomainId
	 * attribute which is only useful internally to an AEMPI instance.
	 * @param attributeName The name of the attribute.
	 * @return
	 */
	public IdentifierDomainAttribute getIdentifierDomainAttribute(IdentifierDomainDict identifierDomain, String attributeName);
	
	/**
	 * This method returns a list of all the identifier domain attributes associated with a given identifier domain.
	 * 
	 * @param identifierDomain This must be an instance of an identifier domain that has been obtained
	 * either from a prior call to getIdentifierDomains() or via an association between an identifier
	 * domain and a person identifier. The only attribute that must be present is the identifierDomainId
	 * attribute which is only useful internally to an AEMPI instance.
	 * @return List of IdentifierDomainAttributes found.
	 */
	public List<IdentifierDomainAttribute> getIdentifierDomainAttributes(IdentifierDomainDict identifierDomain);
	
	public Person findPersonById(PersonIdentifierEMPI identifier);
	
	public Person findPersonById2(PersonIdentifierEMPI identifier);
	
	public List<Person> findPersonById3(Person person);
	
	public PatientVisit findPatientVisitById(Person person);
	
	public List<PatientVisit> findPatientVisitListById(Person person);
	
	public List<PatientVisit> findPatientVisitListByHisId(Person person,String visitflowid);
	
	/**
	 * This method returns a list of all person records that have either an exact or partial match with the
	 * identifier that was provided. The search accepts wildcards when searching on the identifier but will
	 * filter the results on an exact match on any of the identifier domain attributes that are provided.
	 * 
	 * @param identifier Expects the identifier with possible wildcards (such as 555-%) but can filter
	 * out the result set by including identifier domain attributes
	 * @return List of person records that match the search criteria
	 */
	public List<Person> findPersonsById(PersonIdentifierEMPI identifier);
	
	public List<Person> findPersonVisitById(PersonIdentifierEMPI identifier,PatientVisit patientvisit);

	public List<Person> findLinkedPersons(PersonIdentifierEMPI identifier);
	
	public Person loadPerson(Integer personId);
	
	public List<Person> loadPersons(List<Integer> personIds);
	
	public List<Person> loadAllPersonsPaged(int firstRecord, int maxRecords);
	
	/**
	 * This method returns a list of all person records that match any of the person attributes that
	 * are provided in the search object which acts as a template.
	 * 
	 * @param person Person object with any attributes provided as search criteria
	 * @return List of person records that match the search criteria
	 */
	public List<Person> findPersonsByAttributes(Person person);
	
	public List<Person> findPersonVisitByAttributes(Person person,PatientVisit patientvisit);
	
	/**
	 * This method returns a page of person records that match any of the person attributes that
	 * are provided in the search object which acts as a template.
	 * 
	 * @param person Person object with any attributes provided as search criteria
	 * @param firstResult Start index of the page
	 * @param maxResults Size of the page
	 * @return List of person records that match the search criteria
	 */
	public List<Person> findPersonsByAttributesPaged(Person person, int firstResult,
			int maxResults);
	/**
	 * This method returns a list of all person attributes that are supported by the
	 * current model, except those which designated to be custom field holders.
	 * The list of attributes can be used for the purpose of defining the blocking
	 * algorithm, the matching algorithm, or to extract information about what the model
	 * supports.
	 * 
	 * @return A sorted list of the attributes
	 */
	public List<String> getPersonModelAttributeNames();
	
	/**
	 * This method returns a list of all person attributes which designated to be custom
	 * field holders. The list of attributes can be used for the purpose of defining the blocking
	 * algorithm, the matching algorithm, or to extract information about what the model
	 * supports.
	 * 
	 * @return A sorted list of the custom field attributes
	 */
	public List<String> getPersonModelCustomAttributeNames();
	
	public RaceDict findRaceByCode(String raceCode);
	
	public RaceDict findRaceByName(String raceName);
	
	public GenderDict findGenderByCode(String genderCode);
	
	public GenderDict findGenderByName(String genderName);
	
	public List<PersonIdentifierEMPI> GetPersonPid(Person person);
}
