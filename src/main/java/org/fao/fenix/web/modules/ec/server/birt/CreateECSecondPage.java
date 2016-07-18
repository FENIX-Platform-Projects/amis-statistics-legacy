package org.fao.fenix.web.modules.ec.server.birt;

import java.io.File;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.core.server.utils.Setting;


public class CreateECSecondPage {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String blue =  "#124c7d";
	
	String white = "#FFFFFF";
	
	String green = "#047221";
	
	String darkGreen = "#3B9D4B";
	
	String grey = "#C9CBCB";
	
	String orange = "#FF9933";
	
	String red = "#CA1616";
	
	String darkRed = "#A12312";
	
	String yellow = "#F8EDA5";
	
	String darkBlue = "#194f84";
	
	String darkYellow = "#FFCC00";
	
	String tableFontSize = "7.5pt";
	
	String headerFontSize = "10pt";
	
	String foodSituationSize = "0.2cm";
	
	String foodSituationFontSize = "8pt";
	
	String topPaddingHeaders = "0.1cm";
	
	String textFontSize = "9.5pt";
	
	String textSize = "1.9cm";
	
	public CreateECSecondPage(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	public void build() throws SemanticException {
		buildHeaderFirstRow();
		DesignUtils.addSpace(designHandle);
		buildBody();
	}
	
	private void buildHeaderFirstRow() throws SemanticException {	
	/** HEADER GRID **/
	GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderSRGrid", 7, 1);
	
	/** BLUE **/
	RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
	row.setProperty(RowHandle.HEIGHT_PROP, "1.5cm");
	
	CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
	TextItemHandle text = designHandle.getElementFactory().newTextItem("");
	text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	DesignUtils.setBorderGrid(gridCellHandle, blue);
	DesignUtils.setBorderGrid(text, blue);
	gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
	gridCellHandle.getContent().add(text);	
	
	/** FAO LOGO **/
	
	gridCellHandle = (CellHandle) row.getCells().get(1);
	ImageHandle FAOLogoImage = designHandle.getElementFactory().newImage("FaoLogo");
	FAOLogoImage.setWidth("1.1cm");
	FAOLogoImage.setHeight("1.1cm");
	FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
			+ File.separator + "ImgForTemplate" + File.separator + "fao.png\"");
	gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	DesignUtils.setBorderGrid(gridCellHandle, blue);
	gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
	gridCellHandle.getContent().add(FAOLogoImage);
	
	
	/** BLUE **/
	gridCellHandle = (CellHandle) row.getCells().get(2);
	text = designHandle.getElementFactory().newTextItem("");
	text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	DesignUtils.setBorderGrid(gridCellHandle, blue);
	DesignUtils.setBorderGrid(text, blue);
	gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
	gridCellHandle.getContent().add(text);	
	
	/** EC LOGO **/
	gridCellHandle = (CellHandle) row.getCells().get(3);
	ImageHandle ECLogoImage = designHandle.getElementFactory().newImage("ECLogoImage");
	ECLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//			+ File.separator + "ImgForTemplate" + File.separator + "eu.gif\"");
			+ File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
	gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	ECLogoImage.setWidth("1.5cm");
	ECLogoImage.setHeight("1cm");
	
	DesignUtils.setBorderGrid(gridCellHandle, blue);
	gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
	gridCellHandle.getContent().add(ECLogoImage);
	
	/** BLUE **/
	gridCellHandle = (CellHandle) row.getCells().get(4);
	text = designHandle.getElementFactory().newTextItem("");
	text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	DesignUtils.setBorderGrid(gridCellHandle, blue);
	DesignUtils.setBorderGrid(text, blue);
	gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
	gridCellHandle.getContent().add(text);	
	
	
	/** TEXT **/
	gridCellHandle = (CellHandle) row.getCells().get(5);
	text = DesignUtils.createText(designHandle, text, "12pt", "right");
	text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	text.setProperty(StyleHandle.COLOR_PROP, white);
	text.setContent("<div>Food Security Information for Decision Making <br></div>");
	DesignUtils.setBorderGrid(gridCellHandle, blue);
	gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	DesignUtils.setBorderGrid(text, blue);
	gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
	gridCellHandle.getContent().add(text);	
	
	text = DesignUtils.createText(designHandle, text,  "10pt", "right");
	text.setProperty(StyleHandle.COLOR_PROP, white);
	text.setContent("<div style='font-weight:bold'> www.foodsec.org </div>");
	gridCellHandle.getContent().add(text);	
	
	/** BLUE **/
	gridCellHandle = (CellHandle) row.getCells().get(6);
	text = designHandle.getElementFactory().newTextItem("");
	text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
	text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
	DesignUtils.setBorderGrid(gridCellHandle, blue);
	DesignUtils.setBorderGrid(text, blue);
	gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
	gridCellHandle.getContent().add(text);

	
	ColumnHandle col = (ColumnHandle) headerGridHandle.getColumns().get(0);
	col.setProperty("width", "2%");
	col = (ColumnHandle) headerGridHandle.getColumns().get(1);
	col.setProperty("width", "5%");	
	col = (ColumnHandle) headerGridHandle.getColumns().get(2);
	col.setProperty("width", "1%");
	col = (ColumnHandle) headerGridHandle.getColumns().get(3);
	col.setProperty("width", "10%");	
	col = (ColumnHandle) headerGridHandle.getColumns().get(4);
	col.setProperty("width", "30%");
	col = (ColumnHandle) headerGridHandle.getColumns().get(5);
	col.setProperty("width", "40%");	
	col = (ColumnHandle) headerGridHandle.getColumns().get(6);
	col.setProperty("width", "2%");
	
		
	designHandle.getBody().add(headerGridHandle);
}
	
//	private void buildHeaderFirstRow() throws SemanticException {
//		/** HEADER GRID **/
//		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderSRGrid", 2, 1);
//		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
//		row.setProperty(RowHandle.HEIGHT_PROP, "1.5cm");
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		
//		/** BUILD LEFT PART **/
//		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
//		text = DesignUtils.createText(designHandle, text, "12pt", "right");	
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#FFFFFF");
//		text.setProperty(StyleHandle.COLOR_PROP, blue);
//		text.setContent("<div style='font-weight:bold'>Country Brief: Price Monitoring and Analysis <br> " + ecBean.getDate() + " </div>");
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.getContent().add(text);	
//		
//		
//		/** BUILD RIGHT PART **/
//		GridHandle rightGridHandle = designFactory.newGridItem("fpHeaderGrid", 5, 1);
//		/** FAO LOGO **/
//		row = (RowHandle) rightGridHandle.getRows().get(0);
//		row.setProperty(RowHandle.HEIGHT_PROP, "1.5cm");
//		
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		ImageHandle FAOLogoImage = designHandle.getElementFactory().newImage("FaoLogo");
//		FAOLogoImage.setWidth("1.1cm");
//		FAOLogoImage.setHeight("1.1cm");
//		FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ImgForTemplate" + File.separator + "fao.png\"");
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.getContent().add(FAOLogoImage);
//		
//		
//		gridCellHandle = (CellHandle) row.getCells().get(1);
//		text = designHandle.getElementFactory().newTextItem("");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
//		DesignUtils.setBorderGrid(text, blue);
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.getContent().add(text);	
//		
//		
//		/** TEXT **/
//		gridCellHandle = (CellHandle) row.getCells().get(2);
//		text = designHandle.getElementFactory().newTextItem("");
//		text = DesignUtils.createText(designHandle, text,  "8pt", "center");	
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		text.setProperty(StyleHandle.COLOR_PROP, "#FFFFFF");
//		String html = "<div style='font-weight:bold'>Food Security Information for Decision Making <br></div>";
//		html += "<div style='font-weight:bold'>www.foodsec.org </div>";
//		text.setContent("<div style='font-weight:bold'>Food Security Information for Decision Making <br> www.foodsec.org </div>");
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		DesignUtils.setBorderGrid(text, blue);
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.getContent().add(text);	
//		
//		/** BLUE **/
//		gridCellHandle = (CellHandle) row.getCells().get(3);
//		text = designHandle.getElementFactory().newTextItem("");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
//		DesignUtils.setBorderGrid(text, blue);
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.getContent().add(text);
//		
//		/** EC LOGO **/
//		gridCellHandle = (CellHandle) row.getCells().get(4);
//		ImageHandle ECLogoImage = designHandle.getElementFactory().newImage("ECLogoImage");
//		ECLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		ECLogoImage.setWidth("1.5cm");
//		ECLogoImage.setHeight("1.1cm");
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.getContent().add(ECLogoImage);
//	
//		
//		
//		/** header column dimensions **/
//		ColumnHandle col = (ColumnHandle) headerGridHandle.getColumns().get(0);
//		col.setProperty("width", "50%");
//		col = (ColumnHandle) headerGridHandle.getColumns().get(1);
//		col.setProperty("width", "50%");	
//		
//		/** right column dimensions **/
//		col = (ColumnHandle) rightGridHandle.getColumns().get(0);
//		col.setProperty("width", "12%");
//		col = (ColumnHandle) rightGridHandle.getColumns().get(1);
//		col.setProperty("width", "0.5%");	
//		col = (ColumnHandle) rightGridHandle.getColumns().get(2);
//		col.setProperty("width", "65%");
//		col = (ColumnHandle) rightGridHandle.getColumns().get(3);
//		col.setProperty("width", "0.5%");	
//		col = (ColumnHandle) rightGridHandle.getColumns().get(4);
//		col.setProperty("width", "17%");
//		
//		row = (RowHandle) headerGridHandle.getRows().get(0);
//		gridCellHandle = (CellHandle) row.getCells().get(1);
//		gridCellHandle.getContent().add(rightGridHandle);
//		designHandle.getBody().add(headerGridHandle);
//	}
	
	
	private void buildBody() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spBody", 3, 1);
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildFirstColumn());	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		gridCellHandle.getContent().add(buildSecondColumn());	
		
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "49%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "1%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "49%");	

		designHandle.getBody().add(dataGridHandle);
	}
	
	private GridHandle buildFirstColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("firstColumn", 1, 3);
		
		
		/** build natural disaster 2.1 **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildNaturalDisasters());
		
		/** build food security situation 3 **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildFoodSecuritySituation());	
		
	
		/** setting color to grid **/
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		rowHeight.setProperty("height", "14.5cm");
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, red);
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
		rowHeight.setProperty("height", "11.6cm");	
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
		
		return dataGridHandle;		
	}
	
	private GridHandle buildSecondColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("secondColumn", 1, 7);
		
		/** BUILD ISSUES ON FOOD SECURITY (3.1) **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildIssuesFoodSecurity());
		

		/** BUILD GOVERNANCE (4) **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildGovernancePolicies());
		
		
//		/** BUILD CURRENT EVENTS **/
//		row = (RowHandle) dataGridHandle.getRows().get(4);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(buildCurrentEvents());
//		
//		/** BUILD CONTACTS **/
//		row = (RowHandle) dataGridHandle.getRows().get(6);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(buildContacts());
		
		/** BUILD CURRENT EVENTS AND CONTACTS **/
		row = (RowHandle) dataGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildCurrentEventsAndContacts());
	
		
		
		/** setting color to grid **/
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		rowHeight.setProperty("height", "14.5cm");
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
		rowHeight.setProperty("height", "7.4cm");	
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, orange);
		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(4);
