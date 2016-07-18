package org.fao.fenix.web.modules.adam.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

public class ADAMQueryBuilder {
	
	private final static Logger LOGGER = Logger.getLogger(ADAMQueryBuilder.class);
	
	
	/** TODO: ADD NULL CONDITION TO column_quantity 
	 * 		  AND column_quantity is not null 
	 * **/
	
	
	public static String buildAgrgegationQuery(ADAMQueryVO vo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		if (!vo.getAggregations().isEmpty()) {
			int counter = 0;
			for (String column : vo.getAggregations().keySet()) {
				sb.append(buildAggregationFunction(vo.getAggregations().get(column), column, vo.getAggregations().get(column)+counter ));
				
				//				sb.append(vo.getAggregations().get(column));
//				sb.append("(");
//				sb.append(column);
//				sb.append(") AS ");
//				sb.append(vo.getAggregations().get(column));
			if (counter < vo.getAggregations().size() - 1)
				sb.append(", ");
				counter++;
			}
		}
		sb.append(" FROM ");

		if(!vo.getAggregatedViewNames().isEmpty()){
			for (int i = 0 ; i < vo.getAggregatedViewNames().size() ; i++) {
				sb.append(vo.getAggregatedViewNames().get(i));
				if (i < vo.getAggregatedViewNames().size() - 1)
					sb.append(", ");
		  }
		} else {
			for (int i = 0 ; i < vo.getTableNames().size() ; i++) {
				sb.append(vo.getTableNames().get(i));
				if (i < vo.getTableNames().size() - 1)
					sb.append(", ");
			}
		}

		sb.append(" WHERE ");

		int j=0; 
		for(String filter : vo.getWheres().keySet()) {
			if ( j == 0 )
				sb.append(addSQLCondition(filter, vo.getWheres().get(filter), false));
			else
				sb.append(addSQLCondition(filter, vo.getWheres().get(filter), true));
				j++;
			}
			
//		int j=0; asd
//		if (!vo.getWheres().isEmpty()) {
//			for (String column : vo.getWheres().keySet()) {
//				if ( j != 0)
//					sb.append("AND ");
//				sb.append("( ");
//				int i=0;
//				for (String data : vo.getWheres().get(column)) {
//					if ( i !=0 )
//						sb.append(" OR ");
//					sb.append(column);
//					sb.append(" = ");
//					sb.append("'");
//					sb.append(data);
//					sb.append("' ");
//					i++;
//				}
//				sb.append(") ");
//				j++;
//			}
//			
//		}
		if (!vo.getLikes().isEmpty()) {
			for (String column : vo.getLikes().keySet()) {
				sb.append("AND ");
				sb.append("( ");
				int i=0;
				for (String data : vo.getLikes().get(column)) {
					if ( i !=0 )
						sb.append(" OR ");
					sb.append(column);
					sb.append(" = ");
					sb.append("'");
					sb.append(data);
					sb.append("' ");
					i++;
				}
				sb.append(") ");
			}
		}
	//	LOGGER.info("BUILDED AGGREGATION QUERY: " + sb.toString());
		return sb.toString();
	}
	
	public static String buildQuery(ADAMQueryVO vo) {
		if (vo.getCodingSystem() != null ) {
			return buildQueryWithCodingSystem(vo);
		}
		else {
			return buildQueryWithoutCodingSystem(vo);
		}
	}
	
	
	
	
	public static String buildQueryNew(ADAMQueryVO vo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		for (int i = 0 ; i < vo.getSelects().size() ; i++) {
			sb.append(vo.getSelects().get(i));
			if (i < vo.getSelects().size() - 1)
				sb.append(", ");
		}
		if (!vo.getAggregations().isEmpty()) {
			sb.append(", ");
			int counter = 0;
			for (String column : vo.getAggregations().keySet()) {
				sb.append(buildAggregationFunction(vo.getAggregations().get(column), column, vo.getAggregations().get(column)+counter ));
				
//				sb.append(vo.getAggregations().get(column));
//				sb.append("(");
//				sb.append(column);
//				sb.append(") AS ");
//				sb.append(vo.getAggregations().get(column));
				if (counter < vo.getAggregations().size() - 1)
					sb.append(", ");
				counter++;
			}
		}
		sb.append(" FROM ");
			if(!vo.getAggregatedViewNames().isEmpty()){
			for (int i = 0 ; i < vo.getAggregatedViewNames().size() ; i++) {
				sb.append(vo.getAggregatedViewNames().get(i));
				if (i < vo.getAggregatedViewNames().size() - 1)
					sb.append(", ");
			}
		} else {
			for (int i = 0 ; i < vo.getTableNames().size() ; i++) {
				sb.append(vo.getTableNames().get(i));
				if (i < vo.getTableNames().size() - 1)
					sb.append(", ");
			}
		}
		sb.append(", codingsystem AS cs ");
		sb.append(", code as c ");
		sb.append(", codingsystem_code AS csc ");
		sb.append("WHERE ");
		sb.append("cs.resourceid = csc.codingsystem_resourceid ");
		sb.append("AND ");
		sb.append("csc.codes_id = c.id ");
		sb.append("AND ");
		sb.append(vo.getCodedColumn()).append(" = c.code ");
		sb.append("AND ");
		sb.append("c.langcode = '").append(vo.getLangCode()).append("' ");
		sb.append("AND ");
		sb.append("cs.title = '").append(vo.getCodingSystem()).append("' ");
		
		int j = 0;
		for(String filter : vo.getWheres().keySet()) {
			if ( j == 0 )
				sb.append(addSQLCondition(filter, vo.getWheres().get(filter), false));
			else
				sb.append(addSQLCondition(filter, vo.getWheres().get(filter), true));
			j++;
		}
		
//		if (!vo.getWheres().isEmpty()) {
//			for (String column : vo.getWheres().keySet()) {
//				sb.append("AND ");
//				sb.append("( ");
//				int i=0;
//				for (String data : vo.getWheres().get(column)) {
//					if ( i !=0 )
//						sb.append(" OR ");
//					sb.append(column);
//					sb.append(" = ");
//					sb.append("'");
//					sb.append(data);
//					sb.append("' ");
//					i++;
//				}
//				sb.append(") ");
//			}
//		}
		if (!vo.getLikes().isEmpty()) {
			for (String column : vo.getLikes().keySet()) {
				sb.append("AND ");
				sb.append("( ");
				int i=0;
				for (String data : vo.getLikes().get(column)) {
					if ( i !=0 )
						sb.append(" OR ");
					sb.append(column);
					sb.append(" = ");
					sb.append("'");
					sb.append(data);
					sb.append("' ");
					i++;
				}
				sb.append(") ");
			}
		}
		if (!vo.getAggregations().isEmpty()) {
			for (String column : vo.getAggregations().keySet()) {
				sb.append(" AND ");
				sb.append(column);
//				sb.append(" is not null ");
				sb.append(" > 0 ");
			}
		}
		
		sb.append(" GROUP BY c.label ");
		/*if (!vo.getAggregations().isEmpty()) {
			sb.append(", ");
			int k = 0;
			for (String aggregation : vo.getAggregations().keySet()) {	
				sb.append(aggregation);
				
				if (k < vo.getAggregations().size() - 1)
					sb.append(", ");
				
				k++;
			}
		}*/
		
//		if (!vo.getAggregations().isEmpty()) {
//			for (String column : vo.getAggregations().keySet()) {
//				sb.append("HAVING ");
//				sb.append(vo.getAggregations().get(column));
//				sb.append("(");
//				sb.append(column);
//				sb.append(") != 0 ");
//			}
//		}

	      
		if (!vo.getAggregations().isEmpty()) {
		     sb.append(" ORDER BY ");
			int counter = 0;
			for (String column : vo.getAggregations().values()) {
				sb.append(column+counter);
				sb.append(" ");
				sb.append(vo.getSortingOrder());
				if (counter < vo.getAggregations().size() - 1)
					sb.append(", ");
				
				counter++;
			}
		}
		
		if(vo.getLimit()!=0)
			sb.append(" LIMIT ").append(vo.getLimit());
	//	LOGGER.info("BUILDED QUERY: " + sb);
		return sb.toString();
	}
	
	
	
	public static String buildQueryWithoutCodingSystem(ADAMQueryVO vo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		for (int i = 0 ; i < vo.getSelects().size() ; i++) {
			sb.append(vo.getSelects().get(i));
			if (i < vo.getSelects().size() - 1)
				sb.append(", ");
		}
		if (!vo.getAggregations().isEmpty()) {
			sb.append(", ");
			int counter = 0;
			for (String column : vo.getAggregations().keySet()) {
				sb.append(buildAggregationFunction(vo.getAggregations().get(column), column, vo.getAggregations().get(column)+counter ));//				sb.append(" ROUND(");
//					sb.append(vo.getAggregations().get(column));
//					sb.append(" (");
//						sb.append("CAST(" + column + " as numeric) ");
//					sb.append(") ");
//				sb.append(", 2 ) ");
//				sb.append(" AS ");
//				sb.append(vo.getAggregations().get(column));
				if (counter < vo.getAggregations().size() - 1)
					sb.append(", ");
				counter++;
			}
		}
		sb.append(" FROM ");
		if(!vo.getAggregatedViewNames().isEmpty()){
			for (int i = 0 ; i < vo.getAggregatedViewNames().size() ; i++) {
				sb.append(vo.getAggregatedViewNames().get(i));
				if (i < vo.getAggregatedViewNames().size() - 1)
					sb.append(", ");
			}
		} else {
			for (int i = 0 ; i < vo.getTableNames().size() ; i++) {
				sb.append(vo.getTableNames().get(i));
				if (i < vo.getTableNames().size() - 1)
					sb.append(", ");
			}
		}

		sb.append(" WHERE ");

		int j=0; 
		for(String filter : vo.getWheres().keySet()) {
			if ( j == 0 )
				sb.append(addSQLCondition(filter, vo.getWheres().get(filter), false));
			else
				sb.append(addSQLCondition(filter, vo.getWheres().get(filter), true));
				j++;
			}
			
//		int j=0; 
//		if (!vo.getWheres().isEmpty()) {
//			for (String column : vo.getWheres().keySet()) {
//				if ( j != 0)
//					sb.append("AND ");
//				sb.append("( ");
//				int i=0;
//				for (String data : vo.getWheres().get(column)) {
//					if ( i !=0 )
//						sb.append(" OR ");
//					sb.append(column);
//					sb.append(" = ");
//					sb.append("'");
//					sb.append(data);
//					sb.append("' ");
//					i++;
//				}
//				sb.append(") ");
//				j++;
//			}
//			
//		}
		if (!vo.getLikes().isEmpty()) {
			for (String column : vo.getLikes().keySet()) {
				sb.append("AND ");
				sb.append("( ");
				int i=0;
				for (String data : vo.getLikes().get(column)) {
					if ( i !=0 )
						sb.append(" OR ");
					sb.append(column);
					sb.append(" = ");
					sb.append("'");
					sb.append(data);
					sb.append("' ");
					i++;
				}
				sb.append(") ");
			}
		}
		if (!vo.getAggregations().isEmpty()) {
			for (String column : vo.getAggregations().keySet()) {
				sb.append("AND ");
				sb.append(column);
				//				sb.append(" is not null ");
				sb.append(" > 0 ");;
			}
		}
		
		sb.append(" GROUP BY ");
		
		List<String> filteredSelects = new ArrayList<String>();
		for (int i = 0 ; i < vo.getSelects().size() ; i++) {
			if(!vo.getSelects().get(i).contains("SUM"))
				filteredSelects.add(vo.getSelects().get(i));
		}
		
		for (int k = 0 ; k < filteredSelects.size() ; k++) {
			
			sb.append(filteredSelects.get(k) + " ");
			
			if (k < filteredSelects.size() - 1)
				sb.append(", ");
			
		}
		
		/*if (!vo.getAggregations().isEmpty()) {
			int k = 0;
			sb.append(", ");
			for (String aggregation : vo.getAggregations().keySet()) {
				sb.append(aggregation);
				if (k < vo.getAggregations().size() - 1)
					sb.append(", ");
				
				k++;
			}
		}*/
//		if (!vo.getAggregations().isEmpty()) {
//			for (String column : vo.getAggregations().keySet()) {
//				sb.append("HAVING ");
//				sb.append(vo.getAggregations().get(column));
//				sb.append("(");
//				sb.append(column);
//				sb.append(") != 0 ");
//			}
//		}



			
		if (!vo.getAggregations().isEmpty()) {
		    sb.append(" ORDER BY ");
			int counter = 0;
			for (String column : vo.getAggregations().values()) {
				sb.append(column+counter);
				sb.append(" ");
				sb.append(vo.getSortingOrder());
				if (counter < vo.getAggregations().size() - 1)
					sb.append(", ");
				
				counter++;
			}
		} else if(vo.getOrderByColumn()!=null && vo.getSortingOrder()!=null){
			  sb.append(" ORDER BY "+vo.getOrderByColumn() +" "+ vo.getSortingOrder());
		}
		
		if(vo.getLimit()!=0)
			sb.append(" LIMIT ").append(vo.getLimit());

//		LOGGER.info("BUILD (WITHOUT CS) QUERY: " + sb.toString());
		return sb.toString();
	}


	
	public static String buildQueryWithCodingSystem(ADAMQueryVO vo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		for (int i = 0 ; i < vo.getSelects().size() ; i++) {
			sb.append(vo.getSelects().get(i));
			if (i < vo.getSelects().size() - 1)
				sb.append(", ");
		}
		if (!vo.getAggregations().isEmpty()) {
			sb.append(", ");
			int counter = 0;
			for (String column : vo.getAggregations().keySet()) {
				// round function
				sb.append(buildAggregationFunction(vo.getAggregations().get(column), column, vo.getAggregations().get(column)+counter ));

//				sb.append(vo.getAggregations().get(column));
//				sb.append(" (");
//				sb.append(column);
//				sb.append(") ");
//				sb.append(" AS ");
//				sb.append(vo.getAggregations().get(column));
////				sb.append(vo.getAggregations().get(column)+counter);
				if (counter < vo.getAggregations().size() - 1)
					sb.append(", ");
				counter++;
			}
		}
		sb.append(" FROM ");
			if(!vo.getAggregatedViewNames().isEmpty()){
			for (int i = 0 ; i < vo.getAggregatedViewNames().size() ; i++) {
				sb.append(vo.getAggregatedViewNames().get(i));
				if (i < vo.getAggregatedViewNames().size() - 1)
					sb.append(", ");
			}
		} else {
			for (int i = 0 ; i < vo.getTableNames().size() ; i++) {
				sb.append(vo.getTableNames().get(i));
				if (i < vo.getTableNames().size() - 1)
					sb.append(", ");
			}
		}
		sb.append(", codingsystem AS cs ");
		sb.append(", code as c ");
		sb.append(", codingsystem_code AS csc ");
		sb.append("WHERE ");
		sb.append("cs.resourceid = csc.codingsystem_resourceid ");
		sb.append("AND ");
		sb.append("csc.codes_id = c.id ");
		sb.append("AND ");
		sb.append(vo.getCodedColumn()).append(" = c.code ");
		sb.append("AND ");
		sb.append("c.langcode = '").append(vo.getLangCode()).append("' ");
		sb.append("AND ");
		sb.append("cs.title = '").append(vo.getCodingSystem()).append("' ");
		
		/** TODO: LOGS to test **/
		if (!vo.getWheres().isEmpty()) {			
			LOGGER.info("TRACKING--->vo.getWheres().keySet() " + vo.getWheres().keySet());
			for (String column : vo.getWheres().keySet()) {
				LOGGER.info("TRACKING--->column " + column);

				if ( vo.getWheres().get(column) != null ) {
					if ( vo.getWheres().get(column).size() > 0 ) {
						sb.append("AND ");
						sb.append("( ");
						int i=0;
						LOGGER.info("TRACKING--->vo.getWheres().get(column) " + vo.getWheres().get(column));
						for (String data : vo.getWheres().get(column)) {
							LOGGER.info("TRACKING--->data " + data);
							if ( i !=0 )
								sb.append(" OR ");
							sb.append(column);
							sb.append(" = ");
							sb.append("'");
							sb.append(data);
							sb.append("' ");
							i++;
						}
						sb.append(") ");
					}	
				}
				
			}
		}
		if (!vo.getLikes().isEmpty()) {
			for (String column : vo.getLikes().keySet()) {
				sb.append("AND ");
				sb.append("( ");
				int i=0;
				for (String data : vo.getLikes().get(column)) {
					if ( i !=0 )
						sb.append(" OR ");
					sb.append(column);
					sb.append(" = ");
					sb.append("'");
					sb.append(data);
					sb.append("' ");
					i++;
				}
				sb.append(") ");
			}
		}
		if (!vo.getAggregations().isEmpty()) {
			for (String column : vo.getAggregations().keySet()) {
				sb.append("AND ");
				sb.append(column);
//				sb.append(" is not null ");
				sb.append(" > 0 ");
			}
		}
		
		sb.append("GROUP BY ");
		for (int i = 0 ; i < vo.getSelects().size() ; i++) {
			sb.append(vo.getSelects().get(i));
			if (i < vo.getSelects().size() - 1)
				sb.append(", ");			
		}
		/*if (!vo.getAggregations().isEmpty()) {
			sb.append(", ");
			int k = 0;
			for (String aggregation : vo.getAggregations().keySet()) {	
				sb.append(aggregation);
				
				if (k < vo.getAggregations().size() - 1)
					sb.append(", ");
				
				k++;
			}
		}*/
	/*	if (!vo.getAggregations().isEmpty()) {
			for (String column : vo.getAggregations().keySet()) {
				sb.append(" HAVING ");
				sb.append(vo.getAggregations().get(column));
				sb.append("(");
				sb.append(column);
				sb.append(") != 0 ");
			}
		}*/
//		if (!vo.getAggregations().isEmpty()) {
//			sb.append(" HAVING ");
//			int counter = 0;
//			for (String column : vo.getAggregations().keySet()) {
//				sb.append(vo.getAggregations().get(column));
//				sb.append("(");
//				sb.append(column);
//				sb.append(") != 0 ");
//				if (counter < vo.getAggregations().size() - 1)
//					sb.append("AND ");
//				counter++;
//			}
//		}
		if (!vo.getAggregations().isEmpty()) {
			sb.append(" ORDER BY ");
			int counter = 0;
			for (String column : vo.getAggregations().values()) {
				sb.append(column+counter);
				sb.append(" ");
				sb.append(vo.getSortingOrder());
				if (counter < vo.getAggregations().size() - 1)
					sb.append(", ");
				
				counter++;
			}
			//sb.append(" ");
			//sb.append(vo.getSortingOrder());
		}
		
		
		if(vo.getLimit()!=0)
			sb.append(" LIMIT ").append(vo.getLimit());
		
		LOGGER.info("BUILD (WITH CS) QUERY: " + sb.toString());
		return sb.toString();
	}

//	public static String buildQuery(ADAMQueryVO vo) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT ");
//		for (int i = 0 ; i < vo.getSelects().size() ; i++) {
//			sb.append(vo.getSelects().get(i));
//			if (i < vo.getSelects().size() - 1)
//				sb.append(", ");
//		}
//		if (!vo.getAggregations().isEmpty()) {
//			sb.append(", ");
//			int counter = 0;
//			for (String column : vo.getAggregations().keySet()) {
//				sb.append(vo.getAggregations().get(column));
//				sb.append("(");
//				sb.append(column);
//				sb.append(") AS ");
//				sb.append(vo.getAggregations().get(column));
//				if (counter < vo.getAggregations().size() - 1)
//					sb.append(", ");
//				counter++;
//			}
//		}
//		sb.append(" FROM ");
//		for (int i = 0 ; i < vo.getTableNames().size() ; i++) {
//			sb.append(vo.getTableNames().get(i));
//			if (i < vo.getTableNames().size() - 1)
//				sb.append(", ");
//		}
//		sb.append(", codingsystem AS cs ");
//		sb.append(", code as c ");
//		sb.append(", codingsystem_code AS csc ");
//		sb.append("WHERE ");
//		sb.append("cs.resourceid = csc.codingsystem_resourceid ");
//		sb.append("AND ");
//		sb.append("csc.codes_id = c.id ");
//		sb.append("AND ");
//		sb.append(vo.getCodedColumn()).append(" = c.code ");
//		sb.append("AND ");
//		sb.append("c.langcode = '").append(vo.getLangCode()).append("' ");
//		sb.append("AND ");
//		sb.append("cs.title = '").append(vo.getCodingSystem()).append("' ");
//		if (!vo.getWheres().isEmpty()) {
//			for (String column : vo.getWheres().keySet()) {
//				sb.append("AND ");
//				sb.append(column);
//				sb.append(" = ");
//				sb.append("'");
//				sb.append(vo.getWheres().get(column).toString());
//				sb.append("' ");
//			}
//		}
//		if (!vo.getLikes().isEmpty()) {
//			for (String column : vo.getLikes().keySet()) {
//				sb.append("AND ");
//				sb.append(column);
//				sb.append(" LIKE ");
//				sb.append("'");
//				sb.append(vo.getLikes().get(column).toString());
//				sb.append("' ");
//			}
//		}
//		sb.append("GROUP BY c.label ");
//		if (!vo.getAggregations().isEmpty()) {
//			for (String column : vo.getAggregations().keySet()) {
//				sb.append("HAVING ");
//				sb.append(vo.getAggregations().get(column));
//				sb.append("(");
//				sb.append(column);
//				sb.append(") != 0 ");
//			}
//		}
//		if (!vo.getAggregations().isEmpty()) {
//			for (String aggregation : vo.getAggregations().values()) {
//				sb.append("ORDER BY ");
//				sb.append(aggregation);
//				sb.append(" ");
//				sb.append(vo.getSortingOrder());
//				sb.append(" ");
//			}
//		}
//		sb.append("LIMIT ").append(vo.getLimit());
////		LOGGER.info(sb);
//		return sb.toString();
//	}

	public static String getDonors(String tablename, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();	

		
//		sb.append(" SELECT donorcode AS TOTALDONOR,  c.label, sum(usd_disbursement) AS TOTALBUDGET " +
//				  " FROM "+ tablename  +" as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
//				  " WHERE ADAM.donorcode = c.code AND cs.code = 'CRS_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		
	/**	sb.append(" SELECT donorcode AS TOTALDONOR,  donorname, sum(usd_disbursement) AS TOTALBUDGET " +
		  " FROM "+ tablename  +" as ADAM " +
		  " WHERE  ");

		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		
		sb.append(" GROUP BY donorcode, donorname " +  
				  " HAVING sum(usd_disbursement) != 0 " + 
				  " ORDER BY TOTALBUDGET DESC LIMIT "+ limit);**/
		
//		sb.append(" SELECT donorcode AS TOTALDONOR,  donorname, " + aggregationType + "(" + quantityColumn +") AS TOTALBUDGET " +
		sb.append(" SELECT donorcode AS TOTALDONOR,  donorname, " + buildAggregationFunction(aggregationType, quantityColumn, "TOTALBUDGET") + 
		  " FROM "+ tablename  +" as ADAM " +
		  " WHERE  ");

		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY donorcode, donorname " +  
//				  " HAVING " + aggregationType + "(" + quantityColumn +") != 0 " + 
				  " ORDER BY TOTALBUDGET DESC ");
				  
		if ( limit != null || limit != 0)
			 sb.append(" LIMIT  " + limit);
		
		
		
		
//		LOGGER.info("globalView table query: " + sb);
		
		return sb.toString();
	}
	
	public static String getSubSectorDonors(String tablename, String sub_sector, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT donorcode AS TOTALDONOR,  donorname, " + buildAggregationFunction(aggregationType, quantityColumn, "TOTALBUDGET") + 
				  " FROM "+ tablename  +" as ADAM " +
				  " WHERE  purposecode = '"+ sub_sector + "' ");
		 
		for(String filter : filters.keySet() ) 
			sb.append(addSQLCondition(filter, filters.get(filter), true));


//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");

		sb.append(" GROUP BY donorcode, donorname " +  
//				  " HAVING " + aggregationType + "(" + quantityColumn +") != 0 " + 
				  " ORDER BY TOTALBUDGET DESC ");
		  
	if ( limit != null || limit != 0)
		 sb.append(" LIMIT  " + limit);
		
		
//		LOGGER.info("getSubSectorDonors query: " + sb);
		
		return sb.toString();
	}
	
	
	
	
	public static String globalViewDAC(String tablename, String donor, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();	

		
//		sb.append(" SELECT donorcode, c.label, dac_sector, sum(usd_disbursement) " +
//				  " FROM "+ tablename+ " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC "+
//				  " WHERE donorcode = '" + donor +"' " +
//				  " AND ADAM.dac_sector = c.code AND cs.code = 'CRS_SECTORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
//		
//		for(String filter : filters.keySet() ) 
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY donorcode, dac_sector, c.label " +
//				  " HAVING sum(usd_disbursement) != 0 " +
//				  " ORDER BY sum(usd_disbursement) DESC LIMIT "+ limit);
		
		sb.append(" SELECT donorcode, dac_label, dac_sector, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) + 
				  " FROM "+ tablename+ " as ADAM "+
				  " WHERE donorcode = '" + donor +"' ");
		
		
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY donorcode, dac_sector, dac_label " +
//				  " HAVING " + aggregationType + "(" + quantityColumn +") != 0 " + 
				  " ORDER BY " + aggregationType + " DESC ");

		if ( limit != null || limit != 0)
			 sb.append(" LIMIT  "+ limit);
		
//		LOGGER.info("getSubSectorDonors query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String countryViewDAC(String tablename, String donor, Map<String, List<String>> filters, String quantityColumn, String aggregationType,  Integer limit) {
		StringBuilder sb = new StringBuilder();	

		
//		sb.append(" SELECT c.code, c.label, sum(usd_disbursement) " +
//				  " FROM "+ tablename+ " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC "+
//				  " WHERE donorcode = '" + donor +"' " +
//				  " AND ADAM.dac_sector = c.code AND cs.code = 'CRS_SECTORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
//		
//		for(String filter : filters.keySet() ) 
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY c.code, dac_sector, c.label " +
//				  " HAVING sum(usd_disbursement) != 0 " +
//				  " ORDER BY sum(usd_disbursement) DESC LIMIT "+ limit);
		
		sb.append(" SELECT dac_sector, dac_label, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) + 
				  " FROM "+ tablename+ " as ADAM "+
				  " WHERE donorcode = '" + donor +"' ");
		
		for(String filter : filters.keySet() ) 
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY dac_sector, dac_sector, dac_label " +
//				  " HAVING " + aggregationType + "(" + quantityColumn +") != 0 " + 
				  " ORDER BY " + aggregationType + " DESC ");

		if ( limit != null || limit != 0)
			 sb.append(" LIMIT  "+ limit);
		
