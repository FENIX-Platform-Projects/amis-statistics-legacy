package org.fao.fenix.web.modules.re.client.control.search;

import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.pm.common.services.ProjectManagerServiceEntry;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.search.ResourceExplorerSearch;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CataloguePagerSearchController extends CataloguePagerController {

	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer,
			final String resourceType) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
					for (ResourceChildModel model : selectedItems) {						
						ResourceOpener.resourceOpener(model, null);
					}

					// add to project manager
					if (!resourceType.equals(ResourceType.PROJECT))
						PMModel.addResourceToProjectManager(selectedItems, resourceType);
					else
						PMModel.addProjectToProjectManager(selectedItems);
					
					// close RE
					if (REModel.CLOSE) resourceExplorer.getWindow().close();

				} else if (resourceExplorer.getCaller().equals(WhoCall.PROJECTMANAGER)) {


					// selected project from project manager
					TreeItem selectedProject = PMModel.getSelectedTreeItem();

					if (selectedProject != null) {
						String projId =  selectedProject.getId();
						
						if (!projId.equals("untitled")) {
							final Long projectId = Long.valueOf(projId);

                     	// add resources to selected project
							for (int i = 0; i < selectedItems.size(); i++) {
								final ResourceChildModel rsrc =  selectedItems.get(i);
								final Long resourceId = Long.valueOf(rsrc.getId());

								if (!rsrc.getType().equals(ResourceType.PROJECT)) {
									// RPC to add resource to project
									ProjectManagerServiceEntry.getInstance().addResourceTo(projectId, resourceId,
											new AsyncCallback() {

												public void onSuccess(Object result) {
													FenixAlert.info(BabelFish.print().successful(),
															BabelFish.print().resourceSuccessfullyAdded());

													// update project manager
													((ResourceExplorerSearch) resourceExplorer).getProjectManager()
															.addResourceToProject(rsrc, String.valueOf(projectId));

													// refresh window
													((ResourceExplorerSearch) resourceExplorer).getProjectManager()
															.getWindow().getLayout().setContainer(
																	((ResourceExplorerSearch) resourceExplorer)
																			.getProjectManager().getWindow());

													// close RE
													resourceExplorer.getWindow().hide();
												}

												public void onFailure(Throwable caught) {
													FenixAlert.error(BabelFish.print().error(),
													"The resource could not be added as you do now have permissions to do so");
												}

											});
								} else
									FenixAlert.error(BabelFish.print().error(), BabelFish.print()
											.projectsCannotBeAdded());
							}
						} else {
							// add resources to selected project
							for (int i = 0; i < selectedItems.size(); i++) {
								ResourceChildModel rsrc = selectedItems.get(i);

								// update project manager
								((ResourceExplorerSearch) resourceExplorer).getProjectManager().addResourceToProject(
										rsrc, String.valueOf(projId));

								// refresh window
								((ResourceExplorerSearch) resourceExplorer).getProjectManager().getWindow().getLayout()
										.setContainer(
												((ResourceExplorerSearch) resourceExplorer).getProjectManager()
														.getWindow());

							}
							
							// close RE
							resourceExplorer.getWindow().hide();
						}

					}

				}

			}
		};
	}

}
