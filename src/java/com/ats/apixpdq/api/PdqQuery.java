package com.ats.apixpdq.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ats.aexchange.datamodel.Address;
import com.ats.aexchange.datamodel.DriversLicense;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.datamodel.PersonName;
import com.ats.aexchange.datamodel.PhoneNumber;
import com.ats.aexchange.datamodel.SharedEnums.SexType;

/**
 * This is query object used for Patient Demographics Query.
 * 
 * @author yrh
 * @version 1.0,2012-07-03
 */
public class PdqQuery {
	private PatientIdentifier patientIdentifier;
	
	private String HospitalID; //住院号,pm-2012-07-03
	
	private String IdentityNO; //身份证件号,pm-2012-07-03
	
	private String InsuranceNO; //医疗保险号
	
	private String SocailCardNO;//社会保险号
	
	private String IdenDomainID; //机构号
	
	private String MultipleBirthIndicator; //多胞胎
	
	private Integer BirthOrder; //出生顺序
	
	private String MaritalStatus; //婚姻状态
	
	private String NamePY; //姓名拼音
	
	private String PatientPhone; //病人联系电话
	
	private PersonName personName;
	
	private PersonName motherMaidenName;
	
	private DriversLicense driversLicense;
	
	private String ssn;
	
	private SexType sex;
	
	private Calendar birthDate;
	
    private PatientIdentifier patientAccountNumber;
    
    private BigDecimal personId;
	
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
	
	private String admitDate;
	
	private String dischargeDate;
	
	private String patDifference;
	
	private String patTotalCost;
	
	private String patTotalDispatch;
	
	private String patTotalAmountPayable;
	
	private String patSpareId;
	
	private String patVisitLogo;
	
	private String otherMedicalInstitutions;
	
	private String createDate;
	
	private String createId;
	
	private String voidedDate;
	
	private String voidedId;
	
	private String modifyDate;
	
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
	
	private String race;
    
    private String primaryLanguage;
    
    private String religion;
    
    private String ethnicGroup;
    
    private String birthPlace;
    
    private String nameSpellCode;
    
	private String nameWbCode;
	
	private String birthProvince;
	
	private String birthCity;
	
	private String birthCounty;
	
	private String birthZip;
	
	private String citizenCard;
	
	private String medicalCertificate;
	
	private String meicarePerson;
	
	private String elderCertificate;
	
	private String opcaseno;
	
	private String genderName;
	
	private String genderDomain;
	
	private String ethnicName;
	
	private String ethincDomain;
	
	private String raceName;
	
	private String raceDomain;
	
	private String nationality;
	
	private String nationalityName;
	
	private String nationalityDomain;
	
	private String maritalStatusName;
	
	private String maritalDomain;
	
	private String degree;
	
	private String degreeName;
	
	private String degreeDomain;
	
	private String homeProvince;
	
	private String homeCity;
	
	private String homeCounty;
	
	private String homeZip;
	
	private String homeAddress;
	
	private String registeredProvince;
	
	private String registeredCity;
	
	private String registeredCounty;
	
	private String registeredZip;
	
	private String registeredAddress;
	
	private String nativeProvince;
	
	private String nativeCity;
	
	private String profession;
	
	private String professionName;
	
	private String professionDomain;
	
	private String workZip;
	
	private String workAddress;
	
	private String privateNumber;
	
	private String homeNumber;
	
	private String workNumber;
	
	private String guardianPerson;
	
	private String vip;
	
	private String company;
	
	private String patNurseCode;
	
	private String patNurseName;
	
	private String tend;
	
	private String babyFlag;
	
	private String admitWeight;
	
	private String birthWeight;
	
	private String operCode;
	
	private Date operDate;
	
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
	
	private String email;
	/**
	 * @return the custom6
	 */
	public String getCustom6() {
		return custom6;
	}

	/**
	 * @param custom6 the custom6 to set
	 */
	public void setCustom6(String custom6) {
		this.custom6 = custom6;
	}

	/**
	 * @return the custom7
	 */
	public String getCustom7() {
		return custom7;
	}

	/**
	 * @param custom7 the custom7 to set
	 */
	public void setCustom7(String custom7) {
		this.custom7 = custom7;
	}

