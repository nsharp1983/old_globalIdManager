package com.ats.aempi.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Empi entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMPI")
public class Empi implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empi;
	private String name;
	private String nameCode;
	private Date dateOfBirth;
	private String birthPlace;
	private String multipleBirthInd;
	private String birthOrder;
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
	private String address1;
	private String postalCode;
	private String addressTypeCd;
	private String address2;
	private String postalCode1;
	private String address1TypeCd;
	private String city;
	private String state;
	private String country;
	private String countryCode;
	private String phoneCountryCode;
	private String phoneAreaCode;
	private String phoneNumber;
	private String phoneExt;
	private String phoneTypeCd;
	private String phoneCountryCode1;
	private String phoneAreaCode1;
	private String phoneNumber1;
	private String phoneExt1;
	private String phoneTypeCd1;
	private String company;
	private String companycontacts;
	private String birthplaceCd;
	private String workstatus;
	private String profession;
	private String accountIdentifierDomainId;
	private String account;
	private String deathInd;
	private String deathTime;
	private Date dateChanged;
	private String changedById;
	private Date dateVoided;
	private String voidedById;
	private String bloodTypeCd;
	private String rhType;
	private String hospitalCd;
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
	private String nationalityName;
	private String nationalityDomain;
	private String maritalStatusName;
	private String maritalDomain;
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
	private String professionName;
	private String professionDomain;
	private String workZip;
	private String workAddress;
	private String privateNumber;
	private String homeNumber;
	private String workNumber;
	private String guardianPerson;
	private String vip;
	private String healthCard;
	private String accountLocked;
	private Date accountLockedDate;
	private Date birthTime;
	private String cardType;
	// Constructors

	/** default constructor */
	public Empi() {
	}

	/** minimal constructor */
	public Empi(String empi) {
		this.empi = empi;
	}

	/** full constructor */
	public Empi(String empi, String name, String nameCode, Date dateOfBirth,
			String birthPlace, String multipleBirthInd, String birthOrder,
			String mothersMaidenName, String ssn, String identityNo,
			String insuranceNo, String insuranceType, String insuranceName,
			String genderCd, String ethnicGroupCd, String raceCd,
			String nationalityCd, String languageCd,
			String religionCd, String maritalStatusCd,
			String degree, String email, String address1,
			String postalCode, String addressTypeCd, String address2,
			String postalCode1, String address1TypeCd, String city,
			String state, String country, String countryCode,
			String phoneCountryCode, String phoneAreaCode, String phoneNumber,
			String phoneExt, String phoneTypeCd, String phoneCountryCode1,
			String phoneAreaCode1, String phoneNumber1, String phoneExt1,
			String phoneTypeCd1, String company, String companycontacts,
			String birthplaceCd, String workstatus,
			String profession, String accountIdentifierDomainId,
			String account, String deathInd, String deathTime, Date dateChanged,
			String changedById, Date dateVoided, String voidedById,
			String bloodTypeCd, String rhType, String hospitalCd,
			String custom1, String custom2, String custom3, String custom4,
			String custom5, String custom6, String custom7, String custom8,
			String custom9, String custom10) {
		this.empi = empi;
		this.name = name;
		this.nameCode = nameCode;
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
		this.address1 = address1;
		this.postalCode = postalCode;
		this.addressTypeCd = addressTypeCd;
		this.address2 = address2;
		this.postalCode1 = postalCode1;
		this.address1TypeCd = address1TypeCd;
		this.city = city;
		this.state = state;
		this.country = country;
		this.countryCode = countryCode;
		this.phoneCountryCode = phoneCountryCode;
		this.phoneAreaCode = phoneAreaCode;
		this.phoneNumber = phoneNumber;
		this.phoneExt = phoneExt;
		this.phoneTypeCd = phoneTypeCd;
		this.phoneCountryCode1 = phoneCountryCode1;
		this.phoneAreaCode1 = phoneAreaCode1;
		this.phoneNumber1 = phoneNumber1;
		this.phoneExt1 = phoneExt1;
		this.phoneTypeCd1 = phoneTypeCd1;
		this.company = company;
		this.companycontacts = companycontacts;
		this.birthplaceCd = birthplaceCd;
		this.workstatus = workstatus;
		this.profession = profession;
		this.accountIdentifierDomainId = accountIdentifierDomainId;
		this.account = account;
		this.deathInd = deathInd;
		this.deathTime = deathTime;
		this.dateChanged = dateChanged;
		this.changedById = changedById;
		this.dateVoided = dateVoided;
		this.voidedById = voidedById;
		this.bloodTypeCd = bloodTypeCd;
		this.rhType = rhType;
		this.hospitalCd = hospitalCd;
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
	}

	// Property accessors
	@Id
	@Column(name = "EMPI", unique = true, nullable = false, length = 64)
	public String getEmpi() {
		return this.empi;
	}

	public void setEmpi(String empi) {
		this.empi = empi;
	}

	@Column(name = "NAME", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME_CODE", length = 64)
	public String getNameCode() {
		return this.nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", length = 7)
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
	public String getBirthOrder() {
		return this.birthOrder;
	}

	public void setBirthOrder(String birthOrder) {
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

	@Column(name = "INSURANCE_TYPE", precision = 22, scale = 0)
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

	@Column(name = "GENDER_CD")
	public String getGenderCd() {
		return this.genderCd;
	}

	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	@Column(name = "ETHNIC_GROUP_CD", precision = 22, scale = 0)
	public String getEthnicGroupCd() {
		return this.ethnicGroupCd;
	}

	public void setEthnicGroupCd(String ethnicGroupCd) {
		this.ethnicGroupCd = ethnicGroupCd;
	}

	@Column(name = "RACE_CD", precision = 22, scale = 0)
	public String getRaceCd() {
		return this.raceCd;
	}

	public void setRaceCd(String raceCd) {
		this.raceCd = raceCd;
	}

	@Column(name = "NATIONALITY_CD", precision = 22, scale = 0)
	public String getNationalityCd() {
		return this.nationalityCd;
	}

	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
	}

	@Column(name = "LANGUAGE_CD", precision = 22, scale = 0)
	public String getLanguageCd() {
		return this.languageCd;
	}

	public void setLanguageCd(String languageCd) {
		this.languageCd = languageCd;
	}

	@Column(name = "RELIGION_CD", precision = 22, scale = 0)
	public String getReligionCd() {
		return this.religionCd;
	}

	public void setReligionCd(String religionCd) {
		this.religionCd = religionCd;
	}

	@Column(name = "MARITAL_STATUS_CD")
	public String getMaritalStatusCd() {
		return this.maritalStatusCd;
	}

	public void setMaritalStatusCd(String maritalStatusCd) {
		this.maritalStatusCd = maritalStatusCd;
	}

	@Column(name = "DEGREE", precision = 22, scale = 0)
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

	@Column(name = "ADDRESS1", length = 64)
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "POSTAL_CODE", length = 64)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "ADDRESS_TYPE_CD", precision = 22, scale = 0)
	public String getAddressTypeCd() {
		return this.addressTypeCd;
	}

	public void setAddressTypeCd(String addressTypeCd) {
		this.addressTypeCd = addressTypeCd;
	}

	@Column(name = "ADDRESS2", length = 64)
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "POSTAL_CODE1", length = 64)
	public String getPostalCode1() {
		return this.postalCode1;
	}

	public void setPostalCode1(String postalCode1) {
		this.postalCode1 = postalCode1;
	}

	@Column(name = "ADDRESS1_TYPE_CD", precision = 22, scale = 0)
	public String getAddress1TypeCd() {
		return this.address1TypeCd;
	}

	public void setAddress1TypeCd(String address1TypeCd) {
		this.address1TypeCd = address1TypeCd;
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

	@Column(name = "PHONE_COUNTRY_CODE", length = 64)
	public String getPhoneCountryCode() {
		return this.phoneCountryCode;
	}

	public void setPhoneCountryCode(String phoneCountryCode) {
		this.phoneCountryCode = phoneCountryCode;
	}

	@Column(name = "PHONE_AREA_CODE", length = 64)
	public String getPhoneAreaCode() {
		return this.phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	@Column(name = "PHONE_NUMBER", length = 64)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "PHONE_EXT", length = 64)
	public String getPhoneExt() {
		return this.phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	@Column(name = "PHONE_TYPE_CD", precision = 22, scale = 0)
	public String getPhoneTypeCd() {
		return this.phoneTypeCd;
	}

	public void setPhoneTypeCd(String phoneTypeCd) {
		this.phoneTypeCd = phoneTypeCd;
	}

	@Column(name = "PHONE_COUNTRY_CODE1", length = 64)
	public String getPhoneCountryCode1() {
		return this.phoneCountryCode1;
	}

	public void setPhoneCountryCode1(String phoneCountryCode1) {
		this.phoneCountryCode1 = phoneCountryCode1;
	}

	@Column(name = "PHONE_AREA_CODE1", length = 64)
	public String getPhoneAreaCode1() {
		return this.phoneAreaCode1;
	}

	public void setPhoneAreaCode1(String phoneAreaCode1) {
		this.phoneAreaCode1 = phoneAreaCode1;
	}

	@Column(name = "PHONE_NUMBER1", length = 64)
	public String getPhoneNumber1() {
		return this.phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	@Column(name = "PHONE_EXT1", length = 64)
	public String getPhoneExt1() {
		return this.phoneExt1;
	}

	public void setPhoneExt1(String phoneExt1) {
		this.phoneExt1 = phoneExt1;
	}

	@Column(name = "PHONE_TYPE_CD1", precision = 22, scale = 0)
	public String getPhoneTypeCd1() {
		return this.phoneTypeCd1;
	}

	public void setPhoneTypeCd1(String phoneTypeCd1) {
		this.phoneTypeCd1 = phoneTypeCd1;
	}

	@Column(name = "COMPANY", length = 64)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "COMPANYCONTACTS", length = 64)
	public String getCompanycontacts() {
		return this.companycontacts;
	}

	public void setCompanycontacts(String companycontacts) {
		this.companycontacts = companycontacts;
	}

	@Column(name = "BIRTHPLACE_CD", precision = 22, scale = 0)
	public String getBirthplaceCd() {
		return this.birthplaceCd;
	}

	public void setBirthplaceCd(String birthplaceCd) {
		this.birthplaceCd = birthplaceCd;
	}

	@Column(name = "WORKSTATUS", precision = 22, scale = 0)
	public String getWorkstatus() {
		return this.workstatus;
	}

	public void setWorkstatus(String workstatus) {
		this.workstatus = workstatus;
	}

	@Column(name = "PROFESSION", precision = 22, scale = 0)
	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Column(name = "ACCOUNT_IDENTIFIER_DOMAIN_ID", precision = 22, scale = 0)
	public String getAccountIdentifierDomainId() {
		return this.accountIdentifierDomainId;
	}

	public void setAccountIdentifierDomainId(
			String accountIdentifierDomainId) {
		this.accountIdentifierDomainId = accountIdentifierDomainId;
	}

	@Column(name = "ACCOUNT")
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	@Column(name = "DATE_CHANGED")
	public Date getDateChanged() {
		return this.dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	@Column(name = "CHANGED_BY_ID", precision = 22, scale = 0)
	public String getChangedById() {
		return this.changedById;
	}

	public void setChangedById(String changedById) {
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
	public String getVoidedById() {
		return this.voidedById;
	}

	public void setVoidedById(String voidedById) {
		this.voidedById = voidedById;
	}

	@Column(name = "BLOOD_TYPE_CD", precision = 22, scale = 0)
	public String getBloodTypeCd() {
		return this.bloodTypeCd;
	}

	public void setBloodTypeCd(String bloodTypeCd) {
		this.bloodTypeCd = bloodTypeCd;
	}

	@Column(name = "RH_TYPE", precision = 22, scale = 0)
	public String getRhType() {
		return this.rhType;
	}

	public void setRhType(String rhType) {
		this.rhType = rhType;
	}

	@Column(name = "HOSPITAL_CD", precision = 22, scale = 0)
	public String getHospitalCd() {
		return this.hospitalCd;
	}

	public void setHospitalCd(String hospitalCd) {
		this.hospitalCd = hospitalCd;
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

	@Column(name = "NAME_SPELL_CODE")
	public String getNameSpellCode() {
		return nameSpellCode;
	}

	public void setNameSpellCode(String nameSpellCode) {
		this.nameSpellCode = nameSpellCode;
	}

	@Column(name = "NAME_WB_CODE")
	public String getNameWbCode() {
		return nameWbCode;
	}

	public void setNameWbCode(String nameWbCode) {
		this.nameWbCode = nameWbCode;
	}

	@Column(name = "BIRTH_PROVINCE")
	public String getBirthProvince() {
		return birthProvince;
	}

	public void setBirthProvince(String birthProvince) {
		this.birthProvince = birthProvince;
	}

	@Column(name = "BIRTH_CITY")
	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	@Column(name = "BIRTH_COUNTY")
	public String getBirthCounty() {
		return birthCounty;
	}

	public void setBirthCounty(String birthCounty) {
		this.birthCounty = birthCounty;
	}

	@Column(name = "BIRTH_ZIP")
	public String getBirthZip() {
		return birthZip;
	}

	public void setBirthZip(String birthZip) {
		this.birthZip = birthZip;
	}

	@Column(name = "CITIZEN_CARD")
	public String getCitizenCard() {
		return citizenCard;
	}

	public void setCitizenCard(String citizenCard) {
		this.citizenCard = citizenCard;
	}

	@Column(name = "MEDICAL_CERTIFICATE")
	public String getMedicalCertificate() {
		return medicalCertificate;
	}

	public void setMedicalCertificate(String medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	@Column(name = "MEICARE_PERSON")
	public String getMeicarePerson() {
		return meicarePerson;
	}

	public void setMeicarePerson(String meicarePerson) {
		this.meicarePerson = meicarePerson;
	}

	@Column(name = "ELDER_CERTIFICATE")
	public String getElderCertificate() {
		return elderCertificate;
	}

	public void setElderCertificate(String elderCertificate) {
		this.elderCertificate = elderCertificate;
	}

	@Column(name = "OPCASENO")
	public String getOpcaseno() {
		return opcaseno;
	}

	public void setOpcaseno(String opcaseno) {
		this.opcaseno = opcaseno;
	}

	@Column(name = "GENDER_NAME")
	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	@Column(name = "GENDER_DOMAIN")
	public String getGenderDomain() {
		return genderDomain;
	}

	public void setGenderDomain(String genderDomain) {
		this.genderDomain = genderDomain;
	}

	@Column(name = "ETHNIC_NAME")
	public String getEthnicName() {
		return ethnicName;
	}

	public void setEthnicName(String ethnicName) {
		this.ethnicName = ethnicName;
	}

	@Column(name = "ETHNIC_DOMAIN")
	public String getEthincDomain() {
		return ethincDomain;
	}

	public void setEthincDomain(String ethincDomain) {
		this.ethincDomain = ethincDomain;
	}

	@Column(name = "RACE_NAME")
	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	@Column(name = "RACE_DOMAIN")
	public String getRaceDomain() {
		return raceDomain;
	}

	public void setRaceDomain(String raceDomain) {
		this.raceDomain = raceDomain;
	}

	@Column(name = "NATIONALITY_NAME")
	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	@Column(name = "NATIONALITY_DOMAIN")
	public String getNationalityDomain() {
		return nationalityDomain;
	}

	public void setNationalityDomain(String nationalityDomain) {
		this.nationalityDomain = nationalityDomain;
	}

	@Column(name = "MARITAL_STATUS_NAME")
	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	@Column(name = "MARITAL_DOMAIN")
	public String getMaritalDomain() {
		return maritalDomain;
	}

	public void setMaritalDomain(String maritalDomain) {
		this.maritalDomain = maritalDomain;
	}

	@Column(name = "DEGREE_NAME")
	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	@Column(name = "DEGREE_DOMAIN")
	public String getDegreeDomain() {
		return degreeDomain;
	}

	public void setDegreeDomain(String degreeDomain) {
		this.degreeDomain = degreeDomain;
	}

	@Column(name = "HOME_PROVINCE")
	public String getHomeProvince() {
		return homeProvince;
	}

	public void setHomeProvince(String homeProvince) {
		this.homeProvince = homeProvince;
	}

	@Column(name = "HOME_CITY")
	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	@Column(name = "HOME_COUNTY")
	public String getHomeCounty() {
		return homeCounty;
	}

	public void setHomeCounty(String homeCounty) {
		this.homeCounty = homeCounty;
	}

	@Column(name = "HOME_ZIP")
	public String getHomeZip() {
		return homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	@Column(name = "HOME_ADDRESS")
	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "REGISTERED_PROVINCE")
	public String getRegisteredProvince() {
		return registeredProvince;
	}

	public void setRegisteredProvince(String registeredProvince) {
		this.registeredProvince = registeredProvince;
	}

	@Column(name = "REGISTERED_CITY")
	public String getRegisteredCity() {
		return registeredCity;
	}

	public void setRegisteredCity(String registeredCity) {
		this.registeredCity = registeredCity;
	}

	@Column(name = "REGISTERED_COUNTY")
	public String getRegisteredCounty() {
		return registeredCounty;
	}

	public void setRegisteredCounty(String registeredCounty) {
		this.registeredCounty = registeredCounty;
	}

	@Column(name = "REGISTERED_ZIP")
	public String getRegisteredZip() {
		return registeredZip;
	}

	public void setRegisteredZip(String registeredZip) {
		this.registeredZip = registeredZip;
	}

	@Column(name = "REGISTERED_ADDRESS")
	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	@Column(name = "NATIVE_PROVINCE")
	public String getNativeProvince() {
		return nativeProvince;
	}


	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}

	@Column(name = "NATIVE_CITY")
	public String getNativeCity() {
		return nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	@Column(name = "PROFESSION_NAME")
	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	@Column(name = "PROFESSION_DOMAIN")
	public String getProfessionDomain() {
		return professionDomain;
	}

	public void setProfessionDomain(String professionDomain) {
		this.professionDomain = professionDomain;
	}

	@Column(name = "WORK_ZIP")
	public String getWorkZip() {
		return workZip;
	}

	public void setWorkZip(String workZip) {
		this.workZip = workZip;
	}

	@Column(name = "WORK_ADDRESS")
	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	@Column(name = "PRIVATE_NUMBER")
	public String getPrivateNumber() {
		return privateNumber;
	}

	public void setPrivateNumber(String privateNumber) {
		this.privateNumber = privateNumber;
	}

	@Column(name = "HOME_NUMBER")
	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	@Column(name = "WORK_NUMBER")
	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	@Column(name = "GUARDIAN_PERSON")
	public String getGuardianPerson() {
		return guardianPerson;
	}

	public void setGuardianPerson(String guardianPerson) {
		this.guardianPerson = guardianPerson;
	}

	@Column(name = "VIP")
	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}
	
	@Column(name = "HEALTH_CARD")
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
