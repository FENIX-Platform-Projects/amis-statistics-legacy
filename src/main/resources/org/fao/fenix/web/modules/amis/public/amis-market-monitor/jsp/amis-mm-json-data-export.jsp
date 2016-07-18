<%@ page contentType="text/html; charset=UTF-8" language="java"  %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.parser.ParseException"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AMIS Market Monitor Export Data to Excel</title>
<link rel="stylesheet" type="text/css" href="http://statistics.amis-outlook.org/data/amis-market-monitor/css/amis-mm-export-data-table-theme.css">

</head>
<body>
<%

String series = "";
String title = "";
String[] dates = null;
String period = "";


if(request.getParameter("series")!=null && request.getParameter("categories")!=null && request.getParameter("title")!=null) {

		
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename="+ "amis_market_monitor_download.xls");
	 
	series = request.getParameter("series");
	dates = request.getParameter("categories").trim().split(",");
	
	
        JSONArray json = (JSONArray)new JSONParser().parse(series);
	
	
	title = "AMIS Market Monitor: "+ request.getParameter("title");
	period = "From "+dates[0] + " to "+dates[dates.length-1];
	 
	
try {
 
         %>
	 	<table id="amis-mm-export-data-table">
	 		<thead>
	 		        <tr>
	 			   <th class="amis-mm-export-data-table-header" colspan="5"><%=title%></th>
	 			</tr>
	 			<tr>
	 			   <th class="amis-mm-export-data-table-header" colspan="5"><%=period%></th>
	 			</tr>
	 			<tr>
	 				<th  class="amis-mm-export-data-table-column">Date</th>
	 				<th  class="amis-mm-export-data-table-column">Indicator</th>
	 				<th  class="amis-mm-export-data-table-column">Source</th>
	 				<th  class="amis-mm-export-data-table-column">Value</th>
	 				<th  class="amis-mm-export-data-table-column">Measurement Unit</th>
	 			</tr>
	 		</thead>
	 		<tbody>
	 	    	<%
	 	    	        
	 		        for(int k = 0; k<json.size(); k++){
	 		           JSONObject jObj= (JSONObject)json.get(k);
	 		          
	 		           JSONArray jsonArray = (JSONArray)jObj.get("data");
	 		           
	 		             
	 		        for(int p = 0; p<jsonArray.size(); p++){
	 		         
	 		    	%>
	 			<tr>							
	 				<td><%=dates[p]%></td>
	 				<td><%=jObj.get("indicator")%></td>
	 		    	        <td><%=jObj.get("source")%></td>
	 				<td><%=jsonArray.get(p)%></td>
	 				<td><%=jObj.get("units")%></td>				
	 			</tr>
	 			<%
	 			
	 			 }
	 			 
	 			}
	 			%>
	 		</tbody>
	 	</table>
	  <%   
}

catch(Exception e){e.printStackTrace();}
finally
{
out.flush(); 
out.close();
} 


}
%>
</body>
</html>