//		rowHeight.setProperty("height", "2.5cm");	
//		rowHeight.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, grey);
		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(4);
//		rowHeight.setProperty("height", "4cm");	
		
		return dataGridHandle;	
	}
	
	
	private GridHandle buildNaturalDisasters() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spNaturalDisasters", 1, 6);
		
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spNaturalDisastersTitle", 2, 1);
		/** ADDING NATURAL DISASTER HEADER **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/** create the header **/		
		RowHandle headerRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle headerCellHandle = (CellHandle) headerRow.getCells().get(0);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("NDImage");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_blu.gif\"");
//		headerCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
		text.setHeight("0.6cm");
		text.setProperty(StyleHandle.COLOR_PROP, blue);
//		text.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
		text.setContent("<div style='font-weight:bold'> Drought and natural Disasters </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
		
		
		/** ADDING NATURAL DISASTER TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setContent("<div> " + ecBean.getNaturalBean().getNaturalDisastersText() + " </div>");
		text.setHeight(textSize);
		gridCellHandle.getContent().add(text);
		
		
		/** ADDING NATURAL DISASTER CHART **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle chart = designHandle.getElementFactory().newImage("chart1");
//		chart.setWidth("9.5cm");
//		chart.setHeight("4.5cm");
//		chart.setWidth("305px");
//		chart.setHeight("150px");
		chart.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersChart() + "\"");
		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.5cm");
		gridCellHandle.getContent().add(chart);
	

		
		/** ADDING NATURAL DISASTER MAP **/
		row = (RowHandle) dataGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle map = designHandle.getElementFactory().newImage("map");
		map.setWidth("9.5cm");
//		map.setHeight("4.5cm");
//		map.setWidth("330px");
//		map.setHeight("150px");
		map.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersMap() + "\"");
		gridCellHandle.getContent().add(map);
		
		/** Source  **/
		row = (RowHandle) dataGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>Source: <a href='http://mars.jrc.ec.europa.eu/mars/About-us/FOODSEC'>JRC  MARS – FoodSec</a></div>");
		gridCellHandle.getContent().add(text);
		
		
		
		return dataGridHandle;
	}
	
	private GridHandle buildFoodSecuritySituation() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spFoodSecuritySituation", 1,11);
		
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spFoodSecuritySituationTitle", 2, 1);
		/** ADDING FOOD SECURITY SITUATION HEADER (3) **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/** create the header **/		
		RowHandle headerRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle headerCellHandle = (CellHandle) headerRow.getCells().get(0);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("FSImage");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_rossa.gif\"");
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
		text.setHeight("0.6cm");
		text.setProperty(StyleHandle.COLOR_PROP, red);
		text.setContent("<div style='font-weight:bold'> Food security situation </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		CreateECSecondPageFoodSituation reportUtils = new CreateECSecondPageFoodSituation(ecBean, designHandle, designFactory);
		
		
		/** ADDING FOOD SECURITY SITUATION TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setContent("<div> "+ ecBean.getFoodSituationBean().getFoodText() + "</div>");
		gridCellHandle.getContent().add(text);
		
		/** ADDING Title **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, "6.5pt", "left");
		text.setContent("<div style='font-weight:bold; font-style: italic;'>  CURRENT EMERGENCY ASSESMENT </div>");
		gridCellHandle.getContent().add(text);
		
		/** ADDING FOOD SECURITY SITUATION FAO/GIEWS **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildFAOFoodSituation());
//		row = (RowHandle) dataGridHandle.getRows().get(4);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildFAOFoodSituationLegend());
		
		/** ADDING FOOD SECURITY SITUATION FEWSNET **/
		row = (RowHandle) dataGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildFEWSNETFoodSituation());
		
		/** ADDING Title **/
		row = (RowHandle) dataGridHandle.getRows().get(6);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, "6.5pt", "left");
		text.setContent("<div style='font-weight:bold; font-style: italic;'> SCALE OF HUNGER </div>");
		gridCellHandle.getContent().add(text);

		/** ADDING FOOD SECURITY SITUATION WFP **/
		row = (RowHandle) dataGridHandle.getRows().get(7);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildWFPFoodSituation());
