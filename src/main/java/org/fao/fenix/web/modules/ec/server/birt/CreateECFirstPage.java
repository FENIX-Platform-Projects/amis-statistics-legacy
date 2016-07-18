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


public class CreateECFirstPage {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String greyBackground = "#e8e8e8";
	
	String blue =  "#124c7d";
	
	String white = "#FFFFFF";
	
	String tableBorder = "#000000";
	
	String green = "#01641B";
	
	String grey = "#C9CBCB";
	
	String orange = "#FF9933";
	
	String red = "#CA1616";
	
	String yellowSoft = "#F8EDA5";
	
	String tableFontSize = "7.5pt";
	
	String priceHeight = "17.6cm";
	
	String socialEconomicHeight = "5cm";
	
	String conflictsHeight = "12.9cm";
	
	String backgroundHeight = "2.2cm";
	
	String headerFontSize = "13pt";
	
	String rightPaddingTableHeaders = "2.5cm";
	
	String topPaddingHeaders = "0.2cm";
	
	String textFontSize = "9.5pt";
	
	String sectionsFontSize = "10pt";
	
	public CreateECFirstPage(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	public void build() throws SemanticException {
		buildHeaderFirstRow();
		buildHeaderSecondRow();
		DesignUtils.addSpace(designHandle);
		buildKeyMessages();
		DesignUtils.addSpace(designHandle);
		buildSecondPart();
	}
	
	private void buildHeaderFirstRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderGrid", 9, 1);
//		headerGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
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
//				+ File.separator + "ImgForTemplate" + File.separator + "eu.gif\"");
				+ File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		ECLogoImage.setWidth("1.5cm");
		ECLogoImage.setHeight("1.1cm");
		
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
		
		/** MAP **/
		gridCellHandle = (CellHandle) row.getCells().get(5);
		ImageHandle MapImage = designHandle.getElementFactory().newImage("ECMapImage");
		MapImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "map.gif\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
//		MapImage.setWidth("1.5cm");
//		MapImage.setHeight("1.1cm");
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.getContent().add(MapImage);
		
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(6);
		text = designHandle.getElementFactory().newTextItem("");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		DesignUtils.setBorderGrid(text, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.getContent().add(text);	
		
		
		/** TEXT **/
		gridCellHandle = (CellHandle) row.getCells().get(7);
		text = DesignUtils.createText(designHandle, text, "12pt", "right");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div>Food Security Information for Decision Making <br></div>");
		DesignUtils.setBorderGrid(gridCellHandle, blue);
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		DesignUtils.setBorderGrid(text, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.setProperty(StyleHandle.BACKGROUND_IMAGE_PROP, "\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
//		gridCellHandle.setProperty("backgroundImage", "\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
		gridCellHandle.getContent().add(text);	
		

		
		
		
		text = DesignUtils.createText(designHandle, text,  "10pt", "right");
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div style='font-weight:bold'> www.foodsec.org </div>");
		gridCellHandle.getContent().add(text);	
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(8);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		DesignUtils.setBorderGrid(text, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.getContent().add(text);
		
		
	
		
		
		ColumnHandle col = (ColumnHandle) headerGridHandle.getColumns().get(0);
		col.setProperty("width", "1%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(1);
		col.setProperty("width", "10%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(2);
		col.setProperty("width", "1%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(3);
		col.setProperty("width", "10%");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(4);
		col.setProperty("width", "1%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(5);
		col.setProperty("width", "25%");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(6);
		col.setProperty("width", "1%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(7);
		col.setProperty("width", "50%");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(8);
		col.setProperty("width", "1%");
		 
		designHandle.getBody().add(headerGridHandle);
	}
	
	private void buildHeaderSecondRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderSRGrid", 2, 2);
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		row.setProperty(RowHandle.HEIGHT_PROP, "1.5cm");
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "13pt", "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#FFFFFF");
		text.setProperty(StyleHandle.COLOR_PROP, blue);
		text.setContent("<div style='font-weight:bold'>Price Monitoring and Analysis Country Brief </div>");
//		" + ecBean.getDate() + "
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) headerGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, "13pt", "right");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		text.setProperty(StyleHandle.COLOR_PROP, orange);
		text.setContent("<div style='font-weight:bold; text-decoration:underline;'>" + ecBean.getCountry() + "</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		DesignUtils.setBorderGrid(text, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.3cm");
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) headerGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text,"9pt", "right");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		text.setProperty(StyleHandle.COLOR_PROP, orange);
		text.setContent("<div>" + ecBean.getDate() + "</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		DesignUtils.setBorderGrid(gridCellHandle, blue);
		DesignUtils.setBorderGrid(text, blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.3cm");
		gridCellHandle.getContent().add(text);	

		designHandle.getBody().add(headerGridHandle);
	}
	
	private void buildKeyMessages() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("fpKeyMessageGrid", 1, 2);
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, yellowSoft);	
				
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "11pt", "left");	
		text.setContent("<div style='font-weight:bold; color:" + orange + ";'> Key Messages </div>");
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);		
		gridCellHandle.getContent().add(buildKeyMessagesBody());		
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	private GridHandle buildKeyMessagesBody() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("issueDateGrid", 3, 4);	
		
		/** Section 1 TEXT **/	
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");

		
		text.setContent("<div style='font-weight:bold;'>  <ul> <li> "+ ecBean.getKeyMessages().getSection1() + " </li> </ul> </div>");
		gridCellHandle.getContent().add(text);		
		
		/** Section 1 "IMAGE" **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, green);
		text.setContent("<div style='font-weight:bold; color:"+ white + ";'> section 1 </div>");
		gridCellHandle.getContent().add(text);	
		
		/** Section 2 TEXT **/	
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");
		text.setContent("<div style='font-weight:bold;'> <ul> <li> "+ ecBean.getKeyMessages().getSection2() + "  </li> </ul> </div>");
		gridCellHandle.getContent().add(text);		
		
		/** Section 2 "IMAGE" **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
		text.setContent("<div style='font-weight:bold; color:"+ white + ";'> section 2 </div>");
		gridCellHandle.getContent().add(text);	
		
		
		/** Section 3 TEXT **/	
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");
		text.setContent("<div style='font-weight:bold;'> <ul> <li> "+ ecBean.getKeyMessages().getSection3() + " </li> </ul>  </div>");
		gridCellHandle.getContent().add(text);		
		
		/** Section 3 "IMAGE" **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, "9pt", "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, blue);
		text.setContent("<div style='font-weight:bold; color:"+ white + ";'> section 3 </div>");
		gridCellHandle.getContent().add(text);	
		
		/** Section 3 TEXT **/	
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "left");
		text.setContent("<div style='font-weight:bold;'> <ul> <li> "+ ecBean.getKeyMessages().getSection4() + "  </li> </ul> </div>");
		gridCellHandle.getContent().add(text);		
		
		/** Section 3 "IMAGE" **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, sectionsFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text.setContent("<div style='font-weight:bold; color:"+ white + ";'> section 4 </div>");
		gridCellHandle.getContent().add(text);
		
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "80%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "15%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "5%");	
		
		/** row heights **/
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		rowHeight.setProperty("height", "0.92cm");
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(1);
		rowHeight.setProperty("height", "0.92cm");
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
		rowHeight.setProperty("height", "0.92cm");	
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(3);
		rowHeight.setProperty("height", "0.92cm");	
		
		
		return dataGridHandle;
	}
	
