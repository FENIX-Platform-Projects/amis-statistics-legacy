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
import org.fao.fenix.core.domain.bangladesh.ImportsBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateLCTable {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ImportsBean importsBean = null;
	
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
	
	
	
	public CreateLCTable(ReportDesignHandle designHandle, ElementFactory designFactory, ImportsBean importsBean){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.importsBean = importsBean;
	}
	
	
	public GridHandle buildTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("lcGrid", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** TITLE **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div> <b> Table 2. LC situation </b> (in '000 MT) </div> ");
		gridCellHandle.getContent().add(text);
		
		/** TABLE **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
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
		gridCellHandle.getContent().add(buildRows());	

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
		text.setContent("<div style='font-wight:bold;'> L.C. opened </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> L.C. settled </div>");
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
	
	private GridHandle buildRows() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("lcGridrowsTable", 5, 4);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		
		/** create table row 0 (headers) **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);

		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> Rice </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> Wheat</div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> Rice </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-wight:bold;'> Wheat </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		
		
		
		/** build FIRST table content row **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getFirstRowPeriod() + " </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getRice().getFirstRowOpen() + " </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getWheat().getFirstRowOpen() + " </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getRice().getFirstRowSettled() + " </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getWheat().getFirstRowSettled() + " </div>");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		/** build Second table content row **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getSecondRowPeriod() + " </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getRice().getSecondRowOpen() + " </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getWheat().getSecondRowOpen() + " </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getRice().getSecondRowSettled() + " </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getWheat().getSecondRowSettled() + " </div>");
		gridCellHandle.getContent().add(text);
		
		
		
		/** build Third table content row **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getThirdRowPeriod() + " </div>");
		DesignUtils.setOnlyBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getRice().getThirdRowOpen() + " </div>");
		DesignUtils.setOnlyBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getWheat().getThirdRowOpen() + " </div>");
		DesignUtils.setOnlyBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getRice().getThirdRowSettled() + " </div>");
		DesignUtils.setOnlyBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div>" + importsBean.getLcBean().getWheat().getThirdRowSettled() + " </div>");
		DesignUtils.setOnlyBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);
		
		
		
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "33%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "16%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "16%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "16%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "16%");

		return dataGridHandle;
	}
	

	
}