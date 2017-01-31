package org.fao.fenix.web.modules.shapefileupload.client.view;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class FinalShape {
	
	private String crs;
	private String[] envelope;
	private String[] attributes;
	private String geometry;
	private String geometry_type;
	private String url;
	
	private String selectedAttribute = null;

	private Window mainWindow;

	public FinalShape(String crs, String[] envelope, String[] attributes,
						String geometry, String type, String url){
		
		this.crs = crs;
		this.envelope = envelope;
		this.attributes = attributes;
		this.geometry = geometry;
		this.geometry_type = type;
		this.url = url;
	}

	public void build() {
		mainWindow = new Window();
//		window.setAutoWidth(false);
//		window.setSize("500", "500");
		mainWindow.setWidth("600");
		mainWindow.setAutoHeight(true);
		mainWindow.setHeading(BabelFish.print().FinalShapeForm());
		
		// Create a FormPanel and point it at a service.
		final FormPanel form = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getShapeStoreSEP(GWT.getModuleBaseURL(), GWT.getModuleName());
		form.setAction(urlServletUpload);

		form.setMethod(FormPanel.METHOD_POST);

		// Create a panel to hold all of the form widgets.
		final VerticalPanel panel = new VerticalPanel();
		panel.setWidth("580");
		form.setWidget(panel);

		panel.add(new HTML("<br/><table cellpadding='5' cellspacing='5'><tr><td><b>" + BabelFish.print().ShapeFile() + "</b></td></tr>"));
				
		panel.add(new HTML("<tr><td><b>CRS:</b></td><td>"));
		final TextArea crsArea = new TextArea();
		crsArea.setName("CRS");
		crsArea.setTitle("CRS: ");
		crsArea.setReadOnly(false);
		crsArea.setWidth("280px");
		crsArea.setHeight("20px");
		crsArea.setText(crs);
		panel.add(crsArea);
		panel.add(new HTML("</td></tr>"));

		panel.add(new HTML("<tr><td><b>minX:</b></td><td>"));
		final TextArea minX = new TextArea();
		minX.setName("MINX");
		minX.setTitle("minX: ");
		minX.setReadOnly(true);
		minX.setWidth("280px");
		minX.setHeight("20px");
		minX.setText(envelope[0].toString());
		panel.add(minX);
		panel.add(new HTML("</td></tr>"));
		
		panel.add(new HTML("<tr><td><b>minY:</b></td><td>"));
		final TextArea minY = new TextArea();
		minY.setName("MINY");
		minY.setTitle("minY: ");
		minY.setReadOnly(true);
		minY.setWidth("280px");
		minY.setHeight("20px");
		minY.setText(envelope[1].toString());
		panel.add(minY);
		panel.add(new HTML("</td></tr>"));
		
		panel.add(new HTML("<tr><td><b>maxX:</b></td><td>"));
		final TextArea maxX = new TextArea();
		maxX.setName("MAXX");
		maxX.setTitle("maxX: ");
		maxX.setReadOnly(true);
		maxX.setWidth("280px");
		maxX.setHeight("20px");
		maxX.setText(envelope[2].toString());
		panel.add(maxX);
		panel.add(new HTML("</td></tr>"));
		
		panel.add(new HTML("<tr><td><b>maxY:</b></td><td>"));
		final TextArea maxY = new TextArea();
		maxY.setName("MAXY");
		maxY.setTitle("maxY: ");
		maxY.setReadOnly(true);
		maxY.setWidth("280px");
		maxY.setHeight("20px");
		maxY.setText(envelope[3].toString());
		panel.add(maxY);
		panel.add(new HTML("</td></tr>"));
		
		panel.add(new HTML("<tr><td><b>Default Geometry Column:</b></td><td>"));
		final Label geometryArea = new Label();
		geometryArea.setTitle("Default Geometry: ");
		geometryArea.setText(geometry);
		panel.add(geometryArea);
		panel.add(new HTML("</td></tr>"));
		
		Hidden geometryField = new Hidden("GEOM", geometry);
		panel.add(geometryField);
		
		panel.add(new HTML("<tr><td><b>Default Style:</b></td><td>"));
		final Label styleArea = new Label();
		styleArea.setTitle("Style: ");
		styleArea.setText(geometry_type);
		panel.add(styleArea);
		panel.add(new HTML("</td></tr>"));
		
		Hidden typeField = new Hidden("TYPE", geometry_type);
		panel.add(typeField);
		
		panel.add(new HTML("<tr><td><b>Default Column Attribute:</b></td><td>"));
		final Label columnArea = new Label();
		styleArea.setTitle("Column: ");
		panel.add(columnArea);
		
		for(int i=0; i<attributes.length; i++){
			if (!attributes[i].equals(geometry)) {
				RadioButton radio = new RadioButton("column", attributes[i]);
				radio.setText(attributes[i]);
				radio.setWidth("280px");
				radio.addClickListener(new ClickListener() {
					public void onClick(Widget sender) {
						selectedAttribute = ((RadioButton)sender).getText();
					}
				});
				panel.add(radio);
			}
		}
		
		final Hidden attribute = new Hidden("selected_attribute", selectedAttribute);
		panel.add(attribute);
		
		Hidden shapeURL = new Hidden("shape_path_url", url);
		panel.add(shapeURL);
		
		Hidden fakeField = new Hidden("fake", "");
		panel.add(fakeField);
		
		panel.add(new HTML("</td></tr>"));
		
		panel.add(new HTML("</table><br/>"));
		
		final HTML message=new HTML();

		// Add a 'submit' button.
		Button submit = new Button(BabelFish.print().process());
		submit.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				boolean submitErrors = false;
				
				String crs = crsArea.getText();
				if (crs == null || crs.equals("")){
					submitErrors = true;
					FenixAlert.error(BabelFish.print().error(), "Please specify a correct CRS.");
				}
				
				String minx, miny, maxx, maxy;
					minx = minX.getText();
					miny = minY.getText();
					maxx = maxX.getText();
					maxy = maxY.getText();
				if (minx == null || miny == null || maxx == null || maxy == null ||
					minx.equals("") || miny.equals("") || maxx.equals("") || maxy.equals("")) {
					submitErrors = true;
					FenixAlert.error(BabelFish.print().error(), "Invalid Envelope coordinates.");
				}
				
				if (selectedAttribute == null) {
					submitErrors = true;
					FenixAlert.error(BabelFish.print().error(), "Select a default column attribute.");
				}
				
				if (!submitErrors) {
					attribute.setValue(selectedAttribute);
					form.submit();
				}
			}
		});
		submit.setWidth("250");
		panel.add(submit);
		
		// Add an event handler to the form.
		form.addFormHandler(new FormHandler() {
			
			/**
			 * Fired just before submit form, used to perform checks.
			 */
			public void onSubmit(FormSubmitEvent event) {		
				//FenixAlert.alert("OnSubmit", "OnSubmit");
			}

			/**
			 * Fired on submission complete, could be used as a test.
			 */
			public void onSubmitComplete(FormSubmitCompleteEvent event) {

				String results = event.getResults();
				System.out.println("got result::" + results + "::");
				try {
					int p1 = results.indexOf(">");
					int p2 = results.lastIndexOf("<");
					if(p1!=-1 && p2!=-1 && p1<p2)
						results = results.substring(p1+1, p2-1);

					long lid = Long.parseLong(results);	// we have the layer resource id
					// open metadata editor
					MEOpener.showMetadata(lid, true, true);
					mainWindow.close();
				}
				catch(NumberFormatException nfe) {
					com.google.gwt.user.client.Window.alert("["+results+"]");
				}
			}
		});

		panel.add(message);
		
		// add form to the shell
		mainWindow.add(form);
		
		// finally open window
		mainWindow.show();
	}
}
