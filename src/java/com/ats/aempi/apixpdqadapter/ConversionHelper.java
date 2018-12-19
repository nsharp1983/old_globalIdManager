package com.ats.aempi.apixpdqadapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ats.aexchange.datamodel.TAllergy;
import com.ats.aexchange.datamodel.NextOfKin;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.datamodel.PersonName;
import com.ats.aexchange.datamodel.fPatientVisitList;
import com.ats.apixpdq.api.PdqQuery;
import com.ats.aempi.model.Contactperson;
import com.ats.aempi.model.DegreeTypeDict;
import com.ats.aempi.model.EthnicgroupDict;
import com.ats.aempi.model.GenderDict;
import com.ats.aempi.model.IdentifierDomainDict;
import com.ats.aempi.model.LanguageDict;
import com.ats.aempi.model.NameTypeDict;
import com.ats.aempi.model.NationalityDict;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.model.PhoneTypeDict;
import com.ats.aempi.model.ProfessionTypeDict;
import com.ats.aempi.model.RaceDict;
import com.ats.aempi.model.ReligionDict;
import com.ats.aempi.model.MaritalStatusDict;
import com.ats.aempi.apixpdqadapter.converttopin;

import com.ats.aexchange.datamodel.SharedEnums.SexType;
import com.ats.aexchange.datamodel.Address;
import com.ats.aexchange.datamodel.PhoneNumber;
import com.ats.aexchange.datamodel.Identifier;

import freemarker.template.SimpleDate;

/**
 * 病人查询统计,创建查询条件,建立病人信息
 * 
 * @author yrh
 * @version v1.0 2012-04-09
 * 
 */

public class ConversionHelper
{
	private static final Log log = LogFactory.getLog(ConversionHelper.class);
	
	private static final String MAIDEN_NAME_NAME_TYPE_CODE = "M";
	
	private static final String PHONE_TYPE_CODE_HOME = "HP";
	
	private static final String PHONE_TYPE_CODE_WORK = "O";
	
	private static final String PHONE_TYPE_CODE_CELL = "C";
	
	private static final String PHONE_TYPE_CODE_EMERGENCY = "EC";
	
	private static final String PHONE_TYPE_CODE_FAX = "F";
	
	private static final String PHONE_TYPE_CODE_SERVICE = "AS";
	
	private static final String PHONE_TYPE_CODE_UNKNOWN = "UN";
	
	/**
	*
	* @根据输入的查询条件,确定病人的基本查询信息
	*
	* @基本查询条件为：
	*
	* PID:姓、名、前缀、后缀、身份证号、性别、生日、家庭地址、工作地址、城市、国家、省份、邮编、区号、国家号、电话号码、分机号、电话类型、机构名、机构号、机构类型
	*
	* @author yrh-2012-09-21
	* 
	* @param query
	*/
	public static Person getPerson(PdqQuery query) 
	{
		Person person = new Person();
		
		//获取得到的病人姓、名、前缀、后缀、学位、姓名类别信息填充入对应的person实体类
		if(query.getPersonName()!=null)
		{
			populatePersonName(person, query);
		}
		
		//获取得到的生日信息填充入对应的person实体类
		if(query.getBirthDate()!=null)
		{
			person.setDateOfBirth(query.getBirthDate().getTime());
		}
		
		//出生地所在地的省
		if(query.getBirthProvince()!=null)
		{
			person.setBirthProvince(query.getBirthProvince());
		}
		
		//出生地所在地的市
		if(query.getBirthCity()!=null)
		{
			person.setBirthCity(query.getBirthCity());
		}
		
		//出生地所在区县
		if(query.getBirthCounty()!=null)
		{
			person.setBirthCounty(query.getBirthCounty());
		}
		
		//获取得到的出生地信息填充入对应的person实体类
		if(query.getBirthPlace()!=null)
		{
			person.setBirthPlace(query.getBirthPlace());
		}
		
		//出生地所在地邮编
		if(query.getBirthZip()!=null)
		{
			person.setBirthZip(query.getBirthZip());
		}
		
		//获取多胞胎标志
		if(query.getMultipleBirthIndicator()!=null)
		{
			person.setMultipleBirthInd(query.getMultipleBirthIndicator());
		}
		
		//获取出生顺序
		if(String.valueOf(query.getBirthOrder())!=null)
		{
			person.setBirthOrder(query.getBirthOrder());
		}
		
		//根据获取得到的母氏姓填充入对应的person实体类
		if (query.getMotherMaidenName()!= null) 
		{
			person.setMothersMaidenName(query.getMotherMaidenName().getLastName());
		}
		
		//获取得到的社保信息填充入对应的person实体类
		if(query.getSsn()!=null)
		{
			person.setSsn(query.getSsn());
			
			person.setCustom17(query.getSsn());
		}
				
		//获取身份证件号信息填入
		if(query.getIdentityNO()!=null)
		{
			person.setIdentityNo(query.getIdentityNO());
		}
		 
		//市民卡号
		if(query.getCitizenCard()!=null)
		{
			person.setCitizenCard(query.getCitizenCard());
		}
		
		//医疗证号
		if(query.getMedicalCertificate()!=null)
		{
			person.setMedicalCertificate(query.getMedicalCertificate());
		}
		
		//医保个人编号
		if(query.getMeicarePerson()!=null)
		{
			person.setMeicarePerson(query.getMeicarePerson());
		}
		
		//老人证号
		if(query.getElderCertificate()!=null)
		{
			person.setElderCertificate(query.getElderCertificate());
		}
		
		//病历号
		if(query.getOpcaseno()!=null)
		{
			person.setOpcaseno(query.getOpcaseno());
		}
		
		//获取医保号信息填入
		if(query.getInsuranceNO()!=null)
		{
			person.setInsuranceNo(query.getInsuranceNO());
		}
		
		//获取得到的性别信息填充入对应的person实体类
		if (query.getSex()!= null) 
		{
			GenderDict gender = new GenderDict();
			
			gender.setGenderCode(query.getSex().getCDAValue());
			
			person.setGenderDict(gender);
		}
		
		//性别名称
		if(query.getGenderName()!=null)
		{
			person.setGenderName(query.getGenderName());
		}
		
		//性别编码系统
		if(query.getGenderDomain()!=null)
		{
			person.setGenderDomain(query.getGenderDomain());
		}
		
		//获取得到的民族信息填充入对应的person实体类
		if (query.getEthnicGroup() != null) 
		{
			EthnicgroupDict ethnicGroup = new EthnicgroupDict();
			
			ethnicGroup.setEthnicGroupCode(query.getEthnicGroup());
			
			person.setEthnicgroupDict(ethnicGroup);
		}
		
		//民族名称
		if(query.getEthnicName()!=null)
		{
			person.setEthnicName(query.getEthnicName());
		}
		
		//民族编码系统
		if(query.getEthincDomain()!=null)
		{
			person.setEthincDomain(query.getEthincDomain());
		}
		
		//获取得到的种族信息填充入对应的person实体类
		if (query.getRace() != null) 
		{
			RaceDict race = new RaceDict();
			
			race.setRaceCode(query.getRace());
			
			person.setRaceDict(race);
		}
		
		//种族民称
		if(query.getRaceName()!=null)
		{
			person.setRaceName(query.getRaceName());
		}
		
		//种族编码系统
		 if (query.getRaceDomain()!=null)
		 {
			 person.setRaceDomain(query.getRaceDomain());
		 }
		 
		//获取得到的国籍信息填充入对应的person实体类
		if (query.getNationality() != null) 
		{
			NationalityDict nationality = new NationalityDict();
			
			nationality.setAtionalityCode(query.getNationality());
			
			person.setNationalityDict(nationality);
		}
		
		//国籍名
		if(query.getNationalityName()!=null)
		{
			person.setNationalityName(query.getNationalityName());
		}
		
		//国籍编码系统
		if(query.getNationalityDomain()!=null)
		{
			person.setNationalityDomain(query.getNationalityDomain());
		}
		
		//获取得到的语言信息填充如对应的person实体类
		if (query.getPrimaryLanguage() != null) 
		{
			LanguageDict language = new LanguageDict();
			
			language.setLanguageCode(query.getPrimaryLanguage());
			
			person.setLanguageDict(language);
		}
		
		
		//获取得到的宗教信息填充入对应的person实体类
		if (query.getReligion() != null) 
		{
			ReligionDict religion = new ReligionDict();
			
			religion.setReligionCode(query.getReligion());
			
			person.setReligionDict(religion);
		}
		
		
		//获取得到的婚姻状态信息填充如对应 person实体类
		if (query.getMaritalStatus() != null) 
		{
			MaritalStatusDict maritalStatusDict=new MaritalStatusDict(); 
			
			maritalStatusDict.setMaritalStatusCode(query.getMaritalStatus());
			
			person.setMaritalStatusDict(maritalStatusDict);
		}
		
		//婚姻名称
		if (query.getMaritalStatusName()!=null)
		{
			person.setMaritalStatusName(query.getMaritalStatusName());
		}
		
		
		//婚姻编码系统
		if(query.getMaritalDomain()!=null)
		{
			person.setMaritalDomain(query.getMaritalDomain());
		}
		
		//邮件地址
		if(query.getEmail()!=null)
		{
			person.setEmail(query.getEmail());
		}
		
		//居住地所在地省
		if(query.getHomeProvince()!=null)
		{
			person.setHomeProvince(query.getHomeProvince());
		}
		
		//居住地所在市
		if(query.getHomeCity()!=null)
		{
			person.setHomeCity(query.getHomeCity());
		}
		
		//居住地所在地区县
		if(query.getHomeCounty()!=null)
		{
			person.setHomeCounty(query.getHomeCounty());
		}
		
		//居住地所在地邮编
		if(query.getHomeZip()!=null)
		{
			person.setHomeZip(query.getHomeZip());
		}
		
		//居住地址
		if(query.getHomeAddress()!=null)
		{
			person.setHomeAddress(query.getHomeAddress());
		}
		
		//户口所在地省
		if(query.getRegisteredProvince()!=null)
		{
			person.setRegisteredProvince(query.getRegisteredProvince());
		}
		
		//户口所在地市
		if(query.getRegisteredCity()!=null)
		{
			person.setRegisteredCity(query.getRegisteredCity());
		}
		
		//户口所在地区县
		if(query.getRegisteredCounty()!=null)
		{
			person.setRegisteredCounty(query.getRegisteredCounty());
		}
		
		//户口所在地邮编
		if(query.getRegisteredZip()!=null)
		{
			person.setRegisteredZip(query.getRegisteredZip());
		}
		
		//户口地址
		if(query.getRegisteredAddress()!=null)
		{
			person.setRegisteredAddress(query.getRegisteredAddress());
		}
		
		//籍贯所在地省
		if(query.getNativeProvince()!=null)
		{
			person.setNativeProvince(query.getNativeProvince());
		}
		
		//籍贯所在地市
		if(query.getNativeCity()!=null)
		{
			person.setNativeCity(query.getNativeCity());
		}
		
		//职业编码
		if(query.getProfession()!=null)
		{
			ProfessionTypeDict professionTypeDict=new ProfessionTypeDict(); 
			
			professionTypeDict.setProfessionTypeCode(query.getProfession());
			
			person.setProfessionTypeDict(professionTypeDict);
		}
		
		//职业编码名称
		if(query.getProfessionName()!=null)
		{
			person.setProfessionName(query.getProfessionName());
		}
		
		//职业编码系统
		if(query.getProfessionDomain()!=null)
		{
			person.setProfessionDomain(query.getProfessionDomain());
		}
		
		//工作单位
		 if (query.getCompany()!=null)
		 {
			 person.setCompany(query.getCompany());
		 }
		 
		 //工作邮编
		 if(query.getWorkZip()!=null)
		 {
			 person.setWorkZip(query.getWorkZip());
		 }
		 
		 //单位地址
		 if(query.getWorkAddress()!=null)
		 {
			 person.setWorkAddress(query.getWorkAddress());
		 }
		 
		 //私人电话
		 if(query.getPrivateNumber()!=null)
		 {
			 person.setPrivateNumber(query.getPrivateNumber());
		 }
		 
		 //家庭电话
		  if(query.getHomeNumber()!=null)
		  {
			  person.setHomeNumber(query.getHomeNumber());
		  }
		  
		  //工作电话
		  if(query.getWorkNumber()!=null)
		  {
			  person.setWorkNumber(query.getWorkNumber());
		  }
		  
		  //监护人
		  if(query.getGuardianPerson()!=null)
		  {
			  person.setGuardianPerson(query.getGuardianPerson());
		  }
		  
		  //保密级别
		  if(query.getVip()!=null)
		  {
			  person.setVip(query.getVip());
		  }
		  
		
		//根据获取得到的身份识别信息填充入person实体类
		if (query.getCustom6()!=null)
		{
			person.setCustom6(query.getCustom6());
		}
		
		if (query.getCustom7()!=null)
		{
			person.setCustom7(query.getCustom7());
		}
		
		if (query.getCustom8()!=null)
		{
			person.setCustom8(query.getCustom8());
		}
		
		if (query.getCustom9()!=null)
		{
			person.setCustom9(query.getCustom9());
		}
		
		if (query.getCustom13()!=null)
		{
			person.setCustom13(query.getCustom13());
		}
		
		if (query.getCustom14()!=null)
		{
			person.setCustom14(query.getCustom14());
		}
		
		if (query.getCustom15()!=null)
		{
			person.setCustom15(query.getCustom15());
		}
		
		if (query.getCustom18()!=null)
		{
			person.setCustom18(query.getCustom18());
		}
		
		if (query.getCustom19()!=null)
		{
			person.setCustom19(query.getCustom19());
		}
		
		if (query.getCustom20()!=null)
		{
			person.setCustom20(query.getCustom20());
		}
		
		
		log.trace("Converted object: " + query + " to " + person);	
		
		return person;
	}
	
