package com.ats.aempi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TransPerson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRANS_PERSON", schema = "AETRANS")
public class TransPerson implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4976741954908024791L;
	private String uuid;
	private String name;
	private Date dateOfBirth;
	private String birthPlace;
	private String multipleBirthInd;
	private BigDecimal birthOrder;
	private String mothersMaidenName;
	private String ssn;
	private String identityNo;
	private String insuranceNo;
	private String insuranceType;
	private String insuranceName;
	private String genderCd;
	private String ethnicGroupCd;
	private String raceCd;
	private String nationalityCd;
	private String languageCd;
	private String religionCd;
	private String maritalStatusCd;
	private String degree;
	private String email;
	private String homeAddress;
	private String workAddress;
	private String city;
	private String state;
	private String country;
	private String countryCode;
	private String homeNumber;
	private String workNumber;
	private String deathInd;
	private String deathTime;
	private Date dateCreated;
	private BigDecimal creatorId;
	private Date dateChanged;
	private BigDecimal changedById;
	private Date dateVoided;
	private BigDecimal voidedById;
	private String hospitalDomainId;
	private String identifierDomainName;
	private String identifierDomainId;
	private String identifierDomainType;
	private String identifierId;
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
	private String personStatus;
	private String mergePatientId;
	private String mergePersonDomain;
	private String registeredProvince;
	private String registeredCity;
	private String registeredCounty;
	private String registeredAddress;
	private String registeredZip;
	private String homeProvince;
	private String homeCity;
	private String homeCounty;
	private String homeZip;
	private String privateNumber;
	private String citizenCard;
	private String medicalCertificate;
	private String medicarePerson;
	private String elderCertificate;
	private String opcaseno;
	private String company;
	private String workZip;
	private String guardianPerson;
	private String birthProvince;
	private String birthCity;
	private String birthCounty;
	private String birthZip;
	private String profession;
	private String nativeProvince;
	private String nativeCity;
	private BigDecimal vip;
	private String nameSpellCode;
	private String nameWbCode;
	private String bloodCode;
	private String genderName;
	private String maritalStatusName;
	private String degreeName;
	private String professionName;
	private String nationalityName;
	private String ethnicName;
	private String raceName;
	private String genderDomain;
	private String ethnicDomain;
	private String raceDomain;
	private String nationalityDomain;
	private String maritalDomain;
	private String degreeDomain;
	private String professionDomain;
	private String hl7Msg;
	private String hiupStatus;
	private String hiupErrorInfo;
	private String healthCard;
	private String relevanceDomain;
	private String relevanceID;
	private String accountLocked;
	private Date accountLockedDate;
	private Date birthTime;
	private String cardType;

	// Constructors

	/** default constructor */
	public TransPerson() {
	}

	/** minimal constructor */
	public TransPerson(String uuid, Date dateCreated,
			String identifierDomainId, String identifierId) {
		this.uuid = uuid;
		this.dateCreated = dateCreated;
		this.identifierDomainId = identifierDomainId;
		this.identifierId = identifierId;
	}

	/** full constructor */
	public TransPerson(String uuid, String name, Date dateOfBirth,
			String birthPlace, String multipleBirthInd, BigDecimal birthOrder,
			String mothersMaidenName, String ssn, String identityNo,
			String insuranceNo, String insuranceType, String insuranceName,
			String genderCd, String ethnicGroupCd, String raceCd,
			String nationalityCd, String languageCd, String religionCd,
			String maritalStatusCd, String degree, String email,
			String homeAddress, String workAddress, String city, String state,
			String country, String countryCode, String homeNumber,
			String workNumber, String deathInd, String deathTime,
			Date dateCreated, BigDecimal creatorId, Date dateChanged,
			BigDecimal changedById, Date dateVoided, BigDecimal voidedById,
			String hospitalDomainId, String identifierDomainName,
			String identifierDomainId, String identifierDomainType,
			String identifierId, String custom1, String custom2,
			String custom3, String custom4, String custom5, String custom6,
			String custom7, String custom8, String custom9, String custom10,
			String custom11, String custom12, String custom13, String custom14,
			String custom15, String custom16, String custom17, String custom18,
			String custom19, String custom20, String personStatus,
			String mergePatientId, String mergePersonDomain,
			String registeredProvince, String registeredCity,
			String registeredCounty, String registeredAddress,
			String registeredZip, String homeProvince, String homeCity,
			String homeCounty, String homeZip, String privateNumber,
			String citizenCard, String medicalCertificate,
			String medicarePerson, String elderCertificate, String opcaseno,
			String company, String workZip, String guardianPerson,
			String birthProvince, String birthCity, String birthCounty,
			String birthZip, String profession, String nativeProvince,
			String nativeCity, BigDecimal vip, String nameSpellCode,
			String nameWbCode, String bloodCode, String genderName,
			String maritalStatusName, String degreeName, String professionName,
			String nationalityName, String ethnicName, String raceName,
			String genderDomain, String ethnicDomain, String raceDomain,
			String nationalityDomain, String maritalDomain,
			String degreeDomain, String professionDomain, String hl7Msg,
			String hiupStatus, String hiupErrorInfo) {
		this.uuid = uuid;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.birthPlace = birthPlace;
		this.multipleBirthInd = multipleBirthInd;
		this.birthOrder = birthOrder;
		this.mothersMaidenName = mothersMaidenName;
		this.ssn = ssn;
		this.identityNo = identityNo;
		this.insuranceNo = insuranceNo;
		this.insuranceType = insuranceType;
		this.insuranceName = insuranceName;
		this.genderCd = genderCd;
		this.ethnicGroupCd = ethnicGroupCd;
		this.raceCd = raceCd;
		this.nationalityCd = nationalityCd;
		this.languageCd = languageCd;
		this.religionCd = religionCd;
		this.maritalStatusCd = maritalStatusCd;
		this.degree = degree;
		this.email = email;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.countryCode = countryCode;
		this.homeNumber = homeNumber;
		this.workNumber = workNumber;
		this.deathInd = deathInd;
		this.deathTime = deathTime;
		this.dateCreated = dateCreated;
		this.creatorId = creatorId;
		this.dateChanged = dateChanged;
		this.changedById = changedById;
		this.dateVoided = dateVoided;
		this.voidedById = voidedById;
		this.hospitalDomainId = hospitalDomainId;
		this.identifierDomainName = identifierDomainName;
		this.identifierDomainId = identifierDomainId;
		this.identifierDomainType = identifierDomainType;
		this.identifierId = identifierId;
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
		this.personStatus = personStatus;
		this.mergePatientId = mergePatientId;
		this.mergePersonDomain = mergePersonDomain;
		this.registeredProvince = registeredProvince;
		this.registeredCity = registeredCity;
		this.registeredCounty = registeredCounty;
		this.registeredAddress = registeredAddress;
		this.registeredZip = registeredZip;
		this.homeProvince = homeProvince;
		this.homeCity = homeCity;
		this.homeCounty = homeCounty;
		this.homeZip = homeZip;
		this.privateNumber = privateNumber;
		this.citizenCard = citizenCard;
		this.medicalCertificate = medicalCertificate;
		this.medicarePerson = medicarePerson;
		this.elderCertificate = elderCertificate;
		this.opcaseno = opcaseno;
		this.company = company;
		this.workZip = workZip;
		this.guardianPerson = guardianPerson;
		this.birthProvince = birthProvince;
		this.birthCity = birthCity;
		this.birthCounty = birthCounty;
		this.birthZip = birthZip;
		this.profession = profession;
		this.nativeProvince = nativeProvince;
		this.nativeCity = nativeCity;
		this.vip = vip;
		this.nameSpellCode = nameSpellCode;
		this.nameWbCode = nameWbCode;
		this.bloodCode = bloodCode;
		this.genderName = genderName;
		this.maritalStatusName = maritalStatusName;
		this.degreeName = degreeName;
		this.professionName = professionName;
		this.nationalityName = nationalityName;
		this.ethnicName = ethnicName;
		this.raceName = raceName;
		this.genderDomain = genderDomain;
		this.ethnicDomain = ethnicDomain;
		this.raceDomain = raceDomain;
		this.nationalityDomain = nationalityDomain;
		this.maritalDomain = maritalDomain;
		this.degreeDomain = degreeDomain;
		this.professionDomain = professionDomain;
		this.hl7Msg = hl7Msg;
		this.hiupStatus = hiupStatus;
		this.hiupErrorInfo = hiupErrorInfo;
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

	@Column(name = "MULTIPLE_BIRTH_IND", length = 1)
	public String getMultipleBirthInd() {
		return this.multipleBirthInd;
	}

	public void setMultipleBirthInd(String multipleBirthInd) {
		this.multipleBirthInd = multipleBirthInd;
	}

	@Column(name = "BIRTH_ORDER", precision = 22, scale = 0)
	public BigDecimal getBirthOrder() {
		return this.birthOrder;
	}

	public void setBirthOrder(BigDecimal birthOrder) {
		this.birthOrder = birthOrder;
	}

	@Column(name = "MOTHERS_MAIDEN_NAME", length = 64)
	public String getMothersMaidenName() {
		return this.mothersMaidenName;
	}

	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
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

	@Column(name = "INSURANCE_TYPE", length = 64)
	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Column(name = "INSURANCE_NAME", length = 64)
	public String getInsuranceName() {
		return this.insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	@Column(name = "GENDER_CD", length = 32)
	public String getGenderCd() {
		return this.genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	@Column(name = "ETHNIC_GROUP_CD", length = 32)
	public String getEthnicGroupCd() {
		return this.ethnicGroupCd;
	}

	public void setEthnicGroupCd(String ethnicGroupCd) {
		this.ethnicGroupCd = ethnicGroupCd;
	}

	@Column(name = "RACE_CD", length = 32)
	public String getRaceCd() {
		return this.raceCd;
	}

	public void setRaceCd(String raceCd) {
		this.raceCd = raceCd;
	}

	@Column(name = "NATIONALITY_CD", length = 32)
	public String getNationalityCd() {
		return this.nationalityCd;
	}

	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}

	@Column(name = "LANGUAGE_CD", length = 32)
	public String getLanguageCd() {
		return this.languageCd;
	}

	public void setLanguageCd(String languageCd) {
		this.languageCd = languageCd;
	}

	@Column(name = "RELIGION_CD", length = 32)
	public String getReligionCd() {
		return this.religionCd;
	}

	public void setReligionCd(String religionCd) {
		this.religionCd = religionCd;
	}

	@Column(name = "MARITAL_STATUS_CD", length = 32)
	public String getMaritalStatusCd() {
		return this.maritalStatusCd;
	}

	public void setMaritalStatusCd(String maritalStatusCd) {
		this.maritalStatusCd = maritalStatusCd;
	}

	@Column(name = "DEGREE", length = 32)
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "HOME_ADDRESS", length = 64)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "WORK_ADDRESS", length = 64)
	public String getWorkAddress() {
		return this.workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	@Column(name = "CITY", length = 64)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE", length = 64)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "COUNTRY", length = 64)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "COUNTRY_CODE", length = 64)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "HOME_NUMBER", length = 64)
	public String getHomeNumber() {
		return this.homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	@Column(name = "WORK_NUMBER", length = 64)
	public String getWorkNumber() {
		return this.workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	@Column(name = "DEATH_IND", length = 1)
	public String getDeathInd() {
		return this.deathInd;
	}

	public void setDeathInd(String deathInd) {
		this.deathInd = deathInd;
	}

	@Column(name = "DEATH_TIME")
	public String getDeathTime() {
		return this.deathTime;
	}

	public void setDeathTime(String deathTime) {
		this.deathTime = deathTime;
	}

	@Column(name = "DATE_CREATED", nullable = false)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "CREATOR_ID", precision = 22, scale = 0)
	public BigDecimal getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(BigDecimal creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "DATE_CHANGED")
	public Date getDateChanged() {
		return this.dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	@Column(name = "CHANGED_BY_ID", precision = 22, scale = 0)
	public BigDecimal getChangedById() {
		return this.changedById;
	}

	public void setChangedById(BigDecimal changedById) {
		this.changedById = changedById;
	}

	@Column(name = "DATE_VOIDED")
	public Date getDateVoided() {
		return this.dateVoided;
	}

	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}

	@Column(name = "VOIDED_BY_ID", precision = 22, scale = 0)
	public BigDecimal getVoidedById() {
		return this.voidedById;
	}

	public void setVoidedById(BigDecimal voidedById) {
		this.voidedById = voidedById;
	}

	@Column(name = "HOSPITAL_DOMAIN_ID")
	public String getHospitalDomainId() {
		return this.hospitalDomainId;
	}

	public void setHospitalDomainId(String hospitalDomainId) {
		this.hospitalDomainId = hospitalDomainId;
	}

	@Column(name = "IDENTIFIER_DOMAIN_NAME")
	public String getIdentifierDomainName() {
		return this.identifierDomainName;
	}

	public void setIdentifierDomainName(String identifierDomainName) {
		this.identifierDomainName = identifierDomainName;
	}

	@Column(name = "IDENTIFIER_DOMAIN_ID", nullable = false)
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

	@Column(name = "IDENTIFIER_ID", nullable = false)
	public String getIdentifierId() {
		return this.identifierId;
	}

	public void setIdentifierId(String identifierId) {
		this.identifierId = identifierId;
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

	@Column(name = "PERSON_STATUS", length = 64)
	public String getPersonStatus() {
		return this.personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	@Column(name = "MERGE_PATIENT_ID", length = 64)
	public String getMergePatientId() {
		return this.mergePatientId;
	}

	public void setMergePatientId(String mergePatientId) {
		this.mergePatientId = mergePatientId;
	}

	@Column(name = "MERGE_PERSON_DOMAIN", length = 64)
	public String getMergePersonDomain() {
		return this.mergePersonDomain;
	}

	public void setMergePersonDomain(String mergePersonDomain) {
		this.mergePersonDomain = mergePersonDomain;
	}

	@Column(name = "REGISTERED_PROVINCE", length = 64)
	public String getRegisteredProvince() {
		return this.registeredProvince;
	}

	public void setRegisteredProvince(String registeredProvince) {
		this.registeredProvince = registeredProvince;
	}

	@Column(name = "REGISTERED_CITY", length = 64)
	public String getRegisteredCity() {
		return this.registeredCity;
	}

	public void setRegisteredCity(String registeredCity) {
		this.registeredCity = registeredCity;
	}

	@Column(name = "REGISTERED_COUNTY", length = 64)
	public String getRegisteredCounty() {
		return this.registeredCounty;
	}

	public void setRegisteredCounty(String registeredCounty) {
		this.registeredCounty = registeredCounty;
	}

	@Column(name = "REGISTERED_ADDRESS", length = 512)
	public String getRegisteredAddress() {
		return this.registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	@Column(name = "REGISTERED_ZIP", length = 32)
	public String getRegisteredZip() {
		return this.registeredZip;
	}

	public void setRegisteredZip(String registeredZip) {
		this.registeredZip = registeredZip;
	}

	@Column(name = "HOME_PROVINCE", length = 64)
	public String getHomeProvince() {
		return this.homeProvince;
	}

	public void setHomeProvince(String homeProvince) {
		this.homeProvince = homeProvince;
	}

	@Column(name = "HOME_CITY", length = 64)
	public String getHomeCity() {
		return this.homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	@Column(name = "HOME_COUNTY", length = 64)
	public String getHomeCounty() {
		return this.homeCounty;
	}

	public void setHomeCounty(String homeCounty) {
		this.homeCounty = homeCounty;
	}

	@Column(name = "HOME_ZIP", length = 32)
	public String getHomeZip() {
		return this.homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	@Column(name = "PRIVATE_NUMBER", length = 64)
	public String getPrivateNumber() {
		return this.privateNumber;
	}

	public void setPrivateNumber(String privateNumber) {
		this.privateNumber = privateNumber;
	}

	@Column(name = "CITIZEN_CARD", length = 64)
	public String getCitizenCard() {
		return this.citizenCard;
	}

	public void setCitizenCard(String citizenCard) {
		this.citizenCard = citizenCard;
	}

	@Column(name = "MEDICAL_CERTIFICATE", length = 64)
	public String getMedicalCertificate() {
		return this.medicalCertificate;
	}

	public void setMedicalCertificate(String medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	@Column(name = "MEDICARE_PERSON", length = 64)
	public String getMedicarePerson() {
		return this.medicarePerson;
	}

	public void setMedicarePerson(String medicarePerson) {
		this.medicarePerson = medicarePerson;
	}

	@Column(name = "ELDER_CERTIFICATE", length = 64)
	public String getElderCertificate() {
		return this.elderCertificate;
	}

	public void setElderCertificate(String elderCertificate) {
		this.elderCertificate = elderCertificate;
	}

	@Column(name = "OPCASENO", length = 32)
	public String getOpcaseno() {
		return this.opcaseno;
	}

	public void setOpcaseno(String opcaseno) {
		this.opcaseno = opcaseno;
	}

	@Column(name = "COMPANY", length = 128)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "WORK_ZIP", length = 32)
	public String getWorkZip() {
		return this.workZip;
	}

	public void setWorkZip(String workZip) {
		this.workZip = workZip;
	}

	@Column(name = "GUARDIAN_PERSON", length = 64)
	public String getGuardianPerson() {
		return this.guardianPerson;
	}

	public void setGuardianPerson(String guardianPerson) {
		this.guardianPerson = guardianPerson;
	}

	@Column(name = "BIRTH_PROVINCE", length = 64)
	public String getBirthProvince() {
		return this.birthProvince;
	}

	public void setBirthProvince(String birthProvince) {
		this.birthProvince = birthProvince;
	}

	@Column(name = "BIRTH_CITY", length = 64)
	public String getBirthCity() {
		return this.birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	@Column(name = "BIRTH_COUNTY", length = 64)
	public String getBirthCounty() {
		return this.birthCounty;
	}

	public void setBirthCounty(String birthCounty) {
		this.birthCounty = birthCounty;
	}

	@Column(name = "BIRTH_ZIP", length = 32)
	public String getBirthZip() {
		return this.birthZip;
	}

	public void setBirthZip(String birthZip) {
		this.birthZip = birthZip;
	}

	@Column(name = "PROFESSION", length = 64)
	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Column(name = "NATIVE_PROVINCE", length = 64)
	public String getNativeProvince() {
		return this.nativeProvince;
	}

	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}

	@Column(name = "NATIVE_CITY", length = 64)
	public String getNativeCity() {
		return this.nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	@Column(name = "VIP", precision = 22, scale = 0)
	public BigDecimal getVip() {
		return this.vip;
	}

	public void setVip(BigDecimal vip) {
		this.vip = vip;
	}

	@Column(name = "NAME_SPELL_CODE", length = 64)
	public String getNameSpellCode() {
		return this.nameSpellCode;
	}

	public void setNameSpellCode(String nameSpellCode) {
		this.nameSpellCode = nameSpellCode;
	}

	@Column(name = "NAME_WB_CODE", length = 64)
	public String getNameWbCode() {
		return this.nameWbCode;
	}

	public void setNameWbCode(String nameWbCode) {
		this.nameWbCode = nameWbCode;
	}

	@Column(name = "BLOOD_CODE", length = 64)
	public String getBloodCode() {
		return this.bloodCode;
	}

	public void setBloodCode(String bloodCode) {
		this.bloodCode = bloodCode;
	}

	@Column(name = "GENDER_NAME", length = 64)
	public String getGenderName() {
		return this.genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	@Column(name = "MARITAL_STATUS_NAME", length = 64)
	public String getMaritalStatusName() {
		return this.maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	@Column(name = "DEGREE_NAME", length = 64)
	public String getDegreeName() {
		return this.degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	@Column(name = "PROFESSION_NAME", length = 64)
	public String getProfessionName() {
		return this.professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	@Column(name = "NATIONALITY_NAME", length = 64)
	public String getNationalityName() {
		return this.nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	@Column(name = "ETHNIC_NAME", length = 64)
	public String getEthnicName() {
		return this.ethnicName;
	}

	public void setEthnicName(String ethnicName) {
		this.ethnicName = ethnicName;
	}

	@Column(name = "RACE_NAME", length = 64)
	public String getRaceName() {
		return this.raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	@Column(name = "GENDER_DOMAIN", length = 64)
	public String getGenderDomain() {
		return this.genderDomain;
	}

	public void setGenderDomain(String genderDomain) {
		this.genderDomain = genderDomain;
	}

	@Column(name = "ETHNIC_DOMAIN", length = 64)
	public String getEthnicDomain() {
		return this.ethnicDomain;
	}

	public void setEthnicDomain(String ethnicDomain) {
		this.ethnicDomain = ethnicDomain;
	}

	@Column(name = "RACE_DOMAIN", length = 64)
	public String getRaceDomain() {
		return this.raceDomain;
	}

	public void setRaceDomain(String raceDomain) {
		this.raceDomain = raceDomain;
	}

	@Column(name = "NATIONALITY_DOMAIN", length = 64)
	public String getNationalityDomain() {
		return this.nationalityDomain;
	}

	public void setNationalityDomain(String nationalityDomain) {
		this.nationalityDomain = nationalityDomain;
	}

	@Column(name = "MARITAL_DOMAIN", length = 64)
	public String getMaritalDomain() {
		return this.maritalDomain;
	}

	public void setMaritalDomain(String maritalDomain) {
		this.maritalDomain = maritalDomain;
	}

	@Column(name = "DEGREE_DOMAIN", length = 64)
	public String getDegreeDomain() {
		return this.degreeDomain;
	}

	public void setDegreeDomain(String degreeDomain) {
		this.degreeDomain = degreeDomain;
	}

	@Column(name = "PROFESSION_DOMAIN", length = 64)
	public String getProfessionDomain() {
		return this.professionDomain;
	}

	public void setProfessionDomain(String professionDomain) {
		this.professionDomain = professionDomain;
	}

	@Column(name = "HL7_MSG")
	public String getHl7Msg() {
		return this.hl7Msg;
	}

	public void setHl7Msg(String hl7Msg) {
		this.hl7Msg = hl7Msg;
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
	
	@Column(name = "RELEVANCE_DOMAIN",length = 64)
	public String getRelevanceDomain() {
		return relevanceDomain;
	}

	public void setRelevanceDomain(String relevanceDomain) {
		this.relevanceDomain = relevanceDomain;
	}
	
	@Column(name = "RELEVANCE_ID",length = 64)
	public String getRelevanceId() {
		return relevanceID;
	}

	public void setRelevanceId(String relevanceID) {
		this.relevanceID = relevanceID;
	}
	
	@Column(name = "HEALTH_CARD",length = 64)
	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
	}

	@Column(name = "ACCOUNT_LOCKED")
	public String getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}

	@Column(name = "ACCOUNT_LOCKED_DATE")
	public Date getAccountLockedDate() {
		return accountLockedDate;
	}

	public void setAccountLockedDate(Date accountLockedDate) {
		this.accountLockedDate = accountLockedDate;
	}
	
	@Column(name = "BIRTH_TIME")
	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}
	
	@Column(name = "CARD_TYPE")
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
