package com.ats.aempi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ats.aempi.ValidationException;
import com.ats.aempi.context.Context;
import com.ats.aempi.dao.AuditEventDao;
import com.ats.aempi.model.AuditEvent;
import com.ats.aempi.model.AuditEventTypeDict;
import com.ats.aempi.model.Person;
import com.ats.aempi.service.AuditEventService;
import com.ats.aempi.service.ValidationService;

public class AuditEventServiceImpl extends BaseServiceImpl implements AuditEventService
{
	private AuditEventDao auditEventDao;
	
	private Map<String, AuditEventTypeDict> eventTypeMap;
	
	public AuditEventServiceImpl() {
	}
	
	@SuppressWarnings("unchecked")
	public void init() {
		log.info("Initializing the Audit Event Service.");
		eventTypeMap = new HashMap<String, AuditEventTypeDict>();
		List<AuditEventTypeDict> types = (List<AuditEventTypeDict>) auditEventDao.getAll(AuditEventTypeDict.class);
		for (AuditEventTypeDict auditEventType : types) {
			eventTypeMap.put(auditEventType.getAuditEventTypeCode(), auditEventType);
		}
		log.debug("Built the map of audit event types with count of " + types.size());
	}

	@SuppressWarnings("unchecked")
	public List<AuditEvent> getAllAuditEvents() {
		return (List<AuditEvent>) auditEventDao.getAll(AuditEvent.class);
	}

	public AuditEventTypeDict getAuditEventTypeByCode(String eventTypeCode) {
		return eventTypeMap.get(eventTypeCode);
	}

	public AuditEvent saveAuditEvent(String auditEventTypeCode, String auditEventDescription) {
		return saveAuditEvent(auditEventTypeCode, auditEventDescription, null, null,null);
	}

	public AuditEvent saveAuditEvent(String auditEventTypeCode, String auditEventDescription, Person refPerson,List<String> PatientInfo) {
		return saveAuditEvent(auditEventTypeCode, auditEventDescription, refPerson, null,PatientInfo);
	}

	public AuditEvent saveAuditEvent(String auditEventTypeCode, String auditEventDescription, Person refPerson,Person altRefPerson,List<String> PatientInfo) 
	{
		
		AuditEventTypeDict auditEventType = getAuditEventTypeByCode(auditEventTypeCode);
		
		if (auditEventType == null) 
		{
			log.error("Attempted to audit an event with unknown audit event type: " + auditEventTypeCode);
			
			throw new ValidationException("Invalid audit event type code " + auditEventTypeCode);
		}
		
		AuditEvent auditEvent = new AuditEvent(new java.util.Date(), auditEventType, auditEventDescription, Context.getUserContext().getUser());
		
		auditEvent.setPersonByRefPersonId(refPerson);
		
		auditEvent.setPersonByAltRefPersonId(altRefPerson);
		
		if(refPerson.getCustom16()!=null)
		{
			auditEvent.setPatientId(refPerson.getCustom16());
		}
		
		if(refPerson.getCustom11()!=null)
		{
			auditEvent.setPatientDomain(refPerson.getCustom11());
		}
		
		if(String.valueOf(refPerson.getPersonId())!=null)
		{
			auditEvent.setPersonId(String.valueOf(refPerson.getPersonId()));
		}
		
		if(refPerson.getEmpi()!=null)
		{
			auditEvent.setEmpi(refPerson.getEmpi());
		}
		
		if(PatientInfo!=null)
		{
			if(PatientInfo.get(0).toString()!=null)
			{
				auditEvent.setBeforeTheChangePerson(PatientInfo.get(0).toString());
			}
		
			if(PatientInfo.get(1).toString()!=null)
			{
				auditEvent.setAfterTheChangePerson(PatientInfo.get(1).toString());
			}
		
			if(PatientInfo.get(2).toString()!=null)
			{
				auditEvent.setBeforeTheChangeVisit(PatientInfo.get(2).toString());
			}
		
			if(PatientInfo.get(3).toString()!=null)
			{
				auditEvent.setAfterTheChangeVisit(PatientInfo.get(3).toString());
			}
		}
		log.debug("About to audit the event " + auditEvent);
		
		auditEvent = (AuditEvent) auditEventDao.save(auditEvent);
		
		return auditEvent;
	}

	public AuditEvent saveAuditEvent(AuditEvent auditEvent) {
		
		ValidationService validationService = Context.getValidationService();
		validationService.validate(auditEvent);
		
		return (AuditEvent) auditEventDao.save(auditEvent);
	}

	public AuditEventDao getAuditEventDao() {
		return auditEventDao;
	}

	public void setAuditEventDao(AuditEventDao auditEventDao) {
		this.auditEventDao = auditEventDao;
	}


}
