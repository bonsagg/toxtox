package toxtox.app.server.services.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.slf4j.Logger;

import toxtox.app.client.shared.entities.User;
import toxtox.app.client.shared.qualifier.UserLogin;
import toxtox.app.server.services.api.UserService;

/**
 * Implementation of {@link UserService}
 * 
 * @author Michael Goldmann
 *
 */
@RequestScoped
@Stateful
public class DBUserService implements UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Logger logger;
	
	@PostConstruct
	public void init() {

	    //init Shiro
	    Factory<SecurityManager> factory = new IniSecurityManagerFactory("src/main/resources/shiro.ini");
	    SecurityManager securityManager = factory.getInstance();
	    SecurityUtils.setSecurityManager(securityManager);
	    
	}

	/*
	 * (non-Javadoc)
	 * @see toxtox.app.server.services.api.UserService#findUserByUserName(java.lang.String)
	 */
	@Override
	public User findUserByUserName(String userName) {

		try {
			return em.createNamedQuery(User.FIND_USER_BY_USERNAME, User.class)
					.setParameter("userName", userName).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see toxtox.app.server.services.api.UserService#saveUser(toxtox.app.client.shared.entitys.User)
	 */
	@Override
	public void saveUser(User user) {
		if(user.getId() == null){
			em.persist(user);
			logger.info("persist user: " + user.getUserName());
		}else{
			em.merge(user);
			logger.info("merged user: " + user.getUserName());
		}
	}
	
	@Override
	public void loginUser(@Observes @UserLogin User user){
		
		Subject currentUser = SecurityUtils.getSubject();
		if ( !currentUser.isAuthenticated() ) {
		    //collect user principals and credentials in a gui specific manner 
		    //such as username/password html form, X509 certificate, OpenID, etc.
		    //We'll use the username/password example here since it is the most common.
		    //(do you know what movie this is from? ;)
		    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		    //this is all you have to do to support 'remember me' (no config - built in!):
		    token.setRememberMe(true);
		    try {
		        currentUser.login(token);
		        //if no exception, that's it, we're done!
		    } catch ( UnknownAccountException uae ) {
		    	System.out.println("User not found!");
		    	throw uae;
		    } catch ( IncorrectCredentialsException ice ) {
		    	System.out.println("PW wrong!");
		    	throw ice;
		    } catch ( LockedAccountException lae ) {
		        System.out.println("Locked account!");
		    } catch ( AuthenticationException ae ) {
		    	System.out.println("Some error!");
		    }
		}
		
	}
	
	@Override
	public void logout() {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see toxtox.app.server.services.api.UserService#deleteUser(toxtox.app.client.shared.entitys.User)
	 */
	@Override
	public void deleteUser(User user) {
		User rm = em.find(User.class, user.getId());
		em.remove(rm);
		logger.info("removed user: " + user.getUserName());
	}

	/*
	 * (non-Javadoc)
	 * @see toxtox.app.server.services.api.UserService#existsUsername(java.lang.String)
	 */
	@Override
	public boolean existsUsername(String username) {
		User user = findUserByUserName(username);
		return user == null ? false : true;
	}

}
