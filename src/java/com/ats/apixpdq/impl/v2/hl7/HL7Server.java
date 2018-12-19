package com.ats.apixpdq.impl.v2.hl7;

import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;


import org.apache.log4j.Logger;
import com.ats.aexchange.actorconfig.net.ConnectionFactory;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.actorconfig.net.IServerConnection;

import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.HL7Service;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.parser.Parser;

/**
 * Hl7v2服务端
 *
 * @author Hansen
 * @version 1.0.0.0, 2016-08-22
 */
public class HL7Server extends HL7Service {

    private static Logger log = Logger.getLogger(HL7Server.class);
    private IConnectionDescription connection;
    private Connection conn =null; 
    /**
     * 构造函数 
     */
     public HL7Server(IConnectionDescription conn, LowerLayerProtocol llp, Parser parser) {
        super(parser, llp);
        this.connection = conn;
     }

    /**
    * 监听并处理HL7消息
    */
    public void afterStartup() {
    	super.afterStartup();
         try {
			IServerConnection serverConn = ConnectionFactory.getServerConnection(connection);
			ServerSocket ss = serverConn.getServerSocket();
			
            ss.setSoTimeout(60000);
            
            //log.fatal(connection.getDescription() + " is running on port " + ss.getLocalPort());
            while (isRunning()) {
                try {
                     Socket newSocket = ss.accept();
                     //log.fatal("Accepted connection from " + newSocket.getInetAddress().getHostAddress());
                     //System.out.print("Accepted connection from " + newSocket.getInetAddress().getHostAddress());
                     conn = new Connection(parser, this.llp, newSocket);
                     
                     newConnection(conn);
                    
                 }
                catch (InterruptedIOException ie) {
                     //ignore - just timed out waiting for connection
                 }              
                catch (Exception e) {
                     log.error( "Error accepting HL7 connections: ", e);
                }
                finally
                {
                	conn=null;
                }

              }
              ss.close();
              
          }
          catch (Exception e) {
              log.error("Exception", e);
        }
         finally {
             this.stop();
        }
      }

	@Override
	protected void handle() {
		
	}
	
}
