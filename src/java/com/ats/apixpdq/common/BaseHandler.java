package com.ats.apixpdq.common;

import java.net.InetAddress;

import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.actorconfig.net.IBaseDescription;
import com.ats.aexchange.datamodel.Identifier;


/**
 * The base class of all handlers. It provides some common 
 * methods available to all handlers.
 * 
 * @author Wenzhi Li
 * @version 1.0, Dec 26, 2008
 */
public class BaseHandler extends PixPdqSysLogService{
	
	/**
	 * Default constructor
	 */
	public BaseHandler() {
	}
	
	/**
	 * Gets the application name of this PIX/PDQ server. If the server receives messages from
	 * a source application, then the application name is the same as ReceivingApplication, configurable
	 * through the XML actor configuration files.
	 * 
	 * @param description the actor or connection description where this server application is defined.
	 * @return the application name of this PIX/PDQ server
	 * @throws PixPdqException
	 */
	protected Identifier getServerApplication(IBaseDescription description) throws PixPdqException {
		Identifier ret = null;
		try {
			ret = Configuration.getIdentifier(description,
					"ReceivingApplication", true);
		} catch (IheConfigurationException e) {
			throw new PixPdqException(
					"Missing ReceivingApplication in Actor or Connection "
							+ description.getDescription(), e);
		}
		return ret;		
	}
    
	/**
	 * Gets the facility name of this PIX/PDQ server. If the server receives messages from
	 * a source application, then the facility name is the same as ReceivingFacility, configurable
	 * through the XML actor configuration files.
	 * 
	 * @param description the actor or conenction description where this server facility is defined.
	 * @return the facility name of this PIX/PDQ server
	 * @throws PixPdqException
	 */
	protected Identifier getServerFacility(IBaseDescription description) throws PixPdqException {
		Identifier ret = null;
		try {
			ret = Configuration.getIdentifier(description,
					"ReceivingFacility", true);
		} catch (IheConfigurationException e) {
			throw new PixPdqException(
					"Missing ReceivingFacility in Actor or Connection "
							+ description.getDescription(), e);
		}
		return ret;				
	}
	
	private static String ip = null;
	static {
		try {
			InetAddress addr = InetAddress.getLocalHost();
		    ip = addr.getHostAddress();
		}catch(Exception e) {
			//just ignore it.
		}
	}
	
	private static int staticCounter=0;
	private static final int nBits=4;
	
	/**
	 * Generates a new unique message id.
	 * 
	 * @return a message id
	 */
	public static synchronized String getMessageControlId() {
		String prefix = ""; 
		if (ip != null)	prefix += toHex(ip);
		long temp = (System.currentTimeMillis() << nBits) | (staticCounter++ & 2^nBits-1);
		String hex = Long.toHexString(temp);
		String id = prefix + hex;
		return id;
	}
	
    /**
     * Converts an IP address to a hex string.
     *         
     * @param ipAddress an IP address 
     * 
     * @return the IP address in a hex form
     */  
     protected static String toHex(String ipAddress) {                
    	 return Long.toHexString(toLong(ipAddress));        
     }  
     /**
      * Converts an IP address to a long
      * 
      * @param ipAddress the IP address
      * 
      * @return the IP address as a long        
      */   
     protected static long toLong(String ipAddress) {     
    	 long result = 0;           
    	 String[] atoms = ipAddress.split("\\.");     
    	 for (int i = 3; i >= 0; i--) {  
    		 result |= (Long.parseLong(atoms[3 - i]) << (i * 8)); 
    	 }
    	 return result & 0xFFFFFFFF;        
     }

}