	/**
	*
	* @根据输入的查询条件,确定病人的基本查询信息
	*
	* @基本查询条件为：
	*
	* PV1:52段
	* 
	* @author yrh-2012-09-21
	* 
	* @param query
	*/
	public static PatientVisit getPatientVisit(PdqQuery query) 
	{
		PatientVisit patientvisit = new PatientVisit();
		
		//PV1-2.1:PAT_CATEGORY
		if(query.getPatCategory()!=null)
		{
			patientvisit.setPatCategory(query.getPatCategory());
		}
		
		//PV1-3.1:PAT_CURRENT_POINT_OF_CARE
		if(query.getPatCurrentPointOfCare()!=null)
		{
			patientvisit.setPatCurrentPointOfCare(query.getPatCurrentPointOfCare());
		}
		
		//PV1-3.2:PAT_CURRENT_ROOM
		if(query.getPatCurrentRoom()!=null)
		{
			patientvisit.setPatCurrentRoom(query.getPatCurrentRoom());
		}
		
		//PV1-3.3:PAT_CURRENT_BED
		if(query.getPatCurrentBed()!=null)
		{
			patientvisit.setPatCurrentBed(query.getPatCurrentBed());
		}
		
		
		//PV1-3.4:PAT_CURRENT_DEP
		if(query.getPatCuurentDep()!=null)
		{
			patientvisit.setPatCuurentDep(query.getPatCuurentDep());
		}
		
		//PV1-3.5:PAT_CURRENT_POSITION_STATUS
		if(query.getPatCurrentPositionStatus()!=null)
		{
			patientvisit.setPatCurrentPositionStatus(query.getPatCurrentPositionStatus());
		}
		
		//PV1-3.6:PAT_CURRENT_POSITION_TYPE
		if(query.getPatCurrentPositionType()!=null)
		{
			patientvisit.setPatCurrentPositionType(query.getPatCurrentPositionType());
		}
		
		//pv1-3.7:PAT_CURRENT_BUILDING
		if(query.getPatCurrentBuilding()!=null)
		{
			patientvisit.setPatCurrentBuilding(query.getPatCurrentBuilding());
		}
		
		//PV1-3.8:PAT_CURRENT_FLOOR
		if(query.getPatCurrentFloor()!=null)
		{
			patientvisit.setPatCurrentFloor(query.getPatCurrentFloor());
		}
		
		//PV1-3.9:PAT_CURRENT_DESCRIPTION
		if(query.getPatCuurentDescription()!=null)
		{
			patientvisit.setPatCuurentDescription(query.getPatCuurentDescription());
		}
		
		//PV1-4.1:PAT_ADMISSION_TYPE
		if(query.getPatAdmissionType()!=null)
		{
			patientvisit.setPatAdmissionType(query.getPatAdmissionType());
		}
		
		//PV1-5.1:PAT_ADMISSION_NUMBER
		if(query.getPatAdmissionNumber()!=null)
		{
			patientvisit.setPatAdmissionNumber(query.getPatAdmissionNumber());
		}
		
		//PV1-6.1:PAT_FORMER_POINT_OF_CARE
		if(query.getPatFormerPointOfCare()!=null)
		{
			patientvisit.setPatFormerPointOfCare(query.getPatFormerPointOfCare());
		}
		
		//PV1-6.2:PAT_FORMER_ROOM
		if(query.getPatFormerRoom()!=null)
		{
			patientvisit.setPatFormerRoom(query.getPatFormerRoom());
		}
		
		//PV1-6.3:PAT_FORMBER_BED
		if(query.getPatFormerBed()!=null)
		{
			patientvisit.setPatFormerBed(query.getPatFormerBed());
		}
		
		//PV1-6.4:PAT_FORMER_DEP
		if(query.getPatFormerDep()!=null)
		{
			patientvisit.setPatFormerDep(query.getPatFormerDep());
		}
		
		//PV1-6.5:PAT_FORMER_POSITION_STATUS
		if(query.getPatFormerPositionStatus()!=null)
		{
			patientvisit.setPatFormerPositionStatus(query.getPatFormerPositionStatus());
		}
		
		//PV1-6.6:PAT_FORER_POSITION_TYPE
		if(query.getPatFormerPositionType()!=null)
		{
			patientvisit.setPatForTempPositionType(query.getPatFormerPositionType());
		}
		
		//PV1-6.7:PAT_FORMER_BUILDING
		if(query.getPatFormerBuilding()!=null)
		{
			patientvisit.setPatFormerBuilding(query.getPatFormerBuilding());
		}
		
		//PV1-6.8:PAT_FORMER_FLOOR
		if(query.getPatFormerFloor()!=null)
		{
			patientvisit.setPatFormerFloor(query.getPatFormerFloor());
		}
		
		//PV1-6.9:PAT_FORMER_DESCRIPTION
		if(query.getPatFormerDescription()!=null)
		{
			patientvisit.setPatFormerDescription(query.getPatFormerDescription());
		}
		
		//PV1-7.2:ADMISSIONS_DOCTOR
		if(query.getAdmissionsDoctor()!=null)
		{
			patientvisit.setAdmissionsDoctor(query.getAdmissionsDoctor());
		}
		
		//PV1-7.1:ADMISSIONS_DOCTOR_ID
		if(query.getAdmissionsDoctorId()!=null)
		{
			patientvisit.setAdmissionsDoctorId(query.getAdmissionsDoctorId());
		}
		
		//PV1-8.1:REFERRING_DOCTOR_ID
		if(query.getReferringDoctorId()!=null)
		{
			patientvisit.setReferringDoctorId(query.getReferringDoctorId());
		}
		
		//PV1-8.2:REFERRING_DOCTOR
		if(query.getReferringDoctor()!=null)
		{
			patientvisit.setReferringDoctor(query.getReferringDoctor());
		}
		
		//PV1-9.1:CONSULTATION_DOCTOR_ID
		if(query.getConsultationDoctorId()!=null)
		{
			patientvisit.setConsultationDoctorId(query.getConsultationDoctorId());
		}
		
		//PV1-9.2:CONSULTATION_DOCTOR
		if(query.getConsultationDoctor()!=null)
		{
			patientvisit.setConsultationDoctor(query.getConsultationDoctor());
		}
		
		//PV1-10.1:HOSPITAL_SERVICE
		if(query.getHospitalService()!=null)
		{
			patientvisit.setHospitalService(query.getHospitalService());
		}
		
		//PV1-11.1:PAT_TEMP_POINT_OF_CARE
		if(query.getPatTempPointOfCare()!=null)
		{
			patientvisit.setPatTempPointOfCare(query.getPatTempPointOfCare());
		}
		
		//PV1-11.2:PAT_TEMP_ROOM
		if(query.getPatTempRoom()!=null)
		{
			patientvisit.setPatTempRoom(query.getPatTempRoom());
		}
		
		//PV1-11.3:PAT_TEMP_BED
		if(query.getPatTempBed()!=null)
		{
			patientvisit.setPatTempBed(query.getPatTempBed());
		}
		
		//PV1-11.4:PAT_TEMP_DEP
		if(query.getPatTempDep()!=null)
		{
			patientvisit.setPatTempDep(query.getPatTempDep());
		}
		
		//PV1-11.5:PAT_TEMP_POSITION_STATUS
		if(query.getPatTempPositionStatus()!=null)
		{
			patientvisit.setPatTempPositionStatus(query.getPatTempPositionStatus());
		}
		
		//PV1-11.6:PAT_TEMP_POSITION_TYPE
		if(query.getPatTempPositionType()!=null)
		{
			patientvisit.setPatTempPositionType(query.getPatTempPositionType());
		}
		
		//PV1-11.7:PAT_TEMP_BUILDING
		if(query.getPatTempBuilding()!=null)
		{
			patientvisit.setPatTempBuilding(query.getPatTempBuilding());
		}
		
		//PV1-11.8:PAT_TEMP_FLOOR
		if(query.getPatTempFloor()!=null)
		{
			patientvisit.setPatTempFloor(query.getPatTempFloor());
		}
		
		//PV1-11.9:PAT_TEMP_DESCRIPTION
		if(query.getPatTempDescription()!=null)
		{
			patientvisit.setPatTempDescription(query.getPatTempDescription());
		}
		
		//PV1-12.1:PAT_ADMISSION_TEST
		if(query.getPatAdmissionTest()!=null)
		{
			patientvisit.setPatAdmissionTest(query.getPatAdmissionTest());
		}
		
		//pv1-13.1:PAT_RE_ADMISSION
		if(query.getPatReAdmission()!=null)
		{
			patientvisit.setPatReAdmission(query.getPatReAdmission());
		}
		
		//PV1-14.1:PAT_ADMISSION_SOURCE
		if(query.getPatAdmissionSource()!=null)
		{
			patientvisit.setPatAdmissionSource(query.getPatAdmissionSource());
		}
		
		//PV1-15.1:离院处置
		if(query.getDischargeName()!=null)
		{
			patientvisit.setPatAmbulatoryStatus(query.getDischargeName());
		}
		
		//离院处置编码系统
		if(query.getDischargeDomain()!=null)
		{
			patientvisit.setDischargeDomain(query.getDischargeDomain());
		}
		
		//入院时情况名称
		if(query.getAdmissionName()!=null)
		{
			patientvisit.setAdmissionName(query.getAdmissionName());
		}
		
		//入院时情况编码系统
		if(query.getAdmissionDomain()!=null)
		{
			patientvisit.setAdmissionDomain(query.getAdmissionDomain());
		}
		
		//病人住院状态名称
		if(query.getIpStatusName()!=null)
		{
			patientvisit.setIpStatusName(query.getIpStatusName());
		}
		
		//病人住院状态编码
		if(query.getIpStatusDomain()!=null)
		{
			patientvisit.setIpStatusDomain(query.getIpStatusDomain());
		}
		
		//病例分型名称
		if(query.getDificultyName()!=null)
		{
			patientvisit.setDificultyName(query.getDificultyName());
		}
		
		//病例分型编码
		if(query.getDificultyDomain()!=null)
		{
			patientvisit.setDificultyDomain(query.getDificultyDomain());
		}
		
		//入院途径名称
		if(query.getAdmissionSourceName()!=null)
		{
			patientvisit.setAdmissionSourceName(query.getAdmissionSourceName());
		}
		
		//入院途径编码系统
		if(query.getAdmissionSourceDomain()!=null)
		{
			patientvisit.setAdmissionSourceDomain(query.getAdmissionSourceDomain());
		}
		
		//支付方式名称
		if(query.getAccountStatusName()!=null)
		{
			patientvisit.setAccountStatusName(query.getAccountStatusName());
		}
		
		//支付方式编码系统
		if(query.getAccountStatusDomain()!=null)
		{
			patientvisit.setAccountStatusDomain(query.getAccountStatusDomain());
		}
		
		//患者类别名称
		if(query.getPatCategoryName()!=null)
		{
			patientvisit.setPatCategoryName(query.getPatCategoryName());
		}
		
		//患者类别编码系统
		if(query.getPatCategorySystem()!=null)
		{
			patientvisit.setPatCategorySystem(query.getPatCategorySystem());
		}
		
		
		
		//PV1-16.1:PAT_VIP
		if(query.getPatVip()!=null)
		{
			patientvisit.setPatVip(query.getPatVip());
		}
		
		//PV1-17.2:PAT_ADMISSION_DOCTORS
		if(query.getPatAdmissionDoctors()!=null)
		{
			patientvisit.setPatAdmissionDoctors(query.getPatAdmissionDoctors());
		}
		
		//PV1-17.1:PAT_ADMISSION_DOCTORS_ID
		if(query.getPatAdmissionDoctorsId()!=null)
		{
			patientvisit.setPatAdmissionDoctorsId(query.getPatAdmissionDoctorsId());
		}
		
		//PV1-18.1:query_CLASS
		if(query.getPatientClass()!=null)
		{
			patientvisit.setPatientClass(query.getPatientClass());
		}
		
		//PV1-19.1:patient_ID
		if(query.getPatientId()!=null)
		{
			patientvisit.setPatientId(query.getPatientId());
		}
		
		//PV1-20.1:PAT_FINANCIAL_CLASS
		if(query.getPatFinancialClass()!=null)
		{
			patientvisit.setPatFinancialClass(query.getPatFinancialClass());
		}
		
		//PV1-21.1:ROOM_BED_COST_PRICE
		if(query.getRoomBedCostPrice()!=null)
		{
			patientvisit.setRoomBedCostPrice(query.getRoomBedCostPrice());
		}
		
		//PV1-22.1:COURTESY_CODE
		if(query.getCourtesyCode()!=null)
		{
			patientvisit.setCourtesyCode(query.getCourtesyCode());
		}
		
		//PV1-23.1:CREDIT_RATING
		if(query.getCreditRating()!=null)
		{
			patientvisit.setCreditRating(query.getCreditRating());
		}
		
		//PV1-24.1:CONTRACT_CODE
		if(query.getContractCode()!=null)
		{
			patientvisit.setContractCode(query.getContractCode());
		}
		
		//PV1-25.1:CONTRACT_CREATE_DATE
		if(query.getContractCreateDate()!=null)
		{
			patientvisit.setContractCreateDate(query.getContractCreateDate());
		}
		
		//PV1-26.1:CONTRACT_PRICE
		if(query.getContractPrice()!=null)
		{
			patientvisit.setContractPrice(query.getContractPrice());
		}
		
		//PV1-27.1:CONTRACT_TIME
		if(query.getContractTime()!=null)
		{
			patientvisit.setContractTime(query.getContractTime());
		}
		
		//PV1-28.1:INTEREST_RATE_CODE
		if(query.getInterestRateCode()!=null)
		{
			patientvisit.setInterestRateCode(query.getInterestRateCode());
		}
		
		//PV1-29.1:BAD_DEBTS
		if(query.getBadDebts()!=null)
		{
			patientvisit.setBadDebts(query.getBadDebts());
		}
		
		//PV1-30.1:BAD_DEBTS_CREATE_DATE
		if(query.getBadDebtsCreateDate()!=null)
		{
			patientvisit.setBadDebtsCreateDate(query.getBadDebtsCreateDate());
		}
		
		//PV1-31.1:BAD_DEBTS_CODE
		if(query.getBadDebtsCode()!=null)
		{
			patientvisit.setBadDebtsCode(query.getBadDebtsCode());
		}
		
		//PV1-32.1:BAD_DEBTS_PRICE
		if(query.getBadDebtsPrice()!=null)
		{
			patientvisit.setBadDebtsPrice(query.getBadDebtsPrice());
		}
		
		//PV1-33.1:BAD_DEBTS_RESTORE_PRICE
		if(query.getBadDebtsRestorePrice()!=null)
		{
			patientvisit.setBadDebtsRestorePrice(query.getBadDebtsRestorePrice());
		}
		
		//PV1-34.1:PAT_ACCOUNT_VOIDED
		if(query.getPatAccountVoided()!=null)
		{
			patientvisit.setPatAccountVoided(query.getPatAccountVoided());
		}
		
		//PV1-35.1:PAT_ACCOUNT_VOIDED_DATE
		if(query.getPatAccountVoidedDate()!=null)
		{
			patientvisit.setPatAccountVoidedDate(query.getPatAccountVoidedDate());
		}
		
		//PV1-36.1: PAT_DISCHARGE_DISPOSITION
		if(query.getPatDischargeDisposition()!=null)
		{
			patientvisit.setPatDischargeDisposition(query.getPatDischargeDisposition());
		}
		
		//PV1-37.1:PAT_DISCHARGE_LOCATION
		if(query.getPatDischargeLocation()!=null)
		{
			patientvisit.setPatDischargeLocation(query.getPatDischargeLocation());
		}
		
		//PV1-38.1:PAT_DIET_TYPE
		if(query.getPatDietType()!=null)
		{
			patientvisit.setPatDietType(query.getPatDietType());
		}
		
		//PV1-39.1:PAT_SERVICE_AGENCIES
		if(query.getPatServiceAgencies()!=null)
		{
			patientvisit.setPatServiceAgencies(query.getPatServiceAgencies());
		}
		
		//PV1-40.1:PAT_BED_STATUS
		if(query.getPatBedStatus()!=null)
		{
			patientvisit.setPatBedStatus(query.getPatBedStatus());
		}
		
		//PV1-41.1:PAT_ACCOUNT_STATUS
		if(query.getPatAccountStatus()!=null)
		{
			patientvisit.setPatAccountStatus(query.getPatAccountStatus());
		}
		
		//PV1-42.1:PAT_DETER_POINT_OF_CARE
		if(query.getPatDeterPointOfCare()!=null)
		{
			patientvisit.setPatDeterPointOfCare(query.getPatDeterPointOfCare());
		}
		
		//PV1-42.2:PAT_DETER_ROOM
		if(query.getPatDeterRoom()!=null)
		{
			patientvisit.setPatDeterRoom(query.getPatDeterRoom());
		}
		
		//PV1-42.3:PAT_DETER_BED
		if(query.getPatDeterBed()!=null)
		{
			patientvisit.setPatDeterBed(query.getPatDeterBed());
		}
		
		//PV1-42.4:PAT_DETER_DEP
		if(query.getPatDeterDep()!=null)
		{
			patientvisit.setPatDeterDep(query.getPatDeterDep());
		}
		
		//PV1-42.5:PAT_DETER_POSITION_STATUS
		if(query.getTend()!=null)
		{
			patientvisit.setTend(query.getTend());
		}
		
		//PV1-42.6:PAT_DETER_POSITION_TYPE
		if(query.getPatNurseCode()!=null)
		{
			patientvisit.setPatNurseCode(query.getPatNurseCode());
		}
		
		//PV1-42.7:PAT_DETER_BUILDING
		if(query.getPatNurseName()!=null)
		{
			patientvisit.setPatNurseName(query.getPatNurseName());
		}
		
		//PV1-42.8:PAT_DETER_FLOOR
		if(query.getOperCode()!=null)
		{
			patientvisit.setOperCode(query.getOperCode());
		}
		
		//PV1-42.9:PAT_DETER_DESCRIPTION
		if(query.getPatDeterDescription()!=null)
		{
			patientvisit.setPatDeterDescription(query.getPatDeterDescription());
		}
		
		//PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
		if(query.getOperDate()!=null)
		{
			patientvisit.setOperDate(query.getOperDate());
		}
		
		//PV1-43.2:PAT_FOR_TEMP_ROOM
		if(query.getPatForTempRoom()!=null)
		{
			patientvisit.setPatForTempRoom(query.getPatForTempRoom());
		}
		
		//PV1-43.3:pat_for_temp_bed
		if(query.getPatForTempBed()!=null)
		{
			patientvisit.setPatForTempBed(query.getPatForTempBed());
		}
		
		//PV1-43.4:PAT_FOR_TEMP_DEP
		if(query.getPatForTempDep()!=null)
		{
			patientvisit.setPatForTempDep(query.getPatForTempDep());
		}
		
		//PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
		if(query.getPatForTempPositionStatus()!=null)
		{
			patientvisit.setPatForTempPositionStatus(query.getPatForTempPositionStatus());
		}
		
		//PV1-43.6:PAT_FOR_TEMP_POSITION_TYPE
		if(query.getPatForTempPositionType()!=null)
		{
			patientvisit.setPatForTempPositionType(query.getPatForTempPositionType());
		}
		
		//PV1-43.7:PAT_FOR_TEMP_BUILDING
		if(query.getPatForTempBuilding()!=null)
		{
			patientvisit.setPatForTempBuilding(query.getPatForTempBuilding());
		}
		
		//PV1-43.8:PAT_FOR_TEMP_FLOOR
		if(query.getPatForTempFloor()!=null)
		{
			patientvisit.setPatForTempFloor(query.getPatForTempFloor());
		}
		
		//PV1-43.9:PAT_FOR_TEMP_DESCRIPTION
		if(query.getPatTempDescription()!=null)
		{
			patientvisit.setPatForTempDescription(query.getPatFormerDescription());
		}
		
		//PV1-44.1:ADMIT_DATE
		if(query.getAdmitDate()!=null)
		{
			//patientvisit.setAdmitDate(query.getAdmitDate());
		}
		
		//PV1-45.1:DISCHARGE_DATE
		if(query.getDischargeDate()!=null)
		{
			//patientvisit.setDischargeDate(query.getDischargeDate());
		}
		
		//PV1-46.1:PAT_DIFFERENCE
		if(query.getPatDifference()!=null)
		{
			patientvisit.setPatDifference(query.getPatDifference());
		}
		
		//PV1-47.1:PAT_TOTAL_COST
		if(query.getPatTotalCost()!=null)
		{
			patientvisit.setPatTotalCost(query.getPatTotalCost());
		}
		
		//PV1-48.1:PAT_TOTAL_DISPATCH
		if(query.getPatTotalDispatch()!=null)
		{
			patientvisit.setPatTotalDispatch(query.getPatTotalDispatch());
		}
		
		//PV1-49.1:PAT_TOTAL_AMOUNT_PAYABLE
		if(query.getPatTotalAmountPayable()!=null)
		{
			patientvisit.setPatTotalAmountPayable(query.getPatTotalAmountPayable());
		}
		
		//PV1-50.1:PAT_SPARE_ID
		if(query.getBabyFlag()!=null)
		{
			patientvisit.setBabyFlag(query.getBabyFlag());
		}
		
		//PV1-51.1:PAT_VISIT_LOGO
		if(query.getAdmitWeight()!=null)
		{
			patientvisit.setAdmitWeight(query.getAdmitWeight());
		}
		
		if(query.getAdmitWeightUnit()!=null)
		{
			patientvisit.setAdmitWeightUnit(query.getAdmitWeightUnit());
		}
		
		if(query.getBirthWeight()!=null)
		{
			patientvisit.setBirthWeight(query.getBirthWeight());
		}
		
		if(query.getBabyWeightUnit()!=null)
		{
			patientvisit.setBabyWeightUnit(query.getBabyWeightUnit());
		}
		
		//PV1-52.1:OTHER_MEDICAL_INSTITUTIONS
		if(query.getOtherMedicalInstitutions()!=null)
		{
			patientvisit.setOtherMedicalInstitutions(query.getOtherMedicalInstitutions());
		}
		log.trace("Converted object: " + query + " to " + patientvisit);	
		
		return patientvisit;
	}
	/**
	* @函数作用
	* 
	*用于病人信息查询,根据查询数据库获取到的person类信息，转换成对应的patient类信息
	*
	* @author yrh-2012-04-09
	* 
	* @param person
	* 
	*/
	public static Patient getPatient(Person person) 
	{
		Patient patient = new Patient();
		
		//转换病人姓名，包含姓、名、前缀、后缀、学位、名字类型、曾用名
		patient.setPatientName(getPersonName(person));
        
		//获取得到的生日信息填充入对应的person实体类
		if(person.getDateOfBirth()!=null)
		{
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime(person.getDateOfBirth());
			
			patient.setBirthDateTime(calendar);
		}
		
		//出生地所在地的省
		if(person.getBirthProvince()!=null)
		{
			patient.setBirthProvince(person.getBirthProvince());
		}
		
		//出生地所在地的市
		if(person.getBirthCity()!=null)
		{
			patient.setBirthCity(person.getBirthCity());
		}
		
		//出生地所在区县
		if(person.getBirthCounty()!=null)
		{
			patient.setBirthCounty(person.getBirthCounty());
		}
		
		//获取得到的出生地信息填充入对应的person实体类
		if(person.getBirthPlace()!=null)
		{
			patient.setBirthPlace(person.getBirthPlace());
		}
		
		//出生地所在地邮编
		if(person.getBirthZip()!=null)
		{
			patient.setBirthZip(person.getBirthZip());
		}
		
		//获取多胞胎标志
		if(person.getMultipleBirthInd()!=null)
		{
			patient.setMulitpleBirthIndicator(person.getMultipleBirthInd());
		}
		
		//获取出生顺序
		if(String.valueOf(person.getBirthOrder())!=null)
		{
			patient.setBirthOrder(person.getBirthOrder());
		}
		
		//转换母系姓氏
		if (person.getMothersMaidenName() != null) 
		{
			PersonName maidenName = new PersonName();
			
			maidenName.setLastName(person.getMothersMaidenName());
			
			maidenName.setNameTypeCode(MAIDEN_NAME_NAME_TYPE_CODE);
			
			patient.setMothersMaidenName(maidenName);			
		}
		
		//获取得到的社保信息填充入对应的person实体类
		if(person.getSsn()!=null)
		{
			patient.setSsn(person.getSsn());
			
			patient.setCustom17(person.getSsn());
		}
				
		//获取身份证件号信息填入
		if(person.getIdentityNo()!=null)
		{
			patient.setIdentityNO(person.getIdentityNo());
		}
		 
		//市民卡号
		if(person.getCitizenCard()!=null)
		{
			patient.setCitizenCard(person.getCitizenCard());
		}
		
		//医疗证号
		if(person.getMedicalCertificate()!=null)
		{
			patient.setMedicalCertificate(person.getMedicalCertificate());
		}
		
		//医保个人编号
		if(person.getMeicarePerson()!=null)
		{
			patient.setMeicarePerson(person.getMeicarePerson());
		}
		
		//老人证号
		if(person.getElderCertificate()!=null)
		{
			patient.setElderCertificate(person.getElderCertificate());
		}
		
		//病历号
		if(person.getOpcaseno()!=null)
		{
			patient.setOpcaseno(person.getOpcaseno());
		}
		
		//获取医保号信息填入
		if(person.getInsuranceNo()!=null)
		{
			patient.setInsuranceNO(person.getInsuranceNo());
		}
		
		//转换性别
		if (person.getGenderDict() != null) 
		{
			SexType sexType = SexType.UNKNOWN;
			
			try 
			{
				sexType = SexType.getByString(person.getGenderDict().getGenderCode());
			} 
			catch (IllegalArgumentException e) 
			{
				log.error(e.getMessage());
			}
			
			patient.setAdministrativeSex(sexType);
		}
		
		//性别名称
		if(person.getGenderName()!=null)
		{
			patient.setGenderName(person.getGenderName());
		}
		
		//性别编码系统
		if(person.getGenderDomain()!=null)
		{
			patient.setGenderDomain(person.getGenderDomain());
		}
		
		//获取得到的民族信息填充入对应的person实体类
		if (person.getEthnicgroupDict() != null) 
		{
			patient.setEthnicGroup(person.getEthnicgroupDict().getEthnicGroupCode());
		}
		
		//民族名称
		if(person.getEthnicName()!=null)
		{
			patient.setEthnicName(person.getEthnicName());
		}
		
		//民族编码系统
		if(person.getEthincDomain()!=null)
		{
			patient.setEthincDomain(person.getEthincDomain());
		}
		
		//获取得到的种族信息填充入对应的person实体类
		if (person.getRaceDict()!= null) 
		{
			patient.setRace(person.getRaceDict().getRaceCode());
		}
		
		//种族民称
		if(person.getRaceName()!=null)
		{
			patient.setRaceName(person.getRaceName());
		}
		
		//种族编码系统
		 if (person.getRaceDomain()!=null)
		 {
			 patient.setRaceDomain(person.getRaceDomain());
		 }
		 
		//获取得到的国籍信息填充入对应的person实体类
		if (person.getNationalityDict() != null) 
		{
			patient.setNationality(person.getNationalityDict().getAtionalityCode());
		}
		
		//国籍名
		if(person.getNationalityName()!=null)
		{
			patient.setNationalityName(person.getNationalityName());
		}
		
		//国籍编码系统
		if(person.getNationalityDomain()!=null)
		{
			patient.setNationalityDomain(person.getNationalityDomain());
		}
		
		//获取得到的语言信息填充如对应的person实体类
		if (person.getLanguageDict() != null) 
		{
			patient.setPrimaryLanguage(person.getLanguageDict().getLanguageCode());
		}
		
		
		//获取得到的宗教信息填充入对应的person实体类
		if (person.getReligionDict() != null) 
		{
			patient.setReligion(person.getReligionDict().getReligionCode());
		}
		
		
		//获取得到的婚姻状态信息填充如对应 person实体类
		if (person.getMaritalStatusDict() != null) 
		{
			patient.setMaritalStatus(person.getMaritalStatusDict().getMaritalStatusCode());
		}
		
		//婚姻名称
		if (person.getMaritalStatusName()!=null)
		{
			patient.setMaritalStatusName(person.getMaritalStatusName());
		}
		
		
		//婚姻编码系统
		if(person.getMaritalDomain()!=null)
		{
			patient.setMaritalDomain(person.getMaritalDomain());
		}
		
		//邮件地址
		if(person.getEmail()!=null)
		{
			patient.setEmail(person.getEmail());
		}
		
		//居住地所在地省
		if(person.getHomeProvince()!=null)
		{
			patient.setHomeProvince(person.getHomeProvince());
		}
		
		//居住地所在市
		if(person.getHomeCity()!=null)
		{
			patient.setHomeCity(person.getHomeCity());
		}
		
		//居住地所在地区县
		if(person.getHomeCounty()!=null)
		{
			patient.setHomeCounty(person.getHomeCounty());
		}
		
		//居住地所在地邮编
		if(person.getHomeZip()!=null)
		{
			patient.setHomeZip(person.getHomeZip());
		}
		
		//居住地址
		if(person.getHomeAddress()!=null)
		{
			patient.setHomeAddress(person.getHomeAddress());
		}
		
		//户口所在地省
		if(person.getRegisteredProvince()!=null)
		{
			patient.setRegisteredProvince(person.getRegisteredProvince());
		}
		
		//户口所在地市
		if(person.getRegisteredCity()!=null)
		{
			patient.setRegisteredCity(person.getRegisteredCity());
		}
		
		//户口所在地区县
		if(person.getRegisteredCounty()!=null)
		{
			patient.setRegisteredCounty(person.getRegisteredCounty());
		}
		
		//户口所在地邮编
		if(person.getRegisteredZip()!=null)
		{
			patient.setRegisteredZip(person.getRegisteredZip());
		}
		
		//户口地址
		if(person.getRegisteredAddress()!=null)
		{
			patient.setRegisteredAddress(person.getRegisteredAddress());
		}
		
		//籍贯所在地省
		if(person.getNativeProvince()!=null)
		{
			patient.setNativeProvince(person.getNativeProvince());
		}
		
		//籍贯所在地市
		if(person.getNativeCity()!=null)
		{
			patient.setNativeCity(person.getNativeCity());
		}
		
		//职业编码
		if(person.getProfessionTypeDict()!=null)
		{
			patient.setProfession(person.getProfessionTypeDict().getProfessionTypeCode());
		}
		
		//职业编码名称
		if(person.getProfessionName()!=null)
		{
			patient.setProfessionName(person.getProfessionName());
		}
		
		//职业编码系统
		if(person.getProfessionDomain()!=null)
		{
			patient.setProfessionDomain(person.getProfessionDomain());
		}
		
		//工作单位
		 if (person.getCompany()!=null)
		 {
			 patient.setCompany(person.getCompany());
		 }
		 
		 //工作邮编
		 if(person.getWorkZip()!=null)
		 {
			 patient.setWorkZip(person.getWorkZip());
		 }
		 
		 //单位地址
		 if(person.getWorkAddress()!=null)
		 {
			 patient.setWorkAddress(person.getWorkAddress());
		 }
		 
		 //私人电话
		 if(person.getPrivateNumber()!=null)
		 {
			 patient.setPrivateNumber(person.getPrivateNumber());
		 }
		 
		 //家庭电话
		  if(person.getHomeNumber()!=null)
		  {
			  patient.setHomeNumber(person.getHomeNumber());
		  }
		  
		  //工作电话
		  if(person.getWorkNumber()!=null)
		  {
			  patient.setWorkNumber(person.getWorkNumber());
		  }
		  
		  //监护人
		  if(person.getGuardianPerson()!=null)
		  {
			  patient.setGuardianPerson(person.getGuardianPerson());
		  }
		  
		  //保密级别
		  if(person.getVip()!=null)
		  {
			  patient.setVip(person.getVip());
		  }
		  
		//添加病人身份信息
			for (PersonIdentifierEMPI identifier : person.getPersonIdentifiers()) 
			{
				patient.addPatientId(ConversionHelper.getPatientIdentifier(identifier));
			}
			
		
		//根据获取得到的身份识别信息填充入person实体类
		if (person.getCustom6()!=null)
		{
			patient.setCustom6(person.getCustom6());
		}
		
		if (person.getCustom7()!=null)
		{
			patient.setCustom7(person.getCustom7());
		}
		
		if (person.getCustom8()!=null)
		{
			patient.setCustom8(person.getCustom8());
		}
		
		if (person.getCustom9()!=null)
		{
			patient.setCustom9(person.getCustom9());
		}
		
		if (person.getCustom13()!=null)
		{
			patient.setCustom13(person.getCustom13());
		}
		
		if (person.getCustom14()!=null)
		{
			patient.setCustom14(person.getCustom14());
		}
		
		if (person.getCustom15()!=null)
		{
			patient.setCustom15(person.getCustom15());
		}
		
		if (person.getCustom18()!=null)
		{
			patient.setCustom18(person.getCustom18());
		}
		
		if (person.getCustom19()!=null)
		{
			patient.setCustom19(person.getCustom19());
		}
		
		if (person.getCustom20()!=null)
		{
			patient.setCustom20(person.getCustom20());
		}
		
		
		log.trace("Converted object: " + person + " to " + patient);
		
		return patient;
	}

