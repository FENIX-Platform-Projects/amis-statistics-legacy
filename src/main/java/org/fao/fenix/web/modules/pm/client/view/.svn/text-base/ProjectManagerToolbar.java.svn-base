package org.fao.fenix.web.modules.pm.client.view;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.ProjectManagerToolbarController;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class ProjectManagerToolbar {

	ToolBar toolBar;
	
	public ToolBar getToolBar() {
		return toolBar;
	}

	public ProjectManagerToolbar() {
		toolBar = new ToolBar();

		IconButton iconButton;

		//CREATE NEW PROJECT
		iconButton = new IconButton("pmNewProject");
		iconButton.setTitle(BabelFish.print().createNewProject());
		iconButton.addSelectionListener(ProjectManagerToolbarController.createNewProject());
		if(FenixUser.hasUserRole()){
			toolBar.add(iconButton);
		}
		
		// LOAD PROJECT
		iconButton = new IconButton("pmLoadProject");
		iconButton.setTitle(BabelFish.print().loadProject());
		iconButton.addSelectionListener(ProjectManagerToolbarController.openProjectResourceExplorer());
		toolBar.add(iconButton);
		
		
		
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);

		/**toolBar.add(new SeparatorToolItem());

		iconButton = new IconButton("reAddToMyProjectsButton");
		iconButton.setTitle(I18N.print().addToMyProjects());
		toolBar.add(new AdapterToolItem(iconButton));
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);

		toolBar.add(new SeparatorToolItem());

		iconButton = new IconButton("reMoveToProjectButton");
		iconButton.setTitle(I18N.print().moveToProject());
		toolBar.add(new AdapterToolItem(iconButton));
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);

		toolBar.add(new SeparatorToolItem());

		iconButton = new IconButton("reCopyToProjectButton");
		iconButton.setTitle(I18N.print().copyToProject());
		toolBar.add(new AdapterToolItem(iconButton));
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);

		toolBar.add(new SeparatorToolItem());

		iconButton = new IconButton("reDuplicateProjectButton");
		iconButton.setTitle(I18N.print().duplicateProject());
		toolBar.add(new AdapterToolItem(iconButton));
		//catalogueToolBar.setData(FenixToolbarConstants.SAVE, iconButton);**/


	}

}
