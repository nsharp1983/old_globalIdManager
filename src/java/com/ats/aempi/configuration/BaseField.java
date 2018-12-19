package com.ats.aempi.configuration;

import com.ats.aempi.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * BaseField encapsulates a base field that contains information about the person object
 * 
 * @author <a href="mailto:yimin.xie@sysnetint.com">Yimin Xie</a>
 */
public class BaseField extends BaseObject
{
	private static final long serialVersionUID = -4012644666481353904L;

	protected String fieldName;
	
	public BaseField() {
		
	}
	
	public BaseField(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BaseField))
			return false;
		BaseField castOther = (BaseField) other;
		return new EqualsBuilder().append(fieldName, castOther.fieldName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fieldName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fieldName", fieldName).toString();
	}

}
