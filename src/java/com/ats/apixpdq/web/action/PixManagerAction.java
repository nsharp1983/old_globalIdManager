package com.ats.apixpdq.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;


import com.ats.aempi.model.MaritalStatusDict;
import com.ats.aempi.model.NationalityDict;
import com.ats.aempi.model.RelationshipTypeDict;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IActorDescription;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.datamodel.Address;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.datamodel.PersonName;
import com.ats.aexchange.datamodel.SharedEnums;
import com.ats.apixpdq.api.IPdSupplierAdapter;
import com.ats.apixpdq.api.IPixManagerAdapter;
import com.ats.apixpdq.api.PdSupplierException;
import com.ats.apixpdq.api.PdqQuery;
import com.ats.apixpdq.api.PdqResult;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.web.service.IContactpersonService;
import com.ats.apixpdq.web.service.IDictionaryService;
import com.ats.apixpdq.web.servlet.PixPdqConfigurationLoader;
import com.ats.apixpdq.web.beans.PixManagerBean;

public class PixManagerAction  implements ServletRequestAware,ServletResponseAware {
	// 序列化
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(PixManagerAction.class);
	private PdqResult PdqResultEdit=null;
	
	public String execute() throws Exception {
		return "success";
	}

	// 病人注册
	public String Regist() throws Exception {
		connection.getIdentifier("ReceivingFacility");
		Map<String, String> assignList = new HashMap<String, String>();
		IPixManagerAdapter pixAdapter = null;
		IPdSupplierAdapter pdsupp = null;
		List<PixManagerBean> beanList = null;
		PdqQuery query = null;
		Patient patient = null;
		MessageHeader header = null;
		String ip = null;

		try {

			loader = PixPdqConfigurationLoader.getInstance();
			Collection<IActorDescription> actors = loader.getActorDescriptions();
			IActorDescription actor = null;
			for (IActorDescription actorDescription : actors) {
				if (actorDescription.getType().equalsIgnoreCase("PixManager")) {
					actor = actorDescription;
					break;
				}
			}

			pixAdapter = PixPdqFactory.getPixManagerAdapter();
//			pdsupp = (IPdSupplierAdapter) PixPdqFactory.getPdSupplierAdapter();
			if (actor != null) {

				// TODO: check type name
				connection = actor.getConnectionDescriptionByType("Server");
			
				Set<Identifier> defaultDomains = Configuration.getAllDomains(actor);
				ids = pixAdapter.getDomainIdentifiers(defaultDomains);//所有机构类型
				if (ids != null) {
					for (Identifier id : ids) {
						assignList.put(id.getNamespaceId(), id.getNamespaceId());
					}
					InetAddress addr = InetAddress.getLocalHost();
					ip = addr.getHostAddress();
					request.setAttribute("serverport", ip);
				} else {
					ip = "Domain没有配置";
					request.setAttribute("serverport", ip);
				}
			} else {
				ip = "PIX Manager没有配置";
				request.setAttribute("serverport", ip);
			}
			
			List<MaritalStatusDict> maritalStatusDicts=dictionaryService.findMaritalStatusDicts();	//婚姻关系字典
			List<NationalityDict> nationalityDicts=dictionaryService.findNationalityDicts();//国籍字典
			List<RelationshipTypeDict> relationshipTypeDicts=dictionaryService.findRelationshipTypeDicts();//关系字典
			request.setAttribute("maritalList", maritalStatusDicts);
			request.setAttribute("nationalityList", nationalityDicts);
			request.setAttribute("relationshipList", relationshipTypeDicts);


			PixManagerBean pixManagerBean=new PixManagerBean();
			pixManagerBean.setPidlist(pidlist);
			pixManagerBean.setAssigninglist(assignList);

//用户信息为空
			if (pm == null || pm.getAtype() == null || !"Save".equals(pm.getAtype())) {
				beanList = new ArrayList<PixManagerBean>();
				request.setAttribute("beanList", beanList);
				request.setAttribute("PixManagerBean", pixManagerBean);
				pm=null;
				return "regist";
			}


//设置国家地区
			if (pm.getAtype().equalsIgnoreCase("Save")) {
				if(pm.getCountry()==null?false:"国家".equals(pm.getCountry().trim())){
					pm.setCountry(null);
				}
				if(pm.getState()==null?false:"省份、州".equals(pm.getState().trim())){
					pm.setState(null);
				}
				if(pm.getCity()==null?false:"地级市、县".equals(pm.getCity().trim())){
					pm.setCity(null);
				}



				try {
//转换需要类型并保存
					patient = toPatient(pm);
					header = getHeader(pm);
					header.setMessageCode("ADT^A04");
					pixAdapter.createPatient(patient, header);

					request.setAttribute("PixManagerBean", pixManagerBean);
					request.setAttribute("RegistMsg", "病人注册成功！！");
					
				} catch (PixManagerException e) {
					e.printStackTrace();
					log.error("Exception creating patient" + e.getMessage(), e);
					request.setAttribute("RegistMsg", "病人注册失败！！");
					request.setAttribute("PixManagerBean", pixManagerBean);
				}
			}
		} catch (Exception e) {
			log.error("Exception" + e.getMessage(), e);
			System.out.println(e.getMessage());
			request.setAttribute("beanList", new ArrayList<PixManagerBean>());
			request.setAttribute("PixManagerBean", new PixManagerBean());
		}
		
		return "regist";
	}
	
