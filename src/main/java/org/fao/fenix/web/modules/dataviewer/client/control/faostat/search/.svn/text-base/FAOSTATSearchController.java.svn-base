package org.fao.fenix.web.modules.dataviewer.client.control.faostat.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATPivotTableController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot.FAOSTATDownloadTablePanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearch;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchAdvancedPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchClusterResult;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchClusterResults;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchFilter;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchOracleOutput;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchResults;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchSingleResult;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchTextBox;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionFilter;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionSelectedItem;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstantsFilters;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchResultsVO;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;



import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Items;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;



public class FAOSTATSearchController extends FAOSTATDownloadController {

	
	public static void callSearchViewAgent(FAOSTATCurrentView view) {
		

//		Html html = new Html("<IFRAME src='http://168.202.52.216:8080/r-gwt/gui/GUI.html'  width='100%' height='600px'></IFRAME>");
//		RootPanel.get("MAIN_CONTENT").add(html);
		RootPanel.get("MAIN_CONTENT").add(new FAOSTATSearch().build(view));
	}
	
	public static Listener<ComponentEvent> callSearchView(final FAOSTATCurrentView view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				callSearchViewAgent(view);
			}
		};
	}
	
	
	
	public static void loadCodingSystem(final List<String> values, LinkedHashMap<String, String> valuesMap, final String filterType)  {
		
		System.out.println("loadCodingSystem");
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));	
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));		
		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);

		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					
					System.out.println("call returned loading the coding systems...: " + filterType);

					for(DWCodesModelData code : vo.getCodes()) {
						values.add(code.getLabel().toLowerCase());
//						values.put(code.getCode(), code.getLabel());
					}
					
					System.out.println("end loading the coding systems...: " + filterType);
//					System.out.println("values: " + values);
				}
				
				public void onFailure(Throwable arg0) {

				}
			});
		} catch (FenixGWTException e) {
				
			e.printStackTrace();
		}

	}
	
	// TODO: passing all the informations required **/
	public static Listener<ComponentEvent> setTextBox(final FAOSTATSearchOracleOutput searchOracleOutput, final FAOSTATSearchTextBox searchTextBox, final String label) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				setTextBoxAgent(searchOracleOutput, searchTextBox, label);
			}
		};
	}
	
	/** TODO: no delete all the text...**/
	public static void setTextBoxAgent(FAOSTATSearchOracleOutput searchOracleOutput, FAOSTATSearchTextBox searchTextBox, String label) {
		searchOracleOutput.getPanel().hide();
		
		searchTextBox.getSearch().setValue(label);
	
		/** lauch the search **/
		checkClickedStrings(searchTextBox, label);
		

	}
	
	public static void checkClickedStrings(FAOSTATSearchTextBox searchTextBox, String label) {
		/** TODO: for now just add it...take care of multiples searches **/
		
		searchTextBox.getClickedStrings().clear();
		searchTextBox.getClickedStrings().add(label);
		
		// TODO: fixed? 
//		searchEngineAgent(searchTextBox, DataViewerBoxContent.SEARCH_BY_ITEM.toString());

		checkSearchTabsAgent(searchTextBox);
		System.out.println("searchTextBox.getClickedStrings(): " + searchTextBox.getClickedStrings());
	}
	
	public static SelectionListener<IconButtonEvent> searchEngine(final FAOSTATSearchTextBox searchTextBox, final String searchType) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				System.out.println("searchEngine");
