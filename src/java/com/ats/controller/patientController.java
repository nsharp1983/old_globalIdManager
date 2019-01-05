package com.ats.controller;

import com.ats.aempi.apixpdqadapter.ConversionHelper;
import com.ats.aempi.context.Context;
import com.ats.aempi.model.NewPerson;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.service.PersonManagerService;
import com.ats.aempi.util.JsonDateValueProcessor;
import com.ats.aempi.vo.PersonVo;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IActorDescription;
import com.ats.aexchange.datamodel.*;
import com.ats.aexchange.utils.DateUtil;
import com.ats.apixpdq.api.IPixManagerAdapter;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.web.beans.PixManagerBean;
import com.ats.apixpdq.web.servlet.PixPdqConfigurationLoader;
import com.ats.util.PropertiesReady;

import com.ats.vo.PatientVo;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 12397 on 2018/9/26.
 */
@Controller
public class patientController {
    protected Log logger = LogFactory.getLog(getClass());
    Set<Identifier> ids = null;
    PixPdqConfigurationLoader loader;
    IPixManagerAdapter pixManagerAdapter;


    /**
     * 患者注册接口
     *
     * @param response
     * @param pixManagerBean
     */
    @RequestMapping("/patient/createPatient.do")
    public void regist(HttpServletResponse response, PixManagerBean pixManagerBean) {
        try {
            String systemId = PropertiesReady.GetName("systemid", "apixpdq.properties");
            pixManagerBean.setSystemid(systemId);
            setIds();//查询所有机构

            Patient patient = toPatient(pixManagerBean);//转换了类型
            Person addPerson = pixManagerAdapter.registPatient(patient);
            if (addPerson != null) response.getWriter().print(true);
            else response.getWriter().print(false);
        } catch (Exception e) {
            logger.error("注册失败", e);
        }
    }


    /**
     * 患者信息更新
     *
     * @param response
     * @param pixManagerBean
     */
    @RequestMapping("/patient/updatePatient.do")
    public void updatePation(HttpServletResponse response, PixManagerBean pixManagerBean) {
        try {
            String systemId = PropertiesReady.GetName("systemid", "apixpdq.properties");
            pixManagerBean.setSystemid(systemId);
            setIds();//查询所有机构

            Patient patient = toPatient(pixManagerBean);//转换了类型
            pixManagerAdapter.updatePatient(patient, null);
            response.getWriter().print(true);
        } catch (Exception e) {
            logger.error("更新失败", e);
        }
    }


    /**
     * 自动判断新增或更新
     *
     * @param pixManagerBean
     */
    @RequestMapping("/patient/createOrUpdatePatient.do")
    public void createOrUpdatePatient(
            HttpServletRequest request,
            HttpServletResponse response,
            PixManagerBean pixManagerBean
    ) {
        try {

            request.setCharacterEncoding("UTF-8");
            Person addPerson;
            //接收参数
            String jsonString;
            jsonString = getBodyString(request.getReader());
            JSONObject ob ;
            if (StringUtils.isNotEmpty(jsonString)){
                ob = JSONObject.fromObject(jsonString);
                pixManagerBean = (PixManagerBean) JSONObject.toBean(ob, PixManagerBean.class);
                logger.info("传入患者名为" + pixManagerBean.getFname());


//匹配所属机构
                setIds();//查询所有机构(从PixPdqClientDomains.xml中获取)
                Patient patient = toPatient(pixManagerBean);//转换了类型

//查询该病人是否已经注册
                Person person = null;
                PatientIdentifier patientIdentifier = getPatientID(pixManagerBean);//设置病人所属机构和域id和patientId
                PersonManagerService personManagerService = Context.getPersonManagerService();


//吉大一特殊需求(姓名、身份证号、出生日期)判断病人是否已经注册
                String patientName = pixManagerBean.getFname();
                String identityCard=pixManagerBean.getIdentityCard();
                String birthday=pixManagerBean.getDob();
                logger.info("开始验证患者是否已经注册过，传入的患者名："+ patientName + "身份证号"+identityCard+"出生日期"+birthday);
                if (patientIdentifier != null
                    && StringUtils.isNotEmpty(patientName)
                ) {
                    PersonIdentifierEMPI personIdentifierEMPI = ConversionHelper.getPersonIdentifier(patientIdentifier);

                    personIdentifierEMPI.setPerson(new Person());
                    personIdentifierEMPI.getPerson().setName(patientName);
                    personIdentifierEMPI.getPerson().setIdentityNo(identityCard);

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    if (StringUtils.isNotEmpty(birthday))personIdentifierEMPI.getPerson().setDateOfBirth(format.parse(birthday));
                    logger.info("开始在controller中查询患者是否已存在： " + pixManagerBean.getFname());
                    person = personManagerService.findByPatientId(personIdentifierEMPI);
                }



//新增或更新
                if (person==null){
                    logger.info("开始注册患者： " + pixManagerBean.getFname());
                    addPerson = pixManagerAdapter.registPatient(patient);
                    if (addPerson!=null&&StringUtils.isNotEmpty(addPerson.getGivenName()))
                        logger.info("新增成功 " + pixManagerBean.getFname() + "的empi信息为"+addPerson.getEmpi());
                }else {//更新
                    logger.info("开始更新患者： " + pixManagerBean.getFname());
                    if (StringUtils.isNotEmpty(patient.getCustom6()))
                        pixManagerAdapter.registPatient(patient);
                    pixManagerAdapter.updatePatient(patient, null);
                    addPerson = person;
                    if (StringUtils.isNotEmpty(addPerson.getGivenName()))
                        logger.info("更新成功！ " + pixManagerBean.getFname() + "的empi信息为"+addPerson.getEmpi());
                }

                response.getWriter().print(addPerson.getEmpi());
            } else
                logger.error("传入患者信息为空");
        } catch (Exception e) {
            logger.error("更新或新增失败，服务出错",  e);
        }
    }

