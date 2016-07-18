package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATEmptyValuesPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuChart;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuFlatTable;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuPivotTable;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;


import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATBoxMakerFlatTable extends FAOSTATBoxMaker {

	public static ContentPanel build(final DWFAOSTATQueryVO qvo, final DWFAOSTATResultVO rvo, final String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.addStyleName("content_box");
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		
		p.add(FAOSTATBoxMenuFlatTable.buildMenu(qvo, rvo, width));
		
		panel.add(p);

		ContentPanel tablePanel = new ContentPanel();
		tablePanel.setBodyBorder(false);
		tablePanel.setHeaderVisible(false);
		tablePanel.setWidth(width);
//		tablePanel.setSize(width, height);
		
		tablePanel.setScrollMode(Scroll.AUTO);

		// TODO: if no table, write something? call another value in the hashmap if they are not finished?
		if ( rvo.getRows() == null ) {
			tablePanel.add(new Html("<div class=''> " + rvo.getTableHTML() + "</div>"));
		}
		else {
			if ( rvo.getRows() > 0 ) {
					tablePanel.add(new Html("<div class=''> " + rvo.getTableHTML() + "</div>"));
				}
			else {
				tablePanel.add(FAOSTATEmptyValuesPanel.build(FAOSTATLanguage.print().noDataAvailable(), width, height));
//				tablePanel.add(new Html("<div class=''>"+FAOSTATLanguage.print().noDataAvailableForCurrentSelection()+" </div>"));
	
			}
		}
		
		tablePanel.layout();

		panel.add(tablePanel);
		

		return panel;
	} 
	
	

	
}
