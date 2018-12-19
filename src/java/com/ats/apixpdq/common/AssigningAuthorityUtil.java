package com.ats.apixpdq.common;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IActorDescription;
import com.ats.aexchange.actorconfig.net.IBaseDescription;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.datamodel.Identifier;

import com.ats.apixpdq.api.IPixPdqAdapter;


/**
 * This class contains the utility methods for 
 * assigning authority.
 *
 * @author Wenzhi Li
 * @version 1.0, Nov 27, 2008
 */
public class AssigningAuthorityUtil {

    private static final Logger log = Logger.getLogger(AssigningAuthorityUtil.class);

    /**
     * Reconciles authority with the BaseDescritpion configuration. An authority
     * can have NameSpace and/or UniversalId/UniversalIdType. For example, in the data source such as
     * database, if an authority is represented by NameSpace only, while in the xml configuration, the authority is configured
     * with both NameSpace and UnviersalId/UniversalIdType. The authority in the datasource has to be mapped
     * to the authority configured in the XML files.
     *
     * @param authority The authority
     * @param description either actor description or connection description
     * @param adapter the adapter from where to get the domains
     * @return The authority according the configuration
     */
    public static Identifier reconcileIdentifier(Identifier authority, IBaseDescription description, IPixPdqAdapter adapter) {
		Set<Identifier> defaultDomains = Configuration.getAllDomains(description);			
        Set<Identifier> identifiers = adapter.getDomainIdentifiers(defaultDomains);
        for (Identifier identifier : identifiers) {
            if ( identifier.getNamespaceId().equals(authority.getNamespaceId()) 
            		&& identifier.getUniversalId().equals(authority.getUniversalId())) 
            {
                return identifier;
            }
        }
        //no identifier is found, just return the original authority
        return authority;
    }
    
    /**
     * Validates whether an ID domain is valid against the connection configuration.
     *
     * @param id the feed or request ID domain to be validated
     * @param connection
     * @param adapter the adapter from where to get the domains
     * @return <code>true</code> if the idDomain is valid.
     */
    public static boolean validateDomain(Identifier id, IBaseDescription description, IPixPdqAdapter adapter) {
         if (id == null) return  false;

         Set<Identifier> defaultDomains = Configuration.getAllDomains(description);
         
         Set<Identifier> identifiers = adapter.getDomainIdentifiers(defaultDomains);
         
         for (Identifier identifier : identifiers) {
        	           
        	 if (identifier.getNamespaceId().equals(id.getNamespaceId()) && identifier.getUniversalId().equals(id.getUniversalId()))
                return true;
         }
//         if (log.isDebugEnabled()) 
//         {
//	         log.debug("Failed to validate domain: "+ id.getNamespaceId() + "," + id.getUniversalId() + "," + id.getUniversalIdType());
//	         
//	         log.debug("List of known domains:");
//	         
//	         for (Identifier identifier : identifiers)
//	         {
//	             log.debug("  Domain: "+ identifier.getNamespaceId() + "," + identifier.getUniversalId() + "," + identifier.getUniversalIdType());
//	         }
//         }
         return false;
    }
    

}
