package org.fao.fenix.web.modules.shapefileupload.client.view;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
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

public class ShapeFileUploader {

	private Window uploaderWindow;
	
	@SuppressWarnings("deprecation")
	public void build() {
		// window to hold uploader
		uploaderWindow = new Window();
		uploaderWindow.setAutoWidth(false);
		uploaderWindow.setSize("500", "250");
		uploaderWindow.setHeading(BabelFish.print().ShapeFileUploaderForm());
		//DOM.setStyleAttribute(window.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);

		// Create a FormPanel and point it at a service.
		final FormPanel form = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getShapeUploadSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
		form.setAction(urlServletUpload);

		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		panel.setWidth("550");
		form.setWidget(panel);

		panel.add(new HTML("<table cellpadding='5' cellspacing='5'><tr><td><b>" + BabelFish.print().ShapeFile() + "</b></td></tr>"));
				
		
		// Create a FileUpload widget.
		final FileUpload upload = new FileUpload();
		upload.setName("shapeFileItem");
		upload.setWidth("250");
		panel.add(new HTML("<tr><td>"));
		panel.add(upload);
		panel.add(new HTML("</td></tr>"));

		panel.add(new HTML("<tr><td><br/></td></tr>"));

		// Create a FileUpload widget for metedata file
		final FileUpload uploadMd = new FileUpload();
		uploadMd.setName("metadataFileItem");
		uploadMd.setWidth("250");
		panel.add(new HTML("<tr><td><b>" + BabelFish.print().metadataFile() + "</b></td></tr>"));
		panel.add(new HTML("<tr><td>"));
		panel.add(uploadMd);
		panel.add(new HTML("</td></tr>"));

		final HTML message = new HTML();
		
		// Add a 'submit' button.
		Button submit = new Button(BabelFish.print().process());
//		submit.addSelectionListener(ShapeFileUploaderController.
//
//				new SelectionListener<ComponentEvent>() {

		submit.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				String layerFile = upload.getFilename();
				String mdFile    = uploadMd.getFilename();

				if (layerFile.isEmpty()) {
					FenixAlert.error(BabelFish.print().error(),
									 "Missing layer file");  // fixme i18n
				} else if ( ! layerFile.toLowerCase().endsWith(".zip")) {
					FenixAlert.error(BabelFish.print().error(),
									 "Expected a .zip file");  // fixme i18n
				} else if ( ! mdFile.isEmpty() && !mdFile.toLowerCase().endsWith(".xml")) {
					FenixAlert.error(BabelFish.print().error(),
									 "Metadata files must be .xml file");  // fixme i18n
				} else {
					form.submit();
				}
			}
		});
		
		panel.add(new HTML("<tr><td><br/></td></tr>"));

		submit.setWidth("250");
		panel.add(new HTML("<tr><td>"));
		panel.add(submit);
		panel.add(new HTML("</td></tr>"));

		// Add an event handler to the form.
		form.addFormHandler(new FormHandler() {
			
			/**
			 * Fired just before submit form, used to perform checks.
			 */
			public void onSubmit(FormSubmitEvent event) {
//				FenixAlert.info("You are going to upload {0} file.", upload.getFilename());
//				if (shapeFile.getName().length() == 0 || shapeFile.getFilename().equals("")) {
//					FenixAlert.error(I18N.print().noDataset(), I18N.print().errorSelectDataset());
//					event.setCancelled(true);
//				}
			}

			/**
			 * Fired on submission complete, could be used as a test.
			 */
			public void onSubmitComplete(FormSubmitCompleteEvent event) {

				// FenixAlert.info("INFO", "["+event.getResults()+"]");	

				int index = 0;
				for (int i = upload.getFilename().length() - 1 ; i >= 0 ; i--) {
					if ((upload.getFilename().charAt(i) == '/') || (upload.getFilename().charAt(i) == '\\')) {
						index = i;
						break;
					}
				}

				String fileName = upload.getFilename().substring(index, upload.getFilename().length());

				String results = event.getResults();
				
//				FenixAlert.info("RESULTS", results);
				
				if (results.length() > 0) {
					if (results.indexOf("Exception:") > 0) {
						int start = results.indexOf("Exception:")+10;
						int end = results.substring(start).indexOf("org") + start;
						results = results.substring(start, end);
						results = "<b>Errors when uploading shapefie file archive </b>[" + fileName + "]: <br/><br/>" + results;
//						com.google.gwt.user.client.Window.alert("["+results+"]");	
					} else {
						String crs    = null;
						String[] bbox = null;
						String[] atts = null;
						String geom   = null;
						String type   = null;
						String url    = null;
						
						String[] variables = results.split("&");
						for (int v=0; v<variables.length; v++) {
							String[] var = variables[v].split("="); 
							if (var[0].toLowerCase().contains("crs"))
								crs = var[1];
							
							if (var[0].toLowerCase().contains("bbox"))
								bbox = var[1].split(",");
							
							if (var[0].toLowerCase().contains("atts"))
								atts = var[1].split(",");
							
							if (var[0].toLowerCase().contains("geom"))
								geom = var[1];
							
							if (var[0].toLowerCase().contains("type"))
								type = var[1];
							
							if (var[0].toLowerCase().contains("url"))
								url = var[1];
						}
						
						Info.display("ShapeFile Uploaded", "The shapefile has been successfully uploaded.");
						uploaderWindow.close();
						new FinalShape(crs, bbox, atts, geom, type, url).build();
					}
				}
				if(results.equals("")) {
					message.setHTML("<div style='font-weight:bold; color:green'> "+BabelFish.print().successful() + " "+fileName + "</div>");
				} else {
					message.setHTML("<div style='color:red'> " + results + "</div>");
				}
			}

		});

		panel.add(message);
		
		// add form to the shell
		uploaderWindow.add(form);
		
		// finally open window
		uploaderWindow.show();
	}

