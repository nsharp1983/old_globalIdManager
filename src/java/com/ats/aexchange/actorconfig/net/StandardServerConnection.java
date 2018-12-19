package com.ats.aexchange.actorconfig.net;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A standard non encrypted tcp server connection.
 * <p/>
 * This should not be created directly but rather, requested from the ConnectionFactory.
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class StandardServerConnection extends GenericServerConnection {
    /**Commons Log*/
	private static final Log log = LogFactory.getLog(StandardServerConnection.class);
    /**
     * Used by the factory to create a connection.
     */
    public StandardServerConnection(IConnectionDescription connectionDescription) {
        super(connectionDescription);
    }

    /**
     * Used by factory to start the connection.
     */
    public void connect() {
        try {
            ssocket = new ServerSocket(description.getPort());
            // TODO add ATNA logging message, perhaps in finally?
        } catch (IOException e) {
            log.error("Failed to create a server socket on port:" + description.getPort(), e);
            ssocket = null;
        }
    }
}
