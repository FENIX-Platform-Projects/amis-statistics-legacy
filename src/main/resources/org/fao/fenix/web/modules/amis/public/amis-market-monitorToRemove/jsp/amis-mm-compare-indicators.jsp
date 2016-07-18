<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.lang.System" %>


<%

    String indicatorcode = "";
    String domaintablecode = "";
    String timespan = null;
    String timeSpanPeriod = null;
    String startingMonth = "01";
    String startMonth = null;

    if(request.getParameter("indicatorcode")!=null && request.getParameter("domaintablecode")!=null) {
        indicatorcode = request.getParameter("indicatorcode");
        domaintablecode = request.getParameter("domaintablecode");
        timespan = request.getParameter("timespan");
        startMonth = request.getParameter("startMonth");

        if(timespan!=null && timespan!=""){

            String duration = timespan.substring(0,timespan.length()-1);
            String durationType = timespan.substring(timespan.length() - 1);

            timeSpanPeriod = duration + " ";

            if(durationType.equalsIgnoreCase("Y")){
                timeSpanPeriod +=  "year";
            } else if(durationType.equalsIgnoreCase("M")){
                timeSpanPeriod +=  "month";
            } else if(durationType.equalsIgnoreCase("D")){
                timeSpanPeriod +=  "day";
            }
        }


        if(startMonth!=null && startMonth!=""){
             startingMonth = startMonth;
        }

        String endMonth = getEndMonth(startingMonth);

        int numberOfDaysInEndMonth = getNumberOfDaysInEndMonth(startingMonth);

        LinkedList<String> monthsToEndOfYear = getMonthsToEndOfYear(startingMonth);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement1a = null;
        ResultSet resultSet1a = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;

        try {
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost/fenix?user=fenix&password=Qwaszx";
            String username = "fenix";
            String password = "Qwaszx";
            String DataField = null;

            String query1 =
                    "SELECT tablename from customdataset WHERE code='"+domaintablecode+"'";


            String query1a =
                    "SELECT tablename from customdataset WHERE code ='AMIS_INDICATOR_DEFINITIONS'";
            //  out.print("Query XX: " + query1);




            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url);

            // out.print("<br/> connection: " + connection);
            preparedStatement = connection.prepareStatement(query1);

            // out.print("<br/> preparedStatement: " + preparedStatement);

            resultSet = preparedStatement.executeQuery();


            preparedStatement1a = connection.prepareStatement(query1a);
            // out.print("<br/> preparedStatement1a: " + preparedStatement1a);

            resultSet1a = preparedStatement1a.executeQuery();

            if(resultSet.next()) {
                //    out.print(". ");

                String amisTableName = resultSet.getString("tablename");

                // out.print("<br/> amisTableName: " + amisTableName);

                if(resultSet1a.next()) {

                    String amisDefTable = resultSet1a.getString("tablename");

                    String dataQuery1 = "";
                    String trendValue = "";

                    dataQuery1 = getIndicesData(indicatorcode, amisTableName, amisDefTable, timeSpanPeriod, domaintablecode, startingMonth, endMonth, numberOfDaysInEndMonth, monthsToEndOfYear);


                  //  out.print("startingMonth: " + startingMonth);
                  //  out.print("endMonth: " + endMonth);
                  //  out.print("numberOfDaysInEndMonth: " + numberOfDaysInEndMonth);

                    //    out.print("Query: " + dataQuery1);

                    JSONObject results=new JSONObject();

                    preparedStatement1 = connection.prepareStatement(dataQuery1);
                    resultSet1 = preparedStatement1.executeQuery();

                    results = prepareIndicesData(out, resultSet1, results, domaintablecode);

                    response.setHeader("Access-Control-Allow-Origin", "*");
                    response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                    //response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Host,User-Agent,Accept,Accept-Language,Accept-Encoding,Accept-Charset,Keep-Alive,Connection,Referer,Origin");
                    response.setHeader("Access-Control-Allow-Headers", "*");
                    response.setHeader("Cache-Control","no-cache,no-store");

                    response.setContentType("application/json");

                    response.setCharacterEncoding("UTF-8");

                    String jsonp = "jsonCallbackCompare" + indicatorcode +"(" + results.toString() + ")";
                    //String jsonp =  results.toString() ;
                    response.getWriter().write(jsonp);

                }
            }
        }

        catch(ClassNotFoundException e){e.printStackTrace();}
        catch (SQLException ex)
        {
            out.print("SQLException: "+ex.getMessage());
            out.print("SQLState: " + ex.getSQLState());
            out.print("VendorError: " + ex.getErrorCode());
        }
        finally
        {
            resultSet.close();
            resultSet1a.close();
            resultSet1.close();
            preparedStatement1.close();
            preparedStatement1a.close();
            preparedStatement.close();
            connection.close();
            out.flush();
        }

    }
