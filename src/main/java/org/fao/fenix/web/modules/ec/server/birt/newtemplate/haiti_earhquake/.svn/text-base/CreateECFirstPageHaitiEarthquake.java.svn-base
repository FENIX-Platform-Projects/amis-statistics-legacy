package org.fao.fenix.web.modules.ec.server.birt.newtemplate.haiti_earhquake;

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


public class CreateECFirstPageHaitiEarthquake {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String greyBackground = "#e8e8e8";
	
//	String lightBrown = "#f1e3c8";
	String lightBrown = "#faedcf";
	
	String blue =  "#124c7d";
	
	String white = "#FFFFFF";
	
//	String tableBorder = "#000000";
	String tableBorder = "#FFFFFF";
//	String tableBorder = "#FF9933";
	
	String green = "#01641B";
	
	String grey = "#C9CBCB";
	
	String orange = "#FF9933";
	
	String red = "#CA1616";
	
	String yellowSoft = "#F8EDA5";
	
	String tableFontSize = "7.5pt";
	
//	String priceHeight = "17.6cm";
	
//	String socialEconomicHeight = "4cm";
	
//	String conflictsHeight = "12.9cm";
	
//	String backgroundHeight = "1cm";
	
	String headerFontSize = "10pt";
	
	String rightPaddingTableHeaders = "2.5cm";
	
	String topPaddingHeaders = "0.2cm";
	
	String textFontSize = "9.5pt";
	
	String sectionsFontSize = "9pt";
	
	public CreateECFirstPageHaitiEarthquake(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	public void build() throws SemanticException {
		buildHeaderFirstRow();
		buildHeaderSecondRow();
		buildKeyMessages();
		DesignUtils.addSpace(designHandle);
		buildBackground();
		buildSecondPart();
	}
	
	
	
	private void buildHeaderFirstRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderGrid", 9, 1);
		
		/** BLUE **/
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		row.setProperty(RowHandle.HEIGHT_PROP, "1.2cm");	
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		
		/** FAO LOGO **/
		
		gridCellHandle = (CellHandle) row.getCells().get(1);
		ImageHandle FAOLogoImage = designHandle.getElementFactory().newImage("FaoLogo");
		FAOLogoImage.setWidth("0.9cm");
		FAOLogoImage.setHeight("0.9cm");
//		FAOLogoImage.setWidth("1.1cm");
//		FAOLogoImage.setHeight("1.1cm");
		FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "fao.png\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.15cm");
		gridCellHandle.getContent().add(FAOLogoImage);
		
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(2);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);	
		
		/** EC LOGO **/
		gridCellHandle = (CellHandle) row.getCells().get(3);
		ImageHandle ECLogoImage = designHandle.getElementFactory().newImage("ECLogoImage");
		ECLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ImgForTemplate" + File.separator + "eu.png\"");
				+ File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		ECLogoImage.setWidth("1.2cm");
		ECLogoImage.setHeight("0.8cm");
//		ECLogoImage.setWidth("1.5cm");
//		ECLogoImage.setHeight("1.1cm");
		
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.15cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.1cm");
		gridCellHandle.getContent().add(ECLogoImage);
		/** add eu text **/ 
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "4.5pt", "right");
		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.15cm");
		text.setContent("<div style='color:" + white + ";'> European Union </div>");
		gridCellHandle.getContent().add(text);
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(4);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		
		/** MAP **/
		gridCellHandle = (CellHandle) row.getCells().get(5);
		ImageHandle MapImage = designHandle.getElementFactory().newImage("ECMapImage");
		MapImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "map_blue.png\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		MapImage.setWidth("1.5cm");
