package org.fao.fenix.web.modules.edi.client.view;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EDIHTTPSettingsPanel extends EDIPanel {

	private VerticalPanel panel;
	
	private TextField<String> baseUrl;
	
	public EDIHTTPSettingsPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		panel = new VerticalPanel();
		panel.setSpacing(getSPACING());
		baseUrl = new TextField<String>();
		baseUrl.setPassword(true);
		baseUrl.setEmptyText("Base URL");
	}
	
	public VerticalPanel buildHTTPSettingsPanel() {
		panel.add(buildBaseURL());
		return panel;
	}
	
	private VerticalPanel buildBaseURL() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Base URL</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		baseUrl.setWidth(getFIELD_WIDTH());
		p.add(baseUrl);
		return p;
	}

	public TextField<String> getBaseUrl() {
		return baseUrl;
	}
	
}
