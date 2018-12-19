package com.ats.aempi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;



/**
 * AppUser entity. 
 * @author yrh-2012-03-06
 */
@Entity
@Table(name = "app_user")
public class AppUser extends BaseObject implements  Serializable,UserDetails {

	// Fields
	private static final long serialVersionUID = 3832626162173359411L;
	 
	private long id;
	private Integer version;
	private String username;
	private String email;
	private String phoneNumber;
	private String passwordHint;
	private String firstName;
	private String lastName;
	private String website;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private String city;
	private String province;
	private String postalCode;
	private String address;
	private String country;
	//private boolean accountEnabled;
	private String password;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private boolean enabled;
	private Set<UserSession> userSessions = new HashSet<UserSession>(0);
	private Set<PersonLinkReview> personLinkReviewsForReviewerId = new HashSet<PersonLinkReview>(
			0);
	private Set<Person> personsForCreatorId = new HashSet<Person>(0);
	//private Set<AuditEvent> auditEvents = new HashSet<AuditEvent>(0);
	private Set<UserFile> userFiles = new HashSet<UserFile>(0);
	private Set<PersonLinkReview> personLinkReviewsForCreatorId = new HashSet<PersonLinkReview>(
			0);
	private Set<PersonLink> personLinks = new HashSet<PersonLink>(0);
	private Set<Person> personsForChangedById = new HashSet<Person>(0);
	private Set<PersonIdentifierEMPI> personIdentifiersForCreatorId = new HashSet<PersonIdentifierEMPI>(
			0);
	private Set<PersonIdentifierEMPI> personIdentifiersForVoidedById = new HashSet<PersonIdentifierEMPI>(
			0);
	private Set<IdentifierDomainDict> identifierDomainDicts = new HashSet<IdentifierDomainDict>(
			0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<Person> personsForVoidedById = new HashSet<Person>(0);
	private Set<Role> roles = new HashSet<Role>();

	// Constructors

	/** default constructor */
	public AppUser() {
	}

	/** minimal constructor */
	public AppUser(long id, String username, String email, String firstName,
			String lastName, boolean accountExpired1, boolean accountLocked,
			boolean credentialsExpired, String city, String postalCode,
			String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountExpired = accountExpired1;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.city = city;
		this.postalCode = postalCode;
		this.password = password;
	}

	/** full constructor */
	public AppUser(long id, String username, String email, String phoneNumber,
			String passwordHint, String firstName, String lastName,
			String website, boolean accountExpired2, boolean accountLocked,
			boolean credentialsExpired, String city, String province,
			String postalCode, String address, String country,
			boolean accountEnabled, String password, String custom1,
			String custom2, String custom3, String custom4, String custom5,
			Set<UserSession> userSessions,
			Set<PersonLinkReview> personLinkReviewsForReviewerId,
			Set<Person> personsForCreatorId, Set<AuditEvent> auditEvents,
			Set<UserFile> userFiles,
			Set<PersonLinkReview> personLinkReviewsForCreatorId,
			Set<PersonLink> personLinks, Set<Person> personsForChangedById,
			Set<PersonIdentifierEMPI> personIdentifiersForCreatorId,
			Set<PersonIdentifierEMPI> personIdentifiersForVoidedById,
			Set<IdentifierDomainDict> identifierDomainDicts,
			Set<UserRole> userRoles, Set<Person> personsForVoidedById) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.passwordHint = passwordHint;
		this.firstName = firstName;
		this.lastName = lastName;
		this.website = website;
		this.accountExpired = accountExpired2;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.address = address;
		this.country = country;
		this.enabled = accountEnabled;
		this.password = password;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		this.userSessions = userSessions;
		this.personLinkReviewsForReviewerId = personLinkReviewsForReviewerId;
		this.personsForCreatorId = personsForCreatorId;
		//this.auditEvents = auditEvents;
		this.userFiles = userFiles;
		this.personLinkReviewsForCreatorId = personLinkReviewsForCreatorId;
		this.personLinks = personLinks;
		this.personsForChangedById = personsForChangedById;
		this.personIdentifiersForCreatorId = personIdentifiersForCreatorId;
		this.personIdentifiersForVoidedById = personIdentifiersForVoidedById;
		this.identifierDomainDicts = identifierDomainDicts;
		this.userRoles = userRoles;
		this.personsForVoidedById = personsForVoidedById;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled=enabled;
	}

