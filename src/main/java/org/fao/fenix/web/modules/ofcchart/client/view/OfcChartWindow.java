package org.fao.fenix.web.modules.ofcchart.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;

public class OfcChartWindow extends FenixWindow {
	
private OfcChartTabPanel tabPanel;
	
	private OfcChartFilterPanel filterPanel;
	
	private VerticalPanel wrapper;
	
	private ChartWidget chart;
	
	private ChartData chartData;
	
//	private SingleDatasetPanel singleDatasetPanel;
	
	private OfcChartToolbar toolbar;

	public OfcChartWindow() {
		tabPanel = new OfcChartTabPanel();
		filterPanel = new OfcChartFilterPanel();
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		wrapper.setBorders(true);
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setWidth("300px");
		wrapper.setWidth("580px");
		chart = new ChartWidget();
		chartData = new ChartData();
//		singleDatasetPanel = new SingleDatasetPanel();
//		olapMapWindow = new OlapMapWindow();
	}
	
	public void build() {
//		buildWestPanel();
		buildCenterPanel();
		buildEastPanel();
		buildWestPanel();
	
		format();
		show();
		tabPanel.getSingleDatasetPanel().getAddDataset().addSelectionListener(OfcChartController.addDataset(this));
		filterPanel.getAddFilterButton().addSelectionListener(OfcChartController.addFilter(this, filterPanel, tabPanel.getSingleDatasetPanel().getDataSource()));
		filterPanel.getRemoveFilterButton().addSelectionListener(OfcChartController.removeFilter(filterPanel));
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		wrapper.add(chart);
		getCenter().setHeading("Chart");
		toolbar = new OfcChartToolbar();
		getCenter().setTopComponent(toolbar.build(this));
		getCenter().add(wrapper);
//		getCenterData().setSize(500);
		addCenterPartToWindow();
	}
	
	private void buildWestPanel() {
		setWestProperties();
		fillWestPart(tabPanel.build(this));
//		fillWestPart(singleDatasetPanel.build(this));
//		getWestData().setSize(350);
		getWest().setHeading(BabelFish.print().controller());
		getWest().expand();
	}
	
	private void buildEastPanel() {
		setEastProperties();
//		fillEastPart(filterPanel.build());
//		getEastData().setSize(300);
		getEast().setHeading(BabelFish.print().filters());
		getEast().collapse();
	}
	
	private void format() {
		setSize("800px", "600px");
		getWindow().setHeading("Chart Wizard");
		setCollapsible(true);
		getWindow().setResizable(true);
		getEast().collapse();
		getWestData().setSize(785);
		getWest().expand();
	}
	
	public OfcChartTabPanel getTabPanel() {
		return tabPanel;
	}


	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public OfcChartFilterPanel getFilterPanel() {
		return filterPanel;
	}

	public void setFilterPanel(OfcChartFilterPanel filterPanel) {
		this.filterPanel = filterPanel;
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

//	public SingleDatasetPanel getSingleDatasetPanel() {
//		return singleDatasetPanel;
//	}
	
	
}