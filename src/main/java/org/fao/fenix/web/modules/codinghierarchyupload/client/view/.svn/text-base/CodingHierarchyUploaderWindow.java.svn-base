package org.fao.fenix.web.modules.codinghierarchyupload.client.view;


import org.fao.fenix.web.modules.codinghierarchyupload.client.control.CodingHierarchyUploaderController;
import org.fao.fenix.web.modules.core.client.uploader.view.UploaderWindow;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.google.gwt.core.client.GWT;


public class CodingHierarchyUploaderWindow extends UploaderWindow {

	public void build() {
		//set the window properties              
		setWindowProperties(BabelFish.print().hierarchyUploader());

		//create form
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getServiceEntryPointCodingHierarchyUploader(GWT.getModuleBaseURL(), GWT.getModuleName());
		
		setFormPanel(buildBasicFormPanel(urlServletUpload));

		//set form listeners
		getFormPanel().addListener(Events.BeforeSubmit, CodingHierarchyUploaderController.getBeforeSubmitListener(this));
		getFormPanel().addListener(Events.Submit, CodingHierarchyUploaderController.getAfterSubmitFormListener(this));

		buildCodingHierarchyUploadForm();

		getCenter().add(getFormPanel());

		show();
	}
	
	private void buildCodingHierarchyUploadForm() {
		addBasicFileUploadFields();
		addDelimiterCombo();
		addCodingUploadPolicyCombo();
		addRemarksArea();		
		addButtons();
		addUploadButtonListener();

	}
	
	private void addUploadButtonListener() {
		getUploadButton().addSelectionListener(CodingHierarchyUploaderController.getUploadListener(this));
	}
}