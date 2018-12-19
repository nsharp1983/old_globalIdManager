package com.ats.aempi.dao;

import java.util.List;

import com.ats.aempi.model.AuditEvent;
import com.ats.aempi.model.AuditEventTypeDict;;

public interface AuditEventDao extends UniversalDao
{
	public AuditEventTypeDict getAuditEventTypeByCode(String auditEventTypeCode);
	
	public List<AuditEvent> getAuditEventByType(AuditEventTypeDict auditEventType);
}
