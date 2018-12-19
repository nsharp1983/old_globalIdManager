package com.ats.aempi.configuration;

public interface ConfigurationRegistry
{
	public final static String CUSTOM_FIELDS_MAP = "customFieldsMap";
	public final static String CUSTOM_FIELDS_LIST = "customFieldsList";
	public final static String MATCH_CONFIGURATION = "matchConfiguration";
	public final static String FILE_LOADER_CONFIGURATION = "fileLoaderConfiguration";
	
	/**
	 * Used to lookup a configuration entry using a key. Extension components may
	 * register a new configuration entry that specifically supports the configuration
	 * options needed by the component and then lookup this entry during runtime
	 * using this interface.
	 *  
	 * @param key Should uniquely identify the configuration entry
	 * @return
	 */
	public Object lookupConfigurationEntry(String key);
	
	/**
	 * Register a new configuration entry for a component and make it available for
	 * the other modules that make up the component during runtime.
	 *  
	 * @param key A string that uniquely identifies the configuration entry
	 * @param entry The actual configuration entry object itself.
	 */
	public void registerConfigurationEntry(String key, Object entry);
}
