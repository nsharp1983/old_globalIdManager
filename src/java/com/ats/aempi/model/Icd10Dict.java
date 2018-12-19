package com.ats.aempi.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Icd10Dict entity. 
 * @author yrh 2012-03-08
 */
@Entity
@Table(name = "icd10_dict", schema = "ATS_DICT")
public class Icd10Dict extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer icd10Cd;
	private String icd10Code;
	private String icd10Name;
	private String inputCode;
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
	public Icd10Dict() {
	}

	/** minimal constructor */
	public Icd10Dict(Integer icd10Cd, String icd10Code, String icd10Name,
			String inputCode) {
		this.icd10Cd = icd10Cd;
		this.icd10Code = icd10Code;
		this.icd10Name = icd10Name;
		this.inputCode = inputCode;
	}

	/** full constructor */
	public Icd10Dict(Integer icd10Cd, String icd10Code, String icd10Name,
			String inputCode, String createName, Date createDate,
			String comments, String custom1, String custom2, String custom3,
			String custom4, String custom5) {
		this.icd10Cd = icd10Cd;
		this.icd10Code = icd10Code;
		this.icd10Name = icd10Name;
		this.inputCode = inputCode;
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
	@Column(name = "icd10_cd", unique = true, nullable = false)
	public Integer getIcd10Cd() {
		return this.icd10Cd;
	}

	public void setIcd10Cd(Integer icd10Cd) {
		this.icd10Cd = icd10Cd;
	}

	@Column(name = "icd10_code", nullable = false, length = 64)
	public String getIcd10Code() {
		return this.icd10Code;
	}

	public void setIcd10Code(String icd10Code) {
		this.icd10Code = icd10Code;
	}

	@Column(name = "icd10_name", nullable = false, length = 64)
	public String getIcd10Name() {
		return this.icd10Name;
	}

	public void setIcd10Name(String icd10Name) {
		this.icd10Name = icd10Name;
	}

	@Column(name = "input_code", nullable = false, length = 64)
	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
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
		if (!(other instanceof Icd10Dict))
			return false;
		Icd10Dict castOther = (Icd10Dict) other;
		return new EqualsBuilder()
		.append(icd10Cd, castOther.icd10Cd)
		.append(icd10Name,castOther.icd10Name)
		.append(inputCode, castOther.inputCode)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(icd10Cd).append(icd10Name).append(inputCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("icd10Cd", icd10Cd)
		.append("icd10Name",icd10Name)
		.append("inputCode",inputCode)
		.toString();
	}

}
