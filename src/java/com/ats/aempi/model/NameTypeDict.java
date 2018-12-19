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
 * NameTypeDict entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "name_type_dict", schema = "ATS_DICT")
public class NameTypeDict extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer nameTypeCd;
	private String nameTypeName;
	private String nameTypeDescription;
	private String nameTypeCode;
	private String createName;
	private Date createDate;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private Set<Person> persons = new HashSet<Person>(0);

	// Constructors

	/** default constructor */
	public NameTypeDict() {
	}

	/** minimal constructor */
	public NameTypeDict(Integer nameTypeCd, String nameTypeName,
			String nameTypeCode) {
		this.nameTypeCd = nameTypeCd;
		this.nameTypeName = nameTypeName;
		this.nameTypeCode = nameTypeCode;
	}

	/** full constructor */
	public NameTypeDict(Integer nameTypeCd, String nameTypeName,
			String nameTypeDescription, String nameTypeCode, String createName,
			Date createDate, String comments, String custom1, String custom2,
			String custom3, String custom4, String custom5, Set<Person> persons) {
		this.nameTypeCd = nameTypeCd;
		this.nameTypeName = nameTypeName;
		this.nameTypeDescription = nameTypeDescription;
		this.nameTypeCode = nameTypeCode;
		this.createName = createName;
		this.createDate = createDate;
		this.comments = comments;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		this.persons = persons;
	}

	// Property accessors
	@Id
	@Column(name = "name_type_cd", unique = true, nullable = false)
	public Integer getNameTypeCd() {
		return this.nameTypeCd;
	}

	public void setNameTypeCd(Integer nameTypeCd) {
		this.nameTypeCd = nameTypeCd;
	}

	@Column(name = "name_type_name", nullable = false, length = 64)
	public String getNameTypeName() {
		return this.nameTypeName;
	}

	public void setNameTypeName(String nameTypeName) {
		this.nameTypeName = nameTypeName;
	}

	@Column(name = "name_type_description")
	public String getNameTypeDescription() {
		return this.nameTypeDescription;
	}

	public void setNameTypeDescription(String nameTypeDescription) {
		this.nameTypeDescription = nameTypeDescription;
	}

	@Column(name = "name_type_code", nullable = false, length = 64)
	public String getNameTypeCode() {
		return this.nameTypeCode;
	}

	public void setNameTypeCode(String nameTypeCode) {
		this.nameTypeCode = nameTypeCode;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nameTypeDict")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof NameTypeDict))
			return false;
		NameTypeDict castOther = (NameTypeDict) other;
		return new EqualsBuilder()
		.append(nameTypeCd, castOther.nameTypeCd)
		.append(nameTypeName,castOther.nameTypeName)
		.append(nameTypeCode, castOther.nameTypeCode)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nameTypeCd).append(nameTypeName).append(nameTypeCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("nameTypeCd", nameTypeCd)
		.append("nameTypeName",nameTypeName)
		.append("nameTypeCode",nameTypeCode)
		.toString();
	}
}
