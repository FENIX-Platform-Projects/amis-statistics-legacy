package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.List;


import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class FAOSTATVisualizeFilters {

	public ContentPanel panel;
	

	List<FAOSTATVisualizeFilter> filters;

	public FAOSTATVisualizeFilters() { 
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);

	}
	
	public ContentPanel build(List<FAOSTATVisualizeFilter> filters) {	
		HorizontalPanel p = new HorizontalPanel();
		
		this.filters = filters;
		
		panel.add(p);
		
		return panel;
	}
	
	

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
