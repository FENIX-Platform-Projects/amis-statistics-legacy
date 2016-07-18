package org.fao.fenix.web.modules.dataviewer.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATAggregationConstant;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATTableConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.CalculationParametersVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.server.utils.FAOSTATUtils;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DWFAOSTATQueryBuilder {
	
	private final static Logger LOGGER = Logger.getLogger(DWFAOSTATQueryBuilder.class);

	// PIVOT TABLE
	public static String pivotTable(DWFAOSTATQueryVO qvo, String dbName) {
		StringBuffer sql = new StringBuffer();

		if (!qvo.getIsTradeMatrix())
			sql.append(getPivotTableSummariesW(qvo, dbName));
		else {
			sql.append(getPivotTableTMSummariesW(qvo, dbName));
		}		
		
//		LOGGER.info("SQL: " + sql);
		return sql.toString();	
	}	
	
	private static String getPivotTableTMSummariesW(DWFAOSTATQueryVO qvo, String dbName) {
		StringBuffer sql = new StringBuffer();
		if (dbName.equalsIgnoreCase("FAOSTAT") || dbName.equalsIgnoreCase("PRODUCTION-DISSEMINATION")) {
			sql.append("exec getPivotedTMSummariesW ");
		}
		else {
			sql.append("exec Diss..getPivotedTMSummariesW ");
		}
		
		// set the filters on dimensions
		sql.append(getDimensions(qvo));
		
		// set the pivoted columns
		sql.append(getPivotedColumnsTM(qvo));
		
		sql.append(getOptions(qvo));
		return sql.toString();	
	}
	
	private static String getPivotTableSummariesW(DWFAOSTATQueryVO qvo, String dbName) {
		StringBuffer sql = new StringBuffer();
//		sql.append("exec Diss..getPivotedSummariesW ");
//		sql.append("exec getPivotedSummariesW ");
		if (dbName.equalsIgnoreCase("FAOSTAT") || dbName.equalsIgnoreCase("PRODUCTION-DISSEMINATION")) {
			sql.append("exec getPivotedSummariesW ");
		}
		else {
			sql.append("exec Diss..getPivotedSummariesW ");
		}


		
		// set the filters on dimensions
		sql.append(getDimensions(qvo));
		
		// set the pivoted columns
		sql.append(getPivotedColumns(qvo));

		
		sql.append(",@databasename='Warehouse'" +
				   ",@itemtablename='Item'" +
				   ",@areatablename='Area'" +
				   ",@datatablename='Data'" +
				   ",@unittablename='Year'" +
				   ",@subjectsetname=''");
		
		sql.append(getOptions(qvo));
		return sql.toString();	
	}
	
	private static String getDimensions(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		// countries
		if ( !qvo.getAreas().isEmpty() )
			sql.append("@dimension1='" + getPivotFilters( qvo.getAreas() ) +"' ");
		else if ( !qvo.getReportedAreas().isEmpty() ) {
			sql.append("@dimension1='" + getPivotFilters( qvo.getReportedAreas() ) +"' ");
		}
		
		// years 
		sql.append(",@dimension2='" + getPivotFilters( qvo.getYears() ) +"' ");
		
		// items 
		if ( !qvo.getIsFBS() ) {
			// standard
			sql.append(",@dimension3='" + getPivotFilters( qvo.getItems() ) +"' ");
		}
		else {
			// FB order
			sql.append(",@dimension3='" + getPivotFilters( getValues(qvo, getFBItemsOrdered(qvo, true)) ) +"' ");
		}
		
		// elements 
		if ( !qvo.getIsFBS() ) {
			if ( !qvo.getElements().isEmpty() ) {
//				LOGGER.info("!qvo.getElements().isEmpty(): " + qvo.getElements());
				sql.append(",@dimension4='" + getPivotFilters( qvo.getElements() ) +"' ");
			}
			if ( !qvo.getElementsList().isEmpty() ) {
//				LOGGER.info("!qvo.getElementsList).isEmpty() " + qvo.getElementsList());
	
				sql.append(",@dimension4='" + getPivotFilters( getValues(qvo, getElementsFromElementsListSQL(qvo)) ) +"' ");
			}
		}
		else if ( qvo.getIsFBS() ) {

			qvo.setElements(getValues(qvo, getElementsFromElementsListSQL(qvo)));
			
			sql.append(",@dimension4='" + getPivotFilters( getValues(qvo, getFBElementsOrdered(qvo, true)) ) +"' ");

		}
		
		if ( qvo.getIsTradeMatrix() ) {
			if ( !qvo.getPartnerAreas().isEmpty() ) {
//				LOGGER.info("!qvo.getPartnerAreas().isEmpty() " + qvo.getPartnerAreas());
				sql.append(",@dimension5='" + getPivotFilters( qvo.getPartnerAreas() ) +"' ");
			}
			else {
				sql.append(",@dimension5='" + getPivotFilters( getValues(qvo, getPartnerTradeMatrix(qvo)) ) +"' ");
				/** TODO: retrieve all the reported areas... by the reporter countries e sort by label.. or just get all? **/

			}
		}
		
		return sql.toString();	
	}
	

	
	private static String getPivotedColumns(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
//		sql.append(",@pivotdimension1='countries' ");
		sql.append(",@pivotdimension1='" + qvo.getNestedby() + "' ");
		
//		sql.append(",@pivotdimension2='years' ");
		sql.append(",@pivotdimension2='" + qvo.getxAxis() + "' ");
				
		// y1Axis, y2Axis TODO: and the codes columns?? 
//		sql.append("@extracolumns='ccodes,items,icodes,extra'");
		sql.append(",@extracolumns='" + qvo.getY1Axis() + "," + qvo.getY2Axis() + "'");

		if ( qvo.getIsFBS() ) {
			sql.append(",@extratablename = 'vElementFB'");
		}
		else {
			sql.append(",@extratablename='Element'");
		}

		
		return sql.toString();	
	}
	
	private static String getPivotedColumnsTM(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(",@pivotdimension1 = 'Reporter'");                      
		sql.append(",@pivotdimension2 = 'Partner'");                         
		sql.append(",@extracolumns = 'Element, Year,Item'");
		
		return sql.toString();	
	}
	
	private static String getOptions(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		// LIMIT
		if( qvo.getLimit() != null && qvo.getLimit() != 0) {
			sql.append(",@maxrecords=" + qvo.getLimit() + "");
		}	
		else {
			sql.append(",@maxrecords=0");
		}
		
		sql.append(",@showunits=" + getBooleanValue(qvo.getShowMeasurementUnit()) );
		
		sql.append(",@showflags=" + getBooleanValue(qvo.getAddFlag()) );
		
		sql.append(",@showcodes=" + getBooleanValue(qvo.getShowCodes()) );

		sql.append(",@showaggregators=0");
		
		sql.append(",@tablelanguage='" + qvo.getLanguage() + "'");

		sql.append(",@officialflags=''");
		
		sql.append(",@thousandsseparator='" + qvo.getThousandSeparator() + "'");
		
		sql.append(",@decimalseparator='" + qvo.getDecimalseparator() + "'");
		
		sql.append(",@ShowDec='" + qvo.getShowDec() + "'");
		
		sql.append(",@showNull=" + getBooleanValue(qvo.getShowNull()) );

		// Food Balance Sheet Flag
		if (!qvo.getIsTradeMatrix())
			if ( qvo.getIsFBS() )
				sql.append(",@FBS = '1'");
			else
				sql.append(",@FBS = '0'");
		
		return sql.toString();	
	}
	
	private static String getBooleanValue(Boolean value) {
		
		if ( value )
			return "1";
		else
			return "0";
		
	} 
	
	private static Map<String, String> getValues(DWFAOSTATQueryVO qvo, String sql) throws FenixException {
		Map<String, String> values = new HashMap<String, String>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
//				values.add(rs.getString(1));
				values.put(rs.getString(1), rs.getString(1));
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

//		LOGGER.info("VALUES: " + values);
		return values;
	}
	
	private static String getElementsFromElementsListSQL(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
//		SELECT DISTINCT DIEU.ElementCode 
//		FROM Warehouse.dbo.DomainItemElementUnit DIEU 
//		WHERE DIEU.ElementListCode IN ('5155') 
//		AND DIEU.DomainCode IN ('GY')

		sql.append("SELECT DISTINCT DIEU.ElementCode ");
		sql.append("FROM Warehouse.dbo.DomainItemElementUnit DIEU ");
		sql.append("WHERE ");
//		// Elements
		sql.append(" DIEU.ElementListCode IN (" + getFilters(qvo.getElementsList()) +  ") ");
//		
//		// Domains
		sql.append(" AND DIEU.DomainCode IN (" + getFilters(qvo.getDomains()) +  ") ");

		
		
		// OLD
//		StringBuffer sql = new StringBuffer();
//		
//		sql.append("SELECT E.ElementCode ");
//		sql.append("FROM DomainElement DE, Element E ");
//		sql.append("WHERE ");
//
//		// Elements
//		sql.append(" E.ElementListCode IN (" + getFilters(qvo.getElementsList()) +  ") ");
//		
//		// Domains
//		sql.append(" AND DE.DomainCode IN (" + getFilters(qvo.getDomains()) +  ") ");
//		
//		
//		sql.append(" AND DE.ElementCode = E.ElementCode ");
//		
//		sql.append(" GROUP BY E.ElementCode ");
		
		LOGGER.info("sql: " + sql);
		
		return sql.toString();
	}
	
	private static String getPartnerTradeMatrix(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT DA.AreaCode ");
		sql.append("FROM DomainArea DA ");
		sql.append("WHERE ");

		// Elements
		sql.append(" DA.DomainCode = 'TMP' ");
		
		sql.append(" GROUP BY DA.AreaCode ");
		
//		LOGGER.info("sql: " + sql);
		
		return sql.toString();
	}
	
	private static String getFBItemsOrdered(DWFAOSTATQueryVO qvo, Boolean useFilter) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT DI.ItemCode ");
		sql.append(" FROM DomainItem DI");
		sql.append(" WHERE ");

		// Elements
		sql.append(" DI.DomainCode = 'FB' ");
		
		if ( useFilter )
			sql.append(" AND DI.ItemCode IN (" + getFilters(qvo.getItems()) +  ") ");

		sql.append(" ORDER BY DI.[Order] ASC ");

//		LOGGER.info("sql: " + sql);
		
		return sql.toString();
	}
	
	
	private static String getFBElementsOrdered(DWFAOSTATQueryVO qvo, Boolean useFilter) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT DE.ElementCode ");
		sql.append(" FROM DomainElement DE");
		sql.append(" WHERE ");

		// Elements
		sql.append(" DE.DomainCode = 'FB' ");
		
		if ( useFilter ) {
			if ( !qvo.getElements().isEmpty() )
				sql.append(" AND DE.ElementCode IN (" + getFilters(qvo.getElements()) +  ") ");
		}

		
		sql.append(" ORDER BY DE.[Order] ASC ");
		
