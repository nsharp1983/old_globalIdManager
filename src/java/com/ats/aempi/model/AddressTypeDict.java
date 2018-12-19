package com.ats.aempi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * AddressTypeDict entity. 
 * @author yrh-2012-03-06
 */

@SuppressWarnings("unused")
@Entity
@Table(name = "address_type_dict", schema = "ATS_DICT")
public class AddressTypeDict extends BaseObject implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 7714813422902045544L;
	
	private Integer addressTypeCd;
	private String addressTypeName;
	private String addressTypeDescription;
	private String addressTypeCode;
	private String createName;
	private Date createDate;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;

	// Constructors

	/** default constructor */
	public AddressTypeDict() {
	}

	/** minimal constructor */
	public AddressTypeDict(Integer addressTypeCd, String addressTypeName,
			String addressTypeCode) {
		this.addressTypeCd = addressTypeCd;
		this.addressTypeName = addressTypeName;
		this.addressTypeCode = addressTypeCode;
	}

	/** full constructor */
	public AddressTypeDict(Integer addressTypeCd, String addressTypeName,
			String addressTypeDescription, String addressTypeCode,
			String createName, Date createDate, String comments,
			String custom1, String custom2, String custom3, String custom4,
			String custom5){
		this.addressTypeCd = addressTypeCd;
		this.addressTypeName = addressTypeName;
		this.addressTypeDescription = addressTypeDescription;
		this.addressTypeCode = addressTypeCode;
		this.createName = createName;
		this.createDate = createDate;
		this.comments = comments;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		
	}

	// Property accessors
	@Id
	@Column(name = "address_type_cd", unique = true, nullable = false)
	public Integer getAddressTypeCd() {
		return this.addressTypeCd;
	}

	public void setAddressTypeCd(Integer addressTypeCd) {
		this.addressTypeCd = addressTypeCd;
	}

	@Column(name = "address_type_name", nullable = false, length = 64)
	public String getAddressTypeName() {
		return this.addressTypeName;
	}

	public void setAddressTypeName(String addressTypeName) {
		this.addressTypeName = addressTypeName;
	}

	@Column(name = "address_type_description")
	public String getAddressTypeDescription() {
		return this.addressTypeDescription;
	}

	public void setAddressTypeDescription(String addressTypeDescription) {
		this.addressTypeDescription = addressTypeDescription;
	}

	@Column(name = "address_type_code", nullable = false, length = 64)
	public String getAddressTypeCode() {
		return this.addressTypeCode;
	}

	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	@Column(name = "create_name", length = 64)
	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "create_date", length = 8)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "custom1")
	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	@Column(name = "custom2")
	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	@Column(name = "custom3")
	public String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Column(name = "custom4")
	public String getCustom4() {
		return this.custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	@Column(name = "custom5")
	public String getCustom5() {
		return this.custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}


	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AddressTypeDict))
			return false;
		AddressTypeDict castOther = (AddressTypeDict) other;
		return new EqualsBuilder().append(addressTypeCd, castOther.addressTypeCd).append(addressTypeName,
				castOther.addressTypeName).append(addressTypeDescription, castOther.addressTypeDescription).append(
				addressTypeCode, castOther.addressTypeCode).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(addressTypeCd).append(addressTypeName).append(addressTypeDescription)
				.append(addressTypeCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("addressTypeCd", addressTypeCd).append("addressTypeName",
				addressTypeName).append("addressTypeDescription", addressTypeDescription).append("addressTypeCode",
				addressTypeCode).toString();
	}

}
