package org.fao.fenix.web.modules.dataviewer.server.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;
//import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.server.utils.lang.FAOSTATLanguageConstantsServer;

public class DataViewerUtils {
	
	private final static Logger LOGGER = Logger.getLogger(DataViewerUtils.class);


	// TODO: Change the way to get the FlagCodes (that has to be translated)
	//		 just loop the ResultSetMetaData and get the index of "Flag" (if exist)
	//	     instead derive it from the columns number.
	//		 (Do the same also for the flag table)

	public static void getHtmlPivotTable(ResultSet rs, DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String style, Map<String, String> flags) throws Exception {
		if (!qvo.getIsTradeMatrix()) {
			getDefaultPivotTable(rs, qvo, rvo, style, flags);
		}
		else {
			getDefaultPivotTableTradeMatrix(rs, qvo, rvo, style, flags);
		}
	}

	private static void getDefaultPivotTable(ResultSet rs, DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String style, Map<String, String> flags) throws Exception {
		StringBuffer html = new StringBuffer();
		int rowCount = 0;

		try {
			rs.first();
		}catch (Exception e) {
		}

		html.append("<p><table class='"+style+"'>");
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		 // colgroup			 
		 html.append("<colgroup>");
		 for(int i = 1; i <columnCount + 1; i++) {
			 html.append("<col class='"+ style +"_col" + i +"'>");
		 }
		 html.append("</colgroup>");

		Integer skipNestedIndex = skipNestedCode(rs);
		
		int startYHeaders = 4;
			
		if ( skipNestedIndex != -1 ) {
			startYHeaders = startYHeaders + 1;
		}

		html.append(createXHeader(qvo, style, columnCount));
		html.append(createPivotHeaders(rs, qvo, style, startYHeaders));
		
		Integer xcolumns = getXcolumnsNumber(qvo);
				
		  // checking flags
	    int flagId = -1;
		  if ( qvo.getAddFlag() ){
			    flagId = 2;
				if ( qvo.getShowMeasurementUnit() ) {
					flagId = flagId +1;
				}
		  }

		while (rs.next()) {
		  if ( rowCount % 2 == 0)
			  html.append("<TR class='"+ style +"_row1'>");
		  else
			  html.append("<TR class='"+ style +"_row2'>");
		  
		  if (rowCount == 0) {
			  /** TODO: remove it and pass it dynamically **/
			  html.append(createY1Header(qvo.getY1Axis(), style, 1000, qvo.getLanguage()));
		  }
		  rowCount++;

		  // setting the title
		  String title = "";
		  if ( skipNestedIndex == -1 ) {
			  title = rs.getString(2);
		  } else {
			  title = rs.getString(2) + " (" + rs.getString(3) + ")";
		  }
		  qvo.setTitle(title);
		  rvo.setTitle(title);

		  int pos = 1;
		  // removing the first 2 columns at least ( RecordNumber,	nested)
		  for (int i = 3; i <= columnCount; i++) {
				if ( i != skipNestedIndex ) {
					if ( rs.getString(i) != null ) {
						html.append("<TD nowrap>" + rs.getString(i) + "</TD>");
						if ( qvo.getAddFlag() ) {
							if ( i > xcolumns ) {
								if ( (pos % flagId) == 0) {
									flags.put(rs.getString(i), rs.getString(i));
	//								System.out.println("--> flag: " + rs.getString(i) + " | " + i +  " | " + pos +" | " + flagId);
								}
								pos++;
							}
						}
					}
					else
						html.append("<TD nowrap></TD>");
				 }
		    }
		  html.append("</TR>");
		  }
		 html.append("</table></P>");
 
		 rvo.setTableHTML(html.toString());
		 rvo.setRows(Long.valueOf(rowCount));
	}
	
