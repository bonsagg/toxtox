package toxtox.app.server.services.impl;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import toxtox.app.client.shared.entities.User;
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
