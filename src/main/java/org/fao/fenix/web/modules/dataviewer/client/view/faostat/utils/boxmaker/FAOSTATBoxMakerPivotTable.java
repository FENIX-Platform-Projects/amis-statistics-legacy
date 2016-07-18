package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATEmptyValuesPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuChart;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuPivotTable;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;


import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATBoxMakerPivotTable extends FAOSTATBoxMaker {

	public static ContentPanel build(final DWFAOSTATQueryVO qvo, final DWFAOSTATResultVO rvo, final String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.add(FAOSTATBoxMenuPivotTable.buildMenu(panel, qvo, rvo, width));
		panel.add(DataViewerClientUtils.addVSpace(5));
		ContentPanel tablePanel = new ContentPanel();
		tablePanel.setBodyBorder(false);
		tablePanel.setHeaderVisible(false);
		if ( width != null )
			tablePanel.setWidth(width);
		if ( height != null) {
			// to avoid a too tall table
			if ( rvo.getRows() != null )
				if ( rvo.getRows() > 50 )
					tablePanel.setHeight(height);
		}
		
		tablePanel.setScrollMode(Scroll.AUTO);

		if ( rvo.getRows() == null ) {
			tablePanel.add(new Html("<div class=''> " + rvo.getTableHTML() + "</div>"));
		}
		else {
			if ( rvo.getRows() > 0 ) {
					tablePanel.add(new Html("<div class=''> " + rvo.getTableHTML() + "</div>"));
				}
			else {
				tablePanel.add(FAOSTATEmptyValuesPanel.build(FAOSTATLanguage.print().noDataAvailable(), width, null));
			}
		}
		
		tablePanel.layout();
		panel.add(tablePanel);

		return panel;
	} 
	
	
}
