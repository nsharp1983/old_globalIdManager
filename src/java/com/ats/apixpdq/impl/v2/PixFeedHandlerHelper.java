package com.ats.apixpdq.impl.v2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import com.ats.aempi.model.ExtendForPerson;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.apixpdq.api.IPixManagerAdapter;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.PixPdqException;
import com.ats.apixpdq.impl.v2.hl7.HL7v231ToBaseConvertor;

public class PixFeedHandlerHelper
{
	
	
	 /**
     * 将PIX Feed消息，转化为Patient对象
     * 现在的写法并不很负责，但比6000多行的一个类好一些
     * 
     * @param msgIn
     *            the incoming PIX Feed message
     * @return a {@link Patient} object
     * @throws PixPdqException
     *             if something is wrong with the application
     */
    public static Patient getPatient(IPixManagerAdapter pixAdapter, PixManager actor, Message msgIn) throws PixPdqException, HL7Exception
    {
        HL7v231ToBaseConvertor convertor = null;

        // 如果是2.3.1版本 NEW个HL7变量
        if (msgIn.getVersion().equals("2.3.1"))
        {
            convertor = new HL7v231ToBaseConvertor(msgIn, actor.getActorDescription(), pixAdapter);

        }
        else
        {
            throw new PixPdqException("Unexpected HL7 version");
        }

        // 填充PID病人基本信息字段.包含
        // PID注释:
        // PID-1:Set ID
        // PID-2:Patient Identifier LIST.ID NUMBER
        // PID-3:Patient Identifier List.Id NUMBER
        // PID-3.4 Patient Identifier List.Assigning Authority.Namespace ID
        // PID-3.4.2 Patient Identifier List.Assigning Authority.Universal ID
        // PID-3.4.3 Patient Identifier List.Assigning.Universal ID Type
        // PID-3.5 Patient Identifier List.Identifier Type Code
        // PID-4 Alternate Patient ID
        // PID-5 Patient Name Family Name
        // PID-5.2 Patient Name Given Name
        // PID-5.6 Patient Name Degree
        // PID-5.7 Patient Name Type Code
        // PID-6 Mother's Maiden Name
        // PID-7 Date/Time Of Birth
        // PID-8 Administrative Sex
        // PID-9 Patient Alis Family Name.Surname
        // PID-10 Race Identifier
        // PID-11 Patient Address.Street Address.Street or Mailing Address
        // PID-11.2 Patient Address.Other Designation
        // PID-11.3 Patient Address.City
        // PID-11.4 Patient Address.State or Province
        // PID-11.5 Patient Address.Zip or Postal Code
        // PID-12 Country Code
        // PID-13 Phone Number.Home Telephone Number
        // PID-14 Phone Number.Business.Telephone Number
        // PID-15 Primary Language.Identifier
        // PID-16 Marital Status.Identifier
        // PID-17 Religion Identifier
        // PID-18 Patient Account Number.ID Number
        // PID-19 SSN NUMBER 社会保险号(自定)
        // PID-20 Driver's License Number-Patient.License Number 身份证件号(自定)
        // PID-21 Mother's Identifier.ID Number 医疗保险号(自定)
        // PID-22 Ethnic Group.Identifier
        // PID-23 Birth Place
        // PID-28 Nationality.Identifier
        // PID-32 Identity Reliability Code
        Patient patientDesc = new Patient();

        patientDesc.setPatientIds(convertor.getPatientIds());// 病人id

        // 身份识别信息PID4的特殊处理
        // 这里处理了关联ID的情况，多数情况下，这里处理的就是HIS的ID
        @SuppressWarnings("unused")
        List<ExtendForPerson> ExtendList = new ArrayList<ExtendForPerson>();
        List<PatientIdentifier> PatientList = new ArrayList<PatientIdentifier>();

        try
        {
            ExtendList = pixAdapter.ExtendFieldForPatient();

            int AlternatPatientIDCount = convertor.getAlternatePatientIdsSiz();

            if (AlternatPatientIDCount > 0)
            {
                PatientList = convertor.getAlternatePatientIds();

                for (ExtendForPerson mylist : ExtendList)
                {
                    if (mylist.getExtendfieldname() != null && mylist.getPid4fields() != null)
                    {
                        // 这个20是怎么来的？
                        if (mylist.getPid4fields() > 0 && mylist.getPid4fields() < 20)
                        {
                            int ExtendField = mylist.getPid4fields() - 1;

                            // System.out.println(ExtendField + " " +
                            // AlternatPatientIDCount);
                            if (ExtendField < AlternatPatientIDCount)
                            {
                                if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM6"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom6(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom6(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM7"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom7(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom7(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM8"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom8(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom8(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM9"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom9(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom9(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM13"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom13(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom13(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM14"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom14(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom14(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM15"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom15(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom15(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM18"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom18(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom18(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM19"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom19(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom19(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM20"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom20(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom20(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM21"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom21(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom21(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM22"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom22(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom22(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM23"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom23(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom23(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM24"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom24(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom24(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM25"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom25(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom25(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM26"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom26(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom26(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM27"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom27(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom27(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM28"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom28(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom28(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM29"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom29(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom29(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                                else if (mylist.getExtendfieldname().equalsIgnoreCase("CUSTOM30"))
                                {
                                    if (PatientList.get(ExtendField).getId() != null)
                                    {
                                        if (PatientList.get(ExtendField).getAssigningAuthority().getUniversalId() != null)
                                        {
                                            patientDesc.setCustom30(PatientList.get(ExtendField).getId() + "^" + PatientList.get(ExtendField).getAssigningAuthority().getUniversalId());
                                        }
                                        else
                                        {
                                            patientDesc.setCustom30(PatientList.get(ExtendField).getId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ExtendList = null;
            PatientList = null;

        }
        catch (PixManagerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 中文姓名
        patientDesc.setPatientName(convertor.getPatientName());

        patientDesc.setSickName(convertor.getPatientName());

        // 出生日期
        patientDesc.setBirthDateTime(convertor.getBirthDate());

        // 出生所在地的省
        patientDesc.setBirthProvince(convertor.getBirthProvince());

        // 出生所在地的市
        patientDesc.setBirthCity(convertor.getBirthCity());

        // 出生所在区县
        patientDesc.setBirthCounty(convertor.getBirthCounty());

        // 出生地
        patientDesc.setBirthPlace(convertor.getBirthAddress());

        // 出生地所在邮编
        patientDesc.setBirthZip(convertor.getBirhtZip());

        // 多胞胎
        patientDesc.setMulitpleBirthIndicator(convertor.getPID_24());

        // 出生次序
        patientDesc.setBirthOrder(convertor.getBirthOrder());

        // 母亲娘家姓
        patientDesc.setMothersMaidenName(convertor.getMotherMaidenName());

        // 社会保险号
        patientDesc.setSsn(convertor.getSsn());

        // 身份证号
        patientDesc.setIdentityNO(convertor.getDriversLicense().getLicenseNumber());

        // 市民卡号
        if (patientDesc.getCustom7() != null)
            patientDesc.setCitizenCard(patientDesc.getCustom7());

        // 医疗证号
        if (patientDesc.getCustom8() != null)
            patientDesc.setMedicalCertificate(patientDesc.getCustom8());

        // 医保个人编号
        if (patientDesc.getCustom9() != null)
            patientDesc.setMeicarePerson(patientDesc.getCustom9());

        // 老人证号
        if (patientDesc.getCustom13() != null)
            patientDesc.setElderCertificate(patientDesc.getCustom13());

        // 病历号
        if (patientDesc.getCustom14() != null)
            patientDesc.setOpcaseno(patientDesc.getCustom14());

        // 其它
        // 这几个是在做什么？
        if (patientDesc.getCustom15() != null)
            patientDesc.setCustom21(patientDesc.getCustom15());

        // 这几个是在做什么？
        if (patientDesc.getCustom18() != null)
            patientDesc.setHealthCard(patientDesc.getCustom18());

        // 这几个是在做什么？
        if (patientDesc.getCustom19() != null)
            patientDesc.setCustom23(patientDesc.getCustom19());

        // 这几个是在做什么？
        if (patientDesc.getCustom20() != null)
            patientDesc.setCustom24(patientDesc.getCustom20());

        // 医疗保险号
        patientDesc.setInsuranceNO(convertor.getPid21_1_1());

        // 医疗保险类型、医疗保险名称
        // 待定

        // 性别编码
        patientDesc.setAdministrativeSex(convertor.getSexType());

        // 性别编码名称
        patientDesc.setGenderName(convertor.getGenderName());

        // 性别编码系统
        patientDesc.setGenderDomain(convertor.getGenderDomain());

        // 民族编码
        patientDesc.setEthnicGroup(convertor.getEthnicGroup());

        // 民族编码名称
        patientDesc.setEthnicName(convertor.getEthnicGroupName());

        // 民族编码系统
        patientDesc.setEthincDomain(convertor.getEthnicGroupDomain());

        // 种族编码
        patientDesc.setRace(convertor.getRace());

        // 种族编码名称
        patientDesc.setRaceName(convertor.getRaceName());

        // 种族编码系统
        patientDesc.setRaceDomain(convertor.getRaceDomain());

        // 国籍编码
        patientDesc.setNationality(convertor.getNationality());

        // 国籍编码名称
        patientDesc.setNationalityName(convertor.getNationalityName());

        // 国籍编码系统
        patientDesc.setNationalityDomain(convertor.getNationalityDomain());

        // 语言编码
        patientDesc.setPrimaryLanguage(convertor.getPrimaryLanguage());

        // 宗教编码
        patientDesc.setReligion(convertor.getReligion());

        // 婚姻编码
        patientDesc.setMaritalStatus(convertor.getMaritalStatus());

        // 婚姻编码名称
        patientDesc.setMaritalStatusName(convertor.getMaritalStatusName());

        // 婚姻编码系统
        patientDesc.setMaritalDomain(convertor.getMaritalStatusDomain());

        if (patientDesc.getPatientName() != null)
        {
            // 教育程度编码
            patientDesc.setDegree(convertor.getPatientName().getDegree());

            // 教育程度编码名称
            patientDesc.setDegreeName(convertor.getPatientName().getDegreeName());

            // 教育编码系统
            patientDesc.setDegreeDomain(convertor.getPatientName().getDegreeDomain());

        }

        // 邮件地址
        patientDesc.setEmail(convertor.getEmail());

        // 居住地所在地省
        patientDesc.setHomeProvince(convertor.getHomeProvince());

        // 居住地所在地市
        patientDesc.setHomeCity(convertor.getHomeCity());

        // 居住地所在地区县
        patientDesc.setHomeCounty(convertor.getHomeCounty());

        // 居住地所在地邮编
        patientDesc.setHomeZip(convertor.getHomeZip());

        // 居住地址
        patientDesc.setHomeAddress(convertor.getHomeAddress());

        // 居住街道
        patientDesc.setHomeStreet(convertor.getHomeStreet());

        // 户口所在地省
        patientDesc.setRegisteredProvince(convertor.getRegisteredProvince());

        // 户口所在地市
        patientDesc.setRegisteredCity(convertor.getRegisteredCity());

        // 户口所在地区县
        patientDesc.setRegisteredCounty(convertor.getRegisteredCounty());

        // 户口所在地邮编
        patientDesc.setRegisteredZip(convertor.getRegisteredZip());

        // 户口地址
        patientDesc.setRegisteredAddress(convertor.getRegisteredAddress());

        // 户口街道
        patientDesc.setRegisteredStreet(convertor.getRegisteredStreet());

        // 籍贯所在地省
        patientDesc.setNativeProvince(convertor.getNativeProvince());

        // 籍贯所在地市
        patientDesc.setNativeCity(convertor.getNativeCity());

        // 职业编码
        patientDesc.setProfession(convertor.getPID12());

        // 职业名称
        patientDesc.setProfessionName(convertor.getProfessionName());

        // 职业编码系统
        patientDesc.setProfessionDomain(convertor.getProfessionDomain());

        // 工作单位
        patientDesc.setCompany(convertor.getCompany());

        // 工作邮编
        patientDesc.setWorkZip(convertor.getWorkZip());

        // 工作地址
        patientDesc.setWorkAddress(convertor.getWorkAddress());

        // 家庭电话
        patientDesc.setHomePhone(convertor.getHomePhone());

        // 私人电话
        patientDesc.setPrivateNumber(convertor.getPrivatePhone());

        // 单位电话
        patientDesc.setWorkNumber(convertor.getWorkPhone());

        // 监护人
        patientDesc.setGuardianPerson(convertor.getPID9());

        // 保密级别
        patientDesc.setVip(convertor.getVip());

        // 死亡时间
        patientDesc.setDeathDate(convertor.getDeathDate());

        // 死亡标志
        patientDesc.setDeathIndicator(convertor.getDeathIndicator());

        // 其它
        patientDesc.setPatientAliases(convertor.getPatientAliases());

        patientDesc.setPatientAccountNumber(convertor.getpatientAccountNumber());

        patientDesc.setCitizenship(convertor.getCitizenShip());

        patientDesc.setVisits(convertor.getVisitList());

        patientDesc.setVipIndicator(convertor.getVipIndicator());

        patientDesc.setNextOfKin(convertor.getNextOfKin());

        patientDesc.setInsuranceType(convertor.getPid21_2_1());

        patientDesc.setInsuranceName(convertor.getPid21_3_1());

        patientDesc.setAccountLocked(convertor.getPid21_4_1());

        patientDesc.setAccountLockedDate(convertor.getPid21_5_1());

        patientDesc.setBirthTime(convertor.getPid21_6_1());

        patientDesc.setCardType(convertor.getCardType());

        try
        {
            patientDesc.setAllergy(convertor.getAllergy());
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // PV1注释:
        // PV1-2:就诊类型
        // PV1-3:患者现位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
        // PV1-6:患者前位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
        // PV1-7:接诊医生
        // PV1-8:转诊医生
        // PV1-9:会诊医生
        // PV1-10:医院服务
        // PV1-11:患者临时位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
        // PV1-18:病人类型
        // PV1-44:入院日期
        // PV1-45:出院日期
        patientDesc.setPatCategory(convertor.getPV1_2());

        patientDesc.setPatCurrentPointOfCare(convertor.getPV1_3_1());

        patientDesc.setPatCurrentRoom(convertor.getPV1_3_2());

        patientDesc.setPatCurrentBed(convertor.getPV1_3_3());

        patientDesc.setPatCuurentDep(convertor.getPV1_3_4());

        patientDesc.setPatCurrentPositionStatus(convertor.getPV1_3_5());

        patientDesc.setPatCurrentPositionType(convertor.getPV1_3_6());

        patientDesc.setPatCurrentBuilding(convertor.getPV1_3_7());

        patientDesc.setPatCurrentFloor(convertor.getPV1_3_8());

        patientDesc.setPatCuurentDescription(convertor.getPV1_3_9());

        patientDesc.setPatAdmissionType(convertor.getPV1_4_1());

        patientDesc.setPatAdmissionNumber(convertor.getPV1_5_1());

        patientDesc.setPatFormerPointOfCare(convertor.getPV1_6_1());

        patientDesc.setPatFormerRoom(convertor.getPV1_6_2());

        patientDesc.setPatFormerBed(convertor.getPV1_6_3());

        patientDesc.setPatFormerDep(convertor.getPV1_6_4());

        patientDesc.setPatFormerPositionStatus(convertor.getPV1_6_5());

        patientDesc.setPatFormerPositionType(convertor.getPV1_6_6());

        patientDesc.setPatFormerBuilding(convertor.getPV1_6_7());

        patientDesc.setPatFormerFloor(convertor.getPV1_6_8());

        patientDesc.setPatFormerDescription(convertor.getPV1_6_9());

        patientDesc.setAdmissionsDoctor(convertor.getPV1_7_2());

        patientDesc.setAdmissionsDoctorId(convertor.getPV1_7_1());

        patientDesc.setReferringDoctorId(convertor.getPV1_8_1());

        patientDesc.setReferringDoctor(convertor.getPV1_8_2());

        patientDesc.setConsultationDoctorId(convertor.getPV1_9_1());

        patientDesc.setConsultationDoctor(convertor.getPV1_9_2());

        patientDesc.setHospitalService(convertor.getPV1_10());

        patientDesc.setPatTempPointOfCare(convertor.getPV1_11_1());

        patientDesc.setPatTempRoom(convertor.getPV1_11_2());

        patientDesc.setPatTempBed(convertor.getPV1_11_3());

        patientDesc.setPatTempDep(convertor.getPV1_11_4());

        patientDesc.setPatTempPositionStatus(convertor.getPV1_11_5());

        patientDesc.setPatTempPositionType(convertor.getPV1_11_6());

        patientDesc.setPatTempBuilding(convertor.getPV1_11_7());

        patientDesc.setPatTempFloor(convertor.getPV1_11_8());

        patientDesc.setPatTempDescription(convertor.getPV1_11_9());

        patientDesc.setPatAdmissionTest(convertor.getPV1_12_1());

        patientDesc.setPatIpTimes(convertor.getPV1_13_1());

        patientDesc.setPatAdmissionSource(convertor.getPV1_14_1());

        // 离院处置名称
        patientDesc.setDischargeName(convertor.getPV1_15_1());

        // 离院处置编码系统
        patientDesc.setDischargeDomain(convertor.getPV1_15_2_1());

        // 入院时情况名称
        patientDesc.setAdmissionName(convertor.getPV1_15_3_1());

        // 入院时情况编码系统
        patientDesc.setAdmissionDomain(convertor.getPV1_15_4_1());

        // 病人住院状态名称
        patientDesc.setIpStatusName(convertor.getPV1_15_5_1());

        // 病人住院状态编码系统
        patientDesc.setIpStatusDomain(convertor.getPV1_15_6_1());

        // 病例分型名称
        patientDesc.setDificultyName(convertor.getPV1_15_7_1());

        // 病例分型编码系统
        patientDesc.setDificultyDomain(convertor.getPV1_15_8_1());

        // 入院途径名称
        patientDesc.setAdmissionSourceName(convertor.getPV1_15_9_1());

        // 入院途径编码系统
        patientDesc.setAdmissionSourceDomain(convertor.getPV1_15_10_1());

        // 支付方式名称
        patientDesc.setAccountStatusName(convertor.getPV1_15_11_1());

        // 支付方式编码系统
        patientDesc.setAccountStatusDomain(convertor.getPV1_15_12_1());

        // 患者类别编码
        patientDesc.setPatCategoryName(convertor.getPV1_15_13_1());

        // 患者类别编码系统
        patientDesc.setPatCategorySystem(convertor.getPV1_15_14_1());

        // 患者类型编码
        patientDesc.setPatientClassName(convertor.getPV1_15_15_1());

        // 患者类型编码系统
        patientDesc.setPatientClassDomain(convertor.getPV1_15_16_1());

        // 病人来源
        patientDesc.setPatientSourceName(convertor.getPV1_15_17_1());

        // 病人再次入院标识
        patientDesc.setPatReAdmission(convertor.getPV1_15_18_1());

        // 是否急疹转住院
        patientDesc.setIsEmergency(convertor.getPV1_15_19_1());

        patientDesc.setDiagnoseIcd(convertor.getPV1_15_20_1());

        patientDesc.setDiagnoseName(convertor.getPV1_15_21_1());

        patientDesc.setPatVip(convertor.getPV1_16_1());

        patientDesc.setPatAdmissionDoctors(convertor.getPV1_17_2());

        patientDesc.setPatAdmissionDoctorsId(convertor.getPV1_17_1());

        patientDesc.setPatientClass(convertor.getPV1_18());

        patientDesc.setPatientId(convertor.getPV1_19());

        // 流水域名
        patientDesc.setCustom2(convertor.getPV1_19_4_1());

        patientDesc.setCustom3(convertor.getPV1_19_4_2());

        patientDesc.setCustom4(convertor.getPV1_19_4_3());

        patientDesc.setPatFinancialClass(convertor.getPV1_20());

        patientDesc.setRoomBedCostPrice(convertor.getPV1_21());

        patientDesc.setCourtesyCode(convertor.getPV1_22());

        patientDesc.setCreditRating(convertor.getPV1_23());

        patientDesc.setContractCode(convertor.getPV1_24());

        patientDesc.setContractCreateDate(convertor.getPV1_25());

        patientDesc.setContractPrice(convertor.getPV1_26());

        patientDesc.setContractTime(convertor.getPV1_27());

        patientDesc.setInterestRateCode(convertor.getpv1_28());

        patientDesc.setBadDebts(convertor.getPV1_29());

        patientDesc.setBadDebtsCreateDate(convertor.getPV1_30());

        patientDesc.setBadDebtsCode(convertor.getPV1_31());

        patientDesc.setBadDebtsPrice(convertor.getPV1_32());

        patientDesc.setBadDebtsRestorePrice(convertor.getPV1_33());

        patientDesc.setPatAccountVoided(convertor.getPV1_34());

        patientDesc.setPatAccountVoidedDate(convertor.getPV1_35());

        patientDesc.setPatDischargeDisposition(convertor.getPV1_36());

        patientDesc.setPatDischargeLocation(convertor.getPV1_37());

        patientDesc.setPatDietType(convertor.getPV1_38());

        patientDesc.setPatServiceAgencies(convertor.getPV1_39());

        patientDesc.setPatBedStatus(convertor.getPV1_40());

        patientDesc.setPatAccountStatus(convertor.getPV1_41());

        // 限额
        String LimitAmount = convertor.getPV1_42_1();

        if (LimitAmount != null && LimitAmount.length() > 0)
        {
            String[] Amount = LimitAmount.split(",");

            // if (Amount[0].length()>0) patientDesc.setMedicinelimitamount(new
            // BigDecimal(Amount[0]));

            // if (Amount[1].length()>0) patientDesc.setSickbedlimitamount(new
            // BigDecimal(Amount[1]));

            // if (Amount[2].length()>0) patientDesc.setExaminelimitamount(new
            // BigDecimal(Amount[2]));

            // if (Amount[3].length()>0) patientDesc.setCurelimitamount(new
            // BigDecimal(Amount[3]));
        }

        // 前缀
        patientDesc.setPrefix(convertor.getPV1_42_2());

        patientDesc.setPatDeterBed(convertor.getPV1_42_3());

        patientDesc.setPatDeterDep(convertor.getPV1_42_4());

        // 护理
        patientDesc.setTend(convertor.getPV1_42_5());

        // 护士ID
        patientDesc.setPatNurseCode(convertor.getPV1_42_6());

        // 护士姓名
        patientDesc.setPatNurseName(convertor.getPV1_42_7());

        // 操作员
        patientDesc.setOperCode(convertor.getPV1_42_8());

        // 操作日期
        patientDesc.setOperDate(convertor.getPV1_42_9());

        patientDesc.setPatForTempPointOfCare(convertor.getPV1_43_1());

        patientDesc.setPatForTempRoom(convertor.getPV1_43_2());

        patientDesc.setPatForTempBed(convertor.getPV1_43_3());

        patientDesc.setPatForTempDep(convertor.getPV1_43_4());

        patientDesc.setPatForTempPositionStatus(convertor.getPV1_43_5());

        patientDesc.setPatForTempPositionType(convertor.getPV1_43_6());

        patientDesc.setPatForTempBuilding(convertor.getPV1_43_7());

        patientDesc.setPatForTempFloor(convertor.getPV1_43_8());

        patientDesc.setPatForTempDescription(convertor.getPV1_43_9());

        patientDesc.setAdmitDate(convertor.getPV1_44());

        patientDesc.setDischargeDate(convertor.getPV1_45());

        patientDesc.setPatDifference(convertor.getPV1_46());

        patientDesc.setPatTotalCost(convertor.getPV1_47());

        patientDesc.setPatTotalDispatch(convertor.getPV1_48());

        patientDesc.setPatTotalAmountPayable(convertor.getPV1_49());

        // 婴儿标志
        patientDesc.setBabyFlag(convertor.getPV1_50());

        // 母亲姓名
        patientDesc.setMothersName(convertor.getPV1_50_2());

        // 母亲ID
        patientDesc.setMothersID(convertor.getPV1_50_2());

        // 母亲域
        patientDesc.setMothersDomain(convertor.getPV1_50_3());

        // 母亲流水ID
        patientDesc.setMothersFlowID(convertor.getPV1_50_6_2());

        // 母亲流水域
        patientDesc.setMothersFlowDomain(convertor.getPV1_50_6_3());

        // 入院体重
        patientDesc.setAdmitWeight(convertor.getPV1_50_3());

        // 入院体重单位
        patientDesc.setAdmitWeightUnit(convertor.getPV1_50_4());

        // 出生体重
        patientDesc.setBirthWeight(convertor.getPV1_50_5());

        // 出生体重单位
        patientDesc.setBabyWeightUnit(convertor.getPV1_50_6());

        patientDesc.setPatVisitLogo(convertor.getPV1_51());

        patientDesc.setOldPatientId(convertor.getPV1_52_1_1());

        patientDesc.setOldPatientDomain(convertor.getPV1_52_2_1());

        patientDesc.setOldVisitFlowId(convertor.getPV1_52_3_1());

        patientDesc.setOldVisitFlowDomain(convertor.getPV1_52_4_1());

        patientDesc.setOldStatus(convertor.getPV1_52_5_1());

        patientDesc.setOldInfo(convertor.getPV1_52_6_1());

        patientDesc.setNoonCode(convertor.getPV1_52_1_1_1());

        patientDesc.setPaykindCode(convertor.getPV1_52_1_2_1());

        patientDesc.setPaykindName(convertor.getPV1_52_1_3_1());

        patientDesc.setSchemaNo(convertor.getPV1_52_1_4_1());

        patientDesc.setOrderNo(convertor.getPV1_52_1_5_1());

        patientDesc.setSeeNo(convertor.getPV1_52_1_6_1());

        patientDesc.setBeginTime(convertor.getPV1_52_2_1_1());

        patientDesc.setEndTime(convertor.getPV1_52_2_2_1());

        patientDesc.setYnBook(convertor.getPV1_52_2_3_1());

        patientDesc.setYnfr(convertor.getPV1_52_2_4_1());

        patientDesc.setAppendFlag(convertor.getPV1_52_2_5_1());

        patientDesc.setYnSee(convertor.getPV1_52_2_6_1());

        patientDesc.setSeeDate(convertor.getPV1_52_3_1_1());

        patientDesc.setTriageFlag(convertor.getPV1_52_3_2_1());

        patientDesc.setTriageOpcd(convertor.getPV1_52_3_3_1());

        patientDesc.setTriageDate(convertor.getPV1_52_3_4_1());

        patientDesc.setSeeDpcd(convertor.getPV1_52_3_5_1());

        patientDesc.setSeeDocd(convertor.getPV1_52_3_6_1());

        patientDesc.setOutPatientStatusA(convertor.getPV1_52_4_1_1());

        patientDesc.setOutPatientStatusB(convertor.getPV1_52_4_2_1());

        patientDesc.setOutPatientStatusC(convertor.getPV1_52_4_3_1());

        patientDesc.setInPatientStatusA(convertor.getPV1_52_4_4_1());

        patientDesc.setInPatientStatusB(convertor.getPV1_52_4_5_1());

        patientDesc.setInPatientStatusC(convertor.getPV1_52_4_6_1());

        return patientDesc;
    }

}
