package com.ats.aempi.blocking.basicblocking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.InitializationException;
import com.ats.aempi.configuration.BaseField;
import com.ats.aempi.configuration.ConfigurationLoader;
import com.ats.aempi.configuration.ConfigurationRegistry;
import com.ats.aempi.configuration.CustomField;
import com.ats.aempi.configuration.xml.basicblocking.BasicBlockingType;
import com.ats.aempi.configuration.xml.basicblocking.BlockingField;
import com.ats.aempi.configuration.xml.basicblocking.BlockingFields;
import com.ats.aempi.configuration.xml.basicblocking.BlockingRounds;
import com.ats.aempi.context.Context;

/**

 * @author Odysseas Pentakalos 
 * @version $Revision: $ $Date:  $
 */
public class BasicBlockingConfigurationLoader implements ConfigurationLoader
{
	private Log log = LogFactory.getLog(BasicBlockingConfigurationLoader.class);
	
	@SuppressWarnings("unchecked")
	public void loadAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationFragment) throws InitializationException {

		// This loader only knows how to process configuration information specifically
		// for the basic blocking service
		//
		if (!(configurationFragment instanceof BasicBlockingType)) {
			log.error("Custom configuration loader " + getClass().getName() + " is unable to process the configuration fragment " + configurationFragment);
			throw new InitializationException("Custom configuration loader is unable to load this configuration fragment.");
		}
		
		// Register the configuration information with the Configuration Registry so that
		// it is available for the blocking service to use when needed.
		//
		ArrayList<BlockingRound> blockingRounds = new ArrayList<BlockingRound>();
		registry.registerConfigurationEntry(BasicBlockingConstants.BLOCKING_ROUNDS_REGISTRY_KEY, 
				blockingRounds);
		
		BasicBlockingType blockingConfig = (BasicBlockingType) configurationFragment;
		log.debug("Received xml fragment to parse: " + blockingConfig);
		if (blockingConfig == null || blockingConfig.getBlockingRounds().sizeOfBlockingRoundArray() == 0) {
			log.warn("No blocking rounds were configured; probably a configuration issue.");
			return;
		}
		
		for (int i=0; i < blockingConfig.getBlockingRounds().sizeOfBlockingRoundArray(); i++) {
			com.ats.aempi.configuration.xml.basicblocking.BlockingRound round = blockingConfig.getBlockingRounds().getBlockingRoundArray(i);
			BlockingRound blockingRound = new BlockingRound();
			Map<String, CustomField> customFieldsByName = (Map<String, CustomField>)
				registry.lookupConfigurationEntry(ConfigurationRegistry.CUSTOM_FIELDS_MAP);
			for (int j=0; j < round.getBlockingFields().sizeOfBlockingFieldArray(); j++) {
				com.ats.aempi.configuration.xml.basicblocking.BlockingField field = round.getBlockingFields().getBlockingFieldArray(j);
				log.trace("Looking for blocking field named " + field.getFieldName());
				CustomField custom = customFieldsByName.get(field.getFieldName());
				if (custom == null) {
					blockingRound.addField(new BaseField(field.getFieldName()));
				} else {
					blockingRound.addField(custom);
				}
			}
			blockingRounds.add(blockingRound);
		}
	}

	@SuppressWarnings("unchecked")
	public void saveAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationData)
			throws InitializationException {
		List<BlockingRound> rounds = (List<BlockingRound>) configurationData;
		BasicBlockingType xmlConfigurationFragment = buildConfigurationFileFragment(rounds);
		log.debug("Saving blocking info xml configuration fragment: " + xmlConfigurationFragment);
		Context.getConfiguration().saveBlockingConfiguration(xmlConfigurationFragment);
		Context.getConfiguration().saveConfiguration();
		log.debug("Storing updated blocking configuration in configuration registry: " + rounds);
		registry.registerConfigurationEntry(BasicBlockingConstants.BLOCKING_ROUNDS_REGISTRY_KEY, rounds);
	}

	private BasicBlockingType buildConfigurationFileFragment(List<BlockingRound> rounds) {
		BasicBlockingType newBasicBlocking = BasicBlockingType.Factory.newInstance();
		BlockingRounds roundsNode = newBasicBlocking.addNewBlockingRounds();
		for (BlockingRound blockingRound : rounds) {
			com.ats.aempi.configuration.xml.basicblocking.BlockingRound roundNode = roundsNode.addNewBlockingRound();
			BlockingFields blockingFields = roundNode.addNewBlockingFields();
			for (com.ats.aempi.configuration.BaseField field : blockingRound.getFields()) {
				BlockingField xmlField = blockingFields.addNewBlockingField();
				xmlField.setFieldName(field.getFieldName());
			}
		}
		return newBasicBlocking;
	}
}
