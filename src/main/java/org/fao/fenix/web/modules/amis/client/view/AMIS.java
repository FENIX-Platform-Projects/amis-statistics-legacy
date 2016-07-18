package org.fao.fenix.web.modules.amis.client.view;


import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.history.AMISHistory;
import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompare;
import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.RootPanel;

public class AMIS {

	AMISMainMenu mainMenu;

	AMISHome home;
	
	AMISHistory history;

	ContentPanel panel;
	
	public AMIS() {
		AMISController.cacheAMISDatasets();
		//AMISCompare.setOlapIsNull(true);
		//This has to be created here because it has to be set before the OLAP js function is called
		createOlapIframe();		
		AMISCompare.setNoElementsOlapIsNull(true);
		AMISCompare.setCompareNotesIsCreated(false);
		mainMenu = new AMISMainMenu();
		home = new AMISHome(mainMenu);
		history = new AMISHistory(mainMenu, this);
	}

  
	private void createOlapIframe() {
		String iframe = "<iframe style='display: block;' src='pivot.html' width='98%' border='0' frameborder='0' height='500px' name=\"OLAP\" id=\"amisOLAP\"></iframe>";
		// String iframe = "<iframe src='pivot.html' width='100%' height='500px' name=\"OLAP\" id=\"amisOLAP\"></iframe>";
		Html html = new Html(iframe);	
		html.setStyleAttribute("border", "1px solid #B5B8C8");
		html.setId("amisOLAP");
		RootPanel.get("OLAP_IFRAME").add(html);
	}


	public void build() {
		history.buildMainMenu();
		
		AMISHome.hideHomeVisibility();
		//buildHome();
		System.out.println("AMIS current view ................."+AMISController.currentSelectedView);
		// initialize history listener
		history.initHistorySupport(); 
		

	}

	/**private void buildMainMenu() {
		//Logo onclick action
		AMISHome.buildAndReplaceElement("AMIS-LOGO", AMISMenuController.openView(AMISCurrentView.HOME, mainMenu));

		RootPanel.get("MENU").add(mainMenu.build());
	
	}


	private void buildHome(){
		home.build();
		home.buildMap();		
	}
**/


}

