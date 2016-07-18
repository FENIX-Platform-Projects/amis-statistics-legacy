package org.fao.fenix.web.modules.amis.server;

import java.util.List;
import java.util.Map;

import net.sf.cglib.beans.FixedKeySet;
import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.constants.AMISAggregationConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISTableConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISCalculationParametersVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;


public class AMISQueryBuilder {

	private final static Logger LOGGER = Logger.getLogger(AMISQueryBuilder.class);
	
	public static String sqlTableQuery(AMISQueryVO qvo, Boolean withLimitCheck, AMISDao amisDao) {
		StringBuffer sql = new StringBuffer();

	//	AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());
 	   //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: qvo.getselect: ");
//		if(qvo.getSelects()!=null)
//		{
//			//System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: qvo.getSelects()!=null ");
//			for(String select: qvo.getSelects())
//			{
//				System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: select "+ select);
//			}
//		}
//		else
//		{
//			System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: qvo.getSelects()=null ");
//		}
		if(qvo.getRunMaxDateQuery()){
			sql.append(addSelectMaxDate(qvo.getSelectedDataset()));
		} else {
			LOGGER.info("runMaxDateQuery IS FALSE .." );
			sql.append(getSelectsWithoutLimit(qvo));
			// add aggreation /** TODO: dynamic **/
		}
		//System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: getSelects(qvo, true) =  "+getSelects(qvo, true));
		//System.out.println("AMISQueryBuilder Function: sqlTableQuery Text:nUOVO qvo.getFroms() *"+qvo.getFroms()+"*");
		boolean isComparePage = false;
		//sql.append(getFromsWithMonthPosition(qvo, isComparePage));
        sql.append(getFromsWithMonthPosition_YearLabel(qvo, isComparePage));
//		sql.append(" FROM ");
//		
//		//sql.append(qvo.getFroms());
//		
//		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
//			String from = qvo.getFroms().get(i);
//			sql.append(from);
//			
//			if (i < qvo.getFroms().size() - 1)
//				sql.append(", ");
//		}
		sql.append(getWheresYearLabel(qvo));

        //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: after getWheresYearLabel*"+sql.toString()+"*");

//		if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
//		 // check if the flag can give problems
//		 sql.append(AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));
//
//		 if ( qvo.getAddLabels())
//			 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));
//
//		}

        boolean population_found = false;
        StringBuffer population_sql= new StringBuffer();


        //Insert the query to retrieve the data from the POPULATION TABLE
        //If there is or items either elements AND database
        if(((!qvo.getItems().isEmpty())||(!qvo.getElements().isEmpty()))&&(!qvo.getDatabases().isEmpty()))
        {
            //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! FIRST IF ");
            //Checking is possible
            boolean population_item_found = false;
            if(!qvo.getItems().isEmpty())
            {
                Object[] items = qvo.getItems().keySet().toArray();
                //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! items "+items.toString());
                for(int i=0; i< items.length;i++)
                {
                    //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! 9999 item i "+i+" value "+items[i].toString());
                    if((items[i].toString().equals("9")))
                    {
                        //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! 9999 ");
                        population_found = true;
                        population_item_found = true;
                        break;
                    }
                }
//                if((items.toString().contains("9"))||(items.toString().contains("'9'")))
//                {
//                    System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! 9999 ");
//                    population_found = true;
//                }
            }

            if((!qvo.getElements().isEmpty())&&(!population_item_found))
            {
                Object[] elements = qvo.getElements().keySet().toArray();
                //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! elements "+elements);
                for(int i=0; i< elements.length;i++)
                {
                    //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! 1111 item i "+i+" value "+elements[i].toString());
                    if((elements[i].toString().equals("1")))
                    {
                        //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found! 1111 ");
                        population_found = true;
                        break;
                    }
                }
            }

            if(population_found)
            {
                //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found!!!");

//                for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
//                    String select = qvo.getSelects().get(i);
//
//                    if(select.contains("+AMISConstants.defaultLanguage")){
//                        select = select.replace("+AMISConstants.defaultLanguage", defaultLanguage);
//                    }
//
//                    sql.append(select);
//
//
//                    if (i < qvo.getSelects().size() - 1)
//                        sql.append(", ");
//                }

                Object[] databases = qvo.getDatabases().keySet().toArray();

                String table_code = "";
                String table_name = "";

                for (int i = 0 ; i < databases.length ; i++) {
                    if(databases[i].toString().equals("NATIONAL"))
                    {
                        table_code = "AMIS_POPULATION_NATIONAL";

                    }
                    else
                    {
                        table_code = "AMIS_POPULATION";
                    }
                    String tablename_sql = FENIXQueryBuilder.getDatasetsQuery_singleCode(table_code);

                    List<Object[]> sqlResult = amisDao.getResultList(tablename_sql);
                    table_name = (String) sqlResult.get(0)[1].toString();
                    //System.out.println("table_name "+table_name);
                    population_sql.append(FENIXQueryBuilder.population_data(qvo, table_name, databases[i].toString(), AMISConstants.defaultLanguage));
                }


//                boolean first_database = true;
//                String pop_national = "";
//                String pop_not_national = "";
//
//                Object[] databases = qvo.getDatabases().keySet().toArray();
//                if(databases.toString().contains("NATIONAL"))
//                {
//                    first_database = false;
//                }
//                if(!databases.toString().contains("NATIONAL"))
//                {
//                    if(!first_database)
//                    {
//                       //UNION
//                    }
//                }

            }

        }

        AMISQueryVO qvo_popul_found = null;
        if(population_found)
        {
           qvo_popul_found = new AMISQueryVO();
            if(!qvo.getRunMaxDateQuery()) {
                qvo_popul_found.setRunMaxDateQuery(qvo.getRunMaxDateQuery());
            }

            if((qvo.getOrderBys()!=null)&&(qvo.getOrderBys().size()>0))
            {
                String order_elem = "";
                for (int i = 0 ; i < qvo.getOrderBys().size() ; i++) {
                    if((qvo.getOrderBys().get(i).indexOf("D."))>=0)
                    {
                        //System.out.println(" replace ");
                        int index = qvo.getOrderBys().get(i).indexOf("D.");
                        qvo_popul_found.getOrderBys().add(i,qvo.getOrderBys().get(i).substring(index+2));
                    }
                    else
                    {
                        //System.out.println(" not replace ");
                        qvo_popul_found.getOrderBys().add(i,qvo.getOrderBys().get(i));
                    }
                    //System.out.println(" qvo_popul_found.getOrderBys().get(i) "+qvo_popul_found.getOrderBys().get(i));
                }
            }

            if(!qvo.getRunMaxDateQuery()) {
                qvo_popul_found.setRunMaxDateQuery(qvo.getRunMaxDateQuery());
            }

            if(qvo.getSortingOrder()!=null) {
                qvo_popul_found.setSortingOrder(qvo.getSortingOrder());
            }

            sql.append(population_sql);
            if(qvo.getRunMaxDateQuery()) {
                sql.append(addOrderByMaxDate(qvo_popul_found));
            } else {
                if(qvo.getOrderBys().size() > 0) {
                    LOGGER.info("GET Order By Population found.." );
                    sql.append(addOrderByClause(qvo_popul_found));
                }
            }
        }
        else
        {
            if(qvo.getRunMaxDateQuery()) {
                sql.append(addOrderByMaxDate(qvo));
            } else {
                if(qvo.getOrderBys().size() > 0) {
                    LOGGER.info("GET Order By .." );
                    sql.append(addOrderByClause(qvo));
                }
            }
        }

        //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: after addOrderByClause*" + sql.toString() + "*");
		 if ( withLimitCheck )
				if( qvo.getLimit() != null && qvo.getLimit() != 0) {
						sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
				}	
		 
			LOGGER.info("SQL :" + sql );
		return sql.toString();
	}

