package com.ats.aexchange.actorconfig.net;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * An abstract implementation of IServerConnection which does a number of items required by all connections. <p />
 * <p/>
 * To make a new type of connection which requires these features simply extend this class
 * and implement the additionally required features.  Remember that the connect call is
 * where the socket (or other connection type) should be made.
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public abstract class GenericServerConnection implements IServerConnection {
	private static final Log log = LogFactory.getLog(GenericServerConnection.class);
    /**
     * The actual connection.
     */
    protected ServerSocket ssocket = null;
    /**
     * The description of the connection. This includes everything needed to connect.
     */
    protected IConnectionDescription description = null;

    public GenericServerConnection(IConnectionDescription connectionDescription) {
        description = connectionDescription;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IConnection#getConnectionDescription()
     */
    public IConnectionDescription getConnectionDescription() {
        return description;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IServerConnection#isServerConnectionValid()
     */
    public boolean isServerConnectionValid() {
        boolean isValid = false;
        if (ssocket != null) {
            isValid = ssocket.isBound();
        }
        return isValid;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IServerConnection#getServerSocket()
     */
    public ServerSocket getServerSocket() {
        ServerSocket returnVal = null;
        if (isServerConnectionValid()) {
            returnVal = ssocket;
        }
        // TODO add logging message.
        return returnVal;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IServerConnection#closeServerConnection()
     */
    public void closeServerConnection() {
        if (isServerConnectionValid()) {
            try {
                ssocket.close();
            }
            catch (IOException e) {
                ;
            } // TODO add logging message.
            // TODO add ATNA message?
        }
        ssocket = null;
    }

    /**
     * This function must be instantiated by the subclasses
     * because it generates all the actual server sockets when the
     * connection is made.
     */
    public abstract void connect();

}
