package org.fao.fenix.web.modules.ec.server.birt.template2011;

import java.io.File;
import java.util.Date;

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
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECSecondPageFoodSituationNewTemplate;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECSecondPageGovernanceNewTemplate;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECSecondPageVegetationCondition;
import org.fao.fenix.web.modules.ec.server.utils.ECConflicts2011;


public class CreateECSecondPage2011 {
	
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
	
	String imageHeight = "10.5cm";
	
	String imageWidth = "7.4cm";
	
	public CreateECSecondPage2011(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
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
		text.setContent("<div>Food Security Information for Decision Making <br></div>");
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
		GridHandle dataGridHandle = designFactory.newGridItem("firstColumn", 1, 3);
		
		
		/** build natural disaster 2.1 **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		if ( ecBean.getReportType().equalsIgnoreCase("STANDARD"))
			gridCellHandle.getContent().add(new CreateECSecondPageVegetationCondition(ecBean, designHandle, designFactory).buildNaturalDisasters());

			
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(new CreateECCropCalendar2011(ecBean, designHandle, designFactory).buildCropCalendar());
			
		
		/** build food security situation 3 **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(new  ECConflicts2011(ecBean, designHandle, designFactory).buildECConflicts());
//		gridCellHandle.getContent().add(buildFoodSecuritySituation());	
		
	
		/** setting color to grid **/
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		rowHeight.setProperty("height", "14.5cm");
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, red);
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(2);
//		rowHeight.setProperty("height", "11.6cm");	
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
		
		return dataGridHandle;		
	}
	
	private GridHandle buildSecondColumn() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("secondColumn", 1, 3);
		
		/** BUILD ISSUES ON FOOD SECURITY (3.1) **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildIssuesFoodSecurity());
		

		/** BUILD GOVERNANCE (4) **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
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
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildCurrentEventsAndContacts());
	
		
		
		/** setting color to grid **/
		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(0);
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			// DO NOT SET HEIGHT
		}
		else {
			rowHeight.setProperty("height", "14.5cm");
		}
		gridCellHandle = (CellHandle) rowHeight.getCells().get(0);
//		DesignUtils.setBorderGrid(gridCellHandle, blue);
		
		rowHeight = (RowHandle) dataGridHandle.getRows().get(1);
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			// DO NOT SET HEIGHT
		}
		else {
			rowHeight.setProperty("height", "6.5cm");
		}
		
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
				+ File.separator + "ImgForTemplate" + File.separator + "freccia_grande_blue.gif\"");
//		headerCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
		headerCellHandle.getContent().add(headerImg);
		
		headerCellHandle = (CellHandle) headerRow.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, blue);
