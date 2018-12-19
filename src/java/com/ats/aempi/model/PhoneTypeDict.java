package com.ats.aempi.model;

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
 * PhoneTypeDict entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "phone_type_dict", schema = "ATS_DICT")
public class PhoneTypeDict extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer phoneTypeCd;
	private String phoneTypeName;
	private String phoneTypeDescription;
	private String phoneTypeCode;
	private String createName;
	private Date createDate;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private Set<Person> personsForPhoneTypeCd = new HashSet<Person>(0);
	private Set<Person> personsForPhoneTypeCd1 = new HashSet<Person>(0);

	// Constructors

	/** default constructor */
	public PhoneTypeDict() {
	}

	/** minimal constructor */
	public PhoneTypeDict(Integer phoneTypeCd, String phoneTypeName,
			String phoneTypeCode) {
		this.phoneTypeCd = phoneTypeCd;
		this.phoneTypeName = phoneTypeName;
		this.phoneTypeCode = phoneTypeCode;
	}

	/** full constructor */
	public PhoneTypeDict(Integer phoneTypeCd, String phoneTypeName,
			String phoneTypeDescription, String phoneTypeCode,
			String createName, Date createDate, String comments,
			String custom1, String custom2, String custom3, String custom4,
			String custom5, Set<Person> personsForPhoneTypeCd,
			Set<Person> personsForPhoneTypeCd5,
			Set<Person> personsForPhoneTypeCd3,
			Set<Person> personsForPhoneTypeCd4,
			Set<Person> personsForPhoneTypeCd1,
			Set<Person> personsForPhoneTypeCd2) {
		this.phoneTypeCd = phoneTypeCd;
		this.phoneTypeName = phoneTypeName;
		this.phoneTypeDescription = phoneTypeDescription;
		this.phoneTypeCode = phoneTypeCode;
		this.createName = createName;
		this.createDate = createDate;
		this.comments = comments;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		this.personsForPhoneTypeCd = personsForPhoneTypeCd;
		this.personsForPhoneTypeCd1 = personsForPhoneTypeCd1;
	}

	// Property accessors
	@Id
	@Column(name = "phone_type_cd", unique = true, nullable = false)
	public Integer getPhoneTypeCd() {
		return this.phoneTypeCd;
	}

	public void setPhoneTypeCd(Integer phoneTypeCd) {
		this.phoneTypeCd = phoneTypeCd;
	}

	@Column(name = "phone_type_name", nullable = false, length = 64)
	public String getPhoneTypeName() {
		return this.phoneTypeName;
	}

	public void setPhoneTypeName(String phoneTypeName) {
		this.phoneTypeName = phoneTypeName;
	}

	@Column(name = "phone_type_description")
	public String getPhoneTypeDescription() {
		return this.phoneTypeDescription;
	}

	public void setPhoneTypeDescription(String phoneTypeDescription) {
		this.phoneTypeDescription = phoneTypeDescription;
	}

	@Column(name = "phone_type_code", nullable = false, length = 64)
	public String getPhoneTypeCode() {
		return this.phoneTypeCode;
	}

	public void setPhoneTypeCode(String phoneTypeCode) {
		this.phoneTypeCode = phoneTypeCode;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phoneTypeDictByPhoneTypeCd")
	public Set<Person> getPersonsForPhoneTypeCd() {
		return this.personsForPhoneTypeCd;
	}

	public void setPersonsForPhoneTypeCd(Set<Person> personsForPhoneTypeCd) {
		this.personsForPhoneTypeCd = personsForPhoneTypeCd;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "phoneTypeDictByPhoneTypeCd1")
	public Set<Person> getPersonsForPhoneTypeCd1() {
		return this.personsForPhoneTypeCd1;
	}

	public void setPersonsForPhoneTypeCd1(Set<Person> personsForPhoneTypeCd1) {
		this.personsForPhoneTypeCd1 = personsForPhoneTypeCd1;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof PhoneTypeDict))
			return false;
		PhoneTypeDict castOther = (PhoneTypeDict) other;
		return new EqualsBuilder()
		.append(phoneTypeCd, castOther.phoneTypeCd)
		.append(phoneTypeName,castOther.phoneTypeName)
		.append(phoneTypeCode, castOther.phoneTypeCode)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(phoneTypeCd).append(phoneTypeName).append(phoneTypeCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("phoneTypeCd", phoneTypeCd)
		.append("phoneTypeName",phoneTypeName)
		.append("phoneTypeCode",phoneTypeCode)
		.toString();
	}
}