	/**
	 * @return the custom8
	 */
	public String getCustom8() {
		return custom8;
	}

	/**
	 * @param custom8 the custom8 to set
	 */
	public void setCustom8(String custom8) {
		this.custom8 = custom8;
	}

	/**
	 * @return the custom9
	 */
	public String getCustom9() {
		return custom9;
	}

	/**
	 * @param custom9 the custom9 to set
	 */
	public void setCustom9(String custom9) {
		this.custom9 = custom9;
	}

	/**
	 * @return the custom10
	 */
	public String getCustom10() {
		return custom10;
	}

	/**
	 * @param custom10 the custom10 to set
	 */
	public void setCustom10(String custom10) {
		this.custom10 = custom10;
	}

	/**
	 * @return the custom11
	 */
	public String getCustom11() {
		return custom11;
	}

	/**
	 * @param custom11 the custom11 to set
	 */
	public void setCustom11(String custom11) {
		this.custom11 = custom11;
	}

	/**
	 * @return the custom12
	 */
	public String getCustom12() {
		return custom12;
	}

	/**
	 * @param custom12 the custom12 to set
	 */
	public void setCustom12(String custom12) {
		this.custom12 = custom12;
	}

	/**
	 * @return the custom13
	 */
	public String getCustom13() {
		return custom13;
	}

	/**
	 * @param custom13 the custom13 to set
	 */
	public void setCustom13(String custom13) {
		this.custom13 = custom13;
	}

	/**
	 * @return the custom14
	 */
	public String getCustom14() {
		return custom14;
	}

	/**
	 * @param custom14 the custom14 to set
	 */
	public void setCustom14(String custom14) {
		this.custom14 = custom14;
	}

	/**
	 * @return the custom15
	 */
	public String getCustom15() {
		return custom15;
	}

	/**
	 * @param custom15 the custom15 to set
	 */
	public void setCustom15(String custom15) {
		this.custom15 = custom15;
	}

	/**
	 * @return the custom16
	 */
	public String getCustom16() {
		return custom16;
	}

	/**
	 * @param custom16 the custom16 to set
	 */
	public void setCustom16(String custom16) {
		this.custom16 = custom16;
	}

	/**
	 * @return the custom17
	 */
	public String getCustom17() {
		return custom17;
	}

	/**
	 * @param custom17 the custom17 to set
	 */
	public void setCustom17(String custom17) {
		this.custom17 = custom17;
	}

	/**
	 * @return the custom18
	 */
	public String getCustom18() {
		return custom18;
	}

	/**
	 * @param custom18 the custom18 to set
	 */
	public void setCustom18(String custom18) {
		this.custom18 = custom18;
	}

	/**
	 * @return the custom19
	 */
	public String getCustom19() {
		return custom19;
	}

	/**
	 * @param custom19 the custom19 to set
	 */
	public void setCustom19(String custom19) {
		this.custom19 = custom19;
	}

	/**
	 * @return the custom20
	 */
	public String getCustom20() {
		return custom20;
	}

	/**
	 * @param custom20 the custom20 to set
	 */
	public void setCustom20(String custom20) {
		this.custom20 = custom20;
	}

