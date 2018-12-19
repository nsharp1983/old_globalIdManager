package com.ats.aempi.loader.configuration;

import com.ats.aempi.configuration.BaseField;

public class LoaderSubField extends BaseField
{
	private static final long serialVersionUID = 5869246262677801893L;

	private int beginIndex;
	private int endIndex;
	
	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
