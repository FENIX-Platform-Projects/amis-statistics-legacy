package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize;

import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.byarea.FAOSTATVisualizeByArea;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic.FAOSTATVisualizeByTopic;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.top20.FAOSTATVisualizeTop20;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATVisualizeEntry extends LayoutContainer  {
	
	public FAOSTATVisualizeEntry(FAOSTATCurrentView view) {
		buildVisualizeSubMenu(view);
    }
	
	public void build(FAOSTATCurrentView view) {
		
//		System.out.println("FAOSTATVisualize build(FAOSTATCurrentView view): " + view.toString());
			
		switch (view) {			
			case VISUALIZE_BY_AREA: 
				ContentPanel container = new ContentPanel();
				container.setHeaderVisible(false);
				container.setBorders(false);
				container.setBodyBorder(false);
				container.add(new FAOSTATVisualizeByArea());
				RootPanel.get("MAIN_CONTENT").add(container);	
				break;
			case VISUALIZE_BY_DOMAIN:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATVisualizeByDomain().build());
			break;
			case VISUALIZE_BY_TOPIC:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATVisualizeByTopic().build());
			break;
			case VISUALIZE_TOP_20:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATVisualizeTop20().build());
			break;
		}
		
	}
	
	private static void buildVisualizeSubMenu(FAOSTATCurrentView view) {
		// select view
//		System.out.println("building menu");
		if ( RootPanel.get("SECONDARY_MENU_VISUALIZE").getWidgetCount() > 0 ) {
			RootPanel.get("SECONDARY_MENU_VISUALIZE").remove(RootPanel.get("SECONDARY_MENU_VISUALIZE").getWidget(0));
		}
		RootPanel.get("SECONDARY_MENU_VISUALIZE").add(new FAOSTATVisualizeSecondaryMenu().build(view));
	}

}