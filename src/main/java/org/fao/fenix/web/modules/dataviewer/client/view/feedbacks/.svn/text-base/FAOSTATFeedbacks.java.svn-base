package org.fao.fenix.web.modules.dataviewer.client.view.feedbacks;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Window;
import com.gargoylesoftware.htmlunit.javascript.host.Form;
import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATFeedbacks {
	
	ContentPanel panel;
	
	static ContentPanel form;
	

	public FAOSTATFeedbacks() {

	} 
	
	public ContentPanel build() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		form = new ContentPanel();
		form.setBodyBorder(false);
		form.setHeaderVisible(false);
		form.setVisible(false);

		buildLink(form);
		
		return panel;
	}
	
	private static void buildForm() {
		
		form.removeAll();
		
		Html html = new Html();

		html.setHtml("[+] " + FAOSTATMainController.currentState.currentView);
		
		form.add(html);
		
		RootPanel.get("FEEDBACK_FORM").add(form);
	}
	
	private ClickHtml buildLink(ContentPanel panel) {
		
		
		ClickHtml html = new ClickHtml();
		html.setHtml("<A HREF='mailto:kafkas.caprazli@fao.org;fabio.grita@fao.org?subject=Feedback new FAOSTAT'>[+] " + FAOSTATLanguage.print().feedback() + "</A>");
		
/** 	TODO: this is for the form **/		
//		html.setHtml("[+] " + FAOSTATLanguage.print().feedback());
//		html.addListener(Events.OnClick, showHideForm(panel));
		
		RootPanel.get("FEEDBACK").add(html);

		return html;
	}
	
	public static Listener<ComponentEvent> showHideForm(final ContentPanel panel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				buildForm();
				panel.show();
//				buildFormWindow();
			}
		};
	}
	
	private static void buildFormWindow() {
		System.out.println("buildFormWindow()");
		Window window = new Window();
		ContentPanel panel = new ContentPanel();
		
		parseParams(getParamString());
		
		window.add(panel);
		
		window.show();
	}
	
	private static void parseParams(String params) {
		System.out.println("PARAMS: " + params);
	}
	
	private static native String getParamString() /*-{
		return $wnd.location.search;
	}-*/;
	
	

}
