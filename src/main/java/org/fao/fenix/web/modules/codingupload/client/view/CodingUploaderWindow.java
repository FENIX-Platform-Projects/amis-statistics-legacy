package org.fao.fenix.web.modules.codingupload.client.view;


import org.fao.fenix.web.modules.codingupload.client.control.CodingUploaderController;
import org.fao.fenix.web.modules.core.client.uploader.view.UploaderWindow;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.google.gwt.core.client.GWT;



public class CodingUploaderWindow extends UploaderWindow {
	
    
	public void build() {
		//set the window properties              
		setWindowProperties(BabelFish.print().codesUploader());

		//create form
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getServiceEntryPointCodingUploader(GWT.getModuleBaseURL(), GWT.getModuleName());
		
		setFormPanel(buildBasicFormPanel(urlServletUpload));

		//set form listeners
		getFormPanel().addListener(Events.BeforeSubmit, CodingUploaderController.getBeforeSubmitListener(this));
		getFormPanel().addListener(Events.Submit, CodingUploaderController.getAfterSubmitFormListener(this));

		buildCodingUploadForm();

		getCenter().add(getFormPanel());

		show();
	}
	
	private void buildCodingUploadForm() {
		addBasicFileUploadFields();
		addDelimiterCombo();
		addCodingUploadPolicyCombo();
		addRemarksArea();		
		addButtons();
		addUploadButtonListener();

	}
	
	private void addUploadButtonListener() {
		getUploadButton().addSelectionListener(CodingUploaderController.getUploadListener(this));
	}
	
}