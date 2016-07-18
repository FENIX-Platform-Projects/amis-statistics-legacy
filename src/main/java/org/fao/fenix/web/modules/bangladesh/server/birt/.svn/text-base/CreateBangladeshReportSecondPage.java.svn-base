package org.fao.fenix.web.modules.bangladesh.server.birt;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.domain.bangladesh.BangladeshBean;
import org.fao.fenix.core.domain.bangladesh.BangladeshReportStyleBean;
import org.fao.fenix.web.modules.bangladesh.common.vo.BangladeshChartsBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.GraphReportEngine;

public class CreateBangladeshReportSecondPage {

	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	BangladeshBean bangladeshBean = null;
	
	BangladeshChartsBean charts = null;
	
	String priceChangeTable = null;
	
	BangladeshReportStyleBean styleBean = null;
	
	
	private static final Logger LOGGER = Logger.getLogger(CreateBangladeshReportSecondPage.class);
	
	public CreateBangladeshReportSecondPage(ReportDesignHandle designHandle, ElementFactory designFactory, BangladeshBean bangladeshBean, String priceChangeTable, BangladeshChartsBean charts, BangladeshReportStyleBean styleBean) {
		this.designHandle = designHandle;
		this.designFactory = designFactory;
		this.bangladeshBean = bangladeshBean;
		this.priceChangeTable = priceChangeTable;
		this.charts = charts;
		this.styleBean = styleBean;
		
		try {
			createSecondPage();
		} catch (SemanticException e) {
			e.printStackTrace();
		}
		
//		LOGGER.info("************************************************** CHANGE TABLE");
//		LOGGER.info(this.priceChangeTable);
	}
	
	
	
	
	private void createSecondPage() throws SemanticException {
		createSecondPageHeader();
		DesignUtils.addSpace(designHandle);
		createSecondPageDomesticPrices();
		DesignUtils.addSpace(designHandle);
		DesignUtils.addSpace(designHandle);
		createSecondPageInternationalPrices();
	}
	

	
	private void createSecondPageInternationlPricesText() throws SemanticException {
		DesignUtils.addSpace(designHandle);
		// add "International Prices" strip
//		GridHandle textGridHandle = designFactory.newGridItem("internText", 1, 2);	
//		textGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP,  styleBean.getBackgroundText());
		
//		RowHandle row = (RowHandle) textGridHandle.getRows().get(1);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");	
		text = DesignUtils.createText(designHandle, text, styleBean.getTextSizeBigger(), "justify");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent("<div>" + bangladeshBean.getInternationalPricesText() + "</div>");
		text.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP,  styleBean.getBackgroundText());
		
//		gridCellHandle.getContent().add(text);
		DesignUtils.setBorderGrid(text, "#000000");
		