//		MapImage.setHeight("1.1cm");
		DesignUtils.setBorderGrid(gridCellHandle, blue);
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.getContent().add(MapImage);
		
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(6);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		
		
		/** TEXT **/
		gridCellHandle = (CellHandle) row.getCells().get(7);
		text = DesignUtils.createText(designHandle, text, "12pt", "right");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div>"+ ecBean.getLanguageMap().get("title") +"<br></div>");
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		DesignUtils.setBorderGrid(text, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.getContent().add(text);	
	
		
		
		text = DesignUtils.createText(designHandle, text,  "10pt", "right");
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div style='font-weight:bold'> www.foodsec.org </div>");
		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.05cm");
		gridCellHandle.getContent().add(text);	
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(8);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		
		
	
		
		
		ColumnHandle col = (ColumnHandle) headerGridHandle.getColumns().get(0);
		col.setProperty("width", "0.3%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(1);
		col.setProperty("width", "1.1cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(2);
		col.setProperty("width", "0.1cm");
		col = (ColumnHandle) headerGridHandle.getColumns().get(3);
		col.setProperty("width", "1.5cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(4);
		col.setProperty("width", "0.7cm");
		col = (ColumnHandle) headerGridHandle.getColumns().get(5);
		col.setProperty("width", "4cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(6);
		col.setProperty("width", "1cm");
		col = (ColumnHandle) headerGridHandle.getColumns().get(7);
		col.setProperty("width", "11cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(8);
		col.setProperty("width", "0.3%");
		 
		designHandle.getBody().add(headerGridHandle);
	}
	
	private void buildHeaderSecondRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderSRGrid", 2, 1);
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "13pt", "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#FFFFFF");
		text.setProperty(StyleHandle.COLOR_PROP, blue);
		text.setContent("<div style='font-weight:bold'> "+ ecBean.getLanguageMap().get("specialCountryBriefHaiti") +" </div>");
//		" + ecBean.getDate() + "
//		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	
		
//		row = (RowHandle) headerGridHandle.getRows().get(0);
//		gridCellHandle = (CellHandle) row.getCells().get(1);
//		text = DesignUtils.createText(designHandle, text, "14pt", "right");
//		text.setProperty(StyleHandle.COLOR_PROP, "#d54320");
//		text.setContent("<div style='font-weight:bold; text-decoration:underline;'><u>" + ecBean.getCountry() + "</u></div>");
//		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
//		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) headerGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text,"9pt", "right");
		text.setProperty(StyleHandle.COLOR_PROP, "#d54320");
		System.out.println("ecBean.getReferenceDate().toString()" + ecBean.getDate());
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get(ecBean.getDate()) +"</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	
		
//		row = (RowHandle) headerGridHandle.getRows().get(1);
//		gridCellHandle = (CellHandle) row.getCells().get(1);
//		text = DesignUtils.createText(designHandle, text,"7pt", "right");
//		text.setProperty(StyleHandle.COLOR_PROP, "#000000");
//		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
//		text.setContent("<div style='font-style: italic;'> January 2010 </div>");
//		gridCellHandle.getContent().add(text);	

		designHandle.getBody().add(headerGridHandle);
	}
	
	private void buildKeyMessages() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("fpKeyMessageGrid", 1, 2);
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "11pt", "left");	
		text.setContent("<div style='font-weight:bold; color:" + orange + ";'> "+ ecBean.getLanguageMap().get("keyMessages") + " </div>");
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);		
		gridCellHandle.getContent().add(buildKeyMessagesBody());		
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	private GridHandle buildKeyMessagesBody() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("issueDateGrid", 4, 4);	
		
		/** Section 1 Image **/	
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(1);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("section1img");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "little_blue_arrow.gif\"");
		gridCellHandle.getContent().add(headerImg);

		/** Section 1 txt **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");
		text.setContent("<div style='font-weight:bold;'>"+ ecBean.getKeyMessages().getSection1() + "</div>");
		gridCellHandle.getContent().add(text);		
		
		/** Section 2 Image **/	
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		headerImg = designHandle.getElementFactory().newImage("section2img");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "little_green_arrow.gif\"");
		gridCellHandle.getContent().add(headerImg);

		/** Section 2 txt **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");
		text.setContent("<div style='font-weight:bold;'>"+ ecBean.getKeyMessages().getSection2() + "</div>");
		gridCellHandle.getContent().add(text);
		
		/** Section 3 Image **/	
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		headerImg = designHandle.getElementFactory().newImage("section3img");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "little_red_arrow.gif\"");
		gridCellHandle.getContent().add(headerImg);

		/** Section 3 txt **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");
		text.setContent("<div style='font-weight:bold;'>"+ ecBean.getKeyMessages().getSection3() + "</div>");
		gridCellHandle.getContent().add(text);
		
		/** Section 4 Image **/	
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		headerImg = designHandle.getElementFactory().newImage("section4img");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "little_yellow_arrow.gif\"");
		gridCellHandle.getContent().add(headerImg);

		/** Section 4 txt **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");
		text.setContent("<div style='font-weight:bold;'>"+ ecBean.getKeyMessages().getSection4() + "</div>");
		gridCellHandle.getContent().add(text);
		
		
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "1%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "2%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "1%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "96%");	
		
		/** row heights **/
