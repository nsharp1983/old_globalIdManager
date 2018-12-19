package com.ats.apixpdq.web.beans;



public class ConfigBean{

	private String action;
	private String configFile;
	private String logfile;
	private String[] actors;

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the actors.
	 */
	public String[] getActors() {
		return actors;
	}

	/**
	 * @param actors The actors to set.
	 */
	public void setActors(String[] actors) {
		this.actors = actors;
	}

	/**
	 * @return Returns the configFile.
	 */
	public String getConfigFile() {
		return configFile;
	}

	/**
	 * @param configFile The configFile to set.
	 */
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	/**
	 * @return Returns the logfile.
	 */
	public String getLogfile() {
		return logfile;
	}

	/**
	 * @param logfile The logfile to set.
	 */
	public void setLogfile(String logfile) {
		this.logfile = logfile;
	}
}
