package org.fao.fenix.web.modules.re.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class CatalogueToolbar {
	
	ToolBar toolBar;
	ResourceExplorer resourceExplorer;
		
	public ToolBar getToolBar() {
		return toolBar;
	}

	public CatalogueToolbar(ResourceExplorer resourceExplorer) {
		this.resourceExplorer = resourceExplorer;
		toolBar = new ToolBar();
		
		IconButton iconButton;
		
		iconButton = new IconButton("reProjectUpButton");
		iconButton.setTitle(BabelFish.print().up());
		toolBar.add(iconButton);
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);
		
		toolBar.add(new SeparatorToolItem());
		
		iconButton = new IconButton("reAddToMyProjectsButton");
		iconButton.setTitle(BabelFish.print().addToMyProjects());
		toolBar.add(iconButton);
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);
		
		toolBar.add(new SeparatorToolItem());
		
		iconButton = new IconButton("reMoveToProjectButton");
		iconButton.setTitle(BabelFish.print().moveToProject());
		toolBar.add(iconButton);
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);
		
		toolBar.add(new SeparatorToolItem());
		
		iconButton = new IconButton("reCopyToProjectButton");
		iconButton.setTitle(BabelFish.print().copyToProject());
		toolBar.add(iconButton);
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);
		
		toolBar.add(new SeparatorToolItem());
		
		iconButton = new IconButton("reDuplicateProjectButton");
		iconButton.setTitle(BabelFish.print().duplicateProject());
		toolBar.add(iconButton);
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);
		
		toolBar.add(new SeparatorToolItem());
		
		iconButton = new IconButton("delete");
		iconButton.setTitle(BabelFish.print().delete());
		toolBar.add(iconButton);
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);
		
		toolBar.add(new SeparatorToolItem());
		
		iconButton = new IconButton("reMetadataIcon");
		iconButton.setTitle(BabelFish.print().viewMetadata());
		toolBar.add(iconButton);
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);
		
	}
	
}
