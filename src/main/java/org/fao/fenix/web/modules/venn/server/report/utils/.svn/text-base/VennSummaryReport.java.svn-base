package org.fao.fenix.web.modules.venn.server.report.utils;

import java.io.File;
import java.text.DecimalFormat;
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
import org.fao.fenix.web.modules.venn.client.control.VennGridUtils;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGridMD;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennProjectsBean;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.server.report.VennReportConstants;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;



public class VennSummaryReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean = null;

	private static DecimalFormat formatValues;
	
	public VennSummaryReport(VennReportBeanVO vennReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.vennReportBean = vennReportBean;
		formatValues = new DecimalFormat("#.##");
	}
	
	public void buildStandardReport() throws SemanticException {
		
		//build title
		// build content
		buildVennSummaryInfo();
	}
	
	public GridHandle buildGridStandardReport() throws SemanticException {
		
		//build title
		// build content
		return buildVennSummaryInfo();
	}
	
	
	
	private GridHandle buildVennSummaryInfo() throws SemanticException {
		
		VennGraphBeanVO vennGraphBean = vennReportBean.getVennGraphReportBean().getVennGraphBeanVO();
		List<VennIntersectionsBean> intersactions = vennGraphBean.getAllIntersections();
		
		GridHandle intersectionsGridHandle = designFactory.newGridItem("vennTableInfo", 3, intersactions.size() + 1);
		
		// create headers
		buildSummaryHeaders(intersectionsGridHandle);
		
		int j=1;
		// create intersections
		for (int i=0; i < intersactions.size(); i++) { 
				if (!intersactions.get(i).getAggregatedDacCodes().isEmpty()) {			
					buildIntersectionInfoGrid(intersectionsGridHandle, intersactions.get(i), j);
					j++;
				}
		}
//		designHandle.getBody().add(intersectionsGridHandle);
		return intersectionsGridHandle;
	}
	
	
	private void buildSummaryHeaders(GridHandle gridHandle) throws SemanticException {
		
		RowHandle row = (RowHandle) gridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, VennReportConstants.tableHeaderTextSize, "center");	
		text.setContent("<div style='font-weight:bold'> Priorities (DAC classification) </div>");	
		gridCellHandle.getContent().add(text);
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);
		
		row = (RowHandle) gridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, VennReportConstants.tableHeaderTextSize, "center");	
		text.setContent("<div style='font-weight:bold'> Interventions </div>");	
		gridCellHandle.getContent().add(text);
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);
		
	}
	
	
	private void buildIntersectionInfoGrid(GridHandle gridHandle, VennIntersectionsBean intersaction, int tableIndex) throws SemanticException {		
		// intersection title and palette
		RowHandle row = (RowHandle) gridHandle.getRows().get(tableIndex);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildPaletteBox(intersaction, tableIndex));
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);
		
		//intersection content
		row = (RowHandle) gridHandle.getRows().get(tableIndex);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);
		
		for(int i=0; i < intersaction.getAggregatedDacCodes().size(); i++) {
			text = DesignUtils.createText(designHandle, text, VennReportConstants.tableTextSize, "left");	
			// DAC LABEL
			text.setContent("<div> "+ vennReportBean.getVennGraphReportBean().getVennGraphBeanVO().getDacCodes().get(intersaction.getAggregatedDacCodes().get(i)) +"</div>");
			gridCellHandle.getContent().add(text);
		}
		
		
		row = (RowHandle) gridHandle.getRows().get(tableIndex);
		gridCellHandle = (CellHandle) row.getCells().get(2);	
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);
		
		
		

		// summary budget
		// recalculate ( TODO: no way to extract from grid summary??) 
		for ( String key : vennReportBean.getVennCountryBeanVO().keySet() ){
			VennCountryBeanVO countryBean = vennReportBean.getVennCountryBeanVO().get(key);
			VennProjectsBean projectBean = countryBean.getIntersection(intersaction.getIntersectionName());
			if (projectBean.getProjectsRows() != null) {
				if (!projectBean.getProjectsRows().isEmpty()) {
					String summary = buildSummaryInterventionsReport(projectBean);		
					text = DesignUtils.createText(designHandle, text, VennReportConstants.tableTextSize, "left");	
					text.setContent("<div> "+ summary + "</div>");
					gridCellHandle.getContent().add(text);
						
				} 
			}

		}
		

		

	}
	
	private GridHandle buildPaletteBox(VennIntersectionsBean intersaction, int tableIndex) throws SemanticException {
		GridHandle tableGridHandle = designFactory.newGridItem("vennPaletteBox" + tableIndex, 5, 2 );
		String str = "000000" + Integer.toHexString(intersaction.getColor());
		String color = "#" + str.substring(str.length() - 6);
		if ( color.equals("#ffffff")) 
			color = "#000000";
		
		// Colored BOX 
		
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(1);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(1);
		DesignUtils.setBorderGrid(gridCellHandle, Colors.black);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, color);
		row.setProperty("height", "1.1cm");	
		
		

		// Intersection LABEL
		
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(3);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, VennReportConstants.tableTextSize, "center");	
		text.setContent("<div>"+ intersaction.getLabel() +"</div>");
		gridCellHandle.getContent().add(text);
		
		
		
		// set box size with column width and height
		ColumnHandle col = (ColumnHandle) tableGridHandle.getColumns().get(0);
		col.setProperty("width", "0.3cm");	
		
		col = (ColumnHandle) tableGridHandle.getColumns().get(1);
		col.setProperty("width", "1.1cm");
		
		col = (ColumnHandle) tableGridHandle.getColumns().get(2);
		col.setProperty("width", "0.3cm");	
		
		col = (ColumnHandle) tableGridHandle.getColumns().get(4);
		col.setProperty("width", "0.3cm");	
		

		
		
		
		return tableGridHandle;
	}
	

	private String buildSummaryInterventionsReport(VennProjectsBean projectBean) {
		String summary = "";
		
		Integer quantityIdx = 0;
		// headers
		for (int i = 0; i < projectBean.getHeaders().size(); i++) {		
			if (projectBean.getHeaders().get(i).getDataType().toString().equals("quantity")) {
				quantityIdx = i ;
				break;
			}
		}

		List<Double> values = new ArrayList<Double>();
		Double sum = new Double(0);
		Integer projectsNumber = 0; 
		if ( !projectBean.getProjectsRows().isEmpty())
			projectsNumber = projectBean.getProjectsRows().size();

		// content
		for (List<String> projectRow : projectBean.getProjectsRows()) {
			VennGridMD md = new VennGridMD();
			for (int i = 0; i < projectRow.size(); i++) {
				md.addColumnValue(i, projectRow.get(i));
				if (i == quantityIdx) {
					try {
						Double value = Double.parseDouble(projectRow.get(i));
						values.add(value);
						sum = sum + value;
					} catch (NumberFormatException nfe) {
					}
				}
			}
		}

		
		// TODO: to be translated the HTML
		// creating SUM AND AVG
		if ( sum == 0) {
			summary += "Number of projects: " + projectsNumber + "<br>";
			summary += "Total Budget: Not Available <br>";
			summary += "Average Budget: Not Available";
		}
		else {
			summary += "Number of projects: " + projectsNumber + "<br>";
			summary += "Total Budget: " + formatValues.format(sum) + " (mn) <br>";
			summary += "Average Budget: " + formatValues.format(sum / values.size()) + " (mn)";
		}

		return summary;
	}
	
	
	
	
}