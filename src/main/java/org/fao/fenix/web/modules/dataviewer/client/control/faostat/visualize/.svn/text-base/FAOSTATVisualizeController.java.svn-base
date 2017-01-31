package org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualizeContainer;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualizeEntry;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATVisualizeParametersPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATVisualizeTitlePanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionFilter;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstantsFilters;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class FAOSTATVisualizeController {
	
	static FAOSTATVisualizeEntry visualizeView;
	
	public static void callVisualizeView() {
		//initialize menu
		visualizeView = new FAOSTATVisualizeEntry(FAOSTATCurrentView.VISUALIZE_BY_DOMAIN);
		// TODO: call the default view
		callVisualizeViewAgent(FAOSTATCurrentView.VISUALIZE_BY_DOMAIN);
	}
	
    public static void callVisualizeSubView(FAOSTATCurrentView subView) {
		//initialize menu
		visualizeView = new FAOSTATVisualizeEntry(subView);
		// TODO: call the default view
		callVisualizeViewAgent(subView);
	}
	
	public static void callVisualizeViewAgent(FAOSTATCurrentView view) {
		visualizeView.build(view);
	}
	
	public static Listener<ComponentEvent> callVisualizeView(final FAOSTATCurrentView view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				callVisualizeViewAgent(view);
			}
		};
	}

	public static void loadCodingSystem(final FAOSTATVisualizeContainer visualize, final ComboBox<DWCodesModelData> combo, final ListStore<DWCodesModelData> store, final String filterType, DWCodesModelData code)  {	
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));	
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		//should be either country, domain	
		HashMap<String, String> item = new HashMap<String, String>();
		item.put(code.getCode(), code.getLabel());
		qvo.setAreas(item);
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {	
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
						
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
						
					store.removeAll();
					for(DWCodesModelData code : vo.getCodes()) {
						store.add(code);
						map.put(code.getCode(), code.getLabel());
					}
					// set a default code	
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
	
	public static void loadCodingSystem(final FAOSTATVisualizeMultiselectionFilter filterPanel, final ListField<DWCodesModelData> combo, final ListStore<DWCodesModelData> store, final String filterType, DWCodesModelData code)  {	
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(code.getCode(), code.getLabel());
		qvo.setDomains(domain);
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo,
					new AsyncCallback<DWFAOSTATResultVO>() {
						@SuppressWarnings("unchecked")
						public void onSuccess(DWFAOSTATResultVO vo) {
							LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
							store.removeAll();
							for (DWCodesModelData code : vo.getCodes()) {
								store.add(code);
								map.put(code.getCode(), code.getLabel());
							}
							// set a default code
							// combo.setValue(store.getAt(0));
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

	public static void loadCodingSystem(final ComboBox<DWCodesModelData> combo, final ListStore<DWCodesModelData> store, final String filterType, final DWCodesModelData code, final List<DWCodesModelData> defaultCodes, final HashMap<String, String> selectedCodes, final Boolean isMultiselection)  {	
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));	
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		HashMap<String, String> domain = new HashMap<String, String>();
		if ( code != null )
			domain.put(code.getCode(), code.getLabel());
		
		qvo.setDomains(domain);			
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {	
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();		
					store.removeAll();
					selectedCodes.clear();
					/** TODO: language... **/
					DWCodesModelData multiselection =null;
					if ( isMultiselection ) { 
						multiselection = new DWCodesModelData("MULTI", FAOSTATLanguage.print().multiselection());			
						store.add(multiselection);
					}
					for(DWCodesModelData code : vo.getCodes()) {
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
						for(DWCodesModelData code : defaultCodes ) {
							selectedCodes.put(code.getCode(), map.get(code.getCode()));
						}
					}	
					// set a default code	
//					combo.setValue(store.getAt(0));
					combo.enableEvents(true);
//					tokensFiltersReached++;
				}
				
				public void onFailure(Throwable arg0) {
				}
			});
		} catch (FenixGWTException e) {	
			e.printStackTrace();
		}

	}
	
	
	public static void loadCodingSystemTimerange(final ComboBox<DWCodesModelData> fromCombo, final ListStore<DWCodesModelData> fromStore, final ComboBox<DWCodesModelData> toCombo, final ListStore<DWCodesModelData> toStore, final String filterType, final DWCodesModelData code, final List<DWCodesModelData> fromDefaultCodes, final List<DWCodesModelData> toDefaultCodes, final HashMap<String, String> fromSelectedCodes, final HashMap<String, String> toSelectedCodes)  {	
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));	
		FAOSTATConstants.setFAOSTATDBSettings(qvo);	
		HashMap<String, String> domain = new HashMap<String, String>();
		if ( code != null) {
			domain.put(code.getCode(), code.getLabel());
		}
		qvo.setDomains(domain);			
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {	
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();		
					fromStore.removeAll();
					fromSelectedCodes.clear();					
					toStore.removeAll();
					toSelectedCodes.clear();
					for(DWCodesModelData code : vo.getCodes()) {
						fromStore.add(code);
						toStore.add(code);
						map.put(code.getCode(), code.getLabel());
						// defualt codes TODO: Optimze...FROM
						if ( fromDefaultCodes != null && !fromDefaultCodes.isEmpty() ) {
							 if (code.getCode().equals(fromDefaultCodes.get(0).getCode())) {
									 fromCombo.setValue(code);
							}
						}
						else {
							fromCombo.setValue(fromStore.getAt(0));
							fromSelectedCodes.put(fromStore.getAt(0).getCode(), fromStore.getAt(0).getLabel());
						}
						
						// defualt codes TODO: Optimze...TO
						if ( toDefaultCodes != null && !toDefaultCodes.isEmpty() ) {
							 if (code.getCode().equals(toDefaultCodes.get(0).getCode())) {
								 toCombo.setValue(code);
							}
						}
						else {
							toCombo.setValue(toStore.getAt(0));
							toSelectedCodes.put(toStore.getAt(0).getCode(), toStore.getAt(0).getLabel());
						}
						
						
					}
					
					if ( fromDefaultCodes != null && !fromDefaultCodes.isEmpty() ) {
						for(DWCodesModelData code : fromDefaultCodes ) {
							fromSelectedCodes.put(code.getCode(), map.get(code.getCode()));
						}
					}	
					
					if ( toDefaultCodes != null && !toDefaultCodes.isEmpty() ) {
						for(DWCodesModelData code : toDefaultCodes ) {
							toSelectedCodes.put(code.getCode(), map.get(code.getCode()));
						}
					}	
					fromCombo.enableEvents(true);
					toCombo.enableEvents(true);
//					tokensFiltersReached++;
//					tokensFiltersReached++;
				}
				
				public void onFailure(Throwable arg0) {
				}
			});
		} catch (FenixGWTException e) {	
			e.printStackTrace();
		}

	}
	
	
	public static void loadCodingSystem(final ComboBox<DWCodesModelData> combo, final ListStore<DWCodesModelData> store, final String filterType, final Map<String, String> domainCodes, final List<DWCodesModelData> defaultCodes, final HashMap<String, String> selectedCodes, final Map<String, String> filterCodes, final Boolean isMultiselection)  {	
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));			
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		qvo.setDomains(domainCodes);
		if ( !filterCodes.isEmpty() ) {
			// filter codes
			addToFilters(qvo, filterCodes, filterType, null);
		}
			
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					store.removeAll();
					selectedCodes.clear();
					
					/** TODO: language... **/
					DWCodesModelData multiselection =null;
					if ( isMultiselection ) { 
						multiselection = new DWCodesModelData("MULTI", FAOSTATLanguage.print().multiselection());			
						store.add(multiselection);
					}

					for(DWCodesModelData code : vo.getCodes()) {
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
						for(DWCodesModelData code : defaultCodes ) {
							selectedCodes.put(code.getCode(), map.get(code.getCode()));
						}
					}
