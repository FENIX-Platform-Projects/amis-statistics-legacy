package org.fao.fenix.web.modules.map.client.view.sldeditor;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.map.client.colorpicker.ui.FenixColorPicker;
import org.fao.fenix.web.modules.map.client.control.vo.LayerItem;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TableEvent;
import com.extjs.gxt.ui.client.event.TableListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;

/*
 * error when trying to edit the style of a raster layer:
 *
colorMap is undefined
$createRowArray(Object name=colorMap)FA29CE15...ache.html (riga 25263)
$buildColorMapTable(org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMap@14 pane=<div></div> table=<div></div> $H=20 typeId$=0)FA29CE15...ache.html (riga 25237)
$ColorMap(org.fao.fenix.web.modules.map.client.view.sldeditor.ColorMap@14 pane=<div></div> table=<div></div> $H=20 typeId$=0, Object name=colorMap)FA29CE15...ache.html (riga 25217)
Styler:236 --- $showSymbolizers(org.fao.fenix.web.modules.map.client.view.sldeditor.Styler@15)FA29CE15...ache.html (riga 29430)
showSymbolizers_0()FA29CE15...ache.html (riga 29580)
$configRasterStyler(org.fao.fenix.web.modules.map.client.view.sldeditor.Styler@15)FA29CE15...ache.html (riga 29147)
configRasterStyler()FA29CE15...ache.html (riga 29552)
handlerGet(XMLHttpRequest _object=XMLHttpRequest _async=true readyState=4)FA29CE15...ache.html (riga 29393)
anonymous()
 */
 
