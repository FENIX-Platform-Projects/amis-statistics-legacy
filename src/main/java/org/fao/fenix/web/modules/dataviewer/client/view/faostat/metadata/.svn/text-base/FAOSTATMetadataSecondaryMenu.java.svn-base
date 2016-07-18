package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata;

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
public class FAOSTATMetadataSecondaryMenu {

	ContentPanel panel;
	HorizontalPanel secondaryMenuPanel;
	
	public FAOSTATMetadataSecondaryMenu() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		secondaryMenuPanel = new HorizontalPanel();
	}
	
	// setting the current view
	public ContentPanel build(FAOSTATCurrentView view) {
		
		panel.addStyleName("secondary-menu-box-style");
		secondaryMenuPanel.setSpacing(10);
		secondaryMenuPanel.addStyleName("secondary-menu-box-style");
		
//		secondaryMenuPanel.add(DataViewerClientUtils.addHSpace(10));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().abbrevs(), FAOSTATCurrentView.METADATA_ABBREVIATIONS, view, 120));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().glossary(), FAOSTATCurrentView.METADATA_GLOSSARY, view, 80));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().classifications(), FAOSTATCurrentView.METADATA_CLASSIFICATION, view, 125));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().methodology(), FAOSTATCurrentView.METADATA_METHODOLOGY, view, 105));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().units(), FAOSTATCurrentView.METADATA_UNITS, view, 85));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().localCurrency(), FAOSTATCurrentView.METADATA_CURRENCY, view, 200));
	
		
		panel.add(DataViewerClientUtils.addVSpace(3));
		panel.add(secondaryMenuPanel);
	    return panel;
	}
	
	
	private HorizontalPanel buildPanel(String title, FAOSTATCurrentView view, FAOSTATCurrentView currentView, int size) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setId(view.name());
		p.setData("base-style-name", "secondary-menu-title");
		p.setWidth(size);
		
		//set default Abbreviations as selected
		/*if(p.getId().equals(FAOSTATCurrentView.METADATA_ABBREVIATIONS.name())){
			p.addStyleName("secondary-menu-title-selected");
		} else {
			p.addStyleName("secondary-menu-title");
		}*/
			if(p.getId().equals(currentView.name())){
				p.addStyleName("secondary-menu-title-selected");
			} else {
				p.addStyleName("secondary-menu-title");
			}
	
		p.addStyleName("secondary-menu-title");
		
		
		ClickHtml html = new ClickHtml();
		//html.setAutoWidth(true);
		html.setId(currentView.name());
		html.setHtml("<div><img src='dataviewer-images/green_arrow.png'>&nbsp;&nbsp;"+title+"</div>");
		
		//html.addListener(Events.OnClick, FAOSTATMenuController.setHistoryAnchor(secondaryMenuPanel, p, view));
		html.addListener(Events.OnClick, FAOSTATMenuController.setHistoryItem(view));
		
		p.add(html);
		return p;
	}
	
	/**private HorizontalPanel buildPanel(String title, FAOSTATCurrentView view, FAOSTATCurrentView view, int size) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setId(currentView.name());
		p.setData("base-style-name", "secondary-menu-title");
		p.setWidth(size);
		//set default Abbreviations as selected
		if(p.getId().equals(FAOSTATCurrentView.METADATA_ABBREVIATIONS.name())){
			p.addStyleName("secondary-menu-title-selected");
		} else {
			p.addStyleName("secondary-menu-title");
		}
		
		if(p.getId().equals(currentView.name())){
			p.addStyleName("secondary-menu-title-selected");
		} else {
			p.addStyleName("secondary-menu-title");
		}
		
		p.addStyleName("secondary-menu-title");
		
		ClickHtml html = new ClickHtml();
		html.setAutoWidth(true);
		html.setId(currentView.name());
		html.setHtml("<div><img src='dataviewer-images/green_arrow.png'>&nbsp;&nbsp;"+title+"</div>");
		//html.addListener(Events.OnClick, FAOSTATMenuController.openView(secondaryMenuPanel, p, currentView));
		html.addListener(Events.OnClick, FAOSTATMenuController.setHistoryItem(currentView));

		
		p.add(html);
		return p;
	}
	**/
	
	private HorizontalPanel addSeparator() {
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setWidth(10);
		p.add(new Html("<div class='menu-separator'>|</div>"));
		return p;
	}
		

	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}

}
