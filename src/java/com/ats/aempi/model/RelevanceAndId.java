package com.ats.aempi.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Contactperson entity. 
 * @author yrh 2012-11-26
 */
@Entity
@Table(name = "relevance_and_id")
@SequenceGenerator(name="relevance_and_id_seq", sequenceName="relevance_and_id_seq")
public class RelevanceAndId extends BaseObject implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9170876295959342163L;
	
	private Long relevanceAndId;
	private String regId;
	private String regDomain;
	private String regName;
	private String relevanceId;
	private String relevanceDomain;
	private String relevanceName;
	private String empi;
	private Date dateCreated;
	private String custom1;
	private String custom2;

	// Constructors

	/** default constructor */
	public RelevanceAndId() {
	}

	/** minimal constructor */
	public RelevanceAndId(Long relevanceAndId) {
		this.relevanceAndId = relevanceAndId;
	}

	/** full constructor */
	public RelevanceAndId(Long relevanceAndId,
			String regId, String regDomain,
			String regName, String relevanceId,
			String relevanceDomain, String relevanceName,
			Date dateCreated, String custom1, String custom2) {
		this.relevanceAndId = relevanceAndId;
		this.regId = regId;
		this.regDomain = regDomain;
		this.regName = regName;
		this.relevanceId = relevanceId;
		this.relevanceDomain = relevanceDomain;
		this.relevanceName = relevanceName;
		this.dateCreated = dateCreated;
		this.custom1 = custom1;
		this.custom2 = custom2;

	}

	// Property accessors
	@Id
	@Column(name = "relevance_and_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="relevance_and_id_seq")
	public Long getRelevanceAndId() {
		return this.relevanceAndId;
	}

	public void setRelevanceAndId(Long relevanceAndId) {
		this.relevanceAndId = relevanceAndId;
	}

	@Column(name = "reg_id")
	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}


	@Column(name = "reg_domain")
	public String getRegDomain() {
		return this.regDomain;
	}

	public void setRegDomain(String regDomain) {
		this.regDomain = regDomain;
	}

	@Column(name = "reg_name")
	public String getRegName() {
		return this.regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	@Column(name = "relevance_id")
	public String getRelevanceId() {
		return this.relevanceId;
	}

	public void setRelevanceId(String relevanceId) {
		this.relevanceId = relevanceId;
	}

	@Column(name = "relevance_domain")
	public String getRelevanceDomain() {
		return this.relevanceDomain;
	}

	public void setRelevanceDomain(String relevanceDomain) {
		this.relevanceDomain = relevanceDomain;
	}

	@Column(name = "relevance_name")
	public String getRelevanceName() {
		return this.relevanceName;
	}

	public void setRelevanceName(String relevanceName) {
		this.relevanceName = relevanceName;
	}

	@Column(name = "date_created")
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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
	
	@Column(name = "empi")
	public String getEmpi() {
		return this.empi;
	}

	public void setEmpi(String empi) {
		this.empi = empi;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof RelevanceAndId))
			return false;
		RelevanceAndId castOther = (RelevanceAndId) other;
		return new EqualsBuilder()
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(relevanceAndId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.toString();
	}

}
