package org.fao.fenix.web.modules.amis.server.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

public class AMISFaostatUtils {
	
	private final static Logger LOGGER = Logger.getLogger(AMISFaostatUtils.class);
	
	static HashMap<String, String> pivotLabels;
	
	static {
		pivotLabels = new HashMap<String, String>();
		
		pivotLabels.put("items", "Item");
		pivotLabels.put("icodes", "Item Codes");
		
		pivotLabels.put("countries", "Country");
		pivotLabels.put("ccodes", "Country Codes");
		
		pivotLabels.put("extra", "Element");
		
		pivotLabels.put("years", "Year");
		
		
	}
	
	public static void getHtmlPivotTable(ResultSet rs, AMISQueryVO qvo, AMISResultVO rvo, String style, Boolean addDisclaimers) throws Exception {
		StringBuffer html = new StringBuffer();
		int rowCount = 0;		
//		html.append(createPivotTitle(rs, style));
		html.append("<p><table class='"+style+"'>");
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
			 
		LOGGER.info("columnCount:" +columnCount );
		for (int i = 1; i <= columnCount; i++) {
			LOGGER.info(i+ "| " + rsmd.getColumnLabel(i));
		}
		 // colgroup			 
		 html.append("<colgroup>");
		 for(int i = 1; i <columnCount + 1; i++) {
			 html.append("<col class='"+ style +"_col" + i +"'>");
		 }
		 html.append("</colgroup>");
		 
		Integer skipNestedIndex = skipNestedCode(rs);
		Integer codeIdIndex = skipCodeId(rs);
		
		Integer headersStartingIndex = 4;
		
		LOGGER.info("qvo.getNestedby():" +qvo.getNestedby() );
		 // test if there is the code and nested is equal to country or item add+1
		 if ( qvo.getNestedby().equals("countries")) {
			 if ( qvo.getShowCodes()) {
				 headersStartingIndex++;
			 }
		 }
		 
		 if ( qvo.getNestedby().equals("items")) {
//			 headersStartingIndex++;
			 
			 if ( qvo.getShowCodes()) {
				 headersStartingIndex++;
				 headersStartingIndex++;
			 }
		 }
		
		LOGGER.info("skipNestedIndex:" +skipNestedIndex );
		LOGGER.info("codeIdIndex:" +codeIdIndex );
		LOGGER.info("headersStartingIndex:" +headersStartingIndex );

		 html.append(createXHeader(qvo, style, columnCount));
		 html.append(createPivotHeaders(rs, qvo, style, headersStartingIndex));
		 

		
		while (rs.next()) {
		 
		  if ( rowCount % 2 == 0)
			  html.append("<TR class='"+ style +"_row1'>");
		  else
			  html.append("<TR class='"+ style +"_row2'>");
		  
		  
		  if (rowCount == 0) {
			  html.append(createY1Header(qvo.getY1Axis(), style));
		  }
		  rowCount++;
		  
		  // removing the first 2 columns at least ( RecordNumber,	nested)
		  // TODO: check if contains also code the third one
		  for (int i = 3; i <= columnCount; i++) {
				if ( i != codeIdIndex && i != skipNestedIndex ) {
//					if ( !rs.getString(i).equalsIgnoreCase("null") )
						html.append("<TD nowrap>" + rs.getString(i) + "</TD>");
//		  else
//						html.append("<TD nowrap></TD>");
					
				 }else{
					LOGGER.info("SKIPPING: " + rsmd.getColumnLabel(i));
				}
		    }
		  html.append("</TR>");
		  }
//		 html.append("</tbody>");
		 html.append("</table></P>");
		 
		 
		 if ( addDisclaimers ) {
			 if ( rowCount > 0 ) {
				 html.append("<div  class='"+ style +"_disclaimer_flags'>* = Unofficial figure | [ ] = Official data | F = FAO estimate | Im = FAO data based on imputation methodology</div>");
				 html.append("<div  class='"+ style +"_copyright'>FAOSTAT | © FAO Statistics Division 2011 | 01 August 2011</div>"); 
			 }
			 
		 }
		 
		 LOGGER.info("ROWCOUNT:" + rowCount);
//		 LOGGER.info("TABLE:" + html.toString());
		 
		 
		 rvo.setTableHTML(html.toString());
		 rvo.setRows(Long.valueOf(rowCount));
	}
	
