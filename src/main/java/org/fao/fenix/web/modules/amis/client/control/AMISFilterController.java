package org.fao.fenix.web.modules.amis.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.amis.client.view.home.AMISDatasource;
import org.fao.fenix.web.modules.amis.client.view.utils.AMISHomeUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.AMISParametersPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISAreasFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISMultiselectionFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISMultiselectionItem;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.TitlePanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.constants.AMISInterfaceConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISProductionInterfaceConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISConstantsVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISFilterVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AMISFilterController {

	// TODO: DON'T use it, call directly the coding systems and retrieve
	// the codes when the xml is created (?)
	public static Integer tokensFiltersAsync = 0;

	public static Integer tokensFiltersReached = 0;

	private static Timer timer;

	
  
  public static void buildFilters(AMISDatasource visualization, List<AMISFilterVO> filters, AMISCodesModelData code, String selectedArea) {
		VerticalPanel p = new VerticalPanel();
		
		// inizialize token (this is for the async calls)
		tokensFiltersAsync = 0;
		tokensFiltersReached = 0;
		
		List<AMISFilter> f = new ArrayList<AMISFilter>();
		visualization.getFiltersPanel().setFilters(f);
		
		HorizontalPanel h = new HorizontalPanel();
		for (AMISFilterVO filter : filters) {
			
			h.add(buildFilter(visualization, filter, code, selectedArea));
			h.add(FormattingUtils.addHSpace(10));
		}
		
		p.add(FormattingUtils.addVSpace(10));
		p.add(h);
		
		visualization.getFiltersPanel().getPanel().removeAll();
		visualization.getFiltersPanel().getPanel().add(p);
				
		visualization.getFiltersPanel().getPanel().layout();
		
	}
  
  public static HorizontalPanel buildFilter(final AMISDatasource visualization, AMISFilterVO filter, AMISCodesModelData code, String selectedArea) {
		AMISConstants c = AMISConstants.valueOf(filter.getFilterType());
		
		System.out.println("buildFilter: FilterType "+filter.getFilterType());
		
		switch(c) {
			case AMIS_COUNTRIES: return buildAggregatedAreasFilter(visualization, filter, filter.getFilterType(), code);			
			default: return buildStandardFilter(visualization, filter, filter.getFilterType(), code);
		}
	}
  
  public static HorizontalPanel buildAggregatedAreasFilter(final AMISDatasource visualization, AMISFilterVO filter, String title, AMISCodesModelData code) {
			tokensFiltersAsync++;
			AMISAreasFilter filterPanel = new AMISAreasFilter();
			
			visualization.getFiltersPanel().getFilters().add(filterPanel);
			
			return filterPanel.buildAggregatedAreasFilter(visualization, title, filter.getFilterType(), filter.getIsMultiSelection(), code, filter.getDefaultCodes());
		}

  public static SelectionChangedListener<AMISCodesModelData> updateAggregatedAreasFilter(final AMISDatasource visualization, final AMISAreasFilter filter, final ComboBox<AMISCodesModelData> combo, final String filterType, final AMISCodesModelData code, final HashMap<String, String> selectedCodes) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {

				updateAggregatedAreasFilterAgent(visualization, filter, combo, filterType, code, selectedCodes);
			}
		};
	}
  
  public static HorizontalPanel buildStandardFilter(final AMISDatasource visualization, AMISFilterVO filter, String title, AMISCodesModelData code) {
			if ( filter.getUseCodingSystem() ) {
				tokensFiltersAsync++;
			}
			AMISFilter filterPanel = new AMISFilter();
			
			visualization.getFiltersPanel().getFilters().add(filterPanel);
			
			return filterPanel.buildStandardFilter(visualization, filter, code, title);
		}
  
  /**
	 * This method retrieve the countries of the selected area codes, and add to the area filter
	 * 
	 * @param filter
	 * @param values
	 */
	public static void updateAggregatedAreasFilterAgent(final AMISDatasource visualization, AMISAreasFilter filter, ComboBox<AMISCodesModelData> combo, final String filterType, final AMISCodesModelData code, final HashMap<String, String> selectedCodes) {
		/** TODO: Call to retrieve the countries related to the AREA 
		 * 		  and add the codes to the filter **/

		
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.AMIS_COUNTRIES.toString());		
		
		final Map<String, String> aggregationfilter = new HashMap<String, String>();
		aggregationfilter.put(combo.getSelection().get(0).getCode(), combo.getSelection().get(0).getLabel());
		qvo.getAggregationsFilter().putAll(aggregationfilter);
		qvo.getDomains().put(code.getCode(), code.getLabel());

		
		if ( checkIfMultiselection(combo.getSelection().get(0).getCode())) {
			
			// TODO: keep track of the old value, so if the multiselection 
			// window is closed the coherence is maintained
			
			AMISMultiSelectionController.multiselectionAggregatedAreasAgent(visualization, filter, filterType + "_MULTI", code);
		}
		else {
			// build the views
			try {
				AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
					
					@SuppressWarnings("unchecked")
					public void onSuccess(AMISResultVO vo) {

						selectedCodes.clear();
						for(AMISCodesModelData code : vo.getCodes()) {
							selectedCodes.put(code.getCode(), code.getLabel());
						}
						
					    HashMap<String, List<AMISQueryVO>> hmVOs = visualization.getSettings().getQvos();
						List<AMISQueryVO> qvos = hmVOs.get("DEFAULT");
						
						for (AMISQueryVO qvo : qvos ) {
							qvo.getAggregationsFilter().clear();
							qvo.getAggregationsFilter().putAll(aggregationfilter);
							addToFilters(qvo, selectedCodes, filterType, null);
						}
						
						// parameters
						buildParameters(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());

						buildViews(visualization.getCenterPanel(), qvos, "aggregated");
	//					System.out.println("qvos: " + qvos.size());
					}
					
					public void onFailure(Throwable arg0) {
		
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
  
  /** TODO: remove the combo parameter, and selected codes etc...pass filter **/
	public static SelectionChangedListener<AMISCodesModelData> updateFilter(final AMISDatasource visualization, final AMISFilter filter, final ComboBox<AMISCodesModelData> combo, final String filterType, final AMISCodesModelData domaincode, final HashMap<String, String> selectedCodes) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				updateFilterAgent(visualization, filter, combo, filterType,domaincode, selectedCodes);
			}
		};
	}
	
