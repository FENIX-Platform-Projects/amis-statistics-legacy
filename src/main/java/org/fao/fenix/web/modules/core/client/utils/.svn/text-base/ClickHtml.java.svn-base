package org.fao.fenix.web.modules.core.client.utils;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;

/*
 * This class extends EXT GWT Html class, to allow the Html widget to be clickable 
 */
public class ClickHtml extends Html {
	
	public void onRender(Element target, int index) {
		super.onRender(target, index);
		sinkEvents(Event.ONCLICK);
	}
	
	
	public void attach() {
		onAttach();
		RootPanel.detachOnWindowClose(this);
	}
}