	/**
	* @函数作用
	* 
	*用于病人信息查询,根据查询数据库获取到的patientvisit类信息，转换成对应的patient类信息
	*
	* @author yrh-2012-09-21
	* 
	* @param patientvisit
	* 
	*/
	public static Patient getPatient(Patient patient,List<PatientVisit> patientvisitlist) 
	{
		List<fPatientVisitList> mypatientvisit = new ArrayList<fPatientVisitList>();
		
		for(PatientVisit patientvisit:patientvisitlist)
		{
		
			fPatientVisitList fpatientvisitlist = new fPatientVisitList();
			
		//PV1-2.1:PAT_CATEGORY
		if(patientvisit.getPatCategory()!=null)
		{
			fpatientvisitlist.setPatCategory(patientvisit.getPatCategory());
		}
		
		//PV1-3.1:PAT_CURRENT_POINT_OF_CARE
		if(patientvisit.getPatCurrentPointOfCare()!=null)
		{
			fpatientvisitlist.setPatCurrentPointOfCare(patientvisit.getPatCurrentPointOfCare());
		}
		
		//PV1-3.2:PAT_CURRENT_ROOM
		if(patientvisit.getPatCurrentRoom()!=null)
		{
			fpatientvisitlist.setPatCurrentRoom(patientvisit.getPatCurrentRoom());
		}
		
		//PV1-3.3:PAT_CURRENT_BED
		if(patientvisit.getPatCurrentBed()!=null)
		{
			fpatientvisitlist.setPatCurrentBed(patientvisit.getPatCurrentBed());
		}
		
		
		//PV1-3.4:PAT_CURRENT_DEP
		if(patientvisit.getPatCuurentDep()!=null)
		{
			fpatientvisitlist.setPatCuurentDep(patientvisit.getPatCuurentDep());
		}
		
		//PV1-3.5:PAT_CURRENT_POSITION_STATUS
		if(patientvisit.getPatCurrentPositionStatus()!=null)
		{
			fpatientvisitlist.setPatCurrentPositionStatus(patientvisit.getPatCurrentPositionStatus());
		}
		
		//PV1-3.6:PAT_CURRENT_POSITION_TYPE
		if(patientvisit.getPatCurrentPositionType()!=null)
		{
			fpatientvisitlist.setPatCurrentPositionType(patientvisit.getPatCurrentPositionType());
		}
		
		//pv1-3.7:PAT_CURRENT_BUILDING
		if(patientvisit.getPatCurrentBuilding()!=null)
		{
			fpatientvisitlist.setPatCurrentBuilding(patientvisit.getPatCurrentBuilding());
		}
		
		//PV1-3.8:PAT_CURRENT_FLOOR
		if(patientvisit.getPatCurrentFloor()!=null)
		{
			fpatientvisitlist.setPatCurrentFloor(patientvisit.getPatCurrentFloor());
		}
		
		//PV1-3.9:PAT_CURRENT_DESCRIPTION
		if(patientvisit.getPatCuurentDescription()!=null)
		{
			fpatientvisitlist.setPatCuurentDescription(patientvisit.getPatCuurentDescription());
		}
		
		//PV1-4.1:PAT_ADMISSION_TYPE
		if(patientvisit.getPatAdmissionType()!=null)
		{
			fpatientvisitlist.setPatAdmissionType(patientvisit.getPatAdmissionType());
		}
		
		//PV1-5.1:PAT_ADMISSION_NUMBER
		if(patientvisit.getPatAdmissionNumber()!=null)
		{
			fpatientvisitlist.setPatAdmissionNumber(patientvisit.getPatAdmissionNumber());
		}
		
		//PV1-6.1:PAT_FORMER_POINT_OF_CARE
		if(patientvisit.getPatFormerPointOfCare()!=null)
		{
			fpatientvisitlist.setPatFormerPointOfCare(patientvisit.getPatFormerPointOfCare());
		}
		
		//PV1-6.2:PAT_FORMER_ROOM
		if(patientvisit.getPatFormerRoom()!=null)
		{
			fpatientvisitlist.setPatFormerRoom(patientvisit.getPatFormerRoom());
		}
		
		//PV1-6.3:PAT_FORMBER_BED
		if(patientvisit.getPatFormerBed()!=null)
		{
			fpatientvisitlist.setPatFormerBed(patientvisit.getPatFormerBed());
		}
		
		//PV1-6.4:PAT_FORMER_DEP
		if(patientvisit.getPatFormerDep()!=null)
		{
			fpatientvisitlist.setPatFormerDep(patientvisit.getPatFormerDep());
		}
		
		//PV1-6.5:PAT_FORMER_POSITION_STATUS
		if(patientvisit.getPatFormerPositionStatus()!=null)
		{
			fpatientvisitlist.setPatFormerPositionStatus(patientvisit.getPatFormerPositionStatus());
		}
		
		//PV1-6.6:PAT_FORER_POSITION_TYPE
		if(patientvisit.getPatFormerPositionType()!=null)
		{
			fpatientvisitlist.setPatForTempPositionType(patientvisit.getPatFormerPositionType());
		}
		
		//PV1-6.7:PAT_FORMER_BUILDING
		if(patientvisit.getPatFormerBuilding()!=null)
		{
			fpatientvisitlist.setPatFormerBuilding(patientvisit.getPatFormerBuilding());
		}
		
		//PV1-6.8:PAT_FORMER_FLOOR
		if(patientvisit.getPatFormerFloor()!=null)
		{
			fpatientvisitlist.setPatFormerFloor(patientvisit.getPatFormerFloor());
		}
		
		//PV1-6.9:PAT_FORMER_DESCRIPTION
		if(patientvisit.getPatFormerDescription()!=null)
		{
			fpatientvisitlist.setPatFormerDescription(patientvisit.getPatFormerDescription());
		}
		
		//PV1-7.2:ADMISSIONS_DOCTOR
		if(patientvisit.getAdmissionsDoctor()!=null)
		{
			fpatientvisitlist.setAdmissionsDoctor(patientvisit.getAdmissionsDoctor());
		}
		
		//PV1-7.1:ADMISSIONS_DOCTOR_ID
		if(patientvisit.getAdmissionsDoctorId()!=null)
		{
			fpatientvisitlist.setAdmissionsDoctorId(patientvisit.getAdmissionsDoctorId());
		}
		
		//PV1-8.1:REFERRING_DOCTOR_ID
		if(patientvisit.getReferringDoctorId()!=null)
		{
			fpatientvisitlist.setReferringDoctorId(patientvisit.getReferringDoctorId());
		}
		
		//PV1-8.2:REFERRING_DOCTOR
		if(patientvisit.getReferringDoctor()!=null)
		{
			fpatientvisitlist.setReferringDoctor(patientvisit.getReferringDoctor());
		}
		
		//PV1-9.1:CONSULTATION_DOCTOR_ID
		if(patientvisit.getConsultationDoctorId()!=null)
		{
			fpatientvisitlist.setConsultationDoctorId(patientvisit.getConsultationDoctorId());
		}
		
		//PV1-9.2:CONSULTATION_DOCTOR
		if(patientvisit.getConsultationDoctor()!=null)
		{
			fpatientvisitlist.setConsultationDoctor(patientvisit.getConsultationDoctor());
		}
		
		//PV1-10.1:HOSPITAL_SERVICE
		if(patientvisit.getHospitalService()!=null)
		{
			fpatientvisitlist.setHospitalService(patientvisit.getHospitalService());
		}
		
		//PV1-11.1:PAT_TEMP_POINT_OF_CARE
		if(patientvisit.getPatTempPointOfCare()!=null)
		{
			fpatientvisitlist.setPatTempPointOfCare(patientvisit.getPatTempPointOfCare());
		}
		
		//PV1-11.2:PAT_TEMP_ROOM
		if(patientvisit.getPatTempRoom()!=null)
		{
			fpatientvisitlist.setPatTempRoom(patientvisit.getPatTempRoom());
		}
		
		//PV1-11.3:PAT_TEMP_BED
		if(patientvisit.getPatTempBed()!=null)
		{
			fpatientvisitlist.setPatTempBed(patientvisit.getPatTempBed());
		}
		
		//PV1-11.4:PAT_TEMP_DEP
		if(patientvisit.getPatTempDep()!=null)
		{
			fpatientvisitlist.setPatTempDep(patientvisit.getPatTempDep());
		}
		
		//PV1-11.5:PAT_TEMP_POSITION_STATUS
		if(patientvisit.getPatTempPositionStatus()!=null)
		{
			fpatientvisitlist.setPatTempPositionStatus(patientvisit.getPatTempPositionStatus());
		}
		
		//PV1-11.6:PAT_TEMP_POSITION_TYPE
		if(patientvisit.getPatTempPositionType()!=null)
		{
			fpatientvisitlist.setPatTempPositionType(patientvisit.getPatTempPositionType());
		}
		
		//PV1-11.7:PAT_TEMP_BUILDING
		if(patientvisit.getPatTempBuilding()!=null)
		{
			fpatientvisitlist.setPatTempBuilding(patientvisit.getPatTempBuilding());
		}
		
		//PV1-11.8:PAT_TEMP_FLOOR
		if(patientvisit.getPatTempFloor()!=null)
		{
			fpatientvisitlist.setPatTempFloor(patientvisit.getPatTempFloor());
		}
		
		//PV1-11.9:PAT_TEMP_DESCRIPTION
		if(patientvisit.getPatTempDescription()!=null)
		{
			fpatientvisitlist.setPatTempDescription(patientvisit.getPatTempDescription());
		}
		
		//PV1-12.1:PAT_ADMISSION_TEST
		if(patientvisit.getPatAdmissionTest()!=null)
		{
			fpatientvisitlist.setPatAdmissionTest(patientvisit.getPatAdmissionTest());
		}
		
		//pv1-13.1:PAT_RE_ADMISSION
		if(patientvisit.getPatIpTimes()!=null)
		{
			fpatientvisitlist.setPatIpTimes(patientvisit.getPatIpTimes());
		}
		
		//PV1-14.1:PAT_ADMISSION_SOURCE
		if(patientvisit.getPatAdmissionSource()!=null)
		{
			fpatientvisitlist.setPatAdmissionSource(patientvisit.getPatAdmissionSource());
		}
		
		//PV1-15.1:PAT_AMBULATORY_STATUS
		if(patientvisit.getDischargeName()!=null)
		{
			fpatientvisitlist.setDischargeName(patientvisit.getDischargeName());
		}
		
		if(patientvisit.getDischargeDomain()!=null)
		{
			fpatientvisitlist.setDischargeDomain(patientvisit.getDischargeDomain());
		}
		
		if(patientvisit.getAdmissionName()!=null)
		{
			fpatientvisitlist.setAdmissionName(patientvisit.getAdmissionName());
		}
		
		if(patientvisit.getAdmissionDomain()!=null)
		{
			fpatientvisitlist.setAdmissionDomain(patientvisit.getAdmissionDomain());
		}
		
		if(patientvisit.getIpStatusName()!=null)
		{
			fpatientvisitlist.setIpStatusName(patientvisit.getIpStatusName());
		}
		
		if(patientvisit.getIpStatusDomain()!=null)
		{
			fpatientvisitlist.setIpStatusDomain(patientvisit.getIpStatusDomain());
		}
		
		if(patientvisit.getDificultyName()!=null)
		{
			fpatientvisitlist.setDificultyName(patientvisit.getDificultyName());
		}
		
		if(patientvisit.getDificultyDomain()!=null)
		{
			fpatientvisitlist.setDificultyDomain(patientvisit.getDificultyDomain());
		}
		
		if(patientvisit.getAdmissionSourceName()!=null)
		{
			fpatientvisitlist.setAdmissionSourceName(patientvisit.getAdmissionSourceName());
		}
		
		if(patientvisit.getAdmissionSourceDomain()!=null)
		{
			fpatientvisitlist.setAdmissionSourceDomain(patientvisit.getAdmissionSourceDomain());
		}
		
		if(patientvisit.getAccountStatusName()!=null)
		{
			fpatientvisitlist.setAccountStatusName(patientvisit.getAccountStatusName());
		}
		
		if(patientvisit.getAccountStatusDomain()!=null)
		{
			fpatientvisitlist.setAccountStatusDomain(patientvisit.getAccountStatusDomain());
		}
		
		if(patientvisit.getPatCategoryName()!=null)
		{
			fpatientvisitlist.setPatCategoryName(patientvisit.getPatCategoryName());
		}
		
		if(patientvisit.getPatCategorySystem()!=null)
		{
			fpatientvisitlist.setPatCategorySystem(patientvisit.getPatCategorySystem());
		}
		
		//患者类型编码
		if(patientvisit.getPatientClassName()!=null)
		{
			fpatientvisitlist.setPatientClassName(patientvisit.getPatientClassName());
		}
		
		//患者类型编码系统
		if(patientvisit.getPatientClassDomain()!=null)
		{
			fpatientvisitlist.setPatientClassDomain(patientvisit.getPatientClassDomain());
		}
		
		//病人来源
		if(patientvisit.getPatientSourceName()!=null)
		{
			fpatientvisitlist.setPatientSourceName(patientvisit.getPatientSourceName());
		}
		
		//病人再入院标识
		 if(patientvisit.getPatReAdmission()!=null)
		 {
			 fpatientvisitlist.setPatReAdmission(patientvisit.getPatReAdmission());
		 }
		 
		 //是否急疹转住院
		 if(patientvisit.getIsEmergency()!=null)
		 {
			 fpatientvisitlist.setIsEmergency(patientvisit.getIsEmergency());
		 }
		
		//PV1-16.1:PAT_VIP
		if(patientvisit.getPatVip()!=null)
		{
			fpatientvisitlist.setPatVip(patientvisit.getPatVip());
		}
		
		//PV1-17.2:PAT_ADMISSION_DOCTORS
		if(patientvisit.getPatAdmissionDoctors()!=null)
		{
			fpatientvisitlist.setPatAdmissionDoctors(patientvisit.getPatAdmissionDoctors());
		}
		
		//PV1-17.1:PAT_ADMISSION_DOCTORS_ID
		if(patientvisit.getPatAdmissionDoctorsId()!=null)
		{
			fpatientvisitlist.setPatAdmissionDoctorsId(patientvisit.getPatAdmissionDoctorsId());
		}
		
		//PV1-18.1:patient_CLASS
		if(patientvisit.getPatientClass()!=null)
		{
			fpatientvisitlist.setPatientClass(patientvisit.getPatientClass());
		}
		
		//PV1-19.1:Patient_ID
		if(patientvisit.getVisitFlowId()!=null)
		{
			fpatientvisitlist.setVisitFlowId(patientvisit.getVisitFlowId());
		}
		
		//PV1-20.1:PAT_FINANCIAL_CLASS
		if(patientvisit.getPatFinancialClass()!=null)
		{
			fpatientvisitlist.setPatFinancialClass(patientvisit.getPatFinancialClass());
		}
		
		if(patientvisit.getCustom2()!=null)
		{
			fpatientvisitlist.setCustom2(patientvisit.getCustom2());
		}
		
		if(patientvisit.getCustom3()!=null)
		{
			fpatientvisitlist.setCustom3(patientvisit.getCustom3());
		}
		
		if(patientvisit.getCustom4()!=null)
		{
			fpatientvisitlist.setCustom4(patientvisit.getCustom4());
		}
		
		//PV1-21.1:ROOM_BED_COST_PRICE
		if(patientvisit.getRoomBedCostPrice()!=null)
		{
			fpatientvisitlist.setRoomBedCostPrice(patientvisit.getRoomBedCostPrice());
		}
		
		//PV1-22.1:COURTESY_CODE
		if(patientvisit.getCourtesyCode()!=null)
		{
			fpatientvisitlist.setCourtesyCode(patientvisit.getCourtesyCode());
		}
		
		//PV1-23.1:CREDIT_RATING
		if(patientvisit.getCreditRating()!=null)
		{
			fpatientvisitlist.setCreditRating(patientvisit.getCreditRating());
		}
		
		//PV1-24.1:CONTRACT_CODE
		if(patientvisit.getContractCode()!=null)
		{
			fpatientvisitlist.setContractCode(patientvisit.getContractCode());
		}
		
		//PV1-25.1:CONTRACT_CREATE_DATE
		if(patientvisit.getContractCreateDate()!=null)
		{
			fpatientvisitlist.setContractCreateDate(patientvisit.getContractCreateDate());
		}
		
		//PV1-26.1:CONTRACT_PRICE
		if(patientvisit.getContractPrice()!=null)
		{
			fpatientvisitlist.setContractPrice(patientvisit.getContractPrice());
		}
		
		//PV1-27.1:CONTRACT_TIME
		if(patientvisit.getContractTime()!=null)
		{
			fpatientvisitlist.setContractTime(patientvisit.getContractTime());
		}
		
		//PV1-28.1:INTEREST_RATE_CODE
		if(patientvisit.getInterestRateCode()!=null)
		{
			fpatientvisitlist.setInterestRateCode(patientvisit.getInterestRateCode());
		}
		
		//PV1-29.1:BAD_DEBTS
		if(patientvisit.getBadDebts()!=null)
		{
			fpatientvisitlist.setBadDebts(patientvisit.getBadDebts());
		}
		
		//PV1-30.1:BAD_DEBTS_CREATE_DATE
		if(patientvisit.getBadDebtsCreateDate()!=null)
		{
			fpatientvisitlist.setBadDebtsCreateDate(patientvisit.getBadDebtsCreateDate());
		}
		
		//PV1-31.1:BAD_DEBTS_CODE
		if(patientvisit.getBadDebtsCode()!=null)
		{
			fpatientvisitlist.setBadDebtsCode(patientvisit.getBadDebtsCode());
		}
		
		//PV1-32.1:BAD_DEBTS_PRICE
		if(patientvisit.getBadDebtsPrice()!=null)
		{
			fpatientvisitlist.setBadDebtsPrice(patientvisit.getBadDebtsPrice());
		}
		
		//PV1-33.1:BAD_DEBTS_RESTORE_PRICE
		if(patientvisit.getBadDebtsRestorePrice()!=null)
		{
			fpatientvisitlist.setBadDebtsRestorePrice(patientvisit.getBadDebtsRestorePrice());
		}
		
		//PV1-34.1:PAT_ACCOUNT_VOIDED
		if(patientvisit.getPatAccountVoided()!=null)
		{
			fpatientvisitlist.setPatAccountVoided(patientvisit.getPatAccountVoided());
		}
		
		//PV1-35.1:PAT_ACCOUNT_VOIDED_DATE
		if(patientvisit.getPatAccountVoidedDate()!=null)
		{
			fpatientvisitlist.setPatAccountVoidedDate(patientvisit.getPatAccountVoidedDate());
		}
		
		//PV1-36.1: PAT_DISCHARGE_DISPOSITION
		if(patientvisit.getPatDischargeDisposition()!=null)
		{
			fpatientvisitlist.setPatDischargeDisposition(patientvisit.getPatDischargeDisposition());
		}
		
		//PV1-37.1:PAT_DISCHARGE_LOCATION
		if(patientvisit.getPatDischargeLocation()!=null)
		{
			fpatientvisitlist.setPatDischargeLocation(patientvisit.getPatDischargeLocation());
		}
		
		//PV1-38.1:PAT_DIET_TYPE
		if(patientvisit.getPatDietType()!=null)
		{
			fpatientvisitlist.setPatDietType(patientvisit.getPatDietType());
		}
		
		//PV1-39.1:PAT_SERVICE_AGENCIES
		if(patientvisit.getPatServiceAgencies()!=null)
		{
			fpatientvisitlist.setPatServiceAgencies(patientvisit.getPatServiceAgencies());
		}
		
		//PV1-40.1:PAT_BED_STATUS
		if(patientvisit.getPatBedStatus()!=null)
		{
			fpatientvisitlist.setPatBedStatus(patientvisit.getPatBedStatus());
		}
		
		//PV1-41.1:PAT_ACCOUNT_STATUS
		if(patientvisit.getPatAccountStatus()!=null)
		{
			fpatientvisitlist.setPatAccountStatus(patientvisit.getPatAccountStatus());
		}
		
		//PV1-42.1:PAT_DETER_POINT_OF_CARE
		if(patientvisit.getMedicinelimitamount()!=null)
		{
			fpatientvisitlist.setMedicinelimitamount(patientvisit.getMedicinelimitamount());
		}
		
		if(patientvisit.getSickbedlimitamount()!=null)
		{
			fpatientvisitlist.setSickbedlimitamount(patientvisit.getSickbedlimitamount());
		}
		
		if(patientvisit.getExaminelimitamount()!=null)
		{
			fpatientvisitlist.setExaminelimitamount(patientvisit.getExaminelimitamount());
		}
		
		if(patientvisit.getCurelimitamount()!=null)
		{
			fpatientvisitlist.setCurelimitamount(patientvisit.getCurelimitamount());
		}
		
		//PV1-42.2:PAT_DETER_ROOM
		if(patientvisit.getPatDeterRoom()!=null)
		{
			fpatientvisitlist.setPatDeterRoom(patientvisit.getPatDeterRoom());
		}
		
		//PV1-42.3:PAT_DETER_BED
		if(patientvisit.getPatDeterBed()!=null)
		{
			fpatientvisitlist.setPatDeterBed(patientvisit.getPatDeterBed());
		}
		
		//PV1-42.4:PAT_DETER_DEP
		if(patientvisit.getPatDeterDep()!=null)
		{
			fpatientvisitlist.setPatDeterDep(patientvisit.getPatDeterDep());
		}
		
		//PV1-42.5:PAT_DETER_POSITION_STATUS
		if(patientvisit.getTend()!=null)
		{
			fpatientvisitlist.setTend(patientvisit.getTend());
		}
		
		//PV1-42.6:PAT_DETER_POSITION_TYPE
		if(patientvisit.getPatNurseCode()!=null)
		{
			fpatientvisitlist.setPatNurseCode(patientvisit.getPatNurseCode());
		}
		
		//PV1-42.7:PAT_DETER_BUILDING
		if(patientvisit.getPatNurseName()!=null)
		{
			fpatientvisitlist.setPatNurseName(patientvisit.getPatNurseName());
		}
		
		//PV1-42.8:PAT_DETER_FLOOR
		if(patientvisit.getOperCode()!=null)
		{
			fpatientvisitlist.setOperCode(patientvisit.getOperCode());
		}
		
		//PV1-42.9:PAT_DETER_DESCRIPTION
		if(patientvisit.getOperDate()!=null)
		{
			fpatientvisitlist.setOperDate(patientvisit.getOperDate());
		}
		
		//PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
		if(patientvisit.getPatForTempPointOfCare()!=null)
		{
			fpatientvisitlist.setPatForTempPointOfCare(patientvisit.getPatForTempPointOfCare());
		}
		
		//PV1-43.2:PAT_FOR_TEMP_ROOM
		if(patientvisit.getPatForTempRoom()!=null)
		{
			fpatientvisitlist.setPatForTempRoom(patientvisit.getPatForTempRoom());
		}
		
		//PV1-43.3:pat_for_temp_bed
		if(patientvisit.getPatForTempBed()!=null)
		{
			fpatientvisitlist.setPatForTempBed(patientvisit.getPatForTempBed());
		}
		
		//PV1-43.4:PAT_FOR_TEMP_DEP
		if(patientvisit.getPatForTempDep()!=null)
		{
			fpatientvisitlist.setPatForTempDep(patientvisit.getPatForTempDep());
		}
		
		//PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
		if(patientvisit.getPatForTempPositionStatus()!=null)
		{
			fpatientvisitlist.setPatForTempPositionStatus(patientvisit.getPatForTempPositionStatus());
		}
		
		//PV1-43.6:PAT_FOR_TEMP_POSITION_TYPE
		if(patientvisit.getPatForTempPositionType()!=null)
		{
			fpatientvisitlist.setPatForTempPositionType(patientvisit.getPatForTempPositionType());
		}
		
		//PV1-43.7:PAT_FOR_TEMP_BUILDING
		if(patientvisit.getPatForTempBuilding()!=null)
		{
			fpatientvisitlist.setPatForTempBuilding(patientvisit.getPatForTempBuilding());
		}
		
		//PV1-43.8:PAT_FOR_TEMP_FLOOR
		if(patientvisit.getPatForTempFloor()!=null)
		{
			fpatientvisitlist.setPatForTempFloor(patientvisit.getPatForTempFloor());
		}
		
		//PV1-43.9:PAT_FOR_TEMP_DESCRIPTION
		if(patientvisit.getPatTempDescription()!=null)
		{
			fpatientvisitlist.setPatForTempDescription(patientvisit.getPatFormerDescription());
		}
		
		//PV1-44.1:ADMIT_DATE
		if(patientvisit.getAdmitDate()!=null)
		{
			fpatientvisitlist.setAdmitDate(patientvisit.getAdmitDate());
		}
		
		//PV1-45.1:DISCHARGE_DATE
		if(patientvisit.getDischargeDate()!=null)
		{
			fpatientvisitlist.setDischargeDate(patientvisit.getDischargeDate());
		}
		
		//PV1-46.1:PAT_DIFFERENCE
		if(patientvisit.getPatDifference()!=null)
		{
			fpatientvisitlist.setPatDifference(patientvisit.getPatDifference());
		}
		
		//PV1-47.1:PAT_TOTAL_COST
		if(patientvisit.getPatTotalCost()!=null)
		{
			fpatientvisitlist.setPatTotalCost(patientvisit.getPatTotalCost());
		}
		
		//PV1-48.1:PAT_TOTAL_DISPATCH
		if(patientvisit.getPatTotalDispatch()!=null)
		{
			fpatientvisitlist.setPatTotalDispatch(patientvisit.getPatTotalDispatch());
		}
		
		//PV1-49.1:PAT_TOTAL_AMOUNT_PAYABLE
		if(patientvisit.getPatTotalAmountPayable()!=null)
		{
			fpatientvisitlist.setPatTotalAmountPayable(patientvisit.getPatTotalAmountPayable());
		}
		
		//PV1-50.1:PAT_SPARE_ID
		if(patientvisit.getBabyFlag()!=null)
		{
			fpatientvisitlist.setBabyFlag(patientvisit.getBabyFlag());
		}
		
		if(patientvisit.getAdmitWeight()!=null)
		{
			fpatientvisitlist.setAdmitWeight(patientvisit.getAdmitWeight());
		}
		
		if(patientvisit.getAdmitWeightUnit()!=null)
		{
			fpatientvisitlist.setAdmitWeightUnit(patientvisit.getAdmitWeightUnit());
		}
		
		if(patientvisit.getBirthWeight()!=null)
		{
			fpatientvisitlist.setBirthWeight(patientvisit.getBirthWeight());
		}
		
		if(patientvisit.getBabyWeightUnit()!=null)
		{
			fpatientvisitlist.setBabyWeightUnit(patientvisit.getBabyWeightUnit());
		}
		//PV1-51.1:PAT_VISIT_LOGO
		if(patientvisit.getPatVisitLogo()!=null)
		{
			fpatientvisitlist.setPatVisitLogo(patientvisit.getPatVisitLogo());
		}
		
		//PV1-52.1:OTHER_MEDICAL_INSTITUTIONS
		if(patientvisit.getOtherMedicalInstitutions()!=null)
		{
			fpatientvisitlist.setOtherMedicalInstitutions(patientvisit.getOtherMedicalInstitutions());
		}
		mypatientvisit.add(fpatientvisitlist);
		}
		patient.setFpatientvisitlist(mypatientvisit);
		//log.trace("Converted object: " + patientvisit + " to " + patient);
		
		return patient;
	}
	/**
	 * @函数作用
	 * 
	 * 用于创建或更新病人信息时,根据输入的patient类信息 转换成和数据库对应的person实体类
	 * 
	 * @author yrh 2012-04-09/2013-05-23
	 * 
	 * @param patient
	 * 
	 */
	public static Person getPerson(Patient patient) 
	{
		Person person = new Person();
		
		//获取得到的病人姓、名、前缀、后缀、学位、姓名类别信息填充入对应的person实体类
		populatePersonName(person, patient.getPatientName());
		

//		//根据获取得到的姓、名，组成姓名，和对应的拼音码、五笔码信息填充入对应的person实体类
//		if(patient.getSickName()!=null)
//		{
//			person.setName(patient.getPatientName().getLastName());
//
//			converttopin finder = converttopin.getInstance();
//
//		    //person.setNameCode(finder.getSelling(patient.getPatientName().getLastName()));
//
//		    //person.setNameCode2("五笔");
//		}
		if (StringUtils.isNotEmpty(person.getGivenName()))
			person.setName(person.getGivenName());

		//获取得到的生日信息填充入对应的person实体类
		if(patient.getBirthDateTime()!=null)
		{
			person.setDateOfBirth(patient.getBirthDateTime().getTime());
		}
		
		//出生地所在地的省
		if(patient.getBirthProvince()!=null)
		{
			person.setBirthProvince(patient.getBirthProvince());
		}
		
		//出生地所在地的市
		if(patient.getBirthCity()!=null)
		{
			person.setBirthCity(patient.getBirthCity());
		}
		
		//出生地所在区县
		if(patient.getBirthCounty()!=null)
		{
			person.setBirthCounty(patient.getBirthCounty());
		}
		
		//获取得到的出生地信息填充入对应的person实体类
		if(patient.getBirthPlace()!=null)
		{
			person.setBirthPlace(patient.getBirthPlace());
		}
		
		//出生地所在地邮编
		if(patient.getBirthZip()!=null)
		{
			person.setBirthZip(patient.getBirthZip());
		}
		
		//获取多胞胎标志
		if(patient.getMulitpleBirthIndicator()!=null)
		{
			person.setMultipleBirthInd(patient.getMulitpleBirthIndicator());
		}
		
		//获取出生顺序
		if(String.valueOf(patient.getBirthOrder())!=null)
		{
			person.setBirthOrder(patient.getBirthOrder());
		}
		
		//根据获取得到的母氏姓填充入对应的person实体类
		if (patient.getMothersMaidenName() != null) 
		{
			person.setMothersMaidenName(patient.getMothersMaidenName().getLastName());
		}
		
		//获取得到的社保信息填充入对应的person实体类
		if(patient.getSsn()!=null)
		{
			person.setSsn(patient.getSsn());
			
			person.setCustom17(patient.getSsn());
		}
				
		//获取身份证件号信息填入
		if(patient.getIdentityNO()!=null)
		{
			person.setIdentityNo(patient.getIdentityNO());
		}
		 
		//市民卡号
		if(patient.getCitizenCard()!=null)
		{
			person.setCitizenCard(patient.getCitizenCard());
		}
		
		//医疗证号
		if(patient.getMedicalCertificate()!=null)
		{
			person.setMedicalCertificate(patient.getMedicalCertificate());
		}
		
		//医保个人编号
		if(patient.getMeicarePerson()!=null)
		{
			person.setMeicarePerson(patient.getMeicarePerson());
		}
		
		//老人证号
		if(patient.getElderCertificate()!=null)
		{
			person.setElderCertificate(patient.getElderCertificate());
		}
		
		//病历号
		if(patient.getOpcaseno()!=null)
		{
			person.setOpcaseno(patient.getOpcaseno());
		}
		
		//健康卡号
		if(patient.getHealthCard()!=null)
		{
			person.setHealthCard(patient.getHealthCard());
		}
		
		//获取医保号信息填入
		if(patient.getInsuranceNO()!=null)
		{
			person.setInsuranceNo(patient.getInsuranceNO());
		}
		
		//医保类型
		if(patient.getInsuranceType()!=null)
		{
			person.setInsuranceType(patient.getInsuranceType());
		}
		
		//医保类型名称
		if(patient.getInsuranceName()!=null)
		{
			person.setInsuranceName(patient.getInsuranceName());
		}
		
		//健康卡号
		if(patient.getHealthCard()!=null)
		{
			person.setHealthCard(patient.getHealthCard());
		}
		
		//获取得到的性别信息填充入对应的person实体类
		if (patient.getAdministrativeSex() != null) 
		{
			GenderDict gender = new GenderDict();
			
			gender.setGenderCode(patient.getAdministrativeSex().getCDAValue());
			
			person.setGenderDict(gender);
		}
		
		//性别名称
		if(patient.getGenderName()!=null)
		{
			person.setGenderName(patient.getGenderName());
		}
		
		//性别编码系统
		if(patient.getGenderDomain()!=null)
		{
			person.setGenderDomain(patient.getGenderDomain());
		}
		
		//获取得到的民族信息填充入对应的person实体类
		if (patient.getEthnicGroup() != null) 
		{
			EthnicgroupDict ethnicGroup = new EthnicgroupDict();
			
			ethnicGroup.setEthnicGroupCode(patient.getEthnicGroup());
			
			person.setEthnicgroupDict(ethnicGroup);
		}
		
		//民族名称
		if(patient.getEthnicName()!=null)
		{
			person.setEthnicName(patient.getEthnicName());
		}
		
		//民族编码系统
		if(patient.getEthincDomain()!=null)
		{
			person.setEthincDomain(patient.getEthincDomain());
		}
		
		//获取得到的种族信息填充入对应的person实体类
		if (patient.getRaceDict() != null) 
		{
			RaceDict race = new RaceDict();
			
			race.setRaceCode(patient.getRaceDict());
			
			person.setRaceDict(race);
		}
		
		//种族民称
		if(patient.getRaceName()!=null)
		{
			person.setRaceName(patient.getRaceName());
		}
		
		//种族编码系统
		 if (patient.getRaceDomain()!=null)
		 {
			 person.setRaceDomain(patient.getRaceDomain());
		 }
		 
		//获取得到的国籍信息填充入对应的person实体类
		if (patient.getNationality() != null) 
		{
			NationalityDict nationality = new NationalityDict();
			
			nationality.setAtionalityCode(patient.getNationality());
			
			person.setNationalityDict(nationality);
		}
		
		//国籍名
		if(patient.getNationalityName()!=null)
		{
			person.setNationalityName(patient.getNationalityName());
		}
		
		//国籍编码系统
		if(patient.getNationalityDomain()!=null)
		{
			person.setNationalityDomain(patient.getNationalityDomain());
		}
		
		//获取得到的语言信息填充如对应的person实体类
		if (patient.getPrimaryLanguage() != null) 
		{
			LanguageDict language = new LanguageDict();
			
			language.setLanguageCode(patient.getPrimaryLanguage());
			
			person.setLanguageDict(language);
		}
		
		
		//获取得到的宗教信息填充入对应的person实体类
		if (patient.getReligion() != null) 
		{
			ReligionDict religion = new ReligionDict();
			
			religion.setReligionCode(patient.getReligion());
			
			person.setReligionDict(religion);
		}
		
		
		//获取得到的婚姻状态信息填充如对应 person实体类
		if (patient.getMaritalStatus() != null) 
		{
			MaritalStatusDict maritalStatusDict=new MaritalStatusDict(); 
			
			maritalStatusDict.setMaritalStatusCode(patient.getMaritalStatus());
			
			person.setMaritalStatusDict(maritalStatusDict);
		}
		
		//婚姻名称
		if (patient.getMaritalStatusName()!=null)
		{
			person.setMaritalStatusName(patient.getMaritalStatusName());
		}
		
		
		//婚姻编码系统
		if(patient.getMaritalDomain()!=null)
		{
			person.setMaritalDomain(patient.getMaritalDomain());
		}
		
		//邮件地址
		if(patient.getEmail()!=null)
		{
			person.setEmail(patient.getEmail());
		}
		
		//居住地所在地省
		if(patient.getHomeProvince()!=null)
		{
			person.setHomeProvince(patient.getHomeProvince());
		}
		
		//居住地所在市
		if(patient.getHomeCity()!=null)
		{
			person.setHomeCity(patient.getHomeCity());
		}
		
		//居住地所在地区县
		if(patient.getHomeCounty()!=null)
		{
			person.setHomeCounty(patient.getHomeCounty());
		}
		
		//居住地所在地邮编
		if(patient.getHomeZip()!=null)
		{
			person.setHomeZip(patient.getHomeZip());
		}
		
		//居住地址
		if(patient.getHomeAddress()!=null)
		{
			person.setHomeAddress(patient.getHomeAddress());
		}
		
		//居住街道
		if(patient.getHomeStreet()!=null)
		{
			person.setHomeStreet(patient.getHomeStreet());
		}
		
		//户口所在地省
		if(patient.getRegisteredProvince()!=null)
		{
			person.setRegisteredProvince(patient.getRegisteredProvince());
		}
		
		//户口所在地市
		if(patient.getRegisteredCity()!=null)
		{
			person.setRegisteredCity(patient.getRegisteredCity());
		}
		
		//户口所在地区县
		if(patient.getRegisteredCounty()!=null)
		{
			person.setRegisteredCounty(patient.getRegisteredCounty());
		}
		
		//户口所在地邮编
		if(patient.getRegisteredZip()!=null)
		{
			person.setRegisteredZip(patient.getRegisteredZip());
		}
		
		//户口地址
		if(patient.getRegisteredAddress()!=null)
		{
			person.setRegisteredAddress(patient.getRegisteredAddress());
		}
		
		//户口街道
		if(patient.getRegisteredStreet()!=null)
		{
			person.setRegisteredStreet(patient.getRegisteredStreet());
		}
		//籍贯所在地省
		if(patient.getNativeProvince()!=null)
		{
			person.setNativeProvince(patient.getNativeProvince());
		}
		
		//籍贯所在地市
		if(patient.getNativeCity()!=null)
		{
			person.setNativeCity(patient.getNativeCity());
		}
		
		//职业编码
		if(patient.getProfession()!=null)
		{
			ProfessionTypeDict professionTypeDict=new ProfessionTypeDict(); 
			
			professionTypeDict.setProfessionTypeCode(patient.getProfession());
			
			person.setProfessionTypeDict(professionTypeDict);
		}
		
		//职业编码名称
		if(patient.getProfessionName()!=null)
		{
			person.setProfessionName(patient.getProfessionName());
		}
		
		//职业编码系统
		if(patient.getProfessionDomain()!=null)
		{
			person.setProfessionDomain(patient.getProfessionDomain());
		}
		
		//工作单位
		 if (patient.getCompany()!=null)
		 {
			 person.setCompany(patient.getCompany());
		 }
		 
		 //工作邮编
		 if(patient.getWorkZip()!=null)
		 {
			 person.setWorkZip(patient.getWorkZip());
		 }
		 
		 //单位地址
		 if(patient.getWorkAddress()!=null)
		 {
			 person.setWorkAddress(patient.getWorkAddress());
		 }
		 
		 //私人电话
		 if(patient.getPrivateNumber()!=null)
		 {
			 person.setPrivateNumber(patient.getPrivateNumber());
		 }
		 
		 //家庭电话
		  if(patient.getHomePhone()!=null)
		  {
			  person.setHomeNumber(patient.getHomePhone());
		  }
		  
		  //工作电话
		  if(patient.getWorkNumber()!=null)
		  {
			  person.setWorkNumber(patient.getWorkNumber());
		  }
		  
		  //监护人
		  if(patient.getGuardianPerson()!=null)
		  {
			  person.setGuardianPerson(patient.getGuardianPerson());
		  }
		  
		  //保密级别
		  if(patient.getVip()!=null)
		  {
			  person.setVip(patient.getVip());
		  }
		  
		  
		//获取得到的死亡标志信息填充入对应的person实体类
		if (patient.getDeathIndicator()!= null) 
		{
			person.setDeathInd(patient.getDeathIndicator());
		}
		
		
		//根据获取得到的病人ID,机构信息填充入对应的person实体类
		if(patient.getPatientIds().size()>0)
		{
			person.setCustom16(patient.getPatientIds().get(0).getId());
			
			//yrh-2012-04-09 方便内部查询,PERSON表增加机构信息
			person.setCustom10(patient.getPatientIds().get(0).getAssigningAuthority().getNamespaceId());
			
			person.setCustom11(patient.getPatientIds().get(0).getAssigningAuthority().getUniversalId());
			
			person.setCustom12(patient.getPatientIds().get(0).getAssigningAuthority().getUniversalIdType());
		}
		
		
		
		//根据获取得到的身份信息填充入对应的person实体类
		for (PatientIdentifier pid : patient.getPatientIds()) 
		{
			person.addPersonIdentifier(getPersonIdentifier(pid));
		}
		
		//根据获取得到的身份识别信息填充入person实体类
		if (patient.getCustom6()!=null)
		{
			person.setCustom6(patient.getCustom6());
		}
		
		if (patient.getCustom7()!=null)
		{
			person.setCustom7(patient.getCustom7());
		}
		
		if (patient.getCustom8()!=null)
		{
			person.setCustom8(patient.getCustom8());
		}
		
		if (patient.getCustom9()!=null)
		{
			person.setCustom9(patient.getCustom9());
		}
		
		if (patient.getCustom13()!=null)
		{
			person.setCustom13(patient.getCustom13());
		}
		
		if (patient.getCustom14()!=null)
		{
			person.setCustom14(patient.getCustom14());
		}
		
		if (patient.getCustom15()!=null)
		{
			person.setCustom15(patient.getCustom15());
		}
		
		if (patient.getCustom18()!=null)
		{
			person.setCustom18(patient.getCustom18());
		}
		
		if (patient.getCustom19()!=null)
		{
			person.setCustom19(patient.getCustom19());
		}
		
		if (patient.getCustom20()!=null)
		{
			person.setCustom20(patient.getCustom20());
		}
		
		if (patient.getCustom21()!=null)
		{
			person.setCustom21(patient.getCustom21());
		}
		
		if (patient.getCustom22()!=null)
		{
			person.setCustom22(patient.getCustom22());
		}
		
		if (patient.getCustom23()!=null)
		{
			person.setCustom23(patient.getCustom23());
		}
		
		if (patient.getCustom24()!=null)
		{
			person.setCustom24(patient.getCustom24());
		}
		
		if (patient.getCustom25()!=null)
		{
			person.setCustom25(patient.getCustom25());
		}
		
		if(patient.getAccountLocked()!=null)
		{
			person.setAccountLocked(patient.getAccountLocked());
		}
		
		if(patient.getAccountLockedDate()!=null)
		{
			person.setAccountLockedDate(patient.getAccountLockedDate());
		}
		
		if(patient.getBirthTime()!=null)
		{
			person.setBirthTime(patient.getBirthTime());
		}
		
		if(patient.getCardType()!=null)
		{
			person.setCardType(patient.getCardType());
		}
		
		log.trace("Converted object: " + patient + " to " + person);
		
		return person;
	}
	
