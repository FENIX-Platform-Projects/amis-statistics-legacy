package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.byarea;

import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;

// extends something...
public class FAOSTATVisualizeByAreaFilters {

	public ContentPanel panel;
	
	
	public FAOSTATVisualizeByAreaFilters() { 
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
	}
	
	public ContentPanel build(FAOSTATVisualizeSettingsVO filterVO) {	
		HorizontalPanel p = new HorizontalPanel();
				
		panel.add(p);
		
		return panel;
	}
	
	

	public ContentPanel getPanel() {
		return panel;
	}
	
	
	

}

