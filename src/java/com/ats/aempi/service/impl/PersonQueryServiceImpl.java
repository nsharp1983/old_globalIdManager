package com.ats.aempi.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import com.ats.aempi.context.Context;
import com.ats.aempi.dao.EmpiDao;
import com.ats.aempi.dao.PatientVisitDao;
import com.ats.aempi.dao.PersonDao;
import com.ats.aempi.dao.PersonLinkDao;
import com.ats.aempi.model.Criteria;
import com.ats.aempi.model.Criterion;
import com.ats.aempi.model.ExtendedCriterion;
import com.ats.aempi.model.GenderDict;
import com.ats.aempi.model.IdentifierDomainDict;
import com.ats.aempi.model.IdentifierDomainAttribute;
import com.ats.aempi.model.Operation;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.model.PersonLink;
import com.ats.aempi.model.RaceDict;
import com.ats.aempi.model.Record;
import com.ats.aempi.service.PersonQueryService;
import com.ats.aempi.service.ValidationService;
import com.ats.aempi.util.ConvertUtil;

public class PersonQueryServiceImpl extends BaseServiceImpl implements PersonQueryService
{
	private PersonDao personDao;
	private PersonLinkDao personLinkDao;
	private PatientVisitDao patientvisitDao;
	private EmpiDao empiDao;

	DateFormat dateFormat =new SimpleDateFormat("yyyyMMdd"); 

	public Person findPersonById(PersonIdentifierEMPI personIdentifier) {
		
		ValidationService validationService = Context.getValidationService();
		validationService.validate(personIdentifier);
		
		return personDao.getPersonById(personIdentifier);
	}
	
	 public Person findPersonById2(PersonIdentifierEMPI personIdentifier) {
		
		ValidationService validationService = Context.getValidationService();
		validationService.validate(personIdentifier);
		
		return personDao.getPersonById2(personIdentifier);
	}

	 public List<Person> findPersonById3(Person person) {
		 return personDao.getPersonById3(person);
	 }

	public PatientVisit findPatientVisitById(Person person) {
		
		ValidationService validationService = Context.getValidationService();
		validationService.validate(person);
		
		return patientvisitDao.getPatientVisitById(person);
	}
	
	public List<PatientVisit> findPatientVisitListByHisId(Person person,String visitflowid)
	{
		ValidationService validationService = Context.getValidationService();
		validationService.validate(person);
		
		return patientvisitDao.getPatientVisitsByHisId(person,visitflowid);
	}
	
	public List<PatientVisit> findPatientVisitListById(Person person)
	{
		ValidationService validationService = Context.getValidationService();
		validationService.validate(person);
		
		return patientvisitDao.getPatientVisitsById(person);
	}

	public Person loadPerson(Integer personId) {
		if (personId == null || personId.intValue() == 0) {
			return null;
		}
		Person foundPerson = personDao.loadPerson(personId);
		return foundPerson;
	}
	
	public List<Person> loadPersons(List<Integer> personIds) {
		if (personIds == null || personIds.size() == 0) {
			return null;
		}
		List<Person> persons = personDao.loadPersons(personIds);
		hydrateObjects(persons);
		return persons;
	}
	
	public List<Person> loadAllPersonsPaged(int firstRecord, int maxRecords) {
			return findPersonsByAttributesPaged(new Person(), firstRecord, maxRecords);
	}

	public List<Person> findPersonsById(PersonIdentifierEMPI personIdentifier) 
	{	
		ValidationService validationService = Context.getValidationService();
		
		validationService.validate(personIdentifier);
		
		return personDao.getPersonsByIdentifier(personIdentifier);
	}
	
	public List<Person> findPersonVisitById(PersonIdentifierEMPI personIdentifier,PatientVisit patientvisit)
	{		
		ValidationService validationService = Context.getValidationService();
		
		validationService.validate(personIdentifier);
		
		validationService.validate(patientvisit);
		
		return patientvisitDao.getPersonVisitById(personIdentifier,patientvisit);
	}

	public List<Person> findLinkedPersons(PersonIdentifierEMPI identifier) {
		ValidationService validationService = Context.getValidationService();
		validationService.validate(identifier);
		
		List<Person> linkedPersons = new ArrayList<Person>();
		Person person = personDao.getPersonById(identifier);
		if (person == null) {
			return linkedPersons;
		}
		
		List<PersonLink> links = personLinkDao.getPersonLinks(person);
		for (PersonLink link : links) {
			if (String.valueOf(person.getPersonId()).equals(String.valueOf(link.getPersonByLhPersonId().getPersonId()))) {
				Person p = link.getPersonByRhPersonId();
				//System.out.println(p.getPersonIdentifiers().size());
				//System.out.println(p.getPersonId());
				//System.out.println(p.getPersonIdentifiers().toArray()[0].toString());
				linkedPersons.add(p);
			} else {
				Person p = link.getPersonByLhPersonId();
				//p.getPersonIdentifiers().size();;
				//System.out.println(p.getPersonId());
				//System.out.println(p.getPersonIdentifiers());
				linkedPersons.add(p);
			}
		}
//		hydrateObjects(linkedPersons);
		
		//for(Person p:linkedPersons)
		//{
		//	System.out.println(p.getPersonId());
		//	System.out.println(p.getPersonIdentifiers());
		//}
		
		return linkedPersons;
	}
	
	public List<Person> findPersonsByAttributes(Person person) {
		ValidationService validationService = Context.getValidationService();
		validationService.validate(person);
		
		Record record = new Record(person);
		Criteria criteria = buildCriteriaFromProperties(record);
		addIndirectCriteria(person, criteria);
		List<Person> persons;
		if (person.getPersonIdentifiers() != null && person.getPersonIdentifiers().size() > 0) {
			persons = personDao.getPersons(criteria, person.getPersonIdentifiers());
		} else {
			persons = personDao.getPersons(criteria);
		}
		hydrateObjects(persons);
		return persons;
	}
	