		designHandle.getBody().add(text);
//		designHandle.getBody().add(textGridHandle);
//		designHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
	}
	
	private void createExplanatoryLegend() throws SemanticException {
		// add "Domestic Prices" strip
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent("<div style='font-style:italic; width: 100%;'> * Average fob price; FAO, International Commodity Prices, Weekly data; ** Thai Rice Exporters Association, Parboiled rice quotes; *** Thailand Grain and Feed Weekly Rice Price Update, USDA, GAIN Report; indicative fob prices based on averaged exporter observations; **** Government of India, Ministry Of Consumer Affairs Food and Public Distribution, Department Of Consumer Affairs, Price Monitoring Cell </div>");	
		// add export options
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getLegendSize());
		designHandle.getBody().add(text);	
	}
	

	
	private void createSecondPageDomesticPrices() throws SemanticException {
		// add "Domestic Prices" strip
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent("<div style='font-weight: bold;'> 1. Domestic Prices: coarse rice and atta </div>");
		
		// add export options
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, styleBean.getColorTitle());
		text.setProperty(StyleHandle.COLOR_PROP, styleBean.getColorTitleText());
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.FONT_SIZE_PROP,  styleBean.getTitleTextSize());
		DesignUtils.setBorderGrid(text, "#000000");
		designHandle.getBody().add(text);	
		DesignUtils.addSpace(designHandle);
		createSecondPageDomesticPricesCharts();
		DesignUtils.addSpace(designHandle);
		createSecondPageDomesticPricesTable();
		
	}
	
	
	
	private void createSecondPageDomesticPricesCharts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("domesticPricesChart", 3, 3);	
		
		/** create FIGURE 1 TITLE **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("figure1Text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> Figure 1. Change in prices of Coarse Rice (Dhaka) </div>");	
		gridCellHandle.getContent().add(text);
		
		/** create FIGURE 1 SOURCE **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("source1Text");	
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getLegendSize());
		text.setContent("<div style='font-style:italic;'> Source: DAM </div>");	
		gridCellHandle.getContent().add(text);
			
		/** create FIGURE 1 CHART **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		GraphReportEngine gre0 = new GraphReportEngine(charts.getRiceDomesticPrices(), designHandle);
		gridCellHandle.getContent().add(gre0.getChart());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		/** create FIGURE 2 TITLE **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("figure2Text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> Figure 2. Change in prices of Atta (Dhaka) </div>");	
		gridCellHandle.getContent().add(text);
		
		/** create FIGURE 2 CHART **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);	
		GraphReportEngine gre1 = new GraphReportEngine(charts.getAttaDomesticPrices(), designHandle);
		gridCellHandle.getContent().add(gre1.getChart());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		/** create FIGURE 2 SOURCE **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("source2Text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getLegendSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
//		text.setProperty(StyleHandle.FONT_SIZE_PROP, DesignChoiceConstants.FONT_SIZE_SMALLER);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setContent("<div style='font-style:italic;'> Source: DAM </div>");	
		gridCellHandle.getContent().add(text);

		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "48%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "4%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "48%");
		designHandle.getBody().add(dataGridHandle);
	} 
	
	private void createSecondPageDomesticPricesTable() throws SemanticException { // TODO Price Change Table
		GridHandle dataGridHandle = designFactory.newGridItem("domesticPricesTable", 3, 1);	
		
		/** create Table 1 text **/
		GridHandle textGridHandle = designFactory.newGridItem("domesticPricesTable2", 1, 2);	
		RowHandle row = (RowHandle) textGridHandle.getRows().get(1);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("table1Text");	
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "justify");
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getTextSizeBigger());
		text.setContent("<div>" + bangladeshBean.getDomesticPricesText() + "</div>");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, styleBean.getBackgroundText());
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		gridCellHandle.getContent().add(textGridHandle);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, styleBean.getBackgroundText());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		CellHandle priceChangeCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle priceChangeText = designHandle.getElementFactory().newTextItem("priceChangeText");
		priceChangeText.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		priceChangeText.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		priceChangeText.setContent(priceChangeTable);
		text = DesignUtils.createText(designHandle, text, styleBean.getLegendSize(), "left");
		text.setContent("<div style='font-wight:bold;'> Source: DAM Arrows indicate the direction of price change: red if more than 5% rice for annual changes and 1% for monthly/fortnightly changes, green if more than 5% decrease for annual changes and 1% for mnthly/fortnightly changes, yellow otherwise. The yearly change is calculated fortnight to fortnight.</div>");
		priceChangeCellHandle.getContent().add(priceChangeText);
		priceChangeCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "48%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "4%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "48%");
		designHandle.getBody().add(dataGridHandle);
	}
	
	private void createSecondPageInternationalPrices() throws SemanticException { // add "Domestic Prices" strip
		// add "International Prices" strip
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent("<div> 2. International Prices </div>");
		
		// add export options
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP,  styleBean.getColorTitle());
		text.setProperty(StyleHandle.COLOR_PROP,  styleBean.getColorTitleText());
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getTitleTextSize());
		DesignUtils.setBorderGrid(text, "#000000");
		designHandle.getBody().add(text);
		DesignUtils.addSpace(designHandle);
		createSecondPageInternationalPricesCharts();
		DesignUtils.addSpace(designHandle);
		createSecondPageInternationlPricesText();
	}
	
	private void createSecondPageInternationalPricesCharts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("domesticPricesChart", 3, 3);	
		
		/** create FIGURE 3 TITLE **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("figure3Text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP,  styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> Figure 3. Rice wholesale price in Dhaka and relevant international markets </div>");	
		gridCellHandle.getContent().add(text);
		
		
		/** create FIGURE 3 CHART **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		
		/** ADDING FIST CHART adding dataset**/
		GraphReportEngine gre2 = new GraphReportEngine(charts.getRiceInternationalPrices(), designHandle);
		gridCellHandle.getContent().add(gre2.getChart());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		/** create FIGURE 4 TITLE **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("figure4Text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP,  styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> Figure 4. Wheat wholesale price in Dhaka and relevant international markets </div>");	
		gridCellHandle.getContent().add(text);
		
		/** create FIGURE 4 CHART **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);	
		GraphReportEngine gre3 = new GraphReportEngine(charts.getWheatInternationalPrices(), designHandle);
		gridCellHandle.getContent().add(gre3.getChart());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "48%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "4%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "48%");
		designHandle.getBody().add(dataGridHandle);
		createExplanatoryLegend();
	}
	
	private void createSecondPageHeader() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("otherHeaderPage", 3, 1);	
		
		/** create issue **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
	
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("secondIssue");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP,  styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setContent("<div style='font-style:italic;'> Fortnightly Foodgrain Outlook, " + bangladeshBean.getIssue() + "</div>");	
		gridCellHandle.getContent().add(text);	
		
		/** create date **/ 
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		
		text = designHandle.getElementFactory().newTextItem("secondDate");
//		text.setWidth("5cm");
//		text.setHeight("0.5cm");
		
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
		text.setContent("<div>" + bangladeshBean.getDate() + "</div>");
		
		gridCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "35%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "10%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "20%");
		designHandle.getBody().add(dataGridHandle);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}