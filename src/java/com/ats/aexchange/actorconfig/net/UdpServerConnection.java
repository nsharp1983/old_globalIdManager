package com.ats.aexchange.actorconfig.net;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:wenzhi.li@misys.com">Wenzhi Li</a>
 */
public class UdpServerConnection implements IUdpServerConnection {

    private static final Log log = LogFactory.getLog(UdpServerConnection.class);
    private IConnectionDescription description;
    private DatagramSocket socket;
    private final int DEFAULT_MAX_TRANSMISSION_UNIT = 32786;

    public UdpServerConnection(IConnectionDescription description) {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IUdpServerConnection#isServerConnectionValid()
     */
    public boolean isServerConnectionValid() {
        return socket != null && socket.isBound();
    }


    /* (non-Javadoc)
    * @see com.ats.aatna.net.IUdpServerConnection#getServerSocket()
    */
    public DatagramSocket getServerSocket() {
        DatagramSocket returnVal = null;
        if (isServerConnectionValid()) {
            returnVal = socket;
        }

        return returnVal;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IUdpServerConnection#closeServerConnection()
     */
    public void closeServerConnection() {
        if (isServerConnectionValid()) {
            socket.close();
        }
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IUdpServerConnection#getMaxTransmissionUnit()
     */
    public int getMaxTransmissionUnit() {
        int ret = DEFAULT_MAX_TRANSMISSION_UNIT;

        PropertySet udpProps = description.getPropertySet("UdpProperties");
        if (udpProps == null) {
            return ret;
        }
        String mtu = udpProps.getValue("mtu");
        if (mtu != null) {
            try {
                ret = Integer.parseInt(mtu);
            } catch (NumberFormatException e) {
                log.warn("Cannot parse mtu in connection " + description.getName(), e);
            }
        }

        return ret;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IUdpServerConnection#getConnectionDescription()
     */
    public IConnectionDescription getConnectionDescription() {
        return description;
    }

    /* (non-Javadoc)
     * @see com.ats.aatna.net.IUdpServerConnection#connect()
     */
    public void connect() {
        int port = description.getPort();
        String addr = description.getHostname();
        try {
            socket = new DatagramSocket(port, InetAddress.getByName(addr));
        } catch (SocketException e) {
            log.error("UDP socket Connection Error", e);
        } catch (UnknownHostException e) {
            log.error("Unknown host for UDP socket", e);
        }

    }
}
