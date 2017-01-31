package org.fao.fenix.web.modules.ipc.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;



public class IPCUtils {
	
	

	public static List<ModuleVO> parseModules(Map<String, ListBox> dropDownMap, Map<String, RichTextArea> textAreaMap){
		List<ModuleVO> modulesVO = new ArrayList<ModuleVO>();
		Set<String> keySet = dropDownMap.keySet();
		System.out.println("DD");
		for(String key : keySet) {
			ListBox o = dropDownMap.get(key);
			System.out.println(key);// + " | " + o.getItemText(o.getSelectedIndex()));
		}
		
		System.out.println("Text");
		keySet = textAreaMap.keySet();
		for(String key : keySet) {
			RichTextArea o = textAreaMap.get(key);
			System.out.println(key);// + " | " + o.getHTML());
		}
		
	
		return modulesVO;
	}
	
}