//		row = (RowHandle) dataGridHandle.getRows().get(8);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildWFPFoodSituationLegend());
		
		/** ADDING FOOD SECURITY SITUATION IFPRI **/
		row = (RowHandle) dataGridHandle.getRows().get(9);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildIFPRIIFoodSituation());
//		row = (RowHandle) dataGridHandle.getRows().get(10);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildIFPRIFoodSituationLegend());
		
		return dataGridHandle;
	}
	
	private GridHandle buildIssuesFoodSecurity() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spIssuesFC", 1, 4);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** ADDING 3.1 ISSUES ON FOOD SECURITY HEADER **/
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spBalanceSheetTitle", 2, 1);
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/** create the header **/		
		RowHandle headerRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle headerCellHandle = (CellHandle) headerRow.getCells().get(0);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("BSImage");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_rossa.gif\"");
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
		text.setHeight("0.6cm");
		text.setProperty(StyleHandle.COLOR_PROP, red);
		text.setContent("<div style='font-weight:bold'> Food Balance Sheet </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
		
		/** ADDING 3.1 ISSUES ON FOOD SECURITY TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setHeight("2.42cm");
		text.setContent("<div> " + ecBean.getFoodIssuesBean().getFoodText() + " </div>");
		gridCellHandle.getContent().add(text);
		

		
		/** ADDING ISSUES ON FOOD CHART **/
		/** ADDING PRICES CHARTS **/
		if ( ecBean.getPriceBean().getChartFileName() != null) {
			row = (RowHandle) dataGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);	
			ImageHandle imageHandle = designHandle.getElementFactory().newImage("firstPriceChart");
			imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
			imageHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.7cm");
			imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ecCharts/" + ecBean.getFoodIssuesBean().getChartFileName() + "\"");
			gridCellHandle.getContent().add(imageHandle);
			
			/** ADDING CHART SOURCE* */
			row = (RowHandle) dataGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = designHandle.getElementFactory().newTextItem("");
			text = DesignUtils.createText(designHandle, text, "6pt", "right");
			text.setContent("<div>Source: <a href='http://www.fao.org/giews/english/index.htm'>GIEWS</a> - Global Information and Early Warning System</div>");	
			gridCellHandle.getContent().add(text);	
			
		}
		else {
			row = (RowHandle) dataGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, "12pt", "center");
			text.setContent("<div> CHART NOT AVAILABLE </div>");
			gridCellHandle.getContent().add(text);
		}
		
		
		
		return dataGridHandle;
	}
	
	private GridHandle buildGovernancePolicies() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spIGovernance", 1, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		CreateECSecondPageGovernance reportUtils = new CreateECSecondPageGovernance(ecBean, designHandle, designFactory);
		
		
		/** ADDING 4 GOVERNANCE POLICIES HEADER **/
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spGovernancePoliciesTitle", 2, 1);
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/** create the header **/		
		RowHandle headerRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle headerCellHandle = (CellHandle) headerRow.getCells().get(0);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("GPImage");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_gialla.gif\"");
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
		text.setHeight("0.6cm");
		text.setProperty(StyleHandle.COLOR_PROP, orange);
		text.setContent("<div style='font-weight:bold'> Governance Policies </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
		/** ADDING 4 ISSUES ON FOOD SECURITY TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text,  textFontSize, "justify");
//		text.setHeight("2cm");
		text.setContent("<div> "+ ecBean.getGovernanceBean().getGovernanceText() + "</div>");

		gridCellHandle.getContent().add(text);
		
		
		/** ADDING 4 ISSUES ON FOOD SECURITY TABLE **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildGovernance());
		

		
		return dataGridHandle;
	}
	
	private GridHandle buildCurrentEventsAndContacts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("currentEventsGrid", 2, 2);
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);

		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);	
		/** set image **/
		DesignUtils.setBorderGrid(gridCellHandle, blue);
	
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		/** set image **/
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		gridCellHandle.getContent().add(buildCurrentEvents());
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		gridCellHandle.getContent().add(buildContacts());
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		rowHeight.setProperty("height", "2.5cm");
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);

		return dataGridHandle;
	}
	
	private GridHandle buildCurrentEvents() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spCurrentEvents", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
