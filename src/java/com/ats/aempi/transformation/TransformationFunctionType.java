package com.ats.aempi.transformation;

import java.io.Serializable;

import com.ats.aempi.model.BaseObject;
import com.ats.aempi.transformation.function.TransformationFunction;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class TransformationFunctionType extends BaseObject implements Serializable
{
	private static final long serialVersionUID = -4578201112531798226L;

	private String name;
	private TransformationFunction transformationFunction;
	
	public TransformationFunctionType(String name, TransformationFunction transformationFunction) {
		this.name = name;
		this.transformationFunction = transformationFunction;
	}

	public TransformationFunction getTransformationFunction() {
		return transformationFunction;
	}

	public void setTransformationFunction(TransformationFunction transformationFunction) {
		this.transformationFunction = transformationFunction;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof TransformationFunctionType))
			return false;
		TransformationFunctionType castOther = (TransformationFunctionType) other;
		return new EqualsBuilder().append(name, castOther.name).append(
				transformationFunction, castOther.transformationFunction).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(transformationFunction)
				.toHashCode();
	}

	@Override
	public String toString() {
		return name;
	}
}
