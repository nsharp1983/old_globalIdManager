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
@Table(name = "person_in_out")
@SequenceGenerator(name="person_in_out_seq", sequenceName="person_in_out_seq")
public class PersonInOut extends BaseObject implements java.io.Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4824228900635677198L;
	
	
	private Long personInOutID;
	private String patientId;
	private String patientDomain;
	private String visitFlowId;
	private String visitFlowDomain;
	private String name;
	private Date dateOfBirth;
	private String sex;
	private String identityNo;
	private String insuranceNo;
	private String insuranceType;
	private String currentBed;
	private String currentDep;
	private String currentRoom;
	private String oldContent;
	private String newContent;
	private String changeType;
	private String changeDescription;
	private String operId;
	private Date dateCreated;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	

	// Constructors

	/** default constructor */
	public PersonInOut() {
	}

	/** minimal constructor */
	public PersonInOut(Long personInOutID) {
		this.personInOutID = personInOutID;
	}

	/** full constructor */
	public PersonInOut(Long personInOutID,
			String patientId, String patientDomain,
			String visitFlowId, String visitFlowDomain,
			String name, Date dateOfBirth,
			String sex, String identityNo, String insuranceNo,
			String insuranceType, String currentBed,String currentDep,String currentRoom,
			String oldContent,String newContent,String changeType,String changeDescription,String operId,
			Date dateCreated,String custom1,String custom2,String custom3,String custom4,
			String custom5) {
		this.personInOutID = personInOutID;
		this.patientId = patientId;
		this.patientDomain = patientDomain;
		this.visitFlowId = visitFlowId;
		this.visitFlowDomain = visitFlowDomain;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.identityNo = identityNo;
		this.insuranceNo = insuranceNo;
		this.insuranceType = insuranceType;
		this.currentBed = currentBed;
		this.currentDep = currentDep;
		this.currentRoom = currentRoom;
		this.oldContent = oldContent;
		this.newContent = newContent;
		this.changeType = changeType;
		this.changeDescription = changeDescription;
		this.operId = operId;
		this.dateCreated = dateCreated;	

	}

	// Property accessors
	@Id
	@Column(name = "person_in_out_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="person_in_out_seq")
	public Long getPersonInOutID() {
		return personInOutID;
	}

	public void setPersonInOutID(Long personInOutID) {
		this.personInOutID = personInOutID;
	}

	@Column(name = "patient_id")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Column(name = "patient_domain")
	public String getPatientDomain() {
		return patientDomain;
	}

	public void setPatientDomain(String patientDomain) {
		this.patientDomain = patientDomain;
	}

	@Column(name = "visit_flow_id")
	public String getVisitFlowId() {
		return visitFlowId;
	}

	public void setVisitFlowId(String visitFlowId) {
		this.visitFlowId = visitFlowId;
	}

	@Column(name = "visit_flow_domain")
	public String getVisitFlowDomain() {
		return visitFlowDomain;
	}

	public void setVisitFlowDomain(String visitFlowDomain) {
		this.visitFlowDomain = visitFlowDomain;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "identity_no")
	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	@Column(name = "insurance_no")
	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	@Column(name = "insurance_type")
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Column(name = "current_bed")
	public String getCurrentBed() {
		return currentBed;
	}

	public void setCurrentBed(String currentBed) {
		this.currentBed = currentBed;
	}

	@Column(name = "current_dep")
	public String getCurrentDep() {
		return currentDep;
	}

	public void setCurrentDep(String currentDep) {
		this.currentDep = currentDep;
	}

	@Column(name = "current_room")
	public String getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}

	@Column(name = "old_content")
	public String getOldContent() {
		return oldContent;
	}

	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}

	@Column(name = "new_content")
	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	@Column(name = "change_type")
	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	@Column(name = "change_description")
	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	@Column(name = "oper_id")
	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	@Column(name = "date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "custom1")
	public String getCustom1() {
		return custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	@Column(name = "custom2")
	public String getCustom2() {
		return custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	@Column(name = "custom3")
	public String getCustom3() {
		return custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Column(name = "custom4")
	public String getCustom4() {
		return custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	@Column(name = "custom5")
	public String getCustom5() {
		return custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}


	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof PersonInOut))
			return false;
		PersonInOut castOther = (PersonInOut) other;
		return new EqualsBuilder()
		.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(personInOutID).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
		.toString();
	}

}