//		LOGGER.info("sql: " + sql);
		
		return sql.toString();
	}
	
	public static String sqlFenixMetadataTableQuery(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(getSelects(qvo, true));
		sql.append(" FROM ");
		
		// add selection
		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
			String table = qvo.getFroms().get(i);
			
			sql.append(table);
			
			
			if (i < qvo.getFroms().size() - 1)
				sql.append(", ");
		}
		
		if(!qvo.getGroups().isEmpty()) {
			sql.append(" WHERE groupcode IN (");		
			sql.append(getGroups(qvo)); 
			sql.append(")");
		}
		
		 System.out.println("SQL :" + sql );
		return sql.toString();
	}
	
	public static String sqlFaostatMetadataTableQuery(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(getSelects(qvo, true, true));
		sql.append(" FROM ");
		
		// add selection
		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
			String table = qvo.getFroms().get(i);
			
			sql.append(table);
			
			
			if (i < qvo.getFroms().size() - 1)
				sql.append(", ");
		}
		
		if((qvo.getGroups()!=null  && !qvo.getGroups().isEmpty()) || (qvo.getDomains()!=null && !qvo.getDomains().isEmpty())){
			sql.append(" WHERE ");
			
			boolean addedFirst = false;
			
			if(!qvo.getGroups().isEmpty()) {	
				if(addedFirst)
					sql.append(" AND ");	
							
				sql.append(" GroupCode IN (");	
				sql.append(getGroups(qvo)); 
				sql.append(")");
				
				addedFirst = true;
			} 
			
			if(!qvo.getDomains().isEmpty()) {
				if(addedFirst)
					sql.append(" AND ");	
							
				sql.append(" DomainCode IN (");	
				sql.append(getDomain(qvo)); 
				sql.append(")");
				
				addedFirst = true;
			}
		} else {

			if(qvo.getFilterMap()!=null && !qvo.getFilterMap().isEmpty()) {	
				sql.append(" WHERE ");

				int k = 0;

				for(String field: qvo.getFilterMap().keySet()){
					sql.append(" "+field+" IN (");	

					sql.append(getFilterValues(qvo.getFilterMap().get(field))); 
					sql.append(")");

					if (k < qvo.getFilterMap().size() - 1)
						sql.append(" AND ");

					k++;
				}

			} 

		}

		 sql.append(addOrderByClause(qvo));
		 
		 
		 System.out.println("SQL :" + sql );
		return sql.toString();
	}
	
	
	public static String sqlGlossaryTermsForMetadataMethodologyQuery(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT GlossaryName"+qvo.getLanguage());
		sql.append(" FROM Metadata_Glossary G, Metadata_MethodologyGlossary_r MG ");
		
		if(qvo.getFilterMap()!=null && !qvo.getFilterMap().isEmpty()) {	
			sql.append(" WHERE ");

			int k = 0;

			for(String field: qvo.getFilterMap().keySet()){
				sql.append(" "+field+" IN (");	

				sql.append(getFilterValues(qvo.getFilterMap().get(field))); 
				sql.append(")");

				if (k < qvo.getFilterMap().size() - 1)
					sql.append(" AND ");

				k++;
			}
		}
		
	    sql.append(" AND MG.GlossaryCode = G.GlossaryCode ORDER BY GlossaryName"+qvo.getLanguage());
		 
		 System.out.println("SQL :" + sql );
		return sql.toString();
	}
	
	/** TODO: integrate with the buildQuery or sqlTableQuery **/
	public static String sqlJoinQuery(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		if(qvo.getRunMaxDateQuery()){
			sql.append(addSelectMaxDate());		
		} else {
//			LOGGER.info("runMaxDateQuery IS FALSE .." );
			sql.append(getSelects(qvo, true));
		}

		sql.append(" FROM ");
		
		int i = 1;
		for(DWFAOSTATQueryVO q : qvo.getJoinQueryVO().getQvos()) {
			
			String joinColumn = qvo.getJoinColumn();
			
			sql.append(" ( ");
			
			sql.append(getSelects(q, true));
			
//			sql.append(" FROM Data D, Element E, Area A, Item I, DomainArea DA ");
			sql.append(" FROM Data D, Element E, Area A, Item I ");
			if ( qvo.getAddFlag() ) {
				sql.append(", Flag F ");
			}
			
			sql.append(getWheres(q));

			// check if the flag can give problems
			sql.append(addJoins(q, q.getAddFlag()));
				 
			sql.append(addLabesJoins(q));
			
			// general regional conditions 
			// TODO: that is setted at global level (join qvo level)
//			LOGGER.info("GET Regional .." );
			sql.append( addRegionalConditions(qvo));
			
			if(!qvo.getRunMaxDateQuery()) {
				if ( !qvo.getGroupBys().isEmpty() ) {
					sql.append(addGroupBYClause(q.getGroupBys()));
				}
//				 sql.append(addGroupBYClauseJoin(q));
			}
			
			
			sql.append(" ) as Q" + i );

			
			if ( i > 1 ) {
				// add ON clause
				sql.append(" ON Q"+(i-1)+"." + joinColumn + " = Q"+i+"." + joinColumn + " ");
			}
			
			if ( i < qvo.getJoinQueryVO().getQvos().size() ) {
//				sql.append(" FULL JOIN ");
				sql.append(" INNER JOIN ");
			}
		
			i++;
		}
		 sql.append(addOrderByClause(qvo));

					 
//		LOGGER.info("SQL :" + sql );
		return sql.toString();
	}
	

