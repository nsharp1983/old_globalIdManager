package com.ats.aempi.apixpdqadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import com.ats.aempi.service.PersonManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ca.uhn.hl7v2.model.Message;

import com.ats.aexchange.config.PropertyFacade;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.Patient;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.apixpdq.api.IPixManagerAdapter;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.impl.v2.PixFeedHandlerHelper;
import com.ats.aempi.ApplicationException;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.blocking.basicblocking.BasicBlockingConstants;
import com.ats.aempi.blocking.basicblocking.BlockingRound;
import com.ats.aempi.configuration.BaseField;
import com.ats.aempi.configuration.MatchField;
import com.ats.aempi.context.Context;
import com.ats.aempi.dao.PersonDao;
import com.ats.aempi.dao.PersonLinkDao;
import com.ats.aempi.dao.hibernate.PersonDaoHibernate;
import com.ats.aempi.matching.exactmatching.ExactMatchingConstants;
import com.ats.aempi.model.Contactperson;
import com.ats.aempi.model.Criteria;
import com.ats.aempi.model.Criterion;
import com.ats.aempi.model.IdentifierDomainDict;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.model.PersonLink;
import com.ats.aempi.model.ExtendForPerson;
import com.ats.aempi.model.Record;
import com.ats.aempi.model.RecordPair;

/**
 * PIX服务类
 * 
 * @see com.ats.aempi.service.impl.PersonManagerServiceImpl
 * @see com.ats.apixpdq.impl.v2.PixFeedHandler
 * 
 * createPatient:创建注册新病人,参数patient类
 * mergePatients:合并病人,合并指定的2个patient类
 * updatePatient:更新病人
 * 
 * @author yrh-2012-04-10
 *
 */
public class PixManagerAdapter extends BasePixPdqAdapter implements IPixManagerAdapter
{
	protected final Log log = LogFactory.getLog(getClass());
	
	public PixManagerAdapter() 
	{
		super();
	}
	
