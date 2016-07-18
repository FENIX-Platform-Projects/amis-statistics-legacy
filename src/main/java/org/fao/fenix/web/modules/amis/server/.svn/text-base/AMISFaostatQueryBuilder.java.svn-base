package org.fao.fenix.web.modules.amis.server;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.constants.AMISTableConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;

public class AMISFaostatQueryBuilder extends AMISQueryBuilder {

	private final static Logger LOGGER = Logger.getLogger(AMISFaostatQueryBuilder.class);
	
	// PIVOT TABLE
	public static String pivotTable(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		sql.append("exec Diss..getPivotedSummariesW ");
		
		// set the filters on dimensions
		sql.append(getDimensions(qvo));
		
		// set the pivoted columns
		sql.append(getPivotedColumns(qvo));

		
		sql.append(",@databasename='Warehouse'	" +
				"	,@itemtablename='Item'	" +
				"	,@areatablename='Area'		" +
				"   ,@datatablename='Data'	" +
				"	,@unittablename='Year'	" +
				"	,@extratablename='Element'	" +
				"	,@subjectsetname=''");
		
		sql.append(getOptions(qvo));
		
//		sql.append(",@showaggregators=0		,@tablelanguage='E'		,@officialflags=''		,@thousandsseparator=''		,@decimalseparator='.'		,@ShowDec='2'		,@showNull=0");
		
		
		LOGGER.info("SQL: " + sql);
		return sql.toString();	
	}	
	
	private static String getDimensions(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		// countries
		sql.append("@dimension1='" + getPivotFilters( qvo.getAreas() ) +"' ");
		
		// years 
		sql.append(",@dimension2='" + getPivotFilters( qvo.getYears() ) +"' ");
		
		// items 
		sql.append(",@dimension3='" + getPivotFilters( qvo.getItems() ) +"' ");
		
		// elements 
		sql.append(",@dimension4='" + getPivotFilters( qvo.getElements() ) +"' ");
//		sql.append(",@dimension4='" + getPivotFilters( qvo.getElementsList() ) +"' ");
		
		return sql.toString();	
	}
	
	private static String getPivotedColumns(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
//		sql.append(",@pivotdimension1='countries' ");
		sql.append(",@pivotdimension1='" + qvo.getNestedby() + "' ");
		
//		sql.append(",@pivotdimension2='years' ");
		sql.append(",@pivotdimension2='" + qvo.getxAxis() + "' ");
				
		// y1Axis, y2Axis TODO: and the codes columns?? 
//		sql.append("@extracolumns='ccodes,items,icodes,extra'");
		sql.append(",@extracolumns='" + qvo.getY1Axis() + "," + qvo.getY2Axis() + "'");


		
		return sql.toString();	
	}
	
	private static String getOptions(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		// LIMIT
		if( qvo.getLimit() != null && qvo.getLimit() != 0) {
			sql.append(",@maxrecords=" + qvo.getLimit() + "");
		}	
		else {
		sql.append(",@maxrecords=0");
//			if(qvo.getLimit() == 0)
//			{
//				sql.append(",@maxrecords=0");
//			}
//			else
//			{
//				sql.append(",@maxrecords=-9223372036854775808");
//			}
		}
//		if( qvo.getLimit() != null) {
//			if(qvo.getLimit() != 0)
//			{
//				sql.append(",@maxrecords=" + qvo.getLimit() + "");
//			}
//			else
//			{
//				sql.append(",@maxrecords=0");
//			}
//				
//		}	
//		else {
//				sql.append(",@maxrecords=0");
//		}
		
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


		
		return sql.toString();	
	}
	
	private static String getBooleanValue(Boolean value) {
		
		if ( value )
			return "1";
		else
			return "0";
		
	} 

	public static String getPivotFilters(Map<String, String> filter) {
		String codes = "";
		Object[] f = filter.keySet().toArray();
		for (int i = 0 ; i < f.length ; i++) {
			codes += f[i].toString();
			if (i < f.length - 1)
				codes += ", ";
		}
		return codes;
	}