    //Old
    public static String sqlTableQuerySupplyAndDemand(AMISQueryVO qvo, Boolean withLimitCheck) {
        StringBuffer sql = new StringBuffer();

        //	AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());
        //System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: qvo.getselect: ");
//        if(qvo.getSelects()!=null)
//        {
//            //System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: qvo.getSelects()!=null ");
//            for(String select: qvo.getSelects())
//            {
//                System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: select "+ select);
//            }
//        }
//        else
//        {
//            System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: qvo.getSelects()=null ");
//        }
        if(qvo.getRunMaxDateQuery()){
            sql.append(addSelectMaxDate(qvo.getSelectedDataset()));
        } else {
            LOGGER.info("runMaxDateQuery IS FALSE .." );
            sql.append(getSelectsWithoutLimit(qvo));
            // add aggreation /** TODO: dynamic **/
        }
        //System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: getSelects(qvo, true) =  "+getSelects(qvo, true));
        //System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text:nUOVO qvo.getFroms() *"+qvo.getFroms()+"*");
        boolean isComparePage = false;
        sql.append(getFromsWithMonthPosition(qvo, isComparePage));
//		sql.append(" FROM ");
//
//		//sql.append(qvo.getFroms());
//
//		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
//			String from = qvo.getFroms().get(i);
//			sql.append(from);
//
//			if (i < qvo.getFroms().size() - 1)
//				sql.append(", ");
//		}
//        sql.append(getWheres(qvo));
        sql.append(getWheresYearLabelInkey(qvo, false));

//		if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
//		 // check if the flag can give problems
//		 sql.append(AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));
//
//		 if ( qvo.getAddLabels())
//			 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));
//
//		}

        if(qvo.getRunMaxDateQuery()) {
            sql.append(addOrderByMaxDate(qvo));
        } else {
            if(qvo.getOrderBys().size() > 0) {
                LOGGER.info("GET Order By .." );
                sql.append(addOrderByClause(qvo));
            }
        }

        if ( withLimitCheck )
            if( qvo.getLimit() != null && qvo.getLimit() != 0) {
                sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
            }

        LOGGER.info("sqlTableQuerySupplyAndDemand SQL :" + sql );
        return sql.toString();
    }

    //End Old

    public static String sqlTableQuerySupplyAndDemand_population(AMISQueryVO qvo, Boolean withLimitCheck, AMISDao amisDao) {
        StringBuffer sql = new StringBuffer();

//        //	AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());
//        System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: qvo.getselect: ");
//        if(qvo.getSelects()!=null)
//        {
//            System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: qvo.getSelects()!=null ");
//            for(String select: qvo.getSelects())
//            {
//                System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: select "+ select);
//            }
//        }
//        else
//        {
//            System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: qvo.getSelects()=null ");
//        }
//        if(qvo.getRunMaxDateQuery()){
//            sql.append(addSelectMaxDate(qvo.getSelectedDataset()));
//        } else {
//            LOGGER.info("runMaxDateQuery IS FALSE .." );
//            sql.append(getSelectsWithoutLimit(qvo));
//            // add aggreation /** TODO: dynamic **/
//        }
//        System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text: getSelects(qvo, true) =  "+getSelects(qvo, true));
//        System.out.println("AMISQueryBuilder Function: sqlTableQuerySupplyAndDemand Text:nUOVO qvo.getFroms() *"+qvo.getFroms()+"*");
//        boolean isComparePage = false;
//        sql.append(getFromsWithMonthPosition(qvo, isComparePage));
////		sql.append(" FROM ");
////
////		//sql.append(qvo.getFroms());
////
////		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
////			String from = qvo.getFroms().get(i);
////			sql.append(from);
////
////			if (i < qvo.getFroms().size() - 1)
////				sql.append(", ");
////		}
//        sql.append(getWheres(qvo));
//
//
//
////		if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
////		 // check if the flag can give problems
////		 sql.append(AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));
////
////		 if ( qvo.getAddLabels())
////			 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));
////
////		}
//
//        if(qvo.getRunMaxDateQuery()) {
//            sql.append(addOrderByMaxDate(qvo));
//        } else {
//            if(qvo.getOrderBys().size() > 0) {
//                LOGGER.info("GET Order By .." );
//                sql.append(addOrderByClause(qvo));
//            }
//        }
//
//        if ( withLimitCheck )
//            if( qvo.getLimit() != null && qvo.getLimit() != 0) {
//                sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
//            }
//
//        LOGGER.info("sqlTableQuerySupplyAndDemand SQL :" + sql );
//        return sql.toString();
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        StringBuffer population_sql= new StringBuffer();

        //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: Population Found!!!");
        Object[] databases = qvo.getDatabases().keySet().toArray();

        String table_code = "";
        String table_name = "";

        for (int i = 0 ; i < databases.length ; i++) {
            if(databases[i].toString().equals("NATIONAL"))
            {
                table_code = "AMIS_POPULATION_NATIONAL";

            }
            else
            {
                table_code = "AMIS_POPULATION";
            }
            String tablename_sql = FENIXQueryBuilder.getDatasetsQuery_singleCode(table_code);

            List<Object[]> sqlResult = amisDao.getResultList(tablename_sql);
            table_name = (String) sqlResult.get(0)[1].toString();
            //System.out.println("table_name "+table_name);
            population_sql.append(FENIXQueryBuilder.population_data_supplyAndDemand(qvo, table_name, databases[i].toString(), AMISConstants.defaultLanguage, i));
        }
        sql.append(population_sql);
//        if(qvo.getRunMaxDateQuery()) {
//            sql.append(addOrderByMaxDate(qvo));
//        } else {
//            if(qvo.getOrderBys().size() > 0) {
//                LOGGER.info("GET Order By Population found.." );
//                sql.append(addOrderByClause(qvo));
//            }
//        }
//
//        System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: after addOrderByClause*" + sql.toString() + "*");
//        if ( withLimitCheck )
//            if( qvo.getLimit() != null && qvo.getLimit() != 0) {
//                sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
//            }
//
//        LOGGER.info("sqlTableQuerySupplyAndDemand_population SQL :" + sql );
//        return sql.toString();




        AMISQueryVO qvo_popul_found = new AMISQueryVO();


        if(!qvo.getRunMaxDateQuery()) {
            qvo_popul_found.setRunMaxDateQuery(qvo.getRunMaxDateQuery());
        }

        if((qvo.getOrderBys()!=null)&&(qvo.getOrderBys().size()>0))
        {
            String order_elem = "";
            for (int i = 0 ; i < qvo.getOrderBys().size() ; i++) {
                if((qvo.getOrderBys().get(i).indexOf("D."))>=0)
                {
                    //System.out.println(" replace ");
                    int index = qvo.getOrderBys().get(i).indexOf("D.");
                    qvo_popul_found.getOrderBys().add(i,qvo.getOrderBys().get(i).substring(index+2));
                }
                else
                {
                    //System.out.println(" not replace ");
                    qvo_popul_found.getOrderBys().add(i,qvo.getOrderBys().get(i));
                }
                //System.out.println(" qvo_popul_found.getOrderBys().get(i) "+qvo_popul_found.getOrderBys().get(i));
            }
        }

        if(!qvo.getRunMaxDateQuery()) {
            qvo_popul_found.setRunMaxDateQuery(qvo.getRunMaxDateQuery());
        }

        if(qvo.getSortingOrder()!=null) {
            qvo_popul_found.setSortingOrder(qvo.getSortingOrder());
        }

        if(qvo.getRunMaxDateQuery()) {
            sql.append(addOrderByMaxDate(qvo_popul_found));
        } else {
            if(qvo.getOrderBys().size() > 0) {
                LOGGER.info("GET Order By Population found.." );
                sql.append(addOrderByClause(qvo_popul_found));
            }
        }

        //System.out.println("AMISQueryBuilder Function: sqlTableQuery Text: after addOrderByClause*" + sql.toString() + "*");
        if ( withLimitCheck )
            if( qvo.getLimit() != null && qvo.getLimit() != 0) {
                sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
            }

        LOGGER.info("SQL :" + sql );
        return sql.toString();


    }



