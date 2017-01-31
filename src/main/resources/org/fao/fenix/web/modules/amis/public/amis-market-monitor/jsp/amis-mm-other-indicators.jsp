<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.lang.System" %>


<%

String indicatorcode = "";
String domaintablecode = "";
String timespan = null;
String timeSpanPeriod = null;
String callbackIdentifier = "";

if(request.getParameter("indicatorcode")!=null && request.getParameter("domaintablecode")!=null) {
	indicatorcode = request.getParameter("indicatorcode");
	domaintablecode = request.getParameter("domaintablecode");
        timespan = request.getParameter("timespan");
	callbackIdentifier = request.getParameter("cb");

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
//out.print("Query XX: " + query1);




Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url);

preparedStatement = connection.prepareStatement(query1);
resultSet = preparedStatement.executeQuery();

preparedStatement1a = connection.prepareStatement(query1a);
resultSet1a = preparedStatement1a.executeQuery();

if(resultSet.next()) {

String amisTableName = resultSet.getString("tablename");

if(resultSet1a.next()) {

	String amisDefTable = resultSet1a.getString("tablename");
	
//PreparedStatement preparedStatement1 = null;
//ResultSet resultSet1 = null;

//Query 1 
String dataQuery1 = "";
String trendValue = "";
 
if(!domaintablecode.equalsIgnoreCase("AMIS")){
    dataQuery1 = getIndicesData(indicatorcode, amisTableName, amisDefTable, timeSpanPeriod, domaintablecode);
   //out.print("Query: " + dataQuery1);
}
else {
    dataQuery1 = getWorldStockToUseRatioQuery(indicatorcode, amisTableName);
     
    switch (Integer.valueOf(indicatorcode)) {
        case 5: //maize
        	trendValue = "943.1";
        	break;	
        case 4:  //Rice: Pakistan missing
    		 trendValue = "480.3";    				
                 break;         
        case 6:  //Soybean: tendValue not needed for soybeans
    		 trendValue = "";   				
                 break;
        case 1:  //wheat 
    		trendValue = "693.6";	    
                 break;
    }

}

//out.println("dataQuery 1 = "+ dataQuery1);

 JSONObject results=new JSONObject(); 
   
preparedStatement1 = connection.prepareStatement(dataQuery1);
resultSet1 = preparedStatement1.executeQuery();

if(!domaintablecode.equalsIgnoreCase("AMIS")){
    results = prepareIndicesData(out, resultSet1, results, domaintablecode);
}
else {
    results = prepareStocksData(out, resultSet1, results, trendValue, indicatorcode);
}

	
	 
	
         	response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		//response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Host,User-Agent,Accept,Accept-Language,Accept-Encoding,Accept-Charset,Keep-Alive,Connection,Referer,Origin"); 
		response.setHeader("Access-Control-Allow-Headers", "*"); 
		response.setHeader("Cache-Control","no-cache,no-store"); 

		response.setContentType("application/json");
		
	


	    response.setCharacterEncoding("UTF-8"); 
	    

	   String jsonp = "jsonCallback" + callbackIdentifier+"_"+indicatorcode +"(" + results.toString() + ")";
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
 //out.close();
} 

}
%>

<%!

