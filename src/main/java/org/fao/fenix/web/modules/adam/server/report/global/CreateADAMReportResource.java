package org.fao.fenix.web.modules.adam.server.report.global;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.server.report.utils.ADAMReportConstants;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.report.AddGridToReport;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;
import org.fao.fenix.web.modules.core.server.utils.Setting;


public class CreateADAMReportResource {
	
	ElementFactory designFactory = null;
	
	ReportDesignHandle designHandle = null;
	
	
	public CreateADAMReportResource(ReportDesignHandle designHandle, ElementFactory designFactory){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
	}

	
	public GridHandle addChart2(ADAMResultVO adamResultVO, int index) throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("askADAMaddChart" + index, 1, 2);
		
		
		// ADDING TITLE
		GridHandle titleGrid = designFactory.newGridItem("askADAMaddChartTitleGrid" + index, 1, 1);
		RowHandle row = (RowHandle) titleGrid.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setContent("<div style='color:" + ADAMReportConstants.descriptionHeaderFontColor + ";'> " + adamResultVO.getTitle() +" </div>");
		gridCellHandle.getContent().add(text);
		
		
		
		// ADDING CONTENT
		GridHandle contentGridHandle = designFactory.newGridItem("askADAMaddconent" + index, 2, 1);
		DesignUtils.setBorderGrid(contentGridHandle, Colors.black);		
				
		row = (RowHandle) contentGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionTextSize, "justify");
		text.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + adamResultVO.getDescription() +" </div>");
		gridCellHandle.getContent().add(text);
		
		

		row = (RowHandle) contentGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		ImageHandle imageHandle = designHandle.getElementFactory().newImage("image" + index);
		imageHandle.setDisplayName("map");
		imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
		imageHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		gridCellHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");

		String filename = "\"" + Setting.getFenixPath() + File.separator + "tc" + File.separator +"adam" + File.separator + "adamObjects" + File.separator + adamResultVO.getMediumImagePath() + "\"" ;
		System.out.println("addChart path: " +  filename);
		imageHandle.setFile(filename);

		gridCellHandle.getContent().add(imageHandle);
//		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);		
		
		ColumnHandle col = (ColumnHandle) contentGridHandle.getColumns().get(0);
		col.setProperty("width", "40%");
		
		

		
		// ADDING GRIDS
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(titleGrid);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);
		

		return dataGridHandle;
	}
	
	public GridHandle addChart(ADAMResultVO adamResultVO, int index) throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("askADAMaddChart" + index, 1, 2);
		
		
		// ADDING TITLE
		GridHandle titleGrid = designFactory.newGridItem("askADAMaddChartTitleGrid" + index, 1, 1);
		RowHandle row = (RowHandle) titleGrid.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setContent("<div style='font-weight:bold;color:" + ADAMReportConstants.descriptionHeaderFontColor + ";'> " + adamResultVO.getTitle() +" </div>");
		gridCellHandle.getContent().add(text);
	
		
		
		// ADDING CONTENT
		GridHandle contentGridHandle = designFactory.newGridItem("askADAMaddconent" + index, 1, 2);
//		DesignUtils.setBorderGrid(contentGridHandle, Colors.black);		
				
		row = (RowHandle) contentGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionTextSize, "justify");
		
		if (  adamResultVO.getDescription() != null)
			text.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + adamResultVO.getDescription() +" </div>");
		
		gridCellHandle.getContent().add(text);
		
		

		row = (RowHandle) contentGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle imageHandle = designHandle.getElementFactory().newImage("image" + index);
		imageHandle.setDisplayName("map");
		imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
		imageHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		gridCellHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");

		String filename = "\"" + Setting.getFenixPath() + File.separator + "tc" + File.separator +"adam" + File.separator + "adamObjects" + File.separator + adamResultVO.getMediumImagePath() + "\"" ;
		System.out.println("addChart path: " +  filename);
		imageHandle.setFile(filename);

		gridCellHandle.getContent().add(imageHandle);
	
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);		
		
