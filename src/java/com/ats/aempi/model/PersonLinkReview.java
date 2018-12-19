package com.ats.aempi.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * PersonLinkReview entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "person_link_review")
public class PersonLinkReview extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Integer personLinkReviewId;
	private Person personByLhPersonId;
	private Person personByRhPersonId;
	private AppUser appUserByReviewerId;
	private AppUser appUserByCreatorId;
	private Date dateCreated;
	private double weight;
	private Integer dateReviewed;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;

	// Constructors

	/** default constructor */
	public PersonLinkReview() {
	}

	/** minimal constructor */
	public PersonLinkReview(Integer personLinkReviewId,
			Person personByLhPersonId, Person personByRhPersonId,
			AppUser appUserByCreatorId, Date dateCreated) {
		this.personLinkReviewId = personLinkReviewId;
		this.personByLhPersonId = personByLhPersonId;
		this.personByRhPersonId = personByRhPersonId;
		this.appUserByCreatorId = appUserByCreatorId;
		this.dateCreated = dateCreated;
	}

	/** full constructor */
	public PersonLinkReview(Integer personLinkReviewId,
			Person personByLhPersonId, Person personByRhPersonId,
			AppUser appUserByReviewerId, AppUser appUserByCreatorId,
			Date dateCreated, double weight, Integer dateReviewed,
			String custom1, String custom2, String custom3, String custom4,
			String custom5) {
		this.personLinkReviewId = personLinkReviewId;
		this.personByLhPersonId = personByLhPersonId;
		this.personByRhPersonId = personByRhPersonId;
		this.appUserByReviewerId = appUserByReviewerId;
		this.appUserByCreatorId = appUserByCreatorId;
		this.dateCreated = dateCreated;
		this.weight = weight;
		this.dateReviewed = dateReviewed;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
	}

	// Property accessors
	@Id
	@Column(name = "person_link_review_id", unique = true, nullable = false)
	public Integer getPersonLinkReviewId() {
		return this.personLinkReviewId;
	}

	public void setPersonLinkReviewId(Integer personLinkReviewId) {
		this.personLinkReviewId = personLinkReviewId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lh_person_id", nullable = false)
	public Person getPersonByLhPersonId() {
		return this.personByLhPersonId;
	}

	public void setPersonByLhPersonId(Person personByLhPersonId) {
		this.personByLhPersonId = personByLhPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rh_person_id", nullable = false)
	public Person getPersonByRhPersonId() {
		return this.personByRhPersonId;
	}

	public void setPersonByRhPersonId(Person personByRhPersonId) {
		this.personByRhPersonId = personByRhPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reviewer_id")
	public AppUser getAppUserByReviewerId() {
		return this.appUserByReviewerId;
	}

	public void setAppUserByReviewerId(AppUser appUserByReviewerId) {
		this.appUserByReviewerId = appUserByReviewerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false)
	public AppUser getAppUserByCreatorId() {
		return this.appUserByCreatorId;
	}

	public void setAppUserByCreatorId(AppUser appUserByCreatorId) {
		this.appUserByCreatorId = appUserByCreatorId;
	}

	@Column(name = "date_created", nullable = false, length = 8)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "weight", precision = 8, scale = 0)
	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Column(name = "date_reviewed")
	public Integer getDateReviewed() {
		return this.dateReviewed;
	}

	public void setDateReviewed(Integer dateReviewed) {
		this.dateReviewed = dateReviewed;
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
		if (!(other instanceof PersonLinkReview))
			return false;
		PersonLinkReview castOther = (PersonLinkReview) other;
		return new EqualsBuilder()
		.append(personLinkReviewId, castOther.personLinkReviewId)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(personLinkReviewId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("personLinkReviewId", personLinkReviewId)
		.toString();
	}
}
