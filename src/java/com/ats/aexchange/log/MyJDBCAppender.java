package com.ats.aexchange.log;

import org.apache.log4j.*;
import org.apache.log4j.spi.*;
import com.ats.aexchange.log.MyPatternLayout;
import com.globalmentor.log.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class MyJDBCAppender extends org.apache.log4j.AppenderSkeleton


    implements org.apache.log4j.Appender {


  protected String databaseURL = "jdbc:odbc:myDB";

  protected String sql,TempStr1,TempStr2;

  protected String databaseUser = "me";

  protected String databasePassword = "mypassword";


  protected Connection connection = null;


  protected String sqlStatement = "";

  protected int bufferSize = 1;

  protected ArrayList buffer;


  protected ArrayList removes;


  public MyJDBCAppender() {


    super();

    buffer = new ArrayList(bufferSize);

    removes = new ArrayList(bufferSize);

  }

  public void append(LoggingEvent event) {
    buffer.add(event);
    if (buffer.size() >= bufferSize)
      flushBuffer();
  }

  protected String getLogStatement(LoggingEvent event) {

    return getLayout().format(event);

  }

  protected void execute(String sql) throws SQLException {

    Connection con = null;

    Statement stmt = null;

    try {

        con = getConnection();

        stmt = con.createStatement();
        
        //System.out.println(sql + "\n");

        stmt.executeUpdate(sql);

    } catch (SQLException e) {

       if (stmt != null)

         stmt.close();

       throw e;

    }

    stmt.close();

    closeConnection(con);

  }

  protected void closeConnection(Connection con) {

  }

  protected Connection getConnection() throws SQLException {

      if (!DriverManager.getDrivers().hasMoreElements())

         setDriver("sun.jdbc.odbc.JdbcOdbcDriver");

      if (connection == null) {

        connection = DriverManager.getConnection(databaseURL, databaseUser,

                    databasePassword);

      }

      return connection;

  }

  public void close()
  {
    flushBuffer();
    try {
      if (connection != null && !connection.isClosed())
          connection.close();
    } catch (SQLException e) {
        errorHandler.error("Error closing connection", e, ErrorCode.GENERIC_FAILURE);
    }
    this.closed = true;
  }

  public void flushBuffer() {

    removes.ensureCapacity(buffer.size());

    for (Iterator i = buffer.iterator(); i.hasNext();) {

      try {

        LoggingEvent logEvent = (LoggingEvent)i.next();

        sql = getLogStatement(logEvent);
        
        
        if (sql.length()>0 && sql.lastIndexOf("@#$!@DEBUG_MESSAGE:")>0)
        {
        	TempStr1=sql.substring(sql.lastIndexOf("@#$!@DEBUG_MESSAGE:")+19)
        	                              .replace("'","^").replace("&","^").replace("_", "=");
        	
        	if (TempStr1.length()>3999) 
        	{
        		TempStr1=TempStr1.substring(0,3999);
        	}
        	
        	TempStr2=sql.substring(0, sql.lastIndexOf("@#$!@DEBUG_MESSAGE:"));
        	
        	sql=TempStr2 + TempStr1 + "')";
        }

        execute(sql);

        removes.add(logEvent);

      }
      catch (SQLException e) {
    	
        errorHandler.error("Failed to excute sql:" + "\n" + sql +"\n", e,ErrorCode.FLUSH_FAILURE);

      }
    }

    buffer.removeAll(removes);

    removes.clear();

  }

  public void finalize() {

    close();

  }

  public boolean requiresLayout() {

    return true;

  }

  public void setSql(String s) {

    sqlStatement = s;

    if (getLayout() == null) {

        this.setLayout(new MyPatternLayout(s));

    }
    else {
        ((MyPatternLayout)getLayout()).setConversionPattern(s);
    }
  }

  public String getSql() {
    return sqlStatement;
  }


  public void setUser(String user) {
    databaseUser = user;
  }

  public void setURL(String url) {
    databaseURL = url;
  }

  public void setPassword(String password) {
    databasePassword = password;
  }

  public void setBufferSize(int newBufferSize) {
    bufferSize = newBufferSize;
    buffer.ensureCapacity(bufferSize);
    removes.ensureCapacity(bufferSize);
  }

  public String getUser() {
    return databaseUser;
  }

  public String getURL() {
    return databaseURL;
  }


  public String getPassword() {
    return databasePassword;
  }

  public int getBufferSize() {
    return bufferSize;
  }

  public void setDriver(String driverClass) {
    try {
      Class.forName(driverClass);
    } catch (Exception e) {
      errorHandler.error("Failed to load driver", e,
             ErrorCode.GENERIC_FAILURE);
    }
  }
}
