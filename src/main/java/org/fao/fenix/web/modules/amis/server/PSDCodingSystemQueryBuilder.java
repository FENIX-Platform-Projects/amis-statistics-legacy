package org.fao.fenix.web.modules.amis.server;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;

public class PSDCodingSystemQueryBuilder {

	private final static Logger LOGGER = Logger.getLogger(PSDCodingSystemQueryBuilder.class);



	 public static String getPSDElementsQuery(AMISQueryVO qvo, String tableName) {

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT elementcode, elementname"+AMISConstants.defaultLanguage.toLowerCase()+" ");
			sql.append("FROM "+tableName+" " );
			sql.append("WHERE ");
			sql.append("elementcode is not null ");
			sql.append("ORDER BY elementname"+AMISConstants.defaultLanguage.toLowerCase());

			LOGGER.info("getPSDElementsQuery sql: " + sql);
			return sql.toString();
		}

}
