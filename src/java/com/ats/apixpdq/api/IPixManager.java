package com.ats.apixpdq.api;

import java.util.Collection;

import com.ats.aexchange.actorconfig.IheActor;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;

/**
 * This interface defines a patient Identifier cross reference manager (PIX Manager)actor. 
 * PIX Manager is a server side actor specified by IHE PIX profile. 
 * See Section 3.8, 3.9 and 3.10 of <a href="http://www.ihe.net/Technical_Framework/index.cfm#IT">Vol. 2 (ITI TF-2): Transactions</a>, 
 * available on the IHE site.   
 *
 * @author Wenzhi Li
 * @version 1.0, Mar 1, 2007
 */
public interface IPixManager extends IheActor {
	
	/**
	 * Gets the connection for the XDS Registry. The connect provides the details such as host name 
	 * and port etc which are needed for this PIX Manager to talk to the XDS Registry.
	 * 
	 * @return the connection of XDS Registry
	 */
	public IConnectionDescription getXdsRegistryConnection(); 

	/**
     * Gets a collection of all PIX Consumers who have subscribed to
     * the PIX Update Notification transaction.
     *  
	 * @return the pixConsumerConnections
	 */
    Collection<IConnectionDescription> getPixConsumerConnections();
	
}
