package com.ats.aempi.loader.configuration;

import java.util.List;

import com.ats.aempi.configuration.BaseField;

public class LoaderDataField extends BaseField
{
	private static final long serialVersionUID = -8158684309625898508L;

	private String formatString;
	private String functionName;
	private LoaderFieldComposition fieldComposition;
	private List<LoaderSubField> subFields;

	public String getFormatString() {
		return formatString;
	}

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public LoaderFieldComposition getFieldComposition() {
		return fieldComposition;
	}

	public void setFieldComposition(LoaderFieldComposition fieldComposition) {
		this.fieldComposition = fieldComposition;
	}

	public List<LoaderSubField> getSubFields() {
		return subFields;
	}

	public void setSubFields(List<LoaderSubField> subFields) {
		this.subFields = subFields;
	}

}
