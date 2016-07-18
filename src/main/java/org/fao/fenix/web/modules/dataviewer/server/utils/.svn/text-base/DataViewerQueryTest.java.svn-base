package org.fao.fenix.web.modules.dataviewer.server.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.core.domain.faostat.FAOStatParameters;
import org.fao.fenix.core.exception.FenixException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DataViewerQueryTest {
	
	public static void testDB() {
		String original = "increase of 25% in the rice";
		System.out.println(original);
		original = original.replace("%", "\\%");
		
		FAOStatParameters p = getFAOSTATParameters();
		
		List<List<String>> dataset = queryResult(p, false);
		
		System.out.println("dataset");
		for(List<String> d : dataset) {
			System.out.println(d);
		}
	}
	
	private static FAOStatParameters getFAOSTATParameters() {
		FAOStatParameters p = new FAOStatParameters();
		
		setFAOSTATDBSettings(p);
		
//		p.setConnectionString(createSQLServer2000ConnectionString(p));
		
		p.setConnectionString("jdbc:sqlserver://FAOSTAT-GLBL\\Dissemination;databaseName=Warehouse;");
		
		
		
		setFAOSTATParameters(p);
		
//		p.setSql(sql(p));
//		p.setSql(sqlWithLabels(p));
		p.setSql(sqlChart(p));


		
		System.out.println("connection: " + p.getConnectionString());
		return p;
	}
	
	private static void setFAOSTATParameters(FAOStatParameters p){
		
		p.setAreas(getAreas());
		p.setElements(getElements());
		p.setDomains(getDomains());
		p.setItems(getItems());
		p.setYears(getYears());
		
		p.setLanguage("E");
	}
//	
	private static Map<String, String> getAreas() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("5100", "5100");
		return map;
	}
	
	private static Map<String, String> getItems() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("221", "221");
		map.put("800", "800");
		map.put("711", "711");
		return map;
	}
	
	private static Map<String, String> getElements() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("5312", "5312");
		map.put("5510", "5510");
		return map;
	}
	
	private static Map<String, String> getDomains() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("QC", "QC");
		return map;
	}
	
	private static Map<String, String> getYears() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1967", "1967");
		map.put("1966", "1966");
		map.put("1965", "1965");
		map.put("1964", "1964");
		return map;
	}
	
	private static void setFAOSTATDBSettings(FAOStatParameters p) {
		p.setDbDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		p.setConnectionDriver("JDBC");
		p.setDatasource("FAOSTAT");
		
		p.setDbUrl("jdbc:sqlserver://");
		p.setDbServerName("FAOSTAT-GLBL\\Dissemination");
		p.setDbName("Warehouse");
		p.setDbUserName("Warehouse");
		p.setDbPassword("w@reh0use");
		
//		for (int i = 0 ; i < this.getConnectionTypeList().getItemCount() ; i++)
//			if (this.getConnectionTypeList().getValue(i).equals(EDISettings.JDBC.name()))
//				this.getConnectionTypeList().setSelectedIndex(i);
//		EDIController.connectionTypeAgent(this);
//		for (int i = 0 ; i < this.getDatabaseSettingsPanel().getDatabaseDriver().getItemCount() ; i++) {
//			if (this.getDatabaseSettingsPanel().getDatabaseDriver().getItemText(i).equals(EDISettings.SQLSERVER2000.name()))
//				this.getDatabaseSettingsPanel().getDatabaseDriver().setSelectedIndex(i);
//		}
//		for (int i = 0 ; i < this.getDatabaseSettingsPanel().getDatasourceList().getItemCount() ; i++)
//			if (this.getDatabaseSettingsPanel().getDatasourceList().getValue(i).equals(EDISettings.FAOSTAT.name()))
//				this.getDatabaseSettingsPanel().getDatasourceList().setSelectedIndex(i);
//		this.getDatabaseSettingsPanel().getDbUrl().setValue("jdbc:sqlserver://");
//		this.getDatabaseSettingsPanel().getDbServerName().setValue("FAOSTAT-GLBL\\Dissemination");
//		this.getDatabaseSettingsPanel().getDbName().setValue("Warehouse");
//		this.getDatabaseSettingsPanel().getDbUsername().setValue("Warehouse");
//		this.getDatabaseSettingsPanel().getDbPassword().setValue("w@reh0use");
		
	}
	
	private String createSQLServer2000ConnectionString(FAOStatParameters p) {
		StringBuilder sb = new StringBuilder();
		sb.append(p.getDbUrl());
		sb.append(p.getDbServerName());
		sb.append(";");
		sb.append("databaseName=");
		sb.append(p.getDbName());
		sb.append(";");
		return sb.toString();
	}
	
	public static List<List<String>> queryResult(FAOStatParameters p, boolean showLabels) throws FenixException {
		List<List<String>> ds = new ArrayList<List<String>>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(p.getConnectionString(), p.getDbUserName(), p.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(p.getSql());
			ResultSet rs = stmt.getResultSet();
			System.out.println("size size: " + rs.getFetchSize());
//			System.out.println("size : " + rs.getRow();
			System.out.println("size row: " + rs.getRow());
			
			while (rs.next()) {
				System.out.println("-> " + rs.getRow() + " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7)+ " " + rs.getString(8) + " " + rs.getString(9));
				
//				for (int i = 1 ; i <= 5 ; i++) {
//					String cell = "";
//					if (rs.getString(i) != null) {
//							cell = rs.getString(i).trim();
//					} else { 
//							cell = "?";
//					}
//					System.out.println("cell: " + cell);
//				}
			}

//			while (rs.next()) {
//			
//				
//				List<String> row = new ArrayList<String>();
//				int limit = 7;
//				if (showLabels)
//					limit = 10;
//				for (int i = 1 ; i <= limit ; i++) {
//					String cell = "";
//					if (rs.getString(i) != null) {
//						if (wrap)
//							cell = "\"" + rs.getString(i).trim() + "\"";
//						else
//							cell = rs.getString(i).trim();
//					} else { 
//						if (wrap)
//							cell = "\"?\"";
//						else
//							cell = "?";
//					}
//					row.add(cell);
//				}
//				ds.add(row);
		
//			}
			stmt.close();
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
	
		return ds;
	}
	
//	public List<List<String>> importFAOStatDataset(FAOStatParameters p, boolean wrap, boolean showLabels) throws FenixException {
//		List<List<String>> ds = new ArrayList<List<String>>();
//		try {
//			SQLServerDriver.class.newInstance();
//			Connection c = DriverManager.getConnection(p.getConnectionString(), p.getDbUserName(), p.getDbPassword());
//			Statement stmt = c.createStatement();
//			stmt.executeQuery(p.getSql());
//			ResultSet rs = stmt.getResultSet();
//			while (rs.next()) {
//				List<String> row = new ArrayList<String>();
//				int limit = 7;
//				if (showLabels)
//					limit = 10;
//				for (int i = 1 ; i <= limit ; i++) {
//					String cell = "";
//					if (rs.getString(i) != null) {
//						if (wrap)
//							cell = "\"" + rs.getString(i).trim() + "\"";
//						else
//							cell = rs.getString(i).trim();
//					} else { 
//						if (wrap)
//							cell = "\"?\"";
//						else
//							cell = "?";
//					}
//					row.add(cell);
//				}
//				ds.add(row);
//			}
//			
//		} catch (SQLException e) {
//			throw new FenixException(e.getMessage());
//		} catch (IllegalAccessException e) {
//			throw new FenixException(e.getMessage());
//		} catch (InstantiationException e) {
//			throw new FenixException(e.getMessage());
//		} 
//		return ds;
//	}
	
	
	private String sql(FAOStatParameters p) {
		String sql = "SELECT D.Year, D.AreaCode, D.ItemCode, D.ElementCode, D.Value, F.FlagDescription" + p.getLanguage() + ", E.UnitName" + p.getLanguage() + " " +
					 "FROM Data D, DomainItem DI, Element E, Flag F " +
					 "WHERE D.AreaCode IN (" + getAreas(p) + ") " +
					 "AND D.ItemCode IN (" + getItems(p) + ") " +
					 "AND D.ElementCode IN (" + getElements(p) + ") " +
					 "AND DI.DomainCode = '" + getDomain(p) + "' " +
					 "AND D.Year IN (" + getYears(p) + ") " +
					 "AND DI.ItemCode = D.ItemCode " +
					 "AND D.ElementCode = E.ElementCode " +
					 "AND F.Flag = D.Flag " +
					 "ORDER BY D.Year DESC";
		System.out.println("SQL QUERY: " + sql);
		return sql;
	}
	
	private static String getAreas(FAOStatParameters p) {
		String area_codes = "";
		Object[] areas = p.getAreas().keySet().toArray();
		for (int i = 0 ; i < areas.length ; i++) {
			area_codes += "'" + areas[i].toString() + "'";
			if (i < areas.length - 1)
				area_codes += ", ";
		}
		return area_codes;
	}
	
	private static String getItems(FAOStatParameters p) {
		String item_codes = "";
		Object[] items = p.getItems().keySet().toArray();
		for (int i = 0 ; i < items.length ; i++) {
			item_codes += "'" + items[i].toString() + "'";
			if (i < items.length - 1)
				item_codes += ", ";
		}
		return item_codes;
	}
	
	private static String getElements(FAOStatParameters p) {
		String elements_codes = "";
		Object[] elements = p.getElements().keySet().toArray();
		for (int i = 0 ; i < elements.length ; i++) {
			elements_codes += "'" + elements[i].toString() + "'";
			if (i < elements.length - 1)
				elements_codes += ", ";
		}
		return elements_codes;
	}
	
	private static String getYears(FAOStatParameters p) {
		String years_codes = "";
		Object[] years = p.getYears().keySet().toArray();
		for (int i = 0 ; i < years.length ; i++) {
			years_codes += "'" + years[i].toString() + "'";
			if (i < years.length - 1)
				years_codes += ", ";
		}
		return years_codes;
	}
	
	private static String getDomain(FAOStatParameters p) {
		Object[] domains = p.getDomains().keySet().toArray();
		return domains[0].toString().trim();
	}
	
	private String sqlWithLabels(FAOStatParameters p) {
		String sql = "SELECT D.Year, D.AreaCode, A.AreaName" + p.getLanguage() + ", D.ItemCode, I.ItemName" + p.getLanguage() + ", D.ElementCode, E.ElementName" + p.getLanguage() + ", D.Value, F.FlagDescription" + p.getLanguage() + ", E.UnitName" + p.getLanguage() + " " +
					 "FROM Data D, DomainItem DI, Element E, Flag F, Area A, Item I " +
					 "WHERE D.AreaCode IN (" + getAreas(p) + ") " +
					 "AND D.ItemCode IN (" + getItems(p) + ") " +
					 "AND D.ElementCode IN (" + getElements(p) + ") " +
					 "AND DI.DomainCode = '" + getDomain(p) + "' " +
					 "AND D.Year IN (" + getYears(p) + ") " +
					 "AND DI.ItemCode = D.ItemCode " +
					 "AND D.ElementCode = E.ElementCode " +
					 "AND F.Flag = D.Flag " +
					 "AND D.AreaCode = A.AreaCode " +
					 "AND D.ItemCode = I.ItemCode " +
					 "ORDER BY D.Year DESC";
		System.out.println("SQL :" + sql );
		return sql;
	}
	
	private static String sqlChart(FAOStatParameters p) {
		StringBuffer sql = new StringBuffer();
		
					 sql.append("SELECT D.Year, D.AreaCode, A.AreaName" + p.getLanguage() + ", D.ItemCode, I.ItemName" + p.getLanguage() + ", D.ElementCode, E.ElementName" + p.getLanguage() + ", D.Value, F.FlagDescription" + p.getLanguage() + ", E.UnitName" + p.getLanguage() + " " +
//		 sql.append("SELECT D.Year, D.AreaCode   " +

							 	"FROM Data D, DomainItem DI, Element E, Flag F, Area A, Item I " +
							 	"WHERE ");
		
					 Boolean addedFirst = false;
					 if ( !p.getAreas().isEmpty() ) {
						 sql.append(" D.AreaCode IN (" + getAreas(p) + ") " +
									"AND D.AreaCode = A.AreaCode ");
						 addedFirst = true;
					 }
					
					 if ( !p.getItems().isEmpty() ) {
						 if ( addedFirst )
							 sql.append(" AND " );
						 
						 sql.append(" D.ItemCode IN (" + getItems(p) + ") " +
									"AND D.ItemCode = I.ItemCode " +
									"AND DI.ItemCode = D.ItemCode ");
						 
						 addedFirst = true;
					 }
					 if ( !p.getElements().isEmpty() ) {
						 if ( addedFirst )
							 sql.append(" AND " ); 
						 
						 sql.append(" D.ElementCode IN (" + getElements(p) + ") " +
						 			"AND D.ElementCode = E.ElementCode " );
						 
						 addedFirst = true;
					 }
					 
					 if ( !p.getDomains().isEmpty() ) {
						 if ( addedFirst )
							 sql.append(" AND " ); 

						 sql.append(" DI.DomainCode = '" + getDomain(p) + "' " +
								"AND D.Year IN (" + getYears(p) + ") ");
					 	 addedFirst = true;
					 }
								
					 sql.append("AND F.Flag = D.Flag ");
					
					 sql.append("ORDER BY D.Year DESC ");
					 
		System.out.println("SQL :" + sql );
		return sql.toString();
	}

}
