package org.fao.fenix.web.modules.re.client.control.project;

import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.project.CatalogueContextMenuProject;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

/**
 * 
 * CatalogueProjectController class loads a TreeTable. The tree's child items are loaded on demand.
 * 
 */
public class CatalogueProjectController extends CatalogueController{

	/*public static void fillTable(final TreeTable table, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final Catalogue catalogue) {

		REServiceEntry.getInstance().getSearchResultListLength(fenixSearchParameters, new AsyncCallback<Integer>() {
			public void onSuccess(final Integer result) {
				cataloguePager.setFenixPager(new FenixPager(result, NumberPages.NUM_PAGES));
				cataloguePager.writeInfo();
				cataloguePager.validateButtons();
				int to;
				if (result >= NumberPages.NUM_PAGES) {
					to = NumberPages.NUM_PAGES;
				} else {
					to = result;
				}

				catalogue.setTreeStore(null);
				catalogue.setProxyTreeLoader(null);

				// A data proxy is used to load the ResourceChildModel data from
				// the server on demand
				// The RpcProxy executes, and the service call is made to
				// retrieve the appropriate list of resources for the
				// selectedItem
//				RpcProxy<ResourceChildModel, List<ResourceChildModel>> proxy = new RpcProxy<ResourceChildModel, List<ResourceChildModel>>() {
//					@Override
//					protected void load(ResourceChildModel selectedItem, AsyncCallback<List<ResourceChildModel>> callback) {
//						REServiceEntry.getInstance().getResourceModel(selectedItem, fenixSearchParameters, cataloguePager.getFenixPager().calculateIndexFrom(), result, callback);
//					}
//				};
				RpcProxy<List<ResourceChildModel>> proxy = new RpcProxy<List<ResourceChildModel>>() {

					@Override
					protected void load(Object loadConfig, AsyncCallback<List<ResourceChildModel>> callback) {
						REServiceEntry.getInstance().getResourceModel((ResourceChildModel)loadConfig,
								fenixSearchParameters, cataloguePager.getFenixPager().calculateIndexFrom(), result, callback);

					}

				};

				catalogue.setProxyTreeLoader(proxy);

				// The TreeStore uses the treeLoader to load the
				// ResourceChildModel data
				TreeStore<ResourceChildModel> treeStore = new TreeStore<ResourceChildModel>(catalogue.getTreeLoader());

				catalogue.initializeTreeStore(treeStore);

				final TreeTableBinder<ResourceChildModel> treeBinder = new TreeTableBinder<ResourceChildModel>(table, catalogue.getTreeStore());

				treeBinder.init();
				catalogue.initializeTreeBinder(treeBinder);

				// load() is then called on the treeLoader, null is passed as
				// the start input.
				// null is a request for the root elements which have no parent
				catalogue.getTreeLoader().load(null);

			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "RPC Failed: fillTable.getSearchResultListLength");
			}
		});
	}*/

