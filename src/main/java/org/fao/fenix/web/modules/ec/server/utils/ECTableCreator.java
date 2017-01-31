package org.fao.fenix.web.modules.ec.server.utils;


public class ECTableCreator {

	private static String DIV = "<div style='font-size: 7pt; text-align: center; font-weight: bold; '>";
	
	private static String GREEN = "<div style='font-size: 7pt; background-color: #449E55; color:white; text-align: center;'>";
	
	private static String LIGHT = "<div style='font-size: 7pt; background-color: #C0C0C0; text-align: center;'>";
	
	private static String DARK = "<div style='font-size: 7pt; background-color: #808080; text-align: center;'>";
	
	private static int rowCounter;

/*	public static String createWB(Date reportDate) {

		// initiate string builder
		StringBuilder table = new StringBuilder();
		rowCounter = 0;

		// calculate dates
		String actualDate = FieldParser.parseDate(reportDate);
//		String previousDate = FieldParser.parseDate(BangladeshUtils.getPreviousFortnight(reportDate));

		// add table name
		table.append(createTableName());
		
		// initiate table
		table.append("<table>");

		// add headers
//		table.append(createHeader(actualDate, previousDate));

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
		name.append("<div style='font-weight: bold; font-size: 10pt; '>Table 1: Price change over the past two weeks<br>(Dhaka City)</div>");
		return name;
	}

	private static StringBuilder createHeader(String actualDate, String previousDate) {
		StringBuilder header = new StringBuilder();
		header.append("<tr>");
		header.append("<td height='100%'>").append(GREEN).append("&nbsp;<br><br></div></td>");
		header.append("<td height='100%' align='center'>").append(GREEN).append("Price on ").append(previousDate).append(" Tk/kg</div></td>");
		header.append("<td height='100%' align='center'>").append(GREEN).append("Price on ").append(actualDate).append(" Tk/kg</div></td>");
		header.append("<td height='100%'>").append(GREEN).append("% change<br><br></div></td>");
		header.append("</tr>");
		return header;
	}

	private static StringBuilder createCommodityTitle(String commodity) {
		StringBuilder title = new StringBuilder();
		title.append("<tr><td height='100%' colspan='4' align='center'>").append(DIV).append(commodity).append("</div></td></tr>");
		return title;
	}
	
	private static StringBuilder createRow(PriceType priceType, Commodity commodity, PriceChangeOverThePastTwoWeeksBean bean) {
		StringBuilder row = new StringBuilder();
		Double oldPrice = null;
		Double newPrice = null;
		String div = DARK;
		if (rowCounter % 2 == 0)
			div = LIGHT;
		row.append("<tr>");
		row.append("<td height='100%'>").append(div).append(priceType.name()).append("</div></td>");
		switch (commodity) {
			case rice:
				switch (priceType) {
					case retail: 
						oldPrice = bean.getOldRetailRice(); 
						newPrice = bean.getNewRetailRice(); 
						break;
					case wholesale: 
						oldPrice = bean.getOldWholesaleRice(); 
						newPrice = bean.getNewWholesaleRice(); 
						break;
				}
			case atta:
				switch (priceType) {
					case retail: 
						oldPrice = bean.getOldRetailAtta(); 
						newPrice = bean.getNewRetailAtta(); 
						break;
					case wholesale: 
						oldPrice = bean.getOldWholesaleAtta(); 
						newPrice = bean.getNewWholesaleAtta(); 
						break;
				}
			break;
		}
		row.append("<td height='100%'>").append(div).append(oldPrice).append("</div></td>");
		row.append("<td height='100%'>").append(div).append(newPrice).append("</div></td>");
//		row.append("<td height='100%'>").append(div).append(BangladeshUtils.getChangePercentage(oldPrice, newPrice)).append("</div></td>");
		row.append("</tr>");
		rowCounter++;
		return row;
	}

	private enum PriceType {
		retail, wholesale;
	}
	
	private enum Commodity {
		rice, atta;
	}
*/
}