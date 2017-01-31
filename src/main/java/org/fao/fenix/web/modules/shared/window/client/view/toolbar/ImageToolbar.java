package org.fao.fenix.web.modules.shared.window.client.view.toolbar;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class ImageToolbar {

	private ToolBar toolbar;
	
	public ImageToolbar() {
		toolbar = new ToolBar();
	}
	
	public ToolBar build() {
		
		IconButton createNewPagePortrait = new IconButton("latex");
		createNewPagePortrait.setToolTip("Add New Portrait Page");
//		createNewPagePortrait.addSelectionListener(DesignerController.createNewPagePortrait(w));
		toolbar.add(createNewPagePortrait);
		
		toolbar.add(new SeparatorToolItem());
		
		return toolbar;
	}
	
}