    /****public static String sqlTableQuery(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		
		if(qvo.getRunMaxDateQuery()){
			if(selectedDataset.equals(AMISConstants.FAOSTAT))
				sql.append(addSelectMaxDate(qvo.getSelectedDataset()));
			else
				qvo.setLimit(1);
			
		} else {
			LOGGER.info("runMaxDateQuery IS FALSE .." );
			sql.append(getSelects(qvo, true));
		}

        sql.append(qvo.getFroms());
		
		if (selectedDataset.equals(AMISConstants.FAOSTAT) && qvo.getAddFlag() ) {
			sql.append(", Flag F ");
		}


		sql.append(getWheres(qvo));

		if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
		 // check if the flag can give problems
		 sql.append(AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));

		 if ( qvo.getAddLabels())
			 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));

		} else {
			if ( qvo.getAddLabels())
				 sql.append( FENIXQueryBuilder.addLabelJoins(qvo));
		}
	
		 if(qvo.getRunMaxDateQuery())
			sql.append(addOrderByMaxDate(qvo));

			LOGGER.info("SQL :" + sql );
		return sql.toString();
	}
**/
	
	public static String buildQuery(AMISQueryVO qvo, Boolean isRunmaxQuery, Boolean addUnitName) {
		
		//AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		 
		StringBuffer sql = new StringBuffer();
		
		if(qvo.getRunMaxDateQuery()){
			if(qvo.getRunTimeIntervalQuery()){
				sql.append(addSelectTimeInterval(qvo));
		    } 
			else {
				//if(selectedDataset.equals(AMISConstants.FAOSTAT))
				//	sql.append(addSelectMaxDate(qvo.getSelectedDataset()));
				//else {
					sql.append("SELECT D.year ");
					qvo.setLimit(1);
				//}
			}	
		} else {
			LOGGER.info("runMaxDateQuery IS FALSE .." );
			sql.append(getSelects(qvo, true));
			
			if ( addUnitName )
			 sql.append( ", D.Units ");
			
			/* value moved to selects */
			//if (selectedDataset.equals(AMISConstants.FAOSTAT)) 
				//sql.append( ", ROUND(" + qvo.getAggregationType() +"(D.Value), 2) "); 
		     //else
		    	//sql.append( ", ROUND(" + qvo.getAggregationType() +"(CAST(value AS NUMERIC)), 2) ");
			
		}
		
		LOGGER.info("GET FROMS .." );
		sql.append(getFroms(qvo));
		
	
		//if ( qvo.getAddFlag() && selectedDataset.equals(AMISConstants.FAOSTAT)) {
		//	sql.append(", Flag F ");
		//}
		
		LOGGER.info("GET Wheres .." );
		sql.append(getWheres(qvo));
		
			 /** TODO: add if **/
		//if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
		//	LOGGER.info("GET Joins .." );
		//	 sql.append( AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));
			 
		//	 LOGGER.info("GET Label Joins .." );
		//	 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));		 
			 
		//	 LOGGER.info("GET Regional .." );
		//	 sql.append( AMISFaostatQueryBuilder.addRegionalConditions(qvo));
	  //  } else {
	    	LOGGER.info("GET Label Joins .." );
	    	if(qvo.getAddLabels())
	    		sql.append(FENIXQueryBuilder.addLabelJoins(qvo));		
	    //}
	    	
	    //	if(qvo.getShowZeroValues()!= null && !qvo.getShowZeroValues())
	    	//	sql.append(FENIXQueryBuilder.addNoZeroValues(qvo));		
	    	
				 
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
						LOGGER.info("GET FROMS .." );
						sql.append(getFroms(qvo));
					
						//if ( qvo.getAddFlag() && selectedDataset.equals(AMISConstants.FAOSTAT )) {
						//	sql.append(", Flag F ");
						//}
						/** WHERE **/
						sql.append(getWheres(qvo));
						
						//if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
						//	LOGGER.info("GET Joins .." );
						//	 sql.append( AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));
							 
						//	 LOGGER.info("GET Label Joins .." );
						//	 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));		 
					  //  }
						//else {
							LOGGER.info("GET Label Joins .." );
							
							if(qvo.getAddLabels())
								sql.append(FENIXQueryBuilder.addLabelJoins(qvo)); 	
						//}
				    		
						//if (selectedDataset.equals(AMISConstants.FAOSTAT)) 
						//	sql.append(AMISFaostatQueryBuilder.addRegionalConditions(qvo));
								
						sql.append(" GROUP BY " + qvo.getNestedLimitField());
							
						sql.append(addOrderByClause(qvo));
						
						if( qvo.getLimit() != null && qvo.getLimit() != 0) {
							//if(!qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT))
								sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
						}
					sql.append(" ) ");
				}	
			 }
			 
			 
			 if(!qvo.getRunMaxDateQuery() && !qvo.getRunTimeIntervalQuery())
				 sql.append(addGroupBYClause(qvo, addUnitName));
			 
			 
			 LOGGER.info("GET Order By .." );
			sql.append(addOrderByClause(qvo));
			 
			// ORDER BY for the max date
			if(qvo.getRunMaxDateQuery() && !qvo.getRunTimeIntervalQuery()){ 
				sql.append(addOrderByMaxDate(qvo));
			}
			
			if( qvo.getLimit() != null && qvo.getLimit() != 0) {
				if(!qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT))
					sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
			}
			
