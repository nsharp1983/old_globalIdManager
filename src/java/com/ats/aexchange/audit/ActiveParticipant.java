package com.ats.aexchange.audit;

import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.audit.AuditCodeMappings.ActiveParticipantIds;
import com.ats.aexchange.audit.AuditCodeMappings.AuditSourceType;
import com.ats.aexchange.audit.AuditCodeMappings.NetworkAccessPointType;


/** Temporary way of getting participant info to the Audit Trail.
 * 
 * This should be replaced by the LogContext, or we could just
 * make a constructor that takes the log context...?
 * @author Josh Flachsbart
 * @version 2.0 - Nov 17, 2005
 */
public class ActiveParticipant {
    public ActiveParticipantIds role;
    protected String userId; // Unique identifies see A 1.2.1
    protected String altUserId; // Alternate unique ID see A 1.2.2
    protected String userName; // Human meaningful name see A 1.2.3
    protected boolean isRequestor; // is this user the initator of the event
    protected String accessPointId; // See A 1.2.4
    protected NetworkAccessPointType accessPointTypeCode;   
    protected AuditSourceType auditSourceType; // See A 1.2.4

    public ActiveParticipant() {

    }

    public ActiveParticipant(IConnectionDescription serverDescription) {
        role = ActiveParticipantIds.Destination;
        userId = serverDescription.getHostname();
        if (serverDescription.getPort() > 0) userId += ":" + serverDescription.getPort();
        if (serverDescription.getUrlPath() != null) userId += serverDescription.getUrlPath();
        // could look up ip as alt id.
        userName = serverDescription.getName();
        isRequestor = false;
        accessPointId = serverDescription.getHostname();
        accessPointTypeCode = NetworkAccessPointType.IPAddress;
        auditSourceType = AuditSourceType.DatabaseServer;
    }

    public ActiveParticipant(
            String userId, String altUserId,
            String accessPointId) {
        this.userId = userId;
        this.altUserId = altUserId;
        this.accessPointId = accessPointId;
    }

	public String getAccessPointId() {
		return accessPointId;
	}

	public void setAccessPointId(String accessPointId) {
		this.accessPointId = accessPointId;
	}

	public String getAltUserId() {
		return altUserId;
	}

	public void setAltUserId(String altUserId) {
		this.altUserId = altUserId;
	}

	public AuditSourceType getAuditSourceType() {
		return auditSourceType;
	}

	public void setAuditSourceType(AuditSourceType auditSourceType) {
		this.auditSourceType = auditSourceType;
	}

	public boolean isRequestor() {
		return isRequestor;
	}

	public void setRequestor(boolean isRequestor) {
		this.isRequestor = isRequestor;
	}

	public ActiveParticipantIds getRole() {
		return role;
	}

	public void setRole(ActiveParticipantIds role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public NetworkAccessPointType getAccessPointTypeCode() {
		return accessPointTypeCode;
	}

	public void setAccessPointTypeCode(NetworkAccessPointType accessPointTypeCode) {
		this.accessPointTypeCode = accessPointTypeCode;
	}



}
