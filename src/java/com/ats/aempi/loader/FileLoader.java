package com.ats.aempi.loader;

import java.io.File;

import com.ats.aempi.model.Person;


public interface FileLoader
{
	public void setPersonLoaderManager(PersonLoaderManager personManager);
	
	public void parseFile(File file);
	
	public void parseFile(File file, boolean populateCustomFields);
	
	public void loadPerson(Person person);
}
