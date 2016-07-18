package org.fao.fenix.web.modules.fsatmis.client.view;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.datasetupload.client.view.JointLoginUploaderPanel;
import org.fao.fenix.web.modules.ofcchart.client.view.OfcChartToolbar;
import org.fao.fenix.web.modules.olap.client.control.FSATMISToolbarController;
import org.fao.fenix.web.modules.olap.client.view.OlapTool;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.core.client.GWT;

public class FSATMISTabPanel {
	
	private OfcChartToolbar toolbar;
	
	private ContentPanel chartPanel;

	private TabPanel tabPanel;

	private TabItem tableTabItem;

	private TabItem olapTabItem;

	private TabItem mapTabItem;
	
	private TabItem chartTabItem;

	private TabItem reportTabItem;

	private TabItem datasetImporterTabItem;
	
	private OlapTool olapTool;
	
	private JointLoginUploaderPanel loginUploadPanel;
	
	private FSATMISTablePanel tablePanel;
	
	public FSATMISTabPanel() {
		tabPanel = new TabPanel();
		tableTabItem = new TabItem("Raw Data");
		
		
		olapTabItem = new TabItem("Table Analysis");
		mapTabItem = new TabItem("Map");
		chartTabItem = new TabItem("Chart");
//		chartTabItem.setScrollMode(Scroll.AUTO);
//		chartTabItem.setScrollMode(Scroll.ALWAYS);
		reportTabItem = new TabItem("Report");
		reportTabItem.setEnabled(false);
		datasetImporterTabItem = new TabItem("Dataset Importer");
		olapTool = new OlapTool();
		olapTool.build(this);
		tablePanel = new FSATMISTablePanel();
	}
	
	public TabPanel build() {
		buildChartItem();
		olapTabItem.add(olapTool.getToolBase());
		tabPanel.add(olapTabItem);
		tabPanel.add(chartTabItem);
		tabPanel.add(mapTabItem);
		tabPanel.add(reportTabItem);
		tabPanel.add(tableTabItem);
		tabPanel.add(datasetImporterTabItem);
		
		mapTabItem.addListener(Events.Select, FSATMISToolbarController.selectGeoDimensionFromTab(olapTool, this));
		chartTabItem.addListener(Events.Select, FSATMISToolbarController.createChartFromTab(olapTool, this, chartTabItem));
//		chartTabItem.addListener(Events.Select, FSATMISToolbarController.createOldChartFromTab(olapTool, this));
//		chartTabItem.addListener(Events.Select, FSATMISToolbarController.createOFCChart(olapTool, this, chartTabItem));
		tableTabItem.addListener(Events.Select, FSATMISToolbarController.showRawDataFromTab(olapTool, this, tablePanel));
		
		loginUploadPanel = new JointLoginUploaderPanel();
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlJointLoginUploadServlet = ep.getJointLoginUploadServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
		datasetImporterTabItem.add(loginUploadPanel.build(urlJointLoginUploadServlet, this, olapTool.getTabPanel().getFsatmisDatasetPanel().getDataSource()));
		
//		tablePanel.build(this);		
		tableTabItem.add(tablePanel.build(this));
//		tablePanel.getFilterPanel().getRefreshTableButton().addSelectionListener(OlapToolController.refreshTable(olapTool, this, tablePanel));
		
		// to be enabled after dataset selection
		chartTabItem.setEnabled(false);
		tableTabItem.setEnabled(false);
		mapTabItem.setEnabled(false);
		tableTabItem.setEnabled(false);
		
		
		return tabPanel;
	}
	
	public void buildChartItem(){
		toolbar = new OfcChartToolbar();
		chartPanel = new ContentPanel();
		chartPanel.setBodyBorder(false);
		chartPanel.setBorders(false);
		chartPanel.setHeaderVisible(false);
		chartPanel.setTopComponent(toolbar.build(this));
		chartPanel.setScrollMode(Scroll.AUTO);
		chartPanel.setWidth(790);
		chartPanel.setHeight(690);
		chartTabItem.setLayoutOnChange(true);
		chartTabItem.add(chartPanel);
	}
	
	public void buildMapTabItem() {
		
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public TabItem getTableTabItem() {
		return tableTabItem;
	}

	public TabItem getOlapTabItem() {
		return olapTabItem;
	}

	public TabItem getMapTabItem() {
		return mapTabItem;
	}

	public TabItem getDatasetImporterTabItem() {
		return datasetImporterTabItem;
	}

	public OlapTool getOlapTool() {
		return olapTool;
	}

	public TabItem getChartTabItem() {
		return chartTabItem;
	}

	public TabItem getReportTabItem() {
		return reportTabItem;
	}

	public FSATMISTablePanel getTablePanel() {
		return tablePanel;
	}

	public ContentPanel getChartPanel() {
		return chartPanel;
	}

	
}