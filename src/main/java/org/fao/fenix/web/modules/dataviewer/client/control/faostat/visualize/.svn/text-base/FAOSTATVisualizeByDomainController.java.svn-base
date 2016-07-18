package org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATChartController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATMapController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATPivotTableController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATTableController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATMapDisclaimerPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeDomainPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeAggregatedAreasFilter;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeAggregationType;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionFilter;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionItem;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionSelectedItem;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeTimerangeFilter;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstantsFilters;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.rpc.AsyncCallback;




public class FAOSTATVisualizeByDomainController extends FAOSTATVisualizeController {

	
	public static void getVOs(final FAOSTATVisualizeByDomain visualization, final FAOSTATVisualizeSettingsVO settings, final DWCodesModelData code) {	
		try{
	    	 DataViewerServiceEntry.getInstance().getFAOSTATQueryVOs(FAOSTATCurrentView.VISUALIZE_BY_DOMAIN, code.getCode(), FAOSTATConstants.faostatLanguage, new AsyncCallback<FAOSTATVisualizeSettingsVO>() {
					public void onSuccess(FAOSTATVisualizeSettingsVO vo) {
						List<DWFAOSTATQueryVO> qvos = vo.getQvos().get("DEFAULT");
						
						List<FAOSTATVisualizeFilter> filters = vo.getFilters();

						/** TODO: think about setting the default parameters on server side? **/
						/** TODO: should be added also the joinqueries...?**/
						for(DWFAOSTATQueryVO qvo : qvos) {
							FAOSTATConstants.setFAOSTATDBSettings(qvo);
						}
						
						settings.setFilters(filters);
						settings.setQvos(vo.getQvos());
					
						// parameters
						visualization.getParametersPanel().build(true);
						visualization.getParametersPanel().getPanel().layout();
						
						// filters
						buildFilters(visualization, filters, code);

						// views
						// GOOGLE ANALYTICS
						String googleAction = "Visualize the domain";
						buildViews(visualization.getCenterPanel(), qvos, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_WIDTH, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, visualization.getGoogleCategory(), visualization.getGoogleLabel(), googleAction);

						// checks the asyn calss
//						checkIfAllTheAsyncCallsAreReturned(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());
					}
					public void onFailure(Throwable arg0) {
					}
				});
	    	 }
	    	 catch (Exception e) {
	 			e.printStackTrace();
	 		}
	}
		
	public static void buildFilters(FAOSTATVisualize visualization, List<FAOSTATVisualizeFilter> filters, DWCodesModelData code) {
		VerticalPanel p = new VerticalPanel();
		
		// inizialize token (this is for the async calls)
//		tokensFiltersAsync = 0;
//		tokensFiltersReached = 0;
		
		/** TODO: change class name **/
		List<org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter> f = new ArrayList<org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter>();
		visualization.getFiltersPanel().setFilters(f);
		
		if ( !filters.isEmpty()) {
			FieldSet fieldSet = new FieldSet();
			fieldSet.setCollapsible(true);
			fieldSet.setHeading(FAOSTATLanguage.print().choose());
			fieldSet.addStyleName("visualize_fieldset");
			HorizontalPanel h = new HorizontalPanel();	
			for (FAOSTATVisualizeFilter filter : filters) {
				h.add(buildFilter(visualization, filter, code));
				h.add(DataViewerClientUtils.addHSpace(10));
	//			h.add(buildFilter(visualization, filter.getFilterType(), filter.getFilterType(), filter.getIsMultiSelection(), code, filter.getDefaultCodes()));
			}
			
			p.add(DataViewerClientUtils.addVSpace(10));
			fieldSet.add(h);
			p.add(fieldSet);
		}
		
		visualization.getFiltersPanel().getPanel().removeAll();
		visualization.getFiltersPanel().getPanel().add(p);

		visualization.getFiltersPanel().getPanel().layout();
	}
	
