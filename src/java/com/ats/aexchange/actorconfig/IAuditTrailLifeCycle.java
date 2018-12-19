package com.ats.aexchange.actorconfig;

public interface IAuditTrailLifeCycle {

	/**	Sends actor start log message.  Must be called when actor is started. */
	public void start();
	
	/**	Sends actor stop log message.  Must be called when actor is finished. */
	public void stop();

}
