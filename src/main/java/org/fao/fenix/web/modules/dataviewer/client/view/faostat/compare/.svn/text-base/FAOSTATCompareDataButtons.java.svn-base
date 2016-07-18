package org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareDataController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;


public class FAOSTATCompareDataButtons {
	
	ContentPanel panel;
	
	Button button;
	
	public FAOSTATCompareDataButtons() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
	}
	
	public ContentPanel build(FAOSTATCompareData compare) {
		HorizontalPanel h = new HorizontalPanel();
		h.setVerticalAlign(VerticalAlignment.MIDDLE);
		button = new Button("<b><font color='#33528C'>"+FAOSTATLanguage.print().showComparison()+"</font></b>");
		button.setIconStyle("showComparisonChartIcon");
		button.setHeight(30);
		button.addSelectionListener(FAOSTATCompareDataController.compareData(compare));
		h.add(button);
		panel.add(h);
		return panel;
	}

}
