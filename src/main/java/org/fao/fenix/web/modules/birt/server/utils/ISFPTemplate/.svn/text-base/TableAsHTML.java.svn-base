package org.fao.fenix.web.modules.birt.server.utils.ISFPTemplate;

import java.util.List;

import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;

/**
 * 
 * @author salvatore
 * Due to a bug of birt when you export as PDF the table the border disappears (FUCKING BIRT)
 *
 */
@Deprecated 
public class TableAsHTML {
	
	private static String getStyleHeader(){
		String style="style='font-weight:bold; font-size:8px; font-family:arial;'";
		return style;
	}
	
	private static String getStyleBody(){
		String style="style='font-size:8px; font-family:arial;'";
		return style;
	}
	
	private static String getHeader(List<String> header, String firstRow, String otherRow){
		String row="<tr " + getStyleHeader() + " >";
		String value;
		for (int i=0; i<header.size(); i++){
			if (header.get(i).equals("")) value="&nbsp;";
			else value = header.get(i);
			if (i==0){
				row+="<td width='" + firstRow + "'>" + value + "</td>";
			} else {
				row+="<td width='" + otherRow + "'>" + value + "</td>";
			}
			
		}
		row+="</tr>";
		
		return row;
	}
	
	private static String getBody(List<List<String>> data){
		String body="";
		
		for (int i=1; i<data.size(); i++){
			body+="<tr " + getStyleBody() + " >";
			for (String element : data.get(i)){
				body+="<td>" + element + "</td>"; 
			}
			body+="</tr>";
		}
		
		return body;
	}
	
	
	
	public static TextItemHandle getTable(List<List<String>> data, ReportDesignHandle design, String firstRow, String otherRow){
		TextItemHandle table=null;
		String tableAsHTML="<table width='99%' border='2' cellpadding='3' cellspacing='0'>";
		try {
			table = design.getElementFactory().newTextItem("table");
			table.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			table.setStyleName("styleForTable");
			
			tableAsHTML+=getHeader(data.get(0), firstRow, otherRow);
			tableAsHTML+=getBody(data);
			
			tableAsHTML+="</table>";
			
			table.setContent(tableAsHTML);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return table;
		
		
	}

}
