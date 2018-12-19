package com.ats.aempi.configuration;

import com.ats.aempi.InitializationException;

public interface ConfigurationLoader
{
	public void loadAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationFragment)
		throws InitializationException;
	
	public void saveAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationData)
		throws InitializationException;
}
