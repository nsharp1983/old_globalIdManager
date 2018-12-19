package com.ats.aempi.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.ats.aempi.util.ConvertUtil;

public class RecordTypeDef
{
	private List<String> fieldNames = new ArrayList<String>();
	
	public RecordTypeDef(Object object) {
		fieldNames = ConvertUtil.extractProperties(object);
	}
	
	public List<String> getFieldNames() {
		return fieldNames;
	}
	
	public void addFieldName(String fieldName) {
		fieldNames.add(fieldName);
	}
	
	public int fieldCount() {
		return fieldNames.size();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fieldNames", fieldNames).toString();
	}	
}
