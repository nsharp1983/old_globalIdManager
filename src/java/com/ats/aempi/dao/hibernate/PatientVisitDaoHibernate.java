package com.ats.aempi.dao.hibernate;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ats.aempi.dao.PatientVisitDao;
import com.ats.aempi.model.ExtendForPerson;
import com.ats.aempi.model.PatientVisit;
import com.ats.aempi.model.PatientVisitHistory;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.PersonIdentifierEMPI;
import com.ats.aempi.model.PersonInOut;
import com.ats.aempi.model.TransPatientVisit;




import org.springframework.orm.hibernate3.HibernateCallback;

public class PatientVisitDaoHibernate extends UniversalDaoHibernate implements PatientVisitDao
{
	//获取全部的扩展字段，2012-11-19
	@SuppressWarnings("unchecked")
	public List<ExtendForPerson> GetExtendFields() {
		log.debug("finding all ExtendForPerson instances");
		try {
			String queryString = "from ExtendForPerson";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//获取指定属性的扩展字段
	public List<ExtendForPerson> FindExtendByProperty(String propertyName, Object value) {
		log.debug("finding Extendforperson instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ExtendForPerson as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void addPatientVisit(PatientVisit patientvisit) 
	{
		log.debug("Saving PatientVisit record: " + patientvisit);
		getHibernateTemplate().saveOrUpdate(patientvisit);
		getHibernateTemplate().flush();
		log.debug("Finished saving the PatientVisit.");
	}
	
	public void addPersonInOut(PersonInOut personinout) 
	{
		getHibernateTemplate().saveOrUpdate(personinout);
		getHibernateTemplate().flush();
	}
	
	public void SaveTransPatientVisit(TransPatientVisit patientvisit) 
	{
		log.debug("Saving PatientVisit record: " + patientvisit);
		getHibernateTemplate().saveOrUpdate(patientvisit);
		getHibernateTemplate().flush();
		log.debug("Finished saving the PatientVisit.");
	}

	public void updatePatientVisit(PatientVisit patientvisit) 
	{
		log.debug("Saving patientvisit record: " + patientvisit);
		
		getHibernateTemplate().merge(patientvisit);
		
		getHibernateTemplate().flush();
		
		log.debug("Finished saving the patientvisit.");
		
	}
	
	public PatientVisit getPatientVisitById(final Person person)
	{
		return (PatientVisit) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				PatientVisit patientvisit = null;
				
				String TempIdStr=null,TempDomainStr=null;
				
				System.out.println(person.getCustom6());
				
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
				
					//System.out.println(person.getCustom16()+person.getCustom11());
					
					Criteria criteria = session.createCriteria(PatientVisit.class)
										       .add(Restrictions.isNull("voidedDate"))
										       .add(Restrictions.eq("patientId", person.getCustom16()))
										       .add(Restrictions.eq("personDomain", person.getCustom11()))
										       .add(Restrictions.eq("visitFlowId", TempIdStr))
										       .add(Restrictions.eq("visitFlowDomain", TempDomainStr));
				
					List<PatientVisit> list = criteria.list();
				
					log.debug("Query by identifier returned: " + list.size() + " elements.");
				
					if (list.size() == 0) 
					{
						return null;
					}
				
					patientvisit = list.get(0);
				
					session.evict(patientvisit);
				
				}
				
				return patientvisit;
				
			}
		}
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<PatientVisit> getPatientVisitsById(final Person person)
	{
		return (List<PatientVisit>) getHibernateTemplate().execute(new HibernateCallback() 
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				
				Criteria criteria = session.createCriteria(PatientVisit.class)
										   .add(Restrictions.isNull("voidedDate"))
										   .add(Restrictions.eq("patientId", person.getCustom16()))
										   .add(Restrictions.eq("personDomain", person.getCustom11()));
				
				List<PatientVisit> list = criteria.list();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				
				if (list.size() == 0) 
				{
					return null;
				}
				
				//PatientVisit patientvisit = list.get(0);
				
				session.evict(list);
				
				return list;
			}
		}
		);
	}
	
