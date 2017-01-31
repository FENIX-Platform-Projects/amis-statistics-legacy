package org.fao.fenix.web.modules.codingmappingupload.client.view;

import org.fao.fenix.web.modules.codingmappingupload.client.control.CodingMappingUploaderController;
import org.fao.fenix.web.modules.core.client.uploader.view.UploaderWindow;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.google.gwt.core.client.GWT;


public class CodingMappingUploaderWindow extends UploaderWindow {
	
	private FileUploadField dstMetadata;

	public void build() {
		//set the window properties              
		setWindowProperties(BabelFish.print().mappingUploader());

		//create form
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getServiceEntryPointCodingMappingUploader(GWT.getModuleBaseURL(), GWT.getModuleName());
		
		setFormPanel(buildBasicFormPanel(urlServletUpload));

		//set form listeners
		getFormPanel().addListener(Events.BeforeSubmit, CodingMappingUploaderController.getBeforeSubmitListener(this));
		getFormPanel().addListener(Events.Submit, CodingMappingUploaderController.getAfterSubmitFormListener(this));

		buildCodingUploadForm();

		getCenter().add(getFormPanel());

		show();
	}
	
	private void buildCodingUploadForm() {
		addBasicFileUploadFields();
		addSecondMetadata();
		addDelimiterCombo();
		addCodingUploadPolicyCombo();
		addRemarksArea();		
		addButtons();
		addUploadButtonListener();

	}
	
	private void addSecondMetadata(){
		getMetadata().setFieldLabel(BabelFish.print().associatedSourceMetadataFile()); 
		dstMetadata = new FileUploadField();  
		dstMetadata.setButtonOffset(10);
		dstMetadata.setAllowBlank(true);  
		dstMetadata.setFieldLabel(BabelFish.print().associatedDestinationMetadataFile()); 
		dstMetadata.setLabelStyle("font-weight:bold;");
		dstMetadata.setName("metadataFileName");
		dstMetadata.setValue(" "); //fix for Firefox display, when using metadata.getValue() == null
		getLeftColumnContainer().add(dstMetadata, getFormData());
	}
	
	private void addUploadButtonListener() {
		getUploadButton().addSelectionListener(CodingMappingUploaderController.getUploadListener(this));
	}

	public FileUploadField getDstMetadata() {
		return dstMetadata;
	}
	
	
}