	private GridHandle buildFirstColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("firstColumn", 1, 4);
		
		/** IN backGroundDATA HEADER **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,"11pt", "left");
		text.setContent("<div style='font-weight:bold; color:" + orange + ";'> Background </div>");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, greyBackground);
		gridCellHandle.getContent().add(text);
		
		
		
		/** IN Background TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
//		text = DesignUtils.createText(designHandle, text,"10pt", "left");
		text = DesignUtils.createText(designHandle, text,"10pt", "justify");
		text.setHeight(backgroundHeight);
		text.setContent("<div> "+ ecBean.getBackgorundText() + "</div>");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, greyBackground);
		gridCellHandle.getContent().add(text);
		
		
		/** ADD PRICE **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildPrice());
		
		
		
		/** setting color to grid **/
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(3);
		rowHeight.setProperty("height", priceHeight);
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
		DesignUtils.setBorderGrid(gridCellHandle, green);
		
		
		return dataGridHandle;
	}

	
	private void buildSecondPart() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("fpSecondPart", 3, 1);	
		
		/** build the first column **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildFirstColumn());
		
		/** build the second column **/
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
	
	private GridHandle buildSecondColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("secondColumnGrid", 1, 3);
		
		/** social economic table **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, greyBackground);
		gridCellHandle.getContent().add(createSocialEconomicTable());
		
		/** conflict part **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildConflicts());
		
		/** setting color to grid **/
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		rowHeight.setProperty("height", socialEconomicHeight);
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
		rowHeight.setProperty("height", conflictsHeight);	
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
		DesignUtils.setBorderGrid(gridCellHandle, red);

		return dataGridHandle;
	}
	
	
	private GridHandle buildPrice() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("priceGrid", 1, 2);	
		
		/** Prices **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "center");
		text.setHeight("1cm");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, green);
		text.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
		text.setContent("<div style='font-weight:bold; color:#FFFFFF;'> 1. Prices </div>");
		DesignUtils.setBorderGrid(gridCellHandle, green);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, green);
		gridCellHandle.getContent().add(text);
		
		
	
		
		/** IN CONTENT PRICES **/
		/** FIRST PRICE TEXT **/
		GridHandle pricesData = designFactory.newGridItem("fpTPPricesData", 1, 4);	
		row = (RowHandle) pricesData.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setContent("<div> "+ ecBean.getPriceBean().getPriceText() + "</div>");
		gridCellHandle.getContent().add(text);
		
		/** ADDING PRICES CHARTS **/
		if ( ecBean.getPriceBean().getChartFileName() != null) {
			row = (RowHandle) pricesData.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);	
			ImageHandle imageHandle = designHandle.getElementFactory().newImage("firstPriceChart");
			imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
			imageHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.7cm");
			imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ecCharts/" + ecBean.getPriceBean().getChartFileName() + "\"");
			gridCellHandle.getContent().add(imageHandle);
			
			/** ADDING CHART SOURCE* */
			row = (RowHandle) pricesData.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = designHandle.getElementFactory().newTextItem("");
			text = DesignUtils.createText(designHandle, text, "6pt", "right");
			text.setContent("<div>Source: <a href='http://www.fao.org/giews/english/index.htm'>GIEWS</a> - Global Information and Early Warning System</div>");	
			gridCellHandle.getContent().add(text);
		}
		else {
			row = (RowHandle) pricesData.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, "12pt", "center");
			text.setContent("<div> CHART NOT AVAILABLE </div>");
			gridCellHandle.getContent().add(text);
		}
		
		/** setting spaces **/
		RowHandle rowHeight = (RowHandle) pricesData.getRows().get(0);
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
		rowHeight.setProperty("height", "4.7cm");

		
		
		/** ADDING THE GRID TO THE BASIC CELL **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(pricesData);
		return dataGridHandle;
	}
	
	private GridHandle buildConflicts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("conflictsPart", 1, 3);
		
		/** CONFLICTS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "center");
		text.setHeight("1cm");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
		text.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
		text.setContent("<div style='font-weight:bold; color:#ffffff;'>  2. Conflicts, Refugees and... </div>");
		DesignUtils.setBorderGrid(gridCellHandle, red);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, red);
		gridCellHandle.getContent().add(text);
		
		/** CONFLICTS TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setHeight("3cm");
		text.setContent("<div> "+ ecBean.getConflictsBean().getConflictsText() + "</div>");
		gridCellHandle.getContent().add(text);
		
		/** CONFLICTS TABLES **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		gridCellHandle.getContent().add(createConflictsTables());	
		
		dataGridHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	
		return dataGridHandle;
	}
	
	
	private GridHandle createSocialEconomicTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("socialEconomicTable", 1, 3);	
		
		/** ADD WB TABLE TO GRID **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(worldBankTable());
		
		/** ADD food consumption TABLE TO GRID **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(foodConsumptionTable());
		
		/** ADD health indicators TO GRID **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(healthIndicatorsTable());

		return dataGridHandle;
	}
	
	
	private GridHandle worldBankTable() throws SemanticException {
		/** (1) Economic Indicators TABLE (WB) **/
		GridHandle tableGridHandle = designFactory.newGridItem("WBGrid", 2, 7);	
