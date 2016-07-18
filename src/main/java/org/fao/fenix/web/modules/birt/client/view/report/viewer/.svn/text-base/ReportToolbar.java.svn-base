package org.fao.fenix.web.modules.birt.client.view.report.viewer;

import org.fao.fenix.web.modules.birt.client.control.report.ReportViewerController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;


public class ReportToolbar{
	
	ToolBar toolBar;
	
	public ToolBar getToolBar() {
		return toolBar;
	}
	
	public ReportToolbar(ReportViewer reportViewer){
		
		toolBar = new ToolBar();
		
		IconButton iconButton;		
		
		iconButton = new IconButton("saveAs");
		iconButton.setTitle(BabelFish.print().saveAs());
		iconButton.addSelectionListener(ReportViewerController.saveAs(reportViewer));
		toolBar.add(iconButton);
		
		toolBar.add(new SeparatorToolItem());
		
		
		iconButton = new IconButton("mapSaveBtn");
		iconButton.setTitle(BabelFish.print().save());
		iconButton.addSelectionListener(ReportViewerController.save(reportViewer));
		toolBar.add(iconButton);
		
	}

}