//		LOGGER.info("globalView dac_sector query: " + sb);
		
		return sb.toString();
	}
	
	public static String globalViewChannel(String tablename, String donor, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();	
		

		
//		sb.append(" SELECT donorcode, channelcode, c.label, sum(usd_disbursement) " +
//				  " FROM "+ tablename + " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
//				  " WHERE donorcode = '" + donor +"' " +
//				  "	AND ADAM.channelcode = c.code AND cs.code = 'CRS_CHANNELS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
//		
//		for(String filter : filters.keySet() ) 
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY donorcode, channelcode, c.label "+
//				  " HAVING sum(usd_disbursement) != 0 " + 
//				  " ORDER BY  sum(usd_disbursement) DESC LIMIT "+ limit);
		
		sb.append(" SELECT donorcode, channelcode, channelname, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) + 
				  " FROM "+ tablename + " as ADAM  " +
				  " WHERE donorcode = '" + donor +"' ");
		
		for(String filter : filters.keySet() ) 
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY donorcode, channelcode, channelname "+
//				  " HAVING  " + aggregationType + "(" + quantityColumn +")  != 0 " + 
				  " ORDER BY   " + aggregationType + " DESC ");
				  
		if ( limit != null || limit != 0)
			 sb.append(" LIMIT  "+ limit);
		
		LOGGER.info("globalView channelcode query: " + sb);
		
		return sb.toString();
	}
	
	public static String countryViewChannel(String tablename, String donor, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();	
		
//		sb.append(" SELECT channelcode, c.label, sum(usd_disbursement) " +
//				  " FROM "+ tablename + " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
//				  " WHERE donorcode = '" + donor +"' " +
//				  "	AND ADAM.channelcode = c.code AND cs.code = 'CRS_CHANNELS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
//		
//		for(String filter : filters.keySet() ) 
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY donorcode, channelcode, c.label "+
//				  " HAVING sum(usd_disbursement) != 0 " + 
//				  " ORDER BY  sum(usd_disbursement) DESC LIMIT "+ limit);
		
		sb.append(" SELECT channelcode, channelname, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename + " as ADAM " +
				  " WHERE donorcode = '" + donor +"' ");
		
		for(String filter : filters.keySet() ) 
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY donorcode, channelcode, channelname "+
//				  " HAVING  " + aggregationType + "(" + quantityColumn +")  != 0 " + 
				  " ORDER BY   " + aggregationType + " DESC ");
				  
		if ( limit != null || limit != 0)
			 sb.append(" LIMIT  "+ limit);
		
//		LOGGER.info("globalView channelcode query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String globalViewRecipient(String tablename, String donor, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit, String recipientCSCode) {
		StringBuilder sb = new StringBuilder();	

		
//		sb.append("SELECT donorcode, recipientcode, c.label, SUM(usd_disbursement) " + 
//				  " FROM "+ tablename+ " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " + 
//				  " WHERE donorcode = '" + donor +"' " +
//				  " AND ADAM.recipientcode = c.code AND cs.code = '"+ recipientCSCode +"' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
//	
//		for(String filter : filters.keySet() ) 
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY donorcode, recipientcode, c.label " +
//				  " HAVING sum(usd_disbursement) != 0 " +
//				  " ORDER BY  sum(usd_disbursement) DESC LIMIT "+ limit);
		
		sb.append("SELECT donorcode, recipientcode, recipientname, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename+ " as ADAM " + 
				  " WHERE donorcode = '" + donor +"' "); 
		
		for(String filter : filters.keySet() ) 
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");

		sb.append(" GROUP BY donorcode, recipientcode, recipientname " +
//				  " HAVING  " + aggregationType + "(" + quantityColumn +")  != 0 " + 
				  " ORDER BY   " + aggregationType + " DESC ");
				  
		if ( limit != null || limit != 0)
			 sb.append(" LIMIT  "+ limit);
		
		LOGGER.info("globalView recipientcode query: " + sb);
		
		return sb.toString();
	}
	
	
	
	
	
	public static String topSectorsDonorView(String tablename, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();

//		sb.append(" SELECT C1.code AS SECTORCODE, C1.label AS SECTOR, SUM(usd_disbursement) " + 
//				 " FROM "+ tablename+ " as ADAM, code C1, codingsystem CS1, codingsystem_code CSC1 " +
//				 " WHERE C1.langcode = 'EN' AND CS1.code = 'CRS_SECTORS' AND C1.id = CSC1.codes_id AND CSC1.codingsystem_resourceid = CS1.resourceid AND C1.code = dac_sector ");
//				 
//		for(String filter : filters.keySet() )
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY C1.code, C1.labeL HAVING SUM(usd_disbursement) > 0 " +
//				  " ORDER BY SUM(usd_disbursement) DESC"); 
		
		sb.append(" SELECT dac_sector AS SECTORCODE, dac_label AS SECTOR, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				 " FROM "+ tablename+ " as ADAM " +
				 " WHERE ");
				 
		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0)
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY dac_sector, dac_label " +
//				  " HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC"); 
		
		
		if ( limit != null || limit != 0)
			 sb.append(" LIMIT  "+ limit);
		
//		LOGGER.info("topSectorsDonorView: " + sb);
		return sb.toString();
	}
	
	public static String topSectorsByRecipientCode(String tablename, String recipientCode, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();

//		sb.append(" SELECT C1.code AS SECTORCODE, C1.label AS SECTOR, SUM(usd_disbursement) " + 
//				 " FROM "+ tablename+ " as ADAM, code C1, codingsystem CS1, codingsystem_code CSC1 " +
//				 " WHERE C1.langcode = 'EN' AND CS1.code = 'CRS_SECTORS' AND C1.id = CSC1.codes_id AND CSC1.codingsystem_resourceid = CS1.resourceid AND C1.code = dac_sector ");
//
//		sb.append(" AND ADAM.recipientcode = '"+recipientCode+"' ");
//				 
//		for(String filter : filters.keySet() )
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY C1.code, C1.labeL HAVING SUM(usd_disbursement) > 0 " +
//				  " ORDER BY SUM(usd_disbursement) DESC "); 
//		
//		 if ( limit != null)
//			 sb.append(" LIMIT  "+ limit);
		
		
		sb.append(" SELECT dac_sector AS SECTORCODE, dac_label AS SECTOR, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename+ " as ADAM " +
				  " WHERE ADAM.recipientcode = '"+recipientCode+"' ");
				 
		for(String filter : filters.keySet() )
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY dac_sector, dac_label " +
//				  " HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		 if ( limit != null || limit != 0 )
			 sb.append(" LIMIT  "+ limit);
			
		
		
//		LOGGER.info("topSectorsByRecipientCode: " + sb);
		return sb.toString();
	}
	
	
	
	public static String topSectorsDonorViewRecipiens(String tablename, String dac_sector, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit, String  recipientCSCode) {
		StringBuilder sb = new StringBuilder();	

//		sb.append(" SELECT c.code, c.label, SUM(usd_disbursement)" +
//				  " FROM "+ tablename+ " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
//				  "	WHERE ADAM.recipientcode = c.code AND cs.code = '"+ recipientCSCode +"' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
//				  
//				  " AND dac_sector = '"+ dac_sector + "' ");
//		
//		for(String filter : filters.keySet() )
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//
//		
//		sb.append(" GROUP BY dac_sector, c.label,  c.code " +
//				  "	HAVING sum(usd_disbursement) != 0 " +
//				  " ORDER BY SUM(usd_disbursement) DESC LIMIT  " + limit);
		
		sb.append(" SELECT recipientcode, recipientname, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename+ " as ADAM " +
				  "	WHERE dac_sector = '"+ dac_sector + "' ");
		
		for(String filter : filters.keySet() )
			sb.append(addSQLCondition(filter, filters.get(filter), true));

		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY dac_sector, recipientname,  recipientcode " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		 if ( limit != null || limit != 0 )
			 sb.append(" LIMIT  "+ limit);
		
//		LOGGER.info("topSectorsDonorViewRecipiens: " + sb);
		return sb.toString();
	}
	
	public static String topSectorsDonorCountryViewChannels(String tablename, String dac_sector, Map<String, List<String>> filters, String quantityColumn, String aggregationType,  int limit) {
		StringBuilder sb = new StringBuilder();	

		
		sb.append(" SELECT channelcode, c.label,  " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM " + tablename + " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  " WHERE ADAM.channelcode = c.code AND cs.code = 'ADAM_CHANNELS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +

				  " AND dac_sector = '"+ dac_sector + "' ");
		
		for(String filter : filters.keySet() )
			sb.append(addSQLCondition(filter, filters.get(filter), true));

		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY dac_sector, channelcode, c.label " +
//				  "	HAVING sum(usd_disbursement) != 0 " +
				  " ORDER BY " + aggregationType +" DESC LIMIT "+ limit);
		
		
	
//		LOGGER.info("topSectorsDonorViewRecipiens: " + sb);
		return sb.toString();
	}
	
	public static String topSubSectorsDonorViewRecipiens(String tablename, String sub_sector, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit, String  recipientCSCode) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT recipientcode, recipientname, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename+ " as ADAM " +
				  "	WHERE purposecode = '"+ sub_sector + "' ");

		for(String filter : filters.keySet() )
			sb.append(addSQLCondition(filter, filters.get(filter), true));

		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");

		sb.append(" GROUP BY dac_sector, recipientname,  recipientcode " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		 if ( limit != null || limit != 0 )
			 sb.append(" LIMIT  "+ limit);
		
//		LOGGER.info("topSubSectorsDonorViewRecipiens: " + sb);
		return sb.toString();
	}
	
	public static String topCountries(String tablename, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit, String  recipientCSCode) {
		StringBuilder sb = new StringBuilder();	
		
		
		sb.append(" SELECT recipientcode, recipientname, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename+ " as ADAM " +
				  "	WHERE ");
		
		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0)
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY recipientname,  recipientcode " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		 if ( limit != null || limit != 0 )
			 sb.append(" LIMIT  "+ limit);
		
		LOGGER.info("topCountries: " + sb);
		return sb.toString();
	}
	
		
	
	
	public static String topSubSectorsChannels(String tablename, String sub_sector, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();	

//		sb.append(" SELECT channelcode, c.label, sum(usd_disbursement)" +
//				  " FROM " + tablename + " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
//				  " WHERE ADAM.channelcode = c.code AND cs.code = 'CRS_CHANNELS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
//
//				  " AND purposecode = '"+ sub_sector + "' ");
//		
//		for(String filter : filters.keySet() )
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		sb.append(" GROUP BY channelcode, c.label " +
//				  "	HAVING sum(usd_disbursement) != 0 " +
//				  " ORDER BY SUM(usd_disbursement) DESC LIMIT "+ limit);
		
		sb.append(" SELECT channelcode, channelname, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM " + tablename + " as ADAM " +
				  " WHERE purposecode = '"+ sub_sector + "' ");
		
		for(String filter : filters.keySet() )
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY channelcode, channelname " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		 if ( limit != null || limit != 0 )
			 sb.append(" LIMIT  "+ limit);
		
//		LOGGER.info("topSubSectorsChannels: " + sb);
		return sb.toString();
	}
	
	public static String topSubSectorsView(String tablename, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		StringBuilder sb = new StringBuilder();
			
//		sb.append(" SELECT c.code AS PURPOSECODE, c.label AS PURPOSE, SUM(usd_disbursement) " +
//				" FROM "+ tablename+ " as ADAM, code c, codingsystem cs, codingsystem_code csc " +
//				" WHERE c.langcode = 'EN' AND cs.code = 'CRS_PURPOSES' AND c.id = csc.codes_id AND csc.codingsystem_resourceid = cs.resourceid AND c.code = purposecode ");
//
//		for(String filter : filters.keySet() )
//				sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//	
//		sb.append(" GROUP BY c.code, c.labeL " +
//				  " HAVING SUM(usd_disbursement) > 0 " +
//				  "	ORDER BY SUM(usd_disbursement) DESC");
		
		sb.append(" SELECT purposecode AS PURPOSECODE, purposename AS PURPOSE, " + aggregationType + "(" + quantityColumn +")  " + 
				  " FROM " + tablename+ " as ADAM " +
				  " WHERE ");
				 
		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0)
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
	
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
	
		sb.append(" GROUP BY purposecode, purposename " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + "(" + quantityColumn +") DESC "); 
		
		 if ( limit != null || limit != 0 )
			sb.append("LIMIT "+ limit);
		
//		LOGGER.info("topSubSectorsDonorView: " + sb);
		return sb.toString();
	}
	
	public static String favouritePurposesQuestionsView(String tablename, String recipientcode) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT C1.label AS PURPOSE, SUM(usd_disbursement), C2.LABEL AS DONOR FROM ");
		sb.append(tablename);
		sb.append(", code C1, code C2, codingsystem CS1, codingsystem CS2, codingsystem_code CSC1, codingsystem_code CSC2 WHERE C1.langcode = 'EN' AND C2.langcode = 'EN' AND CS1.code = 'ADAM_PURPOSES' AND CS2.code = 'ADAM_DONORS' AND C1.id = CSC1.codes_id AND CSC1.codingsystem_resourceid = CS1.resourceid AND C1.code = purposecode AND C2.id = CSC2.codes_id AND CSC2.codingsystem_resourceid = CS2.resourceid AND C2.code = donorcode AND recipientcode = '");
		sb.append(recipientcode);
		sb.append("' GROUP BY C1.label, C2.label HAVING SUM(usd_disbursement) > 0 ORDER BY SUM(usd_disbursement) DESC, C1.label, C2.label");
//		LOGGER.info(sb);
		return sb.toString();
	}
	
	public static String donorProfileView(String tablename, String donorcode) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT overall_priority_themes, coded_priorities, strategicobjectives, overall_priority_geographical_areas, recipientcountriesinreu, type_of_funding_arrangements_favoured, delivery_by_sources_of_external_funding_in_usd_million, from_date_delivery, to_date_delivery");
		sb.append(" FROM ");
		sb.append(tablename);
		sb.append(" WHERE donorcode = '");
		sb.append(donorcode);
		sb.append("'");
		LOGGER.info(sb);
		return sb.toString();
	}
	  
	public static String donorProcessesView(String tablename, String donorcode) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT funding_bodies_within_resource_partner, channelcooperation, process_of_application_and_negotiation, timeline_of_funding_cycle, issues_to_be_observed, external_links, responsible_resource_mobilization_officer");
		sb.append(" FROM ");
		sb.append(tablename);
		sb.append(" WHERE donorcode = '");
		sb.append(donorcode);
		sb.append("'");
		LOGGER.info(sb);
		return sb.toString();
	}
	
	public static String donorTotalODAView(String tablename, String donorcode) {
		
		//sorted by recipient country list for all years 
		//select C.label, MATRIX.year, SUM(MATRIX."Total ODA") as TOTAL from adam_donormatrix_data as MATRIX, code AS C, codingsystem as CS, codingsystem_code as CSC where donorcode='2' AND MATRIX.recipientcode =c.code AND cs.code = 'CRS_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' GROUP BY C.label, MATRIX.year HAVING SUM(MATRIX."Total ODA") > 0 ORDER BY C.label, MATRIX.year DESC
		//i.e.
		//afghanistan, 2008, value
		//afghanistan, 2007, value
		
		
		//sorted by total ODA received recipient country list for all years
		//select C.label, MATRIX.year, SUM(MATRIX."Total ODA") as TOTAL from adam_donormatrix_data as MATRIX, code AS C, codingsystem as CS, codingsystem_code as CSC where donorcode='2' AND MATRIX.recipientcode =c.code AND cs.code = 'CRS_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' GROUP BY C.label, MATRIX.year HAVING SUM(MATRIX."Total ODA") > 0 ORDER BY year DESC, SUM(MATRIX."Total ODA") DESC, C.label
		//i.e.
		//country x, 2008, maxvalue
		//country y, 2008, 2nd max value
		//...
		// country x, 2007, maxvalue
		//country y, 2007, 2nd max value
		
		
		//for latest year 2008-01-01
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT C.label, MATRIX.year, SUM(MATRIX.\"Total ODA\") as TOTAL FROM ");
		sb.append("adam_donormatrix_data as MATRIX, code AS C, codingsystem as CS, codingsystem_code as CSC ");
		sb.append("WHERE donorcode='");
		sb.append(donorcode);
		sb.append("' AND MATRIX.year='2008-01-01' AND MATRIX.recipientcode =c.code AND cs.code = 'ADAM_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append("GROUP BY C.label, MATRIX.year HAVING SUM(MATRIX.\"Total ODA\") > 0 ORDER BY SUM(MATRIX.\"Total ODA\") DESC, C.label, MATRIX.year");
		
		LOGGER.info(sb);
		return sb.toString();
	}
	
/**	public static String recipientCountriesForDonor(String donorCode, String  recipientCSCode, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.recipientcode, c.label" +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				 // "	WHERE ADAM.donorcode = '"+donorCode+"' AND ADAM.recipientcode =c.code AND cs.code =  'GAUL0' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
		          " WHERE ADAM.donorcode = '"+donorCode+"' AND ") ;
		
		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.recipientcode =c.code AND cs.title =  '"+ recipientCSCode +"' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.recipientcode, c.label");
		
//		LOGGER.info("recipientCountriesForDonor: " + sb);
		return sb.toString();
	}
	
	
	
	
	public static String recipientCountriesForNonDacDonor(String tableName, List<String> donorCodes, String  recipientCSCode, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.recipientcode, c.label" +
				  " FROM "+tableName+" as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
			      " WHERE ADAM.donorcode IN (") ;
		
		int j = 0;
		for(String code : donorCodes ) {
			sb.append("'"+code+"'");
			
			if (j < donorCodes.size() - 1)
    			sb.append(", ");
			
			j++;
		}
		sb.append(") AND ");
		
		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.recipientcode =c.code AND cs.title =  '"+ recipientCSCode +"' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.recipientcode, c.label");
		
//		LOGGER.info("recipientCountriesForDonor: " + sb);
		return sb.toString();
	}**/
	
	public static String recipientCountriesForDonor(Map<String, String> donorCodes, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.recipientcode " +
				  " FROM adam_donormatrix_data as ADAM " +
				 // "	WHERE ADAM.donorcode = '"+donorCode+"' AND ADAM.recipientcode =c.code AND cs.code =  'GAUL0' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
	             " WHERE ADAM.donorcode IN (") ;
		
		int j = 0;
		for(String code : donorCodes.keySet() ) {
			sb.append("'"+code+"'");
			
			if (j < donorCodes.size() - 1)
    			sb.append(", ");
			
			j++;
		}
		sb.append(") AND ");
		
		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" GROUP BY ADAM.recipientcode");
		
//		LOGGER.info("recipientCountriesForDonor: " + sb);
		return sb.toString();
	}
	
	
	public static String recipientCountriesForDacDonor(List<String> donorCodes, String  recipientCSCode, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.recipientcode, c.label" +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				 // "	WHERE ADAM.donorcode = '"+donorCode+"' AND ADAM.recipientcode =c.code AND cs.code =  'GAUL0' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
	             " WHERE ADAM.donorcode IN (") ;
		
		int j = 0;
		for(String code : donorCodes ) {
			sb.append("'"+code+"'");
			
			if (j < donorCodes.size() - 1)
    			sb.append(", ");
			
			j++;
		}
		sb.append(") AND ");
		
		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.recipientcode =c.code AND cs.title =  '"+ recipientCSCode +"' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.recipientcode, c.label");
		
//		LOGGER.info("recipientCountriesForDonor: " + sb);
		return sb.toString();
	}
	
	public static String allRecipientCountries(String recipientCSCode) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.recipientcode, c.label" +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				 // "	WHERE ADAM.donorcode = '"+donorCode+"' AND ADAM.recipientcode =c.code AND cs.code =  'GAUL0' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
		          "	WHERE ADAM.recipientcode =c.code AND cs.title =  '"+ recipientCSCode +"' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
			
		sb.append(" GROUP BY ADAM.recipientcode, c.label");
		