//	/** TODO Mix with the chart query... */
//	public static String sqlTableQuery(DWFAOSTATQueryVO qvo) {
//		StringBuffer sql = new StringBuffer();
//		
//		if(qvo.getRunMaxDateQuery()){
//			sql.append(addSelectMaxDate());		
//		} else {
//			sql.append(getSelects(qvo, true));
//		}
//
//		sql.append(" FROM Element E, Item I ");
//		
//		if ( !qvo.getIsTradeMatrix()) {
//			sql.append(",Area A ,Data D ");
//		}
//		else {
//			// RA + ReporterArea
//			// PA + PartnerArea
//			sql.append(",Area RA ,Area PA, TradeMatrix D ");
//		}
//
//		if ( qvo.getAddFlag() ) {
//			sql.append(", Flag F ");
//		}
//	
//
//		sql.append(getWheres(qvo));
//		
//		
//		
//
//		 // check if the flag can give problems
//		 sql.append(addJoins(qvo, qvo.getAddFlag()));
//			 
//		 if ( qvo.getAddLabels())
//			 sql.append( addLabesJoins(qvo));
//		 
//		sql.append( addRegionalConditions(qvo));
//		
//		if(!qvo.getRunMaxDateQuery()) {
//			if ( !qvo.getGroupBys().isEmpty() ) {
//				sql.append(addGroupBYClause(qvo.getGroupBys()));
//			}
//		}
//			 
//		// TODO: an IF with getRunMaxDateQuery?
//		sql.append(addOrderByClause(qvo));
//			 
//		 if(qvo.getRunMaxDateQuery())
//			sql.append(addOrderByMaxDate());	
//		 
//
//
//		 
//		return sql.toString();
//	}
	
	private static String getSelects(DWFAOSTATQueryVO qvo, Boolean withLimitCheck, Boolean withNullHandling) {
		StringBuffer sql = new StringBuffer();   
        boolean handleNullValues = false;
		
        sql.append("SELECT ");
		
		 if(withNullHandling && qvo.getHandleNullForFieldMap() != null && !qvo.getHandleNullForFieldMap().isEmpty()) {
			 handleNullValues = true;
	     }
		
		 
		if ( withLimitCheck )
			if( qvo.getLimit() != null && qvo.getLimit() != 0) {
				sql.append(" TOP ").append(qvo.getLimit()).append(" ");
			}		
		
		// add selection
		for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
			String select = qvo.getSelects().get(i);
						
			if(select.contains("+FAOSTATConstants.faostatLanguage")){
				select = select.replace("+FAOSTATConstants.faostatLanguage", FAOSTATConstants.faostatLanguage);		
			}
			
			if(handleNullValues) {
				if(qvo.getHandleNullForFieldMap().containsKey(select))
				  select = 	" CASE WHEN ("+select+" IS NULL) THEN '' ELSE "+select+" END AS "+select+" ";
			} 
			
			
			sql.append(select);
					
			
			if ( qvo.getSelectsAlias().containsKey(select)) {
				sql.append(" AS '" +  qvo.getSelectsAlias().get(select) + "' ");
			}
			
			
			if (i < qvo.getSelects().size() - 1)
				sql.append(", ");
		}
		
		return sql.toString();
	}
	
	private static String getSelects(DWFAOSTATQueryVO qvo, Boolean withLimitCheck) {
		StringBuffer sql = new StringBuffer();   

		sql.append("SELECT ");
		
		if ( withLimitCheck )
			if( qvo.getLimit() != null && qvo.getLimit() != 0) {
				sql.append(" TOP ").append(qvo.getLimit()).append(" ");
			}		
		
		// add selection
		for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
			String select = qvo.getSelects().get(i);
			
			if(select.contains("+FAOSTATConstants.faostatLanguage")){
				select = select.replace("+FAOSTATConstants.faostatLanguage", FAOSTATConstants.faostatLanguage);		
			}

			sql.append(select);
			
			if ( qvo.getSelectsAlias().containsKey(select)) {
				sql.append(" AS '" +  qvo.getSelectsAlias().get(select) + "' ");
			}
			
			
			if (i < qvo.getSelects().size() - 1)
				sql.append(", ");
		}
		
		return sql.toString();
	}
	
	
	private static String addSelectMaxDate() {
		StringBuffer sql = new StringBuffer();   
		sql.append(" SELECT TOP 1 D.Year").append(" ");
//		sql.append(" SELECT MAX(D.Year)").append(" ");
		
		return sql.toString();
	}
	
	private static String addOrderByMaxDate() {
		StringBuffer sql = new StringBuffer();   
		sql.append(" ORDER BY D.Year DESC").append(" ");
//		sql.append(" SELECT MAX(D.Year)").append(" ");
		
		return sql.toString();
	}
	
	private static String getLowerDateLimit(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();  
		sql.append(" year(DATEADD(year, -"+qvo.getTimeSpan()+", '"+qvo.getMaxDateLimit()+"'))").append(" ");
		
		return sql.toString(); 
	}

	
	private static String addDateRange(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();  
		sql.append(" (D.Year BETWEEN "+getLowerDateLimit(qvo)+" AND '"+qvo.getMaxDateLimit()+"')").append(" ");
		
		return sql.toString(); 
	}
	
	
	/** TODO: needed?? move to addLabesJoins **/
	private static String addJoins(DWFAOSTATQueryVO qvo, Boolean addFlag) {
		StringBuffer sql = new StringBuffer();     	
//		if ( addedFirst )
//	     	sql.append(" AND " );
		


//		 sql.append(" DA.DomainCode IN (" + getDomain(qvo) + ") ");
//		 sql.append(" AND DA.AreaCode = D.AreaCode  ");
	     	
		 if ( addFlag )
			 sql.append(" AND F.Flag = D.Flag ");
		 
		return sql.toString();
	}
	

	public static String getFlags(DWFAOSTATQueryVO qvo) {
		String sql = new String();
		List<String> backupSelects = new ArrayList<String>();
		for(String select : qvo.getSelects() ) {
			backupSelects.add(select);
		}
		List<String> backupOrderBys = new ArrayList<String>();
		for(String orderBy : qvo.getOrderBys() ) {
			backupOrderBys.add(orderBy);
		}
		
		List<String> selects = new ArrayList<String>();
		selects.add("F.Flag");
		selects.add("F.FlagDescription"+ qvo.getLanguage());
		qvo.setSelects(selects);
		
		List<String> orderBys = new ArrayList<String>();
		orderBys.add("F.Flag");
		qvo.setOrderBys(orderBys);
		
		qvo.setSortingOrder("ASC");
		
		HashMap<String, String> groupBys = new HashMap<String, String>();
		groupBys.put("F.Flag", "F.Flag");
		groupBys.put("F.FlagDescription"+ qvo.getLanguage(), "F.FlagDescription"+ qvo.getLanguage());
		qvo.setGroupBys(groupBys);

		// sort
		
		sql = buildQuery(qvo, false, false, false);
		
		// reinstate the old selects
		qvo.setSelects(backupSelects);
		qvo.setOrderBys(backupOrderBys);
		
		return sql;
	}
	

	public static String buildQuery(DWFAOSTATQueryVO qvo, Boolean isRunmaxQuery, Boolean addUnitName, Boolean addValue) {
		
		/** TODO: remove it from here...
		 * 		pass it or in the qvo or in the method
		 * ***/
		
		Boolean addGroupBy = false;
		Boolean addAverageOnPeriod = false;
		if( qvo.getRunCalculationQuery()) { 
			/** TODO: check if everything works fine with that query definition...**/
			if(qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.AVERAGE_OVER_TIME)){
				addAverageOnPeriod = true;
			}
		}	
		
		StringBuffer sql = new StringBuffer();
		
		if(qvo.getRunMaxDateQuery()){
			sql.append(addSelectMaxDate());		
		} else {
			sql.append(getSelects(qvo, true));
			
			// TODO: Dynamic decimal places
			if ( !addAverageOnPeriod && addValue ) {
				sql.append( ", ROUND(");
				if ( qvo.getAggregationType() != null ) {
					addGroupBy = true;
					sql.append(qvo.getAggregationType() +"(D.Value), 3)");
				}
				else {
					sql.append("D.Value, 3) ");
				}	
			}
			else if ( addAverageOnPeriod && addValue) {
				String timerage = getTimerange(qvo);
				sql.append( ", ROUND(");
				if ( qvo.getAggregationType() != null ) {
					addGroupBy = true;
					sql.append(qvo.getAggregationType() +"(D.Value)/"+ timerage +", 3) ");
				}
				else {
					sql.append("D.Value/"+ timerage +", 3) ");
				}
			}

			if ( addUnitName )
				sql.append( ", E.UnitName" + qvo.getLanguage() + " ");
		}
		
		sql.append(getFroms(qvo, addUnitName));

