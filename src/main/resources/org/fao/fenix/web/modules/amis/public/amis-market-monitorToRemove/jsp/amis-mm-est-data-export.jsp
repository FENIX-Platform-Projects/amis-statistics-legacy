<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AMIS Market Monitor Export to Excel</title>
<link rel="stylesheet" type="text/css" href="http://statistics.amis-outlook.org/data/amis-market-monitor/css/amis-mm-export-data-table-theme.css">
</head>
<body>
	<%
String[] indicatorcodes = null;
String[] marketcodes = null;
String domaintablecode = "";
String minDate = "";
String maxDate = "";
String title = "";
String period = "";


if(request.getParameter("indicatorcode")!=null && request.getParameter("marketcode")!=null && request.getParameter("mindate")!=null && request.getParameter("maxdate")!=null && request.getParameter("title")!=null) {

	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-Disposition", "inline; filename="+ "amis_market_monitor_download.xls");

	indicatorcodes = request.getParameter("indicatorcode").trim().split(",");
	marketcodes = request.getParameter("marketcode").trim().split(",");

	minDate = request.getParameter("mindate");
	maxDate = request.getParameter("maxdate");
	
 
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        SimpleDateFormat est_df = new SimpleDateFormat("dd/MM/yyyy");
        
 
 	java.util.Date fMinDate = df.parse(minDate);
 	
 	java.util.Date fMaxDate = df.parse(maxDate);
 	
 	 
 	minDate = est_df.format(fMinDate);
 	maxDate = est_df.format(fMaxDate);
 	
	title = "AMIS Market Monitor: "+ request.getParameter("title");
	period = "From "+minDate+" to "+maxDate;

	Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet1 = null;
	      
        
  try {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@lprdbwo1:3301:wpr01";
	String username = "ciwp_read";
	String password = "maradona";

	Class.forName(driver).newInstance();
	connection = DriverManager.getConnection(url,username,password);

	// out.println("connection = "+connection);


	//Query 1 
	String dataQuery1 = getExportData(indicatorcodes, marketcodes, minDate, maxDate);

      //  out.println("dataQuery1 = "+dataQuery1);
 
	preparedStatement1 = connection.prepareStatement(dataQuery1);
	resultSet1 = preparedStatement1.executeQuery();
	
	%>
	     <table id="amis-mm-export-data-table">
		<thead>
			<tr>
			   <th class="amis-mm-export-data-table-header" colspan="6"><%=title%></th>
			</tr>
			<tr>
			   <th class="amis-mm-export-data-table-header" colspan="6"><%=period%></th>
			</tr>
			<tr>
				<th  class="amis-mm-export-data-table-column">Date</th>
				<th  class="amis-mm-export-data-table-column">Indicator</th>
				<th  class="amis-mm-export-data-table-column">Source</th>
				<th  class="amis-mm-export-data-table-column">Value</th>
				<th  class="amis-mm-export-data-table-column">Measurement Unit</th>
				<th  class="amis-mm-export-data-table-column">Periodicity</th>
			</tr>
	        </thead>
		<tbody>
	    	<%
		       while(resultSet1.next()) { 
		         
		          
			%>
			<tr>							
				<td><%=resultSet1.getString(1)%></td>
				<td><%=resultSet1.getString(2)%></td>
				<td><%=resultSet1.getString(3)%></td>
				<td><%=resultSet1.getString(4)%></td>
				<td><%=resultSet1.getString(5)%></td>				
				<td><%=resultSet1.getString(6)%></td>
			</tr>
			<%
			 }
			%>
		</tbody>
	</table>
	<%
	  
	} catch(ClassNotFoundException e){e.printStackTrace();}
catch (SQLException ex)
{
ex.getMessage();
ex.getSQLState();
ex.getErrorCode();
}
finally
{
 resultSet1.close();
preparedStatement1.close();
connection.close();
out.flush(); 
out.close();
} 

}
	%>
</body>
</html>

<%!

public String getExportData(String[] indicatorCodes, String[] marketCodes, String minDate, String maxDate){
         StringBuffer buffer = new StringBuffer();
            
          buffer.append("SELECT ");
          buffer.append(" to_char(wp.week_end_date, 'dd/mm/yyyy') sort_date, ");
	  buffer.append(" i.long_name_e || ' ' || m.long_name_e market_name,");
	  buffer.append(" s.long_name_e source_name, ");
	  buffer.append(" to_char(wp.num,'fm999990.00') price_value,");
	  buffer.append(" c.long_name_e ||' '|| u.long_name_e unit_name,");
	  buffer.append(" CASE WHEN mi.price_type='M' THEN 'Monthly' ELSE 'Weekly' END AS periodicity ");	  
	  buffer.append(" FROM  ciwp.ciwp wp,");
	  buffer.append(" ciwp.market_item mi,");
	  buffer.append("  ciwp.item i,");
	  buffer.append(" ciwp.market m,");
	  buffer.append("  ciwp.ccy c,");
	  buffer.append(" ciwp.unit u, ");
	  buffer.append(" ciwp.source s");
	  buffer.append(" WHERE ");
	  buffer.append(" wp.week_end_date BETWEEN to_date('"+minDate+"', 'dd/mm/yyyy') AND to_date('"+maxDate+"', 'dd/mm/yyyy')" );
	  buffer.append(" AND wp.item IN (");
	  	     int i = 0;
	  	     for(String indicator: indicatorCodes){
	  	       buffer.append("'"+indicator+"'");
	  	       
	  	       if(i < indicatorCodes.length-1)
	  	         buffer.append(", ");
	  	       
	  	       i ++;
	  	     }
	  buffer.append(" ) ");
	  buffer.append(" AND wp.market IN (");
	  	  	     int k = 0;
	  	  	     for(String market: marketCodes){
	  	  	       buffer.append("'"+market+"'");
	  	  	       
	  	  	       if(k < marketCodes.length-1)
	  	  	         buffer.append(", ");
	  	  	       
	  	  	       k ++;
	  	  	     }
	  buffer.append(" ) ");  
	  buffer.append(" AND mi.item = wp.item");
	  buffer.append(" AND mi.market = wp.market");
	  buffer.append(" AND i.item = mi.item");
	  buffer.append(" AND m.market = mi.market");
	  buffer.append(" AND c.ccy = mi.ccy ");
	  buffer.append(" AND u.unit = mi.unit");
	  buffer.append(" AND s.source = mi.source ORDER BY wp.week_end_date DESC");
	  
	  return buffer.toString();

}


%>


