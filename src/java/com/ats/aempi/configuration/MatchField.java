package com.ats.aempi.configuration;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class MatchField extends BaseField
{
	private static final long serialVersionUID = -4012644666481353904L;

	private float agreementProbability;
	private float disagreementProbability;
	private ComparatorFunction comparatorFunction;
	private float matchThreshold;
	
	public float getAgreementProbability() {
		return agreementProbability;
	}

	public void setAgreementProbability(float agreementProbability) {
		this.agreementProbability = agreementProbability;
	}

	public float getDisagreementProbability() {
		return disagreementProbability;
	}

	public void setDisagreementProbability(float disagreementProbability) {
		this.disagreementProbability = disagreementProbability;
	}

	public ComparatorFunction getComparatorFunction() {
		return comparatorFunction;
	}

	public void setComparatorFunction(ComparatorFunction comparatorFunction) {
		this.comparatorFunction = comparatorFunction;
	}

	public float getMatchThreshold() {
		return matchThreshold;
	}

	public void setMatchThreshold(float matchThreshold) {
		this.matchThreshold = matchThreshold;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof MatchField))
			return false;
		MatchField castOther = (MatchField) other;
		return new EqualsBuilder()
				.append(fieldName, castOther.fieldName)
				.append(agreementProbability, castOther.agreementProbability)
				.append(disagreementProbability, castOther.disagreementProbability)
				.append(comparatorFunction, castOther.comparatorFunction)
				.append(matchThreshold, castOther.matchThreshold)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(fieldName)
				.append(agreementProbability)
				.append(disagreementProbability)
				.append(comparatorFunction)
				.append(matchThreshold)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(super.toString())
				.append("agreementProbability", agreementProbability)
				.append("disagreementProbability", disagreementProbability)
				.append("comparatorFunctionName", comparatorFunction)
				.append("matchThreshold", matchThreshold)
				.toString();
	}

}
