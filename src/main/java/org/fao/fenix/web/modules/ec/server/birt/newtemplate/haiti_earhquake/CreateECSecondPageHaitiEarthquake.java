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


public class CreateECSecondPageHaitiEarthquake {
	
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
	
	public CreateECSecondPageHaitiEarthquake(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	public void build() throws SemanticException {
		buildHeaderFirstRow();
		buildHeaderSecondRow();
		DesignUtils.addSpace(designHandle);
		buildBody();
	}
	
	private void buildHeaderFirstRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderGrid", 9, 1);
//		headerGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
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
		text.setContent("<div>"+ ecBean.getLanguageMap().get("title") +" <br></div>");
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
		text.setContent("<div style='font-weight:bold'></div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) headerGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, "14pt", "right");
		text.setProperty(StyleHandle.COLOR_PROP, "#d54320");
		text.setContent("<div style='font-weight:bold; text-decoration:underline;'><u>" + ecBean.getCountry() + "</u></div>");
		gridCellHandle.getContent().add(text);	
		
		designHandle.getBody().add(headerGridHandle);
	}

	
	
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
		GridHandle dataGridHandle = designFactory.newGridItem("firstColumn", 1, 1);
		
		
		/** build natural disaster 2.1 **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildMaps());
		
		/** build food security situation 3 **/
//		row = (RowHandle) dataGridHandle.getRows().get(2);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
	
		
	
		/** setting color to grid **/
//		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
//		rowHeight.setProperty("height", "14.5cm");
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, red);
		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
//		rowHeight.setProperty("height", "11.6cm");	
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
		
		return dataGridHandle;		
	}
	
	private GridHandle buildSecondColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("secondColumn", 1, 5);
		
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
//		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
//		rowHeight.setProperty("height", "14.5cm");
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
//		rowHeight.setProperty("height", "4.5cm");	
//		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, orange);
		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(4);
//		rowHeight.setProperty("height", "2.5cm");	
//		rowHeight.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, grey);
		
//		rowHeight = (RowHandle) dataGridHandle.getRows().get(4);
//		rowHeight.setProperty("height", "4cm");	
		
		return dataGridHandle;	
	}
	
	
	private GridHandle buildMaps() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spMapss", 1, 13);
		
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spMapsTitle", 2, 1);
		/** ADDING NATURAL DISASTER HEADER **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/** create the header **/		
		RowHandle headerRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle headerCellHandle = (CellHandle) headerRow.getCells().get(0);
		ImageHandle headerImg = designHandle.getElementFactory().newImage("NDImage");
		headerImg.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_blue.gif\"");
//		headerCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, blue);
//		text.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
//		text.setContent("<div style='font-weight:bold'> Drought and Natural Disasters </div>");
		text.setContent("<div style='font-weight:bold'> "+ ecBean.getLanguageMap().get("tradeFlow") +" </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		

		
		
		/** ADDING MAP TEXT1 **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.FONT_SIZE_PROP, textFontSize);
		gridCellHandle.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		if ( ecBean.getNaturalBean().getNaturalDisastersText() != null)
			text.setContent("<div> " + ecBean.getMaps().getMap1Text() + " </div>");
		text.setHeight(textSize);
		gridCellHandle.getContent().add(text);
		
		
		/** ADDING MAP1 **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle map1 = designHandle.getElementFactory().newImage("map1");
		map1.setWidth("100%");
//		chart.setHeight("4.5cm");
//		chart.setWidth("305px");
//		chart.setHeight("150px");
		map1.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ec" + File.separator + ecBean.getCountryCode() + File.separator + ecBean.getMaps().getMap1() + "\"");
//		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.5cm");
		gridCellHandle.getContent().add(map1);
		
		/** ADDING legend **/
		row = (RowHandle) dataGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle legend = designHandle.getElementFactory().newImage("legendMaps");
		legend.setWidth("100%");
		legend.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ec" + File.separator + ecBean.getCountryCode() + File.separator + ecBean.getMaps().getLegend() + "\"");
