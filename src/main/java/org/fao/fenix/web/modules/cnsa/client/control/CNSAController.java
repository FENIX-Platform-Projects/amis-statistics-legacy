package org.fao.fenix.web.modules.cnsa.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.cnsa.common.services.CNSAServiceEntry;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CNSAController {
	
	public static void initializeCNSAReport(final ListStore<ListItem> store, final ComboBox<ListItem> comboBox, final String type) {
		String language = CheckLanguage.getLanguage();
		
		System.out.println("language: " + language);
		
		CNSAServiceEntry.getInstance().getReportInfo("108_prices", type, language, new AsyncCallback<HashMap<String, String>>() {
			public void onSuccess(HashMap<String, String> result) {
				
				List<ListItem> values = new ArrayList<ListItem>();
				for (String key : result.keySet())  
					values.add(new ListItem(key, result.get(key)));
				

				store.removeAll();
				store.add(values);
	
				if (!values.isEmpty())
					comboBox.setValue(values.get(0));
	
				
				
			}
	
			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error retrieving organizations", "");
			}
		});
	}
	
	public static void initializeCNSAReport(final ContentPanel contentPanel, final ListBox listBox, final String type, final String height, final String width, final String language) {
		
		System.out.println("language: " + language);
		
		CNSAServiceEntry.getInstance().getReportInfo("108_prices", type, language, new AsyncCallback<HashMap<String, String>>() {
			public void onSuccess(HashMap<String, String> result) {
						
				LinkedHashMap<String, String> out = sortByValuesDESC(result);
				
				for (String key : out.keySet())  
					listBox.addItem(key, out.get(key));
				
				
				getReport(contentPanel, listBox, type, height, width, language);
			}
	
			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error retrieving organizations", "");
			}
		});
	}
	
	
	public static SelectionListener<ButtonEvent> getReportExport(final ContentPanel contentPanel, final ListBox listBox, final String type, final String height, final String width, final String language) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				
				final LoadingWindow loadingWindow = new LoadingWindow("Loading Data", BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				
				String value = listBox.getValue(listBox.getSelectedIndex());
				
				String w = calculateSpacerWidth(width);
				String h = calculateSpacerHeight(height);
				
				CNSAServiceEntry.getInstance().getReport("108_prices", type, value, "export", "PDF", language, h, w, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						// export in a new window
						loadingWindow.destroyLoadingBox();
						Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
					}
					
					public void onFailure(Throwable arg0) {
						loadingWindow.destroyLoadingBox();
					}
					
				});
				
			}
		};
	}
	
	public static ChangeListener getReportListener(final ContentPanel contentPanel, final ListBox listBox, final String type, final String height, final String width, final String language) {
		return new ChangeListener() {
			public void onChange(Widget arg0) {		
				getReport(contentPanel, listBox, type, height, width, language);
			}
		};
	}
	
	
	private static void getReport(final ContentPanel contentPanel, final ListBox listBox, final String type, final String height, final String width, final String language) {
		final LoadingWindow loadingWindow = new LoadingWindow("Loading Data", BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();
		
		String value = listBox.getValue(listBox.getSelectedIndex());
		
		System.out.println("value:" + value);
		
		String w = calculateSpacerWidth(width);
		String h = calculateSpacerHeight(height);
		
		CNSAServiceEntry.getInstance().getReport("108_prices", type, value, "normal", "PDF", language, h, w, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				HTML content = new HTML(result);
				contentPanel.removeAll();
				contentPanel.add(content);
				contentPanel.layout();
				contentPanel.setScrollMode(Scroll.AUTO);
				loadingWindow.destroyLoadingBox();
			}
			
			public void onFailure(Throwable arg0) {
				loadingWindow.destroyLoadingBox();
			}
			
		});
	}
	
	public static SelectionListener<ButtonEvent> getExcel(final ListBox listBox, final String type, final String language) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				String value = listBox.getValue(listBox.getSelectedIndex());

				CNSAServiceEntry.getInstance().exportExcel("108_prices", type, value, language, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");	
					}
					
					public void onFailure(Throwable arg0) {
					}
				});
				
				
			}
		};
	}
	
	
	
	private static LinkedHashMap<String, String> sortByValuesASC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = 0; i < size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	private static LinkedHashMap<String, String> sortByValuesDESC(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = size -1; i >= 0; i--)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	private static String calculateSpacerWidth(String width) {
		int spacer = Integer.parseInt(width.substring(0, width.length() - 2));
		return String.valueOf((spacer - 30)) + "px";
	}
	
	private static String calculateSpacerHeight(String height) {
		int spacer = Integer.parseInt(height.substring(0, height.length() - 2));
		return String.valueOf((spacer - 100)) + "px";
	}
}
