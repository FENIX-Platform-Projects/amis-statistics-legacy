package org.fao.fenix.web.modules.haiti.client;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.haiti.client.view.HaitiTabPanel;
import org.fao.fenix.web.modules.haiti.client.view.HaitiWrapper;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Haiti implements EntryPoint {

	private HaitiTabPanel haitiTabPanel;
	
	private final String HTML_ANCHOR = "haiti_00";
	
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
		haitiTabPanel = new HaitiTabPanel("");
		HaitiWrapper w = new HaitiWrapper();
		w.build(null, null);
		w.getWrapper().add(haitiTabPanel.build(null));
		RootPanel.get(HTML_ANCHOR).add(w.getWrapper());
//		FenixAlert.info("Suggestion", "Try to select '<i>Haiti departments (Level 1)</i>' or '<i>Haiti Country Boundaries</i>' layer on your left, then click on the map to visualize available resources(s): Texts, Datasets and Charts.");
	}
	
	private void execute(Map<String, String> paramsMap) {
		String gaul0code = null;
		String width = "1275px";
		String height = "650px";
		String language = "en";
		boolean showNews = false;
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
				}
			}
			catch (Exception e) {
			}
		}
		haitiTabPanel = new HaitiTabPanel(language);
		HaitiWrapper w = new HaitiWrapper();
		w.build(width, language);
		w.getWrapper().add(haitiTabPanel.build(gaul0code, width, height, language));
		RootPanel.get(HTML_ANCHOR).add(w.getWrapper());
	}
	
	enum ParamName {
		country, width, height, language, showNews;
	}
	
}
