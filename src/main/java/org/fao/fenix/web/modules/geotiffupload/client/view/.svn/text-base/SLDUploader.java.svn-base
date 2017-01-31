package org.fao.fenix.web.modules.geotiffupload.client.view;


import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;


public class SLDUploader {

private Window uploaderWindow;
	
	/** TODO: should be checked if the inserted sld, is already in GeoServer
	 * 		  and warn the user that is going to overwrite the an existing sld 
	 */

	@SuppressWarnings("deprecation")
	public void build() {
		// window to hold uploader
		uploaderWindow = new Window();
		uploaderWindow.setAutoWidth(false);
		uploaderWindow.setSize("320", "150");
		uploaderWindow.setHeading(BabelFish.print().SldUploaderForm());
		//DOM.setStyleAttribute(window.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);

		// Create a FormPanel and point it at a service.
		final FormPanel form = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getSLDNewUploaderSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
		form.setAction(urlServletUpload);

		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(8);
		panel.setWidth("400");
		form.setWidget(panel);

//		panel.add(new HTML("<b>" + I18N.print().SldFile()+ "</b>"));
				
		// Create a FileUpload widget.
		HorizontalPanel hpanel = new HorizontalPanel();
		hpanel.setSpacing(5);
		hpanel.add(new HTML("<b>SLD Style Name: <b>"));
		final TextField<String> text = new TextField<String>();
		text.setAllowBlank(false);
		text.setName("sldtext");
		text.setWidth("160px");
		hpanel.add(text);
		panel.add(hpanel);
		
		// Create a FileUpload widget.
		final FileUpload upload = new FileUpload();
		upload.setName("sldFileItem");
		upload.setWidth("120");
		panel.add(upload);
		
		

		
		// Add a 'submit' button.
		Button submit = new Button(BabelFish.print().process());
//		submit.addSelectionListener(ShapeFileUploaderController.
//
//				new SelectionListener<ComponentEvent>() {

		submit.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				String layerFile = upload.getFilename();

				if (layerFile.isEmpty()) {
					FenixAlert.error(BabelFish.print().error(),
									 "Missing layer file");  // fixme i18n
				} else if ( ! layerFile.toLowerCase().endsWith(".sld") && ! layerFile.toLowerCase().endsWith(".xml")) {
					FenixAlert.error(BabelFish.print().error(),
									 "Expected a .sld or . xml file");  // fixme i18n
				} 
				 else {
					form.submit();
				}
			}
		});

		submit.setWidth("120");
		panel.add(submit);

		// Add an event handler to the form.
		form.addFormHandler(new FormHandler() {
			
			/**
			 * Fired just before submit form, used to perform checks.
			 */
			public void onSubmit(FormSubmitEvent event) {
			}

			/**
			 * Fired on submission complete, could be used as a test.
			 */
			public void onSubmitComplete(FormSubmitCompleteEvent event) {
				
				FenixAlert.info("SLD Uploader", "SLD file succesfully uploaded");
				uploaderWindow.close();
			}

		});

//		panel.add(message);
		
		// add form to the shell
		uploaderWindow.add(form);
		
		// finally open window
		uploaderWindow.show();
		
	}

	
}
