<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%

String indicatorcode = "";
String marketcode = "";
String timespan = "";
String timeSpanPeriod = "";


if(request.getParameter("indicatorcode")!=null && request.getParameter("marketcode")!=null) {
    indicatorcode = request.getParameter("indicatorcode");
    marketcode = request.getParameter("marketcode");

    timespan = request.getParameter("timespan");

    if(timespan!=null && timespan!=""){

        String duration = timespan.substring(0,timespan.length()-1);
        String durationType = timespan.substring(timespan.length() - 1);

        timeSpanPeriod = "'"+duration + "' ";

        if(durationType.equalsIgnoreCase("Y")){
            timeSpanPeriod +=  "YEAR";
        } else if(durationType.equalsIgnoreCase("M")){
            timeSpanPeriod +=  "MONTH";
        } else if(durationType.equalsIgnoreCase("D")){
            timeSpanPeriod +=  "DAY";
        }
    }

try {

String driver = "oracle.jdbc.OracleDriver";
String url = "jdbc:oracle:thin:@lprdbwo1:3301:wpr01";
String username = "ciwp_read";
String password = "maradona";


//String query1 = "SELECT count(*) from ciwp.ciwp;";
/** Added an interval of 2 hours to avoid the GMT issue (which is -1 hour), when getting the epoch value which sets to GMT.
e.g. 01-03-2007 00:00:00 results in an epoch value representing 28-02-2007 23:00:00 GMT, so results appear 1 day less than expected

 AND dummy year of 1975 so all values across years are comparable

 **/

/**
 * ELECT  to_char(wp.week_end_date, 'ddmmyyyy') week_ending_date, to_char(wp.num,'fm999990.00') price_value,
 * EXTRACT(YEAR FROM wp.week_end_date) as item_name, m.long_name_e market_name, c.long_name_e currency_name,
 * u.long_name_e unit_name,  '1975-' || to_char(wp.week_end_date + INTERVAL '2' HOUR, 'MM-DD HH:MI:SS') sort_date, s.long_name_e source_name,
 * CASE WHEN mi.price_type='M' THEN 'Monthly' ELSE 'Weekly' END AS periodicity, i.long_name_e indicator_name, wp.item indicator_code FROM  ciwp.ciwp wp,
 * ciwp.market_item mi,
 * ciwp.item i,
 * ciwp.market m,
 * ciwp.ccy c,
 * ciwp.unit u,
 * ciwp.source s WHERE  wp.item = '2301'
 * AND wp.market = '16'  AND wp.week_end_date
 * BETWEEN
 * (to_date('01/01/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) - INTERVAL '4' YEAR from ciwp.ciwp wp WHERE wp.item = '2301' AND wp.market = '16')), 'dd/mm/yyyy'))
 * AND  (to_date('31/12/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) YEAR from ciwp.ciwp wp WHERE wp.item = '2301' AND wp.market = '16')), 'dd/mm/yyyy'))
 * AND mi.item = wp.item AND mi.market = wp.market AND i.item = mi.item AND m.market = mi.market AND c.ccy = mi.ccy  AND u.unit = mi.unit AND s.source = mi.source ORDER BY wp.week_end_date
 */



 // query1 += " ORDER BY item_name, sort_date DESC";
//out.print("Query: " + query1);


Connection connection = null;
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url,username,password);

String query1 = getIndicesData1(indicatorcode, timeSpanPeriod, marketcode);

preparedStatement = connection.prepareStatement(query1);
resultSet = preparedStatement.executeQuery();

     JSONObject results=new JSONObject();

    JSONArray indicatorObjectArray = new JSONArray();
    LinkedHashMap<String, JSONObject> indicatorMap = new LinkedHashMap<String,JSONObject>();

    indicatorMap = prepareIndicesData(out, resultSet, results);

    for(String indy: indicatorMap.keySet()){
      indicatorObjectArray.add(indicatorMap.get(indy));
    }

   // query1 = getIndicesData2(indicatorcode, timeSpanPeriod, marketcode);

   // preparedStatement = connection.prepareStatement(query1);
   // resultSet = preparedStatement.executeQuery();

   // indicatorMap = prepareIndicesData(out, resultSet, results);

   // for(String indy: indicatorMap.keySet()){
    //  indicatorObjectArray.add(indicatorMap.get(indy));
   // }

  //query1 = getIndicesData3(indicatorcode, timeSpanPeriod, marketcode);

    //preparedStatement = connection.prepareStatement(query1);
   // resultSet = preparedStatement.executeQuery();

   // indicatorMap = prepareIndicesData(out, resultSet, results);

   // for(String indy: indicatorMap.keySet()){
    //  indicatorObjectArray.add(indicatorMap.get(indy));
   // }

     results.put("indicators", indicatorObjectArray);



        	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		//response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Host,User-Agent,Accept,Accept-Language,Accept-Encoding,Accept-Charset,Keep-Alive,Connection,Referer,Origin"); 
		response.setHeader("Access-Control-Allow-Headers", "*"); 
		response.setHeader("Cache-Control","no-cache,no-store"); 

		response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8"); 
	    

	   String jsonp = "jsonCallbackCompare" + indicatorcode +"(" + results.toString() + ")";
	   response.getWriter().write(jsonp);
	  
     
resultSet.close();
preparedStatement.close();

connection.close();

out.flush(); 
out.close(); 


}

