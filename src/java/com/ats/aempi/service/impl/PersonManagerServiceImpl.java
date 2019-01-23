package com.ats.aempi.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.naming.NamingException;

import com.ats.aempi.model.*;
import org.apache.commons.beanutils.BeanUtils;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.segment.MSH;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.ValidationException;
import com.ats.aempi.apixpdqadapter.ConversionHelper;
import com.ats.aempi.apixpdqadapter.SecurityHelper;
import com.ats.aempi.configuration.CustomField;
import com.ats.aempi.configuration.GlobalIdentifier;
import com.ats.aempi.context.Context;
import com.ats.aempi.dao.EmpiDao;
import com.ats.aempi.dao.PatientVisitDao;
import com.ats.aempi.dao.PersonDao;
import com.ats.aempi.dao.PersonLinkDao;
import com.ats.aempi.matching.MatchingService;
import com.ats.aempi.service.PersonManagerService;
import com.ats.aempi.service.ValidationService;
import com.ats.aempi.transformation.TransformationService;
import com.ats.aexchange.config.PropertyFacade;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aexchange.datamodel.PatientIdentifier;
import com.ats.aexchange.datamodel.PersonName;
import com.ats.aexchange.datamodel.SharedEnums.SexType;
import com.ats.apixpdq.api.PixManagerException;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.PixPdqException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 患者信息管理
 *
 * @see com.ats.aempi.apixpdqadapter.PixManagerAdapter
 */
public class PersonManagerServiceImpl extends BaseServiceImpl implements PersonManagerService {
    Log log = LogFactory.getLog(getClass());
    //	private Logger logger = LogManager.getLogger(getClass());
    private PersonDao personDao;
    private PersonLinkDao personLinkDao;
    private PatientVisitDao patientvisitDao;
    private EmpiDao empiDao;

    /**
     * 从EXTENDFORPERSON表查询字段
     *
     * @param ExtendForPerson
     * @throws HL7Exception
     * @throws PixPdqException
     */
    public List<ExtendForPerson> FindExtendField(int PidCount) throws ApplicationException {
        return patientvisitDao.FindExtendByProperty("pid4fields", PidCount);
    }

    /**
     * 从EXTENDFORPERSON表查询字段
     *
     * @param ExtendForPerson
     * @throws HL7Exception
     * @throws PixPdqException
     */
    public List<ExtendForPerson> ExtendField() throws ApplicationException {
        return patientvisitDao.GetExtendFields();
    }

    /*
     * 先新增者信息及就诊信息
     *
     * 姓名强制必须一致才可以关联
     * 患者id及就诊id都不存在，可以注册
     * 患者id存在，就诊id不存在，可以注册
     * 患者id及就诊id都存在，不可以注册
     *
     */
    public Person addPerson(Person person, PatientVisit patientvisit, List<Contactperson> contactperson) throws ApplicationException, PixPdqException, HL7Exception {

        //PANMIN-20150706 新增 所传姓名与  关联HIS号 对应姓名不符的情况
        String checkFieldName1 = PropertyFacade.getString(PixPdqConstants.CHECK_FIELD_BASE_NAME);

        String checkFieldName2 = PropertyFacade.getString(PixPdqConstants.CHECK_FIELD_BASE_LS_NAME);

        if (!checkFieldName1.isEmpty()) {
            personDao.getPersonNameByField(person, checkFieldName1, 1);
        }

        if (!checkFieldName2.isEmpty()) {
            personDao.getPersonNameByField(person, checkFieldName2, 2);
        }

        Person personFound=null;

        personFound=findPersonUsingIdentifiers2(person);

        //PANMIN-20150626 按照关联字段计算EMPI数量
        Record record = new Record(person);

        record.setRecordId(new Long(person.getPersonId()));

        MatchingService matchingService = Context.getMatchingService();

        List<String> myEmpiMergeList = matchingService.matchEmpi(record);

        //EMPI记录集增加自身ID关联EMPI
        if (personFound != null && personFound.getGivenName() != null && person.getGivenName() != null) {
            if (personFound.getGivenName().equalsIgnoreCase(person.getGivenName())) {
                if (personFound.getEmpi() != null && !myEmpiMergeList.contains(personFound.getEmpi())) {
                    myEmpiMergeList.add(personFound.getEmpi());
                }
            } else {
                throw new PixPdqException("当合并EMPI时发生异常，存在同号同域不同名情况，请核对数据后，重新注册");
            }
        }

        log.fatal("EMPI查询结果为" + myEmpiMergeList);

        //是否启用新的合并方案
        String enable_auto_merge = PropertyFacade.getString(PixPdqConstants.ENABLE_NEW_AUTO_MERGE);

        if (enable_auto_merge.equalsIgnoreCase("true")) {
            log.fatal("自动合并状态为开");
        } else {
            log.fatal("自动合并状态为关闭");
        }


        //-----------------------------------------------------------------

        //传参里没有就诊信息，只进行患者注册
        if (person.getCustom16() != null && person.getCustom11() != null && person.getCustom6() == null){
            if (personFound != null) {
                log.fatal("病人记录已经存在，重复注册");

                //throw new PixPdqException("Person record to be added already exists in the system.");

                //System.out.println("Person record to be added already exists in the system.");

                return null;
            }

            log.fatal("新增病人注册，无就诊记录");

            generateGlobalId(person, myEmpiMergeList);

            /*20170515-0035156-start */
            synchronized (this) {
                // 再次查询person信息
//                personFound = findPersonUsingIdentifiers2(person);
//                if (personFound != null) {
//                    return addPersonNew(person, patientvisit, contactperson,
//                            personFound);
//                }
                // 保存信息处理
                savePerson(person);
            }
            Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.ADD_NEW_PERSON_NO_PV1_TYPE, "新增病人注册，无就诊记录", person, null, null);

        }
        //传参里有就诊信息
        else if (person.getCustom16() != null && person.getCustom11() != null && person.getCustom6() != null)
           {

            PatientVisit visitFound = findVisitUsingIdentifiers(person);//(根据患者id，域id，流水id，流水域id)查询之前的就诊信息

            log.fatal("PV1记录为：" + visitFound);

                //PERSON与VISIT都重复，不可以重新注册，只可以做更新
                if (personFound != null && visitFound != null) {
                    log.fatal("重复注册：同样的病人及就诊记录已经存在");
                    person = personFound;
                }
                //PERSON记录已经存在，就诊记录不存在
                else if (personFound != null && visitFound == null && myEmpiMergeList.size() == 1) {
                    //PERSON存在，VISIT不存在，EMPI为1，可以注册
                    if (patientvisit != null && personFound.getPersonId() > 0) {

                        if (person.getCustom6() != null) personFound.setCustom6(person.getCustom6());

                        if (person.getCustom15() != null) personFound.setCustom15(person.getCustom15());

                        if (person.getCustom19() != null) personFound.setCustom19(person.getCustom19());

                        savePatientVisit(patientvisit, personFound);

                        person = personFound;
                    }

                    Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.ADD_OLD_PERSON_HAVE_PV1_TYPE, "历史病人注册，增加就诊信息", person, null, null);
                }

                else if (personFound != null && visitFound == null && myEmpiMergeList.size() > 1)//PERSON记录已经存在，且EMPI数量大于1时，执行合并，PV1新增记录
                {
                    //PERSON存在，VISIT不存在，EMPI为1，可以注册，但会发生合并
                    if (enable_auto_merge.equalsIgnoreCase("true")) {
                        try {
                            generateGlobalIdAndMerge(person, myEmpiMergeList, 1);
                        } catch (Exception e) {
                            throw new ApplicationException("当合并EMPI时发生已成，请重新尝试");
                        }
                    }
                    if (person.getCustom6() != null) personFound.setCustom6(person.getCustom6());

                    if (person.getCustom15() != null) personFound.setCustom15(person.getCustom15());

                    if (person.getCustom19() != null) personFound.setCustom19(person.getCustom19());

                    savePatientVisit(patientvisit, personFound);

                    //PANMIN-20150630 person记录存在的情况下，合并操作后，需补一条EMPI记录
                    Empi tempempi = new Empi();

                    Empi myempi = new Empi();

                    tempempi = empiDao.getPersonByEmpi(person);

                    if (tempempi == null) {
                        myempi = AddEmpiAttribute(person);

                        empiDao.addEmpi(myempi);
                    } else if (tempempi != null) {
                        myempi = updateEmpiAttribute(person, tempempi);

                        empiDao.updateEmpi(myempi);
                    }

                    person = personFound;
                }

