package com.ats.apixpdq.api;

import com.ats.aexchange.actorconfig.IheActor;

/**
 * This interface defines a patient demographics supplier (PDS) actor. PDS
 * is a server side actor specified by IHE patient demographics query (PDQ) 
 * profile. See Section 3.21 of <a href="http://www.ihe.net/Technical_Framework/index.cfm#IT">Vol. 2 (ITI TF-2): Transactions</a>, 
 * available on the IHE site.   
 *
 * @author Wenzhi Li
 * @version 1.0, Mar 27, 2007
 * @see IPdSupplierAdapter
 */
public interface IPdSupplier extends IheActor {
	
}
