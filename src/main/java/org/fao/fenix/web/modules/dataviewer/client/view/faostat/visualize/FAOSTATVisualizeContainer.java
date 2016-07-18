package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize;


import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.byarea.FAOSTATVisualizeByAreaFilters;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

public abstract class FAOSTATVisualizeContainer extends LayoutContainer {
	
	
	protected FAOSTATVisualizeByAreaFilters filtersPanel;
	protected FAOSTATVisualizeSettingsVO settings;
	protected ContentPanel centerPanel;
	
	
	public FAOSTATVisualizeContainer(){
	
		filtersPanel = new FAOSTATVisualizeByAreaFilters();
		settings = new FAOSTATVisualizeSettingsVO();
		centerPanel = new ContentPanel();
		centerPanel.setBodyBorder(false);
	}
	
	public ContentPanel getCenterPanel() {
		return centerPanel;
	}
	
	public FAOSTATVisualizeByAreaFilters getFiltersPanel() {
		return filtersPanel;
	}
	
	public FAOSTATVisualizeSettingsVO getSettings() {
		return settings;
	}
	   
}
