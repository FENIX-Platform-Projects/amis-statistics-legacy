package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.FSATMISToolbarController;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.client.control.OlapSaver;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class OlapToolbar {

	private ToolBar toolbar;
	
	private IconButton createChart;
	
	private IconButton createMap;
	
	private IconButton export;
	
	private IconButton exportExcel;
	
	private IconButton exportHTML;
	
	private IconButton save;
	
	private IconButton saveAs;
	
	private IconButton showManual;
	
	public OlapToolbar() {
		toolbar = new ToolBar();
	}
	
	public ToolBar build(OlapWindow window, boolean enableSave) {
		
		// create table
		Button createTable = new Button(BabelFish.print().createMultidimensionalTable(), OlapController.createTable(window));
		createTable.setIconStyle("wand");
		createTable.setToolTip(BabelFish.print().createMultidimensionalTable());
		toolbar.add(createTable);
		toolbar.add(new SeparatorToolItem());
		
		// add filters
		ToggleButton addFilters = new ToggleButton(BabelFish.print().filters());
		addFilters.addSelectionListener(OlapController.addFilters(window, addFilters));
		addFilters.setIconStyle("filter");
		createTable.setToolTip(BabelFish.print().filters());
//		toolbar.add(addFilters);
//		toolbar.add(new SeparatorToolItem());
		
		// reset
//		IconButton reset = new IconButton("undo", OlapController.reset(window));
//		reset.setToolTip(BabelFish.print().reset());
//		toolbar.add(reset);
//		toolbar.add(new SeparatorToolItem());
		
		// show flat data
		IconButton showFlatData = new IconButton("workspaceTable", OlapController.showFlatData(window));
		showFlatData.setToolTip(BabelFish.print().showFlatData());
		toolbar.add(showFlatData);
		toolbar.add(new SeparatorToolItem());
		
		// export
		export = new IconButton("sendToPdf", OlapController.exportPDF(window));
		export.setToolTip(BabelFish.print().exportPdf());
		export.setEnabled(false);
		toolbar.add(export);
		toolbar.add(new SeparatorToolItem());
		
		// export to excel
		exportExcel = new IconButton("sendToExcel", OlapController.exportExcel(window));
		exportExcel.setToolTip("Export To Excel");
		exportExcel.setEnabled(false);
		toolbar.add(exportExcel);
		toolbar.add(new SeparatorToolItem());
		
		// export to HTML
		exportHTML = new IconButton("sendToHTML", OlapController.exportHTML(window));
		exportHTML.setToolTip("Export To HTML");
		exportHTML.setEnabled(false);
		toolbar.add(exportHTML);
		toolbar.add(new SeparatorToolItem());
		
		// add to report
		if (window.isForReport()) {
			Button addToReport = new Button("Add To Report");
			addToReport.setToolTip(BabelFish.print().createMultidimensionalTable());
			addToReport.addSelectionListener(OlapController.addToReport(window.getReportViewer(), window));
			toolbar.add(addToReport);
			toolbar.add(new SeparatorToolItem());
		}
		
		// save
		if (enableSave) {
			save = new IconButton("save", OlapSaver.save(window));
			save.setToolTip("Save Multidimensional Table");
			save.setEnabled(true); // TODO make it user's privileges dependent 
			toolbar.add(save);
			toolbar.add(new SeparatorToolItem());
		}
		
		// save as...
		if (enableSave) {
			saveAs = new IconButton("saveAs");
			saveAs.setToolTip("Save As...");
			saveAs.setEnabled(false); 
			toolbar.add(saveAs);
			toolbar.add(new SeparatorToolItem());
		}
		
		// show manual
		showManual = new IconButton("help", OlapController.showManual(window));
		showManual.setToolTip("Show Manual");
		showManual.setEnabled(true);
		toolbar.add(showManual);
		toolbar.add(new SeparatorToolItem());
		
		return toolbar;
	}
	
	public ToolBar build(OlapTool tool, FSATMISTabPanel tabPanel) {
		
		// create table
		Button createTable = new Button(BabelFish.print().createMultidimensionalTable(), FSATMISToolbarController.createTable(tool, tabPanel));
		createTable.setToolTip(BabelFish.print().createMultidimensionalTable());
		toolbar.add(createTable);
		toolbar.add(new SeparatorToolItem());
		
		// reset
		IconButton reset = new IconButton("undo", FSATMISToolbarController.reset(tool));
		reset.setToolTip(BabelFish.print().reset());
		toolbar.add(reset);
		toolbar.add(new SeparatorToolItem());
		
		// show flat data
		IconButton showFlatData = new IconButton("workspaceTable", FSATMISToolbarController.showRawData(tool, tabPanel));
		showFlatData.setToolTip(BabelFish.print().showFlatData());
		showFlatData.setEnabled(false);
		toolbar.add(showFlatData);
		toolbar.add(new SeparatorToolItem());
		
		// send to project manager
		IconButton openInProjectManager = new IconButton("sendToProject", FSATMISToolbarController.openInProjectManager(tool, tabPanel));
		openInProjectManager.setToolTip("Open in the Workstation");
		toolbar.add(openInProjectManager);
		toolbar.add(new SeparatorToolItem());
		
		// create chart
		createChart = new IconButton("workspaceChart", FSATMISToolbarController.createChartFromIcon(tool, tabPanel));
		createChart.setToolTip(BabelFish.print().createChart());
		createChart.setEnabled(false);
		toolbar.add(createChart);
		toolbar.add(new SeparatorToolItem());
		
		// create map
		createMap = new IconButton("workspaceMap", FSATMISToolbarController.selectGeoDimension(tool, tabPanel));
		createMap.setToolTip(BabelFish.print().createMap());
		createMap.setEnabled(false);
		toolbar.add(createMap);
		toolbar.add(new SeparatorToolItem());
		
		// export
		export = new IconButton("sendToPdf", FSATMISToolbarController.exportPDF(tool));
		export.setToolTip(BabelFish.print().exportPdf());
		export.setEnabled(false);
		toolbar.add(export);
		toolbar.add(new SeparatorToolItem());
		
		// export to excel
		exportExcel = new IconButton("sendToExcel", FSATMISToolbarController.exportExcel(tool));
		exportExcel.setToolTip("Export To Excel");
		exportExcel.setEnabled(false);
		toolbar.add(exportExcel);
		toolbar.add(new SeparatorToolItem());
		
		// export to HTML
//		exportHTML = new IconButton("sendToHTML", OlapController.exportPDF(tabPanel));
//		exportHTML.setToolTip("Export To HTML");
//		exportHTML.setEnabled(false);
//		toolbar.add(exportHTML);
//		toolbar.add(new SeparatorToolItem());
		
		return toolbar;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public IconButton getCreateChart() {
		return createChart;
	}

	public IconButton getCreateMap() {
		return createMap;
	}

	public IconButton getExport() {
		return export;
	}

	public IconButton getExportExcel() {
		return exportExcel;
	}

	public IconButton getExportHTML() {
		return exportHTML;
	}

	public IconButton getSaveAs() {
		return saveAs;
	}

	public IconButton getShowManual() {
		return showManual;
	}
	
}