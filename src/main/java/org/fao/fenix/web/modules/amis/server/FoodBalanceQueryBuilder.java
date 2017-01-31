package org.fao.fenix.web.modules.amis.server;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class FoodBalanceQueryBuilder {

	private final static Logger LOGGER = Logger.getLogger(FoodBalanceQueryBuilder.class);

    //Supply, Opening Stocks, Production, imports nmy, total utilization, exports nmy,  closing stocks
    private final static String[] FOOD_BALANCE_ELEMENTS =  {"19", "18", "5", "7", "35", "10", "16"};

    //supply, total utilization
    public static final Set<String> FOOD_BALANCE_ELEMENTS_HIGHLIGHTED = new HashSet<String>(Arrays.asList(
            new String[] {"19", "35"}
    ));

    // ORIGINAL: food use, feed use, other uses, crush, feed and residual, food/seeds/industrial use
   // public static final Set<String> FOOD_BALANCE_ELEMENTS_INDENTED = new HashSet<String>(Arrays.asList(
    //        new String[] {"14", "13", "15", "36", "38", "24"}
   // ));

    // food use, feed use, other uses, crush, feed and residual, food/seeds/industrial use, seeds, industrial use, residual, post harvest losses
    public static final Set<String> FOOD_BALANCE_ELEMENTS_INDENTED = new HashSet<String>(Arrays.asList(
            new String[] {"14", "13", "15", "36", "38", "24", "21", "28", "39", "34" }
    ));

    //seeds, post harvest losses, industrial use
    public static final Set<String> FOOD_BALANCE_ELEMENTS_MORE_INDENTED = new HashSet<String>(Arrays.asList(
            new String[] {"21", "34", "28"}
    ));

    // wheat, maize, soybeans, rice
    public static final Set<String> FOOD_BALANCE_PRODUCTS = new HashSet<String>(Arrays.asList(
            new String[] {"1", "5", "6", "4"}
    ));


    //imports ity, exports ity
    private final static String[] ITY_ELEMENTS =  {"8", "12"};


    public static AMISQueryVO getElementFootnotes(AMISQueryVO qvo, AMISDao amisDao){

        long startTime = System.currentTimeMillis();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_ELEMENTS"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        if(!qvo.getItems().isEmpty()){
            String table1 = sqlResult.get(0).toString();

            qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_FOOD_BALANCE_ELEMENTS_FOOTNOTES"));
            sqlResult = amisDao.getSingleResultValue(qvo.getSql());

            String table2 = sqlResult.get(0).toString();

            qvo.setSql(getFootnotes(table1, table2, qvo));

            List<Object[]> sqlResult2 = amisDao.getResultList(qvo.getSql());
            LinkedHashMap<String, LinkedHashMap<String,  LinkedHashMap<String, LinkedHashMap<String, String>>>> dsMap = new  LinkedHashMap<String, LinkedHashMap<String,  LinkedHashMap<String, LinkedHashMap<String, String>>>>();




            for(int i=0; i < sqlResult2.size(); i++) {
                String database = sqlResult2.get(i)[0].toString();
                String product_code = sqlResult2.get(i)[1].toString();
                String region = sqlResult2.get(i)[2].toString();
                String element_code = sqlResult2.get(i)[3].toString();
                String element_name = sqlResult2.get(i)[4].toString();
                String footnote = sqlResult2.get(i)[5].toString();

                LinkedHashMap<String,  LinkedHashMap<String, LinkedHashMap<String, String>>> map = new LinkedHashMap<String,  LinkedHashMap<String, LinkedHashMap<String, String>>>();
                LinkedHashMap<String,  LinkedHashMap<String, String>> productMap = new LinkedHashMap<String,  LinkedHashMap<String, String>>();
                LinkedHashMap<String, String> elementFootnoteMap = new  LinkedHashMap<String, String>();

                if(dsMap.containsKey(database)) {
                    map = dsMap.get(database);

                    if(map.containsKey(product_code))    {
                        productMap =  map.get(product_code);

                        if(productMap.containsKey(region)){
                            elementFootnoteMap =  productMap.get(region);

                        }
                    }
                }

               /** if(map.containsKey(region)) {
                    productMap =  map.get(region);
                }

                if(productMap.containsKey(product_code)) {
                    elementFootnoteMap =  productMap.get(product_code);
                }  **/

                elementFootnoteMap.put(element_name, footnote);
                productMap.put(region, elementFootnoteMap);
                map.put(product_code,productMap);
                dsMap.put(database, map);

            }

            LOGGER.info("get -- FOOTNOTES -- qvo.getElementFootNotesByCountry() size = " +  dsMap);

            qvo.setElementFootNotesByCountry(dsMap);


        }

        long endTime = System.currentTimeMillis();

        LOGGER.info("get -- FOOD BALANCE -- qvo.getElementFootNotesByCountry() size = " +  qvo.getElementFootNotesByCountry());

        return qvo;
    }


    public static AMISQueryVO getFoodBalanceElements(AMISQueryVO qvo, AMISDao amisDao){

        long startTime = System.currentTimeMillis();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_ELEMENTS"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        if(!qvo.getItems().isEmpty()){
            String table1 = sqlResult.get(0).toString();

            qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_FOOD_BALANCE_DOMESTIC_UTILIZATION_ELEMENTS"));
            sqlResult = amisDao.getSingleResultValue(qvo.getSql());

            String table2 = sqlResult.get(0).toString();


            String productCode = "";
            for(String prod: qvo.getItems().keySet()){
                productCode = prod;
            }

            qvo.setSql( getFoodBalanceElementsQuery(table1, table2, qvo));

            List<Object[]> sqlResult2 = amisDao.getResultList(qvo.getSql());
            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> dsMap = new  LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>();
            LinkedHashMap<String, String> elements = new LinkedHashMap<String, String>();

            for(int i=0; i < sqlResult2.size(); i++) {
                LOGGER.info("0 "+ sqlResult2.get(i)[0].toString() +" || 1 "+ sqlResult2.get(i)[1].toString());
                LOGGER.info("2 "+ sqlResult2.get(i)[2].toString() +" || 3 "+ sqlResult2.get(i)[3].toString());
                LinkedHashMap<String, LinkedHashMap<String, String>> productMap = new  LinkedHashMap<String, LinkedHashMap<String, String>>();
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

                if(dsMap.containsKey(sqlResult2.get(i)[0].toString())){
                    productMap = dsMap.get(sqlResult2.get(i)[0].toString());

                    if(productMap.containsKey(sqlResult2.get(i)[1].toString())) {
                        map = productMap.get(sqlResult2.get(i)[1].toString());
                    }
                }

                map.put(sqlResult2.get(i)[2].toString(),sqlResult2.get(i)[3].toString());
                productMap.put(sqlResult2.get(i)[1].toString(), map);
                dsMap.put(sqlResult2.get(i)[0].toString(),productMap);

                elements.put(sqlResult2.get(i)[2].toString(),sqlResult2.get(i)[3].toString());

               /** if(dsMap.containsKey(sqlResult2.get(i)[0].toString())){
                    productMap = dsMap.get(sqlResult2.get(i)[0].toString());
                }

                if(productMap.containsKey(sqlResult2.get(i)[1].toString())){
                    map = productMap.get(sqlResult2.get(i)[1].toString());
                }

                map.put(sqlResult2.get(i)[2].toString(),sqlResult2.get(i)[3].toString());
                productMap.put(sqlResult2.get(i)[1].toString(), map);
                dsMap.put(sqlResult2.get(i)[0].toString(),productMap);   **/

                LOGGER.info("map "+ map);
                LOGGER.info("productMap "+ productMap);
                LOGGER.info("dsMap "+ dsMap);
            }

            qvo.setElements(elements);
            qvo.setFoodBalanceElements(dsMap);

        }

        long endTime = System.currentTimeMillis();

        LOGGER.info("get -- FOOD BALANCE -- qvo.getFoodBalanceElements() size = " +  qvo.getFoodBalanceElements());

        return qvo;
    }


    public static AMISQueryVO getITYElements(AMISQueryVO qvo, AMISDao amisDao){

        long startTime = System.currentTimeMillis();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_ELEMENTS"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        if(!qvo.getItems().isEmpty()){
            String table1 = sqlResult.get(0).toString();

            qvo.setSql(getITYElementsQuery(table1, qvo));

            List<Object[]> sqlResult2 = amisDao.getResultList(qvo.getSql());
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

            for(int i=0; i < sqlResult2.size(); i++) {
                map.put(sqlResult2.get(i)[0].toString(),sqlResult2.get(i)[1].toString());
            }
            qvo.setElements(map);
            qvo.setItyElements(map);

        }

        long endTime = System.currentTimeMillis();

        LOGGER.info("get -- ELEMENTS -- qvo.getElements() size = " +  qvo.getElements());
        LOGGER.info("get -- ITY ELEMENTS -- qvo.getItyElements() size = " +  qvo.getItyElements());

        return qvo;
    }


    public static AMISQueryVO getMarketingAndTradeYear(AMISQueryVO qvo, AMISDao amisDao){
       //country,  LinkedList<String> [nmy, nmy_note_base_year, nmy_note_period, harvesting_note_period]

        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String,  LinkedList<String>>>> marketingYearsByDatasource = new  LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String,  LinkedList<String>>>>();

        //country,  LinkedList<String> [ity, ity_note_base_year, ity_note_period]
        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String,  LinkedList<String>>>> tradeYearsByDatasource = new  LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String,  LinkedList<String>>>>();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_MARKET_TRADE_YEAR"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        if(!qvo.getItems().isEmpty()){
            String table = sqlResult.get(0).toString();

            qvo.setSql(getMarketingAndTradeYearQuery(qvo, table));

            List<Object[]> sqlResult2 = amisDao.getResultList(qvo.getSql());
            LOGGER.info("sqlResult2 size = "+sqlResult2.size());
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

            //  Resulting columns are database, productcode, region_name, national_marketing_year, international_trade_year,  n1.note as nmy_note,  n2.note as ity_note
            for(int i=0; i < sqlResult2.size(); i++) {

                LinkedList<String> nmy_values = new LinkedList<String>();
                LinkedList<String> ity_values = new LinkedList<String>();

                String note_base_year = "";

                String nmy = "n.a";
                String nmy_note_period = "";
                String nmy_harvesting_period = "";

                String ity = "n.a";
                String ity_note_period = "";

                if(sqlResult2.get(i)[3]!=null){
                    nmy =  sqlResult2.get(i)[3].toString();
                }

                if(sqlResult2.get(i)[4]!=null){
                    note_base_year =  sqlResult2.get(i)[4].toString();
                }

                if(sqlResult2.get(i)[5]!=null){
                    nmy_note_period =  sqlResult2.get(i)[5].toString();
                }
                if(sqlResult2.get(i)[6]!=null){
                    ity =  sqlResult2.get(i)[6].toString();
                }

                if(sqlResult2.get(i)[7]!=null){
                    ity_note_period =  sqlResult2.get(i)[7].toString();
                }

                if(sqlResult2.get(i)[8]!=null){
                    nmy_harvesting_period =  sqlResult2.get(i)[8].toString();
                }

                nmy_values.add(nmy);
                nmy_values.add(note_base_year);
                nmy_values.add(nmy_note_period);
                nmy_values.add(nmy_harvesting_period);

                ity_values.add(ity);
                ity_values.add(note_base_year);
                ity_values.add(ity_note_period);

                LinkedHashMap<String, LinkedHashMap<String,  LinkedList<String>>> marketingYearsByProduct = new LinkedHashMap<String, LinkedHashMap<String, LinkedList<String>>>();
                LinkedHashMap<String, LinkedList<String>> marketingYearsByCountry = new LinkedHashMap<String, LinkedList<String>>();

                LOGGER.info("marketingYearsByDatasource "+marketingYearsByDatasource);
                LOGGER.info("tradeYearsByDatasource "+tradeYearsByDatasource);

                if(marketingYearsByDatasource.containsKey(sqlResult2.get(i)[0].toString())){
                    marketingYearsByProduct =  marketingYearsByDatasource.get(sqlResult2.get(i)[0].toString());

                    if(marketingYearsByProduct.containsKey(sqlResult2.get(i)[1].toString())){
                        marketingYearsByCountry =  marketingYearsByProduct.get(sqlResult2.get(i)[1].toString());
                    }

                }

                //if(marketingYearsByProduct.containsKey(sqlResult2.get(i)[1].toString())){
                 //   marketingYearsByCountry =  marketingYearsByProduct.get(sqlResult2.get(i)[1].toString());
              //  }
                LinkedHashMap<String, LinkedHashMap<String,  LinkedList<String>>> tradeYearsByProduct = new LinkedHashMap<String, LinkedHashMap<String, LinkedList<String>>>();
                LinkedHashMap<String,  LinkedList<String>> tradeYearsByCountry = new LinkedHashMap<String,  LinkedList<String>>();

                if(tradeYearsByDatasource.containsKey(sqlResult2.get(i)[0].toString())){
                    tradeYearsByProduct = tradeYearsByDatasource.get(sqlResult2.get(i)[0].toString());

                    if(tradeYearsByProduct.containsKey(sqlResult2.get(i)[1].toString())){
                        tradeYearsByCountry =  tradeYearsByProduct.get(sqlResult2.get(i)[1].toString());
                    }

                } //else {
                  //  tradeYearsByDatasource.put(sqlResult2.get(i)[0].toString(), new LinkedHashMap<String, LinkedHashMap<String, LinkedList<String>>>());
               // }

                //if(tradeYearsByProduct.containsKey(sqlResult2.get(i)[1].toString())){
                 //   tradeYearsByCountry =  tradeYearsByProduct.get(sqlResult2.get(i)[1].toString());
               // } //else {
                   // tradeYearsByProduct.put(sqlResult2.get(i)[1].toString(), new LinkedHashMap<String, LinkedList<String>>());
               // }

                marketingYearsByCountry.put(sqlResult2.get(i)[2].toString(), nmy_values);
                marketingYearsByProduct.put(sqlResult2.get(i)[1].toString(), marketingYearsByCountry);
                marketingYearsByDatasource.put(sqlResult2.get(i)[0].toString(), marketingYearsByProduct);

                tradeYearsByCountry.put(sqlResult2.get(i)[2].toString(), ity_values);
                tradeYearsByProduct.put(sqlResult2.get(i)[1].toString(), tradeYearsByCountry);
                tradeYearsByDatasource.put(sqlResult2.get(i)[0].toString(), tradeYearsByProduct);

            }

           // qvo.setCountriesNationalMarketingYear(marketingYearsByCountry);
            //qvo.setCountriesInternationalTradeYear(tradeYearsByCountry);

            LOGGER.info("marketingYearsByDatasource "+marketingYearsByDatasource);
            LOGGER.info("tradeYearsByDatasource "+tradeYearsByDatasource);

             qvo.setCountriesNationalMarketingYear(marketingYearsByDatasource);
             qvo.setCountriesInternationalTradeYear(tradeYearsByDatasource);


        }

        return qvo;
    }


    public static AMISQueryVO getPopulationValues(AMISQueryVO qvo, AMISDao amisDao) {
        Map<String, String> originalItemsMap = qvo.getItems();
        Map<String, String> originalElementsMap = qvo.getElements();
        List<String> selectsList = qvo.getSelects();

        Map<String, String> populationElementMap = new HashMap<String, String>();
        populationElementMap.put("1", "1");

        List<String> selects = new ArrayList<String>();
        selects.add("D.database");
        selects.add("D.region_name");
        selects.add("D.element_code");
        selects.add("D.year_label");
        selects.add("ROUND(CAST(D.value as numeric), 1) ");

        qvo.setSelects(selects);

        qvo.setElements(populationElementMap);
        qvo.setItems(new HashMap<String, String>());

        qvo.setSql(getPopulationValuesQuery(qvo, amisDao));
        List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());


        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>>> populationValuesByDatasource = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>>>();

        LOGGER.info("sqlResult2 size = " + sqlResult.size());
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        //  Resulting columns are  "CBS";"Argentina";"Population";"1";"2001/02";37318.0
        for (int i = 0; i < sqlResult.size(); i++) {

            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>> populationByCountry = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Double>>>();
            LinkedHashMap<String, LinkedHashMap<String, Double>> populationByElement = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
            LinkedHashMap<String, Double> populationByYears = new LinkedHashMap<String, Double>();


            if (populationValuesByDatasource.containsKey(sqlResult.get(i)[0].toString())) {
                populationByCountry = populationValuesByDatasource.get(sqlResult.get(i)[0].toString());

                if (populationByCountry.containsKey(sqlResult.get(i)[1].toString())) {
                    populationByElement = populationByCountry.get(sqlResult.get(i)[1].toString());

                    if (populationByElement.containsKey(sqlResult.get(i)[2].toString())) {
                        populationByYears = populationByElement.get(sqlResult.get(i)[2].toString());
                    }
                }

            }

            Double val = null;

//            if(sqlResult.get(i)[4].toString()!=null){
//                val = Double.valueOf(sqlResult.get(i)[4].toString());
//            }
            if((sqlResult.get(i)[4]!=null)&&(sqlResult.get(i)[4].toString()!=null)){
                val = Double.valueOf(sqlResult.get(i)[4].toString());
            }

            populationByYears.put(sqlResult.get(i)[3].toString(), val);
            populationByElement.put(sqlResult.get(i)[2].toString(), populationByYears);
            populationByCountry.put(sqlResult.get(i)[1].toString(), populationByElement);
            populationValuesByDatasource.put(sqlResult.get(i)[0].toString(), populationByCountry);

           }
            LOGGER.info("populationValuesByDatasource " + populationValuesByDatasource);

            qvo.setPopulationValues(populationValuesByDatasource);

            //Reset values back to original
            qvo.setElements(originalElementsMap);
            qvo.setItems(originalItemsMap);
            qvo.setSelects(selectsList);

        return qvo;
    }

    public static AMISQueryVO getBaseYears(AMISQueryVO qvo, AMISDao amisDao){

           // qvo.setSql(getCBSBaseYearsQuery());
            qvo.setSql(getCBSBaseYearsLabelQuery());
            List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            for(int i=0; i < sqlResult.size(); i++) {
                map.put(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString());
            }

            qvo.setYears(map);

        LOGGER.info("get -- BASE YEARS -- getBaseYears size = " +  qvo.getYears().size());

        return qvo;
    }


    public static AMISQueryVO getCountryBaseYears(AMISQueryVO qvo, AMISDao amisDao){


        if(qvo.getxLabel().equals("COUNTRY") || qvo.getxLabel().equals("DATASOURCE")){
            qvo.setSql(getCountryBaseYearsLabelQuery(qvo));
        } else if(qvo.getxLabel().equals("PRODUCT")){
            qvo.setSql(getProductBaseYearsLabelQuery(qvo));
        }

        List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
        LinkedHashMap<String, String> yearMap = new LinkedHashMap<String, String>();
        LinkedHashMap<String, LinkedHashMap<String, String>> countryYearMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

        for(int i=0; i < sqlResult.size(); i++) {
            if (countryYearMap.containsKey(sqlResult.get(i)[3].toString())) {   // region_name
                yearMap = countryYearMap.get(sqlResult.get(i)[3].toString());
            }

            yearMap.put(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString());
            countryYearMap.put(sqlResult.get(i)[3].toString(), yearMap);
        }

        qvo.setCountryDates(countryYearMap);

        //LOGGER.info("get -- getCountryBaseYears -- size = " +  qvo.getYears().size());

        return qvo;
    }



    private static String  getCBSBaseYearsQuery() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DISTINCT(year), year_label ");
        sql.append("FROM amis_all_datasources " );
        sql.append("WHERE ");
        sql.append("year >= (SELECT MAX(year) - interval '12 year'  from amis_all_datasources WHERE database='CBS') ");
        sql.append("AND year <= (SELECT MAX(year) from amis_all_datasources WHERE database='CBS') ");
        sql.append("GROUP BY year, year_label ");
        sql.append("ORDER BY year_label ");

        return sql.toString();
    }

    //There is no change in the where clause because it's using Cbs data where 2001/02 refers always to 2001
    private static String  getCBSBaseYearsLabelQuery() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DISTINCT(year_label), year ");
        sql.append("FROM amis_all_datasources " );
        sql.append("WHERE ");
        sql.append("year >= (SELECT MAX(year) - interval '12 year'  from amis_all_datasources WHERE database='CBS') ");
        sql.append("AND year <= (SELECT MAX(year) from amis_all_datasources WHERE database='CBS') ");
        sql.append("GROUP BY year_label, year ");
        sql.append("ORDER BY year_label ");

        return sql.toString();
    }

    //There is no change in the where clause because it's using Cbs data where 2001/02 refers always to 2001
    private static String  getCountryBaseYearsLabelQuery(AMISQueryVO qvo) {
        if(qvo.getxLabel().equals("COUNTRY")){
            System.out.println("========== BY COUNTRY: areas = "+qvo.getAreas());
            System.out.println("========== BY COUNTRY: items = "+qvo.getItems());
            System.out.println("========== BY COUNTRY: databases = "+qvo.getDatabases());
        } else if(qvo.getxLabel().equals("PRODUCT")){
            System.out.println("========== BY PRODUCT: areas = "+qvo.getAreas());
            System.out.println("========== BY PRODUCT: items = "+qvo.getItems());
            System.out.println("========== BY PRODUCT: databases = "+qvo.getDatabases());
        }  else if(qvo.getxLabel().equals("DATASOURCE")){
            System.out.println("========== BY DATASOURCE: areas = "+qvo.getAreas());
            System.out.println("========== BY DATASOURCE: items = "+qvo.getItems());
            System.out.println("========== BY DATASOURCE: databases = "+qvo.getDatabases());
        }

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT year_label, year, region_code, region_name ");
        sql.append("FROM amis_all_datasources " );
        sql.append("WHERE ");
        sql.append("year >= (SELECT MAX(year) - interval '12 year' from amis_all_datasources WHERE ");
        sql.append("database IN (");
        int i = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (i <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append(" AND region_code IN (");
        int j = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (j <  qvo.getAreas().size() - 1)
                sql.append(", ");

            j++;
        }
        sql.append(") ");
        sql.append(" AND product_code IN (");
        int k = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (k <  qvo.getItems().size() - 1)
                sql.append(", ");

            k++;
        }
        sql.append(") ");
        sql.append(") ");
        sql.append("AND year <= (SELECT MAX(year) from amis_all_datasources WHERE ");
        sql.append("database IN (");
        int l = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (l <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            l++;
        }
        sql.append(") ");
        sql.append(" AND region_code IN (");
        int m = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (m <  qvo.getAreas().size() - 1)
                sql.append(", ");

            m++;
        }
        sql.append(") ");
        sql.append(" AND product_code IN (");
        int n = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (n <  qvo.getItems().size() - 1)
                sql.append(", ");

            n++;
        }
        sql.append(") ");
        sql.append(") ");
        sql.append(" AND database IN (");
        int p = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (p <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            p++;
        }
        sql.append(") ");
        sql.append(" AND region_code IN (");
        int q = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (q <  qvo.getAreas().size() - 1)
                sql.append(", ");

            q++;
        }
        sql.append(") ");
        sql.append(" AND product_code IN (");
        int r = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (r <  qvo.getItems().size() - 1)
                sql.append(", ");

            r++;
        }
        sql.append(") ");
        sql.append("GROUP BY year_label, year, region_code, region_name ");
        sql.append("ORDER BY year_label ");

        return sql.toString();
    }


    //There is no change in the where clause because it's using Cbs data where 2001/02 refers always to 2001
    private static String  getProductBaseYearsLabelQuery(AMISQueryVO qvo) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT year_label, year, product_code, product_name ");
        sql.append("FROM amis_all_datasources " );
        sql.append("WHERE ");
        sql.append("year >= (SELECT MAX(year) - interval '12 year' from amis_all_datasources WHERE ");
        sql.append("database IN (");
        int i = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (i <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append(" AND region_code IN (");
        int j = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (j <  qvo.getAreas().size() - 1)
                sql.append(", ");

            j++;
        }
        sql.append(") ");
        sql.append(" AND product_code IN (");
        int k = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (k <  qvo.getItems().size() - 1)
                sql.append(", ");

            k++;
        }
        sql.append(") ");
        sql.append(") ");
        sql.append("AND year <= (SELECT MAX(year) from amis_all_datasources WHERE ");
        sql.append("database IN (");
        int l = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (l <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            l++;
        }
        sql.append(") ");
        sql.append(" AND region_code IN (");
        int m = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (m <  qvo.getAreas().size() - 1)
                sql.append(", ");

            m++;
        }
        sql.append(") ");
        sql.append(" AND product_code IN (");
        int n = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (n <  qvo.getItems().size() - 1)
                sql.append(", ");

            n++;
        }
        sql.append(") ");
        sql.append(") ");
        sql.append(" AND database IN (");
        int p = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (p <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            p++;
        }
        sql.append(") ");
        sql.append(" AND region_code IN (");
        int q = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (q <  qvo.getAreas().size() - 1)
                sql.append(", ");

            q++;
        }
        sql.append(") ");
        sql.append(" AND product_code IN (");
        int r = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (r <  qvo.getItems().size() - 1)
                sql.append(", ");

            r++;
        }
        sql.append(") ");
        sql.append("GROUP BY year_label, year, product_code, product_name ");
        sql.append("ORDER BY year_label ");

        return sql.toString();
    }

    private static String  getFoodBalanceElementsQuery(String table, String table2,  AMISQueryVO qvo) {

        /**SELECT database, du.product_code, e.element_code, e.element_name || ' - ' || units,  CAST(e.element_order as integer) as element_order
        FROM AMIS_DOMESTIC_UTILIZATION_AND_SUB_ELEMENTS_DATA_e69bbdbb du, AMIS_ELEMENTS_012e7dc8 e
        WHERE database IN ('CBS')  AND product_code IN ('5') AND
        e.element_code = du.element_code
        UNION ALL
        SELECT 'CBS' as database, '5' as product_code, e.element_code, e.element_name || ' - ' || e.units,  CAST(e.element_order as integer) as element_order
        FROM AMIS_ELEMENTS_012e7dc8 e
        WHERE e.element_code IN ('19', '18', '5', '7', '35', '10', '16')    **/

        StringBuffer sql = new StringBuffer();

        int i = 0;
        int p = 0;
        for(String productCode:  qvo.getItems().keySet()) {

         for(String databaseCode:  qvo.getDatabases().keySet()) {

            sql.append("SELECT database, du.product_code, e.element_code, e.element_name || ' - ' || units,  CAST(e.element_order as integer) as element_order ");
            sql.append("FROM "+table2+" du, "+table+" e ");
            sql.append("WHERE database IN (");
            sql.append("'"+databaseCode+"'");
            sql.append(") ");
            sql.append("AND product_code IN (");
            sql.append("'"+productCode+"'");
            sql.append(") ");
            sql.append("AND du.element_code=e.element_code ");
            sql.append("UNION ALL ");
            sql.append("SELECT '"+databaseCode+"' as database, '"+productCode+"' as product_code, ");
            sql.append("e.element_code, e.element_name || ' - ' || e.units,  CAST(e.element_order as integer) as element_order ");
            sql.append("FROM "+table+" e ");
            sql.append("WHERE ");
            sql.append("element_code IN (");
            int j = 0;

            String [] codes = FOOD_BALANCE_ELEMENTS;

            for(String code: codes) {
                sql.append("'"+code+"'");

                if (j < codes.length - 1)
                    sql.append(", ");

                j++;
            }
            sql.append(") ");

            if (i <  qvo.getDatabases().size() - 1)  {
                sql.append(" UNION ALL ");
            }
            i++;
          }
            if(p <  qvo.getItems().size() - 1){
                sql.append(" UNION ALL ");
            }
           p++;
        }
        sql.append(" ORDER BY database, product_code, element_order ");

        return sql.toString();
    }



    private static String  getITYElementsQuery(String table,  AMISQueryVO qvo) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT element_code, element_name || ' - ' || units ");
        sql.append("FROM "+table+" " );
        sql.append("WHERE ");
        sql.append("element_code IN (");
        int i = 0;

        String [] codes = ITY_ELEMENTS;

        for(String code: codes) {
            sql.append("'"+code+"'");

            if (i < codes.length - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        //sql.append("OR element_code IN (");
       // sql.append(getElementsByProductAndDataSource(table2, qvo));
       // sql.append(") ");

        sql.append("ORDER BY CAST(element_order as integer) ");

        return sql.toString();
    }

    public static String getFoodBalanceProductsQuery(AMISQueryVO qvo, String tableName) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT product_code, product_name ");
        sql.append("FROM "+tableName+" " );
        sql.append("WHERE ");
        sql.append("product_code IN (");
        int i = 0;
        for(String code: FOOD_BALANCE_PRODUCTS) {
            sql.append("'"+code+"'");

            if (i < FOOD_BALANCE_PRODUCTS.size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append("ORDER BY product_name ");


        return sql.toString();
    }

    public static String getFoodBalanceCountriesQuery(AMISQueryVO qvo, String tableName) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DISTINCT(region_code), region_name ");
        sql.append("FROM "+tableName+" " );
        sql.append("WHERE ");
        sql.append("database IN (");
        int i = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (i <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append("ORDER BY region_name ");


        LOGGER.info("sql  = " + sql.toString());
        return sql.toString();
    }

    public static String getMarketingAndTradeYearQuery(AMISQueryVO qvo, String table) {

        /**
         * SELECT national_marketing_year, MAX( EXTRACT(year from year))-1 ||'/'|| substring(CAST((MAX(EXTRACT(year from year))-1)+1 as varchar), 3)  as base_date,
         CASE WHEN nmy_starting_year = '0'
         THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' ||  MAX( EXTRACT(year from year))-1  || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' ||  (MAX( EXTRACT(year from year))-1)+1
         WHEN nmy_starting_year = '1'
         THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' ||   (MAX( EXTRACT(year from year))-1)+1 || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || (MAX( EXTRACT(year from year))-1)+2
         END AS nmy_period,
         international_trade_year,
         CASE WHEN ity_starting_year = '0'
         THEN (regexp_split_to_array(international_trade_year, '/'))[1] || ' ' ||  MAX( EXTRACT(year from year))-1  || ' to ' || (regexp_split_to_array(international_trade_year, '/'))[2] || ' ' ||  (MAX( EXTRACT(year from year))-1)+1
         WHEN ity_starting_year = '1'
         THEN (regexp_split_to_array(international_trade_year, '/'))[1] || ' ' ||   (MAX( EXTRACT(year from year))-1)+1 || ' to ' || (regexp_split_to_array(international_trade_year, '/'))[2] || ' ' || (MAX( EXTRACT(year from year))-1)+2
         END AS ity_period,
         CASE WHEN beginning_of_harvest_starting_year = '0' AND end_of_harvest_starting_year = '0' THEN beginning_of_harvest || ' ' ||  MAX( EXTRACT(year from year))-1  || ' and ' || end_of_harvest || ' ' ||  MAX( EXTRACT(year from year))-1
         WHEN beginning_of_harvest_starting_year = '0' AND end_of_harvest_starting_year = '1' THEN beginning_of_harvest || ' ' ||  MAX( EXTRACT(year from year))-1  || ' and ' || end_of_harvest || ' ' ||  (MAX( EXTRACT(year from year))-1)+1
         END AS nmy_harvesting_period
         from AMIS_NMY_AND_ITY_DATA_775f1a2c n, amis_all_datasources d
         where n.database='CBS'
         and n.region_code IN ('12')
         and n.product_code IN ('1', '4', '5', '6')
         and n.database=d.database
         and n.region_code=d.region_code
         and n.product_code=d.product_code
         GROUP BY n.region_name, national_marketing_year, nmy_starting_year, international_trade_year, ity_starting_year, beginning_of_harvest_starting_year, end_of_harvest_starting_year, beginning_of_harvest, end_of_harvest;
         */

        String maxQuery = "(SELECT MAX(EXTRACT(year from year))-1 FROM amis_all_datasources WHERE database='CBS')";

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("n.database, n.product_code, n.region_name, ");
        sql.append("CASE WHEN national_marketing_year is null ");
        sql.append("THEN '' ");
        sql.append("ELSE national_marketing_year ");
        sql.append("END AS national_marketing_year, ");
        sql.append(maxQuery+" ||'/'|| substring(CAST("+maxQuery+"+1 as varchar), 3)  as base_date, ");
        // sql.append("CASE WHEN nmy_starting_year = '0' AND beginning_of_harvest_starting_year is not null ");
        //sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' ||  "+maxQuery+"  || ' and ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+1 ");
        //sql.append("WHEN nmy_starting_year = '0' AND beginning_of_harvest_starting_year is null  ");
        //sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+"  || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' ||  "+maxQuery+"+1 ");
        //sql.append("WHEN nmy_starting_year = '1' AND beginning_of_harvest_starting_year is not null ");
        //sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' ||   "+maxQuery+"+1 || ' and ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+2 ");
        //sql.append("WHEN nmy_starting_year = '1' AND beginning_of_harvest_starting_year is null   ");
        //sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' ||   "+maxQuery+"+1 || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+2 ");
        //sql.append("END AS nmy_period, ");
        sql.append("CASE WHEN nmy_starting_year is null ");
        sql.append("THEN '' ");
        sql.append("ELSE( ");
        sql.append("CASE WHEN nmy_starting_year = '0' AND nmy_finishing_year = '1' ");
        sql.append("THEN ");
        sql.append("(CASE WHEN beginning_of_harvest_starting_year is not null " );
        sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+" || ' and ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+1 ");
        sql.append("ELSE (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+" || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+1 ");
        sql.append("END) " );
        sql.append("WHEN nmy_starting_year = '0' AND nmy_finishing_year = '0' ");
        sql.append("THEN ");
        sql.append("(CASE WHEN beginning_of_harvest_starting_year is not null " );
        sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+" || ' and ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+" ");
        sql.append("ELSE (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+" || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+" ");
        sql.append("END) " );
        sql.append("WHEN nmy_starting_year = '1' AND nmy_finishing_year = '1' ");
        sql.append("THEN ");
        sql.append("(CASE WHEN beginning_of_harvest_starting_year is not null " );
        sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+"+1 || ' and ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+1 ");
        sql.append("ELSE (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+"+1 || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+1 ");
        sql.append("END) " );
        sql.append("WHEN nmy_starting_year = '1' AND nmy_finishing_year = '2' ");
        sql.append("THEN ");
        sql.append("(CASE WHEN beginning_of_harvest_starting_year is not null " );
        sql.append("THEN (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+"+1 || ' and ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+2 ");
        sql.append("ELSE (regexp_split_to_array(national_marketing_year, '/'))[1] || ' ' || "+maxQuery+"+1 || ' to ' || (regexp_split_to_array(national_marketing_year, '/'))[2] || ' ' || "+maxQuery+"+2 ");
        sql.append("END) ");
        sql.append("END) ");
        sql.append("END AS nmy_period, ");
        sql.append("international_trade_year, ");
        sql.append("CASE WHEN ity_starting_year = '0' AND ity_finishing_year = '1' ");
        sql.append("THEN (regexp_split_to_array(international_trade_year, '/'))[1] || ' ' || "+maxQuery+" || ' to ' || (regexp_split_to_array(international_trade_year, '/'))[2] || ' ' || "+maxQuery+"+1 ");
        sql.append("WHEN ity_starting_year = '1' AND ity_finishing_year = '1' ");
        sql.append("THEN (regexp_split_to_array(international_trade_year, '/'))[1] || ' ' || "+maxQuery+"+1 || ' to ' || (regexp_split_to_array(international_trade_year, '/'))[2] || ' ' || "+maxQuery+"+1 ");
        sql.append("END AS ity_period, ");
        sql.append("CASE WHEN beginning_of_harvest_starting_year = '0' AND end_of_harvest_starting_year = '0' " );
        sql.append("THEN ");
        sql.append("(CASE WHEN beginning_of_harvest = end_of_harvest " );
        sql.append("THEN beginning_of_harvest || ' ' || "+maxQuery+ " ");
        sql.append("ELSE beginning_of_harvest || ' ' || "+maxQuery+"  || ' to ' || end_of_harvest || ' ' ||  "+maxQuery+" ");
        sql.append("END) " );
        sql.append("WHEN beginning_of_harvest_starting_year = '0' AND end_of_harvest_starting_year = '1' ");
        sql.append("THEN beginning_of_harvest || ' ' || "+maxQuery+"  || ' to ' || end_of_harvest || ' ' || "+maxQuery+"+1 ");
        sql.append("WHEN beginning_of_harvest_starting_year = '1' AND end_of_harvest_starting_year = '1' ");
        sql.append("THEN beginning_of_harvest || ' ' || "+maxQuery+"+1  || ' to ' || end_of_harvest || ' ' || "+maxQuery+"+1 ");
        sql.append("WHEN beginning_of_harvest_starting_year = '-1' AND end_of_harvest_starting_year = '0' ");
        sql.append("THEN beginning_of_harvest || ' ' || "+maxQuery+"-1  || ' to ' || end_of_harvest || ' ' || "+maxQuery+" ");
        sql.append("END AS nmy_harvesting_period  ");
        sql.append("FROM "+table+" n, amis_all_datasources d " );
        sql.append("WHERE ");
        sql.append("n.database IN (");
        int i = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (i <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append(" AND ");
        sql.append("n.product_code IN (");
        int j = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (j <  qvo.getItems().size() - 1)
                sql.append(", ");

            j++;
        }
        sql.append(") ");
        sql.append(" AND ");
        sql.append("n.region_code IN (");
        int m = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (m <  qvo.getAreas().size() - 1)
                sql.append(", ");

            m++;
        }
        sql.append(") ");
        sql.append("AND n.database=d.database ");
        sql.append("AND n.region_code=d.region_code ");
        sql.append("AND n.product_code=d.product_code ");
        sql.append("GROUP BY n.database, n.product_code, n.region_name, national_marketing_year, nmy_starting_year, nmy_finishing_year, international_trade_year, ity_starting_year, ity_finishing_year, beginning_of_harvest_starting_year, end_of_harvest_starting_year, beginning_of_harvest, end_of_harvest ");


        return sql.toString();
    }


    public static String getPopulationValuesQuery(AMISQueryVO qvo, AMISDao amisDao) {
//         return FENIXQueryBuilder.sqlTableQuerySupplyAndDemand(qvo, true, amisDao);
        //System.out.println("In getPopulationValuesQuery ... before call sqlTableQuerySupplyAndDemand");
        return FENIXQueryBuilder.sqlTableQuerySupplyAndDemand_population(qvo, true, amisDao);
    }


    public static AMISQueryVO getOtherFoodBalanceElements(AMISQueryVO qvo, AMISDao amisDao){


        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_ELEMENTS"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        if(!qvo.getItems().isEmpty()){

            String table1 = sqlResult.get(0).toString() + " e"; //with alias
          //  String productCode = "";
           // for(String prod: qvo.getItems().keySet()){
           //     productCode = prod;
          //  }

            qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_FOOD_BALANCE_OTHER_TABLE_ELEMENTS"));
            sqlResult = amisDao.getSingleResultValue(qvo.getSql());

            String table2 = sqlResult.get(0).toString();

            qvo.setSql(getOtherFoodBalanceElementsQuery(table1, table2, qvo));
            List<Object[]> sqlResult2 = amisDao.getResultList(qvo.getSql());

            LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> dsMap = new  LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>();

            LinkedHashMap<String, String> elements = new LinkedHashMap<String, String>();

            for(int i=0; i < sqlResult2.size(); i++) {
                LOGGER.info("0 "+ sqlResult2.get(i)[0].toString() +" || 1 "+ sqlResult2.get(i)[1].toString());
                LOGGER.info("2 "+ sqlResult2.get(i)[2].toString() +" || 3 "+ sqlResult2.get(i)[3].toString());
                LinkedHashMap<String, LinkedHashMap<String, String>> productMap = new  LinkedHashMap<String, LinkedHashMap<String, String>>();
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

                if(dsMap.containsKey(sqlResult2.get(i)[0].toString())){
                    productMap = dsMap.get(sqlResult2.get(i)[0].toString());

                    if(productMap.containsKey(sqlResult2.get(i)[1].toString())) {
                        map = productMap.get(sqlResult2.get(i)[1].toString());
                    }
                }

                map.put(sqlResult2.get(i)[2].toString(),sqlResult2.get(i)[3].toString());
                productMap.put(sqlResult2.get(i)[1].toString(), map);
                dsMap.put(sqlResult2.get(i)[0].toString(),productMap);

                elements.put(sqlResult2.get(i)[2].toString(),sqlResult2.get(i)[3].toString());


              /**  if(dsMap.containsKey(sqlResult2.get(i)[0].toString())){
                    productMap = dsMap.get(sqlResult2.get(i)[0].toString());
                } else {
                    dsMap.put(sqlResult2.get(i)[0].toString(), new LinkedHashMap<String, LinkedHashMap<String, String>>());
                }

                if(productMap.containsKey(sqlResult2.get(i)[1].toString())){
                    map = productMap.get(sqlResult2.get(i)[1].toString());
                } else {
                    productMap.put(sqlResult2.get(i)[1].toString(), new LinkedHashMap<String, String>());
                }

                map.put(sqlResult2.get(i)[2].toString(),sqlResult2.get(i)[3].toString()); **/
            }

            qvo.setElements(elements);
            qvo.setOtherFoodBalanceElements(dsMap);

        }

         LOGGER.info("get -- OTHER -- qvo.getOtherFoodBalanceElements() size = " +  qvo.getOtherFoodBalanceElements());
        return qvo;
    }


    public static String  getOtherFoodBalanceElementsQuery(String table, String table2,  AMISQueryVO qvo) {

        StringBuffer sql = new StringBuffer();

                sql.append("SELECT database, du.product_code, e.element_code, e.element_name || ' - ' || units,  CAST(e.element_order as integer) as element_order ");
                sql.append("FROM "+table2+" du, "+table+" ");
                sql.append("WHERE database IN (");
                int j = 0;
                for(String code:  qvo.getDatabases().keySet()) {
                    sql.append("'"+code+"'");

                    if (j <  qvo.getDatabases().size() - 1)
                        sql.append(", ");

                    j++;
                }
                sql.append(") ");
                sql.append("AND product_code IN (");
                int i = 0;
                for(String code:  qvo.getItems().keySet()) {
                    sql.append("'"+code+"'");

                    if (i <  qvo.getItems().size() - 1)
                        sql.append(", ");

                    i++;
                }
                sql.append(") ");
                sql.append(" AND du.element_code=e.element_code ");
                sql.append(" ORDER BY database, product_code, element_order ");
        /** StringBuffer sql = new StringBuffer();
        sql.append("SELECT database, product_code, element_code, element_name || ' - ' || units ");
        sql.append("FROM "+table+" " );
        sql.append("WHERE ");
        sql.append("element_code IN (");
        sql.append(getElementsByProductAndDataSource(table2, qvo));
        sql.append(") ");

        sql.append("ORDER BY CAST(element_order as integer) ");  **/
         //System.out.println("getOtherFoodBalanceElementsQuery "+sql.toString());
        return sql.toString();
    }

   /** public static String  getOtherFoodBalanceElementsQuery(String table, String product) {

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT element_code, element_name || ' - ' || units ");
        sql.append("FROM "+table+" " );
        sql.append("WHERE ");
        sql.append("element_code IN ( ");

        String [] codes = OTHER_ELEMENTS;

        //rice
        if(product.equals("4")){
            codes =   RICE_OTHER_ELEMENTS;
        }

        int i = 0;
        for(String code: codes) {
            sql.append("'"+code+"'");

            if (i < codes.length - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append("ORDER BY CAST(element_order as integer) ");

        return sql.toString();
    }  **/

   /** public static AMISQueryVO getMarketingAndTradeYear(AMISQueryVO qvo, AMISDao amisDao) throws ParseException {
        LinkedHashMap<String, String> marketingYearsByCountry = new LinkedHashMap<String, String>();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_MARKET_TRADE_YEAR"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        String table = sqlResult.get(0).toString();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_COUNTRIES"));
        List<Object> sqlResult3 = amisDao.getSingleResultValue(qvo.getSql());

        String table2 = sqlResult3.get(0).toString();


        String productCode = "";
        for(String prod: qvo.getItems().keySet()){
            productCode = prod;
        }

        qvo.setSql(getMarketingYearQuery(table, table2, productCode, qvo.getAreas()));
        List<Object[]> sqlResult2 = amisDao.getResultList(qvo.getSql());
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM");

        for(int i=0; i < sqlResult2.size(); i++) {
            String startMonth =  sqlResult2.get(i)[0].toString();
            Date date = (Date)formatter.parse(startMonth);
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);

            Date endMonthDate = calendar.getTime();
            LOGGER.info("endMonthDate = "+endMonthDate);
            String endMonth = formatter.format(endMonthDate);
            marketingYearsByCountry.put(sqlResult2.get(i)[1].toString(), startMonth + "/" +endMonth);

        }

        qvo.setCountriesNationalMarketingYear(marketingYearsByCountry);


        LOGGER.info("marketingYearsByCountry = "+marketingYearsByCountry);

        return qvo;

    }    **/

   /** public static AMISQueryVO getMarketingYear(AMISQueryVO qvo, AMISDao amisDao) throws ParseException {
        LinkedHashMap<String, String> marketingYearsByCountry = new LinkedHashMap<String, String>();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_MARKETING_YEAR"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        String table = sqlResult.get(0).toString();

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_COUNTRIES"));
        List<Object> sqlResult3 = amisDao.getSingleResultValue(qvo.getSql());

        String table2 = sqlResult3.get(0).toString();


        String productCode = "";
        for(String prod: qvo.getItems().keySet()){
            productCode = prod;
        }

        qvo.setSql(getMarketingYearQuery(table, table2, productCode, qvo.getAreas()));
        List<Object[]> sqlResult2 = amisDao.getResultList(qvo.getSql());
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM");

        for(int i=0; i < sqlResult2.size(); i++) {
            String startMonth =  sqlResult2.get(i)[0].toString();
            Date date = (Date)formatter.parse(startMonth);
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);

            Date endMonthDate = calendar.getTime();
            LOGGER.info("endMonthDate = "+endMonthDate);
            String endMonth = formatter.format(endMonthDate);
            marketingYearsByCountry.put(sqlResult2.get(i)[1].toString(), startMonth + "/" +endMonth);

        }

        qvo.setCountriesNationalMarketingYear(marketingYearsByCountry);


        LOGGER.info("marketingYearsByCountry = "+marketingYearsByCountry);

        return qvo;

    }   **/


    public static String getMarketingYearQuery(String table, String table2, String productCode, Map<String, String> areas){

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT start_month, r.region_name  ");
        sql.append("FROM "+table+" m, "+table2+" r " );
        sql.append("WHERE ");
        sql.append("product_code = '"+productCode+"' " );
        sql.append("AND m.region_code IN ( ");
        int i = 0;
        for(String code: areas.keySet()) {
            sql.append("'"+code+"'");

            if (i < areas.size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append("AND m.region_code = r.region_code ");

        return sql.toString();

    }



    public static AMISQueryVO getNationalDataSource(AMISQueryVO qvo, AMISDao amisDao){

        qvo.setSql(getNationalDataSource(qvo));

        LOGGER.info("getNationalDataSource"+ qvo.getSql());

        List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());

        LinkedHashMap<String, List<String>> map = new LinkedHashMap<String,  List<String>>();
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> nsMap = new LinkedHashMap<String, LinkedHashMap<String, List<String>>>();

        LinkedHashMap<String, String> regionNameMap = new LinkedHashMap<String, String>();


        for(String code: qvo.getAreas().keySet()) {
            String region = qvo.getAreas().get(code);
            regionNameMap.put(region, code);
        }

        for(int i=0; i < sqlResult.size(); i++) {
            String region_name =  sqlResult.get(i)[0].toString().trim();
            String source =  sqlResult.get(i)[1].toString();
            String last_updated =  sqlResult.get(i)[2].toString();
            String product_name =  sqlResult.get(i)[3].toString();

            if(regionNameMap.containsKey(region_name)){
                LinkedHashMap<String, List<String>> productMap = new LinkedHashMap<String,  List<String>>();
                List<String> sourceList = new ArrayList<String>();

                if(nsMap.containsKey(region_name)) {
                    productMap = nsMap.get(region_name);

                    if(productMap.containsKey(product_name))    {
                       sourceList =  productMap.get(product_name);
                    }
                }

                sourceList.add(source);
                sourceList.add(last_updated);
                productMap.put(product_name, sourceList);
                nsMap.put(region_name, productMap);
            }
        }

        qvo.setNationalDataSources(nsMap);


        LOGGER.info("getNationalDataSources  source = " + qvo.getNationalDataSources());
        return qvo;
    }

    /**public static AMISQueryVO getNationalDataSource(AMISQueryVO qvo, AMISDao amisDao){

        qvo.setSql(getNationalDataSourceOriginal(qvo.getAreas()));

        LOGGER.info("getNationalDataSource"+ qvo.getSql());

        List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());

        LinkedHashMap<String, List<String>> map = new LinkedHashMap<String,  List<String>>();

        LinkedHashMap<String, String> regionNameMap = new LinkedHashMap<String, String>();


        for(String code: qvo.getAreas().keySet()) {
            String region = qvo.getAreas().get(code);
            regionNameMap.put(region, code);
        }

        //country(key): title, last updated
        for(int i=0; i < sqlResult.size(); i++) {
             String region_name =  sqlResult.get(i)[0].toString().trim();
             String source =  sqlResult.get(i)[1].toString();
             String last_updated =  sqlResult.get(i)[2].toString();
             if(regionNameMap.containsKey(region_name)){
                 List<String> sourceDetails = new ArrayList<String>();
                 sourceDetails.add(source);
                 sourceDetails.add(last_updated);
                 map.put(region_name, sourceDetails);
             }
         }

        qvo.setNationalDataSources(map);


        LOGGER.info("getNationalDataSources  source = " + qvo.getNationalDataSources());
        return qvo;
    }   **/

    public static AMISQueryVO getAMISDataLastUpdated(AMISQueryVO qvo, AMISDao amisDao){

        qvo.setSql(getDateLastUpdated());

        LOGGER.info("getAMISDataLastUpdated"+ qvo.getSql());

        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        String date = sqlResult.get(0).toString();
        if(date==null)
            qvo.setAmisLastModifiedDate(date);
        else
          qvo.setAmisLastModifiedDate(date);

        LOGGER.info("getAMISDataLastUpdated  AMIS lastModified = " + qvo.getAmisLastModifiedDate());
        return qvo;
    }


    public static String getNationalDataSourceOriginal(Map<String, String> areas){
        StringBuffer sql = new StringBuffer();
      //  sql.append("SELECT substr(code, 19, 20) as region_name, title as source ");
        sql.append("SELECT (regexp_split_to_array(code, 'AMIS_COUNTRY_DATA_'))[2] as region_name, title as source,  to_char(datelastupdate, 'DD Mon YYYY')  as last_updated ");
        sql.append("FROM customdataset " );
        sql.append("WHERE code IN (");
        int i = 0;
        for(String code: areas.keySet()) {
            sql.append("'AMIS_COUNTRY_DATA_"+areas.get(code)+"'");

            if (i < areas.size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(")");

        return sql.toString();

    }

    public static String getNationalDataSource(AMISQueryVO qvo){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT region_name, modified_by as source, to_char(MAX(last_update), 'DD Mon YYYY')  as last_updated, product_name ");
        sql.append("FROM amis_all_datasources ");
        sql.append("WHERE database = 'NATIONAL' and region_code IN (");
        int i = 0;
        // area codes
        for(String code: qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (i < qvo.getAreas().size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
            // product codes
        sql.append("AND ");
        sql.append("product_code IN (");
        int j = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (j <  qvo.getItems().size() - 1)
                    sql.append(", ");

             j++;
        }
        sql.append(") ");
        sql.append("GROUP BY region_name, product_name, modified_by ");

        return sql.toString();

    }

    public static String getDateLastUpdated(){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT to_char(datelastupdate, 'DD Mon YYYY')  as last_updated ");
        sql.append("FROM customdataset " );
        sql.append("WHERE code = 'AMIS'");

        return sql.toString();

    }


    public static String getElementsByProductAndDataSource(String table, AMISQueryVO qvo){


        StringBuffer sql = new StringBuffer();
        sql.append("SELECT element_code ");
        sql.append("FROM "+table+" " );
        sql.append("WHERE ");
        sql.append("database IN (");
        int i = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (i <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append(" AND ");
        sql.append("product_code IN (");
        int j = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (j <  qvo.getItems().size() - 1)
                sql.append(", ");

            j++;
        }
        sql.append(") ");

        return sql.toString();

    }


    public static String getFootnotes(String elementTable, String footnoteTable, AMISQueryVO qvo){

        StringBuffer sql = new StringBuffer();
        sql.append("SELECT database, product_code, region_name, f.element_code, e.element_name, foot_note ");
        sql.append("FROM "+elementTable+" e,  "+footnoteTable +" f ");
        sql.append("WHERE ");
        sql.append("database IN (");
        int i = 0;
        for(String code:  qvo.getDatabases().keySet()) {
            sql.append("'"+code+"'");

            if (i <  qvo.getDatabases().size() - 1)
                sql.append(", ");

            i++;
        }
        sql.append(") ");
        sql.append(" AND ");
        sql.append("product_code IN (");
        int j = 0;
        for(String code:  qvo.getItems().keySet()) {
            sql.append("'"+code+"'");

            if (j <  qvo.getItems().size() - 1)
                sql.append(", ");

            j++;
        }
        sql.append(") ");
        sql.append(" AND region_code IN (");
        int k = 0;
        for(String code:  qvo.getAreas().keySet()) {
            sql.append("'"+code+"'");

            if (k <  qvo.getAreas().size() - 1)
                sql.append(", ");

            k++;
        }
        sql.append(") ");
        sql.append(" AND f.element_code = e.element_code ");
        sql.append(" ORDER BY database, product_code, region_name, e.element_order ");
        return sql.toString();

    }





}