package com.ats.aempi.configuration;

import java.util.HashMap;
import java.util.Map;

import com.ats.aempi.model.BaseObject;

public class ComparatorFunction extends BaseObject
{
	private static final long serialVersionUID = -6366593766183996854L;

	private String functionName;
	private Map<String,String> parameterMap;
	
	public ComparatorFunction() {
		parameterMap = new HashMap<String,String>();
	}
	
	public ComparatorFunction(String functionName) {
		this.functionName = functionName;
		parameterMap = new HashMap<String,String>();
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public void addParameter(String parameterName, String parameterValue) {
		if (parameterName == null || parameterName.length() == 0 ||
				parameterValue == null || parameterValue.length() == 0) {
			return;
		}
		parameterMap.put(parameterName, parameterValue);
	}
	
	public String getParameter(String parameterName) {
		return parameterMap.get(parameterName);
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	@Override
	public String toString() {
		return "ComparatorFunction [functionName=" + functionName + ", parameterMap=" + parameterMap + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComparatorFunction other = (ComparatorFunction) obj;
		if (functionName == null) {
			if (other.functionName != null)
				return false;
		} else if (!functionName.equals(other.functionName))
			return false;
		if (parameterMap == null) {
			if (other.parameterMap != null)
				return false;
		} else if (!parameterMap.equals(other.parameterMap))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((functionName == null) ? 0 : functionName.hashCode());
		result = prime * result + ((parameterMap == null) ? 0 : parameterMap.hashCode());
		return result;
	}
}
