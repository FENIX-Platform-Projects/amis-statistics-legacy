package org.fao.fenix.web.modules.pm.client.view;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.ProjectManagerContextMenuController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.core.client.GWT;

public class ProjectManagerContextMenu {

	protected Menu contextMenu;

	protected Tree selectedTree;
	protected TreeItem selectedItem;
	protected ProjectContainer projectContainer;
	protected ResourceChildModel model;

	protected ProjectManager projectManager;

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public ProjectContainer getProjectContainer() {
		return projectContainer;
	}

	public void setProjectContainer(ProjectContainer projectContainer) {
		this.projectContainer = projectContainer;
	}

	public ProjectManagerContextMenu(ProjectContainer container) {
		contextMenu = new Menu();
		setProjectContainer(container);

	}

	public Menu build(final TreeItem item) {

		if (item != null) {
			selectedItem = item;
			//PMModel.setSelectedTreeItem(selectedItem);
			model = (ResourceChildModel)item.getModel();
			contextMenu = build();
		}

		return contextMenu;
	}

	public Menu build() {

		contextMenu = new Menu();

		// Save As
		MenuItem saveAs = new MenuItem(BabelFish.print().saveAs());
		saveAs.addSelectionListener(ProjectManagerContextMenuController.saveAs(selectedItem));
		
		// Open
		MenuItem open = new MenuItem(BabelFish.print().open());
		open.addSelectionListener(ProjectManagerContextMenuController.openResource(selectedItem));

        //Dataset Open options
		// View/Edit Dataset
		MenuItem view = new MenuItem(BabelFish.print().viewEdit());
		view.addSelectionListener(ProjectManagerContextMenuController.view(selectedItem));
		
		// Chart Analysis
		MenuItem chartAnalysis = new MenuItem(BabelFish.print().chartAnalysis());
		chartAnalysis.addSelectionListener(ProjectManagerContextMenuController.chartAnalysis(selectedItem));
		
		// Table Analysis - currently disabled
		MenuItem tableAnalysis = new MenuItem(BabelFish.print().tableAnalysis());
		tableAnalysis.addSelectionListener(ProjectManagerContextMenuController.tableAnalysis(selectedItem));
	    tableAnalysis.setEnabled(false);
		
		// Delete
		MenuItem delete = new MenuItem(BabelFish.print().delete());
		//TODO
		//delete.addSelectionListener(ProjectManagerContextMenuController.delete
		// (tree));
		// contextMenu.add(delete);

		// Rename
		MenuItem rename = new MenuItem(BabelFish.print().rename());
		//TODO
		//rename.addSelectionListener(ProjectManagerContextMenuController.rename
		// (tree));
		// contextMenu.add(rename);
        // contextMenu.add(new SeparatorMenuItem());

		// View Metadata
		MenuItem metadata = new MenuItem(BabelFish.print().viewMetadata());
		metadata.addSelectionListener(ProjectManagerContextMenuController.viewMetadata(selectedItem));

		// Add Resource
		MenuItem addResources = new MenuItem(BabelFish.print().addResources());
		addResources.addSelectionListener(ProjectManagerContextMenuController.addResourceToManager(selectedItem));
	
		//Remove project from project manager
		MenuItem removeProjectFromManager = new MenuItem(BabelFish.print().removeProjectFromManager());
		removeProjectFromManager.addSelectionListener(ProjectManagerContextMenuController.removeProjectFromManager(selectedItem, getProjectContainer()));
		
		//Show hidden resources for project
		MenuItem showProjectsHiddenResources = new MenuItem(BabelFish.print().showAllHiddenResources());
		showProjectsHiddenResources.addSelectionListener(ProjectManagerContextMenuController.showHiddenResource(selectedItem, getProjectContainer()));
		
		//Hide resource
		MenuItem hideResource = new MenuItem(BabelFish.print().hideResource());
		hideResource.addSelectionListener(ProjectManagerContextMenuController.hideResource(selectedItem, getProjectContainer()));
			
		// Delete resource from project
		MenuItem deleteFromProject = new MenuItem(BabelFish.print().deleteResourceFromProject());
		deleteFromProject.addSelectionListener(ProjectManagerContextMenuController.deleteResourceFromProject(selectedItem, getProjectContainer()));
		
		// Remove resource from project (for untitled)
		MenuItem removeFromProject = new MenuItem(BabelFish.print().removeResourceFromProject());
		removeFromProject.addSelectionListener(ProjectManagerContextMenuController.deleteResourceFromProject(selectedItem, getProjectContainer()));
		
		//change role!!!
		if (!selectedItem.getId().equals("untitled")) {
			if(model!=null){
			GWT.log("ProjectManagerController@134 | model type: " + model.getType(), null);
				if(!model.getType().equals(ResourceType.PROJECT)){
					if(model.getType().equals(ResourceType.DATASET)){
						GWT.log("ProjectManagerController@134 | ResourceType.DATASET: " + ResourceType.DATASET, null);
						contextMenu.add(view);
						contextMenu.add(new SeparatorMenuItem());
						contextMenu.add(chartAnalysis);	
						contextMenu.add(tableAnalysis);	
						contextMenu.add(new SeparatorMenuItem());
					}
					else { //For other resources
						contextMenu.add(open);	
						contextMenu.add(new SeparatorMenuItem());
					}

					if(selectedItem.getParentItem().getId().equals("untitled"))
						contextMenu.add(removeFromProject);
					else {
						ResourceChildModel parentProjectModel = (ResourceChildModel)selectedItem.getParentItem().getModel();
						GWT.log("ProjectManagerController@134 | parentProjectModel.hasWritePermission(): " + parentProjectModel.hasWritePermission(), null);
						
						if(parentProjectModel.hasWritePermission() && parentProjectModel.hasWritePermission()!=null) {
							contextMenu.add(deleteFromProject);
						}
					}
					contextMenu.add(hideResource);
					contextMenu.add(new SeparatorMenuItem());

				} else { //Projects
					GWT.log("ProjectManagerController@134 | model.hasWritePermission(): " + model.hasWritePermission(), null);
					if(model.hasWritePermission() && model.hasWritePermission()!=null) {
					  contextMenu.add(addResources);
					  contextMenu.add(new SeparatorMenuItem());							
					}
					contextMenu.add(showProjectsHiddenResources);
					contextMenu.add(new SeparatorMenuItem());	
					contextMenu.add(removeProjectFromManager);				
					contextMenu.add(new SeparatorMenuItem());
				}
			}
			contextMenu.add(metadata);

		} else {
			contextMenu.add(addResources);// Add resources available to Untitled project
			if(FenixUser.hasUserRole())
				contextMenu.add(saveAs);
		}
		
				
	/*	if (FenixUser.hasUserRole()) {
			if(model!=null){
				if(model.getType().equals(ResourceType.PROJECT)){
					  contextMenu.add(addResources);
					  contextMenu.add(new SeparatorMenuItem());
				} else {
					if(!selectedItem.getParentItem().getId().equals("untitled")) {
						contextMenu.add(deleteFromProject);
						contextMenu.add(new SeparatorMenuItem());
					}
						
				}
				
			} else {
				if(selectedItem.getId().equals("untitled")){
					contextMenu.add(saveAs);
				}
			}		
		} */
		
		
		
		return contextMenu;

	}

}
