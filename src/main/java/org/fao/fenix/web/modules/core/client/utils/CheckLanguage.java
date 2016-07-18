package org.fao.fenix.web.modules.core.client.utils;

import com.google.gwt.i18n.client.LocaleInfo;

public class CheckLanguage {
	
	private static native String getParam() /*-{
  	return $wnd.location.search;
	}-*/;

//	public static String getLanguage(){
//		String lang = getParam();
//		if ( !lang.isEmpty()) 
//			lang = lang.substring(lang.indexOf("=") +1, lang.length());
//		else
//			lang = "EN";
//		return lang.toUpperCase();
//	}
	
	public static String getLanguage() { 		
		String language = LocaleInfo.getCurrentLocale().getLocaleName();
		if ( language.equals("default"))
			language = "EN";
		return language.toUpperCase();
	} 

}