//		LOGGER.info("recipientCountriesForDonor: " + sb);
		return sb.toString();
	}
	
	public static String nonDacDonorsForRecipientCountry(String tableName, Map<String, String> recipientCodes, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode, c.label" +
				  " FROM "+tableName+" as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  " WHERE ADAM.recipientcode IN (") ;
			
		int j = 0;
		for(String code : recipientCodes.keySet() ) {
			if (j < recipientCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
	    int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.donorcode =c.code AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.donorcode, c.label");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String sectorsForRecipientCountry(String recipientCode, Map<String, String> donorCodes, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT DISTINCT(purposecode), c.label" +
				  " FROM adam_faosectormatrix_data ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC" +
				  " WHERE recipientcode ='"+recipientCode+"' AND "+
				  " donorcode IN (");
		
		int j = 0;
		for(String code : donorCodes.keySet() ) {
			if (j < donorCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
	    int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.purposecode =c.code AND cs.code = 'ADAM_PURPOSES' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" ORDER BY purposecode");
		
//		LOGGER.info("sectorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String getORsForDACSectorCodes(Map<String, String> sectorCodes, String conversionTable) {
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT FAO.dac, FAO.fao, FAO.OR_label" +
				  " FROM "+conversionTable+" FAO " +
				  " WHERE FAO.dac IN (");
		
		int j = 0;
		for(String code : sectorCodes.keySet() ) {
			if (j < sectorCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND");
		sb.append(" FAO.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
		
		sb.append(" ORDER BY FAO.dac, FAO.fao");
		
		LOGGER.info("getORsForDACSectorCodes: " + sb);
		return sb.toString();
	}
	
	 
	public static String donorsForRecipientCountry(Map<String, String> recipientCodes, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode " +
				  " FROM adam_donormatrix_data as ADAM  " +
				  " WHERE ADAM.recipientcode IN (") ;
			
		int j = 0;
		for(String code : recipientCodes.keySet() ) {
			if (j < recipientCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
	    int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" GROUP BY ADAM.donorcode");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String dacMembershipForDonors(List<String> donorCodes, String tableName) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT CASE WHEN crs IS NULL THEN 'false' ELSE 'true' END AS is_dac, code" +
				  " FROM "+tableName+ 
				  " WHERE ") ;
			
		sb.append(addSQLCondition("code", donorCodes, false));
		
		/*int j = 0;
		for(String code : donorCodes.keySet() ) {
			if (j < donorCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}*/
	   sb.append(" ORDER BY code");
		
	//	LOGGER.info("dacMembershipForDonors: " + sb);
		return sb.toString();
	}
	
	public static String getRecipientsWithData(Map<String, String> recipientCodes, Map<String, List<String>> dateFilter, Map<String, String> donorCodes) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.recipientcode, c.label " +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  " WHERE ADAM.recipientcode IN (") ;
			
		int j = 0;
		for(String code : recipientCodes.keySet() ) {
			if (j < recipientCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
		int z = 0;
		if(donorCodes!=null && !donorCodes.isEmpty()) {
			sb.append("ADAM.donorcode IN (");
			for(String code : donorCodes.keySet() ) {
				if (z < donorCodes.size() - 1)
	    			sb.append("'"+code+"', ");
				else
					sb.append("'"+code+"'");
							
				z++;
			}
			sb.append(") AND ");	
		}
		
	    int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.recipientcode =c.code AND cs.code = 'ADAM_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.recipientcode, c.label");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}

	public static String getNonDacDonorsWithData(String tableName, List<String> nonDacCodes, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode, c.label " +
				  " FROM "+tableName+" as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  " WHERE ADAM.donorcode IN (") ;
			
		int j = 0;
		for(String code : nonDacCodes ) {
			if (j < nonDacCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
	    int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.donorcode =c.code AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.donorcode, c.label");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String getDonorsWithData(Map<String, String> donorCodes, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode, c.label " +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  " WHERE ADAM.donorcode IN (") ;
			
		int j = 0;
		for(String code : donorCodes.keySet() ) {
			if (j < donorCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
	    int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.donorcode =c.code AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.donorcode, c.label");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String getDacDonorsWithData(List<String> dacDonorCodes, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode, c.label " +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  " WHERE ADAM.donorcode IN (") ;
			
		int j = 0;
		for(String code : dacDonorCodes ) {
			if (j < dacDonorCodes.size() - 1)
    			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
	    int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.donorcode =c.code AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.donorcode, c.label");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String donorsForRecipientCountry( String recipientCode, Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode, c.label" +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  " WHERE ADAM.recipientcode = '"+recipientCode+"' AND ") ;
			
		
		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" AND ADAM.donorcode =c.code AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		sb.append(" GROUP BY ADAM.donorcode, c.label");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String allDonors() {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode, c.label" +
				  " FROM adam_donormatrix_data as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
				  "	WHERE ADAM.donorcode =c.code AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
			
		sb.append(" GROUP BY ADAM.donorcode, c.label");
		
//		LOGGER.info("donorsForRecipientCountry: " + sb);
		return sb.toString();
	}
	
	public static String getKeySosAndOrsTableNameQuery() {
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT tablename from customdataset where code='ADAM_DONORMATRIX_KEYSOSORS'" );
		
		LOGGER.info("getKeySosAndOrsTableName: " + sb);
		return sb.toString();
	}
	
	public static String getRecipientKeySosAndOrsQuery(Map<String, String> recipientsMap, String tableName) {
		
		StringBuilder recipientList = new StringBuilder();	
		
	    int i = 0;
		for(String code : recipientsMap.keySet() ) {
			if (i < recipientsMap.size() - 1)
				recipientList.append("'"+code+"', ");
			else
				recipientList.append("'"+code+"'");
						
			i++;
		}
		StringBuilder sb = new StringBuilder();	
				
		sb.append("SELECT MAX(to_year), from_year, key_so, key_or, comment, recipientcode " +
				" FROM " + tableName +
				" Where recipientcode IN ("+recipientList.toString()+"))" +
				" GROUP BY recipientcode, to_year, from_year, key_so, key_or, comment ORDER BY to_year, key_so;");
		
		
		
		/*sb.append(" SELECT DISTINCT reference_year, key_sos, key_ors, comment, recipientcode " +
				  " FROM " + tableName +
				  " WHERE reference_year =(SELECT MAX(reference_year) as latest_date FROM "+tableName +
				  " WHERE recipientcode IN ("+recipientList.toString()+"))" +
				  " AND recipientcode IN ("+recipientList.toString()+")");*/
		
		//LOGGER.info("getRecipientKeySosAndOrsQuery: " + sb);
		return sb.toString();
	}
	
	public static String getRecipientKeySosAndOrsQuery(String recipientCode, String tableName) {
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT DISTINCT reference_year, key_sos, key_ors, comment " +
				  " FROM " + tableName +
				  " WHERE reference_year =(SELECT MAX(reference_year) as latest_date FROM "+tableName+" WHERE recipientcode='"+recipientCode+"')" +
				  " AND recipientcode='"+recipientCode+"'");
		
	//	LOGGER.info("getRecipientKeySosAndOrsQuery: " + sb);
		return sb.toString();
	}
	
	public static String getKeySosAndOrsDescriptionQuery(String code) {
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT c.label" +
				" FROM code AS c, codingsystem as cs, codingsystem_code as CSC" +
				" WHERE c.code='"+code+"' AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'") ;		
		
		//LOGGER.info("getKeySosAndOrsDescriptionQuery: " + sb);
		return sb.toString();
	}
	
	
	////// Key SOr and Ors for Donor Matrix
	public static String getRecipientsWithSOsAndOrsQuery(Map<String, String> recipientsMap, String tableName) {
	StringBuilder recipientList = new StringBuilder();	
		
	    int i = 0;
		for(String code : recipientsMap.keySet() ) {
			if (i < recipientsMap.size() - 1)
				recipientList.append("'"+code+"', ");
			else
				recipientList.append("'"+code+"'");
						
			i++;
		}
		StringBuilder sb = new StringBuilder();	
				
		sb.append("SELECT MAX(to_year), from_year, recipientcode, comment " +
				" FROM " + tableName +
				" WHERE recipientcode IN ("+recipientList.toString()+") " +
				" GROUP BY recipientcode, to_year, from_year, comment");
		
		return sb.toString();
	}
	
	public static String getRecipientKeySOsQuery(String recipientCode, String tableName, Date toYear, Date fromYear) {
			StringBuilder sb = new StringBuilder();	
					
			sb.append("SELECT DISTINCT key_so, c.label" +
					" FROM " + tableName + ",code AS c, codingsystem as cs, codingsystem_code as CSC "  +
					" WHERE recipientcode = '"+recipientCode+"' AND  to_year = '"+toYear+"' AND  from_year = '"+fromYear+"' " +
					" AND c.code=key_so AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
					" GROUP BY key_so, c.label");
		
			//LOGGER.info("getRecipientKeySOsQuery: " + sb);
			
			return sb.toString();
	}
	
	
	public static String getRecipientKeySOsQuery(List<String> recipients, String tableName) {
		StringBuilder sb = new StringBuilder();	
		//SELECT DISTINCT key_so, recipientcode, c.label FROM RECIPIENT_COUNTRY_KEY_SOS_AND_ORS_7c6dde51,code AS c, codingsystem as cs, codingsystem_code as CSC  WHERE recipientcode in ( '625', '136')  AND  to_year = '2009-01-01' AND  from_year = '2007-01-01'  AND c.code=key_so AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'  GROUP BY recipientcode, key_so, c.label ORDER BY recipientcode;
		
		//SELECT DISTINCT key_so, MAX(to_year) as to_year, MAX(from_year) as from_year, recipientcode, c.label FROM RECIPIENT_COUNTRY_KEY_SOS_AND_ORS_7c6dde51,code AS c, codingsystem as cs, codingsystem_code as CSC  WHERE recipientcode IN ('136', '55', '645', '666', '769', '282', '738', '543', '625', '259', '730', '238', '550') AND c.code=key_so AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'  GROUP BY recipientcode, key_so, c.label ORDER BY recipientcode;

		sb.append("SELECT DISTINCT key_so, recipientcode, c.label, MAX(to_year) as to_year, MAX(from_year) as from_year" +
				" FROM " + tableName + ",code AS c, codingsystem as cs, codingsystem_code as CSC "  +
				" WHERE ");
		
		sb.append(" c.code=key_so AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
	
		if(recipients!=null && !recipients.isEmpty())
			sb.append(addSQLCondition("recipientcode", recipients, true));
		
		sb.append(" GROUP BY recipientcode, key_so, c.label ORDER BY recipientcode");

		
		//LOGGER.info("getRecipientKeySOsQuery: " + sb);
		
		return sb.toString();
}
	
	
	public static String getRecipientKeyORsQuery(List<String> recipients, String tableName) {
		StringBuilder sb = new StringBuilder();	
		//SELECT DISTINCT key_so, recipientcode, c.label FROM RECIPIENT_COUNTRY_KEY_SOS_AND_ORS_7c6dde51,code AS c, codingsystem as cs, codingsystem_code as CSC  WHERE recipientcode in ( '625', '136')  AND  to_year = '2009-01-01' AND  from_year = '2007-01-01'  AND c.code=key_so AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'  GROUP BY recipientcode, key_so, c.label ORDER BY recipientcode;
		
		//SELECT DISTINCT key_so, MAX(to_year) as to_year, MAX(from_year) as from_year, recipientcode, c.label FROM RECIPIENT_COUNTRY_KEY_SOS_AND_ORS_7c6dde51,code AS c, codingsystem as cs, codingsystem_code as CSC  WHERE recipientcode IN ('136', '55', '645', '666', '769', '282', '738', '543', '625', '259', '730', '238', '550') AND c.code=key_so AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'  GROUP BY recipientcode, key_so, c.label ORDER BY recipientcode;

		sb.append("SELECT DISTINCT key_or, recipientcode, c.label, MAX(to_year) as to_year, MAX(from_year) as from_year" +
				" FROM " + tableName + ",code AS c, codingsystem as cs, codingsystem_code as CSC "  +
				" WHERE ");
		
		sb.append(" c.code=key_or AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");

		
		if(recipients!=null && !recipients.isEmpty())
			sb.append(addSQLCondition("recipientcode", recipients, true));
		
		sb.append(" GROUP BY recipientcode, key_or, c.label ORDER BY recipientcode");
	
		//LOGGER.info("getRecipientKeySOsQuery: " + sb);
		
		return sb.toString();
}
	
	public static String getRecipientORsQuery(String recipientCode, String tableName, Date toYear, Date fromYear) {
	
			StringBuilder sb = new StringBuilder();	
					
			sb.append("SELECT DISTINCT key_or, c.label" +
					" FROM " + tableName + ",code AS c, codingsystem as cs, codingsystem_code as CSC "  +
					" WHERE recipientcode = '"+recipientCode+"' AND  to_year = '"+toYear+"' AND  from_year = '"+fromYear+"' " +
					" AND c.code=key_or AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
					" GROUP BY key_or, c.label");
				
			//LOGGER.info("getRecipientORsQuery: " + sb);
			
			return sb.toString();
	}
	
	//////

	private static StringBuffer addSQLCondition(String column, List<String> content, Boolean addAND) {
		StringBuffer sqlQuery = new StringBuffer();
//		if ( addAND )
//			sqlQuery.append(" AND ( ");
//		else 
//			sqlQuery.append(" ( ");
//		for(int i=0; i < content.size(); i++) {
//			if ( i != 0 )
//				sqlQuery.append("OR ");
//			
//			sqlQuery.append( column + " = '"+ content.get(i) + "' ");
//		}
//		sqlQuery.append(" )");
		
		if ( addAND )
			sqlQuery.append(" AND ");
		 
		sqlQuery.append(column + " IN (");
		for(int i=0; i < content.size(); i++) {
			if ( i != 0 )
				sqlQuery.append(", ");
			
			sqlQuery.append( " '"+ content.get(i) + "' ");
		}
		sqlQuery.append(" )");
		
		return sqlQuery;
	}
	
	private static StringBuffer addSQLCondition(String column, Map<String, String> content, Boolean addAND) {
		StringBuffer sqlQuery = new StringBuffer();
//		if ( addAND )
//			sqlQuery.append(" AND ( ");
//		else 
//			sqlQuery.append(" ( ");
//		int i=0;
//		for(String code: content.keySet()) {
//			if ( i != 0 )
//				sqlQuery.append("OR ");
//			
//			sqlQuery.append( column + " = '"+ code + "' ");
//			i++;
//		}
//		sqlQuery.append(" )");
		
		if ( addAND )
			sqlQuery.append(" AND ");
		 
		sqlQuery.append(column + " IN (");
		
		int i=0;
		for(String code: content.keySet()) {
			if ( i != 0 )
				sqlQuery.append(", ");
			
			sqlQuery.append( " '"+ code + "' ");
			i++;
		}
		sqlQuery.append(" )");
		
		return sqlQuery;
	}
	
	
	
	private StringBuffer addSQLDateCondition(String column, List<Date> dates) {
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(" AND ( ");
		for(int i=0; i < dates.size(); i++) {
			if ( i != 0 )
				sqlQuery.append("OR ");
			
			sqlQuery.append( column + " = '"+ dates.get(i) + "' ");
		}
		sqlQuery.append(" )");
		
		return sqlQuery;
	}
	
	
	public static String getLinks(String tablename, Map<String, List<String>> filters, String columnName, String columnLabel) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT " + columnName + ", " + columnLabel +  
				  " FROM "+ tablename+ " as ADAM " +
				  " WHERE ");
		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		
		sb.append(" GROUP BY " + columnName + ", " + columnLabel);
		
//		LOGGER.info("getLinks: " + sb);
		return sb.toString();
	}
	
//	public static String getLinks(String tablename, Map<String, List<String>> filters, String codingSystemCode, String columnName) {
//		StringBuilder sb = new StringBuilder();	
//
//		sb.append(" SELECT c.code, c.label " +
//				  " FROM "+ tablename+ " as ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC " +
//				  "	WHERE ADAM."+ columnName + "= c.code AND cs.code = '" + codingSystemCode + "' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
//		
//		for(String filter : filters.keySet() ) {
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		}
//
//		
//		sb.append(" GROUP BY c.label,  c.code ");
//		
////		LOGGER.info("getLinks: " + sb);
//		return sb.toString();
//	}
	
	
	
	public static String getProjectsList(String tablename, Map<String, String> selectsMap, Map<String, List<String>> filters, Integer limit, String language){
		
		//EXCEPT ALL = includes duplicated rows, i.e. crsid is the actual differentiator
		//EXCEPT = do not include duplicated rows
		/**SELECT  projecttitle, ROUND(CAST(extract(year from year) AS NUMERIC), 0) AS year,  donorname, agencyname, purposename, 
		CASE WHEN LENGTH(cast(usd_disbursement as varchar)) - position('.' in cast(usd_disbursement as varchar)) > 4 
		THEN ROUND(CAST( usd_disbursement as numeric ), 4) ELSE usd_disbursement END AS usd_disbursement,  
		CASE WHEN LENGTH(cast(usd_commitment as varchar)) - position('.' in cast(usd_commitment as varchar)) > 4 
		THEN ROUND(CAST( usd_commitment as numeric ), 4) ELSE usd_commitment END AS usd_commitment
		FROM
		(SELECT projecttitle, year,  donorname,  agencyname, purposename, usd_disbursement, usd_commitment
		FROM fao_sub_sectors_data 
		WHERE dac_sector IN ( '9999'  ) 
		AND recipientcode IN ( '625'  ) AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
		EXCEPT ALL
		SELECT projecttitle, year,  donorname,  agencyname, purposename, usd_disbursement, usd_commitment
		FROM fao_sub_sectors_data 
		WHERE dac_sector IN ( '9999'  ) 
		AND recipientcode IN ( '625'  )  
		AND ((usd_commitment IS NULL OR usd_commitment = 0)  AND (usd_disbursement IS NULL OR usd_disbursement = 0))  
		AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
		) removedNullsAndZeroesResult
		ORDER BY year DESC, usd_disbursement DESC  **/
		
        StringBuilder sb = new StringBuilder();		
        StringBuilder cleanedSelectsSb = new StringBuilder();	
        StringBuilder filtersSb = new StringBuilder();	
        
        //selects for nested query, use key
        int i = 0;
        for(String select : selectsMap.keySet()){ 
			cleanedSelectsSb.append(select);
			
			if ( i < selectsMap.size() -1) {
				cleanedSelectsSb.append(", ");
			}
			cleanedSelectsSb.append(" ");
			i++;
		}
        
        //Create filter query
		int j = 0;		
		for(String filter : filters.keySet() ){ 
			if ( j == 0 )
				filtersSb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				filtersSb.append(addSQLCondition(filter, filters.get(filter), true));
			j++;
		}
		
		//START QUERY CREATION       
		sb.append("SELECT " ); // uses values from select Map
		int k=0;
		for(String select : selectsMap.keySet()){ 
			sb.append(selectsMap.get(select));
			if ( k < selectsMap.size() -1) {
				sb.append(", ");
			}
			sb.append(" ");
			k++;
		}
		sb.append(" FROM ");
		sb.append("(");
		sb.append(" SELECT "+ cleanedSelectsSb.toString());
		sb.append(" FROM "+ tablename);
        sb.append(" WHERE "+filtersSb.toString());
	    sb.append(" EXCEPT ");
	    sb.append(" SELECT "+ cleanedSelectsSb.toString());
		sb.append(" FROM "+ tablename);
		sb.append(" WHERE "+filtersSb.toString());
		sb.append(" AND ((usd_commitment IS NULL OR usd_commitment = 0)  AND (usd_disbursement IS NULL OR usd_disbursement = 0))"); 
		sb.append(") removedNullsAndZeroesResult");
		sb.append(" ORDER BY year DESC, usd_disbursement DESC ");
		
		LOGGER.info("getProjectsList query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String getProjectsListOriginal(String tablename, List<String> selects, Map<String, List<String>> filters, Integer limit, String language) {
		StringBuilder sb = new StringBuilder();	

		
		sb.append("SELECT " );
		int j=0;
		for(String select : selects){ 
			sb.append(select);
			if ( j < selects.size() -1) {
				sb.append(", ");
			}
			sb.append(" ");
			j++;
		}
		
//		sb.append(" donorname AS DONOR, recipientname AS RECIPIENT, dac_label AS SECTOR , purposename AS PURPOSE_SECTOR, channelname AS CHANNEL, channelreportedname AS CHANNELREPORTEDNAME, projecttitle AS PROJECT_TITLE, year AS YEAR, usd_disbursement, usd_commitment " +
		sb.append(" FROM "+ tablename  +" as ADAM");
				  
		sb.append(" WHERE ");
		
		
		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		sb.append(" ORDER BY year DESC, usd_disbursement DESC ");
		
//		sb.append(" SELECT d.label AS DONOR, r.label AS RECIPIENT, s.label AS SECTOR , ss.label AS PURPOSE_SECTOR, cc.label AS CHANNEL, channelreportedname AS CHANNELREPORTEDNAME, projecttitle AS PROJECT_TITLE, year AS YEAR, usd_disbursement, usd_commitment " +
//				  " FROM "+ tablename  +" as ADAM, code AS r, codingsystem as csr, codingsystem_code as csrr, code AS d, codingsystem as csd, codingsystem_code as csrd, " +
//				  " code AS s, codingsystem as css, codingsystem_code as csrs, code AS cc, codingsystem as cscc, codingsystem_code as csrcc," +
//				  " code AS ss, codingsystem as csss, codingsystem_code as csrss  ");
//				  
//		sb.append(" WHERE ADAM.recipientcode = r.code AND csr.code = 'CRS_RECIPIENTS' AND csr.resourceid = csrr.codingsystem_resourceid AND r.id = csrr.codes_id AND r.langcode = '" + language + "' " + 
//				  " AND   ADAM.donorcode = d.code AND csd.code = 'CRS_DONORS' AND csd.resourceid = csrd.codingsystem_resourceid AND d.id = csrd.codes_id AND d.langcode = '" + language + "' " + 
//				  " AND   ADAM.dac_sector  = s.code AND css.code = 'CRS_SECTORS' AND css.resourceid = csrs.codingsystem_resourceid AND s.id = csrs.codes_id AND s.langcode = '" + language + "' " +
//				  " AND   ADAM.channelcode  = cc.code AND cscc.code = 'CRS_CHANNELS' AND cscc.resourceid = csrcc.codingsystem_resourceid AND cc.id = csrcc.codes_id AND cc.langcode = '" + language + "' " +
//				  " AND   ADAM.purposecode  = ss.code AND csss.code = 'CRS_PURPOSES' AND csss.resourceid = csrss.codingsystem_resourceid AND ss.id = csrss.codes_id AND ss.langcode = '" + language + "' ");
//				
//		for(String filter : filters.keySet() ) 
//			sb.append(addSQLCondition(filter, filters.get(filter), true));
//		
//		
//		sb.append(" AND usd_disbursement != 0 " +
//				  " ORDER BY usd_disbursement DESC ");
//		
//		if ( limit != null ) {
//			sb.append(" LIMIT "+ limit);
//		}
		
		
		LOGGER.info("getProjectsList query: " + sb);
		
		return sb.toString();
	}
	
	public static String getRowsCount(String tablename, Map<String, List<String>> filters) {
		StringBuilder sb = new StringBuilder();	

		
		sb.append(" SELECT count(*) " +
				  " FROM "+ tablename + " ");
				  
		sb.append(" WHERE ");
		
		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		
		LOGGER.info("getRowsCount query: " + sb);
		
		return sb.toString();
	}
	

	public static String getRowsCountEliminatingEmptyValueRows(String tablename, Map<String, List<String>> filters) {
		StringBuilder sb = new StringBuilder();	

		StringBuilder filtersSb = new StringBuilder();	
		//Create filter query
		int j = 0;		
		for(String filter : filters.keySet() ){ 
			if ( j == 0 )
				filtersSb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				filtersSb.append(addSQLCondition(filter, filters.get(filter), true));
			j++;
		}

		sb.append(" SELECT count(*) ");
		sb.append(" FROM( ");
		sb.append(" SELECT * ");
		sb.append(" FROM "+ tablename + " "); 
		sb.append(" WHERE "+filtersSb.toString());
		sb.append(" EXCEPT ALL "); // gives duplicated rows, i.e. where crsid is the differentiator
		sb.append(" SELECT * ");
		sb.append(" FROM "+ tablename + " "); 
		sb.append(" WHERE "+filtersSb.toString());
		sb.append(" AND ((usd_commitment IS NULL OR usd_commitment = 0)  AND (usd_disbursement IS NULL OR usd_disbursement = 0))");  
		sb.append(") countRows ");

		LOGGER.info("getRowsCountEliminatingEmptyValueRows query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String getDonorProfileTableNames() {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT tablename "  +  
				  " FROM customdataset "+ 
				  " WHERE code in ('ADAM_DONOR_PROCESSES', 'ADAM_DONOR_PROFILE_DELIVERIES', 'ADAM_DONOR_PROFILE_FAOREGION_RECIPIENTS', 'ADAM_DONOR_PROFILE')");
		LOGGER.info("getLingetDonorProfileTableNames: " + sb);
		return sb.toString();
	}
	
	
	public static String getDonorProfileTableName() {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT tablename "  +  
				  " FROM customdataset "+ 
				  " WHERE code ='ADAM_DONOR_PROFILE'");
		LOGGER.info("getDonorProfileTableName: " + sb);
		return sb.toString();
	}
	
	public static String getDonorProcessesTableName() {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT tablename "  +  
				  " FROM customdataset "+ 
				  " WHERE code ='ADAM_DONOR_PROCESSES'");
		LOGGER.info("getDonorProcessesTableNames: " + sb);
		return sb.toString();
	}
	
	public static String getDonorDeliveriesTableName() {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT tablename "  +  
				  " FROM customdataset "+ 
		 		  " WHERE code ='ADAM_DONOR_PROFILE_DELIVERIES'");
		LOGGER.info("getDonorDeliveriesTableNames: " + sb);
		return sb.toString();
	}
	
	public static String getDonorRegionalCountriesTableName() {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT tablename "  +  
				  " FROM customdataset "+ 
		 		  " WHERE code ='ADAM_DONOR_PROFILE_FAOREGION_RECIPIENTS'");
		LOGGER.info("getDonorRegionalCountriesTableName: " + sb);
		return sb.toString();
	}
	
	
	public static String getDonorsWithProfiles(String tableName) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT reference_date, donorcode, c.label"  +  
				" FROM "+ tableName + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				" WHERE donorcode=c.code AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
		         
		LOGGER.info("getDonorProfile: " + sb);
		return sb.toString();
	}
	
	public static String getDonorProfile(String donorcode, String tableName, String selectedDataset) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT reference_date, overall_priority_themes, type_of_funding_arrangements_favoured,  funding_bodies_within_resource_partner, external_links, resource_mobilization_officer, resource_mobilization_officer_email, overall_priority_geographical_areas, "  +  
				  " channels_of_cooperation_approach, annual_funding_cycle, process_of_application_and_negotiation, policy_on_the_approval_of_budget_revision, policy_on_the_use_of_accrued_interest, special_characteristics_features " +
				  " FROM "+ tableName + " WHERE ");
		
				  if(selectedDataset.equals(ADAMSelectedDataset.ADAM_FPMIS.toString()))
					  sb.append("fpmis_donorcode = '"+donorcode+"'");
				  else
					  sb.append("donorcode = '"+donorcode+"'");
		
		LOGGER.info("getDonorProfile: " + sb);
		return sb.toString();
	}

	
	
	public static String getDonorProfile_ORIGINAL(String donorcode, String tableName) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT max(reference_date), overall_priority_themes, type_of_funding_arrangements_favoured, funding_bodies_within_resource_partner, external_links, resource_mobilization_officer, resource_mobilization_officer_email, overall_priority_geographical_areas "  +  
				 " FROM "+ tableName +
				  " WHERE donorcode = '"+donorcode+"'" +
				  " GROUP BY overall_priority_themes, type_of_funding_arrangements_favoured,  funding_bodies_within_resource_partner, external_links, resource_mobilization_officer, resource_mobilization_officer_email, overall_priority_geographical_areas"); 
		         
		LOGGER.info("getDonorProfile: " + sb);
		return sb.toString();
	}
	
	public static String getDonorProcesses(String donorcode, String tableName) {
		StringBuilder sb = new StringBuilder();	
  
		sb.append(" SELECT reference_date, channels_of_cooperation, process_of_application_and_negotiation,  annual_funding_cycle, special_characteristics, budget_revision, accrued_interest "  +  
				  " FROM "+ tableName +
				  " WHERE donorcode = '"+donorcode+"'"); 
		         
		LOGGER.info("getDonorProcesses: " + sb);
		return sb.toString();
	}
	
	public static String getDonorDeliveries(String donorcode, String tableName) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT delivery_year, delivery_by_sources_of_external_funding_in_usd_million "  +  
				  " FROM "+ tableName +
				  " WHERE donorcode = '"+donorcode+"'"); 
		LOGGER.info("getDonorDeliveries: " + sb);
		return sb.toString();
	}
	
	public static String getDonorRegionalCountries(String donorcode, String tableName) {
		StringBuilder sb = new StringBuilder();	
  
		sb.append(" SELECT from_year, to_year, fao_region, recipient_countries "  +  
				" FROM "+ tableName +
				" WHERE donorcode = '"+donorcode+"'"); 
		         
		LOGGER.info("getDonorRegionalCountries: " + sb);
		return sb.toString();
	}
	
	public static String getDonorKeySOs(String donorcode, String tableName) {
		StringBuilder sb = new StringBuilder();	
  
		sb.append("SELECT DISTINCT(key_so), from_year, to_year, c.label"  +  
				" FROM "+ tableName + ", code AS c, codingsystem AS cs, codingsystem_code as CSC" +
				" WHERE donorcode = '"+donorcode+"' AND key_so=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'" +
		        " GROUP BY key_so, c.label, from_year, to_year ORDER BY key_so"); 
		LOGGER.info("getDonorKeySOs: " + sb);
		return sb.toString();
	}
	
	// VENN
	
	//PRIORITIES
	
	
	
	public static String getPriorities(String tablename, Map<String, List<String>> filters, Map<String, String> recipients, String dataset, String conversiontablename, String csType) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT recipientcode, c1.label as recipient, key_or, c.label, stated_priority, source, comment, link ");		
		sb.append( " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as csc, code AS c1, codingsystem AS cs1, codingsystem_code as csc1 "  +  
				  " WHERE key_or=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
		          " AND recipientcode = c1.code AND cs1.code = 'ADAM_RECIPIENTS' AND cs1.resourceid = csc1.codingsystem_resourceid AND c1.id = csc1.codes_id AND c1.langcode = 'EN'") ;


		//sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename+" WHERE  ");
		//sb.append(addSQLCondition(dataset+"_code", recipients, false));
		//sb.append(" ) ");
		
		if(recipients!=null && !recipients.isEmpty())
			sb.append(addSQLCondition("recipientcode", recipients, true));
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		
		sb.append(" ORDER BY recipientcode, key_or");
		
		LOGGER.info("getPriorities query: " + sb);
		
		return sb.toString();
	}
	
	public static String convertToDAC(Map<String, String> recipients, String dataset, String conversiontablename) {
		StringBuilder sb = new StringBuilder();	
		
	    sb.append("SELECT adam_crs_code, "+dataset+"_label from "+conversiontablename+" WHERE  ");
		sb.append(addSQLCondition(dataset+"_code", recipients, false));
		sb.append(" ORDER BY adam_crs_code ");
		
		LOGGER.info("convertToDAC query: " + sb);
		
		return sb.toString();
	}
	
	public static String getDACPriorities(String tablename, String conversiontablename, Map<String, List<String>> filters, Map<String, String> recipients) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT recipientcode, c1.label as recipient, dac, c.label, stated_priority, source, comment, link ");
		sb.append( " FROM "+ tablename + ", "+conversiontablename+", code AS c, codingsystem AS cs, codingsystem_code as csc, code AS c1, codingsystem AS cs1, codingsystem_code as csc1 "  +  
				  " WHERE fao = key_or AND dac=c.code AND cs.code = 'ADAM_PURPOSES' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
		          " AND recipientcode = c1.code AND cs1.code = 'ADAM_RECIPIENTS' AND cs1.resourceid = csc1.codingsystem_resourceid AND c1.id = csc1.codes_id AND c1.langcode = 'EN'") ;

		if(recipients!=null && !recipients.isEmpty())
		 sb.append(addSQLCondition("recipientcode", recipients, true));
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		
		sb.append(" ORDER BY recipientcode, dac");
		
		LOGGER.info("getDACPriorities query: " + sb);
		
		return sb.toString();
	}
	
	
	
	// RECIPIENTS
	public static String getRecipientsORPriorities(String tablename, String conversiontablename, Map<String, List<String>> filters, Map<String, String> recipients, String language, String dataset) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(key_or), c.label"  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE key_or=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
		
		
		/*if ( !recipients.isEmpty()) {
			sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename+" WHERE  ");
			sb.append(addSQLCondition(dataset+"_code", recipients, false));
			sb.append(" ) ");
		}*/
		
		sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename);
		
		if(!recipients.isEmpty()){
			sb.append(" WHERE "); 	
			sb.append(addSQLCondition(dataset+"_code", recipients, false));			
		}
			
		sb.append(" ) ");
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		
		sb.append(" ORDER BY key_or");
		
		LOGGER.info("getRecipientsORPriorities query: " + sb);
		
		return sb.toString();
	}
	
	public static String getRecipientsDACPriorities(String tablename, Map<String, List<String>> filters, Map<String, String> recipients, String language) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(dac), c.label"  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE dac=c.code AND cs.code = 'ADAM_PURPOSES' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
			
		if(recipients!=null && !recipients.isEmpty())
		  sb.append(addSQLCondition("recipientcode", recipients, true));
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		LOGGER.info("getRecipientsDACPriorities query: " + sb);
		
		return sb.toString();
	}
	
	// this returns all SOs that containts all the ORs and not a filtered subset
	public static String getRecipientsWholeSO(String tablename, String conversiontablename, Map<String, List<String>> filters, Map<String, String> recipients, String language, String dataset) {
		StringBuilder sb = new StringBuilder();	
		
		// the coding system can be removed
		sb.append(" SELECT DISTINCT(key_so), c.label "  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE key_so=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
				  " AND key_or is null ") ;
			
			
		/*if ( !recipients.isEmpty()) {
			sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename+" WHERE  ");
			sb.append(addSQLCondition(dataset+"_code", recipients, false));
			sb.append(" ) ");
		}*/
		sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename);
		if(!recipients.isEmpty()){
			sb.append(" WHERE "); 	
			sb.append(addSQLCondition(dataset+"_code", recipients, false));			
		}
		sb.append(" ) ");
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		LOGGER.info("getRecipientsWholeSO query: " + sb);
		
		return sb.toString();
	}
	
	
	// this should changed in order to get the SO, and OR,
	public static String getAllRecipientsPrioritiesFAO(String tablename, Map<String, List<String>> filters, Map<String, String> recipients) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT ADAM.key_so, ADAM.key_or, ADAM.recipientcode, c.label "  +  
				  " FROM "+ tablename + " ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC ");
			
		sb.append(" WHERE ADAM.recipientcode = c.code AND cs.code = 'ADAM_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'");
				
		
		if(recipients!=null && !recipients.isEmpty())
			sb.append(addSQLCondition("recipientcode", recipients, true));
		
		
		LOGGER.info("getAllRecipientsPriorities query: " + sb);
		
		return sb.toString();
	}
	

	public static String getALLORsFromSOs(List<String> sos) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT c1.code, c1.label " +
				  " FROM code AS c1, codingsystem AS cs1, codingsystem_code as CSC1 " +
				  " WHERE cs1.code = 'FAO_SO' AND cs1.resourceid = csc1.codingsystem_resourceid AND c1.id = csc1.codes_id AND c1.langcode = 'EN' ");


		int i=0;
		sb.append("AND ( ");
		for(String so : sos) {
			if ( i != 0 )
				sb.append("OR ");
			
			sb.append( " c1.code like '" + so + "_' "); //previously "__' because OR = A01, now A1
			i++;
		}
		sb.append(" )");
			
		LOGGER.info("getALLORsFromSOs query: " + sb);
		
		return sb.toString();
	}
	

	public static String getDACCodesLabels(List<String> dacs) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT c1.code, c1.label " +
				  " FROM code AS c1, codingsystem AS cs1, codingsystem_code as CSC1 " +
				  " WHERE cs1.code = 'ADAM_PURPOSES' AND cs1.resourceid = csc1.codingsystem_resourceid AND c1.id = csc1.codes_id AND c1.langcode = 'EN' ");


		int i=0;
		sb.append("AND ( ");
		for(String dac : dacs) {
			if ( i != 0 )
				sb.append("OR ");
			
			sb.append( " c1.code like '" + dac + "' ");
			i++;
		}
		sb.append(" )");
		
		
		LOGGER.info("getDACCodesLabels query: " + sb);
		
		return sb.toString();
	}
	