//		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.5cm");
		gridCellHandle.getContent().add(legend);
		
		/** ADDING CHART SOURCE* */
		row = (RowHandle) dataGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>"+ ecBean.getLanguageMap().get("source") +": <a href='http://www.fews.net'>FEWSNET</a>/<a href='http://www.usgs.gov/'>USGS</a></div>");
		gridCellHandle.getContent().add(text);	
		
		
		/** ADDING MAP TEXT2 **/
		row = (RowHandle) dataGridHandle.getRows().get(7);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.FONT_SIZE_PROP, textFontSize);
		gridCellHandle.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		if ( ecBean.getNaturalBean().getNaturalDisastersText() != null)
			text.setContent("<div> " + ecBean.getMaps().getMap2Text() + " </div>");
		text.setHeight(textSize);
		gridCellHandle.getContent().add(text);
		
		/** ADDING MAP2 **/
		row = (RowHandle) dataGridHandle.getRows().get(9);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle map2 = designHandle.getElementFactory().newImage("map2");
		map2.setWidth("100%");
		map2.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ec" + File.separator + ecBean.getCountryCode() + File.separator + ecBean.getMaps().getMap2() + "\"");
//		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.5cm");
		gridCellHandle.getContent().add(map2);
		
		/** ADDING CHART SOURCE* */
		row = (RowHandle) dataGridHandle.getRows().get(10);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>"+ ecBean.getLanguageMap().get("source") +": <a href='http://ochaonline.un.org/'>Derived from OCHA </a></div>");
		gridCellHandle.getContent().add(text);	
		

		
	
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
		rowHeight.setProperty("height", "0.3cm");
		rowHeight = (RowHandle) dataGridHandle.getRows().get(6);
		rowHeight.setProperty("height", "1.1cm");
		rowHeight = (RowHandle) dataGridHandle.getRows().get(8);
		rowHeight.setProperty("height", "0.3cm");

		
//		/** ADDING NATURAL DISASTER MAP **/
//		row = (RowHandle) dataGridHandle.getRows().get(4);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		ImageHandle map = designHandle.getElementFactory().newImage("map");
//		map.setWidth("9.5cm");
////		map.setHeight("4.5cm");
////		map.setWidth("330px");
////		map.setHeight("150px");
//		File mapFile = new File(Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersMap());
//		map.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersMap() + "\"");
//		System.out.println(mapFile.getAbsolutePath());
//		if ( mapFile.isFile()) 		
//			gridCellHandle.getContent().add(map);
		
//		if ( mapFile.isFile() || chartFile.isFile()) {				
//			/** Source  **/
//			row = (RowHandle) dataGridHandle.getRows().get(5);
//			gridCellHandle = (CellHandle) row.getCells().get(0);
//			text = designHandle.getElementFactory().newTextItem("");
//			text = DesignUtils.createText(designHandle, text, "6pt", "left");
//			text.setContent("<div>Source: <a href='http://mars.jrc.ec.europa.eu/mars/About-us/FOODSEC'>JRC  MARS – FoodSec</a></div>");
//			gridCellHandle.getContent().add(text);
//		}
		
		
		
		return dataGridHandle;
	}
	
	
	
	private GridHandle buildIssuesFoodSecurity() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spIssuesFC", 1, 6);
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
		text.setProperty(StyleHandle.COLOR_PROP, red);
		text.setContent("<div style='font-weight:bold'> "+ ecBean.getLanguageMap().get("agricultureAndFAOResponse") +" </div>");
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
		

		/** MAP LAND USE **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		ImageHandle imageHandle = designHandle.getElementFactory().newImage("mapLandUseImage");
		imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
		imageHandle.setWidth("100%");
//		imageHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.7cm");
		imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + ecBean.getCountryCode() + File.separator + ecBean.getFoodIssuesBean().getMap() + "\"");
		gridCellHandle.getContent().add(imageHandle);
			
		/** ADDING CHART SOURCE* */
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>"+ ecBean.getLanguageMap().get("source") +": <a href='http://www.cnsahaiti.org'>CNSA</a>/<a href='http://www.fews.net'>FEWSNET</a></div>");
		gridCellHandle.getContent().add(text);	
		
		
		
		
		/** CROP CALENDAR IMAGE **/
		row = (RowHandle) dataGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		imageHandle = designHandle.getElementFactory().newImage("cropCalendarImage");
		imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
		imageHandle.setWidth("100%");
