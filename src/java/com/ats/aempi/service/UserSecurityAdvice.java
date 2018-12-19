package com.ats.aempi.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.EMPIConstants;
import com.ats.aempi.model.Role;
import com.ats.aempi.model.AppUser;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationTrustResolver;
import org.springframework.security.AuthenticationTrustResolverImpl;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.UserDetails;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * This advice is responsible for enforcing security and only allowing administrators
 * to modify users. Users are allowed to modify themselves.
 *
 * @author mraible
 */
public class UserSecurityAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    /**
     * Default "Access Denied" error message (not i18n-ized).
     */
    public static final String ACCESS_DENIED = "Access Denied: Only administrators are allowed to modify other users.";
    private final Log log = LogFactory.getLog(UserSecurityAdvice.class);

    /**
     * Method to enforce security and only allow administrators to modify users. Regular
     * users are allowed to modify themselves.
     *
     * @param method the name of the method executed
     * @param args the arguments to the method
     * @param target the target class
     * @throws Throwable thrown when args[0] is null or not a User object
     */
    public void before(Method method, Object[] args, Object target) throws Throwable {
        SecurityContext ctx = SecurityContextHolder.getContext();

        if (ctx.getAuthentication() != null) {
            Authentication auth = ctx.getAuthentication();
            boolean administrator = false;
            GrantedAuthority[] roles = auth.getAuthorities();
            for (GrantedAuthority role1 : roles) {
                if (role1.getAuthority().equals(EMPIConstants.ADMIN_ROLE)) {
                    administrator = true;
                    break;
                }
            }

            AppUser user = (AppUser) args[0];

            AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
            // allow new users to signup - this is OK b/c Signup doesn't allow setting of roles
            boolean signupUser = resolver.isAnonymous(auth);

            if (!signupUser) {
                AppUser currentUser = getCurrentUser(auth);

                if (String.valueOf(user.getId()) != null && !String.valueOf(user.getId()).equals(String.valueOf(currentUser.getId())) && !administrator) {
                    log.warn("Access Denied: '" + currentUser.getUsername() + "' tried to modify '" + user.getUsername() + "'!");
                    throw new AccessDeniedException(ACCESS_DENIED);
                } else if (String.valueOf(user.getId()) != null && String.valueOf(user.getId()).equals(String.valueOf(currentUser.getId())) && !administrator) {
                    // get the list of roles the user is trying add
                    Set<String> userRoles = new HashSet<String>();
                    if (user.getRoles() != null) {
                        for (Object o : user.getRoles()) {
                            Role role = (Role) o;
                            userRoles.add(role.getName());
                        }
                    }

                    // get the list of roles the user currently has
                    Set<String> authorizedRoles = new HashSet<String>();
                    for (GrantedAuthority role : roles) {
                        authorizedRoles.add(role.getAuthority());
                    }

                    // if they don't match - access denied
                    // regular users aren't allowed to change their roles
                    if (!CollectionUtils.isEqualCollection(userRoles, authorizedRoles)) {
                        log.warn("Access Denied: '" + currentUser.getUsername() + "' tried to change their role(s)!");
                        throw new AccessDeniedException(ACCESS_DENIED);
                    }
                }
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("Registering new user '" + user.getUsername() + "'");
                }
            }
        }
    }

    /**
     * After returning, grab the user, check if they've been modified and reset the SecurityContext if they have.
     * @param returnValue the user object
     * @param method the name of the method executed
     * @param args the arguments to the method
     * @param target the target class
     * @throws Throwable thrown when args[0] is null or not a User object
     */
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
    throws Throwable {
        AppUser user = (AppUser) args[0];

        if (user.getVersion() != null) {
            // reset the authentication object if current user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
            // allow new users to signup - this is OK b/c Signup doesn't allow setting of roles
            boolean signupUser = resolver.isAnonymous(auth);
            if (auth != null && !signupUser) {
                AppUser currentUser = getCurrentUser(auth);
                if (String.valueOf(currentUser.getId()).equals(String.valueOf(user.getId()))) {
                    auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
    }

    private AppUser getCurrentUser(Authentication auth) {
        AppUser currentUser;
        if (auth.getPrincipal() instanceof UserDetails) {
            currentUser = (AppUser) auth.getPrincipal();
        } else if (auth.getDetails() instanceof UserDetails) {
            currentUser = (AppUser) auth.getDetails();
        } else {
            throw new AccessDeniedException("User not properly authenticated.");
        }
        return currentUser;
    }
}
