package com.ats.aempi.dao.hibernate;

import java.math.BigDecimal;
import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

import com.ats.aempi.model.*;
import com.ats.aexchange.utils.StringUtil;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.dao.PersonDao;
import com.ats.aempi.util.DBUtil;
import com.ats.aexchange.config.PropertyFacade;
import com.ats.apixpdq.common.PixPdqConstants;

import org.springframework.orm.hibernate3.HibernateCallback;

public class PersonDaoHibernate extends UniversalDaoHibernate implements PersonDao
{
	protected final Log logger = LogFactory.getLog(getClass());
	public void SaveTransPerson(TransPerson transperson)
	{
		log.debug("Saving transperson record: " + transperson);
		getHibernateTemplate().saveOrUpdate(transperson);
		getHibernateTemplate().flush();
		log.debug("Finished saving the transperson.");
	}
	
	
	public void addContactPerson(List<Contactperson> contactperson,Person person) throws ApplicationException
	{
		for(Contactperson tempcontact:contactperson)
		{
			if(person.getPersonId()>0)
			{
				tempcontact.setPersonId(person.getPersonId());
			}
			else
			{
				throw new ApplicationException("PERSON_ID,获取失败，严重错误");
			}
			
			//loadRelationship(tempcontact);
			
			getHibernateTemplate().saveOrUpdate(tempcontact);
			
			getHibernateTemplate().flush();
		}
		log.debug("Finished saving the person.");
		
	}
	
	public void addMergeEvent(MergeEvent mergeEvent) 
	{

		getHibernateTemplate().saveOrUpdate(mergeEvent);
		
		getHibernateTemplate().flush();
		
	}
	
	public void addRelevanceRecord(RelevanceAndId relevanceandid) 
	{

		getHibernateTemplate().saveOrUpdate(relevanceandid);
		
		getHibernateTemplate().flush();
		
	}
	
	public void addPerson(Person person) 
	{
		log.debug("Saving person record: " + person);
		
		for (PersonIdentifierEMPI identifier : person.getPersonIdentifiers()) 
		{
			identifier.setDateCreated(person.getDateCreated());
			
			identifier.setAppUserByCreatorId(person.getAppUserByCreatorId());
			
			identifier.getIdentifierDomainDict().setDateCreated(person.getDateCreated());
			
			identifier.getIdentifierDomainDict().setAppUser(person.getAppUserByCreatorId());
			
			if(person.getEmpi()!=null)
			{
				identifier.setCustom1(person.getEmpi());
			}
			
			saveIdentifierDomain(identifier.getIdentifierDomainDict());
		}
		 	
		loadAssociations(person);

		getHibernateTemplate().saveOrUpdate(person);
		
		getHibernateTemplate().flush();
		
		log.debug("Finished saving the person.");
	}

