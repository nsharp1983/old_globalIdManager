package com.ats.apixpdq.impl.v2;

import java.util.Collection;

import org.apache.log4j.Logger;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IActorDescription;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.audit.IheAuditTrail;

import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.parser.PipeParser;

import com.ats.apixpdq.api.IPixManager;
import com.ats.apixpdq.api.IStatusChange;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.HL7Actor;
import com.ats.apixpdq.impl.v2.hl7.HL7Server;
import com.misys.hieportal.sysmon.IJMXEventNotifier;

/**
 * This is the Patient Identifier Cross-referencing (PIX) Manager actor, 
 * the server side actor of the IHE PIX profile. This actor accepts HL7 v2 messages
 * such as ADT^A01, ADT^A04, ADT^A05, ADT^A08 and ADT^A40 from a PIX Source, and QBP^Q23 from a PIX Consumer.
 *  The transactions that this actor handles include PIX Feed, PIX Update, PIX Merge, 
 *  PIX Query and PIX Update Notification. See Sections 3.8, 3.9 and 3.10 of 
 * <a href="http://www.ihe.net/Technical_Framework/index.cfm#IT">Vol. 2 (ITI TF-2): Transactions</a>, 
 * available on the IHE site for more details.   
 *
 * @author Wenzhi Li
 * @version 1.0, Mar 1, 2007
 * @see IPixManager
 */
public class HiupStatusManager extends HL7Actor implements IStatusChange {
    /* Logger for problems during SOAP exchanges */
    private static Logger log = Logger.getLogger(HiupStatusManager.class);

	/** The main server connection this actor will be using */
	protected IConnectionDescription connection = null;  
	/** The XDS Registry Connection */
	private IConnectionDescription xdsRegistryConnection = null;
    /* The connections for PIX Consumers that subscribe to the PIX Update Notification*/
    private Collection<IConnectionDescription> pixConsumerConnections = null;
    /** The PIX Server */
    private HL7Server server = null;
    /** PIX event notifier **/
    private IJMXEventNotifier pixEvent = null; 
    /** Whether to get notification from eMPI about patient changes*/
    private boolean notification = false;

   /**
    * Creates a new PixManager that will talk to a PIX client over
    * the connection description supplied.
    *
    * @param actor The actor description of this PIX manager
    * @param auditTrail The audit trail for this PIX Manager
    * @param xdsRegistryConnection The description of the connection of the XDS
    * 			Registry in the affinity domain
    * @param pixConsumerConnections The connections of PIX Consumers subscribing
    * 			to PIX Update Notification messages
    * @throws IheConfigurationException
    */
    public HiupStatusManager(IActorDescription actor, IheAuditTrail auditTrail) 
    	    throws IheConfigurationException {
        super(actor, auditTrail);
		this.connection = Configuration.getConnection(actor, PixPdqConstants.SERVER, true);
		this.xdsRegistryConnection = Configuration.getConnection(actor, PixPdqConstants.XDS_REGISTRY, false);
		this.pixConsumerConnections = Configuration.getConnections(actor, PixPdqConstants.PIX_CONSUMER, false);
        if(this.connection.getProperty("notify") != null){
            setNotification(this.connection.getProperty("notify").equalsIgnoreCase("true")?true:false);
        }
    }

    @Override
    public void start() {
        //call the super one to initiate standard start process
        super.start();
       
        //now begin the local start, initiate pix manager server
        
        LowerLayerProtocol llp = LowerLayerProtocol.makeLLP(); // The transport protocol
        
        PipeParser parser = new PipeParser();
        
        parser.setValidationContext(new MessageValidation());
        
        server = new HL7Server(connection, llp, parser);
            
        Application HiupStatusFeed = new HiupStatusChange(this);
     
        if (isNotification()){
            server.registerApplication("*", "*", HiupStatusFeed);
        }
       
        //A02患者转移-包括转科/转床
        server.registerApplication("ADT", "A02", HiupStatusFeed);
        
        //A03出院/终止就诊-panmin-20121105
        server.registerApplication("ADT", "A03", HiupStatusFeed);
        
        //A06门诊转住院-panmin-20121105
        server.registerApplication("ADT", "A06", HiupStatusFeed);
        
        //A07住院转门诊-panmin-20121105
        server.registerApplication("ADT", "A07", HiupStatusFeed);
        
        //A09患者暂离追踪
        server.registerApplication("ADT","A09", HiupStatusFeed);
        
        //A10患者到达追踪
        server.registerApplication("ADT","A10", HiupStatusFeed);
        
        //A11取消入院/就诊通知-panmin-20121105
        server.registerApplication("ADT", "A11", HiupStatusFeed);
        
        //A12取消转移-panmin-20121105
        server.registerApplication("ADT", "A12", HiupStatusFeed);
        
        //A13取消出院/终止就诊-panmin-20121105
        server.registerApplication("ADT", "A13", HiupStatusFeed);
        
        //A21患者请假
        server.registerApplication("ADT", "A21", HiupStatusFeed);
        
        //A22患者请假后返回
        server.registerApplication("ADT", "A22", HiupStatusFeed);
        
        //A29-删除患者记录
        server.registerApplication("ADT", "A29", HiupStatusFeed);
        
        //A32取消患者到达追踪
        server.registerApplication("ADT", "A32", HiupStatusFeed);
        
        //A33取消患者暂离追踪
        server.registerApplication("ADT", "A33", HiupStatusFeed);
        
        //A47改变患者标识符表
        server.registerApplication("ADT", "A47", HiupStatusFeed);
        
        //A50改变患者就诊号
        server.registerApplication("ADT", "A50", HiupStatusFeed);
        
        //A52取消患者请假
        server.registerApplication("ADT", "A52", HiupStatusFeed);
        
        //A53取消患者请假后返回
        server.registerApplication("ADT", "A53", HiupStatusFeed);
        
        
        
        //now start the Pix Manager server
        server.start();
        
        if(log.isInfoEnabled()) {
        	log.info("Started PIX Manager: " + this.getName() );
        }
    }

    @Override
    public void stop() {
        //now end the local stop, stop the pix manager server
        server.stop();

        //call the super one to initiate standard stop process
        super.stop();
        
        if(log.isInfoEnabled()) {
        	log.info("Stopped PIX Manager: " + this.getName() );
        }
    }
    
    public boolean isNotification(){return notification;}
    public void setNotification(boolean notification){this.notification = notification;}
    
    /**
     * Gets the event notifier bean for this <code>PixManager</code>
     * 
     * @return the event bean
     */
    IJMXEventNotifier getPixEvent() {
    	return pixEvent;
    } 
    
    /**
     * Sets the event notifier bean for this <code>PixManager</code>
     * 
     *@param Pix event bean
     *
     */
	public void setPixEvent(IJMXEventNotifier pixEvent) {
		this.pixEvent = pixEvent;
	}
    
	/**
	 * Gets the main server connection of this actor.
	 * 
	 * @return the main connection
	 */ 
	public IConnectionDescription getConnection() {
		return connection;
	}
	
	

	public static void main(String[] args) throws Exception {

    }


}

