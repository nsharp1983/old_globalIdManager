package com.ats.aempi.model;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.ats.aempi.util.ConvertingWrapDynaBean;
import com.ats.aempi.util.DateUtilEMPI;

public class Record
{
	private Long recordId;
	private Object object;
	private ConvertingWrapDynaBean dynaBean;
	private RecordTypeDef recordTypeDefinition;
	
	public Record(Object object) {
		this.object = object;
		this.dynaBean = new ConvertingWrapDynaBean(object); 
	}
	
	public synchronized RecordTypeDef getRecordDef() {
		if (recordTypeDefinition == null) {
			recordTypeDefinition = new RecordTypeDef(object);
		}
		return recordTypeDefinition;
	}

	public String getAsString(String fieldName) {
		Object obj = dynaBean.get(fieldName);
		if (obj == null) {
			return null;
		}
		if (obj instanceof java.util.Date) {
			return DateUtilEMPI.getDate((java.util.Date) obj);
		}
		return obj.toString();
	}

	public Object get(String fieldName) {
		return dynaBean.get(fieldName);
	}
	
	public void set(String fieldName, Object value) {
		dynaBean.set(fieldName, value);
	}
	
	public Object getObject() {
		return dynaBean.getInstance();
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	
	public Set<String> getPropertyNames() {
		return dynaBean.getPropertyNames();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Record))
			return false;
		Record castOther = (Record) other;
		return new EqualsBuilder().append(recordId, castOther.recordId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(recordId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordId", recordId).append("dynaBean", dynaBean).append(
				"recordTypeDefinition", recordTypeDefinition).toString();
	}	
}
