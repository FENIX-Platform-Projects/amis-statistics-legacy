package org.fao.fenix.web.modules.olap.client.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.view.OlapMapWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class OlapMapController extends OlapController {

	public static SelectionListener<ButtonEvent> selectFilters(final OlapMapWindow w, final OLAPParametersVo p, final OlapWindow olapWindow) {
		
		return new SelectionListener<ButtonEvent>() {
			
			public void componentSelected(ButtonEvent ce) {
				
				String geoLabel = w.getGeoDropDown().getItemText(w.getGeoDropDown().getSelectedIndex());
				
				if (!p.getXLabel().equals(geoLabel)) {
					ListBox xList = olapWindow.getTabPanel().getSingleDatasetPanel().getXAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0 ; i < xList.getItemCount() ; i++) 
						if (xList.isItemSelected(i)) 
							map.put(xList.getValue(i), xList.getItemText(i));
					w.addFilterDropDowns(p, map, w.getxDropDown(), "x");
				}
				
				if (!p.getZLabel().equals(geoLabel)) {
					ListBox zList = olapWindow.getTabPanel().getSingleDatasetPanel().getZAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0 ; i < zList.getItemCount() ; i++)
						if (zList.isItemSelected(i))
							map.put(zList.getValue(i), zList.getItemText(i));
					w.addFilterDropDowns(p, map, w.getzDropDown(), "z");
				}
				
				if ((!p.getYLabels().isEmpty()) && (!p.getYLabel().equals(geoLabel))) {
					ListBox yList = olapWindow.getTabPanel().getSingleDatasetPanel().getYAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0 ; i < yList.getItemCount() ; i++)
						if (yList.isItemSelected(i))
							map.put(yList.getValue(i), yList.getItemText(i));
					w.addFilterDropDowns(p, map, w.getyDropDown(), "y");
				}
				
				if ((!p.getWLabels().isEmpty()) && (!p.getWLabel().equals(geoLabel))) {
					ListBox wList = olapWindow.getTabPanel().getSingleDatasetPanel().getWAxis().getValues();
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0 ; i < wList.getItemCount() ; i++)
						if (wList.isItemSelected(i))
							map.put(wList.getValue(i), wList.getItemText(i));
					w.addFilterDropDowns(p, map, w.getwDropDown(), "w");
				}
				
				HorizontalPanel buttonsPanel = w.buildButtonsPanel();
				w.getWrapper().add(buttonsPanel);
				w.getSelectFilters().setEnabled(false);
				w.getGeoDropDown().setEnabled(false);
				
//				ListBox intervalsList = olapWindow.getTabPanel().getSingleDatasetPanel().getIntervals();
//				int intervals = 1 + Integer.parseInt(intervalsList.getItemText(intervalsList.getSelectedIndex()));
				
				ListBox minColorList = olapWindow.getTabPanel().getFsatmisDatasetPanel().getMinColor();
				String minColor = minColorList.getValue(minColorList.getSelectedIndex());
				
				ListBox maxColorList = olapWindow.getTabPanel().getFsatmisDatasetPanel().getMaxColor();
				String maxColor = maxColorList.getValue(maxColorList.getSelectedIndex());
				
				boolean showBaseLayer = olapWindow.getTabPanel().getFsatmisDatasetPanel().getShowBaseLayer().getValue();
				
				Map<String, String> filters = getMapFilters(olapWindow);
				
