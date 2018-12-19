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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * UserSession entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "user_session", uniqueConstraints = @UniqueConstraint(columnNames = "session_key"))
@SequenceGenerator(name="user_session_seq",sequenceName="user_session_seq")
public class UserSession implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 2611151383140968220L;
	
	private Long sessionId;
	private AppUser appUser;
	private Date dateCreated;
	private String sessionKey;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;

	// Constructors

	/** default constructor */
	public UserSession() {
	}

	/** minimal constructor */
	public UserSession(String sessionKey, AppUser appUser, Date dateCreated) {
		this.sessionKey = sessionKey;
		this.appUser = appUser;
		this.dateCreated = dateCreated;
	}

	/** full constructor */
	public UserSession(Long sessionId, AppUser appUser, Date dateCreated,
			String sessionKey, String custom1, String custom2, String custom3,
			String custom4, String custom5) {
		this.sessionId = sessionId;
		this.appUser = appUser;
		this.dateCreated = dateCreated;
		this.sessionKey = sessionKey;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
	}

	// Property accessors
	@Id
	@Column(name = "session_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_session_seq")
	public Long getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
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

	@Column(name = "session_key", unique = true)
	public String getSessionKey() {
		return this.sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
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
		if (!(other instanceof UserSession))
			return false;
		UserSession castOther = (UserSession) other;
		return new EqualsBuilder().append(sessionKey, castOther.sessionKey).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sessionKey).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("sessionId", sessionId)
		.append("sessionKey", sessionKey)
		.append("appUser",appUser)
		.append("dateCreated", dateCreated).toString();
	}

}