//		LOGGER.info("GET Wheres .." );
		sql.append(getWheres(qvo));

//		LOGGER.info("GET Joins .." );
		sql.append( addJoins(qvo, qvo.getAddFlag()));
			 
//		LOGGER.info("GET Label Joins .." );
		sql.append( addLabesJoins(qvo));
			 
//		LOGGER.info("GET Regional .." );
		sql.append( addRegionalConditions(qvo));

			 
		/** this is the subquery if the limit exists **/
		if ( !isRunmaxQuery ) {
			if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {	
//				LOGGER.info("CREATING THE NESTED QUERY ..");
				// add the other query 
				
				/**
				 * GET NESTED QUERY and IF
				 */
				String nestedQuery = getNestedQuery(qvo);
				
				if ( !nestedQuery.equalsIgnoreCase("")) {	
					sql.append(" AND " + qvo.getNestedLimitField() + " IN ( ");
					sql.append(nestedQuery);
					sql.append(" ) ");
				}
			}	
		}

		// GROUP BY
		if(!qvo.getRunMaxDateQuery()) {
			if ( !qvo.getGroupBys().isEmpty() ) {
				sql.append(addGroupBYClause(qvo.getGroupBys()));
			}
			else if ( addGroupBy ) {
				sql.append(addGroupBYClause(qvo, addUnitName));
			}
			// TODO: workaround to forse the groupby clause
			else if ( !qvo.getGroupBys().isEmpty() ) {
				sql.append(addGroupBYClause(qvo, addUnitName));
			}
		}

		// ORDER BY
		sql.append(addOrderByClause(qvo));
			 
		// ORDER BY for the max date
		if(qvo.getRunMaxDateQuery()){ 
			sql.append(addOrderByMaxDate());
		}
					 
		LOGGER.info("SQL :" + sql );
		return sql.toString();
	}
	
	private static String getFroms(DWFAOSTATQueryVO qvo, Boolean addUnitName) {
		StringBuffer sql = new StringBuffer();
		
//		sql.append(" FROM Data D, Element E, Area A, Item I ");
		sql.append(" FROM ");
		sql.append(" Element E, Item I ");

		if ( !qvo.getIsTradeMatrix()) {
			sql.append(",Area A ,Data D ");
		}
		else {
			// RA + ReporterArea
			// PA + PartnerArea
			sql.append(",Area RA ,Area PA, TradeMatrix D ");
		}

		if ( qvo.getAddFlag() ) {
			sql.append(", Flag F ");
		}


//		sql.append(" FROM ");
//
//		if ( !qvo.getIsTradeMatrix()) {
//			sql.append("Data D ");
//		}
//		else {
//			// RA + ReporterArea
//			// PA + PartnerArea
//			sql.append("TradeMatrix D ");
//		}
//
//		if ( qvo.getAddFlag() ) {
//			sql.append(", Flag F ");
//		}
//
//		// append tables if needed
//		// TEST if Element, Item and Area should be Added
//		// Elements contains also units...so if unites should be added
//		sql.append(addTables(qvo, addUnitName));
		
		return sql.toString();
	}
	
	private static String addTables(DWFAOSTATQueryVO qvo, Boolean addUnitName) {
		StringBuffer sql = new StringBuffer();
		
		HashMap<String, String> tablesAdded = new HashMap<String, String>();
		
		// for the units..
		if ( addUnitName ) {
			sql.append(", " + FAOSTATUtils.tablesMapping.get("E"));
			tablesAdded.put( FAOSTATUtils.tablesMapping.get("E"),  FAOSTATUtils.tablesMapping.get("E"));
		}
		
		for(String select : qvo.getSelects() ) {
			if ( addTable(select)) {
				LOGGER.info("select: " + select);
				try {
					String mapping = select.substring(0, select.indexOf("."));
					
	//				String mapping = select.substring(0, select.toLowerCase().indexOf("name"));
					LOGGER.info("mapping: " + mapping);
					
	//				mapping = mapping.toLowerCase();
					LOGGER.info("FAOSTATUtils.tablesMapping.get(mapping): " +FAOSTATUtils.tablesMapping.get(mapping));
	
					if ( !tablesAdded.containsKey(FAOSTATUtils.tablesMapping.get(mapping))) {
						sql.append(", " + FAOSTATUtils.tablesMapping.get(mapping));
						tablesAdded.put(FAOSTATUtils.tablesMapping.get(mapping), FAOSTATUtils.tablesMapping.get(mapping));
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		// ItemsLevel TODO: do it in a nicer way...
		if ( !qvo.getItemsLevel().isEmpty()) {
			LOGGER.info("qvo.getItemsLevel(): " + qvo.getItemsLevel());
			sql.append(", " + FAOSTATUtils.tablesMapping.get("I"));
			tablesAdded.put( FAOSTATUtils.tablesMapping.get("I"),  FAOSTATUtils.tablesMapping.get("I"));
		}
		
		
		
		LOGGER.info("TABLES ADDED: " +tablesAdded);
		
		LOGGER.info("TABLES sql.toString(): " +sql.toString());
		
		return sql.toString();
	}
	

	private static String addLabesJoins(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();    
		 if ( !qvo.getIsTradeMatrix() ) {
			 sql.append(" AND D.AreaCode = A.AreaCode ");
		 }
		 else {
			 sql.append(" AND D.ReporterAreaCode = RA.AreaCode ");
			 sql.append(" AND D.PartnerAreaCode = PA.AreaCode ");
		 }
		 
		 sql.append(" AND D.ItemCode = I.ItemCode ");	
		 sql.append(" AND D.ElementCode = E.ElementCode " );

		return sql.toString();
	}
	
	private static boolean addTable(String select) {
		Boolean check = false;
		if ( select.toLowerCase().contains("name")) {
			return true;
		}
		return check;
	}
	
	private static String getNestedQuery(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
//		LOGGER.info("getNestedQuery:");
		
		sql.append("SELECT ");
		if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
			sql.append(" TOP ").append(qvo.getNestedLimit()).append(" ");
		}		


		sql.append(" " + qvo.getNestedLimitField() + " ");
				
						
		/** FROM **/
		sql.append(" FROM Data D, Element E, Area A, Item I ");
			
		if ( qvo.getAddFlag() ) {
			sql.append(", Flag F ");
		}

		/** WHERE **/
		sql.append(getWheres(qvo));
		
//		LOGGER.info("GET Joins .." );
		sql.append( addJoins(qvo, qvo.getAddFlag()));
		
//		LOGGER.info("GET Label Joins .." );
		sql.append( addLabesJoins(qvo));
			
		sql.append(addRegionalConditions(qvo));
				
		sql.append(" GROUP BY " + qvo.getNestedLimitField());

		sql.append(addOrderByNestedClause(qvo));
		
		// TODO: shownull values?

		/// launch the query
		qvo.setSql(sql.toString());
		
		List<String> values = querySingleResult(qvo);
		
		StringBuffer result = new StringBuffer();
		
		int i =0;
		for(String value : values ) {
			result.append("'" + value + "'");
			if( i < values.size() -1 ) {
				result.append(",");
			}
			i++;
		}
		
		return result.toString();
	}
	
	private static List<String> querySingleResult(DWFAOSTATQueryVO qvo) throws FenixException {
		List<String> values = new ArrayList<String>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				values.add(rs.getString(1));
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

		return values;
	}

	
	private static String getTimerange(DWFAOSTATQueryVO qvo) {
		Integer years = 0;
		if (qvo.getMaxDateLimit()!=null && qvo.getTimeSpan()!=null) {

			Integer maxDate = Integer.valueOf(qvo.getMaxDateLimit());
			Integer timeSpan = Integer.valueOf(qvo.getTimeSpan());

			years = maxDate - timeSpan;

		 } else {
			 if ( !qvo.getYears().isEmpty() ) {
				 years = qvo.getYears().size();
			 }
		 }
//		LOGGER.info("YEARS TIMERANGE: " + years);
		return years.toString();
	}
	
	
	public static String buildCalculationQuery(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		CalculationParametersVO calcVO = qvo.getCalculationParametersVO();

		if(calcVO.getCalculationType().equals(FAOSTATAggregationConstant.SUBTRACT)){
			sql.append("SELECT ");	

			sql.append(buildSubtractionQuery(qvo, calcVO.getSubtractionMinuend(), calcVO.getFilterColumn()));
			sql.append(" - ");
			sql.append(buildSubtractionQuery(qvo, calcVO.getSubtractionSubtrahend(), calcVO.getFilterColumn()));

			sql.append(" AS DIFFERENCE");	
		}	 
		
//		LOGGER.info("SQL :" + sql );
		return sql.toString();
	}

	
	
	private static String buildSubtractionQuery(DWFAOSTATQueryVO qvo, List<String> filterValues, FAOSTATTableConstant filterColumn){
		StringBuffer sql = new StringBuffer();
		sql.append(" (SELECT SUM(D.Value) ");
		
		sql.append(" FROM Data D, Element E, Area A, Item I ");

		if ( qvo.getAddFlag() ) {
			sql.append(", Flag F ");
		}

		sql.append(getWheres(qvo, filterColumn, filterValues));

		sql.append( addJoins(qvo, qvo.getAddFlag()));

		sql.append( addLabesJoins(qvo));

		sql.append( addRegionalConditions(qvo));


		sql.append(") ");	

		return sql.toString();
	}
	
	/** TODO: add the getReportedAreas and getPartnerAreas **/
	private static String getWheres(DWFAOSTATQueryVO qvo, FAOSTATTableConstant filterColumn, List<String> filterFields) {
		StringBuffer sql = new StringBuffer();
		sql.append(" WHERE ");

		Boolean addedFirst = false;
		if ( !qvo.getAreas().isEmpty() && !filterColumn.equals(FAOSTATTableConstant.Area)) {
			if ( addedFirst )
				sql.append(" AND " );
			
			sql.append(" D.AreaCode IN (" + getAreas(qvo) + ") ");

			addedFirst = true;
		} else {
			if ( addedFirst )
				sql.append(" AND " );
			
			sql.append(" D.AreaCode IN (" + getAreas(filterFields) + ") ");

			addedFirst = true;
		}


		if ( !qvo.getItems().isEmpty() && !filterColumn.equals(FAOSTATTableConstant.Item)) {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" D.ItemCode IN (" + getItems(qvo) + ") ");

			addedFirst = true;
		} else {
			if ( addedFirst )
				sql.append(" AND " );
			
			sql.append(" D.ItemCode IN (" + getItems(filterFields) + ") ");

			addedFirst = true;
		}

		if ( !qvo.getElements().isEmpty() && !filterColumn.equals(FAOSTATTableConstant.Element) ) {
			if ( addedFirst )
				sql.append(" AND " ); 

			sql.append(" D.ElementCode IN (" + getElements(qvo) + ") ");
			addedFirst = true;
		} else {
			sql.append(" D.ElementCode IN (" + getElements(filterFields) + ") ");

			addedFirst = true;
		}

		if ( !qvo.getYears().isEmpty()) {
			if ( addedFirst )
				sql.append(" AND " ); 

			sql.append(" D.Year IN (" + getYears(qvo) + ") ");
			addedFirst = true;
		}

		return sql.toString();
	}
	
	private static String getWheres(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" WHERE ");
		
		 Boolean addedFirst = false;
		 
		 // TODO: this check on the trade matrix is not good for the joins..if needed remove it
		 if ( !qvo.getIsTradeMatrix() ) {
			 if ( !qvo.getAreas().isEmpty() ) {
				 sql.append(" D.AreaCode IN (" + getAreas(qvo) + ") ");	
				 addedFirst = true;
			 }
			 // TODO: add also to the trade matrix?? 
			 if ( !qvo.getAreasBlacklisted().isEmpty() ) {
				 if ( addedFirst ) {
					 sql.append(" AND " );
				 }
				 sql.append(" D.AreaCode NOT IN (" + getFilters(qvo.getAreasBlacklisted()) + ") ");	
				 addedFirst = true;
			 }
		 }

		 if ( qvo.getIsTradeMatrix() ) {
			 if ( !qvo.getReportedAreas().isEmpty() ) {
				 if ( addedFirst ) 
					 sql.append(" AND " );
				 
				 sql.append(" D.ReporterAreaCode IN (" + getFilters(qvo.getReportedAreas()) + ") ");
				 addedFirst = true;
			 }
			 
			 if ( !qvo.getPartnerAreas().isEmpty() ) {
				 if ( addedFirst )
					 sql.append(" AND " );
				 
				 sql.append(" D.PartnerAreaCode IN (" + getFilters(qvo.getPartnerAreas()) + ") ");	
				 addedFirst = true;
			 }
		 }
		
		 if ( !qvo.getItems().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " );
			 
			 sql.append(" D.ItemCode IN (" + getItems(qvo) + ") ");
			 addedFirst = true;
		 }
		 
		 if ( !qvo.getItemsLevel().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " );
			 
			 sql.append(" I.ItemLevel IN (" + getFilters(qvo.getItemsLevel()) + ") ");
			 addedFirst = true;
		 }

		 
		 if ( !qvo.getElements().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " ); 
			 
			 sql.append(" D.ElementCode IN (" + getElements(qvo) + ") ");
			 addedFirst = true;
		 }
		 
		 if ( !qvo.getElementsList().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " ); 
		
			 sql.append(" D.ElementListCode IN (" + 	getFilters(qvo.getElementsList()) +  ") ");
			 addedFirst = true;
		 }
		 
		 if ( !qvo.getDomains().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " ); 

			 sql.append(" D.DomainCode IN (" + getDomain(qvo) + ") ");
			 addedFirst = true;
		 }
		 
		 if (qvo.getMaxDateLimit()!=null && qvo.getTimeSpan()!=null) {
			 if ( addedFirst )
				 sql.append(" AND " ); 

			 sql.append(addDateRange(qvo));
			 addedFirst = true;
		 } else {
			 if ( !qvo.getYears().isEmpty() ) {
				 if ( addedFirst )
					 sql.append(" AND " ); 

				 sql.append(" D.Year IN (" + getYears(qvo) + ") ");
				 addedFirst = true;
			 }
		 }
		 
		
		// condition on the value
//		if (qvo.getValueCondition() != null) {
//			
//			sql.append(" AND D.Value ").append(qvo.getValueCondition().getSymbol()).append(" ").append(qvo.getValueTreshold()).append(" ");
//			
//		} 
			
		if ( !qvo.getShowNull() ) {
			
			sql.append(addNotShowNullValues());
		}
		
//		LOGGER.info("SQL: " + sql);
		return sql.toString();
	}
	
	private static String addNotShowNullValues() {
		StringBuffer sql = new StringBuffer();

		sql.append(" AND D.Value <> 0 ");
		
//		LOGGER.info("NO NULL VALUES sql: " + sql);
		return sql.toString();
	}
	
	private static String addOrderByClause(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		if(!qvo.getRunMaxDateQuery()) {
			if ( !qvo.getOrderBys().isEmpty() ) {
				 sql.append(" ORDER BY ");
				 for (int i = 0 ; i < qvo.getOrderBys().size() ; i++) {
						sql.append(qvo.getOrderBys().get(i));
						
						if (i < qvo.getOrderBys().size() - 1)
							sql.append(", ");
					}
				 
			   	 sql.append(" "+qvo.getSortingOrder()+" "); 
//				 LOGGER.info("ORDER BY: sql: " + sql);
			}
		}

		return sql.toString();
	}
	
	private static String addOrderByNestedClause(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		if(!qvo.getRunMaxDateQuery()) {
			 sql.append(" ORDER BY ");
			 
			 if ( !qvo.getOrderBysNested().isEmpty() ) {
				 for (int i = 0 ; i < qvo.getOrderBysNested().size() ; i++) {
						sql.append(qvo.getOrderBysNested().get(i));
						
						if (i < qvo.getOrderBysNested().size() - 1)
							sql.append(", ");
					}
				 
			   	 sql.append(" "+qvo.getSortingOrderNested()+" "); 
			 }
			 else {
				 // DEFAULT
				sql.append(qvo.getAggregationType() +"(D.Value) "+ qvo.getSortingOrder() +" ");
			 }
			 
		}
		//else {
			//sql.append(" ORDER BY SUM(D.Value) "+ qvo.getSortingOrder() +" ");
			//sql.append(" ORDER BY " + qvo.getAggregationType()+ "(D.Value) "+ qvo.getSortingOrder() +" ");
		//}
//		LOGGER.info("ORDER BY: sql: " + sql);
		
		return sql.toString();
	}
	
	
	private static String addGroupBYClause(DWFAOSTATQueryVO qvo, Boolean addUnitName) {
		
		 StringBuffer sql = new StringBuffer();
		 sql.append(" GROUP BY ");
		 
		 for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
				sql.append(qvo.getSelects().get(i));
				if (i < qvo.getSelects().size() - 1)
					sql.append(", ");
			}
		 
		 if ( addUnitName ) {
			 sql.append( ", E.UnitName" + qvo.getLanguage() + " ");
		 }
		 
		return sql.toString();
	} 
	
	
	private static String addGroupBYClause(Map<String, String> groupBys) {
		
		 StringBuffer sql = new StringBuffer();
		 sql.append(" GROUP BY ");
		 
		 int i=0;
		 for (String key : groupBys.keySet()) {
				sql.append(groupBys.get(key));
				if (i < groupBys.size() - 1)
					sql.append(", ");
				i++;
			}
	 		 
		return sql.toString();
	} 
	
	/** TODO: skip the last value that by default is the value 
	 * 			Make it more dynamic
	 *  **/