//		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
//		rowHeight.setProperty("height", "0.92cm");
//		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(1);
//		rowHeight.setProperty("height", "0.92cm");
//		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
//		rowHeight.setProperty("height", "0.92cm");	
//		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(3);
//		rowHeight.setProperty("height", "0.92cm");	
		
		
		return dataGridHandle;
	}
	
	

	private void buildBackground() throws SemanticException {
		GridHandle fakeGridHandle = designFactory.newGridItem("fakegrid", 1, 1);
		GridHandle dataGridHandle = designFactory.newGridItem("buildBackgound", 1, 2);	
//		dataGridHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		dataGridHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
//		dataGridHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP , "solid");
		dataGridHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP , "solid");	
//		dataGridHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
//		dataGridHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		dataGridHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"5px");
		dataGridHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"5px");
		dataGridHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, white);
		dataGridHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, white);
//		dataGridHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, white);
//		dataGridHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, white);
		
		/** build background **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,"11pt", "left");
		text.setContent("<div style='font-weight:bold; color:" + orange + ";'>  "+ ecBean.getLanguageMap().get("background") + " </div>");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
		gridCellHandle.getContent().add(text);
		
		
		
		/** IN Background TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
//		text = DesignUtils.createText(designHandle, text,"10pt", "left");
		text = DesignUtils.createText(designHandle, text,"9pt", "justify");
		text.setContent("<div> "+ ecBean.getBackgorundText() + "</div>");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
		gridCellHandle.getContent().add(text);
		
		
		row = (RowHandle) fakeGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(dataGridHandle);
		
				
		designHandle.getBody().add(fakeGridHandle);
	}
	
	
	
	private void buildSecondPart() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("fpSecondPart", 3, 1);	
//		dataGridHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
//		dataGridHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
//		dataGridHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, lightBrown);
		
		/** build the first column **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildFirstColumn());
		
		/** build the second column **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		gridCellHandle.getContent().add(buildSecondColumn());
		
		/** build the center color column **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		gridCellHandle.getContent().add(buildCenter());
		
		
	
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "49%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "1%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "49%");	
		designHandle.getBody().add(dataGridHandle);
	}
	
	private GridHandle buildCenter() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("centerColumn", 1, 2);	
	
//		/** social economic table **/
//		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
//		
//		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
//		rowHeight.setProperty("height", "4.5cm");
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, lightBrown);

		return dataGridHandle;
	}
	
	private GridHandle buildFirstColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("firstColumn", 1, 3);
		dataGridHandle.setProperty(StyleHandle.PADDING_TOP_PROP , "0.1cm");

		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(worldBankTable());
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
//		DesignUtils.setBorderGrid(gridCellHandle, lightBrown);
		
		
		/** conflict part **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildConflicts());
		
//		/** ADD PRICE **/
//		row = (RowHandle) dataGridHandle.getRows().get(2);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(buildPrice());
		
		
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(1);
		rowHeight.setProperty("height", "1.6cm");

		return dataGridHandle;
	}
	
	private GridHandle buildSecondColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("secondColumnGrid", 1, 2);
		
		/** social economic table **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
		gridCellHandle.getContent().add(createSocialEconomicTable());
		
//		/** conflict part **/
//		row = (RowHandle) dataGridHandle.getRows().get(2);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(buildConflicts());
		
		/** ADD PRICE **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildPrice());
		
		/** setting color to grid **/
//		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
//		rowHeight.setProperty("height", socialEconomicHeight);
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
////		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
//		DesignUtils.setBorderGrid(gridCellHandle, lightBrown);
		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(1);
//		rowHeight.setProperty("height", "0.5cm");	
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, red);

		return dataGridHandle;
	}
	
	
	private GridHandle buildPrice() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("priceGrid", 1, 2);	
		
//		/** Prices **/
//		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
//		text = DesignUtils.createText(designHandle, text, headerFontSize, "center");
//		text.setHeight("1cm");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, green);
//		text.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
//		text.setContent("<div style='font-weight:bold; color:#FFFFFF;'> 1. Prices </div>");
//		DesignUtils.setBorderGrid(gridCellHandle, green);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, green);
//		gridCellHandle.getContent().add(text);
		/** ADDING 4 PRICES HEADER **/
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spPricesTitle", 2, 1);
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/** create the header **/		
		RowHandle headerRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle headerCellHandle = (CellHandle) headerRow.getCells().get(0);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("PricesImage");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_verde.gif\"");
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
		text.setHeight("0.4cm");
