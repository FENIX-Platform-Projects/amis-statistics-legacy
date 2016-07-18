package org.fao.fenix.web.modules.wcct.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.wcct.client.control.WCCTController;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class WCCTTabPanel {

	private TabPanel tabPanel;
	
	private MapWestPanel mapWestPanel;
	
	private TableWestPanel tableWestPanel;
	
	private ChartWestPanel chartWestPanel;
	
	
	private ContentPanel tableCenterPanel;
	
	private ContentPanel chartCenterPanel;
	
	private TabItem mapTabItem;
	
	private TabItem tableTabItem;
	
	private TabItem chartTabItem;
	
	
	public WCCTTabPanel() {
		tabPanel = new TabPanel();
		mapTabItem = new TabItem(BabelFish.print().mapTabItem());
		tableTabItem = new TabItem(BabelFish.print().tableTabItem());
		chartTabItem = new TabItem(BabelFish.print().chartTabItem());
		mapWestPanel = new MapWestPanel();
		tableWestPanel = new TableWestPanel();
		chartWestPanel = new ChartWestPanel();
		mapTabItem.disable();
//		tabPanel.setSelection(tableTabItem);
		tabPanel.setSelection(chartTabItem);
	
	}
	
	public TabPanel build(final WCCTTool wcctTool) {
//		mapTabItem.add(mapWestPanel.build(wcctTool));
		tableTabItem.setSize(250,390);
		chartTabItem.setSize(250,390);
		chartTabItem.add(chartWestPanel.build(wcctTool));
		tableTabItem.add(tableWestPanel.build(wcctTool));	
//		tabPanel.add(mapTabItem);

		tabPanel.add(chartTabItem);
		tabPanel.add(tableTabItem);
		
		chartTabItem.addListener(Events.Select, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				System.out.println("clicked chartTabItem");
				wcctTool.getCenter().removeAll();
				wcctTool.getCenter().add(wcctTool.getChartCenterPanel().getPanel());
				wcctTool.getCenter().layout();
			}
		});
		
		tableTabItem.addListener(Events.Select, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				System.out.println("clicked chartTabItem");
				wcctTool.getCenter().removeAll();
				wcctTool.getCenter().add(wcctTool.getTableCenterPanel().getPanel());
				wcctTool.getCenter().layout();
			}
		});
		
		initialize(wcctTool);
		return tabPanel;
	}
	
	private void initialize(final WCCTTool wcctTool) {
		String selectedCountryCode = chartWestPanel.getCountries().getValue(chartWestPanel.getCountries().getSelectedIndex());
		WCCTController.getCropCalendarAction(selectedCountryCode, wcctTool);
	}
	

	public TabPanel getTabPanel() {
		return tabPanel;
	}

}
