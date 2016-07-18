<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page import="java.text.SimpleDateFormat" %>

<%

String indicatorcode = "";
String marketcode = "";
String callbackIdentifier = "";


if(request.getParameter("indicatorcode")!=null && request.getParameter("marketcode")!=null) {
    indicatorcode = request.getParameter("indicatorcode");
    marketcode = request.getParameter("marketcode");
	callbackIdentifier = request.getParameter("cb");

try {

String driver = "oracle.jdbc.OracleDriver";
String url = "jdbc:oracle:thin:@lprdbwo1:3301:wpr01";
String username = "ciwp_read";
String password = "maradona";


//String query1 = "SELECT count(*) from ciwp.ciwp;";
/** Added an interval of 2 hours to avoid the GMT issue (which is -1 hour), when getting the epoch value which sets to GMT.
e.g. 01-03-2007 00:00:00 results in an epoch value representing 28-02-2007 23:00:00 GMT, so results appear 1 day less than expected **/

String query1 = "SELECT ";
  query1 += " to_char(wp.week_end_date, 'ddmmyyyy') week_ending_date,";
  query1 += " to_char(wp.num,'fm999990.00') price_value,";
  query1 += " i.long_name_e item_name, m.long_name_e market_name,";
  query1 += " c.long_name_e currency_name, u.long_name_e unit_name,";
 query1 += "  to_char(wp.week_end_date + INTERVAL '2' HOUR, 'YYYY-MM-DD HH:MI:SS') sort_date, s.long_name_e source_name, ";
 query1 += " CASE WHEN mi.price_type='M' THEN 'Monthly' ELSE 'Weekly' END AS periodicity ";
  query1 += " FROM  ciwp.ciwp wp,";
  query1 += "       ciwp.market_item mi,";
 query1 += "       ciwp.item i,";
  query1 += "       ciwp.market m,";
 query1 += "       ciwp.ccy c,";
  query1 += "       ciwp.unit u, ";
  query1 += "       ciwp.source s";
  query1 += " WHERE ";
 query1 += " wp.item = '"+indicatorcode+"' ";
  query1 += " AND wp.market = '"+marketcode+"' ";
 query1 += " AND mi.item = wp.item";
  query1 += " AND mi.market = wp.market";
 query1 += " AND i.item = mi.item";
 query1 += " AND m.market = mi.market";
  query1 += " AND c.ccy = mi.ccy ";
 query1 += " AND u.unit = mi.unit";
  query1 += " AND s.source = mi.source ORDER BY wp.week_end_date";

//out.print("Query: " + query1);


Connection connection = null;
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url,username,password);



preparedStatement = connection.prepareStatement(query1);
resultSet = preparedStatement.executeQuery();

JSONObject results=new JSONObject(); 
JSONArray dataArray = new JSONArray();
  
 String indicator_name = null;
 String source = null;
 String unit = null;
 String periodicity = null;
 String base_period = null;
	 
  
while(resultSet.next()) {
	
      JSONArray indexDataArray = new JSONArray();
      
      indicator_name = (String)resultSet.getString(3);// + " " + (String)resultSet.getString(4);

      if(indicator_name.equals("Soybeans"))
      	indicator_name = "Soybean";

      base_period = (String)resultSet.getString(4);
      source = (String)resultSet.getString(8);
      unit = (String)resultSet.getString(5) + " " + (String)resultSet.getString(6);
      periodicity = (String)resultSet.getString(9);
    
                
	          Double dVal = null;
	          String val = (String)resultSet.getString(2);
	          
	          
	          String str = (String)resultSet.getString(7);
		      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      java.util.Date date = df.parse(str);
		      long epoch = date.getTime();
                 
                 // dataArray.add(str);
		  indexDataArray.add(Long.valueOf(epoch));
		  
		  if(val!=null){
		   dVal = Double.valueOf((String)resultSet.getString(2));
		  } else
		  dVal = null;
		  
		  
		 indexDataArray.add(dVal);
		 dataArray.add(indexDataArray);
	   }

	results.put("indicator", indicator_name);
	results.put("periodicity", periodicity);
	results.put("baseperiod", base_period);
	results.put("source", source + " (distributed by FAO-EST Price Database)");
	//results.put("units", unit);
	results.put("units", "USD per tonne");
	results.put("data", dataArray);
	 
	
         	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		//response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Host,User-Agent,Accept,Accept-Language,Accept-Encoding,Accept-Charset,Keep-Alive,Connection,Referer,Origin"); 
		response.setHeader("Access-Control-Allow-Headers", "*"); 
		response.setHeader("Cache-Control","no-cache,no-store"); 

		response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8"); 
	    
       String jsonp = "jsonCallback" + callbackIdentifier+"_"+indicatorcode +"(" + results.toString() + ")";
	   //String jsonp = "jsonCallback" + indicatorcode +"(" + results.toString() + ")";
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