	/**
	 * PIX查询
	 * @return
	 * @throws Exception
	 */
	public String PixQuery() throws Exception {
		Map<String, String> assignList = new HashMap<String, String>();
		IPixManagerAdapter pixAdapter = null;
		IPdSupplierAdapter pdsupp = null;
		List<PatientIdentifier> beanList = null;
		PdqQuery query = null;
		Patient patient = null;
		MessageHeader header = null;
		String ip = null;

		try {
			loader = PixPdqConfigurationLoader.getInstance();
			
			Collection<IActorDescription> actors = loader.getActorDescriptions();
			IActorDescription actor = null;
			for (IActorDescription actorDescription : actors) {
				if (actorDescription.getType().equalsIgnoreCase("PixManager")) {
					actor = actorDescription;
					break;
				}
			}
			pixAdapter = (IPixManagerAdapter) PixPdqFactory.getPixManagerAdapter();
			pdsupp = (IPdSupplierAdapter) PixPdqFactory.getPdSupplierAdapter();
			if (actor != null) {
				connection = actor.getConnectionDescriptionByType("Server");

				/*
				 * String pixManagerAdapterClass =
				 * connection.getProperty("pixManagerAdapter"); Class cpix =
				 * Class.forName(pixManagerAdapterClass); pixAdapter =
				 * (IPixManagerAdapter) cpix.newInstance();
				 */
				Set<Identifier> defaultDomains = Configuration.getAllDomains(actor);
				ids = pixAdapter.getDomainIdentifiers(defaultDomains);
				if (ids != null) {
					for (Identifier id : ids) {
						assignList.put(id.getNamespaceId(), id.getNamespaceId());
					}
					InetAddress addr = InetAddress.getLocalHost();
					ip = addr.getHostAddress();
					request.setAttribute("serverport", ip);
				} else {
					ip = "No PIX Manager actor is configured";
					request.setAttribute("serverport", ip);
				}
			} else {
				ip = "No PIX Manager actor is configured";
				request.setAttribute("serverport", ip);
			}

			PixManagerBean pixManagerBean=new PixManagerBean();
			pixManagerBean.setPidlist(beanList);
			pixManagerBean.setAssigninglist(assignList);
			if ((pm == null || pm.getAtype() == null || !"PixQuery".equals(pm.getAtype()))) {
				beanList = new ArrayList<PatientIdentifier>();
				request.setAttribute("beanList", beanList);
				request.setAttribute("PixManagerBean", pixManagerBean);
				pm=null;
				return "pixQuery";
			} else if (pm.getAtype().equalsIgnoreCase("PixQuery")) {
				try {
					if(pm.getLocalid()==null?false:"".equals(pm.getLocalid().trim())){
						pm.setLocalid(null);
					}
//					pm.setPidlist(pidlist);
					pm.setAssigninglist(assignList);
					for (IActorDescription actorDescription : actors) {
						if (actorDescription.getType().equalsIgnoreCase("PixManager")) {
							actor = actorDescription;
							break;
						}
					}
					// TODO: check type name
					connection = actor.getConnectionDescriptionByType("Server");

					pid = getPatientID(pm);
					query = toQuery(pm);
					header = getHeader(pm);
					beanList = pixAdapter.findPatientIds(pid, header);

					
					// PdqResult result = pdsupp.findPatients(query, header);
					if (beanList == null) {
						beanList = new ArrayList<PatientIdentifier>();
					}
					request.getSession().setAttribute("beans",beanList );
					request.setAttribute("beanList", findPageResult(beanList));
					request.setAttribute("PixManagerBean", pixManagerBean);
					
					//return "success";
				} catch (Exception e) {
					log.error("Error find patients" + e.getMessage(), e);
					request.setAttribute("PixManagerBean", pixManagerBean);
					request.setAttribute("beanList",new ArrayList<PatientIdentifier>());
				}

			}
		} catch (Exception e) {			
			request.setAttribute("beanList", new ArrayList<PixManagerBean>());
			request.setAttribute("PixManagerBean", new PixManagerBean());
			return "pixQuery";
		}
		return "pixQuery";

	}

