package com.ats.apixpdq.impl.v2.hl7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ats.aexchange.actorconfig.net.IBaseDescription;
import com.ats.aexchange.datamodel.Address;
import com.ats.aexchange.datamodel.DriversLicense;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.NextOfKin;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.datamodel.PersonName;
import com.ats.aexchange.datamodel.PhoneNumber;
import com.ats.aexchange.datamodel.Problem;
import com.ats.aexchange.datamodel.Provider;
import com.ats.aexchange.datamodel.SharedEnums;
import com.ats.aexchange.datamodel.TAllergy;
import com.ats.aexchange.datamodel.Visit;
import com.ats.aexchange.datamodel.SharedEnums.AddressType;
import com.ats.aexchange.datamodel.SharedEnums.PhoneType;
import com.ats.aexchange.datamodel.SharedEnums.SexType;
import com.ats.aexchange.utils.DateUtil;
import com.ats.aexchange.utils.StringUtil;

import com.ats.apixpdq.api.IPixPdqAdapter;
import com.ats.apixpdq.common.AssigningAuthorityUtil;
import com.globalmentor.log.Log;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Structure;
import ca.uhn.hl7v2.model.v231.datatype.CX;
import ca.uhn.hl7v2.model.v231.datatype.IS;
import ca.uhn.hl7v2.model.v231.datatype.SI;
import ca.uhn.hl7v2.model.v231.datatype.XAD;
import ca.uhn.hl7v2.model.v231.datatype.XCN;
import ca.uhn.hl7v2.model.v231.datatype.XPN;
import ca.uhn.hl7v2.model.v231.datatype.XTN;
import ca.uhn.hl7v2.model.v231.group.ADT_A39_PIDPD1MRGPV1;
import ca.uhn.hl7v2.model.v231.message.ADT_A39;
import ca.uhn.hl7v2.model.v231.segment.DG1;
import ca.uhn.hl7v2.model.v231.segment.MRG;
import ca.uhn.hl7v2.model.v231.segment.MSH;
import ca.uhn.hl7v2.model.v231.segment.NK1;
import ca.uhn.hl7v2.model.v231.segment.PD1;
import ca.uhn.hl7v2.model.v231.segment.PID;
import ca.uhn.hl7v2.model.v231.segment.PV1;
import ca.uhn.hl7v2.model.v231.segment.PV2;
import ca.uhn.hl7v2.model.v231.segment.AL1;


/**
 * This class parses the PIX Feed message
 *
 * @author Rasakannu Palaniyandi
 * @date Nov 25, 2008
 */
public class HL7v231ToBaseConvertor{

    private MSH msh;
    private PID pid;
    private PV1 pv1;
    private PV2 pv2;
    private PD1 pd1;
    private DG1 dg1;
    private MRG mrg;
    private NK1 myNK1;
    private NK1 nk1Array[];
    private AL1 al1Array[];
    private ADT_A39_PIDPD1MRGPV1 pidpd1mrgpv1;

    IBaseDescription description;
    IPixPdqAdapter adapter;

    public HL7v231ToBaseConvertor(Message in, IBaseDescription description, IPixPdqAdapter adapter) {
    	this.description = description;
    	this.adapter = adapter;
    	try {
        	msh = (MSH)in.get("MSH");

        	 if (in instanceof ADT_A39) {
        		 // this is for merge patients
        		 pidpd1mrgpv1 = (ADT_A39_PIDPD1MRGPV1)in.get("PIDPD1MRGPV1");
        		 pid = (PID)pidpd1mrgpv1.get("PID");
        		 Log.debug(pid);
                 pv1 = (PV1)pidpd1mrgpv1.get("PV1");
                 try {
                    pv2 = (PV2)pidpd1mrgpv1.get("PV2");
                 } catch (HL7Exception e) {
                    pv2 = null; //Find no PV2, ok, it is fine.
                 }
                 try {
                     dg1 = (DG1)pidpd1mrgpv1.get("DG1");
                 } catch (HL7Exception e) {
                     dg1 = null;
                 }
                 try {
                     pd1 = (PD1)pidpd1mrgpv1.get("PD1");
                 } catch (HL7Exception e) {
                     pd1 = null;
                 }
                 try {
                 	mrg = (MRG)pidpd1mrgpv1.get("MRG");
     			} catch (HL7Exception e) {
     				mrg = null;
     			}
             	 }
         else{
		    pid = (PID)in.get("PID");
		    //panmin -2012-11-28
		    try {
		    	pv1 = (PV1)in.get("PV1");
		    } catch(HL7Exception e){
		    	pv1 = null;
		    }
            try {
               pv2 = (PV2)in.get("PV2");
            } catch (HL7Exception e) {
               pv2 = null; //Find no PV2, ok, it is fine.
            }
            try {
                dg1 = (DG1)in.get("DG1");
            } catch (HL7Exception e) {
                dg1 = null;
            }
            try {
                pd1 = (PD1)in.get("PD1");
            } catch (HL7Exception e) {
                pd1 = null;
            }
            try {
            	mrg = (MRG)in.get("MRG");
			} catch (HL7Exception e) {
				mrg = null;
			}
            try {
                Structure[] s = in.getAll("NK12");
                nk1Array = new NK1[s.length];
                for (int i = 0; i < s.length; i++) {
                    nk1Array[i] = (NK1) s[i];
                }
            } catch (HL7Exception e) {
               nk1Array = null; //Find no NK1, ok, it is fine.
            }
            try {
                Structure[] s = in.getAll("AL1");
                al1Array = new AL1[s.length];
                for (int i = 0; i < s.length; i++) {
                	al1Array[i] = (AL1) s[i];
                }
            } catch (HL7Exception e) {
            	al1Array = null; //Find no AL1, ok, it is fine.
            }
        	 }

        }catch (HL7Exception e) {
              throw new ExceptionInInitializerError(e);
        }
        if(msh == null || pid == null) {
            throw new ExceptionInInitializerError();
        }
    }

