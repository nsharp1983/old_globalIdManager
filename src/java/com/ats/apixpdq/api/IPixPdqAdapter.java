package com.ats.apixpdq.api;

import java.util.Set;

import com.ats.aexchange.datamodel.Identifier;




/** 
 * This is the super interface for both IPixManagerAdapter and 
 * IPdSupplierAdapater.
 *
 * @author Wenzhi Li
 * @version 1.0.1, Oct 29, 2009
 */
public interface IPixPdqAdapter {

    /**
     * Gets the domains (or assigning authorities) supported by this PIX Manager/PD Supplier.
     * The domains can be configured in either OpenPIXPDQ or eMPI. This method takes in a set of
     * default domains configured in OpenPIXPDQ configuration files. The adapter can choose to
     * return the default domains, or retrieve domains configured in eMPI, or simply merge the
     * domains configured in both OpenPIXPDQ and eMPI. 
     * 
     * @defaultDomains the default domains configured in the OpenPIXPDQ configuration file.
     * @return A set of domain identifiers.
     */
	public Set<Identifier> getDomainIdentifiers(Set<Identifier> defaultDomains);
	
	/**
	 * Gets the global domain (or global assigning authority) supported by this PIX Manager/PD
	 * Supplier. The global domain can be configured in either 
	 * 
	 * @param defaultGlobalDomain the default global domain configured in the OpenPIXPDQ 
	 * 		   configuration file
	 * @return the global domain identifier
	 */
	public Identifier getGlobalDomainIdentifier(Identifier defaultGlobalDomain);

}
