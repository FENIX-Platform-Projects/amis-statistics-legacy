package org.fao.fenix.web.modules.bangladesh.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class BangladeshWindow extends FenixWindow {

	private BangladeshTabPanel bangladeshTabPanel;
	
	public BangladeshWindow() {
		bangladeshTabPanel = new BangladeshTabPanel(this);
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();	
//		this.getWindow().hide();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		bangladeshTabPanel.build(this);
		getCenter().add(bangladeshTabPanel.getTabPanel());
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize("400px", "150px");
		getWindow().setHeading("Fortnightly Foodgrain Outlook");
		setCollapsible(true);
		getWindow().setResizable(false);
	}

	public BangladeshTabPanel getBangladeshTabPanel() {
		return bangladeshTabPanel;
	}

	public void setBangladeshTabPanel(BangladeshTabPanel bangladeshTabPanel) {
		this.bangladeshTabPanel = bangladeshTabPanel;
	}
	
	
	
}