//	select count(distinct(recipientcode)) from FAO_KEY_SOS_AND_ORS_b353496d where recipientcode IN ( '228', '266') ;
	public static String checkCFPRecipientsNumber(String tablename, String conversiontablename, Map<String, List<String>> filters, Map<String, String> channels, Map<String, String> recipients, String language, String dataset) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT COUNT(DISTINCT(recipientcode)) "  +  
				  " FROM "+ tablename + 
				  " WHERE ") ;
				
		sb.append(addSQLCondition("channelcode", channels, false));
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename+" WHERE  ");
				sb.append(addSQLCondition(dataset+"_code", recipients, false));
				sb.append(" ) ");
				
				//sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}

		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		LOGGER.info("checkCFPRecipientsNumber query: " + sb);
		
		return sb.toString();
	}
	
	public static String getChannelsORPriorities(String tablename, String conversiontablename, Map<String, List<String>> filters, Map<String, String> channels, Map<String, String> recipients, String language, String dataset) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(key_or), c.label"  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE key_or=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
				
		sb.append(addSQLCondition("channelcode", channels, true));
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename+" WHERE  ");
				sb.append(addSQLCondition(dataset+"_code", recipients, false));
				sb.append(" ) ");
				//sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}

		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		LOGGER.info("getChannelsORPriorities query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String getChannelsVennMatrixORPriorities(String tablename, Map<String, List<String>> filters, Map<String, String> channels, Map<String, String> recipients, String language) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT recipientcode, key_or, c.label, stated_priority, source"  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE key_or=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
				
		sb.append(addSQLCondition("channelcode", channels, true));
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}

		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		sb.append(" ORDER BY key_or");
		
		
		LOGGER.info("query: " + sb);
		
		return sb.toString();
	}
	
	// this returns all SOs that containts all the ORs and not a filtered subset
	public static String getChannelsSOPriorities(String tablename, String conversiontablename, Map<String, List<String>> filters, Map<String, String> channels, Map<String, String> recipients, String language, String dataset) {
		StringBuilder sb = new StringBuilder();	
		
		// the coding system can be removed
		sb.append(" SELECT DISTINCT(key_so), c.label "  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE key_so=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
				  " AND key_or is null ") ;
				
		sb.append(addSQLCondition("channelcode", channels, true));
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename+" WHERE  ");
				sb.append(addSQLCondition(dataset+"_code", recipients, false));
				sb.append(" ) ");
				
				//sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}

		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		LOGGER.info("getChannelsSOPriorities query: " + sb);
		
		return sb.toString();
	}
	
	// this returns all SOs that containts all the ORs and not a filtered subset
	public static String getChannelsWholeORs(String tablename, Map<String, List<String>> filters, Map<String, String> channels, String language) {
		StringBuilder sb = new StringBuilder();	
		
		// the coding system can be removed
		sb.append(" SELECT DISTINCT(key_or), c.label "  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE key_or=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
		 		  " AND comment = 'GLOBAL' ") ;
				
		sb.append(addSQLCondition("channelcode", channels, true));
		
		
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		LOGGER.info("getChannelsWholeORs query: " + sb);
		
		return sb.toString();
	}
	
	public static String getDonorsPrioritiesORs(String tablename, Map<String, List<String>> filters, Map<String, String> donors, Map<String, String> recipients, String sourceType, String language) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(key_or), c.label"  +  
				" FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				" WHERE key_or=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
			
		if (sourceType != null ) {
			sb.append(" AND comment  = '" + sourceType +"'");
		}
		
		if( !donors.isEmpty()) 
			sb.append(addSQLCondition("donorcode", donors, true));
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}

		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}
		

		LOGGER.info("getDonorsPrioritiesORs query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String getAllVennDonors(String tablename, Map<String, List<String>> filters, Map<String, String> donors, Map<String, String> recipients, String sourceType, String language, String csType) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT donorcode, c.label"  +  
				" FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				" WHERE donorcode=c.code AND cs.code = '"+csType+"_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
		
		if (sourceType != null ) {
			sb.append(" AND comment  = '" + sourceType +"'");
		}
		
		if( !donors.isEmpty()) 
			sb.append(addSQLCondition("donorcode", donors, true));
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}
		
		

		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}
		
		
		sb.append(" ORDER BY c.label ");
		
		LOGGER.info("getAllVennDonors query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String getAllVennRecipientDonors(String tablename, Map<String, List<String>> filters, Map<String, String> donors, Map<String, String> recipients, String sourceType, String language) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT recipientcode, donorcode, key_or, label "  +  
				" FROM "+ tablename + ", code AS c, codingsystem as cs, codingsystem_code as CSC " +
				" WHERE " );
		sb.append(" key_or =c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id ");
		sb.append(" AND c.langcode = 'EN' ");
		
		if (sourceType != null ) {
			sb.append(" AND comment  = '" + sourceType +"'");
		}
		
		if( !donors.isEmpty()) {
			sb.append(addSQLCondition("donorcode", donors, true));
		}
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}
				
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}
		
		

		
		
		LOGGER.info("getAllVennDonors query: " + sb);
		
		return sb.toString();
	}
	
	public static String getAllVennRecipientDonorsDAC(String conversiontablename, String tablename, Map<String, List<String>> filters, Map<String, String> donors, Map<String, String> recipients, String sourceType, String language) {
		StringBuilder sb = new StringBuilder();	
				sb.append(" SELECT recipientcode, donorcode, dac, label "  +  
				" FROM "+ tablename + ", "+conversiontablename+", code AS c, codingsystem as cs, codingsystem_code as CSC " +
				" WHERE " );
	   sb.append(" fao = key_or AND dac=c.code AND cs.code = 'ADAM_PURPOSES' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id ");
       sb.append(" AND c.langcode = 'EN' ");
				
       if (sourceType != null ) {
    	   sb.append(" AND comment  = '" + sourceType +"'");
       }
		
       if( !donors.isEmpty()) {
			sb.append(addSQLCondition("donorcode", donors, true));
		}
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}
		
		
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}
		
		
		
		
		LOGGER.info("getAllVennRecipientDonorsDAC query: " + sb);
		
		return sb.toString();
	}
	
	// this should changed in order to get the SO, and OR,
	public static String getAllDonorsPriorities(String tablename, Map<String, List<String>> filters, Map<String, String> donors, Map<String, String> recipients, String sourceType, String dataset) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT ADAM.key_so, ADAM.key_or, ADAM.donorcode, c.label "  +  
				  " FROM "+ tablename + " ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC ");
			
		sb.append(" WHERE ADAM.donorcode = c.code AND cs.code = '"+dataset+"_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'");
				
		if (sourceType != null ) {
			sb.append(" AND comment  = '" + sourceType +"'");
		}
		
		if ( !donors.isEmpty() ) {
			sb.append(addSQLCondition("donorcode", donors, true));
		}
		
		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}
		
		
		
		LOGGER.info("getAllDonorsPriorities query: " + sb);
		
		return sb.toString();
	}
	
	// this returns all SOs that containts all the ORs and not a filtered subset
	public static String getDonorsWholeSOs(String tablename, Map<String, List<String>> filters, Map<String, String> donors, Map<String, String> recipients, String sourceType, String language) {
		StringBuilder sb = new StringBuilder();	
		
		// the coding system can be removed
		sb.append(" SELECT DISTINCT(key_so), c.label "  +  
				  " FROM "+ tablename + ", code AS c, codingsystem AS cs, codingsystem_code as CSC " +
				  " WHERE key_so=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' " +
				  " AND key_or is null ") ;
			
		if (sourceType != null ) {
			sb.append(" AND comment  = '" + sourceType +"'");
		}
		
		if( !donors.isEmpty()) 
			sb.append(addSQLCondition("donorcode", donors, true));

		if ( recipients != null) {
			if ( !recipients.isEmpty()) {
				sb.append(addSQLCondition("recipientcode", recipients, true));
			}
		}
		
	
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}
		
		

		LOGGER.info("getDonorsWholeSOs query: " + sb);
		
		return sb.toString();
	}
	
	// CONVERTION ORs to DACs
	public static String getDACfromORs(String tablename, Map<String, String> ors) {
		StringBuilder sb = new StringBuilder();	
		
		// the coding system can be removed
		sb.append(" SELECT dac, fao "  +  
				  " FROM "+ tablename + " ");
		
		sb.append(" WHERE ");
		
		sb.append(addSQLCondition("fao", ors, false));
		
		sb.append(" AND fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
		

		LOGGER.info("getDACfromORs query: " + sb);
		
		return sb.toString();
	}
	
	
	public static String buildOR_Donorquery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		
		/**
		 * 
		Build OR Revised Query: Total Budget for purposecode is now divided by the number of matching ORs for that purposecode  
		e.g. DAC 1234 has a budget of 20 Million
		     DAC 1234 is associated with ORs A01, B01, C01, D01
		
		So new Query: Will assign 5 million to each OR (20/4)
		Old Query assigned: 20 Million to each OR
		 
		 SELECT t.fao, t.donorname, ROUND(SUM(t.TOTAL), 2) AS SUM from 
		 (SELECT CONV.fao, DATA.donorname, SUM(CAST( usd_commitment as numeric ) )  / OCCURRENCES.count AS TOTAL FROM FAO_DAC_CONVERSION_TABLE_09b403b0 AS CONV, ADAM_CRS_e599fc32 AS DATA  INNER JOIN (  SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_09b403b0  GROUP BY dac ) AS OCCURRENCES  ON DATA.purposecode = OCCURRENCES.dac  WHERE donorcode IN ( '701'  ) AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) AND DATA.purposecode = CONV.dac  AND (  CONV.fao = 'C4' OR  CONV.fao = 'C3' OR  CONV.fao = 'C6' OR  CONV.fao = 'C5' OR  CONV.fao = 'C1' OR  CONV.fao = 'C2'  ) AND usd_commitment > 0  AND CONV.fao !~* '.00'  GROUP BY CONV.dac, CONV.fao, DATA.donorname, OCCURRENCES.count  ORDER BY TOTAL DESC) t 
		 GROUP BY t.fao, t.donorname 
		 ORDER BY SUM DESC
		
		**/
		
		
		// limit query
		Integer limit = 10;
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		 
		// QUERY
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT t.fao, t.donorname, ROUND(SUM(t.SUM), 2) AS TOTAL FROM "); 
		sb.append(" (SELECT CONV.fao, DATA.donorname, " +buildAggregationFunction(aggregationType, quantityColumn)+" / OCCURRENCES.count " + " AS " + aggregationType +
				  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
		
		sb.append(" INNER JOIN ( " + 
				  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");
		
		/*if(!qvo.getPriorities().isEmpty()) {
			sb.append(" WHERE ");  
			int k=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( k != 0 )
					sb.append("OR ");

				sb.append( " fao = '"+ code + "' ");
				k++;
			}
		}*/
				
		sb.append(" GROUP BY dac ) AS OCCURRENCES ");
		sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
		sb.append(" WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		
		// FILTER ON PURPOSE CODE
		sb.append(" AND DATA.purposecode = CONV.dac ");

		if(!qvo.getPriorities().isEmpty()) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");

				sb.append( " CONV.fao = '"+ code + "' ");
				j++;
			}
			sb.append(" )");
		}
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' ");

		
		sb.append(" GROUP BY CONV.dac, CONV.fao, DATA.donorname, OCCURRENCES.count " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 

		
		 sb.append(" ) t ");
		 sb.append(" GROUP BY t.fao, t.donorname ");
		 sb.append(" ORDER BY TOTAL DESC ");

		 //remove the limit
		 //if ( limit != null || limit != 0 )
			// sb.append(" LIMIT  "+ limit);
		 
		 LOGGER.info("*** buildOR_Donorquery *** "+ sb.toString());
		
		return sb.toString();
	}
	
	
	public static String buildOriginalOR_Donorquery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		// limit query
		Integer limit = 10;
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT CONV.fao, DATA.donorname, " +  buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA " +
				  "	WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		
		// FILTER ON PURPOSE CODE
		sb.append(" AND DATA.purposecode = CONV.dac ");
		
		sb.append(" AND ( ");
		int j=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( j != 0 )
				sb.append("OR ");
			
			sb.append( " CONV.fao = '"+ code + "' ");
			j++;
		}
		sb.append(" )");

		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' ");

		
		sb.append(" GROUP BY CONV.fao, DATA.donorname " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 

//		 if ( limit != null || limit != 0 )
//			 sb.append(" LIMIT  "+ limit);
			
//		SELECT CONV.fao, SUM(usd_disbursement) AS SUM
//		FROM FAO_DAC_CONVERSION_TABLE_240c9a0e CONV, OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be DATA 
//		WHERE ( DATA.recipientcode = '130' ) AND ( DATA.year = '2008-01-01' ) AND DATA.purposecode = CONV.dac AND ( CONV.fao = 'E05' OR  CONV.fao = 'E04' OR CONV.fao = 'A02' OR CONV.fao = 'A01' OR CONV.fao = 'B01' OR CONV.fao = 'B02' OR CONV.fao = 'B03' )
//		GROUP BY CONV.fao HAVING SUM(usd_disbursement) != 0 ORDER BY SUM DESC LIMIT 10;
		
		
		return sb.toString();
	}
	
	public static String buildDAC_Donorquery(String oecdtablename, ADAMQueryVO qvo) {
		// limit query
		Integer limit = 10;
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT DATA.purposecode, DATA.donorname, " +  buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM " + oecdtablename +" AS DATA " +
				  "	WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

			
		/**sb.append(" AND ( ");
		int j=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( j != 0 )
				sb.append("OR ");
			
			sb.append( " DATA.purposecode = '"+ code + "' ");
			j++;
		}
		sb.append(" )");**/

		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");

		
		sb.append(" GROUP BY DATA.purposecode, DATA.donorname " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 

//		 if ( limit != null || limit != 0 )
//			 sb.append(" LIMIT  "+ limit);
			
//		SELECT CONV.fao, SUM(usd_disbursement) AS SUM
//		FROM FAO_DAC_CONVERSION_TABLE_240c9a0e CONV, OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be DATA 
//		WHERE ( DATA.recipientcode = '130' ) AND ( DATA.year = '2008-01-01' ) AND DATA.purposecode = CONV.dac AND ( CONV.fao = 'E05' OR  CONV.fao = 'E04' OR CONV.fao = 'A02' OR CONV.fao = 'A01' OR CONV.fao = 'B01' OR CONV.fao = 'B02' OR CONV.fao = 'B03' )
//		GROUP BY CONV.fao HAVING SUM(usd_disbursement) != 0 ORDER BY SUM DESC LIMIT 10;
		
		
		return sb.toString();
	}
	
	
	
	
	public static String buildORquery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		
		/**
		 * 
		Build OR Revised Query: Total Budget for purposecode is now divided by the number of matching ORs for that purposecode  
		e.g. DAC 1234 has a budget of 20 Million
		     DAC 1234 is associated with ORs A01, B01, C01, D01
		
		So new Query: Will assign 5 million to each OR (20/4)
		Old Query assigned: 20 Million to each OR
		 
		Illustrative query below, indicating more columns e.g purposecode and total sum: 
		
		SELECT purposecode, CONV.fao,  ROUND( SUM(  CAST( usd_commitment as numeric ) ) ,2) AS TOTAL, ROUND(SUM(  CAST( usd_commitment as numeric ) ) / Codes.count, 2) AS SUM
		FROM FAO_DAC_CONVERSION_TABLE_25126d47 AS CONV, ADAM_CRS_68379c5f AS DATA
		INNER JOIN
		(
		SELECT dac, count(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_25126d47 AS CONV	
		WHERE dac IN ( '31162' ,  '31291' ,  '31192' ,  '31381' ,  '43040' ,  '31195' ,  '31261' ,  '33110' ,  '25010' ,  '15160' ,  '41030' ,  '41020' ,  '31166' ,  '12240' ,  '31182' ,  '32161' ,  '31281' ,  '31193' ,  '72040' ,  '31163' ,  '14015' ,  '14010' ,  '31210' ,  '41010' ,  '52010' ,  '74010' ,  '31282' ,  '15170' ,  '31130' ,  '31161' ,  '31140' ,  '31194' ,  '31120' ,  '16062' ,  '31382' ,  '31391' ,  '14040' ,  '31320' ,  '41082' ,  '31310' ,  '31110' ,  '73010' ,  '31150' ,  '31220' ,  '31164' ,  '31181' ,  '31191'  ) 
		GROUP BY dac) AS Codes
		ON DATA.purposecode = Codes.dac
		WHERE year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
		AND purposecode IN ( '31162' ,  '31291' ,  '31192' ,  '31381' ,  '43040' ,  '31195' ,  '31261' ,  '33110' ,  '25010' ,  '15160' ,  '41030' ,  '41020' ,  '31166' ,  '12240' ,  '31182' ,  '32161' ,  '31281' ,  '31193' ,  '72040' ,  '31163' ,  '14015' ,  '14010' ,  '31210' ,  '41010' ,  '52010' ,  '74010' ,  '31282' ,  '15170' ,  '31130' ,  '31161' ,  '31140' ,  '31194' ,  '31120' ,  '16062' ,  '31382' ,  '31391' ,  '14040' ,  '31320' ,  '41082' ,  '31310' ,  '31110' ,  '73010' ,  '31150' ,  '31220' ,  '31164' ,  '31181' ,  '31191'  ) 
		AND DATA.purposecode = CONV.dac  AND usd_commitment > 0  AND CONV.fao !~* '.00'  
		GROUP BY CONV.fao, DATA.purposecode, Codes.count 
		ORDER BY SUM DESC 
		
		*SELECT CONV.fao, CONV.fao, ROUND (SUM(  CAST( usd_commitment as numeric )) / OCCURRENCES.count, 2) AS SUM  FROM FAO_DAC_CONVERSION_TABLE_25126d47 AS CONV, ADAM_CRS_68379c5f AS DATA  
INNER JOIN ( SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_25126d47  WHERE dac IN ( '31162' ,  '31291' ,  '31192' ,  '31381' ,  '43040' ,  '31195' ,  '31261' ,  '33110' ,  '25010' ,  '15160' ,  '41030' ,  '41020' ,  '31166' ,  '12240' ,  '31182' ,  '32161' ,  '31281' ,  '31193' ,  '72040' ,  '31163' ,  '14015' ,  '14010' ,  '31210' ,  '41010' ,  '52010' ,  '74010' ,  '31282' ,  '15170' ,  '31130' ,  '31161' ,  '31140' ,  '31194' ,  '31120' ,  '16062' ,  '31382' ,  '31391' ,  '14040' ,  '31320' ,  '41082' ,  '31310' ,  '31110' ,  '73010' ,  '31150' ,  '31220' ,  '31164' ,  '31181' ,  '31191'  ) GROUP BY dac ) AS OCCURRENCES  
ON DATA.purposecode = OCCURRENCES.dac  
WHERE recipientcode IN ( '625'  ) AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) AND purposecode IN ( '31162' ,  '31291' ,  '31192' ,  '31381' ,  '43040' ,  '31195' ,  '31261' ,  '33110' ,  '25010' ,  '15160' ,  '41030' ,  '41020' ,  '31166' ,  '12240' ,  '31182' ,  '32161' ,  '31281' ,  '31193' ,  '72040' ,  '31163' ,  '14015' ,  '14010' ,  '31210' ,  '41010' ,  '52010' ,  '74010' ,  '31282' ,  '15170' ,  '31130' ,  '31161' ,  '31140' ,  '31194' ,  '31120' ,  '16062' ,  '31382' ,  '31391' ,  '14040' ,  '31320' ,  '41082' ,  '31310' ,  '31110' ,  '73010' ,  '31150' ,  '31220' ,  '31164' ,  '31181' ,  '31191'  ) 
AND DATA.purposecode = CONV.dac AND usd_commitment > 0  AND CONV.fao !~* '.00'  
GROUP BY CONV.fao, OCCURRENCES.count ORDER BY SUM DESC;
		*
		*
		**/
		
		
		// limit query
		Integer limit = qvo.getLimit();
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY		
		StringBuilder sb = new StringBuilder();		
		sb.append(" SELECT ");  
		
		if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
			for(String column: qvo.getSelects()) {
				sb.append( column +", ");
			}
		} else {
			sb.append(" CONV.fao, CONV.fao, ");  //SELECT ORs
	    }
			
		sb.append(" ROUND("+buildAggregationFunction(aggregationType, quantityColumn)+" / OCCURRENCES.count, 2)" + " AS " + aggregationType +
				  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
		
		sb.append(" INNER JOIN ( " + 
				  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");
		
		/*if(!qvo.getPriorities().isEmpty()) {
			sb.append(" WHERE ");  
			int k=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( k != 0 )
					sb.append("OR ");

				sb.append( " fao = '"+ code + "' ");
				k++;
			}
		}*/
				
		sb.append(" GROUP BY dac ) AS OCCURRENCES ");
		sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
		sb.append(" WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		
		// FILTER ON PURPOSE CODE
		sb.append(" AND DATA.purposecode = CONV.dac ");
		
		if ( !qvo.getPriorities().isEmpty() ) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");
				
				sb.append( " CONV.fao = '"+ code + "' ");
				j++;
			}
			sb.append(" )");
		}

		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
		
		sb.append(" GROUP BY ");
		if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
			for(String column: qvo.getSelects()) {
				sb.append( column +", ");
			}
		} else {
			sb.append(" CONV.dac, CONV.fao, ");  
	    }
		
		sb.append(" OCCURRENCES.count " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		//TEST REMOVE LIMIT, HANDLED AFTER !
		// if ( limit != null ||  limit != 0 )
				//sb.append("LIMIT "+ limit);

		
		 LOGGER.info("*** buildORquery *** "+ sb.toString());
		return sb.toString();
	}
	
  public static String buildORAggregationQuery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {

		/**
		 * 
		 * SELECT ROUND( SUM(  CAST( t.SUBTOTAL as numeric ) ) ,2) AS TOTAL
          FROM (SELECT SUM( usd_commitment)  /  OCCURRENCES.count  AS SUBTOTAL FROM FAO_DAC_CONVERSION_TABLE_09b403b0 AS CONV, ADAM_CRS_e599fc32 AS DATA  INNER JOIN (  SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_09b403b0  GROUP BY dac ) AS OCCURRENCES  ON DATA.purposecode = OCCURRENCES.dac  WHERE year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) AND purposecode IN ( '31162' ,  '31291' ,  '31192' ,  '31381' ,  '43040' ,  '31195' ,  '31261' ,  '33110' ,  '25010' ,  '15160' ,  '41030' ,  '41020' ,  '31166' ,  '12240' ,  '31182' ,  '32161' ,  '31281' ,  '31193' ,  '72040' ,  '31163' ,  '14015' ,  '14010' ,  '31210' ,  '41010' ,  '52010' ,  '74010' ,  '31282' ,  '15170' ,  '31130' ,  '31161' ,  '31140' ,  '31194' ,  '31120' ,  '16062' ,  '31382' ,  '31391' ,  '14040' ,  '31320' ,  '41082' ,  '31310' ,  '31110' ,  '73010' ,  '31150' ,  '31220' ,  '31164' ,  '31191' ,  '31181'  ) 
          AND DATA.purposecode = CONV.dac  AND usd_commitment > 0  AND CONV.fao !~* '.00' GROUP BY OCCURRENCES.count) t  
		*
		**/
		
		
		// limit query
		Integer limit = qvo.getLimit();
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY		
		StringBuilder sb = new StringBuilder();		
		sb.append(" SELECT ROUND(SUM(CAST( t.SUM as numeric )) ,2) AS TOTAL ");  	 	
		sb.append(" FROM (SELECT ");  		
		sb.append(" "+buildAggregationFunction(aggregationType, quantityColumn)+" / OCCURRENCES.count " + " AS " + aggregationType +
				  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
		
		sb.append(" INNER JOIN ( " + 
				  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");	
		sb.append(" GROUP BY dac ) AS OCCURRENCES ");
		sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
		sb.append(" WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		
		// FILTER ON PURPOSE CODE
		sb.append(" AND DATA.purposecode = CONV.dac ");
		
		if ( !qvo.getPriorities().isEmpty() ) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");
				
				sb.append( " CONV.fao = '"+ code + "' ");
				j++;
			}
			sb.append(" )");
		}

		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)	
		sb.append(" GROUP BY OCCURRENCES.count "); 
		sb.append(") t ");  	
		 LOGGER.info("*** buildAggregationquery *** "+ sb.toString());
		return sb.toString();
	}

  
  
  public static String compareOrToTop10OrsQuery(String conversiontablename, String oecdtablename, Double selectedORTotal, ADAMQueryVO qvo) {
		
		/**
  
  SELECT t.code, t.label, [OR TOTAL] - ROUND( SUM(  CAST( t.SUBTOTAL as numeric ) ) ,2) AS DIFF, ROUND( SUM(  CAST( t.SUBTOTAL as numeric ) ) ,2) AS TOTAL
  FROM (SELECT CONV.fao as code, CONV.fao as label, SUM( usd_commitment)  /  OCCURRENCES.count  AS SUBTOTAL 
  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 AS CONV, ADAM_CRS_e599fc32 AS DATA  
  INNER JOIN (  SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 GROUP BY dac ) AS OCCURRENCES  
  ON DATA.purposecode = OCCURRENCES.dac  
  WHERE recipientcode IN ( '625'  ) AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
  AND DATA.purposecode = CONV.dac  AND usd_commitment > 0  AND CONV.fao !~* '.00' GROUP BY CONV.fao, OCCURRENCES.count) t
  GROUP BY  t.code, t.label
  ORDER BY TOTAL DESC LIMIT 10
  **/
	 
	// if the aggreation is not setted that's the default
		String quantityColumn = "usd_commitment";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();	
		
		// QUERY
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT t.code, t.label, "+selectedORTotal+" - ROUND( SUM(  CAST( t.SUBTOTAL as numeric ) ) ,2) AS DIFF, ROUND( SUM(  CAST( t.SUBTOTAL as numeric ) ) ,2) AS TOTAL ");
		sb.append(" FROM (");
		sb.append(" SELECT CONV.fao as code, CONV.fao as label, SUM( usd_commitment)  /  OCCURRENCES.count  AS SUBTOTAL ");
		sb.append(" FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
		sb.append(" INNER JOIN ( " + 
				  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");	
		sb.append(" GROUP BY dac ) AS OCCURRENCES ");
		sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
		sb.append(" WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		sb.append(" AND DATA.purposecode = CONV.dac ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)	
		sb.append(" GROUP BY CONV.dac, CONV.fao, OCCURRENCES.count "); 
		sb.append(") t ");
		sb.append(" GROUP BY  t.code, t.label ");
		sb.append(" ORDER BY TOTAL DESC LIMIT 10");
		 
		LOGGER.info("%%% compareOrToTop10OrsQuery %%% "+ sb.toString());
		
		return sb.toString();
	  
  }
  
   public static String buildTimeseriesORquery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		
		/**
	
	SELECT year, purposecode, CONV.fao,  ROUND( SUM(  CAST( usd_commitment as numeric ) ) ,2) AS TOTAL, ROUND(SUM(  CAST( usd_commitment as numeric ) ) / Codes.count, 2) AS SUM
	FROM FAO_DAC_CONVERSION_TABLE_25126d47 AS CONV, ADAM_CRS_68379c5f AS DATA
	INNER JOIN
	(
	SELECT dac, fao, count(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_25126d47 AS CONV	
	WHERE fao IN ( 'I02' ,  'H02' ,  'G01' ,  'G02') 
	GROUP BY dac, fao) AS Codes
	ON DATA.purposecode = Codes.dac
	WHERE year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
	AND DATA.purposecode = CONV.dac  AND usd_commitment > 0  AND CONV.fao !~* '.00'  
	AND CONV.fao IN ('I02' ,  'H02' ,  'G01' ,  'G02')
	GROUP BY CONV.fao, DATA.purposecode, Codes.count, year 
	ORDER BY year, SUM DESC 
	**/
	
	// limit query
	Integer limit = qvo.getLimit();
	
	int subQueryLimit = 5;
	
//	LOGGER.info("LIMIT: " + limit);
	
	// if the aggreation is not setted that's the default
	String quantityColumn = "usd_disbursement";	
	String aggregationType = "SUM";
	
	if (!qvo.getAggregations().isEmpty()) {
		for (String column : qvo.getAggregations().keySet()) {
			quantityColumn = column;
			aggregationType = qvo.getAggregations().get(column);
		}
	}
	
	LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
	
	Map<String, List<String>> filters = qvo.getWheres();
	
	
	// QUERY		
	StringBuilder sb = new StringBuilder();		
	sb.append(" SELECT ");  
	
	if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
		for(String column: qvo.getSelects()) {
			sb.append( column +", ");
		}
	} else {
		sb.append(" CONV.fao, ");  //SELECT ORs
    }
	
	sb.append(" ROUND(CAST(extract(year from year) AS NUMERIC), 0) AS year, " +  "ROUND("+buildAggregationFunction(aggregationType, quantityColumn)+" / OCCURRENCES.count, 2)" + " AS " + aggregationType +
			  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
	
	sb.append(" INNER JOIN ( " + 
			  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");
	
	/*if(!qvo.getPriorities().isEmpty()) {
		sb.append(" WHERE ");  
		int k=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( k != 0 )
				sb.append("OR ");

			sb.append( " fao = '"+ code + "' ");
			k++;
		}
	}*/
			
	sb.append(" GROUP BY dac ) AS OCCURRENCES ");
	sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
	sb.append(" WHERE ");

	int i = 0;		
	for(String filter : filters.keySet() ){ 
		if ( i == 0 )
			sb.append(addSQLCondition(filter, filters.get(filter), false));
		else
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		i++;
	}

	
	// FILTER ON PURPOSE CODE
	sb.append(" AND DATA.purposecode = CONV.dac ");
	
	
	if ( !qvo.getPriorities().isEmpty() ) {
		sb.append(" AND ( ");
		int j=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( j != 0 )
				sb.append("OR ");
			
			sb.append( " CONV.fao = '"+ code + "' ");
			j++;
		}
		sb.append(" )");
	}

	//		sb.append(" AND " + quantityColumn +" is not null ");
	sb.append(" AND " + quantityColumn +" > 0 ");
	sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
	
	sb.append(" GROUP BY ");
	if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
		for(String column: qvo.getSelects()) {
			sb.append( column +", ");
		}
	} else {
		sb.append(" CONV.dac, CONV.fao, ");  
    }
	
	sb.append(" year, OCCURRENCES.count " +
//			  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
			  " ORDER BY " + aggregationType + " DESC "); 
	
	
	//TEST REMOVE LIMIT, HANDLED AFTER !
	// if ( limit != null ||  limit != 0 )
			//sb.append("LIMIT "+ limit);

	 LOGGER.info("BUILD ** OR ** QUERY: " + sb.toString());
	return sb.toString();
}
   
   public static String topItemsORQuery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		
	   /**
	    * 
	    * SELECT recipientname, SUM(t.SUM) FROM (SELECT recipientname, ROUND( SUM(  CAST( usd_commitment as numeric ) )  / OCCURRENCES.count, 2) AS SUM FROM FAO_DAC_CONVERSION_TABLE_09b403b0 AS CONV, ADAM_CRS_e599fc32 AS DATA  INNER JOIN (  SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_09b403b0  GROUP BY dac ) AS OCCURRENCES  ON DATA.purposecode = OCCURRENCES.dac  WHERE year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) AND DATA.purposecode = CONV.dac  AND (  CONV.fao = 'A1'  ) AND usd_commitment > 0  AND CONV.fao !~* '.00'  GROUP BY recipientname, OCCURRENCES.count  ORDER BY recipientname, SUM DESC) t
GROUP BY t.recipientname
ORDER BY SUM(t.SUM) DESC LIMIT 3

	    */
	// limit query
	Integer limit = qvo.getLimit();
	
	int subQueryLimit = 5;
	
//	LOGGER.info("LIMIT: " + limit);
	
	// if the aggreation is not setted that's the default
	String quantityColumn = "usd_disbursement";	
	String aggregationType = "SUM";
	
	if (!qvo.getAggregations().isEmpty()) {
		for (String column : qvo.getAggregations().keySet()) {
			quantityColumn = column;
			aggregationType = qvo.getAggregations().get(column);
		}
	}
	
	LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
	
	Map<String, List<String>> filters = qvo.getWheres();
	
	//Clean up the selects, to replace any name fields with the code field
	List<String> codedSelects = new ArrayList<String>();
	
	if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
		 codedSelects.clear();
		 
		for(String column: qvo.getSelects()) {	
			if(column.contains("name"))
				column = column.replace("name", "code");
			
			codedSelects.add(column);
		}
	 } else {
		 codedSelects.clear();
		 codedSelects.add("fao"); //SELECT ORs
    }
	
	
	
	// QUERY		
	StringBuilder sb = new StringBuilder();		
	
	sb.append(" SELECT "); 
	for(String column: codedSelects) {
		sb.append( column +", ");
	}
	sb.append(" SUM(t.SUM) "); 
	sb.append(" FROM ("); 
	sb.append(" SELECT ");  
	
	/*if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
		for(String column: qvo.getSelects()) {
			if(column.contains("name"))
				column = column.replace("name", "code");
			sb.append( column +", ");
		}
	} else {
		sb.append(" CONV.fao, ");  //SELECT ORs
   }*/
	
	for(String column: codedSelects) {
		sb.append( column +", ");
	}
	sb.append("ROUND("+buildAggregationFunction(aggregationType, quantityColumn)+" / OCCURRENCES.count, 2)" + " AS " + aggregationType +
			  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
	
	sb.append(" INNER JOIN ( " + 
			  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");
	
	/*if(!qvo.getPriorities().isEmpty()) {
		sb.append(" WHERE ");  
		int k=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( k != 0 )
				sb.append("OR ");

			sb.append( " fao = '"+ code + "' ");
			k++;
		}
	}*/
			
	sb.append(" GROUP BY dac ) AS OCCURRENCES ");
	sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
	sb.append(" WHERE ");

	int i = 0;		
	for(String filter : filters.keySet() ){ 
		if ( i == 0 )
			sb.append(addSQLCondition(filter, filters.get(filter), false));
		else
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		i++;
	}

	
	// FILTER ON PURPOSE CODE
	if(filters.size() > 0)
		sb.append(" AND ");
		
	sb.append(" DATA.purposecode = CONV.dac ");
		
	if ( !qvo.getPriorities().isEmpty() ) {
		sb.append(" AND ( ");
		int j=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( j != 0 )
				sb.append("OR ");
			
			sb.append( " fao = '"+ code + "' ");
			j++;
		}
		sb.append(" )");
	}

	//		sb.append(" AND " + quantityColumn +" is not null ");
	sb.append(" AND " + quantityColumn +" > 0 ");
	sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
	
	sb.append(" GROUP BY ");
	for(String column: codedSelects) {
		sb.append( column +", ");
	}
	
	/*if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
		for(String column: qvo.getSelects()) {
			if(column.contains("name"))
				column = column.replace("name", "code");
			sb.append( column +", ");
		}
	}else {
		sb.append(" CONV.dac, CONV.fao, ");  
   }*/
	
	sb.append(" OCCURRENCES.count " +
			  " ORDER BY " + aggregationType + " DESC "); 
	
	sb.append(") t ");
	
	sb.append(" GROUP BY "); 
	
	int k = 0;
	for(String column: codedSelects) {
		if ( k != 0 )
			sb.append(", ");
		
		sb.append( column +" ");
		i++;
	}

	sb.append(" ORDER BY SUM(t.SUM) DESC "); 
	
	if ( limit != null ||  limit != 0 )
		sb.append(" LIMIT "+ limit);

    LOGGER.info("BUILD ** OR ** LIMIT QUERY: " + sb.toString());
	return sb.toString();
}

   public static String buildMapCRSORquery(String conversiontablename, String oecdtablename, String isoconversiontablename, ADAMQueryVO qvo) {
	/**SELECT iso2, ROUND(SUM(  CAST( usd_commitment as numeric ) ) / Codes.count, 2) AS SUM
	FROM GEOCODE_CONVERSION_TABLE_b5d85729 as GEO, FAO_DAC_CONVERSION_TABLE_25126d47 AS CONV, ADAM_CRS_68379c5f AS DATA
	INNER JOIN
	(
	SELECT dac, count(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_25126d47 AS CONV	
	WHERE fao IN ( 'I02' ,  'H02' ,  'G01' , 'G02', 'D02', 'D03') 
	GROUP BY dac) AS Codes
	ON DATA.purposecode = Codes.dac
	WHERE year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
	AND DATA.purposecode = CONV.dac  AND usd_commitment > 0  AND CONV.fao !~* '.00'  
	AND CONV.fao IN ( 'I02' ,  'H02' ,  'G01' , 'G02', 'D02', 'D03') 
	AND recipientcode = GEO.crs
	GROUP BY iso2,Codes.count
	ORDER BY iso2, SUM DESC 
**/
	
	
	// if the aggreation is not setted that's the default
	String quantityColumn = "usd_disbursement";	
	String aggregationType = "SUM";
	
	if (!qvo.getAggregations().isEmpty()) {
		for (String column : qvo.getAggregations().keySet()) {
			quantityColumn = column;
			aggregationType = qvo.getAggregations().get(column);
		}
	}
	
	LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
	
	Map<String, List<String>> filters = qvo.getWheres();
	
	
	// QUERY		
	StringBuilder sb = new StringBuilder();			
	sb.append(" SELECT iso2, ISO.label, " +  "ROUND("+buildAggregationFunction(aggregationType, quantityColumn)+" / OCCURRENCES.count, 2)" + " AS " + aggregationType +
			  " FROM "+ isoconversiontablename+ " AS ISO, "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
	
	sb.append(" INNER JOIN ( " + 
			  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");
	
	/*if(!qvo.getPriorities().isEmpty()) {
		sb.append(" WHERE ");  
		int k=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( k != 0 )
				sb.append("OR ");

			sb.append( " fao = '"+ code + "' ");
			k++;
		}
	}*/
			
	sb.append(" GROUP BY dac ) AS OCCURRENCES ");
	sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
	sb.append(" WHERE ");

	int i = 0;		
	for(String filter : filters.keySet() ){ 
		if ( i == 0 )
			sb.append(addSQLCondition(filter, filters.get(filter), false));
		else
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		i++;
	}

	sb.append(" AND DATA.purposecode = CONV.dac ");
	
	// FILTER ON PURPOSE CODE
	if ( !qvo.getPriorities().isEmpty() ) {
		sb.append(" AND ( ");
		int j=0;
		for(String code: qvo.getPriorities().keySet()) {
			if ( j != 0 )
				sb.append("OR ");
			
			sb.append( " CONV.fao like '"+ code + "%' ");
			j++;
		}
		sb.append(" )");
	}
	sb.append(" AND recipientcode = ISO.crs  ");
	sb.append(" AND ISO.iso2 is not null  ");

	sb.append(" AND " + quantityColumn +" > 0 ");
	sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
	
	sb.append(" GROUP BY iso2, ISO.label, OCCURRENCES.count " +
			  " ORDER BY iso2, " + aggregationType + " DESC "); 
	
	
	 LOGGER.info("*** buildMapORquery *** "+ sb.toString());
	 
	return sb.toString();
}
   
   
   public static String buildMapFPMISORquery(String fpmistablename, String isoconversiontablename, ADAMQueryVO qvo) {
		/** SELECT iso2, recipientcode, recipientname, ROUND(SUM(  CAST( usd_commitment as numeric ) ), 2) AS SUM
	FROM ADMINISTRATIVE_LEVEL_0_CONVERSION_TABLE_d1e65cc5 as GEO, ADAM_FPMIS_f9c2dda1 AS DATA
	WHERE year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
	AND usd_commitment > 0  
	AND DATA.purposecode IN ( 'I2' ,  'H2' ,  'G1' , 'G2', 'D2', 'D3') 
	AND recipientcode = GEO.fpmis
	GROUP BY iso2, recipientcode, recipientname
	ORDER BY iso2, SUM DESC 
	**/
		
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_commitment";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY		
		StringBuilder sb = new StringBuilder();			
		sb.append(" SELECT iso2, ISO.label, " +  "ROUND("+buildAggregationFunction(aggregationType, quantityColumn)+", 2)" + " AS " + aggregationType +
				  " FROM "+ isoconversiontablename+ " AS ISO, " + fpmistablename +" AS DATA ");
		sb.append(" WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		sb.append(" AND recipientcode = fpmis  ");
		
		// FILTER ON PURPOSE CODE
		if ( !qvo.getPriorities().isEmpty() ) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");
				
				sb.append( " CONV.fao like '"+ code + "%' ");
				j++;
			}
			sb.append(" )");
		}
		sb.append(" AND ISO.iso2 is not null  ");

		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" GROUP BY iso2, ISO.label " +
				  " ORDER BY iso2, " + aggregationType + " DESC "); 
		
		
		 LOGGER.info("*** buildMapFPMISORquery *** "+ sb.toString());
		 
		return sb.toString();
	}
   
   
   
  
	
   public static String buildORContributionTableQuery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		
		/**
 
 SELECT t.year, ROUND( SUM(  CAST( t.SUBTOTAL as numeric ) ) ,2) AS COMMITMENT, ROUND( SUM(  CAST( t.DISB as numeric ) ) ,2) AS DISBURSEMENT
        FROM (SELECT year, SUM( usd_disbursement)  /  OCCURRENCES.count  AS DISB, SUM( usd_commitment)  /  OCCURRENCES.count  AS SUBTOTAL 
        FROM FAO_DAC_CONVERSION_TABLE_09b403b0 AS CONV, ADAM_CRS_e599fc32 AS DATA  
        INNER JOIN (  SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 GROUP BY dac ) AS OCCURRENCES  
        ON DATA.purposecode = OCCURRENCES.dac  
        WHERE recipientcode IN ( '625'  ) AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
        AND DATA.purposecode = CONV.dac  AND usd_commitment > 0  AND CONV.fao !~* '.00' GROUP BY year, OCCURRENCES.count) t
        GROUP BY t.year
        ORDER BY year DESC
 **/
	 
	// if the aggreation is not setted that's the default
		String quantityColumn = "usd_commitment";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();	
		
		// QUERY	
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ROUND(CAST(extract(year from t.year) AS NUMERIC), 0), ROUND(SUM(CAST( t.COMM as numeric )) ,2) AS commitment, ROUND( SUM(CAST( t.COMMDEFL as numeric)) ,2) AS commitment_defl, ROUND(SUM(CAST( t.DISB as numeric)) ,2) AS disbursement, ROUND(SUM(CAST(t.DISBDEFL as numeric)) ,2) AS disbursement_defl ");
		sb.append(" FROM (");
		sb.append(" SELECT year, SUM(usd_commitment) / OCCURRENCES.count AS COMM, SUM(usd_commitment_defl) / OCCURRENCES.count AS COMMDEFL, SUM(usd_disbursement) / OCCURRENCES.count AS DISB, SUM(usd_disbursement_defl) / OCCURRENCES.count AS DISBDEFL ");
		sb.append(" FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
		sb.append(" INNER JOIN ( " + 
				  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");	
		
		/*if(!qvo.getPriorities().isEmpty()) {
			sb.append(" WHERE ");  
			int k=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( k != 0 )
					sb.append("OR ");

				sb.append( " fao = '"+ code + "' ");
				k++;
			}
		}*/
		sb.append(" GROUP BY dac ) AS OCCURRENCES ");
		sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
		sb.append(" WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		sb.append(" AND DATA.purposecode = CONV.dac ");
		
		if ( !qvo.getPriorities().isEmpty() ) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");
				
				sb.append( " CONV.fao = '"+ code + "' ");
				j++;
			}
			sb.append(" )");
		}
		
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)	
		sb.append(" GROUP BY year, OCCURRENCES.count "); 
		sb.append(") t ");
		sb.append(" GROUP BY year ");
		sb.append(" ORDER BY year DESC ");
		 
		LOGGER.info("%%% buildORContributionTableQuery %%% "+ sb.toString());
		
		return sb.toString();
	  
 }
   
   public static String buildORTopImplementingAgenciesTableQuery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		
		/**

SELECT t.channelname, ROUND( SUM(  CAST( t.SUBTOTAL as numeric ) ) ,2) AS COMMITMENT, ROUND( SUM(  CAST( t.DISB as numeric ) ) ,2) AS DISBURSEMENT
       FROM (SELECT channelname, SUM( usd_disbursement)  /  OCCURRENCES.count  AS DISB, SUM( usd_commitment)  /  OCCURRENCES.count  AS SUBTOTAL 
       FROM FAO_DAC_CONVERSION_TABLE_09b403b0 AS CONV, ADAM_CRS_e599fc32 AS DATA  
       INNER JOIN (  SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 GROUP BY dac ) AS OCCURRENCES  
       ON DATA.purposecode = OCCURRENCES.dac  
       WHERE recipientcode IN ( '625'  ) AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
       AND DATA.purposecode = CONV.dac  AND usd_commitment > 0  AND CONV.fao !~* '.00' 
       AND fao IN ('A1', 'A2')
       GROUP BY channelname, OCCURRENCES.count) t	
       GROUP BY channelname
       ORDER BY commitment DESC, channelname
       LIMIT 10
**/
	 
	// if the aggreation is not setted that's the default
		String quantityColumn = "usd_commitment";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();	
		
		// QUERY	
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT t.channelname, ROUND(SUM(CAST( t.COMM as numeric )) ,2) AS commitment, ROUND(SUM(CAST( t.DISB as numeric)) ,2) AS disbursement ");
		sb.append(" FROM (");
		sb.append(" SELECT channelname, SUM(usd_commitment) / OCCURRENCES.count AS COMM, SUM(usd_disbursement) / OCCURRENCES.count AS DISB ");
		sb.append(" FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA ");
		sb.append(" INNER JOIN ( " + 
				  " SELECT dac, COUNT(fao) as count  FROM "+conversiontablename+" ");	
		
		/*if(!qvo.getPriorities().isEmpty()) {
			sb.append(" WHERE ");  
			int k=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( k != 0 )
					sb.append("OR ");

				sb.append( " fao = '"+ code + "' ");
				k++;
			}
		}*/
		sb.append(" GROUP BY dac ) AS OCCURRENCES ");
		sb.append(" ON DATA.purposecode = OCCURRENCES.dac ");
		sb.append(" WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		sb.append(" AND DATA.purposecode = CONV.dac ");
		
		if ( !qvo.getPriorities().isEmpty() ) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");
				
				sb.append( " CONV.fao = '"+ code + "' ");
				j++;
			}
			sb.append(" )");
		}
		
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)	
		sb.append(" GROUP BY channelname, OCCURRENCES.count "); 
		sb.append(") t ");
		sb.append(" GROUP BY channelname ");
		sb.append(" ORDER BY commitment DESC, channelname DESC ");
		if(qvo.getLimit()!=0)
			sb.append(" LIMIT ").append(qvo.getLimit());
		
		LOGGER.info("%%% buildORImplementingAgenciesTableQuery %%% "+ sb.toString());
		
		return sb.toString();
	  
}
   
	public static String buildOriginalORquery(String conversiontablename, String oecdtablename, ADAMQueryVO qvo) {
		// limit query
		Integer limit = qvo.getLimit();
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY
		
		StringBuilder sb = new StringBuilder();
//		 ROUND(SUM(CAST(usd_commitment as numeric)),2)		
		
		sb.append(" SELECT CONV.fao, CONV.fao, " +  buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS DATA " +
				  "	WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		
		// FILTER ON PURPOSE CODE
		sb.append(" AND DATA.purposecode = CONV.dac ");
		
		if ( !qvo.getPriorities().isEmpty() ) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");
				
				sb.append( " CONV.fao = '"+ code + "' ");
				j++;
			}
			sb.append(" )");
		}

		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		sb.append(" AND CONV.fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
		
		sb.append(" GROUP BY CONV.fao " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		 if ( limit != null ||  limit != 0 )
				sb.append("LIMIT "+ limit);

		
		return sb.toString();
	}
	
	public static String buildDACquery(String oecdtablename, ADAMQueryVO qvo) {
		// limit query
		Integer limit = qvo.getLimit();
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT DATA.purposecode, DATA.purposecode, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM " + oecdtablename +" AS DATA " +
				  "	WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

	
		if ( !qvo.getPriorities().isEmpty() ) {
			sb.append(" AND ( ");
			int j=0;
			for(String code: qvo.getPriorities().keySet()) {
				if ( j != 0 )
					sb.append("OR ");
				
				sb.append( " DATA.purposecode = '"+ code + "' ");
				j++;
			}
			sb.append(" )");
		}

		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY DATA.purposecode " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
				  " ORDER BY " + aggregationType + " DESC "); 
		
		 if ( limit != null ||  limit != 0 )
				sb.append("LIMIT "+ limit);

		
		return sb.toString();
	}
	
	

	public static String getRecipientCodesForFAORegion(String regionCode){
		return "SELECT ch.code, c.label FROM codinghierarchy AS ch, code AS c, codingsystem as cs, codingsystem_code as CSC WHERE ch.parent IN (SELECT code FROM codinghierarchy WHERE parent='"+regionCode+"') AND ch.code =c.code AND cs.title =  'ADAMRecipients' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' GROUP BY ch.code, c.label ORDER BY c.label";
	}
	
	
	public static String buildGoogleDataset(String conversiontablename, String oecdtablename, ADAMQueryVO qvo, ADAMSelectedDataset selectedDataset) {
		// limit query
		Integer limit = 10;
		
		int subQueryLimit = 5;
		
//		LOGGER.info("LIMIT: " + limit);
		
		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";	
		String aggregationType = "SUM";
		
		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}
		
		//LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
		
		Map<String, List<String>> filters = qvo.getWheres();
		
		
		// QUERY
		/** 
		 * SELECT CONV.iso2, SUM(usd_disbursement)  
		   FROM OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be as ADAM, GAUL_CRS_ISO2_ISO3_CONVERSION_TABLE_bc5beaa1 as CONV
		   WHERE  ( year = '2000-01-01' OR year = '2001-01-01' OR year = '2007-01-01' OR year = '2008-01-01'  ) 
		   AND ADAM.recipientcode = CONV.crs GROUP BY CONV.iso2, CONV.crs, ADAM.recipientcode  
		   HAVING SUM(usd_disbursement) != 0  
		   AND ADAM.recipientcode is not null AND CONV.iso2 is not null; 
		 * **/
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT CONV.iso2, CONV.label, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ conversiontablename+ " AS CONV, " + oecdtablename +" AS ADAM " +
				  "	WHERE ");

		int i = 0;		
		for(String filter : filters.keySet() ){ 
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}

		
		// FILTER ON recipient CODE
		if(selectedDataset.equals(ADAMSelectedDataset.ADAM_CRS))
			sb.append(" AND ADAM.recipientcode = CONV.crs ");
		else
			sb.append(" AND ADAM.recipientcode = CONV.fpmis");

		
