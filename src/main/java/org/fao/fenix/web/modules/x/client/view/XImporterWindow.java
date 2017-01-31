package org.fao.fenix.web.modules.x.client.view;

import org.fao.fenix.web.modules.core.client.uploader.view.UploaderWindow;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.x.client.control.XImporterController;

import com.extjs.gxt.ui.client.event.Events;
import com.google.gwt.core.client.GWT;

public class XImporterWindow extends UploaderWindow {

	public void build() {
		setWindowProperties("X Importer");
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getXImporterServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
		setFormPanel(buildBasicFormPanel(urlServletUpload));
		getFormPanel().addListener(Events.BeforeSubmit, XImporterController.getBeforeSubmitListener(this));
		getFormPanel().addListener(Events.Submit, XImporterController.getAfterSubmitFormListener(this));
		buildDatasetUploadForm();
		getCenter().add(getFormPanel());
		getWindow().setHeight("350px");
		show();
	}
	
	private void buildDatasetUploadForm() {
		addBasicFileUploadFields();
		addRemarksArea();
		addButtons();
		addUploadButtonListener();
	}
	
	@Override
	protected void addBasicFileUploadFields() {
		addFileField();
	}
	
	private void addUploadButtonListener() {
		getUploadButton().addSelectionListener(XImporterController.getUploadListener(this));
	}
	
}
