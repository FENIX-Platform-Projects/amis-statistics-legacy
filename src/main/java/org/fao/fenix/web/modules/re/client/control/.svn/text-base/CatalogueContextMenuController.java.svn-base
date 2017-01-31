package org.fao.fenix.web.modules.re.client.control;

import java.util.ArrayList;
import java.util.List;


import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.security.permissionmanager.PermissionManagerControllerRe;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.ipc.client.view.ConfirmWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceParentModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class CatalogueContextMenuController {

	public static SelectionListener<MenuEvent> viewMetadata(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				for (ResourceChildModel model : selectedItems) {
					if (model.getType().toLowerCase().contains("layer"))
						MEOpener.showMetadata(Long.valueOf(model.getId()), model.hasWritePermission(), true);
					else
						MEOpener.showMetadata(Long.valueOf(model.getId()), model.hasWritePermission(), false);
				}
			}
		};
	}

	public static SelectionListener<MenuEvent> rename(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				ResourceChildModel model = table.getSelectionModel().getSelectedItem();
				openRename(model);
			}
		};
	}
	
	
	public static SelectionListener<MenuEvent> changeStyleName(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				ResourceChildModel model = table.getSelectionModel().getSelectedItem();
				openChangeStyleName(model);
			}
		};
	}
	
	public static void openChangeStyleName(final ResourceChildModel model) {
		Window window = new Window();
		window.setHeading(BabelFish.print().changeLayerStyle());
		
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(10);
		panel.add(new Html("  Layer: " + model.getName()));
		
		HorizontalPanel hpanel = new HorizontalPanel();
		hpanel.setSpacing(5);
		TextField<String> field = new TextField<String>();
		field.setAllowBlank(false);
		hpanel.add(new Html("Set new style name:" ));
		hpanel.add(field);
		hpanel.add(new Button(BabelFish.print().apply(), changeLayerStyle(Long.parseLong(model.getId()), field, window)));
		panel.add(hpanel);
		
		window.add(panel);
		window.show();
		window.setSize(350, 150);
	}
	
	public static SelectionListener<ButtonEvent> changeLayerStyle(final Long resourceId, final TextField<String> field, final Window window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				// create method to change style in the db
				if ( !field.getValue().isEmpty()) {
					System.out.println("Changing style to the layer: " + resourceId +" | " + field.getValue());
					REServiceEntry.getInstance().changeLayerStyle(resourceId, field.getValue(), new AsyncCallback() {

						public void onSuccess(Object arg0) {
							FenixAlert.info(BabelFish.print().changeLayerStyle(), BabelFish.print().changeLayerStyleSuccessful());
							window.close();
						}
					
						public void onFailure(Throwable arg0) {
							FenixAlert.alert(BabelFish.print().changeLayerStyle(), "The Style has not been changed");
						}

		
						
					});
				}
				else 
					FenixAlert.alert(BabelFish.print().changeLayerStyle(), "Style name cannot be empty");
			}
				
		};
	}
	
	

	public static SelectionListener<MenuEvent> delete(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				confirmDelete(table);
			}
		};
	}

	/*
	 * public static void openMetadata(final Long id) { new MetadataViewer(id).build(); }
	 */

	public static void openRename(final ResourceChildModel model) {
		MetadataEditorWindow meWindow = new MetadataEditorWindow();
		meWindow.build(false, true, false, MESaver.getUpdateMetadataListener(meWindow, Long.parseLong(model.getId())));
	}
	

	private static void confirmDelete(final Grid<ResourceChildModel> table) {
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
		
		for (final ResourceChildModel model : selectedItems) {
			if (!model.hasDeletePermission() && !FenixUser.hasAdminRole()) {
				FenixAlert.alert("Security Alert", "You do not have delete permission on this resource", "0");
			} else {
				
				System.out.println("ID: " + model.getId() + " | resourcetype: " +  model.getType()); 
				/** TODO: check associated reources if is a dataset **/
				Window window = new Window();
				REServiceEntry.getInstance().checkConnectedResources(Long.parseLong((model.getId())), model.getType(), new AsyncCallback<List<ResourceParentModel>>() {
					public void onSuccess(List<ResourceParentModel> result) {
						String text = new String();
						text += "<font color='red'> <b>" + BabelFish.print().areYouSureDeleteResource() + "</b></font>";
						
						if ( !result.isEmpty() ) {	
							text += "<br><br> <b>"+ BabelFish.print().resourceConnected() +":</b><br>";
							/** TODO: the resourcetype should be translated dynamically **/
							for(ResourceParentModel resource : result) {
								System.out.println("-> " + resource.getName() + " | " + resource.getType());
								text += resource.getType() + " | " + resource.getName() + "<br>";
							}
						}
						
						new ConfirmWindow(BabelFish.print().confirmResourceDelete(), text, deleteResourceConfirmed(Long.parseLong((model.getId())), model.getType()));
						
					}
					
					public void onFailure(Throwable caught) {

					}
				});
				
			
				
			}

		}

	}
	
	public static SelectionListener<ButtonEvent> deleteResourceConfirmed(final Long resourceId,final String type) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				deleteResource(resourceId, type);

				if (type.equalsIgnoreCase("wmsmapprovider")) {
					System.out.println("depublishing layerId: " + resourceId);
					MapServiceEntry.getInstance().depublishLayer(resourceId, new AsyncCallback() {

						public void onFailure(Throwable arg0) {

						}

						public void onSuccess(Object arg0) {

						}
					});
				}
			}
				
		};
	}
	

	private static void deleteResource(final long id, final String resourceType) {		
		if (FenixUser.hasAdminRole())
			REServiceEntry.getInstance().adminDeletesResource(id, resourceType, new DeleteResourceAsyncCallback());
		else
			REServiceEntry.getInstance().deleteResource(id, resourceType, new DeleteResourceAsyncCallback());
	}

	public static SelectionListener<MenuEvent> setPermissions(final Grid<ResourceChildModel> catalogueGrid) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				List<FenixResourceVo> fenixResourceVoList = new ArrayList<FenixResourceVo>();
				final List<ResourceChildModel> selectedItems = catalogueGrid.getSelectionModel().getSelectedItems();
				
				for (ResourceChildModel model : selectedItems) {
					FenixResourceVo vo = model.getFenixResourceVo();
					fenixResourceVoList.add(vo);
				}
				PermissionManagerControllerRe.getInstance(fenixResourceVoList);
			}
		};
	}
	
	
	public static SelectionListener<MenuEvent> associateDatasetToChart(final ResourceExplorer explorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent evt) {
				new ResourceExplorerDataset(explorer);
			}
		};
	}
}
