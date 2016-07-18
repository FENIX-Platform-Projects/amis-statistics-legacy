package org.fao.fenix.web.modules.core.client.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;

public class DataPeriodicityList {

	private LinkedHashMap<String, String> periodicityMap;

	public DataPeriodicityList() {

		periodicityMap = new LinkedHashMap<String, String>();
		initializePeriodicityMap();

	}

	public LinkedHashMap<String, String> getPeriodicityList() {
		return periodicityMap;
	}

	private void setPeriodicityList(LinkedHashMap<String, String> periodicityMap) {
		this.periodicityMap = periodicityMap;
	}

	public String getPeriodicityLabel(String key) {

		String label = (String) periodicityMap.get(key);

		return label;
	}

	private void initializePeriodicityMap() {

		periodicityMap.put("daily", "Daily");
		periodicityMap.put("weekly", "Weekly");
		periodicityMap.put("dekadal", "Dekadal");
		periodicityMap.put("monthly", "Monthly");
		periodicityMap.put("yearly", "Yearly");
		periodicityMap.put("when needed", "When Needed");

		setPeriodicityList(periodicityMap);

	}

	public ListBox getPeriodicityListBox() {
		ListBox list = new ListBox();
		LinkedHashMap<String, String> map = getPeriodicityList();

		list.setWidth("95%");
		list.setName("periodTypeCode");

		list.addItem("", "");

		Iterator<Map.Entry<String, String>> keyValuePairs = map.entrySet().iterator();

		for (int i = 0; i < map.size(); i++) {
			Map.Entry<String, String> entry = keyValuePairs.next();
			//String key = (String) entry.getKey();
			//String value = (String) entry.getValue();
			String key = entry.getKey();
			String value = entry.getValue();


			list.addItem(value, key);

		}
		return list;

	}

}
