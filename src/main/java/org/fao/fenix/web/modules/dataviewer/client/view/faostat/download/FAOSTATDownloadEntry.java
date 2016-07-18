package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATDownloadEntry extends LayoutContainer  {

	
	public FAOSTATDownloadEntry(FAOSTATCurrentView view) {
		buildDownloadSubMenu(view);
    }
	
	
	public void build(FAOSTATCurrentView view) {
		
//		System.out.println("FAOSTATDownload build(FAOSTATCurrentView view): " + view.toString());	
		
		switch (view) {
			case DOWNLOAD:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATDownload().build());
			break;
			case DOWNLOAD_STANDARD:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATDownload().build());
			break;
			case DOWNLOAD_BULK:
				RootPanel.get("MAIN_CONTENT").add(new FAOSTATBulkDownloadPanel());
//				RootPanel.get("MAIN_CONTENT").add(new FAOSTATBulkDownloadTreePanel());
			break;			
		}
		
	}
	
	private static void buildDownloadSubMenu(FAOSTATCurrentView view) {
		
		if ( RootPanel.get("SECONDARY_MENU_DOWNLOAD").getWidgetCount() > 0 ) {
			RootPanel.get("SECONDARY_MENU_DOWNLOAD").remove(RootPanel.get("SECONDARY_MENU_DOWNLOAD").getWidget(0));
		}
		RootPanel.get("SECONDARY_MENU_DOWNLOAD").add(new FAOSTATDownloadSecondaryMenu().build(view));

	}
   
}