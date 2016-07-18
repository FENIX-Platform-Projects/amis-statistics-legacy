package org.fao.fenix.web.modules.ec.server.birt.newtemplate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.core.domain.ec.ECConflictsBean;
import org.fao.fenix.core.domain.ec.ECValuesBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateECFirstPageConflictsNewTemplate {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String grey = "#C9CBCB";
	
	String orange = "#FF9933";
	
	String tableFontSize = "7.5pt";
	
	Logger LOGGER = Logger.getLogger(CreateECFirstPageConflictsNewTemplate.class);
	
	public CreateECFirstPageConflictsNewTemplate(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	
	public GridHandle buildRefugeesTable() throws SemanticException {

		GridHandle tableGridHandle = designFactory.newGridItem("refugeesTable", 4, 3);	
		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** HEADERS  **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-weight:bold;'> Refugees and IDPs </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getConflictsBean().getRefugeesIn1().getDate().equals("n.a."))
			text.setContent("<div style='font-weight:bold;'> "+ ecBean.getConflictsBean().getRefugeesIn1().getDate() +" </div>");
		else 
			text.setContent("<div style='font-weight:bold;'> "+ ecBean.getConflictsBean().getRefugeesFrom1().getDate() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getConflictsBean().getRefugeesIn1().getDate().equals("n.a."))
			text.setContent("<div style='font-weight:bold;'> "+ ecBean.getConflictsBean().getRefugeesIn2().getDate() +" </div>");
		else 
			text.setContent("<div style='font-weight:bold;'> "+ ecBean.getConflictsBean().getRefugeesFrom2().getDate() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		if ( !ecBean.getConflictsBean().getRefugeesIn1().getDate().equals("n.a."))
			text.setContent("<div style='font-weight:bold;'> "+ ecBean.getConflictsBean().getRefugeesIn3().getDate() +" </div>");
		else 
			text.setContent("<div style='font-weight:bold;'> "+ ecBean.getConflictsBean().getRefugeesFrom3().getDate() +" </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		/** Create refugees in **/
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Total in the country </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getRefugeesIn1().getValue() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getRefugeesIn2().getValue() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getRefugeesIn3().getValue() +" </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		/** Create refugees from **/
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> Total outside the country </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getRefugeesFrom1().getValue() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getRefugeesFrom2().getValue() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'>"+ ecBean.getConflictsBean().getRefugeesFrom3().getValue() +" </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "40%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "20%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(2);
		col.setProperty("width", "20%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(3);
		col.setProperty("width", "20%");
		return tableGridHandle;
	}
	
	
	public GridHandle buildRefugeesSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("refugeesFoodSituationLegend", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>Source: <a href='http://www.unhcr.org/pages/4a013eb06.html'>UNHCR</a> Statistical Online Population Database, United Nations High Commissioner for Refugees (UNHCR), Data extracted on "+ ecBean.getConflictsBean().getUnhcrDate() +".</div>");	
		gridCellHandle.getContent().add(text);


		return dataGridHandle;
	}
	
	
	public GridHandle buildConflictsTable() throws SemanticException {

		GridHandle tableGridHandle = designFactory.newGridItem("conflictsTable", 4, 4);	
		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** HEADERS  **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-weight:bold;'> Conflicts </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-weight:bold;'> Start </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-weight:bold;'> Status </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-weight:bold;'> Intensity </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		/** Create conflicts **/
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts1().getSource()+ "</div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts1().getDate() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts1().getValue() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts1().getIntensity() +"/5 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		/** Create conflicts **/
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts2().getSource() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts2().getDate() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts2().getValue() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'>"+ ecBean.getConflictsBean().getConflicts2().getIntensity() +"/5 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		/** Create conflicts **/
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts3().getSource() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts3().getDate() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'> "+ ecBean.getConflictsBean().getConflicts3().getValue() +" </div>");
		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div style='font-style: italic;'>"+ ecBean.getConflictsBean().getConflicts3().getIntensity() +"/5 </div>");
		DesignUtils.setBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "40%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "20%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(2);
		col.setProperty("width", "20%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(3);
		col.setProperty("width", "20%");
		return tableGridHandle;
	}
	
	public GridHandle buildConflictsSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("conflictsFoodSituationLegend", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>Source: <a href='http://hiik.de/en/konfliktbarometer/index.html'>Heidelberg Institute For International Conflict Research </a> </div>");	
		
		gridCellHandle.getContent().add(text);
		
		return dataGridHandle;
	}
	
	public GridHandle buildDisastersTable(CellHandle tableCellHandle, CellHandle sourceCellHandle) throws SemanticException {

		GridHandle tableGridHandle = designFactory.newGridItem("disastersTable", 4, 4);	
		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		List<ECValuesBean> disaseters = disastersInformations(ecBean.getConflictsBean());
		
		
		/*************************
		 * 
		 * WORKAROUND BECAUSE THEY CHANGED THE TEMPLATES 2000 TIMES IN THE LAST 12 MONTHS
		 * 
		 *****************/
//		System.out.println(disaseters.size());
		if (!disaseters.get(2).getSource().equals("NO DATA")) {
			/** HEADERS  **/
			RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
			TextItemHandle text = designHandle.getElementFactory().newTextItem("");
			text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
			text.setContent("<div><b>Disaster Type</b> (last year)</div>");
			DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
			gridCellHandle.getContent().add(text);
			
			row = (RowHandle) tableGridHandle.getRows().get(0);
			gridCellHandle = (CellHandle) row.getCells().get(1);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
			text.setContent("<div style='font-weight:bold;'> Date </div>");
			DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
			gridCellHandle.getContent().add(text);
			
			row = (RowHandle) tableGridHandle.getRows().get(0);
			gridCellHandle = (CellHandle) row.getCells().get(2);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
			text.setContent("<div style='font-weight:bold;'> Location </div>");
			DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
			gridCellHandle.getContent().add(text);
			
			row = (RowHandle) tableGridHandle.getRows().get(0);
			gridCellHandle = (CellHandle) row.getCells().get(3);
			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
			text.setContent("<div style='font-weight:bold;'> Alert </div>");
			DesignUtils.setBorderGrid(gridCellHandle, grey);	
			gridCellHandle.getContent().add(text);
			
			/** Create conflicts **/
			int i=1;
			for(ECValuesBean bean : disaseters) {
				if (!bean.getSource().equals("NO DATA") && i < 4) {
					row = (RowHandle) tableGridHandle.getRows().get(i);
					gridCellHandle = (CellHandle) row.getCells().get(0);
					text = designHandle.getElementFactory().newTextItem("");
					text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
					text.setContent("<div style='font-style: italic;'> "+ bean.getSource() + "</div>");
					DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
					gridCellHandle.getContent().add(text);
					
					row = (RowHandle) tableGridHandle.getRows().get(i);
					gridCellHandle = (CellHandle) row.getCells().get(1);
					text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
					text.setContent("<div style='font-style: italic;'> "+ bean.getDate() +" </div>");
					DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
					gridCellHandle.getContent().add(text);
					
					row = (RowHandle) tableGridHandle.getRows().get(i);
					gridCellHandle = (CellHandle) row.getCells().get(2);
					text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
					text.setContent("<div style='font-style: italic;'> "+ bean.getValue() +" </div>");
					DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
					gridCellHandle.getContent().add(text);
					
				
					row = (RowHandle) tableGridHandle.getRows().get(i);
					gridCellHandle = (CellHandle) row.getCells().get(3);
					text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
					if ( !bean.getIntensity().equals("-"))
						text.setContent("<div style='font-style: italic;'> "+ bean.getIntensity() +"/3 </div>");
					else
						text.setContent("<div style='font-style: italic;'> "+ bean.getIntensity() +" </div>");
					DesignUtils.setBorderGrid(gridCellHandle, grey);	
					gridCellHandle.getContent().add(text);
				}
				i++;
			}
			tableCellHandle.getContent().add(tableGridHandle);
			sourceCellHandle.getContent().add(buildDisastersSource());
		}
		else {
//			/** HEADERS  **/
//			RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
//			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//			TextItemHandle text = designHandle.getElementFactory().newTextItem("");
//			text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
//			text.setContent("<div><b>Disaster Type</b> (last year)</div>");
//			DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
//			gridCellHandle.getContent().add(text);
//			
//			row = (RowHandle) tableGridHandle.getRows().get(0);
//			gridCellHandle = (CellHandle) row.getCells().get(1);
//			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
//			text.setContent("<div style='font-weight:bold;'> Date </div>");
//			DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
//			gridCellHandle.getContent().add(text);
//			
//			row = (RowHandle) tableGridHandle.getRows().get(0);
//			gridCellHandle = (CellHandle) row.getCells().get(2);
//			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
//			text.setContent("<div style='font-weight:bold;'> Location </div>");
//			DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
//			gridCellHandle.getContent().add(text);
//			
//			row = (RowHandle) tableGridHandle.getRows().get(0);
//			gridCellHandle = (CellHandle) row.getCells().get(3);
//			text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
//			text.setContent("<div style='font-weight:bold;'> Alert </div>");
//			DesignUtils.setBorderGrid(gridCellHandle, grey);	
//			gridCellHandle.getContent().add(text);
//			
//			/** Create conflicts **/
//			int i=1;
//			for(ECValuesBean bean : disaseters) {
//				if (!bean.getSource().equals("NO DATA")) {
//						row = (RowHandle) tableGridHandle.getRows().get(i);
//						gridCellHandle = (CellHandle) row.getCells().get(0);
//						text = designHandle.getElementFactory().newTextItem("");
//						text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
//						text.setContent("<div style='font-style: italic;'> "+ bean.getSource() + "</div>");
//						DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
//						gridCellHandle.getContent().add(text);
//						
//						row = (RowHandle) tableGridHandle.getRows().get(i);
//						gridCellHandle = (CellHandle) row.getCells().get(1);
//						text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
//						text.setContent("<div style='font-style: italic;'> "+ bean.getDate() +" </div>");
//						DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
//						gridCellHandle.getContent().add(text);
//						
//						row = (RowHandle) tableGridHandle.getRows().get(i);
//						gridCellHandle = (CellHandle) row.getCells().get(2);
//						text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
//						text.setContent("<div style='font-style: italic;'> "+ bean.getValue() +" </div>");
//						DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
//						gridCellHandle.getContent().add(text);
//						
//						
//						row = (RowHandle) tableGridHandle.getRows().get(i);
//						gridCellHandle = (CellHandle) row.getCells().get(3);
//						text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
//						if ( !bean.getIntensity().equals("-"))
//							text.setContent("<div style='font-style: italic;'> "+ bean.getIntensity() +"/3 </div>");
//						else
//							text.setContent("<div style='font-style: italic;'> "+ bean.getIntensity() +" </div>");
//						DesignUtils.setBorderGrid(gridCellHandle, grey);	
//						gridCellHandle.getContent().add(text);
//				}
//				i++;
//			}
		}
		
		
		
		
		
		
		
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "40%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "20%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(2);
		col.setProperty("width", "20%");
		col = (ColumnHandle) tableGridHandle.getColumns().get(3);
		col.setProperty("width", "20%");
		return tableGridHandle;
	}
	
	public GridHandle buildDisastersSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("disastersSource", 1, 1);
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "6pt", "left");
		text.setContent("<div>Source: <a href='http://www.gdacs.org/'>Global Disaster Alert and Coordination System</a> - 3 = 1000 or more people killed or 800000 or more people displaced. 2 = 100 or more people killed or 80000 or more displaced.</div>");
		gridCellHandle.getContent().add(text);
		
		return dataGridHandle;
	}
	
	private List<ECValuesBean> disastersInformations(ECConflictsBean bean) {
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		
		/** floods **/
		if (!bean.getFloods().isEmpty() && bean.getEarthQuakes().isEmpty()){
			disasters = addFlods(bean);
		}
		
		/** earthquakes **/
		else if (bean.getFloods().isEmpty() && !bean.getEarthQuakes().isEmpty()){
			disasters = addEarthQuakes(bean);
		}
		
		/** floods & earthquakes **/
		else if ( !bean.getFloods().isEmpty() && !bean.getEarthQuakes().isEmpty()){
			disasters = addFloodsAndEarthquakes(bean);
		}
		
		else {
			ECValuesBean v = new ECValuesBean("-", "-");
			v.setSource("No Floods");
			v.setIntensity("-");
			disasters.add(v);
			v = new ECValuesBean("-", "-");
			v.setSource("No Earthquakes");
			v.setIntensity("-");
			disasters.add(v);
			v = new ECValuesBean("-", "-");
			v.setSource("NO DATA");
			v.setIntensity("-");
			disasters.add(v);
		}
		
		return disasters;		
	}
	
	private List<ECValuesBean> addFlods(ECConflictsBean bean){
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		int indicator = 0;
		
		while( indicator != 3) {
			if ( indicator < bean.getFloods().size() ) 
				disasters.add(bean.getFloods().get(indicator));
//			else {
//				ECValuesBean v = new ECValuesBean("-", "-");
//				v.setSource("-");
//				v.setIntensity("-");
//				disasters.add(v);			
//			}
			indicator++;
		}
		ECValuesBean v = new ECValuesBean("-", "-");
		v.setSource("No Earthquakes");
		v.setIntensity("-");
		disasters.add(v);
		v = new ECValuesBean("-", "-");
		v.setSource("NO DATA");
		v.setIntensity("-");
		disasters.add(v);
			
		return disasters;
	}
	
	private List<ECValuesBean> addEarthQuakes(ECConflictsBean bean){
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		int indicator = 0;
		
		while( indicator != 3) {
			if ( indicator < bean.getEarthQuakes().size()) 
				disasters.add(bean.getEarthQuakes().get(indicator));
//			else {
//				ECValuesBean v = new ECValuesBean("-", "-");
//				v.setSource("-");
//				v.setIntensity("-");
//				disasters.add(v);			
//			}
			indicator++;
		}	
		ECValuesBean v = new ECValuesBean("-", "-");
		v.setSource("No Floods");
		v.setIntensity("-");
		disasters.add(v);
		
		v = new ECValuesBean("-", "-");
		v.setSource("NO DATA");
		v.setIntensity("-");
		disasters.add(v);
			
		return disasters;
	}
	
	private List<ECValuesBean> addFloodsAndEarthquakes(ECConflictsBean bean){
		List<ECValuesBean> disasters = new ArrayList<ECValuesBean>();
		int indicator = 0;
		int i=0;
		
		while ( i < 2) {
			if (i < bean.getEarthQuakes().size()) {
				disasters.add(bean.getEarthQuakes().get(i));
				indicator++;
			}
			i++;
		}
		
		i=0;
		while ( i < 2) {
			if ( i < bean.getFloods().size()) {
				disasters.add(bean.getFloods().get(i));
				indicator++;
			}
			i++;
		}
		
		while( indicator <= 3) {
			ECValuesBean v = new ECValuesBean("-", "-");
			v.setSource("NO DATA");
			v.setIntensity("-");
			disasters.add(v);			
			indicator++;
		}	
			
		
		return disasters;
	}
	
	private void printECvalue(List<ECValuesBean> beans){
		System.out.println("disasters");
		for(ECValuesBean bean : beans)
			System.out.println(bean.getDate() + " | " + bean.getValue());
	}
}