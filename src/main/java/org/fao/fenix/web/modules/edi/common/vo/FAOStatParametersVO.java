package org.fao.fenix.web.modules.edi.common.vo;

import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FAOStatParametersVO implements IsSerializable {

	private String dbUrl = "";

	private String dbServerName = "";

	private String dbPortNumber = "";

	private String dbName = "";

	private String dbUserName = "";

	private String dbPassword = "";
	
	private String groupCode;
	
	private String groupLabel;
	
	private Map<String, String> domains;
	
	private Map<String, String> areas;
	
	private Map<String, String> years;
	
	private Map<String, String> items;
	
	private Map<String, String> elements;
	
	private String dbDriver;
	
	private String datasource;
	
	private String language;
	
	private String connectionString;
	
	private String connectionDriver;
	
	private String sql;
	
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

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
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

	public Map<String, String> getDomains() {
		return domains;
	}

	public void setDomains(Map<String, String> domains) {
		this.domains = domains;
	}

	public Map<String, String> getAreas() {
		return areas;
	}

	public void setAreas(Map<String, String> areas) {
		this.areas = areas;
	}

	public Map<String, String> getYears() {
		return years;
	}

	public void setYears(Map<String, String> years) {
		this.years = years;
	}

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

	public Map<String, String> getElements() {
		return elements;
	}

	public void setElements(Map<String, String> elements) {
		this.elements = elements;
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