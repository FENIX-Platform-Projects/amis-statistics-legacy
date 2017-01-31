package org.fao.fenix.web.modules.amis.server;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.constants.AMISAggregationConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISTableConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISCalculationParametersVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
public class CCBSQueryBuilder {

	private final static Logger LOGGER = Logger.getLogger(CCBSQueryBuilder.class);

    
	public static String sqlFenixTableQuery(AMISQueryVO qvo) {
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
			 sql.append( addLabesJoins(qvo));

		 // REMOVE
//		 sql.append("ORDER BY D.Year DESC, AreaNameE ASC, ItemNameE ASC, ItemNameE ASC  ");

		 if(qvo.getRunMaxDateQuery())
			sql.append(addOrderByMaxDate());

			LOGGER.info("SQL :" + sql );
		return sql.toString();
	}



	private static String getSelects(AMISQueryVO qvo, Boolean withLimitCheck) {
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
				select = select.replace("+FAOSTATConstants.faostatLanguage", AMISConstants.defaultLanguage);
			}

			sql.append(select);


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

	private static String getLowerDateLimit(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" year(DATEADD(year, -"+qvo.getTimeSpan()+", '"+qvo.getMaxDateLimit()+"'))").append(" ");

		return sql.toString();
	}


	private static String addDateRange(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" (D.Year BETWEEN "+getLowerDateLimit(qvo)+" AND '"+qvo.getMaxDateLimit()+"')").append(" ");

		return sql.toString();
	}

	private static String addJoins(AMISQueryVO qvo, Boolean addFlag) {
		StringBuffer sql = new StringBuffer();
//		if ( addedFirst )
	     	sql.append(" AND " );


//		 sql.append(" DIE.ItemCode = D.ItemCode  ");
//		 sql.append(" D.ElementCode = DIE.ElementCode " );
//		 sql.append(" AND DIE.DomainCode IN (" + getDomain(qvo) + ") ");
		 sql.append(" DA.DomainCode IN (" + getDomain(qvo) + ") ");
		 sql.append(" AND DA.AreaCode = D.AreaCode  ");

		 if ( addFlag )
			 sql.append(" AND F.Flag = D.Flag ");

		return sql.toString();
	}

	private static String addLabesJoins(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		 sql.append(" AND D.AreaCode = A.AreaCode ");
		 sql.append(" AND D.ItemCode = I.ItemCode ");
	     sql.append(" AND D.ItemCode = I.ItemCode ");
		 sql.append(" AND D.ElementCode = E.ElementCode " );
		return sql.toString();
	}



	public static String buildQuery(AMISQueryVO qvo, Boolean isRunmaxQuery) {
		StringBuffer sql = new StringBuffer();

		if(qvo.getRunMaxDateQuery()){
			sql.append(addSelectMaxDate());
		} else {
			LOGGER.info("runMaxDateQuery IS FALSE .." );
			sql.append(getSelects(qvo, true));

//			sql.append( ", " + qvo.getAggregationType() +"(D.Value) ");
			sql.append( ", ROUND(" + qvo.getAggregationType() +"(D.Value), 2) ");
		}

		sql.append(" FROM Data D, Element E, Area A, Item I, DomainArea DA ");

		if ( qvo.getAddFlag() ) {
			sql.append(", Flag F ");
		}

		LOGGER.info("GET Wheres .." );
		sql.append(getWheres(qvo));



			 /** TODO: add if **/
		LOGGER.info("GET Joins .." );
			 sql.append( addJoins(qvo, qvo.getAddFlag()));

			 LOGGER.info("GET Label Joins .." );
			 sql.append( addLabesJoins(qvo));

			 LOGGER.info("GET Regional .." );
			 sql.append( addRegionalConditions(qvo));


			 /** this is the subquery if the limit exists **/
			 if ( !isRunmaxQuery ) {
				if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
					LOGGER.info("CREATING THE NESTED QUERY ..");
					// add the other query

					sql.append(" AND " + qvo.getNestedLimitField() + " IN ( ");
						sql.append("SELECT ");
						if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
							sql.append(" TOP ").append(qvo.getNestedLimit()).append(" ");
						}


						sql.append(" " + qvo.getNestedLimitField() + " ");


						/** FROM **/
						sql.append(" FROM Data D, Element E, Area A, Item I, DomainArea DA ");

						if ( qvo.getAddFlag() ) {
							sql.append(", Flag F ");
						}


						/** WHERE **/
						sql.append(getWheres(qvo));

						LOGGER.info("GET Joins .." );
						sql.append( addJoins(qvo, qvo.getAddFlag()));

						 LOGGER.info("GET Label Joins .." );
						 sql.append( addLabesJoins(qvo));


						sql.append(addRegionalConditions(qvo));

						sql.append(" GROUP BY " + qvo.getNestedLimitField());

						sql.append(addOrderByClause(qvo));
//						sql.append(" ORDER BY " + qvo.getAggregationType() +"(D.Value) "+ qvo.getSortingOrder() +" ");

					sql.append(" ) ");
				}
			 }


			 if(!qvo.getRunMaxDateQuery())
				 sql.append(addGroupBYClause(qvo));

			 LOGGER.info("GET Order By .." );
			 sql.append(addOrderByClause(qvo));

			// ORDER BY for the max date
			if(qvo.getRunMaxDateQuery()){
				sql.append(addOrderByMaxDate());
			}