	//PM-2012-09-21:PATIENTVISIT
	public List<Person> findPersonVisitByAttributes(Person person,PatientVisit patientvisit)
	{
		ValidationService validationService = Context.getValidationService();
		
		validationService.validate(person);
		
		validationService.validate(patientvisit);
		
		List<Person> persons= new ArrayList<Person>();
		
		String StrSql="Select * from person where date_voided is null ";//person_id in (select person_id from patient_visit where PAT_CATEGORY='E')";
		
		if(person.getGivenName()!=null)
		{
			StrSql = StrSql + " AND given_name='" + person.getGivenName() + "'";
		}
		
		if(person.getFamilyName()!=null)
		{
			StrSql = StrSql + " AND family_name='" + person.getFamilyName() + "'";
		}
		
		if(person.getDateOfBirth()!=null)
		{
			StrSql = StrSql + " AND date_of_birth=to_date('" + dateFormat.format(person.getDateOfBirth())  +"','yyyymmdd')";
		}
		
		if(person.getBirthProvince()!=null)
		{
			StrSql = StrSql + " AND BIRTH_PROVINCE ='" + person.getBirthProvince() + "'";
		}
		
		if(person.getBirthCity()!=null)
		{
			StrSql = StrSql + " AND BIRTH_CITY  ='" + person.getBirthCity() + "'";
		}
		
		if(person.getBirthCounty()!=null)
		{
			StrSql = StrSql + " AND BIRTH_COUNTY ='" + person.getBirthCounty() + "'";
		}
		
		if(person.getBirthPlace()!=null)
		{
			StrSql = StrSql + " AND BIRTH_PLACE ='" + person.getBirthPlace() + "'";
		}
		
		if(person.getBirthZip()!=null)
		{
			StrSql = StrSql + " AND BIRTH_ZIP = '" + person.getBirthZip() + "'";
		}
		
		if(person.getMultipleBirthInd()!=null)
		{
			StrSql = StrSql + " AND multiple_birth_ind='" + person.getMultipleBirthInd() + "'";
		}
		
		if(person.getBirthOrder()!=null)
		{
			StrSql = StrSql + " AND bitrh_order='" + person.getBirthOrder() + "'";
		}
		
		if(person.getMothersMaidenName()!=null)
		{
			StrSql = StrSql + " AND mothers_maiden_name='" + person.getMothersMaidenName() + "'";
		}
		
		if(person.getSsn()!=null)
		{
			StrSql = StrSql + " AND ssn='" + person.getSsn() + "'";
		}
		
		if(person.getIdentityNo()!=null)
		{
			StrSql = StrSql + " AND identity_no='" + person.getIdentityNo() + "'";
		}
		
		if(person.getCitizenCard()!=null)
		{
			StrSql = StrSql + " AND CITIZEN_CARD ='" + person.getCitizenCard() + "'";
		}
		
		if(person.getMedicalCertificate()!=null)
		{
			StrSql = StrSql + " AND MEDICAL_CERTIFICATE='" + person.getMedicalCertificate() + "'";
		}
		
		if(person.getMeicarePerson()!=null)
		{
			StrSql = StrSql + " AND MEICARE_PERSON='" + person.getMeicarePerson() + "'";
		}
		
		if(person.getElderCertificate()!=null)
		{
			StrSql = StrSql + " AND ELDER_CERTIFICATE='" + person.getElderCertificate() + "'";
		}
		
		if(person.getOpcaseno()!=null)
		{
			StrSql = StrSql + " AND OPCASENO='" + person.getOpcaseno() + "'";
		}
		
		if(person.getInsuranceNo()!=null)
		{
			StrSql = StrSql + " AND insurance_no='" + person.getInsuranceNo() + "'";
		}
		
		if(person.getGenderDict()!=null)
		{
			if(person.getGenderDict().getGenderCode().equals("F"))
			{
				StrSql = StrSql + " AND gender_cd='1'";
			}
			
			if(person.getGenderDict().getGenderCode().equals("M"))
			{
				StrSql = StrSql + " AND gender_cd='2'";
			}
			
			if(person.getGenderDict().getGenderCode().equals("O"))
			{
				StrSql = StrSql + " AND gender_cd='3'";
			}
			
			if(person.getGenderDict().getGenderCode().equals("U"))
			{
				StrSql = StrSql + " AND gender_cd='4'";
			}
			
			if(person.getGenderDict().getGenderCode().equals("A"))
			{
				StrSql = StrSql + " AND gender_cd='5'";
			}
			
			if(person.getGenderDict().getGenderCode().equals("N"))
			{
				StrSql = StrSql + " AND gender_cd='6'";
			}
		}
		
		if(person.getGenderName()!=null)
		{
			StrSql = StrSql + " AND GENDER_NAME='" + person.getGenderName() + "'";
		}
		
		if(person.getGenderDomain()!=null)
		{
			StrSql = StrSql + " AND GENDER_DOMAIN='" + person.getGenderDomain() + "'";
		}
		
		if(person.getEthnicgroupDict()!=null)
		{
			if(person.getEthnicgroupDict().getEthnicGroupCode()!=null)
			{
				StrSql = StrSql + " AND ETHNIC_GROUP_CD='" + person.getEthnicgroupDict().getEthnicGroupCode() + "'";
			}
		}
		
		if(person.getEthnicName()!=null)
		{
			StrSql = StrSql + " AND ETHNIC_NAME='" + person.getEthnicName() + "'";
		}
		
		if(person.getEthincDomain()!=null)
		{
			StrSql = StrSql + " AND ETHNIC_DOMAIN='" + person.getEthincDomain() + "'";
		}
		
		if(person.getRaceDict()!=null)
		{
			if(person.getRaceDict().getRaceCode()!=null)
			{
				StrSql = StrSql + " AND  RACE_CD='" + person.getRaceDict().getRaceCode() + "'";
			}
		}
		
		if(person.getRaceName()!=null)
		{
			StrSql = StrSql + " AND RACE_NAME='" + person.getRaceName() + "'";
		}
		
		if(person.getRaceDomain()!=null)
		{
			StrSql = StrSql + " AND RACE_DOMAIN='" + person.getRaceDomain() + "'";
		}
		
		if(person.getNationalityDict()!=null)
		{
			if(person.getNationalityDict().getAtionalityCode()!=null)
			{
				StrSql = StrSql + " AND NATIONALITY_CD='" + person.getNationalityDict().getAtionalityCode() + "'";
			}
		}
		
		if(person.getNationalityName()!=null)
		{
			StrSql = StrSql + " AND NATIONALITY_NAME='" +person.getNationalityName() + "'";
		}
		
		if(person.getNationalityDomain()!=null)
		{
			StrSql = StrSql + " AND NATIONALITY_DOMAIN='" + person.getNationalityDomain() + "'";
		}
		
		if(person.getMaritalStatusDict()!=null)
		{
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("A"))
			{
				StrSql = StrSql + " AND marital_status_cd='1'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("D"))
			{
				StrSql = StrSql + " AND marital_status_cd='2'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("M"))
			{
				StrSql = StrSql + " AND marital_status_cd='3'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("S"))
			{
				StrSql = StrSql + " AND marital_status_cd='4'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("W"))
			{
				StrSql = StrSql + " AND marital_status_cd='5'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("C"))
			{
				StrSql = StrSql + " AND marital_status_cd='6'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("G"))
			{
				StrSql = StrSql + " AND marital_status_cd='7'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("P"))
			{
				StrSql = StrSql + " AND marital_status_cd='8'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("R"))
			{
				StrSql = StrSql + " AND marital_status_cd='9'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("E"))
			{
				StrSql = StrSql + " AND marital_status_cd='10'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("N"))
			{
				StrSql = StrSql + " AND marital_status_cd='11'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("I"))
			{
				StrSql = StrSql + " AND marital_status_cd='12'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("B"))
			{
				StrSql = StrSql + " AND marital_status_cd='13'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("U"))
			{
				StrSql = StrSql + " AND marital_status_cd='14'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("O"))
			{
				StrSql = StrSql + " AND marital_status_cd='15'";
			}
			
			if(person.getMaritalStatusDict().getMaritalStatusCode().equals("T"))
			{
				StrSql = StrSql + " AND marital_status_cd='16'";
			}
		}
		
		if(person.getMaritalStatusName()!=null)
		{
			StrSql = StrSql + " AND MARITAL_STATUS_NAME ='" + person.getMaritalStatusName() + "'";
		}
		
		if(person.getMaritalDomain()!=null)
		{
			StrSql = StrSql + " AND MARITAL_DOMAIN ='" + person.getMaritalDomain() + "'";
		}
		
		if(person.getDegreeTypeDict()!=null)
		{
			if(person.getDegreeTypeDict().getDegreeTypeCode()!=null)
			{
				StrSql = StrSql + " AND DEGREE='" + person.getDegreeTypeDict().getDegreeTypeCode() + "'";
			}
		}
		
		if(person.getDegreeName()!=null)
		{
			StrSql = StrSql + " AND DEGREE_NAME='" + person.getDegreeName() + "'";
		}
		
		if(person.getDegreeDomain()!=null)
		{
			StrSql = StrSql + " AND DEGREE_DOMAIN='" + person.getDegreeDomain() + "'";
		}
		
		if(person.getEmail()!=null)
		{
			StrSql = StrSql + " AND EMAIL='" + person.getEmail() + "'";
		}
		
		if(person.getHomeProvince()!=null)
		{
			StrSql = StrSql + " AND HOME_PROVINCE='" + person.getHomeProvince() + "'";
		}
		
		if(person.getHomeCity()!=null)
		{
			StrSql = StrSql + " AND HOME_CITY='" + person.getHomeCity() + "'";
		}
		
		if(person.getHomeCounty()!=null)
		{
			StrSql = StrSql + " AND HOME_COUNTY='" + person.getHomeCounty() + "'";
		}
		
		if(person.getHomeZip()!=null)
		{
			StrSql = StrSql + " AND HOME_ZIP='" + person.getHomeZip() + "'";
		}
		
		if(person.getHomeAddress()!=null)
		{
			StrSql = StrSql + " AND HOME_ADDRESS='" + person.getHomeAddress() + "'";
		}
		
		if(person.getRegisteredProvince()!=null)
		{
			StrSql = StrSql + " AND REGISTERED_PROVINCE='" + person.getRegisteredProvince() + "'";
		}
		
		if(person.getRegisteredCity()!=null)
		{
			StrSql = StrSql + " AND REGISTERED_CITY='" + person.getRegisteredCity() + "'";
		}
		
		if(person.getRegisteredCounty()!=null)
		{
			StrSql = StrSql + " AND REGISTERED_COUNTY='" + person.getRegisteredCounty() + "'";
		}
		
		if(person.getRegisteredZip()!=null)
		{
			StrSql = StrSql + " AND REGISTERED_ZIP='" + person.getRegisteredZip() + "'";
		}
		
		if(person.getRegisteredAddress()!=null)
		{
			StrSql = StrSql + " AND REGISTERED_ADDRESS='" + person.getRegisteredAddress() + "'";
		}
		
		if(person.getNativeProvince()!=null)
		{
			StrSql = StrSql + " AND NATIVE_PROVINCE='" + person.getNativeProvince() + "'";
		}
		
		if(person.getNativeCity()!=null)
		{
			StrSql = StrSql + " AND NATIVE_CITY='" + person.getNativeCity() + "'";
		}
		
		if(person.getProfessionTypeDict()!=null)
		{
			if(person.getProfessionTypeDict().getProfessionTypeCode()!=null)
			{
				StrSql = StrSql + " AND PROFESSION='" + person.getProfessionTypeDict().getProfessionTypeCode() + "'";
			}
		}
		
		if(person.getProfessionName()!=null)
		{
			StrSql = StrSql + " AND PROFESSION_NAME='" + person.getProfessionName() + "'";
		}
		
		if(person.getProfessionDomain()!=null)
		{
			StrSql = StrSql + " AND PROFESSION_DOMAIN='" + person.getProfessionDomain() + "'";
		}
		
		if(person.getCompany()!=null)
		{
			StrSql = StrSql + " AND COMPANY='" + person.getCompany() + "'";
		}
		
		if(person.getWorkZip()!=null)
		{
			StrSql = StrSql + " AND WORK_ZIP='" + person.getWorkZip() + "'";
		}
		
		if(person.getWorkAddress()!=null)
		{
			StrSql = StrSql + " AND WORK_ADDRESS='" + person.getWorkAddress() + "'";
		}
		
		if(person.getPrivateNumber()!=null)
		{
			StrSql = StrSql + " AND PRIVATE_NUMBER='" + person.getPrivateNumber() + "'";
		}
		
		if(person.getHomeNumber()!=null)
		{
			StrSql = StrSql + " AND HOME_NUMBER='" + person.getHomeNumber() + "'";
		}
		
		if(person.getWorkNumber()!=null)
		{
			StrSql = StrSql + " AND WORK_NUMBER='" + person.getWorkNumber() + "'";
		}
		
		if(person.getGuardianPerson()!=null)
		{
			StrSql = StrSql + " AND GUARDIAN_PERSON='" + person.getGuardianPerson() + "'";
		}
		
		if(person.getVip()!=null)
		{
			StrSql = StrSql + " AND VIP='" + person.getVip() + "'";
		}
		
		StrSql = StrSql + " and person_id in (select person_id from patient_visit where voided_date is null";
		
		//PV1-2.1:PAT_CATEGORY
		if(patientvisit.getPatCategory()!=null)
		{
			StrSql = StrSql + " And pat_category = '" + patientvisit.getPatCategory() + "'";
		}
		
		//PV1-3.1:PAT_CURRENT_POINT_OF_CARE
		if(patientvisit.getPatCurrentPointOfCare()!=null)
		{
			StrSql = StrSql + " And pat_current_point_of_care='" + patientvisit.getPatCurrentPointOfCare() + "'";
		}
		
		//PV1-3.2:PAT_CURRENT_ROOM
		if(patientvisit.getPatCurrentRoom()!=null)
		{
			StrSql = StrSql + " And pat_current_room='" + patientvisit.getPatCurrentRoom() + "'";
		}
		
		//PV1-3.3:PAT_CURRENT_BED
		if(patientvisit.getPatCurrentBed()!=null)
		{
			StrSql = StrSql + " And pat_current_bed='" + patientvisit.getPatCurrentBed() + "'";
		}
		
		
		//PV1-3.4:PAT_CURRENT_DEP
		if(patientvisit.getPatCuurentDep()!=null)
		{
			StrSql = StrSql + " And pat_cuurent_dep='" + patientvisit.getPatCuurentDep() + "'";
		}
		
		//PV1-3.5:PAT_CURRENT_POSITION_STATUS
		if(patientvisit.getPatCurrentPositionStatus()!=null)
		{
			StrSql = StrSql + " And pat_current_position_status='" + patientvisit.getPatCurrentPositionStatus() + "'";
		}
		
		//PV1-3.6:PAT_CURRENT_POSITION_TYPE
		if(patientvisit.getPatCurrentPositionType()!=null)
		{
			StrSql = StrSql + " And pat_current_position_type='" + patientvisit.getPatCurrentPositionType() + "'";
		}
		
		//pv1-3.7:PAT_CURRENT_BUILDING
		if(patientvisit.getPatCurrentBuilding()!=null)
		{
			StrSql = StrSql + " And pat_current_building='" + patientvisit.getPatCurrentBuilding() + "'";
		}
		
		//PV1-3.8:PAT_CURRENT_FLOOR
		if(patientvisit.getPatCurrentFloor()!=null)
		{
			StrSql = StrSql + " And pat_current_floor='" + patientvisit.getPatCurrentFloor() + "'";
		}
		
		//PV1-3.9:PAT_CURRENT_DESCRIPTION
		if(patientvisit.getPatCuurentDescription()!=null)
		{
			StrSql = StrSql + " And pat_current_description='" + patientvisit.getPatCuurentDescription() + "'";
		}
		
		//PV1-4.1:PAT_ADMISSION_TYPE
		if(patientvisit.getPatAdmissionType()!=null)
		{
			StrSql = StrSql + " And pat_admission_type='" + patientvisit.getPatAdmissionType() + "'";
		}
		
		//PV1-5.1:PAT_ADMISSION_NUMBER
		if(patientvisit.getPatAdmissionNumber()!=null)
		{
			StrSql = StrSql + " And pat_admission_number='" + patientvisit.getPatAdmissionNumber() + "'";
		}
		
		//PV1-6.1:PAT_FORMER_POINT_OF_CARE
		if(patientvisit.getPatFormerPointOfCare()!=null)
		{
			StrSql = StrSql + " And pat_former_point_of_care='" + patientvisit.getPatFormerPointOfCare() + "'";
		}
		
		//PV1-6.2:PAT_FORMER_ROOM
		if(patientvisit.getPatFormerRoom()!=null)
		{
			StrSql = StrSql + " And pat_former_room='" + patientvisit.getPatFormerRoom() + "'";
		}
		
		//PV1-6.3:PAT_FORMBER_BED
		if(patientvisit.getPatFormerBed()!=null)
		{
			StrSql = StrSql + " And pat_former_bed='" + patientvisit.getPatFormerBed() + "'";
		}
		
		//PV1-6.4:PAT_FORMER_DEP
		if(patientvisit.getPatFormerDep()!=null)
		{
			StrSql = StrSql + " And pat_former_dep='" + patientvisit.getPatFormerDep() + "'";
		}
		
		//PV1-6.5:PAT_FORMER_POSITION_STATUS
		if(patientvisit.getPatFormerPositionStatus()!=null)
		{
			StrSql = StrSql + " And pat_former_position_status='" + patientvisit.getPatFormerPositionStatus() + "'";
		}
		
		//PV1-6.6:PAT_FORER_POSITION_TYPE
		if(patientvisit.getPatFormerPositionType()!=null)
		{
			StrSql = StrSql + " And pat_former_position_type='" + patientvisit.getPatFormerPositionType() + "'";
		}
		
		//PV1-6.7:PAT_FORMER_BUILDING
		if(patientvisit.getPatFormerBuilding()!=null)
		{
			StrSql = StrSql + " And pat_former_building='" + patientvisit.getPatFormerBuilding() + "'";
		}
		
		//PV1-6.8:PAT_FORMER_FLOOR
		if(patientvisit.getPatFormerFloor()!=null)
		{
			StrSql = StrSql + " And pat_former_floor='" + patientvisit.getPatFormerFloor() + "'";
		}
		
		//PV1-6.9:PAT_FORMER_DESCRIPTION
		if(patientvisit.getPatFormerDescription()!=null)
		{
			StrSql = StrSql + " And pat_former_description='" + patientvisit.getPatFormerDescription() + "'";
		}
		
		//PV1-7.2:ADMISSIONS_DOCTOR
		if(patientvisit.getAdmissionsDoctor()!=null)
		{
			StrSql = StrSql + " And admissions_doctor='" + patientvisit.getAdmissionsDoctor() + "'";
		}
		
		//PV1-7.1:ADMISSIONS_DOCTOR_ID
		if(patientvisit.getAdmissionsDoctorId()!=null)
		{
			StrSql = StrSql + " And admissions_doctor_id='" + patientvisit.getAdmissionsDoctorId() + "'";
		}
		
		//PV1-8.1:REFERRING_DOCTOR_ID
		if(patientvisit.getReferringDoctorId()!=null)
		{
			StrSql = StrSql + " And referring_doctor_id='" + patientvisit.getReferringDoctorId() + "'";
		}
		
		//PV1-8.2:REFERRING_DOCTOR
		if(patientvisit.getReferringDoctor()!=null)
		{
			StrSql = StrSql + " And referring_doctor='" + patientvisit.getReferringDoctor() + "'";
		}
		
		//PV1-9.1:CONSULTATION_DOCTOR_ID
		if(patientvisit.getConsultationDoctorId()!=null)
		{
			StrSql = StrSql + " And consultation_doctor_id='" + patientvisit.getConsultationDoctorId() + "'";
		}
		
		//PV1-9.2:CONSULTATION_DOCTOR
		if(patientvisit.getConsultationDoctor()!=null)
		{
			StrSql = StrSql + " And consultation_doctor='" + patientvisit.getConsultationDoctor() + "'";
		}
		
		//PV1-10.1:HOSPITAL_SERVICE
		if(patientvisit.getHospitalService()!=null)
		{
			StrSql = StrSql + " And hospital_service='" + patientvisit.getHospitalService() + "'";
		}
		
		//PV1-11.1:PAT_TEMP_POINT_OF_CARE
		if(patientvisit.getPatTempPointOfCare()!=null)
		{
			StrSql = StrSql + " And pat_temp_point_of_care='" + patientvisit.getPatTempPointOfCare() + "'";
		}
		
		//PV1-11.2:PAT_TEMP_ROOM
		if(patientvisit.getPatTempRoom()!=null)
		{
			StrSql = StrSql + " And pat_temp_room='" + patientvisit.getPatTempRoom() + "'";
		}
		
		//PV1-11.3:PAT_TEMP_BED
		if(patientvisit.getPatTempBed()!=null)
		{
			StrSql = StrSql + " And pat_temp_bed='" + patientvisit.getPatTempBed() + "'";
		}
		
		//PV1-11.4:PAT_TEMP_DEP
		if(patientvisit.getPatTempDep()!=null)
		{
			StrSql = StrSql + " And pat_temp_dep='" + patientvisit.getPatTempDep() + "'";
		}
		
		//PV1-11.5:PAT_TEMP_POSITION_STATUS
		if(patientvisit.getPatTempPositionStatus()!=null)
		{
			StrSql = StrSql + " And pat_temp_position_status='" + patientvisit.getPatTempPositionStatus() + "'";
		}
		
		//PV1-11.6:PAT_TEMP_POSITION_TYPE
		if(patientvisit.getPatTempPositionType()!=null)
		{
			StrSql = StrSql + " And pat_temp_position_type='" + patientvisit.getPatTempPositionType() + "'";
		}
		
		//PV1-11.7:PAT_TEMP_BUILDING
		if(patientvisit.getPatTempBuilding()!=null)
		{
			StrSql = StrSql + " And pat_temp_building=" + patientvisit.getPatTempBuilding() + "'";
		}
		
		//PV1-11.8:PAT_TEMP_FLOOR
		if(patientvisit.getPatTempFloor()!=null)
		{
			StrSql = StrSql + " And pat_temp_floor='" + patientvisit.getPatTempFloor() + "'";
		}
		
		//PV1-11.9:PAT_TEMP_DESCRIPTION
		if(patientvisit.getPatTempDescription()!=null)
		{
			StrSql = StrSql + " And pat_temp_description='" + patientvisit.getPatTempDescription() + "'";
		}
		
		//PV1-12.1:PAT_ADMISSION_TEST
		if(patientvisit.getPatAdmissionTest()!=null)
		{
			StrSql = StrSql + " And pat_admission_test='" + patientvisit.getPatAdmissionTest() + "'";
		}
		
		//pv1-13.1:PAT_RE_ADMISSION
		if(patientvisit.getPatReAdmission()!=null)
		{
			StrSql = StrSql + " And pat_re_admission='" + patientvisit.getPatReAdmission() + "'";
		}
		
		//PV1-14.1:PAT_ADMISSION_SOURCE
		if(patientvisit.getPatAdmissionSource()!=null)
		{
			StrSql = StrSql + " And pat_admission_source='" + patientvisit.getPatAdmissionSource() + "'";
		}
		
		//PV1-15.1:PAT_AMBULATORY_STATUS
		if(patientvisit.getDischargeName()!=null)
		{
			StrSql = StrSql + " And DISCHARGE_NAME='" + patientvisit.getDischargeName() + "'";
		}
		
		if(patientvisit.getDischargeDomain()!=null)
		{
			StrSql = StrSql + " AND DISCHARGE_DOMAIN='" + patientvisit.getDischargeDomain() + "'";
		}
		
		if(patientvisit.getAdmissionName()!=null)
		{
			StrSql = StrSql + " AND ADMISSION_NAME='" + patientvisit.getAdmissionName() + "'";
		}
		
		if(patientvisit.getAdmissionDomain()!=null)
		{
			StrSql = StrSql + " AND ADMISSION_DOMAIN='" + patientvisit.getAdmissionDomain() + "'";
		}
		
		if(patientvisit.getIpStatusName()!=null)
		{
			StrSql = StrSql + " AND IP_STATUS_NAME='" + patientvisit.getIpStatusName() + "'";
		}
		
		if(patientvisit.getIpStatusDomain()!=null)
		{
			StrSql = StrSql + " AND IP_STATUS_DOMAIN='" + patientvisit.getIpStatusDomain() +"'";
		}
		
		if(patientvisit.getDificultyName()!=null)
		{
			StrSql = StrSql + " AND DIFICULTY_NAME='" + patientvisit.getDificultyName() + "'";
		}
		
		if(patientvisit.getDificultyDomain()!=null)
		{
			StrSql = StrSql + " AND DIFICULTY_DOMAIN='" + patientvisit.getDificultyDomain() + "'";
		}
		
		if(patientvisit.getAdmissionSourceName()!=null)
		{
			StrSql = StrSql + " AND ADMISSION_SOURCE_NAME='" + patientvisit.getAdmissionSourceName() + "'";
		}
		
		if(patientvisit.getAdmissionSourceDomain()!=null)			
		{
			StrSql = StrSql + " AND ADMISSION_SOURCE_DOMAIN='" + patientvisit.getAdmissionSourceDomain() + "'";
		}
		
		if(patientvisit.getPatCategoryName()!=null)
		{
			StrSql = StrSql + " AND PAT_CATEGORY_NAME='" + patientvisit.getPatCategoryName();
		}
		
		if(patientvisit.getPatCategorySystem()!=null)
		{
			StrSql = StrSql + " AND PAT_CATEGORY_SYSTEM='" + patientvisit.getPatCategoryName();
		}
		
		//PV1-16.1:PAT_VIP
		if(patientvisit.getPatVip()!=null)
		{
			StrSql = StrSql + " And pat_vip='" + patientvisit.getPatVip() + "'";
		}
		
		//PV1-17.2:PAT_ADMISSION_DOCTORS
		if(patientvisit.getPatAdmissionDoctors()!=null)
		{
			StrSql = StrSql + " And pat_admission_doctors='" + patientvisit.getPatAdmissionDoctors() + "'";
		}
		
		//PV1-17.1:PAT_ADMISSION_DOCTORS_ID
		if(patientvisit.getPatAdmissionDoctorsId()!=null)
		{
			StrSql = StrSql + " And pat_admission_doctors_id='" + patientvisit.getPatAdmissionDoctorsId() + "'";
		}
		
		//PV1-18.1:patient_CLASS
		if(patientvisit.getPatientClass()!=null)
		{
			StrSql = StrSql + " And patient_class='" + patientvisit.getPatientClass() + "'";
		}
		
		//PV1-19.1:Patient_ID
		if(patientvisit.getPatientId()!=null)
		{
			StrSql = StrSql + " And patient_id='" + patientvisit.getPatientId() + "'";
		}
		
		//PV1-20.1:PAT_FINANCIAL_CLASS
		if(patientvisit.getPatFinancialClass()!=null)
		{
			StrSql = StrSql + " And pat_financial_class='" + patientvisit.getPatFinancialClass() + "'";
		}
		
		//PV1-21.1:ROOM_BED_COST_PRICE
		if(patientvisit.getRoomBedCostPrice()!=null)
		{
			StrSql = StrSql + " And room_bed_cost_price='" + patientvisit.getRoomBedCostPrice() + "'";
		}
		
		//PV1-22.1:COURTESY_CODE
		if(patientvisit.getCourtesyCode()!=null)
		{
			StrSql = StrSql + " And courtesy_code='" + patientvisit.getCourtesyCode() + "'";
		}
		
		//PV1-23.1:CREDIT_RATING
		if(patientvisit.getCreditRating()!=null)
		{
			StrSql = StrSql + " And credit_rating='" + patientvisit.getCreditRating() + "'";
		}
		
		//PV1-24.1:CONTRACT_CODE
		if(patientvisit.getContractCode()!=null)
		{
			StrSql = StrSql + " And contract_code='" + patientvisit.getContractCode() + "'";
		}
		
		//PV1-25.1:CONTRACT_CREATE_DATE
		if(patientvisit.getContractCreateDate()!=null)
		{
			StrSql = StrSql + " And contract_create_date='" + patientvisit.getContractCreateDate() + "'";
		}
		
		//PV1-26.1:CONTRACT_PRICE
		if(patientvisit.getContractPrice()!=null)
		{
			StrSql = StrSql + " And contract_price='" + patientvisit.getContractPrice() + "'";
		}
		
		//PV1-27.1:CONTRACT_TIME
		if(patientvisit.getContractTime()!=null)
		{
			StrSql = StrSql + " And contract_time='" + patientvisit.getContractTime() + "'";
		}
		
		//PV1-28.1:INTEREST_RATE_CODE
		if(patientvisit.getInterestRateCode()!=null)
		{
			StrSql = StrSql + " And interest_rate_code='" + patientvisit.getInterestRateCode() + "'";
		}
		
		//PV1-29.1:BAD_DEBTS
		if(patientvisit.getBadDebts()!=null)
		{
			StrSql = StrSql + " And bad_debts='" + patientvisit.getBadDebts() + "'";
		}
		
		//PV1-30.1:BAD_DEBTS_CREATE_DATE
		if(patientvisit.getBadDebtsCreateDate()!=null)
		{
			StrSql = StrSql + " And bad_debts_create_date='" + patientvisit.getBadDebtsCreateDate() + "'";
		}
		
		//PV1-31.1:BAD_DEBTS_CODE
		if(patientvisit.getBadDebtsCode()!=null)
		{
			StrSql = StrSql + " And bad_debts_code='" + patientvisit.getBadDebtsCode() + "'";
		}
		
		//PV1-32.1:BAD_DEBTS_PRICE
		if(patientvisit.getBadDebtsPrice()!=null)
		{
			StrSql = StrSql + " And bad_debts_price='" + patientvisit.getBadDebtsPrice() + "'";
		}
		
		//PV1-33.1:BAD_DEBTS_RESTORE_PRICE
		if(patientvisit.getBadDebtsRestorePrice()!=null)
		{
			StrSql = StrSql + " And bad_debts__restorre_price='" + patientvisit.getBadDebtsRestorePrice() + "'";
		}
		
		//PV1-34.1:PAT_ACCOUNT_VOIDED
		if(patientvisit.getPatAccountVoided()!=null)
		{
			StrSql = StrSql + " And pat_account_voided='" + patientvisit.getPatAccountVoided() + "'";
		}
		
		//PV1-35.1:PAT_ACCOUNT_VOIDED_DATE
		if(patientvisit.getPatAccountVoidedDate()!=null)
		{
			StrSql = StrSql + " And pat_account_voided_date='" + patientvisit.getPatAccountVoidedDate() + "'";
		}
		
		//PV1-36.1: PAT_DISCHARGE_DISPOSITION
		if(patientvisit.getPatDischargeDisposition()!=null)
		{
			StrSql = StrSql + " And pat_discharge_disposition='" + patientvisit.getPatDischargeDisposition() + "'";
		}
		
		//PV1-37.1:PAT_DISCHARGE_LOCATION
		if(patientvisit.getPatDischargeLocation()!=null)
		{
			StrSql = StrSql + " And pat_discharge_location='" + patientvisit.getPatDischargeLocation() + "'";
		}
		
		//PV1-38.1:PAT_DIET_TYPE
		if(patientvisit.getPatDietType()!=null)
		{
			StrSql = StrSql + " And pat_diet_type='" + patientvisit.getPatDietType() + "'";
		}
		
		//PV1-39.1:PAT_SERVICE_AGENCIES
		if(patientvisit.getPatServiceAgencies()!=null)
		{
			StrSql = StrSql + " And pat_service_agencies='" + patientvisit.getPatServiceAgencies() + "'";
		}
		
		//PV1-40.1:PAT_BED_STATUS
		if(patientvisit.getPatBedStatus()!=null)
		{
			StrSql = StrSql + " And pat_bed_status='" + patientvisit.getPatBedStatus() + "'";
		}
		
		//PV1-41.1:PAT_ACCOUNT_STATUS
		if(patientvisit.getPatAccountStatus()!=null)
		{
			StrSql = StrSql + " And pat_account_status='" + patientvisit.getPatAccountStatus() + "'";
		}
		
		//PV1-42.1:PAT_DETER_POINT_OF_CARE
		if(patientvisit.getPatDeterPointOfCare()!=null)
		{
			StrSql = StrSql + " And pat_deter_point_of_care='" + patientvisit.getPatDeterPointOfCare() + "'";
		}
		
		//PV1-42.2:PAT_DETER_ROOM
		if(patientvisit.getPatDeterRoom()!=null)
		{
			StrSql = StrSql + " And pat_deter_room='" + patientvisit.getPatDeterRoom() + "'";
		}
		
		//PV1-42.3:PAT_DETER_BED
		if(patientvisit.getPatDeterBed()!=null)
		{
			StrSql = StrSql + " And pat_deter_bed='" + patientvisit.getPatDeterBed() + "'";
		}
		
		//PV1-42.4:PAT_DETER_DEP
		if(patientvisit.getPatDeterDep()!=null)
		{
			StrSql = StrSql + " And pat_deter_dep='" + patientvisit.getPatDeterDep() + "'";
		}
		
		//PV1-42.5:PAT_DETER_POSITION_STATUS
		if(patientvisit.getTend()!=null)
		{
			StrSql = StrSql + " And tend='" + patientvisit.getTend() + "'";
		}
		
		//PV1-42.6:PAT_DETER_POSITION_TYPE
		if(patientvisit.getPatNurseCode()!=null)
		{
			StrSql = StrSql + " And pat_nurse_code='" + patientvisit.getPatNurseCode() + "'";
		}
		
		//PV1-42.7:PAT_DETER_BUILDING
		if(patientvisit.getPatNurseName()!=null)
		{
			StrSql = StrSql + " And pat_nurse_name='" + patientvisit.getPatNurseName()+ "'";
		}
		
		//PV1-42.8:PAT_DETER_FLOOR
		if(patientvisit.getOperCode()!=null)
		{
			StrSql = StrSql + " And oper_code='" + patientvisit.getOperCode() + "'";
		}
		
		//PV1-42.9:PAT_DETER_DESCRIPTION
		if(patientvisit.getOperDate()!=null)
		{
			StrSql = StrSql + " And oper_date='" + patientvisit.getOperDate() + "'";
		}
		
		//PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
		if(patientvisit.getPatForTempPointOfCare()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_point_of_care='" + patientvisit.getPatForTempPointOfCare() + "'";
		}
		
		//PV1-43.2:PAT_FOR_TEMP_ROOM
		if(patientvisit.getPatForTempRoom()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_room='" + patientvisit.getPatForTempRoom() + "'";
		}
		
		//PV1-43.3:pat_for_temp_bed
		if(patientvisit.getPatForTempBed()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_bed='" + patientvisit.getPatForTempBed() + "'";
		}
		
		//PV1-43.4:PAT_FOR_TEMP_DEP
		if(patientvisit.getPatForTempDep()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_dep='" + patientvisit.getPatForTempDep() + "'";
		}
		
		//PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
		if(patientvisit.getPatForTempPositionStatus()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_position_status='" + patientvisit.getPatForTempPositionStatus() + "'";
		}
		
		//PV1-43.6:PAT_FOR_TEMP_POSITION_TYPE
		if(patientvisit.getPatForTempPositionType()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_position_type='" + patientvisit.getPatForTempPositionType() + "'";
		}
		
		//PV1-43.7:PAT_FOR_TEMP_BUILDING
		if(patientvisit.getPatForTempBuilding()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_building='" + patientvisit.getPatForTempBuilding() + "'";
		}
		
		//PV1-43.8:PAT_FOR_TEMP_FLOOR
		if(patientvisit.getPatForTempFloor()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_floor='" + patientvisit.getPatForTempFloor() + "'";
		}
		
		//PV1-43.9:PAT_FOR_TEMP_DESCRIPTION
		if(patientvisit.getPatTempDescription()!=null)
		{
			StrSql = StrSql + " And pat_for_temp_description='" + patientvisit.getPatFormerDescription() + "'";
		}
		
		//PV1-44.1:ADMIT_DATE
		if(patientvisit.getAdmitDate()!=null)
		{
			StrSql = StrSql + " And admit_date='" + patientvisit.getAdmitDate() + "'";
		}
		
		//PV1-45.1:DISCHARGE_DATE
		if(patientvisit.getDischargeDate()!=null)
		{
			StrSql = StrSql + " And discharge_date='" + patientvisit.getDischargeDate() + "'";
		}
		
		//PV1-46.1:PAT_DIFFERENCE
		if(patientvisit.getPatDifference()!=null)
		{
			StrSql = StrSql + " And pat_difference='" + patientvisit.getPatDifference() + "'";
		}
		
		//PV1-47.1:PAT_TOTAL_COST
		if(patientvisit.getPatTotalCost()!=null)
		{
			StrSql = StrSql + " And pat_total_cost='" + patientvisit.getPatTotalCost() + "'";
		}
		
		//PV1-48.1:PAT_TOTAL_DISPATCH
		if(patientvisit.getPatTotalDispatch()!=null)
		{
			StrSql = StrSql + " And pat_total_dispatch='" + patientvisit.getPatTotalDispatch() + "'";
		}
		
		//PV1-49.1:PAT_TOTAL_AMOUNT_PAYABLE
		if(patientvisit.getPatTotalAmountPayable()!=null)
		{
			StrSql = StrSql + " And pat_total_amount_payable='" + patientvisit.getPatTotalAmountPayable() + "'";
		}
		
		//PV1-50.1:PAT_SPARE_ID
		if(patientvisit.getBabyFlag()!=null)
		{
			StrSql = StrSql + " And baby_flag='" + patientvisit.getBabyFlag() + "'";
		}
		
		if(patientvisit.getAdmitWeight()!=null)
		{
			StrSql = StrSql + " AND ADMIT_WEIGHT='" + patientvisit.getAdmitWeight() + "'";
		}
		
		if(patientvisit.getAdmitWeightUnit()!=null)
		{
			StrSql = StrSql + " AND ADMIT_WEIGHT_UNIT='" + patientvisit.getAdmitWeightUnit() + "'";
		}
		
		if(patientvisit.getBirthWeight()!=null)
		{
			StrSql = StrSql + " AND BIRTH_WEIGHT='" + patientvisit.getBirthWeight() + "'";
		}
		
		if(patientvisit.getBabyWeightUnit()!=null)
		{
			StrSql = StrSql + " AND BABY_WEIGHT_UNIT='" + patientvisit.getBabyWeightUnit() + "'";
		}
		//PV1-51.1:PAT_VISIT_LOGO
		if(patientvisit.getPatVisitLogo()!=null)
		{
			StrSql = StrSql + " And pat_visit_logo='" + patientvisit.getPatVisitLogo() + "'";
		}
		
		//PV1-52.1:OTHER_MEDICAL_INSTITUTIONS
		if(patientvisit.getOtherMedicalInstitutions()!=null)
		{
			StrSql = StrSql + " And other_medical_institutions='" + patientvisit.getOtherMedicalInstitutions() + "'";
		}
		
		StrSql = StrSql + ")";
		
		persons=patientvisitDao.getPersons(StrSql);
		
		return persons;
	}

	public List<String> getPersonModelAttributeNames() {
		return getModelAttributeNames(false);
	}
	
	public List<String> getPersonModelCustomAttributeNames() {
		return getModelAttributeNames(true);
	}
	
	private List<String> getModelAttributeNames(boolean needCustomFields) {
		Record record = new Record(new Person());
		return ConvertUtil.getModelAttributeNames(record, needCustomFields);
	}
	
	public List<Person> findPersonsByAttributesPaged(Person person, int firstResult, int maxResults) {
		ValidationService validationService = Context.getValidationService();
		validationService.validate(person);
		
		Record record = new Record(person);
		Criteria criteria = buildCriteriaFromProperties(record);
		addIndirectCriteria(person, criteria);
		List<Person> persons;
		if (person.getPersonIdentifiers() != null && person.getPersonIdentifiers().size() > 0) {
			persons = personDao.getPersonsPaged(criteria, person.getPersonIdentifiers(), firstResult,
					maxResults, true);
		} else {
			persons = personDao.getPersonsPaged(criteria, firstResult, maxResults);
		}
		hydrateObjects(persons);
		return persons;
	}
	
	private void addIndirectCriteria(Person person, Criteria criteria) {
		if (person.getGenderDict() != null && person.getGenderDict().getGenderCode() != null) {
			ExtendedCriterion criterion = new ExtendedCriterion();
			criterion.setAlias("genderDict");
			criterion.setAssociationPath("genderDict");
			criterion.setName("genderDict.genderCode");
			criterion.setOperation(Operation.EQ);
			criterion.setValue(person.getGenderDict().getGenderCode());
			criteria.addCriterion(criterion);
			//criteria.add(Restrictions.like("name", "Fritz%"));
		}
		
		if (person.getMaritalStatusDict() != null && person.getMaritalStatusDict().getMaritalStatusCode() != null) {
			ExtendedCriterion criterion = new ExtendedCriterion();
			criterion.setAlias("maritalStatusDict");
			criterion.setAssociationPath("maritalStatusDict");
			criterion.setName("maritalStatusDict.maritalStatusCode");
			criterion.setOperation(Operation.EQ);
			criterion.setValue(person.getMaritalStatusDict().getMaritalStatusCode());
			criteria.addCriterion(criterion);
			//criteria.add(Restrictions.like("name", "Fritz%"));
		}
	}

	public GenderDict findGenderByCode(String genderCode) {
		return personDao.findGenderByCode(genderCode);
	}

	public GenderDict findGenderByName(String genderName) {
		return personDao.findGenderByName(genderName);
	}

	public RaceDict findRaceByCode(String raceCode) {
		return personDao.findRaceByCode(raceCode);
	}

	public RaceDict findRaceByName(String raceName) {
		return personDao.findRaceByName(raceName);
	}
	
	private void hydrateObjects(List<Person> linkedPersons) {
		// This is used for materializing the hibernate proxies before returning the collection
		for (Person p : linkedPersons) {
			p.toString();
			for (PersonIdentifierEMPI identifier : p.getPersonIdentifiers()) {
				identifier.toString();
				identifier.getIdentifierDomainDict().toString();
			}
		}
	}
	
	private Criteria buildCriteriaFromProperties(Record record) {
		Set<String> propertyNames = record.getPropertyNames();
		Criteria criteria = new Criteria();
		for (String property : propertyNames) {
			//System.out.println(property);
			Object value = record.get(property);
			if (value != null &&
					(value instanceof java.lang.String 
							|| value instanceof java.lang.Integer
							|| value instanceof java.util.Date)) {
				Criterion criterion = new Criterion();
				criterion.setName(property);
				criterion.setOperation(Operation.LIKE);
				criterion.setValue(value);
				criteria.addCriterion(criterion);
			}
		}
		return criteria;		
	}

	public IdentifierDomainAttribute getIdentifierDomainAttribute(IdentifierDomainDict identifierDomain,
			String attributeName) {
		
		ValidationService validationService = Context.getValidationService();
		validationService.validate(identifierDomain);
		
		return personDao.getIdentifierDomainAttribute(identifierDomain, attributeName);
	}

	public List<IdentifierDomainAttribute> getIdentifierDomainAttributes(IdentifierDomainDict identifierDomain) {
		
		ValidationService validationService = Context.getValidationService();
		validationService.validate(identifierDomain);
		
		return personDao.getIdentifierDomainAttributes(identifierDomain);
	}

	public List<String> getIdentifierDomainTypeCodes() {
		return personDao.getIdentifierDomainTypeCodes();
	}

	public List<IdentifierDomainDict> getIdentifierDomains() {
		return personDao.getIdentifierDomains();
	}

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public PersonLinkDao getPersonLinkDao() {
		return personLinkDao;
	}

	/**
	 * @return the patientvisitDao
	 */
	public PatientVisitDao getPatientvisitDao() {
		return patientvisitDao;
	}

	/**
	 * @param patientvisitDao the patientvisitDao to set
	 */
	public void setPatientvisitDao(PatientVisitDao patientvisitDao) {
		this.patientvisitDao = patientvisitDao;
	}

	public void setPersonLinkDao(PersonLinkDao personLinkDao) {
		this.personLinkDao = personLinkDao;
	}
	
	public List<PersonIdentifierEMPI> GetPersonPid(final Person person){		
		return personDao.getPidS(person);
	}

	/**
	 * @return the empiDao
	 */
	public EmpiDao getEmpiDao() {
		return empiDao;
	}

	/**
	 * @param empiDao the empiDao to set
	 */
	public void setEmpiDao(EmpiDao empiDao) {
		this.empiDao = empiDao;
	}
}
