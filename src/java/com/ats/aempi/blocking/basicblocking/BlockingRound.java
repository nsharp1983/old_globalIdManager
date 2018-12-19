package com.ats.aempi.blocking.basicblocking;

import java.util.ArrayList;
import java.util.List;

import com.ats.aempi.configuration.BaseField;
import com.ats.aempi.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BlockingRound extends BaseObject
{
	private static final long serialVersionUID = 6836460519679990976L;

	private List<BaseField> fields;
	
	public BlockingRound() {
		fields = new ArrayList<BaseField>();
	}
	
	public void addField(BaseField field) {
		fields.add(field);
	}
	
	public List<BaseField> getFields() {
		return fields;
	}

	public void setFields(List<BaseField> fields) {
		this.fields = fields;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BlockingRound))
			return false;
		BlockingRound castOther = (BlockingRound) other;
		return new EqualsBuilder().append(fields, castOther.fields).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fields).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fields", fields).toString();
	}
}
