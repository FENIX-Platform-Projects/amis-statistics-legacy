package org.fao.fenix.web.modules.adam.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface ADAMResources extends ClientBundle {
	public static final ADAMResources INSTANCE = GWT.create(ADAMResources.class);
	
	@Source("adam-home.html")
	public TextResource getHomeHtml();

}
