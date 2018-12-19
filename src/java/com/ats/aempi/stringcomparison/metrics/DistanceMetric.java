package com.ats.aempi.stringcomparison.metrics;

public interface DistanceMetric
{
	public double score(Object string1, Object string2);
	
	public String getName();
	
	public void setName(String name);
	
	public void setParameters(java.util.Map<String, String> parameterMap);
	
	public void setParameter(String key, Object value);
	
	public Object getParameter(String key);
}
