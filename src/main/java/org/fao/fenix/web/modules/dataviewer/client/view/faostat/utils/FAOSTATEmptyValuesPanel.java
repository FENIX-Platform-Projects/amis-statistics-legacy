package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class FAOSTATEmptyValuesPanel {

	
	public static ContentPanel build(String title, String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		panel.setWidth(width);
		
		if ( height != null )
			panel.setHeight(height);


		panel.addStyleName("empty-values-panel");
		panel.add(DataViewerClientUtils.addVSpace(7));
		Html html = new Html("<div class='empty-values-panel'>"+FAOSTATLanguage.print().noDataAvailableForCurrentSelection()+"</div>");
		
		panel.add(html);
		
		
		return panel;
	}
	
	
//	public static ContentPanel build(String title, String text, String width, String height) {
//		ContentPanel panel = new ContentPanel();
//		panel.setBodyBorder(false);
//		panel.setHeaderVisible(false);
//		
//		panel.setWidth(width);
//		panel.setHeight(height);
//
//
//		panel.addStyleName("empty-values-panel");
//		
//		Html titleHtml = new Html("<div class='empty-values-panel'>"+ title +"</div>");
//		
//		
//		Html textHtml = new Html("<div class='empty-values-panel'>"+ text +"</div>");
//		
//		panel.add(titleHtml);
//		panel.add(textHtml);
//		panel.add(DataViewerClientUtils.addVSpace(7));
//		
//		
//		return panel;
//	}
}
