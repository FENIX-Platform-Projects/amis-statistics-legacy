package org.fao.fenix.web.modules.geotiffupload.client.view;


import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 * @author $Author: Alessio Fabiani (alessio.fabiani@geo-solutions.it)
 *
 */

public class GeotiffUploader {

	private Window window = null;

	public void build() {
		
		// window to hold uploader
		window = new Window();
		window.setAutoWidth(false);
		window.setSize("500", "250");
		window.setHeading(BabelFish.print().GeotiffUploaderForm());
		//DOM.setStyleAttribute(window.getElement(), "backgroundColor", FenixSettings.TOOLS_COLOR);

		// Create a FormPanel and point it at a service.
		final FormPanel form = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getGeotiffUploadSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
//		FenixAlert.alert("URL", urlServletUpload);	
		form.setAction(urlServletUpload);

		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		panel.setWidth("550");
		form.setWidget(panel);

		panel.add(new HTML("<table cellpadding='5' cellspacing='5'><tr><td><b>" + BabelFish.print().Geotiff() + "</b></td></tr>"));
				
		
		// Create a FileUpload widget.
		final FileUpload upload = new FileUpload();
		upload.setName("geotiffItem");
		upload.setWidth("250");
		panel.add(new HTML("<tr><td>"));
		panel.add(upload);
		panel.add(new HTML("</td></tr>"));

		panel.add(new HTML("<tr><td><br/></td></tr>"));

		// Create a FileUpload widget for metadata file
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


		submit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String layerFile = upload.getFilename();
				String mdFile    = uploadMd.getFilename();

				if (layerFile.isEmpty()) {
					FenixAlert.error(BabelFish.print().error(),
									 "Missing geotiff file");  // fixme i18n
				}else if ( ! layerFile.toLowerCase().endsWith(".zip") &&
						! layerFile.toLowerCase().endsWith(".tiff") &&
						! layerFile.toLowerCase().endsWith(".tif")) {
					FenixAlert.error(BabelFish.print().error(),
									 "Expected a .zip or .tiff or .tif file");  // fixme i18n
				}
				else if ( ! mdFile.isEmpty() && !mdFile.toLowerCase().endsWith(".xml")) {
					FenixAlert.error(BabelFish.print().error(),
									 "Metadata files must be .xml file");  // fixme i18n
				}
				else {
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
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {

				String results = event.getResults();
				String error = null;

//				FenixAlert.info("RESULTS", results);

				if (results.length() > 0) {
					if (results.indexOf("Exception:") > 0) {
//						int start = results.indexOf("Exception:")+10;
//						int end = results.substring(start).indexOf("org") + start;
//						results = results.substring(start, end);
						error = results.substring(10);
					} else {
						String crs    = null;
						String[] bbox = null;
						String url    = null;

						String[] variables = results.split("&");
//						com.google.gwt.user.client.Window.alert("["+variables[0]+"]");

						for (int v=0; v<variables.length; v++) {
							String[] var = variables[v].split("=");

							if (var[0].toLowerCase().contains("crs"))
								crs = var[1];

							if (var[0].toLowerCase().contains("bbox"))
								bbox = var[1].split(",");

							if (var[0].toLowerCase().contains("url")){
								url = var[1];
								if(url.indexOf("<") != -1)
									url = url.substring(0, url.indexOf("<")-1);
							}
						}

//						com.google.gwt.user.client.Window.alert("["+crs+"]");
//						com.google.gwt.user.client.Window.alert("["+bbox[0]+" "+bbox[1]+" "+bbox[2]+" "+bbox[3]+"]");
//						com.google.gwt.user.client.Window.alert("["+url+"]");

						window.close();
						new FinalGTiff(crs, bbox, url).build();
					}
				}

				if(error == null) {
					message.setHTML("<div style='font-weight:bold; color:green'> "
							+ BabelFish.print().successful()
							+ "</div>");
				} else {
					message.setHTML("<div style='color:red'> " 
							+ "<b>Errors uploading file </b>: <br/><br/>" 
							+ error
							+ "</div>");
					com.google.gwt.user.client.Window.alert(error);
				}
			}
		});

		panel.add(message);
		
		// add form to the shell
		window.add(form);
		
		// finally open window
		window.show();
	}	
}