	public static String addLabelJoins(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		 sql.append(" AND D.AreaCode = A.AreaCode ");
		 sql.append(" AND D.ItemCode = I.ItemCode ");
	     sql.append(" AND D.ItemCode = I.ItemCode ");
		 sql.append(" AND D.ElementCode = E.ElementCode " );
		return sql.toString();
	}
	
		public static String buildDivisionQuery(AMISQueryVO qvo, List<String> filterValues, AMISTableConstants filterColumn){
		StringBuffer sql = new StringBuffer();
		sql.append(" (SELECT SUM(D.Value) ");

		sql.append(" FROM Data D, Element E, Area A, Item I, DomainArea DA ");

		if ( qvo.getAddFlag() ) {
			sql.append(", Flag F ");
		}

		LOGGER.info("GET Wheres .." );
		sql.append(getWheres(qvo, filterColumn, filterValues));

		/** TODO: add if **/
		LOGGER.info("GET Joins .." );
		sql.append( addJoins(qvo, qvo.getAddFlag()));

		LOGGER.info("GET Label Joins .." );
		sql.append( addLabelJoins(qvo));

		LOGGER.info("GET Regional .." );
		sql.append( addRegionalConditions(qvo));


		sql.append(") ");

		return sql.toString();
	}
	
	public static String getMeasurementUnits(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" FROM Element E " );
		sql.append(" WHERE  " );
		
		// TODO: added boolean
		 if ( !qvo.getElements().isEmpty() ) {
			 sql.append(" E.ElementCode IN (" + getElements(qvo) + ") ");
		 }
		 else if ( !qvo.getElementsList().isEmpty() ) {
			 sql.append(" E.ElementCode IN (" + getElementsListNestedQuery(qvo) + ") ");
		 }
		 else {
			 LOGGER.info("ELEMENTS EMPTY! ");
			 // return null??
			 return null;
		 }
		 
		sql.append(" GROUP BY E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY E.UnitName" + qvo.getLanguage() + " ");
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String sqlTableQuery(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		if(qvo.getRunMaxDateQuery()){
			sql.append(addSelectMaxDate());		
		} else {
			LOGGER.info("runMaxDateQuery IS FALSE .." );
			sql.append(getSelects(qvo, true));
			// add aggreation /** TODO: dynamic **/
		}
		

		sql.append(" FROM Data D, Element E, Area A, Item I, DomainArea DA ");

		if ( qvo.getAddFlag() ) {
			sql.append(", Flag F ");
		}
	

		sql.append(getWheres(qvo));
		
		 // check if the flag can give problems
		 sql.append(addJoins(qvo, qvo.getAddFlag()));
			 
		 if ( qvo.getAddLabels())
			 sql.append( addLabelJoins(qvo));
			 
		 // REMOVE
//		 sql.append("ORDER BY D.Year DESC, AreaNameE ASC, ItemNameE ASC, ItemNameE ASC  ");
			 
		 if(qvo.getRunMaxDateQuery())
			sql.append(addOrderByMaxDate());		
					 
			LOGGER.info("SQL :" + sql );
		return sql.toString();
	}
	
	public static String addSelectMaxDate() {
		StringBuffer sql = new StringBuffer();   
		sql.append(" SELECT TOP 1 D.Year").append(" ");
//		sql.append(" SELECT MAX(D.Year)").append(" ");
		
		return sql.toString();
	}
	
	static String getWheres(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" WHERE ");
		
		 Boolean addedFirst = false;
		 if ( !qvo.getAreas().isEmpty() ) {
			 sql.append(" D.AreaCode IN (" + getAreas(qvo) + ") ");
						
			 addedFirst = true;
		 }
		
		 if ( !qvo.getItems().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " );
			 
			 sql.append(" D.ItemCode IN (" + getItems(qvo) + ") ");
					
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

//			 sql.append(" D.ElementCode IN (" + getElementsListNestedQuery(qvo) + ") ");
		
			 addedFirst = true;
		 }
		 
		
		 
