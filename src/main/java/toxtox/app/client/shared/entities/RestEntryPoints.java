package toxtox.app.client.shared.entities;

/**
 * This class is used to populate the root resources to the service consumers.
 * 
 * @author chris
 * 
 */
public class RestEntryPoints {

	// Is replaced with the real application context
	private static final String CONTEXT_ROOT = "http://127.0.0.1:8080/rest/api/";
	private final String users = CONTEXT_ROOT + "users";
	private final String games = CONTEXT_ROOT + "games";
	private final String messages = CONTEXT_ROOT + "messages";
	// Is replaced with the real logged in user id
	private final String me = CONTEXT_ROOT + users +"/4738279";

	public String getUsers() {
		return users;
	}

	public String getMe() {
		return me;
	}

	public String getGames() {
		return games;
	}

	public String getMessages() {
		return messages;
	}

}
