package com.ats.aempi.apixpdqadapter;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.context.Context;

public class SecurityHelper
{
	protected static final Log log = LogFactory.getLog(SecurityHelper.class);
	public static final String USERNAME_PROPERTY = "username";
	public static final String PASSWORD_PROPERTY = "password";
	public static final String DEFAULT_USERNAME = "admin";
	public static final String DEFAULT_PASSWORD = "admin";
	
	public static String getSessionKey() throws AuthenticationException, NamingException {
		log.debug("Current user context is " + Context.getUserContext());
		if (Context.getUserContext() != null && Context.getUserContext().getSessionKey() != null) {
			return Context.getUserContext().getSessionKey();
		}
		String sessionKey = Context.authenticate(getUsername(), getPassword());
		return sessionKey;
	}
	
	public static String getUsername() {
		String username = getSystemProperty(USERNAME_PROPERTY);
		if (username == null) {
			username = DEFAULT_USERNAME;
		}
		return username;
	}
	
	public static String getPassword() {
		String password = getSystemProperty(PASSWORD_PROPERTY);
		if (password == null) {
			password = DEFAULT_PASSWORD;
		}
		return password;
	}
	
	public static String getSystemProperty(String propertyName) {
		return System.getProperty(propertyName);
	}
}