//			 sql.append(" ORDER BY SUM(D.Value) "+ qvo.getSortingOrder() +" ");
					 
				LOGGER.info("SQL :" + sql );
				//System.out.println("Class:AmisQueryBuilder Function: buildQuery Text: sql= "+sql);
		return sql.toString();
	}
	
//	public static String buildQueryWithMonth(AMISQueryVO qvo, Boolean isRunmaxQuery, Boolean addUnitName) {
//
//		//AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());
//
//		StringBuffer sql = new StringBuffer();
//
//		if(qvo.getRunMaxDateQuery()){
//			if(qvo.getRunTimeIntervalQuery()){
//				sql.append(addSelectTimeInterval(qvo));
//		    }
//			else {
//				//if(selectedDataset.equals(AMISConstants.FAOSTAT))
//				//	sql.append(addSelectMaxDate(qvo.getSelectedDataset()));
//				//else {
//					sql.append("SELECT D.year ");
//					qvo.setLimit(1);
//				//}
//			}
//		} else {
//			LOGGER.info("runMaxDateQuery IS FALSE .." );
//			sql.append(getSelects(qvo, true));
//
//			if ( addUnitName )
//			 sql.append( ", D.Units ");
//
//			/* value moved to selects */
//			//if (selectedDataset.equals(AMISConstants.FAOSTAT))
//				//sql.append( ", ROUND(" + qvo.getAggregationType() +"(D.Value), 2) ");
//		     //else
//		    	//sql.append( ", ROUND(" + qvo.getAggregationType() +"(CAST(value AS NUMERIC)), 2) ");
//
//		}
//
//		LOGGER.info("GET FROMS .." );
//		boolean isComparePage = true;
//		sql.append(getFromsWithMonthPosition(qvo, isComparePage));
//
//
//		//if ( qvo.getAddFlag() && selectedDataset.equals(AMISConstants.FAOSTAT)) {
//		//	sql.append(", Flag F ");
//		//}
//
//		LOGGER.info("GET Wheres .." );
//		sql.append(getWheresWithMonth(qvo));
//
//			 /** TODO: add if **/
//		//if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
//		//	LOGGER.info("GET Joins .." );
//		//	 sql.append( AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));
//
//		//	 LOGGER.info("GET Label Joins .." );
//		//	 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));
//
//		//	 LOGGER.info("GET Regional .." );
//		//	 sql.append( AMISFaostatQueryBuilder.addRegionalConditions(qvo));
//	  //  } else {
//	    	LOGGER.info("GET Label Joins .." );
//	    	if(qvo.getAddLabels())
//	    		sql.append(FENIXQueryBuilder.addLabelJoins(qvo));
//	    //}
//
//	    //	if(qvo.getShowZeroValues()!= null && !qvo.getShowZeroValues())
//	    	//	sql.append(FENIXQueryBuilder.addNoZeroValues(qvo));
//
//
//			 /** this is the subquery if the limit exists **/
//			 if ( !isRunmaxQuery ) {
//				if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
//					LOGGER.info("CREATING THE NESTED QUERY ..");
//					// add the other query
//
//					sql.append(" AND " + qvo.getNestedLimitField() + " IN ( ");
//						sql.append("SELECT ");
//						if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
//							sql.append(" TOP ").append(qvo.getNestedLimit()).append(" ");
//						}
//
//
//						sql.append(" " + qvo.getNestedLimitField() + " ");
//
//
//						/** FROM **/
//						LOGGER.info("GET FROMS .." );
//						sql.append(getFroms(qvo));
//
//						//if ( qvo.getAddFlag() && selectedDataset.equals(AMISConstants.FAOSTAT )) {
//						//	sql.append(", Flag F ");
//						//}
//						/** WHERE **/
//						sql.append(getWheres(qvo));
//
//						//if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
//						//	LOGGER.info("GET Joins .." );
//						//	 sql.append( AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));
//
//						//	 LOGGER.info("GET Label Joins .." );
//						//	 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));
//					  //  }
//						//else {
//							LOGGER.info("GET Label Joins .." );
//
//							if(qvo.getAddLabels())
//								sql.append(FENIXQueryBuilder.addLabelJoins(qvo));
//						//}
//
//						//if (selectedDataset.equals(AMISConstants.FAOSTAT))
//						//	sql.append(AMISFaostatQueryBuilder.addRegionalConditions(qvo));
//
//						sql.append(" GROUP BY " + qvo.getNestedLimitField());
//
//						sql.append(addOrderByClause(qvo));
//
//						if( qvo.getLimit() != null && qvo.getLimit() != 0) {
//							//if(!qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT))
//								sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
//						}
//					sql.append(" ) ");
//				}
//			 }
//
//
//			 if(!qvo.getRunMaxDateQuery() && !qvo.getRunTimeIntervalQuery())
//				 sql.append(addGroupBYClause(qvo, addUnitName));
//
//
//			 LOGGER.info("GET Order By .." );
//			sql.append(addOrderByClause(qvo));
//
//			// ORDER BY for the max date
//			if(qvo.getRunMaxDateQuery() && !qvo.getRunTimeIntervalQuery()){
//				sql.append(addOrderByMaxDate(qvo));
//			}
//
//			if( qvo.getLimit() != null && qvo.getLimit() != 0) {
//				if(!qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT))
//					sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
//			}
//
////			 sql.append(" ORDER BY SUM(D.Value) "+ qvo.getSortingOrder() +" ");
//
//				LOGGER.info("SQL :" + sql );
//				System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: sql= "+sql);
//		return sql.toString();
//	}

    //End Year Label Changes
    public static String buildQueryWithMonth(AMISQueryVO qvo, Boolean isRunmaxQuery, Boolean addUnitName) {

        //AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());

        StringBuffer sql = new StringBuffer();

        if(qvo.getRunMaxDateQuery()){
            //System.out.println("1");
            if(qvo.getRunTimeIntervalQuery()){
               // System.out.println("2");
                sql.append(addSelectTimeInterval(qvo));
            }
            else {
                //if(selectedDataset.equals(AMISConstants.FAOSTAT))
                //	sql.append(addSelectMaxDate(qvo.getSelectedDataset()));
                //else {
               // System.out.println("3");
                sql.append("SELECT D.year ");
                qvo.setLimit(1);
                //}
            }
            //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 1 sql= "+sql);
        } else {
           // System.out.println("4");
            LOGGER.info("runMaxDateQuery IS FALSE .." );
            sql.append(getSelects(qvo, true));

            if ( addUnitName )
                sql.append( ", D.Units ");

			/* value moved to selects */
            //if (selectedDataset.equals(AMISConstants.FAOSTAT))
            //sql.append( ", ROUND(" + qvo.getAggregationType() +"(D.Value), 2) ");
            //else
            //sql.append( ", ROUND(" + qvo.getAggregationType() +"(CAST(value AS NUMERIC)), 2) ");
            //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 2  sql= "+sql);
        }

        LOGGER.info("GET FROMS .." );
        boolean isComparePage = true;
        //sql.append(getFromsWithMonthPosition(qvo, isComparePage));
        //Year -> Year Label
        sql.append(getFromsWithMonthPosition_YearLabel(qvo, isComparePage));
        //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 3 sql= "+sql);
        //if ( qvo.getAddFlag() && selectedDataset.equals(AMISConstants.FAOSTAT)) {
        //	sql.append(", Flag F ");
        //}

        LOGGER.info("GET Wheres .." );
        sql.append(getWheresWithMonth(qvo));
        //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 4 sql= "+sql);
        /** TODO: add if **/
        //if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
        //	LOGGER.info("GET Joins .." );
        //	 sql.append( AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));

        //	 LOGGER.info("GET Label Joins .." );
        //	 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));

        //	 LOGGER.info("GET Regional .." );
        //	 sql.append( AMISFaostatQueryBuilder.addRegionalConditions(qvo));
        //  } else {
        LOGGER.info("GET Label Joins .." );
        if(qvo.getAddLabels())
            sql.append(FENIXQueryBuilder.addLabelJoins(qvo));
        //}
        //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 5 sql= "+sql);
        //	if(qvo.getShowZeroValues()!= null && !qvo.getShowZeroValues())
        //	sql.append(FENIXQueryBuilder.addNoZeroValues(qvo));


        /** this is the subquery if the limit exists **/
        if ( !isRunmaxQuery ) {
            //System.out.println("5");
            if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
                LOGGER.info("CREATING THE NESTED QUERY ..");
                // add the other query

                sql.append(" AND " + qvo.getNestedLimitField() + " IN ( ");
                sql.append("SELECT ");
                if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
                    //System.out.println("6");
                    sql.append(" TOP ").append(qvo.getNestedLimit()).append(" ");
                }


                sql.append(" " + qvo.getNestedLimitField() + " ");

                //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 6 sql= "+sql);
                /** FROM **/
                LOGGER.info("GET FROMS .." );
                sql.append(getFroms(qvo));

                //if ( qvo.getAddFlag() && selectedDataset.equals(AMISConstants.FAOSTAT )) {
                //	sql.append(", Flag F ");
                //}
                /** WHERE **/
                //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 7 sql= "+sql);
                sql.append(getWheres(qvo));
                //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 8 sql= "+sql);
                //if (selectedDataset.equals(AMISConstants.FAOSTAT)) {
                //	LOGGER.info("GET Joins .." );
                //	 sql.append( AMISFaostatQueryBuilder.addJoins(qvo, qvo.getAddFlag()));

                //	 LOGGER.info("GET Label Joins .." );
                //	 sql.append( AMISFaostatQueryBuilder.addLabelJoins(qvo));
                //  }
                //else {
                LOGGER.info("GET Label Joins .." );

                if(qvo.getAddLabels())
                {
                    //System.out.println("7");
                    sql.append(FENIXQueryBuilder.addLabelJoins(qvo));
                    //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 9 sql= "+sql);
                }

                //}

                //if (selectedDataset.equals(AMISConstants.FAOSTAT))
                //	sql.append(AMISFaostatQueryBuilder.addRegionalConditions(qvo));

                sql.append(" GROUP BY " + qvo.getNestedLimitField());

                sql.append(addOrderByClause(qvo));

                //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 10 sql= "+sql);

                if( qvo.getLimit() != null && qvo.getLimit() != 0) {
                    //if(!qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT))
                    sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");
                    //System.out.println("8");
                }
                sql.append(" ) ");
            }
        }
        //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 11 sql= "+sql);

        if(!qvo.getRunMaxDateQuery() && !qvo.getRunTimeIntervalQuery())
        {
            sql.append(addGroupBYClause(qvo, addUnitName));
            //System.out.println("9");
        }

        //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 12 sql= "+sql);

        LOGGER.info("GET Order By .." );
        sql.append(addOrderByClause(qvo));

        // ORDER BY for the max date
        if(qvo.getRunMaxDateQuery() && !qvo.getRunTimeIntervalQuery()){
            //System.out.println("10");
            sql.append(addOrderByMaxDate(qvo));
        }
        //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: 13 sql= "+sql);

        if( qvo.getLimit() != null && qvo.getLimit() != 0) {
            if(!qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT))
                sql.append(" LIMIT ").append(qvo.getLimit()).append(" ");

            //System.out.println("11");
        }