	//查询扩展字段从EXTENDFORPERSON表-20121119
	public List<ExtendForPerson> ExtendFieldForPatient() throws PixManagerException
	{
		@SuppressWarnings("unused")
		List<ExtendForPerson> myExtendField = new ArrayList<ExtendForPerson>();
		
		try {
			myExtendField = Context.getPersonManagerService().ExtendField();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myExtendField;

	}

	//创建注册新病人
	public List<PatientIdentifier> createPatient(Patient patient, MessageHeader armessageHeader1) throws PixManagerException,ApplicationException
	{
		//从patient类获取输入信息填充入person实体类
		Person person = ConversionHelper.getPerson(patient);

		//获取就诊信息
		PatientVisit patientvisit=ConversionHelper.getPatientVisit(patient);

		//联系人
		List<Contactperson> mycontactperson = new ArrayList<Contactperson>();
		//List<Allergy> myallergy = new ArrayList<Allergy>();

		mycontactperson = ConversionHelper.getConatactPerson(patient);

		//myallergy = ConversionHelper.getPersonAllergy(patient);

		Person personAdded;

		try
		{
			PersonManagerService p=Context.getPersonManagerService();
			SecurityHelper.getSessionKey();

			personAdded = Context.getPersonManagerService().addPerson(person,patientvisit,mycontactperson);

			person=null;

			patientvisit=null;

		}
		catch (Exception e)
		{
			// 如果注册的病人已经存在则报错

			if (e instanceof ApplicationException)
			{
				return patient.getPatientIds();
			}
			else
			{
				throw PixManagerAdapterHelper.GeneratePixManagerException(e);
			}
		}

		//List<PatientIdentifier> ret = new ArrayList<PatientIdentifier>();



		//进行推送匹配,IHE测试时打开
//		if(personAdded!=null)
//		{
//			@SuppressWarnings("unused")
//			List<PersonIdentifier> Pmatching=new ArrayList<PersonIdentifier>();
//
//			Pmatching=Context.getPersonQueryService().GetPersonPid(personAdded);
//
//
//			//System.out.println(Pmatching.size());
//
//			PersonIdentifier from=new PersonIdentifier();
//
//		if(Pmatching!=null)
//		{
//
//			for (int i=0;i<Pmatching.size();i++) {
//				try
//				{
//					from=Pmatching.get(i);
//				}
//				catch(Exception e)
//				{
//					e.printStackTrace();
//				}
//			PatientIdentifier to = ConversionHelper.convertPatientIdentifier(from);
//
//			ret.add( to );
//		}
//
//		   Pmatching=null;
//
//	       return ret;
//		}
//
//		}
		List<PatientIdentifier> matching = null;

		if(personAdded!=null) matching=getPatientIds(personAdded);

		return matching;
	}

	//合并病人
	public void mergePatients(Patient patientMain, Patient otherPatient, MessageHeader header)throws PixManagerException, ApplicationException, AuthenticationException, NamingException
	{
		//List<List<PatientIdentifier>> ret = new ArrayList<List<PatientIdentifier>>();

		//获取需要合并的2个人的病人ID
		PatientIdentifier mainPatientId = patientMain.getPatientIds().get(0);

		PatientIdentifier otherPatientId = otherPatient.getPatientIds().get(0);

		//List<PatientIdentifier> oldMrgMatching = findPatientIds(mainPatientId, header);

		//获取2个人的身份信息,进行合并操作
		Context.getPersonManagerService().mergePersons(ConversionHelper.getPersonIdentifier(otherPatientId), ConversionHelper.getPersonIdentifier(mainPatientId));

		//3. Find lists of patients to be updated
		//List<PatientIdentifier> newMatching = findPatientIds(mainPatientId, header);

		//List<PatientIdentifier> unmatching = new ArrayList<PatientIdentifier>();

//		for (PatientIdentifier oldMrg : oldMrgMatching) {
//			if (!newMatching.contains(oldMrg)) {
//				unmatching.add(oldMrg);
//			}
//		}

		//4.PIX Update Notification to PIX consumers
		//If there is any update on the matching
//		if (!newMatching.equals(oldMrgMatching)) {
//			//Add the original patient id since findPatientIds
//			//does not include the original patient id.
//			newMatching.add(mainPatientId);
//			ret.add(newMatching);
//		}
//		if (unmatching.size() > 0) {
//			ret.add(unmatching);
//		}
//		return ret;
	}

	public void ArtificialMergePatientID(Patient patientMain,Patient patientOld, MessageHeader header) throws PixManagerException, ApplicationException
	{
		//获取需要合并的2个人的病人ID
		PatientIdentifier mainPatientId = patientMain.getPatientIds().get(0);

		PatientIdentifier otherPatientId = patientOld.getPatientIds().get(0);

		Person LeftPerson = Context.getPersonQueryService().findPersonById2(ConversionHelper.getPersonIdentifier(mainPatientId));

		if (LeftPerson == null)
		{
			//log.warn("While attempting to merge two persons was not able to locate a record with the given identifier: " + survivingIdentifier);

			throw new ApplicationException("ID为 ：" + ConversionHelper.getPersonIdentifier(mainPatientId).getIdentifier() + " 的记录不存在" );
		}

		Person RightPerson = Context.getPersonQueryService().findPersonById2(ConversionHelper.getPersonIdentifier(otherPatientId));

		if (RightPerson == null)
		{
			//log.warn("While attempting to merge two persons was not able to locate a record with the given identifier: " + survivingIdentifier);

			throw new ApplicationException("ID为 ：" + ConversionHelper.getPersonIdentifier(otherPatientId).getIdentifier() + " 的记录不存在" );
		}


		if (LeftPerson.getEmpi().equalsIgnoreCase(RightPerson.getEmpi()))
		{
			throw new PixManagerException("合并者的LINK信息已包含被合并者，无需再关联");
		}

		Context.getPersonManagerService().ArtificiamergePersons(LeftPerson, RightPerson);

	}

	public void ArtificialCancelMergePatientID(Patient patientMain,Patient patientOld, MessageHeader header) throws PixManagerException, ApplicationException
	{
		//获取需要合并的2个人的病人ID
		PatientIdentifier mainPatientId = patientMain.getPatientIds().get(0);

		PatientIdentifier otherPatientId = patientOld.getPatientIds().get(0);

		Person LeftPerson = Context.getPersonQueryService().findPersonById2(ConversionHelper.getPersonIdentifier(mainPatientId));

		if (LeftPerson == null)
		{

			throw new ApplicationException("ID为 ：" + ConversionHelper.getPersonIdentifier(mainPatientId).getIdentifier() + " 的记录不存在" );
		}

		Person RightPerson = Context.getPersonQueryService().findPersonById2(ConversionHelper.getPersonIdentifier(otherPatientId));

		if (RightPerson == null)
		{

			throw new ApplicationException("ID为 ：" + ConversionHelper.getPersonIdentifier(otherPatientId).getIdentifier() + " 的记录不存在" );
		}


		if (!LeftPerson.getEmpi().equalsIgnoreCase(RightPerson.getEmpi()))
		{
			throw new PixManagerException("合并者与被合并者，本就不关联，无需再反合并");
		}

		Context.getPersonManagerService().CancelmergePersons(LeftPerson, RightPerson, null);
	}


	public void TransPatient(Patient patient, MessageHeader messageHeader,Message msgIn) throws PixManagerException
	{
		Person person = ConversionHelper.getPerson(patient);

		PatientVisit patientvisit = ConversionHelper.getPatientVisit(patient);

		List<Contactperson> mycontactperson = new ArrayList<Contactperson>();

		mycontactperson = ConversionHelper.getConatactPerson(patient);

		try
		{
			//SecurityHelper.getSessionKey();

			Context.getPersonManagerService().TransPerson(person, patientvisit, mycontactperson, messageHeader, msgIn);

		}
		catch (Exception e)
		{
			throw new PixManagerException(e);
		}



	}

	//PATIENT,A02|A03|A06|A07|A09|A10|A12|A13|A21|A22|A32|A33的状态变更
	public void ChangePatient(Patient patient, MessageHeader messageHeader) throws PixManagerException
	{

		String TempIdStr =null,TempDomainStr=null;

		Person person = ConversionHelper.getPerson(patient);

		PatientVisit patientvisit = ConversionHelper.getPatientVisit(patient);

		//PV1为空直接报错
		if (patientvisit==null)
		{
			throw new PixManagerException("状态变化错误：PV1为空");
		}
		else if(messageHeader.getTriggerEvent().equalsIgnoreCase("A02"))
		{
//			if(patientvisit.getPatCurrentBed()!=null)
//			{
//				//if(patientvisit.getPatFormerBed()==null)
//				//{
//					//throw new PixManagerException("A02消息错误：PV1.3.3必须记录新的床位 PV1.6.3必须记录旧的床位");
//				//}
//			}
//			else
//			{
//				throw new PixManagerException("A02消息错误：PV1.3.3必须记录新的床位");
//			}
//
//			if(patientvisit.getPatCuurentDep()!=null)
//			{
//				//if(patientvisit.getPatCuurentDep()==null)
//				//{
//					//throw new PixManagerException("A02消息错误：PV1.3.4必须记录新的科室 PV1.6.4必须记录旧的科室");
//				//}
//			}
//			else
//			{
//				throw new PixManagerException("A02消息错误：PV1.3.4必须记录新的科室 PV1.6.4必须记录旧的科室");
//			}
//
		}
		else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A03"))
		{
			if(patientvisit.getDischargeDate()!=null)
			{
				if(patientvisit.getPatCurrentBed()==null && patientvisit.getPatCuurentDep()==null)
				{
					throw new PixManagerException("A03消息错误：PV1.3.3或PV1.3.4必须有值");
				}
			}
			else
			{
				throw new PixManagerException("A03消息错误：PV1.45，出院日期必须有值");
			}
		}
		else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A06"))
		{
			if(patientvisit.getPatCurrentBed()==null && patientvisit.getPatCuurentDep()==null)
			{
				throw new PixManagerException("A06消息错误：PV1.3.3或PV1.3.4必须有值");
			}
			else if(!patientvisit.getPatCategory().equalsIgnoreCase("E") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("I") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("O") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("P") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("R") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("B") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("C") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("N") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("U") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("A") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("0") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("1") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("2") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("L"))
			{
				throw new PixManagerException("A06消息错误：PV1.2的患者类别不在有效范围内，有效的值为：E、I、O、P、R、B、C、N、U、A、L、0、1、2");
			}
		}
		else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A07"))
		{
			if(patientvisit.getPatCurrentBed()==null && patientvisit.getPatCuurentDep()==null)
			{
				throw new PixManagerException("A07消息错误：PV1.3.3或PV1.3.4必须有值");
			}
			else if(!patientvisit.getPatCategory().equalsIgnoreCase("E") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("I") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("O") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("P") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("R") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("B") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("C") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("N") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("U") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("A") ||
					!patientvisit.getPatCategory().equalsIgnoreCase("L"))
			{
				throw new PixManagerException("A07消息错误：PV1.2的患者类别不在有效范围内，有效的值为：E、I、O、P、R、B、C、N、U、A、L");
			}
		}
		else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A09"))
		{
			if(patientvisit.getPatCurrentBed()==null &&
			   patientvisit.getPatCuurentDep()==null &&
			   patientvisit.getPatFormerBed()==null &&
			   patientvisit.getPatFormerDep()==null)
			{
					throw new PixManagerException("A09消息错误");
			}

		}
		else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A10"))
		{
			if(patientvisit.getPatCurrentBed()==null &&
			   patientvisit.getPatCuurentDep()==null &&
			   patientvisit.getPatFormerBed()==null &&
			   patientvisit.getPatFormerDep()==null)
			{
					throw new PixManagerException("A10消息错误");
			}

		}

		PersonIdentifierEMPI id = person.getPersonIdentifiers().iterator().next();

		Person originalPerson;

		PatientVisit mypatientvisit = new  PatientVisit();

		PatientVisit visitFound = new PatientVisit();

		List<String> PatientVisitInfo = new ArrayList<String>();

		List<String> PatientInfo = new ArrayList<String>();

		try
		{
			SecurityHelper.getSessionKey();

			originalPerson = Context.getPersonQueryService().findPersonById2(id);

		}
		catch (Exception e)
		{
			throw new PixManagerException(e);
		}

		if (originalPerson == null)
		{
			log.warn("Unable to find the patient to be updated using the identifier: "  + id);

			throw new PixManagerException("Unable to find the patient to be updated in the registry.");
		}

		if (person.getCustom6()!=null)
		{

			if(person.getCustom6().indexOf("^")>0)
			{
				TempIdStr = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));

				TempDomainStr = person.getCustom6().substring(person.getCustom6().indexOf("^")+1);
			}
			else
			{
				TempIdStr = person.getCustom6();

				TempDomainStr = null;
			}
		}

