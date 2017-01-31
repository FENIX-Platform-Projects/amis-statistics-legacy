package org.fao.fenix.web.modules.map.client.view.sldeditor.io;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

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


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class ExternalGraphicFileUploader {

	private TextField<String> externalGraphic;
	
	
	public ExternalGraphicFileUploader(TextField<String> externalGraphic){
		this.externalGraphic = externalGraphic;
		build();
	}
	
	public void build(){
		
		// window to hold uploader
		final Window window = new Window();
		window.setAutoWidth(false);
		window.setSize("400", "150");
		window.setHeading(BabelFish.print().ExternalGraphicUploaderForm());

		// Create a FormPanel and point it at a service.
		final FormPanel form = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		final String urlServletUpload = ep.getExternalGraphicUploadSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
		form.setAction(urlServletUpload);
		
		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		panel.setWidth("550");
		form.setWidget(panel);

		panel.add(new HTML("<table cellpadding='5' cellspacing='5'><tr><td><b>" + 
				BabelFish.print().ExternalGraphic() + "</b></td></tr>"));
				
		
		// Create a FileUpload widget.
		final FileUpload upload = new FileUpload();
		upload.setName("externalGraphicFileItem");
		upload.setWidth("250");
		panel.add(new HTML("<tr><td>"));
		panel.add(upload);
		panel.add(new HTML("</td></tr>"));

		panel.add(new HTML("<tr><td><br/></td></tr>"));
		
		final HTML message = new HTML();
		
		// Add a 'submit' button.
		Button submit = new Button(BabelFish.print().process());

		submit.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				String layerFile = upload.getFilename();

				if (layerFile.isEmpty()) {
					FenixAlert.error(BabelFish.print().error(),
									 "Missing layer file");
				}else if (!(layerFile.toLowerCase().endsWith(".png") || 
						   		layerFile.toLowerCase().endsWith(".jpeg") ||
						   			layerFile.toLowerCase().endsWith(".jpg") ||
						   				layerFile.toLowerCase().endsWith(".gif"))) {
					FenixAlert.error(BabelFish.print().error(),
									 "Expected a .png, .jpeg or .gif file");
				}else {
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
						results = "<b>Errors when uploading image file </b>[" + fileName + "]: <br/><br/>" + results;
//						com.google.gwt.user.client.Window.alert("["+results+"]");	
					} else {
						
						String name = "";	
						String dirName = "";
						
						String[] variables = results.split("&");
						for (int v=0; v<variables.length; v++) {
							String[] var = variables[v].split("="); 
							if (var[0].toLowerCase().contains("name")){
								name = var[1];
								if(name.indexOf("</pre>") != -1){
									name = name.substring(0,name.indexOf("</pre>"));
								}
							}								
							
							if (var[0].toLowerCase().contains("dir")){
								dirName = var[1];
								if(dirName.indexOf("</pre>") != -1){
									dirName = dirName.substring(0,dirName.indexOf("</pre>"));
								}								
							}															
						}
						
						String serviceUrl = urlServletUpload;
						if(serviceUrl.indexOf("SingleExternalGraphicUploadServlet") != -1){
							serviceUrl = serviceUrl.substring(0, serviceUrl.indexOf("SingleExternalGraphicUploadServlet"));
							serviceUrl.trim();
						}
						
						externalGraphic.setValue(serviceUrl + dirName.trim() + "/" + name.trim());
						window.hide();
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
		window.add(form);
		
		// finally open window
		window.show();
	}	
}