	/**
	 * This method works as entry-point switcher to search either trough the node or through the GIEWS network.
	 */
	/*public static void updateTable(final ResourceExplorer resourceExplorer, final Grid<ResourceChildModel> table, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final Catalogue catalogue) {
		if (resourceExplorer.getSearchParameters() instanceof SearchParametersDataset || resourceExplorer.getSearchParameters() instanceof SearchParametersLayer) {
			String scope = ((SearchParametersCommunication) resourceExplorer.getSearchParameters()).getLookIn();
			resourceExplorer.getSearchButtons().getFenixSearchParameters().setScope(scope);
		}
		if (fenixSearchParameters.getScope().equals(I18N.print().scopeNode().trim()))
			updateTableNode(resourceExplorer, table, fenixSearchParameters, cataloguePager, catalogue);
		else if (fenixSearchParameters.getScope().equals(I18N.print().scopeNetwork().trim()))
			updateTableCommunication(resourceExplorer, table, fenixSearchParameters, cataloguePager, catalogue);
	}

	public static void updateTableCommunication(final ResourceExplorer resourceExplorer, final TreeTable table, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final Catalogue catalogue) {

		System.out.println(">>>>>>>>>> SYSTEM ENTERED updateTableCommunication @ CatalogueProjectController");
		REServiceEntry.getInstance().getCommunicationResourceSize(fenixSearchParameters, new AsyncCallback<Integer>() {

			public void onSuccess(final Integer result) {

				cataloguePager.setFenixPager(new FenixPager(result, NumberPages.NUM_PAGES));
				cataloguePager.writeInfo();
				cataloguePager.validateButtons();
				int to;
				if (result >= NumberPages.NUM_PAGES)
					to = NumberPages.NUM_PAGES;
				else
					to = result;
				catalogue.setTreeStore(null);
				catalogue.setProxyTreeLoader(null);
				catalogue.setTreeBinder(null);

				REServiceEntry.getInstance().searchCommunication(fenixSearchParameters, cataloguePager.getFenixPager().calculateIndexFrom(), cataloguePager.getFenixPager().calculateIndexTo(), new AsyncCallback<List<CommunicationResourceVo>>() {
					public void onSuccess(final List<CommunicationResourceVo> result) {

						table.removeAll();

//						RpcProxy<ResourceChildModel, List<ResourceChildModel>> proxy = new RpcProxy<ResourceChildModel, List<ResourceChildModel>>() {
//							@Override
//							protected void load(ResourceChildModel selectedItem, AsyncCallback<List<ResourceChildModel>> callback) {
//								REServiceEntry.getInstance().getResourceModelCommunication2(selectedItem, result, callback);
//							}
//						};
						RpcProxy<List<ResourceChildModel>> proxy = new RpcProxy<List<ResourceChildModel>>() {
							@Override
							protected void load(Object loadConfig, AsyncCallback<List<ResourceChildModel>> callback) {
								REServiceEntry.getInstance().getResourceModelCommunication2((ResourceChildModel)loadConfig, result, callback);
							}
						};
						
						catalogue.setProxyTreeLoader(proxy);
						TreeStore<ResourceChildModel> treeStore = new TreeStore<ResourceChildModel>(catalogue.getTreeLoader());
						catalogue.initializeTreeStore(treeStore);
						final TreeTableBinder<ResourceChildModel> treeBinder = new TreeTableBinder<ResourceChildModel>(table, catalogue.getTreeStore());
						treeBinder.init();
						catalogue.initializeTreeBinder(treeBinder);
						catalogue.getTreeLoader().load(null);

						// renameTableHeadersNetwork(table);
						table.setContextMenu(new CatalogueContextMenuCommunication(catalogue).buildMenu());
					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("RPC Error", "updateTableCommunication failed!");
					}
				});
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "RPC failed: updateTable.search");
			}

		});

	}

	public static void renameTableHeadersNetwork(final TreeTable table) {
		try {
			table.getColumn(0).setText("ONE");
			table.getColumn(2).setText("TWO");
			table.getColumn(3).setText("THREE");
			table.getColumn(4).setText("FOUR");
		} catch (NullPointerException e) {
			System.out.println("WORK IN PROGRESS @ renameTableHeadersNetwork");
		}
	}

	public static void renameTableHeadersNode(final TreeTable table) {
		table.getColumn(0).setText("Name");
		table.getColumn(2).setText("Geographic Area");
		table.getColumn(3).setText("Category");
		table.getColumn(4).setText("Date Modified");
	}

	public static void updateTableNode(final ResourceExplorer resourceExplorer, final TreeTable table, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final Catalogue catalogue) {

		REServiceEntry.getInstance().getSearchResultListLength(fenixSearchParameters, new AsyncCallback<Integer>() {
			public void onSuccess(final Integer result) {
				cataloguePager.setFenixPager(new FenixPager(result, NumberPages.NUM_PAGES));
				cataloguePager.writeInfo();
				cataloguePager.validateButtons();
				int to;
				if (result >= NumberPages.NUM_PAGES) {
					to = NumberPages.NUM_PAGES;
				} else {
					to = result;
				}
				catalogue.setTreeStore(null);
				catalogue.setProxyTreeLoader(null);
				catalogue.setTreeBinder(null);
				REServiceEntry.getInstance().search(fenixSearchParameters, cataloguePager.getFenixPager().calculateIndexFrom(), cataloguePager.getFenixPager().calculateIndexTo(), new AsyncCallback<List<FenixResourceVo>>() {
					public void onSuccess(final List<FenixResourceVo> result) {
						table.removeAll();
//						RpcProxy<ResourceChildModel, List<ResourceChildModel>> proxy = new RpcProxy<ResourceChildModel, List<ResourceChildModel>>() {
//							@Override
//							protected void load(ResourceChildModel selectedItem, AsyncCallback<List<ResourceChildModel>> callback) {
//								REServiceEntry.getInstance().getResourceModel2(selectedItem, result, callback);
//							}
//						};
						RpcProxy<List<ResourceChildModel>> proxy = new RpcProxy<List<ResourceChildModel>>() {
							@Override
							protected void load(Object loadConfig, AsyncCallback<List<ResourceChildModel>> callback) {
								REServiceEntry.getInstance().getResourceModel2((ResourceChildModel)loadConfig, result, callback); // TODO checkme
							}
						};
						catalogue.setProxyTreeLoader(proxy);
						// The TreeStore uses the treeLoader to load the
						// ResourceChildModel data
						TreeStore<ResourceChildModel> treeStore = new TreeStore<ResourceChildModel>(catalogue.getTreeLoader());
						catalogue.initializeTreeStore(treeStore);
						final TreeTableBinder<ResourceChildModel> treeBinder = new TreeTableBinder<ResourceChildModel>(table, catalogue.getTreeStore());
						treeBinder.init();
						catalogue.initializeTreeBinder(treeBinder);
						catalogue.getTreeLoader().load(null);

						// renameTableHeadersNode(table);
						table.setContextMenu(new CatalogueContextMenuDataset(resourceExplorer, catalogue).buildMenu());

					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("Error", "RPC failed: updateTable.search");
					}
				});
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "RPC Failed: updateTable.getSearchResultListLength");
			}
		});
	}
*/
	public static Listener<BaseEvent> onDoubleClick(final Grid<ResourceChildModel> table) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				onDoubleClickAction(table);
			}
		};
	}
	
	public static void onDoubleClickAction(final Grid<ResourceChildModel> table){
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

		for (ResourceChildModel model : selectedItems) {
			
			// add to project manager
			if(model.getType().equals(ResourceType.PROJECT)){
				//add to project manager
				PMModel.addProjectToProjectManager(selectedItems);
			}
		}

		// close resource explorer
		if (REModel.CLOSE) REModel.getResourceExplorer().getWindow().close();
		
		// Info.display("handle DoubleClick Event", "The '{0}' button was clicked.", "onDoubleClick");
	}
	
	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuProject(resourceExplorer, catalogue).buildMenu());	
				}
				else{ 
					System.out.println("############# CatalogueProjectController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}
}
