package org.fao.fenix.web.modules.amis.server.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

public class AMISUtils {
	
	private final static Logger LOGGER = Logger.getLogger(AMISUtils.class);

	public static void getHtmlTable(List<Object[]> rs, AMISResultVO rvo, Boolean showColumnHeaders, String style, List<String> header) throws Exception {
		StringBuffer html = new StringBuffer();
		int rowCount = 0;
		List<String> headers = new ArrayList<String>();
		List<List<String>> contents = new ArrayList<List<String>>();
		
		html.append("<p><table class='"+style+"'>");
		
		// ResultSetMetaData rsmd = rs.getMetaData();
		// int columnCount = rsmd.getColumnCount();
		 int columnCount = rs.get(0).length;
		  
		 // colgroup			 
		 html.append("<colgroup>");
		 for(int i = 1; i <columnCount + 1; i++) {
			 html.append("<col class='"+ style +"_col" + i +"'>");
		 }
		html.append("</colgroup>");
			 
		 // table header
		 if ( showColumnHeaders ) {
			html.append("<TR>");
			for (int i = 0; i < columnCount; i++) {
				// html.append("<TH class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i + 1) + "</TH>");
				 html.append("<TH class='"+ style +"_header' nowrap>" + header.get(i) + "</TH>");
				// headers.add(rsmd.getColumnLabel(i + 1));
				 headers.add(header.get(i));
			   }
			html.append("</TR>");
		 }
		 // the data
		 
		 html.append("<tbody>");
		 
		 for(Object res[] : rs){
			// while (rs.next()) {
		  rowCount++;
		  if ( rowCount % 2 == 0)
			  html.append("<TR class='"+ style +"_row1'>");
		  else
			  html.append("<TR class='"+ style +"_row2'>");
		   
		  List<String> content = new ArrayList<String>();
		  for (int i = 0; i < columnCount; i++) {
				// html.append("<TD nowrap>" + rs.getString(i + 1) + "</TD>");
				 html.append("<TD nowrap>" + res[i] + "</TD>");
				// content.add(rs.getString(i + 1)); 
				 if(res[i] == null){
					 content.add("null");	 
				 } else {
					 content.add(res[i].toString());	
				 }
				  
		    }
		  
		  
		  
		  contents.add(content);
		  html.append("</TR>");
		  }
		 html.append("</table></P>");
		 
		 LOGGER.info("ROWCOUNT:" + rowCount);
		 LOGGER.info("TABLE:" + html.toString());
		 
		 
		 rvo.setTableHTML(html.toString());
		 rvo.setRows(Long.valueOf(rowCount));
		 
		 rvo.setTableHeaders(headers);
		 rvo.setTableContents(contents);
		 
	}
	
	public static void getTable(List<Object[]> rs, AMISResultVO rvo, Boolean showColumnHeaders, String style, List<String> header) throws Exception {
		StringBuffer html = new StringBuffer();
		int rowCount = 0;
		List<String> headers = new ArrayList<String>();
		List<List<String>> contents = new ArrayList<List<String>>();
		
		html.append("<p><table class='"+style+"'>");
		
		// ResultSetMetaData rsmd = rs.getMetaData();
		// int columnCount = rsmd.getColumnCount();
		// int columnCount = rs.get(0).length;
		int columnCount =0;
		 
		 // colgroup			 
		 html.append("<colgroup>");
		 for(int i = 1; i <columnCount + 1; i++) {
			 html.append("<col class='"+ style +"_col" + i +"'>");
		 }
		 html.append("</colgroup>");
		 
		 // table header
		 if ( showColumnHeaders ) {
			html.append("<TR>");
			for (int i = 0; i < columnCount; i++) {
				// html.append("<TH class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i + 1) + "</TH>");
				 html.append("<TH class='"+ style +"_header' nowrap>" + header.get(i) + "</TH>");
				// headers.add(rsmd.getColumnLabel(i + 1));
				 headers.add(header.get(i));
			   }
			html.append("</TR>");
		 }
		 // the data
		 
		 html.append("<tbody>");
		 rowCount=0;
//		 for(Object res[] : rs){
//		// while (rs.next()) {
//		  rowCount++;
//		  if ( rowCount % 2 == 0)
//			  html.append("<TR class='"+ style +"_row1'>");
//		  else
//			  html.append("<TR class='"+ style +"_row2'>");		  
//		  
//		  List<String> content = new ArrayList<String>();
//		  contents.add(content);
//		  html.append("</TR>");
//		  }
		 List<String> content = new ArrayList<String>();
		  contents.add(content);
		 html.append("</table></P>");
		 
		 LOGGER.info("ROWCOUNT:" + rowCount);
		 LOGGER.info("TABLE:" + html.toString());
		 
		 
		 rvo.setTableHTML(html.toString());
		 rvo.setRows(Long.valueOf(rowCount));
		 
		 rvo.setTableHeaders(headers);
		 rvo.setTableContents(contents);
		 
	}
	
}
