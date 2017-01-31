package org.fao.fenix.web.modules.edi.client.view;

import com.extjs.gxt.ui.client.widget.TabPanel;

public abstract class EDITabPanel {

	private TabPanel panel = new TabPanel();
	
	private EDIPanel infoPanel;
	
	private EDIPanel dataPanel;
	
	private EDIPanel codesPanel;
	
	private EDIFENIXPanel fenixPanel;
	
	private EDISettingsPanel settingsPanel;
	
	public TabPanel build() {
		this.getPanel().add(getInfoPanel().build());
		this.getPanel().add(getDataPanel().build());
		this.getPanel().add(getCodesPanel().build());
		this.getPanel().add(getFenixPanel().build());
		this.getPanel().add(getSettingsPanel().build());
		return panel;
	}

	public TabPanel getPanel() {
		return panel;
	}

	public void setPanel(TabPanel panel) {
		this.panel = panel;
	}

	public EDIPanel getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(EDIPanel infoPanel) {
		this.infoPanel = infoPanel;
	}

	public EDIPanel getDataPanel() {
		return dataPanel;
	}

	public void setDataPanel(EDIPanel dataPanel) {
		this.dataPanel = dataPanel;
	}

	public EDIPanel getCodesPanel() {
		return codesPanel;
	}

	public void setCodesPanel(EDIPanel codesPanel) {
		this.codesPanel = codesPanel;
	}

	public EDIFENIXPanel getFenixPanel() {
		return fenixPanel;
	}

	public void setFenixPanel(EDIFENIXPanel fenixPanel) {
		this.fenixPanel = fenixPanel;
	}

	public EDISettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	public void setSettingsPanel(EDISettingsPanel settingsPanel) {
		this.settingsPanel = settingsPanel;
	}	
	
}