	//修改病人信息
	public String updatePation() throws Exception
	{	
		IPixManagerAdapter pixAdapter = null;
		
		Patient patient = null;
		
		MessageHeader header = null;

		try {

			pixAdapter = (IPixManagerAdapter) PixPdqFactory.getPixManagerAdapter();

			PixManagerAction pixManagerBean=new PixManagerAction();
			
			if (pm.getAtype().equalsIgnoreCase("Update")) 
			{
				try 
				{
					pm.setLocalid(pm.getHospitalId());
					
					patient = toPatient(pm);

					header = getHeader(pm);
					
					header.setMessageCode("ADT^A08");
					
					pixAdapter.updatePatient(patient, header);
							
					return "success";
					
				} catch (PixManagerException e) 
				{
					log.error("Exception creating patient" + e.getMessage(), e);
					
					request.setAttribute("PixManagerBean", pixManagerBean);
				
				}
			}else 
			{
				pm=null;
				
				request.setAttribute("PixManagerBean", pixManagerBean);
				
				return "success";
			}
		} catch (Exception e)
		{
			log.error("Exception" + e.getMessage(), e);
			
			request.setAttribute("PixManagerBean", new PixManagerBean());
		
			return "success";
		}

		return "success";
	}
	
	
	
	/**
	 * PDQ查看病人信息
	 * @return
	 * @throws Exception
	 */
	public String patientInfo() throws Exception
	{		
		@SuppressWarnings("unused")
		List<PixManagerBean> beanList = null;
		
		try 
		{		
			try 
			{				
				beanList = toBean(PdqResultEdit);
			
			
				List<PixManagerBean> beans=(List<PixManagerBean>) request.getSession().getAttribute("beans" );
				
				if(beans!=null&&beans.size()>personIndex)
				{
					request.setAttribute("patient",beans.get(personIndex));
				}else 
				{
					PrintWriter out = response.getWriter();
					
					out.print("<script type='text/javascript'>");
					
					out.print("window.alert('获取该用户信息失败，请重试！！');");
					
					out.print("window.history.back();");
					
					out.print("</script>");
					
					return null;				
				}
				
				//判断是查询，还是修改病人信息
				if(pm.getAtype().equalsIgnoreCase("select"))
				{
					return "patientInfo";
				}else 
				{
					return "update";
				}
			} catch (Exception e) 
			{				
				log.error("Error find patients" + e.getMessage(), e);
			}
		} catch (Exception e) 
		{
			log.error("Exception" + e.getMessage(), e);
			
			return "pdqQuery";
		}
		return "patientInfo";
	}
	