//			 sql.append(" ORDER BY SUM(D.Value) "+ qvo.getSortingOrder() +" ");

				LOGGER.info("SQL :" + sql );
		return sql.toString();
	}


	public static String buildNestedQuery(AMISQueryVO qvo) {

		LOGGER.info("BUILDING NESTED QUERY");
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
			sql.append(" TOP ").append(qvo.getNestedLimit()).append(" ");
		}


		sql.append(" " + qvo.getNestedLimitField() + " ");


		/** FROM **/
		sql.append(" FROM Data D ");


		/** WHERE **/
		sql.append(getWheres(qvo));


		sql.append(addRegionalConditions(qvo));

		sql.append(" GROUP BY " + qvo.getNestedLimitField());

		 sql.append(addOrderByClause(qvo));
//		sql.append(" ORDER BY " + qvo.getAggregationType() +"(D.Value) "+ qvo.getSortingOrder() +" ");

		LOGGER.info("SQL --> BUILDING NESTED QUERY: " + sql.toString());
		return sql.toString();
	}

	public static String buildCalculationQuery(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		AMISCalculationParametersVO calcVO = qvo.getCalculationParametersVO();

		if(calcVO.getCalculationType().equals(AMISAggregationConstants.SUBTRACT)){
			sql.append("SELECT ");

			sql.append(buildSubtractionQuery(qvo, calcVO.getSubtractionMinuend(), calcVO.getFilterColumn()));
			sql.append(" - ");
			sql.append(buildSubtractionQuery(qvo, calcVO.getSubtractionSubtrahend(), calcVO.getFilterColumn()));

			sql.append(" AS DIFFERENCE");
		}

		LOGGER.info("SQL :" + sql );
		return sql.toString();
	}



	private static String buildSubtractionQuery(AMISQueryVO qvo, List<String> filterValues, AMISTableConstants filterColumn){
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
		sql.append( addLabesJoins(qvo));

		LOGGER.info("GET Regional .." );
		sql.append( addRegionalConditions(qvo));


		sql.append(") ");

		return sql.toString();
	}







	public static String getMeaurementUnits(AMISQueryVO qvo) {
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

	private static String getWheres(AMISQueryVO qvo, AMISTableConstants filterColumn, List<String> filterFields) {
		StringBuffer sql = new StringBuffer();
		sql.append(" WHERE ");

		Boolean addedFirst = false;
		if ( !qvo.getAreas().isEmpty() && !filterColumn.equals(AMISTableConstants.Area)) {
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


		if ( !qvo.getItems().isEmpty() && !filterColumn.equals(AMISTableConstants.Item)) {
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

		if ( !qvo.getElements().isEmpty() && !filterColumn.equals(AMISTableConstants.Element) ) {
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

	private static String getWheres(AMISQueryVO qvo) {
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
		return sql.toString();
	}

	private static String addOrderByClause(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		if(!qvo.getRunMaxDateQuery()) {
			 sql.append(" ORDER BY ");
			 for (int i = 0 ; i < qvo.getOrderBys().size() ; i++) {
					sql.append(qvo.getOrderBys().get(i));

					if (i < qvo.getOrderBys().size() - 1)
						sql.append(", ");
				}

		   	 sql.append(" "+qvo.getSortingOrder()+" ");

		}
		//else {
			//sql.append(" ORDER BY SUM(D.Value) "+ qvo.getSortingOrder() +" ");
			//sql.append(" ORDER BY " + qvo.getAggregationType()+ "(D.Value) "+ qvo.getSortingOrder() +" ");
		//}
		LOGGER.info("ORDER BY: sql: " + sql);

		return sql.toString();
	}


	private static String addGroupBYClause(AMISQueryVO qvo) {

		 StringBuffer sql = new StringBuffer();
		 sql.append(" GROUP BY ");

		 for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
				sql.append(qvo.getSelects().get(i));
				if (i < qvo.getSelects().size() - 1)
					sql.append(", ");
			}

		return sql.toString();
	}

	private static String addRegionalConditions(AMISQueryVO qvo) {

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

	public static String addSelects(List<String> selects){
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i < selects.size() ; i++) {
			sb.append(selects.get(i));
			if (i < selects.size() - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

	public static String getAreas(AMISQueryVO p) {
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

	public static String getItems(AMISQueryVO p) {
		String item_codes = "";
		Object[] items = p.getItems().keySet().toArray();
		for (int i = 0 ; i < items.length ; i++) {
			item_codes += "'" + items[i].toString() + "'";
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

	public static String getElements(AMISQueryVO p) {
		String elements_codes = "";
		Object[] elements = p.getElements().keySet().toArray();
		for (int i = 0 ; i < elements.length ; i++) {
			elements_codes += "'" + elements[i].toString() + "'";
			if (i < elements.length - 1)
				elements_codes += ", ";
		}
		return elements_codes;
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




	public static String getElements(List<String> p) {
		String elements_codes = "";
		for (int i = 0 ; i < p.size() ; i++) {
			elements_codes += "'" + p.get(i) + "'";
			if (i < p.size() - 1)
				elements_codes += ", ";
		}
		return elements_codes;
	}

	public static String getYears(AMISQueryVO p) {
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

	public static String buildGoogleDataset(String conversiontablename, List<String> faostatCodes) {
		// limit query



		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT faostat, iso2, label ");
		sb.append(" FROM " + conversiontablename + " ");

		sb.append(" WHERE ");

		sb.append(addSQLCondition("faostat", faostatCodes, false));

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


	public static String getGroups(AMISQueryVO p) {
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
			codes += f[i].toString();
			if (i < f.length - 1)
				codes += ", ";
		}
		return codes;
	}
}