package org.fao.fenix.web.modules.dataviewer.client.control.faostat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FAOSTATMainController {
	
	public static FAOSTATCurrentState currentState;
		
	
	public static SelectionListener<MenuEvent> exportChartExcel(final DWFAOSTATResultVO vo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				exportChartExcelAgent(vo);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> exportChartExcelIconButton(final DWFAOSTATResultVO vo) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				exportChartExcelAgent(vo);
			}
		};
	}
	
	private static void exportChartExcelAgent(final DWFAOSTATResultVO vo) {
		List<List<String>> table = new ArrayList<List<String>>();
		LinkedHashMap<String, Map<String, Double>> chartValues = vo.getChartValues();
		
//		System.out.println("chartValues" + chartValues);
		
		// TODO: quick fix for the stack (DO A SERIE switcher)
//		System.out.println("vo.getOutputType(): " + vo.getOutputType());

		
//		System.out.println("vo.getOutputType(): " + vo.getOutputType());
		
		System.out.println("COUNT: " + chartValues.size());
		
		System.out.println("HERE");
		for(String key : chartValues.keySet()) {
			for(String chartKey : chartValues.get(key).keySet()) {
				List<String> row = new ArrayList<String>();
				row.add(key);
				row.add(chartKey);
				row.add(chartValues.get(key).get(chartKey).toString());
				table.add(row);
			}
		}

		vo.setTableHeaders(new ArrayList<String>());
		vo.setTableContents(table);

		DataViewerServiceEntry.getInstance().export(vo, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}

			public void onFailure(Throwable arg0) {

			}
		});
	}
	
	public static SelectionListener<MenuEvent> exportTableExcel(final DWFAOSTATResultVO vo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				exportTableExcelAgent(vo);
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> exportTableExcelIconButton(final DWFAOSTATResultVO vo) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				exportTableExcelAgent(vo);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> exportTableExcelButton(final DWFAOSTATResultVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				exportTableExcelAgent(vo);
			}
		};
	}
	
	private static void exportTableExcelAgent(final DWFAOSTATResultVO vo) {
		DataViewerServiceEntry.getInstance().export(vo, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}

			public void onFailure(Throwable arg0) {

			}
		});
	}
	
	public static SelectionListener<ButtonEvent> selectAll(final ListView<DWCodesModelData> list) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				selectAllAgent(list);
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> deselectAll(final ContentPanel selectedCodes, final Grid<DWCodesModelData> grid) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				deselectAllAgent(selectedCodes, grid);
			}
		};
	}
	
	public static void deselectAllAgent(ContentPanel selectedCodes, Grid<DWCodesModelData> grid) {
		grid.getSelectionModel().deselectAll();
		selectedCodes.removeAll();
	}

	
	public static SelectionListener<ButtonEvent> deselectAll(final ListView<DWCodesModelData> list) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				deselectAllAgent(list);
			}
		};
	}
	
	public static void selectAllAgent(ListView<DWCodesModelData> list) {
		list.getSelectionModel().selectAll();
	}
	
	public static void deselectAllAgent(ListView<DWCodesModelData> list) {
		list.getSelectionModel().deselectAll();
	}
	
	public static LinkedHashMap<String, String> sortByValuesASC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	public static LinkedHashMap<String, String> sortByValuesDESC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = size -1; i>= 0; i--)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	
	public static String getGoogleLabel(DWFAOSTATQueryVO qvo) {
		Map<String, String> domains = qvo.getDomains();
		StringBuffer sb = new StringBuffer();
		int i = 1;
		for(String key : domains.keySet()) {
			sb.append(key);
			if ( i < domains.size() ) {
				sb.append(" - ");
			}
			i++;
		}
		return sb.toString();
	}
	
	public static String getGoogleLabel(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		int i = 1;
		for(String key : map.keySet()) {
			sb.append(key);
			if ( i < map.size() ) {
				sb.append(" - ");
			}
			i++;
		}
		return sb.toString();
	}
	
	public static SelectionListener<MenuEvent> refreshMapContent(final ContentPanel panel, final String url) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				refreshMapContentAgent(panel, url);
			}
		};
	}
	
	private static void refreshMapContentAgent(ContentPanel panel, String url) {
		System.out.println(url);
		panel.setUrl(url);
		panel.layout();
	}
	
}
