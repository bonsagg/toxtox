package toxtox.app.client.local;

import javax.annotation.PostConstruct;

import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.user.client.ui.Composite;

@Templated("Header.html")
public class Header extends Composite{

	@PostConstruct
	public void init(){
	}
}