//			 sql.append(" ORDER BY SUM(D.Value) "+ qvo.getSortingOrder() +" ");

        LOGGER.info("SQL :" + sql );
        //System.out.println("Class:AmisQueryBuilder Function: buildQueryWithMonth Text: sql= "+sql);
        return sql.toString();
    }
    //End Year Label Changes
	
	public static String buildNestedQuery(AMISQueryVO qvo) {
		AMISConstants selectedDataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		
		LOGGER.info("BUILDING NESTED QUERY");
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT ");
		if( qvo.getNestedLimit() != null && qvo.getNestedLimit() != 0) {
			sql.append(" TOP ").append(qvo.getNestedLimit()).append(" ");
		}


		sql.append(" " + qvo.getNestedLimitField() + " ");


		/** FROM **/		
		if (selectedDataset.equals(AMISConstants.FAOSTAT)) 
			sql.append(" FROM Data D ");
		else 
			sql.append(" FROM "+qvo.getDatasetTableName());
		
		/** WHERE **/
		sql.append(getWheres(qvo));

		if (selectedDataset.equals(AMISConstants.FAOSTAT)) 
			sql.append(AMISFaostatQueryBuilder.addRegionalConditions(qvo));
		
		sql.append(" GROUP BY " + qvo.getNestedLimitField());

		 sql.append(addOrderByClause(qvo));

		 LOGGER.info("SQL --> BUILDING NESTED QUERY: " + sql.toString());
		return sql.toString();
	}
	
	
   public static String getMeasurementUnits(AMISQueryVO qvo, AMISDao amisDao) {
		
		String sql = FENIXQueryBuilder.getMeasurementUnits(qvo, "AMIS_ELEMENTS", amisDao);
		
		LOGGER.info("sql: " + sql);

       if(sql!=null)   {
           return sql.toString();
       }
       else {
         return null;
       }
	}
	
   
   private static String addSelectMaxDate(String selectedDataset) {
		StringBuffer sql = new StringBuffer(); 
		sql.append(FENIXQueryBuilder.addSelectMaxDate());
		
		return sql.toString();
	}
   

	/**public static String getMeasurementUnits(AMISQueryVO qvo, AMISDao amisDao) {
		
		String sql = "";
		
		LOGGER.info("selected dataset: " + qvo.getSelectedDataset());
			
		AMISConstants dataset = AMISConstants.valueOf(qvo.getSelectedDataset());
		
		switch (dataset) {
			case PSD: sql = FENIXQueryBuilder.getMeasurementUnits(qvo, "AMIS_ELEMENTS", amisDao); break;
			case FAOSTAT:  sql = AMISFaostatQueryBuilder.getMeasurementUnits(qvo); break;
			case CBS: sql = FENIXQueryBuilder.getMeasurementUnits(qvo, "AMIS_ELEMENTS", amisDao); break;
		}
		
		LOGGER.info("sql: " + sql);
		return sql.toString();
	}
	
	
private static String addSelectMaxDate(String selectedDataset) {
		StringBuffer sql = new StringBuffer(); 
		
       AMISConstants dataset = AMISConstants.valueOf(selectedDataset);
		
		switch (dataset) {
			case PSD: sql.append(FENIXQueryBuilder.addSelectMaxDate()); break;
			case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.addSelectMaxDate()); break;
			case CBS: sql.append(FENIXQueryBuilder.addSelectMaxDate()); break;
		}
			
		return sql.toString();
	}
	**/
   
	private static String addSelectTimeInterval(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer(); 
		sql.append(FENIXQueryBuilder.addTimeIntervalSelects(qvo));
		
       return sql.toString();
	}
	
