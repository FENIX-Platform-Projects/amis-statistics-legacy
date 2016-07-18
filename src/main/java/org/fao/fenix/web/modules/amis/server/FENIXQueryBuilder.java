package org.fao.fenix.web.modules.amis.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.constants.AMISTableConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;


public class FENIXQueryBuilder extends AMISQueryBuilder {

	private final static Logger LOGGER = Logger.getLogger(FENIXQueryBuilder.class);

    public static String getDatasetsQuery(AMISQueryVO qvo) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT code, tablename from customdataset ");
		sql.append("WHERE code IN (");
		sql.append(getDatasetCodes(qvo));
		sql.append(")");
			
		LOGGER.info("getDatasetsQuery sql: " + sql);
		return sql.toString();
	}

    public static String getDatasetsQuery_singleCode(String code) {

        StringBuffer sql = new StringBuffer();

        sql.append("SELECT code, tablename from customdataset ");
        sql.append("WHERE code = '"+code+"'");
        LOGGER.info("getDatasetsQuery_singleCode sql: " + sql);
        return sql.toString();
    }

    public static String addSelectMaxDate() {
		StringBuffer sql = new StringBuffer();   
	    sql.append(" SELECT MAX(D.Year)").append(" ");
		
		return sql.toString();
	}
	/*public static String addSelectMaxDate() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TOP 1 year").append(" ");

		return sql.toString();
	}*/

	public static String addOrderByMaxDate() {
		StringBuffer sql = new StringBuffer();
		sql.append(" ORDER BY year DESC").append(" ");

		return sql.toString();
	}

	/*private static String getLowerDateLimit(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" year(DATEADD(year, -"+qvo.getTimeSpan()+", '"+qvo.getMaxDateLimit()+"'))").append(" ");

		return sql.toString();
	}*/

	public static String addLabelJoins(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		 sql.append(" AND D.element_code = E.element_code " );
		return sql.toString();
	}
	
	public static String addNoZeroValues(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		 sql.append(" AND D.value <> 0" );
		return sql.toString();
	}
	
	public static String addTimeIntervalSelects(AMISQueryVO qvo){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT "+qvo.getTimeIntervalQuery()).append(" ");

		return sql.toString();
	}

	/*private static String addDateRange(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" (year BETWEEN "+getLowerDateLimit(qvo)+" AND '"+qvo.getMaxDateLimit()+"')").append(" ");

		return sql.toString();
	}*/

	private static String addDateRange(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" (year BETWEEN '"+qvo.getLowerDateLimit()+"' AND '"+qvo.getMaxDateLimit()+"')").append(" ");

		return sql.toString();
	}

	public static String buildSubtractionQuery(AMISQueryVO qvo, List<String> filterValues, AMISTableConstants filterColumn){
		StringBuffer sql = new StringBuffer();
		
		sql.append(" (SELECT SUM(value) ");

		sql.append(" FROM "+qvo.getDatasetTableName());

		LOGGER.info("GET Wheres .." );
		
		sql.append(getWheres(qvo, filterColumn, filterValues));

		sql.append(") ");

		return sql.toString();
	}

	
	public static String buildDivisionQuery(AMISQueryVO qvo, List<String> filterValues, AMISTableConstants filterColumn){
		StringBuffer sql = new StringBuffer();
		
		sql.append(" (SELECT SUM(value) ");

		sql.append(" FROM "+qvo.getDatasetTableName());

		LOGGER.info("GET Wheres .." );
		
		sql.append(getWheres(qvo, filterColumn, filterValues));

		sql.append(") ");

		return sql.toString();
	}


	public static String getMeasurementUnits(AMISQueryVO qvo, String codingSystemCode, AMISDao amisDao) {
		
        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(codingSystemCode));	
			
	    List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
		String tablename = sqlResult.get(0).toString();
		 
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT units ");
		sql.append(" FROM "+tablename );
		sql.append(" WHERE  " );

		// TODO: added boolean
		 if ( !qvo.getElements().isEmpty() ) {
			 sql.append(" element_code IN (" + getElements(qvo) + ") ");
		 }
		 else {
			 LOGGER.info("ELEMENTS EMPTY! ");
			 // return null??
			 return null;
		 }
		 	
		sql.append(" GROUP BY units  ");
		sql.append(" ORDER BY units ");

		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	

	public static String getWheres(AMISQueryVO qvo, AMISTableConstants filterColumn, List<String> filterFields) {
		StringBuffer sql = new StringBuffer();
		sql.append(" WHERE ");

		Boolean addedFirst = false;
		if ( !qvo.getAreas().isEmpty() && !filterColumn.equals(AMISTableConstants.Area)) {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" countrycode IN (" + getAreas(qvo) + ") ");

			addedFirst = true;
		} else {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" countrycode IN (" + getAreas(filterFields) + ") ");

			addedFirst = true;
		}


		if ( !qvo.getItems().isEmpty() && !filterColumn.equals(AMISTableConstants.Item)) {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" product_code IN (" + getItems(qvo) + ") ");

			addedFirst = true;
		} else {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" product_code IN (" + getItems(filterFields) + ") ");

			addedFirst = true;
		}

		if ( !qvo.getElements().isEmpty() && !filterColumn.equals(AMISTableConstants.Element) ) {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" element_code IN (" + getElements(qvo) + ") ");
			addedFirst = true;
		} else {
			sql.append(" element_code IN (" + getElements(filterFields) + ") ");

			addedFirst = true;
		}

		if ( !qvo.getYears().isEmpty()) {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" year IN (" + getYears(qvo) + ") ");
			addedFirst = true;
		}
		
		if ( !qvo.getDatabases().isEmpty()) {
			if ( addedFirst )
				sql.append(" AND " );

			sql.append(" database IN (" + getDatabases(qvo) + ") ");
			addedFirst = true;
		}

		return sql.toString();
	}

	public static String getWheres(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" WHERE ");

		 Boolean addedFirst = false;
		 if ( !qvo.getAreas().isEmpty() ) {
			 sql.append(" D.region_code IN (" + getAreas(qvo) + ") ");

			 addedFirst = true;
		 }

		 if ( !qvo.getItems().isEmpty()) {
			 if ( addedFirst )
				 sql.append(" AND " );

            sql.append(" D.product_code IN (" + getItems(qvo) + ") ");

			 addedFirst = true;
		 }


		 if ( !qvo.getElements().isEmpty() ) {
			 if ( addedFirst )
				 sql.append(" AND " );

			 sql.append(" D.element_code IN (" + getElements(qvo) + ") ");
			 addedFirst = true;
		 }

		 if (qvo.getMaxDateLimit()!=null && qvo.getTimeSpan()!=null) {
			 if ( addedFirst )
				 sql.append(" AND " );
             
			 sql.append(addDateRange(qvo));
			 addedFirst = true;
		 } else {
			 if ( qvo.getYears()!=null && !qvo.getYears().isEmpty() ) {
				 if ( addedFirst )
					 sql.append(" AND " );

				 sql.append(" D.year IN (" + getYears(qvo) + ") ");
				 addedFirst = true;
			 }
		 }
		 
		 if (qvo.getValueType()!=null) {
			 if ( addedFirst )
				 sql.append(" AND " );
             
			 sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
			 addedFirst = true;
		 } 
		 
		  /**if ( addedFirst )
				sql.append(" AND " );

			sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
			addedFirst = true;
		 **/

		if ( !qvo.getDatabases().isEmpty()) {
				if ( addedFirst )
					sql.append(" AND " );

				sql.append(" database IN (" + getDatabases(qvo) + ") ");
				addedFirst = true;
		}
			
		return sql.toString();
	}

    //Start Year Label Changes
    public static String getWheresYearLabel(AMISQueryVO qvo) {
        //System.out.println("getWheresYearLabel Start "+qvo.getYears());
        StringBuffer sql = new StringBuffer();
        sql.append(" WHERE ");

        Boolean addedFirst = false;
        if ( !qvo.getAreas().isEmpty() ) {
            sql.append(" D.region_code IN (" + getAreas(qvo) + ") ");

            addedFirst = true;
        }

        if ( !qvo.getItems().isEmpty()) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.product_code IN (" + getItems(qvo) + ") ");

            addedFirst = true;
        }


        if ( !qvo.getElements().isEmpty() ) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.element_code IN (" + getElements(qvo) + ") ");
            addedFirst = true;
        }

        if (qvo.getMaxDateLimit()!=null && qvo.getTimeSpan()!=null) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(addDateRange(qvo));
            addedFirst = true;
        } else {
            if ( qvo.getYears()!=null && !qvo.getYears().isEmpty() ) {
                if ( addedFirst )
                    sql.append(" AND " );
                //System.out.println("getWheresYearLabel getYearsLabel "+getYearsLabel_fromkeys(qvo));
                sql.append(" D.year_label IN (" + getYearsLabel_fromkeys(qvo) + ") ");
                addedFirst = true;
            }
        }
        //System.out.println("getWheresYearLabel getYearsLabel "+sql.toString());
        if (qvo.getValueType()!=null) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
            addedFirst = true;
        }

        /**if ( addedFirst )
         sql.append(" AND " );

         sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
         addedFirst = true;
         **/

        if ( !qvo.getDatabases().isEmpty()) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" database IN (" + getDatabases(qvo) + ") ");
            addedFirst = true;
        }

        return sql.toString();
    }

    public static String getWheresYearLabelInKey(AMISQueryVO qvo, boolean isTotal) {
        //System.out.println("getWheresYearLabel Start "+qvo.getYears());
        StringBuffer sql = new StringBuffer();
        sql.append(" WHERE ");

        Boolean addedFirst = false;
        if ( !qvo.getAreas().isEmpty() ) {
            sql.append(" D.region_code IN (" + getAreas(qvo) + ") ");

            addedFirst = true;
        }

        if ( !qvo.getItems().isEmpty()) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.product_code IN (" + getItems(qvo) + ") ");

            addedFirst = true;
        }


        if ( !qvo.getElements().isEmpty() ) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.element_code IN (" + getElements(qvo) + ") ");
            addedFirst = true;
        }

        if (qvo.getMaxDateLimit()!=null && qvo.getTimeSpan()!=null) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(addDateRange(qvo));
            addedFirst = true;
        } else {
            if ( qvo.getYears()!=null && !qvo.getYears().isEmpty() ) {
                if ( addedFirst )
                    sql.append(" AND " );
                //System.out.println("getWheresYearLabel getYearsLabel "+getYearsLabel_storedInkeys(qvo));
                sql.append(" D.year_label IN (" + getYearsLabel_storedInkeys(qvo) + ") ");
                addedFirst = true;
            }
        }
        //System.out.println("getWheresYearLabel getYearsLabel "+sql.toString());
        if ((qvo.getValueType()!=null)&&(isTotal!=false)) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
            addedFirst = true;
        }

        /**if ( addedFirst )
         sql.append(" AND " );

         sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
         addedFirst = true;
         **/

        if ( !qvo.getDatabases().isEmpty()) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" database IN (" + getDatabases(qvo) + ") ");
            addedFirst = true;
        }
       // System.out.println("getWheresYearLabel Start sql = "+sql.toString());
        return sql.toString();
    }
    //End Year Label Changes

    //Action can be "SUPPLY_AND_DEMAND" OR "DOWNLOAD"
    public static String getWheres_population(AMISQueryVO qvo, String action) {
        //System.out.println("getWheres_population Start "+qvo.getYears());
        StringBuffer sql = new StringBuffer();
        sql.append(" WHERE ");

        Boolean addedFirst = false;
        if ( !qvo.getAreas().isEmpty() ) {
            sql.append(" region_code IN (" + getAreas(qvo) + ") ");

            addedFirst = true;
        }

        if ( qvo.getYears()!=null && !qvo.getYears().isEmpty() ) {
            if ( addedFirst )
                sql.append(" AND " );
            if(action.equals("SUPPLY_AND_DEMAND"))
            {
                //System.out.println("getWheresYearLabel getYearsLabel "+getYears_fromYearLabel_storedInkeys(qvo));
                sql.append(" year IN (" + getYears_fromYearLabel_storedInkeys(qvo) + ") ");
            }
            else
            {
                //Download page
                //System.out.println("getWheresYearLabel getYearsLabel "+getYears_fromYearLabel(qvo));
                sql.append(" year IN (" + getYears_fromYearLabel(qvo) + ") ");
            }

           // sql.append(" year IN (" + getYears(qvo) + ") ");

            addedFirst = true;
        }

        //System.out.println("getWheresYearLabel getYearsLabel "+sql.toString());

        return sql.toString();
    }
	
