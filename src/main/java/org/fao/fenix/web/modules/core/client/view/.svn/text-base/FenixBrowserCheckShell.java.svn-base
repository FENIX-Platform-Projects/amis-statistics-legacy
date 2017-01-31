package org.fao.fenix.web.modules.core.client.view;

import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FenixBrowserCheckShell {

	private Window window;

	public FenixBrowserCheckShell() {
		window = new Window();
		window.setModal(true);
		window.setHeading("Browser Detection");
		window.setSize("300px", "100px");
		DOM.setStyleAttribute(window.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);
	}

	public void build() {
		String browser = getBrowserName();
		if (!browser.equals("Netscape")) {
			HTML label = new HTML("This application has been designed to work with Mozilla Firefox. You can download it by clicking <a href='http://www.mozilla.com/en-US/firefox/'>here</a>.");
			VerticalPanel panel = new VerticalPanel();
			panel.setSpacing(10);
			panel.add(label);
			window.add(panel);
			window.show();
		}
	}

	public static native String getBrowserName() /*-{
	  return navigator.appName;
	}-*/;
	
}