//	public static String getSelects(AMISQueryVO qvo, Boolean withLimitCheck) {
//		StringBuffer sql = new StringBuffer();   
//
//		sql.append("SELECT ");
//		
//		if ( withLimitCheck )
//			if( qvo.getLimit() != null && qvo.getLimit() != 0) {
//				if(qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT))
//					sql.append(" TOP ").append(qvo.getLimit()).append(" ");
//			}		
//		
//		// add selection
//		for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
//			String select = qvo.getSelects().get(i);
//			
//			if(select.contains("+AMISConstants.defaultLanguage")){
//				select = select.replace("+AMISConstants.defaultLanguage", AMISConstants.defaultLanguage);		
//			}
//			
//			sql.append(select);
//			
//			
//			if (i < qvo.getSelects().size() - 1)
//				sql.append(", ");
//		}
//		
//		return sql.toString();
//	}
//	
	public static String getSelects(AMISQueryVO qvo, Boolean withLimitCheck) {
		StringBuffer sql = new StringBuffer();   

		sql.append("SELECT ");
		
		if ( withLimitCheck )
			if( qvo.getLimit() != null && qvo.getLimit() != 0) {
					sql.append(" MAX ").append(qvo.getLimit()).append(" ");
			}		
		
		// add selection
		for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
			String select = qvo.getSelects().get(i);
			
			if(select.contains("+AMISConstants.defaultLanguage")){
				select = select.replace("+AMISConstants.defaultLanguage", AMISConstants.defaultLanguage);		
			}
			
			/**if(select.contains("AMIS_DATA_TABLE")){
				
				if(AMISController.amisDatasetMap.containsKey(AMISCurrentDatasetView.AMIS.name())) {
					LOGGER.info("AMIS IS IN THE amisDatasetMap !");
					
				} else 
					LOGGER.info("AMIS IS NOT IN THE amisDatasetMap !");
				
				if(AMISController.getTableNameForDataset(AMISCurrentDatasetView.AMIS)!=null)
					select = select.replace("AMIS_DATA_TABLE", AMISController.getTableNameForDataset(AMISCurrentDatasetView.AMIS));		
			}**/
			
			
			sql.append(select);
			
			
			if (i < qvo.getSelects().size() - 1)
				sql.append(", ");
		}
		
		return sql.toString();
	}
	
	public static String getSelectsWithoutLimit(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();   

		sql.append("SELECT ");
		
//		if ( withLimitCheck )
//			if( qvo.getLimit() != null && qvo.getLimit() != 0) {
//					sql.append(" MAX ").append(qvo.getLimit()).append(" ");
//			}		
		
		// add selection
		for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
			String select = qvo.getSelects().get(i);
			
			if(select.contains("+AMISConstants.defaultLanguage")){
				select = select.replace("+AMISConstants.defaultLanguage", AMISConstants.defaultLanguage);		
			}
			
			sql.append(select);
			
			
			if (i < qvo.getSelects().size() - 1)
				sql.append(", ");
		}
		
		return sql.toString();
	}
	private static String getFroms(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();   

		sql.append(" FROM ");
		
		//if(selectedDataset.equals(AMISConstants.FAOSTAT)){
		//	sql.append(" Data D, Element E, Area A, Item I, DomainArea DA ");

	//	} else {
		// add froms
		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
			String from = qvo.getFroms().get(i);
			sql.append(from);
			
			
			if (i < qvo.getFroms().size() - 1)
				sql.append(", ");
		}
		//}
		return sql.toString();
	}
	
	//Class: AMISQueryBuilder Function:getFromsWithMonthPosition Text: i= 0 from amis_all_datasources D
	//Class: AMISQueryBuilder Function:getFromsWithMonthPosition Text: i= 1 from AMIS_ELEMENTS_b320ad05 E
	private static String getFromsWithMonthPosition(AMISQueryVO qvo, boolean isComparePage) {
		StringBuffer sql = new StringBuffer();

		sql.append(" FROM ");

		//if(selectedDataset.equals(AMISConstants.FAOSTAT)){
		//	sql.append(" Data D, Element E, Area A, Item I, DomainArea DA ");

	//	} else {
		// add froms
//		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
//			String from = qvo.getFroms().get(i);
//			sql.append(from);
//
//
//			if (i < qvo.getFroms().size() - 1)
//				sql.append(", ");
//		}
		//}
		/*
		 * (SELECT amis.* FROM amis_all_datasources  amis INNER JOIN (SELECT database, region_code, product_code, element_code, year, MAX(CAST(month_position AS integer)) AS MaxMonthPosition from amis_all_datasources  GROUP BY database, region_code, product_code, element_code, year ) grouppedAmis ON amis.database = grouppedAmis.database AND amis.region_code = grouppedAmis.region_code AND amis.product_code = grouppedAmis.product_code AND amis.element_code = grouppedAmis.element_code AND amis.year = grouppedAmis.year AND CAST(amis.month_position AS integer) = grouppedAmis.MaxMonthPosition)  D
		 * */
		String data_table = qvo.getFroms().get(0);
		String elements_table = qvo.getFroms().get(0);
		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
			String from = qvo.getFroms().get(i);
			//System.out.println("Class: AMISQueryBuilder Function:getFromsWithMonthPosition Text: i= "+i+" from "+from);
		}
