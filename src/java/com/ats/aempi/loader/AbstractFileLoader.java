package com.ats.aempi.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ats.aempi.model.GenderDict;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.RaceDict;

public abstract class AbstractFileLoader implements FileLoader
{
	protected Logger log = Logger.getLogger(AbstractFileLoader.class);
	private PersonLoaderManager personManager;
	
	private Map<String,RaceDict> raceCacheByName = new HashMap<String,RaceDict>();
	private Map<String,GenderDict> genderCacheByCode = new HashMap<String,GenderDict>();
	private Map<String,GenderDict> genderCacheByName = new HashMap<String,GenderDict>();
	
	public void loadPerson(Person person) {
		log.debug("Attempting to load person entry " + person);
		try {
			personManager.addPerson(person);
		} catch (Exception e) {
			log.error("Failed while adding person entry to the system. Error: " + e, e);
			if (e.getCause() instanceof org.hibernate.exception.SQLGrammarException) {
				org.hibernate.exception.SQLGrammarException sge = (org.hibernate.exception.SQLGrammarException) e;
				log.error("Cause is: " + sge.getSQL());
			}
//			throw new RuntimeException("Failed while adding person entry to the system.");
		}
	}
	
	public RaceDict findRaceByName(String raceName) {
		//log.trace("Looking up race by race name: " + raceName);
		RaceDict race = raceCacheByName.get(raceName);
		if (race != null) {
			//log.trace("Looking up race by race name: " + raceName);
			return race;
		}
		race = personManager.getPersonQueryService().findRaceByName(raceName);
		if (race != null) {
			raceCacheByName.put(raceName, race);
		}
		return race;
	}
	
	public GenderDict findGenderByCode(String genderCode) {
		GenderDict gender = genderCacheByCode.get(genderCode);
		if (gender != null) {
			return gender;
		}
		gender = personManager.getPersonQueryService().findGenderByCode(genderCode);
		if (gender != null) {
			genderCacheByCode.put(genderCode, gender);
		}
		return gender;
	}

	public GenderDict findGenderByName(String genderName) {
		GenderDict gender = genderCacheByName.get(genderName);
		if (gender != null) {
			return gender;
		}
		gender = personManager.getPersonQueryService().findGenderByName(genderName);
		if (gender != null) {
			genderCacheByName.put(genderName, gender);
		}
		return gender;
	}

	public void parseFile(File file) {
		parseFile(file, true);
	}
	
	public void parseFile(File file, boolean populateCustomFields) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			log.error("Unable to read the input file. Error: " + e);
			throw new RuntimeException("Unable to read the input file.");
		}
		
		try {
			boolean done = false;
			int lineIndex=0;
			while (!done) {
				String line = reader.readLine();
				if (line == null) {
					done = true;
					continue;
				}
				Person person = processLine(line, lineIndex++, populateCustomFields);
				if (person == null) {
					continue;
				}
				loadPerson(person);
			}
		} catch (IOException e) {
			log.error("Failed while loading the input file. Error: " + e);
			throw new RuntimeException("Failed while loading the input file.");
		}
	}

	public void setPersonLoaderManager(PersonLoaderManager personManager) {
		this.personManager = personManager; 
	}
	
	protected abstract Person processLine(String line, int lineIndex);
	
	protected Person processLine(String line, int lineIndex, boolean populateCustomFields) {
		return processLine(line, lineIndex);
	}
	
}