	/** TODO: Workaround for CodeId...ask amanda to remove it **/
	private static Integer skipNestedCode(ResultSet rs) {
		Integer index = -1;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			

			LOGGER.info("2)" +rsmd.getColumnLabel(2));
			LOGGER.info("3)" +rsmd.getColumnLabel(3));
			LOGGER.info("4)" +rsmd.getColumnLabel(4));
			if( rsmd.getColumnLabel(3).contains("code")) {
				 index = 3;

			}

		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return index; 
		
	}
	
	/** TODO: Workaround for CodeId...ask amanda to remove it **/
	private static Integer skipCodeId(ResultSet rs) {
		Integer index = -1;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			

			int columnCount = rsmd.getColumnCount();
			
			for (int i = 3; i <= columnCount; i++) {
				 if( rsmd.getColumnLabel(i).equalsIgnoreCase("CodeId")) {
					 index = i;
					 break;
				 }
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return index; 
		
	}
	
	private static String createXHeader(AMISQueryVO qvo, String style, Integer columnCount) {
		StringBuffer html = new StringBuffer();
		
		
		/** TODO: check the col span **/
		html.append("<TR>");
		html.append("<TH class='"+ style +"_empty' nowrap></TH>");
		html.append("<TH class='"+ style +"_empty' nowrap></TH>");
		html.append("<TH class='"+ style +"_empty' nowrap></TH>");

		if ( qvo.getShowCodes() ) {
			if (qvo.getY1Axis().equals("items") || qvo.getY1Axis().equals("countries")) {
				html.append("<TH class='"+ style +"_empty' nowrap></TH>");
			}
			if (qvo.getY2Axis().equals("items") || qvo.getY2Axis().equals("countries")) {
				html.append("<TH class='"+ style +"_empty' nowrap></TH>");
			}
		}
		
		html.append("<TH colspan='"+ (columnCount - 1) + "' class='"+ style +"_x_header' nowrap> "+ pivotLabels.get(qvo.getxAxis()) + "</TH>");
		html.append("</TR>");
		
		return html.toString();
	}
	
	private static String createPivotHeaders(ResultSet rs, AMISQueryVO qvo, String style, Integer startingIndex) {
		StringBuffer html = new StringBuffer();
		try {
			Integer colspan = 1;
			
			if ( qvo.getAddFlag() )
				colspan++;
			if ( qvo.getShowMeasurementUnit() )
				colspan++;
			
			LOGGER.info("colspan: " + colspan);
			
			Integer skipNestedIndex = skipNestedCode(rs);
			Integer codeIdIndex = skipCodeId(rs);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnCount = rsmd.getColumnCount();
			
			
			
			 // table header
			html.append("<TR>");
			// skip the first two
			html.append("<TD class='"+ style +"_empty'></TD>");
			html.append("<TD class='"+ style +"_empty'></TD>");
			 // removing the first 2 columns at least ( RecordNumber,	nested)
			 // TODO: check if contains also code the third one
//			for (int i = 3; i <= columnCount; i++) {
			for (int i = startingIndex; i <= columnCount; i++) {
				if ( i != codeIdIndex && i != skipNestedIndex ) {
					LOGGER.info("i != codeIdIndex && i != skipNestedIndex");
					Boolean addColspan = false;
					LOGGER.info("rsmd.getColumnLabel(i)  " + rsmd.getColumnLabel(i));
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						LOGGER.info("!rsmd.getColumnLabel(i).contains(\"*\")");
						if ( (i + 1) <= columnCount && colspan != 1 ) {
							LOGGER.info("i != codeIdIndex && i != skipNestedIndex");
							if ( rsmd.getColumnLabel(i + 1) != null ) {
								if ( rsmd.getColumnLabel(i + 1).contains("*") && ! rsmd.getColumnLabel(i +1).contains("**")) {
									LOGGER.info("rsmd.getColumnLabel(i).contains:true  " + rsmd.getColumnLabel(i +1));

									addColspan = true;
								}
							}
						}
					}
					
					LOGGER.info("addColspan: " + addColspan);
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						if ( addColspan ) {
								html.append("<TD colspan='"+colspan+"' class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i) + "</TD>");
						}
						else {
							String header = pivotLabels.get(rsmd.getColumnLabel(i));
							if ( header != null )
								html.append("<TD class='"+ style +"_header' nowrap>" + header + "</TD>");
							else {
								html.append("<TD class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i) + "</TD>");
							}
						}
					}

				}
				else{
						LOGGER.info("SKIPPING: " + rsmd.getColumnLabel(i));
					}
				}
			html.append("</TR>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return html.toString();
	}
	
	private static String createY1Header(String y1Value, String style) {
		StringBuffer html = new StringBuffer();
		
		Integer rowcount = 100;
		
		/** TODO: check the col span **/
//		html.append("<TR>");
		html.append("<TD  rowspan='"+ rowcount +"' class='"+ style +"_y1_header' nowrap> "+ pivotLabels.get(y1Value) + "</TD>");
//		  html.append("</TR>");
		
		return html.toString();
		
	}
	
	public static void getHtmlTableGrowthRate(AMISResultVO rvo, LinkedHashMap<String, Map<String, Double>> values, String style, String titleStyle) {

		StringBuffer html = new StringBuffer();

		LOGGER.info("RESULT SIZE:" + values.size());
		 
		
		
		 for(String key : values.keySet()) {
			 html.append("<div class='" + titleStyle + "'>"+ key + "</div>");
			 html.append("<p>");
			 html.append("<table class='"+style+"'>");
			 
			 // colgroup			 
			 html.append("<colgroup>");
			 for(int i = 1; i < values.get(key).size() + 1; i++) {
				 html.append("<col class='"+ style +"_col" + i +"'>");
			 }
			 html.append("</colgroup>");

			 
			  for (String key2 : values.get(key).keySet()) {
				  html.append("<tr>");
				  Double value = values.get(key).get(key2);
//				  html.append("<td>" + key + "</td>");
				  html.append("<td>" + key2 + "</td>");
				  
				  if ( value < 0 ) {
					  /** TODO: set red **/
					  html.append("<td><font color='red'> " + value + " % </font> </td>");
				  }
				  else 
					  html.append("<td><font color='green'> " + value + " % </td>");
				  
				  html.append("</tr>");
			  }
			  html.append("</table>");
			  html.append("</p>");

		 }
		
		
		
			 
		
		// just for the download... TODO: change it... merge it with the other one
		List<List<String>> contents = new ArrayList<List<String>>();
		 for(String key : values.keySet()) {
			  for (String key2 : values.get(key).keySet()) {
				  List<String> content = new ArrayList<String>();
				  Double value = values.get(key).get(key2);
				  content.add(key);
				  content.add(key2);
				  content.add(value.toString() + " %");
				  contents.add(content);
			  }
			
		 }
//		
//		 html.append("</table></p>");
			 
		 LOGGER.info("TABLE:" + html.toString());
		 
		 rvo.setTableHTML(html.toString());
		 rvo.setTableHeaders(new ArrayList<String>());
		 rvo.setTableContents(contents);
			   
	}
	
	public static void getHtmlTable(ResultSet rs, AMISResultVO rvo, Boolean showColumnHeaders, String style) throws Exception {
		StringBuffer html = new StringBuffer();
		int rowCount = 0;
		List<String> headers = new ArrayList<String>();
		List<List<String>> contents = new ArrayList<List<String>>();
		
		html.append("<p><table class='"+style+"'>");
		
		 ResultSetMetaData rsmd = rs.getMetaData();
		 int columnCount = rsmd.getColumnCount();
		 
		 
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
				 html.append("<TH class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i + 1) + "</TH>");
				 headers.add(rsmd.getColumnLabel(i + 1));
			   }
			html.append("</TR>");
		 }
		 // the data
		 
		 html.append("<tbody>");
		 while (rs.next()) {
		  rowCount++;
		  if ( rowCount % 2 == 0)
			  html.append("<TR class='"+ style +"_row1'>");
		  else
			  html.append("<TR class='"+ style +"_row2'>");
		  
		  List<String> content = new ArrayList<String>();
		  for (int i = 0; i < columnCount; i++) {
				 html.append("<TD nowrap>" + rs.getString(i + 1) + "</TD>");
				 content.add(rs.getString(i + 1)); 
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
	
	
	
	public static LinkedHashMap<String, String> sortByValuesDESC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = size -1; i>= 0; i--)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
}