%>

<%!
    /** Added an interval of 2 hours to avoid the GMT issue (which is -1 hour), when getting the epoch value which sets to GMT.
     e.g. 01-03-2007 00:00:00 results in an epoch value representing 28-02-2007 23:00:00 GMT, so results appear 1 day less than expected
     AND
     a dummy year of 1975 is used to standardize the day/month date values in order for values across years to be comparable **/

  public String getIndicesData(String indicatorcode, String amisTableName, String amisDefinitionTableName, String timeSpan, String domaintablecode, String startingMonth, String endMonth, int numberOfDaysInEndMonth, LinkedList<String> monthsToEndOfYear){
        StringBuffer buffer = new StringBuffer();

        /**SELECT p.timestamp, p.index, p.year, p.source, p.measurement_unit, base_value, p.periodicity, p.source_url,  p.indicator_name, p.indicator_code  FROM
         (SELECT
         CASE WHEN EXTRACT(month from date) IN ('06', '07', '08', '09', '10', '11', '12')
         THEN (to_date('1975-' || to_char(date, 'MM'), 'yyyy-MM') + interval '2 hour')::timestamp
         ELSE  (to_date('1976-' || to_char(date, 'MM'), 'yyyy-MM') + interval '2 hour')::timestamp
         END AS timestamp,
         CASE WHEN  D.measurement_unit is not null THEN ROUND(CAST(value as numeric), 2) ELSE ROUND(CAST(value as numeric), 1)
         END AS index, EXTRACT(YEAR FROM date) as year, D.source, D.measurement_unit,
         CASE WHEN  I.Index_Base_Value is not null THEN I.Index_Base_Period || ' = ' || I.Index_Base_Value
         ELSE I.Index_Base_Period END AS base_value, I.periodicity, I.source_url,  D.indicator_name, D.indicator_code
         FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_eaf00771 D, AMIS_MARKET_MONITOR_INDICATOR_DEFINITIONS_acfc98c9 I
         WHERE  D.indicator_code='GCFPPGVC' AND D.indicator_code = I.indicator_code  AND to_date(to_char(D.date, 'YYYY-MM'), 'YYYY-MM')
         BETWEEN  (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) - interval '4 year' FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_eaf00771
         WHERE indicator_code = 'GCFPPGVC')) || '-06-01', 'YYYY-MM'))  AND  (to_date(EXTRACT(YEAR FROM (SELECT MAX(date)
         FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_eaf00771 WHERE indicator_code = 'GCFPPGVC')) || '-05-31', 'YYYY-MM')))p
         ORDER BY  p.year, p.timestamp ASC  **/


        buffer.append("SELECT p.timestamp, p.index, p.year, p.source, p.measurement_unit, p.base_value, p.periodicity, p.source_url,  p.indicator_name, p.indicator_code ");
        buffer.append("FROM ");
        buffer.append("( ");
        //dummy year of 1975 used to standardize the day/month date values for the x-axis
        if(domaintablecode.contains("DAILY")) {
            buffer.append("SELECT ");
            buffer.append("CASE WHEN EXTRACT(month from date) IN (");
            int i = 0;
            for(String month: monthsToEndOfYear){
                buffer.append("'"+month+"'");

                if(i < monthsToEndOfYear.size() -1){
                    buffer.append(",");
                }
                i++;
            }
            buffer.append(") ");
            buffer.append("THEN (to_date('1975-' || to_char(date, 'MM-dd'), 'yyyy-MM-dd') + interval '2 hour')::timestamp ");
            buffer.append("ELSE  (to_date('1976-' || to_char(date, 'MM-dd'), 'yyyy-MM-dd') + interval '2 hour')::timestamp ");
            buffer.append("END AS timestamp, ");
        } else if(domaintablecode.contains("MONTHLY")){
            buffer.append("SELECT ");
            buffer.append("CASE WHEN EXTRACT(month from date) IN (");
            int j = 0;
            for(String month: monthsToEndOfYear){
                buffer.append("'"+month+"'");

                if(j < monthsToEndOfYear.size() -1){
                    buffer.append(",");
                }
                j++;
            }
            buffer.append(")");
            buffer.append("THEN (to_date('1975-' || to_char(date, 'MM'), 'yyyy-MM') + interval '2 hour')::timestamp ");
            buffer.append("ELSE  (to_date('1976-' || to_char(date, 'MM'), 'yyyy-MM') + interval '2 hour')::timestamp ");
            buffer.append("END AS timestamp, ");
         }

        buffer.append(" CASE WHEN  D.measurement_unit is not null THEN ROUND(CAST(value as numeric), 2) ELSE ROUND(CAST(value as numeric), 1) END AS index, EXTRACT(YEAR FROM date) as year, D.source, D.measurement_unit,  ");
        buffer.append(" CASE WHEN  I.Index_Base_Value is not null THEN I.Index_Base_Period || ' = ' || I.Index_Base_Value ELSE I.Index_Base_Period END AS base_value, I.periodicity, I.source_url, ");
        buffer.append(" D.indicator_name, D.indicator_code");
        buffer.append(" FROM "+amisTableName + " D, "+amisDefinitionTableName + " I");
        buffer.append(" WHERE  D.indicator_code='"+indicatorcode+"'");
        buffer.append(" AND D.indicator_code = I.indicator_code");
        if(timeSpan!=null){
            if(domaintablecode.contains("DAILY")) {
                buffer.append(" AND D.date BETWEEN ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) - interval '"+timeSpan+"' FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+startingMonth+"-01', 'YYYY-mm-dd')) ");
                buffer.append(" AND ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+endMonth+"-"+numberOfDaysInEndMonth+"', 'YYYY-mm-dd')) ");
            } else if(domaintablecode.contains("MONTHLY")){
                buffer.append("  AND to_date(to_char(D.date, 'YYYY-MM'), 'YYYY-MM') BETWEEN ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) - interval '"+timeSpan+"' FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+startingMonth+"-01', 'YYYY-MM')) ");
                buffer.append(" AND ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date)  FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+endMonth+"-"+numberOfDaysInEndMonth+"', 'YYYY-MM')) ");
            }

        }
        buffer.append(") p ");
        buffer.append(" ORDER BY  p.year, p.timestamp ASC ");


        return buffer.toString();

    }

    public String getIndicesDataFirst(String indicatorcode, String amisTableName, String amisDefinitionTableName, String timeSpan, String domaintablecode, String startingMonth, String endMonth, int numberOfDaysInEndMonth){
        StringBuffer buffer = new StringBuffer();


       /** SELECT (to_date('1975-' || to_char(date, 'MM'), 'yyyy-MM') + interval '2 hour')::timestamp as date_timestamp,
                CASE WHEN  D.measurement_unit is not null THEN ROUND(CAST(value as numeric), 2)
        ELSE ROUND(CAST(value as numeric), 1) END AS index,
                EXTRACT(YEAR FROM date) as year, D.source, D.measurement_unit,   CASE WHEN  I.Index_Base_Value is not null
        THEN I.Index_Base_Period || ' = ' || I.Index_Base_Value ELSE I.Index_Base_Period END AS base_value, I.periodicity, I.source_url,  D.indicator_name, D.indicator_code
        FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_eaf00771 D, AMIS_MARKET_MONITOR_INDICATOR_DEFINITIONS_acfc98c9 I
        WHERE  D.indicator_code='GCFPPGVC' AND D.indicator_code = I.indicator_code
        AND to_date(to_char(D.date, 'YYYY-MM'), 'YYYY-MM') BETWEEN
                (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) - interval '4 year' FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_eaf00771 WHERE indicator_code = 'GCFPPGVC')) || '-01-01', 'YYYY-MM'))  AND  (to_date(EXTRACT(YEAR FROM (SELECT MAX(date)  FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_eaf00771 WHERE indicator_code = 'GCFPPGVC')) || '-12-31', 'YYYY-MM'))  ;
        **/

        //dummy year of 1975 used to standardize the day/month date values for the x-axis
        if(domaintablecode.contains("DAILY")) {
            buffer.append(" SELECT (to_date('1975-' || to_char(date, 'MM-dd'), 'yyyy-MM-dd') + interval '2 hour')::timestamp as date_timestamp, ");
        } else if(domaintablecode.contains("MONTHLY")){
            buffer.append(" SELECT (to_date('1975-' || to_char(date, 'MM'), 'yyyy-MM') + interval '2 hour')::timestamp as date_timestamp, ");
        }

         buffer.append(" CASE WHEN  D.measurement_unit is not null THEN ROUND(CAST(value as numeric), 2) ELSE ROUND(CAST(value as numeric), 1) END AS index, EXTRACT(YEAR FROM date) as year, D.source, D.measurement_unit,  ");
        buffer.append(" CASE WHEN  I.Index_Base_Value is not null THEN I.Index_Base_Period || ' = ' || I.Index_Base_Value ELSE I.Index_Base_Period END AS base_value, I.periodicity, I.source_url, ");
        buffer.append(" D.indicator_name, D.indicator_code");
        buffer.append(" FROM "+amisTableName + " D, "+amisDefinitionTableName + " I");
        buffer.append(" WHERE  D.indicator_code='"+indicatorcode+"'");
        buffer.append(" AND D.indicator_code = I.indicator_code");
        if(timeSpan!=null){
            if(domaintablecode.contains("DAILY")) {
                buffer.append(" AND D.date BETWEEN ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) - interval '"+timeSpan+"' FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+startingMonth+"-01', 'YYYY-mm-dd')) ");
                buffer.append(" AND ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+endMonth+"-"+numberOfDaysInEndMonth+"', 'YYYY-mm-dd')) ");
            } else if(domaintablecode.contains("MONTHLY")){
                buffer.append("  AND to_date(to_char(D.date, 'YYYY-MM'), 'YYYY-MM') BETWEEN ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) - interval '"+timeSpan+"' FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+startingMonth+"-01', 'YYYY-MM')) ");
                buffer.append(" AND ");
                buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date)  FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"')) || '-"+endMonth+"-"+numberOfDaysInEndMonth+"', 'YYYY-MM')) ");
            }

        }
        buffer.append(" ");


        return buffer.toString();

    }

    public JSONObject prepareIndicesData(javax.servlet.jsp.JspWriter out, ResultSet resultSet1,  JSONObject results, String domaintablecode) throws SQLException, ParseException, IOException {
        //out.print("<br/><br/>resultSet1 . START  ");
        //out.print("<br/><br/> ");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateMonthlyFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat newDateformat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat monthlyDateFormatLabel = new SimpleDateFormat("MMM yyyy");
        SimpleDateFormat dailyDateFormatLabel = new SimpleDateFormat("dd MMM yyyy");

        //Query 1 RESULTS: prepare data

        String series_name = "";
        String indicator = null;
        String indicator_code = null;
        String source = null;
        String source_url = null;
        String unit = null;
        String base_period = null;
        String periodicity = null;
        String startdate = null;
        String enddate = null;
        String startdatelabel = null;
        String enddatelabel = null;

        JSONArray indicatorObjectArray = new JSONArray();

        LinkedHashMap<String, JSONObject> indicatorMap = new LinkedHashMap<String,JSONObject>();


        while(resultSet1.next()) {

            JSONObject indicatorObject = new JSONObject();
            series_name = (String)resultSet1.getString(3);
            source = (String)resultSet1.getString(4);
            unit = (String)resultSet1.getString(5);
            base_period = (String)resultSet1.getString(6);
            periodicity = (String)resultSet1.getString(7);
            source_url = (String)resultSet1.getString(8);
            indicator = (String)resultSet1.getString(9);
            indicator_code = (String)resultSet1.getString(10);

            String dateSt = (String)resultSet1.getString(1);
            java.util.Date date = dateFormat.parse(dateSt);

            JSONArray dataArray = new JSONArray();
            JSONArray indexDataArray = new JSONArray();

            long epoch = date.getTime();
            indexDataArray.add(Long.valueOf(epoch));

            if(resultSet1.isFirst()){
                startdate = newDateformat.format(date);
                if(domaintablecode.contains("DAILY"))
                    startdatelabel = dailyDateFormatLabel.format(date);
                else
                    startdatelabel = monthlyDateFormatLabel.format(date);
            }

            if(resultSet1.isLast()){
                enddate = newDateformat.format(date);

                if(domaintablecode.contains("DAILY"))
                    enddatelabel = dailyDateFormatLabel.format(date);
                else
                    enddatelabel = monthlyDateFormatLabel.format(date);

            }

            if(source== null)
                source = "";

            if(source_url== null)
                source_url = "";


            if(unit == null)
                unit = "";

            if(base_period== null)
                base_period = "";

            if(periodicity== null)
                periodicity = "";

            if(indicator== null)
                indicator = "";

            if(indicator_code== null)
                indicator_code = "";


            Double dVal = null;
            String val = (String)resultSet1.getString(2);

            if(val!=null){
                dVal = Double.valueOf((String)resultSet1.getString(2));
            } else
                dVal = null;

            indexDataArray.add(dVal);


            if(indicatorMap.containsKey((String)resultSet1.getString(3))){
                indicatorObject = indicatorMap.get((String)resultSet1.getString(3));


                if(indicatorObject.containsKey("data")){
                    dataArray = (JSONArray)indicatorObject.get("data");
                }
            }




            // dataArray.add((String)resultSet1.getString(1));
            dataArray.add(indexDataArray);


            indicatorObject.put("series", series_name);
            indicatorObject.put("indicator", indicator);
            indicatorObject.put("indicator_code", indicator_code);
            indicatorObject.put("periodicity", periodicity);
            indicatorObject.put("baseperiod", base_period);
            indicatorObject.put("source", source);
            indicatorObject.put("sourceurl", source_url);
            indicatorObject.put("units", unit);
            indicatorObject.put("data", dataArray);
            indicatorObject.put("startdate", startdate);
            indicatorObject.put("enddate", enddate);
            indicatorObject.put("startdatelabel", startdatelabel);
            indicatorObject.put("enddatelabel", enddatelabel);
            indicatorObject.put("visible", "true");

            //  indicatorObjectArray.add(indicatorObject);

            indicatorMap.put((String)resultSet1.getString(3), indicatorObject);
            //out.println("<br/><br/> ");
            //out.println("indicatorMap "+indicatorMap);
            // out.println("<br/><br/> ");
        }

        for(String indy: indicatorMap.keySet()){
            indicatorObjectArray.add(indicatorMap.get(indy));
        }

        // out.println("<br/><br/>");
        // out.println("FINAL indicatorMap size = "+indicatorMap.size());
        // out.println("FINAL indicatorMap "+indicatorMap);
        // out.println("<br/><br/>");


        results.put("indicators", indicatorObjectArray);

        // out.println("<br/><br/> results = "+results);

        return results;
    }

    public String getEndMonth(String startMonth) throws ParseException, IOException {

        String dateString = "01-"+startMonth+"-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date convertedDate = dateFormat.parse(dateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertedDate);
        calendar.add(Calendar.MONTH,11);

        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String endMonth = monthFormat.format(calendar.getTime());

        return endMonth;
    }


    public int getNumberOfDaysInEndMonth(String startMonth) throws ParseException, IOException {

        String dateString = "01-"+startMonth+"-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date convertedDate = dateFormat.parse(dateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertedDate);
        calendar.add(Calendar.MONTH,11);

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        return daysInMonth;
    }


    public LinkedList<String> getMonthsToEndOfYear(String startMonth) throws ParseException, IOException {

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

        String dateString = "01-"+startMonth+"-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date convertedDate = dateFormat.parse(dateString);

        Calendar start = Calendar.getInstance();
        start.setTime(convertedDate);

        Calendar end = Calendar.getInstance();
        end.setTime(convertedDate);
        end.add(Calendar.MONTH,11);

        LinkedList<String> months = new LinkedList<String>();

        for (Date date = start.getTime(); !start.after(end); start.add(Calendar.MONTH, 1), date = start.getTime()) {

            String month = monthFormat.format(date);
            String year = yearFormat.format(date);

            if(year.equals("2000"))
                months.add(month);

        }



        return months;
    }





%>

