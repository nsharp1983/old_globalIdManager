package com.ats.aempi.apixpdqadapter;

import java.util.ArrayList;
import java.util.List;

import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.apixpdq.api.PixManagerException;

public class PixManagerAdapterHelper
{
    //反馈异常信息
    //这个写法很不负责，但比6000多行的一个类好一些
    public static PixManagerException GeneratePixManagerException(Exception e)
    {
        if (e.getMessage().indexOf("com.ats.aempi.model.MaritalStatusDict")>0)
        {
            return new PixManagerException("PID.16.1字段输入的内容不符合婚姻字典的设置，请查阅婚姻字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.AddressTypeDict")>0)
        {
            return new PixManagerException("PID.11.7字段输入的内容不符合地址类型字典的设置，请查阅地址类型字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.EthnicgroupDict")>0)
        {
            return new PixManagerException("PID.22.1字段输入的内容不符合民族字典的设置，请查阅民族字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.GenderDict")>0)
        {
            return new PixManagerException("PID.8.1字段输入的内容不符合性别字典的设置，请查阅性别字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.LanguageDict")>0)
        {
            return new PixManagerException("PID.15.1字段输入的内容不符合语种字典的设置，请查阅语种字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.NationalityDict")>0)
        {
            return new PixManagerException("PID.28.1字段输入的内容不符合国家字典的设置，请查阅国家字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.RaceDict")>0)
        {
            return new PixManagerException("PID.10.1字段输入的内容不符合种族字典的设置，请查阅种族字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.ReligionDict")>0)
        {
            return new PixManagerException("PID.17.1字段输入的内容不符合宗教字典的设置，请查阅宗教字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.DegreeTypeDict")>0)
        {
            return new PixManagerException("PID.5.6.1字段输入的内容不符合学位字典的设置，请查阅学位字典");
        }
        else if (e.getMessage().indexOf("com.ats.aempi.model.ProfessionTypeDict")>0)
        {
            return new PixManagerException("PID.12.1字段输入的内容不符合专业字典的设置，请查阅专业字典");
        }
        else
        {
            return new PixManagerException(e);
        }
    }
    
