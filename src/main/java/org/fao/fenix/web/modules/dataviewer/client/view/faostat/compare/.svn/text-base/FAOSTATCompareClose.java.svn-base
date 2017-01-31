package org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection.FAOSTATMultipleSelectionPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATClosePanel;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.FieldSet;

public class FAOSTATCompareClose extends FAOSTATClosePanel {
	
	public HorizontalPanel buildCloseDomain(FAOSTATCompareSelectionPanel selectionPanel, String code, FieldSet panel) {

		HorizontalPanel p = new HorizontalPanel();
		p.addStyleName("closebuttonstyle");
		IconButton close = new IconButton("delete");
		close.addSelectionListener(hidePanel(selectionPanel, code, panel));
		
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(close);
		ClickHtml html = new ClickHtml();
		html.setHtml("<div>"+FAOSTATLanguage.print().close()+"</div>");
		html.addListener(Events.OnClick, hidePanelHtml(selectionPanel, code, panel));

		p.add(html);
		return p;
	}
	
	public HorizontalPanel buildCloseDomain2(FAOSTATCompareSelectionPanel selectionPanel, String code, FieldSet panel) {

		HorizontalPanel p = new HorizontalPanel();
		p.addStyleName("closebuttonstyle");
//		IconButton close = new IconButton("delete");
//		close.addSelectionListener(hidePanel(selectionPanel, code, panel));
		
		p.add(DataViewerClientUtils.addHSpace(5));
//		p.add(close);
		ClickHtml html = new ClickHtml();
		html.setHtml("<div >"+FAOSTATLanguage.print().close()+"</div>");
		html.addListener(Events.OnClick, hidePanelHtml(selectionPanel, code, panel));

		p.add(html);
		return p;
	}

	
	
}