	@Column(name = "username", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "password_hint")
	public String getPasswordHint() {
		return this.passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	@Column(name = "first_name", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "website")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "city", nullable = false, length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province", length = 100)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "postal_code", nullable = false, length = 15)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "address", length = 150)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles=roles;
	}
	@Column(name = "country", length = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUser")
	public Set<UserSession> getUserSessions() {
		return this.userSessions;
	}

	public void setUserSessions(Set<UserSession> userSessions) {
		this.userSessions = userSessions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUserByReviewerId")
	public Set<PersonLinkReview> getPersonLinkReviewsForReviewerId() {
		return this.personLinkReviewsForReviewerId;
	}

	public void setPersonLinkReviewsForReviewerId(
			Set<PersonLinkReview> personLinkReviewsForReviewerId) {
		this.personLinkReviewsForReviewerId = personLinkReviewsForReviewerId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUserByCreatorId")
	public Set<Person> getPersonsForCreatorId() {
		return this.personsForCreatorId;
	}

	public void setPersonsForCreatorId(Set<Person> personsForCreatorId) {
		this.personsForCreatorId = personsForCreatorId;
	}

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUser")
//	public Set<AuditEvent> getAuditEvents() {
//		return this.auditEvents;
//	}
//
//	public void setAuditEvents(Set<AuditEvent> auditEvents) {
//		this.auditEvents = auditEvents;
//	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUser")
	public Set<UserFile> getUserFiles() {
		return this.userFiles;
	}

	public void setUserFiles(Set<UserFile> userFiles) {
		this.userFiles = userFiles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUserByCreatorId")
	public Set<PersonLinkReview> getPersonLinkReviewsForCreatorId() {
		return this.personLinkReviewsForCreatorId;
	}

	public void setPersonLinkReviewsForCreatorId(
			Set<PersonLinkReview> personLinkReviewsForCreatorId) {
		this.personLinkReviewsForCreatorId = personLinkReviewsForCreatorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUser")
	public Set<PersonLink> getPersonLinks() {
		return this.personLinks;
	}

	public void setPersonLinks(Set<PersonLink> personLinks) {
		this.personLinks = personLinks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUserByChangedById")
	public Set<Person> getPersonsForChangedById() {
		return this.personsForChangedById;
	}

	public void setPersonsForChangedById(Set<Person> personsForChangedById) {
		this.personsForChangedById = personsForChangedById;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUserByCreatorId")
	public Set<PersonIdentifierEMPI> getPersonIdentifiersForCreatorId() {
		return this.personIdentifiersForCreatorId;
	}

	public void setPersonIdentifiersForCreatorId(
			Set<PersonIdentifierEMPI> personIdentifiersForCreatorId) {
		this.personIdentifiersForCreatorId = personIdentifiersForCreatorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUserByVoidedById")
	public Set<PersonIdentifierEMPI> getPersonIdentifiersForVoidedById() {
		return this.personIdentifiersForVoidedById;
	}

	public void setPersonIdentifiersForVoidedById(
			Set<PersonIdentifierEMPI> personIdentifiersForVoidedById) {
		this.personIdentifiersForVoidedById = personIdentifiersForVoidedById;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUser")
	public Set<IdentifierDomainDict> getIdentifierDomainDicts() {
		return this.identifierDomainDicts;
	}

	public void setIdentifierDomainDicts(
			Set<IdentifierDomainDict> identifierDomainDicts) {
		this.identifierDomainDicts = identifierDomainDicts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUser")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUserByVoidedById")
	public Set<Person> getPersonsForVoidedById() {
		return this.personsForVoidedById;
	}

	public void setPersonsForVoidedById(Set<Person> personsForVoidedById) {
		this.personsForVoidedById = personsForVoidedById;
	}
	 
	/**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUser)) {
            return false;
        }

        final AppUser user = (AppUser) o;

        return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

    }
    
	/**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (username != null ? username.hashCode() : 0);
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("username", this.username)
                .append("enabled", this.enabled)
                .append("accountExpired", this.accountExpired)
                .append("credentialsExpired", this.credentialsExpired)
                .append("accountLocked", this.accountLocked).toString();
    }
    
    
    /**
     * {@inheritDoc}
     */
    public String toStringLong() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("username", this.username)
                .append("enabled", this.enabled)
                .append("accountExpired", this.accountExpired)
                .append("credentialsExpired", this.credentialsExpired)
                .append("accountLocked", this.accountLocked);

        GrantedAuthority[] auths = this.getAuthorities();
        if (auths != null) {
            sb.append("Granted Authorities: ");

            for (int i = 0; i < auths.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(auths[i].toString());
            }
        } else {
            sb.append("No Granted Authorities");
        }
        return sb.toString();
    }
    
    /**
     * Returns the full name.
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }


    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable(
            name="user_role",
            joinColumns = { @JoinColumn( name="user_id") },
            inverseJoinColumns = @JoinColumn( name="role_id")
    )    
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Convert user roles to LabelValue objects for convenience.
     * @return a list of LabelValue objects with role information
     */
    //@Transient
//    public List<LabelValue> getRoleList() {
//        List<LabelValue> userRoles = new ArrayList<LabelValue>();
//
//        if (this.roles != null) {
//            for (Role role :roles) {
//                // convert the user's roles to LabelValue Objects
//                userRoles.add(new LabelValue(role.getName(), role.getName()));
//            }
//        }
//
//        return userRoles;
//    }

    /**
     * Adds a role for the user
     * @param role the fully instantiated role
     */
    public void addRole(Role role) {
        getRoles().add(role);
    }
    
    /**
     * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
     * @return GrantedAuthority[] an array of roles.
     */
    @Transient
    public GrantedAuthority[] getAuthorities() {
        return roles.toArray(new GrantedAuthority[0]);
    }

 
    @Column(name="account_enabled")
    public boolean isEnabled() {
        return enabled;
    }
    
	public void setAccountEnabled(boolean accountEnabled) {
		this.enabled = accountEnabled;
	}
    
    @Column(name="account_expired",nullable=false)
    public boolean isAccountExpired() {
        return accountExpired;
    }
    
    public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
    
    @Column(name="account_locked",nullable=false)
    public boolean isAccountLocked() {
        return accountLocked;
    }
    
    public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
    
    @Column(name="credentials_expired",nullable=false)
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }
    
    public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
    /**
     * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
     */
    @Transient
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    /**
     * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
     */
    @Transient
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    
    /**
     * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }
   

	
}
