package org.fao.fenix.web.modules.map.client.view.sldeditor;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.colorpicker.ui.FenixColorPicker;
import org.fao.fenix.web.modules.map.client.view.sldeditor.io.ExternalGraphicFileUploader;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class PointSymbolizerForm implements AbstractSymbolizerForm {

	private FormPanel pointForm;
	private JavaScriptObject rule;
	private JavaScriptObject symbolizer;
	private int index;	
	
	private NumberField pointSize;
	private ComboBox<PointSymbolizerType> pointType;
	private TextField<String> externalGraphic;
	
	private CheckBox outline;
	private NumberField strokeWidth;
	private TextField<String> strokeColor;
	private ComboBox<OpacityValue> strokeOpacity;
	private CheckBox fill;
	private TextField<String> fillColor;
	private ComboBox<OpacityValue> fillOpacity;
	private FenixColorPicker colorPicker = null;

    
	
	public PointSymbolizerForm(JavaScriptObject rule, int index) {
		this.rule = rule;
		this.index = index;
		configurePointSymbolizerForm(rule);
		createPointSymbolizerForm();
	}
	
	private native void configurePointSymbolizerForm(JavaScriptObject rule)/*-{
		this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer = rule.symbolizer["Point"] || {};
	}-*/;
	
	private void createPointSymbolizerForm(){
		
		FormPanel form = new FormPanel();		
		form.setFrame(true);
		form.setHeaderVisible(false);
		form.setAutoHeight(true);
		form.setWidth(370);		
		
		FormLayout layout = new FormLayout();
		layout.setLabelWidth(50);
		layout.setLabelPad(5);
		form.setLayout(layout);
		
		Number num;
		JavaScriptObject obj;		
		
		// ///////////////
		// size, type and external initialization
		// ///////////////
		
		pointSize = new NumberField();
		pointSize.setFieldLabel("Size");
		pointSize.setWidth(40);
		obj = getValue("size", "");	
		try{
			num = Integer.parseInt(obj.toString());
		}catch(NumberFormatException exc){			
			num = 1;			
		}	
		pointSize.setValue(num);
		form.add(pointSize);				
		
	    List<PointSymbolizerType> types = new ArrayList<PointSymbolizerType>();
	    types.add(new PointSymbolizerType("square", "square"));
	    types.add(new PointSymbolizerType("circle", "circle"));
	    types.add(new PointSymbolizerType("triangle", "triangle"));
	    types.add(new PointSymbolizerType("star", "star"));
	    types.add(new PointSymbolizerType("cross", "cross"));
	    types.add(new PointSymbolizerType("X", "X"));
	    types.add(new PointSymbolizerType("external", "external"));
	  	
		ListStore<PointSymbolizerType> store = new ListStore<PointSymbolizerType>();
		store.add(types);
		
		pointType = new ComboBox<PointSymbolizerType>();
		pointType.setTriggerAction(TriggerAction.ALL);
		pointType.setDisplayField("property");
		pointType.setFieldLabel("value");
		pointType.setEditable(false);
		pointType.setStore(store);	
		obj = getValue("type", "");
		for(int y=0; y<7; y++){
			if(obj.toString().equalsIgnoreCase(store.getAt(y).getValue())){
				pointType.setValue(store.getAt(y));
			}
		}
		pointType.addListener(Events.SelectionChange, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {					
				if(pointType.getValue().getProperty().equalsIgnoreCase("external")){
					if(!externalGraphic.isEnabled()){
						externalGraphic.setEnabled(true);
					}					
				}else{
					if(externalGraphic.isEnabled()){
						externalGraphic.setEnabled(false);
					}
				}
			}
		});
		form.add(pointType);
		
		externalGraphic = new TextField<String>();
		externalGraphic.setFieldLabel("External Graphic");	
		externalGraphic.setWidth(40);		
		if(isExternal() != null){
			externalGraphic.setEnabled(true);
			externalGraphic.setValue(isExternal().toString());
		}else{
			externalGraphic.setEnabled(false);
		}		
		externalGraphic.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				new ExternalGraphicFileUploader(externalGraphic);
			}		
		});		
		form.add(externalGraphic);

		// ///////////////
		// outline initialization
		// ///////////////		

		strokeWidth = new NumberField();	
		strokeWidth.setFieldLabel("Width");
		strokeWidth.setWidth(40);		
		obj = getValue("stroke", "width");		
		try{
			num = Integer.parseInt(obj.toString());
		}catch(NumberFormatException exc){
			try{
				num = Double.parseDouble(obj.toString());
			}catch(NumberFormatException exc1){
				num = 1.0;
			}
		}
		strokeWidth.setValue(num);
		
		strokeColor = new TextField<String>();
		strokeColor.setFieldLabel("Color");	
		strokeColor.setWidth(40);		
		obj = getValue("stroke", "color");		
		strokeColor.setValue(obj.toString());
		strokeColor.setStyleAttribute("background", obj.toString());
		strokeColor.setStyleAttribute("color", obj.toString());
		strokeColor.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}				
				showColorPicker("stroke");		        
			}		
		});		
		
		List<OpacityValue> opacityList = new ArrayList<OpacityValue>();
		opacityList.add(new OpacityValue("10%", 0.1));
		opacityList.add(new OpacityValue("20%", 0.2));
		opacityList.add(new OpacityValue("30%", 0.3));
		opacityList.add(new OpacityValue("40%", 0.4));
		opacityList.add(new OpacityValue("50%", 0.5));
		opacityList.add(new OpacityValue("60%", 0.6));
		opacityList.add(new OpacityValue("70%", 0.7));
		opacityList.add(new OpacityValue("80%", 0.8));
		opacityList.add(new OpacityValue("90%", 0.9));
		opacityList.add(new OpacityValue("100%", 1.0));
	  	 
		ListStore<OpacityValue> opacityStore = new ListStore<OpacityValue>();
		opacityStore.add(opacityList);		
		
		strokeOpacity = new ComboBox<OpacityValue>();
		strokeOpacity.setTriggerAction(TriggerAction.ALL);
		strokeOpacity.setDisplayField("property");
		strokeOpacity.setFieldLabel("opacity");		
		strokeOpacity.setEditable(false);
		strokeOpacity.setStore(opacityStore);
		obj = getValue("stroke", "opacity");
		
		String result = obj.toString();
		if(result.equalsIgnoreCase(" ")){
			strokeOpacity.setValue(opacityStore.getAt(9));	
		}else{
			int valueAt;
			try{
				if(result.indexOf(".") != -1 || result.indexOf(",") != -1){					
					if(result.indexOf(",") != -1)result = result.replaceAll(",", ".");	
					double val = Double.parseDouble(result);
					valueAt = (int)(val*10);
					valueAt--;
				}else{
					valueAt = Integer.parseInt(result);
					if(valueAt == 1) valueAt = 9;
				}				
			}catch(NumberFormatException exc){
				valueAt = 9;
			}		
			strokeOpacity.setValue(opacityStore.getAt(valueAt));				
		}					
		
		outline = new CheckBox();
		outline.setWidth(40);
		outline.setFieldLabel("Outline");
		outline.setEnabled(true);
		
		boolean bool = strokeIsExist(this.symbolizer);
		
		if(bool){	
			outline.setValue(true);
		}else{
			outline.setValue(false);
			strokeWidth.setEnabled(false);
			strokeColor.setEnabled(false);
			strokeOpacity.setEnabled(false);
		}		
			
		outline.addListener(Events.Change, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {			
				if(outline.getValue()){
					strokeWidth.setEnabled(true);
					strokeColor.setEnabled(true);
					strokeOpacity.setEnabled(true);
				}else{
					strokeWidth.setEnabled(false);
					strokeColor.setEnabled(false);
					strokeOpacity.setEnabled(false);
				}		
			}		
		});			
		form.add(outline);			

		form.add(strokeWidth);	
		
		form.add(strokeColor);				
		
		form.add(strokeOpacity);		
		
		// ///////////////
		// fill initialization
		// ///////////////		
		
		fillColor = new TextField<String>();
		fillColor.setFieldLabel("Color");	
		fillColor.setWidth(40);	
		fillColor.setItemId("pointFillColor"+index);
		obj = getValue("fill", "color");		
		fillColor.setValue(obj.toString());
		fillColor.setStyleAttribute("background", obj.toString());
		fillColor.setStyleAttribute("color", obj.toString());
		fillColor.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}	
		        showColorPicker("fill");
			}		
		});
		
		fillOpacity = new ComboBox<OpacityValue>();
		fillOpacity.setTriggerAction(TriggerAction.ALL);
		fillOpacity.setDisplayField("property");
		fillOpacity.setFieldLabel("opacity");	
		fillOpacity.setItemId("pointFillOpacity"+index);
		fillOpacity.setEditable(false);
		fillOpacity.setStore(opacityStore);
		obj = getValue("fill", "opacity");
		
		result = obj.toString();
		if(result.equalsIgnoreCase(" ")){
			fillOpacity.setValue(opacityStore.getAt(9));	
		}else{			
			int valueAt;
			try{
				if(result.indexOf(".") != -1 || result.indexOf(",") != -1){					
					if(result.indexOf(",") != -1)result = result.replaceAll(",", ".");	
					double val = Double.parseDouble(result);
					valueAt = (int)(val*10);
					valueAt--;
				}else{
					valueAt = Integer.parseInt(result);
					if(valueAt == 1) valueAt = 9;
				}				
			}catch(NumberFormatException exc){
				valueAt = 9;
			}		
			fillOpacity.setValue(opacityStore.getAt(valueAt));				
		}			
		
		fill = new CheckBox();
		fill.setFieldLabel("Fill");
		fill.setItemId("pointFill"+index);
		fill.setWidth(40);
		fill.setEnabled(true);
		
		bool = fillIsExist(this.symbolizer);
		
		if(bool){	
			fill.setValue(true);
		}else{
			fill.setValue(false);
			fillColor.setEnabled(false);
			fillOpacity.setEnabled(false);
		}
		
		fill.addListener(Events.Change, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if(fill.getValue()){
					fillColor.setEnabled(true);
					fillOpacity.setEnabled(true);
				}else{
					fillColor.setEnabled(false);
					fillOpacity.setEnabled(false);
				}			
			}		
		});		
		form.add(fill);		
		
		form.add(fillColor);			
		
		form.add(fillOpacity);
		
		this.pointForm = form;
	}
	
	private void showColorPicker(String field){
		this.colorPicker = new FenixColorPicker(this, field);
	} 
   
	public void setStrokeColor(String hexColor){
		this.strokeColor.setValue(hexColor);
		this.strokeColor.setStyleAttribute("background", hexColor);
		this.strokeColor.setStyleAttribute("color", hexColor);
	}
	
	public String getStrokeColor(){
		if(!this.strokeColor.getValue().equalsIgnoreCase(" ")){
			return this.strokeColor.getValue();
		}else{
			return null;
		}		
	}
	
	public void setFillColor(String hexColor){
		this.fillColor.setValue(hexColor);
		this.fillColor.setStyleAttribute("background", hexColor);
		this.fillColor.setStyleAttribute("color", hexColor);
		
		Table table = Styler.table;
		TableItem tabItem = table.getItem(index);
		
		tabItem.setValue(0, hexColor);		
		String itemColor = tabItem.getValue(0).toString();
		Element cell = getTextCellElement(tabItem, 0);
		DOM.setStyleAttribute(cell, "background", itemColor);
		DOM.setStyleAttribute(cell, "color", itemColor);
	}
	
	public String getFillColor(){
		if(!this.fillColor.getValue().equalsIgnoreCase(" ")){
			return this.fillColor.getValue();
		}else{
			return null;
		}		
	}
	
	public Element getTextCellElement(TableItem item, int cell) {
	    return getTextCellInternal(item.getElement(), cell);
	}
	
	public native Element getTextCellInternal(Element elem, int column) /*-{
		return elem.firstChild.firstChild.firstChild.childNodes[column].firstChild.firstChild;
	}-*/;
	
	private native boolean strokeIsExist(JavaScriptObject symbolizer)/*-{	
		if(symbolizer.strokeColor != undefined && symbolizer.strokeColor != null){
			return true;
		}else{
			return false;
		}
	}-*/;
	
	private native boolean fillIsExist(JavaScriptObject symbolizer)/*-{	
		if(symbolizer.fillColor != undefined && symbolizer.fillColor != null){
			return true;
		}else{
			return false;
		}
	}-*/;
	
	private native JavaScriptObject getValue(String field, String parameter)/*-{		
		if (field == "stroke"){
			if (parameter == "width"){
				var result = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.strokeWidth;
				if(result == undefined || result == null){
					result = 0;
				}
				return result;
			}else if (parameter == "color"){
				var result = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.strokeColor;
				if(result == undefined || result == null){
					result = " ";
				}
				return result;
			}else if (parameter == "opacity"){
				var result = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.strokeOpacity;
				if(result == undefined || result == null){
					result = " ";
				}
				return result;
			}
		}else if (field == "fill"){
			if (parameter == "color"){
				var result = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.fillColor;
				if(result == undefined || result == null){
					result = " ";
				}
				return result;
			}else if (parameter == "opacity"){
				var result = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.fillOpacity;
				if(result == undefined || result == null){
					result = " ";
				}
				return result;
			}
		}else if (field == "size"){
			var result = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.pointRadius;
			if(result == undefined || result == null){
				result = 1;
			}
			return result;
		}else if (field == "type"){
			var result = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.graphicName;
			if(result == undefined || result == null){
				result = "external";
			}
			return result;
		}
		
		return null;		
	}-*/;
	

	/**
	 * @deprecated use getSymbolizerForm
	 */
	public FormPanel getPointSymbolizerForm(){
		return this.pointForm;
	}

	public FormPanel getSymbolizerForm(){
		return this.pointForm;
	}
	
	public JavaScriptObject updateSymbolizer(){
		
		JavaScriptObject tempSymbolizer = this.symbolizer;
		boolean modified = false;		
		
		// ////////////////////////////// //
		// update point radius properties //      
		// ////////////////////////////// //
		
		if(this.pointSize.isDirty()){			
        	Number pointRadius = this.pointSize.getValue(); 
        	
        	if (pointRadius != null){
        		float floatPointRadius = pointRadius.floatValue();
            	
            	if(floatPointRadius >= 0) {
            		
        			// ////////////////
        			// point radius should be positive float
        			// ////////////////
            		
            		tempSymbolizer = modifyPointSize(tempSymbolizer, floatPointRadius);
    				modified = true;
            	}
        	}
		}
		
		String stringType = this.pointType.getValue().getValue();
    	if(stringType.equalsIgnoreCase("external")) {    		
    		Object extGraphic = this.externalGraphic.getValue();
    		String stringExtGraphic = extGraphic.toString();
    		
    		// //////////////////////////
    	    // external graphic should be a non-empty string
    		// //////////////////////////
    		
    	    if(stringExtGraphic != null) {
    	    	tempSymbolizer = modifyExternalGraphic(tempSymbolizer, stringExtGraphic);
       		    modified = true;       		
        	}
    	} else {
    		
    		// ////////////////
        	// TODO: deal with named symbols
    		// ////////////////
    		
			String wellKnownName = stringType;
        	if(wellKnownName != null) {
        		tempSymbolizer = modifyWellKnownName(tempSymbolizer, wellKnownName);
            	modified = true;
        	}
        	
        	// //////////////////////// //
    		// update stroke properties //
    		// //////////////////////// //
    		
    		if(this.outline.getValue()){
    			
    			if(this.strokeWidth.isDirty()){
    				Number strokeWidth = this.strokeWidth.getValue();
    				
    				if (strokeWidth != null){
    					float floatStrokeWidth = strokeWidth.floatValue();
    					
    					// ////////////////
    					// stroke width should be a positive float
    					// ////////////////
    					
    					if(floatStrokeWidth >= 0){
    						tempSymbolizer = modifyStrokeWidth(tempSymbolizer, floatStrokeWidth);
    						modified = true;
    					}
    				}
    			}
    			
    			if (this.strokeOpacity.isDirty() || !strokeIsExist(tempSymbolizer)){
    				Double strokeOpacity = this.strokeOpacity.getValue().getOpacity();
    				
    				if (strokeOpacity != null){
    					float floatStrokeOpacity = strokeOpacity.floatValue();
    					
    					// ////////////////
    					// stroke opacity should be 0.0 - 1.0
    					// ////////////////

    		        	if(floatStrokeOpacity >= 0 && floatStrokeOpacity <= 1) {
    		        		tempSymbolizer = modifyStrokeOpacity(tempSymbolizer, floatStrokeOpacity);
    		            	modified = true;
    		        	}
    				}
    			}
            	
    			if (this.strokeColor.isDirty()){
    	        	Object strokeColor = this.strokeColor.getValue();
    	        	
    				// ////////////////
    				// stroke color should be a non-empty string
    				// ////////////////
    	        	
    	        	if(strokeColor.toString() != null) {
    	        		tempSymbolizer = modifyStrokeColor(tempSymbolizer, strokeColor.toString());
    	        	    modified = true;
    	       		}
    			}
    		}else if(strokeIsExist(tempSymbolizer)){	
    			tempSymbolizer = resetStroke(tempSymbolizer);
    			modified = true;	
    		} 
    		
    		// ////////////////////// //
    		// update fill properties // 
    		// ////////////////////// //
    		
    		if(this.fill.getValue()){
    			
    			if (this.fillOpacity.isDirty() || !fillIsExist(tempSymbolizer)){	
    				Double fillOpacity = this.fillOpacity.getValue().getOpacity();
    				
    				if (fillOpacity != null){
    					float floatFillOpacity = fillOpacity.floatValue();
    					
    					// ////////////////
    					// fill opacity should be 0.0 - 1.0
    					// ////////////////

    		        	if(floatFillOpacity >= 0 && floatFillOpacity <= 1) {
    		        		tempSymbolizer = modifyFillOpacity(tempSymbolizer, floatFillOpacity);
    		            	modified = true;
    		        	}
    				}
    			}
            	
    			if (this.fillColor.isDirty()){
    	        	Object fillColor = this.fillColor.getValue();
    	        	
        			// ////////////////
        			// fill color should be a non-empty string
        			// ////////////////
    	        	
    	        	if(fillColor.toString() != null) {        		
    	        		tempSymbolizer = modifyFillColor(tempSymbolizer, fillColor.toString());
    	        	    modified = true;
    	       		}		
    			}
    		}else if(fillIsExist(tempSymbolizer)){
    			tempSymbolizer = resetFill(tempSymbolizer);
    			modified = true;	
    		}  
    	}		
		
		if (modified){			
			return modifySymbolizer(this.symbolizer, tempSymbolizer, this.pointType.getValue().getValue());			
		}else return null;			
	}
	
	private native JavaScriptObject resetFill(JavaScriptObject tempSymbolizer)/*-{
		tempSymbolizer.fillColor = null;
		tempSymbolizer.fillOpacity = null;
		return tempSymbolizer;
	}-*/;
	
	private native JavaScriptObject resetStroke(JavaScriptObject tempSymbolizer)/*-{
		tempSymbolizer.strokeColor = null;
		tempSymbolizer.strokeWidth = null;
		tempSymbolizer.strokeOpacity = null;
		return tempSymbolizer;
	}-*/;
	
	private native JavaScriptObject modifyPointSize(JavaScriptObject tempSymbolizer, float floatPointRadius)/*-{		
		tempSymbolizer.pointRadius = floatPointRadius;
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifyExternalGraphic(JavaScriptObject tempSymbolizer, String stringExtGraphic)/*-{		
		if(tempSymbolizer.graphicName != undefined || tempSymbolizer.graphicName != null){
 			tempSymbolizer.graphicName = null;
 		} 
		tempSymbolizer.externalGraphic = stringExtGraphic;
		
		if (stringExtGraphic.indexOf(".png") != -1){
			tempSymbolizer.graphicFormat = "image/png";
		}else if (stringExtGraphic.indexOf(".jpeg") != -1){
			tempSymbolizer.graphicFormat = "image/jpeg";
		}else if (stringExtGraphic.indexOf(".gif") != -1){
			tempSymbolizer.graphicFormat = "image/gif";
		}
		
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifyWellKnownName(JavaScriptObject tempSymbolizer, String wellKnownName)/*-{		
		tempSymbolizer.graphicName = wellKnownName;
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifyStrokeWidth(JavaScriptObject tempSymbolizer, float floatStrokeWidth)/*-{		
		tempSymbolizer.strokeWidth = floatStrokeWidth;
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifyStrokeOpacity(JavaScriptObject tempSymbolizer, float floatStrokeOpacity)/*-{		
		tempSymbolizer.strokeOpacity = floatStrokeOpacity;
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifyStrokeColor(JavaScriptObject tempSymbolizer, String stringStrokeColor)/*-{		
		tempSymbolizer.strokeColor = stringStrokeColor;
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifyFillOpacity(JavaScriptObject tempSymbolizer, float floatFillOpacity)/*-{		
		tempSymbolizer.fillOpacity = floatFillOpacity;
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifyFillColor(JavaScriptObject tempSymbolizer, String stringFillColor)/*-{		
		tempSymbolizer.fillColor = stringFillColor;
		return tempSymbolizer;		
	}-*/;
	
	private native JavaScriptObject modifySymbolizer(JavaScriptObject symb, JavaScriptObject tempSymb, String pointType)/*-{
 		
 		symb.pointRadius = tempSymb.pointRadius;
 		
 		if (pointType == "external"){
 		
 			if(symb.graphicName != undefined || symb.graphicName != null){
 				symb.graphicName = null;
 			} 			
 			symb.externalGraphic = tempSymb.externalGraphic;
 			symb.graphicFormat = tempSymb.graphicFormat;
 			
 		} else {
 		
 			if(symb.externalGraphic != undefined || symb.externalGraphic != null){
 				symb.externalGraphic = null;
 			}  
 			 
    		symb = tempSymb;			
 		} 	
 		 		
		return symb;		
	}-*/;
	
	private native JavaScriptObject isExternal()/*-{
		var ext = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.PointSymbolizerForm::symbolizer.externalGraphic;
		
		if(ext != null || ext != undefined){
			return ext;
		}else return null;
	}-*/;
	
	public class PointSymbolizerType extends BaseModelData {


		private static final long serialVersionUID = -8359457407443006649L;

		public PointSymbolizerType() {

		  }

		  public PointSymbolizerType(String property, String value) {
		    setProperty(property);
		    setValue(value);
		  }
		  
		  public String getProperty() {
		    return get("property");
		  }

		  public void setProperty(String property) {
		    set("property", property);
		  }

		  public String getValue() {
		    return get("value");
		  }

		  public void setValue(String value) {
		    set("value", value);
		  }
	}
	
	public class OpacityValue extends BaseModelData {

		  private static final long serialVersionUID = 9137243087261071186L;

		  public OpacityValue() {

		  }

		  public OpacityValue(String property, Double opacity) {
		    setProperty(property);
		    setOpacity(opacity);
		  }
		  
		  public String getProperty() {
		    return get("property");
		  }

		  public void setProperty(String property) {
		    set("property", property);
		  }

		  public Double getOpacity() {
		    return get("opacity");
		  }

		  public void setOpacity(Double opacity) {
		    set("opacity", opacity);
		  }
	}
}
