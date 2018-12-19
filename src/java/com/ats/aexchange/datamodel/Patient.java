package com.ats.aexchange.datamodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ats.aexchange.datamodel.SharedEnums.PhoneType;
import com.ats.aexchange.datamodel.SharedEnums.SexType;

public class Patient {
	
	/* a list of patient identifiers of this patient*/
	private List<PatientIdentifier> patientIds = new ArrayList<PatientIdentifier>();

	private PersonName  patientName; 
	
	private PersonName  mothersMaidenName;
	
	private PersonName  SickName;
	
    private Calendar    birthDateTime;
    
    private SexType     administrativeSex;
    
    private String      race;
    
    private String      primaryLanguage;
    
    private String      maritalStatus;
    
    private String      religion;
    
    private PatientIdentifier patientAccountNumber;
    
    private String      ssn;
    
    private String      InsuranceNO;
    
    private String      IdentityNO;
    
    private String      SocialcardNO;
    
    private String      TelPhone;
    
    private String      HomePhone;
    
    private String      BusinessPhone; 
    
    private DriversLicense driversLicense;
    
    private PersonIdentifier mothersId;
    
    private String      ethnicGroup;
    
    private String      birthPlace;
    
    private int         birthOrder;
    
    private String      citizenship;
    
    private Calendar    deathDate;
    
    private String     deathIndicator;

    private String		vipIndicator;
    
    private String		email;
    
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
	
	private String custom21;
	
	private String custom22;
	
	private String custom23;
	
	private String custom24;
	
	private String custom25;
	
	private String custom26;
	
	private String custom27;
	
	private String custom28;
	
	private String custom29;
	
	private String custom30;
	
    private String      PatientAccountNumbers;
    
    private String      MulitpleBirthIndicator;
    
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
	
	private String homeStreet;
	
	private String registeredProvince;
	
	private String registeredCity;
	
	private String registeredCounty;
	
	private String registeredZip;
	
	private String registeredAddress;
	
	private String registeredStreet;
	
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
	private String healthCard;
	private String patientSourceName;
	private String oldPatientId;
	private String oldPatientDomain;
	private String oldVisitFlowId;
	private String oldVisitFlowDomain;
	private String oldVisitId;
	private String oldPersonId;
	private String oldStatus;
	private String oldInfo;
	private String accountLocked;
	private Date accountLockedDate;
	private String isEmergency;
	private String patIpTimes;
	private Date birthTime;
	private String diagnoseIcd;
	private String diagnoseName;
	private String cardType;
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
	
	
    
    //private String      BirthOrder;

    /**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}



	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}



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
	 * @return the birthTime
	 */
	public Date getBirthTime() {
		return birthTime;
	}



	/**
	 * @param birthTime the birthTime to set
	 */
	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
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
	 * @return the accountLocked
	 */
	public String getAccountLocked() {
		return accountLocked;
	}



