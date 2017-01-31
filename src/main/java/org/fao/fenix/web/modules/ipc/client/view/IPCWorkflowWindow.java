package org.fao.fenix.web.modules.ipc.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class IPCWorkflowWindow extends FenixWindow {

	private IPCTabPanel ipcTabPanel;
	
	public IPCWorkflowWindow() {
		ipcTabPanel = new IPCTabPanel();
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();	
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(ipcTabPanel.build(this));
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
	private void format() {
		this.getWindow().setResizable(false);
		setSize("500px", "670px");
		getWindow().setHeading("IPC: Integrated Food Security Phase Classification");
		setCollapsible(true);
	}

	
	
}