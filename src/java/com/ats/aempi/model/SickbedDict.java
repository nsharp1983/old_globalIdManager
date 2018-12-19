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
 * SickbedDict entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "sickbed_dict", schema = "ATS_DICT")
public class SickbedDict  extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer sickbedCd;
	private String sickbedCode;
	private String sickbedName;
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
	public SickbedDict() {
	}

	/** minimal constructor */
	public SickbedDict(Integer sickbedCd, String sickbedCode,
			String sickbedName, String inputCode) {
		this.sickbedCd = sickbedCd;
		this.sickbedCode = sickbedCode;
		this.sickbedName = sickbedName;
		this.inputCode = inputCode;
	}

	/** full constructor */
	public SickbedDict(Integer sickbedCd, String sickbedCode,
			String sickbedName, String inputCode, String createName,
			Date createDate, String comments, String custom1, String custom2,
			String custom3, String custom4, String custom5) {
		this.sickbedCd = sickbedCd;
		this.sickbedCode = sickbedCode;
		this.sickbedName = sickbedName;
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
	@Column(name = "sickbed_cd", unique = true, nullable = false)
	public Integer getSickbedCd() {
		return this.sickbedCd;
	}

	public void setSickbedCd(Integer sickbedCd) {
		this.sickbedCd = sickbedCd;
	}

	@Column(name = "sickbed_code", nullable = false, length = 64)
	public String getSickbedCode() {
		return this.sickbedCode;
	}

	public void setSickbedCode(String sickbedCode) {
		this.sickbedCode = sickbedCode;
	}

	@Column(name = "sickbed_name", nullable = false, length = 64)
	public String getSickbedName() {
		return this.sickbedName;
	}

	public void setSickbedName(String sickbedName) {
		this.sickbedName = sickbedName;
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
		if (!(other instanceof SickbedDict))
			return false;
		SickbedDict castOther = (SickbedDict) other;
		return new EqualsBuilder()
		.append(sickbedCd, castOther.sickbedCd)
		.append(sickbedName,castOther.sickbedName)
		.append(inputCode, castOther.inputCode)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sickbedCd).append(sickbedName).append(inputCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("sickbedCd", sickbedCd)
		.append("sickbedName",sickbedName)
		.append("inputCode",inputCode)
		.toString();
	}

}
