package com.ats.aexchange.actorconfig.net;

import java.util.List;
import java.util.Set;


/**
 * The basic connection description interface.
 * <p/>
 * It should be used to get information about a specific
 * connection.  You can get descriptions from the
 * connection factory, or from a specific connection.
 *
 * @author Josh Flachsbart
 * @see ConnectionFactory, IConnection
 */
public interface IConnectionDescription extends IBaseDescription {

    /**
     * Used to get the other host in this connection.
     *
     * @return The IP or name of the other host.
     */
    public String getHostname();

    /**
     * Used to get the port on the remote host we connect to.
     *
     * @return The port as an integer, -1 if specific port is not set.
     */
    public int getPort();

    /**
     * Used to get the URL of the service we connect to, not
     * including the hostName or port.
     *
     * @return The url of the service, or null if not specified
     */
    public String getUrlPath();

    /**
     * Used to determine if this is an SSL/TLS connection.
     *
     * @return True if it is.
     */
    public boolean isSecure();

    /**
     * Used to determine if this is a server connection.
     *
     * @return True if it is.
     */
    public boolean isServer();
}
