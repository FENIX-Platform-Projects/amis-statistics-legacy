package org.fao.fenix.web.modules.datasetupload.client.view;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;

public class DatasetUploaderView {

	final Window window;
	//FormPanel and point it at a service.
	final FormPanel form;
	final HTML message = new HTML();
	Button submit;
	// Create a FileUploadMetadata widget
	final FileUpload uploadMetadata;
	// Create a FileUploadDataset widget
	final FileUpload upload;
	
	public HTML getMessage() {
		return message;
	}

	public FileUpload getUploadMetadata() {
		return uploadMetadata;
	}

	public FileUpload getUpload() {
		return upload;
	}

	public FormPanel getForm() {
		return form;
	}

	public Button getSubmit() {
		return submit;
	}

	public DatasetUploaderView(){
		window = new Window();
		window.setHeight(300);
		window.setWidth(400);
		window.isMaximizable();
		window.isMinimizable();
		window.setHeading(BabelFish.print().datasetUploaderForm());
		
		form = new FormPanel();
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getSingleDatasetUploadServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
		form.setAction(urlServletUpload);
		
		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		
		submit = new Button(BabelFish.print().upload());
		
		uploadMetadata = new FileUpload();
		upload = new FileUpload();
		
	}
	
	public void build() {

		
		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		//panel.setWidth("600");
		panel.setSpacing(5);
		form.setWidget(panel);

		panel.add(new HTML("<b>" + BabelFish.print().datasetFile() + "<b>"));
				
		upload.setName("datasetFileItem");
		//upload.setWidth("450");
		panel.add(upload);
		
		panel.add(new HTML("<br><b>" + BabelFish.print().metadataFile() + "<b>"));
		
		
		uploadMetadata.setName("metadataFileItem");
		//uploadMetadata.setWidth("450");
		panel.add(uploadMetadata);

		panel.add(new HTML("<br>"));
		
		submit.setWidth("150");
		panel.add(submit);

		panel.add(message);

		window.add(form);

		window.show();
	}
}
