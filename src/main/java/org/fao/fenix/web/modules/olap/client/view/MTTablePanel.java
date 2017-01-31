package org.fao.fenix.web.modules.olap.client.view;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.HTML;

public class MTTablePanel extends MTStepPanel {
	
	private VerticalPanel wrapper;
	
	private ContentPanel contentPanel;
	
	private HTML html;
	
	private final static String WRAPPER_WIDTH = "640px";
	
	private final static String WRAPPER_HEIGHT = "360px";
	
	private final static String HTML_WIDTH = "600px";
	
	private final static String HTML_HEIGHT = "330px";
	
	private final static int SPACING = 5;

	public MTTablePanel(String suggestion, String width) {
		super(suggestion, width);
		contentPanel = new ContentPanel(new FillLayout());
		contentPanel.setHeaderVisible(false);
		contentPanel.setBodyBorder(false);
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FillLayout());
		wrapper.setSize(WRAPPER_WIDTH, WRAPPER_HEIGHT);
		wrapper.setScrollMode(Scroll.AUTO);
		html = new HTML("");
		html.setSize(HTML_WIDTH, HTML_HEIGHT);
		this.getLayoutContainer().add(buildContentPanel());
	}
	
	private ContentPanel buildContentPanel() {
		contentPanel.add(buildWrapperPanel());
		return contentPanel;
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.add(html);
		return wrapper;
	}
	
	public void setHtml(String olap) {
		html.setHTML(olap);
		wrapper.getLayout().layout();
	}

	public HTML getHtml() {
		return html;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}
	
}