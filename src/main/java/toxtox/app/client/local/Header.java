package toxtox.app.client.local;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;

@Templated("HeaderTemplate.html")
public class Header extends Composite{
	
	@Inject
	@DataField
	private Anchor index;
	
	@Inject
	@DataField
	private Anchor about;
	
	@Inject
	private TransitionTo<IndexPage> indexPage;
	
	@Inject
	private TransitionTo<AboutPage> aboutPage;

	@PostConstruct
	public void init(){
		
		ClickHandler indexClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				GWT.log("clicked");
				indexPage.go();	
			}
		};
		
		ClickHandler aboutClickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				GWT.log("clicked");
				aboutPage.go();
			}
		};
	
		index.addClickHandler(indexClickHandler);
		about.addClickHandler(aboutClickHandler);
	}
}
