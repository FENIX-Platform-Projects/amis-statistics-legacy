package org.fao.fenix.web.modules.adam.client.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMTabMenuController;
import org.fao.fenix.web.modules.adam.client.control.ADAMURLController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMAnalyseDataController;
import org.fao.fenix.web.modules.adam.client.view.ADAMTabMenu;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;

public class ADAMHistory implements HistoryListener {

	// query string parameter we may use          
	private String id = null;          
	private String add = null;  
	private String locale = null;  
	private String recipientISO3 = null;  

	private ADAMTabMenu tabMenu;
	private ADAM adam;
	
	public static ADAMHistory historyListener;

	public ADAMHistory(ADAMTabMenu tabMenu, ADAM adam) {
		
		this.tabMenu = tabMenu;
		this.adam = adam;
		
		historyListener = this;
		
		// initialize history listener                  
		initHistorySupport();  

	}
	
	public void buildTabMenu(ADAMCurrentVIEW currentVIEW) {

		ADAMTabMenu.build(currentVIEW);
		
		History.addHistoryListener(this);

	}
	
	/** 
	 * init history support, start watching for changes in history 
	 *  
	 * use historyTokens (anchor tags) to navigate 
	 *  
	 * observe history changes (tokens) 
	 */ 
	public void initHistorySupport() { 
		
		System.out.println("initHistorySupport()");
		
		History.addHistoryListener(this); 

		// check to see if there are any tokens passed at startup via the anchor tag 
		String token = parseHistoryToken(History.getToken()); 
		

		System.out.println("initHistorySupport() "+token + " | token.length() "+token.length());
		if (token.length() == 0) { 
       	// navigate with anchors to home 
			ADAMController.openADAM("TAB1", ADAMCurrentVIEW.HOME, ADAMCurrentVIEW.HOME.name());
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

		System.out.println("onHistoryChanged history event ::: " + historyToken + " | "+historyToken.length()); 
		
		if (historyToken == null || historyToken.length() == 0) {  
			System.out.println(" onHistoryChanged historyToken is null || historyToken.length() == 0 ");
			
			// GOOGLE ANALYTICS
			//GoogleAnalyticsController.trackEvent(ADAMCurrentVIEW.HOME.toString(), "Access to " + ADAMCurrentVIEW.HOME.toString(), "");
			
			ADAMController.openADAM("TAB1", ADAMCurrentVIEW.HOME, ADAMCurrentVIEW.HOME.name());
		} 
		else {
			
			//selectedTab
			historyToken = parseHistoryToken(historyToken); 
			
			ADAMCurrentVIEW view = ADAMCurrentVIEW.getCurrentView(historyToken);
			String position = "";
			
			if(historyToken.contains(ADAMCurrentVIEW.ANALYSE.name()))
				position = "TAB3";
			else	{
			   position = ADAMTabMenuController.getPosition(view); 
			}
			if(view.name().contains(ADAMCurrentVIEW.ANALYSE.name()) || view.name().equals(ADAMCurrentVIEW.BROWSE.name()))
				view = ADAMCurrentVIEW.ADAMVIEW;
	
			//String position = ADAMTabMenuController.getPosition(view);
			
			
			/*if(historyToken.contains(ADAMCurrentVIEW.ANALYSE.name()))
				position = "TAB3";
			else	
			  position = ADAMTabMenuController.getPosition(view); 
			*/
			System.out.println("onHistoryChanged position ::: " + position); 
			System.out.println("onHistoryChanged ADAMController.currentVIEW ::: " + view); 
			
			// GOOGLE ANALYTICS
		//	GoogleAnalyticsController.trackEvent(historyToken.toString(), "Access to " + historyToken.toString(), "");
			
	        ADAMController.setTabAndCallView(position, view, historyToken);
		
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

			//get just the history token / anchor tag , not with parameters 
			historyToken = getHistoryToken(historyToken); 
			
			//use the parameters 
			setParams(params, historyToken); 

			//get just the history token / anchor tag , not with parameters 
			//historyToken = getHistoryToken(historyToken); 
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
		System.out.println("getHistoryTokenParameters: PARAMS " + params); 

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
	public String getHistoryToken(String historyToken) { 

		//skip if there is no question mark 
		if (!historyToken.contains("?")) { 
			return historyToken; 
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
	private void setParams(HashMap params, String historyToken) { 

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
			
		if (params.get("recipientISO3") != null) { 
			String code = (String)params.get("recipientISO3");
			List<String> codes = new ArrayList<String>();
			
			if(code.contains(",")){
				String[] arStr = code.substring(0, code.length()).split(","); 
				
				for (int i = 0; i < arStr.length; i++) { 
					codes.add(arStr[i]); 
				} 
			} else
				codes.add(code);
			
			System.out.println("setParams: codes "+ codes); 
			
			ADAMURLController.setISO3CountriesFilter(ADAMCurrentVIEW.ADAMVIEW, historyToken, codes);
			
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
	