//		sb.append(" GROUP BY CONV.iso2, CONV.label, ADAM.recipientcode " +
//				  "	HAVING " + aggregationType + "(" + quantityColumn +") > 0 " +
//				  " AND ADAM.recipientcode is not null AND CONV.iso2 is not null "); 
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");

		sb.append(" AND CONV.iso2 is not null " +
				  " GROUP BY CONV.iso2, CONV.label ");

		
		return sb.toString();
	}
	
	
	
	/**
	 * 
	 *   ALL ORs (then you can aggregate for SO)
	 *   
	 *   
	 * 
	 *  SELECT CONV.fao, SUM(usd_disbursement)  
	 *   FROM FAO_DAC_CONVERSION_TABLE_240c9a0e AS CONV, OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be AS DATA 	
	 *   WHERE  DATA.purposecode = CONV.dac  GROUP BY CONV.fao  	
	 *   HAVING SUM(usd_disbursement) > 0  ORDER BY SUM(usd_disbursement) DESC; 
	 *  
	 *  
	 *  **/
	
	public static String getALLDacFromORs(String tablename) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(dac) "  +  
				  " FROM "+ tablename + " ");
		
		return sb.toString();
	}
	
	public static String getALLDacFromSOs(String tablename, List<String> sos) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(dac) "  +  
				  " FROM "+ tablename + " ");
		
		sb.append("WHERE ");
		


		sb.append(" ( ");
		
		for(int i=0; i < sos.size(); i++) {
			if ( i != 0 )
				sb.append("OR ");
			
			sb.append( " fao like '" + sos.get(i) +"%'");
		}
		
		sb.append(" )");
		
		//sb.append(" AND fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
			
		
		return sb.toString();
	}
	

	public static String getALLDacFromSOs(String tablename, Object[] sos) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(dac) "  +  
				  " FROM "+ tablename + " ");
		
		sb.append("WHERE ");
		


		sb.append(" ( ");
		
		for(int i=0; i < sos.length; i++) {
			if ( i != 0 )
				sb.append("OR ");
			
			sb.append( " fao like '" + sos[i].toString() +"%'");
		}
		
		sb.append(" )");
		sb.append(" AND fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
		
		
		return sb.toString();
	}
	
	public static String getALLDacFromSOs(String tablename, Map<String, Map<String, String>> sos) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(dac), fao"  +  
				  " FROM "+ tablename + " ");
		
		sb.append("WHERE ");

		sb.append(" ( ");
		
		Object[] so = sos.keySet().toArray();
			for (int i = 0 ; i < so.length ; i++) {
				if ( i != 0 )
					sb.append("OR ");
				
				sb.append( " fao like '" + so[i].toString() +"%'");
			}
		
		sb.append(" )");
		sb.append(" AND fao !~* '.00' "); // ORs with a 00 suffix should not be included as an OR (e.g E00)
			
		return sb.toString();
	}
	
	
	public static String getALLDacFromSO(String tablename, String so) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT DISTINCT(dac) "  +  
				  " FROM "+ tablename + " ");
		
		sb.append("WHERE ");


		sb.append( " fao like '" + so +"%'");
			
		
		LOGGER.info("BUILD ALL DAC FROM SO QUERY: "+sb.toString());
		return sb.toString();
	}
	
	
	
	
	

	
	
	public static String getTimeSerie(String tablename, String columncode, String columnlabel, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		
		/***
		 * 
		 * SELECT donorname, year, SUM(usd_disbursement) AS SUM 
			FROM OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be 
			WHERE ( dac_sector = '300' ) 
			AND donorcode IN 
				(SELECT donorcode 
				 FROM OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be 
				 WHERE ( dac_sector = '300' ) 
				 GROUP BY donorcode	
				 HAVING SUM(usd_disbursement) != 0 
				 ORDER BY SUM(usd_disbursement) DESC LIMIT 5
				 )
			
			GROUP BY donorname, year
			HAVING SUM(usd_disbursement) != 0 
		 * 
		 */
		
		
		StringBuilder sb = new StringBuilder();	


//		sb.append(" SELECT " + columnlabel + ", date_part('year', year), " + aggregationType + "(" + quantityColumn +") " +
		sb.append(" SELECT " + columnlabel + ", year, " +  buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename  +" " +
				  " WHERE  ");

		// general filters
		int i = 0;
		for(String filter : filters.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		////////// adding subquery 
		if ( !filters.isEmpty() )
			sb.append(" AND ");
		
		sb.append(columncode + " IN (");
		sb.append(" SELECT " + columncode + " " +
				  " FROM "+ tablename  + " ");
				  if ( !filters.isEmpty() ) {
						  sb.append(" WHERE  ");
						  int j = 0;
						  for(String filter : filters.keySet() ) {
								  if ( j == 0 )
									  sb.append(addSQLCondition(filter, filters.get(filter), false));
								  else
									  sb.append(addSQLCondition(filter, filters.get(filter), true));
								  j++;
							  }
				  }
				  
		if ( !filters.isEmpty() )
			sb.append(" AND "); 
		else 
			 sb.append(" WHERE  ");
		
		sb.append(" " + quantityColumn +" is not null ");		  
				  
		sb.append(" GROUP BY " + columncode + " " +
//				  " HAVING " + aggregationType + "(" + quantityColumn +") != 0 " + 
				  " ORDER BY " + aggregationType + "(" + quantityColumn +") DESC ");
		
		if ( limit != null || limit != 0)
			sb.append(" LIMIT  " + limit);
			
		
		sb.append(" ) " );
		////////// end subquery 
		
		
		sb.append(" GROUP BY " + columnlabel + ", year " +  
//				  " HAVING " + aggregationType + "(" + quantityColumn +") != 0 " + 
				  " ORDER BY " + aggregationType + " DESC ");
				  
	
		
		
		LOGGER.info("%% BUILD TIMESERIES QUERY: " + sb.toString());
		
//	LOGGER.info("getTimeSerie table query: " + sb);
		
		return sb.toString();
	}
	
	public static String getTimeSerie(String tablename, Map<String, List<String>> filters, String quantityColumn, String aggregationType) {
		
		LOGGER.info("FILTERS: " + filters);
		
		StringBuilder sb = new StringBuilder();	


		sb.append(" SELECT year, " + buildAggregationFunction(aggregationType, quantityColumn, aggregationType) +
				  " FROM "+ tablename  +" " +
				  " WHERE  ");

		// general filters
		int i = 0;
		for(String filter : filters.keySet() ) {
			LOGGER.info("filter: " + filter);
			if ( i == 0 )
				sb.append(addSQLCondition(filter, filters.get(filter), false));
			else
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			i++;
		}
		
		//		sb.append(" AND " + quantityColumn +" is not null ");
		sb.append(" AND " + quantityColumn +" > 0 ");
				
		sb.append(" GROUP BY year " +  
//				  " HAVING " + aggregationType + "(" + quantityColumn +") != 0 " + 
				  " ORDER BY " + aggregationType + " DESC ");
				  
	
		LOGGER.info("BUILD TIMESERIE QUERY: " + sb.toString());
		return sb.toString();
	}
	
	public static String getDonorsForRecipientByDataset(Map<String, List<String>> dateFilter, Map<String, String> recipientFilter, String tableName) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode " +
				" FROM "+tableName+" as ADAM " +
		" WHERE ADAM.recipientcode IN (") ;
		
		int j = 0;
		for(String code : recipientFilter.keySet() ) {
			if (j < recipientFilter.size() - 1)
  			sb.append("'"+code+"', ");
			else
				sb.append("'"+code+"'");
						
			j++;
		}
		sb.append(") AND ");
		
		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		
		
		sb.append(" AND ADAM.usd_commitment IS NOT NULL");
		sb.append(" GROUP BY ADAM.donorcode ORDER BY sum(ADAM.usd_commitment) DESC");

		return sb.toString();
	}
	
	public static String getDonorMatrixTop6Donors(Map<String, List<String>> dateFilter, String tableName) {
		StringBuilder sb = new StringBuilder();	


	//	sb.append(" SELECT ADAM.donorcode, c.label " +
	
		
		sb.append(" SELECT ADAM.donorcode " +
				" FROM "+tableName+" as ADAM " +
		" WHERE ") ;

		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
			
		sb.append(" AND ADAM.recipientcode IS NOT NULL AND ADAM.usd_commitment IS NOT NULL");
		sb.append(" GROUP BY ADAM.donorcode ORDER BY sum(ADAM.usd_commitment) DESC LIMIT 6");

		return sb.toString();
	}
	
	
	public static String getDonorLabels(List<String> donors) {
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT c.code, c.label " +
				" FROM code AS c, codingsystem as cs, codingsystem_code as CSC " +
		" WHERE ") ;

		sb.append(addSQLCondition("c.code", donors, false));
		
		sb.append(" AND cs.code = 'ADAM_DONORS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");

		return sb.toString();
	}
	
	public static String getDonorMatrixTop6Donors(Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.donorcode " +
				" FROM adam_donormatrix_data as ADAM " +
		" WHERE ") ;

		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
			
		sb.append(" GROUP BY ADAM.donorcode ORDER BY sum(ADAM.\"Total ODA\") DESC LIMIT 6");

		return sb.toString();
	}
	
	public static String getDonorMatrixTop14CountriesForFilteredDonors(Map<String, List<String>> dateFilter) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT ADAM.recipientcode " +
				" FROM adam_donormatrix_data as ADAM " +
		" WHERE ") ;
		int i = 0;
		for(String filter : dateFilter.keySet() ) {
			if ( i == 0 )
				sb.append(addSQLCondition(filter, dateFilter.get(filter), false));
			else
				sb.append(addSQLCondition(filter, dateFilter.get(filter), true));
			i++;
		}
		sb.append(" GROUP BY ADAM.recipientcode ORDER BY sum(ADAM.\"Total ODA\") DESC LIMIT 14");

		return sb.toString();
	}
	
	public static String getRecipientLabels(List<String> recipients) {
		StringBuilder sb = new StringBuilder();	

		sb.append(" SELECT c.code, c.label " +
				" FROM code AS c, codingsystem as cs, codingsystem_code as CSC " +
		" WHERE ") ;
		
		sb.append(addSQLCondition("c.code", recipients, false));
		
		sb.append(" AND cs.code = 'ADAM_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");
		
		return sb.toString();
	}
	
	
	public static String getViewMatrix(String tablename, String firstColumnCode, String firstColumnLabel, String secondColumnCode, String secondColumnLabel, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit) {
		
		StringBuilder sb = new StringBuilder();	
		
		
		
		/**
		 * 
		SELECT n1.channelname, n2.donorname
		FROM
		(SELECT  channelcode,channelname, SUM(usd_commitment) as budget 
		 FROM OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be 
		 WHERE  channelname <> ''   AND recipientcode IN ( '225'  ) AND year IN ( '01-01-2008' ,  '01-01-2009'  )  
		AND usd_commitment > 0 
		 GROUP BY channelcode, channelname 
		ORDER BY SUM(usd_commitment) DESC  LIMIT  30  ) n1
		
		INNER JOIN 
		
		(SELECT channelcode, donorname 
		  FROM  OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be 
		  WHERE  channelname <> ''  
		 AND recipientcode IN ( '225'  )
		AND year IN ( '01-01-2008' ,  '01-01-2009' )) n2
		
		ON n1.channelcode = n2.channelcode
		GROUP BY n1.channelname, n2.donorname, n1.budget
		ORDER BY n1.budget DESC;
		**/
		
		sb.append("SELECT n1."+ firstColumnLabel +",n2."+ secondColumnLabel+ " ");
		
		sb.append("FROM ");
		
		// FIRST QUERY
		sb.append("( ");
		sb.append("SELECT " + firstColumnCode+ ", "+ firstColumnLabel + ", " + buildAggregationFunction(aggregationType, quantityColumn, "BUDGET") + " ");
		
		sb.append(" FROM "+ tablename + " ");
		
		sb.append( " WHERE " + firstColumnLabel+" <> ''  ");
			
			// general filters
			for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			}	  
		
			sb.append(" AND " + quantityColumn +" > 0 ");
			
			sb.append(" GROUP BY " + firstColumnCode + ", " + firstColumnLabel + " " + 
					  " ORDER BY BUDGET DESC ");
			
			if ( limit != null ) {
				if ( limit != 0 ) {
					sb.append(" LIMIT  " + limit); 
				}
			}
		
		sb.append(") n1 ");
		
		sb.append(" INNER JOIN ");
		
		// SECOND QUERY
		sb.append("( ");
		sb.append("SELECT " + firstColumnCode+ ", "+  secondColumnLabel + " ");
		
		sb.append(" FROM "+ tablename + " ");
		
		sb.append( " WHERE " + firstColumnLabel+" <> ''  ");
			
			// general filters
			for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			}	  
		sb.append(") n2 ");
		
		// INNER JOIN 
		sb.append(" ON n1." + firstColumnCode+ " = n2." + firstColumnCode);
		sb.append(" GROUP BY n1." + firstColumnLabel + ", n2." + secondColumnLabel+ ", n1.BUDGET ");
		sb.append(" ORDER BY n1.BUDGET DESC");

		
		/** OLD QUERY
		 *  SELECT FIRST.channelname, FIRST.donorname
			FROM OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be FIRST
			WHERE FIRST.channelname <> '' AND FIRST.channelcode IN
				(SELECT SECOND.channelcode 
			  	FROM OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be SECOND 
			  	WHERE SECOND.channelname <> '' 
			  	GROUP BY SECOND.channelcode
			  	HAVING SUM(SECOND.usd_disbursement) != 0
			  	ORDER BY SUM(SECOND.usd_disbursement) DESC 
			  	LIMIT 30) 
			GROUP BY FIRST.channelname, FIRST.donorname;
		 * 
		 
		
		LOGGER.info("FILTERS: " + filters);
		
		

		// QUERY 
		sb.append(" SELECT FIRST." + firstColumnLabel+", FIRST." + secondColumnLabel+" " +
				  " FROM "+ tablename  +" FIRST " +
				  " WHERE  FIRST." + firstColumnLabel+" <> ''  ");
		
		// general filters
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}

		
		sb.append("AND FIRST." + firstColumnCode+" IN ");

		
		
		// NESTED QUERY 
		sb.append("( ");
		sb.append(" SELECT SECOND." + firstColumnCode+" " +
				  " FROM "+ tablename  +" SECOND " +
				  " WHERE  SECOND." + firstColumnLabel+" <> ''  ");
		
		// general filters
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}	  
	
		sb.append(" AND " + quantityColumn +" > 0 ");
		
		sb.append(" GROUP BY SECOND." + firstColumnCode+" " +  
				  " ORDER BY " + aggregationType + "(" + quantityColumn +") DESC ");
		
		if ( limit != null ) {
			if ( limit != 0 ) {
				sb.append(" LIMIT  " + limit); 
			}
		}
		
		sb.append(" )");
		// NESTED QUERY (END) 
		
		sb.append(" GROUP BY  FIRST." + firstColumnLabel+",  FIRST." + secondColumnLabel+" ");
		
		sb.append(" ORDER BY FIRST." + firstColumnLabel );
		
		*/
		
		return sb.toString();
	}
	
	
  public static String getSOViewMatrix(String tablename, String firstColumnCode, String firstColumnLabel, String secondColumnCode, String secondColumnLabel, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit, String conversiontablename, Map<String, String> sos, boolean addfilterOnSOs) {
		
		StringBuilder sb = new StringBuilder();	
		
		
		
		/**
		 * 
	SELECT n1.channelname,n2.so

FROM ( SELECT channelcode, channelname,  ROUND( SUM(  CAST( usd_commitment as numeric ) ) ,2) AS BUDGET   
FROM ADAM_CRS_e599fc32  
WHERE channelname <> ''     
AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
AND purposecode IN ('12240', 
			..)
AND usd_commitment > 0  GROUP BY channelcode, channelname  ORDER BY BUDGET DESC  LIMIT  30) n1  
INNER JOIN ( 
SELECT channelcode, substring(fao, 1, char_length(fao)-1) as so
  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 CONV, ADAM_CRS_e599fc32 DATA
  INNER JOIN 
	(SELECT dac FROM FAO_DAC_CONVERSION_TABLE_09b403b0
	WHERE  dac IN ('12240', 
			..)
			GROUP BY dac ) AS OCCURRENCES  
ON purposecode = OCCURRENCES.dac
WHERE channelname <> ''   
AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  )
AND purposecode IN ('12240', 
			..)
AND purposecode = CONV.dac

) n2  
ON n1.channelcode = n2.channelcode GROUP BY n1.channelname, n2.so, n1.BUDGET  
ORDER BY n1.BUDGET DESC, n2.so**/
		
		sb.append("SELECT n1."+ firstColumnLabel +",n2.soname ");
		
		sb.append("FROM ");
		
		// FIRST QUERY
		sb.append("( ");
		sb.append("SELECT " + firstColumnCode+ ", "+ firstColumnLabel + ", " + buildAggregationFunction(aggregationType, quantityColumn, "BUDGET") + " ");
		
		sb.append(" FROM "+ tablename + " ");
		
		sb.append( " WHERE " + firstColumnLabel+" <> ''  ");
			
			// general filters
			for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			}	  
		
			sb.append(" AND " + quantityColumn +" > 0 ");
			
			sb.append(" GROUP BY " + firstColumnCode + ", " + firstColumnLabel + " " + 
					  " ORDER BY BUDGET DESC ");
			
			if ( limit != null ) {
				if ( limit != 0 ) {
					sb.append(" LIMIT  " + limit); 
				}
			}
		
		sb.append(") n1 ");
		
		sb.append(" INNER JOIN ");
		
		// SECOND QUERY
		sb.append("( ");
		sb.append("SELECT " + firstColumnCode+ ", substring(fao, 1, char_length(fao)-1) as soname ");
		sb.append(" FROM "+ conversiontablename + " CONV, "+tablename);
		sb.append(" INNER JOIN ");
		sb.append(" (SELECT dac FROM "+conversiontablename);
		sb.append(" WHERE ");
	    sb.append(addSQLCondition("dac", filters.get("purposecode"), false));
		//sos
		if(addfilterOnSOs && !sos.isEmpty()) {
			sb.append( " AND ");
			int i=0;
			for(String so: sos.keySet()) {
				if ( i != 0 )
					sb.append(" OR ");

				sb.append( " fao like '" + so +"%'");
				i++;
			}
		}
		sb.append(" GROUP BY dac ) AS CONVERSION ");
		sb.append(" ON purposecode = CONVERSION.dac ");
		sb.append( " WHERE " + firstColumnLabel+" <> ''  ");
		sb.append(" AND purposecode = CONV.dac");
		// general filters
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}
		//sos
		if(addfilterOnSOs && !sos.isEmpty()) {
			sb.append( " AND ");
			int i=0;
			for(String so: sos.keySet()) {
				if ( i != 0 )
					sb.append(" OR ");

				sb.append( " fao like '" + so +"%'");
				i++;
			}
		}
		sb.append(" GROUP BY "+firstColumnCode+", soname");
		sb.append(") n2 ");
		
		// INNER JOIN 
		sb.append(" ON n1." + firstColumnCode+ " = n2." + firstColumnCode);
		sb.append(" GROUP BY n1." + firstColumnLabel + ",n2.soname, n1.BUDGET ");
		sb.append(" ORDER BY n1.BUDGET DESC");
		
		return sb.toString();
	}
	
  
  public static String getORViewMatrix(String tablename, String firstColumnCode, String firstColumnLabel, String secondColumnCode, String secondColumnLabel, Map<String, List<String>> filters, String quantityColumn, String aggregationType, Integer limit, String conversiontablename, Map<String, String> sos) {
		
		StringBuilder sb = new StringBuilder();	
				
		/**
		 * 
	SELECT n1.channelname,n2.or

FROM ( SELECT channelcode, channelname,  ROUND( SUM(  CAST( usd_commitment as numeric ) ) ,2) AS BUDGET   
FROM ADAM_CRS_e599fc32  
WHERE channelname <> ''     
AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
AND purposecode IN ('12240')
AND usd_commitment > 0  GROUP BY channelcode, channelname  ORDER BY BUDGET DESC  LIMIT  30) n1  
INNER JOIN ( 
SELECT channelcode, fao as or
FROM FAO_DAC_CONVERSION_TABLE_09b403b0 CONV, ADAM_CRS_e599fc32 DATA
INNER JOIN 
	(SELECT dac FROM FAO_DAC_CONVERSION_TABLE_09b403b0
	WHERE  dac IN ('12240')
			GROUP BY dac ) AS OCCURRENCES  
ON purposecode = OCCURRENCES.dac
WHERE channelname <> ''   
AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  )
AND purposecode IN ('12240')
AND purposecode = CONV.dac

) n2  
ON n1.channelcode = n2.channelcode GROUP BY n1.channelname, n2.or, n1.BUDGET  
ORDER BY n1.BUDGET DESC, n2.or **/
		
		sb.append("SELECT n1."+ firstColumnLabel +",n2.orname ");
		
		sb.append("FROM ");
		
		// FIRST QUERY
		sb.append("( ");
		sb.append("SELECT " + firstColumnCode+ ", "+ firstColumnLabel + ", " + buildAggregationFunction(aggregationType, quantityColumn, "BUDGET") + " ");
		
		sb.append(" FROM "+ tablename + " ");
		
		sb.append( " WHERE " + firstColumnLabel+" <> ''  ");
			
			// general filters
			for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
			}	  
		
			sb.append(" AND " + quantityColumn +" > 0 ");
			
			sb.append(" GROUP BY " + firstColumnCode + ", " + firstColumnLabel + " " + 
					  " ORDER BY BUDGET DESC ");
			
			if ( limit != null ) {
				if ( limit != 0 ) {
					sb.append(" LIMIT  " + limit); 
				}
			}
		
		sb.append(") n1 ");
		
		sb.append(" INNER JOIN ");
		
		// SECOND QUERY
		sb.append("( ");
		sb.append("SELECT " + firstColumnCode+ ", fao as orname ");
		sb.append(" FROM "+ conversiontablename + " CONV, "+tablename);
		sb.append(" INNER JOIN ");
		sb.append(" (SELECT dac FROM "+conversiontablename);
		sb.append(" WHERE ");
	    sb.append(addSQLCondition("dac", filters.get("purposecode"), false));
	    //sos
		if(!sos.isEmpty()) {
			sb.append( " AND ");
			int i=0;
			for(String so: sos.keySet()) {
				if ( i != 0 )
					sb.append(" OR ");

				sb.append( " fao like '" + so +"%'");
				i++;
			}
		}
		sb.append(" GROUP BY dac ) AS CONVERSION ");
		sb.append(" ON purposecode = CONVERSION.dac ");
		sb.append( " WHERE " + firstColumnLabel+" <> ''  ");
		sb.append(" AND purposecode = CONV.dac");
		// general filters
		for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
		}	
		//sos
		if(!sos.isEmpty()) {
			sb.append( " AND ");
			int i=0;
			for(String so: sos.keySet()) {
				if ( i != 0 )
					sb.append(" OR ");

				sb.append( " fao like '" + so +"%'");
				i++;
			}
		}
		sb.append(" GROUP BY "+firstColumnCode+", orname");
		sb.append(") n2 ");
		
		// INNER JOIN 
		sb.append(" ON n1." + firstColumnCode+ " = n2." + firstColumnCode);
		sb.append(" GROUP BY n1." + firstColumnLabel + ",n2.orname, n1.BUDGET ");
		sb.append(" ORDER BY n1.BUDGET DESC");
		
		return sb.toString();
	}
	
	
   
	// Convert ISO3 to CRS, FPMIS etc
	public static String getConversionfromISO3(String tablename, List<String> iso3Codes, String datasetColumnName) {
		StringBuilder sb = new StringBuilder();	
		
		// the coding system can be removed
		sb.append(" SELECT "+datasetColumnName+  
				  " FROM "+ tablename + " ");
		
		sb.append(" WHERE ");
		
		sb.append(addSQLCondition("iso3", iso3Codes, false));

		LOGGER.info("getConversionfromISO3 query: " + sb);
		
		return sb.toString();
	}
	
	
	// this should changed in order to get the SO, and OR,
	public static String getORSOOLabels(String tablename, Map<String, List<String>> filters, Map<String, String> recipients) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT c.code c.label "  +  
				  " FROM code AS c, codingsystem as cs, codingsystem_code as CSC ");
			
		sb.append(" WHERE ADAM.recipientcode = c.code " +
				  " AND cs.code = 'ADAM_RECIPIENTS' " +
				  " AND cs.resourceid = csc.codingsystem_resourceid " +
				  " AND c.id = csc.codes_id AND c.langcode = 'EN'");
				
		
		if ( !recipients.isEmpty() ) {
			sb.append(addSQLCondition("code", recipients, true));
		}
		
		LOGGER.info("getORSOOLabels query: " + sb);
		
		return sb.toString();
	}
	
 public static String getFAOComparativeAdvantageMatrix(String tablename, Map<String, List<String>> filters, List<String> selects,  Map<String,String> aggregations) {
		
		/**
		 
TOP: Combined

"41301" = FAO
"11" = Switzerland
625 = Afghanistan

select n1.year, n1.purposecode, n1.fao_sum, n2.all_sum,
cast(fao_sum as numeric)/all_sum as fao_delivery_per_sector  
from (select year, purposecode, SUM(usd_commitment) as fao_sum           
	from adam_crs_76025dea       
	where recipientcode='625' and channelcode='41301' and 
	donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
	(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010'))   
	group by year, purposecode) n1 
inner join (select year, purposecode, SUM(usd_commitment) as all_sum 
	      from adam_crs_76025dea  
            where recipientcode='625' and channelcode is not null and 
	donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
	(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010'))    
	      group by year, purposecode) n2   
on (n2.purposecode = n1.purposecode) AND (n2.year = n1.year)
ORDER BY year, purposecode

Bottom: 
Combined (all purposecodes)
select n1.year, n1.fao_sum, n2.all_sum,
cast(fao_sum as numeric)/all_sum as fao_total_delivery  
from (select year, SUM(usd_commitment) as fao_sum           
	from adam_crs_76025dea       
	where recipientcode='625' and channelcode='41301' and 
	donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
	purposecode is not null   
	group by year) n1 
inner join (select year, SUM(usd_commitment) as all_sum 
	      from adam_crs_76025dea  
            where recipientcode='625' and channelcode is not null and 
	donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
	purposecode is not null    
	      group by year) n2   
on (n2.year = n1.year)
ORDER BY year

 *
 *
 *FULL QUERY:::
 *SELECT  top.year, top.purposecode, fao_delivery_per_sector, fao_total_delivery, cast(fao_delivery_per_sector as numeric)/cast(fao_total_delivery as numeric) as ratio
FROM (select n1.year, n1.purposecode, fao_delivery_sum, delivery_all_sum,
	cast(fao_delivery_sum as numeric)/delivery_all_sum as fao_delivery_per_sector  
	from (select year, purposecode, SUM(usd_commitment) as fao_delivery_sum           
		from adam_crs_76025dea       
		where recipientcode='625' and channelcode='41301' and 
		donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
		(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010'))   
		group by year, purposecode) n1 
		
		inner join (select year, purposecode, SUM(usd_commitment) as delivery_all_sum 

		from adam_crs_76025dea  

		where recipientcode='625' and channelcode is not null and 
			donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
			(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010'))    
			group by year, purposecode) n2 
			  
		on (n2.purposecode = n1.purposecode) AND (n2.year = n1.year)) top
inner join
	(select n3.year, n3.fao_sum, n4.all_sum,
	cast(fao_sum as numeric)/all_sum as fao_total_delivery  
	from (select year, SUM(usd_commitment) as fao_sum           
		from adam_crs_76025dea       
		where recipientcode='625' and channelcode='41301' and 
		donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
		purposecode is not null   
		group by year) n3 
		inner join (select year, SUM(usd_commitment) as all_sum 
		from adam_crs_76025dea  
		where recipientcode='625' and channelcode is not null and 
		donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and  
		purposecode is not null  
		group by year) n4   
		on (n3.year = n4.year)) bottom
ON (top.year = bottom.year)
ORDER BY top.year;
 *
 *
 */
		
		StringBuilder sb = new StringBuilder();	
		
		// QUERY (rounded to 4 decimal places) TOP.donorname removed
		
		if(aggregations!=null){
			//year removed from select
			sb.append(" SELECT TOP.purposename, TOP.recipientname, " +
					"round(cast(fao_delivery_sum as numeric)/cast(fao_sum as numeric) * 100, 2) as sector_relavance_within_fao_budget, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric) * 100, 2) as fao_delivery_per_sector, " +
					"round(cast(fao_total_delivery as numeric) * 100, 2) as fao_total_delivery, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4) as comparative_advantage_ratio, ");
			sb.append(" CASE WHEN round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4)  > 1 THEN 'Yes'");
			sb.append(" ELSE 'No'");
			sb.append(" END AS fao_has_comparative_advantage ");
		    sb.append(" FROM ("+sectorSumYearRatioQuery(tablename, filters, selects)+") TOP ");
			sb.append(" INNER JOIN ("+totalSumYearDeliveryRatioQuery(tablename, filters, selects)+") BOTTOM ");
			sb.append(" ON ");
			sb.append(addONClause(selects, false, "TOP", "BOTTOM"));
			sb.append(" AND TOP.fao_delivery_per_sector is not null ");
			sb.append(" ORDER BY TOP.recipientname, TOP.purposename ");
			sb.append(addSelects(selects, true, "TOP"));	
		}
		
		else {
			//year included in the select
			sb.append(" SELECT TOP.purposename, TOP.recipientname, " +
					"round(cast(fao_delivery_sum as numeric)/cast(fao_sum as numeric) * 100, 2) as sector_relavance_within_fao_budget, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric) * 100, 2) as fao_delivery_per_sector, " +
					"round(cast(fao_total_delivery as numeric) * 100, 2) as fao_total_delivery, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4) as comparative_advantage_ratio, ");
			sb.append(" CASE WHEN round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4)  > 1 THEN 'Yes'");
			sb.append(" ELSE 'No'");
			sb.append(" END AS fao_has_comparative_advantage, ");
			sb.append(" CAST(EXTRACT(year from TOP.year) AS varchar) as year ");
		    sb.append(" FROM ("+sectorRatioQuery(tablename, filters, selects)+") TOP ");
			sb.append(" INNER JOIN ("+totalDeliveryRatioQuery(tablename, filters, selects)+") BOTTOM ");
			sb.append(" ON (TOP.year = BOTTOM.year) ");
			sb.append(addONClause(selects, true, "TOP", "BOTTOM"));
			sb.append(" AND TOP.fao_delivery_per_sector is not null ");
			sb.append(" ORDER BY TOP.year, TOP.recipientname, TOP.purposename ");
			sb.append(addSelects(selects, true, "TOP"));	
		}
		
		return sb.toString();
	}
 
 
 public static String getFAOComparativeAdvantageORMatrix(String tablename, String conversiontablename, Map<String, List<String>> filters, List<String> selects,  Map<String,String> aggregations) {
		
		/** Includes the conversion of the DAC purpose code to OR. The budget is split by the number of ORs that represent the purposecode
		 * e.g purpose code 12240 is matched to 4 ORs - D01,D02,D03,D04,H03. So the budget of each OR =  total budget for 12240 divided by 4
		 * 
 SELECT TOP.orname ||': '||  c.label as orname, TOP.recipientname, round(cast(fao_delivery_sum as numeric)/cast(fao_sum as numeric) * 100, 2) 
	 as sector_relavance_within_fao_budget, round(cast(TOP.fao_delivery_per_sector as numeric) * 100, 2) as fao_delivery_per_sector, 
	 round(cast(fao_total_delivery as numeric) * 100, 2) as fao_total_delivery, 
	 round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4) as comparative_advantage_ratio,  
	 CASE WHEN round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4)  > 1 THEN 'Yes' ELSE 'No' END AS fao_has_comparative_advantage,  
	 CAST(EXTRACT(year from TOP.year) AS varchar) as year  
	  FROM codingsystem AS cs, code as c, codingsystem_code AS csc,
	 
	 (SELECT n1.year, n1.orcode, n1.orname, fao_delivery_sum, delivery_all_sum, CASE WHEN delivery_all_sum > 0 THEN CAST(fao_delivery_sum as numeric)/ delivery_all_sum ELSE null END AS fao_delivery_per_sector , 
	 n1.recipientname 
	 
	 FROM 
	 (SELECT GROUPED_OR_RESULTS.year, GROUPED_OR_RESULTS.orcode, GROUPED_OR_RESULTS.orname, SUM(GROUPED_OR_RESULTS.sum) as fao_delivery_sum , 
	 GROUPED_OR_RESULTS.recipientname 
     FROM 
	 (SELECT year, CONV.fao as orcode, CONV.fao as orname, SUM(CAST( usd_commitment as numeric )) / OCCURRENCES.count as sum, recipientname  
	  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 CONV, ADAM_CRS_e599fc32 DATA  
	  INNER JOIN 
	  (SELECT dac, COUNT(fao) as count  
	  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 WHERE  dac IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  
	  GROUP BY dac ) AS OCCURRENCES   
	  ON DATA.purposecode = OCCURRENCES.dac 
	  WHERE channelcode='41301'  
	  AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
	  AND  purposecode IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  
	  AND DATA.purposecode = CONV.dac AND CONV.fao !~* '.00' 
	  GROUP BY year, CONV.fao, OCCURRENCES.count, recipientname  
	  ORDER BY year, CONV.fao , recipientname ) GROUPED_OR_RESULTS 
	  
	 GROUP BY GROUPED_OR_RESULTS.year, GROUPED_OR_RESULTS.orcode, GROUPED_OR_RESULTS.orname , GROUPED_OR_RESULTS.recipientname ) n1 
	  
	 INNER JOIN 
	  (SELECT GROUPED_OR_DELIVERYALL.year, GROUPED_OR_DELIVERYALL.orcode, GROUPED_OR_DELIVERYALL.orname, SUM(GROUPED_OR_DELIVERYALL.split_delivery) as delivery_all_sum , 
	  GROUPED_OR_DELIVERYALL.recipientname  
	  FROM 
	  (SELECT year, CONV.fao as orcode, CONV.fao as orname, SUM(CAST( usd_commitment as numeric )) / OCCURRENCES.count as split_delivery, recipientname  
	  FROM 
	    FAO_DAC_CONVERSION_TABLE_09b403b0 CONV, ADAM_CRS_e599fc32 DATA     
	   INNER JOIN 
	    (SELECT dac, COUNT(fao) as count FROM FAO_DAC_CONVERSION_TABLE_09b403b0 
	     WHERE  dac IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  
	     GROUP BY dac ) AS OCCURRENCES   
	   ON DATA.purposecode = OCCURRENCES.dac 
	   WHERE channelcode is not null  
	   AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
	   AND  purposecode IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  AND DATA.purposecode = CONV.dac AND CONV.fao !~* '.00' GROUP BY year, CONV.fao, OCCURRENCES.count , recipientname  
	   ORDER BY year, CONV.fao , recipientname ) GROUPED_OR_DELIVERYALL 
	  
	  GROUP BY GROUPED_OR_DELIVERYALL.year, GROUPED_OR_DELIVERYALL.orcode, GROUPED_OR_DELIVERYALL.orname , GROUPED_OR_DELIVERYALL.recipientname ) n2 
	  
	  ON (n1.orcode = n2.orcode) AND (n1.year = n2.year)  
	  AND (n1.recipientname = n2.recipientname) ) TOP  

	  INNER JOIN (SELECT n3.year, n3.fao_sum, n4.all_sum, CASE WHEN all_sum > 0 THEN 
	  CAST(fao_sum as numeric)/all_sum ELSE null END AS fao_total_delivery , n3.recipientname 
	  FROM 
	  (SELECT year, SUM(usd_commitment) as fao_sum, recipientname  FROM ADAM_CRS_e599fc32 
	   WHERE channelcode='41301'  AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) AND  
	  purposecode IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  
	  GROUP BY year, recipientname ) n3  
	  
	  INNER JOIN (SELECT year, SUM(usd_commitment) as all_sum , recipientname  FROM ADAM_CRS_e599fc32 
	  WHERE channelcode is not null  AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
	  AND  purposecode IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  
	  GROUP BY year, recipientname ) n4 
	  
	  ON (n3.year = n4.year) AND CAST(fao_sum as numeric)/all_sum <> 0 AND (n3.recipientname = n4.recipientname) ) BOTTOM  
	  
	  ON (TOP.year = BOTTOM.year)  
	  AND (TOP.recipientname = BOTTOM.recipientname)  
	  AND TOP.fao_delivery_per_sector is not null  
	  WHERE  cs.resourceid = csc.codingsystem_resourceid AND csc.codes_id = c.id AND TOP.orname = c.code 
	  AND c.langcode = 'EN' AND cs.title = 'FAO Strategic Framework' 
	  ORDER BY TOP.year, TOP.recipientname, TOP.orname, TOP.recipientname

*/
		
		StringBuilder sb = new StringBuilder();	
		
		// QUERY (rounded to 4 decimal places) TOP.donorname removed
		
		if(aggregations!=null){
			//year removed from select
			sb.append(" SELECT TOP.orname ||': '||  c.label as orname, TOP.recipientname, " +
					"round(cast(fao_delivery_sum as numeric)/cast(fao_sum as numeric) * 100, 2) as sector_relavance_within_fao_budget, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric) * 100, 2) as fao_delivery_per_sector, " +
					"round(cast(fao_total_delivery as numeric) * 100, 2) as fao_total_delivery, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4) as comparative_advantage_ratio, ");
			sb.append(" CASE WHEN round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4)  > 1 THEN 'Yes'");
			sb.append(" ELSE 'No'");
			sb.append(" END AS fao_has_comparative_advantage ");
		    sb.append(" FROM codingsystem AS cs, code as c, codingsystem_code AS csc, ("+sectorSumYearORRatioQuery(tablename, conversiontablename, filters, selects)+") TOP ");
			sb.append(" INNER JOIN ("+totalSumYearDeliveryRatioQuery(tablename, filters, selects)+") BOTTOM ");
			sb.append(" ON ");
			sb.append(addONClause(selects, false, "TOP", "BOTTOM"));
			sb.append(" AND TOP.fao_delivery_per_sector is not null ");
			sb.append(" WHERE cs.resourceid = csc.codingsystem_resourceid AND csc.codes_id = c.id AND TOP.orname = c.code"); 
			sb.append(" AND c.langcode = 'EN' AND cs.title = 'FAO Strategic Framework' ");
			sb.append(" ORDER BY TOP.recipientname, TOP.orname ");
			sb.append(addSelects(selects, true, "TOP"));	
		}
		
		else {
			//year included in the select
			sb.append(" SELECT TOP.orname ||': '||  c.label as orname, TOP.recipientname, " +
					"round(cast(fao_delivery_sum as numeric)/cast(fao_sum as numeric) * 100, 2) as sector_relavance_within_fao_budget, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric) * 100, 2) as fao_delivery_per_sector, " +
					"round(cast(fao_total_delivery as numeric) * 100, 2) as fao_total_delivery, " +
					"round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4) as comparative_advantage_ratio, ");
			sb.append(" CASE WHEN round(cast(TOP.fao_delivery_per_sector as numeric)/cast(BOTTOM.fao_total_delivery as numeric), 4)  > 1 THEN 'Yes'");
			sb.append(" ELSE 'No'");
			sb.append(" END AS fao_has_comparative_advantage, ");
			sb.append(" CAST(EXTRACT(year from TOP.year) AS varchar) as year ");
		    sb.append(" FROM codingsystem AS cs, code as c, codingsystem_code AS csc, ("+orRatioQuery(tablename, conversiontablename, filters, selects)+") TOP ");
			sb.append(" INNER JOIN ("+totalDeliveryRatioQuery(tablename, filters, selects)+") BOTTOM ");
			sb.append(" ON (TOP.year = BOTTOM.year) ");
			sb.append(addONClause(selects, true, "TOP", "BOTTOM"));
			sb.append(" AND TOP.fao_delivery_per_sector is not null ");
			sb.append(" WHERE cs.resourceid = csc.codingsystem_resourceid AND csc.codes_id = c.id AND TOP.orname = c.code"); 
			sb.append(" AND c.langcode = 'EN' AND cs.title = 'FAO Strategic Framework' ");
			sb.append(" ORDER BY TOP.year, TOP.recipientname, TOP.orname ");
			sb.append(addSelects(selects, true, "TOP"));	
		}
		
		return sb.toString();
	}
 
 private static String sectorRatioQuery(String tablename, Map<String, List<String>> filters, List<String> selects){
	 
	 //channelcode='41301' is FAO
	 
	 StringBuilder sb = new StringBuilder();	
	 
	 sb.append("SELECT n1.year, n1.purposecode, n1.purposename, fao_delivery_sum, delivery_all_sum, ");
	 sb.append("CASE WHEN delivery_all_sum > 0 THEN CAST(fao_delivery_sum as numeric)/ delivery_all_sum ELSE null END AS fao_delivery_per_sector ");
	 
	 sb.append(addSelects(selects, true, "n1"));
	 sb.append("FROM ");
	 //inner select
	 sb.append("(SELECT year, purposecode, purposename, SUM(usd_commitment) as fao_delivery_sum"); 
	        sb.append(addSelects(selects, true, null));
	 		sb.append(" FROM "+tablename+" ");
	 		sb.append("WHERE channelcode='41301' ");
	 		//sb.append("AND purposecode is not null ");
	 		for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
		 	}
	 		//sb.append("WHERE recipientcode='625' and channelcode='41301' and "); 
	 		//sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 		//sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 		sb.append(addFAOPurposeCodes("purposecode", true));
	 		sb.append(" GROUP BY year, purposecode, purposename"); 
	 		sb.append(addSelects(selects, true, null));
	 		sb.append(") n1  ");
	 sb.append("INNER JOIN ");
	 	sb.append("(SELECT year, purposecode, purposename, SUM(usd_commitment) as delivery_all_sum"); 
	 	sb.append(addSelects(selects, true, null));
	 	sb.append(" FROM "+tablename+" ");
	 	sb.append("WHERE channelcode is not null "); 
	 	//sb.append("AND purposecode is not null ");	 	
	 	for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
	 	}
	 		
	 //	sb.append("WHERE recipientcode='625' and channelcode is not null and "); 
	 //	sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	// sb.append("(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010') ");  //previously commented out  	 
	 //sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 sb.append(addFAOPurposeCodes("purposecode", true));
	 sb.append(" GROUP BY year, purposecode, purposename");     
	 sb.append(addSelects(selects, true, null));
	 sb.append(") n2  ");
	 sb.append("ON (n1.purposecode = n2.purposecode) AND (n1.year = n2.year) ");    
	 sb.append(addONClause(selects, true, "n1", "n2"));
	 //sb.append(" AND fao_delivery_sum <> null");
	 
	 return sb.toString();
 }
 
 
