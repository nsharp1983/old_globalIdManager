package com.ats.aempi.stringcomparison.metrics;

import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity;

public class JaccardSimilarityMetricStringVersion extends AbstractDistanceMetric
{
	private JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
	
	public JaccardSimilarityMetricStringVersion() {
	}

	public double score(Object value1, Object value2) {
		if (missingValues(value1, value2)) {
			return handleMissingValues(value1, value2);
		}
		String string1 = value1.toString();
		String string2 = value2.toString();			
		double distance = jaccardSimilarity.getSimilarity(upperCase(string1), upperCase(string2));
		log.trace("Computed the distance between :" + string1 + ": and :" + string2 + ": to be " + distance);
		return distance;
	}
}
