package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;


import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu.FAOSTATMenuController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.utils.FAOSTATSearchOracleCache;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Window;

public class FAOSTATSearchOracleOutput {
	
	
	
	
	public ContentPanel panel;
	
	FAOSTATSearchTextBox searchTextBox;
	
	
	public FAOSTATSearchOracleOutput() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.setHeight(FAOSTATSearchConstants.ORACLE_HEIGHT);
//		panel.setScrollMode(Scroll.AUTO);
		panel.hide();
	}
	
	public ContentPanel build(FAOSTATSearchTextBox searchTextBox) {
		panel.removeAll();
		
		this.searchTextBox = searchTextBox;
		
		return panel;
	}

	
	public void build(List<String> values) {
		panel.removeAll();
		panel.setScrollMode(Scroll.AUTO);

//		panel.setStyleName("oraclepanel");
//		panel.addStyleName("overflow-y: auto");

		HorizontalPanel p = new HorizontalPanel();
		p.add(new DataViewerClientUtils().addHSpace(FAOSTATSearchConstants.SEARCH_TEXT_ORACLE_LEFT_MARGIN));

		VerticalPanel v = new VerticalPanel();
		for(String value : values) {
			ClickHtml html = new ClickHtml();
			html.setHtml("<div class='oraclevalues'> "+ value + "</div>");
			html.addListener(Events.OnClick, FAOSTATSearchController.setTextBox(this, searchTextBox, value));
			v.add(html);
		}
		
		v.setHeight(FAOSTATSearchConstants.ORACLE_HEIGHT);
		v.setWidth(FAOSTATSearchConstants.SEARCH_TEXT_WIDTH);
		
		v.setBorders(true);
		v.setScrollMode(Scroll.AUTO);

		p.add(v);
		
		HorizontalPanel closePanel = new HorizontalPanel();
		closePanel.addStyleName("closebuttonstyle");
		
		IconButton close = new IconButton("delete");
		close.addSelectionListener(hidePanel());
		
		closePanel.add(close);
		ClickHtml html = new ClickHtml();
		html.setHtml("<div class='oraclevalues'>"+FAOSTATLanguage.print().close()+"</div>");
		html.addListener(Events.OnClick, hidePanelHtml());
		closePanel.add(html);
		
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(closePanel);
		
		panel.add(p);
		
		if ( values.size() > 0 ) {
			panel.show();
		}
		else
			panel.hide();
		
		panel.layout();
	}
	
	private SelectionListener<IconButtonEvent> hidePanel() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				hidePanelAgent();
			}
		};
	}
	
	public Listener<ComponentEvent> hidePanelHtml() {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				hidePanelAgent();
			}
		};
	}
	
	private void hidePanelAgent() {
		panel.hide();
	}
	
	

	public ContentPanel getPanel() {
		return panel;
	}
	
	
}