private static String orRatioQuery(String tablename, String conversiontablename, Map<String, List<String>> filters, List<String> selects){
	 
	 //channelcode='41301' is FAO
	 StringBuilder sb = new StringBuilder();	
	 
	 sb.append("SELECT n1.year, n1.orcode, n1.orname, fao_delivery_sum, delivery_all_sum, ");
	 sb.append("CASE WHEN delivery_all_sum > 0 THEN CAST(fao_delivery_sum as numeric)/ delivery_all_sum ELSE null END AS fao_delivery_per_sector ");
	 
	 sb.append(addSelects(selects, true, "n1"));
	 sb.append("FROM ");
	 
	 //Group all ORs together, so 1 Summed value for the OR
	 //e.g. "2006-01-01";"A1";"A1"; 5.0950, "Bilateral, unspecified" INSTEAD OF 2 rows 
	 //"2006-01-01";"A1";"A1";0.2500;"Bilateral, unspecified"
	 // "2006-01-01";"A1";"A1";4.8450;"Bilateral, unspecified"
	 
	 sb.append("(SELECT GROUPED_OR_RESULTS.year, GROUPED_OR_RESULTS.orcode, GROUPED_OR_RESULTS.orname, SUM(GROUPED_OR_RESULTS.sum) as fao_delivery_sum ");
	 sb.append(addSelects(selects, true, "GROUPED_OR_RESULTS"));
	 sb.append("FROM ");

	 //inner select
	 sb.append("(SELECT year, CONV.fao as orcode, CONV.fao as orname, SUM(CAST( usd_commitment as numeric )) / OCCURRENCES.count as sum"); 
	        sb.append(addSelects(selects, true, null));
	        sb.append(" FROM "+conversiontablename+" CONV, "+tablename + " DATA ");
	 		//INNER JOIN to get ORs
	 		sb.append(" INNER JOIN (SELECT dac, COUNT(fao) as count ");
	 		sb.append(" FROM "+conversiontablename);
	 		sb.append(" WHERE ");
	 		sb.append(addFAOPurposeCodes("dac", false));
	 		sb.append(" GROUP BY dac ) AS OCCURRENCES  ");
	 		sb.append(" ON purposecode = OCCURRENCES.dac ");			 
	 		sb.append("WHERE channelcode='41301' ");
	 		//sb.append("AND purposecode is not null ");
	 		for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
		 	}
	 		//sb.append("WHERE recipientcode='625' and channelcode='41301' and "); 
	 		//sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 		//sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 		sb.append(addFAOPurposeCodes("purposecode", true));
	 		sb.append(" AND purposecode = CONV.dac");
	 		sb.append(" AND CONV.fao !~* '.00'");
	 		sb.append(" GROUP BY year, CONV.fao, OCCURRENCES.count");
	 		sb.append(addSelects(selects, true, null));
	 		sb.append(" ORDER BY year, CONV.fao ");
	 		sb.append(addSelects(selects, true, null));
	 	sb.append(") GROUPED_OR_RESULTS ");
	    sb.append("GROUP BY GROUPED_OR_RESULTS.year, GROUPED_OR_RESULTS.orcode, GROUPED_OR_RESULTS.orname ");
	    sb.append(addSelects(selects, true, "GROUPED_OR_RESULTS"));
	    sb.append(") n1 ");
	 				
	 sb.append("INNER JOIN ");
	 //Group all ORs together, so 1 Summed value for the OR
	 sb.append("(SELECT GROUPED_OR_DELIVERYALL.year, GROUPED_OR_DELIVERYALL.orcode, GROUPED_OR_DELIVERYALL.orname, SUM(GROUPED_OR_DELIVERYALL.split_delivery) as delivery_all_sum ");
	 sb.append(addSelects(selects, true, "GROUPED_OR_DELIVERYALL"));
	 sb.append(" FROM ");
	 sb.append("(SELECT year, CONV.fao as orcode, CONV.fao as orname, SUM(CAST( usd_commitment as numeric )) / OCCURRENCES.count as split_delivery"); 
	 	sb.append(addSelects(selects, true, null));
	 	 sb.append(" FROM "+conversiontablename+" CONV, "+tablename + " DATA ");
	 	//INNER JOIN to get ORs
	 	sb.append(" INNER JOIN (SELECT dac, COUNT(fao) as count ");
		sb.append(" FROM "+conversiontablename);
 		sb.append(" WHERE ");
 		sb.append(addFAOPurposeCodes("dac", false));
 		sb.append(" GROUP BY dac ) AS OCCURRENCES  ");
	 	sb.append(" ON purposecode = OCCURRENCES.dac ");			 
	 	sb.append("WHERE channelcode is not null "); 
	 	//sb.append("AND purposecode is not null ");	 	
	 	for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
	 	}
	 		
	 //	sb.append("WHERE recipientcode='625' and channelcode is not null and "); 
	 //	sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	// sb.append("(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010') ");  //previously commented out  	 
	 //sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 sb.append(addFAOPurposeCodes("purposecode", true));
	 sb.append(" AND purposecode = CONV.dac");
	 sb.append(" AND CONV.fao !~* '.00'");
	 sb.append(" GROUP BY year, CONV.fao, OCCURRENCES.count ");   
	 sb.append(addSelects(selects, true, null));
	 sb.append(" ORDER BY year, CONV.fao ");   
	 sb.append(addSelects(selects, true, null));	
	 sb.append(") GROUPED_OR_DELIVERYALL ");
	 sb.append("GROUP BY GROUPED_OR_DELIVERYALL.year, GROUPED_OR_DELIVERYALL.orcode, GROUPED_OR_DELIVERYALL.orname ");
	 sb.append(addSelects(selects, true, "GROUPED_OR_DELIVERYALL"));
	 sb.append(") n2 ");
	 
	 
	 sb.append("ON (n1.orcode = n2.orcode) AND (n1.year = n2.year) ");    
	 sb.append(addONClause(selects, true, "n1", "n2"));
	 //sb.append(" AND fao_delivery_sum <> null");
	 
	 return sb.toString();
 }

 
