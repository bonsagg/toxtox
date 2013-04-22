package toxtox.app.server.services.api;

import toxtox.app.client.shared.entities.User;


/**
 * Service for user handling.
 * 
 * @author Michael Goldmann
 * 
 */
public interface UserService {

	/**
	 * Check if username already exists.
	 * 
	 * @param username
	 * @return
	 */
	public boolean existsUsername(String username);

	/**
	 * Find a user by username.
	 * 
	 * @param userName
	 * @return {@link User}
	 */
	public User findUserByUserName(String userName);

	/**
	 * Persist a user.
	 * 
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * Delete a user.
	 * 
	 * @param user
	 */
	public void deleteUser(User user);

	public void loginUser(User user);

	public void logout();
	
}
