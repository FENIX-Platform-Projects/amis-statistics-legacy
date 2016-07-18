package org.fao.fenix.web.modules.edi.client.view.faostat;

import org.fao.fenix.web.modules.edi.client.view.EDIPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class FAOStatInfoPanel extends EDIPanel {

	private Html html = new Html(BabelFish.print().faostatManual());
	
	private VerticalPanel wrapper;
	
	public FAOStatInfoPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		wrapper = new VerticalPanel();
	}
	
	public TabItem build() {
		wrapper.setSpacing(getSPACING() + getSPACING());
		wrapper.add(this.getHtml());
		this.getPanel().add(wrapper);
		return this.getTabItem();
	}

	public Html getHtml() {
		return html;
	}
	
}