//		sql.append("(SELECT amis.* FROM AMIS_DATA_MONTH_75ba84df amis INNER JOIN (SELECT database, region_code, product_code, element_code, year, MAX(CAST(month_position AS integer)) AS MaxMonthPosition from AMIS_DATA_MONTH_75ba84df GROUP BY database, region_code, product_code, element_code, year ) grouppedAmis ON amis.database = grouppedAmis.database AND amis.region_code = grouppedAmis.region_code AND amis.product_code = grouppedAmis.product_code AND amis.element_code = grouppedAmis.element_code AND amis.year = grouppedAmis.year AND CAST(amis.month_position AS integer) = grouppedAmis.MaxMonthPosition) ");
//		sql.append(" D, AMIS_ELEMENTS_b320ad05 E ");
		sql.append("(SELECT amis.* FROM "+data_table+" amis INNER JOIN (SELECT database, region_code, product_code, element_code, year_label, MAX(CAST(month_position AS integer)) AS MaxMonthPosition from "+data_table+" GROUP BY database, region_code, product_code, element_code, year_label ) grouppedAmis ON amis.database = grouppedAmis.database AND amis.region_code = grouppedAmis.region_code AND amis.product_code = grouppedAmis.product_code AND amis.element_code = grouppedAmis.element_code AND amis.year_label = grouppedAmis.year_label AND CAST(amis.month_position AS integer) = grouppedAmis.MaxMonthPosition) ");
		sql.append(" D");


		if(isComparePage)
		{
			sql.append(", "+elements_table+" E ");
		}


		return sql.toString();
	}

    //Year Label Start Changes
    private static String getFromsWithMonthPosition_YearLabel(AMISQueryVO qvo, boolean isComparePage) {
        StringBuffer sql = new StringBuffer();

        sql.append(" FROM ");

        //if(selectedDataset.equals(AMISConstants.FAOSTAT)){
        //	sql.append(" Data D, Element E, Area A, Item I, DomainArea DA ");

        //	} else {
        // add froms
//		for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
//			String from = qvo.getFroms().get(i);
//			sql.append(from);
//
//
//			if (i < qvo.getFroms().size() - 1)
//				sql.append(", ");
//		}
        //}
		/*
		 * (SELECT amis.* FROM amis_all_datasources  amis INNER JOIN (SELECT database, region_code, product_code, element_code, year, MAX(CAST(month_position AS integer)) AS MaxMonthPosition from amis_all_datasources  GROUP BY database, region_code, product_code, element_code, year ) grouppedAmis ON amis.database = grouppedAmis.database AND amis.region_code = grouppedAmis.region_code AND amis.product_code = grouppedAmis.product_code AND amis.element_code = grouppedAmis.element_code AND amis.year = grouppedAmis.year AND CAST(amis.month_position AS integer) = grouppedAmis.MaxMonthPosition)  D
		 * */
        String data_table = qvo.getFroms().get(0);
        String elements_table = qvo.getFroms().get(0);
//        for (int i = 0 ; i < qvo.getFroms().size() ; i++) {
//            String from = qvo.getFroms().get(i);
//            System.out.println("Class: AMISQueryBuilder Function:getFromsWithMonthPosition_YearLabel Text: i= "+i+" from "+from);
//        }
//		sql.append("(SELECT amis.* FROM AMIS_DATA_MONTH_75ba84df amis INNER JOIN (SELECT database, region_code, product_code, element_code, year, MAX(CAST(month_position AS integer)) AS MaxMonthPosition from AMIS_DATA_MONTH_75ba84df GROUP BY database, region_code, product_code, element_code, year ) grouppedAmis ON amis.database = grouppedAmis.database AND amis.region_code = grouppedAmis.region_code AND amis.product_code = grouppedAmis.product_code AND amis.element_code = grouppedAmis.element_code AND amis.year = grouppedAmis.year AND CAST(amis.month_position AS integer) = grouppedAmis.MaxMonthPosition) ");
//		sql.append(" D, AMIS_ELEMENTS_b320ad05 E ");
        sql.append("(SELECT amis.* FROM "+data_table+" amis INNER JOIN (SELECT database, region_code, product_code, element_code, year_label, MAX(CAST(month_position AS integer)) AS MaxMonthPosition from "+data_table+" GROUP BY database, region_code, product_code, element_code, year_label ) grouppedAmis ON amis.database = grouppedAmis.database AND amis.region_code = grouppedAmis.region_code AND amis.product_code = grouppedAmis.product_code AND amis.element_code = grouppedAmis.element_code AND amis.year_label = grouppedAmis.year_label AND CAST(amis.month_position AS integer) = grouppedAmis.MaxMonthPosition) ");
        sql.append(" D");


        if(isComparePage)
        {
            sql.append(", "+elements_table+" E ");
        }

       // System.out.println("Class: AMISQueryBuilder Function:getFromsWithMonthPosition_YearLabel Text: sql.toString()= "+sql.toString());
        return sql.toString();
    }
    //Year Label End Changes
	
	private static String getWheres(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(FENIXQueryBuilder.getWheres(qvo));
		
		/**AMISConstants dataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		
		switch (dataset) {
			case PSD: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
			case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.getWheres(qvo)); break;
			case CBS: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
	    }**/
		
		return sql.toString();
	}

    private static String getWheresYearLabel(AMISQueryVO qvo) {
        StringBuffer sql = new StringBuffer();
        sql.append(FENIXQueryBuilder.getWheresYearLabel(qvo));

        /**AMISConstants dataset = AMISConstants.valueOf( qvo.getSelectedDataset());

         switch (dataset) {
         case PSD: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
         case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.getWheres(qvo)); break;
         case CBS: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
         }**/

        return sql.toString();
    }

    private static String getWheresYearLabelInkey(AMISQueryVO qvo, boolean isTotal) {
        StringBuffer sql = new StringBuffer();
        sql.append(FENIXQueryBuilder.getWheresYearLabelInKey(qvo, isTotal));

        /**AMISConstants dataset = AMISConstants.valueOf( qvo.getSelectedDataset());

         switch (dataset) {
         case PSD: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
         case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.getWheres(qvo)); break;
         case CBS: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
         }**/

        return sql.toString();
    }
	
	private static String getWheresWithMonth(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();
		sql.append(FENIXQueryBuilder.getWheresWithMonth(qvo));
		
		/**AMISConstants dataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		
		switch (dataset) {
			case PSD: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
			case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.getWheres(qvo)); break;
			case CBS: sql.append(FENIXQueryBuilder.getWheres(qvo)); break;
	    }**/
		
		return sql.toString();
	}
	
	protected static String getWheres(AMISQueryVO qvo, AMISTableConstants filterColumn, List<String> filterFields) {
		StringBuffer sql = new StringBuffer();
		 sql.append(FENIXQueryBuilder.getWheres(qvo, filterColumn, filterFields));
	/*	AMISConstants dataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		
		switch (dataset) {
			case PSD: sql.append(FENIXQueryBuilder.getWheres(qvo, filterColumn, filterFields)); break;
			case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.getWheres(qvo, filterColumn, filterFields)); break;
			case CBS: sql.append(FENIXQueryBuilder.getWheres(qvo, filterColumn, filterFields)); break;
	    }*/
		
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
		LOGGER.info("ORDER BY: sql: " + sql);
		
		return sql.toString();
	}
	
	private static String addGroupBYClause(AMISQueryVO qvo, Boolean addUnitName) {
		
		 StringBuffer sql = new StringBuffer();
		 sql.append(" GROUP BY ");
		 
		 if(qvo.getGroupBys()!=null && !qvo.getGroupBys().isEmpty()){
			 for (int i = 0 ; i < qvo.getGroupBys().size() ; i++) {
					sql.append(qvo.getGroupBys().get(i));
					if (i < qvo.getGroupBys().size() - 1)
						sql.append(", ");
				}
		  }
		 else {
		 for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
				sql.append(qvo.getSelects().get(i));
				if (i < qvo.getSelects().size() - 1)
					sql.append(", ");
			}
		 }
		 
		 if ( addUnitName ) {
			 sql.append( ", D.Units ");
		 }

		return sql.toString();
	} 
	
	
	private static String addOrderByMaxDate(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();   
		sql.append(FENIXQueryBuilder.addOrderByMaxDate());
		
     /* AMISConstants dataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		
		switch (dataset) {
			case PSD: sql.append(FENIXQueryBuilder.addOrderByMaxDate()); break;
			case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.addOrderByMaxDate()); break;
			case CBS: sql.append(FENIXQueryBuilder.addOrderByMaxDate()); break;
	    }*/
	
		return sql.toString();
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
    public static String getSplitYears(AMISQueryVO p) {
        String years_codes = "";
        Object[] years = p.getYears().keySet().toArray();
        for (int i = 0 ; i < years.length ; i++) {
            years_codes += "'" + years[i].toString() + "'";
            if (i < years.length - 1)
                years_codes += ", ";
        }
        return years_codes;
    }


    public static String getYearsLabel(AMISQueryVO p) {
        String years_codes = "";
        Object[] years = p.getYears().keySet().toArray();
        for (int i = 0 ; i < years.length ; i++) {
           // years_codes += "'" + years[i].toString() + "'";
            //2000-01-01
            String year_i = years[i].toString();
            //2000
            year_i= year_i.substring(6);
            //2001
            Integer year_i_next = (Integer.parseInt(year_i))+1;
            //2000/01
            String year_label = year_i+"/"+ (""+year_i_next).substring(2);
            //2000-01-01 -> 2000/01
            years_codes += "'" + year_label + "'";
            if (i < years.length - 1)
                years_codes += ", ";
        }
        return years_codes;
    }

    public static String getYearsLabel_fromkeys(AMISQueryVO p) {
        String years_codes = "";
        int i=0;
        for( String k : p.getYears().keySet() )
        {
            String v =  (String)p.getYears().get(k);
            //System.out.println("v="+v);
            years_codes += "'" + v + "'";
            if (i < p.getYears().keySet().size() - 1)
                years_codes += ", ";

            i++;
        }

        System.out.println("years_codes="+years_codes);

        return years_codes;
    }
    //For Supply and demand
    public static String getYearsLabel_storedInkeys(AMISQueryVO p) {
        String years_codes = "";
        int i=0;
        for( String k : p.getYears().keySet() )
        {
            years_codes += "'" + k + "'";
            if (i < p.getYears().keySet().size() - 1)
                years_codes += ", ";

            i++;
        }

        System.out.println("years_codes="+years_codes);

        return years_codes;
    }

    public static String getYears_fromYearLabel(AMISQueryVO p) {
        //2000/01 -> 2000-01-01
        String years_codes = "";
        int i=0;
        for( String k : p.getYears().keySet() )
        {
            String v =  (String)p.getYears().get(k);
            //System.out.println("v="+v);
            years_codes += "'" + v.substring(0,4) + "-01-01'";
            if (i < p.getYears().keySet().size() - 1)
                years_codes += ", ";

            i++;
        }

        System.out.println("years_codes="+years_codes);

        return years_codes;
    }

    //For Supply and demand
    public static String getYears_fromYearLabel_storedInkeys(AMISQueryVO p) {
        //2000/01 -> 2000-01-01
        String years_codes = "";
        int i=0;
        for( String k : p.getYears().keySet() )
        {
            String v =  k;
            //System.out.println("v="+v);
            years_codes += "'" + v.substring(0,4) + "-01-01'";
            if (i < p.getYears().keySet().size() - 1)
                years_codes += ", ";

            i++;
        }

        System.out.println("years_codes="+years_codes);

        return years_codes;
    }
	
	public static String getDatabases(AMISQueryVO p) {
		String databases_codes = "";
		Object[] databases = p.getDatabases().keySet().toArray();
		for (int i = 0 ; i < databases.length ; i++) {
			databases_codes += "'" + databases[i].toString() + "'";
			if (i < databases.length - 1)
				databases_codes += ", ";
		}
		return databases_codes;
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
	
	public static String buildCalculationQuery(AMISQueryVO qvo) {
		StringBuffer sql = new StringBuffer();

		AMISCalculationParametersVO calcVO = qvo.getCalculationParametersVO();

		if(calcVO.getCalculationType().equals(AMISAggregationConstants.PERCENTAGE_SHARE)){
			sql.append("SELECT ");	

			sql.append(buildDivisionQuery(qvo, calcVO.getDividend(), calcVO.getFilterColumn()));
			sql.append(" / ");
			sql.append(buildDivisionQuery(qvo, calcVO.getDivisor(), calcVO.getFilterColumn()));

			sql.append(" AS SHARE");	
		}	 
		
		LOGGER.info("SQL :" + sql );
		return sql.toString();
	}
	
	private static String buildDivisionQuery(AMISQueryVO qvo, List<String> filterValues, AMISTableConstants filterColumn){
		StringBuffer sql = new StringBuffer();
	    sql.append(FENIXQueryBuilder.buildDivisionQuery(qvo, filterValues, filterColumn));
		 
		/*AMISConstants dataset = AMISConstants.valueOf( qvo.getSelectedDataset());
		
		switch (dataset) {
			case PSD: sql.append(FENIXQueryBuilder.buildDivisionQuery(qvo, filterValues, filterColumn)); break;
			case FAOSTAT:  sql.append(AMISFaostatQueryBuilder.buildDivisionQuery(qvo,filterValues,filterColumn)); break;
			case CBS: sql.append(FENIXQueryBuilder.buildDivisionQuery(qvo,filterValues, filterColumn)); break;
		}	*/

		return sql.toString();
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
	
	public static String getAreas(List<String> p) {
		String area_codes = "";
		for (int i = 0 ; i < p.size() ; i++) {
			area_codes += "'" + p.get(i) + "'";
			if (i < p.size() - 1)
				area_codes += ", ";
		}
		return area_codes;
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
	
}