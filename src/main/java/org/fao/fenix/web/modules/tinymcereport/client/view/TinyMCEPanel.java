package org.fao.fenix.web.modules.tinymcereport.client.view;

import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEWindowsManager;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

public class TinyMCEPanel {

	private ContentPanel panel;
	
	private String code = "";
	
	private String WIDTH = "700px";
	
	private String HEIGHT = "475px";
	
	public TinyMCEPanel() {
		TinyMCEWindowsManager.register(this);
		panel = new ContentPanel(new FillLayout());
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setId(this.getCode());
	}
	
	public ContentPanel build() {
		return panel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}