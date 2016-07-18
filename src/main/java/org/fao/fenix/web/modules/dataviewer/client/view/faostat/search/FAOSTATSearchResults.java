package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class FAOSTATSearchResults {
	
	ContentPanel panel;
	
	// based on items, elements etc...
	LinkedHashMap<String, FAOSTATSearchClusterResults> clusterResults;
	

	
	public FAOSTATSearchResults() {
		
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		clusterResults = new LinkedHashMap<String, FAOSTATSearchClusterResults>();

	}
	
	public void build(FAOSTATSearchTextBox searchTextBox) {
//		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
		panel.add(DataViewerClientUtils.addVSpace(10));
		
		panel.hide();
	}
	
	

	public ContentPanel getPanel() {
		return panel;
	}

	public LinkedHashMap<String, FAOSTATSearchClusterResults> getClusterResults() {
		return clusterResults;
	}


	
	
	
	
	
	
//	HashMap<String, List<FAOSTATSearchSingleResult>> searchSingleResults;
//	
//	/** TODO: change it **/
//	HashMap<String, String> titleAdded;
//	
//	
//	
//	// type of clustering I.E. ITEM, ELEMENT etc.. (needed??)
//	LinkedHashMap<String, FAOSTATSearchClusterResults> searchClusterResults;
//	
//
//	public FAOSTATSearchResults() {
//		panel = new ContentPanel();
//		panel.setBodyBorder(false);
//		panel.setHeaderVisible(false);
//		
//		searchSingleResults = new HashMap<String, List<FAOSTATSearchSingleResult>>();
//		
//		titleAdded = new HashMap<String, String>();
//		
//		searchClusterResults = new LinkedHashMap<String, FAOSTATSearchClusterResults>();
//	}
//	
//	public void build(FAOSTATSearchTextBox searchTextBox) {
//		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
//		panel.add(DataViewerClientUtils.addVSpace(10));
//		
//		panel.hide();
//	
//	}
//	
//	/** TODO: for the demo **/
//	public VerticalPanel buildComodityTitle(String title) {
//		VerticalPanel panel = new VerticalPanel();
//		panel.addStyleName("search_single_result");
//		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
//		
//		Html html = new Html("<div class='search_result_title'> " + title + "</div>");
//		
//		panel.add(DataViewerClientUtils.addVSpace(5));
//
//		panel.add(html);
//		
//		return panel;
//	}
//	
//	public HorizontalPanel addSubTitle(String title) {
//		
//		HorizontalPanel panel = new HorizontalPanel();
//		
////		IconButton html = new IconButton("<div class='search_result_subtitle'> " + title + "</div>");
//		
////		panel.add(widget)
//		
//		Html html = new Html("<div class='search_result_grouptitle'> " + title + "</div>");
//		
//		panel.add(html);
//		
//		return panel;
//	}
//	
//	public HorizontalPanel addDomainTitle(String title) {
//		
//		HorizontalPanel panel = new HorizontalPanel();
//		
////		IconButton html = new IconButton("<div class='search_result_subtitle'> " + title + "</div>");
//		
////		panel.add(widget)
//		
//		Html html = new Html("<div class='search_result_domaintitle'> " + title + "</div>");
//		
//		panel.add(html);
//		
//		return panel;
//	}
//
//	public ContentPanel getPanel() {
//		return panel;
//	}
//
//	public void setPanel(ContentPanel panel) {
//		this.panel = panel;
//	}
//
//
//
//	public HashMap<String, String> getTitleAdded() {
//		return titleAdded;
//	}
//
//	public void setTitleAdded(HashMap<String, String> titleAdded) {
//		this.titleAdded = titleAdded;
//	}
//	
	
	
}