/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class Styler {
	
	private String layerName;
	private String layerListName;
	private String styleName;
	public static Table table;
	private boolean ruleTitleFlag = false;
	
	private boolean isJoined;
	private Long geoviewid;
	private JavaScriptObject newStyleName = null;
	
	private String geoserverUrl;	
	private Window symbolizersWindow = null;
	private Button applyBtn;
	private LayerItem layerItem;
	private MapWindow mapWindow;
	
	private JavaScriptObject rasterSld;
	private JavaScriptObject colorMap;
	protected boolean isVector;
	protected boolean isRaster;
	
	private JavaScriptObject sld;	
	private JavaScriptObject[] rules;
	private JavaScriptObject[] symbolizers = null;		
	private SldClassifier classifier = null;
	private UniqueValueClassifier uvclassifier = null;
	private SymbolOperation symbolOperation = null;
	private FenixColorPicker colorPicker = null;
	private OperationManager opManager = null;
	
	private ColorMapForm colorMapForm = null;
	private boolean applyControl = false;
	
	
	public Styler (LayerItem layerItem, MapWindow mw){	
		if(layerItem.getClientGeoView().isStored()){
			this.mapWindow = mw;
			this.layerItem = layerItem;
			
			this.isJoined = layerItem.isJoined();
			this.isRaster = layerItem.isRaster();
			this.isVector = layerItem.isVect();
			
			this.geoviewid = layerItem.getUniqueId();
			this.layerName = layerItem.getLayerName();
			this.styleName = layerItem.getLayerStyleName();
			this.layerListName = layerItem.getLayerTitle();
			
			String url = (layerItem.getWMSUrl().indexOf("wms") > 0 ?
					layerItem.getWMSUrl().substring(0, layerItem.getWMSUrl().indexOf("wms")) :
					layerItem.getWMSUrl());
			this.geoserverUrl = url;
			
			setStylerContext(geoserverUrl, styleName, layerName);
		}else{
			FenixAlert.info("Alert", "The layer is not stored !");
		}		
	}
	
	private native void setStylerContext(String geoserverUrl, String styleName, String layerName)/*-{
//		var geoserverUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::geoserverUrl;
//		var styleName = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::styleName;
//		var layerName = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::layerName;
		
		var url = geoserverUrl + "rest/styles/" + styleName;
				
		function handlerGet(req) {						
			if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::isVector){	
				
				var format = new $wnd.OpenLayers.Format.SLD();		
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld = '';	
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld = format.read(req.responseXML || req.responseText);
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::configVectorStyler()();
			
			}else if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::isRaster){
				
				var format = new $wnd.OpenLayers.Format.XML();		
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::rasterSld = '';		
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::rasterSld = format.read(req.responseText);
				this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::configRasterStyler()();
			}				
		}

        $wnd.OpenLayers.Request.GET({
			url: url,
			callback: handlerGet,
			scope: this
		});

	}-*/;
	
	private native void configVectorStyler()/*-{

		var style = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers;

		var rules = [];
		var id;

        for(var name in style) id = style[name];

		rules[0] = id.userStyles[0].rules[0];
		for(var j=1; j<id.userStyles[0].rules.length; j++){
				if(rules[j-1]==null)break;
				rules[j] = id.userStyles[0].rules[j];	
		}
		
		this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::rules = rules;
		
		if(rules[0].symbolizer["Polygon"]){
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::enableClassification()();
		} else {
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::showSymbolizers()();
		}		
	}-*/;
	
	private native void configRasterStyler()/*-{
		var cmap = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::rasterSld.getElementsByTagName("ColorMap")[0];
		this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::colorMap = cmap;
		
		this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::showSymbolizers()();
	}-*/;
	
	private void enableClassification(){
		this.classifier = new SldClassifier(this);
		this.uvclassifier = new UniqueValueClassifier(this);
		this.symbolOperation = new SymbolOperation(this);
		this.classifier.getAttributes();	
	}
	
	public void refreshSymbolizer(){
		if (symbolizersWindow != null){
			symbolizersWindow.hide();
			symbolizersWindow = null;	

			setStylerContext(geoserverUrl, styleName, layerName);
		}
	}
	
	public LayerItem getLayerItem(){
		return this.layerItem;
	}
	
	public JavaScriptObject[] getRules(){
		return this.rules;
	}
	
	protected void showSymbolizers(){
		final SymbolizersForm sf = new SymbolizersForm();
		
		if(this.isVector){
			sf.initSymbolizersForm(rules);
		}
		
		symbolizersWindow = new Window();		
		symbolizersWindow.setTitle("Symbolizer - " + layerListName);
		symbolizersWindow.setHeading("Symbolizer - " + layerListName);
		symbolizersWindow.setCollapsible(false);
		symbolizersWindow.setWidth(385);
		symbolizersWindow.setHeight(615);
		symbolizersWindow.setPosition(800, 0);				
		symbolizersWindow.setPlain(true);
		
		symbolizersWindow.setLayout(new FlowLayout());

		SldToolBar sldToolBar;
		final Status statusMsg = new Status();
		final ToolBar stBar = new ToolBar();
		
		if(this.isRaster){
			symbolizersWindow.setWidth(385);
			symbolizersWindow.setHeight(550);
			symbolizersWindow.setTitle("ColorMap - " + layerListName);
			symbolizersWindow.setHeading("ColorMap - " + layerListName);
			
			final ColorMap tableColorMap = new ColorMap(colorMap);
			
			
			sldToolBar = new SldToolBar(this, tableColorMap);
			symbolizersWindow.add(sldToolBar.getToolBar());				



			statusMsg.setEnabled(true);
			symbolizersWindow.add(statusMsg);
			statusMsg.setBusy("Initializing symbolizer interface ...");
			
			
			symbolizersWindow.add(tableColorMap.getColorMapTable());
			colorMapForm = new ColorMapForm(table, this);
			
			ToolBar colorMapToolBar = new ToolBar();    
			
			Button addEntry = new Button("Add entry");
			addEntry.setHeight("50");
			addEntry.setToolTip("Add entry");
			addEntry.setIconStyle("addEntry"); 
			SelectionListener<ButtonEvent> addEntryListener = new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					String[] matrix = tableColorMap.mergeColorMap();
					colorMap = tableColorMap.mergeJSObject(matrix);					
					table.disable();
					colorMapForm.setColorMap(colorMap);
					applyBtn.disable();
					colorMapForm.getForm().show();					
				}  
		    };  
		    addEntry.addSelectionListener(addEntryListener);
		    colorMapToolBar.add(addEntry);
			
			Button removeEntry = new Button("Remove entry");
			removeEntry.setHeight("50");
			removeEntry.setToolTip("Remove entry");
			removeEntry.setIconStyle("removeEntry"); 	
			SelectionListener<ButtonEvent> removeEntryListener = new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					if(table.getSelectedItem() != null){
	
						final Listener<MessageBoxEvent> messageListener = new Listener<MessageBoxEvent>() {
							public void handleEvent(MessageBoxEvent e) {
//								Dialog dialog = (Dialog) ce.component;
						        Button btn = e.getButtonClicked();
						        
						        if(btn.getText().indexOf("Yes") != -1){
									double quantity = tableColorMap.calculateTableItemIndex(table.getSelectedItem());
									String[] matrix = tableColorMap.mergeColorMap();
									colorMap = tableColorMap.mergeJSObject(matrix);	
									tableColorMap.setColorMapJsO(colorMap);
									colorMap = tableColorMap.removeEntry(quantity);
									if(colorMap != null){
										updateColorMap(colorMap);
										Info.display("Remove Node", "The child entry as been removed !");
									}else{
										FenixAlert.info("Error", "Error when removing child entry !");
									}	
						        }
						    }  
						};  
							
						MessageBox box = new MessageBox();  
			            box.setButtons(MessageBox.YESNO);  
			            box.setIcon(MessageBox.QUESTION);  
			            box.setTitle("Remove entry?");  
			            box.addCallback(messageListener);  
			            box.setMessage("Are you sure to remove the selected entry?");  
			            box.show();				
					}else{
						FenixAlert.info("Info", "To remove the row you ave to select the entry in the table !");					
					}					
				}  
		    };  
		    removeEntry.addSelectionListener(removeEntryListener);
		    colorMapToolBar.add(removeEntry);		    
		    
		    
			symbolizersWindow.add(colorMapToolBar);				
			
			symbolizersWindow.add(colorMapForm.buildForm());	
			
			symbolizersWindow.addWindowListener(new WindowListener(){			
				@Override
				public void windowActivate(WindowEvent we) {
					super.windowActivate(we);				
				}

				@Override
				public void windowHide(WindowEvent we) {
					super.windowHide(we);
					Info.display("Close Event", "The symbolizer window as been closed !");
				}			
			});	
			
			SelectionListener<ButtonEvent> appListener = new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					String[] matrix = tableColorMap.mergeColorMap();
					colorMap = tableColorMap.mergeJSObject(matrix);
					updateColorMap(colorMap);
				}  
	        };  

			applyBtn = new Button("Apply", appListener);
			symbolizersWindow.addButton(applyBtn);
			
			symbolizersWindow.show();	
			setColorInTable();			
			
		} else if(this.isVector) {
			
			sldToolBar = new SldToolBar(this, sf, ruleTitleFlag);
			symbolizersWindow.add(sldToolBar.getToolBar());				
			
			statusMsg.setEnabled(true);
			symbolizersWindow.add(statusMsg);
			statusMsg.setBusy("Initializing symbolizer interface ...");
			
			if(classifier != null && uvclassifier != null && symbolOperation != null){
				opManager = new OperationManager(this);
				symbolizersWindow.add(opManager.buildForm());	

				classifier.createClassifyFormPanel();
				symbolizersWindow.add(classifier.getClassifyForm());
				
				uvclassifier.setlayerAttributes(classifier.getlayerAttributes());
				uvclassifier.createUniqueValueFormPanel();
				symbolizersWindow.add(uvclassifier.getUniqueValueForm());		
				
				symbolOperation.createSymbolFormPanel();
				symbolizersWindow.add(symbolOperation.getSymbolForm());	
			}	
			
			List<TableColumn> columns = new ArrayList<TableColumn>();		
			columns.add(new TableColumn("color", 55));
			columns.add(new TableColumn("value", 140));
			columns.add(new TableColumn("label", 140));

			TableColumnModel cm = new TableColumnModel(columns);
			
			VerticalPanel pane = new VerticalPanel();		
			pane.setWidth(370);
			pane.setHeight(150);
			pane.setScrollMode(Scroll.AUTO);
			
			TableLayout lay = new TableLayout();
			pane.setLayout(lay);		
			final Table table = new Table(cm);
			table.setWidth(372);	
		
			JavaScriptObject[] rowArrayJS = createRowArray(rules);
			
			for (int j=0; j<rowArrayJS.length; j++){
				TableItem item = createTableItem(rowArrayJS[j]);
			    item.disableTextSelection(false);		    
			    table.add(item);	
			}
		    
		    table.addTableListener(new TableListener(){

				@Override
				public void tableRowClick(TableEvent te) {
					super.tableRowClick(te);
					sf.show(te.getRowIndex());
//					Table t = (Table)te.getSource();
//					VerticalPanel p = (VerticalPanel)symbolizersWindow.getItemByItemId("SymbolizerFormPanel");
//					for(int i=0; i<t.getItemCount(); i++){
//						if(p.getItem(i).isVisible())
//							p.getItem(i).hide();
//					}
//					p.getItem(te.getRowIndex()).show();
				}

				@Override
				public void tableCellDoubleClick(final TableEvent te) {
					super.tableCellDoubleClick(te);
					
					if(te.getCellIndex() != 1){
						new LegendEditor(Styler.this, sf, te, table);
					}				
				}	    	
		    });
		    
		    Styler.table = table;
		    pane.add(table);
		    symbolizersWindow.add(pane);
		    
			symbolizersWindow.add(sf.getSymbolizerForm());		
			
			symbolizersWindow.addWindowListener(new WindowListener(){			
				@Override
				public void windowActivate(WindowEvent we) {
					super.windowActivate(we);
				
				}

				@Override
				public void windowHide(WindowEvent we) {
					super.windowHide(we);	
					if(classifier != null){
						classifier = null;
						Info.display("Close Event", "Symbolizer window has been closed");
					}
				}			
			});	
			
			SelectionListener<ButtonEvent> appListener = new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					symbolizers = sf.updateSymbolizerForm();
					
					if(!applyControl){
						applyControl = sf.isSymbolizersModifyed();
					}			
					
					if(applyControl || ruleTitleFlag){											
						statusMsg.setBusy("Sending SLD data...");
						
						onUpdateStyle(symbolizers);						
						sf.resetSymbModifyed();
						applyControl = false;
						ruleTitleFlag = false;
						
						statusMsg.clearStatus("Ready");
						Info.display("Upload Style", "SLD succesfully uploaded!");
					} 
				}  
	        };  

			applyBtn = new Button("Apply", appListener);
			symbolizersWindow.addButton(applyBtn);
			
			symbolizersWindow.show();	
			setFormsStyle(sldToolBar.getToolBar(), pane);
			setColorInTable();
			
			sf.show(0);
		}		
				
		statusMsg.clearStatus("Ready");
		Info.display("Initializing", "SLD editor succesfully initialized!");
	}
	
	private void setFormsStyle(ToolBar toolBar, VerticalPanel pane){
		LayoutContainer symbolizerFormPanel = (LayoutContainer)symbolizersWindow.getItemByItemId("SymbolizerFormPanel");
	    
		int left = 8; int top;
	    if(opManager != null){
	    	FormPanel opManagerForm = (FormPanel)symbolizersWindow.getItemByItemId("opManagerForm");
	    	top = toolBar.getBounds(true).height + opManagerForm.getBounds(true).height + 
	    		  	pane.getBounds(true).height;
	    }else{
	    	top = toolBar.getBounds(true).height + pane.getBounds(true).height;
	    }

		for(int i=0; i<symbolizerFormPanel.getItemCount(); i++){
			DOM.setStyleAttribute(symbolizerFormPanel.getItem(i).getElement(), "position", "absolute");
	        DOM.setStyleAttribute(symbolizerFormPanel.getItem(i).getElement(), "left", left + "px");
	        DOM.setStyleAttribute(symbolizerFormPanel.getItem(i).getElement(), "top", top + 45 + "px");
	        
	        if(i != 0)
	        	DOM.setStyleAttribute(symbolizerFormPanel.getItem(i).getElement(), "display", "none");
	    }		
	}
	
	private void setColorInTable(){		
		for(int k=0; k<Styler.table.getItemCount(); k++){
			TableItem tabItem = Styler.table.getItem(k);
			String itemBackround = tabItem.getValue(0).toString();
			if(itemBackround.indexOf("#") != -1){
				Element cell = LegendEditor.getTextCellElement(tabItem, 0);
				DOM.setStyleAttribute(cell, "background", itemBackround);
				DOM.setStyleAttribute(cell, "color", itemBackround);
			}else if(itemBackround.indexOf("undefined") != -1){
				tabItem.setValue(0, "undefined");
			}else{
				tabItem.setValue(0, " ");
				Element cell = LegendEditor.getTextCellElement(tabItem, 0);
				Image image = new Image();
				image.setUrl(itemBackround);
				image.setHeight("25");
				image.setWidth("21");
				DOM.appendChild(cell, image.getElement());				
			}
		}
	}
	
	public native void updateColorMap(JavaScriptObject colorMap)/*-{
	 	
	 	var sld_raster = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::rasterSld; 
 		
 		var format = new $wnd.OpenLayers.Format.XML();
 		var data = format.write(sld_raster); 		
		
		var geoserverUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::geoserverUrl;		
		var styleName = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::styleName;		
		
		var url;	
		var id = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::geoviewid;
		if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::newStyleName != null){
			url = geoserverUrl + "rest/styles/" + styleName;
		}else{				
			if (styleName.indexOf(id) != -1){
				url = geoserverUrl + "rest/styles/" + styleName;
			}else{
			    url = geoserverUrl + "rest/styles/" + styleName + id;
			    this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::newStyleName = styleName + id;
			    this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::setNewStyleName()();
			}
		}	 	
		
		$wnd.OpenLayers.Request.PUT({
			url: url,
			user: 'admin',
			password:'geoserver',
			data: data,
			callback: handlerPut,
			scope: this														  
		});
		
		function handlerPut(req){
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::refreshLayer()();
		}						
	}-*/;
	
	private native void onUpdateStyle(JavaScriptObject[] symbolizer)/*-{			
		var style = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers;

		var id;
        for(var name in style) id = name; 
		
		for (var i=0; i<symbolizer.length; i++){
			if(symbolizer[i] != null){
				if (this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Polygon"]){
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Polygon"] = symbolizer[i];
				}else if (this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Point"]){
						this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Point"] = symbolizer[i];
				}else if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Line"]){
					this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers[id].userStyles[0].rules[i].symbolizer["Line"] = symbolizer[i];
				}	
			}
		}	
		
		var format = new $wnd.OpenLayers.Format.SLD();
		var data = format.write(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld);	
		
		var geoserverUrl = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::geoserverUrl;		
		var styleName = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::styleName;		
		
		var url;
		if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::isJoined){
			url = geoserverUrl + "rest/styles/" + styleName;
		}else{		
					
			var id = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::geoviewid;
			if(this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::newStyleName != null){
				url = geoserverUrl + "rest/styles/" + styleName;
			}else{				
				if (styleName.indexOf(id) != -1){
					url = geoserverUrl + "rest/styles/" + styleName;
				}else{
				    url = geoserverUrl + "rest/styles/" + styleName + id;
				    this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::newStyleName = styleName + id;
				    this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::setNewStyleName()();
				}
			}	    
		}
		
		$wnd.OpenLayers.Request.PUT({
			url: url,
			user: 'admin',
			password:'geoserver',
			data: data,
			callback: handlerPut,
			scope: this														  
		});
		
		function handlerPut(req){
			this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::refreshLayer()();
		}						
	}-*/;
	
	public native void modifyRuleTitle(String newName, int index)/*-{	
	    var style = this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers;

		var id;
        for(var name in style) id = name;

		this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::sld.namedLayers[id].userStyles[0].rules[index].title = newName;
		this.@org.fao.fenix.web.modules.map.client.view.sldeditor.Styler::ruleTitleFlag = true;
	}-*/;
	
	public void setNewStyleName(){
		this.layerItem.setLayerStyleName(this.newStyleName.toString());
		this.styleName = this.newStyleName.toString();
	}
	
	public void setNewStyleNameByClassification(JavaScriptObject newStyleName){
		this.newStyleName = newStyleName;
		this.layerItem.setLayerStyleName(this.newStyleName.toString());
		this.styleName = this.newStyleName.toString();
	}
	
	public void refreshLayer(){			
		
		if (styleName.indexOf(""+this.layerItem.getUniqueId()) != -1){
			mergeNewStyle(""+this.layerItem.getUniqueId(), styleName);
			Info.display("Refresh Layer", "Layer Refreshed " + this.styleName);
		}else{
			this.mapWindow.redrawLayer(this.layerItem.getUniqueId());
			Info.display("Refresh Layer", "Layer Refreshed " + this.styleName);
		}	

		refreshSymbolizer();
	}
	
	private native JavaScriptObject[] createRowArray(JavaScriptObject[] rules)/*-{
		var geometryType;	
		var row = [];
		
		for (var i=0; i<rules.length; i++){
			if (rules[i].symbolizer["Line"]){
				geometryType = rules[i].symbolizer["Line"];
				if(rules[i].title == null){
					rules[i].title = rules[i].name;
				}
				row[i] = geometryType.strokeColor + "%" + rules[i].name + "%" + rules[i].title;
			}else if(rules[i].symbolizer["Point"]){
				geometryType = rules[i].symbolizer["Point"];
				if(rules[i].title == null){
					rules[i].title = rules[i].name;
				}
				if(geometryType.externalGraphic != null){
					row[i] = geometryType.externalGraphic + "%" + rules[i].name + "%" + rules[i].title;
				}else{
					if(geometryType.fillColor != null || geometryType.fillColor != undefined){
						row[i] = geometryType.fillColor + "%" + rules[i].name + "%" + rules[i].title;
					}else{
						row[i] = "undefined" + "%" + rules[i].name + "%" + rules[i].title;
					}					
				}				
			}else if(rules[i].symbolizer["Polygon"]){
				geometryType = rules[i].symbolizer["Polygon"];
				if(rules[i].title == null){
					rules[i].title = rules[i].name;
				}
				if(geometryType.fillColor != null || geometryType.fillColor != undefined){
					row[i] = geometryType.fillColor + "%" + rules[i].name + "%" + rules[i].title;
				}else{
					row[i] = "undefined" + "%" + rules[i].name + "%" + rules[i].title;
				}	
			}			
		}	
		
		return row;
	}-*/;
	
	private TableItem createTableItem(JavaScriptObject rowArrayJS){
		String[] row = rowArrayJS.toString().split("%");
		
	    Object[] obj = new Object[3];
	    obj[0] = row[0];
	    obj[1] = row[1];
	    obj[2] = row[2];
	    
		TableItem item = new TableItem(obj);
		return item;
	}
	
	private native void mergeNewStyle(String layerId, String styleName)/*-{
		var mapObj = $doc.getElementById($wnd.mapId).map;
		var layer = mapObj.getLayer(layerId);		
		layer.mergeNewParams({'styles': styleName});
		layer.redraw(true);
	}-*/;
	
	protected SldClassifier getClassifier(){
		return this.classifier;
	}
	
	protected UniqueValueClassifier getUniqueValueClassifier(){
		return this.uvclassifier;
	}
	
	protected SymbolOperation getSymbolOperation(){
		return this.symbolOperation;
	}
	
	protected void setSLD(JavaScriptObject jso){
		this.sld = jso;
		
		if (symbolizersWindow != null){
			symbolizersWindow.hide();
			symbolizersWindow = null;	
			
			applyControl = true;
			configVectorStyler();
		}		
	}
	
	protected void setRasterSLD(JavaScriptObject jso){
		this.rasterSld = jso;
		
		if (symbolizersWindow != null){
			symbolizersWindow.hide();
			symbolizersWindow = null;	
			
			configRasterStyler();
		}		
	}
	
	protected JavaScriptObject getSLD(){
		return this.sld;
	}
	
	protected JavaScriptObject getRasterSLD(){
		return this.rasterSld;
	}

	public Button getApplyButton() {
		return applyBtn;
	}

	protected Window getSymbolizersWindow(){
		return this.symbolizersWindow;
	}
	
	public void showSymbolizerFormPanel(){
		LayoutContainer symbolizerFormPanel = (LayoutContainer)symbolizersWindow.getItemByItemId("SymbolizerFormPanel");
		
		for(int i=0; i<symbolizerFormPanel.getItemCount(); i++){
			DOM.setStyleAttribute(symbolizerFormPanel.getItem(i).getElement(), "display", "block");
		}
	}
	
	public void hideSymbolizerFormPanel(){
		LayoutContainer symbolizerFormPanel = (LayoutContainer)symbolizersWindow.getItemByItemId("SymbolizerFormPanel");
		
		for(int i=0; i<symbolizerFormPanel.getItemCount(); i++){
			DOM.setStyleAttribute(symbolizerFormPanel.getItem(i).getElement(), "display", "none");
		}
	}
}