//		text.setProperty(StyleHandle.PADDING_TOP_PROP, topPaddingHeaders);
//		text.setContent("<div style='font-weight:bold'> Drought and Natural Disasters </div>");
		text.setContent("<div style='font-weight:bold'> Vegetation Condition </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
		
//		row = (RowHandle) dataGridHandle.getRows().get(1);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		text = DesignUtils.createText(designHandle, text,  textFontSize, "justify");
//		text.setContent("<div> "+ ecBean.getGovernanceBean().getGovernanceText() + "</div>");
//		gridCellHandle.getContent().add(text);
		
		
		/** ADDING NATURAL DISASTER TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.FONT_SIZE_PROP, textFontSize);
		gridCellHandle.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setHeight("2.42cm");
		text.setContent("<div> " + ecBean.getNaturalBean().getNaturalDisastersText() + " </div>");
//		text.setContent("<div> "+ ecBean.getConflictsBean().getConflictsText()+ "</div>");
//		text.setHeight(textSize);
		gridCellHandle.getContent().add(text);
		
		
		/** ADDING NATURAL DISASTER CHART **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle chart = designHandle.getElementFactory().newImage("chart1");
//		chart.setWidth("9.5cm");
//		chart.setHeight("4.5cm");
//		chart.setWidth("305px");
//		chart.setHeight("150px");
//		File chartFile = new File(Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersChart());
//		chart.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersChart() + "\"");
		File chartFile = new File(ecBean.getNaturalBean().getNaturalDisastersChart().replace("\"", ""));
		chart.setFile(ecBean.getNaturalBean().getNaturalDisastersChart());
		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.5cm");
		if ( chartFile.isFile()) 		
			gridCellHandle.getContent().add(chart);

		
	

		
		/** ADDING NATURAL DISASTER MAP **/
		row = (RowHandle) dataGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle map = designHandle.getElementFactory().newImage("map");
		map.setWidth("9.5cm");
//		map.setHeight("4.5cm");
//		map.setWidth("330px");
//		map.setHeight("150px");
//		File mapFile = new File(Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersMap());
//		map.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersMap() + "\"");
//		System.out.println(mapFile.getAbsolutePath());
		File mapFile = new File(ecBean.getNaturalBean().getNaturalDisastersMap().replace("\"", ""));
		map.setFile(ecBean.getNaturalBean().getNaturalDisastersMap());
		if ( mapFile.isFile()) 		
			gridCellHandle.getContent().add(map);
		
		if ( mapFile.isFile() || chartFile.isFile()) {				
			/** Source  **/
			row = (RowHandle) dataGridHandle.getRows().get(5);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = designHandle.getElementFactory().newTextItem("");
			text = DesignUtils.createText(designHandle, text, "6pt", "left");
			if ( ecBean.getNaturalBean().getShowLegend().equalsIgnoreCase("JRC"))
				text.setContent("<div>Source: <a href='http://mars.jrc.ec.europa.eu/mars/About-us/FOODSEC'>JRC  MARS – FoodSec</a></div>");
			gridCellHandle.getContent().add(text);
		}
		
		
		
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
		text.setProperty(StyleHandle.COLOR_PROP, red);
		text.setContent("<div style='font-weight:bold'> Food Security Situation Assessment </div>");
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		CreateECSecondPageFoodSituationNewTemplate reportUtils = new CreateECSecondPageFoodSituationNewTemplate(ecBean, designHandle, designFactory);
		
		
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
		if ( !ecBean.getFoodSituationBean().getFao().getDate().equals("n.a.")) {
			row = (RowHandle) dataGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildFAOFoodSituation());
		}
		else {
			/** STATIC SENTENCE FROM GIEWS **/
			row = (RowHandle) dataGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildGIEWSNote());
		}
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
		
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			// DO NOT SET HEIGHT
		}
		else {
			text.setHeight("2.42cm");
		}
		text.setContent("<div> " + ecBean.getFoodIssuesBean().getFoodText() + " </div>");
		gridCellHandle.getContent().add(text);
		

		
		/** ADDING ISSUES ON FOOD CHART **/
		/** ADDING PRICES CHARTS **/
		File chartFile = new File(ecBean.getFoodIssuesBean().getChartFileName().replace("\"", ""));

		if ( chartFile.isFile()) {	
			row = (RowHandle) dataGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);	
			ImageHandle imageHandle = designHandle.getElementFactory().newImage("firstPriceChart");
			imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
			// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
			if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
				// DO NOT SET HEIGHT
				imageHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "1.1cm");
			}
			else {
				imageHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.7cm");
			}
			
			// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
			if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
				// DO NOT SET HEIGHT
				imageHandle.setWidth(imageWidth);
			}
			else {
				imageHandle.setHeight(imageHeight);
			}
//			imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ecCharts/" + ecBean.getFoodIssuesBean().getChartFileName() + "\"");
			imageHandle.setFile(ecBean.getFoodIssuesBean().getChartFileName());
			gridCellHandle.getContent().add(imageHandle);
			
			/** ADDING CHART SOURCE* */
			row = (RowHandle) dataGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = designHandle.getElementFactory().newTextItem("");
			text = DesignUtils.createText(designHandle, text, "6pt", "left");
			

			text.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.7cm");
		
			text.setContent("<div>Source: <a href='http://www.fao.org/giews/countrybrief/index.jsp'>GIEWS Country Briefs</a></div>");
			if (ecBean.getFoodIssuesBean().getShowLegend().equals("GIEWS"))
				gridCellHandle.getContent().add(text);		
//			text.setContent("<div>Source: <a href='http://www.fao.org/giews/english/index.htm'>GIEWS</a> - Global Information and Early Warning System</div>");
//			gridCellHandle.getContent().add(text);	
			
		}
		else {
			row = (RowHandle) dataGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, "12pt", "center");
