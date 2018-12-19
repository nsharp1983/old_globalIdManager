package com.ats.aempi.configuration;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CustomField encapsulates a custom field that contains generated information from another field
 * 
 * @author <a href="mailto:yimin.xie@sysnetint.com">Yimin Xie</a>
 */
public class CustomField extends BaseField
{
	private static final long serialVersionUID = 1224297603229522913L;

	private String sourceFieldName;
	private String transformationFunctionName;

	public String getSourceFieldName() {
		return sourceFieldName;
	}

	public void setSourceFieldName(String sourceFieldName) {
		this.sourceFieldName = sourceFieldName;
	}

	public String getTransformationFunctionName() {
		return transformationFunctionName;
	}

	public void setTransformationFunctionName(String transformationFunctionName) {
		this.transformationFunctionName = transformationFunctionName;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CustomField))
			return false;
		CustomField castOther = (CustomField) other;
		return new EqualsBuilder().append(fieldName, castOther.fieldName).append(sourceFieldName,
				castOther.sourceFieldName).append(transformationFunctionName, castOther.transformationFunctionName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fieldName).append(sourceFieldName).append(transformationFunctionName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(super.toString())
				.append("sourceFieldName", sourceFieldName)
				.append("transformationFunctionName", transformationFunctionName)
				.toString();
	}

}
