package com.ats.apixpdq.web.servlet;

import java.io.File;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aexchange.actorconfig.ActorConfigurationLoader;
import com.ats.aexchange.actorconfig.ActorDescriptionLoader;
import com.ats.aexchange.actorconfig.AuditBroker;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IActorDescription;
import com.ats.aexchange.actorconfig.IBrokerController;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.audit.IheAuditTrail;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.log.IMesaLogger;

import com.ats.apixpdq.api.IMessageStoreLogger;
import com.ats.apixpdq.api.IPdSupplier;
import com.ats.apixpdq.api.IPixManager;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.PatientBroker;
import com.ats.apixpdq.common.PixPdqFactory;
import com.ats.apixpdq.impl.v2.HiupStatusManager;
import com.ats.apixpdq.impl.v2.PdSupplier;
import com.ats.apixpdq.impl.v2.PixManager;
import com.ats.apixpdq.web.servlet.PixPdqConfigurationLoader;

import com.misys.hieportal.sysmon.IJMXEventNotifier;

/**
 * This class loads an Actor configuration file and initializes all of the
 * appropriate APIXPDQ actors within the PatientBroker and AuditBroker.
 * 
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class PixPdqConfigurationLoader extends ActorConfigurationLoader {

	/* Logger for debugging messages */
	private static final Log log = LogFactory.getLog(PixPdqConfigurationLoader.class);
	
	/* Singleton instance */
	private static PixPdqConfigurationLoader instance = null;
	
	private IJMXEventNotifier eventBean = null;
	
	/**
	 * Gets the singleton instance for this class.
	 * 
	 * @return the singleton ConfigurationLoader
	 */
	public static synchronized PixPdqConfigurationLoader getInstance() {
		if (instance == null) instance = new PixPdqConfigurationLoader();
		return instance;
	}
	
	@Override
	protected void destroyAllActors() {
		// Create a controller that will reset all IHE actors
		IheBrokerController controller = new IheBrokerController();
		// Apply it to the AuditBroker
		AuditBroker abroker = AuditBroker.getInstance();
		abroker.unregisterAuditSources(controller);
		// Apply it to the PatientBroker
		PatientBroker pbroker = PatientBroker.getInstance();
        pbroker.unregisterPixManagers(controller);
        pbroker.unregisterPdSuppliers(controller);
        // Okay, nothing is installed
		actorsInstalled.clear();
	}
	
	@Override
	protected boolean validateActor(IActorDescription actor, File configFile) throws IheConfigurationException {
		String actorName = actor.getName();
		// Make sure we got out a valid definition
		if (actor.getType().equalsIgnoreCase(PixPdqConstants.PIX_MANAGER)) {
			IConnectionDescription connection = Configuration.getConnection(actor, PixPdqConstants.SERVER, true);
			assert connection != null;
			
			IConnectionDescription xdsRegistryConnection = Configuration.getConnection(actor, PixPdqConstants.XDS_REGISTRY, false);
			if (xdsRegistryConnection != null) {
				//Global Domain is required for XDS Registry
				validateGlobalDomain(actor);
			}
		} 
		else if (actor.getType().equalsIgnoreCase(PixPdqConstants.PD_SUPPLIER)) {
			IConnectionDescription connection = Configuration.getConnection(actor, PixPdqConstants.SERVER, true);
			assert connection != null;
		}
		else if (actor.getType().equalsIgnoreCase(PixPdqConstants.HIUP_STATUS)) {
			IConnectionDescription connection = Configuration.getConnection(actor, PixPdqConstants.SERVER, true);
			assert connection != null;
		}
		else if (actor.getType().equalsIgnoreCase(SECURENODE)) {
			if (actor.getAuditLogConnection().isEmpty())
				throw new IheConfigurationException("Actor '" + actorName + "' must specify a valid '" + AUDITTRAIL + "' element");
		}
		else {
			throw new IheConfigurationException("Actor '" + actorName + "' must specify a valid actor type");			
		}
		
		return true;
	}

	private void validateGlobalDomain(IActorDescription actor) throws IheConfigurationException {
		Identifier defaultDomain = Configuration.getGlobalDomain(actor, false);	
		Identifier globalDomain = PixPdqFactory.getPixManagerAdapter().getGlobalDomainIdentifier(defaultDomain);
		if (null == globalDomain) {
			throw new IheConfigurationException("Global Domain is not defined");
		}
	}
	
	@Override
	protected boolean createIheActor(IActorDescription actor, Collection<IConnectionDescription> auditLogs, IMesaLogger logger, File configFile) 
	throws IheConfigurationException {
		boolean okay = false;
		IheAuditTrail auditTrail = null;
		
		// Build a new audit trail if there are any connections to audit repositories.
		if (!auditLogs.isEmpty()) { 
			auditTrail = new IheAuditTrail(actor.getName(), auditLogs);
		}
		// Actually create the actor
		if (actor.getType().equalsIgnoreCase(SECURENODE)) {
			if (auditTrail != null) {
				AuditBroker broker = AuditBroker.getInstance();
				broker.registerAuditSource(auditTrail);
				okay = true;
			}
		}
		else if (actor.getType().equalsIgnoreCase(PixPdqConstants.PIX_MANAGER)) {
	        PixManager pixMan =  new PixManager(actor, auditTrail);
	        if (pixMan != null) { 
	        	pixMan.setStoreLogger(getMessageStore(pixMan.getConnection(), actor.getType(), configFile));
	            pixMan.setMesaLogger(logger);
	            pixMan.setPixEvent(eventBean);
	            PatientBroker broker = PatientBroker.getInstance();
	            broker.registerPixManager(pixMan);
	            okay = true;
	        }
		}
		else if (actor.getType().equalsIgnoreCase(PixPdqConstants.PD_SUPPLIER)) {
            PdSupplier pdSup = new PdSupplier(actor, auditTrail);
            if (pdSup != null) {
                pdSup.setStoreLogger(getMessageStore(pdSup.getConnection(), actor.getType(), configFile));
                pdSup.setMesaLogger(logger);
                PatientBroker broker = PatientBroker.getInstance();
                broker.registerPdSupplier(pdSup);
                okay = true;
            }
		}
		else if (actor.getType().equalsIgnoreCase(PixPdqConstants.HIUP_STATUS)) {
            HiupStatusManager StatusManager = new HiupStatusManager(actor, auditTrail);
            if (StatusManager != null) {
            	StatusManager.setStoreLogger(getMessageStore(StatusManager.getConnection(), actor.getType(), configFile));
            	StatusManager.setMesaLogger(logger);
                PatientBroker broker = PatientBroker.getInstance();
                broker.registerStatusChange(StatusManager);
                okay = true;
            }
		}
		else {
			ActorDescriptionLoader.throwIheConfigurationException("Invalid actor type '" + actor.getType() + "'", configFile);
		}
		
        // Record this installation, if it succeeded
		if (okay) actorsInstalled.add(actor.getName()); 
		return okay;
	}

	    /**
	     * Gets the {@link IMessageStoreLogger} from the storeLogger XML configuration.
	     * 
	     * @param connection the connection description of this actor
	     * @param type the type of this actor
	     * @param configFile the configuration file
	     * @return the {@link IMessageStoreLogger} as in the configuration. Otherwise, return
	     *         null if storeLogger is not configured.
	     */
	    private IMessageStoreLogger getMessageStore(IConnectionDescription connection, String type, 
	    		File configFile) throws IheConfigurationException {
	        String storeLogClass = Configuration.getPropertyValue(connection, "storeLogger", false);
	        IMessageStoreLogger storeLogger = null;
//TODO:
//	        if (storeLogClass != null) {
//	            try {
//	                Class c = Class.forName(storeLogClass);
//	                storeLogger = (IMessageStoreLogger)getObjectInstance( c );
//	            } catch (Exception e) {
//	                String message = "Could not load StoreLogger in actor type '"+ type +"' in config file " + configFile;
//	                log.error(message, e);
//	                throw new IheConfigurationException(message);
//	            }
//	        }
	    	return storeLogger;
	    }
		
		
	@Override
	protected String getHumanReadableActorType(String type, File configFile) throws IheConfigurationException {
		if (type.equalsIgnoreCase("SecureNode")) {
			return "Audit Record Repository";
		} else if (type.equals(PixPdqConstants.HIUP_STATUS)) {
            return "AHIUP Status Manager";
		} else if (type.equals(PixPdqConstants.PIX_MANAGER)) {
            return "APIXPDQ PIX Manager";
		} else if (type.equals(PixPdqConstants.PIX_MANAGER_V3)) {
            return "APIXPDQ PIX Manager V3";
        } else if (type.equals(PixPdqConstants.PD_SUPPLIER)) {
            return "APIXPDQ Patient Demographics Supplier";
        } else if (type.equals(PixPdqConstants.PD_SUPPLIER_V3)) {
            return "APIXPDQ Patient Demographics Supplier V3";
	    }
		else {
			ActorDescriptionLoader.throwIheConfigurationException("Invalid actor type '" + type + "'", configFile);
			return null;
		}
	}
	
	/**
	 * An implementation of a broker controller that will unregister and IHE actor.
	 * 
	 */
	public class IheBrokerController implements IBrokerController {

		/**
		 * Whether to unregister of a give actor or not
		 */
		public boolean shouldUnregister(Object actor) {
			// Unregister any IHE Actor
			if (actor instanceof IheAuditTrail) return true;
            if (actor instanceof IPixManager) return true;
            if (actor instanceof IPdSupplier) return true;

            return false;
		}
	}
	
	public IJMXEventNotifier getEventBean() {
		return eventBean;
	}

	public void setEventBean(IJMXEventNotifier eventBean) {
		this.eventBean = eventBean;
	}	
}
