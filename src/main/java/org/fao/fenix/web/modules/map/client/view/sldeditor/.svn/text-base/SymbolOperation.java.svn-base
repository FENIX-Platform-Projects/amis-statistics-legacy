package org.fao.fenix.web.modules.map.client.view.sldeditor;

import org.fao.fenix.web.modules.map.client.colorpicker.ui.FenixColorPicker;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SymbolOperation {

	
	private String layerName;
	private String layerListName;
	private String layerStyle;
	private String geoserverUrl;
	
	private Styler styler;
	private boolean isJoined;
	private Long geoviewid;
	
	private JavaScriptObject newStyleName = null;
	private FormPanel symbolForm;
	private TextField<String> colorLayer;
//	private final ToolBar statusBar;
	private final Status statusMessage;
	private Button applyBtn;
	private FenixColorPicker colorPicker = null;
	
	
	public SymbolOperation(Styler styler) {		
			
			this.styler = styler;
			
			LayerItem layerItem = styler.getLayerItem();
			this.layerName = layerItem.getLayerName();
			this.layerListName = layerItem.getLayerTitle();
			this.layerStyle = layerItem.getLayerStyleName();
			this.geoviewid = layerItem.getUniqueId();
			this.isJoined = layerItem.isJoined();
			
			String url = (layerItem.getWMSUrl().indexOf("wms") > 0 ? 
					layerItem.getWMSUrl().substring(0, layerItem.getWMSUrl().indexOf("wms")) : 
					layerItem.getWMSUrl());
			this.geoserverUrl = url;
			
			this.statusMessage = new Status();
			this.statusMessage.clearStatus("Ready");
			this.statusMessage.setEnabled(true);

//			this.statusBar = new ToolBar();
//			statusBar.add(statusMessage);
	}
	
	protected void  createSymbolFormPanel(){
		
		symbolForm = new FormPanel();
		symbolForm.setBodyStyle("padding: 5px 5px 10px");
		symbolForm.setWidth(372);
		symbolForm.setFrame(true);
		symbolForm.setHeight(150);
		symbolForm.setVisible(false);
		symbolForm.setHeaderVisible(false);
		symbolForm.setAutoHeight(true);

		FormLayout layout = new FormLayout();
		layout.setLabelWidth(70);
		layout.setLabelPad(5);
		symbolForm.setLayout(layout);		
	
		
		colorLayer = new TextField<String>();
		colorLayer.setFieldLabel("color");
		colorLayer.setValue("click to insert a color...");
		colorLayer.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}		

				showColorPicker();			        
			}		
		});
		symbolForm.add(colorLayer);
		
		SelectionListener<ButtonEvent> applyListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if(colorLayer.getValue().indexOf("#") != -1){
					statusMessage.setBusy("Sending data...");
					putSymbol(colorLayer.getValue());
				}else{
					FenixAlert.alert("Alert", "The field color is missing !", "");
				}				
			}
		};
		
		SelectionListener<ButtonEvent> closeListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				symbolForm.hide();
				styler.showSymbolizerFormPanel();
				styler.getApplyButton().enable(); // TODO: check this is the right button to enable // getSymbolizersWindow().getButtonBar().getItem(0).enable();
			}
		};


		applyBtn = new Button("Apply", applyListener);
		symbolForm.addButton(applyBtn);
		symbolForm.addButton(new Button("Close", closeListener));

		symbolForm.add(statusMessage);
		statusMessage.show();
	}
	
	private native void putSymbol(String color)/*-{
	
		var style = '<?xml version="1.0" encoding="UTF-8"?><StyledLayerDescriptor version="1.0.0" xmlns:gml="http://www.opengis.net/gml" xmlns:ogc="http://www.opengis.net/ogc" xmlns="http://www.opengis.net/sld">';
		
		style += '<NamedLayer><Name>polygon</Name><UserStyle><Name>polygon</Name><Title>Simple polys</Title><FeatureTypeStyle><Rule><Name>default</Name><Title>default</Title><PolygonSymbolizer>';
		style += '<Fill>';
		style += '<CssParameter name="fill">'+color+'</CssParameter>';
		style += '<CssParameter name="fill-opacity">1</CssParameter>';
	  	style += '</Fill>';
	  	style += '<Stroke>';
		style += '<CssParameter name="stroke">#000000</CssParameter>';
		style += '<CssParameter name="stroke-opacity">1</CssParameter>';
		style += '</Stroke>';
	  	style += '</PolygonSymbolizer></Rule></FeatureTypeStyle></UserStyle></NamedLayer></StyledLayerDescriptor>';  	
	  	
	  	
	  	var geoUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolOperation::geoserverUrl;
		var name = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolOperation::layerStyle; 
			
		var url;
		if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolOperation::isJoined){
			url = geoUrl + "rest/styles/" + name;
		}else{								
			var id = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolOperation::geoviewid;			
			if (name.indexOf(id) != -1){
				url = geoUrl + "rest/styles/" + name;
			}else{
				url = geoUrl + "rest/styles/" + name + id;
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolOperation::newStyleName = name + id;
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolOperation::setNewStyleName()();
			}	    
		}
			
		$wnd.OpenLayers.Request.PUT({
			url: url,
			user: 'admin',
			password:'geoserver',
			data: style,
			callback: handlerClassPut,
			scope: this
		});
			
		function handlerClassPut(req){
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SymbolOperation::refreshLayer()();
		}
	  	
	}-*/;
	
	public void setNewStyleName(){
		this.styler.setNewStyleNameByClassification(this.newStyleName);
	}
	
	private void refreshLayer(){
		this.styler.refreshLayer();
		
		statusMessage.clearStatus("Ready");
		Info.display("Upload Style", "SLD succesfully uploaded!");
	}
	
	private void showColorPicker(){
		this.colorPicker = new FenixColorPicker(colorLayer.getItemId(), colorLayer.getElement());
	}
	
	public FormPanel getSymbolForm(){
		return this.symbolForm;
	}
}
