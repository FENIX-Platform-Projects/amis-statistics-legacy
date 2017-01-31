package org.fao.fenix.web.modules.edi.client.view;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public abstract class EDIButtonsPanel {

	private Button getExcelButton;
	
	private Button getExcelWithCodesButton;
	
	private Button importDatasetButton;
	
	private HorizontalPanel panel = new HorizontalPanel();
	
	private final int SPACING = 5;
	
	public EDIButtonsPanel() {
		getExcelButton = new Button("Get Excel");
		getExcelButton.setIconStyle("sendToExcel");
		getExcelWithCodesButton = new Button("Get Excel With Labels");
		getExcelWithCodesButton.setIconStyle("codingSystemIcon");
		importDatasetButton = new Button("Import Dataset");
		importDatasetButton.setIconStyle("uploadData");
	}
	
	public HorizontalPanel build() {
		panel.setSpacing(SPACING);
		panel.add(getExcelButton);
		panel.add(getExcelWithCodesButton);
		panel.add(importDatasetButton);
		return panel;
	}

	public Button getGetExcelButton() {
		return getExcelButton;
	}

	public Button getImportDatasetButton() {
		return importDatasetButton;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}

	public Button getGetExcelWithCodesButton() {
		return getExcelWithCodesButton;
	}

	public int getSPACING() {
		return SPACING;
	}
	
}