public static void updateFilterAgent(AMISDatasource visualization, AMISFilter filter, ComboBox<AMISCodesModelData> combo, String filterType, AMISCodesModelData domaincode, HashMap<String, String> selectedCodes) {
		
		// check if the selected code is multiselection
		
		System.out.println("combo.getSelection().get(0).getCode(): &&&&&" + combo.getSelection().get(0).getCode());
		System.out.println("checkIfMultiselection: " + checkIfMultiselection(combo.getSelection().get(0).getCode()));

		if ( checkIfMultiselection(combo.getSelection().get(0).getCode())) {
			
			// TODO: call the multiselection 
			multiselectionFilterAgent(visualization, filter, filterType +"_MULTI", domaincode);
		}
		else {	
			// build the view
			String code = combo.getSelection().get(0).getCode();
			String label = combo.getSelection().get(0).getLabel();
			String domain = combo.getSelection().get(0).getDomain();
			
			selectedCodes.clear();
			selectedCodes.put(code, label);
			
			
		    HashMap<String, List<AMISQueryVO>> hmVOs = visualization.getSettings().getQvos();
			List<AMISQueryVO> qvos = hmVOs.get("DEFAULT");
				
			for (AMISQueryVO qvo : qvos ) {
				/* more detailed context title to use in export **/
				String exportTitle = qvo.getTitle() + ":";
				
				if(visualization.getDataSourceSelectionPanel().getSelectedArea()!=null){
					exportTitle += " "+ visualization.getDataSourceSelectionPanel().getSelectedArea().getLabel();
				}
				
				exportTitle += " " + combo.getSelection().get(0).getLabel();
				
				//re-set how the years are viewed for Rice imports and exports chart
				//re-set how the elements based on whether it is the global/country view
				if(filterType.equalsIgnoreCase(AMISConstants.AMIS_PRODUCTS_WITHOUT_POPULATION.toString())){
					if(qvo.getTitle().equalsIgnoreCase("Imports and Exports")){
						AMISController.handleRiceTradeYears(qvo, label);
						AMISController.handleTradeElements(qvo, visualization.getDataSourceSelectionPanel().getSelectedArea());
					}
					if(qvo.getTitle().equalsIgnoreCase("Market Summary")){
						AMISController.handleTradeElements(qvo, visualization.getDataSourceSelectionPanel().getSelectedArea());								
					}
					
				}
				
				if(AMISController.getCurrentDatasetView()!=null) {
					
					AMISCurrentDatasetView currentDataset = AMISController.getCurrentDatasetView();
										
					switch(currentDataset) {
//					case CBS: exportTitle += " (AMIS Statistics Source: FAO-CBS)"; break;
                    case CBS: exportTitle += " (AMIS Statistics Source: FAO-AMIS)"; break;
					case PSD: exportTitle += " (AMIS Statistics Source: USDA-PSD)"; break;
					default: exportTitle += " ";
				    }
				
				}
					
				
				
				qvo.setExportTitle(exportTitle);
				
				addToFilters(qvo, selectedCodes, filterType, domain);
			}
			
			
		
			
			// parameters
			//buildParameters(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());

			buildViews(visualization.getCenterPanel(), qvos, label);
			
			//visualization.getTitlePanel().getPanel().removeAll();
			if(visualization.getDataSourceSelectionPanel().getSelectedArea()!=null)
				visualization.getTitlePanel().build(label, visualization.getDataSourceSelectionPanel().getSelectedArea().getLabel());
			else
				visualization.getTitlePanel().build(label, "WORLD");
			
			visualization.getTitlePanel().getPanel().layout();
			
		}
	}

