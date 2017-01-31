package org.fao.fenix.web.modules.amis.client.view.utils.layout;

import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class EmptyValuesPanel {


	public static ContentPanel build(String width, String height, AMISQueryVO qvo) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("padding", "10px");
		
		panel.addStyleName("empty-values-panel");
		panel.add(FormattingUtils.addVSpace(7));
		Html html = new Html();
		
		
		if(qvo!=null && qvo.getTitle()!=null && qvo.getTitle()!="")
			html.setHtml("<div class='empty-values-panel'>No Data available for "+qvo.getTitle()+"</div>");
		else
			html.setHtml("<div class='empty-values-panel'>No Data available for the current selection</div>");


		panel.add(html);

		return panel;
	}
	
	
	public static ContentPanel build(String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("padding", "10px");
		
		panel.addStyleName("empty-values-panel");
		panel.add(FormattingUtils.addVSpace(7));
		Html html = new Html();
		
		
		html.setHtml("<div class='empty-values-panel'>No Data available for the current selection</div>");


		panel.add(html);

		return panel;
	}
	
	
	public static ContentPanel build(String width, String height, String message) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("padding", "10px");
	
		panel.addStyleName("empty-values-panel");
		panel.add(FormattingUtils.addVSpace(7));
		Html html = new Html();
		
		html.setHtml("<div class='empty-values-panel'> "+message+"</div>");
		
		//html.setWidth("30px");
		//panel.setWidth("40px");
	//	panel.setWidth("1030px");
		panel.add(html);

		return panel;
	}

}
