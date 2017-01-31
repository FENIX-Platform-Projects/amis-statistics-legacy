package org.fao.fenix.web.modules.venn.server.report.utils;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.GraphReportEngine;
import org.fao.fenix.web.modules.birt.server.utils.report.AddGridToReport;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.server.VennContants;
import org.fao.fenix.web.modules.venn.server.report.VennReportConstants;



public class CreateVennChartsReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean = null;
	
	private static final Logger LOGGER = Logger.getLogger(CreateVennChartsReport.class);
	
	/** TODO: find a way to decide dinamically the charts for the standard report **/
	public CreateVennChartsReport(VennReportBeanVO vennReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.vennReportBean = vennReportBean;
	}
	
	private void setStandardCharts() {
		
	}
	
	public void buildStandardReport() throws SemanticException {
//		setStandardCharts();
		buildGraphs(true);
	}
	
	public GridHandle buildGraphAndTable() throws SemanticException { 
		GridHandle gridHandle = designFactory.newGridItem("buildGraphAndTable", 1, 3);
		
		// graphs
		RowHandle row = (RowHandle) gridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);	
		gridCellHandle.getContent().add(buildGraphs(false));
		
		row = (RowHandle) gridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
//		gridCellHandle.getContent().add(AddGridToReport.buildGrid(vennReportBean.getVennTablesReportBean().get("categories").getTableValues(), designFactory, designHandle, VennReportConstants.tableTextSize, VennReportConstants.tableFontColor, VennReportConstants.headerTextSize, VennReportConstants.headerFontColor, Colors.black, Colors.white));
//		gridCellHandle.getContent().add(AddGridToReport.buildGrid(vennReportBean.getVennTablesReportBean().get("globalbycategories").getTableValues(), designFactory, designHandle, VennReportConstants.tableTextSize, VennReportConstants.tableFontColor, VennReportConstants.headerTextSize, VennReportConstants.headerFontColor, Colors.black, Colors.white));
		
		return gridHandle;
	}
	
	public GridHandle buildGraphs(Boolean insertIntoReport) throws SemanticException {
		GridHandle gridHandle = designFactory.newGridItem("charts", 1, 1);
//		GridHandle gridHandle = designFactory.newGridItem("charts", 1, vennReportBean.getVennChartsBeanVO().size());
		LOGGER.info("here");


		int i=0;
//		for (String chartCategory : vennReportBean.getVennChartsBeanVO().keySet()) {
//			for (String chartKey : vennReportBean.getVennChartsBeanVO().get(chartCategory).keySet()) {
//				LOGGER.info("here" + i );
//				gridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
//				RowHandle row = (RowHandle) gridHandle.getRows().get(i);
//				CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);	
//				GraphReportEngine gre0 = new GraphReportEngine(vennReportBean.getVennChartsBeanVO().get(chartCategory).get(chartKey).getChart(), designHandle);
//				gridCellHandle.getContent().add(gre0.getChart());
//				DesignUtils.setBorderGrid(gridCellHandle, "#000000");
//				i++;
//			}
//		}
		
		/** that code retrieves the total of categories **/
		for (String chartCategory : vennReportBean.getVennChartsBeanVO().keySet()) {
			if ( chartCategory.contains("globalbycategories")) {
//			if ( chartCategory.contains("bycategories")) {
				for (String chartKey : vennReportBean.getVennChartsBeanVO().get(chartCategory).keySet()) {
					if ( chartKey.toLowerCase().equals("total")) {
						gridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
						RowHandle row = (RowHandle) gridHandle.getRows().get(i);
						CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);	
						GraphReportEngine gre0 = new GraphReportEngine(vennReportBean.getVennChartsBeanVO().get(chartCategory).get(chartKey).getChart(), designHandle);
						gridCellHandle.getContent().add(gre0.getChart());
						DesignUtils.setBorderGrid(gridCellHandle, "#000000");
						i++;
					}
				}
			}
			
//			for (String chartKey : vennReportBean.getVennChartsBeanVO().get(chartCategory).keySet()) {
//				gridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
//				RowHandle row = (RowHandle) gridHandle.getRows().get(i);
//				CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);	
//				GraphReportEngine gre0 = new GraphReportEngine(vennReportBean.getVennChartsBeanVO().get(chartCategory).get(chartKey).getChart(), designHandle);
//				gridCellHandle.getContent().add(gre0.getChart());
//				DesignUtils.setBorderGrid(gridCellHandle, "#000000");
//				i++;
//			}
		}

		
		if ( insertIntoReport )
			designHandle.getBody().add(gridHandle);
		return gridHandle;
	}
	
	
	

	
	
}