private static void multiselectionFilterAgent(final AMISDatasource visualization, final AMISFilter filter, final String filterTypeMultisel, final AMISCodesModelData code) {
	filter.getMultiselection().build(visualization, filter, filterTypeMultisel, code);
}

public static SelectionListener<ButtonEvent> updateMultiselectionFilter(final Window window, final AMISDatasource visualization, final AMISFilter filter, final List<AMISMultiselectionItem> multiSelectionItems, final String filterType, final AMISCodesModelData code, final HashMap<String, String> selectedCodes) {
	return new SelectionListener<ButtonEvent>() {
		public void componentSelected(ButtonEvent ce) {
			updateMultiselectionFilterAgent(window, visualization, filter, multiSelectionItems, filterType, code, selectedCodes);
		}
	};
}





private static void updateMultiselectionFilterAgent(Window window, final AMISDatasource visualization, AMISFilter filter, List<AMISMultiselectionItem> multiSelectionItems, final String filterType, final AMISCodesModelData code, final HashMap<String, String> selectedCodes) {
	
	System.out.println("updateFilterAgent");
	selectedCodes.clear();
	
	for(AMISMultiselectionItem item : multiSelectionItems) {
		if ( item.getCheckbox().getValue() ) {
			selectedCodes.put(item.getCode().getCode(), item.getCode().getLabel());
		}
	}


	if( selectedCodes.isEmpty() ) {
		FenixAlert.alert("Selection Error", "Please select some codes or close the window");
	}
	else{
		window.close();
		System.out.println("selectedCodes: " + selectedCodes);
		
	    HashMap<String, List<AMISQueryVO>> hmVOs = visualization.getSettings().getQvos();
		List<AMISQueryVO> qvos = hmVOs.get("DEFAULT");
		
		for (AMISQueryVO qvo : qvos ) {
			// TODO: pass also domain if needed
			addToFilters(qvo, selectedCodes, filterType, null);
		}
		
		// parameters
		//buildParameters(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());
		
		buildViews(visualization.getCenterPanel(), qvos, "aggregated");
	}
	
	
	// set the combo box
	if ( selectedCodes.size() > 1 ) {
		// set multiselection combo box
		List<AMISCodesModelData> store = filter.getStore().getModels();
		for(AMISCodesModelData value : store) {
			if ( value.getCode().equals("MULTI")) {
				filter.getCombo().enableEvents(false);
				filter.getCombo().setValue(value);
				filter.getCombo().enableEvents(true);
				break;
			}
		}
		
	}
	// single value
	else if ( selectedCodes.size() == 1 ) {
		List<AMISCodesModelData> store = filter.getStore().getModels();
		for(String key : selectedCodes.keySet() ) {
			Boolean added = false;
			for(AMISCodesModelData value : store) {
				if ( value.getCode().equals(key)) {
					filter.getCombo().enableEvents(false);
					filter.getCombo().setValue(value);
					filter.getCombo().enableEvents(true);
					added = true;
					break;
				}
			}
			if ( !added ) {
				// if any coded is retrieved as default if set the multiselection value
				// (i.e. if the combobox contains the regions and the multiselection is based on 
				//  countries and the countries selected are just one)
				for(AMISCodesModelData value : store) {
					if ( value.getCode().equals("MULTI")) {
						filter.getCombo().enableEvents(false);
						filter.getCombo().setValue(value);
						filter.getCombo().enableEvents(true);
						break;
					}
				}
			}
		}
	}
	
	
	
	
}




	public static void loadCodingSystem(final AMISDatasource visualize, final ComboBox<AMISCodesModelData> combo, final ListStore<AMISCodesModelData> store, final String filterType, AMISCodesModelData code)  {

		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.codingSystemMap.get(filterType));

		AMISConstantsVO.setLanguageSettings(qvo);
		//should be either country, domain

		HashMap<String, String> item = new HashMap<String, String>();
		item.put(code.getCode(), code.getLabel());
		qvo.setAreas(item);

		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {

					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

					store.removeAll();
					for(AMISCodesModelData code : vo.getCodes()) {
						store.add(code);
						map.put(code.getCode(), code.getLabel());
					}


					// TODO: set a default code
					combo.setValue(store.getAt(0));
					combo.enableEvents(true);
				}

				public void onFailure(Throwable arg0) {

				}
			});
		} catch (FenixGWTException e) {

			e.printStackTrace();
		}

	}

	public static void loadCodingSystem(final AMISMultiselectionFilter filterPanel, final ListField<AMISCodesModelData> combo, final ListStore<AMISCodesModelData> store, final String filterType, AMISCodesModelData code)  {

		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.codingSystemMap.get(filterType));

		AMISConstantsVO.setLanguageSettings(qvo);
	
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(code.getCode(), code.getLabel());
		qvo.setDomains(domain);

		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {

					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

					store.removeAll();
					for(AMISCodesModelData code : vo.getCodes()) {
						store.add(code);
						map.put(code.getCode(), code.getLabel());
					}


					// TODO: set a default code
//					combo.setValue(store.getAt(0));
					combo.enableEvents(true);

					filterPanel.getSelectionPanel().layout();
				}

				public void onFailure(Throwable arg0) {

				}
			});
		} catch (FenixGWTException e) {

			e.printStackTrace();
		}

	}


