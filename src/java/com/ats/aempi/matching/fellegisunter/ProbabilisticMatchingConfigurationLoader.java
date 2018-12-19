package com.ats.aempi.matching.fellegisunter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.InitializationException;
import com.ats.aempi.configuration.ComparatorFunction;
import com.ats.aempi.configuration.ConfigurationLoader;
import com.ats.aempi.configuration.ConfigurationRegistry;
import com.ats.aempi.configuration.MatchField;
import com.ats.aempi.configuration.xml.MatchingConfigurationType;
import com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType;
import com.ats.aempi.context.Context;

public class ProbabilisticMatchingConfigurationLoader implements ConfigurationLoader
{
	private Log log = LogFactory.getLog(ProbabilisticMatchingConfigurationLoader.class);
	
	public void loadAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationFragment) throws InitializationException {

		// This loader only knows how to process configuration information specifically
		// for the probabilistic matching service
		//
		if (!(configurationFragment instanceof ProbabilisticMatchingType)) {
			log.error("Custom configuration loader " + getClass().getName() + " is unable to process the configuration fragment " + configurationFragment);
			throw new InitializationException("Custom configuration loader is unable to load this configuration fragment.");
		}
		
		// Register the configuration information with the Configuration registry so that
		// it is available for the matching service to use when needed.
		//
		MatchConfiguration config = new MatchConfiguration();
		registry.registerConfigurationEntry(ProbabilisticMatchingConstants.PROBABILISTIC_MATCHING_FIELDS_REGISTRY_KEY, config);
		
		ProbabilisticMatchingType matchConfigXml = (ProbabilisticMatchingType) configurationFragment;
		log.debug("Received xml fragment to parse: " + matchConfigXml);
		config.setFalseNegativeProbability(matchConfigXml.getFalseNegativeProbability());
		config.setFalsePositiveProbability(matchConfigXml.getFalsePositiveProbability());
		for (int i=0; i < matchConfigXml.getMatchFields().sizeOfMatchFieldArray(); i++) {
			com.ats.aempi.configuration.xml.probabilisticmatching.MatchField field = matchConfigXml.getMatchFields().getMatchFieldArray(i);
			MatchField matchField = buildMatchFieldFromXml(field);
			config.addMatchField(matchField);
		}
		config.setConfigFileDirectory(matchConfigXml.getConfigFileDirectory());
	}

	private MatchField buildMatchFieldFromXml(com.ats.aempi.configuration.xml.probabilisticmatching.MatchField field) {
		MatchField matchField = new MatchField();
		matchField.setFieldName(field.getFieldName());
		matchField.setAgreementProbability(field.getAgreementProbability());
		matchField.setDisagreementProbability(field.getDisagreementProbability());
		matchField.setMatchThreshold(field.getMatchThreshold());
		matchField.setComparatorFunction(buildComparatorFunctionFromXml(field.getComparatorFunction()));
		return matchField;
	}

	private ComparatorFunction buildComparatorFunctionFromXml(com.ats.aempi.configuration.xml.ComparatorFunction comparatorFunction) {
		ComparatorFunction function = new ComparatorFunction();
		function.setFunctionName(comparatorFunction.getFunctionName());
		if (comparatorFunction.isSetParameters() && comparatorFunction.getParameters().sizeOfParameterArray() > 0) {
			for (com.ats.aempi.configuration.xml.Parameter parameter : comparatorFunction.getParameters().getParameterArray()) {
				log.debug("Adding parameter (" + parameter.getName() + "," + parameter.getValue() + ") to comparator function " + 
						function.getFunctionName());
				function.addParameter(parameter.getName(), parameter.getValue());
			}
		}
		return function;
	}

	public void saveAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationData)
			throws InitializationException {
		MatchConfiguration config = (MatchConfiguration) configurationData;
		MatchingConfigurationType xmlConfigurationFragment = buildMatchingConfigurationFragment(config);
		log.debug("Saving matching info xml configuration fragment: " + xmlConfigurationFragment);
		Context.getConfiguration().saveMatchingConfiguration(xmlConfigurationFragment);
		Context.getConfiguration().saveConfiguration();
		log.debug("Storing updated matching configuration in configuration registry: " + config);
		registry.registerConfigurationEntry(ProbabilisticMatchingConstants.PROBABILISTIC_MATCHING_FIELDS_REGISTRY_KEY, config);
	}

	private com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType buildMatchingConfigurationFragment(MatchConfiguration matchConfig) {
		com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType matchingConfigurationType =
			com.ats.aempi.configuration.xml.probabilisticmatching.ProbabilisticMatchingType.Factory.newInstance();
		matchingConfigurationType.setFalseNegativeProbability(matchConfig.getFalseNegativeProbability());
		matchingConfigurationType.setFalsePositiveProbability(matchConfig.getFalsePositiveProbability());
		com.ats.aempi.configuration.xml.probabilisticmatching.MatchFields matchFieldsXml =
			matchingConfigurationType.addNewMatchFields();
		for (MatchField matchField : matchConfig.getMatchFields()) {
			com.ats.aempi.configuration.xml.probabilisticmatching.MatchField matchFieldXml =
			matchFieldsXml.addNewMatchField();
			matchFieldXml.setFieldName(matchField.getFieldName());
			matchFieldXml.setAgreementProbability(matchField.getAgreementProbability());
			matchFieldXml.setDisagreementProbability(matchField.getDisagreementProbability());
			matchFieldXml.setComparatorFunction(buildComparatorFunctionFragment(matchField.getComparatorFunction()));
			matchFieldXml.setMatchThreshold(matchField.getMatchThreshold());
		}
		matchingConfigurationType.setConfigFileDirectory(matchConfig.getConfigFileDirectory());
		return matchingConfigurationType;
	}

	private com.ats.aempi.configuration.xml.ComparatorFunction buildComparatorFunctionFragment(ComparatorFunction comparatorFunction) {
		com.ats.aempi.configuration.xml.ComparatorFunction function =
			com.ats.aempi.configuration.xml.ComparatorFunction.Factory.newInstance();
		function.setFunctionName(comparatorFunction.getFunctionName());
		if (comparatorFunction.getParameterMap().size() == 0) {
			return function;
		}

		com.ats.aempi.configuration.xml.Parameters parameters = 
			com.ats.aempi.configuration.xml.Parameters.Factory.newInstance();
		for (String parameterName : comparatorFunction.getParameterMap().keySet()) {
			com.ats.aempi.configuration.xml.Parameter parameter = parameters.addNewParameter();
			parameter.setName(parameterName);
			parameter.setValue(comparatorFunction.getParameterMap().get(parameterName));
		}
		return function;
	}
}
