package com.ats.aexchange.audit;

import com.ats.aexchange.audit.AuditCodeMappings.AuditEventIds;
import com.ats.aexchange.audit.AuditCodeMappings.AuditTypeCodes;
import com.ats.aexchange.audit.AuditCodeMappings.EventActionCode;
import com.ats.aexchange.audit.AuditCodeMappings.SecurityAlertType;
import com.ats.aexchange.audit.AuditCodeMappings.SuccessCode;


/** 
 * Used internally to define the different possible events.
 * 
 * See the audit code mappings file to know what all the different codes
 * mean.
 *   
 * @author Josh Flachsbart
 * @version 1.0 - Nov 17, 2005
 */
public class EventId {
	public AuditEventIds eventId;
	public AuditTypeCodes typeCode;
	public EventActionCode actionCode; // Normally 'C' create 'R' read 'U' update 'D' delete 'E'
	public SuccessCode outcome;
	
	public EventId(AuditEventIds eventId, AuditTypeCodes typeCode, EventActionCode actionCode, SuccessCode outcome) {
		this.eventId = eventId;
		this.typeCode = typeCode;
		this.actionCode = actionCode;
		this.outcome = outcome;
	}

	public EventId(AuditEventIds eventId, SecurityAlertType typeCode, EventActionCode actionCode, SuccessCode outcome) {
		this.eventId = eventId;
		this.typeCode = typeCode.getValue();
		this.actionCode = actionCode;
		this.outcome = outcome;
	}
}