//		DesignUtils.setBorderGrid(text, green);
		text.setProperty(StyleHandle.COLOR_PROP, green);
		text.setContent("<div style='font-weight:bold'> "+ ecBean.getLanguageMap().get("foodSituation") + " </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
	
		
		/** IN CONTENT PRICES **/
		/** FIRST PRICE TEXT **/
		GridHandle pricesData = designFactory.newGridItem("fpTPPricesData", 1, 3);	
		row = (RowHandle) pricesData.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setContent("<div> "+ ecBean.getPriceBean().getPriceText() + "</div>");
		gridCellHandle.getContent().add(text);
		
		/** ADDING PRICES CHARTS **/
//		row = (RowHandle) pricesData.getRows().get(2);
//		gridCellHandle = (CellHandle) row.getCells().get(0);	
//		ImageHandle chartValue = designHandle.getElementFactory().newImage("valuesChart");
//		chartValue.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
//		chartValue.setProperty(StyleHandle.PADDING_LEFT_PROP, "1.1cm");
//		chartValue.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ec/" + ecBean.getPriceBean().getChartValues() + "\"");
//		chartValue.setWidth("100%");
////		imageHandle.setHeight("10.5cm");
//		gridCellHandle.getContent().add(chartValue);
			
		row = (RowHandle) pricesData.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		ImageHandle chartVariations = designHandle.getElementFactory().newImage("variationChart");
		chartVariations.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
//		chartVariations.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.1cm");
		chartVariations.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + ecBean.getCountryCode() + File.separator + ecBean.getPriceBean().getChartVariations() + "\"");
		chartVariations.setWidth("100%");
		gridCellHandle.getContent().add(chartVariations);
		
		/** ADDING CHART SOURCE* */
		row = (RowHandle) pricesData.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
//		text.setProperty(StyleHandle.PADDING_LEFT_PROP, "1.1cm");
//		text.setContent("<div>Source: <a href='http://www.fao.org/giews/english/index.htm'>GIEWS</a> - Global Information and Early Warning System</div>");	
		text.setContent("<div>"+ ecBean.getLanguageMap().get("source") + " : <a href='http://www.cnsahaiti.org/'>Coordination Nationale de la Securte Alimentaire (CNSA)</a></div>");
		gridCellHandle.getContent().add(text);
		
		
		/** setting spaces **/
//		RowHandle rowHeight = (RowHandle) pricesData.getRows().get(0);
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		rowHeight.setProperty("height", "4.7cm");

		
		
		/** ADDING THE GRID TO THE BASIC CELL **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(pricesData);
		return dataGridHandle;
	}
	
	private GridHandle buildConflicts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("conflictsPart", 1, 4);
		
		/** CONFLICTS **/
//		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
//		text = DesignUtils.createText(designHandle, text, headerFontSize, "center");
//		text.setHeight("1cm");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
//		text.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
//		text.setContent("<div style='font-weight:bold; color:#ffffff;'>  2. Conflicts, Refugees and... </div>");
//		DesignUtils.setBorderGrid(gridCellHandle, red);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
//		gridCellHandle.getContent().add(text);
		
		/** ADDING  conflicts HEADER **/
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spConflictsTitle", 2, 1);
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/** create the header **/		
		RowHandle headerRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle headerCellHandle = (CellHandle) headerRow.getCells().get(0);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("conflictsImage");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_blue.gif\"");
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
//		text.setHeight("0.6cm");
		text.setProperty(StyleHandle.COLOR_PROP, blue);