    /**
	 * Gets a list of Alternate patient identifiers from the PID segment.
	 *
	 * @return a list of {@link PatientIdentifier}
	 */
    public List<PatientIdentifier> getAlternatePatientIds(){
    	List<PatientIdentifier> patientIds = new ArrayList<PatientIdentifier>();
    	
       
    	CX[] cxs = pid.getAlternatePatientIDPID();
    	for(CX cx: cxs) {
            PatientIdentifier identifier =new PatientIdentifier();
    		Identifier assignAuth= new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(),cx.getAssigningAuthority().getUniversalID().getValue(),cx.getAssigningAuthority().getUniversalIDType().getValue());
            Identifier assignFac =new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(),cx.getAssigningFacility().getUniversalID().getValue(),cx.getAssigningFacility().getUniversalIDType().getValue());
    		//Need to reconcile assigning authority to fill up any missing components
            Identifier reconciledAssignAuth = AssigningAuthorityUtil.reconcileIdentifier(assignAuth, description, adapter);
            identifier.setAssigningAuthority(reconciledAssignAuth);
        	identifier.setAssigningFacility(assignFac);
    		identifier.setId(cx.getID().getValue());
            identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
           // identifier.setEffectiveDate(cx.get);
        //	identifier.setExpirationDate(expirationDate);
        	patientIds.add(identifier);
        }
    	return patientIds;
    }
    
    public Integer getAlternatePatientIdsSiz(){return pid.getAlternatePatientIDPIDReps();}
    

    /**
	 * Gets a list of patient identifiers from the PID segment.
	 *
	 * @return a list of {@link PatientIdentifier}
	 */
    public List<PatientIdentifier> getPatientIds(){
    	List<PatientIdentifier> patientIds = new ArrayList<PatientIdentifier>();
    	
       
    	CX[] cxs = pid.getPatientIdentifierList();
    	for(CX cx: cxs) {
            PatientIdentifier identifier =new PatientIdentifier();
    		Identifier assignAuth= new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(),cx.getAssigningAuthority().getUniversalID().getValue(),cx.getAssigningAuthority().getUniversalIDType().getValue());
            Identifier assignFac =new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(),cx.getAssigningFacility().getUniversalID().getValue(),cx.getAssigningFacility().getUniversalIDType().getValue());
    		//Need to reconcile assigning authority to fill up any missing components
            Identifier reconciledAssignAuth = AssigningAuthorityUtil.reconcileIdentifier(assignAuth, description, adapter);
            identifier.setAssigningAuthority(reconciledAssignAuth);
        	identifier.setAssigningFacility(assignFac);
    		identifier.setId(cx.getID().getValue());
            identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
           // identifier.setEffectiveDate(cx.get);
        //	identifier.setExpirationDate(expirationDate);
        	patientIds.add(identifier);
        }
    	return patientIds;
    }
    
    //DG1-信息获取

    //PV1-信息获取
    //pm-2012-07-11
    
    //PV1-2: 病人类型
    public String getPV1_2()
    {   	
    	return pv1.getPv12_PatientClass().getValue();
    }
    
  //PV1-3:患者现位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
    public String getPV1_3_1()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl1_PointOfCare().getValue();
    }
    
    public String getPV1_3_2()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl2_Room().getValue();
    }
    
    public String getPV1_3_3()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl3_Bed().getValue();
    }
    
    public String getPV1_3_4()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl4_Facility().getHd1_NamespaceID().getValue();
    }
    
    public String getPV1_3_5()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl5_LocationStatus().getValue();
    }
    
    public String getPV1_3_6()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl6_PersonLocationType().getValue();
    }
    
    public String getPV1_3_7()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl7_Building().getValue();
    }
    
    public String getPV1_3_8()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl8_Floor().getValue();
    }
    
    public String getPV1_3_9()
    {
    	return pv1.getPv13_AssignedPatientLocation().getPl9_LocationDescription().getValue();
    }
    
    //PV1-4 PAT_ADMINSSION_TYPE
    public String getPV1_4_1()
    {
    	return pv1.getPv14_AdmissionType().getValue();
    }
    
    //PV1-5 PAT_ADMISSION_NUMBER
    public String getPV1_5_1()
    {
    	return pv1.getPv15_PreadmitNumber().getCx1_ID().getValue();
    }
    
    
    //PV1-6 患者前位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
    public String getPV1_6_1()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl1_PointOfCare().getValue();
    }
    
    public String getPV1_6_2()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl2_Room().getValue();
    }
    
    public String getPV1_6_3()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl3_Bed().getValue();
    }
    
    public String getPV1_6_4()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl4_Facility().getHd1_NamespaceID().getValue();
    }
    
    public String getPV1_6_5()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl5_LocationStatus().getValue();
    }
    
    public String getPV1_6_6()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl6_PersonLocationType().getValue();
    }
    
    public String getPV1_6_7()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl7_Building().getValue();
    }
    
    public String getPV1_6_8()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl8_Floor().getValue();
    }
    
    public String getPV1_6_9()
    {
    	return pv1.getPv16_PriorPatientLocation().getPl9_LocationDescription().getValue();
    }
    
    //PV1-7:接诊医生:工号,姓名
    public String getPV1_7_1()
    {
    	return pv1.getPv17_AttendingDoctor(0).getXcn1_IDNumber().getValue();
    }
    
    public String getPV1_7_2()
    {
    	//String TempString1=pv1.getPv17_AttendingDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv17_AttendingDoctor(0).getXcn2_FamilyLastName().getFamilyName().getValue();
    	
    	return TempString2 ;
    }
    
    //PV1-8:转诊医生
    public String getPV1_8_1()
    {
    	return pv1.getPv18_ReferringDoctor(0).getXcn1_IDNumber().getValue();
    }
    
    public String getPV1_8_2()
    {
    	//String TempString1=pv1.getPv18_ReferringDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv18_ReferringDoctor(0).getXcn2_FamilyLastName().getFamilyName().getValue();
    	
    	return TempString2;
    }
    
  //PV1-9:会诊医生
    public String getPV1_9_1()
    {
    	return pv1.getPv19_ConsultingDoctor(0).getXcn1_IDNumber().getValue();
    }
    
    public String getPV1_9_2()
    {
    	//String TempString1=pv1.getPv19_ConsultingDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv19_ConsultingDoctor(0).getXcn2_FamilyLastName().getFamilyName().getValue();
    	
    	return TempString2;
    }
    
    //PV1-10:医院服务
    public String getPV1_10()
    {
    	return pv1.getPv110_HospitalService().getValue();
    }
    
    //PV1-11:患者临时位置--医疗点|病房|床位|机构|位置状况|个人位置类型|楼号|楼层|位置描述
    public String getPV1_11_1()
    {
    	return pv1.getPv111_TemporaryLocation().getPl1_PointOfCare().getValue();
    }
    
    public String getPV1_11_2()
    {
    	return pv1.getPv111_TemporaryLocation().getPl2_Room().getValue();
    }
    
    public String getPV1_11_3()
    {
    	return pv1.getPv111_TemporaryLocation().getPl3_Bed().getValue();
    }
    
    public String getPV1_11_4()
    {
    	return pv1.getPv111_TemporaryLocation().getPl4_Facility().getHd1_NamespaceID().getValue();
    }
    
    public String getPV1_11_5()
    {
    	return pv1.getPv111_TemporaryLocation().getPl5_LocationStatus().getValue();
    }
    
    public String getPV1_11_6()
    {
    	return pv1.getPv111_TemporaryLocation().getPl6_PersonLocationType().getValue();
    }
    
    public String getPV1_11_7()
    {
    	return pv1.getPv111_TemporaryLocation().getPl7_Building().getValue();
    }
    
    public String getPV1_11_8()
    {
    	return pv1.getPv111_TemporaryLocation().getPl8_Floor().getValue();
    }
    
    public String getPV1_11_9()
    {
    	return pv1.getPv111_TemporaryLocation().getPl9_LocationDescription().getValue();
    }
    
    //PV1-12:PAT_ADMISSION_TEST
    public String getPV1_12_1()
    {
    	return pv1.getPv112_PreadmitTestIndicator().getValue();
    }
    
    //PV1-13:PAT_RE_ADMISSION
    public String getPV1_13_1()
    {
    	return pv1.getPv113_ReAdmissionIndicator().getValue();
    }
    
    //PV1-14:PAT_ADMISSION_SOURCE
    public String getPV1_14_1()
    {
    	return pv1.getPv114_AdmitSource().getValue();
    }
    
    //PV1-15:PAT_AMBULATORY_STATUS
    public String getPV1_15_1()
    {
    	return pv1.getPv115_AmbulatoryStatus(0).getValue();
    }
    
    //离院处置编码系统
    public String getPV1_15_2_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=1)
    	{
    		return pv1.getPv115_AmbulatoryStatus(1).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //入院时情况名称
    public String getPV1_15_3_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=2)
    	{
    		return pv1.getPv115_AmbulatoryStatus(2).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //入院时情况编码系统
    public String getPV1_15_4_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=3)
    	{
    		return pv1.getPv115_AmbulatoryStatus(3).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //病人住院状态名称
    public String getPV1_15_5_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=4)
    	{
    		return pv1.getPv115_AmbulatoryStatus(4).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //病人住院状态编码系统
    public String getPV1_15_6_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=5)
    	{
    		return pv1.getPv115_AmbulatoryStatus(5).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //病例分型名称
    public String getPV1_15_7_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=6)
    	{
    		return pv1.getPv115_AmbulatoryStatus(6).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //病例分型编码系统
    public String getPV1_15_8_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=7)
    	{
    		return pv1.getPv115_AmbulatoryStatus(7).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //入院途径名称
    public String getPV1_15_9_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=8)
    	{
    		return pv1.getPv115_AmbulatoryStatus(8).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //入院途径编码系统
    public String getPV1_15_10_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=9)
    	{
    		return pv1.getPv115_AmbulatoryStatus(9).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //支付方式名称
    public String getPV1_15_11_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=10)
    	{
    		return pv1.getPv115_AmbulatoryStatus(10).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //支付方式编码系统
    public String getPV1_15_12_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=11)
    	{
    		return pv1.getPv115_AmbulatoryStatus(11).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //患者类别编码
    public String getPV1_15_13_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=12)
    	{
    		return pv1.getPv115_AmbulatoryStatus(12).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //患者类别编码系统
    public String getPV1_15_14_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=13)
    	{
    		return pv1.getPv115_AmbulatoryStatus(13).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //患者类型编码
    public String getPV1_15_15_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=14)
    	{
    		return pv1.getPv115_AmbulatoryStatus(14).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //患者类型编码系统
    public String getPV1_15_16_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=15)
    	{
    		return pv1.getPv115_AmbulatoryStatus(15).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //病人来源
    public String getPV1_15_17_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=16)
    	{
    		return pv1.getPv115_AmbulatoryStatus(16).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
  //病人再次入院标识
    public String getPV1_15_18_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=17)
    	{
    		return pv1.getPv115_AmbulatoryStatus(17).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //是否急疹转住院
    public String getPV1_15_19_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=18)
    	{
    		return pv1.getPv115_AmbulatoryStatus(18).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //诊断编码
    public String getPV1_15_20_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=19)
    	{
    		return pv1.getPv115_AmbulatoryStatus(19).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //诊断名称
    public String getPV1_15_21_1()
    {
    	if(pv1.getPv115_AmbulatoryStatusReps()>=20)
    	{
    		return pv1.getPv115_AmbulatoryStatus(20).getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //PV1-16:PAT_VIP
    public String getPV1_16_1()
    {
    	return pv1.getPv116_VIPIndicator().getValue();
    }
    
    //PV1-17_1:PAT_ADMISSION_DOCTORS_ID
    public String getPV1_17_1()
    {
    	return pv1.getPv117_AdmittingDoctor(0).getXcn1_IDNumber().getValue();
    }
    
    //PV1_17_2:PAT_ADMISSION_DOCTORS
    public String getPV1_17_2()
    {
    	//String TempString1=pv1.getPv117_AdmittingDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv117_AdmittingDoctor(0).getXcn2_FamilyLastName().getFamilyName().getValue();
    	
    	return TempString2;
    }
    
    //PV1-18:patient_class
    public String getPV1_18()
    {
    	return pv1.getPv118_PatientType().getValue();

    }
    
    //pv1-19:PATIENT_ID
    public String getPV1_19()
    {
    	return pv1.getPv119_VisitNumber().getCx1_ID().getValue();
    }
    
    public String getPV1_19_4_1()
    {
    	return pv1.getPv119_VisitNumber().getCx4_AssigningAuthority().getHd1_NamespaceID().getValue();
    }
    
    public String getPV1_19_4_2()
    {
    	return pv1.getPv119_VisitNumber().getCx4_AssigningAuthority().getHd2_UniversalID().getValue();
    }
    
    public String getPV1_19_4_3()
    {
    	return pv1.getPv119_VisitNumber().getCx4_AssigningAuthority().getHd3_UniversalIDType().getValue();
    }
    
    
    
    //PV1-20:PAT_FINANCIAL_CLASS
    public String getPV1_20()
    {
    	return pv1.getPv120_FinancialClass(0).getFc1_FinancialClass().getValue();
    }
    
    //pv1-21:ROOM_BED_COST_PRICE
    public String getPV1_21()
    {
    	return pv1.getPv121_ChargePriceIndicator().getValue();
    }
    
    //PV1-22:COURTESY_CODE
    public String getPV1_22()
    {
    	return pv1.getPv122_CourtesyCode().getValue();
    }
    
    //PV1-23:CREDIT_RATING
    public String getPV1_23()
    {
    	return pv1.getPv123_CreditRating().getValue();
    }
    
    //PV1-24:CONTRACT_CODE
    public String getPV1_24()
    {
    	return pv1.getPv124_ContractCode(0).getValue();
    }
    
    //PV1-25:CONTRACT_CREATE_DATE
    public String getPV1_25()
    {
    	return pv1.getPv125_ContractEffectiveDate(0).getValue();
    }
    
    //pv1-26:CONTRACT_PRICE
    public String getPV1_26()
    {
    	return pv1.getPv126_ContractAmount(0).getValue();
    }
    
    //PV1-27:CONTRACT_TIME
    public String getPV1_27()
    {
    	return pv1.getPv127_ContractPeriod(0).getValue();
    }
    
    //PV1-28:INTEREST_RATE_CODE
    public String getpv1_28()
    {
    	return pv1.getPv128_InterestCode().getValue();
    }
    
    //PV1-29:BAD_DEBTS
    public String getPV1_29()
    {
    	return pv1.getPv129_TransferToBadDebtCode().getValue();
    }
    
    //PV1-30:BAD_DEBTS_CREATE_DATE
    public String getPV1_30()
    {
    	return pv1.getPv130_TransferToBadDebtDate().getValue();
    }
    
    //PV1-31:BAD_DEBTS_CODE
    public String getPV1_31()
    {
    	return pv1.getPv131_BadDebtAgencyCode().getValue();
    }
    
    //PV1-32:BAD_DEBTS_PRICE
    public String getPV1_32()
    {
    	return pv1.getPv132_BadDebtTransferAmount().getValue();
    }
    
    //PV1-33:BAD_DEBTS__RESTORE_PRICE
    public String getPV1_33()
    {
    	return pv1.getPv133_BadDebtRecoveryAmount().getValue();
    }
    
    //PV1-34:PAT_ACCOUNT_VOIDED
    public String getPV1_34()
    {
    	return pv1.getPv134_DeleteAccountIndicator().getValue();
    }
    
    //PV1-35:PAT_ACCOUNT_VOIDED_DATE
    public String getPV1_35()
    {
    	return pv1.getPv135_DeleteAccountDate().getValue();
    }
    
    //PV1-36:PAT_DISCHARGE_DISPOSITION
    public String getPV1_36()
    {
    	return pv1.getPv136_DischargeDisposition().getValue();
    }
    
    //PV1-37:PAT_DISCHARGE_LOCATION
    public String getPV1_37()
    {
    	return pv1.getPv137_DischargedToLocation().getDld1_DischargeLocation().getValue();
    }
    
    //PV1-38:PAT_DIET_TYPE
    public String getPV1_38()
    {
    	return pv1.getPv138_DietType().getCe1_Identifier().getValue();
    }
    
    //PV1-39:PAT_SERVICE_AGENCIES
    public String getPV1_39()
    {
    	return pv1.getPv139_ServicingFacility().getValue();
    }
    
    //PV1-40:PAT_BED_STATUS
    public String getPV1_40()
    {
    	return pv1.getPv140_BedStatus().getValue();
    }
    
    //PV1-41:PAT_ACCOUNT_STATUS
    public String getPV1_41()
    {
    	return pv1.getPv141_AccountStatus().getValue();
    }
    
    //PV1-42:PAT_DETER_LOCATION
    public String getPV1_42_1()
    {
    	return pv1.getPv142_PendingLocation().getPl1_PointOfCare().getValue();
    }
    
    public String getPV1_42_2()
    {
    	return pv1.getPv142_PendingLocation().getPl2_Room().getValue();
    }
    
    public String getPV1_42_3()
    {
    	return pv1.getPv142_PendingLocation().getPl3_Bed().getValue();
    }
    
    public String getPV1_42_4()
    {
    	return pv1.getPv142_PendingLocation().getPl4_Facility().getHd1_NamespaceID().getValue();
    }
    
    public String getPV1_42_5()
    {
    	return pv1.getPv142_PendingLocation().getPl5_LocationStatus().getValue();
    }
    
    public String getPV1_42_6()
    {
    	return pv1.getPv142_PendingLocation().getPl6_PersonLocationType().getValue();
    }
    
    public String getPV1_42_7()
    {
    	return pv1.getPv142_PendingLocation().getPl7_Building().getValue();
    }
    
    public String getPV1_42_8()
    {
    	return pv1.getPv142_PendingLocation().getPl8_Floor().getValue();
    }
    
    public Date getPV1_42_9()
    {
    	return DateUtil.convertHL7Date(pv1.getPv142_PendingLocation().getPl9_LocationDescription().getValue());
    }
    
    //PV1-43:PAT_FOR_TEMP
     public String getPV1_43_1()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl1_PointOfCare().getValue();
     }
     
     public String getPV1_43_2()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl2_Room().getValue();
     }
     
     public String getPV1_43_3()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl3_Bed().getValue();
     }
     
     public String getPV1_43_4()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl4_Facility().getHd1_NamespaceID().getValue();
     }
     
     public String getPV1_43_5()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl5_LocationStatus().getValue();
     }
     
     public String getPV1_43_6()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl6_PersonLocationType().getValue();
     }
     
     public String getPV1_43_7()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl7_Building().getValue();
     }
     
     public String getPV1_43_8()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl8_Floor().getValue();
     }
     
     public String getPV1_43_9()
     {
    	 return pv1.getPv143_PriorTemporaryLocation().getPl9_LocationDescription().getValue();
     }
    //PV1-44:住院日期
    public Date getPV1_44()
    {
    	return DateUtil.convertHL7Date(pv1.getPv144_AdmitDateTime().getTs1_TimeOfAnEvent().getValue());
    }
    
    //PV1-45:出院日期
    public Date getPV1_45()
    {
    	return DateUtil.convertHL7Date(pv1.getPv145_DischargeDateTime().getTs1_TimeOfAnEvent().getValue());
    }
    
    //PV1-46:PAT_DIFFERENCE
    public String getPV1_46()
    {
    	return pv1.getPv146_CurrentPatientBalance().getValue();
    }
    
    //PV1-47:PAT_TOTAL_COST
    public String getPV1_47()
    {
    	return pv1.getPv147_TotalCharges().getValue();
    }
    
    //PV1-48:PAT_TOTAL_DISPATCH
    public String getPV1_48()
    {
    	return pv1.getPv148_TotalAdjustments().getValue();
    }
    
    //PV1-49:PAT_TOTAL_AMOUNT_PAYABLE
    public String getPV1_49()
    {
    	return pv1.getPv149_TotalPayments().getValue();
    }
    
    //PV1-50:PAT_SPARE_ID
    public String getPV1_50()
    {
    	return pv1.getPv150_AlternateVisitID().getCx1_ID().getValue();
    }
    //入院体重
    public String getPV1_50_3()
    {
    	return pv1.getPv150_AlternateVisitID().getCx3_CodeIdentifyingTheCheckDigitSchemeEmployed().getValue();
    }
    
    //母亲姓名
    public String getPV1_50_2()
    {
    	return pv1.getPv150_AlternateVisitID().getCx2_CheckDigit().getValue();
    }
    
    
    
    //入院体重单位
    public String getPV1_50_4()
    {
    	return pv1.getPv150_AlternateVisitID().getCx4_AssigningAuthority().getHd1_NamespaceID().getValue();
    }
    
    //婴儿母亲ID
    public String getPV1_50_4_2()
    {
    	return pv1.getPv150_AlternateVisitID().getCx4_AssigningAuthority().getHd2_UniversalID().getValue();
    }
    
    //婴儿母亲域
    public String getPV1_50_4_3()
    {
    	return pv1.getPv150_AlternateVisitID().getCx4_AssigningAuthority().getHd3_UniversalIDType().getValue();
    }
    
    //出生体重
    public String getPV1_50_5()
    {
    	return pv1.getPv150_AlternateVisitID().getCx5_IdentifierTypeCode().getValue();
    }
    
    //出生体重单位
    public String getPV1_50_6()
    {
    	return pv1.getPv150_AlternateVisitID().getCx6_AssigningFacility().getHd1_NamespaceID().getValue();
    }
    
    //母亲流水ID
    public String getPV1_50_6_2()
    {
    	return pv1.getPv150_AlternateVisitID().getCx6_AssigningFacility().getHd2_UniversalID().getValue();
    }
    
    //母亲流水域
    public String getPV1_50_6_3()
    {
    	return pv1.getPv150_AlternateVisitID().getCx6_AssigningFacility().getHd3_UniversalIDType().getValue();
    }
    
    //PV1-51:PAT_VISIT_LOGO
    public String getPV1_51()
    {
    	return pv1.getPv151_VisitIndicator().getValue();
    }

    //PV1-52:OTHER_MEDICAL_INSTITUTIONS
    public String getPV1_52_1_1()
    {
    	return pv1.getPv152_OtherHealthcareProvider(0).getXcn1_IDNumber().getValue();
    }
    
    public String getPV1_52_2_1()
    {
    	return pv1.getPv152_OtherHealthcareProvider(0).getXcn2_FamilyLastName().getFn1_FamilyName().getValue();
    }
    
    public String getPV1_52_3_1()
    {
    	return pv1.getPv152_OtherHealthcareProvider(0).getXcn3_GivenName().getValue();
    }
    
    public String getPV1_52_4_1()
    {
    	return pv1.getPv152_OtherHealthcareProvider(0).getXcn4_MiddleInitialOrName().getValue();
    }
    
    public String getPV1_52_5_1()
    {
    	return pv1.getPv152_OtherHealthcareProvider(0).getXcn5_SuffixEgJRorIII().getValue();
    }
    
    public String getPV1_52_6_1()
    {
    	return pv1.getPv152_OtherHealthcareProvider(0).getXcn6_PrefixEgDR().getValue();
    }
    
    //2013-12-26-新增NOON_CODE                      VARCHAR2(16),