	// PDQ查询
	public String PdqQuery() throws Exception {
		if(request.getSession().getAttribute("beans")!=null&&isPage!=null&&!"0".equals(isPage)){
			request.setAttribute("beanList", findPageResult((List<PixManagerBean>)request.getSession().getAttribute("beans")));
			return "pdqQuery";
		}
		Map<String, String> assignList = new HashMap<String, String>();
		IPixManagerAdapter pixAdapter = null;
		IPdSupplierAdapter pdsupp = null;
		List<PixManagerBean> beanList = null;
		PdqQuery query = null;
		Patient patient = null;
		MessageHeader header = null;
		String ip = null;


		//卡号类型
		String cardType=request.getParameter("cardType")==null?"":request.getParameter("cardType");
		String cardNo=request.getParameter("cardNo")==null?"":request.getParameter("cardNo");
		if("0".equals(cardType)){
			pm.setInsuranceCard(cardNo);
		}else if("1".equals(cardType)){
			pm.setSocialCard(cardNo);
		}
		//证件类型
		String identitrType=request.getParameter("identitrType")==null?"":request.getParameter("identitrType");
		String identityCard=request.getParameter("identityCard")==null?"":request.getParameter("identityCard");
		if("0".equals(identitrType)){
			pm.setIdentityCard(identityCard);
		}

		
		try {
			loader = PixPdqConfigurationLoader.getInstance();
			Collection<IActorDescription> actors = loader.getActorDescriptions();
			IActorDescription actor = null;
			for (IActorDescription actorDescription : actors) {
				if (actorDescription.getType().equalsIgnoreCase("PdSupplier")) {
					actor = actorDescription;
					break;
				}
			}
			pixAdapter = (IPixManagerAdapter) PixPdqFactory.getPixManagerAdapter();
			pdsupp = (IPdSupplierAdapter) PixPdqFactory.getPdSupplierAdapter();
			
			if (actor != null) 
			{
				connection = actor.getConnectionDescriptionByType("Server");

				Set<Identifier> defaultDomains = Configuration.getAllDomains(actor);
				
				ids = pixAdapter.getDomainIdentifiers(defaultDomains);
				if (ids != null) {
					for (Identifier id : ids) {
						assignList.put(id.getNamespaceId(), id.getNamespaceId());
					}
					InetAddress addr = InetAddress.getLocalHost();
					ip = addr.getHostAddress();
					request.setAttribute("serverport", ip);
				} else {
					ip = "No PIX Manager actor is configured";
					request.setAttribute("serverport", ip);
				}
			} else {
				ip = "No PIX Manager actor is configured";
				request.setAttribute("serverport", ip);
			}
			
			PixManagerBean pixManagerBean=new PixManagerBean();
			pixManagerBean.setPidlist(pidlist);
			pixManagerBean.setAssigninglist(assignList);
			
			if ((pm == null || pm.getAtype() == null || !"PdqQuery".equals(pm.getAtype()))) {
				beanList = new ArrayList<PixManagerBean>();
				request.setAttribute("beanList", beanList);
				request.setAttribute("PixManagerBean", pixManagerBean);	
				return "pdqQuery";
			} else if (pm.getAtype().equalsIgnoreCase("PdqQuery")) {
				try {
					for (IActorDescription actorDescription : actors) {
						if (actorDescription.getType().equalsIgnoreCase(
								"PdSupplier")) {
							actor = actorDescription;
							break;
						}
					}
					connection = actor.getConnectionDescriptionByType("Server");
					if(pm.getNameString()==null?false:"".equals(pm.getNameString().trim())){
						pm.setNameString(null);
					}
					if(pm.getPinyinCode()==null?false:"".equals(pm.getPinyinCode().trim())){
						pm.setPinyinCode(null);
					}
					if(pm.getLname()==null?false:"".equals(pm.getLname().trim())){
						pm.setLname(null);
					}
					if(pm.getFname()==null?false:"".equals(pm.getFname().trim())){
						pm.setFname(null);
					}
					if(pm.getHospitalId()==null?false:"".equals(pm.getHospitalId().trim())){
						pm.setHospitalId(null);
					}
					if(pm.getInsuranceCard()==null?false:"".equals(pm.getInsuranceCard().trim())){
						pm.setInsuranceCard(null);
					}
					
					if(pm.getIdentityCard()==null?false:"".equals(pm.getIdentityCard().trim())){
						pm.setIdentityCard(null);
					}
					
					if(pm.getSocialCard()==null?false:"".equals(pm.getSocialCard().trim())){
						pm.setSocialCard(null);
					}
					
					if(pm.getAddress()==null?false:"".equals(pm.getAddress().trim())){
						pm.setAddress(null);
					}
					if(pm.getPhone()==null?false:"".equals(pm.getPhone().trim())){
						pm.setPhone(null);
					}
					if(pm.getDob()==null?false:"".equals(pm.getDob().trim())){
						pm.setDob(null);
					}
					if(pm.getGender()==null?false:"".equals(pm.getGender().trim())){
						pm.setGender(null);
					}

					query = toQuery(pm);
					
					header = getHeader(pm);
					
					if("PdqQuery".equals(pm.getAtype()))
					{
						header.setTriggerEvent("Q22");
					}
					
					PdqResult result = pdsupp.findPatients(query, header);
					
					PdqResultEdit=null;
					
					PdqResultEdit=result;
					
					
					if (result != null) 
					{
						beanList = toBean(result);
					} 
					else 
					{
						beanList = new ArrayList<PixManagerBean>();
					}
					
					if(beanList.size()<=0)
					{
						beanList=new ArrayList<PixManagerBean>();
					}
					
					if(beanList!=null)
					{
						request.getSession().setAttribute("beans",beanList );
						
						request.setAttribute("beanList", findPageResult(beanList));
					}
					
					request.setAttribute("PixManagerBean", pixManagerBean);	
					
					return "pdqQuery";
					
				} catch (PdSupplierException e) 
				{
					log.error("Error find patients" + e.getMessage(), e);
					
					request.setAttribute("beanList",new ArrayList<PixManagerBean>());
					
					request.setAttribute("PixManagerBean", pixManagerBean);	
				}

			}
		} catch (Exception e) 
		{
			log.error("Exception" + e.getMessage(), e);
			
			request.setAttribute("beanList", new ArrayList<PixManagerBean>());
			
			request.setAttribute("PixManagerBean", new PixManagerBean());
			
			return "pdqQuery";
		}
		
		return "pdqQuery";
	}
	
