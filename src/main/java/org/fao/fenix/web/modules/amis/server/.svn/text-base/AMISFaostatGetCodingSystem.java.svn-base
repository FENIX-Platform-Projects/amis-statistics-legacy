package org.fao.fenix.web.modules.amis.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;


public class AMISFaostatGetCodingSystem {
	
	private final static Logger LOGGER = Logger.getLogger(AMISFaostatGetCodingSystem.class);

	
//	public static AMISResultVO getAllGroupsAndAssociatedDomains(AMISQueryVO qvo, AMISResultVO rvo){
//		
//		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getAllGroupsAndAssociatedDomains(qvo));
//		
//		rvo.setCodesHierachy(getDomainsCodingSystem(qvo));
//		
//		return rvo;
//	}
	
	public static AMISResultVO getCropGroupsAndAssociatedDomains(AMISQueryVO qvo, AMISResultVO rvo){
		
		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getCropGroupsAndAssociatedDomains(qvo));
		
		rvo.setCodesHierachy(getDomainsCodingSystem(qvo));
		
		return rvo;
	}
	
	private static LinkedHashMap<AMISCodesModelData, List<AMISCodesModelData>> getDomainsCodingSystem(AMISQueryVO qvo) {
		LinkedHashMap<AMISCodesModelData, List<AMISCodesModelData>> map = new LinkedHashMap<AMISCodesModelData, List<AMISCodesModelData>>();
		
		try {
			SQLServerDriver.class.newInstance();
			//System.out.println("AMISFaostatGetCodingSystem Url = "+qvo.getConnectionString());
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				
				try {
					AMISCodesModelData top = new AMISCodesModelData(rs.getString(1).toString(), rs.getString(2).toString());
	
					AMISCodesModelData domain = new AMISCodesModelData(rs.getString(3).toString(), rs.getString(4).toString());
					List<AMISCodesModelData> current = new ArrayList<AMISCodesModelData>();
					
					AMISCodesModelData existKey = exist(map, top.getCode());
					
					LOGGER.info("existKey " + existKey);
					if ( existKey != null ) {
						current = map.get(existKey);
	
					}
					
					else {
						existKey = top;
					}
					LOGGER.info("existKey2 " + existKey.getCode());
					current.add(domain);
					map.put(existKey, current);
				}
				catch (Exception e) {
					
				}


			}

			stmt.close();
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		LOGGER.info("map: " + map);
		return map;
	}
	
	/** TODO: do it better and faster **/
	public static AMISCodesModelData exist(LinkedHashMap<AMISCodesModelData, List<AMISCodesModelData>> map, String code) {
		
		for(AMISCodesModelData codeMD : map.keySet() ) {
			if( codeMD.getCode().equalsIgnoreCase(code)) {
				
				LOGGER.info("found " + code);
				return codeMD;
			}
		}
		
		return null;
	}
	
	public static AMISResultVO getCountries(AMISQueryVO qvo, AMISResultVO rvo){
		
		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getCountries(qvo));
		
		rvo.setCodes(getCodingSystem(qvo));
		
		return rvo;
	}
	
	private static List<AMISCodesModelData> getCodingSystem(AMISQueryVO qvo) {
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				codes.add(new AMISCodesModelData(rs.getString(1).toString(), rs.getString(2).toString()));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
	public static AMISResultVO getAreas(AMISQueryVO qvo, AMISResultVO rvo){
		
		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getAreas(qvo));
		
		/** TODO: set another if for the only the list and the total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodingSystem(qvo));
		}
		
		return rvo;
	}

	private static List<AMISCodesModelData> getCodingSystemAggregated(AMISQueryVO qvo) {
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				codes.add(new AMISCodesModelData(rs.getString(1).toString(), rs.getString(2).toString()  + " > (List)", true));
				codes.add(new AMISCodesModelData(rs.getString(1).toString(), rs.getString(2).toString()  + " + (Total)", false));
			}

			stmt.close();
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
public static AMISResultVO getAreasFAO(AMISQueryVO qvo, AMISResultVO rvo){
		
		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getAreasFAO(qvo));
		
		/** TODO: set another if for the only the list and the total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodingSystem(qvo));
		}
		
		return rvo;
	}
	
//	public static AMISResultVO getCountrieAreasAll(AMISQueryVO qvo, AMISResultVO rvo){
//		
//		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
//		rvo.setCodes(codes);
//		 
//		// COUNTRIES
//		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getCountries(qvo));
//		rvo.getCodes().addAll(getCodingSystem(qvo));
//		
//		
//		// AREAS
//		// SET "TITLE"
////		rvo.getCodes().add(new DWCodesModelData("", "----- Areas -----"));
//		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getAreas(qvo));
//
//		if ( qvo.getGetTotalAndList() ) {
//			rvo.getCodes().addAll(getCodingSystemAggregated(qvo));
//			
//		}
//		else {
//			rvo.getCodes().addAll(getCodingSystem(qvo));
//		}
//
//		// AREAS FAO 
//		// SET "TITLE"
////		rvo.getCodes().add(new DWCodesModelData("", "----- Areas FAO -----"));
//		
//		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getAreasFAO(qvo));
//		
//		if ( qvo.getGetTotalAndList() ) {
//			rvo.getCodes().addAll(getCodingSystemAggregated(qvo));
//		}
//		else {
//			rvo.getCodes().addAll(getCodingSystem(qvo));
//		}
//				
//		return rvo;
//	}
	
//public static AMISResultVO getYears(AMISQueryVO qvo, AMISResultVO rvo){
//		
//		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getYears(qvo));
//		
//		/** TODO: make it nicer **/
//		List<AMISCodesModelData> codes = getCodingSystem(qvo);
//	
//		// code = startyear
//		// label = endyear
//		Integer startYear = new Integer(codes.get(0).getCode());
//		Integer endYear = new Integer(codes.get(0).getLabel());
//
//		LOGGER.info("endYear: " + endYear);
//		LOGGER.info("startYear: " + startYear);
//		List<AMISCodesModelData> result = new ArrayList<AMISCodesModelData>();
//		while(endYear >= startYear) {
//			if(endYear>=1990)
//			{
//			 result.add(new AMISCodesModelData(endYear.toString(), endYear.toString()));
////			 LOGGER.info("getDate: " + startYear);
//			}
//			 endYear--;
//		}
//		
//		rvo.setCodes(result);
//		
//		/** TODO get the other years **/
//		
//		return rvo;
//	}

public static AMISResultVO getStaticYears(AMISQueryVO qvo, AMISResultVO rvo){
	
	List<AMISCodesModelData> result = new ArrayList<AMISCodesModelData>();
	String endYear[] = new String[22];
	String year= "";
	int i =0;
	int indexYear =0;
	for(i=0; i< 10;i++)
	{
		year = "199"+i;
		endYear[indexYear] = year;
		indexYear++;
	}
	for(i=0; i< 10;i++)
	{
		year = "200"+i;
		endYear[indexYear] = year;
		indexYear++;
	}
	for(i=10; i< 12;i++)
	{
		year = "20"+i;
		endYear[indexYear] = year;
		indexYear++;
	}
	
	for(i=0; i< 22; i++)
	{
		result.add(new AMISCodesModelData(endYear[i], endYear[i]));
	}
	
	rvo.setCodes(result);
	
	/** TODO get the other years **/
	
	return rvo;
}
	
	public static AMISResultVO getItems(AMISQueryVO qvo, AMISResultVO rvo){
		
		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getItems(qvo));
		
		rvo.setCodes(getCodingSystem(qvo));
		
		return rvo;
	}
	
