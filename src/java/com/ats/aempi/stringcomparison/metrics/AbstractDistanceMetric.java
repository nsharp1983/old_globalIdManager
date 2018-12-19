package com.ats.aempi.stringcomparison.metrics;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractDistanceMetric implements DistanceMetric
{
	protected final Log log = LogFactory.getLog(getClass());
	
	private String name;
	private Map<String,Object> parameters;
	
	public AbstractDistanceMetric() {
		parameters = new HashMap<String,Object>();
	}
	
	public AbstractDistanceMetric(Map<String,Object> parameters) {
		this.parameters = parameters;
	}
	
	public AbstractDistanceMetric(String name) {
		this.name = name;
		parameters = new HashMap<String,Object>();
	}
	
	public AbstractDistanceMetric(String name, Map<String,Object> parameters) {
		this.name = name;
		this.parameters = parameters;
	}
	
	public String upperCase(String value) {
		if (value == null) {
			return null;
		}
		return value.toUpperCase();
	}
	
	public boolean missingValues(Object value1, Object value2) {
		if (value1 == null || value2 == null) {
			return true;
		}
		return false;
	}
	
	public double handleMissingValues(Object value1, Object value2) {
		double distance = 0;
		if (value1 == null && value2 == null) {
			distance = 1.0;
		}
		log.trace("Computed the distance between :" + value1 + ": and :" + value2 + ": to be " + distance);
		return distance;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParameter(String key, Object value) {
		this.parameters.put(key, value);
	}

	public Object getParameter(String key) {
		return this.parameters.get(key);
	}

	public Map<String,Object> getParameters() {
		return this.parameters;
	}
	
	public void setParameters(Map<String,String> parameters) {
		for (String parameterName : parameters.keySet()) {
			setParameter(parameterName, parameters.get(parameterName));
		}
	}
	
	public String toString() {
		return name;
	}
}
