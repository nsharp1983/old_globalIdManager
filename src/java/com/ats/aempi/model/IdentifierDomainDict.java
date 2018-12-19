package com.ats.aempi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * IdentifierDomainDict entity. 
 * @author yrh 2012-03-08
 */
@Entity
@Table(name = "identifier_domain_dict",schema = "ATS_DICT",uniqueConstraints = {
				@UniqueConstraint(columnNames = { "universal_identifier","universal_identifier_type_code" }),
				@UniqueConstraint(columnNames = "namespace_identifier") })
@SequenceGenerator(name="identifier_domain_seq", sequenceName="identifier_domain_seq")

public class IdentifierDomainDict extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 2611151383140968220L;
	
	private Integer identifierDomainId;
	private AppUser appUser;
	private String universalIdentifier;
	private String universalIdentifierTypeCode;
	private String namespaceIdentifier;
	private Date dateCreated;
	private String comments;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private Set<PersonIdentifierEMPI> personIdentifiers = new HashSet<PersonIdentifierEMPI>(
			0);
	private Set<Person> persons = new HashSet<Person>(0);

	// Constructors

	/** default constructor */
	public IdentifierDomainDict() {}

	/** minimal constructor */
	public IdentifierDomainDict(Integer identifierDomainId, AppUser appUser,
			Date dateCreated) {
		this.identifierDomainId = identifierDomainId;
		this.appUser = appUser;
		this.dateCreated = dateCreated;
	}

	/** full constructor */
	public IdentifierDomainDict(Integer identifierDomainId, AppUser appUser,
			String universalIdentifier, String universalIdentifierTypeCode,
			String namespaceIdentifier, Date dateCreated, String comments,
			String custom1, String custom2, String custom3, String custom4,
			String custom5, Set<PersonIdentifierEMPI> personIdentifiers,
			Set<Person> persons) {
		this.identifierDomainId = identifierDomainId;
		this.appUser = appUser;
		this.universalIdentifier = universalIdentifier;
		this.universalIdentifierTypeCode = universalIdentifierTypeCode;
		this.namespaceIdentifier = namespaceIdentifier;
		this.dateCreated = dateCreated;
		this.comments = comments;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		this.personIdentifiers = personIdentifiers;
		this.persons = persons;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier_domain_seq")
	@Column(name = "identifier_domain_id", unique = true, nullable = false)
	public Integer getIdentifierDomainId() {
		return this.identifierDomainId;
	}

	public void setIdentifierDomainId(Integer identifierDomainId) {
		this.identifierDomainId = identifierDomainId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Column(name = "universal_identifier")
	public String getUniversalIdentifier() {
		return this.universalIdentifier;
	}

	public void setUniversalIdentifier(String universalIdentifier) {
		this.universalIdentifier = universalIdentifier;
	}

	@Column(name = "universal_identifier_type_code")
	public String getUniversalIdentifierTypeCode() {
		return this.universalIdentifierTypeCode;
	}

	public void setUniversalIdentifierTypeCode(
			String universalIdentifierTypeCode) {
		this.universalIdentifierTypeCode = universalIdentifierTypeCode;
	}

	@Column(name = "namespace_identifier", unique = true)
	public String getNamespaceIdentifier() {
		return this.namespaceIdentifier;
	}

	public void setNamespaceIdentifier(String namespaceIdentifier) {
		this.namespaceIdentifier = namespaceIdentifier;
	}

	@Column(name = "date_created", nullable = false, length = 8)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "identifierDomainDict")
	public Set<PersonIdentifierEMPI> getPersonIdentifiers() {
		return this.personIdentifiers;
	}

	public void setPersonIdentifiers(Set<PersonIdentifierEMPI> personIdentifiers) {
		this.personIdentifiers = personIdentifiers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "identifierDomainDict")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (this==other) {
			return true;
		}
		if (identifierDomainId == null) {
			return false;
		}
		if (!(other instanceof IdentifierDomainDict)) {
			return false;
		}
		IdentifierDomainDict castOther = (IdentifierDomainDict) other;
		if (this.identifierDomainId == castOther.getIdentifierDomainId()) {
			return true;
		}
		if (this.getNamespaceIdentifier() != null && castOther.getNamespaceIdentifier() != null) {
			return getNamespaceIdentifier().equals(castOther.getNamespaceIdentifier());
		}
		if (this.getUniversalIdentifier() != null && castOther.getUniversalIdentifier() != null &&
				this.getUniversalIdentifierTypeCode() != null && castOther.getUniversalIdentifierTypeCode() != null) {
			return new EqualsBuilder().append(universalIdentifier, castOther.universalIdentifier).append(
							universalIdentifierTypeCode, castOther.universalIdentifierTypeCode).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		if (identifierDomainId != null) {
			return new HashCodeBuilder().append(identifierDomainId).toHashCode(); 
		}
		if (this.getNamespaceIdentifier() != null ) {
			return new HashCodeBuilder().append(namespaceIdentifier).toHashCode();
		}
		if (this.getUniversalIdentifier() != null &&
				this.getUniversalIdentifierTypeCode() != null) {
			return new HashCodeBuilder().append(universalIdentifier)
			.append(universalIdentifierTypeCode).toHashCode();
		}
		return System.identityHashCode(this);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("identifierDomainId", identifierDomainId)
		//.append("appUser",appUser)
		.append("universalIdentifier", universalIdentifier)
		.append("universalIdentifierTypeCode",universalIdentifierTypeCode)
		.append("namespaceIdentifier", namespaceIdentifier)
		.append("dateCreated",dateCreated).toString();
	}

	public Integer hydrate() {
		return getIdentifierDomainId();
	}

}
