package com.ats.aempi.model;

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
 * EthnicgroupDict entity.
 *  @author yrh 2012-03-08
 */
@Entity
@Table(name = "ethnicgroup_dict", schema = "ATS_DICT")
public class EthnicgroupDict extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer ethnicGroupCd;
	private String ethnicGroupName;
	private String ethnicGroupDescription;
	private String ethnicGroupCode;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private Set<Person> persons = new HashSet<Person>(0);

	// Constructors

	/** default constructor */
	public EthnicgroupDict() {
	}

	/** minimal constructor */
	public EthnicgroupDict(Integer ethnicGroupCd, String ethnicGroupName,
			String ethnicGroupCode) {
		this.ethnicGroupCd = ethnicGroupCd;
		this.ethnicGroupName = ethnicGroupName;
		this.ethnicGroupCode = ethnicGroupCode;
	}

	/** full constructor */
	public EthnicgroupDict(Integer ethnicGroupCd, String ethnicGroupName,
			String ethnicGroupDescription, String ethnicGroupCode,
			String comments, String custom1, String custom2, String custom3,
			String custom4, String custom5, Set<Person> persons) {
		this.ethnicGroupCd = ethnicGroupCd;
		this.ethnicGroupName = ethnicGroupName;
		this.ethnicGroupDescription = ethnicGroupDescription;
		this.ethnicGroupCode = ethnicGroupCode;
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
	@Column(name = "ethnic_group_cd", unique = true, nullable = false)
	public Integer getEthnicGroupCd() {
		return this.ethnicGroupCd;
	}

	public void setEthnicGroupCd(Integer ethnicGroupCd) {
		this.ethnicGroupCd = ethnicGroupCd;
	}

	@Column(name = "ethnic_group_name", nullable = false, length = 64)
	public String getEthnicGroupName() {
		return this.ethnicGroupName;
	}

	public void setEthnicGroupName(String ethnicGroupName) {
		this.ethnicGroupName = ethnicGroupName;
	}

	@Column(name = "ethnic_group_description")
	public String getEthnicGroupDescription() {
		return this.ethnicGroupDescription;
	}

	public void setEthnicGroupDescription(String ethnicGroupDescription) {
		this.ethnicGroupDescription = ethnicGroupDescription;
	}

	@Column(name = "ethnic_group_code", nullable = false, length = 64)
	public String getEthnicGroupCode() {
		return this.ethnicGroupCode;
	}

	public void setEthnicGroupCode(String ethnicGroupCode) {
		this.ethnicGroupCode = ethnicGroupCode;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ethnicgroupDict")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof EthnicgroupDict))
			return false;
		EthnicgroupDict castOther = (EthnicgroupDict) other;
		return new EqualsBuilder()
		.append(ethnicGroupCd, castOther.ethnicGroupCd)
		.append(ethnicGroupName,castOther.ethnicGroupName)
		.append(ethnicGroupCode, castOther.ethnicGroupCode)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(ethnicGroupCd).append(ethnicGroupName).append(ethnicGroupCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("ethnicGroupCd", ethnicGroupCd)
		.append("ethnicGroupName",ethnicGroupName)
		.append("ethnicGroupCode",ethnicGroupCode)
		.toString();
	}
}
