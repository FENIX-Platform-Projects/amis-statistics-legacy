package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils;


import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATDownloadConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;

import com.extjs.gxt.ui.client.Style.Scroll;

import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;


public class FAOSTATDomainPanel {
	
	public ContentPanel wrapper;
	
	public ContentPanel panel;
	
	public TreeStore<DWCodesModelData> store;  
  
	public TreePanel<DWCodesModelData> tree;  
    
	public String currentCode;
    
	public Boolean checkTreeOnClick = true;

	public FAOSTATDomainPanel() {
//		panel = new ContentPanel();
//		panel.setHeaderVisible(false);
//		panel.setBodyBorder(false);
//		panel.setScrollMode(Scroll.AUTO);
//		panel.setWidth(FAOSTATDownloadConstants.TREE_PANEL_WIDTH);
//		panel.setHeight(FAOSTATDownloadConstants.TREE_PANEL_HEIGHT);
		
		
		wrapper = new ContentPanel();
		wrapper.setHeaderVisible(false);
		wrapper.setBodyBorder(false);
		wrapper.setScrollMode(Scroll.NONE);
		
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
//		panel.setSize(FAOSTATDownloadConstants.TREE_PANEL_WIDTH, FAOSTATDownloadConstants.TREE_PANEL_HEIGHT);
		panel.setScrollMode(Scroll.NONE);
		
		
		/** Tree **/
		store = new TreeStore<DWCodesModelData>();
		tree = new TreePanel<DWCodesModelData>(store);
		tree.setDisplayProperty("label");
//		tree.setHeight(FAOSTATDownloadConstants.TREE_HEIGHT);
		tree.setWidth(FAOSTATDownloadConstants.TREE_WIDTH);
		tree.addStyleName("tree-box");
	}

	private Html addTitle(String title) {
		return new Html("<div class='download_title'> " + title + "</div>");
	}
	
	public void addTree() {
		panel.add(tree);
	}

	public TreeStore<DWCodesModelData> getStore() {
		return store;
	}

	public TreePanel<DWCodesModelData> getTree() {
		return tree;
	}

	public String getCurrentCode() {
		return currentCode;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}
	
	
	
}