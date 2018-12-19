package com.ats.aempi.apixpdqadapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.apixpdq.api.IPdSupplierAdapter;
import com.ats.apixpdq.api.PdSupplierException;
import com.ats.apixpdq.api.PdqQuery;
import com.ats.apixpdq.api.PdqResult;
import com.ats.aempi.ApplicationException;
import com.ats.aempi.context.Context;
import com.ats.aempi.model.ExtendForPerson;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;

public class PdqSupplierAdapter extends BasePixPdqAdapter implements IPdSupplierAdapter
{
	PatientVisit patientvisitTemplate;
	
	public PdqSupplierAdapter() 
	{
		super();
	}
	
	public List<ExtendForPerson> findExtendFields(int PidCount) throws PdSupplierException, ApplicationException{
		return Context.getPersonManagerService().FindExtendField(PidCount);
		
	}
	
	public List<ExtendForPerson> findAllFields() throws PdSupplierException, ApplicationException{
		return Context.getPersonManagerService().ExtendField();
		
	}
	/**
	 * @基本思路
	 * 
	 * 先查找符合输入查询参数条件的病人，在查找此病人的关联病人既可合并的病人，在转换成patient类输出
	 * 
	 * @author yrh-2012-04-10
	 */
	public PdqResult findPatients(PdqQuery query, MessageHeader header) throws PdSupplierException 
	{
		//获取病人基本查询条件
 		Person personTemplate = ConversionHelper.getPerson(query);
 		
 		if(header.getTriggerEvent().toString().equals("ZV1"))
 		{		
 			patientvisitTemplate=new PatientVisit();
 			
 			patientvisitTemplate=ConversionHelper.getPatientVisit(query);
 		}
 		
 		log.fatal("查询条件是:" + "\n");
		
 		if(personTemplate.getGivenName()!=null)
 		{
 			log.fatal( " 姓:" + personTemplate.getGivenName().toString());
 		}
		
 		if(personTemplate.getFamilyName()!=null)
 		{
 			log.fatal(" 名:" + personTemplate.getFamilyName().toString());
 		}
 		
 		if(personTemplate.getNameCode()!=null)
 		{
 			log.fatal(" 拼音码: " + personTemplate.getNameCode().toString());
 		}
 		
 		if(personTemplate.getEmpi()!=null)
 		{
 			log.fatal(" 住院号: " + personTemplate.getEmpi().toString());
 		}
 		
 		if(personTemplate.getCountry()!=null)
 		{
 			log.fatal(" 国家:" + personTemplate.getCountry().toString());
 		}
 		
 		if(personTemplate.getState()!=null)
 		{
 			log.fatal(" 省/直辖市:" + personTemplate.getState().toString());
 		}
 		
 		if(personTemplate.getCity()!=null)
 		{
 			log.fatal(" 城市:" + personTemplate.getCity().toString());
 		}
 		
 		if(personTemplate.getAddress1()!=null)
 		{
 			log.fatal(" 地址:" + personTemplate.getAddress1().toString());
 		}
 		
 		if(personTemplate.getPhoneNumber()!=null)
 		{
 			log.fatal(" 手机号码:" + personTemplate.getPhoneNumber().toString());
 		}
 		
 		if(personTemplate.getDateOfBirth()!=null)
 		{
 			log.fatal(" 生日:" + personTemplate.getDateOfBirth().toString());
 		}
 		
 		if(personTemplate.getIdentityNo()!=null)
 		{
 			log.fatal(" 身份证号:" + personTemplate.getIdentityNo().toString());
 		}
 		
 		if(personTemplate.getCustom16()!=null)
 		{
 			log.fatal("住院号:" + personTemplate.getCustom16().toString());
 		}
 		
 		if(personTemplate.getInsuranceNo()!=null)
 		{
 			log.fatal("医保号:" + personTemplate.getInsuranceNo().toString());
 		}
 		
 		if(personTemplate.getCustom17()!=null)
 		{
 			log.fatal("社保号:" + personTemplate.getCustom17().toString());
 		}
 		
 		if(personTemplate.getCustom11()!=null)
 		{
 			log.fatal("机构ID:" + personTemplate.getCustom11().toString());
 		}
 		
 		
 		if(personTemplate.getPhoneNumber()!=null)
 		{
 			log.fatal("联系电话:" + personTemplate.getPhoneNumber().toString());
 		}
 		
 		if(personTemplate.getMothersMaidenName()!=null)
 		{
 			log.fatal("母亲姓氏:" + personTemplate.getMothersMaidenName());
 		}
 		
 		if(personTemplate.getMultipleBirthInd()!=null)
 		{
 			log.fatal("是否多胞胎:" + personTemplate.getMultipleBirthInd());
 		}
 		
 		if(personTemplate.getBirthOrder()!=null)
 		{
 			log.fatal("出生顺序：" + personTemplate.getBirthOrder());
 		}
 		
 		if(personTemplate.getMaritalStatusDict()!=null)
 		{
 			log.fatal("婚姻状态:" + personTemplate.getMaritalStatusDict().getMaritalStatusCode());
 		}
 
 		if(personTemplate.getCustom6()!=null)
 		{
 			log.fatal("CUSTOM6:" + personTemplate.getCustom6());
 		}
 		
 		if(personTemplate.getCustom7()!=null)
 		{
 			log.fatal("CUSTOM7:" + personTemplate.getCustom7());
 		}

 		if(personTemplate.getCustom8()!=null)
 		{
 			log.fatal("CUSTOM8:" + personTemplate.getCustom8());
 		}
 		
 		if(personTemplate.getCustom9()!=null)
 		{
 			log.fatal("CUSTOM9:" + personTemplate.getCustom9());
 		}
 		
 		if(personTemplate.getCustom13()!=null)
 		{
 			log.fatal("CUSTOM13:" + personTemplate.getCustom13());
 		}
 		
 		if(personTemplate.getCustom14()!=null)
 		{
 			log.fatal("CUSTOM14:" + personTemplate.getCustom14());
 		}
 		
 		if(personTemplate.getCustom15()!=null)
 		{
 			log.fatal("CUSTOM15:" + personTemplate.getCustom15());
 		}
 		
 		if(personTemplate.getCustom18()!=null)
 		{
 			log.fatal("CUSTOM18:" + personTemplate.getCustom18());
 		}
 		
 		if(personTemplate.getCustom19()!=null)
 		{
 			log.fatal("CUSTOM19:" + personTemplate.getCustom19());
 		}
 		
 		if(personTemplate.getCustom20()!=null)
 		{
 			log.fatal("CUSTOM20:" + personTemplate.getCustom20());
 		}
 		
 		if(header.getTriggerEvent().toString().equals("ZV1"))
 		{
 		//PV1-2.1:PAT_CATEGORY
		if(patientvisitTemplate.getPatCategory()!=null)
		{
			log.fatal("\n" + "PAT_CATEGORY: " + patientvisitTemplate.getPatCategory());
		}
		
		//PV1-3.1:PAT_CURRENT_POINT_OF_CARE
		if(patientvisitTemplate.getPatCurrentPointOfCare()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_POINT_OF_CARE: " + patientvisitTemplate.getPatCurrentPointOfCare());
		}
		
		//PV1-3.2:PAT_CURRENT_ROOM
		if(patientvisitTemplate.getPatCurrentRoom()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_ROOM: " + patientvisitTemplate.getPatCurrentRoom());
		}
		
		//PV1-3.3:PAT_CURRENT_BED
		if(patientvisitTemplate.getPatCurrentBed()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_BED: " + patientvisitTemplate.getPatCurrentBed());
		}
		
		
		//PV1-3.4:PAT_CURRENT_DEP
		if(patientvisitTemplate.getPatCuurentDep()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_DEP: " + patientvisitTemplate.getPatCuurentDep());
		}
		
		//PV1-3.5:PAT_CURRENT_POSITION_STATUS
		if(patientvisitTemplate.getPatCurrentPositionStatus()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_POSITION_STATUS: " + patientvisitTemplate.getPatCurrentPositionStatus());
		}
		
		//PV1-3.6:PAT_CURRENT_POSITION_TYPE
		if(patientvisitTemplate.getPatCurrentPositionType()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_POSITION_TYPE: " + patientvisitTemplate.getPatCurrentPositionType());
		}
		
		//pv1-3.7:PAT_CURRENT_BUILDING
		if(patientvisitTemplate.getPatCurrentBuilding()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_BUILDING: " + patientvisitTemplate.getPatCurrentBuilding());
		}
		
		//PV1-3.8:PAT_CURRENT_FLOOR
		if(patientvisitTemplate.getPatCurrentFloor()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_FLOOR: " + patientvisitTemplate.getPatCurrentFloor());
		}
		
		//PV1-3.9:PAT_CURRENT_DESCRIPTION
		if(patientvisitTemplate.getPatCuurentDescription()!=null)
		{
			log.fatal("\n" + "PAT_CURRENT_DESCRIPTION: " + patientvisitTemplate.getPatCuurentDescription());
		}
		
		//PV1-4.1:PAT_ADMISSION_TYPE
		if(patientvisitTemplate.getPatAdmissionType()!=null)
		{
			log.fatal("\n" + "PAT_ADMISSION_TYPE: " + patientvisitTemplate.getPatAdmissionType());
		}
		
		//PV1-5.1:PAT_ADMISSION_NUMBER
		if(patientvisitTemplate.getPatAdmissionNumber()!=null)
		{
			log.fatal("\n" + "PAT_ADMISSION_NUMBER: " + patientvisitTemplate.getPatAdmissionNumber());
		}
		
		//PV1-6.1:PAT_FORMER_POINT_OF_CARE
		if(patientvisitTemplate.getPatFormerPointOfCare()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_POINT_OF_CARE: " + patientvisitTemplate.getPatFormerPointOfCare());
		}
		
		//PV1-6.2:PAT_FORMER_ROOM
		if(patientvisitTemplate.getPatFormerRoom()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_ROOM: " + patientvisitTemplate.getPatFormerRoom());
		}
		
		//PV1-6.3:PAT_FORMBER_BED
		if(patientvisitTemplate.getPatFormerBed()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_BED: " + patientvisitTemplate.getPatFormerBed());
		}
		
		//PV1-6.4:PAT_FORMER_DEP
		if(patientvisitTemplate.getPatFormerDep()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_DEP: " + patientvisitTemplate.getPatFormerDep());
		}
		
		//PV1-6.5:PAT_FORMER_POSITION_STATUS
		if(patientvisitTemplate.getPatFormerPositionStatus()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_POSITION_STATUS: " + patientvisitTemplate.getPatFormerPositionStatus());
		}
		
		//PV1-6.6:PAT_FORER_POSITION_TYPE
		if(patientvisitTemplate.getPatFormerPositionType()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_POSITION_TYPE: " + patientvisitTemplate.getPatFormerPositionType());
		}
		
		//PV1-6.7:PAT_FORMER_BUILDING
		if(patientvisitTemplate.getPatFormerBuilding()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_BUILDING: " +patientvisitTemplate.getPatFormerBuilding());
		}
		
		//PV1-6.8:PAT_FORMER_FLOOR
		if(patientvisitTemplate.getPatFormerFloor()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_FLOOR: " + patientvisitTemplate.getPatFormerFloor());
		}
		
		//PV1-6.9:PAT_FORMER_DESCRIPTION
		if(patientvisitTemplate.getPatFormerDescription()!=null)
		{
			log.fatal("\n" + "PAT_FORMER_DESCRIPTION: " + patientvisitTemplate.getPatFormerDescription());
		}
		
		//PV1-7.2:ADMISSIONS_DOCTOR
		if(patientvisitTemplate.getAdmissionsDoctor()!=null)
		{
			log.fatal("\n" + "ADMISSIONS_DOCOTOR: " + patientvisitTemplate.getAdmissionsDoctor());
		}
		
		//PV1-7.1:ADMISSIONS_DOCTOR_ID
		if(patientvisitTemplate.getAdmissionsDoctorId()!=null)
		{
			log.fatal("\n" + "ADMISSIONS_DOCTOR_ID: " + patientvisitTemplate.getAdmissionsDoctorId());
		}
		
		//PV1-8.1:REFERRING_DOCTOR_ID
		if(patientvisitTemplate.getReferringDoctorId()!=null)
		{
			log.fatal("\n" + "REFERRING_DOCTOR_ID: " + patientvisitTemplate.getReferringDoctorId());
		}
		
		//PV1-8.2:REFERRING_DOCTOR
		if(patientvisitTemplate.getReferringDoctor()!=null)
		{
			log.fatal("\n" + "REFERRING_DOCOTOR: " + patientvisitTemplate.getReferringDoctor());
		}
		
		//PV1-9.1:CONSULTATION_DOCTOR_ID
		if(patientvisitTemplate.getConsultationDoctorId()!=null)
		{
			log.fatal("\n" + "CONSULTATION_DOCOTOR_ID: " + patientvisitTemplate.getConsultationDoctorId());
		}
		
		//PV1-9.2:CONSULTATION_DOCTOR
		if(patientvisitTemplate.getConsultationDoctor()!=null)
		{
			log.fatal("\n" + "CONSULTATION_DOCOTOR: " + patientvisitTemplate.getConsultationDoctor());
		}
		
		//PV1-10.1:HOSPITAL_SERVICE
		if(patientvisitTemplate.getHospitalService()!=null)
		{
			log.fatal("\n" + "HOSPITAL_SERVICE: " + patientvisitTemplate.getHospitalService());
		}
		
		//PV1-11.1:PAT_TEMP_POINT_OF_CARE
		if(patientvisitTemplate.getPatTempPointOfCare()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_POINT_OF_CARE: " + patientvisitTemplate.getPatTempPointOfCare());
		}
		
		//PV1-11.2:PAT_TEMP_ROOM
		if(patientvisitTemplate.getPatTempRoom()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_ROOM: " + patientvisitTemplate.getPatTempRoom());
		}
		
		//PV1-11.3:PAT_TEMP_BED
		if(patientvisitTemplate.getPatTempBed()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_BED: " + patientvisitTemplate.getPatTempBed());
		}
		
		//PV1-11.4:PAT_TEMP_DEP
		if(patientvisitTemplate.getPatTempDep()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_DEP: " + patientvisitTemplate.getPatTempDep());
		}
		
		//PV1-11.5:PAT_TEMP_POSITION_STATUS
		if(patientvisitTemplate.getPatTempPositionStatus()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_POSITION_STATUS: " + patientvisitTemplate.getPatTempPositionStatus());
		}
		
		//PV1-11.6:PAT_TEMP_POSITION_TYPE
		if(patientvisitTemplate.getPatTempPositionType()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_POSITION_TYPE: " + patientvisitTemplate.getPatTempPositionType());
		}
		
		//PV1-11.7:PAT_TEMP_BUILDING
		if(patientvisitTemplate.getPatTempBuilding()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_BUILDING: " + patientvisitTemplate.getPatTempBuilding());
		}
		
		//PV1-11.8:PAT_TEMP_FLOOR
		if(patientvisitTemplate.getPatTempFloor()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_FLOOR: " + patientvisitTemplate.getPatTempFloor());
		}
		
		//PV1-11.9:PAT_TEMP_DESCRIPTION
		if(patientvisitTemplate.getPatTempDescription()!=null)
		{
			log.fatal("\n" + "PAT_TEMP_DESCRIPTION: " + patientvisitTemplate.getPatTempDescription());
		}
		
		//PV1-12.1:PAT_ADMISSION_TEST
		if(patientvisitTemplate.getPatAdmissionTest()!=null)
		{
			log.fatal("\n" + "PAT_ADMISSION_TEST: " + patientvisitTemplate.getPatAdmissionTest());
		}
		
		//pv1-13.1:PAT_RE_ADMISSION
		if(patientvisitTemplate.getPatReAdmission()!=null)
		{
			log.fatal("\n" + "PAT_RE_ADMISSION: " + patientvisitTemplate.getPatReAdmission());
		}
		
		//PV1-14.1:PAT_ADMISSION_SOURCE
		if(patientvisitTemplate.getPatAdmissionSource()!=null)
		{
			log.fatal("\n" + "PAT_ADMISSION_SOURCE: " + patientvisitTemplate.getPatAdmissionSource());
		}
		
		//PV1-15.1:PAT_AMBULATORY_STATUS
		if(patientvisitTemplate.getPatAmbulatoryStatus()!=null)
		{
			log.fatal("\n" + "PAT_AMBULATORY_STATUS: " + patientvisitTemplate.getPatAmbulatoryStatus());
		}
		
		//PV1-16.1:PAT_VIP
		if(patientvisitTemplate.getPatVip()!=null)
		{
			log.fatal("\n" + "PAT_VIP: " +patientvisitTemplate.getPatVip());
		}
		
		//PV1-17.2:PAT_ADMISSION_DOCTORS
		if(patientvisitTemplate.getPatAdmissionDoctors()!=null)
		{
			log.fatal("\n" + "PAT_ADMISSION_DOCTORS: " + patientvisitTemplate.getPatAdmissionDoctors());
		}
		
		//PV1-17.1:PAT_ADMISSION_DOCTORS_ID
		if(patientvisitTemplate.getPatAdmissionDoctorsId()!=null)
		{
			log.fatal("\n" + "PAT_ADMISSION_DOCTORS_ID: " + patientvisitTemplate.getPatAdmissionDoctorsId());
		}
		
		//PV1-18.1:patient_CLASS
		if(patientvisitTemplate.getPatientClass()!=null)
		{
			log.fatal("\n" + "PATIENT_CLASS: " + patientvisitTemplate.getPatientClass());
		}
		
		//PV1-19.1:patien_ID
		if(patientvisitTemplate.getPatientId()!=null)
		{
			log.fatal("\n" + "PATIENT_ID: " + patientvisitTemplate.getPatientId());
		}
		
		//PV1-20.1:PAT_FINANCIAL_CLASS
		if(patientvisitTemplate.getPatFinancialClass()!=null)
		{
			log.fatal("\n" + "PAT_FINANCIAL_CLASS: " + patientvisitTemplate.getPatFinancialClass());
		}
		
		//PV1-21.1:ROOM_BED_COST_PRICE
		if(patientvisitTemplate.getRoomBedCostPrice()!=null)
		{
			log.fatal("\n" + "ROOM_BED_COST_PRICE: " + patientvisitTemplate.getRoomBedCostPrice());
		}
		
		//PV1-22.1:COURTESY_CODE
		if(patientvisitTemplate.getCourtesyCode()!=null)
		{
			log.fatal("\n" + "COURTESTY_CODE: " + patientvisitTemplate.getCourtesyCode());
		}
		
		//PV1-23.1:CREDIT_RATING
		if(patientvisitTemplate.getCreditRating()!=null)
		{
			log.fatal("\n" + "CREDIT_RATING: " + patientvisitTemplate.getCreditRating());
		}
		
		//PV1-24.1:CONTRACT_CODE
		if(patientvisitTemplate.getContractCode()!=null)
		{
			log.fatal("\n" + "CONTRACT_CODE: " + patientvisitTemplate.getContractCode());
		}
		
		//PV1-25.1:CONTRACT_CREATE_DATE
		if(patientvisitTemplate.getContractCreateDate()!=null)
		{
			log.fatal("\n" + "CONTRACT_CREATE_DATE: " + patientvisitTemplate.getContractCreateDate());
		}
		
		//PV1-26.1:CONTRACT_PRICE
		if(patientvisitTemplate.getContractPrice()!=null)
		{
			log.fatal("\n" + "CONTRACT_PRICE: " + patientvisitTemplate.getContractPrice());
		}
		
		//PV1-27.1:CONTRACT_TIME
		if(patientvisitTemplate.getContractTime()!=null)
		{
			log.fatal("\n" + "CONTRACT_TIME: " + patientvisitTemplate.getContractTime());
		}
		
		//PV1-28.1:INTEREST_RATE_CODE
		if(patientvisitTemplate.getInterestRateCode()!=null)
		{
			log.fatal("\n" + "INTEREST_RATE_CODE: " + patientvisitTemplate.getInterestRateCode());
		}
		
		//PV1-29.1:BAD_DEBTS
		if(patientvisitTemplate.getBadDebts()!=null)
		{
			log.fatal("\n" + "BAD_DEBTS" + patientvisitTemplate.getBadDebts());
		}
		
		//PV1-30.1:BAD_DEBTS_CREATE_DATE
		if(patientvisitTemplate.getBadDebtsCreateDate()!=null)
		{
			log.fatal("\n" + "BAD_DEBTS_CREATE_DATE: " + patientvisitTemplate.getBadDebtsCreateDate());
		}
		
		//PV1-31.1:BAD_DEBTS_CODE
		if(patientvisitTemplate.getBadDebtsCode()!=null)
		{
			log.fatal("\n" + "BAD_DEBTS_CODE: " + patientvisitTemplate.getBadDebtsCode());
		}
		
		//PV1-32.1:BAD_DEBTS_PRICE
		if(patientvisitTemplate.getBadDebtsPrice()!=null)
		{
			log.fatal("\n" + "BAD_DEBTS_PRICE: " + patientvisitTemplate.getBadDebtsPrice());
		}
		
		//PV1-33.1:BAD_DEBTS_RESTORE_PRICE
		if(patientvisitTemplate.getBadDebtsRestorePrice()!=null)
		{
			log.fatal("\n" + "BAD_DEBTS_RESTORE_PRICE: " + patientvisitTemplate.getBadDebtsRestorePrice());
		}
		
		//PV1-34.1:PAT_ACCOUNT_VOIDED
		if(patientvisitTemplate.getPatAccountVoided()!=null)
		{
			log.fatal("\n" + "PAT_ACCOUNT_VOIDED: " + patientvisitTemplate.getPatAccountVoided());
		}
		
		//PV1-35.1:PAT_ACCOUNT_VOIDED_DATE
		if(patientvisitTemplate.getPatAccountVoidedDate()!=null)
		{
			log.fatal("\n" + "PAT_ACCOUNT_VOIDED_DATE: " + patientvisitTemplate.getPatAccountVoidedDate());
		}
		
		//PV1-36.1: PAT_DISCHARGE_DISPOSITION
		if(patientvisitTemplate.getPatDischargeDisposition()!=null)
		{
			log.fatal("\n" + "PAT_DISCHARGE_DISPOSITION: " + patientvisitTemplate.getPatDischargeDisposition());
		}
		
		//PV1-37.1:PAT_DISCHARGE_LOCATION
		if(patientvisitTemplate.getPatDischargeLocation()!=null)
		{
			log.fatal("\n" + "PAT_DISCHARGE_LOCATION: " +patientvisitTemplate.getPatDischargeLocation());
		}
		
		//PV1-38.1:PAT_DIET_TYPE
		if(patientvisitTemplate.getPatDietType()!=null)
		{
			log.fatal("\n" + "PAT_DITE_TYPE: " +patientvisitTemplate.getPatDietType());
		}
		
		//PV1-39.1:PAT_SERVICE_AGENCIES
		if(patientvisitTemplate.getPatServiceAgencies()!=null)
		{
			log.fatal("\n" + "PAT_SERVICE_AGENCIES: " + patientvisitTemplate.getPatServiceAgencies());
		}
		
		//PV1-40.1:PAT_BED_STATUS
		if(patientvisitTemplate.getPatBedStatus()!=null)
		{
			log.fatal("\n" + "PAT_BED_STATUS: " + patientvisitTemplate.getPatBedStatus());
		}
		
		//PV1-41.1:PAT_ACCOUNT_STATUS
		if(patientvisitTemplate.getPatAccountStatus()!=null)
		{
			log.fatal("\n" + "PAT_ACCOUNT_STATUS: " + patientvisitTemplate.getPatAccountStatus());
		}
		
		//PV1-42.1:PAT_DETER_POINT_OF_CARE
		if(patientvisitTemplate.getPatDeterPointOfCare()!=null)
		{
			log.fatal("\n" + "PAT_DETER_POINT_OF_CARE: " + patientvisitTemplate.getPatDeterPointOfCare());
		}
		
		//PV1-42.2:PAT_DETER_ROOM
		if(patientvisitTemplate.getPatDeterRoom()!=null)
		{
			log.fatal("\n" + "PAT_DETER_ROOM: " + patientvisitTemplate.getPatDeterRoom());
		}
		
		//PV1-42.3:PAT_DETER_BED
		if(patientvisitTemplate.getPatDeterBed()!=null)
		{
			log.fatal("\n" + "PAT_DETER_BED: " + patientvisitTemplate.getPatDeterBed());
		}
		
		//PV1-42.4:PAT_DETER_DEP
		if(patientvisitTemplate.getPatDeterDep()!=null)
		{
			log.fatal("\n" + "PAT_DETER_DEP: " + patientvisitTemplate.getPatDeterDep());
		}
		
		//PV1-42.5:PAT_DETER_POSITION_STATUS
		if(patientvisitTemplate.getPatDeterPositionStatus()!=null)
		{
			log.fatal("\n" + "PAT_DETER_POSITION_STATUS: " + patientvisitTemplate.getPatDeterPositionStatus());
		}
		
		//PV1-42.6:PAT_DETER_POSITION_TYPE
		if(patientvisitTemplate.getPatDeterPositionType()!=null)
		{
			log.fatal("\n" + "PAT_DETER_POSITION_TYPE: " + patientvisitTemplate.getPatDeterPositionType());
		}
		
		//PV1-42.7:PAT_DETER_BUILDING
		if(patientvisitTemplate.getPatDeterBuilding()!=null)
		{
			log.fatal("\n" + "PAT_DETER_BUILDING: " + patientvisitTemplate.getPatDeterBuilding());
		}
		
		//PV1-42.8:PAT_DETER_FLOOR
		if(patientvisitTemplate.getPatDeterFloor()!=null)
		{
			log.fatal("\n" + "PAT_DETER_FLOOR: " + patientvisitTemplate.getPatDeterFloor());
		}
		
		//PV1-42.9:PAT_DETER_DESCRIPTION
		if(patientvisitTemplate.getPatDeterDescription()!=null)
		{
			log.fatal("\n" + "PAT_DETER_DESCRIPTION: " + patientvisitTemplate.getPatDeterDescription());
		}
		
		//PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
		if(patientvisitTemplate.getPatForTempPointOfCare()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_POINT_OF_CARE: " + patientvisitTemplate.getPatForTempPointOfCare());
		}
		
		//PV1-43.2:PAT_FOR_TEMP_ROOM
		if(patientvisitTemplate.getPatForTempRoom()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_ROON: " + patientvisitTemplate.getPatForTempRoom());
		}
		
		//PV1-43.3:pat_for_temp_bed
		if(patientvisitTemplate.getPatForTempBed()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_BED: " + patientvisitTemplate.getPatForTempBed());
		}
		
		//PV1-43.4:PAT_FOR_TEMP_DEP
		if(patientvisitTemplate.getPatForTempDep()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_DEP: " + patientvisitTemplate.getPatForTempDep());
		}
		
		//PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
		if(patientvisitTemplate.getPatForTempPositionStatus()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_POSITION_STATUS: " + patientvisitTemplate.getPatForTempPositionStatus());
		}
		
		//PV1-43.6:PAT_FOR_TEMP_POSITION_TYPE
		if(patientvisitTemplate.getPatForTempPositionType()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_POSITION_TYPE: " + patientvisitTemplate.getPatForTempPositionType());
		}
		
		//PV1-43.7:PAT_FOR_TEMP_BUILDING
		if(patientvisitTemplate.getPatForTempBuilding()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_BUILDING: " + patientvisitTemplate.getPatForTempBuilding());
		}
		
		//PV1-43.8:PAT_FOR_TEMP_FLOOR
		if(patientvisitTemplate.getPatForTempFloor()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_FLOOR: " + patientvisitTemplate.getPatForTempFloor());
		}
		
		//PV1-43.9:PAT_FOR_TEMP_DESCRIPTION
		if(patientvisitTemplate.getPatTempDescription()!=null)
		{
			log.fatal("\n" + "PAT_FOR_TEMP_DESCRIPTION: " + patientvisitTemplate.getPatFormerDescription());
		}
		
		//PV1-44.1:ADMIT_DATE
		if(patientvisitTemplate.getAdmitDate()!=null)
		{
			log.fatal("\n" + "ADMIT_DATE: " + patientvisitTemplate.getAdmitDate());
		}
		
		//PV1-45.1:DISCHARGE_DATE
		if(patientvisitTemplate.getDischargeDate()!=null)
		{
			log.fatal("\n" + "DISCHARGE_DATE: " + patientvisitTemplate.getDischargeDate());
		}
		
		//PV1-46.1:PAT_DIFFERENCE
		if(patientvisitTemplate.getPatDifference()!=null)
		{
			log.fatal("\n" + "PAT_DIFFERENCE: " + patientvisitTemplate.getPatDifference());
		}
		
		//PV1-47.1:PAT_TOTAL_COST
		if(patientvisitTemplate.getPatTotalCost()!=null)
		{
			log.fatal("\n" + "PAT_TOTAL_COST: " + patientvisitTemplate.getPatTotalCost());
		}
		
		//PV1-48.1:PAT_TOTAL_DISPATCH
		if(patientvisitTemplate.getPatTotalDispatch()!=null)
		{
			log.fatal("\n" + "PAT_TOTAL_DISPATCH: " + patientvisitTemplate.getPatTotalDispatch());
		}
		
		//PV1-49.1:PAT_TOTAL_AMOUNT_PAYABLE
		if(patientvisitTemplate.getPatTotalAmountPayable()!=null)
		{
			log.fatal("\n" + "PAT_TOTAL_AMOUNT_RAYABLE: " + patientvisitTemplate.getPatTotalAmountPayable());
		}
		
		//PV1-50.1:PAT_SPARE_ID
		if(patientvisitTemplate.getPatSpareId()!=null)
		{
			log.fatal("\n" + "PAT_SPARE_ID: " + patientvisitTemplate.getPatSpareId());
		}
		
		//PV1-51.1:PAT_VISIT_LOGO
		if(patientvisitTemplate.getPatVisitLogo()!=null)
		{
			log.fatal("\n" + "PAT_VISIT_LOGO: " + patientvisitTemplate.getPatVisitLogo());
		}
		
		//PV1-52.1:OTHER_MEDICAL_INSTITUTIONS
		if(patientvisitTemplate.getOtherMedicalInstitutions()!=null)
		{
			log.fatal("\n" + "OTHER_MEDICAL_INSTITUTIONS: " + patientvisitTemplate.getOtherMedicalInstitutions());
		}
 		}
		try {
			
			SecurityHelper.getSessionKey();
			
			List<List<Patient>> allPatients = new ArrayList<List<Patient>>();
			
			List<Person> persons = null;
			
			//如果查询条件中包含身份机构信息则按照提供的身份机构信息查找(参数PersonIdentifier类)，否则按照提供的病人基本信息属性查找(参数person实体类)
			if (personTemplate.getPersonIdentifiers().size() > 0) 
			{
				//System.out.println(personTemplate.getPersonIdentifiers().iterator().next().getIdentifierDomainDict());
				
				if(header.getTriggerEvent().toString().equals("Q22"))
				{
					persons = Context.getPersonQueryService().findPersonsById(personTemplate.getPersonIdentifiers().iterator().next());
				}
				else if (header.getTriggerEvent().toString().equals("ZV1"))
				{
					persons = Context.getPersonQueryService().findPersonVisitById(personTemplate.getPersonIdentifiers().iterator().next(), patientvisitTemplate);
				}
			} 
			else 
			{
				if(header.getTriggerEvent().toString().equals("Q22"))
				{
					persons = Context.getPersonQueryService().findPersonsByAttributes(personTemplate);
				}
				else if (header.getTriggerEvent().toString().equals("ZV1"))
				{
					persons = Context.getPersonQueryService().findPersonVisitByAttributes(personTemplate, patientvisitTemplate);
				}
			}

			//结果为空返回空病人
			if (persons == null || persons.size() == 0) 
			{
				return new PdqResult(allPatients);
			}

			log.debug("符合条件的病人有" + persons.size() + "条");
			
			List<Person> uniquePersons = new ArrayList<Person>();
			
			//根据查找到的person信息查找是否存在相关联的person病人(既可合并的),如果有合并显示
			for (int i=0; i < persons.size(); i++) 
			{
	
				Person person = Context.getPersonQueryService().loadPerson(new Long(persons.get(i).getPersonId()).intValue());
			
//				Person uniquePerson = findLinkedPersonInList(person, uniquePersons);
	
//				if (uniquePerson != null) 
//				{
//					addPersonIdentifiersToUniquePerson(person.getPersonIdentifiers(), uniquePerson);
//				} 
//				else 
//				{
					uniquePersons.add(person);
//				}
			}
            
			log.fatal("符合条件的关联病人有" + uniquePersons.size() + "条");
			
			
            //转换成patient类,进行输出
			
			for (Person person : uniquePersons) 
			{
				List<Patient> patients = new ArrayList<Patient>();
				
				//PatientVisit patientvisit =new PatientVisit();
				
				List<PatientVisit> patientvisitlist = new ArrayList<PatientVisit>();
				
				Patient patient = ConversionHelper.getPatient(person);

				//VISIT QUERY查询时,查询出pv1的信息添加进patient类
				if(header.getTriggerEvent().toString().equals("ZV1"))
				{
					patientvisitlist=Context.getPersonQueryService().findPatientVisitListById(person);
					
					if(patientvisitlist!=null)
					{
						patient=ConversionHelper.getPatient(patient,patientvisitlist);
					}
				}
				
				patients.add(patient);
				
				allPatients.add(patients);
			}
			
			return new PdqResult(allPatients);
			
		} catch (Exception e) 
		{
			log.error(e.getMessage());
			
			throw new PdSupplierException(e);
		}
	}
	