	private static void getDefaultPivotTableTradeMatrix(ResultSet rs, DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String style, Map<String, String> flags) throws Exception {
		StringBuffer html = new StringBuffer();
		int rowCount = 0;
		// title
		qvo.setTitle("");
		rvo.setTitle("");

		try {
			rs.first();
		} catch (Exception e) {
		}

		html.append("<p><table class='" + style + "'>");

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		html.append(createHeadersTradeMatrix(rsmd, columnCount, qvo, style, "1"));

		// checking flags
		int flagId = -1;
		if (qvo.getAddFlag()) {
			flagId = 2;
			if (qvo.getShowMeasurementUnit()) {
				flagId = flagId + 1;
			}
		}

		Integer headerCount = 5;
		if (qvo.getShowCodes())
			headerCount = 8;

		while (rs.next()) {
			if (rowCount % 2 == 0)
				html.append("<TR class='" + style + "_row1'>");
			else
				html.append("<TR class='" + style + "_row2'>");

			rowCount++;

			// removing the first 2 columns at least ( RecordNumber, nested)
			int pos = 1;
			for (int i = 2; i <= columnCount; i++) {
				if (rs.getString(i) != null) {
					html.append("<TD nowrap>" + rs.getString(i) + "</TD>");
					if (qvo.getAddFlag()) {
						if (i > headerCount) {
							if ((pos % flagId) == 0) {
								flags.put(rs.getString(i), rs.getString(i));
							}
							pos++;
						}
					}
				} else
					html.append("<TD nowrap></TD>");
			}
			html.append("</TR>");
		}

		html.append("</table></P>");

		rvo.setTableHTML(html.toString());
		rvo.setRows(Long.valueOf(rowCount));
	}
	
	private static String createHeadersTradeMatrix(ResultSetMetaData rsmd, int columnCount, DWFAOSTATQueryVO qvo, String style, String type) {
		StringBuffer html = new StringBuffer();
		html.append("<TR>");
		
		Integer colspan = 1;
		if ( qvo.getAddFlag() )
			colspan++;
		if ( qvo.getShowMeasurementUnit() )
			colspan++;
		
		Integer headerCount = 4;
		if ( qvo.getShowCodes()) 
			headerCount = 7;

		// top header
		html.append("<TR>");
		for (int i = 0; i < headerCount; i++) { 
			html.append("<TD class='"+ style +"_empty'></TD>");
		}
		int topspan = columnCount - headerCount;
		html.append("<TD colspan='"+topspan+"' class='"+ style +"_y1_header' nowrap>"+ FAOSTATLanguageConstantsServer.pivotLabels.get("partner" + qvo.getLanguage().toUpperCase()) +"</TD>");
		html.append("</TR>");
		 
		// Table header
		for (int i = 2; i < headerCount; i++) {
				try {
					String header = FAOSTATLanguageConstantsServer.pivotLabels.get(rsmd.getColumnLabel(i).toLowerCase() + qvo.getLanguage().toUpperCase());
					html.append("<TD class='"+ style +"_header' nowrap>" + header +"</TD>");
				} catch (SQLException e) {
					html.append("<TD class='"+ style +"_header' nowrap>");
				}
		}
		
		// Columns
		try {
			for (int i = headerCount; i <= columnCount; i++) {
					Boolean addColspan = false;
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						if ( (i + 1) <= columnCount && colspan != 1 ) {
							if ( rsmd.getColumnLabel(i + 1) != null ) {
								if ( rsmd.getColumnLabel(i + 1).contains("*") && ! rsmd.getColumnLabel(i +1).contains("**")) {
									addColspan = true;
								}
							}
						}
					}
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						if ( addColspan ) {
								html.append("<TD colspan='"+colspan+"' class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i).toLowerCase() + "</TD>");
						}
						else {
							String header = FAOSTATLanguageConstantsServer.pivotLabels.get(rsmd.getColumnLabel(i).toLowerCase() + qvo.getLanguage().toUpperCase());
							if ( header != null )
								html.append("<TD class='"+ style +"_header' nowrap>" + header + "</TD>");
							else {
								html.append("<TD class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i) + "</TD>");
							}
						}
					}
			}
		}catch (Exception e) {
		}
		
		
		html.append("</TR>");
		return html.toString();
	} 

	

	
	private static String createXHeader(DWFAOSTATQueryVO qvo, String style, Integer columnCount) {
		StringBuffer html = new StringBuffer();
		html.append("<TR>");
		html.append("<TH class='"+ style +"_empty' nowrap></TH>");
		html.append("<TH class='"+ style +"_empty' nowrap></TH>");
		html.append("<TH class='"+ style +"_empty' nowrap></TH>");

		if ( qvo.getShowCodes() ) {
			if (qvo.getY1Axis().equals("item") || qvo.getY1Axis().equals("reporter") || qvo.getY1Axis().equals("partner") || qvo.getY1Axis().equals("area")|| qvo.getY1Axis().equals("element")) {
				html.append("<TH class='"+ style +"_empty' nowrap></TH>");
			}
			if (qvo.getY2Axis().equals("item") || qvo.getY2Axis().equals("reporter") || qvo.getY2Axis().equals("partner") || qvo.getY2Axis().equals("area") || qvo.getY1Axis().equals("element")) {
				html.append("<TH class='"+ style +"_empty' nowrap></TH>");
			}
		}
		html.append("<TH colspan='"+ (columnCount - 1) + "' class='"+ style +"_x_header' nowrap> "+ FAOSTATLanguageConstantsServer.pivotLabels.get(qvo.getxAxis() + qvo.getLanguage().toUpperCase()) + "</TH>");
		html.append("</TR>");
		return html.toString();
	}
	
