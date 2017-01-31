package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.utils.FAOSTATSearchOracleCache;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.KeyboardEvents;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Window;

public class FAOSTATSearchTextBox {
	
	
	public ContentPanel panel;
	
	public TextField<String> search;
	
	public ComboBox<DWCodesModelData> combo;
	
	public ListStore<DWCodesModelData> store;  
	
	PagingLoader<PagingLoadResult<DWCodesModelData>> loader;
	
	FAOSTATSearchOracleCache oracleCache;
	
	FAOSTATSearchOracleOutput oracleOutput;
	
	/** this is the left panel **/
//	FAOSTATSearchFilter searchFilter;
	
	/** this is the left panel **/
	FAOSTATSearchResults searchResults;
	

	FAOSTATSearchTypes searchTypes;
	
	IconButton searchButton;
	
	Html searchLabel;
	
	
	/** TODO: this is a quick workaround for ENTER listener **/
	String currentString;
	
	/** TODO: have the DWCODES?? **/
	public List<String> clickedStrings; 
	
	
	FAOSTATSearchAdvancedPanel advancedPanel;
	
	LoadingWindow window;
	
	
	public FAOSTATSearchTextBox() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		oracleCache = new FAOSTATSearchOracleCache();
		
		oracleOutput = new FAOSTATSearchOracleOutput();
		
		clickedStrings = new ArrayList<String>();
		
//		searchFilter = new FAOSTATSearchFilter();
		
		searchResults = new FAOSTATSearchResults();
		
//		clusterResults = new FAOSTATSearchClusterResults();
		
//		paging = new FAOSTATSearchResultsPaging();
		
		currentString = "";
		
		searchTypes = new FAOSTATSearchTypes();
		
		advancedPanel = new FAOSTATSearchAdvancedPanel();
		
	}

	
	public ContentPanel build() {
		searchLabel = new Html();
		searchLabel.setHtml("<div class='search_label'>"+FAOSTATLanguage.print().search()+": </div>");
		search = new TextField<String>();
		search.setWidth(FAOSTATSearchConstants.SEARCH_TEXT_WIDTH);
		search.setEmptyText(FAOSTATLanguage.print().egRice());
		search.addStyleName("oracletext");

		searchButton = new IconButton("searchIcon");
		searchButton.setWidth(39);
		searchButton.setHeight(22);

//		// ENTER listener
		KeyListener listener = new KeyListener() {
	        @Override
	        public void componentKeyPress(ComponentEvent event) {
	        	System.out.println("KeyListener componentKeyPress: " + event.getKeyCode());
	        	 int key = event.getKeyCode();

	        	 // ENTER listener
		        	 if ( key == KeyboardEvents.Enter.getEventCode()){
		        		 System.out.println("ENTER PRESSED");
		        		 startSearch();
		        	 }
	       }
	    };
	    search.addKeyListener(listener);

	    search.addListener(Events.KeyUp, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				TextField<String> search = (TextField<String>) be.getSource();

				System.out.println("search: " + search.getValue());

				System.out.println("currentString: " + currentString);
				if (!currentString.equals(search.getValue())) {
					List<String> values = new ArrayList<String>();
					// items
					if (search.getValue().length() > 2) {
						for (Integer i = 0; i < oracleCache.getItems().size(); i++) {
							if (oracleCache.getItems().get(i).toLowerCase().contains(search.getValue().toLowerCase())) {
								values.add(oracleCache.getItems().get(i));
							}
						}
					}

					// countries
					if (search.getValue().length() > 2) {
						for (Integer i = 0; i < oracleCache.getCountries().size(); i++) {
							if (oracleCache.getCountries().get(i).toLowerCase().contains(search.getValue().toLowerCase())) {
								values.add(oracleCache.getCountries().get(i));
							}
						}
					}

					// elements
					if (search.getValue().length() > 2) {
						for (Integer i = 0; i < oracleCache.getElements().size(); i++) {
							if (oracleCache.getElements().get(i).toLowerCase().contains(search.getValue().toLowerCase())) {
								values.add(oracleCache.getElements().get(i));
							}
						}
					}

					oracleOutput.build(values);
				}

				currentString = search.getValue();
			}
		});
	    
	    
	    
		VerticalPanel v = new VerticalPanel();
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		
		p.add(new DataViewerClientUtils().addHSpace(FAOSTATSearchConstants.SEARCH_TEXT_LEFT_MARGIN));
		
		Html html = new Html("<div class='search_title'>"+FAOSTATLanguage.print().search()+":</div>");

