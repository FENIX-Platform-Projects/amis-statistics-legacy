<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.* " %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%
String productcode = "";

if(request.getParameter("productcode")!=null) {
    productcode = request.getParameter("productcode");


//String callbackName = queryMap['callback']; //jquery will pass in some name in our .getJSON call below




try {
String driver = "org.postgresql.Driver";
String url = "jdbc:postgresql://localhost/fenix?user=fenix&password=Qwaszx";
String username = "fenix";
String password = "Qwaszx";
String DataField = null;

String query1 =
"SELECT tablename from customdataset WHERE code='AMIS'";



//out.print("Query: " + query);


Connection connection = null;
PreparedStatement preparedStatement1 = null;
ResultSet resultSet1 = null;

Class.forName(driver).newInstance();
connection = DriverManager.getConnection(url);

preparedStatement1 = connection.prepareStatement(query1);
resultSet1 = preparedStatement1.executeQuery();

if(resultSet1.next()) {

String amisTableName = resultSet1.getString("tablename");

 String dataQuery =
 "SELECT a.product_name, a.start_date, a.end_date, "+
 "       ROUND(CAST ((a.amis_countries_prod / a.world_prod) * 100 as numeric), 0) as amis_prod_percent, "+
 "       ROUND(CAST ((a.non_amis_prod / a.world_prod) * 100 as numeric), 0) as non_amis_prod_percent, "+
 "       ROUND(CAST ((a.amis_countries_export / a.world_export) * 100 as numeric), 0) as amis_exports_percent, "+
 "       ROUND(CAST ((a.non_amis_export / a.world_export) * 100 as numeric), 0) as non_amis_exports_percent, "+
 " ROUND(CAST ((a.amis_countries_import / a.world_import) * 100 as numeric), 0) as amis_imports_percent, "+
 "      ROUND(CAST ((a.non_amis_import /a.world_import) * 100 as numeric), 0) as non_amis_imports_percent, "+
 "      ROUND(CAST (a.non_amis_prod as numeric), 2) as non_amis_prod_data, "+
 "      ROUND(CAST (a.amis_countries_prod as numeric), 2) as amis_countries_prod_data, "+
 "      ROUND(CAST (a.non_amis_export as numeric), 2) as non_amis_export_data, "+
 "      ROUND(CAST (a.amis_countries_export as numeric), 2) as amis_countries_export_data, "+
 "      ROUND(CAST (a.non_amis_import as numeric), 2) as non_amis_imports_data, "+
 "      ROUND(CAST (a.amis_countries_import as numeric), 2) as amis_countries_import_data "+ 
 "FROM ("+
 "    SELECT product_name, extract(year from MAX(year)) as end_date, extract(year from MAX(year) - interval '2 years') as start_date, "+
 "    sum(case when region_name='World' and element_code='5' THEN (value / 3) end) as world_prod, "+
 "    sum(case when region_name not in ('World', 'China Mainland', 'South America', 'Pakistan')  and element_code='5' THEN (value / 3) end) as amis_countries_prod, "+
 "    sum(case when region_name='World' and element_code='5' THEN (value / 3) end) -  sum(case when region_name not in ('World', 'China Mainland', 'South America', 'Pakistan')  and element_code='5' THEN (value / 3) end) AS non_amis_prod, "+
 "sum(case when region_name='World' and element_code='10' THEN (value / 3) end) As world_export, "+
 "    sum(case when region_name not in ('World', 'China Mainland', 'South America', 'Pakistan')  and element_code='10' THEN (value / 3) end) as amis_countries_export, "+
 "    sum(case when region_name='World' and element_code='10' THEN (value / 3) end) -  sum(case when region_name not in ('World', 'China Mainland', 'South America', 'Pakistan')  and element_code='10' THEN (value / 3) end) AS non_amis_export, "+
 "sum(case when region_name='World' and element_code='7' THEN (value / 3) end) As world_import, "+
 "    sum(case when region_name not in ('World', 'China Mainland', 'South America', 'Pakistan')  and element_code='7' THEN (value / 3) end) as amis_countries_import, "+
 "    sum(case when region_name='World' and element_code='7' THEN (value / 3) end) -  sum(case when region_name not in ('World', 'China Mainland', 'South America', 'Pakistan')  and element_code='7' THEN (value / 3) end) AS non_amis_import "+ 
 "FROM "+ amisTableName + " "+
 "WHERE "+
 "    product_code='"+productcode+"' AND "+
 "    database = 'CBS' "+
 "AND year in (select distinct(year) from "+amisTableName+" WHERE database='CBS' AND product_code='"+productcode+"' GROUP BY year HAVING year <=  (SELECT MAX(year) FROM  "+amisTableName+" where database='CBS' and product_code='"+productcode+"') and year >= ((SELECT MAX(year) FROM  "+amisTableName+" where database='CBS' AND product_code='"+productcode+"')- interval '2 years')) "+
 "GROUP BY product_name "+
 ") a;";

 
PreparedStatement preparedStatement = null;
ResultSet resultSet = null;

preparedStatement = connection.prepareStatement(dataQuery);
resultSet = preparedStatement.executeQuery();

if(resultSet.next())
   
  response.setHeader("Access-Control-Allow-Origin", "*");
  response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
  //response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,Host,User-Agent,Accept,Accept-Language,Accept-Encoding,Accept-Charset,Keep-Alive,Connection,Referer,Origin"); 
  response.setHeader("Access-Control-Allow-Headers", "*"); 
  response.setHeader("Cache-Control","no-cache,no-store"); 


  response.setContentType("application/json");
  response.setCharacterEncoding("UTF-8"); 
  
 JSONObject json=new JSONObject();
 
 String prod_code = "5";
 
 try{
     
     json.put("product_name",resultSet.getString(1));
     json.put("start_date",resultSet.getString(2));
     json.put("end_date",resultSet.getString(3));
   

     JSONArray data = new JSONArray();
       
       
     JSONObject production = new JSONObject();
     production.put("prod_code", "5");
     production.put("amis_prod_percent", resultSet.getString(4));
     production.put("non_amis_prod_percent", resultSet.getString(5));
     production.put("non_amis_prod_data", resultSet.getString(10));
     production.put("amis_prod_data", resultSet.getString(11));
          
     
     data.add(production);
     
     JSONObject export = new JSONObject();
     export.put("export_code", "12");
     export.put("amis_exports_percent", resultSet.getString(6));
     export.put("non_amis_exports_percent", resultSet.getString(7));
     export.put("non_amis_exports_data", resultSet.getString(12));
     export.put("amis_exports_data", resultSet.getString(13));
               
     data.add(export);
     
    JSONObject imports = new JSONObject();
    imports.put("import_code", "8");
    imports.put("amis_imports_percent", resultSet.getString(8));
    imports.put("non_amis_imports_percent", resultSet.getString(9));
   
    imports.put("non_amis_imports_data", resultSet.getString(14));
    imports.put("amis_imports_data", resultSet.getString(15));
          
    data.add(imports);
     
    json.put("data", data);
   
   String elementsTableQuery = "SELECT tablename from customdataset where code='AMIS_ELEMENTS'";
   
   PreparedStatement preparedStatement3 = null;
   ResultSet resultSet3 = null;
   
   preparedStatement3 = connection.prepareStatement(elementsTableQuery);
   resultSet3 = preparedStatement3.executeQuery();
   
   if(resultSet3.next()) {
    
     
     String unitsQuery = "SELECT element_code, element_name, units from "+ resultSet3.getString(1)+" where element_code IN ('5', '12', '8')";
        
     PreparedStatement preparedStatement4 = null;
     ResultSet resultSet4 = null;
        
     preparedStatement4 = connection.prepareStatement(unitsQuery);
     resultSet4 = preparedStatement4.executeQuery();
     
      while(resultSet4.next()) {
      
      String el_code = (String)resultSet4.getString("element_code");
     
      if(el_code.equals("5")) {
          json.put("prod_name", resultSet4.getString(2));
          json.put("prod_unit",  resultSet4.getString(3));
       } else if(el_code.equals("12")){
         json.put("export_name",  resultSet4.getString(2));
         json.put("export_unit",  resultSet4.getString(3));
       }      
       else if(el_code.equals("8")){
          json.put("import_name",  resultSet4.getString(2));
          json.put("import_unit",  resultSet4.getString(3));
       }
       
    
     }
     resultSet4.close();
     preparedStatement4.close();
   }
   
  resultSet3.close();
  preparedStatement3.close();
   
} catch (Exception ex)
{ 

}

//String jsonp = callbackName + "(" + json.toString() + ")";

//response.send( jsonp );


String jsonp = "jsonCallback" + productcode +"(" + json.toString() + ")";

//response.getWriter().write(json.toString());
response.getWriter().write(jsonp);

resultSet.close();
preparedStatement.close();

}

resultSet1.close();
preparedStatement1.close();

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
else {
    out.println("No productCode!");
}

%>

