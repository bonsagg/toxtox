/*
 * Copyright 2009 JBoss, a division of Red Hat Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package toxtox.app.client.local;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Main application entry point.
 */
/*
 * The '#template' value tells Errai UI to use the specified Element from the
 * HTML markup file (with data-field="template") as the root of this Composite
 * component.
 */
@Templated("#template")
@EntryPoint
public class App extends Composite {
	/*
	 * Inject any Widget with a default constructor, bind to data-field
	 * "sendMessage".
	 */
	@Inject
	@DataField
	private Button sendMessage;

	/*
	 * Or create it yourself, and bind to data-field "messageText".
	 */
	@DataField
	private TextBox messageText = new TextBox();

	@DataField
	private HTMLPanel spotlights = new HTMLPanel("");
	
	@DataField
	private TextBox username = new TextBox();
	
	@DataField
	private TextBox email = new TextBox();

	/*
	 * Allows us to instantiate new Spotlight instances.
	 */
	@Inject
	Instance<Spotlight> spotlightInstance;

	@PostConstruct
	public void setup() {
		sendMessage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				createSpotlight();
			}
		});

		RootPanel.get().add(this);
	}

	private void createSpotlight() {
		Spotlight spotlight = spotlightInstance.get();
		String name = username.getText();
		String eml = email.getText();
		spotlight.setTitle((name == null ? "Anonymous" : name));
		spotlight.setContent((eml == null ? "" : "[ " + eml + " ]") + " "
				+ messageText.getText());
		spotlights.add(spotlight);
	}

}
