package org.fao.fenix.web.modules.map.client.view.sldeditor;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
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

public class SLDLoad {
	
	private JavaScriptObject oldSld;
	private JavaScriptObject newSld;
	private Styler styler;
	private JavaScriptObject[] layerAttributes = null;
	
	
	public SLDLoad(Styler styler){	
		this.styler = styler;		
		this.oldSld = styler.isVector ? styler.getSLD() : styler.getRasterSLD();	
		
		if(styler.isVector && styler.getClassifier() != null){
			this.layerAttributes = styler.getClassifier().getlayerAttributes();					
		}

		build();
	}
	
	public void build() {
		
		// window to hold uploader
		final Window window = new Window();
		window.setAutoWidth(false);
		window.setSize("500", "250");
		window.setHeading(BabelFish.print().SldUploaderForm());

		// Create a FormPanel and point it at a service.
		final FormPanel form = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getSLDUploadSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
		form.setAction(urlServletUpload);

		// set form to use the POST method, and multipart MIME encoding.
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		panel.setWidth("550");
		form.setWidget(panel);

		panel.add(new HTML("<table cellpadding='5' cellspacing='5'><tr><td><b>" + BabelFish.print().SldFile() + "</b></td></tr>"));
				
		// Create a FileUpload widget.
		final FileUpload upload = new FileUpload();
		upload.setName("SLDFileItem");
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
				
				if(layerFile.isEmpty()){
					FenixAlert.error(BabelFish.print().error(),
									 "Missing file"); 
				}else if(!(layerFile.toLowerCase().endsWith(".sld") || 
							layerFile.toLowerCase().endsWith(".xml"))){					
					FenixAlert.error(BabelFish.print().error(),
									 "Expected a .xml or .sld file");
				}else{
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

				int index = 0;
				for (int i = upload.getFilename().length() - 1 ; i >= 0 ; i--) {
					if ((upload.getFilename().charAt(i) == '/') || (upload.getFilename().charAt(i) == '\\')) {
						index = i;
						break;
					}
				}

				String fileName = upload.getFilename().substring(index, upload.getFilename().length());
				String results = event.getResults();								
				
				if (results.length() > 0) {
					if (results.indexOf("Exception:") > 0) {
						int start = results.indexOf("Exception:")+10;
						int end = results.substring(start).indexOf("org") + start;
						results = results.substring(start, end);
						results = "<b>Errors when uploading sld file: <br/><br/>" + results;
					} else {
						
						String sld = results;						
						if(sld.indexOf("<pre>") != -1){
							sld = sld.replaceAll("<pre>", "");
							if(sld.indexOf("</pre>") != -1){
								sld = sld.replaceAll("</pre>", "");
							}
						}
						
						if(sld.indexOf("SLD=") != -1){
							sld = sld.replaceAll("SLD=", "");
						}
						
						String res;
						if(styler.isVector){
							res = readVectorSldFile(sld).toString();
							
							if(res.indexOf("ok") != -1){
								styler.setSLD(newSld);
							}else{
								FenixAlert.error("ERROR", res, "");
							}
						}else if(styler.isRaster){
							res = readRasterSldFile(sld).toString();
							
							if(res.indexOf("ok") != -1){
								styler.setRasterSLD(newSld);
							}else{
								FenixAlert.error("ERROR", res, "");
							}
						}
						
						window.hide();
					}
				}
				
				if(results.indexOf("Exception:") != -1) {
					message.setHTML("<div style='color:red'> " + results + "</div>");
				} else {
					message.setHTML("<div style='font-weight:bold; color:green'> "+BabelFish.print().successful() + " "+fileName + "</div>");
				}
			}
		});

		panel.add(message);
		
		// add form to the shell
		window.add(form);
		
		// finally open window
		window.show();
	}
	
	private native JavaScriptObject readVectorSldFile(String result)/*-{  		
 		var style = result; 		
 		
		regEq1 = /%3F/gi;
		regEq2 = /%2F/gi;
		regEq3 = /%3D/gi;
		regEq4 = /%3A/gi;
		regEq5 = /%3B/gi;
		regEq6 = /%26/gi;
		regEq7 = /%23/gi;
		regEq8 = /%20/gi;
		regEq9 = /\+/gi;
		
		style = style.replace(regEq1,"?");
		style = style.replace(regEq2,"/");
		style = style.replace(regEq3,"=");
		style = style.replace(regEq4,":");
		style = style.replace(regEq5,";");
		style = style.replace(regEq6,"&");
		style = style.replace(regEq7,"#");	
		style = style.replace(regEq8," ");
 		style = style.replace(regEq9," ");	
 		
 		style = decodeURI(style);
		
		try{			
			var format = new $wnd.OpenLayers.Format.SLD();	

			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDLoad::newSld = format.read(style);
			
			var formatXML = new $wnd.OpenLayers.Format.XML();				
			var newStyle = formatXML.read(style);	

			var oldStyle = formatXML.read(format.write(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDLoad::oldSld));

			var newGeometry;
			if(newStyle.getElementsByTagName("PolygonSymbolizer")[0]){
				newGeometry = "polygon";
			}else if(newStyle.getElementsByTagName("LineSymbolizer")[0]){
				newGeometry = "line";
			}else if(newStyle.getElementsByTagName("PointSymbolizer")[0]){
				newGeometry = "point";
			}
			
			var oldGeometry;
			if(oldStyle.getElementsByTagName("PolygonSymbolizer")[0]){
				oldGeometry = "polygon";
			}else if(oldStyle.getElementsByTagName("LineSymbolizer")[0]){
				oldGeometry = "line";
			}else if(oldStyle.getElementsByTagName("PointSymbolizer")[0]){
				oldGeometry = "point";
			}
			
			if(newGeometry == oldGeometry){		
				
				var numRules = newStyle.getElementsByTagName("Rule").length;
				
				if(numRules > 1 && newGeometry == "polygon"){
					
					var numAttributes = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDLoad::layerAttributes;
					var equal = false;
					
					for(var i=0; i<numRules; i++){

						if(newStyle.getElementsByTagName("ogc:PropertyName")[i]){

							var property = newStyle.getElementsByTagName("ogc:PropertyName")[i];
							
							var value;
							if(navigator.appName.indexOf("Netscape") != -1)	value = property.textContent;							
							else if(navigator.appName.indexOf("Microsoft Internet Explorer") != -1) value = property.text;

							for(var k=0; k<numAttributes.length; k++){
								if(numAttributes[k] == value) equal = true;
								else continue;
							}

							if(!equal) return "The style you are trying to load is not compliant with the layer in use due to a mismatch of the attributes"; 
						}
					}
					
					return "ok";
				}else return "ok";
			}else{
				return "The geometry type not corresponding!";
			}			
		}catch(e){
			return "This sld file is not properly formatted (may lead to non UTF character also)";
		}
	}-*/;
	
	private native JavaScriptObject readRasterSldFile(String result)/*-{ 		
 		var style = result; 		
 		
		regEq1 = /%3F/gi;
		regEq2 = /%2F/gi;
		regEq3 = /%3D/gi;
		regEq4 = /%3A/gi;
		regEq5 = /%3B/gi;
		regEq6 = /%26/gi;
		regEq7 = /%23/gi;
		regEq8 = /%20/gi;
		regEq9 = /\+/gi;
		
		style = style.replace(regEq1,"?");
		style = style.replace(regEq2,"/");
		style = style.replace(regEq3,"=");
		style = style.replace(regEq4,":");
		style = style.replace(regEq5,";");
		style = style.replace(regEq6,"&");
		style = style.replace(regEq7,"#");	
		style = style.replace(regEq8," ");
 		style = style.replace(regEq9," ");	
 		
 		style = decodeURI(style);	
		
		try{
			var format = new $wnd.OpenLayers.Format.XML();
			
			var sld_raster = format.read(style);
			var rasterSymbolizerNode = sld_raster.getElementsByTagName("RasterSymbolizer")[0];
			
			if(rasterSymbolizerNode != null || rasterSymbolizerNode != undefined){
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SLDLoad::newSld = sld_raster;
				return "ok";
			}else{
				return "The symbolizer type not corresponding!";
			}			
		}catch(e){
			return "This sld file is not properly formatted (may lead to non UTF character also)";
		}
	}-*/;
}