	public void updatePerson(Person person) 
	{
		log.debug("Saving person record: " + person);
		
		loadAssociations(person);
		
		getHibernateTemplate().update(person);
		
		getHibernateTemplate().flush();
		
		log.debug("Finished saving the person.");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getPersonsByIdentifier(final PersonIdentifierEMPI personIdentifier) {
		return (List<Person>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(Person.class)
					.add(Restrictions.isNull("dateVoided"))
					.createAlias("personIdentifiers", "pi")
					.createAlias("personIdentifiers.identifierDomainDict", "id")
					//.add(Expression.eq("pi.identifier", personIdentifier.getIdentifier()));
				    .add(Expression.like("pi.identifier", personIdentifier.getIdentifier()));
				
				//System.out.println(criteria);
				
				//System.out.println(personIdentifier.getIdentifier());
				
				//System.out.println(personIdentifier.getIdentifierDomainDict());

				if (personIdentifier.getIdentifierDomainDict() != null) {
					if (personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() != null) {
						criteria.add(Restrictions.eq("id.namespaceIdentifier", personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier()));
					}
					if (personIdentifier.getIdentifierDomainDict().getUniversalIdentifier() != null) {
						criteria.add(Restrictions.eq("id.universalIdentifier", personIdentifier.getIdentifierDomainDict().getUniversalIdentifier()));
					}
					if (personIdentifier.getIdentifierDomainDict().getUniversalIdentifierTypeCode() != null) {
						criteria.add(Restrictions.eq("id.universalIdentifierTypeCode", personIdentifier.getIdentifierDomainDict().getUniversalIdentifierTypeCode()));
					}
				}
				List<Person> list = criteria.list();
				log.debug("Query by partial identifier returned: " + list.size() + " elements.");
				return list;
			}
		});		
	}
	
	public Person loadPerson(final Integer personId) {
		return (Person) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {

				 String hql = "from Person where dateVoided is null " +
				   		 "and personId='" + personId + "'";
				
				
				Query queryObject = session.createQuery(hql);	
				
				List<Person> list=queryObject.list();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				if (list.size() == 0) {
					return null;
				}
				Person person = list.get(0);
				list=null;
				session.evict(person);
				return person;
			}
		}
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> loadPersons(final List<Integer> personIds) {
		return (List<Person>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Person> persons = new ArrayList<Person>();
				for(Integer personId : personIds) {
					Person p = (Person) session.load(Person.class, personId);
					persons.add(p);
				}
				return persons;
			}});
	}
	//panm-2012-05-29
	public Person getPersonForID(final PersonIdentifierEMPI personIdentifier) 
	{
		return (Person) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String QueryString="select * from Person_Identifier,Identifier_Domain_Dict" 
					             + " where Person_Identifier.date_Voided is null"
					             + " and Person_Identifier.identifier='" + personIdentifier.getIdentifier() + "'"
					             + " and Identifier_Domain_Dict.namespace_Identifier='" + personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() + "'"
					             + " and rownum<2";
				
				System.out.print(QueryString);
				
				Query QueryObject=session.createSQLQuery(QueryString);
							
				List<Person> list = QueryObject.list();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				
				if (list.size() == 0) 
				{
					return null;
				}
				
				Person person=new Person();
				
				person.setAccount(list.toString());
				
				session.evict(person);
				
				return person;
			}
		}
		);
	}
	
	public Person getPersonById(final PersonIdentifierEMPI personIdentifier) 
	{
		return (Person) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				//log.fatal("TEMP");
					
				Criteria criteria = session.createCriteria(Person.class)
					.add(Restrictions.isNull("dateVoided"))
					.createAlias("personIdentifiers", "pi")
					.createAlias("personIdentifiers.identifierDomainDict", "id")
					.add(Expression.eq("pi.identifier", personIdentifier.getIdentifier()));
			
				if (personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() != null) {
					
					criteria.add(Restrictions.eq("id.namespaceIdentifier", personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier()));
					criteria.add(Restrictions.eq("id.universalIdentifier", personIdentifier.getIdentifierDomainDict().getUniversalIdentifier()));
					
			         log.fatal("查询注册病人，条件为：" + personIdentifier.getIdentifier() + " | " + personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() + " | " + personIdentifier.getIdentifierDomainDict().getUniversalIdentifier());
			         
			         //System.out.println("查询注册病人：" + personIdentifier.getIdentifier() + " | " + personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() + " | " + personIdentifier.getIdentifierDomainDict().getUniversalIdentifier());
			         
			         
				}
				
			
				List<Person> list = criteria.list();
				
				log.fatal("符合查询条件的记录为：" + list.size());
				
				if (list.size() == 0) {
					return null;
				}
				Person person = list.get(0);
				list=null;
				session.evict(person);
				return person;
			}
		}
		);
	}
	
	public Person getPersonById2(final PersonIdentifierEMPI personIdentifier) 
	{
		return (Person) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String hql=null;
				Date birthday=null;
				if (personIdentifier.getPerson()!=null){
					birthday=personIdentifier.getPerson().getDateOfBirth();
				}

				   hql = "from Person where dateVoided is null " +
				   		 " and custom16='" + personIdentifier.getIdentifier() + "' " +//机构内病人ID
				   		 " and custom10='" + personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() + "' " + //域名
				   		 " and custom11='" + personIdentifier.getIdentifierDomainDict().getUniversalIdentifier() + "' ";  //机构域id

//				   		 " and NAME='" + personIdentifier.getPerson().getName() + "' ";


						   if(birthday!=null){
							   hql=hql+" and DATE_OF_BIRTH=?";
						   }
							if (personIdentifier.getPerson()!=null&&StringUtils.isNotEmpty(personIdentifier.getPerson().getIdentityNo())){
								hql=hql+" and IDENTITY_NO='" + personIdentifier.getPerson().getIdentityNo() + "' " ;
							}

				
				Query queryObject = session.createQuery(hql);
				if (birthday!=null)
					queryObject.setParameter(0,birthday);
				List<Person> list=queryObject.list();
				
				//System.out.println(criteria);
				//List<Person> list = criteria.list();
				//log.fatal("Query by identifier returned: " + list.size() + " elements.");
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				if (list.size() == 0) {
					return null;
				}
				Person person = list.get(0);
				list=null;
				session.evict(person);
				return person;
			}
		}
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getPersonById3(final Person person) 
	{
		return (List<Person>) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String hql=null;

				   hql = "from Person where dateVoided is null " +
				   		 "and empi='" + person.getEmpi()+ "'";	
				
				
				Query queryObject = session.createQuery(hql);	
				
				List<Person> list=queryObject.list();

				log.debug("Query by identifier returned: " + list.size() + " elements.");
				if (list.size() == 0) {
					return null;
				}
				
				session.evict(list);
				return list;
			}
		}
		);
	}
	
	public List<Person> getPersons(final com.ats.aempi.model.Criteria criteria) {
		return getPersonsPaged(criteria, false, 0, 0);
	}
	
	public List<Person> getPersons(final com.ats.aempi.model.Criteria personCriteria,
			final Set<PersonIdentifierEMPI> personIdentifiers) {
		return getPersonsPaged(personCriteria, personIdentifiers, false, 0, 0);
	}
	
	public List<Person> getPersonsPaged(final com.ats.aempi.model.Criteria criteria,
			final int firstResult, final int maxResults) {
		return getPersonsPaged(criteria, true, firstResult, maxResults);
	}
	
	public List<Person> getPersonsPaged(final com.ats.aempi.model.Criteria personCriteria,
			final Set<PersonIdentifierEMPI> personIdentifiers, final int firstResult, final int maxResults,
			final boolean hydrate) {
		return getPersonsPaged(personCriteria, personIdentifiers, true, firstResult, maxResults);
	}
	
	@SuppressWarnings("unchecked")
	private List<Person> getPersonsPaged(final com.ats.aempi.model.Criteria criteria,
			final boolean paging, final int firstResult, final int maxResults) {
		return  (List<Person>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//获取查询条件 yrh-2012-03-30
				org.hibernate.Criteria criteriaHibernate = buildHibernateCriteria(session, criteria);
				if (paging) {
					criteriaHibernate.setFirstResult(firstResult);
					criteriaHibernate.setMaxResults(maxResults);
				}
				log.debug("Querying by criteria using " + criteriaHibernate.toString());
				//criteriaHibernate.
				List<Person> list = (List<Person>) criteriaHibernate.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
				
				//System.out.println(criteriaHibernate.toString());
				log.debug("Query by criteria returned: " + list.size() + " elements.");
				return list;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private List<Person> getPersonsPaged(final com.ats.aempi.model.Criteria personCriteria,
			final Set<PersonIdentifierEMPI> personIdentifiers, final boolean paging, final int firstResult,
			final int maxResults) {
		return  (List<Person>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				org.hibernate.Criteria criteriaHibernate = buildHibernateCriteria(session, personCriteria, personIdentifiers);
				if (paging) {
					criteriaHibernate.setFirstResult(firstResult);
					criteriaHibernate.setMaxResults(maxResults);
				}
				log.debug("Querying by criteria using " + criteriaHibernate.toString());
				List<Person> list = (List<Person>) criteriaHibernate.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
				log.debug("Query by criteria returned: " + list.size() + " elements.");
				return list;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<IdentifierDomainDict> getIdentifierDomains() {
		List<IdentifierDomainDict> domains = (List<IdentifierDomainDict>) getHibernateTemplate().find("from IdentifierDomainDict");
		log.trace("Obtained the list of identifier domains with " + domains.size() + " entries.");
		return domains;
	}
	
	//潘-2012-08-07
	@SuppressWarnings("unchecked")
	public List<PersonIdentifierEMPI> getPidS(final Person person)
	{
		return (List<PersonIdentifierEMPI>) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String QueryString="select person_identifier.identifier,person.custom16,person.custom10,person.custom11,person.custom12 from person_identifier,person" 
					             + " where person_identifier.person_id=person.person_id and Person_Identifier.person_id in ("
					             + " (select RH_PERSON_ID from person_link where LH_PERSON_ID='" + person.getPersonId()+ "')"
					             + " union (select LH_PERSON_ID from person_link where LH_PERSON_ID='" + person.getPersonId() + "')"
					             +")";
				//System.out.print(QueryString);
				
				Query QueryObject=session.createSQLQuery(QueryString);
							
				List<Object> list = QueryObject.list();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				
				if (list.size() == 0) 
				{
					return null;
				}

				
				
				List<PersonIdentifierEMPI> ret = new ArrayList<PersonIdentifierEMPI>();
				
				for (int i=0;i<list.size();i++) {
				
						Object[] o =(Object[])list.get(i);
						
						PersonIdentifierEMPI personidentifier=new PersonIdentifierEMPI();
						
						personidentifier.setIdentifier(o[0].toString());
						
						IdentifierDomainDict myDict = new IdentifierDomainDict();
						
					    myDict.setNamespaceIdentifier(o[2].toString());
					    
					    myDict.setUniversalIdentifier(o[3].toString());
					    
					    myDict.setUniversalIdentifierTypeCode(o[4].toString());
					    
					    personidentifier.setIdentifierDomainDict(myDict);
				
					    ret.add(personidentifier);
				}
				
				return ret;
			}
		}
		);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> getIdentifierDomainTypeCodes() {
		String sql = "select distinct i.universalIdentifierTypeCode from IdentifierDomainDict i where i.universalIdentifierTypeCode is not null order by i.universalIdentifierTypeCode";
		List<String> codes = (List<String>) getHibernateTemplate().find(sql);
		log.trace("Obtained the list of universal identifier type codes of size " + codes.size() + " entries.");
		return codes;
	}
	
	public void addIdentifierDomain(IdentifierDomainDict identifierDomain) {
		getHibernateTemplate().saveOrUpdate(identifierDomain);
		getHibernateTemplate().flush();
		log.debug("Finished saving the identifier domain.");
	}
	
	public void removeIdentifierDomain(IdentifierDomainDict identifierDomain) {
		getHibernateTemplate().delete(identifierDomain);
		getHibernateTemplate().flush();
		log.debug("Removed an identifier domain instance.");
	}
	
	@SuppressWarnings("unchecked")
	public boolean isKnownUniversalIdentifierTypeCode(String universalIdentifierTypeCode) {
		String queryString = "from IdentifierDomain i where i.universalIdentifierTypeCode = ?";
		List<IdentifierDomainDict> domains = getHibernateTemplate().find(queryString, universalIdentifierTypeCode);
		if (domains.size() == 0) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public IdentifierDomainAttribute addIdentifierDomainAttribute(IdentifierDomainDict identifierDomain, String attributeName, String attributeValue) {
		if (identifierDomain == null || identifierDomain.getIdentifierDomainId() == null) {
			log.debug("User attempted to add identifier domain attribute for an unknown identifier domain: " + identifierDomain);
			return null;
		}
		IdentifierDomainDict foundIdentifierDomain = (IdentifierDomainDict) getHibernateTemplate().get(IdentifierDomainDict.class, identifierDomain.getIdentifierDomainId());
		if (foundIdentifierDomain == null) {
			log.debug("User attempted to add identifier domain attribute for an unknown identifier domain: " + identifierDomain);
			return null;
		}
		// Now check to see if this attribute already exists in which case this should be an update operation
		String queryString = "from IdentifierDomainAttribute i where i.identifierDomainId = ? and i.attributeName = ?";
		List<IdentifierDomainAttribute> attribs = (List<IdentifierDomainAttribute>) getHibernateTemplate().find(queryString,
				new Object[] {foundIdentifierDomain.getIdentifierDomainId(), attributeName});
		if (attribs.size() > 0) {
			IdentifierDomainAttribute attribute = attribs.get(0);
			log.debug("User attempted to add an attribute that already exists in the repository: " + attribute);
			throw new RuntimeException("This attribute already exists in the repository.");
		}
		IdentifierDomainAttribute attribute = new IdentifierDomainAttribute(foundIdentifierDomain.getIdentifierDomainId(), attributeName, attributeValue);
		getHibernateTemplate().saveOrUpdate(attribute);
		getHibernateTemplate().flush();
		log.debug("Finished saving the identifier domain attribute: " + attribute);
		return attribute;
	}
	
	@SuppressWarnings("unchecked")
	public IdentifierDomainAttribute getIdentifierDomainAttribute(IdentifierDomainDict identifierDomain, String attributeName) {
		if (identifierDomain == null || identifierDomain.getIdentifierDomainId() == null || attributeName == null) {
			log.debug("User attempted to retrieve identifier domain attribute without providing the appropriate query criteria.");
			return null;
		}
		String queryString = "from IdentifierDomainAttribute i where i.identifierDomainId = ? and i.attributeName = ?";
		List<IdentifierDomainAttribute> attribs = (List<IdentifierDomainAttribute>) getHibernateTemplate().find(queryString,
				new Object[] {identifierDomain.getIdentifierDomainId(), attributeName});
		if (attribs.size() == 0) {
			return null;
		}
		IdentifierDomainAttribute attrib = attribs.get(0);
		log.trace("Loaded the identifier domain attribute: " + attrib);
		return attrib;
	}
	
	@SuppressWarnings("unchecked")
	public List<IdentifierDomainAttribute> getIdentifierDomainAttributes(IdentifierDomainDict identifierDomain) {
		if (identifierDomain == null || identifierDomain.getIdentifierDomainId() == null) {
			log.debug("User attempted to retrieve list of identifier domain attributes without providing the appropriate query criteria.");
			return null;
		}
		String queryString = "from IdentifierDomainAttribute i where i.identifierDomainId = ?";
		List<IdentifierDomainAttribute> attribs = (List<IdentifierDomainAttribute>) getHibernateTemplate().find(queryString,
				new Object[] {identifierDomain.getIdentifierDomainId()});
		log.trace("Loaded list of identifier domain attributes: " + attribs);
		return attribs;
	}
	
	public void updateIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute) {
		if (identifierDomainAttribute == null || 
				identifierDomainAttribute.getIdentifierDomainAttributeId() == null) {
			log.debug("User attempted to update identifier domain attribute without providing the appropriate query criteria.");
			return;
		}
		getHibernateTemplate().update(identifierDomainAttribute);
		getHibernateTemplate().flush();
		log.trace("Updated the identifier domain attribute: " + identifierDomainAttribute);
	}
	
	public void removeIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute) {
		if (identifierDomainAttribute == null || identifierDomainAttribute.getIdentifierDomainAttributeId() == null) {
			log.debug("User attempted to delete an identifier domain attribute without providing the appropriate query criteria.");
			return;
		}
		getHibernateTemplate().delete(identifierDomainAttribute);
		getHibernateTemplate().flush();
		log.debug("Removed an identifier domain instance.");
	}
	
	private void loadAssociations(Person person) {
		loadAddressType(person);
		loadEthnicGroup(person);
		loadGender(person);
		loadLanguage(person);
		loadNameType(person);
		loadNationality(person);
		loadPhoneType(person);
		loadRace(person);
		loadReligion(person);
		loadMaritasStatus(person);
		loadDegreeTypeDict(person);
		loadProfessionTypeDict(person);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void loadDegreeTypeDict(Person person) {
		if (person.getDegreeTypeDict() == null) {
			return;
		}
		List<DegreeTypeDict> list = (List<DegreeTypeDict>) getHibernateTemplate()
			.find("from DegreeTypeDict as r where r.degreeTypeCode = ?",
					person.getDegreeTypeDict().getDegreeTypeCode());
		if (list.size() == 0) {
			log.error("输入关系超出字典范围");
			getHibernateTemplate().save(person.getDegreeTypeDict());
		} else {
			person.setDegreeTypeDict(list.get(0));
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void loadProfessionTypeDict(Person person) {
		if (person.getProfessionTypeDict() == null) {
			return;
		}
		List<ProfessionTypeDict> list = (List<ProfessionTypeDict>) getHibernateTemplate()
			.find("from ProfessionTypeDict as r where r.professionTypeCode = ?",
					person.getProfessionTypeDict().getProfessionTypeCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getProfessionTypeDict());
		} else {
			person.setProfessionTypeDict(list.get(0));
		}
	}

	
	@SuppressWarnings("unchecked")
	private void loadMaritasStatus(Person person) {
		//log.debug(person.getMaritalStatusDict().getMaritalStatusCode());
		if (person.getMaritalStatusDict() == null || person.getMaritalStatusDict().getMaritalStatusCd()!= null) {
			return;
		}
		List<MaritalStatusDict> list = (List<MaritalStatusDict>) getHibernateTemplate()
			.find("from MaritalStatusDict as l where l.maritalStatusCode = ?",
					person.getMaritalStatusDict().getMaritalStatusCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getMaritalStatusDict());
		} else {
			person.setMaritalStatusDict(list.get(0));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadReligion(Person person) {
		if (person.getReligionDict() == null || person.getReligionDict().getReligionCd() != null) {
			return;
		}
		List<ReligionDict> list = (List<ReligionDict>) getHibernateTemplate()
			.find("from ReligionDict as r where r.religionCode = ?",
					person.getReligionDict().getReligionCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getReligionDict());
		} else {
			person.setReligionDict(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private void loadRace(Person person) {
		if (person.getRaceDict() == null || person.getRaceDict().getRaceCd() != null) {
			return;
		}
		List<RaceDict> list = (List<RaceDict>) getHibernateTemplate()
			.find("from RaceDict as r where r.raceCode = ?",
					person.getRaceDict().getRaceCode());
		if (list.size() == 0) {
			log.debug("查找结果" + list.size());
			getHibernateTemplate().save(person.getRaceDict());
		} else {
			person.setRaceDict(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private void loadNationality(Person person) {
		if (person.getNationalityDict() == null || person.getNationalityDict().getNationalityCd() != null) {
			return;
		}
		List<NationalityDict> list = (List<NationalityDict>) getHibernateTemplate()
			.find("from NationalityDict as n where n.ationalityCode = ?",
					person.getNationalityDict().getAtionalityCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getNationalityDict());
		} else {
			person.setNationalityDict(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private void loadNameType(Person person) {
		if (person.getNameTypeDict() == null || person.getNameTypeDict().getNameTypeCd() != null) {
			return;
		}
		List<NameTypeDict> list = (List<NameTypeDict>) getHibernateTemplate()
			.find("from NameTypeDict as n where n.nameTypeCode = ?",
					person.getNameTypeDict().getNameTypeCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getNameTypeDict());
		} else {
			person.setNameTypeDict(list.get(0));
		}		
	}

	@SuppressWarnings("unchecked")
	private void loadPhoneType(Person person) {
		if (person.getPhoneTypeDictByPhoneTypeCd() == null || person.getPhoneTypeDictByPhoneTypeCd().getPhoneTypeCd() != null) {
			return;
		}
		List<PhoneTypeDict> list = (List<PhoneTypeDict>) getHibernateTemplate()
			.find("from PhoneTypeDict as p where p.phoneTypeCode = ?",
					person.getPhoneTypeDictByPhoneTypeCd().getPhoneTypeCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getPhoneTypeDictByPhoneTypeCd());
		} else {
			person.setPhoneTypeDictByPhoneTypeCd(list.get(0));
		}		
	}

	@SuppressWarnings("unchecked")
	private void loadLanguage(Person person) {
		if (person.getLanguageDict() == null || person.getLanguageDict().getLanguageCd() != null) {
			return;
		}
		List<LanguageDict> list = (List<LanguageDict>) getHibernateTemplate()
			.find("from LanguageDict as l where l.languageCode = ?",
					person.getLanguageDict().getLanguageCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getLanguageDict());
		} else {
			person.setLanguageDict(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private void loadGender(Person person) {
		if (person.getGenderDict() == null || person.getGenderDict().getGenderCd() != null) {
			return;
		}
		List<GenderDict> list = (List<GenderDict>) getHibernateTemplate().find("from GenderDict as g where g.genderCode = ?",
					person.getGenderDict().getGenderCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getGenderDict());
		} else {
			person.setGenderDict(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private void loadEthnicGroup(Person person) {
		if (person.getEthnicgroupDict() == null || person.getEthnicgroupDict().getEthnicGroupCd() != null) {
			return;
		}
		List<EthnicgroupDict> list = (List<EthnicgroupDict>) getHibernateTemplate()
			.find("from EthnicgroupDict as e where e.ethnicGroupCode = ?",
					person.getEthnicgroupDict().getEthnicGroupCode());
		if (list.size() == 0) {
			getHibernateTemplate().save(person.getEthnicgroupDict());
		} else {
			person.setEthnicgroupDict(list.get(0));
		}
	}

	@SuppressWarnings("unchecked")
	private void loadAddressType(Person person) {
//		if (person.getAddressTypeDictByAddressTypeCd() == null || person.getAddressTypeDictByAddressTypeCd().getAddressTypeCd() != null) {
//			return;
//		}
//		List<AddressTypeDict> list = (List<AddressTypeDict>) getHibernateTemplate()
//			.find("from AddressTypeDict as n where n.addressTypeCode = ?",
//					person.getAddressTypeDictByAddressTypeCd().getAddressTypeCode());
//		if (list.size() == 0) {
//			getHibernateTemplate().save(person.getAddressTypeDictByAddressTypeCd());
//		} else {
//			person.setAddressTypeDictByAddressTypeCd(list.get(0));
//		}
	}

	private org.hibernate.Criteria buildHibernateCriteria(Session session, com.ats.aempi.model.Criteria criteria) {
		org.hibernate.Criteria criteriaHibernate = session.createCriteria(Person.class).add(Restrictions.isNull("dateVoided"));
		for (Criterion criterion : criteria.getCriteria()) {
			addCriterion(criteriaHibernate, criterion);
		}
		log.debug("查询条件为:" + criteriaHibernate);
		return criteriaHibernate;
	}
	
	private org.hibernate.Criteria buildHibernateCriteria(Session session, com.ats.aempi.model.Criteria personCriteria, Set<PersonIdentifierEMPI> personIdentifiers) {
		Criteria criteria = session.createCriteria(Person.class).add(Restrictions.isNull("dateVoided"));
		if (personIdentifiers.size() > 0) {
			criteria.createAlias("personIdentifiers", "pi");
		}
		if (hasIdentifierDomainAttributes(personIdentifiers)) {
			criteria.createAlias("personIdentifiers.identifierDomainDict", "id");
		}
		for (Criterion criterion : personCriteria.getCriteria()) {
			addCriterion(criteria, criterion);
		}
		
		Disjunction disjunction = Restrictions.disjunction();
		for (PersonIdentifierEMPI personIdentifier : personIdentifiers) {
			if (personIdentifier.getIdentifier() != null) {
				//disjunction.add(Expression.eq("pi.identifier", personIdentifier.getIdentifier()));			
				disjunction.add(Expression.like("pi.identifier", personIdentifier.getIdentifier()));
			}
			if (personIdentifier.getIdentifierDomainDict() != null) {
				if (personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() != null) {
					//disjunction.add(Restrictions.eq("id.namespaceIdentifier", personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier()));
					
					disjunction.add(Restrictions.like("id.namespaceIdentifier", personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier()));
				} else {
					disjunction.add(Restrictions.and(
							Restrictions.eq("id.universalIdentifier", personIdentifier.getIdentifierDomainDict().getUniversalIdentifier()),
							Restrictions.eq("id.universalIdentifierTypeCode", personIdentifier.getIdentifierDomainDict().getUniversalIdentifierTypeCode())));
					//Restrictions.like("id.universalIdentifier", personIdentifier.getIdentifierDomainDict().getUniversalIdentifier()),
					//Restrictions.like("id.universalIdentifierTypeCode", personIdentifier.getIdentifierDomainDict().getUniversalIdentifierTypeCode())));
		
				}
			}
		}
		return criteria.add(disjunction);		
	}

	private boolean hasIdentifierDomainAttributes(Set<PersonIdentifierEMPI> personIdentifiers) {
		for (PersonIdentifierEMPI pi : personIdentifiers) {
			if (pi.getIdentifierDomainDict() != null && (pi.getIdentifierDomainDict().getNamespaceIdentifier() != null ||
					pi.getIdentifierDomainDict().getUniversalIdentifier() != null ||
					pi.getIdentifierDomainDict().getUniversalIdentifierTypeCode() != null)) {
				return true;
			}
		}
		return false;
	}

	private void addCriterion(org.hibernate.Criteria criteriaHibernate, com.ats.aempi.model.Criterion criterion) {
		if (criterion instanceof ExtendedCriterion) {
			ExtendedCriterion extended = (ExtendedCriterion) criterion;
			criteriaHibernate.createAlias(extended.getAssociationPath(), extended.getAlias());
		}
		if (criterion.getOperation().equals(Operation.EQ) || criterion.getValue() instanceof java.util.Date) {
			criteriaHibernate.add(Restrictions.eq(criterion.getName(), criterion.getValue()));
		} else if (criterion.getOperation().equals(Operation.ISNOTNULL)) {
			criteriaHibernate.add(Restrictions.isNotNull(criterion.getName()));
		} else if (criterion.getOperation().equals(Operation.ISNULL)) {
			criteriaHibernate.add(Restrictions.isNull(criterion.getName()));
		} else if (criterion.getOperation().equals(Operation.LIKE)) {
			//criteriaHibernate.add(Restrictions.eq(criterion.getName(), criterion.getValue()));
			
			criteriaHibernate.add(Restrictions.like(criterion.getName(), criterion.getValue()));
		} else if (criterion.getOperation().equals(Operation.EQ)) {
			criteriaHibernate.add(Restrictions.eq(criterion.getName(), criterion.getValue()));
		} else if (criterion.getOperation().equals(Operation.NE)) {
			criteriaHibernate.add(Restrictions.ne(criterion.getName(), criterion.getValue()));
		}
	}
	
	public void saveIdentifierDomain(IdentifierDomainDict identifierDomain) {
		log.debug("Looking for existing identifier domain " + identifierDomain);
		IdentifierDomainDict idFound = findIdentifierDomain(identifierDomain);
		if (idFound != null) {
			identifierDomain.setIdentifierDomainId(idFound.getIdentifierDomainId());
			log.debug("Identifier domain already exists: " + identifierDomain);
			//System.out.println("Identifier domain already exists: " + identifierDomain);
			return;
		}
		getHibernateTemplate().save(identifierDomain);
		getHibernateTemplate().flush();
	}
	
	public IdentifierDomainDict findIdentifierDomain(final IdentifierDomainDict identifierDomain) {
		if (identifierDomain.getIdentifierDomainId() != null) {
			return (IdentifierDomainDict) getHibernateTemplate().load(IdentifierDomainDict.class, identifierDomain.getIdentifierDomainId());
		}
		return (IdentifierDomainDict) getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(IdentifierDomainDict.class);
				/**
				 * A domain identifier uniquely identifies a domain either through the namespace identifier or through the
				 * combination of universalIdentifier and universalIdentifierTypeCode
				 */
				if (identifierDomain.getNamespaceIdentifier() != null) {
					criteria.add(Restrictions.eq("namespaceIdentifier", identifierDomain.getNamespaceIdentifier()));
					criteria.add(Restrictions.eq("universalIdentifier", identifierDomain.getUniversalIdentifier()));
							
				}
				List<Person> list = criteria.list();
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				if (list.size() == 0) {
					return null;
				}
				return list.get(0);
			}
		});		
	}

	@SuppressWarnings("unchecked")
	public GenderDict findGenderByCode(String genderCode) {
		List<GenderDict> list = (List<GenderDict>) getHibernateTemplate().
			find("from GenderDict as g where g.genderCode = ?", genderCode);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public RaceDict findRaceByCode(String raceCode) {
		List<RaceDict> list = (List<RaceDict>) getHibernateTemplate().
			find("from RaceDict as r where r.raceCode = ?", raceCode);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public GenderDict findGenderByName(String genderName) {
		List<GenderDict> list = (List<GenderDict>) getHibernateTemplate().
			find("from GenderDict as g where g.genderName = ?", genderName);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public RaceDict findRaceByName(String raceName) {
		List<RaceDict> list = (List<RaceDict>) getHibernateTemplate().
			find("from RaceDict as r where r.raceName = ?", raceName);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
	
	public String runProcedureForEmpiMerge(List<String>myempi,String empi,int ControlID) throws ApplicationException, SQLException
	{
		String myA1 = "'" + empi + "'";
		
		String myB1 = "('";
		
		for(String temp :myempi)
		{
			myB1 = myB1 + temp + "','";
		}
		
		myB1 = myB1 + "00')";
				
		if (ControlID == 1) {
			

			DBUtil dbUtil = new DBUtil();

			Connection conn = dbUtil.openConnection();
			
			conn.setAutoCommit(false);

			try {
				
				Statement mystatement = conn.createStatement();
				
				mystatement.addBatch(PropertyFacade.getString("new.auto.merge.insert.mergeevent.sql").replace("A1", myA1).replace("B1", myB1).replace("A2", "'" + UUID.randomUUID().toString() + "'"));
				log.fatal(PropertyFacade.getString("new.auto.merge.insert.mergeevent.sql").replace("A1", myA1).replace("B1", myB1).replace("A2", "'" + UUID.randomUUID().toString() + "'"));
				
				mystatement.addBatch(PropertyFacade.getString("new.auto.merge.empi.sql").replace("A1", myA1).replace("B1", myB1));
				log.fatal(PropertyFacade.getString("new.auto.merge.empi.sql").replace("A1", myA1).replace("B1", myB1));
				
				mystatement.addBatch(PropertyFacade.getString("new.auto.merge.person.sql").replace("A1", myA1).replace("B1", myB1));
				log.fatal(PropertyFacade.getString("new.auto.merge.person.sql").replace("A1", myA1).replace("B1", myB1));
				
				mystatement.addBatch(PropertyFacade.getString("new.auto.merge.personidentifier.identifier.sql").replace("A1", myA1).replace("B1", myB1));
				log.fatal(PropertyFacade.getString("new.auto.merge.personidentifier.identifier.sql").replace("A1", myA1).replace("B1", myB1));
				
				mystatement.addBatch(PropertyFacade.getString("new.auto.merge.personidentifier.custom1.sql").replace("A1", myA1).replace("B1", myB1));
				log.fatal(PropertyFacade.getString("new.auto.merge.personidentifier.custom1.sql").replace("A1", myA1).replace("B1", myB1));
				
				//mystatement.addBatch(PropertyFacade.getString("new.auto.merge.patientvisit.sql").replace("A1", myA1).replace("B1", myB1));
				//log.fatal(PropertyFacade.getString("new.auto.merge.patientvisit.sql").replace("A1", myA1).replace("B1", myB1));
				
				//mystatement.addBatch(PropertyFacade.getString("new.auto.merge.patientvisithistory.sql").replace("A1", myA1).replace("B1", myB1));
				//log.fatal(PropertyFacade.getString("new.auto.merge.patientvisithistory.sql").replace("A1", myA1).replace("B1", myB1));
						
				
				mystatement.executeBatch();
				
				conn.commit();
				
				return "true";
			} catch (BatchUpdateException e) {
				e.printStackTrace();
				try {
					conn.rollback();
					return "false";
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				dbUtil.closeConn(conn);
			}
		}
		return "true";

	}
	
	@SuppressWarnings("deprecation")
	public String runProcedureForEmpi(Person person,Person personFound,String ReplaceID,int ControlID) throws ApplicationException
	{
		CallableStatement statement = null;
		
		String returnMsg = null;
	
		try 
		{
			if(ControlID==0)
			{
				statement = getSession().connection().prepareCall("{Call UPDATE_TYPE_A(?,?,?,?,?,?,?)}");
			
				log.fatal("执行存储过程UPDATE_TYPE_A。。。");
				
				log.fatal("参数一，EMPI： " + person.getEmpi());
				
				log.fatal("参数二，旧EMPI： " + person.getCustom26());
				
				log.fatal("参数三，域ID：" + person.getCustom11());
				
				log.fatal("参数四，域名： " + person.getCustom10());
				
				log.fatal("参数五，PERSON_ID :" + personFound.getPersonId());
				
				log.fatal("参数六，REPLACE_ID :" + ReplaceID);
			
				statement.setString(1, person.getEmpi());
				
				statement.setString(2, person.getCustom26());
				
				statement.setString(3, person.getCustom11());
				
				statement.setString(4, person.getCustom10());
				
				statement.setString(5, String.valueOf(personFound.getPersonId()));
				
				statement.setString(6, ReplaceID);
				
				statement.registerOutParameter(7, Types.VARCHAR);
				
			}
			else if(ControlID==1)
			{
				statement = getSession().connection().prepareCall("{Call UPDATE_TYPE_B(?,?,?,?,?,?,?)}");
				
				log.fatal("执行存储过程UPDATE_TYPE_B。。。");
				
				log.fatal("参数一，EMPI： " + person.getEmpi());
				
				log.fatal("参数二，旧EMPI： " + person.getCustom25());
				
				log.fatal("参数三，域ID：" + person.getCustom11());
				
				log.fatal("参数四，域名： " + person.getCustom10());
				
				log.fatal("参数五，PERSON_ID :" + personFound.getPersonId());
				
				log.fatal("参数六，REPLACE_ID :" + ReplaceID);
			
				statement.setString(1, person.getEmpi());
				
				statement.setString(2, person.getCustom25());
				
				statement.setString(3, person.getCustom11());
				
				statement.setString(4, person.getCustom10());
				
				statement.setString(5, String.valueOf(personFound.getPersonId()));
				
				statement.setString(6, ReplaceID);
				
				statement.registerOutParameter(7, Types.VARCHAR);
			}
			else if(ControlID==2)
			{
				statement = getSession().connection().prepareCall("{Call UPDATE_TYPE_C(?,?,?,?,?,?,?)}");
				
				log.fatal("执行存储过程UPDATE_TYPE_C。。。");
				
				log.fatal("参数一，EMPI： " + person.getEmpi());
				
				log.fatal("参数二，旧EMPI： " + person.getCustom25());
				
				log.fatal("参数三，域ID：" + person.getCustom11());
				
				log.fatal("参数四，域名： " + person.getCustom10());
				
				log.fatal("参数五，PERSON_ID :" + personFound.getPersonId());
				
				log.fatal("参数六，REPLACE_ID :" + ReplaceID);
			
				statement.setString(1, person.getEmpi());
				
				statement.setString(2, person.getCustom25());
				
				statement.setString(3, person.getCustom11());
				
				statement.setString(4, person.getCustom10());
				
				statement.setString(5, String.valueOf(personFound.getPersonId()));
				
				statement.setString(6, ReplaceID);
				
				statement.registerOutParameter(7, Types.VARCHAR);
			}
			
	        statement.executeUpdate();
	        
	        returnMsg = statement.getString(7);
	        
	        return returnMsg;
	        
		} catch (SQLException re) 
		{
		    re.printStackTrace();
		    
		    if(ControlID==0)
			{
		    	throw new ApplicationException("存储过程:UPDATE_TYPE_A,调用失败，请检查存储过程的正确性");
			}
		    else if(ControlID==1)
			{
		    	throw new ApplicationException("存储过程:UPDATE_TYPE_B,调用失败，请检查存储过程的正确性");
			}
		    else if(ControlID==2)
			{
		    	throw new ApplicationException("存储过程:UPDATE_TYPE_C,调用失败，请检查存储过程的正确性");
			}
		    
		}
		
		return null;

	}
	
	public void procedureForXDS (String Empi,String OldEmpi,int ControlID) throws ApplicationException
	{
		CallableStatement statement = null;
		
		try 
		{
			if ( ControlID==0)
			{
				statement = getSession().connection().prepareCall("{Call UPDATE_FOR_XDS_A(?)}");
			
				System.out.println("执行存储过程A。。。");
				
				System.out.println("参数一，EMPI： " + Empi);
				
				statement.setString(1, Empi);
			}
			else if (ControlID==1)
			{
				statement = getSession().connection().prepareCall("{Call UPDATE_FOR_XDS_B(?,?)}");
				
				System.out.println("执行存储过程B。。。");
				
				System.out.println("参数一，EMPI： " + Empi);
				
				statement.setString(1, Empi);
				
				System.out.println("参数二，OldEMPI： " + OldEmpi);
				
				statement.setString(2, OldEmpi);
			}
				
	        statement.executeUpdate();
	        
		} catch (SQLException re) 
		{
		    re.printStackTrace();
		    
		    if ( ControlID==0)
			{
		    	throw new ApplicationException("存储过程:UPDATE_FOR_XDS_A,调用失败，请检查存储过程的正确性");
			}
		    else if(ControlID==1)
			{
		    	throw new ApplicationException("存储过程:UPDATE_FOR_XDS_B,调用失败，请检查存储过程的正确性");
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Person>  getEmpiById (String personid)
	{
		List<Person> list = (List<Person>) getHibernateTemplate().find("from Person as e where e.personId = ?",
				Long.valueOf(personid));
		
		return  list;
	}
	
	@SuppressWarnings("deprecation")
	public String runProcedureForMerge(Person leftperson,Person rightperson,int ControlID) throws ApplicationException
	{
		CallableStatement statement = null;
		
		String returnMsg = null;
		
		try 
		{
			if ( ControlID == 1)
			{
				statement = getSession().connection().prepareCall("{Call MERGE_TYPE_A(?,?,?)}");
			
				log.fatal("执行存储过程MERGE_TYPE_A。。。");
				
				log.fatal("参数一，LEFT_EMPI： " + leftperson.getEmpi());
				
				statement.setString(1, leftperson.getEmpi());
				
				log.fatal("参数二，RIGHT_EMPI： " + rightperson.getEmpi());
				
				statement.setString(2, rightperson.getEmpi());
				
				statement.registerOutParameter(3, Types.VARCHAR);
				
				statement.executeUpdate();
		        
		        returnMsg = statement.getString(3);
		        
		        return returnMsg;
			
			}
			else if ( ControlID == 2)
			{
				statement = getSession().connection().prepareCall("{Call MERGE_TYPE_B(?,?,?,?,?,?)}");
			
				log.fatal("执行存储过程MERGE_TYPE_B。。。");
				
				log.fatal("参数一，EMPI： " + rightperson.getEmpi());
				
				statement.setString(1, rightperson.getEmpi());
				
				log.fatal("参数二，PERSONID： " + String.valueOf(rightperson.getPersonId()));
				
				statement.setString(2, String.valueOf(rightperson.getPersonId()));
				
				log.fatal("参数三，IDENTIFIER： " + rightperson.getCustom16());
				
				statement.setString(3, String.valueOf(rightperson.getCustom16()));
				
				log.fatal("参数四，IDENTIFIER_DOMAIN： " + rightperson.getCustom11());
				
				statement.setString(4, String.valueOf(rightperson.getCustom11()));
				
				log.fatal("参数五，IDENTIFIER_DOMAIN_NAME： " + rightperson.getCustom10());
				
				statement.setString(5, String.valueOf(rightperson.getCustom10()));
				
				statement.registerOutParameter(6, Types.VARCHAR);
				
				statement.executeUpdate();
		        
		        returnMsg = statement.getString(6);
		        
		        return returnMsg;
						
			}
		
	        
		} catch (SQLException re) 
		{
		    re.printStackTrace();
		    
		    if ( ControlID==1)
			{
		    	throw new ApplicationException("存储过程:MERGE_TYPE_A,调用失败，请检查存储过程的正确性");
			}
		    if ( ControlID==2)
			{
		    	throw new ApplicationException("存储过程:MERGE_TYPE_B,调用失败，请检查存储过程的正确性");
			}
		   
		}
		return returnMsg;
	}
	
	public List<String> getPersonidByEmpi (String empi)
	{
		List<String> LinkList = new ArrayList<String>();
		
		try 
		{
			String queryString = "select person_id from person_link_cancel where empi ='" + empi + "'";
			
			Query queryObject = getSession().createSQLQuery(queryString);
			
			List list = queryObject.list();
			
			for(Object object : list) 
			{
				 BigDecimal entry = (BigDecimal)object;
				 
				 System.out.print(entry.toString());

				 LinkList.add(entry.toString());
			} 
			
		} catch (RuntimeException re) 
		{
			log.error("find all failed", re);
			
			throw re;
		}
		return LinkList;
		
	}
	
	public List<String> getPersonNameByField(Person person,String str,int type) throws ApplicationException {
		
		List<String> NameList = new ArrayList<String>();
		String queryString = null;

		try {
			
			if(type==1){
				
				String myField1 = getFieldString(person,str);
				
				if(myField1==null){
			    	return null;
			    }
				
			     queryString = PropertyFacade.getString("check.field.base.sql").replace("AAA1", "'" + myField1 + "'").replace("AAA2", "'" + PropertyFacade.getString("check.field.base.domain") + "'");
			
			}else if(type==2){
				
				String myField2 = getFieldZYString(person,str);
				
				if(myField2==null){
			    	return null;
			    }
				
			     queryString = PropertyFacade.getString("check.field.base.sql").replace("AAA1", "'" + myField2 + "'").replace("AAA2", "'" + PropertyFacade.getString("check.field.base.domain") + "'");
			
				
			}
			
			
			Query queryObject = getSession().createSQLQuery(queryString);

			List list = queryObject.list();

			for (Object object : list) {
				String entry = (String) object;

				NameList.add(entry.toString());
			}
			
			if(NameList.size()==0){
				return null;
			}
		
			try
			{
				runProcedureForNameReg(person,str,NameList,1);
			} 
			catch (ApplicationException e) {
					// TODO Auto-generated catch block
				throw new ApplicationException("姓名对比失败");
			} 
			catch (SQLException e) {
					// TODO Auto-generated catch block
				throw new ApplicationException("姓名对比失败");
			}
			

		} catch (RuntimeException re) {
			log.error("find all failed", re);

			throw re;
		}
		return NameList;
	}
	
	private String getFieldString(Person person,String str){
		String result = null;
		if (str.equalsIgnoreCase("CUSTOM1")){
			if (person.getCustom1()!=null) result = person.getCustom1().substring(0, person.getCustom1().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM2")){
			if (person.getCustom2()!=null) result = person.getCustom2().substring(0, person.getCustom2().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM3")){
			if (person.getCustom3()!=null) result = person.getCustom3().substring(0, person.getCustom3().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM4")){
			if (person.getCustom4()!=null) result = person.getCustom4().substring(0, person.getCustom4().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM5")){
			if (person.getCustom5()!=null) result = person.getCustom5().substring(0, person.getCustom5().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM6")){
			if (person.getCustom6()!=null) result = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM7")){
			if (person.getCustom7()!=null) result = person.getCustom7().substring(0, person.getCustom7().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM8")){
			if (person.getCustom8()!=null) result = person.getCustom8().substring(0, person.getCustom8().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM9")){
			if (person.getCustom9()!=null) result = person.getCustom9().substring(0, person.getCustom9().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM10")){
			if (person.getCustom10()!=null) result = person.getCustom10().substring(0, person.getCustom10().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM11")){
			if (person.getCustom11()!=null) result = person.getCustom11().substring(0, person.getCustom11().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM12")){
			if (person.getCustom12()!=null) result = person.getCustom12().substring(0, person.getCustom12().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM13")){
			if (person.getCustom13()!=null) result = person.getCustom13().substring(0, person.getCustom13().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM14")){
			if (person.getCustom14()!=null) result = person.getCustom14().substring(0, person.getCustom14().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM15")){
			if (person.getCustom15()!=null) result = person.getCustom15().substring(0, person.getCustom15().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM16")){
			if (person.getCustom16()!=null) result = person.getCustom16().substring(0, person.getCustom16().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM17")){
			if (person.getCustom17()!=null) result = person.getCustom17().substring(0, person.getCustom17().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM18")){
			if (person.getCustom18()!=null) result = person.getCustom18().substring(0, person.getCustom18().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM19")){
			if (person.getCustom19()!=null) result = person.getCustom19().substring(0, person.getCustom19().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM20")){
			if (person.getCustom20()!=null) result = person.getCustom20().substring(0, person.getCustom20().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM21")){
			if (person.getCustom21()!=null) result = person.getCustom21().substring(0, person.getCustom21().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM22")){
			if (person.getCustom22()!=null) result = person.getCustom22().substring(0, person.getCustom22().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM23")){
			if (person.getCustom23()!=null) result = person.getCustom23().substring(0, person.getCustom23().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM24")){
			if (person.getCustom24()!=null) result = person.getCustom24().substring(0, person.getCustom24().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM25")){
			if (person.getCustom25()!=null) result = person.getCustom25().substring(0, person.getCustom25().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM26")){
			if (person.getCustom26()!=null) result = person.getCustom26().substring(0, person.getCustom26().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM27")){
			if (person.getCustom27()!=null) result = person.getCustom27().substring(0, person.getCustom27().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM28")){
			if (person.getCustom28()!=null) result = person.getCustom28().substring(0, person.getCustom28().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM29")){
			if (person.getCustom29()!=null) result = person.getCustom29().substring(0, person.getCustom29().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM30")){
			if (person.getCustom30()!=null) result = person.getCustom30().substring(0, person.getCustom30().indexOf("^"));
		}
		return result;
	}
	private String getFieldZYString(Person person,String str){
		String result = null;
		if (str.equalsIgnoreCase("CUSTOM1")){
			if (person.getCustom1()!=null) result = person.getCustom1().substring(4, person.getCustom1().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM2")){
			if (person.getCustom2()!=null) result = person.getCustom2().substring(4, person.getCustom2().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM3")){
			if (person.getCustom3()!=null) result = person.getCustom3().substring(4, person.getCustom3().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM4")){
			if (person.getCustom4()!=null) result = person.getCustom4().substring(4, person.getCustom4().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM5")){
			if (person.getCustom5()!=null) result = person.getCustom5().substring(4, person.getCustom5().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM6")){
			if (person.getCustom6()!=null) result = person.getCustom6().substring(4, person.getCustom6().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM7")){
			if (person.getCustom7()!=null) result = person.getCustom7().substring(4, person.getCustom7().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM8")){
			if (person.getCustom8()!=null) result = person.getCustom8().substring(4, person.getCustom8().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM9")){
			if (person.getCustom9()!=null) result = person.getCustom9().substring(4, person.getCustom9().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM10")){
			if (person.getCustom10()!=null) result = person.getCustom10().substring(4, person.getCustom10().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM11")){
			if (person.getCustom11()!=null) result = person.getCustom11().substring(4, person.getCustom11().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM12")){
			if (person.getCustom12()!=null) result = person.getCustom12().substring(4, person.getCustom12().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM13")){
			if (person.getCustom13()!=null) result = person.getCustom13().substring(4, person.getCustom13().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM14")){
			if (person.getCustom14()!=null) result = person.getCustom14().substring(4, person.getCustom14().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM15")){
			if (person.getCustom15()!=null) result = person.getCustom15().substring(4, person.getCustom15().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM16")){
			if (person.getCustom16()!=null) result = person.getCustom16().substring(4, person.getCustom16().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM17")){
			if (person.getCustom17()!=null) result = person.getCustom17().substring(4, person.getCustom17().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM18")){
			if (person.getCustom18()!=null) result = person.getCustom18().substring(4, person.getCustom18().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM19")){
			if (person.getCustom19()!=null) result = person.getCustom19().substring(4, person.getCustom19().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM20")){
			if (person.getCustom20()!=null) result = person.getCustom20().substring(4, person.getCustom20().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM21")){
			if (person.getCustom21()!=null) result = person.getCustom21().substring(4, person.getCustom21().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM22")){
			if (person.getCustom22()!=null) result = person.getCustom22().substring(4, person.getCustom22().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM23")){
			if (person.getCustom23()!=null) result = person.getCustom23().substring(4, person.getCustom23().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM24")){
			if (person.getCustom24()!=null) result = person.getCustom24().substring(4, person.getCustom24().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM25")){
			if (person.getCustom25()!=null) result = person.getCustom25().substring(4, person.getCustom25().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM26")){
			if (person.getCustom26()!=null) result = person.getCustom26().substring(4, person.getCustom26().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM27")){
			if (person.getCustom27()!=null) result = person.getCustom27().substring(4, person.getCustom27().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM28")){
			if (person.getCustom28()!=null) result = person.getCustom28().substring(4, person.getCustom28().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM29")){
			if (person.getCustom29()!=null) result = person.getCustom29().substring(4, person.getCustom29().indexOf("^"));
		}
		if (str.equalsIgnoreCase("CUSTOM30")){
			if (person.getCustom30()!=null) result = person.getCustom30().substring(4, person.getCustom30().indexOf("^"));
		}
		return result;
	}
	
	public Boolean runProcedureForNameReg(Person person, String str,List<String> myname, int ControlID) throws ApplicationException,SQLException 
	{

		String Sql = "insert into reg_error_event (REG_ERROR_ID,"
												+ "BASE_ID,"
												+ "BASE_DOMAIN,"
												+ "BASE_NAME,"
												+ "REG_ID,"
												+ "REG_DOMAIN,"
												+ "REG_NAME,"
												+ "ERROR_TYPE,"
												+ "ERROR_DESCRIPTION,"
												+ "ERROR_DATE) values ("
												+ "reg_error_seq.nextval,"
												+ "?,?,?,?,?,?,?,?,sysdate)";
		String myFieldName = getFieldString(person,str);
		
		if(myFieldName==null){
			return false;
		}

		DBUtil util = new DBUtil();
		
		Connection conn = util.openConnection();

		try {

			PreparedStatement Pstmt = conn.prepareStatement(Sql);
			Pstmt.setString(1, myFieldName);
			Pstmt.setString(2, PropertyFacade.getString("check.field.base.domain"));
			
			if(myname.size()>1){
				Pstmt.setNull(3, OracleTypes.VARCHAR);
				Pstmt.setNull(4, OracleTypes.VARCHAR);
				Pstmt.setNull(5, OracleTypes.VARCHAR);
				Pstmt.setNull(6, OracleTypes.VARCHAR);
				Pstmt.setString(7, "Duplicated id");
				Pstmt.setString(8, "同ID同域ID");
			}else if(myname.size()==1){
				if(!person.getGivenName().equalsIgnoreCase(myname.get(0).toString()))
				{
					Pstmt.setString(3, myname.get(0).toString());
					Pstmt.setString(4, person.getCustom16());
					Pstmt.setString(5, person.getCustom11());
					Pstmt.setString(6, person.getGivenName());
					Pstmt.setString(7, "DIFF NAME");
					Pstmt.setString(8, "注册病人与对应对比病人的姓名不符");
				}else{
					return false;
				}
				
			}else{
				return false;
			}

			Pstmt.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			util.closeConn(conn);
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public String findRelevanceRecord(final String relevanceID,final String relevanceDomain)
	{
		return (String) getHibernateTemplate().execute(new HibernateCallback() 
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String QueryString = "select distinct name from person_for_relevance where patient_id = '" + relevanceID + "' and patient_domain = '" + relevanceDomain + "'";
				

				Query QueryObject=session.createSQLQuery(QueryString);
							
				List<String> list = QueryObject.list();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				
				if (list.size() == 0) 
				{
					return null;
				}

				return list.get(0).toString();
			}
		}
		);
	}

	public List<NewPerson> queryPersonByParam(final Person person)throws Exception {
		return (List<NewPerson>) getHibernateTemplate().execute(new HibernateCallback() {
	   public Object doInHibernate(Session session) throws HibernateException, SQLException {
		   String QueryString = "from NewPerson where 1=1 ";

		   String QueryCountHql = "select count(*) from PERSON where 1=1 ";

		   if (StringUtils.isNotBlank(person.getEmpi())){
			   QueryString=QueryString + " and EMPI=" + "'" + person.getEmpi()+ "'";
			   QueryCountHql=QueryCountHql + " and EMPI=" + "'" + person.getEmpi()+ "'";
		   }
		   if (StringUtils.isNotBlank(person.getIdentityNo())){
			   QueryString=QueryString+" and IDENTITY_NO=" + "'" + person.getIdentityNo()+ "'";
			   QueryCountHql=QueryCountHql+" and IDENTITY_NO=" + "'" + person.getIdentityNo()+ "'";
		   }
		   if (StringUtils.isNotBlank(person.getInsuranceNo())){
			   QueryString=QueryString+" and INSURANCE_NO=" + "'" + person.getInsuranceNo()+ "'";
			   QueryCountHql=QueryCountHql+" and INSURANCE_NO=" + "'" + person.getInsuranceNo()+ "'";
		   }
		   if (StringUtils.isNotBlank(person.getSsn())){
			   QueryString=QueryString+" and SSN=" + "'" + person.getSsn()+ "'";
			   QueryCountHql=QueryCountHql+" and SSN=" + "'" + person.getSsn()+ "'";
		   }
		   if (StringUtils.isNotBlank(person.getName())){
			   QueryString=QueryString+" and GIVEN_NAME=" + "'" + person.getName()+ "'";
			   QueryCountHql=QueryCountHql+" and GIVEN_NAME=" + "'" + person.getName()+ "'";
		   }

		   Query queryObject = session.createQuery(QueryString);
//		   if (person.getThisPageNo()!=null && person.getThisPageSize()!=null){
//			   queryObject.setFirstResult(person.getThisPageNo()*person.getThisPageSize()+1);
//			   queryObject.setMaxResults(person.getThisPageSize());
//		   }
		   List<NewPerson> list=queryObject.list();


		   //查询总数量
//		   Query queryCount = session.createSQLQuery(QueryCountHql);
//		   List<BigDecimal> countList = queryCount.list();
//		   if(CollectionUtils.isNotEmpty(list) && CollectionUtils.isNotEmpty(countList)){
//			   for (NewPerson newPerson:list){
//			   	newPerson.setSize(countList.get(0).intValue());
//			   }
//		   }


		   log.debug("Query by identifier returned: " + list.size() + " elements.");
		   if (list.size() == 0) {
			   return null;
		   }

		   return list;
	   }
   }
		);
	}



























}
