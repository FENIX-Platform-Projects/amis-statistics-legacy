package org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea.FAOSTATCompareByArea;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATCompare {
	
	
	public FAOSTATCompare(FAOSTATCurrentView view) {
		buildCompareSubMenu(view);
    }
	
	public void build(FAOSTATCurrentView view) {
		
		switch (view) {		
			case COMPARE_BY_AREA:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATCompareByArea().build());
			break;
			case COMPARE_DATA:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATCompareData().build());
			break;
		}
		
	}
	
	private static void buildCompareSubMenu(FAOSTATCurrentView view) {
		if ( RootPanel.get("SECONDARY_MENU_COMPARE").getWidgetCount() > 0 ) {
			RootPanel.get("SECONDARY_MENU_COMPARE").remove(RootPanel.get("SECONDARY_MENU_COMPARE").getWidget(0));
		}
		//RootPanel.get("SECONDARY_MENU_COMPARE").add(new FAOSTATCompareSecondaryMenu().build(view));
	}
	
	
	
	
}
