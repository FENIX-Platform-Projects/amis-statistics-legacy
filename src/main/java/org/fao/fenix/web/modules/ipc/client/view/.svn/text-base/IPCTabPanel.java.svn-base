package org.fao.fenix.web.modules.ipc.client.view;


import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class IPCTabPanel {

	private TabPanel tabPanel;
	
	private CreateIPCWorkflowTab createIPCWorkflowTab;
	
	private ShowIPCWorkflowTab showIPCWorkflowTab;
	
	private ViewerIPCWorkflowTab viewerIPCWorkflowTab;
	
	private TabItem createIPCWorkflowTabItem;
	
	private TabItem showIPCWorkflowTabItem;
	
	private TabItem viewerIPCWorkflowTabItem;
	
	public IPCTabPanel() {
		tabPanel = new TabPanel();
		createIPCWorkflowTabItem = new TabItem("Create New WORKFLOW");
		createIPCWorkflowTab = new CreateIPCWorkflowTab();
		showIPCWorkflowTabItem = new TabItem("Load Your WORKFLOW");
		showIPCWorkflowTab = new ShowIPCWorkflowTab();
		viewerIPCWorkflowTabItem = new TabItem("View WORKFLOW");
		viewerIPCWorkflowTab = new ViewerIPCWorkflowTab();
	}

	public TabPanel build(IPCWorkflowWindow window) {
		createIPCWorkflowTabItem.add(createIPCWorkflowTab.build(window));
		showIPCWorkflowTabItem.add(showIPCWorkflowTab.build(window));
		viewerIPCWorkflowTabItem.add(viewerIPCWorkflowTab.build());
		tabPanel.add(createIPCWorkflowTabItem);
		tabPanel.add(showIPCWorkflowTabItem);
		tabPanel.add(viewerIPCWorkflowTabItem);
		return tabPanel;
	}

	
}