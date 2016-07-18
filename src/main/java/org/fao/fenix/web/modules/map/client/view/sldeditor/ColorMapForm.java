package org.fao.fenix.web.modules.map.client.view.sldeditor;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.colorpicker.ui.FenixColorPicker;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;


/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class ColorMapForm {

	private FenixColorPicker colorPicker = null;
	private JavaScriptObject colorMap;
	private Table table;
	private FormPanel form;
	private Styler styler;
	
	
	public ColorMapForm(Table table, Styler styler) {
		this.table = table;
		this.styler = styler;
	}
	
	public FormPanel buildForm(){
		
		form = new FormPanel();		
		form.setFrame(true);
		form.setItemId("ColorMapForm");
		form.setHeaderVisible(false);
		form.setAutoHeight(true);
		form.setVisible(false);
		form.setWidth(370);	
		
		FormLayout layout = new FormLayout();
		layout.setLabelWidth(50);
		layout.setLabelPad(5);
		form.setLayout(layout);
		
		final TextField<String> color = new TextField<String>();
		color.setFieldLabel("Color");	
		color.setWidth(40);					
		color.setValue(" ");
		color.addListener(Events.OnClick, new Listener<ComponentEvent>(){
			public void handleEvent(ComponentEvent be) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}
				showColorPicker(color.getItemId(), color.getElement());		        
			}		
		});
		form.add(color);
		
		final NumberField quantity = new NumberField();
		quantity.setFieldLabel("Quantity");	
		quantity.setWidth(40);					
		form.add(quantity);
		
		final TextField<String> label = new TextField<String>();
		label.setFieldLabel("Label");	
		label.setWidth(40);					
		label.setValue("");
		form.add(label);
		
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
		
		final ComboBox<OpacityValue> opacity = new ComboBox<OpacityValue>();	
		opacity.setTriggerAction(TriggerAction.ALL);
		opacity.setDisplayField("property");
		opacity.setFieldLabel("opacity");		
		opacity.setEditable(false);
		opacity.setStore(opacityStore);
		opacity.setValue(opacityStore.getAt(9));
		form.add(opacity);		
		
		SelectionListener<ButtonEvent> applyListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				String[] result = checkFieldValueBeforeInsert(color, quantity, label, opacity);
				
				String colorValue = result[0];
				String quantityValue = result[1];
				String labelValue = result[2];
				String opacityValue = result[3];
				
				if(!colorValue.equalsIgnoreCase("null") && !quantityValue.equalsIgnoreCase("null") && 
						!labelValue.equalsIgnoreCase("null") && !opacityValue.equalsIgnoreCase("null")){
					double doubleCell = checkQuantityPosition(quantityValue);
					if(doubleCell != -1){
						if(addColorMapEntry(colorValue, quantityValue, labelValue, opacityValue, doubleCell)){
							styler.updateColorMap(colorMap);
						}
					}else{
						FenixAlert.info("Error", "There were problems while inserting quantity value!");
					}					
				}				
			}
		};
		
		SelectionListener<ButtonEvent> closeListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				form.hide();
				table.enable();
				styler.getApplyButton().enable(); // TODO: check this is the right button to enable
			}
		};		
		
		form.addButton(new Button("Apply", applyListener));
		form.addButton(new Button("Close", closeListener));
		
		return form;
	}
	
	protected double checkQuantityPosition(String quantity){
		Double quantityValue = Double.parseDouble(quantity);
		
		for(int i=0; i<table.getItemCount(); i++){
			try{
				Double doubleCella = Double.parseDouble(table.getItem(i).getValue(1).toString());
				
				if(i == 0 && quantityValue < doubleCella){
					return doubleCella.doubleValue();
				}else if(i == table.getItemCount()-1 && quantityValue > doubleCella){
					return doubleCella.doubleValue();
				}else if(quantityValue > doubleCella){
					Double doubleCellb = Double.parseDouble(table.getItem(i+1).getValue(1).toString());
					
					if(quantityValue < doubleCellb){
						return doubleCellb.doubleValue();
					}
				}
			}catch(NumberFormatException exc1){
				break;		
			}
		}
		
		return -1;
	}
	
	private native boolean addColorMapEntry(String color, String quantity, String label, 
			String opacity, double doubleCell)/*-{
		var cmap = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMapForm::colorMap;
		var format = new $wnd.OpenLayers.Format.XML();			
		
		try{			
			var childNodes = cmap.childNodes;			
			var node;
			
			for(var child=0; child<childNodes.length; child++){
 				if(childNodes[child].attributes == null || childNodes[child].attributes == undefined) continue; 				
 				
 				var nodeValue = parseFloat(childNodes[child].attributes.getNamedItem("quantity").nodeValue);
 				if(doubleCell == nodeValue){
 					
 					var lastNode = cmap.lastChild;
					while (lastNode.nodeType != 1){
					  lastNode = lastNode.previousSibling;
					}
					
					var lastQuantityValue = lastNode.attributes.getNamedItem("quantity").nodeValue;
					
 					if(doubleCell != lastQuantityValue){ 						
	 					node = childNodes[child];
	 					var new_entry = format.createElementNS("","ColorMapEntry");
						format.setAttributeNS(new_entry,'','color',color);
						format.setAttributeNS(new_entry,'','quantity',quantity);
						format.setAttributeNS(new_entry,'','label',label);
						format.setAttributeNS(new_entry,'','opacity',opacity);
						cmap.insertBefore(new_entry, node);
						
						this.@org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMapForm::colorMap = cmap;
						return true;
 					}else{
 						var newQuantity = parseFloat(quantity);
 						
 						if(newQuantity < doubleCell){
		 					node = childNodes[child];
		 					var new_entry = format.createElementNS("","ColorMapEntry");
							format.setAttributeNS(new_entry,'','color',color);
							format.setAttributeNS(new_entry,'','quantity',quantity);
							format.setAttributeNS(new_entry,'','label',label);
							format.setAttributeNS(new_entry,'','opacity',opacity);
							cmap.insertBefore(new_entry, node);
							
							this.@org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMapForm::colorMap = cmap;
							return true;
 						}else{
							if(navigator.appName.indexOf("Netscape") != -1){
								var new_entry = format.createElementNS("","ColorMapEntry");
								format.setAttributeNS(new_entry,'','color',color);
								format.setAttributeNS(new_entry,'','quantity',quantity);
								format.setAttributeNS(new_entry,'','label',label);
								format.setAttributeNS(new_entry,'','opacity',opacity);
								cmap.appendChild(new_entry);
								
							}else if(navigator.appName.indexOf("Microsoft Internet Explorer") != -1){
								
								var new_entry = format.createElementNS("","ColorMapEntry");
								format.setAttributeNS(new_entry,'','color',color);
								format.setAttributeNS(new_entry,'','quantity',quantity);
								format.setAttributeNS(new_entry,'','label',label);
								format.setAttributeNS(new_entry,'','opacity',opacity);
								cmap.appendChild(new_entry);
							}						
							
							this.@org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMapForm::colorMap = cmap;
							return true;
 						}
 					}
 				}
			}
			
			return false;
		}catch(e){
			return false;
		}
		
	}-*/;
	
	public void setColorMap(JavaScriptObject colorMap){
		this.colorMap = colorMap;
	}
	
	public FormPanel getForm(){
		return this.form;
	}
	
	private void showColorPicker(String fieldId, Element element){
		this.colorPicker = new FenixColorPicker(fieldId, element);
	} 
	
	private String[] checkFieldValueBeforeInsert(TextField<String> color, NumberField quantity, 
			TextField<String> label, ComboBox<OpacityValue> opacity){
		
		String[] fieldsValue = new String[4];
		
		if (color.isDirty()){
        	Object colorValue = color.getValue();
        	
			// ////////////////
			// color value should be a non-empty string
			// ////////////////
        	
        	if(colorValue.toString().indexOf("#") != -1) {
        		fieldsValue[0] = colorValue.toString();
       		}else{
    			FenixAlert.info("Error", "The color value is incorrect !");
    			fieldsValue[0] = "null";
       		}
		}else{
			FenixAlert.info("Error", "The color value is missing !");
			fieldsValue[0] = "null";
   		}		
		
		if(quantity.isDirty()){	
			Number quantityValue = quantity.getValue();
			
			if (quantityValue != null){
				Double doubleQuantity = quantityValue.doubleValue();
				
				if(doubleQuantity != null){
					fieldsValue[1] = doubleQuantity.toString();
				}else{
	       			FenixAlert.info("Error", "The quantity value is incorrect !");
	       			fieldsValue[1] = "null";
	       		}					
			}
		}else{
   			FenixAlert.info("Error", "The quantity value is missing !");
   			fieldsValue[1] = "null";
   		}
		
		if (label.isDirty()){
        	Object labelValue = label.getValue();
        	
			// ////////////////
			// label value should be a non-empty string
			// ////////////////
        	
        	if(labelValue != null) {
        		fieldsValue[2] = labelValue.toString();
       		}else{
       			FenixAlert.info("Error", "The label value is incorrect !");
       			fieldsValue[2] = "null";
       		}
		}else{
   			fieldsValue[2] = "undefined";
   		}		
		
		if (opacity.isDirty() || opacity.getValue().getProperty().equalsIgnoreCase("100%")){
			Double opacityValue = opacity.getValue().getOpacity();
			
			if(opacityValue != null){
				Float floatOpacity = opacityValue.floatValue();
				
				// ////////////////
				// stroke opacity should be 0.0 - 1.0
				// ////////////////
				
	        	if(floatOpacity >= 0 && floatOpacity <= 1) {
	        		fieldsValue[3] = floatOpacity.toString();
	        	}else{
	       			FenixAlert.info("Error", "The quantity value is incorrect !");
	       			fieldsValue[3] = "null";
	       		}		        	
			}else{
       			fieldsValue[3] = "1.0";
       		}
		}
		
		return fieldsValue;  		
	}
	
	public class OpacityValue extends BaseModelData {

		  private static final long serialVersionUID = 8884976835118007918L;

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
