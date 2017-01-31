package org.fao.fenix.web.modules.re.client.control.communication;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.communication.client.view.CommunicationMetadataWindow;
import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.x.common.services.XServiceEntry;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueContextMenuCommunicationController extends CatalogueContextMenuController {

	public static SelectionListener<MenuEvent> viewCommunicationResourceMetadata(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent component) {
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				for (ResourceChildModel model : selectedItems)
					new CommunicationMetadataWindow().build(model);
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public static SelectionListener<MenuEvent> deleteCommunicationResource(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent component) {
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				for (ResourceChildModel model : selectedItems) {
						try {
							CommunicationServiceEntry.getInstance().deleteCommunicationResource(Long.parseLong(model.getId()), new AsyncCallback() {
								public void onSuccess(Object result) {
									FenixAlert.info(BabelFish.print().info(), BabelFish.print().communicationResourceHasBeenDeleted());
								}
								public void onFailure(Throwable caught) {
									FenixAlert.alert("RPC Failed", "deleteCommunicationResource @ CatalogueContextMenuCommunicationController");
								}
							});
						} catch (NumberFormatException e) {
							FenixAlert.alert(BabelFish.print().info(), e.getMessage());
						} catch (FenixGWTException e) {
							FenixAlert.alert(BabelFish.print().info(), e.getMessage());
						}
				}
			}
		};
	}

	public static SelectionListener<MenuEvent> download(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent component) {
//				if (FenixUser.hasAdminRole() || FenixUser.hasUserRole()) {
//					final LoadingWindow loadingWindow = new LoadingWindow();
//					loadingWindow.create();
//					List<ResourceChildModel> resources = new ArrayList<ResourceChildModel>();
//					for (TreeItem item : table.getSelectedItems()) {
//						final ResourceChildModel model = (ResourceChildModel) item.getModel();
//						resources.add(model);
//						CommunicationServiceEntry.getInstance().downloadResource(model.getHost(), model.getLocalId(), new AsyncCallback() {
//							public void onSuccess(Object result) {
//								loadingWindow.destroy();
//								FenixAlert.info(I18N.print().info(), I18N.print().resourceHasBeenDownloaded());
//							}
//
//							public void onFailure(Throwable caught) {
//								FenixAlert.alert("RPC Failed", "download @ CatalogueContextMenuCommunicationController");
//							}
//						});
//					}
//				} else {
//					FenixAlert.info(I18N.print().info(), I18N.print().pleaseLogin());
//				}
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static SelectionListener<MenuEvent> share(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent component) {
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				List<Long> selectedIDs = new ArrayList<Long>();
				String resourceType = null;
				for (ResourceChildModel model: selectedItems) {
					selectedIDs.add(Long.valueOf(model.getId()));
					resourceType = model.getType();
				}
				if (resourceType.equalsIgnoreCase("TextView")) // change it to fit the <XResourceType enum
					resourceType = "text";
				try {
					final LoadingWindow loading = new LoadingWindow("X - Information eXchange Tool", "System is making available selected resource(s) for download from other GIEWS Network Nodes.", "Please wait...");
					XServiceEntry.getInstance().createXResource(selectedIDs, resourceType, new AsyncCallback() {
						public void onSuccess(Object result) {
							loading.destroyLoadingBox();
							FenixAlert.info("INFO", "Resource(s) succesfully shared.");
							loading.destroyLoadingBox();
						}
						public void onFailure(Throwable e) {
							loading.destroyLoadingBox();
							FenixAlert.error("ERROR", e.getMessage());
							loading.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}

}
