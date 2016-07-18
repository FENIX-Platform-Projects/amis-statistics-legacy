package org.fao.fenix.web.modules.map.client.view.sldeditor;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class UniqueValueClassifier{

	
	private String layerName;
	private String layerListName;
	private String layerStyle;
	private String geoserverUrl;
	
	private Styler styler;
	private boolean isJoined;
	private Long geoviewid;
	private JavaScriptObject newStyleName = null;
	
	private JavaScriptObject[] layerAttributes;
	private JavaScriptObject classifyRules;
	private ComboBox<AttributeName> attributes;
	private FormPanel uniqueValueForm;

//	private final ToolBar statusBar;
	private final Status statusMessage;
	private Button applyBtn;
	

	public UniqueValueClassifier(Styler styler) {		
		
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
//		this.statusMessage.setWidth(150);
		this.statusMessage.setText("Ready");
		this.statusMessage.setEnabled(true);
		
//		this.statusBar = new ToolBar();
//		this.statusBar.add(statusMessage);
	}
	
	protected void  createUniqueValueFormPanel(){
		
		uniqueValueForm = new FormPanel();
		uniqueValueForm.setBodyStyle("padding: 5px 5px 10px");
		uniqueValueForm.setWidth(372);
		uniqueValueForm.setFrame(true);
		uniqueValueForm.setHeight(150);
		uniqueValueForm.setVisible(false);
		uniqueValueForm.setHeaderVisible(false);
		uniqueValueForm.setAutoHeight(true);

		FormLayout layout = new FormLayout();
		layout.setLabelWidth(70);
		layout.setLabelPad(5);
		uniqueValueForm.setLayout(layout);		
		
	    List<AttributeName> attributeList = new ArrayList<AttributeName>();
	    for(int j=0; j<layerAttributes.length; j++){
	    	 attributeList.add(new AttributeName(layerAttributes[j].toString(), 
	    			 layerAttributes[j].toString()));
	    }
	    	  	
		ListStore<AttributeName> attributeStore = new ListStore<AttributeName>();
		attributeStore.add(attributeList);
		
		attributes = new ComboBox<AttributeName>();
		attributes.setTriggerAction(TriggerAction.ALL);
		attributes.setDisplayField("property");
		attributes.setFieldLabel("name");
		attributes.setEditable(false);
		attributes.setStore(attributeStore);	
		attributes.setValue(attributeStore.getAt(0));
		uniqueValueForm.add(attributes);
		
		SelectionListener<ButtonEvent> applyListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				statusMessage.setBusy("Sending data...");
				putClassification();
				statusMessage.clearStatus("Ready");
				Info.display("Upload Style", "SLD succesfully uploaded!");
			}
		};
		
		SelectionListener<ButtonEvent> generateListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

					statusMessage.setBusy("Generate data...");

					String attributesValue = attributes.getValue().getName();
					String methodValue = "uniqueInterval";					
					String intervalsValue = "1";					
					boolean openIntervalsValue = true;
					String colorRampValue = "random";

					getClassification(attributesValue, methodValue,
							intervalsValue, openIntervalsValue,
							colorRampValue, null);
			}
		};
		
		SelectionListener<ButtonEvent> closeListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				uniqueValueForm.hide();
				styler.showSymbolizerFormPanel();
//				styler.getSymbolizersWindow().getButtonBar().getItem(0).enable();
				styler.getApplyButton().enable();
			}
		};

		applyBtn = new Button("Apply", applyListener);
		applyBtn.setEnabled(false);
		uniqueValueForm.addButton(applyBtn);
		uniqueValueForm.addButton(new Button("Generate", generateListener));
		uniqueValueForm.addButton(new Button("Close", closeListener));

		
		uniqueValueForm.add(statusMessage);
		statusMessage.show();
		
