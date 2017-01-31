<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.lang.System" %>

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
String domaintablecode = "";
String minDate = "";
String maxDate = "";
String title = "";
String period = "";
String timespan = null;
String timeSpanPeriod = null;


if(request.getParameter("indicatorcode")!=null && request.getParameter("domaintable")!=null && request.getParameter("timespan")!=null  && request.getParameter("title")!=null) {

	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-Disposition", "inline; filename="+ "amis_market_monitor_download.xls");

	indicatorcodes = request.getParameter("indicatorcode").trim().split(",");
	domaintablecode = request.getParameter("domaintable");

	title = "AMIS Market Monitor: "+ request.getParameter("title");

    timespan = request.getParameter("timespan");

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


//Query 1 
	String dataQuery1 = "";

    dataQuery1 = getExportData(indicatorcodes, amisTableName, amisDefTable, timeSpanPeriod, indicatorcodes[0]);

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
		          String mUnit = resultSet1.getString(5);
		          
		          if(mUnit==null) {
		            mUnit = "Index";
		          }
		          
			%>
			<tr>							
				<td><%=resultSet1.getString(1)%></td>
				<td><%=resultSet1.getString(2)%></td>
				<td><%=resultSet1.getString(3)%></td>
				<td><%=resultSet1.getString(4)%></td>
				<td><%=mUnit%></td>				
				<td><%=resultSet1.getString(6)%></td>
			</tr>
			<%
			 }
			%>
		</tbody>
	</table>
	<%
	  } 
	 }
	} catch(ClassNotFoundException e){e.printStackTrace();}
catch (SQLException ex)
{
ex.getMessage();
ex.getSQLState();
ex.getErrorCode();
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
out.close();
} 

}
	%>
</body>
</html>

<%!

public String getExportData(String[] indicatorCodes, String amisTableName, String amisDefinitionTableName, String timeSpan, String indicatorCode){
         StringBuffer buffer = new StringBuffer();

        buffer.append(" SELECT D.date, ");
	     buffer.append(" D.indicator_name, D.source,  "); 
	     buffer.append(" CASE WHEN  D.measurement_unit is not null THEN ROUND(CAST(value as numeric), 2) ELSE ROUND(CAST(value as numeric), 1) END AS value, D.measurement_unit, I.periodicity "); 	   
	     buffer.append(" FROM "+amisTableName + " D, "+amisDefinitionTableName + " I");
	     buffer.append(" WHERE D.date BETWEEN ");
         buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) - interval '"+timeSpan+"' FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorCode+"')) || '-01-01', 'YYYY-mm-dd')) ");
         buffer.append(" AND ");
         buffer.append(" (to_date(EXTRACT(YEAR FROM (SELECT MAX(date) FROM "+amisTableName+ " WHERE indicator_code = '"+indicatorCode+"')) || '-12-31', 'YYYY-mm-dd')) ");
         buffer.append(" AND D.indicator_code IN (");
	     int i = 0;
	     for(String indicator: indicatorCodes){
	       buffer.append("'"+indicator+"'");
	       
	       if(i < indicatorCodes.length-1)
	         buffer.append(", ");
	       
	       i ++;
	     }
	     buffer.append(" ) ");
	     
	     buffer.append(" AND D.indicator_code = I.indicator_code");
	     buffer.append(" ORDER BY D.indicator_code, D.date DESC ");
			 
	     return buffer.toString();

}


%>