public static void loadCodingSystem(final AMISMultiselectionFilter filterPanel, final String filterType, AMISCodesModelData code, final HashMap<String, String> selectedCodes)  {
		
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.codingSystemMap.get(filterType));		
		
		AMISConstantsVO.setLanguageSettings(qvo);
			
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(code.getCode(), code.getLabel());
		qvo.setDomains(domain);
			
		System.out.println("AMISConstants.codingSystemMap.get(filterType): " + AMISConstants.codingSystemMap.get(filterType));
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
					
				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {
						
					// cleaning
					filterPanel.getMultiSelectionItems().clear();
					filterPanel.getSelectionPanel().removeAll();
					filterPanel.getSelectionPanelContent().removeAll();
					
					/** TODO: build in a filter class...**/
					Html title = new Html("<div class='visualize_multiselection_title'>List of items:</div>");
					filterPanel.getSelectionPanel().add(FormattingUtils.addVSpace(5));
					filterPanel.getSelectionPanel().add(title);
					filterPanel.getSelectionPanel().add(FormattingUtils.addVSpace(5));
					filterPanel.getSelectionPanel().add(filterPanel.getSelectionPanelContent());

					
					for(AMISCodesModelData code : vo.getCodes()) {
						AMISMultiselectionItem item = new AMISMultiselectionItem(filterPanel);
						
//						System.out.println("item: " + code.getCode() + " | " + code.getLabel() + " | " + selectedCodes.containsKey(code.getCode()));

						
						filterPanel.getSelectionPanelContent().add(item.build(code, selectedCodes.containsKey(code.getCode())));
						
//						filterPanel.getSelectionPanelContent().layout();
						
						filterPanel.getSelectionPanel().layout();
						
						filterPanel.getMultiSelectionItems().add(item);
					}
					
					filterPanel.getSelectionPanelContent().layout();
					
					filterPanel.getSelectionPanel().layout();
										
					AMISMultiSelectionController.buildListCurrentSelectedCodes(filterPanel);
	
					filterPanel.getSelectionPanel().layout();
			
				}
				
				public void onFailure(Throwable arg0) {

				}
			});
		} catch (FenixGWTException e) {
				
			e.printStackTrace();
		}

	}

	public static void loadCodingSystem(final ComboBox<AMISCodesModelData> combo, final ListStore<AMISCodesModelData> store, final String filterType, final AMISCodesModelData code, final List<AMISCodesModelData> defaultCodes, final HashMap<String, String> selectedCodes, final Boolean isMultiselection)  {

		System.out.println("AMISProductionController: loadCodingSystem ... START");
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.codingSystemMap.get(filterType));

		AMISConstantsVO.setLanguageSettings(qvo);
		
		//HashMap<String, String> domain = new HashMap<String, String>();
		//domain.put(code.getCode(), code.getLabel());
		//qvo.setDomains(domain);

		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {

					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

					store.removeAll();
					selectedCodes.clear();

//					System.out.println("---------> DEFAULTCODES: " + defaultCodes);


					/** TODO: language... **/
					AMISCodesModelData multiselection =null;
					if ( isMultiselection ) {
						multiselection = new AMISCodesModelData("MULTI", "Multiselection");
						store.add(multiselection);
					}

					for(AMISCodesModelData code : vo.getCodes()) {
						store.add(code);
						map.put(code.getCode(), code.getLabel());

						// defualt codes TODO: Optimze...
						if ( defaultCodes != null && !defaultCodes.isEmpty() ) {
							if ( defaultCodes.size() > 1 ) {
								// set MULTI (multiselection) as default code
								combo.setValue(multiselection);
							}
							else {
								 if (code.getCode().equals(defaultCodes.get(0).getCode())) {
									 combo.setValue(code);
								}
							}
						}
						else {
							combo.setValue(store.getAt(0));
							selectedCodes.put(store.getAt(0).getCode(), store.getAt(0).getLabel());
						}
					}

					if ( defaultCodes != null && !defaultCodes.isEmpty() ) {
						for(AMISCodesModelData code : defaultCodes ) {
							selectedCodes.put(code.getCode(), map.get(code.getCode()));
						}
					}

					System.out.println("-----> SELECTED CODES: " + selectedCodes);


					// TODO: set a default code
//					combo.setValue(store.getAt(0));
					combo.enableEvents(true);



					tokensFiltersReached++;


				}

				public void onFailure(Throwable arg0) {

				}
			});
		} catch (FenixGWTException e) {

			e.printStackTrace();
		}

	}



	public static void loadCodingSystem(final ComboBox<AMISCodesModelData> combo, final ListStore<AMISCodesModelData> store, final String filterType, final AMISCodesModelData code, final Boolean isMultiselection)  {

		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.codingSystemMap.get(filterType));

		AMISConstantsVO.setLanguageSettings(qvo);
	
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(code.getCode(), code.getLabel());
		qvo.setDomains(domain);

		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {

					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

					store.removeAll();

					/** TODO: language... **/
					AMISCodesModelData multiselection =null;
					if ( isMultiselection ) {
						multiselection = new AMISCodesModelData("MULTI", "Multiselection");
						store.add(multiselection);
					}

					for(AMISCodesModelData code : vo.getCodes()) {
						store.add(code);
						map.put(code.getCode(), code.getLabel());
					}




					combo.enableEvents(true);

				}

				public void onFailure(Throwable arg0) {

				}
			});
		} catch (FenixGWTException e) {

			e.printStackTrace();
		}

	}


	public static Boolean checkIfMultiselection(String code) {
		if ( code.equals("MULTI"))
			return true;
		return false;
	}

	public static void buildTitle(TitlePanel titlePanel, String title) {
		titlePanel.build(title);
		System.out.println("buildTitle "+title);
		titlePanel.getPanel().layout();
	}

	public static void buildParameters(AMISParametersPanel parameters, List<AMISFilter> filters) {
		parameters.build(filters);
		parameters.getPanel().layout();
	}

	public static void checkIfAllTheAsyncCallsAreReturned(final AMISParametersPanel parameters, final List<AMISFilter> filters) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensFiltersAsync);
				System.out.println("tokensReached: " + tokensFiltersReached);
				if ( tokensFiltersAsync != 0 ) {
					if ( tokensFiltersAsync == tokensFiltersReached ) {
						System.out.println("ok: ");
						buildParameters(parameters, filters);
						timer.cancel();
					}
				}

				// TODO: CHECK if it works
				if ( tokensFiltersAsync == 0 && tokensFiltersReached == 0) {
					System.out.println(" tokensFiltersAsync == 0 && tokensFiltersReached == 0");
				}
			}
		};
		timer.scheduleRepeating(1000);


	}

	public static void destroy() {
		if (timer != null)
		timer.cancel();
	}




	//// JUST ADDED 27-07-2011
	/**
	 *
	 * The visualization param, has to be takes for the asynccall for the aggregated countries
	 *
	 * TODO: they could be cached somewhere?
	 *
	 * @param visualization
	 * @param qvo
	 * @param values
	 * @param filterType
	 */
	public static void addToFilters(AMISQueryVO qvo, Map<String, String> values, String filterType, String domain) {


		// this is a check if the filter is applicable to the QVO
		if (checkIfAddFilter(qvo, filterType)){

			// TODO: should be a list of domains(List<DWCOdes>
			// WORKAROUND
			if ( domain != null ) {
				Map<String, String> v = new HashMap<String, String>();
				v.put(domain, domain);
				addValuesFilter(qvo.getDomains(), v);
			}

			AMISConstants c = AMISConstants.valueOf(filterType);
			switch(c) {
				case AMIS_COUNTRIES: addValuesFilter(qvo.getAreas(), values); break;
				case AMIS_PRODUCTS: addValuesFilter(qvo.getItems(), values); break;
				case AMIS_PRODUCTS_WITHOUT_POPULATION: addValuesFilter(qvo.getItems(), values);break;
				case CCBS_PRODUCTS: addValuesFilter(qvo.getItems(), values); break;
				case AMIS_ELEMENTS: addValuesFilter(qvo.getElements(), values); break;
				case CCBS_YEARS:
							   qvo.setRunMaxDateQuery(false);
							   addValuesFilter(qvo.getYears(), values); break;

				/** TODO: Implement the between **/
				case CCBS_TIMERANGE: {
							qvo.setRunMaxDateQuery(false);
							addValuesFilter(qvo.getYears(), values);
							break;
				}

				case AGGREGATION_TYPE:
							addAggregationValue(qvo, values); break;
			}
		}
		else{
			System.out.println("FILTER: " + filterType + " not added");
		}
	}
	
	
	public static void clearFilter(AMISQueryVO qvo, String filterType) {

		AMISConstants c = AMISConstants.valueOf(filterType);
			switch(c) {
				case AMIS_COUNTRIES: clearFilter(qvo.getAreas()); break;
				case AMIS_PRODUCTS: clearFilter(qvo.getItems()); break;
				case AMIS_PRODUCTS_WITHOUT_POPULATION: clearFilter(qvo.getItems()); break;
				case CCBS_PRODUCTS: clearFilter(qvo.getItems()); break;
				case AMIS_ELEMENTS: clearFilter(qvo.getElements()); break;
				case CCBS_YEARS:
							   qvo.setRunMaxDateQuery(false);
							   clearFilter(qvo.getYears()); break;

				/** TODO: Implement the between **/
				case CCBS_TIMERANGE: {
							qvo.setRunMaxDateQuery(false);
							clearFilter(qvo.getYears());
							break;
				}

				case AGGREGATION_TYPE:
					removeAggregationValue(qvo); break;
			}
		}
		


	private static Boolean checkIfAddFilter(AMISQueryVO qvo, String filterType) {
		Boolean check = true;

		AMISConstants c = AMISConstants.valueOf(filterType);

		System.out.println("qvo.getIsFixedAvoidingYears(): " + qvo.getApplyOnlyYearFilter());
		// this is for the years if the current filter is YEARS or TIMERANGE it's applicable otherwise no
		System.out.println("true AMISConstants: " + c.toString());

		// this means that any filter can be applied
		if ( qvo.getNotApplyFilters() ) {
			return false;
		}

		if ( qvo.getApplyOnlyYearFilter() && (c.toString().equalsIgnoreCase(AMISConstants.CCBS_YEARS.toString()) || c.toString().equalsIgnoreCase(AMISConstants.CCBS_TIMERANGE.toString()))) {
			System.out.println("true AMISConstants: " + c.toString());
			return true;
		}
		else if ( qvo.getApplyOnlyYearFilter() && (!c.toString().equalsIgnoreCase(AMISConstants.CCBS_YEARS.toString()) || !c.toString().equalsIgnoreCase(AMISConstants.CCBS_TIMERANGE.toString()))) {
			System.out.println("false AMISConstants: " + c.toString());
			return false;
		}


		switch(c) {
			/** TODO: switch the different cases **/
			case AMIS_COUNTRIES:
							System.out.println("APPLY_ALL_FILTERS_EXCEPT_AREAS: " + qvo.getApplyAllFiltersExceptAreas());
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  {
								check = false;
							}
							break;

			case CCBS_PRODUCTS: break;
			case CCBS_ELEMENTS:  break;
			case CCBS_YEARS:
						System.out.println("APPLY_ALL_FILTERS_EXCEPT_YEARS: " + qvo.getApplyAllFiltersExceptYears());
						// this is to avoid to add the filter
						if ( !qvo.getApplyAllFiltersExceptYears() ) {
							check = true;
						}
						else if ( qvo.getApplyAllFiltersExceptYears() )  {
							check = false;
						}
				break;

			/** TODO: Implement the between **/
			case CCBS_TIMERANGE: {
					System.out.println("APPLY_ALL_FILTERS_EXCEPT_YEARS: " + qvo.getApplyAllFiltersExceptYears());
					// this is to avoid to add the filter
					if ( !qvo.getApplyAllFiltersExceptYears() ) {
						check = true;
					}
					else if ( qvo.getApplyAllFiltersExceptYears() ) {
						check = false;
					}
					break;
			}

			case AGGREGATION_TYPE:
					System.out.println("APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE: " + qvo.getApplyAllFiltersExceptAggregrationType());
					// this is to avoid to add the filter
					if ( !qvo.getApplyAllFiltersExceptAggregrationType() ) {
						check = true;
					}
					else if ( qvo.getApplyAllFiltersExceptAggregrationType() ) {
						check = false;
					}

					break;
		}

		return check;
	}

	/** TODO: Change it... **/
	public static void addAggregationValue(AMISQueryVO qvo, Map<String, String> values) {
		for( String key : values.keySet()) {
			qvo.setAggregationType(key);
		}
	}
	
	public static void removeAggregationValue(AMISQueryVO qvo) {
		qvo.setAggregationType(null);
	}


	public static void addValuesFilter(Map<String, String> filter, Map<String, String> values) {
		filter.clear();
		filter.putAll(values);
	}
	
	public static void clearFilter(Map<String, String> filter) {
		filter.clear();
	}
	
