package org.fao.fenix.web.modules.core.client.utils;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.re.client.control.SearchParametersController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.ui.ListBox;

public class CategoryList {

	private LinkedHashMap<String,String> categoryMap = new LinkedHashMap<String,String>();
	private Boolean check = false;



	public CategoryList() {
	
	}
	
	public CategoryList(ListBox listBox) {
		categoryMap = new LinkedHashMap<String,String>();
		initializeCategoryMap(listBox);
	}
	
	

	public LinkedHashMap<String,String> getCategoryList() {
		return categoryMap;
	}

	private void setCategoryList(LinkedHashMap<String,String> categoryMap) {
		this.categoryMap = categoryMap;
	}



	private void initializeCategoryMap(ListBox listBox) {
		System.out.println("inizialiaze");
		SearchParametersController.fillCategories(categoryMap, listBox);

		setCategoryList(categoryMap);
		
	}
	
	public ListStore<CategoryModelData> getCategoryStore(ListStore<CategoryModelData> listStore) {
		SearchParametersController.fillCategories(listStore);
		return listStore;
	}
	
	public ResourceChildModel getCategoryResourceChildModel(String code, ResourceChildModel rcm) {
		SearchParametersController.fillCategories(code, rcm);
		return rcm;
	}
	
	
	

	public ListBox getCategoriesListBox(ListBox listBox) {
		
//		categoryMap = new LinkedHashMap<String,String>();
		
		System.out.println(categoryMap.size());
		listBox.addItem("");
	

//		System.out.println(categoryMap.size());
//		setCategoryList(categoryMap);
//		
//		System.out.println("tanto per gradi'" + getCategoryList().size());
		
//		LinkedHashMap<String,String> map = getCategoryList();
//
//		list.setWidth("95%");
//		list.setName("categories");
//
//		list.addItem("", "");
//
//		Iterator<Map.Entry<String, String>> keyValuePairs = map.entrySet().iterator();
//
//		for (int i = 0; i < map.size(); i++) {
//			Map.Entry<String, String> entry = keyValuePairs.next();
//			String key = (String) entry.getKey();
//			String value = (String) entry.getValue();
//
//			list.addItem(value, key);
//
//		}
		
		return listBox;
	}
	

}
