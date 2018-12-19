package com.ats.aempi.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * PersonLink entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person_link")
@SequenceGenerator(name="person_link_seq", sequenceName="person_link_seq")
public class PersonLink implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -2998399249175445866L;
	
	private Long personLinkId;
	private Person personByLhPersonId;
	private Person personByRhPersonId;
	private AppUser appUser;
	private Date dateCreated;
	private double weight;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;

	// Constructors

	/** default constructor */
	public PersonLink() {
	}

	/** minimal constructor */
	public PersonLink(Long personLinkId, Person personByLhPersonId,
			Person personByRhPersonId, AppUser appUser, Date dateCreated,
			double weight) {
		this.personLinkId = personLinkId;
		this.personByLhPersonId = personByLhPersonId;
		this.personByRhPersonId = personByRhPersonId;
		this.appUser = appUser;
		this.dateCreated = dateCreated;
		this.weight = weight;
	}

	/** full constructor */
	public PersonLink(Long personLinkId, Person personByLhPersonId,
			Person personByRhPersonId, AppUser appUser, Date dateCreated,
			double weight, String custom1, String custom2, String custom3,
			String custom4, String custom5) {
		this.personLinkId = personLinkId;
		this.personByLhPersonId = personByLhPersonId;
		this.personByRhPersonId = personByRhPersonId;
		this.appUser = appUser;
		this.dateCreated = dateCreated;
		this.weight = weight;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
	}

	// Property accessors
	@Id
	@Column(name = "person_link_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="person_link_seq")
	public Long getPersonLinkId() {
		return this.personLinkId;
	}

	public void setPersonLinkId(Long personLinkId) {
		this.personLinkId = personLinkId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lh_person_id", nullable =true)
	public Person getPersonByLhPersonId() {
	//	if(personByLhPersonId!=null)personByLhPersonId.toString();
		return this.personByLhPersonId;
	}

	public void setPersonByLhPersonId(Person personByLhPersonId) {
		this.personByLhPersonId = personByLhPersonId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rh_person_id", nullable = true)
	public Person getPersonByRhPersonId() {
	//	if(personByRhPersonId!=null)personByRhPersonId.getPersonIdentifiers().toString();
		return this.personByRhPersonId;
	}

	public void setPersonByRhPersonId(Person personByRhPersonId) {
		this.personByRhPersonId = personByRhPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "date_created", nullable = false, length = 8)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "weight", nullable = false, precision = 8, scale = 0)
	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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
		if (!(other instanceof PersonLink))
			return false;
		PersonLink castOther = (PersonLink) other;
		return new EqualsBuilder().append(personLinkId, castOther.personLinkId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(personLinkId).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("personLinkId", personLinkId)
		//.append("personLeft",personByLhPersonId.getPersonId())
		//.append("personRight", personByRhPersonId.getPersonId())
		//.append("creatorId", appUser)
		.append("dateCreated", dateCreated)
		.append("weight", weight).toString();
	}
}
