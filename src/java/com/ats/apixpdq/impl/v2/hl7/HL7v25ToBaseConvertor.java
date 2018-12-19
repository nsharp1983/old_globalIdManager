package com.ats.apixpdq.impl.v2.hl7;

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
import com.ats.aexchange.datamodel.Visit;
import com.ats.aexchange.datamodel.SharedEnums.AddressType;
import com.ats.aexchange.datamodel.SharedEnums.PhoneType;
import com.ats.aexchange.datamodel.SharedEnums.SexType;
import com.ats.aexchange.utils.DateUtil;
import com.ats.aexchange.utils.StringUtil;

import com.ats.apixpdq.api.IPixPdqAdapter;
import com.ats.apixpdq.common.AssigningAuthorityUtil;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Structure;
import ca.uhn.hl7v2.model.v23.message.ADT_A40;
import ca.uhn.hl7v2.model.v231.group.ADT_A40_PIDPD1MRGPV1;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.SI;
import ca.uhn.hl7v2.model.v25.datatype.TS;
import ca.uhn.hl7v2.model.v25.datatype.XAD;
import ca.uhn.hl7v2.model.v25.datatype.XCN;
import ca.uhn.hl7v2.model.v25.datatype.XPN;
import ca.uhn.hl7v2.model.v25.datatype.XTN;
import ca.uhn.hl7v2.model.v25.segment.DG1;
import ca.uhn.hl7v2.model.v25.segment.MRG;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.NK1;
import ca.uhn.hl7v2.model.v25.segment.PD1;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.model.v25.segment.PV1;
import ca.uhn.hl7v2.model.v25.segment.PV2;

/**
 * HL7v25ToBaseConvertor parsers HL7v2.5 messages.
 *
 * @author Wenzhi Li
 * @version 1.0, Dec 22, 2008
 */
public class HL7v25ToBaseConvertor {

    private MSH msh;
    private PID pid;
    private PV1 pv1;
    private PV2 pv2;
    private PD1 pd1;
    private DG1 dg1;
    private MRG mrg;
    private NK1 nk1Array[];
    private ADT_A40_PIDPD1MRGPV1 pidpd1mrgpv1;

    IBaseDescription description;
    IPixPdqAdapter adapter;

