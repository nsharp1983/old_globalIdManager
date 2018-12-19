package com.ats.aexchange.actorconfig;


/**
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 *
 */
public interface IheActor {
	
	/**
	 *  Starts this actor. It must be called once for each actor when the program starts. 
	 */  
	public void start();

	/**
	 * Stops this actor. It must be called once for each actor just before the program quits. 
	 */ 
	public void stop();
  
	/**
	 * Returns a useful name for this Actor so that it can be put into
	 * debugging and logging messages.
	 * 
	 * @returns a useful name for this Actor
	 */
	public String getName(); 
	
	/**
	 * Gets the <code>IActorDescription</code> of this actor.
	 * 
	 * @return the actor description
	 */
	public IActorDescription getActorDescription();
	
	/**
	 * Gets the Audit Trail of this actor.
	 * 
	 * @return the auditTrail
	 */
	public IAuditTrailLifeCycle getAuditTrail();

}
