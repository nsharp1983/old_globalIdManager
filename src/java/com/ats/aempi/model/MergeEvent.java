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
@Table(name = "merge_event")
@SequenceGenerator(name="merge_event_seq", sequenceName="merge_event_seq")
public class MergeEvent extends BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7816324024350034426L;
	// Fields
	//private static final long serialVersionUID =0;
	
	private Long mergeEventID;
	private String eventUUID;
	private String oldPersonID;
	private String oldPatientID;
	private String oldPatientDomain;
	private String oldName;
	private String oldSex;
	private String oldIdentityNO;
	private String oldBirthday;
	private String oldEmpi;
	private String newEmpi;
	private String mergeType;
	private String mergeStatus;
	private Date mergeDate;
	private String sendStatus;
	private Date sendDate;
	private Date sendBackDate;
	private String notifyStatus;
	private Date notifyDate;
	private Date notifyBackDate;

	// Constructors

	/** default constructor */
	public MergeEvent() {
	}

	/** minimal constructor */
	public MergeEvent(Long mergeEventID) {
		this.mergeEventID = mergeEventID;
	}

	/** full constructor */
	public MergeEvent(Long mergeEventID,
			String eventUUID, String oldPersonID,
			String oldPatientID, String oldPatientDomain,
			String oldName, String oldSex,
			String oldIdentityNO, String oldBirthday, String oldEmpi,
			String newEmpi, String mergeType,String mergeStatus,
			Date mergeDate,String sendStatus,Date sendDate,Date sendBackDate,
			String notifyStatus, Date notifyDate, Date notifyBackDate) {
		this.mergeEventID = mergeEventID;
		this.eventUUID = eventUUID;
		this.oldPersonID = oldPersonID;
		this.oldPatientID = oldPatientID;
		this.oldPatientDomain = oldPatientDomain;
		this.oldName = oldName;
		this.oldSex = oldSex;
		this.oldIdentityNO = oldIdentityNO;
		this.oldBirthday = oldBirthday;
		this.oldEmpi = oldEmpi;
		this.newEmpi = newEmpi;
		this.mergeType = mergeType;
		this.mergeStatus = mergeStatus;
		this.mergeDate = mergeDate;
		this.sendStatus = sendStatus;
		this.sendDate = sendDate;
		this.sendBackDate = sendBackDate;
		this.notifyStatus = notifyStatus;
		this.notifyDate = notifyDate;
		this.notifyBackDate = notifyBackDate;

	}

	// Property accessors
	@Id
	@Column(name = "merge_event_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="merge_event_seq")
	public Long getMergeEventID() {
		return this.mergeEventID;
	}

	public void setMergeEventID(Long mergeEventID) {
		this.mergeEventID = mergeEventID;
	}

	@Column(name = "event_uuid")
	public String getEventUUID() {
		return this.eventUUID;
	}

	public void setEventUUID(String eventUUID) {
		this.eventUUID = eventUUID;
	}


	@Column(name = "old_person_id")
	public String getOldPersonID() {
		return this.oldPersonID;
	}

	public void setOldPersonID(String oldPersonID) {
		this.oldPersonID = oldPersonID;
	}

	@Column(name = "old_patient_id")
	public String getOldPatientID() {
		return this.oldPatientID;
	}

	public void setOldPatientID(String oldPatientID) {
		this.oldPatientID = oldPatientID;
	}

	@Column(name = "old_patient_domain")
	public String getOldPatientDomain() {
		return this.oldPatientDomain;
	}

	public void setOldPatientDomain(String oldPatientDomain) {
		this.oldPatientDomain = oldPatientDomain;
	}

	@Column(name = "old_name")
	public String getOldName() {
		return this.oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	@Column(name = "old_sex")
	public String getOldSex() {
		return this.oldSex;
	}

	public void setOldSex(String oldSex) {
		this.oldSex = oldSex;
	}

	@Column(name = "old_identity_no")
	public String getOldIdentityNO() {
		return this.oldIdentityNO;
	}

	public void setOldIdentityNO(String oldIdentityNO) {
		this.oldIdentityNO = oldIdentityNO;
	}

	@Column(name = "old_birthday")
	public String getOldBirthday() {
		return this.oldBirthday;
	}

	public void setOldBirthday(String oldBirthday) {
		this.oldBirthday = oldBirthday;
	}

	@Column(name = "old_empi")
	public String getOldEmpi() {
		return this.oldEmpi;
	}

	public void setOldEmpi(String oldEmpi) {
		this.oldEmpi = oldEmpi;
	}

	@Column(name = "new_empi")
	public String getNewEmpi() {
		return this.newEmpi;
	}

	public void setNewEmpi(String newEmpi) {
		this.newEmpi = newEmpi;
	}

	@Column(name = "merge_type")
	public String getMergeType() {
		return this.mergeType;
	}

	public void setMergeType(String mergeType) {
		this.mergeType = mergeType;
	}

	@Column(name = "merge_status")
	public String getMergeStatus() {
		return this.mergeStatus;
	}

	public void setMergeStatus(String mergeStatus) {
		this.mergeStatus = mergeStatus;
	}
	
	@Column(name = "merge_date")
	public Date getMergeDate() {
		return this.mergeDate;
	}

	public void setMergeDate(Date mergeDate) {
		this.mergeDate = mergeDate;
	}
	
	@Column(name = "send_status")
	public String getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	@Column(name = "send_date")
	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@Column(name = "send_back_date")
	public Date getSendBackDate() {
		return this.sendBackDate;
	}

	public void setSendBackDate(Date sendBackDate) {
		this.sendBackDate = sendBackDate;
	}
	
	@Column(name = "notify_status")
	public String getNotifyStatus() {
		return this.notifyStatus;
	}

	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}
	
	@Column(name = "notify_date")
	public Date getNotifyDate() {
		return this.notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	
	@Column(name = "notify_back_date")
	public Date getNotifyBackDate() {
		return this.notifyBackDate;
	}

	public void setNotifyBackDate(Date notifyBackDate) {
		this.notifyBackDate = notifyBackDate;
	}
	
	
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof MergeEvent))
			return false;
		MergeEvent castOther = (MergeEvent) other;
		return new EqualsBuilder()
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(mergeEventID).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.toString();
	}

}