private static String totalDeliveryRatioQuery(String tablename, Map<String, List<String>> filters, List<String> selects){
	 
	 //channelcode='41301' is FAO
	 // all purpose codes, so remove FAO Sector filter
	 
	if(filters.containsKey("purposecode"))
		filters.remove("purposecode");
	
	 StringBuilder sb = new StringBuilder();	
	 
	 sb.append("SELECT n3.year, n3.fao_sum, n4.all_sum, ");
	 sb.append("CASE WHEN all_sum > 0 THEN CAST(fao_sum as numeric)/all_sum ELSE null END AS fao_total_delivery "); // handling divide by 0
	 sb.append(addSelects(selects, true, "n3"));
	 sb.append("FROM ");
	 //inner select
	 sb.append("(SELECT year, SUM(usd_commitment) as fao_sum");  
	 sb.append(addSelects(selects, true, null));
	  		sb.append(" FROM "+tablename+" ");
	 		sb.append("WHERE channelcode='41301' ");
	 		//sb.append("AND purposecode is not null ");
	 		for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
		 	}
	 		//sb.append("WHERE recipientcode='625' and channelcode='41301' and "); 
	 		//sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 		//sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");  //previously commented out  	 
	 		sb.append(addFAOPurposeCodes("purposecode", true));
	 		sb.append(" GROUP BY year"); 
	 		sb.append(addSelects(selects, true, null));
	 		sb.append(") n3  "); 
	 		
	 sb.append("INNER JOIN ");
	 	sb.append("(SELECT year, SUM(usd_commitment) as all_sum "); 
	 	 sb.append(addSelects(selects, true, null));
	 	sb.append(" FROM "+tablename+" ");
	 	sb.append("WHERE channelcode is not null ");  	
	 	//sb.append("AND purposecode is not null ");
	 	for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
	 	}
	 	//sb.append("WHERE recipientcode='625' and channelcode is not null and "); 
	//	sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 	//sb.append("AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");    
	 	sb.append(addFAOPurposeCodes("purposecode", true));
	 	sb.append(" GROUP BY year"); 
 		sb.append(addSelects(selects, true, null));
 		sb.append(") n4  ");
	 sb.append("ON (n3.year = n4.year) AND CAST(fao_sum as numeric)/all_sum <> 0");  
	 sb.append(addONClause(selects, true, "n3", "n4"));
	 //sb.append(" AND fao_sum <> null");
	 
	 return sb.toString();
 }


