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
import org.fao.fenix.web.modules.ec.server.birt.template2011.CreateECFirstPageConflicts2011;

public class ECConflicts2011 {

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
	
	public ECConflicts2011(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	public GridHandle buildECConflicts() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("conflictsPart", 1, 3);
		
		/** ADDING  conflicts HEADER **/
		/*******************************************************************************/
		GridHandle headerGrid = designFactory.newGridItem("spConflictsTitle2011", 2, 1);
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
		
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			text.setContent("<div style='font-weight:bold'> Natural Disasters and Conflicts </div>");
		}
		else	
			text.setContent("<div style='font-weight:bold'> Natural Disasters, Drought and Conflicts </div>");
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
//		text.setHeight("3cm");
		text.setContent("<div> "+ ecBean.getConflictsBean().getConflictsText() + "</div>");
		gridCellHandle.getContent().add(text);
		
		// HARDCODED FOR 12,19,92,165 (ARMENIA, Azerbaijan, GEORGIA, Repubblic of Moldova
		if ( ecBean.getCountryCode().equals("13") || ecBean.getCountryCode().equals("19") || ecBean.getCountryCode().equals("92") || ecBean.getCountryCode().equals("165")) {
			// DO NOTHING
		}
		else {
			row = (RowHandle) dataGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) row.getCells().get(0);	
			gridCellHandle.getContent().add(createConflictsTables());
		}
		
		
		
		
//		dataGridHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	
		return dataGridHandle;
	}
	
	private GridHandle createConflictsTables() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("conflictsTables", 1, 5);	
		CreateECFirstPageConflicts2011 reportUtils = new CreateECFirstPageConflicts2011(ecBean, designHandle, designFactory);
		
		/** ADD refugees TABLE TO GRID **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildRefugeesTable());
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(reportUtils.buildRefugeesSource());
		
		/** ADD disasters TABLE TO GRID **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		
		/// workaroud for the source 30/09/2010
		RowHandle rowSource = (RowHandle) dataGridHandle.getRows().get(4);
		CellHandle gridCellHandleSource = (CellHandle) row.getCells().get(0);	
		

		reportUtils.buildDisastersTable(gridCellHandle, gridCellHandleSource);


//		RowHandle rowHeight = (RowHandle) dataGridHandle.getRows().get(5);
//		rowHeight.setProperty("height", "0.3cm");
	
		return dataGridHandle;
	}
	
	
}
