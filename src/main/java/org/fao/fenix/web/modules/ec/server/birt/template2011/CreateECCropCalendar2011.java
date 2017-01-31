package org.fao.fenix.web.modules.ec.server.birt.template2011;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.core.domain.ec.ECConflictsBean;
import org.fao.fenix.core.domain.ec.ECCropCalendarBean;
import org.fao.fenix.core.domain.ec.ECValuesBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateECCropCalendar2011 {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String grey = "#C9CBCB";
	
	String orange = "#FF9933";
	
	String tableFontSize = "7.5pt";
	
	String sowingColor = "#815121";
	
	String growingColor = "#68bb45";
	
	String harvestingColor = "#f9c80d";
	
	String white = "#ffffff";
	
	String black = "#000000";
	
	Logger LOGGER = Logger.getLogger(CreateECCropCalendar2011.class);
	
	public CreateECCropCalendar2011(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	
	public GridHandle buildCropCalendar() throws SemanticException {

		GridHandle tableGridHandle = designFactory.newGridItem("cropcalendar", 1, 2);	
		tableGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** HEADERS  **/
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setContent("<div style='font-weight:bold;'> Crop phase in the reference period </div>");
//		DesignUtils.setLeftBorderGrid(gridCellHandle, grey);	
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildStagesTable());

		
	
		return tableGridHandle;
	}
	
	
	public GridHandle buildStagesTable() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("stagesTable", 2, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		
		ECCropCalendarBean cropCalendar = ecBean.getCropCalendarBean();
		// sowing
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, sowingColor);
		text.setProperty(StyleHandle.COLOR_PROP, white);

		text.setContent("<div><b>Sowing</b></div>");	
		gridCellHandle.getContent().add(text);
//		DesignUtils.setBorderGrid(gridCellHandle, grey);
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, black);
		if ( cropCalendar.getSowing() != null)
			text.setContent("<div>" + cropCalendar.getSowing().getText() + "</div>");	
		gridCellHandle.getContent().add(text);
//		DesignUtils.setBorderGrid(gridCellHandle, grey);
		
		
		// growing
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div><b>Growing</b></div>");	
		gridCellHandle.getContent().add(text);
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, growingColor);
		text.setProperty(StyleHandle.COLOR_PROP, white);
//		DesignUtils.setBorderGrid(gridCellHandle, grey);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, black);
		if ( cropCalendar.getGrowing() != null)
			text.setContent("<div>" + cropCalendar.getGrowing().getText() + "</div>");	
		gridCellHandle.getContent().add(text);
//		DesignUtils.setBorderGrid(gridCellHandle, grey);
		
		
		// harvesting
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div><b>Harvesting</b></div>");			
		gridCellHandle.getContent().add(text);
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, harvestingColor);
		text.setProperty(StyleHandle.COLOR_PROP, white);
//		DesignUtils.setBorderGrid(gridCellHandle, grey);
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, tableFontSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, black);
		if ( cropCalendar.getHaversting() != null)
			text.setContent("<div>" + cropCalendar.getHaversting().getText() + "</div>");	
		gridCellHandle.getContent().add(text);
//		DesignUtils.setBorderGrid(gridCellHandle, grey);
		

		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "20%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "80%");	

		return dataGridHandle;
	}

}