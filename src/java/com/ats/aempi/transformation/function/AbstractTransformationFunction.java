package com.ats.aempi.transformation.function;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractTransformationFunction implements TransformationFunction
{
	protected final Log log = LogFactory.getLog(getClass());

	private Map<String, String> configuration;

	private String name;
	
	public AbstractTransformationFunction() {
	}
	
	public AbstractTransformationFunction(String name) {
		this.name = name;
	}
	
	public void init(Map<String, String> configParameters) {
		configuration = configParameters;
	}

	public abstract Object transform(Object field);

	public Map<String, String> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Map<String, String> configuration) {
		this.configuration = configuration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