private static String sectorSumYearRatioQuery(String tablename, Map<String, List<String>> filters, List<String> selects){
	 
	 //channelcode='41301' is FAO
	 
	 StringBuilder sb = new StringBuilder();	
	 
	 sb.append("SELECT n1.purposecode, n1.purposename, fao_delivery_sum, delivery_all_sum, ");
	 sb.append("CASE WHEN delivery_all_sum > 0 THEN CAST(fao_delivery_sum as numeric)/ delivery_all_sum ELSE null END AS fao_delivery_per_sector ");
	 
	 sb.append(addSelects(selects, true, "n1"));
	 sb.append("FROM ");
	 //inner select
	 sb.append("(SELECT purposecode, purposename, SUM(usd_commitment) as fao_delivery_sum"); 
	        sb.append(addSelects(selects, true, null));
	 		sb.append(" FROM "+tablename+" ");
	 		sb.append("WHERE channelcode='41301' ");
	 		//sb.append("AND purposecode is not null ");
	 		for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
		 	}
	 		//sb.append("WHERE recipientcode='625' and channelcode='41301' and "); 
	 		//sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 		//sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 		sb.append(addFAOPurposeCodes("purposecode", true));
	 		sb.append(" GROUP BY purposecode, purposename"); 
	 		sb.append(addSelects(selects, true, null));
	 		sb.append(") n1  ");
	 sb.append("INNER JOIN ");
	 	sb.append("(SELECT purposecode, purposename, SUM(usd_commitment) as delivery_all_sum"); 
	 	sb.append(addSelects(selects, true, null));
	 	sb.append(" FROM "+tablename+" ");
	 	sb.append("WHERE channelcode is not null "); 
	 	//sb.append("AND purposecode is not null ");	 	
	 	for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
	 	}
	 		
	 //	sb.append("WHERE recipientcode='625' and channelcode is not null and "); 
	 //	sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	// sb.append("(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010') ");  //previously commented out  	 
	 //sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 	sb.append(addFAOPurposeCodes("purposecode", true));
	 sb.append(" GROUP BY purposecode, purposename");     
	 sb.append(addSelects(selects, true, null));
	 sb.append(") n2  ");
	 sb.append("ON (n1.purposecode = n2.purposecode) ");    
	 sb.append(addONClause(selects, true, "n1", "n2"));
	 //sb.append(" AND fao_delivery_sum <> null");
	 
	 return sb.toString();
}

private static String sectorSumYearORRatioQuery(String tablename, String conversiontablename, Map<String, List<String>> filters, List<String> selects){
	 
	 //channelcode='41301' is FAO
	 
	 StringBuilder sb = new StringBuilder();	
	 
	 
	 
	 /*SELECT TEST.year, TEST.orcode, TEST.recipientname, SUM(TEST.fao_delivery_sum) as fao_delivery_sum
	 FROM (
	 SELECT year, CONV.fao as orcode, CONV.fao as orname, SUM(CAST( usd_commitment as numeric )) / OCCURRENCES.count as fao_delivery_sum, recipientname  
	 FROM FAO_DAC_CONVERSION_TABLE_09b403b0 CONV, ADAM_CRS_e599fc32 DATA  
	 INNER JOIN (SELECT dac, COUNT(fao) as count  FROM FAO_DAC_CONVERSION_TABLE_09b403b0 
	 WHERE  dac IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  
	 GROUP BY dac ) AS OCCURRENCES   
	 ON DATA.purposecode = OCCURRENCES.dac WHERE channelcode='41301'  
	 AND year IN ( '2006-01-01' ,  '2007-01-01' ,  '2008-01-01' ,  '2009-01-01' ,  '2010-01-01'  ) 
	 AND  purposecode IN ('12240', '14010', '14015', '14040', '15160', '15170', '16062', '25010', '31110', '31120', '31130', '31140', '31150', '31161', '31162', '31163', '31164', '31166', '31181', '31182', '31191', '31192', '31193', '31194', '31195', '31210', '31220', '31261', '31281', '31282', '31291', '31310', '31320', '31381', '31382', '31391', '32161', '33110', '41010', '41020', '41030', '41082', '43040', '52010', '72040', '73010', '74010')  
	 AND DATA.purposecode = CONV.dac AND CONV.fao !~* '.00' 
	 GROUP BY year, OCCURRENCES.count, CONV.fao, recipientname  
	 ORDER BY year, CONV.fao, recipientname) TEST
	 GROUP BY TEST.year, TEST.orcode, TEST.recipientname
	 
	*/

	 
	 
	 sb.append("SELECT n1.orcode, n1.orname, fao_delivery_sum, delivery_all_sum, ");
	 sb.append("CASE WHEN delivery_all_sum > 0 THEN CAST(fao_delivery_sum as numeric)/ delivery_all_sum ELSE null END AS fao_delivery_per_sector ");
	 sb.append(addSelects(selects, true, "n1"));
	 sb.append("FROM ");
	 
	 //Group all ORs together, so 1 Summed value for the OR
	 //e.g. "2006-01-01";"A1";"A1"; 5.0950, "Bilateral, unspecified" INSTEAD OF 2 rows 
	 //"2006-01-01";"A1";"A1";0.2500;"Bilateral, unspecified"
	 // "2006-01-01";"A1";"A1";4.8450;"Bilateral, unspecified"
	 
	 sb.append("(SELECT GROUPED_OR_RESULTS.orcode, GROUPED_OR_RESULTS.orname, SUM(GROUPED_OR_RESULTS.sum) as fao_delivery_sum ");
	 sb.append(addSelects(selects, true, "GROUPED_OR_RESULTS"));
	 sb.append("FROM ( ");
	 	//inner select
	 	sb.append("SELECT CONV.fao as orcode, CONV.fao as orname, SUM(CAST( usd_commitment as numeric )) / OCCURRENCES.count as sum"); 
	        sb.append(addSelects(selects, true, null));
	 		sb.append(" FROM "+conversiontablename+" CONV, "+tablename + " DATA ");
	 		//INNER JOIN to get ORs
	 		sb.append(" INNER JOIN (SELECT dac, COUNT(fao) as count ");
	 		sb.append(" FROM "+conversiontablename+"  ");
	 		sb.append(" WHERE ");
	 		sb.append(addFAOPurposeCodes("dac", false));
	 		sb.append(" GROUP BY dac ) AS OCCURRENCES  ");
	 		sb.append(" ON purposecode = OCCURRENCES.dac ");			 
	 		sb.append("WHERE channelcode='41301' ");
	 		//sb.append("AND purposecode is not null ");
	 		for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
		 	}
	 		//sb.append("WHERE recipientcode='625' and channelcode='41301' and "); 
	 		//sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 		//sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 		sb.append(addFAOPurposeCodes("purposecode", true));
	 		sb.append(" AND purposecode = CONV.dac");
	 		sb.append(" AND CONV.fao !~* '.00'");
	 		sb.append(" GROUP BY CONV.fao, OCCURRENCES.count "); 
	 		sb.append(addSelects(selects, true, null));
	 		sb.append(" ORDER BY CONV.fao ");
	 		sb.append(addSelects(selects, true, null));
	 	sb.append(") GROUPED_OR_RESULTS ");
	    sb.append("GROUP BY GROUPED_OR_RESULTS.orcode, GROUPED_OR_RESULTS.orname ");
	    sb.append(addSelects(selects, true, "GROUPED_OR_RESULTS"));
	    sb.append(") n1 ");
	 		
	 sb.append("INNER JOIN ");
	 //Group all ORs together, so 1 Summed value for the OR
	 //e.g. "2006-01-01";"A1";"A1"; 5.0950, "Bilateral, unspecified" INSTEAD OF 2 rows 
	 //"2006-01-01";"A1";"A1";0.2500;"Bilateral, unspecified"
	 // "2006-01-01";"A1";"A1";4.8450;"Bilateral, unspecified"

	 sb.append("(SELECT GROUPED_OR_DELIVERYALL.orcode, GROUPED_OR_DELIVERYALL.orname, SUM(GROUPED_OR_DELIVERYALL.split_delivery) as delivery_all_sum ");
	 sb.append(addSelects(selects, true, "GROUPED_OR_DELIVERYALL"));
	 sb.append("FROM ");
	 	sb.append("(SELECT CONV.fao as orcode, CONV.fao as orname, SUM(CAST( usd_commitment as numeric )) / OCCURRENCES.count as split_delivery"); 
	 	sb.append(addSelects(selects, true, null));
	 	sb.append(" FROM "+conversiontablename+" CONV, "+tablename);
	 	//INNER JOIN to get ORS
	 	sb.append(" INNER JOIN (SELECT dac, COUNT(fao) as count ");
 		sb.append(" FROM "+conversiontablename+" ");
 		sb.append(" WHERE ");
 		sb.append(addFAOPurposeCodes("dac", false));
 		sb.append(" GROUP BY dac ) AS OCCURRENCES  ");
 		sb.append(" ON purposecode = OCCURRENCES.dac ");		
 		sb.append("WHERE channelcode is not null "); 
	 	//sb.append("AND purposecode is not null ");	 	
	 	for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
	 	}
	 		
	 //	sb.append("WHERE recipientcode='625' and channelcode is not null and "); 
	 //	sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	// sb.append("(purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010') ");  //previously commented out  	 
	 //sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");   
	 	sb.append(addFAOPurposeCodes("purposecode", true));
	 sb.append(" AND purposecode = CONV.dac");
	 sb.append(" AND CONV.fao !~* '.00'");
	 sb.append(" GROUP BY CONV.fao, OCCURRENCES.count ");  
	 sb.append(addSelects(selects, true, null));
	 sb.append(" ORDER BY CONV.fao ");
	 sb.append(addSelects(selects, true, null));
	 sb.append(") GROUPED_OR_DELIVERYALL ");
	 sb.append("GROUP BY GROUPED_OR_DELIVERYALL.orcode, GROUPED_OR_DELIVERYALL.orname ");
	 sb.append(addSelects(selects, true, "GROUPED_OR_DELIVERYALL"));
	 sb.append(") n2 ");

	 sb.append("ON (n1.orcode = n2.orcode) ");    
	 sb.append(addONClause(selects, true, "n1", "n2"));
	 //sb.append(" AND fao_delivery_sum <> null");
	 
	 return sb.toString();
}

private static String totalSumYearDeliveryRatioQuery(String tablename, Map<String, List<String>> filters, List<String> selects){
	 
	 //channelcode='41301' is FAO
	 // all purpose codes, so remove FAO Sector filter
	 
	if(filters.containsKey("purposecode"))
		filters.remove("purposecode");
	
	 StringBuilder sb = new StringBuilder();	
	 
	 sb.append("SELECT n3.fao_sum, n4.all_sum, ");
	 sb.append("CASE WHEN all_sum > 0 THEN CAST(fao_sum as numeric)/all_sum ELSE null END AS fao_total_delivery "); // handling divide by 0
	 sb.append(addSelects(selects, true, "n3"));
	 sb.append("FROM ");
	 //inner select
	 sb.append("(SELECT SUM(usd_commitment) as fao_sum");  
	 sb.append(addSelects(selects, true, null));
	  		sb.append(" FROM "+tablename+" ");
	 		sb.append("WHERE channelcode='41301' ");
	 		//sb.append("AND purposecode is not null ");
	 		for(String filter : filters.keySet() ) {
				sb.append(addSQLCondition(filter, filters.get(filter), true));
		 	}
	 		//sb.append("WHERE recipientcode='625' and channelcode='41301' and "); 
	 		//sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 		//sb.append(" AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");  //previously commented out  	 
	 		sb.append(addFAOPurposeCodes("purposecode", true));
	 		sb.append(" GROUP BY "); 
	 		sb.append(addSelects(selects, false, null));
	 		sb.append(") n3  "); 
	 		
	 sb.append("INNER JOIN ");
	 	sb.append("(SELECT SUM(usd_commitment) as all_sum "); 
	 	 sb.append(addSelects(selects, true, null));
	 	sb.append(" FROM "+tablename+" ");
	 	sb.append("WHERE channelcode is not null ");  	
	 	//sb.append("AND purposecode is not null ");
	 	for(String filter : filters.keySet() ) {
			sb.append(addSQLCondition(filter, filters.get(filter), true));
	 	}
	 	//sb.append("WHERE recipientcode='625' and channelcode is not null and "); 
	//	sb.append("donorcode='11' and year in ('2009-01-01','2008-01-01','2007-01-01', '2006-01-01') and "); 
	 	//sb.append("AND (purposecode like '31%' OR purposecode in ('43040', '52010', '72010', '72040','73010', '74010')) ");    
	 	sb.append(addFAOPurposeCodes("purposecode", true));
	 	sb.append(" GROUP BY "); 
		sb.append(addSelects(selects, false, null));
		sb.append(") n4  ");
	 sb.append("ON CAST(fao_sum as numeric)/all_sum <> 0 ");  
	 sb.append(addONClause(selects, true, "n3", "n4"));
	 //sb.append(" AND fao_sum <> null");
	 
	 return sb.toString();
}


private static StringBuffer addFAOPurposeCodes(String fieldName, boolean addAnd) {
	StringBuffer sqlQuery = new StringBuffer();
	if(addAnd)
		sqlQuery.append(" AND ");
	
	sqlQuery.append(" "+fieldName+" IN " +
			"('12240', " +
			"'14010', " +
			"'14015', " +
			"'14040', " +
			"'15160', " +
			"'15170', " +
			"'16062', " +
			"'25010', " +
			"'31110', " +
			"'31120', " +
			"'31130', " +
			"'31140', " +
			"'31150', " +
			"'31161', " +
			"'31162', " +
			"'31163', " +
			"'31164', " +
			"'31166', " +
			"'31181', " +
			"'31182', " +
			"'31191', " +
			"'31192', " +
			"'31193', " +
			"'31194', " +
			"'31195', " +
			"'31210', " +
			"'31220', " +
			"'31261', " +
			"'31281', " +
			"'31282', " +
			"'31291', " +
			"'31310', " +
			"'31320', " +
			"'31381', " +
			"'31382', " +
			"'31391', " +
			"'32161', " +
			"'33110', " +
			"'41010', " +
			"'41020', " +
			"'41030', " +
			"'41082', " +
			"'43040', " +
			"'52010', " +
			"'72040', " +
			"'73010', " +
			"'74010') " );

	/**sqlQuery.append(" purposecode IN " +
			"('31110', " +
			"'31120', " +
			"'31130', " +
			"'31140', " +
			"'31150', " +
			"'31161', " +
			"'31162', " +
			"'31163', " +
			"'31164', " +
			"'31165', " +
			"'31166', " +
			"'31181', " +
			"'31182', " +
			"'31191', " +
			"'31192', " +
			"'31193', " +
			"'31194', " +
			"'31195', " +
			"'31210', " +
			"'31220', " +
			"'31261', " +
			"'31281', " +
			"'31282', " +
			"'31291', " +
			"'31310', " +
			"'31320', " +
			"'31381', " +
			"'31382', " +
			"'31391', " +
			"'32161', " +
			"'32162', " +
			"'32165', " +
			"'41010', " +
			"'41030', " +
			"'41082', " +
			"'43040', " +
			"'52010', " +
			"'72040', " +
			"'73010', " +
			"'74010')");**/
	
	return sqlQuery;
}


private static StringBuffer addSelects(List<String> selects, Boolean addCOMMA, String alias) {
	StringBuffer sqlQuery = new StringBuffer();
	if ( addCOMMA && selects.size() > 0)
		sqlQuery.append(", ");
	
	int j = 0;
	for(String select : selects){ 
		if(alias!=null)
			sqlQuery.append(alias+"."+select);
		else
			sqlQuery.append(select);
		
		 if ( j < selects.size() -1) {
			 sqlQuery.append(", ");
		 }
		 sqlQuery.append(" ");
		 j++;
	 }
	
	return sqlQuery;
}

private static StringBuffer addONClause(List<String> selects, Boolean addAND, String table1, String table2) {
	StringBuffer sqlQuery = new StringBuffer();
	if ( addAND && selects.size() > 0)
		sqlQuery.append(" AND ");
	
	int j = 0;
	for(String select : selects){ 
		sqlQuery.append("("+table1+"."+select +" = "+table2+"."+select+")");
		 if ( j < selects.size() -1) {
			 sqlQuery.append(" AND ");
		 }
		 sqlQuery.append(" ");
		 j++;
	 }
	
	return sqlQuery;
}


	//this should changed in order to get the SO, and OR,
	 public static String getAllRecipientsPriorities(String tablename, String conversiontablename, Map<String, List<String>> filters, Map<String, String> recipients, String dataset) {
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT ADAM.key_so, ADAM.key_or, ADAM.recipientcode, c.label, ADAM.comment, ADAM.stated_priority, ADAM.source "  +  
				  " FROM "+ tablename + " ADAM, code AS c, codingsystem as cs, codingsystem_code as CSC ");
			
		sb.append(" WHERE ADAM.recipientcode = c.code AND cs.code = 'ADAM_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN'");
				
		
		if ( !recipients.isEmpty() ) {
			sb.append(" AND recipientcode IN (SELECT adam_crs_code from "+conversiontablename+" WHERE  ");
			sb.append(addSQLCondition(dataset+"_code", recipients, false));
			sb.append(" ) ");
			
			//sb.append(addSQLCondition("recipientcode", recipients, true));
		}
		
		sb.append(" ORDER BY ADAM.key_or");
		
		
		LOGGER.info("getAllRecipientsPriorities query: " + sb);
		
		return sb.toString();
	}
	 
	 public static String compareSubSectorToTop10SubSectorsQuery(String oecdtablename, ADAMQueryVO qvo) {
			
			/**
			 * 
			select (select SUM(usd_commitment) AS SUM from adam_crs_e599fc32 where year in ('2010-01-01') AND 
			recipientcode in ('625') AND purposecode IN ('43010')) -  SUM(usd_commitment) AS DIFF,  
			SUM(usd_commitment) AS TOTAL, purposename, purposecode from adam_crs_e599fc32 
			where year in ('2010-01-01') AND recipientcode in ('625') 
			AND usd_commitment > 0 GROUP BY purposename, purposecode order by TOTAL DESC, purposename LIMIT 10;
			**/
			
		
			// if the aggreation is not setted that's the default
			String quantityColumn = "usd_commitment";	
			String aggregationType = "SUM";
			
			if (!qvo.getAggregations().isEmpty()) {
				for (String column : qvo.getAggregations().keySet()) {
					quantityColumn = column;
					aggregationType = qvo.getAggregations().get(column);
				}
			}
			
			LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
			
			Map<String, List<String>> filters = qvo.getWheres();	
			
			// QUERY
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT purposename, purposename, ROUND(CAST( ");	
			sb.append(" (SELECT "+buildAggregationFunction(aggregationType, quantityColumn));
			sb.append(" FROM "+ oecdtablename);
			sb.append(" WHERE ");
			int i = 0;		
			for(String filter : filters.keySet() ){ 
				if ( i == 0 )
					sb.append(addSQLCondition(filter, filters.get(filter), false));
				else
					sb.append(addSQLCondition(filter, filters.get(filter), true));
				i++;
			}
			sb.append(" )");
			sb.append(" - ");
			sb.append(buildAggregationFunction(aggregationType, quantityColumn));
			sb.append(" as numeric), 2) AS DIFFERENCE, ");
			sb.append(buildAggregationFunction(aggregationType, quantityColumn) +" AS TOTAL ");
			sb.append(" FROM "+ oecdtablename);
			sb.append(" WHERE ");
			int j = 0;		
			for(String filter : filters.keySet() ){
				if(!filter.equalsIgnoreCase("purposecode")) { //skip purposecode filter
					if ( j == 0 )
						sb.append(addSQLCondition(filter, filters.get(filter), false));
					else
						sb.append(addSQLCondition(filter, filters.get(filter), true));
				}
				j++;
			}
			sb.append(" AND " + quantityColumn +" > 0 ");
			sb.append(" GROUP BY purposename, purposecode ");
			sb.append(" ORDER BY TOTAL DESC, purposename ");
			sb.append(" LIMIT 10 ");
			
			
			LOGGER.info("%%% compareSubSectorToTop10SubSectorsQuery %%% "+ sb.toString());
			
			return sb.toString();
		}
	 
	 
	 public static String compareSubSectorToTop10AgricultureRelatedSectorsQuery(String oecdtablename, String conversiontablename, ADAMQueryVO qvo) {
			
			/**
			 * 
			select (select SUM(usd_commitment) AS SUM from adam_crs_e599fc32 where year in ('2010-01-01') AND 
			recipientcode in ('625') AND purposecode IN ('43010')) -  SUM(usd_commitment) AS DIFF,  
			SUM(usd_commitment) AS TOTAL, purposename, purposecode from adam_crs_e599fc32 
			where year in ('2010-01-01') AND recipientcode in ('625') 
			AND usd_commitment > 0 GROUP BY purposename, purposecode order by TOTAL DESC, purposename LIMIT 10;
			**/
			
		
			// if the aggreation is not setted that's the default
			String quantityColumn = "usd_commitment";	
			String aggregationType = "SUM";
			
			if (!qvo.getAggregations().isEmpty()) {
				for (String column : qvo.getAggregations().keySet()) {
					quantityColumn = column;
					aggregationType = qvo.getAggregations().get(column);
				}
			}
			
			LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
			
			Map<String, List<String>> filters = qvo.getWheres();	
			
			// QUERY
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT purposename, purposename, ROUND(CAST( ");	
			sb.append(" (SELECT "+buildAggregationFunction(aggregationType, quantityColumn));
			sb.append(" FROM "+ oecdtablename);
			sb.append(" WHERE ");
			int i = 0;		
			for(String filter : filters.keySet() ){ 
				if ( i == 0 )
					sb.append(addSQLCondition(filter, filters.get(filter), false));
				else
					sb.append(addSQLCondition(filter, filters.get(filter), true));
				i++;
			}
			sb.append(" )");
			sb.append(" - ");
			sb.append(buildAggregationFunction(aggregationType, quantityColumn));
			sb.append(" as numeric), 2) AS DIFFERENCE, ");
			sb.append(buildAggregationFunction(aggregationType, quantityColumn) +" AS TOTAL ");
			sb.append(" FROM "+ oecdtablename);
			sb.append(" WHERE ");
			int j = 0;		
			for(String filter : filters.keySet() ){
				if(!filter.equalsIgnoreCase("purposecode") && !filter.equalsIgnoreCase("dac_sector")) { //skip purposecode, sector filter
					if ( j == 0 )
						sb.append(addSQLCondition(filter, filters.get(filter), false));
					else
						sb.append(addSQLCondition(filter, filters.get(filter), true));
					
					j++;
				}
				
			}
			sb.append(" AND purposecode IN (SELECT DISTINCT(dac) FROM "+conversiontablename+")");
			
			sb.append(" AND " + quantityColumn +" > 0 ");
			sb.append(" GROUP BY purposename, purposecode ");
			sb.append(" ORDER BY TOTAL DESC, purposename ");
			sb.append(" LIMIT 10 ");
			
			
			LOGGER.info("%%% compareSubSectorToTop10SubSectorsQuery %%% "+ sb.toString());
			
			return sb.toString();
		}
	 
	 public static String sumQuery(String oecdtablename, ADAMQueryVO qvo) {
			
			// if the aggreation is not setted that's the default
			String quantityColumn = "usd_commitment";	
			String aggregationType = "SUM";
			
			if (!qvo.getAggregations().isEmpty()) {
				for (String column : qvo.getAggregations().keySet()) {
					quantityColumn = column;
					aggregationType = qvo.getAggregations().get(column);
				}
			}
			
			LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;
			
			Map<String, List<String>> filters = qvo.getWheres();	
			
			// QUERY
			StringBuilder sb = new StringBuilder();
			//sb.append(" SELECT "+buildAggregationFunction(aggregationType, quantityColumn, "SUM"));
			sb.append(" SELECT "+buildAggregationFunctionWithThousandSeparator(aggregationType, quantityColumn, "SUM"));
			
			
			sb.append(" FROM "+ oecdtablename);
			sb.append(" WHERE ");
			int i = 0;		
			for(String filter : filters.keySet() ){ 
				if ( i == 0 )
					sb.append(addSQLCondition(filter, filters.get(filter), false));
				else
					sb.append(addSQLCondition(filter, filters.get(filter), true));
				i++;
			}
			
			
			LOGGER.info("%%% sumQuery %%% "+ sb.toString());
			
			return sb.toString();
		}
	 
	 		
	 private static String buildAggregationFunctionWithThousandSeparator(String aggregationType, String quantityColumn, String alias) {
		 StringBuffer value = new StringBuffer();
		 
		 value.append(" to_char(ROUND(");
		 
		 	value.append(" "+ aggregationType + "( " );
		 		value.append(" CAST( " +quantityColumn + " as numeric )");
		 	value.append(" ) ");
		 
		 value.append(",2), 'FM999G999G990D00') AS " + alias + " " );
		 
		 return value.toString();
	 }
	 
	 private static String buildAggregationFunction(String aggregationType, String quantityColumn, String alias) {
		 StringBuffer value = new StringBuffer();
		 
		 value.append(" ROUND(");
		 
		 	value.append(" "+ aggregationType + "( " );
		 		value.append(" CAST( " +quantityColumn + " as numeric )");
		 	value.append(" ) ");
		 
		 value.append(",2) AS " + alias + " " );
		 
		 return value.toString();
	 }
	
	 
	 private static String buildAggregationFunction(String aggregationType, String quantityColumn) {
		 StringBuffer value = new StringBuffer();
		 	 
		 	value.append(" "+ aggregationType + "( " );
		 		value.append(" CAST( " +quantityColumn + " as numeric )");
		 	value.append(" ) ");
		 
		 return value.toString();
	 }
}