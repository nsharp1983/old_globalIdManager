package com.ats.aempi.stringcomparison.metrics;

import uk.ac.shef.wit.simmetrics.similaritymetrics.DiceSimilarity;

public class DiceSimilarityMetricStringVersion extends AbstractDistanceMetric
{
	private DiceSimilarity diceSimilarity = new DiceSimilarity();
	
	public DiceSimilarityMetricStringVersion() {
	}

	public double score(Object value1, Object value2) {
		if (missingValues(value1, value2)) {
			return handleMissingValues(value1, value2);
		}
		String string1 = value1.toString();
		String string2 = value2.toString();			
		double distance = diceSimilarity.getSimilarity(upperCase(string1), upperCase(string2));
		log.trace("Computed the distance between :" + string1 + ": and :" + string2 + ": to be " + distance);
		return distance;
	}
}
