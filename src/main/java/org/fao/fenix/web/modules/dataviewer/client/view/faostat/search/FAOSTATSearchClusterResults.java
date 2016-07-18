package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;


public class FAOSTATSearchClusterResults {
	
	ContentPanel panel;
	
	ContentPanel pagingPanel;
	
	ContentPanel clusterResultPanel;

	ContentPanel filtersPanel;
	
	// code (i.e. ItemCode) - all the infos related
	LinkedHashMap<String, FAOSTATSearchClusterResult> clusterResult;
	
	// I.E. element, item (needed ??)
	String clusterType;
	
	FAOSTATSearchFilter searchFilter;
	
	FAOSTATSearchResultsPaging paging;
	
	public FAOSTATSearchClusterResults() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		clusterResultPanel = new ContentPanel();
		clusterResultPanel.setBodyBorder(false);
		clusterResultPanel.setHeaderVisible(false);
		
		pagingPanel = new ContentPanel();
		pagingPanel.setBodyBorder(false);
		pagingPanel.setHeaderVisible(false);
		
		filtersPanel = new ContentPanel();
		filtersPanel.setBodyBorder(false);
		filtersPanel.setHeaderVisible(false);
		
		clusterResult = new LinkedHashMap<String, FAOSTATSearchClusterResult>();	
		
		searchFilter = new FAOSTATSearchFilter();
		
		paging = new FAOSTATSearchResultsPaging();
		
		
		
		clusterResultPanel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
		pagingPanel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);


	}
	
	public ContentPanel build(FAOSTATSearchTextBox searchTextBox) {
		
		HorizontalPanel h = new HorizontalPanel();
		h.add(DataViewerClientUtils.addHSpace(FAOSTATSearchConstants.SEARCH_TEXT_LEFT_MARGIN));
		h.add(buildOutputPanel(searchTextBox));
		panel.add(h);
		
		return panel;
	}

	
	private HorizontalPanel buildOutputPanel(FAOSTATSearchTextBox searchTextBox) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(buildOutputFilterPanel(searchTextBox));
		panel.add(DataViewerClientUtils.addHSpace(20));
		panel.add(buildOutputResultsPanel(searchTextBox));
		return panel;
	}
	
	private ContentPanel buildOutputFilterPanel(FAOSTATSearchTextBox searchTextBox) {
		
		return searchFilter.build(this);

	}

	private VerticalPanel buildOutputResultsPanel(FAOSTATSearchTextBox searchTextBox) {
		VerticalPanel panel = new VerticalPanel();
		
		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
	
		
		panel.add(paging.build(searchTextBox));
	
		panel.add(DataViewerClientUtils.addVSpace(5));
		
		panel.add(clusterResultPanel);
		
		return panel;
	}

	public LinkedHashMap<String, FAOSTATSearchClusterResult> getClusterResult() {
		return clusterResult;
	}


	public void setClusterResult(
			LinkedHashMap<String, FAOSTATSearchClusterResult> clusterResult) {
		this.clusterResult = clusterResult;
	}


	public String getClusterType() {
		return clusterType;
	}


	public void setClusterType(String clusterType) {
		this.clusterType = clusterType;
	}


	

	public FAOSTATSearchFilter getSearchFilter() {
		return searchFilter;
	}

	public ContentPanel getClusterResultPanel() {
		return clusterResultPanel;
	}

	public ContentPanel getFiltersPanel() {
		return filtersPanel;
	}

	public FAOSTATSearchResultsPaging getPaging() {
		return paging;
	}

	public ContentPanel getPanel() {
		return panel;
	}

//	public ContentPanel getPanel() {
//		return panel;
//	}
	
	
	
	
	
}
