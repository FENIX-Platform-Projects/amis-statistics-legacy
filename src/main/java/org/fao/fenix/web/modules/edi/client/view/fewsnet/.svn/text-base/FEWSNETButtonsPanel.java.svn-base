package org.fao.fenix.web.modules.edi.client.view.fewsnet;

import org.fao.fenix.web.modules.edi.client.view.EDIButtonsPanel;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FEWSNETButtonsPanel extends EDIButtonsPanel {

	private Button getZipButton;
	
	private Button importZipButton;
	
	private String WIDTH = "178px";
	
	public FEWSNETButtonsPanel() {
		super();
		getZipButton = new Button("Get ZIP(s)");
		getZipButton.setWidth(WIDTH);
		getZipButton.setIconStyle("zip");
		importZipButton = new Button("Import ZIP(s)");
		importZipButton.setWidth(WIDTH);
		importZipButton.setIconStyle("uploadData");
	}
	
	@Override
	public HorizontalPanel build() {
		this.getPanel().setSpacing(getSPACING());
		this.getPanel().add(getZipButton);
		this.getPanel().add(importZipButton);
		return this.getPanel();
	}

	public Button getGetZipButton() {
		return getZipButton;
	}

	public Button getImportZipButton() {
		return importZipButton;
	}
	
}