	//NK1-信息填充
	//PM-2012-11-30
	public static List<Contactperson> getConatactPerson(Patient patient) 
	{
		List<Contactperson> contactperson = new ArrayList<Contactperson>();
		
		List<NextOfKin> tempkin = new ArrayList<NextOfKin>(0);
		
		tempkin=patient.getNextOfKin();
		
		for(NextOfKin nok:tempkin)
		{
			Contactperson TempContactperson = new Contactperson();
			
			if(nok.getNextOfKinName()!=null)
			{
				TempContactperson.setContactName(nok.getNextOfKinName().toString());
			}
			
			if(nok.getNextOfKinRelationship()!=null)
			{
				TempContactperson.setContactType(nok.getNextOfKinRelationship().toString());
			}
			
			if(nok.getAddresses().size()>0)
			{
				TempContactperson.setContactAddress(nok.getAddresses().get(0).getAddLine1());
			}
			
			if(nok.getPhoneNumbers().size()>0)
			{
				if(nok.getPhoneNumbers().size()==1)
				{
					TempContactperson.setContactPhone(nok.getPhoneNumbers().get(0).getRawValue());
				}
				else if(nok.getPhoneNumbers().size()==2)
				{
					TempContactperson.setContactPhone(nok.getPhoneNumbers().get(0).getRawValue().toString());
					
					TempContactperson.setContactMobile(nok.getPhoneNumbers().get(1).getRawValue().toString());
				}
			}
			
			contactperson.add(TempContactperson);
		}
		return contactperson;
	
	}
	
