package org.fao.fenix.web.modules.dataviewer.common.vo;

import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataViewerDBVO implements IsSerializable {


	private String dbUrl = "";

	private String dbServerName = "";

	private String dbPortNumber = "";

	private String dbName = "";

	private String dbUserName = "";

	private String dbPassword = "";
	
	private String groupCode;
	
	private String groupLabel;
	
	private String dbDriver;
	
	private String datasource;
	
	private String language;
	
	private String connectionString;
	
	private String connectionDriver;
	
	
	
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupLabel() {
		return groupLabel;
	}

	public void setGroupLabel(String groupLabel) {
		this.groupLabel = groupLabel;
	}
	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public String getConnectionDriver() {
		return connectionDriver;
	}

	public void setConnectionDriver(String connectionDriver) {
		this.connectionDriver = connectionDriver;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}


	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	
}