		 if ( !qvo.getDomains().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " ); 
			 
			// sql.append(" D.DomainCode IN (" + getDomain(qvo) + ") ");
			 String whereString = " D.DomainCode IN (";
				int count=0;
				int dimMinusOne =qvo.getCodeList().size()-1;
				for(String codeString : qvo.getCodeList())
				{
					whereString+=codeString;
					if(count!=dimMinusOne)
					{
						whereString+=",";
					}
					count++;
				}
				whereString += ") ";
				sql.append(whereString);
			 
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
		return sql.toString();
	}

	static String addJoins(AMISQueryVO qvo, Boolean addFlag) {
		StringBuffer sql = new StringBuffer();     	
//		if ( addedFirst )
	     	//sql.append(" AND " );
		

//		 sql.append(" DIE.ItemCode = D.ItemCode  ");
//		 sql.append(" D.ElementCode = DIE.ElementCode " );
//		 sql.append(" AND DIE.DomainCode IN (" + getDomain(qvo) + ") ");
		// sql.append(" DA.DomainCode IN (" + getDomain(qvo) + ") ");
		 sql.append(" AND DA.AreaCode = D.AreaCode  ");
	     	
		 if ( addFlag )
			 sql.append(" AND F.Flag = D.Flag ");
		 
		return sql.toString();
	}
	
	
	/** TODO: More domanins ?? **/
	public static String getDomain(AMISQueryVO qvo) {
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
	
	
	static String addRegionalConditions(AMISQueryVO qvo) {
		
		 StringBuffer sql = new StringBuffer();
		 /** TODO: change it in a nicer way... **/
		 // country or regional level 
		 if ( qvo.getIsCountryLevel() && qvo.getIsRegionLevel()) {
			 // do nothing
		 }
		 else if ( qvo.getIsCountryLevel() && !qvo.getIsRegionLevel()) {
			 sql.append(" AND ( D.AreaCode like '_' OR D.AreaCode like '__' OR D.AreaCode like '___') ");
		 }else if ( qvo.getIsRegionLevel() && !qvo.getIsCountryLevel()) {
			 sql.append(" AND ( D.AreaCode like '____') ");
		 }
		 return sql.toString();
	}
	
	static String addOrderByMaxDate() {
		StringBuffer sql = new StringBuffer();   
		sql.append(" ORDER BY D.Year DESC").append(" ");
//		sql.append(" SELECT MAX(D.Year)").append(" ");
		
		return sql.toString();
	}
		
	public static String getElementsListNestedQuery(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT E.ElementCode ");
		sql.append("FROM  Element E, DomainItemElementUnit DIEU ");
		sql.append("WHERE DIEU.DomainCode IN (" + getDomain(qvo) + ") ");
		sql.append("AND E.ElementCode = DIEU.ElementCode ");
		sql.append("AND E.UnitCode = DIEU.UnitCode ");
		sql.append("AND E.ElementListCode = DIEU.ElementListCode ");
		sql.append("AND E.Multiplier = DIEU.Multiplier ");
		
		// Elements
		sql.append("AND E.ElementListCode IN (" + getFilters(qvo.getElementsList()) +  ") ");
		
		// Items
		if ( !qvo.getItems().isEmpty() ) {
			 sql.append("AND DIEU.ItemCode IN (" + getItems(qvo) + ") ");
		 }
		
		sql.append(" GROUP BY E.ElementCode ");
		
		return sql.toString();
	}
		
	private static String addDateRange(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();  
		sql.append(" (D.Year BETWEEN "+getLowerDateLimit(qvo)+" AND '"+qvo.getMaxDateLimit()+"')").append(" ");
		
		return sql.toString(); 
	}
	
	private static String getLowerDateLimit(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();  
		sql.append(" year(DATEADD(year, -"+qvo.getTimeSpan()+", '"+qvo.getMaxDateLimit()+"'))").append(" ");
		
		return sql.toString(); 
	}
	
}
