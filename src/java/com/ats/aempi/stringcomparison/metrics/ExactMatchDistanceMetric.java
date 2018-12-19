package com.ats.aempi.stringcomparison.metrics;


public class ExactMatchDistanceMetric extends AbstractDistanceMetric
{
	public final static String TRUNCATION_LENGTH = "truncation-length";
	
	private Integer truncationLength;
	
	public ExactMatchDistanceMetric() {
	}

	public double score(Object value1, Object value2) {
		boolean match = false;
		if (value1 == null && value2 == null) {
			match = true;
		} else if (value1 == null || value2 == null) {
			match = false;
		} else {
			String string1 = value1.toString();
			String string2 = value2.toString();
			if (truncationLength != null) {
				if (string1.length() > truncationLength) {
					string1 = string1.substring(0,truncationLength.intValue());
				}
				if (string2.length() > truncationLength) {
					string2 = string2.substring(0,truncationLength.intValue());
				}
			}
			match = (string1.equalsIgnoreCase(string2)) ? true : false;
		}
		double distance = 0.0;
		if (match) {
			distance = 1.0;
		}
		log.trace("Computed the distance between :" + value1 + ": and :" + value2 + ": to be " + distance);
		return distance;
	}

	@Override
	public void setParameter(String key, Object value) {
		if (key == null || !key.equalsIgnoreCase(TRUNCATION_LENGTH)) {
			return;
		}
		if (value instanceof String) {
			try {
				truncationLength = Integer.parseInt((String) value);
				log.debug("Set the truncation length to " + truncationLength);
			} catch (NumberFormatException e) {	
				truncationLength = null;
			}
		}
	}
}
