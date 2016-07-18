package org.fao.fenix.web.modules.bangladesh.client.view;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class BangladeshTabPanel {

	private TabPanel tabPanel;
	
	private BangladeshNewOutlookTab bangladeshNewOutlookTab;
	
	private BangladeshOldOutlookTab bangladeshOldOutlookTab;
	
	private BangladeshPreviewTab bangladeshPreviewTab;
	
	private TabItem newOutlookTab;
	
	private TabItem oldOutlookTab;
	
	private TabItem previewTab;
	
	public BangladeshTabPanel(BangladeshWindow bangladeshWindow) {
		tabPanel = new TabPanel();
		newOutlookTab = new TabItem("Create New Outlook");
		oldOutlookTab = new TabItem("Open Existing Outlook");
		previewTab = new TabItem("Report Preview");
		bangladeshNewOutlookTab = new BangladeshNewOutlookTab(bangladeshWindow);
		bangladeshOldOutlookTab = new BangladeshOldOutlookTab();
		bangladeshPreviewTab = new BangladeshPreviewTab(bangladeshWindow);
	}
	
	public TabPanel build(BangladeshWindow bangladeshWindow) {
		newOutlookTab.add(bangladeshNewOutlookTab.build(bangladeshWindow));
		previewTab.add(bangladeshPreviewTab.build(bangladeshWindow));
		tabPanel.add(newOutlookTab);
//		tabPanel.add(oldOutlookTab);
//		tabPanel.add(previewTab);
		return tabPanel;
	}
	
	

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public TabItem getPreviewTab() {
		return previewTab;
	}

	public void setPreviewTab(TabItem previewTab) {
		this.previewTab = previewTab;
	}

	public BangladeshPreviewTab getBangladeshPreviewTab() {
		return bangladeshPreviewTab;
	}

	public TabItem getNewOutlookTab() {
		return newOutlookTab;
	}

	public TabItem getOldOutlookTab() {
		return oldOutlookTab;
	}
	
}