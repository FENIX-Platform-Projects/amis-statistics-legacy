package org.fao.fenix.web.modules.dataviewer.server.faostat.codingsystem;

import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.server.DWFAOSTATQueryBuilder;

public class FAOSTATCodingSystemQueryBuilder {
	
	/** TODO: Handle External - Trade - Internal countries on AreaGroupArea table **/
	
	private final static Logger LOGGER = Logger.getLogger(FAOSTATCodingSystemQueryBuilder.class);

	public static String getGroups(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT GroupCode, GroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM Domain ");
//		sql.append(" WHERE GroupCode <> DomainCode ");
		sql.append(" GROUP BY GroupCode, GroupName" + qvo.getLanguage() + " ");
		
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getDomains(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DomainCode, DomainName" + qvo.getLanguage() + " ");
		sql.append(" FROM Domain " );
		sql.append(" WHERE  " );
		sql.append(" DomainCode = '" + DWFAOSTATQueryBuilder.getDomain(qvo) + "' ");
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAllGroupsAndAssociatedDomains(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT GroupCode, GroupName" + qvo.getLanguage() + ", DomainCode, DomainName" + qvo.getLanguage() + ", CONVERT(VARCHAR(10), DateUpdate, 103) AS [DD/MM/YYYY] ");
		sql.append(" FROM Domain " );
		sql.append(" WHERE  ");
		sql.append(" DomainName" + qvo.getLanguage() + " <> 'null' ");
		
//		sql.append(" WHERE GroupCode <> DomainCode ");
//		sql.append(" AND DomainName" + qvo.getLanguage() + " <> 'null' ");
//		sql.append(" ORDER BY  GroupName" + qvo.getLanguage() + " , DomainName" + qvo.getLanguage() + ", DateUpdate ");
		sql.append(" ORDER BY  Ord ");
	
		LOGGER.info("Groups and Domains: " + sql);
		return sql.toString();
	}
	
	public static String getAllGroupsAndAssociatedDomainsBulkDownload(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT D.GroupCode, D.GroupName" + qvo.getLanguage() + ", D.DomainCode, D.DomainName" + qvo.getLanguage());
		sql.append(" FROM Domain D, BulkDownloads B" );
		sql.append(" WHERE  ");
		sql.append(" D.DomainName" + qvo.getLanguage() + " <> 'null' ");
		sql.append(" AND D.DomainCode = B.Domain ");
		sql.append(" GROUP BY  D.GroupCode, D.GroupName" + qvo.getLanguage() + ", D.DomainCode, D.DomainName" + qvo.getLanguage());
		sql.append(" ORDER BY  D.GroupName" + qvo.getLanguage() + " , D.DomainName" + qvo.getLanguage());
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getSubDomains(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DomainCode, DomainName" + qvo.getLanguage() + " ");
		sql.append(" FROM Domain " );
		sql.append(" WHERE GroupCode = " + DWFAOSTATQueryBuilder.getDomain(qvo) + " ");
		sql.append(" AND DomainName" + qvo.getLanguage() + " <> 'null' ");
//		sql.append(" AND GroupCode <> DomainCode ");
		sql.append(" ORDER BY  DomainName" + qvo.getLanguage());

//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getDomainsAllFromFenix(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DISTINCT(domaincode), domainname" + qvo.getLanguage().toLowerCase() + ",");
		sql.append(" groupcode, groupname"+ qvo.getLanguage().toLowerCase());
		sql.append(" FROM "+qvo.getFenixDBTableName());
		sql.append(" WHERE domaincode <> groupcode ");
		sql.append(" AND groupname" + qvo.getLanguage().toLowerCase() + " <> 'null' ");
		sql.append(" ORDER BY domainname"+qvo.getLanguage().toLowerCase()+", groupname" + qvo.getLanguage().toLowerCase());
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAllMetadataGroupsAndAssociatedDomains(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DISTINCT(GroupCode), GroupName" + qvo.getLanguage() + ", DomainCode, DomainName" + qvo.getLanguage());
		sql.append(" FROM Metadata_Item " );
		sql.append(" WHERE  ");
		sql.append(" DomainName" + qvo.getLanguage() + " <> 'null' ");
		sql.append(" ORDER BY  GroupName" + qvo.getLanguage() + " , DomainName" + qvo.getLanguage());
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAllMetadataMethodologies(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DISTINCT(M.MethodologyCode), M.MethodologyTitle" + qvo.getLanguage() );
		sql.append(" FROM Metadata_Methodology M " );
		sql.append(" ORDER BY  M.MethodologyTitle" + qvo.getLanguage());
		
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getCountries(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT A.AreaCode, A.AreaName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainAreaGroupArea A " );
			
		Boolean and = false;
		if ( !qvo.getDomains().isEmpty() && qvo.getDomains() != null ) {
			sql.append(" WHERE ");
			sql.append(" A.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");	
			and = true;
		}
		
		if ( !qvo.getAggregationsFilter().isEmpty() ) {
			if ( !and ) {
				sql.append(" WHERE ");
			}
			else {
				sql.append(" AND ");
			}
			sql.append(" A.AreaGroupCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getAggregationsFilter()) + ") ");
		}
		
		sql.append(" GROUP BY A.AreaCode, A.AreaName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY A.AreaName" + qvo.getLanguage());
		
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getCountriesWithContinents(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT A.AreaCode, A.AreaName" + qvo.getLanguage() + ", A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainAreaGroupArea A " );
		sql.append(" WHERE  " );
	
		
		sql.append(" A.AreaGroupCode in ('5100', '5500', '5300', '5200', '5400')");
		sql.append(" AND A.AreaGroupCode not like '58__'");
		sql.append(" AND A.AreaGroupCode not in ('5000')");
		
		if ( !qvo.getDomains().isEmpty() && qvo.getDomains() != null ) {
			sql.append(" AND A.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		}
		
		// this is to get the countries of a specific subregion
		if ( !qvo.getAggregationsFilter().isEmpty() ) {
			sql.append(" AND A.AreaGroupCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getAggregationsFilter()) + ") ");
		}
		
		sql.append(" GROUP BY A.AreaGroupCode, A.AreaCode, A.AreaName" + qvo.getLanguage() + ", A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY A.AreaGroupName" + qvo.getLanguage() + ", A.AreaName" + qvo.getLanguage());
		
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAreas(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainAreaGroupArea A " );
		sql.append(" WHERE  " );
		
		// TODO: HARDCODED?
		sql.append(" A.AreaGroupCode not like '58__'");
		
		if ( !qvo.getDomains().isEmpty() && qvo.getDomains() != null ) {
			sql.append(" AND A.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");	
		}		
		
		sql.append(" GROUP BY A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY A.AreaGroupCode ASC");
		
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAreasFAO(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainAreaGroupArea A " );
		sql.append(" WHERE ");
	
		// TODO: HARDCODED?
		sql.append("A.AreaGroupCode like '58__'");
			
		if ( !qvo.getDomains().isEmpty() && qvo.getDomains() != null ) {
			sql.append(" AND A.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");	
		}
			
		sql.append(" GROUP BY A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY A.AreaGroupName" + qvo.getLanguage() + " ASC");
		return sql.toString();
	}
	
	public static String getYears(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT StartYear, EndYear ");
		sql.append(" FROM Domain " );
		
		if ( qvo.getDomains() != null && !qvo.getDomains().isEmpty() ) {
			sql.append(" WHERE  " );
			sql.append(" DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
			sql.append(" OR GroupCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		}
		
		// TODO: ask dinamically
		sql.append(" ORDER BY StartYear Asc, EndYear Desc ");
//		sql.append(" ORDER BY StartYear Asc, EndYear Asc ");
//		sql.append(" ORDER BY StartYear Desc, EndYear Asc ");
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	
	public static String getItemsCompareData(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		
		if ( qvo.getDomains() != null && !qvo.getDomains().isEmpty() ) { 
			sql.append(" SELECT DI.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
			sql.append(" FROM DomainItem DI, Item I " );
			sql.append(" WHERE  " );
			
			sql.append(" DI.ItemCode = I.ItemCode " );
			

		
			sql.append(" AND DI.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
			
			sql.append(" ORDER BY I.ItemName" + qvo.getLanguage() + " ASC");
			
		}
		else {
			// get all the Codes and Items
			sql.append(" SELECT I.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
			sql.append(" FROM Item I " );
			/** TODO: ItemLevel = 5 for the demo  **/
			
			sql.append(" WHERE ItemLevel = '5'");

			
			sql.append(" GROUP BY I.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
			sql.append(" ORDER BY I.ItemName" + qvo.getLanguage() + " ASC");
			
		}
		
		

//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getDownloadItemsOnly(DWFAOSTATQueryVO qvo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DI.ItemCode, I.ItemName").append(qvo.getLanguage()).append(" ");
		sb.append("FROM DomainItem DI, Item I ");
		sb.append("WHERE DI.ItemCode = I.ItemCode ");
		sb.append("AND DI.DomainCode = ").append(DWFAOSTATQueryBuilder.getDomain(qvo)).append(" ");
		sb.append("AND I.ItemLevel IN ('5','15') ");
		sb.append("ORDER BY DI.Ord, I.ItemLevel, I.ItemName").append(qvo.getLanguage()).append(" ");
	
		LOGGER.info("sql: " + sb.toString());
//		SELECT DI.ItemCode, I.ItemNameE 
//		FROM DomainItem DI, Item I
//		WHERE DI.ItemCode = I.ItemCode AND DI.DomainCode = 'GY'
//		AND I.ItemLevel IN ('5','15') 
//		ORDER BY DI.Ord, I.ItemLevel, I.ItemNameE
		return sb.toString();
	}
	
	public static String getDownloadAggregatesOnly(DWFAOSTATQueryVO qvo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DI.ItemCode, I.ItemName").append(qvo.getLanguage()).append(" ");
		sb.append("FROM DomainItem DI, Item I ");
		sb.append("WHERE DI.ItemCode = I.ItemCode ");
		sb.append("AND DI.DomainCode = ").append(DWFAOSTATQueryBuilder.getDomain(qvo)).append(" ");
		sb.append("AND I.ItemLevel = '10' ");
		sb.append("ORDER BY DI.Ord, I.ItemLevel, I.ItemName").append(qvo.getLanguage()).append(" ");
		return sb.toString();
	}
	
	public static String getDownloadItemsAndAggregates(DWFAOSTATQueryVO qvo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT DI.ItemCode, I.ItemName").append(qvo.getLanguage()).append(" ");
		sb.append("FROM DomainItem DI, Item I ");
		sb.append("WHERE DI.ItemCode = I.ItemCode ");
		sb.append("AND DI.DomainCode = ").append(DWFAOSTATQueryBuilder.getDomain(qvo)).append(" ");
		sb.append("ORDER BY DI.Ord, I.ItemLevel, I.ItemName").append(qvo.getLanguage()).append(" ");
		return sb.toString();
	}
	
	public static String getItems(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		
		if ( qvo.getDomains() != null && !qvo.getDomains().isEmpty() ) { 
			sql.append(" SELECT DI.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
			sql.append(" FROM DomainItem DI, Item I " );
			sql.append(" WHERE  " );
			
			sql.append(" DI.ItemCode = I.ItemCode " );
			
			if ( !qvo.getItems().isEmpty() ) {
				sql.append(" AND I.ItemCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getItems()) + ") ");
			}
		
			sql.append(" AND DI.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
			
			sql.append(" ORDER BY DI.Ord, I.ItemName" + qvo.getLanguage() + " ASC");
			
		}
		else {
			
			/** TODO: this is used just in the SEARCH,  
			 *  	  please remove the ImteLevel = '5' hardcoded and pass it as parameter
			 */
			// get all the Codes and Items
			sql.append(" SELECT I.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
			sql.append(" FROM Item I " );
			
			/** TODO: this should be called in the search, ItemLevel = 5 for the demo  **/
			sql.append(" WHERE ItemLevel = '5'");
			
			if ( !qvo.getItems().isEmpty() ) {
				sql.append(" AND I.ItemCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getItems()) + ") ");
			}
			
			sql.append(" GROUP BY I.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
			sql.append(" ORDER BY I.ItemName" + qvo.getLanguage() + " ASC");
		}

	
		return sql.toString();
	}
		
	public static String getItemsBYDomainItemElement(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT I.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItemElementUnit DIEU, Item I " );
		sql.append(" WHERE  ");
		sql.append(" DIEU.ItemCode = I.ItemCode " );
		sql.append(" AND DIEU.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		
		// nested query to get only the Items with the same element
		sql.append(" AND DIEU.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		
		if ( !qvo.getElements().isEmpty() ) { 
			
		}
		
		if ( !qvo.getItems().isEmpty() ) { 
			
		}

		sql.append(" ORDER BY I.ItemName" + qvo.getLanguage() + " ASC");
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getItemsDisaggregations(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DIGI.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItemGroupItem DIGI, Item I " );
		sql.append(" WHERE  " );
		sql.append(" DIGI.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DIGI.ItemCode = I.ItemCode " );
		
		// this is to get the items of a specific item aggregation
		if ( !qvo.getAggregationsFilter().isEmpty() ) {
			sql.append(" AND DIGI.ItemGroupCode IN (" + DWFAOSTATQueryBuilder.getFilters(qvo.getAggregationsFilter()) + ") ");
		}
				
		sql.append(" ORDER BY I.ItemName" + qvo.getLanguage() + " ASC");
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getItemsAggregations(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
	
		sql.append(" SELECT DIC.ItemGroupCode,  DIC.ItemGroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItemGroupItem DIC " );
		sql.append(" WHERE  " );
		sql.append(" DIC.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" GROUP BY DIC.ItemGroupCode, DIC.ItemGroupName" + qvo.getLanguage());
		sql.append(" ORDER BY DIC.ItemGroupName" + qvo.getLanguage() + " ASC");
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	
	public static String getElements(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		
		if ( qvo.getDomains() != null && !qvo.getDomains().isEmpty() ) { 
			sql.append(" SELECT DE.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
			sql.append(" FROM DomainElement DE, Element E " );
			sql.append(" WHERE  " );
			sql.append(" DE.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
			sql.append(" AND DE.ElementCode = E.ElementCode " );
			sql.append(" GROUP BY DE.Ord, DE.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
			sql.append(" ORDER BY DE.Ord, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		}
		else {
			// get all elements
			sql.append(" SELECT E.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
			sql.append(" FROM Element E " );
			sql.append(" GROUP BY E.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
			sql.append(" ORDER BY E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		}
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	/** TODO: this query should be modofied, ask amand to connect DomainElement with the ElementsList **/	
	public static String getElementsList(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT s.ElementListCode, s.ElementListName" + qvo.getLanguage());
		sql.append(" FROM (");
			sql.append(" SELECT E.ElementListCode, E.ElementListName" + qvo.getLanguage() + ",  MIN(DE.Ord) AS Ord");
//			sql.append(" FROM DomainItemElementUnit DIEU, Element E, DomainElement DE " );
			sql.append(" FROM DomainItemElementUnit DIEU " );
			sql.append(" INNER JOIN Element E ON DIEU.ElementCode = E.ElementCode ");
			sql.append(" INNER JOIN DomainElement DE ON DIEU.DomainCode = DE.DomainCode ");
			sql.append(" AND DIEU.ElementCode = DE.ElementCode ");
			if ( !qvo.getDomains().isEmpty()) {
				sql.append(" WHERE " );
				sql.append(" DIEU.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
				sql.append(" AND DIEU.ElementListCode = E.ElementListCode  AND E.ElementCode = DE.ElementCode " );
			}
			sql.append(" GROUP BY E.ElementListCode, E.ElementListName" + qvo.getLanguage());
			
		sql.append( " )");
		sql.append( " s ORDER BY Ord, ElementListNameE");

	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	public static String getElementsBYDomainItemElement(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
				
		sql.append(" SELECT ");
		if(qvo.getItems().size() == 0)
			sql.append(" DISTINCT ");
		sql.append(" DIEU.ElementCode,(E.ElementName"+qvo.getLanguage()+" +' (' + E.UnitName"+ qvo.getLanguage()+"  +')') AS ElementLabel");
		sql.append(" FROM DomainItemElementUnit DIEU, Element E");
		sql.append(" WHERE  DIEU.ElementCode = E.ElementCode");
		sql.append(" AND DIEU.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		if(qvo.getItems().size() > 0)
			sql.append(" AND DIEU.ItemCode IN (" + DWFAOSTATQueryBuilder.getItems(qvo) + ") ");
		
		sql.append(" ORDER BY ElementLabel ASC");
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
	public static String getItemsByElement(DWFAOSTATQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DIEU.ItemCode,I.ItemName"+qvo.getLanguage());
		sql.append(" FROM DomainItemElementUnit DIEU, Item I");
		sql.append(" WHERE  DIEU.ItemCode = I.ItemCode");
		sql.append(" AND DIEU.DomainCode IN (" + DWFAOSTATQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DIEU.ElementCode IN (" + DWFAOSTATQueryBuilder.getElements(qvo) + ") ");
		sql.append(" ORDER BY I.ItemName" + qvo.getLanguage() + " ASC");
	
//		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getFlags(String language, Map<String, String> flags ) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT F.Flag,F.FlagDescription"+language);
		sql.append(" FROM Flag F");
		sql.append(" WHERE  ");
		sql.append(" F.Flag IN (" + DWFAOSTATQueryBuilder.getFilters(flags) + ") ");
		sql.append(" ORDER BY F.Flag,F.FlagDescription"+language + " ASC");
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	

	
	
	
}
