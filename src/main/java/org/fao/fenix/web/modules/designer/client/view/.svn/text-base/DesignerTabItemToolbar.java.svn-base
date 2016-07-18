package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.designer.client.control.DesignerController;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class DesignerTabItemToolbar {

	private ToolBar toolbar;
	
	public DesignerTabItemToolbar() {
		toolbar = new ToolBar();
		toolbar.setWidth("455px");
	}
	
	public ToolBar build(DesignerTabItem dti) {
		
		IconButton addBox = new IconButton("addIcon");
		addBox.setToolTip("Add New Box");
		addBox.addSelectionListener(DesignerController.addBox(dti));
		toolbar.add(addBox);
		
		toolbar.add(new SeparatorToolItem());
		
		return toolbar;
	}
	
}