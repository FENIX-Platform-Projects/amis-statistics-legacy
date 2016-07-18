package org.fao.fenix.web.modules.ofcchart.client.view.viewer;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.view.OfcChartToolbar;
import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcChartWizard;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;

public class OfcChartViewer extends FenixWindow {
	
	private OfcChartToolbar toolbar;
	
	private VerticalPanel wrapper;
	
	
	
	
	private ChartWidget chart;
	
	private ChartData chartData;
	
	private Button apply;
	
	private OfChartViewerControllerPanel viewerController;
	
	public OfcChartViewer(OfcChartWizard wizard) {
		wrapper = new VerticalPanel();
		wrapper.setBorders(true);
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setWidth(750);
		wrapper.setHeight(550);
		chart = new ChartWidget();
		chartData = new ChartData();
		apply = new Button(BabelFish.print().apply());
		apply.setSize(35, 25);
		viewerController = new OfChartViewerControllerPanel();
	}
	
	public void build(OfcChartWizard wizard) {
		buildCenterPanel();
		buildWestPanel(wizard);
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		wrapper.add(chart);
//		fillCenterPart(chartViewerShow.build(this));
		fillCenterPart(wrapper);
		toolbar = new OfcChartToolbar();
		getCenter().setTopComponent(toolbar.build(this));
		getCenter().add(wrapper);
//		chartViewerShow.build(this);
		getCenter().setCollapsible(true);
		addCenterPartToWindow();
//		getCenter().setExpanded(true);
	}
	
	private void buildWestPanel(OfcChartWizard wizard) {
		setWestProperties();
		getWest().setLayout(new AccordionLayout());
		getWest().setHeading(BabelFish.print().controller());

		viewerController.build(this, wizard);	
		addWestPartToWindow();
		getWest().setCollapsible(true);
		getWest().setSize(200, 550);
//		getWest().setExpanded(false);
		getWest().setBottomComponent(apply);
//		getWest().collapse();
//		getWest().expand();
	}
	
	
	private void format() {
		setSize("800px", "600px");
		getWindow().setHeading("Chart Viewer");
		getCenter().setHeaderVisible(false);
//		getWest().setHeaderVisible(false);
		setCollapsible(true);
		getWindow().setResizable(true);
	}



	public void setToolbar(OfcChartToolbar toolbar) {
		this.toolbar = toolbar;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public void setWrapper(VerticalPanel wrapper) {
		this.wrapper = wrapper;
	}

	public ChartWidget getChart() {
		return chart;
	}

	public void setChart(ChartWidget chart) {
		this.chart = chart;
	}

	public ChartData getChartData() {
		return chartData;
	}

	public void setChartData(ChartData chartData) {
		this.chartData = chartData;
	}

	

	
	
	
	
}