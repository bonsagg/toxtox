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
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.user.client.ui.Composite;

/**
 * Main application entry point.
 */
/*
 * The '#template' value tells Errai UI to use the specified Element from the
 * HTML markup file (with data-field="template") as the root of this Composite
 * component.
 */
@Templated
@Page(startingPage=true)
public class App extends Composite {

	@Inject
	@DataField
	private Header header;
	
	@Inject
	@DataField
	private Content content;
	
	@Inject
	@DataField
	private Footer footer;
	
	@PostConstruct
	public void setup() {
		
	}
}