	@SuppressWarnings("unchecked")
	public List<PatientVisit> getPatientVisitsByHisId(final Person person,final String visitflowid)
	{
		return (List<PatientVisit>) getHibernateTemplate().execute(new HibernateCallback() 
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				
				Criteria criteria = session.createCriteria(PatientVisit.class)
										   .add(Restrictions.isNull("voidedDate"))
										   .add(Restrictions.eq("custom4", person.getCustom15()))
										   .add(Restrictions.eq("custom5", person.getCustom19()))
										   .add(Restrictions.eq("visitFlowId", visitflowid));
				
				List<PatientVisit> list = criteria.list();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				
				if (list.size() == 0) 
				{
					return null;
				}
				
				//PatientVisit patientvisit = list.get(0);
				System.out.println("流水记录变更" + list.size());
				
				session.evict(list);
				
				return list;
			}
		}
		);
	}
	
	public PatientVisit getVisitById(final Person person)
	{
		return (PatientVisit) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				
				Criteria criteria = session.createCriteria(PatientVisit.class)
										   .add(Restrictions.isNull("voidedDate"))
										   .add(Restrictions.eq("personId", person.getPersonId()));
				
				List<PatientVisit> list = criteria.list();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				
				if (list.size() == 0) 
				{
					return null;
				}
				
				PatientVisit patientvisit = list.get(0);
				
				session.evict(patientvisit);
				
				return patientvisit;
			}
		}
		);
	}
	@SuppressWarnings("unchecked")
	public List<Person> getPersons(final String StrSql)
	{
		return (List<Person>) getHibernateTemplate().execute(new HibernateCallback() 
		{
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				
				Query QueryObject = session.createSQLQuery(StrSql);
				
				List<Object> list = QueryObject.list();
				
				List<Person> personList = new ArrayList<Person>();
				
				log.debug("Query by identifier returned: " + list.size() + " elements.");
				
				if (list.size() == 0) 
				{
					return null;
				}
				
				for(int i=0;i<list.size();i++)
				{
					Object[] Oh =(Object[])list.get(i);
					
				    Person person=new Person();
				    
				    person.setPersonId(Long.valueOf(Oh[0].toString()));
				    
				    //System.out.println(person.getPersonId());
				    
				    personList.add(person);
				    
				    Oh=null;
				}
				
				list=null;
							
				return personList;
			}
		}
		);
	}
	
		@SuppressWarnings("unchecked")
		public List<Person> getPersonVisitById(final PersonIdentifierEMPI personIdentifier,final PatientVisit patientvisit) {
			return (List<Person>) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					
					String StrSql="Select * from person";
					
					if (personIdentifier.getIdentifier()!=null)
					{
						StrSql = StrSql + " Where custom16 like '%" + personIdentifier.getIdentifier() + "%'";
						
						if (personIdentifier.getIdentifierDomainDict() != null)
						{
							if (personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() != null) 
							{
								StrSql = StrSql + " And custom10='" + personIdentifier.getIdentifierDomainDict().getNamespaceIdentifier() +"'";
							}
							if (personIdentifier.getIdentifierDomainDict().getUniversalIdentifier() != null) 
							{
								StrSql = StrSql + " And custom11='" + personIdentifier.getIdentifierDomainDict().getUniversalIdentifier() + "'";
							}
							if (personIdentifier.getIdentifierDomainDict().getUniversalIdentifierTypeCode() != null) 
							{
								StrSql = StrSql + " And custom12='" + personIdentifier.getIdentifierDomainDict().getUniversalIdentifierTypeCode() + "'";
							}
						}
						
						if(patientvisit!=null)
						{
							StrSql = StrSql + " And person_id in ( select person_id from patient_visit where rownum<10000";
							
							//PV1-2.1:PAT_CATEGORY
							if(patientvisit.getPatCategory()!=null)
							{
								StrSql = StrSql + " And pat_category = '" + patientvisit.getPatCategory() + "'";
							}
							
							//PV1-3.1:PAT_CURRENT_POINT_OF_CARE
							if(patientvisit.getPatCurrentPointOfCare()!=null)
							{
								StrSql = StrSql + " And pat_current_point_of_care='" + patientvisit.getPatCurrentPointOfCare() + "'";
							}
							
							//PV1-3.2:PAT_CURRENT_ROOM
							if(patientvisit.getPatCurrentRoom()!=null)
							{
								StrSql = StrSql + " And pat_current_room='" + patientvisit.getPatCurrentRoom() + "'";
							}
							
							//PV1-3.3:PAT_CURRENT_BED
							if(patientvisit.getPatCurrentBed()!=null)
							{
								StrSql = StrSql + " And pat_current_bed='" + patientvisit.getPatCurrentBed() + "'";
							}
							
							
							//PV1-3.4:PAT_CURRENT_DEP
							if(patientvisit.getPatCuurentDep()!=null)
							{
								StrSql = StrSql + " And pat_cuurent_dep='" + patientvisit.getPatCuurentDep() + "'";
							}
							
							//PV1-3.5:PAT_CURRENT_POSITION_STATUS
							if(patientvisit.getPatCurrentPositionStatus()!=null)
							{
								StrSql = StrSql + " And pat_current_position_status='" + patientvisit.getPatCurrentPositionStatus() + "'";
							}
							
							//PV1-3.6:PAT_CURRENT_POSITION_TYPE
							if(patientvisit.getPatCurrentPositionType()!=null)
							{
								StrSql = StrSql + " And pat_current_position_type='" + patientvisit.getPatCurrentPositionType() + "'";
							}
							
							//pv1-3.7:PAT_CURRENT_BUILDING
							if(patientvisit.getPatCurrentBuilding()!=null)
							{
								StrSql = StrSql + " And pat_current_building='" + patientvisit.getPatCurrentBuilding() + "'";
							}
							
							//PV1-3.8:PAT_CURRENT_FLOOR
							if(patientvisit.getPatCurrentFloor()!=null)
							{
								StrSql = StrSql + " And pat_current_floor='" + patientvisit.getPatCurrentFloor() + "'";
							}
							
							//PV1-3.9:PAT_CURRENT_DESCRIPTION
							if(patientvisit.getPatCuurentDescription()!=null)
							{
								StrSql = StrSql + " And pat_current_description='" + patientvisit.getPatCuurentDescription() + "'";
							}
							
							//PV1-4.1:PAT_ADMISSION_TYPE
							if(patientvisit.getPatAdmissionType()!=null)
							{
								StrSql = StrSql + " And pat_admission_type='" + patientvisit.getPatAdmissionType() + "'";
							}
							
							//PV1-5.1:PAT_ADMISSION_NUMBER
							if(patientvisit.getPatAdmissionNumber()!=null)
							{
								StrSql = StrSql + " And pat_admission_number='" + patientvisit.getPatAdmissionNumber() + "'";
							}
							
							//PV1-6.1:PAT_FORMER_POINT_OF_CARE
							if(patientvisit.getPatFormerPointOfCare()!=null)
							{
								StrSql = StrSql + " And pat_former_point_of_care='" + patientvisit.getPatFormerPointOfCare() + "'";
							}
							
							//PV1-6.2:PAT_FORMER_ROOM
							if(patientvisit.getPatFormerRoom()!=null)
							{
								StrSql = StrSql + " And pat_former_room='" + patientvisit.getPatFormerRoom() + "'";
							}
							
							//PV1-6.3:PAT_FORMBER_BED
							if(patientvisit.getPatFormerBed()!=null)
							{
								StrSql = StrSql + " And pat_former_bed='" + patientvisit.getPatFormerBed() + "'";
							}
							
							//PV1-6.4:PAT_FORMER_DEP
							if(patientvisit.getPatFormerDep()!=null)
							{
								StrSql = StrSql + " And pat_former_dep='" + patientvisit.getPatFormerDep() + "'";
							}
							
							//PV1-6.5:PAT_FORMER_POSITION_STATUS
							if(patientvisit.getPatFormerPositionStatus()!=null)
							{
								StrSql = StrSql + " And pat_former_position_status='" + patientvisit.getPatFormerPositionStatus() + "'";
							}
							
							//PV1-6.6:PAT_FORER_POSITION_TYPE
							if(patientvisit.getPatFormerPositionType()!=null)
							{
								StrSql = StrSql + " And pat_former_position_type='" + patientvisit.getPatFormerPositionType() + "'";
							}
							
							//PV1-6.7:PAT_FORMER_BUILDING
							if(patientvisit.getPatFormerBuilding()!=null)
							{
								StrSql = StrSql + " And pat_former_building='" + patientvisit.getPatFormerBuilding() + "'";
							}
							
							//PV1-6.8:PAT_FORMER_FLOOR
							if(patientvisit.getPatFormerFloor()!=null)
							{
								StrSql = StrSql + " And pat_former_floor='" + patientvisit.getPatFormerFloor() + "'";
							}
							
							//PV1-6.9:PAT_FORMER_DESCRIPTION
							if(patientvisit.getPatFormerDescription()!=null)
							{
								StrSql = StrSql + " And pat_former_description='" + patientvisit.getPatFormerDescription() + "'";
							}
							
							//PV1-7.2:ADMISSIONS_DOCTOR
							if(patientvisit.getAdmissionsDoctor()!=null)
							{
								StrSql = StrSql + " And admissions_doctor='" + patientvisit.getAdmissionsDoctor() + "'";
							}
							
							//PV1-7.1:ADMISSIONS_DOCTOR_ID
							if(patientvisit.getAdmissionsDoctorId()!=null)
							{
								StrSql = StrSql + " And admissions_doctor_id='" + patientvisit.getAdmissionsDoctorId() + "'";
							}
							
							//PV1-8.1:REFERRING_DOCTOR_ID
							if(patientvisit.getReferringDoctorId()!=null)
							{
								StrSql = StrSql + " And referring_doctor_id='" + patientvisit.getReferringDoctorId() + "'";
							}
							
							//PV1-8.2:REFERRING_DOCTOR
							if(patientvisit.getReferringDoctor()!=null)
							{
								StrSql = StrSql + " And referring_doctor='" + patientvisit.getReferringDoctor() + "'";
							}
							
							//PV1-9.1:CONSULTATION_DOCTOR_ID
							if(patientvisit.getConsultationDoctorId()!=null)
							{
								StrSql = StrSql + " And consultation_doctor_id='" + patientvisit.getConsultationDoctorId() + "'";
							}
							
							//PV1-9.2:CONSULTATION_DOCTOR
							if(patientvisit.getConsultationDoctor()!=null)
							{
								StrSql = StrSql + " And consultation_doctor='" + patientvisit.getConsultationDoctor() + "'";
							}
							
							//PV1-10.1:HOSPITAL_SERVICE
							if(patientvisit.getHospitalService()!=null)
							{
								StrSql = StrSql + " And hospital_service='" + patientvisit.getHospitalService() + "'";
							}
							
							//PV1-11.1:PAT_TEMP_POINT_OF_CARE
							if(patientvisit.getPatTempPointOfCare()!=null)
							{
								StrSql = StrSql + " And pat_temp_point_of_care='" + patientvisit.getPatTempPointOfCare() + "'";
							}
							
							//PV1-11.2:PAT_TEMP_ROOM
							if(patientvisit.getPatTempRoom()!=null)
							{
								StrSql = StrSql + " And pat_temp_room='" + patientvisit.getPatTempRoom() + "'";
							}
							
							//PV1-11.3:PAT_TEMP_BED
							if(patientvisit.getPatTempBed()!=null)
							{
								StrSql = StrSql + " And pat_temp_bed='" + patientvisit.getPatTempBed() + "'";
							}
							
							//PV1-11.4:PAT_TEMP_DEP
							if(patientvisit.getPatTempDep()!=null)
							{
								StrSql = StrSql + " And pat_temp_dep='" + patientvisit.getPatTempDep() + "'";
							}
							
							//PV1-11.5:PAT_TEMP_POSITION_STATUS
							if(patientvisit.getPatTempPositionStatus()!=null)
							{
								StrSql = StrSql + " And pat_temp_position_status='" + patientvisit.getPatTempPositionStatus() + "'";
							}
							
							//PV1-11.6:PAT_TEMP_POSITION_TYPE
							if(patientvisit.getPatTempPositionType()!=null)
							{
								StrSql = StrSql + " And pat_temp_position_type='" + patientvisit.getPatTempPositionType() + "'";
							}
							
							//PV1-11.7:PAT_TEMP_BUILDING
							if(patientvisit.getPatTempBuilding()!=null)
							{
								StrSql = StrSql + " And pat_temp_building=" + patientvisit.getPatTempBuilding() + "'";
							}
							
							//PV1-11.8:PAT_TEMP_FLOOR
							if(patientvisit.getPatTempFloor()!=null)
							{
								StrSql = StrSql + " And pat_temp_floor='" + patientvisit.getPatTempFloor() + "'";
							}
							
							//PV1-11.9:PAT_TEMP_DESCRIPTION
							if(patientvisit.getPatTempDescription()!=null)
							{
								StrSql = StrSql + " And pat_temp_description='" + patientvisit.getPatTempDescription() + "'";
							}
							
							//PV1-12.1:PAT_ADMISSION_TEST
							if(patientvisit.getPatAdmissionTest()!=null)
							{
								StrSql = StrSql + " And pat_admission_test='" + patientvisit.getPatAdmissionTest() + "'";
							}
							
							//pv1-13.1:PAT_RE_ADMISSION
							if(patientvisit.getPatReAdmission()!=null)
							{
								StrSql = StrSql + " And pat_re_admission='" + patientvisit.getPatReAdmission() + "'";
							}
							
							//PV1-14.1:PAT_ADMISSION_SOURCE
							if(patientvisit.getPatAdmissionSource()!=null)
							{
								StrSql = StrSql + " And pat_admission_source='" + patientvisit.getPatAdmissionSource() + "'";
							}
							
							//PV1-15.1:PAT_AMBULATORY_STATUS
							if(patientvisit.getDischargeName()!=null)
							{
								StrSql = StrSql + " And DISCHARGE_NAME='" + patientvisit.getDischargeName() + "'";
							}
							
							if(patientvisit.getDischargeDomain()!=null)
							{
								StrSql = StrSql + " AND DISCHARGE_DOMAIN='" + patientvisit.getDischargeDomain() + "'";
							}
							
							if(patientvisit.getAdmissionName()!=null)
							{
								StrSql = StrSql + " AND ADMISSION_NAME='" + patientvisit.getAdmissionName() + "'";
							}
							
							if(patientvisit.getAdmissionDomain()!=null)
							{
								StrSql = StrSql + " AND ADMISSION_DOMAIN='" + patientvisit.getAdmissionDomain() + "'";
							}
							
							if(patientvisit.getIpStatusName()!=null)
							{
								StrSql = StrSql + " AND IP_STATUS_NAME='" + patientvisit.getIpStatusName() + "'";
							}
							
							if(patientvisit.getIpStatusDomain()!=null)
							{
								StrSql = StrSql + " AND IP_STATUS_DOMAIN='" + patientvisit.getIpStatusDomain() +"'";
							}
							
							if(patientvisit.getDificultyName()!=null)
							{
								StrSql = StrSql + " AND DIFICULTY_NAME='" + patientvisit.getDificultyName() + "'";
							}
							
							if(patientvisit.getDificultyDomain()!=null)
							{
								StrSql = StrSql + " AND DIFICULTY_DOMAIN='" + patientvisit.getDificultyDomain() + "'";
							}
							
							if(patientvisit.getAdmissionSourceName()!=null)
							{
								StrSql = StrSql + " AND ADMISSION_SOURCE_NAME='" + patientvisit.getAdmissionSourceName() + "'";
							}
							
							if(patientvisit.getAdmissionSourceDomain()!=null)			
							{
								StrSql = StrSql + " AND ADMISSION_SOURCE_DOMAIN='" + patientvisit.getAdmissionSourceDomain() + "'";
							}
							
							if(patientvisit.getPatCategoryName()!=null)
							{
								StrSql = StrSql + " AND PAT_CATEGORY_NAME='" + patientvisit.getPatCategoryName();
							}
							
							if(patientvisit.getPatCategorySystem()!=null)
							{
								StrSql = StrSql + " AND PAT_CATEGORY_SYSTEM='" + patientvisit.getPatCategoryName();
							}
							
							//PV1-16.1:PAT_VIP
							if(patientvisit.getPatVip()!=null)
							{
								StrSql = StrSql + " And pat_vip='" + patientvisit.getPatVip() + "'";
							}
							
							//PV1-17.2:PAT_ADMISSION_DOCTORS
							if(patientvisit.getPatAdmissionDoctors()!=null)
							{
								StrSql = StrSql + " And pat_admission_doctors='" + patientvisit.getPatAdmissionDoctors() + "'";
							}
							
							//PV1-17.1:PAT_ADMISSION_DOCTORS_ID
							if(patientvisit.getPatAdmissionDoctorsId()!=null)
							{
								StrSql = StrSql + " And pat_admission_doctors_id='" + patientvisit.getPatAdmissionDoctorsId() + "'";
							}
							
							//PV1-18.1:patient_CLASS
							if(patientvisit.getPatientClass()!=null)
							{
								StrSql = StrSql + " And patient_class='" + patientvisit.getPatientClass() + "'";
							}
							
							//PV1-19.1:Patient_ID
							if(patientvisit.getPatientId()!=null)
							{
								StrSql = StrSql + " And patient_id='" + patientvisit.getPatientId() + "'";
							}
							
							//PV1-20.1:PAT_FINANCIAL_CLASS
							if(patientvisit.getPatFinancialClass()!=null)
							{
								StrSql = StrSql + " And pat_financial_class='" + patientvisit.getPatFinancialClass() + "'";
							}
							
							//PV1-21.1:ROOM_BED_COST_PRICE
							if(patientvisit.getRoomBedCostPrice()!=null)
							{
								StrSql = StrSql + " And room_bed_cost_price='" + patientvisit.getRoomBedCostPrice() + "'";
							}
							
							//PV1-22.1:COURTESY_CODE
							if(patientvisit.getCourtesyCode()!=null)
							{
								StrSql = StrSql + " And courtesy_code='" + patientvisit.getCourtesyCode() + "'";
							}
							
							//PV1-23.1:CREDIT_RATING
							if(patientvisit.getCreditRating()!=null)
							{
								StrSql = StrSql + " And credit_rating='" + patientvisit.getCreditRating() + "'";
							}
							
							//PV1-24.1:CONTRACT_CODE
							if(patientvisit.getContractCode()!=null)
							{
								StrSql = StrSql + " And contract_code='" + patientvisit.getContractCode() + "'";
							}
							
							//PV1-25.1:CONTRACT_CREATE_DATE
							if(patientvisit.getContractCreateDate()!=null)
							{
								StrSql = StrSql + " And contract_create_date='" + patientvisit.getContractCreateDate() + "'";
							}
							
							//PV1-26.1:CONTRACT_PRICE
							if(patientvisit.getContractPrice()!=null)
							{
								StrSql = StrSql + " And contract_price='" + patientvisit.getContractPrice() + "'";
							}
							
							//PV1-27.1:CONTRACT_TIME
							if(patientvisit.getContractTime()!=null)
							{
								StrSql = StrSql + " And contract_time='" + patientvisit.getContractTime() + "'";
							}
							
							//PV1-28.1:INTEREST_RATE_CODE
							if(patientvisit.getInterestRateCode()!=null)
							{
								StrSql = StrSql + " And interest_rate_code='" + patientvisit.getInterestRateCode() + "'";
							}
							
							//PV1-29.1:BAD_DEBTS
							if(patientvisit.getBadDebts()!=null)
							{
								StrSql = StrSql + " And bad_debts='" + patientvisit.getBadDebts() + "'";
							}
							
							//PV1-30.1:BAD_DEBTS_CREATE_DATE
							if(patientvisit.getBadDebtsCreateDate()!=null)
							{
								StrSql = StrSql + " And bad_debts_create_date='" + patientvisit.getBadDebtsCreateDate() + "'";
							}
							
							//PV1-31.1:BAD_DEBTS_CODE
							if(patientvisit.getBadDebtsCode()!=null)
							{
								StrSql = StrSql + " And bad_debts_code='" + patientvisit.getBadDebtsCode() + "'";
							}
							
							//PV1-32.1:BAD_DEBTS_PRICE
							if(patientvisit.getBadDebtsPrice()!=null)
							{
								StrSql = StrSql + " And bad_debts_price='" + patientvisit.getBadDebtsPrice() + "'";
							}
							
							//PV1-33.1:BAD_DEBTS_RESTORE_PRICE
							if(patientvisit.getBadDebtsRestorePrice()!=null)
							{
								StrSql = StrSql + " And bad_debts__restorre_price='" + patientvisit.getBadDebtsRestorePrice() + "'";
							}
							
							//PV1-34.1:PAT_ACCOUNT_VOIDED
							if(patientvisit.getPatAccountVoided()!=null)
							{
								StrSql = StrSql + " And pat_account_voided='" + patientvisit.getPatAccountVoided() + "'";
							}
							
							//PV1-35.1:PAT_ACCOUNT_VOIDED_DATE
							if(patientvisit.getPatAccountVoidedDate()!=null)
							{
								StrSql = StrSql + " And pat_account_voided_date='" + patientvisit.getPatAccountVoidedDate() + "'";
							}
							
							//PV1-36.1: PAT_DISCHARGE_DISPOSITION
							if(patientvisit.getPatDischargeDisposition()!=null)
							{
								StrSql = StrSql + " And pat_discharge_disposition='" + patientvisit.getPatDischargeDisposition() + "'";
							}
							
							//PV1-37.1:PAT_DISCHARGE_LOCATION
							if(patientvisit.getPatDischargeLocation()!=null)
							{
								StrSql = StrSql + " And pat_discharge_location='" + patientvisit.getPatDischargeLocation() + "'";
							}
							
							//PV1-38.1:PAT_DIET_TYPE
							if(patientvisit.getPatDietType()!=null)
							{
								StrSql = StrSql + " And pat_diet_type='" + patientvisit.getPatDietType() + "'";
							}
							
							//PV1-39.1:PAT_SERVICE_AGENCIES
							if(patientvisit.getPatServiceAgencies()!=null)
							{
								StrSql = StrSql + " And pat_service_agencies='" + patientvisit.getPatServiceAgencies() + "'";
							}
							
							//PV1-40.1:PAT_BED_STATUS
							if(patientvisit.getPatBedStatus()!=null)
							{
								StrSql = StrSql + " And pat_bed_status='" + patientvisit.getPatBedStatus() + "'";
							}
							
							//PV1-41.1:PAT_ACCOUNT_STATUS
							if(patientvisit.getPatAccountStatus()!=null)
							{
								StrSql = StrSql + " And pat_account_status='" + patientvisit.getPatAccountStatus() + "'";
							}
							
							//PV1-42.1:PAT_DETER_POINT_OF_CARE
							if(patientvisit.getPatDeterPointOfCare()!=null)
							{
								StrSql = StrSql + " And pat_deter_point_of_care='" + patientvisit.getPatDeterPointOfCare() + "'";
							}
							
							//PV1-42.2:PAT_DETER_ROOM
							if(patientvisit.getPatDeterRoom()!=null)
							{
								StrSql = StrSql + " And pat_deter_room='" + patientvisit.getPatDeterRoom() + "'";
							}
							
							//PV1-42.3:PAT_DETER_BED
							if(patientvisit.getPatDeterBed()!=null)
							{
								StrSql = StrSql + " And pat_deter_bed='" + patientvisit.getPatDeterBed() + "'";
							}
							
							//PV1-42.4:PAT_DETER_DEP
							if(patientvisit.getPatDeterDep()!=null)
							{
								StrSql = StrSql + " And pat_deter_dep='" + patientvisit.getPatDeterDep() + "'";
							}
							
							//PV1-42.5:PAT_DETER_POSITION_STATUS
							if(patientvisit.getTend()!=null)
							{
								StrSql = StrSql + " And tend='" + patientvisit.getTend() + "'";
							}
							
							//PV1-42.6:PAT_DETER_POSITION_TYPE
							if(patientvisit.getPatNurseCode()!=null)
							{
								StrSql = StrSql + " And pat_nurse_code='" + patientvisit.getPatNurseCode() + "'";
							}
							
							//PV1-42.7:PAT_DETER_BUILDING
							if(patientvisit.getPatNurseName()!=null)
							{
								StrSql = StrSql + " And pat_nurse_name='" + patientvisit.getPatNurseName()+ "'";
							}
							
							//PV1-42.8:PAT_DETER_FLOOR
							if(patientvisit.getOperCode()!=null)
							{
								StrSql = StrSql + " And oper_code='" + patientvisit.getOperCode() + "'";
							}
							
							//PV1-42.9:PAT_DETER_DESCRIPTION
							if(patientvisit.getOperDate()!=null)
							{
								StrSql = StrSql + " And oper_date='" + patientvisit.getOperDate() + "'";
							}
							
							//PV1-43.1:PAT_FOR_TEMP_POINT_OF_CARE
							if(patientvisit.getPatForTempPointOfCare()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_point_of_care='" + patientvisit.getPatForTempPointOfCare() + "'";
							}
							
							//PV1-43.2:PAT_FOR_TEMP_ROOM
							if(patientvisit.getPatForTempRoom()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_room='" + patientvisit.getPatForTempRoom() + "'";
							}
							
							//PV1-43.3:pat_for_temp_bed
							if(patientvisit.getPatForTempBed()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_bed='" + patientvisit.getPatForTempBed() + "'";
							}
							
							//PV1-43.4:PAT_FOR_TEMP_DEP
							if(patientvisit.getPatForTempDep()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_dep='" + patientvisit.getPatForTempDep() + "'";
							}
							
							//PV1-43.5:PAT_FOR_TEMP_POSITION_STATUS
							if(patientvisit.getPatForTempPositionStatus()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_position_status='" + patientvisit.getPatForTempPositionStatus() + "'";
							}
							
							//PV1-43.6:PAT_FOR_TEMP_POSITION_TYPE
							if(patientvisit.getPatForTempPositionType()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_position_type='" + patientvisit.getPatForTempPositionType() + "'";
							}
							
							//PV1-43.7:PAT_FOR_TEMP_BUILDING
							if(patientvisit.getPatForTempBuilding()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_building='" + patientvisit.getPatForTempBuilding() + "'";
							}
							
							//PV1-43.8:PAT_FOR_TEMP_FLOOR
							if(patientvisit.getPatForTempFloor()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_floor='" + patientvisit.getPatForTempFloor() + "'";
							}
							
							//PV1-43.9:PAT_FOR_TEMP_DESCRIPTION
							if(patientvisit.getPatTempDescription()!=null)
							{
								StrSql = StrSql + " And pat_for_temp_description='" + patientvisit.getPatFormerDescription() + "'";
							}
							
							//PV1-44.1:ADMIT_DATE
							if(patientvisit.getAdmitDate()!=null)
							{
								StrSql = StrSql + " And admit_date='" + patientvisit.getAdmitDate() + "'";
							}
							
							//PV1-45.1:DISCHARGE_DATE
							if(patientvisit.getDischargeDate()!=null)
							{
								StrSql = StrSql + " And discharge_date='" + patientvisit.getDischargeDate() + "'";
							}
							
							//PV1-46.1:PAT_DIFFERENCE
							if(patientvisit.getPatDifference()!=null)
							{
								StrSql = StrSql + " And pat_difference='" + patientvisit.getPatDifference() + "'";
							}
							
							//PV1-47.1:PAT_TOTAL_COST
							if(patientvisit.getPatTotalCost()!=null)
							{
								StrSql = StrSql + " And pat_total_cost='" + patientvisit.getPatTotalCost() + "'";
							}
							
							//PV1-48.1:PAT_TOTAL_DISPATCH
							if(patientvisit.getPatTotalDispatch()!=null)
							{
								StrSql = StrSql + " And pat_total_dispatch='" + patientvisit.getPatTotalDispatch() + "'";
							}
							
							//PV1-49.1:PAT_TOTAL_AMOUNT_PAYABLE
							if(patientvisit.getPatTotalAmountPayable()!=null)
							{
								StrSql = StrSql + " And pat_total_amount_payable='" + patientvisit.getPatTotalAmountPayable() + "'";
							}
							
							//PV1-50.1:PAT_SPARE_ID
							if(patientvisit.getBabyFlag()!=null)
							{
								StrSql = StrSql + " And baby_flag='" + patientvisit.getBabyFlag() + "'";
							}
							
							if(patientvisit.getAdmitWeight()!=null)
							{
								StrSql = StrSql + " AND ADMIT_WEIGHT='" + patientvisit.getAdmitWeight() + "'";
							}
							
							if(patientvisit.getAdmitWeightUnit()!=null)
							{
								StrSql = StrSql + " AND ADMIT_WEIGHT_UNIT='" + patientvisit.getAdmitWeightUnit() + "'";
							}
							
							if(patientvisit.getBirthWeight()!=null)
							{
								StrSql = StrSql + " AND BIRTH_WEIGHT='" + patientvisit.getBirthWeight() + "'";
							}
							
							if(patientvisit.getBabyWeightUnit()!=null)
							{
								StrSql = StrSql + " AND BABY_WEIGHT_UNIT='" + patientvisit.getBabyWeightUnit() + "'";
							}
							//PV1-51.1:PAT_VISIT_LOGO
							if(patientvisit.getPatVisitLogo()!=null)
							{
								StrSql = StrSql + " And pat_visit_logo='" + patientvisit.getPatVisitLogo() + "'";
							}
							
							//PV1-52.1:OTHER_MEDICAL_INSTITUTIONS
							if(patientvisit.getOtherMedicalInstitutions()!=null)
							{
								StrSql = StrSql + " And other_medical_institutions='" + patientvisit.getOtherMedicalInstitutions() + "'";
							}
							StrSql = StrSql + ")";
						}
						else
						{
							StrSql = StrSql + ")";						
						}
					
					}
					Query QueryObject = session.createSQLQuery(StrSql);
					
					List<Object> list = QueryObject.list();
					
					List<Person> personList = new ArrayList<Person>();
					
					log.debug("Query by identifier returned: " + list.size() + " elements.");
					
					if (list.size() == 0) 
					{
						return null;
					}
					
					for(int i=0;i<list.size();i++)
					{
						Object[] Oh =(Object[])list.get(i);
						
					    Person person=new Person();
					    
					    person.setPersonId(Long.valueOf(Oh[0].toString()));
					    
					    personList.add(person);
					    
					    Oh=null;
					}
					
					list=null;
					
					return personList;
				}
			});		
		}

		
		public void SavePatientVisitHistory(PatientVisitHistory patientvisithistory) {
			log.debug("saving Extendforperson instance");
			try {
				log.debug("Saving PatientVisit record: " + patientvisithistory);
				getHibernateTemplate().saveOrUpdate(patientvisithistory);
				getHibernateTemplate().flush();
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
		}
		
}