		if(person.getCustom6()!=null && patientvisit !=null && patientvisit.getPatientId().equalsIgnoreCase(TempIdStr))
		{
			//查询获取PATIENTVISIT的资料
//			if(originalPerson.getCustom15()!=null && originalPerson.getCustom19()!=null)
//			{
//				mypatientvisit = Context.getPersonQueryService().findPatientVisitListByHisId(originalPerson,TempIdStr);
//			}
//			else
//			{
			mypatientvisit = Context.getPersonQueryService().findPatientVisitById(originalPerson);

			visitFound = mypatientvisit;
			//}

			if (mypatientvisit==null)
			{
				log.warn("无法找到对应的就诊记录"  + id);

				throw new PixManagerException("无法找到对应的就诊记录");
			}

		}
		else if(person.getCustom6()!=null && patientvisit !=null && !patientvisit.getPatientId().equalsIgnoreCase(TempIdStr))
		{
			log.warn("PID.4.1与PV1.19.1的流水号不匹配");

			throw new PixManagerException("PID.4.1与PV1.19.1的流水号不匹配");
		}
		else
		{
			mypatientvisit =null;
		}


		PatientVisitInfo = null;

		PatientVisitInfo = PixManagerAdapterHelper.updatePatientVisitAttributes(mypatientvisit,patientvisit,person);

