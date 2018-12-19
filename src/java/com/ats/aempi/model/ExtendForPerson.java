package com.ats.aempi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RaceDict entity. 
 * @author yrh 2012-11-19
 */
@Entity
@Table(name = "extendforperson")
public class ExtendForPerson extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;
	

	private Integer extendId;
	private String extendfieldname;
	private Integer pid4fields;
	private String custom1;
	private String custom2;
	private String custom3;

	// Constructors

	/** default constructor */
	public ExtendForPerson() {
	}

	/** minimal constructor */
	public ExtendForPerson(Integer extendId) {
		this.extendId = extendId;
	}

	/** full constructor */
	public ExtendForPerson(Integer extendId, String extendfieldname,
			Integer pid4fields,String custom1,
			String custom2, String custom3) {
		this.extendId = extendId;
		this.extendfieldname = extendfieldname;
		this.pid4fields = pid4fields;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
	}

	// Property accessors

	@Id
	@Column(name = "EXTEND_ID", nullable = false, precision = 22, scale = 0)
	public Integer getExtendId() {
		return this.extendId;
	}

	public void setExtendId(Integer extendId) {
		this.extendId = extendId;
	}

	@Column(name = "EXTENDFIELDNAME", length = 64)
	public String getExtendfieldname() {
		return this.extendfieldname;
	}

	public void setExtendfieldname(String extendfieldname) {
		this.extendfieldname = extendfieldname;
	}

	@Column(name = "PID4_FIELDS")
	public Integer getPid4fields() {
		return this.pid4fields;
	}

	public void setPid4fields(Integer pid4fields) {
		this.pid4fields = pid4fields;
	}

	@Column(name = "CUSTOM1", length = 256)
	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	@Column(name = "CUSTOM2", length = 256)
	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	@Column(name = "CUSTOM3", length = 256)
	public String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof ExtendForPerson))
			return false;
		ExtendForPerson castOther = (ExtendForPerson) other;
		return new EqualsBuilder()
		.append(extendId, castOther.extendId)
		.append(extendfieldname,castOther.extendfieldname)
		.append(pid4fields, castOther.pid4fields)
		.append(custom1, castOther.custom1)
		.append(custom2, castOther.custom2)
		.append(custom3, custom3)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
									.append(extendId)
									.append(extendfieldname)
									.append(pid4fields)
									.append(custom1)
									.append(custom2)
									.append(custom3)
									.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("extendId", extendId)
		.append("extendfieldname",extendfieldname)
		.append("pid4fields",pid4fields)
		.append("custom1",custom1)
		.append("custom2",custom2)
		.append("custom3",custom3)
		.toString();
	}
}
