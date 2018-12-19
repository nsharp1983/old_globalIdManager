package com.ats.aempi.loader.configuration;

import com.ats.aempi.configuration.ConfigurationRegistry;
import com.ats.aempi.context.Context;
import com.ats.aempi.service.impl.BaseServiceImpl;

public class FileLoaderConfigurationServiceImpl extends BaseServiceImpl implements FileLoaderConfigurationService
{
	public void init() {
		log.trace("Initializing the FileLoaderConfiguration Service");
	}

	public LoaderConfig getConfiguration() {
		return (LoaderConfig)Context.getConfiguration().lookupConfigurationEntry(ConfigurationRegistry.FILE_LOADER_CONFIGURATION);
	}
}