//				searchEngineAgent(searchTextBox, searchType);
				checkSearchTabsAgent(searchTextBox);
			}
		};
	}
	
	
	
	public static Listener<ComponentEvent> searchEngineListener(final FAOSTATSearchTextBox searchTextBox, final String searchType) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				System.out.println("searchEngineListener");
				//TODO: this can stay here i think
				searchEngineAgent(searchTextBox, searchType);
			}
		};
	}
	
	public static void checkSearchTabsAgent(final FAOSTATSearchTextBox searchTextBox) {
		System.out.println("checkSearchTabs");
		List<String> searchTokens = new ArrayList<String>();
		
		String textString = searchTextBox.getSearch().getValue();
		
		/** TODO: QUICK VERSION OF THE SEARCH WITH RANGE CODES **/
		HashMap<String, String> rangeCodes = new HashMap<String, String>();
		if ( textString.contains("-")) {
			// splitting the code in two for the range
			System.out.println("splitting the code textString: " + textString );
			String from = textString.substring(0, textString.indexOf("-"));
			String to = textString.substring(textString.indexOf("-") + 1, textString.length());
			
			System.out.println("splitting the code from: " + from );
			System.out.println("splitting the code to: " + to );
			rangeCodes.put(from, to);
		}
		
		else {
			/** check if the label in clickedStrings is still there... **/
			System.out.println("START textString: " + textString);
			if ( !searchTextBox.getClickedStrings().isEmpty() ) {
				System.out.println("START searchTextBox.getClickedStrings().get(0).toLowerCase(): " + searchTextBox.getClickedStrings().get(0).toLowerCase());
				/** check if the string is in the current textstring **/
				/** TODO: change when there will be multiple istances...*/
				if ( textString.toLowerCase().contains(searchTextBox.getClickedStrings().get(0).toLowerCase())) {
					System.out.println("contains: " + searchTextBox.getClickedStrings().get(0));
					
					System.out.println("TEXT STRING1: " + textString);
					textString = textString.replace(searchTextBox.getClickedStrings().get(0).toLowerCase(), "");
					System.out.println("TEXT STRING2: " + textString);
					searchTokens.add(searchTextBox.getClickedStrings().get(0));
				}
			}
			
			/** TODO: Tokenize the rest of the text string **/
			List<String> tokenizeResult = tokenizeSearchTextString(textString);
			if ( !tokenizeResult.isEmpty()) {
				System.out.println("tokenizeResult: " + tokenizeResult);
				searchTokens.addAll(tokenizeResult);
			}
		}
		
//		System.out.println("searchTextBox.getClickedStrings().get(0).toLowerCase(): " + searchTextBox.getClickedStrings().get(0).toLowerCase());
		System.out.println("textString: " + textString);
		System.out.println("searchTokens: " + searchTokens);
		
		/** TODO: this is a method called for the demo....**/
		System.out.println();
		
	
		List<String> searchingStrings = new ArrayList<String>();
		for(String searchString : searchTokens ) {
			System.out.println("SEARCHING: -" + searchString + "-");
			/** TODO: should be removed that check **/
			if ( searchString.length() > 2) {
				searchingStrings.add(searchString.trim());
//				searchItem(searchTextBox, searchString, loadingWindow);
			}
			else {
				System.out.println("searching for lenght: -" + searchString + "-");
				if ( searchingCodes(searchString)) {
					System.out.println("yes: -" + searchString + "-");
					searchingStrings.add(searchString.trim());
				}
				else {
					System.out.println("not... -" + searchString + "-");
				}
			}
		}
		
		// checking the tabs...
		checkSearchTabs(searchTextBox, searchingStrings, rangeCodes);

	}
	
	private static void checkSearchTabs(final FAOSTATSearchTextBox searchTextBox, final List<String> searchStrings, final HashMap<String, String> rangeCodes) {
		
		
		final LoadingWindow loadingWindow = new LoadingWindow(FAOSTATLanguage.print().searchingFAOSTAT(), FAOSTATLanguage.print().retrievingResults(), FAOSTATLanguage.print().loading());
		loadingWindow.showLoadingBox();
		
		searchTextBox.getSearchTypes().getNoReturns().hide();
		
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.ITEMS_BY_LABEL.toString());		
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(DataViewerBoxContent.ITEMS_BY_LABEL.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		HashMap<String, String> map = new HashMap<String, String>();
		for(String searchString : searchStrings ){
			map.put(searchString, searchString);
		}
		
		qvo.setItems(map);
		
		// range codes
		qvo.setRangeCodes(rangeCodes);
		
		// CHECKING ITEMS
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
			
					System.out.println("CALL RETURN ITEMS: " + vo.getCodes().isEmpty());
					
					if ( vo.getCodes().isEmpty() ) {
						searchTextBox.getSearchTypes().getItems().hide();
						searchTextBox.getSearchTypes().setItemsVisible(false);
					}
					else {
//						searchTextBox.getSearchTypes().getNoReturns().hide();
						searchTextBox.getSearchTypes().getItems().show();
						searchTextBox.getSearchTypes().setItemsVisible(true);
					}

					// CHECKING ELEMENTS 
					DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
					qvo.setOutput(DataViewerBoxContent.GET.toString());
					qvo.setTypeOfOutput(DataViewerBoxContent.ELEMENTS_BY_LABEL.toString());	
//					qvo.setOutputType(DataViewerBoxContent.GET.toString());
//					qvo.setResourceType(DataViewerBoxContent.ELEMENTS_BY_LABEL.toString());		
					FAOSTATConstants.setFAOSTATDBSettings(qvo);

					
					HashMap<String, String> map = new HashMap<String, String>();
					for(String searchString : searchStrings ){
						map.put(searchString, searchString);
					}
					
					qvo.setElements(map);
					
					// range codes
					qvo.setRangeCodes(rangeCodes);

					try {
						DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
							
							@SuppressWarnings("unchecked")
							public void onSuccess(DWFAOSTATResultVO vo) {
						
								System.out.println("CALL RETURN ELEMENTS " + vo.getCodes().isEmpty());
								
								if ( vo.getCodes().isEmpty() ) {
									searchTextBox.getSearchTypes().getElements().hide();
									searchTextBox.getSearchTypes().setElementsVisible(false);
								}
								else {
//									searchTextBox.getSearchTypes().getNoReturns().hide();
									searchTextBox.getSearchTypes().getElements().show();
									searchTextBox.getSearchTypes().setElementsVisible(true);
								}
								
								
								// checks
								if (searchTextBox.getSearchTypes().getItemsVisible() ) {
									searchEngineAgent(searchTextBox, DataViewerBoxContent.SEARCH_BY_ITEM.toString());
									
								}
								else if ( !searchTextBox.getSearchTypes().getItemsVisible() && searchTextBox.getSearchTypes().getElementsVisible() ) {
									searchEngineAgent(searchTextBox, DataViewerBoxContent.SEARCH_BY_ELEMENT.toString());
								}
								else {
									searchTextBox.getSearchTypes().getNoReturns().show();
									searchTextBox.getSearchTypes().getPanel().layout();
									searchTextBox.getSearchTypes().getPanel().show();
									System.out.println("ANY OF BOTH ARE AVAILABLE");
									
									// central panel removing
									searchTextBox.getOracleOutput().getPanel().hide();
									searchTextBox.getOracleOutput().getPanel().layout();
									searchTextBox.getSearchResults().getPanel().removeAll();
								}
								loadingWindow.destroyLoadingBox();
							}
							
							public void onFailure(Throwable arg0) {
								loadingWindow.destroyLoadingBox();
								searchTextBox.getSearchTypes().getElements().hide();
								searchTextBox.getSearchTypes().setElementsVisible(false);
							}
						});	
					}
					
						catch (Exception e) {	
							loadingWindow.destroyLoadingBox();
					}
				}

				public void onFailure(Throwable arg0) {
					loadingWindow.destroyLoadingBox();
					searchTextBox.getSearchTypes().getItems().hide();
					searchTextBox.getSearchTypes().setItemsVisible(false);
				}
			});
			
		} catch (Exception e) {
			loadingWindow.destroyLoadingBox();
			e.printStackTrace();

		}
	
	}
	

	
	public static void searchEngineAgent(FAOSTATSearchTextBox searchTextBox, String searchType) {
		System.out.println("search engine:");
		// cleaning all the variables
		searchTextBox.getOracleOutput().getPanel().hide();
		searchTextBox.getOracleOutput().getPanel().layout();
		
		FAOSTATSearchClusterResults faostatSearchClusterResults = new FAOSTATSearchClusterResults();
		faostatSearchClusterResults.build(searchTextBox);
		
		/*** TODO: pass it and check if exists
		 * 		-> keep track if the search changed 
		 * 		-> if exist just show the panel **/

		
		LoadingWindow loadingWindow = new LoadingWindow(FAOSTATLanguage.print().searchingFAOSTAT(), FAOSTATLanguage.print().retrievingResults(), FAOSTATLanguage.print().loading());
		loadingWindow.showLoadingBox();
		
		List<String> searchTokens = new ArrayList<String>();
		
		String textString = searchTextBox.getSearch().getValue();
		
		// GOOGLE ANALYTICS
		GoogleAnalyticsController.trackEvent(FAOSTATCurrentView.SEARCH_DATA.toString(), "Search text", textString);
		
		/** TODO: QUICK VERSION OF THE SEARCH WITH RANGE CODES **/
		HashMap<String, String> rangeCodes = new HashMap<String, String>();
		if ( textString.contains("-")) {
			// splitting the code in two for the range
			System.out.println("splitting the code textString: " + textString );
			String from = textString.substring(0, textString.indexOf("-"));
			String to = textString.substring(textString.indexOf("-") + 1, textString.length());
			
			System.out.println("splitting the code from: " + from );
			System.out.println("splitting the code to: " + to );
			rangeCodes.put(from, to);
		}
		
		else {
			/** check if the label in clickedStrings is still there... **/
			System.out.println("START textString: " + textString);
			if ( !searchTextBox.getClickedStrings().isEmpty() ) {
				System.out.println("START searchTextBox.getClickedStrings().get(0).toLowerCase(): " + searchTextBox.getClickedStrings().get(0).toLowerCase());
				/** check if the string is in the current textstring **/
				/** TODO: change when there will be multiple istances...*/
				if ( textString.toLowerCase().contains(searchTextBox.getClickedStrings().get(0).toLowerCase())) {
					System.out.println("contains: " + searchTextBox.getClickedStrings().get(0));
					
					System.out.println("TEXT STRING1: " + textString);
					textString = textString.replace(searchTextBox.getClickedStrings().get(0).toLowerCase(), "");
					System.out.println("TEXT STRING2: " + textString);
					searchTokens.add(searchTextBox.getClickedStrings().get(0));
				}
			}
			
			/** TODO: Tokenize the rest of the text string **/
			List<String> tokenizeResult = tokenizeSearchTextString(textString);
			if ( !tokenizeResult.isEmpty()) {
				System.out.println("tokenizeResult: " + tokenizeResult);
				searchTokens.addAll(tokenizeResult);
			}
		}
		
//		System.out.println("searchTextBox.getClickedStrings().get(0).toLowerCase(): " + searchTextBox.getClickedStrings().get(0).toLowerCase());
		System.out.println("textString: " + textString);
		System.out.println("searchTokens: " + searchTokens);
		
		/** TODO: this is a method called for the demo....**/
		System.out.println();
		
	
		List<String> searchingStrings = new ArrayList<String>();
		for(String searchString : searchTokens ) {
			System.out.println("SEARCHING: -" + searchString + "-");
			/** TODO: should be removed that check **/
			if ( searchString.length() > 2) {
				searchingStrings.add(searchString.trim());
//				searchItem(searchTextBox, searchString, loadingWindow);
			}
			else {
				System.out.println("searching for lenght: -" + searchString + "-");
				if ( searchingCodes(searchString)) {
					System.out.println("yes: -" + searchString + "-");
					searchingStrings.add(searchString.trim());
				}
				else {
					System.out.println("not... -" + searchString + "-");
				}
			}
		}
		
		System.out.println("SEARCH for: -" + searchingStrings +"-");
		/** TODO: check if the items or elements has results **/
		DataViewerBoxContent type = DataViewerBoxContent.valueOf(searchType);	
		// TODO: "ITEM" should be the searchType
		searchTextBox.getSearchResults().getClusterResults().put("ITEM", faostatSearchClusterResults);
		
		switch (type) {
		case SEARCH_BY_ITEM:
			searchItem(searchTextBox, searchingStrings, rangeCodes, loadingWindow);
			searchTextBox.getSearchTypes().setTabAgent(searchTextBox.getSearchTypes().getItems());
			break;
			
		case SEARCH_BY_ELEMENT:
			searchElement(searchTextBox, searchingStrings, rangeCodes, loadingWindow);
			searchTextBox.getSearchTypes().setTabAgent(searchTextBox.getSearchTypes().getElements());
			break;

		default:
			break;
		}

		
		// adding the paing title 
		// TODO: the real pager...
		faostatSearchClusterResults.getPaging().getTitle().setHtml("<div class='search_paging_title'>"+FAOSTATLanguage.print().resultsFor()+" <b>"+ searchTextBox.getSearch().getValue() +"</b></div>");
		faostatSearchClusterResults.getPaging().getPanel().show();
		faostatSearchClusterResults.getPaging().getPanel().layout();	
	}
	
	
	/** TODO: this is a method called for the demo....**/
	private static void searchItem(final FAOSTATSearchTextBox searchTextBox, final List<String> searchStrings, final HashMap<String, String> rangeCodes, final LoadingWindow loadingWindow) {
		System.out.println("searchItem: " + searchStrings);	
		
		/** TODO: calling the item to get the code **/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.ITEMS_BY_LABEL.toString());		
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(DataViewerBoxContent.ITEMS_BY_LABEL.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		searchTextBox.getSearchResults().getPanel().removeAll();
		
		HashMap<String, String> map = new HashMap<String, String>();
		for(String searchString : searchStrings ){
			map.put(searchString, searchString);
		}
		
		qvo.setItems(map);
		
		// range codes
		qvo.setRangeCodes(rangeCodes);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					

					System.out.println("CALL RETURN");
					
					for(DWCodesModelData code : vo.getCodes()) {
						System.out.println("code: " + code.getCode() + " | " + code.getLabel());
					}
					
	
//					if ( !vo.getCodes().isEmpty() ) {
//						System.out.println("!vo.getCodes().isEmpty()");
						searchDomainsByItem(searchTextBox, vo.getCodes(), loadingWindow);			
						searchAllItems(searchTextBox, vo.getCodes(), loadingWindow);
//					}
//					else {
//						System.out.println("vo.getCodes().isEmpty()");
//						searchElement(searchTextBox, searchStrings, loadingWindow);
//					}
				}
				

				
				public void onFailure(Throwable arg0) {
//					System.out.println("in the error part");
//					searchElement(searchTextBox, searchStrings, loadingWindow);
					loadingWindow.destroyLoadingBox();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void searchElement(final FAOSTATSearchTextBox searchTextBox, List<String> searchStrings, final HashMap<String, String> rangeCodes, final LoadingWindow loadingWindow) {
		System.out.println("searchItem: " + searchStrings);	
		
		/** TODO: calling the item to get the code **/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.ELEMENTS_BY_LABEL.toString());		
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(DataViewerBoxContent.ELEMENTS_BY_LABEL.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		searchTextBox.getSearchResults().getPanel().removeAll();
		
		HashMap<String, String> map = new HashMap<String, String>();
		for(String searchString : searchStrings ){
			map.put(searchString, searchString);
		}
		
		qvo.setElements(map);
		
		// range codes
		qvo.setRangeCodes(rangeCodes);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
				

					System.out.println("CALL RETURN: ");
					
					for(DWCodesModelData code : vo.getCodes()) {
						System.out.println("code: " + code.getCode() + " | " + code.getLabel());
					}
					
					/** TODO: call all the domains 
					 *  	- create the left panel with the domains - subdomains tree
					 *  	- create for each commodity the panel for the download/view  
					 **/
					searchDomainsByElement(searchTextBox, vo.getCodes(), loadingWindow);
					
					searchAllElements(searchTextBox, vo.getCodes(), loadingWindow);
				}
				

				
				public void onFailure(Throwable arg0) {
					loadingWindow.destroyLoadingBox();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** TODO: this is made for the demo...**/
	private static void searchDomainsByItem(final FAOSTATSearchTextBox searchTextBox, final List<DWCodesModelData> itemCodes, final LoadingWindow loadingWindow) {
		
		
		
		/** TODO: calling the item to get the code **/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.GROUPS_AND_DOMAINS_BY_ITEM.toString());	
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(DataViewerBoxContent.GROUPS_AND_DOMAINS_BY_ITEM.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		for(DWCodesModelData itemCode : itemCodes) {
			map.put(itemCode.getCode(), itemCode.getLabel());
		}
		
		qvo.setItems(map);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					
					FAOSTATSearchFilter searchFilter = searchTextBox.getSearchResults().getClusterResults().get("ITEM").getSearchFilter();
					
					// TODO: Pass it
					TreeStore treeStore = searchFilter.getStore();

					System.out.println("CALL RETURN");
					// 
					treeStore.removeAll();
					
	
							for(DWCodesModelData topcode : vo.getCodesHierachy().keySet()) {
								System.out.println("CODE: " + topcode.getCode()+ " | " + topcode.getLabel());
								for (DWCodesModelData subcode: vo.getCodesHierachy().get(topcode)) {
									System.out.println("SUBCODE: " + subcode.getCode() + " | " + subcode.getLabel());
								}
								treeStore.add(topcode, true);
								treeStore.add(topcode, vo.getCodesHierachy().get(topcode), true);
								System.out.println("");
							}
				
							searchFilter.getTree().expandAll();
							searchFilter.getPanel().show();
							searchFilter.getPanel().layout();
							
							/** TODO: call all the domains 
							 *  	- create for each commodity the panel for the download/view  
							 **/
//							for(DWCodesModelData itemCode : itemCodes) {
//								searchSingleResult(searchTextBox, vo.getCodesHierachy(), itemCode, loadingWindow);
//							}
							
							if ( vo.getCodesHierachy().isEmpty() )
								loadingWindow.destroyLoadingBox();
				}
				
				public void onFailure(Throwable arg0) {
					loadingWindow.destroyLoadingBox();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void searchDomainsByElement(final FAOSTATSearchTextBox searchTextBox, final List<DWCodesModelData> elementListCodes, final LoadingWindow loadingWindow) {
		
		
		
		/** TODO: calling the item to get the code **/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.GROUPS_AND_DOMAINS_BY_ELEMENTLIST.toString());		
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(DataViewerBoxContent.GROUPS_AND_DOMAINS_BY_ELEMENTLIST.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		for(DWCodesModelData elementListCode : elementListCodes) {
			map.put(elementListCode.getCode(), elementListCode.getLabel());
		}
		
		qvo.setElementsList(map);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					
					FAOSTATSearchFilter searchFilter = searchTextBox.getSearchResults().getClusterResults().get("ITEM").getSearchFilter();
					
					// TODO: Pass it
					TreeStore treeStore = searchFilter.getStore();

					treeStore.removeAll();
					
	
					for(DWCodesModelData topcode : vo.getCodesHierachy().keySet()) {
						System.out.println("CODE: " + topcode.getCode()+ " | " + topcode.getLabel());
						for (DWCodesModelData subcode: vo.getCodesHierachy().get(topcode)) {
							System.out.println("SUBCODE: " + subcode.getCode() + " | " + subcode.getLabel());
						}
						treeStore.add(topcode, true);
						treeStore.add(topcode, vo.getCodesHierachy().get(topcode), true);
						System.out.println("");
					}
				
					searchFilter.getTree().expandAll();
					searchFilter.getPanel().show();
					searchFilter.getPanel().layout();

					if ( vo.getCodesHierachy().isEmpty() )
						loadingWindow.destroyLoadingBox();
				}
				
				public void onFailure(Throwable arg0) {
					loadingWindow.destroyLoadingBox();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void searchAllItems(final FAOSTATSearchTextBox searchTextBox, final List<DWCodesModelData> itemCodes, final LoadingWindow loadingWindow) {

		
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.RESULT_SEARCH_ITEMS.toString());		

		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		 HashMap<String, String> itemMap = new HashMap<String, String>();
		for(DWCodesModelData itemCode : itemCodes) {
			itemMap.put(itemCode.getCode(), itemCode.getLabel());
		}
		qvo.setItems(itemMap);
		
		final FAOSTATSearchResults searchResults = searchTextBox.getSearchResults();
		
		// TODO: this should about the switch
		searchResults.getPanel().removeAll();
		searchResults.getPanel().show();

		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					LinkedHashMap<String, FAOSTATSearchResultsVO>  searchResutsHM = vo.getSearchResuts();

					// TODO: "ITEM" should be the searchType
					FAOSTATSearchClusterResults clusterResults = searchTextBox.getSearchResults().getClusterResults().get("ITEM");
					clusterResults.getPaging().getCounter().setHtml("<div class='search_paging_title'>(<i>"+FAOSTATLanguage.print().totalResults()+":</i> "+ searchResutsHM.size() +")</div>");
					clusterResults.getPaging().getPanel().show();
					clusterResults.getPaging().getPanel().layout();
					
					for(String itemCode : searchResutsHM.keySet()) {
						
						
						
						// create the cluster 
						FAOSTATSearchClusterResult clusterResult = new FAOSTATSearchClusterResult();
						if ( clusterResults.getClusterResult().containsKey(itemCode)) {
							clusterResult = clusterResults.getClusterResult().get(itemCode);
						}
						

						clusterResult.setSearchResultsVO(searchResutsHM.get(itemCode));
						DWCodesModelData code = searchResutsHM.get(itemCode).getCode();
						clusterResult.setCode(code.getCode());
						clusterResult.setLabel(code.getLabel());
						
						// building the title
						clusterResult.getPanel().add(clusterResult.buildTitle(code.getLabel() + " (" + code.getCode() + ")"));
						
						// building the contents
						FAOSTATSearchResultsVO searchResultsVO = searchResutsHM.get(itemCode);
//						System.out.println("ITEM: " + searchResultsVO.getCode().getCode() + " | " + searchResultsVO.getCode().getLabel());
						for(String domainCode : searchResultsVO.getSearchResultVO().keySet()) {
							
							FAOSTATSearchResultVO searchResultVO = searchResultsVO.getSearchResultVO().get(domainCode);
							
							// building the single content
							FAOSTATSearchSingleResult singleResult = new FAOSTATSearchSingleResult();
							
							VerticalPanel v = new VerticalPanel();
							v.add(singleResult.build(searchTextBox, searchResultVO.getGroups(), searchResultVO.getDomains(), searchResultVO.getElements(), searchResultVO.getItems()));
							v.layout();
							
//							System.out.println("CONTENTS: " + searchResultVO.getGroups() + " | " + searchResultVO.getDomains() + " | " + searchResultVO.getElements());
						
							clusterResult.getPanel().add(v);
							clusterResult.getPanel().layout();
							
							clusterResult.getSearchSingleResults().add(singleResult);
						
						}
						
						clusterResults.getClusterResultPanel().add(clusterResult.getPanel());
						
						
						
						
						// the download all is used just if the domains (or any clustering is more than one)
						if ( searchResutsHM.get(itemCode).getSearchResultVO().size() > 1 ) {
							clusterResult.addDownloadAll(searchTextBox);
							clusterResult.getPanel().add(clusterResult.getDownloadAll());
						}
						
											
						clusterResults.getClusterResult().put(itemCode, clusterResult);
						clusterResults.getClusterResultPanel().layout();

//						System.out.println("--------------");
					}
					
					searchResults.getPanel().add(clusterResults.getPanel());
					searchResults.getPanel().layout();
					
					// use ITEMS?
					// TODO: "ITEM" should be the searchType
					searchResults.getClusterResults().put("ITEMS", clusterResults);
					
					
					loadingWindow.destroyLoadingBox();
				}
				
				
				public void onFailure(Throwable arg0) {
					System.out.println("items loading failed");
					FAOSTATSearchClusterResults clusterResults = searchTextBox.getSearchResults().getClusterResults().get("ITEM");
					clusterResults.getPaging().getTitle().setHtml("<div class='search_paging_title'>"+FAOSTATLanguage.print().noItemsFound()+"</div>");
					clusterResults.getPaging().getPanel().show();
					clusterResults.getPaging().getPanel().layout();
					clusterResults.getClusterResultPanel().layout();
					
					searchResults.getPanel().add(clusterResults.getPanel());
					searchResults.getPanel().layout();
					
					loadingWindow.destroyLoadingBox();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void searchAllElements(final FAOSTATSearchTextBox searchTextBox, final List<DWCodesModelData> elementListCodes, final LoadingWindow loadingWindow) {

		
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.RESULT_SEARCH_ELEMENTS.toString());	
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(DataViewerBoxContent.RESULT_SEARCH_ELEMENTS.toString());		
		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		 HashMap<String, String> map = new HashMap<String, String>();
		for(DWCodesModelData elementListCode : elementListCodes) {
			map.put(elementListCode.getCode(), elementListCode.getLabel());
		}
		qvo.setElementsList(map);
		
		final FAOSTATSearchResults searchResults = searchTextBox.getSearchResults();
		
		// TODO: this should about the switch
		searchResults.getPanel().removeAll();
		searchResults.getPanel().show();
		
		
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					loadingWindow.destroyLoadingBox();
					
					LinkedHashMap<String, FAOSTATSearchResultsVO>  searchResutsHM = vo.getSearchResuts();
	


//					FAOSTATSearchClusterResults clusterResults = new FAOSTATSearchClusterResults();
					FAOSTATSearchClusterResults clusterResults = searchTextBox.getSearchResults().getClusterResults().get("ITEM");
					clusterResults.getPaging().getCounter().setHtml("<div class='search_paging_title'>(<i>"+FAOSTATLanguage.print().totalResults()+":</i> "+ searchResutsHM.size() +")</div>");
					clusterResults.getPaging().getPanel().show();
					clusterResults.getPaging().getPanel().layout();
					
					for(String itemCode : searchResutsHM.keySet()) {
						
						
						
						// create the cluster 
						FAOSTATSearchClusterResult clusterResult = new FAOSTATSearchClusterResult();
						if ( clusterResults.getClusterResult().containsKey(itemCode)) {
							clusterResult = clusterResults.getClusterResult().get(itemCode);
						}
						

						clusterResult.setSearchResultsVO(searchResutsHM.get(itemCode));
						DWCodesModelData code = searchResutsHM.get(itemCode).getCode();
						clusterResult.setCode(code.getCode());
						clusterResult.setLabel(code.getLabel());
						
						// building the title
						clusterResult.getPanel().add(clusterResult.buildTitle(code.getLabel() + " (" + code.getCode() + ")"));
						
						// building the contents
						FAOSTATSearchResultsVO searchResultsVO = searchResutsHM.get(itemCode);
//						System.out.println("ITEM: " + searchResultsVO.getCode().getCode() + " | " + searchResultsVO.getCode().getLabel());
						for(String domainCode : searchResultsVO.getSearchResultVO().keySet()) {
							
							FAOSTATSearchResultVO searchResultVO = searchResultsVO.getSearchResultVO().get(domainCode);
							
							// building the single content
							FAOSTATSearchSingleResult singleResult = new FAOSTATSearchSingleResult();
							
							VerticalPanel v = new VerticalPanel();
							v.add(singleResult.build(searchTextBox, searchResultVO.getGroups(), searchResultVO.getDomains(), searchResultVO.getElements(), searchResultVO.getItems()));
							v.layout();
							
//							System.out.println("CONTENTS: " + searchResultVO.getGroups() + " | " + searchResultVO.getDomains() + " | " + searchResultVO.getElements());
						
							clusterResult.getPanel().add(v);
							clusterResult.getPanel().layout();
							
							clusterResult.getSearchSingleResults().add(singleResult);
						
						}
						
						clusterResults.getClusterResultPanel().add(clusterResult.getPanel());
						
						
						
						
						// the download all is used just if the domains (or any clustering is more than one)
//						if ( searchResutsHM.get(itemCode).getSearchResultVO().size() > 1 ) {
//							clusterResult.addDownloadAll();
//							clusterResult.getPanel().add(clusterResult.getDownloadAll());
//						}
						
											
						clusterResults.getClusterResult().put(itemCode, clusterResult);
						clusterResults.getClusterResultPanel().layout();

//						System.out.println("--------------");
					}
					
					searchResults.getPanel().add(clusterResults.getPanel());
					searchResults.getPanel().layout();
					
					// use ITEMS?
					searchResults.getClusterResults().put("ITEMS", clusterResults);
				}
				
				
				public void onFailure(Throwable arg0) {
					System.out.println("items loading failed");
					FAOSTATSearchClusterResults clusterResults = searchTextBox.getSearchResults().getClusterResults().get("ITEM");
					clusterResults.getPaging().getTitle().setHtml("<div class='search_paging_title'>"+FAOSTATLanguage.print().noElementsFound()+"</div>");
					clusterResults.getPaging().getPanel().show();
					clusterResults.getPaging().getPanel().layout();
					clusterResults.getClusterResultPanel().layout();
					
					searchResults.getPanel().add(clusterResults.getPanel());
					searchResults.getPanel().layout();
					
					loadingWindow.destroyLoadingBox();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	private static void searchSingleResult(final FAOSTATSearchTextBox searchTextBox, LinkedHashMap<DWCodesModelData, List<DWCodesModelData>> groupCodes, final DWCodesModelData itemCode, final LoadingWindow loadingWindow) {
		/** CHANGED FOR ITEMS SEARCH 
		// Build title for the commodity
		
		final ContentPanel panel = searchTextBox.getSearchResults().getPanel();
		final FAOSTATSearchResults searchResults = searchTextBox.getSearchResults();
		
		// commodity title
		final VerticalPanel v = new VerticalPanel();

		panel.show();
		
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutputType(DataViewerBoxContent.GET.toString());
		qvo.setResourceType(DataViewerBoxContent.RESULT_SEARCH_INFOS.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		final HashMap<String, String> itemMap = new HashMap<String, String>();
		itemMap.put(itemCode.getCode(), itemCode.getLabel());
		qvo.setItems(itemMap);
		
		// For each domain check if exist and get all the related info
		
		for(DWCodesModelData groupCode : groupCodes.keySet()) {
			for (final DWCodesModelData domainCode: groupCodes.get(groupCode)) {
				
				final HashMap<String, String> groupMap = new HashMap<String, String>();
				groupMap.put(groupCode.getCode(), groupCode.getLabel());
				
				final HashMap<String, String> domainMap = new HashMap<String, String>();
				domainMap.put(domainCode.getCode(), domainCode.getLabel());
				qvo.setDomains(domainMap);
				
				// call the server and render the informs
				try {
					DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
						
						@SuppressWarnings("unchecked")
						public void onSuccess(DWFAOSTATResultVO vo) {
							loadingWindow.destroyLoadingBox();

							
							if ( !vo.getCodes().isEmpty() ) {


								if ( !searchTextBox.getSearchResults().getTitleAdded().containsKey(itemCode.getCode())) {
									System.out.println("adding ---> " + itemCode.getCode() + " | " + itemCode.getLabel());
									searchTextBox.getSearchResults().getTitleAdded().put(itemCode.getCode(), itemCode.getLabel());
									v.add(searchResults.buildComodityTitle(itemCode.getLabel()));
								}
								else {
									System.out.println("already added ---> " + itemCode.getCode() + " | " + itemCode.getLabel());
								}

									
									
								HashMap<String, String> elementsMap = new HashMap<String, String>();
								for( DWCodesModelData element : vo.getCodes()) {
									elementsMap.put(element.getCode(), element.getLabel());
								}
								
								FAOSTATSearchSingleResult singleResult = new FAOSTATSearchSingleResult();
								
								v.add(singleResult.build(searchTextBox, groupMap, domainMap, elementsMap, itemMap));
								v.layout();

							}
						}
						
						public void onFailure(Throwable arg0) {
							loadingWindow.destroyLoadingBox();
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			
			}
			
			panel.add(v);
			v.layout();
			panel.layout();
		}
		
		**/
	}
	
	/** TODO: FINISH THE TOKENIZE...**/
	private static List<String> tokenizeSearchTextString(String textString) {
		List<String> tokens = new ArrayList<String>();
		
		textString = cleanString(textString);
		textString = textString.trim();
		
		System.out.println("clean textString: -" + textString + "-");

		
		String[] tempTokens;
		 
		/* delimiter */
		String delimiter = " ";
		/* given string will be split by the argument delimiter provided. */
		tempTokens = textString.split(delimiter);
		/* print substrings */
		for(int i =0; i < tempTokens.length ; i++) {
		    System.out.println(tempTokens[i]);
		    /** TODO: Tokenize and black listes...**/
//			if ( tempTokens[i].length() > 2 ) {
				System.out.println("adding: -" + tempTokens[i].trim() +"-"+ tempTokens[i].length());
				if ( tempTokens[i].length() > 0 ) { 
					tokens.add(tempTokens[i].trim());
				}
				
//			}
//			else {
//				System.out.println("no adding: -" + tempTokens[i] +"-");
//			}
		}
		
		
		System.out.println("TOKENS: " + tokens);
		return tokens;
	}
	
	private static String cleanString(String textString) {
		textString = textString.replace(",", "");
		textString = textString.replace(";", "");
		textString = textString.replace(".", "");
		return textString;
	} 
	
	
	private static void getSearchParameters( FAOSTATSearchTextBox searchTextBox,  FAOSTATSearchSingleResult searchResult,  DWFAOSTATQueryVO qvo) {
		Boolean isTradeMatrix = false;
			
		if ( !searchResult.getDomains().isEmpty() ) {
			qvo.setDomains(searchResult.getDomains());
			// TODO: not good for joins..
			for ( String domainCode : searchResult.getDomains().keySet()) {
				isTradeMatrix = FAOSTATDimensionConstant.isTradeMatrix(domainCode);
			}
		}
		qvo.setTradeMatrix(isTradeMatrix);

		
		if ( !searchResult.getElements().isEmpty() )
			qvo.setElements(searchResult.getElements());
			
		if ( !searchResult.getItems().isEmpty() )
			qvo.setItems(searchResult.getItems());
		
		// getting also the area and date filters
		getFilters(searchTextBox.getAdvancedPanel(), qvo);
		
		// select
		FAOSTATConstants.selectLabels(qvo, false, true, true, isTradeMatrix);
	}
	
	
	// TODO: passing all the informations required **/
	public static Listener<ComponentEvent> quickDownloadCSV(final FAOSTATSearchTextBox searchTextBox, final FAOSTATSearchSingleResult searchResult) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				quickDownloadCSVAgent(searchTextBox, searchResult);
			}
		};
	}
	

	

	public static void quickDownloadCSVAgent(FAOSTATSearchTextBox searchTextBox, final FAOSTATSearchSingleResult searchResult) {
		
		tokensAsync = 0;
		tokensReached = 0;
		
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setOutput(DataViewerBoxContent.TABLE.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.EXPORT_CSV.toString());
		
		// By default gets the regions and countries
		qvo.setIsRegionLevel(true);
		qvo.setIsCountryLevel(true);
		
		getSearchParameters(searchTextBox, searchResult, qvo);
			
		final LoadingWindow loadingWindow = new LoadingWindow(FAOSTATLanguage.print().downloadingData(), FAOSTATLanguage.print().download(), FAOSTATLanguage.print().loading());
		loadingWindow.showLoadingBox();
		
		if ( tokensAsync > 0) {
			checkQuickDownloadIfAllTheAsyncCallsAreReturned(qvo, loadingWindow);
		}
		else
			quickDownloadAgentCall(qvo, loadingWindow);
	}
	
	private static void getFilters(FAOSTATSearchAdvancedPanel advancedPanel, DWFAOSTATQueryVO qvo) {
		HashMap<String, String> areas = new HashMap<String, String>();
		try {
			FAOSTATDownloadController.getCodes(areas, advancedPanel.getAreas().getSelectorPanel().getList(), qvo.getDomains(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		}catch (Exception e) {
		}
		

		// Switch between TradeMatrix Table and Data Table
		System.out.println("qvo.getTradeMatrix(): " + qvo.getTradeMatrix());
		if ( !qvo.getTradeMatrix() ) {
			qvo.setAreas(areas);
		}
		else { 
			qvo.setReportedAreas(areas);
		}
		
//		System.out.println("AREAS: " + qvo.getTradeMatrix() + " | " + qvo.getReportedAreas() );
		
		HashMap<String, String> years = new HashMap<String, String>(); 
		try {
			FAOSTATDownloadController.getCodes(years, advancedPanel.getYears().getList(), qvo.getDomains(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
		}catch (Exception e) {
		}
		qvo.setYears(years);
		
	}
	
	public static Listener<ComponentEvent> quickDownloadAllCluster(final FAOSTATSearchTextBox searchTextBox, final List<FAOSTATSearchSingleResult> searchSingleResults) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				quickDownloadAllClusterAgent(searchTextBox, searchSingleResults);
			}
		};
	}
	
	public static void quickDownloadAllClusterAgent(final FAOSTATSearchTextBox searchTextBox, final List<FAOSTATSearchSingleResult> searchSingleResults) {
		
		System.out.println("quickDownloadAllClusterAgent");
		
		tokensAsync = 0;
		tokensReached = 0;
		
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		FAOSTATConstants.setFAOSTATDBSettings(qvo);	
		qvo.setOutput(DataViewerBoxContent.TABLE.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.EXPORT_CSV.toString());
		
		// By default gets the regions and countries
		qvo.setIsRegionLevel(true);
		qvo.setIsCountryLevel(true);
//		qvo.setOutputType(DataViewerBoxContent.TABLE.toString());
//		qvo.setResourceType(DataViewerBoxContent.EXPORT_CSV.toString());
		
		// TODO: check the search for TRADE MATRIX **/
		FAOSTATConstants.selectLabels(qvo, true, true, true, false);

		for(FAOSTATSearchSingleResult searchResult : searchSingleResults) {
			if ( !searchResult.getDomains().isEmpty() )
				qvo.getDomains().putAll(searchResult.getDomains());
			
			if ( !searchResult.getElements().isEmpty() )
				qvo.getElements().putAll(searchResult.getElements());
			
			if ( !searchResult.getItems().isEmpty() )
				qvo.getItems().putAll(searchResult.getItems());
		}
		
		
		// getting the filters
		getFilters(searchTextBox.getAdvancedPanel(), qvo);
		
		final LoadingWindow loadingWindow = new LoadingWindow(FAOSTATLanguage.print().downloadingData(), FAOSTATLanguage.print().download(), FAOSTATLanguage.print().loading());
		loadingWindow.showLoadingBox();
		
		if ( tokensAsync > 0) {
			System.out.println("waiting the async call..");
			checkQuickDownloadIfAllTheAsyncCallsAreReturned(qvo, loadingWindow);
		}
		else
			quickDownloadAgentCall(qvo, loadingWindow);
		
//		try {
//			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
//				
//
//				public void onSuccess(DWFAOSTATResultVO vo) {
//					System.out.println("export finished");				
//					loadingWindow.destroyLoadingBox();
//					com.google.gwt.user.client.Window.open("../exportObject/" + vo.getText(), "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//
//				}
//				
//				public void onFailure(Throwable arg0) {
//					loadingWindow.destroyLoadingBox();
//				}
//			});
//		} catch (FenixGWTException e) {
//			e.printStackTrace();
//		}


	}
	
	private static void quickDownloadAgentCall(DWFAOSTATQueryVO qvo, final LoadingWindow loadingWindow) {
		System.out.println("quickDownloadAllClusterAgentCall");				
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
	
				public void onSuccess(DWFAOSTATResultVO vo) {
					System.out.println("export finished");				
					loadingWindow.destroyLoadingBox();
					com.google.gwt.user.client.Window.open("../exportObject/" + vo.getText(), "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
	
				}
				
				public void onFailure(Throwable arg0) {
					loadingWindow.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}
	}
	
	
	

	
	// TODO: passing all the informations required **/
	public static Listener<ComponentEvent> showTable(final FAOSTATSearchTextBox searchTextBox, final FAOSTATSearchSingleResult searchResult) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				showTableAgent(searchTextBox, searchResult);
			}
		};
	}
	
	public static void showTableAgent(final FAOSTATSearchTextBox searchTextBox, final FAOSTATSearchSingleResult searchResult) {
		
		System.out.println("showTableAgent");
		/** TODO: Should call the FAOSTATDownloadController.tokensAsync or should be internal...or extends FAOSTATDownloadController.. or FAOSTATMainController **/
		
		tokensAsync = 0;
		tokensReached = 0;
	
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		FAOSTATConstants.setFAOSTATDBSettings(qvo);	
		qvo.setOutput(DataViewerBoxContent.TABLE.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.HTML.toString());
		
		// By default gets the regions and countries
		qvo.setIsRegionLevel(true);
		qvo.setIsCountryLevel(true);	
		qvo.setLimit(10);
		
		getSearchParameters(searchTextBox, searchResult, qvo);
			
		/** TODO: check the search...*/
//		Boolean isTradeMatrix = false;
//		
//		System.out.println("searchResult.getDomains(): " + searchResult.getDomains());
//			
//		if ( !searchResult.getDomains().isEmpty() ) {
//			qvo.setDomains(searchResult.getDomains());
//			// TODO: not good for joins..
//			for ( String domainCode : searchResult.getDomains().keySet()) {
//				isTradeMatrix = FAOSTATDimensionConstant.isTradeMatrix(domainCode);
//			}
//			System.out.println("isTradeMatrix: " + isTradeMatrix);
//		}
//		qvo.setTradeMatrix(isTradeMatrix);
//
//		
//		if ( !searchResult.getElements().isEmpty() )
//			qvo.setElements(searchResult.getElements());
//			
//		if ( !searchResult.getItems().isEmpty() )
//			qvo.setItems(searchResult.getItems());
//		
//		// getting also the area and date filters
//		getFilters(searchTextBox.getAdvancedPanel(), qvo);
//		
//		// select
//		FAOSTATConstants.selectLabels(qvo, false, true, true, isTradeMatrix);

		
		final LoadingWindow loadingWindow = new LoadingWindow(FAOSTATLanguage.print().creatingTables(), FAOSTATLanguage.print().pleaseWait()+" ..", FAOSTATLanguage.print().loading());
		loadingWindow.showLoadingBox();
		
		if ( tokensAsync > 0) {
			System.out.println("waiting the async call..");
			checkShowTableIfAllTheAsyncCallsAreReturned(qvo, searchResult, loadingWindow);
		}
		else {
			showTableAgentCall(qvo, searchResult, loadingWindow);
		}

	}
	
	private static void showTableAgentCall(DWFAOSTATQueryVO qvo, FAOSTATSearchSingleResult searchResult, final LoadingWindow loadingWindow) {
		System.out.println("SHOWING TABLE...");
		
		FAOSTATDownloadTablePanel tablePanel = searchResult.getTablePanel();	
		tablePanel.getTablesPanel().removeAll();
		tablePanel.getViewMorePanel().hide();
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		System.out.println("qvo.getAreas(): " + qvo.getAreas());
		System.out.println("qvo.getReportedAreas(): " + qvo.getReportedAreas());
		

		loadingWindow.destroyLoadingBox();

		qvo.setTitle("");

		
		tablePanel.getTablesPanel().add(panel);
		
		tablePanel.getTablesPanel().add(DataViewerClientUtils.addVSpace(15));
		
		
		searchResult.getTablePanel().getPanel().layout();
		
		/** TODO: add to the normal table **/
		FAOSTATPivotTableController.addPivotTable(panel, qvo, "750", "300");

	}
	
	
	public static void hideByDomainPanels(FAOSTATSearchClusterResults clusterResults, String domainCode) {
//		System.out.println("domainCode: " + domainCode);
		
		
		int i = 0;
		for(String code : clusterResults.getClusterResult().keySet()) {
//			System.out.println("CODE: " + code);

			Integer added = 0;
			for(FAOSTATSearchSingleResult singleResult : clusterResults.getClusterResult().get(code).getSearchSingleResults()) {
				if ( singleResult.getDomains().containsKey(domainCode)) {
//					System.out.println("---> ok domain code");
					singleResult.getPanel().show();
					added++;
					
				}
				else {
//					System.out.println("---> no domain code");
					singleResult.getPanel().hide();
				}
			}
			
			// if there are not result for that domain
			if ( added > 0 ) { 
				i++;
				clusterResults.getClusterResult().get(code).getPanel().show();
			}
			else {
				clusterResults.getClusterResult().get(code).getPanel().hide();
			}
			
			clusterResults.getClusterResult().get(code).getDownloadAll().hide();
			
			clusterResults.getPaging().getCounter().setHtml("<div class='search_paging_title'>(<i>"+FAOSTATLanguage.print().totalResults()+":</i> "+ i +")</div>");
			clusterResults.getPaging().getPanel().show();
			clusterResults.getPaging().getPanel().layout();
		}
	}
	
	// TODO: passing all the informations required **/
	public static Listener<ComponentEvent> showAllDomains(final FAOSTATSearchClusterResults clusterResults) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				showAllAgent(clusterResults);
			}
		};
	}
	
	public static void showAllAgent(FAOSTATSearchClusterResults clusterResults) {
		

		int i =0;
		for(String code : clusterResults.getClusterResult().keySet()) {
			i++;
			for(FAOSTATSearchSingleResult singleResult : clusterResults.getClusterResult().get(code).getSearchSingleResults()) {
				singleResult.getPanel().show();
			}
			clusterResults.getClusterResult().get(code).getPanel().show();
			clusterResults.getClusterResult().get(code).getDownloadAll().show();
			clusterResults.getClusterResult().get(code).getPanel().show();
			
			
		}
		
		clusterResults.getSearchFilter().getTree().disableEvents(true);
		clusterResults.getSearchFilter().getTree().getSelectionModel().deselectAll();
		clusterResults.getSearchFilter().getTree().enableEvents(true);
		
		
		clusterResults.getPaging().getCounter().setHtml("<div class='search_paging_title'>(<i>"+FAOSTATLanguage.print().totalResults()+":</i> "+ i +")</div>");
		clusterResults.getPaging().getPanel().show();
		clusterResults.getPaging().getPanel().layout();
	}
	
	public static void checkShowTableIfAllTheAsyncCallsAreReturned(final DWFAOSTATQueryVO qvo, final FAOSTATSearchSingleResult searchResult, final LoadingWindow loadingWindow) {
		timer = new Timer() {
			public void run() {
				System.out.println("checkShowTableIfAllTheAsyncCallsAreReturned tokensAsync: " + tokensAsync);
				System.out.println("checkShowTableIfAllTheAsyncCallsAreReturned tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
					/** TODO: start download **/
					System.out.println("start checkShowTableIfAllTheAsyncCallsAreReturned...");
					showTableAgentCall(qvo, searchResult, loadingWindow);
					timer.cancel();
				}

			}
		};
		timer.scheduleRepeating(10);
	}
	
	public static void checkQuickDownloadIfAllTheAsyncCallsAreReturned(final DWFAOSTATQueryVO qvo, final LoadingWindow loadingWindow) {
		timer = new Timer() {
			public void run() {
				System.out.println("checkAllClusterDownloadIfAllTheAsyncCallsAreReturned tokensAsync: " + tokensAsync);
				System.out.println("checkAllClusterDownloadIfAllTheAsyncCallsAreReturned tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
					/** TODO: start download **/
					System.out.println("start checkAllClusterDownloadIfAllTheAsyncCallsAreReturned...");
					quickDownloadAgentCall(qvo, loadingWindow);
					timer.cancel();
				}

			}
		};
		timer.scheduleRepeating(10);
	}

	public static Boolean searchingCodes(String textString){  
		 
		  Boolean searchingCodes = true;
		  for(int i=0; i<textString.length(); i++) {
				System.out.println("char: -" + textString.charAt(i)+"-");
				 //If we find a non-digit character we return false.
	            if (!Character.isDigit(textString.charAt(i))) {
	            	if ( textString.charAt(i) != '-') {
	            		searchingCodes = false;
	            		return searchingCodes;
	            	}
	            }
		  }
		  return searchingCodes;
		 } 


}
