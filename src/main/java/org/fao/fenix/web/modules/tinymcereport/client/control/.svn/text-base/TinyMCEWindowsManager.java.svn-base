package org.fao.fenix.web.modules.tinymcereport.client.control;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEPanel;

public class TinyMCEWindowsManager {

	public static Map<String, TinyMCEPanel> map;
	
	static {
		map = new HashMap<String, TinyMCEPanel>();
	}
	
	public static void register(TinyMCEPanel p) {
		String code = generateCode();
		map.put(code, p);
		p.setCode(code);
	}
	
	private static String generateCode() {
		String code = "TinyMCE_" + String.valueOf(Double.MAX_EXPONENT + Math.random());
		if (!map.containsKey(code)) {
			return code;
		} else {
			return generateCode();
		}
	}
	
}