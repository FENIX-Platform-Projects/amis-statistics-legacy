package org.fao.fenix.web.modules.birt.client.view.chart.viewer;

import org.fao.fenix.web.modules.birt.client.control.chart.ChartViewerController;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class ChartToolBar {

	ToolBar toolBar;
	
	public ToolBar getToolBar() {
		return toolBar;
	}

	
	public ChartToolBar(ChartViewer chartViewer){
		toolBar = new ToolBar();
		
		IconButton iconButton;
		
		if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()){
			iconButton = new IconButton("saveAs");
			iconButton.setTitle(BabelFish.print().saveAs());
			iconButton.addSelectionListener(ChartViewerController.saveAs(chartViewer));
			toolBar.add(iconButton);
			
			toolBar.add(new SeparatorToolItem());
						
			iconButton = new IconButton("mapSaveBtn");
			iconButton.setTitle(BabelFish.print().save());
			iconButton.addSelectionListener(ChartViewerController.save(chartViewer));
			toolBar.add(iconButton);
	
			toolBar.add(new SeparatorToolItem());
					
			iconButton = new IconButton("undo");
			iconButton.setTitle("get link");
			iconButton.addSelectionListener(ChartViewerController.viewLink(chartViewer));
			toolBar.add(iconButton);	
			toolBar.add(new SeparatorToolItem());
		}
		
		SplitButton splitItemIn = new SplitButton("Stretch");
		splitItemIn.setBorders(true);
		
		Menu menu = new Menu();  
		MenuItem strHor = new MenuItem("Horizontal");
		strHor.addSelectionListener(ChartViewerController.zoom(chartViewer,"in","hor"));
		menu.add(strHor);  
		MenuItem strVer = new MenuItem("Vertical");
		strVer.addSelectionListener(ChartViewerController.zoom(chartViewer,"in","ver"));
		menu.add(strVer);  
		MenuItem strBoth = new MenuItem("Horizontal and Vertical");
		strBoth.addSelectionListener(ChartViewerController.zoom(chartViewer,"in","both"));
		menu.add(strBoth);  
		splitItemIn.setMenu(menu);  
		   
		toolBar.add(splitItemIn);
		
		toolBar.add(new SeparatorToolItem());
		
		SplitButton splitItemOut = new SplitButton("Contract");
		splitItemOut.setBorders(true);
		
		menu = new Menu();  
		strHor = new MenuItem("Horizonatal");
		strHor.addSelectionListener(ChartViewerController.zoom(chartViewer,"out","hor"));
		menu.add(strHor);  
		strVer = new MenuItem("Vertical");
		strVer.addSelectionListener(ChartViewerController.zoom(chartViewer,"out","ver"));
		menu.add(strVer);  
		strBoth = new MenuItem("Horizonatal and Vertical");
		strBoth.addSelectionListener(ChartViewerController.zoom(chartViewer,"out","both"));
		menu.add(strBoth);  
		splitItemOut.setMenu(menu);  
		   
		toolBar.add(splitItemOut);
		
		toolBar.add(new SeparatorToolItem());
		iconButton = new IconButton("chartEdit");
		iconButton.setTitle(BabelFish.print().formatChart());
		iconButton.addSelectionListener(ChartViewerController.formatChart(chartViewer));
		toolBar.add(iconButton);
			
		toolBar.add(new SeparatorToolItem());
		iconButton = new IconButton("showHidePar");
		iconButton.setTitle("Show/Hide Parameters");
		iconButton.addSelectionListener(ChartViewerController.showHideParameters(chartViewer));
		toolBar.add(iconButton);
		
		toolBar.add(new SeparatorToolItem());
		iconButton = new IconButton("sendToPdf");
		iconButton.setTitle(BabelFish.print().exportPDF());
		iconButton.addSelectionListener(ChartViewerController.export(chartViewer, "pdf"));
		toolBar.add(iconButton);
	}
	
}