//		imageHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.7cm");
		imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + ecBean.getCountryCode()  + File.separator + ecBean.getFoodIssuesBean().getChartFileName() + "\"");
		gridCellHandle.getContent().add(imageHandle);
			
		/** ADDING CHART SOURCE* */
		row = (RowHandle) dataGridHandle.getRows().get(5);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>"+ ecBean.getLanguageMap().get("source") +": <a href='http://www.cnsahaiti.org'>CNSA</a>/<a href='http://www.fews.net'>FEWSNET</a></div>");
		gridCellHandle.getContent().add(text);	


		
		

		
		
		
		
		return dataGridHandle;
	}
	
	private GridHandle buildGovernancePolicies() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spIGovernance", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		CreateECSecondPageGovernanceHaitiEarthquake reportUtils = new CreateECSecondPageGovernanceHaitiEarthquake(ecBean, designHandle, designFactory);
		
		
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
		text.setProperty(StyleHandle.COLOR_PROP, orange);
		text.setContent("<div style='font-weight:bold'> "+ ecBean.getLanguageMap().get("governmentPolicies") +" </div>");
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
//		row = (RowHandle) dataGridHandle.getRows().get(2);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(reportUtils.buildGovernance());
		

		
		return dataGridHandle;
	}
	
	private GridHandle buildCurrentEventsAndContacts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("currentEventsGrid", 1, 2);
		GridHandle newsContactsGrid = designFactory.newGridItem("newsContactsGrid", 1, 1);
		newsContactsGrid.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);


		
		
//		RowHandle row = (RowHandle) newsContactsGrid.getRows().get(0);
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		gridCellHandle.getContent().add(buildCurrentEvents());
		
		RowHandle row = (RowHandle) newsContactsGrid.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildContacts());
		
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(newsContactsGrid);
		
//		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
//		col.setProperty("width", "5%");
//		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
//		col.setProperty("width", "95%");	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  "6pt", "right");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, white);
		text.setProperty(StyleHandle.COLOR_PROP, "#4f5559");
		text.setContent("<div style='font-style: italic;'>"+ ecBean.getLanguageMap().get("info") +"</div>");
		gridCellHandle.getContent().add(text);	
		
//		RowHandle rowHeight = (RowHandle) newsContactsGrid.getRows().get(0);
//		rowHeight.setProperty("height", "2cm");



		return dataGridHandle;
	}
	
	private GridHandle buildCurrentEvents() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spCurrentEvents", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
//		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, grey);
		
		/** ADDING CURRENT EVENTS HEADER **/
		
		
		GridHandle headerHandle = designFactory.newGridItem("currentEventsheader", 2, 1);
		headerHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		RowHandle row = (RowHandle) headerHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  textFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div style='font-weight:bold'> Current events to watch </div> ");
		gridCellHandle.getContent().add(text);
		
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text,  "6pt", "left");
		text.setProperty(StyleHandle.COLOR_PROP, "#000000");
		text.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		text.setContent("<div style='font-weight:bold'> (Click to see the full report) </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(headerHandle);
		
		ColumnHandle col = (ColumnHandle) headerHandle.getColumns().get(0);
		col.setProperty("width", "4cm");
			
		/*****/
		
		/** ADDING CURRENT EVENTS TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text,  tableFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, blue);

		if ( !ecBean.getNews().isEmpty()) {
			String news = "";
			int i=0;
			for(String n : ecBean.getNews()) {
				news += n;
				if ( i != ecBean.getNews().size() -1) {
					news += "<br>";
				}
			}
			text.setContent("<div>"+ news + "</div>");
		}
		else 
			text.setContent("<div> No News Found </div>");		
		
		gridCellHandle.getContent().add(text);

		return dataGridHandle;
	}
	
	private GridHandle buildContacts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spContacts", 1, 2);
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		
		/** ADDING CONTACTS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  "7.5pt", "left");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text.setProperty(StyleHandle.COLOR_PROP, white);
		text.setContent("<div style='font-weight:bold'; font-style: italic;'>"+ ecBean.getLanguageMap().get("contacts") +": Information-for-action@fao.org<br>"+ ecBean.getLanguageMap().get("website") +": www.foodsec.org</div>");
		DesignUtils.setBorderGrid(gridCellHandle, orange);
//		DesignUtils.setBorderGrid(text, orange);
		gridCellHandle.getContent().add(text);

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text = DesignUtils.createText(designHandle, text,  "6pt", "right");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text.setProperty(StyleHandle.COLOR_PROP, "#000000");
		text.setContent("<div style='font-weight:bold'>"+ ecBean.getLanguageMap().get("poweredBy") +" <a href='http://lprapp08.fao.org/fenix-portal/'><font color='#FFFFFF'>GIEWS Workstation</font></a></div>");
		gridCellHandle.getContent().add(text);	
		
		


		return dataGridHandle;
	}
	
	
	
}