//			text.setContent("<div> CHART NOT AVAILABLE </div>");
			gridCellHandle.getContent().add(text);
		}
		
		
		
		return dataGridHandle;
	}
	
	private GridHandle buildGovernancePolicies() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spIGovernance", 1, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		CreateECSecondPageGovernanceNewTemplate reportUtils = new CreateECSecondPageGovernanceNewTemplate(ecBean, designHandle, designFactory);
		
		
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
		text.setContent("<div style='font-weight:bold'> Government Policies </div>");
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
		GridHandle dataGridHandle = designFactory.newGridItem("currentEventsGrid", 1, 3);
		GridHandle newsContactsGrid = designFactory.newGridItem("newsContactsGrid", 1, 2);
		newsContactsGrid.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);


		
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			RowHandle row = (RowHandle) newsContactsGrid.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(buildFrancoiseCountries());
			
			row = (RowHandle) newsContactsGrid.getRows().get(1);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(buildFrancoiseContacts());
			
			
			row = (RowHandle) dataGridHandle.getRows().get(0);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(newsContactsGrid);
		}
		else  {
			RowHandle row = (RowHandle) newsContactsGrid.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(buildCurrentEvents());
			
			row = (RowHandle) newsContactsGrid.getRows().get(1);
			gridCellHandle = (CellHandle) row.getCells().get(0);
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
			text.setContent("<div style='font-style: italic;'>The Programme on Linking Information and Decision Making to Improve Food Security is funded by the European Union and implemented by the Food and Agriculture Organization of the United Nations.</div>");
			gridCellHandle.getContent().add(text);
			
			// hardcoding on the latest minute for the briefs date = '01/01/2015
			System.out.println("1)CURRENT DATE: " + ecBean.getReferenceDate());

			
			System.out.println("YEAR: " + ecBean.getReferenceDate().getYear());
			System.out.println("month: " + ecBean.getReferenceDate().getMonth());
			System.out.println("date: " + ecBean.getReferenceDate().getDate());

			
			if ( ecBean.getReferenceDate().getYear() == 111 && ecBean.getReferenceDate().getMonth() == 0 && ecBean.getReferenceDate().getDate() == 15 ){ 
				row = (RowHandle) dataGridHandle.getRows().get(2);
				gridCellHandle = (CellHandle) row.getCells().get(0);
				text = designHandle.getElementFactory().newTextItem("");
				text = DesignUtils.createText(designHandle, text,  "6pt", "right");
				
				text.setProperty(StyleHandle.COLOR_PROP, "#CC0000");
				//mozambique
				if ( ecBean.getCountryCode().equals("170") ) {
					text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Mozambique please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/mozambique'>Annex</a></div>");
					gridCellHandle.getContent().add(text);
				}
				// kenya
				else if ( ecBean.getCountryCode().equals("133") ) {
					text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Kenya please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/kenya'>Annex</a></div>");
					gridCellHandle.getContent().add(text);
				}
				// ethiopia
				else if ( ecBean.getCountryCode().equals("79") ) {
					text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Ethiopia please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/ethiopia'>Annex</a></div>");
					gridCellHandle.getContent().add(text);
				}
				// Afganistan
				else if ( ecBean.getCountryCode().equals("1") ) {
					text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Afghanistan please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/asia/afghanistan'>Annex</a></div>");
					gridCellHandle.getContent().add(text);
				}
			}
			else if ( ecBean.getReferenceDate().getYear() == 111 && ecBean.getReferenceDate().getMonth() == 4 && ecBean.getReferenceDate().getDate() == 15 ){ 
					row = (RowHandle) dataGridHandle.getRows().get(2);
					gridCellHandle = (CellHandle) row.getCells().get(0);
					text = designHandle.getElementFactory().newTextItem("");
					text = DesignUtils.createText(designHandle, text,  "6pt", "right");
					
					text.setProperty(StyleHandle.COLOR_PROP, "#CC0000");
					if ( ecBean.getCountryCode().equals("133") ) {
						text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Kenya please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/kenya'>Annex</a></div>");
						gridCellHandle.getContent().add(text);
					}
					else if ( ecBean.getCountryCode().equals("1") ) {
                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Afghanistan please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/asia/afghanistan'>Annex</a></div>");
                        gridCellHandle.getContent().add(text);
                    }
					
					else if ( ecBean.getCountryCode().equals("23") ) {
					                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Bangladesh please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/asia/bangladesh'>Annex</a></div>");
					                        gridCellHandle.getContent().add(text);
					                }
					
					else if ( ecBean.getCountryCode().equals("79") ) {
					                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Ethiopia please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/ethiopia'>Annex</a></div>");
					                        gridCellHandle.getContent().add(text);
					                }
					
					else if ( ecBean.getCountryCode().equals("111") ) {
					                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Honduras please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/honduras'>Annex</a></div>");
					                        gridCellHandle.getContent().add(text);
					                }
					
					else if ( ecBean.getCountryCode().equals("180") ) {
					                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Nicaragua please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/nicaragua'>Annex</a></div>");
					                        gridCellHandle.getContent().add(text);
					                }
					
					else if ( ecBean.getCountryCode().equals("170") ) {
					                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Mozambique please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/mozambique'>Annex</a></div>");
					                        gridCellHandle.getContent().add(text);
					                }
					
					else if ( ecBean.getCountryCode().equals("181") ) {
					                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Niger please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/niger'>Annex</a></div>");
					                        gridCellHandle.getContent().add(text);
					                }
					else if ( ecBean.getCountryCode().equals("33") ) {
					                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Bolivia please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/bolivia'>Annex</a></div>");
					                        gridCellHandle.getContent().add(text);
					}
			}
			else if ( ecBean.getReferenceDate().getYear() == 111 && ecBean.getReferenceDate().getMonth() == 9 && ecBean.getReferenceDate().getDate() == 15 ){
				row = (RowHandle) dataGridHandle.getRows().get(2);
				gridCellHandle = (CellHandle) row.getCells().get(0);
				text = designHandle.getElementFactory().newTextItem("");
				text = DesignUtils.createText(designHandle, text,  "6pt", "right");
				
				text.setProperty(StyleHandle.COLOR_PROP, "#CC0000");
				if ( ecBean.getCountryCode().equals("133") ) {
					text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Kenya please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/kenya'>Annex</a></div>");
					gridCellHandle.getContent().add(text);
				}
				else if ( ecBean.getCountryCode().equals("1") ) {
                    text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Afghanistan please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/asia/afghanistan'>Annex</a></div>");
                    gridCellHandle.getContent().add(text);
                }
				
				else if ( ecBean.getCountryCode().equals("23") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Bangladesh please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/asia/bangladesh'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("79") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Ethiopia please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/ethiopia'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("111") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Honduras please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/honduras'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("180") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Nicaragua please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/nicaragua'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("170") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Mozambique please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/mozambique'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				else if ( ecBean.getCountryCode().equals("33") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Bolivia please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/bolivia'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				}
			}
			else if ( ecBean.getReferenceDate().getYear() == 112 && ecBean.getReferenceDate().getMonth() == 2 && ecBean.getReferenceDate().getDate() == 1 ){
				row = (RowHandle) dataGridHandle.getRows().get(2);
				gridCellHandle = (CellHandle) row.getCells().get(0);
				text = designHandle.getElementFactory().newTextItem("");
				text = DesignUtils.createText(designHandle, text,  "6pt", "right");
				
				text.setProperty(StyleHandle.COLOR_PROP, "#CC0000");
				if ( ecBean.getCountryCode().equals("133") ) {
					text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Kenya please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/kenya'>Annex</a></div>");
					gridCellHandle.getContent().add(text);
				}
				else if ( ecBean.getCountryCode().equals("1") ) {
                    text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Afghanistan please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/asia/afghanistan'>Annex</a></div>");
                    gridCellHandle.getContent().add(text);
                }
				
				else if ( ecBean.getCountryCode().equals("23") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Bangladesh please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/asia/bangladesh'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("79") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Ethiopia please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/ethiopia'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("111") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Honduras please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/honduras'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("180") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Nicaragua please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/nicaragua'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				
				else if ( ecBean.getCountryCode().equals("170") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Mozambique please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/africa/mozambique'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				                }
				else if ( ecBean.getCountryCode().equals("33") ) {
				                        text.setContent("<div style='font-weight:bold'>* For a further analysis of prices in Bolivia please see: <a href='http://www.foodsec.org/web/publications/briefs/countries/america/bolivia'>Annex</a></div>");
				                        gridCellHandle.getContent().add(text);
				}
			}
				

		}
		
//		RowHandle rowHeight = (RowHandle) newsContactsGrid.getRows().get(0);
//		rowHeight.setProperty("height", "2cm");


		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		// ADD DISCAIMER
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			RowHandle row = (RowHandle) dataGridHandle.getRows().get(1);
			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
			TextItemHandle text = designHandle.getElementFactory().newTextItem("");
			text = DesignUtils.createText(designHandle, text,  "6pt", "left");
			text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, white);
			text.setProperty(StyleHandle.COLOR_PROP, "#4f5559");
			text.setContent("<div style='font-style: italic;'>The Programme on Linking Information and Decision Making to Improve Food Security is funded by the European Union and implemented by the Food and Agriculture Organization of the United Nations.</div>");
			gridCellHandle.getContent().add(text);
			
			row = (RowHandle) dataGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);
	
			gridCellHandle.getContent().add(buildFrancoiseDisclaimer());	
		}
		
		
		
	


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
		text.setContent("<div style='font-weight:bold'; font-style: italic;'>For more information, contact: Information-for-action@fao.org<br>Website: www.foodsec.org</div>");
		DesignUtils.setBorderGrid(gridCellHandle, orange);
