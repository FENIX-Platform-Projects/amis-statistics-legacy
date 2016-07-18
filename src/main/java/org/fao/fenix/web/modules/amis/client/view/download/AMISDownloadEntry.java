package org.fao.fenix.web.modules.amis.client.view.download;

import com.google.gwt.user.client.ui.RootPanel;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.view.download.foodbalance.AMISDownloadFoodBalance;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginPanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.core.client.security.FenixUser;

public class AMISDownloadEntry {

	public AMISDownloadEntry(AMISCurrentView view) {

        buildDownloadSubMenu(view);

        //REINSTATE THIS, AND DELETE ABOVE!!!
//       if(FenixUser.hasAdminRole()) {
//          if((AMISLoginPanel.getAmisUserParameters().getUsername()!=null)&& AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT))
//            buildDownloadSubMenu(view);
//        }
    }
	
	public void build(AMISCurrentView view) {			
		switch (view) {			
			case DOWNLOAD_STANDARD:
                AMISDownloadController.isFoodBalance = false;
                RootPanel.get("SUB_MENU_DOWNLOAD").setVisible(true);
				RootPanel.get("OLAP_IFRAME").setVisible(false);
				RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
				RootPanel.get("COMPARE_NOTES").setVisible(false);
				RootPanel.get("MAIN_CONTENT").add(new AMISDownload().build());
				break;
			case DOWNLOAD_FULL_BALANCE:
                AMISDownloadController.isFoodBalance = true;
                RootPanel.get("SUB_MENU_DOWNLOAD").setVisible(true);
				RootPanel.get("OLAP_IFRAME").setVisible(false);
				RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
				RootPanel.get("COMPARE_NOTES").setVisible(false);
                RootPanel.get("MAIN_CONTENT").add(new AMISDownloadFoodBalance().build());
			break;
			default:
				RootPanel.get("OLAP_IFRAME").setVisible(false);
				RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
				RootPanel.get("COMPARE_NOTES").setVisible(false);
				RootPanel.get("MAIN_CONTENT").add(new AMISDownload().build());
			break;
		}
		
	}
	
	private static void buildDownloadSubMenu(AMISCurrentView view) {
   	   if ( RootPanel.get("SUB_MENU_DOWNLOAD").getWidgetCount() > 0 ) {
			RootPanel.get("SUB_MENU_DOWNLOAD").remove(RootPanel.get("SUB_MENU_DOWNLOAD").getWidget(0));
		}
   	   	
   	    RootPanel.get("SUB_MENU_DOWNLOAD").add(new AMISDownloadSubMenu().build(view));
   	 
	}
    
}