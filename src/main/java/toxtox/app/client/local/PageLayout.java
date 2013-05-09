package toxtox.app.client.local;

import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.user.client.ui.Composite;

@Templated
public class PageLayout extends Composite{
	
	@Inject
	@DataField
	private Header header;
	
	@Inject
	@DataField
	private Footer footer;

}
