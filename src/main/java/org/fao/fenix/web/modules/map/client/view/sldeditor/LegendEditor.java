package org.fao.fenix.web.modules.map.client.view.sldeditor;

import org.fao.fenix.web.modules.map.client.colorpicker.ui.FenixColorPicker;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TableEvent;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class LegendEditor {
	
	private TableEvent te;
	private Table table;
	private Styler styler;
	private FenixColorPicker colorPicker = null;
	private SymbolizersForm sf;
	
	//private Logger LOGGER = Logger.getLogger(LegendEditor.class);
	
	
	public LegendEditor(Styler styler, SymbolizersForm sf, TableEvent te, Table table){
		this.table = table;
		this.te = te;
		this.styler = styler;
		this.sf = sf;
		
		build();
	}
	
	private void build(){
		
		boolean picker = false;
		if(te.getCellIndex() == 0) picker = true;
		
		final Window cell = new Window();
		cell.setTitle("cell");
		cell.setHeading("cell");
		cell.setCollapsible(false);
		cell.setWidth(300);
		cell.setHeight(110);	
		cell.focus();
		cell.setPlain(true);
		
		FitLayout cellLayout = new FitLayout();
		cell.setLayout(cellLayout);
		
		FormPanel form = new FormPanel();		
		form.setWidth(200);
		form.setFrame(true);
		form.setHeight(50);
		form.setHeaderVisible(false);
		
		FormLayout layout = new FormLayout();
		layout.setLabelWidth(30);
		layout.setLabelPad(5);
		form.setLayout(layout);
		
		final TextField<String> field = new TextField<String>();
		field.setFieldLabel("value");	
		field.setWidth("50");		
		field.setEnabled(true);
		
		String itemValue = table.getItem(te.getRowIndex()).getValue(te.getCellIndex()).toString();
		field.setValue(itemValue);		
		if(itemValue.indexOf("#") != -1){
			field.setStyleAttribute("background", itemValue);
			field.setStyleAttribute("color", itemValue);
		}
		form.add(field);				
		cell.add(form);
		
		SelectionListener<ButtonEvent> appListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				table.getItem(te.getRowIndex()).setValue(te.getCellIndex(), field.getValue());
				cell.hide();
				
//				LayoutContainer formPanel = sf.getSymbolizerForm();
				
				if(te.getCellIndex() == 0){
					
					TableItem tabItem = table.getItem(te.getRowIndex());
					String itemColor = tabItem.getValue(0).toString();
					Element cellElement = getTextCellElement(tabItem, 0);
					DOM.setStyleAttribute(cellElement, "background", itemColor);
					DOM.setStyleAttribute(cellElement, "color", itemColor);
					
					if(sf.isPolygonSymbolizer()){
						
						FormPanel polyForm = sf.getForm(te.getRowIndex());
						
						CheckBox polyFill = (CheckBox)polyForm.getItemByItemId("polyFill"+te.getRowIndex());
						
						TextField<String> polyFillColor = (TextField<String>)polyForm.getItemByItemId("polyFillColor"+te.getRowIndex());
						
						ComboBox polyFillOpacity = (ComboBox)polyForm.getItemByItemId("polyFillOpacity"+te.getRowIndex());
						polyFillOpacity.setTriggerAction(TriggerAction.ALL);
						polyFill.setValue(true);
						polyFillColor.setEnabled(true);	
						polyFillOpacity.setEnabled(true);
						polyFillColor.setValue(field.getValue());
						polyFillColor.setStyleAttribute("background", field.getValue());
						polyFillColor.setStyleAttribute("color", field.getValue());
						
					}else if(sf.isPointSymbolizer()){
						
						FormPanel pointForm = sf.getForm(te.getRowIndex());
						
						CheckBox pointFill = (CheckBox)pointForm.getItemByItemId("pointFill"+te.getRowIndex());
						
						TextField<String> pointFillColor = (TextField<String>)pointForm.getItemByItemId("pointFillColor"+te.getRowIndex());
						
						ComboBox pointFillOpacity = (ComboBox)pointForm.getItemByItemId("pointFillOpacity"+te.getRowIndex());
						pointFillOpacity.setTriggerAction(TriggerAction.ALL);
						
						pointFill.setValue(true);
						pointFillColor.setEnabled(true);	
						pointFillOpacity.setEnabled(true);
						pointFillColor.setValue(field.getValue());
						pointFillColor.setStyleAttribute("background", field.getValue());
						pointFillColor.setStyleAttribute("color", field.getValue());
						
					}else if(sf.isLineSymbolizer()){
						
						FormPanel lineForm = sf.getForm(te.getRowIndex());
						
						TextField<String> lineStrokeColor = (TextField<String>)lineForm.getItemByItemId("lineStrokeColor"+te.getRowIndex());
						
						ComboBox lineStrokeOpacity = (ComboBox)lineForm.getItemByItemId("lineStrokeOpacity"+te.getRowIndex());
						lineStrokeOpacity.setTriggerAction(TriggerAction.ALL);
						
						lineStrokeColor.setEnabled(true);	
						lineStrokeOpacity.setEnabled(true);
						lineStrokeColor.setValue(field.getValue());
						lineStrokeColor.setStyleAttribute("background", field.getValue());
						lineStrokeColor.setStyleAttribute("color", field.getValue());
					}
				}else{								
					styler.modifyRuleTitle(field.getValue(), te.getRowIndex());
				}	
			}  
        };  
        
		SelectionListener<ButtonEvent> pickerListener = new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (colorPicker != null){
					colorPicker.destroyFenixColorPicker();
					colorPicker = null;
				}				
				showColorPicker(field.getItemId(), field.getElement());
			}  
        };
		
		Button pickerBtn = new Button("Color Picker", pickerListener);
		pickerBtn.setEnabled(picker);

		cell.addButton(new Button("Apply", appListener));
		cell.addButton(pickerBtn);


        cell.show();
	}
	
	public static Element getTextCellElement(TableItem item, int cell) {
	    return getTextCellInternal(item.getElement(), cell);
	}
	
	public static native Element getTextCellInternal(Element elem, int column) /*-{
		return elem.firstChild.firstChild.firstChild.childNodes[column].firstChild.firstChild;
	}-*/;
	
	private void showColorPicker(String fieldId, Element element){
		//LOGGER.info(fieldId + " | " + element);
		this.colorPicker = new FenixColorPicker(fieldId, element);
	} 
}
