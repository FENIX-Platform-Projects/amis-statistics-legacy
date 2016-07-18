package org.fao.fenix.web.modules.amis.client.control;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.amis.client.view.utils.AMISParametersPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISMultiselectionFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISMultiselectionItem;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.TitlePanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISProductionInterfaceConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISConstantsVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;



public class AMISProductionController {




	// TODO: DON'T use it, call directly the coding systems and retrieve
	// the codes when the xml is created (?)
	public static Integer tokensFiltersAsync = 0;

	public static Integer tokensFiltersReached = 0;

	private static Timer timer;

	


  
  
  

 



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

	public static void loadCodingSystem(final ComboBox<AMISCodesModelData> combo, final ListStore<AMISCodesModelData> store, final String filterType, final AMISCodesModelData setSelectedModel, final List<AMISCodesModelData> defaultCodes, final HashMap<String, String> selectedCodes, final Boolean isMultiselection)  {

		System.out.println("AMISProductionController: loadCodingSystem ... START");
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setSelectedDataset(AMISController.getCurrentDatasetView().name());
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.codingSystemMap.get(filterType));

		System.out.println("AMISConstants.codingSystemMap.get(filterType) "+AMISConstants.codingSystemMap.get(filterType));
		
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

					/** TODO: language... **/
					AMISCodesModelData multiselection =null;
					if ( isMultiselection ) {
						multiselection = new AMISCodesModelData("MULTI", "Multiselection");
						store.add(multiselection);
					}

					for(AMISCodesModelData code : vo.getCodes()) {
						store.add(code);
						map.put(code.getCode(), code.getLabel());

						if(setSelectedModel!=null){
								System.out.println("---------> SELECTED PRODUCT: ");
								if (code.getCode().equals(setSelectedModel.getCode())) {
									 combo.setValue(code);
									 selectedCodes.put(code.getCode(), map.get(code.getCode()));
								}
						}
						else {
							if ( defaultCodes != null && !defaultCodes.isEmpty() ) {
								System.out.println("---------> DEFAULT CODES: ");
								if ( defaultCodes.size() > 1 ) {
									// set MULTI (multiselection) as default code
									combo.setValue(multiselection);
								}
								else {
									 if (code.getCode().equals(defaultCodes.get(0).getCode())) {
										 combo.setValue(code);
									}
								}
								
								for(AMISCodesModelData model : defaultCodes ) {
									selectedCodes.put(model.getCode(), map.get(model.getCode()));
								}
							} 
							else {
								System.out.println("---------> DEFAULT PRODUCT: ");
								combo.setValue(store.getAt(0));
								selectedCodes.put(store.getAt(0).getCode(), store.getAt(0).getLabel());
							}
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
				case AMIS_COUNTRIES:  addValuesFilter(qvo.getAreas(), values); break;
				case CCBS_PRODUCTS: addValuesFilter(qvo.getItems(), values); break;
				case CCBS_ELEMENTS: addValuesFilter(qvo.getElements(), values); break;
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


	public static void addValuesFilter(Map<String, String> filter, Map<String, String> values) {
		filter.clear();
		filter.putAll(values);
	}
	
public static void buildViews(ContentPanel panel, List<AMISQueryVO> qvos, String title) {
		
		panel.removeAll();
		VerticalPanel v = new VerticalPanel();
		
		v.setHorizontalAlign(HorizontalAlignment.LEFT);
		
		v.setScrollMode(Scroll.AUTOX);
		
		System.out.println("qvos.size(): " + qvos.size());
		
		Integer currentSize = 0;
		
		HorizontalPanel widgetsPanel = new HorizontalPanel();
		
		Boolean addHSpacing = false;
		for(int i=0; i < qvos.size(); i++) {
			
			AMISQueryVO qvo = qvos.get(i);
			
			v.add(FormattingUtils.addVSpace(10));
			
			AMISConstants content = AMISConstants.valueOf(qvo.getOutput());
			
			// Object Size (Width and Height)
			String width = getWidth(content, qvo);
			String height = getHeight(content, qvo);
			
			
			
			// Calculate width
			currentSize = currentSize + Integer.valueOf(width);
			
			System.out.println(i + ") content: " + content.name() + " | w:" + width + " | h:" + height + " (currentsize: " + currentSize + ")" + "  addHSpacing: " + addHSpacing);

			if ( currentSize < ((AMISProductionInterfaceConstants.CENTER_PANEL_WIDTH) - AMISProductionInterfaceConstants.CENTER_PANEL_HORIZONTAL_SPACING) ) {
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
			}

			

			
			switch (content) {
			case TABLE:
				widgetsPanel.add(AMISController.addTable(qvo, width, height));
				break;
				
			case CHART:
				widgetsPanel.add(AMISController.addChart(qvo, width, height));

				break;

			}
		}
		
		v.add(widgetsPanel);
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



}
