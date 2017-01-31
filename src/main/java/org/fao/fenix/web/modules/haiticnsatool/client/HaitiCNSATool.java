package org.fao.fenix.web.modules.haiticnsatool.client;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class HaitiCNSATool implements EntryPoint {

	
	private final String HTML_ANCHOR = "haiticnsatool_00";
	
	public void onModuleLoad() {
		parseParams(getParamString());
	}
	
	private native String getParamString() /*-{
		return $wnd.location.search;
	}-*/;
	
	private void parseParams(String params) {
		System.out.println("params: " + params);
		
		
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
	
//		FenixAlert.info("Suggestion", "Try to select '<i>Haiti departments (Level 1)</i>' or '<i>Haiti Country Boundaries</i>' layer on your left, then click on the map to visualize available resources(s): Texts, Datasets and Charts.");
	}
	
	private void execute(Map<String, String> paramsMap) {
		
		System.out.println("HERE HAITI CNSA TOOL");
		String gaul0code = null;
		String width = "1275px";
		String height = "650px";
		String language = "en";
		String dekads = "1";
		boolean showNews = false;
		boolean CNSATool = false;
		for (String paramName : paramsMap.keySet()) {
			try {
			ParamName param = ParamName.valueOf(paramName);
			
				
				switch (param) {
					case country: gaul0code = paramsMap.get(paramName); break;
					case width: width = paramsMap.get(paramName); break;
					case height: height = paramsMap.get(paramName); break;
					case language: 
						language = paramsMap.get(paramName);
						if ((language == null) || language.equals(""))
							language = "en";
					break;
					case showNews: showNews = Boolean.parseBoolean(paramsMap.get(paramName)); break;
					case CNSATool: CNSATool = Boolean.parseBoolean(paramsMap.get(paramName)); break;
					case dekads: dekads = paramsMap.get(paramName); break;
				}
			}
			catch (Exception e) {
			}
		}
		
		

			HaitiCNSAPanel panel = new HaitiCNSAPanel(gaul0code, width, height, language.toUpperCase(), Integer.valueOf(dekads));
			panel.build();
			RootPanel.get(HTML_ANCHOR).add(panel.getToolBase());
		
	}
	
	enum ParamName {
		country, width, height, language, showNews, CNSATool, dekads;
	}
	
}
