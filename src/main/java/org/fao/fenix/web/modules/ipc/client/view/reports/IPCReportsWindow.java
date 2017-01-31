package org.fao.fenix.web.modules.ipc.client.view.reports;


import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;


public class IPCReportsWindow extends FenixWindow {
	
	private IPCReportsPanel ipcReportPanel;
	
	public IPCReportsWindow() {
		ipcReportPanel = new IPCReportsPanel();
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
		getCenter().add(ipcReportPanel.build());
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize("500px", "670px");
		getWindow().setHeading("IPC: Create IPC Report");
		setCollapsible(true);
		getWindow().setResizable(true);
	}

	
	
}