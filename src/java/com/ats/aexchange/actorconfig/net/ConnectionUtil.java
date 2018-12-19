package com.ats.aexchange.actorconfig.net;

import org.apache.commons.httpclient.protocol.Protocol;

import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.actorconfig.net.SecureConnectionDescription;
import com.ats.aexchange.actorconfig.net.SecureSocketFactory;


/**
 * The helper class to manipulate IConnectionDescription objects to
 * get the web services url and transport protocol etc.
 *  
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class ConnectionUtil {
	/**
	 * Assembles a web service end point based on the given <code>ConnectionDescription</code>
	 * 
	 * @param connection the ConnectionDescription
	 * @return the URL string of the web service
	 */
	public static String getTransactionEndpoint(IConnectionDescription connection) {
		if (connection == null)
			return null;
		
		String host = connection.getHostname();
		if (host == null)
			host = "localhost";
		
		int port = connection.getPort();
		boolean isSecure = connection.isSecure();
		String url = "http://";
		if(isSecure) {
			url="https://";
		}
		
		String path = connection.getUrlPath();
		if (!path.startsWith("/")) 
			path = "/" + path;
		
		url+= host + ":" + port + connection.getUrlPath();
		return url;
	}

	/**
	 * Creates a customized protocol, e.g. https. This protocol can be used
	 * to replaced the default protocol used by Axis2.
	 * 
	 * @param connection the ConnectionDescription
	 * @return a Protocol object. Null is returned if no customization is needed,
	 * and a default protocol is supposed to be used.
	 */
	public static Protocol getProtocol(IConnectionDescription connection) {
		Protocol protocol = null;
		if (connection.isSecure()) {
			protocol = new Protocol("https", new SecureSocketFactory((SecureConnectionDescription)connection), connection.getPort());			
		}
		return protocol;		
	}
}
