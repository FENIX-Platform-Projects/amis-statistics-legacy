package org.fao.fenix.web.modules.ec.server.utils;

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
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECSecondPageFoodSituationNewTemplate;
import org.fao.fenix.web.modules.ec.server.birt.template2011.CreateECFoodSituation2011;

public class ECFoodSecuirtySituation2011 {

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
	
	String faoFontSize = "6.5pt";
	
	String imageWidth = "8cm";
	
	public ECFoodSecuirtySituation2011(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	public GridHandle buildFoodSecuritySituation() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("spFoodSecuritySituation", 1,15);
		
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
		
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			text.setContent("<div style='font-weight:bold'> Food Security and Poverty Situation </div>");		}
		else {
			text.setContent("<div style='font-weight:bold'> Food Security Situation Assessment </div>");
		}
		headerCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) headerGrid.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) headerGrid.getColumns().get(1);
		col.setProperty("width", "95%");	
		
		/** add the title **/
		gridCellHandle.getContent().add(headerGrid);
		/*******************************************************************************/
		
		
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		CreateECFoodSituation2011 reportUtils = new CreateECFoodSituation2011(ecBean, designHandle, designFactory);
		

		
		/** ADDING FOOD SECURITY SITUATION TEXT **/
		
		GridHandle textGrid = designFactory.newGridItem("spFoodSecuritySituationTitletext", 1, 2);
		RowHandle textRow = (RowHandle) headerGrid.getRows().get(0);
		CellHandle textCellHandle = (CellHandle) headerRow.getCells().get(0);
		
		textRow = (RowHandle) textGrid.getRows().get(0);
		textCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, textFontSize, "justify");
		text.setContent("<div> "+ ecBean.getFoodSituationBean().getFoodText() + "</div>");
		textCellHandle.getContent().add(text);
		
		textRow = (RowHandle) textGrid.getRows().get(1);
		textRow.setProperty("height", "0.5cm");
			
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(textGrid);
		
		
		
		
		
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			/** ADDING Title **/
			row = (RowHandle) dataGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, "6.5pt", "left");
			text.setContent("<div style='font-weight:bold; font-style: italic;'>  INTERNATIONAL CLASSIFICATIONS </div>");
			gridCellHandle.getContent().add(text);
			
			/** ADDING Title **/
			row = (RowHandle) dataGridHandle.getRows().get(3);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, "6.5pt", "left");
			text.setContent("<div style='font-weight:bold; font-style: italic;'> FAO SCALE OF HUNGER (% of Undernourishment) </div>");
			gridCellHandle.getContent().add(text);
	
			/** ADDING FOOD SECURITY SITUATION WFP **/
			row = (RowHandle) dataGridHandle.getRows().get(4);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildFAOHungerMap());
			row = (RowHandle) dataGridHandle.getRows().get(5);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildFAOHungerMapLegend());
			
			/** ADDING FOOD SECURITY SITUATION IFPRI **/
			
			row = (RowHandle) dataGridHandle.getRows().get(8);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, "6.5pt", "left");
			text.setContent("<div style='font-weight:bold; font-style: italic;'> IFPRI GLOBAL HUNGER INDEX </div>");
			gridCellHandle.getContent().add(text);
			
			row = (RowHandle) dataGridHandle.getRows().get(9);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildIFPRIIFoodSituation());
		}
		else {
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
			
			row = (RowHandle) dataGridHandle.getRows().get(6);
			row.setProperty("height", "0.2cm");
	
			
			/** ADDING Title **/
			row = (RowHandle) dataGridHandle.getRows().get(7);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, "6.5pt", "left");
			text.setContent("<div style='font-weight:bold; font-style: italic;'> SCALE OF HUNGER (% of Undernourishment) </div>");
			gridCellHandle.getContent().add(text);
	
			/** ADDING FOOD SECURITY SITUATION WFP **/
			row = (RowHandle) dataGridHandle.getRows().get(8);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildFAOHungerMap());
			row = (RowHandle) dataGridHandle.getRows().get(9);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildFAOHungerMapLegend());
			
			/** ADDING FOOD SECURITY SITUATION IFPRI **/
			row = (RowHandle) dataGridHandle.getRows().get(10);
			gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(reportUtils.buildIFPRIIFoodSituation());
	//		row = (RowHandle) dataGridHandle.getRows().get(10);
	//		gridCellHandle = (CellHandle) row.getCells().get(0);
	//		gridCellHandle.getContent().add(reportUtils.buildIFPRIFoodSituationLegend());
		}
		
		return dataGridHandle;
	}
	
}
