package com.ats.aempi.service;

import java.util.List;

import com.ats.aempi.model.AuditEvent;
import com.ats.aempi.model.AuditEventTypeDict;
import com.ats.aempi.model.Person;

public interface AuditEventService
{
	public AuditEventTypeDict getAuditEventTypeByCode(String eventTypeCode);
	
	public AuditEvent saveAuditEvent(AuditEvent auditEvent);
	
	public AuditEvent saveAuditEvent(String auditEventType, String auditEventDescription);

	public AuditEvent saveAuditEvent(String auditEventType, String auditEventDescription, Person refPerson,List<String> PatientInfo);
	
	public AuditEvent saveAuditEvent(String auditEventType, String auditEventDescription, Person refPerson, Person altRefPerson,List<String> PatientInfo);
	
	public List<AuditEvent> getAllAuditEvents();
}
