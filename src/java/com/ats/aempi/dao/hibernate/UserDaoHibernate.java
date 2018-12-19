package com.ats.aempi.dao.hibernate;

import java.util.List;

import javax.persistence.Table;

import com.ats.aempi.dao.UserDao;
import com.ats.aempi.model.AppUser;
import com.ats.aempi.model.UserFile;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *   Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 *   Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 *   Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with 
 *   the new BaseDaoHibernate implementation that uses generics.
*/
public class UserDaoHibernate extends GenericDaoHibernate<AppUser, Long> implements UserDao, UserDetailsService {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoHibernate() {
        super(AppUser.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<AppUser> getUsers() {
        return getHibernateTemplate().find("from AppUser u order by upper(u.username)");
    }

    /**
     * {@inheritDoc}
     */
    public AppUser saveUser(AppUser user) {
        log.debug("user's id: " + user.getId());
        getHibernateTemplate().saveOrUpdate(user);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getHibernateTemplate().flush();
        return user;
    }

    /**
     * Overridden simply to call the saveUser method. This is happening 
     * because saveUser flushes the session and saveObject of BaseDaoHibernate 
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public AppUser save(AppUser user) {
        return this.saveUser(user);
    }

    /** 
     * {@inheritDoc}
    */
    @SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> users = getHibernateTemplate().find("from AppUser where username=?", username);
        if (users == null || users.isEmpty()) {
            throw new UsernameNotFoundException("user '" + username + "' not found...");
        } else {
        	UserDetails userDetails = (UserDetails) users.get(0);
        	for (GrantedAuthority authority : userDetails.getAuthorities()) {
        		log.trace("User " + username + " has authority: " + authority.getAuthority());
        	}
            return userDetails; 
        }
    }

    /** 
     * {@inheritDoc}
    */
    public String getUserPassword(String username) {
        SimpleJdbcTemplate jdbcTemplate =
                new SimpleJdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        Table table = AnnotationUtils.findAnnotation(AppUser.class, Table.class);
        return jdbcTemplate.queryForObject(
                "select password from " + table.name() + " where username=?", String.class, username);

    }

    @SuppressWarnings("unchecked")
	public List<UserFile> getUserFiles(AppUser user) {
    	if (user == null || String.valueOf(user.getId()) == null) { //yrh在此我坑一个准备坑谁呢
    		return new java.util.ArrayList<UserFile>();
    	}
		List<UserFile> userFiles = getHibernateTemplate().find("from UserFile userFile where userFile.appUser.id = ? ", user.getId());
		return userFiles;
	}

    public UserFile getUserFile(Integer userFileId) {
    	if (userFileId == null) {
    		return null;
    	}
    	return (UserFile) getHibernateTemplate().get(UserFile.class, userFileId);
    }
    
	public UserFile saveUserFile(UserFile userFile) {
		getHibernateTemplate().merge(userFile);
        getHibernateTemplate().flush();
		return userFile;
	}
	
	public void removeUserFile(UserFile userFile) {
		if (userFile == null || userFile.getUserFileId() == null) {
			return;
		}
		UserFile theFile = (UserFile) getHibernateTemplate().get(UserFile.class, userFile.getUserFileId());
		getHibernateTemplate().delete(theFile);
		getHibernateTemplate().flush();
	}
}
