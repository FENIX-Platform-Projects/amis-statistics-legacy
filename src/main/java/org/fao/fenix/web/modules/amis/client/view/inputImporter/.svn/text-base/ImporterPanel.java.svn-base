package org.fao.fenix.web.modules.amis.client.view.inputImporter;

import org.fao.fenix.web.modules.amis.client.control.inputImporter.ImporterControl;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class ImporterPanel {
	
	private ContentPanel panel;

	private Button cancelButton;

	private Button uploadButton;
	
	private ImportFormPanel importFormPanel;

	
	public ImporterPanel() {
		panel = new ContentPanel();
		//panel.setLayout(
		panel.setHeaderVisible(false);
		cancelButton = new Button("Cancel");
		uploadButton = new Button("Upload");
		importFormPanel = new ImportFormPanel();
	}
	
	public ContentPanel build() {
		panel.add(importFormPanel.buildForm());
		// create buttons
		addButtons();
		// return panel
		return panel;
	}
	
	private void addButtons() {
		//prevButton.setEnabled(false);
		//cancelButton.addSelectionListener(ExcelImporterController.next(this));
		uploadButton.addSelectionListener(ImporterControl.uploadListener(this));
		panel.addButton(cancelButton);
		panel.addButton(uploadButton);
		panel.setButtonAlign(HorizontalAlignment.CENTER);
	}
	
	public ImportFormPanel getImportFormPanel() {
		return importFormPanel;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public Button getUploadButton() {
		return uploadButton;
	}
	
	public ContentPanel getPanel() {
		return panel;
	}
}
