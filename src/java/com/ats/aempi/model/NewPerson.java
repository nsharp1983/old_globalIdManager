package com.ats.aempi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Person entity. 
 * @author yrh 2012-03-09
 */
@Entity
@Table(name = "person")
@SequenceGenerator(name="person_seq", sequenceName="person_seq")
public class NewPerson extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -6061320465621019356L;

	private long personId;
	private String givenName;
	private String middleName;
	private String familyName;
	private String prefix;
	private String suffix;
	private String name;
	private String nameCode;
	private Date dateOfBirth;
	private String birthPlace;
	private String multipleBirthInd;
	private Integer birthOrder;
	private String mothersMaidenName;
	private String ssn;
	private String identityNo;
	private String insuranceNo;
	private String insuranceType;
	private String insuranceName;
	private String email;
	private String address1;
	private String postalCode;
	private String address2;
	private String postalCode1;
	private String city;
	private String state;
	private String country;
	private String countryCode;
	private String phoneCountryCode;
	private String phoneAreaCode;
	private String phoneNumber;
	private String phoneExt;
	private String phoneCountryCode1;
	private String phoneAreaCode1;
	private String phoneNumber1;
	private String phoneExt1;
	private String company;
	private String companycontacts;
	private String account;
	private String deathInd;
	private Date deathTime;
	private Date dateCreated;
	private Date dateChanged;
	private Date dateVoided;
	private String groupNumber;
	private Integer rhType;
	private String empi;
	private String custom1;
	private String custom2;
	private String custom3;
	private String custom4;
	private String custom5;
	//患者id+患者域id
	private String custom6;
	private String custom7;
	private String custom8;
	private String custom9;
	//患者id域名
	private String custom10;
	//患者域id
	private String custom11;
	private String custom12;
	private String custom13;
	private String custom14;
	private String custom15;
	//HIS ID
	private String custom16;
	private String custom17;
	private String custom18;
	//HISID+HIS域ID
	private String custom19;
	private String custom20;
	private String custom21;
	private String custom22;
	//HISID+HIS域ID
	private String custom23;
	private String custom24;
	private String custom25;
	private String custom26;
	private String custom27;
	private String custom28;
	private String custom29;
	private String custom30;
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
	private String homeStreet;
	private String registeredStreet;

	Integer totalSize=0;//总条数

	public Integer toltalNum(){
		return totalSize;
	}

    public void setSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    // Property accessors
	@Id
	@Column(name = "person_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="person_seq")
	public long getPersonId() {
		return this.personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}


	@Column(name = "given_name", length = 64)
	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@Column(name = "middle_name", length = 64)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "family_name", length = 64)
	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	@Column(name = "prefix", length = 50)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(name = "suffix", length = 50)
	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_code", length = 64)
	public String getNameCode() {
		return this.nameCode;
	}


	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", length = 4)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "birth_place")
	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Column(name = "multiple_birth_ind", length = 1)
	public String getMultipleBirthInd() {
		return this.multipleBirthInd;
	}

	public void setMultipleBirthInd(String multipleBirthInd) {
		this.multipleBirthInd = multipleBirthInd;
	}

	@Column(name = "birth_order")
	public Integer getBirthOrder() {
		return this.birthOrder;
	}

	public void setBirthOrder(Integer birthOrder) {
		this.birthOrder = birthOrder;
	}

	@Column(name = "mothers_maiden_name", length = 50)
	public String getMothersMaidenName() {
		return this.mothersMaidenName;
	}

	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}

	@Column(name = "ssn", length = 30)
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Column(name = "identity_no", length = 32)
	public String getIdentityNo() {
		return this.identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	@Column(name = "insurance_no", length = 32)
	public String getInsuranceNo() {
		return this.insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	@Column(name = "insurance_type", length = 32)
	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	@Column(name = "insurance_name", length = 32)
	public String getInsuranceName() {
		return this.insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address1", length = 64)
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "postal_code", length = 30)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "address2", length = 64)
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "postal_code1", length = 30)
	public String getPostalCode1() {
		return this.postalCode1;
	}

	public void setPostalCode1(String postalCode1) {
		this.postalCode1 = postalCode1;
	}

	@Column(name = "city", length = 64)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", length = 64)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "country", length = 64)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "country_code", length = 16)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "phone_country_code", length = 32)
	public String getPhoneCountryCode() {
		return this.phoneCountryCode;
	}

	public void setPhoneCountryCode(String phoneCountryCode) {
		this.phoneCountryCode = phoneCountryCode;
	}

	@Column(name = "phone_area_code", length = 32)
	public String getPhoneAreaCode() {
		return this.phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	@Column(name = "phone_number", length = 64)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "phone_ext", length = 30)
	public String getPhoneExt() {
		return this.phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	@Column(name = "phone_country_code1", length = 32)
	public String getPhoneCountryCode1() {
		return this.phoneCountryCode1;
	}

	public void setPhoneCountryCode1(String phoneCountryCode1) {
		this.phoneCountryCode1 = phoneCountryCode1;
	}

	@Column(name = "phone_area_code1", length = 32)
	public String getPhoneAreaCode1() {
		return this.phoneAreaCode1;
	}

	public void setPhoneAreaCode1(String phoneAreaCode1) {
		this.phoneAreaCode1 = phoneAreaCode1;
	}

	@Column(name = "phone_number1", length = 64)
	public String getPhoneNumber1() {
		return this.phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	@Column(name = "phone_ext1", length = 30)
	public String getPhoneExt1() {
		return this.phoneExt1;
	}

	public void setPhoneExt1(String phoneExt1) {
		this.phoneExt1 = phoneExt1;
	}

	@Column(name = "company", length = 64)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "companycontacts", length = 64)
	public String getCompanycontacts() {
		return this.companycontacts;
	}

	public void setCompanycontacts(String companycontacts) {
		this.companycontacts = companycontacts;
	}

	@Column(name = "account")
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	@Column(name = "death_ind", length = 1)
	public String getDeathInd() {
		return this.deathInd;
	}

	public void setDeathInd(String deathInd) {
		this.deathInd = deathInd;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "death_time", length = 4)
	public Date getDeathTime() {
		return this.deathTime;
	}

	public void setDeathTime(Date deathTime) {
		this.deathTime = deathTime;
	}

	@Column(name = "date_created", nullable = false, length = 8)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "date_changed", length = 8)
	public Date getDateChanged() {
		return this.dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	@Column(name = "date_voided", length = 8)
	public Date getDateVoided() {
		return this.dateVoided;
	}

	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}

	@Column(name = "group_number", length = 64)
	public String getGroupNumber() {
		return this.groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	@Column(name = "rh_type")
	public Integer getRhType() {
		return this.rhType;
	}

	public void setRhType(Integer rhType) {
		this.rhType = rhType;
	}

	@Column(name = "empi", length = 32)
	public String getEmpi() {
		return this.empi;
	}

	public void setEmpi(String empi) {
		this.empi = empi;
	}

	@Column(name = "custom1")
	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	@Column(name = "custom2")
	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	@Column(name = "custom3")
	public String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	@Column(name = "custom4")
	public String getCustom4() {
		return this.custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	@Column(name = "custom5")
	public String getCustom5() {
		return this.custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	@Column(name = "custom6")
	public String getCustom6() {
		return this.custom6;
	}

	public void setCustom6(String custom6) {
		this.custom6 = custom6;
	}

	@Column(name = "custom7")
	public String getCustom7() {
		return this.custom7;
	}

	public void setCustom7(String custom7) {
		this.custom7 = custom7;
	}

	@Column(name = "custom8")
	public String getCustom8() {
		return this.custom8;
	}

	public void setCustom8(String custom8) {
		this.custom8 = custom8;
	}

	@Column(name = "custom9")
	public String getCustom9() {
		return this.custom9;
	}

	public void setCustom9(String custom9) {
		this.custom9 = custom9;
	}

	@Column(name = "custom10")
	public String getCustom10() {
		return this.custom10;
	}

	public void setCustom10(String custom10) {
		this.custom10 = custom10;
	}

	@Column(name = "custom11")
	public String getCustom11() {
		return this.custom11;
	}

	public void setCustom11(String custom11) {
		this.custom11 = custom11;
	}

	@Column(name = "custom12")
	public String getCustom12() {
		return this.custom12;
	}

	public void setCustom12(String custom12) {
		this.custom12 = custom12;
	}

	@Column(name = "custom13")
	public String getCustom13() {
		return this.custom13;
	}

	public void setCustom13(String custom13) {
		this.custom13 = custom13;
	}

	@Column(name = "custom14")
	public String getCustom14() {
		return this.custom14;
	}

	public void setCustom14(String custom14) {
		this.custom14 = custom14;
	}

	@Column(name = "custom15")
	public String getCustom15() {
		return this.custom15;
	}

	public void setCustom15(String custom15) {
		this.custom15 = custom15;
	}

	@Column(name = "custom16")
	public String getCustom16() {
		return this.custom16;
	}

	public void setCustom16(String custom16) {
		this.custom16 = custom16;
	}

	@Column(name = "custom17")
	public String getCustom17() {
		return this.custom17;
	}

	public void setCustom17(String custom17) {
		this.custom17 = custom17;
	}

	@Column(name = "custom18")
	public String getCustom18() {
		return this.custom18;
	}

	public void setCustom18(String custom18) {
		this.custom18 = custom18;
	}

	@Column(name = "custom19")
	public String getCustom19() {
		return this.custom19;
	}

	public void setCustom19(String custom19) {
		this.custom19 = custom19;
	}

	@Column(name = "custom20")
	public String getCustom20() {
		return this.custom20;
	}

	public void setCustom20(String custom20) {
		this.custom20 = custom20;
	}

	@Column(name = "custom21")
	public String getCustom21() {
		return custom21;
	}

	public void setCustom21(String custom21) {
		this.custom21 = custom21;
	}

	@Column(name = "custom22")
	public String getCustom22() {
		return custom22;
	}

	public void setCustom22(String custom22) {
		this.custom22 = custom22;
	}

	@Column(name = "custom23")
	public String getCustom23() {
		return custom23;
	}

	public void setCustom23(String custom23) {
		this.custom23 = custom23;
	}

	@Column(name = "custom24")
	public String getCustom24() {
		return custom24;
	}

	public void setCustom24(String custom24) {
		this.custom24 = custom24;
	}

	@Column(name = "custom25")
	public String getCustom25() {
		return custom25;
	}

	public void setCustom25(String custom25) {
		this.custom25 = custom25;
	}

	@Column(name = "custom26")
	public String getCustom26() {
		return custom26;
	}

	public void setCustom26(String custom26) {
		this.custom26 = custom26;
	}

	@Column(name = "custom27")
	public String getCustom27() {
		return custom27;
	}

	public void setCustom27(String custom27) {
		this.custom27 = custom27;
	}

	@Column(name = "custom28")
	public String getCustom28() {
		return custom28;
	}

	public void setCustom28(String custom28) {
		this.custom28 = custom28;
	}

	@Column(name = "custom29")
	public String getCustom29() {
		return custom29;
	}

	public void setCustom29(String custom29) {
		this.custom29 = custom29;
	}

	@Column(name = "custom30")
	public String getCustom30() {
		return custom30;
	}

	public void setCustom30(String custom30) {
		this.custom30 = custom30;
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
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "HEALTH_CARD")
	public String getHealthCard() {
		return healthCard;
	}

	public void setHealthCard(String healthCard) {
		this.healthCard = healthCard;
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

	@Column(name = "home_street")
	public String getHomeStreet() {
		return homeStreet;
	}

	public void setHomeStreet(String homeStreet) {
		this.homeStreet = homeStreet;
	}

	@Column(name = "registered_street")
	public String getRegisteredStreet() {
		return registeredStreet;
	}

	public void setRegisteredStreet(String registeredStreet) {
		this.registeredStreet = registeredStreet;
	}



	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("personId", personId)
				.append("givenName", givenName)
				.append("familyName", familyName)
				.toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Person))
			return false;
		NewPerson castOther = (NewPerson) other;
		return new EqualsBuilder().append(personId, castOther.personId).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(personId).toHashCode();
	}

	public String toStringLong() {
		return new ToStringBuilder(this)
				.append("personId", personId)
				.append("prefix", prefix)
				.append("suffix",suffix)
				.append("givenName", givenName)
				.append("middleName", middleName)
				.append("familyName",familyName)
				.append("mothersMaidenName",mothersMaidenName)
				.append("dateOfBirth", dateOfBirth)
				.append("address1", address1)
				.append("address2", address2)
				.append("identityNo",identityNo)
				.append("city", city)
				.append("state", state)
				.append("postalCode", postalCode)
				.append("country", country)
				.append("countryCode", countryCode)
				.append("phoneCountryCode",phoneCountryCode)
				.append("phoneAreaCode", phoneAreaCode)
				.append("phoneNumber",phoneNumber)
				.append("phoneExt", phoneExt)
				.append("email", email)
				.append("multipleBirthInd",multipleBirthInd)
				.append("birthOrder", birthOrder)
				.append("birthPlace", birthPlace)
				.append("deathInd", deathInd)
				.append("custom1", custom1)
				.append("custom2", custom2)
				.append("custom3", custom3)
				.append("custom4", custom4)
				.append("custom5", custom5)
				.append("custom6", custom6)
				.append("custom7", custom7)
				.append("custom8", custom8)
				.append("custom9", custom9)
				.append("custom10", custom10)
				.append("custom11", custom11)
				.append("custom12", custom12)
				.append("custom13", custom13)
				.append("custom14", custom14)
				.append("custom15", custom15)
				.append("custom16", custom16)
				.append("custom17", custom17)
				.append("custom18", custom18)
				.append("custom19", custom19)
				.append("custom20", custom20)
				.append("ssn", ssn)
				.append("deathTime", deathTime)
				.append("dateCreated",dateCreated)
				.append("dateChanged", dateChanged)
				.append("dateVoided", dateVoided)
				.append("account", account)
				.toString();
	}
}