    //根据传入的信息拼接病人id
    private PatientIdentifier getPatientID(PixManagerBean bean) {
        //判断发布机构和患者id是否都为空
        if (bean.getSystemid() == null && bean.getLocalid() == null) {
            return null;
        }
        PatientIdentifier pid ;
        pid = new PatientIdentifier();

        for (Identifier id : ids) {//病人所属机构名和机构域id

                if (id.getNamespaceId().equalsIgnoreCase(bean.getSystemid())
                ||id.getUniversalId().equals(bean.getDomainId())
                ) {
                    pid.setAssigningAuthority(id);
                }

        }

        if (bean.getLocalid() != null && !bean.getLocalid().equalsIgnoreCase("")) {//病人id
            pid.setId(bean.getLocalid());
        }
        return pid;
    }

    /**
     * 唐新月接口专用----
     *
     * @param pixManagerBean
     */
    @RequestMapping("/patient/registAndReturnVisit.do")
    public void registAndReturnVisit(
            HttpServletRequest request,
            HttpServletResponse response,
            PixManagerBean pixManagerBean
    ) {
        try {

            Map<String,Object> result=new HashMap<>();

            request.setCharacterEncoding("UTF-8");
            Person addPerson;
            //接收参数
            String jsonString;
            jsonString = getBodyString(request.getReader());
            JSONObject ob ;
            if (StringUtils.isNotEmpty(jsonString)){
                ob = JSONObject.fromObject(jsonString);
                pixManagerBean = (PixManagerBean) JSONObject.toBean(ob, PixManagerBean.class);
                logger.info("传入患者名为" + pixManagerBean.getFname());

                setIds();//查询所有机构(从PixPdqClientDomains.xml中获取)

                //查询患者ADT
                PatientVisit resultPatientVisit=null;
                if (StringUtils.isNotEmpty(pixManagerBean.getVisistFlowIdAndDomain())){
                    Person person=new Person();
                    person.setCustom6(pixManagerBean.getVisistFlowIdAndDomain());
                    person.setCustom11(pixManagerBean.getDomainId());
                    person.setCustom16(pixManagerBean.getLocalid());
                    resultPatientVisit=pixManagerAdapter.queryPatientVisit(person);
                }

//只查询visit不新增visit
                pixManagerBean.setVisistFlowIdAndDomain(null);


//匹配所属机构

                Patient patient = toPatient(pixManagerBean);//转换了类型

//查询该病人是否已经注册
                Person oldPerson = null;
                PatientIdentifier patientIdentifier = getPatientID(pixManagerBean);//设置病人所属机构和域id和patientId
                PersonManagerService personManagerService = Context.getPersonManagerService();


//吉大一特殊需求(姓名、身份证号、出生日期)判断病人是否已经注册
                String patientName = pixManagerBean.getFname();
                String identityCard=pixManagerBean.getIdentityCard();
                String birthday=pixManagerBean.getDob();
                logger.info("开始验证患者是否已经注册过，传入的患者名："+ patientName + "身份证号"+identityCard+"出生日期"+birthday);
                if (patientIdentifier != null
                        && StringUtils.isNotEmpty(patientName)
                ) {
                    PersonIdentifierEMPI personIdentifierEMPI = ConversionHelper.getPersonIdentifier(patientIdentifier);

                    personIdentifierEMPI.setPerson(new Person());
                    personIdentifierEMPI.getPerson().setName(patientName);
                    personIdentifierEMPI.getPerson().setIdentityNo(identityCard);

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    if (StringUtils.isNotEmpty(birthday))personIdentifierEMPI.getPerson().setDateOfBirth(format.parse(birthday));
                    logger.info("开始在controller中查询患者是否已存在： " + pixManagerBean.getFname());
                    oldPerson = personManagerService.findByPatientId(personIdentifierEMPI);
                }


//新增或更新
                if (oldPerson==null){
                    logger.info("开始注册患者： " + pixManagerBean.getFname());
                    addPerson = pixManagerAdapter.registPatient(patient);
                    if (addPerson!=null&&StringUtils.isNotEmpty(addPerson.getGivenName())){
                        logger.info("新增成功 " + pixManagerBean.getFname() + "的empi信息为"+addPerson.getEmpi());
                    }

                }else {//更新
                    logger.info("开始更新患者： " + pixManagerBean.getFname());
                    if (StringUtils.isNotEmpty(patient.getCustom6()))
                        pixManagerAdapter.registPatient(patient);
                    pixManagerAdapter.updatePatient(patient, null);
                    addPerson = oldPerson;
                    if (StringUtils.isNotEmpty(addPerson.getGivenName())){
                        logger.info("更新成功！ " + pixManagerBean.getFname() + "的empi信息为"+addPerson.getEmpi());
                    }
                }





                JsonConfig jsonConfig=new JsonConfig();
//        jsonConfig.registerJsonValueProcessor(Person.class,Date.class,new JsonValueProcessor2());
                jsonConfig.setJavaPropertyFilter(new PropertyFilter() {
                    @Override
                    public boolean apply(Object object, String key, Object value) {
                        boolean result=true;
                        if(null==value){
                            result=false;
                        }
                        return result;
                    }
                });

                JSONObject jsonObject1=JSONObject.fromObject(resultPatientVisit,jsonConfig);
                String patientVisitJ=jsonObject1.toString();
                List<NewPerson> list =getPersonByParam(addPerson);


                String personJ="";
                if (CollectionUtils.isNotEmpty(list)){
                    JSONObject jsonObject2=JSONObject.fromObject(new PersonVo(list.get(0)));
                    personJ=jsonObject2.toString();
                }


                result.put("patientVisit",patientVisitJ);
                result.put("person",personJ);
                response.getWriter().print(result);
            } else
                logger.error("传入患者信息为空");
        } catch (Exception e) {
            logger.error("更新或新增失败，服务出错",  e);
        }
    }


