package org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATChartController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATTableController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchOracleOutput;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchTextBox;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.byarea.FAOSTATVisualizeByArea;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATQueryVOBuilderVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.top20.FAOSTATVisualizeTop20;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsInfoVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;


public class FAOSTATVisualizeByAreaController extends FAOSTATVisualizeController {

	
//public static void testFilters(FAOSTATVisualizeByArea visualization, FAOSTATVisualizeSettingsVO settings, DWCodesModelData code, List<DWCodesModelData> defaultCodes) {
//		
//		System.out.println("testFilters ...");
//
//		// FILTERS
//		List<FAOSTATVisualizeFilter> filters = settings.getFilters();
//		
//		filters = new ArrayList<FAOSTATVisualizeFilter>();
//	
//		FAOSTATVisualizeFilter filter5 = new FAOSTATVisualizeFilter();
//		filter5.setFilterType("COUNTRIES");
//		filters.add(filter5);
//		
//		
//		FAOSTATVisualizeFilter filter4 = new FAOSTATVisualizeFilter();
//		filter4.setFilterType("TIMERANGE");
//		filters.add(filter4);
//		
//		FAOSTATVisualizeController.buildFilters(visualization, filters, code, defaultCodes);
//	}


//	public static void testGetVOSOriginal(ContentPanel panel, FAOSTATVisualizeSettingsVO settings, DWCodesModelData code) {
//
//		System.out.println("testGetVOS ...");
//
//		// QVOS
//		List<DWFAOSTATQueryVO> qvos = new ArrayList<DWFAOSTATQueryVO>();
//
//		//VIEW 1: Resources		
//
//		// SELECTS
//		List<String> selects = new ArrayList<String>();
//		selects.add("I.ItemName" + FAOSTATConstants.faostatLanguage);
//		selects.add("(CONVERT(varchar, CONVERT(money, D.Value), 1) + ' ' + E.UnitName"+FAOSTATConstants.faostatLanguage+")");
//
//		// ITEMS
//		HashMap<String, String> items = new HashMap<String, String>();
//		items.put("6600", "6600");
//		items.put("6610", "6610");
//		items.put("6661", "6661");
//
//		// ELEMENTS
//		HashMap<String, String> elements = new HashMap<String, String>();
//		elements.put("5110", "5110");
//
//		// DOMAIN
//		HashMap<String, String> domain = new HashMap<String, String>();
//		domain.put("RL", "RL");
//
//		DWFAOSTATQueryVO qvo = FAOSTATQueryVOBuilderVisualize.getByAreaStandardQuery(code, "TABLE", "TABLE", "Land Resources", selects, elements, items, domain, true, false, null,  "500", "300");
//		qvo.setRunMaxDateQuery(true);
//		qvos.add(qvo);
//		
//		// ORDERBYS
//		List<String> orderBys = new ArrayList<String>();
//		orderBys.add(qvo.getAggregationType()+"(D.Value)");
//		qvo.setOrderBys(orderBys);
//		
//
//		//VIEW 2: Land Use 	
//		// SELECTS
//		List<String> selects2 = new ArrayList<String>();
//		selects2.add("I.ItemCode");
//		selects2.add("I.ItemName" + FAOSTATConstants.faostatLanguage);
//
//		// ITEMS
//		HashMap<String, String> items2 = new HashMap<String, String>();
//		items2.put("6621", "6621");
//		items2.put("6655", "6655");
//		
//
//		// ELEMENTS
//		HashMap<String, String> elements2 = new HashMap<String, String>();
//		elements2.put("5110", "5110");
//
//		// DOMAIN
//		HashMap<String, String> domain2 = new HashMap<String, String>();
//		domain2.put("RL", "RL");
//
//		
//        
//		qvo = FAOSTATQueryVOBuilderVisualize.getByAreaStandardQuery(code, "CHART", "PIE", "Land Use", selects2, elements2, items2, domain2, true, false, null,  FAOSTATConstants.SMALL_WIDTH, FAOSTATConstants.SMALL_HEIGHT);
//		qvo.setRunMaxDateQuery(true);
//		qvo.setRunCalculationQuery(true);
//		
//		CalculationParametersVO  calcParameters = new CalculationParametersVO();
//		calcParameters.setResultLabel("Other land");
//		calcParameters.setCalculationType(FAOSTATAggregationConstant.SUBTRACT);
//		calcParameters.setFilterColumn(FAOSTATTableConstant.Item);
//		
//		List<String> subtractItems = new ArrayList<String>();
//		subtractItems.add("6601");		
//		calcParameters.setSubtractionMinuend(subtractItems);
//		
//		List<String> subtractItemsFrom = new ArrayList<String>();
//		subtractItemsFrom.add("6655");
//		subtractItemsFrom.add("6621");
//		
//		calcParameters.setSubtractionSubtrahend(subtractItemsFrom);
//		
//        qvo.setCalculationParametersVO(calcParameters);
//        
//     // ORDERBYS
//		List<String> orderBys3 = new ArrayList<String>();
//		orderBys3.add(qvo.getAggregationType()+"(D.Value)");
//		qvo.setOrderBys(orderBys3);
//		
//		qvos.add(qvo);
//
//	
//
//		//VIEW 3: Production (Cereals)		
//		
//		// SELECTS
//		List<String> selects3 = new ArrayList<String>();
//		selects3.add("I.ItemName" + FAOSTATConstants.faostatLanguage);
//		selects3.add("D.Year");
//		
//		// ITEMS
//		HashMap<String, String> items3 = new HashMap<String, String>();
//		items3.put("1717", "1717");
//		items3.put("27", "27");
//		items3.put("15", "15");
//
//		// ELEMENTS
//		HashMap<String, String> elements3 = new HashMap<String, String>();
//		elements3.put("5510", "5510");
//
//		// DOMAIN
//		HashMap<String, String> domain3 = new HashMap<String, String>();
//		domain3.put("QC", "QC");
//
//		qvo = FAOSTATQueryVOBuilderVisualize.getByAreaStandardQuery(code, "CHART", "BAR_WITH_CATEGORIES", "Cereal Production", selects3, elements3, items3, domain3, true, false, null,  FAOSTATConstants.MEDIUM_WIDTH, FAOSTATConstants.MEDIUM_HEIGHT);
//		qvo.setRunMaxDateQuery(true);
//		qvo.setTimeSpan(10);
//		// ORDERBYS
//		List<String> orderBys4 = new ArrayList<String>();
//		orderBys4.add("I.ItemName"+ FAOSTATConstants.faostatLanguage);
//		orderBys4.add("D.Year");
//		qvo.setOrderBys(orderBys4);
//		
//		qvo.setSortingOrder("ASC");
//
//		qvos.add(qvo);
//
//
//		//VIEW 4 Livestock primary production
//		
//		// SELECTS
//		List<String> selects4 = new ArrayList<String>();
//		selects4.add("I.ItemName" + FAOSTATConstants.faostatLanguage);
//		selects4.add("D.Year");
//
//		// ITEMS
//		HashMap<String, String> items4 = new HashMap<String, String>();
//		//items4.put("1780", "1780"); remove milk!
//		items4.put("1783", "1783");
//		items4.put("1806", "1806");
//		items4.put("1806", "1806");
//		items4.put("1807", "1807");
//		items4.put("1808", "1808");
//
//		// ELEMENTS
//		HashMap<String, String> elements4 = new HashMap<String, String>();
//		elements4.put("5510", "5510");
//
//		// DOMAIN
//		HashMap<String, String> domain4 = new HashMap<String, String>();
//		domain4.put("QL", "QL");
//
//		qvo = FAOSTATQueryVOBuilderVisualize.getByAreaStandardQuery(code, "CHART", "TIMESERIE", "Livestock Primary Production", selects4, elements4, items4, domain4, true, false, null,  FAOSTATConstants.MEDIUM_WIDTH, FAOSTATConstants.MEDIUM_HEIGHT);
//		qvo.setSortingOrder("ASC");
//		qvo.setRunMaxDateQuery(true);
//		qvo.setTimeSpan(10);
//		
//		// ORDERBYS
//		List<String> orderBys2 = new ArrayList<String>();
//		orderBys2.add("I.ItemName"+ FAOSTATConstants.faostatLanguage);
//		orderBys2.add("D.Year");
//		qvo.setOrderBys(orderBys2);
//		
//
//		qvos.add(qvo);
//
//
//		settings.setQvos(qvos);
//
//		buildViews(panel, qvos, code.getLabel()+ " Overview");
//	}
	
	
	public static void buildViews(ContentPanel panel, List<DWFAOSTATQueryVO> qvos, String title) {
	    System.out.println("buildViews ...");

	    for(DWFAOSTATQueryVO vo: qvos){
//	    	System.out.println("$$$$$$$$$$$$$$$$$ Default: title "+ vo.getTitle() +" : type "+vo.getResourceType());
	    	System.out.println("$$$$$$$$$$$$$$$$$ Default: title "+ vo.getTitle() +" : ot="+vo.getTypeOfOutput() + " | o=" + vo.getOutput());

	    }
	    
	   /*** panel.removeAll();
	    
	    VerticalPanel v = new VerticalPanel();
		v.add(DataViewerClientUtils.addVSpace(15));
		v.add(new Html("<div align='center' class='visualize_center_title'>" + title + "</div>"));

		v.add(DataViewerClientUtils.addVSpace(15));
		v.add(DataViewerClientUtils.addVSpace(15));		
			
				
		
		// first row
		HorizontalPanel h1 = new HorizontalPanel();
		ContentPanel c2 = new ContentPanel();
		c2.setHeaderVisible(false);
		c2.setBodyBorder(false);
		//c2.setWidth(250);
//		c2.addStyleName("content_box");
//		visualization.getCenterPanel().add(c2);
		h1.add(c2);
		FAOSTATTableController.addTable(c2, qvos.get(0), "200px", "100px");
		
		
		h1.add(DataViewerClientUtils.addHSpace(10));
		
	
		ContentPanel c3 = new ContentPanel();
		c3.setHeaderVisible(false);
		c3.setBodyBorder(false);
		//c3.setWidth(500);
//		c3.addStyleName("content_box");
//		visualization.getCenterPanel().add(c3);
		h1.add(c3);
		//FAOSTATChartController.addChart(c3, qvos.get(1), FAOSTATVisualizeByDomainConstants.SMALL_CHART_WIDTH, FAOSTATVisualizeByDomainConstants.SMALL_CHART_HEIGHT);
		FAOSTATChartController.addChart(c3, qvos.get(1), FAOSTATConstants.SMALL_WIDTH_PIXEL, FAOSTATConstants.SMALL_HEIGHT_PIXEL, DataViewerBoxContent.SMALL_SIZE);
		
		v.add(h1);

		v.add(DataViewerClientUtils.addVSpace(15));
		
		
		// second row	
		HorizontalPanel h2 = new HorizontalPanel();
		ContentPanel c4 = new ContentPanel();
		c4.setHeaderVisible(false);
		c4.setBodyBorder(false);
		//c4.setWidth(900);
//		c4.addStyleName("content_box");
		h2.add(c4);
		FAOSTATChartController.addChart(c4, qvos.get(3), FAOSTATConstants.MEDIUM_WIDTH_PIXEL, FAOSTATConstants.MEDIUM_HEIGHT_PIXEL, DataViewerBoxContent.MEDIUM_SIZE);

		v.add(h2);
		
		
		// third row	
		HorizontalPanel h3 = new HorizontalPanel();
		ContentPanel c5 = new ContentPanel();
		c5.setHeaderVisible(false);
		c5.setBodyBorder(false);
		c5.setWidth(800);
		h3.add(c5);
		FAOSTATChartController.addChart(c5, qvos.get(2), FAOSTATConstants.MEDIUM_WIDTH_PIXEL, FAOSTATConstants.MEDIUM_HEIGHT_PIXEL, DataViewerBoxContent.MEDIUM_SIZE);
		
	
		v.add(h3);
		
	    
		panel.add(v);
		panel.layout();***/
		
	}
	
