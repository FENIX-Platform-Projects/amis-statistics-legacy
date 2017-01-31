package org.fao.fenix.web.modules.amis.client.view.compare;

import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.core.client.security.FenixUser;

import com.google.gwt.user.client.ui.RootPanel;

public class AMISCompareEntry {

	public AMISCompareEntry(AMISCurrentView view) {
		if(FenixUser.hasAdminRole()){
	       buildCompareSubMenu(view);
		}  
    }
	
	public void build(AMISCurrentView view) {			
		switch (view) {			
			case COMPARE_DATASOURCES: 
				RootPanel.get("OLAP_IFRAME").setVisible(false);
				RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
				RootPanel.get("COMPARE_NOTES").setVisible(false);
				RootPanel.get("MAIN_CONTENT").add(new AMISCompare().build());	
				break;
			case COMPARE_ACTIVITY_TRACKING:
				RootPanel.get("OLAP_IFRAME").setVisible(false);
				RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
				RootPanel.get("COMPARE_NOTES").setVisible(false);
				
				buildActivityTrackingJS();
				
			break;
			default:
				RootPanel.get("OLAP_IFRAME").setVisible(false);
				RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
				RootPanel.get("COMPARE_NOTES").setVisible(false);
				RootPanel.get("MAIN_CONTENT").add(new AMISCompare().build());	
			break;
		}
		
	}
	
	private static void buildCompareSubMenu(AMISCurrentView view) {
   	   if ( RootPanel.get("SUB_MENU_COMPARE").getWidgetCount() > 0 ) {
			RootPanel.get("SUB_MENU_COMPARE").remove(RootPanel.get("SUB_MENU_COMPARE").getWidget(0));
		}
   	   	
   	    RootPanel.get("SUB_MENU_COMPARE").add(new AMISCompareSubMenu().build(view));
   	 
	}
	
	
    public static native void buildActivityTrackingJS() /*-{
		  $wnd.AMISActivityTracking.initActivityTrackingUI();
	}-*/;
    
}
