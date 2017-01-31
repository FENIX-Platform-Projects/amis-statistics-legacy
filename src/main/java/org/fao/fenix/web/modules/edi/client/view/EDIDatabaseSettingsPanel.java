package org.fao.fenix.web.modules.edi.client.view;

import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EDIDatabaseSettingsPanel extends EDIPanel {

	private VerticalPanel panel;
	
	private ListBox databaseDriver;
	
	private ListBox datasourceList;
	
	private TextField<String> dbUrl;
	
	private TextField<String> dbServerName;
	
	private TextField<String> dbPortNumber;
	
	private TextField<String> dbName;
	
	private TextField<String> dbUsername;
	
	private TextField<String> dbPassword;
	
	public EDIDatabaseSettingsPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		panel = new VerticalPanel();
		panel.setSpacing(getSPACING());
		databaseDriver = new ListBox();
		databaseDriver.addItem("Please Select a Driver...");
		databaseDriver.addItem(EDISettings.POSTGRESQL.name(), "org.postgresql.Driver");
		databaseDriver.addItem(EDISettings.MYSQL.name(), "com.mysql.jdbc.Driver");
		databaseDriver.addItem(EDISettings.SQLSERVER2000.name(), "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		datasourceList = new ListBox();
		datasourceList.addItem("Please Select a Datasource...");
		datasourceList.addItem(EDISettings.WORKSTATION.name(), EDISettings.WORKSTATION.name());
		datasourceList.addItem(EDISettings.FAOSTAT.name(), EDISettings.FAOSTAT.name());
		datasourceList.addItem(EDISettings.ESOKO.name(), EDISettings.ESOKO.name());
		dbUrl = new TextField<String>();
		dbUrl.setPassword(true);
		dbUrl.setEmptyText("Database URL");
		dbServerName = new TextField<String>();
		dbServerName.setPassword(true);
		dbServerName.setEmptyText("Database Server Name");
		dbPortNumber = new TextField<String>();
		dbPortNumber.setPassword(true);
		dbPortNumber.setEmptyText("Database Port Number");
		dbName = new TextField<String>();
		dbName.setPassword(true);
		dbName.setEmptyText("Database Name");
		dbUsername = new TextField<String>();
		dbUsername.setPassword(true);
		dbUsername.setEmptyText("Database Username");
		dbPassword = new TextField<String>();
		dbPassword.setPassword(true);
		dbPassword.setEmptyText("Database Password");
	}
	
	public VerticalPanel buildDatabaseSettingsPanel() {
		panel.add(buildDatabaseDriver());
		panel.add(buildDatasourcePanel());
		panel.add(buildDBURL());
		panel.add(buildDBServerName());
		panel.add(buildDBPortNumber());
		panel.add(buildDBName());
		panel.add(buildDBUsername());
		panel.add(buildDBPassword());
		return panel;
	}
	
	private VerticalPanel buildDatabaseDriver() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Database Driver</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		databaseDriver.setWidth(getFIELD_WIDTH());
		p.add(databaseDriver);
		return p;
	}
	
	private VerticalPanel buildDatasourcePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Data Source</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		datasourceList.setWidth(getFIELD_WIDTH());
		p.add(datasourceList);
		return p;
	}
	
	private VerticalPanel buildDBURL() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Database URL</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		dbUrl.setWidth(getFIELD_WIDTH());
		p.add(dbUrl);
		return p;
	}
	
	private VerticalPanel buildDBServerName() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Database Server Name</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		dbServerName.setWidth(getFIELD_WIDTH());
		p.add(dbServerName);
		return p;
	}
	
	private VerticalPanel buildDBPortNumber() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Database Port Number</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		dbPortNumber.setWidth(getFIELD_WIDTH());
		p.add(dbPortNumber);
		return p;
	}
	
	private VerticalPanel buildDBName() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Database Name</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		dbName.setWidth(getFIELD_WIDTH());
		p.add(dbName);
		return p;
	}
	
	private VerticalPanel buildDBUsername() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Database Username</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		dbUsername.setWidth(getFIELD_WIDTH());
		p.add(dbUsername);
		return p;
	}
	
	private VerticalPanel buildDBPassword() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Database Password</b>");
		l.setWidth(getLABEL_WIDTH());
		p.add(l);
		dbPassword.setWidth(getFIELD_WIDTH());
		p.add(dbPassword);
		return p;
	}

	public ListBox getDatasourceList() {
		return datasourceList;
	}

	public TextField<String> getDbUrl() {
		return dbUrl;
	}

	public TextField<String> getDbServerName() {
		return dbServerName;
	}

	public TextField<String> getDbPortNumber() {
		return dbPortNumber;
	}

	public TextField<String> getDbName() {
		return dbName;
	}

	public TextField<String> getDbUsername() {
		return dbUsername;
	}

	public TextField<String> getDbPassword() {
		return dbPassword;
	}

	public ListBox getDatabaseDriver() {
		return databaseDriver;
	}
	
}