//					combo.setValue(store.getAt(0));
					combo.enableEvents(true);
//					tokensFiltersReached++;
				}
				
				public void onFailure(Throwable arg0) {

				}
			});
		} catch (FenixGWTException e) {
				
			e.printStackTrace();
		}

	}
	
	
	
	public static void loadCodingSystem(final ComboBox<DWCodesModelData> combo, final ListStore<DWCodesModelData> store, final String filterType, final DWCodesModelData code, final Boolean isMultiselection)  {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
			
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(code.getCode(), code.getLabel());
		qvo.setDomains(domain);	
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
					store.removeAll();

					/** TODO: language... **/
					DWCodesModelData multiselection =null;
					if ( isMultiselection ) { 
						multiselection = new DWCodesModelData("MULTI", FAOSTATLanguage.print().multiselection());			
						store.add(multiselection);
					}
					for(DWCodesModelData code : vo.getCodes()) {
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
	
	public static void buildTitle(FAOSTATVisualizeTitlePanel titlePanel, String title) {
		titlePanel.build(title);
		titlePanel.getPanel().layout();
	}
	
	public static void buildParameters(FAOSTATVisualizeParametersPanel parameters, List<org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter> filters) {
		parameters.build(filters);
		parameters.getPanel().layout();
	}
	
//	public static void checkIfAllTheAsyncCallsAreReturned(final FAOSTATVisualizeParametersPanel parameters, final List<org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter> filters) {
//		timer = new Timer() {
//			public void run() {
//				System.out.println("tokensAsync: " + tokensFiltersAsync);
//				System.out.println("tokensReached: " + tokensFiltersReached);
//				if ( tokensFiltersAsync != 0 ) {
//					if ( tokensFiltersAsync == tokensFiltersReached ) {
//						System.out.println("ok: ");
//						buildParameters(parameters, filters);
//						timer.cancel();
//					}
//				}
//				// TODO: CHECK if it works
//				if ( tokensFiltersAsync == 0 && tokensFiltersReached == 0) {
//					System.out.println(" tokensFiltersAsync == 0 && tokensFiltersReached == 0");
//				}
//			}
//		};
//		timer.scheduleRepeating(1000);
//	}
//	
//	public static void destroy() {
//		if (timer != null)
//		timer.cancel();
//	}
	
	
	
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
	public static void addToFilters(DWFAOSTATQueryVO qvo, Map<String, String> values, String filterType, String domain) {
		System.out.println("TYPE: filterType " + values);
		
		// this is a check if the filter is applicable to the QVO
		if (checkIfAddFilter(qvo, filterType, values)){
			
			// this is to run an asynCall and get the new data based on the new filter
			qvo.setRunAsyncCall(true);
			
			// TODO: should be a list of domains(List<DWCOdes>
			// WORKAROUND
			if ( domain != null && !domain.isEmpty() ) {
//				System.out.println("addToFilters DOMAIN: |" + domain + "|");
				Map<String, String> v = new HashMap<String, String>();
				v.put(domain, domain);
				addValuesFilter(qvo.getDomains(), v);
			}
			
			FAOSTATDimensionConstant c = FAOSTATDimensionConstant.valueOf(filterType);
			switch(c) {
				/** TODO: switch the different cases **/
				case GROUPS:  addValuesFilter(qvo.getDomains(), values); break;
			    case DOMAINS: addValuesFilter(qvo.getDomains(), values); break;
			    case DOMAINS_FOR_GROUP: addValuesFilter(qvo.getDomains(), values); 
//			    						System.out.println("DOMAIN: " + qvo.getDomains());
			    						break;
			    case COUNTRIES: {
			    					/**TODO: add it also to AREAS_WOLRD **/
			    					if ( !qvo.getIsTradeMatrix())
			    						addValuesFilter(qvo.getAreas(), values);
			    					else 
			    						addValuesFilter(qvo.getReportedAreas(), values);
			    				}
			    break;
			    case COUNTRIES_AREAS: {
			    	
									/**TODO: add it also to AREAS_WOLRD **/
									if ( !qvo.getIsTradeMatrix())
										addValuesFilter(qvo.getAreas(), values);
									else 
										addValuesFilter(qvo.getReportedAreas(), values);
								}
				break;
		 
				/** TODO: To be changed **/
				case AREAS_WORLD: addValuesFilter(qvo.getAreas(), values); break;
				case AREAS_FAO:  addValuesFilter(qvo.getAreas(), values); break;
	
				
				case ITEMS: addValuesFilter(qvo.getItems(), values); break;
				case ELEMENTS: addValuesFilter(qvo.getElements(), values); break;
				case ELEMENTS_LIST: addValuesFilter(qvo.getElementsList(), values); break;
				case ELEMENTS_FOR_ITEM: addValuesFilter(qvo.getElements(), values); break;
		
				case YEARS: 
							   qvo.setRunMaxDateQuery(false);
							   qvo.setMaxDateLimit(null);
							   addValuesFilter(qvo.getYears(), values); break;
							   
				/** TODO: Implement the between **/
				case TIMERANGE: {
							qvo.setRunMaxDateQuery(false); 
							qvo.setMaxDateLimit(null);
							addValuesFilter(qvo.getYears(), values); 
							break;
				}
				
				case AGGREGATION_TYPE:  addAggregationValue(qvo, values); break;
							
				case SORTING:  addSortingValue(qvo, values, "DESC"); break;
			}
		}
		else{
			// this is to run an asynCall and get the new data based on the new filter
			qvo.setRunAsyncCall(false);
//			System.out.println("FILTER: " + filterType + " not added");
		}
	}

	
		
	private static Boolean checkIfAddFilter(DWFAOSTATQueryVO qvo, String filterType, Map<String, String> values) {		
		Boolean check = true;
		
		FAOSTATDimensionConstant c = FAOSTATDimensionConstant.valueOf(filterType);

//		System.out.println("qvo.getIsFixedAvoidingYears(): " + qvo.getApplyOnlyYearFilter());
		// this is for the years if the current filter is YEARS or TIMERANGE it's applicable otherwise no
//		System.out.println("true FAOSTATDimensionConstant: " + c.toString());

		// this means that any filter can be applied
		if ( qvo.getNotApplyFilters() ) {
			return false;
		}
		
		if ( qvo.getApplyOnlyYearFilter() && (c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.YEARS.toString()) || c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.TIMERANGE.toString()))) {
//			System.out.println("true FAOSTATDimensionConstant: " + c.toString());
			return true;
		}
		else if ( qvo.getApplyOnlyYearFilter() && (!c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.YEARS.toString()) || !c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.TIMERANGE.toString()))) {
//			System.out.println("false FAOSTATDimensionConstant: " + c.toString());
			return false;
		}
		// areas
		else if ( qvo.getApplyOnlyAreasFilter()){
			if ( c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.COUNTRIES.toString()) || c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.COUNTRIES_AREAS.toString()) ||c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.AREAS_FAO.toString())  || c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.AREAS_WORLD.toString()) || c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.AREAS_ALL.toString()))
				return true;
			else
				return false;
		}
		// items
		else if ( qvo.getApplyOnlyItemsFilter()){
			if ( c.toString().equalsIgnoreCase(FAOSTATDimensionConstant.ITEMS.toString()))
				return true;
			else
				return false;
		}
		
		
		switch(c) {
			/** TODO: switch the different cases **/
			case GROUPS: break;
			case DOMAINS: break;
			case DOMAINS_FOR_GROUP: break;
			case COUNTRIES:  
				
							// setting also the possible zoom to the country 
							//(the zoom is possible also if the filter is not applied)
							qvo.getZoomto().clear();
							qvo.getZoomto().putAll(values);
				
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  { 
								check = false;
							}
							break;
			case COUNTRIES_AREAS: 
							// setting also the possible zoom to the country 
							//(the zoom is possible also if the filter is not applied)
							qvo.getZoomto().clear();
							qvo.getZoomto().putAll(values);
							
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								// adding the filter
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  { 
								check = false;
							}
							break;
			
			case AREAS_WORLD: 
				
							// setting also the possible zoom to the country 
							//(the zoom is possible also if the filter is not applied)
							qvo.getZoomto().clear();
							qvo.getZoomto().putAll(values);
							
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  { 
								check = false;
							}
							break;
			case AREAS_FAO: 
							// setting also the possible zoom to the country 
							//(the zoom is possible also if the filter is not applied)
							qvo.getZoomto().clear();
							qvo.getZoomto().putAll(values);
				
							if ( !qvo.getApplyAllFiltersExceptAreas() ) {
								check = true;
							}
							else if ( qvo.getApplyAllFiltersExceptAreas() )  { 
								check = false;
							}
							break;
			case ITEMS: 
							if ( qvo.getApplyAllFiltersExceptItems() ) {
								check = false;
							}
								
							break;
							
			case ELEMENTS:  break;
			case ELEMENTS_FOR_ITEM: break;
			case YEARS: 
//						System.out.println("APPLY_ALL_FILTERS_EXCEPT_YEARS: " + qvo.getApplyAllFiltersExceptYears());
						// this is to avoid to add the filter
						if ( !qvo.getApplyAllFiltersExceptYears() ) {
							check = true;
						}
						else if ( qvo.getApplyAllFiltersExceptYears() )  { 
							check = false;
						}
				break;
				
			/** TODO: Implement the between **/
			case TIMERANGE: {
//					System.out.println("APPLY_ALL_FILTERS_EXCEPT_YEARS: " + qvo.getApplyAllFiltersExceptYears());
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
//					System.out.println("APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE: " + qvo.getApplyAllFiltersExceptAggregrationType());
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
	public static void addAggregationValue(DWFAOSTATQueryVO qvo, Map<String, String> values) {	
		for( String key : values.keySet()) {
			qvo.setAggregationType(key);
		}
	}
	
	// TODO: USING AN LINKEDHASHMAP, giving the column to order by, and the type of sorting..
	public static void addSortingValue(DWFAOSTATQueryVO qvo, Map<String, String> values, String sortingOrder) {
		List<String> orderBys = new ArrayList<String>();
		for( String key : values.keySet()) {
			orderBys.add(key);
		}
		qvo.setOrderBys(orderBys);
	}

	
	public static void addValuesFilter(Map<String, String> filter, Map<String, String> values) {	
		filter.clear();
		filter.putAll(values);
	}

}
