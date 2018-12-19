package com.ats.aempi.stringcomparison.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ats.aempi.ValidationException;
import com.ats.aempi.service.impl.BaseServiceImpl;
import com.ats.aempi.stringcomparison.DistanceMetricType;
import com.ats.aempi.stringcomparison.StringComparisonService;
import com.ats.aempi.stringcomparison.metrics.DistanceMetric;

public class StringComparisonServiceImpl extends BaseServiceImpl implements StringComparisonService
{
	private HashMap<String,DistanceMetric> distanceMetricTypeMap;

	public double score(String metricType, Object value1, Object value2) {
		DistanceMetric distanceMetric = getDistanceMetric(metricType);
		return distanceMetric.score(value1, value2);
	}
	
	public double score(String metricType, Map<String,String> parameters, Object value1, Object value2) {
		DistanceMetric distanceMetric = getDistanceMetric(metricType);
		distanceMetric.setParameters(parameters);
		return distanceMetric.score(value1, value2);
	}

	// Factory method pattern
	private DistanceMetric getDistanceMetric(String metricTypeName) {
		DistanceMetric distanceMetric = distanceMetricTypeMap.get(metricTypeName);
		if (distanceMetric == null) {
			log.error("Unknown distance metric requested: " + metricTypeName);
			throw new ValidationException("Unknown distance metric requested for string comparision: " + distanceMetric);
		}
		return distanceMetric;
	}
	
	public DistanceMetricType getDistanceMetricType(String name) {
		DistanceMetric metric = distanceMetricTypeMap.get(name);
		if (metric == null) {
			return null;
		}
		return new DistanceMetricType(name, metric);
	}

	public DistanceMetricType[] getDistanceMetricTypes() {
		DistanceMetricType[] list = new DistanceMetricType[distanceMetricTypeMap.keySet().size()];
		int index=0;
		for (String key : distanceMetricTypeMap.keySet()) {
			list[index++] = new DistanceMetricType(key, distanceMetricTypeMap.get(key));
		}
		return list;
	}
	
	public List<String> getComparisonFunctionNames()
	{
		List<String> comparisonFunctionNames = new ArrayList<String>();
		for (String key : distanceMetricTypeMap.keySet()) {
			comparisonFunctionNames.add(key);
		}
		return comparisonFunctionNames;
	}

	public HashMap<String, DistanceMetric> getDistanceMetricTypeMap() {
		return distanceMetricTypeMap;
	}

	public void setDistanceMetricTypeMap(HashMap<String, DistanceMetric> distanceMetricTypeMap) {
		this.distanceMetricTypeMap = distanceMetricTypeMap;
	}
}