//		uniqueValueForm.setBottomComponent(statusBar);
//		statusMessage.show();
	}
	
	public void setlayerAttributes(JavaScriptObject[] layerAttributes){
		this.layerAttributes = layerAttributes;
	}
	
	public FormPanel getUniqueValueForm(){
		return this.uniqueValueForm;
	}
	
	private native void getClassification(String attributesValue,
			String methodValue, String intervalsValue,
			boolean openIntervalsValue, String colorRampValue,
			String[] customValues)/*-{
			
			var geoUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::geoserverUrl;
			var layName = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::layerName; 
			
			var url = geoUrl + "rest/sldservice/" + layName + "/classify?attribute=" + attributesValue + "&method=" + methodValue + "&intervals=" + intervalsValue + "&open=" + openIntervalsValue + "&ramp=" + colorRampValue;

			$wnd.OpenLayers.Request.GET({
				url: url,
				callback: classHandler,
				scope: this
			});
			
			function classHandler(req){
				try{
					var classRules = $wnd.JSON.parse(req.responseText);					
					var sld_rule = " ";
					
					for(var i=0; i<classRules.length; i++){
						var polyName = classRules[i].Title;
	                    
	                    var textneu = polyName.replace(/&/,"&amp;");
						textneu = textneu.replace(/</,"&lt;");
						textneu = textneu.replace(/>/,"&gt;");
						textneu = textneu.replace(/\r\n/,"<br>");
						textneu = textneu.replace(/\n/,"<br>");
						textneu = textneu.replace(/\r/,"<br>");
	
	 					sld_rule += "<Rule><Name>" + textneu +"</Name>";	
						sld_rule += createFilter(classRules[i]);
			
						var strokeColor = classRules[i].PolygonSymbolizer.Stroke.CssParameter[0].Literal;
						var strokeOpacity = classRules[i].PolygonSymbolizer.Stroke.CssParameter[1].Literal;
						var polygonStroke = createStroke(strokeColor, strokeOpacity);
						
						var fillColor = classRules[i].PolygonSymbolizer.Fill.CssParameter[0].Literal;
						var fillOpacity = classRules[i].PolygonSymbolizer.Fill.CssParameter[1].Literal;
						var polygonFill = createFill(fillColor, fillOpacity);			
						
						sld_rule += createSLDPolygonSymbolizer(polygonFill, polygonStroke);
						sld_rule += '</Rule>';
					}
					
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::classifyRules = sld_rule;					
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::toClassifySuccess()();					
				}catch(e){ 
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::toClassifyFailed()();	
				} 
			}
			
			function createSLDPolygonSymbolizer(fill, stroke){
				XML  = '<PolygonSymbolizer>';
				XML += fill;
				
				if (stroke != null) {
					XML += stroke;
				}
				
				XML += '</PolygonSymbolizer>';
			
				return XML;
			}	
			
			function createFill(color, opacity){	
				// add # to front of 'color'
				if(color.charAt(0) != "#")
					color = "#"+color;
			
				XML  = '<Fill>';
				XML += '<CssParameter name="fill">'+color+'</CssParameter>';
				XML += '<CssParameter name="fill-opacity">'+opacity+'</CssParameter>';
				XML += '</Fill>';
			
				return XML;
			}
			
			function createStroke(color, opacity){	
				// add # to front of 'color'
				if(color.charAt(0) != "#")
					color = "#"+color;
			
				XML  = '<Stroke>';
				if(color)	XML += '<CssParameter name="stroke">'+color+'</CssParameter>';
				if(opacity)	XML += '<CssParameter name="stroke-opacity">'+opacity+'</CssParameter>';
				XML += '</Stroke>';
			
				return XML;
			}
			
			function createFilter(rule) {
			
				XML = '<ogc:Filter>';
				
				if (rule.Filter.And != undefined && rule.Filter.And != null) {
					XML += '<ogc:And>';
					
					if (rule.Filter.And.PropertyIsLessThan != undefined && rule.Filter.And.PropertyIsLessThan != null) {
					   XML += '<ogc:PropertyIsLessThan>';
					   XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsLessThan.PropertyName+'</ogc:PropertyName>';
					   XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsLessThan.Literal+'</ogc:Literal>';
					   XML += '</ogc:PropertyIsLessThan>';
					}
					
					if (typeof rule.Filter.And.PropertyIsLessThanOrEqualTo != undefined && rule.Filter.And.PropertyIsLessThanOrEqualTo != null) {
					   XML += '<ogc:PropertyIsLessThanOrEqualTo>';
					   XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsLessThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
					   XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsLessThanOrEqualTo.Literal+'</ogc:Literal>';
					   XML += '</ogc:PropertyIsLessThanOrEqualTo>';
					}
					
					if (typeof rule.Filter.And.PropertyIsGreaterThan != undefined && rule.Filter.And.PropertyIsGreaterThan != null) {
					   XML += '<ogc:PropertyIsGreaterThan>';
					   XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsGreaterThan.PropertyName+'</ogc:PropertyName>';
					   XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsGreaterThan.Literal+'</ogc:Literal>';
					   XML += '</ogc:PropertyIsGreaterThan>';
					}
					
					if (typeof rule.Filter.And.PropertyIsGreaterThanOrEqualTo != undefined && rule.Filter.And.PropertyIsGreaterThanOrEqualTo != null) 			{
					   XML += '<ogc:PropertyIsGreaterThanOrEqualTo>';
					   XML += '<ogc:PropertyName>'+rule.Filter.And.PropertyIsGreaterThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
					   XML += '<ogc:Literal>'+rule.Filter.And.PropertyIsGreaterThanOrEqualTo.Literal+'</ogc:Literal>';
					   XML += '</ogc:PropertyIsGreaterThanOrEqualTo>';
					}
						
					if (typeof rule.Filter.PropertyIsEqualTo != undefined && rule.Filter.PropertyIsEqualTo != null) {
						XML += '<ogc:PropertyIsEqualTo>';
						XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsEqualTo.PropertyName+'</ogc:PropertyName>';
						XML += '<ogc:Literal>'+rule.Filter.PropertyIsEqualTo.Literal+'</ogc:Literal>';
						XML += '</ogc:PropertyIsEqualTo>';
					}
						
					XML += '</ogc:And>';		
			  }
			  
			  if (rule.Filter.PropertyIsLessThan != undefined && rule.Filter.PropertyIsLessThan != null) {
				XML += '<ogc:PropertyIsLessThan>';
			    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsLessThan.PropertyName+'</ogc:PropertyName>';
			    XML += '<ogc:Literal>'+rule.Filter.PropertyIsLessThan.Literal+'</ogc:Literal>';
			    XML += '</ogc:PropertyIsLessThan>';
			  }
			
			  if (typeof rule.Filter.PropertyIsLessThanOrEqualTo != undefined && rule.Filter.PropertyIsLessThanOrEqualTo != null) {
				XML += '<ogc:PropertyIsLessThanOrEqualTo>';
			    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsLessThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
			    XML += '<ogc:Literal>'+rule.Filter.PropertyIsLessThanOrEqualTo.Literal+'</ogc:Literal>';
			    XML += '</ogc:PropertyIsLessThanOrEqualTo>';
			  }
			
			  if (typeof rule.Filter.PropertyIsGreaterThan != undefined && rule.Filter.PropertyIsGreaterThan != null) {
				XML += '<ogc:PropertyIsGreaterThan>';
			    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsGreaterThan.PropertyName+'</ogc:PropertyName>';
			    XML += '<ogc:Literal>'+rule.Filter.PropertyIsGreaterThan.Literal+'</ogc:Literal>';
			    XML += '</ogc:PropertyIsGreaterThan>';
			  }
			
			  if (typeof rule.Filter.PropertyIsGreaterThanOrEqualTo != undefined && rule.Filter.PropertyIsGreaterThanOrEqualTo != null) {
				XML += '<ogc:PropertyIsGreaterThanOrEqualTo>';
			    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsGreaterThanOrEqualTo.PropertyName+'</ogc:PropertyName>';
			    XML += '<ogc:Literal>'+rule.Filter.PropertyIsGreaterThanOrEqualTo.Literal+'</ogc:Literal>';
			    XML += '</ogc:PropertyIsGreaterThanOrEqualTo>';
			  }
				
			  if (typeof rule.Filter.PropertyIsEqualTo != undefined && rule.Filter.PropertyIsEqualTo != null) {
				XML += '<ogc:PropertyIsEqualTo>';
			    XML += '<ogc:PropertyName>'+rule.Filter.PropertyIsEqualTo.PropertyName+'</ogc:PropertyName>';
			    XML += '<ogc:Literal>'+rule.Filter.PropertyIsEqualTo.Literal+'</ogc:Literal>';
			    XML += '</ogc:PropertyIsEqualTo>';
			  }
			
			  XML += '</ogc:Filter>';
			
			  return XML;
			}
	}-*/;

	private native void putClassification()/*-{
				
			var geoUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::geoserverUrl;
			var name = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::layerStyle; 
			
			var st = '<StyledLayerDescriptor xmlns="http://www.opengis.net/sld" version="1.0.0" xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc">';
			
			var url;
			if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::isJoined){
				url = geoUrl + "rest/styles/" + name;
			}else{								
				var id = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::geoviewid;			
				if (name.indexOf(id) != -1){
					url = geoUrl + "rest/styles/" + name;
				}else{
					url = geoUrl + "rest/styles/" + name + id;
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::newStyleName = name + id;
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::setNewStyleName()();
				}	    
			}
			
			st += '<NamedLayer><Name>Default Polygon</Name><UserStyle><Title>A boring default style</Title><Abstract>A sample style that just prints out a transparent red interior with a red outline</Abstract><FeatureTypeStyle>';
			
			st += this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::classifyRules;
			
			st += '</FeatureTypeStyle></UserStyle></NamedLayer></StyledLayerDescriptor>';	

			
			$wnd.OpenLayers.Request.PUT({
				url: url,
				user: 'admin',
				password:'geoserver',
				data: st,
				callback: handlerClassPut,
				scope: this
			});
			
			function handlerClassPut(req){
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.UniqueValueClassifier::refreshLayer()();
			}
	}-*/;
	
	public void setNewStyleName(){
		this.styler.setNewStyleNameByClassification(this.newStyleName);
	}
	
	private void refreshLayer(){
		this.styler.refreshLayer();
	}
	
	private void toClassifyFailed(){
		this.statusMessage.clearStatus("Ready");
		Info.display("Generating Rules", "There were problems while generating rules!");
	}
	
	private void toClassifySuccess(){
		this.statusMessage.clearStatus("Ready");
		Info.display("Generating Rules", "Rules succesfully generated!");
		applyBtn.setEnabled(true);
	}

	
	public class AttributeName extends BaseModelData {

		private static final long serialVersionUID = -5697492756813816141L;

		public AttributeName() {
		  }

		  public AttributeName(String property, String name) {
		    setProperty(property);
		    setName(name);
		  }
		  
		  public String getProperty() {
		    return get("property");
		  }

		  public void setProperty(String property) {
		    set("property", property);
		  }

		  public String getName() {
		    return get("name");
		  }

		  public void setName(String name) {
		    set("name", name);
		  }
	}
}