	private void addPersonIdentifiersToUniquePerson(Set<PersonIdentifierEMPI> personIdentifiers, Person uniquePerson) 
	{
		Set<com.ats.aempi.model.PersonIdentifierEMPI> identifiers = uniquePerson.getPersonIdentifiers();
		
		identifiers.addAll(personIdentifiers);
	}

	private Person findLinkedPersonInList(Person person, List<Person> uniquePersons) 
	{
		if (uniquePersons.size() == 0) 
		{
			return null;
		}
		
		Set<Person> linkedPersonSet = new HashSet<Person>();
		
		for (com.ats.aempi.model.PersonIdentifierEMPI identifier : person.getPersonIdentifiers()) 
		{
			List<Person> linkedPersons = Context.getPersonQueryService().findLinkedPersons(identifier);
			
			linkedPersonSet.addAll(linkedPersons);
		}
		
		for (Person uniquePerson : uniquePersons) 
		{
			for (Person linkedPerson : linkedPersonSet) 
			{
				
				if (String.valueOf(linkedPerson.getPersonId()).equals(String.valueOf(uniquePerson.getPersonId()))) 
				{
					return uniquePerson;
				}
			}
		}
		return null;
	}

	public void cancelQuery(String queryTag, String messageQueryName) throws PdSupplierException 
	{

	}
}