	//PV1-信息填充
	//PM-2012-09-19
	public static PatientVisit getPatientVisit(Patient patient) 
	{
		PatientVisit mypatientvisit=new PatientVisit();
		
		//PV1-2.1:PAT_CATEGORY患者类别编码
		if(patient.getPatCategory()!=null)
		{
			mypatientvisit.setPatCategory(patient.getPatCategory());
		}
		
		//PV1-3.1:PAT_CURRENT_POINT_OF_CARE
		if(patient.getPatCurrentPointOfCare()!=null)
		{
			mypatientvisit.setPatCurrentPointOfCare(patient.getPatCurrentPointOfCare());
		}
		
		//PV1-3.2:PAT_CURRENT_ROOM
		if(patient.getPatCurrentRoom()!=null)
		{
			mypatientvisit.setPatCurrentRoom(patient.getPatCurrentRoom());
		}
		
		//PV1-3.3:PAT_CURRENT_BED
		if(patient.getPatCurrentBed()!=null)
		{
			mypatientvisit.setPatCurrentBed(patient.getPatCurrentBed());
		}
		
		
		//PV1-3.4:PAT_CURRENT_DEP
		if(patient.getPatCuurentDep()!=null)
		{
			mypatientvisit.setPatCuurentDep(patient.getPatCuurentDep());
		}
		
		//PV1-3.5:PAT_CURRENT_POSITION_STATUS
		if(patient.getPatCurrentPositionStatus()!=null)
		{
			mypatientvisit.setPatCurrentPositionStatus(patient.getPatCurrentPositionStatus());
		}
		
		//PV1-3.6:PAT_CURRENT_POSITION_TYPE
		if(patient.getPatCurrentPositionType()!=null)
		{
			mypatientvisit.setPatCurrentPositionType(patient.getPatCurrentPositionType());
		}
		
		//pv1-3.7:PAT_CURRENT_BUILDING
		if(patient.getPatCurrentBuilding()!=null)
		{
			mypatientvisit.setPatCurrentBuilding(patient.getPatCurrentBuilding());
		}
		
		//PV1-3.8:PAT_CURRENT_FLOOR
		if(patient.getPatCurrentFloor()!=null)
		{
			mypatientvisit.setPatCurrentFloor(patient.getPatCurrentFloor());
		}
		
		//PV1-3.9:PAT_CURRENT_DESCRIPTION
		if(patient.getPatCuurentDescription()!=null)
		{
			mypatientvisit.setPatCuurentDescription(patient.getPatCuurentDescription());
		}
		
		//PV1-4.1:PAT_ADMISSION_TYPE
		if(patient.getPatAdmissionType()!=null)
		{
			mypatientvisit.setPatAdmissionType(patient.getPatAdmissionType());
		}
		
		//PV1-5.1:PAT_ADMISSION_NUMBER
		if(patient.getPatAdmissionNumber()!=null)
		{
			mypatientvisit.setPatAdmissionNumber(patient.getPatAdmissionNumber());
		}
		
		//PV1-6.1:PAT_FORMER_POINT_OF_CARE
		if(patient.getPatFormerPointOfCare()!=null)
		{
			mypatientvisit.setPatFormerPointOfCare(patient.getPatFormerPointOfCare());
		}
		
		//PV1-6.2:PAT_FORMER_ROOM
		if(patient.getPatFormerRoom()!=null)
		{
			mypatientvisit.setPatFormerRoom(patient.getPatFormerRoom());
		}
		
		//PV1-6.3:PAT_FORMBER_BED
		if(patient.getPatFormerBed()!=null)
		{
			mypatientvisit.setPatFormerBed(patient.getPatFormerBed());
		}
		
		//PV1-6.4:PAT_FORMER_DEP
		if(patient.getPatFormerDep()!=null)
		{
			mypatientvisit.setPatFormerDep(patient.getPatFormerDep());
		}
		
		//PV1-6.5:PAT_FORMER_POSITION_STATUS
		if(patient.getPatFormerPositionStatus()!=null)
		{
			mypatientvisit.setPatFormerPositionStatus(patient.getPatFormerPositionStatus());
		}
		
		//PV1-6.6:PAT_FORER_POSITION_TYPE
		if(patient.getPatFormerPositionType()!=null)
		{
			mypatientvisit.setPatForTempPositionType(patient.getPatFormerPositionType());
		}
		
		//PV1-6.7:PAT_FORMER_BUILDING
		if(patient.getPatFormerBuilding()!=null)
		{
			mypatientvisit.setPatFormerBuilding(patient.getPatFormerBuilding());
		}
		
		//PV1-6.8:PAT_FORMER_FLOOR
		if(patient.getPatFormerFloor()!=null)
		{
			mypatientvisit.setPatFormerFloor(patient.getPatFormerFloor());
		}
		
		//PV1-6.9:PAT_FORMER_DESCRIPTION
		if(patient.getPatFormerDescription()!=null)
		{
			mypatientvisit.setPatFormerDescription(patient.getPatFormerDescription());
		}
		
		//PV1-7.2:ADMISSIONS_DOCTOR
		if(patient.getAdmissionsDoctor()!=null)
		{
			mypatientvisit.setAdmissionsDoctor(patient.getAdmissionsDoctor());
		}
		
		//PV1-7.1:ADMISSIONS_DOCTOR_ID
		if(patient.getAdmissionsDoctorId()!=null)
		{
			mypatientvisit.setAdmissionsDoctorId(patient.getAdmissionsDoctorId());
		}
		
		//PV1-8.1:REFERRING_DOCTOR_ID
		if(patient.getReferringDoctorId()!=null)
		{
			mypatientvisit.setReferringDoctorId(patient.getReferringDoctorId());
		}
		
		//PV1-8.2:REFERRING_DOCTOR
		if(patient.getReferringDoctor()!=null)
		{
			mypatientvisit.setReferringDoctor(patient.getReferringDoctor());
		}
		
		//PV1-9.1:CONSULTATION_DOCTOR_ID
		if(patient.getConsultationDoctorId()!=null)
		{
			mypatientvisit.setConsultationDoctorId(patient.getConsultationDoctorId());
		}
		
		//PV1-9.2:CONSULTATION_DOCTOR
		if(patient.getConsultationDoctor()!=null)
		{
			mypatientvisit.setConsultationDoctor(patient.getConsultationDoctor());
		}
		
		//PV1-10.1:HOSPITAL_SERVICE
		if(patient.getHospitalService()!=null)
		{
			mypatientvisit.setHospitalService(patient.getHospitalService());
		}
		
		//PV1-11.1:PAT_TEMP_POINT_OF_CARE
		if(patient.getPatTempPointOfCare()!=null)
		{
			mypatientvisit.setPatTempPointOfCare(patient.getPatTempPointOfCare());
		}
		
		//PV1-11.2:PAT_TEMP_ROOM
		if(patient.getPatTempRoom()!=null)
		{
			mypatientvisit.setPatTempRoom(patient.getPatTempRoom());
		}
		
		//PV1-11.3:PAT_TEMP_BED
		if(patient.getPatTempBed()!=null)
		{
			mypatientvisit.setPatTempBed(patient.getPatTempBed());
		}
		
		//PV1-11.4:PAT_TEMP_DEP
		if(patient.getPatTempDep()!=null)
		{
			mypatientvisit.setPatTempDep(patient.getPatTempDep());
		}
		
		//PV1-11.5:PAT_TEMP_POSITION_STATUS
		if(patient.getPatTempPositionStatus()!=null)
		{
			mypatientvisit.setPatTempPositionStatus(patient.getPatTempPositionStatus());
		}
		
		
		//PV1-11.6:PAT_TEMP_POSITION_TYPE
		if(patient.getPatTempPositionType()!=null)
		{
			mypatientvisit.setPatTempPositionType(patient.getPatTempPositionType());
		}
		
		//PV1-11.7:PAT_TEMP_BUILDING
		if(patient.getPatTempBuilding()!=null)
		{
			mypatientvisit.setPatTempBuilding(patient.getPatTempBuilding());
		}
		
		//PV1-11.8:PAT_TEMP_FLOOR
		if(patient.getPatTempFloor()!=null)
		{
			mypatientvisit.setPatTempFloor(patient.getPatTempFloor());
		}
		
		//PV1-11.9:PAT_TEMP_DESCRIPTION
		if(patient.getPatTempDescription()!=null)
		{
			mypatientvisit.setPatTempDescription(patient.getPatTempDescription());
		}
		
		//PV1-12.1:PAT_ADMISSION_TEST
		if(patient.getPatAdmissionTest()!=null)
		{
			mypatientvisit.setPatAdmissionTest(patient.getPatAdmissionTest());
		}
		
		//pv1-13.1:PAT_RE_ADMISSION
		if(patient.getPatIpTimes()!=null)
		{
			mypatientvisit.setPatIpTimes(patient.getPatIpTimes());
		}
		
		//PV1-14.1:PAT_ADMISSION_SOURCE
		if(patient.getPatAdmissionSource()!=null)
		{
			mypatientvisit.setPatAdmissionSource(patient.getPatAdmissionSource());
		}
		
		//PV1-15.1:PAT_AMBULATORY_STATUS
		if(patient.getDischargeName()!=null)
		{
			mypatientvisit.setDischargeName(patient.getDischargeName());
		}
		
		//离院处置编码系统
		if(patient.getDischargeDomain()!=null)
		{
			mypatientvisit.setDischargeDomain(patient.getDischargeDomain());
		}
		
		//入院时情况名称
		if(patient.getAdmissionName()!=null)
		{
			mypatientvisit.setAdmissionName(patient.getAdmissionName());
		}
		
		//入院时情况编码系统
		if(patient.getAdmissionDomain()!=null)
		{
			mypatientvisit.setAdmissionDomain(patient.getAdmissionDomain());
		}
		
		//病人住院状态名称
		if(patient.getIpStatusName()!=null)
		{
			mypatientvisit.setIpStatusName(patient.getIpStatusName());
		}
		
		//病人住院状态编码系统
		if(patient.getIpStatusDomain()!=null)
		{
			mypatientvisit.setIpStatusDomain(patient.getIpStatusDomain());
		}
		
		//病例分型名称
		if(patient.getDificultyName()!=null)
		{
			mypatientvisit.setDificultyName(patient.getDificultyName());
		}
		
		//病例分型编码系统
		if(patient.getDificultyDomain()!=null)
		{
			mypatientvisit.setDificultyDomain(patient.getDificultyDomain());
		}
		
		//入院途径名称
		if(patient.getAdmissionSourceName()!=null)
		{
			mypatientvisit.setAdmissionSourceName(patient.getAdmissionSourceName());
		}
		
		//入院途径编码系统
		if(patient.getAdmissionSourceDomain()!=null)
		{
			mypatientvisit.setAdmissionSourceDomain(patient.getAdmissionSourceDomain());
		}
		
		//支付方式名称
		if(patient.getAccountStatusName()!=null)
		{
			mypatientvisit.setAccountStatusName(patient.getAccountStatusName());
		}
		
		//支付方式编码系统
		if(patient.getAccountStatusDomain()!=null)
		{
			mypatientvisit.setAccountStatusDomain(patient.getAccountStatusDomain());
		}
		
		//患者类别编码名称
		if(patient.getPatCategoryName()!=null)
		{
			mypatientvisit.setPatCategoryName(patient.getPatCategoryName());
		}
		
		//患者类别编码系统
		if(patient.getPatCategorySystem()!=null)
		{
			mypatientvisit.setPatCategorySystem(patient.getPatCategorySystem());
		}
		
		//患者类型编码
		if(patient.getPatientClassName()!=null)
		{
			mypatientvisit.setPatientClassName(patient.getPatientClassName());
		}
		
		//患者类型编码系统
		if(patient.getPatientClassDomain()!=null)
		{
			mypatientvisit.setPatientClassDomain(patient.getPatientClassDomain());
		}
		
		//病人来源
		if(patient.getPatientSourceName()!=null)
		{
			mypatientvisit.setPatientSourceName(patient.getPatientSourceName());
		}
		
		//病人再入院标识
		 if(patient.getPatReAdmission()!=null)
		 {
			 mypatientvisit.setPatReAdmission(patient.getPatReAdmission());
		 }
		 
		 //是否急疹转住院
		 if(patient.getIsEmergency()!=null)
		 {
			 mypatientvisit.setIsEmergency(patient.getIsEmergency());
		 }
		 
		 if(patient.getDiagnoseIcd()!=null)
		 {
			 mypatientvisit.setDiagnoseIcd(patient.getDiagnoseIcd());
		 }
		 
		 if(patient.getDiagnoseName()!=null)
		 {
			 mypatientvisit.setDiagnoseName(patient.getDiagnoseName());
		 }
		 
		
		//PV1-16.1:PAT_VIP
		if(patient.getPatVip()!=null)
		{
			mypatientvisit.setPatVip(patient.getPatVip());
		}
		
		//PV1-17.2:PAT_ADMISSION_DOCTORS
		if(patient.getPatAdmissionDoctors()!=null)
		{
			mypatientvisit.setPatAdmissionDoctors(patient.getPatAdmissionDoctors());
		}
		
		//PV1-17.1:PAT_ADMISSION_DOCTORS_ID
		if(patient.getPatAdmissionDoctorsId()!=null)
		{
			mypatientvisit.setPatAdmissionDoctorsId(patient.getPatAdmissionDoctorsId());
		}
		
		//PV1-18.1:PATIENT_CLASS
		if(patient.getPatientClass()!=null)
		{
			mypatientvisit.setPatientClass(patient.getPatientClass());
		}
		
		//PV1-19.1:PATIENT_ID
		if(patient.getPatientId()!=null)
		{
			mypatientvisit.setPatientId(patient.getPatientId());
		}
		
		if(patient.getCustom2()!=null)
		{
			mypatientvisit.setCustom2(patient.getCustom2());
		}
		
		if(patient.getCustom3()!=null)
		{
			mypatientvisit.setCustom3(patient.getCustom3());
		}
		
		if(patient.getCustom4()!=null)
		{
			mypatientvisit.setCustom4(patient.getCustom4());
		}
		
		//PV1-20.1:PAT_FINANCIAL_CLASS
		if(patient.getPatFinancialClass()!=null)
		{
			mypatientvisit.setPatFinancialClass(patient.getPatFinancialClass());
		}
		
		//PV1-21.1:ROOM_BED_COST_PRICE
		if(patient.getRoomBedCostPrice()!=null)
		{
			mypatientvisit.setRoomBedCostPrice(patient.getRoomBedCostPrice());
		}
		
		//PV1-22.1:COURTESY_CODE
		if(patient.getCourtesyCode()!=null)
		{
			mypatientvisit.setCourtesyCode(patient.getCourtesyCode());
		}
		
		//PV1-23.1:CREDIT_RATING
		if(patient.getCreditRating()!=null)
		{
			mypatientvisit.setCreditRating(patient.getCreditRating());
		}
		
		//PV1-24.1:CONTRACT_CODE
		if(patient.getContractCode()!=null)
		{
			mypatientvisit.setContractCode(patient.getContractCode());
		}
		
		//PV1-25.1:CONTRACT_CREATE_DATE
		if(patient.getContractCreateDate()!=null)
		{
			mypatientvisit.setContractCreateDate(patient.getContractCreateDate());
		}
		
		//PV1-26.1:CONTRACT_PRICE
		if(patient.getContractPrice()!=null)
		{
			mypatientvisit.setContractPrice(patient.getContractPrice());
		}
		
		//PV1-27.1:CONTRACT_TIME
		if(patient.getContractTime()!=null)
		{
			mypatientvisit.setContractTime(patient.getContractTime());
		}
		
		//PV1-28.1:INTEREST_RATE_CODE
		if(patient.getInterestRateCode()!=null)
		{
			mypatientvisit.setInterestRateCode(patient.getInterestRateCode());
			
			mypatientvisit.setPayRate(patient.getInterestRateCode());
		}
		
		//PV1-29.1:BAD_DEBTS
		if(patient.getBadDebts()!=null)
		{
			mypatientvisit.setBadDebts(patient.getBadDebts());
		}
		
		//PV1-30.1:BAD_DEBTS_CREATE_DATE
		if(patient.getBadDebtsCreateDate()!=null)
		{
			mypatientvisit.setBadDebtsCreateDate(patient.getBadDebtsCreateDate());
		}
		
		//PV1-31.1:BAD_DEBTS_CODE
		if(patient.getBadDebtsCode()!=null)
		{
			mypatientvisit.setBadDebtsCode(patient.getBadDebtsCode());
		}
		
		//PV1-32.1:BAD_DEBTS_PRICE
		if(patient.getBadDebtsPrice()!=null)
		{
			mypatientvisit.setBadDebtsPrice(patient.getBadDebtsPrice());
		}
		
		//PV1-33.1:BAD_DEBTS_RESTORE_PRICE
		if(patient.getBadDebtsRestorePrice()!=null)
		{
			mypatientvisit.setBadDebtsRestorePrice(patient.getBadDebtsRestorePrice());
		}
		
		//PV1-34.1:PAT_ACCOUNT_VOIDED
		if(patient.getPatAccountVoided()!=null)
		{
			mypatientvisit.setPatAccountVoided(patient.getPatAccountVoided());
		}
		
		//PV1-35.1:PAT_ACCOUNT_VOIDED_DATE
		if(patient.getPatAccountVoidedDate()!=null)
		{
			mypatientvisit.setPatAccountVoidedDate(patient.getPatAccountVoidedDate());
		}
		
		//PV1-36.1: PAT_DISCHARGE_DISPOSITION
		if(patient.getPatDischargeDisposition()!=null)
		{
			mypatientvisit.setPatDischargeDisposition(patient.getPatDischargeDisposition());
		}
		
		//PV1-37.1:PAT_DISCHARGE_LOCATION
		if(patient.getPatDischargeLocation()!=null)
		{
			mypatientvisit.setPatDischargeLocation(patient.getPatDischargeLocation());
		}
		
		//PV1-38.1:PAT_DIET_TYPE
		if(patient.getPatDietType()!=null)
		{
			mypatientvisit.setPatDietType(patient.getPatDietType());
		}
		
		//PV1-39.1:PAT_SERVICE_AGENCIES
		if(patient.getPatServiceAgencies()!=null)
		{
			mypatientvisit.setPatServiceAgencies(patient.getPatServiceAgencies());
		}
		
		//PV1-40.1:PAT_BED_STATUS
		if(patient.getPatBedStatus()!=null)
		{
			mypatientvisit.setPatBedStatus(patient.getPatBedStatus());
		}
		
		//PV1-41.1:PAT_ACCOUNT_STATUS
		if(patient.getPatAccountStatus()!=null)
		{
			mypatientvisit.setPatAccountStatus(patient.getPatAccountStatus());
		}
		
		//PV1-42.1:PAT_DETER_POINT_OF_CARE
		//药费限额
		if(patient.getMedicinelimitamount()!=null)
		{
			mypatientvisit.setMedicinelimitamount(patient.getMedicinelimitamount());
		}
		
		//床位限额
		 if(patient.getSickbedlimitamount()!=null)
		 {
			 mypatientvisit.setSickbedlimitamount(patient.getSickbedlimitamount());
		 }
		 
		 //检查限额
		 if(patient.getExaminelimitamount()!=null)
		 {
			 mypatientvisit.setExaminelimitamount(patient.getExaminelimitamount());
		 }
		 
		 //治疗限额
		 if(patient.getCurelimitamount()!=null)
		 {
			 mypatientvisit.setCurelimitamount(patient.getCurelimitamount());
		 }
		
		//PV1-42.2:PAT_DETER_ROOM
		if(patient.getPrefix()!=null)
		{
			mypatientvisit.setPrefix(patient.getPrefix());
		}
		
		//PV1-42.3:PAT_DETER_BED
		if(patient.getPatDeterBed()!=null)
		{
			mypatientvisit.setPatDeterBed(patient.getPatDeterBed());
		}
		
		//PV1-42.4:PAT_DETER_DEP
		if(patient.getPatDeterDep()!=null)
		{
			mypatientvisit.setPatDeterDep(patient.getPatDeterDep());
		}
		
		//PV1-42.5:PAT_DETER_POSITION_STATUS
		//护理
		if(patient.getTend()!=null)
		{
			mypatientvisit.setTend(patient.getTend());
		}
		
		//PV1-42.6:PAT_DETER_POSITION_TYPE
		//护士ID
		if(patient.getPatNurseCode()!=null)
		{
			mypatientvisit.setPatNurseCode(patient.getPatNurseCode());
		}
		
		//PV1-42.7:PAT_DETER_BUILDING
		//护士姓名
		if(patient.getPatNurseName()!=null)
		{
			mypatientvisit.setPatNurseName(patient.getPatNurseName());
		}
		
		//PV1-42.8:PAT_DETER_FLOOR
		//入院登记操作
		if(patient.getOperCode()!=null)
		{
			mypatientvisit.setOperCode(patient.getOperCode());
		}
		
		//PV1-42.9:PAT_DETER_DESCRIPTION
		//操作日期
		if(patient.getOperDate()!=null)
		{
			mypatientvisit.setOperDate(patient.getOperDate());
		}
		
		//PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
		if(patient.getPatForTempPointOfCare()!=null)
		{
			mypatientvisit.setPatForTempPointOfCare(patient.getPatForTempPointOfCare());
		}
		
		//PV1-43.2:PAT_FOR_TEMP_ROOM
		if(patient.getPatForTempRoom()!=null)
		{
			mypatientvisit.setPatForTempRoom(patient.getPatForTempRoom());
		}
		
		//PV1-43.3:pat_for_temp_bed
		if(patient.getPatForTempBed()!=null)
		{
			mypatientvisit.setPatForTempBed(patient.getPatForTempBed());
		}
		
		//PV1-43.4:PAT_FOR_TEMP_DEP
		if(patient.getPatForTempDep()!=null)
		{
			mypatientvisit.setPatForTempDep(patient.getPatForTempDep());
		}
		
		//PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
		if(patient.getPatForTempPositionStatus()!=null)
		{
			mypatientvisit.setPatForTempPositionStatus(patient.getPatForTempPositionStatus());
		}
		
		//PV1-43.6:PAT_FOR_TEMP_POSITION_TYPE
		if(patient.getPatForTempPositionType()!=null)
		{
			mypatientvisit.setPatForTempPositionType(patient.getPatForTempPositionType());
		}
		
		//PV1-43.7:PAT_FOR_TEMP_BUILDING
		if(patient.getPatForTempBuilding()!=null)
		{
			mypatientvisit.setPatForTempBuilding(patient.getPatForTempBuilding());
		}
		
		//PV1-43.8:PAT_FOR_TEMP_FLOOR
		if(patient.getPatForTempFloor()!=null)
		{
			mypatientvisit.setPatForTempFloor(patient.getPatForTempFloor());
		}
		
		//PV1-43.9:PAT_FOR_TEMP_DESCRIPTION
		if(patient.getPatTempDescription()!=null)
		{
			mypatientvisit.setPatForTempDescription(patient.getPatFormerDescription());
		}
		
		//PV1-44.1:ADMIT_DATE
		if(patient.getAdmitDate()!=null)
		{
			mypatientvisit.setAdmitDate(patient.getAdmitDate());
		}
		
		//PV1-45.1:DISCHARGE_DATE
		if(patient.getDischargeDate()!=null)
		{
			mypatientvisit.setDischargeDate(patient.getDischargeDate());
		}
		
		//PV1-46.1:PAT_DIFFERENCE
		if(patient.getPatDifference()!=null)
		{
			mypatientvisit.setPatDifference(patient.getPatDifference());
		}
		
		//PV1-47.1:PAT_TOTAL_COST
		if(patient.getPatTotalCost()!=null)
		{
			mypatientvisit.setPatTotalCost(patient.getPatTotalCost());
		}
		
		//PV1-48.1:PAT_TOTAL_DISPATCH
		if(patient.getPatTotalDispatch()!=null)
		{
			mypatientvisit.setPatTotalDispatch(patient.getPatTotalDispatch());
		}
		
		//PV1-49.1:PAT_TOTAL_AMOUNT_PAYABLE
		if(patient.getPatTotalAmountPayable()!=null)
		{
			mypatientvisit.setPatTotalAmountPayable(patient.getPatTotalAmountPayable());
		}
		
		//PV1-50.1:PAT_SPARE_ID
		//婴儿标志
		if(patient.getBabyFlag()!=null)
		{
			mypatientvisit.setBabyFlag(patient.getBabyFlag());
		}
		
		//母亲姓名
		if(patient.getMothersName()!=null)
		{
			mypatientvisit.setMothersName(patient.getMothersName());
		}
		
		//母亲ID
		if(patient.getMothersID()!=null)
		{
			mypatientvisit.setMothersID(patient.getMothersID());
		}
		
		//母亲域
		if(patient.getMothersDomain()!=null)
		{
			mypatientvisit.setMothersDomain(patient.getMothersDomain());
		}
		
		//母亲流水ID
		if(patient.getMothersFlowID()!=null)
		{
			mypatientvisit.setMothersFlowID(patient.getMothersFlowID());
		}
		
		//母亲流水域
		if(patient.getMothersFlowDomain()!=null)
		{
			mypatientvisit.setMothersDomain(patient.getMothersFlowDomain());
		}
		
		//入院体重
		if(patient.getAdmitWeight()!=null)
		{
			mypatientvisit.setAdmitWeight(patient.getAdmitWeight());
		}
		
		//入院体重单位
		if(patient.getAdmitWeightUnit()!=null)
		{
			mypatientvisit.setAdmitWeightUnit(patient.getAdmitWeightUnit());
		}
		
		//出生体重
		if(patient.getBirthWeight()!=null)
		{
			mypatientvisit.setBirthWeight(patient.getBirthWeight());
		}
		
		//出生体重单位
		if(patient.getBabyWeightUnit()!=null)
		{
			mypatientvisit.setBabyWeightUnit(patient.getBabyWeightUnit());
		}
		
		//PV1-51.1:PAT_VISIT_LOGO
		if(patient.getPatVisitLogo()!=null)
		{
			mypatientvisit.setPatVisitLogo(patient.getPatVisitLogo());
		}
		
		//PV1-52.1:OTHER_MEDICAL_INSTITUTIONS
		if(patient.getOldPatientId()!=null)
		{
			mypatientvisit.setOldPatientId(patient.getOldPatientId());
		}
		
		if(patient.getOldPatientDomain()!=null)
		{
			mypatientvisit.setOldPatientDomain(patient.getOldPatientDomain());
		}
		
		if(patient.getOldVisitFlowId()!=null)
		{
			mypatientvisit.setOldVisitFlowId(patient.getOldVisitFlowId());
		}
		
		if(patient.getOldVisitFlowDomain()!=null)
		{
			mypatientvisit.setOldVisitFlowDomain(patient.getOldVisitFlowDomain());
		}
		
		if(patient.getOldStatus()!=null)
		{
			mypatientvisit.setOldStatus(patient.getOldStatus());
		}
		
		if(patient.getOldInfo()!=null)
		{
			mypatientvisit.setOldInfo(patient.getOldInfo());
		}
		
		//GENDER_NAME
		if(patient.getGenderName()!=null)
		{
			mypatientvisit.setGenderName(patient.getGenderName());
		}
		
		//GENDER_DOMAIN
		if(patient.getGenderDomain()!=null)
		{
			mypatientvisit.setGenderDomain(patient.getGenderDomain());
		}
		
		//nooncode
		if(patient.getNoonCode()!=null)
		{
			mypatientvisit.setNoonCode(patient.getNoonCode());
		}
		
		//paykind_code
		if(patient.getPaykindCode()!=null)
		{
			mypatientvisit.setPaykindCode(patient.getPaykindCode());
		}
		
		//paykind_name
		if(patient.getPaykindName()!=null)
		{
			mypatientvisit.setPaykindName(patient.getPaykindName());
		}
		
		//schema_no
		if(patient.getSchemaNo()!=null)
		{
			mypatientvisit.setSchemaNo(patient.getSchemaNo());
		}
		
		//order_no
		if(patient.getOrderNo()!=null)
		{
			mypatientvisit.setOrderNo(patient.getOrderNo());
		}
		
		//seeno
		if(patient.getSeeNo()!=null)
		{
			mypatientvisit.setSeeNo(patient.getSeeNo());
		}
		
		//begin_time
		if(patient.getBeginTime()!=null)
		{
			mypatientvisit.setBeginTime(patient.getBeginTime());
		}
		
		//end_time
		if(patient.getEndTime()!=null)
		{
			mypatientvisit.setEndTime(patient.getEndTime());
		}
		
		//ynbook
		if(patient.getYnBook()!=null)
		{
			mypatientvisit.setYnBook(patient.getYnBook());
		}
		
		
		//ynfr
		if(patient.getYnfr()!=null)
		{
			mypatientvisit.setYNFR(patient.getYnfr());
		}
		
		//append_flag
		if(patient.getAppendFlag()!=null)
		{
			mypatientvisit.setAppendFlag(patient.getAppendFlag());
		}
		
		//ynsee
		if(patient.getYnSee()!=null)
		{
			mypatientvisit.setYnSee(patient.getYnSee());
		}
		
		//see_date
		if(patient.getSeeDate()!=null)
		{
			mypatientvisit.setSeeDate(patient.getSeeDate());
		}
		
		//triage_flag
		if(patient.getTriageFlag()!=null)
		{
			mypatientvisit.setTriageFlag(patient.getTriageFlag());
		}
		
		//triage_opcd
		if(patient.getTriageOpcd()!=null)
		{
			mypatientvisit.setTriageOpcd(patient.getTriageOpcd());
		}
		
		//triage_date
		if(patient.getTriageDate()!=null)
		{
			mypatientvisit.setTriageDate(patient.getTriageDate());
		}
		
		//see_dpcd
		if(patient.getSeeDpcd()!=null)
		{
			mypatientvisit.setSeeDpcd(patient.getSeeDpcd());
		}
		
		//see_docd
		if(patient.getSeeDocd()!=null)
		{
			mypatientvisit.setSeeDocd(patient.getSeeDocd());
		}
		
		//out_patient_status_a
		if(patient.getOutPatientStatusA()!=null)
		{
			mypatientvisit.setOutPatientStatusA(patient.getOutPatientStatusA());
		}
		
		//out_patient_status_b
		if(patient.getOutPatientStatusB()!=null)
		{
			mypatientvisit.setOutPatientStatusB(patient.getOutPatientStatusB());
		}
		
		//out_patient_status_c
		if(patient.getOutPatientStatusC()!=null)
		{
			mypatientvisit.setOutPatientStatusC(patient.getOutPatientStatusC());
		}
		
		//in_patient_status_a
		if(patient.getInPatientStatusA()!=null)
		{
			mypatientvisit.setInPatientStatusA(patient.getInPatientStatusA());
		}
		
		//in_patient_status_b
		if(patient.getInPatientStatusB()!=null)
		{
			mypatientvisit.setInPatientStatusB(patient.getInPatientStatusB());
		}
		
		//in_patient_status_c
		if(patient.getInPatientStatusC()!=null)
		{
			mypatientvisit.setInPatientStatusC(patient.getInPatientStatusC());
		}
		
		
		log.trace("Converted object: " + patient + " to " + mypatientvisit);
		
		return mypatientvisit;
	}
	