//		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, grey);
		
		/** ADDING CURRENT EVENTS HEADER **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  textFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div style='font-weight:bold'> Current events to watch </div>");
		gridCellHandle.getContent().add(text);
		text = DesignUtils.createText(designHandle, text,  "6pt", "left");
		text.setProperty(StyleHandle.COLOR_PROP, "#000000");
		text.setContent("<div style='font-weight:bold'> (Click to see the full report) </div>");
		gridCellHandle.getContent().add(text);
	
		/** ADDING CURRENT EVENTS TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text,  tableFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, blue);
//		if ( !ecBean.getNews().isEmpty()) {
//			String news = "<ul> ";
//			for(String n : ecBean.getNews()) 
//				news += "<li>"+ n +"</li>";
//			news += "</ul> ";
//			text.setContent("<div>"+ news + "</div>");
//		}
//		else 
//			text.setContent("<div> No News Found </div>");
		
		if ( !ecBean.getNews().isEmpty()) {
			String news = "";
			for(String n : ecBean.getNews()) {
				news += n;
			}
//			news += "</ul> ";
			text.setContent("<div>"+ news + "</div>");
		}
		else 
			text.setContent("<div> No News Found </div>");		
		
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	private GridHandle buildContacts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spContacts", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
//		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkBlue);
		
		/** ADDING CONTACTS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  "7.5pt", "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkBlue);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkBlue);
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div style='font-weight:bold'; font-style: italic;'>For Further Informations Contact</div>");
		DesignUtils.setBorderGrid(gridCellHandle, darkBlue);
		
		text = DesignUtils.createText(designHandle, text,  "7.5pt", "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkBlue);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, darkBlue);
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div style='font-weight:bold'>For Further Informations Contact<br>Information-for-action@fao.org<br>Website: www.foodsec.org</div>");
		DesignUtils.setBorderGrid(gridCellHandle, darkBlue);
		gridCellHandle.getContent().add(text);
		
//		row = (RowHandle) dataGridHandle.getRows().get(1);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		text = DesignUtils.createText(designHandle, text,  "7.5pt", "left");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, w);
//		text.setProperty(StyleHandle.COLOR_PROP, white);
//		text.setContent("<div style='font-weight:bold'>Contacts: information-for-action@fao.org<br>Website: www.foodsec.org</div>");
//		DesignUtils.setBorderGrid(gridCellHandle, darkBlue);
//		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text,  "6pt", "right");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, white);
		text.setProperty(StyleHandle.COLOR_PROP, darkBlue);
		text.setContent("<div style='font-weight:bold'>Powered By the <a href='http://lprapp08.fao.org/fenix-portal/'><font color='#FFFFFF'>GIEWS Workstation</font></a></div>");
		gridCellHandle.getContent().add(text);		


		return dataGridHandle;
	}
	
	
	
}