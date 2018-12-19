package com.ats.aempi.loader.configuration;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

public class LoaderConfig
{
	private boolean headerLinePresent;
	private List<LoaderDataField> dataFields;

	public LoaderConfig() {
		dataFields = new ArrayList<LoaderDataField>();
	}

	public boolean getHeaderLinePresent() {
		return headerLinePresent;
	}

	public void setHeaderLinePresent(boolean headerLinePresent) {
		this.headerLinePresent = headerLinePresent;
	}

	public void addDataField(LoaderDataField dataField) {
		dataFields.add(dataField);
	}

	public List<LoaderDataField> getDataFields() {
		return dataFields;
	}

	public void setDataFields(List<LoaderDataField> dataFields) {
		this.dataFields = dataFields;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(
				"headerLinePresent", headerLinePresent).append("dataFields", dataFields).toString();
	}
}