//		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** (1) Economic Indicators HEADER (WB) **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		text.setContent("<div style='font-weight:bold; color:"+ orange + ";'> Economic Indicators </div>");
		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, rightPaddingTableHeaders);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
	
	
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		if ( ecBean.getSocialEconomicBean().getDatasetId() != null) 
			text.setContent("<div><a href='http://lprapp08.fao.org:8080/fenix-web/fenix/Fenix.html?openProjectDataset="+ ecBean.getSocialEconomicBean().getDatasetId() + "'>View Data</a></div>");
		DesignUtils.setRightBorderGrid(gridCellHandle, tableBorder);		
		gridCellHandle.getContent().add(text);
		
		
		/** Total Population **/
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Total Population - " + ecBean.getSocialEconomicBean().getWB1().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB1().getSource() +") </div>");
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
		text.setContent("<div style='font-style: italic;'> Population growth rate - " + ecBean.getSocialEconomicBean().getWB2().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB2().getSource() +") </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB2().getValue() + "% </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		/** THIRD ROW IS GDP IF EXIST OF GNI IF GDP DOESN'T EXIST per capita **/
		if ( ecBean.getSocialEconomicBean().getGDP() != null) {
			row = (RowHandle) tableGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
			text.setContent("<div style='font-style: italic;'> GDP per capita, $ PPP - " + ecBean.getSocialEconomicBean().getGDP().getDate() + " ("+ ecBean.getSocialEconomicBean().getGDP().getSource() +") </div>");
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
			text.setContent("<div style='font-style: italic;'> GNI per capita, $ PPP - " + ecBean.getSocialEconomicBean().getWB3().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB3().getSource() +") </div>");
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
		text.setContent("<div style='font-style: italic;'> Population below 1$ PPP per day - " + ecBean.getSocialEconomicBean().getMDGI1().getDate() + " ("+ ecBean.getSocialEconomicBean().getMDGI1().getSource() +") </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getMDGI1().getValue() + "% </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Rural population **/
		row = (RowHandle) tableGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Rural population - " + ecBean.getSocialEconomicBean().getWB4().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB4().getSource() +") </div>");
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
		text.setContent("<div style='font-style: italic;'> Agriculture, value added (% of GDP) - " + ecBean.getSocialEconomicBean().getWB5().getDate() + " ("+ ecBean.getSocialEconomicBean().getWB5().getSource() +") </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(6);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWB5().getValue() + "% </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);	
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "84%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "16%");
		return tableGridHandle;
	}
	
	private GridHandle foodConsumptionTable() throws SemanticException {
		/** (2) foodConsumptionTable **/
		GridHandle tableGridHandle = designFactory.newGridItem("FCtable", 2, 4);	
//		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** (2) foodConsumptionTable HEADER  **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		text.setContent("<div style='font-weight:bold; color:"+ orange + ";'> Food Consumption </div>");
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
		text.setContent("<div style='font-style: italic;'> Undernourished Population - " + ecBean.getSocialEconomicBean().getFAO1().getDate() + " ("+ ecBean.getSocialEconomicBean().getFAO1().getSource() +") </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
	
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO1().getValue() + "% </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Cereal share in total dietary... **/	
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Cereal share in total dietary energy consuption - " + ecBean.getSocialEconomicBean().getFAO2().getDate() + " ("+ ecBean.getSocialEconomicBean().getFAO2().getSource() +") </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO2().getValue() + "% </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Meat share in total dietary.. **/
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Meat share in total dietary energy consuption - " + ecBean.getSocialEconomicBean().getFAO3().getDate() + " ("+ ecBean.getSocialEconomicBean().getFAO3().getSource() +") </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getFAO3().getValue() + "% </div>");
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
//		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** (3) healthIndicators HEADER  **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "right");
		text.setContent("<div style='font-weight:bold; color:"+ orange + ";'> Health Indicators </div>");
		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, rightPaddingTableHeaders);
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
		text.setContent("<div style='font-style: italic;'> Pop. with sustainable access to improved sanitation - " + ecBean.getSocialEconomicBean().getWHO1().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO1().getSource() +") </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
	
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO1().getValue() + "% </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Life expectancy at birth (years) **/	
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Life expectancy at birth (years) both sexes - " + ecBean.getSocialEconomicBean().getWHO2().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO2().getSource() +") </div>");
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
		text.setContent("<div style='font-style: italic;'>  Pop. with access to improved drinking water sources - " + ecBean.getSocialEconomicBean().getWHO3().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO3().getSource() +") </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO3().getValue() + "% </div>");
		DesignUtils.setTopBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		/** Prevalence of HIV among adults aged >= 15 years **/
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Prevalence of HIV among adults aged >= 15 years - " + ecBean.getSocialEconomicBean().getWHO4().getDate() + " ("+ ecBean.getSocialEconomicBean().getWHO4().getSource() +") </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> " + ecBean.getSocialEconomicBean().getWHO4().getValue() + "% </div>");
		DesignUtils.setBorderGrid(gridCellHandle, tableBorder);
		gridCellHandle.getContent().add(text);
		
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "84%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "16%");
		return tableGridHandle;
	}

	
	private GridHandle createConflictsTables() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("conflictsTables", 1, 6);	
		CreateECFirstPageConflicts reportUtils = new CreateECFirstPageConflicts(ecBean, designHandle, designFactory);
		
		/** ADD conflicts TABLE TO GRID **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildConflictsTable());
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildConflictsSource());	
		
		/** ADD refugees TABLE TO GRID **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildRefugeesTable());
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildRefugeesSource());
		
		/** ADD disasters TABLE TO GRID **/
		row = (RowHandle) dataGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildDisastersTable());
		
		row = (RowHandle) dataGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildDisastersSource());
		

		return dataGridHandle;
	}
	

	
	
}