	public static void buildViewsAgent(final ContentPanel panel, List<DWFAOSTATQueryVO> qvos, String title) {
			panel.removeAll();
		
			VerticalPanel v = new VerticalPanel();
			v.setSpacing(5);
			//v.add(new Html("<div align='center' class='visualize_center_title'>" + title + "</div>"));
			v.add(new Html("<div align='center' class='page_title'>" + title + "</div>"));
		
			v.add(DataViewerClientUtils.addVSpace(15));
		
			for(DWFAOSTATQueryVO qvo : qvos) {
				FAOSTATConstants.setFAOSTATDBSettings(qvo);
			}

			ContentPanel objects = new ContentPanel();  
			objects.setHeaderVisible(false);
			objects.setBodyBorder(false);
			
			v.add(objects);
			panel.add(v);
			
			// GOOGLE stuff
			String googleCategory = FAOSTATCurrentView.VISUALIZE_BY_AREA.toString();
			String googleLabel = title;
			String googleAction = "Visualize by Area";
			
			// change to visualize by country constrants
			FAOSTATVisualizeByDomainController.buildViews(objects, qvos, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_WIDTH, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, googleCategory, googleLabel, googleAction);

			panel.layout();

	}
	
//	public static void buildDefaultViews(ContentPanel panel, List<DWFAOSTATQueryVO> qvos, String title) {
//	    System.out.println("buildDefaultViews ...");
//	    
//	    panel.removeAll();
//			
//		VerticalPanel v = new VerticalPanel();
//		v.add(DataViewerClientUtils.addVSpace(15));
//		//v.add(new Html("<div align='center' class='visualize_center_title'>" + title + "</div>"));
//		v.add(new Html("<div align='center' class='page_title'>" + title + "</div>"));
//
//		v.add(DataViewerClientUtils.addVSpace(15));
//		v.add(DataViewerClientUtils.addVSpace(15));
//		
//		
//		// first row
//		HorizontalPanel h1 = new HorizontalPanel();
//		ContentPanel c2 = new ContentPanel();
//		c2.setHeaderVisible(false);
//		c2.setBodyBorder(false);
//		//c2.setWidth(250);
////		c2.addStyleName("content_box");
////		visualization.getCenterPanel().add(c2);
//		h1.add(c2);
//		qvos.get(0).setShowColumnHeaders(false);
//		FAOSTATTableController.addTable(c2, qvos.get(0), "250px", "100px");
//		
//		
//		h1.add(DataViewerClientUtils.addHSpace(10));
//		
//	
//		ContentPanel c3 = new ContentPanel();
//		c3.setHeaderVisible(false);
//		c3.setBodyBorder(false);
//		//c3.setWidth(500);
////		c3.addStyleName("content_box");
////		visualization.getCenterPanel().add(c3);
//		h1.add(c3);
//		//FAOSTATChartController.addChart(c3, qvos.get(1), FAOSTATVisualizeByDomainConstants.SMALL_CHART_WIDTH, FAOSTATVisualizeByDomainConstants.SMALL_CHART_HEIGHT);
//		FAOSTATChartController.addChart(c3, qvos.get(1), "650", FAOSTATConstants.SMALL_HEIGHT);
//		
//		v.add(h1);
//
//		v.add(DataViewerClientUtils.addVSpace(15));
//		
//		
//		// second row	
//		HorizontalPanel h2 = new HorizontalPanel();
//		ContentPanel c4 = new ContentPanel();
//		c4.setHeaderVisible(false);
//		c4.setBodyBorder(false);
//		//c4.setWidth(900);
////		c4.addStyleName("content_box");
//		h2.add(c4);
//		FAOSTATChartController.addChart(c4, qvos.get(3), FAOSTATConstants.MEDIUM_WIDTH, FAOSTATConstants.MEDIUM_HEIGHT);
//
//		v.add(h2);
//		
//		v.add(DataViewerClientUtils.addVSpace(15));
//		
//		// third row	
//		HorizontalPanel h3 = new HorizontalPanel();
//		ContentPanel c5 = new ContentPanel();
//		c5.setHeaderVisible(false);
//		c5.setBodyBorder(false);
//		c5.setWidth(800);
//		h3.add(c5);
//		FAOSTATChartController.addChart(c5, qvos.get(2), FAOSTATConstants.MEDIUM_WIDTH, FAOSTATConstants.MEDIUM_HEIGHT);
//		
//		v.add(h3);
//		
//		v.add(DataViewerClientUtils.addVSpace(15));
//		
//		// fourth row	
//		HorizontalPanel h4 = new HorizontalPanel();
//		ContentPanel c6 = new ContentPanel();
//		c6.setHeaderVisible(false);
//		c6.setBodyBorder(false);
//		c6.setWidth(800);
//		h4.add(c6);
//		FAOSTATChartController.addChart(c6, qvos.get(4), FAOSTATConstants.MEDIUM_WIDTH, FAOSTATConstants.MEDIUM_HEIGHT);
//
//	
//		v.add(h4);
//		
//		v.add(DataViewerClientUtils.addVSpace(15));
//		
//		panel.add(v);
//		panel.layout();
//		
//	}
	

