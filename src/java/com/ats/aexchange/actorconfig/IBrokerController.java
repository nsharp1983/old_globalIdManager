package com.ats.aexchange.actorconfig;

/**
 * This interface is used in objects that are passed to Broker
 * reset methods.
 * 
 * @author Jim Firby
 */
public interface IBrokerController {
	
	/**
	 * Called by the Broker to see if the supplied actor should
	 * be removed and stopped.
	 * 
	 * @return 'true' if the supplied actor should be removed and stopped
	 */
	public boolean shouldUnregister(Object actor);

}