	//填充电话信息
	private static void populatePhone(Person person, PhoneNumber phone)
	{
		if (phone == null) 
		{
			return;
		}
		
		person.setPhoneAreaCode(phone.getAreaCode());
		
		person.setPhoneCountryCode(phone.getCountryCode());
		
		person.setPhoneExt(phone.getExtension());
		
		person.setPhoneNumber(phone.getNumber());
		
		if (phone.getType() != null) 
		{
			person.setPhoneAreaCode(getAEmpiPhoneType(phone.getType()).getPhoneTypeCode());
		}
	}

	//获取电话类型CODE
	private static PhoneTypeDict getAEmpiPhoneType(com.ats.aexchange.datamodel.SharedEnums.PhoneType type) 
	{
		PhoneTypeDict phoneType = new PhoneTypeDict();
		
		log.debug(com.misyshealthcare.connect.base.SharedEnums.PhoneType.HOME);
		
		log.debug(type);
		
		if (type.name().equals(com.misyshealthcare.connect.base.SharedEnums.PhoneType.CELL.name())) 
		{
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_CELL);
		} 
		else if (type.name().equals(com.misyshealthcare.connect.base.SharedEnums.PhoneType.EMERGENCY.name())) 
		{
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_EMERGENCY);
		} 
		else if (type.name().equals(com.misyshealthcare.connect.base.SharedEnums.PhoneType.FAX.name())) 
		{
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_FAX);
		} 
		else if (type.name().equals(com.misyshealthcare.connect.base.SharedEnums.PhoneType.HOME.name())) 
		{
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_HOME);
		} 
		else if (type.name().equals(com.misyshealthcare.connect.base.SharedEnums.PhoneType.SERVICE.name())) 
		{
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_SERVICE);
		} 
		else if (type.name().equals(com.misyshealthcare.connect.base.SharedEnums.PhoneType.UNKNOWN.name())) 
		{
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_UNKNOWN);
		} 
		else if (type.name().equals(com.misyshealthcare.connect.base.SharedEnums.PhoneType.WORK.name())) 
		{
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_WORK);
		} 
		else 
		{
			log.warn("未知电话类型 " + type);
			
			phoneType.setPhoneTypeCode(PHONE_TYPE_CODE_UNKNOWN);
		}
		
		return phoneType;
	}

	//枚举电话类型
	private static com.ats.aexchange.datamodel.SharedEnums.PhoneType getMisysPhoneType(PhoneTypeDict type) 
	{
		if (type == null || type.getPhoneTypeCode() == null) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.UNKNOWN; 
		} 
		else if (type.getPhoneTypeCode().equalsIgnoreCase(PHONE_TYPE_CODE_CELL)) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.CELL;
		} 
		else if (type.getPhoneTypeCode().equalsIgnoreCase(PHONE_TYPE_CODE_EMERGENCY)) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.EMERGENCY;
		} 
		else if (type.getPhoneTypeCode().equalsIgnoreCase(PHONE_TYPE_CODE_FAX)) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.FAX;
		} 
		else if (type.getPhoneTypeCode().equalsIgnoreCase(PHONE_TYPE_CODE_HOME)) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.HOME;
		} 
		else if (type.getPhoneTypeCode().equalsIgnoreCase(PHONE_TYPE_CODE_SERVICE)) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.SERVICE;
		} 
		else if (type.getPhoneTypeCode().equalsIgnoreCase(PHONE_TYPE_CODE_UNKNOWN)) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.UNKNOWN;
		} 
		else if (type.getPhoneTypeCode().equalsIgnoreCase(PHONE_TYPE_CODE_WORK)) 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.WORK;
		} 
		else 
		{
			return com.ats.aexchange.datamodel.SharedEnums.PhoneType.UNKNOWN;
		}
	}
	
	
	//获取电话信息,包含电话号,区号,国家号,分机号,电话类型填充入PhoneNumber类
	private static PhoneNumber getPhoneNumber(Person person) 
	{
		PhoneNumber number = new PhoneNumber();
		
		number.setAreaCode(person.getPhoneAreaCode());
		
		number.setCountryCode(person.getPhoneCountryCode());
		
		number.setExtension(person.getPhoneExt());
		
		number.setNumber(person.getPhoneNumber());
		
		if (person.getPhoneTypeDictByPhoneTypeCd() != null) 
		{
			number.setType(getMisysPhoneType(person.getPhoneTypeDictByPhoneTypeCd()));
		}
		
		return number;
	}

	//填充地址信息,包含家庭地址、工作地址、城市、省份、邮编、国家
	public static void populateAddress(Person person, Address address) 
	{
		if (address == null) 
		{
			return;
		}
		
		person.setAddress1(address.getAddLine1());
		
		person.setAddress2(address.getAddLine2());
		
		person.setCity(address.getAddCity());
		
		person.setState(address.getAddState());
		
		person.setPostalCode(address.getAddZip());
		
		person.setCountry(address.getAddCountry());
		
	}


	//获取地址信息,包含家庭地址、工作地址、城市、省份、邮编、国家填充入 Address类
	private static Address getAddress(Person person) 
	{
		Address address = new Address();
		
		address.setAddLine1(person.getAddress1());
		
		address.setAddLine2(person.getAddress2());
		
		address.setAddCity(person.getCity());
		
		address.setAddState(person.getState());
		
		address.setAddZip(person.getPostalCode());
		
		address.setAddCountry(person.getCountry());
		
		return address;
	}

	//根据获取得到的personIdentifier实体类获取身份机构信息
	public static PersonIdentifierEMPI getPersonIdentifier(com.ats.aexchange.datamodel.PersonIdentifier personIdentifier) 
	{
		log.trace("Person identifier is: " + personIdentifier.getAssigningAuthority());
		
		log.debug("身份机构1" + personIdentifier.getAssigningAuthority());
		
		PersonIdentifierEMPI pi = new PersonIdentifierEMPI();
		
		pi.setIdentifier(personIdentifier.getId());//设置localid
		
		//pi.setCustom1(personIdentifier.)
		
		if (personIdentifier.getAssigningAuthority() == null) 
		{
			return pi;
		}
		
		IdentifierDomainDict id = new IdentifierDomainDict();
		
		id.setNamespaceIdentifier(personIdentifier.getAssigningAuthority().getNamespaceId());
		
		id.setUniversalIdentifier(personIdentifier.getAssigningAuthority().getUniversalId());
		
		id.setUniversalIdentifierTypeCode(personIdentifier.getAssigningAuthority().getUniversalIdType());
		
		pi.setIdentifierDomainDict(id);
		
		return pi;
	}


	//根据输入的查询信息，身份信息填充PersonIdentifier实体类，生成不同的匹配查询条件
	public static PersonIdentifierEMPI getPersonIdentifier(com.ats.aexchange.datamodel.PersonIdentifier personIdentifier, PdqQuery query) 
	{
		log.trace("Person identifier is: " + personIdentifier.getAssigningAuthority());
		
		PersonIdentifierEMPI pi = new PersonIdentifierEMPI();
		
		pi.setIdentifier(handlePrefixSuffix(query, personIdentifier.getId()));
		
		if (personIdentifier.getAssigningAuthority() == null) 
		{
			return pi;
		}
		
		IdentifierDomainDict id = new IdentifierDomainDict();
		
		id.setNamespaceIdentifier(handlePrefixSuffix(query, personIdentifier.getAssigningAuthority().getNamespaceId()));
		
		id.setUniversalIdentifier(handlePrefixSuffix(query, personIdentifier.getAssigningAuthority().getUniversalId()));
		
		id.setUniversalIdentifierTypeCode(handlePrefixSuffix(query, personIdentifier.getAssigningAuthority().getUniversalIdType()));
		
		pi.setIdentifierDomainDict(id);
		
		return pi;
	}

	//根据PersonIdentifier实体类转换成PatientIdentifier类
	public static PatientIdentifier getPatientIdentifier(PersonIdentifierEMPI identifier) 
	{
		Identifier aa = new Identifier(identifier.getIdentifierDomainDict().getNamespaceIdentifier(),
									   identifier.getIdentifierDomainDict().getUniversalIdentifier(),
									   identifier.getIdentifierDomainDict().getUniversalIdentifierTypeCode());
		
		return new PatientIdentifier(identifier.getIdentifier(), aa);
	}

	//填充病人姓名信息包含姓、名、前缀、后缀、曾用名
	public static void populatePersonName(Person person, PdqQuery query) 
	{
		PersonName from = query.getPersonName();
		
		if (from == null) 
		{
			return;
		}
		
		person.setFamilyName(handlePrefixSuffix(query, from.getFirstName()));
		
		person.setGivenName(handlePrefixSuffix(query, from.getLastName()));
		
		person.setMiddleName(handlePrefixSuffix(query, from.getSecondName()));
		
		person.setPrefix(from.getPrefix());
		
		person.setSuffix(from.getSuffix());
		
		if (query.getDegree()!=null) 
		{
			DegreeTypeDict degree = new DegreeTypeDict();
			
			degree.setDegreeTypeCode(query.getDegree());
			
			person.setDegreeTypeDict(degree);
		}
		
		if(query.getDegreeName()!=null)
		{
			person.setDegreeName(query.getDegreeName());
		}
		
		if(query.getDegreeDomain()!=null)
		{
			person.setDegreeDomain(query.getDegreeDomain());
		}
		
	}
	
	//查询条件，左%还是右%
	private static String handlePrefixSuffix(PdqQuery query, String from) {
		if (from == null) return null;
		
		String ret = from;
		String prefix = query.getPrefix();
		String suffix = query.getSuffix();
		
		if (prefix != null) {
			if (from.startsWith(prefix)) {
				ret = "%" + ret.substring(prefix.length());
			}
		}
		if (suffix != null) {
			if (from.endsWith(suffix)) {
				ret = ret.substring(0, ret.length()-suffix.length()) + "%";
			}
		}
		return ret;
	}
	
	//根据PERSON实体类获取得到的病人姓名信息包含姓、名、前缀、后缀、曾用名、学位、姓名类别传入PersonName类
	public static PersonName getPersonName(Person person) 
	{
		PersonName personName = new PersonName();
		
		if(person.getDegreeTypeDict()!=null)
		{
			personName.setDegree(person.getDegreeTypeDict().getDegreeTypeCode());
		}
		
		if(person.getDegreeName()!=null)
		{
			personName.setDegreeName(person.getDegreeTypeDict().getDegreeTypeName());
		}
		
		if(person.getDegreeDomain()!=null)
		{
			personName.setDegreeDomain(person.getDegreeDomain());
		}
		
		personName.setFirstName(person.getGivenName());
		
		personName.setLastName(person.getFamilyName());
		
		personName.setPrefix(person.getPrefix());
		
		personName.setSecondName(person.getMiddleName());
		
		personName.setSuffix(person.getSuffix());
		
		if (person.getNameTypeDict() != null) 
		{
			personName.setNameTypeCode(person.getNameTypeDict().getNameTypeCode());
		}
		
		return personName;
	}

	//填充病人姓名信息，从PersonName类传入Person实体类
	public static void populatePersonName(Person person, PersonName personName)
	{
//		personName=new PersonName();
//		personName.setFirstName("123");
//		personName.setLastName("456");
		String lastName = personName.getLastName();
		
		if (lastName != null)
		{
			lastName = lastName.toUpperCase();
		}
		
		String firstName = personName.getFirstName();
		
		if (firstName != null) 
		{
			firstName = firstName.toUpperCase();
		}
		
		//person.setGivenName((lastName + firstName));
		
		person.setGivenName(lastName); 
				
		person.setFamilyName(firstName);
		
		person.setPrefix(personName.getPrefix());
		
		person.setSuffix(personName.getSuffix());
		
		//person.setDegreeTypeDict(personName.getDegree());
		
		if (personName.getNameTypeCode() != null) 
		{
			NameTypeDict nameType = new NameTypeDict();
			
			nameType.setNameTypeCode(personName.getNameTypeCode());
			
			person.setNameTypeDict(nameType);
		}
		
		//学位
		if (personName.getDegree()!=null)
		{
			DegreeTypeDict degreeType = new DegreeTypeDict();
			
			degreeType.setDegreeTypeCode(personName.getDegree());
			
			person.setDegreeTypeDict(degreeType);
		}
		
		//学位名称
		person.setDegreeName(personName.getDegreeName());
		
		//学位编码系统
		person.setDegreeDomain(personName.getDegreeDomain());
	}


	//根据获取得到的PersonIdentifier实体类信息，传入PatientIdentifier类
	public static PatientIdentifier convertPatientIdentifier(PersonIdentifierEMPI pid) 
	{
		if (pid == null) 
		{
			return null;
		}
		 
		PatientIdentifier ret = new PatientIdentifier();
		
		ret.setId(pid.getIdentifier());
		
		ret.setAssigningAuthority(convertIdentifierDomain(pid.getIdentifierDomainDict()));
		
		return ret;
	}


	//根据获取得到身份机构信息传入Identifier类
	public static Identifier convertIdentifierDomain(IdentifierDomainDict identifierDomain) 
	{
		if (identifierDomain == null) 
		{
			return null;
		}
		
		Identifier ret = new Identifier(identifierDomain.getNamespaceIdentifier(),
										identifierDomain.getUniversalIdentifier(),
										identifierDomain.getUniversalIdentifierTypeCode());
		
		return ret;
	}
}