//		p.add(html);
//		p.add(new DataViewerClientUtils().addHSpace(5));
		p.add(searchLabel);
		p.add(DataViewerClientUtils.addHSpace(4));
		p.add(search);
		p.add(new DataViewerClientUtils().addHSpace(5));
		
		p.add(addSearchIconAdvancedSearch());
//		p.add(searchButton);
		
//		System.out.println("getScrollTop: " + Window.getScrollTop());
//		System.out.println("Window.getClientHeight(): " + Window.getClientHeight());
//		System.out.println("getScrollLeft(): " + Window.getScrollLeft());

		v.add(p);
		
		v.add(oracleOutput.build(this));
		
		v.add(DataViewerClientUtils.addVSpace(5));
		
		v.add(advancedPanel.getPanel());

		VerticalPanel v2 = new VerticalPanel();
		
		v2.add(DataViewerClientUtils.addVSpace(25));
		v2.add(searchTypes.build(this));
		
		v2.add(DataViewerClientUtils.addVSpace(10));
//		
		v2.add(buildOutputResultsPanel());

		panel.add(v);
		panel.add(v2);
		return panel;
	}
	
	  
	
	private HorizontalPanel addSearchIconAdvancedSearch() {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setVerticalAlign(VerticalAlignment.BOTTOM);
		
		panel.add(addSearchIconPanel());
		panel.add(new DataViewerClientUtils().addHSpace(5));
		panel.add(addAdvancedSearch());
		return panel;
	}
	
	private IconButton addSearchIconPanel() {
		searchButton.addSelectionListener(FAOSTATSearchController.searchEngine(this, DataViewerBoxContent.SEARCH_BY_ITEM.toString()));
		
		return searchButton;
	}

	private VerticalPanel addAdvancedSearch() {
		VerticalPanel panel = new VerticalPanel();
		
		ClickHtml html = new ClickHtml();
		
		html.setHtml("<div class='advanced_search'>"+FAOSTATLanguage.print().advancedSearch()+"</div>");
		
		html.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				advancedPanel.build();
			}
			
		});

		
		panel.add(html);
		
		return panel;
	}
	


	
	
	
	private VerticalPanel buildOutputResultsPanel() {
		VerticalPanel panel = new VerticalPanel();
		
		searchResults.build(this);
		panel.add(searchResults.getPanel());
		


		return panel;
	}
	
	public void startSearch() {
		System.out.println("starting the search");
		FAOSTATSearchController.checkSearchTabsAgent(this);

//		FAOSTATSearchController.searchEngineAgent(this, DataViewerBoxContent.SEARCH_BY_ITEM.toString());

	}
	
	
	
	public TextField<String> getSearch() {
		return search;
	}


	public native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;


	public List<String> getClickedStrings() {
		return clickedStrings;
	}


//	public FAOSTATSearchFilter getSearchFilter() {
//		return searchFilter;
//	}


	public FAOSTATSearchResults getSearchResults() {
		return searchResults;
	}


	public FAOSTATSearchOracleOutput getOracleOutput() {
		return oracleOutput;
	}


	public FAOSTATSearchTypes getSearchTypes() {
		return searchTypes;
	}


	public FAOSTATSearchAdvancedPanel getAdvancedPanel() {
		return advancedPanel;
	}


//	public FAOSTATSearchResultsPaging getPaging() {
//		return paging;
//	}


	
	
	
	
}
