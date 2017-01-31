package org.fao.fenix.web.modules.cpf.client.control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.cpf.common.services.CPFServiceEntry;
import org.fao.fenix.web.modules.cpf.common.vo.CPFResultVO;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;




public class CPFController {


     public static SelectionListener<MenuEvent> exportChartExcel(final CPFResultVO vo) {
 		return new SelectionListener<MenuEvent>() {
 			public void componentSelected(MenuEvent ce) {
 				exportChartExcelAgent(vo);
 			}
 		};
 	}


 	private static void exportChartExcelAgent(final CPFResultVO vo) {
 		List<List<String>> table = new ArrayList<List<String>>();
 		LinkedHashMap<String, Map<String, Double>> chartValues = vo.getChartValues();

// 		System.out.println("chartValues" + chartValues);

 		// TODO: quick fix for the stack (DO A SERIE switcher)
 		System.out.println("vo.getOutputType(): " + vo.getTypeOfOutput());


 		System.out.println("COUNT: " + chartValues.size());

 		for(String key : chartValues.keySet()) {
 			for(String chartKey : chartValues.get(key).keySet()) {
 				List<String> row = new ArrayList<String>();
 				row.add(key);
 				row.add(chartKey);
 				row.add(chartValues.get(key).get(chartKey).toString());
 				table.add(row);
 			}
 		}

 		vo.setTableHeaders(vo.getTableHeaders());
 		vo.setTableContents(table);


// 		System.out.println("getTableContents" + vo.getTableContents());

 		CPFServiceEntry.getInstance().export(vo, new AsyncCallback<String>() {

 			public void onSuccess(String result) {
 			//	com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
 				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "",  "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");

 			}

 			public void onFailure(Throwable arg0) {

 			}
 		});
 	}

 	public static SelectionListener<MenuEvent> exportTableExcel(final CPFResultVO vo) {
 		return new SelectionListener<MenuEvent>() {
 			public void componentSelected(MenuEvent ce) {
 				exportTableExcelAgent(vo);
 			}
 		};
 	}



 	private static void exportTableExcelAgent(final CPFResultVO vo) {
 		CPFServiceEntry.getInstance().export(vo, new AsyncCallback<String>() {

 			public void onSuccess(String result) {
 				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
 			}

 			public void onFailure(Throwable arg0) {

 			}
 		});
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


}