//		text.setContent("<div style='font-weight:bold'> Conflicts and Refugees </div>");
		text.setContent("<div style='font-weight:bold'> "+ ecBean.getLanguageMap().get("emergencySituation") + " </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		/** CONFLICTS TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setHeight("3cm");
		text.setContent("<div> "+ ecBean.getConflictsBean().getConflictsText() + "</div>");
		gridCellHandle.getContent().add(text);
		
		/** CONFLICTS TABLES **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		gridCellHandle.getContent().add(createConflictsTables());	
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
		rowHeight.setProperty("height", "0.4cm");
		
		dataGridHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	
		return dataGridHandle;
	}
	
	
	private GridHandle createSocialEconomicTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("socialEconomicTable", 1, 2);	
//		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);

		
		/** ADD WB TABLE TO GRID **/
//		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(worldBankTable());
		
		/** ADD food consumption TABLE TO GRID **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(foodConsumptionTable());
		
		/** ADD health indicators TO GRID **/
//		row = (RowHandle) dataGridHandle.getRows().get(1);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(healthIndicatorsTable());
//		
		/** ADD NEW HARDCORED GRID **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildTopFiveCultures());

		return dataGridHandle;
	}
	
	
	private GridHandle worldBankTable() throws SemanticException {
		/** (1) Economic Indicators TABLE (WB) **/
		GridHandle tableGridHandle = designFactory.newGridItem("WBGrid", 2, 7);	
		GridHandle fakeGridHandle = designFactory.newGridItem("fakegrid", 1, 1);
		tableGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
//		DesignUtils.setBorderGrid(tableGridHandle, lightBrown);
		
//		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** (1) Economic Indicators HEADER (WB) **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		text.setContent("<div style='font-weight:bold; color:"+ orange + ";'> "+ ecBean.getLanguageMap().get("economicIndicators") + " </div>");
		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, rightPaddingTableHeaders);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
	
		/** TODO that's the View Data **/