//	public static String getWheresWithMonth(AMISQueryVO qvo) {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" WHERE ");
//
//		 Boolean addedFirst = false;
//		 if ( !qvo.getAreas().isEmpty() ) {
//			 sql.append(" D.region_code IN (" + getAreas(qvo) + ") ");
//
//			 addedFirst = true;
//		 }
//
//		 if ( !qvo.getItems().isEmpty() ) {
//			 if ( addedFirst )
//				 sql.append(" AND " );
//
//			 sql.append(" D.product_code IN (" + getItems(qvo) + ") ");
//
//			 addedFirst = true;
//		 }
//
//
//		 if ( !qvo.getElements().isEmpty() ) {
//			 if ( addedFirst )
//				 sql.append(" AND " );
//
//			 sql.append(" D.element_code IN (" + getElements(qvo) + ") ");
//			 addedFirst = true;
//		 }
//
//		 if (qvo.getMaxDateLimit()!=null && qvo.getTimeSpan()!=null) {
//			 if ( addedFirst )
//				 sql.append(" AND " );
//
//			 sql.append(addDateRange(qvo));
//			 addedFirst = true;
//		 } else {
//			 if ( qvo.getYears()!=null && !qvo.getYears().isEmpty() ) {
//				 if ( addedFirst )
//					 sql.append(" AND " );
//
//				 sql.append(" D.year IN (" + getYears(qvo) + ") ");
//				 addedFirst = true;
//			 }
//		 }
//
//		 if (qvo.getValueType()!=null) {
//			 if ( addedFirst )
//				 sql.append(" AND " );
//
//			 sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
//			 addedFirst = true;
//		 }
//
//		  /**if ( addedFirst )
//				sql.append(" AND " );
//
//			sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
//			addedFirst = true;
//		 **/
//
//		if ( !qvo.getDatabases().isEmpty()) {
//				if ( addedFirst )
//					sql.append(" AND " );
//
//				sql.append(" D.database IN (" + getDatabases(qvo) + ") ");
//				addedFirst = true;
//		}
//
//		return sql.toString();
//	}

    //Start Year_label Changes
    public static String getWheresWithMonth(AMISQueryVO qvo) {
        StringBuffer sql = new StringBuffer();
        sql.append(" WHERE ");

        Boolean addedFirst = false;
        if ( !qvo.getAreas().isEmpty() ) {
            sql.append(" D.region_code IN (" + getAreas(qvo) + ") ");

            addedFirst = true;
        }

        if ( !qvo.getItems().isEmpty() ) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.product_code IN (" + getItems(qvo) + ") ");

            addedFirst = true;
        }


        if ( !qvo.getElements().isEmpty() ) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.element_code IN (" + getElements(qvo) + ") ");
            addedFirst = true;
        }

        if (qvo.getMaxDateLimit()!=null && qvo.getTimeSpan()!=null) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(addDateRange(qvo));
            addedFirst = true;
        } else {
            if ( qvo.getYears()!=null && !qvo.getYears().isEmpty() ) {
                if ( addedFirst )
                    sql.append(" AND " );

                //sql.append(" D.year IN (" + getYears(qvo) + ") "); getYearsLabel
//                sql.append(" D.year_label IN (" + getYearsLabel(qvo) + ") ");
                sql.append(" D.year_label IN (" + getSplitYears(qvo) + ") ");
                addedFirst = true;
            }
        }