public static void buildViews(ContentPanel panel, List<AMISQueryVO> qvos, String title) {
	System.out.println("buildViews Start filter !!!!!!!!!!!!!!!!!! ");
		panel.removeAll();
		if(qvos!=null)
		{
			System.out.println("buildViews Start filter Before !!!!!!!!!!!!!!!!!! "+ qvos.size());
			boolean found=false;
			AMISQueryVO qvoFound = null;
			for(AMISQueryVO qvo: qvos)
			{
				if(qvo!=null)
				{
					AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
					if(content.toString().equals(AMISConstants.FOOTNOTE_TABLE.toString()))
					{
						System.out.println("buildViews Start filter remove!!!!!!!!!!!!!!!!!! ");
						qvos.remove(qvo);
						break;
					}
				}
			}
			for(AMISQueryVO qvo: qvos)
			{
				if(qvo!=null)
				{
					AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
					if(content.toString().equals(AMISConstants.TABLE.toString()))
					{
						System.out.println("buildViews Start filter remove!!!!!!!!!!!!!!!!!! ");
						qvos.remove(qvo);
						break;
					}
				}
			}
			System.out.println("buildViews Start filter After !!!!!!!!!!!!!!!!!! "+ qvos.size());
			boolean foundCereal=false;
			for(AMISQueryVO qvo: qvos)
			{
				if(qvo!=null)
				{
					AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
					switch(content)
					{
					case CEREAL_BALANCE_TABLE:
						System.out.println("CEREAL_BALANCE_TABLE case!!!!!!!!!!!!!!!!!! ");							
						AMISQueryVO qvoNew = qvo.createCopy();
						qvos.add(qvoNew);
						foundCereal = true;
						break;
					}
				}
				if(foundCereal)
				{
					break;
				}
			}			
		}
		
		VerticalPanel v = new VerticalPanel();
		v.setStyleAttribute("backgroundColor", "#FFFFFF");
		
		
		v.setHorizontalAlign(HorizontalAlignment.LEFT);
		
		v.setScrollMode(Scroll.AUTOX);
		
		System.out.println("qvos.size(): " + qvos.size());
		
		Integer currentSize = 0;
		
		HorizontalPanel widgetsPanel = new HorizontalPanel();
		String areas = "";
		Boolean addHSpacing = false;
		for(int i=0; i < qvos.size(); i++) {
			
			AMISQueryVO qvo = qvos.get(i);
			
			//v.add(FormattingUtils.addVSpace(10));
			
			AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
			
			// Object Size (Width and Height)
			String width = getWidth(content, qvo);
			String height = getHeight(content, qvo);
			
			
			
			// Calculate width
			currentSize = currentSize + Integer.valueOf(width);
			
			System.out.println(i + ") content: " + content.name() + " | w:" + width + " | h:" + height + " (currentsize: " + currentSize + ")" + "  addHSpacing: " + addHSpacing);

			if ( currentSize < ((AMISInterfaceConstants.CENTER_PANEL_WIDTH) - AMISInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING) ) {
				if ( addHSpacing )
					widgetsPanel.add(FormattingUtils.addHSpace(AMISInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING));
				addHSpacing = true;
			}
			else {
				v.add(widgetsPanel);
			    if (i != 0)
			    	v.add(FormattingUtils.addVSpace(AMISInterfaceConstants.CENTER_PANEL_VERTICAL_SPACING));
			    
				widgetsPanel = new HorizontalPanel();
				currentSize = Integer.valueOf(width);
				addHSpacing = true;
			}
			
			/**if ( currentSize < ((AMISProductionInterfaceConstants.CENTER_PANEL_WIDTH) - AMISProductionInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING) ) {
				if ( addHSpacing )
					widgetsPanel.add(FormattingUtils.addHSpace(AMISProductionInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING));
				addHSpacing = true;
			}
			else {
				v.add(widgetsPanel);
				v.add(FormattingUtils.addVSpace(AMISProductionInterfaceConstants.CENTER_PANEL_VERTICAL_SPACING));
				widgetsPanel = new HorizontalPanel();
				currentSize = Integer.valueOf(width);
				addHSpacing = true;
			}**/

					
			switch (content) {
//			case TABLE:
//				widgetsPanel.add(AMISController.addTable(qvo, width, height));
//				break;
			case CEREAL_BALANCE_TABLE:
				widgetsPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
				widgetsPanel.add(AMISController.addTable(qvo, width, height));
				if(qvo.getAreas()!=null && 	qvo.getAreas().containsKey("999000")) { //GLOBAL
					areas = "999000";
				}
				break;	
			case CHART:
				widgetsPanel.add(AMISController.addChart(qvo, width, height));

				break;
			case FOOTNOTE_TABLE:
				//widgetsPanel.add(AMISController.createFootnoteTable(qvo, "cereal_table", areas));
				//AMISController.createFootnoteTable(qvo, "cereal_table", areas, widgetsPanel);
				widgetsPanel.add(AMISController.createFootnoteTable(qvo, "cereal_table", areas));
				break;
			}
		}
		
		v.add(widgetsPanel);
	//	v.add(AMISHomeUtils.getHtmlFootnoteTable("cereal_table", areas));
		panel.add(v);
     	panel.layout();
	}


