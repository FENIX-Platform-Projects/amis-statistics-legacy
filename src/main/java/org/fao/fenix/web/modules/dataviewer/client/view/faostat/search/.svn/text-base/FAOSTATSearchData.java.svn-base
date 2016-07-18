package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;


import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.utils.FAOSTATSearchOracleCache;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.byarea.FAOSTATVisualizeByArea;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic.FAOSTATVisualizeByTopic;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;


public class FAOSTATSearchData {
	
	ContentPanel panel;
	
	FAOSTATSearchTextBox textBox;
	
	
	
	
	public FAOSTATSearchData() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		panel.setStyleAttribute("background", "#FFFFFF");
		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.getMainContentHeight(false) + "px"));
//		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.MAIN_CONTENT_MIN_HEIGHT) + "px");

		
		textBox = new FAOSTATSearchTextBox();

//		panel.setStyleAttribute("min-height", "500px");
//		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATConstants.MAIN_CONTENT_MIN_HEIGHT) + "px");
	}

	
	public ContentPanel buildSearchData() {
		
		
		
		VerticalPanel p = new VerticalPanel();
//		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		p.add(new DataViewerClientUtils().addVSpace(35));
		p.add(buildTextBox());

//		p.add(buildAdditionalSpace());
		
		
		panel.add(p);
		
		return panel;
	}
	
	private HorizontalPanel buildTextBox() {
		HorizontalPanel p = new HorizontalPanel();
//		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		p.setWidth(FAOSTATConstants.INTERFACE_WIDTH);
		
		p.add(textBox.build());
		

		return p;
	}

}
