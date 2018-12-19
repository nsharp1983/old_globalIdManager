package com.ats.apixpdq.web.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ats.aexchange.datamodel.PatientIdentifier;

/**
 * 病人实体类
 * @author jiangmei
 *
 */
public class PixManagerBean implements Serializable {
	public static void main(String[] args) {
		System.out.println(new PixManagerBean().getClass().getResourceAsStream("/conf/aempi-extension-contexts.properties"));
		
		
	}

	private String systemid; 		//发布机构
	
	private String localid;  		//病人ID
	
	private String lname;    		//姓

	private String fname;			//名
	
	private String gender;			//性别	
	
	private String dob;				//生日
	
	private String pinyinCode;		//拼音码
	
	private String hospitalId;		//住院号	
	
	private String maritalStatus;	//婚姻	
	
	private String phone;			//手机号	
	
	private String homePhone;		//家庭电话	
	
	private String identityCard;	//身份证号码	
	
	private String insuranceCard;	//医保卡号码
	
	private String socialCard;		//社保号
	
	private String companyName;		//公司名称	
	
	private String companyPhone;	//公司电话
	
	private String companyEmail;	//公司邮箱	
	
	private String companyAdderss;	//公司地址
	
	private String relationName;	//联系人姓名
	
	private String relationShip;	//联系人关系
	
	private String relationPhone;	//联系人手机号码
	
	private String relationHomePhone;	//联系人家庭电话
	
	private String relationAddress;	//联系人地址
	
	private String citizenShip;		//国籍
	
	private String nativePlace;		//籍贯
	
	private String education;		//学历
	
	private String zip; 			//邮编
	
	private String country;			//国家
	
	private String state;			//省
	
	private String city;			//城市
	
	private String address;			//地址
	
	private String email;			//电子邮件
		
	private String atype; 			//标识操作(保存，查询等等)
		
	private Map<String,String> assigninglist =new HashMap<String,String>(); //发布机构
	
	private String fullAddress;  	//地址（组合）
	
	private List<PatientIdentifier> pidlist = new ArrayList<PatientIdentifier>();//病人列表



	private String domainId;//机构域id

	//patientVisit所需
	private String visistFlowIdAndDomain;//流水ID^流水域ID

	private String patReAdmission;//入院次数

	private String empi;

	private String nameString;




	//------360用到的
	private String patCuurentDep;//入院科室
	private String admitDate;//入院日期
	private String custom2;//就诊日期
	private String custom1;//就诊科室
	private String custom3;//出院诊断
	private String custom4;//住院日期
	private String custom5;//门诊日期
	private String outPatientStatusA;//出院诊断
	private String diagnoseName;//诊断名称
	private String ynsee;//门诊诊断名称
	private String patForTempBed;//住院科室
	private String patDischargeLocation;//出院科室


	public String getCustom3() {
		return custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	public String getCustom4() {
		return custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	public String getCustom5() {
		return custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	public String getOutPatientStatusA() {
		return outPatientStatusA;
	}

	public void setOutPatientStatusA(String outPatientStatusA) {
		this.outPatientStatusA = outPatientStatusA;
	}

	public String getDiagnoseName() {
		return diagnoseName;
	}

	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}

	public String getYnsee() {
		return ynsee;
	}

	public void setYnsee(String ynsee) {
		this.ynsee = ynsee;
	}

	public String getPatForTempBed() {
		return patForTempBed;
	}

	public void setPatForTempBed(String patForTempBed) {
		this.patForTempBed = patForTempBed;
	}

	public String getPatDischargeLocation() {
		return patDischargeLocation;
	}

	public void setPatDischargeLocation(String patDischargeLocation) {
		this.patDischargeLocation = patDischargeLocation;
	}

	public String getPatCuurentDep() {
		return patCuurentDep;
	}

	public void setPatCuurentDep(String patCuurentDep) {
		this.patCuurentDep = patCuurentDep;
	}

	public String getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}

	public String getCustom2() {
		return custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	public String getCustom1() {
		return custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getVisistFlowIdAndDomain() {
		return visistFlowIdAndDomain;
	}

	public void setVisistFlowIdAndDomain(String visistFlowIdAndDomain) {
		this.visistFlowIdAndDomain = visistFlowIdAndDomain;
	}

	public String getPatReAdmission() {
		return patReAdmission;
	}

	public void setPatReAdmission(String patReAdmission) {
		this.patReAdmission = patReAdmission;
	}

	public String getEmpi() {
		return empi;
	}

	public void setEmpi(String empi) {
		this.empi = empi;
	}

	/**
	 * 转换出生日期的显示方式
	 * @param
	 * @return
	 */
	public String time(){
		String d=this.dob;
		if(d!=null&&!"".equals(d)){
			return d.substring(d.lastIndexOf('/')+1, d.length())+'-'+d.substring(d.indexOf('/')+1, d.lastIndexOf('/'))+'-'+d.substring(0,d.indexOf('/'));
		}
		return "";
	}


	public String getSystemid() {
		return systemid;
	}

	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}

	public String getLocalid() {
		return localid;
	}

	public String getPinyinCode() {
		return pinyinCode;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public void setLocalid(String localid) {
		this.localid = localid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getInsuranceCard() {
		return insuranceCard;
	}

	public void setInsuranceCard(String insuranceCard) {
		this.insuranceCard = insuranceCard;
	}

	public String getSocialCard() {
		return socialCard;
	}

	public void setSocialCard(String socialCard) {
		this.socialCard = socialCard;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyAdderss() {
		return companyAdderss;
	}

	public void setCompanyAdderss(String companyAdderss) {
		this.companyAdderss = companyAdderss;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}

	public String getRelationPhone() {
		return relationPhone;
	}

	public void setRelationPhone(String relationPhone) {
		this.relationPhone = relationPhone;
	}

	public String getRelationHomePhone() {
		return relationHomePhone;
	}

	public void setRelationHomePhone(String relationHomePhone) {
		this.relationHomePhone = relationHomePhone;
	}


	public String getRelationAddress() {
		return relationAddress;
	}

	public void setRelationAddress(String relationAddress) {
		this.relationAddress = relationAddress;
	}

	public String getCitizenShip() {
		return citizenShip;
	}

	public void setCitizenShip(String citizenShip) {
		this.citizenShip = citizenShip;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public Map<String, String> getAssigninglist() {
		return assigninglist;
	}

	public void setAssigninglist(Map<String, String> assigninglist) {
		this.assigninglist = assigninglist;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public List<PatientIdentifier> getPidlist() {
		return pidlist;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public void setPidlist(List<PatientIdentifier> pidlist) {
		this.pidlist = pidlist;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}


}
