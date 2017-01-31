package org.fao.fenix.web.modules.rainfall.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class RainfallTabPanel {

	private TabPanel tabPanel;
	
	private ConfigurationPanel configurationPanel;
	
//	private TreeRegionPanel treeRegionPanel;
	
	private GAULExplorerPanel gaulExplorerPanel;
	
	private SeasonPeriodPanel seasonPeriodPanel;
	
	private OptionPanel optionPanel;
	
	private TabItem configurationTabItem;
	
	private TabItem regionTabItem;
	
	private TabItem periodTabItem;
	
	private TabItem optionTabItem;
	
	public RainfallTabPanel() {
		tabPanel = new TabPanel();
		configurationTabItem = new TabItem(BabelFish.print().favourites());
		regionTabItem = new TabItem(BabelFish.print().region());
		periodTabItem = new TabItem(BabelFish.print().period());
		optionTabItem = new TabItem(BabelFish.print().options());
		configurationPanel = new ConfigurationPanel();
//		treeRegionPanel = new TreeRegionPanel();
		gaulExplorerPanel = new GAULExplorerPanel();
		seasonPeriodPanel = new SeasonPeriodPanel();
		optionPanel = new OptionPanel();
	}
	
	public TabPanel build() {
		configurationTabItem.add(configurationPanel.build());
//		regionTabItem.add(treeRegionPanel.build());
		regionTabItem.add(gaulExplorerPanel.build());
		periodTabItem.add(seasonPeriodPanel.build());
		optionTabItem.add(optionPanel.build());
		tabPanel.add(regionTabItem);
		tabPanel.add(periodTabItem);
		tabPanel.add(optionTabItem);
		tabPanel.add(configurationTabItem);
		return tabPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

//	public TreeRegionPanel getTreeRegionPanel() {
//		return treeRegionPanel;
//	}

	public TabItem getRegionTabItem() {
		return regionTabItem;
	}

	public TabItem getPeriodTabItem() {
		return periodTabItem;
	}

	public SeasonPeriodPanel getSeasonPeriodPanel() {
		return seasonPeriodPanel;
	}

	public OptionPanel getOptionPanel() {
		return optionPanel;
	}

	public ConfigurationPanel getConfigurationPanel() {
		return configurationPanel;
	}

	public GAULExplorerPanel getGaulExplorerPanel() {
		return gaulExplorerPanel;
	}
	
}
