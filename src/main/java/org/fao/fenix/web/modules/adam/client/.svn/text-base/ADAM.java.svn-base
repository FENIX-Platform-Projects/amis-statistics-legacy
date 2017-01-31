package org.fao.fenix.web.modules.adam.client;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMBoxController;
import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMURLController;
import org.fao.fenix.web.modules.adam.client.control.ADAMURLInterceptor;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.history.ADAMHistory;
import org.fao.fenix.web.modules.adam.client.lang.ADAMLanguage;
import org.fao.fenix.web.modules.adam.client.view.ADAMTabMenu;
import org.fao.fenix.web.modules.adam.client.view.ADAMToolbar;
import org.fao.fenix.web.modules.adam.client.view.ADAMWrapper;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMURLParameters;
import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.json.client.JSONArray;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Image;

import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.i18n.client.LocaleInfo;

public class ADAM implements EntryPoint {

	private ADAMToolbar adamToolbar;
	private ADAMTabMenu adamTabMenu;
	
	private static ADAMWrapper adamWrapper;
	
	ADAMHistory history;
	
	//public static Map<ADAMURLParameters, List<String>> parameters;
	
	public ADAM() {
		adamToolbar = new ADAMToolbar();
		adamTabMenu = new ADAMTabMenu();
		history = new ADAMHistory(adamTabMenu, this);


		adamWrapper = new ADAMWrapper("EN");
		//parameters = new HashMap<ADAMURLParameters, List<String>>();
	}
	
	
	public void onModuleLoad() {
		execute();
		//parseParams(getParamString());
	}
	
	private native String getParamString() /*-{
		return $wnd.location.search;
	}-*/;
	
/*	private void parseParams(String params) {
//		System.out.println("params: " + params);
		
		
		if (params.length() > 0) {
			ADAMURLInterceptor.parseParams(params);
		}

		
		// execute standard ADAM
		if ( parameters.isEmpty() ) {
			execute();
		}
		
		// execute ADAM with parameters
		else {
			
			executeWithParameters();
		}
	}
	*/
	
//	public void onModuleLoad() {
//		
//		// logo
//		PushButton logo = new PushButton(new Image("adam-images/ADAM_LOGO.png"));
//		logo.addClickHandler(ADAMController.globalLOGIN(adamWrapper));
//		RootPanel.get("LOGO").add(logo);
//		
//		// menu corners
//		Image leftCorner = new Image("adam-images/MENU_LEFT_CORNER.png");
//		Image rightCorner = new Image("adam-images/MENU_RIGHT_CORNER.png");
//		
//		// menu
//		RootPanel.get("MAIN_MENU").add(adamToolbar.buildMainMenuBarLOGIN());
//		RootPanel.get("MENU_LEFT_CORNER").add(leftCorner);
//		RootPanel.get("MENU_RIGHT_CORNER").add(rightCorner);
//		
//		// boxes
////		ADAMController.globalAgent(adamWrapper);
//		
//		
//	}
	
//	BACKUP
	
	
//	@org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow::show(Ljava/lang/String;)(html);

	
	public static native void drawMapJS(JSONArray countriesCode, JSONArray quantity, JSONArray countriesLabel, String width, String height, String position)/*-{
		///////////////////// JSONArray
		var size =      countriesCode.@com.google.gwt.json.client.JSONArray::size()();	
	//	alert("size: " + size);
		
		var countries = new Array();
	 	var values = new Array();
	 	var countriesL = new Array();
		
	//	var k = countriesCode.@com.google.gwt.json.client.JSONArray::jsArray[0];
	//	alert("keyset: " + k);
		
		for(var index = 0; index < size; index++) {
			var c = countriesCode.@com.google.gwt.json.client.JSONArray::jsArray[index];
			var v = quantity.@com.google.gwt.json.client.JSONArray::jsArray[index];
			var l = countriesLabel.@com.google.gwt.json.client.JSONArray::jsArray[index];
			
	//		alert("value: " + c + " | " + v + " | " + l );
			countries[index] = c;
			values[index] = v;
			countriesL[index] = l;
		}
	//	var i = 0;
	//	var keyset = args.@com.google.gwt.json.client.JSONArray::get(Ljava/lang/Integer;)(i);	
		
	//	alert("keyset.length: " + keyset);
	
		////////////////////////////
		
		
	
		
	//	alert(countries.length);
	//	alert(values.length);
		
		$wnd.countries = countries;
		$wnd.values = values;
		$wnd.countriesLabel = countriesL;
	
	
		
		//	alert("here2: "); 
		$wnd.map_width = width;
	    $wnd.map_height = height;
	    $wnd.map_position = position;
		
		$wnd.drawMap();
		
		
	
	     //alert("matix:" + matrix);
	
	 
	}-*/;
	
	
	