	public static HorizontalPanel buildFilter(FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, DWCodesModelData code) {
		FAOSTATDimensionConstant c = FAOSTATDimensionConstant.valueOf(filter.getFilterType());
		
		switch(c) {
			/** TODO: switch the different cases **/
			case TIMERANGE: return buildFromYearToYearFilter(visualization, filter, filter.getFilterType(), code);
				
			case AGGREGATION_TYPE:  return buildAggregationTypeFilter(visualization, filter, filter.getFilterType(), code);
			
			case AREAS_WORLD: return buildAggregatedAreasFilter(visualization, filter, filter.getFilterType(), code, filter.getDisaggregated());
			case AREAS_FAO: return buildAggregatedAreasFilter(visualization, filter, filter.getFilterType(), code, filter.getDisaggregated());
	
			default: return buildStandardFilter(visualization, filter, filter.getFilterType(), code);
		}
	}

	public static HorizontalPanel buildAggregatedAreasFilter(FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, String title, DWCodesModelData code, Boolean disaggregate) {
//		tokensFiltersAsync++;
		FAOSTATVisualizeAggregatedAreasFilter filterPanel = new FAOSTATVisualizeAggregatedAreasFilter();
		
		visualization.getFiltersPanel().getFilters().add(filterPanel);
		
		return filterPanel.buildAggregatedAreasFilter(visualization, filter, title, filter.getFilterType(), filter.getIsMultiSelection(), code, filter.getDefaultCodes(), disaggregate);
	}
	
	
	public static HorizontalPanel buildStandardFilter(FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, String title, DWCodesModelData code) {
		/** TODO: Change class name...**/

//		if ( filter.getUseCodingSystem() ) {
//			tokensFiltersAsync++;
//		}
		org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter filterPanel = new org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter();
		
		visualization.getFiltersPanel().getFilters().add(filterPanel);
		
		return filterPanel.buildStandardFilter(visualization, filter, code, title);
	}
	
	


	
	public static HorizontalPanel buildAggregationTypeFilter(FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, String title, DWCodesModelData code) {
		FAOSTATVisualizeAggregationType filterPanel = new FAOSTATVisualizeAggregationType();
		
		visualization.getFiltersPanel().getFilters().add(filterPanel);
		
		return filterPanel.build(visualization, filter, title, filter.getFilterType(), filter.getIsMultiSelection(), code, filter.getDefaultCodes());
	}
	
	/** TODO: set the from year to year values **/

	public static HorizontalPanel buildFromYearToYearFilter(FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, String title, DWCodesModelData code) {		
		// TODO: if is not codying system... check
//		if ( !filter.getCodes().isEmpty() )
//			tokensFiltersAsync = tokensFiltersAsync + 2;
		
		FAOSTATVisualizeTimerangeFilter filterPanel = new FAOSTATVisualizeTimerangeFilter();
		
		visualization.getFiltersPanel().getFilters().add(filterPanel);
		
		return filterPanel.build(visualization, filter, title, filter.getFilterType(), filter.getIsMultiSelection(), code, filter.getDefaultCodes(), filter.getCodes());
	}