	private static int getXcolumnsNumber(DWFAOSTATQueryVO qvo) {
		int xcolumns = 1;
		int add = 1;
		if ( qvo.getShowCodes() ) {
			add = add +1;
		}
		
		if (qvo.getY1Axis().equals("item") || qvo.getY1Axis().equals("area")|| qvo.getY1Axis().equals("element")) {
			xcolumns = xcolumns + add;
		}
		else {
			// years
			xcolumns = xcolumns + 1;
		}
		
		if (qvo.getY2Axis().equals("item") || qvo.getY2Axis().equals("area") || qvo.getY2Axis().equals("element")) {
			xcolumns = xcolumns + add;
		}
		else {
			// years
			xcolumns = xcolumns + 1;
		}
		
		if (qvo.getNestedby().equals("item") || qvo.getNestedby().equals("area") || qvo.getNestedby().equals("element")) {
			xcolumns = xcolumns + add;
		}
		else {
			// years
			xcolumns = xcolumns + 1;
		}
	
		return xcolumns;
	}
	
	private static int getXcolumnsNumberTradeMatrix(DWFAOSTATQueryVO qvo) {
		int xcolumns = 1;
		int add = 1;
		if ( qvo.getShowCodes() ) {
			add = add +1;
		}
		
//		sql.append(",@pivotdimension1 = 'Reporter'");                      
//		sql.append(",@pivotdimension2 = 'Partner'"); 
		
		if (qvo.getY1Axis().toLowerCase().equals("item") || qvo.getY1Axis().toLowerCase().equals("partner") || qvo.getY1Axis().toLowerCase().equals("reporter") || qvo.getY1Axis().equals("element")) {
			xcolumns = xcolumns + add;
		}
		else {
			// years
			xcolumns = xcolumns + 1;
		}
		
		if (qvo.getY2Axis().toLowerCase().equals("item") || qvo.getY2Axis().toLowerCase().equals("partner") ||  qvo.getY2Axis().toLowerCase().equals("reporter") || qvo.getY2Axis().equals("element")) {
			xcolumns = xcolumns + add;
		}
		else {
			// years
			xcolumns = xcolumns + 1;
		}
		
		if (qvo.getNestedby().toLowerCase().equals("item") || qvo.getNestedby().toLowerCase().equals("partner") || qvo.getNestedby().toLowerCase().equals("reporter") || qvo.getNestedby().equals("element")) {
			xcolumns = xcolumns + add;
		}
		else {
			// years
			xcolumns = xcolumns + 1;
		}
	
		return xcolumns;
	}
	
