package org.fao.fenix.web.modules.dataviewer.server.faostat.codingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchResultsVO;


import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class FAOSTATGetCodingSystem {
	
	private final static Logger LOGGER = Logger.getLogger(FAOSTATGetCodingSystem.class);
	
	public static DWFAOSTATResultVO getGroups(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getGroups(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getAllGroupsAndAssociatedDomains(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAllGroupsAndAssociatedDomains(qvo));
		
//		rvo.setCodes(getCodyingSystem(qvo));
		
		rvo.setCodesHierachy(getDomainsCodyingSystem(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getAllGroupsAndAssociatedDomainsBulkDownload(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){	
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAllGroupsAndAssociatedDomainsBulkDownload(qvo));
		rvo.setCodesHierachy(getDomainsCodyingSystem(qvo));
		return rvo;
	}
	
   public static DWFAOSTATResultVO getDomainsForGroup(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getSubDomains(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
	
   public static DWFAOSTATResultVO getAllMetadataGroupsAndAssociatedDomains(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAllMetadataGroupsAndAssociatedDomains(qvo));
		
		rvo.setCodesHierachy(getDomainsCodyingSystem(qvo));
		
		return rvo;
	}
   
   public static DWFAOSTATResultVO getAllMetadataMethodologies(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAllMetadataMethodologies(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		//rvo.setCodesHierachy(getDomainsCodyingSystem(qvo));
		
		return rvo;
	}
   
	public static DWFAOSTATResultVO getCountries(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getCountries(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
	
	
	
	public static DWFAOSTATResultVO getCountriesWithContinents(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getCountriesWithContinents(qvo));
		
		rvo.setCodes(getCodyingSystemWithContinents(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getAreas(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAreas(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodyingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodyingSystem(qvo));
		}
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getAreasFAO(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAreasFAO(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodyingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodyingSystem(qvo));
		}
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getCountriesAreasAll(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		List<DWCodesModelData> codes = new ArrayList<DWCodesModelData>();
		rvo.setCodes(codes);
		 
		// COUNTRIES
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getCountries(qvo));
		rvo.getCodes().addAll(getCodyingSystem(qvo));
		
		
		// AREAS
		// SET "TITLE"
//		rvo.getCodes().add(new DWCodesModelData("", "----- Areas -----"));
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAreas(qvo));

		if ( qvo.getGetTotalAndList() ) {
			rvo.getCodes().addAll(getCodyingSystemAggregated(qvo));
			
		}
		else {
			rvo.getCodes().addAll(getCodyingSystem(qvo));
		}
		


		// AREAS FAO 
		// SET "TITLE"
//		rvo.getCodes().add(new DWCodesModelData("", "----- Areas FAO -----"));
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getAreasFAO(qvo));
		
		if ( qvo.getGetTotalAndList() ) {
			rvo.getCodes().addAll(getCodyingSystemAggregated(qvo));
		}
		else {
			rvo.getCodes().addAll(getCodyingSystem(qvo));
		}
		
		
		
		return rvo;
	}

	
	public static DWFAOSTATResultVO getYears(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getYears(qvo));
		
		/** TODO: make it nicer **/
		List<DWCodesModelData> codes = getCodyingSystem(qvo);
	
		// code = startyear
		// label = endyear
		Integer startYear = new Integer(codes.get(0).getCode());
		Integer endYear = new Integer(codes.get(0).getLabel());

		List<DWCodesModelData> result = new ArrayList<DWCodesModelData>();
		while(endYear >= startYear) {
			 result.add(new DWCodesModelData(endYear.toString(), endYear.toString()));
//			 LOGGER.info("getDate: " + startYear);
			 endYear--;
		}
		
		rvo.setCodes(result);
		
		/** TODO get the other years **/
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getItems(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getItems(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getItemsCompareData(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getItemsCompareData(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getItemsAggregations(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getItemsAggregations(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodyingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodyingSystem(qvo));
		}
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getItemsDisaggregations(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getItemsDisaggregations(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodyingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodyingSystem(qvo));
		}
		
		return rvo;
	}
	
	
	
   public static DWFAOSTATResultVO getItemsBYDomainItemElement(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getItems(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
   
   public static DWFAOSTATResultVO getItemsByElement(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getItemsByElement(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getElements(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getElements(qvo));
		
		rvo.setCodes(getCodyingSystemElements(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getElementsList(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getElementsList(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
	

	
   public static DWFAOSTATResultVO getElementsByItem(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getElementsBYDomainItemElement(qvo));
		
		rvo.setCodes(getCodyingSystem(qvo));
		
		return rvo;
	}
   
   
	public static List<DWCodesModelData> getFlags(DWFAOSTATQueryVO qvo, Map<String, String> flags){
		
		List<DWCodesModelData> result = new ArrayList<DWCodesModelData>();
		
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getFlags(qvo.getLanguage(), flags));
		
		result = getCodyingSystem(qvo);
		
		return result;
	}
	
	
	
	
	
	private static LinkedHashMap<DWCodesModelData, List<DWCodesModelData>> getDomainsCodyingSystem(DWFAOSTATQueryVO qvo) {
		LinkedHashMap<DWCodesModelData, List<DWCodesModelData>> map = new LinkedHashMap<DWCodesModelData, List<DWCodesModelData>>();
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				
				try {
					DWCodesModelData top = new DWCodesModelData(rs.getString(1).toString(), rs.getString(2).toString());
	
					DWCodesModelData domain = new DWCodesModelData(rs.getString(3).toString(), rs.getString(4).toString());
					List<DWCodesModelData> current = new ArrayList<DWCodesModelData>();
					
					// getting the date last update if exist 
					String dateLastUpdate = "";
					try { 
						dateLastUpdate = rs.getString(5).toString();
					}catch (Exception e) {
						// TODO: handle exception
					}
					top.setDateLastUpdate(dateLastUpdate);
					domain.setDateLastUpdate(dateLastUpdate);
					
					
					
					DWCodesModelData existKey = exist(map, top.getCode());
					
//					LOGGER.info("existKey " + existKey);
					if ( existKey != null ) {
						current = map.get(existKey);
	
					}
					
					else {
						existKey = top;
					}
					//LOGGER.info("existKey2 " + existKey.getCode());
					current.add(domain);
					map.put(existKey, current);
				}
				catch (Exception e) {
					
				}


			}

			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
//		LOGGER.info("map: " + map);
		return map;
	}
	
	/** TODO: do it better and faster **/
	public static DWCodesModelData exist(LinkedHashMap<DWCodesModelData, List<DWCodesModelData>> map, String code) {
		
		for(DWCodesModelData codeMD : map.keySet() ) {
			if( codeMD.getCode().equalsIgnoreCase(code)) {
				
//				LOGGER.info("found " + code);
				return codeMD;
			}
		}
		
		return null;
	}
	
	
	public static List<DWCodesModelData> getCodyingSystem(DWFAOSTATQueryVO qvo) {
		List<DWCodesModelData> codes = new ArrayList<DWCodesModelData>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				codes.add(new DWCodesModelData(rs.getString(1).toString(), rs.getString(2).toString()));
			}

			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
//		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
	private static List<DWCodesModelData> getCodyingSystemWithContinents(DWFAOSTATQueryVO qvo) {
		List<DWCodesModelData> codes = new ArrayList<DWCodesModelData>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				codes.add(new DWCodesModelData(rs.getString(1).toString(), 
											   rs.getString(2).toString(),
											   rs.getString(3).toString(),
											   rs.getString(4).toString()));
			}

			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
//		LOGGER.info("CODES WITH CONTINENTS: " + codes);
		return codes;
	}
	
	private static List<DWCodesModelData> getCodyingSystemElements(DWFAOSTATQueryVO qvo) {
		List<DWCodesModelData> codes = new ArrayList<DWCodesModelData>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				codes.add(new DWCodesModelData(rs.getString(1).toString(), rs.getString(2).toString() + " ("+ rs.getString(3).toString() + ")"));
//				LOGGER.info(rs.getString(1).toString() + " " + rs.getString(2).toString());
			}

			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
//		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
	private static void setSearchtItemsResults(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		LinkedHashMap<String, FAOSTATSearchResultsVO>  searchResutsHM = new LinkedHashMap<String, FAOSTATSearchResultsVO>();

		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			 int columnCount = rsmd.getColumnCount();
			 
			 // table header
			 for (int i = 0; i < columnCount; i++) {
				 System.out.print(rsmd.getColumnLabel(i + 1)+ " | ");
			  }
			 
			 System.out.println();

			 // the data
			 while (rs.next()) {
				 String itemCode = rs.getString(1).toString();
				 String itemName = rs.getString(2).toString();
				 String groupCode = rs.getString(3).toString();
				 String groupName = rs.getString(4).toString();
				 String domainCode = rs.getString(5).toString();
				 String domainName = rs.getString(6).toString();
				 String elementListCode = rs.getString(7).toString();
				 String elementListName = rs.getString(8).toString();
				 String elementCode = rs.getString(9).toString();
				 String elementName = rs.getString(10).toString();
				 

				 
				 
				 
				 DWCodesModelData code = new DWCodesModelData();
				 code.setCode(itemCode);
				 code.setLabel(itemName);
				  
				  
				  /** mapping to the items results **/
				  
				  // setting the itemCode 
				  FAOSTATSearchResultsVO searchResutsVO = new FAOSTATSearchResultsVO();
				  if ( searchResutsHM.containsKey(itemCode)) {
					  searchResutsVO = searchResutsHM.get(itemCode);
				  }
				

				  // checking if the domain already exists
				  FAOSTATSearchResultVO searchResultVO = new FAOSTATSearchResultVO();
				  if ( searchResutsVO.getSearchResultVO().containsKey(domainCode)) {
					  searchResultVO = searchResutsVO.getSearchResultVO().get(domainCode);
				  }
				  // adding the elements the groups and the domain
				  searchResultVO.getGroups().put(groupCode, groupName);
				  searchResultVO.getDomains().put(domainCode, domainName);
				  searchResultVO.getElementsList().put(elementListCode, elementListName);
				  searchResultVO.getElements().put(elementCode, elementName);
				  searchResultVO.getItems().put(itemCode, itemName);
	

				  // adding to the map
				  searchResutsVO.setCode(code);
				  searchResutsVO.getSearchResultVO().put(domainCode, searchResultVO);  
				  searchResutsHM.put(itemCode, searchResutsVO);

			  }
			 

				//stmt.close();
//				c.close();
				
				rs.close();
				stmt.close();
				c.close();
				
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
		rvo.setSearchResuts(searchResutsHM);
//		printHashMap(searchResutsHM);
//		LOGGER.info("CODES: " + codes);
	
	}
	
	private static void setSearchtElementsResults(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		LinkedHashMap<String, FAOSTATSearchResultsVO>  searchResutsHM = new LinkedHashMap<String, FAOSTATSearchResultsVO>();

		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
//			ResultSetMetaData rsmd = rs.getMetaData();
//			 int columnCount = rsmd.getColumnCount();
			 
			 // table header
//			 for (int i = 0; i < columnCount; i++) {
//				 System.out.print(rsmd.getColumnLabel(i + 1)+ " | ");
//			  }
//			 System.out.println();

			 // the data
			 while (rs.next()) {
				 String elementListCode = rs.getString(1).toString();
				 String elementListName = rs.getString(2).toString();
				 String groupCode = rs.getString(3).toString();
				 String groupName = rs.getString(4).toString();
				 String domainCode = rs.getString(5).toString();
				 String domainName = rs.getString(6).toString();
				 String elementCode = rs.getString(7).toString();
				 String elementName = rs.getString(8).toString();
				 
				 DWCodesModelData code = new DWCodesModelData();
				 code.setCode(elementListCode);
				 code.setLabel(elementListName);
				  
				  /** mapping to the items results **/
				  
				  // setting the itemCode 
				  FAOSTATSearchResultsVO searchResutsVO = new FAOSTATSearchResultsVO();
				  if ( searchResutsHM.containsKey(elementListCode)) {
					  searchResutsVO = searchResutsHM.get(elementListCode);
				  }
				

				  // checking if the domain already exists
				  FAOSTATSearchResultVO searchResultVO = new FAOSTATSearchResultVO();
				  if ( searchResutsVO.getSearchResultVO().containsKey(domainCode)) {
					  searchResultVO = searchResutsVO.getSearchResultVO().get(domainCode);
				  }
				  // adding the elements the groups and the domain
				  searchResultVO.getGroups().put(groupCode, groupName);
				  searchResultVO.getDomains().put(domainCode, domainName);
				  searchResultVO.getElementsList().put(elementListCode, elementListName);
				  searchResultVO.getElements().put(elementCode, elementName);
//				  searchResultVO.getItems().put(itemCode, itemName);
	

				  // adding to the map
				  searchResutsVO.setCode(code);
				  searchResutsVO.getSearchResultVO().put(domainCode, searchResultVO);  
				  searchResutsHM.put(elementListCode, searchResutsVO);

			  }
			 
			
				//stmt.close();
//				c.close();
				
				rs.close();
				stmt.close();
				c.close();
				
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
		rvo.setSearchResuts(searchResutsHM);
//		printHashMap(searchResutsHM);
//		LOGGER.info("CODES: " + codes);
	
	}
	
	private static void printHashMap(LinkedHashMap<String, FAOSTATSearchResultsVO>  searchResutsHM) {
		LOGGER.info("PRINTING THE HASHMAP");
		for(String itemCode : searchResutsHM.keySet()) {
			FAOSTATSearchResultsVO searchResultsVO = searchResutsHM.get(itemCode);
			LOGGER.info("ITEM: " + searchResultsVO.getCode().getCode() + " | " + searchResultsVO.getCode().getLabel());
			
			for(String domainCode : searchResultsVO.getSearchResultVO().keySet()) {
				FAOSTATSearchResultVO searchResultVO = searchResultsVO.getSearchResultVO().get(domainCode);
				LOGGER.info("CONTENTS: " + searchResultVO.getGroups() + " | " + searchResultVO.getDomains() + " | " + searchResultVO.getElementsList());
			}
			
			LOGGER.info("--------------");
		}
	}
	

	
	private static List<DWCodesModelData> getCodyingSystemAggregated(DWFAOSTATQueryVO qvo) {
		List<DWCodesModelData> codes = new ArrayList<DWCodesModelData>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				codes.add(new DWCodesModelData(rs.getString(1).toString(), rs.getString(2).toString()  + " > (List)", true));
				codes.add(new DWCodesModelData(rs.getString(1).toString(), rs.getString(2).toString()  + " + (Total)", false));
			}

			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
//		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
	private static List<DWCodesModelData> getCodyingSystemList(DWFAOSTATQueryVO qvo) {
		List<DWCodesModelData> codes = new ArrayList<DWCodesModelData>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				codes.add(new DWCodesModelData(rs.getString(1).toString(), rs.getString(2).toString(), true));
			}

			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
//		LOGGER.info("CODES: " + codes);
		return codes;
	}

	
	
	// SEARCH METHODS
	
	public static DWFAOSTATResultVO getItemsByLabel(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getItemsByLabel(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodyingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodyingSystem(qvo));
		}
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getElementsByLabel(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getElementsByLabel(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodyingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodyingSystem(qvo));
		}
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getAllGroupsAndAssociatedDomainsByItems(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getAllGroupsAndAssociatedDomainsByItems(qvo));
		
		rvo.setCodesHierachy(getDomainsCodyingSystem(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getAllGroupsAndAssociatedDomainsByElementsList(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getAllGroupsAndAssociatedDomainsByElementsList(qvo));
		
		rvo.setCodesHierachy(getDomainsCodyingSystem(qvo));
		
		return rvo;
	}
	
	
	public static DWFAOSTATResultVO getResultSearchInfos(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getElementsByDomainAndItem(qvo));
		
		rvo.setCodes(getCodyingSystemElements(qvo));
		
		return rvo;
	}
	
	/** TODO: remove? **/
	public static DWFAOSTATResultVO getElementsByDomainAndItem(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getElementsByDomainAndItem(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		rvo.setCodes(getCodyingSystemElements(qvo));
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getResultSearchItems(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getResultSearchItems(qvo));
		
		setSearchtItemsResults(qvo, rvo);
		
		return rvo;
	}
	
	
	public static DWFAOSTATResultVO getResultSearchElements(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		
		qvo.setSql(FAOSTATSearchQueryBuilder.getResultSearchElements(qvo));
		
		setSearchtElementsResults(qvo, rvo);
		
		return rvo;
	}
	
	public static DWFAOSTATResultVO getDownloadItemsOnly(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getDownloadItemsOnly(qvo));
		rvo.setCodes(getCodyingSystem(qvo));
		return rvo;
	}
	
	public static DWFAOSTATResultVO getDownloadItemsAndAggregates(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getDownloadItemsAndAggregates(qvo));
		rvo.setCodes(getCodyingSystem(qvo));
		return rvo;
	}
	
	public static DWFAOSTATResultVO getDownloadAggregatesOnly(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo){
		qvo.setSql(FAOSTATCodingSystemQueryBuilder.getDownloadAggregatesOnly(qvo));
//		rvo.setCodes(getCodyingSystem(qvo));
		rvo.setCodes(getCodyingSystemAggregated(qvo));
		return rvo;
	}

}