public static AMISResultVO getItemsAggregations(AMISQueryVO qvo, AMISResultVO rvo){
		
		qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getItemsAggregations(qvo));
		
		/** TODO: set another if for the only the list and thte total...**/
		if ( qvo.getGetTotalAndList() ) {
			rvo.setCodes(getCodingSystemAggregated(qvo));
		}
		else {
			rvo.setCodes(getCodingSystem(qvo));
		}
		
		return rvo;
	}

public static AMISResultVO getElements(AMISQueryVO qvo, AMISResultVO rvo){
	
	qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getElements(qvo));
	
	rvo.setCodes(getCodingSystemElements(qvo));
	
	return rvo;
}
//
//public static AMISResultVO getAllElements(AMISQueryVO qvo, AMISResultVO rvo){
//	//public static AMISResultVO getAllElements(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
//	
////	qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCTS"));	
////	
////    List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
////    String tablename = sqlResult.get(0).toString();
////    
////    
////	qvo.setSql(AMISCodingSystemQueryBuilder.getProductsQuery(qvo, tablename));	
////	
////    List<Object[]> productsSqlResult =  amisDao.getResultList(qvo.getSql());
////	HashMap<String, String> hm = new HashMap<String, String>();
////	for(int i=0; i < productsSqlResult.size(); i++) {
////		hm.put(productsSqlResult.get(i)[0].toString(),productsSqlResult.get(i)[1].toString());
////	}
////	qvo.setItems(hm);
//	qvo.setSql(AMISFaostatCodingSystemQueryBuilder.getAllElements(qvo));
//	
//	rvo.setCodes(getCodingSystemElements(qvo));
//	
//	return rvo;
//}

public static List<AMISCodesModelData> getCodingSystemElements(AMISQueryVO qvo) {
	List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
	try {
		SQLServerDriver.class.newInstance();
		Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
		Statement stmt = c.createStatement();
		stmt.executeQuery(qvo.getSql());
		ResultSet rs = stmt.getResultSet();
		
		while (rs.next()) {
			codes.add(new AMISCodesModelData(rs.getString(1).toString(), rs.getString(2).toString() + " ("+ rs.getString(3).toString() + ")"));
		}

		stmt.close();
	} catch (SQLException e) {
		throw new FenixException(e.getMessage());
	} catch (IllegalAccessException e) {
		throw new FenixException(e.getMessage());
	} catch (InstantiationException e) {
		throw new FenixException(e.getMessage());
	} 
	
	LOGGER.info("CODES: " + codes);
	return codes;
}
}