//    PAYKIND_CODE                   VARCHAR2(64),
//    PAYKIND_NAME                   VARCHAR2(64),
//    SCHEMA_NO                      VARCHAR2(64),
//    ORDER_NO                       VARCHAR2(64),
//    SEENO                          VARCHAR2(64),
//    BEGIN_TIME                     TIMESTAMP(6),
//    END_TIME                       TIMESTAMP(6),
//    YNBOOK                         VARCHAR2(16),
//    YNFR                           VARCHAR2(16),
//    APPEND_FLAG                    VARCHAR2(16),
//    YNSEE                          VARCHAR2(16),
//    SEE_DATE                       TIMESTAMP(6),
//    TRIAGE_FLAG                    VARCHAR2(16),
//    TRIAGE_OPCD                    VARCHAR2(64),
//    TRIAGE_DATE                    TIMESTAMP(6),
//    SEE_DPCD                       VARCHAR2(64),
//    SEE_DOCD                       VARCHAR2(64),
//    OUT_PATIENT_STATUS_A           VARCHAR2(16),
//    OUT_PATIENT_STATUS_B           VARCHAR2(16),
//    OUT_PATIENT_STATUS_C           VARCHAR2(16),
//    IN_PATIENT_STATUS_A            VARCHAR2(16),
//    IN_PATIENT_STATUS_B            VARCHAR2(16),
//    IN_PATIENT_STATUS_C            VARCHAR2(16)
    
    
    //NOONCODE
    public String getPV1_52_1_1_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=1)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(1).getXcn1_IDNumber().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    //PAYKIND_CODE
    public String getPV1_52_1_2_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=1)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(1).getXcn2_FamilyLastName().getFn1_FamilyName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    //PAYKIND_NAME
    public String getPV1_52_1_3_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=1)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(1).getXcn3_GivenName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //SCHEMA_NO
    public String getPV1_52_1_4_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=1)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(1).getXcn4_MiddleInitialOrName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //ORDER_NO
    public String getPV1_52_1_5_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=1)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(1).getXcn5_SuffixEgJRorIII().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //SEENO
    public String getPV1_52_1_6_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=1)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(1).getXcn6_PrefixEgDR().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //BEGIN_TIME
    public Date getPV1_52_2_1_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=2)
    	{
    		return DateUtil.convertHL7Date(pv1.getPv152_OtherHealthcareProvider(2).getXcn1_IDNumber().getValue());
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //END_TIME
    public Date getPV1_52_2_2_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=2)
    	{
    		return DateUtil.convertHL7Date(pv1.getPv152_OtherHealthcareProvider(2).getXcn2_FamilyLastName().getFn1_FamilyName().getValue());
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //YNBOOK
    public String getPV1_52_2_3_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=2)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(2).getXcn3_GivenName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //YNFR
    public String getPV1_52_2_4_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=2)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(2).getXcn4_MiddleInitialOrName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //APPEND_FLAG
    public String getPV1_52_2_5_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=2)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(2).getXcn5_SuffixEgJRorIII().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //YNSEE
    public String getPV1_52_2_6_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=2)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(2).getXcn6_PrefixEgDR().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //SEE_DATE
    public Date getPV1_52_3_1_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=3)
    	{
    		return DateUtil.convertHL7Date(pv1.getPv152_OtherHealthcareProvider(3).getXcn1_IDNumber().getValue());
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //TRIAGE_FLAG
    public String getPV1_52_3_2_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=3)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(3).getXcn2_FamilyLastName().getFn1_FamilyName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //TRIAGE_OPCD
    public String getPV1_52_3_3_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=3)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(3).getXcn3_GivenName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //TRIAGE_DATE
    public Date getPV1_52_3_4_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=3)
    	{
    		return DateUtil.convertHL7Date(pv1.getPv152_OtherHealthcareProvider(3).getXcn4_MiddleInitialOrName().getValue());
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //SEE_DPCD
    public String getPV1_52_3_5_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=3)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(3).getXcn5_SuffixEgJRorIII().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //SEE_DOCD
    public String getPV1_52_3_6_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=3)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(3).getXcn6_PrefixEgDR().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //OUT_PATIENT_STATUS_A
    public String getPV1_52_4_1_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=4)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(4).getXcn1_IDNumber().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //OUT_PATIENT_STATUS_B
    public String getPV1_52_4_2_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=4)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(4).getXcn2_FamilyLastName().getFn1_FamilyName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //OUT_PATIENT_STATUS_C
    public String getPV1_52_4_3_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=4)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(4).getXcn3_GivenName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //IN_PATIENT_STATUS_A
    public String getPV1_52_4_4_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=4)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(4).getXcn4_MiddleInitialOrName().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //IN_PATIENT_STATUS_B
    public String getPV1_52_4_5_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=4)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(4).getXcn5_SuffixEgJRorIII().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //IN_PATIENT_STATUS_C
    public String getPV1_52_4_6_1()
    {
    	if(pv1.getPv152_OtherHealthcareProviderReps()>=4)
    	{
    		return pv1.getPv152_OtherHealthcareProvider(4).getXcn6_PrefixEgDR().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //pid-信息获取
    //pm-2012-07-11
    public String getPID_18()
    {
    	return pid.getPid18_PatientAccountNumber().getCx1_ID().getValue();
    }
    
    public String getPID_21()
    {
    	//System.out.println(pid.getPid21_MotherSIdentifier(0).getAssigningFacility().getUniversalID().getValue());
    	return pid.getPid21_MotherSIdentifier(0).getAssigningAuthority().getHd1_NamespaceID().getValue();
    }
    
    public String getPID_24()
    {
    	return pid.getPid24_MultipleBirthIndicator().getValue();
    }
    
    public String getPID_25()
    {
    	return pid.getPid25_BirthOrder().getValue();
    }
    
   
    /**
	 * Gets a list of patient identifiers from the MRG segment
	 *
	 * @return a list of {@link PatientIdentifier} of the merge patient
	 */
    public List<PatientIdentifier> getMrgPatientIds(){
    	List<PatientIdentifier> patientIds = new ArrayList<PatientIdentifier>();
    	PatientIdentifier identifier =new PatientIdentifier();
    	CX[] cxs = mrg.getPriorPatientIdentifierList();
    	
    	for(CX cx: cxs) {
    		Identifier assignAuth= new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(),cx.getAssigningAuthority().getUniversalID().getValue(),cx.getAssigningAuthority().getUniversalIDType().getValue());
            Identifier assignFac = new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(),cx.getAssigningFacility().getUniversalID().getValue(),cx.getAssigningFacility().getUniversalIDType().getValue());

    		//Need to reconcile assigning authority to fill up any missing components
            Identifier reconciledAssignAuth = AssigningAuthorityUtil.reconcileIdentifier(assignAuth, description, adapter);
            identifier.setAssigningAuthority(reconciledAssignAuth);
        	identifier.setAssigningFacility(assignFac);
    		identifier.setId(cx.getID().getValue());
            identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
           // identifier.setEffectiveDate(cx.get);
        //	identifier.setExpirationDate(expirationDate);
        	patientIds.add(identifier);
        }
    	return patientIds;
    }

    @SuppressWarnings("unchecked")
	public List<NextOfKin> getNextOfKin() {
        List<NextOfKin> ret = new ArrayList();
        if (nk1Array != null) {
            for (NK1 nk1 : nk1Array) {
                SI setId = nk1.getSetIDNK1();
                if (setId != null && setId.getValue() != null && setId.getValue().length() > 0) {
                    NextOfKin nok = new NextOfKin();
                    XPN names[] = nk1.getNKName();
                    if (names.length > 0) {
                        PersonName pName = new PersonName();
                        pName.setSuffix(names[0].getSuffixEgJRorIII().getValue()); //patient name suffix
                        pName.setSecondName(names[0].getMiddleInitialOrName().getValue()); //patient middle name
                        pName.setLastName(names[0].getFamilyLastName().getFamilyName().getValue()); //patient last name
                        pName.setFirstName(names[0].getGivenName().getValue()); //patient name first
                        pName.setPrefix(names[0].getPrefixEgDR().getValue());
                        pName.setDegree(names[0].getDegreeEgMD().getValue());
                        nok.setNextOfKinName(pName);
                    }
                    XAD[] addrs = nk1.getAddress();
                    if (addrs != null) {
                        for (XAD a : addrs) {
                            Address addr = new Address();
                            addr.setAddLine1(a.getStreetAddress().getValue());
                            addr.setAddCity(a.getCity().getValue());
                            addr.setAddState(a.getStateOrProvince().getValue());
                            addr.setAddZip(a.getZipOrPostalCode().getValue());
                            addr.setAddCountry(a.getCountry().getValue());
                            nok.addAddress(addr);
                        }
                    }

                    XTN phoneNums[] = nk1.getPhoneNumber();
                    if (phoneNums != null) {
                        for (XTN phoneNum : phoneNums) {
                            PhoneNumber phone = new PhoneNumber();
                            phone.setRawValue(phoneNum.get9999999X99999CAnyText().getValue());
                            phone.setType(PhoneType.HOME);
                            nok.addPhoneNumber(phone);
                        }
                    }

                    phoneNums = nk1.getBusinessPhoneNumber();
                    if (phoneNums != null) {
                        for (XTN phoneNum : phoneNums) {
                            PhoneNumber phone = new PhoneNumber();
                            phone.setRawValue(phoneNum.get9999999X99999CAnyText().getValue());
                            phone.setType(PhoneType.WORK);
                            nok.addPhoneNumber(phone);
                        }
                    }

                    nok.setContactRole(nk1.getContactRole().getIdentifier().getValue());
                    nok.setNextOfKinRelationship(nk1.getRelationship().getIdentifier().getValue());

                    ret.add(nok);
                }
            }
        }

        return ret;
    }

    @SuppressWarnings("unchecked")
	public List<TAllergy> getAllergy() throws ParseException {
        List<TAllergy> ret = new ArrayList();
        if (al1Array != null) {
            for (AL1 al1 : al1Array) {
                SI setId = al1.getSetIDAL1();
                if (setId != null && setId.getValue() != null && setId.getValue().length() > 0) {
                	TAllergy al1ok = new TAllergy();
                    al1ok.setAllergyCd(al1.getAl12_AllergyType().getValue());
                    al1ok.setAllergyText(al1.getAl13_AllergyCodeMnemonicDescription().getCe2_Text().getValue());
                    al1ok.setAllergyClass(al1.getAl14_AllergySeverity().getValue());
                    al1ok.setAllergyType(al1.getAl15_AllergyReaction(0).getValue());
                    if(al1.getAl16_IdentificationDate().getValue()!=null)
                    {
                    al1ok.setAllergyDate(new SimpleDateFormat("yyyyMMdd").parse(al1.getAl16_IdentificationDate().getValue()));
                    }
                    else
                    {
                    al1ok.setAllergyDate(new java.util.Date());
                    }
                    ret.add(al1ok);
                }
            }
        }

        return ret;
    }
    
    /**
	 * Gets the patient account number from the PID segment.
	 *
	 * @return the patient account number
	 */
    public PatientIdentifier getpatientAccountNumber(){
    	PatientIdentifier identifier =new PatientIdentifier();
    	CX cx =pid.getPatientAccountNumber();
    	Identifier assignAuth= new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(),cx.getAssigningAuthority().getUniversalID().getValue(),cx.getAssigningAuthority().getUniversalIDType().getValue());
        Identifier assignFac =new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(),cx.getAssigningFacility().getUniversalID().getValue(),cx.getAssigningFacility().getUniversalIDType().getValue());
		identifier.setAssigningAuthority(assignAuth);
    	identifier.setAssigningFacility(assignFac);
		identifier.setId(cx.getID().getValue());
        identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
       //identifier.setEffectiveDate(cx.get);
       //identifier.setExpirationDate(expirationDate);
    	return identifier;
    }


    /**
	 * Gets the merge patient account number from the MRG segment
	 *
	 * @return the patient account number of the merge patient
	 */
    public PatientIdentifier getMrgpatientAccountNumber(){
    	PatientIdentifier identifier =new PatientIdentifier();
    	CX cx =mrg.getPriorPatientAccountNumber();
    	Identifier assignAuth= new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(),cx.getAssigningAuthority().getUniversalID().getValue(),cx.getAssigningAuthority().getUniversalIDType().getValue());
        Identifier assignFac =new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(),cx.getAssigningFacility().getUniversalID().getValue(),cx.getAssigningFacility().getUniversalIDType().getValue());
		identifier.setAssigningAuthority(assignAuth);
    	identifier.setAssigningFacility(assignFac);
		identifier.setId(cx.getID().getValue());
        identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
       //identifier.setEffectiveDate(cx.get);
       //identifier.setExpirationDate(expirationDate);
    	return identifier;
    }

    /**
	 * Gets the monther's id from the PID segment
	 *
	 * @return the monther's id
	 */
    public PatientIdentifier getMothersId(){
    	PatientIdentifier identifier =new PatientIdentifier();
    	CX[] cxs = pid.getMotherSIdentifier();
    	for(CX cx: cxs) {
    		Identifier assignAuth= new Identifier(cx.getAssigningAuthority().getNamespaceID().getValue(),cx.getAssigningAuthority().getUniversalID().getValue(),cx.getAssigningAuthority().getUniversalIDType().getValue());
            Identifier assignFac =new Identifier(cx.getAssigningFacility().getNamespaceID().getValue(),cx.getAssigningFacility().getUniversalID().getValue(),cx.getAssigningFacility().getUniversalIDType().getValue());
    		identifier.setAssigningAuthority(assignAuth);
        	identifier.setAssigningFacility(assignFac);
    		identifier.setId(cx.getID().getValue());
            identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
           // identifier.setEffectiveDate(cx.get);
        //	identifier.setExpirationDate(expirationDate);
        }
    	return identifier;
    }

    /**
	 * Gets the PersonName from the PID segment
	 *
	 * @return the {@link PersonName} of the patient
	 */
    public PersonName getPatientName() throws HL7Exception {
    	PersonName pName=new PersonName();
    	
    	pName.setSuffix(pid.getPatientName(0).getSuffixEgJRorIII().getValue()); //patient name suffix
    	pName.setSecondName(pid.getPatientName(0).getMiddleInitialOrName().getValue()); //patient middle name
    	pName.setLastName(pid.getPatientName(0).getFamilyLastName().getFamilyName().getValue()); //patient last name
    	pName.setFirstName(pid.getPatientName(0).getGivenName().getValue()); //patient name first
    	pName.setPrefix(pid.getPatientName(0).getPrefixEgDR().getValue());
    	pName.setNameTypeCode(pid.getPatientName(0).getNameTypeCode().getValue());
    	pName.setNameRepresentationCode(pid.getPatientName(0).getNameRepresentationCode().getValue());
    	pName.setDegree(pid.getPatientName(0).getDegreeEgMD().getValue());
    	pName.setDegreeName(pid.getPatientName(0).getXpn3_MiddleInitialOrName().getValue());
    	pName.setDegreeDomain(pid.getPatientName(0).getXpn8_NameRepresentationCode().getValue());
    	//pid.

    	return pName;
    }


    /**
	 * Gets the PersonName from the MRG segment
	 *
	 * @return the {@link PersonName} of the merge patient
	 */
    public PersonName getMrgPatientName() throws HL7Exception {
    	PersonName pName=new PersonName();
    	pName.setSuffix(mrg.getPriorPatientName(0).getSuffixEgJRorIII().getValue()); //patient name suffix
    	pName.setSecondName(mrg.getPriorPatientName(0).getMiddleInitialOrName().getValue()); //patient middle name
    	pName.setLastName(mrg.getPriorPatientName(0).getFamilyLastName().getFamilyName().getValue()); //patient last name
    	pName.setFirstName(mrg.getPriorPatientName(0).getGivenName().getValue()); //patient name first
    	pName.setPrefix(mrg.getPriorPatientName(0).getPrefixEgDR().getValue());
    	pName.setNameTypeCode(mrg.getPriorPatientName(0).getNameTypeCode().getValue());
    	pName.setNameRepresentationCode(mrg.getPriorPatientName(0).getNameRepresentationCode().getValue());
    	pName.setDegree(mrg.getPriorPatientName(0).getDegreeEgMD().getValue());
    	return pName;
    }

    /**
	 * Get the mother's PersonName from the PID segment
	 *
	 * @return the {@link PersonName} of the monther's maiden name
	 */
    public PersonName getMotherMaidenName() throws HL7Exception {
    	PersonName mName=new PersonName();
		mName.setLastName(pid.getMotherSMaidenName(0).getFamilyLastName().getFamilyName().getValue());
		mName.setSecondName(pid.getMotherSMaidenName(0).getMiddleInitialOrName().getValue());
		mName.setFirstName(pid.getMotherSMaidenName(0).getGivenName().getValue());
		mName.setPrefix(pid.getMotherSMaidenName(0).getPrefixEgDR().getValue());
		mName.setSuffix(pid.getMotherSMaidenName(0).getSuffixEgJRorIII().getValue());
		mName.setDegree(pid.getMotherSMaidenName(0).getDegreeEgMD().getValue());
		mName.setNameTypeCode(pid.getMotherSMaidenName(0).getNameTypeCode().getValue());
		mName.setNameRepresentationCode(pid.getMotherSMaidenName(0).getNameRepresentationCode().getValue());
		return mName;
    }

    /**
	 * Gets the Patient Alias Name from the PID segment
	 *
	 * @return the patient alias name
	 */
    public List<PersonName> getPatientAliases() throws HL7Exception {
        XPN[] aliases = pid.getPatientAlias();
        List<PersonName> aNames = null;
        if (aliases != null && aliases.length > 0) {
            aNames = new ArrayList<PersonName>(aliases.length);
            for (XPN alias : aliases) {
                PersonName aName=new PersonName();
                aName.setLastName(alias.getFamilyLastName().getFamilyName().getValue());
                aName.setSecondName(alias.getMiddleInitialOrName().getValue());
                aName.setFirstName(alias.getGivenName().getValue());
                aName.setPrefix(alias.getPrefixEgDR().getValue());
                aName.setSuffix(alias.getSuffixEgJRorIII().getValue());
                aName.setDegree(alias.getDegreeEgMD().getValue());
                aName.setNameTypeCode(alias.getNameTypeCode().getValue());
                aName.setNameRepresentationCode(alias.getNameRepresentationCode().getValue());
                aNames.add(aName);
            }
        }
		return aNames;
    }

    /**
	 * Gets the DriversLincense from the PID segment
	 *
	 * @return the DriversLicense
	 */
    public DriversLicense getDriversLicense() {
    	DriversLicense lic = new DriversLicense();
    	lic.setLicenseNumber(pid.getDriverSLicenseNumberPatient().getDriverSLicenseNumber().getValue());
    	lic.setExpirationDate(DateUtil.convertHL7DateToCalender(pid.getDriverSLicenseNumberPatient().getExpirationDate().getValue()));
    	lic.setIssuingState(pid.getDriverSLicenseNumberPatient().getIssuingStateProvinceCountry().getValue());
        return lic;
    }

    /**
	 * Gets the race from the PID segment for a particular patient
	 *
	 * @return the race of the patient
	 */
    public String getRace(){
    	String race = pid.getRace(0).getIdentifier().getValue();
    	return race;
    }
    
    public String getRaceName(){
    	return pid.getRace(0).getCe2_Text().getValue();
    }
    
    public String getRaceDomain(){
    	return pid.getRace(0).getCe3_NameOfCodingSystem().getValue();
    }

    /**
	 * Gets the primary language from the PID segment for a particular patient
	 *
	 * @return the primary language
	 */
    public String getPrimaryLanguage(){
    	return pid.getPrimaryLanguage().getText().getValue();
    }

    /**
	 * Gets the martial status from the PID segment for a particular patient
	 *
	 * @return the martial status
	 */
    public String getMaritalStatus(){
    	return pid.getMaritalStatus().getIdentifier().getValue();
    }
    
    //婚姻状态编码名称
    public String getMaritalStatusName()
    {
    	return pid.getMaritalStatus().getCe2_Text().getValue();
    }
    
    //婚姻状态编码系统
    public String getMaritalStatusDomain()
    {
    	return pid.getMaritalStatus().getCe3_NameOfCodingSystem().getValue();
    }

    /**
	 * Gets the Religion from the PID segment for a particular patient
	 *
	 * @return the religion
	 */
    public String getReligion(){
    	return pid.getReligion().getText().getValue();
    }

    /**
	 * Gets the ethnic group from the PID segment for a particular patient
	 *
	 * @return the ethnic group
	 */
    public String getEthnicGroup() throws HL7Exception{
    	String ethnicGroup = pid.getEthnicGroup(0).getCe1_Identifier().getValue();
    	return ethnicGroup;
    }
    
    public String getEthnicGroupName()
    {
    	return pid.getEthnicGroup(0).getCe2_Text().getValue();
    }

    public String getEthnicGroupDomain()
    {
    	return pid.getEthnicGroup(0).getCe3_NameOfCodingSystem().getValue();
    }
    
    /**
	 * Gets the birth place from the PID segment for a particular patient
	 *
	 * @return the birth place
	 */
    public String getBirthPlace(){
		return pid.getBirthPlace().getValue();
    }

    /**
	 * Gets the birth order from the PID segment for a particular patient
	 *
	 * @return the birth order
	 */
    public int getBirthOrder(){
    	if (pid.getBirthOrder().getValue()==null)
    		return 0;

    	return Integer.valueOf(pid.getBirthOrder().getValue());

    }

    /**
	 * Gets the citizen ship from the PID segment for a particular patient
	 *
	 * @return the citizenship
	 */
    public String getCitizenShip() throws HL7Exception{
    	String citizenShip = pid.getCitizenship(0).getIdentifier().getValue();
    	return citizenShip;

    }

    /**
	 * Gets the death date from the PID segment for a particular patient
	 *
	 * @return the death date
	 */
    public Calendar getDeathDate(){
    	return DateUtil.convertHL7DateToCalender(pid.getPatientDeathDateAndTime().getTimeOfAnEvent().getValue());
    }

    /**
	 * Gets the death indicator from the PID segment for a particular patient
	 *
	 * @return <code>true</code> if dead
	 */
    public String getDeathIndicator(){
    	return pid.getPatientDeathIndicator().getValue();
    }

    /**
	 * Gets the Address list from the PID segment for a particular patient
	 *
	 * @return the list {@link Address}
	 */
    public List<Address> getAddressList11() {
        List<Address> addressList = new ArrayList<Address>();
        XAD[] xads = pid.getPatientAddress();
        for(XAD xad: xads) {
            Address address = new Address();
            address.setAddCity(xad.getCity().getValue()); 
            address.setAddCountry(xad.getCountry().getValue());
            address.setAddCounty(xad.getCountyParishCode().getValue());
            address.setAddLine1(xad.getStreetAddress().getValue()); //地址
            address.setAddLine2(xad.getOtherDesignation().getValue());
            address.setAddState(xad.getStateOrProvince().getValue());
            address.setAddType(_mapAddressToBase(xad.getAddressType().getValue()));
            address.setAddZip(xad.getZipOrPostalCode().getValue());
            addressList.add(address);
        }
        return addressList;
    }

    /**
     * Converts an address type from a HL7 string encode to {@link AddressType}.
     *
     * @param value the address type in HL7 string encode to be converted from
     * @return an address type in {@link AddressType}
     */
    private SharedEnums.AddressType _mapAddressToBase(String value) {
    	return SharedEnums.AddressType.hl7ValueOf(value);
    }

    /**
     * Gets the Sex Type.
     *
     * @return the {@link SexType} of the patient
     */
    public SharedEnums.SexType getSexType() {
        String sex = pid.getSex().getValue();
        if (sex == null) return null;
    	return SharedEnums.SexType.getByString( sex );
    }

    /**
     * Gets the birth date
     *
     * @return the birth date
     */
    public Calendar getBirthDate() {
        String dob = pid.getDateTimeOfBirth().getTimeOfAnEvent().getValue();
        return DateUtil.convertHL7DateToCalender(dob);
    }

    /**
	 * Gets the list of vists from the PID segment for a particular patient
	 *
	 * @return a list of {@link Visit}
	 */
    public List<Visit> getVisitList() {
        if(pv1 == null) {
            return null;
        }
        List<Visit> visitList = new ArrayList<Visit>();

        String systemId = getHomeSystem();
        String visitId = pv1.getVisitNumber().getID().getValue();
        if (!StringUtil.goodString(visitId)) {
            //generate one if null
        }
        Visit visit = new Visit(systemId, visitId);
        visit.setReason(getVisitReason());
        visit.setVisitEndTimestamp(getEndDate());
        Date startDate = getStartDate();
        visit.setVisitStartTimestamp( startDate == null ? new GregorianCalendar().getTime() : startDate );
        visitList.add(visit);
        return visitList;
    }

    /**
	 * Gets the Visit list from the pv1 segment for a particular patient
	 *
	 * @return a list of {@link Visit}
	 */
    public List<Visit> getMrgVisitList() {

        List<Visit> visitList = new ArrayList<Visit>();
        String systemId = mrg.getPriorVisitNumber().getAssigningFacility().getNamespaceID().getValue();
        String visitId = mrg.getPriorAlternateVisitID().getID().getValue();
        if (!StringUtil.goodString(visitId)) {
            //generate one if null
        }
        Visit visit = new Visit(systemId, visitId);
        visit.setReason(getVisitReason());
        visit.setVisitEndTimestamp(getEndDate());
        Date startDate = getStartDate();
        visit.setVisitStartTimestamp( startDate == null ? new GregorianCalendar().getTime() : startDate );
        visitList.add(visit);
        return visitList;
    }

    /**
	 * Gets the ProblemList from the PID segment for a particular patient
	 * @return List<Problem>
	 */
    private List<Problem> getProblemList() {
        List<Problem> probList = new ArrayList<Problem>();
        if(dg1 != null) {
            Problem problem = new Problem();

            problem.setProbCode(dg1.getDiagnosisCodeDG1().getIdentifier().getValue());
            problem.setProbCodeSystem(dg1.getDiagnosisCodingMethod().getValue());
            //problem.setProbCodeVersion();
            problem.setProbName(dg1.getDiagnosisDescription().getValue());
            probList.add(problem);
        }

        return probList;
    }


    /**
	 * Gets the ProviderList from the PID segment for a particular patient
	 * @return List<Provider>
	 */
    private List<Provider> getProviderList() {
        List<Provider> providerList = new ArrayList<Provider>();
        XCN[] xcns = pv1.getAttendingDoctor();
        for(XCN xcn: xcns){
            Provider provider = new Provider();
            provider.setProviderId(xcn.getIDNumber().getValue());
            provider.setProvNameFirst(xcn.getGivenName().getValue());
            provider.setProvNameLast(xcn.getFamilyLastName().getName());
            provider.setProvNameMiddle(xcn.getMiddleInitialOrName().getValue());
            provider.setProvNameSuffix(xcn.getSuffixEgJRorIII().getValue());
            provider.setProvNameTitle(xcn.getPrefixEgDR().getValue());

            providerList.add(provider);
        }
        return providerList;
    }

    private String getVisitReason() {
        if(pv2 == null) {
            return  null;
        }
        return pv2.getAdmitReason().getText().getValue();
    }

    private Date getEndDate() {
        String dob = pv1.getDischargeDateTime().getTimeOfAnEvent().getValue();
        return DateUtil.convertHL7Date(dob);
    }

    private Date getStartDate() {
        return DateUtil.convertHL7Date(pv1.getAdmitDateTime().getTimeOfAnEvent().getValue());
    }

    /**
	 * Gets the ssn from the PID segment for a particular patient
	 * @return String
	 */

    public String getSsn() {
        return pid.getSSNNumberPatient().getValue();
    }
    
	/**
	 * Return the VIP indicator.  
	 * 
	 * @return the VIP indicator
	 */
	public String getVipIndicator() {
		if (pv1 == null) return null;
		return pv1.getVIPIndicator().getValue();
	}

    //家庭电话
    public String getHomePhone()
    {
    	return pid.getPid13_PhoneNumberHome(0).get9999999X99999CAnyText().getValue();
    }
    
    //邮件地址
    public String getEmail()
    {
    	return pid.getPid13_PhoneNumberHome(0).getXtn4_EmailAddress().getValue();
    }
    
    //私人电话
    public String getPrivatePhone()
    {
    	if(pid.getPid13_PhoneNumberHomeReps()>=1)
    	{
    		return pid.getPid13_PhoneNumberHome(1).getXtn1_9999999X99999CAnyText().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
 
    //单位电话
    public String getWorkPhone()
    {
    	if(pid.getPid13_PhoneNumberHomeReps()>=2)
    	{
    		return pid.getPid13_PhoneNumberHome(2).getXtn1_9999999X99999CAnyText().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //职业名称
    public String getProfessionName()
    {
    	return pid.getPid14_PhoneNumberBusiness(0).getXtn1_9999999X99999CAnyText().getValue();
    }
    
    //职业编码系统
    public String getProfessionDomain()
    {
    	if(pid.getPid14_PhoneNumberBusinessReps()>=1)
    	{
    		return pid.getPid14_PhoneNumberBusiness(1).getXtn1_9999999X99999CAnyText().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //性别名称
    public String getGenderName()
    {
    	if(pid.getPid14_PhoneNumberBusinessReps()>=2)
    	{
    		return pid.getPid14_PhoneNumberBusiness(2).getXtn1_9999999X99999CAnyText().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //性别编码系统
    public String getGenderDomain()
    {
    	if(pid.getPid14_PhoneNumberBusinessReps()>=3)
    	{
    		return pid.getPid14_PhoneNumberBusiness(3).getXtn1_9999999X99999CAnyText().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
     //CARD_TYPE
    public String getCardType()
    {
    	if(pid.getPid14_PhoneNumberBusinessReps()>=4)
    	{
    		return pid.getPid14_PhoneNumberBusiness(4).getXtn1_9999999X99999CAnyText().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    public String getBusinessHome()
    {
    	return pid.getPid14_PhoneNumberBusiness(0).getXtn7_PhoneNumber().getValue();
    }
    
    public String getHomeSystem() {
        return msh.getSendingFacility().getNamespaceID().getValue();
    }
    
    //监护人姓名
    public String getPID9() {
    	return pid.getPid9_PatientAlias(0).getXpn1_FamilyLastName().getFn1_FamilyName().getValue();
    }
    
    //职业编码
    public String getPID12(){
    	return pid.getPid12_CountyCode().getValue();
    }
    
    //国籍
    public String getNationality()
    {
    	return pid.getPid28_Nationality().getCe1_Identifier().getValue();
    }
    
    //国籍名称
    public String getNationalityName()
    {
    	return pid.getPid28_Nationality().getCe2_Text().getValue();
    }
    
    //国籍编码系统
    public String getNationalityDomain()
    {
    	return pid.getPid28_Nationality().getCe3_NameOfCodingSystem().getValue();
    }
    
    //保密
    public String getVip()
    {
    	return pid.getPid30_PatientDeathIndicator().getValue();
    }
    
    //居住地址
    public String getHomeAddress()
    {
    	return pid.getPid11_PatientAddress(0).getXad1_StreetAddress().getValue();
    }
    
    //居住街道
    public String getHomeStreet()
    {
    	return pid.getPid11_PatientAddress(0).getXad2_OtherDesignation().getValue();
    }
    
    //居住所在地市
    public  String getHomeCity()
    {
    	return pid.getPid11_PatientAddress(0).getXad3_City().getValue();
    }
    
    //居住所在地省
    public String getHomeProvince()
    {
    	return pid.getPid11_PatientAddress(0).getXad4_StateOrProvince().getValue();
    }
    
    //居住所在地区县
    public String getHomeCounty()
    {
    	return pid.getPid11_PatientAddress(0).getXad9_CountyParishCode().getValue();
    }
    
    //居住所在地邮编
    public String getHomeZip()
    {
    	return pid.getPid11_PatientAddress(0).getXad5_ZipOrPostalCode().getValue();
    }
    
    //户口地址
    public String getRegisteredAddress()
    {
    	if(pid.getPid11_PatientAddressReps()>=1)
    	{
    		return pid.getPid11_PatientAddress(1).getXad1_StreetAddress().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //户口街道
    public String getRegisteredStreet()
    {
    	if(pid.getPid11_PatientAddressReps()>=1)
    	{
    		return pid.getPid11_PatientAddress(1).getXad2_OtherDesignation().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //户口所在地市
    public String getRegisteredCity()
    {
    	if(pid.getPid11_PatientAddressReps()>=1)
    	{
    	return pid.getPid11_PatientAddress(1).getXad3_City().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //户口所在地省
    public String getRegisteredProvince()
    {
    	if(pid.getPid11_PatientAddressReps()>=1)
    	{
    		return pid.getPid11_PatientAddress(1).getXad4_StateOrProvince().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //户口所在地区县
    public String getRegisteredCounty()
    {
    	if(pid.getPid11_PatientAddressReps()>=1)
    	{
    		return pid.getPid11_PatientAddress(1).getXad9_CountyParishCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //户口所在地邮编
    public String getRegisteredZip()
    {
    	if(pid.getPid11_PatientAddressReps()>=1)
    	{
    		return pid.getPid11_PatientAddress(1).getXad5_ZipOrPostalCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //出生地址
    public String getBirthAddress()
    {
    	if(pid.getPid11_PatientAddressReps()>=2)
    	{
    		return pid.getPid11_PatientAddress(2).getXad1_StreetAddress().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //出生所在地市
    public String getBirthCity()
    {
    	if(pid.getPid11_PatientAddressReps()>=2)
    	{
    		return pid.getPid11_PatientAddress(2).getXad3_City().getValue();
    	}
    	else
    	{
    		return null;
    	}
    } 
    
    //出生所在地省
    public String getBirthProvince()
    {
    	if(pid.getPid11_PatientAddressReps()>=2)
    	{
    		return pid.getPid11_PatientAddress(2).getXad4_StateOrProvince().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //出生地所在地邮编
    public String getBirhtZip()
    {
    	if(pid.getPid11_PatientAddressReps()>=2)
    	{
    		return pid.getPid11_PatientAddress(2).getXad5_ZipOrPostalCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //出生地所在地区县
    public String getBirthCounty()
    {
    	if(pid.getPid11_PatientAddressReps()>=2)
    	{
    	return pid.getPid11_PatientAddress(2).getXad9_CountyParishCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //籍贯地址
    public String getNativeAddress()
    {
    	if(pid.getPid11_PatientAddressReps()>=3)
    	{
    		return pid.getPid11_PatientAddress(3).getXad1_StreetAddress().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //籍贯所在地市
    public String getNativeCity()
    {
    	if(pid.getPid11_PatientAddressReps()>=3)
    	{
    		return pid.getPid11_PatientAddress(3).getXad3_City().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //籍贯所在地省
    public String getNativeProvince()
    {
    	if(pid.getPid11_PatientAddressReps()>=3)
    	{
    		return pid.getPid11_PatientAddress(3).getXad4_StateOrProvince().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //籍贯所在地邮编
    public String getNativeZip()
    {
    	if(pid.getPid11_PatientAddressReps()>=3)
    	{
    		return pid.getPid11_PatientAddress(3).getXad5_ZipOrPostalCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //籍贯所在地区县
    public String getNativeCounty()
    {
    	if(pid.getPid11_PatientAddressReps()>=3)
    	{
    		return pid.getPid11_PatientAddress(3).getXad9_CountyParishCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
 
    //单位地址
    public String getWorkAddress()
    {
    	if(pid.getPid11_PatientAddressReps()>=4)
    	{
    		return pid.getPid11_PatientAddress(4).getXad1_StreetAddress().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //工作单位
    public String getCompany()
    {
    	if(pid.getPid11_PatientAddressReps()>=4)
    	{
    		return pid.getPid11_PatientAddress(4).getXad2_OtherDesignation().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //单位所在地市
    public String getWorkCity()
    {
    	if(pid.getPid11_PatientAddressReps()>=4)
    	{
    		return pid.getPid11_PatientAddress(4).getXad3_City().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //单位所在地省
    public String getWorkProvince()
    {
    	if(pid.getPid11_PatientAddressReps()>=4)
    	{
    		return pid.getPid11_PatientAddress(4).getXad4_StateOrProvince().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //单位所在地邮编
    public String getWorkZip()
    {
    	if(pid.getPid11_PatientAddressReps()>=4)
    	{
    		return pid.getPid11_PatientAddress(4).getXad5_ZipOrPostalCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //单位所在地区县
    public String getWorkCounty()
    {
    	if(pid.getPid11_PatientAddressReps()>=4)
    	{
    	return pid.getPid11_PatientAddress(4).getXad9_CountyParishCode().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //医保号类型
    public String getPid21_2_1()
    {
    	if(pid.getPid21_MotherSIdentifierReps()>=1)
    	{
    	   return pid.getPid21_MotherSIdentifier(1).getCx1_ID().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
 
    //医保号类型名称
    public String getPid21_3_1()
    {
    	if(pid.getPid21_MotherSIdentifierReps()>=2)
    	{
    	   return pid.getPid21_MotherSIdentifier(2).getCx1_ID().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //是否封帐
    public String getPid21_4_1()
    {
    	if(pid.getPid21_MotherSIdentifierReps()>=3)
    	{
    	   return pid.getPid21_MotherSIdentifier(3).getCx1_ID().getValue();
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //封帐时间
    public Date getPid21_5_1()
    {
    	if(pid.getPid21_MotherSIdentifierReps()>=4)
    	{
    	   return DateUtil.convertHL7Date(pid.getPid21_MotherSIdentifier(4).getCx1_ID().getValue());
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //出生时间
    public Date getPid21_6_1()
    {
    	if(pid.getPid21_MotherSIdentifierReps()>=5)
    	{
    	   return DateUtil.convertHL7Date(pid.getPid21_MotherSIdentifier(5).getCx1_ID().getValue());
    	}
    	else
    	{
    		return null;
    	}
    }
    
    //医保号
    public String getPid21_1_1()
    {
    	return pid.getPid21_MotherSIdentifier(0).getCx1_ID().getValue();
    }
    
}
