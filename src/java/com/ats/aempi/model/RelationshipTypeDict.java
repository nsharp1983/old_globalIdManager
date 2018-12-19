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
 * RelationshipTypeDict entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "relationship_type_dict", schema = "ATS_DICT")
public class RelationshipTypeDict extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer relationshipTypeCd;
	private String relationshipTypeCode;
	private String relationshipTypeName;
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
	public RelationshipTypeDict() {
	}

	/** minimal constructor */
	public RelationshipTypeDict(Integer relationshipTypeCd,
			String relationshipTypeCode, String relationshipTypeName,
			String inputCode) {
		this.relationshipTypeCd = relationshipTypeCd;
		this.relationshipTypeCode = relationshipTypeCode;
		this.relationshipTypeName = relationshipTypeName;
		this.inputCode = inputCode;
	}

	/** full constructor */
	public RelationshipTypeDict(Integer relationshipTypeCd,
			String relationshipTypeCode, String relationshipTypeName,
			String inputCode, String createName, Date createDate,
			String comments, String custom1, String custom2, String custom3,
			String custom4, String custom5) {
		this.relationshipTypeCd = relationshipTypeCd;
		this.relationshipTypeCode = relationshipTypeCode;
		this.relationshipTypeName = relationshipTypeName;
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
	@Column(name = "relationship_type_cd", unique = true, nullable = false)
	public Integer getRelationshipTypeCd() {
		return this.relationshipTypeCd;
	}

	public void setRelationshipTypeCd(Integer relationshipTypeCd) {
		this.relationshipTypeCd = relationshipTypeCd;
	}

	@Column(name = "relationship_type_code", nullable = false, length = 64)
	public String getRelationshipTypeCode() {
		return this.relationshipTypeCode;
	}

	public void setRelationshipTypeCode(String relationshipTypeCode) {
		this.relationshipTypeCode = relationshipTypeCode;
	}

	@Column(name = "relationship_type_name", nullable = false, length = 64)
	public String getRelationshipTypeName() {
		return this.relationshipTypeName;
	}

	public void setRelationshipTypeName(String relationshipTypeName) {
		this.relationshipTypeName = relationshipTypeName;
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
		if (!(other instanceof RelationshipTypeDict))
			return false;
		RelationshipTypeDict castOther = (RelationshipTypeDict) other;
		return new EqualsBuilder()
		.append(relationshipTypeCd, castOther.relationshipTypeCd)
		.append(relationshipTypeName,castOther.relationshipTypeName)
		.append(relationshipTypeCode, castOther.relationshipTypeCode)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(relationshipTypeCd).append(relationshipTypeName).append(relationshipTypeCode).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("relationshipTypeCd", relationshipTypeCd)
		.append("relationshipTypeName",relationshipTypeName)
		.append("relationshipTypeCode",relationshipTypeCode)
		.toString();
	}
}
