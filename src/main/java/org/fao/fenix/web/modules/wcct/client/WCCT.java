package org.fao.fenix.web.modules.wcct.client;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.wcct.client.view.WCCTTool;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class WCCT implements EntryPoint {

//	public void onModuleLoad() {
//		
//		// logos
//		Image giewsLogo = new Image("rainfall-images/giews_logo_blue.png");
////		Image faoLogo = new Image("rainfall-images/fao_logo_blue.png");
////		Image ueLogo = new Image("rainfall-images/UE_logo_blue.png");
//		Image uefaoLogo = new Image("rainfall-images/fao_ue_logo_blue.png");
//		RootPanel.get("giewsLogo").add(giewsLogo);
////		RootPanel.get("ueLogo").add(ueLogo);
////		RootPanel.get("faoLogo").add(faoLogo);
////		RootPanel.get("uefaoLogo").add(uefaoLogo);
//		
//		
//		// tool
//		WCCTTool tool = new WCCTTool();
//		tool.build();
//		RootPanel.get("wcct").add(tool.getToolBase());
//	}
	
	
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
			execute();
		} else {
			execute();
		}
	}
	
	private void execute() {
		// logos
		Image giewsLogo = new Image("rainfall-images/giews_logo_blue.png");
//		Image faoLogo = new Image("rainfall-images/fao_logo_blue.png");
//		Image ueLogo = new Image("rainfall-images/UE_logo_blue.png");
		Image uefaoLogo = new Image("rainfall-images/fao_ue_logo_blue.png");
		RootPanel.get("giewsLogo").add(giewsLogo);
//		RootPanel.get("ueLogo").add(ueLogo);
//		RootPanel.get("faoLogo").add(faoLogo);
//		RootPanel.get("uefaoLogo").add(uefaoLogo);
		
		
		// tool
		WCCTTool tool = new WCCTTool();
		tool.build();
		RootPanel.get("wcct").add(tool.getToolBase());
	}
	

	
}
