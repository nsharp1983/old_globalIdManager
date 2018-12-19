package com.ats.aexchange.actorconfig.net;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ats.aexchange.utils.StringUtil;


/**
 * A standard non encrypted tcp connection.
 * <p/>
 * This should not be created directly but rather, requested from the ConnectionFactory.
 *
 * @author Josh Flachsbart
 * @see ConnectionFactory
 */
public class StandardConnection extends GenericConnection {
    /**Commons Log*/
	private static final Log log = LogFactory.getLog(StandardConnection.class);
    /**
     * Used by the factory to create a connection.
     */
    public StandardConnection(IConnectionDescription connectionDescription) {
        super(connectionDescription);
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IConnection#getConnectionEndpoint()
     */
    public String getConnectionEndpoint() {
		String host = description.getHostname();
		if (host == null)
			host = "localhost";
		
		int port = description.getPort();
		
		String url = "http://" + host + ":" + port;

		//path is optional
		String path = description.getUrlPath();
		if (StringUtil.goodString(path)) {
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			url += path;
		}
		
		return url;
    }
    
    /**
     * Used by factory to start the connection.
     */
    public void connect() {
        try {
            socket = new Socket(description.getHostname(), description.getPort());
            // TODO add ATNA logging message, perhaps in finally?
        } catch (IOException e) {
            log.error("Failed to create a socket on hostname:" + description.getHostname()
                    + " port:" + description.getPort(), e);
            socket = null;
        }
    }
}
