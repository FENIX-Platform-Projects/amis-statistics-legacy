package org.fao.fenix.web.modules.ipc.client.view.dataentry;

import java.util.HashMap;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class Module {

	public ContentPanel panel;

	public Map<String, ListBox> dropDownMap;

//	public Map<String, RichTextArea> textAreaMap;
	public Map<String, HTML> textAreaMap;
	

	public Module() {
		panel = new ContentPanel();
		panel.setLayout(new FitLayout());
		panel.setScrollMode(Scroll.AUTO);
		panel.setHeaderVisible(false);
		dropDownMap = new HashMap<String, ListBox>();
//		textAreaMap = new HashMap<String, RichTextArea>();
		textAreaMap = new HashMap<String, HTML>();
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public Map<String, ListBox> getDropDownMap() {
		return dropDownMap;
	}

	public Map<String, HTML> getTextAreaMap() {
		return textAreaMap;
	}

//	public Map<String, RichTextArea> getTextAreaMap() {
//		return textAreaMap;
//	}
	
}