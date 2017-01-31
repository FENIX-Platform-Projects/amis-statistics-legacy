package org.fao.fenix.web.modules.amis.client.control.compare;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompare;
import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompareEntry;
import org.fao.fenix.web.modules.amis.client.view.compare.AMISCompareSelectorMaker;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.EmptyValuesPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.constants.AMISInterfaceConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISConstantsVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class AMISCompareController  {

	public static Integer tokensAsync = 0;
	public static Integer tokensReached = 0;

	private static Timer timer;
	
	private static List<Widget> emptyPanels = new ArrayList<Widget>();
	
	private static List<Widget> chartPanels = new ArrayList<Widget>();
	
	
	static AMISCompareEntry compareViewEntry;
	
	public static void callCompareView() {
		compareViewEntry = new AMISCompareEntry(AMISCurrentView.COMPARE_DATASOURCES);
		callCompareViewAgent(AMISCurrentView.COMPARE_DATASOURCES);
	}

	public static void callCompareSubView(AMISCurrentView subView) {
		compareViewEntry = new AMISCompareEntry(subView);
		callCompareViewAgent(subView);
	}

	
	public static Listener<ComponentEvent> callCompareView(final AMISCurrentView view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				callCompareViewAgent(view);
			}
		};
	}

	public static void callCompareViewAgent(AMISCurrentView view) {
		compareViewEntry.build(view);
	}

	/**
	 *
	 * This method creates the compare panel
	 *
	 */
			    
	public static SelectionListener<ButtonEvent> compare(final AMISCompare compare, final ContentPanel panel, final FormPanel form, final LayoutContainer column1, final LayoutContainer column2, final LayoutContainer column3, final LayoutContainer column4) {
	return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("unchecked")
			@Override  
	        public void componentSelected(ButtonEvent ce) {   
				System.out.println("compare START &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				final LoadingWindow loadingWindow = new LoadingWindow("Loading...", "Loading data... ", "");
				AMISQueryVO qvo = new AMISQueryVO();
										
				if(!verifyForm(form, column1, column2, column3)){
					FenixAlert.error("Selections", ("Please select at least 1 data source, country, product and element"));	
				    loadingWindow.destroyLoadingBox();
				}
				else {	
					
					//GOOGLE ANALYTICS TRACK EVENT: VIEWING DATA
					AMISController.trackUserEvent(AMISConstants.VIEWING.name());
	
					Component areaSelector = column1.getItemByItemId(AMISConstants.AMIS_COUNTRIES.toString());
					CheckBoxListView<AMISCodesModelData> list = (CheckBoxListView<AMISCodesModelData>) areaSelector;
					AMISQueryVO originalQVO = null;
					if(areaSelector!=null) {
						originalQVO = (AMISQueryVO)list.getData("QVO");
						HashMap<String, String> codes = new HashMap<String, String>(); 

						if(list.getChecked().size() > 0){
							getCodes(codes, list.getChecked(), originalQVO, AMISConstants.AMIS_COUNTRIES.name());
							qvo.setAreas(codes);
						}	
					}				
					//Products
					Component productSelector = column2.getItemByItemId(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString());
					list = (CheckBoxListView<AMISCodesModelData>) productSelector;


					if(productSelector!=null) {
						originalQVO = (AMISQueryVO)list.getData("QVO");
						HashMap<String, String> codes = new HashMap<String, String>(); 

						if(list.getChecked().size() > 0){
							getCodes(codes, list.getChecked(), originalQVO, AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.name());
							qvo.setItems(codes);
						} 

					}
					
					//Elements
					Component elementSelector = column3.getItemByItemId(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString());
					list = (CheckBoxListView<AMISCodesModelData>) elementSelector;

					
					if(elementSelector!=null) {
						originalQVO = (AMISQueryVO)list.getData("QVO");
						HashMap<String, String> codes = new HashMap<String, String>(); 

						if(list.getChecked().size() > 0){
							getCodes(codes, list.getChecked(), originalQVO, AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.name());
							qvo.setElements(codes);
						} 
					} 
					
					AMISQueryVO params = getQVOParameters(form, qvo, true);
					System.out.println(" endDateCombo.getSelectedText() years "+qvo.getYears());
					System.out.println(" endDateCombo.getSelectedText() SelectTypeChartOrTable "+qvo.getSelectTypeChartOrTable());
					
					String userRole =AMISConstants.AMIS_NOT_LOGGED_USER.toString();
					String userCountryCode = "";
					//For Registered Users
					if(FenixUser.hasAdminRole())
					{
						// Delete NATIONAL database, if the Logged In User's (Not AMIS Secretariat) country is not in the selected countries
						if(!AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT))
						{
							userRole =AMISConstants.AMIS_COUNTRY_USER.toString();
							//if(qvo.getDatabases().containsKey(AMISConstants.NATIONAL.toString()) && !qvo.getAreas().containsKey(CCBS.COUNTRY_CODES.get(0))) {
							//	qvo.getDatabases().remove(AMISConstants.NATIONAL.toString());

							//}
						}
						else
						{
							userRole =AMISConstants.AMIS_SECRETARIAT.toString();;
						}
						 userCountryCode = CCBS.COUNTRY_CODES.get(0);
					}
					AMISQueryVO test = qvo;
					 int lenght = test.getAreas().size();
				//	 System.out.println("Class: AMISCompareController Function: compare listener Text: AREAS lenght "+ lenght);
					 Set<String> keyset= test.getAreas().keySet();
					 
					 Iterator it = keyset.iterator();
//					 while(it.hasNext())
//					 {
//						 String key =(String)it.next();
//						 String value = test.getAreas().get(key);
//						 System.out.println("Class: AMISCompareController Function: compare listener Text: key "+ key+ " value "+ value);
//					 }
					 lenght = test.getDatabases().size();
					// System.out.println("Class: AMISCompareController Function: compare listener Text: DATASET lenght "+ lenght);
					 keyset= test.getDatabases().keySet();
					 
					 it = keyset.iterator();
//					 while(it.hasNext())
//					 {
//						 String key =(String)it.next();
//						 String value = test.getDatabases().get(key);
//						 System.out.println("Class: AMISCompareController Function: compare listener Text: key "+ key+ " value "+ value);
//					 }
					 lenght = test.getItems().size();
				//	 System.out.println("Class: AMISCompareController Function: compare listener Text: PRODUCTS lenght "+ lenght);
					 keyset= test.getItems().keySet();
					 it = keyset.iterator();
//					 while(it.hasNext())
//					 {
//						 String key =(String)it.next();
//						 String value = test.getItems().get(key);
//						 System.out.println("Class: AMISCompareController Function: compare listener Text: key "+ key+ " value "+ value);
//					 }
					 
					 lenght = test.getElements().size();
				//	 System.out.println("Class: AMISCompareController Function: compare listener Text: ELEMENTS lenght "+ lenght);
					 keyset= test.getElements().keySet();
					 it = keyset.iterator();
//					 while(it.hasNext())
//					 {
//						 String key =(String)it.next();
//						 String value = test.getElements().get(key);
//						 System.out.println("Class: AMISCompareController Function: compare listener Text: key "+ key+ " value "+ value);
//					 }
				//	 System.out.println("Class: AMISCompareController Function: compare listener Text: qvo.getSelectTypeChartOrTable() "+qvo.getSelectTypeChartOrTable());
					 if((qvo.getSelectTypeChartOrTable()!=null)&&(qvo.getSelectTypeChartOrTable().equals(AMISConstants.OLAP_TABLE.toString())))
					 {
						  // Show OLAP Table
					    viewByOLAPTable(loadingWindow, qvo, userRole, userCountryCode);
					 }
					 else
					 {
						 // Show Charts
						viewByChart(loadingWindow, qvo, params);
					 }
				
				}
				
				
				Component itemElementSelector = column2.getItemByItemId("ITEM_ELEMENT");

				if(itemElementSelector!=null){
					FieldSet fs = (FieldSet) itemElementSelector;
					qvo = (AMISQueryVO)fs.getData("UPDATED_QVO");

					if(qvo==null){
						qvo = new AMISQueryVO();
					}
					//System.out.println("ITEMS size: "+qvo.getItems().size()+ " ELEMENTS size: "+qvo.getElements().size());
				}	else
					qvo = new AMISQueryVO();

				
	        }

			private void viewByChart(LoadingWindow loadingWindow, AMISQueryVO qvo, AMISQueryVO params) {
				 	// need to track the number of permutations
					//need to track compare by					
			 		LinkedHashMap<String, Map<String, String>> allDimensions = new LinkedHashMap<String, Map<String, String>>();
					allDimensions.put(AMISConstants.AMIS_DATASETS.toString(), qvo.getDatabases());
					allDimensions.put(AMISConstants.AMIS_COUNTRIES.toString(), qvo.getAreas());
					allDimensions.put(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString(), qvo.getItems());
					allDimensions.put(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString(), qvo.getElements());
					
					List<String> dimensionType = new LinkedList<String>(); 
						 
					System.out.println("allDimensions size = "+ allDimensions.size());
					
					String compareByDimension  = qvo.getFirstSelectField();
					
					System.out.println("compareByDimension: "+compareByDimension);
					//LinkedHashMap<String, Map<String, String>> dimensionsMinusCompareByDimension = (LinkedHashMap<String, Map<String, String>>) allDimensions.clone();
					//LinkedHashMap<String, Map<String, String>> dimensionsMinusCompareByDimension = (LinkedHashMap<String, Map<String, String>>) allDimensions.clone();
					LinkedHashMap<String, Map<String, String>> dimensionsMinusCompareByDimension = new LinkedHashMap<String, Map<String, String>>();
					dimensionsMinusCompareByDimension.put(AMISConstants.AMIS_DATASETS.toString(), qvo.getDatabases());
					dimensionsMinusCompareByDimension.put(AMISConstants.AMIS_COUNTRIES.toString(), qvo.getAreas());
					dimensionsMinusCompareByDimension.put(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString(), qvo.getItems());
					dimensionsMinusCompareByDimension.put(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString(), qvo.getElements());
					
					for(String key: allDimensions.keySet()){
						if(key.equals(compareByDimension)) {
							dimensionsMinusCompareByDimension.remove(key);
						}
					}
					
					System.out.println("dimensionsMinusCompareByDimension size = "+ dimensionsMinusCompareByDimension.size());
					
			        int dimensionIndex = 0;
					
			        Object[][] dimensionsArray =  new Object[dimensionsMinusCompareByDimension.size()][] ;
			       
					for(String key: dimensionsMinusCompareByDimension.keySet()){
						int counter = 0;	
						Map<String, String> dimensionItemsMap = dimensionsMinusCompareByDimension.get(key);	
						String[] dimensionItemsArray= new String[dimensionItemsMap.size()];
						
						for(String itemCode: dimensionItemsMap.keySet()){
							dimensionItemsArray[counter]= itemCode;//+key;//dimensionItemsMap.get(item);
							counter ++;
						}
						
						dimensionsArray[dimensionIndex] = dimensionItemsArray;
						dimensionType.add(key);
						
						dimensionIndex ++;
					}
					System.out.println("dimensionsArray length: "+dimensionsArray.length);
					
					//check the dimensionsArray contents				
					 List<String> permutations = new ArrayList<String>();
					 getPermutations(permutations, "", dimensionsArray, 0);
					 System.out.println("permutations: "+ permutations.size());    
					 String delimiter = ":"; 

					 List<AMISQueryVO> vos = new ArrayList<AMISQueryVO>();
				
					
					 for(String p: permutations){
						 AMISQueryVO qvo1 = new AMISQueryVO();	
						 //WHAT's MISSING: FIRST SELECT and ORDER BYS - DB Specific
						 qvo1.setRunMaxDateQuery(false);
						
						 qvo1.setFirstSelectField(qvo.getFirstSelectField());
						 
						 AMISConstants co = AMISConstants.valueOf(qvo.getFirstSelectField());
						 switch(co){
						 case AMIS_DATASETS:  
							 // When FAOSTAT is selected:
							 if(qvo.getDatabases().size() == 2 && qvo.getDatabases().containsKey("FAOSTAT")){
								 qvo.getDatabases().clear();  //Remove all database comparisons
							 } else if(qvo.getDatabases().size() > 2 && qvo.getDatabases().containsKey("FAOSTAT")){
								 qvo.getDatabases().remove("FAOSTAT");  //Remove FAOSTAT only from database comparisons
							 }
							 
							 qvo1.getSelects().clear();
							 qvo1.getSelects().add("D.database");
							 qvo1.getSelects().addAll(qvo.getSelects());		
							 qvo1.getGroupBys().clear();
							 qvo1.getGroupBys().add("D.database");
							 qvo1.getGroupBys().addAll(qvo.getGroupBys());		
							 List<String> orderBys = new ArrayList<String>();
							 orderBys.add("year"); // order by year only otherwise time series is skewed if dates are missing for one of the selected datasources
						     qvo1.setOrderBys(orderBys);	
						     qvo1.getTableHeaders().clear();
							 qvo1.getTableHeaders().add("Data Source");
							 qvo1.getTableHeaders().addAll(qvo.getTableHeaders());	
							 break;
						 case AMIS_COUNTRIES: 
							 qvo1.getSelects().clear();
							 qvo1.getSelects().add("D.region_name");
							 qvo1.getSelects().addAll(qvo.getSelects());	
							 qvo1.getGroupBys().clear();
							 qvo1.getGroupBys().add("D.region_name");
							 qvo1.getGroupBys().addAll(qvo.getGroupBys());	
							 orderBys = new ArrayList<String>();
							 orderBys.add("year"); // order by year only otherwise time series is skewed if dates are missing for one of the selected region_names
						     qvo1.setOrderBys(orderBys);	
						     qvo1.getTableHeaders().clear();
							 qvo1.getTableHeaders().add("Countries/Regions");
							 qvo1.getTableHeaders().addAll(qvo.getTableHeaders());	
							 break;
					     case AMIS_GET_PRODUCTS_BY_ELEMENT: 
							 qvo1.getSelects().clear();
							 qvo1.getSelects().add("D.product_name");
							 qvo1.getSelects().addAll(qvo.getSelects());	
							 qvo1.getGroupBys().clear();
							 qvo1.getGroupBys().add("D.product_name");
							 qvo1.getGroupBys().addAll(qvo.getGroupBys());	
							 orderBys = new ArrayList<String>();
							 orderBys.add("year"); // order by year only otherwise time series is skewed if dates are missing for one of the selected product_name
						     qvo1.setOrderBys(orderBys);	
						     qvo1.getTableHeaders().clear();
							 qvo1.getTableHeaders().add("Products");
							 qvo1.getTableHeaders().addAll(qvo.getTableHeaders());	
							 break;
						 case AMIS_GET_ELEMENTS_BY_PRODUCT: 
							 qvo1.getSelects().clear();
							 qvo1.getSelects().add("D.element_name");
							 qvo1.getSelects().addAll(qvo.getSelects());	
							 qvo1.getGroupBys().clear();
							 qvo1.getGroupBys().add("D.element_name");
							 qvo1.getGroupBys().addAll(qvo.getGroupBys());	
							 orderBys = new ArrayList<String>();
							 orderBys.add("year"); // order by D.year only otherwise time series is skewed if dates are missing for one of the selected D.element_names
							 qvo1.setOrderBys(orderBys);	
						     qvo1.getTableHeaders().clear();
							 qvo1.getTableHeaders().add("Elements");
							 qvo1.getTableHeaders().addAll(qvo.getTableHeaders());
							 break;
						 }     
						 
						 qvo1.setSortingOrder("ASC");
						 qvo1.setDatabases(qvo.getDatabases());
						
						 qvo1.setAreas(qvo.getAreas());
						 qvo1.setItems(qvo.getItems());
						 qvo1.setElements(qvo.getElements());
						 qvo1.setYears(qvo.getYears());
						 qvo1.setIsRegionLevel(qvo.getIsRegionLevel());
						 qvo1.setIsCountryLevel(qvo.getIsCountryLevel());
						 qvo1.setFroms(qvo.getFroms());
						
						 if(qvo.getYears().size() > 1){
							 qvo1.setOutput(AMISConstants.CHART.toString());
							 qvo1.setTypeOfOutput(AMISConstants.TIMESERIES.toString());
						 }
						 else {
							 qvo1.setOutput(AMISConstants.CHART.toString());
							 qvo1.setTypeOfOutput(AMISConstants.BAR_WITH_CATEGORIES.toString());
						 }

						 String[] permutationItems;    

						 permutationItems = p.split(delimiter); 
						 
						 String title = "";
						
						 for(int k =0; k < permutationItems.length ; k++)   { 	
							 AMISConstants c = AMISConstants.valueOf(dimensionType.get(k));
							
							 switch(c){
							 case AMIS_DATASETS:  
								 Map<String, String> databases = new HashMap<String, String>();
								 databases.put(permutationItems[k], qvo.getDatabases().get(permutationItems[k]));
								 qvo1.setDatabases(databases);
								 title += qvo.getDatabases().get(permutationItems[k]) + " ";
								 System.out.println("========  case AMIS_DATASETS qvo1 = "+title);
								 break;
							 case AMIS_COUNTRIES: 
								 Map<String, String> areas = new HashMap<String, String>();
								 areas.put(permutationItems[k], qvo.getAreas().get(permutationItems[k]));
								 qvo1.setAreas(areas);
								 title += qvo.getAreas().get(permutationItems[k]) + " ";
								 System.out.println("========  case AMIS_COUNTRIES qvo1 = "+title);
								 break;
							 case AMIS_GET_PRODUCTS_BY_ELEMENT: 
								 Map<String, String> items = new HashMap<String, String>();
								 items.put(permutationItems[k], qvo.getItems().get(permutationItems[k]));
								 qvo1.setItems(items);
								 title += qvo.getItems().get(permutationItems[k]) + " ";
								 System.out.println("========  case AMIS_GET_PRODUCTS_BY_ELEMENT qvo1 = "+title);
								 break;
							 case AMIS_GET_ELEMENTS_BY_PRODUCT: 
								 Map<String, String> elements = new HashMap<String, String>();
								 elements.put(permutationItems[k], qvo.getElements().get(permutationItems[k]));
								 qvo1.setElements(elements);
								 title += qvo.getElements().get(permutationItems[k]) + " ";
								 System.out.println("========  case AMIS_GET_ELEMENTS_BY_PRODUCT qvo1 = "+title);
								 break;
							 }   
						 }
						 
						 qvo1.setTitle(title);
						 
						 System.out.println("======== START qvo1 = "+qvo1.getTitle() + " ---- [" +qvo.getDatabases()+"] ");
							
						 //For Registered Users
						 //if(FenixUser.hasAdminRole())
						 //{
							// Set the country code, if the Logged In User's  is Not AMIS Secretariat							
							 //if(!AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT))
							 //{
							//	 HashMap<String, String> nationalDSFilters = new HashMap<String, String>();
							//	 nationalDSFilters.put("region_code", CCBS.COUNTRY_CODES.get(0));
							//	 qvo1.setNationalDataSourceFilters(nationalDSFilters);
							// }

						// }
					
						 vos.add(qvo1);
					 }
			

					 System.out.println(" VOS created "+vos.size());
					 panel.setStyleAttribute("padding-left", "10px");
				
					 
					
					 if(qvo.getDatabases().size() > 0) {
						 AMISController.buildViews(compare, panel, vos, "", loadingWindow);
					 }	 
					 // qvo.getDatabases().size()== 0 when FAOSTAT and another data source has been selected (when Comparing by data sources)
					/* else if(qvo.getDatabases().size()== 0) {
						    loadingWindow.destroyLoadingBox();
						    ContentPanel emptyPanelHolder = new ContentPanel();
						    emptyPanelHolder.setHeaderVisible(false);
						    emptyPanelHolder.setBorders(true);
						    emptyPanelHolder.add(EmptyValuesPanel.build(AMISInterfaceConstants.CHART_WIDTH, AMISInterfaceConstants.CHART_HEIGHT	, "The selected data sources are not comparable as they have different periodicities. FAOSTAT data is on a calendar year basis whereas the other data sources are based on a marketing year."));
						    emptyPanelHolder.setWidth("1030px");
							
						    panel.add(emptyPanelHolder);
							panel.layout();
						
					 }*/

					 

						// checks the asyn calls
						if ( tokensAsync > 0)
							checkIfAllTheAsyncCallsAreReturned(panel, params);
					
			}

			private void viewByOLAPTable(final LoadingWindow loadingWindow, AMISQueryVO qvo, String userRole, String userCountryCode) {
				 //Show Olap Table
				//userRole can be "Secretariat", "Country User" and "Not Logged User"
			//	 System.out.println("Class: AMISCompareController Function: compare listener Text: Show Olap Table");
				 AMISServiceEntry.getInstance().createOlapTable(qvo, userRole, userCountryCode, new AsyncCallback<List<HashMap<String, String>>>() {

					@Override
					public void onFailure(Throwable arg0) {
						 System.out.println("Class: AMISCompareController Function: compare listener Text: Show Olap Table ON FAILURE");
						if(loadingWindow!=null)
						{
							loadingWindow.destroyLoadingBox();
						}
					}

					@Override
					public void onSuccess(List<HashMap<String, String>> list) {
						 System.out.println("Class: AMISCompareController Function: compare listener Text: Show Olap Table ON SUCCESS ");
						 if((list==null)||(list.size()==0))
						 {
							System.out.println("Class: AMISCompareController Function: compare listener Text: NO ELEMENTS HAVE BEEN FOUND!!!!");
							panel.removeAll();
							RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").clear();
							ContentPanel cp = new EmptyValuesPanel().build(AMISInterfaceConstants.CHART_WIDTH, AMISInterfaceConstants.CHART_HEIGHT);
							cp.setBorders(false);
							cp.setWidth("1030px");
							cp.setHeight("43px");
							//cp.setStyleAttribute("padding", "10px");
							ContentPanel extCp = new ContentPanel();
							//extCp.setStyleAttribute("padding", "10px");
							extCp.setHeaderVisible(false);
							extCp.setBorders(true);
							extCp.setBodyBorder(false);
							extCp.setWidth("1030px");
							extCp.setHeight("43px");
							extCp.add(cp);
							RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").add(extCp);
							if(AMISCompare.isNoElementsOlapIsNull())
							{										
								AMISCompare.setNoElementsOlapIsNull(false);
							}				
							RootPanel.get("OLAP_IFRAME").setVisible(false);										
							RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(true);								 
						 }
						 else
						 {
							 System.out.println("Elements have been founded ");
							List<AMISQueryVO> notFoundElements = new ArrayList<AMISQueryVO>();
							ContentPanel extCp = new ContentPanel();
							//extCp.setStyleAttribute("padding", "10px");
							extCp.setWidth("1030px");
							extCp.setHeaderVisible(false);
							extCp.setBorders(true);
							extCp.setBodyBorder(false);
							RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
							JSONArray olapJsElements = new JSONArray();
							StringBuffer jsonString = new StringBuffer("[");
							
							int listSize = list.size();
							System.out.println("Class: AMISCompareController Function: compare listener Text: Show Olap Table Text: list.size = "+ list.size());
							int i=0;
							System.out.println("Class: AMISCompareController Function: compare listener Text: Start creation of the object for olap");
							for(HashMap<String, String> hm: list)
							 {
								 if(hm.get("FOUND_ELEMENT").equals("FALSE"))
								 {
									 //The element has NOT been found
									 AMISQueryVO notFoundElement = new AMISQueryVO();
									 String title= hm.get("PRODUCT_NAME")+" "+ hm.get("COUNTRY_NAME")+" "+hm.get("ELEMENT_NAME");
									 notFoundElement.setTitle(title);
									 notFoundElements.add(notFoundElement);
									 continue;
								 }
								 jsonString.append("[");
								 String year = (String)hm.get("YEAR");
								 if(year !=null)
								 {
									 int index = year.indexOf("-01-01");
									 if(index != -1)
									 {
										 year = year.substring(0, index);
									 }
								 }
								 //Rename the database for having the same labels of the interface
								 
								 String database = hm.get("DATABASE");
								 if(database!=null)
								 {
									if(database.equals("CBS"))
									{
//										hm.put("DATABASE", "FAO-CBS");
                                        hm.put("DATABASE", "FAO-AMIS");
									}
									if(database.equals("PSD"))
									{
										hm.put("DATABASE", "USDA-PSD");
									}
								 }
								 if((hm.get("FLAG")!=null)&&(hm.get("FLAG").equals("null")))
									 hm.put("FLAG", "");
								 //Start json object test
								// int iJsonObject=0;
//										 JSONObject obj = new JSONObject();
//										 obj.put("DATABASE",new JSONString(hm.get("DATABASE")));
//										 obj.put("COUNTRY_CODE",new JSONString(hm.get("COUNTRY_CODE")));
//										 obj.put("COUNTRY_NAME",new JSONString(hm.get("COUNTRY_NAME")));
//										 obj.put("PRODUCT_CODE",new JSONString(hm.get("PRODUCT_CODE")));
//										 obj.put("PRODUCT_NAME",new JSONString(hm.get("PRODUCT_NAME")));
//										 obj.put("ELEMENT_CODE",new JSONString(hm.get("ELEMENT_CODE")));
//										 obj.put("ELEMENT_NAME",new JSONString(hm.get("ELEMENT_NAME")));
//										 obj.put("YEAR",new JSONString(year));
//										 obj.put("VALUE_TYPE",new JSONString(hm.get("VALUE_TYPE")));
//										 obj.put("UNIT",new JSONString(hm.get("UNIT")));
//										 obj.put("MONTH",new JSONString(hm.get("MONTH")));
//										 obj.put("FLAG",new JSONString(hm.get("FLAG")));
//										 obj.put("VALUE",new JSONString(hm.get("VALUE")));	
//										// olapInit(obj);
//										 olapJsElements.set(i, obj);
								//End json object test
								 
								 //jsonString.append("[["Argentina<span class=cs>3</span>","total cereal<span class=cs>5</span>","Autilization<span class=cs>555</span>","FAO-CBS<span class=cs>FAO-CBS</span>","Tonnes","",1500], ["Argentina<span class=cs>3</span>","total cereal<span class=cs>5</span>","Autilization<span class=cs>555</span>","FAO-CBS<span class=cs>FAO-CBS</span>","Tonnes","",1500]]");
								//UNIT, FLAG, VALUE
								// ["Argentina<span class=cs>3</span>","total cereal<span class=cs>5</span>","Autilization<span class=cs>555</span>","FAO-CBS<span class=cs>FAO-CBS</span>","Tonnes","",1500] 
//										 jsonString.append("\""+hm.get("DATABASE")+"<span class=cs>"+hm.get("DATABASE")+"</span>\",\""+hm.get("COUNTRY_NAME")+"<span class=cs>"+hm.get("COUNTRY_CODE")+"</span>\",\""+hm.get("PRODUCT_NAME")+"<span class=cs>"+hm.get("PRODUCT_CODE")+"</span>\",\""+hm.get("ELEMENT_NAME")+"<span class=cs>"+hm.get("ELEMENT_CODE")+"</span>\",\""+year+"\",\""+hm.get("UNIT")+"\",\""+hm.get("FLAG")+"\",\""+hm.get("VALUE")+"\"");
								 jsonString.append("\""+hm.get("DATABASE")+"\",\""+hm.get("COUNTRY_NAME")+"<span class=cs> ["+hm.get("COUNTRY_CODE")+"]</span>\",\""+hm.get("PRODUCT_NAME")+"<span class=cs> ["+hm.get("PRODUCT_CODE")+"]</span>\",\""+hm.get("ELEMENT_NAME")+"<span class=cs> ["+hm.get("ELEMENT_CODE")+"]</span>\",\""+year+"\",\""+hm.get("UNIT")+"\",\""+hm.get("FLAG")+"\",\""+hm.get("VALUE")+"\""); 
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: Element i= "+ i);
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: DATABASE "+ hm.get("DATABASE"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: COUNTRY_CODE "+ hm.get("COUNTRY_CODE"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: COUNTRY_NAME "+ hm.get("COUNTRY_NAME"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: PRODUCT_CODE "+ hm.get("PRODUCT_CODE"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: PRODUCT_NAME "+ hm.get("PRODUCT_NAME"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: ELEMENT_CODE "+ hm.get("ELEMENT_CODE"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: ELEMENT_NAME "+ hm.get("ELEMENT_NAME"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: YEAR "+ year);
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: VALUE_TYPE "+ hm.get("VALUE_TYPE"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: UNIT "+ hm.get("UNIT"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: MONTH "+ hm.get("MONTH"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: VALUE "+ hm.get("VALUE"));
//									 System.out.println("Class: AMISCompareController Function: compare listener Text: FLAG "+ hm.get("FLAG"));
							
								 jsonString.append("]");
								 if(i!=listSize-1)
								 {
									 jsonString.append(", ");
								 }
							 i++;//Counts the found elements
							 }
							 jsonString.append("]");
						
							panel.removeAll();
							System.out.println("Class: AMISCompareController Function: compare listener Text: End creation of the object for olap");
							olapInit(jsonString);
							RootPanel.get("OLAP_IFRAME").setVisible(true);
							RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").clear();
							for(AMISQueryVO el: notFoundElements)
							{
								ContentPanel empty = new EmptyValuesPanel().build(AMISInterfaceConstants.CHART_WIDTH, AMISInterfaceConstants.CHART_HEIGHT ,el);
								empty.setBorders(false);
								empty.setWidth("1030px");
								empty.setStyleAttribute("padding", "10px");
								extCp.add(empty);
							}
							if(notFoundElements.size()>0)
							{
								RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").add(FormattingUtils.addVSpace(5));
								RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").add(extCp);
								RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(true);
							}
						 }	
						if(loadingWindow!=null)
						{
							loadingWindow.destroyLoadingBox();
						}
					}				
				});
				
			}   
		};
	}
	
//	$wnd.OLAP.window.data=jsonString;
	public static native void olapInit(StringBuffer/*JSONObject*/ jsonString) /*-{
     	 
    //alert(jsonString);
    $wnd.document.getElementById("amisOLAP").style.display="block";
   	//alert('starts native 1');
   	// alert($wnd.OLAP);
   	//alert($wnd.OLAP == null);
   	$wnd.OLAP.window.data=  eval('(' + jsonString + ')');
   	//alert('Processing has been completated');
   	//alert('starts native 2'+$wnd.OLAP.window.data);
   	$wnd.OLAP.window.row = [0,1];
	$wnd.OLAP.window.col = [2,3,4];
	$wnd.OLAP.window.agg = [];
	$wnd.OLAP.window.dataIndex=0;
	$wnd.OLAP.window.option = { showChart: 0, headingBefore: 0, headingAfter: 0, nbDec:2,headingOutside: 1, showUnit: 0,showFlag:1 ,nestedby:1,subtotals:0,totals:0,calcTotal:0};
 	var arr=$wnd.OLAP.document.getElementById('nbDec').getElementsByTagName('option');
	for(var k=0;k<arr.length;k++){if(arr[k].value==2){arr[k].selected=true;}}

 	$wnd.OLAP.document.getElementById('shownestedby').checked=true;
  	$wnd.OLAP.document.getElementById('showCode').checked=false;
 	$wnd.OLAP.window.showCode();
 
 	$wnd.OLAP.window.init2();
	// alert('starts native 3');
 	}-*/;
	
	private static void getPermutations(List<String> permutations, String s, Object[][] arrs, int k) {
	  	
		if (k == arrs.length) {
			// System.out.println("s "+ s);    
			 permutations.add(s);	  
		   } else {
	        	for (Object o : arrs[k]) {
	        		if(s.equals(""))
	        			getPermutations(permutations, s + o, arrs, k + 1);
	        		else
	        			getPermutations(permutations, s +":"+ o, arrs, k + 1);
	            }
	        }
	   }

	
	public static SelectionListener<ButtonEvent> compareAreas(final ContentPanel panel, final FormPanel form, final LayoutContainer column1) {
		return new SelectionListener<ButtonEvent>() {
			@Override
	        public void componentSelected(ButtonEvent ce) {


				AMISQueryVO qvo = new AMISQueryVO();


				Component compareSelector = column1.getItemByItemId("COMPARE_SELECTOR");
				if(compareSelector!=null) {
					HashMap<String, String> codes = new HashMap<String, String>();
					CheckBoxListView<AMISCodesModelData> list = (CheckBoxListView<AMISCodesModelData>) compareSelector;
					AMISQueryVO originalQVO = (AMISQueryVO)list.getData("QVO");

					if(list.getChecked().size() > 0){
						getCodes(codes, list.getChecked(), originalQVO, AMISConstants.AMIS_COUNTRIES.toString());
						qvo.setAreas(codes);

						AMISQueryVO params = getQVOParameters(form, qvo, false);

						//final LoadingWindow loadingWindow = new LoadingWindow("Compare Areas", "Please wait..", "loading");
						//loadingWindow.showLoadingBox();

						panel.setStyleAttribute("padding", "20px");



						if ( tokensAsync > 0)
							checkExportIfAllTheAsyncCallsAreReturned(panel, params);
						else {
							AMISController.addChart(panel, params, "1050", "400");

						}

						panel.getLayout().layout();
					}
					else {
						FenixAlert.error("Areas Selection", ("Please select the areas you would like to compare"));
					}
				} else
					System.out.println("compareSelector IS NULL ");

	        }
		};
	}


	public static SelectionListener<ButtonEvent> clearSelections(final CheckBoxListView<AMISCodesModelData> view, final AMISCompare compare) {
		return new SelectionListener<ButtonEvent>() {
			@Override
	        public void componentSelected(ButtonEvent ce) {

				
				view.getSelectionModel().deselectAll();
			    view.refresh();
			    
				//compare.getMessage().setHtml("");

	        }
		};
	}
	
	public static SelectionListener<ButtonEvent> reset(final CheckBoxListView<AMISCodesModelData> productsx, final CheckBoxListView<AMISCodesModelData> elementsx, final AMISCompare compare, final LayoutContainer elementContainer, final LayoutContainer productContainer, final AMISCompareSelectorMaker selectorMaker) {
		return new SelectionListener<ButtonEvent>() {
			@Override
	        public void componentSelected(ButtonEvent ce) {

				System.out.println("creset START ");
				
				//products.getSelectionModel().deselectAll();
				//products.refresh();
			    
				//for (AMISCodesModelData model: products.getChecked()){
				//	products.setChecked(model, false);
				//}
					 
				
                Component productComponent = compare.getColumn2().getItemByItemId(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString());
				
				//if(productComponent!=null){
				CheckBoxListView<AMISCodesModelData> products = (CheckBoxListView<AMISCodesModelData>) productComponent;
			
				products.getSelectionModel().deselectAll();
				products.refresh();
				//}
				
				Component elementComponent = compare.getColumn3().getItemByItemId(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString());
				
				//if(elementComponent!=null){
				CheckBoxListView<AMISCodesModelData> elements = (CheckBoxListView<AMISCodesModelData>) elementComponent;
			
				elements.getSelectionModel().deselectAll();
				elements.refresh();
				//}
				
				//if(elements.isRendered()){
				//	elements.getSelectionModel().deselectAll();
				//	elements.refresh();
				    
					//for (AMISCodesModelData model: elements.getChecked()){
					//	elements.setChecked(model, false);
					//}
				//}
				
				System.out.println("products.getChecked() size  "+products.getChecked().size());
				System.out.println("elements.getChecked() size  "+elements.getChecked().size());
				
				
				AMISCompareController.rebuildProductSelector(compare, elements, productContainer, selectorMaker);	
				AMISCompareController.rebuildElementSelector(compare, products, elementContainer, selectorMaker);

	        }
		};
	}
	
	public static SelectionListener<ButtonEvent> clearProductSelections(final CheckBoxListView<AMISCodesModelData> view, final AMISCompare compare, final LayoutContainer elementContainer, final Html elementNote, final AMISCompareSelectorMaker selectorMaker) {
		return new SelectionListener<ButtonEvent>() {
			@Override
	        public void componentSelected(ButtonEvent ce) {

				System.out.println("clearProductSelections START ");
				view.getSelectionModel().deselectAll();
				
				for (AMISCodesModelData model: view.getChecked()){
					view.setChecked(model, false);
				}
				view.refresh();
				 
				System.out.println("products.getChecked() size  "+view.getChecked().size());
				
				rebuildElementSelector(compare, view, elementContainer, selectorMaker);

	        }
		};
	}


//	private static AMISQueryVO getQVOParameters(final FormPanel form, AMISQueryVO qvo) {
//
//	   HashMap<String, AMISCodesModelData> timerange = new HashMap<String, AMISCodesModelData>();
//	   timerange.clear();
//	   
//	   String selectType = null;
//	   
//
//	   for(Field<?> field: form.getFields()) {
//			
//			if(field instanceof CheckBoxGroup) {
//				HashMap<String, String> databases = new HashMap<String, String>();	
//				
//				CheckBoxGroup group = (CheckBoxGroup)field;
//				List<CheckBox> boxes = group.getValues();
//				
//				for(CheckBox box: boxes){
//					if(box.getValue()) {
//						databases.put(box.getId(), box.getId());
//					}
//				}
//				
//				if(!databases.isEmpty())
//					qvo.setDatabases(databases);
//				
//									
//			}
//			else if(field instanceof RadioGroup) {
//				RadioGroup group = (RadioGroup)field;
//				Radio radio = group.getValue();
//				
//				selectType = radio.getId();
//				
//				System.out.println("radio "+radio.getId() + " value  "+ radio.getValue());
//				
//			}
//			else {
//				AMISCodesModelData model = (AMISCodesModelData)field.getValue();
//				
//				if(field.getId().contains(AMISConstants.TIMERANGE.name())) {
//					timerange.put(field.getId(), model);
//				}
//				
//				
//			}
//		}
//	   
//		if(!timerange.isEmpty())
//			setYears2(qvo, timerange);//setYears(qvo, timerange);
//
//		if(qvo.getYears().size() > 1){
//			qvo.setOutput(AMISConstants.CHART.toString());
//			qvo.setTypeOfOutput(AMISConstants.TIMESERIES.toString());
//		}
//		else {
//			qvo.setOutput(AMISConstants.CHART.toString());
//			qvo.setTypeOfOutput(AMISConstants.BAR_WITH_CATEGORIES.toString());
//		}
//
//		//qvo.setSelects should be set based on this, in conjunction with getSelects (D.Year = FAOSTAT, Year = CCBS) ... field name is Dataset dependant 
//		if(selectType!=null)
//			qvo.setFirstSelectField(selectType);
//
//	    List<String> selects = new ArrayList<String>();
//		selects.add("ROUND(CAST(extract(year from D.year) AS NUMERIC), 0) as year");
//		selects.add("D.value");
//
//		qvo.setSelects(selects);
//		
//		
//		List<String> tableHeaders = new ArrayList<String>();
//		tableHeaders.add("Year");
//		tableHeaders.add("Value");
//        qvo.setTableHeaders(tableHeaders);
//			
//		List<String> groupBys = new ArrayList<String>();
//		groupBys.add("ROUND(CAST(extract(year from D.year) AS NUMERIC), 0)");
//		groupBys.add("D.value");
//		groupBys.add("D.year");
//
//		qvo.setGroupBys(groupBys);
//			
//		
//
//	    List<String> froms = new ArrayList<String>();
//		//froms.add(AMISController.getTableNameForDataset(AMISCurrentDatasetView.AMIS)+ " D" );
//		froms.add("amis_all_datasources D" ); // combined data sources view
//		froms.add(AMISController.getTableNameForCodingSystem(AMISCurrentDatasetView.AMIS.toString()+"_ELEMENTS")+ " E");
//		qvo.setFroms(froms);
//
//		
//		//order by should be set based on qvo.getFirstSelectField ... field name is Dataset dependant 
//		
//		/**List<String> orderBys = new ArrayList<String>();
//		orderBys.add("A.AreaNameE");
//        qvo.setOrderBys(orderBys);**/
//
//		
//		qvo.setTitle(createTitle(qvo));
//
//		qvo.setIsCountryLevel(true); //allows country and regional selections in the checkbox
//		qvo.setIsRegionLevel(true);
//
//
//		//AMISConstants.setFAOSTATDBSettings(qvo);
//
//
//		return qvo;
//	}

	private static AMISQueryVO getQVOParameters(final FormPanel form, AMISQueryVO qvo, boolean isCompare) {

		   HashMap<String, AMISCodesModelData> timerange = new HashMap<String, AMISCodesModelData>();
		   timerange.clear();
		   
		   String selectType = null;
		   String selectTypeChartOrTable = null;

		   for(Field<?> field: form.getFields()) {
				
				if(field instanceof CheckBoxGroup) {
					HashMap<String, String> databases = new HashMap<String, String>();	
					
					CheckBoxGroup group = (CheckBoxGroup)field;
					List<CheckBox> boxes = group.getValues();
					
					for(CheckBox box: boxes){
						if(box.getValue()) {
							databases.put(box.getId(), box.getId());
						}
					}
					
					if(!databases.isEmpty())
						qvo.setDatabases(databases);
					
										
				}
				else if(field instanceof RadioGroup) {
					RadioGroup group = (RadioGroup)field;
					Radio radio = group.getValue();
					if(radio.getId().equals(AMISConstants.AMIS_CHART.toString())||(radio.getId().equals(AMISConstants.OLAP_TABLE.toString())))
					{
						selectTypeChartOrTable = radio.getId();
					}
					else
					{
						selectType = radio.getId();
					}
					
					System.out.println("radioX "+radio.getId() + " value  "+ radio.getValue());
					
				}
				else {
					AMISCodesModelData model = (AMISCodesModelData)field.getValue();
					
					if(field.getId().contains(AMISConstants.TIMERANGE.name())) {
						timerange.put(field.getId(), model);
					}
					
					
				}
			}
		   
			if(!timerange.isEmpty())
				setSplitYears(qvo, timerange);//setYears(qvo, timerange);
                //setYears2(qvo, timerange);//setYears(qvo, timerange);

			if(qvo.getYears().size() > 1){
				qvo.setOutput(AMISConstants.CHART.toString());
				qvo.setTypeOfOutput(AMISConstants.TIMESERIES.toString());
			}
			else {
				qvo.setOutput(AMISConstants.CHART.toString());
				qvo.setTypeOfOutput(AMISConstants.BAR_WITH_CATEGORIES.toString());
			}

			//qvo.setSelects should be set based on this, in conjunction with getSelects (D.Year = FAOSTAT, Year = CCBS) ... field name is Dataset dependant 
			if(selectType!=null)
				qvo.setFirstSelectField(selectType);
			
			if(selectTypeChartOrTable!=null)
			{
				qvo.setSelectTypeChartOrTable(selectTypeChartOrTable);
			}

		    List<String> selects = new ArrayList<String>();
			//selects.add("ROUND(CAST(extract(year from D.year) AS NUMERIC), 0) as year");
			selects.add("D.year_label as year");
			selects.add("D.value");

			qvo.setSelects(selects);
			
			
			List<String> tableHeaders = new ArrayList<String>();
			tableHeaders.add("Year");
			tableHeaders.add("Value");
	        qvo.setTableHeaders(tableHeaders);
				
			List<String> groupBys = new ArrayList<String>();
			//groupBys.add("ROUND(CAST(extract(year from D.year) AS NUMERIC), 0)");
			groupBys.add("D.year_label");
            if((qvo.getSelectTypeChartOrTable()!=null)&&(!qvo.getSelectTypeChartOrTable().equals(AMISConstants.AMIS_CHART.toString())))
            {
                groupBys.add("D.year");
            }
			groupBys.add("D.value");
			
			//groupBys.add("D.year");

			qvo.setGroupBys(groupBys);
				
			

		    List<String> froms = new ArrayList<String>();
			//froms.add(AMISController.getTableNameForDataset(AMISCurrentDatasetView.AMIS)+ " D" );
			//froms.add("amis_all_datasources D" ); // combined data sources view
		    if(isCompare)
		    {
		    	froms.add("amis_all_datasources " );
		    }
		    else
		    {
		    	froms.add("amis_all_datasources D " );
		    }
			froms.add(AMISController.getTableNameForCodingSystem(AMISCurrentDatasetView.AMIS.toString()+"_ELEMENTS")+ " E");
			qvo.setFroms(froms);

			
			//order by should be set based on qvo.getFirstSelectField ... field name is Dataset dependant 
			
			/**List<String> orderBys = new ArrayList<String>();
			orderBys.add("A.AreaNameE");
	        qvo.setOrderBys(orderBys);**/

			
			qvo.setTitle(createTitle(qvo));

			qvo.setIsCountryLevel(true); //allows country and regional selections in the checkbox
			qvo.setIsRegionLevel(true);


			//AMISConstants.setFAOSTATDBSettings(qvo);


			return qvo;
		}

	public static void getCodes(final HashMap<String, String> map, List<AMISCodesModelData> selectedCodes, AMISQueryVO originalQVO, String resourceType) {
		HashMap<String, String> codes = new HashMap<String, String>();

		HashMap<String, String> aggregatedCodes = new HashMap<String, String>();
		System.out.println(" ^^^^^^^^^^^^^^^^^^^^^^^^^AMIS COMAPARE getCodes ");



		for(AMISCodesModelData code : selectedCodes) {
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

			AMISQueryVO qvo =  new AMISQueryVO();
			qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
			qvo.setTypeOfOutput(resourceType);
			//AMISConstants.setFAOSTATDBSettings(qvo);

			qvo.getAggregationsFilter().putAll(aggregatedCodes);
			qvo.setDomains(originalQVO.getDomains());

			// TODO: get codes
			try {
				AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

					@SuppressWarnings("unchecked")
					public void onSuccess(AMISResultVO vo) {

						for(AMISCodesModelData code : vo.getCodes()) {
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

    public static void setYears2(AMISQueryVO qvo, HashMap<String, AMISCodesModelData> timerange) {

        //List<String> baseDate = new ArrayList<String>();
        System.out.println("setYears2 start");
        HashMap<String, String> selectedCodes = new HashMap<String, String>();


		Date fromDate = FieldParser.parseDate(timerange.get(AMISConstants.TIMERANGE.name()+"_FROM").getCode());
		Date toDate =  FieldParser.parseDate(timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getCode());

        System.out.println("ok");

        Boolean updateView = true;

        // error message
        if ( fromDate.compareTo(toDate) > 0 ) {
            updateView = false;
            FenixAlert.error("Select date", ("Date selection is wrong"));
//				Window.alert("Date selection is wrong");

        }
        else if ( fromDate.compareTo(toDate) == 0 ) {
            selectedCodes.clear();
            selectedCodes.put(timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getCode(), timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getLabel());

        }
        else if ( fromDate.compareTo(toDate) < 0 ) {
            selectedCodes.clear();

            while( fromDate.compareTo(toDate) <= 0 ) {
                //	System.out.println("dates: " + fromDate + " | " + toDate);

                selectedCodes.put(FieldParser.formatDate(fromDate, "formatterMinusReverse"), FieldParser.formatDate(fromDate, "formatterYear"));

                fromDate = new Date(fromDate.getYear() + 1, fromDate.getMonth(), fromDate.getDate());
            }

        }

        System.out.println("basedate: " + selectedCodes);

        qvo.setYears(selectedCodes);


    }
	
	
	public static void setSplitYears(AMISQueryVO qvo, HashMap<String, AMISCodesModelData> timerange) {

		//List<String> baseDate = new ArrayList<String>();
        System.out.println("setYears2 start");
		HashMap<String, String> selectedCodes = new HashMap<String, String>();


//		Date fromDate = FieldParser.parseDate(timerange.get(AMISConstants.TIMERANGE.name()+"_FROM").getCode());
//		Date toDate =  FieldParser.parseDate(timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getCode());
        String fromDate = timerange.get(AMISConstants.TIMERANGE.name()+"_FROM").getCode();
        String toDate =  timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getCode();

			System.out.println("ok");

			Boolean updateView = true;

			// error message
			if ( fromDate.compareTo(toDate) > 0 ) {
				updateView = false;
				FenixAlert.error("Select date", ("Date selection is wrong"));
//				Window.alert("Date selection is wrong");

			}
			else if ( fromDate.compareTo(toDate) == 0 ) {
				selectedCodes.clear();
				selectedCodes.put(timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getCode(), timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getLabel());

			}
			else if ( fromDate.compareTo(toDate) < 0 ) {
				selectedCodes.clear();

				while( fromDate.compareTo(toDate) <= 0 ) {
				//	System.out.println("dates: " + fromDate + " | " + toDate);

					/*selectedCodes.put(FieldParser.formatDate(fromDate, "formatterMinusReverse"), FieldParser.formatDate(fromDate, "formatterYear"));*/
                    selectedCodes.put(fromDate, fromDate);
                    String fromDateApp = fromDate.substring(0,4);
                    Integer first_year = Integer.parseInt(fromDateApp) + 1;
                    Integer last_year = first_year + 1;
                    fromDate = ""+first_year +"/"+(""+last_year).substring(2);
					//fromDate = new Date(fromDate.getYear() + 1, fromDate.getMonth(), fromDate.getDate());
				}

			}

			System.out.println("basedate: " + selectedCodes);

			qvo.setYears(selectedCodes);


	}
	
	
	private static void setYears(AMISQueryVO qvo, HashMap<String, AMISCodesModelData> timerange) {
		HashMap<String, String> selectedCodes = new HashMap<String, String>();

		
		//Integer fromDate =  Integer.valueOf(timerange.get(AMISConstants.TIMERANGE.name()+"_FROM").getCode());
		//Integer toDate =  Integer.valueOf(timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getCode());

		Date fromDate = FieldParser.parseDate(timerange.get(AMISConstants.TIMERANGE.name()+"_FROM").getCode());
		Date toDate =  FieldParser.parseDate(timerange.get(AMISConstants.TIMERANGE.name()+"_TO").getCode());

			
		System.out.println("from  year: " + fromDate);
		System.out.println("to  year: " + toDate);
		
		
		System.out.println("timerange FROM .getCode() "+ timerange.get(AMISConstants.TIMERANGE.name()+"_FROM").getCode());

		selectedCodes.clear();

		/** TODO: implement between **/
		// error message
		if ( fromDate.compareTo(toDate) > 0 ) {
			FenixAlert.error("Year Selection", ("Form Year is greater than the To Year, please re-select"));
		}
		else if ( fromDate.compareTo(toDate) == 0 ) {
			System.out.println("fromDate.compareTo(toDate) == 0");
			//selectedCodes.put(fromDate.toString(), fromDate.toString());
			selectedCodes.put(FieldParser.formatDate(fromDate, "formatterMinusReverse"), FieldParser.formatDate(fromDate, "formatterMinusReverse"));

		}
		else if ( fromDate.compareTo(toDate) < 0 ) {
			System.out.println("fromDate.compareTo(toDate)  < 0 ");

			while( fromDate.compareTo(toDate) <= 0 ) {
				//System.out.println("dates: " + fromDate + " | " + toDate);

				selectedCodes.put(FieldParser.formatDate(fromDate, "formatterMinusReverse"), FieldParser.formatDate(fromDate, "formatterMinusReverse"));

				
				//fromDate++;
			}

		}

		qvo.setYears(selectedCodes);
	}

	private static String createTitle(AMISQueryVO qvo){
		String title = "Compare Areas: ";

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

	public static void checkExportIfAllTheAsyncCallsAreReturned(final ContentPanel panel, final AMISQueryVO qvo) {
		timer = new Timer() {
			public void run() {
				System.out.println("tokensAsync: " + tokensAsync);
				System.out.println("tokensReached: " + tokensReached);
				if ( tokensAsync == tokensReached ) {
					/** TODO: create chart **/
					System.out.println("start chart creation ...");
					AMISController.addChart(panel, qvo, "1050", "400");
					timer.cancel();
				}
			}
		};
		timer.scheduleRepeating(10);
	}
	
	private static List<AMISQueryVO> buildVOList(AMISQueryVO baseQvo) {
		List<AMISQueryVO> vos = new ArrayList<AMISQueryVO>();
		AMISQueryVO updatedQvo = baseQvo;
		updatedQvo.setWidth("1000"); 
		updatedQvo.setHeight("400"); 

		if(baseQvo.getYears().size() > 1){
			updatedQvo.setOutput(AMISConstants.CHART.toString());
			updatedQvo.setTypeOfOutput(AMISConstants.TIMESERIES.toString());

			if(baseQvo.getItems().size() > 1){
				for(Map.Entry<String,String> entry: baseQvo.getItems().entrySet()){
					String itemCode = entry.getKey();						

					HashMap<String, String> newItemMap = new HashMap<String, String>();
					newItemMap.put(itemCode, entry.getValue());

					AMISQueryVO itemQvo = createVO(updatedQvo);
					itemQvo.setTitle(newItemMap.get(itemCode));
					itemQvo.setItems(newItemMap);	
					itemQvo.setElements(updatedQvo.getElements());		
					vos.add(itemQvo);

				}

			}
			else if (baseQvo.getElements().size() > 1){

				for(Map.Entry<String,String> entry: baseQvo.getElements().entrySet()){
					String elementCode = entry.getKey();		

					HashMap<String, String> newElementMap = new HashMap<String, String>();
					newElementMap.put(elementCode, entry.getValue());

					AMISQueryVO elementQvo = createVO(updatedQvo);
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
			updatedQvo.setOutput(AMISConstants.CHART.toString());
			updatedQvo.setTypeOfOutput(AMISConstants.BAR_WITH_CATEGORIES.toString());

			vos.add(updatedQvo);

		}

		System.out.println("buildVOList: VOs Size: " + vos.size());
		for(AMISQueryVO v : vos){
			System.out.println("debug: " + v.getTitle() + v.getElements());
		}
		return vos;

	}
		
	private static AMISQueryVO createVO(AMISQueryVO updatedQvo) {
		AMISQueryVO qvo = new AMISQueryVO();
		//AMISConstants.setFAOSTATDBSettings(qvo);
		qvo.setOutput(AMISConstants.CHART.toString());
		qvo.setTypeOfOutput(AMISConstants.TIMESERIES.toString());
		qvo.setDomains(updatedQvo.getDomains());
		qvo.setAreas(updatedQvo.getAreas());
		qvo.setYears(updatedQvo.getYears());
		qvo.setOrderBys(updatedQvo.getOrderBys());
		qvo.setIsCountryLevel(updatedQvo.getIsCountryLevel()); 
		qvo.setIsRegionLevel(updatedQvo.getIsRegionLevel());
		qvo.setSelects(updatedQvo.getSelects());
		qvo.setWidth("430"); 
		qvo.setHeight("400"); 

		return qvo;
	}
	
	public static void checkIfAllTheAsyncCallsAreReturned(final ContentPanel panel, final AMISQueryVO qvo) {
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
	
	private static boolean verifyForm(FormPanel form, LayoutContainer column1, LayoutContainer column2, LayoutContainer column3){
		boolean isVerified = true;
		
		List<String> selectedDatasources = new ArrayList<String>(); 
		for (Field<?> field: form.getFields()){
			
			if(field.getId().equalsIgnoreCase("DATASOURCE")) {
				CheckBoxGroup grp = (CheckBoxGroup) field;
				List<CheckBox> boxes = grp.getValues();
				for(CheckBox box: boxes){
					if(box.getValue()) {
						selectedDatasources.add(box.getBoxLabel());
					} 
				} 
			}
		}
		
		if(selectedDatasources.size() == 0)
			return isVerified = false;
		
		
		Component areaSelector = column1.getItemByItemId(AMISConstants.AMIS_COUNTRIES.toString());
		CheckBoxListView<AMISCodesModelData> list = (CheckBoxListView<AMISCodesModelData>) areaSelector;
	
		if(areaSelector!=null) {
		    if(list.getChecked().size() == 0) {
		    	return isVerified = false;
		        
		    }    
		}
		
		
		Component productSelector = column2.getItemByItemId(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString());
		list = (CheckBoxListView<AMISCodesModelData>) productSelector;
		
		if(productSelector!=null) {
		    if(list.getChecked().size() == 0)
		    	return isVerified = false;
		}
		
		Component elementSelector = column3.getItemByItemId(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString());
		list = (CheckBoxListView<AMISCodesModelData>) elementSelector;
		
		if(elementSelector!=null) {
		    if(list.getChecked().size() == 0)
		    	return isVerified = false;
		}
		
		return isVerified;
		
	}

	public static List<Widget> getEmptyPanels() {
		return emptyPanels;
	}

	public static void setEmptyPanels(List<Widget> emptyPanels) {
		AMISCompareController.emptyPanels = emptyPanels;
	}
	
	public static List<Widget> getChartPanels() {
		return chartPanels;
	}

	public static void setChartPanels(List<Widget> chartPanels) {
		AMISCompareController.chartPanels = chartPanels;
	}
  	
	/* Get elements */
	public static Listener<BaseEvent> getElementsForProductsOnClick(final AMISCompare compare, final CheckBoxListView<AMISCodesModelData> products, final LayoutContainer elementContainer, final AMISCompareSelectorMaker selectorMaker) {
		return  new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				System.out.println("getElementsForProductsOnClick: START ");
				
				
				if(products.getChecked().size() > 0) {
					 products.getSelectionModel().setSelection(products.getChecked());					 
				} 
				//rebuildElementSelector(compare, products, elementContainer, selectorMaker);
				

				Component component = elementContainer.getItemByItemId(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.name());
				
				if(component!=null) {
					CheckBoxListView<AMISCodesModelData> elementListView = (CheckBoxListView<AMISCodesModelData>) component;
					if(elementListView.getChecked().size() == 0) {
						System.out.println("getElementsForProductsOnClick: REBUILD ELEMENT LIST");
						rebuildElementSelector(compare, products, elementContainer, selectorMaker);	
					}
					else {
						System.out.println("getElementsForProductsOnClick: DONT REBUILD ELEMENT LIST");
					}
				} else
					rebuildElementSelector(compare, products, elementContainer, selectorMaker);	
				
			}
		};
	}
	
	public static Listener<SelectionChangedEvent<AMISCodesModelData>> getElementsForProducts(final AMISCompare compare, final CheckBoxListView<AMISCodesModelData> products, final LayoutContainer elementContainer, final AMISCompareSelectorMaker selectorMaker) {
		return  new Listener<SelectionChangedEvent<AMISCodesModelData>>() {
			public void handleEvent(SelectionChangedEvent<AMISCodesModelData> be) {
				rebuildElementSelector(compare, products, elementContainer, selectorMaker);		
			}
		};
	}
	
	
	private static void rebuildElementSelector(final AMISCompare compare, final CheckBoxListView<AMISCodesModelData> products, final LayoutContainer elementContainer, final AMISCompareSelectorMaker selectorMaker){
		// if there products.getChecked().size() > 0 then get the elements in common to the checked products	
		// otherwise get full element list i.e with no product filter applied
				
		HashMap<String, String> productsFilter = new HashMap<String, String>();

		List<AMISCodesModelData> checkedProducts = products.getChecked();
		System.out.println(" &&&&&&&&& CHECKED ITEMS "+ products.getChecked().size());
		
		if(products.getChecked().size() > 0) {
			List<AMISCodesModelData> selectedProducts = products.getSelectionModel().getSelectedItems();
			for(AMISCodesModelData product: selectedProducts){
				productsFilter.put(product.getCode(), product.getLabel());
			}

			for(AMISCodesModelData product: checkedProducts){
				productsFilter.put(product.getCode(), product.getLabel());
			}
		}	else {
			productsFilter.clear();
		}
		
	
		Component component = elementContainer.getItemByItemId(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.name());
		
		if(component!=null) {
			CheckBoxListView<AMISCodesModelData> elementListView = (CheckBoxListView<AMISCodesModelData>) component;
			elementContainer.remove(elementListView);
			System.out.println("AMIS_PRODUCT_ELEMENTS REMOVED ");
		} else
			System.out.println("AMIS_PRODUCT_ELEMENTS is null ");

		System.out.println("productsFilter "+productsFilter);
		
		final CheckBoxListView<AMISCodesModelData> elementsList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString(), AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT_COMPARE.toString(), true, false, "168px", "200px", null, productsFilter, AMISConstants.AMIS_PRODUCTS.name());
		
		elementsList.addListener(Events.OnClick,   
				new Listener<BaseEvent>() {   
			public void handleEvent(final BaseEvent ce) {
					
				if(elementsList.getStore().getCount() > 0 ){
				/** Sing element selection: 
				 AMISCodesModelData selected = elementsList.getSelectionModel().getSelectedItem();
				 
					System.out.println("selected "+selected.getLabel());

					for(AMISCodesModelData model: elementsList.getChecked()) {
						if(!model.getCode().equals(selected.getCode())) {
							elementsList.getSelectionModel().deselect(model);
							elementsList.refreshNode(elementsList.getStore().indexOf(model));
						}

					}*/
					if(products.getChecked().size() == 0) {
						AMISCompareController.rebuildProductSelector(compare, elementsList, compare.getColumn2(), selectorMaker);		
					}
				
				}

			}
		}); 
		
		
		
		elementContainer.add(elementsList, compare.getFormData());
		
		compare.getColumn3().layout();

		
	}
		
		
	
	
	/* Get products */
	public static void rebuildProductSelector(final AMISCompare compare, final CheckBoxListView<AMISCodesModelData> elements, final LayoutContainer productContainer, final AMISCompareSelectorMaker selectorMaker){
		// get the associated products for the checked elements
		System.out.println("rebuildProductSelector START ");	
		HashMap<String, String> elementsFilter = new HashMap<String, String>();

		List<AMISCodesModelData> checkedElements = elements.getChecked();
		System.out.println(" &&&&&&&&& CHECKED ITEMS  "+ elements.getChecked().size());
		
		if(elements.getChecked().size() > 0) {
			List<AMISCodesModelData> selectedElements = elements.getSelectionModel().getSelectedItems();
			for(AMISCodesModelData element: selectedElements){
				elementsFilter.put(element.getCode(), element.getLabel());
			}

			for(AMISCodesModelData element: checkedElements){
				elementsFilter.put(element.getCode(), element.getLabel());
			}
		}	else {
			elementsFilter.clear();
		}
		
		Component component = productContainer.getItemByItemId(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.name());
		
		if(component!=null) {
			CheckBoxListView<AMISCodesModelData> elementListView = (CheckBoxListView<AMISCodesModelData>) component;
			productContainer.remove(elementListView);
			System.out.println("AMIS_ELEMENT_PRODUCTS REMOVED ");
		} else
			System.out.println("AMIS_ELEMENT_PRODUCTS is null ");

		System.out.println("elementsFilter "+elementsFilter);
		
		final CheckBoxListView<AMISCodesModelData> productsList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString(), AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT_COMPARE.toString(), true, false, "168px", "200px", null, elementsFilter, AMISConstants.AMIS_ELEMENTS.name());
		//productsList.getSelectionModel().addListener(Events.SelectionChange, AMISCompareController.getElementsForProducts(compare, productsList, compare.getColumn3(), selectorMaker));
		productsList.addListener(Events.OnClick, AMISCompareController.getElementsForProductsOnClick(compare, productsList, compare.getColumn3(), selectorMaker));
		
		
		compare.getColumn2().insert(productsList, 1, compare.getFormData());
		compare.getColumn2().layout();
	}
	
	
	/* Get countries */
	public static void rebuildCountriesSelector(final AMISCompare compare, final AMISCompareSelectorMaker selectorMaker){
		System.out.println("rebuildCountriesSelector START ");	
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.AMIS_COUNTRIES.toString());
		AMISConstantsVO.setLanguageSettings(qvo);
		qvo.setGetTotalAndList(true);
		qvo.setSelectedDataset(null);
		
		Component areaSelector = compare.getColumn1().getItemByItemId(AMISConstants.AMIS_COUNTRIES.toString());
	
		if(areaSelector!=null) {
			CheckBoxListView<AMISCodesModelData> list = (CheckBoxListView<AMISCodesModelData>) areaSelector;
			compare.getColumn1().remove(list);
			
			System.out.println("AMIS_COUNTRIES REMOVED ");
		} else
			System.out.println("AMIS_COUNTRIES is null ");

		
		final CheckBoxListView<AMISCodesModelData> countriesList = selectorMaker.buildCheckBoxList(compare, qvo, AMISConstants.AMIS_COUNTRIES.toString(), false, "190px", "200px", null);
		
		compare.getColumn1().insert(countriesList, 1, compare.getFormData());
		compare.getColumn1().layout();
	}
	
	
	public static Listener<FieldEvent> changeFieldsetHeading(final AMISCompare compare, final Radio radio, final String title) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {			
				if(radio.getValue())
				{
					FieldSet f3 = (FieldSet)compare.getColumn4().getItem(3);
					if(title!=null)
					{
						if(title.equals("COMPARE"))
						{
							RootPanel.get("OLAP_IFRAME").setVisible(false);
							RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
							//RootPanel.get("COMPARE_NOTES").setVisible(true);
							f3.setHeading("<font color='#000000' style='font-weight:normal;font-size:13px'>Compare By: </font>"); 
							f3.setVisible(true);
							//compare.getOutputPanel().setData("IFRAME", null);
							// Html test = (Html)compare.getOutputPanel().getData("IFRAME");
							 //'display: block;'
//							 if(test!=null)
//							 {				
//								// ((Html)compare.getOutputPanel().getData("IFRAME")).setVisible(false);
//								//test.getElement().setAttribute("display","none");
//								// test.setVisible(false);
//								
//								compare.getOutputPanel().getLayout().layout();
//							 }
						
						//	RootPanel.get("WHITE_SPACE").setVisible(true);
						}
						else if(title.equals("NESTED"))
						{
							//f3.setHeading("<font color='#000000' style='font-weight:normal;font-size:13px'>Nested By: </font>"); 
							f3.setVisible(false);
							 for(int z=compare.getOutputPanel().getItemCount()-1; z>=0; z--)
								{
									 System.out.println("Class: AMISController Function: buildViews Text: id= "+compare.getOutputPanel().getWidget(z).getElement().getId());
									 System.out.println("Class: AMISController Function: buildViews Text: title = "+compare.getOutputPanel().getWidget(z).getElement().getTitle());
//									if((compare.getOutputPanel().getWidget(z).getElement().getId()!=null)&&(compare.getOutputPanel().getWidget(z).getElement().getId().equals("amisOLAP")))
//									{
//										//Olap Iframe.... do not remove
//										 System.out.println("Class: AMISController Function: buildViews Text: if  title = "+compare.getOutputPanel().getWidget(z).getElement().getTitle());
//									}
//									else if((compare.getOutputPanel().getWidget(z).getElement().getTitle()!=null)&&(!compare.getOutputPanel().getWidget(z).getElement().getTitle().equals("Notes")))
//									{
//										System.out.println("Class: AMISController Function: buildViews Text: else title = "+compare.getOutputPanel().getWidget(z).getElement().getTitle()+" ... Removing");
//										compare.getOutputPanel().getWidget(z).removeFromParent();
//									}
									compare.getOutputPanel().getWidget(z).removeFromParent();
								}
						}
						// RootPanel.get("COMPARE_NOTES").setVisible(false);
					}	
					
				}
			}
		};
	}
  
}
