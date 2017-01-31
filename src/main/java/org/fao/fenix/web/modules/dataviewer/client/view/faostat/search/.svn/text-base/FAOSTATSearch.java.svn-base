package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;


import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.extjs.gxt.ui.client.widget.ContentPanel;


public class FAOSTATSearch {
	
	ContentPanel panel;

	public FAOSTATSearch() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
	}
	
	public ContentPanel build(FAOSTATCurrentView view) {
		switch (view) {	
			case SEARCH_DATA: return buildSearchData();
		}
		return null;
	}
	
	public ContentPanel buildSearchData() {	
		FAOSTATSearchData searchData = new FAOSTATSearchData();
		return searchData.buildSearchData();
	}
}
