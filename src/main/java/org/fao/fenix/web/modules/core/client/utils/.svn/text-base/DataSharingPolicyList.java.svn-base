package org.fao.fenix.web.modules.core.client.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;

public class DataSharingPolicyList {

	private LinkedHashMap<String, String> dataSharingMap;

	public DataSharingPolicyList() {

		dataSharingMap = new LinkedHashMap<String, String>();
		initializeDataSharingPolicyMap();

	}

	public LinkedHashMap<String, String> getDataSharingPolicyList() {
		return dataSharingMap;
	}

	private void setDataSharingPolicyList(LinkedHashMap<String, String> dataSharingMap) {
		this.dataSharingMap = dataSharingMap;
	}

	public String getDataSharingPolicyLabel(String key) {

		String label = (String) dataSharingMap.get(key);

		return label;
	}

	private void initializeDataSharingPolicyMap() {

		dataSharingMap.put("Shared", "Shared");
		dataSharingMap.put("Public", "Public");
		dataSharingMap.put("Private", "Private");
		dataSharingMap.put("Role", "Role");

		setDataSharingPolicyList(dataSharingMap);

	}

	public ListBox getDataSharingPolicyListBox() {
		ListBox list = new ListBox();
		Map<String, String> map = getDataSharingPolicyList();

		list.setWidth("95%");
		list.setName("dataSharing");

		list.addItem("", "");
	
		Iterator<Map.Entry<String, String>> keyValuePairs = map.entrySet().iterator();
		
		for (int i = 0; i < map.size(); i++) {
			Map.Entry<String, String> entry = keyValuePairs.next();
			String key = entry.getKey();
			String value = entry.getValue();

			list.addItem(value, key);

		}
		return list;

	}

}
