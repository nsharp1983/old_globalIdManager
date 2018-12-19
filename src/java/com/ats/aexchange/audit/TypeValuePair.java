package com.ats.aexchange.audit;

/**
 * The pair of Type and Value of a Object detail.
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class TypeValuePair {
	private String type;
	private String value;
	
	public TypeValuePair(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
}
