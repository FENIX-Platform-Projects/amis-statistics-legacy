package org.fao.fenix.web.modules.adam.client.view.makers;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class ADAMEmptyValuesPanel {

	
	public static ContentPanel build(String title, String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		panel.setWidth(width);
		panel.setHeight(height);
		
//		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
//		panel.setHorizontalAlign(HorizontalAlignment.CENTER);

		panel.addStyleName("empty-values-panel");
		panel.add(addVSpace(7));
		//Html html = new Html("<div class='empty-values-title'>No Data available for the current selection</div>");
		Html html = new Html("<div align='center' style='font-size: 12px; color: #17376D;'><b>No Data available for the current selection.</b></div>");
		
		panel.add(html);
		
		
		return panel;
	}
	
	private static HorizontalPanel addVSpace(Integer height) {
		HorizontalPanel p = new HorizontalPanel();
		p.setHeight(height);
		return p;
	} 
	
	public static ContentPanel build(String title) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		//panel.setWidth(width);
		//panel.setHeight(height);
		
//		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
//		panel.setHorizontalAlign(HorizontalAlignment.CENTER);

		panel.addStyleName("empty-values-panel");
	
		//Html html = new Html("<div class='small-empty-values-title'>"+title+"</div>");
		Html html = new Html("<div align='center' style='font-size: 12px; color: #17376D;'><b>"+title+"</b></div>");
		
		panel.add(html);
		panel.add(addVSpace(7));
		
		
		return panel;
	}
}
