package com.ats.aempi.matching.fellegisunter;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FellegiSunterParameters implements Serializable
{
	private static final long serialVersionUID = 7534673028743277151L;
	
	int fieldCount;
	private int[] vectorFrequencies;
	private int vectorCount;
	private double[] mValues;
	private double[] uValues;
	private double lowerBound;
	private double upperBound;
	private double lambda; 					// Type I (false negative) error
	private double mu; 						// Type II (false positive) error
	
	public FellegiSunterParameters(int vectorCount, int[] vectorFrequencies, double[] mValues, double[] uValues, double lambda, double mu) {
		this.vectorCount = vectorCount;
		this.vectorFrequencies = vectorFrequencies;
		this.mValues = mValues;
		this.uValues = uValues;
		this.lambda = lambda;
		this.mu = mu;
	}
	
	public FellegiSunterParameters() {
	}
	
	public FellegiSunterParameters(int fieldCount) {
		this.fieldCount = fieldCount;
		mValues = new double[fieldCount];
		uValues = new double[fieldCount];
		vectorCount = (int)Math.pow(2,fieldCount);
		vectorFrequencies = new int[vectorCount];
	}

	public int getFieldCount() {
		return fieldCount;
	}
	
	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}
	
	public double getMValue(int index) {
		checkIndexBounds(index, fieldCount);
		return mValues[index];
	}
	
	public void setMValue(int index, double values) {
		checkIndexBounds(index, fieldCount);
		mValues[index] = values;
	}

	public double getUValue(int index) {
		checkIndexBounds(index, fieldCount);
		return uValues[index];
	}
	
	public void setUValue(int index, double values) {
		checkIndexBounds(index, fieldCount);
		uValues[index] = values;
	}

	public void incrementVectorFrequency(int index) {
		checkIndexBounds(index, vectorCount);
		vectorFrequencies[index]++;
	}
	
	public int getVectorFrequency(int index) {
		checkIndexBounds(index, vectorCount);
		return vectorFrequencies[index];
	}
	
	public void setVectorFrequency(int index, int frequency) {
		checkIndexBounds(index, vectorCount);
		vectorFrequencies[index] = frequency;
	}

	private void checkIndexBounds(int index, int bound) {
		if (index < 0 || index > bound-1) {
			throw new IndexOutOfBoundsException("Index value is out of valid range between 0 and " + bound);
		}
	}
	
	public double[] getMValues() {
		return mValues;
	}
	
	public void setMValues(double[] values) {
		mValues = values;
	}
	
	public double[] getUValues() {
		return uValues;
	}
	
	public void setUValues(double[] values) {
		uValues = values;
	}
	
	public double getLowerBound() {
		return lowerBound;
	}
	
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	public double getUpperBound() {
		return upperBound;
	}
	
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public int[] getVectorFrequencies() {
		return vectorFrequencies;
	}

	public void setVectorFrequencies(int[] vectorFrequencies) {
		this.vectorFrequencies = vectorFrequencies;
	}

	public int getVectorCount() {
		return vectorCount;
	}

	public void setVectorCount(int vectorCount) {
		this.vectorCount = vectorCount;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public double getMu() {
		return mu;
	}

	public void setMu(double mu) {
		this.mu = mu;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fieldCount", fieldCount)
				.append("vectorFrequencies", vectorFrequencies).append("vectorCount", vectorCount).append("mValues",
						mValues).append("uValues", uValues).append("lowerBound", lowerBound).append("upperBound",
						upperBound).append("lambda", lambda).append("mu", mu).toString();
	}
}
