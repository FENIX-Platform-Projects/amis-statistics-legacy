package org.fao.fenix.web.modules.edi.client.view.fewsnet;

import org.fao.fenix.web.modules.edi.client.control.FEWSNETController;
import org.fao.fenix.web.modules.edi.client.view.EDIWindow;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;

public class FEWSNETWindow extends EDIWindow {

	private FEWSNETTabPanel fewsnetTabPanel;
	
	private FEWSNETButtonsPanel fewsnetButtonsPanel;
	
	private Long datasetID;
	
	public FEWSNETWindow() {
		super();
		fewsnetTabPanel = new FEWSNETTabPanel();
		fewsnetButtonsPanel = new FEWSNETButtonsPanel();
		fewsnetTabPanel.getPanel().setSelection(fewsnetTabPanel.getDataPanel().getTabItem());
		this.setButtonsPanel(fewsnetButtonsPanel);
		this.setTabPanel(fewsnetTabPanel);
	}
	
	@Override
	public void build(EDISettings datasource) {
		super.build(datasource);
		fewsnetButtonsPanel.getGetZipButton().addSelectionListener(FEWSNETController.getZIPs(this));
		fewsnetButtonsPanel.getImportZipButton().addSelectionListener(FEWSNETController.importZIPs(this));
		fewsnetTabPanel.getDataPanel().getHispaniolaNDVIList().addChangeHandler(FEWSNETController.oneAtATime(this, "HISPANIOLA"));
		fewsnetTabPanel.getDataPanel().getHispaniolaNDVIDAList().addChangeHandler(FEWSNETController.oneAtATime(this, "HISPANIOLA_DA"));
		fewsnetTabPanel.getDataPanel().getHispaniolaNDVIPYDList().addChangeHandler(FEWSNETController.oneAtATime(this, "HISPANIOLA_PYD"));
		fewsnetTabPanel.getDataPanel().getHaitiNDVIList().addChangeHandler(FEWSNETController.oneAtATime(this, "HAITI"));
		fewsnetTabPanel.getDataPanel().getHaitiNDVIDAList().addChangeHandler(FEWSNETController.oneAtATime(this, "HAITI_DA"));
		fewsnetTabPanel.getDataPanel().getHaitiNDVIPYDList().addChangeHandler(FEWSNETController.oneAtATime(this, "HAITI_PYD"));
	}

	public FEWSNETTabPanel getFewsnetTabPanel() {
		return fewsnetTabPanel;
	}

	public FEWSNETButtonsPanel getFewsnetButtonsPanel() {
		return fewsnetButtonsPanel;
	}

	public Long getDatasetID() {
		return datasetID;
	}
		
}