	/**
	 * @return the personId
	 */
	public BigDecimal getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
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
	public String getAdmitDate() {
		return admitDate;
	}

	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}

	/**
	 * @return the dischargeDate
	 */
	public String getDischargeDate() {
		return dischargeDate;
	}

	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(String dischargeDate) {
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
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
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
	public String getVoidedDate() {
		return voidedDate;
	}

	/**
	 * @param voidedDate the voidedDate to set
	 */
	public void setVoidedDate(String voidedDate) {
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
	public String getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
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
	 * @param returnDomains the returnDomains to set
	 */
	public void setReturnDomains(List<Identifier> returnDomains) {
		this.returnDomains = returnDomains;
	}

	/* Fields with multiple values for patient, but only one in query */
	private PhoneNumber phone;
	private Address  address;
	
    /*for non-patient related field*/
	//confidence is not used if it is <0;
	private double confidence = -1; 
	//how many patient records to retrieve; defaults to all if<0
	private int howMany = -1;  
	//the reference pointer for subsequent query
	private String continuationPointer;  
	//the domains in which patients will be returned
	private List<Identifier> returnDomains = new ArrayList<Identifier>(); 
	
	//the query tag (the query id), used by QPD-2 in PDQ Query, 
	//and by QID-1 in Cancel Query.
	private String queryTag;
	//the query name, used by QPD-1 in PDQ Query, and by QID-2 in Cancel Query
	private String queryName;
	
    /*Wild Card search*/
    //Wild card prefix characters. Applies only to the string fields
    private String prefix;
    //Wild card suffix characters. Applies only to the string fields
    private String suffix;
    	
    
    
   
	
    /**
	 * Gets and set the MaritalStatus.
	 * 
	 * @return the MaritalStatus
	 */
	public String getMaritalStatus() {
		return MaritalStatus;
	}
	
	public void setMaritalStatus(String MaritalStatus) {
		this.MaritalStatus = MaritalStatus;
	}
	
	
    /**
	 * Gets and set the BirthOrder.
	 * 
	 * @return the BirthOrder
	 */
	public Integer getBirthOrder() {
		return BirthOrder;
	}
	
	public void setBirthOrder(Integer BirthOrder) {
		this.BirthOrder = BirthOrder;
	}
	
	
    /**
	 * Gets and set the MultipleBirthIndicator.
	 * 
	 * @return the MultipleBirthIndicator
	 */
	public String getMultipleBirthIndicator() {
		return MultipleBirthIndicator;
	}
	
	public void setMultipleBirthIndicator(String MultipleBirthIndicator) {
		this.MultipleBirthIndicator = MultipleBirthIndicator;
	}
	
	
	/**
	 * Gets the patient name.
	 * 
	 * @return the personName
	 */
	public PersonName getPersonName() {
		return personName;
	}
	
	/**
	 * Sets the patient name.
	 * 
	 * @param personName the personName to set
	 */
	public void setPersonName(PersonName personName) {
		this.personName = personName;
	}
	
	/**
	 * Gets the mother's maiden name.
	 * 
	 * @return the mother's maiden name
	 */
	public PersonName getMotherMaidenName() {
		return motherMaidenName;
	}
	
	/**
	 * Sets the mother's maiden name.
	 * 
	 * @param motherMaidenName the mother's maiden name to set
	 */
	public void setMotherMaidenName(PersonName motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}
	
	/**
	 * Gets the driver's license.
	 * 
	 * @return the driver's license
	 */
	public DriversLicense getDriversLicense() {
		return driversLicense;
	}
	
	/**
	 * Sets the driver's license.
	 *  
	 * @param driversLicense the driver's license to set
	 */
	public void setDriversLicense(DriversLicense driversLicense) {
		this.driversLicense = driversLicense;
	}
	
	/**
	 * Gets the social security number.
	 * 
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	
	
	/**
	 * Sets the social security number.
	 * 
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	/**
	 * Sets the IdentityNO.
	 * 
	 * @param IdentityNO the IdentityNO to set
	 */
	public void setIdentityNO(String IdentityNO) {
		this.IdentityNO = IdentityNO;
	}
	
	/**
	 * Gets the IdentityNO.
	 * 
	 * @return the IdentityNO
	 */
	public String getIdentityNO() {
		return IdentityNO;
	}
	
	/**
	 * Sets the NamePY.
	 * 
	 * @param NamePY the NamePY to set
	 */
	public void setNamePY(String NamePY) {
		this.NamePY = NamePY;
	}
	
	/**
	 * Gets the NamePY.
	 * 
	 * @return the NamePY
	 */
	public String getNamePY() {
		return NamePY;
	}
	
	
	/**
	 * Sets the PatientPhone.
	 * 
	 * @param PatientPhone the PatientPhone to set
	 */
	public void setPatientPhone(String PatientPhone) {
		this.PatientPhone = PatientPhone;
	}
	
	/**
	 * Gets the PatientPhone.
	 * 
	 * @return the PatientPhone
	 */
	public String getPatientPhone() {
		return PatientPhone;
	}
	
	
	
	/**
	 * Sets the InsuranceNO.
	 * 
	 * @param InsuranceNO the InsuranceNO to set
	 */
	public void setInsuranceNO(String InsuranceNO) {
		this.InsuranceNO = InsuranceNO;
	}
	
	/**
	 * Gets the InsuranceNO.
	 * 
	 * @return the InsuranceNO
	 */
	public String getInsuranceNO() {
		return InsuranceNO;
	}
	
	/**
	 * Sets the SocailCardNO.
	 * 
	 * @param SocailCardNO the SocailCardNO to set
	 */
	public void setSocailCardNO(String SocailCardNO) {
		this.SocailCardNO = SocailCardNO;
	}
	
	/**
	 * Gets the SocailCardNO.
	 * 
	 * @return the SocailCardNO
	 */
	public String getSocailCardNO() {
		return SocailCardNO;
	}
	
	/**
	 * Sets the HospitalID.
	 * 
	 * @param HospitalID the HospitalID to set
	 */
	public void setHospitalID(String HospitalID) {
		this.HospitalID = HospitalID;
	}
	
	/**
	 * Gets the HospitalID.
	 * 
	 * @return the HospitalID
	 */
	public String getHospitalID() {
		return HospitalID;
	}
	
	
	/**
	 * Sets the IdenDomainID.
	 * 
	 * @param IdenDomainID the IdenDomainID to set
	 */
	public void setIdenDomainID(String IdenDomainID) {
		this.IdenDomainID = IdenDomainID;
	}
	
	/**
	 * Gets the IdenDomainID.
	 * 
	 * @return the IdenDomainID
	 */
	public String getIdenDomainID() {
		return IdenDomainID;
	}
	/**
	 * Gets the sex.
	 * 
	 * @return the sex
	 */
	public SexType getSex() {
		return sex;
	}
	
	/**
	 * Sets the sex.
	 * 
	 * @param sex the sex to set
	 */
	public void setSex(SexType sex) {
		this.sex = sex;
	}
	
	/**
	 * Gets the birth date.
	 * 
	 * @return the birthDate
	 */
	public Calendar getBirthDate() {
		return birthDate;
	}
	
	/**
	 * Sets the birth date.
	 * 
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * Gets the patient account number.
	 * 
	 * @return the patient account number
	 */
	public PatientIdentifier getPatientAccountNumber() {
		return patientAccountNumber;
	}
	
	/**
	 * Sets the patient account number.
	 * 
	 * @param patientAccountNumber the patient account number to set
	 */
	public void setPatientAccountNumber(PatientIdentifier patientAccountNumber) {
		this.patientAccountNumber = patientAccountNumber;
	}
	
	/**
	 * Gets the phone number.
	 * 
	 * @return the phone number
	 */
	public PhoneNumber getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone number.
	 * 
	 * @param phone the phone to set
	 */
	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 * 
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * Gets the expected confidence of this search. Ignore confidence
	 * if eMPI does not support confidence search. 
	 * 
	 * @return the confidence
	 */
	public double getConfidence() {
		return confidence;
	}
	
	/**
	 * Sets the confidence. Ignore confidence
	 * if eMPI does not support confidence search. 
	 * 
	 * @param confidence the confidence to set
	 */
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
	/**
	 * Gets the number of patient records to return for this 
	 * PDQ search.
	 * 
	 * @return the howMany the number patient records
	 */
	public int getHowMany() {
		return howMany;
	}
	
	/**
	 * Sets the number of patient records to return for this
	 * PDQ search.
	 * 
	 * @param howMany the number of patient records to set
	 */
	public void setHowMany(int howMany) {
		this.howMany = howMany;
	}
	
	/**
	 * Gets the continuation pointer for this PDQ query search.
	 * 
	 * @return the continuation pointer
	 */
	public String getContinuationPointer() {
		return continuationPointer;
	}
	
	/**
	 * Sets the continuation pointer for this PDQ query search.
	 * 
	 * @param continuationPointer the continuation pointer to set
	 */
	public void setContinuationPointer(String continuationPointer) {
		this.continuationPointer = continuationPointer;
	}
	 
	/**
	 * Gets the return domains.
	 * 
	 * @return the list of return domains in the form of <code>Identifier</code>.
	 */
	public List<Identifier> getReturnDomains() {
		return returnDomains;
	}

	/**
	 * Adds a list of return domains.
	 * 
	 * @param returnDomains the return domains to add
	 */
	public void addReturnDomains(List<Identifier> returnDomains) {
		this.returnDomains.addAll(returnDomains);
	}

	/**
	 * Gets the query tag of this PDQ search.
	 * 
	 * @return the query tag
	 */
	public String getQueryTag() {
		return queryTag;
	}
	
	/**
	 * Sets the query tag of this PDQ search.
	 * 
	 * @param queryTag the query tag to set
	 */
	public void setQueryTag(String queryTag) {
		this.queryTag = queryTag;
	}

	/**
	 * Gets the query name of this PDQ search.
	 * 
	 * @return the query name
	 */
	public String getQueryName() {
		return queryName;
	}
	
	/**
	 * Sets the query name of this PDQ search.
	 * 
	 * @param queryName the query name to set
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	
	/**
	 * Gets the prefix wild card search string.
	 * 
	 * @return the prefix wild card
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * Sets the prefix wild card search string.
	 * 
	 * @param prefix the prefix wild card to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * Gets the suffix wild search string.
	 * 
	 * @return the suffix wild card
	 */
	public String getSuffix() {
		return suffix;
	}
	
	/**
	 * Sets the suffix wild card search string.
	 * 
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Gets the patient identifier.
	 * 
	 * @return the patient identifier
	 */
	public PatientIdentifier getPatientIdentifier() {
		return patientIdentifier;
	}
	
	/**
	 * Sets the patient identifier.
	 * 
	 * @param patientIdentifier the patient identifier to set
	 */
	public void setPatientIdentifier(PatientIdentifier patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the primaryLanguage
	 */
	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	/**
	 * @param primaryLanguage the primaryLanguage to set
	 */
	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * @return the ethnicGroup
	 */
	public String getEthnicGroup() {
		return ethnicGroup;
	}

	/**
	 * @param ethnicGroup the ethnicGroup to set
	 */
	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	/**
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @return the nameSpellCode
	 */
	public String getNameSpellCode() {
		return nameSpellCode;
	}

	/**
	 * @param nameSpellCode the nameSpellCode to set
	 */
	public void setNameSpellCode(String nameSpellCode) {
		this.nameSpellCode = nameSpellCode;
	}

	/**
	 * @return the nameWbCode
	 */
	public String getNameWbCode() {
		return nameWbCode;
	}

	/**
	 * @param nameWbCode the nameWbCode to set
	 */
	public void setNameWbCode(String nameWbCode) {
		this.nameWbCode = nameWbCode;
	}

	/**
	 * @return the birthProvince
	 */
	public String getBirthProvince() {
		return birthProvince;
	}

	/**
	 * @param birthProvince the birthProvince to set
	 */
	public void setBirthProvince(String birthProvince) {
		this.birthProvince = birthProvince;
	}

	/**
	 * @return the birthCity
	 */
	public String getBirthCity() {
		return birthCity;
	}

	/**
	 * @param birthCity the birthCity to set
	 */
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	/**
	 * @return the birthCounty
	 */
	public String getBirthCounty() {
		return birthCounty;
	}

	/**
	 * @param birthCounty the birthCounty to set
	 */
	public void setBirthCounty(String birthCounty) {
		this.birthCounty = birthCounty;
	}

	/**
	 * @return the birthZip
	 */
	public String getBirthZip() {
		return birthZip;
	}

	/**
	 * @param birthZip the birthZip to set
	 */
	public void setBirthZip(String birthZip) {
		this.birthZip = birthZip;
	}

	/**
	 * @return the citizenCard
	 */
	public String getCitizenCard() {
		return citizenCard;
	}

	/**
	 * @param citizenCard the citizenCard to set
	 */
	public void setCitizenCard(String citizenCard) {
		this.citizenCard = citizenCard;
	}

	/**
	 * @return the medicalCertificate
	 */
	public String getMedicalCertificate() {
		return medicalCertificate;
	}

	/**
	 * @param medicalCertificate the medicalCertificate to set
	 */
	public void setMedicalCertificate(String medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	/**
	 * @return the meicarePerson
	 */
	public String getMeicarePerson() {
		return meicarePerson;
	}

	/**
	 * @param meicarePerson the meicarePerson to set
	 */
	public void setMeicarePerson(String meicarePerson) {
		this.meicarePerson = meicarePerson;
	}

	/**
	 * @return the elderCertificate
	 */
	public String getElderCertificate() {
		return elderCertificate;
	}

	/**
	 * @param elderCertificate the elderCertificate to set
	 */
	public void setElderCertificate(String elderCertificate) {
		this.elderCertificate = elderCertificate;
	}

	/**
	 * @return the opcaseno
	 */
	public String getOpcaseno() {
		return opcaseno;
	}

	/**
	 * @param opcaseno the opcaseno to set
	 */
	public void setOpcaseno(String opcaseno) {
		this.opcaseno = opcaseno;
	}

	/**
	 * @return the genderName
	 */
	public String getGenderName() {
		return genderName;
	}

	/**
	 * @param genderName the genderName to set
	 */
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	/**
	 * @return the genderDomain
	 */
	public String getGenderDomain() {
		return genderDomain;
	}

	/**
	 * @param genderDomain the genderDomain to set
	 */
	public void setGenderDomain(String genderDomain) {
		this.genderDomain = genderDomain;
	}

	/**
	 * @return the ethnicName
	 */
	public String getEthnicName() {
		return ethnicName;
	}

	/**
	 * @param ethnicName the ethnicName to set
	 */
	public void setEthnicName(String ethnicName) {
		this.ethnicName = ethnicName;
	}

	/**
	 * @return the ethincDomain
	 */
	public String getEthincDomain() {
		return ethincDomain;
	}

	/**
	 * @param ethincDomain the ethincDomain to set
	 */
	public void setEthincDomain(String ethincDomain) {
		this.ethincDomain = ethincDomain;
	}

	/**
	 * @return the raceName
	 */
	public String getRaceName() {
		return raceName;
	}

	/**
	 * @param raceName the raceName to set
	 */
	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	/**
	 * @return the raceDomain
	 */
	public String getRaceDomain() {
		return raceDomain;
	}

	/**
	 * @param raceDomain the raceDomain to set
	 */
	public void setRaceDomain(String raceDomain) {
		this.raceDomain = raceDomain;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the nationalityName
	 */
	public String getNationalityName() {
		return nationalityName;
	}

	/**
	 * @param nationalityName the nationalityName to set
	 */
	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	/**
	 * @return the nationalityDomain
	 */
	public String getNationalityDomain() {
		return nationalityDomain;
	}

	/**
	 * @param nationalityDomain the nationalityDomain to set
	 */
	public void setNationalityDomain(String nationalityDomain) {
		this.nationalityDomain = nationalityDomain;
	}

	/**
	 * @return the maritalStatusName
	 */
	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	/**
	 * @param maritalStatusName the maritalStatusName to set
	 */
	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	/**
	 * @return the maritalDomain
	 */
	public String getMaritalDomain() {
		return maritalDomain;
	}

	/**
	 * @param maritalDomain the maritalDomain to set
	 */
	public void setMaritalDomain(String maritalDomain) {
		this.maritalDomain = maritalDomain;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the degreeName
	 */
	public String getDegreeName() {
		return degreeName;
	}

	/**
	 * @param degreeName the degreeName to set
	 */
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	/**
	 * @return the degreeDomain
	 */
	public String getDegreeDomain() {
		return degreeDomain;
	}

	/**
	 * @param degreeDomain the degreeDomain to set
	 */
	public void setDegreeDomain(String degreeDomain) {
		this.degreeDomain = degreeDomain;
	}

	/**
	 * @return the homeProvince
	 */
	public String getHomeProvince() {
		return homeProvince;
	}

	/**
	 * @param homeProvince the homeProvince to set
	 */
	public void setHomeProvince(String homeProvince) {
		this.homeProvince = homeProvince;
	}

	/**
	 * @return the homeCity
	 */
	public String getHomeCity() {
		return homeCity;
	}

	/**
	 * @param homeCity the homeCity to set
	 */
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	/**
	 * @return the homeCounty
	 */
	public String getHomeCounty() {
		return homeCounty;
	}

	/**
	 * @param homeCounty the homeCounty to set
	 */
	public void setHomeCounty(String homeCounty) {
		this.homeCounty = homeCounty;
	}

	/**
	 * @return the homeZip
	 */
	public String getHomeZip() {
		return homeZip;
	}

	/**
	 * @param homeZip the homeZip to set
	 */
	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	/**
	 * @return the homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * @param homeAddress the homeAddress to set
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * @return the registeredProvince
	 */
	public String getRegisteredProvince() {
		return registeredProvince;
	}

	/**
	 * @param registeredProvince the registeredProvince to set
	 */
	public void setRegisteredProvince(String registeredProvince) {
		this.registeredProvince = registeredProvince;
	}

	/**
	 * @return the registeredCity
	 */
	public String getRegisteredCity() {
		return registeredCity;
	}

	/**
	 * @param registeredCity the registeredCity to set
	 */
	public void setRegisteredCity(String registeredCity) {
		this.registeredCity = registeredCity;
	}

	/**
	 * @return the registeredCounty
	 */
	public String getRegisteredCounty() {
		return registeredCounty;
	}

	/**
	 * @param registeredCounty the registeredCounty to set
	 */
	public void setRegisteredCounty(String registeredCounty) {
		this.registeredCounty = registeredCounty;
	}

	/**
	 * @return the registeredZip
	 */
	public String getRegisteredZip() {
		return registeredZip;
	}

	/**
	 * @param registeredZip the registeredZip to set
	 */
	public void setRegisteredZip(String registeredZip) {
		this.registeredZip = registeredZip;
	}

	/**
	 * @return the registeredAddress
	 */
	public String getRegisteredAddress() {
		return registeredAddress;
	}

	/**
	 * @param registeredAddress the registeredAddress to set
	 */
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	/**
	 * @return the nativeProvince
	 */
	public String getNativeProvince() {
		return nativeProvince;
	}

	/**
	 * @param nativeProvince the nativeProvince to set
	 */
	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}

	/**
	 * @return the nativeCity
	 */
	public String getNativeCity() {
		return nativeCity;
	}

	/**
	 * @param nativeCity the nativeCity to set
	 */
	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the professionName
	 */
	public String getProfessionName() {
		return professionName;
	}

	/**
	 * @param professionName the professionName to set
	 */
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	/**
	 * @return the professionDomain
	 */
	public String getProfessionDomain() {
		return professionDomain;
	}

	/**
	 * @param professionDomain the professionDomain to set
	 */
	public void setProfessionDomain(String professionDomain) {
		this.professionDomain = professionDomain;
	}

	/**
	 * @return the workZip
	 */
	public String getWorkZip() {
		return workZip;
	}

	/**
	 * @param workZip the workZip to set
	 */
	public void setWorkZip(String workZip) {
		this.workZip = workZip;
	}

	/**
	 * @return the workAddress
	 */
	public String getWorkAddress() {
		return workAddress;
	}

	/**
	 * @param workAddress the workAddress to set
	 */
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	/**
	 * @return the privateNumber
	 */
	public String getPrivateNumber() {
		return privateNumber;
	}

	/**
	 * @param privateNumber the privateNumber to set
	 */
	public void setPrivateNumber(String privateNumber) {
		this.privateNumber = privateNumber;
	}

	/**
	 * @return the homeNumber
	 */
	public String getHomeNumber() {
		return homeNumber;
	}

	/**
	 * @param homeNumber the homeNumber to set
	 */
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	/**
	 * @return the workNumber
	 */
	public String getWorkNumber() {
		return workNumber;
	}

	/**
	 * @param workNumber the workNumber to set
	 */
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	/**
	 * @return the guardianPerson
	 */
	public String getGuardianPerson() {
		return guardianPerson;
	}

	/**
	 * @param guardianPerson the guardianPerson to set
	 */
	public void setGuardianPerson(String guardianPerson) {
		this.guardianPerson = guardianPerson;
	}

	/**
	 * @return the vip
	 */
	public String getVip() {
		return vip;
	}

	/**
	 * @param vip the vip to set
	 */
	public void setVip(String vip) {
		this.vip = vip;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
}
