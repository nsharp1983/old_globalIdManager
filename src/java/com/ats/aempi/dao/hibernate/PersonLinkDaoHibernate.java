package com.ats.aempi.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.dao.PersonLinkDao;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonLink;
import com.ats.aempi.model.PersonLinkCancel;

public class PersonLinkDaoHibernate extends UniversalDaoHibernate implements PersonLinkDao
{
	public void addPersonLinkCancel(PersonLinkCancel personLinkcancel)
	{
		log.debug("Storing a person link cancel.");
		getHibernateTemplate().saveOrUpdate(personLinkcancel);
		getHibernateTemplate().flush();
		log.debug("Finished saving the person link.");
	}
	public void addPersonLink(PersonLink personLink) {
		log.debug("Storing a person link.");
		getHibernateTemplate().saveOrUpdate(personLink);
		log.debug("Finished saving the person link.");
	}

	@SuppressWarnings("unchecked")
	public PersonLink getPersonLink(Person leftPerson, Person rightPerson) {
		Long[] personIds = {Long.valueOf(String.valueOf(leftPerson.getPersonId())), 
				Long.valueOf(Long.valueOf(rightPerson.getPersonId())), 
				Long.valueOf(String.valueOf(leftPerson.getPersonId())), 
				Long.valueOf(String.valueOf(rightPerson.getPersonId()))};
//		Integer[] personIds = {Integer.valueOf(String.valueOf(leftPerson.getPersonId())), 
//				Integer.valueOf(String.valueOf(rightPerson.getPersonId())), 
//						Integer.valueOf(String.valueOf(leftPerson.getPersonId())), 
//								Integer.valueOf(String.valueOf(rightPerson.getPersonId()))};
		//log.trace("Looking for links between person " + leftPerson + " and " + rightPerson);
		
		List<PersonLink> links = (List<PersonLink>) getHibernateTemplate().find("from PersonLink " +
				"where (personByLhPersonId.personId=? and personByRhPersonId.personId=?) or " +
				"(personByRhPersonId.personId=? and personByLhPersonId.personId=?) ",personIds);
        if (links.isEmpty()) {
        	//log.trace("No links found between person " + leftPerson + " and " + rightPerson);
            return null;
        } else {
        	//log.trace("Found links between person " + leftPerson + " and " + rightPerson);
            return (PersonLink) links.get(0);
        }
	}

	@SuppressWarnings("unchecked")
	public List<PersonLink> getPersonLinks(Person person) {
		//log.trace("Looking for links to this person " + person.getPersonId());
		List<PersonLink> links = (List<PersonLink>) getHibernateTemplate().find("from PersonLink " +
				"where personByLhPersonId.personId=? or personByRhPersonId.personId=?",
				new Long[] { person.getPersonId(), person.getPersonId() });
		//log.trace("Found " + links.size() + " links to person " + person.getPersonId());

		return links;
	}
	
	public List<String> getPersonLinkRecord(String personid) 
	{
		List<String> LinkList = new ArrayList<String>();
		
		try 
		{
			String queryString = "select distinct person_id from person_identifier where custom1 in (select custom1 from person_identifier where person_id = '" + personid + "')";
			
			Query queryObject = getSession().createSQLQuery(queryString);
			
			List list = queryObject.list();
			
			for(Object object : list) {
				
				BigDecimal entry = (BigDecimal)object;
				 
				 LinkList.add(entry.toString());
				} 
			
		} catch (RuntimeException re) 
		{
			log.error("寻找LINK关系失败", re);
			throw re;
		}
		return LinkList;
	}
	
	public PersonLinkCancel getPersonLinkCancel(String empi, Long personid) throws ApplicationException
	{
		PersonLinkCancel links = new PersonLinkCancel();
		
		links.setEmpi(empi);
		
		links.setPersonId(personid);
		
		List list =this.getHibernateTemplate().findByExample(links);
	
		if(list.size()==0)
		{
			return null;
		}
		
		return (PersonLinkCancel) list.get(0);
		
	}
	
	public void removeAllLinks() {
		log.trace("Removing all person links.");
		getHibernateTemplate().bulkUpdate("delete from PersonLink");
		log.trace("Removed all links from system.");
	}
	
	public void RemovePersonLinkCancel(String empi,String personid) throws ApplicationException
	{
		PersonLinkCancel links = new PersonLinkCancel();
		
		links.setEmpi(empi);
		
		links.setPersonId(Long.valueOf(personid));
		
		getHibernateTemplate().delete(links);
		
	}
	
	
}