//        if (qvo.getValueType()!=null) {
//            if ( addedFirst )
//                sql.append(" AND " );
//
//            sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
//            addedFirst = true;
//        }

       // if (qvo.getValueType()!=null) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.Value_Type IN ('TOTAL') ");
            addedFirst = true;
       // }

        /**if ( addedFirst )
         sql.append(" AND " );

         sql.append(" D.Value_Type IN (" + qvo.getValueType() + ") ");
         addedFirst = true;
         **/

        if ( !qvo.getDatabases().isEmpty()) {
            if ( addedFirst )
                sql.append(" AND " );

            sql.append(" D.database IN (" + getDatabases(qvo) + ") ");
            addedFirst = true;
        }

        return sql.toString();
    }
    //End Year_label Changes

    //Population Query
//    database     | text                   |
//    region_code  | text                   |
//    region_name  | text                   |
//    element_code | text                   |
//    element_name | text                   |
//    units        | character varying(255) |
//    year         | date                   |
//    value        | double precision       |
//    flag         | text                   |
    public static String population_data(AMISQueryVO qvo, String view, String datasource, String defaultLanguage) {

        boolean nat_found = true;
        if((datasource!=null)&&(!datasource.isEmpty())&&(!(datasource.equals("NATIONAL"))))
        {
            nat_found = false;
        }

        StringBuffer sql = new StringBuffer();
        sql.append(" UNION SELECT ");

        for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
            String select = qvo.getSelects().get(i);

            if(select.contains("+AMISConstants.defaultLanguage")){
                select = select.replace("+AMISConstants.defaultLanguage", defaultLanguage);
            }

            if(select.contains("D.")){
                select = select.replace("D.", "");
            }

            if((nat_found)&&(select.equals("database")))
            {
                sql.append("'NATIONAL' as database");
            }
            else if(select.equals("product_code"))
            {
                sql.append("'9' as product_code");
            }
            else if(select.equals("product_name"))
            {
                sql.append("'Population' as product_name");
            }
            else if(select.equals("year_label"))
            {
               // sql.append("year|| '/' ||substr((CAST(year AS integer)+1),2)");
                sql.append("substr((CAST(extract(year from year) as varchar)) ,0, 5)|| '/' ||substr(CAST(((CAST(extract(year from year) as integer))+1) as varchar) ,3)");
            }
            else
            {
                sql.append(select);
            }

            if (i < qvo.getSelects().size() - 1)
                sql.append(", ");
        }
         //System.out.println(" population_data afer getSelect "+sql);
       // StringBuffer sql = new StringBuffer();
       // sql.append(" SELECT element_name, element_code, value, flag, year as code, CAST(EXTRACT(year from year) AS varchar) as label ");
        sql.append(" FROM "+view+" " );
        sql.append(getWheres_population(qvo, "DOWNLOAD"));
        //System.out.println(" population_data afer getWheres_population "+sql);
        if(!nat_found)
        {
            //System.out.println(" population_data afer if(!nat_found) "+nat_found);
            sql.append(" AND Database = '"+datasource+"' ");
        }

        return sql.toString();
    }

    //Population Query
