package org.fao.fenix.web.modules.adam.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.core.client.utils.FormatValues;

public class ADAMTableUtils {
	
	private final static Logger LOGGER = Logger.getLogger(ADAMTableUtils.class);
	
	public static List<String> projectsHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Project Title");
		tableHeaders.add("Year");
		tableHeaders.add("Resource Partner");
		tableHeaders.add("Resource Partner Agency Name");
		tableHeaders.add("Recipient Country");
		tableHeaders.add("Sector");
		tableHeaders.add("Sub-Sector");
		tableHeaders.add("Channel of Delivery");
		tableHeaders.add("Reported Channel Name");
		tableHeaders.add("Disbursement (USD Mil)");
		tableHeaders.add("Commitment (USD Mil)");
		return tableHeaders;
	}
	
	public static List<List<String>> projectsContent(List<Object[]> result) {
		List<List<String>> table =  new ArrayList<List<String>>();
		for(int i=0; i < result.size(); i++) {
			List<String> row = new ArrayList<String>();
			for (Object o : result.get(i)) {
				try {
					row.add(o.toString());
				} 
				catch (Exception e) {
					row.add("");
				}
			}
			table.add(row);
		}
		System.out.println("table: " + table);
		return table;
	}
	
	public static String createHtmlTable(List<String> headers, List<List<String>> tableContents, List<Object[]> result) {
		StringBuilder table = new StringBuilder();
		
		table.append("<div class='small-content'> ");
		table.append("<TABLE STYLE='border-collapse:collapse;' BGCOLOR='#FFFFFF' WIDTH='100%' CELLSPACING='0'>");
	    
		
		createHtmlHeaders(table, headers);
		
		for(int i=0; i < result.size(); i++) {
			table.append("<TR BGCOLOR='#FFFFFF'>");
			List<String> row = new ArrayList<String>();
			Object[] objects = result.get(i);
			
			for(int j=0; j < objects.length; j ++){
				Object o = objects[j];

				if(j == 0 || j == objects.length-3 ) //project title and channelname, wrap text
					table.append("<TD style='padding:4px; border:1px solid #545454;'>");	
				else
					table.append("<TD nowrap style='padding:4px; border:1px solid #545454;'>");

				try {
					table.append(o.toString());
					row.add(o.toString());
				} 
				catch (Exception e) {
					table.append("");
					row.add("");
				}
				table.append("</TD>");
			}		
		
			/**for (Object o : result.get(i)) {
				
				if(i == 5) //project title, wrap text
					table.append("<TD style='border:1px solid #545454;'>");
				else
				    table.append("<TD nowrap style='border:1px solid #545454;'>");
				    	
				try {
					table.append(o.toString());
					row.add(o.toString());
				} 
				catch (Exception e) {
					table.append("");
					row.add("");
				}
				table.append("</TD>");
			
			}**/
			table.append("</TR>");
			tableContents.add(row);
		}
		
		table.append("</TABLE>");
		table.append("</div>");
		
		LOGGER.info("table completed");
		//System.out.println("--------------- PROJECTS HTML table: ");
		//System.out.println(table.toString());
		return table.toString();
	}
	

	public static String createFixedHeaderColumnHtmlTable(List<String> headers, List<List<String>> tableContents, List<Object[]> result) {
		StringBuilder table = new StringBuilder();
		
	  	table.append("<div class='projectlist_container'> ");
		table.append("<div class='projects_grid height570'> ");
		//table.append("<div class='small-content'> ");
		table.append("<TABLE class='fancyTable' id='project_table' cellpadding='0' cellspacing='0'>");
		//table.append("<TABLE STYLE='border-collapse:collapse;' BGCOLOR='#FFFFFF' WIDTH='100%' CELLSPACING='0'>");
	    
		createFixedHtmlHeaders(table, headers);
		//createHtmlHeaders(table, headers);
		
		table.append("<TBODY>");
		for(int i=0; i < result.size(); i++) {
			table.append("<TR>");
			List<String> row = new ArrayList<String>();
			Object[] objects = result.get(i);
			
			for(int j=0; j < objects.length; j ++){
				Object o = objects[j];

				//if(j == 0 || j == objects.length-3 ) //project title and channelname, wrap text
				//	table.append("<TD style='padding:4px; border:1px solid #545454;'>");	
				//else
				table.append("<TD>");

				try {
					table.append(o.toString());
					row.add(o.toString());
				} 
				catch (Exception e) {
					table.append("Not specified"); // "" = empty cell causes mis-alignment when scrolling the table
					row.add("Not specified"); // "" = empty cell causes mis-alignment when scrolling the table
				}
				table.append("</TD>");
			}		
		
			/**for (Object o : result.get(i)) {
				
				if(i == 5) //project title, wrap text
					table.append("<TD style='border:1px solid #545454;'>");
				else
				    table.append("<TD nowrap style='border:1px solid #545454;'>");
				    	
				try {
					table.append(o.toString());
					row.add(o.toString());
				} 
				catch (Exception e) {
					table.append("");
					row.add("");
				}
				table.append("</TD>");
			
			}**/
			table.append("</TR>");
			tableContents.add(row);
		}
		table.append("</TBODY>");
		table.append("</TABLE>");
		table.append("</div>");
		table.append("<div class='clear'></div>");	
		table.append("</div>");
		
		LOGGER.info("table completed");
		//System.out.println("--------------- PROJECTS HTML table: ");
		System.out.println(table.toString());
		return table.toString();
	}
	
	
	private static String createHtmlHeaders(StringBuilder table, List<String> headers) {
		table.append("<TR BGCOLOR='#1B65A4' ALIGN='CENTER'>");
		
		for(String header : headers) {
			table.append("<TD nowrap style='padding:4px; border:1px solid #FFFFFF;'>");
			table.append("<FONT COLOR='#FFFFFF'><B>" + header + "</B></FONT>");
			table.append("</TD>");
		}

		table.append("</TR>");
		return table.toString();
	}
	
	private static String createFixedHtmlHeaders(StringBuilder table, List<String> headers) {
	    table.append("<THEAD>");
		table.append("<TR>");
		
		for(String header : headers)
			table.append("<TH>"+header+"</TH>");
		
		table.append("</TR>");
		table.append("</THEAD>");
		
		return table.toString();
	}
	

	public static List<String> globalDonorTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Resource Partner");
		tableHeaders.add("Resource Partner Budget");
		tableHeaders.add("Sector");
		tableHeaders.add("Sector Budget");
		tableHeaders.add("Channel");
		tableHeaders.add("Channel Budget");
		tableHeaders.add("Recipient");
		tableHeaders.add("Recipient Budget");
		return tableHeaders;
	}
	
	public static List<List<String>> globalDonorTable(List<List<String>> result, int maxNumberAfterComma, Boolean isGrouped) {
		List<List<String>> tableContents = new ArrayList<List<String>>();
		HashMap<String, String> value = new HashMap<String, String>();
		int counter=1;
		Boolean addValue = false;
		for(List<String> resultRow : result) {
			List<String> row = new ArrayList<String>();
			
			// donor 
			// sector
			try {
				if ( isGrouped ) {
					if ( !value.containsKey(resultRow.get(1))) {
						value.put(resultRow.get(1), getNumber(counter));
						counter++;
						addValue = true;
					}
					else {
						addValue = false;
					}
												
	
					row.add("#" + value.get(resultRow.get(1)) + " - " + resultRow.get(1));
				}
				else {
					addValue = false;
					row.add(resultRow.get(1));
				}
					
			}
			catch (Exception e) {
				row.add("");
			}
			

			
			
			// donor budget
			if ( addValue )
				row.add(FormatValues.formatValue(resultRow.get(2), maxNumberAfterComma));
			else
				row.add("");
			
			// sector
			try {
				row.add(resultRow.get(4));
			}
			catch (Exception e) {
				row.add("");
			}
			
			// sector budget
			try {
				row.add(FormatValues.formatValue(resultRow.get(6), maxNumberAfterComma));
			}
			catch (Exception e) {
				row.add("");
			}
			
			// channel
			try {
				row.add(resultRow.get(9));
			}
			catch (Exception e) {
				row.add("");
			}
			
			// channel budget
			try {
				row.add(FormatValues.formatValue(resultRow.get(10), maxNumberAfterComma));
			}
			catch (Exception e) {
				row.add("");
			}
			
			// recipient
			try {
				row.add(resultRow.get(13));
			}
			catch (Exception e) {
				row.add("");
			}
			
			// recipient budget
			try {
				row.add(FormatValues.formatValue(resultRow.get(14), maxNumberAfterComma));
			}
			catch (Exception e) {
				row.add("");
			}
			
			tableContents.add(row);
		}
		return tableContents;
	}
	
	private static String getNumber(int n) {
		if ( n < 10 )
			return "00" + n;
		else if ( n < 100 )
			return "0" + n;
		else 
			return String.valueOf(n);
	}
	
	
	public static List<String> countryDonorTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Resource Partner");
		tableHeaders.add("Resource Partner Budget");
		tableHeaders.add("Sector");
		tableHeaders.add("Sector Budget");
		tableHeaders.add("Channel");
		tableHeaders.add("Channel Budget");
		return tableHeaders;
	}
	
	public static List<String> topDonorsTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Resource Partner");
		tableHeaders.add("Resource Partner Budget");
		tableHeaders.add("Sector");
		tableHeaders.add("Sector Budget");
		tableHeaders.add("Channel");
		tableHeaders.add("Channel Budget");
		tableHeaders.add("Recipient");
		tableHeaders.add("Recipient Budget");
		
		return tableHeaders;
	}
	
	public static List<String> agriculturalDonorHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Agricultural Sector");
		tableHeaders.add("Agricultural Sector Budget");
		tableHeaders.add("Recipient Country");
		tableHeaders.add("Recipient Country Budget");	
		
		return tableHeaders;
	}
	
	
	public static List<String> topCountriesSectorsByDonorTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Recipient");
		tableHeaders.add("Recipient Budget");
		tableHeaders.add("Sector");
		tableHeaders.add("Sector Budget");
		return tableHeaders;
	}
	
	public static List<String> topSectorsDonorsChannelsTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Agricultural Sector");
		tableHeaders.add("Agricultural Sector Budget");
		tableHeaders.add("Resource Partner");
		tableHeaders.add("Resource Partner Budget");
		tableHeaders.add("Channel");
		tableHeaders.add("Channel Budget");	
		return tableHeaders;
	}
	
	public static List<String> topAgricSectorsChannelsTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Agricultural Sector");
		tableHeaders.add("Agricultural Sector Budget");
		tableHeaders.add("Channel");
		tableHeaders.add("Channel Budget");	
		return tableHeaders;
	}
	
	public static List<String> topSectorsDonorsTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Resource Partner");
		tableHeaders.add("Resource Partner Budget");
		tableHeaders.add("Sector");
		tableHeaders.add("Sector Budget");
		return tableHeaders;
	}
	
	public static List<String> topSectorsChannelsTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Sector");
		tableHeaders.add("Sector Budget");
		tableHeaders.add("Channel");
		tableHeaders.add("Channel Budget");	
		return tableHeaders;
	}
	
	
	public static List<String> topSectorsRecipientsTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Sector");
		tableHeaders.add("Sector Budget");
		tableHeaders.add("Recipient");
		tableHeaders.add("Recipient Budget");
		
		return tableHeaders;
	}
	
	public static List<String> donorProfileTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Overall Priority Themes");
		tableHeaders.add("Coded Priorities");
		tableHeaders.add("Strategic Objectives (of current FAO projects)");
		tableHeaders.add("Overall Priority Geographical Areas");
		tableHeaders.add("Recipient Countries in REU since 2005 (FAO projects)");
		tableHeaders.add("Type of Funding Arrangements Favoured");
		tableHeaders.add("Delivery by sources of external funding in USD million");
		tableHeaders.add("From date delivery");
		tableHeaders.add("To date delivery");

		return tableHeaders;
	}
	
	public static List<String> donorProcessesTableHeaders() {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Funding Bodies within Resource Partner");
		tableHeaders.add("Channel of cooperation / decision making");
		tableHeaders.add("Process of application and negotiation");
		tableHeaders.add("Timeline of funding cycle");
		tableHeaders.add("Issues to be observed");
		tableHeaders.add("External links");
		tableHeaders.add("Responsible Resource Mobilization Officer");
		
		return tableHeaders;
	}
	
	public static List<List<String>> topSectorsRecipientsTableContents(List<List<String>> result, int maxNumberAfterComma) {
		List<List<String>> tableContents = new ArrayList<List<String>>();
		HashMap<String, String> value = new HashMap<String, String>();
		int counter=1;
		Boolean addValue = false;
		for(List<String> resultRow : result) {
			List<String> row = new ArrayList<String>();
			
			// sector
			try {
				if ( !value.containsKey(resultRow.get(1))) {
					value.put(resultRow.get(1), getNumber(counter));
					counter++;
					addValue = true;
				}
				else {
					addValue = false;
				}

				row.add("#" + value.get(resultRow.get(1)) + " - " + resultRow.get(1));
			}
			catch (Exception e) {
				row.add("");
			}
			
			// sector budget
			try {
				if ( addValue)
					row.add(FormatValues.formatValue(resultRow.get(2), maxNumberAfterComma));
				else
					row.add("");
			}
			catch (Exception e) {
				row.add("");
			}
			
			// recipient
			try {
				row.add(resultRow.get(5));
			}
			catch (Exception e) {
				row.add("");
			}
			
			// recipient budget
			try {
				row.add(FormatValues.formatValue(resultRow.get(6), maxNumberAfterComma));
			}
			catch (Exception e) {
				row.add("");
			}
			
			
			tableContents.add(row);
		}
		LOGGER.info("final table" );
		System.out.println(tableContents);
		return tableContents;
	}
	

	
	
	public static List<List<String>> globalTableContents(List<Object[]> result, LinkedHashMap<String, List<Object[]>> channels, LinkedHashMap<String, List<Object[]>> recipients, LinkedHashMap<String, List<Object[]>> dacSectors, Boolean isGrouped, Integer limit) {
		System.out.println("LIMIT: " + limit);
		if ( limit == null) {
			limit = result.size();
			System.out.println("----> " + limit);
		}
		
		
		
		List<List<String>> tableContents = new ArrayList<List<String>>();
		for (int i=0; i < result.size(); i++) {
			List<String> row = new ArrayList<String>();
			
			List<String> total = new ArrayList<String>();
			
			// add total table
			for (Object o : result.get(i)) {
				if (o != null) {
					total.add(o.toString());
					row.add(o.toString());
				}
				else { 
					total.add("");
					row.add("");
				}
			}
			
			// add sector table first row	
			try {
				for (Object o : dacSectors.get(total.get(0)).get(0)) {
					if (o != null) {
						row.add(o.toString());
					}
					else {
						row.add("");
					}
				}
			}catch (Exception e) {
				for (int k =0; k< 4; k++)
					row.add("");
			}
			
			// add channels table first row
			try {
				for (Object o : channels.get(total.get(0)).get(0)) {
					if (o != null) {
						row.add(o.toString());
					}
					else {
						row.add("");
					}
				}
			}catch (Exception e) {
				for (int k =0; k< 4; k++)
					row.add("");
			}
			
			// add recipient table
			try {
				for (Object o : recipients.get(total.get(0)).get(0)) {
					if (o != null) 
						row.add(o.toString());
					else 
						row.add("");
				}
			}catch (Exception e) {
				for (int k =0; k< 4; k++)
					row.add("");
			}
			
			// adding first row
			tableContents.add(row);
			
			
			
			// adding other rows
			for(int j = 1; j < limit; j++ ){
				List<String> otherRows = new ArrayList<String>();
				// total
				// takes just the name, not the budget
				for (int x=0 ; x< total.size(); x++) {
					if ( isGrouped ) {
						if ( x != 2)
							otherRows.add(total.get(x));
						else
							otherRows.add("");
					}
					else {
						otherRows.add("");
					}
				}
				
//				for (String t : total) {
//					if ( isGrouped ) {
//						
//						otherRows.add(t);
//					}
//					else
//						otherRows.add("");
//				}
				
				// add sector table first row
				try {
					for (Object o : dacSectors.get(total.get(0)).get(j)) {
						if (o != null) {
							otherRows.add(o.toString());
						}
						else {
							otherRows.add("");
						}
					}
				}catch (Exception e) {
					for (int k =0; k< 4; k++)
						otherRows.add("");
				}
				
				// add channels table first row
				try {
					for (Object o : channels.get(total.get(0)).get(j)) {
						if (o != null) {
							otherRows.add(o.toString());
						}
						else {
							otherRows.add("");
						}
					}
				}catch (Exception e) {
					for (int k =0; k< 4; k++)
						otherRows.add("");
				}
				
				// add recipient table
				try {
					for (Object o : recipients.get(total.get(0)).get(j)) {
						if (o != null) 
							otherRows.add(o.toString());
						else 
							otherRows.add("");
					}
				}catch (Exception e) {
					for (int k =0; k< 4; k++)
						otherRows.add("");
				}
				
				if ( checkRow(otherRows, total.size()) )
					tableContents.add(otherRows);
			}


		}
		
		LOGGER.info("TABLE CONTENT");
		for(List<String> row : tableContents)
			System.out.println(row);
//			LOGGER.info("tableContents: " + row);
		
		return tableContents;
	}
	
	
	public static List<List<String>> countryTableContents(List<Object[]> result, LinkedHashMap<String, List<Object[]>> channels, LinkedHashMap<String, List<Object[]>> dacSectors, Boolean isGrouped, Integer limit) {
		System.out.println("LIMIT: " + limit);
		if ( limit == null) {
			limit = result.size();
			System.out.println("----> " + limit);
		}
		
		List<List<String>> tableContents = new ArrayList<List<String>>();
		for (int i=0; i < result.size(); i++) {
			List<String> row = new ArrayList<String>();
			
			List<String> total = new ArrayList<String>();
			
			// add total table
			for (Object o : result.get(i)) {
				if (o != null) {
					total.add(o.toString());
					row.add(o.toString());
				}
				else { 
					total.add("");
					row.add("");
				}
			}
			
			// add sector table first row	
			try {
				for (Object o : dacSectors.get(total.get(0)).get(0)) {
					if (o != null) {
						row.add(o.toString());
					}
					else {
						row.add("");
					}
				}
			}catch (Exception e) {
				for (int k =0; k< 3; k++)
					row.add("");
			}
			
			// add channels table first row
			try {
				for (Object o : channels.get(total.get(0)).get(0)) {
					if (o != null) {
						row.add(o.toString());
					}
					else {
						row.add("");
					}
				}
			}catch (Exception e) {
				for (int k =0; k< 3; k++)
					row.add("");
			}
			
						
			// adding first row
			tableContents.add(row);
			
			
			
			// adding other rows
			for(int j = 1; j < limit; j++ ){
				List<String> otherRows = new ArrayList<String>();
				// total
				// takes just the name, not the budget
				for (int x=0 ; x< total.size(); x++) {
					if ( isGrouped ) {
						if ( x != 2)
							otherRows.add(total.get(x));
						else
							otherRows.add("");
					}
					else {
						otherRows.add("");
					}
				
				}
		
//				for (String t : total) {
//					if ( isGrouped )
//						otherRows.add(t);
//					else
//						otherRows.add("");
//				}
				
				// add sector table first row
				try {
					for (Object o : dacSectors.get(total.get(0)).get(j)) {
						if (o != null) {
							otherRows.add(o.toString());
						}
						else {
							otherRows.add("");
						}
					}
				}catch (Exception e) {
					for (int k =0; k< 3; k++)
						otherRows.add("");
				}
				
				// add channels table first row
				try {
					for (Object o : channels.get(total.get(0)).get(j)) {
						if (o != null) {
							otherRows.add(o.toString());
						}
						else {
							otherRows.add("");
						}
					}
				}catch (Exception e) {
					for (int k =0; k< 3; k++)
						otherRows.add("");
				}
				
							
				if ( checkRow(otherRows, total.size()) )
					tableContents.add(otherRows);
			}


		}
		
		LOGGER.info("TABLE CONTENT");
		for(List<String> row : tableContents)
			System.out.println(row);
//			LOGGER.info("tableContents: " + row);
		
		return tableContents;
	}
	
	
	
	
	
	public static List<List<String>> donorTableContents(List<Object[]> result, LinkedHashMap<String, List<Object[]>> recipients, Boolean isGrouped, Integer rowSize, Integer subRows) {
		List<List<String>> tableContents = new ArrayList<List<String>>();
		for (int i=0; i < result.size(); i++) {
			List<String> row = new ArrayList<String>();
			
			List<String> total = new ArrayList<String>();
			
			// add total table
			for (Object o : result.get(i)) {
				if (o != null) {
					total.add(o.toString());
					row.add(o.toString());
				}
				else { 
					total.add("");
					row.add("");
				}
			}
			
			// add recipient table
			try {
				for (Object o : recipients.get(total.get(0)).get(0)) {
					if (o != null) 
						row.add(o.toString());
					else 
						row.add("");
				}
			}catch (Exception e) {
				for (int k =0; k < rowSize; k++)
					row.add("");
			}
			
			// adding first row
			tableContents.add(row);
			
			
			
			// adding other rows
			for(int j = 1; j < subRows ; j++ ){
				List<String> otherRows = new ArrayList<String>();
				// total
				// takes just the name, not the budget
				for (int x=0 ; x< total.size(); x++) {
					if ( isGrouped ) {
						if ( x != 2)
							otherRows.add(total.get(x));
						else
							otherRows.add("");
					}
					else {
						otherRows.add("");
					}
				
				}
				
//				for (String t : total) {
//					if ( isGrouped )
//						otherRows.add(t);
//					else
//						otherRows.add("");
//				}
				
								
				// add recipient table
				try {
					for (Object o : recipients.get(total.get(0)).get(j)) {
						if (o != null) 
							otherRows.add(o.toString());
						else 
							otherRows.add("");
					}
				}catch (Exception e) {
					for (int k =0; k< rowSize; k++)
						otherRows.add("");
				}
				
				if ( checkRow(otherRows, total.size()) )
					tableContents.add(otherRows);
				
			}


		}
		
		LOGGER.info("TABLE TOP SECTOR BY DONOR CONTENT");
		for(List<String> row : tableContents)
			System.out.println(row);
//			LOGGER.info("tableContents: " + row);
		
		return tableContents;
	}
	
	
	
	
	public static List<List<String>> tableContents(List<Object[]> result, LinkedHashMap<String, List<Object[]>> donors, LinkedHashMap<String, List<Object[]>> channels, Boolean isGrouped, Integer limit) {
		System.out.println("LIMIT: " + limit);
		if ( limit == null) {
			limit = result.size();
			System.out.println("----> " + limit);
		}
		
		List<List<String>> tableContents = new ArrayList<List<String>>();
		for (int i=0; i < result.size(); i++) {
			List<String> row = new ArrayList<String>();
			
			List<String> total = new ArrayList<String>();
			
			// add total table
			for (Object o : result.get(i)) {
				if (o != null) {
					total.add(o.toString());
					row.add(o.toString());
				}
				else { 
					total.add("");
					row.add("");
				}
			}
			
			// add donors table
			try {
				for (Object o : donors.get(total.get(0)).get(0)) {
					if (o != null) 
						row.add(o.toString());
					else 
						row.add("");
				}
			}catch (Exception e) {
				for (int k =0; k< 3; k++)
					row.add("");
			}
			
			// add channels table
			try {
				for (Object o : channels.get(total.get(0)).get(0)) {
					if (o != null) 
						row.add(o.toString());
					else 
						row.add("");
				}
			}catch (Exception e) {
				for (int k =0; k< 3; k++)
					row.add("");
			}
			
			
			// adding first row
			tableContents.add(row);
			
			
			
			// adding other rows
			for(int j = 1; j < limit; j++ ){
				List<String> otherRows = new ArrayList<String>();
				// total
				// takes just the name, not the budget
				for (int x=0 ; x< total.size(); x++) {
					if ( isGrouped ) {
						if ( x != 2)
							otherRows.add(total.get(x));
						else
							otherRows.add("");
					}
					else {
						otherRows.add("");
					}
				
				}
				
				
//				for (String t : total) {
//					if ( isGrouped )
//						otherRows.add(t);
//					else
//						otherRows.add("");
//				}
				
								
				// add donors table
				try {
					for (Object o : donors.get(total.get(0)).get(j)) {
						if (o != null) 
							otherRows.add(o.toString());
						else 
							otherRows.add("");
					}
				}catch (Exception e) {
					for (int k =0; k< 3; k++)
						otherRows.add("");
				}
				
				// add channels table
				try {
					for (Object o : channels.get(total.get(0)).get(j)) {
						if (o != null) 
							otherRows.add(o.toString());
						else 
							otherRows.add("");
					}
				}catch (Exception e) {
					for (int k =0; k< 3; k++)
						otherRows.add("");
				}
			
				
				if ( checkRow(otherRows, total.size()) )
					tableContents.add(otherRows);
				
			}


		}
		
		LOGGER.info("TABLE TOP AGRICUL SECTOR COUNTRY");
		for(List<String> row : tableContents)
			System.out.println(row);
//			LOGGER.info("tableContents: " + row);
		
		return tableContents;
	}
	
	
	
	/**
	 * 
	 * This takes a result made as
	 *  code1,label1,budget1,code2,label2,budget2...codeN,labelN,budgetN 
	 * 
	 * grouping by index1 (label1)
	 * 
	 * maxNumberAfterComma = max numbers after the comma
	 * 
	 * @param result
	 * @param maxNumberAfterComma
	 * @param isGrouped
	 * @param results
	 * @return
	 */
	public static List<List<String>> generalTableContents(List<List<String>> result, int maxNumberAfterComma, Boolean isGrouped) {
		List<List<String>> tableContents = new ArrayList<List<String>>();
		HashMap<String, String> value = new HashMap<String, String>();
		int counter=1;
		int groupIndex = 1;
		Boolean addValue = false;

//		LOGGER.info("isGrouped: " + isGrouped);
		for(List<String> resultRow : result) {
			List<String> row = new ArrayList<String>();
			
			for(int index = 1; index < resultRow.size(); index++){
//				LOGGER.info("index: " + index);
				if ( isGrouped && groupIndex == index) {
					try {
						
						if ( !value.containsKey(resultRow.get(index))) {
							value.put(resultRow.get(index), getNumber(counter));
							counter++;
							addValue = true;
						}
						else {
							addValue = false;
						}
//						LOGGER.info("value label grouped: " +  resultRow.get(index));
						row.add("#" + value.get(resultRow.get(index)) + " - " + resultRow.get(index));
					}
					catch (Exception e) {
						row.add("");
					}
				}
				else {
//					LOGGER.info("value label: " +  resultRow.get(index));
					// label 
					addValue = false;
					row.add(resultRow.get(index));
				}
					
				// budget 
				index++;
//				LOGGER.info("value budget: " +  resultRow.get(index));
				// if if the value after the one to group
				if ( addValue && ((groupIndex+1) == index)) {
					row.add(FormatValues.formatValue(resultRow.get(index), maxNumberAfterComma));
				}
				else if (!addValue && ((groupIndex+1) == index)) {
					row.add("");
				}
				else
					row.add(FormatValues.formatValue(resultRow.get(index), maxNumberAfterComma));
					
				// skipping next code
				index++;
//				LOGGER.info("--->row: " +  row);
			}
			
			
			
			tableContents.add(row);
		}
//		LOGGER.info("final table" );
//		System.out.println(tableContents);
		return tableContents;
	}
	
	
	private static Boolean checkRow(List<String> row, Integer startingPoint){ 

		for(int i= startingPoint ; i < row.size(); i++) {
			if(!row.get(i).equalsIgnoreCase("")){
				return true;
			}
		}
		return false;
	}
	
	
	/** CREATE VIEW MATRIX **/
	
	public static void createMatrix(Map<String, Map<String, String>> matrixMap, Map<String, String> secondColumnMap, String tableTitle, ADAMResultVO vo) {
		
		// headers
		List<String> headers = new ArrayList<String>();
		headers.add(tableTitle);
		for (String key : secondColumnMap.keySet()) {
			headers.add(secondColumnMap.get(key));
		}
	//	headers.add("Total");

		
		// contents
		List<List<String>> contents = new ArrayList<List<String>>();

		/** For every country **/
		Map<String, Integer> counting = new HashMap<String, Integer>();

		
		for(String x : matrixMap.keySet()) {
			contents.add(createContentRow(x, matrixMap.get(x), secondColumnMap, counting));
		}
		
		

		
		// ADD totals
		contents.add(createSumRow(secondColumnMap, counting));
		
		String title = "Organizational Results per Recipient Countries";
		vo.setTitle(title);
		
		
		// set headers
		vo.setTableHeaders(headers);

		// set contents
		vo.setTableContents(contents);
	}
	
	private static List<String> createSumRow(Map<String, String> map, Map<String, Integer> countingMap) {
		List<String> row = new ArrayList<String>();
		row.add("(Total)");
		
		for(String key : map.keySet()) {
			
			if ( countingMap.containsKey(key)) {
				row.add("(" + countingMap.get(key).toString() + ")");
			}
			else 
				row.add("(0)");
		}

		row.add("-"); 
		
		return row;
	}
	
	private static List<String> createContentRow(String x, Map<String, String> matrixXMap, Map<String, String> yMap, Map<String, Integer> counting) {
		
		List<String> contents = new ArrayList<String>();
		contents.add(x);
		
		Integer total = new Integer(0);

		for(String key : yMap.keySet()) {
			Boolean added = false;
			if ( matrixXMap.containsKey(key)) {
				String title = matrixXMap.get(key) + ": "+yMap.get(key);
				
				contents.add("<IMG src=\"images/accept.png\" BORDER=\"0\" TITLE=\""+x+"\"/>");
				addCounting(counting, key);
				total++;
				added = true;
			}

			if ( !added )
				contents.add("");
		}
		
		//contents.add("(" + total.toString() + ")"); 
		return contents;
	}

	private static void addCounting(Map<String, Integer> map, String key) {
		Integer value = 1;
		if( map.containsKey(key)) {
			value = map.get(key);
			value++;
			
		}
		map.put(key, value);
	}
	
	
	public static List<String> globalContributionHeaders(String currentDatasetSelection) {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Year");
		
		String title = "";
		if(currentDatasetSelection.equals(ADAMSelectedDataset.ADAM_FPMIS.name())){
			title = "Approvals (USD)";
		} else {
			title = "Commitments Current (USD Mil)";
		}
		
		tableHeaders.add(title);
		
		if(currentDatasetSelection.equals(ADAMSelectedDataset.ADAM_CRS.name())) {
			tableHeaders.add("Commitments Constant (2011 USD Mil)");
			tableHeaders.add("Disbursements Current (USD Mil)");
			tableHeaders.add("Disbursements Constant (2011 USD Mil)");
		}
			

		return tableHeaders;
	}
	
	public static List<String> globalImplementingAgenciesHeaders(String currentDatasetSelection) {
		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Implementing Agency");
		String title = "";
		if(currentDatasetSelection.equals(ADAMSelectedDataset.ADAM_FPMIS.name())){
			title = "Approvals (USD)";
		} else {
			title = "Commitments Current (USD Mil)";
		}
		
		tableHeaders.add(title);
		
		if(currentDatasetSelection.equals(ADAMSelectedDataset.ADAM_CRS.name()))
			tableHeaders.add("Disbursements Current (USD Mil)");
		
		return tableHeaders;
	}
	
	
}
