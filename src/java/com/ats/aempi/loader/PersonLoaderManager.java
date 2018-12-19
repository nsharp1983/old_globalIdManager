package com.ats.aempi.loader;

import ca.uhn.hl7v2.HL7Exception;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.model.Person;
import com.ats.aempi.service.PersonQueryService;
import com.ats.apixpdq.common.PixPdqException;

public interface PersonLoaderManager
{
	public void setupConnection(java.util.HashMap<String,Object> properties);
	
	public Person addPerson(Person person) throws ApplicationException, PixPdqException, HL7Exception;
	
	public PersonQueryService getPersonQueryService();
	
	public void shutdownConnection();
}
