package org.fao.fenix.web.modules.re.client.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.client.utils.CategoryList;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.GAULCountryList;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.communication.CatalogueContextMenuCommunication;
import org.fao.fenix.web.modules.re.client.view.util.CommunicationResourceChildModel;
import org.fao.fenix.web.modules.re.client.view.util.Pager.FenixPager;
import org.fao.fenix.web.modules.re.client.view.util.Pager.NumberPages;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueController {

	private static GAULCountryList countryList = new GAULCountryList(false);

	public static void fillGrid(final ResourceExplorer resourceExplorer, final Grid<ResourceChildModel> table, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final Catalogue catalogue) {
//		System.out.println(">>>>>>>>>> SYSTEM ENTERED @ fillGrid");
		// FILL TABLE FOR NODE SCOPE
		if (fenixSearchParameters.getScope().equals(BabelFish.print().scopeNode()))
			fillGridNodeScope(resourceExplorer, table, fenixSearchParameters, cataloguePager, catalogue);
		else
			fillGridNetworkScope(resourceExplorer, table, fenixSearchParameters, cataloguePager, catalogue);

	}

	public static void fillGridNetworkScope(final ResourceExplorer resourceExplorer, final Grid<ResourceChildModel> table, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final Catalogue catalogue) {

//		System.out.println(">>>>>>>>>> SYSTEM ENTERED fillGridNetworkScope @ fillGrid");
		REServiceEntry.getInstance().getCommunicationResourceSize(fenixSearchParameters, new AsyncCallback<Integer>() {

			public void onSuccess(Integer result) {
				cataloguePager.setFenixPager(new FenixPager(result, NumberPages.NUM_PAGES));
				cataloguePager.writeInfo();
				cataloguePager.validateButtons();
				int to;
				if (result >= NumberPages.NUM_PAGES)
					to = NumberPages.NUM_PAGES;
				else
					to = result;

				REServiceEntry.getInstance().searchCommunication(fenixSearchParameters, cataloguePager.getFenixPager().calculateIndexFrom(), cataloguePager.getFenixPager().calculateIndexTo(), new AsyncCallback<List<CommunicationResourceVo>>() {

					public void onSuccess(final List<CommunicationResourceVo> result) {
						catalogue.getStore().removeAll();

						PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(getCommunicationResourceModel(result));

						// loader
						catalogue.setLoader(new BasePagingLoader<PagingLoadResult<ResourceChildModel>>(proxy));

						/*
						 * catalogue.getLoader().addListener(Loader.BeforeLoad,
						 * new Listener<LoadEvent>() { public void
						 * handleEvent(LoadEvent be) { BasePagingLoadConfig m =
						 * be.<BasePagingLoadConfig> getConfig(); m.set("start",
						 * m.get("offset")); m.set("ext", "js");
						 * m.set("lightWeight", true); m.set("sort",
						 * (m.get("sortField") == null) ? "" :
						 * m.get("sortField")); m.set("dir", (m.get("sortDir")
						 * == null || (m.get("sortDir") != null && m.<SortDir>
						 * get( "sortDir").equals(SortDir.NONE))) ? "" :
						 * m.get("sortDir"));
						 * 
						 * } });
						 */
						catalogue.getLoader().setSortDir(SortDir.DESC);
						catalogue.getLoader().setSortField("dateModified");
						catalogue.getLoader().setRemoteSort(true);

						catalogue.getStore().add(getCommunicationResourceModel(result));
						// catalogue.setStore(new
						// ListStore<ResourceChildModel>(catalogue.getLoader()));

						resourceExplorer.getPagingToolBar().bind(catalogue.getLoader());
						resourceExplorer.setPagingToolBar(resourceExplorer.getPagingToolBar());

						resourceExplorer.getPagingToolBar().refresh();

						table.addListener(Events.Attach, new Listener<GridEvent<ResourceChildModel>>() {
							public void handleEvent(GridEvent<ResourceChildModel> be) {
								PagingLoadConfig config = new BasePagingLoadConfig();
								config.setOffset(0);
								config.setLimit(50);

								Map<String, Object> state = table.getState();
								if (state.containsKey("offset")) {
									int offset = (Integer) state.get("offset");
									int limit = (Integer) state.get("limit");
									config.setOffset(offset);
									config.setLimit(limit);
								}
								if (state.containsKey("sortField")) {
									config.setSortField((String) state.get("sortField"));
									config.setSortDir(SortDir.valueOf((String) state.get("sortDir")));
								}
								catalogue.getLoader().load(config);
								// catalogue.getLoader().load(0, 500);
							}

						});

						table.setContextMenu(new CatalogueContextMenuCommunication(resourceExplorer, catalogue).buildMenu());

						resourceExplorer.getWindow().getLayout().layout();
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "Communication Module Search");
					}
				});
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "Communication Resources Size.");
			}
		});
	}

	public static void fillGridNodeScope(final ResourceExplorer resourceExplorer, final Grid<ResourceChildModel> table, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final Catalogue catalogue) {
//		System.out.println(">>>>>>>>>> SYSTEM ENTERED fillGridNodeScope @ fillGrid");

		REServiceEntry.getInstance().getSearchResultListLength(fenixSearchParameters, new AsyncCallback<Integer>() {

			public void onSuccess(Integer result) {
				// resourceExplorer.getWindow().getLayout().layout();
				
				cataloguePager.setFenixPager(new FenixPager(result, NumberPages.NUM_PAGES));
				cataloguePager.writeInfo();
				cataloguePager.validateButtons();
				int to;
				if (result >= NumberPages.NUM_PAGES)
					to = NumberPages.NUM_PAGES;
				else
					to = result;
				

				/*
				 * if (resourceExplorer instanceof ResourceExplorerSearch) {
				 * List<String> type =
				 * fenixSearchParameters.getResourceTypeList();
				 * if(!type.get(0).equals(ResourceType.DATASET)) {
				 * System.out.println
				 * ("--------------- type.get(0) = "+type.get(0) + " HIDE");
				 * table
				 * .getColumnModel().getColumnById("periodTypeCode").setHidden
				 * (true); } }
				 */

				REServiceEntry.getInstance().search(fenixSearchParameters, cataloguePager.getFenixPager().calculateIndexFrom(), cataloguePager.getFenixPager().calculateIndexTo(), new AsyncCallback<List<FenixResourceVo>>() {
					public void onSuccess(List<FenixResourceVo> result) {
						

						

						catalogue.getStore().removeAll();
						PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(getResourceModel(result));

						// loader
						catalogue.setLoader(new BasePagingLoader<PagingLoadResult<ResourceChildModel>>(proxy));

						/*
						 * catalogue.getLoader().addListener(Loader.BeforeLoad,
						 * new Listener<LoadEvent>() { public void
						 * handleEvent(LoadEvent be) { BasePagingLoadConfig m =
						 * be.<BasePagingLoadConfig> getConfig(); m.set("start",
						 * m.get("offset")); m.set("ext", "js");
						 * m.set("lightWeight", true); m.set("sort",
						 * (m.get("sortField") == null) ? "" :
						 * m.get("sortField")); m.set("dir", (m.get("sortDir")
						 * == null || (m.get("sortDir") != null && m.<SortDir>
						 * get( "sortDir").equals(SortDir.NONE))) ? "" :
						 * m.get("sortDir"));
						 * 
						 * } });
						 */
						catalogue.getLoader().setSortDir(SortDir.DESC);
						catalogue.getLoader().setSortField("dateModified");
						catalogue.getLoader().setRemoteSort(true);

						catalogue.getStore().add(getResourceModel(result));
						// catalogue.setStore(new
						// ListStore<ResourceChildModel>(catalogue.getLoader()));

						// catalogue.setGrid(catalogue.getStore(),
						// catalogue.getColumnModel());

						resourceExplorer.getPagingToolBar().bind(catalogue.getLoader());
						resourceExplorer.setPagingToolBar(resourceExplorer.getPagingToolBar());

						resourceExplorer.getPagingToolBar().refresh();

						table.addListener(Events.Attach, new Listener<GridEvent<ResourceChildModel>>() {
							public void handleEvent(GridEvent<ResourceChildModel> be) {
								PagingLoadConfig config = new BasePagingLoadConfig();
								config.setOffset(0);
								config.setLimit(50);

								Map<String, Object> state = table.getState();
								if (state.containsKey("offset")) {
									int offset = (Integer) state.get("offset");
									int limit = (Integer) state.get("limit");
									config.setOffset(offset);
									config.setLimit(limit);
								}
								if (state.containsKey("sortField")) {
									config.setSortField((String) state.get("sortField"));
									config.setSortDir(SortDir.valueOf((String) state.get("sortDir")));
								}
								catalogue.getLoader().load(config);
								// catalogue.getLoader().load(0, 500);
							}

						});

						// resourceExplorer.getWindow().getLayout().layout();
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failed", "search");
					}
				});
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "getSearchResultListLength");
			}
		});
	}

	public static List<ResourceChildModel> getResourceModel(List<FenixResourceVo> result) {
//		System.out.println(result.size() + " RESOURCES FOUND____________________________________________________");
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		for (int i = 0; i < result.size(); i++) {

			FenixResourceVo resource = (FenixResourceVo) result.get(i);
			String region = resource.getRegion();
			ResourceChildModel model = new ResourceChildModel(resource.getTitle());
			model.set("dateModified", FieldParser.parseLastModifiedDate(resource.getLastUpdated()));

			if (countryList.getGAULCountryLabel(region) != null)
				model.set("region", countryList.getGAULCountryLabel(region));
			else
				model.set("region", resource.getRegion());

			model.set("regionCode", resource.getRegion());

			/** MODIFIED **/
			CategoryList c = new CategoryList();
			c.getCategoryResourceChildModel(resource.getCategory(), model);
			// model.set("category", new
			// CategoryList().getCategoryLabel(resource.getCategory()));

			model.setId(resource.getId()); // set ID
			model.setType(resource.getType()); // set Resource type

			model.setSource(resource.getSource()); // set Source
			if (resource.getPeriodTypeCode() != null) // set Period Type Code
				model.setPeriodTypeCode(resource.getPeriodTypeCode());
			// else model.setPeriodTypeCode("n.a.");
			// GWT.log("CatalogueController@140 | periodTypeCode: " +
			// resource.getPeriodTypeCode(), null);

			model.setReadPermission(true);
			model.setWritePermission(resource.isHasWritePermission());
			model.setDeletePermission(resource.isHasDeletePermission());
			model.setDownloadPermission(resource.isHasDownloadPermission());
			model.setFlexDatasetType(resource.isDatasetFlex());
			model.setFenixResourceVo(resource);
			model.setLocalId(resource.getLocalID());

			// FIXME look FenixResourceVo about security
			// model.setResourceIsModifiable(Boolean.toString(resource.isModifiable()));
			models.add(model);
		}
		return models;
	}

	public static List<ResourceChildModel> getCommunicationResourceModel(List<CommunicationResourceVo> result) {
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		for (int i = 0; i < result.size(); i++) {
			CommunicationResourceVo resource = result.get(i);
			CommunicationResourceChildModel model = new CommunicationResourceChildModel(resource.getTitle());
			model.set("dateModified", "");
			model.set("region", resource.getOGroup());
			model.set("category", "");
			model.set("periodTypeCode", "");
			model.setId(String.valueOf(resource.getId())); // set ID
			model.setType(resource.getType()); // set Resource type
			model.setDigest(resource.getDigest());
			model.setHost(resource.getHost());
			model.setHostLabel(resource.getHostLabel());
			model.setLocalId(resource.getLocalId());
			models.add(model);
		}
		return models;
	}

	public static void fillGridUpdate(final Grid<ResourceChildModel> table, final ListStore<ResourceChildModel> store, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final int from, final int to) {
		if (fenixSearchParameters.getScope().equals(BabelFish.print().scopeNode()))
			fillGridUpdateNode(table, store, fenixSearchParameters, cataloguePager, from, to);
		else
			fillGridUpdateNetwork(table, store, fenixSearchParameters, cataloguePager, from, to);
	}

	public static void fillGridUpdateNode(final Grid<ResourceChildModel> table, final ListStore<ResourceChildModel> store, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final int from, final int to) {
		cataloguePager.writeInfo();
		cataloguePager.validateButtons();
		REServiceEntry.getInstance().search(fenixSearchParameters, from, to, new AsyncCallback<List<FenixResourceVo>>() {
			public void onSuccess(List<FenixResourceVo> result) {
				// table.removeAll();
				store.removeAll();
				System.out.println("-------------- search: result size = " + result.size());
				store.add(getResourceModel(result));
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "search");
			}
		});
	}

	public static void fillGridUpdateNetwork(final Grid<ResourceChildModel> table, final ListStore<ResourceChildModel> store, final FenixSearchParameters fenixSearchParameters, final CataloguePager cataloguePager, final int from, final int to) {
		cataloguePager.writeInfo();
		cataloguePager.validateButtons();
		REServiceEntry.getInstance().searchCommunication(fenixSearchParameters, from, to, new AsyncCallback<List<CommunicationResourceVo>>() {
			public void onSuccess(List<CommunicationResourceVo> result) {
				// table.removeAll();
				store.removeAll();
				store.add(getCommunicationResourceModel(result));
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "search");
			}
		});
	}

}
