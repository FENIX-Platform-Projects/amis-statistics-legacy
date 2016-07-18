package org.fao.fenix.web.modules.edi.client.view;

import org.fao.fenix.web.modules.edi.client.control.EDIController;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;

public class EDISettingsPanel extends EDIPanel {

	private ListBox connectionTypeList;
	
	private EDIDatabaseSettingsPanel databaseSettingsPanel;
	
	private EDIHTTPSettingsPanel httpSettingsPanel;
	
	public EDISettingsPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		databaseSettingsPanel = new EDIDatabaseSettingsPanel("", "");
		httpSettingsPanel = new EDIHTTPSettingsPanel("", "");
		connectionTypeList = new ListBox();
		connectionTypeList.addItem("Please Select a Connection Type...");
		connectionTypeList.addItem(EDISettings.JDBC.name(), EDISettings.JDBC.name());
		connectionTypeList.addItem(EDISettings.HTTP.name(), EDISettings.HTTP.name());
		connectionTypeList.addItem(EDISettings.WEBSERVICE.name(), EDISettings.WEBSERVICE.name());
		connectionTypeList.addChangeHandler(EDIController.connectionType(this));
	}
	
	@Override
	public TabItem build() {
		this.getPanel().setTopComponent(buildConnectionTypePanel());
		return this.getTabItem();
	}
	
	private VerticalPanel buildConnectionTypePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Connection Type</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		connectionTypeList.setWidth(getFIELD_WIDTH());
		p.add(connectionTypeList);
		return p;
	}
	
	public ListBox getConnectionTypeList() {
		return connectionTypeList;
	}

	public EDIDatabaseSettingsPanel getDatabaseSettingsPanel() {
		return databaseSettingsPanel;
	}

	public EDIHTTPSettingsPanel getHttpSettingsPanel() {
		return httpSettingsPanel;
	}
	
}