//    database     | text                   |
//    region_code  | text                   |
//    region_name  | text                   |
//    element_code | text                   |
//    element_name | text                   |
//    units        | character varying(255) |
//    year         | date                   |
//    value        | double precision       |
//    flag         | text                   |
    public static String population_data_supplyAndDemand(AMISQueryVO qvo, String view, String datasource, String defaultLanguage, int i_datasource) {

        boolean nat_found = true;
        if((datasource!=null)&&(!datasource.isEmpty())&&(!(datasource.equals("NATIONAL"))))
        {
            nat_found = false;
        }

        StringBuffer sql = new StringBuffer();
        if(i_datasource!=0)
        {
            sql.append(" UNION ");
        }

        sql.append("SELECT ");

        for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
            String select = qvo.getSelects().get(i);
            //System.out.println(" population_data_supplyAndDemand 1 select "+select);
            if((select.indexOf("D."))>=0)
            {
                //System.out.println(" replace ");
                int index = select.indexOf("D.");
                //System.out.println(" population_data_supplyAndDemand 2 index "+index);
                select = select.replace("D.","");
                //select = select.substring(index+2);
                //System.out.println(" population_data_supplyAndDemand 3 select "+select);
            }
            //System.out.println(" population_data_supplyAndDemand afer getSelect "+sql);
            if((nat_found)&&(select.equals("database")))
            {
                sql.append("'NATIONAL' as database");
            }
            else if(select.equals("product_code"))
            {
                sql.append("'9' as product_code");
            }
            else if(select.equals("product_name"))
            {
                sql.append("'Population' as product_name");
            }
            else if(select.equals("year_label"))
            {
                // sql.append("year|| '/' ||substr((CAST(year AS integer)+1),2)");
                sql.append("(substr((CAST(extract(year from year) as varchar)) ,0, 5)|| '/' ||substr(CAST(((CAST(extract(year from year) as integer))+1) as varchar) ,3)) as year_label");
            }
            else
            {
                sql.append(select);
            }

            if (i < qvo.getSelects().size() - 1)
                sql.append(", ");
        }
        //System.out.println(" population_data_supplyAndDemand afer getSelect "+sql);
        // StringBuffer sql = new StringBuffer();
        // sql.append(" SELECT element_name, element_code, value, flag, year as code, CAST(EXTRACT(year from year) AS varchar) as label ");

        sql.append(", 'Population' as product_name, element_name ");
        sql.append(" FROM "+view+" " );
        sql.append(getWheres_population(qvo, "SUPPLY_AND_DEMAND"));
        //System.out.println(" population_data_supplyAndDemand afer getWheres_population "+sql);
        if(!nat_found)
        {
            //System.out.println(" population_data_supplyAndDemand afer if(!nat_found) "+nat_found);
            sql.append(" AND Database = '"+datasource+"' ");
        }

        return sql.toString();
    }

    public static String getDatasetCodes(AMISQueryVO p) {
		String dataset_codes = "";
		Object[] codes = p.getDatasetCodes().keySet().toArray();
		for (int i = 0 ; i < codes.length ; i++) {
			dataset_codes += "'" + codes[i].toString() + "'";
			if (i < codes.length - 1)
				dataset_codes += ", ";
		}
		return dataset_codes;
	}
}