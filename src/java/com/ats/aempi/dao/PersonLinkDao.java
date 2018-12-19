package com.ats.aempi.dao;

import java.util.List;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonLink;
import com.ats.aempi.model.PersonLinkCancel;

public interface PersonLinkDao extends UniversalDao
{
	public void addPersonLink(PersonLink personLink);
	
	public void addPersonLinkCancel(PersonLinkCancel personLinkcancel);
	
	public List<PersonLink> getPersonLinks(Person person);
	
	public List<String> getPersonLinkRecord(String personid);
	
	public PersonLink getPersonLink(Person leftPerson, Person rightPerson);
	
	public PersonLinkCancel getPersonLinkCancel(String empi, Long personid) throws ApplicationException;
	
	public void RemovePersonLinkCancel(String empi,String personid) throws ApplicationException;
	
	public void removeAllLinks();
}
