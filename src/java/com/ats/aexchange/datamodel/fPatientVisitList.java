package com.ats.aexchange.datamodel;

import java.math.BigDecimal;
import java.util.Date;

import com.ats.aexchange.datamodel.SharedEnums.AddressType;


/**
 *
 * @author Wenzhi Li
 */
public class fPatientVisitList {
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
	
	
	/**
	 * @return the noonCode
	 */
	public String getNoonCode() {
		return noonCode;
	}
	/**
	 * @param noonCode the noonCode to set
	 */
	public void setNoonCode(String noonCode) {
		this.noonCode = noonCode;
	}
	/**
	 * @return the paykindCode
	 */
	public String getPaykindCode() {
		return paykindCode;
	}
	/**
	 * @param paykindCode the paykindCode to set
	 */
	public void setPaykindCode(String paykindCode) {
		this.paykindCode = paykindCode;
	}
	/**
	 * @return the paykindName
	 */
	public String getPaykindName() {
		return paykindName;
	}
	/**
	 * @param paykindName the paykindName to set
	 */
	public void setPaykindName(String paykindName) {
		this.paykindName = paykindName;
	}
	/**
	 * @return the schemaNo
	 */
	public String getSchemaNo() {
		return schemaNo;
	}
	/**
	 * @param schemaNo the schemaNo to set
	 */
	public void setSchemaNo(String schemaNo) {
		this.schemaNo = schemaNo;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the seeNo
	 */
	public String getSeeNo() {
		return seeNo;
	}
	/**
	 * @param seeNo the seeNo to set
	 */
	public void setSeeNo(String seeNo) {
		this.seeNo = seeNo;
	}
	/**
	 * @return the beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the ynBook
	 */
	public String getYnBook() {
		return ynBook;
	}
	/**
	 * @param ynBook the ynBook to set
	 */
	public void setYnBook(String ynBook) {
		this.ynBook = ynBook;
	}
	/**
	 * @return the ynfr
	 */
	public String getYnfr() {
		return ynfr;
	}
	/**
	 * @param ynfr the ynfr to set
	 */
	public void setYnfr(String ynfr) {
		this.ynfr = ynfr;
	}
	/**
	 * @return the appendFlag
	 */
	public String getAppendFlag() {
		return appendFlag;
	}
	/**
	 * @param appendFlag the appendFlag to set
	 */
	public void setAppendFlag(String appendFlag) {
		this.appendFlag = appendFlag;
	}
	/**
	 * @return the ynSee
	 */
	public String getYnSee() {
		return ynSee;
	}
	/**
	 * @param ynSee the ynSee to set
	 */
	public void setYnSee(String ynSee) {
		this.ynSee = ynSee;
	}
	/**
	 * @return the seeDate
	 */
	public Date getSeeDate() {
		return seeDate;
	}
	/**
	 * @param seeDate the seeDate to set
	 */
	public void setSeeDate(Date seeDate) {
		this.seeDate = seeDate;
	}
	/**
	 * @return the triageFlag
	 */
	public String getTriageFlag() {
		return triageFlag;
	}
	/**
	 * @param triageFlag the triageFlag to set
	 */
	public void setTriageFlag(String triageFlag) {
		this.triageFlag = triageFlag;
	}
	/**
	 * @return the triageOpcd
	 */
	public String getTriageOpcd() {
		return triageOpcd;
	}
	/**
	 * @param triageOpcd the triageOpcd to set
	 */
	public void setTriageOpcd(String triageOpcd) {
		this.triageOpcd = triageOpcd;
	}
	/**
	 * @return the triageDate
	 */
	public Date getTriageDate() {
		return triageDate;
	}
	/**
	 * @param triageDate the triageDate to set
	 */
	public void setTriageDate(Date triageDate) {
		this.triageDate = triageDate;
	}
	/**
	 * @return the seeDpcd
	 */
	public String getSeeDpcd() {
		return seeDpcd;
	}
	/**
	 * @param seeDpcd the seeDpcd to set
	 */
	public void setSeeDpcd(String seeDpcd) {
		this.seeDpcd = seeDpcd;
	}
	/**
	 * @return the seeDocd
	 */
	public String getSeeDocd() {
		return seeDocd;
	}
	/**
	 * @param seeDocd the seeDocd to set
	 */
	public void setSeeDocd(String seeDocd) {
		this.seeDocd = seeDocd;
	}
	/**
	 * @return the outPatientStatusA
	 */
	public String getOutPatientStatusA() {
		return outPatientStatusA;
	}
	/**
	 * @param outPatientStatusA the outPatientStatusA to set
	 */
	public void setOutPatientStatusA(String outPatientStatusA) {
		this.outPatientStatusA = outPatientStatusA;
	}
	/**
	 * @return the outPatientStatusB
	 */
	public String getOutPatientStatusB() {
		return outPatientStatusB;
	}
	/**
	 * @param outPatientStatusB the outPatientStatusB to set
	 */
	public void setOutPatientStatusB(String outPatientStatusB) {
		this.outPatientStatusB = outPatientStatusB;
	}
	/**
	 * @return the outPatientStatusC
	 */
	public String getOutPatientStatusC() {
		return outPatientStatusC;
	}
	/**
	 * @param outPatientStatusC the outPatientStatusC to set
	 */
	public void setOutPatientStatusC(String outPatientStatusC) {
		this.outPatientStatusC = outPatientStatusC;
	}
	/**
	 * @return the inPatientStatusA
	 */
	public String getInPatientStatusA() {
		return inPatientStatusA;
	}
	/**
	 * @param inPatientStatusA the inPatientStatusA to set
	 */
	public void setInPatientStatusA(String inPatientStatusA) {
		this.inPatientStatusA = inPatientStatusA;
	}
	/**
	 * @return the inPatientStatusB
	 */
	public String getInPatientStatusB() {
		return inPatientStatusB;
	}
	/**
	 * @param inPatientStatusB the inPatientStatusB to set
	 */
	public void setInPatientStatusB(String inPatientStatusB) {
		this.inPatientStatusB = inPatientStatusB;
	}
	/**
	 * @return the inPatientStatusC
	 */
	public String getInPatientStatusC() {
		return inPatientStatusC;
	}
	/**
	 * @param inPatientStatusC the inPatientStatusC to set
	 */
	public void setInPatientStatusC(String inPatientStatusC) {
		this.inPatientStatusC = inPatientStatusC;
	}
	/**
	 * @return the diagnoseIcd
	 */
	public String getDiagnoseIcd() {
		return diagnoseIcd;
	}
	/**
	 * @param diagnoseIcd the diagnoseIcd to set
	 */
	public void setDiagnoseIcd(String diagnoseIcd) {
		this.diagnoseIcd = diagnoseIcd;
	}
	/**
	 * @return the diagnoseName
	 */
	public String getDiagnoseName() {
		return diagnoseName;
	}
	/**
	 * @param diagnoseName the diagnoseName to set
	 */
	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}
	/**
	 * @return the patIpTimes
	 */
	public String getPatIpTimes() {
		return patIpTimes;
	}
	/**
	 * @param patIpTimes the patIpTimes to set
	 */
	public void setPatIpTimes(String patIpTimes) {
		this.patIpTimes = patIpTimes;
	}
	/**
	 * @return the isEmergency
	 */
	public String getIsEmergency() {
		return isEmergency;
	}
	/**
	 * @param isEmergency the isEmergency to set
	 */
	public void setIsEmergency(String isEmergency) {
		this.isEmergency = isEmergency;
	}
	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
	 * @return the personDomain
	 */
	public String getPersonDomain() {
		return personDomain;
	}
	/**
	 * @param personDomain the personDomain to set
	 */
	public void setPersonDomain(String personDomain) {
		this.personDomain = personDomain;
	}
	/**
	 * @return the visitFlowId
	 */
	public String getVisitFlowId() {
		return visitFlowId;
	}
	/**
	 * @param visitFlowId the visitFlowId to set
	 */
	public void setVisitFlowId(String visitFlowId) {
		this.visitFlowId = visitFlowId;
	}
	/**
	 * @return the visitFlowDomain
	 */
	public String getVisitFlowDomain() {
		return visitFlowDomain;
	}
	/**
	 * @param visitFlowDomain the visitFlowDomain to set
	 */
	public void setVisitFlowDomain(String visitFlowDomain) {
		this.visitFlowDomain = visitFlowDomain;
	}
	/**
	 * @return the hospitalDomain
	 */
	public String getHospitalDomain() {
		return hospitalDomain;
	}
	/**
	 * @param hospitalDomain the hospitalDomain to set
	 */
	public void setHospitalDomain(String hospitalDomain) {
		this.hospitalDomain = hospitalDomain;
	}
	/**
	 * @return the patCategory
	 */
	public String getPatCategory() {
		return patCategory;
	}
	/**
	 * @param patCategory the patCategory to set
	 */
	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}
	/**
	 * @return the patCurrentPointOfCare
	 */
	public String getPatCurrentPointOfCare() {
		return patCurrentPointOfCare;
	}
	/**
	 * @param patCurrentPointOfCare the patCurrentPointOfCare to set
	 */
	public void setPatCurrentPointOfCare(String patCurrentPointOfCare) {
		this.patCurrentPointOfCare = patCurrentPointOfCare;
	}
	/**
	 * @return the patCurrentRoom
	 */
	public String getPatCurrentRoom() {
		return patCurrentRoom;
	}
	/**
	 * @param patCurrentRoom the patCurrentRoom to set
	 */
	public void setPatCurrentRoom(String patCurrentRoom) {
		this.patCurrentRoom = patCurrentRoom;
	}
	/**
	 * @return the patCurrentBed
	 */
	public String getPatCurrentBed() {
		return patCurrentBed;
	}
	/**
	 * @param patCurrentBed the patCurrentBed to set
	 */
	public void setPatCurrentBed(String patCurrentBed) {
		this.patCurrentBed = patCurrentBed;
	}
	/**
	 * @return the patCuurentDep
	 */
	public String getPatCuurentDep() {
		return patCuurentDep;
	}
	/**
	 * @param patCuurentDep the patCuurentDep to set
	 */
	public void setPatCuurentDep(String patCuurentDep) {
		this.patCuurentDep = patCuurentDep;
	}
	/**
	 * @return the patCurrentPositionStatus
	 */
	public String getPatCurrentPositionStatus() {
		return patCurrentPositionStatus;
	}
	/**
	 * @param patCurrentPositionStatus the patCurrentPositionStatus to set
	 */
	public void setPatCurrentPositionStatus(String patCurrentPositionStatus) {
		this.patCurrentPositionStatus = patCurrentPositionStatus;
	}
	/**
	 * @return the patCurrentPositionType
	 */
	public String getPatCurrentPositionType() {
		return patCurrentPositionType;
	}
	/**
	 * @param patCurrentPositionType the patCurrentPositionType to set
	 */
	public void setPatCurrentPositionType(String patCurrentPositionType) {
		this.patCurrentPositionType = patCurrentPositionType;
	}
	/**
	 * @return the patCurrentBuilding
	 */
	public String getPatCurrentBuilding() {
		return patCurrentBuilding;
	}
	/**
	 * @param patCurrentBuilding the patCurrentBuilding to set
	 */
	public void setPatCurrentBuilding(String patCurrentBuilding) {
		this.patCurrentBuilding = patCurrentBuilding;
	}
	/**
	 * @return the patCurrentFloor
	 */
	public String getPatCurrentFloor() {
		return patCurrentFloor;
	}
	/**
	 * @param patCurrentFloor the patCurrentFloor to set
	 */
	public void setPatCurrentFloor(String patCurrentFloor) {
		this.patCurrentFloor = patCurrentFloor;
	}
	/**
	 * @return the patCuurentDescription
	 */
	public String getPatCuurentDescription() {
		return patCuurentDescription;
	}
	/**
	 * @param patCuurentDescription the patCuurentDescription to set
	 */
	public void setPatCuurentDescription(String patCuurentDescription) {
		this.patCuurentDescription = patCuurentDescription;
	}
	/**
	 * @return the patAdmissionType
	 */
	public String getPatAdmissionType() {
		return patAdmissionType;
	}
	/**
	 * @param patAdmissionType the patAdmissionType to set
	 */
	public void setPatAdmissionType(String patAdmissionType) {
		this.patAdmissionType = patAdmissionType;
	}
	/**
	 * @return the patAdmissionNumber
	 */
	public String getPatAdmissionNumber() {
		return patAdmissionNumber;
	}
	/**
	 * @param patAdmissionNumber the patAdmissionNumber to set
	 */
	public void setPatAdmissionNumber(String patAdmissionNumber) {
		this.patAdmissionNumber = patAdmissionNumber;
	}
	/**
	 * @return the patFormerPointOfCare
	 */
	public String getPatFormerPointOfCare() {
		return patFormerPointOfCare;
	}
	/**
	 * @param patFormerPointOfCare the patFormerPointOfCare to set
	 */
	public void setPatFormerPointOfCare(String patFormerPointOfCare) {
		this.patFormerPointOfCare = patFormerPointOfCare;
	}
	/**
	 * @return the patFormerRoom
	 */
	public String getPatFormerRoom() {
		return patFormerRoom;
	}
	/**
	 * @param patFormerRoom the patFormerRoom to set
	 */
	public void setPatFormerRoom(String patFormerRoom) {
		this.patFormerRoom = patFormerRoom;
	}
	/**
	 * @return the patFormerBed
	 */
	public String getPatFormerBed() {
		return patFormerBed;
	}
	/**
	 * @param patFormerBed the patFormerBed to set
	 */
	public void setPatFormerBed(String patFormerBed) {
		this.patFormerBed = patFormerBed;
	}
	/**
	 * @return the patFormerDep
	 */
	public String getPatFormerDep() {
		return patFormerDep;
	}
	/**
	 * @param patFormerDep the patFormerDep to set
	 */
	public void setPatFormerDep(String patFormerDep) {
		this.patFormerDep = patFormerDep;
	}
	/**
	 * @return the patFormerPositionStatus
	 */
	public String getPatFormerPositionStatus() {
		return patFormerPositionStatus;
	}
	/**
	 * @param patFormerPositionStatus the patFormerPositionStatus to set
	 */
	public void setPatFormerPositionStatus(String patFormerPositionStatus) {
		this.patFormerPositionStatus = patFormerPositionStatus;
	}
	/**
	 * @return the patFormerPositionType
	 */
	public String getPatFormerPositionType() {
		return patFormerPositionType;
	}
	/**
	 * @param patFormerPositionType the patFormerPositionType to set
	 */
	public void setPatFormerPositionType(String patFormerPositionType) {
		this.patFormerPositionType = patFormerPositionType;
	}
	/**
	 * @return the patFormerBuilding
	 */
	public String getPatFormerBuilding() {
		return patFormerBuilding;
	}
	/**
	 * @param patFormerBuilding the patFormerBuilding to set
	 */
	public void setPatFormerBuilding(String patFormerBuilding) {
		this.patFormerBuilding = patFormerBuilding;
	}
	/**
	 * @return the patFormerFloor
	 */
	public String getPatFormerFloor() {
		return patFormerFloor;
	}
	/**
	 * @param patFormerFloor the patFormerFloor to set
	 */
	public void setPatFormerFloor(String patFormerFloor) {
		this.patFormerFloor = patFormerFloor;
	}
	/**
	 * @return the patFormerDescription
	 */
	public String getPatFormerDescription() {
		return patFormerDescription;
	}
	/**
	 * @param patFormerDescription the patFormerDescription to set
	 */
	public void setPatFormerDescription(String patFormerDescription) {
		this.patFormerDescription = patFormerDescription;
	}
	/**
	 * @return the admissionsDoctor
	 */
	public String getAdmissionsDoctor() {
		return admissionsDoctor;
	}
	/**
	 * @param admissionsDoctor the admissionsDoctor to set
	 */
	public void setAdmissionsDoctor(String admissionsDoctor) {
		this.admissionsDoctor = admissionsDoctor;
	}
	/**
	 * @return the admissionsDoctorId
	 */
	public String getAdmissionsDoctorId() {
		return admissionsDoctorId;
	}
	/**
	 * @param admissionsDoctorId the admissionsDoctorId to set
	 */
	public void setAdmissionsDoctorId(String admissionsDoctorId) {
		this.admissionsDoctorId = admissionsDoctorId;
	}
	/**
	 * @return the referringDoctor
	 */
	public String getReferringDoctor() {
		return referringDoctor;
	}
	/**
	 * @param referringDoctor the referringDoctor to set
	 */
	public void setReferringDoctor(String referringDoctor) {
		this.referringDoctor = referringDoctor;
	}
	/**
	 * @return the referringDoctorId
	 */
	public String getReferringDoctorId() {
		return referringDoctorId;
	}
	/**
	 * @param referringDoctorId the referringDoctorId to set
	 */
	public void setReferringDoctorId(String referringDoctorId) {
		this.referringDoctorId = referringDoctorId;
	}
	/**
	 * @return the consultationDoctor
	 */
	public String getConsultationDoctor() {
		return consultationDoctor;
	}
	/**
	 * @param consultationDoctor the consultationDoctor to set
	 */
	public void setConsultationDoctor(String consultationDoctor) {
		this.consultationDoctor = consultationDoctor;
	}
	/**
	 * @return the consultationDoctorId
	 */
	public String getConsultationDoctorId() {
		return consultationDoctorId;
	}
	/**
	 * @param consultationDoctorId the consultationDoctorId to set
	 */
	public void setConsultationDoctorId(String consultationDoctorId) {
		this.consultationDoctorId = consultationDoctorId;
	}
	/**
	 * @return the hospitalService
	 */
	public String getHospitalService() {
		return hospitalService;
	}
	/**
	 * @param hospitalService the hospitalService to set
	 */
	public void setHospitalService(String hospitalService) {
		this.hospitalService = hospitalService;
	}
	/**
	 * @return the patTempPointOfCare
	 */
	public String getPatTempPointOfCare() {
		return patTempPointOfCare;
	}
	/**
	 * @param patTempPointOfCare the patTempPointOfCare to set
	 */
	public void setPatTempPointOfCare(String patTempPointOfCare) {
		this.patTempPointOfCare = patTempPointOfCare;
	}
	/**
	 * @return the patTempRoom
	 */
	public String getPatTempRoom() {
		return patTempRoom;
	}
	/**
	 * @param patTempRoom the patTempRoom to set
	 */
	public void setPatTempRoom(String patTempRoom) {
		this.patTempRoom = patTempRoom;
	}
	/**
	 * @return the patTempBed
	 */
	public String getPatTempBed() {
		return patTempBed;
	}
	/**
	 * @param patTempBed the patTempBed to set
	 */
	public void setPatTempBed(String patTempBed) {
		this.patTempBed = patTempBed;
	}
	/**
	 * @return the patTempDep
	 */
	public String getPatTempDep() {
		return patTempDep;
	}
	/**
	 * @param patTempDep the patTempDep to set
	 */
	public void setPatTempDep(String patTempDep) {
		this.patTempDep = patTempDep;
	}
	/**
	 * @return the patTempPositionStatus
	 */
	public String getPatTempPositionStatus() {
		return patTempPositionStatus;
	}
	/**
	 * @param patTempPositionStatus the patTempPositionStatus to set
	 */
	public void setPatTempPositionStatus(String patTempPositionStatus) {
		this.patTempPositionStatus = patTempPositionStatus;
	}
	/**
	 * @return the patTempPositionType
	 */
	public String getPatTempPositionType() {
		return patTempPositionType;
	}
	/**
	 * @param patTempPositionType the patTempPositionType to set
	 */
	public void setPatTempPositionType(String patTempPositionType) {
		this.patTempPositionType = patTempPositionType;
	}
	/**
	 * @return the patTempBuilding
	 */
	public String getPatTempBuilding() {
		return patTempBuilding;
	}
	/**
	 * @param patTempBuilding the patTempBuilding to set
	 */
	public void setPatTempBuilding(String patTempBuilding) {
		this.patTempBuilding = patTempBuilding;
	}
	/**
	 * @return the patTempFloor
	 */
	public String getPatTempFloor() {
		return patTempFloor;
	}
	/**
	 * @param patTempFloor the patTempFloor to set
	 */
	public void setPatTempFloor(String patTempFloor) {
		this.patTempFloor = patTempFloor;
	}
	/**
	 * @return the patTempDescription
	 */
	public String getPatTempDescription() {
		return patTempDescription;
	}
	/**
	 * @param patTempDescription the patTempDescription to set
	 */
	public void setPatTempDescription(String patTempDescription) {
		this.patTempDescription = patTempDescription;
	}
	/**
	 * @return the patAdmissionTest
	 */
	public String getPatAdmissionTest() {
		return patAdmissionTest;
	}
	/**
	 * @param patAdmissionTest the patAdmissionTest to set
	 */
	public void setPatAdmissionTest(String patAdmissionTest) {
		this.patAdmissionTest = patAdmissionTest;
	}
	/**
	 * @return the patReAdmission
	 */
	public String getPatReAdmission() {
		return patReAdmission;
	}
	/**
	 * @param patReAdmission the patReAdmission to set
	 */
	public void setPatReAdmission(String patReAdmission) {
		this.patReAdmission = patReAdmission;
	}
	/**
	 * @return the patAdmissionSource
	 */
	public String getPatAdmissionSource() {
		return patAdmissionSource;
	}
	/**
	 * @param patAdmissionSource the patAdmissionSource to set
	 */
	public void setPatAdmissionSource(String patAdmissionSource) {
		this.patAdmissionSource = patAdmissionSource;
	}
	/**
	 * @return the patAmbulatoryStatus
	 */
	public String getPatAmbulatoryStatus() {
		return patAmbulatoryStatus;
	}
	/**
	 * @param patAmbulatoryStatus the patAmbulatoryStatus to set
	 */
	public void setPatAmbulatoryStatus(String patAmbulatoryStatus) {
		this.patAmbulatoryStatus = patAmbulatoryStatus;
	}
	/**
	 * @return the patVip
	 */
	public String getPatVip() {
		return patVip;
	}
	/**
	 * @param patVip the patVip to set
	 */
	public void setPatVip(String patVip) {
		this.patVip = patVip;
	}
	/**
	 * @return the patAdmissionDoctors
	 */
	public String getPatAdmissionDoctors() {
		return patAdmissionDoctors;
	}
	/**
	 * @param patAdmissionDoctors the patAdmissionDoctors to set
	 */
	public void setPatAdmissionDoctors(String patAdmissionDoctors) {
		this.patAdmissionDoctors = patAdmissionDoctors;
	}
	/**
	 * @return the patAdmissionDoctorsId
	 */
	public String getPatAdmissionDoctorsId() {
		return patAdmissionDoctorsId;
	}
	/**
	 * @param patAdmissionDoctorsId the patAdmissionDoctorsId to set
	 */
	public void setPatAdmissionDoctorsId(String patAdmissionDoctorsId) {
		this.patAdmissionDoctorsId = patAdmissionDoctorsId;
	}
	/**
	 * @return the patientClass
	 */
	public String getPatientClass() {
		return patientClass;
	}
	/**
	 * @param patientClass the patientClass to set
	 */
	public void setPatientClass(String patientClass) {
		this.patientClass = patientClass;
	}
	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the patFinancialClass
	 */
	public String getPatFinancialClass() {
		return patFinancialClass;
	}
	/**
	 * @param patFinancialClass the patFinancialClass to set
	 */
	public void setPatFinancialClass(String patFinancialClass) {
		this.patFinancialClass = patFinancialClass;
	}
	/**
	 * @return the roomBedCostPrice
	 */
	public String getRoomBedCostPrice() {
		return roomBedCostPrice;
	}
	/**
	 * @param roomBedCostPrice the roomBedCostPrice to set
	 */
	public void setRoomBedCostPrice(String roomBedCostPrice) {
		this.roomBedCostPrice = roomBedCostPrice;
	}
	/**
	 * @return the courtesyCode
	 */
	public String getCourtesyCode() {
		return courtesyCode;
	}
	/**
	 * @param courtesyCode the courtesyCode to set
	 */
	public void setCourtesyCode(String courtesyCode) {
		this.courtesyCode = courtesyCode;
	}
	/**
	 * @return the creditRating
	 */
	public String getCreditRating() {
		return creditRating;
	}
	/**
	 * @param creditRating the creditRating to set
	 */
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	/**
	 * @return the contractCode
	 */
	public String getContractCode() {
		return contractCode;
	}
	/**
	 * @param contractCode the contractCode to set
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	/**
	 * @return the contractCreateDate
	 */
	public String getContractCreateDate() {
		return contractCreateDate;
	}
	/**
	 * @param contractCreateDate the contractCreateDate to set
	 */
	public void setContractCreateDate(String contractCreateDate) {
		this.contractCreateDate = contractCreateDate;
	}
	/**
	 * @return the contractPrice
	 */
	public String getContractPrice() {
		return contractPrice;
	}
	/**
	 * @param contractPrice the contractPrice to set
	 */
	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}
	/**
	 * @return the contractTime
	 */
	public String getContractTime() {
		return contractTime;
	}
	/**
	 * @param contractTime the contractTime to set
	 */
	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}
	/**
	 * @return the interestRateCode
	 */
	public String getInterestRateCode() {
		return interestRateCode;
	}
	/**
	 * @param interestRateCode the interestRateCode to set
	 */
	public void setInterestRateCode(String interestRateCode) {
		this.interestRateCode = interestRateCode;
	}
	/**
	 * @return the badDebts
	 */
	public String getBadDebts() {
		return badDebts;
	}
	/**
	 * @param badDebts the badDebts to set
	 */
	public void setBadDebts(String badDebts) {
		this.badDebts = badDebts;
	}
	/**
	 * @return the badDebtsCreateDate
	 */
	public String getBadDebtsCreateDate() {
		return badDebtsCreateDate;
	}
	/**
	 * @param badDebtsCreateDate the badDebtsCreateDate to set
	 */
	public void setBadDebtsCreateDate(String badDebtsCreateDate) {
		this.badDebtsCreateDate = badDebtsCreateDate;
	}
	/**
	 * @return the badDebtsCode
	 */
	public String getBadDebtsCode() {
		return badDebtsCode;
	}
	/**
	 * @param badDebtsCode the badDebtsCode to set
	 */
	public void setBadDebtsCode(String badDebtsCode) {
		this.badDebtsCode = badDebtsCode;
	}
	/**
	 * @return the badDebtsPrice
	 */
	public String getBadDebtsPrice() {
		return badDebtsPrice;
	}
	/**
	 * @param badDebtsPrice the badDebtsPrice to set
	 */
	public void setBadDebtsPrice(String badDebtsPrice) {
		this.badDebtsPrice = badDebtsPrice;
	}
	/**
	 * @return the badDebtsRestorePrice
	 */
	public String getBadDebtsRestorePrice() {
		return badDebtsRestorePrice;
	}
	/**
	 * @param badDebtsRestorePrice the badDebtsRestorePrice to set
	 */
	public void setBadDebtsRestorePrice(String badDebtsRestorePrice) {
		this.badDebtsRestorePrice = badDebtsRestorePrice;
	}
	/**
	 * @return the patAccountVoided
	 */
	public String getPatAccountVoided() {
		return patAccountVoided;
	}
	/**
	 * @param patAccountVoided the patAccountVoided to set
	 */
	public void setPatAccountVoided(String patAccountVoided) {
		this.patAccountVoided = patAccountVoided;
	}
	/**
	 * @return the patAccountVoidedDate
	 */
	public String getPatAccountVoidedDate() {
		return patAccountVoidedDate;
	}
	/**
	 * @param patAccountVoidedDate the patAccountVoidedDate to set
	 */
	public void setPatAccountVoidedDate(String patAccountVoidedDate) {
		this.patAccountVoidedDate = patAccountVoidedDate;
	}
	/**
	 * @return the patDischargeDisposition
	 */
	public String getPatDischargeDisposition() {
		return patDischargeDisposition;
	}
	/**
	 * @param patDischargeDisposition the patDischargeDisposition to set
	 */
	public void setPatDischargeDisposition(String patDischargeDisposition) {
		this.patDischargeDisposition = patDischargeDisposition;
	}
	/**
	 * @return the patDischargeLocation
	 */
	public String getPatDischargeLocation() {
		return patDischargeLocation;
	}
	/**
	 * @param patDischargeLocation the patDischargeLocation to set
	 */
	public void setPatDischargeLocation(String patDischargeLocation) {
		this.patDischargeLocation = patDischargeLocation;
	}
	/**
	 * @return the patDietType
	 */
	public String getPatDietType() {
		return patDietType;
	}
	/**
	 * @param patDietType the patDietType to set
	 */
	public void setPatDietType(String patDietType) {
		this.patDietType = patDietType;
	}
	/**
	 * @return the patServiceAgencies
	 */
	public String getPatServiceAgencies() {
		return patServiceAgencies;
	}
	/**
	 * @param patServiceAgencies the patServiceAgencies to set
	 */
	public void setPatServiceAgencies(String patServiceAgencies) {
		this.patServiceAgencies = patServiceAgencies;
	}
	/**
	 * @return the patBedStatus
	 */
	public String getPatBedStatus() {
		return patBedStatus;
	}
	/**
	 * @param patBedStatus the patBedStatus to set
	 */
	public void setPatBedStatus(String patBedStatus) {
		this.patBedStatus = patBedStatus;
	}
	/**
	 * @return the patAccountStatus
	 */
	public String getPatAccountStatus() {
		return patAccountStatus;
	}
	/**
	 * @param patAccountStatus the patAccountStatus to set
	 */
	public void setPatAccountStatus(String patAccountStatus) {
		this.patAccountStatus = patAccountStatus;
	}
	/**
	 * @return the patDeterPointOfCare
	 */
	public String getPatDeterPointOfCare() {
		return patDeterPointOfCare;
	}
	/**
	 * @param patDeterPointOfCare the patDeterPointOfCare to set
	 */
	public void setPatDeterPointOfCare(String patDeterPointOfCare) {
		this.patDeterPointOfCare = patDeterPointOfCare;
	}
	/**
	 * @return the patDeterRoom
	 */
	public String getPatDeterRoom() {
		return patDeterRoom;
	}
	/**
	 * @param patDeterRoom the patDeterRoom to set
	 */
	public void setPatDeterRoom(String patDeterRoom) {
		this.patDeterRoom = patDeterRoom;
	}
	/**
	 * @return the patDeterBed
	 */
	public String getPatDeterBed() {
		return patDeterBed;
	}
	/**
	 * @param patDeterBed the patDeterBed to set
	 */
	public void setPatDeterBed(String patDeterBed) {
		this.patDeterBed = patDeterBed;
	}
	/**
	 * @return the patDeterDep
	 */
	public String getPatDeterDep() {
		return patDeterDep;
	}
	/**
	 * @param patDeterDep the patDeterDep to set
	 */
	public void setPatDeterDep(String patDeterDep) {
		this.patDeterDep = patDeterDep;
	}
	/**
	 * @return the patDeterPositionStatus
	 */
	public String getPatDeterPositionStatus() {
		return patDeterPositionStatus;
	}
	/**
	 * @param patDeterPositionStatus the patDeterPositionStatus to set
	 */
	public void setPatDeterPositionStatus(String patDeterPositionStatus) {
		this.patDeterPositionStatus = patDeterPositionStatus;
	}
	/**
	 * @return the patDeterPositionType
	 */
	public String getPatDeterPositionType() {
		return patDeterPositionType;
	}
	/**
	 * @param patDeterPositionType the patDeterPositionType to set
	 */
	public void setPatDeterPositionType(String patDeterPositionType) {
		this.patDeterPositionType = patDeterPositionType;
	}
	/**
	 * @return the patDeterBuilding
	 */
	public String getPatDeterBuilding() {
		return patDeterBuilding;
	}
	/**
	 * @param patDeterBuilding the patDeterBuilding to set
	 */
	public void setPatDeterBuilding(String patDeterBuilding) {
		this.patDeterBuilding = patDeterBuilding;
	}
	/**
	 * @return the patDeterFloor
	 */
	public String getPatDeterFloor() {
		return patDeterFloor;
	}
	/**
	 * @param patDeterFloor the patDeterFloor to set
	 */
	public void setPatDeterFloor(String patDeterFloor) {
		this.patDeterFloor = patDeterFloor;
	}
	/**
	 * @return the patDeterDescription
	 */
	public String getPatDeterDescription() {
		return patDeterDescription;
	}
	/**
	 * @param patDeterDescription the patDeterDescription to set
	 */
	public void setPatDeterDescription(String patDeterDescription) {
		this.patDeterDescription = patDeterDescription;
	}
	/**
	 * @return the patForTempPointOfCare
	 */
	public String getPatForTempPointOfCare() {
		return patForTempPointOfCare;
	}
	/**
	 * @param patForTempPointOfCare the patForTempPointOfCare to set
	 */
	public void setPatForTempPointOfCare(String patForTempPointOfCare) {
		this.patForTempPointOfCare = patForTempPointOfCare;
	}
	/**
	 * @return the patForTempRoom
	 */
	public String getPatForTempRoom() {
		return patForTempRoom;
	}
	/**
	 * @param patForTempRoom the patForTempRoom to set
	 */
	public void setPatForTempRoom(String patForTempRoom) {
		this.patForTempRoom = patForTempRoom;
	}
	/**
	 * @return the patForTempBed
	 */
	public String getPatForTempBed() {
		return patForTempBed;
	}
	/**
	 * @param patForTempBed the patForTempBed to set
	 */
	public void setPatForTempBed(String patForTempBed) {
		this.patForTempBed = patForTempBed;
	}
	/**
	 * @return the patForTempDep
	 */
	public String getPatForTempDep() {
		return patForTempDep;
	}
	/**
	 * @param patForTempDep the patForTempDep to set
	 */
	public void setPatForTempDep(String patForTempDep) {
		this.patForTempDep = patForTempDep;
	}
	/**
	 * @return the patForTempPositionStatus
	 */
	public String getPatForTempPositionStatus() {
		return patForTempPositionStatus;
	}
	/**
	 * @param patForTempPositionStatus the patForTempPositionStatus to set
	 */
	public void setPatForTempPositionStatus(String patForTempPositionStatus) {
		this.patForTempPositionStatus = patForTempPositionStatus;
	}
	/**
	 * @return the patForTempPositionType
	 */
	public String getPatForTempPositionType() {
		return patForTempPositionType;
	}
	/**
	 * @param patForTempPositionType the patForTempPositionType to set
	 */
	public void setPatForTempPositionType(String patForTempPositionType) {
		this.patForTempPositionType = patForTempPositionType;
	}
	/**
	 * @return the patForTempBuilding
	 */
	public String getPatForTempBuilding() {
		return patForTempBuilding;
	}
	/**
	 * @param patForTempBuilding the patForTempBuilding to set
	 */
	public void setPatForTempBuilding(String patForTempBuilding) {
		this.patForTempBuilding = patForTempBuilding;
	}
	/**
	 * @return the patForTempFloor
	 */
	public String getPatForTempFloor() {
		return patForTempFloor;
	}
	/**
	 * @param patForTempFloor the patForTempFloor to set
	 */
	public void setPatForTempFloor(String patForTempFloor) {
		this.patForTempFloor = patForTempFloor;
	}
	/**
	 * @return the patForTempDescription
	 */
	public String getPatForTempDescription() {
		return patForTempDescription;
	}
	/**
	 * @param patForTempDescription the patForTempDescription to set
	 */
	public void setPatForTempDescription(String patForTempDescription) {
		this.patForTempDescription = patForTempDescription;
	}
	/**
	 * @return the admitDate
	 */
	public Date getAdmitDate() {
		return admitDate;
	}
	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	/**
	 * @return the dischargeDate
	 */
	public Date getDischargeDate() {
		return dischargeDate;
	}
	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	/**
	 * @return the patDifference
	 */
	public String getPatDifference() {
		return patDifference;
	}
	/**
	 * @param patDifference the patDifference to set
	 */
	public void setPatDifference(String patDifference) {
		this.patDifference = patDifference;
	}
	/**
	 * @return the patTotalCost
	 */
	public String getPatTotalCost() {
		return patTotalCost;
	}
	/**
	 * @param patTotalCost the patTotalCost to set
	 */
	public void setPatTotalCost(String patTotalCost) {
		this.patTotalCost = patTotalCost;
	}
	/**
	 * @return the patTotalDispatch
	 */
	public String getPatTotalDispatch() {
		return patTotalDispatch;
	}
	/**
	 * @param patTotalDispatch the patTotalDispatch to set
	 */
	public void setPatTotalDispatch(String patTotalDispatch) {
		this.patTotalDispatch = patTotalDispatch;
	}
	/**
	 * @return the patTotalAmountPayable
	 */
	public String getPatTotalAmountPayable() {
		return patTotalAmountPayable;
	}
	/**
	 * @param patTotalAmountPayable the patTotalAmountPayable to set
	 */
	public void setPatTotalAmountPayable(String patTotalAmountPayable) {
		this.patTotalAmountPayable = patTotalAmountPayable;
	}
	/**
	 * @return the patSpareId
	 */
	public String getPatSpareId() {
		return patSpareId;
	}
	/**
	 * @param patSpareId the patSpareId to set
	 */
	public void setPatSpareId(String patSpareId) {
		this.patSpareId = patSpareId;
	}
	/**
	 * @return the patVisitLogo
	 */
	public String getPatVisitLogo() {
		return patVisitLogo;
	}
	/**
	 * @param patVisitLogo the patVisitLogo to set
	 */
	public void setPatVisitLogo(String patVisitLogo) {
		this.patVisitLogo = patVisitLogo;
	}
	/**
	 * @return the otherMedicalInstitutions
	 */
	public String getOtherMedicalInstitutions() {
		return otherMedicalInstitutions;
	}
	/**
	 * @param otherMedicalInstitutions the otherMedicalInstitutions to set
	 */
	public void setOtherMedicalInstitutions(String otherMedicalInstitutions) {
		this.otherMedicalInstitutions = otherMedicalInstitutions;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the createId
	 */
	public String getCreateId() {
		return createId;
	}
	/**
	 * @param createId the createId to set
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	/**
	 * @return the voidedDate
	 */
	public Date getVoidedDate() {
		return voidedDate;
	}
	/**
	 * @param voidedDate the voidedDate to set
	 */
	public void setVoidedDate(Date voidedDate) {
		this.voidedDate = voidedDate;
	}
	/**
	 * @return the voidedId
	 */
	public String getVoidedId() {
		return voidedId;
	}
	/**
	 * @param voidedId the voidedId to set
	 */
	public void setVoidedId(String voidedId) {
		this.voidedId = voidedId;
	}
	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the modifyId
	 */
	public String getModifyId() {
		return modifyId;
	}
	/**
	 * @param modifyId the modifyId to set
	 */
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	/**
	 * @return the custom1
	 */
	public String getCustom1() {
		return custom1;
	}
	/**
	 * @param custom1 the custom1 to set
	 */
	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}
	/**
	 * @return the custom2
	 */
	public String getCustom2() {
		return custom2;
	}
	/**
	 * @param custom2 the custom2 to set
	 */
	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}
	/**
	 * @return the custom3
	 */
	public String getCustom3() {
		return custom3;
	}
	/**
	 * @param custom3 the custom3 to set
	 */
	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}
	/**
	 * @return the custom4
	 */
	public String getCustom4() {
		return custom4;
	}
	/**
	 * @param custom4 the custom4 to set
	 */
	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}
	/**
	 * @return the custom5
	 */
	public String getCustom5() {
		return custom5;
	}
	/**
	 * @param custom5 the custom5 to set
	 */
	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}
	/**
	 * @return the patNurseCode
	 */
	public String getPatNurseCode() {
		return patNurseCode;
	}
	/**
	 * @param patNurseCode the patNurseCode to set
	 */
	public void setPatNurseCode(String patNurseCode) {
		this.patNurseCode = patNurseCode;
	}
	/**
	 * @return the patNurseName
	 */
	public String getPatNurseName() {
		return patNurseName;
	}
	/**
	 * @param patNurseName the patNurseName to set
	 */
	public void setPatNurseName(String patNurseName) {
		this.patNurseName = patNurseName;
	}
	/**
	 * @return the tend
	 */
	public String getTend() {
		return tend;
	}
	/**
	 * @param tend the tend to set
	 */
	public void setTend(String tend) {
		this.tend = tend;
	}
	/**
	 * @return the babyFlag
	 */
	public String getBabyFlag() {
		return babyFlag;
	}
	/**
	 * @param babyFlag the babyFlag to set
	 */
	public void setBabyFlag(String babyFlag) {
		this.babyFlag = babyFlag;
	}
	/**
	 * @return the admitWeight
	 */
	public String getAdmitWeight() {
		return admitWeight;
	}
	/**
	 * @param admitWeight the admitWeight to set
	 */
	public void setAdmitWeight(String admitWeight) {
		this.admitWeight = admitWeight;
	}
	/**
	 * @return the birthWeight
	 */
	public String getBirthWeight() {
		return birthWeight;
	}
	/**
	 * @param birthWeight the birthWeight to set
	 */
	public void setBirthWeight(String birthWeight) {
		this.birthWeight = birthWeight;
	}
	/**
	 * @return the operCode
	 */
	public String getOperCode() {
		return operCode;
	}
	/**
	 * @param operCode the operCode to set
	 */
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	/**
	 * @return the operDate
	 */
	public Date getOperDate() {
		return operDate;
	}
	/**
	 * @param operDate the operDate to set
	 */
	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return the insuranceType
	 */
	public String getInsuranceType() {
		return insuranceType;
	}
	/**
	 * @param insuranceType the insuranceType to set
	 */
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}
	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	/**
	 * @return the contactRelations
	 */
	public String getContactRelations() {
		return contactRelations;
	}
	/**
	 * @param contactRelations the contactRelations to set
	 */
	public void setContactRelations(String contactRelations) {
		this.contactRelations = contactRelations;
	}
	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return contactAddress;
	}
	/**
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @return the patCategoryName
	 */
	public String getPatCategoryName() {
		return patCategoryName;
	}
	/**
	 * @param patCategoryName the patCategoryName to set
	 */
	public void setPatCategoryName(String patCategoryName) {
		this.patCategoryName = patCategoryName;
	}
	/**
	 * @return the payRate
	 */
	public String getPayRate() {
		return payRate;
	}
	/**
	 * @param payRate the payRate to set
	 */
	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}
	/**
	 * @return the dischargeName
	 */
	public String getDischargeName() {
		return dischargeName;
	}
	/**
	 * @param dischargeName the dischargeName to set
	 */
	public void setDischargeName(String dischargeName) {
		this.dischargeName = dischargeName;
	}
	/**
	 * @return the insuranceName
	 */
	public String getInsuranceName() {
		return insuranceName;
	}
	/**
	 * @param insuranceName the insuranceName to set
	 */
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	/**
	 * @return the admissionName
	 */
	public String getAdmissionName() {
		return admissionName;
	}
	/**
	 * @param admissionName the admissionName to set
	 */
	public void setAdmissionName(String admissionName) {
		this.admissionName = admissionName;
	}
	/**
	 * @return the ipStatusName
	 */
	public String getIpStatusName() {
		return ipStatusName;
	}
	/**
	 * @param ipStatusName the ipStatusName to set
	 */
	public void setIpStatusName(String ipStatusName) {
		this.ipStatusName = ipStatusName;
	}
	/**
	 * @return the dificultyName
	 */
	public String getDificultyName() {
		return dificultyName;
	}
	/**
	 * @param dificultyName the dificultyName to set
	 */
	public void setDificultyName(String dificultyName) {
		this.dificultyName = dificultyName;
	}
	/**
	 * @return the admitWayName
	 */
	public String getAdmitWayName() {
		return admitWayName;
	}
	/**
	 * @param admitWayName the admitWayName to set
	 */
	public void setAdmitWayName(String admitWayName) {
		this.admitWayName = admitWayName;
	}
	/**
	 * @return the admitWeightUnit
	 */
	public String getAdmitWeightUnit() {
		return admitWeightUnit;
	}
	/**
	 * @param admitWeightUnit the admitWeightUnit to set
	 */
	public void setAdmitWeightUnit(String admitWeightUnit) {
		this.admitWeightUnit = admitWeightUnit;
	}
	/**
	 * @return the babyWeightUnit
	 */
	public String getBabyWeightUnit() {
		return babyWeightUnit;
	}
	/**
	 * @param babyWeightUnit the babyWeightUnit to set
	 */
	public void setBabyWeightUnit(String babyWeightUnit) {
		this.babyWeightUnit = babyWeightUnit;
	}
	/**
	 * @return the medicinelimitamount
	 */
	public BigDecimal getMedicinelimitamount() {
		return medicinelimitamount;
	}
	/**
	 * @param medicinelimitamount the medicinelimitamount to set
	 */
	public void setMedicinelimitamount(BigDecimal medicinelimitamount) {
		this.medicinelimitamount = medicinelimitamount;
	}
	/**
	 * @return the sickbedlimitamount
	 */
	public BigDecimal getSickbedlimitamount() {
		return sickbedlimitamount;
	}
	/**
	 * @param sickbedlimitamount the sickbedlimitamount to set
	 */
	public void setSickbedlimitamount(BigDecimal sickbedlimitamount) {
		this.sickbedlimitamount = sickbedlimitamount;
	}
	/**
	 * @return the examinelimitamount
	 */
	public BigDecimal getExaminelimitamount() {
		return examinelimitamount;
	}
	/**
	 * @param examinelimitamount the examinelimitamount to set
	 */
	public void setExaminelimitamount(BigDecimal examinelimitamount) {
		this.examinelimitamount = examinelimitamount;
	}
	/**
	 * @return the curelimitamount
	 */
	public BigDecimal getCurelimitamount() {
		return curelimitamount;
	}
	/**
	 * @param curelimitamount the curelimitamount to set
	 */
	public void setCurelimitamount(BigDecimal curelimitamount) {
		this.curelimitamount = curelimitamount;
	}
	/**
	 * @return the hiupStatus
	 */
	public String getHiupStatus() {
		return hiupStatus;
	}
	/**
	 * @param hiupStatus the hiupStatus to set
	 */
	public void setHiupStatus(String hiupStatus) {
		this.hiupStatus = hiupStatus;
	}
	/**
	 * @return the hiupErrorInfo
	 */
	public String getHiupErrorInfo() {
		return hiupErrorInfo;
	}
	/**
	 * @param hiupErrorInfo the hiupErrorInfo to set
	 */
	public void setHiupErrorInfo(String hiupErrorInfo) {
		this.hiupErrorInfo = hiupErrorInfo;
	}
	/**
	 * @return the admissionDomain
	 */
	public String getAdmissionDomain() {
		return admissionDomain;
	}
	/**
	 * @param admissionDomain the admissionDomain to set
	 */
	public void setAdmissionDomain(String admissionDomain) {
		this.admissionDomain = admissionDomain;
	}
	/**
	 * @return the admissionSourceDomain
	 */
	public String getAdmissionSourceDomain() {
		return admissionSourceDomain;
	}
	/**
	 * @param admissionSourceDomain the admissionSourceDomain to set
	 */
	public void setAdmissionSourceDomain(String admissionSourceDomain) {
		this.admissionSourceDomain = admissionSourceDomain;
	}
	/**
	 * @return the admissionSourceName
	 */
	public String getAdmissionSourceName() {
		return admissionSourceName;
	}
	/**
	 * @param admissionSourceName the admissionSourceName to set
	 */
	public void setAdmissionSourceName(String admissionSourceName) {
		this.admissionSourceName = admissionSourceName;
	}
	/**
	 * @return the patientClassName
	 */
	public String getPatientClassName() {
		return patientClassName;
	}
	/**
	 * @param patientClassName the patientClassName to set
	 */
	public void setPatientClassName(String patientClassName) {
		this.patientClassName = patientClassName;
	}
	/**
	 * @return the patientClassDomain
	 */
	public String getPatientClassDomain() {
		return patientClassDomain;
	}
	/**
	 * @param patientClassDomain the patientClassDomain to set
	 */
	public void setPatientClassDomain(String patientClassDomain) {
		this.patientClassDomain = patientClassDomain;
	}
	/**
	 * @return the ipStatusDomain
	 */
	public String getIpStatusDomain() {
		return ipStatusDomain;
	}
	/**
	 * @param ipStatusDomain the ipStatusDomain to set
	 */
	public void setIpStatusDomain(String ipStatusDomain) {
		this.ipStatusDomain = ipStatusDomain;
	}
	/**
	 * @return the dificultyDomain
	 */
	public String getDificultyDomain() {
		return dificultyDomain;
	}
	/**
	 * @param dificultyDomain the dificultyDomain to set
	 */
	public void setDificultyDomain(String dificultyDomain) {
		this.dificultyDomain = dificultyDomain;
	}
	/**
	 * @return the dischargeDomain
	 */
	public String getDischargeDomain() {
		return dischargeDomain;
	}
	/**
	 * @param dischargeDomain the dischargeDomain to set
	 */
	public void setDischargeDomain(String dischargeDomain) {
		this.dischargeDomain = dischargeDomain;
	}
	/**
	 * @return the accountStatusName
	 */
	public String getAccountStatusName() {
		return accountStatusName;
	}
	/**
	 * @param accountStatusName the accountStatusName to set
	 */
	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}
	/**
	 * @return the accountStatusDomain
	 */
	public String getAccountStatusDomain() {
		return accountStatusDomain;
	}
	/**
	 * @param accountStatusDomain the accountStatusDomain to set
	 */
	public void setAccountStatusDomain(String accountStatusDomain) {
		this.accountStatusDomain = accountStatusDomain;
	}
	/**
	 * @return the patCategorySystem
	 */
	public String getPatCategorySystem() {
		return patCategorySystem;
	}
	/**
	 * @param patCategorySystem the patCategorySystem to set
	 */
	public void setPatCategorySystem(String patCategorySystem) {
		this.patCategorySystem = patCategorySystem;
	}
	/**
	 * @return the mothersID
	 */
	public String getMothersID() {
		return mothersID;
	}
	/**
	 * @param mothersID the mothersID to set
	 */
	public void setMothersID(String mothersID) {
		this.mothersID = mothersID;
	}
	/**
	 * @return the mothersFlowID
	 */
	public String getMothersFlowID() {
		return mothersFlowID;
	}
	/**
	 * @param mothersFlowID the mothersFlowID to set
	 */
	public void setMothersFlowID(String mothersFlowID) {
		this.mothersFlowID = mothersFlowID;
	}
	/**
	 * @return the mothersFlowDomain
	 */
	public String getMothersFlowDomain() {
		return mothersFlowDomain;
	}
	/**
	 * @param mothersFlowDomain the mothersFlowDomain to set
	 */
	public void setMothersFlowDomain(String mothersFlowDomain) {
		this.mothersFlowDomain = mothersFlowDomain;
	}
	/**
	 * @return the mothersName
	 */
	public String getMothersName() {
		return mothersName;
	}
	/**
	 * @param mothersName the mothersName to set
	 */
	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}
	/**
	 * @return the mothersDomain
	 */
	public String getMothersDomain() {
		return mothersDomain;
	}
	/**
	 * @param mothersDomain the mothersDomain to set
	 */
	public void setMothersDomain(String mothersDomain) {
		this.mothersDomain = mothersDomain;
	}
	/**
	 * @return the patientSourceName
	 */
	public String getPatientSourceName() {
		return patientSourceName;
	}
	/**
	 * @param patientSourceName the patientSourceName to set
	 */
	public void setPatientSourceName(String patientSourceName) {
		this.patientSourceName = patientSourceName;
	}
	/**
	 * @return the oldPatientId
	 */
	public String getOldPatientId() {
		return oldPatientId;
	}
	/**
	 * @param oldPatientId the oldPatientId to set
	 */
	public void setOldPatientId(String oldPatientId) {
		this.oldPatientId = oldPatientId;
	}
	/**
	 * @return the oldPatientDomain
	 */
	public String getOldPatientDomain() {
		return oldPatientDomain;
	}
	/**
	 * @param oldPatientDomain the oldPatientDomain to set
	 */
	public void setOldPatientDomain(String oldPatientDomain) {
		this.oldPatientDomain = oldPatientDomain;
	}
	/**
	 * @return the oldVisitFlowId
	 */
	public String getOldVisitFlowId() {
		return oldVisitFlowId;
	}
	/**
	 * @param oldVisitFlowId the oldVisitFlowId to set
	 */
	public void setOldVisitFlowId(String oldVisitFlowId) {
		this.oldVisitFlowId = oldVisitFlowId;
	}
	/**
	 * @return the oldVisitFlowDomain
	 */
	public String getOldVisitFlowDomain() {
		return oldVisitFlowDomain;
	}
	/**
	 * @param oldVisitFlowDomain the oldVisitFlowDomain to set
	 */
	public void setOldVisitFlowDomain(String oldVisitFlowDomain) {
		this.oldVisitFlowDomain = oldVisitFlowDomain;
	}
	/**
	 * @return the oldVisitId
	 */
	public String getOldVisitId() {
		return oldVisitId;
	}
	/**
	 * @param oldVisitId the oldVisitId to set
	 */
	public void setOldVisitId(String oldVisitId) {
		this.oldVisitId = oldVisitId;
	}
	/**
	 * @return the oldPersonId
	 */
	public String getOldPersonId() {
		return oldPersonId;
	}
	/**
	 * @param oldPersonId the oldPersonId to set
	 */
	public void setOldPersonId(String oldPersonId) {
		this.oldPersonId = oldPersonId;
	}
	/**
	 * @return the oldStatus
	 */
	public String getOldStatus() {
		return oldStatus;
	}
	/**
	 * @param oldStatus the oldStatus to set
	 */
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	/**
	 * @return the oldInfo
	 */
	public String getOldInfo() {
		return oldInfo;
	}
	/**
	 * @param oldInfo the oldInfo to set
	 */
	public void setOldInfo(String oldInfo) {
		this.oldInfo = oldInfo;
	}
	
	

}