                //患者信息不存在
                else if (personFound == null)
                {
                    //PERSON不存在，EMPI为0或1，可以注册
                    if (myEmpiMergeList.size() < 2) {
                        generateGlobalId(person, myEmpiMergeList);
                    } else if (myEmpiMergeList.size() > 1) {
                        if (enable_auto_merge.equalsIgnoreCase("true")) {
                            try {
                                generateGlobalIdAndMerge(person, myEmpiMergeList, 1);
                            } catch (Exception e) {
                                throw new PixPdqException("当合并EMPI时发生异常，请重新尝试");
                            }
                        }
                    }
                    /*20170515-0035156-start */
                    synchronized (this) {
//                        personFound = findPersonUsingIdentifiers2(person);
//                        if (personFound != null) {
//                            return addPersonNew(person, patientvisit,
//                                    contactperson, personFound);
//                        }

                        savePerson(person);
                    }
                    //savePerson(person);
                    /* 20170515-0035156-end */

                    if (person.getCustom6() == null) {
                        throw new PixPdqException("住院流水号缺失");
                    }

                    //增加NK1信息的保存
                    if (contactperson.size() > 0) {
                        if (contactperson.get(0).getContactName() != null) {
                            patientvisit.setContactPerson(contactperson.get(0).getContactName());
                        }

                        if (contactperson.get(0).getContactType() != null) {
                            patientvisit.setContactRelations(contactperson.get(0).getContactType());
                        }

                        if (contactperson.get(0).getContactAddress() != null) {
                            patientvisit.setContactAddress(contactperson.get(0).getContactAddress());
                        }

                        if (contactperson.get(0).getContactPhone() != null) {
                            patientvisit.setContactPhone(contactperson.get(0).getContactPhone());
                        }

                        personDao.addContactPerson(contactperson, person);

                    }

                    //增加就诊信息表的保存
                    if (patientvisit != null && person.getPersonId() > 0) {

                        //20190110新增
                        if (patientvisit.getIsoutpatient()==null) patientvisit.setIsoutpatient(100);
                        savePatientVisit(patientvisit, person);
                    }

                    Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.ADD_NEW_PERSON_NO_PV1_TYPE, "新增病人注册", person, null, null);

                }

                //20150706 废弃LINK算法
                //LINK计算
                //findAndProcessAddRecordLinks(person);

                //由于新反LINK的开启，而增加的对应清除LINK
                //RemovePersonLinkForCancel(person);


            }

        return person;
    }

    /*20170515-0035156-start */

    /**
     * 并发追加病人信息注册
     *
     * @param person
     * @param patientvisit
     * @param personFound
     * @return
     * @throws ApplicationException
     * @throws PixPdqException
     * @throws HL7Exception
     */
    private Person addPersonNew(Person person, PatientVisit patientvisit, List<Contactperson> contactperson, Person personFound)
            throws ApplicationException, PixPdqException, HL7Exception {

        // PANMIN-20150626 按照关联字段计算EMPI数量
        Record record = new Record(person);

        record.setRecordId(new Long(person.getPersonId()));

        MatchingService matchingService = Context.getMatchingService();

        List<String> myEmpiMergeList = matchingService.matchEmpi(record);

        // EMPI记录集增加自身ID关联EMPI
        if (personFound != null && personFound.getGivenName() != null && person.getGivenName() != null) {
            if (personFound.getGivenName().equalsIgnoreCase(person.getGivenName())) {
                if (personFound.getEmpi() != null && !myEmpiMergeList.contains(personFound.getEmpi())) {
                    myEmpiMergeList.add(personFound.getEmpi());
                }
            } else {
                throw new PixPdqException("当合并EMPI时发生异常，存在同号同域不同名情况，请核对数据后，重新注册");
            }
        }

        log.fatal("EMPI查询结果为" + myEmpiMergeList);

        // 是否启用新的合并方案
        String enable_auto_merge = PropertyFacade.getString(PixPdqConstants.ENABLE_NEW_AUTO_MERGE);

        if (enable_auto_merge.equalsIgnoreCase("true")) {
            log.fatal("自动合并状态为开");
        } else {
            log.fatal("自动合并状态为关闭");
        }

        // -----------------------------------------------------------------

        if (person.getCustom16() != null && person.getCustom11() != null && person.getCustom6() == null) // 只有pid，无pv1
        {
            if (personFound != null) {
                log.fatal("病人记录已经存在，重复注册");

                // throw new PixPdqException("Person record to be added already
                // exists in the system.");

                // System.out.println("Person record to be added already exists
                // in the system.");

                return null;
            }

            log.fatal("新增病人注册，无就诊记录");

            generateGlobalId(person, myEmpiMergeList);

            savePerson(person);

            // 20150706-废弃LINK旧算法
            // findAndProcessAddRecordLinks(person);

            // 由于新反LINK的开启，而增加的对应清除LINK
            // RemovePersonLinkForCancel(person);

            Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.ADD_NEW_PERSON_NO_PV1_TYPE, "新增病人注册，无就诊记录",
                    person, null, null);

        } else if (person.getCustom16() != null && person.getCustom11() != null && person.getCustom6() != null)// 有PID，PV1
        {
            String TempIdStr = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));

            PatientVisit visitFound = findVisitUsingIdentifiers(person);

            log.fatal("PV1记录为：" + visitFound);

            if (TempIdStr.equalsIgnoreCase(patientvisit.getPatientId())) // 如果pv4.1==pv19.1
            // 才判断为正确数据，进行注册
            {

                if (personFound != null && visitFound != null) {
                    // PERSON与VISIT都重复，不可以重新注册，只可以做更新
                    log.fatal("重复注册：同样的病人及就诊记录已经存在");
                    return null;
                } else if (personFound != null && visitFound == null && myEmpiMergeList.size() == 1)// PERSON记录已经存在，且EMPI数量为1，PV1记录为新增时，添加PV1记录
                {
                    // PERSON存在，VISIT不存在，EMPI为1，可以注册
                    if (patientvisit != null && personFound.getPersonId() > 0) {

                        if (person.getCustom6() != null)
                            personFound.setCustom6(person.getCustom6());

                        if (person.getCustom15() != null)
                            personFound.setCustom15(person.getCustom15());

                        if (person.getCustom19() != null)
                            personFound.setCustom19(person.getCustom19());

                        savePatientVisit(patientvisit, personFound);

                        person = personFound;
                    }

                    Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.ADD_OLD_PERSON_HAVE_PV1_TYPE,
                            "历史病人注册，增加就诊信息", person, null, null);
                } else if (personFound != null && visitFound == null && myEmpiMergeList.size() > 1)// PERSON记录已经存在，且EMPI数量大于1时，执行合并，PV1新增记录
                {
                    // PERSON存在，VISIT不存在，EMPI为1，可以注册，但会发生合并
                    if (enable_auto_merge.equalsIgnoreCase("true")) {
                        try {
                            generateGlobalIdAndMerge(person, myEmpiMergeList, 1);
                        } catch (Exception e) {
                            throw new ApplicationException("当合并EMPI时发生已成，请重新尝试");
                        }
                    }
                    if (person.getCustom6() != null)
                        personFound.setCustom6(person.getCustom6());

                    if (person.getCustom15() != null)
                        personFound.setCustom15(person.getCustom15());

                    if (person.getCustom19() != null)
                        personFound.setCustom19(person.getCustom19());

                    savePatientVisit(patientvisit, personFound);

                    // PANMIN-20150630 person记录存在的情况下，合并操作后，需补一条EMPI记录
                    Empi tempempi = new Empi();

                    Empi myempi = new Empi();

                    tempempi = empiDao.getPersonByEmpi(person);

                    if (tempempi == null) {
                        myempi = AddEmpiAttribute(person);

                        empiDao.addEmpi(myempi);
                    } else if (tempempi != null) {
                        myempi = updateEmpiAttribute(person, tempempi);

                        empiDao.updateEmpi(myempi);
                    }

                    person = personFound;
                } else if (personFound == null) // PERSON为新增记录，EMPI的数量为0或1时，执行老的判断逻辑，完成新增或单记录合并
                {
                    // PERSON不存在，EMPI为0或1，可以注册
                    if (myEmpiMergeList.size() < 2) {
                        generateGlobalId(person, myEmpiMergeList);
                    } else if (myEmpiMergeList.size() > 1) {
                        if (enable_auto_merge.equalsIgnoreCase("true")) {
                            try {
                                generateGlobalIdAndMerge(person, myEmpiMergeList, 1);
                            } catch (Exception e) {
                                throw new PixPdqException("当合并EMPI时发生异常，请重新尝试");
                            }
                        }
                    }

                    savePerson(person);

                    if (person.getCustom6() == null) {
                        throw new PixPdqException("住院流水号缺失");
                    }

                    //增加NK1信息的保存
                    if (contactperson.size() > 0) {
                        if (contactperson.get(0).getContactName() != null) {
                            patientvisit.setContactPerson(contactperson.get(0).getContactName());
                        }

                        if (contactperson.get(0).getContactType() != null) {
                            patientvisit.setContactRelations(contactperson.get(0).getContactType());
                        }

                        if (contactperson.get(0).getContactAddress() != null) {
                            patientvisit.setContactAddress(contactperson.get(0).getContactAddress());
                        }

                        if (contactperson.get(0).getContactPhone() != null) {
                            patientvisit.setContactPhone(contactperson.get(0).getContactPhone());
                        }

                        personDao.addContactPerson(contactperson, person);

                    }

                    // 增加就诊信息表的保存
                    if (patientvisit != null && person.getPersonId() > 0) {
                        savePatientVisit(patientvisit, person);
                    }

                    Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.ADD_NEW_PERSON_TYPE, "新增病人注册",
                            person, null, null);

                }

                // 20150706 废弃LINK算法
                // LINK计算
                // findAndProcessAddRecordLinks(person);

                // 由于新反LINK的开启，而增加的对应清除LINK
                // RemovePersonLinkForCancel(person);

            } else {
                log.fatal("PID.4.1与PV1.19.1的流水号不匹配");

                // throw new PixPdqException("PID.4.1与PV1.19.1的流水号不匹配");

                // System.out.println("PID.4.1与PV1.19.1的流水号不匹配");

                return null;
            }
        }
        return person;
    }
    /*20170515-0035156-end */


    private Object getHibernateTemplate() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * 删除患者信息
     *
     */
    public void deletePerson(PersonIdentifierEMPI personIdentifier) throws ApplicationException {

        ValidationService validationService = Context.getValidationService();
        validationService.validate(personIdentifier);

        Person personFound = personDao.getPersonById(personIdentifier);
        if (personFound == null) {
            log.warn("While attempting to delete a person was not able to locate a record with the given identifier: " + personIdentifier);
            throw new ApplicationException("Person record to be deleted does not exist in the system.");
        }

        findAndDeleteRecordLinks(personFound);
        deletePerson(personFound);

        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.DELETE_PERSON_EVENT_TYPE, "Deleted a person record", personFound, null, null);
    }

    /*
     * 删除患者id
     *
     */
    public void mergePersons(PersonIdentifierEMPI retiredIdentifier, PersonIdentifierEMPI survivingIdentifier) throws ApplicationException, AuthenticationException, NamingException {
        SecurityHelper.getSessionKey();
//		ValidationService validationService = Context.getValidationService();
//
//		validationService.validate(retiredIdentifier);
//
//		validationService.validate(survivingIdentifier);

        Person personSurviving = personDao.getPersonById2(survivingIdentifier); //PID

        if (personSurviving == null) {
            //log.warn("While attempting to merge two persons was not able to locate a record with the given identifier: " + survivingIdentifier);

            throw new ApplicationException("ID为 ：" + survivingIdentifier.getIdentifier() + " 的记录不存在");
        }

        Person personRetiring = personDao.getPersonById2(retiredIdentifier); //MRG

        if (personRetiring == null) {
            //log.warn("While attempting to merge two persons was not able to locate a record with the given identifier: " + retiredIdentifier);

            throw new ApplicationException("ID为 ：" + retiredIdentifier.getIdentifier() + " 的记录不存在");
        }

        // Delete the retired person record
        deleteGlobalPerson(personRetiring, personSurviving);

        //插入事件
        saveMergeEvent(personSurviving, personRetiring, 1);

        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.MERGE_PERSON_EVENT_TYPE, "病人合并", personSurviving, personRetiring, null);
    }

    @SuppressWarnings("unused")
    public void ArtificiamergePersons(Person leftperson, Person rightperson) throws ApplicationException {
        String returnMsg = null;

        //检查PERSON_LINK_CANCEL是否存在同样记录
        PersonLinkCancel personlinkcancel = null; //personLinkDao.getPersonLinkCancel(leftperson.getEmpi(), rightperson.getPersonId());

        if (personlinkcancel != null) {
            //新EMPI的检查，是否已存在

            Empi tempempi = new Empi();

            tempempi = empiDao.getPersonByEmpi(rightperson);

            if (tempempi == null) {
                throw new ApplicationException("发生EMPI记录不存在的情况");
            }

            System.out.println("NEW EMPI: " + leftperson.getEmpi());

            log.fatal("NEW EMPI: " + leftperson.getEmpi());


            NewRunProcedure(rightperson, rightperson, null, 1);


            //EMPI删除处理,清除PERSON_LINK_CANCEL记录
            //NewMergeProcedure(leftperson,rightperson,2);


        } else {
            //System.out.println("开始合并操作 ");

            log.fatal("开始合并操作 ");
        }

        //右LINK刷新EMPI，涉及PERSON、PERSON_IDENTIFIER、PATIENT_VISIT、PATIENT_VISIT_HISTORY

        //移除左右LINK的所有关联

        //刷新左右LINK的CUSTOM27设置为UUID并更新
        returnMsg = NewMergeProcedure(leftperson, rightperson, 1);

        if (returnMsg.equalsIgnoreCase("0")) {
            throw new ApplicationException("存储过过程MERGE_TYPE_A 运行错误");
        }

    }

    /*
     * 取消合并患者id
     *
     */
    public void CancelmergePersons(Person leftperson, Person rightperson, List<String> MergeLiks) throws ApplicationException {
        String returnMsg = null;

        //获取globalid,判断是否要生成globalid
        GlobalIdentifier globalId = Context.getConfiguration().getGlobalIdentifier();

        log.trace("Global Identifier Configuration is " + globalId);

        if (globalId != null && !globalId.isAssignGlobalIdentifier()) {
            return;
        }

        IdentifierDomainDict globalIdentDomain = globalId.getIdentifierDomain();

        PersonIdentifierEMPI identifier = generateGlobalIdentifier(globalIdentDomain, rightperson);

        log.fatal("NEW EMPI: " + identifier.getIdentifier().toString());

        rightperson.setCustom25(null);

        rightperson.setCustom26(rightperson.getEmpi());

        rightperson.setEmpi(identifier.getIdentifier());


        returnMsg = NewMergeProcedure(leftperson, rightperson, 2);

        if (returnMsg.equalsIgnoreCase("0")) {
            throw new ApplicationException("存储过过程MERGE_TYPE_B 运行错误");
        } else if (returnMsg.equalsIgnoreCase("1")) {
            //插入事件
            saveMergeEvent(leftperson, rightperson, 2);

            //新EMPI的检查，是否已存在

            Empi tempempi = new Empi();

            Empi myempi = new Empi();

            tempempi = empiDao.getPersonByEmpi(rightperson);

            if (tempempi != null) {
                throw new ApplicationException("更新失败：发生新生成的EMPI已存在的情况");
            }

            //EMPI新增处理

            myempi = AddEmpiAttribute(rightperson);

            empiDao.addEmpi(myempi);
        }

    }

    public void addPersonLinkCancel(Person leftperson, Person rightperson) {
        PersonLinkCancel temppersonlinkcancel = new PersonLinkCancel();

        temppersonlinkcancel.setEmpi(leftperson.getEmpi());

        temppersonlinkcancel.setPersonId(rightperson.getPersonId());

        temppersonlinkcancel.setDateCreated(new java.util.Date());

        personLinkDao.addPersonLinkCancel(temppersonlinkcancel);

    }

    /*
     * 更新患者信息
     *
     */
    public void updatePerson(Person foundPerson, Person person, PatientVisit foundPatientVisit, PatientVisit patientvisit, List<String> PersonInfo, List<String> OldLinks, int ControlID) throws ApplicationException {

        Person personFound = foundPerson;//findPersonUsingIdentifiers2(person);

        //更新记录
        if (person.getCustom16() != null && person.getCustom11() != null && person.getCustom6() == null) {
            //关联ID不为空，患者域不为空，患者id+域id为空
            NewUpdatePersonForControlID(person, personFound, patientvisit, PersonInfo, OldLinks, ControlID);

            //由于新反LINK的开启，而增加的对应清除LINK
            //RemovePersonLinkForCancel(person);

            Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.MODFIY_PERSON_NO_PV1_TYPE, "病人更新，不更新就诊信息", person, null, null);

        } else if (person.getCustom16() != null && person.getCustom11() != null && person.getCustom6() != null) {
            //关联ID不为空，患者域不为空，患者id+域id不为空
            String TempIdStr = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));

            PatientVisit visitFound = foundPatientVisit;//findVisitUsingIdentifiers(person);

            if (TempIdStr.equalsIgnoreCase(patientvisit.getVisitFlowId())) {

                if (personFound != null && visitFound != null) {

                    if (patientvisit != null) {
                        //更新PV1属性
                        updatePatientVisit(patientvisit, personFound);

                        //PV1更新也应保存痕迹
                        SavePatientVisitHistory(visitFound, personFound);
                    }

                    NewUpdatePersonForControlID(person, personFound, patientvisit, PersonInfo, OldLinks, ControlID);

                    //if(ControlID>1) SavePatientVisitHistory(visitFound,personFound);

                    //由于新反LINK的开启，而增加的对应清除LINK
                    //RemovePersonLinkForCancel(person);

                    Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.MODFIY_PERSON_TYPE, "病人更新", person, PersonInfo);

                }
            } else {
                log.warn("PID.4.1与PV1.19.1的流水号不匹配");

                throw new ApplicationException("PID.4.1与PV1.19.1的流水号不匹配");
            }
        }
    }

    /*
     * 这个是在做什么？？？
     * 匹配场景分为下面九种
     * 一、关闭更新时重新计算EMPI的功能
     * 不需要重新计算EMPI
     * 二、更新字段内容，不影响匹配结果
     * 不需要重新计算EMPI
     * 三、原始匹配无LINK记录，更新后无匹配LINK信息
     * 不需要重新计算EMPI
     * 五、原始匹配无LINK记录，更新后有匹配LINK信息
     * 四、原始匹配有LINK记录，更新后无匹配LINK信息
     * 六、原始匹配有LINK记录，更新后有匹配LINK信息，且新记录不包含老记录
     * 七、原始匹配有LINK记录，更新后有匹配LINK信息，且新记录包含老记录
     * 八、原始匹配有LINK记录，更新后有匹配LINK信息，且新记录包含于老记录
     * 九、原始匹配有LINK记录，更新后有匹配LINK信息，且新记录相等于老记录
     * 不需要重新计算EMPI
     */
    public void NewUpdatePersonForControlID(Person person, Person personFound, PatientVisit patientvisit, List<String> PersonInfo, List<String> OldLinks, int ControlID) throws ApplicationException {
        String myResult = null;

        //新UPDATE关闭时，默认使用原有旧程序
        if (ControlID == 0) {
            //System.out.println("匹配场景一，调用老版本UPDATE模块");

            log.fatal("匹配场景一，调用老版本UPDATE模块");

            updatePerson(person, personFound);

            //findAndUpdateRecordLinks(person);//close
        } else if (ControlID == 1) {
            log.fatal("匹配场景二，更新字段不包含匹配字段，不进行LINK重置计算");

            updatePerson(person, personFound);

        } else if (ControlID == 2) {

            //属性更新后的新LINK匹配
            Record record = new Record(person);

            record.setRecordId(new Long(person.getPersonId()));

            MatchingService matchingService = Context.getMatchingService();

            //这里获取了新的关联
            List<String> newLinks = matchingService.matchPersonID(record);//改改改改改改改改改改改改改

            record = null;

            //List<PersonLink> newLink = new ArrayList<PersonLin();

            //List<String> newLinks = new ArrayList<String>();

//			for (RecordPair recordPair : links)
//			{
//				PersonLink personLink = getPersonLinkFromRecordPair(recordPair);
//
//				String TempStrA = String.valueOf(personLink.getPersonByLhPersonId().getPersonId());
//
//				String TempStrB = String.valueOf(personLink.getPersonByRhPersonId().getPersonId());
//
//				if(!newLinks.contains(TempStrA)) newLinks.add(TempStrA);
//
//				if(!newLinks.contains(TempStrB)) newLinks.add(TempStrB);
//
//				newLink.add(personLink);
//			}
            //去除自身id

            String TempString = String.valueOf(personFound.getPersonId());

            if (OldLinks.contains(TempString)) {
                OldLinks.remove(TempString);

            }


            if (newLinks.contains(String.valueOf(TempString))) {
                newLinks.remove(TempString);

            }


            int LinkCount = newLinks.size();

            int OldCount = OldLinks.size();

            log.fatal("CUSTOM26:" + person.getCustom26() + " NEWLINK: " + LinkCount + " OLDLINK: " + OldLinks.size());

            log.fatal("NEWLINK: { " + newLinks + " }");

            log.fatal("OldLink: { " + OldLinks + " }");


            //匹配场景，原始记录无LINK信息，更新后也无LINK信息
            if (OldCount == 0 && LinkCount == 0 && person.getCustom26() == null) {
                //System.out.println("匹配场景三，原始匹配无LINK记录，更新后无匹配LINK信息");

                log.fatal("匹配场景三，原始匹配无LINK记录，更新后无匹配LINK信息");

                updatePerson(person, personFound);

            }

            //匹配场景，原始匹配有LINK记录，更新后无匹配LINK信息
            if (OldCount != 0 && LinkCount == 0 && person.getCustom26() != null) {
                //System.out.println("匹配场景四，原始匹配有LINK记录，更新后无匹配LINK信息");

                log.fatal("匹配场景四，原始匹配有LINK记录，更新后无匹配LINK信息");

                //获取globalid,判断是否要生成globalid
                GlobalIdentifier globalId = Context.getConfiguration().getGlobalIdentifier();

                //log.trace("Global Identifier Configuration is " + globalId);

                if (globalId != null && !globalId.isAssignGlobalIdentifier()) {
                    return;
                }

                IdentifierDomainDict globalIdentDomain = globalId.getIdentifierDomain();

                PersonIdentifierEMPI identifier = generateGlobalIdentifier(globalIdentDomain, person);

                log.fatal("NEW EMPI: " + identifier.getIdentifier().toString());

                person.setCustom25(null);

                person.setEmpi(identifier.getIdentifier());

                //新EMPI的检查，是否已存在

                Empi tempempi = new Empi();

                Empi myempi = new Empi();

                tempempi = empiDao.getPersonByEmpi(person);

                if (tempempi != null) {
                    throw new ApplicationException("更新失败：于匹配场景四中，发生新生成的EMPI已存在的情况");
                }


                //PERSON表更新
                NewUpdatePerson(person, personFound);

                //PERSON_IDENTIFIER,PATIENT_VISIT,PATIENT_VISIT_HSITORY表更新
                String ReplaceID = null;

                for (String TempStr : OldLinks) {
                    if (!TempStr.equalsIgnoreCase(String.valueOf(personFound.getPersonId()))) {
                        ReplaceID = TempStr;

                        break;
                    }
                }

                myResult = NewRunProcedure(person, personFound, ReplaceID, 0);

                if (myResult.equalsIgnoreCase("0")) {
                    throw new ApplicationException("更新失败：于匹配场景四中，出现了存储过程执行异常");
                }

                //EMPI新增处理
                if (person.getCustom26().length() > 0 && (person.getCustom25() == null || person.getCustom25() == "")) {
                    myempi = AddEmpiAttribute(person);

                    empiDao.addEmpi(myempi);
                } else {
                    throw new ApplicationException("更新失败：于匹配场景四中，出现了异常的CUSTOM25和CUSTOM26值");
                }

                //EMPI同步XDS，XDSI处理
                //personDao.procedureForXDS(person.getEmpi(),person.getCustom26(),0);

                //重建LINK
                //findAndUpdateRecordLinks(person);
            }

            //匹配场景，原始匹配无LINK记录，更新后有匹配LINK信息
            if (OldCount == 0 && LinkCount > 0 && person.getCustom26() == null) {
                //System.out.println("匹配场景五，原始匹配无LINK记录，更新后有匹配LINK信息");

                log.fatal("匹配场景五，原始匹配无LINK记录，更新后有匹配LINK信息");

                //获取NEWLINKS中非更新属性所属病人ID的关联ID
                String ReplaceID = null;

                for (String TempStr : newLinks) {
                    if (!TempStr.equalsIgnoreCase(String.valueOf(personFound.getPersonId()))) {
                        ReplaceID = TempStr;

                        break;
                    }
                }

                //获取EMPI值
                List<Person> list = personDao.getEmpiById(ReplaceID);

                if (list == null) {
                    throw new ApplicationException("更新失败：于匹配场景五中，从匹配记录中获取EMPI失败");
                }

                String empi = list.get(0).getEmpi();

                //新EMPI的检查，是否已存在

                Empi tempempi = new Empi();

                tempempi = empiDao.getPersonByEmpi(person);

                if (tempempi == null) {
                    throw new ApplicationException("更新失败：于匹配场景五中，发生EMPI记录不存在的情况");
                }

                //System.out.println("NEW EMPI: " + empi);

                log.fatal("NEW EMPI: " + empi);

                person.setCustom25(person.getEmpi());

                person.setEmpi(empi);

                //personFound.setCustom26(person.get);


                //PERSON表更新
                NewUpdatePerson(person, personFound);

                //PERSON_IDENTIFIER,PATIENT_VISIT,PATIENT_VISIT_HSITORY表更新
                myResult = NewRunProcedure(person, personFound, ReplaceID, 1);

                if (myResult.equalsIgnoreCase("0")) {
                    throw new ApplicationException("更新失败：于匹配场景五中，出现了存储过程执行异常");
                }

                //EMPI删除处理
                if (person.getCustom25().length() > 0 && (person.getCustom26() == null || person.getCustom26() == "")) {
                    tempempi.setDateVoided(new java.util.Date());

                    empiDao.updateEmpi(tempempi);
                } else {
                    throw new ApplicationException("更新失败：于匹配场景 五中，出现了异常的CUSTOM25和CUSTOM26值");
                }

                //EMPI同步XDS，XDSI处理
                //personDao.procedureForXDS(person.getEmpi(),person.getCustom25(),1);

                //重建LINK
                //findAndUpdateRecordLinks(person);

            }

            //匹配场景，原始匹配有LINK记录，更新后有匹配LINK信息
            if (OldCount > 0 && LinkCount > 0 && person.getCustom26() != null) {
                //新老LINK的包含关系计算 0:不包含，1：新LINK包含老LINK，2：老LINK包含新LINK，3：等于

                int IsInclude = IsIncludeOldLink(newLinks, OldLinks);

                if (IsInclude == 0) {
                    log.fatal("匹配场景六，原始匹配有LINK记录，更新后有匹配LINK信息，且新记录不包含老记录");

                    //获取NEWLINKS中非更新属性所属病人ID的关联ID
                    String ReplaceID = null;

                    for (String TempStr : newLinks) {
                        if (!TempStr.equalsIgnoreCase(String.valueOf(personFound.getPersonId()))) {
                            ReplaceID = TempStr;

                            break;
                        }
                    }

                    //获取EMPI值
                    List<Person> list = personDao.getEmpiById(ReplaceID);

                    if (list == null) {
                        throw new ApplicationException("更新失败：于匹配场景六中，从匹配记录中获取EMPI失败");
                    }

                    //新EMPI的检查，是否已存在

                    Empi tempempi = new Empi();

                    tempempi = empiDao.getPersonByEmpi(person);

                    if (tempempi == null) {
                        throw new ApplicationException("更新失败：于匹配场景六中，发生EMPI记录不存在的情况");
                    }

                    String empi = list.get(0).getEmpi();

                    //System.out.println("NEW EMPI: " + empi);

                    log.fatal("NEW EMPI: " + empi);

                    person.setCustom25(person.getEmpi());

                    person.setEmpi(empi);

                    //PERSON表更新
                    NewUpdatePerson(person, personFound);

                    //PERSON_IDENTIFIER,PATIENT_VISIT,PATIENT_VISIT_HSITORY表更新
                    myResult = NewRunProcedure(person, personFound, ReplaceID, 1);

                    if (myResult.equalsIgnoreCase("0")) {
                        throw new ApplicationException("更新失败：于匹配场景六中，出现了存储过程执行异常");
                    }

                    //EMPI无动作
                    if (person.getCustom25().length() > 0 && person.getCustom26().length() > 0 && person.getCustom25().equalsIgnoreCase(person.getCustom26())) {
                        System.out.println("于匹配场景六种，EMPI无任何动作处理");

                        log.fatal("于匹配场景六种，EMPI无任何动作处理");
                    } else {
                        throw new ApplicationException("更新失败：于匹配场景 六中，出现了异常的CUSTOM25和CUSTOM26值");
                    }

                    //重建LINK
                    //findAndUpdateRecordLinks(person);

                } else if (IsInclude == 1) {
                    log.fatal("匹配场景七，原始匹配有LINK记录，更新后有匹配LINK信息，且新记录包含老记录");

                    //获取NEWLINKS中非更新属性所属病人ID的关联ID
                    String ReplaceID = null;

                    for (String TempStr : newLinks) {
                        if (!OldLinks.contains(TempStr)) {
                            ReplaceID = TempStr;

                            break;
                        }
                    }

                    //获取EMPI值
                    List<Person> list = personDao.getEmpiById(ReplaceID);

                    if (list == null) {
                        throw new ApplicationException("更新失败：于匹配场景七中，从匹配记录中获取PERSON失败");
                    }

                    //新EMPI的检查，是否已存在

                    Empi tempempi = new Empi();

                    tempempi = empiDao.getPersonByEmpi(person);

                    if (tempempi == null) {
                        throw new ApplicationException("更新失败：于匹配场景七中，发生EMPI记录不存在的情况");
                    }

                    person.setCustom25(person.getEmpi());

                    person.setEmpi(list.get(0).getEmpi());

                    String empi = list.get(0).getEmpi();

                    //System.out.println("NEW EMPI: " + empi);

                    log.fatal("NEW EMPI: " + empi);


                    //根据新EMPI替换所有老的EMPI，删除老EMPI
                    NewRunProcedure(person, personFound, ReplaceID, 2);

//					//EMPI同步XDS，XDSI处理
//					personDao.procedureForXDS(person.getEmpi(),person.getCustom25(),1);
//
//					//重建LINK
//					findAndUpdateRecordLinks(person);

                } else if (IsInclude == 2) {
                    log.fatal("匹配场景八，原始匹配有LINK记录，更新后有匹配LINK信息，且新记录包含于老记录");

                    throw new ApplicationException("异常错误：新记录包含于老记录");
                } else if (IsInclude == 3) {
                    //System.out.println("匹配场景九，原始匹配有LINK记录，更新后有匹配LINK信息，且新记录相等于老记录");

                    log.fatal("匹配场景九，原始匹配有LINK记录，更新后有匹配LINK信息，且新记录相等于老记录");

                    updatePerson(person, personFound);
                }
            }
        }

    }


    /*
     * 比较新旧Link的大小
     * 这个是在做什么？？？
     * 0 新纪录不包含老记录
     * 1 新纪录包含全部老记录
     * 2 老记录包含全部新纪录
     * 3 新纪录全部等于老记录
     */
    public int IsIncludeOldLink(List<String> newlinks, List<String> oldlinks) {
        int IsInclude = 0;

        if (newlinks.size() == oldlinks.size()) {
            IsInclude = 3;

            for (String tempstr : newlinks) {
                if (!oldlinks.contains(tempstr)) {
                    IsInclude = 0;

                    break;
                }
            }
        } else if (newlinks.size() > oldlinks.size()) {
            IsInclude = 1;

            for (String tempstr : oldlinks) {
                if (!newlinks.contains(tempstr)) {
                    IsInclude = 0;

                    break;
                }
            }
        } else if (newlinks.size() < oldlinks.size()) {
            IsInclude = 2;

            for (String tempstr : newlinks) {
                if (!oldlinks.contains(tempstr)) {
                    IsInclude = 0;

                    break;
                }
            }
        }

        return IsInclude;
    }

    public void updatePersonID(Person person, PersonIdentifierEMPI changeIdentifier) throws ApplicationException {
        ValidationService validationService = Context.getValidationService();

        validationService.validate(person);

        Person personFound = findPersonUsingIdentifiers(person);

        if (personFound == null) {
            log.warn("While attempting to update a person was not able to locate a record with the given identifier: " + person);
            throw new ApplicationException("Person record to be updated does not exist in the system.");
        }

        updatePersonForID(personFound, changeIdentifier);

        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.MOIFIY_PERSON_ID_TYPE, "修改病人ID", person, null, null);


    }

    //
    public void TransPerson(Person person, PatientVisit patientvisit, List<Contactperson> contactperson, MessageHeader messageHeader, Message msgIn) throws ApplicationException, HL7Exception {
        MSH temp = (ca.uhn.hl7v2.model.v231.segment.MSH) msgIn.get("MSH");

        String uuid = temp.getMessageControlID().toString();

        java.util.Date now = new java.util.Date();

        TransPerson transpereson = new TransPerson();

        transpereson.setUuid(uuid.toString());

        if (person.getName() != null) transpereson.setName(person.getName());

        if (person.getDateOfBirth() != null) transpereson.setDateOfBirth(person.getDateOfBirth());

        if (person.getBirthPlace() != null) transpereson.setBirthPlace(person.getBirthPlace());

        if (person.getMultipleBirthInd() != null) transpereson.setMultipleBirthInd(person.getMultipleBirthInd());

        if (person.getBirthOrder() != null)
            transpereson.setBirthOrder(new BigDecimal(person.getBirthOrder().toString()));

        if (person.getMothersMaidenName() != null) transpereson.setMothersMaidenName(person.getMothersMaidenName());

        if (person.getSsn() != null) transpereson.setSsn(person.getSsn());

        if (person.getIdentityNo() != null) transpereson.setIdentityNo(person.getIdentityNo());

        if (person.getInsuranceNo() != null) transpereson.setInsuranceNo(person.getInsuranceNo());

        if (person.getInsuranceType() != null) transpereson.setInsuranceType(person.getInsuranceType());

        if (person.getInsuranceName() != null) transpereson.setInsuranceName(person.getInsuranceName());

        if (person.getGenderDict() != null) transpereson.setGenderCd(person.getGenderDict().getGenderCode());

        if (person.getEthnicgroupDict() != null)
            transpereson.setEthnicGroupCd(person.getEthnicgroupDict().getEthnicGroupCode());

        if (person.getRaceDict() != null) transpereson.setRaceCd(person.getRaceDict().getRaceCode());

        if (person.getNationalityDict() != null)
            transpereson.setNationalityCd(person.getNationalityDict().getAtionalityCode());

        if (person.getLanguageDict() != null) transpereson.setLanguageCd(person.getLanguageDict().getLanguageCode());

        if (person.getReligionDict() != null) transpereson.setReligionCd(person.getReligionDict().getReligionCode());

        if (person.getMaritalStatusDict() != null)
            transpereson.setMaritalStatusCd(person.getMaritalStatusDict().getMaritalStatusCode());

        if (person.getDegreeTypeDict() != null) transpereson.setDegree(person.getDegreeTypeDict().getDegreeTypeCode());

        if (person.getEmail() != null) transpereson.setEmail(person.getEmail());

        if (person.getHomeAddress() != null) transpereson.setHomeAddress(person.getHomeAddress());

        if (person.getWorkAddress() != null) transpereson.setWorkAddress(person.getWorkAddress());

        if (person.getCity() != null) transpereson.setCity(person.getCity());

        if (person.getState() != null) transpereson.setState(person.getState());

        if (person.getCountry() != null) transpereson.setCountry(person.getCountry());

        if (person.getCountryCode() != null) transpereson.setCountryCode(person.getCountryCode());

        if (person.getHomeNumber() != null) transpereson.setHomeNumber(person.getHomeNumber());

        if (person.getWorkNumber() != null) transpereson.setWorkNumber(person.getWorkNumber());

        if (person.getDeathInd() != null) transpereson.setDeathInd(person.getDeathInd());

        if (person.getDeathTime() != null) transpereson.setDeathTime(person.getDeathTime().toString());

        transpereson.setDateCreated(now);

        if (person.getCustom10() != null) transpereson.setIdentifierDomainName(person.getCustom10());

        if (person.getCustom11() != null) transpereson.setIdentifierDomainId(person.getCustom11());

        if (person.getCustom12() != null) transpereson.setIdentifierDomainType(person.getCustom12());

        if (person.getCustom16() != null) transpereson.setIdentifierId(person.getCustom16());

        if (person.getCustom15() != null) {
            if (person.getCustom15().indexOf("^") > 1) {
                String TempStr1 = person.getCustom15().substring(0, person.getCustom15().indexOf("^"));

                String TempStr2 = person.getCustom15().substring(person.getCustom15().indexOf("^") + 1);

                transpereson.setRelevanceId(TempStr1);

                transpereson.setRelevanceDomain(TempStr2);

            } else {
                transpereson.setRelevanceId(person.getCustom15());
            }
        }

        if (person.getCustom19() != null) {
            if (person.getCustom19().indexOf("^") > 1) {
                String TempStr1 = person.getCustom19().substring(0, person.getCustom19().indexOf("^"));

                String TempStr2 = person.getCustom19().substring(person.getCustom19().indexOf("^") + 1);

                transpereson.setCustom14(TempStr1);

                transpereson.setCustom4(TempStr2);

            } else {
                transpereson.setCustom14(person.getCustom19());
            }
        }

        if (messageHeader.getTriggerEvent().equalsIgnoreCase("A04") || messageHeader.getTriggerEvent().equalsIgnoreCase("A01")) {
            transpereson.setPersonStatus("01");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A08")) {
            transpereson.setPersonStatus("02");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A02")) {
            transpereson.setPersonStatus("06");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A03")) {
            transpereson.setPersonStatus("08");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A06")) {
            transpereson.setPersonStatus("09");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A07")) {
            transpereson.setPersonStatus("10");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A09")) {
            transpereson.setPersonStatus("11");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A10")) {
            transpereson.setPersonStatus("12");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A11")) {
            transpereson.setPersonStatus("13");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A12")) {
            transpereson.setPersonStatus("14");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A13")) {
            transpereson.setPersonStatus("15");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A21")) {
            transpereson.setPersonStatus("16");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A22")) {
            transpereson.setPersonStatus("17");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A33")) {
            transpereson.setPersonStatus("18");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A32")) {
            transpereson.setPersonStatus("19");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A52")) {
            transpereson.setPersonStatus("20");
        } else if (messageHeader.getTriggerEvent().equalsIgnoreCase("A53")) {
            transpereson.setPersonStatus("21");
        }

        if (person.getRegisteredProvince() != null) transpereson.setRegisteredProvince(person.getRegisteredProvince());

        if (person.getRegisteredCity() != null) transpereson.setRegisteredCity(person.getRegisteredCity());

        if (person.getRegisteredCounty() != null) transpereson.setRegisteredCounty(person.getRegisteredCounty());

        if (person.getRegisteredAddress() != null) transpereson.setRegisteredAddress(person.getRegisteredAddress());

        if (person.getRegisteredZip() != null) transpereson.setRegisteredZip(person.getRegisteredZip());

        if (person.getHomeProvince() != null) transpereson.setHomeProvince(person.getHomeProvince());

        if (person.getHomeCity() != null) transpereson.setHomeCity(person.getHomeCity());

        if (person.getHomeCounty() != null) transpereson.setHomeCounty(person.getHomeCounty());

        if (person.getHomeZip() != null) transpereson.setHomeZip(person.getHomeZip());

        if (person.getPrivateNumber() != null) transpereson.setPrivateNumber(person.getPrivateNumber());

        if (person.getCitizenCard() != null) transpereson.setCitizenCard(person.getCitizenCard());

        if (person.getMedicalCertificate() != null) transpereson.setMedicalCertificate(person.getMedicalCertificate());

        if (person.getMeicarePerson() != null) transpereson.setMedicarePerson(person.getMeicarePerson());

        if (person.getElderCertificate() != null) transpereson.setElderCertificate(person.getElderCertificate());

        if (person.getOpcaseno() != null) transpereson.setOpcaseno(person.getOpcaseno());

        if (person.getCompany() != null) transpereson.setCompany(person.getCompany());

        if (person.getWorkZip() != null) transpereson.setWorkZip(person.getWorkZip());

        if (person.getGuardianPerson() != null) transpereson.setGuardianPerson(person.getGuardianPerson());

        if (person.getBirthProvince() != null) transpereson.setBirthProvince(person.getBirthProvince());

        if (person.getBirthCity() != null) transpereson.setBirthCity(person.getBirthCity());

        if (person.getBirthCounty() != null) transpereson.setBirthCounty(person.getBirthCounty());

        if (person.getBirthZip() != null) transpereson.setBirthZip(person.getBirthZip());

        if (person.getProfessionTypeDict() != null)
            transpereson.setProfession(person.getProfessionTypeDict().getProfessionTypeCode());

        if (person.getNativeProvince() != null) transpereson.setNativeProvince(person.getNativeProvince());

        if (person.getNativeCity() != null) transpereson.setNativeCity(person.getNativeCity());

        if (person.getVip() != null) transpereson.setVip(new BigDecimal(person.getVip()));

        if (person.getNameSpellCode() != null) transpereson.setNameSpellCode(person.getNameSpellCode());

        if (person.getNameWbCode() != null) transpereson.setNameWbCode(person.getNameWbCode());

        if (person.getGenderName() != null) transpereson.setGenderName(person.getGenderName());

        if (person.getMaritalStatusName() != null) transpereson.setMaritalStatusName(person.getMaritalStatusName());

        if (person.getDegreeName() != null) transpereson.setDegreeName(person.getDegreeName());

        if (person.getProfessionName() != null) transpereson.setProfessionName(person.getProfessionName());

        if (person.getNationalityName() != null) transpereson.setNationalityName(person.getNationalityName());

        if (person.getEthnicName() != null) transpereson.setEthnicName(person.getEthnicName());

        if (person.getRaceName() != null) transpereson.setRaceName(person.getRaceName());

        if (person.getGenderDomain() != null) transpereson.setGenderDomain(person.getGenderDomain());

        if (person.getEthincDomain() != null) transpereson.setEthnicDomain(person.getEthincDomain());

        if (person.getRaceDomain() != null) transpereson.setRaceDomain(person.getRaceDomain());

        if (person.getNationalityDomain() != null) transpereson.setNationalityDomain(person.getNationalityDomain());

        if (person.getMaritalDomain() != null) transpereson.setMaritalDomain(person.getMaritalDomain());

        if (person.getDegreeDomain() != null) transpereson.setDegreeDomain(person.getDegreeDomain());

        if (person.getProfessionDomain() != null) transpereson.setProfessionDomain(person.getProfessionDomain());

        if (person.getHealthCard() != null) transpereson.setHealthCard(person.getHealthCard());

        if (person.getAccountLocked() != null) transpereson.setAccountLocked(person.getAccountLocked());

        if (person.getAccountLockedDate() != null) transpereson.setAccountLockedDate(person.getAccountLockedDate());

        if (person.getBirthTime() != null) transpereson.setDateOfBirth(person.getBirthTime());

        if (person.getCardType() != null) transpereson.setCardType(person.getCardType());

        //transpereson.setHiupErrorInfo("注册成功");

        transpereson.setHiupStatus("0");

        transpereson.setHospitalDomainId("2.16.840.1.113883.4.487.2.1");

        transpereson.setHl7Msg(msgIn.toString());

        personDao.SaveTransPerson(transpereson);

        TransPatientVisit transpatientvisit = new TransPatientVisit();

        transpatientvisit.setUuid(uuid.toString());

        if (person.getCustom16() != null) transpatientvisit.setPatientId(person.getCustom16());

        if (patientvisit.getPatientId() != null) transpatientvisit.setVisitFlowId(patientvisit.getPatientId());

        if (person.getName() != null) transpatientvisit.setName(person.getName());

        if (person.getBirthPlace() != null) transpatientvisit.setBirthPlace(person.getBirthPlace());

        if (person.getDateOfBirth() != null) transpatientvisit.setDateOfBirth(person.getDateOfBirth());

        if (person.getSsn() != null) transpatientvisit.setSsn(person.getSsn());

        if (person.getIdentityNo() != null) transpatientvisit.setIdentityNo(person.getIdentityNo());

        if (person.getInsuranceNo() != null) transpatientvisit.setInsuranceNo(person.getInsuranceNo());

        if (person.getGenderDict() != null) transpatientvisit.setGenderCd(person.getGenderDict().getGenderCode());

        if (person.getMaritalStatusDict() != null)
            transpatientvisit.setMaritalStatus(person.getMaritalStatusDict().getMaritalStatusCode());

        if (person.getHomeAddress() != null) transpatientvisit.setHomeAddress(person.getHomeAddress());

        if (person.getWorkAddress() != null) transpatientvisit.setWorkAddress(person.getWorkAddress());

        if (person.getHomeNumber() != null) transpatientvisit.setHomePhone(person.getHomeNumber());

        if (person.getWorkNumber() != null) transpatientvisit.setWorkPhone(person.getWorkNumber());

        if (person.getCustom10() != null) transpatientvisit.setIdentifierDomainName(person.getCustom10());

        if (person.getCustom11() != null) transpatientvisit.setIdentifierDomainId(person.getCustom11());

        if (person.getCustom12() != null) transpatientvisit.setIdentifierDomainType(person.getCustom12());

        if (patientvisit.getCustom2() != null) transpatientvisit.setIdentifierFlowDomainName(patientvisit.getCustom2());

        if (patientvisit.getCustom3() != null) transpatientvisit.setIdentifierFlowDomainId(patientvisit.getCustom3());

        if (patientvisit.getCustom4() != null) transpatientvisit.setIdentifierFlowDomainType(patientvisit.getCustom4());

        if (patientvisit.getPatCategory() != null) transpatientvisit.setPatCategory(patientvisit.getPatCategory());

        if (patientvisit.getPatCurrentPointOfCare() != null)
            transpatientvisit.setPatCurrentPointOfCare(patientvisit.getPatCurrentPointOfCare());

        if (patientvisit.getPatCurrentRoom() != null)
            transpatientvisit.setPatCurrentRoom(patientvisit.getPatCurrentRoom());

        if (patientvisit.getPatCurrentBed() != null)
            transpatientvisit.setPatCurrentBed(patientvisit.getPatCurrentBed());

        if (patientvisit.getPatCuurentDep() != null)
            transpatientvisit.setPatCuurentDep(patientvisit.getPatCuurentDep());

        //transpatientvisit.setPatCurrentDepName(patientvisit.getpatcu);

        if (patientvisit.getPatCurrentPositionStatus() != null)
            transpatientvisit.setPatCurrentPositionStatus(patientvisit.getPatCurrentPositionStatus());

        if (patientvisit.getPatCurrentPositionType() != null)
            transpatientvisit.setPatCurrentPositionType(patientvisit.getPatCurrentPositionType());

        if (patientvisit.getPatCurrentBuilding() != null)
            transpatientvisit.setPatCurrentBuilding(patientvisit.getPatCurrentBuilding());

        if (patientvisit.getPatCurrentFloor() != null)
            transpatientvisit.setPatCurrentFloor(patientvisit.getPatCurrentFloor());

        if (patientvisit.getPatCuurentDescription() != null)
            transpatientvisit.setPatCuurentDescription(patientvisit.getPatCuurentDescription());

        if (patientvisit.getPatAdmissionType() != null)
            transpatientvisit.setPatAdmissionType(patientvisit.getPatAdmissionType());

        if (patientvisit.getPatAdmissionNumber() != null)
            transpatientvisit.setPatAdmissionNumber(patientvisit.getPatAdmissionNumber());

        if (patientvisit.getAdmissionsDoctor() != null)
            transpatientvisit.setAdmissionsDoctor(patientvisit.getAdmissionsDoctor());

        if (patientvisit.getAdmissionsDoctorId() != null)
            transpatientvisit.setAdmissionsDoctorId(patientvisit.getAdmissionsDoctorId());

        if (patientvisit.getReferringDoctor() != null)
            transpatientvisit.setReferringDoctor(patientvisit.getReferringDoctor());

        if (patientvisit.getReferringDoctorId() != null)
            transpatientvisit.setReferringDoctorId(patientvisit.getReferringDoctorId());

        if (patientvisit.getConsultationDoctor() != null)
            transpatientvisit.setConsultationDoctor(patientvisit.getConsultationDoctor());

        if (patientvisit.getConsultationDoctorId() != null)
            transpatientvisit.setConsultationDoctorId(patientvisit.getConsultationDoctorId());

        if (patientvisit.getHospitalService() != null)
            transpatientvisit.setHospitalService(patientvisit.getHospitalService());

        if (patientvisit.getPatAdmissionTest() != null)
            transpatientvisit.setPatAdmissionTest(patientvisit.getPatAdmissionTest());

        if (patientvisit.getPatIpTimes() != null) transpatientvisit.setPatIptimes(patientvisit.getPatIpTimes());

        if (patientvisit.getPatAdmissionSource() != null)
            transpatientvisit.setPatAdmissionSource(patientvisit.getPatAdmissionSource());

        if (patientvisit.getPatAmbulatoryStatus() != null)
            transpatientvisit.setPatAmbulatoryStatus(patientvisit.getPatAmbulatoryStatus());

        if (patientvisit.getPatVip() != null) transpatientvisit.setPatVip(patientvisit.getPatVip());

        if (patientvisit.getPatAdmissionDoctors() != null)
            transpatientvisit.setPatAdmissionDoctors(patientvisit.getPatAdmissionDoctors());

        if (patientvisit.getPatAdmissionDoctorsId() != null)
            transpatientvisit.setPatAdmissionDoctorsId(patientvisit.getPatAdmissionDoctorsId());

        if (patientvisit.getPatientClass() != null) transpatientvisit.setPatientClass(patientvisit.getPatientClass());

        if (patientvisit.getPatientId() != null) transpatientvisit.setPatientFlowId(patientvisit.getPatientId());

        if (patientvisit.getPatDischargeDisposition() != null)
            transpatientvisit.setPatDischargeDisposition(patientvisit.getPatDischargeDisposition());

        if (patientvisit.getPatDischargeLocation() != null)
            transpatientvisit.setPatDischargeLocation(patientvisit.getPatDischargeLocation());

        if (patientvisit.getPatDietType() != null) transpatientvisit.setPatDietType(patientvisit.getPatDietType());

        if (patientvisit.getPatServiceAgencies() != null)
            transpatientvisit.setPatServiceAgencies(patientvisit.getPatServiceAgencies());

        if (patientvisit.getPatBedStatus() != null) transpatientvisit.setPatBedStatus(patientvisit.getPatBedStatus());

        if (patientvisit.getPatAccountStatus() != null)
            transpatientvisit.setPatAccountStatus(patientvisit.getPatAccountStatus());

        if (patientvisit.getPatNurseCode() != null) transpatientvisit.setPatNurseCode(patientvisit.getPatNurseCode());

        if (patientvisit.getPatNurseName() != null) transpatientvisit.setPatNurseName(patientvisit.getPatNurseName());

        if (patientvisit.getTend() != null) transpatientvisit.setTend(patientvisit.getTend());

        //transpatientvisit.setPatDieteticMark(patientvisit.getPatDieteticMark());

        if (patientvisit.getPatReAdmission() != null)
            transpatientvisit.setPatReAdmission(patientvisit.getPatReAdmission());

        if (patientvisit.getPatDischargeDisposition() != null)
            transpatientvisit.setPatDischargeCode(patientvisit.getPatDischargeDisposition());

        if (patientvisit.getPatDeterPointOfCare() != null)
            transpatientvisit.setPatDeterPointOfCare(patientvisit.getPatDeterPointOfCare());

        if (patientvisit.getPatDeterRoom() != null) transpatientvisit.setPatDeterRoom(patientvisit.getPatDeterRoom());

        if (patientvisit.getPatDeterBed() != null) transpatientvisit.setPatDeterBed(patientvisit.getPatDeterBed());

        if (patientvisit.getPatDeterDep() != null) transpatientvisit.setPatDeterDep(patientvisit.getPatDeterDep());

        if (patientvisit.getPatDeterPositionStatus() != null)
            transpatientvisit.setPatDeterPositionStatus(patientvisit.getPatDeterPositionStatus());

        if (patientvisit.getPatDeterPositionType() != null)
            transpatientvisit.setPatDeterPositionType(patientvisit.getPatDeterPositionType());

        if (patientvisit.getPatDeterBuilding() != null)
            transpatientvisit.setPatDeterBuilding(patientvisit.getPatDeterBuilding());

        if (patientvisit.getPatDeterFloor() != null)
            transpatientvisit.setPatDeterFloor(patientvisit.getPatDeterFloor());

        if (patientvisit.getPatDeterDescription() != null)
            transpatientvisit.setPatDeterDescription(patientvisit.getPatDeterDescription());

        if (patientvisit.getCourtesyCode() != null)
            transpatientvisit.setPatIpstatuscode(patientvisit.getCourtesyCode());

        if (patientvisit.getCreditRating() != null)
            transpatientvisit.setPatDifficultyLevelcode(patientvisit.getCreditRating());

        if (patientvisit.getBabyFlag() != null) transpatientvisit.setBabyFlag(patientvisit.getBabyFlag());

        if (patientvisit.getAdmitWeight() != null) transpatientvisit.setAdmitWeight(patientvisit.getAdmitWeight());

        if (patientvisit.getBirthWeight() != null) transpatientvisit.setBirthWeight(patientvisit.getBirthWeight());

        if (patientvisit.getPatFormerPointOfCare() != null)
            transpatientvisit.setPatFormerPointOfCare(patientvisit.getPatFormerPointOfCare());

        if (patientvisit.getPatFormerRoom() != null)
            transpatientvisit.setPatFormerRoom(patientvisit.getPatFormerRoom());

        if (patientvisit.getPatFormerBed() != null) transpatientvisit.setPatFormerBed(patientvisit.getPatFormerBed());

        if (patientvisit.getPatFormerDep() != null) transpatientvisit.setPatFormerDep(patientvisit.getPatFormerDep());

        if (patientvisit.getPatFormerPositionStatus() != null)
            transpatientvisit.setPatFormerPositionStatus(patientvisit.getPatFormerPositionStatus());

        if (patientvisit.getPatFormerPositionType() != null)
            transpatientvisit.setPatFormerPositionType(patientvisit.getPatFormerPositionType());

        if (patientvisit.getPatFormerBuilding() != null)
            transpatientvisit.setPatFormerBuilding(patientvisit.getPatFormerBuilding());

        if (patientvisit.getPatFormerFloor() != null)
            transpatientvisit.setPatFormerFloor(patientvisit.getPatFormerFloor());

        if (patientvisit.getPatFormerDescription() != null)
            transpatientvisit.setPatFormerDescription(patientvisit.getPatFormerDescription());

        if (patientvisit.getPatTempPointOfCare() != null)
            transpatientvisit.setPatTempPointOfCare(patientvisit.getPatTempPointOfCare());

        if (patientvisit.getPatTempRoom() != null) transpatientvisit.setPatTempRoom(patientvisit.getPatTempRoom());

        if (patientvisit.getPatTempBed() != null) transpatientvisit.setPatTempBed(patientvisit.getPatTempBed());

        if (patientvisit.getPatTempDep() != null) transpatientvisit.setPatTempDep(patientvisit.getPatTempDep());

        if (patientvisit.getPatTempPositionStatus() != null)
            transpatientvisit.setPatTempPositionStatus(patientvisit.getPatTempPositionStatus());

        if (patientvisit.getPatTempPositionType() != null)
            transpatientvisit.setPatTempPositionType(patientvisit.getPatTempPositionType());

        if (patientvisit.getPatTempBuilding() != null)
            transpatientvisit.setPatTempBuilding(patientvisit.getPatTempBuilding());

        if (patientvisit.getPatTempFloor() != null) transpatientvisit.setPatTempFloor(patientvisit.getPatTempFloor());

        if (patientvisit.getPatTempDescription() != null)
            transpatientvisit.setPatTempDescription(patientvisit.getPatTempDescription());

        if (patientvisit.getPatForTempPointOfCare() != null)
            transpatientvisit.setPatForTempPointOfCare(patientvisit.getPatForTempPointOfCare());

        if (patientvisit.getPatForTempRoom() != null)
            transpatientvisit.setPatForTempRoom(patientvisit.getPatForTempRoom());

        if (patientvisit.getPatForTempBed() != null)
            transpatientvisit.setPatForTempBed(patientvisit.getPatForTempBed());

        if (patientvisit.getPatForTempDep() != null)
            transpatientvisit.setPatForTempDep(patientvisit.getPatForTempDep());

        if (patientvisit.getPatForTempPositionStatus() != null)
            transpatientvisit.setPatForTempPositionStatus(patientvisit.getPatForTempPositionStatus());

        if (patientvisit.getPatForTempPositionType() != null)
            transpatientvisit.setPatForTempPositionType(patientvisit.getPatForTempPositionType());

        if (patientvisit.getPatForTempBuilding() != null)
            transpatientvisit.setPatForTempBuilding(patientvisit.getPatForTempBuilding());

        if (patientvisit.getPatForTempFloor() != null)
            transpatientvisit.setPatForTempFloor(patientvisit.getPatForTempFloor());

        if (patientvisit.getPatForTempDescription() != null)
            transpatientvisit.setPatForTempDescription(patientvisit.getPatForTempDescription());

        if (patientvisit.getOperCode() != null) transpatientvisit.setOperCode(patientvisit.getOperCode());

        if (patientvisit.getOperDate() != null) transpatientvisit.setOperDate(patientvisit.getOperDate());


        if (patientvisit.getAdmitDate() != null) transpatientvisit.setAdmitDate(patientvisit.getAdmitDate());

        if (patientvisit.getDischargeDate() != null)
            transpatientvisit.setDischargeDate(patientvisit.getDischargeDate());

        transpatientvisit.setCreateDate(now);

        if (patientvisit.getPrefix() != null) transpatientvisit.setPrefix(patientvisit.getPrefix());

        if (person.getInsuranceType() != null) transpatientvisit.setInsuranceType(person.getInsuranceType());

        if (contactperson.size() >= 1) {

            if (contactperson.get(0).getContactName() != null)
                transpatientvisit.setContactPerson(contactperson.get(0).getContactName());

            if (contactperson.get(0).getContactType() != null)
                transpatientvisit.setContactRelations(contactperson.get(0).getContactType());

            if (contactperson.get(0).getContactAddress() != null)
                transpatientvisit.setContactAddress(contactperson.get(0).getContactAddress());

            if (contactperson.get(0).getContactPhone() != null)
                transpatientvisit.setContactPhone(contactperson.get(0).getContactPhone());
        }

        if (patientvisit.getPatCategoryName() != null)
            transpatientvisit.setPatCategoryName(patientvisit.getPatCategoryName());

        if (patientvisit.getGenderName() != null) transpatientvisit.setGenderName(patientvisit.getGenderName());

        if (patientvisit.getPayRate() != null) transpatientvisit.setPayRate(patientvisit.getPayRate());

        if (patientvisit.getDischargeName() != null)
            transpatientvisit.setDischargeName(patientvisit.getDischargeName());

        if (person.getInsuranceName() != null) transpatientvisit.setInsuranceName(person.getInsuranceName());

        if (patientvisit.getAdmissionName() != null)
            transpatientvisit.setAdmissionName(patientvisit.getAdmissionName());

        if (patientvisit.getIpStatusName() != null) transpatientvisit.setIpStatusName(patientvisit.getIpStatusName());

        if (patientvisit.getDificultyName() != null)
            transpatientvisit.setDificultyName(patientvisit.getDificultyName());

        if (patientvisit.getAdmitWayName() != null) transpatientvisit.setAdmitWayName(patientvisit.getAdmitWayName());

        if (patientvisit.getAdmitWeightUnit() != null)
            transpatientvisit.setAdmitWeightUnit(patientvisit.getAdmitWeightUnit());

        if (patientvisit.getBabyWeightUnit() != null)
            transpatientvisit.setBabyWeightUnit(patientvisit.getBabyWeightUnit());

        if (patientvisit.getAdmissionDomain() != null)
            transpatientvisit.setAdmissionDomain(patientvisit.getAdmissionDomain());

        if (patientvisit.getAdmissionSourceDomain() != null)
            transpatientvisit.setAdmissionSourceDomain(patientvisit.getAdmissionSourceDomain());

        if (patientvisit.getAdmissionSourceName() != null)
            transpatientvisit.setAdmissionSourceName(patientvisit.getAdmissionSourceName());

        if (patientvisit.getPatientClassName() != null)
            transpatientvisit.setPatientClassName(patientvisit.getPatientClassName());

        if (patientvisit.getPatientClassDomain() != null)
            transpatientvisit.setPatientClassDomain(patientvisit.getPatientClassDomain());

        if (patientvisit.getIpStatusName() != null)
            transpatientvisit.setIpStatusDomain(patientvisit.getIpStatusDomain());

        if (patientvisit.getDificultyDomain() != null)
            transpatientvisit.setDificultyDomain(patientvisit.getDificultyDomain());

        if (patientvisit.getDischargeDomain() != null)
            transpatientvisit.setDischargeDomain(patientvisit.getDischargeDomain());

        if (patientvisit.getAccountStatusName() != null)
            transpatientvisit.setAccountStatusName(patientvisit.getAccountStatusName());

        if (patientvisit.getAccountStatusDomain() != null)
            transpatientvisit.setAccountStatusDomain(patientvisit.getAccountStatusDomain());

        if (patientvisit.getGenderDomain() != null) transpatientvisit.setGenderDomain(patientvisit.getGenderDomain());

        transpatientvisit.setHiupStatus("0");

        //transpatientvisit.setHiupErrorInfo("注册成功");

        if (person.getHomeNumber() != null) transpatientvisit.setHomePhone(person.getHomeNumber());

        if (person.getWorkNumber() != null) transpatientvisit.setWorkPhone(person.getWorkNumber());

        if (patientvisit.getPatCategorySystem() != null)
            transpatientvisit.setPatCategorySystem(patientvisit.getPatCategorySystem());

        if (patientvisit.getInterestRateCode() != null)
            transpatientvisit.setPayRate(patientvisit.getInterestRateCode());

        transpatientvisit.setHospitalDomainId("2.16.840.1.113883.4.487.2.1");

        if (patientvisit.getPatientSourceName() != null)
            transpatientvisit.setPatientSourceName(patientvisit.getPatientSourceName());

        if (patientvisit.getOldPatientId() != null) transpatientvisit.setOldPatientId(patientvisit.getOldPatientId());

        if (patientvisit.getOldPatientDomain() != null)
            transpatientvisit.setOldPatientDomain(patientvisit.getOldPatientDomain());

        if (patientvisit.getOldVisitFlowId() != null)
            transpatientvisit.setOldVisitFlowId(patientvisit.getOldVisitFlowId());

        if (patientvisit.getOldVisitFlowDomain() != null)
            transpatientvisit.setOldVisitFlowDomain(patientvisit.getOldVisitFlowDomain());

        if (patientvisit.getOldStatus() != null) transpatientvisit.setOldStatus(patientvisit.getOldStatus());

        if (patientvisit.getOldInfo() != null) transpatientvisit.setOldInfo(patientvisit.getOldInfo());

        if (patientvisit.getIsEmergency() != null) transpatientvisit.setIsEmergency(patientvisit.getIsEmergency());

        if (patientvisit.getDiagnoseIcd() != null) transpatientvisit.setDiagnoseIcd(patientvisit.getDiagnoseIcd());

        if (patientvisit.getDiagnoseName() != null) transpatientvisit.setDiagnoseName(patientvisit.getDiagnoseName());

        if (patientvisit.getNoonCode() != null) transpatientvisit.setNoonCode(patientvisit.getNoonCode());

        if (patientvisit.getPaykindCode() != null) transpatientvisit.setPaykindCode(patientvisit.getPaykindCode());

        if (patientvisit.getPaykindName() != null) transpatientvisit.setPaykindName(patientvisit.getPaykindName());

        if (patientvisit.getSchemaNo() != null) transpatientvisit.setSchemaNo(patientvisit.getSchemaNo());

        if (patientvisit.getOrderNo() != null) transpatientvisit.setOrderNo(patientvisit.getOrderNo());

        if (patientvisit.getSeeNo() != null) transpatientvisit.setSeeNo(patientvisit.getSeeNo());

        if (patientvisit.getBeginTime() != null) transpatientvisit.setBeginTime(patientvisit.getBeginTime());

        if (patientvisit.getEndTime() != null) transpatientvisit.setEndTime(patientvisit.getEndTime());

        if (patientvisit.getYnBook() != null) transpatientvisit.setYnBook(patientvisit.getYnBook());

        if (patientvisit.getYNFR() != null) transpatientvisit.setYNFR(patientvisit.getYNFR());

        if (patientvisit.getAppendFlag() != null) transpatientvisit.setAppendFlag(patientvisit.getAppendFlag());

        if (patientvisit.getYnSee() != null) transpatientvisit.setYnSee(patientvisit.getYnSee());

        if (patientvisit.getSeeDate() != null) transpatientvisit.setSeeDate(patientvisit.getSeeDate());

        if (patientvisit.getTriageFlag() != null) transpatientvisit.setTriageFlag(patientvisit.getTriageFlag());

        if (patientvisit.getTriageOpcd() != null) transpatientvisit.setTriageOpcd(patientvisit.getTriageOpcd());

        if (patientvisit.getTriageDate() != null) transpatientvisit.setTriageDate(patientvisit.getTriageDate());

        if (patientvisit.getSeeDpcd() != null) transpatientvisit.setSeeDpcd(patientvisit.getSeeDpcd());

        if (patientvisit.getSeeDocd() != null) transpatientvisit.setSeeDocd(patientvisit.getSeeDocd());

        if (patientvisit.getOutPatientStatusA() != null)
            transpatientvisit.setOutPatientStatusA(patientvisit.getOutPatientStatusA());

        if (patientvisit.getOutPatientStatusB() != null)
            transpatientvisit.setOutPatientStatusB(patientvisit.getOutPatientStatusB());

        if (patientvisit.getOutPatientStatusC() != null)
            transpatientvisit.setOutPatientStatusC(patientvisit.getOutPatientStatusC());

        if (patientvisit.getInPatientStatusA() != null)
            transpatientvisit.setInPatientStatusA(patientvisit.getInPatientStatusA());

        if (patientvisit.getInPatientStatusB() != null)
            transpatientvisit.setInPatientStatusB(patientvisit.getInPatientStatusB());

        if (patientvisit.getInPatientStatusC() != null)
            transpatientvisit.setInPatientStatusC(patientvisit.getInPatientStatusC());


        if (transpatientvisit.getVisitFlowId() != null) {
            patientvisitDao.SaveTransPatientVisit(transpatientvisit);
        }

        log.fatal("推送成功");
    }

    //Change the person 2012-11-23 panmin
    public void ChangePerson(Person person, PatientVisit patientvisit, PatientVisit visitFound, List<String> PersonInfo) throws ApplicationException {

        Person personFound = person;//findPersonUsingIdentifiers(person);

        if (personFound != null && visitFound != null) {

            if (patientvisit != null) {
                updatePatientVisit(patientvisit, personFound);

                SavePatientVisitHistory(visitFound, personFound);

                SavePersonInOut(patientvisit, visitFound, personFound, PersonInfo);
            }

            Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.MODFIY_PERSON_PV1_TYPE, person.getCustom29() + "病人就诊状态更新", person, PersonInfo);

        } else {
            log.warn("PID.4.1与PV1.19.1的流水号不匹配");

            throw new ApplicationException("PID.4.1与PV1.19.1的流水号不匹配");
        }
    }


    public Person getPerson(List<PersonIdentifierEMPI> personIdentifiers) {
        ValidationService validationService = Context.getValidationService();
        for (PersonIdentifierEMPI identifier : personIdentifiers) {
            validationService.validate(identifier);

            Person personFound = personDao.getPersonById(identifier);
            if (personFound != null) {
                return personFound;
            }
        }
        return null;
    }

    /**
     * Imports person into the system.
     * 1. First it attempts to locate the person in the system using the persons identifiers. If the person is already in
     * the system then there is nothing more to do.
     * 2. Since the person doesn't exist in the system yet, a new record is added.
     */
    public Person importPerson(Person person) throws ApplicationException {

        ValidationService validationService = Context.getValidationService();
        validationService.validate(person);

        Person personFound = findPersonUsingIdentifiers(person);
        if (personFound != null) {
            log.warn("While attempting to add a person, found an existing record with same identifier: " + person);
            throw new ApplicationException("Person record to be added already exists in the system.");
        }

        // Before we save the entry we need to generate any custom
        // fields that have been requested through configuration
        populateCustomFields(person);

        savePerson(person);
        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.IMPORT_PERSON_EVENT_TYPE, "Imported person record.", person, null, null);
        return person;
    }

    public IdentifierDomainAttribute addIdentifierDomainAttribute(IdentifierDomainDict identifierDomain, String attributeName, String attributeValue) {

        ValidationService validationService = Context.getValidationService();
        validationService.validate(identifierDomain);

        IdentifierDomainAttribute attribute = personDao.addIdentifierDomainAttribute(identifierDomain, attributeName, attributeValue);

        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.ADD_IDENTIFIER_DOMAIN_ATTRIBUTE_EVENT_TYPE, "Added attribute " + attributeName + " to identifier domain " + identifierDomain.toString());

        return attribute;
    }

    public void updateIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute) {

        ValidationService validationService = Context.getValidationService();
        validationService.validate(identifierDomainAttribute);

        personDao.updateIdentifierDomainAttribute(identifierDomainAttribute);

        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.UPDATE_IDENTIFIER_DOMAIN_ATTRIBUTE_EVENT_TYPE, "Updated attribute " + identifierDomainAttribute.getAttributeName() +
                " of identifier domain with ID " + identifierDomainAttribute.getIdentifierDomainId());
    }

    public void removeIdentifierDomainAttribute(IdentifierDomainAttribute identifierDomainAttribute) {

        ValidationService validationService = Context.getValidationService();
        validationService.validate(identifierDomainAttribute);

        personDao.removeIdentifierDomainAttribute(identifierDomainAttribute);
        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.DELETE_IDENTIFIER_DOMAIN_ATTRIBUTE_EVENT_TYPE, "Deleted attribute " + identifierDomainAttribute.getAttributeName() +
                " of identifier domain with ID " + identifierDomainAttribute.getIdentifierDomainId());
    }

    public IdentifierDomainDict obtainUniqueIdentifierDomain(String universalIdentifierTypeCode) {
        boolean isKnown = personDao.isKnownUniversalIdentifierTypeCode(universalIdentifierTypeCode);
        log.trace("The universlIdentifierTypeCode " + universalIdentifierTypeCode + " is known to the system exptresson is " + isKnown);
        if (!isKnown) {
            throw new ValidationException("The universalIdentifierTypeCode " + universalIdentifierTypeCode + " is not known to the system.");
        }
        IdentifierDomainDict identifierDomain = generateIdentifierDomainForUniversalIdentifierTypeCode(universalIdentifierTypeCode);
        personDao.addIdentifierDomain(identifierDomain);
        log.trace("Created new identifier domain " + identifierDomain);
        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.OBTAIN_UNIQUE_IDENTIFIER_DOMAIN_EVENT_TYPE, "Obtained unique identifier domain type for type code " + universalIdentifierTypeCode);
        return identifierDomain;
    }

    public void initializeRepository() throws ApplicationException {
        log.trace("Initialized the repository from the beginning using the underlying matching algorithm to do so.");
        Context.getMatchingService().initializeRepository();
    }

    private Person findPersonUsingIdentifiers(Person person) {
        Set<PersonIdentifierEMPI> identifiers = person.getPersonIdentifiers();
        for (PersonIdentifierEMPI identifier : identifiers) {
            Person personFound = personDao.getPersonById(identifier);
            if (personFound != null) {
                return personFound;
            }
        }
        return null;
    }

    private Person findPersonUsingIdentifiers2(Person person) {
        Set<PersonIdentifierEMPI> identifiers = person.getPersonIdentifiers();
        for (PersonIdentifierEMPI identifier : identifiers) {
            Person personFound = personDao.getPersonById2(identifier);
            if (personFound != null) {
                return personFound;
            }
        }
        return null;
    }

    /**
     * 根据病人id查询该病人是否已经注册----2018yrh
     *
     * @param identifier
     * @return
     */
    public Person findByPatientId(PersonIdentifierEMPI identifier) {
        if (identifier != null) {
            try {
                Person personFound = personDao.getPersonById2(identifier);
                return personFound;
            } catch (Exception e) {
                log.error("dao层personDao.getPersonById2方法出错");
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * queryPatients
     *
     * @param person
     * @return
     */
    public List<NewPerson> queryPatients(Person person) throws Exception {
        List<NewPerson> personList;

        log.info("dao层queryPatients开始查询");
        personList = personDao.queryPersonByParam(person);
        if (CollectionUtils.isEmpty(personList))
            log.error("没有查询出患者信息");
        else
            log.info("查询出" + personList.size() + "条患者信息");

        return personList;
    }

    private PatientVisit findVisitUsingIdentifiers(Person person) {
        PatientVisit visitFound = patientvisitDao.getPatientVisitById(person);

        if (visitFound != null) {
            return visitFound;
        }

        return null;
    }


    /**
     * 查询患者ADT信息-----20181213
     * @param person
     * @return
     */
    public PatientVisit queryPatientVisit(Person person){
            return this.findVisitUsingIdentifiers(person);
    }

    private void saveMergeEvent(Person survivingPerson, Person retiringPerson, int controlID) {
        java.util.Date now = new java.util.Date();

        MergeEvent myMergeEvent = new MergeEvent();

        myMergeEvent.setEventUUID(UUID.randomUUID().toString());

        if (controlID == 1) {

            myMergeEvent.setOldPersonID(String.valueOf(retiringPerson.getPersonId()) + "^" + String.valueOf(survivingPerson.getPersonId()));

            myMergeEvent.setOldPatientID(retiringPerson.getCustom16() + "^" + survivingPerson.getCustom16());

            myMergeEvent.setMergeType("MERGE_A40");

            myMergeEvent.setMergeStatus("1");

            myMergeEvent.setMergeDate(now);
        } else if (controlID == 2) {
            myMergeEvent.setOldPersonID(String.valueOf(retiringPerson.getPersonId()));

            myMergeEvent.setOldPatientID(retiringPerson.getCustom16());

            myMergeEvent.setOldPatientDomain(retiringPerson.getCustom11());

            if (retiringPerson.getGivenName() != null) myMergeEvent.setOldName(retiringPerson.getGivenName());

            if (retiringPerson.getIdentityNo() != null) myMergeEvent.setOldIdentityNO(retiringPerson.getIdentityNo());

            myMergeEvent.setOldEmpi(survivingPerson.getEmpi());

            myMergeEvent.setNewEmpi(retiringPerson.getEmpi());

            myMergeEvent.setMergeType("MERGE_CANCEL");

            myMergeEvent.setMergeStatus("1");

            myMergeEvent.setMergeDate(now);
        }

        personDao.addMergeEvent(myMergeEvent);

    }

    private void savePerson(Person person) {
        log.debug("Current user is " + Context.getUserContext().getUser());

        java.util.Date now = new java.util.Date();

        person.setDateCreated(now);

        person.setAppUserByCreatorId(Context.getUserContext().getUser());

        person.setDateChanged(now);

        person.setAppUserByChangedById(Context.getUserContext().getUser());

        /*20170524-0035156-start */
        Person personDb = findPersonUsingIdentifiers2(person);
        if (personDb == null) {
            personDao.addPerson(person);
        } else {
            person = personDb;
        }
        /*20170524-0035156-end */

        Empi tempempi = new Empi();

        Empi myempi = new Empi();

        if (person.getCustom15() != null) {
            String TempStr1 = person.getCustom15().substring(0, person.getCustom15().indexOf("^"));

            String TempStr2 = person.getCustom15().substring(person.getCustom15().indexOf("^") + 1);

            addRelevanceID(person, TempStr1, TempStr2);
        }

        if (person.getCustom19() != null) {
            String TempStr1 = person.getCustom19().substring(0, person.getCustom19().indexOf("^"));

            String TempStr2 = person.getCustom19().substring(person.getCustom19().indexOf("^") + 1);

            addRelevanceID(person, TempStr1, TempStr2);
        }

        if (person.getCustom20() != null) {
            String TempStr1 = person.getCustom20().substring(0, person.getCustom20().indexOf("^"));

            String TempStr2 = person.getCustom20().substring(person.getCustom20().indexOf("^") + 1);

            addRelevanceID(person, TempStr1, TempStr2);
        }

        if (person.getCustom22() != null) {
            String TempStr1 = person.getCustom22().substring(0, person.getCustom22().indexOf("^"));

            String TempStr2 = person.getCustom22().substring(person.getCustom22().indexOf("^") + 1);

            addRelevanceID(person, TempStr1, TempStr2);
        }

        if (person.getCustom24() != null) {
            String TempStr1 = person.getCustom24().substring(0, person.getCustom24().indexOf("^"));

            String TempStr2 = person.getCustom24().substring(person.getCustom24().indexOf("^") + 1);

            addRelevanceID(person, TempStr1, TempStr2);
        }

        if (person.getCustom25() != null) {
            String TempStr1 = person.getCustom25().substring(0, person.getCustom25().indexOf("^"));

            String TempStr2 = person.getCustom25().substring(person.getCustom25().indexOf("^") + 1);

            addRelevanceID(person, TempStr1, TempStr2);
        }

        tempempi = empiDao.getPersonByEmpi(person);

        if (tempempi == null) {
            myempi = AddEmpiAttribute(person);

            empiDao.addEmpi(myempi);
        } else if (tempempi != null) {
            myempi = updateEmpiAttribute(person, tempempi);

            empiDao.updateEmpi(myempi);
        }


    }

    private void addRelevanceID(Person person, String relevanceID, String relevanceDomain) {
        String myRelevanceName = personDao.findRelevanceRecord(relevanceID, relevanceDomain);

        java.util.Date now = new java.util.Date();

        RelevanceAndId myRelevanceAndId = new RelevanceAndId();

        if (myRelevanceName != null && person.getGivenName() != null) {
            if (myRelevanceName.equalsIgnoreCase(person.getGivenName())) {
                myRelevanceAndId.setRegId(person.getCustom16());

                myRelevanceAndId.setRegDomain(person.getCustom11());

                myRelevanceAndId.setRegName(person.getGivenName());

                myRelevanceAndId.setRelevanceId(relevanceID);

                myRelevanceAndId.setRelevanceDomain(relevanceDomain);

                myRelevanceAndId.setRelevanceName(myRelevanceName);

                //myRelevanceAndId.setEmpi(person.getEmpi());

                myRelevanceAndId.setDateCreated(now);

                personDao.addRelevanceRecord(myRelevanceAndId);
            }
        }
    }

    private Empi AddEmpiAttribute(Person person) {
        Empi myempi = new Empi();

        if (person.getEmpi() != null) {
            myempi.setEmpi(person.getEmpi());
        }

        if (person.getGivenName() != null) {
            myempi.setName(person.getGivenName());
        }

        //生日
        if (person.getDateOfBirth() != null) {
            myempi.setDateOfBirth(person.getDateOfBirth());
        }

        //出生地所在地的省
        if (person.getBirthProvince() != null) {
            myempi.setBirthProvince(person.getBirthProvince());
        }

        //出生地所在地的市
        if (person.getBirthCity() != null) {
            myempi.setBirthCity(person.getBirthCity());
        }

        //出生地所在区县
        if (person.getBirthCounty() != null) {
            myempi.setBirthCounty(person.getBirthCounty());
        }

        //出生地
        if (person.getBirthPlace() != null) {
            myempi.setBirthPlace(person.getBirthPlace());
        }

        //出生地所在地邮编
        if (person.getBirthZip() != null) {
            myempi.setBirthZip(person.getBirthZip());
        }

        //多胞胎标志
        if (person.getMultipleBirthInd() != null) {
            myempi.setMultipleBirthInd(person.getMultipleBirthInd());
        }

        //出生顺序
        if (person.getBirthOrder() != null) {
            myempi.setBirthOrder(person.getBirthOrder().toString());
        }

        //母亲娘家姓
        if (person.getMothersMaidenName() != null) {
            myempi.setMothersMaidenName(person.getMothersMaidenName());
        }

        //社会保险号
        if (person.getSsn() != null) {
            myempi.setSsn(person.getSsn());
        }


        //身份证号identity_no
        if (person.getIdentityNo() != null) {
            myempi.setIdentityNo(person.getIdentityNo());
        }

        //市民卡号
        if (person.getCitizenCard() != null) {
            myempi.setCitizenCard(person.getCitizenCard());
        }

        //医疗证号
        if (person.getMedicalCertificate() != null) {
            myempi.setMedicalCertificate(person.getMedicalCertificate());
        }

        //医保个人编号
        if (person.getMeicarePerson() != null) {
            myempi.setMeicarePerson(person.getMeicarePerson());
        }

        //老人证号
        if (person.getElderCertificate() != null) {
            myempi.setElderCertificate(person.getElderCertificate());
        }

        //病历号
        if (person.getOpcaseno() != null) {
            myempi.setOpcaseno(person.getOpcaseno());
        }

        //健康卡号
        if (person.getHealthCard() != null) {
            myempi.setHealthCard(person.getHealthCard());
        }

        //获取医保号信息填入
        if (person.getInsuranceNo() != null) {
            myempi.setInsuranceNo(person.getInsuranceNo());
        }

        //获取得到的性别信息填充入对应的person实体类
        if (person.getGenderDict() != null) {
            myempi.setGenderCd(person.getGenderDict().getGenderCode());
        }

        //性别名称
        if (person.getGenderName() != null) {
            myempi.setGenderName(person.getGenderName());
        }

        //性别编码系统
        if (person.getGenderDomain() != null) {
            myempi.setGenderDomain(person.getGenderDomain());
        }

        //获取得到的民族信息填充入对应的person实体类
        if (person.getEthnicgroupDict() != null) {
            myempi.setEthnicGroupCd(person.getEthnicgroupDict().getEthnicGroupCode());
        }

        //民族名称
        if (person.getEthnicName() != null) {
            myempi.setEthnicName(person.getEthnicName());
        }

        //民族编码系统
        if (person.getEthincDomain() != null) {
            myempi.setEthincDomain(person.getEthincDomain());
        }

        //获取得到的种族信息填充入对应的person实体类
        if (person.getRaceDict() != null) {
            myempi.setRaceCd(person.getRaceDict().getRaceCode());
        }

        //种族民称
        if (person.getRaceName() != null) {
            myempi.setRaceName(person.getRaceName());
        }

        //种族编码系统
        if (person.getRaceDomain() != null) {
            myempi.setRaceDomain(person.getRaceDomain());
        }

        //获取得到的国籍信息填充入对应的person实体类
        if (person.getNationalityDict() != null) {
            myempi.setNationalityCd(person.getNationalityDict().getAtionalityCode());
        }

        //国籍名
        if (person.getNationalityName() != null) {
            myempi.setNationalityName(person.getNationalityName());
        }

        //国籍编码系统
        if (person.getNationalityDomain() != null) {
            myempi.setNationalityDomain(person.getNationalityDomain());
        }

        //获取得到的语言信息填充如对应的person实体类
        if (person.getLanguageDict() != null) {
            myempi.setLanguageCd(person.getLanguageDict().getLanguageCode());
        }


        //获取得到的宗教信息填充入对应的person实体类
        if (person.getReligionDict() != null) {
            myempi.setReligionCd(person.getReligionDict().getReligionCode());
        }


        //获取得到的婚姻状态信息填充如对应 person实体类
        if (person.getMaritalStatusDict() != null) {
            myempi.setMaritalStatusCd(person.getMaritalStatusDict().getMaritalStatusCode());
        }

        //婚姻名称
        if (person.getMaritalStatusName() != null) {
            myempi.setMaritalStatusName(person.getMaritalStatusName());
        }


        //婚姻编码系统
        if (person.getMaritalDomain() != null) {
            myempi.setMaritalDomain(person.getMaritalDomain());
        }

        //教育程度编码
        if (person.getDegreeTypeDict() != null) {
            myempi.setDegree(person.getDegreeTypeDict().getDegreeTypeCode());
        }

        //教育程度名称
        if (person.getDegreeName() != null) {
            myempi.setDegreeName(person.getDegreeName());
        }

        //教育编码系统
        if (person.getDegreeDomain() != null) {
            myempi.setDegreeDomain(person.getDegreeDomain());
        }

        //邮件地址
        if (person.getEmail() != null) {
            myempi.setEmail(person.getEmail());
        }

        //居住地所在地省
        if (person.getHomeProvince() != null) {
            myempi.setHomeProvince(person.getHomeProvince());
        }

        //居住地所在市
        if (person.getHomeCity() != null) {
            myempi.setHomeCity(person.getHomeCity());
        }

        //居住地所在地区县
        if (person.getHomeCounty() != null) {
            myempi.setHomeCounty(person.getHomeCounty());
        }

        //居住地所在地邮编
        if (person.getHomeZip() != null) {
            myempi.setHomeZip(person.getHomeZip());
        }

        //居住地址
        if (person.getHomeAddress() != null) {
            myempi.setHomeAddress(person.getHomeAddress());
        }

        //户口所在地省
        if (person.getRegisteredProvince() != null) {
            myempi.setRegisteredProvince(person.getRegisteredProvince());
        }

        //户口所在地市
        if (person.getRegisteredCity() != null) {
            myempi.setRegisteredCity(person.getRegisteredCity());
        }

        //户口所在地区县
        if (person.getRegisteredCounty() != null) {
            myempi.setRegisteredCounty(person.getRegisteredCounty());
        }

        //户口所在地邮编
        if (person.getRegisteredZip() != null) {
            myempi.setRegisteredZip(person.getRegisteredZip());
        }

        //户口地址
        if (person.getRegisteredAddress() != null) {
            myempi.setRegisteredAddress(person.getRegisteredAddress());
        }

        //籍贯所在地省
        if (person.getNativeProvince() != null) {
            myempi.setNativeProvince(person.getNativeProvince());
        }

        //籍贯所在地市
        if (person.getNativeCity() != null) {
            myempi.setNativeCity(person.getNativeCity());
        }

        //职业编码
        if (person.getProfessionTypeDict() != null) {
            myempi.setProfession(person.getProfessionTypeDict().getProfessionTypeCode());
        }

        //职业编码名称
        if (person.getProfessionName() != null) {
            myempi.setProfessionName(person.getProfessionName());
        }

        //职业编码系统
        if (person.getProfessionDomain() != null) {
            myempi.setProfessionDomain(person.getProfessionDomain());
        }

        //工作单位
        if (person.getCompany() != null) {
            myempi.setCompany(person.getCompany());
        }

        //工作邮编
        if (person.getWorkZip() != null) {
            myempi.setWorkZip(person.getWorkZip());
        }

        //单位地址
        if (person.getWorkAddress() != null) {
            myempi.setWorkAddress(person.getWorkAddress());
        }

        //私人电话
        if (person.getPrivateNumber() != null) {
            myempi.setPrivateNumber(person.getPrivateNumber());
        }

        //家庭电话
        if (person.getHomeNumber() != null) {
            myempi.setHomeNumber(person.getHomeNumber());
        }

        //工作电话
        if (person.getWorkNumber() != null) {
            myempi.setWorkNumber(person.getWorkNumber());
        }

        //监护人
        if (person.getGuardianPerson() != null) {
            myempi.setGuardianPerson(person.getGuardianPerson());
        }

        //保密级别
        if (person.getVip() != null) {
            myempi.setVip(person.getVip());
        }


        //获取得到的死亡标志信息填充入对应的person实体类
        if (person.getDeathInd() != null) {
            myempi.setDeathInd(person.getDeathInd());
        }

        //死亡时间
        if (person.getDeathTime() != null) {
            myempi.setDeathTime(person.getDeathTime().toString());
        }

        //是否封帐
        if (person.getAccountLocked() != null) {
            myempi.setAccountLocked(person.getAccountLocked());
        }

        //封帐日期
        if (person.getAccountLockedDate() != null) {
            myempi.setAccountLockedDate(person.getAccountLockedDate());
        }

        //出生时间
        if (person.getBirthTime() != null) {
            myempi.setBirthTime(person.getBirthTime());
        }

        //cardtype
        if (person.getCardType() != null) {
            myempi.setCardType(person.getCardType());
        }


        return myempi;

    }

    //PM-20120920-PATIENTVISIT
    private void savePatientVisit(PatientVisit patientvisit, Person person) {
        String TempIdStr = null, TempDomainStr = null;

        java.util.Date now = new java.util.Date();

        if (person.getPersonId() > 0) {
            patientvisit.setPersonId(person.getPersonId());

            //patientvisit.setOldPersonId(person.getPersonId());
        }

        if (patientvisit.getPatientId() != null) {
            patientvisit.setVisitFlowId(patientvisit.getPatientId());
        }

        if (person.getCustom16() != null) {
            patientvisit.setPatientId(person.getCustom16());
        }

        if (person.getCustom11() != null) {
            patientvisit.setPersonDomain(person.getCustom11());
        }

        if (person.getCustom6().length() > 0) {

            if (person.getCustom6().indexOf("^") > 0) {
                TempIdStr = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));

                TempDomainStr = person.getCustom6().substring(person.getCustom6().indexOf("^") + 1);
            } else {
                TempIdStr = person.getCustom6();

                TempDomainStr = null;
            }
        }

        if (person.getInsuranceType() != null) {
            patientvisit.setInsuranceType(person.getInsuranceType());
        }

        if (person.getInsuranceName() != null) {
            patientvisit.setInsuranceName(person.getInsuranceName());
        }

        patientvisit.setVisitFlowId(TempIdStr);

        patientvisit.setVisitFlowDomain(TempDomainStr);

        if (person.getEmpi() != null) {
            patientvisit.setCustom1("");
        }

        if (person.getCustom15() != null) {
            patientvisit.setCustom4(person.getCustom15());
        }

        if (person.getCustom19() != null) {
            patientvisit.setCustom5(person.getCustom19());

            String TempStr1 = person.getCustom19().substring(0, person.getCustom19().indexOf("^"));

            String TempStr2 = person.getCustom19().substring(person.getCustom19().indexOf("^") + 1);

            patientvisit.setRelevanceID(TempStr1);

            patientvisit.setRelevanceDomain(TempStr2);
        }

        if (person.getGivenName() != null) {
            patientvisit.setRelevanceName(person.getGivenName());
        }
        patientvisit.setCreateDate(now);

        //patientvisit.setCreateId(Context.getUserContext().getUser());

        now = null;

        patientvisitDao.addPatientVisit(patientvisit);

        PersonInOut personinout = new PersonInOut();

        personinout.setPatientId(patientvisit.getPatientId());

        personinout.setPatientDomain(patientvisit.getPersonDomain());

        personinout.setVisitFlowId(patientvisit.getVisitFlowId());

        personinout.setVisitFlowDomain(patientvisit.getVisitFlowDomain());

        if (person.getGivenName() != null) personinout.setName(person.getGivenName());

        if (person.getIdentityNo() != null) personinout.setIdentityNo(person.getIdentityNo());

        if (person.getInsuranceNo() != null) personinout.setInsuranceNo(person.getInsuranceNo());

        if (person.getInsuranceType() != null) personinout.setInsuranceType(person.getInsuranceType());

        if (patientvisit.getPatCurrentBed() != null) personinout.setCurrentBed(patientvisit.getPatCurrentBed());

        if (patientvisit.getPatCuurentDep() != null) personinout.setCurrentDep(patientvisit.getPatCuurentDep());

        if (patientvisit.getPatCurrentRoom() != null) personinout.setCurrentRoom(patientvisit.getPatCurrentRoom());

        personinout.setChangeType("A01");

        personinout.setDateCreated(new java.util.Date());

        patientvisitDao.addPersonInOut(personinout);

    }


    private IdentifierDomainDict generateIdentifierDomainForUniversalIdentifierTypeCode(String universalIdentifierTypeCode) {
        //UUID uuid = new UUID(0, 0);
        IdentifierDomainDict id = new IdentifierDomainDict();
        java.util.Date now = new java.util.Date();
        id.setDateCreated(now);
        id.setAppUser(Context.getUserContext().getUser());
        id.setNamespaceIdentifier(UUID.randomUUID().toString());
        id.setUniversalIdentifier(UUID.randomUUID().toString());
        id.setUniversalIdentifierTypeCode(universalIdentifierTypeCode);
        return id;
    }

    private String NewMergeProcedure(Person leftperson, Person rightperson, int ControlID) throws ApplicationException {
        return personDao.runProcedureForMerge(leftperson, rightperson, ControlID);
    }

    private String NewRunProcedure(Person person, Person personFound, String ReplaceID, int ControlID) throws ApplicationException {
        return personDao.runProcedureForEmpi(person, personFound, ReplaceID, ControlID);
    }

    private String NewEmpiMergeProcedure(List<String> myempi, String empi, int ControlID) throws ApplicationException, SQLException {
        return personDao.runProcedureForEmpiMerge(myempi, empi, ControlID);
    }

    private void NewUpdatePerson(Person person, Person personFound) {
        person.setPersonId(personFound.getPersonId());
        person.setDateChanged(new java.util.Date());
        person.setAppUserByChangedById(Context.getUserContext().getUser());
        personDao.updatePerson(person);
    }

    private void updatePerson(Person person, Person personFound) {
        person.setPersonId(personFound.getPersonId());
        person.setDateChanged(new java.util.Date());
        person.setAppUserByChangedById(Context.getUserContext().getUser());
        personDao.updatePerson(person);

        updateEmpi(person, personFound);
    }

    private void updateEmpi(Person person, Person personFound) {

        Empi tempempi = new Empi();

        Empi myempi = new Empi();

        tempempi = empiDao.getPersonByEmpi(personFound);

        if (tempempi != null) {
            myempi = updateEmpiAttribute(person, tempempi);

            empiDao.updateEmpi(myempi);
        }
    }

    private Empi updateEmpiAttribute(Person person, Empi tempempi) {
        Empi myempi = new Empi();

        if (person.getGivenName() != null) {
            if (tempempi.getName() == null || !tempempi.getName().equalsIgnoreCase(person.getGivenName())) {
                myempi.setName(person.getGivenName());
            } else {
                myempi.setName(tempempi.getName());
            }
        }

        //生日
        if (person.getDateOfBirth() != null) {
            if (tempempi.getDateOfBirth() == null || !tempempi.getDateOfBirth().toString().equalsIgnoreCase(person.getDateOfBirth().toString())) {
                myempi.setDateOfBirth(person.getDateOfBirth());
            } else {
                myempi.setDateOfBirth(tempempi.getDateOfBirth());
            }
        }


        //出生地//出生地所在地的省
        if (person.getBirthProvince() != null) {
            if (tempempi.getBirthProvince() == null || !tempempi.getBirthProvince().equalsIgnoreCase(person.getBirthProvince())) {
                myempi.setBirthProvince(person.getBirthProvince());
            } else {
                myempi.setBirthProvince(tempempi.getBirthProvince());
            }
        }

        //出生地所在地的市
        if (person.getBirthCity() != null) {
            if (tempempi.getBirthCity() == null || !tempempi.getBirthCity().equalsIgnoreCase(person.getBirthCity())) {
                myempi.setBirthCity(person.getBirthCity());
            } else {
                myempi.setBirthCity(tempempi.getBirthCity());
            }
        }

        //出生地所在区县
        if (person.getBirthCounty() != null) {
            if (tempempi.getBirthCounty() == null || !tempempi.getBirthCounty().equalsIgnoreCase(person.getBirthCounty())) {
                myempi.setBirthCounty(person.getBirthCounty());
            } else {
                myempi.setBirthCounty(tempempi.getBirthCounty());
            }
        }

        //出生地
        if (person.getBirthPlace() != null) {
            if (tempempi.getBirthPlace() == null || !tempempi.getBirthPlace().equalsIgnoreCase(person.getBirthPlace())) {
                myempi.setBirthPlace(person.getBirthPlace());
            } else {
                myempi.setBirthPlace(tempempi.getBirthPlace());
            }
        }

        //出生地所在地邮编
        if (person.getBirthZip() != null) {
            if (tempempi.getBirthZip() == null || !tempempi.getBirthZip().equalsIgnoreCase(person.getBirthZip())) {
                myempi.setBirthZip(person.getBirthZip());
            } else {
                myempi.setBirthZip(tempempi.getBirthZip());
            }
        }

        //多胞胎标志
        if (person.getMultipleBirthInd() != null) {
            if (tempempi.getMultipleBirthInd() == null || !tempempi.getMultipleBirthInd().equalsIgnoreCase(person.getMultipleBirthInd())) {
                myempi.setMultipleBirthInd(person.getMultipleBirthInd());
            } else {
                myempi.setMultipleBirthInd(tempempi.getMultipleBirthInd());
            }
        }

        //出生顺序
        if (person.getBirthOrder() != null) {
            if (tempempi.getBirthOrder() == null || !tempempi.getBirthOrder().equalsIgnoreCase(Integer.toString(person.getBirthOrder()))) {
                myempi.setBirthOrder(Integer.toString(person.getBirthOrder()));
            } else {
                myempi.setBirthOrder(tempempi.getBirthOrder());
            }
        }

        //母亲娘家姓
        if (person.getMothersMaidenName() != null) {
            if (tempempi.getMothersMaidenName() == null || !tempempi.getMothersMaidenName().equalsIgnoreCase(person.getMothersMaidenName())) {
                myempi.setMothersMaidenName(person.getMothersMaidenName());
            } else {
                myempi.setMothersMaidenName(tempempi.getMothersMaidenName());
            }
        }

        //社会保险号
        if (person.getSsn() != null) {
            if (tempempi.getSsn() == null || !tempempi.getSsn().equalsIgnoreCase(person.getSsn())) {
                myempi.setSsn(person.getSsn());
            } else {
                myempi.setSsn(tempempi.getSsn());
            }
        }

        //身份证号identity_no
        if (person.getIdentityNo() != null) {
            if (tempempi.getIdentityNo() == null || !tempempi.getIdentityNo().equalsIgnoreCase(person.getIdentityNo())) {
                myempi.setIdentityNo(person.getIdentityNo());
            } else {
                myempi.setIdentityNo(tempempi.getIdentityNo());
            }
        }

        //市民卡号
        if (person.getCitizenCard() != null) {
            if (tempempi.getCitizenCard() == null || !tempempi.getCitizenCard().equalsIgnoreCase(person.getCitizenCard())) {
                myempi.setCitizenCard(person.getCitizenCard());
            } else {
                myempi.setCitizenCard(tempempi.getCitizenCard());
            }
        }

        //医疗证号
        if (person.getMedicalCertificate() != null) {
            if (tempempi.getMedicalCertificate() == null || !tempempi.getMedicalCertificate().equalsIgnoreCase(person.getMedicalCertificate())) {
                myempi.setMedicalCertificate(person.getMedicalCertificate());
            } else {
                myempi.setMedicalCertificate(tempempi.getMedicalCertificate());
            }
        }

        //医保个人编号
        if (person.getMeicarePerson() != null) {
            if (tempempi.getMeicarePerson() == null || !tempempi.getMeicarePerson().equalsIgnoreCase(person.getMeicarePerson())) {
                myempi.setMeicarePerson(person.getMeicarePerson());
            } else {
                myempi.setMeicarePerson(tempempi.getMeicarePerson());
            }
        }

        //老人证号
        if (person.getElderCertificate() != null) {
            if (tempempi.getElderCertificate() == null || !tempempi.getElderCertificate().equalsIgnoreCase(person.getElderCertificate())) {
                myempi.setElderCertificate(person.getElderCertificate());
            } else {
                myempi.setElderCertificate(tempempi.getElderCertificate());
            }
        }

        //病历号
        if (person.getOpcaseno() != null) {
            if (tempempi.getOpcaseno() == null || !tempempi.getOpcaseno().equalsIgnoreCase(person.getOpcaseno())) {
                myempi.setOpcaseno(person.getOpcaseno());
            } else {
                myempi.setOpcaseno(tempempi.getOpcaseno());
            }
        }

        //医保卡号insurance_no
        if (person.getInsuranceNo() != null) {
            if (tempempi.getInsuranceNo() == null || !tempempi.getInsuranceNo().equalsIgnoreCase(person.getInsuranceNo())) {
                myempi.setInsuranceNo(person.getInsuranceNo());
            } else {
                myempi.setInsuranceNo(tempempi.getInsuranceNo());
            }
        }

        if (person.getHealthCard() != null) {
            if (tempempi.getHealthCard() == null || !tempempi.getHealthCard().equalsIgnoreCase(person.getHealthCard())) {
                myempi.setHealthCard(person.getHealthCard());
            } else {
                myempi.setHealthCard(tempempi.getHealthCard());
            }
        }

        //性别编码
        if (person.getGenderDict() != null) {
            if (tempempi.getGenderCd() == null || !tempempi.getGenderCd().equalsIgnoreCase(person.getGenderDict().getGenderCode())) {
                myempi.setGenderCd(person.getGenderDict().getGenderCode());
            } else {
                myempi.setGenderCd(tempempi.getGenderCd());
            }
        }

        //性别名称
        if (person.getGenderName() != null) {
            if (tempempi.getGenderName() == null || !tempempi.getGenderName().equalsIgnoreCase(person.getGenderName())) {
                myempi.setGenderName(person.getGenderName());
            } else {
                myempi.setGenderName(tempempi.getGenderName());
            }
        }

        //性别编码系统
        if (person.getGenderDomain() != null) {
            if (tempempi.getGenderDomain() == null || !tempempi.getGenderDomain().equalsIgnoreCase(person.getGenderDomain())) {
                myempi.setGenderDomain(person.getGenderDomain());
            } else {
                myempi.setGenderDomain(tempempi.getGenderDomain());
            }
        }

        //民族编码
        if (person.getEthnicgroupDict() != null) {
            if (tempempi.getEthnicGroupCd() == null || !tempempi.getEthnicGroupCd().equalsIgnoreCase(person.getEthnicgroupDict().getEthnicGroupCode())) {
                myempi.setEthnicGroupCd(person.getEthnicgroupDict().getEthnicGroupCode());
            } else {
                myempi.setEthnicGroupCd(tempempi.getEthnicGroupCd());
            }
        }

        //民族编码名称
        if (person.getEthnicName() != null) {
            if (tempempi.getEthnicName() == null || !tempempi.getEthnicName().equalsIgnoreCase(person.getEthnicName())) {
                myempi.setEthnicName(person.getEthnicName());
            } else {
                myempi.setEthnicName(tempempi.getEthnicName());
            }
        }

        // 民族编码系统
        if (person.getEthincDomain() != null) {
            if (tempempi.getEthincDomain() == null || !tempempi.getEthincDomain().equalsIgnoreCase(person.getEthincDomain())) {
                myempi.setEthincDomain(person.getEthincDomain());
            } else {
                myempi.setEthincDomain(tempempi.getEthincDomain());
            }
        }

        //种族编码
        if (person.getRaceDict() != null) {
            if (tempempi.getRaceCd() == null || !tempempi.getRaceCd().equalsIgnoreCase(person.getRaceDict().getRaceCode())) {
                myempi.setRaceCd(person.getRaceDict().getRaceCode());
            } else {
                myempi.setRaceCd(tempempi.getRaceCd());
            }
        }

        // 种族名称
        if (person.getRaceName() != null) {
            if (tempempi.getRaceName() == null || !tempempi.getRaceName().equalsIgnoreCase(person.getRaceName())) {
                myempi.setRaceName(person.getRaceName());
            } else {
                myempi.setRaceName(tempempi.getRaceName());
            }
        }

        //种族编码系统
        if (person.getRaceDomain() != null) {
            if (tempempi.getRaceDomain() == null || !tempempi.getRaceDomain().equalsIgnoreCase(person.getRaceDomain())) {
                myempi.setRaceDomain(person.getRaceDomain());
            } else {
                myempi.setRaceDomain(person.getRaceDomain());
            }
        }

        //国籍编码
        if (person.getNationalityDict() != null) {
            if (tempempi.getNationalityCd() == null || !tempempi.getNationalityCd().equalsIgnoreCase(person.getNationalityDict().getAtionalityCode())) {
                myempi.setNationalityCd(person.getNationalityDict().getAtionalityCode());
            } else {
                myempi.setNationalityCd(tempempi.getNationalityCd());
            }
        }

        //国籍名
        if (person.getNationalityName() != null) {
            if (tempempi.getNationalityName() == null || !tempempi.getNationalityName().equalsIgnoreCase(person.getNationalityName())) {
                myempi.setNationalityName(person.getNationalityName());
            } else {
                myempi.setNationalityName(tempempi.getNationalityName());
            }
        }

        //国籍编码系统
        if (person.getNationalityDomain() != null) {
            if (tempempi.getNationalityDomain() == null || !tempempi.getNationalityDomain().equalsIgnoreCase(person.getNationalityDomain())) {
                myempi.setNationalityDomain(person.getNationalityDomain());
            } else {
                myempi.setNationalityDomain(tempempi.getNationalityDomain());
            }
        }

        //语言编码
        if (person.getLanguageDict() != null) {
            if (tempempi.getLanguageCd() == null || !tempempi.getLanguageCd().equalsIgnoreCase(person.getLanguageDict().getLanguageCode())) {
                myempi.setLanguageCd(person.getLanguageDict().getLanguageCode());
            } else {
                myempi.setLanguageCd(tempempi.getLanguageCd());
            }
        }

        //宗教编码
        if (person.getReligionDict() != null) {
            if (tempempi.getReligionCd() == null || !tempempi.getReligionCd().equalsIgnoreCase(person.getReligionDict().getReligionCode())) {
                myempi.setReligionCd(person.getReligionDict().getReligionCode());
            } else {
                myempi.setReligionCd(tempempi.getLanguageCd());
            }
        }

        //婚姻编码
        if (person.getMaritalStatusDict() != null) {
            if (tempempi.getMaritalStatusCd() == null || !tempempi.getMaritalStatusCd().equalsIgnoreCase(person.getMaritalStatusDict().getMaritalStatusCode())) {
                myempi.setMaritalStatusCd(person.getMaritalStatusDict().getMaritalStatusCode());
            } else {
                myempi.setMaritalStatusCd(tempempi.getMaritalStatusCd());
            }
        }

        //婚姻名称
        if (person.getMaritalStatusName() != null) {
            if (tempempi.getMaritalStatusName() == null || !tempempi.getMaritalStatusName().equalsIgnoreCase(person.getMaritalStatusName())) {
                myempi.setMaritalStatusName(person.getMaritalStatusName());
            } else {
                myempi.setMaritalStatusName(tempempi.getMaritalStatusName());
            }
        }

        //婚姻编码系统
        if (person.getMaritalDomain() != null) {
            if (tempempi.getMaritalDomain() == null || !tempempi.getMaritalDomain().equalsIgnoreCase(person.getMaritalDomain())) {
                myempi.setMaritalDomain(person.getMaritalDomain());
            } else {
                myempi.setMaritalDomain(tempempi.getMaritalDomain());
            }
        }

        //教育程度编码
        if (person.getDegreeTypeDict() != null) {
            if (tempempi.getDegree() == null || !tempempi.getDegree().equalsIgnoreCase(person.getDegreeTypeDict().getDegreeTypeCode())) {
                myempi.setDegree(person.getDegreeTypeDict().getDegreeTypeCode());
            } else {
                myempi.setDegree(tempempi.getDegree());

            }
        }

        //教育程度编码名称
        if (person.getDegreeName() != null) {
            if (tempempi.getDegreeName() == null || !tempempi.getDegreeName().equalsIgnoreCase(person.getDegreeName())) {
                myempi.setDegreeName(person.getDegreeName());
            } else {
                myempi.setDegreeName(tempempi.getDegreeName());
            }
        }

        // 教育程度编码系统
        if (person.getDegreeDomain() != null) {
            if (tempempi.getDegreeDomain() == null || !tempempi.getDegreeDomain().equalsIgnoreCase(person.getDegreeDomain())) {
                myempi.setDegreeDomain(person.getDegreeDomain());
            } else {
                myempi.setDegreeDomain(tempempi.getDegreeDomain());
            }
        }

        //邮件地址
        if (person.getEmail() != null) {
            if (tempempi.getEmail() == null || !tempempi.getEmail().equalsIgnoreCase(person.getEmail())) {
                myempi.setEmail(person.getEmail());
            } else {
                myempi.setEmail(tempempi.getEmail());
            }
        }

        //居住地所在地省
        if (person.getHomeProvince() != null) {
            if (tempempi.getHomeProvince() == null || !tempempi.getHomeProvince().equalsIgnoreCase(person.getHomeProvince())) {
                myempi.setHomeProvince(person.getHomeProvince());
            } else {
                myempi.setHomeProvince(tempempi.getHomeProvince());
            }
        }

        //居住地所在地市
        if (person.getHomeCity() != null) {
            if (tempempi.getHomeCity() == null || !tempempi.getHomeCity().equalsIgnoreCase(person.getHomeCity())) {
                myempi.setHomeCity(person.getHomeCity());
            } else {
                myempi.setHomeCity(tempempi.getHomeCity());
            }
        }

        //居住地所在地区县
        if (person.getHomeCounty() != null) {
            if (tempempi.getHomeCounty() == null || !tempempi.getHomeCounty().equalsIgnoreCase(person.getHomeCounty())) {
                myempi.setHomeCounty(person.getHomeCounty());
            } else {
                myempi.setHomeCounty(tempempi.getHomeCounty());
            }
        }

        //居住地所在地邮编
        if (person.getHomeZip() != null) {
            if (tempempi.getHomeZip() == null || !tempempi.getHomeZip().equalsIgnoreCase(person.getHomeZip())) {
                myempi.setHomeZip(person.getHomeZip());
            } else {
                myempi.setHomeZip(tempempi.getHomeZip());
            }
        }

        //居住地址
        if (person.getHomeAddress() != null) {
            if (tempempi.getHomeAddress() == null || !tempempi.getHomeAddress().equalsIgnoreCase(person.getHomeAddress())) {
                myempi.setHomeAddress(person.getHomeAddress());
            } else {
                myempi.setHomeAddress(tempempi.getHomeAddress());
            }
        }

        //户口所在地区县
        if (person.getRegisteredCounty() != null) {
            if (tempempi.getRegisteredCounty() == null || !tempempi.getRegisteredCounty().equalsIgnoreCase(person.getRegisteredCounty())) {
                myempi.setRegisteredCounty(person.getRegisteredCounty());
            } else {
                myempi.setRegisteredCounty(tempempi.getRegisteredProvince());
            }
        }

        //户口所在地市
        if (person.getRegisteredCity() != null) {
            if (tempempi.getRegisteredCity() == null || !tempempi.getRegisteredCity().equalsIgnoreCase(person.getRegisteredCity())) {
                myempi.setRegisteredCity(person.getRegisteredCity());
            } else {
                myempi.setRegisteredCity(tempempi.getRegisteredCity());
            }
        }

        //户口所在地省
        if (person.getRegisteredProvince() != null) {
            if (tempempi.getRegisteredProvince() == null || !tempempi.getRegisteredProvince().equalsIgnoreCase(person.getRegisteredProvince())) {
                myempi.setRegisteredProvince(person.getRegisteredProvince());
            } else {
                myempi.setRegisteredProvince(tempempi.getRegisteredProvince());
            }
        }

        //户口所在地邮编
        if (person.getRegisteredZip() != null) {
            if (tempempi.getRegisteredZip() == null || !tempempi.getRegisteredZip().equalsIgnoreCase(person.getRegisteredZip())) {
                myempi.setRegisteredZip(person.getRegisteredZip());
            } else {
                myempi.setRegisteredZip(tempempi.getRegisteredZip());
            }
        }

        //户口地址
        if (person.getRegisteredAddress() != null) {
            if (tempempi.getRegisteredAddress() == null || !tempempi.getRegisteredAddress().equalsIgnoreCase(person.getRegisteredAddress())) {
                myempi.setRegisteredAddress(person.getRegisteredAddress());
            } else {
                myempi.setRegisteredAddress(tempempi.getRegisteredAddress());
            }
        }

        //籍贯所在地省
        if (person.getNativeProvince() != null) {
            if (tempempi.getNativeProvince() == null || !tempempi.getNativeProvince().equalsIgnoreCase(person.getNativeProvince())) {
                myempi.setNativeProvince(person.getNativeProvince());
            } else {
                myempi.setNativeProvince(tempempi.getNativeProvince());
            }
        }

        //籍贯所在地市
        if (person.getNativeCity() != null) {
            if (tempempi.getNativeCity() == null || !tempempi.getNativeCity().equalsIgnoreCase(person.getNativeCity())) {
                myempi.setNativeCity(person.getNativeCity());
            } else {
                myempi.setNativeCity(tempempi.getNativeCity());
            }
        }

        //职业编码
        if (person.getProfessionTypeDict() != null) {
            if (tempempi.getProfession() == null || !tempempi.getProfession().equalsIgnoreCase(person.getProfessionTypeDict().getProfessionTypeCode())) {
                myempi.setProfession(person.getProfessionTypeDict().getProfessionTypeCode());
            } else {
                myempi.setProfession(tempempi.getProfession());
            }
        }

        //职业名称
        if (person.getProfessionName() != null) {
            if (tempempi.getProfessionName() == null || !tempempi.getProfessionName().equalsIgnoreCase(person.getProfessionName())) {
                myempi.setProfessionName(person.getProfessionName());
            } else {
                myempi.setProfessionName(tempempi.getProfessionName());
            }
        }

        //职业编码系统
        if (person.getProfessionDomain() != null) {
            if (tempempi.getProfessionDomain() == null || !tempempi.getProfessionDomain().equalsIgnoreCase(person.getProfessionDomain())) {
                myempi.setProfessionDomain(person.getProfessionDomain());
            } else {
                myempi.setProfessionDomain(tempempi.getProfessionDomain());
            }
        }

        //工作单位
        if (person.getCompany() != null) {
            if (tempempi.getCompany() == null || !tempempi.getCompany().equalsIgnoreCase(person.getCompany())) {
                myempi.setCompany(person.getCompany());
            } else {
                myempi.setCompany(tempempi.getCompany());
            }
        }

        //工作邮编
        if (person.getWorkZip() != null) {
            if (tempempi.getWorkZip() == null || !tempempi.getWorkZip().equalsIgnoreCase(person.getWorkZip())) {
                myempi.setWorkZip(person.getWorkZip());
            } else {
                myempi.setWorkZip(tempempi.getWorkZip());
            }
        }

        //单位地址
        if (person.getWorkAddress() != null) {
            if (tempempi.getWorkAddress() == null || !tempempi.getWorkAddress().equalsIgnoreCase(person.getWorkAddress())) {
                myempi.setWorkAddress(person.getWorkAddress());
            } else {
                myempi.setWorkAddress(tempempi.getWorkAddress());
            }
        }

        //私人电话
        if (person.getPrivateNumber() != null) {
            if (tempempi.getPrivateNumber() == null || !tempempi.getPrivateNumber().equalsIgnoreCase(person.getPrivateNumber())) {
                myempi.setPrivateNumber(person.getPrivateNumber());
            } else {
                myempi.setPrivateNumber(tempempi.getPrivateNumber());
            }
        }

        //家庭电话
        if (person.getHomeNumber() != null) {
            if (tempempi.getHomeNumber() == null || !tempempi.getHomeNumber().equalsIgnoreCase(person.getHomeNumber())) {
                myempi.setHomeNumber(person.getHomeNumber());
            } else {
                myempi.setHomeNumber(tempempi.getHomeNumber());
            }
        }

        // 工作电话
        if (person.getWorkNumber() != null) {
            if (tempempi.getWorkNumber() == null || !tempempi.getWorkNumber().equalsIgnoreCase(person.getWorkNumber())) {
                myempi.setWorkNumber(person.getWorkNumber());
            } else {
                myempi.setWorkNumber(tempempi.getWorkNumber());
            }
        }

        //监护人
        if (person.getGuardianPerson() != null) {
            if (tempempi.getGuardianPerson() == null || !tempempi.getGuardianPerson().equalsIgnoreCase(person.getGuardianPerson())) {
                myempi.setGuardianPerson(person.getGuardianPerson());
            } else {
                myempi.setGuardianPerson(tempempi.getGuardianPerson());
            }
        }

        //保密级别
        if (person.getVip() != null) {
            if (tempempi.getVip() == null || !tempempi.getVip().equalsIgnoreCase(person.getVip())) {
                myempi.setVip(person.getVip());
            } else {
                myempi.setVip(tempempi.getVip());
            }
        }

        //死亡标记
        if (person.getDeathInd() != null) {
            if (tempempi.getDeathInd() == null || !tempempi.getDeathInd().equalsIgnoreCase(person.getDeathInd())) {
                myempi.setDeathInd(person.getDeathInd());
            } else {
                myempi.setDeathInd(tempempi.getDeathInd());
            }
        }

        //cardtype
        if (person.getCardType() != null) {
            if (tempempi.getCardType() == null || !tempempi.getCardType().equalsIgnoreCase(person.getCardType())) {
                myempi.setCardType(person.getCardType());
            } else {
                myempi.setCardType(tempempi.getCardType());
            }
        }


        myempi.setEmpi(person.getEmpi());

        return myempi;
    }

    private void DeletePatientVisit(PatientVisit patientvisit, Person personFound) {
        if (personFound.getPersonId() > 0) {
            patientvisit.setPersonId(personFound.getPersonId());
        }

        if (personFound.getCustom16() != null) {
            patientvisit.setPatientId(personFound.getCustom16());
        }

        patientvisit.setModifyDate(new java.util.Date());

        patientvisit.setVoidedDate(new java.util.Date());

        patientvisitDao.updatePatientVisit(patientvisit);
    }


    private void updatePatientVisit(PatientVisit patientvisit, Person personFound) {
        if (personFound.getPersonId() > 0) {
            patientvisit.setPersonId(personFound.getPersonId());
        }

        if (personFound.getCustom16() != null) {
            patientvisit.setPatientId(personFound.getCustom16());
        }

        patientvisit.setModifyDate(new java.util.Date());

        patientvisitDao.updatePatientVisit(patientvisit);
    }

    private void SavePersonInOut(PatientVisit patientvisit, PatientVisit visitFound, Person personFound, List<String> Info) {
        PersonInOut personinout = new PersonInOut();

        java.util.Date now = new java.util.Date();

        personinout.setPatientId(visitFound.getPatientId());

        personinout.setPatientDomain(visitFound.getPersonDomain());

        personinout.setVisitFlowId(visitFound.getVisitFlowId());

        personinout.setVisitFlowDomain(visitFound.getVisitFlowDomain());

        if (personFound.getGivenName() != null) personinout.setName(personFound.getGivenName());

        if (personFound.getIdentityNo() != null) personinout.setIdentityNo(personFound.getIdentityNo());

        if (personFound.getInsuranceNo() != null) personinout.setInsuranceNo(personFound.getInsuranceNo());

        if (personFound.getInsuranceType() != null) personinout.setInsuranceType(personFound.getInsuranceType());

        if (patientvisit.getPatCurrentBed() != null) personinout.setCurrentBed(patientvisit.getPatCurrentBed());

        if (patientvisit.getPatCuurentDep() != null) personinout.setCurrentDep(patientvisit.getPatCuurentDep());

        if (patientvisit.getPatCurrentRoom() != null) personinout.setCurrentRoom(patientvisit.getPatCurrentRoom());

        if (Info.get(2) != null) personinout.setOldContent(Info.get(2).toString());

        if (Info.get(3) != null) personinout.setNewContent(Info.get(3).toString());

        if (personFound.getCustom29() != null) personinout.setChangeType(personFound.getCustom29());

        personinout.setDateCreated(now);

        patientvisitDao.addPersonInOut(personinout);
    }


    //2012-11-23 panmin
    private void SavePatientVisitHistory(PatientVisit patientvisit, Person personFound) {
        PatientVisitHistory mypatientvisithistory = new PatientVisitHistory();

        if (personFound.getEmpi() != null) mypatientvisithistory.setGlobalId(personFound.getEmpi());

        if (personFound.getPersonId() > 0) mypatientvisithistory.setPersonId(personFound.getPersonId());

        if (patientvisit.getVisitId() > 0) mypatientvisithistory.setVisitId(patientvisit.getVisitId());

        if (patientvisit.getPersonDomain() != null)
            mypatientvisithistory.setPersonDomain(patientvisit.getPersonDomain());

        if (patientvisit.getVisitFlowId() != null) mypatientvisithistory.setVisitFlowId(patientvisit.getVisitFlowId());

        if (patientvisit.getVisitFlowDomain() != null)
            mypatientvisithistory.setVisitFlowDomain(patientvisit.getVisitFlowDomain());

        if (patientvisit.getHospitalDomain() != null)
            mypatientvisithistory.setHospitalDomain(patientvisit.getHospitalDomain());

        if (patientvisit.getPatCategory() != null) mypatientvisithistory.setPatCategory(patientvisit.getPatCategory());

        if (patientvisit.getPatCurrentPointOfCare() != null)
            mypatientvisithistory.setPatCurrentPointOfCare(patientvisit.getPatCurrentPointOfCare());

        if (patientvisit.getPatCurrentRoom() != null)
            mypatientvisithistory.setPatCurrentRoom(patientvisit.getPatCurrentRoom());

        if (patientvisit.getPatCurrentBed() != null)
            mypatientvisithistory.setPatCurrentBed(patientvisit.getPatCurrentBed());

        if (patientvisit.getPatCuurentDep() != null)
            mypatientvisithistory.setPatCuurentDep(patientvisit.getPatCuurentDep());

        if (patientvisit.getPatCurrentPositionStatus() != null)
            mypatientvisithistory.setPatCurrentPositionStatus(patientvisit.getPatCurrentPositionStatus());

        if (patientvisit.getPatCurrentPositionType() != null)
            mypatientvisithistory.setPatCurrentPositionType(patientvisit.getPatCurrentPositionType());

        if (patientvisit.getPatCurrentBuilding() != null)
            mypatientvisithistory.setPatCurrentBuilding(patientvisit.getPatCurrentBuilding());

        if (patientvisit.getPatCurrentFloor() != null)
            mypatientvisithistory.setPatCurrentFloor(patientvisit.getPatCurrentFloor());

        if (patientvisit.getPatCuurentDescription() != null)
            mypatientvisithistory.setPatCuurentDescription(patientvisit.getPatCuurentDescription());

        if (patientvisit.getPatAdmissionType() != null)
            mypatientvisithistory.setPatAdmissionType(patientvisit.getPatAdmissionType());

        if (patientvisit.getPatAdmissionNumber() != null)
            mypatientvisithistory.setPatAdmissionNumber(patientvisit.getPatAdmissionNumber());

        if (patientvisit.getPatFormerPointOfCare() != null)
            mypatientvisithistory.setPatFormerPointOfCare(patientvisit.getPatFormerPointOfCare());

        if (patientvisit.getPatFormerRoom() != null)
            mypatientvisithistory.setPatFormerRoom(patientvisit.getPatFormerRoom());

        if (patientvisit.getPatFormerBed() != null)
            mypatientvisithistory.setPatFormerBed(patientvisit.getPatFormerBed());

        if (patientvisit.getPatFormerDep() != null)
            mypatientvisithistory.setPatFormerDep(patientvisit.getPatFormerDep());

        if (patientvisit.getPatFormerPositionStatus() != null)
            mypatientvisithistory.setPatFormerPositionStatus(patientvisit.getPatFormerPositionStatus());

        if (patientvisit.getPatFormerPositionType() != null)
            mypatientvisithistory.setPatFormerPositionType(patientvisit.getPatFormerPositionType());

        if (patientvisit.getPatFormerBuilding() != null)
            mypatientvisithistory.setPatFormerBuilding(patientvisit.getPatFormerBuilding());

        if (patientvisit.getPatFormerFloor() != null)
            mypatientvisithistory.setPatFormerFloor(patientvisit.getPatFormerFloor());

        if (patientvisit.getPatFormerDescription() != null)
            mypatientvisithistory.setPatFormerDescription(patientvisit.getPatFormerDescription());

        if (patientvisit.getAdmissionsDoctor() != null)
            mypatientvisithistory.setAdmissionsDoctor(patientvisit.getAdmissionsDoctor());

        if (patientvisit.getAdmissionsDoctorId() != null)
            mypatientvisithistory.setAdmissionsDoctorId(patientvisit.getAdmissionsDoctorId());

        if (patientvisit.getReferringDoctor() != null)
            mypatientvisithistory.setReferringDoctor(patientvisit.getReferringDoctor());

        if (patientvisit.getReferringDoctorId() != null)
            mypatientvisithistory.setReferringDoctorId(patientvisit.getReferringDoctorId());

        if (patientvisit.getConsultationDoctor() != null)
            mypatientvisithistory.setConsultationDoctor(patientvisit.getConsultationDoctor());

        if (patientvisit.getConsultationDoctorId() != null)
            mypatientvisithistory.setConsultationDoctorId(patientvisit.getConsultationDoctorId());

        if (patientvisit.getHospitalService() != null)
            mypatientvisithistory.setHospitalService(patientvisit.getHospitalService());

        if (patientvisit.getPatTempPointOfCare() != null)
            mypatientvisithistory.setPatTempPointOfCare(patientvisit.getPatTempPointOfCare());

        if (patientvisit.getPatTempRoom() != null) mypatientvisithistory.setPatTempRoom(patientvisit.getPatTempRoom());

        if (patientvisit.getPatTempBed() != null) mypatientvisithistory.setPatTempBed(patientvisit.getPatTempBed());

        if (patientvisit.getPatTempDep() != null) mypatientvisithistory.setPatTempDep(patientvisit.getPatTempDep());

        if (patientvisit.getPatTempPositionStatus() != null)
            mypatientvisithistory.setPatTempPositionStatus(patientvisit.getPatTempPositionStatus());

        if (patientvisit.getPatTempPositionType() != null)
            mypatientvisithistory.setPatTempPositionType(patientvisit.getPatTempPositionType());

        if (patientvisit.getPatTempBuilding() != null)
            mypatientvisithistory.setPatTempBuilding(patientvisit.getPatTempBuilding());

        if (patientvisit.getPatTempFloor() != null)
            mypatientvisithistory.setPatTempFloor(patientvisit.getPatTempFloor());

        if (patientvisit.getPatTempDescription() != null)
            mypatientvisithistory.setPatTempDescription(patientvisit.getPatTempDescription());

        if (patientvisit.getPatAdmissionTest() != null)
            mypatientvisithistory.setPatAdmissionTest(patientvisit.getPatAdmissionTest());

        if (patientvisit.getPatReAdmission() != null)
            mypatientvisithistory.setPatReAdmission(patientvisit.getPatReAdmission());

        if (patientvisit.getPatAdmissionSource() != null)
            mypatientvisithistory.setPatAdmissionSource(patientvisit.getPatAdmissionSource());

        //出院处置名
        if (patientvisit.getDischargeName() != null)
            mypatientvisithistory.setDischargeName(patientvisit.getDischargeName());

        if (patientvisit.getDischargeDomain() != null)
            mypatientvisithistory.setDischargeDomain(patientvisit.getDischargeDomain());

        if (patientvisit.getAdmissionName() != null)
            mypatientvisithistory.setAdmissionName(patientvisit.getAdmissionName());

        if (patientvisit.getAdmissionDomain() != null)
            mypatientvisithistory.setAdmissionDomain(patientvisit.getAdmissionDomain());

        if (patientvisit.getIpStatusName() != null)
            mypatientvisithistory.setIpStatusName(patientvisit.getIpStatusName());

        if (patientvisit.getIpStatusDomain() != null)
            mypatientvisithistory.setIpStatusDomain(patientvisit.getIpStatusDomain());

        if (patientvisit.getDificultyName() != null)
            mypatientvisithistory.setDificultyName(patientvisit.getDificultyName());

        if (patientvisit.getDificultyDomain() != null)
            mypatientvisithistory.setDificultyDomain(patientvisit.getDificultyDomain());

        if (patientvisit.getAdmissionSourceName() != null)
            mypatientvisithistory.setAdmissionSourceName(patientvisit.getAdmissionSourceName());

        if (patientvisit.getAdmissionSourceDomain() != null)
            mypatientvisithistory.setAdmissionSourceDomain(patientvisit.getAdmissionSourceDomain());

        if (patientvisit.getAccountStatusName() != null)
            mypatientvisithistory.setAccountStatusName(patientvisit.getAccountStatusName());

        if (patientvisit.getAccountStatusDomain() != null)
            mypatientvisithistory.setAccountStatusDomain(patientvisit.getAccountStatusDomain());

        if (patientvisit.getPatCategoryName() != null)
            mypatientvisithistory.setPatCategoryName(patientvisit.getPatCategoryName());

        if (patientvisit.getPatCategorySystem() != null)
            mypatientvisithistory.setPatCategorySystem(patientvisit.getPatCategorySystem());

        if (patientvisit.getPatVip() != null) mypatientvisithistory.setPatVip(patientvisit.getPatVip());

        if (patientvisit.getAdmissionsDoctor() != null)
            mypatientvisithistory.setAdmissionsDoctor(patientvisit.getAdmissionsDoctor());

        if (patientvisit.getAdmissionsDoctorId() != null)
            mypatientvisithistory.setAdmissionsDoctorId(patientvisit.getAdmissionsDoctorId());

        if (patientvisit.getPatientClass() != null)
            mypatientvisithistory.setPatientClass(patientvisit.getPatientClass());

        if (patientvisit.getPatientId() != null) mypatientvisithistory.setPatientId(patientvisit.getPatientId());

        if (patientvisit.getCustom1() != null) mypatientvisithistory.setCustom1(patientvisit.getCustom1());

        if (patientvisit.getCustom2() != null) mypatientvisithistory.setCustom2(patientvisit.getCustom2());

        if (patientvisit.getCustom3() != null) mypatientvisithistory.setCustom3(patientvisit.getCustom3());

        if (patientvisit.getCustom4() != null) mypatientvisithistory.setCustom4(patientvisit.getCustom4());

        if (patientvisit.getCustom5() != null) mypatientvisithistory.setCustom5(patientvisit.getCustom5());

        if (patientvisit.getPatFinancialClass() != null)
            mypatientvisithistory.setPatFinancialClass(patientvisit.getPatFinancialClass());

        if (patientvisit.getRoomBedCostPrice() != null)
            mypatientvisithistory.setRoomBedCostPrice(patientvisit.getRoomBedCostPrice());

        if (patientvisit.getCourtesyCode() != null)
            mypatientvisithistory.setCourtesyCode(patientvisit.getCourtesyCode());

        if (patientvisit.getCreditRating() != null)
            mypatientvisithistory.setCreditRating(patientvisit.getCreditRating());

        if (patientvisit.getContractCode() != null)
            mypatientvisithistory.setContractCode(patientvisit.getContractCode());

        if (patientvisit.getContractCreateDate() != null)
            mypatientvisithistory.setContractCreateDate(patientvisit.getContractCreateDate());

        if (patientvisit.getContractPrice() != null)
            mypatientvisithistory.setContractPrice(patientvisit.getContractPrice());

        if (patientvisit.getContractTime() != null)
            mypatientvisithistory.setContractTime(patientvisit.getContractTime());

        if (patientvisit.getInterestRateCode() != null)
            mypatientvisithistory.setInterestRateCode(patientvisit.getInterestRateCode());

        if (patientvisit.getBadDebts() != null) mypatientvisithistory.setBadDebts(patientvisit.getBadDebts());

        if (patientvisit.getBadDebtsCreateDate() != null)
            mypatientvisithistory.setBadDebtsCreateDate(patientvisit.getBadDebtsCreateDate());

        if (patientvisit.getBadDebtsCode() != null)
            mypatientvisithistory.setBadDebtsCode(patientvisit.getBadDebtsCode());

        if (patientvisit.getBadDebtsPrice() != null)
            mypatientvisithistory.setBadDebtsPrice(patientvisit.getBadDebtsPrice());

        if (patientvisit.getPatAccountVoided() != null)
            mypatientvisithistory.setPatAccountVoided(patientvisit.getPatAccountVoided());

        if (patientvisit.getPatAccountVoidedDate() != null)
            mypatientvisithistory.setPatAccountVoidedDate(patientvisit.getPatAccountVoidedDate());

        if (patientvisit.getPatDischargeDisposition() != null)
            mypatientvisithistory.setPatDischargeDisposition(patientvisit.getPatDischargeDisposition());

        if (patientvisit.getPatDischargeLocation() != null)
            mypatientvisithistory.setPatDischargeLocation(patientvisit.getPatDischargeLocation());

        if (patientvisit.getPatDietType() != null) mypatientvisithistory.setPatDietType(patientvisit.getPatDietType());

        if (patientvisit.getPatServiceAgencies() != null)
            mypatientvisithistory.setPatServiceAgencies(patientvisit.getPatServiceAgencies());

        if (patientvisit.getPatBedStatus() != null)
            mypatientvisithistory.setPatBedStatus(patientvisit.getPatBedStatus());

        if (patientvisit.getPatAccountStatus() != null)
            mypatientvisithistory.setPatAccountStatus(patientvisit.getPatAccountStatus());

        //限额
        if (patientvisit.getMedicinelimitamount() != null)
            mypatientvisithistory.setMedicinelimitamount(patientvisit.getMedicinelimitamount());

        if (patientvisit.getSickbedlimitamount() != null)
            mypatientvisithistory.setSickbedlimitamount(patientvisit.getSickbedlimitamount());

        if (patientvisit.getExaminelimitamount() != null)
            mypatientvisithistory.setExaminelimitamount(patientvisit.getExaminelimitamount());

        if (patientvisit.getCurelimitamount() != null)
            mypatientvisithistory.setCurelimitamount(patientvisit.getCurelimitamount());


        //前缀
        if (patientvisit.getPrefix() != null) mypatientvisithistory.setPrefix(patientvisit.getPrefix());

        if (patientvisit.getPatDeterPointOfCare() != null)
            mypatientvisithistory.setPatDeterPointOfCare(patientvisit.getPatDeterPointOfCare());

        if (patientvisit.getPatDeterRoom() != null)
            mypatientvisithistory.setPatDeterRoom(patientvisit.getPatDeterRoom());

        if (patientvisit.getPatDeterBed() != null) mypatientvisithistory.setPatDeterBed(patientvisit.getPatDeterBed());

        if (patientvisit.getPatDeterDep() != null) mypatientvisithistory.setPatDeterDep(patientvisit.getPatDeterDep());

        //护理
        if (patientvisit.getTend() != null) mypatientvisithistory.setTend(patientvisit.getTend());

        if (patientvisit.getPatNurseCode() != null)
            mypatientvisithistory.setPatNurseCode(patientvisit.getPatNurseCode());

        if (patientvisit.getPatNurseName() != null)
            mypatientvisithistory.setPatNurseName(patientvisit.getPatNurseName());

        if (patientvisit.getOperCode() != null) mypatientvisithistory.setOperCode(patientvisit.getOperCode());

        if (patientvisit.getOperDate() != null) mypatientvisithistory.setOperDate(patientvisit.getOperDate());

        if (patientvisit.getPatDeterPositionStatus() != null)
            mypatientvisithistory.setPatDeterPositionStatus(patientvisit.getPatDeterPositionStatus());

        if (patientvisit.getPatDeterPositionType() != null)
            mypatientvisithistory.setPatDeterPositionType(patientvisit.getPatDeterPositionType());

        if (patientvisit.getPatDeterBuilding() != null)
            mypatientvisithistory.setPatDeterBuilding(patientvisit.getPatDeterBuilding());

        if (patientvisit.getPatDeterBuilding() != null)
            mypatientvisithistory.setPatDeterBuilding(patientvisit.getPatDeterBuilding());

        if (patientvisit.getPatDeterFloor() != null)
            mypatientvisithistory.setPatDeterFloor(patientvisit.getPatDeterFloor());

        if (patientvisit.getPatDeterDescription() != null)
            mypatientvisithistory.setPatDeterDescription(patientvisit.getPatDeterDescription());

        if (patientvisit.getPatForTempPointOfCare() != null)
            mypatientvisithistory.setPatForTempPointOfCare(patientvisit.getPatForTempPointOfCare());

        if (patientvisit.getPatForTempRoom() != null)
            mypatientvisithistory.setPatForTempRoom(patientvisit.getPatForTempRoom());

        if (patientvisit.getPatForTempBed() != null)
            mypatientvisithistory.setPatForTempBed(patientvisit.getPatForTempBed());

        if (patientvisit.getPatForTempDep() != null)
            mypatientvisithistory.setPatForTempDep(patientvisit.getPatForTempDep());

        if (patientvisit.getPatForTempPositionStatus() != null)
            mypatientvisithistory.setPatForTempPositionStatus(patientvisit.getPatForTempPositionStatus());

        if (patientvisit.getPatForTempPositionType() != null)
            mypatientvisithistory.setPatForTempPositionType(patientvisit.getPatForTempPositionType());

        if (patientvisit.getPatForTempBuilding() != null)
            mypatientvisithistory.setPatForTempBuilding(patientvisit.getPatForTempBuilding());

        if (patientvisit.getPatForTempFloor() != null)
            mypatientvisithistory.setPatForTempFloor(patientvisit.getPatForTempFloor());

        if (patientvisit.getPatForTempDescription() != null)
            mypatientvisithistory.setPatForTempDescription(patientvisit.getPatForTempDescription());

        if (patientvisit.getAdmitDate() != null) mypatientvisithistory.setAdmitDate(patientvisit.getAdmitDate());

        if (patientvisit.getDischargeDate() != null)
            mypatientvisithistory.setDischargeDate(patientvisit.getDischargeDate());

        if (patientvisit.getPatDifference() != null)
            mypatientvisithistory.setPatDifference(patientvisit.getPatDifference());

        if (patientvisit.getPatTotalCost() != null)
            mypatientvisithistory.setPatTotalCost(patientvisit.getPatTotalCost());

        if (patientvisit.getPatTotalDispatch() != null)
            mypatientvisithistory.setPatTotalDispatch(patientvisit.getPatTotalDispatch());

        if (patientvisit.getPatTotalAmountPayable() != null)
            mypatientvisithistory.setPatTotalAmountPayable(patientvisit.getPatTotalAmountPayable());

        if (patientvisit.getPatVisitLogo() != null)
            mypatientvisithistory.setPatVisitLogo(patientvisit.getPatVisitLogo());

        if (patientvisit.getOtherMedicalInstitutions() != null)
            mypatientvisithistory.setOtherMedicalInstitutions(patientvisit.getOtherMedicalInstitutions());

        if (patientvisit.getPatSpareId() != null) mypatientvisithistory.setModifyId(patientvisit.getPatSpareId());

        //婴儿标志
        if (patientvisit.getBabyFlag() != null) mypatientvisithistory.setBabyFlag(patientvisit.getBabyFlag());

        if (patientvisit.getAdmitWeight() != null) mypatientvisithistory.setAdmitWeight(patientvisit.getAdmitWeight());

        if (patientvisit.getAdmitWeightUnit() != null)
            mypatientvisithistory.setAdmitWeightUnit(patientvisit.getAdmitWeightUnit());

        if (patientvisit.getBirthWeight() != null) mypatientvisithistory.setBirthWeight(patientvisit.getBirthWeight());

        if (patientvisit.getBabyWeightUnit() != null)
            mypatientvisithistory.setBabyWeightUnit(patientvisit.getBabyWeightUnit());

        if (patientvisit.getPatCategorySystem() != null)
            mypatientvisithistory.setPatCategorySystem(patientvisit.getPatCategorySystem());

        if (patientvisit.getMothersID() != null) mypatientvisithistory.setMothersID(patientvisit.getMothersID());

        if (patientvisit.getMothersDomain() != null)
            mypatientvisithistory.setMothersDomain(patientvisit.getMothersDomain());

        if (patientvisit.getMothersFlowID() != null)
            mypatientvisithistory.setMothersFlowID(patientvisit.getMothersFlowID());

        if (patientvisit.getMothersFlowDomain() != null)
            mypatientvisithistory.setMothersFlowDomain(patientvisit.getMothersFlowDomain());

        if (patientvisit.getMothersName() != null) mypatientvisithistory.setMothersName(patientvisit.getMothersName());

        if (patientvisit.getPatientSourceName() != null)
            mypatientvisithistory.setPatientSourceName(patientvisit.getPatientSourceName());

        if (patientvisit.getOldPatientId() != null)
            mypatientvisithistory.setOldPatientId(patientvisit.getOldPatientId());

        if (patientvisit.getOldPatientDomain() != null)
            mypatientvisithistory.setOldPatientDomain(patientvisit.getOldPatientDomain());

        if (patientvisit.getOldVisitFlowId() != null)
            mypatientvisithistory.setOldVisitFlowId(patientvisit.getOldVisitFlowId());

        if (patientvisit.getOldVisitFlowDomain() != null)
            mypatientvisithistory.setOldVisitFlowDomain(patientvisit.getOldVisitFlowDomain());

        if (patientvisit.getOldVisitId() != null) mypatientvisithistory.setOldVisitId(patientvisit.getOldVisitId());

        if (patientvisit.getOldPersonId() != null) mypatientvisithistory.setOldPersonId(patientvisit.getOldPersonId());

        if (patientvisit.getOldStatus() != null) mypatientvisithistory.setOldStatus(patientvisit.getOldStatus());

        if (patientvisit.getOldInfo() != null) mypatientvisithistory.setOldInfo(patientvisit.getOldInfo());

        if (patientvisit.getIsEmergency() != null) mypatientvisithistory.setIsEmergency(patientvisit.getIsEmergency());

        if (patientvisit.getPatIpTimes() != null) mypatientvisithistory.setIsEmergency(patientvisit.getIsEmergency());

        if (patientvisit.getDiagnoseIcd() != null) mypatientvisithistory.setDiagnoseIcd(patientvisit.getDiagnoseIcd());

        if (patientvisit.getDiagnoseName() != null)
            mypatientvisithistory.setDiagnoseName(patientvisit.getDiagnoseName());

        if (patientvisit.getNoonCode() != null) mypatientvisithistory.setNoonCode(patientvisit.getNoonCode());

        if (patientvisit.getPaykindCode() != null) mypatientvisithistory.setPaykindCode(patientvisit.getPaykindCode());

        if (patientvisit.getPaykindName() != null) mypatientvisithistory.setPaykindName(patientvisit.getPaykindName());

        if (patientvisit.getSchemaNo() != null) mypatientvisithistory.setSchemaNo(patientvisit.getSchemaNo());

        if (patientvisit.getOrderNo() != null) mypatientvisithistory.setOrderNo(patientvisit.getOrderNo());

        if (patientvisit.getSeeNo() != null) mypatientvisithistory.setSeeNo(patientvisit.getSeeNo());

        if (patientvisit.getBeginTime() != null) mypatientvisithistory.setBeginTime(patientvisit.getBeginTime());

        if (patientvisit.getEndTime() != null) mypatientvisithistory.setEndTime(patientvisit.getEndTime());

        if (patientvisit.getYnBook() != null) mypatientvisithistory.setYnBook(patientvisit.getYnBook());

        if (patientvisit.getYNFR() != null) mypatientvisithistory.setYNFR(patientvisit.getYNFR());

        if (patientvisit.getAppendFlag() != null) mypatientvisithistory.setAppendFlag(patientvisit.getAppendFlag());

        if (patientvisit.getYnSee() != null) mypatientvisithistory.setYnSee(patientvisit.getYnSee());

        if (patientvisit.getSeeDate() != null) mypatientvisithistory.setSeeDate(patientvisit.getSeeDate());

        if (patientvisit.getTriageFlag() != null) mypatientvisithistory.setTriageFlag(patientvisit.getTriageFlag());

        if (patientvisit.getTriageOpcd() != null) mypatientvisithistory.setTriageOpcd(patientvisit.getTriageOpcd());

        if (patientvisit.getTriageDate() != null) mypatientvisithistory.setTriageDate(patientvisit.getTriageDate());

        if (patientvisit.getSeeDpcd() != null) mypatientvisithistory.setSeeDpcd(patientvisit.getSeeDpcd());

        if (patientvisit.getSeeDocd() != null) mypatientvisithistory.setSeeDocd(patientvisit.getSeeDocd());

        if (patientvisit.getOutPatientStatusA() != null)
            mypatientvisithistory.setOutPatientStatusA(patientvisit.getOutPatientStatusA());

        if (patientvisit.getOutPatientStatusB() != null)
            mypatientvisithistory.setOutPatientStatusB(patientvisit.getOutPatientStatusB());

        if (patientvisit.getOutPatientStatusC() != null)
            mypatientvisithistory.setOutPatientStatusC(patientvisit.getOutPatientStatusC());

        if (patientvisit.getInPatientStatusA() != null)
            mypatientvisithistory.setInPatientStatusA(patientvisit.getInPatientStatusA());

        if (patientvisit.getInPatientStatusB() != null)
            mypatientvisithistory.setInPatientStatusB(patientvisit.getInPatientStatusB());

        if (patientvisit.getInPatientStatusC() != null)
            mypatientvisithistory.setInPatientStatusC(patientvisit.getInPatientStatusC());


        mypatientvisithistory.setModifyDate(new java.util.Date());

        patientvisitDao.SavePatientVisitHistory(mypatientvisithistory);
    }

    public void DeletePatientVisit(Person person, PatientVisit patientvisit, List<String> PersonInfo) throws ApplicationException {
        ValidationService validationService = Context.getValidationService();

        validationService.validate(person);

        Person personFound = findPersonUsingIdentifiers(person);

        if (personFound == null) {
            log.warn("While attempting to update a person was not able to locate a record with the given identifier: " + person);
            throw new ApplicationException("Person record to be updated does not exist in the system.");
        }

        if (person.getCustom16() != null && person.getCustom11() != null && person.getCustom6() != null && patientvisit != null) {
            String TempIdStr = person.getCustom6().substring(0, person.getCustom6().indexOf("^"));

            PatientVisit visitFound = findVisitUsingIdentifiers(person);

            if (TempIdStr.equalsIgnoreCase(patientvisit.getVisitFlowId())) {

                if (visitFound == null) {
                    log.warn("While attempting to update a patient visit was not able to locate a record with the given identifier: " + person);

                    throw new ApplicationException("Patient Visit record to be updated does not exist in the system.");
                } else if (personFound != null && visitFound != null) {

                    if (patientvisit != null) {
                        DeletePatientVisit(patientvisit, personFound);

                        SavePatientVisitHistory(visitFound, personFound);
                    }

                    Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.CANCEL_PERSON_PV1_TYPE, person.getCustom29() + "取消病人入院", person, PersonInfo);
                }
            } else {
                log.warn("PID.4.1与PV1.19.1的流水号不匹配");

                throw new ApplicationException("PID.4.1与PV1.19.1的流水号不匹配");
            }
        } else {
            log.warn("HL7消息不正确，可能是PV1为空或者流水号信息填写有误");

            throw new ApplicationException("HL7消息不正确，可能是PV1为空或者流水号信息填写有误");
        }
    }

    public void DeletePersonRecord(Person person) throws ApplicationException {
        ValidationService validationService = Context.getValidationService();

        validationService.validate(person);

        Person personFound = findPersonUsingIdentifiers(person);

        if (personFound == null) {
            log.warn("While attempting to update a person was not able to locate a record with the given identifier: " + person);
            throw new ApplicationException("Person record to be updated does not exist in the system.");
        }

        deletePersonRecord(personFound);

        Context.getAuditEventService().saveAuditEvent(AuditEventTypeDict.DELETE_PERSON_RECORD_TYPE, person.getCustom29() + "删除病人及所有就诊记录", person, null);

    }

    private void deletePerson(Person person) {
        java.util.Date now = new java.util.Date();

        AppUser currUser = Context.getUserContext().getUser();

        Set<PersonIdentifierEMPI> ids = person.getPersonIdentifiers();

        for (PersonIdentifierEMPI id : ids) {

            id.setDateVoided(now);

            id.setAppUserByVoidedById(currUser);
        }

        person.setDateCreated(now);

        person.setAppUserByCreatorId(currUser);

        person.setDateChanged(now);

        person.setAppUserByChangedById(currUser);

        person.setDateVoided(now);

        person.setAppUserByVoidedById(Context.getUserContext().getUser());

        log.trace("Voiding the person record: " + person);

        personDao.updatePerson(person);

    }

    private void deletePersonRecord(Person person) {
        java.util.Date now = new java.util.Date();

        AppUser currUser = Context.getUserContext().getUser();

        Set<PersonIdentifierEMPI> ids = person.getPersonIdentifiers();

        for (PersonIdentifierEMPI id : ids) {

            id.setDateVoided(now);

            id.setAppUserByVoidedById(currUser);
        }

        person.setDateCreated(now);

        person.setAppUserByCreatorId(currUser);

        person.setDateChanged(now);

        person.setAppUserByChangedById(currUser);

        person.setDateVoided(now);

        person.setAppUserByVoidedById(Context.getUserContext().getUser());

        log.trace("Voiding the person record: " + person);

        personDao.updatePerson(person);

        List<PatientVisit> patientvisits = new ArrayList<PatientVisit>();

        patientvisits = patientvisitDao.getPatientVisitsById(person);

        if (patientvisits != null) {
            for (PatientVisit patientvisit : patientvisits) {
                patientvisit.setModifyDate(now);

                patientvisit.setVoidedDate(now);

                patientvisitDao.updatePatientVisit(patientvisit);
            }
        }
    }

    private void deleteGlobalPerson(Person person1, Person person2) {
        java.util.Date now = new java.util.Date();

        AppUser currUser = Context.getUserContext().getUser();

        Set<PersonIdentifierEMPI> ids = person1.getPersonIdentifiers();


        for (PersonIdentifierEMPI id : ids) {
            if (id.getIdentifier().equals(person1.getEmpi())) {
                id.setPerson(person2);
            } else {
                id.setDateVoided(now);

                id.setAppUserByVoidedById(currUser);

            }
        }

        person1.setDateCreated(now);

        person1.setAppUserByCreatorId(currUser);

        person1.setDateChanged(now);

        person1.setAppUserByChangedById(currUser);

        person1.setDateVoided(now);

        person1.setAppUserByVoidedById(Context.getUserContext().getUser());

        log.trace("Voiding the person record: " + person1);

        personDao.updatePerson(person1);

        List<PatientVisit> patientvisits = new ArrayList<PatientVisit>();

        patientvisits = patientvisitDao.getPatientVisitsById(person1);

        for (PatientVisit patientvisit : patientvisits) {
            patientvisit.setModifyDate(now);

            patientvisit.setVoidedDate(now);

            patientvisitDao.updatePatientVisit(patientvisit);
        }

    }

    private void findAndDeleteRecordLinks(Person person) {
        List<PersonLink> links = personLinkDao.getPersonLinks(person);
        for (PersonLink link : links) {
            log.trace("Deleting the person link: " + link);
            personLinkDao.remove(PersonLink.class, link.getPersonLinkId());
        }
    }

    private Person getOtherPerson(PersonLink originalLink, Person person) {
        if (String.valueOf(originalLink.getPersonByLhPersonId().getPersonId()).equals(String.valueOf(person.getPersonId()))) {
            return originalLink.getPersonByRhPersonId();
        }
        return originalLink.getPersonByLhPersonId();
    }

    private void findAndUpdateRecordLinks(Person person) throws ApplicationException {
        List<PersonLink> currLinks = personLinkDao.getPersonLinks(person);

        for (PersonLink link : currLinks) {
            log.trace("Deleting the person link during an update: " + link);
            personLinkDao.remove(PersonLink.class, link.getPersonLinkId());
        }
        findAndProcessAddRecordLinks(person);
    }

    private void findAndProcessAddRecordLinks(Person person) throws ApplicationException {
        Record record = new Record(person);
        record.setRecordId(new Long(person.getPersonId()));
        MatchingService matchingService = Context.getMatchingService();

        Set<RecordPair> links = matchingService.match(record);

        record = null;
        //System.out.println(links.size());

        for (RecordPair recordPair : links) {
            PersonLink personLink = getPersonLinkFromRecordPair(recordPair);

            log.trace("Adding link: " + personLink);
            personLinkDao.addPersonLink(personLink);
        }
    }

    private PersonLink getPersonLinkFromRecordPair(RecordPair recordPair) {
        PersonLink personLink = new PersonLink();
        personLink.setDateCreated(new java.util.Date());
        personLink.setAppUser(Context.getUserContext().getUser());
        personLink.setPersonByLhPersonId((Person) recordPair.getLeftRecord().getObject());
        personLink.setPersonByRhPersonId((Person) recordPair.getRightRecord().getObject());
        personLink.setWeight(recordPair.getWeight());
        return personLink;
    }

    private PersonLink getPersonLinkFromRecordPair(Person leftPerson, Person rightPerson) {
        PersonLink personLink = new PersonLink();
        personLink.setDateCreated(new java.util.Date());
        personLink.setAppUser(Context.getUserContext().getUser());
        personLink.setPersonByLhPersonId(leftPerson);
        personLink.setPersonByRhPersonId(rightPerson);
        personLink.setWeight(com.ats.aempi.EMPIConstants.MERGE_RECORDS_WEIGHT);
        return personLink;
    }

    //pm-2012-07-06,修改增加 globalid
    private void generateGlobalId(Person person, List<String> myempi) throws ApplicationException {
        //获取globalid,判断是否要生成globalid
        GlobalIdentifier globalId = Context.getConfiguration().getGlobalIdentifier();

        log.fatal("全局域ID为：" + globalId);

        if (globalId != null && !globalId.isAssignGlobalIdentifier()) {
            return;
        }

        IdentifierDomainDict globalIdentDomain = globalId.getIdentifierDomain();


        if (myempi.size() > 0) {

            person.setEmpi(myempi.get(0).toString());

            return;

        } else if (myempi.size() == 0) {
            //根据uuid生成globalid
            PersonIdentifierEMPI identifier = generateGlobalIdentifier(globalIdentDomain, person);

            if (identifier.getIdentifier() != null) {
                identifier.setCustom1(identifier.getIdentifier());
            }

            //personidentifier表增加一条globalid记录
            person.addPersonIdentifier(identifier);

            //person表填入empi信息,用globalid填入
            if (identifier.getIdentifier() != null) {
                person.setEmpi(identifier.getIdentifier());
            }
        }


//		// Check to see if the person already has a global identifier
//		for (PersonIdentifier identifier : person.getPersonIdentifiers()) {
//			IdentifierDomainDict identifierDomain = identifier.getIdentifierDomainDict();
//			//if (identifierDomain != null && identifierDomain.equals(globalIdentDomain))
//			if (identifierDomain.equals(globalIdentDomain)){
//				log.debug("Person already has an global identifier assigned: " + identifier);
//				return;
//			}
//		}


    }

    //PANMIN-20150629 自动MERGE
    private void generateGlobalIdAndMerge(Person person, List<String> myempi, int controlID) throws ApplicationException, SQLException {
        //获取globalid,判断是否要生成globalid
        GlobalIdentifier globalId = Context.getConfiguration().getGlobalIdentifier();

        log.fatal("全局域ID为：" + globalId);

        if (globalId != null && !globalId.isAssignGlobalIdentifier()) {
            return;
        }

        IdentifierDomainDict globalIdentDomain = globalId.getIdentifierDomain();

        //根据uuid生成globalid
        PersonIdentifierEMPI myidentifier = generateGlobalIdentifier(globalIdentDomain, person);

        String empi = myidentifier.getIdentifier();

        String MergeStatus = null;

        if (controlID == 1) {
            MergeStatus = NewEmpiMergeProcedure(myempi, empi, 1);
        } else {
            MergeStatus = NewEmpiMergeProcedure(myempi, empi, 1);
        }

        if (MergeStatus.equalsIgnoreCase("false")) {
            throw new ApplicationException("EMPI合并异常，请检查相关数据后重新上传");
        }

        person.setEmpi(empi);

    }

    @SuppressWarnings("unused")
    private int RemovePersonLinkForCancel(Person person) {
        List<String> tempStr = personDao.getPersonidByEmpi(person.getEmpi());

        if (tempStr.size() == 0) {
            return 0;
        }

        for (String str : tempStr) {
            List<Person> tempPerson = personDao.getEmpiById(str);

            //去除关联LINK
            List<PersonLink> currLinks = personLinkDao.getPersonLinks(tempPerson.get(0));

            for (PersonLink link : currLinks) {
                log.trace("Deleting the person link during an update: " + link);

                personLinkDao.remove(PersonLink.class, link.getPersonLinkId());
            }
        }

        return 0;
    }

    private void updatePersonForID(Person person, PersonIdentifierEMPI changeIdentifier) {
        java.util.Date now = new java.util.Date();

        List<PatientVisit> patientvisits = new ArrayList<PatientVisit>();

        patientvisits = patientvisitDao.getPatientVisitsById(person);

        if (patientvisits != null) {
            for (PatientVisit patientvisit : patientvisits) {
                patientvisit.setPatientId(changeIdentifier.getIdentifier());

                patientvisitDao.updatePatientVisit(patientvisit);
            }
        }

        AppUser currUser = Context.getUserContext().getUser();

        Set<PersonIdentifierEMPI> ids = person.getPersonIdentifiers();

        for (PersonIdentifierEMPI id : ids) {
            if (id.getIdentifier().equalsIgnoreCase(person.getCustom16())) {
                id.setIdentifier(changeIdentifier.getIdentifier());
            }
        }

        person.setCustom16(changeIdentifier.getIdentifier());

        person.setDateChanged(now);

        person.setAppUserByChangedById(currUser);

        personDao.updatePerson(person);

    }

    private PersonIdentifierEMPI generateGlobalIdentifier(IdentifierDomainDict globalIdentifierDomain, Person person) {
        //UUID uuid = new UUID();
        PersonIdentifierEMPI identifier = new PersonIdentifierEMPI();
        identifier.setIdentifier(UUID.randomUUID().toString());
        identifier.setIdentifierDomainDict(globalIdentifierDomain);
        identifier.setPerson(person);
        return identifier;
    }

    //不明白其作用，暂时废了
    private void populateCustomFields(Person person) {
        List<CustomField> customFields = Context.getConfiguration().getCustomFields();
        TransformationService transformationService = Context.getTransformationService();
        for (CustomField customField : customFields) {
            log.trace("Need to generate a value for field " + customField.getSourceFieldName() + " using function " +
                    customField.getTransformationFunctionName() + " and save it as field " + customField.getFieldName());
            try {
                String value = BeanUtils.getProperty(person, customField.getSourceFieldName());
                log.debug("Obtained a value of " + value + " for field " + customField.getSourceFieldName());
                if (value != null) {
                    Object transformedValue = transformationService.transform(customField.getTransformationFunctionName(), value);
                    if (transformedValue instanceof String) {
                        BeanUtils.setProperty(person, customField.getFieldName(), transformedValue);
                        log.debug("Custom field " + customField.getFieldName() + " has value " + BeanUtils.getProperty(person,
                                customField.getFieldName()));
                    }
                }
            } catch (Exception e) {
                log.error("Failed while trying to obtain property for field " + customField.getSourceFieldName() + ":" + e.getMessage(), e);
            }
        }
    }

    public List<String> getPersonLinks(String personid) {
        return personLinkDao.getPersonLinkRecord(personid);
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public PersonLinkDao getPersonLinkDao() {
        return personLinkDao;
    }

    public void setPersonLinkDao(PersonLinkDao personLinkDao) {
        this.personLinkDao = personLinkDao;
    }

    public PatientVisitDao getPatientvisitDao() {
        return patientvisitDao;
    }

    public void setPatientvisitDao(PatientVisitDao patientvisitDao) {
        this.patientvisitDao = patientvisitDao;
    }

    /**
     * @return the empiDao
     */
    public EmpiDao getEmpiDao() {
        return empiDao;
    }

    /**
     * @param empiDao the empiDao to set
     */
    public void setEmpiDao(EmpiDao empiDao) {
        this.empiDao = empiDao;
    }
}