	/**
	 * The patient paging results
	 * 
	 * @return
	 */
	private List findPageResult(List beanList) {
		
		
		List resuList = new ArrayList();
		pageIndex = 1;
		if (request.getParameter("pageIndex") != null
				&& request.getParameter("pageIndex") != "") {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		// 得到结果集记录数
		int sum = beanList.size();
		// 得到有多少页
		count = sum % pageSize == 0 ? sum / pageSize : sum / pageSize + 1;
		if (pageIndex > count) {
			pageIndex = count;
		}
		if (pageIndex <= 0) {
			pageIndex = 1;
		}

		for (int i = (pageIndex - 1) * pageSize; i < pageIndex * pageSize; i++) {
			if (i < sum&&i<beanList.size()) {
				resuList.add(beanList.get(i));
			}
		}
		return resuList;
	}
	
	

	/**
	 * Gives Complete Name of the patient as String.
	 * 
	 * @param lname
	 *            the <code>LastName</code>
	 * @param fname
	 *            the <code>FirstName</code>
	 * @return String
	 */
	private String getName(String lname, String fname) {
		if (lname == null && fname == null) {
			return null;
		}
		String ret = "";
		if (lname != null) {
			ret = lname + " ";
		}
		if (fname != null) {
			ret = ret + fname;
		}
		return ret;
	}

	/**
	 * Gives Complete Address for the patient as String.
	 * 
	 * @param addresslist
	 *            List of <code>Address</code>
	 * @return String.
	 */
	private String getAddressString(List<Address> addresslist) {
		Address address = addresslist.get(0);
		StringBuffer addString = new StringBuffer();
		if (address != null) {
				
//			if (address.getAddCountry() != null
//					&& address.getAddCountry().equalsIgnoreCase("") != true) {
//				addString.append(address.getAddCountry()).append(",");
//			}
//			if (address.getAddState() != null
//					&& address.getAddState().equalsIgnoreCase("") != true) {
//				addString.append(address.getAddState()).append(",");
//			}
//			if (address.getAddCity() != null
//					&& address.getAddCity().equalsIgnoreCase("") != true) {
//				addString.append(address.getAddCity()).append(",");
//			}
//			if (address.getAddLine2() != null
//					&& address.getAddLine2().equalsIgnoreCase("") != true) {
//				addString.append(address.getAddLine2()).append(",");
//			}
			if (address.getAddLine1() != null
					&& address.getAddLine1().equalsIgnoreCase("") != true) {
				addString.append(address.getAddLine1());
			}
//			if (address.getAddZip() != null
//					&& address.getAddZip().equalsIgnoreCase("") != true) {
//				addString.append(address.getAddZip());
//			}
		}
		return addString.toString();
	}

	/**
	 * Converts <code>PixManagerBean</code> to the <code>PdqQuery</code>
	 * 
	 * @param bean
	 *            the <code>PixManagerBean</code>
	 * @return <code>PdqQuery</code>
	 */
	private PdqQuery toQuery(PixManagerBean bean) 
	{
		PdqQuery query = new PdqQuery();
		
		query.setHospitalID(bean.getHospitalId());//住院号pm-2012-07-03
		
		query.setIdentityNO(bean.getIdentityCard()); //身份证件号 pm-2012-07-03
		
		query.setInsuranceNO(bean.getInsuranceCard()); //医保号,pm-2012-07-03
		
		query.setSocailCardNO(bean.getSocialCard()); //社保号,pm-2012-07-03
		
		query.setNamePY(bean.getPinyinCode()); //拼音,pm-2012-07-03
		
		query.setPatientPhone(bean.getPhone()); //联系电话
		
		query.setAddress(getAddress(bean));
		
		query.setBirthDate(_convertStringToCalendar(bean.getDob()));

		PatientIdentifier pIdentifier = getPatientID(bean);
		
		query.setIdenDomainID((pIdentifier.getAssigningAuthority().getUniversalId()));//机构号

		query.setPersonName(getPersonName(bean.getLname(), bean.getFname()));
		
		return query;
	}

	/**
	 * Converts <code>PixManagerBean</code> to the <code>Patient</code>
	 * 
	 * @param bean
	 *            the <code>PixManagerBean</code>
	 * @return <code>Patient</code>
	 */
	private Patient toPatient(PixManagerBean bean) 
	{
		Patient patient = new Patient();
		
		if(bean.getPhone()!=null)
		{
			patient.setTelPhone(bean.getPhone());
		}
		
		if(bean.getIdentityCard()!=null)
		{
			patient.setIdentityNO(bean.getIdentityCard());
		}
		
		if(bean.getInsuranceCard()!=null)
		{
			patient.setInsuranceNO(bean.getInsuranceCard());
		}
		
		if(bean.getSocialCard()!=null)
		{
			patient.setSocialcardNO(bean.getSocialCard());
		}
		
		patient.setPatientName(getPersonName(bean.getLname(), bean.getNameString()));
		
		if (bean.getGender() != null) 
		{
			if (bean.getGender().equalsIgnoreCase("male")|| bean.getGender().equalsIgnoreCase("M")) 
			{
				patient.setAdministrativeSex(SharedEnums.SexType.MALE);
			} else if (bean.getGender().equalsIgnoreCase("female")|| bean.getGender().equalsIgnoreCase("F")) 
			{
				patient.setAdministrativeSex(SharedEnums.SexType.FEMALE);
			}
		}
		
		patient.setBirthDateTime(_convertStringToCalendar(bean.getDob()));
		
		List<Address> addList = new ArrayList<Address>();
		
		Address address = getAddress(bean);
		
		if (address != null)
			addList.add(address);
		
		patient.setAddresses(addList);
		
		List<PatientIdentifier> pids = new ArrayList<PatientIdentifier>();
		
		pids.add(getPatientID(bean));

		patient.setPatientIds(pids);
		
		patient.setEmail(pm.getEmail());
		
		return patient;
	}

	
	
	/**
	 * Converts <code>PdqResult</code> to the list of
	 * <code>PixManagerBean</code>
	 * 
	 * @param pdqresult
	 *            the <code>PdqResult</code>
	 * @return the list of <code>PixManagerBean</code>
	 */
	private List<PixManagerBean> toBean(PdqResult result) 
	{
		List<PixManagerBean> beanList = new ArrayList<PixManagerBean>();
			
		for (List<Patient> patientlist : result.getPatients()) 
		{
			for (Patient patient : patientlist) 
			{
				PixManagerBean bean = new PixManagerBean();
				
				
				
				if(patient.getPatientIds().size()>0)
				{
					bean.setSystemid(patient.getPatientIds().get(patient.getPatientIds().size()-1).getAssigningAuthority().getNamespaceId());
					
					bean.setLocalid(patient.getPatientIds().get(patient.getPatientIds().size()-1).getId());
					
					bean.setHospitalId(patient.getPatientIds().get(patient.getPatientIds().size()-1).getId());
				}
				
				
				if(patient.getPatientName()!=null)
				{
					bean.setFname(patient.getPatientName().getLastName());
				}
												
				if (patient.getPatientName() != null) 
				{
					bean.setNameString(getName(patient.getPatientName().getLastName(), patient.getPatientName().getFirstName()));
				}
				
				if (patient.getAdministrativeSex() != null) 
				{
					bean.setGender(patient.getAdministrativeSex().equals(SharedEnums.SexType.MALE) ? "男" : "女");
				}
				
				//System.out.println(patient.getMaritalStatus());
				
				//System.out.println(SharedEnums.MartitalStatusType.UNMARRIED.getHL7Value());
				
				if(patient.getMaritalStatus()!=null)
				{
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.SEPARATED.getHL7Value()))
					{
						bean.setMaritalStatus("分居");
					}				
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.DIVORCED.getHL7Value()))
					{
						bean.setMaritalStatus("离婚");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.MARRIED.getHL7Value()))
					{
						bean.setMaritalStatus("已婚");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.SINGLE.getHL7Value()))
					{
						bean.setMaritalStatus("独身");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.WIDOWED.getHL7Value()))
					{
						bean.setMaritalStatus("丧偶");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.COMMON_LAW.getHL7Value()))
					{
						bean.setMaritalStatus("普通法律");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.LIVING_TOGETHER.getHL7Value()))
					{
						bean.setMaritalStatus("同住");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.DOMESTIC_PARTNER.getHL7Value()))
					{
						bean.setMaritalStatus("同居");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.REGISTERED_DOMESTIC_PARTNER.getHL7Value()))
					{
						bean.setMaritalStatus("已登记的同居伴侣");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.LEGALLY_SEPARATED.getHL7Value()))
					{
						bean.setMaritalStatus("合法分居");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.ANNULLED.getHL7Value()))
					{
						bean.setMaritalStatus("废止");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.INTERLOCUTORY.getHL7Value()))
					{
						bean.setMaritalStatus("中间");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.UNMARRIED.getHL7Value()))
					{
						bean.setMaritalStatus("未婚");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.UNKNOWN.getHL7Value()))
					{
						bean.setMaritalStatus("未知");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.OTHER.getHL7Value()))
					{
						bean.setMaritalStatus("其他");
					}
					if(patient.getMaritalStatus().equals(SharedEnums.MartitalStatusType.UNREPORTED.getHL7Value()))
					{
						bean.setMaritalStatus("未报告");
					}
					
				}
				
				if(patient.getPhoneNumbers().get(0)!=null)
				{
					bean.setPhone(patient.getPhoneNumbers().get(0).getNumber());					
				}				
				
				if(patient.getIdentityNO()!=null)
				{
					bean.setIdentityCard(patient.getIdentityNO());
				}
				
				if(patient.getInsuranceNO()!=null)
				{
					bean.setInsuranceCard(patient.getInsuranceNO());
				}
				
				if(patient.getSocialcardNO()!=null)
				{
					bean.setSocialCard(patient.getSocialcardNO());
				}
				
				if(patient.getEmail()!=null)
				{
					bean.setEmail(patient.getEmail() != null ? patient.getEmail(): "");
				}
				

				//System.out.println(patient.getAddresses());
				
				if (patient.getAddresses() != null) 
				{
					bean.setFullAddress(getAddressString(patient.getAddresses()));
					
					bean.setAddress(getAddressString(patient.getAddresses()));
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				if (patient.getBirthDateTime() != null) 
				{
					bean.setDob(sdf.format(patient.getBirthDateTime().getTime()));
				}
				
				bean.setPidlist(patient.getPatientIds());
				
				beanList.add(bean);
			}
		}
		return beanList;
	}

	/**
	 * Gets <code>PersonName</code>
	 * 
	 * @param lastName
	 *            the <code>LastName</code> of the patient
	 * @param firstName
	 *            the <code>FirstName</code> of the patinet
	 * @return <code>PersonName</code>
	 */
	private PersonName getPersonName(String lastName, String firstName) {
		if ((lastName == null || lastName.equalsIgnoreCase(""))
				&& (firstName == null || firstName.equalsIgnoreCase(""))) {
			return null;
		}
		PersonName pname = new PersonName();
		pname.setLastName(lastName);
		pname.setFirstName(firstName);

		return pname;
	}

	/**
	 * Gets <code>Address</code> form <code>PixManagerBean</code>
	 * 
	 * @param bean
	 *            the <code>PixManagerBean</code> where to get
	 *            <code>Address</code>
	 * @return <code> Address </code>
	 */
	private Address getAddress(PixManagerBean bean) {
		if ((bean.getAddress() == null || bean.getAddress()
				.equalsIgnoreCase(""))
				&& (bean.getCity() == null || bean.getCity().equalsIgnoreCase(
						""))
				&& (bean.getState() == null || bean.getState()
						.equalsIgnoreCase(""))
				&& (bean.getCountry() == null || bean.getCountry()
						.equalsIgnoreCase(""))
				&& (bean.getZip() == null || bean.getZip().equalsIgnoreCase(""))) {
			return null;
		}
		Address address = new Address();
		address.setAddLine1(bean.getAddress());
		address.setAddCity(bean.getCity());
		address.setAddState(bean.getState());
		address.setAddCountry(bean.getCountry());
		address.setAddZip(bean.getZip());
		return address;
	}

	/**
	 * Gets <code>PatientIdentifier</code> form <code>PixManagerBean</code>
	 * 
	 * @param bean
	 *            the <code>PixManagerBean</code> where to get
	 *            <code>PatientIdentifier</code>
	 * @return <code>PatientIdentifier</code>
	 */
	private PatientIdentifier getPatientID(PixManagerBean bean) 
	{
		//System.out.println(bean.getSystemid());
		
		//System.out.println(bean.getLocalid());
		
		if (bean.getSystemid() == null && bean.getLocalid() == null) {
			return null;
		}
		
		//System.out.println(bean.getSystemid());
		
		//System.out.println(bean.getLocalid());
		
		PatientIdentifier pid = new PatientIdentifier();

		for (Identifier id : ids) {
			if (id.getNamespaceId().equalsIgnoreCase(bean.getSystemid())) {
				pid.setAssigningAuthority(id);
			}
		}
		if (bean.getLocalid() != null
				&& !bean.getLocalid().equalsIgnoreCase("")) {
			pid.setId(bean.getLocalid());
		}
		return pid;
	}

	/**
	 * Gets <code>MessageHeader</code> from <code>PixManagerBean</code> and
	 * <code>IConnectionDescription</code>
	 * 
	 * @param bean
	 *            the <code>PixManagerBean</code>
	 * @return <code>MessageHeader</code>
	 */
	private MessageHeader getHeader(PixManagerBean bean) {
		MessageHeader mh = new MessageHeader();
		mh.setReceivingFacility(connection.getIdentifier("ReceivingFacility"));
		mh.setReceivingApplication(connection
				.getIdentifier("ReceivingApplication"));
		mh.setSendingApplication(connection.getIdentifier(bean.getSystemid()));
		mh.setMessgeDate(Calendar.getInstance());
		return mh;
	}

	/**
	 * Converts String to <code>Calendar</code>.
	 * 
	 * @param String
	 * @return <code>Calendar</code>
	 */
	private Calendar _convertStringToCalendar(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (date == null) {
			return null;
		}
		try {
			Date date1 = null;
			date1 = dateFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);

			return cal;
		} catch (ParseException pex) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param response
	 * @param msg
	 * @param url
	 * @throws IOException
	 */
	private void print(HttpServletResponse response,String msg,String url) throws IOException{
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		out.print("<script type='text/javascript'>");
		if(msg!=null){
			out.print("window.alert('"+msg+"');");
		}
		if(url!=null){
			out.print("window.location.href='"+url+"';");
		}
		out.print("</script>");
	}
	
	PatientIdentifier pid = null;
	Set<Identifier> ids = null;
	PixPdqConfigurationLoader loader = null;
	IConnectionDescription connection = null;
	private PixManagerBean pm=new PixManagerBean();		
	private HttpServletResponse response;
	private HttpServletRequest request;
	// 页面变量
	private Map<String, String> assigninglist = new HashMap<String, String>();
	private List<PatientIdentifier> pidlist = new ArrayList<PatientIdentifier>();
	// 初始化每页显示的数据行数
	private static int pageSize = 20;
	private int pageIndex = 1;
	private String isPage;
	private int personIndex;
	private IDictionaryService dictionaryService;
	private IContactpersonService contactpersonService;
	
	
	
	
	public IContactpersonService getContactpersonService() {
		return contactpersonService;
	}

	public void setContactpersonService(IContactpersonService contactpersonService) {
		this.contactpersonService = contactpersonService;
	}

	public PixManagerBean getPm() {
		return pm;
	}

	public void setPm(PixManagerBean pm) {
		this.pm = pm;
	}
	public IDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	private int count = 0;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public int getPersonIndex() {
		return personIndex;
	}

	public void setPersonIndex(int personIndex) {
		this.personIndex = personIndex;
	}

	public List<PatientIdentifier> getPidlist() {
		return pidlist;
	}

	public void setPidlist(List<PatientIdentifier> pidlist) {
		this.pidlist = pidlist;
	}

	public Map<String, String> getAssigninglist() {
		return assigninglist;
	}

	public void setAssigninglist(Map<String, String> assigninglist) {
		this.assigninglist = assigninglist;
	}

	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response = arg0;
	}
	
}
