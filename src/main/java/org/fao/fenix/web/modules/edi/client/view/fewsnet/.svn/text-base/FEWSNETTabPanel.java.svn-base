package org.fao.fenix.web.modules.edi.client.view.fewsnet;

import org.fao.fenix.web.modules.edi.client.view.EDITabPanel;

public class FEWSNETTabPanel extends EDITabPanel {

	private FEWSNETInfoPanel infoPanel;
	
	private FEWSNETSettingsPanel settingsPanel;
	
	private FEWSNETDataPanel dataPanel;
	
	private FEWSNETCodesPanel codesPanel;
	
	private FEWSNETFENIXPanel fenixPanel;
	
	public FEWSNETTabPanel() {
		super();
		infoPanel = new FEWSNETInfoPanel("Info", "info");
		this.setInfoPanel(infoPanel);
		settingsPanel = new FEWSNETSettingsPanel("Settings", "gear");
		this.setSettingsPanel(settingsPanel);
		dataPanel = new FEWSNETDataPanel("Data", "datasource");
		this.setDataPanel(dataPanel);
		codesPanel = new FEWSNETCodesPanel("Codes", "codingSystemIcon");
		codesPanel.getTabItem().setEnabled(false);
		this.setCodesPanel(codesPanel);
		fenixPanel = new FEWSNETFENIXPanel("Workstation", "wand");
		fenixPanel.getTabItem().setEnabled(false);
		this.setFenixPanel(fenixPanel);
	}

	public FEWSNETInfoPanel getInfoPanel() {
		return infoPanel;
	}

	public FEWSNETSettingsPanel getSettingsPanel() {
		return settingsPanel;
	}

	public FEWSNETDataPanel getDataPanel() {
		return dataPanel;
	}

	public FEWSNETCodesPanel getCodesPanel() {
		return codesPanel;
	}

	public FEWSNETFENIXPanel getFenixPanel() {
		return fenixPanel;
	}
}