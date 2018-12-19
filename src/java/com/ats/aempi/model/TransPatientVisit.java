package com.ats.aempi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TransPatientVisit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRANS_PATIENT_VISIT", schema = "AETRANS")
public class TransPatientVisit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -613376485753361527L;
	private String uuid;
	private String patientId;
	private String visitFlowId;
	private String name;
	private Date dateOfBirth;
	private String birthPlace;
	private String ssn;
	private String identityNo;
	private String insuranceNo;
	private String genderCd;
	private String maritalStatus;
	private String homeAddress;
	private String workAddress;
	private String homePhone;
	private String workPhone;
	private String hospitalDomainId;
	private String hospitalDomainName;
	private String identifierDomainName;
	private String identifierDomainId;
	private String identifierDomainType;
	private String identifierFlowDomainName;
	private String identifierFlowDomainId;
	private String identifierFlowDomainType;
	private String patCategory;
	private String patCurrentPointOfCare;
	private String patCurrentRoom;
	private String patCurrentBed;
	private String patCuurentDep;
	private String patCurrentDepName;
	private String patCurrentPositionStatus;
	private String patCurrentPositionType;
	private String patCurrentBuilding;
	private String patCurrentFloor;
	private String patCuurentDescription;
	private String patAdmissionType;
	private String patAdmissionNumber;
	private String admissionsDoctor;
	private String admissionsDoctorId;
	private String referringDoctor;
	private String referringDoctorId;
	private String consultationDoctor;
	private String consultationDoctorId;
	private String hospitalService;
	private String patAdmissionTest;
	private String patReAdmission;
	private String patAdmissionSource;
	private String patAmbulatoryStatus;
	private String patVip;
	private String patAdmissionDoctors;
	private String patAdmissionDoctorsId;
	private String patientClass;
	private String patientFlowId;
	private String patDischargeDisposition;
	private String patDischargeLocation;
	private String patDietType;
	private String patServiceAgencies;
	private String patBedStatus;
	private String patAccountStatus;
	private String patNurseCode;
	private String patNurseName;
	private String tend;
	private String patDieteticMark;
	private String patIptimes;
	private String patDischargeCode;
	private String patDeterPointOfCare;
	private String patDeterRoom;
	private String patDeterBed;
	private String patDeterDep;
	private String patDeterPositionStatus;
	private String patDeterPositionType;
	private String patDeterBuilding;
	private String patDeterFloor;
	private String patDeterDescription;
	private String patIpstatuscode;
	private String patDifficultyLevelcode;
	private String babyFlag;
	private String admitWeight;
	private String birthWeight;
	private String patFormerPointOfCare;
	private String patFormerRoom;
	private String patFormerBed;
	private String patFormerDep;
	private String patFormerPositionStatus;
	private String patFormerPositionType;
	private String patFormerBuilding;
	private String patFormerFloor;
	private String patFormerDescription;
	private String patTempPointOfCare;
	private String patTempRoom;
	private String patTempBed;
	private String patTempDep;
	private String patTempPositionStatus;
	private String patTempPositionType;
	private String patTempBuilding;
	private String patTempFloor;
	private String patTempDescription;
	private String patForTempPointOfCare;
	private String patForTempRoom;
	private String patForTempBed;
	private String patForTempDep;
	private String patForTempPositionStatus;
	private String patForTempPositionType;
	private String patForTempBuilding;
	private String patForTempFloor;
	private String patForTempDescription;
	private String operCode;
	private Date operDate;
	private Date admitDate;
	private Date dischargeDate;
	private Date regDate;
	private Date oprDate;
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
	private String custom6;
	private String custom7;
	private String custom8;
	private String custom9;
	private String custom10;
	private String custom11;
	private String custom12;
	private String custom13;
	private String custom14;
	private String custom15;
	private String custom16;
	private String custom17;
	private String custom18;
	private String custom19;
	private String custom20;
	private String patientVisitStatus;
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
	private String hiupStatus;
	private String hiupErrorInfo;
	private String mothersID;
	private String mothersFlowID;
	private String mothersFlowDomain;
	private String mothersName;
	private String mothersDomain;
	private String patCategorySystem;
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
	// Constructors

	/** default constructor */
	public TransPatientVisit() {
	}

	/** minimal constructor */
	public TransPatientVisit(String uuid, String patientId, String visitFlowId,
			String identifierDomainId, String identifierFlowDomainId) {
		this.uuid = uuid;
		this.patientId = patientId;
		this.visitFlowId = visitFlowId;
		this.identifierDomainId = identifierDomainId;
		this.identifierFlowDomainId = identifierFlowDomainId;
	}

	/** full constructor */
	public TransPatientVisit(String uuid, String patientId, String visitFlowId,
			String name, Date dateOfBirth, String birthPlace, String ssn,
			String identityNo, String insuranceNo, String genderCd,
			String maritalStatus, String homeAddress, String workAddress,
			String homePhone, String workPhone, String hospitalDomainId,
			String hospitalDomainName, String identifierDomainName,
			String identifierDomainId, String identifierDomainType,
			String identifierFlowDomainName, String identifierFlowDomainId,
			String identifierFlowDomainType, String patCategory,
			String patCurrentPointOfCare, String patCurrentRoom,
			String patCurrentBed, String patCuurentDep,
			String patCurrentDepName, String patCurrentPositionStatus,
			String patCurrentPositionType, String patCurrentBuilding,
			String patCurrentFloor, String patCuurentDescription,
			String patAdmissionType, String patAdmissionNumber,
			String admissionsDoctor, String admissionsDoctorId,
			String referringDoctor, String referringDoctorId,
			String consultationDoctor, String consultationDoctorId,
			String hospitalService, String patAdmissionTest,
			String patReAdmission, String patAdmissionSource,
			String patAmbulatoryStatus, String patVip,
			String patAdmissionDoctors, String patAdmissionDoctorsId,
			String patientClass, String patientFlowId,
			String patDischargeDisposition, String patDischargeLocation,
			String patDietType, String patServiceAgencies, String patBedStatus,
			String patAccountStatus, String patNurseCode, String patNurseName,
			String tend, String patDieteticMark, String patIptimes,
			String patDischargeCode, String patDeterPointOfCare,
			String patDeterRoom, String patDeterBed, String patDeterDep,
			String patDeterPositionStatus, String patDeterPositionType,
			String patDeterBuilding, String patDeterFloor,
			String patDeterDescription, String patIpstatuscode,
			String patDifficultyLevelcode, String babyFlag, String admitWeight,
			String birthWeight, String patFormerPointOfCare,
			String patFormerRoom, String patFormerBed, String patFormerDep,
			String patFormerPositionStatus, String patFormerPositionType,
			String patFormerBuilding, String patFormerFloor,
			String patFormerDescription, String patTempPointOfCare,
			String patTempRoom, String patTempBed, String patTempDep,
			String patTempPositionStatus, String patTempPositionType,
			String patTempBuilding, String patTempFloor,
			String patTempDescription, String patForTempPointOfCare,
			String patForTempRoom, String patForTempBed, String patForTempDep,
			String patForTempPositionStatus, String patForTempPositionType,
			String patForTempBuilding, String patForTempFloor,
			String patForTempDescription, String operCode, Date operDate,
			Date admitDate, Date dischargeDate, Date regDate,
			Date oprDate, Date createDate, String createId,
			Date voidedDate, String voidedId, Date modifyDate,
			String modifyId, String custom1, String custom2, String custom3,
			String custom4, String custom5, String custom6, String custom7,
			String custom8, String custom9, String custom10, String custom11,
			String custom12, String custom13, String custom14, String custom15,
			String custom16, String custom17, String custom18, String custom19,
			String custom20, String patientVisitStatus, String prefix,
			String insuranceType, String contactPerson,
			String contactRelations, String contactAddress,
			String contactPhone, String patCategoryName, String genderName,
			String payRate, String dischargeName, String insuranceName,
			String admissionName, String ipStatusName, String dificultyName,
			String admitWayName, String admitWeightUnit, String babyWeightUnit,
			String admissionDomain, String admissionSourceDomain,
			String admissionSourceName, String patientClassName,
			String patientClassDomain, String ipStatusDomain,
			String dificultyDomain, String dischargeDomain,
			String accountStatusName, String accountStatusDomain,
			String genderDomain, String hiupStatus, String hiupErrorInfo,String pateCategorySystem) {
		this.uuid = uuid;
		this.patientId = patientId;
		this.visitFlowId = visitFlowId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.birthPlace = birthPlace;
		this.ssn = ssn;
		this.identityNo = identityNo;
		this.insuranceNo = insuranceNo;
		this.genderCd = genderCd;
		this.maritalStatus = maritalStatus;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.homePhone = homePhone;
		this.workPhone = workPhone;
		this.hospitalDomainId = hospitalDomainId;
		this.hospitalDomainName = hospitalDomainName;
		this.identifierDomainName = identifierDomainName;
		this.identifierDomainId = identifierDomainId;
		this.identifierDomainType = identifierDomainType;
		this.identifierFlowDomainName = identifierFlowDomainName;
		this.identifierFlowDomainId = identifierFlowDomainId;
		this.identifierFlowDomainType = identifierFlowDomainType;
		this.patCategory = patCategory;
		this.patCurrentPointOfCare = patCurrentPointOfCare;
		this.patCurrentRoom = patCurrentRoom;
		this.patCurrentBed = patCurrentBed;
		this.patCuurentDep = patCuurentDep;
		this.patCurrentDepName = patCurrentDepName;
		this.patCurrentPositionStatus = patCurrentPositionStatus;
		this.patCurrentPositionType = patCurrentPositionType;
		this.patCurrentBuilding = patCurrentBuilding;
		this.patCurrentFloor = patCurrentFloor;
		this.patCuurentDescription = patCuurentDescription;
		this.patAdmissionType = patAdmissionType;
		this.patAdmissionNumber = patAdmissionNumber;
		this.admissionsDoctor = admissionsDoctor;
		this.admissionsDoctorId = admissionsDoctorId;
		this.referringDoctor = referringDoctor;
		this.referringDoctorId = referringDoctorId;
		this.consultationDoctor = consultationDoctor;
		this.consultationDoctorId = consultationDoctorId;
		this.hospitalService = hospitalService;
		this.patAdmissionTest = patAdmissionTest;
		this.patReAdmission = patReAdmission;
		this.patAdmissionSource = patAdmissionSource;
		this.patAmbulatoryStatus = patAmbulatoryStatus;
		this.patVip = patVip;
		this.patAdmissionDoctors = patAdmissionDoctors;
		this.patAdmissionDoctorsId = patAdmissionDoctorsId;
		this.patientClass = patientClass;
		this.patientFlowId = patientFlowId;
		this.patDischargeDisposition = patDischargeDisposition;
		this.patDischargeLocation = patDischargeLocation;
		this.patDietType = patDietType;
		this.patServiceAgencies = patServiceAgencies;
		this.patBedStatus = patBedStatus;
		this.patAccountStatus = patAccountStatus;
		this.patNurseCode = patNurseCode;
		this.patNurseName = patNurseName;
		this.tend = tend;
		this.patDieteticMark = patDieteticMark;
		this.patIptimes = patIptimes;
		this.patDischargeCode = patDischargeCode;
		this.patDeterPointOfCare = patDeterPointOfCare;
		this.patDeterRoom = patDeterRoom;
		this.patDeterBed = patDeterBed;
		this.patDeterDep = patDeterDep;
		this.patDeterPositionStatus = patDeterPositionStatus;
		this.patDeterPositionType = patDeterPositionType;
		this.patDeterBuilding = patDeterBuilding;
		this.patDeterFloor = patDeterFloor;
		this.patDeterDescription = patDeterDescription;
		this.patIpstatuscode = patIpstatuscode;
		this.patDifficultyLevelcode = patDifficultyLevelcode;
		this.babyFlag = babyFlag;
		this.admitWeight = admitWeight;
		this.birthWeight = birthWeight;
		this.patFormerPointOfCare = patFormerPointOfCare;
		this.patFormerRoom = patFormerRoom;
		this.patFormerBed = patFormerBed;
		this.patFormerDep = patFormerDep;
		this.patFormerPositionStatus = patFormerPositionStatus;
		this.patFormerPositionType = patFormerPositionType;
		this.patFormerBuilding = patFormerBuilding;
		this.patFormerFloor = patFormerFloor;
		this.patFormerDescription = patFormerDescription;
		this.patTempPointOfCare = patTempPointOfCare;
		this.patTempRoom = patTempRoom;
		this.patTempBed = patTempBed;
		this.patTempDep = patTempDep;
		this.patTempPositionStatus = patTempPositionStatus;
		this.patTempPositionType = patTempPositionType;
		this.patTempBuilding = patTempBuilding;
		this.patTempFloor = patTempFloor;
		this.patTempDescription = patTempDescription;
		this.patForTempPointOfCare = patForTempPointOfCare;
		this.patForTempRoom = patForTempRoom;
		this.patForTempBed = patForTempBed;
		this.patForTempDep = patForTempDep;
		this.patForTempPositionStatus = patForTempPositionStatus;
		this.patForTempPositionType = patForTempPositionType;
		this.patForTempBuilding = patForTempBuilding;
		this.patForTempFloor = patForTempFloor;
		this.patForTempDescription = patForTempDescription;
		this.operCode = operCode;
		this.operDate = operDate;
		this.admitDate = admitDate;
		this.dischargeDate = dischargeDate;
		this.regDate = regDate;
		this.oprDate = oprDate;
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
		this.custom6 = custom6;
		this.custom7 = custom7;
		this.custom8 = custom8;
		this.custom9 = custom9;
		this.custom10 = custom10;
		this.custom11 = custom11;
		this.custom12 = custom12;
		this.custom13 = custom13;
		this.custom14 = custom14;
		this.custom15 = custom15;
		this.custom16 = custom16;
		this.custom17 = custom17;
		this.custom18 = custom18;
		this.custom19 = custom19;
		this.custom20 = custom20;
		this.patientVisitStatus = patientVisitStatus;
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
		this.hiupStatus = hiupStatus;
		this.hiupErrorInfo = hiupErrorInfo;
		this.patCategorySystem=pateCategorySystem;
	}

	// Property accessors
	@Id
	@Column(name = "UUID", unique = true, nullable = false, length = 64)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "PATIENT_ID", nullable = false, length = 64)
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Column(name = "VISIT_FLOW_ID", nullable = false, length = 64)
	public String getVisitFlowId() {
		return this.visitFlowId;
	}

	public void setVisitFlowId(String visitFlowId) {
		this.visitFlowId = visitFlowId;
	}

	@Column(name = "NAME", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DATE_OF_BIRTH")
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "BIRTH_PLACE")
	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Column(name = "SSN", length = 64)
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Column(name = "IDENTITY_NO", length = 64)
	public String getIdentityNo() {
		return this.identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	@Column(name = "INSURANCE_NO", length = 64)
	public String getInsuranceNo() {
		return this.insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	@Column(name = "GENDER_CD", length = 32)
	public String getGenderCd() {
		return this.genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	@Column(name = "MARITAL_STATUS", length = 32)
	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Column(name = "HOME_ADDRESS", length = 256)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "WORK_ADDRESS", length = 256)
	public String getWorkAddress() {
		return this.workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	@Column(name = "HOME_PHONE", length = 64)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "WORK_PHONE", length = 64)
	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	@Column(name = "HOSPITAL_DOMAIN_ID", length = 256)
	public String getHospitalDomainId() {
		return this.hospitalDomainId;
	}

	public void setHospitalDomainId(String hospitalDomainId) {
		this.hospitalDomainId = hospitalDomainId;
	}

	@Column(name = "HOSPITAL_DOMAIN_NAME", length = 256)
	public String getHospitalDomainName() {
		return this.hospitalDomainName;
	}

	public void setHospitalDomainName(String hospitalDomainName) {
		this.hospitalDomainName = hospitalDomainName;
	}

	@Column(name = "IDENTIFIER_DOMAIN_NAME")
	public String getIdentifierDomainName() {
		return this.identifierDomainName;
	}

	public void setIdentifierDomainName(String identifierDomainName) {
		this.identifierDomainName = identifierDomainName;
	}

	@Column(name = "IDENTIFIER_DOMAIN_ID")
	public String getIdentifierDomainId() {
		return this.identifierDomainId;
	}

	public void setIdentifierDomainId(String identifierDomainId) {
		this.identifierDomainId = identifierDomainId;
	}

	@Column(name = "IDENTIFIER_DOMAIN_TYPE")
	public String getIdentifierDomainType() {
		return this.identifierDomainType;
	}

	public void setIdentifierDomainType(String identifierDomainType) {
		this.identifierDomainType = identifierDomainType;
	}

	@Column(name = "IDENTIFIER_FLOW_DOMAIN_NAME")
	public String getIdentifierFlowDomainName() {
		return this.identifierFlowDomainName;
	}

	public void setIdentifierFlowDomainName(String identifierFlowDomainName) {
		this.identifierFlowDomainName = identifierFlowDomainName;
	}

	@Column(name = "IDENTIFIER_FLOW_DOMAIN_ID")
	public String getIdentifierFlowDomainId() {
		return this.identifierFlowDomainId;
	}

	public void setIdentifierFlowDomainId(String identifierFlowDomainId) {
		this.identifierFlowDomainId = identifierFlowDomainId;
	}

	@Column(name = "IDENTIFIER_FLOW_DOMAIN_TYPE")
	public String getIdentifierFlowDomainType() {
		return this.identifierFlowDomainType;
	}

	public void setIdentifierFlowDomainType(String identifierFlowDomainType) {
		this.identifierFlowDomainType = identifierFlowDomainType;
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

	@Column(name = "PAT_CURRENT_DEP_NAME", length = 256)
	public String getPatCurrentDepName() {
		return this.patCurrentDepName;
	}

	public void setPatCurrentDepName(String patCurrentDepName) {
		this.patCurrentDepName = patCurrentDepName;
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

	@Column(name = "PATIENT_FLOW_ID", length = 64)
	public String getPatientFlowId() {
		return this.patientFlowId;
	}

	public void setPatientFlowId(String patientFlowId) {
		this.patientFlowId = patientFlowId;
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

	@Column(name = "PAT_DIETETIC_MARK", length = 32)
	public String getPatDieteticMark() {
		return this.patDieteticMark;
	}

	public void setPatDieteticMark(String patDieteticMark) {
		this.patDieteticMark = patDieteticMark;
	}

	@Column(name = "PAT_IPTIMES")
	public String getPatIptimes() {
		return this.patIptimes;
	}

	public void setPatIptimes(String patIptimes) {
		this.patIptimes = patIptimes;
	}

	@Column(name = "PAT_DISCHARGE_CODE")
	public String getPatDischargeCode() {
		return this.patDischargeCode;
	}

	public void setPatDischargeCode(String patDischargeCode) {
		this.patDischargeCode = patDischargeCode;
	}

	@Column(name = "PAT_DETER_POINT_OF_CARE")
	public String getPatDeterPointOfCare() {
		return this.patDeterPointOfCare;
	}

	public void setPatDeterPointOfCare(String patDeterPointOfCare) {
		this.patDeterPointOfCare = patDeterPointOfCare;
	}

	@Column(name = "PAT_DETER_ROOM")
	public String getPatDeterRoom() {
		return this.patDeterRoom;
	}

	public void setPatDeterRoom(String patDeterRoom) {
		this.patDeterRoom = patDeterRoom;
	}

	@Column(name = "PAT_DETER_BED")
	public String getPatDeterBed() {
		return this.patDeterBed;
	}

	public void setPatDeterBed(String patDeterBed) {
		this.patDeterBed = patDeterBed;
	}

	@Column(name = "PAT_DETER_DEP")
	public String getPatDeterDep() {
		return this.patDeterDep;
	}

	public void setPatDeterDep(String patDeterDep) {
		this.patDeterDep = patDeterDep;
	}

	@Column(name = "PAT_DETER_POSITION_STATUS")
	public String getPatDeterPositionStatus() {
		return this.patDeterPositionStatus;
	}

	public void setPatDeterPositionStatus(String patDeterPositionStatus) {
		this.patDeterPositionStatus = patDeterPositionStatus;
	}

	@Column(name = "PAT_DETER_POSITION_TYPE")
	public String getPatDeterPositionType() {
		return this.patDeterPositionType;
	}

	public void setPatDeterPositionType(String patDeterPositionType) {
		this.patDeterPositionType = patDeterPositionType;
	}

	@Column(name = "PAT_DETER_BUILDING")
	public String getPatDeterBuilding() {
		return this.patDeterBuilding;
	}

	public void setPatDeterBuilding(String patDeterBuilding) {
		this.patDeterBuilding = patDeterBuilding;
	}

	@Column(name = "PAT_DETER_FLOOR")
	public String getPatDeterFloor() {
		return this.patDeterFloor;
	}

	public void setPatDeterFloor(String patDeterFloor) {
		this.patDeterFloor = patDeterFloor;
	}

	@Column(name = "PAT_DETER_DESCRIPTION")
	public String getPatDeterDescription() {
		return this.patDeterDescription;
	}

	public void setPatDeterDescription(String patDeterDescription) {
		this.patDeterDescription = patDeterDescription;
	}

	@Column(name = "PAT_IPSTATUSCODE")
	public String getPatIpstatuscode() {
		return this.patIpstatuscode;
	}

	public void setPatIpstatuscode(String patIpstatuscode) {
		this.patIpstatuscode = patIpstatuscode;
	}

	@Column(name = "PAT_DIFFICULTY_LEVELCODE")
	public String getPatDifficultyLevelcode() {
		return this.patDifficultyLevelcode;
	}

	public void setPatDifficultyLevelcode(String patDifficultyLevelcode) {
		this.patDifficultyLevelcode = patDifficultyLevelcode;
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

	@Column(name = "ADMIT_DATE")
	public Date getAdmitDate() {
		return this.admitDate;
	}

	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	@Column(name = "DISCHARGE_DATE")
	public Date getDischargeDate() {
		return this.dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Column(name = "REG_DATE")
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column(name = "OPR_DATE")
	public Date getOprDate() {
		return this.oprDate;
	}

	public void setOprDate(Date oprDate) {
		this.oprDate = oprDate;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_ID", length = 32)
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

	@Column(name = "VOIDED_ID", length = 32)
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

	@Column(name = "MODIFY_ID", length = 32)
	public String getModifyId() {
		return this.modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	@Column(name = "CUSTOM1")
	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	@Column(name = "CUSTOM2")
	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	@Column(name = "CUSTOM3")
	public String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Column(name = "CUSTOM4")
	public String getCustom4() {
		return this.custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	@Column(name = "CUSTOM5")
	public String getCustom5() {
		return this.custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	@Column(name = "CUSTOM6")
	public String getCustom6() {
		return this.custom6;
	}

	public void setCustom6(String custom6) {
		this.custom6 = custom6;
	}

	@Column(name = "CUSTOM7")
	public String getCustom7() {
		return this.custom7;
	}

	public void setCustom7(String custom7) {
		this.custom7 = custom7;
	}

	@Column(name = "CUSTOM8")
	public String getCustom8() {
		return this.custom8;
	}

	public void setCustom8(String custom8) {
		this.custom8 = custom8;
	}

	@Column(name = "CUSTOM9")
	public String getCustom9() {
		return this.custom9;
	}

	public void setCustom9(String custom9) {
		this.custom9 = custom9;
	}

	@Column(name = "CUSTOM10")
	public String getCustom10() {
		return this.custom10;
	}

	public void setCustom10(String custom10) {
		this.custom10 = custom10;
	}

	@Column(name = "CUSTOM11")
	public String getCustom11() {
		return this.custom11;
	}

	public void setCustom11(String custom11) {
		this.custom11 = custom11;
	}

	@Column(name = "CUSTOM12")
	public String getCustom12() {
		return this.custom12;
	}

	public void setCustom12(String custom12) {
		this.custom12 = custom12;
	}

	@Column(name = "CUSTOM13")
	public String getCustom13() {
		return this.custom13;
	}

	public void setCustom13(String custom13) {
		this.custom13 = custom13;
	}

	@Column(name = "CUSTOM14")
	public String getCustom14() {
		return this.custom14;
	}

	public void setCustom14(String custom14) {
		this.custom14 = custom14;
	}

	@Column(name = "CUSTOM15")
	public String getCustom15() {
		return this.custom15;
	}

	public void setCustom15(String custom15) {
		this.custom15 = custom15;
	}

	@Column(name = "CUSTOM16")
	public String getCustom16() {
		return this.custom16;
	}

	public void setCustom16(String custom16) {
		this.custom16 = custom16;
	}

	@Column(name = "CUSTOM17")
	public String getCustom17() {
		return this.custom17;
	}

	public void setCustom17(String custom17) {
		this.custom17 = custom17;
	}

	@Column(name = "CUSTOM18")
	public String getCustom18() {
		return this.custom18;
	}

	public void setCustom18(String custom18) {
		this.custom18 = custom18;
	}

	@Column(name = "CUSTOM19")
	public String getCustom19() {
		return this.custom19;
	}

	public void setCustom19(String custom19) {
		this.custom19 = custom19;
	}

	@Column(name = "CUSTOM20")
	public String getCustom20() {
		return this.custom20;
	}

	public void setCustom20(String custom20) {
		this.custom20 = custom20;
	}

	@Column(name = "PATIENT_VISIT_STATUS", length = 64)
	public String getPatientVisitStatus() {
		return this.patientVisitStatus;
	}

	public void setPatientVisitStatus(String patientVisitStatus) {
		this.patientVisitStatus = patientVisitStatus;
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

	/**
	 * @return the patCategorySystem
	 */
	@Column(name = "PAT_CATEGORY_SYSTEM")
	public String getPatCategorySystem() {
		return patCategorySystem;
	}

	/**
	 * @param patCategorySystem the patCategorySystem to set
	 */
	public void setPatCategorySystem(String patCategorySystem) {
		this.patCategorySystem = patCategorySystem;
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
}
