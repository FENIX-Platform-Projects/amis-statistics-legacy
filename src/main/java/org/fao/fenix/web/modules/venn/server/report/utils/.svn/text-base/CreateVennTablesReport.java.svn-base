package org.fao.fenix.web.modules.venn.server.report.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;

import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.server.VennServiceImpl;
import org.fao.fenix.web.modules.venn.server.VennUtils;
import org.fao.fenix.web.modules.venn.server.report.VennReportConstants;




public class CreateVennTablesReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean = null;
	

	public CreateVennTablesReport(VennReportBeanVO vennReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.vennReportBean = vennReportBean;
	}
	
	public GridHandle buildStandardReport() throws SemanticException {
		return null;
	}
	

	/** creates the table with categories, projects numer and most active donors **/
	private GridHandle buildCategoriesProjectsDonorTable() throws SemanticException { 
		GridHandle dataGridHandle = designFactory.newGridItem("tableVenn", 1, 3);
//		DesignUtils.setBorderGrid(dataGridHandle, Colors.black);
		
		//build title
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildtitle());	
//		
		// build content
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildTable());	
		
		// build info
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(new VennSummaryReport(vennReportBean, designHandle, designFactory).buildGridStandardReport());	
		
		return dataGridHandle;
	}

	private GridHandle buildtitle() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("tableVennGridtitle", 1, 1);
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "11pt", "left");	
		text.setContent("<div style='font-weight:bold; color:" + Colors.orange + ";'> Table </div>");
		gridCellHandle.getContent().add(text);	
		
		

//		designHandle.getBody().add(dataGridHandle);
		return dataGridHandle;
	}
	
	
	private GridHandle buildTable() throws SemanticException {
		List<List<String>> table = getCategoriesProjectsDonorTable();
		
		GridHandle headerGridHandle = designFactory.newGridItem("vennTableGridShow", 3, 1);




		
		ColumnHandle col = (ColumnHandle) headerGridHandle.getColumns().get(0);
		col.setProperty("width", "10%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(1);
		col.setProperty("width", "80%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(2);
		col.setProperty("width", "10%");
		
		 
//		designHandle.getBody().add(headerGridHandle);
		return headerGridHandle;
	}
	
	private List<List<String>> getCategoriesProjectsDonorTable() {
		List<List<String>> table = new ArrayList<List<String>>();
		
		// headers
		table.add(buildCategoriesProjectsDonorHeaders());
		
		// content
		table.addAll(buildCategoriesProjectsDonorContent());
		
		return table;
	}
	
	private List<String> buildCategoriesProjectsDonorHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Sector");
		headers.add("Projects Number");
		headers.add("Most Active Donors");
		return headers;
		
	}
	
	private List<List<String>> buildCategoriesProjectsDonorContent() {
		List<List<String>> content = new ArrayList<List<String>>();
		
//		content = VennServiceImpl.buildCategoriesProjectsDonorContent();
		
		
		return content;
		
	}
	
	
	
}