	public static Listener<ComponentEvent> addAreaList(final FAOSTATVisualizeByArea visualization, final ContentPanel panel, final String areaType) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
//				visualization.center = new ContentPanel();  
//				visualization.center.setHeaderVisible(false);
////				center.setScrollMode(Scroll.AUTOY);   
//				
//				/** TODO: get filters **/
//				visualization.center.add(visualization.getFiltersPanel().build(null));
				getAreas(visualization, panel, areaType);	
//				FAOSTATVisualizeByAreaController.getAreas(this, center, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString());

			}
		};
	}
	
//	public static Listener<BaseEvent> addAreaList(final FAOSTATVisualizeByArea visualization, final ContentPanel panel, final String areaType) {
//		return new ClickHandler() {
//			     @Override  
//			     public void onClick(ClickEvent event) {  
//			    	 getAreas(visualization, panel, areaType);	
//			        }  
//			     }; 
//		}
	
	public static VerticalPanel buildContinentPanel(List<DWCodesModelData> codes, String continentIconPath, FAOSTATVisualizeByArea visualization) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		p.setBorders(true);
		Image i = new Image(continentIconPath);
		p.add(i);
		p.add(buildCountryColumns(codes, visualization));
		return p;
	}
	
	public static HorizontalPanel buildCountryColumns(final List<DWCodesModelData> codes, final FAOSTATVisualizeByArea visualization) {
		HorizontalPanel p = new HorizontalPanel();
		VerticalPanel vp = new VerticalPanel();
//		int limit = 15;
		
		int limit = (codes.size() / 4 ) +1;
		int i = 0;
		for(final DWCodesModelData code : codes ) {	
			ClickHtml html = new ClickHtml();
			html.setWidth("225px");
			html.setHtml(code.getLabel());
			html.addStyleName("area-hyperlink");
			
			html.addListener(Events.OnClick, new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent be) {
					try{
						DataViewerServiceEntry.getInstance().getFAOSTATQueryVOs(FAOSTATCurrentView.VISUALIZE_BY_AREA, FAOSTATCurrentView.VISUALIZE_BY_AREA.name(), FAOSTATConstants.faostatLanguage, new AsyncCallback<FAOSTATVisualizeSettingsVO>() {
							public void onSuccess(FAOSTATVisualizeSettingsVO settings) {
								Map<String,List<DWFAOSTATQueryVO>> vo = settings.getQvos();
								vo = FAOSTATQueryVOBuilderVisualize.getByAreaStandardQuery(vo, code, true, false);									
								buildViewsAgent(visualization.center, vo.get("DEFAULT"), code.getLabel() + " " + FAOSTATLanguage.print().overview());
							}
							public void onFailure(Throwable arg0) {
								
							}
						});	
			    	} catch (Exception e) {
			    		e.printStackTrace();
			 		}
				}
			}); 
			
			
			vp.add(html);