//				w.getCreateMapButton().addSelectionListener(OlapMapController.createOlapMap(p, w.getGeoDropDown(), filters, olapWindow.getHtml().toString(), intervals, minColor, maxColor, showBaseLayer));
				w.getCreateMapButton().addSelectionListener(OlapMapController.createOlapMap(p, w.getGeoDropDown(), filters, olapWindow.getHtml().toString(), 0, minColor, maxColor, showBaseLayer));
				w.getWrapper().getLayout().layout();
			};
		};
	}
	
	public static SelectionListener<IconButtonEvent> createMap(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				OLAPParametersVo p = retrieveParameters(window);
				window.getOlapMapWindow().build(p, window);
			};
		};
	}	
	
	private static Map<String, String> getMapFilters(OlapWindow olapTool) {
		
		Map<String, String> filters = new HashMap<String, String>();
		
		ListBox xList = olapTool.getTabPanel().getFsatmisDatasetPanel().getXAxis().getValues();
		if (xList.getSelectedIndex() > 0) {
			String xCode = xList.getValue(xList.getSelectedIndex());
			filters.put("x", xCode);
		}
		
		ListBox zList = olapTool.getTabPanel().getFsatmisDatasetPanel().getZAxis().getValues();
		if (zList.getSelectedIndex() > 0) {
			String zCode = zList.getValue(zList.getSelectedIndex());
			filters.put("z", zCode);
		}
		
		ListBox yList = olapTool.getTabPanel().getFsatmisDatasetPanel().getYAxis().getValues();
		if (yList.getSelectedIndex() > 0) {
			String yCode = yList.getValue(yList.getSelectedIndex());
			filters.put("y", yCode);
		}
		
		ListBox wList = olapTool.getTabPanel().getFsatmisDatasetPanel().getWAxis().getValues();
		if (wList.getSelectedIndex() > 0) {
			String wCode = wList.getValue(wList.getSelectedIndex());
			filters.put("w", wCode);
		}
		
		return filters;
	}
	
	/**
	 * Create a map with the data projected on the OLAP table as an image, with GAUL0, GAUL1 and a Base Layer added on.
	 * 
	 * @param p OLAP parameters retrieved from the interface.
	 * @param geoDropDown Drop down containing the geographic dimension selected by the user.
	 * @return Listener for the "Create Map button"
	 */
	public static SelectionListener<ButtonEvent> createOlapMap(final OLAPParametersVo p, final ListBox geoDropDown, final Map<String, String> filters, final String html, final int intervals, final String minColor, final String maxColor, final boolean showBaseLayer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loading = new LoadingWindow(BabelFish.print().createMap(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
				final String geoDataType = geoDropDown.getValue(geoDropDown.getSelectedIndex());
				final String geoLabel = geoDropDown.getItemText(geoDropDown.getSelectedIndex());
				final String mapTitle = p.getDataSourceTitle() + " (" + p.getFunction() + ")";
				OlapServiceEntry.getInstance().createOLAPMap(p, geoDataType, geoLabel, filters, html, intervals, minColor, maxColor, showBaseLayer, new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> mapPaths) {
						for (String s : mapPaths)
							Window.open("../olapMaps/" + s, mapTitle, "status=no");
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
	
	public static ChangeListener alterOlapMapParameters(final OLAPParametersVo p, final String dimensionToAlter) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox list = (ListBox)sender;
				if (list.getSelectedIndex() > 0) {
					String code = list.getValue(list.getSelectedIndex());
					String label = list.getItemText(list.getSelectedIndex());
					Map<String, String> map = new HashMap<String, String>();
					map.put(code, label);
					if (dimensionToAlter.equalsIgnoreCase("x")) {
						p.setXLabels(map);
					} else if (dimensionToAlter.equalsIgnoreCase("z")) {
						p.setZLabels(map);
					} else if (dimensionToAlter.equalsIgnoreCase("y")) {
						p.setYLabels(map);
					} else if (dimensionToAlter.equalsIgnoreCase("w")) {
						p.setWLabels(map);
					}
				}
			}
		};
	}
	
	public static void selectFlexGeoDimension(Long datasetId, final ListBox geoDropDown) {
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().multidimensionalTableTool(), BabelFish.print().pleaseWait() + "...", BabelFish.print().loading() + "...");
		OlapServiceEntry.getInstance().findGeoColumnName(datasetId, new AsyncCallback<String>() {
			public void onSuccess(String header) {
				for (int i = 0 ; i < geoDropDown.getItemCount() ; i++)
					if (geoDropDown.getItemText(i).equalsIgnoreCase(header))
						geoDropDown.setSelectedIndex(i);
				loading.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				loading.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				loading.destroyLoadingBox();
			}
		});
	}
	
}