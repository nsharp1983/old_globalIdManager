package com.ats.aempi.loader;

import java.io.File;

import org.apache.log4j.Logger;
import com.ats.aempi.context.Context;
import com.ats.aempi.util.BaseSpringApp;

public class FileLoaderManager extends BaseSpringApp
{
	protected static Logger log = Logger.getLogger(FileLoaderManager.class);
	private PersonLoaderManager personLoaderMgr;
	
	public FileLoaderManager() {
		
	}
	
	public void setUp() {
		startup();
		personLoaderMgr = (PersonLoaderManager) Context.getApplicationContext().getBean("personLoaderManager");
		java.util.HashMap<String,Object> map = new java.util.HashMap<String,Object>();
		map.put("context", Context.getApplicationContext());
		personLoaderMgr.setupConnection(map);
	}
	
	public void loadFile(String filename, String loaderAlias) {
		 File file = new File(filename);
		 log.debug("Loading file " + file.getAbsolutePath());
		 if (!file.isFile() || !file.canRead()) {
			 log.error("Input file is not available.");
			 throw new RuntimeException("Input file " + filename + " is not readable.");
		 }
		 FileLoader loader = FileLoaderFactory.getFileLoader(Context.getApplicationContext(), loaderAlias);
		 loader.setPersonLoaderManager(personLoaderMgr);
		 loader.parseFile(file);
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
			System.exit(-1);
		}
		String filename = args[0];
		String loaderAlias = args[1];
		log.info("Loading the data file " + filename + " using loader " + loaderAlias);
		FileLoaderManager fileLoaderManager = new FileLoaderManager();
		fileLoaderManager.setUp();
		fileLoaderManager.loadFile(filename, loaderAlias);
	}
	
	public static void usage() {
		System.out.println("Usage: " + FileLoaderManager.class.getName() + " <file-to-load> <loader-alias>");
	}
}
