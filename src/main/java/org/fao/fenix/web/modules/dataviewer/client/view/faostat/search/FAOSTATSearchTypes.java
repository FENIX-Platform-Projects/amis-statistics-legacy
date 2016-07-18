package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class FAOSTATSearchTypes {
	
	ContentPanel panel;
	
	ClickHtml items;
	
	ClickHtml elements;
	
	// default
	String searchType = DataViewerBoxContent.SEARCH_BY_ITEM.toString();
	
	// default
	Boolean itemsVisible = true;
	
	// default
	Boolean elementsVisible = true;
	
	Html noReturns;
	
	public FAOSTATSearchTypes() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		items = new ClickHtml();

		elements = new ClickHtml();

		items.addStyleName("search_tab_selected");
		
		elements.addStyleName("search_tab_unselected");
		
		noReturns = new Html();
	}
	
	public ContentPanel build(FAOSTATSearchTextBox searchTextBox) {

		HorizontalPanel p = new HorizontalPanel();
		p.add(DataViewerClientUtils.addHSpace(FAOSTATSearchConstants.SEARCH_TEXT_LEFT_MARGIN));
		p.add(buildTab(searchTextBox, items, FAOSTATLanguage.print().viewByItem(), DataViewerBoxContent.SEARCH_BY_ITEM.toString(), true));
		p.add(DataViewerClientUtils.addHSpace(10));
		p.add(buildTab(searchTextBox, elements, FAOSTATLanguage.print().viewByElement(), DataViewerBoxContent.SEARCH_BY_ELEMENT.toString(), false));

		noReturns = new Html("<div class='search_noresults'>"+FAOSTATLanguage.print().noResultsAvailableForSearch()+"</div>");
		p.add(noReturns);
		
//		noReturns.hide();

		panel.add(p);

		panel.hide();
		return panel;

	}
	
	

	public HorizontalPanel buildTab(FAOSTATSearchTextBox searchTextBox, ClickHtml html, String label, String searchType, Boolean isSelected) {
		HorizontalPanel panel = new HorizontalPanel();
		
		html.setHtml("<div>" + label +"</div>");
	
		html.addListener(Events.OnClick, FAOSTATSearchController.searchEngineListener(searchTextBox, searchType));
		html.addListener(Events.OnClick, setTab(html));

		
//		setStyle(html, isSelected);
		
		panel.add(html);
		
		return panel;
	}

//	private void setStyle(ClickHtml html, Boolean isSelected) {
//		System.out.println("setting the styles");
//		if ( isSelected ) {
//			html.addStyleName("search_tab_selected");
//		}
//		else {
//			html.addStyleName("search_tab_unselected");
//		}
//	}

	
	public void setDefaultStyles() {
		items.addStyleName("search_tab_selected");
		elements.addStyleName("search_tab_unselected");
		panel.show();
	} 

	private void resetStyles() {
		System.out.println("resetting");
		items.removeStyleName("search_tab_selected");
		items.addStyleName("search_tab_unselected");
		
		elements.removeStyleName("search_tab_selected");
		elements.addStyleName("search_tab_unselected");

		
	} 
	

	public void setTabAgent(final ClickHtml html) {
		
		resetStyles();
		System.out.println("html: " + html.getHtml());
				
		html.removeStyleName("search_tab_unselected");
		html.addStyleName("search_tab_selected");
		
		panel.show();
	}
	
	
	
	public Listener<ComponentEvent> setTab(final ClickHtml html) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				setTabAgent(html);
				
				
				
				
//				resetStyles();
//				System.out.println("html: " + html.getHtml());
//				
//				html.removeStyleName("search_tab_unselected");
//				html.addStyleName("search_tab_selected");
			}
		};
	}

	public ClickHtml getElements() {
		return elements;
	}

	public void setElements(ClickHtml elements) {
		this.elements = elements;
	}

	public ClickHtml getItems() {
		return items;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public String getSearchType() {
		return searchType;
	}

	public Boolean getItemsVisible() {
		return itemsVisible;
	}

	public Boolean getElementsVisible() {
		return elementsVisible;
	}

	public void setElementsVisible(Boolean elementsVisible) {
		this.elementsVisible = elementsVisible;
	}

	public void setItemsVisible(Boolean itemsVisible) {
		this.itemsVisible = itemsVisible;
	}

	public Html getNoReturns() {
		return noReturns;
	}


	
	

	
}
