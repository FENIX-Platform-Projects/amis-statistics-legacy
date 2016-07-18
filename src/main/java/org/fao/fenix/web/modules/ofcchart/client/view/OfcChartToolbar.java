package org.fao.fenix.web.modules.ofcchart.client.view;

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartController;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartViewerController;
import org.fao.fenix.web.modules.ofcchart.client.view.viewer.OfcChartViewer;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class OfcChartToolbar {

	private ToolBar toolbar;
	
	private IconButton refresh;
	
	private IconButton modifyChart;
	
	private IconButton hideParameters;
	
	private IconButton saveAs;
	
	private IconButton smallerCharts;
	
	private IconButton normalCharts;
	
	private IconButton resizeCharts;
	
	private IconButton export;
	
	public OfcChartToolbar() {
		toolbar = new ToolBar();
	}
	
	public ToolBar build(OfcChartWindow window) {
		// reset
		refresh = new IconButton("refresh", OfcChartController.refreshChart(window));
		refresh.setToolTip("Refresh");
		toolbar.add(refresh);
		toolbar.add(new SeparatorToolItem());
		
		// reset
		modifyChart = new IconButton("chartEdit"/*, OfcChartController.chartFormat(window)*/);
		modifyChart.setToolTip("Chart Format");
		toolbar.add(modifyChart);
		toolbar.add(new SeparatorToolItem());
		
		// show flat data
		hideParameters = new IconButton("showHidePar"/*, OlapController.showFlatData(window)*/);
		hideParameters.setToolTip("Hide Paramters");
		toolbar.add(hideParameters);
		toolbar.add(new SeparatorToolItem());

		// export
		saveAs = new IconButton("save", OfcChartController.save(window));
		saveAs.setToolTip("Save Image");
//		saveAs.setEnabled(false);
		toolbar.add(saveAs);
		toolbar.add(new SeparatorToolItem());
		
		// export
		export = new IconButton("sendToPdf"/*, OlapController.exportPDF(window)*/);
		export.setToolTip(BabelFish.print().exportPdf());
//		export.setEnabled(false);
		toolbar.add(export);
		toolbar.add(new SeparatorToolItem());

		return toolbar;
	}
	
	public ToolBar build(OfcChartViewer window) {
		// reset
		refresh = new IconButton("refresh", OfcChartViewerController.refreshChart(window));
		refresh.setToolTip("Refresh");
		toolbar.add(refresh);
		toolbar.add(new SeparatorToolItem());
		
		// reset
		modifyChart = new IconButton("chartEdit", OfcChartViewerController.chartFormat(window));
		modifyChart.setToolTip("Chart Format");
		toolbar.add(modifyChart);
		toolbar.add(new SeparatorToolItem());
		
		// show flat data
		hideParameters = new IconButton("showHidePar"/*, OfcChartFormatController.showFlatData(window)*/);
		hideParameters.setToolTip("Hide Paramters");
		toolbar.add(hideParameters);
		toolbar.add(new SeparatorToolItem());

		// export
		saveAs = new IconButton("save", OfcChartViewerController.save(window));
		saveAs.setToolTip("Save Image");
//		saveAs.setEnabled(false);
		toolbar.add(saveAs);
		toolbar.add(new SeparatorToolItem());
		
		// export
		export = new IconButton("sendToPdf"/*, OfcChartFormatController.exportPDF(window)*/);
		export.setToolTip(BabelFish.print().exportPdf());
//		export.setEnabled(false);
		toolbar.add(export);
		toolbar.add(new SeparatorToolItem());

		return toolbar;
	}
	
	public ToolBar build(FSATMISTabPanel window) {
	

		// export
		saveAs = new IconButton("save", OfcChartController.save(window.getChartPanel()));
		saveAs.setToolTip("Save Image");
		toolbar.add(saveAs);
		toolbar.add(new SeparatorToolItem());
		
		smallerCharts = new IconButton("zoomOutBirt", OfcChartController.resizeChartsToSmall(window.getChartPanel()));
		smallerCharts.setToolTip("Resize charts to smaller size");
		toolbar.add(smallerCharts);
		toolbar.add(new SeparatorToolItem());
		
		normalCharts = new IconButton("zoomInBirt", OfcChartController.resizeChartsToNormal(window.getChartPanel()));
		normalCharts.setToolTip("Resize charts to normal size");
		toolbar.add(normalCharts);
		toolbar.add(new SeparatorToolItem());
		
		resizeCharts = new IconButton("chartEdit", OfcChartController.formatCharts(window.getChartPanel()));
		resizeCharts.setToolTip("Resize charts to custom size");
		toolbar.add(resizeCharts);
		


		return toolbar;
	}
	
	

	public ToolBar getToolbar() {
		return toolbar;
	}

	public IconButton getExport() {
		return export;
	}
	
	public IconButton getsaveAs() {
		return saveAs;
	}
	
}