package org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmaker;

import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu.AMISBoxMenuFlatTable;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;


import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class AMISBoxMakerFlatTable extends BoxMaker {

	public static ContentPanel build(final AMISQueryVO qvo, final AMISResultVO rvo, final String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBorders(false);
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
//		panel.addStyleName("content_box");
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		p.setStyleAttribute("backgroundColor", "#FFFFFF");
		
		p.add(AMISBoxMenuFlatTable.buildMenu(qvo, rvo, width));
		
		panel.add(p);

		ContentPanel tablePanel = new ContentPanel();
		tablePanel.setBodyBorder(false);
		tablePanel.setHeaderVisible(false);
		tablePanel.setBorders(false);
		tablePanel.setWidth(width);
		tablePanel.setStyleAttribute("backgroundColor", "#FFFFFF");
//		tablePanel.setSize(width, height);
		
		//tablePanel.setScrollMode(Scroll.AUTO);

		// TODO: if no table, write something? call another value in the hashmap if they are not finished?
		if ( rvo.getRows() == null ) {
			tablePanel.add(new Html("<div class=''> " + rvo.getTableHTML() + "</div>"));
		}
		else {
			if ( rvo.getRows() > 0 ) {
					tablePanel.add(new Html("<div style='background:#FFFFFF;'> " + rvo.getTableHTML() + "</div>"));
				}
			else {
				tablePanel.add(new Html("<div style='background:#FFFFFF;'> No data available for the current selection. </div>"));
	
			}
		}
		
		tablePanel.layout();

		panel.add(tablePanel);
		

		return panel;
	} 
	
	

	
}
