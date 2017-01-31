package org.fao.fenix.web.modules.re.client.control.dataset;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueContextMenuDatasetController extends CatalogueContextMenuController {

	public static SelectionListener<MenuEvent> open(final ResourceExplorer resourceExplorer, final String typeResource, final Grid<ResourceChildModel>  table) {
		return new SelectionListener<MenuEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(MenuEvent ce) {

				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				// We should unify the dispatching of evets inside fenix.
				// Typeresource and whocall
				// are way too intrusive. The callback is lighter and more
				// general.

				if (resourceExplorer.getCallback() != null) {
					resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
					// resourceExplorer.getWindow().close();
				}

				if (typeResource.equals("chart")) {

					// hide the resource explorer
					// REModel.getResourceExplorer().getWindow().hide();
					REModel.setResourceExplorer(null);

					final List<List<String>> datasetsMap = new ArrayList<List<String>>();
							
					final int datasetSelected = (selectedItems.size() - 1);
					
					for (int i = 0; i < selectedItems.size(); i++) {

						final ResourceChildModel model = selectedItems.get(i);
						final int count = i;

						BirtServiceEntry.getInstance().isQualitativeDataset(Long.valueOf(model.getId()), new AsyncCallback<Boolean>() {

							public void onSuccess(Boolean result) {
								if (!result) {
									List<String> element = new ArrayList<String>();
									element.add(model.getName()); // i.e. title
									element.add(model.getId());
									datasetsMap.add(element);
								} else {
									FenixAlert.alert("Warning", model.getName() + " is a qualitative dataset. You can't chart it.", "");
								}

								if (datasetSelected == count && datasetsMap.size() > 0) {

									if (REModel.CLOSE) {
										// hide the context menu
										REModel.getResourceExplorer().setCatalogueContextMenu(null);
										resourceExplorer.getWindow().close();
									}

									new ChartWizard(datasetsMap);

									// add to Project Manager
									PMModel.addResourceToProjectManager(selectedItems, ResourceType.DATASET);

								}

							}

							public void onFailure(Throwable caught) {
								Info.display("isQualitativeDataset", caught.getLocalizedMessage());
							}
						});

					}

				}

				if (typeResource.equals("table")) {

					// hide the resource explorer
					// REModel.getResourceExplorer().getWindow().hide();
					REModel.setResourceExplorer(null);
				
					for (ResourceChildModel model : selectedItems) {
						Long resourceId = Long.valueOf(model.getId());
						
						new TableWindow().showAllData(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
						//new TableWindow().build(resourceId, model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
						//new UDTableWindow().build(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType());

					}

					if (REModel.CLOSE) {
						// hide the context menu
						REModel.getResourceExplorer().setCatalogueContextMenu(null);
						resourceExplorer.getWindow().close();
					}

					// add to Project Manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.DATASET);
				}

				if (typeResource.equals("tableAnalysis")) {

					// hide the resource explorer
					// REModel.getResourceExplorer().getWindow().hide();
					REModel.setResourceExplorer(null);

					List<ResourceChildModel> resources = new ArrayList<ResourceChildModel>();

					for (ResourceChildModel model : selectedItems) {
						@SuppressWarnings("unused")
						Long resourceId = Long.valueOf(model.getId());
					}

					OlapWindow olap = new OlapWindow();
					if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
						olap.build(true, true);
					} else {
						olap.build(true, false);
					}

					if (REModel.CLOSE) {
						// hide the context menu
						REModel.getResourceExplorer().setCatalogueContextMenu(null);
						resourceExplorer.getWindow().close();
					}

					// add to Project Manager
					PMModel.addResourceToProjectManager(resources, ResourceType.DATASET);
				}
			}
		};
	}

	public static SelectionListener<MenuEvent> exportAsCSV(final Grid<ResourceChildModel> table) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				ResourceChildModel model = table.getSelectionModel().getSelectedItem();
				final LoadingWindow loading = new LoadingWindow(BabelFish.print().exportAsCSV(), BabelFish.print().pleaseWait(), BabelFish.print().pleaseWait());
				BirtServiceEntry.getInstance().exportAsCSV(Long.valueOf(model.getId()), new AsyncCallback<String>() {

					public void onSuccess(String fileName) {
						Window.open("../exportObject/" + fileName, "_blank", "status=no");
						loading.destroyLoadingBox();
					}

					public void onFailure(Throwable caught) {
						Info.display("exportAsCSV", caught.getLocalizedMessage());
						loading.destroyLoadingBox();
					}
				});

			}
		};
	}

}
