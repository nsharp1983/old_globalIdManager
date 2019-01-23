package com.ats.aempi.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * PersonVisit entity. 
 * 扩展PV1字段表
 * @author yrh 2012-09-13
 */
@Entity
@Table(name = "patient_visit")
@SequenceGenerator(name="visit_seq", sequenceName="visit_seq")
public class PatientVisit extends BaseObject implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long visitId;
	private Long personId;
	private String personDomain;
	private String visitFlowId;
	private String visitFlowDomain;
	private String hospitalDomain;
	private String patCategory;
	private String patCurrentPointOfCare;
	private String patCurrentRoom;
	private String patCurrentBed;
	private String patCuurentDep;
	private String patCurrentPositionStatus;
	private String patCurrentPositionType;
	private String patCurrentBuilding;
	private String patCurrentFloor;
	private String patCuurentDescription;
	private String patAdmissionType;
	private String patAdmissionNumber;
	private String patFormerPointOfCare;
	private String patFormerRoom;
	private String patFormerBed;
	private String patFormerDep;
	private String patFormerPositionStatus;
	private String patFormerPositionType;
	private String patFormerBuilding;
	private String patFormerFloor;
	private String patFormerDescription;
	private String admissionsDoctor;
	private String admissionsDoctorId;
	private String referringDoctor;
	private String referringDoctorId;
	private String consultationDoctor;
	private String consultationDoctorId;
	private String hospitalService;
	private String patTempPointOfCare;
	private String patTempRoom;
	private String patTempBed;
	private String patTempDep;
	private String patTempPositionStatus;
	private String patTempPositionType;
	private String patTempBuilding;
	private String patTempFloor;
	private String patTempDescription;
	private String patAdmissionTest;
	private String patReAdmission;
	private String patAdmissionSource;
	private String patAmbulatoryStatus;
	private String patVip;
	private String patAdmissionDoctors;
	private String patAdmissionDoctorsId;
	private String patientClass;
	private String patientId;
	private String patFinancialClass;
	private String roomBedCostPrice;
	private String courtesyCode;
	private String creditRating;
	private String contractCode;
	private String contractCreateDate;
	private String contractPrice;
	private String contractTime;
	private String interestRateCode;
	private String badDebts;
	private String badDebtsCreateDate;
	private String badDebtsCode;
	private String badDebtsPrice;
	private String badDebtsRestorePrice;
	private String patAccountVoided;
	private String patAccountVoidedDate;
	private String patDischargeDisposition;
	private String patDischargeLocation;
	private String patDietType;
	private String patServiceAgencies;
	private String patBedStatus;
	private String patAccountStatus;
	private String patDeterPointOfCare;
	private String patDeterRoom;
	private String patDeterBed;
	private String patDeterDep;
	private String patDeterPositionStatus;
	private String patDeterPositionType;
	private String patDeterBuilding;
	private String patDeterFloor;
	private String patDeterDescription;
	private String patForTempPointOfCare;
	private String patForTempRoom;
	private String patForTempBed;
	private String patForTempDep;
	private String patForTempPositionStatus;
	private String patForTempPositionType;
	private String patForTempBuilding;
	private String patForTempFloor;
	private String patForTempDescription;
	private Date admitDate;
	private Date dischargeDate;
	private String patDifference;
	private String patTotalCost;
	private String patTotalDispatch;
	private String patTotalAmountPayable;
	private String patSpareId;
	private String patVisitLogo;
	private String otherMedicalInstitutions;
	private Date createDate;
	private String createId;
	private Date voidedDate;
	private String voidedId;
	private Date modifyDate;
	private String modifyId;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	private String patNurseCode;
	private String patNurseName;
	private String tend;
	private String babyFlag;
	private String admitWeight;
	private String birthWeight;
	private String operCode;
	private Date operDate;
	private String prefix;
	private String insuranceType;
	private String contactPerson;
	private String contactRelations;
	private String contactAddress;
	private String contactPhone;
	private String patCategoryName;
	private String genderName;
	private String payRate;
	private String dischargeName;
	private String insuranceName;
	private String admissionName;
	private String ipStatusName;
	private String dificultyName;
	private String admitWayName;
	private String admitWeightUnit;
	private String babyWeightUnit;
	private BigDecimal medicinelimitamount;
	private BigDecimal sickbedlimitamount;
	private BigDecimal examinelimitamount;
	private BigDecimal curelimitamount;
	private String hiupStatus;
	private String hiupErrorInfo;
	private String admissionDomain;
	private String admissionSourceDomain;
	private String admissionSourceName;
	private String patientClassName;
	private String patientClassDomain;
	private String ipStatusDomain;
	private String dificultyDomain;
	private String dischargeDomain;
	private String accountStatusName;
	private String accountStatusDomain;
	private String genderDomain;
	private String patCategorySystem;
	private String mothersID;
	private String mothersFlowID;
	private String mothersFlowDomain;
	private String mothersName;
	private String mothersDomain;
	private String patientSourceName;
	private String oldPatientId;
	private String oldPatientDomain;
	private String oldVisitFlowId;
	private String oldVisitFlowDomain;
	private String oldVisitId;
	private String oldPersonId;
	private String oldStatus;
	private String oldInfo;
	private String isEmergency;
	private String patIpTimes;
	private String diagnoseIcd;
	private String diagnoseName;
	private String noonCode;
	private String paykindCode;
	private String paykindName;
	private String schemaNo;
	private String orderNo;
	private String seeNo;
	private Date beginTime;
	private Date endTime;
	private String ynBook;
	private String ynfr;
	private String appendFlag;
	private String ynSee;
	private Date seeDate;
	private String triageFlag;
	private String triageOpcd;
	private Date triageDate;
	private String seeDpcd;
	private String seeDocd;
	private String outPatientStatusA;
	private String outPatientStatusB;
	private String outPatientStatusC;
	private String inPatientStatusA;
	private String inPatientStatusB;
	private String inPatientStatusC;
	private String relevanceID;
	private String relevanceDomain;
	private String relevanceName;

	private Integer isoutpatient;

	// Constructors

	/**
	 * default constructor
	 */
	public PatientVisit() {
	}


	/**
	 * full constructor
	 */
	public PatientVisit(Long visitId, Long personId,
						String personDomain, String visitFlowId, String visitFlowDomain,
						String hospitalDomain, String patCategory,
						String patCurrentPointOfCare, String patCurrentRoom,
						String patCurrentBed, String patCuurentDep,
						String patCurrentPositionStatus, String patCurrentPositionType,
						String patCurrentBuilding, String patCurrentFloor,
						String patCuurentDescription, String patAdmissionType,
						String patAdmissionNumber, String patFormerPointOfCare,
						String patFormerRoom, String patFormerBed, String patFormerDep,
						String patFormerPositionStatus, String patFormerPositionType,
						String patFormerBuilding, String patFormerFloor,
						String patFormerDescription, String admissionsDoctor,
						String admissionsDoctorId, String referringDoctor,
						String referringDoctorId, String consultationDoctor,
						String consultationDoctorId, String hospitalService,
						String patTempPointOfCare, String patTempRoom, String patTempBed,
						String patTempDep, String patTempPositionStatus,
						String patTempPositionType, String patTempBuilding,
						String patTempFloor, String patTempDescription,
						String patAdmissionTest, String patReAdmission,
						String patAdmissionSource, String patAmbulatoryStatus,
						String patVip, String patAdmissionDoctors,
						String patAdmissionDoctorsId, String patientClass,
						String patientId, String patFinancialClass,
						String roomBedCostPrice, String courtesyCode, String creditRating,
						String contractCode, String contractCreateDate,
						String contractPrice, String contractTime, String interestRateCode,
						String badDebts, String badDebtsCreateDate, String badDebtsCode,
						String badDebtsPrice, String badDebtsRestorePrice,
						String patAccountVoided, String patAccountVoidedDate,
						String patDischargeDisposition, String patDischargeLocation,
						String patDietType, String patServiceAgencies, String patBedStatus,
						String patAccountStatus, String patDeterPointOfCare,
						String patDeterRoom, String patDeterBed, String patDeterDep,
						String patDeterPositionStatus, String patDeterPositionType,
						String patDeterBuilding, String patDeterFloor,
						String patDeterDescription, String patForTempPointOfCare,
						String patForTempRoom, String patForTempBed, String patForTempDep,
						String patForTempPositionStatus, String patForTempPositionType,
						String patForTempBuilding, String patForTempFloor,
						String patForTempDescription, Date admitDate,
						Date dischargeDate, String patDifference, String patTotalCost,
						String patTotalDispatch, String patTotalAmountPayable,
						String patSpareId, String patVisitLogo,
						String otherMedicalInstitutions, Date createDate,
						String createId, Date voidedDate, String voidedId,
						Date modifyDate, String modifyId, String custom1,
						String custom2, String custom3, String custom4, String custom5,
						String patNurseCode, String patNurseName, String tend,
						String babyFlag, String admitWeight, String birthWeight,
						String operCode, Date operDate, String prefix,
						String insuranceType, String contactPerson,
						String contactRelations, String contactAddress,
						String contactPhone, String patCategoryName, String genderName,
						String payRate, String dischargeName, String insuranceName,
						String admissionName, String ipStatusName, String dificultyName,
						String admitWayName, String admitWeightUnit, String babyWeightUnit,
						BigDecimal medicinelimitamount, BigDecimal sickbedlimitamount,
						BigDecimal examinelimitamount, BigDecimal curelimitamount,
						String hiupStatus, String hiupErrorInfo, String admissionDomain,
						String admissionSourceDomain, String admissionSourceName,
						String patientClassName, String patientClassDomain,
						String ipStatusDomain, String dificultyDomain,
						String dischargeDomain, String accountStatusName,
						String accountStatusDomain, String genderDomain,
						String patCategorySystem) {
		this.visitId = visitId;
		this.personId = personId;
		this.personDomain = personDomain;
		this.visitFlowId = visitFlowId;
		this.visitFlowDomain = visitFlowDomain;
		this.hospitalDomain = hospitalDomain;
		this.patCategory = patCategory;
		this.patCurrentPointOfCare = patCurrentPointOfCare;
		this.patCurrentRoom = patCurrentRoom;
		this.patCurrentBed = patCurrentBed;
		this.patCuurentDep = patCuurentDep;
		this.patCurrentPositionStatus = patCurrentPositionStatus;
		this.patCurrentPositionType = patCurrentPositionType;
		this.patCurrentBuilding = patCurrentBuilding;
		this.patCurrentFloor = patCurrentFloor;
		this.patCuurentDescription = patCuurentDescription;
		this.patAdmissionType = patAdmissionType;
		this.patAdmissionNumber = patAdmissionNumber;
		this.patFormerPointOfCare = patFormerPointOfCare;
		this.patFormerRoom = patFormerRoom;
		this.patFormerBed = patFormerBed;
		this.patFormerDep = patFormerDep;
		this.patFormerPositionStatus = patFormerPositionStatus;
		this.patFormerPositionType = patFormerPositionType;
		this.patFormerBuilding = patFormerBuilding;
		this.patFormerFloor = patFormerFloor;
		this.patFormerDescription = patFormerDescription;
		this.admissionsDoctor = admissionsDoctor;
		this.admissionsDoctorId = admissionsDoctorId;
		this.referringDoctor = referringDoctor;
		this.referringDoctorId = referringDoctorId;
		this.consultationDoctor = consultationDoctor;
		this.consultationDoctorId = consultationDoctorId;
		this.hospitalService = hospitalService;
		this.patTempPointOfCare = patTempPointOfCare;
		this.patTempRoom = patTempRoom;
		this.patTempBed = patTempBed;
		this.patTempDep = patTempDep;
		this.patTempPositionStatus = patTempPositionStatus;
		this.patTempPositionType = patTempPositionType;
		this.patTempBuilding = patTempBuilding;
		this.patTempFloor = patTempFloor;
		this.patTempDescription = patTempDescription;
		this.patAdmissionTest = patAdmissionTest;
		this.patReAdmission = patReAdmission;
		this.patAdmissionSource = patAdmissionSource;
		this.patAmbulatoryStatus = patAmbulatoryStatus;
		this.patVip = patVip;
		this.patAdmissionDoctors = patAdmissionDoctors;
		this.patAdmissionDoctorsId = patAdmissionDoctorsId;
		this.patientClass = patientClass;
		this.patientId = patientId;
		this.patFinancialClass = patFinancialClass;
		this.roomBedCostPrice = roomBedCostPrice;
		this.courtesyCode = courtesyCode;
		this.creditRating = creditRating;
		this.contractCode = contractCode;
		this.contractCreateDate = contractCreateDate;
		this.contractPrice = contractPrice;
		this.contractTime = contractTime;
		this.interestRateCode = interestRateCode;
		this.badDebts = badDebts;
		this.badDebtsCreateDate = badDebtsCreateDate;
		this.badDebtsCode = badDebtsCode;
		this.badDebtsPrice = badDebtsPrice;
		this.badDebtsRestorePrice = badDebtsRestorePrice;
		this.patAccountVoided = patAccountVoided;
		this.patAccountVoidedDate = patAccountVoidedDate;
		this.patDischargeDisposition = patDischargeDisposition;
		this.patDischargeLocation = patDischargeLocation;
		this.patDietType = patDietType;
		this.patServiceAgencies = patServiceAgencies;
		this.patBedStatus = patBedStatus;
		this.patAccountStatus = patAccountStatus;
		this.patDeterPointOfCare = patDeterPointOfCare;
		this.patDeterRoom = patDeterRoom;
		this.patDeterBed = patDeterBed;
		this.patDeterDep = patDeterDep;
		this.patDeterPositionStatus = patDeterPositionStatus;
		this.patDeterPositionType = patDeterPositionType;
		this.patDeterBuilding = patDeterBuilding;
		this.patDeterFloor = patDeterFloor;
		this.patDeterDescription = patDeterDescription;
		this.patForTempPointOfCare = patForTempPointOfCare;
		this.patForTempRoom = patForTempRoom;
		this.patForTempBed = patForTempBed;
		this.patForTempDep = patForTempDep;
		this.patForTempPositionStatus = patForTempPositionStatus;
		this.patForTempPositionType = patForTempPositionType;
		this.patForTempBuilding = patForTempBuilding;
		this.patForTempFloor = patForTempFloor;
		this.patForTempDescription = patForTempDescription;
		this.admitDate = admitDate;
		this.dischargeDate = dischargeDate;
		this.patDifference = patDifference;
		this.patTotalCost = patTotalCost;
		this.patTotalDispatch = patTotalDispatch;
		this.patTotalAmountPayable = patTotalAmountPayable;
		this.patSpareId = patSpareId;
		this.patVisitLogo = patVisitLogo;
		this.otherMedicalInstitutions = otherMedicalInstitutions;
		this.createDate = createDate;
		this.createId = createId;
		this.voidedDate = voidedDate;
		this.voidedId = voidedId;
		this.modifyDate = modifyDate;
		this.modifyId = modifyId;
		this.custom1 = custom1;
		this.custom2 = custom2;
		this.custom3 = custom3;
		this.custom4 = custom4;
		this.custom5 = custom5;
		this.patNurseCode = patNurseCode;
		this.patNurseName = patNurseName;
		this.tend = tend;
		this.babyFlag = babyFlag;
		this.admitWeight = admitWeight;
		this.birthWeight = birthWeight;
		this.operCode = operCode;
		this.operDate = operDate;
		this.prefix = prefix;
		this.insuranceType = insuranceType;
		this.contactPerson = contactPerson;
		this.contactRelations = contactRelations;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.patCategoryName = patCategoryName;
		this.genderName = genderName;
		this.payRate = payRate;
		this.dischargeName = dischargeName;
		this.insuranceName = insuranceName;
		this.admissionName = admissionName;
		this.ipStatusName = ipStatusName;
		this.dificultyName = dificultyName;
		this.admitWayName = admitWayName;
		this.admitWeightUnit = admitWeightUnit;
		this.babyWeightUnit = babyWeightUnit;
		this.medicinelimitamount = medicinelimitamount;
		this.sickbedlimitamount = sickbedlimitamount;
		this.examinelimitamount = examinelimitamount;
		this.curelimitamount = curelimitamount;
		this.hiupStatus = hiupStatus;
		this.hiupErrorInfo = hiupErrorInfo;
		this.admissionDomain = admissionDomain;
		this.admissionSourceDomain = admissionSourceDomain;
		this.admissionSourceName = admissionSourceName;
		this.patientClassName = patientClassName;
		this.patientClassDomain = patientClassDomain;
		this.ipStatusDomain = ipStatusDomain;
		this.dificultyDomain = dificultyDomain;
		this.dischargeDomain = dischargeDomain;
		this.accountStatusName = accountStatusName;
		this.accountStatusDomain = accountStatusDomain;
		this.genderDomain = genderDomain;
		this.patCategorySystem = patCategorySystem;
	}

	// Property accessors
	@Column(name = "ISOUTPATIENT",unique = true, nullable = false)
	public Integer getIsoutpatient() {
		return isoutpatient;
	}

	public void setIsoutpatient(Integer isoutpatient) {
		this.isoutpatient = isoutpatient;
	}

	@Id
	@Column(name = "VISIT_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_seq")
	public Long getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	@Column(name = "PERSON_ID", nullable = false, precision = 22, scale = 0)
	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	@Column(name = "PERSON_DOMAIN", nullable = false, length = 64)
	public String getPersonDomain() {
		return this.personDomain;
	}

	public void setPersonDomain(String personDomain) {
		this.personDomain = personDomain;
	}

	@Column(name = "VISIT_FLOW_ID", nullable = false, length = 64)
	public String getVisitFlowId() {
		return this.visitFlowId;
	}

	public void setVisitFlowId(String visitFlowId) {
		this.visitFlowId = visitFlowId;
	}

	@Column(name = "VISIT_FLOW_DOMAIN", nullable = false, length = 64)
	public String getVisitFlowDomain() {
		return this.visitFlowDomain;
	}

	public void setVisitFlowDomain(String visitFlowDomain) {
		this.visitFlowDomain = visitFlowDomain;
	}

	@Column(name = "HOSPITAL_DOMAIN", length = 64)
	public String getHospitalDomain() {
		return this.hospitalDomain;
	}

	public void setHospitalDomain(String hospitalDomain) {
		this.hospitalDomain = hospitalDomain;
	}

	@Column(name = "PAT_CATEGORY", length = 16)
	public String getPatCategory() {
		return this.patCategory;
	}

	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}

	@Column(name = "PAT_CURRENT_POINT_OF_CARE", length = 256)
	public String getPatCurrentPointOfCare() {
		return this.patCurrentPointOfCare;
	}

	public void setPatCurrentPointOfCare(String patCurrentPointOfCare) {
		this.patCurrentPointOfCare = patCurrentPointOfCare;
	}

	@Column(name = "PAT_CURRENT_ROOM", length = 256)
	public String getPatCurrentRoom() {
		return this.patCurrentRoom;
	}

	public void setPatCurrentRoom(String patCurrentRoom) {
		this.patCurrentRoom = patCurrentRoom;
	}

	@Column(name = "PAT_CURRENT_BED", length = 256)
	public String getPatCurrentBed() {
		return this.patCurrentBed;
	}

	public void setPatCurrentBed(String patCurrentBed) {
		this.patCurrentBed = patCurrentBed;
	}

	@Column(name = "PAT_CUURENT_DEP", length = 256)
	public String getPatCuurentDep() {
		return this.patCuurentDep;
	}

	public void setPatCuurentDep(String patCuurentDep) {
		this.patCuurentDep = patCuurentDep;
	}

	@Column(name = "PAT_CURRENT_POSITION_STATUS", length = 256)
	public String getPatCurrentPositionStatus() {
		return this.patCurrentPositionStatus;
	}

	public void setPatCurrentPositionStatus(String patCurrentPositionStatus) {
		this.patCurrentPositionStatus = patCurrentPositionStatus;
	}

	@Column(name = "PAT_CURRENT_POSITION_TYPE", length = 32)
	public String getPatCurrentPositionType() {
		return this.patCurrentPositionType;
	}

	public void setPatCurrentPositionType(String patCurrentPositionType) {
		this.patCurrentPositionType = patCurrentPositionType;
	}

	@Column(name = "PAT_CURRENT_BUILDING", length = 64)
	public String getPatCurrentBuilding() {
		return this.patCurrentBuilding;
	}

	public void setPatCurrentBuilding(String patCurrentBuilding) {
		this.patCurrentBuilding = patCurrentBuilding;
	}

	@Column(name = "PAT_CURRENT_FLOOR", length = 64)
	public String getPatCurrentFloor() {
		return this.patCurrentFloor;
	}

	public void setPatCurrentFloor(String patCurrentFloor) {
		this.patCurrentFloor = patCurrentFloor;
	}

	@Column(name = "PAT_CUURENT_DESCRIPTION", length = 256)
	public String getPatCuurentDescription() {
		return this.patCuurentDescription;
	}

	public void setPatCuurentDescription(String patCuurentDescription) {
		this.patCuurentDescription = patCuurentDescription;
	}

	@Column(name = "PAT_ADMISSION_TYPE", length = 16)
	public String getPatAdmissionType() {
		return this.patAdmissionType;
	}

	public void setPatAdmissionType(String patAdmissionType) {
		this.patAdmissionType = patAdmissionType;
	}

	@Column(name = "PAT_ADMISSION_NUMBER", length = 128)
	public String getPatAdmissionNumber() {
		return this.patAdmissionNumber;
	}

	public void setPatAdmissionNumber(String patAdmissionNumber) {
		this.patAdmissionNumber = patAdmissionNumber;
	}

	@Column(name = "PAT_FORMER_POINT_OF_CARE", length = 256)
	public String getPatFormerPointOfCare() {
		return this.patFormerPointOfCare;
	}

	public void setPatFormerPointOfCare(String patFormerPointOfCare) {
		this.patFormerPointOfCare = patFormerPointOfCare;
	}

	@Column(name = "PAT_FORMER_ROOM", length = 256)
	public String getPatFormerRoom() {
		return this.patFormerRoom;
	}

	public void setPatFormerRoom(String patFormerRoom) {
		this.patFormerRoom = patFormerRoom;
	}

	@Column(name = "PAT_FORMER_BED", length = 256)
	public String getPatFormerBed() {
		return this.patFormerBed;
	}

	public void setPatFormerBed(String patFormerBed) {
		this.patFormerBed = patFormerBed;
	}

	@Column(name = "PAT_FORMER_DEP", length = 256)
	public String getPatFormerDep() {
		return this.patFormerDep;
	}

	public void setPatFormerDep(String patFormerDep) {
		this.patFormerDep = patFormerDep;
	}

	@Column(name = "PAT_FORMER_POSITION_STATUS", length = 256)
	public String getPatFormerPositionStatus() {
		return this.patFormerPositionStatus;
	}

	public void setPatFormerPositionStatus(String patFormerPositionStatus) {
		this.patFormerPositionStatus = patFormerPositionStatus;
	}

	@Column(name = "PAT_FORMER_POSITION_TYPE", length = 32)
	public String getPatFormerPositionType() {
		return this.patFormerPositionType;
	}

	public void setPatFormerPositionType(String patFormerPositionType) {
		this.patFormerPositionType = patFormerPositionType;
	}

	@Column(name = "PAT_FORMER_BUILDING", length = 64)
	public String getPatFormerBuilding() {
		return this.patFormerBuilding;
	}

	public void setPatFormerBuilding(String patFormerBuilding) {
		this.patFormerBuilding = patFormerBuilding;
	}

	@Column(name = "PAT_FORMER_FLOOR", length = 64)
	public String getPatFormerFloor() {
		return this.patFormerFloor;
	}

	public void setPatFormerFloor(String patFormerFloor) {
		this.patFormerFloor = patFormerFloor;
	}

	@Column(name = "PAT_FORMER_DESCRIPTION", length = 256)
	public String getPatFormerDescription() {
		return this.patFormerDescription;
	}

	public void setPatFormerDescription(String patFormerDescription) {
		this.patFormerDescription = patFormerDescription;
	}

	@Column(name = "ADMISSIONS_DOCTOR", length = 256)
	public String getAdmissionsDoctor() {
		return this.admissionsDoctor;
	}

	public void setAdmissionsDoctor(String admissionsDoctor) {
		this.admissionsDoctor = admissionsDoctor;
	}

	@Column(name = "ADMISSIONS_DOCTOR_ID", length = 32)
	public String getAdmissionsDoctorId() {
		return this.admissionsDoctorId;
	}

	public void setAdmissionsDoctorId(String admissionsDoctorId) {
		this.admissionsDoctorId = admissionsDoctorId;
	}

	@Column(name = "REFERRING_DOCTOR", length = 256)
	public String getReferringDoctor() {
		return this.referringDoctor;
	}

	public void setReferringDoctor(String referringDoctor) {
		this.referringDoctor = referringDoctor;
	}

	@Column(name = "REFERRING_DOCTOR_ID", length = 32)
	public String getReferringDoctorId() {
		return this.referringDoctorId;
	}

	public void setReferringDoctorId(String referringDoctorId) {
		this.referringDoctorId = referringDoctorId;
	}

	@Column(name = "CONSULTATION_DOCTOR", length = 256)
	public String getConsultationDoctor() {
		return this.consultationDoctor;
	}

	public void setConsultationDoctor(String consultationDoctor) {
		this.consultationDoctor = consultationDoctor;
	}

	@Column(name = "CONSULTATION_DOCTOR_ID", length = 32)
	public String getConsultationDoctorId() {
		return this.consultationDoctorId;
	}

	public void setConsultationDoctorId(String consultationDoctorId) {
		this.consultationDoctorId = consultationDoctorId;
	}

	@Column(name = "HOSPITAL_SERVICE", length = 32)
	public String getHospitalService() {
		return this.hospitalService;
	}

	public void setHospitalService(String hospitalService) {
		this.hospitalService = hospitalService;
	}

	@Column(name = "PAT_TEMP_POINT_OF_CARE", length = 256)
	public String getPatTempPointOfCare() {
		return this.patTempPointOfCare;
	}

	public void setPatTempPointOfCare(String patTempPointOfCare) {
		this.patTempPointOfCare = patTempPointOfCare;
	}

	@Column(name = "PAT_TEMP_ROOM", length = 256)
	public String getPatTempRoom() {
		return this.patTempRoom;
	}

	public void setPatTempRoom(String patTempRoom) {
		this.patTempRoom = patTempRoom;
	}

	@Column(name = "PAT_TEMP_BED", length = 256)
	public String getPatTempBed() {
		return this.patTempBed;
	}

	public void setPatTempBed(String patTempBed) {
		this.patTempBed = patTempBed;
	}

	@Column(name = "PAT_TEMP_DEP", length = 256)
	public String getPatTempDep() {
		return this.patTempDep;
	}

	public void setPatTempDep(String patTempDep) {
		this.patTempDep = patTempDep;
	}

	@Column(name = "PAT_TEMP_POSITION_STATUS", length = 256)
	public String getPatTempPositionStatus() {
		return this.patTempPositionStatus;
	}

	public void setPatTempPositionStatus(String patTempPositionStatus) {
		this.patTempPositionStatus = patTempPositionStatus;
	}

	@Column(name = "PAT_TEMP_POSITION_TYPE", length = 32)
	public String getPatTempPositionType() {
		return this.patTempPositionType;
	}

	public void setPatTempPositionType(String patTempPositionType) {
		this.patTempPositionType = patTempPositionType;
	}

	@Column(name = "PAT_TEMP_BUILDING", length = 64)
	public String getPatTempBuilding() {
		return this.patTempBuilding;
	}

	public void setPatTempBuilding(String patTempBuilding) {
		this.patTempBuilding = patTempBuilding;
	}

	@Column(name = "PAT_TEMP_FLOOR", length = 64)
	public String getPatTempFloor() {
		return this.patTempFloor;
	}

	public void setPatTempFloor(String patTempFloor) {
		this.patTempFloor = patTempFloor;
	}

	@Column(name = "PAT_TEMP_DESCRIPTION", length = 256)
	public String getPatTempDescription() {
		return this.patTempDescription;
	}

	public void setPatTempDescription(String patTempDescription) {
		this.patTempDescription = patTempDescription;
	}

	@Column(name = "PAT_ADMISSION_TEST", length = 256)
	public String getPatAdmissionTest() {
		return this.patAdmissionTest;
	}

	public void setPatAdmissionTest(String patAdmissionTest) {
		this.patAdmissionTest = patAdmissionTest;
	}

	@Column(name = "PAT_RE_ADMISSION", length = 32)
	public String getPatReAdmission() {
		return this.patReAdmission;
	}

	public void setPatReAdmission(String patReAdmission) {
		this.patReAdmission = patReAdmission;
	}

	@Column(name = "PAT_ADMISSION_SOURCE", length = 256)
	public String getPatAdmissionSource() {
		return this.patAdmissionSource;
	}

	public void setPatAdmissionSource(String patAdmissionSource) {
		this.patAdmissionSource = patAdmissionSource;
	}

	@Column(name = "PAT_AMBULATORY_STATUS", length = 256)
	public String getPatAmbulatoryStatus() {
		return this.patAmbulatoryStatus;
	}

	public void setPatAmbulatoryStatus(String patAmbulatoryStatus) {
		this.patAmbulatoryStatus = patAmbulatoryStatus;
	}

	@Column(name = "PAT_VIP", length = 32)
	public String getPatVip() {
		return this.patVip;
	}

	public void setPatVip(String patVip) {
		this.patVip = patVip;
	}

	@Column(name = "PAT_ADMISSION_DOCTORS", length = 256)
	public String getPatAdmissionDoctors() {
		return this.patAdmissionDoctors;
	}

	public void setPatAdmissionDoctors(String patAdmissionDoctors) {
		this.patAdmissionDoctors = patAdmissionDoctors;
	}

	@Column(name = "PAT_ADMISSION_DOCTORS_ID", length = 32)
	public String getPatAdmissionDoctorsId() {
		return this.patAdmissionDoctorsId;
	}

	public void setPatAdmissionDoctorsId(String patAdmissionDoctorsId) {
		this.patAdmissionDoctorsId = patAdmissionDoctorsId;
	}

	@Column(name = "PATIENT_CLASS", length = 64)
	public String getPatientClass() {
		return this.patientClass;
	}

	public void setPatientClass(String patientClass) {
		this.patientClass = patientClass;
	}

	@Column(name = "PATIENT_ID", length = 64)
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Column(name = "PAT_FINANCIAL_CLASS", length = 32)
	public String getPatFinancialClass() {
		return this.patFinancialClass;
	}

	public void setPatFinancialClass(String patFinancialClass) {
		this.patFinancialClass = patFinancialClass;
	}

	@Column(name = "ROOM_BED_COST_PRICE", length = 64)
	public String getRoomBedCostPrice() {
		return this.roomBedCostPrice;
	}

	public void setRoomBedCostPrice(String roomBedCostPrice) {
		this.roomBedCostPrice = roomBedCostPrice;
	}

	@Column(name = "COURTESY_CODE", length = 16)
	public String getCourtesyCode() {
		return this.courtesyCode;
	}

	public void setCourtesyCode(String courtesyCode) {
		this.courtesyCode = courtesyCode;
	}

	@Column(name = "CREDIT_RATING", length = 32)
	public String getCreditRating() {
		return this.creditRating;
	}

	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}

	@Column(name = "CONTRACT_CODE", length = 32)
	public String getContractCode() {
		return this.contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	@Column(name = "CONTRACT_CREATE_DATE")
	public String getContractCreateDate() {
		return this.contractCreateDate;
	}

	public void setContractCreateDate(String contractCreateDate) {
		this.contractCreateDate = contractCreateDate;
	}

	@Column(name = "CONTRACT_PRICE", length = 32)
	public String getContractPrice() {
		return this.contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_TIME", length = 32)
	public String getContractTime() {
		return this.contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}

	@Column(name = "INTEREST_RATE_CODE", length = 32)
	public String getInterestRateCode() {
		return this.interestRateCode;
	}

	public void setInterestRateCode(String interestRateCode) {
		this.interestRateCode = interestRateCode;
	}

	@Column(name = "BAD_DEBTS", length = 32)
	public String getBadDebts() {
		return this.badDebts;
	}

	public void setBadDebts(String badDebts) {
		this.badDebts = badDebts;
	}

	@Column(name = "BAD_DEBTS_CREATE_DATE")
	public String getBadDebtsCreateDate() {
		return this.badDebtsCreateDate;
	}

	public void setBadDebtsCreateDate(String badDebtsCreateDate) {
		this.badDebtsCreateDate = badDebtsCreateDate;
	}

	@Column(name = "BAD_DEBTS_CODE", length = 32)
	public String getBadDebtsCode() {
		return this.badDebtsCode;
	}

	public void setBadDebtsCode(String badDebtsCode) {
		this.badDebtsCode = badDebtsCode;
	}

	@Column(name = "BAD_DEBTS_PRICE", length = 32)
	public String getBadDebtsPrice() {
		return this.badDebtsPrice;
	}

	public void setBadDebtsPrice(String badDebtsPrice) {
		this.badDebtsPrice = badDebtsPrice;
	}

	@Column(name = "BAD_DEBTS__RESTORE_PRICE", length = 32)
	public String getBadDebtsRestorePrice() {
		return this.badDebtsRestorePrice;
	}

	public void setBadDebtsRestorePrice(String badDebtsRestorePrice) {
		this.badDebtsRestorePrice = badDebtsRestorePrice;
	}

	@Column(name = "PAT_ACCOUNT_VOIDED", length = 32)
	public String getPatAccountVoided() {
		return this.patAccountVoided;
	}

	public void setPatAccountVoided(String patAccountVoided) {
		this.patAccountVoided = patAccountVoided;
	}

	@Column(name = "PAT_ACCOUNT_VOIDED_DATE")
	public String getPatAccountVoidedDate() {
		return this.patAccountVoidedDate;
	}

	public void setPatAccountVoidedDate(String patAccountVoidedDate) {
		this.patAccountVoidedDate = patAccountVoidedDate;
	}

	@Column(name = "PAT_DISCHARGE_DISPOSITION", length = 32)
	public String getPatDischargeDisposition() {
		return this.patDischargeDisposition;
	}

	public void setPatDischargeDisposition(String patDischargeDisposition) {
		this.patDischargeDisposition = patDischargeDisposition;
	}

	@Column(name = "PAT_DISCHARGE_LOCATION", length = 256)
	public String getPatDischargeLocation() {
		return this.patDischargeLocation;
	}

	public void setPatDischargeLocation(String patDischargeLocation) {
		this.patDischargeLocation = patDischargeLocation;
	}

	@Column(name = "PAT_DIET_TYPE", length = 64)
	public String getPatDietType() {
		return this.patDietType;
	}

	public void setPatDietType(String patDietType) {
		this.patDietType = patDietType;
	}

	@Column(name = "PAT_SERVICE_AGENCIES", length = 64)
	public String getPatServiceAgencies() {
		return this.patServiceAgencies;
	}

	public void setPatServiceAgencies(String patServiceAgencies) {
		this.patServiceAgencies = patServiceAgencies;
	}

	@Column(name = "PAT_BED_STATUS", length = 32)
	public String getPatBedStatus() {
		return this.patBedStatus;
	}

	public void setPatBedStatus(String patBedStatus) {
		this.patBedStatus = patBedStatus;
	}

	@Column(name = "PAT_ACCOUNT_STATUS", length = 32)
	public String getPatAccountStatus() {
		return this.patAccountStatus;
	}

	public void setPatAccountStatus(String patAccountStatus) {
		this.patAccountStatus = patAccountStatus;
	}

	@Column(name = "PAT_DETER_POINT_OF_CARE", length = 256)
	public String getPatDeterPointOfCare() {
		return this.patDeterPointOfCare;
	}

	public void setPatDeterPointOfCare(String patDeterPointOfCare) {
		this.patDeterPointOfCare = patDeterPointOfCare;
	}

	@Column(name = "PAT_DETER_ROOM", length = 256)
	public String getPatDeterRoom() {
		return this.patDeterRoom;
	}

	public void setPatDeterRoom(String patDeterRoom) {
		this.patDeterRoom = patDeterRoom;
	}

	@Column(name = "PAT_DETER_BED", length = 256)
	public String getPatDeterBed() {
		return this.patDeterBed;
	}

	public void setPatDeterBed(String patDeterBed) {
		this.patDeterBed = patDeterBed;
	}

	@Column(name = "PAT_DETER_DEP", length = 256)
	public String getPatDeterDep() {
		return this.patDeterDep;
	}

	public void setPatDeterDep(String patDeterDep) {
		this.patDeterDep = patDeterDep;
	}

	@Column(name = "PAT_DETER_POSITION_STATUS", length = 256)
	public String getPatDeterPositionStatus() {
		return this.patDeterPositionStatus;
	}

	public void setPatDeterPositionStatus(String patDeterPositionStatus) {
		this.patDeterPositionStatus = patDeterPositionStatus;
	}

	@Column(name = "PAT_DETER_POSITION_TYPE", length = 32)
	public String getPatDeterPositionType() {
		return this.patDeterPositionType;
	}

	public void setPatDeterPositionType(String patDeterPositionType) {
		this.patDeterPositionType = patDeterPositionType;
	}

	@Column(name = "PAT_DETER_BUILDING", length = 64)
	public String getPatDeterBuilding() {
		return this.patDeterBuilding;
	}

	public void setPatDeterBuilding(String patDeterBuilding) {
		this.patDeterBuilding = patDeterBuilding;
	}

	@Column(name = "PAT_DETER_FLOOR", length = 64)
	public String getPatDeterFloor() {
		return this.patDeterFloor;
	}

	public void setPatDeterFloor(String patDeterFloor) {
		this.patDeterFloor = patDeterFloor;
	}

	@Column(name = "PAT_DETER_DESCRIPTION", length = 256)
	public String getPatDeterDescription() {
		return this.patDeterDescription;
	}

	public void setPatDeterDescription(String patDeterDescription) {
		this.patDeterDescription = patDeterDescription;
	}

	@Column(name = "PAT_FOR_TEMP_POINT_OF_CARE", length = 256)
	public String getPatForTempPointOfCare() {
		return this.patForTempPointOfCare;
	}

	public void setPatForTempPointOfCare(String patForTempPointOfCare) {
		this.patForTempPointOfCare = patForTempPointOfCare;
	}

	@Column(name = "PAT_FOR_TEMP_ROOM", length = 256)
	public String getPatForTempRoom() {
		return this.patForTempRoom;
	}

	public void setPatForTempRoom(String patForTempRoom) {
		this.patForTempRoom = patForTempRoom;
	}

	@Column(name = "PAT_FOR_TEMP_BED", length = 256)
	public String getPatForTempBed() {
		return this.patForTempBed;
	}

	public void setPatForTempBed(String patForTempBed) {
		this.patForTempBed = patForTempBed;
	}

	@Column(name = "PAT_FOR_TEMP_DEP", length = 256)
	public String getPatForTempDep() {
		return this.patForTempDep;
	}

	public void setPatForTempDep(String patForTempDep) {
		this.patForTempDep = patForTempDep;
	}

	@Column(name = "PAT_FOR_TEMP_POSITION_STATUS", length = 256)
	public String getPatForTempPositionStatus() {
		return this.patForTempPositionStatus;
	}

	public void setPatForTempPositionStatus(String patForTempPositionStatus) {
		this.patForTempPositionStatus = patForTempPositionStatus;
	}

	@Column(name = "PAT_FOR_TEMP_POSITION_TYPE", length = 32)
	public String getPatForTempPositionType() {
		return this.patForTempPositionType;
	}

	public void setPatForTempPositionType(String patForTempPositionType) {
		this.patForTempPositionType = patForTempPositionType;
	}

	@Column(name = "PAT_FOR_TEMP_BUILDING", length = 64)
	public String getPatForTempBuilding() {
		return this.patForTempBuilding;
	}

	public void setPatForTempBuilding(String patForTempBuilding) {
		this.patForTempBuilding = patForTempBuilding;
	}

	@Column(name = "PAT_FOR_TEMP_FLOOR", length = 64)
	public String getPatForTempFloor() {
		return this.patForTempFloor;
	}

	public void setPatForTempFloor(String patForTempFloor) {
		this.patForTempFloor = patForTempFloor;
	}

	@Column(name = "PAT_FOR_TEMP_DESCRIPTION", length = 256)
	public String getPatForTempDescription() {
		return this.patForTempDescription;
	}

	public void setPatForTempDescription(String patForTempDescription) {
		this.patForTempDescription = patForTempDescription;
	}

	@Column(name = "ADMIT_DATE", length = 64)
	public Date getAdmitDate() {
		return this.admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	@Column(name = "DISCHARGE_DATE", length = 64)
	public Date getDischargeDate() {
		return this.dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Column(name = "PAT_DIFFERENCE", length = 32)
	public String getPatDifference() {
		return this.patDifference;
	}

	public void setPatDifference(String patDifference) {
		this.patDifference = patDifference;
	}

	@Column(name = "PAT_TOTAL_COST", length = 32)
	public String getPatTotalCost() {
		return this.patTotalCost;
	}

	public void setPatTotalCost(String patTotalCost) {
		this.patTotalCost = patTotalCost;
	}

	@Column(name = "PAT_TOTAL_DISPATCH", length = 32)
	public String getPatTotalDispatch() {
		return this.patTotalDispatch;
	}

	public void setPatTotalDispatch(String patTotalDispatch) {
		this.patTotalDispatch = patTotalDispatch;
	}

	@Column(name = "PAT_TOTAL_AMOUNT_PAYABLE", length = 32)
	public String getPatTotalAmountPayable() {
		return this.patTotalAmountPayable;
	}

	public void setPatTotalAmountPayable(String patTotalAmountPayable) {
		this.patTotalAmountPayable = patTotalAmountPayable;
	}

	@Column(name = "PAT_SPARE_ID", length = 32)
	public String getPatSpareId() {
		return this.patSpareId;
	}

	public void setPatSpareId(String patSpareId) {
		this.patSpareId = patSpareId;
	}

	@Column(name = "PAT_VISIT_LOGO", length = 32)
	public String getPatVisitLogo() {
		return this.patVisitLogo;
	}

	public void setPatVisitLogo(String patVisitLogo) {
		this.patVisitLogo = patVisitLogo;
	}

	@Column(name = "OTHER_MEDICAL_INSTITUTIONS", length = 256)
	public String getOtherMedicalInstitutions() {
		return this.otherMedicalInstitutions;
	}

	public void setOtherMedicalInstitutions(String otherMedicalInstitutions) {
		this.otherMedicalInstitutions = otherMedicalInstitutions;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_ID")
	public String getCreateId() {
		return this.createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	@Column(name = "VOIDED_DATE")
	public Date getVoidedDate() {
		return this.voidedDate;
	}

	public void setVoidedDate(Date voidedDate) {
		this.voidedDate = voidedDate;
	}

	@Column(name = "VOIDED_ID")
	public String getVoidedId() {
		return this.voidedId;
	}

	public void setVoidedId(String voidedId) {
		this.voidedId = voidedId;
	}

	@Column(name = "MODIFY_DATE")
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	//@Join

	@Column(name = "MODIFY_ID")
	public String getModifyId() {
		return this.modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name = "CUSTOM1", length = 256)
	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	@Column(name = "CUSTOM2", length = 256)
	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	@Column(name = "CUSTOM3", length = 256)
	public String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Column(name = "CUSTOM4", length = 256)
	public String getCustom4() {
		return this.custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	@Column(name = "CUSTOM5", length = 256)
	public String getCustom5() {
		return this.custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	@Column(name = "PAT_NURSE_CODE", length = 32)
	public String getPatNurseCode() {
		return this.patNurseCode;
	}

	public void setPatNurseCode(String patNurseCode) {
		this.patNurseCode = patNurseCode;
	}

	@Column(name = "PAT_NURSE_NAME", length = 32)
	public String getPatNurseName() {
		return this.patNurseName;
	}

	public void setPatNurseName(String patNurseName) {
		this.patNurseName = patNurseName;
	}

	@Column(name = "TEND", length = 32)
	public String getTend() {
		return this.tend;
	}

	public void setTend(String tend) {
		this.tend = tend;
	}

	@Column(name = "BABY_FLAG")
	public String getBabyFlag() {
		return this.babyFlag;
	}

	public void setBabyFlag(String babyFlag) {
		this.babyFlag = babyFlag;
	}

	@Column(name = "ADMIT_WEIGHT")
	public String getAdmitWeight() {
		return this.admitWeight;
	}

	public void setAdmitWeight(String admitWeight) {
		this.admitWeight = admitWeight;
	}

	@Column(name = "BIRTH_WEIGHT")
	public String getBirthWeight() {
		return this.birthWeight;
	}

	public void setBirthWeight(String birthWeight) {
		this.birthWeight = birthWeight;
	}

	@Column(name = "OPER_CODE", length = 32)
	public String getOperCode() {
		return this.operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}

	@Column(name = "OPER_DATE")
	public Date getOperDate() {
		return this.operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

	@Column(name = "PREFIX", length = 64)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "INSURANCE_TYPE", length = 64)
	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Column(name = "CONTACT_PERSON", length = 64)
	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Column(name = "CONTACT_RELATIONS", length = 64)
	public String getContactRelations() {
		return this.contactRelations;
	}

	public void setContactRelations(String contactRelations) {
		this.contactRelations = contactRelations;
	}

	@Column(name = "CONTACT_ADDRESS", length = 512)
	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	@Column(name = "CONTACT_PHONE", length = 64)
	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Column(name = "PAT_CATEGORY_NAME", length = 64)
	public String getPatCategoryName() {
		return this.patCategoryName;
	}

	public void setPatCategoryName(String patCategoryName) {
		this.patCategoryName = patCategoryName;
	}

	@Column(name = "GENDER_NAME", length = 64)
	public String getGenderName() {
		return this.genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	@Column(name = "PAY_RATE", length = 64)
	public String getPayRate() {
		return this.payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}

	@Column(name = "DISCHARGE_NAME", length = 64)
	public String getDischargeName() {
		return this.dischargeName;
	}

	public void setDischargeName(String dischargeName) {
		this.dischargeName = dischargeName;
	}

	@Column(name = "INSURANCE_NAME", length = 64)
	public String getInsuranceName() {
		return this.insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	@Column(name = "ADMISSION_NAME", length = 64)
	public String getAdmissionName() {
		return this.admissionName;
	}

	public void setAdmissionName(String admissionName) {
		this.admissionName = admissionName;
	}

	@Column(name = "IP_STATUS_NAME", length = 64)
	public String getIpStatusName() {
		return this.ipStatusName;
	}

	public void setIpStatusName(String ipStatusName) {
		this.ipStatusName = ipStatusName;
	}

	@Column(name = "DIFICULTY_NAME", length = 64)
	public String getDificultyName() {
		return this.dificultyName;
	}

	public void setDificultyName(String dificultyName) {
		this.dificultyName = dificultyName;
	}

	@Column(name = "ADMIT_WAY_NAME", length = 64)
	public String getAdmitWayName() {
		return this.admitWayName;
	}

	public void setAdmitWayName(String admitWayName) {
		this.admitWayName = admitWayName;
	}

	@Column(name = "ADMIT_WEIGHT_UNIT", length = 64)
	public String getAdmitWeightUnit() {
		return this.admitWeightUnit;
	}

	public void setAdmitWeightUnit(String admitWeightUnit) {
		this.admitWeightUnit = admitWeightUnit;
	}

	@Column(name = "BABY_WEIGHT_UNIT", length = 64)
	public String getBabyWeightUnit() {
		return this.babyWeightUnit;
	}

	public void setBabyWeightUnit(String babyWeightUnit) {
		this.babyWeightUnit = babyWeightUnit;
	}

	@Column(name = "MEDICINELIMITAMOUNT", precision = 22, scale = 0)
	public BigDecimal getMedicinelimitamount() {
		return this.medicinelimitamount;
	}

	public void setMedicinelimitamount(BigDecimal medicinelimitamount) {
		this.medicinelimitamount = medicinelimitamount;
	}

	@Column(name = "SICKBEDLIMITAMOUNT", precision = 22, scale = 0)
	public BigDecimal getSickbedlimitamount() {
		return this.sickbedlimitamount;
	}

	public void setSickbedlimitamount(BigDecimal sickbedlimitamount) {
		this.sickbedlimitamount = sickbedlimitamount;
	}

	@Column(name = "EXAMINELIMITAMOUNT", precision = 22, scale = 0)
	public BigDecimal getExaminelimitamount() {
		return this.examinelimitamount;
	}

	public void setExaminelimitamount(BigDecimal examinelimitamount) {
		this.examinelimitamount = examinelimitamount;
	}

	@Column(name = "CURELIMITAMOUNT", precision = 22, scale = 0)
	public BigDecimal getCurelimitamount() {
		return this.curelimitamount;
	}

	public void setCurelimitamount(BigDecimal curelimitamount) {
		this.curelimitamount = curelimitamount;
	}

	@Column(name = "HIUP_STATUS", length = 16)
	public String getHiupStatus() {
		return this.hiupStatus;
	}

	public void setHiupStatus(String hiupStatus) {
		this.hiupStatus = hiupStatus;
	}

	@Column(name = "HIUP_ERROR_INFO", length = 4000)
	public String getHiupErrorInfo() {
		return this.hiupErrorInfo;
	}

	public void setHiupErrorInfo(String hiupErrorInfo) {
		this.hiupErrorInfo = hiupErrorInfo;
	}

	@Column(name = "ADMISSION_DOMAIN", length = 64)
	public String getAdmissionDomain() {
		return this.admissionDomain;
	}

	public void setAdmissionDomain(String admissionDomain) {
		this.admissionDomain = admissionDomain;
	}

	@Column(name = "ADMISSION_SOURCE_DOMAIN", length = 64)
	public String getAdmissionSourceDomain() {
		return this.admissionSourceDomain;
	}

	public void setAdmissionSourceDomain(String admissionSourceDomain) {
		this.admissionSourceDomain = admissionSourceDomain;
	}

	@Column(name = "ADMISSION_SOURCE_NAME", length = 64)
	public String getAdmissionSourceName() {
		return this.admissionSourceName;
	}

	public void setAdmissionSourceName(String admissionSourceName) {
		this.admissionSourceName = admissionSourceName;
	}

	@Column(name = "PATIENT_CLASS_NAME", length = 64)
	public String getPatientClassName() {
		return this.patientClassName;
	}

	public void setPatientClassName(String patientClassName) {
		this.patientClassName = patientClassName;
	}

	@Column(name = "PATIENT_CLASS_DOMAIN", length = 64)
	public String getPatientClassDomain() {
		return this.patientClassDomain;
	}

	public void setPatientClassDomain(String patientClassDomain) {
		this.patientClassDomain = patientClassDomain;
	}

	@Column(name = "IP_STATUS_DOMAIN", length = 64)
	public String getIpStatusDomain() {
		return this.ipStatusDomain;
	}

	public void setIpStatusDomain(String ipStatusDomain) {
		this.ipStatusDomain = ipStatusDomain;
	}

	@Column(name = "DIFICULTY_DOMAIN", length = 64)
	public String getDificultyDomain() {
		return this.dificultyDomain;
	}

	public void setDificultyDomain(String dificultyDomain) {
		this.dificultyDomain = dificultyDomain;
	}

	@Column(name = "DISCHARGE_DOMAIN", length = 64)
	public String getDischargeDomain() {
		return this.dischargeDomain;
	}

	public void setDischargeDomain(String dischargeDomain) {
		this.dischargeDomain = dischargeDomain;
	}

	@Column(name = "ACCOUNT_STATUS_NAME", length = 64)
	public String getAccountStatusName() {
		return this.accountStatusName;
	}

	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}

	@Column(name = "ACCOUNT_STATUS_DOMAIN", length = 64)
	public String getAccountStatusDomain() {
		return this.accountStatusDomain;
	}

	public void setAccountStatusDomain(String accountStatusDomain) {
		this.accountStatusDomain = accountStatusDomain;
	}

	@Column(name = "GENDER_DOMAIN", length = 64)
	public String getGenderDomain() {
		return this.genderDomain;
	}

	public void setGenderDomain(String genderDomain) {
		this.genderDomain = genderDomain;
	}

	@Column(name = "PAT_CATEGORY_SYSTEM", length = 64)
	public String getPatCategorySystem() {
		return this.patCategorySystem;
	}

	public void setPatCategorySystem(String patCategorySystem) {
		this.patCategorySystem = patCategorySystem;
	}

	@Column(name = "MOTHERS_ID")
	public String getMothersID() {
		return mothersID;
	}

	public void setMothersID(String mothersID) {
		this.mothersID = mothersID;
	}

	@Column(name = "MOTHERS_FLOW_ID")
	public String getMothersFlowID() {
		return mothersFlowID;
	}

	public void setMothersFlowID(String mothersFlowID) {
		this.mothersFlowID = mothersFlowID;
	}

	@Column(name = "MOTHERS_FLOW_DOMAIN")
	public String getMothersFlowDomain() {
		return mothersFlowDomain;
	}

	public void setMothersFlowDomain(String mothersFlowDomain) {
		this.mothersFlowDomain = mothersFlowDomain;
	}

	@Column(name = "MOTHERS_NAME")
	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	@Column(name = "MOTHERS_DOMAIN")
	public String getMothersDomain() {
		return mothersDomain;
	}

	public void setMothersDomain(String mothersDomain) {
		this.mothersDomain = mothersDomain;
	}

	@Column(name = "PATIENT_SOURCE_NAME")
	public String getPatientSourceName() {
		return patientSourceName;
	}

	public void setPatientSourceName(String patientSourceName) {
		this.patientSourceName = patientSourceName;
	}

	@Column(name = "OLD_PATIENT_ID")
	public String getOldPatientId() {
		return oldPatientId;
	}

	public void setOldPatientId(String oldPatientId) {
		this.oldPatientId = oldPatientId;
	}

	@Column(name = "OLD_PATIENT_DOMAIN")
	public String getOldPatientDomain() {
		return oldPatientDomain;
	}

	public void setOldPatientDomain(String oldPatientDomain) {
		this.oldPatientDomain = oldPatientDomain;
	}

	@Column(name = "OLD_VISIT_FLOW_ID")
	public String getOldVisitFlowId() {
		return oldVisitFlowId;
	}

	public void setOldVisitFlowId(String oldVisitFlowId) {
		this.oldVisitFlowId = oldVisitFlowId;
	}

	@Column(name = "OLD_VISIT_FLOW_DOMAIN")
	public String getOldVisitFlowDomain() {
		return oldVisitFlowDomain;
	}

	public void setOldVisitFlowDomain(String oldVisitFlowDomain) {
		this.oldVisitFlowDomain = oldVisitFlowDomain;
	}

	@Column(name = "OLD_VISIT_ID")
	public String getOldVisitId() {
		return oldVisitId;
	}

	public void setOldVisitId(String oldVisitId) {
		this.oldVisitId = oldVisitId;
	}

	@Column(name = "OLD_PERSON_ID")
	public String getOldPersonId() {
		return oldPersonId;
	}

	public void setOldPersonId(String oldPersonId) {
		this.oldPersonId = oldPersonId;
	}

	@Column(name = "OLD_STATUS")
	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	@Column(name = "OLD_INFO")
	public String getOldInfo() {
		return oldInfo;
	}

	public void setOldInfo(String oldInfo) {
		this.oldInfo = oldInfo;
	}

	@Column(name = "ISEMERGENCY")
	public String getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(String isEmergency) {
		this.isEmergency = isEmergency;
	}

	@Column(name = "PAT_IP_TIMES")
	public String getPatIpTimes() {
		return patIpTimes;
	}

	public void setPatIpTimes(String patIpTimes) {
		this.patIpTimes = patIpTimes;
	}

	@Column(name = "DIAGNOSE_ICD")
	public String getDiagnoseIcd() {
		return diagnoseIcd;
	}

	public void setDiagnoseIcd(String diagnoseIcd) {
		this.diagnoseIcd = diagnoseIcd;
	}

	@Column(name = "DIAGNOSE_NAME")
	public String getDiagnoseName() {
		return diagnoseName;
	}

	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}

	@Column(name = "NOON_CODE")
	public String getNoonCode() {
		return noonCode;
	}

	public void setNoonCode(String noonCode) {
		this.noonCode = noonCode;
	}

	@Column(name = "PAYKIND_CODE")
	public String getPaykindCode() {
		return paykindCode;
	}

	public void setPaykindCode(String paykindCode) {
		this.paykindCode = paykindCode;
	}

	@Column(name = "PAYKIND_NAME")
	public String getPaykindName() {
		return paykindName;
	}

	public void setPaykindName(String paykindName) {
		this.paykindName = paykindName;
	}

	@Column(name = "SCHEMA_NO")
	public String getSchemaNo() {
		return schemaNo;
	}

	public void setSchemaNo(String schemaNo) {
		this.schemaNo = schemaNo;
	}

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "SEENO")
	public String getSeeNo() {
		return seeNo;
	}

	public void setSeeNo(String seeNo) {
		this.seeNo = seeNo;
	}

	@Column(name = "BEGIN_TIME")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "YNBOOK")
	public String getYnBook() {
		return ynBook;
	}

	public void setYnBook(String ynBook) {
		this.ynBook = ynBook;
	}

	@Column(name = "YNFR")
	public String getYNFR() {
		return ynfr;
	}

	public void setYNFR(String ynfr) {
		this.ynfr = ynfr;
	}

	@Column(name = "APPEND_FLAG")
	public String getAppendFlag() {
		return appendFlag;
	}

	public void setAppendFlag(String appendFlag) {
		this.appendFlag = appendFlag;
	}

	@Column(name = "YNSEE")
	public String getYnSee() {
		return ynSee;
	}

	public void setYnSee(String ynSee) {
		this.ynSee = ynSee;
	}

	@Column(name = "SEE_DATE")
	public Date getSeeDate() {
		return seeDate;
	}

	public void setSeeDate(Date seeDate) {
		this.seeDate = seeDate;
	}

	@Column(name = "TRIAGE_FLAG")
	public String getTriageFlag() {
		return triageFlag;
	}

	public void setTriageFlag(String triageFlag) {
		this.triageFlag = triageFlag;
	}

	@Column(name = "TRIAGE_OPCD")
	public String getTriageOpcd() {
		return triageOpcd;
	}

	public void setTriageOpcd(String triageOpcd) {
		this.triageOpcd = triageOpcd;
	}

	@Column(name = "TRIAGE_DATE")
	public Date getTriageDate() {
		return triageDate;
	}

	public void setTriageDate(Date triageDate) {
		this.triageDate = triageDate;
	}

	@Column(name = "SEE_DPCD")
	public String getSeeDpcd() {
		return seeDpcd;
	}

	public void setSeeDpcd(String seeDpcd) {
		this.seeDpcd = seeDpcd;
	}

	@Column(name = "SEE_DOCD")
	public String getSeeDocd() {
		return seeDocd;
	}

	public void setSeeDocd(String seeDocd) {
		this.seeDocd = seeDocd;
	}

	@Column(name = "OUT_PATIENT_STATUS_A")
	public String getOutPatientStatusA() {
		return outPatientStatusA;
	}

	public void setOutPatientStatusA(String outPatientStatusA) {
		this.outPatientStatusA = outPatientStatusA;
	}

	@Column(name = "OUT_PATIENT_STATUS_B")
	public String getOutPatientStatusB() {
		return outPatientStatusB;
	}

	public void setOutPatientStatusB(String outPatientStatusB) {
		this.outPatientStatusB = outPatientStatusB;
	}

	@Column(name = "OUT_PATIENT_STATUS_C")
	public String getOutPatientStatusC() {
		return outPatientStatusC;
	}

	public void setOutPatientStatusC(String outPatientStatusC) {
		this.outPatientStatusC = outPatientStatusC;
	}

	@Column(name = "IN_PATIENT_STATUS_A")
	public String getInPatientStatusA() {
		return inPatientStatusA;
	}

	public void setInPatientStatusA(String inPatientStatusA) {
		this.inPatientStatusA = inPatientStatusA;
	}

	@Column(name = "IN_PATIENT_STATUS_B")
	public String getInPatientStatusB() {
		return inPatientStatusB;
	}

	public void setInPatientStatusB(String inPatientStatusB) {
		this.inPatientStatusB = inPatientStatusB;
	}

	@Column(name = "IN_PATIENT_STATUS_C")
	public String getInPatientStatusC() {
		return inPatientStatusC;
	}

	public void setInPatientStatusC(String inPatientStatusC) {
		this.inPatientStatusC = inPatientStatusC;
	}

	@Column(name = "RELEVANCE_ID")
	public String getRelevanceID() {
		return relevanceID;
	}

	public void setRelevanceID(String relevanceID) {
		this.relevanceID = relevanceID;
	}

	@Column(name = "RELEVANCE_DOMAIN")
	public String getRelevanceDomain() {
		return relevanceDomain;
	}

	public void setRelevanceDomain(String relevanceDomain) {
		this.relevanceDomain = relevanceDomain;
	}

	@Column(name = "RELEVANCE_NAME")
	public String getRelevanceName() {
		return relevanceName;
	}

	public void setRelevanceName(String relevanceName) {
		this.relevanceName = relevanceName;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PatientVisit))
			return false;
		PatientVisit castOther = (PatientVisit) other;

		return ((this.getVisitId() == castOther.getVisitId()) || (this
				.getVisitId() != null
				&& castOther.getVisitId() != null && this.getVisitId().equals(
				castOther.getVisitId())))
				&& ((this.getPersonId() == castOther.getPersonId()) || (this
				.getPersonId() != null
				&& castOther.getPersonId() != null && this
				.getPersonId().equals(castOther.getPersonId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getVisitId() == null ? 0 : this.getVisitId().hashCode());
		result = 37 * result
				+ (getPersonId() == null ? 0 : this.getPersonId().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("visitID", visitId)
				.append("personID", personId)
				.append("patCategory", patCategory)
				.append("patCurrentRoom", patCurrentRoom)
				.append("patCurrentBed", patCurrentBed)
				.toString();
	}

	public String toStringLong() {
		return new ToStringBuilder(this)
				.append("visitId", visitId)
				.append("personId", personId)
				.append("personDomain", personDomain)
				.append("visitFlowId", visitFlowId)
				.append("visitFlowDomain", visitFlowDomain)
				.append("hospitalDomain", hospitalDomain)
				.append("patCategory", patCategory)
				.append("patCurrentPointOfCare", patCurrentPointOfCare)
				.append("patCurrentRoom", patCurrentRoom)
				.append("patCurrentBed", patCurrentBed)
				.append("patCuurentDep", patCuurentDep)
				.append("patCurrentPositionStatus", patCurrentPositionStatus)
				.append("patCurrentPositionType", patCurrentPositionType)
				.append("patCurrentBuilding", patCurrentBuilding)
				.append("patCurrentFloor", patCurrentFloor)
				.append("patCuurentDescription", patCuurentDescription)
				.append("patAdmissionType", patAdmissionType)
				.append("patAdmissionNumber", patAdmissionNumber)
				.append("patFormerPointOfCare", patFormerPointOfCare)
				.append("patFormerRoom", patFormerRoom)
				.append("patFormerBed", patFormerBed)
				.append("patFormerDep", patFormerDep)
				.append("patFormerPositionStatus", patFormerPositionStatus)
				.append("patFormerPositionType", patFormerPositionType)
				.append("patFormerBuilding", patFormerBuilding)
				.append("patFormerFloor", patFormerFloor)
				.append("patFormerDescription", patFormerDescription)
				.append("admissionsDoctor", admissionsDoctor)
				.append("admissionsDoctorId", admissionsDoctorId)
				.append("referringDoctor", referringDoctor)
				.append("referringDoctorId", referringDoctorId)
				.append("consultationDoctor", consultationDoctor)
				.append("consultationDoctorId", consultationDoctorId)
				.append("hospitalService", hospitalService)
				.append("patTempPointOfCare", patTempPointOfCare)
				.append("patTempRoom", patTempRoom)
				.append("patTempBed", patTempBed)
				.append("patTempDep", patTempDep)
				.append("patTempPositionStatus", patTempPositionStatus)
				.append("patTempPositionType", patTempPositionType)
				.append("patTempBuilding", patTempBuilding)
				.append("patTempFloor", patTempFloor)
				.append("patTempDescription", patTempDescription)
				.append("patAdmissionTest", patAdmissionTest)
				.append("patReAdmission", patReAdmission)
				.append("patAdmissionSource", patAdmissionSource)
				.append("patAmbulatoryStatus", patAmbulatoryStatus)
				.append("patVip", patVip)
				.append("patAdmissionDoctors", patAdmissionDoctors)
				.append("patAdmissionDoctorsId", patAdmissionDoctorsId)
				.append("patientClass", patientClass)
				.append("patientId", patientId)
				.append("patFinancialClass", patFinancialClass)
				.append("roomBedCostPrice", roomBedCostPrice)
				.append("courtesyCode", courtesyCode)
				.append("creditRating", creditRating)
				.append("contractCode", contractCode)
				.append("contractCreateDate", contractCreateDate)
				.append("contractPrice", contractPrice)
				.append("contractTime", contractTime)
				.append("interestRateCode", interestRateCode)
				.append("badDebts", badDebts)
				.append("badDebtsCreateDate", badDebtsCreateDate)
				.append("badDebtsCode", badDebtsCode)
				.append("badDebtsPrice", badDebtsPrice)
				.append("badDebtsRestorePrice", badDebtsRestorePrice)
				.append("patAccountVoided", patAccountVoided)
				.append("patAccountVoidedDate", patAccountVoidedDate)
				.append("patDischargeDisposition", patDischargeDisposition)
				.append("patDischargeLocation", patDischargeLocation)
				.append("patDietType", patDietType)
				.append("patServiceAgencies", patServiceAgencies)
				.append("patBedStatus", patBedStatus)
				.append("patAccountStatus", patAccountStatus)
				.append("patDeterPointOfCare", patDeterPointOfCare)
				.append("patDeterRoom", patDeterRoom)
				.append("patDeterBed", patDeterBed)
				.append("patDeterDep", patDeterDep)
				.append("patDeterPositionStatus", patDeterPositionStatus)
				.append("patDeterPositionType", patDeterPositionType)
				.append("patDeterBuilding", patDeterBuilding)
				.append("patDeterFloor", patDeterFloor)
				.append("patDeterDescription", patDeterDescription)
				.append("patForTempPointOfCare", patForTempPointOfCare)
				.append("patForTempRoom", patForTempRoom)
				.append("patForTempBed", patForTempBed)
				.append("patForTempDep", patForTempDep)
				.append("patForTempPositionStatus", patForTempPositionStatus)
				.append("patForTempPositionType", patForTempPositionType)
				.append("patForTempBuilding", patForTempBuilding)
				.append("patForTempFloor", patForTempFloor)
				.append("patForTempDescription", patForTempDescription)
				.append("admitDate", admitDate)
				.append("dischargeDate", dischargeDate)
				.append("patDifference", patDifference)
				.append("patTotalCost", patTotalCost)
				.append("patTotalDispatch", patTotalDispatch)
				.append("patTotalAmountPayable", patTotalAmountPayable)
				.append("patSpareId", patSpareId)
				.append("patVisitLogo", patVisitLogo)
				.append("otherMedicalInstitutions", otherMedicalInstitutions)
				.append("createDate", createDate)
				.append("createId", createId)
				.append("voidedDate", voidedDate)
				.append("voidedId", voidedId)
				.append("modifyDate", modifyDate)
				.append("modifyId", modifyId)
				.append("custom1", custom1)
				.append("custom2", custom2)
				.append("custom3", custom3)
				.append("custom4", custom4)
				.append("custom5", custom5)
				.append("patNurseCode", patNurseCode)
				.append("patNurseName", patNurseName)
				.append("tend", tend)
				.append("babyFlag", babyFlag)
				.append("admitWeight", admitWeight)
				.append("birthWeight", birthWeight)
				.append("operCode", operCode)
				.append("operDate", operDate)
				.append("prefix", prefix)
				.append("insuranceType", insuranceType)
				.append("contactPerson", contactPerson)
				.append("contactRelations", contactRelations)
				.append("contactAddress", contactAddress)
				.append("contactPhone", contactPhone)
				.append("patCategoryName", patCategoryName)
				.append("genderName", genderName)
				.append("payRate", payRate)
				.append("dischargeName", dischargeName)
				.append("insuranceName", insuranceName)
				.append("admissionName", admissionName)
				.append("ipStatusName", ipStatusName)
				.append("dificultyName", dificultyName)
				.append("admitWayName", admitWayName)
				.append("admitWeightUnit", admitWeightUnit)
				.append("babyWeightUnit", babyWeightUnit)
				.append("medicinelimitamount", medicinelimitamount)
				.append("sickbedlimitamount", sickbedlimitamount)
				.append("examinelimitamount", examinelimitamount)
				.append("curelimitamount", curelimitamount)
				.append("hiupStatus", hiupStatus)
				.append("hiupErrorInfo", hiupErrorInfo)
				.append("admissionDomain", admissionDomain)
				.append("admissionSourceDomain", admissionSourceDomain)
				.append("admissionSourceName", admissionSourceName)
				.append("patientClassName", patientClassName)
				.append("patientClassDomain", patientClassDomain)
				.append("ipStatusDomain", ipStatusDomain)
				.append("dificultyDomain", dificultyDomain)
				.append("dischargeDomain", dischargeDomain)
				.append("accountStatusName", accountStatusName)
				.append("accountStatusDomain", accountStatusDomain)
				.append("genderDomain", genderDomain)
				.append("patCategorySystem", patCategorySystem)
				.append("mothersID", mothersID)
				.append("mothersFlowID", mothersFlowID)
				.append("mothersFlowDomain", mothersFlowDomain)
				.append("mothersName", mothersName)
				.append("mothersDomain", mothersDomain)
				.append("patientSourceName", patientSourceName)
				.append("oldPatientId", oldPatientId)
				.append("oldPatientDomain", oldPatientDomain)
				.append("oldVisitFlowId", oldVisitFlowId)
				.append("oldVisitFlowDomain", oldVisitFlowDomain)
				.append("oldVisitId", oldVisitId)
				.append("oldPersonId", oldPersonId)
				.append("oldStatus", oldStatus)
				.append("oldInfo", oldInfo)
				.append("isEmergency", isEmergency)
				.append("patIpTimes", patIpTimes)
				.append("diagnoseIcd", diagnoseIcd)
				.append("diagnoseName", diagnoseName)
				.append("noonCode", noonCode)
				.append("paykindCode", paykindCode)
				.append("paykindName", paykindName)
				.append("schemaNo", schemaNo)
				.append("orderNo", orderNo)
				.append("seeNo", seeNo)
				.append("beginTime", beginTime)
				.append("endTime", endTime)
				.append("ynBook", ynBook)
				.append("ynfr", ynfr)
				.append("appendFlag", appendFlag)
				.append("ynSee", ynSee)
				.append("seeDate", seeDate)
				.append("triageFlag", triageFlag)
				.append("triageOpcd", triageOpcd)
				.append("triageDate", triageDate)
				.append("seeDpcd", seeDpcd)
				.append("seeDocd", seeDocd)
				.append("outPatientStatusA", outPatientStatusA)
				.append("outPatientStatusB", outPatientStatusB)
				.append("outPatientStatusC", outPatientStatusC)
				.append("inPatientStatusA", inPatientStatusA)
				.append("inPatientStatusB", inPatientStatusB)
				.append("inPatientStatusC", inPatientStatusC)
				.append("relevanceID", relevanceID)
				.append("relevanceDomain", relevanceDomain)
				.append("relevanceName", relevanceName)
				.toString();
	}
	public StringBuffer toStringLong2() {
		StringBuffer str = new StringBuffer("{");
		str.append("'visitId':"+visitId+",")
				.append("'personId':"+personId+",")
				.append("'personDomain':"+personDomain+",")
				.append("'visitFlowId':"+visitFlowId+",")
				.append("'visitFlowDomain':"+visitFlowDomain+",")
				.append("'hospitalDomain':"+hospitalDomain+",")
				.append("'patCategory':"+patCategory+",")
				.append("'patCurrentPointOfCare':"+patCurrentPointOfCare+",")
				.append("'patCurrentRoom':"+patCurrentRoom+",")
				.append("'patCurrentBed':"+patCurrentBed+",")
				.append("'patCuurentDep':"+patCuurentDep+",")
				.append("'patCurrentPositionStatus':"+patCurrentPositionStatus+",")
				.append("'patCurrentPositionType':"+patCurrentPositionType+",")
				.append("'patCurrentBuilding':"+patCurrentBuilding+",")
				.append("'patCurrentFloor':"+patCurrentFloor+",")
				.append("'patCuurentDescription':"+patCuurentDescription+",")
				.append("'patAdmissionType':"+patAdmissionType+",")
				.append("'patAdmissionNumber':"+patAdmissionNumber+",")
				.append("'patFormerPointOfCare':"+patFormerPointOfCare+",")
				.append("'patFormerRoom':"+patFormerRoom+",")
				.append("'patFormerBed':"+patFormerBed+",")
				.append("'patFormerDep':"+patFormerDep+",")
				.append("'patFormerPositionStatus':"+patFormerPositionStatus+",")
				.append("'patFormerPositionType':"+patFormerPositionType+",")
				.append("'patFormerBuilding':"+patFormerBuilding+",")
				.append("'patFormerFloor':"+patFormerFloor+",")
				.append("'patFormerDescription':"+patFormerDescription+",")
				.append("'admissionsDoctor':"+admissionsDoctor+",")
				.append("'admissionsDoctorId':"+admissionsDoctorId+",")
				.append("'referringDoctor':"+referringDoctor+",")
				.append("'referringDoctorId':"+referringDoctorId+",")
				.append("'consultationDoctor':"+consultationDoctor+",")
				.append("'consultationDoctorId':"+consultationDoctorId+",")
				.append("'hospitalService':"+hospitalService+",")
				.append("'patTempPointOfCare':"+patTempPointOfCare+",")
				.append("'patTempRoom':"+patTempRoom+",")
				.append("'patTempBed':"+patTempBed+",")
				.append("'patTempDep':"+patTempDep+",")
				.append("'patTempPositionStatus':"+patTempPositionStatus+",")
				.append("'patTempPositionType':"+patTempPositionType+",")
				.append("'patTempBuilding':"+patTempBuilding+",")
				.append("'patTempFloor':"+patTempFloor+",")
				.append("'patTempDescription':"+patTempDescription+",")
				.append("'patAdmissionTest':"+patAdmissionTest+",")
				.append("'patReAdmission':"+patReAdmission+",")
				.append("'patAdmissionSource':"+patAdmissionSource+",")
				.append("'patAmbulatoryStatus':"+patAmbulatoryStatus+",")
				.append("'patVip':"+patVip+",")
				.append("'patAdmissionDoctors':"+patAdmissionDoctors+",")
				.append("'patAdmissionDoctorsId':"+patAdmissionDoctorsId+",")
				.append("'patientClass':"+patientClass+",")
				.append("'patientId':"+patientId+",")
				.append("'patFinancialClass':"+patFinancialClass+",")
				.append("'roomBedCostPrice':"+roomBedCostPrice+",")
				.append("'courtesyCode':"+courtesyCode+",")
				.append("'creditRating':"+creditRating+",")
				.append("'contractCode':"+contractCode+",")
				.append("'contractCreateDate':"+contractCreateDate+",")
				.append("'contractPrice':"+contractPrice+",")
				.append("'contractTime':"+contractTime+",")
				.append("'interestRateCode':"+interestRateCode+",")
				.append("'badDebts':"+badDebts+",")
				.append("'badDebtsCreateDate':"+badDebtsCreateDate+",")
				.append("'badDebtsCode':"+badDebtsCode+",")
				.append("'badDebtsPrice':"+badDebtsPrice+",")
				.append("'badDebtsRestorePrice':"+badDebtsRestorePrice+",")
				.append("'patAccountVoided':"+patAccountVoided+",")
				.append("'patAccountVoidedDate':"+patAccountVoidedDate+",")
				.append("'patDischargeDisposition':"+patDischargeDisposition+",")
				.append("'patDischargeLocation':"+patDischargeLocation+",")
				.append("'patDietType':"+patDietType+",")
				.append("'patServiceAgencies':"+patServiceAgencies+",")
				.append("'patBedStatus':"+patBedStatus+",")
				.append("'patAccountStatus':"+patAccountStatus+",")
				.append("'patDeterPointOfCare':"+patDeterPointOfCare+",")
				.append("'patDeterRoom':"+patDeterRoom+",")
				.append("'patDeterBed':"+patDeterBed+",")
				.append("'patDeterDep':"+patDeterDep+",")
				.append("'patDeterPositionStatus':"+patDeterPositionStatus+",")
				.append("'patDeterPositionType':"+patDeterPositionType+",")
				.append("'patDeterBuilding':"+patDeterBuilding+",")
				.append("'patDeterFloor':"+patDeterFloor+",")
				.append("'patDeterDescription':"+patDeterDescription+",")
				.append("'patForTempPointOfCare':"+patForTempPointOfCare+",")
				.append("'patForTempRoom':"+patForTempRoom+",")
				.append("'patForTempBed':"+patForTempBed+",")
				.append("'patForTempDep':"+patForTempDep+",")
				.append("'patForTempPositionStatus':"+patForTempPositionStatus+",")
				.append("'patForTempPositionType':"+patForTempPositionType+",")
				.append("'patForTempBuilding':"+patForTempBuilding+",")
				.append("'patForTempFloor':"+patForTempFloor+",")
				.append("'patForTempDescription':"+patForTempDescription+",")
				.append("'admitDate':"+admitDate+",")
				.append("'dischargeDate':"+dischargeDate+",")
				.append("'patDifference':"+patDifference+",")
				.append("'patTotalCost':"+patTotalCost+",")
				.append("'patTotalDispatch':"+patTotalDispatch+",")
				.append("'patTotalAmountPayable':"+patTotalAmountPayable+",")
				.append("'patSpareId':"+patSpareId+",")
				.append("'patVisitLogo':"+patVisitLogo+",")
				.append("'otherMedicalInstitutions':"+otherMedicalInstitutions+",")
				.append("'createDate':"+createDate+",")
				.append("'createId':"+createId+",")
				.append("'voidedDate':"+voidedDate+",")
				.append("'voidedId':"+voidedId+",")
				.append("'modifyDate':"+modifyDate+",")
				.append("'modifyId':"+modifyId+",")
				.append("'custom1':"+custom1+",")
				.append("'custom2':"+custom2+",")
				.append("'custom3':"+custom3+",")
				.append("'custom4':"+custom4+",")
				.append("'custom5':"+custom5+",")
				.append("'patNurseCode':"+patNurseCode+",")
				.append("'patNurseName':"+patNurseName+",")
				.append("'tend':"+tend+",")
				.append("'babyFlag':"+babyFlag+",")
				.append("'admitWeight':"+admitWeight+",")
				.append("'birthWeight':"+birthWeight+",")
				.append("'operCode':"+operCode+",")
				.append("'operDate':"+operDate+",")
				.append("'prefix':"+prefix+",")
				.append("'insuranceType':"+insuranceType+",")
				.append("'contactPerson':"+contactPerson+",")
				.append("'contactRelations':"+contactRelations+",")
				.append("'contactAddress':"+contactAddress+",")
				.append("'contactPhone':"+contactPhone+",")
				.append("'patCategoryName':"+patCategoryName+",")
				.append("'genderName':"+genderName+",")
				.append("'payRate':"+payRate+",")
				.append("'dischargeName':"+dischargeName+",")
				.append("'insuranceName':"+insuranceName+",")
				.append("'admissionName':"+admissionName+",")
				.append("'ipStatusName':"+ipStatusName+",")
				.append("'dificultyName':"+dificultyName+",")
				.append("'admitWayName':"+admitWayName+",")
				.append("'admitWeightUnit':"+admitWeightUnit+",")
				.append("'babyWeightUnit':"+babyWeightUnit+",")
				.append("'medicinelimitamount':"+medicinelimitamount+",")
				.append("'sickbedlimitamount':"+sickbedlimitamount+",")
				.append("'examinelimitamount':"+examinelimitamount+",")
				.append("'curelimitamount':"+curelimitamount+",")
				.append("'hiupStatus':"+hiupStatus+",")
				.append("'hiupErrorInfo':"+hiupErrorInfo+",")
				.append("'admissionDomain':"+admissionDomain+",")
				.append("'admissionSourceDomain':"+admissionSourceDomain+",")
				.append("'admissionSourceName':"+admissionSourceName+",")
				.append("'patientClassName':"+patientClassName+",")
				.append("'patientClassDomain':"+patientClassDomain+",")
				.append("'ipStatusDomain':"+ipStatusDomain+",")
				.append("'dificultyDomain':"+dificultyDomain+",")
				.append("'dischargeDomain':"+dischargeDomain+",")
				.append("'accountStatusName':"+accountStatusName+",")
				.append("'accountStatusDomain':"+accountStatusDomain+",")
				.append("'genderDomain':"+genderDomain+",")
				.append("'patCategorySystem':"+patCategorySystem+",")
				.append("'mothersID':"+mothersID+",")
				.append("'mothersFlowID':"+mothersFlowID+",")
				.append("'mothersFlowDomain':"+mothersFlowDomain+",")
				.append("'mothersName':"+mothersName+",")
				.append("'mothersDomain':"+mothersDomain+",")
				.append("'patientSourceName':"+patientSourceName+",")
				.append("'oldPatientId':"+oldPatientId+",")
				.append("'oldPatientDomain':"+oldPatientDomain+",")
				.append("'oldVisitFlowId':"+oldVisitFlowId+",")
				.append("'oldVisitFlowDomain':"+oldVisitFlowDomain+",")
				.append("'oldVisitId':"+oldVisitId+",")
				.append("'oldPersonId':"+oldPersonId+",")
				.append("'oldStatus':"+oldStatus+",")
				.append("'oldInfo':"+oldInfo+",")
				.append("'isEmergency':"+isEmergency+",")
				.append("'patIpTimes':"+patIpTimes+",")
				.append("'diagnoseIcd':"+diagnoseIcd+",")
				.append("'diagnoseName':"+diagnoseName+",")
				.append("'noonCode':"+noonCode+",")
				.append("'paykindCode':"+paykindCode+",")
				.append("'paykindName':"+paykindName+",")
				.append("'schemaNo':"+schemaNo+",")
				.append("'orderNo':"+orderNo+",")
				.append("'seeNo':"+seeNo+",")
				.append("'beginTime':"+beginTime+",")
				.append("'endTime':"+endTime+",")
				.append("'ynBook':"+ynBook+",")
				.append("'ynfr':"+ynfr+",")
				.append("'appendFlag':"+appendFlag+",")
				.append("'ynSee':"+ynSee+",")
				.append("'seeDate':"+seeDate+",")
				.append("'triageFlag':"+triageFlag+",")
				.append("'triageOpcd':"+triageOpcd+",")
				.append("'triageDate':"+triageDate+",")
				.append("'seeDpcd':"+seeDpcd+",")
				.append("'seeDocd':"+seeDocd+",")
				.append("'outPatientStatusA':"+outPatientStatusA+",")
				.append("'outPatientStatusB':"+outPatientStatusB+",")
				.append("'outPatientStatusC':"+outPatientStatusC+",")
				.append("'inPatientStatusA':"+inPatientStatusA+",")
				.append("'inPatientStatusB':"+inPatientStatusB+",")
				.append("'inPatientStatusC':"+inPatientStatusC+",")
				.append("'relevanceID':"+relevanceID+",")
				.append("'relevanceDomain':"+relevanceDomain+",")
				.append("'relevanceName':"+relevanceName)
				.append("}");
		return str;
	}




	}

	

