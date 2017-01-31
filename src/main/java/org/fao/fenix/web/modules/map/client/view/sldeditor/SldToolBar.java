package org.fao.fenix.web.modules.map.client.view.sldeditor;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class SldToolBar {

	private ToolBar toolBar;
	
	private Styler styler;
	private ColorMap cMap;	
	
	private boolean ruleTitleFlag = false;
	private SymbolizersForm sf;
	
	
	
	public SldToolBar(Styler styler, ColorMap cMap){
		this.styler = styler;
		this.cMap = cMap;
		
		buildToolBar();
	}
	
	public SldToolBar(Styler styler, SymbolizersForm sf, boolean ruleTitleFlag){
		this.styler = styler;
		this.sf = sf;
		this.ruleTitleFlag = ruleTitleFlag;
		
		buildToolBar();
	}
	
	private void buildToolBar(){
		toolBar = new ToolBar();    
		
		Button toolBarItem = new Button("File");
//		toolBarItem.setHeight("50");
//		toolBarItem.setToolTip("File");
		toolBarItem.setIconStyle("sldToolbarItemIcon");  
		  
		Menu menu = new Menu(); 
		
		MenuItem load = new MenuItem("Load Style");
		SelectionListener<MenuEvent> loadListener = new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				new SLDLoad(styler);
			}  
	    };  
	    load.addSelectionListener(loadListener);
	    load.setIconStyle("load-icon");
		menu.add(load);	
		
		MenuItem save = new MenuItem("Save Style");
		SelectionListener<MenuEvent> saveListener = new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				if(styler.isVector){
					new SLDSave(styler, sf, ruleTitleFlag).saveVectorStyle();
				}else if(styler.isRaster){
					String[] matrix = cMap.mergeColorMap();
					cMap.mergeJSObject(matrix);
					
					new SLDSave(styler).saveRasterStyle();
				}					
			}  
	    };  
	    save.addSelectionListener(saveListener);
	    save.setIconStyle("save-icon");
		menu.add(save);
		
		toolBarItem.setMenu(menu);
		toolBar.add(toolBarItem);
		
	}
	
	public ToolBar getToolBar(){
		return this.toolBar;
	}
}
