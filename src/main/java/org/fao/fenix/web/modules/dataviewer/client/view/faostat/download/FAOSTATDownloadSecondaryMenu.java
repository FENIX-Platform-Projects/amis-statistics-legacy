package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu.FAOSTATMenuController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

//public class FAOSTATVisualizeSecondaryMenu extends FAOSTATSecondaryMenu {
public class FAOSTATDownloadSecondaryMenu {

	ContentPanel panel;
	HorizontalPanel secondaryMenuPanel;

	public FAOSTATDownloadSecondaryMenu() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		secondaryMenuPanel = new HorizontalPanel();
	}

	// setting the current view
	public ContentPanel build(FAOSTATCurrentView currentView) {
		panel.addStyleName("secondary-menu-box-style");
		secondaryMenuPanel.setSpacing(10);
		secondaryMenuPanel.addStyleName("secondary-menu-box-style");
		
//		secondaryMenuPanel.add(DataViewerClientUtils.addHSpace(5));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().downloadStandard(), FAOSTATCurrentView.DOWNLOAD_STANDARD, currentView, 250));
		secondaryMenuPanel.add(DataViewerClientUtils.addHSpace(10));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().downloadBulk(), FAOSTATCurrentView.DOWNLOAD_BULK, currentView, 200));
//		secondaryMenuPanel.add(DataViewerClientUtils.addHSpace(10));
		
		panel.add(DataViewerClientUtils.addVSpace(3));
		panel.add(secondaryMenuPanel);
	    return panel;
	}

	private HorizontalPanel buildPanel(String title, FAOSTATCurrentView view, FAOSTATCurrentView currentView, int size) {

		System.out.println("### " + currentView.name() + " ###");
		
		HorizontalPanel p = new HorizontalPanel();
		p.setId(view.name());
		p.setData("base-style-name", "secondary-menu-title");
		// p.setWidth(size);

		// set default Abbreviations as selected
		/**
		 * if(currentView==null){
		 * if(p.getId().equals(FAOSTATCurrentView.VISUALIZE_BY_DOMAIN.name())){
		 * p.addStyleName("secondary-menu-title-selected"); } else {
		 * p.addStyleName("secondary-menu-title"); } } else {
		 **/
		if (p.getId().equals(currentView.name())) {
			p.addStyleName("secondary-menu-title-selected");
		} else {
			p.addStyleName("secondary-menu-title");
		}
		// }
		p.addStyleName("secondary-menu-title");

		System.out.println("$$%$%$%%$%  selected: " + currentView.name() + " : " + p.getStyleName());
		ClickHtml html = new ClickHtml();
		html.setId(currentView.name());
		html.setHtml("<div><img src='dataviewer-images/green_arrow.png'>&nbsp;&nbsp;" + title + "</div>");

		// html.addListener(Events.OnClick,
		// FAOSTATMenuController.setHistoryAnchor(secondaryMenuPanel, p, view));
		html.addListener(Events.OnClick, FAOSTATMenuController.setHistoryItem(view));

		p.add(html);
		return p;
	}

	private HorizontalPanel addSeparator() {
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setWidth(10);
		p.add(new Html("<div class='menu-separator'>|</div>"));
		return p;
	}

	private HorizontalPanel addSpacing(Integer width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(width);
		return p;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}

}