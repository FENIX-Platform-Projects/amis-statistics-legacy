package org.fao.fenix.web.modules.bangladesh.server.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;

import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.core.utils.bangladesh.PriceChangeOverThePastTwoWeeksBean;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class BangladeshTableCreator {

	private static String DIV = "<div style='font-size: 7pt; text-align: center; font-weight: bold; '>";
	
	private static String DIV_LEFT = "<div style='font-size: 7pt; text-align: left; '>";
	
	private static String DIV_RIGHT = "<div style='font-size: 7pt; text-align: right; '>";
	
	private static String GREEN = "<div style='font-size: 7pt; background-color: #008800; color:black; text-align: center;'>";
//	private static String GREEN = "<div style='font-size: 7pt; text-align: center;'>";
	
	private static String RED = "<div style='font-size: 7pt; background-color: #FF0000; text-align: center;'>";
	
//	private static String LIGHT = "<div style='font-size: 7pt; background-color: #C0C0C0; text-align: center;'>";
	
	private static String LIGHT = "<div style='font-size: 7pt; text-align: center;'>";
	
	private static String DARK = "<div style='font-size: 7pt; text-align: center;'>";
	
//	private static String DARK = "<div style='font-size: 7pt; background-color: #808080; text-align: center;'>";
	
	private static int rowCounter;
	
	private static DecimalFormat formatTableValue;
	
	private static DecimalFormat formatChangeValue;

	public static String createPriceChangeOverTwoWeeksTable(PriceChangeOverThePastTwoWeeksBean bean, Date reportDate, Date previousDate, Date lastYearDate) {
		formatChangeValue = new DecimalFormat("#.#");
		formatTableValue = new DecimalFormat("#.##");
		
		
		// initiate string builder
		StringBuilder table = new StringBuilder();
		rowCounter = 0;

		// calculate dates
		String actualDate = FieldParser.parseDDMMYYDate(reportDate);
//		String previousDate = FieldParser.parseDate(BangladeshUtils.getPreviousFortnight(reportDate));
		String previous = FieldParser.parseDDMMYYDate(previousDate);
		String lastYear = FieldParser.parseDDMMYYDate(lastYearDate);

		// add table name
		table.append(createTableName());
		
		// initiate table
		table.append("<table BORDER=1>");

		// add headers
		table.append(createHeader(actualDate, previous, lastYear));

		// add rice
		table.append(createCommodityTitle("RICE"));
		table.append(createRow(PriceType.retail, Commodity.rice, bean));
		table.append(createRow(PriceType.wholesale, Commodity.rice, bean));

		// add atta
		table.append(createCommodityTitle("ATTA"));
		table.append(createRow(PriceType.retail, Commodity.atta, bean));
		table.append(createRow(PriceType.wholesale, Commodity.atta, bean));

		// close table
		table.append("</table>");

		// return result
		return table.toString();
	}
	
	private static StringBuilder createTableName() {
		StringBuilder name = new StringBuilder();
		name.append("<div style='font-weight: bold; font-size: 10pt; '>Table 1: Rice and atta price changes (Dhaka City)</div>");
		return name;
	}

//	private static StringBuilder createHeader(String actualDate, String previousDate, String lastYear) {
//		StringBuilder header = new StringBuilder();
//
//		header.append("<tr>");
//		header.append("<td rowspan='2' ></td>");
//		header.append("<td rowspan='2' height='100%' valign=middle align='center'>").append(GREEN).append("Price on ").append(previousDate).append(" Tk/kg</div></td>");
//		header.append("<td colspan='3' height='100%' align='center'>").append(GREEN).append("change in % </div></td>");
//		header.append("<tr>");
//			header.append("<td height='100%' align='center'>").append(GREEN).append("over last fortnight</div></td>");
//			header.append("<td height='100%' align='center'>").append(GREEN).append("over last month</div></td>");
//			header.append("<td height='100%' align='center'>").append(GREEN).append("over last year</div></td>");
//		header.append("</tr>");
//
//
//		return header;
//	}
	
	private static StringBuilder createHeader(String actualDate, String previousDate, String lastYear) {
		StringBuilder header = new StringBuilder();

		header.append("<tr>");
		header.append("<td colspan='2'>  </td>");
		header.append("<td colspan='2' height='100%' valign=middle align='center'>").append(GREEN).append("Price on <br>").append(actualDate).append(" Tk/kg</div></td>");;
		header.append("<td colspan='2' height='100%' align='center'>").append(GREEN).append("over last <br> fortnight</div></td>");
		header.append("<td colspan='2' height='100%' align='center'>").append(GREEN).append("over last <br> month</div></td>");
		header.append("<td colspan='2' height='100%' align='center'>").append(GREEN).append("over last <br> year</div></td>");

		return header;
	}

	private static StringBuilder createCommodityTitle(String commodity) {
		StringBuilder title = new StringBuilder();
		title.append("<tr><td height='100%' colspan='10' align='center'>").append(DIV).append(commodity).append("</div></td></tr>");
		return title;
	}
	
	private static StringBuilder createRow(PriceType priceType, Commodity commodity, PriceChangeOverThePastTwoWeeksBean bean) {
		System.out.println("commodity: " + commodity  + " | " + priceType);
		StringBuilder row = new StringBuilder();
		Double oldPrice = null;
		Double newPrice = null;
		Double lastMonth = null;
		Double lastYear = null;
		String div = DARK;
		if (rowCounter % 2 == 0)
			div = LIGHT;
		row.append("<tr>");
		row.append("<td colspan='2' height='100%'>").append(div).append(priceType.name()).append("</div></td>");
//		switch (commodity) {
//			case rice:
			if ( commodity.toString().equals("rice")) {
				switch (priceType) {
					case retail: 
						oldPrice = bean.getOldRetailRice(); 
						newPrice = bean.getNewRetailRice();
						lastMonth =  bean.getLastMonthRetailRice();
						lastYear =  bean.getLastYearRetailRice();
						break;
					case wholesale: 				
						oldPrice = bean.getOldWholesaleRice(); 
						newPrice = bean.getNewWholesaleRice(); 
						lastMonth =  bean.getLastMonthWholesaleRice(); 
						lastYear =  bean.getLastYearWholesaleRice(); 
						break;
				}
			}
//			case atta:
			else {
				switch (priceType) {
					case retail: 
						oldPrice = bean.getOldRetailAtta(); 
						newPrice = bean.getNewRetailAtta(); 
						lastMonth =  bean.getLastMonthRetailAtta(); 
						lastYear =  bean.getLastYearRetailAtta(); 
						break;
					case wholesale: 
						oldPrice = bean.getOldWholesaleAtta(); 
						newPrice = bean.getNewWholesaleAtta(); 
						lastMonth =  bean.getLastMonthWholesaleAtta(); 
						lastYear =  bean.getLastYearWholesaleAtta(); 
						break;
				}
			
			}
//			break;
//		}
	
		System.out.println(newPrice + " | " + oldPrice + " | " + lastMonth + " | " + lastYear);
		if ( newPrice != null)
			row.append("<td colspan='2' height='100%'> ").append(div).append(formatTableValue.format(newPrice)).append(" </div></td>");
		else
			row.append("<td colspan='2' height='100%'>").append(div).append("n.a").append("</div></td>");
		
		if ( oldPrice != null) {
			row.append("<td height='100%'>").append(DIV_RIGHT).append(getSymbol(oldPrice)).append("</div></td>");
			row.append("<td height='100%'>").append(DIV_LEFT).append(formatChangeValue.format(oldPrice)).append("% </div></td>");
		}
		else
			row.append("<td colspan='2' height='100%'>").append(div).append("n.a").append("</div></td>");
		
		if ( lastMonth != null) {
			row.append("<td height='100%'>").append(DIV_RIGHT).append(getSymbol(lastMonth)).append("</div></td>");
			row.append("<td height='100%'> ").append(DIV_LEFT).append(formatChangeValue.format(lastMonth)).append("% </div></td>");
		}
		else 
			row.append("<td colspan='2' height='100%'>").append(div).append("n.a").append("</div></td>");
		
		if ( lastYear != null) {
			row.append("<td height='100%'>").append(DIV_RIGHT).append(getSymbol(lastYear)).append("</div></td>");
			row.append("<td height='100%'>").append(DIV_LEFT).append(formatChangeValue.format(lastYear)).append("%  </div></td>");
		}
		else
			row.append("<td colspan='2' height='100%'>").append(div).append("n.a").append("</div></td>");		
//		row.append("<td height='100%'>").append(div).append(BangladeshUtils.getChangePercentage(oldPrice, newPrice)).append("</div></td>");
		row.append("</tr>");
		rowCounter++;
		return row;
	}
	
	private static String getSymbolAnnual(Double value){
		
		if ( value >= 5) 
			return "<IMG SRC=\""+ Setting.getSystemPath() + File.separator + "fenix"  + File.separator +"images"+ File.separator +"arrow-up-report.png\" VSPACE=\"3\" WIDTH=\"8\" HEIGHT=\"8\">";
		else if ( value <= -5) 
			return "<IMG SRC=\""+ Setting.getSystemPath() + File.separator + "fenix"  + File.separator +"images"+ File.separator +"arrow-down-report.png\" VSPACE=\"3\" WIDTH=\"8\" HEIGHT=\"8\">";
		else
			return "<IMG SRC=\""+ Setting.getSystemPath() + File.separator + "fenix"  + File.separator +"images"+ File.separator +"arrow-stable-report.png\" VSPACE=\"3\" WIDTH=\"8\" HEIGHT=\"8\">";
		
	}
	
	private static String getSymbol(Double value){

		if ( value >= 1) 
			return "<IMG SRC=\""+ Setting.getSystemPath() + File.separator + "fenix"  + File.separator +"images"+ File.separator +"arrow-up-report.png\"  VSPACE=\"3\" WIDTH=\"8\" HEIGHT=\"8\">";
		else if ( value <= -1) 
			return "<IMG SRC=\""+ Setting.getSystemPath() + File.separator + "fenix"  + File.separator +"images"+ File.separator +"arrow-down-report.png\" VSPACE=\"3\" WIDTH=\"8\" HEIGHT=\"8\">";
		else 
			return "<IMG SRC=\""+ Setting.getSystemPath() + File.separator + "fenix"  + File.separator +"images"+ File.separator +"arrow-stable-report.png\" VSPACE=\"3\" WIDTH=\"8\" HEIGHT=\"8\">";		
	}


	private enum PriceType {
		retail, wholesale;
	}
	
	private enum Commodity {
		rice, atta;
	}

}