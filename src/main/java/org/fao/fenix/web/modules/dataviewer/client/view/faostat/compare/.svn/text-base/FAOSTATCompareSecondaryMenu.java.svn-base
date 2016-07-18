package org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare;

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
public class FAOSTATCompareSecondaryMenu {

	ContentPanel panel;
	HorizontalPanel secondaryMenuPanel;
	
	public FAOSTATCompareSecondaryMenu() {
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
  
		secondaryMenuPanel.add(DataViewerClientUtils.addHSpace(10));
		secondaryMenuPanel.add(buildPanel(FAOSTATLanguage.print().byCountryRegion(), FAOSTATCurrentView.COMPARE_BY_AREA));
		//secondaryMenuPanel.add(addSeparator());
	/*	secondaryMenuPanel.add(buildPanel("Glossary", FAOSTATCurrentView.METADATA_GLOSSARY));
		secondaryMenuPanel.add(addSeparator());
		secondaryMenuPanel.add(buildPanel("Classifications", FAOSTATCurrentView.METADATA_CLASSIFICATION));
		secondaryMenuPanel.add(addSeparator());
		secondaryMenuPanel.add(buildPanel("Units", FAOSTATCurrentView.METADATA_UNITS));
		secondaryMenuPanel.add(addSeparator());
		secondaryMenuPanel.add(buildPanel("Local Currency", FAOSTATCurrentView.METADATA_CURRENCY));*/
	
		panel.add(DataViewerClientUtils.addVSpace(10));
		panel.add(secondaryMenuPanel);
	    return panel;
	}
	
		
	private HorizontalPanel buildPanel(String title, FAOSTATCurrentView currentView) {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setId(currentView.name());
		p.setData("base-style-name", "secondary-menu-title");
		
		//set default Abbreviations as selected
		if(p.getId().equals(FAOSTATCurrentView.COMPARE_BY_AREA.name())){
			p.addStyleName("secondary-menu-title-selected");
		} else {
			p.addStyleName("secondary-menu-title");
		}
		
		p.addStyleName("secondary-menu-title");
		
		ClickHtml html = new ClickHtml();
		html.setId(currentView.name());
		html.setHtml("<div><img src='dataviewer-images/green_arrow.png'>&nbsp;&nbsp;"+title+"</div>");
		//html.addListener(Events.OnClick, FAOSTATMenuController.openView(secondaryMenuPanel, p, currentView));
		//html.addListener(Events.OnClick, FAOSTATMenuController.setHistoryAnchor(secondaryMenuPanel, p, currentView));
		html.addListener(Events.OnClick, FAOSTATMenuController.setHistoryItem(currentView));
		
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
		

	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}

}
