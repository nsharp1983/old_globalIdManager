package com.ats.aexchange.audit;

import com.ats.aexchange.actorconfig.IAuditTrailLifeCycle;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.audit.AuditCodeMappings.SuccessCode;


/**
 *  The base interface of an audit message class.
 *
 * @author Michael Traum
 */
public interface IAuditTrail extends IAuditTrailLifeCycle {
	
	/** Call when a user authenticates himself. 
	 * 
	 * Described in DICOM Supp95 A 1.3.15 as User Authentication.
	 * Described in ITI TF-2 p. 172 as Node-authentication-failure.
	 */
	public void userLogin(SuccessCode success, ActiveParticipant user);
	

	/** Call when a user logs out. 
	 * 
	 * Described in DICOM Supp95 A 1.3.15 as User Authentication.
	 * Described in ITI TF-2 p. 172 as Node-authentication-failure.
	 */
	public void userLogout(SuccessCode success, ActiveParticipant user);

	/** Call when the node fails to authenticate itself with another node. 
	 * 
	 * Generally you don't log successes since there can be many of those.
	 * 
	 * Described in DICOM Supp95 A 1.3.14 as Security Alert.
	 * Described in ITI TF-2 p. 172 as Node-authentication-failure.
	 */
	public void nodeAuthenticationFailure(SuccessCode success, IConnectionDescription otherServer);
}
