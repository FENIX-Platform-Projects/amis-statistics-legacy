package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare.FAOSTATCompareSelectionPanel;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

public class FAOSTATClosePanel {
	
	public static HorizontalPanel buildClose(ContentPanel panel) {
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
	
	
	
	public static SelectionListener<IconButtonEvent> hidePanel(final FAOSTATCompareSelectionPanel selectionPanel, final String code, final FieldSet panel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				hidePanelAgent(selectionPanel, code, panel);
			}
		};
	}
	
	public static Listener<ComponentEvent> hidePanelHtml( final FAOSTATCompareSelectionPanel selectionPanel, final String code, final FieldSet panel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				hidePanelAgent(selectionPanel, code, panel);
			}
		};
	}
	
	private static void hidePanelAgent(FAOSTATCompareSelectionPanel selectionPanel, String code, FieldSet panel) {
		selectionPanel.getDomainSelectionPanels().remove(code);
		panel.hide();
		
	}
	
	
	public static SelectionListener<IconButtonEvent> hidePanel(final ContentPanel panel) {
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