catch(ClassNotFoundException e){e.printStackTrace();}
catch (SQLException ex)
{
out.print("SQLException: "+ex.getMessage());
out.print("SQLState: " + ex.getSQLState());
out.print("VendorError: " + ex.getErrorCode());
}
}
%>

<%!
    public String getIndicesData1(String indicatorcode, String timeSpanPeriod, String marketcode){
        StringBuffer buffer = new StringBuffer();


        buffer.append("SELECT ");
        buffer.append(" to_char(wp.week_end_date, 'ddmmyyyy') week_ending_date,");
        buffer.append(" to_char(wp.num,'fm999990.00') price_value,");
        buffer.append(" EXTRACT(YEAR FROM wp.week_end_date) as item_name, m.long_name_e market_name,");
        buffer.append(" c.long_name_e currency_name, u.long_name_e unit_name,");
        buffer.append("  '1975-' || to_char(wp.week_end_date + INTERVAL '2' HOUR, 'MM-DD HH:MI:SS') sort_date, s.long_name_e source_name, ");
        buffer.append(" CASE WHEN mi.price_type='M' THEN 'Monthly' ELSE 'Weekly' END AS periodicity, i.long_name_e indicator_name, wp.item indicator_code");
        buffer.append(" FROM  ciwp.ciwp wp,");
        buffer.append("       ciwp.market_item mi,");
        buffer.append("       ciwp.item i,");
        buffer.append("       ciwp.market m,");
        buffer.append("       ciwp.ccy c,");
        buffer.append("       ciwp.unit u, ");
        buffer.append("       ciwp.source s");
        buffer.append(" WHERE ");
        buffer.append(" wp.item = '"+indicatorcode+"' ");
        buffer.append(" AND wp.market = '"+marketcode+"' ");
        buffer.append(" AND wp.week_end_date BETWEEN ");
        buffer.append(" (to_date('01/01/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) - INTERVAL "+timeSpanPeriod+" from ciwp.ciwp wp WHERE wp.item = '"+indicatorcode+"' AND wp.market = '"+marketcode+"')), 'dd/mm/yyyy')) ");
        buffer.append(" AND ");
        buffer.append(" (to_date('31/12/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) YEAR from ciwp.ciwp wp WHERE wp.item = '"+indicatorcode+"' AND wp.market = '"+marketcode+"')), 'dd/mm/yyyy')) ");
        buffer.append("  AND week_end_date BETWEEN to_date('01/01/2009', 'dd/mm/yyyy') AND to_date('31/12/2009', 'dd/mm/yyyy') ");
        buffer.append(" AND mi.item = wp.item");
        buffer.append(" AND mi.market = wp.market");
        buffer.append(" AND i.item = mi.item");
        buffer.append(" AND m.market = mi.market");
        buffer.append(" AND c.ccy = mi.ccy ");
        buffer.append(" AND u.unit = mi.unit");
        buffer.append(" AND s.source = mi.source ");     // AND ROWNUM <= 70
        buffer.append(" ORDER BY EXTRACT(YEAR FROM wp.week_end_date), sort_date ");


        return buffer.toString();

    }

     public String getIndicesData2(String indicatorcode, String timeSpanPeriod, String marketcode){
        StringBuffer buffer = new StringBuffer();


        buffer.append("SELECT ");
        buffer.append(" to_char(wp.week_end_date, 'ddmmyyyy') week_ending_date,");
        buffer.append(" to_char(wp.num,'fm999990.00') price_value,");
        buffer.append(" EXTRACT(YEAR FROM wp.week_end_date) as item_name, m.long_name_e market_name,");
        buffer.append(" c.long_name_e currency_name, u.long_name_e unit_name,");
        buffer.append("  '1975-' || to_char(wp.week_end_date + INTERVAL '2' HOUR, 'MM-DD HH:MI:SS') sort_date, s.long_name_e source_name, ");
        buffer.append(" CASE WHEN mi.price_type='M' THEN 'Monthly' ELSE 'Weekly' END AS periodicity, i.long_name_e indicator_name, wp.item indicator_code");
        buffer.append(" FROM  ciwp.ciwp wp,");
        buffer.append("       ciwp.market_item mi,");
        buffer.append("       ciwp.item i,");
        buffer.append("       ciwp.market m,");
        buffer.append("       ciwp.ccy c,");
        buffer.append("       ciwp.unit u, ");
        buffer.append("       ciwp.source s");
        buffer.append(" WHERE ");
        buffer.append(" wp.item = '"+indicatorcode+"' ");
        buffer.append(" AND wp.market = '"+marketcode+"' ");
        buffer.append(" AND wp.week_end_date BETWEEN ");
        buffer.append(" (to_date('01/01/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) - INTERVAL "+timeSpanPeriod+" from ciwp.ciwp wp WHERE wp.item = '"+indicatorcode+"' AND wp.market = '"+marketcode+"')), 'dd/mm/yyyy')) ");
        buffer.append(" AND ");
        buffer.append(" (to_date('31/12/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) YEAR from ciwp.ciwp wp WHERE wp.item = '"+indicatorcode+"' AND wp.market = '"+marketcode+"')), 'dd/mm/yyyy')) ");
        buffer.append("  AND week_end_date BETWEEN to_date('01/01/2010', 'dd/mm/yyyy') AND to_date('31/12/2010', 'dd/mm/yyyy') ");
        buffer.append(" AND mi.item = wp.item");
        buffer.append(" AND mi.market = wp.market");
        buffer.append(" AND i.item = mi.item");
        buffer.append(" AND m.market = mi.market");
        buffer.append(" AND c.ccy = mi.ccy ");
        buffer.append(" AND u.unit = mi.unit");
        buffer.append(" AND s.source = mi.source ");     // AND ROWNUM <= 70
        buffer.append(" ORDER BY EXTRACT(YEAR FROM wp.week_end_date), sort_date ");


        return buffer.toString();

    }


      public String getIndicesData3(String indicatorcode, String timeSpanPeriod, String marketcode){
        StringBuffer buffer = new StringBuffer();


        buffer.append("SELECT ");
        buffer.append(" to_char(wp.week_end_date, 'ddmmyyyy') week_ending_date,");
        buffer.append(" to_char(wp.num,'fm999990.00') price_value,");
        buffer.append(" EXTRACT(YEAR FROM wp.week_end_date) as item_name, m.long_name_e market_name,");
        buffer.append(" c.long_name_e currency_name, u.long_name_e unit_name,");
        buffer.append("  '1975-' || to_char(wp.week_end_date + INTERVAL '2' HOUR, 'MM-DD HH:MI:SS') sort_date, s.long_name_e source_name, ");
        buffer.append(" CASE WHEN mi.price_type='M' THEN 'Monthly' ELSE 'Weekly' END AS periodicity, i.long_name_e indicator_name, wp.item indicator_code");
        buffer.append(" FROM  ciwp.ciwp wp,");
        buffer.append("       ciwp.market_item mi,");
        buffer.append("       ciwp.item i,");
        buffer.append("       ciwp.market m,");
        buffer.append("       ciwp.ccy c,");
        buffer.append("       ciwp.unit u, ");
        buffer.append("       ciwp.source s");
        buffer.append(" WHERE ");
        buffer.append(" wp.item = '"+indicatorcode+"' ");
        buffer.append(" AND wp.market = '"+marketcode+"' ");
        buffer.append(" AND wp.week_end_date BETWEEN ");
        buffer.append(" (to_date('01/01/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) - INTERVAL "+timeSpanPeriod+" from ciwp.ciwp wp WHERE wp.item = '"+indicatorcode+"' AND wp.market = '"+marketcode+"')), 'dd/mm/yyyy')) ");
        buffer.append(" AND ");
        buffer.append(" (to_date('31/12/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) YEAR from ciwp.ciwp wp WHERE wp.item = '"+indicatorcode+"' AND wp.market = '"+marketcode+"')), 'dd/mm/yyyy')) ");
        buffer.append("  AND week_end_date BETWEEN to_date('01/01/2013', 'dd/mm/yyyy') AND to_date('31/12/2013', 'dd/mm/yyyy') ");
        buffer.append(" AND mi.item = wp.item");
        buffer.append(" AND mi.market = wp.market");
        buffer.append(" AND i.item = mi.item");
        buffer.append(" AND m.market = mi.market");
        buffer.append(" AND c.ccy = mi.ccy ");
        buffer.append(" AND u.unit = mi.unit");
        buffer.append(" AND s.source = mi.source ");     // AND ROWNUM <= 70
        buffer.append(" ORDER BY EXTRACT(YEAR FROM wp.week_end_date), sort_date ");


        return buffer.toString();

    }

 public LinkedHashMap<String, JSONObject> prepareIndicesData(javax.servlet.jsp.JspWriter out, ResultSet resultSet, JSONObject results) throws SQLException, ParseException, IOException {
    //out.print("<br/><br/>resultSet1 . START  ");
    //out.print("<br/><br/> ");


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

     while(resultSet.next()) {
         JSONObject indicatorObject = new JSONObject();

         series_name = (String)resultSet.getString(3);// + " " + (String)resultSet.getString(4);
         base_period = (String)resultSet.getString(4);
         source = (String)resultSet.getString(8);
         unit = (String)resultSet.getString(5) + " " + (String)resultSet.getString(6);
         periodicity = (String)resultSet.getString(9);
         indicator = (String)resultSet.getString(10);
         indicator_code = (String)resultSet.getString(11 );

         JSONArray dataArray = new JSONArray();
         JSONArray indexDataArray = new JSONArray();

         String str = (String)resultSet.getString(7);
         // out.print(" str  "+str);
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         java.util.Date date = df.parse(str);
         //out.print(" date  "+date);
         long epoch = date.getTime();
         //   indexDataArray.add(Long.valueOf(epoch));
         //   indexDataArray.add(Long.valueOf(epoch));
         //  out.print("value row["+p+"] = "+str);
         //  out.print("<br/><br/> /n");

         // dataArray.add(str);


         Double dVal = null;
         String val = (String)resultSet.getString(2);
         if(val!=null){
             dVal = Double.valueOf((String)resultSet.getString(2));
         } else
             dVal = null;


         if(!indexDataArray.contains(epoch)){
             indexDataArray.add(Long.valueOf(epoch))  ;
             indexDataArray.add(dVal);
         } else {
             indexDataArray.add("MATCH");
         }
         // else {
         //
         //  }

         indexDataArray.add(str);
         indexDataArray.add(series_name);


         if(indicatorMap.containsKey((String)resultSet.getString(3))){
             indicatorObject = indicatorMap.get((String)resultSet.getString(3));

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
         indicatorObject.put("units", unit);
         indicatorObject.put("data", dataArray);
         indicatorObject.put("visible", "true");

         //  indicatorObjectArray.add(indicatorObject);

         indicatorMap.put((String)resultSet.getString(3), indicatorObject);


     }


    // out.println("<br/><br/>");
    // out.println("FINAL indicatorMap size = "+indicatorMap.size());
    // out.println("FINAL indicatorMap "+indicatorMap);
    // out.println("<br/><br/>");


    //results.put("indicators", indicatorObjectArray);

    // out.println("<br/><br/> results = "+results);

    return indicatorMap;
    }
%>



