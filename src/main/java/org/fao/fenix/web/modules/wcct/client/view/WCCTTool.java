package org.fao.fenix.web.modules.wcct.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class WCCTTool extends FenixToolBase {
	
	private ContentPanel wcctControlPanel;
	
	private WCCTTabPanel wcctTabPanel;
	
//	private ContentPanel wcctCenterPanel;
	
	private TableCenterPanel tableCenterPanel;
	
	private ChartCenterPanel chartCenterPanel;
	
	public WCCTTool() {
		wcctControlPanel = new ContentPanel();
		wcctTabPanel = new WCCTTabPanel();
//		wcctCenterPanel = new ContentPanel();
//		wcctCenterPanel.setHeaderVisible(false);
		wcctControlPanel.setHeaderVisible(false);
		tableCenterPanel = new TableCenterPanel();
		chartCenterPanel = new ChartCenterPanel();
	}
	
	public void build() {
		buildCenterPanel();
		buildWestPanel();
		format();
//		this.getWcctTabPanel().getTabPanel().setSelection(wcctTabPanel.getTabPanel().getItem(1));
		getWest().expand();
	
		
	}
	
	private void buildWestPanel(){
		wcctControlPanel.add(wcctTabPanel.build(this));
		fillWestPart(wcctControlPanel);
		getWestData().setSize(250);
		getWest().setHeading(BabelFish.print().controller());
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
//		getCenter().add(wcctCenterPanel);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
	}
	
	
	private void format() {
		getToolBase().setWidth("100%");
		getToolBase().setHeight("450px");
		getWest().setWidth("30%");
	
		getToolBase().setHeaderVisible(false);
	}

//	public ContentPanel getWcctCenterPanel() {
//		return wcctCenterPanel;
//	}
//
//	public void setWcctCenterPanel(ContentPanel wcctCenterPanel) {
//		this.wcctCenterPanel = wcctCenterPanel;
//	}

	public TableCenterPanel getTableCenterPanel() {
		return tableCenterPanel;
	}

	public void setTableCenterPanel(TableCenterPanel tableCenterPanel) {
		this.tableCenterPanel = tableCenterPanel;
	}

	public WCCTTabPanel getWcctTabPanel() {
		return wcctTabPanel;
	}

	public ChartCenterPanel getChartCenterPanel() {
		return chartCenterPanel;
	}

	public void setChartCenterPanel(ChartCenterPanel chartCenterPanel) {
		this.chartCenterPanel = chartCenterPanel;
	}
	
	
}