	public static void loadCodingSystem(final FAOSTATVisualizeMultiselectionFilter filterPanel, final String filterType, DWCodesModelData code, final HashMap<String, String> selectedCodes)  {
		
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
			
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(code.getCode(), code.getLabel());
		qvo.setDomains(domain);	
//		System.out.println("FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType): " + FAOSTATConstantsFilters.dimensionCodingSystem.get(filterType));
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					// cleaning
					filterPanel.getMultiSelectionItems().clear();
					filterPanel.getSelectionPanel().removeAll();
					filterPanel.getSelectionPanelContent().removeAll();
					
					/** TODO: build in a filter class...**/
					Html title = new Html("<div class='visualize_multiselection_title'>"+FAOSTATLanguage.print().listOfItems()+":</div>");
					filterPanel.getSelectionPanel().add(DataViewerClientUtils.addVSpace(5));
					filterPanel.getSelectionPanel().add(title);
					filterPanel.getSelectionPanel().add(DataViewerClientUtils.addVSpace(5));
					filterPanel.getSelectionPanel().add(filterPanel.getSelectionPanelContent());

					for(DWCodesModelData code : vo.getCodes()) {
						FAOSTATVisualizeMultiselectionItem item = new FAOSTATVisualizeMultiselectionItem(filterPanel);
//						System.out.println("item: " + code.getCode() + " | " + code.getLabel() + " | " + selectedCodes.containsKey(code.getCode()));
						filterPanel.getSelectionPanelContent().add(item.build(code, selectedCodes.containsKey(code.getCode())));
						filterPanel.getSelectionPanel().layout();
						filterPanel.getMultiSelectionItems().add(item);
					}
					
					filterPanel.getSelectionPanelContent().layout();
					filterPanel.getSelectionPanel().layout();			
					buildListCurrentSelectedCodes(filterPanel);
					filterPanel.getSelectionPanel().layout();
				}
				public void onFailure(Throwable arg0) {
				}
			});
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}
	}

	
	/*** multiselection icon deselectItem **/
	public static SelectionListener<IconButtonEvent> deselectItem(final FAOSTATVisualizeMultiselectionFilter filterPanel, final FAOSTATVisualizeMultiselectionSelectedItem removedItem) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				deselectItemAgent(filterPanel, removedItem);
			}
		};
	}
	
	public static void deselectItemAgent(FAOSTATVisualizeMultiselectionFilter filterPanel,  FAOSTATVisualizeMultiselectionSelectedItem removedItem) {
		// TODO: list of items, checkbox
		List<FAOSTATVisualizeMultiselectionItem> items = filterPanel.getMultiSelectionItems();
		for(FAOSTATVisualizeMultiselectionItem item : items) {
			if ( item.getCode().getCode().equals(removedItem.getCode().getCode())) {		
				item.getPanel().setStyleName("visualize_multiselection_item");
				item.getCheckbox().setValue(false);
				break;
			}
		}
		/** TODO: change it... **/
		buildListCurrentSelectedCodes(filterPanel);
	}

	/** TODO: remove the combo parameter, and selected codes etc...pass filter **/
	public static SelectionChangedListener<DWCodesModelData> updateFilter(final FAOSTATVisualize visualization, final org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter filter, final ComboBox<DWCodesModelData> combo, final String filterType, final DWCodesModelData domaincode, final HashMap<String, String> selectedCodes) {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				updateFilterAgent(visualization, filter, combo, filterType,domaincode, selectedCodes);
			}
		};
	}
	
	public static SelectionChangedListener<DWCodesModelData> updateTimeRangeFilter(final FAOSTATVisualize visualization, final ComboBox<DWCodesModelData> fromCombo, final ComboBox<DWCodesModelData> toCombo, final String filterType,  final HashMap<String, String> selectedCodes) {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				updateTimeRangeFilterAgent(visualization, fromCombo, toCombo, filterType, selectedCodes);
			}
		};
	}
	
	/** TODO: remove combo, filterType parameters....passing filter **/
	public static SelectionChangedListener<DWCodesModelData> updateAggregatedAreasFilter(final FAOSTATVisualize visualization, final FAOSTATVisualizeAggregatedAreasFilter filter, final ComboBox<DWCodesModelData> combo, final String filterType, final DWCodesModelData code, final HashMap<String, String> selectedCodes, final Boolean disaggregate) {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				updateAggregatedAreasFilterAgent(visualization, filter, combo, filterType, code, selectedCodes, disaggregate);
			}
		};
	}
	
	public static void updateTimeRangeFilterAgent(FAOSTATVisualize visualization,  ComboBox<DWCodesModelData> fromCombo, ComboBox<DWCodesModelData> toCombo, String filterType, HashMap<String, String> selectedCodes) {
		/** TODO: Date or Integer...they are just years ***/
		Integer fromDate = Integer.valueOf(fromCombo.getSelection().get(0).getCode());
		Integer toDate = Integer.valueOf(toCombo.getSelection().get(0).getCode());

		Boolean updateView = true;

		/** TODO: implement between **/
		// error message
		if (fromDate.compareTo(toDate) > 0) {
			updateView = false;
			FenixAlert.error(FAOSTATLanguage.print().selectDate(), FAOSTATLanguage.print().dateSelectionWrong());
		} else {
			selectedCodes.clear();

			if (fromDate.compareTo(toDate) == 0) {
				// System.out.println("fromDate.compareTo(toDate) == 0");
				selectedCodes.put(fromDate.toString(), fromDate.toString());
			} else if (fromDate.compareTo(toDate) < 0) {
				// System.out.println("fromDate.compareTo(toDate)  < 0 ");
				while (fromDate.compareTo(toDate) <= 0) {
					// System.out.println("dates: " + fromDate + " | " + // toDate);
					selectedCodes.put(fromDate.toString(), fromDate.toString());
					fromDate++;
				}
			}
		}
		
		if ( updateView ) {
			HashMap<String, List<DWFAOSTATQueryVO>> hmVOs = visualization.getSettings().getQvos();
			
			List<DWFAOSTATQueryVO> qvos = hmVOs.get("DEFAULT");
			updateViews(qvos, visualization, filterType, null, selectedCodes);
		}
	}
	

	public static void updateFilterAgent(FAOSTATVisualize visualization, org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter filter, ComboBox<DWCodesModelData> combo, String filterType, DWCodesModelData domaincode, HashMap<String, String> selectedCodes) {
		
		// check if the selected code is multiselection
//		System.out.println("combo.getSelection().get(0).getCode(): " + combo.getSelection().get(0).getCode());
//		System.out.println("checkIfMultiselection: " + checkIfMultiselection(combo.getSelection().get(0).getCode()));

		if ( checkIfMultiselection(combo.getSelection().get(0).getCode())) {
			
			// TODO: call the multiselection 
			FAOSTATVisualizeByDomainController.multiselectionFilterAgent(visualization, filter, filterType +"_MULTI", domaincode);
		}
		else {	
			// build the view
			String code = combo.getSelection().get(0).getCode();
			String label = combo.getSelection().get(0).getLabel();
			String domain = combo.getSelection().get(0).getDomain();
			
			selectedCodes.clear();
			selectedCodes.put(code, label);

		    HashMap<String, List<DWFAOSTATQueryVO>> hmVOs = visualization.getSettings().getQvos();
			List<DWFAOSTATQueryVO> qvos = hmVOs.get("DEFAULT");
			
			updateViews(qvos, visualization, filterType, domain, selectedCodes);
		}
	}
	
	/** TODO: should be passes aslo the various widts....**/
	private static void updateViews(List<DWFAOSTATQueryVO> qvos, FAOSTATVisualize visualization, String filterType, String domain, HashMap<String, String> selectedCodes) {
//		System.out.println("DOMAIN: " + domain);
		
		for (DWFAOSTATQueryVO qvo : qvos ) {
			if ( qvo.getJoinQueryVO() != null ) {
//				System.out.println("qvo.getJoinQueryVO() != null");
//				addToFilters(qvo, selectedCodes, filterType, domain);
				for(DWFAOSTATQueryVO q : qvo.getJoinQueryVO().getQvos()) {
					addToFilters(q, selectedCodes, filterType, domain);
				}

			}
//			else {
//				System.out.println("qvo.getJoinQueryVO() == null");
				addToFilters(qvo, selectedCodes, filterType, domain);
//			}
		}
		// parameters
		buildParameters(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());
		
		String googleCategory = visualization.getGoogleCategory();
		String googleLabel = visualization.getGoogleLabel();
		String googleAction = "Update " + googleCategory; // + googleLabel;
		buildViews(visualization.getCenterPanel(), qvos, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_WIDTH, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, googleCategory, googleLabel, googleAction);
	}
	
	/**
	 * This method retrieve the countries of the selected area codes, and add to the area filter
	 * 
	 * @param filter
	 * @param values
	 */
	public static void updateAggregatedAreasFilterAgent(final FAOSTATVisualize visualization, FAOSTATVisualizeAggregatedAreasFilter filter, ComboBox<DWCodesModelData> combo, final String filterType, final DWCodesModelData code, final HashMap<String, String> selectedCodes, Boolean disaggregate) {
		/** TODO: Call to retrieve the countries related to the AREA 
		 * 		  and add the codes to the filter **/
		
		
		// check if is enabled the zoom to the map (to keep track of the selected area)
		// adding the zoomto (by default), in theory should be checked if enable it or not 
	    HashMap<String, List<DWFAOSTATQueryVO>> hmVOs = visualization.getSettings().getQvos();
		List<DWFAOSTATQueryVO> qvos = hmVOs.get("DEFAULT");
		for(DWFAOSTATQueryVO qvo : qvos) {
			qvo.getZoomto().clear();
			qvo.getZoomto().put(combo.getSelection().get(0).getCode(), combo.getSelection().get(0).getLabel());
		}

		if ( checkIfMultiselection(combo.getSelection().get(0).getCode())) {
			
			// TODO: keep track of the old value, so if the multiselection 
			// window is closed the coherence is maintained
			
			FAOSTATVisualizeByDomainController.multiselectionAggregatedAreasAgent(visualization, filter, filterType + "_MULTI", code);
		}
		else if ( !disaggregate ) {
//		    HashMap<String, List<DWFAOSTATQueryVO>> hmVOs = visualization.getSettings().getQvos();
//			List<DWFAOSTATQueryVO> qvos = hmVOs.get("DEFAULT");
			
			selectedCodes.clear();
			selectedCodes.put(combo.getSelection().get(0).getCode(), combo.getSelection().get(0).getLabel());

//			System.out.println("SELECTED CODES: " + selectedCodes);

			updateViews(qvos, visualization, filterType, null, selectedCodes);
		}
		else {
			// build the views
			try {
				
				DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
				qvo.setOutput(DataViewerBoxContent.GET.toString());
				qvo.setTypeOfOutput(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());		
				FAOSTATConstants.setFAOSTATDBSettings(qvo);

				final Map<String, String> aggregationfilter = new HashMap<String, String>();
				aggregationfilter.put(combo.getSelection().get(0).getCode(), combo.getSelection().get(0).getLabel());
				qvo.getAggregationsFilter().putAll(aggregationfilter);
				qvo.getDomains().put(code.getCode(), code.getLabel());
				
				DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					@SuppressWarnings("unchecked")
					public void onSuccess(DWFAOSTATResultVO vo) {
						selectedCodes.clear();
						for(DWCodesModelData code : vo.getCodes()) {
							selectedCodes.put(code.getCode(), code.getLabel());
						}
						
					    HashMap<String, List<DWFAOSTATQueryVO>> hmVOs = visualization.getSettings().getQvos();
						List<DWFAOSTATQueryVO> qvos = hmVOs.get("DEFAULT");

						updateViews(qvos, visualization, filterType, null, selectedCodes);
					}
					public void onFailure(Throwable arg0) {
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static SelectionListener<ButtonEvent> closeWindow(final Window window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.close();
			}
		};
	}
	
	/** MULTISELECTION SELECT/DESELECT ALL **/
	public static SelectionListener<ButtonEvent> selectAllMultiselection(final FAOSTATVisualizeMultiselectionFilter filterPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				selectAllMultiselectionAgent(filterPanel);
			}
		};
	}
	
	private static void selectAllMultiselectionAgent(FAOSTATVisualizeMultiselectionFilter filterPanel) {
		for(FAOSTATVisualizeMultiselectionItem item : filterPanel.getMultiSelectionItems()) {
			item.getCheckbox().setValue(true);
			item.getPanel().setStyleName("visualize_multiselection_item_selected");
		}
		
		buildListCurrentSelectedCodes(filterPanel);
	}
	
	public static SelectionListener<ButtonEvent> deselectAllMultiselection(final FAOSTATVisualizeMultiselectionFilter filterPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				deselectAllMultiselectionAgent(filterPanel);
			}
		};
	}
	
	private static void deselectAllMultiselectionAgent(FAOSTATVisualizeMultiselectionFilter filterPanel) {
		for(FAOSTATVisualizeMultiselectionItem item : filterPanel.multiSelectionItems) {
			item.getCheckbox().setValue(false);
			item.getPanel().setStyleName("visualize_multiselection_item");
		}
		
		buildListCurrentSelectedCodes(filterPanel);

	}
	/*****/
	
	public static SelectionListener<ButtonEvent> updateMultiselectionFilter(final Window window, final FAOSTATVisualize visualization, final org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter filter, final List<FAOSTATVisualizeMultiselectionItem> multiSelectionItems, final String filterType, final DWCodesModelData code, final HashMap<String, String> selectedCodes) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				updateMultiselectionFilterAgent(window, visualization, filter, multiSelectionItems, filterType, code, selectedCodes);
			}
		};
	}

	private static void updateMultiselectionFilterAgent(Window window, final FAOSTATVisualize visualization, org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter filter, List<FAOSTATVisualizeMultiselectionItem> multiSelectionItems, final String filterType, final DWCodesModelData code, final HashMap<String, String> selectedCodes) {
//		System.out.println("updateFilterAgent");
		selectedCodes.clear();
		
		for(FAOSTATVisualizeMultiselectionItem item : multiSelectionItems) {
			if ( item.getCheckbox().getValue() ) {
				selectedCodes.put(item.getCode().getCode(), item.getCode().getLabel());
			}
		}
	
		if( selectedCodes.isEmpty() ) {
			FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().noCodesHaveBeenSelected());
		}
		else{
			window.close();
//			System.out.println("selectedCodes: " + selectedCodes);
			
		    HashMap<String, List<DWFAOSTATQueryVO>> hmVOs = visualization.getSettings().getQvos();
			List<DWFAOSTATQueryVO> qvos = hmVOs.get("DEFAULT");
			
			updateViews(qvos, visualization, filterType, null, selectedCodes);
		}
		
		
		// set the combo box
		if ( selectedCodes.size() > 1 ) {
			// set multiselection combo box
			List<DWCodesModelData> store = filter.getStore().getModels();
			for(DWCodesModelData value : store) {
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
			List<DWCodesModelData> store = filter.getStore().getModels();
			for(String key : selectedCodes.keySet() ) {
				Boolean added = false;
				for(DWCodesModelData value : store) {
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
					for(DWCodesModelData value : store) {
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

	public static SelectionListener<IconButtonEvent> multiselectionAggregatedAreas(final FAOSTATVisualize visualization, final FAOSTATVisualizeAggregatedAreasFilter filter, final String filterTypeMultisel, final DWCodesModelData code) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				multiselectionAggregatedAreasAgent(visualization, filter, filterTypeMultisel, code);
			}
		};
	}
	
	private static void multiselectionAggregatedAreasAgent(final FAOSTATVisualize visualization, final FAOSTATVisualizeAggregatedAreasFilter filter, final String filterTypeMultisel, final DWCodesModelData code) {
		filter.getMultiselection().build(visualization, filter, filterTypeMultisel, code);
	}
	
	public static SelectionListener<IconButtonEvent> multiselectionFilter(final FAOSTATVisualize visualization, final org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter filter, final String filterTypeMultisel, final DWCodesModelData code) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				multiselectionFilterAgent(visualization, filter, filterTypeMultisel, code);
			}
		};
	}
	
	private static void multiselectionFilterAgent(final FAOSTATVisualize visualization, final org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeFilter filter, final String filterTypeMultisel, final DWCodesModelData code) {
		filter.getMultiselection().build(visualization, filter, filterTypeMultisel, code);
	}

	public static void buildListCurrentSelectedCodes(FAOSTATVisualizeMultiselectionFilter filterPanel) {
		
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		
		// title
		Html title = new Html("<div class='visualize_multiselection_title'>"+FAOSTATLanguage.print().currentSelectedItems()+":</div>");
		panel.add(title);
		panel.add(DataViewerClientUtils.addHSpace(5));
		
		// get the list
		List<FAOSTATVisualizeMultiselectionItem> items = filterPanel.getMultiSelectionItems();
		
		for(FAOSTATVisualizeMultiselectionItem item : items) {
			if (item.getCheckbox().getValue()) {
				FAOSTATVisualizeMultiselectionSelectedItem itemSelected = new FAOSTATVisualizeMultiselectionSelectedItem(filterPanel);
				panel.add(itemSelected.build(item.getCode()));
				panel.add(DataViewerClientUtils.addHSpace(2));
				
			}
		}	
		panel.layout();
		
		// build the panel
		filterPanel.getCurrentSelection().removeAll();
		filterPanel.getCurrentSelection().add(panel);
		
		filterPanel.getCurrentSelection().layout();
	} 
	
	
	public static void buildViews(ContentPanel panel, List<DWFAOSTATQueryVO> qvos, Integer centerPanelWidth, Integer centerPanelHorizontalSpacing, Integer centerPanelVerticalSpacing, String googleCategory, String googleLabel, String googleAction) {
		
		// call to GOOGLE Analytics
//		System.out.println("----------------------------> Google Analytics: " + googleCategory + " - " + googleLabel  + " - " + googleAction);
		GoogleAnalyticsController.trackEvent(googleCategory, googleAction, googleLabel);
		
		panel.removeAll();
		VerticalPanel v = new VerticalPanel();
		
		v.setHorizontalAlign(HorizontalAlignment.LEFT);
		
		v.setScrollMode(Scroll.AUTOX);
		
		Integer currentSize = 0;
		
		HorizontalPanel widgetsPanel = new HorizontalPanel();
		
		Boolean addHSpacing = false;
	
		for(int i=0; i < qvos.size(); i++) {
			
			DWFAOSTATQueryVO qvo = qvos.get(i);	

			DataViewerBoxContent content = DataViewerBoxContent.valueOf(qvo.getOutput());
			DataViewerBoxContent typeOfOutput = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
			

			// Object Size (Width and Height)
			String width = getWidth(content, qvo);
			String height = getHeight(content, qvo);

			// Calculate width
			currentSize = currentSize + Integer.valueOf(width);
			
//			System.out.println(i + ") content: " + content.name() + " | w:" + width + " | h:" + height + " (currentsize: " + currentSize + ")" + "  addHSpacing: " + addHSpacing);

			if ( currentSize < ((centerPanelWidth) - centerPanelHorizontalSpacing) ) {
				if ( addHSpacing ) {
					widgetsPanel.add(DataViewerClientUtils.addHSpace(centerPanelHorizontalSpacing));
				}
				addHSpacing = true;
			}
			else {
				v.add(widgetsPanel);
				v.add(DataViewerClientUtils.addVSpace(centerPanelVerticalSpacing));
				widgetsPanel = new HorizontalPanel();
				currentSize = Integer.valueOf(width);
				addHSpacing = true;
			}
			
			switch (content) {
				case MAP:
					widgetsPanel.add(addMap(qvo, width, height, typeOfOutput, qvo.isUseCustomColors()));
					break;
				case TABLE:
					widgetsPanel.add(addTable(qvo, width, height));
					break;
				case PIVOT_TABLE:
					widgetsPanel.add(addPivotTable(qvo, width, height));
					break;
					
				case CHART:
					widgetsPanel.add(addChart(qvo, width, height));
					break;
			}
		}
		
		v.add(widgetsPanel);

		System.out.println("[FAOSTATVisualizeByDomain] - Adding to panel");
		panel.add(v);
     	panel.layout();
	}
	
	private static String getWidth(DataViewerBoxContent content, DWFAOSTATQueryVO qvo) {
		
		if ( qvo.getWidth() != null )
			return qvo.getWidth();
		
		switch (content) {
			case MAP:
				return FAOSTATVisualizeByDomainConstants.MAP_WIDTH;
			case TABLE:
				return FAOSTATVisualizeByDomainConstants.TABLE_WIDTH;
			case PIVOT_TABLE:
				return FAOSTATVisualizeByDomainConstants.TABLE_WIDTH;
			case CHART:
				return FAOSTATVisualizeByDomainConstants.CHART_WIDTH;
		}
		
		return null;
	}
	
	private static String getHeight(DataViewerBoxContent content, DWFAOSTATQueryVO qvo) {
		if ( qvo.getHeight() != null )
			return qvo.getHeight();
		
		switch (content) {
			case MAP:
				return FAOSTATVisualizeByDomainConstants.MAP_HEIGHT;
			case TABLE:
				return FAOSTATVisualizeByDomainConstants.TABLE_HEIGHT;
			case PIVOT_TABLE:
				return FAOSTATVisualizeByDomainConstants.TABLE_WIDTH;
			case CHART:
				return FAOSTATVisualizeByDomainConstants.CHART_HEIGHT;
		}
		
		return null;
	}
	
	
	private static ContentPanel addMap(DWFAOSTATQueryVO qvo, String width, String height, DataViewerBoxContent typeOfOutput, boolean customColors) {
		ContentPanel mapPanel = new ContentPanel();
		mapPanel.setHeaderVisible(false);
		mapPanel.setBodyBorder(false);
		switch (typeOfOutput) {
			case MAP_GOOGLE: FAOSTATMapController.addGoogleMap(mapPanel, qvo, width, height, customColors); break;
			case MAP: FAOSTATMapController.addMap(mapPanel, qvo, width, height, customColors); break;
		}	
		return mapPanel;
	}
	
	private static ContentPanel addTable(DWFAOSTATQueryVO qvo, String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		FAOSTATTableController.addTable(panel, qvo, width, height);
		return panel;
	}
	
	private static ContentPanel addPivotTable(DWFAOSTATQueryVO qvo, String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		FAOSTATPivotTableController.addPivotTable(panel, qvo, width, height);
		return panel;
	}
	
	/** TODO: to eventually add two charts **/
	private static HorizontalPanel addChart(DWFAOSTATQueryVO qvo, String width, String height) {
		HorizontalPanel panel = new HorizontalPanel();
		
		ContentPanel c1 = new ContentPanel();
		c1.setHeaderVisible(false);
		c1.setBodyBorder(false);
		FAOSTATChartController.addChart(c1, qvo, width, height);
		
		panel.add(c1);

		return panel;
	}
	
	public static void getDomainsAgent(final FAOSTATVisualizeByDomain visualizePanel, final FAOSTATVisualizeDomainPanel domainPanel, final TreeStore<DWCodesModelData> treeStore, final TreePanel<DWCodesModelData> tree, final String defaultDomainCode) {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ALL_GROUPS_DOMAINS.toString());			
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					treeStore.removeAll();			
					for(DWCodesModelData topcode : vo.getCodesHierachy().keySet()) {
						treeStore.add(topcode, true);
						treeStore.add(topcode, vo.getCodesHierachy().get(topcode), true);
					}
					
					// check default code (for now top code...chage with all codes...)
					if ( defaultDomainCode != null) {
//						System.out.println("defaultDomainCode: " + defaultDomainCode);
						for(DWCodesModelData topCode : treeStore.getAllItems()) {
//							System.out.println("topCode: " + topCode.getCode());
							if ( topCode.getCode().equals(defaultDomainCode)) {
//								System.out.println("select tree: " + topCode.getCode());
								tree.getSelectionModel().select(topCode, true);
								tree.setExpanded(topCode, true);
								
								// not hierarchic, just the default code..
								visualizePanel.getTitlePanel().build(topCode.getLabel());
								visualizePanel.getTitlePanel().getPanel().layout();
								
								domainPanel.getVOs(visualizePanel, topCode);
								break;
							}
						}
					}
					
					// remove from the list
					// PA - Price Archive
					// RA - Fertilizers archive
					// RY - Machinery Archive
					// FB - Food balance sheet
					// FT - Forestry trade matrix
					// TM - Trade Matrix
					// QV - Value of Agrocultural Production
					// GT - GHG Agriculture Totals
					// GL - GHG LULUCF Totals
					List<DWCodesModelData> removeCodes = new ArrayList<DWCodesModelData>();
					for(DWCodesModelData code : treeStore.getAllItems()) {
//						System.out.println("CODE: " + code.getCode());
						if ( code.getCode().equals("GL") || code.getCode().equals("GT") ||code.getCode().equals("PA") || code.getCode().equals("RA") || code.getCode().equals("RY") || code.getCode().equals("FT") || code.getCode().equals("TM") || code.getCode().equals("QV")) {
//							System.out.println("remove code: " + code.getCode());
							removeCodes.add(code);
//							treeStore.remove(topCode);
						}
					}
					
					for(DWCodesModelData code : removeCodes){
//						System.out.println("removing: " + code.getCode());
						treeStore.remove(code);
					}
				}
				
				public void onFailure(Throwable arg0) {
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
