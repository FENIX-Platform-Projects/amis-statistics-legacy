package org.fao.fenix.web.modules.core.client.uploader.view.images;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;

public class UploaderImage {
	
	private Window window;
	private final FormPanel form;
	private Button submit;
	private final FileUpload upload;
	private HTML message;
	
	public HTML getMessage() {
		return message;
	}

	public FormPanel getForm() {
		return form;
	}

	public FileUpload getUpload() {
		return upload;
	}

	public Button getSubmit() {
		return submit;
	}

	public Window getWindow() {
		return window;
	}

	public UploaderImage() {
		
		// window to hold uploader
		window = new Window();
		window.setAutoWidth(false);
		window.setSize("500", "250");
		window.setHeading("Image uploader");
		message = new HTML();
		//DOM.setStyleAttribute(window.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);

		// Create a FormPanel and point it at a service.
		form = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getImageUpload(GWT.getModuleBaseURL(), GWT.getModuleName());
//		FenixAlert.alert("URL", urlServletUpload);	
		form.setAction(urlServletUpload);

		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		panel.setWidth("550");
		form.setWidget(panel);

		panel.add(new HTML("<table cellpadding='5' cellspacing='5'><tr><td><b>Image:</b></td></tr>"));
				
		
		// Create a FileUpload widget.
		upload = new FileUpload();
		upload.setName("imageItem");
		upload.setWidth("250");
		panel.add(new HTML("<tr><td>"));
		panel.add(upload);
		panel.add(new HTML("</td></tr>"));

		panel.add(new HTML("<tr><td><br/></td></tr>"));

		// Add a 'submit' button.
		submit = new Button(BabelFish.print().upload());

		panel.add(new HTML("<tr><td><br/></td></tr>"));

		submit.setWidth("250");
		panel.add(new HTML("<tr><td>"));
		panel.add(submit);
		panel.add(new HTML("</td></tr>"));

		panel.add(message);
		
		// add form to the shell
		window.add(form);
		
		// finally open window
		window.show();
	}

}
