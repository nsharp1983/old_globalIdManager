package com.ats.aempi.service.impl;

import java.io.File;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.EntityExistsException;

import com.ats.aempi.AuthenticationException;
import com.ats.aempi.context.Context;
import com.ats.aempi.dao.UserDao;
import com.ats.aempi.dao.UserSessionDao;
import com.ats.aempi.model.AppUser;
import com.ats.aempi.model.UserFile;
import com.ats.aempi.model.UserSession;
import com.ats.aempi.service.UserExistsException;
import com.ats.aempi.service.UserManager;
import com.ats.aempi.service.UserService;
import com.ats.aempi.util.SessionGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UsernameNotFoundException;


/**
 * Implementation of UserManager interface.
 *
 * @author Odysseas Pentakalos
 */
@WebService(serviceName = "UserService", endpointInterface = "com.ats.aempi.service.UserService")
public class UserManagerImpl extends UniversalManagerImpl implements UserManager, UserService
{
    private UserDao dao;
    private UserSessionDao userSessionDao;
    private PasswordEncoder passwordEncoder;

    /**
     * Set the PasswordEncoder used to encrypt passwords.
     * @param passwordEncoder the PasswordEncoder implementation
     */
    @Required
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * {@inheritDoc}
     */
    public AppUser getUser(String userId) {
        return dao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    public List<AppUser> getUsers(AppUser user) {
        return dao.getUsers();
    }
    
    public String createSession(AppUser user) {
    	String sessionKey = SessionGenerator.generateSessionId();
    	//log.debug(Integer.valueOf(sessionKey));
    	UserSession userSession = new UserSession(sessionKey, user, new java.util.Date());
    	userSessionDao.saveUserSession(userSession);
    	return sessionKey;
    }
    
    public AppUser authenticate(String username, String password) throws AuthenticationException {
		log.debug("Authentication request for user with username: " + username);
		if (username == null || password == null) {
			log.warn("Authentication attempt failed due to missing credentials: username=" + username + "; password=" + password);
			throw new AuthenticationException("Authentication failed; no credentials were provided in the request.");
		}
		
		AppUser user = (AppUser) getUserByUsername(username);
		if (user == null) {
			log.warn("Authentication attempt with unknown username " + username);
			throw new AuthenticationException("Authentication failed.");
		}
		
		String encrypted = passwordEncoder.encodePassword(password, null);
		log.debug("Encrypted password is " + encrypted + " as opposed to " + user.getPassword());
		if (!encrypted.equalsIgnoreCase(user.getPassword())) {
			log.warn("Authentication attempt failed due to incorrect password.");
			throw new AuthenticationException("Authentication failed.");
		}
		return user;
    }
    
    public AppUser authenticate(String sessionKey) throws AuthenticationException {
    	log.debug("Authentication request for user with session id: " + sessionKey);
    	UserSession userSession = userSessionDao.findBySessionKey(sessionKey);
    	if (userSession == null) {
    		log.warn("Authentication attempt failed due to invalid session key: " + sessionKey);
    		throw new AuthenticationException("Invalid session key");
    	}
    	log.debug("Authentication attempt succeeded for user: " + userSession.getSessionKey() + " and session key " + sessionKey);
    	return userSession.getAppUser();
    }
    
    /** 
     * TODO Need to add support for a logout operation that removes the session from the system
     */
    public void logout(UserSession userSession) {
    	
    }
    
    /**
     * {@inheritDoc}
     */
    public AppUser saveUser(AppUser user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }
        
        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                String currentPassword = dao.getUserPassword(user.getUsername());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }
        
        try {
            return dao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        } catch (EntityExistsException e) { // needed for JPA
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userId) {
        log.debug("removing user: " + userId);
        dao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public AppUser getUserByUsername(String username) throws UsernameNotFoundException {
        return (AppUser) dao.loadUserByUsername(username);
    }

    public UserFile getUserFile(Integer userFileId) {
    	log.debug("Loading user file entry with identifier: " + userFileId);
    	if (userFileId == null) {
    		return null;
    	}
    	return dao.getUserFile(userFileId);
    }
    
	public List<UserFile> getUserFiles(AppUser user) {
		log.debug("Loading user file entries for user: " + user);
		return dao.getUserFiles(user);
	}

	public void removeUserFile(UserFile userFile) {
		log.debug("Deleting a user file entry: " + userFile);
		if (userFile.getUserFileId() == null) {
			return;
		}
		UserFile userFileEntry = dao.getUserFile(userFile.getUserFileId());
		if (userFileEntry == null) {
			return;
		}
		File file = new File(userFileEntry.getFilename());
		boolean deleteOutcome = file.delete();
		log.debug("Deleting physical upload file stored at: " + file.getAbsolutePath() + " returned " + deleteOutcome);
		dao.removeUserFile(userFile);
	}

	public UserFile saveUserFile(UserFile userFile) {
		userFile.setDateCreated( new java.util.Date());
		userFile.setAppUser(Context.getUserContext().getUser());
		return dao.saveUserFile(userFile);
	}

	public UserSessionDao getUserSessionDao() {
		return userSessionDao;
	}

	public void setUserSessionDao(UserSessionDao userSessionDao) {
		this.userSessionDao = userSessionDao;
	}
	
    /**
     * Set the Dao for communication with the data layer.
     * @param dao the UserDao that communicates with the database
     */
    @Required
    public void setUserDao(UserDao dao) {
        this.dao = dao;
    }
}
