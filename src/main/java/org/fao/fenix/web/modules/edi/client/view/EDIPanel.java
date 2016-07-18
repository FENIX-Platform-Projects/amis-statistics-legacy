package org.fao.fenix.web.modules.edi.client.view;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

public abstract class EDIPanel {

	private ContentPanel panel;
	
	private TabItem tabItem;
	
	private int SPACING = 3;
	
	private  String FIELD_WIDTH = "350px";
	
	private  String LABEL_WIDTH = "350px";
	
	private  String BUTTON_WIDTH = "180px";
	
	public EDIPanel(String tabItemHeader, String iconStyle) {
		panel = new ContentPanel(new FillLayout());
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		tabItem = new TabItem(tabItemHeader);
		tabItem.add(panel);
		tabItem.setIconStyle(iconStyle);
	}
	
	public TabItem build() {
		return tabItem;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public int getSPACING() {
		return SPACING;
	}

	public String getFIELD_WIDTH() {
		return FIELD_WIDTH;
	}

	public String getLABEL_WIDTH() {
		return LABEL_WIDTH;
	}

	public String getBUTTON_WIDTH() {
		return BUTTON_WIDTH;
	}

	public void setSPACING(int sPACING) {
		SPACING = sPACING;
	}

	public void setFIELD_WIDTH(String fIELDWIDTH) {
		FIELD_WIDTH = fIELDWIDTH;
	}

	public void setLABEL_WIDTH(String lABELWIDTH) {
		LABEL_WIDTH = lABELWIDTH;
	}

	public void setBUTTON_WIDTH(String bUTTONWIDTH) {
		BUTTON_WIDTH = bUTTONWIDTH;
	}

	public TabItem getTabItem() {
		return tabItem;
	}

	public void setTabItem(TabItem tabItem) {
		this.tabItem = tabItem;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}
	
}