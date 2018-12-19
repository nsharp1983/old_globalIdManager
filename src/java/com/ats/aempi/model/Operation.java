package com.ats.aempi.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Operation implements Serializable
{
	private static final long serialVersionUID = -8735471015676733746L;

	public static Operation EQ = new Operation("Equals");
	public static Operation NE = new Operation("Not Equals");
	public static Operation LIKE = new Operation("Like");
	public static Operation ISNULL = new Operation("Is NULL");
	public static Operation ISNOTNULL = new Operation("Is Not NULL");
	
	private String name;
	
	private Operation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).toString();
	}	
}