		PatientInfo.add(0, "");

		PatientInfo.add(1, "");

		PatientInfo.add(2, PatientVisitInfo.get(0).toString());

		PatientInfo.add(3, PatientVisitInfo.get(1).toString());

		originalPerson.setCustom29(messageHeader.getTriggerEvent().toString());

		// Change
		// Patient,不更新PERSON，只更新PATIENTVISIT，同时将历史PATIENTVISIT移入PATIENTVISIT_HISTORY
		try
		{
			Context.getPersonManagerService().ChangePerson(originalPerson, mypatientvisit, visitFound, PatientInfo);
		}
		catch (Exception e)
		{
			throw new PixManagerException(e);
		}


	}

	//PATIENT,A11|A29的状态变更
	public void DeletePatient(Patient patient, MessageHeader messageHeader) throws PixManagerException
	{
		String TempIdStr =null,TempDomainStr=null;

		Person person = ConversionHelper.getPerson(patient);

		PatientVisit patientvisit = ConversionHelper.getPatientVisit(patient);

		PersonIdentifierEMPI id = person.getPersonIdentifiers().iterator().next();

		Person originalPerson;

		PatientVisit mypatientvisit;

		List<String> PatientVisitInfo = new ArrayList<String>();

		List<String> PatientInfo = new ArrayList<String>();

		try
		{
			SecurityHelper.getSessionKey();

			originalPerson = Context.getPersonQueryService().findPersonById(id);

		}
		catch (Exception e)
		{
			throw new PixManagerException(e);
		}

		if (originalPerson == null)
		{
			log.warn("Unable to find the patient to be updated using the identifier: "  + id);

			throw new PixManagerException("Unable to find the patient to be updated in the registry.");
		}


		if(messageHeader.getTriggerEvent().equalsIgnoreCase("A11"))
		{
			//A11事件情况下，PV1为空直接报错
			if (patientvisit==null)
			{
				throw new PixManagerException("取消入院信息有误：PV1为空");
			}

			if (person.getCustom6()!=null)
			{

				if(person.getCustom6().indexOf("^")>0)
				{
					TempIdStr = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));

					TempDomainStr = person.getCustom6().substring(person.getCustom6().indexOf("^")+1);
				}
				else
				{
					TempIdStr = person.getCustom6();

					TempDomainStr = null;
				}
			}

			if(person.getCustom6()!=null && patientvisit !=null && patientvisit.getPatientId().equalsIgnoreCase(TempIdStr) )
			{
				//查询获取PATIENTVISIT的资料
				mypatientvisit = Context.getPersonQueryService().findPatientVisitById(originalPerson);

				if (mypatientvisit == null)
				{
					log.warn("Unable to find the patient visit to be updated using the identifier: "  + id);

					throw new PixManagerException("Unable to find the patient visit to be updated in the registry.");
				}

			}
			else if(person.getCustom6()!=null && patientvisit !=null && !patientvisit.getPatientId().equalsIgnoreCase(TempIdStr) )
			{
				log.warn("PID.4.1与PV1.19.1的流水号不匹配");

				throw new PixManagerException("PID.4.1与PV1.19.1的流水号不匹配");
			}
			else
			{
				mypatientvisit =null;
			}

			PatientInfo.add(0,"");

			PatientInfo.add(1,"");

			PatientInfo.add(2,"");

			PatientInfo.add(3,"");

			originalPerson.setCustom29(messageHeader.getTriggerEvent().toString());

			try
			{
				Context.getPersonManagerService().DeletePatientVisit(originalPerson, mypatientvisit, PatientInfo);
			}
			catch (Exception e)
			{
				throw new PixManagerException(e);
			}

		}
		else if(messageHeader.getTriggerEvent().equalsIgnoreCase("A29"))
		{
			PatientInfo.add(0,"");

			PatientInfo.add(1,"");

			PatientInfo.add(2,"");

			PatientInfo.add(3,"");

			originalPerson.setCustom29(messageHeader.getTriggerEvent().toString());

			try
			{
				Context.getPersonManagerService().DeletePersonRecord(originalPerson);
			}
			catch (Exception e)
			{
				throw new PixManagerException(e);
			}
		}
	}

	/* 更新患者信息
	 *
	 *
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public List<List<PatientIdentifier>> updatePatient(Patient patient, MessageHeader messageHeader) throws PixManagerException
	{
		String TempIdStr =null,TempDomainStr=null;

		List<List<PatientIdentifier>> ret = new ArrayList<List<PatientIdentifier>>();

		Person person = ConversionHelper.getPerson(patient);

		PatientVisit patientvisit = ConversionHelper.getPatientVisit(patient);

		PersonIdentifierEMPI id = person.getPersonIdentifiers().iterator().next();

		Person originalPerson,foundPerson =null;

		PatientVisit mypatientvisit,foundPatientVisit = null;

		List<String> PersonInfo = new ArrayList<String>();

		List<String> PatientVisitInfo = new ArrayList<String>();

		List<String> PatientInfo = new ArrayList<String>();

		try
		{
			SecurityHelper.getSessionKey();

			originalPerson = Context.getPersonQueryService().findPersonById2(id);

			foundPerson = originalPerson;

			if (originalPerson!=null ) originalPerson.setCustom6(person.getCustom6());

		if (originalPerson == null){

			//log.w("Unable to find the patient to be updated using the identifier: "  + id);

			throw new PixManagerException("查找病人记录失败，没有查找到病人：" + person.getCustom16() + " | " + person.getCustom11());

		}

		if (person.getCustom6()!=null)
		{

			if(person.getCustom6().indexOf("^")>0)
			{
				TempIdStr = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));
			}
			else
			{
				TempIdStr = person.getCustom6();
			}
		}



		//传参有就诊信息
		if(person.getCustom6()!=null && patientvisit !=null)
		{
			//id+域id不为空，patientvisit不为空，patientvisit中id与custom6中患者id相同

			//查询获取PATIENTVISIT的资料
			mypatientvisit = Context.getPersonQueryService().findPatientVisitById(originalPerson);

			foundPatientVisit = mypatientvisit;

			if (mypatientvisit != null)
				PatientVisitInfo=PixManagerAdapterHelper.updatePatientVisitAttributes(mypatientvisit, patientvisit,person);
		}
		else
		{
			mypatientvisit =null;
		}

        //Hansen
		//Bug修正开始：更新关联字段后，不自动重新计算关联关系
		//关联字段包括：姓名givenName，医保卡号insuranceNo，身份证号identityNo，HIS就诊号custom15，HIS流水号custom19，病案号custom24
        //判断更新字段是否存在匹配字段
        int MatchCount = 0;

        //如果更改了姓名，要重新计算EMPI
        if(person.getGivenName()!=null && (!person.getGivenName().equals(originalPerson.getGivenName())))
        {
            MatchCount++;
        }

        //如果更改了身份证号，要重新计算EMPI
        if(person.getIdentityNo()!=null && (!person.getIdentityNo().equals(originalPerson.getIdentityNo())))
        {
            MatchCount++;
        }

        //如果更改了医保号，要重新计算EMPI
        if(person.getInsuranceNo()!=null && (!person.getInsuranceNo().equals(originalPerson.getInsuranceNo())))
        {
            MatchCount++;
        }

        //如果新的匹配Id与原匹配Id不一致，则需要重新计算EMPI
        if(person.getCustom15()!=null && (!person.getCustom15().equals(originalPerson.getCustom15())))
        {
            MatchCount++;
        }
        if(person.getCustom19()!=null && (!person.getCustom19().equals(originalPerson.getCustom19())))
        {
            MatchCount++;
        }
        if(person.getCustom24()!=null && (!person.getCustom24().equals(originalPerson.getCustom24())))
        {
            MatchCount++;
        }
        //Bug修正结束：更新关联字段后，不自动重新计算关联关系

        //然后再说更新Person的事情
        //这个逻辑需要调整，现在逻辑太混乱了
		PersonInfo=PixManagerAdapterHelper.updatePersonAttributes(originalPerson, person);

		PatientInfo.add(0,PersonInfo.get(0).toString());

		PatientInfo.add(1,PersonInfo.get(1).toString());

		if(PatientVisitInfo!=null && PatientVisitInfo.size()>0)
		{
			PatientInfo.add(2,PatientVisitInfo.get(0).toString());

			PatientInfo.add(3,PatientVisitInfo.get(1).toString());
		}
		else
		{
			PatientInfo.add(2,"无");

			PatientInfo.add(3,"无");
		}

		log.fatal("更新信息为" + PatientInfo);

		//是否启用新的更新方案
		String enablenewupdate = PropertyFacade.getString(PixPdqConstants.ENABLE_NEW_UPDATE);

		if (enablenewupdate.equalsIgnoreCase("true"))
		{
			//获取得到MPI设置的匹配字段,过滤掉重复的
			List<BlockingRound> blockingRounds = null;

			List<String> MatchFields = new ArrayList();

			if (blockingRounds == null)
			{
				blockingRounds = (List<BlockingRound>)Context.getConfiguration().lookupConfigurationEntry(BasicBlockingConstants.BLOCKING_ROUNDS_REGISTRY_KEY);
			}

			for (BlockingRound round : blockingRounds)
			{
				List<BaseField> fields = round.getFields();

				for (BaseField field : fields)
				{
					String MatchTemp = field.getFieldName();

					if(!MatchFields.contains(MatchTemp)) MatchFields.add(MatchTemp);
				}
			}

			for(String TempFiled:MatchFields)
			{
				if(PatientInfo.get(0).indexOf(TempFiled)>0) MatchCount++;
			}

			//如果MatchCount为0，说明没有更新匹配字段，不需要重新计算关联关系
			//如果MatchCount不为0，说明需要更新匹配字段，需要重新计算关联关系
			if (MatchCount==0)
			{
				try
				{
					//1-不涉及匹配字的更新
					Context.getPersonManagerService().updatePerson(foundPerson, originalPerson, foundPatientVisit, mypatientvisit, PatientInfo, null, 1);
				}
				catch (Exception e)
				{
					throw new PixManagerException(e);
				}
			}
			else if(MatchCount>0)
			{
				//取病人的PERSON_ID 读取LINK信息
				List<String> OldLinks=Context.getPersonManagerService().getPersonLinks(String.valueOf(originalPerson.getPersonId()));

				String TempString = String.valueOf(originalPerson.getPersonId());

				if (OldLinks.contains(TempString))
				{
					OldLinks.remove(TempString);

				}

				if(OldLinks.size()>0)
				{
					originalPerson.setCustom26(originalPerson.getEmpi());
				}
				else
				{
					originalPerson.setCustom26(null);
				}

				try
				{
					//2-涉及匹配字的更新
				    //boolean b1=(foundPerson==originalPerson);
				    //boolean b2=(foundPatientVisit==mypatientvisit);
					Context.getPersonManagerService().updatePerson(foundPerson, originalPerson, foundPatientVisit, mypatientvisit, PatientInfo, OldLinks, 2);
				}
				catch (Exception e)
				{
					throw new PixManagerException(e);
				}

			}
		}
		else if (enablenewupdate.equalsIgnoreCase("false"))
		{
			//2. Update Patient
			try
			{
				//0-保留老版本的功能
				Context.getPersonManagerService().updatePerson(foundPerson, originalPerson, foundPatientVisit, mypatientvisit, PatientInfo, null, 0);
			}
			catch (Exception e)
			{
				throw new PixManagerException(e);
			}
		}
	}
	catch (Exception e)
	{
		throw new PixManagerException(e);
	}
		return ret;
	}





	@SuppressWarnings({ "unchecked", "null" })
	public List<PatientIdentifier> findPatientIds(PatientIdentifier identifier, MessageHeader messageHeader) throws PixManagerException
	{

		List<PatientIdentifier> ret = new ArrayList<PatientIdentifier>();

		String TempString = null;

		PersonIdentifierEMPI searchIdentifier = ConversionHelper.getPersonIdentifier(identifier);

		try
		{
			SecurityHelper.getSessionKey();

			Person myperson = Context.getPersonQueryService().findPersonById2(searchIdentifier);

			if(myperson==null) return ret;


			List<Person> persons = Context.getPersonQueryService().findPersonById3(myperson);

			//List<Person> persons = Context.getPersonQueryService().findLinkedPersons(searchIdentifier);

			for (Person person : persons)
			{
				if(person.getPersonId()!=0)
				{
					for (PersonIdentifierEMPI id : person.getPersonIdentifiers())
					{

						if (!id.equals(searchIdentifier)  && id.getDateVoided()==null)
						{
							//System.out.println(TempString.indexOf(id.getIdentifier()));

							if(TempString==null)
							{
								ret.add(ConversionHelper.getPatientIdentifier(id));

								TempString=TempString + id.getIdentifier();
							}
							else if(TempString.indexOf(id.getIdentifier())<0)
							{
								ret.add(ConversionHelper.getPatientIdentifier(id));

								TempString=TempString + id.getIdentifier();
							}
						}
					}
				}
			}
		} catch (Exception e)
		{
			throw new PixManagerException(e);
		}
		return ret;
	}

	public boolean isValidPatient(PatientIdentifier patientIdentifier, MessageHeader messageHeader) throws PixManagerException {
		try {
			SecurityHelper.getSessionKey();
			Person person = Context.getPersonQueryService().findPersonById2(ConversionHelper.getPersonIdentifier(patientIdentifier));
			if (person != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new PixManagerException(e);
		}
	}

	private List<PatientIdentifier> getPatientIds(Person person) {
		List<PatientIdentifier> ret = new ArrayList<PatientIdentifier>();
		if (person != null) {
			Set<com.ats.aempi.model.PersonIdentifierEMPI> pids = person.getPersonIdentifiers();
			for (com.ats.aempi.model.PersonIdentifierEMPI from : pids) {
				PatientIdentifier to = ConversionHelper.convertPatientIdentifier(from);
				ret.add( to );
			}
		}
		return ret;
	}

	public void changePatientID(Patient patientMain,Patient patientOld, MessageHeader header) throws PixManagerException
	{
		PatientIdentifier mainPatientId = patientMain.getPatientIds().get(0);

		PatientIdentifier otherPatientId = patientOld.getPatientIds().get(0);

		PersonIdentifierEMPI mainIdentifier = ConversionHelper.getPersonIdentifier(mainPatientId);

		PersonIdentifierEMPI otherIdentifier = ConversionHelper.getPersonIdentifier(otherPatientId);

		Person originalPerson=null;

		try
		{
			SecurityHelper.getSessionKey();

			originalPerson = Context.getPersonQueryService().findPersonById(mainIdentifier);

		}
		catch (Exception e)
		{
			throw new PixManagerException(e);
		}

		if (originalPerson == null)
		{
			log.warn("Unable to find the patient to be updated using the identifier: "  + mainIdentifier);

			throw new PixManagerException("Unable to find the patient to be updated in the registry.");
		}

		if (header.getTriggerEvent().equalsIgnoreCase("A47"))
		{
			try
			{
				Context.getPersonManagerService().updatePersonID(originalPerson, otherIdentifier);
			} 
			catch (Exception e)
			{
				throw new PixManagerException(e);
			}
		}
		else if(header.getTriggerEvent().equalsIgnoreCase("A50"))
		{
			throw new PixManagerException("暂无开发计划，待定");
		}
		
	}






	//创建注册新病人
	public Person registPatient(Patient patient) throws PixManagerException{
		//从patient类获取输入信息填充入person实体类
		Person person = ConversionHelper.getPerson(patient);

		//获取就诊信息
		PatientVisit patientvisit=ConversionHelper.getPatientVisit(patient);

		//联系人
		List<Contactperson> mycontactperson;

		mycontactperson = ConversionHelper.getConatactPerson(patient);

		Person personAdded;

		try
		{
			SecurityHelper.getSessionKey();

			personAdded = Context.getPersonManagerService().addPerson(person,patientvisit,mycontactperson);

		}
		catch (Exception e) {
			// 如果注册的病人已经存在则报错

			if (e instanceof ApplicationException)
			{
				return null;
			}
			else
			{
				throw PixManagerAdapterHelper.GeneratePixManagerException(e);
			}
		}


		return personAdded;


	}





	/**
	 * 查询患者ADT信息-----20181213
	 * @param person
	 * @return
	 */
		public PatientVisit queryPatientVisit(Person person){
			PatientVisit patientVisit = new PatientVisit();
			try {
				patientVisit=Context.getPersonManagerService().queryPatientVisit(person);
			} catch (Exception e) {
				log.error("查询PatientVisit失败");
				return patientVisit;
			}
			return patientVisit;
		}





}
