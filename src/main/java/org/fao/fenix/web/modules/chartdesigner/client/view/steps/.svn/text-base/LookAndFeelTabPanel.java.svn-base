package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.widget.TabPanel;

public class LookAndFeelTabPanel extends ChartDesignerStepPanel {
	
	private final static String HEIGHT = "500px";
	
	private final static String WIDTH = "850px";
	
	private TabPanel tabPanel;
	
	private LookAndFeelTabItem generalSettings;
	
	private Map<Long, LookAndFeelTabItem> datasetSettings;
	
	private List<Long> datasetIDs;
	
	private List<LookAndFeelTabItem> tabItems;

	public LookAndFeelTabPanel(String suggestion, String width) {
		super(suggestion, width);
		tabPanel = new TabPanel();
		tabPanel.setBodyBorder(false);
		tabPanel.setBorders(false);
		tabPanel.setHeight(HEIGHT);
		tabPanel.setWidth(WIDTH);
		tabPanel.setTabScroll(true);
		generalSettings = new LookAndFeelTabItem("General Settings");
		datasetSettings = new HashMap<Long, LookAndFeelTabItem>();
		datasetIDs = new ArrayList<Long>();
		tabItems = new ArrayList<LookAndFeelTabItem>();
		this.getLayoutContainer().add(buildTabPanel());
	}
	
	private TabPanel buildTabPanel() {
		tabPanel.add(generalSettings.build(null, null, false));
		return tabPanel;
	}
	
	public void addDataset(Long datasetID, String datasetName, boolean calculateYAxisMaxValue) {
		if (!datasetSettings.containsKey(datasetID)) {
			datasetIDs.add(datasetID);
			LookAndFeelTabItem tab = new LookAndFeelTabItem("Y-Axis: " + datasetName);
			tabPanel.add(tab.build(datasetID, this.getDatasetIDs(), calculateYAxisMaxValue));
			datasetSettings.put(datasetID, tab);
			tabItems.add(tab);
		}
	}

	public LookAndFeelTabItem getGeneralSettings() {
		return generalSettings;
	}

	public Map<Long, LookAndFeelTabItem> getDatasetSettings() {
		return datasetSettings;
	}

	public List<Long> getDatasetIDs() {
		return datasetIDs;
	}

	public List<LookAndFeelTabItem> getTabItems() {
		return tabItems;
	}
	
}