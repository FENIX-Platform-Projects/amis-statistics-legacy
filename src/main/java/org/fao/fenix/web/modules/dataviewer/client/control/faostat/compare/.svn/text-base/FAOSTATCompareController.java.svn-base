package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea.FAOSTATCompareByArea;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare.FAOSTATCompare;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class FAOSTATCompareController  {
	
	static FAOSTATCompare compareView;
    public static Integer tokensAsync = 0;	
	public static Integer tokensReached = 0;
	
	private static Timer timer;

	/** 
	 * 
	 * This method creates the compare panel
	 * 
	 */
	
   public static void callCompareView() {
		
		//initialize menu
	   compareView = new FAOSTATCompare(FAOSTATCurrentView.COMPARE_BY_AREA);
	   callCompareViewAgent(FAOSTATCurrentView.COMPARE_BY_AREA);

//	   compareView = new FAOSTATCompare(FAOSTATCurrentView.COMPARE_DATA);
//	   callCompareViewAgent(FAOSTATCurrentView.COMPARE_DATA);
	}
	
	
	public static void callCompareViewAgent(FAOSTATCurrentView view) {
		compareView.build(view);
	}
	
	public static Listener<ComponentEvent> callCompareView(final FAOSTATCurrentView view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				callCompareViewAgent(view);
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> compareAreas(final ContentPanel panel, final FormPanel form, final LayoutContainer column1, final LayoutContainer column2) {
		return new SelectionListener<ButtonEvent>() {
			@Override  
	        public void componentSelected(ButtonEvent ce) {   
				

				Component itemElementSelector = column2.getItemByItemId("ITEM_ELEMENT");
				
				DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
				
				
				if(itemElementSelector!=null){
					FieldSet fs = (FieldSet) itemElementSelector;
					qvo = (DWFAOSTATQueryVO)fs.getData("UPDATED_QVO");
					
					if(qvo==null){
						qvo = new DWFAOSTATQueryVO();
					}
				}	
				else
					qvo = new DWFAOSTATQueryVO();
				
				
				Component compareSelector = column1.getItemByItemId("COMPARE_SELECTOR");
				if(compareSelector!=null) {
					HashMap<String, String> codes = new HashMap<String, String>(); 
					CheckBoxListView<DWCodesModelData> list = (CheckBoxListView<DWCodesModelData>) compareSelector;
					DWFAOSTATQueryVO originalQVO = (DWFAOSTATQueryVO)list.getData("QVO");
								
					if(list.getChecked().size() > 0){
						getCodes(codes, list.getChecked(), originalQVO, FAOSTATDimensionConstant.dimensionCodingSystem.get("COUNTRIES"));
						qvo.setAreas(codes);
						
						DWFAOSTATQueryVO params = getQVOParameters(form, qvo);
						
						//panel.setStyleAttribute("padding", "20px");
						
						//BUILD VIEWS
						List<DWFAOSTATQueryVO> qvos = buildVOList(params);
						
						/** TODO: Change it with a constant based on the current view **/
						
						// GOOGLE stuff
						String googleCategory = FAOSTATCurrentView.COMPARE_BY_AREA.toString();
						String googleLabel = FAOSTATMainController.getGoogleLabel(codes);
						String googleAction = "Visualize compare by area";
						FAOSTATVisualizeByDomainController.buildViews(panel, qvos, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_COMPARE_WIDTH, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, googleCategory, googleLabel, googleAction);
						
						// checks the asyn calls
						if ( tokensAsync > 0)
							checkIfAllTheAsyncCallsAreReturned(panel, params);
						
					
					}
					else {
						FenixAlert.error(FAOSTATLanguage.print().areaSelection(), FAOSTATLanguage.print().pleaseSelectTheAreasYouWouldLikeToCompare());
					}
				} else 
					System.out.println("compareSelector IS NULL ");
				
	        }   
		};
	}
	

	
	public static SelectionListener<ButtonEvent> clearSelections(final CheckBoxListView<DWCodesModelData> view, final FAOSTATCompareByArea area) {
		return new SelectionListener<ButtonEvent>() {
			@Override  
	        public void componentSelected(ButtonEvent ce) {   
				 
				view.getSelectionModel().deselectAll();
				view.refresh();
				
				area.getMessage().setHtml("");
				
	        }   
		};
	}
	
	
	private static DWFAOSTATQueryVO getQVOParameters(final FormPanel form, DWFAOSTATQueryVO qvo) {
	  
	   HashMap<String, DWCodesModelData> timerange = new HashMap<String, DWCodesModelData>(); 
	   timerange.clear();
		List<String> selects = new ArrayList<String>();
		selects.add("A.AreaNameE");
		selects.add("D.Year");
		qvo.setSelects(selects);
		
		for(Field<?> field: form.getFields()) {
			DWFAOSTATQueryVO originalQVO = field.getData("QVO");
			
			DWCodesModelData model = (DWCodesModelData)field.getValue();
			List<DWCodesModelData> selectedModels = new ArrayList<DWCodesModelData>();
			selectedModels.add(model);

			HashMap<String, String> codes = new HashMap<String, String>(); 
			
						
			if(field.getId().contains(FAOSTATDimensionConstant.TIMERANGE.name())) {
				timerange.put(field.getId(), model);	
			}
			else {	
				
				FAOSTATDimensionConstant type = FAOSTATDimensionConstant.valueOf(field.getId());
				
				switch (type) {
				case AREAS_ALL: 	
					getCodes(codes, selectedModels, originalQVO, FAOSTATDimensionConstant.dimensionCodingSystem.get("COUNTRIES"));
					qvo.setAreas(codes);
					break;
				case GROUPS: 
					codes.put(model.getCode(), model.getLabel());
					qvo.setDomains(codes);
					break;
				case DOMAINS_FOR_GROUP: 
					codes.put(model.getCode(), model.getLabel());
					qvo.setDomains(codes);
					break;
				case ITEMS: 
					if(qvo.getItems().isEmpty()) {
						getCodes(codes, selectedModels, originalQVO, FAOSTATDimensionConstant.dimensionCodingSystem.get(type.name()));					
						qvo.setItems(codes);
					}
					
					break;
				case ELEMENTS_FOR_ITEM:
					if(qvo.getElements().isEmpty() ) {
						codes.put(model.getCode(), model.getLabel());
						qvo.setElements(codes);
					}
					break;
				}

			}
		}	
		
		if(!timerange.isEmpty())
			setYears(qvo, timerange);
			
		if(qvo.getYears().size() == 1){
		//	qvo.setResourceType(DataViewerBoxContent.CHART.toString());
		//	qvo.setOutputType(DataViewerBoxContent.TIMESERIE.toString());
		//}
		//else {
			//qvo.setResourceType(DataViewerBoxContent.CHART.toString());
			//qvo.setOutputType(DataViewerBoxContent.BAR_WITH_CATEGORIES.toString());
			
			if(qvo.getItems().size() > 1){
				qvo.getSelects().clear();
				qvo.getSelects().add("I.ItemName"+FAOSTATConstants.faostatLanguage);
				qvo.getSelects().add("A.AreaName"+FAOSTATConstants.faostatLanguage);
			}
			else if (qvo.getElements().size() > 1){
				qvo.getSelects().clear();
				qvo.getSelects().add("E.ElementName"+FAOSTATConstants.faostatLanguage);
				qvo.getSelects().add("A.AreaName"+FAOSTATConstants.faostatLanguage);
			}
		}
		
		
	
		
		
		
		List<String> orderBys = new ArrayList<String>();
		orderBys.add("A.AreaNameE");
		
		qvo.setOrderBys(orderBys);
			
		
		qvo.setTitle(createTitle(qvo));
		
		qvo.setIsCountryLevel(true); //allows country and regional selections in the checkbox
		qvo.setIsRegionLevel(true);
		   
		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		
		return qvo;
	}
	
	public static void getCodes(final HashMap<String, String> map, List<DWCodesModelData> selectedCodes, DWFAOSTATQueryVO originalQVO, String typeOfOutput) {
		HashMap<String, String> codes = new HashMap<String, String>();
		
		HashMap<String, String> aggregatedCodes = new HashMap<String, String>();
		System.out.println(" ^^^^^^^^^^^^^^^^^^^^^^^^^FAOSTAT COMAPARE getCodes ");
			
		for(DWCodesModelData code : selectedCodes) { 
			System.out.println(" ^^^^^^^^^^^^^^^^^^^^^^^^^SELECTED CODES "+code.getLabel());
			if ( code.isList() ) 
				aggregatedCodes.put(code.getCode(), code.getLabel());
			 else 
				 codes.put(code.getCode(), code.getLabel());
				
		}
		
		// adding codes
		map.putAll(codes);
		
		
		if ( !aggregatedCodes.isEmpty() ) {
			
			// an async call is required
			tokensAsync = tokensAsync +1;
			
			DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
			qvo.setOutput(DataViewerBoxContent.GET.toString());
			qvo.setTypeOfOutput(typeOfOutput);	
//			qvo.setOutputType(DataViewerBoxContent.GET.toString());
//			qvo.setResourceType(resourceType);		
			FAOSTATConstants.setFAOSTATDBSettings(qvo);

			qvo.getAggregationsFilter().putAll(aggregatedCodes);
			qvo.setDomains(originalQVO.getDomains());

			// TODO: get codes
			try {
				DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					
					@SuppressWarnings("unchecked")
					public void onSuccess(DWFAOSTATResultVO vo) {
						
						for(DWCodesModelData code : vo.getCodes()) {
							map.put(code.getCode(), code.getLabel());
						}

						System.out.println("MAP: " + map);
						tokensReached++;
					}
					
					public void onFailure(Throwable arg0) {
		
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
			System.out.println("aggregatedCodes: " + aggregatedCodes);
		}
		
	}
	
	private static void setYears(DWFAOSTATQueryVO qvo, HashMap<String, DWCodesModelData> timerange) {
		HashMap<String, String> selectedCodes = new HashMap<String, String>(); 

		Integer fromDate =  Integer.valueOf(timerange.get(FAOSTATDimensionConstant.TIMERANGE.name()+"_FROM").getCode());
		Integer toDate =  Integer.valueOf(timerange.get(FAOSTATDimensionConstant.TIMERANGE.name()+"_TO").getCode());

		System.out.println("from  year: " + fromDate);
		System.out.println("to  year: " + toDate);

		selectedCodes.clear();

		/** TODO: implement between **/
		// error message
		if ( fromDate.compareTo(toDate) > 0 ) {
			FenixAlert.error("Year Selection", ("Form Year is greater than the To Year, please re-select"));
		}
		else if ( fromDate.compareTo(toDate) == 0 ) {
			System.out.println("fromDate.compareTo(toDate) == 0");
			selectedCodes.put(fromDate.toString(), fromDate.toString());
		}
		else if ( fromDate.compareTo(toDate) < 0 ) {
			System.out.println("fromDate.compareTo(toDate)  < 0 ");

			while( fromDate.compareTo(toDate) <= 0 ) {
				System.out.println("dates: " + fromDate + " | " + toDate);

				selectedCodes.put(fromDate.toString(), fromDate.toString());

				fromDate++;
			}

		}

		qvo.setYears(selectedCodes);
	}

	private static String createTitle(DWFAOSTATQueryVO qvo){
		String title = FAOSTATLanguage.print().compareAreas() +":";
		
		/*if(!qvo.getAreas().isEmpty()) {
			int count = 0;
			for(String key: qvo.getAreas().keySet()) {
				
				title+=" "+ qvo.getAreas().get(key) + " ";
				
				if(count > 0)
					title+=", "+
					
				count++;
			}				
			
		}	*/
		if(!qvo.getItems().isEmpty()) {
			for(String key: qvo.getItems().keySet())
				title+=" "+ qvo.getItems().get(key) + " ";
		
		}
		
		if(!qvo.getElements().isEmpty()) {
			for(String key: qvo.getElements().keySet())
				title+=" "+ qvo.getElements().get(key) + " ";
		
		}
		
	   return title;
	}
	
	/*public static void checkExportIfAllTheAsyncCallsAreReturned(final ContentPanel panel, final DWFAOSTATQueryVO qvo) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
						System.out.println("start chart creation ...");
					FAOSTATChartController.addChart(panel, qvo, "1050", "400");
					timer.cancel();
				}
			}
		};
		timer.scheduleRepeating(10);
	}*/
	
	
	public static void checkIfAllTheAsyncCallsAreReturned(final ContentPanel panel, final DWFAOSTATQueryVO qvo) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				if ( tokensAsync != 0 ) {
					if ( tokensAsync == tokensReached ) {
						System.out.println("ok: ");
						//FAOSTATChartController.addChart(panel, qvo, "1050", "400");
						timer.cancel();
					}
				}
				
				// TODO: CHECK if it works
				if ( tokensAsync == 0 && tokensReached == 0) {
					System.out.println(" tokensFiltersAsync == 0 && tokensFiltersReached == 0");
				}
			}
		};
		timer.scheduleRepeating(1000);

	}
	
	
	private static List<DWFAOSTATQueryVO> buildVOList(DWFAOSTATQueryVO baseQvo) {
		List<DWFAOSTATQueryVO> vos = new ArrayList<DWFAOSTATQueryVO>();
		DWFAOSTATQueryVO updatedQvo = baseQvo;
		updatedQvo.setWidth("1060"); 
		updatedQvo.setHeight("300"); 

		if(baseQvo.getYears().size() > 1){
			updatedQvo.setOutput(DataViewerBoxContent.CHART.toString());
			updatedQvo.setTypeOfOutput(DataViewerBoxContent.TIMESERIE.toString());
//			updatedQvo.setResourceType(DataViewerBoxContent.CHART.toString());
//			updatedQvo.setOutputType(DataViewerBoxContent.TIMESERIE.toString());

			if(baseQvo.getItems().size() > 1){
				for(Map.Entry<String,String> entry: baseQvo.getItems().entrySet()){
					String itemCode = entry.getKey();						

					HashMap<String, String> newItemMap = new HashMap<String, String>();
					newItemMap.put(itemCode, entry.getValue());

					DWFAOSTATQueryVO itemQvo = createVO(updatedQvo);
					itemQvo.setTitle(newItemMap.get(itemCode));
					itemQvo.setItems(newItemMap);	
					itemQvo.setElements(updatedQvo.getElements());		

					/**DWFAOSTATQueryVO itemQvo = new DWFAOSTATQueryVO();

						FAOSTATConstants.setFAOSTATDBSettings(itemQvo);
						itemQvo.setResourceType(DataViewerBoxContent.CHART.toString());
						itemQvo.setOutputType(DataViewerBoxContent.TIMESERIE.toString());
						itemQvo.setDomains(updatedQvo.getDomains());
						itemQvo.setTitle(newItemMap.get(itemCode));
						itemQvo.setAreas(updatedQvo.getAreas());
						itemQvo.setYears(updatedQvo.getYears());
						itemQvo.setElements(updatedQvo.getElements());
						itemQvo.setOrderBys(updatedQvo.getOrderBys());
						itemQvo.setIsCountryLevel(updatedQvo.getIsCountryLevel()); 
						itemQvo.setIsRegionLevel(updatedQvo.getIsRegionLevel());
						itemQvo.setSelects(updatedQvo.getSelects());
						itemQvo.setWidth("420"); 
						itemQvo.setHeight("400"); 

						itemQvo.setItems(newItemMap);		**/				

					vos.add(itemQvo);

				}

			}
			else if (baseQvo.getElements().size() > 1){

				for(Map.Entry<String,String> entry: baseQvo.getElements().entrySet()){
					String elementCode = entry.getKey();		

					HashMap<String, String> newElementMap = new HashMap<String, String>();
					newElementMap.put(elementCode, entry.getValue());

					DWFAOSTATQueryVO elementQvo = createVO(updatedQvo);
					elementQvo.setTitle(newElementMap.get(elementCode));
					elementQvo.setItems(updatedQvo.getItems());
					elementQvo.setElements(newElementMap);		

					vos.add(elementQvo);

				}
			}
			else {
				vos.add(updatedQvo);
			}

		}
		else {
			updatedQvo.setOutput(DataViewerBoxContent.CHART.toString());
			updatedQvo.setTypeOfOutput(DataViewerBoxContent.BAR_WITH_CATEGORIES.toString());
//			updatedQvo.setResourceType(DataViewerBoxContent.CHART.toString());
//			updatedQvo.setOutputType(DataViewerBoxContent.BAR_WITH_CATEGORIES.toString());

			vos.add(updatedQvo);

		}

		System.out.println("buildVOList: VOs Size: " + vos.size());
		for(DWFAOSTATQueryVO v : vos){
			System.out.println("debug: " + v.getTitle() + v.getElements());
		}
		return vos;

	}
  
	private static DWFAOSTATQueryVO createVO(DWFAOSTATQueryVO updatedQvo) {
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		qvo.setOutput(DataViewerBoxContent.CHART.toString());
		qvo.setTypeOfOutput(DataViewerBoxContent.TIMESERIE.toString());
//		qvo.setResourceType(DataViewerBoxContent.CHART.toString());
//		qvo.setOutputType(DataViewerBoxContent.TIMESERIE.toString());
		qvo.setDomains(updatedQvo.getDomains());
		qvo.setAreas(updatedQvo.getAreas());
		qvo.setYears(updatedQvo.getYears());
		qvo.setOrderBys(updatedQvo.getOrderBys());
		qvo.setIsCountryLevel(updatedQvo.getIsCountryLevel()); 
		qvo.setIsRegionLevel(updatedQvo.getIsRegionLevel());
		qvo.setSelects(updatedQvo.getSelects());
		qvo.setWidth("520"); 
		qvo.setHeight("270"); 

		return qvo;
	}

	
}
