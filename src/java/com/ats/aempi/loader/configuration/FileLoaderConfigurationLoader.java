package com.ats.aempi.loader.configuration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.InitializationException;
import com.ats.aempi.configuration.EMPIConfiguration;
import com.ats.aempi.configuration.ConfigurationLoader;
import com.ats.aempi.configuration.ConfigurationRegistry;
import com.ats.aempi.configuration.xml.fileloader.Composition;
import com.ats.aempi.configuration.xml.fileloader.DataField;
import com.ats.aempi.configuration.xml.fileloader.DataFields;
import com.ats.aempi.configuration.xml.fileloader.FileLoaderConfig;
import com.ats.aempi.configuration.xml.fileloader.FileLoaderType;
import com.ats.aempi.configuration.xml.fileloader.Substring;
import com.ats.aempi.configuration.xml.fileloader.Substrings;
import com.ats.aempi.context.Context;

/**
 * @author ctoth 
 * @version $Revision: $ $Date:  $
 */
public class FileLoaderConfigurationLoader implements ConfigurationLoader
{
	private Log log = LogFactory.getLog(EMPIConfiguration.class);
	
	public void loadAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationFragment) throws InitializationException {

		// This loader only knows how to process configuration information specifically
		// for the file loader configuration service
		//
		if (!(configurationFragment instanceof FileLoaderType)) {
			log.error("Custom configuration loader " + getClass().getName() + " is unable to process the configuration fragment " + configurationFragment);
			throw new InitializationException("Custom configuration loader is unable to load this configuration fragment.");
		}
		
		// Register the configuration information with the Configuration Registry so that
		// it is available for the file loader configuration service to use when needed.
		//
		LoaderConfig loaderConfiguration = new LoaderConfig();
		registry.registerConfigurationEntry(ConfigurationRegistry.FILE_LOADER_CONFIGURATION, 
				loaderConfiguration);
		
		FileLoaderType loaderConfig = (FileLoaderType) configurationFragment;
		log.debug("Received xml fragment to parse: " + loaderConfig);
		if (loaderConfig == null || loaderConfig.getFileLoaderConfig().getDataFields().sizeOfDataFieldArray() == 0) {
			log.warn("No data fields were configured; probably a configuration issue.");
			return;
		}
		
		loaderConfiguration.setHeaderLinePresent(loaderConfig.getFileLoaderConfig().getHeaderLinePresent());
		for (int i = 0; i < loaderConfig.getFileLoaderConfig().getDataFields().sizeOfDataFieldArray(); i++) {
			com.ats.aempi.configuration.xml.fileloader.DataField dataField =
				loaderConfig.getFileLoaderConfig().getDataFields().getDataFieldArray(i);
			LoaderDataField loaderDataField = new LoaderDataField();
			if (dataField.getTargetFieldName() != null)
				loaderDataField.setFieldName(dataField.getTargetFieldName());
			if (dataField.getFormat() != null)
				loaderDataField.setFormatString(dataField.getFormat());
			if (dataField.getFunction() != null)
				loaderDataField.setFunctionName(dataField.getFunction());
			if (dataField.getSubstrings() != null) {
				List<LoaderSubField> loaderSubFields = new ArrayList<LoaderSubField>();
				for (int j = 0; j < dataField.getSubstrings().getSubstringArray().length; j++) {
					com.ats.aempi.configuration.xml.fileloader.Substring substring =
						dataField.getSubstrings().getSubstringArray(j);
					LoaderSubField loaderSubField = new LoaderSubField();
					loaderSubField.setBeginIndex(substring.getBeginIndex());
					loaderSubField.setEndIndex(substring.getEndIndex());
					loaderSubField.setFieldName(substring.getTargetFieldName());
					loaderSubFields.add(loaderSubField);
				}
				loaderDataField.setSubFields(loaderSubFields);
			}
			if (dataField.getComposition() != null) {
				com.ats.aempi.configuration.xml.fileloader.Composition composition =
					dataField.getComposition();
				LoaderFieldComposition loaderFieldComposition = new LoaderFieldComposition();
				loaderFieldComposition.setIndex(composition.getIndex());
				loaderFieldComposition.setSeparator(composition.getSeparator());
				loaderFieldComposition.setValue(dataField.getTargetFieldName()); // redundant
			}
			loaderConfiguration.addDataField(loaderDataField);
		}
	}

	public void saveAndRegisterComponentConfiguration(ConfigurationRegistry registry, Object configurationData)
			throws InitializationException {
		LoaderConfig loaderConfiguration = (LoaderConfig) configurationData;
		FileLoaderType xmlConfigurationFragment = buildConfigurationFileFragment(loaderConfiguration);
		log.debug("Saving file loader configuration info xml configuration fragment: " + xmlConfigurationFragment);
		Context.getConfiguration().saveFileLoaderConfiguration(xmlConfigurationFragment);
		Context.getConfiguration().saveConfiguration();
		log.debug("Storing updated file loader configuration in configuration registry: " + loaderConfiguration);
		registry.registerConfigurationEntry(ConfigurationRegistry.FILE_LOADER_CONFIGURATION, loaderConfiguration);
	}

	private FileLoaderType buildConfigurationFileFragment(LoaderConfig loaderConfiguration) {
		FileLoaderType newFileLoaderConfig = FileLoaderType.Factory.newInstance();
		FileLoaderConfig fileLoaderConfigNode = newFileLoaderConfig.addNewFileLoaderConfig();
		fileLoaderConfigNode.setHeaderLinePresent(loaderConfiguration.getHeaderLinePresent());
		DataFields dataFieldsNode = fileLoaderConfigNode.addNewDataFields();
		for (LoaderDataField loaderDataField : loaderConfiguration.getDataFields()) {
			DataField dataFieldNode = dataFieldsNode.addNewDataField();
			if (loaderDataField.getFieldName() != null)
				dataFieldNode.setTargetFieldName(loaderDataField.getFieldName());
			if (loaderDataField.getFormatString() != null)
				dataFieldNode.setFormat(loaderDataField.getFormatString());
			if (loaderDataField.getFunctionName() != null)
				dataFieldNode.setFunction(loaderDataField.getFunctionName());
			if (loaderDataField.getSubFields() != null) {
				Substrings substrings = dataFieldNode.addNewSubstrings();
				for (LoaderSubField loaderSubField : loaderDataField.getSubFields()) {
					Substring substring = substrings.addNewSubstring();
					substring.setBeginIndex(loaderSubField.getBeginIndex());
					substring.setEndIndex(loaderSubField.getEndIndex());
					substring.setTargetFieldName(loaderSubField.getFieldName());
				}
			}
			if (loaderDataField.getFieldComposition() != null) {
				Composition composition = dataFieldNode.addNewComposition();
				composition.setIndex(loaderDataField.getFieldComposition().getIndex());
				composition.setSeparator(loaderDataField.getFieldComposition().getSeparator());
			}
		}
		return newFileLoaderConfig;
	}
}
