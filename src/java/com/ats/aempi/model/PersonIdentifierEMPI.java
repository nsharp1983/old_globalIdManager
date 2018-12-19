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
 * PersonIdentifier entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "person_identifier")
@SequenceGenerator(name="person_identifier_seq", sequenceName="person_identifier_seq")
public class PersonIdentifierEMPI extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1943429923033311936L;
	
	private Long personIdentifierId;//机构内病人id
	private IdentifierDomainDict identifierDomainDict;
	private AppUser appUserByCreatorId;
	private AppUser appUserByVoidedById;
	private Person person;
	private Date dateCreated;
	private Date dateVoided;
	private String identifier;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;

	// Constructors

	/** default constructor */
	public PersonIdentifierEMPI() {
	}

	/** minimal constructor */
	public PersonIdentifierEMPI(Long personIdentifierId,
			IdentifierDomainDict identifierDomainDict,
			AppUser appUserByCreatorId, Person person, Date dateCreated,
			String identifier) {
		this.personIdentifierId = personIdentifierId;
		this.identifierDomainDict = identifierDomainDict;
		this.appUserByCreatorId = appUserByCreatorId;
		this.person = person;
		this.dateCreated = dateCreated;
		this.identifier = identifier;
	}

	/** full constructor */
	public PersonIdentifierEMPI(Long personIdentifierId,
			IdentifierDomainDict identifierDomainDict,
			AppUser appUserByCreatorId, AppUser appUserByVoidedById,
			Person person, Date dateCreated, Date dateVoided,
			String identifier, String custom1, String custom2, String custom3,
			String custom4, String custom5) {
		this.personIdentifierId = personIdentifierId;
		this.identifierDomainDict = identifierDomainDict;
		this.appUserByCreatorId = appUserByCreatorId;
		this.appUserByVoidedById = appUserByVoidedById;
		this.person = person;
		this.dateCreated = dateCreated;
		this.dateVoided = dateVoided;
		this.identifier = identifier;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
	}

	// Property accessors
	@Id
	@Column(name = "person_identifier_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="person_identifier_seq") 
	public Long getPersonIdentifierId() {
		return this.personIdentifierId;
	}

	public void setPersonIdentifierId(Long personIdentifierId) {
		this.personIdentifierId = personIdentifierId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "identifier_domain_id", nullable = false)
	public IdentifierDomainDict getIdentifierDomainDict() {
		return this.identifierDomainDict;
	}

	public void setIdentifierDomainDict(
			IdentifierDomainDict identifierDomainDict) {
		this.identifierDomainDict = identifierDomainDict;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false)
	public AppUser getAppUserByCreatorId() {
		return this.appUserByCreatorId;
	}

	public void setAppUserByCreatorId(AppUser appUserByCreatorId) {
		this.appUserByCreatorId = appUserByCreatorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voided_by_id")
	public AppUser getAppUserByVoidedById() {
		return this.appUserByVoidedById;
	}

	public void setAppUserByVoidedById(AppUser appUserByVoidedById) {
		this.appUserByVoidedById = appUserByVoidedById;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "date_created", nullable = false, length = 8)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "date_voided", length = 8)
	public Date getDateVoided() {
		return this.dateVoided;
	}

	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}

	@Column(name = "identifier", nullable = false)
	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
		if (!(other instanceof PersonIdentifierEMPI))
			return false;
		PersonIdentifierEMPI castOther = (PersonIdentifierEMPI) other;
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}
		if (personIdentifierId == castOther.personIdentifierId) {
			return true;
		}
		return new EqualsBuilder().append(identifierDomainDict,
				castOther.identifierDomainDict)
				.append(identifier, castOther.identifier)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(personIdentifierId)
			.append(identifierDomainDict)
			.append(identifier).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("personIdentifierId", personIdentifierId)
		.append("identifierDomainDict",identifierDomainDict)
		.append("identifier", identifier)
		.append("dateCreated",dateCreated)
		.append("dateVoided", dateVoided)
		.toString();
	}

	public Long hydrate() {
		return getPersonIdentifierId();
	}
}