//	public void buildPanel() {
//		formPanel = new FormPanel();		
//		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
//		String urlShapefileUploadServlet = ep.getShapefileUploadServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
//		formPanel.setAction(urlShapefileUploadServlet);   
//		
//		// set form to use the POST method, and multipart MIME encoding.
//		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
//		formPanel.setMethod(FormPanel.METHOD_POST);
//
//		// Create a panel to hold all of the form widgets.
//		panel = new VerticalPanel();
//		panel.setWidth("230px");
//		formPanel.setWidget(panel);
//
//		panel.add(new HTML("<br/><b> &nbsp; "+I18N.print().ShapeFile()+"</b><br/> &nbsp; "));
//
//		// Create a FileUpload widget.
//		final FileUpload shapeFile = new FileUpload();
//		shapeFile.setName("shapeFileUploadFormElement");
//		shapeFile.setWidth("230px");
//		panel.add(shapeFile);
//
//		panel.add(new HTML("<br/>"));
//
//		// Add a 'submit' button.
//		Button submit = new Button(I18N.print().process(), new ClickListener() {
//			public void onClick(Widget sender) {
//				formPanel.submit();
//			}});
//
//		// Add an event handler to the form.
//		formPanel.addFormHandler(new FormHandler() {
//			public void onSubmit(FormSubmitEvent event) {
//				// This event is fired just before the form is submitted. We can take
//				// this opportunity to perform validation.
//				if (shapeFile.getName().length() == 0 || shapeFile.getFilename().equals("")) {
//					FenixAlert.error(I18N.print().noDataset(), I18N.print().errorSelectDataset());
//					event.setCancelled(true);
//				}
//			}
//
//			public void onSubmitComplete(FormSubmitCompleteEvent event) {
//				// When the form submission is successfully completed, this event is
//				// fired. Assuming the service returned a response of type text/html,
//				// we can get the result text here (see the FormPanel documentation for
//				// further explanation).
//				Window.alert(event.getResults());
//			}
//		});
//
//		RootPanel.get().add(formPanel);
//		
//		panel.add(submit);
//		final HTML message = new HTML();
//
//		// Add an event handler to the form.
//		formPanel.addFormHandler(new FormHandler() {
//			public void onSubmit(FormSubmitEvent event) {
//				FenixAlert.error("onsubmit", "onsubmit");
//			}
//
//			/**
//			 * Fired on submission complete, could be used as a test.
//			 */
//			public void onSubmitComplete(FormSubmitCompleteEvent event) {
//
//				int index = 0;
//				for (int i = shapeFile.getFilename().length() - 1; i >= 0; i--) {
//					if ((shapeFile.getFilename().charAt(i) == '/') || (shapeFile.getFilename().charAt(i) == '\\')) {
//						index = i;
//						break;
//					}
//				}
//
//				String fileName = shapeFile.getFilename().substring(index + 1, shapeFile.getFilename().length());
//
//				message.setHTML("<div style='font-weight:bold; color:green'>"
//						+ I18N.print().successful() + fileName + "</div>");
//				message.setWidth("250px");
//
//			}
//
//		});
//
//		panel.add(message);
//
//		window.add(panel);
//		
//		// Fill & build the form
//		// window.add(center);
//	}
//
//	public void fillCenterPart(Widget widget) {
//		// setCenterProperties();
//		// center.add(widget);
//		// window.add(center);
//	}
	
}
