package org.fao.fenix.web.modules.pm.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.pm.client.view.ProjectContainer;
import org.fao.fenix.web.modules.pm.common.services.ProjectManagerServiceEntry;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.search.ResourceExplorerSearch;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ProjectManagerContextMenuController {
	
	public static SelectionListener<MenuEvent> addResourceToManager(final TreeItem item) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				new ResourceExplorerSearch(PMModel.getProjectManager());
			}
		};
	}

	public static SelectionListener<MenuEvent> hideResource(final TreeItem selectedItem, final ProjectContainer container) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				if (selectedItem != null) {					
					selectedItem.getParentItem().remove(selectedItem);
				}
			}

		};
	}
	
	public static SelectionListener<MenuEvent> removeProjectFromManager(final TreeItem selectedItem, final ProjectContainer container) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {

				if (selectedItem != null) {
					String id = selectedItem.getId();
					ResourceChildModel model = (ResourceChildModel)selectedItem.getModel();
					Component component;
					ContentPanel projectPanel;

					if(model!=null){
						String type = model.getType();
						if(type.equals(ResourceType.PROJECT)) {
							component = container.getItemByItemId(model.getId());
							projectPanel = (ContentPanel) component;
							container.remove(projectPanel);
						} //else 
						//	selectedItem.getParentItem().remove(selectedItem);
					} else if(id.equals("untitled")){
						component = container.getItemByItemId(id);
						projectPanel = (ContentPanel) component;
						container.remove(projectPanel);
					} 
				}
			}

		};
	}
	
	public static SelectionListener<MenuEvent> showHiddenResource(final TreeItem selectedItem, final ProjectContainer container) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {

				if (selectedItem != null) {
					String id = selectedItem.getId();
					ResourceChildModel model = (ResourceChildModel)selectedItem.getModel();
					
					Component component;
					ContentPanel projectPanel;

					if(model!=null){
						String type = model.getType();
						if(type.equals(ResourceType.PROJECT)) {
							final List<ResourceChildModel> projectList = new ArrayList<ResourceChildModel>();
							projectList.add(model);
							
							//remove project panel
							component = container.getItemByItemId(model.getId());
							projectPanel = (ContentPanel) component;
							int index = projectPanel.indexOf(component);
							
							container.remove(projectPanel);
							
							//re-add project 
							//container.addProjects(projectList, index);
							PMModel.addProjectToProjectManager(projectList);		
						} 
					} else if(id.equals("untitled")){
						FenixAlert.info("Information", "This feature is not applicable to the 'Untitled' project");
					} 
				}
			}

		};
	}
	
	
	public static SelectionListener<MenuEvent> saveAs(final TreeItem selectedItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
			     List<Long> resourceIdList = new ArrayList<Long>();
			    if (selectedItem.hasChildren()){
			        List<TreeItem> children = selectedItem.getItems();	
			        for(int i=0; i< children.size(); i++){
			           TreeItem childItem = children.get(i);
			           ResourceChildModel model = (ResourceChildModel)childItem.getModel(); 	
			           resourceIdList.add(Long.valueOf(model.getId()));
			        }
			       		    
			    }
			    MetadataEditorWindow meWindow = new MetadataEditorWindow();
			    meWindow.build(false, true, false, MESaver.getSaveProjectListener(meWindow, resourceIdList));
			}
		};
	}
	
	public static SelectionListener<MenuEvent> viewMetadata(final TreeItem selectedItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
					ResourceChildModel model = (ResourceChildModel)selectedItem.getModel();
					if (model.getType().equalsIgnoreCase(ResourceType.LAYER) || model.getType().equalsIgnoreCase(ResourceType.LAYERSUBTYPE))
						MEOpener.showMetadata(Long.parseLong(model.getId()), false, true);
					else
						MEOpener.showMetadata(Long.parseLong(model.getId()), false, false);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> rename(final TreeItem selectedItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				ResourceChildModel model = (ResourceChildModel)selectedItem.getModel();
				ResourceOpener.openRename(model);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> openResource(final TreeItem selectedItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				  ResourceOpener.openResource(selectedItem);	
			}
		};
	}
	
	
	public static SelectionListener<MenuEvent> delete(final Tree tree) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				confirmDelete(tree);
			}
		};
	}
	
	
	public static SelectionListener<MenuEvent> view(final TreeItem selectedItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				ResourceOpener.openDatasetAsTable(selectedItem);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> chartAnalysis(final TreeItem selectedItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				ResourceOpener.openDatasetAsChart(selectedItem);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> tableAnalysis(final TreeItem selectedItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				OlapWindow olap = new OlapWindow();
				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
					olap.build(true, true);
				} else {
					olap.build(true, false);
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> deleteResourceFromProject(final TreeItem selectedItem, final ProjectContainer container) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {

				if (selectedItem != null) {
					final String projectId = selectedItem.getParentItem().getId();
					final ResourceChildModel projectModel = (ResourceChildModel)selectedItem.getParentItem().getModel();
					final ResourceChildModel resourceModel = (ResourceChildModel)selectedItem.getModel();

					if(projectModel!=null && resourceModel!=null){
						ProjectManagerServiceEntry.getInstance().deleteResourceFrom(Long.valueOf(projectModel.getId()), Long.valueOf(resourceModel.getId()), new AsyncCallback() {
							public void onSuccess(Object result) {
								selectedItem.getParentItem().remove(selectedItem);
							}
							public void onFailure(Throwable caught) {
								FenixAlert.error(BabelFish.print().error(), "RPC Fail ProjectManagerService.deleteResourceFrom");
							}
						});
					} else if(projectId.equals("untitled")){
						selectedItem.getParentItem().remove(selectedItem);
					} 

				}		
			}

		};
	}
	/*public static void openMetadata(final Long id) {
		new MetadataViewer(id).build();
	}
	
	
	public static void openRename(final ResourceChildModel model) {
		new MetadataRename().build(model);
	}*/
		
	private static void confirmDelete(final Tree tree) {

		MessageBox box = new MessageBox();   
		box.setButtons(MessageBox.YESNO);   
		box.setIcon(MessageBox.WARNING);   
		box.setTitle("<b>"+BabelFish.print().confirmResourceDelete()+"</b>");
		box.setMessage("<b>"+BabelFish.print().areYouSureDeleteResource()+"</b>");   
		box.addCallback(new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent event) {
				Dialog dialog = (Dialog) event.getComponent();
				Button btn = event.getButtonClicked();
             	if(btn.getText().equals("Yes")){
					//Info.display("confirmDelete", "The '{0}' button was pressed", btn.getText());
					for (TreeItem item: tree.getSelectedItems()){
						ResourceChildModel model = (ResourceChildModel)item.getModel();
						deleteResource(Long.parseLong((model.getId())), model.getType());	
                     }
				}   else { 
				//	Info.display("confirmDelete", "The '{0}' button was pressed", btn.getText());
					dialog.hide();   
				}
			}   
		});   

		box.show();   
	}
	
	
    private static void deleteResource(final long id, final String resourceType) {
		REServiceEntry.getInstance().deleteResource(id, resourceType, new AsyncCallback() {
			public void onSuccess(Object result) {
				//refresh the catalogue
				if (REModel.isReIsopen()) {
					ResourceExplorer explorer = REModel.getResourceExplorer();
					//	if (explorer instanceof ResourceExplorerProject || explorer instanceof ResourceExplorerSearch ){
					//		CatalogueProjectController.fillTable(explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
					//	} else{
							CatalogueController.fillGrid(explorer, explorer.getCatalogue().getGrid(), explorer.getSearchButtons().getFenixSearchParameters(), explorer.getCataloguePager(), explorer.getCatalogue());
					//	}
		    	}
				
				FenixAlert.info(BabelFish.print().deleteCompleted(), BabelFish.print().deleteSuccessful());
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().error(), "RPC Fail REService.deleteResource");
			}
		});
	}
    
   

}
