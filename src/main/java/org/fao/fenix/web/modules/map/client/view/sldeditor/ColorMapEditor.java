package org.fao.fenix.web.modules.map.client.view.sldeditor;

import org.fao.fenix.web.modules.map.client.colorpicker.ui.FenixColorPicker;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TableEvent;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
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

public class ColorMapEditor {
	
	private TableEvent te;
	private Table table;
	private FenixColorPicker colorPicker = null;	
	

	public ColorMapEditor(TableEvent te, Table table) {
		this.table = table;
		this.te = te;
		
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
				
				if(te.getCellIndex() == 0){
					
					table.getItem(te.getRowIndex()).setValue(te.getCellIndex(), field.getValue());
					cell.hide();
					
					TableItem tabItem = table.getItem(te.getRowIndex());
					String itemColor = tabItem.getValue(0).toString();
					Element cellElement = getTextCellElement(tabItem, 0);
					DOM.setStyleAttribute(cellElement, "background", itemColor);
					DOM.setStyleAttribute(cellElement, "color", itemColor);	
					
				}else if(te.getCellIndex() == 1){
					
					if(checkQuantityField(te.getRowIndex(), te.getCellIndex(), field)){
						table.getItem(te.getRowIndex()).setValue(te.getCellIndex(), field.getValue());
						cell.hide();
					}else{
						FenixAlert.info("Error", "The quantity value is incorrect !");
					}

				} else if(te.getCellIndex() == 2){
					
					table.getItem(te.getRowIndex()).setValue(te.getCellIndex(), field.getValue());
					cell.hide();
					
				}else if(te.getCellIndex() == 3){
					
					try{
						Double fieldValue = Double.parseDouble(field.getValue());
						
						if(fieldValue >= 0 && fieldValue <= 1){
							table.getItem(te.getRowIndex()).setValue(te.getCellIndex(), field.getValue());
							cell.hide();
						}else{
							FenixAlert.info("Error", "The opacity value is incorrect !");
						}
					
					}catch(NumberFormatException exc1){
							FenixAlert.info("Error", "The opacity value is incorrect !");
					}
					
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
		

		cell.addButton(new Button("Apply", appListener));
		Button pickerBtn = new Button("Color Picker", pickerListener);
		pickerBtn.setEnabled(picker);
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
		this.colorPicker = new FenixColorPicker(fieldId, element);
	} 
	
	private boolean checkQuantityField(int rowIndex, int cellIndex, TextField<String> field){
		
		boolean result;
		
		if(rowIndex == 0){			
			try{
				Double middleQuantity = Double.parseDouble(field.getValue());
				Double postQuantity = Double.parseDouble(table.getItem(rowIndex + 1).getValue(cellIndex).toString());				
				
				result = middleQuantity-postQuantity<0 ? true : false;
			
			}catch(NumberFormatException exc1){
				try{
					Integer middleQuantity = Integer.parseInt(field.getValue());
					Integer postQuantity = Integer.parseInt(table.getItem(rowIndex + 1).getValue(cellIndex).toString());	
					
					result = middleQuantity-postQuantity<0 ? true : false;
				}catch(NumberFormatException exc2){
					result = false;
				}
			}
		}else if(rowIndex == table.getItemCount()-1){
			try{
				Double middleQuantity = Double.parseDouble(field.getValue());
				Double preQuantity = Double.parseDouble(table.getItem(rowIndex - 1).getValue(cellIndex).toString());			
				
				result = middleQuantity-preQuantity>0 ? true : false;
			
			}catch(NumberFormatException exc1){
				try{
					Integer middleQuantity = Integer.parseInt(field.getValue());
					Integer preQuantity = Integer.parseInt(table.getItem(rowIndex - 1).getValue(cellIndex).toString());	
					
					result = middleQuantity-preQuantity>0 ? true : false;
				}catch(NumberFormatException exc2){
					result = false;
				}
			}
		}else{
			try{
				Double middleQuantity = Double.parseDouble(field.getValue());	
				Double preQuantity = Double.parseDouble(table.getItem(rowIndex - 1).getValue(cellIndex).toString());
				Double postQuantity = Double.parseDouble(table.getItem(rowIndex + 1).getValue(cellIndex).toString());							
				
				result = middleQuantity-preQuantity>0 && middleQuantity-postQuantity<0 ? true : false;
			
			}catch(NumberFormatException exc1){
				try{
					Integer middleQuantity = Integer.parseInt(field.getValue());
					Integer preQuantity = Integer.parseInt(table.getItem(rowIndex - 1).getValue(cellIndex).toString());
					Integer postQuantity = Integer.parseInt(table.getItem(rowIndex + 1).getValue(cellIndex).toString());				
				
					result = middleQuantity-preQuantity>0 && middleQuantity-postQuantity<0 ? true : false;
				}catch(NumberFormatException exc2){
					result = false;
				}
			}			
		}		
		
		return result;		
	}	
}
