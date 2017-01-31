package org.fao.fenix.web.modules.amis.client.view.utils;

import java.util.List;

import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;



import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class AMISFiltersPanel {

	public ContentPanel panel;


	List<AMISFilter> filters;

	public AMISFiltersPanel() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");

	}

	public ContentPanel build(List<AMISFilter> filters) {
		HorizontalPanel p = new HorizontalPanel();
		p.setStyleAttribute("backgroundColor", "#FFFFFF");

		this.filters = filters;

		panel.add(p);
		

		return panel;
	}



	public ContentPanel getPanel() {
		return panel;
	}

	public List<AMISFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<AMISFilter> filters) {
		this.filters = filters;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}






}