    public HL7v25ToBaseConvertor(Message in, IBaseDescription description, IPixPdqAdapter adapter) {
    	this.description = description;
        this.adapter = adapter;
    	try {
        	msh = (MSH)in.get("MSH");

        	 if (in instanceof ADT_A40) {
        		 // this is for merge patients
        		 pidpd1mrgpv1 = (ADT_A40_PIDPD1MRGPV1)in.get("PIDPD1MRGPV1");
        		 pid = (PID)pidpd1mrgpv1.get("PID");
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
            pv1 = (PV1)in.get("PV1");
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
                Structure[] s = in.getAll("NK1");
                nk1Array = new NK1[s.length];
                for (int i = 0; i < s.length; i++) {
                    nk1Array[i] = (NK1) s[i];
                }
            } catch (HL7Exception e) {
               nk1Array = null; //Find no NK1, ok, it is fine.
            }
        	 }

        }catch (HL7Exception e) {
              throw new ExceptionInInitializerError(e);
        }
        if(msh == null || pid == null) {
            throw new ExceptionInInitializerError();
        }
    }

    
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
    	return pv1.getPv15_PreadmitNumber().getCx1_IDNumber().getValue();
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
    	String TempString1=pv1.getPv17_AttendingDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv17_AttendingDoctor(0).getFamilyName().getFn1_Surname().getValue();
    	
    	return TempString2 + TempString1;
    }
    
    //PV1-8:转诊医生
    public String getPV1_8_1()
    {
    	return pv1.getPv18_ReferringDoctor(0).getXcn1_IDNumber().getValue();
    }
    
    public String getPV1_8_2()
    {
    	String TempString1=pv1.getPv18_ReferringDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv18_ReferringDoctor(0).getXcn2_FamilyName().getFn1_Surname().getValue();
    	
    	return TempString2 + TempString1;
    }
    
  //PV1-9:会诊医生
    public String getPV1_9_1()
    {
    	return pv1.getPv19_ConsultingDoctor(0).getXcn1_IDNumber().getValue();
    }
    
    public String getPV1_9_2()
    {
    	String TempString1=pv1.getPv19_ConsultingDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv19_ConsultingDoctor(0).getXcn2_FamilyName().getFn1_Surname().getValue();
    	
    	return TempString2 + TempString1;
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
    	String TempString1=pv1.getPv117_AdmittingDoctor(0).getXcn3_GivenName().getValue();
    	
    	String TempString2=pv1.getPv117_AdmittingDoctor(0).getXcn2_FamilyName().getFn1_Surname().getValue();
    	
    	return TempString2 + TempString1;
    }
    
    //PV1-18:patient_class
    public String getPV1_18()
    {
    	return pv1.getPv118_PatientType().getValue();

    }
    
    //pv1-19:PATIENT_ID
    public String getPV1_19()
    {
    	return pv1.getPv119_VisitNumber().getCx1_IDNumber().getValue();
    }
    
    //PV1-20:PAT_FINANCIAL_CLASS
    public String getPV1_20()
    {
    	return pv1.getPv120_FinancialClass(0).getFc1_FinancialClassCode().getValue();
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
    
    public String getPV1_42_9()
    {
    	return pv1.getPv142_PendingLocation().getPl9_LocationDescription().getValue();
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
    public String getPV1_44()
    {
    	return pv1.getPv144_AdmitDateTime().getTs1_Time().getValue();
    }
    
    //PV1-45:出院日期
    public String getPV1_45()
    {
    	return pv1.getPv145_DischargeDateTime(0).getTime().getValue();
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
    	return pv1.getPv150_AlternateVisitID().getCx1_IDNumber().getValue();
    }
    
    //PV1-51:PAT_VISIT_LOGO
    public String getPV1_51()
    {
    	return pv1.getPv151_VisitIndicator().getValue();
    }

    //PV1-52:OTHER_MEDICAL_INSTITUTIONS
    public String getPV1_52()
    {
    	return pv1.getPv152_OtherHealthcareProvider(0).getGivenName().getValue();
    }
    
    
    
    //pid-信息获取
    //pm-2012-07-11
    

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
    		identifier.setId(cx.getIDNumber().getValue());
            identifier.setIdentifierTypeCode(cx.getIdentifierTypeCode().getValue());
           // identifier.setEffectiveDate(cx.get);
        //	identifier.setExpirationDate(expirationDate);
        	patientIds.add(identifier);
        }
    	return patientIds;
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
    		identifier.setId(cx.getIDNumber().getValue());
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
                        pName.setSecondName(names[0].getSecondAndFurtherGivenNamesOrInitialsThereof().getValue()); //patient middle name
                        pName.setLastName(names[0].getFamilyName().getSurname().getValue()); //patient last name
                        pName.setFirstName(names[0].getGivenName().getValue()); //patient name first
                        pName.setPrefix(names[0].getPrefixEgDR().getValue());
                        pName.setDegree(names[0].getDegreeEgMD().getValue());
                        nok.setNextOfKinName(pName);
                    }
                    XAD[] addrs = nk1.getAddress();
                    if (addrs != null) {
                        for (XAD a : addrs) {
                            Address addr = new Address();
                            addr.setAddLine1(a.getStreetAddress().getStreetOrMailingAddress().getValue());
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
                            phone.setRawValue(phoneNum.getAnyText().getValue());
                            phone.setType(PhoneType.HOME);
                            nok.addPhoneNumber(phone);
                        }
                    }

                    phoneNums = nk1.getBusinessPhoneNumber();
                    if (phoneNums != null) {
                        for (XTN phoneNum : phoneNums) {
                            PhoneNumber phone = new PhoneNumber();
                            phone.setRawValue(phoneNum.getAnyText().getValue());
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
		identifier.setId(cx.getIDNumber().getValue());
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
		identifier.setId(cx.getIDNumber().getValue());
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
    		identifier.setId(cx.getIDNumber().getValue());
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
    	pName.setSecondName(pid.getPatientName(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue()); //patient middle name
    	pName.setLastName(pid.getPatientName(0).getFamilyName().getSurname().getValue()); //patient last name
    	pName.setFirstName(pid.getPatientName(0).getGivenName().getValue()); //patient name first
    	pName.setPrefix(pid.getPatientName(0).getPrefixEgDR().getValue());
    	pName.setNameTypeCode(pid.getPatientName(0).getNameTypeCode().getValue());
    	pName.setNameRepresentationCode(pid.getPatientName(0).getNameRepresentationCode().getValue());
    	pName.setDegree(pid.getPatientName(0).getDegreeEgMD().getValue());

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
    	pName.setSecondName(mrg.getPriorPatientName(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue()); //patient middle name
    	pName.setLastName(mrg.getPriorPatientName(0).getFamilyName().getSurname().getValue()); //patient last name
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
		mName.setLastName(pid.getMotherSMaidenName(0).getFamilyName().getSurname().getValue());
		mName.setSecondName(pid.getMotherSMaidenName(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue());
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
                aName.setLastName(alias.getFamilyName().getSurname().getValue());
                aName.setSecondName(alias.getSecondAndFurtherGivenNamesOrInitialsThereof().getValue());
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
    	lic.setLicenseNumber(pid.getDriverSLicenseNumberPatient().getLicenseNumber().getValue());
    	lic.setExpirationDate(DateUtil.convertHL7DateToCalender(pid.getDriverSLicenseNumberPatient().getExpirationDate().getValue()));
    	lic.setIssuingState(pid.getDriverSLicenseNumberPatient().getIssuingStateProvinceCountry().getValue());
        return lic;
    }

    /**
	 * Gets the race from the PID segment for a particular patient
	 *
	 * @return the race of the patient
	 */
    public String getRace()throws HL7Exception{
    	String race = pid.getRace(0).getIdentifier().getValue();
    	return race;
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
    	String ethnicGroup = pid.getEthnicGroup(0).getText().getValue();
    	return ethnicGroup;
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
    	return DateUtil.convertHL7DateToCalender(pid.getPatientDeathDateAndTime().getTime().getValue());
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
    public List<Address> getAddressList() {
        List<Address> addressList = new ArrayList<Address>();
        XAD[] xads = pid.getPatientAddress();
        for(XAD xad: xads) {
            Address address = new Address();
            address.setAddCity(xad.getCity().getValue());
            address.setAddCountry(xad.getCountry().getValue());
            address.setAddCounty(xad.getCountyParishCode().getValue());
            address.setAddLine1(xad.getStreetAddress().getStreetOrMailingAddress().getValue());
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
        String sex = pid.getAdministrativeSex().getValue();
        if (sex == null) return null;
    	return SharedEnums.SexType.getByString( sex );
    }

    /**
     * Gets the birth date
     *
     * @return the birth date
     */
    public Calendar getBirthDate() {
        String dob = pid.getDateTimeOfBirth().getTime().getValue();
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
        String visitId = pv1.getVisitNumber().getIDNumber().getValue();
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
        String visitId = mrg.getPriorAlternateVisitID().getIDNumber().getValue();
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
            provider.setProvNameLast(xcn.getFamilyName().getSurname().getName());
            provider.setProvNameMiddle(xcn.getSecondAndFurtherGivenNamesOrInitialsThereof().getValue());
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
    	TS[] ts = pv1.getDischargeDateTime();
    	if (ts==null || ts.length == 0)
    		return null;
    	String dob = ts[0].getTime().getValue();
        return DateUtil.convertHL7Date(dob);
    }

    private Date getStartDate() {
        return DateUtil.convertHL7Date(pv1.getAdmitDateTime().getTime().getValue());
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

    /**
	 * Gets the PhoneList from the PID segment for a particular patient
	 * @return List<PhoneNumber>
	 */
    public List<PhoneNumber> getPhoneList() {
        List<PhoneNumber> phoneList = new ArrayList<PhoneNumber>();

        for (XTN xtn : pid.getPhoneNumberHome()) {
            PhoneNumber home = _getPhoneNumber(xtn);
            if(home != null){
                home.setType(SharedEnums.PhoneType.HOME);
                phoneList.add(home);
            }
        }

        for (XTN xtn : pid.getPhoneNumberBusiness()) {
            PhoneNumber business = _getPhoneNumber(xtn);
            if(business != null){
            	business.setType(SharedEnums.PhoneType.WORK);
            	phoneList.add(business);
            }
        }
        
        return phoneList;
    }
    
    public String getHomePhone()
    {
    	return pid.getPid13_PhoneNumberHome(0).getXtn1_TelephoneNumber().getValue();
    }
    
    public String getBusinessHome()
    {
    	return pid.getPid14_PhoneNumberBusiness(0).getXtn1_TelephoneNumber().getValue();
    }

    private PhoneNumber _getPhoneNumber(XTN xtn) {
        PhoneNumber number = null;
        if(xtn != null) {
            String sNum = xtn.getAnyText().getValue();
            String email =  xtn.getEmailAddress().getValue();
            if(sNum != null && sNum.length() > 0) {
                number = new PhoneNumber();
                number.setRawValue(sNum);
                number.setAreaCode(xtn.getAreaCityCode().getValue());
                number.setExtension(xtn.getExtension().getValue());
                number.setNumber(xtn.getLocalNumber().getValue());
            }
            if(email != null && email.length() > 0) {
                if (number == null) {
                    number = new PhoneNumber();
                }
                number.setEmail(email);
            }
            if (number != null) {
                if (xtn.getTelecommunicationUseCode() != null && xtn.getTelecommunicationUseCode().getValue() != null) {
                    number.setUseCode(SharedEnums.TelecomUseCode.valueOf(xtn.getTelecommunicationUseCode().getValue()));
                }
            }
        }
        return number;
    }

    public String getHomeSystem() {
        return msh.getSendingFacility().getNamespaceID().getValue();
    }
}