	/**
	 * @param accountLocked the accountLocked to set
	 */
	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}



	/**
	 * @return the accountLockedDate
	 */
	public Date getAccountLockedDate() {
		return accountLockedDate;
	}



	/**
	 * @param accountLockedDate the accountLockedDate to set
	 */
	public void setAccountLockedDate(Date accountLockedDate) {
		this.accountLockedDate = accountLockedDate;
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



	private List<PersonName>  patientAliases = new ArrayList<PersonName>();
    
    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
    
    private List<Address>  addresses = new ArrayList<Address>();
    
    private List<Visit>    visits = new ArrayList<Visit>();
    
    private List<NextOfKin>    nextOfKin = new ArrayList<NextOfKin>();
    
    private List<TAllergy>    allergy  = new ArrayList<TAllergy>();
    
    private List<fPatientVisitList> fpatientvisitlist = new ArrayList<fPatientVisitList>();

	/**
	 * Default Constructor
	 */
	public Patient() {
	}

	
	
	/**
	 * Set and Gets MulitpleBirthIndicator.
	 *
	 * @return the MulitpleBirthIndicator
	 */
	public String getMulitpleBirthIndicator() {
		return MulitpleBirthIndicator;
	}

	public void setMulitpleBirthIndicator(String MulitpleBirthIndicator) {
		this.MulitpleBirthIndicator = MulitpleBirthIndicator;
	}
	
	
	/**
	 * Set and Gets PatientAccountNumber.
	 *
	 * @return the PatientAccountNumber
	 */
	public String getPatientAccountNumbers() {
		return PatientAccountNumbers;
	}

	public void setPatientAccountNumbers(String PatientAccountNumbers) {
		this.PatientAccountNumbers = PatientAccountNumbers;
	}
	
	/**
	 * Set and Gets BusinessPhone.
	 *
	 * @return the BusinessPhone
	 */
	public String getBusinessPhone() {
		return BusinessPhone;
	}

	public void setBusinessPhone(String BusinessPhone) {
		this.BusinessPhone = BusinessPhone;
	}
	
	
	/**
	 * Set and Gets HomePhone.
	 *
	 * @return the HomePhone
	 */
	public String getHomePhone() {
		return HomePhone;
	}

	public void setHomePhone(String HomePhone) {
		this.HomePhone = HomePhone;
	}
	
	
	
	
	/**
	 * Gets a list of <code>PatientIdentifier</code>.
	 * 
	 * @return the <code>PatientIdentifier</code> list
	 */
	public List<PatientIdentifier> getPatientIds() {
		return patientIds;
	}


	/**
	 * Adds a <code>PatientIdentifier</code> to this patient.
	 *  
	 * @param id the <code>PatientIdentifier</code> to add
	 */
	public void addPatientId(PatientIdentifier id) {
		this.patientIds.add(id);
	}

	/**
	 * Removes a <code>PatientIdentifier</code> from this patient.
	 * 
	 * @param id the <code>PatientIdentifier</code> to be removed
	 */
	public void removePatientId(PatientIdentifier id) {
		this.patientIds.remove(id);
	}

	/**
	 * Removes all the <code>PatientIdentifier</code> from this patient.
	 * 
	 */
	public void clearPatientId() {
		this.patientIds.clear();
	}
    
 

	/**
	 * Gets patientName.
	 *
	 * @return the patientName
	 */
	public PersonName getPatientName() {
		return patientName;
	}

	public PersonName getSickName() {
		return SickName;
	}
	/**
	 * Sets patientName.
	 *
	 * @param patientName the patientName to set
	 */
	public void setPatientName(PersonName name) {
		this.patientName = name;
	}

	public void setSickName(PersonName name) {
		this.SickName = name;
	}
	/**
	 * Gets mothersMaidenName.
	 *
	 * @return the mothersMaidenName
	 */
	public PersonName getMothersMaidenName() {
		return mothersMaidenName;
	}


	/**
	 * Sets mothersMaidenName.
	 *
	 * @param mothersMaidenName the mothersMaidenName to set
	 */
	public void setMothersMaidenName(PersonName mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}


	/**
	 * Gets birthDateTime.
	 *
	 * @return the birthDateTime
	 */
	public Calendar getBirthDateTime() {
		return birthDateTime;
	}


	/**
	 * Sets birthDateTime.
	 *
	 * @param birthDateTime the birthDateTime to set
	 */
	public void setBirthDateTime(Calendar birthDateTime) {
		this.birthDateTime = birthDateTime;
	}


	/**
	 * Gets administrativeSex.
	 *
	 * @return the administrativeSex
	 */
	public SexType getAdministrativeSex() {
		return administrativeSex;
	}


	/**
	 * Sets administrativeSex.
	 *
	 * @param administrativeSex the administrativeSex to set
	 */
	public void setAdministrativeSex(SexType administrativeSex) {
		this.administrativeSex = administrativeSex;
	}

	/**
	 * Gets a <code>PersonName</code> list.
	 *
	 * @return the list of aliases
	 */
	public List<PersonName> getPatientAliases() {
		return patientAliases;
	}

	public void setPatientAliases(List<PersonName> patientAliases) {
		this.patientAliases = patientAliases;
	}

	/**
	 * Adds an <code>PersonName</code> to this patient.
	 *
	 * @param alias the alias to add
	 */
	public void addPatientAliases(PersonName alias) {
		this.patientAliases.add(alias);
	}

	/**
	 * Removes an <code>PersonName</code>.
	 *
	 * @param alias the <code>PersonName</code> to be removed
  	 * @return boolean true if removed, false otherwise
	 */
	public boolean removePatientAliases(PersonName alias) {
		return patientAliases.remove(alias);
	}

 	/**
	 * Clears the <code>PersonName</code> list.
	 */
	public void clearPatientAliases() {
		patientAliases.clear();
	}

	/**
	 * Gets race.
	 *
	 * @return the race
	 */
	public String getRaceDict() {
		return race;
	}

	

	/**
	 * Sets race.
	 *
	 * @param race the race to set
	 */
	
//	public void setRaceDict(String race) {
//		this.raceDict=null;
//	}
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * Gets primaryLanguage.
	 *
	 * @return the primaryLanguage
	 */
	public String getPrimaryLanguage() {
		return primaryLanguage;
	}


	/**
	 * Sets primaryLanguage.
	 *
	 * @param primaryLanguage the primaryLanguage to set
	 */
	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}


	/**
	 * Gets maritalStatus.
	 *
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}


	/**
	 * Sets maritalStatus.
	 *
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	/**
	 * Gets religion.
	 *
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}


	/**
	 * Sets religion.
	 *
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}


	/**
	 * Gets patientAccountNumber.
	 *
	 * @return the patientAccountNumber
	 */
	public PatientIdentifier getPatientAccountNumber() {
		return patientAccountNumber;
	}


	/**
	 * Sets patientAccountNumber.
	 *
	 * @param patientAccountNumber the patientAccountNumber to set
	 */
	public void setPatientAccountNumber(PatientIdentifier patientAccountNumber) {
		this.patientAccountNumber = patientAccountNumber;
	}


	/**
	 * Gets ssn.
	 *
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}


	/**
	 * Sets ssn.
	 *
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * Gets IdentityNO.
	 *
	 * @return the IdentityNO
	 */
	public String getIdentityNO() {
		return IdentityNO;
	}


	/**
	 * Sets IdentityNO.
	 *
	 * @param IdentityNO the IdentityNO to set
	 */
	public void setIdentityNO(String IdentityNO) {
		this.IdentityNO = IdentityNO;
	}
	

	/**
	 * Gets TelPhone.
	 *
	 * @return the TelPhone
	 */
	public String getTelPhone() {
		return TelPhone;
	}


	/**
	 * Sets TelPhone.
	 *
	 * @param TelPhone the TelPhone to set
	 */
	public void setTelPhone(String TelPhone) {
		this.TelPhone = TelPhone;
	}
	
	
	
	/**
	 * Gets InsuranceNO.
	 *
	 * @return the InsuranceNO
	 */
	public String getInsuranceNO() {
		return InsuranceNO;
	}


	/**
	 * Sets InsuranceNO.
	 *
	 * @param InsuranceNO the InsuranceNO to set
	 */
	public void setInsuranceNO(String InsuranceNO) {
		this.InsuranceNO = InsuranceNO;
	}
	
	
	
	/**
	 * Gets SocialcardNO.
	 *
	 * @return the SocialcardNO
	 */
	public String getSocialcardNO() {
		return SocialcardNO;
	}


	/**
	 * Sets SocialcardNO.
	 *
	 * @param SocialcardNO the SocialcardNO to set
	 */
	public void setSocialcardNO(String SocialcardNO) {
		this.SocialcardNO = SocialcardNO;
	}
	
	
	/**
	 * Gets driversLicense.
	 *
	 * @return the driversLicense
	 */
	public DriversLicense getDriversLicense() {
		return driversLicense;
	}


	/**
	 * Sets driversLicense.
	 *
	 * @param driversLicense the driversLicense to set
	 */
	public void setDriversLicense(DriversLicense driversLicense) {
		this.driversLicense = driversLicense;
	}


	/**
	 * Gets mothersId.
	 *
	 * @return the mothersId
	 */
	public PersonIdentifier getMothersId() {
		return mothersId;
	}


	/**
	 * Sets mothersId.
	 *
	 * @param mothersId the mothersId to set
	 */
	public void setMothersId(PersonIdentifier mothersId) {
		this.mothersId = mothersId;
	}


	/**
	 * Gets ethnicGroup.
	 *
	 * @return the ethnicGroup
	 */
	public String getEthnicGroup() {
		return ethnicGroup;
	}


	/**
	 * Sets ethnicGroup.
	 *
	 * @param ethnicGroup the ethnicGroup to set
	 */
	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}


	/**
	 * Gets birthPlace.
	 *
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}


	/**
	 * Sets birthPlace.
	 *
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}


	/**
	 * Gets birthOrder.
	 *
	 * @return the birthOrder
	 */
	public int getBirthOrder() {
		return birthOrder;
	}


	/**
	 * Sets birthOrder.
	 *
	 * @param birthOrder the birthOrder to set
	 */
	public void setBirthOrder(int birthOrder) {
		this.birthOrder = birthOrder;
	}


	/**
	 * Gets citizenship.
	 *
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}


	/**
	 * Sets citizenship.
	 *
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}


	/**
	 * Gets deathDate.
	 *
	 * @return the deathDate
	 */
	public Calendar getDeathDate() {
		return deathDate;
	}


	/**
	 * Sets deathDate.
	 *
	 * @param deathDate the deathDate to set
	 */
	public void setDeathDate(Calendar deathDate) {
		this.deathDate = deathDate;
	}


	/**
	 * Gets deathIndicator.
	 *
	 * @return the deathIndicator
	 */
	public String getDeathIndicator() {
		return deathIndicator;
	}


	/**
	 * Sets deathIndicator.
	 *
	 * @param deathIndicator the deathIndicator to set
	 */
	public void setDeathIndicator(String deathIndicator) {
		this.deathIndicator = deathIndicator;
	}

	

	/**
	 * Gets vipIndicator
	 * 
	 * @return the vipIndicator
	 */
	public String getVipIndicator() {
		return vipIndicator;
	}


	/**
	 * Sets vipIndicator
	 * 
	 * @param vipIndicator the vipIndicator to set
	 */
	public void setVipIndicator(String vipIndicator) {
		this.vipIndicator = vipIndicator;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return this.email;
	}

	/**
	 * Gets a list of <code>PhoneNumber</code>s of this patient.
	 * 
	 * @return a list of PhoneNumbers
	 */
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}


	/**
	 * Adds a <code>PhoneNumber</code> to this patient.
	 * 
	 * @param phoneNumber the phoneNumber to add
	 */
	public void addPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumbers.add(phoneNumber);
	}

	/**
	 * Removes a <code>PhoneNumber</code>.
	 * 
	 * @param phoneNumber the <code>PhoneNumber</code> to be removed
  	 * @return boolean true if removed, false otherwise
	 */
	public boolean removePhoneNumber(PhoneNumber phoneNumber) {
		return phoneNumbers.remove(phoneNumber);
	}

 	/**
	 * Clears the <code>PhoneNumber</code> list.
	 */
	public void clearPhoneNumbers() {
		phoneNumbers.clear();
	}
	
	
	
	/**
	 * Get a particular phone number from the list for this
	 * patient.
	 * 
	 * @param type The type of phone number to get
	 * @return The phone number
	 */
	public PhoneNumber getPhoneNumber(PhoneType type) {
		if (phoneNumbers == null) return null;
		// Remove the old entry for this type of address
		for (int i=0; i<phoneNumbers.size(); i++) {
			PhoneNumber next = phoneNumbers.get(i);
			PhoneType nextType = next.getType();
			if (nextType == null) {
				if (type == null) return next;
			} else if ((type != null) && nextType.equals(type)) {
				return next;
			}
		}
		// Couldn't find a match
		return null;
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
	 * @return the race
	 */
	public String getRace() {
		return race;
	}



	/**
	 * Gets a <code>Address</code> list.
	 * 
	 * @return the list of addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}


	/**
	 * Adds an <code>Address</code> to this patient.
	 * 
	 * @param address the address to add
	 */
	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	/**
	 * Removes an <code>Address</code>.
	 * 
	 * @param address the <code>Address</code> to be removed
  	 * @return boolean true if removed, false otherwise
	 */
	public boolean removeAddress(Address address) {
		return addresses.remove(address);
	}

 	/**
	 * Clears the <code>Address</code> list.
	 */
	public void clearAddresses() {
		addresses.clear();
	}

	/**
	 * Gets a <code>Visit</code> list.
	 * 
	 * @return the list of visits
	 */
	public List<Visit> getVisits() {
		return visits;
	}


	/**
	 * Adds a <code>Visit</code> to this patient.
	 * 
	 * @param visit the <code>Visit</code> to add
	 */
	public void addVisit(Visit visit) {
		this.visits.add(visit);
	}

	/**
	 * Removes a <code>Visit</code>.
	 * 
	 * @param visit the <code>Visit</code> to be removed
  	 * @return boolean true if removed, false otherwise
	 */
	public boolean removeVisit(Visit visit) {
		return visits.remove(visit);
	}

 	/**
	 * Clears the <code>Visit</code> list.
	 */
	public void clearVisits() {
		visits.clear();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addresses == null) ? 0 : addresses.hashCode());
		result = prime
				* result
				+ ((administrativeSex == null) ? 0 : administrativeSex
						.hashCode());
		result = prime * result
				+ ((birthDateTime == null) ? 0 : birthDateTime.hashCode());
		result = prime * result + birthOrder;
		result = prime * result
				+ ((birthPlace == null) ? 0 : birthPlace.hashCode());
		result = prime * result
				+ ((citizenship == null) ? 0 : citizenship.hashCode());
		result = prime * result
				+ ((deathDate == null) ? 0 : deathDate.hashCode());
		result = prime * result
				+ ((deathIndicator == null) ? 0 : deathIndicator.hashCode());
		result = prime * result + ((vipIndicator == null) ? 0 : vipIndicator.hashCode());				
		result = prime * result
				+ ((driversLicense == null) ? 0 : driversLicense.hashCode());
		result = prime * result
				+ ((ethnicGroup == null) ? 0 : ethnicGroup.hashCode());
		result = prime * result
				+ ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result
				+ ((mothersId == null) ? 0 : mothersId.hashCode());
		result = prime
				* result
				+ ((mothersMaidenName == null) ? 0 : mothersMaidenName
						.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		result = prime
				* result
				+ ((patientAccountNumber == null) ? 0 : patientAccountNumber
						.hashCode());
		result = prime * result
				+ ((patientAliases == null) ? 0 : patientAliases.hashCode());
		result = prime * result
				+ ((patientIds == null) ? 0 : patientIds.hashCode());
		result = prime * result
				+ ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
		result = prime * result
				+ ((primaryLanguage == null) ? 0 : primaryLanguage.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result
				+ ((religion == null) ? 0 : religion.hashCode());
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		result = prime * result + ((visits == null) ? 0 : visits.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Patient))
			return false;
		final Patient other = (Patient) obj;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (administrativeSex == null) {
			if (other.administrativeSex != null)
				return false;
		} else if (!administrativeSex.equals(other.administrativeSex))
			return false;
		if (birthDateTime == null) {
			if (other.birthDateTime != null)
				return false;
		} else if (!birthDateTime.equals(other.birthDateTime))
			return false;
		if (birthOrder != other.birthOrder)
			return false;
		if (birthPlace == null) {
			if (other.birthPlace != null)
				return false;
		} else if (!birthPlace.equals(other.birthPlace))
			return false;
		if (citizenship == null) {
			if (other.citizenship != null)
				return false;
		} else if (!citizenship.equals(other.citizenship))
			return false;
		if (deathDate == null) {
			if (other.deathDate != null)
				return false;
		} else if (!deathDate.equals(other.deathDate))
			return false;
		if (deathIndicator != other.deathIndicator)
			return false;
		if (driversLicense == null) {
			if (other.driversLicense != null)
				return false;
		} else if (!driversLicense.equals(other.driversLicense))
			return false;
		if (ethnicGroup == null) {
			if (other.ethnicGroup != null)
				return false;
		} else if (!ethnicGroup.equals(other.ethnicGroup))
			return false;
		if (maritalStatus == null) {
			if (other.maritalStatus != null)
				return false;
		} else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (mothersId == null) {
			if (other.mothersId != null)
				return false;
		} else if (!mothersId.equals(other.mothersId))
			return false;
		if (mothersMaidenName == null) {
			if (other.mothersMaidenName != null)
				return false;
		} else if (!mothersMaidenName.equals(other.mothersMaidenName))
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		if (patientAccountNumber == null) {
			if (other.patientAccountNumber != null)
				return false;
		} else if (!patientAccountNumber.equals(other.patientAccountNumber))
			return false;
		if (patientAliases == null) {
			if (other.patientAliases != null)
				return false;
		} else if (!patientAliases.equals(other.patientAliases))
			return false;
		if (patientIds == null) {
			if (other.patientIds != null)
				return false;
		} else if (!patientIds.equals(other.patientIds))
			return false;
		if (phoneNumbers == null) {
			if (other.phoneNumbers != null)
				return false;
		} else if (!phoneNumbers.equals(other.phoneNumbers))
			return false;
		if (primaryLanguage == null) {
			if (other.primaryLanguage != null)
				return false;
		} else if (!primaryLanguage.equals(other.primaryLanguage))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (religion == null) {
			if (other.religion != null)
				return false;
		} else if (!religion.equals(other.religion))
			return false;
		if (ssn == null) {
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		if (vipIndicator == null) {
			if (other.vipIndicator != null)
				return false;
		} else if (!vipIndicator.equals(other.vipIndicator))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;	
		if (visits == null) {
			if (other.visits != null)
				return false;
		} else if (!visits.equals(other.visits))
			return false;
		
		return true;
	}
	public void setPatientIds(List<PatientIdentifier> patientIds) {
		this.patientIds = patientIds;
	}


	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}


	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

    public List<NextOfKin> getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(List<NextOfKin> nextOfKin) {
        this.nextOfKin = nextOfKin;
    }



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
	 * @return the custom21
	 */
	public String getCustom21() {
		return custom21;
	}



	/**
	 * @param custom21 the custom21 to set
	 */
	public void setCustom21(String custom21) {
		this.custom21 = custom21;
	}



	/**
	 * @return the custom22
	 */
	public String getCustom22() {
		return custom22;
	}



	/**
	 * @param custom22 the custom22 to set
	 */
	public void setCustom22(String custom22) {
		this.custom22 = custom22;
	}



	/**
	 * @return the custom23
	 */
	public String getCustom23() {
		return custom23;
	}



	/**
	 * @param custom23 the custom23 to set
	 */
	public void setCustom23(String custom23) {
		this.custom23 = custom23;
	}



	/**
	 * @return the custom24
	 */
	public String getCustom24() {
		return custom24;
	}



	/**
	 * @param custom24 the custom24 to set
	 */
	public void setCustom24(String custom24) {
		this.custom24 = custom24;
	}



	/**
	 * @return the custom25
	 */
	public String getCustom25() {
		return custom25;
	}



	/**
	 * @param custom25 the custom25 to set
	 */
	public void setCustom25(String custom25) {
		this.custom25 = custom25;
	}



	/**
	 * @return the custom26
	 */
	public String getCustom26() {
		return custom26;
	}



	/**
	 * @param custom26 the custom26 to set
	 */
	public void setCustom26(String custom26) {
		this.custom26 = custom26;
	}



	/**
	 * @return the custom27
	 */
	public String getCustom27() {
		return custom27;
	}



	/**
	 * @param custom27 the custom27 to set
	 */
	public void setCustom27(String custom27) {
		this.custom27 = custom27;
	}



	/**
	 * @return the custom28
	 */
	public String getCustom28() {
		return custom28;
	}



	/**
	 * @param custom28 the custom28 to set
	 */
	public void setCustom28(String custom28) {
		this.custom28 = custom28;
	}



	/**
	 * @return the custom29
	 */
	public String getCustom29() {
		return custom29;
	}



	/**
	 * @param custom29 the custom29 to set
	 */
	public void setCustom29(String custom29) {
		this.custom29 = custom29;
	}



	/**
	 * @return the custom30
	 */
	public String getCustom30() {
		return custom30;
	}



	/**
	 * @param custom30 the custom30 to set
	 */
	public void setCustom30(String custom30) {
		this.custom30 = custom30;
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
	 * @return the allergy
	 */
	public List<TAllergy> getAllergy() {
		return allergy;
	}



	/**
	 * @param allergy the allergy to set
	 */
	public void setAllergy(List<TAllergy> allergy) {
		this.allergy = allergy;
	}



	/**
	 * @return the fpatientvisitlist
	 */
	public List<fPatientVisitList> getFpatientvisitlist() {
		return fpatientvisitlist;
	}



	/**
	 * @return the healthCard
	 */
	public String getHealthCard() {
		return healthCard;
	}



	/**
	 * @param healthCard the healthCard to set
	 */
	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
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
	
	public String getHomeStreet(){
		return homeStreet;
	}
	
	public void setHomeStreet(String homeStreet){
		this.homeStreet = homeStreet;
	}
	
	public String getRegisteredStreet(){
		return registeredStreet;
	}
	
	public void setRegisteredStreet(String registeredStreet){
		this.registeredStreet = registeredStreet;
	}


	/**
	 * @param fpatientvisitlist the fpatientvisitlist to set
	 */
	public void setFpatientvisitlist(List<fPatientVisitList> fpatientvisitlist) {
		this.fpatientvisitlist = fpatientvisitlist;
	}




    
}
