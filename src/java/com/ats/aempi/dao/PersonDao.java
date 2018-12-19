package com.ats.aempi.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.model.*;

public interface PersonDao extends UniversalDao
{
	public void SaveTransPerson(TransPerson person);
	
	public void addPerson(Person person);
	
	public void addMergeEvent(MergeEvent mergeEvent);
	
	public void addContactPerson(List<Contactperson> contactperson,Person person) throws ApplicationException;
	
	public String findRelevanceRecord(String relevanceID,String relevanceDomain);
	
	public void addRelevanceRecord(RelevanceAndId relevanceandid);
	
	public void updatePerson(Person person);
	
	public String runProcedureForEmpi(Person person,Person personFound,String ReplaceID,int ControlID) throws ApplicationException;
	
	public String runProcedureForMerge(Person leftperson,Person rightperson,int ControlID) throws ApplicationException;
	
	public void procedureForXDS (String Empi,String OldEmpi,int ControlID) throws ApplicationException;
	
	public Person getPersonById(PersonIdentifierEMPI personIdentifier);
	
	public Person getPersonById2(PersonIdentifierEMPI personIdentifier);
	
	public List<Person> getPersonById3(Person person);
	
	public Person getPersonForID(PersonIdentifierEMPI personIdentifier);
	
	public List<Person>  getEmpiById (String personid);
	
	public List<String> getPersonidByEmpi (String empi);
	
	public List<String> getPersonNameByField(Person person,String str,int type) throws ApplicationException;
	
	public List<Person> getPersonsByIdentifier(final PersonIdentifierEMPI personIdentifier);
	
	public List<Person> getPersons(final com.ats.aempi.model.Criteria criteria);
	
	public List<Person> getPersonsPaged(final com.ats.aempi.model.Criteria criteria, final int firstResult, final int maxResults);
			
	public List<Person> getPersons(final com.ats.aempi.model.Criteria personCriteria, final Set<PersonIdentifierEMPI> personIdentifier);
	
	public List<Person> getPersonsPaged(final com.ats.aempi.model.Criteria personCriteria,
			final Set<PersonIdentifierEMPI> personIdentifiers, final int firstResult, final int maxResults,
			final boolean hydrate);
	
	public Person loadPerson(Integer personId);
	
	public List<Person> loadPersons(List<Integer> personIds);
	
	public List<IdentifierDomainDict> getIdentifierDomains();
	
	//yrh-2012-08-07 IHE-C测试A31消息推送关联病人
	public List<PersonIdentifierEMPI> getPidS(final Person person);
	
	public List<String> getIdentifierDomainTypeCodes();
	
	public void addIdentifierDomain(IdentifierDomainDict identifierDomain);
	
	public void removeIdentifierDomain(IdentifierDomainDict identifierDomain);
	
	public boolean isKnownUniversalIdentifierTypeCode(String universalIdentifierTypeCode);
	
	public IdentifierDomainAttribute addIdentifierDomainAttribute(IdentifierDomainDict identifierDomain, String attributeName, String attributeValue);
	
	public IdentifierDomainAttribute getIdentifierDomainAttribute(IdentifierDomainDict identifierDomain, String attributeName);
	
	public List<IdentifierDomainAttribute> getIdentifierDomainAttributes(IdentifierDomainDict identifierDomain);
	
	public void updateIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute);
	
	public void removeIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute);
	
	public RaceDict findRaceByName(String raceName);
	
	public RaceDict findRaceByCode(String raceCode);
	
	public GenderDict findGenderByName(String genderName);

	public GenderDict findGenderByCode(String genderCode);
	
	//PANMIN-20150629 自动merge的存储过程调用
	public String runProcedureForEmpiMerge(List<String>myempi,String empi,int ControlID) throws ApplicationException, SQLException;

//	public List<Person> queryPersonByParam(final Person person);

	List<NewPerson> queryPersonByParam(Person person)throws Exception;
}
