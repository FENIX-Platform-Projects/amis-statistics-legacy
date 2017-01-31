package org.fao.fenix.web.modules.amis.server;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;

public class AMISFaostatCodingSystemQueryBuilder {
	
	private final static Logger LOGGER = Logger.getLogger(AMISFaostatCodingSystemQueryBuilder.class);

	public static String getAllGroupsAndAssociatedDomains(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT GroupCode, GroupName, DomainCode, DomainName" + qvo.getLanguage() + " ");
		sql.append(" FROM Domain " );
		sql.append(" WHERE GroupCode <> DomainCode ");
		sql.append(" AND DomainName" + qvo.getLanguage() + " <> 'null' ");
		sql.append(" ORDER BY  GroupName, DomainName" + qvo.getLanguage());
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getCropGroupsAndAssociatedDomains(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT GroupCode, GroupName, DomainCode, DomainName" + qvo.getLanguage() + " ");
		sql.append(" FROM Domain " );
		sql.append(" WHERE GroupCode in ('Q', 'T', 'P') AND DomainCode in ('QC', 'TP', 'PC')");
		sql.append(" AND DomainName" + qvo.getLanguage() + " <> 'null' ");
		sql.append(" ORDER BY  GroupName, DomainName" + qvo.getLanguage());
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getCountries(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DA.AreaCode, A.AreaName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainArea DA, AreaGroupArea A " );
		sql.append(" WHERE  " );
		sql.append(" DA.DomainCode IN (" + AMISFaostatQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DA.AreaCode = A.AreaCode" );
		
		// TODO dynamic selection of type
		sql.append(" AND A.Type = 'External'");
		
		// this is to get the countries of a specific subregion
		if ( !qvo.getAggregationsFilter().isEmpty() ) {
			sql.append(" AND A.AreaGroupCode IN (" + AMISQueryBuilder.getFilters(qvo.getAggregationsFilter()) + ") ");
		}
		
		sql.append(" GROUP BY DA.AreaCode, A.AreaName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY A.AreaName" + qvo.getLanguage());

		
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAreas(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainArea DA, AreaGroupArea A " );
		sql.append(" WHERE  " );
		sql.append(" DA.DomainCode IN (" + AMISFaostatQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DA.AreaCode = A.AreaCode" );
		
		// TODO: HARDCODED?
		sql.append(" AND A.AreaGroupCode not like '58__'");
		
		// TODO dynamic selection of type
		sql.append(" AND A.Type = 'External'");
		
		sql.append(" GROUP BY A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY A.AreaGroupCode ASC");

		
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAreasFAO(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainArea DA, AreaGroupArea A " );
		sql.append(" WHERE  " );
		sql.append(" DA.DomainCode IN (" + AMISFaostatQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DA.AreaCode = A.AreaCode" );
		
		// TODO: HARDCODED?
		sql.append(" AND A.AreaGroupCode like '58__'");
		
		// TODO dynamic selection of type
		sql.append(" AND A.Type = 'External'");
		
		sql.append(" GROUP BY A.AreaGroupCode, A.AreaGroupName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY A.AreaGroupName" + qvo.getLanguage() + " ASC");

		
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getYears(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT StartYear, EndYear ");
		sql.append(" FROM Domain " );
		sql.append(" WHERE  " );
		sql.append(" DomainCode IN (" + AMISFaostatQueryBuilder.getDomain(qvo) + ") ");
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getItems(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DI.ItemCode, I.ItemName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItem DI, Item I " );
		sql.append(" WHERE  " );
		sql.append(" DI.DomainCode IN (" + AMISFaostatQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DI.ItemCode = I.ItemCode " );
		sql.append(" ORDER BY I.ItemName" + qvo.getLanguage() + " ASC");
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}

	public static String getItemsAggregations(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
	
		sql.append(" SELECT DIC.ItemGroupCode,  DIC.ItemGroupName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItemGroupItem DIC " );
		sql.append(" WHERE  " );
		sql.append(" DIC.DomainCode IN (" + AMISFaostatQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" GROUP BY DIC.ItemGroupCode, DIC.ItemGroupName" + qvo.getLanguage());
		sql.append(" ORDER BY DIC.ItemGroupName" + qvo.getLanguage() + " ASC");
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getElements(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT DE.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainElement DE, Element E " );
		sql.append(" WHERE  " );
		sql.append(" DE.DomainCode IN (" + AMISFaostatQueryBuilder.getDomain(qvo) + ") ");
		sql.append(" AND DE.ElementCode = E.ElementCode " );
		sql.append(" GROUP BY DE.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	public static String getAllElements(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
				
		sql.append(" SELECT DIE.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" FROM DomainItemElement DIE, Element E " );
		sql.append(" WHERE  " );
		
		String whereString = " DIE.DomainCode IN (";
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
		LOGGER.info("Test whereString: " + whereString);
		sql.append(" AND DIE.ElementCode = E.ElementCode " );
		sql.append(" AND DIE.ItemCode IN (" + AMISFaostatQueryBuilder.getItems(qvo) + ") " );
		sql.append(" GROUP BY DIE.ElementCode, E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
		sql.append(" ORDER BY E.ElementName" + qvo.getLanguage() + ", E.UnitName" + qvo.getLanguage() + " ");
	
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
}
