package com.ats.aexchange.datamodel;

/**
 * This class represents a patient identifier 
 * 
 * @author Wenzhi Li
 * @version 1.0, Nov 14, 2008
 * @see PersonIdentifier
 */
public class PatientIdentifier extends PersonIdentifier implements java.io.Serializable
{
	
	private static final long serialVersionUID =0L;
	
	public PatientIdentifier(){		
		super();
	}
	public PatientIdentifier(String id, Identifier assigningAuthority) {
		super(id, assigningAuthority);
	}
}
