package org.fao.fenix.web.modules.cpf.client.view.utils.layout;

import org.fao.fenix.web.modules.cpf.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.cpf.common.vo.CPFQueryVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class EmptyValuesPanel {


	public static ContentPanel build(String width, String height, CPFQueryVO qvo) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("padding", "10px");

		//panel.setWidth(width);
		//panel.setHeight(100);

		panel.addStyleName("empty-values-panel");
		panel.add(FormattingUtils.addVSpace(7));
		Html html = new Html();
		
		if(qvo.getTitle()!=null && qvo.getTitle()!="")
			html.setHtml("<div class='empty-values-panel'>No Data available for "+qvo.getTitle()+"</div>");
		else
			html.setHtml("<div class='empty-values-panel'>No Data available for the current selection</div>");

		
		panel.add(html);


		return panel;
	}

}