public JSONObject prepareStocksData( javax.servlet.jsp.JspWriter out, ResultSet resultSet1, JSONObject results, String trendValue, String indicatorCode) throws SQLException, ParseException, IOException {
        	//out.println("START ");
		
		LinkedHashMap<String, String> years = new LinkedHashMap<String, String>(); // year, year_label
		LinkedHashMap<String, String> yearsTimestamp = new LinkedHashMap<String, String>(); // year, timestamp
		LinkedHashMap<String, HashMap<String, Double>> closing = new LinkedHashMap<String, HashMap<String, Double>>(); // year, <next-year, val>
		LinkedHashMap<String, Double> utils = new LinkedHashMap<String, Double>(); // year, val
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat newDateformat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newDateFormatLabel = new SimpleDateFormat("MMM yyyy");
                JSONArray dataArray = new JSONArray();
		
		
		 //Query 1 RESULTS: prepare data	
		 while(resultSet1.next()) {
			   
			    HashMap<String, Double> map = new  HashMap<String, Double>();
			    map.put((String)resultSet1.getString(3), Double.valueOf((String)resultSet1.getString(5)));
			    
	            String year_label = (String)resultSet1.getString(2);		  
	            
	            boolean firstRow = resultSet1.isFirst(); 
	            
	            String yearSt = (String)resultSet1.getString(1);
	            java.util.Date yearDate = newDateformat.parse(yearSt);
	           
	         
	            if(firstRow){
	         	    year_label += " f'cast";
	         	    results.put("enddate", (String)resultSet1.getString(1));
	         	    results.put("enddatelabel", newDateFormatLabel.format(yearDate) );	       
	             } 
	             
	            if(resultSet1.isLast()){
	               results.put("startdate", (String)resultSet1.getString(1));
	               results.put("startdatelabel", newDateFormatLabel.format(yearDate));
	            }
			    
			    years.put((String)resultSet1.getString(1), year_label);
			    yearsTimestamp.put((String)resultSet1.getString(1), (String)resultSet1.getString(7));
			    closing.put((String)resultSet1.getString(1), map);
			    utils.put((String)resultSet1.getString(1), Double.valueOf((String)resultSet1.getString(4)));
			
			
			
		    results.put("indicator", (String)resultSet1.getString(6));
		   
		
		   }
		
		
		
			
		// 5 data arrays (for each crop):
		// - year categories list
		// - 4 data series list
		
	
	       
	        JSONArray ratioDataArray = new JSONArray();
		JSONArray categoriesArray = new JSONArray();
		
		 
	    ArrayList<String> keys = new ArrayList<String>(closing.keySet());
	
	    for(int j=closing.keySet().size()-1; j>=0;j--){ // loop in reverse (as the query is DESC)
	        String year = keys.get(j);
	    	categoriesArray.add(years.get(year));
	      	HashMap<String, Double> data = closing.get(year);
			
			for(String next_year: data.keySet()){
			JSONArray indexDataArray = new JSONArray();
			
		        Double utilVal = null;
				if(utils.containsKey(next_year)){
					utilVal = utils.get(next_year);
		        }
		        else 
		           utilVal = Double.valueOf(trendValue);
				  
				double ratio = data.get(next_year) / utilVal;
				double percent =  ratio * 100;
				
				int percent_whole = (int) Math.round(ratio * 100);
				
			          JSONObject ratioData = new JSONObject();
				   ratioData.put("year_id",  year);
				   ratioData.put("ratio", ratio);
				   ratioData.put("percent", percent);
				   ratioData.put("rounded_percent", percent_whole);
				   ratioData.put("util", utilVal);
				   ratioData.put("closing",  data.get(next_year));  
				  
				  
				  
				  
				  ratioDataArray.add(percent_whole);
				   
			   
				   String dateSt = yearsTimestamp.get(year);
				   java.util.Date date = dateFormat.parse(dateSt);
				   
				   long epoch = date.getTime();
				   
				 //  indexDataArray.add(year);
				    
				   
				   indexDataArray.add(Long.valueOf(epoch));
	                        //   dataArray.add(indexDataArray);      
	           
	           
				  //Double dVal = Double.valueOf(percent);
				  // indexDataArray.add(dVal);
				 
				  indexDataArray.add(percent_whole);
							 
			        //  dataArray.add(indexDataArray);
			          
			          
			          JSONObject dataValues = new JSONObject();
			          dataValues.put("x", Long.valueOf(epoch));
			          dataValues.put("y", percent_whole);
			          dataValues.put("name", years.get(year));
			          dataValues.put("unit", "%");
			          
			          dataArray.add(dataValues);
			          
			  
				   
			}	
			
		}
		// out.println("<br/> HERE ");
		
		 JSONArray dataSeries = new JSONArray();
		 JSONObject dataSeriesDef = new JSONObject();
	         dataSeriesDef = new JSONObject();
		 dataSeriesDef.put("indicator", "World Stock-to-use ratio");	
		 dataSeriesDef.put("units", "Percent");
		 dataSeriesDef.put("data", ratioDataArray);
//		 dataSeriesDef.put("source", "FAO-CBS (distributed by AMIS Statistics)");
         dataSeriesDef.put("source", "FAO-AMIS (distributed by AMIS Statistics)");
		 dataSeriesDef.put("sourceurl", "http://statistics.amis-outlook.org/data/index.html");
		 dataSeriesDef.put("charttype", "spline");
		 		 
		 dataSeries.add(dataSeriesDef);
	
		
		
		//results.put("indicator", "World Stock-to-use ratio ");
		//results.put("periodicity", periodicity);
		//		results.put("baseperiod", base_period);
		
//		results.put("source", "FAO-CBS (distributed by AMIS Statistics)");
        results.put("source", "FAO-AMIS (distributed by AMIS Statistics)");
		results.put("sourceurl", "http://statistics.amis-outlook.org/data/index.html");
		results.put("units", "Percent");
		results.put("data", dataArray);
		results.put("periodicity", "Annual");
		
		if(indicatorCode.equals("6")){  
		  results.put("visible", "false");		          
		} else {
		  results.put("visible", "true");
		}
			       
		
		//results.put("startdate", startdate);
		//results.put("enddate", enddate);
		
		
	        results.put("dataseries", dataSeries);
	        results.put("categories", categoriesArray);
	        
	        
	     
			   
	        return results;
}

