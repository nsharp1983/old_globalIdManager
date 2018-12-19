package com.ats.aempi.stringcomparison;

import java.util.List;

public interface StringComparisonService
{
	public DistanceMetricType[] getDistanceMetricTypes();
	
	public List<String> getComparisonFunctionNames();
	
	public DistanceMetricType getDistanceMetricType(String name);
	
	public double score(String metricType, Object value1, Object value2);
	
	public double score(String metricType, java.util.Map<String,String> parameters, Object value1, Object value2);
}
