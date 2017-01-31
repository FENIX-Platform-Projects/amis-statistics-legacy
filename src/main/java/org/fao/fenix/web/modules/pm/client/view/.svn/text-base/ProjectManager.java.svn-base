package org.fao.fenix.web.modules.pm.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.pm.client.control.ProjectManagerController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.google.gwt.user.client.Window;

public class ProjectManager extends FenixWindow {

	protected static HashMap<String, String> resourceTypeMap;
	protected static HashMap<String, String> iconMap;

	protected List<ResourceChildModel> resourceList;
	protected String resourceType;

	protected ProjectContainer projectContainer;

	public ProjectManager() {
		setProperties();

	}

	private void setProperties() {
		PMModel.setProjectManagerIsOpen(true);
		PMModel.setProjectManager(this);

		setTitle(BabelFish.print().projectManager());
		setSize(250, 300);
		setCollapsible(true);
		setMaximizable(true);
		setAutomaticScrollBar();
		getWindow().setPosition(Window.getClientWidth() - 350, (int) (Window.getClientHeight() / 5));
	
		// on close handler
		getWindow().addWindowListener(ProjectManagerController.onCloseManager(this));

	}
	
	public void addProject(ResourceChildModel resourceChild) {
		
		List<ResourceChildModel> projectModelList = new ArrayList<ResourceChildModel>();
		projectModelList.add(resourceChild);
		
		projectContainer.addProjects(projectModelList);

		// refresh
		//getWindow().setWidth(getWindow().getWidth());

	}

	
	public void addResource(ResourceChildModel resourceChild) {
		List<ResourceChildModel> resourceModelList = new ArrayList<ResourceChildModel>();
		resourceModelList.add(resourceChild);
		
		projectContainer.addResources(resourceModelList);

		// refresh
		getWindow().setWidth(getWindow().getWidth());

	}

	
	public void addResources(List<ResourceChildModel> list) {
		projectContainer.addResources(list);

		// refresh
		getWindow().setWidth(getWindow().getWidth());

	}

	public void addResourceToProject(ResourceChildModel resourceChild, String projectId) {
		projectContainer.addResourceToProject(resourceChild, projectId);
		
		// refresh
		getWindow().setWidth(getWindow().getWidth());

	}
	
	
	public void build() {
		projectContainer = new ProjectContainer(this);
		setResourceList(null);
		setResourceType(null);
		
		setCenterProperties();
		getCenter().setTopComponent(new ProjectManagerToolbar().getToolBar());
		getCenter().add(projectContainer);
		addCenterPartToWindow();

		getCenter().setHeaderVisible(false);

		show();
	}
	
	public void build(List<ResourceChildModel> list, String type) {
		setResourceList(list);
		setResourceType(type);

		projectContainer = new ProjectContainer(this);

		setCenterProperties();
		getCenter().setTopComponent(new ProjectManagerToolbar().getToolBar());
		getCenter().add(projectContainer);
		addCenterPartToWindow();

		getCenter().setHeaderVisible(false);

		show();
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String parentType) {
		this.resourceType = parentType;
	}

	public List<ResourceChildModel> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ResourceChildModel> resourceList) {
		this.resourceList = resourceList;
	}

	public ProjectContainer getProjectContainer() {
		return projectContainer;
	}

	public void setProjectContainer(ProjectContainer projectContainer) {
		this.projectContainer = projectContainer;
	}

}