//		ColumnHandle col = (ColumnHandle) contentGridHandle.getColumns().get(0);
//		col.setProperty("width", "40%");
		
		

		
		// ADDING GRIDS
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(titleGrid);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);
		

		return dataGridHandle;
	}
	
	
	public GridHandle addChartWithCategories(ADAMResultVO adamResultVO, int index) throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("askADAMaddChart" + index, 1, 2);
		
		
		// ADDING TITLE
		GridHandle titleGrid = designFactory.newGridItem("askADAMaddChartTitleGrid" + index, 1, 1);
		RowHandle row = (RowHandle) titleGrid.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setContent("<div style='color:" + ADAMReportConstants.descriptionHeaderFontColor + ";'></div>");
		gridCellHandle.getContent().add(text);
		
		
		
		// ADDING CONTENT
		GridHandle contentGridHandle = designFactory.newGridItem("askADAMaddconent" + index, 1, 1);
		DesignUtils.setBorderGrid(contentGridHandle, Colors.black);		
				
		//row = (RowHandle) contentGridHandle.getRows().get(0);
		//gridCellHandle = (CellHandle) row.getCells().get(0);
		
			
		//text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionTextSize, "justify");
		//text.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + adamResultVO.getDescription() +" </div>");
		//gridCellHandle.getContent().add(text);
		
		

		row = (RowHandle) contentGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle imageHandle = designHandle.getElementFactory().newImage("image" + index);
		imageHandle.setDisplayName("map");
		imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
		imageHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		gridCellHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");

		String filename = "\"" + Setting.getFenixPath() + File.separator + "tc" + File.separator +"adam" + File.separator + "adamObjects" + File.separator + adamResultVO.getBigImagePath() + "\"" ;
		System.out.println(filename);
		imageHandle.setFile(filename);

		gridCellHandle.getContent().add(imageHandle);
//		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);		
		
		ColumnHandle col = (ColumnHandle) contentGridHandle.getColumns().get(0);
		col.setProperty("width", "40%");
		
		

		
		// ADDING GRIDS
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(titleGrid);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);
		

		return dataGridHandle;
	}
	
	
	

	
	public GridHandle addTable(ADAMResultVO adamResultVO, int index) throws SemanticException {
//		GridHandle dataGridHandle = designFactory.newGridItem("askADAMaddTable" + index, 3, 3);
		GridHandle dataGridHandle = designFactory.newGridItem("askADAMaddTable" + index, 1, 3);

		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.headerTextSize, "justify");
		
		if (  adamResultVO.getDescription() != null)
			text.setContent("<div style='color:" + ADAMReportConstants.headerFontColor + ";'> " + adamResultVO.getDescription() +" </div>");
//		text.setContent("<div style='color:" + ADAMReportConstants.headerFontColor + ";'> " + adamResultVO.getTitle() +" </div>");	
		
		gridCellHandle.getContent().add(text);
		
		

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		
		List<List<String>> tablecontents = new ArrayList<List<String>>();
		tablecontents.add(adamResultVO.getTableHeaders());
		tablecontents.addAll(adamResultVO.getTableContents());
		
		System.out.println(tablecontents);
		
		gridCellHandle.getContent().add(new AddGridToReport().buildGrid(tablecontents, designFactory, designHandle, "7pt", "#000000", "7pt", "#000000", "#000000", "#FFFFFF"));
		
//		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
//		col.setProperty("width", "12%");
//		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
//		col.setProperty("width", "1%");

		return dataGridHandle;
	}
	
	public GridHandle addColumnTable(ADAMResultVO adamResultVO, int index) throws SemanticException {
//		GridHandle dataGridHandle = designFactory.newGridItem("askADAMaddTable" + index, 3, 3);
		GridHandle dataGridHandle = designFactory.newGridItem("askADAMaddTable" + index, 1, 3);

		
		/*RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.headerTextSize, "justify");
		text.setContent("<div style='color:" + ADAMReportConstants.headerFontColor + ";'> " + adamResultVO.getDescription() +" </div>");
		gridCellHandle.getContent().add(text);
		*/
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		//row = (RowHandle) dataGridHandle.getRows().get(1);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		List<List<String>> tablecontents = new ArrayList<List<String>>();
		tablecontents.add(adamResultVO.getTableHeaders());
		tablecontents.addAll(adamResultVO.getTableContents());
		
		System.out.println(tablecontents);
		
		gridCellHandle.getContent().add(new AddGridToReport().buildColumnGrid(adamResultVO.getTitle(), tablecontents, designFactory, designHandle, "7pt", "#000000", "7pt", "#000000", "#000000", "#FFFFFF"));
		
//		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
//		col.setProperty("width", "12%");
//		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
//		col.setProperty("width", "1%");

		return dataGridHandle;
	}
	
	
	

	
}