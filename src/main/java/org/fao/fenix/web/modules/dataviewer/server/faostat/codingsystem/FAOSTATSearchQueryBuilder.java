package org.fao.fenix.web.modules.dataviewer.server.faostat.codingsystem;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.server.DWFAOSTATQueryBuilder;

public class FAOSTATSearchQueryBuilder {
	
	private final static Logger LOGGER = Logger.getLogger(FAOSTATSearchQueryBuilder.class);

	
	
	public static String getItemsByLabel(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT I.ItemCode, I.ItemName"+qvo.getLanguage() );
		sql.append(" FROM Item I ");
		sql.append(" WHERE ");
		
		int i = 0;
		Boolean added = false;
		for(String code : qvo.getItems().keySet()) {
			sql.append(" I.ItemName"+qvo.getLanguage()+" like '%" + qvo.getItems().get(code) + "%' ");
			if ( i !=  qvo.getItems().size() -1)
				sql.append(" OR "); 
			i++;
			added = true;
		}
		
		for(String code : qvo.getItems().keySet()) {
			try {
				Integer.valueOf(code);
				sql.append(" OR I.ItemCode = '" + code + "' ");			
				added = true;
			}
			catch (Exception e) {
			}
		}
	
		if ( !qvo.getRangeCodes().isEmpty() ) {
			if ( added ) {
				sql.append(" OR "); 
			}
			for(String from : qvo.getRangeCodes().keySet()) {
				sql.append(" ( I.ItemCode >= '" + from + "' AND I.ItemCode <= '" + qvo.getRangeCodes().get(from) + "' ) ");		
			}
		}
				

//		sql.append(" AND I.ItemLevel = 5 ");
		
		sql.append(" GROUP BY I.ItemCode, I.ItemName"+qvo.getLanguage() );


//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	public static String getElementsByLabel(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT E.ElementListCode, E.ElementListName"+qvo.getLanguage() );
		sql.append(" FROM Element E ");
		sql.append(" WHERE ");
		
		int i = 0;
		Boolean added = false;
		if ( !qvo.getElements().isEmpty() ) {
			added = true;
			sql.append(" ( "); 
			for(String code : qvo.getElements().keySet()) {
				sql.append(" E.ElementListName"+qvo.getLanguage()+" like '%" + qvo.getElements().get(code) + "%' ");
				if ( i !=  qvo.getElements().size() -1)
					sql.append(" OR "); 
				i++;
				
			}
			sql.append(" ) "); 
			
			sql.append(" AND "); 
			
			i = 0;
			sql.append(" ( "); 
			for(String code : qvo.getElements().keySet()) {
				sql.append(" E.ElementName"+qvo.getLanguage()+" like '%" + qvo.getElements().get(code) + "%' ");
				if ( i !=  qvo.getElements().size() -1)
					sql.append(" OR "); 
				i++;
			}
			sql.append(" ) "); 
			
			for(String code : qvo.getElements().keySet()) {
				try {
					Integer.valueOf(code);
					sql.append(" OR E.ElementCode = '" + code + "' ");				}
				catch (Exception e) {
				}
			}
			
			for(String code : qvo.getElements().keySet()) {
				try {
					Integer.valueOf(code);
					sql.append(" OR E.ElementListCode = '" + code + "' ");				}
				catch (Exception e) {
				}
			}
		}
		
		// getting the code range
		if ( !qvo.getRangeCodes().isEmpty() ) {
			if ( added ) {
				sql.append(" OR "); 
			}
			for(String from : qvo.getRangeCodes().keySet()) {
				sql.append(" ( E.ElementCode >= '" + from + "' AND E.ElementCode <= '" + qvo.getRangeCodes().get(from) + "' ) ");		
			}
			
			sql.append(" OR "); 
			
			for(String from : qvo.getRangeCodes().keySet()) {
				sql.append(" ( E.ElementListCode >= '" + from + "' AND E.ElementListCode <= '" + qvo.getRangeCodes().get(from) + "' ) ");		
			}
		}

		sql.append(" GROUP BY E.ElementListCode, E.ElementListName"+qvo.getLanguage() );
		sql.append(" ORDER BY E.ElementListName"+qvo.getLanguage() );

//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAllGroupsAndAssociatedDomainsByItems(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT D.GroupCode, D.GroupName" + qvo.getLanguage()+", D.DomainCode, D.DomainName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItem DI, Domain D ");
		sql.append(" WHERE DI.ItemCode IN (" + DWFAOSTATQueryBuilder.getItems(qvo) + ") ");
		sql.append(" AND DI.DomainCode = D.DomainCode ");
		sql.append(" AND DI.GroupCode = D.GroupCode ");
		sql.append(" AND DomainName" + qvo.getLanguage() + " <> 'null' ");
//		sql.append(" AND D.GroupName <> D.DomainName" + qvo.getLanguage() + " ");
		
		/** TODO: this is unnecessary, it's used for the inconsistency in the database... **/
		sql.append(" GROUP BY D.GroupCode, D.GroupName" + qvo.getLanguage()+", D.DomainCode, D.DomainName" + qvo.getLanguage() + " ");
	

//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAllGroupsAndAssociatedDomainsByElementsList(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT D.GroupCode, D.GroupName" + qvo.getLanguage()+", D.DomainCode, D.DomainName" + qvo.getLanguage() + " ");
		sql.append(" FROM Element E, Domain D, DomainElement DE ");
		sql.append(" WHERE " );
		sql.append(" E.ElementListCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getElementsList()) + ") ");
		sql.append(" AND E.ElementCode = DE.ElementCode ");
		sql.append(" AND D.DomainCode = DE.DomainCode ");
		sql.append(" AND D.DomainName" + qvo.getLanguage() + " <> 'null' ");
//		sql.append(" AND D.GroupName <> D.DomainName" + qvo.getLanguage() + " ");
		sql.append(" GROUP BY D.GroupCode, D.GroupName" + qvo.getLanguage()+", D.DomainCode, D.DomainName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY D.GroupName" + qvo.getLanguage()+", D.DomainName" + qvo.getLanguage() + " ");

//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	public static String getElementsByDomainAndItem(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT E.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItemElementUnit DIEU, Element E " );
		sql.append(" WHERE  " );
		sql.append(" DIEU.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DIEU.ItemCode IN (" + DWFAOSTATQueryBuilder.getItems(qvo) + ") ");
		sql.append(" AND DIEU.ElementCode = E.ElementCode " );
		sql.append(" GROUP BY E.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
	

//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getResultSearchItems(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT I.ItemCode, I.ItemName" + qvo.getLanguage()+", D.GroupCode, D.GroupName"+qvo.getLanguage()+", D.DomainCode, D.DomainName" + qvo.getLanguage()+", E.ElementListCode, E.ElementListName" + qvo.getLanguage()+", E.ElementCode, E.ElementName" + qvo.getLanguage()+", E.UnitName" + qvo.getLanguage()+"  "); 
		sql.append(" FROM DomainItem DI, Domain D, DomainItemElementUnit DIEU, Element E, Item I  ");   
		sql.append(" WHERE "); 

		sql.append(" I.ItemCode IN (" + DWFAOSTATQueryBuilder.getItems(qvo) + ") ");

		sql.append(" AND I.ItemCode = DI.ItemCode ");
		sql.append(" AND I.ItemCode = DIEU.ItemCode ");
		sql.append(" AND DI.DomainCode = D.DomainCode "); 
		sql.append(" AND DI.GroupCode = D.GroupCode "); 
		sql.append(" AND DomainName"+qvo.getLanguage()+" <> 'null' "); 
//		sql.append(" AND D.GroupName <> D.DomainName"+qvo.getLanguage()+" ");  
		sql.append(" AND DIEU.ElementCode = E.ElementCode "); 
		sql.append(" AND DIEU.DomainCode = D.DomainCode "); 
		sql.append(" GROUP BY I.ItemCode, I.ItemName"+qvo.getLanguage()+", D.GroupCode, D.GroupName"+qvo.getLanguage()+", D.DomainCode, D.DomainName"+qvo.getLanguage()+", E.ElementListCode, E.ElementListName"+qvo.getLanguage()+", E.ElementCode, E.ElementName"+qvo.getLanguage()+", E.UnitName"+qvo.getLanguage()+" ");   
		sql.append(" ORDER BY I.ItemName"+qvo.getLanguage()+", I.ItemCode "); 
		
		
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	public static String getResultSearchElements(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT E.ElementListCode, E.ElementListName"+qvo.getLanguage()+", D.GroupCode, D.GroupName"+qvo.getLanguage()+", D.DomainCode, D.DomainName"+qvo.getLanguage()+",  E.ElementCode, E.ElementName"+qvo.getLanguage()+", E.UnitName"+qvo.getLanguage()+" "); 
		sql.append(" FROM DomainElement DE, Domain D, Element E ");   
		sql.append(" WHERE "); 

		Boolean added = false;
		if ( !qvo.getElements().isEmpty() )  {
			sql.append(" E.ElementCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getElements()) + ") ");
			added = true;
		}
		if ( !qvo.getElementsList().isEmpty() )  {
			if ( added )  
				sql.append(" AND " );
			sql.append(" E.ElementListCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getElementsList()) + ") ");
		}
		
		sql.append(" AND DE.GroupCode = D.GroupCode ");
		sql.append(" AND DomainName"+qvo.getLanguage()+" <> 'null' ");
//		sql.append(" AND D.GroupName <> D.DomainName"+qvo.getLanguage()+" ");
		sql.append(" AND DE.ElementCode = E.ElementCode ");
		sql.append(" AND DE.DomainCode = D.DomainCode ");
		sql.append(" GROUP BY E.ElementListCode, E.ElementListName"+qvo.getLanguage()+", D.GroupCode, D.GroupName"+qvo.getLanguage()+", D.DomainCode, D.DomainName"+qvo.getLanguage()+",  E.ElementCode, E.ElementName"+qvo.getLanguage()+", E.UnitName"+qvo.getLanguage()+" "); 
		sql.append(" ORDER BY E.ElementListName"+qvo.getLanguage()+", E.ElementListCode ");


//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	
	
}
