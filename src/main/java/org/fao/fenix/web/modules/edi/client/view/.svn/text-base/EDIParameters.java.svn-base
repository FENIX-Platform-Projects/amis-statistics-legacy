package org.fao.fenix.web.modules.edi.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

public abstract class EDIParameters {

	private List<EDIFilter> filters = new ArrayList<EDIFilter>();
	
	private EDISettings connectionType = EDISettings.JDBC;
	
	private EDISettings datasource = EDISettings.WORKSTATION;
	
	private String dbUrl = "";
	
	private String dbServerName = "";
	
	private String dbUsername = "";
	
	private String dbPassword = "";
	
	private String dbPortNumber = "";
	
	private String dbName = "";

	public void addFilter(EDIFilter f) {
		if (this.filters == null)
			this.filters = new ArrayList<EDIFilter>();
		this.filters.add(f);
	}
	
	public List<EDIFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<EDIFilter> filters) {
		this.filters = filters;
	}

	public EDISettings getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(EDISettings connectionType) {
		this.connectionType = connectionType;
	}

	public EDISettings getDatasource() {
		return datasource;
	}

	public void setDatasource(EDISettings datasource) {
		this.datasource = datasource;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbServerName() {
		return dbServerName;
	}

	public void setDbServerName(String dbServerName) {
		this.dbServerName = dbServerName;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbPortNumber() {
		return dbPortNumber;
	}

	public void setDbPortNumber(String dbPortNumber) {
		this.dbPortNumber = dbPortNumber;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
}