//		DesignUtils.setBorderGrid(text, orange);
		gridCellHandle.getContent().add(text);

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text = DesignUtils.createText(designHandle, text,  "6pt", "right");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text.setProperty(StyleHandle.COLOR_PROP, "#000000");
		text.setContent("<div style='font-weight:bold'>Powered By the <a href='http://lprapp08.fao.org/fenix-portal/'><font color='#FFFFFF'>GIEWS Workstation</font></a></div>");
		gridCellHandle.getContent().add(text);	
		
		


		return dataGridHandle;
	}
	
	
	// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
	private GridHandle buildFrancoiseCountries() throws SemanticException {
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
		text.setContent("<div style='font-weight:bold'> Further Information </div> ");
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
		col.setProperty("width", "3.5cm");
			
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
	
	
	private GridHandle buildFrancoiseContacts() throws SemanticException {
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
		
		// ARMENIA CONTACT POINT
		if ( ecBean.getCountryCode().equals("13")) {
			text.setContent("<div style='font-weight:bold'; font-style: italic;'>FAO Contact Point: Mane Tapaltsyan (Mane.Tapaltsyan@fao.org) <br>Website: www.foodsec.org</div>");
		}
		else if ( ecBean.getCountryCode().equals("19")) {
			text.setContent("<div style='font-weight:bold'; font-style: italic;'>FAO Contact Point: Rasmiyya Aliyeva (Rasmiyya.Aliyeva@fao.org) <br>Website: www.foodsec.org</div>");
		}
		else if ( ecBean.getCountryCode().equals("92")) {
			text.setContent("<div style='font-weight:bold'; font-style: italic;'>FAO Contact Point: Rati Shavgulidze (Rati.Shavgulidze@fao.org) <br>Website: www.foodsec.org</div>");
		}
		else if ( ecBean.getCountryCode().equals("165")) {
			text.setContent("<div style='font-weight:bold'; font-style: italic;'>FAO Contact Point: Viorel Gherciu (Viorel.Gherciu@fao.org) <br>Website: www.foodsec.org</div>");
		}
		
		
		DesignUtils.setBorderGrid(gridCellHandle, orange);
//		DesignUtils.setBorderGrid(text, orange);
		gridCellHandle.getContent().add(text);

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text = DesignUtils.createText(designHandle, text,  "6pt", "right");
//		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		text.setProperty(StyleHandle.COLOR_PROP, "#000000");
		text.setContent("<div style='font-weight:bold'>Powered By the <a href='http://lprapp08.fao.org/fenix-portal/'><font color='#FFFFFF'>GIEWS Workstation</font></a></div>");
		gridCellHandle.getContent().add(text);	
		
		


		return dataGridHandle;
	}
	
	
	private GridHandle buildFrancoiseDisclaimer() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("disclaimer", 1, 1);
//		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, orange);
		
		/** ADDING CONTACTS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,  "6pt", "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, white);
		text.setProperty(StyleHandle.COLOR_PROP, "#4f5559");
		text.setContent("<div style='font-style: italic;'>The views expressed in this publication do not necessarily reflect the views of the European Union or the Food and Agriculture Organization of the United Nations.</div>");
		gridCellHandle.getContent().add(text);	
		
		
		
	
		


		return dataGridHandle;
	}
	
}