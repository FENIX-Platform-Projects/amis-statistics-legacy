package org.fao.fenix.web.modules.map.client.view.sldeditor;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.TableEvent;
import com.extjs.gxt.ui.client.event.TableListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.core.client.JavaScriptObject;



/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class ColorMap {
	
	private JavaScriptObject colorMap;
	private VerticalPanel pane;
	private Table table;	
	
	
	public ColorMap(JavaScriptObject colorMap) {
		this.colorMap = colorMap;	
		buildColorMapTable();
	}
	
	private void buildColorMapTable(){
		
		List<TableColumn> columns = new ArrayList<TableColumn>();		
		columns.add(new TableColumn("color", 80));
		columns.add(new TableColumn("quantity", 80));
		columns.add(new TableColumn("label", 80));
		columns.add(new TableColumn("opacity", 80));

		TableColumnModel cm = new TableColumnModel(columns);
		
		pane = new VerticalPanel();		
		pane.setWidth(370);
		pane.setHeight(200);
		pane.setScrollMode(Scroll.AUTO);
		
		TableLayout lay = new TableLayout();
		pane.setLayout(lay);		
		table = new Table(cm);
		table.setWidth(372);	
	
		JavaScriptObject[] rowArrayJS = createRowArray(colorMap);
		
		for (int j=0; j<rowArrayJS.length; j++){
			TableItem item = createTableItem(rowArrayJS[j]);
		    item.disableTextSelection(false);		    
		    table.add(item);	
		}
	    
	    table.addTableListener(new TableListener(){

			@Override
			public void tableCellDoubleClick(final TableEvent te) {
				super.tableCellDoubleClick(te);
				new ColorMapEditor(te, table);				
			}	    	
	    });
	    
	    Styler.table = table;
	    pane.add(table);
	}	
	
	private TableItem createTableItem(JavaScriptObject rowArrayJS){
		String[] row = rowArrayJS.toString().split("%");
		
	    Object[] obj = new Object[4];
	    obj[0] = row[0];
	    obj[1] = row[1];
	    obj[2] = row[2];
	    obj[3] = row[3];
	    
		TableItem item = new TableItem(obj);
		return item;
	}
	
	private native JavaScriptObject[] createRowArray(JavaScriptObject colorMap)/*-{
		var row = [];
 
 		var i = 0;
 		var childNodes = colorMap.childNodes;
 		for(var child=0; child<childNodes.length; child++){
 			if(childNodes[child].attributes == null || childNodes[child].attributes == undefined) continue;
 			
 			var attributes = [];
 			if(childNodes[child].attributes.getNamedItem("color") != undefined || childNodes[child].attributes.getNamedItem("color") != null){
 				attributes[0] = childNodes[child].attributes.getNamedItem("color").nodeValue;
 			}else{
 				attributes[0] = "undefined";
 			}
 			
 			if(childNodes[child].attributes.getNamedItem("quantity") != undefined || childNodes[child].attributes.getNamedItem("quantity") != null){
 				attributes[1] = childNodes[child].attributes.getNamedItem("quantity").nodeValue;
 			}else{
 				attributes[1] = "undefined";
 			}
 			
 			if(childNodes[child].attributes.getNamedItem("label") != undefined || childNodes[child].attributes.getNamedItem("label") != null){
 				attributes[2] = childNodes[child].attributes.getNamedItem("label").nodeValue;
 			}else{
 				attributes[2] = "undefined";
 			}
 			
 			if(childNodes[child].attributes.getNamedItem("opacity") != undefined || childNodes[child].attributes.getNamedItem("opacity") != null){
 				attributes[3] = childNodes[child].attributes.getNamedItem("opacity").nodeValue;
 			}else{
 				attributes[3] = "1.0";
 			}
			
			row[i] = attributes[0] + "%" + attributes[1] + "%" + attributes[2] + "%" + attributes[3];
 			 					
 		    i++;
 		}
		
		return row;
	}-*/;
	
	protected VerticalPanel getColorMapTable(){
		return this.pane;
	}
	
	protected String[] mergeColorMap(){
		String[] matrix = new String[table.getItemCount()];
		for(int i=0; i<table.getItemCount(); i++){
			matrix[i] = "";
			for(int j=0; j<table.getColumnCount(); j++){
				matrix[i] = matrix[i].concat(table.getItem(i).getValue(j).toString());
				if(j != table.getColumnCount()-1)matrix[i] = matrix[i].concat("%");
			}			
		}	
		
		return matrix;
	}
	
	protected native JavaScriptObject mergeJSObject(String[] matrix)/*-{
		var cmap = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMap::colorMap;
		var format = new $wnd.OpenLayers.Format.XML();	
		
		var nodeAttributes = [];
		var i = 0;		
		
 		var childNodes = cmap.childNodes;	
 		
 		for(var child=0; child<childNodes.length; child++){
 			if(childNodes[child].attributes == null || childNodes[child].attributes == undefined) continue;
			nodeAttributes = matrix[i].split("%");	
					
			try{
				if(childNodes[child].attributes.getNamedItem("color") != undefined || childNodes[child].attributes.getNamedItem("color") != null){
	 				childNodes[child].attributes.getNamedItem("color").nodeValue =  nodeAttributes[0];
	 			}else if(nodeAttributes[0] != "undefined"){ 			
	 				format.setAttributeNS(childNodes[child],'','color',nodeAttributes[0]);
	 			}
	 			
	 			if(childNodes[child].attributes.getNamedItem("quantity") != undefined || childNodes[child].attributes.getNamedItem("quantity") != null){
	 				childNodes[child].attributes.getNamedItem("quantity").nodeValue = nodeAttributes[1];
	 			}else if(nodeAttributes[1] != "undefined"){
	 				format.setAttributeNS(childNodes[child],'','quantity',nodeAttributes[1]);
	 			}
	 			
	 			if(childNodes[child].attributes.getNamedItem("label") != undefined || childNodes[child].attributes.getNamedItem("label") != null){
	 				childNodes[child].attributes.getNamedItem("label").nodeValue = nodeAttributes[2];
	 			}else if(nodeAttributes[2] != "undefined"){
	 				format.setAttributeNS(childNodes[child],'','label',nodeAttributes[2]);;
	 			}
	 			
	 			if(childNodes[child].attributes.getNamedItem("opacity") != undefined || childNodes[child].attributes.getNamedItem("opacity") != null){
	 				childNodes[child].attributes.getNamedItem("opacity").nodeValue = nodeAttributes[3];
	 			}else if(nodeAttributes[3] != "undefined"){
	 				format.setAttributeNS(childNodes[child],'','opacity',nodeAttributes[3]);
	 			}
	 			
	 			i++;
			
			}catch(e){
			}			
 		}
 		
 		return cmap;
	}-*/;
	
	public double calculateTableItemIndex(TableItem item){
		
		Double quantity = 0.0;
		
		for(int i=0; i<table.getItemCount(); i++){
			if(item.getValue(1).toString().equalsIgnoreCase(table.getItem(i).getValue(1).toString())){
				quantity = Double.parseDouble(item.getValue(1).toString());
				break;
			}
		}
		
		return quantity.doubleValue();	
	}
	
	public native JavaScriptObject removeEntry(double quantity)/*-{
		var cmap = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMap::colorMap;		
		
		try{
			var childNodes = cmap.childNodes;
			var node;
			
			for(var child=0; child<childNodes.length; child++){
 				if(childNodes[child].attributes == null || childNodes[child].attributes == undefined) continue;
 				
 				if(childNodes[child].attributes.getNamedItem("quantity") != undefined || childNodes[child].attributes.getNamedItem("quantity") != null){
	 				var nodeValue = parseFloat(childNodes[child].attributes.getNamedItem("quantity").nodeValue);
	 				if(nodeValue == quantity){
	 					node = childNodes[child];
	 					break;
	 				}
	 			}
			}		
			
			cmap.removeChild(node);	
					
		}catch(e){
			return null;
		}
	
		return cmap;
	}-*/;	
	
	public void setColorMapJsO(JavaScriptObject colorMap){
		this.colorMap = colorMap;
	}
}
