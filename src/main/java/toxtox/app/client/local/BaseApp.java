package toxtox.app.client.local;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;

import com.google.gwt.user.client.ui.RootPanel;

@EntryPoint
public class BaseApp {

	@Inject
	private Navigation navigation;
 
	@PostConstruct
	public void clientMain() {
		RootPanel.get().add(navigation.getContentPanel());
	}

}
