package com.ats.aexchange.actorconfig.net;

import javax.net.ssl.SSLServerSocket;

/**
 * An encrypted tcp server connection.
 * <p/>
 * This should not be created directly but rather, requested from the ConnectionFactory.
 *
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class SecureServerConnection extends GenericServerConnection {
    /**
     * Used by the factory to create a server connection.
     */
    public SecureServerConnection(IConnectionDescription connectionDescription) {
        super(connectionDescription);
    }

    /**
     * Checks to make sure the description matches requirements for SecureServerConnection.
     */
    public boolean isServerConnectionValid() {
        boolean isValid = false;
        if ((description != null) &&
                description.isSecure() &&
                (description instanceof SecureConnectionDescription)) {
            isValid = super.isServerConnectionValid();
        }
        return isValid;
    }

    /**
     * Used by factory to start the server connection.
     */
    public void connect() {
        SSLServerSocket secureServerSocket = null;
        if (description != null && description instanceof SecureConnectionDescription) {
            SecureConnectionDescription scd = (SecureConnectionDescription) description;
            // Secure socket factory handles bidirectional certs.
            SecureSocketFactory sslFactory = new SecureSocketFactory(scd);
            secureServerSocket = (SSLServerSocket) sslFactory.createServerSocket(description.getPort());

            // TODO Add ATNA logging.
        }
        ssocket = secureServerSocket;
    }

}