	public void execute() {
	
		System.out.println("%%%%%%%%%%%%%%%%%%% CURRENT LOCALE: "+LocaleInfo.getCurrentLocale().getLocaleName());
		
		//GOOGLE Analytics
		//GoogleAnalyticsController.trackEvent("Language Selection", LocaleInfo.getCurrentLocale().getLocaleName(), "");	
		
//		JSONArray obj= new JSONArray();
//		JSONString a = new JSONString("AF");
//		JSONString b = new JSONString("IT");
//		obj.set(0, a);
//		obj.set(1, b);
//		 
//		JSONArray obj2= new JSONArray();
//		JSONNumber a2 = new JSONNumber(10);
//		JSONNumber b2 = new JSONNumber(2222.30);
//		obj2.set(0, a2);
//		obj2.set(1, b2);
//		
//		JSONArray obj3 = new JSONArray();
//		JSONString a3 = new JSONString("Afga");
//		JSONString b3 = new JSONString("ITalia");
//		obj3.set(0, a3);
//		obj3.set(1, b3);
//		
//		System.out.println(obj + " | " + obj2);
//		drawMapJS(obj, obj2, obj3, "400px", "300px", "CENTER");
		
	
		
//		Window.addWindowClosingHandler(new ClosingHandler(){
//				public void onWindowClosing(ClosingEvent arg0) {
//					 System.out.println("cleaning function");
//					 ADAMController.cleanLayersAndViews();
//				}
//			});
//
//		
		ADAMBoxController.resizeObjects();
		
		// FAO Label
		Html faoTitle = new Html("<a href='http://www.fao.org' target='_blank' class='link-fao'>Food and Agriculture Organization of the United Nations - for a world without hunger</a>");
		//RootPanel.get("FAO_TITLE").add(faoTitle);
		
		
		// FAO logo
		Html faoLogo = new Html();
		faoLogo.setHtml("<a href='http://www.fao.org' target='_blank'> <img src='adam-images/FAO.png' title='Food and Agriculture Organization of the United Nations'/></a>");
		
		//PushButton faoLogo = new PushButton(new Image("adam-images/FAO.png"));
//		faoLogo.addClickHandler(ADAMController.global(adamWrapper));
		//RootPanel.get("FAO_LOGO").add(faoLogo);
		
		// ADAM logo
		String logoPath;
		if(LocaleInfo.getCurrentLocale().getLocaleName().equals("es"))
			logoPath = "adam-images/es/adam_logo.png";
		else if (LocaleInfo.getCurrentLocale().getLocaleName().equals("fr"))
			 logoPath = "adam-images/fr/adam_logo.png";
		else
				logoPath = "adam-images/adam_logo.png";
		
		System.out.println("logoPath: "+logoPath);
		
		PushButton adamLogo = new PushButton(new Image(logoPath));
		adamLogo.setTitle("ADAM");
		adamLogo.setStyleName("pointer");
		adamLogo.addClickHandler(ADAMController.global(adamWrapper));
		RootPanel.get("ADAM_LOGO").add(adamLogo);
		
		// ADAM write
	//	PushButton adamText = new PushButton(new Image("adam-images/ADAMtext+.png"));
	//	RootPanel.get("ADAM_WRITE").add(adamText);
		
		// ADAM write
		//PushButton adamText = new PushButton(new Image("adam-images/ADAMTitle.png"));
//		adamLogo.addClickHandler(ADAMController.global(adamWrapper));
		//RootPanel.get("ADAM_WRITE").add(adamText);
		
		// menu
		RootPanel.get("MAIN_MENU").add(adamToolbar.buildMainMenuBar());
		
		if(History.getToken().length() == 0) {
			history.buildTabMenu(ADAMCurrentVIEW.HOME);
			ADAMController.globalAgentHome(adamWrapper);
		}	
		else {
			//parsedHistoryToken = get the exact history token ... remove any URL parameters
			System.out.println("execute() ADAMHistory.historyListener = "+ADAMHistory.historyListener);
			String parsedHistoryToken = ADAMHistory.historyListener.getHistoryToken(History.getToken());
			System.out.println("execute() parsedHistoryToken = "+parsedHistoryToken + "historyToken "+History.getToken());
			history.buildTabMenu(ADAMCurrentVIEW.getCurrentView(parsedHistoryToken));
		}
	
		
	}
	
	
	/*public void executeWithParameters() {
		
		System.out.println("EXECUTE WITH PARAMETERS");
		
		
		///
        ADAMBoxController.resizeObjects();
		
		// FAO Label
		Html faoTitle = new Html("<a href='http://www.fao.org' target='_blank' class='link-fao'>Food and Agriculture Organization of the United Nations - for a world without hunger</a>");
		//RootPanel.get("FAO_TITLE").add(faoTitle);
		
		
		// FAO logo
		Html faoLogo = new Html();
		faoLogo.setHtml("<a href='http://www.fao.org' target='_blank'> <img src='adam-images/FAO.png' title='Food and Agriculture Organization of the United Nations'/></a>");
		
		//PushButton faoLogo = new PushButton(new Image("adam-images/FAO.png"));
//		faoLogo.addClickHandler(ADAMController.global(adamWrapper));
		//RootPanel.get("FAO_LOGO").add(faoLogo);
		
		// ADAM logo
		String logoPath;
		if(LocaleInfo.getCurrentLocale().getLocaleName().equals("es"))
			logoPath = "adam-images/es/adam_logo.png";
		else if (LocaleInfo.getCurrentLocale().getLocaleName().equals("fr"))
				logoPath = "adam-images/fr/adam_logo.png";
		else
				logoPath = "adam-images/adam_logo.png";
		
		PushButton adamLogo = new PushButton(new Image(logoPath));
		
		adamLogo.setTitle(ADAMLanguage.print().home());
		adamLogo.setStyleName("pointer");
		adamLogo.addClickHandler(ADAMController.global(adamWrapper));
		RootPanel.get("ADAM_LOGO").add(adamLogo);
		
		// ADAM write
	//	PushButton adamText = new PushButton(new Image("adam-images/ADAMtext+.png"));
	//	RootPanel.get("ADAM_WRITE").add(adamText);
		
		// ADAM write
		//PushButton adamText = new PushButton(new Image("adam-images/ADAMTitle.png"));
//		adamLogo.addClickHandler(ADAMController.global(adamWrapper));
		//RootPanel.get("ADAM_WRITE").add(adamText);
		
		// menu
		RootPanel.get("MAIN_MENU").add(adamToolbar.buildMainMenuBar());
		
		//
		//ADAMTabMenu.build(ADAMCurrentVIEW.HOME);

		
		// boxes
		//ADAMController.globalAgentHome(adamWrapper);
		
		
		//ADAMURLController.createView();
		
		
		///
	
		
		// FAO logo
	*//***	PushButton faoLogo = new PushButton(new Image("adam-images/faologo.png"));
//		faoLogo.addClickHandler(ADAMController.global(adamWrapper));
		RootPanel.get("FAO_LOGO").add(faoLogo);
		
		// ADAM logo
		PushButton adamLogo = new PushButton(new Image("adam-images/adamLogo.png"));
		adamLogo.addClickHandler(ADAMController.global(adamWrapper));
		RootPanel.get("ADAM_LOGO").add(adamLogo);
		
		// ADAM write
		PushButton adamText = new PushButton(new Image("adam-images/ADAMtext+.png"));
//		adamLogo.addClickHandler(ADAMController.global(adamWrapper));
		//RootPanel.get("ADAM_WRITE").add(adamText);
		
		// menu
		RootPanel.get("MAIN_MENU").add(adamToolbar.buildMainMenuBar());
//		ADAMTabMenu.build();

		ADAMURLController.createView();
		
		// boxes
//		ADAMController.globalAgentHome(adamWrapper);
		
		ADAMVisibilityController.setDefaultNoDisplayObjects();
		
		**//*
		
	}*/
	
	


	public static ADAMWrapper getAdamWrapper() {
		return adamWrapper;
	}


	public ADAMHistory getHistory() {
		return history;
	}


	public void setHistory(ADAMHistory history) {
		this.history = history;
	}
	
	

	
}