package com.ats.aempi.stringcomparison.metrics;

import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

public class JaroWinklerDistanceMetric extends AbstractDistanceMetric
{
	private JaroWinkler jaroWinkler = new JaroWinkler();

	public JaroWinklerDistanceMetric() {
	}
	
	public double score(Object value1, Object value2) {
		if (missingValues(value1, value2)) {
			return handleMissingValues(value1, value2);
		}
		String string1 = value1.toString();
		String string2 = value2.toString();		
		double distance = jaroWinkler.getSimilarity(upperCase(string1), upperCase(string2));
		log.trace("Computed the distance between :" + string1 + ": and :" + string2 + ": to be " + distance);
		return distance;
	}

}
