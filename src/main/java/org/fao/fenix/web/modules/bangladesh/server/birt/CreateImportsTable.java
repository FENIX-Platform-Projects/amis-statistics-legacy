package org.fao.fenix.web.modules.bangladesh.server.birt;

import java.text.DecimalFormat;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.domain.bangladesh.ImportsChartValuesBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateImportsTable {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ImportsChartValuesBean importsChartValuesBean = null;
	
	String tableColor = "#000000";

	String fontSize = "7pt";
	
	String aidColor = "#ffff00";

	String publicColor = "#cc0003";
	
	String privateColor = "#00ccff";
	
	String commodity = null;
	
	private static DecimalFormat formatChangeValue;	
	

	
	
	public CreateImportsTable(String commodity, ReportDesignHandle designHandle, ElementFactory designFactory, ImportsChartValuesBean importsChartValuesBean){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.importsChartValuesBean = importsChartValuesBean;
		this.commodity = commodity;
		formatChangeValue = new DecimalFormat("#.#");
	}
	
	
	public GridHandle buildTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("importtable" + commodity  , 5, 4);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		buildAid(dataGridHandle);
		buildPublicCommercial(dataGridHandle);
		buildPrivate(dataGridHandle);
		buildTotal(dataGridHandle);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "5%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "25%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "23%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "23%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "23%");
		
		return dataGridHandle;
	}
	
	private void buildAid(GridHandle dataGridHandle) throws SemanticException {
		GridHandle colorGrid = designFactory.newGridItem("colorGrid1" + commodity  , 3, 3);
		colorGrid.setProperty(GridHandle.WIDTH_PROP, "100%");
		/** SET AID **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		RowHandle rowColor = (RowHandle) colorGrid.getRows().get(1);
		CellHandle gridCellHandleColor = (CellHandle) rowColor.getCells().get(1);
		gridCellHandleColor.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, aidColor );
		DesignUtils.setBorderGrid(gridCellHandleColor, tableColor);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(colorGrid);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "7pt", "left");
		text.setContent("<div> Aid </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setRightBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);	
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getLastFNAid() != null)
			text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getLastFNAid()) +" </div>");
		else 
			text.setContent("<div> "+ importsChartValuesBean.getLastFNAid() +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getCurrentAid() != null)
			text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getCurrentAid()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getCurrentAid() +" </div>");
		
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getLastYearAid() != null)
			text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getLastYearAid()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getLastYearAid()+" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);

	}
	
	private void buildPublicCommercial(GridHandle dataGridHandle) throws SemanticException {
		GridHandle colorGrid = designFactory.newGridItem("colorGrid2" + commodity  , 3, 3);
		colorGrid.setProperty(GridHandle.WIDTH_PROP, "100%");
		/** SET Public Commercial **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(1);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
	
		RowHandle rowColor = (RowHandle) colorGrid.getRows().get(1);
		CellHandle gridCellHandleColor = (CellHandle) rowColor.getCells().get(1);
		gridCellHandleColor.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, publicColor );
		DesignUtils.setBorderGrid(gridCellHandleColor, tableColor);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(colorGrid);

		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "7pt", "left");
		text.setContent("<div> Public Commercial </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setRightBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getLastFNPublicCommercial() != null)
			text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getLastFNPublicCommercial()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getLastFNPublicCommercial() +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);	
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getCurrentPublicCommercial() != null)
			text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getCurrentPublicCommercial()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getCurrentPublicCommercial() +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getLastYearPublicCommercial() != null)
			text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getLastYearPublicCommercial()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getLastYearPublicCommercial() +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);

	}
	
	private void buildPrivate(GridHandle dataGridHandle) throws SemanticException {
		GridHandle colorGrid = designFactory.newGridItem("colorGrid3" + commodity  , 3, 3);
		colorGrid.setProperty(GridHandle.WIDTH_PROP, "100%");
		/** SET Private **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(2);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);

		RowHandle rowColor = (RowHandle) colorGrid.getRows().get(1);
		CellHandle gridCellHandleColor = (CellHandle) rowColor.getCells().get(1);
		gridCellHandleColor.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, privateColor );
		DesignUtils.setBorderGrid(gridCellHandleColor, tableColor);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableColor);
		gridCellHandle.getContent().add(colorGrid);
		

		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "7pt", "left");
		text.setContent("<div> Private </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setRightBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getLastFNPrivate() != null)
			text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getLastFNPrivate()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getLastFNPrivate() +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(3);	
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getCurrentPrivate() != null)
			text.setContent("<div> "+formatChangeValue.format(importsChartValuesBean.getCurrentPrivate()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getCurrentPrivate() +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		if ( importsChartValuesBean.getLastYearPrivate() != null)
			text.setContent("<div> "+formatChangeValue.format(importsChartValuesBean.getLastYearPrivate()) +" </div>");
		else
			text.setContent("<div> "+ importsChartValuesBean.getLastYearPrivate() +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setTopBorderGrid(gridCellHandle, tableColor);

	}
	
	
	
	private void buildTotal(GridHandle dataGridHandle) throws SemanticException {
		/** SET Total **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(3);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "7pt", "left");
		text.setContent("<div> </div>");
		gridCellHandle.getContent().add(text);
		DesignUtils.setLeftBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, "7pt", "left");
		text.setContent("<div> Total </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setRightBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getLastFNTotal()) +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(3);	
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getCurrentTotal()) +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setBorderGrid(gridCellHandle, tableColor);
		
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		text = DesignUtils.createText(designHandle, text, "7pt", "center");
		text.setContent("<div> "+ formatChangeValue.format(importsChartValuesBean.getLastYearTotal()) +" </div>");
		gridCellHandle.getContent().add(text);	
		DesignUtils.setBorderGrid(gridCellHandle, tableColor);

	}

	
}