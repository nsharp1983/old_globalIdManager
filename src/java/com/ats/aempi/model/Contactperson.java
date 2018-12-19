package com.ats.aempi.model;

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
@Table(name = "contactperson")
@SequenceGenerator(name="contact_seq", sequenceName="contact_seq")
public class Contactperson extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Long contactId;
	private String contactType;
	private Long personId;
	private String contactName;
	private String contactAddress;
	private String contactPhone;
	private String contactMobile;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;

	// Constructors

	/** default constructor */
	public Contactperson() {
	}

	/** minimal constructor */
	public Contactperson(Long contactId) {
		this.contactId = contactId;
	}

	/** full constructor */
	public Contactperson(Long contactId,
			String contactType, Long personId,
			String contactName, String contactAddress,
			String contactPhone, String contactMobile,
			String custom1, String custom2, String custom3,
			String custom4, String custom5) {
		this.contactId = contactId;
		this.contactType = contactType;
		this.personId = personId;
		this.contactName = contactName;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.contactMobile = contactMobile;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;

	}

	// Property accessors
	@Id
	@Column(name = "contact_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contact_seq")
	public Long getContactId() {
		return this.contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	@Column(name = "contact_type")
	public String getContactType() {
		return this.contactType;
	}

	public void setContactType(
			String contactType) {
		this.contactType = contactType;
	}


	@Column(name = "person_id",nullable = false)
	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	@Column(name = "contact_name", length = 64)
	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "contact_address")
	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	@Column(name = "contact_phone")
	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Column(name = "contact_mobile")
	public String getContactMobile() {
		return this.contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
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
		if (!(other instanceof Contactperson))
			return false;
		Contactperson castOther = (Contactperson) other;
		return new EqualsBuilder()
		.append(contactId, castOther.contactId)
		.append(contactName,castOther.contactName)
		.append(contactType, castOther.contactType)
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(contactId).append(contactName).append(contactType).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.append("contactpersonId", contactId)
		.append("contactpersonname",contactName)
		.append("relationshipType",contactType)
		.toString();
	}

}
