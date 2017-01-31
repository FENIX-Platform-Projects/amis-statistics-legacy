package org.fao.fenix.web.modules.ec.server.birt.newtemplate;

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


public class CreateECSecondPagePakistanAgriculture {
	
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
	
	public CreateECSecondPagePakistanAgriculture(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	


	
	public GridHandle buildNaturalDisasters() throws SemanticException {
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
		text.setContent("<div style='font-weight:bold'>  Impact to Agriculture  </div>");
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
		
		
//		/** ADDING NATURAL DISASTER CHART **/
//		row = (RowHandle) dataGridHandle.getRows().get(2);
//		gridCellHandle = (CellHandle) row.getCells().get(0);
//		ImageHandle chart = designHandle.getElementFactory().newImage("chart1");
////		chart.setWidth("9.5cm");
////		chart.setHeight("4.5cm");
////		chart.setWidth("305px");
////		chart.setHeight("150px");
////		File chartFile = new File(Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
////				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersChart());
////		chart.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
////				+ File.separator + "ec" + File.separator + ecBean.getNaturalBean().getNaturalDisastersChart() + "\"");
//		File chartFile = new File(ecBean.getNaturalBean().getNaturalDisastersChart().replace("\"", ""));
//		chart.setFile(ecBean.getNaturalBean().getNaturalDisastersChart());
//		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.5cm");
//		if ( chartFile.isFile()) 		
//			gridCellHandle.getContent().add(chart);
//
//		
//	
//
//		
//		/** ADDING NATURAL DISASTER MAP **/
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
//		File mapFile = new File(ecBean.getNaturalBean().getNaturalDisastersMap().replace("\"", ""));
//		map.setFile(ecBean.getNaturalBean().getNaturalDisastersMap());
//		if ( mapFile.isFile()) 		
//			gridCellHandle.getContent().add(map);
//		
//		if ( mapFile.isFile() || chartFile.isFile()) {				
//			/** Source  **/
//			row = (RowHandle) dataGridHandle.getRows().get(5);
//			gridCellHandle = (CellHandle) row.getCells().get(0);
//			text = designHandle.getElementFactory().newTextItem("");
//			text = DesignUtils.createText(designHandle, text, "6pt", "left");
//			if ( ecBean.getNaturalBean().getShowLegend().equalsIgnoreCase("JRC"))
//				text.setContent("<div>Source: <a href='http://mars.jrc.ec.europa.eu/mars/About-us/FOODSEC'>JRC  MARS – FoodSec</a></div>");
//			gridCellHandle.getContent().add(text);
//		}
		
		
		
		return dataGridHandle;
	}
	
	
	
	
}