package org.fao.fenix.web.modules.map.client.view.sldeditor;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.colorpicker.ui.FenixColorPicker;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.HTML;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SldClassifier {

	private String layerName;
	private String layerListName;
	private String layerStyle;
	private String geoserverUrl;
	
	private Styler styler;
	private boolean isJoined;
	private Long geoviewid;
	private JavaScriptObject newStyleName = null;
	
	private Window intervallWindow = null;
	private FormPanel classifyForm;

	private JavaScriptObject[] layerAttributes = null;
	private JavaScriptObject[] layerNumAttributes = null;
	private JavaScriptObject classifyRules;
	private JavaScriptObject table;

	private final Status statusMsg;
	private Button applyBtn;
	private ComboBox<AttributeName> attributes;
	private ComboBox<MethodType> method;
	private TextField<String> intervals;
	private CheckBox openIntervals;
	private ComboBox<ColorRampType> colorRamp;

	private TextField<String> customStart;
	private TextField<String> customEnd;
	private TextField<String> customMid;
	private FenixColorPicker colorPicker = null;

	
	
	public SldClassifier(Styler styler) {		
		
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
		
		this.statusMsg = new Status();
		this.statusMsg.clearStatus("Ready");
		this.statusMsg.setEnabled(true);
	}

	public native void getAttributes()/*-{
			
			var geoUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::geoserverUrl;
			var layName = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::layerName;
			
			var url = geoUrl + "rest/sldservice/" + layName + "/attributes"; 

			$wnd.OpenLayers.Request.GET({
				url: url,
				callback: handlerAttributes,
				scope: this
			});
			
			function handlerAttributes(req){
				var attributes = $wnd.JSON.parse(req.responseText);			
				
				var arrayNum = [];
				var num = 0;
				
				for(var k=0; k<attributes.length; k++){
					if(attributes[k].type == "long" || attributes[k].type == "integer" || attributes[k].type == "int" ||
						attributes[k].type == "double" || attributes[k].type == "float" || attributes[k].type == "number"){
						arrayNum[num] = attributes[k].name;
						num++;
					}		
				}				
				
				var array = new Array(attributes.length-1);				
				
				for(var i=1; i<attributes.length; i++){
					array[i-1] = attributes[i].name;				
				}

				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::layerAttributes = array;
				
				if(num != 0){
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::layerNumAttributes = arrayNum;	
				}else{
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::layerNumAttributes = null;	
				}
				
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::showSymbolizers()();
			}
	}-*/;
	
	private void showSymbolizers(){
		styler.showSymbolizers();
	}
	
	public JavaScriptObject[] getlayerAttributes(){
		return this.layerAttributes;
	}
	
	protected void createClassifyFormPanel() {
		
		classifyForm = new FormPanel();
		classifyForm.setBodyStyle("padding: 5px 5px 10px");
		classifyForm.setWidth(372);
		classifyForm.setFrame(true);
		classifyForm.setHeight(280);
		classifyForm.setVisible(false);
		classifyForm.setHeaderVisible(false);
		classifyForm.setAutoHeight(true);

		FormLayout layout = new FormLayout();
		layout.setLabelWidth(70);
		layout.setLabelPad(5);
		classifyForm.setLayout(layout);		
		
		attributes = new ComboBox<AttributeName>();
		attributes.setTriggerAction(TriggerAction.ALL);
	    List<AttributeName> attributeList = new ArrayList<AttributeName>();	   
	    
	    if(layerNumAttributes != null){
		    for(int j=0; j<layerNumAttributes.length; j++){
		    	 attributeList.add(new AttributeName(layerNumAttributes[j].toString(), 
		    			 layerNumAttributes[j].toString()));
		    }	    	
	    }else{
	    	attributeList.add(new AttributeName("no valid attributes", 
	    			"no valid attributes"));
	    	attributes.setEnabled(false);
	    }
	    	  	
		ListStore<AttributeName> attributeStore = new ListStore<AttributeName>();
		attributeStore.add(attributeList);		
		
		attributes.setDisplayField("property");
		attributes.setFieldLabel("name");
		attributes.setEditable(false);
		attributes.setStore(attributeStore);	
		attributes.setValue(attributeStore.getAt(0));
		classifyForm.add(attributes);
		
		List<MethodType> methodList = new ArrayList<MethodType>();
		methodList.add(new MethodType("equalInterval", "equalInterval"));
		methodList.add(new MethodType("quantile", "quantile"));
	  	
		ListStore<MethodType> methodStore = new ListStore<MethodType>();
		methodStore.add(methodList);
		
		method = new ComboBox<MethodType>();
		method.setTriggerAction(TriggerAction.ALL);
		method.setDisplayField("property");
		method.setFieldLabel("method");
		method.setEditable(false);
		method.setStore(methodStore);
		method.setValue(methodStore.getAt(0));
		classifyForm.add(method);		

		intervals = new TextField<String>();
		intervals.setFieldLabel("Intervals");
		classifyForm.add(intervals);

		openIntervals = new CheckBox();
		openIntervals.setFieldLabel("Open Intervals");
		openIntervals.setEnabled(true);
		openIntervals.setValue(true);
		openIntervals.setWidth(40);
		classifyForm.add(openIntervals);   		
		
	    List<ColorRampType> rampList = new ArrayList<ColorRampType>();
	    rampList.add(new ColorRampType("red", "red"));
	    rampList.add(new ColorRampType("blue", "blue"));
	    rampList.add(new ColorRampType("random", "random"));
	    rampList.add(new ColorRampType("custom", "custom"));
	  	
		ListStore<ColorRampType> rampStore = new ListStore<ColorRampType>();
		rampStore.add(rampList);
		
		colorRamp = new ComboBox<ColorRampType>();
		colorRamp.setTriggerAction(TriggerAction.ALL);
		colorRamp.setDisplayField("property");
		colorRamp.setFieldLabel("ramp");
		colorRamp.setEditable(false);
		colorRamp.setStore(rampStore);	
		colorRamp.setValue(rampStore.getAt(0));
		colorRamp.addListener(Events.SelectionChange, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {			
				if (colorRamp.getValue().getProperty().equalsIgnoreCase("custom")){
					if (!customStart.isEnabled() && !customEnd.isEnabled() && !customMid.isEnabled()){
						customStart.setEnabled(true);
						customEnd.setEnabled(true);
						customMid.setEnabled(true);
						
						customMid.setValue(" ");
						customStart.setValue(" ");
						customEnd.setValue(" ");
					}
				}else{
					if (customStart.isEnabled() && customEnd.isEnabled() && customMid.isEnabled()){
						customStart.setEnabled(false);
						customEnd.setEnabled(false);
						customMid.setEnabled(false);
						
						if (colorPicker != null){
							colorPicker.destroyFenixColorPicker();
							colorPicker = null;
						}	
					}
				}
			}
		});
		classifyForm.add(colorRamp);		
		
		// /////////////////////
		// Custom fields
		// /////////////////////

		customStart = new TextField<String>();
		customStart.setFieldLabel("Custom start");
		customStart.setEnabled(false);
		customStart.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}		

				showColorPicker("start");			        
			}		
		});
		classifyForm.add(customStart);

		customEnd = new TextField<String>();
		customEnd.setFieldLabel("Custom end");
		customEnd.setEnabled(false);
		customEnd.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}		
				showColorPicker("end");		        
			}		
		});
		classifyForm.add(customEnd);

		customMid = new TextField<String>();
		customMid.setFieldLabel("Custom mid");
		customMid.setEnabled(false);
		customMid.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}		
				showColorPicker("mid");		        
			}		
		});
		classifyForm.add(customMid);
		
		SelectionListener<ButtonEvent> applyListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				statusMsg.setBusy("Sending classification data...");

				putClassification();

				statusMsg.clearStatus("Ready");
				Info.display("Upload Style", "SLD succesfully uploaded!");
			}
		};
		
		SelectionListener<ButtonEvent> classifyListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				if (intervals.getValue() == null) {
					FenixAlert.info("Message", "Interval value is mandatory !");
				}else if(!attributes.isEnabled()){
					FenixAlert.info("Message", "No valid attributes available to perform the layer classification !");
				}else{
					String attributesValue = attributes.getValue().getName();
					String methodValue = method.getValue().getMethod();
					
					String intervalsValue;
					intervalsValue = intervals.getValue();
					
					boolean openIntervalsValue = openIntervals.getValue();
					String colorRampValue = colorRamp.getValue().getRamp();

					String[] customValues = new String[3];
					if (customStart.isEnabled() && customMid.isEnabled()
							&& customEnd.isEnabled()) {
						if(customStart.getValue().indexOf("#") != -1){
							customValues[0] = customStart.getValue();
						}else{
							customValues[0] = null;
						}
						if(customMid.getValue().indexOf("#") != -1){
							customValues[1] = customMid.getValue();
						}else{
							customValues[1] = null;
						}
						if(customEnd.getValue().indexOf("#") != -1){
							customValues[2] = customEnd.getValue();
						}else{
							customValues[2] = null;
						}		
						
						if(customValues[0] != null && customValues[1] != null && customValues[2] != null){
							statusMsg.setBusy("Classifying data...");
							getClassification(attributesValue, methodValue,
									intervalsValue, openIntervalsValue,
									colorRampValue, customValues);
						}else{
							FenixAlert.alert("Alert", "The custom fields are missing !", "");
						}						
					} else {
						statusMsg.setBusy("Classifying data...");
						getClassification(attributesValue, methodValue,
								intervalsValue, openIntervalsValue,
								colorRampValue, null);
					}
				}
			}
		};
		
		SelectionListener<ButtonEvent> closeListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				classifyForm.hide();
				styler.showSymbolizerFormPanel();
				styler.getApplyButton().enable(); // TODO: check this is the right button to enable // getSymbolizersWindow().getButtonBar().getItem(0).enable();
			}
		};

		applyBtn = new Button("Apply", applyListener);
		applyBtn.setEnabled(false);

		classifyForm.addButton(applyBtn);
		classifyForm.addButton(new Button("Classify", classifyListener));
		classifyForm.addButton(new Button("Close", closeListener));

		classifyForm.add(statusMsg);
		statusMsg.show();
	}
	
	private void showColorPicker(String field){
		this.colorPicker = new FenixColorPicker(this, field);
	} 
   
	public void setMidColor(String hexColor){
		this.customMid.setValue(hexColor);
		this.customMid.setStyleAttribute("background", hexColor);
		this.customMid.setStyleAttribute("color", hexColor);
	}
	
	public String getMidColor(){
		if(!this.customMid.getValue().equalsIgnoreCase(" ")){
			return this.customMid.getValue();
		}else{
			return null;
		}	
	}
	
	public void setStartColor(String hexColor){
		this.customStart.setValue(hexColor);
		this.customStart.setStyleAttribute("background", hexColor);
		this.customStart.setStyleAttribute("color", hexColor);
	}
	
	public String getStartColor(){
		if(!this.customStart.getValue().equalsIgnoreCase(" ")){
			return this.customStart.getValue();
		}else{
			return null;
		}	
	}
	
	public FormPanel getClassifyForm(){
		return this.classifyForm;
	}
	
	public void setEndColor(String hexColor){
		this.customEnd.setValue(hexColor);
		this.customEnd.setStyleAttribute("background", hexColor);
		this.customEnd.setStyleAttribute("color", hexColor);
	}
	
	public String getEndColor(){
		if(!this.customEnd.getValue().equalsIgnoreCase(" ")){
			return this.customEnd.getValue();
		}else{
			return null;
		}	
	}

	private native void getClassification(String attributesValue,
			String methodValue, String intervalsValue,
			boolean openIntervalsValue, String colorRampValue,
			String[] customValues)/*-{
			
			var geoUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::geoserverUrl;
			var layName = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::layerName; 
			
			var url = geoUrl + "rest/sldservice/" + layName + "/classify?attribute=" + attributesValue + "&method=" + methodValue + "&intervals=" + intervalsValue + "&open=" + openIntervalsValue + "&ramp=" + colorRampValue;
			
			if (customValues != null) {
				if(customValues[0] != null && customValues[2] != null){
					customValues[0] = "%23"+customValues[0].substr(1);
					customValues[2] = "%23"+customValues[2].substr(1);
					
					if(customValues[1] != null){
						customValues[1] = "%23"+customValues[1].substr(1);						
						url += "&startColor="+customValues[0]+"&endColor="+customValues[1]+"&minColor="+customValues[2];
					}else{
						url += "&startColor="+customValues[0]+"&endColor="+customValues[1];
					}
				}				
			}

			$wnd.OpenLayers.Request.GET({
				url: url,
				callback: classHandler,
				scope: this
			});
			
			function classHandler(req){
				try{
					var classRules = $wnd.JSON.parse(req.responseText);
					
					var sld_rule = " ";
					var divTable = "<table><tbody>";
					
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
						
						divTable += "<tr><td bgcolor='"+fillColor+"' width='15'></td><td style='font-size:12px'>"+polyName+"</td></tr>";
			
						
						sld_rule += createSLDPolygonSymbolizer(polygonFill, polygonStroke);
						sld_rule += '</Rule>';
					}
					
					divTable += "</tbody></table>";
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::classifyRules = sld_rule;
					
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::table = divTable;				
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::fillPaneTable()();
					
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::toClassifySuccess()();					
				}catch(e){ 
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::toClassifyFailed()();	
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
				
			var geoUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::geoserverUrl;
			var name = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::layerStyle; 
			
			var st = '<StyledLayerDescriptor xmlns="http://www.opengis.net/sld" version="1.0.0" xsi:schemaLocation="http://www.opengis.net/sld http://schemas.opengis.net/sld/1.0.0/StyledLayerDescriptor.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ogc="http://www.opengis.net/ogc">';
			
			var url;
			if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::isJoined){
				url = geoUrl + "rest/styles/" + name;
			}else{								
				var id = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::geoviewid;			
				if (name.indexOf(id) != -1){
					url = geoUrl + "rest/styles/" + name;
				}else{
					url = geoUrl + "rest/styles/" + name + id;
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::newStyleName = name + id;
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::setNewStyleName()();
				}	    
			}
			
			st += '<NamedLayer><Name>Default Polygon</Name><UserStyle><Title>A boring default style</Title><Abstract>A sample style that just prints out a transparent red interior with a red outline</Abstract><FeatureTypeStyle>';
			
			st += this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::classifyRules;
			
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
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.SldClassifier::refreshLayer()();
			}
	}-*/;
	
	public void setNewStyleName(){
		this.styler.setNewStyleNameByClassification(this.newStyleName);
	}
	
	private void refreshLayer(){
		this.intervallWindow.hide();
		this.styler.refreshLayer();
	}
	
	private void toClassifyFailed(){		
		statusMsg.clearStatus("Ready");
		Info.display("Classifying Rules", "There were problems while generating rules!");
	}
	
	private void toClassifySuccess(){
		statusMsg.clearStatus("Ready");
		Info.display("Generating Rules", "Rules succesfully generated!");
	}

	private void fillPaneTable() {

		if (intervallWindow != null) {
			intervallWindow.hide();
			intervallWindow = null;
		}

		intervallWindow = new Window();

		intervallWindow.setTitle("Classification Intervals");
		intervallWindow.setHeading("Classification Intervals");
		intervallWindow.setCollapsible(false);
		intervallWindow.setWidth(200);
		intervallWindow.setHeight(250);
		intervallWindow.setPosition(10, 350);
		intervallWindow.setPlain(true);

		VerticalPanel panel = new VerticalPanel();
		panel.add(new HTML(this.table.toString()));
		
		intervallWindow.add(panel);
		intervallWindow.show();

		applyBtn.setEnabled(true);
	}
	
	public class AttributeName extends BaseModelData {


		private static final long serialVersionUID = -6887373558529396616L;

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
	
	public class ColorRampType extends BaseModelData {


		private static final long serialVersionUID = 3505244030039950450L;

		public ColorRampType() {

		  }

		  public ColorRampType(String property, String ramp) {
		    setProperty(property);
		    setRamp(ramp);
		  }
		  
		  public String getProperty() {
		    return get("property");
		  }

		  public void setProperty(String property) {
		    set("property", property);
		  }

		  public String getRamp() {
		    return get("ramp");
		  }

		  public void setRamp(String ramp) {
		    set("ramp", ramp);
		  }
	}
	
	public class MethodType extends BaseModelData {


		private static final long serialVersionUID = -496569058538194197L;

		public MethodType() {

		  }

		  public MethodType(String property, String method) {
		    setProperty(property);
		    setMethod(method);
		  }
		  
		  public String getProperty() {
		    return get("property");
		  }

		  public void setProperty(String property) {
		    set("property", property);
		  }

		  public String getMethod() {
		    return get("method");
		  }

		  public void setMethod(String method) {
		    set("method", method);
		  }
	}
}
