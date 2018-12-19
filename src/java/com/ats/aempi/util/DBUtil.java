package com.ats.aempi.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	//打开数据库连接
	public Connection openConnection(){
		
		Properties properties=new Properties();
		String driver=null;
		String url=null;
		String username=null;
		String password=null;
		Connection conn=null;
		
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("apixpdq.properties"));
			driver=properties.getProperty("jdbc.driverClassName");
			url=properties.getProperty("jdbc.url");
			username=properties.getProperty("jdbc.username");
			password=properties.getProperty("jdbc.password");
		
				Class.forName(driver);
				conn= DriverManager.getConnection(url, username, password);
				conn.setAutoCommit(false);//关闭自动提交
				

				} catch (SQLException e) {
				e.printStackTrace();
				}
			 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return conn;
		
	}
	
	//关闭数据库连接
	public void closeConn(Connection conn){
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
