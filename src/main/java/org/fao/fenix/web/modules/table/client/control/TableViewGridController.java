package org.fao.fenix.web.modules.table.client.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableViewGrid;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableFilterVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TableViewGridController {
	
	 static String datasetPrecision = "2";
		

	public static void build(final TableViewGrid tableGrid, final Long resourceId, final Map<String, List<String>> filterCriteria, final TableVO tableVO) {
		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();
		Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
		Iterator<Map.Entry<String, DimensionBeanVO>> iterator = dimensionDetailsMap.entrySet().iterator();
		tableGrid.getStore().removeAll();
		final List<String> columnNames = new ArrayList<String>();
		
//		if (tableGrid.getReadOnlyConfigs().size() > 0)
//			tableGrid.getReadOnlyConfigs().removeAll(tableGrid.getReadOnlyConfigs());
		
		columnNames.add("ID");
		tableGrid.getReadOnlyConfigs().add(tableGrid.getRowIdColumnConfiguration());
		for (int i = 0; i < dimensionDetailsMap.size(); i++) {
			Map.Entry<String, DimensionBeanVO> entry = iterator.next();
			String columnId = entry.getKey();
			DimensionBeanVO dimensionVO = entry.getValue();
			String columnName = dimensionVO.getHeader();
			columnNames.add(columnName);
			ColumnConfig columnConfig = new ColumnConfig();
			columnConfig.setId(columnId);
			columnConfig.setHeader(columnName);
			columnConfig.setWidth(100);
			tableGrid.getReadOnlyConfigs().add(columnConfig);
		}
		tableGrid.setReadOnlyConfigs(tableGrid.getReadOnlyConfigs());
		final RpcProxy proxy = new RpcProxy() {
			@Override
			public void load(Object loadConfig, AsyncCallback callback) {
				tableGrid.getStore().removeAll();
				TableServiceEntry.getInstance().getFilteredData(datasetPrecision, (PagingLoadConfig) loadConfig, resourceId, columnNames, filterCriteria, tableGrid.caller, CheckLanguage.getLanguage(), callback);
			}
		};
		final BasePagingLoader pagingLoader = new BasePagingLoader(proxy);
		pagingLoader.setRemoteSort(true);
		pagingLoader.load(0, TableConstants.PAGE_SIZE);
		pagingLoader.addLoadListener(new LoadListener() {
			public void loaderLoad(LoadEvent le) {
				loadingWindow.destroyLoadingBox();
				tableGrid.getTableWindow().getWest().collapse();
			}

			public void loaderLoadException(LoadEvent le) {
				loadingWindow.destroyLoadingBox();
			}
		});
		final ListStore<DatasetRowModel> store = new ListStore<DatasetRowModel>(pagingLoader);
		tableGrid.setStore(store);
		tableGrid.getTableWindow().getTablePager().bind(pagingLoader);
		tableGrid.setEditorGrid(tableGrid.getStore(), new ColumnModel(tableGrid.getReadOnlyConfigs()), resourceId);
		tableGrid.getTableWindow().getCenter().add(tableGrid.getEditorGrid());
		if (tableGrid.caller != null) {
			tableGrid.getTableWindow().getTablePager().recalculate(); // recalculate
		}
		tableGrid.getTableWindow().addCenterPartToWindow();
		
//		if (tableGrid.getTableWindow().getWest().isExpanded()) {
//			tableGrid.getTableWindow().getWest().collapse();
//		} else {
//			tableGrid.getTableWindow().getWest().expand();
//		}
		
		tableGrid.caller = null;
		if (tableGrid.getTableWindow().getTableFilters().addToReportButton != null)
			tableGrid.getTableWindow().getTableFilters().addToReportButton.enable();
	}

}