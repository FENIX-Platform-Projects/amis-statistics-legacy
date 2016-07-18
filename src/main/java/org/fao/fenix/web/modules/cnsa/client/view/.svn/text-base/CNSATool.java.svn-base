package org.fao.fenix.web.modules.cnsa.client.view;


import com.extjs.gxt.ui.client.widget.ContentPanel;

public class CNSATool {
	
//	private VennPortalPanel vennPortalPanel;
	
	private ContentPanel panel;
	
	String width;
	
	String height;
	
	String language;
	
	
	public CNSATool() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setBorders(false);
	}
	
	public void build() {
	}
	
	
	public void build(String type, String height, String width, String language) {
		this.width = width;
		this.height = height;
		this.language = language;
		
		panel.setWidth(width);
		panel.setHeight(height);
		
		if ( type.equals("report") || type.equals("bydate"))
			buildReportPanel(type);
		

	}
	
	private void buildReportPanel(String type) {
		CNSAReportsSelection reportPanel = new CNSAReportsSelection();
		panel.add(reportPanel.build(type, height, width, language));
	}
	

	


	public ContentPanel getPanel() {
		return panel;
	}
	

	
}