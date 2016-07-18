package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchResultsVO;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class FAOSTATSearchClusterResult {
	
	ContentPanel panel;
	
	ContentPanel downloadAll;
	
	List<FAOSTATSearchSingleResult> searchSingleResults;
	
	String label;
	
	String code;
	
	FAOSTATSearchResultsVO searchResultsVO;
	
	// checkBox
	
		

	public FAOSTATSearchClusterResult() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		downloadAll = new ContentPanel();
		downloadAll.setBodyBorder(false);
		downloadAll.setHeaderVisible(false);
		
		searchSingleResults = new ArrayList<FAOSTATSearchSingleResult>();
		

	}
	
	public void build(FAOSTATSearchTextBox searchTextBox) {
		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
		panel.add(DataViewerClientUtils.addVSpace(10));
		
		panel.hide();
	
	}
	
	public VerticalPanel buildTitle(String title) {
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName("search_single_result");
		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
		
		Html html = new Html("<div class='search_result_title'> " + title + "</div>");
		
		panel.add(DataViewerClientUtils.addVSpace(5));

		panel.add(html);
		
		return panel;
	}
	
	public void addDownloadAll(FAOSTATSearchTextBox searchTextBox) {
		HorizontalPanel panel = new HorizontalPanel();

		ClickHtml html = new ClickHtml();
		html.setHtml("<div class='search_result_export'>"+FAOSTATLanguage.print().downloadAll()+"</div>");
		
	
		html.addListener(Events.OnClick, FAOSTATSearchController.quickDownloadAllCluster(searchTextBox, searchSingleResults));
		
		panel.add(html);
		
		downloadAll.add(panel);
	}
	
	
	


	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}

	public List<FAOSTATSearchSingleResult> getSearchSingleResults() {
		return searchSingleResults;
	}

	public void setSearchSingleResults(
			List<FAOSTATSearchSingleResult> searchSingleResults) {
		this.searchSingleResults = searchSingleResults;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public FAOSTATSearchResultsVO getSearchResultsVO() {
		return searchResultsVO;
	}

	public void setSearchResultsVO(FAOSTATSearchResultsVO searchResultsVO) {
		this.searchResultsVO = searchResultsVO;
	}

	public ContentPanel getDownloadAll() {
		return downloadAll;
	}

	
	
	
}
