package com.ats.aempi.stringcomparison;

import java.io.Serializable;

import com.ats.aempi.model.BaseObject;
import com.ats.aempi.stringcomparison.metrics.DistanceMetric;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class DistanceMetricType extends BaseObject implements Serializable
{
	private static final long serialVersionUID = 1850164680144032068L;

	private String name;
	private DistanceMetric distanceMetric;
	
	public DistanceMetricType(String name, DistanceMetric distanceMetric) {
		this.name = name;
		this.distanceMetric = distanceMetric;
	}

	public DistanceMetric getDistanceMetric() {
		return distanceMetric;
	}

	public void setDistanceMetric(DistanceMetric distanceMetric) {
		this.distanceMetric = distanceMetric;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof DistanceMetricType))
			return false;
		DistanceMetricType castOther = (DistanceMetricType) other;
		return new EqualsBuilder().append(name, castOther.name).append(
				distanceMetric, castOther.distanceMetric).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(distanceMetric)
				.toHashCode();
	}

	@Override
	public String toString() {
		return name;
	}
}
