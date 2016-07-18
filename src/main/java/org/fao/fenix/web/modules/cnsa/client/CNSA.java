package org.fao.fenix.web.modules.cnsa.client;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.cnsa.client.view.CNSATool;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.RootPanel;

public class CNSA {

	CNSATool tool; 
	
	private final String HTML_ANCHOR = "cnsa";
	
	
	public void onModuleLoad() {
		parseParams(getParamString());
	}
	
	private native String getParamString() /*-{
		return $wnd.location.search;
	}-*/;
	
	private void parseParams(String params) {
		if (params.length() > 0) {
			Map<String, String> paramsMap = new HashMap<String, String>();
			for (int i = 0; i < params.length(); i++) {
				int idx_1 = params.indexOf('=', i);
				int idx_2 = params.indexOf('&', idx_1);
				String name = params.substring(1 + i, idx_1);
				String value = null;
				if (idx_2 > 0)
					value = params.substring(1 + idx_1, idx_2);
				else
					value = params.substring(1 + idx_1);
				paramsMap.put(name, value);
				i = idx_2 - 1;
				if (i > 0)
					continue;
				else
					break;
			}
			execute(paramsMap);
		} else {
			execute();
		}
	}
	
	private void execute() {

		
		// open choosing window

	}
	
	private void execute(Map<String, String> paramsMap) {
		// type can be a gaulcode, or multicountry, or generic
		String type = "g";
		String width = "1275px";
		String height = "650px";
		String language = "en";
		for (String paramName : paramsMap.keySet()) {
			System.out.println("paramnam: " + paramName);
			if ( !paramName.contains("gwt.codesvr")) {
				ParamName param = ParamName.valueOf(paramName);
				switch (param) {
					case type: type = paramsMap.get(paramName); break;
					case width: width = paramsMap.get(paramName); break;
					case height: height = paramsMap.get(paramName); break;
					case language: 
						language = paramsMap.get(paramName);
						if ((language == null) || language.equals(""))
							language = "en";
					break;
				}
				
			}
		}
		System.out.println(type + " | " + width + " | " + height + " | " + language);
		tool = new CNSATool();
//		tool.build();
		
		tool.build(type, height, width, language);
		
//		RootPanel.get(HTML_ANCHOR).add(new Html("asdoijasoidj"));

		RootPanel.get(HTML_ANCHOR).add(tool.getPanel());
		
	}
	
	enum ParamName {
		type, country, width, height, language;
	}
	
}