//	private static String addGroupBYClauseJoin(DWFAOSTATQueryVO qvo) {
//		
//		 StringBuffer sql = new StringBuffer();
//		 sql.append(" GROUP BY ");
//		 
//		 for (int i = 0 ; i < qvo.getSelects().size() -1; i++) {
//				sql.append(qvo.getSelects().get(i));
//				if (i < qvo.getSelects().size() - 2)
//					sql.append(", ");
//			}
//		 		 
//		return sql.toString();
//	} 
	
	private static String addRegionalConditions(DWFAOSTATQueryVO qvo) {
		
		 StringBuffer sql = new StringBuffer();
		 /** TODO: change it in a nicer way... **/
		 // country or regional level 
		 if ( qvo.getIsCountryLevel() && qvo.getIsRegionLevel()) {
			 // do nothing
		 }
		 //TODO: for the trade matrix there are WORLD AND AREA LEVELS?
		 else if ( qvo.getIsCountryLevel() && !qvo.getIsRegionLevel()) {
			 if ( !qvo.getIsTradeMatrix() ) {
				 sql.append(" AND ( A.AreaLevel = 5 ) ");
//				 sql.append(" AND ( D.AreaCode like '_' OR D.AreaCode like '__' OR D.AreaCode like '___') ");
			 }
			 else  {
				 sql.append(" AND ( D.ReporterAreaCode like '_' OR D.ReporterAreaCode like '__' OR D.ReporterAreaCode like '___') ");
				 sql.append(" AND ( D.PartnerAreaCode like '_' OR D.PartnerAreaCode like '__' OR D.PartnerAreaCode like '___') ");
			 }
		 }else if ( qvo.getIsRegionLevel() && !qvo.getIsCountryLevel()) {
			 if ( !qvo.getIsTradeMatrix() ) {

				 sql.append(" AND ( A.AreaLevel = 10 OR A.AreaLevel = 15 ) ");
//				 sql.append(" AND ( D.AreaCode like '____') ");
			 }
			 else {
				 sql.append(" AND ( D.ReporterAreaCode like '____') ");
				 sql.append(" AND ( D.PartnerAreaCode like '____') ");
			 }
		 }
		 return sql.toString();
	} 
	
	public static String addSelects(List<String> selects){
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < selects.size() ; i++) {
			sb.append(selects.get(i));
			if (i < selects.size() - 1)
				sb.append(", ");
		}
		return sb.toString();
	}
	
	public static String getAreas(DWFAOSTATQueryVO p) {
		String area_codes = "";
		Object[] areas = p.getAreas().keySet().toArray();
		for (int i = 0 ; i < areas.length ; i++) {
			area_codes += "'" + areas[i].toString() + "'";
			if (i < areas.length - 1)
				area_codes += ", ";
		}
		return area_codes;
	}
	
	public static String getAreas(List<String> p) {
		String area_codes = "";
		for (int i = 0 ; i < p.size() ; i++) {
			area_codes += "'" + p.get(i) + "'";
			if (i < p.size() - 1)
				area_codes += ", ";
		}
		return area_codes;
	}
	
	public static String getItems(DWFAOSTATQueryVO p) {
		String item_codes = "";
		Object[] items = p.getItems().keySet().toArray();
		for (int i = 0 ; i < items.length ; i++) {
			item_codes += "'" + items[i].toString() + "'";
			if (i < items.length - 1)
				item_codes += ", ";
		}
		return item_codes;
	}
	
	/** TODO: did it for the DEMO **/
	public static String getItemsLike(DWFAOSTATQueryVO p) {
		String item_codes = "";
		Object[] items = p.getItems().keySet().toArray();
		for (int i = 0 ; i < items.length ; i++) {
			item_codes += "'%" + items[i].toString() + "%'";
			if (i < items.length - 1)
				item_codes += ", ";
		}
		return item_codes;
	}
	
	
	
	public static String getItems(List<String> p) {
		String item_codes = "";
		for (int i = 0 ; i < p.size() ; i++) {
			item_codes += "'" + p.get(i) + "'";
			if (i < p.size() - 1)
				item_codes += ", ";
		}
		return item_codes;
	}
	
	public static String getElements(DWFAOSTATQueryVO p) {
		String elements_codes = "";
		Object[] elements = p.getElements().keySet().toArray();
		for (int i = 0 ; i < elements.length ; i++) {
			elements_codes += "'" + elements[i].toString() + "'";
			if (i < elements.length - 1)
				elements_codes += ", ";
		}
		return elements_codes;
	}
	

	public static String getElementsListNestedQuery(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT E.ElementCode ");
		sql.append("FROM  Element E, DomainItemElementUnit DIEU ");
		sql.append("WHERE ");
		sql.append(" E.ElementCode = DIEU.ElementCode ");
		sql.append("AND E.UnitCode = DIEU.UnitCode ");
		sql.append("AND E.ElementListCode = DIEU.ElementListCode ");
		sql.append("AND E.Multiplier = DIEU.Multiplier ");
		
		// Domains
		if ( !qvo.getDomains().isEmpty() )
			sql.append("AND DIEU.DomainCode IN (" + getDomain(qvo) + ") ");

		
		// Elements
		sql.append("AND E.ElementListCode IN (" + getFilters(qvo.getElementsList()) +  ") ");
		
		// Items
		if ( !qvo.getItems().isEmpty() ) {
			 sql.append("AND DIEU.ItemCode IN (" + getItems(qvo) + ") ");
		 }
		
		sql.append(" GROUP BY E.ElementCode ");
		
		return sql.toString();
	}
	
	
	
	
	public static String getElements(List<String> p) {
		String elements_codes = "";
		for (int i = 0 ; i < p.size() ; i++) {
			elements_codes += "'" + p.get(i) + "'";
			if (i < p.size() - 1)
				elements_codes += ", ";
		}
		return elements_codes;
	}
	
	public static String getYears(DWFAOSTATQueryVO p) {
		String years_codes = "";
		Object[] years = p.getYears().keySet().toArray();
		for (int i = 0 ; i < years.length ; i++) {
			years_codes += "'" + years[i].toString() + "'";
			if (i < years.length - 1)
				years_codes += ", ";
		}
		return years_codes;
	}
	
	public static String getYears(List<String> p) {
		String years_codes = "";
		for (int i = 0 ; i < p.size() ; i++) {
			years_codes += "'" + p.get(i) + "'";
			if (i < p.size() - 1)
				years_codes += ", ";
		}
		return years_codes;
	}
	
	
	/** TODO: More domanins ?? **/
	public static String getDomain(DWFAOSTATQueryVO qvo) {
//		Object[] domains = qvo.getDomains().keySet().toArray();
//		return domains[0].toString().trim();
		String area_codes = "";
		Object[] areas = qvo.getDomains().keySet().toArray();
		for (int i = 0 ; i < areas.length ; i++) {
			area_codes += "'" + areas[i].toString() + "'";
			if (i < areas.length - 1)
				area_codes += ", ";
		}
		return area_codes;
	}
	
	public static String buildGoogleDataset(String conversiontablename, List<String> faostatCodes, String language) {
		// limit query


		
		StringBuilder sb = new StringBuilder();
//		sb.append(" SELECT faostat, iso2, label ");
		sb.append(" SELECT areacode, iso2, areaname"+ language + " ");
		sb.append(" FROM " + conversiontablename + " ");
		
		sb.append(" WHERE ");
		
		sb.append(addSQLCondition("areacode", faostatCodes, false));
		
		sb.append(" AND iso2 is not null ");
		// WHERE ISO2 is not null!!!

		return sb.toString();
	}
	
	private static StringBuffer addSQLCondition(String column, List<String> content, Boolean addAND) {
		StringBuffer sqlQuery = new StringBuffer();
		if ( addAND )
			sqlQuery.append(" AND ( ");
		else 
			sqlQuery.append(" ( ");
		for(int i=0; i < content.size(); i++) {
			if ( i != 0 )
				sqlQuery.append("OR ");
			
			sqlQuery.append( column + " = '"+ content.get(i) + "' ");
		}
		sqlQuery.append(" )");
		
		return sqlQuery;
	}
	
	public static String getFilters(Map<String, String> filter) {
		String codes = "";
		Object[] f = filter.keySet().toArray();
		for (int i = 0 ; i < f.length ; i++) {
			codes += "'" + f[i].toString() + "'";
			if (i < f.length - 1)
				codes += ", ";
		}
		return codes;
	}
	
	public static String getFilterValues(List<String> filter) {
		String codes = "";
		for (int i = 0 ; i < filter.size() ; i++) {
			codes += "'" + filter.get(i) + "'";
			if (i < filter.size() - 1)
				codes += ", ";
		}
		return codes;
	}
	
	
	public static String getGroups(DWFAOSTATQueryVO p) {
		String group_codes = "";
		Object[] groups = p.getGroups().keySet().toArray();
		for (int i = 0 ; i < groups.length ; i++) {
			group_codes += "'" + groups[i].toString() + "'";
			if (i < groups.length - 1)
				group_codes += ", ";
		}
		return group_codes;
	}

	public static String getPivotFilters(Map<String, String> filter) {
		String codes = "";
		Object[] f = filter.keySet().toArray();
		for (int i = 0 ; i < f.length ; i++) {
//			LOGGER.info("CODE: " + f[i].toString());
			if ( !f[i].toString().equalsIgnoreCase("" )) {
				codes += f[i].toString();
				if (i < f.length - 1)
					codes += ", ";
			}
		}
		return codes;
	}
	
	public static String getLastUpdatedDomains(String lang, String langName) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SET LANGUAGE "+langName);
		sql.append(" SELECT TOP 7 GroupName"+lang + " as groupName, DomainName"+lang+"  as domain, ");
		sql.append(" '('+DATENAME(month, dateupdate) + ' ' + DATENAME(year, dateupdate) + ')' as last_updated ");
		sql.append(" FROM Domain ");
		sql.append(" ORDER BY dateupdate DESC, DomainName"+lang+" ASC ");
		sql.append(" SET LANGUAGE English");
		
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
}