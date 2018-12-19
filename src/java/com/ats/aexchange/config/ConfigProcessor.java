package com.ats.aexchange.config;

import java.util.Collection;

import com.ats.aexchange.actorconfig.IActorDescription;


/**
 * The interface for configureation processor to handle configuration
 * preprocess and postprocess. 
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public interface ConfigProcessor {

	/**
	 * The logics to invoke before the configuration is loaded.
	 */
	public void preProcess();
	
	/**
	 * The logics to invoke after the configuration is loaded.
	 */
	public void postProcess(Collection<IActorDescription> actors);
}
