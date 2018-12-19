package com.ats.aempi.matching.exactmatching;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.InitializationException;
import com.ats.aempi.configuration.ConfigurationLoader;
import com.ats.aempi.configuration.ConfigurationRegistry;
import com.ats.aempi.configuration.MatchField;
import com.ats.aempi.configuration.xml.MatchingConfigurationType;
import com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType;
import com.ats.aempi.context.Context;

//Hansen
//20160603
//在最新版本的程序中，是没有用到ExactMatching的配置的
public class ExactMatchingConfigurationLoader implements ConfigurationLoader
{
	private Log log = LogFactory.getLog(ExactMatchingConfigurationLoader.class);
	
	public void loadAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationFragment) throws InitializationException {

		// This loader only knows how to process configuration information specifically
		// for the exact matching service
		//
		if (!(configurationFragment instanceof ExactMatchingType)) {
			log.error("Custom configuration loader " + getClass().getName() + " is unable to process the configuration fragment " + configurationFragment);
			throw new InitializationException("Custom configuration loader is unable to load this configuration fragment.");
		}
		
		// Register the configuration information with the Configuration Registry so that
		// it is available for the matching service to use when needed.
		//
		ArrayList<MatchField> matchFields = new ArrayList<MatchField>();
		registry.registerConfigurationEntry(ExactMatchingConstants.EXACT_MATCHING_FIELDS_REGISTRY_KEY, 
				matchFields);
		
		ExactMatchingType matchingConfig = (ExactMatchingType) configurationFragment;
		log.debug("Received xml fragment to parse: " + matchingConfig);
		if (matchingConfig == null || matchingConfig.getMatchFields().sizeOfMatchFieldArray() == 0) {
			log.info("No matching fields were configured; probably a configuration issue." + " 多数情况下请忽略此信息。");
			return;
		}
		
		for (int i=0; i < matchingConfig.getMatchFields().sizeOfMatchFieldArray(); i++) {
			com.ats.aempi.configuration.xml.exactmatching.MatchField matchField = matchingConfig.getMatchFields().getMatchFieldArray(i);
			matchFields.add(buildMatchFieldFromXml(matchField));
		}
	}

	@SuppressWarnings("unchecked")
	public void saveAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationData)
			throws InitializationException {
		List<MatchField> fields = (List<MatchField>) configurationData;
		MatchingConfigurationType xmlConfigurationFragment = buildMatchingConfigurationFragment(fields);
		log.debug("Saving matching info xml configuration fragment: " + xmlConfigurationFragment);
		Context.getConfiguration().saveMatchingConfiguration(xmlConfigurationFragment);
		Context.getConfiguration().saveConfiguration();
		log.debug("Storing updated matching configuration in configuration registry: " + fields);
		registry.registerConfigurationEntry(ExactMatchingConstants.EXACT_MATCHING_FIELDS_REGISTRY_KEY, fields);
	}
	
	private MatchField buildMatchFieldFromXml(com.ats.aempi.configuration.xml.exactmatching.MatchField field) {
		MatchField matchField = new MatchField();
		matchField.setFieldName(field.getFieldName());
		return matchField;
	}
	
	private com.ats.aempi.configuration.xml.MatchingConfigurationType buildMatchingConfigurationFragment(List<MatchField> fields) {
		com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType matchingType =
			com.ats.aempi.configuration.xml.exactmatching.ExactMatchingType.Factory.newInstance();
		com.ats.aempi.configuration.xml.exactmatching.MatchFields matchFieldsXml = matchingType.addNewMatchFields();
		for (MatchField matchField : fields) {
			com.ats.aempi.configuration.xml.exactmatching.MatchField matchFieldXml = matchFieldsXml.addNewMatchField();
			matchFieldXml.setFieldName(matchField.getFieldName());
		}
		return matchingType;
	}	
}
