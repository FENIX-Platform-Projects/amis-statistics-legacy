package org.fao.fenix.web.modules.olap.client.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISMapPanel;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTablePanel;
import org.fao.fenix.web.modules.fsatmis.common.vo.FSATMISModelData;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.view.OlapTool;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TabPanelEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class FSATMISToolbarController extends OlapToolController {

	public static SelectionListener<ButtonEvent> selectFilters(final FSATMISMapPanel mapPanel, final OLAPParametersVo p, final OlapTool olapTool, final FSATMISTabPanel fsatmisTabPanel) {

		return new SelectionListener<ButtonEvent>() {

			public void componentSelected(ButtonEvent ce) {

				String geoLabel = mapPanel.getGeoDropDown().getItemText(mapPanel.getGeoDropDown().getSelectedIndex());

				if (!p.getXLabel().equals(geoLabel)) {
					ListBox xList = olapTool.getTabPanel().getFsatmisDatasetPanel().getXAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < xList.getItemCount(); i++)
						if (xList.isItemSelected(i))
							map.put(xList.getValue(i), xList.getItemText(i));
					mapPanel.addFilterDropDowns(p, map, mapPanel.getxDropDown(), "x");
				}

				if (!p.getZLabel().equals(geoLabel)) {
					ListBox zList = olapTool.getTabPanel().getFsatmisDatasetPanel().getZAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < zList.getItemCount(); i++)
						if (zList.isItemSelected(i))
							map.put(zList.getValue(i), zList.getItemText(i));
					mapPanel.addFilterDropDowns(p, map, mapPanel.getzDropDown(), "z");
				}

				if ((!p.getYLabels().isEmpty()) && (!p.getYLabel().equals(geoLabel))) {
					ListBox yList = olapTool.getTabPanel().getFsatmisDatasetPanel().getYAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < yList.getItemCount(); i++)
						if (yList.isItemSelected(i))
							map.put(yList.getValue(i), yList.getItemText(i));
					mapPanel.addFilterDropDowns(p, map, mapPanel.getyDropDown(), "y");
				}

				if ((!p.getWLabels().isEmpty()) && (!p.getWLabel().equals(geoLabel))) {
					ListBox wList = olapTool.getTabPanel().getFsatmisDatasetPanel().getWAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < wList.getItemCount(); i++)
						if (wList.isItemSelected(i))
							map.put(wList.getValue(i), wList.getItemText(i));
					mapPanel.addFilterDropDowns(p, map, mapPanel.getwDropDown(), "w");
				}

				HorizontalPanel buttonsPanel = mapPanel.buildButtonsPanel();
				mapPanel.getWrapper().add(buttonsPanel);
				mapPanel.getSelectFilters().setEnabled(false);
				mapPanel.getGeoDropDown().setEnabled(false);
				
				ListBox intervalsList = olapTool.getTabPanel().getFsatmisDatasetPanel().getIntervals();
				int intervals = 1 + Integer.parseInt(intervalsList.getItemText(intervalsList.getSelectedIndex()));
				
				ListBox minColorList = olapTool.getTabPanel().getFsatmisDatasetPanel().getMinColor();
				String minColor = minColorList.getValue(minColorList.getSelectedIndex());
				
				ListBox maxColorList = olapTool.getTabPanel().getFsatmisDatasetPanel().getMaxColor();
				String maxColor = maxColorList.getValue(maxColorList.getSelectedIndex());
				
				boolean showBaseLayer = olapTool.getTabPanel().getFsatmisDatasetPanel().getShowBaseLayer().getValue();
				
				mapPanel.getCreateMapButton().addSelectionListener(createOlapMap(p, olapTool.getHtml().toString(), fsatmisTabPanel, mapPanel, intervals, minColor, maxColor, showBaseLayer));
				mapPanel.getWrapper().getLayout().layout();
			};
		};
	}
	
	private static Map<String, String> getMapFilters(OLAPParametersVo p, ListBox geoDropDown, ListBox xList, ListBox zList, ListBox yList, ListBox wList) {
		
		String geoDataType = geoDropDown.getValue(geoDropDown.getSelectedIndex());
		String geoLabel = geoDropDown.getItemText(geoDropDown.getSelectedIndex());
		
		String featureCode = "x";
		if (p.getZ().equals(geoDataType) && p.getZLabel().equals(geoLabel))
			featureCode = "z";
		else if (!p.getYLabels().isEmpty() && p.getY().equals(geoDataType) && p.getYLabel().equals(geoLabel))
			featureCode = "y";
		else if (!p.getWLabels().isEmpty() && p.getW().equals(geoDataType) && p.getWLabel().equals(geoLabel))
			featureCode = "w";
//		System.out.println("[FSATMISToolbarController] | Feature Code: " + featureCode);
		
		Map<String, String> filters = new HashMap<String, String>(); // TODO exclude geo-dimension
		
		if (!featureCode.equalsIgnoreCase("x")) {
			if (xList.getSelectedIndex() > 0) {
				String xCode = xList.getValue(xList.getSelectedIndex());
//				String xCode = xList.getItemText(xList.getSelectedIndex());
				filters.put("x", xCode);
			}
		}
		
		if (!featureCode.equalsIgnoreCase("z")) {
			if (zList.getSelectedIndex() > 0) {
				String zCode = zList.getValue(zList.getSelectedIndex());
//				String zCode = zList.getItemText(zList.getSelectedIndex());
				filters.put("z", zCode);
			}
		}
		
		if (!featureCode.equalsIgnoreCase("y")) {
			if (yList.getSelectedIndex() > 0) {
//				String yCode = yList.getValue(yList.getSelectedIndex());
				String yCode = yList.getItemText(yList.getSelectedIndex());
				filters.put("y", yCode);
			}
		}
		
		if (!featureCode.equalsIgnoreCase("w")) {
			if (wList.getSelectedIndex() > 0) {
				String wCode = wList.getValue(wList.getSelectedIndex());
//				String wCode = wList.getItemText(wList.getSelectedIndex());
				filters.put("w", wCode);
			}
		}
		
//		for (String f : filters.keySet())
//			System.out.println("[FSATMISToolbarController]_____________" + f + ": " + filters.get(f));
		
		return filters;
	}

	public static SelectionListener<ButtonEvent> createOlapMap(final OLAPParametersVo p, final String html, final FSATMISTabPanel fsatmisTabPanel, final FSATMISMapPanel mapPanel, final int intervals, final String minColor, final String maxColor, final boolean showBaseLayer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
				final String geoDataType = mapPanel.getGeoDropDown().getValue(mapPanel.getGeoDropDown().getSelectedIndex());
				final String geoLabel = mapPanel.getGeoDropDown().getItemText(mapPanel.getGeoDropDown().getSelectedIndex());
				
				Map<String, String> filters = getMapFilters(p, mapPanel.getGeoDropDown(), mapPanel.getxDropDown(), mapPanel.getzDropDown(), mapPanel.getyDropDown(), mapPanel.getwDropDown());
				
				OlapServiceEntry.getInstance().createOLAPMap(p, geoDataType, geoLabel, filters, html, intervals, minColor, maxColor, showBaseLayer, new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> mapPaths) {
						TabItem mapTabItem = fsatmisTabPanel.getMapTabItem();
						for (int i = mapTabItem.getItemCount() - 1; i >= 0; i--)
							mapTabItem.remove(mapTabItem.getWidget(i));
						for (String s : mapPaths) {
							Frame frame = new Frame("../olapMaps/" + s);
							mapTabItem.add(frame);
							frame.setSize("800px", "900px");
							mapTabItem.getLayout().layout();
						}
						loading.destroyLoadingBox();
					}

					public void onFailure(Throwable e) {
						loading.destroyLoadingBox();
						FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
						loading.destroyLoadingBox();
					}
				});
			}
		};
	}

	public static SelectionListener<IconButtonEvent> selectGeoDimension(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				selectGeoDimensionFunction(tool, tabPanel);
			};
		};
	}

	public static SelectionListener<TabPanelEvent> selectGeoDimensionFromTab(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		return new SelectionListener<TabPanelEvent>() {
			public void componentSelected(TabPanelEvent ce) {
				selectGeoDimensionFunction(tool, tabPanel);
			};
		};
	}

	private static void selectGeoDimensionFunction(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		TabItem mapTabItem = tabPanel.getMapTabItem();
		for (int i = mapTabItem.getItemCount() - 1; i >= 0; i--)
			mapTabItem.remove(mapTabItem.getWidget(i));
		OLAPParametersVo p = retrieveParameters(tool);
		FSATMISMapPanel mapPanel = new FSATMISMapPanel();
		mapPanel.build(p, tool, tabPanel);
		mapTabItem.add(mapPanel.getToolBase());
		tabPanel.getTabPanel().setSelection(tabPanel.getMapTabItem());
		mapTabItem.getLayout().layout();
	}
	

	public static SelectionListener<IconButtonEvent> createChartFromIcon(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				TabItem chartTabItem = tabPanel.getChartTabItem();
				chartTabItem.removeAllListeners();
				chartTabItem.removeAll();
				if (tool.getTabPanel().getFsatmisDatasetPanel().getUseFlashCharts().getValue()) 	
					createOFCChartToolBarIcon(tool, tabPanel);
				
				else 
					createOldChart(tool, tabPanel);				
			};
		};
	}
	
	public static SelectionListener<TabPanelEvent> createChartFromTab(final OlapTool tool, final FSATMISTabPanel tabPanel, final TabItem chartTabItem) {
		return new SelectionListener<TabPanelEvent>() {
			public void componentSelected(TabPanelEvent ce) {
				TabItem chartTabItem = tabPanel.getChartTabItem();
				chartTabItem.removeAllListeners();
				chartTabItem.removeAll();
				if (tool.getTabPanel().getFsatmisDatasetPanel().getUseFlashCharts().getValue()) 
					FSATMISOFCChartUtils.createOFCChartFunction(tool, tabPanel, chartTabItem);			
				else 
					createOldChartFromTab(tool, tabPanel);			
			};
		};
	}

	public static void createOldChart(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		createChartFunction(tool, tabPanel);	
	}

	public static void createOldChartFromTab(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		createChartFunction(tool, tabPanel);
	}

	private static void createChartFunction(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		final OLAPParametersVo p = retrieveParameters(tool);
		createOLAPChartBeans(p, tool, tabPanel);
	}

	public static SelectionListener<IconButtonEvent> showRawData(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				showRawDataFunction(tool, tabPanel, tabPanel.getTablePanel());
				tabPanel.getTabPanel().setSelection(tabPanel.getTableTabItem());
			};
		};
	}

	public static SelectionListener<TabPanelEvent> showRawDataFromTab(final OlapTool tool, final FSATMISTabPanel tabPanel, final FSATMISTablePanel tablePanel) {
		return new SelectionListener<TabPanelEvent>() {
			public void componentSelected(TabPanelEvent ce) {
				showRawDataFunction(tool, tabPanel, tablePanel);
			};
		};
	}

	private static void showRawDataFunction(final OlapTool tool, final FSATMISTabPanel tabPanel, final FSATMISTablePanel tablePanel) {
		ListBox dataSource = tool.getTabPanel().getFsatmisDatasetPanel().getDataSource();
		Long datasetId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
		createFSATMISTable(datasetId, tablePanel);
	}

	public static void refreshRawData(final OlapTool tool, final FSATMISTabPanel tabPanel, final FSATMISTablePanel tablePanel, final List<UDTableFilterVO> filters) {

		ListBox dataSource = tool.getTabPanel().getFsatmisDatasetPanel().getDataSource();
		Long datasetId = Long.valueOf(dataSource.getValue(dataSource.getSelectedIndex()));
		createFSATMISTable(datasetId, tablePanel);

	}

	public static void createFSATMISTable(final Long datasetId, final FSATMISTablePanel tablePanel) {

		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();

		OlapServiceEntry.getInstance().getFSATMISModelData(datasetId, new AsyncCallback<List<FSATMISModelData>>() {
			public void onSuccess(List<FSATMISModelData> result) {

				tablePanel.getListStore().removeAll();

				//paging proxy
				PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(result);  

				// loader  
				final PagingLoader<PagingLoadResult<FSATMISModelData>> loader = new BasePagingLoader<PagingLoadResult<FSATMISModelData>>(proxy);  
				loader.setRemoteSort(true);

				loader.addLoadListener(new LoadListener() {
					public void loaderLoad(LoadEvent le) {
						loadingWindow.destroyLoadingBox();
					}

					public void loaderLoadException(LoadEvent le) {
						loadingWindow.destroyLoadingBox();
					}
				});

				//set the store
				final ListStore<FSATMISModelData> store = new ListStore<FSATMISModelData>(loader);
				tablePanel.setListStore(store);

				//bind loader to the pager
				tablePanel.getPagingToolBar().bind(loader);
				tablePanel.setPagingToolBar(tablePanel.getPagingToolBar());

				//refresh grid and pager
				tablePanel.getPagingToolBar().refresh();
				tablePanel.getGrid().reconfigure(store, tablePanel.getColumnModel());


				//System.out.println("*********************  store count = "+store.getCount());
				//System.out.println("*********************  list store count = "+tablePanel.getListStore().getCount());


				try {
					tablePanel.getPanel().getLayout().layout();
				} catch (NullPointerException e) {
					GWT.log("It's not possible to layout()", new Throwable());
				}

			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed: createFSATMISTable", "getFSATMISModelData");
			}
		});
	}


	private static void createOLAPChartBeans(final OLAPParametersVo p, final OlapTool tool, final FSATMISTabPanel tabPanel) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().chart(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");

		OlapServiceEntry.getInstance().createOlapCharts(p, tool.getHtml().toString(), new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> paths) {
				TabItem chartTabItem = tabPanel.getChartTabItem();
				for (int i = chartTabItem.getItemCount() - 1; i >= 0; i--)
					chartTabItem.remove(chartTabItem.getWidget(i));
				loading.destroyLoadingBox();

				chartTabItem.removeAll();
				HorizontalPanel tipPanel = new HorizontalPanel();
				tipPanel.setSpacing(10);
				tipPanel.add(new HTML("<i><b>Right-click on the chart to save it as image</b></i>"));
				chartTabItem.add(tipPanel);

				for (String s : paths) {
					Frame frame = new Frame("../olapCharts/" + s);
					chartTabItem.add(frame);
					frame.setSize("800px", "550px");
				}
				tabPanel.getTabPanel().setSelection(tabPanel.getChartTabItem());
				chartTabItem.getLayout().layout();
				chartTabItem.addListener(Events.Select, FSATMISToolbarController.createChartFromTab(tool, tabPanel, chartTabItem));
				loading.destroyLoadingBox();
			}

			public void onFailure(Throwable e) {
				loading.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				loading.destroyLoadingBox();
			};
		});

	}
	
	public static SelectionListener<IconButtonEvent> openInProjectManager(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ListBox dataSource = tool.getTabPanel().getFsatmisDatasetPanel().getDataSource();
				String datasetId = dataSource.getValue(dataSource.getSelectedIndex());
				String hostPageURL = GWT.getHostPageBaseURL();
				int index = hostPageURL.indexOf("fenix-web") + "fenix-web".length();
				String url = hostPageURL.substring(0, index) + "/fenix/Fenix.html?openFSATMISDataset=" + datasetId;
				Window.open(url, "_blank", "status=no");
			};
		};
	}
	
	
	public static void createOFCChartToolBarIcon(final OlapTool tool, final FSATMISTabPanel tabPanel) {
		TabItem chartTabItem = tabPanel.getChartTabItem();
		FSATMISOFCChartUtils.createOFCChartFunction(tool, tabPanel, chartTabItem);		
	}		
	
}
