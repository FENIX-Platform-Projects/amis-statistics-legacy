package org.fao.fenix.web.modules.edi.client.view.faostat;

import org.fao.fenix.web.modules.edi.client.view.EDITabPanel;

public class FAOStatTabPanel extends EDITabPanel {

	private FAOStatInfoPanel infoPanel;
	
	private FAOStatSettingsPanel settingsPanel;
	
	private FAOStatDataPanel dataPanel;
	
	private FAOStatCodesPanel codesPanel;
	
	private FAOStatFENIXPanel fenixPanel;
	
	public FAOStatTabPanel() {
		super();
		infoPanel = new FAOStatInfoPanel("Info", "info");
		this.setInfoPanel(infoPanel);
		settingsPanel = new FAOStatSettingsPanel("Settings", "gear");
		this.setSettingsPanel(settingsPanel);
		dataPanel = new FAOStatDataPanel("Data", "datasource");
		this.setDataPanel(dataPanel);
		codesPanel = new FAOStatCodesPanel("Codes", "codingSystemIcon");
		codesPanel.getTabItem().setEnabled(false);
		this.setCodesPanel(codesPanel);
		fenixPanel = new FAOStatFENIXPanel("Workstation", "wand");
		fenixPanel.getTabItem().setEnabled(false);
		this.setFenixPanel(fenixPanel);
	}
	
	public FAOStatSettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	public FAOStatInfoPanel getInfoPanel() {
		return infoPanel;
	}

	public FAOStatDataPanel getDataPanel() {
		return dataPanel;
	}

	public FAOStatCodesPanel getCodesPanel() {
		return codesPanel;
	}

	public FAOStatFENIXPanel getFenixPanel() {
		return fenixPanel;
	}
	
}