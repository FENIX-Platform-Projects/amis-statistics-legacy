package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FAOSTATSearchResultsPaging {
	
	ContentPanel panel;
	
	Integer currentIndex = 0;
	
	Integer totalResults = 0;
	
	Integer maxResults = 10;
	
	Html title;
	
	Html counter;
	
	public FAOSTATSearchResultsPaging() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		title = new Html();
		
		counter = new Html();
	}
	
	public ContentPanel build(FAOSTATSearchTextBox searchTextBox) {
		
		panel.removeAll();
		panel.setWidth(FAOSTATSearchConstants.RESULTS_WIDTH);
		
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("search_top_panel");
	
	
		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
			
		panelL.addStyleName("search_top_panel_left");
		panelR.addStyleName("search_top_panel_right");
		

		panelL.add(buildTitle());
//		panelR.add(buildPager());
		
		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.setTopComponent(toolBar);
		
		panel.hide();
		
		return panel;
	}
	
	private HorizontalPanel buildTitle() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(title);
		
		panel.add(DataViewerClientUtils.addHSpace(10));	
		
		panel.add(counter);
		
		return panel;
	}

	public Html getTitle() {
		return title;
	}
	
	private HorizontalPanel buildPager() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Html first =  new Html("<div class='search_pager'>"+FAOSTATLanguage.print().first()+"</div>");
		
		Html previous =  new Html("<div class='search_pager'>"+FAOSTATLanguage.print().previous()+"</div>");
		
		Html next =  new Html("<div class='search_pager_active'>"+FAOSTATLanguage.print().next()+"</div>");

		panel.add(first);
		panel.add(DataViewerClientUtils.addHSpace(10));
		panel.add(previous);
		panel.add(DataViewerClientUtils.addHSpace(10));
		panel.add(next);

		return panel;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public Html getCounter() {
		return counter;
	}
	
	
	
}