/** Added an interval of 2 hours to avoid the GMT issue (which is -1 hour), when getting the epoch value which sets to GMT.
e.g. 01-03-2007 00:00:00 results in an epoch value representing 28-02-2007 23:00:00 GMT, so results appear 1 day less than expected **/

public String getIndicesData(String indicatorcode, String amisTableName, String amisDefinitionTableName, String timeSpan, String domaintablecode){
         StringBuffer buffer = new StringBuffer();
            
            /**SELECT (to_date(to_char(date, 'YYYY-MM'), 'YYYY-MM') + interval '2 hour')::timestamp as date_timestamp, date, 
            CASE WHEN  D.measurement_unit is not null THEN ROUND(CAST(value as numeric), 2) 
            ELSE ROUND(CAST(value as numeric), 1) END AS index, D.indicator_name, D.source, D.measurement_unit, 
            I.Index_Base_Period || ' = ' || I.Index_Base_Value as base_value, I.periodicity, I.source_url  
            FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_9a05c811 D, 
            AMIS_MARKET_MONITOR_INDICATOR_DEFINITIONS_62034943 I WHERE  
            D.indicator_code='GCFPPGVC' AND D.indicator_code = I.indicator_code AND to_date(to_char(D.date, 'YYYY-MM'), 'YYYY-MM') 
            BETWEEN  (SELECT MAX(to_date(to_char(date, 'YYYY-MM'), 'YYYY-MM')) - interval '12 month' FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_9a05c811 WHERE indicator_code = 'GCFPPGVC')  AND  (SELECT MAX(to_date(to_char(date, 'YYYY-MM'), 'YYYY-MM')) FROM AMIS_MONTHLY_MARKET_MONITOR_INDICATORS_9a05c811 WHERE indicator_code = 'GCFPPGVC')  ORDER BY date ASC;
            **/
            
              if(domaintablecode.contains("DAILY")) {    
                buffer.append(" SELECT (date + interval '2 hour')::timestamp as date_timestamp, ");
              } else if(domaintablecode.contains("MONTHLY")){
                buffer.append(" SELECT (to_date(to_char(date, 'YYYY-MM'), 'YYYY-MM') + interval '2 hour')::timestamp as date_timestamp, ");
              } 
              
            // buffer.append(" SELECT (date + interval '2 hour')::timestamp as date_timestamp, ");
	     buffer.append(" CASE WHEN  D.measurement_unit is not null THEN ROUND(CAST(value as numeric), 2) ELSE ROUND(CAST(value as numeric), 1) END AS index, D.indicator_name, D.source, D.measurement_unit,  "); 
	     buffer.append(" CASE WHEN  I.Index_Base_Value is not null THEN I.Index_Base_Period || ' = ' || I.Index_Base_Value ELSE I.Index_Base_Period END AS base_value, I.periodicity, I.source_url, date + interval '2 hour' as original_date ");
	     buffer.append(" FROM "+amisTableName + " D, "+amisDefinitionTableName + " I");
	     buffer.append(" WHERE  D.indicator_code='"+indicatorcode+"'");
	     buffer.append(" AND D.indicator_code = I.indicator_code");
	     if(timeSpan!=null){
	      if(domaintablecode.contains("DAILY")) {    
	         buffer.append(" AND D.date BETWEEN ");
	       	      buffer.append(" (SELECT MAX(date) - interval '"+timeSpan+"' FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"') ");
	       	      buffer.append(" AND ");
	      	      buffer.append(" (SELECT MAX(date) FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"') ");	
	      } else if(domaintablecode.contains("MONTHLY")){	      
	        	buffer.append(" AND to_date(to_char(D.date, 'YYYY-MM'), 'YYYY-MM') BETWEEN ");
	      		buffer.append(" (SELECT MAX(to_date(to_char(date, 'YYYY-MM'), 'YYYY-MM')) - interval '"+timeSpan+"' FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"') ");
	      		buffer.append(" AND ");
	      		buffer.append(" (SELECT MAX(to_date(to_char(date, 'YYYY-MM'), 'YYYY-MM')) FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorcode+"') ");	
	      } 
             
	     } 
	     buffer.append(" ORDER BY date ASC ");
	   
	      
	     return buffer.toString();

}

public JSONObject prepareIndicesData(javax.servlet.jsp.JspWriter out, ResultSet resultSet1,  JSONObject results, String domaintablecode) throws SQLException, ParseException, IOException {
        //out.print("resultSet1 . START  ");
		  JSONArray dataArray = new JSONArray();
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  SimpleDateFormat dateMonthlyFormat = new SimpleDateFormat("yyyy-MM");
                  SimpleDateFormat newDateformat = new SimpleDateFormat("yyyy-MM-dd");
                 
                  SimpleDateFormat monthlyDateFormatLabel = new SimpleDateFormat("MMM yyyy");
                  SimpleDateFormat dailyDateFormatLabel = new SimpleDateFormat("dd MMM yyyy");
       
		 //Query 1 RESULTS: prepare data	
		 String indicator_name = null;
		 String source = null;
		 String source_url = null;
		 String unit = null;
		 String base_period = null;
		 String periodicity = null;
		 String startdate = null;
		 String enddate = null;
		 String startdatelabel = null;
		 String enddatelabel = null;
		 		 
		 while(resultSet1.next()) { 
		          JSONArray indexDataArray = new JSONArray();
		          
		          indicator_name = (String)resultSet1.getString(3);
		          source = (String)resultSet1.getString(4);
		          unit = (String)resultSet1.getString(5);    
		          base_period = (String)resultSet1.getString(6);   
		          periodicity = (String)resultSet1.getString(7); 
		          source_url = (String)resultSet1.getString(8);

                  String original_date = (String)resultSet1.getString(9);
                  java.util.Date orig_date = dateFormat.parse(original_date);


             //String dateSt = (String)resultSet1.getString(1);
			  // java.util.Date date = dateFormat.parse(dateSt);
			  // long epoch = date.getTime();
	                  // indexDataArray.add(Long.valueOf(epoch));
	                   
	                    String dateSt = (String)resultSet1.getString(1);
			    java.util.Date date = null;
			    
			     
	                   
	                 //  if(domaintablecode.contains("MONTHLY")) {
	                   //  out.println("dateSt = "+dateSt);
			   //  date = dateMonthlyFormat.parse(dateSt); 
			    // out.println("date = "+date);
		          // } else {
			     date = dateFormat.parse(dateSt);           
		          // }
		           
		           long epoch = date.getTime();
	                   indexDataArray.add(Long.valueOf(epoch));		
	                  
		           if(resultSet1.isFirst()){
		             startdate = newDateformat.format(orig_date);
		             if(domaintablecode.contains("DAILY"))
		                startdatelabel = dailyDateFormatLabel.format(orig_date);
		             else
		               startdatelabel = monthlyDateFormatLabel.format(orig_date);
		           }
		           
		           if(resultSet1.isLast()){
		             enddate = newDateformat.format(orig_date);
		             
		              if(domaintablecode.contains("DAILY"))
			         enddatelabel = dailyDateFormatLabel.format(orig_date);
			      else
		               enddatelabel = monthlyDateFormatLabel.format(orig_date);
		               
		             
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
		  	
		  	 
		  
			  Double dVal = null;
			  String val = (String)resultSet1.getString(2);
		         
			  if(val!=null){
			   dVal = Double.valueOf((String)resultSet1.getString(2));
			  } else
			  dVal = null;
			  
			  
			  indexDataArray.add(dVal);
			 // dataArray.add((String)resultSet1.getString(1));
			  dataArray.add(indexDataArray);
			  
			
		   }
		
		
		results.put("indicator", indicator_name);
		results.put("periodicity", periodicity);
		results.put("baseperiod", base_period);
		results.put("source", source);
		results.put("sourceurl", source_url);
		results.put("units", unit);
		results.put("data", dataArray);
		results.put("startdate", startdate);
	        results.put("enddate", enddate);
	        results.put("startdatelabel", startdatelabel);
	        results.put("enddatelabel", enddatelabel);
	        results.put("visible", "true");
	        
                return results;
}


/**
  Calculation for all Products except for Soybean: Closing stocks for a year / Utilization for year + 1
  Calculation for Soybean: Closing stocks for a year / Utilization for a year
  Where region = World
		product_code = 1 product
**/


/** Added an interval of 2 hours to timestamp to avoid the GMT issue (which is -1 hour), when getting the epoch value which sets to GMT.
e.g. 01-03-2007 00:00:00 results in an epoch value representing 28-02-2007 23:00:00 GMT, so results appear 1 day less than expected **/

public String getWorldStockToUseRatioQuery(String product_code, String amisTableName){
            StringBuffer buffer = new StringBuffer();
            
             buffer.append(" SELECT year as year_id,  CAST(extract(year from year) as varchar) ||'/'|| substring(CAST(extract(year from (year + interval '1 years')) as varchar) from '..$') as year, ");
			
             //soybeans
		     if(product_code.equals("6")){  
			     buffer.append(" year as next_year, ");
			 } else {
				  buffer.append(" (year + interval '1 years')::date as next_year, ");
			 }
		     
		         buffer.append(" SUM(case when element_code='20' THEN value end) as util, ");
			 buffer.append(" SUM(case when element_code='16' THEN value end) as closing_stocks, ");
			 buffer.append(" (CASE WHEN product_code='4' THEN 'Rice (Milled equivalent)' WHEN product_code='1' THEN 'Wheat' ELSE product_name end) as product_name, ");
			 buffer.append(" (year + interval '2 hour')::timestamp as date_timestamp ");
			 buffer.append(" FROM "+amisTableName + " ");
			 buffer.append(" WHERE  region_code='999000' AND product_code='"+product_code+"' and  database='CBS'  and value_type='TOTAL'  ");
			 buffer.append(" GROUP BY year, product_code, product_name ORDER BY year DESC ");
			 buffer.append(" LIMIT 5;");
			 
			 return buffer.toString();

}




%>

