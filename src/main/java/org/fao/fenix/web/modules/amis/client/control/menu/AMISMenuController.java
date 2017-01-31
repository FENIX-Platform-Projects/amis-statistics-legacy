package org.fao.fenix.web.modules.amis.client.control.menu;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.compare.AMISCompareController;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.control.input.AMISInputController;
import org.fao.fenix.web.modules.amis.client.lang.AMISLanguage;
import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.statisticalnotes.AMISStatisticalNotes;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
public class AMISMenuController {

	
	public static void openView(String historyToken, AMISMainMenu mainMenu) {
		final AMISCurrentView currentView = AMISCurrentView.getCurrentView(historyToken);
		
		System.out.println("*************** AMISMenuController: openView CALLS openViewAgent : "+currentView + " historyToken "+ historyToken + "mainMenu "+mainMenu);
		
		
//		if((currentView!=null)&&(!(currentView.toString().equals(AMISCurrentView.INPUT.toString()))))
//		{
//			if(AMISInput.openedCbsTool)
//			{						
//				System.out.println("The tool is opened "+AMISInput.openedCbsTool);
//				
//				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {  
//				    public void handleEvent(MessageBoxEvent ce) {  
//				       Button btn = ce.getButtonClicked();  
//				       //Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());  
//				       if((btn!=null)&&(btn.getText().equalsIgnoreCase("YES")))
//				       {
//				    	   AMISInput.openedCbsTool = false;
//				    	   openViewAgent(currentView, mainMenu);
//				       }
//				    }  
//				    };  
//				  				
//				MessageBox.confirm("Confirm", "You are changing view without closing the CBS Tool. Any modification will be lost. Are you sure you want to do this?", l);  
//			}
//			else
//			{
//				//Call the view
//				openViewAgent(currentView, mainMenu);
//			}
//		}
//		else
//		{
//			//Call the view
//			openViewAgent(currentView, mainMenu);
//		}
		//Call the view
		openViewAgent(currentView, mainMenu);
	}
	
