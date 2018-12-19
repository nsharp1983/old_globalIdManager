package com.ats.aexchange.actorconfig;


/**
 * This class presents a virtual interface to a collection of
 * audit trail sources.  Some of these sources will be external 
 * IHE sources.
 * <p>
 * This class presents a single global <code>AuditBroker</code>
 * instance to the Misys Connect 2.0 code.  That way it need
 * not be passed around in global web state.  It can simply be
 * initialized and then requested from any code whenever necessary.
 * 
 * @author Josh Flachsbart
 */
public class AuditBroker {
	
	/* A single instance for this class */
	private static AuditBroker singleton = null;
	
	/* All the audit trail connections. */
	private IAuditTrailLifeCycle auditTrail;
	
	/**
	 * A private constructor for creating the singleton instance.
	 */
	private AuditBroker() {
		super();
	}
	
	/**
	 * Get the single global instance of the <code>AuditBroker</code>.
	 * 
	 * @return The patient broker
	 */
	public static synchronized AuditBroker getInstance() {
		if (singleton == null) singleton = new AuditBroker();
		return singleton;
	}
	
	/**
	 * Set the AuditTrail for this broker.  May only be set once.
	 * 
	 * @param source A complete audit trail.
	 * @return True if this source was successfully added
	 */
	public synchronized boolean registerAuditSource(IAuditTrailLifeCycle source) {
		boolean added = false;
		if ((auditTrail == null) && (source != null)) {
			source.start();
			auditTrail = source;
			added = true;
		}
		return added;
	}
	
	/**
	 * Unregister the audit sources the controller suggests.  If the controller
	 * is null unregister all sources.
	 * 
	 * @param controller The controller to ask about the audit sources.
	 * @return
	 */
	public synchronized boolean unregisterAuditSources(IBrokerController controller) {
		boolean removed = false;
		if (auditTrail != null) {
			if ((controller == null) || controller.shouldUnregister(auditTrail)) {
				auditTrail.stop();
				auditTrail = null;
				removed = true;
			}
		}
		return removed;
	}
	
}
