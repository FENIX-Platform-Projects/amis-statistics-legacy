package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class ChartAxisTabPanel extends ChartDesignerStepPanel  {

	private TabPanel tabPanel;
	
	private Map<Long, ChartAxisPanel> chartXAxisPanelsMap;
	
	private Map<Long, ChartAxisPanel> chartYAxisPanelsMap;
	
	private Map<Long, TabItem> tabItemsMap;
	
	private String TAB_PANEL_WIDTH = "850px";
	
	private String TAB_PANEL_HEIGTH = "600px";
	
	public ChartAxisTabPanel(String suggestion, String width) {
		super(suggestion, width);
		tabPanel = new TabPanel();
		tabPanel.setBodyBorder(false);
		tabPanel.setBorders(false);
		tabPanel.setHeight(TAB_PANEL_HEIGTH);
		tabPanel.setWidth(TAB_PANEL_WIDTH);
		tabPanel.setTabScroll(true);
		chartXAxisPanelsMap = new HashMap<Long, ChartAxisPanel>();
		chartYAxisPanelsMap = new HashMap<Long, ChartAxisPanel>();
		tabItemsMap = new HashMap<Long, TabItem>();
		this.getLayoutContainer().add(buildTabPanel());
	}
	
	public TabPanel buildTabPanel() {
		return tabPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public Map<Long, ChartAxisPanel> getChartXAxisPanelsMap() {
		return chartXAxisPanelsMap;
	}

	public Map<Long, ChartAxisPanel> getChartYAxisPanelsMap() {
		return chartYAxisPanelsMap;
	}

	public Map<Long, TabItem> getTabItemsMap() {
		return tabItemsMap;
	}
	
}