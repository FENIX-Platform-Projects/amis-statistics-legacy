package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.designer.client.control.DesignerController;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class DesignerToolbar {

	private ToolBar toolbar;
	
	public DesignerToolbar() {
		toolbar = new ToolBar();
	}
	
	public ToolBar build(DesignerWindow w) {
		
		IconButton createNewPagePortrait = new IconButton("portrait");
		createNewPagePortrait.setToolTip("Add New Portrait Page");
		createNewPagePortrait.addSelectionListener(DesignerController.createNewPagePortrait(w));
		toolbar.add(createNewPagePortrait);
		
		toolbar.add(new SeparatorToolItem());
		
		IconButton createNewPageLandscape = new IconButton("landscape");
		createNewPageLandscape.setToolTip("Add New Landscape Page");
		createNewPageLandscape.addSelectionListener(DesignerController.createNewPageLandscape(w));
		toolbar.add(createNewPageLandscape);
		
		toolbar.add(new SeparatorToolItem());
		
		Button createReport = new Button("Create Report");
		createReport.addSelectionListener(DesignerController.createReport(w));
		createReport.setIconStyle("wand");
		toolbar.add(createReport);
		
		toolbar.add(new SeparatorToolItem());
		
		IconButton save = new IconButton("save");
		save.setToolTip("Save Report");
		save.addSelectionListener(DesignerController.save(w));
		toolbar.add(save);
		
		toolbar.add(new SeparatorToolItem());
		
		IconButton saveAs = new IconButton("saveAs");
		saveAs.setToolTip("Save Report As...");
		saveAs.addSelectionListener(DesignerController.saveAs(w));
		toolbar.add(saveAs);
		
		toolbar.add(new SeparatorToolItem());
		
		return toolbar;
	}
	
}