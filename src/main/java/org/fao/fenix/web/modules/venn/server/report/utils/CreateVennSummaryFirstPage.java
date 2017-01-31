package org.fao.fenix.web.modules.venn.server.report.utils;

import java.awt.Color;
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
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.venn.client.control.VennController;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;

import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;



public class CreateVennSummaryFirstPage {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean = null;

	
	public CreateVennSummaryFirstPage(VennReportBeanVO vennReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.vennReportBean = vennReportBean;
	}
	
	public void buildStandardReport() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("gridVenn21223", 5, 1);
		
		
		// build venn part
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(new CreateVennGraphReport(vennReportBean, designHandle, designFactory).buildVennGraphGrid(false));	
		
		
		// build summaryRightPart part
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(4);
		gridCellHandle.getContent().add(new CreateVennChartsReport(vennReportBean, designHandle, designFactory).buildGraphAndTable());
		
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.black);
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);
		row.setProperty("height", "22cm");
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "49.5%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "0.1%");
		
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "49.5%");
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	

	
	
}