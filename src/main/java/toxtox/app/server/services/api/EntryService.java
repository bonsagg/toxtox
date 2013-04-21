package toxtox.app.server.services.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import toxtox.app.client.shared.entities.RestEntryPoints;

/**
 * All service consumers initially call this service to get a fresh list of
 * possible entry points.
 * 
 * Later on, we should produce and consume our own versioned mime type like
 * 'application/vnd.org.toxtox-v1+json'
 * 
 * @author chris
 * 
 */
@Path("/api")
@Produces("application/json")
public interface EntryService {

	/**
	 * 
	 * @return all Entry Points
	 */
	@GET
	public RestEntryPoints getEntrypoint();
}
