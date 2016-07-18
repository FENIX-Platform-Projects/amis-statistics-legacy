package org.fao.fenix.web.modules.bangladesh.client.view;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;

public class BangladeshPreviewTab {

	private ContentPanel panel;
	private ContentPanel panel1;
	private ContentPanel panel2;
	private ContentPanel panel3;
	private ContentPanel panel4;
	
	BangladeshWindow window;
	
	public BangladeshPreviewTab(BangladeshWindow bangladeshWindow) {
		this.window = bangladeshWindow;
		panel = new ContentPanel();
		panel.setScrollMode(Scroll.AUTO);
//		panel1 = new ContentPanel();
//		panel2 = new ContentPanel();
//		panel3 = new ContentPanel();
//		panel4 = new ContentPanel();
	}

	
	public ContentPanel build(BangladeshWindow bangladeshWindow) {	
//		panel.setBottomComponent(buildButtons());
		return panel;
	}

	public ContentPanel getPanel() {
		return panel;
	}


	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}


	public BangladeshWindow getWindow() {
		return window;
	}
	
	
	
	
}