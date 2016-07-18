<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>

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
String timespan = "";
String timeSpanPeriod = "";
String title = "";
String period = "";


if(request.getParameter("indicatorcode")!=null && request.getParameter("marketcode")!=null && request.getParameter("timespan")!=null && request.getParameter("title")!=null) {

	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-Disposition", "inline; filename="+ "amis_market_monitor_download.xls");

	indicatorcodes = request.getParameter("indicatorcode").trim().split(",");
	marketcodes = request.getParameter("marketcode").trim().split(",");

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


    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     //SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy", Locale.US);
     //SimpleDateFormat est_df = new SimpleDateFormat("dd/MM/yyyy");
        
 
 	//java.util.Date fMinDate = df.parse(minDate);
 	
 	//java.util.Date fMaxDate = df.parse(maxDate);
 	
 	 
 	//minDate = est_df.format(fMinDate);
 	//maxDate = est_df.format(fMaxDate);
 	
	title = "AMIS Market Monitor: "+ request.getParameter("title");
	//period = "From "+minDate+" to "+maxDate;

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
	String dataQuery1 = getExportData(out, indicatorcodes, marketcodes, timeSpanPeriod);

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
 //resultSet1.close();
// preparedStatement1.close();
//connection.close();
out.flush(); 
out.close();
} 

}
	%>
</body>
</html>

<%!

public String getExportData(javax.servlet.jsp.JspWriter out, String[] indicatorCodes, String[] marketCodes, String timeSpanPeriod) throws ParseException, IOException{

    String indicators = "";
    int k = 0;
    for(String indicator: indicatorCodes){
        indicators+="'"+indicator+"'";

        if(k < indicatorCodes.length-1)
            indicators+=", ";

        k ++;
    }

    String markets = "";
    int j = 0;
    for(String market: marketCodes){
        markets+="'"+market+"'";

        if(j < marketCodes.length-1)
            markets+=", ";

        j ++;
    }

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
      buffer.append(" wp.week_end_date ");
      buffer.append(" BETWEEN ");
      buffer.append(" (to_date('01/01/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) - INTERVAL "+timeSpanPeriod+" from ciwp.ciwp wp ");
      buffer.append(" WHERE wp.item IN ("+indicators+")");
      buffer.append(" AND wp.market IN ("+markets+")");
      buffer.append(")), 'dd/mm/yyyy')) ");
      buffer.append(" AND ");
      buffer.append(" (to_date('31/12/' || EXTRACT(YEAR FROM (SELECT MAX(week_end_date) YEAR from ciwp.ciwp wp ");
      buffer.append(" WHERE wp.item IN ("+indicators+")");
      buffer.append(" AND wp.market IN ("+markets+")");
      buffer.append(")), 'dd/mm/yyyy')) ");

      buffer.append(" AND wp.item IN ("+indicators+")");
      buffer.append(" AND wp.market IN ("+markets+")");
	  buffer.append(" AND mi.item = wp.item");
	  buffer.append(" AND mi.market = wp.market");
	  buffer.append(" AND i.item = mi.item");
	  buffer.append(" AND m.market = mi.market");
	  buffer.append(" AND c.ccy = mi.ccy ");
	  buffer.append(" AND u.unit = mi.unit");
	  buffer.append(" AND s.source = mi.source ORDER BY wp.week_end_date DESC");

    //  out.print("Query = "+buffer.toString());

	  return buffer.toString();

}


%>


