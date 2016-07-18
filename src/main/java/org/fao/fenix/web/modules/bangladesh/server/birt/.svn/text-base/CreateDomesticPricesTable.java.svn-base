package org.fao.fenix.web.modules.bangladesh.server.birt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.utils.bangladesh.PriceChangeOverThePastTwoWeeksBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateDomesticPricesTable {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	PriceChangeOverThePastTwoWeeksBean bean = null;
	
	String tableColor = "#000000";

	String fontSize = "7pt";
	
	public static Map<Integer, String> monthLabelMap;
	
	static {
		monthLabelMap = new HashMap<Integer, String>();
		monthLabelMap.put(0, "Jan");
		monthLabelMap.put(1, "Feb");
		monthLabelMap.put(2, "Mar");
		monthLabelMap.put(3, "Apr");
		monthLabelMap.put(4, "May");
		monthLabelMap.put(5, "Jun");
		monthLabelMap.put(6, "Jul");
		monthLabelMap.put(7, "Aug");
		monthLabelMap.put(8, "Sep");
		monthLabelMap.put(9, "Oct");
		monthLabelMap.put(10, "Nov");
		monthLabelMap.put(11, "Dec");
	}
	
	
	
	public CreateDomesticPricesTable(ReportDesignHandle designHandle, ElementFactory designFactory, PriceChangeOverThePastTwoWeeksBean bean){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.bean = bean;
	}
	
	
	public GridHandle buildTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("changePriceGrie", 1, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** TITLE **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div> <b> Table 1. Rice and atta price changes (Dhaka city) </div> ");
		gridCellHandle.getContent().add(text);
		
		/** TABLE **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildContentTable());
		
		/** SOURCE **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildContentTable());
		
		return dataGridHandle;
	}
	
	private GridHandle buildContentTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("lcGridTable", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");

		
		/** SET HEADERS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildHeaders());	
		
		/** SET ROWS **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
	

		return dataGridHandle;
	}
	
	private GridHandle buildHeaders() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("lcGridheadersTable", 3, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> Period </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> L.C. opened <br> ('000 Mt) </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> L.C. settled <br> ('000 Mt) </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "33%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "33%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "33%");

		return dataGridHandle;
	}
	

	
}