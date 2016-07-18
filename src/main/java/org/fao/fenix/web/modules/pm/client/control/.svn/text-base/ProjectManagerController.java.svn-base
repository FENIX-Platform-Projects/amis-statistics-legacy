package org.fao.fenix.web.modules.pm.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.CategoryList;
import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.pm.client.view.ProjectContainer;
import org.fao.fenix.web.modules.pm.client.view.ProjectManager;
import org.fao.fenix.web.modules.pm.client.view.ProjectManagerContextMenu;
import org.fao.fenix.web.modules.pm.common.services.ProjectManagerServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ProjectManagerController {

	public static WindowListener onCloseManager(final ProjectManager manager) {
		return new WindowListener() {
			public void handleEvent(WindowEvent event) {
				if (event.getType() == Events.Hide) {
					PMModel.setProjectManagerIsOpen(false);
					PMModel.setProjectManager(null);
					manager.getWindow().hide();
				}
			}
		};
	}

	public static void buildProjectTree(final ResourceChildModel parentProject, final ProjectContainer container) {
		ProjectManagerServiceEntry.getInstance().getResourceChildren(Long.valueOf(parentProject.getId()),
				new AsyncCallback<List<FenixResourceVo>>() {
					public void onSuccess(final List<FenixResourceVo> result) {

						Tree tree = new Tree();
						container.addTreeListener(tree);

						TreeItem parentItem = container.newItem(parentProject);
						parentItem.setData("projectRegion", parentProject.getRegionCode());
						tree.getRootItem().add(parentItem);

						List<ResourceChildModel> list = getResourceModel(result);

						for (int j = 0; j < list.size(); j++) {
							parentItem.add(container.newItem(list.get(j)));
						}

						parentItem.setExpanded(true);

						Component comp = container.getItemByItemId(parentProject.getId());
						ContentPanel panel = (ContentPanel) comp;
						
						panel.add(tree);
						tree.expandAll();
	
						//refresh
						panel.layout();
						container.layout();
					
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("Error", "getResourceChildren Failed");
					}
				});

	}

//	public static void buildPatternTree(final TreeItem pattern, final TreeItem project, final ProjectContainer container) {
//
//		ProjectManagerServiceEntry.getInstance().getPatternDatasets(Long.valueOf(pattern.getId()),
//				new AsyncCallback<List<FenixResourceVo>>() {
//					public void onSuccess(final List<FenixResourceVo> result) {
//
//						List<ResourceChildModel> datasets = getResourceModel(result);
//
//						for (int j = 0; j < datasets.size(); j++) {
//							pattern.add(container.newItem(datasets.get(j)));
//						}
//
//						project.add(pattern);
//
//						Component comp = container.getItemByItemId(project.getId());
//						ContentPanel panel = (ContentPanel) comp;
//						panel.setExpanded(true);
//						pattern.setExpanded(true);
//
//						// refresh
//						PMModel.getProjectManager().getWindow().setWidth(
//								PMModel.getProjectManager().getWindow().getWidth());
//
//					}
//
//					public void onFailure(Throwable caught) {
//						FenixAlert.alert("Error", "getPatternDatasets Failed");
//					}
//				});
//
//	}

	public static List<ResourceChildModel> getResourceModel(List<FenixResourceVo> result) {
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		for (int i = 0; i < result.size(); i++) {
			FenixResourceVo resource = (FenixResourceVo) result.get(i);
			ResourceChildModel model = new ResourceChildModel(resource.getTitle());
			model.set("dateModified", resource.getLastUpdated());
			model.set("region", resource.getRegion());
			//model.set("category", new CategoryList().getCategoryLabel(resource.getCategory()));

			/** MODIFIED **/
			CategoryList c = new CategoryList();
			c.getCategoryResourceChildModel(resource.getCategory(), model);
			
			model.setId(resource.getId()); // set ID

			model.setType(resource.getType()); // set Resource type
			models.add(model);
		}

		return models;

	}

	public static Listener onClickTreeItem(final Tree tree) {
		return new Listener() {
			public void handleEvent(BaseEvent event) {
				TreeItem item = tree.getSelectionModel().getSelectedItem();
				PMModel.setSelectedTreeItem(item);
			}
		};
	}

	public static Listener onDoubleClickTreeItem(final Tree tree) {
		return new Listener() {
			public void handleEvent(BaseEvent event) {
				TreeItem item = tree.getSelectionModel().getSelectedItem();
				PMModel.setSelectedTreeItem(item);
				ResourceOpener.openResource(item);
			}
		};
	}

	public static Listener setContextMenu(final Tree tree, final ProjectContainer container) {
		return new Listener() {
			public void handleEvent(BaseEvent event) {
				TreeItem item = tree.getSelectionModel().getSelectedItem();
				PMModel.setSelectedTreeItem(item);
				tree.setContextMenu(new ProjectManagerContextMenu(container).build(item));
			}
		};
	}

}
