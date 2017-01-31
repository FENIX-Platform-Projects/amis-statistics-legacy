package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.lang.client.BabelFish;


import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class FAOSTATVisualizeCompareFilters {

	ContentPanel panel;
	
	HorizontalPanel comparePanel;
	

	List<FAOSTATVisualizeFilter> filters;

	public FAOSTATVisualizeCompareFilters() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		comparePanel = new HorizontalPanel();
	}
	
	public ContentPanel build(Boolean buildWaitingPanel) {
		panel.removeAll();
		comparePanel.removeAll();
	
		if ( buildWaitingPanel ) {
			panel.add(buildTitle());
			
			panel.add(DataViewerClientUtils.addVSpace(5));
			
//			panel.add(new FAOSTATLoadingPanel().buildWaitingPanel("Loading", "300px", "50px", false));
		}
		

		panel.add(comparePanel);
		
		return panel;
	}
	
	private HorizontalPanel buildTitle() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Html title = new Html("<div class='visualize_parameters_title>"+FAOSTATLanguage.print().compareWith()+" </div>");
		
//		ClickHtml showhide = new ClickHtml();
//		showhide.setHtml("<div class='visualize_parameters_title_showhide'>(show/hide):</div>");
		
//		showhide.addListener(Events.OnClick, showhideParametersPanel());

		
		panel.add(title);
//		panel.add(DataViewerClientUtils.addHSpace(5));
//		panel.add(showhide);
		
		return panel;
	}
	
//	private HorizontalPanel buildCompareFilter(FAOSTATVisualizeFilter compareFilter) {
//		HorizontalPanel panel = new HorizontalPanel();
//		
//		comparePanel.add(compareFilter.buildStandardFilter(visualization, title, filterType, multi, code, defaultCodes));
//
//
//		return panel;
//	}
	
//	public ContentPanel build(List<FAOSTATVisualizeFilter> filters) {	
//		HorizontalPanel p = new HorizontalPanel();
//		
//		this.filters = filters;
//		
//		panel.add(p);
//		
//		return panel;
//	}
	
	

	public ContentPanel getPanel() {
		return panel;
	}

	public List<FAOSTATVisualizeFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<FAOSTATVisualizeFilter> filters) {
		this.filters = filters;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}

	

	
	
	
}