	public static Listener<ComponentEvent> openView(final AMISCurrentView currentView, final AMISMainMenu menubar) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {

				System.out.println("*************** AMISMenuController: openView<ComponentEvent> calls openViewAgent:  "+currentView.name());
				
				HorizontalPanel p = (HorizontalPanel)menubar.getMenuPanel().getItemByItemId(currentView.name());
                p.removeStyleName(p.getStyleName());
			    String baseStyleName = (String)p.getData("base-style-name");
			    
			    
			    for (AMISCurrentView view: AMISCurrentView.values()) {
			    	if(!currentView.equals(view) && menubar.getMenuPanel().getItemByItemId(view.name())!=null) {
			    		HorizontalPanel otherPanel = (HorizontalPanel)menubar.getMenuPanel().getItemByItemId(view.name());
			    		otherPanel.setStyleName(baseStyleName);
			    		ClickHtml html = (ClickHtml)otherPanel.getItemByItemId(view.name());						   
			    		html.setHtml("<div class='main-menu-title'>" + AMISLanguage.print().getString(view.name()) + "</div>");
			    		
			    	}
			    	else {
			    		ClickHtml html = (ClickHtml)p.getItemByItemId(currentView.name());
			    		p.setStyleName(baseStyleName+"-selected");
			    		html.setHtml("<div class='main-menu-title-selected'>" + AMISLanguage.print().getString(currentView.name()) + "</div>");
			    		
			    	}
			    }

				openViewAgent(currentView, menubar);
			}
		};
	}

	public static Listener<ComponentEvent> openAMISHome() {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				Window.open("http://www.amis-outlook.org/home/en/", "_parent", "");
			}
		};
	}
	
	

	/**
	 *
	 * This method call the selected view
	 *
	 * set the selected style to the panel and
	 * set the default style to the others
	 *
	 *
	 * @param currentView
	 * @return
	 * @return
	 */

	public static void openViewAgent(final AMISCurrentView currentView, final AMISMainMenu menubar) {
		System.out.println("openViewAgent VIEW: " + currentView.toString());

		/**
		 * TODO: select/unselect styles
		 *
		 */
		System.out.println(" %%%%%%%%%%%%%%%  AMISMENUCONTROLLER ==== Current View = "+currentView + " | MAIN_CONTENT WidgetCount() = "+RootPanel.get("MAIN_CONTENT").getWidgetCount());

		// remove MAIN_CONTENT
		if(RootPanel.get("MAIN_CONTENT").getWidgetCount() > 0){
			RootPanel.get("MAIN_CONTENT").remove(RootPanel.get("MAIN_CONTENT").getWidget(0));			
		} else if(RootPanel.get("MAIN_CONTENT").getWidgetCount() == 0) {

			if(RootPanel.get("MAIN_CONTENT").getElement().getChildCount() > 0){ // to allow for AMIS Tracking JS object to be removed also
				for(int i = 0; i < RootPanel.get("MAIN_CONTENT").getElement().getChildCount(); i++) {
					Element node = (Element)RootPanel.get("MAIN_CONTENT").getElement().getChild(i);
					if(node!=null){
						   if(node.getId()!=null && node.getId().equals("x-amis-tracking-js")){
							System.out.println("%%%%%%%%%%%%%%%  AMISMENUCONTROLLER ==== REMOVE CHILD "+node.getId());
							RootPanel.get("MAIN_CONTENT").getElement().removeChild(node); 
							break;
						}   
					}
				}
			}
		}
		
	
		if (RootPanel.get("SUB_MENU_COMPARE").getWidgetCount() > 0 && !currentView.name().contains(AMISCurrentView.COMPARE.name()))
			RootPanel.get("SUB_MENU_COMPARE").remove(RootPanel.get("SUB_MENU_COMPARE").getWidget(0));

        if (RootPanel.get("SUB_MENU_DOWNLOAD").getWidgetCount() > 0 && !currentView.name().contains(AMISCurrentView.DOWNLOAD.name()))
            RootPanel.get("SUB_MENU_DOWNLOAD").remove(RootPanel.get("SUB_MENU_DOWNLOAD").getWidget(0));


        //AMISHome.hideHomeVisibility();

		switch (currentView) {
		case HOME:
			setMainMenuTabStyle(AMISCurrentView.HOME, menubar);
			AMISController.currentSelectedView = AMISCurrentView.HOME;
			new AMISHome(menubar).build();
			break;
		case COMPARE:
			setMainMenuTabStyle(AMISCurrentView.COMPARE, menubar);
			AMISController.currentSelectedView = AMISCurrentView.COMPARE;
			AMISCompareController.callCompareView();
			break;
		case COMPARE_DATASOURCES:
          	setMainMenuTabStyle(AMISCurrentView.COMPARE, menubar);
			//sub-view
			AMISCompareController.callCompareSubView(currentView);
			break;
		case COMPARE_ACTIVITY_TRACKING:
			setMainMenuTabStyle(AMISCurrentView.COMPARE, menubar);
			//sub-view
			AMISCompareController.callCompareSubView(currentView);
			break;	
		case DOWNLOAD:
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!IN DOWNLOAD .... openViewAgent");
			setMainMenuTabStyle(AMISCurrentView.DOWNLOAD, menubar);
			AMISController.currentSelectedView = AMISCurrentView.DOWNLOAD;
			AMISDownloadController.callDownloadView();
			break;
        case DOWNLOAD_STANDARD:
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!IN DOWNLOAD_STANDARD .... openViewAgent");
            setMainMenuTabStyle(AMISCurrentView.DOWNLOAD, menubar);
            //sub-view
            AMISDownloadController.callDownloadSubView(currentView);
            break;
        case DOWNLOAD_FULL_BALANCE:
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!IN DOWNLOAD_FULL_BALANCE .... openViewAgent");
            setMainMenuTabStyle(AMISCurrentView.DOWNLOAD, menubar);
            //sub-view
            AMISDownloadController.callDownloadSubView(currentView);
            break;
        case INPUT:
			setMainMenuTabStyle(AMISCurrentView.INPUT, menubar);
			AMISController.currentSelectedView = AMISCurrentView.INPUT;
			AMISInputController.callInputView(menubar);
		    break;
		case STATISTICALNOTES:
			setMainMenuTabStyle(AMISCurrentView.STATISTICALNOTES, menubar);
			AMISController.currentSelectedView = AMISCurrentView.STATISTICALNOTES;
			//System.out.println("Class: Amismenucontroller Text: before new AMISStatisticalNotes().build()");
		    RootPanel.get("MAIN_CONTENT").add(new AMISStatisticalNotes().build());
		   // System.out.println("Class: Amismenucontroller Text: after new AMISStatisticalNotes().build()");
	      	break;
		default:
			break;
		}
		
	}	
		
	public static void setMainMenuTabStyle(final AMISCurrentView currentView, final AMISMainMenu menubar){

		System.out.println("setMainMenuTabStyle: view "+currentView);

		HorizontalPanel selectedPanel = (HorizontalPanel)menubar.getMenuPanel().getItemByItemId(currentView.name());

		//System.out.println("setMainMenuTabStyle: selectedPanel "+selectedPanel);
		if(selectedPanel!=null){
		//	System.out.println("setMainMenuTabStyle: IF selectedPanel "+selectedPanel);
			selectedPanel.removeStyleName(selectedPanel.getStyleName());

			String baseStyleName = (String)selectedPanel.getData("base-style-name");

			for (AMISCurrentView view: AMISCurrentView.values()) {
				if(!currentView.equals(view) && menubar.getMenuPanel().getItemByItemId(view.name())!=null) {	 
				//	System.out.println("setMainMenuTabStyle: IF if(!currentView.equals(view) && menubar.getMenuPanel().getItemByItemId(view.name())!=null) view.name() "+view.name());
					menubar.getMenuPanel().getItemByItemId(view.name()).setStyleName(baseStyleName);
				}
				else {
					//System.out.println("setMainMenuTabStyle: ELSE if(!currentView.equals(view) && menubar.getMenuPanel().getItemByItemId(view.name())!=null) view.name() "+view.name());
					selectedPanel.setStyleName(baseStyleName+"-selected");
				}	
			}
			//System.out.println("setMainMenuTabStyle: END ");
		}	
		else
		{
			//System.out.println("setMainMenuTabStyle: ELSE selectedPanel "+selectedPanel);
		}
	}



}