//		row = (RowHandle) tableGridHandle.getRows().get(0);
//		gridCellHandle = (CellHandle) row.getCells().get(1);
//		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
//		if ( ecBean.getSocialEconomicBean().getDatasetId() != null) {
//			/** TODO REMOVE HARDCODED DATASET ID ONCE IS DEPLOYED **/
//			text.setContent("<div><a href='http://lprapp08.fao.org:8080/fenix-web/fenix/Fenix.html?openProjectDataset="+ ecBean.getSocialEconomicBean().getDatasetId() + "'>View Data</a></div>");
//			text.setContent("<div><a href='http://lprapp08.fao.org:8080/fenix-web/fenix/Fenix.html?openProjectDataset=285325013'>View Data</a></div>");
//		}
//		DesignUtils.setRightBorderGrid(gridCellHandle, tableBorder);		
//		gridCellHandle.getContent().add(text);
		
		
		/** Total Population **/
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWB1().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("totalPopulation") + " - " + ecBean.getSocialEconomicBean().getWB1().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB1().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("totalPopulation") + " </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
	
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getSocialEconomicBean().getWB1().getValue() +" </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Population Growth rate**/
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWB2().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("populationGrowthRate") + " - " + ecBean.getSocialEconomicBean().getWB2().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB2().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("populationGrowthRate") + " </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getWB2().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB2().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB2().getValue() + "</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		/** THIRD ROW IS GDP IF EXIST OF GNI IF GDP DOESN'T EXIST per capita **/
		if ( ecBean.getSocialEconomicBean().getGDP() != null) {
			row = (RowHandle) tableGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
			if ( !ecBean.getSocialEconomicBean().getGDP().getDate().equals("n.a.") )
				text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("gdpPerCapita") + " - " + ecBean.getSocialEconomicBean().getGDP().getDate() + " ("+ ecBean.getSocialEconomicBean().getGDP().getSource() +") </div>");
			else
				text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("gdpPerCapita") + " </div>");
			DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
			gridCellHandle.getContent().add(text);
			
			row = (RowHandle) tableGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(1);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getGDP().getValue() + " </div>");
			DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
			gridCellHandle.getContent().add(text);
		}
		else {
			row = (RowHandle) tableGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
			if ( !ecBean.getSocialEconomicBean().getWB3().getDate().equals("n.a.") )
				text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("gniPerCapita") + " - " + ecBean.getSocialEconomicBean().getWB3().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB3().getSource() +") </div>");
			else
				text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("gniPerCapita") + " </div>");
			DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
			gridCellHandle.getContent().add(text);
			
			row = (RowHandle) tableGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(1);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB3().getValue() + " </div>");
			DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
			gridCellHandle.getContent().add(text);
		}
		
		/** Population below 1$ PPP**/
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getMDGI1().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("populationBelow1D") + " - " + ecBean.getSocialEconomicBean().getMDGI1().getDate() + " ("+ ecBean.getSocialEconomicBean().getMDGI1().getSource() +") </div>");
		else 
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("populationBelow1D") + " </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getMDGI1().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getMDGI1().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getMDGI1().getValue() + "</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Rural population **/
		row = (RowHandle) tableGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWB4().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("ruralPopulation") + " - " + ecBean.getSocialEconomicBean().getWB4().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB4().getSource() +") </div>");
		else 
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("ruralPopulation") +"</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB4().getValue() + "% </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Agriculture, value added % GDP **/
		row = (RowHandle) tableGridHandle.getRows().get(6);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWB5().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("agricultureValueAdded") +" - " + ecBean.getSocialEconomicBean().getWB5().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB5().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("agricultureValueAdded") +" </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(6);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getWB5().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB5().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB5().getValue() + "</div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);	
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "16%");
		
		/** adding to a fake grid, just for the margin STUPID BIRT */
		row = (RowHandle) fakeGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(tableGridHandle);	
		return fakeGridHandle;
	}
	
	private GridHandle foodConsumptionTable() throws SemanticException {
		/** (2) foodConsumptionTable **/
		GridHandle tableGridHandle = designFactory.newGridItem("FCtable", 2, 4);	
		tableGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
//		DesignUtils.setBorderGrid(tableGridHandle, lightBrown);
//		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** (2) foodConsumptionTable HEADER  **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		text.setContent("<div style='font-weight:bold; color:"+ orange + ";'> "+ ecBean.getLanguageMap().get("foodConsuption") +" </div>");
		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, rightPaddingTableHeaders);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableBorder);	
		gridCellHandle.getContent().add(text);
	
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		DesignUtils.setRightBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		/** Undernourished  Population **/
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getFAO1().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("undernourishedPopulation") +" - " + ecBean.getSocialEconomicBean().getFAO1().getDate() + " ("+ ecBean.getSocialEconomicBean().getFAO1().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("undernourishedPopulation") +"n </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
	
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getFAO1().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO1().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO1().getValue() + "</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Cereal share in total dietary... **/	
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getFAO2().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'>  "+ ecBean.getLanguageMap().get("cerealShare") +" - " + ecBean.getSocialEconomicBean().getFAO2().getDate() + " ("+ ecBean.getSocialEconomicBean().getFAO2().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'>  "+ ecBean.getLanguageMap().get("cerealShare") +" </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getFAO2().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO2().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO2().getValue() + "</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Meat share in total dietary.. **/
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getFAO3().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'>  "+ ecBean.getLanguageMap().get("meatShare") +" - " + ecBean.getSocialEconomicBean().getFAO3().getDate() + " ("+ ecBean.getSocialEconomicBean().getFAO3().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'>  "+ ecBean.getLanguageMap().get("meatShare") +" </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getFAO3().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO3().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO3().getValue() + "</div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "84%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "16%");
		
		return tableGridHandle;
	}
	
	private GridHandle healthIndicatorsTable() throws SemanticException {
		/** (3) healthIndicators **/
		GridHandle tableGridHandle = designFactory.newGridItem("HItable", 2, 5);
		tableGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
//		DesignUtils.setBorderGrid(tableGridHandle, lightBrown);
//		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** (3) healthIndicators HEADER  **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		text.setContent("<div style='font-weight:bold; color:"+ orange + ";'> Health Indicators </div>");
//		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, rightPaddingTableHeaders);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableBorder);	
		gridCellHandle.getContent().add(text);
	
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		DesignUtils.setRightBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		/** Population with sustainable access to.. **/
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWHO1().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> Pop. with sustainable access to improved sanitation - " + ecBean.getSocialEconomicBean().getWHO1().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO1().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'> Pop. with sustainable access to improved sanitation</div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
	
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getWHO1().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO1().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO1().getValue() + "</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Life expectancy at birth (years) **/	
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWHO2().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> Life expectancy at birth (years) both sexes - " + ecBean.getSocialEconomicBean().getWHO2().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO2().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'> Life expectancy at birth (years) both sexes  </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO2().getValue() + " </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Pop. with sustainable.. **/
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWHO3().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'>  Pop. with access to improved drinking water sources - " + ecBean.getSocialEconomicBean().getWHO3().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO3().getSource() +") </div>");
		else 
			text.setContent("<div style='font-style: italic;'>  Pop. with access to improved drinking water sources </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getWHO3().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO3().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO3().getValue() + "</div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Prevalence of HIV among adults aged >= 15 years **/
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		if ( !ecBean.getSocialEconomicBean().getWHO4().getDate().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> Prevalence of HIV among adults aged >= 15 years - " + ecBean.getSocialEconomicBean().getWHO4().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO4().getSource() +") </div>");
		else
			text.setContent("<div style='font-style: italic;'> Prevalence of HIV among adults aged >= 15 years </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getSocialEconomicBean().getWHO4().getValue().equals("n.a.") )
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO4().getValue() + "% </div>");
		else
			text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO4().getValue() + "</div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "84%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "16%");
		return tableGridHandle;
	}

	
	private GridHandle createConflictsTables() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("conflictsTables", 1, 4);	

		//* add map **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(1);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle chartImg = designHandle.getElementFactory().newImage("chartConflictImage");
