package org.fao.fenix.web.modules.pm.client.view;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.pm.client.control.ProjectManagerController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.Element;

public class ProjectContainer extends ContentPanel {

	private ProjectManager projManager;
	public static TreeItem selectedTreeItem;

	public static TreeItem getSelectedTreeItem() {
		return selectedTreeItem;
	}

	public static void setSelectedTreeItem(TreeItem selectedItem) {
		ProjectContainer.selectedTreeItem = selectedItem;
	}

	private static HashMap<String, String> iconMap;

	public ProjectManager getProjManager() {
		return projManager;
	}

	public void setProjManager(ProjectManager projManager) {
		this.projManager = projManager;
	}

	public ProjectContainer(ProjectManager projectManager) {
		setBodyBorder(false);
		setHeaderVisible(false);
		setProjManager(projectManager);
		iconMap = new HashMap<String, String>();
	}

	public ProjectContainer() {
		setBodyBorder(false);
		setHeaderVisible(false);
		iconMap = new HashMap<String, String>();
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);

		setLayout(new AccordionLayout());
		setIconStyle("icon-accordion");
		setSize(250, 300);

		final List<ResourceChildModel> resourceList = getProjManager().getResourceList();
		final String type = getProjManager().getResourceType();

		System.out.println("--------------- resource type = " + type);

		if (resourceList != null) {
			// if(type!=null && resourceList!=null) {
			if (type.equals(ResourceType.PROJECT)) {
				for (int i = 0; i < resourceList.size(); i++) {
                	
                	final ResourceChildModel parentProject = resourceList.get(i);
					add(newPanel(parentProject.getName(), parentProject.getId()));

					ProjectManagerController.buildProjectTree(parentProject, this);
				}
			} else {
				addResources(resourceList);
			}
		}
	}

	public void addProjects(final List<ResourceChildModel> resources) {
		
		for (int i = 0; i < resources.size(); i++) {

			final ResourceChildModel parentProject = resources.get(i);
			ContentPanel projectContainer = new ContentPanel();
			System.out.println("---------- content panel = " + parentProject.getName());
			projectContainer.setHeading(parentProject.getName());
			projectContainer.setScrollMode(Scroll.AUTO);
			projectContainer.setId(parentProject.getId());

			projectContainer.setExpanded(true);
			add(projectContainer);

			ProjectManagerController.buildProjectTree(parentProject, this);

		}

	}

	public void addResources(final List<ResourceChildModel> resources) {

		ContentPanel untitledContainer;
		Tree untitledTree;
		TreeItem parentItem;

		if (untitledProjectExists()) {
			untitledContainer = (ContentPanel) getItemByItemId("untitled");
			untitledContainer.setExpanded(true);
		
			untitledTree = (Tree) untitledContainer.getWidget(0);
			untitledTree.expandAll();
			
			parentItem = untitledTree.getItem(0);

			System.out.println("parentItem text = " + parentItem.getText());
			
						
		} else {
			untitledContainer = newPanel(BabelFish.print().untitledProject(), "untitled");
			untitledContainer.setExpanded(true);
			add(untitledContainer);
			
			untitledTree = new Tree();
			addTreeListener(untitledTree);
			parentItem = new TreeItem(BabelFish.print().untitledProject());
			parentItem.setId("untitled");
			untitledTree.getRootItem().add(parentItem);

			untitledTree.expandAll();
			untitledContainer.add(untitledTree);		

		}

		for (int i = 0; i < resources.size(); i++) {
			ResourceChildModel rsrc = resources.get(i);			
			TreeItem item = newItem(rsrc);
			
			//test if the item is in the tree
			if(!isResourceInTree(rsrc.getId(), untitledTree))
				parentItem.add(item);

			// if(rsrc.getType().equals(ResourceType.PATTERN)){
			// System.out.println("PATTERN = " + rsrc.getType());
			// ProjectManagerController.buildPatternTree(item, parentItem, this);
			// } else parentItem.add(item);
			//			
		}
		
		untitledTree.expandAll();
		parentItem.setExpanded(true);
		
		//refresh
		untitledContainer.layout();
		this.layout();//container.layout();

	}

	public void addResourceToProject(ResourceChildModel resourceChild, String projectId) {

		TreeItem item = PMModel.getSelectedTreeItem();

		if (item != null) {
			TreeItem newResource = newItem(resourceChild);
			item.add(newResource);
			item.setExpanded(true);
		}
	}

	public boolean untitledProjectExists() {
		boolean untitledProjectExists = false;

		Component comp = getItemByItemId("untitled");

		if (comp == null) {
			untitledProjectExists = false;
		} else
			untitledProjectExists = true;

		return untitledProjectExists;
	}

	public TreeItem newItem(ResourceChildModel model) {
		TreeItem item = new TreeItem(model.getName());
		item.setId(model.getId());
		ComponentHelper.setModel(item, model);
		item.setIconStyle(ResourceType.resourceIconMap.get(model.getType()));
		if (item.getIconStyle() == null)
			item.setIconStyle(ResourceType.resourceIconMap.get(ResourceType.DEFAULT));
		item.setData("region", model.getRegion());
		return item;
	}

	public ContentPanel newPanel(String text, String id) {
		ContentPanel panel = new ContentPanel();
		panel.setHeading(text);
		panel.setScrollMode(Scroll.AUTO);
		panel.setId(id);
		//panel.setExpanded(false);

		return panel;
	}

	public void addTreeListener(final Tree tree) {
		tree.addListener(Events.OnClick, ProjectManagerController.onClickTreeItem(tree));

		tree.addListener(Events.OnDoubleClick, ProjectManagerController.onDoubleClickTreeItem(tree));

		// initialize the project manager context menu
		tree.setContextMenu(new ProjectManagerContextMenu(this).build(null));

		tree.addListener(Events.ContextMenu, ProjectManagerController.setContextMenu(tree, this));
	}
	
	private boolean isResourceInTree(String newResourceId, Tree untitledTree) {
		boolean isInTree = false;
		
		TreeItem root = untitledTree.getItem(0);
		List<TreeItem> children = root.getItems();
		System.out.println("--------------- isResourceInTree: newResourceId = " + newResourceId);
		for (int i = 0; i < children.size(); i++) {
			TreeItem rsrc = children.get(i);		
			System.out.println("--------------- isResourceInTree: rsrc.getId() = " + rsrc.getId());
			if(rsrc.getId().equals(newResourceId)) {
				System.out.println("--------------- isResourceInTree: MATCH on the id !!!");
				isInTree = true;
				break;
			}	
		}
			
		return isInTree;
	}

}
