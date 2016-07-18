package org.fao.fenix.web.modules.cpf.client.history;

import java.util.HashMap;

import org.fao.fenix.web.modules.cpf.client.control.menu.CPFMenuController;
import org.fao.fenix.web.modules.cpf.client.view.CPF;
import org.fao.fenix.web.modules.cpf.client.view.home.CPFHome;
import org.fao.fenix.web.modules.cpf.client.view.menu.CPFMainMenu;
import org.fao.fenix.web.modules.cpf.client.view.utils.html.HTMLUtils;
import org.fao.fenix.web.modules.cpf.common.constants.CPFCurrentView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.RootPanel;

public class CPFHistory implements HistoryListener {

	// query string parameter we may use          
	private String id = null;          
	private String add = null;  
	private String locale = null;  

	private CPFMainMenu mainMenu;
	private CPF CPF;

	public CPFHistory(CPFMainMenu mainMenu, CPF CPF) {
		
		this.mainMenu = mainMenu;
		this.CPF = CPF;
		
		
		// initialize history listener                  
		initHistorySupport();  

	}
	
	public void buildMainMenu(boolean isLoggedIn) {
		
			if (RootPanel.get("MENU").getWidgetCount() > 0)
				RootPanel.get("MENU").remove(RootPanel.get("MENU").getWidget(0));
		
			this.mainMenu = new CPFMainMenu(CPF);
			
			RootPanel.get("MENU").add(mainMenu.build(isLoggedIn));
			
			History.addHistoryListener(this);
		}
	
/*	public void buildMainMenu(boolean isLoggedIn) {
	
		if (RootPanel.get("MENU").getWidgetCount() > 0)
			RootPanel.get("MENU").remove(RootPanel.get("MENU").getWidget(0));
	
		RootPanel.get("MENU").add(mainMenu.build(isLoggedIn));
		
		History.addHistoryListener(this);
	}*/
	

	/** 
	 * init history support, start watching for changes in history 
	 *  
	 * use historyTokens (anchor tags) to navigate 
	 *  
	 * observe history changes (tokens) 
	 */ 
	public void initHistorySupport() { 

		History.addHistoryListener(this); 

		// check to see if there are any tokens passed at startup via the anchor tag 
		String token = History.getToken(); 


		if (token.length() == 0) { 
				
       	// navigate with anchors to home 
			//History.newItem(FAOSTATCurrentView.HOME.name()); 
			CPFMenuController.openView(CPFCurrentView.HOME.name(), mainMenu);

		} else { 
			//Rebuild the language links based on the current history
			HTMLUtils.buildAndReplaceLanguageElement("language", token);
			
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

		System.out.println("history event ::: " + historyToken); 
		
		if (historyToken == null || historyToken.length() == 0) { 
			CPFMenuController.openView(CPFCurrentView.HOME.name(), mainMenu);
			//return; 
		} else {

		CPFHome.hideHomeVisibility();
		
		historyToken = parseHistoryToken(historyToken); 

		CPFMenuController.openView(historyToken, mainMenu);

		track(historyToken); 
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

	
}
	

