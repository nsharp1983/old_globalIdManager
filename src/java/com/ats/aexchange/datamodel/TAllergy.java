package com.ats.aexchange.datamodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ats.aempi.model.RelationshipTypeDict;
import com.ats.aexchange.datamodel.SharedEnums.PhoneType;
import com.ats.aexchange.datamodel.SharedEnums.SexType;

/**
 * This class represents a patient throughout the PIX/PDQ 
 * sever.  It is used for passing patient information between
 * PIX/PDQ clients actors (such as PIX Source, PIX Consumer and 
 * PDQ Consumer) and underneath eMPI system.
 * 
 * @author Wenzhi Li
 * @version 1.0  Nov 11, 2008
 *
 */
public class TAllergy {
	
	private String allergyCd;
	private String allergyText;
	private String allergyCode;
	private String allergyType;
	private String allergyClass;
	private Date allergyDate;

    
	/**
	 * Default Constructor
	 */
	public TAllergy() {
	}


	/**
	 * @return the allergyCd
	 */
	public String getAllergyCd() {
		return allergyCd;
	}


	/**
	 * @param allergyCd the allergyCd to set
	 */
	public void setAllergyCd(String allergyCd) {
		this.allergyCd = allergyCd;
	}


	/**
	 * @return the allergyText
	 */
	public String getAllergyText() {
		return allergyText;
	}


	/**
	 * @param allergyText the allergyText to set
	 */
	public void setAllergyText(String allergyText) {
		this.allergyText = allergyText;
	}


	/**
	 * @return the allergyCode
	 */
	public String getAllergyCode() {
		return allergyCode;
	}


	/**
	 * @param allergyCode the allergyCode to set
	 */
	public void setAllergyCode(String allergyCode) {
		this.allergyCode = allergyCode;
	}


	/**
	 * @return the allergyType
	 */
	public String getAllergyType() {
		return allergyType;
	}


	/**
	 * @param allergyType the allergyType to set
	 */
	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}


	/**
	 * @return the allergyClass
	 */
	public String getAllergyClass() {
		return allergyClass;
	}


	/**
	 * @param allergyClass the allergyClass to set
	 */
	public void setAllergyClass(String allergyClass) {
		this.allergyClass = allergyClass;
	}


	/**
	 * @return the allergyDate
	 */
	public Date getAllergyDate() {
		return allergyDate;
	}


	/**
	 * @param date the allergyDate to set
	 */
	public void setAllergyDate(Date allergyDate) {
		this.allergyDate = allergyDate;
	}


	

}