//		chartImg.setWidth("9.5cm");
		chartImg.setWidth("100%");
		System.out.println("ecBean.getConflictsBean().getChart(): " + ecBean.getConflictsBean().getChart());
		chartImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ec" + File.separator + ecBean.getCountryCode() + File.separator + ecBean.getConflictsBean().getChart() + "\"");
		gridCellHandle.getContent().add(chartImg);
		
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
//		text.setProperty(StyleHandle.PADDING_LEFT_PROP, "1.1cm");
//		text.setContent("<div>Source: <a href='http://www.fao.org/giews/english/index.htm'>GIEWS</a> - Global Information and Early Warning System</div>");	
		text.setContent("<div>"+ ecBean.getLanguageMap().get("source") +": <a href='http://ochaonline.un.org/'>UN Office for the Coordination of Humanitarian Affairs</a></div>");
		gridCellHandle.getContent().add(text);
		
		/** ADD conflicts TABLE TO GRID **/
//		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildConflictsTable());
//		
//		row = (RowHandle) dataGridHandle.getRows().get(1);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildConflictsSource());	
		
		/** ADD refugees TABLE TO GRID **/
//		RowHandle row = (RowHandle) dataGridHandle.getRows().get(3);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//	
//		
//		row = (RowHandle) dataGridHandle.getRows().get(4);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildRefugeesSource());
//		
//		/** ADD disasters TABLE TO GRID **/
//		row = (RowHandle) dataGridHandle.getRows().get(6);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//
//		
//		row = (RowHandle) dataGridHandle.getRows().get(7);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildDisastersSource());
//		

//		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
//		rowHeight.setProperty("height", "0.3cm");
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		rowHeight.setProperty("height", "0.5cm");
	
		return dataGridHandle;
	}
	

	private GridHandle buildTopFiveCultures() throws SemanticException {
		GridHandle allTableGridHandle = designFactory.newGridItem("topFiveHeader", 1, 2);
//		allTableGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
		
		/**  HEADER  **/
		RowHandle row = (RowHandle) allTableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-weight:bold; color:"+ orange + ";'>  "+ ecBean.getLanguageMap().get("topFiveCropProductions") +" - ("+ ecBean.getLanguageMap().get("metricTonnes") +") </div>");
//		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, rightPaddingTableHeaders);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableBorder);	
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);	
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);	
		gridCellHandle.getContent().add(text);
		

		
		/** values **/
		GridHandle tableGridHandle = designFactory.newGridItem("topFiveCultures", 5, 5);
		tableGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, lightBrown);
		
		row = (RowHandle) allTableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(tableGridHandle);

		
		
	
	
		
		
		/** FIRST ROWWW **/
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div> <b> "+ ecBean.getLanguageMap().get("crop") +" </b></div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div> <b> 2004 </b></div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div> <b> 2005 </b> </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div> <b> 2006 </b> </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div> <b> 2007 </b> </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
	
		
		/** SECOND ROW **/	
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("sugarCane") +" </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 1,050,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 1050000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 1,075,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 1,340,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		
		/** Row three **/
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("cassava") +" </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 340,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 400,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 400,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 450,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		
		
		/** Row four **/
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("bananas") +" </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 300,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 300,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 280,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 295,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		
		
		/** Row five **/
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getLanguageMap().get("maize") +" </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 198,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 200,500 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 205,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> 270,000 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
//		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
//		col.setProperty("width", "84%");
//		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
//		col.setProperty("width", "16%");
		return allTableGridHandle;
	}
	
	
}