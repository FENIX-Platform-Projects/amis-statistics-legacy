package org.fao.fenix.web.modules.dataviewer.client.control.faostat.download;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATPivotTableController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguageConstants;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare.FAOSTATCompareData;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATBulkDownloadPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATBulkDownloadTreePanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadDomainPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadEntry;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadNotes;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadOptions;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATNotesPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot.FAOSTATDownloadOutputType;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot.FAOSTATDownloadTablePanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATDownloadConstants;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATDownloadController {
	
	public static Integer tokensAsync = 0;
	
	public static Integer tokensReached = 0;
	
	public static Timer timer;
	
	private static LinkedHashMap<String, String> fertilizersElements = new LinkedHashMap<String, String>();
	
	static FAOSTATDownloadEntry downloadView;
	
	static {
		fertilizersElements = new LinkedHashMap<String, String>();

		fertilizersElements.put("2510", "Production Quantity");
		fertilizersElements.put("5515", "Production Quantity in nutrients");
		fertilizersElements.put("2610", "Import Quantity");
		fertilizersElements.put("5615", "Import Quantity in nutrients");
		fertilizersElements.put("2910", "Export Quantity");
		fertilizersElements.put("5915", "Export Quantity in nutrients");
		fertilizersElements.put("5850", "Non-fertilizer use");
		fertilizersElements.put("5849", "Non-fertilizer use in nutrients");
		fertilizersElements.put("5157", "Consumption");
		fertilizersElements.put("5155", "Consumption in nutrients");

	}
	
	private static LinkedHashMap<String, String> fertilizersArchiveElements = new LinkedHashMap<String, String>();
	
	static {
		fertilizersArchiveElements = new LinkedHashMap<String, String>();

		fertilizersArchiveElements.put("2510", "Production Quantity");
		fertilizersArchiveElements.put("2610", "Import Quantity");
		fertilizersArchiveElements.put("2910", "Export Quantity");
		fertilizersArchiveElements.put("5157", "Consumption");
		fertilizersArchiveElements.put("5751", "Prices Paid by Farmers");
	}
	
	
	private static LinkedHashMap<String, String> fertilizersTradeItems = new LinkedHashMap<String, String>();
	
	static {
		fertilizersTradeItems = new LinkedHashMap<String, String>();

		fertilizersTradeItems.put("1360", "Nitrogenous fertilizers");
		fertilizersTradeItems.put("1375", "Phosphate fertilizers");
		fertilizersTradeItems.put("1386", "Potash fertilizers");
		fertilizersTradeItems.put("1397", "Fertilizers Manufactured, nes");
		fertilizersTradeItems.put("1401", "Fertilizers, Organic");
		fertilizersTradeItems.put("1399", "Natural Phosphates");
		fertilizersTradeItems.put("1400", "Natural Potassic Salts");
		fertilizersTradeItems.put("1398", "Natural Sodium Nitrate");
	}
	
	private static LinkedHashMap<String, String> fertilizersTradeElements = new LinkedHashMap<String, String>();
	
	static {
		fertilizersTradeElements = new LinkedHashMap<String, String>();

		fertilizersTradeElements.put("2620", "Import Value");
		fertilizersTradeElements.put("2920", "Export Value");
	}
	
	// This are hadcoded for the Pesticides Trade
	private static LinkedHashMap<String, String> pesticidesTrade = new LinkedHashMap<String, String>();
	
	static {
		pesticidesTrade = new LinkedHashMap<String, String>();

		pesticidesTrade.put("1357", "Pesticides");
		pesticidesTrade.put("1416", "Insecticides");
		pesticidesTrade.put("1417", "Fungicides");
		pesticidesTrade.put("1418", "Herbicides");		
		pesticidesTrade.put("1419", "Disinfectants");
		pesticidesTrade.put("1442", "Hazardous pesticides");
		pesticidesTrade.put("1443", "Insecticides (excl. Haz. pest.)");
		pesticidesTrade.put("1444", "Fungicides (excl. Haz. pest.)");
		pesticidesTrade.put("1445", "Herbicides (excl. Haz. pest.)");
		pesticidesTrade.put("1446", "Disinfectants, etc (excl. Haz. pes)");
	}
	
	// Rotterdam Convention pesticides
	private static LinkedHashMap<String, String> pesticidesTradeRotterdamConvention = new LinkedHashMap<String, String>();
	
	static {
		pesticidesTradeRotterdamConvention = new LinkedHashMap<String, String>();

		pesticidesTradeRotterdamConvention.put("1422", "Mercury compounds etc. excl. amalgams");
		pesticidesTradeRotterdamConvention.put("1423", "Ethylene dichloride");
		pesticidesTradeRotterdamConvention.put("1424", "Ethylene dibromide (1,2-dibromoethane)");
		pesticidesTradeRotterdamConvention.put("1425", "HCH (mixed isomers) / Lindane");
		pesticidesTradeRotterdamConvention.put("1426", "Aldrin, Chlordane, Heptachlor");
		pesticidesTradeRotterdamConvention.put("1427", "DDT, Hexachlorobenzene");
		pesticidesTradeRotterdamConvention.put("1428", "Pentachlorophenol");
		pesticidesTradeRotterdamConvention.put("1429", "Salts of Pentachlorophenol (excl 280811)");
		pesticidesTradeRotterdamConvention.put("1430", "Dinoseb and dinoseb salts");
		pesticidesTradeRotterdamConvention.put("1431", "DNOC and its salts (excl. 290811-290891)");
		pesticidesTradeRotterdamConvention.put("1432", "Oxirane (ethylene oxide)");
		pesticidesTradeRotterdamConvention.put("1433", "Dieldrin");		
		pesticidesTradeRotterdamConvention.put("1434", "Dinoseb acetate");
		pesticidesTradeRotterdamConvention.put("1435", "Binapacryl");
		pesticidesTradeRotterdamConvention.put("1436", "Chlorobenzilate");
		pesticidesTradeRotterdamConvention.put("1437", "2,4,5-T and its salts and esters");
		pesticidesTradeRotterdamConvention.put("1438", "Parathion & parathion-methyl");
		pesticidesTradeRotterdamConvention.put("1439", "Fluoroacetamide, monocrotophos & phosphamidon");
		pesticidesTradeRotterdamConvention.put("1440", "Chlordimeform");
		pesticidesTradeRotterdamConvention.put("1441", "Captafol & methamidophos");
	}
	
	private static LinkedHashMap<String, String> pesticidesTradeElements = new LinkedHashMap<String, String>();
	
	static {
		pesticidesTradeElements = new LinkedHashMap<String, String>();

		pesticidesTradeElements.put("2620", "Import Value");
		pesticidesTradeElements.put("5600", "Import Quantity");
		pesticidesTradeElements.put("2920", "Export Value");
		pesticidesTradeElements.put("5900", "Export Quantity");
	}
	
	
	private static LinkedHashMap<String, String> machineryElements = new LinkedHashMap<String, String>();
	
	static {
		machineryElements = new LinkedHashMap<String, String>();

		machineryElements.put("5116", "In Use");
		machineryElements.put("2610", "Import Quantity");
		machineryElements.put("2620", "Import Value");
		machineryElements.put("2910", "Export Quantity");
		machineryElements.put("2920", "Export Value");
	}
	
	
	/** 
	 * 
	 * This method creates the download panel
	 * 
	 */
	public static void callDownloadView() {
//		RootPanel.get("MAIN_CONTENT").add(new FAOSTATDownload().build());
		
		downloadView = new FAOSTATDownloadEntry(FAOSTATCurrentView.DOWNLOAD_STANDARD);
		callDownloadViewAgent(FAOSTATCurrentView.DOWNLOAD_STANDARD);
	}
	
	// TODO link it somewhere
	public static void callDownloadSubView(FAOSTATCurrentView subView) {
		downloadView = new FAOSTATDownloadEntry(subView);
		callDownloadViewAgent(subView);
	}
	
	public static void callDownloadViewAgent(FAOSTATCurrentView view) {
		downloadView.build(view);
	}
	
	public static Listener<ComponentEvent> callDownloadView(final FAOSTATCurrentView view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				callDownloadViewAgent(view);
			}
		};
	}

	public static void getQVOParameters(FAOSTATDownload download, DWFAOSTATQueryVO qvo) {
		List<DWCodesModelData> d = download.getDomains().getTree().getSelectionModel().getSelectedItems();

		HashMap<String, String> domains = new HashMap<String, String>(); 
		getCodes(domains, d, null, null);

		// check about FBS
		for(String key : domains.keySet()){
			if ( key != null && key.equalsIgnoreCase("FB")) {
				qvo.setIsFBS(true);
				// TODO: change not with checkbox...
				if ( download.getOutputType().getPivotTable().getFsbStyleCheckBox().getValue() ) {
					FAOSTATMainController.selectAll(download.getElementsList().getList());
					FAOSTATMainController.selectAll(download.getItems().getSelectorPanel().getList());
				}
			}
		}
			
		HashMap<String, String> areas = new HashMap<String, String>();
		if ( download.getAreas() != null ) {
			getCodes(areas, download.getAreas().getSelectorPanel().getList(), domains, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		}
		
		HashMap<String, String> reportedAreas = new HashMap<String, String>(); 
		if ( download.getReportedAreas() != null ) {
			getCodes(reportedAreas, download.getReportedAreas().getSelectorPanel().getList(), domains, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
		}
		
		HashMap<String, String> partnerAreas  = new HashMap<String, String>(); 
//		if ( download.getPartnerAreas() != null ) {
//			getCodes(partnerAreas, download.getPartnerAreas().getSelectorPanel().getList(), domains, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());
//		}
			
		HashMap<String, String> years = new HashMap<String, String>(); 
		getCodes(years, download.getYears().getList(), domains, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString());
			
		HashMap<String, String> items = new HashMap<String, String>(); 
		getCodes(items, download.getItems().getSelectorPanel().getList(), domains, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_DISAGGREGATIONS.toString());
			
		HashMap<String, String> elements = new HashMap<String, String>(); 
		if ( download.getElements() != null ) {
			getCodes(elements, download.getElements().getList(), domains, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT.toString());
		}
		
		HashMap<String, String> elementsList = new HashMap<String, String>(); 
		if ( download.getElementsList() != null ) {
			getCodes(elementsList, download.getElementsList().getList(), domains, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_LIST.toString());
		}
			
		// setting dimentions
		qvo.setDomains(domains);
		
		qvo.setAreas(areas);
		
		if ( !reportedAreas.isEmpty() || !partnerAreas.isEmpty() ) {
//			System.out.println("THEY ARE NOT EMPTY: "+ reportedAreas + " | " + partnerAreas);
			qvo.setIsTradeMatrix(true);
		}
		qvo.setReportedAreas(reportedAreas);
		qvo.setPartnerAreas(partnerAreas);
		
		qvo.setElements(elements);
		qvo.setElementsList(elementsList);
		qvo.setItems(items);
		qvo.setYears(years);
			
		// setting output type (Pivot or Flat Table)
		setOutput(download, qvo);
			
		setDownloadOptions(download.getOptions(), qvo);
	
		// setting as default all the available areas
		qvo.setIsCountryLevel(true);
		qvo.setIsRegionLevel(true);
	}
	
	/** TODO: Extends also with the REporterArea and ElementsList**/
	private static Boolean checkSelectors(FAOSTATDownload download) {
		Boolean check = true;

	
		if ( download.getAreas().getSelectorPanel().getPanel().isVisible() )
			if ( checkSelector(download.getAreas().getSelectorPanel().getList()) == false) {
				check = false;
				FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneArea());
			}
		
		if ( download.getReportedAreas().getSelectorPanel().getPanel().isVisible() )
			if ( checkSelector(download.getReportedAreas().getSelectorPanel().getList()) == false) {
				check = false;
				FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneArea());
			}
		
		if ( check != false)
			if ( checkSelector(download.getYears().getList()) == false) {
				check = false;
				FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneYear());
			}
		
		if ( check != false)
			if ( checkSelector(download.getItems().getSelectorPanel().getList()) == false) {
				check = false;
				FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneItem());
			}
		
		if ( download.getElements().getPanel().isVisible() )
			if ( check != false)
				if ( checkSelector(download.getElements().getList()) == false) {
					check = false;
					FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneElement());
				}
		
		if ( download.getElementsList().getPanel().isVisible() )
			if ( check != false)
				if ( checkSelector(download.getElementsList().getList()) == false) {
					check = false;
					FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneElement());
				}
		
		
		return check;
	}
	
	private static Boolean checkSelector(ListView<DWCodesModelData> list) {
		if ( list.getSelectionModel().getSelectedItems().size() > 0) {
			return true;
		}
		else 
			return false;
	}
	
	private static void setOutput(FAOSTATDownload download, DWFAOSTATQueryVO qvo) {
		FAOSTATDownloadOutputType outputType = download.getOutputType();
		// check if is checked Flat or pivot Table
		if ( outputType.getFlatTable().getCheckBox().getValue() ) {
			// Flat Table
			qvo.setOutput(DataViewerBoxContent.TABLE.toString());
		}
		else {
			// setting the PIVOT Table dimensions
			qvo.setOutput(DataViewerBoxContent.PIVOT_TABLE.toString());
//			qvo.setOutputType(DataViewerBoxContent.PIVOT_TABLE.toString());
			
			System.out.println("outputType.getPivotTable().getFsbStyleCheckBox().getValue(): " + outputType.getPivotTable().getFsbStyleCheckBox().getValue() );
			// checking if it's set FSB as Default
			if ( outputType.getPivotTable().getFsbStyleCheckBox().getValue() ) {
				qvo.setNestedby("area");
				qvo.setxAxis("element");
				qvo.setY1Axis("year");
				qvo.setY2Axis("item");
			}
			else {
				/** TODO: quick solution **/
				// the Trade Matrix is been commented out because it's hardcoded on the server side with a default layout
				if ( !qvo.getIsTradeMatrix())
						qvo.setNestedby(FAOSTATDimensionConstant.dimension_to_sp.get(outputType.getPivotTable().getNested().getCombo().getSelection().get(0).getCode()));
					else {
//						qvo.setNestedby(FAOSTATDimensionConstant.dimension_to_sp_matrix.get(outputType.getPivotTable().getNested().getCombo().getSelection().get(0).getCode()));
					}
		
					if ( !qvo.getIsTradeMatrix()) 
						qvo.setxAxis(FAOSTATDimensionConstant.dimension_to_sp.get(outputType.getPivotTable().getX().getCombo().getSelection().get(0).getCode()));
					else {
//						qvo.setxAxis(FAOSTATDimensionConstant.dimension_to_sp_matrix.get(outputType.getPivotTable().getX().getCombo().getSelection().get(0).getCode()));
					}
		
					if ( !qvo.getIsTradeMatrix()) 
						qvo.setY1Axis(FAOSTATDimensionConstant.dimension_to_sp.get(outputType.getPivotTable().getY1().getCombo().getSelection().get(0).getCode()));
					else {
//						qvo.setY1Axis(FAOSTATDimensionConstant.dimension_to_sp_matrix.get(outputType.getPivotTable().getY1().getCombo().getSelection().get(0).getCode()));
					}
						
					if ( !qvo.getIsTradeMatrix()) 
						qvo.setY2Axis(FAOSTATDimensionConstant.dimension_to_sp.get(outputType.getPivotTable().getY2().getCombo().getSelection().get(0).getCode()));
					else {
//						qvo.setY2Axis(FAOSTATDimensionConstant.dimension_to_sp_matrix.get(outputType.getPivotTable().getY2().getCombo().getSelection().get(0).getCode()));
					}
				}
		}
		
		
	}

	private static void setDownloadOptions(FAOSTATDownloadOptions options, DWFAOSTATQueryVO qvo) {
		
		// setting the labels for the download (affects the Flat Table)
		FAOSTATConstants.selectLabels(qvo, options.getShowCodes().getValue(), options.getShowFlags().getValue(), options.getShowUnits().getValue(), qvo.getIsTradeMatrix());
		
		// show flags
		qvo.setAddFlag(options.getShowFlags().getValue());

		// codes
		qvo.setShowCodes(options.getShowCodes().getValue());
		
		try {
			// show null values
			qvo.setShowNull(options.getShowNull().getValue());
		}catch (Exception e) {
		}
		
		try {
			// MU
			qvo.setShowMeasurementUnit(options.getShowUnits().getValue());
		}catch (Exception e) {
		}
		
		try {
			// thousand separator
			qvo.setThousandSeparator(options.getThousandCombo().getValue().getCode());
		}catch (Exception e) {
		}
		
		try {
			// Decimal separator
			qvo.setDecimalseparator(options.getDecimalCombo().getValue().getCode());
		}catch (Exception e) {
		}
		
		try {
			// Decimals values
			qvo.setShowDec(options.getDecimalValuesCombo().getValue().getCode());
		}catch (Exception e) {
		}
	}
	
	public static SelectionListener<IconButtonEvent> getTopDomains(final FAOSTATDownloadDomainPanel domains) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				getTopDomainsAgent(domains);
			}
		};
	}
	
	public static void getTopDomainsAgent(final FAOSTATDownloadDomainPanel domains) {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_GROUPS.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					TreeStore treeStore = domains.getStore();
					treeStore.removeAll();		
					for(DWCodesModelData code : vo.getCodes()) {
						treeStore.add(code, true);
					}
				}
				public void onFailure(Throwable arg0) {
				}
			});	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getFenixDomainsAgent(final TreeStore treeStore) {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_DOMAINS_FROM_FENIX.toString());		
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
				}
				public void onFailure(Throwable arg0) {
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getDomainsAgent(final FAOSTATDownload download, final TreeStore<DWCodesModelData> treeStore, final TreePanel<DWCodesModelData> tree, final String defaultDomainCode) {
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
			
						// Hardcode for the Water (aquastat code)
						// TODO: add it to the database and manage it when it's called the domain 'AQUASTAT'
						for(DWCodesModelData c : vo.getCodesHierachy().get(topcode)) {
							// adding after pesticides trade
							treeStore.add(topcode, c, true);
							if ( c.getCode().equals("RT")) {
								DWCodesModelData aquastat = new DWCodesModelData("AQUASTAT" , FAOSTATLanguage.print().water() + " (" + FAOSTATLanguage.print().aquastat() + ")");
								treeStore.add(topcode, aquastat, true);
							}
						}
					}
					
					// check default code (for now top code...chage with all codes...)
					if ( defaultDomainCode != null) {
						for(DWCodesModelData topCode : treeStore.getAllItems()) {
							if ( topCode.getCode().equals(defaultDomainCode)) {
								DWCodesModelData parentDomain = tree.getStore().getParent(topCode);
								tree.setExpanded(topCode, true);
							
								download.buildIntroductionPage(topCode);
								
								tree.getSelectionModel().select(topCode, true);
								
								download.getDomains().setCurrentCode(topCode.getCode());
								download.getTablePanel().getTablesPanel().removeAll();
								
								if(parentDomain==null){
									download.getTitlePanel().build(topCode.getLabel(), "");
								} 
								else {
									download.getTitlePanel().build(parentDomain.getLabel() + " > " + topCode.getLabel(), topCode.getDateLastUpdate());
								}
								
								download.getTitlePanel().getPanel().layout();

								break;
							}
						}
					}
					else {						
//						tree.expandAll();						
					}
						
						
					// remove from the list
					// FT - Forestry trade matrix
					// TM - Trade Matrix
					// QV - Value of Agrocultural Production
					List<DWCodesModelData> removeCodes = new ArrayList<DWCodesModelData>();
					for(DWCodesModelData code : treeStore.getAllItems()) {
						System.out.println("CODE: " + code.getCode());
						if ( code.getCode().equals("QV")) {
							removeCodes.add(code);
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
	
	
	public static void getDomainsAgent(final FAOSTATBulkDownloadTreePanel download, final TreeStore<DWCodesModelData> treeStore, final TreePanel<DWCodesModelData> tree, final String defaultDomainCode) {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ALL_GROUPS_DOMAINS_BULK_DOWNLOAD.toString());		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					treeStore.removeAll();
					
					for(DWCodesModelData topcode : vo.getCodesHierachy().keySet()) {
						treeStore.add(topcode, true);
			
						// Hardcode for the Water (aquastat code)
						// TODO: add it to the database and manage it when it's called the domain 'AQUASTAT'
						for(DWCodesModelData c : vo.getCodesHierachy().get(topcode)) {
							// adding after pesticides trade
							treeStore.add(topcode, c, true);
						}
					}	
				}
				
				public void onFailure(Throwable arg0) {
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getDomainsAgent(final FAOSTATCompareData compare, final TreeStore<DWCodesModelData> treeStore, final TreePanel<DWCodesModelData> tree, final String defaultDomainCode) {
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
//						treeStore.add(topcode, vo.getCodesHierachy().get(topcode), true);
						
						// Hardcode for the Water (aquastat code)
						// TODO: add it to the database and manage it when it's called the domain 'AQUASTAT'
						for(DWCodesModelData c : vo.getCodesHierachy().get(topcode)) {
							// adding after pesticides trade
							treeStore.add(topcode, c, true);
							if ( c.getCode().equals("RT")) {
								DWCodesModelData aquastat = new DWCodesModelData("AQUASTAT" , FAOSTATLanguage.print().water() + " (" + FAOSTATLanguage.print().aquastat() + ")");
								treeStore.add(topcode, aquastat, true);
							}
						}
						
					}
					
					// check default code (for now top code...chage with all codes...)
					if ( defaultDomainCode != null) {
//						System.out.println("defaultDomainCode: " + defaultDomainCode);
						for(DWCodesModelData topCode : treeStore.getAllItems()) {
//							System.out.println("topCode: " + topCode.getCode());
							if ( topCode.getCode().equals(defaultDomainCode)) {
								
								DWCodesModelData parentDomain = tree.getStore().getParent(topCode);
								
//								System.out.println("select tree: " + topCode.getCode());
//								tree.getSelectionModel().select(topCode, true);
								tree.setExpanded(topCode, true);
//								download.buildSelectors(topCode);

								
								tree.getSelectionModel().select(topCode, true);
								
								if(parentDomain==null){
		
								} else {
									
									String date = getDate(topCode.getDateLastUpdate());
								}

								break;
							}
						}
					}
					else {						
//						tree.expandAll();						
					}
						
						
					// remove from the list
					// FT - Forestry trade matrix
					// TM - Trade Matrix
//					List<DWCodesModelData> removeCodes = new ArrayList<DWCodesModelData>();
					for(DWCodesModelData code : treeStore.getAllItems()) {
						System.out.println("CODE: " + code.getCode());
//						if ( code.getCode().equals("FT") || code.getCode().equals("TM")) {
//							removeCodes.add(code);
//						}
					}
						
//					for(DWCodesModelData code : removeCodes){
////						System.out.println("removing: " + code.getCode());
//						treeStore.remove(code);
//					}
						
						
					
					
					
				}
				
				public void onFailure(Throwable arg0) {
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** TODO: convert it?? **/
	public static String getDate(String textDate) {
		System.out.println("textDate: " + textDate);
//		textDate = textDate.replace(" 00:00:00.0", "");
//		System.out.println("textDate2: " + textDate);
//		Date date = new Date(textDate);
		
//		System.out.println("DATE: " + date.toString());
		
//		String text = "";
		
		textDate = "(" +FAOSTATLanguage.print().dateLastUpdate() + ": " + textDate+ ")"; 
		
		return textDate;
		
	}
	
	
	public static void getSelectors(final FAOSTATDownloadSelectorPanel panel, DWFAOSTATQueryVO qvo, String selectionType, final Boolean addSelectAll) {
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					panel.getPanel().removeAll();
					ListStore store = panel.getStore();
					store.removeAll();
					
					
					for(DWCodesModelData topcode : vo.getCodes()) {
						store.add(topcode);

					}
					
					/** TODO: make a unique builder **/
					if ( panel.getTitle() != null )
						panel.getPanel().add(panel.addTitle(panel.getTitle()));
					
					panel.addList();
					panel.getPanel().layout();
					
					if ( addSelectAll ) {
						panel.getPanel().add(DataViewerClientUtils.addVSpace(5));
						panel.getPanel().add(panel.buildSelectAllPanel());
					}
					
					panel.getPanel().layout();
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** ad hoc for fertilizers, pesticides and land...**/
	public static void getSelectorsItems(final FAOSTATDownloadSelectorPanel panel, DWFAOSTATQueryVO qvo, String selectionType, final Boolean addSelectAll, final Boolean selectAll, final DWCodesModelData domainFilter) {
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					panel.getPanel().removeAll();
					ListStore store = panel.getStore();
					store.removeAll();
					
					// fertilizers
					// ad hoc for fertilizers
					if ( domainFilter.getCode().equals("RF")) {
						itemsListForFertilizers(vo, store);
					}
					else if ( domainFilter.getCode().equals("RV") ) {
						itemsForFertilizersTrade(vo, store);
					}
					else if ( domainFilter.getCode().equals("RL")) {
						itemsListForLand(vo, store);
					}
					else if ( domainFilter.getCode().equals("RT")) {
						itemsForPesticidesTrade(vo, store);
					}
					else {
						for(DWCodesModelData topcode : vo.getCodes()) {
							store.add(topcode);
						}
					}
						
					/** TODO: make a unique builder **/
					if ( panel.getTitle() != null )
						panel.getPanel().add(panel.addTitle(panel.getTitle()));
						
					panel.addList();
					panel.getPanel().layout();
					
					if ( addSelectAll ) {
						panel.getPanel().add(DataViewerClientUtils.addVSpace(5));
						panel.getPanel().add(panel.buildSelectAllPanel());
					}
					
					if ( selectAll ) {
						FAOSTATMainController.selectAllAgent(panel.getList());
					}
						
					panel.getPanel().layout();
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** ad hoc for fertilizers, pesticides and land...**/
	public static void getSelectorsElementsList(final FAOSTATDownloadSelectorPanel panel, DWFAOSTATQueryVO qvo, String selectionType, final Boolean addSelectAll, final Boolean selectAll, final DWCodesModelData domainFilter) {
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					panel.getPanel().removeAll();
					ListStore store = panel.getStore();
					store.removeAll();
					
					// fertilizers
					// ad hoc for fertilizers
					if ( domainFilter.getCode().equals("RF")) {
						sortByMap(vo, store, fertilizersElements);
					}
					else if ( domainFilter.getCode().equals("RA") ) {
						sortByMap(vo, store, fertilizersArchiveElements);
					}
					else if ( domainFilter.getCode().equals("RV")) {
						sortByMap(vo, store, fertilizersTradeElements);
					}
					else if ( domainFilter.getCode().equals("RT")) {
						sortByMap(vo, store, pesticidesTradeElements);
					}
					else if ( domainFilter.getCode().equals("RM")) {
						sortByMap(vo, store, machineryElements);
					}
					else if ( domainFilter.getCode().equals("RY")) {
						sortByMap(vo, store, machineryElements);
					}
					else {
						for(DWCodesModelData topcode : vo.getCodes()) {
							System.out.println("CODE: " + topcode.getCode() + " | " + topcode.getLabel());
							store.add(topcode);
						}
					}
//						
					/** TODO: make a unique builder **/
					if ( panel.getTitle() != null )
						panel.getPanel().add(panel.addTitle(panel.getTitle()));
						
					panel.addList();
					panel.getPanel().layout();
					
					if ( addSelectAll ) {
						panel.getPanel().add(DataViewerClientUtils.addVSpace(5));
						panel.getPanel().add(panel.buildSelectAllPanel());
					}
					
					System.out.println("SELECT ALL: " + selectAll);
					if ( selectAll ) {
						FAOSTATMainController.selectAllAgent(panel.getList());
					}
						
					panel.getPanel().layout();
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void itemsListForFertilizers(DWFAOSTATResultVO vo, ListStore store) {
		
		// adding the top
		List<DWCodesModelData> toRemove = new ArrayList<DWCodesModelData>();
		for(DWCodesModelData code : vo.getCodes()) {
			//Nitrogen Fertilizers (N total nutrients)
			if ( code.getCode().equals("3102")) {
				store.add(code);
				toRemove.add(code);
			}
			//Phosphate Fertilizers (P205 total nutrients)
			else if  ( code.getCode().equals("3103")) {
				store.add(code);
				toRemove.add(code);
			}
			//Potash Fertilizers (K20 total nutrients)
			else if  ( code.getCode().equals("3104")) {
				store.add(code);
				toRemove.add(code);
			}
		}
		
		vo.getCodes().removeAll(toRemove);

		store.add(new DWCodesModelData("", "----------------------------------------------"));
		
		for(DWCodesModelData code : vo.getCodes()) {
			store.add(code);
		}
	}
	
	private static void sortByMap(DWFAOSTATResultVO vo, ListStore store, LinkedHashMap<String, String> map) {

		// adding first list
		for(String code : map.keySet()) {
			for(DWCodesModelData c : vo.getCodes()) {
				if ( c.getCode().equalsIgnoreCase(code)) {
					store.add(c);
					break;	
				}
			}
		}
	}
	
	private static void itemsForFertilizersTrade(DWFAOSTATResultVO vo, ListStore store) {

		// adding first list
		for(String code : fertilizersTradeItems.keySet()) {
			for(DWCodesModelData c : vo.getCodes()) {
				if ( c.getCode().equalsIgnoreCase(code)) {
					store.add(c);
					break;	
				}
			}
		}
	}
	
	
	private static void itemsListForLand(DWFAOSTATResultVO vo, ListStore store) {

		// adding the top
		List<DWCodesModelData> toRemove = new ArrayList<DWCodesModelData>();
		for(DWCodesModelData code : vo.getCodes()) {
			//Country area
			if ( code.getCode().equals("6600")) {
				store.add(code);
				toRemove.add(code);
			}
			//Land area
			else if  ( code.getCode().equals("6601")) {
				store.add(code);
				toRemove.add(code);
			}
		}
		
		vo.getCodes().removeAll(toRemove);

		
//		store.add(new DWCodesModelData("", "----------------------------------------------"));
		
		for(DWCodesModelData code : vo.getCodes()) {
			store.add(code);
		}
	}
	
	private static void itemsForPesticidesTrade(DWFAOSTATResultVO vo, ListStore store) {

		// adding first list
		for(String code : pesticidesTrade.keySet()) {
			for(DWCodesModelData c : vo.getCodes()) {
				if ( c.getCode().equalsIgnoreCase(code)) {
					store.add(c);
					break;	
				}
			}
		}
			
		DWCodesModelData rc = new DWCodesModelData("-1", "--- " + FAOSTATLanguage.print().rotterdamConventionPesticides() + " ---");
		
		store.add(rc);
		
		// adding  Rotterdam Convention Pesticides
		for(String code : pesticidesTradeRotterdamConvention.keySet()) {
			for(DWCodesModelData c : vo.getCodes()) {
				if ( c.getCode().equalsIgnoreCase(code)) {
					store.add(c);
					break;	
				}
			}
		}
	}
	
	
	public static SelectionListener<ButtonEvent> exportCSV(final FAOSTATDownload download) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				exportCSVAgent(download);
			}
		};
	}

	public static void exportCSVAgent(FAOSTATDownload download) {
		if ( checkSelectors(download) ) {
			// clean the tokens
			tokensAsync = 0;
			tokensReached = 0;
			
			DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
			FAOSTATConstants.setFAOSTATDBSettings(qvo);		
			
			getQVOParameters(download, qvo);
			
			qvo.setTypeOfOutput(DataViewerBoxContent.EXPORT_CSV.toString());
						
			GoogleAnalyticsController.trackEvent(FAOSTATCurrentView.DOWNLOAD_STANDARD.toString(), qvo.getTypeOfOutput() + " - " + qvo.getOutput(), FAOSTATMainController.getGoogleLabel(qvo));
				
			final LoadingWindow loadingWindow = new LoadingWindow(FAOSTATLanguage.print().exporting(), FAOSTATLanguage.print().pleaseWait()+" ...", FAOSTATLanguage.print().loading());
	
			loadingWindow.showLoadingBox();
			
			if ( tokensAsync > 0)
				checkExportIfAllTheAsyncCallsAreReturned(qvo, download, loadingWindow);
			else
				exportCSVAgentCall(qvo, download, loadingWindow);
		}
	}
	
	private static void exportCSVAgentCall(DWFAOSTATQueryVO qvo, FAOSTATDownload download, final LoadingWindow loadingWindow) {
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				public void onSuccess(DWFAOSTATResultVO vo) {			
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
	
	
	
	public static SelectionListener<ButtonEvent> showTable(final FAOSTATDownload download) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				showTableAgent(download);
			}
		};
	}
	
	
	public static void showTableAgent(FAOSTATDownload download) {
		if ( checkSelectors(download) ) {
			// clean the tokens
			tokensAsync = 0;
			tokensReached = 0;
			
			DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
			FAOSTATConstants.setFAOSTATDBSettings(qvo);		
			qvo.setTypeOfOutput(DataViewerBoxContent.HTML.toString());
			
			getQVOParameters(download, qvo);

			final LoadingWindow loadingWindow = new LoadingWindow(FAOSTATLanguage.print().creatingTables(), FAOSTATLanguage.print().pleaseWait()+" ...", FAOSTATLanguage.print().loading());
			loadingWindow.showLoadingBox();
			
			if ( tokensAsync > 0)
				checkPivotTableIfAllTheAsyncCallsAreReturned(qvo, download, loadingWindow);
			else
				showTableAgentCall(qvo, download, loadingWindow);
		}
	}
	
	private static void showTableAgentCall(DWFAOSTATQueryVO qvo, final FAOSTATDownload download, final LoadingWindow loadingWindow) {
		/** TODO: check is it's Flat or Pivot Table, do a better check **/
		if ( download.getOutputType().getFlatTable().getCheckBox().getValue() ) {
			buildFlatTable(qvo, download, loadingWindow);
		}
		else {
			buildPivotTables(qvo, download, loadingWindow);
		}
	}

	public static void buildFlatTable(DWFAOSTATQueryVO qvo, final FAOSTATDownload download, final LoadingWindow loadingWindow) {
		
		GoogleAnalyticsController.trackEvent(FAOSTATCurrentView.DOWNLOAD_STANDARD.toString(), "Show Tables - TABLE", FAOSTATMainController.getGoogleLabel(qvo));

		FAOSTATDownloadTablePanel tablePanel = download.getTablePanel();	
		tablePanel.getTablesPanel().removeAll();
		tablePanel.getViewMorePanel().hide();
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		loadingWindow.destroyLoadingBox();
		
		// LIMIT and Title (?)
		qvo.setLimit(100);
		qvo.setTitle("");

//		qvo.setTitle(FAOSTATLanguage.print().tableSampleMaxRows() + " "  + qvo.getLimit());
		
		tablePanel.getTablesPanel().add(panel);
		tablePanel.getTablesPanel().add(DataViewerClientUtils.addVSpace(15));	
		
		download.getSelectionPanel().layout();
		download.getTablePanel().getPanel().layout();
		FAOSTATPivotTableController.addPivotTable(panel, qvo, FAOSTATDownloadConstants.TABLE_WIDTH, FAOSTATDownloadConstants.TABLE_HEIGHT);
	}

	
	public static Listener<ComponentEvent> viewMoreTables(final FAOSTATDownloadTablePanel tablePanel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				buildPivotTablesAgent(tablePanel);
			}
		};
	}
	
	public static void buildPivotTables(DWFAOSTATQueryVO qvo, final FAOSTATDownload download, final LoadingWindow loadingWindow) {
		
		GoogleAnalyticsController.trackEvent(FAOSTATCurrentView.DOWNLOAD_STANDARD.toString(), "Show Tables - PIVOT_TABLE", FAOSTATMainController.getGoogleLabel(qvo));
		
		FAOSTATDownloadTablePanel tablePanel = download.getTablePanel();	
		tablePanel.getTablesPanel().removeAll();
		tablePanel.getViewMorePanel().show();

		tablePanel.setCurrentIndex(0);
		tablePanel.setQvo(qvo);
		
		loadingWindow.destroyLoadingBox();

		// FBS...
		FAOSTATDimensionConstant dimension = null;
		if ( download.getOutputType().getPivotTable().getFsbStyleCheckBox().getValue() ) {
			dimension = FAOSTATDimensionConstant.valueOf("COUNTRIES");
		}
		else {
			// LIMIT??
			qvo.setLimit(100);
			if ( !qvo.getIsTradeMatrix())
				dimension = FAOSTATDimensionConstant.valueOf(download.getOutputType().getPivotTable().getNested().getCombo().getSelection().get(0).getCode());
		}
		tablePanel.setDimension(dimension);
		
		// TODO: workaround for the pivot table of the trade matrix
		if ( !qvo.getIsTradeMatrix()) {
			LinkedHashMap<String, String> map = download.getTablePanel().getCodes();
			map.clear();
			
			switch (dimension) {
			case ELEMENTS:
				// TODO: this a workaround for the filter
				tablePanel.setDimension(FAOSTATDimensionConstant.ELEMENTS_LIST);
				map = FAOSTATMainController.sortByValuesASC(qvo.getElementsList());
				break;
				
			case ELEMENTS_LIST:
				map = FAOSTATMainController.sortByValuesASC(qvo.getElementsList());
				break;
				
			case COUNTRIES: {
					if ( !qvo.getIsTradeMatrix() ) {
						map = FAOSTATMainController.sortByValuesASC(qvo.getAreas());
					}
					else{
						map = FAOSTATMainController.sortByValuesASC(qvo.getReportedAreas());
						}
	//				System.out.println("MAP COUNTRIES: " + map);
					}
			
				break;
				
			case ITEMS:
				map = FAOSTATMainController.sortByValuesASC(qvo.getItems());
				break;
				
			case YEARS:
				map = FAOSTATMainController.sortByValuesDESC(qvo.getYears());
				break;
	
			default:
				break;
			}

			download.getTablePanel().getCodes().putAll(map);	
			buildPivotTablesAgent(tablePanel); 
		}
		else {
			qvo.setTitle("");
			ContentPanel panel = new ContentPanel();
			panel.setHeaderVisible(false);
			panel.setBodyBorder(false);
			tablePanel.getViewMorePanel().hide();
			tablePanel.getTablesPanel().add(panel);
			FAOSTATPivotTableController.addPivotTable(panel, qvo, FAOSTATDownloadConstants.TABLE_WIDTH, FAOSTATDownloadConstants.TABLE_HEIGHT);
			tablePanel.getTablesPanel().layout();
		}
	}
	
	public static void buildPivotTablesAgent(FAOSTATDownloadTablePanel tablePanel) {
		
		LinkedHashMap<String, String> map = tablePanel.getCodes();
		DWFAOSTATQueryVO qvo = tablePanel.getQvo();
		FAOSTATDimensionConstant dimension = tablePanel.getDimension();

		Integer currentIndex = tablePanel.getCurrentIndex();
		Integer maxResults = tablePanel.getMaxResults();
		
		int i = 0;
		for( String key : map.keySet() ) {
			if ( i >= currentIndex && i < (currentIndex + maxResults) ) {
				Map<String, String> values = new HashMap<String, String>();
				values.put(key, map.get(key));
				
				qvo.setTitle(map.get(key));
				FAOSTATVisualizeController.addToFilters(qvo, values, dimension.toString(), null);
				ContentPanel panel = new ContentPanel();
				panel.setHeaderVisible(false);
				panel.setBodyBorder(false);

				tablePanel.getTablesPanel().add(panel);
								
				tablePanel.getTablesPanel().layout();
				FAOSTATPivotTableController.addPivotTable(panel, qvo, FAOSTATDownloadConstants.TABLE_WIDTH, FAOSTATDownloadConstants.TABLE_HEIGHT);
				tablePanel.getTablesPanel().add(DataViewerClientUtils.addVSpace(35));
			}
			i++;
		}
		
		tablePanel.updateIndex(currentIndex + maxResults);
	}

	public static void getCodes(HashMap<String, String> map, ListView<DWCodesModelData> list, Map<String, String> domains, String resourceType) {
		List<DWCodesModelData> selectedCodes = list.getSelectionModel().getSelectedItems();
		getCodes(map, selectedCodes, domains, resourceType);
	}
	

	private static void getCodes(final HashMap<String, String> map, List<DWCodesModelData> selectedCodes, Map<String, String> domains, String selectorType) {
		HashMap<String, String> codes = new HashMap<String, String>();
		HashMap<String, String> aggregatedCodes = new HashMap<String, String>();
		
		for(DWCodesModelData code : selectedCodes) { 
			if ( !code.isList() ) {
				// the code
				if ( !code.getCode().equalsIgnoreCase("")) {
					codes.put(code.getCode(), code.getLabel());
				}
			}
			else {
				// the code is disaggregated
				aggregatedCodes.put(code.getCode(), code.getLabel());
			}
		}
		
		// adding codes
		map.putAll(codes);
		
		if ( !aggregatedCodes.isEmpty() ) {
			// an async call is required
			tokensAsync = tokensAsync +1;
			
			DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
			qvo.setOutput(DataViewerBoxContent.GET.toString());
			qvo.setTypeOfOutput(selectorType);			
			FAOSTATConstants.setFAOSTATDBSettings(qvo);
			
			qvo.getAggregationsFilter().putAll(aggregatedCodes);
			qvo.getDomains().putAll(domains);

			// TODO: get codes
			try {
				DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					@SuppressWarnings("unchecked")
					public void onSuccess(DWFAOSTATResultVO vo) {		
						for(DWCodesModelData code : vo.getCodes()) {
							map.put(code.getCode(), code.getLabel());
						}
//						System.out.println("MAP: " + map);
						tokensReached++;
					}
					
					public void onFailure(Throwable arg0) {
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
//			System.out.println("aggregatedCodes: " + aggregatedCodes);
		}
		
	}
	

	public static Listener<ComponentEvent> reloadFilter(final FAOSTATDownloadSelectorPanel panel, final DWCodesModelData domainFilter, final String selectionType, final Boolean isTotalAndList, final Boolean addSelectAll, final String width, final String height) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				reloadFilterAgent(panel, domainFilter, selectionType, isTotalAndList, addSelectAll, width, height);
			}
		};
	}
	
	
	public static void reloadFilterAgent(FAOSTATDownloadSelectorPanel panel, DWCodesModelData domainFilter, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {
		panel.getPanel().removeAll();
		panel.build(domainFilter, null, selectionType, isTotalAndList, addSelectAll, width, height);
		panel.getPanel().layout();
	}
	
	
	public static Listener<ComponentEvent> reloadFilter(final FAOSTATDownloadSelectorPanel panel, final List<DWCodesModelData> domainFilter, final String selectionType, final Boolean isTotalAndList, final Boolean addSelectAll, final String width, final String height) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				reloadFilterAgent(panel, domainFilter, selectionType, isTotalAndList, addSelectAll, width, height);
			}
		};
	}
	
	
	public static void reloadFilterAgent(FAOSTATDownloadSelectorPanel panel, List<DWCodesModelData> domainFilter, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {
		panel.getPanel().removeAll();
		panel.build(domainFilter, null, selectionType, isTotalAndList, addSelectAll, width, height);
		panel.getPanel().layout();
	}
	
	/** TODO: change it...should not be like that the test... **/ 
	public static void buildTabCheck(final ClickHtml html, final FAOSTATDownloadSelectorPanel selectorPanel,final  DWCodesModelData domainFilter,final  String title, final String selectionType, final Boolean isTotalAndList, final Boolean addSelectAll, final String width, final String height) {

		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);				
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setDomains(buildDomain(domainFilter));

		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					@SuppressWarnings("unchecked")
					public void onSuccess(DWFAOSTATResultVO vo) {
						if ( !vo.getCodes().isEmpty() ) {
							html.show();
							/** TODO: all the rests at false **/
						}
						else {
							html.hide();
						}
					}
					
					public void onFailure(Throwable arg0) {

					}
				});
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}

	}
	
	public static void buildTabCheck(final ClickHtml html, final  DWCodesModelData domainFilter, String selectionType) {
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);			
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setDomains(buildDomain(domainFilter));
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {					
					@SuppressWarnings("unchecked")
					public void onSuccess(DWFAOSTATResultVO vo) {
						if ( !vo.getCodes().isEmpty() ) {
							html.show();
						}
						else {
							html.hide();
						}
					}
					public void onFailure(Throwable arg0) {
					}
				});
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}

	}
	
	public static HorizontalPanel buildTab(FAOSTATDownloadSelectorPanel selectorPanel, DWCodesModelData domainFilter, String title, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height, Boolean isSelected) {
		HorizontalPanel p = new HorizontalPanel();
		ClickHtml html = new ClickHtml();
		html.setHtml("<div class=''>" + title + "</div>");
		p.add(html);
		return p;
	}
	
	private static HashMap<String, String> buildDomain(DWCodesModelData domainFilter) {
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(domainFilter.getCode(), domainFilter.getLabel());
		
		return domain;
	}
	
	/***********************/
	
	public static void checkExportIfAllTheAsyncCallsAreReturned(final DWFAOSTATQueryVO qvo, final FAOSTATDownload download, final LoadingWindow loadingWindow) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
					/** TODO: start download **/
					System.out.println("start download...");
					exportCSVAgentCall(qvo, download, loadingWindow);
					timer.cancel();
				}

			}
		};
		timer.scheduleRepeating(10);
	}
	
	public static void checkPivotTableIfAllTheAsyncCallsAreReturned(final DWFAOSTATQueryVO qvo, final FAOSTATDownload download, final LoadingWindow loadingWindow) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				// TODO: should be >= ? 
				if ( tokensAsync == tokensReached ) {
					System.out.println("start download...");
					showTableAgentCall(qvo, download, loadingWindow);
					timer.cancel();
				}

			}
		};
		timer.scheduleRepeating(10);
	}
	
	public static void destroy() {
		if (timer != null)
		timer.cancel();
	}

	public static void getDomainNotes(final FAOSTATDownloadNotes panel, DWFAOSTATQueryVO qvo) {
		
		try {
			
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					FAOSTATNotesPanel notesPanel = new FAOSTATNotesPanel(vo.getNotes());
					panel.getPanel().add(notesPanel.getPanel());
					panel.getPanel().layout();
					showNotesAgent(vo.getNotes().get(0).getContent(), "Empty Label", notesPanel.getWrapper());
				}
				
				public void onFailure(Throwable e) {
					FenixAlert.error("Error", e.getMessage());
				}
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static ClickHandler showNotes(final String code, final String label, final VerticalPanel wrapper) {
		return new ClickHandler() {
			public void onClick(ClickEvent e) {
				showNotesAgent(code, label, wrapper);
			}
		};
	}
	
	public static void showNotesAgent(final String code, final String label, final VerticalPanel wrapper) {
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		qvo.setOutput("DOMAIN_NOTES_CONTENT");
		qvo.setTypeOfOutput("TABLE");
		qvo.getDomains().put(code, label);
		qvo.setLanguage(FAOSTATConstants.faostatLanguage);
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				public void onSuccess(DWFAOSTATResultVO vo) {
					wrapper.removeAll();
					wrapper.setLayout(new FitLayout());
					wrapper.add(new HTML(vo.getNotes().get(0).getContent()));
					wrapper.getLayout().layout();
				}
				public void onFailure(Throwable e) {
					FenixAlert.error("Error", e.getMessage());
				}
			});
		} catch (FenixGWTException e1) {
			FenixAlert.error("Error", e1.getMessage());
		}
	}
	
}
