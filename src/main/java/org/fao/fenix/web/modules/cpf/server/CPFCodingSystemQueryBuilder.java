package org.fao.fenix.web.modules.cpf.server;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.cpf.common.vo.CPFQueryVO;


public class CPFCodingSystemQueryBuilder {

	private final static String master_table_name = "cpf_main";
    private final static String priorities_table_name = "cpf_priorities";
    private final static String outcomes_table_name = "cpf_outcomes";
    private final static String outputs_table_name = "cpf_outputs";
    private final static String indicators_table_name = "cpf_indicators";
    private final static String user_reference_table_name = "cpf_user_reference";

	private final static Logger LOGGER = Logger.getLogger(CPFQueryBuilder.class);


	  public static String getAllCPFNamesQuery(CPFQueryVO qvo) {

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT DISTINCT(cpfname) || '_cpfname' AS code, cpfname AS label");
			sql.append("FROM "+master_table_name+" " );
			sql.append("ORDER BY cpfname " );

			LOGGER.info("getAllCPFNamesQuery sql: " + sql);
			return sql.toString();
	}

	  public static String getAllCPFCodesQuery(CPFQueryVO qvo) {

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT DISTINCT(cpfcode) || '_cpfcode' AS code, cpfcode AS label");
			sql.append("FROM "+master_table_name+" " );
			sql.append("ORDER BY cpfcode " );

			LOGGER.info("getAllCPFCodesQuery sql: " + sql);
			return sql.toString();
	}

	 public static String getAllCountriesQuery(CPFQueryVO qvo) {

				StringBuffer sql = new StringBuffer();

				sql.append("SELECT DISTINCT(country) || '_country' AS code, c.label");
				sql.append("FROM "+master_table_name+", code AS c, codingsystem AS cs, codingsystem_code as CSC " );
				sql.append("WHERE country=c.code AND cs.code = 'ADAM_RECIPIENTS' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
				sql.append("ORDER BY c.label " );

				LOGGER.info("getAllCountriesQuery sql: " + sql);
				return sql.toString();
	}

	 public static String getAllFAOFrameworkQuery(CPFQueryVO qvo) {

				StringBuffer sql = new StringBuffer();

				sql.append("SELECT DISTINCT(or) || '_or' AS code, or || ' - ' || c.label as label");
				sql.append("FROM "+outcomes_table_name+" " );
				sql.append("WHERE or=c.code AND cs.code = 'FAO_SO' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ") ;
				sql.append("ORDER BY or " );

				LOGGER.info("getAllFAOFrameworkQuery sql: " + sql);
				return sql.toString();
	}
	 
	 public static String getAllDACClassificationQuery(CPFQueryVO qvo) {

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT DISTINCT(dac) || '_dac' AS code, c.label ");
			sql.append("FROM "+outcomes_table_name+", code AS c, codingsystem AS cs, codingsystem_code as CSC ");
			sql.append("WHERE dac=c.code AND cs.code = 'ADAM_PURPOSES' AND cs.resourceid = csc.codingsystem_resourceid AND c.id = csc.codes_id AND c.langcode = 'EN' ");

			LOGGER.info("getAllDACClassificationQuery sql: " + sql);
			return sql.toString();
}


}