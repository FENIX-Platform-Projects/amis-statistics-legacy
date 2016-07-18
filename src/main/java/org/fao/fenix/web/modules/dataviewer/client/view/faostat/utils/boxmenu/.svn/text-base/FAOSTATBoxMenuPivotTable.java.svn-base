package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATChartController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FAOSTATBoxMenuPivotTable extends FAOSTATBoxMenu {
	
	public static ContentPanel buildMenu(ContentPanel tablePanel, DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String width){ 
		
		// main panel
		ContentPanel panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("content_pivot_top_panel");
	
		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	

		panelL.add(buildTitle(qvo, rvo, "pivot_table_title")); 
		
		panelR.add(buildClose(tablePanel)); 

		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.add(toolBar);

		return panel;
	}

	public static HorizontalPanel buildTitle(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, String titleStyle) {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
	
		panel.add(DataViewerClientUtils.addHSpace(2));
		
		panel.add(DataViewerClientUtils.addHSpace(2));
		
		String title = rvo.getTitle();

	
		panel.add(new Html("<div class='" + titleStyle + "'> " + title + " </div>"));
		
		return panel;
	}
	
	private static HorizontalPanel buildClose(ContentPanel panel) {
		HorizontalPanel p = new HorizontalPanel();
		p.addStyleName("closebuttonstyle");
		IconButton close = new IconButton("delete");
		close.addSelectionListener(hidePanel(panel));
		
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(close);
		ClickHtml html = new ClickHtml();
		html.setHtml("<div>"+FAOSTATLanguage.print().close()+"</div>");
		html.addListener(Events.OnClick, hidePanelHtml(panel));
		p.add(html);
		return p;
	}
	
	private static SelectionListener<IconButtonEvent> hidePanel(final ContentPanel panel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				hidePanelAgent(panel);
			}
		};
	}
	
	public static Listener<ComponentEvent> hidePanelHtml(final ContentPanel panel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				hidePanelAgent(panel);
			}
		};
	}
	
	private static void hidePanelAgent(ContentPanel panel) {
		panel.hide();
	}

}
