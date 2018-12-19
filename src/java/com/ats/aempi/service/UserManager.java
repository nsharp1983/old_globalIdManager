package com.ats.aempi.service;

import java.util.List;

import org.springframework.security.userdetails.UsernameNotFoundException;
import com.ats.aempi.AuthenticationException;
import com.ats.aempi.dao.UserDao;
import com.ats.aempi.model.AppUser;
import com.ats.aempi.model.UserFile;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *  Modified by <a href="mailto:dan@getrolling.com">Dan Kibler </a> 
 */
public interface UserManager extends UniversalManager {

    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param userDao the UserDao implementation to use
     */
    void setUserDao(UserDao userDao);

    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    AppUser getUser(String userId);

    /**
     * Authentication method. Currently the only authentication method available is using
     * username/password pair credentials. In the future we need to add support for certificate
     * based authentication.
     */
    public AppUser authenticate(String username, String password) throws AuthenticationException;
    
    /**
     * Authentication method using a session Key. This authentication method is used
     * by the EJB layer for the purpose of being able to maintain a conversation with a service
     * after successful authentication using another method.
     * 
     * @param sessionKey Obtained after successful authentication and used by EJB layer methods 
     * to have a "stateful-like" interaction regarding authentication with the stateless services
     * 
     * @return If the session key is recognized and the authentication attempt is successful, the
     * User object of the authenticated user is returned.
     * 
     * @throws AuthenticationException
     */
    public AppUser authenticate(String sessionKey) throws AuthenticationException;
    
    /**
     * Creates a new session usually as a result of a successful authentication event. End users
     * can utilize the sessionId for subsequent requests.
     */
    String createSession(AppUser user);
    
    /**
    /**
     * Finds a user by their username.
     * @param username the user's username used to login
     * @return User a populated user object
     * @throws org.springframework.security.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    AppUser getUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Retrieves a list of users, filtering with parameters on a user object
     * @param user parameters to filter on
     * @return List
     */
    List<AppUser> getUsers(AppUser user);

    /**
     * Saves a user's information.
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return user the updated user object
     */
    AppUser saveUser(AppUser user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);
    
    /**
     * Retrieves the list of files that are associated with a user.
     * @param user parameter to filter on
     * @return List
     */
    public List<UserFile> getUserFiles(AppUser user);
    
    /**
     * Retrieves a specific file entry using the entry's primary key
     * @param unique identifier for user file entry
     * @return UserFile
     */
    public UserFile getUserFile(Integer userFileId);
    
    /**
     * Save a user file entry
     * 
     * @param userFile a populated user file entry
     * @return userFile the stored user file object with identifying information
     */
    public UserFile saveUserFile(UserFile userFile);
    
    /**
     * Removes a user entry from the database by its id
     *
     * @param user file entry
     */
    void removeUserFile(UserFile userFile);
}