    /**
     * 按条件查询患者信息
     *
     * @param request
     * @param response
     * @param person
     */
    @RequestMapping("/patient/queryPatient.do")
    public void queryPatint(
            HttpServletRequest request,
            HttpServletResponse response,
            Person person) {
        try {
            //从请求中获取参数并赋值
            requestForPerson(request, person);

            //查询患者信息
            List<NewPerson> personList = getPersonByParam(person);

            //转换为Person类型


            List<PatientVo> patientVos = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(personList))
                patientVos = personToPatientVo(personList);

            //返回json格式的参数
            String result = "";
            if (CollectionUtils.isNotEmpty(patientVos)) {
                JSONArray ja = JSONArray.fromObject(patientVos);
                result = ja.toString();
            }

            if (StringUtils.isNotEmpty(result))
                logger.info("开始返回数据，返回的数据为" + result);

            response.getWriter().print(result);

        } catch (Exception e) {
            logger.error("查询失败,程序出错",e);
        }

    }





    /**
     * 按条件查询患者信息（管理界面使用）
     *
     * @param request
     * @param response
     * @param person
     */
    @RequestMapping("/patient/queryPersonForManager.do")
    public void queryPersonForManager(
            HttpServletRequest request,
            HttpServletResponse response,
            Person person) {
        try {

            //从请求中提取参数
            requestForPerson(request, person);

            //查询
            List<NewPerson> personList = getPersonByParam(person);

            //返回json格式的参数
            String result = "";
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
            if (CollectionUtils.isNotEmpty(personList)) {
                List<JSONObject> jsonObjects=new ArrayList<>();
                for (NewPerson newPerson:personList){
                    JSONObject jo = JSONObject.fromObject(newPerson, jsonConfig);
                    jo.put("totalSize",newPerson.toltalNum());
                    jsonObjects.add(jo);
                }

                JSONArray ja = JSONArray.fromObject(jsonObjects);
                result = ja.toString();
            }

            if (StringUtils.isNotEmpty(result))
                logger.info("开始返回数据，返回的数据为" + result);

            response.getWriter().print(result);

        } catch (Exception e) {
            logger.error("查询失败,程序出错",e);
        }

    }



    //-----------private---------------


    private List<NewPerson> getPersonByParam(Person person) throws Exception {
        PersonManagerService personManagerService = Context.getPersonManagerService();
        logger.info("开始查询患者信息");
        if (personManagerService != null)
            logger.info("personManagerService" + personManagerService.toString());
        else
            logger.info("personManagerService为空");
        List<NewPerson> personList = personManagerService.queryPatients(person);

        if (CollectionUtils.isNotEmpty(personList))
            logger.info("查询结束,查询出" + personList.size() + "个患者信息");
        else
            logger.info("没有查询出患者信息");


        return personList;
    }


    //request向person赋值
    private void requestForPerson(HttpServletRequest request, Person person) throws IOException {
        request.setCharacterEncoding("UTF-8");
        //接收参数
        String jsonString;
        jsonString = getBodyString(request.getReader());
        if (StringUtils.isEmpty(jsonString))
            logger.error("接收到的查询参数为空");
        else
            logger.info("接收到的查询参数json为 " + jsonString);
        JSONObject ob = new JSONObject();
        if (StringUtils.isNotEmpty(jsonString))
            ob = JSONObject.fromObject(jsonString);

        //赋值
        if (StringUtils.isEmpty(person.getIdentityNo()))
            person.setIdentityNo((String) ob.get("identityCard"));
        if (StringUtils.isEmpty(person.getName()))
            person.setName((String) ob.get("name"));
        if (StringUtils.isEmpty(person.getInsuranceNo()))
            person.setInsuranceNo((String) ob.get("insuranceNo"));
        if (StringUtils.isEmpty(person.getEmpi()))
            person.setEmpi((String) ob.get("empi"));

//        person.setThisPageNo((Integer) ob.get("pageNo"));
//        person.setThisPageSize((Integer) ob.get("pageSize"));

    }


    private List<PatientVo> personToPatientVo(List<NewPerson> personList) {
        List<PatientVo> patientVos = new ArrayList<PatientVo>();
        for (NewPerson person : personList) {
            PatientVo patientVo = new PatientVo();
            patientVo.setEmpi(person.getEmpi());
            patientVo.setName(person.getName());
            patientVo.setBirthday(person.getGenderName());
            if (person.getDateOfBirth() != null) {
                DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String date = format1.format(person.getDateOfBirth());
                patientVo.setBirthday(date);
            }
            patientVo.setInsuranceNo(person.getInsuranceNo());
            patientVo.setIdentityNo(person.getIdentityNo());
            patientVo.setBirthPlace(person.getBirthPlace());
            patientVo.setHealthCard(person.getHealthCard());
            patientVo.setCitizenCard(person.getCitizenCard());
            patientVo.setMedicalCertificate(person.getMedicalCertificate());
            patientVo.setMeicarePerson(person.getMeicarePerson());
            patientVo.setElderCertificate(person.getElderCertificate());
            patientVo.setOpcaseno(person.getOpcaseno());
            patientVo.setFlowFieldId(person.getCustom3());
            patientVo.setFlowFieldName(person.getCustom2());
            patientVo.setHisFlowFieldId(person.getCustom19());
            patientVo.setHisVisitNo(person.getCustom15());
            patientVo.setMedicalRecordNo(person.getCustom24());
            patientVo.setSocialCard(person.getSsn());

            patientVos.add(patientVo);
        }
        return patientVos;
    }


    //--------private-----------

    //查询所有机构
    private void setIds() {
        loader = PixPdqConfigurationLoader.getInstance();
        Collection<IActorDescription> actors = loader.getActorDescriptions();
        IActorDescription actor = null;
        for (IActorDescription actorDescription : actors) {
            if (actorDescription.getType().equalsIgnoreCase("PixManager")) {
                actor = actorDescription;
                break;
            }
        }
        Set<Identifier> defaultDomains = Configuration.getAllDomains(actor);
        pixManagerAdapter = PixPdqFactory.getPixManagerAdapter();
        ids = pixManagerAdapter.getDomainIdentifiers(defaultDomains);//所有机构类型
    }


    private String getBodyString(BufferedReader br) throws IOException {
        String inputLine;
        String str = "";
        while ((inputLine = br.readLine()) != null) {
            str += inputLine;
        }
        br.close();
        return str;
    }


    //bean转Patient类
    private Patient toPatient(PixManagerBean bean) {
        Patient patient = new Patient();

        if (StringUtils.isNotEmpty(bean.getVisistFlowIdAndDomain()))
            patient.setCustom6(bean.getVisistFlowIdAndDomain());

        if (StringUtils.isNotEmpty(bean.getPatReAdmission()))
            patient.setPatReAdmission(bean.getPatReAdmission());

        //patientVisit表使用数据
        if(StringUtils.isNotEmpty(bean.getLocalid()))
            patient.setPatientId(bean.getLocalid());


        if (bean.getPhone() != null) {
            patient.setTelPhone(bean.getPhone());
        }

        if (bean.getIdentityCard() != null) {
            patient.setIdentityNO(bean.getIdentityCard());
        }

        if (bean.getInsuranceCard() != null) {
            patient.setInsuranceNO(bean.getInsuranceCard());
        }

        if (bean.getSocialCard() != null) {
            patient.setSocialcardNO(bean.getSocialCard());
        }
        if (StringUtils.isNotEmpty(bean.getFname())) {
            PersonName personName = new PersonName();
            personName.setLastName(bean.getFname());
            patient.setPatientName(personName);
        }


        if (bean.getGender() != null) {
            if (bean.getGender().equalsIgnoreCase("男")) {
                patient.setAdministrativeSex(SharedEnums.SexType.MALE);
                patient.setGenderName("男");
            } else if (bean.getGender().equalsIgnoreCase("女")) {
                patient.setAdministrativeSex(SharedEnums.SexType.FEMALE);
                patient.setGenderName("女");
            }else {
                patient.setGenderName("其它");
            }
        }

        patient.setBirthDateTime(_convertStringToCalendar(bean.getDob()));

        List<Address> addList = new ArrayList<Address>();

        Address address = getAddress(bean);

        if (address != null) addList.add(address);

        patient.setAddresses(addList);

        List<PatientIdentifier> pids = new ArrayList<PatientIdentifier>();

        pids.add(getPatientID(bean));

        patient.setPatientIds(pids);

        patient.setEmail(bean.getEmail());


        //360修改----------
        patient.setPatCuurentDep(bean.getPatCuurentDep());
        if (StringUtils.isNotEmpty(bean.getAdmitDate())){
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                patient.setAdmitDate(format.parse(bean.getAdmitDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        patient.setCustom2(bean.getCustom2());
        patient.setCustom3(bean.getCustom3());
        patient.setCustom4(bean.getCustom4());
        patient.setCustom5(bean.getCustom5());
        patient.setOutPatientStatusA(bean.getOutPatientStatusA());
        patient.setDiagnoseName(bean.getDiagnoseName());
        patient.setYnSee(bean.getYnsee());
        patient.setPatForTempBed(bean.getPatForTempBed());
        patient.setPatDischargeLocation(bean.getPatDischargeLocation());






        return patient;
    }



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


}