//			if ((i + 1) % limit == 0) {
			i++;
			if (i == limit) {
				i = 0;
				p.add(vp);
				vp = new VerticalPanel();
			}
		}
		p.add(vp);
		return p;
	}
	
	public static VerticalPanel buildVerticalSpacer() {
		VerticalPanel p = new VerticalPanel();
		p.setHeight("25px");
		return p;
	}	

	public static void getAreas(final FAOSTATVisualizeByArea visualization, final ContentPanel panel, final String areaType) {
		
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(areaType);	
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		qvo.setDomains(getDefaultDomain());
		panel.setBorders(false);
		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel("Loading", "200px", "200px", false ));
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings({ "unchecked", "deprecation" })
				public void onSuccess(DWFAOSTATResultVO vo) {
					
					panel.removeAll();
					
					LayoutContainer main = new LayoutContainer();
				    main.setLayout(new ColumnLayout());
				    main.setStyleAttribute("padding", "20px");
				    
					List<List<DWCodesModelData>> columns = splitList(vo.getCodes(), 50); 
					
					// TODO
					String[] iconPaths = new String[]{"dataviewer-images/africa.png", 
													  "dataviewer-images/americas.png", 
													  "dataviewer-images/asia.png", 
													  "dataviewer-images/europe.png", 
													  "dataviewer-images/oceania.png", };
					List<List<DWCodesModelData>> continents = splitContinents(vo.getCodes());
					for (int i = 0 ; i < continents.size() ; i++) {
						panel.add(buildContinentPanel(continents.get(i), iconPaths[i], visualization));
						panel.add(buildVerticalSpacer());
					}
				
				   panel.add(main);
				   panel.layout();
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	static <T> List<List<T>> splitList(List<T> list, final int L) {     
		List<List<T>> parts = new ArrayList<List<T>>();     
		final int N = list.size();     
		for (int i = 0; i < N; i += L) {         
			parts.add(new ArrayList<T>(             
					list.subList(i, Math.min(N, i + L)))         
					);     
			}     
		return parts; 
		}
	
	public static List<List<DWCodesModelData>> splitContinents(List<DWCodesModelData> codes) {     
		List<List<DWCodesModelData>> continents = new ArrayList<List<DWCodesModelData>>();
		List<DWCodesModelData> africa = new ArrayList<DWCodesModelData>();
		List<DWCodesModelData> americas = new ArrayList<DWCodesModelData>();
		List<DWCodesModelData> asia = new ArrayList<DWCodesModelData>();
		List<DWCodesModelData> europe = new ArrayList<DWCodesModelData>();
		List<DWCodesModelData> oceania = new ArrayList<DWCodesModelData>();
		for (DWCodesModelData code : codes) {
			if (code.getGroupCode().equals("5100")) {
					africa.add(code);
				} else if (code.getGroupCode().equals("5200")) {
					americas.add(code);
				} else if (code.getGroupCode().equals("5300")) {
					asia.add(code);
				} else if (code.getGroupCode().equals("5400")) {
					europe.add(code);
				} else if (code.getGroupCode().equals("5500")) {
					oceania.add(code);
				}
		}
		continents.add(africa);
		continents.add(americas);
		continents.add(asia);
		continents.add(europe);
		continents.add(oceania);
		return continents; 
	} 
	
	private static HashMap<String, String> getDefaultDomain() {
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put("QC", "Crops");
		
		return domain;
	}
	
}
