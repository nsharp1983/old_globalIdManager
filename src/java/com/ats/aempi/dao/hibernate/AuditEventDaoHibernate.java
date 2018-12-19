package com.ats.aempi.dao.hibernate;

import java.util.List;

import com.ats.aempi.dao.AuditEventDao;
import com.ats.aempi.model.AuditEvent;
import com.ats.aempi.model.AuditEventTypeDict;

public class AuditEventDaoHibernate extends UniversalDaoHibernate implements AuditEventDao
{
	public List<AuditEvent> getAuditEventByType(AuditEventTypeDict auditEventType) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public AuditEventTypeDict getAuditEventTypeByCode(String auditEventTypeCode) {
		if (auditEventTypeCode == null || auditEventTypeCode.length() == 0) {
			return null;
		}
		String query = "from AuditEventTypeDict aet where aet.auditEventTypeCode = '" + auditEventTypeCode + "'";
        List<AuditEventTypeDict> values = getHibernateTemplate().find(query);
        log.trace("Search for audit event types by type: " + auditEventTypeCode + " found " + values.size() + " entries.");
        return values.get(0);
	}
}