private static String getWidth(AMISConstants constant, AMISQueryVO qvo) {
	
	if ( qvo.getWidth() != null )
		return qvo.getWidth();
	
	switch (constant) {
		case MAP:
			return AMISProductionInterfaceConstants.MAP_WIDTH;
		case TABLE:
			return AMISProductionInterfaceConstants.TABLE_WIDTH;
		case CHART:
			return AMISProductionInterfaceConstants.CHART_WIDTH;
	}
	
	return null;
}

private static String getHeight(AMISConstants constant, AMISQueryVO qvo) {
	if ( qvo.getHeight() != null )
		return qvo.getHeight();
	
	switch (constant) {
		case MAP:
			return AMISProductionInterfaceConstants.MAP_HEIGHT;
		case TABLE:
			return AMISProductionInterfaceConstants.TABLE_HEIGHT;
		case CHART:
			return AMISProductionInterfaceConstants.CHART_HEIGHT;
	}
	
	return null;
}

public static SelectionListener<IconButtonEvent> multiselectionFilter(final AMISDatasource visualization, final AMISFilter filter, final String filterTypeMultisel, final AMISCodesModelData code) {
	return new SelectionListener<IconButtonEvent>() {
		public void componentSelected(IconButtonEvent ce) {
			multiselectionFilterAgent(visualization, filter, filterTypeMultisel, code);
		}
	};
}

}
