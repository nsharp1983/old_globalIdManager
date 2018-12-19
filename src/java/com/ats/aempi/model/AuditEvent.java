package com.ats.aempi.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * AddressTypeDict entity. 
 * @author yrh-2012-03-06
 */

@Entity
@Table(name = "audit_event")
@SequenceGenerator(name="audit_event_seq",sequenceName="audit_event_seq")

public class AuditEvent extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 0;//待修改,暂时未知,2012-03-08
	
	private Long auditEventId;
	private Person personByAltRefPersonId;
	private AuditEventTypeDict auditEventTypeDict;
	private AppUser appUser;
	private Person personByRefPersonId; 
	private String patientId;
	private String patientDomain;
	private String personId;
	private String visitFlowId;
	private String visitFlowDomain;
	private String empi;
	private String hospitalDomain;
	private String beforeTheChangePerson;
	private String afterTheChangePerson;
	private String beforeTheChangeVisit;
	private String afterTheChangeVisit;
	private String beforeTheChangeOrder;
	private String afterTheChangeOrder;
	private String tempTheChange;
	private String changeDescription;
	private Date dateCreated;
	private String auditEventDescription;

	// Constructors

	/** default constructor */
	public AuditEvent() {
	}

	/** minimal constructor */
	public AuditEvent(Date dateCreated,AuditEventTypeDict auditEventTypeDict, String auditEventDescription,AppUser appUser
			) {
		this.auditEventDescription = auditEventDescription;
		this.auditEventTypeDict = auditEventTypeDict;
		this.appUser = appUser;
		this.dateCreated = dateCreated;
	}

	/** full constructor */
	public AuditEvent(Long auditEventId, Person personByAltRefPersonId,
			AuditEventTypeDict auditEventTypeDict, AppUser appUser,
			Person personByRefPersonId, Date dateCreated,String patientId,
			String auditEventDescription) {
		super();
		this.auditEventId = auditEventId;
		this.personByAltRefPersonId = personByAltRefPersonId;
		this.auditEventTypeDict = auditEventTypeDict;
		this.appUser = appUser;
		this.personByRefPersonId = personByRefPersonId;
		this.dateCreated = dateCreated;
		this.patientId = patientId;
		this.auditEventDescription = auditEventDescription;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="audit_event_seq")
	@Column(name = "audit_event_id", unique = true, nullable = false)
	public Long getAuditEventId() {
		return this.auditEventId;
	}

	public void setAuditEventId(Long auditEventId) {
		this.auditEventId = auditEventId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alt_ref_person_id")
	public Person getPersonByAltRefPersonId() {
		return this.personByAltRefPersonId;
	}

	public void setPersonByAltRefPersonId(Person personByAltRefPersonId) {
		this.personByAltRefPersonId = personByAltRefPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "audit_event_type_cd", nullable = false)
	public AuditEventTypeDict getAuditEventTypeDict() {
		return this.auditEventTypeDict;
	}

	public void setAuditEventTypeDict(AuditEventTypeDict auditEventTypeDict) {
		this.auditEventTypeDict = auditEventTypeDict;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id", nullable = false)
	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ref_person_id")
	public Person getPersonByRefPersonId() {
		return this.personByRefPersonId;
	}

	public void setPersonByRefPersonId(Person personByRefPersonId) {
		this.personByRefPersonId = personByRefPersonId;
	}

	@Column(name = "date_created", nullable = false, length = 8)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "audit_event_description")
	public String getAuditEventDescription() {
		return this.auditEventDescription;
	}

	public void setAuditEventDescription(String auditEventDescription) {
		this.auditEventDescription = auditEventDescription;
	}
	
	@Column(name = "patient_id")
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Column(name = "patient_domain")
	public String getPatientDomain() {
		return this.patientDomain;
	}

	public void setPatientDomain(String patientDomain) {
		this.patientDomain = patientDomain;
	}
	
	@Column(name = "person_id")
	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	@Column(name = "visit_flow_id")
	public String getVisitFlowId() {
		return this.visitFlowId;
	}

	public void setVisitFlowId(String visitFlowId) {
		this.visitFlowId = visitFlowId;
	}
	
	@Column(name = "visit_flow_domain")
	public String getVisitFlowDomain() {
		return this.visitFlowDomain;
	}

	public void setVisitFlowDomain(String visitFlowDomain) {
		this.visitFlowDomain = visitFlowDomain;
	}
	
	@Column(name = "empi")
	public String getEmpi() {
		return this.empi;
	}

	public void setEmpi(String empi) {
		this.empi = empi;
	}
	
	@Column(name = "hospital_domain")
	public String getHospitalDomain() {
		return this.hospitalDomain;
	}

	public void setHospitalDomain(String hospitalDomain) {
		this.hospitalDomain = hospitalDomain;
	}
	
	@Column(name = "before_the_change_person")
	public String getBeforeTheChangePerson() {
		return this.beforeTheChangePerson;
	}

	public void setBeforeTheChangePerson(String beforeTheChangePerson) {
		this.beforeTheChangePerson = beforeTheChangePerson;
	}
	
	@Column(name = "after_the_change_person")
	public String getAfterTheChangePerson() {
		return this.afterTheChangePerson;
	}

	public void setAfterTheChangePerson(String afterTheChangePerson) {
		this.afterTheChangePerson = afterTheChangePerson;
	}
	
	@Column(name = "before_the_change_visit")
	public String getBeforeTheChangeVisit() {
		return this.beforeTheChangeVisit;
	}

	public void setBeforeTheChangeVisit(String beforeTheChangeVisit) {
		this.beforeTheChangeVisit = beforeTheChangeVisit;
	}
	
	@Column(name = "after_the_change_visit")
	public String getAfterTheChangeVisit() {
		return this.afterTheChangeVisit;
	}

	public void setAfterTheChangeVisit(String afterTheChangeVisit) {
		this.afterTheChangeVisit = afterTheChangeVisit;
	}
	
	@Column(name = "before_the_change_order")
	public String getBeforeTheChangeOrder() {
		return this.beforeTheChangeOrder;
	}

	public void setBeforeTheChangeOrder(String beforeTheChangeOrder) {
		this.beforeTheChangeOrder = beforeTheChangeOrder;
	}
	
	@Column(name = "after_the_change_order")
	public String getAfterTheChangeOrder() {
		return this.afterTheChangeOrder;
	}

	public void setAfterTheChangeOrder(String afterTheChangeOrder) {
		this.afterTheChangeOrder = afterTheChangeOrder;
	}
	
	@Column(name = "temp_the_change")
	public String getTempTheChange() {
		return this.tempTheChange;
	}

	public void setTempTheChange(String tempTheChange) {
		this.tempTheChange = tempTheChange;
	}
	
	
	@Column(name = "change_description")
	public String getChangeDescription() {
		return this.changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AuditEvent))
			return false;
		AuditEvent castOther = (AuditEvent) other;
		return new EqualsBuilder().append(auditEventId, castOther.auditEventId)
				.append(dateCreated, castOther.dateCreated).append(
						auditEventTypeDict, castOther.auditEventTypeDict).append(
						auditEventDescription, castOther.auditEventDescription)
				.append(personByRefPersonId, castOther.personByRefPersonId).append(personByAltRefPersonId,
						castOther.personByAltRefPersonId).append(appUser,
						castOther.appUser).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(auditEventId).append(dateCreated)
				.append(auditEventTypeDict).append(auditEventDescription).append(
						personByRefPersonId).append(personByAltRefPersonId).append(appUser)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("auditEventId", auditEventId)
				.append("dateCreated", dateCreated).append("auditEventTypeDict",
						auditEventTypeDict).append("auditEventDescription",
						auditEventDescription).append("personByRefPersonId", personByRefPersonId)
				.append("personByAltRefPersonId", personByAltRefPersonId).append("appUser",
						appUser).toString();
	}

}