	private static String createPivotHeaders(ResultSet rs, DWFAOSTATQueryVO qvo, String style, Integer startingIndex) {
		StringBuffer html = new StringBuffer();
		try {
			Integer colspan = 1;
			
			if ( qvo.getAddFlag() )
				colspan++;
			if ( qvo.getShowMeasurementUnit() )
				colspan++;

			Integer skipNestedIndex = skipNestedCode(rs);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			 // table header
			html.append("<TR>");
			// skip the first two
			html.append("<TD class='"+ style +"_empty'></TD>");
			html.append("<TD class='"+ style +"_empty'></TD>");
			for (int i = startingIndex; i <= columnCount; i++) {
				if ( i != skipNestedIndex ) {
					Boolean addColspan = false;
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						if ( (i + 1) <= columnCount && colspan != 1 ) {
							if ( rsmd.getColumnLabel(i + 1) != null ) {
								if ( rsmd.getColumnLabel(i + 1).contains("*") && ! rsmd.getColumnLabel(i +1).contains("**")) {
									addColspan = true;
								}
							}
						}
					}
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						if ( addColspan ) {
								html.append("<TD colspan='"+colspan+"' class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i).toLowerCase() + "</TD>");
						}
						else {
							String header = FAOSTATLanguageConstantsServer.pivotLabels.get(rsmd.getColumnLabel(i).toLowerCase() + qvo.getLanguage().toUpperCase());
							if ( header != null )
								html.append("<TD class='"+ style +"_header' nowrap>" + header + "</TD>");
							else {
								html.append("<TD class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i) + "</TD>");
							}
						}
					}

				}
			}

			html.append("</TR>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return html.toString();
	}
	
	private static String createPivotHeadersTradeMatrix(ResultSet rs, DWFAOSTATQueryVO qvo, String style, Integer startingIndex) {
		StringBuffer html = new StringBuffer();
		try {
			Integer colspan = 1;
			
			if ( qvo.getAddFlag() )
				colspan++;
			if ( qvo.getShowMeasurementUnit() )
				colspan++;

			Integer skipNestedIndex = skipNestedCode(rs);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			 // table header
			html.append("<TR>");
			// skip the first two
			html.append("<TD class='"+ style +"_empty'></TD>");
			html.append("<TD class='"+ style +"_empty'></TD>");
			for (int i = startingIndex; i <= columnCount; i++) {
				if ( i != skipNestedIndex ) {
					Boolean addColspan = false;
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						if ( (i + 1) <= columnCount && colspan != 1 ) {
							if ( rsmd.getColumnLabel(i + 1) != null ) {
								if ( rsmd.getColumnLabel(i + 1).contains("*") && ! rsmd.getColumnLabel(i +1).contains("**")) {
									addColspan = true;
								}
							}
						}
					}
					if ( !rsmd.getColumnLabel(i).contains("*")) {
						if ( addColspan ) {
								html.append("<TD colspan='"+colspan+"' class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i).toLowerCase() + "</TD>");
						}
						else {
							String header = FAOSTATLanguageConstantsServer.pivotLabels.get(rsmd.getColumnLabel(i).toLowerCase() + qvo.getLanguage().toUpperCase());
							if ( header != null )
								html.append("<TD class='"+ style +"_header' nowrap>" + header + "</TD>");
							else {
								html.append("<TD class='"+ style +"_header' nowrap>" + rsmd.getColumnLabel(i) + "</TD>");
							}
						}
					}

				}
			}

			html.append("</TR>");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return html.toString();
	}

	
	private static String createY1Header(String y1Value, String style, Integer rowcount, String language) {
		StringBuffer html = new StringBuffer();
		html.append("<TD  rowspan='"+ rowcount +"' class='"+ style +"_y1_header' nowrap> "+ FAOSTATLanguageConstantsServer.pivotLabels.get(y1Value + language.toUpperCase()) + "</TD>");
		return html.toString();
		
	}

	/** TODO: Workaround for CodeId...ask amanda to remove it **/
	private static Integer skipNestedCode(ResultSet rs) {
		Integer index = -1;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			if( rsmd.getColumnLabel(3).toLowerCase().contains("code")) {
				 index = 3;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return index; 
	}
	
	private static Integer skipNestedCodeTradeMatrix(ResultSet rs) {
		Integer index = -1;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			if( rsmd.getColumnLabel(3).toLowerCase().contains("code")) {
				 index = 3;
			}
			
			if( rsmd.getColumnLabel(5).toLowerCase().contains("code")) {
				 index = 5;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return index; 
	}

	public static void getHtmlTable(ResultSet rs, DWFAOSTATResultVO rvo, Boolean showColumnHeaders, String style) throws Exception {
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
		 
//		 LOGGER.info("ROWCOUNT:" + rowCount);
//		 LOGGER.info("TABLE:" + html.toString());
		 
		 
		 rvo.setTableHTML(html.toString());
		 rvo.setRows(Long.valueOf(rowCount));
		 
		 rvo.setTableHeaders(headers);
		 rvo.setTableContents(contents);
		 
	}
	
	public static void getExportTable(ResultSet rs, DWFAOSTATResultVO rvo, Boolean showColumnHeaders) throws Exception {
		int rowCount = 0;
		List<String> headers = new ArrayList<String>();
		List<List<String>> contents = new ArrayList<List<String>>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		// table header
		if (showColumnHeaders) {
			for (int i = 0; i < columnCount; i++) {
				headers.add(rsmd.getColumnLabel(i + 1));
			}
		}
		while (rs.next()) {
			rowCount++;
			List<String> content = new ArrayList<String>();
			for (int i = 0; i < columnCount; i++) {
				content.add(rs.getString(i + 1));
			}
			contents.add(content);
		}
		rvo.setRows(Long.valueOf(rowCount));
		rvo.setTableHeaders(headers);
		rvo.setTableContents(contents);
	}
	
	public static void getHtmlTable(ResultSet rs, DWFAOSTATResultVO rvo, List<String> columns, String style) throws Exception {
		StringBuffer html = new StringBuffer();
		int colCount = 0;
		int counter= 1;
		
		int rowCount = 0;
		List<List<String>> contents = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		html.append("<p><table class='"+style+"'>");
		 
		// LOGGER.info("RESULT SIZE:" + rs.size());
//		 LOGGER.info("COLUMNS :" + columns.size());

		 html.append("<colgroup>");
		 for(String columnName: columns) { 
			 html.append("<col class='"+style+"_col"+counter+"'/>");  
			 headers.add(columnName);
			 counter++;
		 }	 
		 html.append("</colgroup>");
		 
		 html.append("<tr>");
		 for(String columnName: columns) { 
			 if(colCount==0)
				 html.append("<th width='80px'>");  
			 else
				 html.append("<th>");  
				
			 html.append(columnName+ "</th>"); 
			 colCount++;
		 }	 
		 html.append("</tr>");
		 
		 while (rs.next()) {
			  rowCount++;
			  if ( rowCount % 2 == 0)
				  html.append("<TR class='"+ style +"_row1'>");
			  else
				  html.append("<TR class='"+ style +"_row2'>");
			  
			  List<String> content = new ArrayList<String>();
			  for (int i = 0; i < colCount; i++) {
					 html.append("<TD>" + rs.getString(i + 1) + "</TD>"); //wrap text
					 content.add(rs.getString(i + 1)); 
			    }
			  contents.add(content);
			  html.append("</TR>");
			  }
		 
			 html.append("</table></p>");
			 
//			 LOGGER.info("TABLE:" + html.toString());
			 
			 rvo.setTableHTML(html.toString());
			 rvo.setRows(Long.valueOf(rowCount));
			 rvo.setTableContents(contents);
			 rvo.setTableHeaders(headers);
			 
	     // return html.toString();
	}
	
	public static void getHtmlTableMethodology(ResultSet methodologyResultSet, ResultSet glossaryResultSet, DWFAOSTATResultVO rvo, List<String> columns, String style) throws Exception {
		StringBuffer html = new StringBuffer();
		int colCount = 0;
		int counter= 1;
		
		int rowCount = 0;
		List<List<String>> contents = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		html.append("<p><table class='"+style+"'>");
		 
		// LOGGER.info("RESULT SIZE:" + rs.size());
//		 LOGGER.info("COLUMNS :" + columns.size());
		
		 StringBuffer glossaryHtml = new StringBuffer();
		 
		 while (glossaryResultSet.next()) {
			 glossaryHtml.append(glossaryResultSet.getString(1) + "<br/>");
		  }
		 
		html.append("<colgroup>");
		 for(String columnName: columns) { 
			 html.append("<col class='"+style+"_col"+counter+"'/>");  
			 headers.add(columnName);
			 counter++;
		 }	 
		 html.append("</colgroup>");	 
		
		 while (methodologyResultSet.next()) {
		 for (int i = 0; i < columns.size()-1; i++) {
			 List<String> content = new ArrayList<String>();
			 if(i == 0){
				 html.append("<TR>");
				 html.append("<TD colspan='2'style='text-align:justify'>" + methodologyResultSet.getNString(i + 1) + "</TD>"); //wrap text
				 content.add(methodologyResultSet.getString(i + 1)); 	
				 html.append("</TR>");
					 
			 } else {
				 html.append("<TR valign='top'>");				 
				 html.append("<TD class='"+style+"_row1'>"+ columns.get(i)+ "</TD>"); 	
				 content.add(columns.get(i)); 	
				 html.append("<TD style='text-align:justify'>" + methodologyResultSet.getString(i + 1) + "</TD>"); //wrap text
				 content.add(methodologyResultSet.getNString(i + 1)); 	
				 html.append("</TR>");
				
			 }
			 
			// headers.add(columns.get(i));
			
			 contents.add(content);
			 rowCount ++;
		  }	 
		 }
		 
		 html.append("<TR valign='top'>");
		 html.append("<TD class='"+style+"_row1'>"+ columns.get(columns.size()-1)+ "</TD>"); 	
		 html.append("<TD>" + glossaryHtml+ "</TD>"); //wrap text
		// content.add(methodologyResultSet.getString(i + 1)); 	
		 html.append("</TR>");
		 
		 
		 html.append("</table></p>");
			 
			// LOGGER.info("TABLE:" + html.toString());
			 
			 rvo.setTableHTML(html.toString());
			 rvo.setRows(Long.valueOf(rowCount));
			 rvo.setTableContents(contents);
			 //rvo.setTableHeaders(headers);
			 
	     // return html.toString();
	}
	
	
	
	public static String getHtmlTable(List<Object[]> rs, List<String> columns, String style) {
		StringBuffer html = new StringBuffer();
		int colCount = 0;
		int counter= 1;
		html.append("<p><table class='"+style+"'>");
		 
//		 LOGGER.info("RESULT SIZE:" + rs.size());
//		 LOGGER.info("COLUMNS :" + columns.size());

		 html.append("<colgroup>");
		 for(String columnName: columns) { 
			 html.append("<col class='"+style+"_col"+counter+"'/>");  
			 counter++;
		 }	 
		 html.append("</colgroup>");
		 
		 html.append("<tr>");
		 for(String columnName: columns) { 
			 if(colCount==0)
				 html.append("<th width='80px'>");  
			 else
				 html.append("<th>");  
				
			 html.append(columnName+ "</th>"); 
			 colCount++;
		 }	 
		 html.append("</tr>");
		 
		 for(int i=0; i < rs.size(); i++) {
	
			  html.append("<tr>");
			  for (int j = 0; j < columns.size(); j++) {
				 String value;
				 
				// if(j==0)
				//	 html.append("<td>");  
				 //else
				//	 html.append("<td>");  
				 
				  if(rs.get(i)[j]!=null)
					  value =  rs.get(i)[j].toString();
				  else 
					  value = "";
				  
				  html.append("<td>"+value+ "</td>");
			    }
			  html.append("</tr>");
			  }
			 html.append("</table></p>");
			 
//			 LOGGER.info("TABLE:" + html.toString());
			 
	      return html.toString();
	}
	
	
	public static void getHtmlTableGrowthRate(DWFAOSTATResultVO rvo, LinkedHashMap<String, Map<String, Double>> values, String style, String titleStyle) {
		StringBuffer html = new StringBuffer();
		Boolean addTitle = false;
		if ( values.size() > 1 )
			addTitle = true;
		for (String key : values.keySet()) {
			if ( addTitle ) {
				html.append("<div class='" + titleStyle + "'>" + key + "</div>");
			}
			else
				html.append("<div class='" + titleStyle + "'></div>");
			
			html.append("<p>");
			html.append("<table class='" + style + "'>");

			// colgroup
			html.append("<colgroup>");
			for (int i = 1; i < values.get(key).size() + 1; i++) {
				html.append("<col class='" + style + "_col" + i + "'>");
			}
			html.append("</colgroup>");

			for (String key2 : values.get(key).keySet()) {
				html.append("<tr>");
				Double value = values.get(key).get(key2);
				html.append("<td>" + key2 + "</td>");

				if (value < 0) {
					/** TODO: set red **/
					html.append("<td><font color='red'> " + value + "%</font></td>");
				} else
					html.append("<td><font color='green'> " + value + "%</td>");

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
			 
//		 LOGGER.info("TABLE:" + html.toString());
		 
		 rvo.setTableHTML(html.toString());
		 rvo.setTableHeaders(new ArrayList<String>());
		 rvo.setTableContents(contents);
			 
	   
	}
	
	public static String addDislaimerFlags(List<DWCodesModelData> codes, String style) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<div class='"+ style +"_disclaimer_flags'>");
		int i=0;
		for(DWCodesModelData code : codes ) {
			// workaround for the official data
			if ( code.getCode().equals("  ")) 
				sb.append("[" + code.getCode()+"]");
			else
				sb.append(code.getCode());
			sb.append(" = ");
			sb.append(code.getLabel());
			if ( i < codes.size() -1 ) 
				sb.append(" | ");
			i++;
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static String addCopyright(String style) {
		StringBuffer sb = new StringBuffer();
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat yearFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String day = dateFormat.format(date).toString();
		String year = yearFormat.format(date).toString();
		
		// copyright
		sb.append("<div  class='"+ style +"_copyright'>FAOSTAT | © FAO Statistics Division "+ year +" | " + day + "</div>"); 			


		return sb.toString();
	}
	
	
	
	public static LinkedHashMap<String, String> sortByValuesASC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
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