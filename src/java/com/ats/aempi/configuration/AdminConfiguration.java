package com.ats.aempi.configuration;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AdminConfiguration
{
	private String configFileDirectory;
	private boolean autoStartPIXPDQ;
	
	public AdminConfiguration() {
	}

	public boolean isAutoStartPIXPDQ() {
		return autoStartPIXPDQ;
	}

	public void setAutoStartPIXPDQ(boolean autoStartPIXPDQ) {
		this.autoStartPIXPDQ = autoStartPIXPDQ;
	}

	public String getConfigFileDirectory() {
		return configFileDirectory;
	}

	public void setConfigFileDirectory(String configFileDirectory) {
		this.configFileDirectory = configFileDirectory;
	}

	@Override
	public String toString() {
		return "AdminConfiguration [configFileDirectory=" + configFileDirectory + ", autoStartPIXPDQ="
				+ autoStartPIXPDQ + "]";
	}
}
