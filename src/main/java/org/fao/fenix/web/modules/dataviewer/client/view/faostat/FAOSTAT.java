package org.fao.fenix.web.modules.dataviewer.client.view.faostat;

import java.util.HashMap;


import org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu.FAOSTATMenuController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguageConstants;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.home.FAOSTATHome;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.menu.FAOSTATMainMenu;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.menu.FAOSTATSecondaryMenu;
import org.fao.fenix.web.modules.dataviewer.client.view.feedbacks.FAOSTATFeedbacks;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.ContentPanel;

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTAT implements HistoryListener {

	//FAOSTATMainMenu mainMenu;

	FAOSTATSecondaryMenu secondaryMenu;

	FAOSTATHome home;

	ContentPanel panel;

	FAOSTATMainMenu mainMenu;
	
	public static FAOSTAT historyListener;

	// query string parameter we may use          
	private String id = null;          
	private String add = null;  
	private String locale = null;  


	public FAOSTAT() {
		secondaryMenu = new FAOSTATSecondaryMenu();
		mainMenu = new FAOSTATMainMenu();
		home = new FAOSTATHome(mainMenu);
		
		historyListener = this;

	}

	public void build() {

		// setting language
		setLanguage();
		
		// setting language
		setBrowser();
	
//		buildFeedbacks();

		buildMainMenu();
		
		// checking if it is IE7 (suggesting another browser...)
		Boolean ie7 = checkIEBrowser();
		if ( ie7 ) {
//			FenixAlert.info(FAOSTATLanguage.print().browserInUse(), FAOSTATLanguage.print().ie7Disclaimer(), "");
		}

		FAOSTATHome.hideHomeVisibility();

		// initialize history listener                  
		initHistorySupport();  
	}




	private void setLanguage() {
//		System.out.println("current locale: " + LocaleInfo.getCurrentLocale().getLocaleName());
		String language = LocaleInfo.getCurrentLocale().getLocaleName();
		if ( !language.equalsIgnoreCase("default")) {
			FAOSTATConstants.faostatLanguage = FAOSTATConstants.faostatLanguages.get(language);

//			System.out.println("FAOSTATConstants.faostatLanguage: " + FAOSTATConstants.faostatLanguage);

		}
	}
	
	private void setBrowser() {
//		System.out.println("current locale: " + LocaleInfo.getCurrentLocale().getLocaleName());
		String browser = getAppName();
		
		FAOSTATConstants.browser = browser.toLowerCase();
	
		String version = getAppVersion();
		
		FAOSTATConstants.version = version.toLowerCase();
		
		System.out.println("Browser: " + FAOSTATConstants.browser + " | " + FAOSTATConstants.version);

	}
	
	private void buildFeedbacks() {
		
		try {
			if (RootPanel.get("FEEDBACK").getWidgetCount() > 0)
				RootPanel.get("FEEDBACK").remove(RootPanel.get("FEEDBACK").getWidget(0));
			
			if (RootPanel.get("FEEDBACK_FORM").getWidgetCount() > 0)
				RootPanel.get("FEEDBACK_FORM").remove(RootPanel.get("FEEDBACK_FORM").getWidget(0));
			
				new FAOSTATFeedbacks().build();
//				RootPanel.get("FEEDBACK").add(new FAOSTATFeedbacks().build());
			}
		catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void buildMainMenu() {

		//Logo onclick action
//		FAOSTATHome.buildAndReplaceElement("FAOSTAT-LOGO", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.HOME));		

		RootPanel.get("MENU").add(mainMenu.build(this));

		History.addHistoryListener(this);

	}



	private void buildHome(){
		home.build();
	}




	/** 
	 * init history support, start watching for changes in history 
	 *  
	 * use historyTokens (anchor tags) to navigate 
	 *  
	 * observe history changes (tokens) 
	 */ 
	private void initHistorySupport() { 
		
		System.out.println("initHistorySupport()");
		
		History.addHistoryListener(this); 

		// check to see if there are any tokens passed at startup via the anchor tag 
		String token = History.getToken(); 

		if (token.length() == 0) { 
       	// navigate with anchors to home 
			//History.newItem(FAOSTATCurrentView.HOME.name()); 
			FAOSTATMenuController.openView(FAOSTATCurrentView.HOME.name(), mainMenu);
		} else { 
			onHistoryChanged(token); 
		} 
	} 


	/** 
	 * This observes History Change - via Anchors 
	 *  
	 * forward, back, load(b/c of set token) 
	 *  
	 */ 

	public void onHistoryChanged(String historyToken) { 

		System.out.println("onHistoryChanged history event ::: " + historyToken); 
		
		if (historyToken == null || historyToken.length() == 0) {  
			FAOSTATMenuController.openView(FAOSTATCurrentView.HOME.name(), mainMenu);
			//return; 
		} 
		else {
			FAOSTATHome.hideHomeVisibility();
			
			historyToken = parseHistoryToken(historyToken); 
	
			FAOSTATMenuController.openView(historyToken, mainMenu);
		}
		
	} 



	/** 
	 * parse the historyToken 
	 *   
	 * like domaint.tld#anchor?[var=1&var3=2&var3=3] 
	 *  
	 * @param historyToken anchor tag 
	 */ 
	private String parseHistoryToken(String historyToken) { 

		System.out.println("historyToken = "+historyToken);

		if (historyToken == null) { 
			return ""; 
		} 

		//get parameters from history token 
		if (historyToken.contains("?")) { 
			HashMap params = getHistoryTokenParameters(historyToken); 

			//use the parameters 
			setParams(params); 

			//get just the history token / anchor tag , not with parameters 
			historyToken = getHistoryToken(historyToken); 
		}  

		return historyToken; 
	}

	/** 
	 * get historyToken parameters 
	 *  
	 * like domaint.tld#anchor?[var=1&var3=2&var3=3] 
	 *  
	 * @param historyToken anchor tag 
	 * @return hashmap of the parameters 
	 */ 
	private static HashMap getHistoryTokenParameters(String historyToken) { 

		//skip if there is no question mark 
		if (!historyToken.contains("?")) { 
			return null; 
		} 

		//debug 
		//System.out.println("parse historyToken: " + historyToken); 

		// ? position 
		int questionMarkIndex = historyToken.indexOf("?") + 1; 

		//get the sub string of parameters var=1&var2=2&var3=3... 
		String[] arStr = historyToken.substring(questionMarkIndex, historyToken.length()).split("&"); 
		HashMap params = new HashMap(); 
		for (int i = 0; i < arStr.length; i++) { 
			String[] substr = arStr[i].split("="); 
			params.put(substr[0], substr[1]); 
		} 

		//debug 
		//System.out.println("map: " + params); 

		return params; 
	} 

	/** 
	 * get historyToken by itself 
	 *  
	 * like domain.tld#[historyToken]?params=1 
	 *   
	 * @param historyToken 
	 * @return 
	 */ 
	private String getHistoryToken(String historyToken) { 

		//skip if there is no question mark 
		if (!historyToken.contains("?")) { 
			return ""; 
		} 

		//get just the historyToken/anchor tag 
		String[] arStr = historyToken.split("\\?"); 
		historyToken = arStr[0]; 

		return historyToken; 
	} 

	/** 
	 * use the parameters 
	 * @param params 
	 */ 
	private void setParams(HashMap params) { 

		if (params == null) { 
			return; 
		} 

		if (params.get("id") != null) { 
			this.id = (String) params.get("id"); 
		} 

		if (params.get("add") != null) { 
			this.add  = (String) params.get("add"); 
		} 

		if (params.get("locale") != null) { 
			this.locale  = (String) params.get("locale"); 
		} 
	} 
	
	/** 
     * track with google analytics 
     */ 
    private void track(String s) { 
             
           //When ready to use Google anayltics enable
    	   //trackGA(s); 
             
            //debug 
            System.out.println("track - google analytics - finished"); 
    } 
     
    /** 
     * track with google analytics 
     *  
     * Uses JSNI - accesses native javascript in urchin.js and sends a gif request to google analytics 
     */ 
    private static native void trackGA(String s) /*-{ 
            $wnd._uacct = "UA-2862268-12"; 
            $wnd._uanchor = 1; 
            $wnd.urchinTracker("/HistoryAnchor/" + s);  
    }-*/; 

    
	
	
	/**
	 * Gets the navigator.appName.
	 *
	 * @return the window's navigator.appName.
	 */
	public static native String getAppName() /*-{
	  return $wnd.navigator.appName;
	}-*/;
	
	
	/**
	 * Gets the navigator.appVersion
	 *
	 * @return the window's navigator.appVersion
	 */
	public static native String getAppVersion() /*-{
	  return $wnd.navigator.appVersion;
	}-*/;

	public Boolean checkIEBrowser() {
		String userBrowserVersion = getAppName();
		return userBrowserVersion.toLowerCase().contains("msie 7");
	}
	
	
}
	

