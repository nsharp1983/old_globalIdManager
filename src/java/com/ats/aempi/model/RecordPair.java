package com.ats.aempi.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class RecordPair
{
	private Record leftRecord;
	private Record rightRecord;
	private Double weight;
	private ComparisonVector comparisonVector;
	
	public RecordPair(Record leftRecord, Record rightRecord) {
		this.leftRecord = leftRecord;
		this.rightRecord = rightRecord;
	}

	public Record getLeftRecord() {
		return leftRecord;
	}

	public void setLeftRecord(Record leftRecord) {
		this.leftRecord = leftRecord;
	}

	public Record getRightRecord() {
		return rightRecord;
	}

	public void setRightRecord(Record rightRecord) {
		this.rightRecord = rightRecord;
	}

	public Record getRecord(int index) {
		if (index == 0) {
			return leftRecord;
		}
		return rightRecord;
	}
	
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public ComparisonVector getComparisonVector() {
		return comparisonVector;
	}

	public void setComparisonVector(ComparisonVector comparisonVector) {
		this.comparisonVector = comparisonVector;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof RecordPair))
			return false;
		RecordPair castOther = (RecordPair) other;
		return new EqualsBuilder()
			.append(leftRecord.getRecordId(), castOther.leftRecord.getRecordId())
			.append(rightRecord.getRecordId(), castOther.rightRecord.getRecordId())
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(leftRecord.getRecordId())
			.append(rightRecord.getRecordId())
			.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("leftRecord", leftRecord).append("rightRecord", rightRecord).append("weight", weight).toString();
	}
	
	
}
