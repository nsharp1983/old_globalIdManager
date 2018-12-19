package com.ats.aempi.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.model.AppUser;
import com.ats.aempi.service.UserManager;

/**
 * Implementation of UserContext
 *
 * @author Odysseas Pentakalos
 */
public class UserContext
{
	private static final Log log = LogFactory.getLog(UserContext.class);
	
	private UserManager userManager;
	
	private AppUser user;
	private String sessionKey;
	
	public String authenticate(String username, String password) throws AuthenticationException {
		AppUser user = userManager.authenticate(username, password);
		sessionKey = userManager.createSession(user);
		log.debug("Authentication request succeeded for user " + username);
		this.user = user;
		return sessionKey;
 	}

	public AppUser authenticate(String sessionKey) throws AuthenticationException {
		AppUser user = userManager.authenticate(sessionKey);
		this.sessionKey = sessionKey;
		this.user = user;
		return user;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
