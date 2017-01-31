package org.fao.fenix.web.modules.venn.client;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.venn.client.view.BaseTool;
import org.fao.fenix.web.modules.venn.client.view.VennTypeChooseWindow;
import org.fao.fenix.web.modules.venn.client.view.VennWrapper;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Venn implements EntryPoint {

	BaseTool tool; 
	
	private final String HTML_ANCHOR = "venn";
	
	
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
		VennTypeChooseWindow window = new VennTypeChooseWindow();
		window.build();
		RootPanel.get(HTML_ANCHOR).add(window.getComplex());

	}
	
	private void execute(Map<String, String> paramsMap) {
		// type can be a gaulcode, or multicountry, or generic
		String type = "g";
		String width = "1275px";
		String height = "650px";
		String language = "EN";
		String xml = null;
		String countries = null;
		for (String paramName : paramsMap.keySet()) {
			System.out.println("paramnam: " + paramName);
			if ( !paramName.contains("gwt.codesvr")) {
				ParamName param = ParamName.valueOf(paramName);
				switch (param) {
					case type: type = paramsMap.get(paramName); break;
					case width: width = paramsMap.get(paramName); break;
					case countries: countries = paramsMap.get(paramName); break;
					case xml: xml = paramsMap.get(paramName); break;
					case height: height = paramsMap.get(paramName); break;
					case language: 
						language = paramsMap.get(paramName);
						if ((language == null) || language.equals(""))
							language = "EN";
					break;
				}
				
			}
		}
		
		System.out.println(xml + " | " + type + " | " + width + " | " + height + " | " + language);
		
		tool = new BaseTool();
		VennWrapper w = new VennWrapper();		
		tool.build(type, width, height, language, countries, xml);
		w.build(width, language, tool.getVennPortalPanel());
		w.getWrapper().add(tool.getToolBase());
		RootPanel.get(HTML_ANCHOR).add(w.getWrapper());
	}
	
	enum ParamName {
		type, countries, queryType, xml, width, height, language;
	}
	
}