    //一个函数3000多行
    public static List<String> updatePatientVisitAttributes(PatientVisit mypatientvisit,PatientVisit patientvisit,Person person)
    {
        List<String> VisitInfo = new ArrayList<String>();
        
        String OldVisitInfo = "Old Visit Info: ";
        
        String NewVisitInfo = "New Visit Info: ";
        
        //PV1-2:PAT_CATEGORY
        if (isPopulated(patientvisit.getPatCategory())) 
        {
            if(isPopulated(mypatientvisit.getPatCategory()))
            {
                if(!mypatientvisit.getPatCategory().equalsIgnoreCase(patientvisit.getPatCategory()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCategory(" + mypatientvisit.getPatCategory() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCategory(" + patientvisit.getPatCategory() + "),";            
            
                    mypatientvisit.setPatCategory(patientvisit.getPatCategory());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCategory(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCategory(" + patientvisit.getPatCategory() + "),";            
        
                mypatientvisit.setPatCategory(patientvisit.getPatCategory());
            }
        }
        
        //pv1-3.1:PAT_CURRENT_POINT_OF_CARE
        if(isPopulated(patientvisit.getPatCurrentPointOfCare()))
        {
            if(isPopulated(mypatientvisit.getPatCurrentPointOfCare()))
            {
                if(!mypatientvisit.getPatCurrentPointOfCare().equalsIgnoreCase(patientvisit.getPatCurrentPointOfCare()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCurrentPointOfCare(" + mypatientvisit.getPatCurrentPointOfCare() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCurrentPointOfCare(" + patientvisit.getPatCurrentPointOfCare() + "),";            
            
                    mypatientvisit.setPatCurrentPointOfCare(patientvisit.getPatCurrentPointOfCare());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCurrentPointOfCare(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCurrentPointOfCare(" + patientvisit.getPatCurrentPointOfCare() + "),";            
        
                mypatientvisit.setPatCurrentPointOfCare(patientvisit.getPatCurrentPointOfCare());
            }
        }
        
        //PV1-3.2:PAT_CURRENT_ROOM
        if(isPopulated(patientvisit.getPatCurrentRoom()))
        {
            if(isPopulated(mypatientvisit.getPatCurrentRoom()))
            {
                if(!mypatientvisit.getPatCurrentRoom().equalsIgnoreCase(patientvisit.getPatCurrentRoom()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCurrentRoom(" + mypatientvisit.getPatCurrentRoom() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCurrentRoom(" + patientvisit.getPatCurrentRoom() + "),";          
            
                    mypatientvisit.setPatCurrentRoom(patientvisit.getPatCurrentRoom());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCurrentRoom(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCurrentRoom(" + patientvisit.getPatCurrentRoom() + "),";          
        
                mypatientvisit.setPatCurrentRoom(patientvisit.getPatCurrentRoom());
            }
        }
        
        //PV1-3.3:PAT_CURRENT_BED
        if(isPopulated(patientvisit.getPatCurrentBed()))
        {
            if(isPopulated(mypatientvisit.getPatCurrentBed()))
            {
                if(!mypatientvisit.getPatCurrentBed().equalsIgnoreCase(patientvisit.getPatCurrentBed()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCurrentBed(" + mypatientvisit.getPatCurrentBed() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCurrentBed(" + patientvisit.getPatCurrentBed() + "),";            
            
                    mypatientvisit.setPatCurrentBed(patientvisit.getPatCurrentBed());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCurrentBed(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCurrentBed(" + patientvisit.getPatCurrentBed() + "),";            
        
                mypatientvisit.setPatCurrentBed(patientvisit.getPatCurrentBed());
            }
        }
        
        //PV1-3.4:pat_current_dep
        if(isPopulated(patientvisit.getPatCuurentDep()))
        {
            if(isPopulated(mypatientvisit.getPatCuurentDep()))
            {
                if(!mypatientvisit.getPatCuurentDep().equalsIgnoreCase(patientvisit.getPatCuurentDep()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCuurentDep(" + mypatientvisit.getPatCuurentDep() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCuurentDep(" + patientvisit.getPatCuurentDep() + "),";            
            
                    mypatientvisit.setPatCuurentDep(patientvisit.getPatCuurentDep());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCuurentDep(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCuurentDep(" + patientvisit.getPatCuurentDep() + "),";            
        
                mypatientvisit.setPatCuurentDep(patientvisit.getPatCuurentDep());
            }
        }
        
        //PV1-3.5:PAT_CURRENT_POSITION_STATUS
        if(isPopulated(patientvisit.getPatCurrentPositionStatus()))
        {
            if(isPopulated(mypatientvisit.getPatCurrentPositionStatus()))
            {
                if(!mypatientvisit.getPatCurrentPositionStatus().equalsIgnoreCase(patientvisit.getPatCurrentPositionStatus()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCurrentPositionStatus(" + mypatientvisit.getPatCurrentPositionStatus() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCurrentPositionStatus(" + patientvisit.getPatCurrentPositionStatus() + "),";          
            
                    mypatientvisit.setPatCurrentPositionStatus(patientvisit.getPatCurrentPositionStatus());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCurrentPositionStatus(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCurrentPositionStatus(" + patientvisit.getPatCurrentPositionStatus() + "),";          
        
                mypatientvisit.setPatCurrentPositionStatus(patientvisit.getPatCurrentPositionStatus());
            }
        }
        
        //PV1-3.6:PAT_CURRENT_POSITION_TYPE
        if(isPopulated(patientvisit.getPatCurrentPositionType()))
        {
            if(isPopulated(mypatientvisit.getPatCurrentPositionType()))
            {
                if(!mypatientvisit.getPatCurrentPositionType().equalsIgnoreCase(patientvisit.getPatCurrentPositionType()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCurrentPositionType(" + mypatientvisit.getPatCurrentPositionType() + "),";
                    
                    NewVisitInfo = NewVisitInfo + "PatCurrentPositionType(" + patientvisit.getPatCurrentPositionType() + "),";          
            
                    mypatientvisit.setPatCurrentPositionType(patientvisit.getPatCurrentPositionType());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCurrentPositionType(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCurrentPositionType(" + patientvisit.getPatCurrentPositionType() + "),";          
        
                mypatientvisit.setPatCurrentPositionType(patientvisit.getPatCurrentPositionType());
            }
        }
        
        //PV1-3.7:PAT_CURRENT_BUILDING
        if(isPopulated(patientvisit.getPatCurrentBuilding()))
        {
            if(isPopulated(mypatientvisit.getPatCurrentBuilding()))
            {
                if(!mypatientvisit.getPatCurrentBuilding().equalsIgnoreCase(patientvisit.getPatCurrentBuilding()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCurrentBuilding(" + mypatientvisit.getPatCurrentBuilding() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCurrentBuilding(" + patientvisit.getPatCurrentBuilding() + "),";          
            
                    mypatientvisit.setPatCurrentBuilding(patientvisit.getPatCurrentBuilding());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCurrentBuilding(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCurrentBuilding(" + patientvisit.getPatCurrentBuilding() + "),";          
            
                mypatientvisit.setPatCurrentBuilding(patientvisit.getPatCurrentBuilding());
            }
        }
        
        //PV1-3.8:PAT_CURRENT_FLOOR
        if(isPopulated(patientvisit.getPatCurrentFloor()))
        {
            if(isPopulated(mypatientvisit.getPatCurrentFloor()))
            {
                if(!mypatientvisit.getPatCurrentFloor().equalsIgnoreCase(patientvisit.getPatCurrentFloor()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCurrentFloor(" + mypatientvisit.getPatCurrentFloor() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCurrentFloor(" + patientvisit.getPatCurrentFloor() + "),";            
            
                    mypatientvisit.setPatCurrentFloor(patientvisit.getPatCurrentFloor());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCurrentFloor(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCurrentFloor(" + patientvisit.getPatCurrentFloor() + "),";            
        
                mypatientvisit.setPatCurrentFloor(patientvisit.getPatCurrentFloor());
            }
        }
        
        //PV1-3.9:PAT_CURRENT_DESCRIPTION
        if(isPopulated(patientvisit.getPatCuurentDescription()))
        {
            if(isPopulated(mypatientvisit.getPatCuurentDescription()))
            {
                if(!mypatientvisit.getPatCuurentDescription().equalsIgnoreCase(patientvisit.getPatCuurentDescription()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCuurentDescription(" + mypatientvisit.getPatCuurentDescription() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCuurentDescription(" + patientvisit.getPatCuurentDescription() + "),";            
            
                    mypatientvisit.setPatCuurentDescription(patientvisit.getPatCuurentDescription());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCuurentDescription(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCuurentDescription(" + patientvisit.getPatCuurentDescription() + "),";            
        
                mypatientvisit.setPatCuurentDescription(patientvisit.getPatCuurentDescription());
            }
        }
        
        //PV1-4.1:PAT_ADMISSION_TYPE
        if(isPopulated(patientvisit.getPatAdmissionType()))
        {
            if(isPopulated(mypatientvisit.getPatAdmissionType()))
            {
                if(!mypatientvisit.getPatAdmissionType().equalsIgnoreCase(patientvisit.getPatAdmissionType()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAdmissionType(" + mypatientvisit.getPatAdmissionType() + "),";
                    
                    NewVisitInfo = NewVisitInfo + "PatAdmissionType(" + patientvisit.getPatAdmissionType() + "),";          
            
                    mypatientvisit.setPatAdmissionType(patientvisit.getPatAdmissionType());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAdmissionType(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAdmissionType(" + patientvisit.getPatAdmissionType() + "),";          
        
                mypatientvisit.setPatAdmissionType(patientvisit.getPatAdmissionType());
            }
        }
        
        //PV1-5.1:PAT_ADMISSION NUMBER
        if(isPopulated(patientvisit.getPatAdmissionNumber()))
        {
            if(isPopulated(mypatientvisit.getPatAdmissionNumber()))
            {
                if(!mypatientvisit.getPatAdmissionNumber().equalsIgnoreCase(patientvisit.getPatAdmissionNumber()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAdmissionNumber(" + mypatientvisit.getPatAdmissionNumber() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAdmissionNumber(" + patientvisit.getPatAdmissionNumber() + "),";          
            
                    mypatientvisit.setPatAdmissionNumber(patientvisit.getPatAdmissionNumber());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAdmissionNumber(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAdmissionNumber(" + patientvisit.getPatAdmissionNumber() + "),";          
        
                mypatientvisit.setPatAdmissionNumber(patientvisit.getPatAdmissionNumber());
            }
        }
        
        //PV1-6.1:PAT_FORMER_POINT_OF_CARE
        if(isPopulated(patientvisit.getPatFormerPointOfCare()))
        {
            if(isPopulated(mypatientvisit.getPatFormerPointOfCare()))
            {
                if(!mypatientvisit.getPatFormerPointOfCare().equalsIgnoreCase(patientvisit.getPatFormerPointOfCare()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerPointOfCare(" + mypatientvisit.getPatFormerPointOfCare() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerPointOfCare(" + patientvisit.getPatFormerPointOfCare() + "),";          
            
                    mypatientvisit.setPatFormerPointOfCare(patientvisit.getPatFormerPointOfCare());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerPointOfCare(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerPointOfCare(" + patientvisit.getPatFormerPointOfCare() + "),";          
            
                mypatientvisit.setPatFormerPointOfCare(patientvisit.getPatFormerPointOfCare());
            }
        }
        
        //PV1-6.2:PAT_FORMER_ROOM
        if(isPopulated(patientvisit.getPatFormerRoom()))
        {
            if(isPopulated(mypatientvisit.getPatFormerRoom()))
            {
                if(!mypatientvisit.getPatFormerRoom().equalsIgnoreCase(patientvisit.getPatFormerRoom()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerRoom(" + mypatientvisit.getPatFormerRoom() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerRoom(" + patientvisit.getPatFormerRoom() + "),";            
            
                    mypatientvisit.setPatFormerRoom(patientvisit.getPatFormerRoom());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerRoom(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerRoom(" + patientvisit.getPatFormerRoom() + "),";            
        
                mypatientvisit.setPatFormerRoom(patientvisit.getPatFormerRoom());
            }
        
        }
        
        //PV1-6.3:PAT_FORMER_BED
        if(isPopulated(patientvisit.getPatFormerBed()))
        {
            if(isPopulated(mypatientvisit.getPatFormerBed()))
            {
                if(!mypatientvisit.getPatFormerBed().equalsIgnoreCase(patientvisit.getPatFormerBed()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerBed(" + mypatientvisit.getPatFormerBed() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerBed(" + patientvisit.getPatFormerBed() + "),";          
            
                    mypatientvisit.setPatFormerBed(patientvisit.getPatFormerBed());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerBed(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerBed(" + patientvisit.getPatFormerBed() + "),";          
        
                mypatientvisit.setPatFormerBed(patientvisit.getPatFormerBed());
            }
        }
        
        //PV1-6.4:PAT_FORMER_DEP
        if(isPopulated(patientvisit.getPatFormerDep()))
        {
            if(isPopulated(mypatientvisit.getPatFormerDep()))
            {
                if(!mypatientvisit.getPatFormerDep().equalsIgnoreCase(patientvisit.getPatFormerDep()))
                {
                    OldVisitInfo = OldVisitInfo + "FormerDep(" + mypatientvisit.getPatFormerDep() + "),";
                
                    NewVisitInfo = NewVisitInfo + "FormerDep(" + patientvisit.getPatFormerDep() + "),";         
            
                    mypatientvisit.setPatFormerDep(patientvisit.getPatFormerDep());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "FormerDep(空值),";
                
                NewVisitInfo = NewVisitInfo + "FormerDep(" + patientvisit.getPatFormerDep() + "),";         
        
                mypatientvisit.setPatFormerDep(patientvisit.getPatFormerDep());
            }
        }
        
        //PV1-6.5:pat_former_position_status
        if(isPopulated(patientvisit.getPatFormerPositionStatus()))
        {
            if(isPopulated(mypatientvisit.getPatFormerPositionStatus()))
            {
                if(!mypatientvisit.getPatFormerPositionStatus().equalsIgnoreCase(patientvisit.getPatFormerPositionStatus()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerPositionStatus(" + mypatientvisit.getPatFormerPositionStatus() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerPositionStatus(" + patientvisit.getPatFormerPositionStatus() + "),";            
            
                    mypatientvisit.setPatFormerPositionStatus(patientvisit.getPatFormerPositionStatus());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerPositionStatus(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerPositionStatus(" + patientvisit.getPatFormerPositionStatus() + "),";            
        
                mypatientvisit.setPatFormerPositionStatus(patientvisit.getPatFormerPositionStatus());
            }
        }
        
        //PV1-6.6:PAT_FORMER_POSITION_TYPE
        if(isPopulated(patientvisit.getPatFormerPositionType()))
        {
            if(isPopulated(mypatientvisit.getPatFormerPositionType()))
            {
                if(!mypatientvisit.getPatFormerPositionType().equalsIgnoreCase(patientvisit.getPatFormerPositionType()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerPositionType(" + mypatientvisit.getPatFormerPositionType() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerPositionType(" + patientvisit.getPatFormerPositionType() + "),";            
            
                    mypatientvisit.setPatFormerPositionType(patientvisit.getPatFormerPositionType());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerPositionType(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerPositionType(" + patientvisit.getPatFormerPositionType() + "),";            
        
                mypatientvisit.setPatFormerPositionType(patientvisit.getPatFormerPositionType());
            }
        }
        
        //PV1-6.7:PAT_FORMER_BUILDING
        if(isPopulated(patientvisit.getPatFormerBuilding()))
        {
            if(isPopulated(mypatientvisit.getPatFormerBuilding()))
            {
                if(!mypatientvisit.getPatFormerBuilding().equalsIgnoreCase(patientvisit.getPatFormerBuilding()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerBuilding(" + mypatientvisit.getPatFormerBuilding() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerBuilding(" + patientvisit.getPatFormerBuilding() + "),";            
            
                    mypatientvisit.setPatFormerBuilding(patientvisit.getPatFormerBuilding());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerBuilding(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerBuilding(" + patientvisit.getPatFormerBuilding() + "),";            
        
                mypatientvisit.setPatFormerBuilding(patientvisit.getPatFormerBuilding());
            }
        }
        
        //PV1-6.8:PAT_FORMER_FLOOR
        if(isPopulated(patientvisit.getPatFormerFloor()))
        {
            if(isPopulated(mypatientvisit.getPatFormerFloor()))
            {
                if(!mypatientvisit.getPatFormerFloor().equalsIgnoreCase(patientvisit.getPatFormerFloor()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerFloor(" + mypatientvisit.getPatFormerFloor() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerFloor(" + patientvisit.getPatFormerFloor() + "),";          
            
                    mypatientvisit.setPatFormerFloor(patientvisit.getPatFormerFloor());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerFloor(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerFloor(" + patientvisit.getPatFormerFloor() + "),";          
        
                mypatientvisit.setPatFormerFloor(patientvisit.getPatFormerFloor());
            }
        }
        
        //PV1-6.9:PAT_FORMER_DESCRIPTION
        if(isPopulated(patientvisit.getPatFormerDescription()))
        {
            if(isPopulated(mypatientvisit.getPatFormerDescription()))
            {
                if(!mypatientvisit.getPatFormerDescription().equalsIgnoreCase(patientvisit.getPatFormerDescription()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFormerDescription(" + mypatientvisit.getPatFormerDescription() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFormerDescription(" + patientvisit.getPatFormerDescription() + "),";          
            
                    mypatientvisit.setPatFormerDescription(patientvisit.getPatFormerDescription());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFormerDescription(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFormerDescription(" + patientvisit.getPatFormerDescription() + "),";          
        
                mypatientvisit.setPatFormerDescription(patientvisit.getPatFormerDescription());
            }
        }
        
        //PV1-7.1:ADMISSIONS_DOCTOR_ID
        if(isPopulated(patientvisit.getAdmissionsDoctorId()))
        {
            if(isPopulated(mypatientvisit.getAdmissionsDoctorId()))
            {
                if(!mypatientvisit.getAdmissionsDoctorId().equalsIgnoreCase(patientvisit.getAdmissionsDoctorId()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmissionsDoctorId(" + mypatientvisit.getAdmissionsDoctorId() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmissionsDoctorId(" + patientvisit.getAdmissionsDoctorId() + "),";          
            
                    mypatientvisit.setAdmissionsDoctorId(patientvisit.getAdmissionsDoctorId());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmissionsDoctorId(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmissionsDoctorId(" + patientvisit.getAdmissionsDoctorId() + "),";          
        
                mypatientvisit.setAdmissionsDoctorId(patientvisit.getAdmissionsDoctorId());
            }
        }
        
        //PV1-7.2:ADMISSIONS_DOCTOR
        if(isPopulated(patientvisit.getAdmissionsDoctor()))
        {
            if(isPopulated(mypatientvisit.getAdmissionsDoctor()))
            {
                if(!mypatientvisit.getAdmissionsDoctor().equalsIgnoreCase(patientvisit.getAdmissionsDoctor()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmissionsDoctor(" + mypatientvisit.getAdmissionsDoctor() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmissionsDoctor(" + patientvisit.getAdmissionsDoctor() + "),";          
            
                    mypatientvisit.setAdmissionsDoctor(patientvisit.getAdmissionsDoctor());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmissionsDoctor(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmissionsDoctor(" + patientvisit.getAdmissionsDoctor() + "),";          
        
                mypatientvisit.setAdmissionsDoctor(patientvisit.getAdmissionsDoctor());
            }
        }
        
        //PV1-8.1:REFERRING_DOCTOR_ID
        if(isPopulated(patientvisit.getReferringDoctorId()))
        {
            if(isPopulated(mypatientvisit.getReferringDoctorId()))
            {
                if(!mypatientvisit.getReferringDoctorId().equalsIgnoreCase(patientvisit.getReferringDoctorId()))
                {
                    OldVisitInfo = OldVisitInfo + "ReferringDoctorId(" + mypatientvisit.getReferringDoctorId() + "),";
                
                    NewVisitInfo = NewVisitInfo + "ReferringDoctorId(" + patientvisit.getReferringDoctorId() + "),";            
            
                    mypatientvisit.setReferringDoctorId(patientvisit.getReferringDoctorId());
            }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ReferringDoctorId(空值),";
                
                NewVisitInfo = NewVisitInfo + "ReferringDoctorId(" + patientvisit.getReferringDoctorId() + "),";            
        
                mypatientvisit.setReferringDoctorId(patientvisit.getReferringDoctorId());
            }
        }
        
        //PV1-8.2:REFERRING_DOCTOR
        if(isPopulated(patientvisit.getReferringDoctor()))
        {
            if(isPopulated(mypatientvisit.getReferringDoctor()))
            {
                if(!mypatientvisit.getReferringDoctor().equalsIgnoreCase(patientvisit.getReferringDoctor()))
                {
                    OldVisitInfo = OldVisitInfo + "ReferringDoctor(" + mypatientvisit.getReferringDoctor() + "),";
                
                    NewVisitInfo = NewVisitInfo + "ReferringDoctor(" + patientvisit.getReferringDoctor() + "),";            
            
                    mypatientvisit.setReferringDoctor(patientvisit.getReferringDoctor());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ReferringDoctor(空值),";
                
                NewVisitInfo = NewVisitInfo + "ReferringDoctor(" + patientvisit.getReferringDoctor() + "),";            
        
                mypatientvisit.setReferringDoctor(patientvisit.getReferringDoctor());
            }
        }
        
        //PV1-9.1:CONSULATATION_DOCTOR_ID
        if(isPopulated(patientvisit.getConsultationDoctorId()))
        {
            if(isPopulated(mypatientvisit.getConsultationDoctorId()))
            {
                if(!mypatientvisit.getConsultationDoctorId().equalsIgnoreCase(patientvisit.getConsultationDoctorId()))
                {
                    OldVisitInfo = OldVisitInfo + "ConsultationDoctorId(" + mypatientvisit.getConsultationDoctorId() + "),";
                    
                    NewVisitInfo = NewVisitInfo + "ConsultationDoctorId(" + patientvisit.getConsultationDoctorId() + "),";          
            
                    mypatientvisit.setConsultationDoctorId(patientvisit.getConsultationDoctorId());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ConsultationDoctorId(空值),";
                
                NewVisitInfo = NewVisitInfo + "ConsultationDoctorId(" + patientvisit.getConsultationDoctorId() + "),";          
        
                mypatientvisit.setConsultationDoctorId(patientvisit.getConsultationDoctorId());
            }
        }
        
        //PV1-9.2:CONSULATATON_DOCTOR
        if(isPopulated(patientvisit.getConsultationDoctor()))
        {
            if(isPopulated(mypatientvisit.getConsultationDoctor()))
            {
                if(!mypatientvisit.getConsultationDoctor().equalsIgnoreCase(patientvisit.getConsultationDoctor()))
                {
                    OldVisitInfo = OldVisitInfo + "ConsultationDoctor(" + mypatientvisit.getConsultationDoctor() + "),";
                
                    NewVisitInfo = NewVisitInfo + "ConsultationDoctor(" + patientvisit.getConsultationDoctor() + "),";          
            
                    mypatientvisit.setConsultationDoctor(patientvisit.getConsultationDoctor());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ConsultationDoctor(空值),";
                
                NewVisitInfo = NewVisitInfo + "ConsultationDoctor(" + patientvisit.getConsultationDoctor() + "),";          
        
                mypatientvisit.setConsultationDoctor(patientvisit.getConsultationDoctor());
            }
        }
        
        //PV1-10:HOSPITAL_SERVICE
        if(isPopulated(patientvisit.getHospitalService()))
        {
            if(isPopulated(mypatientvisit.getHospitalService()))
            {
                if(!mypatientvisit.getHospitalService().equalsIgnoreCase(patientvisit.getHospitalService()))
                {
                    OldVisitInfo = OldVisitInfo + "HospitalService(" + mypatientvisit.getHospitalService() + "),";
                
                    NewVisitInfo = NewVisitInfo + "HospitalService(" + patientvisit.getHospitalService() + "),";            
            
                    mypatientvisit.setHospitalService(patientvisit.getHospitalService());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "HospitalService(空值),";
                
                NewVisitInfo = NewVisitInfo + "HospitalService(" + patientvisit.getHospitalService() + "),";            
        
                mypatientvisit.setHospitalService(patientvisit.getHospitalService());
            }
        }
        
        //PV1-11.1:pat_temp_point_of care
        if(isPopulated(patientvisit.getPatTempPointOfCare()))
        {
            if(isPopulated(mypatientvisit.getPatTempPointOfCare()))
            {
                if(!mypatientvisit.getPatTempPointOfCare().equalsIgnoreCase(patientvisit.getPatTempPointOfCare()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempPointOfCare(" + mypatientvisit.getPatTempPointOfCare() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempPointOfCare(" + patientvisit.getPatTempPointOfCare() + "),";          
            
                    mypatientvisit.setPatTempPointOfCare(patientvisit.getPatTempPointOfCare());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempPointOfCare(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempPointOfCare(" + patientvisit.getPatTempPointOfCare() + "),";          
        
                mypatientvisit.setPatTempPointOfCare(patientvisit.getPatTempPointOfCare());
            }
        }
        
        //PV1-11.2:PAT_TEMP_ROOM
        if(isPopulated(patientvisit.getPatTempRoom()))
        {
            if(isPopulated(mypatientvisit.getPatTempRoom()))
            {
                if(!mypatientvisit.getPatTempRoom().equalsIgnoreCase(patientvisit.getPatTempRoom()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempRoom(" + mypatientvisit.getPatTempRoom() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempRoom(" + patientvisit.getPatTempRoom() + "),";            
            
                    mypatientvisit.setPatTempRoom(patientvisit.getPatTempRoom());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempRoom(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempRoom(" + patientvisit.getPatTempRoom() + "),";            
        
                mypatientvisit.setPatTempRoom(patientvisit.getPatTempRoom());
            }
        }
        
        //PV1-11.3:PAT_TEMP_BED
        if(isPopulated(patientvisit.getPatTempBed()))
        {
            if(isPopulated(mypatientvisit.getPatTempBed()))
            {
                if(!mypatientvisit.getPatTempBed().equalsIgnoreCase(patientvisit.getPatTempBed()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempBed(" + mypatientvisit.getPatTempBed() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempBed(" + patientvisit.getPatTempBed() + "),";          
            
                    mypatientvisit.setPatTempBed(patientvisit.getPatTempBed());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempBed(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempBed(" + patientvisit.getPatTempBed() + "),";          
        
                mypatientvisit.setPatTempBed(patientvisit.getPatTempBed());
            }
        }
        
        //PV1-11.4:PAT_TEMP_DEP
        if(isPopulated(patientvisit.getPatTempDep()))
        {
            if(isPopulated(mypatientvisit.getPatTempDep()))
            {
                if(!mypatientvisit.getPatTempDep().equalsIgnoreCase(patientvisit.getPatTempDep()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempDep(" + mypatientvisit.getPatTempDep() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempDep(" + patientvisit.getPatTempDep() + "),";          
            
                    mypatientvisit.setPatTempDep(patientvisit.getPatTempDep());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempDep(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempDep(" + patientvisit.getPatTempDep() + "),";          
        
                mypatientvisit.setPatTempDep(patientvisit.getPatTempDep());
            }
        }
        
        //PV1-11.5:PAT_TEMP_POSITION_STATUS
        if(isPopulated(patientvisit.getPatTempPositionStatus()))
        {
            if(isPopulated(mypatientvisit.getPatTempPositionStatus()))
            {
                if(!mypatientvisit.getPatTempPositionStatus().equalsIgnoreCase(patientvisit.getPatTempPositionStatus()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempPositionStatus(" + mypatientvisit.getPatTempPositionStatus() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempPositionStatus(" + patientvisit.getPatTempPositionStatus() + "),";            
            
                    mypatientvisit.setPatTempPositionStatus(patientvisit.getPatTempPositionStatus());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempPositionStatus(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempPositionStatus(" + patientvisit.getPatTempPositionStatus() + "),";            
        
                mypatientvisit.setPatTempPositionStatus(patientvisit.getPatTempPositionStatus());
            }
        }
        
        //PV1-11.6:PAT_TEMP_POSITION_TYPE
        if(isPopulated(patientvisit.getPatTempPositionType()))
        {
            if(isPopulated(mypatientvisit.getPatTempPositionType()))
            {
                if(!mypatientvisit.getPatTempPositionType().equalsIgnoreCase(patientvisit.getPatTempPositionType()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempPositionType(" + mypatientvisit.getPatTempPositionType() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempPositionType(" + patientvisit.getPatTempPositionType() + "),";            
            
                    mypatientvisit.setPatTempPositionType(patientvisit.getPatTempPositionType());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempPositionType(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempPositionType(" + patientvisit.getPatTempPositionType() + "),";            
        
                mypatientvisit.setPatTempPositionType(patientvisit.getPatTempPositionType());
            }
        }
        
        //PV1-11.7:PAT_TEMP_BUILDING
        if(isPopulated(patientvisit.getPatTempBuilding()))
        {
            if(isPopulated(mypatientvisit.getPatTempBuilding()))
            {
                if(!mypatientvisit.getPatTempBuilding().equalsIgnoreCase(patientvisit.getPatTempBuilding()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempBuilding(" + mypatientvisit.getPatTempBuilding() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempBuilding(" + patientvisit.getPatTempBuilding() + "),";            
            
                    mypatientvisit.setPatTempBuilding(patientvisit.getPatTempBuilding());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempBuilding(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempBuilding(" + patientvisit.getPatTempBuilding() + "),";            
        
                mypatientvisit.setPatTempBuilding(patientvisit.getPatTempBuilding());
            }
        }
        
        //PV1-11.8:PAT_TEMP_FLOOR
        if(isPopulated(patientvisit.getPatTempFloor()))
        {
            if(isPopulated(mypatientvisit.getPatTempFloor()))
            {
                if(!mypatientvisit.getPatTempFloor().equalsIgnoreCase(patientvisit.getPatTempFloor()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempFloor(" + mypatientvisit.getPatTempFloor() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempFloor(" + patientvisit.getPatTempFloor() + "),";          
            
                    mypatientvisit.setPatTempFloor(patientvisit.getPatTempFloor());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempFloor(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempFloor(" + patientvisit.getPatTempFloor() + "),";          
        
                mypatientvisit.setPatTempFloor(patientvisit.getPatTempFloor());
            }
        }
        
        //PV1-11.9:PAT_TEMP_DESCRIPTION
        if(isPopulated(patientvisit.getPatTempDescription()))
        {
            if(isPopulated(mypatientvisit.getPatTempDescription()))
            {
                if(!mypatientvisit.getPatTempDescription().equalsIgnoreCase(patientvisit.getPatTempDescription()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTempDescription(" + mypatientvisit.getPatTempDescription() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTempDescription(" + patientvisit.getPatTempDescription() + "),";          
            
                    mypatientvisit.setPatTempDescription(patientvisit.getPatTempDescription());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTempDescription(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTempDescription(" + patientvisit.getPatTempDescription() + "),";          
        
                mypatientvisit.setPatTempDescription(patientvisit.getPatTempDescription());
            }
        }
        
        //PV1-12.1:PAT_ADMISSION_TEST
        if(isPopulated(patientvisit.getPatAdmissionTest()))
        {
            if(isPopulated(mypatientvisit.getPatAdmissionTest()))
            {
                if(!mypatientvisit.getPatAdmissionTest().equalsIgnoreCase(patientvisit.getPatAdmissionTest()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAdmissionTest(" + mypatientvisit.getPatAdmissionTest() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAdmissionTest(" + patientvisit.getPatAdmissionTest() + "),";          
            
                    mypatientvisit.setPatAdmissionTest(patientvisit.getPatAdmissionTest());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAdmissionTest(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAdmissionTest(" + patientvisit.getPatAdmissionTest() + "),";          
        
                mypatientvisit.setPatAdmissionTest(patientvisit.getPatAdmissionTest());
            }
        }
        
        //PV1-13.1:PAT_IP_TIMES
        if(isPopulated(patientvisit.getPatIpTimes()))
        {
            if(isPopulated(mypatientvisit.getPatIpTimes()))
            {
                if(!mypatientvisit.getPatIpTimes().equalsIgnoreCase(patientvisit.getPatIpTimes()))
                {
                    OldVisitInfo = OldVisitInfo + "PatIpTimes(" + mypatientvisit.getPatIpTimes() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatIpTimes(" + patientvisit.getPatIpTimes() + "),";          
            
                    mypatientvisit.setPatIpTimes(patientvisit.getPatIpTimes());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatIpTimes(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatIpTimes(" + patientvisit.getPatIpTimes() + "),";          
        
                mypatientvisit.setPatIpTimes(patientvisit.getPatIpTimes());
            }
        }
        
        //PV1-14.1:PAT_ADMISSION_SOURCE
        if(isPopulated(patientvisit.getPatAdmissionSource()))
        {
            if(isPopulated(mypatientvisit.getPatAdmissionSource()))
            {
                if(!mypatientvisit.getPatAdmissionSource().equalsIgnoreCase(patientvisit.getPatAdmissionSource()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAdmissionSource(" + mypatientvisit.getPatAdmissionSource() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAdmissionSource(" + patientvisit.getPatAdmissionSource() + "),";          
            
                    mypatientvisit.setPatAdmissionSource(patientvisit.getPatAdmissionSource());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAdmissionSource(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAdmissionSource(" + patientvisit.getPatAdmissionSource() + "),";          
        
                mypatientvisit.setPatAdmissionSource(patientvisit.getPatAdmissionSource());
            }
        }
        
        //PV1-15.1:离院处置名称
        if(isPopulated(patientvisit.getDischargeName()))
        {
            if(isPopulated(mypatientvisit.getDischargeName()))
            {
                if(!mypatientvisit.getDischargeName().equalsIgnoreCase(patientvisit.getDischargeName()))
                {
                    OldVisitInfo = OldVisitInfo + "DischargeName(" + mypatientvisit.getDischargeName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "DischargeName(" + patientvisit.getDischargeName() + "),";            
            
                    mypatientvisit.setDischargeName(patientvisit.getDischargeName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "DischargeName(空值),";
                
                NewVisitInfo = NewVisitInfo + "DischargeName(" + patientvisit.getDischargeName() + "),";            
        
                mypatientvisit.setDischargeName(patientvisit.getDischargeName());
            }
        }
        
        //离院处置编码系统
        if(isPopulated(patientvisit.getDischargeDomain()))
        {
            if(isPopulated(mypatientvisit.getDischargeDomain()))
            {
                if(!mypatientvisit.getDischargeDomain().equalsIgnoreCase(patientvisit.getDischargeDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "DischargeDomain(" + mypatientvisit.getDischargeDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "DischargeDomain(" + patientvisit.getDischargeDomain() + "),";            
            
                    mypatientvisit.setDischargeDomain(patientvisit.getDischargeDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "DischargeDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "DischargeDomain(" + patientvisit.getDischargeDomain() + "),";            
        
                mypatientvisit.setDischargeDomain(patientvisit.getDischargeDomain());
            }
        }
        
        //入院时情况名称
        if(isPopulated(patientvisit.getAdmissionName()))
        {
            if(isPopulated(mypatientvisit.getAdmissionName()))
            {
                if(!mypatientvisit.getAdmissionName().equalsIgnoreCase(patientvisit.getAdmissionName()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmissionName(" + mypatientvisit.getAdmissionName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmissionName(" + patientvisit.getAdmissionName() + "),";            
            
                    mypatientvisit.setAdmissionName(patientvisit.getAdmissionName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmissionName(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmissionName(" + patientvisit.getAdmissionName() + "),";            
        
                mypatientvisit.setAdmissionName(patientvisit.getAdmissionName());
            }
        }
        
        //入院时情况编码系统
        if(isPopulated(patientvisit.getAdmissionDomain()))
        {
            if(isPopulated(mypatientvisit.getAdmissionDomain()))
            {
                if(!mypatientvisit.getAdmissionDomain().equalsIgnoreCase(patientvisit.getAdmissionDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmissionDomain(" + mypatientvisit.getAdmissionDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmissionDomain(" + patientvisit.getAdmissionDomain() + "),";            
            
                    mypatientvisit.setAdmissionDomain(patientvisit.getAdmissionName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmissionDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmissionDomain(" + patientvisit.getAdmissionDomain() + "),";            
        
                mypatientvisit.setAdmissionDomain(patientvisit.getAdmissionDomain());
            }
        }
        
        //病人住院状态名称
        if(isPopulated(patientvisit.getIpStatusName()))
        {
            if(isPopulated(mypatientvisit.getIpStatusName()))
            {
                if(!mypatientvisit.getIpStatusName().equalsIgnoreCase(patientvisit.getIpStatusName()))
                {
                    OldVisitInfo = OldVisitInfo + "IpStatusName(" + mypatientvisit.getIpStatusName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "IpStatusName(" + patientvisit.getIpStatusName() + "),";          
            
                    mypatientvisit.setIpStatusName(patientvisit.getIpStatusName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "IpStatusName(空值),";
                
                NewVisitInfo = NewVisitInfo + "IpStatusName(" + patientvisit.getIpStatusName() + "),";          
        
                mypatientvisit.setIpStatusName(patientvisit.getIpStatusName());
            }
        }
        
        //病人住院状态编码系统
        if(isPopulated(patientvisit.getIpStatusDomain()))
        {
            if(isPopulated(mypatientvisit.getIpStatusDomain()))
            {
                if(!mypatientvisit.getIpStatusDomain().equalsIgnoreCase(patientvisit.getIpStatusDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "IpStatusDomain(" + mypatientvisit.getIpStatusDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "IpStatusDomain(" + patientvisit.getIpStatusDomain() + "),";          
            
                    mypatientvisit.setIpStatusDomain(patientvisit.getIpStatusDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "IpStatusDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "IpStatusDomain(" + patientvisit.getIpStatusDomain() + "),";          
        
                mypatientvisit.setIpStatusDomain(patientvisit.getIpStatusDomain());
            }
        }
        
        //病例分型名称
        if(isPopulated(patientvisit.getDificultyName()))
        {
            if(isPopulated(mypatientvisit.getDificultyName()))
            {
                if(!mypatientvisit.getDificultyName().equalsIgnoreCase(patientvisit.getDificultyName()))
                {
                    OldVisitInfo = OldVisitInfo + "DificultyName(" + mypatientvisit.getDificultyName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "DificultyName(" + patientvisit.getDificultyName() + "),";            
            
                    mypatientvisit.setDificultyName(patientvisit.getDificultyName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "DificultyName(空值),";
                
                NewVisitInfo = NewVisitInfo + "DificultyName(" + patientvisit.getDificultyName() + "),";            
        
                mypatientvisit.setDificultyName(patientvisit.getDificultyName());
            }
        }
        
        //病例分型编码系统
        if(isPopulated(patientvisit.getDificultyDomain()))
        {
            if(isPopulated(mypatientvisit.getDificultyDomain()))
            {
                if(!mypatientvisit.getDificultyDomain().equalsIgnoreCase(patientvisit.getDificultyDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "DificultyDomain(" + mypatientvisit.getDificultyDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "DificultyDomain(" + patientvisit.getDificultyDomain() + "),";            
            
                    mypatientvisit.setDificultyDomain(patientvisit.getDificultyDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "DificultyDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "DificultyDomain(" + patientvisit.getDificultyDomain() + "),";            
        
                mypatientvisit.setDificultyDomain(patientvisit.getDificultyDomain());
            }
        }
        
        //入院途径名称
        if(isPopulated(patientvisit.getAdmissionSourceName()))
        {
            if(isPopulated(mypatientvisit.getAdmissionSourceName()))
            {
                if(!mypatientvisit.getAdmissionSourceName().equalsIgnoreCase(patientvisit.getAdmissionSourceName()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmissionSourceName(" + mypatientvisit.getAdmissionSourceName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmissionSourceName(" + patientvisit.getAdmissionSourceName() + "),";            
            
                    mypatientvisit.setAdmissionSourceName(patientvisit.getAdmissionSourceName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmissionSourceName(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmissionSourceName(" + patientvisit.getAdmissionSourceName() + "),";            
        
                mypatientvisit.setAdmissionSourceName(patientvisit.getAdmissionSourceName());
            }
        }
        
        //入院途径编码系统
        if(isPopulated(patientvisit.getAdmissionSourceDomain()))
        {
            if(isPopulated(mypatientvisit.getAdmissionSourceDomain()))
            {
                if(!mypatientvisit.getAdmissionSourceDomain().equalsIgnoreCase(patientvisit.getAdmissionSourceDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmissionSourceDomain(" + mypatientvisit.getAdmissionSourceDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmissionSourceDomain(" + patientvisit.getAdmissionSourceDomain() + "),";            
            
                    mypatientvisit.setAdmissionSourceDomain(patientvisit.getAdmissionSourceDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmissionSourceDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmissionSourceDomain(" + patientvisit.getAdmissionSourceDomain() + "),";            
        
                mypatientvisit.setAdmissionSourceDomain(patientvisit.getAdmissionSourceDomain());
            }
        }
        
        //支付方式编码
        if(isPopulated(patientvisit.getAccountStatusName()))
        {
            if(isPopulated(mypatientvisit.getAccountStatusName()))
            {
                if(!mypatientvisit.getAccountStatusName().equalsIgnoreCase(patientvisit.getAccountStatusName()))
                {
                    OldVisitInfo = OldVisitInfo + "AccountStatusName(" + mypatientvisit.getAccountStatusName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AccountStatusName(" + patientvisit.getAccountStatusName() + "),";            
            
                    mypatientvisit.setAccountStatusName(patientvisit.getAccountStatusName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AccountStatusName(空值),";
                
                NewVisitInfo = NewVisitInfo + "AccountStatusName(" + patientvisit.getAccountStatusName() + "),";            
        
                mypatientvisit.setAccountStatusName(patientvisit.getAccountStatusName());
            }
        }
        
        //支付方式编码系统
        if(isPopulated(patientvisit.getAccountStatusDomain()))
        {
            if(isPopulated(mypatientvisit.getAccountStatusDomain()))
            {
                if(!mypatientvisit.getAccountStatusDomain().equalsIgnoreCase(patientvisit.getAccountStatusDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "AccountStatusDomain(" + mypatientvisit.getAccountStatusDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AccountStatusDomain(" + patientvisit.getAccountStatusDomain() + "),";            
            
                    mypatientvisit.setAccountStatusDomain(patientvisit.getAccountStatusDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AccountStatusDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "AccountStatusDomain(" + patientvisit.getAccountStatusDomain() + "),";            
        
                mypatientvisit.setAccountStatusDomain(patientvisit.getAccountStatusDomain());
            }
        }
        
        //患者类别名称
        if(isPopulated(patientvisit.getPatCategoryName()))
        {
            if(isPopulated(mypatientvisit.getPatCategoryName()))
            {
                if(!mypatientvisit.getPatCategoryName().equalsIgnoreCase(patientvisit.getPatCategoryName()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCategoryName(" + mypatientvisit.getPatCategoryName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCategoryName(" + patientvisit.getPatCategoryName() + "),";            
            
                    mypatientvisit.setPatCategoryName(patientvisit.getPatCategoryName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCategoryName(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCategoryName(" + patientvisit.getPatCategoryName() + "),";            
        
                mypatientvisit.setPatCategoryName(patientvisit.getPatCategoryName());
            }
        }
        
        //患者类别编码系统
        if(isPopulated(patientvisit.getPatCategorySystem()))
        {
            if(isPopulated(mypatientvisit.getPatCategorySystem()))
            {
                if(!mypatientvisit.getPatCategorySystem().equalsIgnoreCase(patientvisit.getPatCategorySystem()))
                {
                    OldVisitInfo = OldVisitInfo + "PatCategorySystem(" + mypatientvisit.getPatCategorySystem() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatCategorySystem(" + patientvisit.getPatCategorySystem() + "),";            
            
                    mypatientvisit.setPatCategorySystem(patientvisit.getPatCategorySystem());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatCategorySystem(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatCategorySystem(" + patientvisit.getPatCategorySystem() + "),";            
        
                mypatientvisit.setPatCategorySystem(patientvisit.getPatCategorySystem());
            }
        }
        
        //是否急疹转住院
        if(isPopulated(patientvisit.getIsEmergency()))
        {
            if(isPopulated(mypatientvisit.getIsEmergency()))
            {
                if(!mypatientvisit.getIsEmergency().equalsIgnoreCase(patientvisit.getIsEmergency()))
                {
                    OldVisitInfo = OldVisitInfo + "IsEmergency(" + mypatientvisit.getIsEmergency() + "),";
                
                    NewVisitInfo = NewVisitInfo + "IsEmergency(" + patientvisit.getIsEmergency() + "),";            
            
                    mypatientvisit.setIsEmergency(patientvisit.getIsEmergency());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "IsEmergency(空值),";
                
                NewVisitInfo = NewVisitInfo + "IsEmergency(" + patientvisit.getIsEmergency() + "),";            
        
                mypatientvisit.setIsEmergency(patientvisit.getIsEmergency());
            }
        }
        
        if(isPopulated(patientvisit.getPatientSourceName()))
        {
            if(isPopulated(mypatientvisit.getPatientSourceName()))
            {
                if(!mypatientvisit.getPatientSourceName().equalsIgnoreCase(patientvisit.getPatientSourceName()))
                {
                    OldVisitInfo = OldVisitInfo + "PatientSourceName(" + mypatientvisit.getPatientSourceName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatientSourceName(" + patientvisit.getPatientSourceName() + "),";            
            
                    mypatientvisit.setPatientSourceName(patientvisit.getPatientSourceName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatientSourceName(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatientSourceName(" + patientvisit.getPatientSourceName() + "),";            
        
                mypatientvisit.setPatientSourceName(patientvisit.getPatientSourceName());
            }
        }
        
        if(isPopulated(patientvisit.getPatReAdmission()))
        {
            if(isPopulated(mypatientvisit.getPatReAdmission()))
            {
                if(!mypatientvisit.getPatReAdmission().equalsIgnoreCase(patientvisit.getPatReAdmission()))
                {
                    OldVisitInfo = OldVisitInfo + "PatReAdmission(" + mypatientvisit.getPatReAdmission() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatReAdmission(" + patientvisit.getPatReAdmission() + "),";          
            
                    mypatientvisit.setPatReAdmission(patientvisit.getPatReAdmission());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatReAdmission(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatReAdmission(" + patientvisit.getPatReAdmission() + "),";          
        
                mypatientvisit.setPatReAdmission(patientvisit.getPatReAdmission());
            }
        }
        
        
        
        //PV1-16.1:PAT_VIP
        if(isPopulated(patientvisit.getPatVip()))
        {
            if(isPopulated(mypatientvisit.getPatVip()))
            {
                if(!mypatientvisit.getPatVip().equalsIgnoreCase(patientvisit.getPatVip()))
                {
                    OldVisitInfo = OldVisitInfo + "PatVip(" + mypatientvisit.getPatVip() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatVip(" + patientvisit.getPatVip() + "),";          
            
                    mypatientvisit.setPatVip(patientvisit.getPatVip());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatVip(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatVip(" + patientvisit.getPatVip() + "),";          
        
                mypatientvisit.setPatVip(patientvisit.getPatVip());
            }
        }
        
        //PV1-17.1:PAT_ADMISSION_DOCTORS_ID
        if(isPopulated(patientvisit.getPatAdmissionDoctorsId()))
        {
            if(isPopulated(mypatientvisit.getPatAdmissionDoctorsId()))
            {
                if(!mypatientvisit.getPatAdmissionDoctorsId().equalsIgnoreCase(patientvisit.getPatAdmissionDoctorsId()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAdmissionDoctorsId(" + mypatientvisit.getPatAdmissionDoctorsId() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAdmissionDoctorsId(" + patientvisit.getPatAdmissionDoctorsId() + "),";            
            
                    mypatientvisit.setPatAdmissionDoctorsId(patientvisit.getPatAdmissionDoctorsId());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAdmissionDoctorsId(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAdmissionDoctorsId(" + patientvisit.getPatAdmissionDoctorsId() + "),";            
        
                mypatientvisit.setPatAdmissionDoctorsId(patientvisit.getPatAdmissionDoctorsId());
            }
        }
        
        //PV1-17.2:PAT_ADMISSION_DOCTORS
        if(isPopulated(patientvisit.getPatAdmissionDoctors()))
        {
            if(isPopulated(mypatientvisit.getPatAdmissionDoctors()))
            {
                if(!mypatientvisit.getPatAdmissionDoctors().equalsIgnoreCase(patientvisit.getPatAdmissionDoctors()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAdmissionDoctors(" + mypatientvisit.getPatAdmissionDoctors() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAdmissionDoctors(" + patientvisit.getPatAdmissionDoctors() + "),";            
            
                    mypatientvisit.setPatAdmissionDoctors(patientvisit.getPatAdmissionDoctors());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAdmissionDoctors(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAdmissionDoctors(" + patientvisit.getPatAdmissionDoctors() + "),";            
        
                mypatientvisit.setPatAdmissionDoctors(patientvisit.getPatAdmissionDoctors());
            }
        }
        
        //PV1-18.1:PATIENT_CLASS
        if(isPopulated(patientvisit.getPatientClass()))
        {
            if(isPopulated(mypatientvisit.getPatientClass()))
            {
                if(!mypatientvisit.getPatientClass().equalsIgnoreCase(patientvisit.getPatientClass()))
                {
                    OldVisitInfo = OldVisitInfo + "PatientClass(" + mypatientvisit.getPatientClass() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatientClass(" + patientvisit.getPatientClass() + "),";          
            
                    mypatientvisit.setPatientClass(patientvisit.getPatientClass());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatientClass(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatientClass(" + patientvisit.getPatientClass() + "),";          
        
                mypatientvisit.setPatientClass(patientvisit.getPatientClass());
            }
        }
        
//      //PV1-19.1:PATIENT_ID
//      if(isPopulated(patientvisit.getPatientId()))
//      {
//          if(!mypatientvisit.getPatientId().equalsIgnoreCase(patientvisit.getPatientId()))
//          {
//              OldVisitInfo = OldVisitInfo + "PatientId(" + mypatientvisit.getPatientId() + "),";
//              
//              NewVisitInfo = NewVisitInfo + "PatientId(" + patientvisit.getPatientId() + "),";            
//          
//              mypatientvisit.setPatientId(patientvisit.getPatientId());
//          }
//      }
        
        //PV1-20.1:PAT_FINANCIAL_CLASS
        if(isPopulated(patientvisit.getPatFinancialClass()))
        {
            if(isPopulated(mypatientvisit.getPatFinancialClass()))
            {
                if(!mypatientvisit.getPatFinancialClass().equalsIgnoreCase(patientvisit.getPatFinancialClass()))
                {
                    OldVisitInfo = OldVisitInfo + "PatFinancialClass(" + mypatientvisit.getPatFinancialClass() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatFinancialClass(" + patientvisit.getPatFinancialClass() + "),";            
            
                    mypatientvisit.setPatFinancialClass(patientvisit.getPatFinancialClass());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatFinancialClass(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatFinancialClass(" + patientvisit.getPatFinancialClass() + "),";            
        
                mypatientvisit.setPatFinancialClass(patientvisit.getPatFinancialClass());
            }
        }
        
        //PV1-21.1:ROOM_BED_COST_PRICE
        if(isPopulated(patientvisit.getRoomBedCostPrice()))
        {
            if(isPopulated(mypatientvisit.getRoomBedCostPrice()))
            {
                if(!mypatientvisit.getRoomBedCostPrice().equalsIgnoreCase(patientvisit.getRoomBedCostPrice()))
                {
                    OldVisitInfo = OldVisitInfo + "RoomBedCostPrice(" + mypatientvisit.getRoomBedCostPrice() + "),";
                
                    NewVisitInfo = NewVisitInfo + "RoomBedCostPrice(" + patientvisit.getRoomBedCostPrice() + "),";          
            
                    mypatientvisit.setRoomBedCostPrice(patientvisit.getRoomBedCostPrice());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "RoomBedCostPrice(空值),";
                
                NewVisitInfo = NewVisitInfo + "RoomBedCostPrice(" + patientvisit.getRoomBedCostPrice() + "),";          
        
                mypatientvisit.setRoomBedCostPrice(patientvisit.getRoomBedCostPrice());
            }
        }
        
        //PV1-22.1:COURTESY_CODE
        if(isPopulated(patientvisit.getCourtesyCode()))
        {
            if(isPopulated(mypatientvisit.getCourtesyCode()))
            {
                if(!mypatientvisit.getCourtesyCode().equalsIgnoreCase(patientvisit.getCourtesyCode()))
                {
                    OldVisitInfo = OldVisitInfo + "CourtesyCode(" + mypatientvisit.getCourtesyCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "CourtesyCode(" + patientvisit.getCourtesyCode() + "),";          
            
                    mypatientvisit.setCourtesyCode(patientvisit.getCourtesyCode());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "CourtesyCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "CourtesyCode(" + patientvisit.getCourtesyCode() + "),";          
        
                mypatientvisit.setCourtesyCode(patientvisit.getCourtesyCode());
            }
        }
        
        //PV1-23.1:CREDIT_RATING
        if(isPopulated(patientvisit.getCreditRating()))
        {
            if(isPopulated(mypatientvisit.getCreditRating()))
            {
                if(!mypatientvisit.getCreditRating().equalsIgnoreCase(patientvisit.getCreditRating()))
                {
                    OldVisitInfo = OldVisitInfo + "CreditRating(" + mypatientvisit.getCreditRating() + "),";
                
                    NewVisitInfo = NewVisitInfo + "CreditRating(" + patientvisit.getCreditRating() + "),";          
            
                    mypatientvisit.setCreditRating(patientvisit.getCreditRating());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "CreditRating(空值),";
                
                NewVisitInfo = NewVisitInfo + "CreditRating(" + patientvisit.getCreditRating() + "),";          
        
                mypatientvisit.setCreditRating(patientvisit.getCreditRating());
            }
        }
        
        //PV1-24.1:CONTRACT_CODE
        if(isPopulated(patientvisit.getContractCode()))
        {
            if(isPopulated(mypatientvisit.getContractCode()))
            {
                if(!mypatientvisit.getContractCode().equalsIgnoreCase(patientvisit.getContractCode()))
                {
                    OldVisitInfo = OldVisitInfo + "ContractCode(" + mypatientvisit.getContractCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "ContractCode(" + patientvisit.getContractCode() + "),";          
            
                    mypatientvisit.setContractCode(patientvisit.getContractCode());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ContractCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "ContractCode(" + patientvisit.getContractCode() + "),";          
        
                mypatientvisit.setContractCode(patientvisit.getContractCode());
            }
        }
        
        //PV1-25.1:CONTRACT_CREATE_DATE
        if(isPopulated(patientvisit.getContractCreateDate()))
        {
            if(isPopulated(mypatientvisit.getContractCreateDate()))
            {
                if(!mypatientvisit.getContractCreateDate().equalsIgnoreCase(patientvisit.getContractCreateDate()))
                {
                    OldVisitInfo = OldVisitInfo + "ContractCreateDate(" + mypatientvisit.getContractCreateDate() + "),";
                
                    NewVisitInfo = NewVisitInfo + "ContractCreateDate(" + patientvisit.getContractCreateDate() + "),";          
            
                    mypatientvisit.setContractCreateDate(patientvisit.getContractCreateDate());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ContractCreateDate(空值),";
                
                NewVisitInfo = NewVisitInfo + "ContractCreateDate(" + patientvisit.getContractCreateDate() + "),";          
        
                mypatientvisit.setContractCreateDate(patientvisit.getContractCreateDate());
            }
        }
        
        //PV1-26.1:CONTRACT_PRICE
        if(isPopulated(patientvisit.getContractPrice()))
        {
            if(isPopulated(mypatientvisit.getContractPrice()))
            {
            if(!mypatientvisit.getContractPrice().equalsIgnoreCase(patientvisit.getContractPrice()))
            {
                OldVisitInfo = OldVisitInfo + "ContractPrice(" + mypatientvisit.getContractPrice() + "),";
                
                NewVisitInfo = NewVisitInfo + "ContractPrice(" + patientvisit.getContractPrice() + "),";            
            
                mypatientvisit.setContractPrice(patientvisit.getContractPrice());
            }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ContractPrice(空值),";
                
                NewVisitInfo = NewVisitInfo + "ContractPrice(" + patientvisit.getContractPrice() + "),";            
            
                mypatientvisit.setContractPrice(patientvisit.getContractPrice());
            }
        }
        
        //PV1-27.1:CONTRACT_TIME
        if(isPopulated(patientvisit.getContractTime()))
        {
            if(isPopulated(mypatientvisit.getContractTime()))
            {
                if(!mypatientvisit.getContractTime().equalsIgnoreCase(patientvisit.getContractTime()))
                {
                    OldVisitInfo = OldVisitInfo + "ContractTime(" + mypatientvisit.getContractTime() + "),";
                
                    NewVisitInfo = NewVisitInfo + "ContractTime(" + patientvisit.getContractTime() + "),";          
            
                    mypatientvisit.setContractTime(patientvisit.getContractTime());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "ContractTime(空值),";
                
                NewVisitInfo = NewVisitInfo + "ContractTime(" + patientvisit.getContractTime() + "),";          
        
                mypatientvisit.setContractTime(patientvisit.getContractTime());
            }
        }
        
        //PV1-28.1:INTEREST_RAT_CODE
        if(isPopulated(patientvisit.getInterestRateCode()))
        {
            if(isPopulated(mypatientvisit.getInterestRateCode()))
            {
                if(!mypatientvisit.getInterestRateCode().equalsIgnoreCase(patientvisit.getInterestRateCode()))
                {
                    OldVisitInfo = OldVisitInfo + "InterestRateCode(" + mypatientvisit.getInterestRateCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "InterestRateCode(" + patientvisit.getInterestRateCode() + "),";          
            
                    mypatientvisit.setInterestRateCode(patientvisit.getInterestRateCode());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "InterestRateCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "InterestRateCode(" + patientvisit.getInterestRateCode() + "),";          
            
                mypatientvisit.setInterestRateCode(patientvisit.getInterestRateCode());
            }
        }
        
        //PV1-29.1:BAD_DEBTS
        if(isPopulated(patientvisit.getBadDebts()))
        {
            if(isPopulated(mypatientvisit.getBadDebts()))
            {
                if(!mypatientvisit.getBadDebts().equalsIgnoreCase(patientvisit.getBadDebts()))
                {
                    OldVisitInfo = OldVisitInfo + "BadDebts(" + mypatientvisit.getBadDebts() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BadDebts(" + patientvisit.getBadDebts() + "),";          
            
                    mypatientvisit.setBadDebts(patientvisit.getBadDebts());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BadDebts(空值),";
                
                NewVisitInfo = NewVisitInfo + "BadDebts(" + patientvisit.getBadDebts() + "),";          
        
                mypatientvisit.setBadDebts(patientvisit.getBadDebts());
            }
        }
        
        //PV1-30.1:BAD_DEBTS_CREATE_DATE
        if(isPopulated(patientvisit.getBadDebtsCreateDate()))
        {
            if(isPopulated(mypatientvisit.getBadDebtsCreateDate()))
            {
                if(!mypatientvisit.getBadDebtsCreateDate().equalsIgnoreCase(patientvisit.getBadDebtsCreateDate()))
                {
                    OldVisitInfo = OldVisitInfo + "BadDebtsCreateDate(" + mypatientvisit.getBadDebtsCreateDate() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BadDebtsCreateDate(" + patientvisit.getBadDebtsCreateDate() + "),";          
            
                    mypatientvisit.setBadDebtsCreateDate(patientvisit.getBadDebtsCreateDate());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BadDebtsCreateDate(空值),";
                
                NewVisitInfo = NewVisitInfo + "BadDebtsCreateDate(" + patientvisit.getBadDebtsCreateDate() + "),";          
        
                mypatientvisit.setBadDebtsCreateDate(patientvisit.getBadDebtsCreateDate());
            }
        }
        
        //PV1-31.1:BAD_DEBTS_CODE
        if(isPopulated(patientvisit.getBadDebtsCode()))
        {
            if(isPopulated(mypatientvisit.getBadDebtsCode()))
            {
                if(!mypatientvisit.getBadDebtsCode().equalsIgnoreCase(patientvisit.getBadDebtsCode()))
                {
                    OldVisitInfo = OldVisitInfo + "BadDebtsCode(" + mypatientvisit.getBadDebtsCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BadDebtsCode(" + patientvisit.getBadDebtsCode() + "),";          
            
                    mypatientvisit.setBadDebtsCode(patientvisit.getBadDebtsCode());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BadDebtsCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "BadDebtsCode(" + patientvisit.getBadDebtsCode() + "),";          
        
                mypatientvisit.setBadDebtsCode(patientvisit.getBadDebtsCode());
            }
        }
        
        //PV1-32.1:BAD_DEBTS_PRICE
        if(isPopulated(patientvisit.getBadDebtsPrice()))
        {
            if(isPopulated(mypatientvisit.getBadDebtsPrice()))
            {
                if(!mypatientvisit.getBadDebtsPrice().equalsIgnoreCase(patientvisit.getBadDebtsPrice()))
                {
                    OldVisitInfo = OldVisitInfo + "BadDebtsPrice(" + mypatientvisit.getBadDebtsPrice() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BadDebtsPrice(" + patientvisit.getBadDebtsPrice() + "),";            
            
                    mypatientvisit.setBadDebtsPrice(patientvisit.getBadDebtsPrice());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BadDebtsPrice(空值),";
                
                NewVisitInfo = NewVisitInfo + "BadDebtsPrice(" + patientvisit.getBadDebtsPrice() + "),";            
        
                mypatientvisit.setBadDebtsPrice(patientvisit.getBadDebtsPrice());
            }
        }
        
        //PV1-33.1:BAD_DEBTS_RESTORE_PRICE
        if(isPopulated(patientvisit.getBadDebtsRestorePrice()))
        {
            if(isPopulated(mypatientvisit.getBadDebtsRestorePrice()))
            {
                if(!mypatientvisit.getBadDebtsRestorePrice().equalsIgnoreCase(patientvisit.getBadDebtsRestorePrice()))
                {
                    OldVisitInfo = OldVisitInfo + "BadDebtsRestorePrice(" + mypatientvisit.getBadDebtsRestorePrice() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BadDebtsRestorePrice(" + patientvisit.getBadDebtsRestorePrice() + "),";          
            
                    mypatientvisit.setBadDebtsRestorePrice(patientvisit.getBadDebtsRestorePrice());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BadDebtsRestorePrice(空值),";
                
                NewVisitInfo = NewVisitInfo + "BadDebtsRestorePrice(" + patientvisit.getBadDebtsRestorePrice() + "),";          
        
                mypatientvisit.setBadDebtsRestorePrice(patientvisit.getBadDebtsRestorePrice());
            }
        }
        
        //PV1-34.1:PAT_ACCOUNT_VOIDED
        if(isPopulated(patientvisit.getPatAccountVoided()))
        {
            if(isPopulated(mypatientvisit.getPatAccountVoided()))
            {
                if(!mypatientvisit.getPatAccountVoided().equalsIgnoreCase(patientvisit.getPatAccountVoided()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAccountVoided(" + mypatientvisit.getPatAccountVoided() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAccountVoided(" + patientvisit.getPatAccountVoided() + "),";          
            
                    mypatientvisit.setPatAccountVoided(patientvisit.getPatAccountVoided());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAccountVoided(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAccountVoided(" + patientvisit.getPatAccountVoided() + "),";          
        
                mypatientvisit.setPatAccountVoided(patientvisit.getPatAccountVoided());
            }
        }
        
        //PV1-35.1:PAT_ACCOUNT_VOIDED_DATE
        if(isPopulated(patientvisit.getPatAccountVoidedDate()))
        {
            if(isPopulated(mypatientvisit.getPatAccountVoidedDate()))
            {
                if(!mypatientvisit.getPatAccountVoidedDate().equalsIgnoreCase(patientvisit.getPatAccountVoidedDate()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAccountVoidedDate(" + mypatientvisit.getPatAccountVoidedDate() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAccountVoidedDate(" + patientvisit.getPatAccountVoidedDate() + "),";          
            
                    mypatientvisit.setPatAccountVoidedDate(patientvisit.getPatAccountVoidedDate());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAccountVoidedDate(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAccountVoidedDate(" + patientvisit.getPatAccountVoidedDate() + "),";          
        
                mypatientvisit.setPatAccountVoidedDate(patientvisit.getPatAccountVoidedDate());
            }
        }
        
        //PV1-36.1:PAT_DISCHARGE_DISPOSITION
        if(isPopulated(patientvisit.getPatDischargeDisposition()))
        {
            if(isPopulated(mypatientvisit.getPatDischargeDisposition()))
            {
                if(!mypatientvisit.getPatDischargeDisposition().equalsIgnoreCase(patientvisit.getPatDischargeDisposition()))
                {
                    OldVisitInfo = OldVisitInfo + "PatDischargeDisposition(" + mypatientvisit.getPatDischargeDisposition() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatDischargeDisposition(" + patientvisit.getPatDischargeDisposition() + "),";            
            
                    mypatientvisit.setPatDischargeDisposition(patientvisit.getPatDischargeDisposition());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatDischargeDisposition(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatDischargeDisposition(" + patientvisit.getPatDischargeDisposition() + "),";            
        
                mypatientvisit.setPatDischargeDisposition(patientvisit.getPatDischargeDisposition());
            }
        }
        
        //PV1-37.1:PAT_DISCHARGE_LOCATION
        if(isPopulated(patientvisit.getPatDischargeLocation()))
        {
            if(isPopulated(mypatientvisit.getPatDischargeLocation()))
            {
                if(!mypatientvisit.getPatDischargeLocation().equalsIgnoreCase(patientvisit.getPatDischargeLocation()))
                {
                    OldVisitInfo = OldVisitInfo + "PatDischargeLocation(" + mypatientvisit.getPatDischargeLocation() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatDischargeLocation(" + patientvisit.getPatDischargeLocation() + "),";          
            
                    mypatientvisit.setPatDischargeLocation(patientvisit.getPatDischargeLocation());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatDischargeLocation(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatDischargeLocation(" + patientvisit.getPatDischargeLocation() + "),";          
        
                mypatientvisit.setPatDischargeLocation(patientvisit.getPatDischargeLocation());
            }
        }
        
        //PV1-38.1:PAT_DIET_TYPE
        if(isPopulated(patientvisit.getPatDietType()))
        {
            if(isPopulated(mypatientvisit.getPatDietType()))
            {
                if(!mypatientvisit.getPatDietType().equalsIgnoreCase(patientvisit.getPatDietType()))
                {
                    OldVisitInfo = OldVisitInfo + "PatDietType(" + mypatientvisit.getPatDietType() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatDietType(" + patientvisit.getPatDietType() + "),";            
            
                    mypatientvisit.setPatDietType(patientvisit.getPatDietType());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatDietType(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatDietType(" + patientvisit.getPatDietType() + "),";            
        
                mypatientvisit.setPatDietType(patientvisit.getPatDietType());
            }
        }
        
        //PV1-39.1:PAT_SERVICE_AGENCIES
        if(isPopulated(patientvisit.getPatServiceAgencies()))
        {
            if(isPopulated(mypatientvisit.getPatServiceAgencies()))
            {
                if(!mypatientvisit.getPatServiceAgencies().equalsIgnoreCase(patientvisit.getPatServiceAgencies()))
                {
                    OldVisitInfo = OldVisitInfo + "PatServiceAgencies(" + mypatientvisit.getPatServiceAgencies() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatServiceAgencies(" + patientvisit.getPatServiceAgencies() + "),";          
            
                    mypatientvisit.setPatServiceAgencies(patientvisit.getPatServiceAgencies());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatServiceAgencies(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatServiceAgencies(" + patientvisit.getPatServiceAgencies() + "),";          
        
                mypatientvisit.setPatServiceAgencies(patientvisit.getPatServiceAgencies());
            }
        }
        
        //PV1-40.1:PAT_BED_STATUS
        if(isPopulated(patientvisit.getPatBedStatus()))
        {
            if(isPopulated(mypatientvisit.getPatBedStatus()))
            {
                if(!mypatientvisit.getPatBedStatus().equalsIgnoreCase(patientvisit.getPatBedStatus()))
                {
                    OldVisitInfo = OldVisitInfo + "PatBedStatus(" + mypatientvisit.getPatBedStatus() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatBedStatus(" + patientvisit.getPatBedStatus() + "),";          
            
                    mypatientvisit.setPatBedStatus(patientvisit.getPatBedStatus());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatBedStatus(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatBedStatus(" + patientvisit.getPatBedStatus() + "),";          
        
                mypatientvisit.setPatBedStatus(patientvisit.getPatBedStatus());
            }
        }
        
        //PV1-41.1:PAT_ACCOUNT_STATUS
        if(isPopulated(patientvisit.getPatAccountStatus()))
        {
            if(isPopulated(mypatientvisit.getPatAccountStatus()))
            {
                if(!mypatientvisit.getPatAccountStatus().equalsIgnoreCase(patientvisit.getPatAccountStatus()))
                {
                    OldVisitInfo = OldVisitInfo + "PatAccountStatus(" + mypatientvisit.getPatAccountStatus() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatAccountStatus(" + patientvisit.getPatAccountStatus() + "),";          
            
                    mypatientvisit.setPatAccountStatus(patientvisit.getPatAccountStatus());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatAccountStatus(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatAccountStatus(" + patientvisit.getPatAccountStatus() + "),";          
        
                mypatientvisit.setPatAccountStatus(patientvisit.getPatAccountStatus());
            }
        }
        
        //PV1-42.1:PAT_DETER_POINT_OF_CARE
        if(isPopulated(patientvisit.getPatDeterPointOfCare()))
        {
            if(isPopulated(mypatientvisit.getPatDeterPointOfCare()))
            {
                if(!mypatientvisit.getPatDeterPointOfCare().equalsIgnoreCase(patientvisit.getPatDeterPointOfCare()))
                {
                    OldVisitInfo = OldVisitInfo + "PatDeterPointOfCare(" + mypatientvisit.getPatDeterPointOfCare() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatDeterPointOfCare(" + patientvisit.getPatDeterPointOfCare() + "),";            
            
                    mypatientvisit.setPatDeterPointOfCare(patientvisit.getPatDeterPointOfCare());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatDeterPointOfCare(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatDeterPointOfCare(" + patientvisit.getPatDeterPointOfCare() + "),";            
        
                mypatientvisit.setPatDeterPointOfCare(patientvisit.getPatDeterPointOfCare());
            }
        }
        
        //药费上限
        if(patientvisit.getMedicinelimitamount()!=null)
        if(isPopulated(patientvisit.getMedicinelimitamount().toString()))
        {
            if(isPopulated(mypatientvisit.getMedicinelimitamount().toString()))
            {
                if(!mypatientvisit.getMedicinelimitamount().toString().equalsIgnoreCase(patientvisit.getMedicinelimitamount().toString()))
                {
                    OldVisitInfo = OldVisitInfo + "Medicinelimitamount(" + mypatientvisit.getMedicinelimitamount().toString() + "),";
                
                    NewVisitInfo = NewVisitInfo + "Medicinelimitamount(" + patientvisit.getMedicinelimitamount().toString() + "),";         
            
                    mypatientvisit.setMedicinelimitamount(patientvisit.getMedicinelimitamount());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "Medicinelimitamount(空值),";
                
                NewVisitInfo = NewVisitInfo + "Medicinelimitamount(" + patientvisit.getMedicinelimitamount().toString() + "),";         
        
                mypatientvisit.setMedicinelimitamount(patientvisit.getMedicinelimitamount());
            }
        }
        
        //床费限额
        if(patientvisit.getSickbedlimitamount()!=null)
        if(isPopulated(patientvisit.getSickbedlimitamount().toString()))
        {
            if(isPopulated(mypatientvisit.getSickbedlimitamount().toString()))
            {
                if(!mypatientvisit.getSickbedlimitamount().toString().equalsIgnoreCase(patientvisit.getSickbedlimitamount().toString()))
                {
                    OldVisitInfo = OldVisitInfo + "Sickbedlimitamount(" + mypatientvisit.getSickbedlimitamount().toString() + "),";
                
                    NewVisitInfo = NewVisitInfo + "Sickbedlimitamount(" + patientvisit.getSickbedlimitamount().toString() + "),";           
            
                    mypatientvisit.setSickbedlimitamount(patientvisit.getSickbedlimitamount());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "Sickbedlimitamount(空值),";
                
                NewVisitInfo = NewVisitInfo + "Sickbedlimitamount(" + patientvisit.getSickbedlimitamount().toString() + "),";           
        
                mypatientvisit.setSickbedlimitamount(patientvisit.getSickbedlimitamount());
            }
        }
        
        //检查限额
        if(patientvisit.getExaminelimitamount()!=null)
        if(isPopulated(patientvisit.getExaminelimitamount().toString()))
        {
            if(isPopulated(mypatientvisit.getExaminelimitamount().toString()))
            {
                if(!mypatientvisit.getExaminelimitamount().toString().equalsIgnoreCase(patientvisit.getExaminelimitamount().toString()))
                {
                    OldVisitInfo = OldVisitInfo + "Examinelimitamount(" + mypatientvisit.getExaminelimitamount().toString() + "),";
                
                    NewVisitInfo = NewVisitInfo + "Examinelimitamount(" + patientvisit.getExaminelimitamount().toString() + "),";           
            
                    mypatientvisit.setExaminelimitamount(patientvisit.getExaminelimitamount());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "Examinelimitamount(空值),";
                
                NewVisitInfo = NewVisitInfo + "Examinelimitamount(" + patientvisit.getExaminelimitamount().toString() + "),";           
        
                mypatientvisit.setExaminelimitamount(patientvisit.getExaminelimitamount());
            }
        }
        
        //治疗限额
        if(patientvisit.getCurelimitamount()!=null)
        if(isPopulated(patientvisit.getCurelimitamount().toString()))
        {
            if(isPopulated(mypatientvisit.getCurelimitamount().toString()))
            {
                if(!mypatientvisit.getCurelimitamount().toString().equalsIgnoreCase(patientvisit.getCurelimitamount().toString()))
                {
                    OldVisitInfo = OldVisitInfo + "Curelimitamount(" + mypatientvisit.getCurelimitamount().toString() + "),";
                
                    NewVisitInfo = NewVisitInfo + "Curelimitamount(" + patientvisit.getCurelimitamount().toString() + "),";         
            
                    mypatientvisit.setCurelimitamount(patientvisit.getCurelimitamount());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "Curelimitamount(空值),";
                
                NewVisitInfo = NewVisitInfo + "Curelimitamount(" + patientvisit.getCurelimitamount().toString() + "),";         
        
                mypatientvisit.setCurelimitamount(patientvisit.getCurelimitamount());
            }
        }
        
        //PV1-42.2:PAT_DETER_ROOM 前缀
        if(isPopulated(patientvisit.getPrefix()))
        {
            if(isPopulated(mypatientvisit.getPrefix()))
            {
                if(!mypatientvisit.getPrefix().equalsIgnoreCase(patientvisit.getPrefix()))
                {
                    OldVisitInfo = OldVisitInfo + "Prefix(" + mypatientvisit.getPrefix() + "),";
                    
                    NewVisitInfo = NewVisitInfo + "Prefix(" + patientvisit.getPrefix() + "),";          
            
                    mypatientvisit.setPrefix(patientvisit.getPrefix());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "Prefix(空值),";
                
                NewVisitInfo = NewVisitInfo + "Prefix(" + patientvisit.getPrefix() + "),";          
        
                mypatientvisit.setPrefix(patientvisit.getPrefix());
            }
        }
        
        //PV1-42.3:PAT_DETER_BED
        if(isPopulated(patientvisit.getPatDeterBed()))
        {
            if(isPopulated(mypatientvisit.getPatDeterBed()))
            {
                if(!mypatientvisit.getPatDeterBed().equalsIgnoreCase(patientvisit.getPatDeterBed()))
                {
                    OldVisitInfo = OldVisitInfo + "PatDeterBed(" + mypatientvisit.getPatDeterBed() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatDeterBed(" + patientvisit.getPatDeterBed() + "),";            
            
                    mypatientvisit.setPatDeterBed(patientvisit.getPatDeterBed());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatDeterBed(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatDeterBed(" + patientvisit.getPatDeterBed() + "),";            
        
                mypatientvisit.setPatDeterBed(patientvisit.getPatDeterBed());
            }
        }
        
        //PV1-42.4:PAT_DETER_DEP
        if(isPopulated(patientvisit.getPatDeterDep()))
        {
            if(isPopulated(mypatientvisit.getPatDeterDep()))
            {
                if(!mypatientvisit.getPatDeterDep().equalsIgnoreCase(patientvisit.getPatDeterDep()))
                {
                    OldVisitInfo = OldVisitInfo + "PatDeterDep(" + mypatientvisit.getPatDeterDep() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatDeterDep(" + patientvisit.getPatDeterDep() + "),";            
            
                    mypatientvisit.setPatDeterDep(patientvisit.getPatDeterDep());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatDeterDep(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatDeterDep(" + patientvisit.getPatDeterDep() + "),";            
        
                mypatientvisit.setPatDeterDep(patientvisit.getPatDeterDep());
            }
        }
        
        //PV1-42.5:PAT_DETER_POSITION_STATUS 护理
        if(isPopulated(patientvisit.getTend()))
        {
            if(isPopulated(mypatientvisit.getTend()))
            {
                if(!mypatientvisit.getTend().equalsIgnoreCase(patientvisit.getTend()))
                {
                    OldVisitInfo = OldVisitInfo + "Tend(" + mypatientvisit.getTend() + "),";
                
                    NewVisitInfo = NewVisitInfo + "Tend(" + patientvisit.getTend() + "),";          
            
                    mypatientvisit.setTend(patientvisit.getTend());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "Tend(空值),";
                
                NewVisitInfo = NewVisitInfo + "Tend(" + patientvisit.getTend() + "),";          
        
                mypatientvisit.setTend(patientvisit.getTend());
            }
        }
        
        //PV1-42.6:PAT_DETER_POSITION_TYPE 护士ID
        if(isPopulated(patientvisit.getPatNurseCode()))
        {
            if(isPopulated(mypatientvisit.getPatNurseCode()))
            {
                if(!mypatientvisit.getPatNurseCode().equalsIgnoreCase(patientvisit.getPatNurseCode()))
                {
                    OldVisitInfo = OldVisitInfo + "PatNurseCode(" + mypatientvisit.getPatNurseCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatNurseCode(" + patientvisit.getPatNurseCode() + "),";          
            
                    mypatientvisit.setPatNurseCode(patientvisit.getPatDeterPositionType());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatNurseCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatNurseCode(" + patientvisit.getPatNurseCode() + "),";          
            
                mypatientvisit.setPatNurseCode(patientvisit.getPatNurseCode());
            }
        }
        
        //PV1-42.7:PAT_DETER_BUILDING 护士姓名
        if(isPopulated(patientvisit.getPatNurseName()))
        {
            if(isPopulated(mypatientvisit.getPatNurseName()))
            {
                if(!mypatientvisit.getPatNurseName().equalsIgnoreCase(patientvisit.getPatNurseName()))
                {
                    OldVisitInfo = OldVisitInfo + "PatNurseName(" + mypatientvisit.getPatNurseName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatNurseName(" + patientvisit.getPatNurseName() + "),";          
            
                    mypatientvisit.setPatNurseName(patientvisit.getPatNurseName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatNurseName(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatNurseName(" + patientvisit.getPatNurseName() + "),";          
        
                mypatientvisit.setPatNurseName(patientvisit.getPatNurseName());
            }
        }
        
        //PV1-42.8:PAT_DETER_FLOOR 入院登记操作员
        if(isPopulated(patientvisit.getOperCode()))
        {
            if(isPopulated(mypatientvisit.getOperCode()))
            {
                if(!mypatientvisit.getOperCode().equalsIgnoreCase(patientvisit.getOperCode()))
                {
                    OldVisitInfo = OldVisitInfo + "OperCode(" + mypatientvisit.getOperCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OperCode(" + patientvisit.getOperCode() + "),";          
            
                    mypatientvisit.setOperCode(patientvisit.getOperCode());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OperCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "OperCode(" + patientvisit.getOperCode() + "),";          
        
                mypatientvisit.setOperCode(patientvisit.getOperCode());
            }
        }
        
//      //PV1-42.9:PAT_DETER_DESCRIPTION 操作日期
//      if(isPopulated(patientvisit.getOperDate()))
//      {
//          if(isPopulated(mypatientvisit.getOperDate()))
//          {
//              if(!mypatientvisit.getOperDate().toString().equalsIgnoreCase(patientvisit.getOperDate().toString()))
//              {
//                  OldVisitInfo = OldVisitInfo + "OperDate(" + mypatientvisit.getOperDate().toString() + "),";
//              
//                  NewVisitInfo = NewVisitInfo + "OperDate(" + patientvisit.getOperDate().toString() + "),";           
//          
//                  mypatientvisit.setOperDate(patientvisit.getOperDate());
//              }
//          }
//          else
//          {
//              OldVisitInfo = OldVisitInfo + "OperDate(空值),";
//              
//              NewVisitInfo = NewVisitInfo + "OperDate(" + patientvisit.getOperDate().toString() + "),";           
//      
//              mypatientvisit.setOperDate(patientvisit.getOperDate());
//          }
//      }
        
        //PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
        if(isPopulated(patientvisit.getPatForTempPointOfCare()))
        {
            if(isPopulated(mypatientvisit.getPatForTempPointOfCare()))
            {
                if(!mypatientvisit.getPatForTempPointOfCare().equalsIgnoreCase(patientvisit.getPatForTempPointOfCare()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempPointOfCare(" + mypatientvisit.getPatForTempPointOfCare() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempPointOfCare(" + patientvisit.getPatForTempPointOfCare() + "),";            
            
                    mypatientvisit.setPatForTempPointOfCare(patientvisit.getPatForTempPointOfCare());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempPointOfCare(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempPointOfCare(" + patientvisit.getPatForTempPointOfCare() + "),";            
        
                mypatientvisit.setPatForTempPointOfCare(patientvisit.getPatForTempPointOfCare());
            }
        }
        
        //PV1-43.2:PAT_FOR_TEMP_ROOM
        if(isPopulated(patientvisit.getPatForTempRoom()))
        {
            if(isPopulated(mypatientvisit.getPatForTempRoom()))
            {
                if(!mypatientvisit.getPatForTempRoom().equalsIgnoreCase(patientvisit.getPatForTempRoom()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempRoom(" + mypatientvisit.getPatForTempRoom() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempRoom(" + patientvisit.getPatForTempRoom() + "),";          
            
                    mypatientvisit.setPatForTempRoom(patientvisit.getPatForTempRoom());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempRoom(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempRoom(" + patientvisit.getPatForTempRoom() + "),";          
        
                mypatientvisit.setPatForTempRoom(patientvisit.getPatForTempRoom());
            }
        }
        
        //PV1-43.3:PAT_FOR_TEMP_BED
        if(isPopulated(patientvisit.getPatForTempBed()))
        {
            if(isPopulated(mypatientvisit.getPatForTempBed()))
            {
                if(!mypatientvisit.getPatForTempBed().equalsIgnoreCase(patientvisit.getPatForTempBed()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempBed(" + mypatientvisit.getPatForTempBed() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempBed(" + patientvisit.getPatForTempBed() + "),";            
            
                    mypatientvisit.setPatForTempBed(patientvisit.getPatForTempBed());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempBed(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempBed(" + patientvisit.getPatForTempBed() + "),";            
        
                mypatientvisit.setPatForTempBed(patientvisit.getPatForTempBed());
            }
        }
        
        //PV1-43.4:PAT_FOR_TEMP_DEP
        if(isPopulated(patientvisit.getPatForTempDep()))
        {
            if(isPopulated(mypatientvisit.getPatForTempDep()))
            {
                if(!mypatientvisit.getPatForTempDep().equalsIgnoreCase(patientvisit.getPatForTempDep()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempDep(" + mypatientvisit.getPatForTempDep() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempDep(" + patientvisit.getPatForTempDep() + "),";            
            
                    mypatientvisit.setPatForTempDep(patientvisit.getPatForTempDep());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempDep(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempDep(" + patientvisit.getPatForTempDep() + "),";            
        
                mypatientvisit.setPatForTempDep(patientvisit.getPatForTempDep());
            }
        }
        
        //PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
        if(isPopulated(patientvisit.getPatForTempPositionStatus()))
        {
            if(isPopulated(mypatientvisit.getPatForTempPositionStatus()))
            {
                if(!mypatientvisit.getPatForTempPositionStatus().equalsIgnoreCase(patientvisit.getPatForTempPositionStatus()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempPositionStatus(" + mypatientvisit.getPatForTempPositionStatus() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempPositionStatus(" + patientvisit.getPatForTempPositionStatus() + "),";          
            
                    mypatientvisit.setPatForTempPositionStatus(patientvisit.getPatForTempPositionStatus());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempPositionStatus(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempPositionStatus(" + patientvisit.getPatForTempPositionStatus() + "),";          
        
                mypatientvisit.setPatForTempPositionStatus(patientvisit.getPatForTempPositionStatus());
            }
        }
        
        //PV1-43.6:PAT_FOR_TEMP_POSITION_TYPE
        if(isPopulated(patientvisit.getPatForTempPositionType()))
        {
            if(isPopulated(mypatientvisit.getPatForTempPositionType()))
            {
                if(!mypatientvisit.getPatForTempPositionType().equalsIgnoreCase(patientvisit.getPatForTempPositionType()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempPositionType(" + mypatientvisit.getPatForTempPositionType() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempPositionType(" + patientvisit.getPatForTempPositionType() + "),";          
            
                    mypatientvisit.setPatForTempPositionType(patientvisit.getPatForTempPositionType());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempPositionType(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempPositionType(" + patientvisit.getPatForTempPositionType() + "),";          
        
                mypatientvisit.setPatForTempPositionType(patientvisit.getPatForTempPositionType());
            }
        }
        
        //PV1-43.7:PAT_FOR_TEMP_BUILDING
        if(isPopulated(patientvisit.getPatForTempBuilding()))
        {
            if(isPopulated(mypatientvisit.getPatForTempBuilding()))
            {
                if(!mypatientvisit.getPatForTempBuilding().equalsIgnoreCase(patientvisit.getPatForTempBuilding()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempBuilding(" + mypatientvisit.getPatForTempBuilding() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempBuilding(" + patientvisit.getPatForTempBuilding() + "),";          
            
                    mypatientvisit.setPatForTempBuilding(patientvisit.getPatForTempBuilding());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempBuilding(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempBuilding(" + patientvisit.getPatForTempBuilding() + "),";          
        
                mypatientvisit.setPatForTempBuilding(patientvisit.getPatForTempBuilding());
            }
        }
        
        //PV1-43.8:PAT_FOR_TEMP_FLOOR
        if(isPopulated(patientvisit.getPatForTempFloor()))
        {
            if(isPopulated(mypatientvisit.getPatForTempFloor()))
            {
                if(!mypatientvisit.getPatForTempFloor().equalsIgnoreCase(patientvisit.getPatForTempFloor()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempFloor(" + mypatientvisit.getPatForTempFloor() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempFloor(" + patientvisit.getPatForTempFloor() + "),";            
            
                    mypatientvisit.setPatForTempFloor(patientvisit.getPatForTempFloor());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempFloor(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempFloor(" + patientvisit.getPatForTempFloor() + "),";            
        
                mypatientvisit.setPatForTempFloor(patientvisit.getPatForTempFloor());
            }
        }
        
        //PV1-43.9:PAT_FOR_TEMP_DESCRIPTION
        if(isPopulated(patientvisit.getPatForTempDescription()))
        {
            if(isPopulated(mypatientvisit.getPatForTempDescription()))
            {
                if(!mypatientvisit.getPatForTempDescription().equalsIgnoreCase(patientvisit.getPatForTempDescription()))
                {
                    OldVisitInfo = OldVisitInfo + "PatForTempDescription(" + mypatientvisit.getPatForTempDescription() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatForTempDescription(" + patientvisit.getPatForTempDescription() + "),";            
            
                    mypatientvisit.setPatForTempDescription(patientvisit.getPatForTempDescription());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatForTempDescription(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatForTempDescription(" + patientvisit.getPatForTempDescription() + "),";            
        
                mypatientvisit.setPatForTempDescription(patientvisit.getPatForTempDescription());
            }
        }
        
//      //PV1-44.1:ADMIT_DATE
//      if(isPopulated(patientvisit.getAdmitDate()))
//      {
//          if(isPopulated(mypatientvisit.getAdmitDate()))
//          {
//              if(!mypatientvisit.getAdmitDate().toString().equalsIgnoreCase(patientvisit.getAdmitDate().toString()))
//              {
//                  OldVisitInfo = OldVisitInfo + "AdmitDate(" + mypatientvisit.getAdmitDate().toString() + "),";
//              
//                  NewVisitInfo = NewVisitInfo + "AdmitDate(" + patientvisit.getAdmitDate().toString() + "),";         
//          
//                  mypatientvisit.setAdmitDate(patientvisit.getAdmitDate());
//              }
//          }
//          else
//          {
//              OldVisitInfo = OldVisitInfo + "AdmitDate(空值),";
//              
//              NewVisitInfo = NewVisitInfo + "AdmitDate(" + patientvisit.getAdmitDate().toString() + "),";         
//      
//              mypatientvisit.setAdmitDate(patientvisit.getAdmitDate());
//          }
//      }
//      
//      //PV1-45.1:DISCHARGE_DATE
//      if(isPopulated(patientvisit.getDischargeDate()))
//      {
//          if(isPopulated(mypatientvisit.getDischargeDate()))
//          {
//              if(!mypatientvisit.getDischargeDate().toString().equalsIgnoreCase(patientvisit.getDischargeDate().toString()))
//              {
//                  OldVisitInfo = OldVisitInfo + "DischargeDate(" + mypatientvisit.getDischargeDate().toString() + "),";
//                  
//                  NewVisitInfo = NewVisitInfo + "DischargeDate(" + patientvisit.getDischargeDate().toString() + "),";         
//          
//                  mypatientvisit.setDischargeDate(patientvisit.getDischargeDate());
//              }
//          }
//          else
//          {
//              OldVisitInfo = OldVisitInfo + "DischargeDate(空值),";
//              
//              NewVisitInfo = NewVisitInfo + "DischargeDate(" + patientvisit.getDischargeDate().toString() + "),";         
//          
//              mypatientvisit.setDischargeDate(patientvisit.getDischargeDate());
//          }
//      }
        
        //PV1-46.1:PAT_DIFERRENCE
        if(isPopulated(patientvisit.getPatDifference()))
        {
            if(isPopulated(mypatientvisit.getPatDifference()))
            {
                if(!mypatientvisit.getPatDifference().equalsIgnoreCase(patientvisit.getPatDifference()))
                {
                    OldVisitInfo = OldVisitInfo + "PatDifference(" + mypatientvisit.getPatDifference() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatDifference(" + patientvisit.getPatDifference() + "),";            
            
                    mypatientvisit.setPatDifference(patientvisit.getPatDifference());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatDifference(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatDifference(" + patientvisit.getPatDifference() + "),";            
        
                mypatientvisit.setPatDifference(patientvisit.getPatDifference());
            }
        }
        
        //PV1-47.1:PAT_TOTAL_COST
        if(isPopulated(patientvisit.getPatTotalCost()))
        {
            if(isPopulated(mypatientvisit.getPatTotalCost()))
            {
                if(!mypatientvisit.getPatTotalCost().equalsIgnoreCase(patientvisit.getPatTotalCost()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTotalCost(" + mypatientvisit.getPatTotalCost() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTotalCost(" + patientvisit.getPatTotalCost() + "),";          
            
                    mypatientvisit.setPatTotalCost(patientvisit.getPatTotalCost());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTotalCost(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTotalCost(" + patientvisit.getPatTotalCost() + "),";          
        
                mypatientvisit.setPatTotalCost(patientvisit.getPatTotalCost());
            }
        }
        
        //PV1-48.1:PAT_TOTAL_DIPATCH
        if(isPopulated(patientvisit.getPatTotalDispatch()))
        {
            if(isPopulated(mypatientvisit.getPatTotalDispatch()))
            {
                if(!mypatientvisit.getPatTotalDispatch().equalsIgnoreCase(patientvisit.getPatTotalDispatch()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTotalDispatch(" + mypatientvisit.getPatTotalDispatch() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTotalDispatch(" + patientvisit.getPatTotalDispatch() + "),";          
            
                    mypatientvisit.setPatTotalDispatch(patientvisit.getPatTotalDispatch());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTotalDispatch(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTotalDispatch(" + patientvisit.getPatTotalDispatch() + "),";          
        
                mypatientvisit.setPatTotalDispatch(patientvisit.getPatTotalDispatch());
            }
        }
        
        //PV1-49.1:pat_total_amount_payable
        if(isPopulated(patientvisit.getPatTotalAmountPayable()))
        {
            if(isPopulated(mypatientvisit.getPatTotalAmountPayable()))
            {
                if(!mypatientvisit.getPatTotalAmountPayable().equalsIgnoreCase(patientvisit.getPatTotalAmountPayable()))
                {
                    OldVisitInfo = OldVisitInfo + "PatTotalAmountPayable(" + mypatientvisit.getPatTotalAmountPayable() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatTotalAmountPayable(" + patientvisit.getPatTotalAmountPayable() + "),";            
            
                    mypatientvisit.setPatTotalAmountPayable(patientvisit.getPatTotalAmountPayable());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatTotalAmountPayable(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatTotalAmountPayable(" + patientvisit.getPatTotalAmountPayable() + "),";            
        
                mypatientvisit.setPatTotalAmountPayable(patientvisit.getPatTotalAmountPayable());
            }
        }
        
        //PV1-50.1:PAT_SPARE_ID 婴儿标志
        if(isPopulated(patientvisit.getBabyFlag()))
        {
            if(isPopulated(mypatientvisit.getBabyFlag()))
            {
                if(!mypatientvisit.getBabyFlag().equalsIgnoreCase(patientvisit.getBabyFlag()))
                {
                    OldVisitInfo = OldVisitInfo + "BabyFlag(" + mypatientvisit.getBabyFlag() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BabyFlag(" + patientvisit.getBabyFlag() + "),";          
            
                    mypatientvisit.setBabyFlag(patientvisit.getBabyFlag());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BabyFlag(空值),";
                
                NewVisitInfo = NewVisitInfo + "BabyFlag(" + patientvisit.getBabyFlag() + "),";          
        
                mypatientvisit.setBabyFlag(patientvisit.getBabyFlag());
            }
        }
        
        //PV1-50.2:母亲姓名
        if(isPopulated(patientvisit.getMothersName()))
        {
            if(isPopulated(mypatientvisit.getMothersName()))
            {
                if(!mypatientvisit.getMothersName().equalsIgnoreCase(patientvisit.getMothersName()))
                {
                    OldVisitInfo = OldVisitInfo + "MothersName(" + mypatientvisit.getMothersName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "MothersName(" + patientvisit.getMothersName() + "),";            
            
                    mypatientvisit.setMothersName(patientvisit.getMothersName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "MothersName(空值),";
                
                NewVisitInfo = NewVisitInfo + "MothersName(" + patientvisit.getMothersName() + "),";            
        
                mypatientvisit.setMothersName(patientvisit.getMothersName());
            }
        }
        
        //母亲ID
        if(isPopulated(patientvisit.getMothersID()))
        {
            if(isPopulated(mypatientvisit.getMothersID()))
            {
                if(!mypatientvisit.getMothersID().equalsIgnoreCase(patientvisit.getMothersID()))
                {
                    OldVisitInfo = OldVisitInfo + "MothersID(" + mypatientvisit.getMothersID() + "),";
                
                    NewVisitInfo = NewVisitInfo + "MothersID(" + patientvisit.getMothersID() + "),";            
            
                    mypatientvisit.setMothersID(patientvisit.getMothersID());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "MothersID(空值),";
                
                NewVisitInfo = NewVisitInfo + "MothersID(" + patientvisit.getMothersID() + "),";            
        
                mypatientvisit.setMothersID(patientvisit.getMothersID());
            }
        }
        
        //母亲域ID
        if(isPopulated(patientvisit.getMothersDomain()))
        {
            if(isPopulated(mypatientvisit.getMothersDomain()))
            {
                if(!mypatientvisit.getMothersDomain().equalsIgnoreCase(patientvisit.getMothersDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "MothersDomain(" + mypatientvisit.getMothersDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "MothersDomain(" + patientvisit.getMothersDomain() + "),";            
            
                    mypatientvisit.setMothersDomain(patientvisit.getMothersDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "MothersDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "MothersDomain(" + patientvisit.getMothersDomain() + "),";            
        
                mypatientvisit.setMothersDomain(patientvisit.getMothersDomain());
            }
        }
        
        //母亲流水ID
        if(isPopulated(patientvisit.getMothersFlowID()))
        {
            if(isPopulated(mypatientvisit.getMothersFlowID()))
            {
                if(!mypatientvisit.getMothersFlowID().equalsIgnoreCase(patientvisit.getMothersFlowID()))
                {
                    OldVisitInfo = OldVisitInfo + "MothersFlowID(" + mypatientvisit.getMothersFlowID() + "),";
                
                    NewVisitInfo = NewVisitInfo + "MothersFlowID(" + patientvisit.getMothersFlowID() + "),";            
            
                    mypatientvisit.setMothersFlowID(patientvisit.getMothersFlowID());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "MothersFlowID(空值),";
                
                NewVisitInfo = NewVisitInfo + "MothersFlowID(" + patientvisit.getMothersFlowID() + "),";            
        
                mypatientvisit.setMothersFlowID(patientvisit.getMothersFlowID());
            }
        }
        
        //母亲流水域ID
        if(isPopulated(patientvisit.getMothersFlowDomain()))
        {
            if(isPopulated(mypatientvisit.getMothersFlowDomain()))
            {
                if(!mypatientvisit.getMothersFlowDomain().equalsIgnoreCase(patientvisit.getMothersFlowDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "MothersFlowDomain(" + mypatientvisit.getMothersFlowDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "MothersFlowDomain(" + patientvisit.getMothersFlowDomain() + "),";            
            
                    mypatientvisit.setMothersFlowDomain(patientvisit.getMothersFlowDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "MothersFlowDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "MothersFlowDomain(" + patientvisit.getMothersFlowDomain() + "),";            
        
                mypatientvisit.setMothersFlowDomain(patientvisit.getMothersFlowDomain());
            }
        }
        
        //入院体重
        if(isPopulated(patientvisit.getAdmitWeight()))
        {
            if(isPopulated(mypatientvisit.getAdmitWeight()))
            {
                if(!mypatientvisit.getAdmitWeight().equalsIgnoreCase(patientvisit.getAdmitWeight()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmitWeight(" + mypatientvisit.getAdmitWeight() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmitWeight(" + patientvisit.getAdmitWeight() + "),";            
            
                    mypatientvisit.setAdmitWeight(patientvisit.getAdmitWeight());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmitWeight(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmitWeight(" + patientvisit.getAdmitWeight() + "),";            
        
                mypatientvisit.setAdmitWeight(patientvisit.getAdmitWeight());
            }
        }

        //入院体重单位
        if(isPopulated(patientvisit.getAdmitWeightUnit()))
        {
            if(isPopulated(mypatientvisit.getAdmitWeightUnit()))
            {
                if(!mypatientvisit.getAdmitWeight().equalsIgnoreCase(patientvisit.getAdmitWeightUnit()))
                {
                    OldVisitInfo = OldVisitInfo + "AdmitWeightUnit(" + mypatientvisit.getAdmitWeightUnit() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AdmitWeightUnit(" + patientvisit.getAdmitWeightUnit() + "),";            
            
                    mypatientvisit.setAdmitWeightUnit(patientvisit.getAdmitWeightUnit());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AdmitWeightUnit(空值),";
                
                NewVisitInfo = NewVisitInfo + "AdmitWeightUnit(" + patientvisit.getAdmitWeightUnit() + "),";            
        
                mypatientvisit.setAdmitWeightUnit(patientvisit.getAdmitWeightUnit());
            }
        }
        
        //出生体重
        if(isPopulated(patientvisit.getBirthWeight()))
        {
            if(isPopulated(mypatientvisit.getBirthWeight()))
            {
                if(!mypatientvisit.getBirthWeight().equalsIgnoreCase(patientvisit.getBirthWeight()))
                {
                    OldVisitInfo = OldVisitInfo + "BirthWeight(" + mypatientvisit.getBirthWeight() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BirthWeight(" + patientvisit.getBirthWeight() + "),";            
            
                    mypatientvisit.setBirthWeight(patientvisit.getBirthWeight());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BirthWeight(空值),";
                
                NewVisitInfo = NewVisitInfo + "BirthWeight(" + patientvisit.getBirthWeight() + "),";            
        
                mypatientvisit.setBirthWeight(patientvisit.getBirthWeight());
            }
        }
        
        //出生体重单位
        if(isPopulated(patientvisit.getBabyWeightUnit()))
        {
            if(isPopulated(mypatientvisit.getBabyWeightUnit()))
            {
                if(!mypatientvisit.getBabyWeightUnit().equalsIgnoreCase(patientvisit.getBabyWeightUnit()))
                {
                    OldVisitInfo = OldVisitInfo + "BirthWeight(" + mypatientvisit.getBabyWeightUnit() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BirthWeight(" + patientvisit.getBabyWeightUnit() + "),";         
            
                    mypatientvisit.setBabyWeightUnit(patientvisit.getBabyWeightUnit());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BirthWeight(空值),";
                
                NewVisitInfo = NewVisitInfo + "BirthWeight(" + patientvisit.getBabyWeightUnit() + "),";         
        
                mypatientvisit.setBabyWeightUnit(patientvisit.getBabyWeightUnit());
            }
        }
        //PV1-51.1:pat_visit_logo
        if(isPopulated(patientvisit.getPatVisitLogo()))
        {
            if(isPopulated(mypatientvisit.getPatVisitLogo()))
            {
                if(!mypatientvisit.getPatVisitLogo().equalsIgnoreCase(patientvisit.getPatVisitLogo()))
                {
                    OldVisitInfo = OldVisitInfo + "PatVisitLogo(" + mypatientvisit.getPatVisitLogo() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PatVisitLogo(" + patientvisit.getPatVisitLogo() + "),";          
            
                    mypatientvisit.setPatVisitLogo(patientvisit.getPatVisitLogo());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PatVisitLogo(空值),";
                
                NewVisitInfo = NewVisitInfo + "PatVisitLogo(" + patientvisit.getPatVisitLogo() + "),";          
        
                mypatientvisit.setPatVisitLogo(patientvisit.getPatVisitLogo());
            }
        }
        
        //PV1-52.1:OTHER_MEDICAL_INSTITUTIONS
        if(isPopulated(patientvisit.getOldPatientId()))
        {
            if(isPopulated(mypatientvisit.getOldPatientId()))
            {
                if(!mypatientvisit.getOldPatientId().equalsIgnoreCase(patientvisit.getOldPatientId()))
                {
                    OldVisitInfo = OldVisitInfo + "OldPatientId(" + mypatientvisit.getOldPatientId() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OldPatientId(" + patientvisit.getOldPatientId() + "),";          
            
                    mypatientvisit.setOldPatientId(patientvisit.getOldPatientId());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OldPatientId(空值),";
                
                NewVisitInfo = NewVisitInfo + "OldPatientId(" + patientvisit.getOldPatientId() + "),";          
        
                mypatientvisit.setOldPatientId(patientvisit.getOldPatientId());
            }
        }
        
        if(isPopulated(patientvisit.getOldPatientDomain()))
        {
            if(isPopulated(mypatientvisit.getOldPatientDomain()))
            {
                if(!mypatientvisit.getOldPatientDomain().equalsIgnoreCase(patientvisit.getOldPatientDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "OldPatientDomain(" + mypatientvisit.getOldPatientDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OldPatientDomain(" + patientvisit.getOldPatientDomain() + "),";          
            
                    mypatientvisit.setOldPatientDomain(patientvisit.getOldPatientDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OldPatientDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "OldPatientDomain(" + patientvisit.getOldPatientDomain() + "),";          
        
                mypatientvisit.setOldPatientDomain(patientvisit.getOldPatientDomain());
            }
        }
        
        if(isPopulated(patientvisit.getOldVisitFlowId()))
        {
            if(isPopulated(mypatientvisit.getOldVisitFlowId()))
            {
                if(!mypatientvisit.getOldVisitFlowId().equalsIgnoreCase(patientvisit.getOldVisitFlowId()))
                {
                    OldVisitInfo = OldVisitInfo + "OldVisitFlowId(" + mypatientvisit.getOldVisitFlowId() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OldVisitFlowId(" + patientvisit.getOldVisitFlowId() + "),";          
            
                    mypatientvisit.setOldVisitFlowId(patientvisit.getOldVisitFlowId());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OldVisitFlowId(空值),";
                
                NewVisitInfo = NewVisitInfo + "OldVisitFlowId(" + patientvisit.getOldVisitFlowId() + "),";          
        
                mypatientvisit.setOldVisitFlowId(patientvisit.getOldVisitFlowId());
            }
        }
        
        if(isPopulated(patientvisit.getOldVisitFlowDomain()))
        {
            if(isPopulated(mypatientvisit.getOldVisitFlowDomain()))
            {
                if(!mypatientvisit.getOldVisitFlowDomain().equalsIgnoreCase(patientvisit.getOldVisitFlowDomain()))
                {
                    OldVisitInfo = OldVisitInfo + "OldVisitFlowDomain(" + mypatientvisit.getOldVisitFlowDomain() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OldVisitFlowDomain(" + patientvisit.getOldVisitFlowDomain() + "),";          
            
                    mypatientvisit.setOldVisitFlowDomain(patientvisit.getOldVisitFlowDomain());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OldVisitFlowDomain(空值),";
                
                NewVisitInfo = NewVisitInfo + "OldVisitFlowDomain(" + patientvisit.getOldVisitFlowDomain() + "),";          
        
                mypatientvisit.setOldVisitFlowDomain(patientvisit.getOldVisitFlowDomain());
            }
        }
        
        if(isPopulated(patientvisit.getOldStatus()))
        {
            if(isPopulated(mypatientvisit.getOldStatus()))
            {
                if(!mypatientvisit.getOldStatus().equalsIgnoreCase(patientvisit.getOldStatus()))
                {
                    OldVisitInfo = OldVisitInfo + "OldStatus(" + mypatientvisit.getOldStatus() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OldStatus(" + patientvisit.getOldStatus() + "),";            
            
                    mypatientvisit.setOldStatus(patientvisit.getOldStatus());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OldStatus(空值),";
                
                NewVisitInfo = NewVisitInfo + "OldStatus(" + patientvisit.getOldStatus() + "),";            
        
                mypatientvisit.setOldStatus(patientvisit.getOldStatus());
            }
        }
        
        if(isPopulated(patientvisit.getOldInfo()))
        {
            if(isPopulated(mypatientvisit.getOldInfo()))
            {
                if(!mypatientvisit.getOldInfo().equalsIgnoreCase(patientvisit.getOldInfo()))
                {
                    OldVisitInfo = OldVisitInfo + "OldInfo(" + mypatientvisit.getOldInfo() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OldInfo(" + patientvisit.getOldInfo() + "),";            
            
                    mypatientvisit.setOldInfo(patientvisit.getOldInfo());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OldInfo(空值),";
                
                NewVisitInfo = NewVisitInfo + "OldInfo(" + patientvisit.getOldInfo() + "),";            
        
                mypatientvisit.setOldInfo(patientvisit.getOldInfo());
            }
        }
        
        //noon_code
        if(isPopulated(patientvisit.getNoonCode()))
        {
            if(isPopulated(mypatientvisit.getNoonCode()))
            {
                if(!mypatientvisit.getNoonCode().equalsIgnoreCase(patientvisit.getNoonCode()))
                {
                    OldVisitInfo = OldVisitInfo + "noonCode(" + mypatientvisit.getNoonCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "noonCode(" + patientvisit.getNoonCode() + "),";          
            
                    mypatientvisit.setNoonCode(patientvisit.getNoonCode());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "noonCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "noonCode(" + patientvisit.getNoonCode() + "),";          
        
                mypatientvisit.setNoonCode(patientvisit.getNoonCode());
            }
        }
        
        //paykind_code
        if(isPopulated(patientvisit.getPaykindCode()))
        {
            if(isPopulated(mypatientvisit.getPaykindCode()))
            {
                if(!mypatientvisit.getPaykindCode().equalsIgnoreCase(patientvisit.getPaykindCode()))
                {
                    OldVisitInfo = OldVisitInfo + "PaykindCode(" + mypatientvisit.getPaykindCode() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PaykindCode(" + patientvisit.getPaykindCode() + "),";            
            
                    mypatientvisit.setPaykindCode(patientvisit.getPaykindCode());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PaykindCode(空值),";
                
                NewVisitInfo = NewVisitInfo + "PaykindCode(" + patientvisit.getPaykindCode() + "),";            
        
                mypatientvisit.setPaykindCode(patientvisit.getPaykindCode());
            }
        }
        
        //paykind_name
        if(isPopulated(patientvisit.getPaykindName()))
        {
            if(isPopulated(mypatientvisit.getPaykindName()))
            {
                if(!mypatientvisit.getPaykindName().equalsIgnoreCase(patientvisit.getPaykindName()))
                {
                    OldVisitInfo = OldVisitInfo + "PaykindName(" + mypatientvisit.getPaykindName() + "),";
                
                    NewVisitInfo = NewVisitInfo + "PaykindName(" + patientvisit.getPaykindName() + "),";            
            
                    mypatientvisit.setPaykindName(patientvisit.getPaykindName());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "PaykindName(空值),";
                
                NewVisitInfo = NewVisitInfo + "PaykindName(" + patientvisit.getPaykindName() + "),";            
        
                mypatientvisit.setPaykindName(patientvisit.getPaykindName());
            }
        }
        
        
        //schema_no
        if(isPopulated(patientvisit.getSchemaNo()))
        {
            if(isPopulated(mypatientvisit.getSchemaNo()))
            {
                if(!mypatientvisit.getSchemaNo().equalsIgnoreCase(patientvisit.getSchemaNo()))
                {
                    OldVisitInfo = OldVisitInfo + "SchemaNo(" + mypatientvisit.getSchemaNo() + "),";
                
                    NewVisitInfo = NewVisitInfo + "SchemaNo(" + patientvisit.getSchemaNo() + "),";          
            
                    mypatientvisit.setSchemaNo(patientvisit.getSchemaNo());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "SchemaNo(空值),";
                
                NewVisitInfo = NewVisitInfo + "SchemaNo(" + patientvisit.getSchemaNo() + "),";          
        
                mypatientvisit.setSchemaNo(patientvisit.getSchemaNo());
            }
        }
        
        //order_no
        if(isPopulated(patientvisit.getOrderNo()))
        {
            if(isPopulated(mypatientvisit.getOrderNo()))
            {
                if(!mypatientvisit.getOrderNo().equalsIgnoreCase(patientvisit.getOrderNo()))
                {
                    OldVisitInfo = OldVisitInfo + "OrderNo(" + mypatientvisit.getOrderNo() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OrderNo(" + patientvisit.getOrderNo() + "),";            
            
                    mypatientvisit.setOrderNo(patientvisit.getOrderNo());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OrderNo(空值),";
                
                NewVisitInfo = NewVisitInfo + "OrderNo(" + patientvisit.getOrderNo() + "),";            
        
                mypatientvisit.setOrderNo(patientvisit.getOrderNo());
            }
        }
        
        //see_no
        if(isPopulated(patientvisit.getSeeNo()))
        {
            if(isPopulated(mypatientvisit.getSeeNo()))
            {
                if(!mypatientvisit.getSeeNo().equalsIgnoreCase(patientvisit.getSeeNo()))
                {
                    OldVisitInfo = OldVisitInfo + "SeeNo(" + mypatientvisit.getSeeNo() + "),";
                
                    NewVisitInfo = NewVisitInfo + "SeeNo(" + patientvisit.getSeeNo() + "),";            
            
                    mypatientvisit.setSeeNo(patientvisit.getSeeNo());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "SeeNo(空值),";
                
                NewVisitInfo = NewVisitInfo + "SeeNo(" + patientvisit.getSeeNo() + "),";            
        
                mypatientvisit.setSeeNo(patientvisit.getSeeNo());
            }
        }
        
        
        //begin_time
        if(isPopulated(patientvisit.getBeginTime()))
        {
            if(isPopulated(mypatientvisit.getBeginTime()))
            {
                if(!(mypatientvisit.getBeginTime()==patientvisit.getBeginTime()))
                {
                    OldVisitInfo = OldVisitInfo + "BeginTime(" + mypatientvisit.getBeginTime() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BeginTime(" + patientvisit.getBeginTime() + "),";            
            
                    mypatientvisit.setBeginTime(patientvisit.getBeginTime());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BeginTime(空值),";
                
                NewVisitInfo = NewVisitInfo + "BeginTime(" + patientvisit.getBeginTime() + "),";            
        
                mypatientvisit.setBeginTime(patientvisit.getBeginTime());
            }
        }
        
        //end_time
        if(isPopulated(patientvisit.getEndTime()))
        {
            if(isPopulated(mypatientvisit.getEndTime()))
            {
                if(!(mypatientvisit.getEndTime()==patientvisit.getEndTime()))
                {
                    OldVisitInfo = OldVisitInfo + "BeginTime(" + mypatientvisit.getEndTime() + "),";
                
                    NewVisitInfo = NewVisitInfo + "BeginTime(" + patientvisit.getEndTime() + "),";          
            
                    mypatientvisit.setEndTime(patientvisit.getEndTime());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "BeginTime(空值),";
                
                NewVisitInfo = NewVisitInfo + "BeginTime(" + patientvisit.getEndTime() + "),";          
        
                mypatientvisit.setEndTime(patientvisit.getEndTime());
            }
        }
        
        
        //ynbook
        if(isPopulated(patientvisit.getYnBook()))
        {
            if(isPopulated(mypatientvisit.getYnBook()))
            {
                if(!(mypatientvisit.getYnBook().equalsIgnoreCase(patientvisit.getYnBook())))
                {
                    OldVisitInfo = OldVisitInfo + "YnBook(" + mypatientvisit.getYnBook() + "),";
                
                    NewVisitInfo = NewVisitInfo + "YnBook(" + patientvisit.getYnBook() + "),";          
            
                    mypatientvisit.setYnBook(patientvisit.getYnBook());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "YnBook(空值),";
                
                NewVisitInfo = NewVisitInfo + "YnBook(" + patientvisit.getYnBook() + "),";          
        
                mypatientvisit.setYnBook(patientvisit.getYnBook());
            }
        }
        
        
        //ynfr
        if(isPopulated(patientvisit.getYNFR()))
        {
            if(isPopulated(mypatientvisit.getYNFR()))
            {
                if(!(mypatientvisit.getYNFR().equalsIgnoreCase(patientvisit.getYNFR())))
                {
                    OldVisitInfo = OldVisitInfo + "YNFR(" + mypatientvisit.getYNFR() + "),";
                
                    NewVisitInfo = NewVisitInfo + "YNFR(" + patientvisit.getYNFR() + "),";          
            
                    mypatientvisit.setYNFR(patientvisit.getYNFR());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "YNFR(空值),";
                
                NewVisitInfo = NewVisitInfo + "YNFR(" + patientvisit.getYNFR() + "),";          
        
                mypatientvisit.setYNFR(patientvisit.getYNFR());
            }
        }
        
        //qppend_flag
        if(isPopulated(patientvisit.getAppendFlag()))
        {
            if(isPopulated(mypatientvisit.getAppendFlag()))
            {
                if(!(mypatientvisit.getAppendFlag().equalsIgnoreCase(patientvisit.getAppendFlag())))
                {
                    OldVisitInfo = OldVisitInfo + "AppendFlag(" + mypatientvisit.getAppendFlag() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AppendFlag(" + patientvisit.getAppendFlag() + "),";          
            
                    mypatientvisit.setAppendFlag(patientvisit.getAppendFlag());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AppendFlag(空值),";
                
                NewVisitInfo = NewVisitInfo + "AppendFlag(" + patientvisit.getAppendFlag() + "),";          
        
                mypatientvisit.setAppendFlag(patientvisit.getAppendFlag());
            }
        }
        
        //qppend_flag
        if(isPopulated(patientvisit.getAppendFlag()))
        {
            if(isPopulated(mypatientvisit.getAppendFlag()))
            {
                if(!(mypatientvisit.getAppendFlag().equalsIgnoreCase(patientvisit.getAppendFlag())))
                {
                    OldVisitInfo = OldVisitInfo + "AppendFlag(" + mypatientvisit.getAppendFlag() + "),";
                
                    NewVisitInfo = NewVisitInfo + "AppendFlag(" + patientvisit.getAppendFlag() + "),";          
            
                    mypatientvisit.setAppendFlag(patientvisit.getAppendFlag());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "AppendFlag(空值),";
                
                NewVisitInfo = NewVisitInfo + "AppendFlag(" + patientvisit.getAppendFlag() + "),";          
        
                mypatientvisit.setAppendFlag(patientvisit.getAppendFlag());
            }
        }
        
        //ynsee
        if(isPopulated(patientvisit.getYnSee()))
        {
            if(isPopulated(mypatientvisit.getYnSee()))
            {
                if(!(mypatientvisit.getYnSee().equalsIgnoreCase(patientvisit.getYnSee())))
                {
                    OldVisitInfo = OldVisitInfo + "YnSee(" + mypatientvisit.getYnSee() + "),";
                
                    NewVisitInfo = NewVisitInfo + "YnSee(" + patientvisit.getYnSee() + "),";            
            
                    mypatientvisit.setYnSee(patientvisit.getYnSee());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "YnSee(空值),";
                
                NewVisitInfo = NewVisitInfo + "YnSee(" + patientvisit.getYnSee() + "),";            
        
                mypatientvisit.setYnSee(patientvisit.getYnSee());
            }
        }
        
        //seedate
        if(isPopulated(patientvisit.getSeeDate()))
        {
            if(isPopulated(mypatientvisit.getSeeDate()))
            {
                if(!(mypatientvisit.getSeeDate()==patientvisit.getSeeDate()))
                {
                    OldVisitInfo = OldVisitInfo + "SeeDate(" + mypatientvisit.getSeeDate() + "),";
                
                    NewVisitInfo = NewVisitInfo + "SeeDate(" + patientvisit.getSeeDate() + "),";            
            
                    mypatientvisit.setSeeDate(patientvisit.getSeeDate());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "SeeDate(空值),";
                
                NewVisitInfo = NewVisitInfo + "SeeDate(" + patientvisit.getSeeDate() + "),";            
        
                mypatientvisit.setSeeDate(patientvisit.getSeeDate());
            }
        }
        
        
        //triage_flag
        if(isPopulated(patientvisit.getTriageFlag()))
        {
            if(isPopulated(mypatientvisit.getTriageFlag()))
            {
                if(!(mypatientvisit.getTriageFlag().equalsIgnoreCase(patientvisit.getTriageFlag())))
                {
                    OldVisitInfo = OldVisitInfo + "TriageFlag(" + mypatientvisit.getTriageFlag() + "),";
                
                    NewVisitInfo = NewVisitInfo + "TriageFlag(" + patientvisit.getTriageFlag() + "),";          
            
                    mypatientvisit.setTriageFlag(patientvisit.getTriageFlag());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "TriageFlag(空值),";
                
                NewVisitInfo = NewVisitInfo + "TriageFlag(" + patientvisit.getTriageFlag() + "),";          
        
                mypatientvisit.setTriageFlag(patientvisit.getTriageFlag());
            }
        }
        
        
        //triage_opcd
        if(isPopulated(patientvisit.getTriageOpcd()))
        {
            if(isPopulated(mypatientvisit.getTriageOpcd()))
            {
                if(!(mypatientvisit.getTriageOpcd().equalsIgnoreCase(patientvisit.getTriageOpcd())))
                {
                    OldVisitInfo = OldVisitInfo + "TriageOpcd(" + mypatientvisit.getTriageOpcd() + "),";
                
                    NewVisitInfo = NewVisitInfo + "TriageOpcd(" + patientvisit.getTriageOpcd() + "),";          
            
                    mypatientvisit.setTriageOpcd(patientvisit.getTriageOpcd());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "TriageOpcd(空值),";
                
                NewVisitInfo = NewVisitInfo + "TriageOpcd(" + patientvisit.getTriageOpcd() + "),";          
        
                mypatientvisit.setTriageOpcd(patientvisit.getTriageOpcd());
            }
        }
        
        
        //triage_date
        if(isPopulated(patientvisit.getTriageDate()))
        {
            if(isPopulated(mypatientvisit.getTriageDate()))
            {
                if(!(mypatientvisit.getTriageDate()==patientvisit.getTriageDate()))
                {
                    OldVisitInfo = OldVisitInfo + "TriageDate(" + mypatientvisit.getTriageDate() + "),";
                
                    NewVisitInfo = NewVisitInfo + "TriageDate(" + patientvisit.getTriageDate() + "),";          
            
                    mypatientvisit.setTriageDate(patientvisit.getTriageDate());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "TriageDate(空值),";
                
                NewVisitInfo = NewVisitInfo + "TriageDate(" + patientvisit.getTriageDate() + "),";          
        
                mypatientvisit.setTriageDate(patientvisit.getTriageDate());
            }
        }
        
        //see_dpcd
        if(isPopulated(patientvisit.getSeeDpcd()))
        {
            if(isPopulated(mypatientvisit.getSeeDpcd()))
            {
                if(!(mypatientvisit.getSeeDpcd().equalsIgnoreCase(patientvisit.getSeeDpcd())))
                {
                    OldVisitInfo = OldVisitInfo + "SeeDpcd(" + mypatientvisit.getSeeDpcd() + "),";
                
                    NewVisitInfo = NewVisitInfo + "SeeDpcd(" + patientvisit.getSeeDpcd() + "),";            
            
                    mypatientvisit.setSeeDpcd(patientvisit.getSeeDpcd());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "SeeDpcd(空值),";
                
                NewVisitInfo = NewVisitInfo + "SeeDpcd(" + patientvisit.getSeeDpcd() + "),";            
        
                mypatientvisit.setSeeDpcd(patientvisit.getSeeDpcd());
            }
        }
        
        
        //see_docd
        if(isPopulated(patientvisit.getSeeDocd()))
        {
            if(isPopulated(mypatientvisit.getSeeDocd()))
            {
                if(!(mypatientvisit.getSeeDocd().equalsIgnoreCase(patientvisit.getSeeDocd())))
                {
                    OldVisitInfo = OldVisitInfo + "SeeDocd(" + mypatientvisit.getSeeDocd() + "),";
                
                    NewVisitInfo = NewVisitInfo + "SeeDocd(" + patientvisit.getSeeDocd() + "),";            
            
                    mypatientvisit.setSeeDocd(patientvisit.getSeeDocd());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "SeeDocd(空值),";
                
                NewVisitInfo = NewVisitInfo + "SeeDocd(" + patientvisit.getSeeDocd() + "),";            
        
                mypatientvisit.setSeeDocd(patientvisit.getSeeDocd());
            }
        }
        
        
        //out_patient_status_a
        if(isPopulated(patientvisit.getOutPatientStatusA()))
        {
            if(isPopulated(mypatientvisit.getOutPatientStatusA()))
            {
                if(!(mypatientvisit.getOutPatientStatusA().equalsIgnoreCase(patientvisit.getOutPatientStatusA())))
                {
                    OldVisitInfo = OldVisitInfo + "OutPatientStatusA(" + mypatientvisit.getOutPatientStatusA() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OutPatientStatusA(" + patientvisit.getOutPatientStatusA() + "),";            
            
                    mypatientvisit.setOutPatientStatusA(patientvisit.getOutPatientStatusA());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OutPatientStatusA(空值),";
                
                NewVisitInfo = NewVisitInfo + "OutPatientStatusA(" + patientvisit.getOutPatientStatusA() + "),";            
        
                mypatientvisit.setOutPatientStatusA(patientvisit.getOutPatientStatusA());
            }
        }
        
        //out_patient_status_B
        if(isPopulated(patientvisit.getOutPatientStatusB()))
        {
            if(isPopulated(mypatientvisit.getOutPatientStatusB()))
            {
                if(!(mypatientvisit.getOutPatientStatusB().equalsIgnoreCase(patientvisit.getOutPatientStatusB())))
                {
                    OldVisitInfo = OldVisitInfo + "OutPatientStatusB(" + mypatientvisit.getOutPatientStatusB() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OutPatientStatusB(" + patientvisit.getOutPatientStatusB() + "),";            
            
                    mypatientvisit.setOutPatientStatusB(patientvisit.getOutPatientStatusB());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OutPatientStatusB(空值),";
                
                NewVisitInfo = NewVisitInfo + "OutPatientStatusB(" + patientvisit.getOutPatientStatusB() + "),";            
        
                mypatientvisit.setOutPatientStatusB(patientvisit.getOutPatientStatusB());
            }
        }
        
        //out_patient_status_c
        if(isPopulated(patientvisit.getOutPatientStatusC()))
        {
            if(isPopulated(mypatientvisit.getOutPatientStatusC()))
            {
                if(!(mypatientvisit.getOutPatientStatusC().equalsIgnoreCase(patientvisit.getOutPatientStatusC())))
                {
                    OldVisitInfo = OldVisitInfo + "OutPatientStatusC(" + mypatientvisit.getOutPatientStatusC() + "),";
                
                    NewVisitInfo = NewVisitInfo + "OutPatientStatusC(" + patientvisit.getOutPatientStatusC() + "),";            
            
                    mypatientvisit.setOutPatientStatusC(patientvisit.getOutPatientStatusC());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "OutPatientStatusC(空值),";
                
                NewVisitInfo = NewVisitInfo + "OutPatientStatusC(" + patientvisit.getOutPatientStatusC() + "),";            
        
                mypatientvisit.setOutPatientStatusC(patientvisit.getOutPatientStatusC());
            }
        }
        
        
        //in_patient_status_a
        if(isPopulated(patientvisit.getInPatientStatusA()))
        {
            if(isPopulated(mypatientvisit.getInPatientStatusA()))
            {
                if(!(mypatientvisit.getInPatientStatusA().equalsIgnoreCase(patientvisit.getInPatientStatusA())))
                {
                    OldVisitInfo = OldVisitInfo + "InPatientStatusA(" + mypatientvisit.getInPatientStatusA() + "),";
                
                    NewVisitInfo = NewVisitInfo + "InPatientStatusA(" + patientvisit.getInPatientStatusA() + "),";          
            
                    mypatientvisit.setInPatientStatusA(patientvisit.getInPatientStatusA());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "InPatientStatusA(空值),";
                
                NewVisitInfo = NewVisitInfo + "InPatientStatusA(" + patientvisit.getInPatientStatusA() + "),";          
        
                mypatientvisit.setInPatientStatusA(patientvisit.getInPatientStatusA());
            }
        }
        
        //in_patient_status_b
        if(isPopulated(patientvisit.getInPatientStatusB()))
        {
            if(isPopulated(mypatientvisit.getInPatientStatusB()))
            {
                if(!(mypatientvisit.getInPatientStatusB().equalsIgnoreCase(patientvisit.getInPatientStatusB())))
                {
                    OldVisitInfo = OldVisitInfo + "InPatientStatusB(" + mypatientvisit.getInPatientStatusB() + "),";
                
                    NewVisitInfo = NewVisitInfo + "InPatientStatusB(" + patientvisit.getInPatientStatusB() + "),";          
            
                    mypatientvisit.setInPatientStatusB(patientvisit.getInPatientStatusB());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "InPatientStatusB(空值),";
                
                NewVisitInfo = NewVisitInfo + "InPatientStatusB(" + patientvisit.getInPatientStatusB() + "),";          
        
                mypatientvisit.setInPatientStatusB(patientvisit.getInPatientStatusB());
            }
        }
        
        if(isPopulated(person.getCustom19()))
        {
            String TempStr1 = person.getCustom19().substring(0, person.getCustom19().indexOf("^"));
            
            String TempStr2 = person.getCustom19().substring(person.getCustom19().indexOf("^")+1);
            
            if(isPopulated(mypatientvisit.getRelevanceID()))
            {
                if(!mypatientvisit.getRelevanceID().equalsIgnoreCase(TempStr1))
                {
                    mypatientvisit.setRelevanceID(TempStr1);
                }
            }
            
            if(isPopulated(mypatientvisit.getRelevanceDomain()))
            {
                if(!mypatientvisit.getRelevanceDomain().equalsIgnoreCase(TempStr2))
                {
                    mypatientvisit.setRelevanceDomain(TempStr2);
                }
            }
        }
        
        if(isPopulated(person.getGivenName()))
        {
            if(isPopulated(mypatientvisit.getRelevanceName()))
            {
                if(!mypatientvisit.getRelevanceName().equalsIgnoreCase(person.getGivenName()))
                {
                    mypatientvisit.setRelevanceName(person.getGivenName());
                }
            }
        }
        //in_patient_status_C
        if(isPopulated(patientvisit.getInPatientStatusC()))
        {
            if(isPopulated(mypatientvisit.getInPatientStatusC()))
            {
                if(!(mypatientvisit.getInPatientStatusC().equalsIgnoreCase(patientvisit.getInPatientStatusC())))
                {
                    OldVisitInfo = OldVisitInfo + "InPatientStatusC(" + mypatientvisit.getInPatientStatusC() + "),";
                
                    NewVisitInfo = NewVisitInfo + "InPatientStatusC(" + patientvisit.getInPatientStatusC() + "),";          
            
                    mypatientvisit.setInPatientStatusC(patientvisit.getInPatientStatusC());
                }
            }
            else
            {
                OldVisitInfo = OldVisitInfo + "InPatientStatusC(空值),";
                
                NewVisitInfo = NewVisitInfo + "InPatientStatusC(" + patientvisit.getInPatientStatusC() + "),";          
        
                mypatientvisit.setInPatientStatusC(patientvisit.getInPatientStatusC());
            }
        }
        
        VisitInfo.add(OldVisitInfo);
        
        VisitInfo.add(NewVisitInfo);
        
        return VisitInfo;
            
    }

    //一个函数2000行
    public static List<String> updatePersonAttributes(Person originalPerson, Person person)
    {
        List<String> PersonInfo = new ArrayList<String>();
        
        String OldPersonInfo = "Old Person Info: ";
        
        String NewPersonInfo = "New Person Info: ";
        
        //中文姓名
        if (isPopulated(person.getGivenName())) 
        {
            if (isPopulated(originalPerson.getGivenName())) 
            {
                if(!originalPerson.getGivenName().equalsIgnoreCase(person.getGivenName()))
                {
                    OldPersonInfo = OldPersonInfo + "givenName(" + originalPerson.getGivenName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "givenName(" + person.getGivenName() + "),";            
            
                    originalPerson.setGivenName(person.getGivenName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "givenName(空值),";
                
                NewPersonInfo = NewPersonInfo + "givenName(" + person.getGivenName() + "),";            
        
                originalPerson.setGivenName(person.getGivenName());
            }
        }
        
        //Hansen
        //Bug修正开始：修正不修改姓名的问题
        if (isPopulated(person.getName())) 
        {
            if (isPopulated(originalPerson.getName())) 
            {
                if(!originalPerson.getName().equalsIgnoreCase(person.getName()))
                {
                    OldPersonInfo = OldPersonInfo + "name(" + originalPerson.getName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "name(" + person.getName() + "),";            
            
                    originalPerson.setName(person.getName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "name(空值),";
                
                NewPersonInfo = NewPersonInfo + "name(" + person.getName() + "),";            
        
                originalPerson.setName(person.getName());
            }
        }
        //Bug修正结束：修正不修改姓名的问题
        
        if (isPopulated(person.getFamilyName())) 
        {
            if (isPopulated(originalPerson.getFamilyName())) 
            {
                if(!originalPerson.getFamilyName().equalsIgnoreCase(person.getFamilyName()))
                {
                    OldPersonInfo = OldPersonInfo + "familyName(" + originalPerson.getFamilyName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "familyName(" + person.getFamilyName() + "),";
            
                    originalPerson.setFamilyName(person.getFamilyName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "familyName(空值),";
                
                NewPersonInfo = NewPersonInfo + "familyName(" + person.getFamilyName() + "),";
        
                originalPerson.setFamilyName(person.getFamilyName());
            }
        }
        
        //出生日期
        if (isPopulated(person.getDateOfBirth())) 
        {
            if (isPopulated(originalPerson.getDateOfBirth())) 
            {
                if(!originalPerson.getDateOfBirth().toString().equalsIgnoreCase(person.getDateOfBirth().toString()))
                {
                    OldPersonInfo = OldPersonInfo + "dateOfBirth(" + originalPerson.getDateOfBirth().toString() + "),";
                
                    NewPersonInfo = NewPersonInfo + "dateOfBirth(" + person.getDateOfBirth().toString() + "),";         
            
                    originalPerson.setDateOfBirth(person.getDateOfBirth());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "dateOfBirth(空值),";
                
                NewPersonInfo = NewPersonInfo + "dateOfBirth(" + person.getDateOfBirth().toString() + "),";         
        
                originalPerson.setDateOfBirth(person.getDateOfBirth());
            }
        }
         
        //出生地所在地省
        if (isPopulated(person.getBirthProvince())) 
        {
            if (isPopulated(originalPerson.getBirthProvince())) 
            {
                if(!originalPerson.getBirthProvince().equalsIgnoreCase(person.getBirthProvince()))
                {
                    OldPersonInfo = OldPersonInfo + "birthProvince(" + originalPerson.getBirthProvince() + "),";
                
                    NewPersonInfo = NewPersonInfo + "birthProvince(" + person.getBirthProvince() + "),";            
            
                    originalPerson.setBirthProvince(person.getBirthProvince());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "birthProvince(空值),";
                
                NewPersonInfo = NewPersonInfo + "birthProvince(" + person.getBirthProvince() + "),";            
        
                originalPerson.setBirthProvince(person.getBirthProvince());
            }
        }
        
        //出生所在地市
        if (isPopulated(person.getBirthCity())) 
        {
            if (isPopulated(originalPerson.getBirthCity())) 
            {
                if(!originalPerson.getBirthCity().equalsIgnoreCase(person.getBirthCity()))
                {
                    OldPersonInfo = OldPersonInfo + "birthCity(" + originalPerson.getBirthCity() + "),";
                
                    NewPersonInfo = NewPersonInfo + "birthCity(" + person.getBirthCity() + "),";            
            
                    originalPerson.setBirthCity(person.getBirthCity());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "birthCity(空值),";
                
                NewPersonInfo = NewPersonInfo + "birthCity(" + person.getBirthCity() + "),";            
        
                originalPerson.setBirthCity(person.getBirthCity());
            }
        }
        
        //出生所在区县
        if (isPopulated(person.getBirthCounty())) 
        {
            if (isPopulated(originalPerson.getBirthCounty())) 
            {
                if(!originalPerson.getBirthCounty().equalsIgnoreCase(person.getBirthCounty()))
                {
                    OldPersonInfo = OldPersonInfo + "birthCounty(" + originalPerson.getBirthCounty() + "),";
                
                    NewPersonInfo = NewPersonInfo + "birthCounty(" + person.getBirthCounty() + "),";            
            
                    originalPerson.setBirthCounty(person.getBirthCounty());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "birthCounty(空值),";
                
                NewPersonInfo = NewPersonInfo + "birthCounty(" + person.getBirthCounty() + "),";            
        
                originalPerson.setBirthCounty(person.getBirthCounty());
            }
        }
        
        //出生地
        if (isPopulated(person.getBirthPlace())) 
        {
            if (isPopulated(originalPerson.getBirthPlace())) 
            {
                if(!originalPerson.getBirthPlace().equalsIgnoreCase(person.getBirthPlace()))
                {
                    OldPersonInfo = OldPersonInfo + "birthPlace(" + originalPerson.getBirthPlace() + "),";
                
                    NewPersonInfo = NewPersonInfo + "birthPlace(" + person.getBirthPlace() + "),";          
            
                    originalPerson.setBirthPlace(person.getBirthPlace());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "birthPlace(空值),";
                
                NewPersonInfo = NewPersonInfo + "birthPlace(" + person.getBirthPlace() + "),";          
        
                originalPerson.setBirthPlace(person.getBirthPlace());
            }
        }
        
        //出生地所在邮编
        if (isPopulated(person.getBirthZip())) 
        {
            if (isPopulated(originalPerson.getBirthZip())) 
            {
                if(!originalPerson.getBirthZip().equalsIgnoreCase(person.getBirthZip()))
                {
                    OldPersonInfo = OldPersonInfo + "birthZip(" + originalPerson.getBirthZip() + "),";
                
                    NewPersonInfo = NewPersonInfo + "birthZip(" + person.getBirthZip() + "),";          
            
                    originalPerson.setBirthZip(person.getBirthZip());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "birthZip(空值),";
                
                NewPersonInfo = NewPersonInfo + "birthZip(" + person.getBirthZip() + "),";          
        
                originalPerson.setBirthZip(person.getBirthZip());
            }
        }
        
        //多胞胎
        if(isPopulated(person.getMultipleBirthInd()))
        {
            if(isPopulated(originalPerson.getMultipleBirthInd()))
            {
                if(!originalPerson.getMultipleBirthInd().equalsIgnoreCase(person.getMultipleBirthInd()))
                {
                    OldPersonInfo = OldPersonInfo + "multipleBirthInd(" + originalPerson.getMultipleBirthInd() + "),";
                
                    NewPersonInfo = NewPersonInfo + "multipleBirthInd(" + person.getMultipleBirthInd() + "),";
            
                    originalPerson.setMultipleBirthInd(person.getMultipleBirthInd());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "multipleBirthInd(空值),";
                
                NewPersonInfo = NewPersonInfo + "multipleBirthInd(" + person.getMultipleBirthInd() + "),";
        
                originalPerson.setMultipleBirthInd(person.getMultipleBirthInd());
            }
        }
        
        //出生顺序
        if(isPopulated(person.getBirthOrder().toString()))
        {
            if(isPopulated(originalPerson.getBirthOrder().toString()))
            {
                if(!originalPerson.getBirthOrder().toString().equalsIgnoreCase(person.getBirthOrder().toString()))
                {
                    OldPersonInfo = OldPersonInfo + "multipleBirthInd(" + originalPerson.getBirthOrder().toString() + "),";
                
                    NewPersonInfo = NewPersonInfo + "multipleBirthInd(" + person.getBirthOrder().toString() + "),";
            
                    originalPerson.setBirthOrder(person.getBirthOrder());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "multipleBirthInd(空值),";
                
                NewPersonInfo = NewPersonInfo + "multipleBirthInd(" + person.getBirthOrder().toString() + "),";
        
                originalPerson.setBirthOrder(person.getBirthOrder());
            }
        }
        
        //母亲娘家姓
        if(isPopulated(person.getMothersMaidenName()))
        {
            if(isPopulated(originalPerson.getMothersMaidenName()))
            {
                if(!originalPerson.getMothersMaidenName().equalsIgnoreCase(person.getMothersMaidenName()))
                {
                    OldPersonInfo = OldPersonInfo + "mothersMaidenName(" + originalPerson.getMothersMaidenName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "mothersMaidenName(" + person.getMothersMaidenName() + "),";
            
                    originalPerson.setMothersMaidenName(person.getMothersMaidenName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "mothersMaidenName(空值),";
                
                NewPersonInfo = NewPersonInfo + "mothersMaidenName(" + person.getMothersMaidenName() + "),";
        
                originalPerson.setMothersMaidenName(person.getMothersMaidenName());
            }
        }
        
        //社会保险号
        if(isPopulated(person.getSsn()))
        {
            if(isPopulated(originalPerson.getSsn()))
            {
                if(!originalPerson.getSsn().equalsIgnoreCase(person.getSsn()))
                {
                    OldPersonInfo = OldPersonInfo + "ssn(" + originalPerson.getSsn() + "),";
                
                    NewPersonInfo = NewPersonInfo + "ssn(" + person.getSsn() + "),";
            
                    originalPerson.setSsn(person.getSsn());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "ssn(空值),";
                
                NewPersonInfo = NewPersonInfo + "ssn(" + person.getSsn() + "),";
        
                originalPerson.setSsn(person.getSsn());
            }
        }
        
        //身份证件号
        if(isPopulated(person.getIdentityNo()))
        {
            if(isPopulated(originalPerson.getIdentityNo()))
            {
                if(!originalPerson.getIdentityNo().equalsIgnoreCase(person.getIdentityNo()))
                {
                    OldPersonInfo = OldPersonInfo + "identityNo(" + originalPerson.getIdentityNo() + "),";
                
                    NewPersonInfo = NewPersonInfo + "identityNo(" + person.getIdentityNo() + "),";
            
                    originalPerson.setIdentityNo(person.getIdentityNo());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "identityNo(空值),";
                
                NewPersonInfo = NewPersonInfo + "identityNo(" + person.getIdentityNo() + "),";
        
                originalPerson.setIdentityNo(person.getIdentityNo());
            }
        }
        
        
        //市民卡号
        if(isPopulated(person.getCitizenCard()))
        {
            if(isPopulated(originalPerson.getCitizenCard()))
            {
                if(!originalPerson.getCitizenCard().equalsIgnoreCase(person.getCitizenCard()))
                {
                    OldPersonInfo = OldPersonInfo + "citizenCard(" + originalPerson.getCitizenCard() + "),";
                
                    NewPersonInfo = NewPersonInfo + "citizenCard(" + person.getCitizenCard() + "),";
            
                    originalPerson.setCitizenCard(person.getCitizenCard());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "citizenCard(空值),";
                
                NewPersonInfo = NewPersonInfo + "citizenCard(" + person.getCitizenCard() + "),";
        
                originalPerson.setCitizenCard(person.getCitizenCard());
            }
        }
        
        //医疗证件号
        if(isPopulated(person.getMedicalCertificate()))
        {
            if(isPopulated(originalPerson.getMedicalCertificate()))
            {
                if(!originalPerson.getMedicalCertificate().equalsIgnoreCase(person.getMedicalCertificate()))
                {
                    OldPersonInfo = OldPersonInfo + "medicalCertificate(" + originalPerson.getMedicalCertificate() + "),";
                
                    NewPersonInfo = NewPersonInfo + "medicalCertificate(" + person.getMedicalCertificate() + "),";
            
                    originalPerson.setMedicalCertificate(person.getMedicalCertificate());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "medicalCertificate(空值),";
                
                NewPersonInfo = NewPersonInfo + "medicalCertificate(" + person.getMedicalCertificate() + "),";
        
                originalPerson.setMedicalCertificate(person.getMedicalCertificate());
            }
        }
    
        //医保个人编号
        if(isPopulated(person.getMeicarePerson()))
        {
            if(isPopulated(originalPerson.getMeicarePerson()))
            {
                if(!originalPerson.getMeicarePerson().equalsIgnoreCase(person.getMeicarePerson()))
                {
                    OldPersonInfo = OldPersonInfo + "meicarePerson(" + originalPerson.getMeicarePerson() + "),";
                
                    NewPersonInfo = NewPersonInfo + "meicarePerson(" + person.getMeicarePerson() + "),";
            
                    originalPerson.setMeicarePerson(person.getMeicarePerson());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "meicarePerson(空值),";
                
                NewPersonInfo = NewPersonInfo + "meicarePerson(" + person.getMeicarePerson() + "),";
        
                originalPerson.setMeicarePerson(person.getMeicarePerson());
            }
        }
        
        //老人证件号
        if(isPopulated(person.getElderCertificate()))
        {
            if(isPopulated(originalPerson.getElderCertificate()))
            {
                if(!originalPerson.getElderCertificate().equalsIgnoreCase(person.getElderCertificate()))
                {
                    OldPersonInfo = OldPersonInfo + "elderCertificate(" + originalPerson.getElderCertificate() + "),";
                
                    NewPersonInfo = NewPersonInfo + "elderCertificate(" + person.getElderCertificate() + "),";
            
                    originalPerson.setElderCertificate(person.getElderCertificate());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "elderCertificate(空值),";
                
                NewPersonInfo = NewPersonInfo + "elderCertificate(" + person.getElderCertificate() + "),";
        
                originalPerson.setElderCertificate(person.getElderCertificate());
            }
        }
        
        //病例号
        if(isPopulated(person.getOpcaseno()))
        {
            if(isPopulated(originalPerson.getOpcaseno()))
            {
                if(!originalPerson.getOpcaseno().equalsIgnoreCase(person.getOpcaseno()))
                {
                    OldPersonInfo = OldPersonInfo + "opcaseno(" + originalPerson.getOpcaseno() + "),";
                
                    NewPersonInfo = NewPersonInfo + "opcaseno(" + person.getOpcaseno() + "),";
            
                    originalPerson.setOpcaseno(person.getOpcaseno());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "opcaseno(空值),";
                
                NewPersonInfo = NewPersonInfo + "opcaseno(" + person.getOpcaseno() + "),";
        
                originalPerson.setOpcaseno(person.getOpcaseno());
            }
        }
        
        //健康卡号
        if(isPopulated(person.getHealthCard()))
        {
            if(isPopulated(originalPerson.getHealthCard()))
            {
                if(!originalPerson.getHealthCard().equalsIgnoreCase(person.getHealthCard()))
                {
                    OldPersonInfo = OldPersonInfo + "healthCard(" + originalPerson.getHealthCard() + "),";
                
                    NewPersonInfo = NewPersonInfo + "healthCard(" + person.getHealthCard() + "),";
            
                    originalPerson.setHealthCard(person.getOpcaseno());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "healthCard(空值),";
                
                NewPersonInfo = NewPersonInfo + "healthCard(" + person.getHealthCard() + "),";
        
                originalPerson.setHealthCard(person.getHealthCard());
            }
        }
        
        //医疗保险号
        if(isPopulated(person.getInsuranceNo()))
        {
            if(isPopulated(originalPerson.getInsuranceNo()))
            {
                if(!originalPerson.getInsuranceNo().equalsIgnoreCase(person.getInsuranceNo()))
                {
                    OldPersonInfo = OldPersonInfo + "insuranceNo(" + originalPerson.getInsuranceNo() + "),";
                
                    NewPersonInfo = NewPersonInfo + "insuranceNo(" + person.getInsuranceNo() + "),";
            
                    originalPerson.setInsuranceNo(person.getInsuranceNo());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "insuranceNo(空值),";
                
                NewPersonInfo = NewPersonInfo + "insuranceNo(" + person.getInsuranceNo() + "),";
        
                originalPerson.setInsuranceNo(person.getInsuranceNo());
            }
        }
        
        //性别编码
        if(person.getGenderDict()!=null)
        {
            originalPerson.setGenderDict(person.getGenderDict());
        }
        
        //性别编码名称
        if (isPopulated(person.getGenderName())) 
        {
            if (isPopulated(originalPerson.getGenderName())) 
            {
                if(!originalPerson.getGenderName().equalsIgnoreCase(person.getGenderName()))
                {
                    OldPersonInfo = OldPersonInfo + "genderName(" + originalPerson.getGenderName() + "),";
                    
                    NewPersonInfo = NewPersonInfo + "genderName(" + person.getGenderName() + "),";
            
                    originalPerson.setGenderName(person.getAddress1());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "genderName(空值),";
                
                NewPersonInfo = NewPersonInfo + "genderName(" + person.getGenderName() + "),";
        
                originalPerson.setGenderName(person.getGenderName());
            }
        }
        
        //性别编码系统
        if (isPopulated(person.getGenderDomain())) 
        {
            if (isPopulated(originalPerson.getGenderDomain())) 
            {
                if(!originalPerson.getGenderDomain().equalsIgnoreCase(person.getGenderDomain()))
                {
                    OldPersonInfo = OldPersonInfo + "genderDomain(" + originalPerson.getGenderDomain() + "),";
                
                    NewPersonInfo = NewPersonInfo + "genderDomain(" + person.getGenderDomain() + "),";
            
                    originalPerson.setGenderDomain(person.getGenderDomain());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "genderDomain(空值),";
                
                NewPersonInfo = NewPersonInfo + "genderDomain(" + person.getGenderDomain() + "),";
        
                originalPerson.setGenderDomain(person.getGenderDomain());
            }
        }
        
        //民族编码
        if(person.getEthnicgroupDict()!=null)
        {
            originalPerson.setEthnicgroupDict(person.getEthnicgroupDict());
        }
        
        //民族编码名称
        if (isPopulated(person.getEthnicName())) 
        {
            if (isPopulated(originalPerson.getEthnicName())) 
            {
                if(!originalPerson.getEthnicName().equalsIgnoreCase(person.getEthnicName()))
                {
                    OldPersonInfo = OldPersonInfo + "ethnicName(" + originalPerson.getEthnicName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "ethnicName(" + person.getEthnicName() + "),";
            
                    originalPerson.setEthnicName(person.getEthnicName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "ethnicName(空值),";
                
                NewPersonInfo = NewPersonInfo + "ethnicName(" + person.getEthnicName() + "),";
        
                originalPerson.setEthnicName(person.getEthnicName());
            }
        }
        
        //民族编码系统
        if (isPopulated(person.getEthincDomain())) 
        {
            if (isPopulated(originalPerson.getEthincDomain())) 
            {
                if(!originalPerson.getEthincDomain().equalsIgnoreCase(person.getEthincDomain()))
                {
                    OldPersonInfo = OldPersonInfo + "ethincDomain(" + originalPerson.getEthincDomain() + "),";
                
                    NewPersonInfo = NewPersonInfo + "ethincDomain(" + person.getEthincDomain() + "),";
            
                    originalPerson.setEthincDomain(person.getEthincDomain());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "ethincDomain(空值),";
                
                NewPersonInfo = NewPersonInfo + "ethincDomain(" + person.getEthincDomain() + "),";
        
                originalPerson.setEthincDomain(person.getEthincDomain());
            }
        }
        
        //种族编码
        if(person.getRaceDict()!=null)
        {
            originalPerson.setRaceDict(person.getRaceDict());
        }
        
        //种族编码名称
        if (isPopulated(person.getRaceName())) 
        {
            if (isPopulated(originalPerson.getRaceName())) 
            {
                if(!originalPerson.getRaceName().equalsIgnoreCase(person.getRaceName()))
                {
                    OldPersonInfo = OldPersonInfo + "raceName(" + originalPerson.getRaceName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "raceName(" + person.getRaceName() + "),";
            
                    originalPerson.setRaceName(person.getRaceName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "raceName(空值),";
                
                NewPersonInfo = NewPersonInfo + "raceName(" + person.getRaceName() + "),";
        
                originalPerson.setRaceName(person.getRaceName());
            }
        }
        
        //种族编码系统
        if (isPopulated(person.getRaceDomain())) 
        {
            if (isPopulated(originalPerson.getRaceDomain())) 
            {
                if(!originalPerson.getRaceDomain().equalsIgnoreCase(person.getRaceDomain()))
                {
                    OldPersonInfo = OldPersonInfo + "raceDomain(" + originalPerson.getRaceDomain() + "),";
                
                    NewPersonInfo = NewPersonInfo + "raceDomain(" + person.getRaceDomain() + "),";
            
                    originalPerson.setRaceDomain(person.getRaceDomain());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "raceDomain(空值),";
                
                NewPersonInfo = NewPersonInfo + "raceDomain(" + person.getRaceDomain() + "),";
        
                originalPerson.setRaceDomain(person.getRaceDomain());
            }
        }
        
        //国籍编码
        if (person.getNationalityDict()!=null) 
        {
            originalPerson.setNationalityDict(person.getNationalityDict());
        }
        
        //国籍编码名称
        if (isPopulated(person.getNationalityName())) 
        {
            if (isPopulated(originalPerson.getNationalityName())) 
            {
                if(!originalPerson.getNationalityName().equalsIgnoreCase(person.getNationalityName()))
                {
                    OldPersonInfo = OldPersonInfo + "nationalityName(" + originalPerson.getNationalityName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "nationalityName(" + person.getNationalityName() + "),";
            
                    originalPerson.setNationalityName(person.getNationalityName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "nationalityName(空值),";
                
                NewPersonInfo = NewPersonInfo + "nationalityName(" + person.getNationalityName() + "),";
        
                originalPerson.setNationalityName(person.getNationalityName());
            }
        }
        
        //国籍编码系统
        if (isPopulated(person.getNationalityDomain())) 
        {
            if (isPopulated(originalPerson.getNationalityDomain())) 
            {
                if(!originalPerson.getNationalityDomain().equalsIgnoreCase(person.getNationalityDomain()))
                {
                    OldPersonInfo = OldPersonInfo + "nationalityDomain(" + originalPerson.getNationalityDomain() + "),";
                
                    NewPersonInfo = NewPersonInfo + "nationalityDomain(" + person.getNationalityDomain() + "),";
            
                    originalPerson.setNationalityDomain(person.getNationalityDomain());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "nationalityDomain(空值),";
                
                NewPersonInfo = NewPersonInfo + "nationalityDomain(" + person.getNationalityDomain() + "),";
        
                originalPerson.setNationalityDomain(person.getNationalityDomain());
            }
        }
        
        //语言编码
        if (person.getLanguageDict()!=null) 
        {
            originalPerson.setLanguageDict(person.getLanguageDict());
        }
        
        //宗教编码
        if (person.getReligionDict()!=null) 
        {
            originalPerson.setReligionDict(person.getReligionDict());
        }
        
        //婚姻编码
        if (person.getMaritalStatusDict()!=null) 
        {
            originalPerson.setMaritalStatusDict(person.getMaritalStatusDict());
        }
        
        //婚姻编码名称
        if (isPopulated(person.getMaritalStatusName())) 
        {
            if (isPopulated(originalPerson.getMaritalStatusName())) 
            {
                if(!originalPerson.getMaritalStatusName().equalsIgnoreCase(person.getMaritalStatusName()))
                {
                    OldPersonInfo = OldPersonInfo + "maritalStatusName(" + originalPerson.getMaritalStatusName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "maritalStatusName(" + person.getMaritalStatusName() + "),";
            
                    originalPerson.setMaritalStatusName(person.getMaritalStatusName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "maritalStatusName(空值),";
                
                NewPersonInfo = NewPersonInfo + "maritalStatusName(" + person.getMaritalStatusName() + "),";
        
                originalPerson.setMaritalStatusName(person.getMaritalStatusName());
            }
        }
        
        //婚姻编码系统
        if (isPopulated(person.getMaritalDomain())) 
        {
            if (isPopulated(originalPerson.getMaritalDomain())) 
            {
                if(!originalPerson.getMaritalDomain().equalsIgnoreCase(person.getMaritalDomain()))
                {
                    OldPersonInfo = OldPersonInfo + "maritalDomain(" + originalPerson.getMaritalDomain() + "),";
                
                    NewPersonInfo = NewPersonInfo + "maritalDomain(" + person.getMaritalDomain() + "),";
            
                    originalPerson.setMaritalDomain(person.getMaritalDomain());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "maritalDomain(空值),";
                
                NewPersonInfo = NewPersonInfo + "maritalDomain(" + person.getMaritalDomain() + "),";
        
                originalPerson.setMaritalDomain(person.getMaritalDomain());
            }
        }
        
        //教育程度编码
        if (person.getDegreeTypeDict()!=null) 
        {
            originalPerson.setDegreeTypeDict(person.getDegreeTypeDict());
        }
        
        //教育程度名称
        if (isPopulated(person.getDegreeName())) 
        {
            if (isPopulated(originalPerson.getDegreeName())) 
            {
                if(!originalPerson.getDegreeName().equalsIgnoreCase(person.getDegreeName()))
                {
                    OldPersonInfo = OldPersonInfo + "degreeName(" + originalPerson.getDegreeName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "degreeName(" + person.getDegreeName() + "),";
            
                    originalPerson.setDegreeName(person.getDegreeName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "degreeName(空值),";
                
                NewPersonInfo = NewPersonInfo + "degreeName(" + person.getDegreeName() + "),";
        
                originalPerson.setDegreeName(person.getDegreeName());
            }
        }
        
        //教育程度编码系统
        if (isPopulated(person.getDegreeDomain())) 
        {
            if (isPopulated(originalPerson.getDegreeDomain())) 
            {
                if(!originalPerson.getDegreeDomain().equalsIgnoreCase(person.getDegreeDomain()))
                {
                    OldPersonInfo = OldPersonInfo + "degreeDomain(" + originalPerson.getDegreeDomain() + "),";
                
                    NewPersonInfo = NewPersonInfo + "degreeDomain(" + person.getDegreeDomain() + "),";
            
                    originalPerson.setDegreeDomain(person.getDegreeDomain());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "degreeDomain(空值),";
                
                NewPersonInfo = NewPersonInfo + "degreeDomain(" + person.getDegreeDomain() + "),";
        
                originalPerson.setDegreeDomain(person.getDegreeDomain());
            }
        }
        
        //邮件地址
        if (isPopulated(person.getEmail())) 
        {
            if (isPopulated(originalPerson.getEmail())) 
            {
                if(!originalPerson.getEmail().equalsIgnoreCase(person.getEmail()))
                {
                    OldPersonInfo = OldPersonInfo + "email(" + originalPerson.getEmail() + "),";
                
                    NewPersonInfo = NewPersonInfo + "email(" + person.getEmail() + "),";
            
                    originalPerson.setEmail(person.getEmail());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "email(空值),";
                
                NewPersonInfo = NewPersonInfo + "email(" + person.getEmail() + "),";
        
                originalPerson.setEmail(person.getEmail());
            }
        }
        
        //居住地所在地省
        if (isPopulated(person.getHomeProvince())) 
        {
            if (isPopulated(originalPerson.getHomeProvince())) 
            {
                if(!originalPerson.getHomeProvince().equalsIgnoreCase(person.getHomeProvince()))
                {
                    OldPersonInfo = OldPersonInfo + "homeProvince(" + originalPerson.getHomeProvince() + "),";
                
                    NewPersonInfo = NewPersonInfo + "homeProvince(" + person.getHomeProvince() + "),";
            
                    originalPerson.setHomeProvince(person.getHomeProvince());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "homeProvince(空值),";
                
                NewPersonInfo = NewPersonInfo + "homeProvince(" + person.getHomeProvince() + "),";
        
                originalPerson.setHomeProvince(person.getHomeProvince());
            }
        }
        
        //居住地所在地市
        if (isPopulated(person.getHomeCity())) 
        {
            if (isPopulated(originalPerson.getHomeCity())) 
            {
                if(!originalPerson.getHomeCity().equalsIgnoreCase(person.getHomeCity()))
                {
                    OldPersonInfo = OldPersonInfo + "homeCity(" + originalPerson.getHomeCity() + "),";
                
                    NewPersonInfo = NewPersonInfo + "homeCity(" + person.getHomeCity() + "),";
            
                    originalPerson.setHomeCity(person.getHomeCity());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "homeCity(空值),";
                
                NewPersonInfo = NewPersonInfo + "homeCity(" + person.getHomeCity() + "),";
        
                originalPerson.setHomeCity(person.getHomeCity());
            }
        }
        
        //居住地所在地区县
        if (isPopulated(person.getHomeCounty())) 
        {
            if (isPopulated(originalPerson.getHomeCounty())) 
            {
                if(!originalPerson.getHomeCounty().equalsIgnoreCase(person.getHomeCounty()))
                {
                    OldPersonInfo = OldPersonInfo + "homeCounty(" + originalPerson.getHomeCounty() + "),";
                
                    NewPersonInfo = NewPersonInfo + "homeCounty(" + person.getHomeCounty() + "),";
            
                    originalPerson.setHomeCounty(person.getHomeCounty());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "homeCounty(空值),";
                
                NewPersonInfo = NewPersonInfo + "homeCounty(" + person.getHomeCounty() + "),";
        
                originalPerson.setHomeCounty(person.getHomeCounty());
            }
        }
        
        //居住地所在地邮编
        if (isPopulated(person.getHomeZip())) 
        {
            if (isPopulated(originalPerson.getHomeZip())) 
            {
                if(!originalPerson.getHomeZip().equalsIgnoreCase(person.getHomeZip()))
                {
                    OldPersonInfo = OldPersonInfo + "homeZip(" + originalPerson.getHomeZip() + "),";
                
                    NewPersonInfo = NewPersonInfo + "homeZip(" + person.getHomeZip() + "),";
            
                    originalPerson.setHomeZip(person.getHomeZip());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "homeZip(空值),";
                
                NewPersonInfo = NewPersonInfo + "homeZip(" + person.getHomeZip() + "),";
        
                originalPerson.setHomeZip(person.getHomeZip());
            }
        }
        
        //居住地址
        if (isPopulated(person.getHomeAddress())) 
        {
            if (isPopulated(originalPerson.getHomeAddress())) 
            {
                if(!originalPerson.getHomeAddress().equalsIgnoreCase(person.getHomeAddress()))
                {
                    OldPersonInfo = OldPersonInfo + "homeAddress(" + originalPerson.getHomeAddress() + "),";
                
                    NewPersonInfo = NewPersonInfo + "homeAddress(" + person.getHomeAddress() + "),";
            
                    originalPerson.setHomeAddress(person.getHomeAddress());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "homeAddress(空值),";
                
                NewPersonInfo = NewPersonInfo + "homeAddress(" + person.getHomeAddress() + "),";
        
                originalPerson.setHomeAddress(person.getHomeAddress());
            }
        }
        
        //居住街道
        if (isPopulated(person.getHomeStreet())) 
        {
            if (isPopulated(originalPerson.getHomeStreet())) 
            {
                if(!originalPerson.getHomeStreet().equalsIgnoreCase(person.getHomeStreet()))
                {
                    OldPersonInfo = OldPersonInfo + "HomeStreet(" + originalPerson.getHomeStreet() + "),";
                
                    NewPersonInfo = NewPersonInfo + "HomeStreet(" + person.getHomeStreet() + "),";
            
                    originalPerson.setHomeStreet(person.getHomeStreet());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "HomeStreet(空值),";
                
                NewPersonInfo = NewPersonInfo + "HomeStreet(" + person.getHomeStreet() + "),";
        
                originalPerson.setHomeStreet(person.getHomeStreet());
            }
        }

        
        //户口所在地省
        if (isPopulated(person.getRegisteredProvince())) 
        {
            if (isPopulated(originalPerson.getRegisteredProvince())) 
            {
                if(!originalPerson.getRegisteredProvince().equalsIgnoreCase(person.getRegisteredProvince()))
                {
                    OldPersonInfo = OldPersonInfo + "registeredProvince(" + originalPerson.getRegisteredProvince() + "),";
                
                    NewPersonInfo = NewPersonInfo + "registeredProvince(" + person.getRegisteredProvince() + "),";
            
                    originalPerson.setRegisteredProvince(person.getRegisteredProvince());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "registeredProvince(空值),";
                
                NewPersonInfo = NewPersonInfo + "registeredProvince(" + person.getRegisteredProvince() + "),";
        
                originalPerson.setRegisteredProvince(person.getRegisteredProvince());
            }
        }
        
        //户口所在地市
        if (isPopulated(person.getRegisteredCity())) 
        {
            if (isPopulated(originalPerson.getRegisteredCity())) 
            {
                if(!originalPerson.getRegisteredCity().equalsIgnoreCase(person.getRegisteredCity()))
                {
                    OldPersonInfo = OldPersonInfo + "registeredCity(" + originalPerson.getRegisteredCity() + "),";
                
                    NewPersonInfo = NewPersonInfo + "registeredCity(" + person.getRegisteredCity() + "),";
            
                    originalPerson.setRegisteredCity(person.getRegisteredCity());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "registeredCity(空值),";
                
                NewPersonInfo = NewPersonInfo + "registeredCity(" + person.getRegisteredCity() + "),";
        
                originalPerson.setRegisteredCity(person.getRegisteredCity());
            }
        }
        
        //户口所在地区县
        if (isPopulated(person.getRegisteredCounty())) 
        {
            if (isPopulated(originalPerson.getRegisteredCounty())) 
            {
                if(!originalPerson.getRegisteredCounty().equalsIgnoreCase(person.getRegisteredCounty()))
                {
                    OldPersonInfo = OldPersonInfo + "registeredCounty(" + originalPerson.getRegisteredCounty() + "),";
                
                    NewPersonInfo = NewPersonInfo + "registeredCounty(" + person.getRegisteredCounty() + "),";
            
                    originalPerson.setRegisteredCounty(person.getRegisteredCounty());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "registeredCounty(空值),";
                
                NewPersonInfo = NewPersonInfo + "registeredCounty(" + person.getRegisteredCounty() + "),";
        
                originalPerson.setRegisteredCounty(person.getRegisteredCounty());
            }
        }
        
        //户口所在地邮编
        if (isPopulated(person.getRegisteredZip())) 
        {
            if (isPopulated(originalPerson.getRegisteredZip())) 
            {
                if(!originalPerson.getRegisteredZip().equalsIgnoreCase(person.getRegisteredZip()))
                {
                    OldPersonInfo = OldPersonInfo + "registeredZip(" + originalPerson.getRegisteredZip() + "),";
                
                    NewPersonInfo = NewPersonInfo + "registeredZip(" + person.getRegisteredZip() + "),";
            
                    originalPerson.setRegisteredZip(person.getRegisteredZip());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "registeredZip(空值),";
                
                NewPersonInfo = NewPersonInfo + "registeredZip(" + person.getRegisteredZip() + "),";
        
                originalPerson.setRegisteredZip(person.getRegisteredZip());
            }
        }
        
        //户口地址
        if (isPopulated(person.getRegisteredAddress())) 
        {
            if (isPopulated(originalPerson.getRegisteredAddress())) 
            {
                if(!originalPerson.getRegisteredAddress().equalsIgnoreCase(person.getRegisteredAddress()))
                {
                    OldPersonInfo = OldPersonInfo + "registeredAddress(" + originalPerson.getRegisteredAddress() + "),";
                
                    NewPersonInfo = NewPersonInfo + "registeredAddress(" + person.getRegisteredAddress() + "),";
            
                    originalPerson.setRegisteredAddress(person.getRegisteredAddress());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "registeredAddress(空值),";
                
                NewPersonInfo = NewPersonInfo + "registeredAddress(" + person.getRegisteredAddress() + "),";
        
                originalPerson.setRegisteredAddress(person.getRegisteredAddress());
            }
        }
        
        //户口街道
        if (isPopulated(person.getRegisteredStreet())) 
        {
            if (isPopulated(originalPerson.getRegisteredStreet())) 
            {
                if(!originalPerson.getRegisteredStreet().equalsIgnoreCase(person.getRegisteredStreet()))
                {
                    OldPersonInfo = OldPersonInfo + "RegisteredStreet(" + originalPerson.getRegisteredStreet() + "),";
                
                    NewPersonInfo = NewPersonInfo + "RegisteredStreet(" + person.getRegisteredStreet() + "),";
            
                    originalPerson.setRegisteredStreet(person.getRegisteredStreet());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "RegisteredStreet(空值),";
                
                NewPersonInfo = NewPersonInfo + "RegisteredStreet(" + person.getRegisteredStreet() + "),";
        
                originalPerson.setRegisteredStreet(person.getRegisteredStreet());
            }
        }
        
        //籍贯所在地省
        if (isPopulated(person.getNativeProvince())) 
        {
            if (isPopulated(originalPerson.getNativeProvince())) 
            {
                if(!originalPerson.getNativeProvince().equalsIgnoreCase(person.getNativeProvince()))
                {
                    OldPersonInfo = OldPersonInfo + "nativeProvince(" + originalPerson.getNativeProvince() + "),";
                
                    NewPersonInfo = NewPersonInfo + "nativeProvince(" + person.getNativeProvince() + "),";
            
                    originalPerson.setNativeProvince(person.getNativeProvince());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "nativeProvince(空值),";
                
                NewPersonInfo = NewPersonInfo + "nativeProvince(" + person.getNativeProvince() + "),";
        
                originalPerson.setNativeProvince(person.getNativeProvince());
            }
        }
        
        //籍贯所在地市
        if (isPopulated(person.getNativeCity())) 
        {
            if (isPopulated(originalPerson.getNativeCity())) 
            {
                if(!originalPerson.getNativeCity().equalsIgnoreCase(person.getNativeCity()))
                {
                    OldPersonInfo = OldPersonInfo + "nativeCity(" + originalPerson.getNativeCity() + "),";
                
                    NewPersonInfo = NewPersonInfo + "nativeCity(" + person.getNativeCity() + "),";
            
                    originalPerson.setNativeCity(person.getNativeCity());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "nativeCity(空值),";
                
                NewPersonInfo = NewPersonInfo + "nativeCity(" + person.getNativeCity() + "),";
        
                originalPerson.setNativeCity(person.getNativeProvince());
            }
        }
        
        //职业编码
        if (person.getProfessionTypeDict()!=null)
        {
            originalPerson.setProfessionTypeDict(person.getProfessionTypeDict());
        }
        
        //职业编码名称
        if (isPopulated(person.getProfessionName())) 
        {
            if (isPopulated(originalPerson.getProfessionName())) 
            {
                if(!originalPerson.getProfessionName().equalsIgnoreCase(person.getProfessionName()))
                {
                    OldPersonInfo = OldPersonInfo + "professionName(" + originalPerson.getProfessionName() + "),";
                
                    NewPersonInfo = NewPersonInfo + "professionName(" + person.getProfessionName() + "),";
            
                    originalPerson.setProfessionName(person.getProfessionName());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "professionName(空值),";
                
                NewPersonInfo = NewPersonInfo + "professionName(" + person.getProfessionName() + "),";
        
                originalPerson.setProfessionName(person.getProfessionName());
            }
        }
        
        //职业编码系统
        if (isPopulated(person.getProfessionDomain())) 
        {
            if (isPopulated(originalPerson.getProfessionDomain())) 
            {
                if(!originalPerson.getProfessionDomain().equalsIgnoreCase(person.getProfessionDomain()))
                {
                    OldPersonInfo = OldPersonInfo + "professionDomain(" + originalPerson.getProfessionDomain() + "),";
                
                    NewPersonInfo = NewPersonInfo + "professionDomain(" + person.getProfessionDomain() + "),";
            
                    originalPerson.setProfessionDomain(person.getProfessionDomain());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "professionDomain(空值),";
                
                NewPersonInfo = NewPersonInfo + "professionDomain(" + person.getProfessionDomain() + "),";
        
                originalPerson.setProfessionDomain(person.getProfessionDomain());
            }
        }
        
        //工作单位
        if (isPopulated(person.getCompany())) 
        {
            if (isPopulated(originalPerson.getCompany())) 
            {
                if(!originalPerson.getCompany().equalsIgnoreCase(person.getCompany()))
                {
                    OldPersonInfo = OldPersonInfo + "company(" + originalPerson.getCompany() + "),";
                
                    NewPersonInfo = NewPersonInfo + "company(" + person.getCompany() + "),";
            
                    originalPerson.setCompany(person.getCompany());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "company(空值),";
                
                NewPersonInfo = NewPersonInfo + "company(" + person.getCompany() + "),";
        
                originalPerson.setCompany(person.getCompany());
            }
        }
        
        //工作邮编
        if (isPopulated(person.getWorkZip())) 
        {
            if (isPopulated(originalPerson.getWorkZip())) 
            {
                if(!originalPerson.getWorkZip().equalsIgnoreCase(person.getWorkZip()))
                {
                    OldPersonInfo = OldPersonInfo + "workZip(" + originalPerson.getWorkZip() + "),";
                
                    NewPersonInfo = NewPersonInfo + "workZip(" + person.getWorkZip() + "),";
            
                    originalPerson.setWorkZip(person.getWorkZip());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "workZip(空值),";
                
                NewPersonInfo = NewPersonInfo + "workZip(" + person.getWorkZip() + "),";
        
                originalPerson.setWorkZip(person.getWorkZip());
            }
        }
        
        //单位地址
        if (isPopulated(person.getWorkAddress())) 
        {
            if (isPopulated(originalPerson.getWorkAddress())) 
            {
                if(!originalPerson.getWorkAddress().equalsIgnoreCase(person.getWorkAddress()))
                {
                    OldPersonInfo = OldPersonInfo + "workAddress(" + originalPerson.getWorkAddress() + "),";
                
                    NewPersonInfo = NewPersonInfo + "workAddress(" + person.getWorkAddress() + "),";
            
                    originalPerson.setWorkAddress(person.getWorkAddress());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "workAddress(空值),";
                
                NewPersonInfo = NewPersonInfo + "workAddress(" + person.getWorkAddress() + "),";
        
                originalPerson.setWorkAddress(person.getWorkAddress());
            }
        }
        
        //私人电话
        if (isPopulated(person.getPrivateNumber())) 
        {
            if (isPopulated(originalPerson.getPrivateNumber())) 
            {
                if(!originalPerson.getPrivateNumber().equalsIgnoreCase(person.getPrivateNumber()))
                {
                    OldPersonInfo = OldPersonInfo + "privateNumber(" + originalPerson.getPrivateNumber() + "),";
                
                    NewPersonInfo = NewPersonInfo + "privateNumber(" + person.getPrivateNumber() + "),";
            
                    originalPerson.setPrivateNumber(person.getPrivateNumber());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "privateNumber(空值),";
                
                NewPersonInfo = NewPersonInfo + "privateNumber(" + person.getPrivateNumber() + "),";
        
                originalPerson.setPrivateNumber(person.getPrivateNumber());
            }
        }
        
        //家庭电话   
        if (isPopulated(person.getHomeNumber())) 
        {
            if (isPopulated(originalPerson.getHomeNumber())) 
            {
                if(!originalPerson.getHomeNumber().equalsIgnoreCase(person.getHomeNumber()))
                {
                    OldPersonInfo = OldPersonInfo + "homeNumber(" + originalPerson.getHomeNumber() + "),";
                
                    NewPersonInfo = NewPersonInfo + "homeNumber(" + person.getHomeNumber() + "),";
            
                    originalPerson.setHomeNumber(person.getHomeNumber());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "homeNumber(空值),";
                
                NewPersonInfo = NewPersonInfo + "homeNumber(" + person.getHomeNumber() + "),";
        
                originalPerson.setHomeNumber(person.getHomeNumber());
            }
        }
        
        //工作电话
        if (isPopulated(person.getWorkNumber())) 
        {
            if (isPopulated(originalPerson.getWorkNumber())) 
            {
                if(!originalPerson.getWorkNumber().equalsIgnoreCase(person.getWorkNumber()))
                {
                    OldPersonInfo = OldPersonInfo + "workNumber(" + originalPerson.getWorkNumber() + "),";
                
                    NewPersonInfo = NewPersonInfo + "workNumber(" + person.getWorkNumber() + "),";
            
                    originalPerson.setWorkNumber(person.getWorkNumber());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "workNumber(空值),";
                
                NewPersonInfo = NewPersonInfo + "workNumber(" + person.getWorkNumber() + "),";
        
                originalPerson.setWorkNumber(person.getWorkNumber());
            }
        }
        
        //监护人
        if (isPopulated(person.getGuardianPerson())) 
        {
            if (isPopulated(originalPerson.getGuardianPerson())) 
            {
                if(!originalPerson.getGuardianPerson().equalsIgnoreCase(person.getGuardianPerson()))
                {
                    OldPersonInfo = OldPersonInfo + "guardianPerson(" + originalPerson.getGuardianPerson() + "),";
                
                    NewPersonInfo = NewPersonInfo + "guardianPerson(" + person.getGuardianPerson() + "),";
            
                    originalPerson.setGuardianPerson(person.getGuardianPerson());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "guardianPerson(空值),";
                
                NewPersonInfo = NewPersonInfo + "guardianPerson(" + person.getGuardianPerson() + "),";
        
                originalPerson.setGuardianPerson(person.getGuardianPerson());
            }
        }
        
        //保密级别
        if (isPopulated(person.getVip())) 
        {
            if (isPopulated(originalPerson.getVip())) 
            {
                if(!originalPerson.getVip().equalsIgnoreCase(person.getVip()))
                {
                    OldPersonInfo = OldPersonInfo + "guardianPerson(" + originalPerson.getVip() + "),";
                
                    NewPersonInfo = NewPersonInfo + "guardianPerson(" + person.getVip() + "),";
            
                    originalPerson.setVip(person.getVip());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "guardianPerson(空值),";
                
                NewPersonInfo = NewPersonInfo + "guardianPerson(" + person.getVip() + "),";
        
                originalPerson.setVip(person.getVip());
            }
        }
        
        //是否封帐
        if (isPopulated(person.getAccountLocked())) 
        {
            if (isPopulated(originalPerson.getAccountLocked())) 
            {
                if(!originalPerson.getAccountLocked().equalsIgnoreCase(person.getAccountLocked()))
                {
                    OldPersonInfo = OldPersonInfo + "accountLocked(" + originalPerson.getAccountLocked() + "),";
                
                    NewPersonInfo = NewPersonInfo + "accountLocked(" + person.getAccountLocked() + "),";
            
                    originalPerson.setAccountLocked(person.getAccountLocked());
                }
            }
            else
            {
                OldPersonInfo = OldPersonInfo + "accountLocked(空值),";
                
                NewPersonInfo = NewPersonInfo + "accountLocked(" + person.getAccountLocked() + "),";
        
                originalPerson.setAccountLocked(person.getAccountLocked());
            }
        }
        
//      //是否封帐日期
//      if(person.getAccountLockedDate()!=null)
//      {
//      if (isPopulated(person.getAccountLockedDate().toLocaleString())) 
//      {
//          if (isPopulated(originalPerson.getAccountLockedDate().toLocaleString())) 
//          {
//              if(!originalPerson.getAccountLockedDate().toLocaleString().equalsIgnoreCase(person.getAccountLockedDate().toLocaleString()))
//              {
//                  OldPersonInfo = OldPersonInfo + "accountLockedDate(" + originalPerson.getAccountLockedDate().toLocaleString() + "),";
//              
//                  NewPersonInfo = NewPersonInfo + "accountLockedDate(" + person.getAccountLockedDate().toLocaleString() + "),";
//          
//                  originalPerson.setAccountLockedDate(person.getAccountLockedDate());
//              }
//          }
//          else
//          {
//              OldPersonInfo = OldPersonInfo + "accountLockedDate(空值),";
//              
//              NewPersonInfo = NewPersonInfo + "accountLockedDate(" + person.getAccountLockedDate().toLocaleString() + "),";
//      
//              originalPerson.setAccountLockedDate(person.getAccountLockedDate());
//          }
//      }
//      }
        
//      //出生时间
//      if(person.getBirthTime()!=null)
//      {
//      if (isPopulated(person.getBirthTime().toLocaleString())) 
//      {
//          if (isPopulated(originalPerson.getBirthTime().toLocaleString())) 
//          {
//              if(!originalPerson.getBirthTime().toLocaleString().equalsIgnoreCase(person.getBirthTime().toLocaleString()))
//              {
//                  OldPersonInfo = OldPersonInfo + "BirthTime(" + originalPerson.getBirthTime().toLocaleString() + "),";
//              
//                  NewPersonInfo = NewPersonInfo + "BirthTime(" + person.getBirthTime().toLocaleString() + "),";
//          
//                  originalPerson.setBirthTime(person.getBirthTime());
//              }
//          }
//          else
//          {
//              OldPersonInfo = OldPersonInfo + "BirthTime(空值),";
//              
//              NewPersonInfo = NewPersonInfo + "BirthTime(" + person.getBirthTime().toLocaleString() + "),";
//      
//              originalPerson.setBirthTime(person.getBirthTime());
//          }
//      }
//      }
            
             //卡类型          
            if (isPopulated(person.getCardType())) 
            {
                if (isPopulated(originalPerson.getCardType())) 
                {
                    if(!originalPerson.getCardType().equalsIgnoreCase(person.getCardType()))
                    {
                        OldPersonInfo = OldPersonInfo + "CardType(" + originalPerson.getCardType() + "),";
                    
                        NewPersonInfo = NewPersonInfo + "CardType(" + person.getCardType() + "),";
                
                        originalPerson.setCardType(person.getCardType());
                    }
                }
                else
                {
                    OldPersonInfo = OldPersonInfo + "CardType(空值),";
                    
                    NewPersonInfo = NewPersonInfo + "CardType(" + person.getCardType() + "),";
            
                    originalPerson.setCardType(person.getCardType());
                }
            }
        
        
        
//      if(isPopulated(person.getCustom6())){
//          originalPerson.setCustom6(person.getCustom6());
//      }
//      
//      if(isPopulated(person.getCustom7())){
//          originalPerson.setCustom7(person.getCustom7());
//      }
//      
//      if(isPopulated(person.getCustom8())){
//          originalPerson.setCustom8(person.getCustom8());
//      }
//      
//      if(isPopulated(person.getCustom9())){
//          originalPerson.setCustom9(person.getCustom9());
//      }
//      
//      if(isPopulated(person.getCustom13())){
//          originalPerson.setCustom13(person.getCustom13());
//      }
//      
//      if(isPopulated(person.getCustom14())){
//          originalPerson.setCustom14(person.getCustom14());
//      }
//      
        if(isPopulated(person.getCustom15())){
            originalPerson.setCustom15(person.getCustom15());
        }
//
//      if(isPopulated(person.getCustom18())){
//          originalPerson.setCustom18(person.getCustom18());
//      }
//      
       if(isPopulated(person.getCustom19())){
           originalPerson.setCustom19(person.getCustom19());
       }
//      
//      if(isPopulated(person.getCustom20())){
//          originalPerson.setCustom20(person.getCustom20());
//      }
        
        PersonInfo.add(OldPersonInfo);
        
        PersonInfo.add(NewPersonInfo);
        
        return PersonInfo;
    }
    
    private static boolean isPopulated(String field) {
        if (field != null && field.length() > 0) {
            return true;
        }
        return false;
    }
    
    private static boolean isPopulated(java.util.Date field) {
        if (field != null && field.toString().length() > 0) {
            return true;
        }
        return false;
    }
}
