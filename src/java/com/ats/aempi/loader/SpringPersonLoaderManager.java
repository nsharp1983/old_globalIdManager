package com.ats.aempi.loader;

import java.util.HashMap;

import ca.uhn.hl7v2.HL7Exception;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.context.Context;
import com.ats.aempi.model.Person;
import com.ats.aempi.service.PersonManagerService;
import com.ats.aempi.service.PersonQueryService;
import com.ats.apixpdq.common.PixPdqException;

public class SpringPersonLoaderManager implements PersonLoaderManager
{
	private String username;
	private String password;
	private PersonManagerService personManagerService;
	private PersonQueryService personQueryService;
	
	public void setupConnection(HashMap<String, Object> properties) {
		if (personManagerService == null) {
			personManagerService = Context.getPersonManagerService();
			personQueryService = Context.getPersonQueryService();
		}
	}

	public void shutdownConnection() {
	}

	public Person addPerson(Person person) throws ApplicationException,PixPdqException,HL7Exception {
		//return personManagerService.addPerson(person);
		//pm-2012-09-19-临时关闭
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PersonManagerService getPersonManagerService() {
		return personManagerService;
	}

	public void setPersonManagerService(PersonManagerService personManagerService) {
		this.personManagerService = personManagerService;
	}

	public PersonQueryService getPersonQueryService() {
		return personQueryService;
	}

	public void setPersonQueryService(PersonQueryService personQueryService) {
		this.personQueryService = personQueryService;
	}

}
