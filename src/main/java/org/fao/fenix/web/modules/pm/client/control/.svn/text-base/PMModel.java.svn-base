package org.fao.fenix.web.modules.pm.client.control;

import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.CategoryList;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.pm.client.view.ProjectContainer;
import org.fao.fenix.web.modules.pm.client.view.ProjectManager;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;

public class PMModel {

	private static boolean projectManagerIsopen; // to avoid multiple instances of the Project Manager
	private static ProjectManager projectManager; // currently opened project manager
	private static String caller;
	 public static TreeItem selectedTreeItem;
	
	
	public String getCaller() {
		return caller;
	}

	public static void setCaller(String caller) {
		PMModel.caller = caller;
	}

	public static void setProjectManager(ProjectManager projectManager) {
		PMModel.projectManager = projectManager;
	}

	public static ProjectManager getProjectManager() {
		return projectManager;
	}

	/*public static boolean untitledProjectExists() {
		Tree tree = getProjectManager().getTree();
		TreeItem root = tree.getRootItem();
		ResourceChildModel model = (ResourceChildModel)root.getModel();
		String id = model.getId();
		
		if (id.equals("untitled"))
			return true;
		else return false;
	}*/

	public static void setProjectManagerIsOpen(boolean projectManagerIsopen) {
		PMModel.projectManagerIsopen = projectManagerIsopen;
	}

	public static boolean isProjectManagerOpen() {
		return projectManagerIsopen;
	}
	
	public static ResourceChildModel createResourceModel(FenixResourceVo resource){
		
//		GWT.log("[] - WRITE? " + resource.isHasWritePermission(), new Throwable());
//		GWT.log("[] - DELETE? " + resource.isHasDeletePermission(), new Throwable());
//		GWT.log("[] - TYPE? " + resource.getType(), new Throwable());
		
		ResourceChildModel model = new ResourceChildModel(resource.getTitle());
		model.set("dateModified", resource.getLastUpdated());
		model.set("region", resource.getRegion());
		/** MODIFIED **/
		CategoryList c = new CategoryList();
		c.getCategoryResourceChildModel(resource.getCategory(), model);
//		model.set("category", new CategoryList().getCategoryLabel(resource.getCategory()));
//		System.out.println("REGION HAS BEEN SET TO " + resource.getRegion());
		model.setId(resource.getId()); //set ID
     	model.setType(resource.getType()); //set Resource type
     	model.setWritePermission(resource.isHasWritePermission());
		model.setDeletePermission(resource.isHasDeletePermission());
		model.setDownloadPermission(resource.isHasDownloadPermission());
		model.setFlexDatasetType(resource.isDatasetFlex());
	
		return model;
	}
	
	
	 public static void addResourceToProjectManager(List<ResourceChildModel> resourceModelList, String resourceType) {
		 
	    	final ProjectManager pm;
				
			 if (!isProjectManagerOpen() || getProjectManager() == null) {
					pm = new ProjectManager();
					pm.build(resourceModelList, resourceType);
				} else {
					pm = getProjectManager();
					pm.addResources(resourceModelList);
				}
			 
		 }
	 
	 public static void addProjectToProjectManager(List<ResourceChildModel> projectModelList) {

		 final ProjectManager pm;

		 if (!isProjectManagerOpen() || getProjectManager() == null) {
			 pm = new ProjectManager();
			 pm.build(projectModelList, ResourceType.PROJECT);
		 } else {
			 pm = getProjectManager();

			 ProjectContainer pc = pm.getProjectContainer();

			 for (int j = 0; j < projectModelList.size(); j++) {
			     ResourceChildModel model = projectModelList.get(j);
				 ContentPanel projectPanel = (ContentPanel) pc.getItemByItemId(model.getId());
				 if(projectPanel==null){
					 pm.addProject(model);
				 }
				 else FenixAlert.info("Info", model.getName()+ " is already open in the Project Manager");
			 }


		 }
		 

	 }
	 
	 public static void addNewProjectToProjectManager(List<ResourceChildModel> projectModelList, ResourceChildModel model) {
    	 final ProjectManager pm;

		 if (!isProjectManagerOpen() || getProjectManager() == null) {
			 pm = new ProjectManager();
			 pm.build(projectModelList, ResourceType.PROJECT);
		 } else {
			 pm = getProjectManager();
			 pm.addProject(model);
		 }

	 }
	 
	 
	 public static void addNewResourceToProjectManager(List<ResourceChildModel> projectModelList, ResourceChildModel model, String resourceType) {
    	 final ProjectManager pm;

		 if (!isProjectManagerOpen() || getProjectManager() == null) {
			 pm = new ProjectManager();
			 pm.build(projectModelList, resourceType);
		 } else {
			 pm = getProjectManager();
			 pm.addResource(model);
		 }

	 }
	 
	
		
		public static TreeItem getSelectedTreeItem() {
			return selectedTreeItem;
		}

		public static void setSelectedTreeItem(TreeItem selectedItem) {
		PMModel.selectedTreeItem = selectedItem;
		}

}