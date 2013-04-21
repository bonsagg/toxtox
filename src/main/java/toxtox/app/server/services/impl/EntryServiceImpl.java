package toxtox.app.server.services.impl;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

import toxtox.app.client.shared.entities.RestEntryPoints;
import toxtox.app.server.services.api.EntryService;

@Stateless
public class EntryServiceImpl implements EntryService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see toxtox.app.server.services.api.EntryService#getEntrypoint()
	 */
	@Override
	public RestEntryPoints getEntrypoint() {
		return new RestEntryPoints();
	}

}
