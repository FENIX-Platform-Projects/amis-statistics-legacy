package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.chart.model.component.Series;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATChartController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare.FAOSTATCompareData;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare.FAOSTATCompareSelectionPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection.FAOSTATMultipleSelectionPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection.FAOSTATVisualizeParameters;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeTimerangeFilter;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATJoinQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.constants.FAOSTATCompareConstants;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FAOSTATCompareDataController extends FAOSTATMainController {
	
	public static Integer tokensAsync = 0;
	
	public static Integer tokensReached = 0;
	
	public static Timer timer;
	
	static Integer maxSeriesNumber = 42;
	
	public static SelectionListener<ButtonEvent> compareData(final FAOSTATCompareData compare) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				compareALLDataAgent(compare);
			}
		};
	}
	
	private static Boolean checkCompareDataAgent(FAOSTATCompareData compare) {
		Boolean check = true;
		
		// if all the domains are removed
		if ( compare.getSelectionPanel().getDomainSelectionPanels().isEmpty() ) {
			FenixAlert.alert(FAOSTATLanguage.print().selectionError(), "Please select at least a Domain");
			return false;
		}
		
		
		// checking selections
		FAOSTATCompareSelectionPanel selectionPanel = compare.getSelectionPanel();
		// checking areas selection
		if ( !checkSelection(selectionPanel.getAreas().getGrid()) ) {
			FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneArea());
			return false;
		}
		
		// for all the domains
		for (String domainCode : compare.getSelectionPanel().getDomainSelectionPanels().keySet()) {
			FAOSTATMultipleSelectionPanel multiselectionPanel = compare.getSelectionPanel().getDomainSelectionPanels().get(domainCode);
			// checking items selection
			if ( !checkSelection(multiselectionPanel.getItems().getGrid()) ) {
				FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneItem());
				return false;
			}
			if ( !checkSelection(multiselectionPanel.getElements().getGrid()) ) {
				FenixAlert.alert(FAOSTATLanguage.print().selectionError(), FAOSTATLanguage.print().pleaseSelectAtLeastOneElement());
				return false;
			}
		}
		
		// too many series
		if ( !checkSeriesNumber(compare) ) {
			return false;
		}
		
		return check;
	}
	
	public static Boolean checkSelection(Grid<DWCodesModelData> g) {
		Boolean check = true;
		if ( g.getSelectionModel().getSelectedItems().isEmpty() )
			return false;
		return check;
	}
	
	public static Integer checkSelectionNumber(Grid<DWCodesModelData> g) {
		return g.getSelectionModel().getSelectedItems().size();
	}
	
	
	private static Boolean checkSeriesNumber(FAOSTATCompareData compare) {
		Integer areas = 0;
		Integer seriesNumber = 0;
		
		List<Integer> itemsElements = new ArrayList<Integer>();
		List<Integer> seriesNumbers = new ArrayList<Integer>();

		
		FAOSTATCompareSelectionPanel selectionPanel = compare.getSelectionPanel();
		
		// checking areas 
		areas = checkSelectionNumber(selectionPanel.getAreas().getGrid());
		// for all the domains
		for (String domainCode : compare.getSelectionPanel().getDomainSelectionPanels().keySet()) {
			FAOSTATMultipleSelectionPanel multiselectionPanel = compare.getSelectionPanel().getDomainSelectionPanels().get(domainCode);
			// checking items 
			Integer items = checkSelectionNumber(multiselectionPanel.getItems().getGrid());
			// checking elements
			Integer elements = checkSelectionNumber(multiselectionPanel.getElements().getGrid());
			
			itemsElements.add(items * elements);
		}
		
		for(Integer itemsElement : itemsElements ){
			seriesNumbers.add(areas * itemsElement);
		}
		
		for(Integer s : seriesNumbers ) {
			seriesNumber = seriesNumber +s;
		}
		if ( seriesNumber > maxSeriesNumber) {
			FenixAlert.alert(FAOSTATLanguage.print().selectionError(), "The current selections have exceed the number of series that can be displayed in one chart (max = "+ maxSeriesNumber + ", current selection =" + seriesNumber +").");
			return false;
		}
		
		return true;
	}
	
	public static void compareALLDataAgent(FAOSTATCompareData compare) {
		
		if ( checkCompareDataAgent(compare) ) {
			
			// setting up the main QVO
			DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
			
			// output TODO: dynamic
			qvo.setOutput(DataViewerBoxContent.CHART.toString());
			qvo.setTypeOfOutput(DataViewerBoxContent.TIMESERIE.toString());
			
			qvo.setTitle("");
			// dynamic
			qvo.setWidth(String.valueOf(FAOSTATCompareConstants.CHART_WIDTH));
			qvo.setHeight(String.valueOf(FAOSTATCompareConstants.CHART_HEIGHT));
			
			// TODO: the check should be made when the selection is changed
			FAOSTATVisualizeTimerangeFilter timerange = compare.getSelectionPanel().getTimerange();
			Map<String, String> years = getTimerangeFilter(timerange.getFromCombo(), timerange.getToCombo());
			
			// getting areas
			Map<String, String> areas = getSelectedCodes(compare.getSelectionPanel().getAreas().getGrid());
	
			// selects (this is for the old join query)
//			List<String> selects = getSelectsCompareALLData(compare.getSelectionPanel().getDomainSelectionPanels().size());
//			qvo.setSelects(selects);
	
			// This contains all the QVOs to join...
			FAOSTATJoinQueryVO joinQueryVO = new FAOSTATJoinQueryVO();
			List<DWFAOSTATQueryVO> qvos = new ArrayList<DWFAOSTATQueryVO>();
	
			for (String domainCode : compare.getSelectionPanel().getDomainSelectionPanels().keySet()) {
				FAOSTATMultipleSelectionPanel multiselectionPanel = compare.getSelectionPanel().getDomainSelectionPanels().get(domainCode);
				qvos.add(createQVOCompare(multiselectionPanel, areas, years));
//				qvos.add(createQVOJoinQuery(multiselectionPanel, areas, years));
			}
			joinQueryVO.setQvos(qvos);
	
			qvo.setJoinQueryVO(joinQueryVO);
			qvo.setIsCompare(true);
//			qvo.setRunJoinQuery(true);
	
			// launch chart
			compare.getOutputPanel().removeAll();
			
			// TODO: dyanmic switcher
			FAOSTATChartController.addChart(compare.getOutputPanel(), qvo, qvo.getWidth(), qvo.getHeight());
	
			compare.getOutputPanel().layout();
		}
	}
	
	
	private static List<String> getSelectsCompareALLData(Integer joinQueriesSize) {
		List<String> selects = new ArrayList<String>();
		
		for(int i = 1; i <= joinQueriesSize; i++ ) {
			if ( i == 1) 
				selects.add("Q1.Year");
			selects.add("Q"+i+".AreaName"+ FAOSTATConstants.faostatLanguage +" + ' - ' + Q"+i+".ItemName"+ FAOSTATConstants.faostatLanguage +"+ ' (' + Q"+i+".ElementName"+ FAOSTATConstants.faostatLanguage +" + ') '+ Q"+i+".DomainCode");
			selects.add("Q"+i+".UnitName"+ FAOSTATConstants.faostatLanguage +"");
			selects.add("Q"+i+".Value");
		}
		return selects;
	}
	
	private static DWFAOSTATQueryVO createQVOJoinQuery(FAOSTATMultipleSelectionPanel multiselectionPanel, Map<String, String> areas, Map<String, String> years ) {
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();

		List<String> selects = new ArrayList<String>();

		selects.add("D.Year");
		selects.add("A.AreaName"+ FAOSTATConstants.faostatLanguage +"");
		selects.add("I.ItemName"+ FAOSTATConstants.faostatLanguage +"");
		selects.add("D.DomainCode");
		selects.add("E.ElementName"+ FAOSTATConstants.faostatLanguage +"");
		selects.add("E.UnitName"+ FAOSTATConstants.faostatLanguage +"");
		selects.add("D.Value");
		qvo.setSelects(selects);
		
		Map<String, String> domains = new HashMap<String, String>();
		domains.put(multiselectionPanel.getDomainCode(),multiselectionPanel.getDomainCode());
		qvo.setDomains(domains);
		qvo.setAreas(areas);
		qvo.setYears(years);
		
		List<String> orderbys = new ArrayList<String>();
		orderbys.add("Year");
		qvo.setOrderBys(orderbys);
		qvo.setSortingOrder("ASC");
		
		// getting items
		Map<String, String> items = getSelectedCodes(multiselectionPanel.getItems().getGrid());
		qvo.setItems(items);
		
		// getting elementslist
		Map<String, String> elementslist = getSelectedCodes(multiselectionPanel.getElements().getGrid());
		qvo.setElementsList(elementslist);
		
		return qvo;
	}
	
	private static DWFAOSTATQueryVO createQVOCompare(FAOSTATMultipleSelectionPanel multiselectionPanel, Map<String, String> areas, Map<String, String> years ) {
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();

		List<String> selects = new ArrayList<String>();
		selects.add("A.AreaName"+ FAOSTATConstants.faostatLanguage +" + ' - ' + I.ItemName"+ FAOSTATConstants.faostatLanguage +" + ' (' + E.ElementName"+ FAOSTATConstants.faostatLanguage +" + ') ' + D.DomainCode AS SERIE" );
		selects.add("D.Year");
		selects.add("D.Value");
		selects.add("E.UnitName"+ FAOSTATConstants.faostatLanguage);
		qvo.setSelects(selects);
		
		// this orders the series based on the serie name
		// TODO: check if it's useful, the should could be made when all the series are retrieved from the DB
		List<String> orderbys = new ArrayList<String>();
		orderbys.add("SERIE");
		qvo.setOrderBys(orderbys);
		qvo.setSortingOrder("ASC");
		
		Map<String, String> domains = new HashMap<String, String>();
		domains.put(multiselectionPanel.getDomainCode(),multiselectionPanel.getDomainCode());
		qvo.setDomains(domains);
		qvo.setAreas(areas);
		qvo.setYears(years);

		// getting items
		Map<String, String> items = getSelectedCodes(multiselectionPanel.getItems().getGrid());
		qvo.setItems(items);
		
		// getting elementslist
		Map<String, String> elementslist = getSelectedCodes(multiselectionPanel.getElements().getGrid());
		qvo.setElementsList(elementslist);
		
		return qvo;
	}

	public static Map<String, String> getSelectedCodes(Grid<DWCodesModelData> g) {
		Map<String, String> codes = new HashMap<String, String>();
		List<DWCodesModelData> selectedCodes = g.getSelectionModel().getSelectedItems();
		for(DWCodesModelData c : selectedCodes ) {
			codes.put(c.getCode(), c.getLabel());
		}
		return codes;
	}
	
	
	private static void getCodes(final HashMap<String, String> map, List<DWCodesModelData> selectedCodes, Map<String, String> domains, String selectorType) {
		HashMap<String, String> codes = new HashMap<String, String>();

		HashMap<String, String> aggregatedCodes = new HashMap<String, String>();

		for (DWCodesModelData code : selectedCodes) {
			if (!code.isList()) {
				// the code
				if (!code.getCode().equalsIgnoreCase("")) {
					codes.put(code.getCode(), code.getLabel());
				}
			} else {
				// the code is disaggregated
				aggregatedCodes.put(code.getCode(), code.getLabel());
			}
		}

		// adding codes
		map.putAll(codes);

		if (!aggregatedCodes.isEmpty()) {
			// an async call is required
			tokensAsync = tokensAsync + 1;

			DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
			qvo.setOutput(DataViewerBoxContent.GET.toString());
			qvo.setTypeOfOutput(selectorType);
			// qvo.setOutputType(DataViewerBoxContent.GET.toString());
			// qvo.setResourceType(resourceType);
			FAOSTATConstants.setFAOSTATDBSettings(qvo);

			qvo.getAggregationsFilter().putAll(aggregatedCodes);
			qvo.getDomains().putAll(domains);

			// TODO: get codes
			try {
				DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
							@SuppressWarnings("unchecked")
							public void onSuccess(DWFAOSTATResultVO vo) {
								for (DWCodesModelData code : vo.getCodes()) {
									map.put(code.getCode(), code.getLabel());
								}
								// System.out.println("MAP: " + map);
								tokensReached++;
							}

							public void onFailure(Throwable arg0) {

							}
						});

			} catch (Exception e) {
				e.printStackTrace();
			}
			// System.out.println("aggregatedCodes: " + aggregatedCodes);
		}

	}
	
	private static Map<String, String> getTimerangeFilter(ComboBox<DWCodesModelData> fromCombo, ComboBox<DWCodesModelData> toCombo) {
		
		Map<String, String> years = new LinkedHashMap<String, String>();

		Integer fromDate =  Integer.valueOf(fromCombo.getSelection().get(0).getCode());
		Integer toDate =  Integer.valueOf(toCombo.getSelection().get(0).getCode());

		if ( fromDate.compareTo(toDate) == 0 ) {
			years.put(fromDate.toString(), fromDate.toString());
		}
		else if ( fromDate.compareTo(toDate) < 0 ) {
			while( fromDate.compareTo(toDate) <= 0 ) {
				years.put(fromDate.toString(), fromDate.toString());
				fromDate++;
			}

		}

		return years;
	}
	
	
	
	
	public static SelectionListener<ButtonEvent> addDomainPanel(final FAOSTATCompareData compare) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				addDomainAgent(compare);
			}
		};
	}
	
	
	
	public static void addDomainAgent(FAOSTATCompareData compare) {

		DWCodesModelData selectedCode = compare.getDomains().getTree().getSelectionModel().getSelectedItem();

//		System.out.println("MAP: " + compare.getSelectionPanel().getDomainSelectionPanels() );
		// TODO: check if is a leaf
		if ( !compare.getSelectionPanel().getDomainSelectionPanels().containsKey(selectedCode.getCode())) {
			compare.getSelectionPanel().addDomainSelector(compare, selectedCode.getLabel(), selectedCode.getCode());
		}
	}
	
	public static Listener<BaseEvent> buildParameters(final ContentPanel panel, final Grid<DWCodesModelData> grid, final String title) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				buildParametersAgent(panel, grid, title);
			}
		};
	}
	
	public static void buildParametersAgent(final ContentPanel panel, final Grid<DWCodesModelData> grid, final String title) {
		panel.removeAll();
		Map<String, String> values = getSelectedCodes(grid);
		panel.add(new FAOSTATVisualizeParameters().buildParameter(title, values));
		panel.layout();
	}
	
	public static SelectionListener<ButtonEvent> deselectAll(final ContentPanel panel, final Grid<DWCodesModelData> grid) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				deselectAllAgent(panel, grid);
			}
		};
	}
	
	public static void deselectAllAgent(ContentPanel panel, Grid<DWCodesModelData> grid) {
		grid.getSelectionModel().deselectAll();
	}
	
	

}
