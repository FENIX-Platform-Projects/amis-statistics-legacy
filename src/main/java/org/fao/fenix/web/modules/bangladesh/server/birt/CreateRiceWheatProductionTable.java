package org.fao.fenix.web.modules.bangladesh.server.birt;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateRiceWheatProductionTable {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	String tableColor = "#000000";

	String fontSize = "6.5pt";
	
	public CreateRiceWheatProductionTable(ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
	}
	
	
	public GridHandle buildTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("box1ricewheatgrid", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** TABLE **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildContentTable());
		
		/** SOURCE **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildSource());
		
		return dataGridHandle;
	}
	
	private GridHandle buildContentTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("box1ricewheattable", 6, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** SET ROW 0 **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> <br>2007/08 </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> 2008/09 <br>estim. </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> 2009/10 <br>f'cast. </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div style='font-weight:bold'> Change 2009 over <br>2008 in % </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		/** SET ROW 1 **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div> Production </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div> Rice (milled) <br>Wheat </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> 441.1 <br>625.5 </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> 459.6 <br>681.4 </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> 450.8 <br>678.6 </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> -1.9% <br>-0.4% </div> ");
		DesignUtils.setOnlyTopBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		/** SET ROW 2 **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div> Ending <br>stocks </div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, fontSize, "left");
		text.setContent("<div> Rice (milled) <br>Wheat </div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> 110.8 <br>143.3 </div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> 124.4 <br>172.3 </div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> 121.1 <br>183.5 </div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(5);
		text = DesignUtils.createText(designHandle, text, fontSize, "center");
		text.setContent("<div> -2.7% <br>6.5% </div> ");
		DesignUtils.setTopBottomBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(text);	

		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "14%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "20%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "11%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "11%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "11%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(5);
		col.setProperty("width", "25%");
		
		return dataGridHandle;
	}
	
	private GridHandle buildSource() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("box1ricewheatsource", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text,fontSize, "left");
		text.setContent("<div style='font-style: italic;'>Source: FAO Food Outlook December 2009 </div>");
		gridCellHandle.getContent().add(text);	

		return dataGridHandle;
	}
	
	
	
	
	
}