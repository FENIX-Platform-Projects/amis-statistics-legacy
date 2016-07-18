package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMAnalyseDataController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.ADAMComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMDataSourceSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMHome;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMMapWrapper;
import org.fao.fenix.web.modules.adam.client.view.ADAMOLPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMUsefulLinks;
import org.fao.fenix.web.modules.adam.client.view.ADAMQuantityColumn;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMSwitchClassificationPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMTabMenu;
import org.fao.fenix.web.modules.adam.client.view.ADAMToolbar;
import org.fao.fenix.web.modules.adam.client.view.ADAMWrapper;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMChangeInformation;
import org.fao.fenix.web.modules.adam.client.view.dataentry.DonorProfileDataManagementWindow;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMQuestionMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMVennMaker;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxColors;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.DatasetModel;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListImporterWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ExcelImporterWindow;
import org.fao.fenix.web.modules.haiti.client.view.HaitiLayersPanel;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.map.client.view.NativeOLCalls;
import org.fao.fenix.web.modules.map.client.view.OLBBoxRetriever;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.Counter;
import org.fao.fenix.web.modules.map.common.vo.JoinDatasetVO;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;


import org.fao.fenix.web.modules.adam.common.enums.ADAMAnalyseVIEW;
public class ADAMController {

	public static Boolean containsMaps;

	public final static Map<String, ADAMResultVO> chartMap;

	public final static Map<String, ADAMResultVO> tableMap;

	public final static Map<String, ADAMResultVO> questionsMap;

	public final static Map<String, ADAMResultVO> mapMap;

	public final static Map<String, ADAMResultVO> vennMap;

	public final static Map<String, ADAMBoxContent> currentUsedObjects;

	public final static int QUESTIONS = 6;

	public static List<String> baseDate = new ArrayList<String>();

	public final static Map<String, ADAMResultVO> currentCustom;
	
	public final static Map<String, ADAMResultVO> faoMap;
	
	public final static int CUSTOMS = 10;
	
	public final static int FAO = 8;
	
	public final static int CHANNELS = 2;
	
	public final static int COMPARATIVE_ADVANTAGE = 2;

	public static String globalTitle = "Global view";
	
	// code, label
	public static GaulModelData crs_aggregationColumn;

	// aggregationType
	public static String crs_aggregationType;

	// Measurement Unit
	public static String baseUnit;
	public static String baseUnitShort;
	
	// This is manage the objectes in the view
	// each time is changed a view, the counter is increased and it's associated through the objectWindowID to the objectes in the view
	//
	// each time the object has to be added there it's checked if the objectWindowId it's the same of the object to be inserted
	// if the view didn't change, the object is inserted
	//
	// TODO: the counter is not increased when there is a call to the fullScreenView and the smallScreenView of the objects
	protected static Counter objectWindow = new Counter(100);

	protected static long objectWindowId;

	public static Integer timeserieLimit = 3;

	public static ADAMSelectedDataset currentlySelectedDatasetCode;
	
	public static ADAMCurrentVIEW currentVIEW;
	
	public static String selectedTab;
	
	public static String referencePeriodString;
	
	public static Integer tokensAsync = 0;	
	public static Integer tokensReached = 0;
		
	protected static Timer timer;
	
	static {
		chartMap = new HashMap<String, ADAMResultVO>();
		tableMap = new HashMap<String, ADAMResultVO>();
		questionsMap = new HashMap<String, ADAMResultVO>();
		mapMap = new HashMap<String, ADAMResultVO>();
		vennMap = new HashMap<String, ADAMResultVO>();
		currentUsedObjects = new HashMap<String, ADAMBoxContent>();
		currentCustom = new HashMap<String, ADAMResultVO>();
		faoMap = new HashMap<String, ADAMResultVO>();
		//baseDate.add("01-01-2008");
		//baseDate.add("2008-01-01");		
	    //baseDate.add("2009-01-01");
	    //baseDate.add("2005-01-01");
	    baseDate.add("2006-01-01");
	    baseDate.add("2007-01-01");
	    baseDate.add("2008-01-01");
	    baseDate.add("2009-01-01");
	    baseDate.add("2010-01-01");
	    baseDate.add("2011-01-01");
	    
	     /* Remove the following two lines for FPMIS test */
	    //baseDate.add("2011-01-01");
	   // baseDate.add("2012-01-01");
	    
	    referencePeriodString = "2011";
		  
	    containsMaps = true;
		currentVIEW = ADAMCurrentVIEW.ADAMVIEW;
		selectedTab = ADAMCurrentVIEW.BROWSE.name();
		
		crs_aggregationColumn = new GaulModelData("Commitment", "usd_commitment");
		crs_aggregationType = "SUM";
		currentlySelectedDatasetCode = ADAMSelectedDataset.ADAM_CRS;		
		baseUnit = "USD Mil";
		baseUnitShort = "$Mil";

	}

//	public String getCode(String code){
//		return code;
//	}
	
/*	public static Listener<ComponentEvent> openADAM(final String position, final ADAMCurrentVIEW view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				//System.out.println("openADAM()");
				ADAMController.callViewAgent(view, "openADAM(position, view)");
				RootPanel.get(position).setStyleName("tab_menu_selected");
				ADAMTabMenu.setDefaultStyle(position);
			}
		};
	}*/
	
	
	public static void openADAM(final String position, final ADAMCurrentVIEW view, final String selectedTab) {
			   System.out.println(" openADAM for "+view);
				setTabAndCallView(position, view, selectedTab);
	}
	
	
	public static Listener<ComponentEvent> openADAMListener(final String position, final ADAMCurrentVIEW view, final String selectedTab) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				System.out.println(" openADAM for "+view);
				
				setTabAndCallView(position, view, selectedTab);
			}
		};
	}
	
	
	public static void setTabAndCallView(final String position, final ADAMCurrentVIEW view, final String selectedTab){
		RootPanel.get(position).setStyleName("tab_menu_selected");
		ADAMTabMenu.setDefaultStyle(position);
		
		System.out.println("setTabAndCallView: "+view + " historyToken/selectedTab "+ selectedTab);
		
		//Rebuild the language links based on the current history
		ADAMHome.buildAndReplaceLanguageElement(selectedTab);
		
		//ADAMController.callViewAgent(view, "setTabAndCallView(position, view, tab)", selectedTab);
		
	/*	ADAMAnalyseVIEW analyse = ADAMAnalyseVIEW.getCurrentView(selectedTab);
		
		if(analyse==null)
			ADAMController.callViewAgent(view, "setTabAndCallView(position, view, tab)", selectedTab);
		else {
			ADAMAnalyseDataController.analyseView = analyse;
			ADAMAnalyseDataController.openAnalyseView(ADAMAnalyseDataController.filters);	
		}*/
		
        ADAMAnalyseVIEW analyse = ADAMAnalyseVIEW.getCurrentView(selectedTab);
		
        if(selectedTab.contains("ANALYSE")) {
        	if(ADAMAnalyseDataController.analyseView==null)
    			ADAMController.callViewAgent(view, "setTabAndCallView(position, view, tab)", selectedTab);
    		else {
    			ADAMAnalyseDataController.openAnalyseView(ADAMAnalyseDataController.filters);	
    		}
        } else
        	ADAMController.callViewAgent(view, "setTabAndCallView(position, view, tab)", selectedTab);
        
	}
	
	/*public static Listener<ComponentEvent> openADAM(final ADAMCurrentVIEW view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				//System.out.println("openADAM()");
				ADAMController.callViewAgent(view, "openADAM(View)");
			}
		};
	}*/
	
	/*public static void addQuestionChart(final int questionIDX, final String entityLabel, final String color, final ADAMQueryVO q, final long objID) {
		final String position = "QUESTION_" + questionIDX;
		RootPanel.get(position).setStyleName("question-" + color + "-box content");
		RootPanel.get(position).add(ADAMQuestionMaker.buildWaitingPanel(questionIDX));
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				HorizontalPanel panel = ADAMQuestionMaker.buildQuestionChartPanel(questionIDX, vo, fullScreenQuestionsChart(position, color, vo.getTitle()));

				//System.out.println("addQuestionChart objID: " + objID + " | " + objectWindowId);

				if ( objID == objectWindowId) {
					questionsMap.put(position, vo);
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					RootPanel.get(position).setStyleName("question-" + color + "-box content");
					RootPanel.get(position).add(panel);
				}
			}
			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}*/

	/*public static void addQuestionGroupTable(final int questionIDX, final String entityLabel, final String color, final ADAMQueryVO q, final long objID) {
		final String position = "QUESTION_" + questionIDX;
		RootPanel.get(position).setStyleName("question-" + color + "-box content");
		RootPanel.get(position).add(ADAMQuestionMaker.buildWaitingPanel(questionIDX));
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {

				VerticalPanel cp = ADAMTableMaker.buildSmallQuestionTable(vo);
				HorizontalPanel panel = ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, vo, ADAMTableController.fullScreenQuestionTable(position, color, vo.getTitle()), cp);



				//System.out.println("addQuestionTable objID: " + objID + " | " + objectWindowId + " | " + position);

				if ( objID == objectWindowId) {
					questionsMap.put(position, vo);


					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					RootPanel.get(position).setStyleName("question-" + color + "-box content");
					RootPanel.get(position).add(panel);
				}


			}
			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}*/

	/**public static void addChart(final String position, final String color, final ADAMQueryVO q, final long objID, boolean addRemoveChartIcon, SelectionListener<ButtonEvent> removeListener) {
		
		ADAMController.addRemoveChartIcon = addRemoveChartIcon;
		ADAMController.removeListener = removeListener;
		
		addChart(position, color, q, objID);	
	}**/
	
	public static void addChart(final String position, final String color, final ADAMQueryVO q, final long objID, final boolean addRemoveChartIcon, final SelectionListener<ButtonEvent> removeListener, final String removeLabel) {
			//System.out.println("COLOR: " + color);
		RootPanel.get(position).setStyleName("small-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				if ( !q.getSwitchResources().isEmpty()) {
					vo.setSwitchResources(q.getSwitchResources());
				}
				
				VerticalPanel chartPanel = ADAMChartMaker.buildSmallChart(q, vo, position, color, fullScreenChart(q, position, color, q.getTitle()), addRemoveChartIcon, removeListener, removeLabel);
				//chartPanel.setHeight(ADAMConstants.SMALL_BOX_HEIGHT);
				//chartPanel.setWidth(ADAMConstants.SMALL_BOX_WIDTH);
				
				//System.out.println("CHART objID: " + objID + " | objectWindowId: " + objectWindowId  + " position " + position);
					chartMap.put(position, vo);
					currentUsedObjects.put(position, ADAMBoxContent.CHART);
				
				
				if ( objID == objectWindowId ) {
					//System.out.println("CHART objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position + " ADDED ");
//					chartMap.put(position, vo);
//					currentUsedObjects.put(position, ADAMBoxContent.CHART);
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//					if (RootPanel.get(position).getWidgetCount() > 0)
//						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					chartPanel.layout();
					RootPanel.get(position).setStyleName("small-box content");
					RootPanel.get(position).add(chartPanel);
				}
					
			}
			public void onFailure(Throwable e) {
				
				ADAMResultVO vo = new ADAMResultVO();
				
				vo.setDefaultResultVO(vo, q);
				
				VerticalPanel chartPanel = ADAMChartMaker.buildSmallChart(q, vo, position, color, fullScreenChart(q, position, color, q.getTitle()),addRemoveChartIcon, removeListener, removeLabel);

				
				if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
				
				RootPanel.get(position).setStyleName("small-box content");
				RootPanel.get(position).add(chartPanel);
				
				
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	public static void addChart(final String position, final String color, final ADAMQueryVO q, final long objID) {	
		//System.out.println("COLOR: " + color);
		System.out.println("addChart -------------------- q.getOutputType(): " + q.getResourceType() + " position "+position);
		
		RootPanel.get(position).setStyleName("small-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				if ( !q.getSwitchResources().isEmpty()) {
					vo.setSwitchResources(q.getSwitchResources());
				}
				
				if(vo.getDisclaimer()==null){
					vo.setDisclaimer(q.getDisclaimer());
				} 
				
				VerticalPanel chartPanel = ADAMChartMaker.buildSmallChart(q, vo, position, color, fullScreenChart(q, position, color, q.getTitle()), false, null, null);
				//chartPanel.setHeight(ADAMConstants.SMALL_BOX_HEIGHT);
				//chartPanel.setWidth(ADAMConstants.SMALL_BOX_WIDTH);
				
			//	System.out.println("CHART objID: " + objID + " | objectWindowId: " + objectWindowId  + " position " + position);
					chartMap.put(position, vo);
					currentUsedObjects.put(position, ADAMBoxContent.CHART);
				
				
				if ( objID == objectWindowId ) {
				//	System.out.println("CHART objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position + " ADDED ");
//					chartMap.put(position, vo);
//					currentUsedObjects.put(position, ADAMBoxContent.CHART);
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//					if (RootPanel.get(position).getWidgetCount() > 0)
//						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					chartPanel.layout();
					RootPanel.get(position).setStyleName("small-box content");
					RootPanel.get(position).add(chartPanel);
				}
				
			}
			public void onFailure(Throwable e) {
				
				ADAMResultVO vo = new ADAMResultVO();
				
				vo.setDefaultResultVO(vo, q);
				
				VerticalPanel chartPanel = ADAMChartMaker.buildSmallChart(q, vo, position, color, fullScreenChart(q, position, color, q.getTitle()),false, null, null);

				
				if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
				
				RootPanel.get(position).setStyleName("small-box content");
				RootPanel.get(position).add(chartPanel);
				
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	public static SelectionListener<ButtonEvent> fullScreenChart(final ADAMQueryVO qvo, final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeCenter();
//				ADAMVisibilityController.restoreAdamViewVisibility();
				ADAMVisibilityController.styleVisibilityDisplay("CENTER");
//				objectWindowId = objectWindow.getNext();
				ADAMResultVO vo = chartMap.get(position);
				RootPanel.get("CENTER").setStyleName("big-box border  content");
				RootPanel.get("CENTER").add(ADAMChartMaker.buildBigChart(qvo, vo, "CENTER", color,  smallScreenChart(position, color, title)));
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> fullScreenCustomChart(final ADAMQueryVO qvo, final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeCenter();
//				removeBoxes();
				ADAMVisibilityController.restoreAdamViewVisibility();
//				objectWindowId = objectWindow.getNext();
				ADAMResultVO vo = currentCustom.get(position);
				RootPanel.get("CENTER").setStyleName("big-box border  content");
				RootPanel.get("CENTER").add(ADAMChartMaker.buildBigChart(qvo, vo, "CENTER", color, smallScreenChart(position, color, title)));
			}
		};
	}

/*	public static SelectionListener<ButtonEvent> fullScreenQuestionsChart(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeQuestions();
				ADAMVisibilityController.restoreCenterVisibility();
				objectWindowId = objectWindow.getNext();
				//System.out.println("POSITION IS: " + position);
				ADAMResultVO vo = questionsMap.get(position);
				RootPanel.get("CENTER").setStyleName("big-box border  content");
				RootPanel.get("CENTER").add(ADAMChartMaker.buildBigChart(vo, "CENTER", color, smallScreenQuestionsChart(position, color, title)));
			}
		};
	}*/

	public static SelectionListener<ButtonEvent> smallScreenChart(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
//				removeBoxes();

				ADAMController.removeCenter();

//				objectWindowId = objectWindow.getNext();
//				try {
//					ADAMVisibilityController.restoreAdamViewVisibility();
//					RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//				} 
//				catch (Exception e) {
//				}
//				restoreBoxesContent(title);
			}
		};
	}

	/*public static SelectionListener<ButtonEvent> smallScreenQuestionsChart(final String position, final String color, final String title) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeBoxes();
				ADAMVisibilityController.restoreAskADAMVisibility();
				objectWindowId = objectWindow.getNext();
				RootPanel.get(position).setStyleName("question-" + color + "-box content");
				restoreQuestionsContent();
			}
		};
	}*/



	public static void restoreBoxesContent(ADAMQueryVO qvo, String title) {
		RootPanel.get("CENTER").setStyleName("remove-box");
		ADAMVisibilityController.styleVisibilityNoDisplay("CENTER");

	//	System.out.println("MAP IS USED: " + containsMaps);

 		for(String position : currentUsedObjects.keySet() ) {
 			ADAMVisibilityController.styleVisibilityDisplay(position);
 			
 			Boolean putObject = true;
		//	System.out.println("restoreBoxesContent: " + position);
			if ( containsMaps ) {
				if ( position.equals("BOTTOM_LEFT") || position.equals("BOTTOM_RIGHT"))
					putObject = false;
			}
			else {
				if ( position.equals("MAP_LEFT") || position.equals("MAP_RIGHT"))
					putObject = false;
			}
			if ( putObject ) {
				ADAMBoxContent type = currentUsedObjects.get(position);
				switch(type) {
					case TABLE:
						ADAMResultVO tableVo = tableMap.get(position);
						ADAMBoxContent c = ADAMBoxContent.valueOf(tableVo.getOutputType());
//						RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.TABLE) + "-box border content");
						RootPanel.get(position).setStyleName("small-box content");
						RootPanel.get(position).add(ADAMTableMaker.buildSmallTable(tableVo, position, tableVo.getBoxColor(), ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title), false, null, null));
	//					switch (c) {
	//						case TOP_DONORS_TABLE:
	//							RootPanel.get(position).add(ADAMTableMaker.buildSmallTable(tableVo, ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title)));
	//						break;
	//						case TOP_SECTORS_DONOR_VIEW_TABLE:
	//							RootPanel.get(position).add(ADAMTableMaker.buildSmallTopSectorsDonorViewTable(tableVo, ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title)));
	//						break;
	//						case TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE:
	//							RootPanel.get(position).add(ADAMTableMaker.buildSmallTopSectorsDonorViewTable(tableVo, ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title)));
	//						break;
	//					}
					break;

					case CHART:
						ADAMResultVO chartVo = chartMap.get(position);
//						RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.CHART) + "-box border content");
						RootPanel.get(position).setStyleName("small-box content");
						RootPanel.get(position).add(ADAMChartMaker.buildSmallChart(qvo, chartVo, position, chartVo.getBoxColor(), fullScreenChart(qvo, position, chartVo.getBoxColor(), title), false, null, null));
					break;

					case MAP:
					//	System.out.println("restoreBoxesContent mapMap: " + position);
						ADAMResultVO mapVo = mapMap.get(position);
						// TODO: vo needed?
						if ( RootPanel.get(position).getWidgetCount() > 0) {
//							RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.MAP) + "-box border content");
							RootPanel.get(position).setStyleName("small-box content");
							RootPanel.get(position).getWidget(0).setVisible(true);
						}
					break;

					case VENN_PRIORITIES:
					//	System.out.println("restoreBoxesContent vennMap: " + position);
						ADAMResultVO vennVo = vennMap.get(position);
//						RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.VENN) + "-box border content");
						RootPanel.get(position).setStyleName("small-box content");
						RootPanel.get(position).add(ADAMVennMaker.buildSmallPriorityVenn(vennVo.getTitle(), vennVo.getSmallImagePath(), ADAMConstants.SMALL_WIDTH, ADAMConstants.SMALL_HEIGHT, vennVo, position, ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES),ADAMVennController.fullScreenVenn(position, vennVo.getBoxColor(), vennVo.getTitle())));
					break;
					
					
					case CUSTOM_CHART:
					//	System.out.println("restoreBoxesContent customChart: " + position);
						ADAMResultVO customChartVo = currentCustom.get(position);
						RootPanel.get(position).setStyleName("small-box content");
						RootPanel.get(position).add(ADAMChartMaker.buildSmallCustomChart(qvo, customChartVo, fullScreenCustomChart(qvo, position, customChartVo.getBoxColor(), title), position));
					break;
				}
			}

		}
	}
	
	public static void restoreCustomContent() {
		
		Boolean displayCustom = false;
		
		for(int i = 0; i < CUSTOMS; i ++ ) {
		//	System.out.println("restoreCustomContent(): " + "CUSTOM_1" + i );

			if ( currentCustom.containsKey("CUSTOM_1" + i )) {
				displayCustom = true;
				
				String position = "CUSTOM_1" + i ;
				ADAMResultVO customChartVo = currentCustom.get(position);
	
				RootPanel.get(position).setStyleName("small-box content");
				RootPanel.get(position).add(ADAMChartMaker.buildSmallCustomChart(null, customChartVo, fullScreenCustomChart(null, position , customChartVo.getBoxColor(), ""), position));
			}
		}
		
		if ( displayCustom ) {
			ADAMVisibilityController.restoreCustomVisibility(false);
		}
	}
	
	public static HashMap<String, ADAMResultVO> getCurrentViewBoxes() {
		HashMap<String, ADAMResultVO> currentView = new HashMap<String, ADAMResultVO>();
		RootPanel.get("CENTER").setStyleName("remove-box");

		//System.out.println("MAP IS USED: " + containsMaps);

 		for(String position : currentUsedObjects.keySet() ) {
 			Boolean putObject = true;
			//System.out.println("restoreBoxesContent: " + position);
			if ( containsMaps ) {
				if ( position.equals("BOTTOM_LEFT") || position.equals("BOTTOM_RIGHT"))
					putObject = false;
			}
			else {
				if ( position.equals("MAP_LEFT") || position.equals("MAP_RIGHT"))
					putObject = false;
			}
			if ( putObject ) {
				ADAMBoxContent type = currentUsedObjects.get(position);
				switch(type) {
					case TABLE:
						currentView.put(position, tableMap.get(position));
					break;

					case CHART:
						currentView.put(position, chartMap.get(position));
					break;

					case MAP:
						currentView.put(position, mapMap.get(position));
					break;

					case VENN_PRIORITIES:
						currentView.put(position, vennMap.get(position));
					break;
					case CUSTOM_CHART:
						currentView.put(position, currentCustom.get(position));			
					break;
					
				}
			}
			
			
		}
 		return currentView;
	}


	public static ClickHandler openDataManagement(final ADAMToolbar t) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				new DonorProfileDataManagementWindow();		
			}
		};
	}
	public static Listener<ComponentEvent> help() {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				com.google.gwt.user.client.Window.open("adam-docs/adam_user_manual.pdf", "_blank","");
			}
		};
	}
	
	
	public static Listener<ComponentEvent> about() {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				
			  com.google.gwt.user.client.Window.open("adam-docs/about.html", "_blank","width=420,height=340");
			}
		};
	}
	
	
	public static Listener<ComponentEvent> openNotAvailableMessage(final String title, final String message) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				FenixAlert.info(title, message);
			}
		};
	}
	
	public static Listener<ComponentEvent> logIn(final ADAMToolbar t) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				removeSecondaryMenu();
				RootPanel.get("SECONDARY_MENU").add(t.buildLoginMenu());
			}
		};
	}
	
	public static Listener<ComponentEvent> logOut(final ADAMToolbar t) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				removeSecondaryMenu();
				doLogout(t);
			}
		};
	}
	
	private static void doLogout(final ADAMToolbar t) {
		UserServiceEntry.getInstance().logout(new AsyncCallback<String>() {
			public void onSuccess(String result) {
				FenixUser.giveAnonymousRole();
				t.removeLoginItems();
				t.getMainMenuBar().getLayout().layout();
			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
				RootPanel.get("loginMessage").clear();
			}
		});
	}

	public static Command login(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				removeSecondaryMenu();
				RootPanel.get("SECONDARY_MENU").add(t.buildLoginMenu());

			}
		};
	};


	public static Command referencePeriod(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				removeSecondaryMenu();
				RootPanel.get("SECONDARY_MENU").add(t.buildReferencePeriod());
			}
		};
	};

	/*public static Command askADAM(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				removeSecondaryMenu();
				RootPanel.get("SECONDARY_MENU").add(t.buildAskADAMFilters());
			}
		};
	};
*/


	/*public static SelectionListener<ButtonEvent> askADAM(final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeAllContents(true);
				ADAMVisibilityController.restoreAskADAMVisibility();

				currentVIEW = ADAMCurrentVIEW.ASKADAM;

				objectWindowId = objectWindow.getNext();

			//	System.out.println("askADAM objectWindowId: " + objectWindowId);
			//	System.out.println("currentVIEW: " + currentVIEW);


				GaulModelData selectedEntity = entryList.getSelection().get(0);
				String entity = selectedEntity.getGaulCode();

				GaulModelData codesEntity = adamCodesList.getSelection().get(0);
				String code = codesEntity.getGaulCode().substring(1 + codesEntity.getGaulCode().indexOf('_'));
				String label = codesEntity.getGaulLabel();

				Map<String, String> codes = new HashMap<String, String>();
				codes.put(code, label);

				//System.out.println("askADAM: " + entity + " | " + code + " | " + label);

				HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

				if ( entity.equalsIgnoreCase("Gaul_")) {
					Boolean countryAdded = addCountryFilter(filters, codes);
				//	System.out.println("askADAM countryAdded: " + countryAdded + " | " + filters);
				}

				else if ( entity.equalsIgnoreCase("Donor_")) {
					Boolean donorAdded = addDonorFilter(filters, codes);
				//	System.out.println("askADAM donorAdded: " + donorAdded + " | " + filters);
				}

				ADAMQuestionMaker.buildQuestions(entity, code, label, filters, baseDate, entity, objectWindowId);
			}
		};
	}*/

	public static SelectionListener<ButtonEvent> changeReferencePeriod(final ComboBox<GaulModelData> fromDateList, final ComboBox<GaulModelData> toDateList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				changeReferencePeriodAgent(fromDateList, toDateList);
			}
		};
	}

	public static SelectionChangedListener<GaulModelData> changeReferencePeriodListener(final ComboBox<GaulModelData> fromDateList, final ComboBox<GaulModelData> toDateList) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				changeReferencePeriodAgent(fromDateList, toDateList);
			}
		};
	}
	
	public static void changeReferencePeriodAgent(final ComboBox<GaulModelData> fromDateList, final ComboBox<GaulModelData> toDateList) {

				System.out.println("fromDateList.getValue().getGaulCode():" + fromDateList);
				System.out.println("toDateList.getValue().getGaulCode():" + toDateList);



	 			Date fromDate = FieldParser.parseDate(fromDateList.getValue().getGaulCode());
	 			Date toDate =  FieldParser.parseDate(toDateList.getValue().getGaulCode());

	 			//System.out.println("ok");

	 			Boolean updateView = true;

	 			// error message
	 			if ( fromDate.compareTo(toDate) > 0 ) {
	 				updateView = false;
	 				FenixAlert.error("Select date", ("Date selection is wrong"));
//	 				Window.alert("Date selection is wrong");

	 			}
	 			else if ( fromDate.compareTo(toDate) == 0 ) {
	 				baseDate.clear();
					baseDate.add(toDateList.getValue().getGaulCode());

	 			}
	 			else if ( fromDate.compareTo(toDate) < 0 ) {
	 				baseDate.clear();

	 				while( fromDate.compareTo(toDate) <= 0 ) {
	 					//System.out.println("dates: " + fromDate + " | " + toDate);

	 					baseDate.add(FieldParser.formatDate(fromDate, "formatterMinusReverse"));

	 					fromDate = new Date(fromDate.getYear() + 1, fromDate.getMonth(), fromDate.getDate());
	 				}

	 			}

	 			System.out.println("basedate: " + baseDate);
	 			
	 			referencePeriodString = getStringReferenceDate();

	 			if ( updateView )
	 				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMBoxMaker.subSectorsSelected, currentVIEW, "changeReferencePeriodAgent", selectedTab);
//	 				callView(ADAMBoxMaker.getDonorList(), ADAMBoxMaker.getGaulList(), ADAMBoxMaker.getSectorList());

			}


	public static Command upload(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				removeSecondaryMenu();
				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
					new ExcelImporterWindow().build();
				} else {
					FenixAlert.alert(BabelFish.print().error(), "You need to sign in before uploading a new dataset");
				}
			}
		};
	};

	
	public static ClickHandler upload2(final ADAMToolbar t) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				removeSecondaryMenu();
				Info.display("onclick", "onclick");
				RootPanel.get("SECONDARY_MENU").add(t.buildLoginMenu());removeSecondaryMenu();
				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
					new ExcelImporterWindow().build();
				} else {
					FenixAlert.alert(BabelFish.print().error(), "You need to sign in before uploading a new dataset");
				}
			}
		};
	}
	public static Command uploadCS(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				removeSecondaryMenu();
				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
					new CodeListImporterWindow().build();
				} else {
					FenixAlert.alert(BabelFish.print().error(), "You need to sign in before uploading a new coding system");
				}
			}
		};
	};

	@SuppressWarnings("unchecked")
	public static SelectionListener<IconButtonEvent> loginListener(final ADAMToolbar t, final TextField<String> username, final TextField<String> password) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				//<String> username = (TextField<String>)secondaryMenuBar.getData("USERNAME");
				//TextField<String> password = (TextField<String>)secondaryMenuBar.getData("PASSWORD");
			
				UserServiceEntry.getInstance().login(username.getValue(), password.getValue(), new AsyncCallback<LoginResponseVo>() {
					public void onSuccess(LoginResponseVo vo) {
						removeSecondaryMenu();
						if (vo.isSucceeded()) {
							String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();
							String welcomeMessage = "  " + BabelFish.print().welcome() + " <b>" + firstAndLastName + "</b>";
							FenixUser.populateRoles(vo.getFenixUserVo());
							RootPanel.get("SECONDARY_MENU").add(new HTML(welcomeMessage));
							
							t.addLoginItems();
							t.getMainMenuBar().getLayout().layout();

						//	RootPanel.get("loginMessage").setStyleName("link");
						} else {
							String message = vo.getResponseMessage();
							if (vo.getResponseMessage().equals("UsernameNotFoundException"))
								message = BabelFish.print().UsernameNotFoundException();
							if (vo.getResponseMessage().equals("BadCredentialsException"))
								message = BabelFish.print().BadCredentialsException();
							if (vo.getResponseMessage().equals("AuthenticationException"))
								message = BabelFish.print().AuthenticationException();
							if (vo.getResponseMessage().equals("DisabledException"))
								message =  BabelFish.print().UserGroupDisabledException();;
							//RootPanel.get("loginMessage").add(new HTML(message));
							//RootPanel.get("loginMessage").setStyleName("link");
								
							ClickHtml html = new ClickHtml();
							html.setHtml(message + " <b>[click to cancel]</b>");
							html.setStyleAttribute("cursor", "pointer");
							html.addListener(Events.OnClick, removeLogInMessage());
							
							RootPanel.get("SECONDARY_MENU").add(html);
						}
						

						
					}
					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().error(), e.getMessage());
					}
				});
			}
		};
	}
	
	private static Listener<BaseEvent> removeLogInMessage() {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
				
				removeSecondaryMenu();
			        	
			}
		};
	}
	
	public static void fillSelectorStoreADAMBox(final String prefix, final ListStore<GaulModelData> store, final ComboBox<GaulModelData> comboBox, final String selectedCodeFromURL, final ADAMSelectedDataset currentlySelectedDatasetCode, final String calledfrom, final SelectionChangedListener<GaulModelData> comboBoxListener) {
		
		// an async call is required
		tokensAsync = tokensAsync +1;
		 System.out.println("%%%%%%% fillSelectorStoreADAMBox : tokensAsync: " + tokensAsync + " | selectedCodeFromURL "+selectedCodeFromURL);
		
		final String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		
		comboBox.removeAllListeners();
		
		ADAMServiceEntry.getInstance().findAll(prefix, codingSystemCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
						
				//System.out.println("fillSelectorStoreADAMBox A: find all! vos: " + vos.size() + "prefix " + prefix);
				if(prefix.equals("Gaul_") || prefix.equals("Donor_") || prefix.equals("Dac_") || prefix.equals("SubDac_") || prefix.equals("SO_") || prefix.equals("OR_")) {	
				 if(store.getCount() == 2 || store.getCount() == 1){
					for (CodeVo vo : vos) {
						GaulModelData g = new GaulModelData();
						g.setGaulCode(vo.getCode());
						g.setGaulLabel(vo.getLabel());
						store.add(g);
					}
					}
				}

				if ( selectedCodeFromURL != null ){
					
					
					GaulModelData modelByCode = store.findModel("gaulCode", selectedCodeFromURL);
					GaulModelData modelByLabel = store.findModel("gaulLabel", selectedCodeFromURL);
					
					System.out.println("fillSelectorStoreADAMBox: modelByCode = "+modelByCode);
					System.out.println("fillSelectorStoreADAMBox: modelByLabel = "+modelByCode);
					
					if(modelByCode!=null) {
						comboBox.addSelectionChangedListener(comboBoxListener);
						comboBox.setValue(modelByCode);
					}	
					else if(modelByLabel!=null) {
						comboBox.addSelectionChangedListener(comboBoxListener);
						comboBox.setValue(modelByLabel);
					}	
					else {
						comboBox.setValue(store.getAt(0));
						comboBox.addSelectionChangedListener(comboBoxListener);
					}	
					
					//System.out.println(" ^^^^^^^ fillSelectorStoreADAMBox: (IF) SET VALUE "+comboBox.getValue().getGaulCode());
					//comboBox.addSelectionChangedListener(comboBoxListener);
					
				} else {
					//System.out.println(" ^^^^^^^ fillSelectorStoreADAMBox: SELECTED URL NULL");
					//set Default Value = "FAO-Related Sub Sectors" and code "Dac_9999"	
					GaulModelData model = store.findModel("gaulCode", "Dac_9999");
					
					
					if(model!=null){
						comboBox.addSelectionChangedListener(comboBoxListener);
						comboBox.setValue(model);
						} else{
							comboBox.setValue(store.getAt(0));
							comboBox.addSelectionChangedListener(comboBoxListener);
				  	}
					
				}
				
				tokensReached++;
				 System.out.println("%%%%%%% CALL SUCCESS to findAll ENDED : tokensReached: " + tokensReached);
				
			   //System.out.println(" ^^^^^^^ fillSelectorStoreADAMBox: END .... CALLED FROM '"+calledfrom+"'" +" comboBox has listeners =  " +  comboBox.hasListeners());
			
				
				//if(!comboBox.hasListeners())
					//comboBox.addSelectionChangedListener(comboBoxListener);

				//System.out.println(" ^^^^^^^ fillSelectorStoreADAMBox: END .... CALLED FROM '"+calledfrom+"' codingSystemCode "+codingSystemCode + " | "+prefix + " | selectedDataset "+currentlySelectedDatasetCode +  " | store.getCount() "+ store.getCount());
				
			}
			public void onFailure(Throwable e) {
				tokensReached++;
				 System.out.println("%%%%%%% CALL FAILURE to findAll ENDED : tokensReached: " + tokensReached);
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	
 public static void fillHierarchicalSelectorStoreADAMBox(final String prefix, final ListStore<GaulModelData> store, final ComboBox<GaulModelData> comboBox, final String selectedCodeFromURL, List<String> parents, final ADAMSelectedDataset currentlySelectedDatasetCode, final SelectionChangedListener<GaulModelData> comboBoxListener) {
	//System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox  prefix: "+prefix + " : selectedCodeFromURL = "+selectedCodeFromURL);
	
	 // an async call is required
		tokensAsync = tokensAsync +1;
		 System.out.println("$$$$$$$ fillHierarchicalSelectorStoreADAMBox : tokensAsync: " + tokensAsync);
		
	 if(parents.size() > 0){
		 comboBox.removeAllListeners();
		 
		 String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		// System.out.println(" fillHierarchicalSelectorStoreADAMBox: codingSystemCode "+codingSystemCode + " | "+prefix);
					
		 if(!parents.contains("GLOBAL")){ 
			 	
			// System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox !parents.contains(GLOBAL) prefix:"+prefix + ":");
			 ADAMServiceEntry.getInstance().findChildren(prefix, parents, codingSystemCode, new AsyncCallback<List<CodeVo>>() {
				 public void onSuccess(List<CodeVo> vos) {
					// System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox FIND CHILDREN size "+vos.size());
					 for (CodeVo vo : vos) {
						 GaulModelData g = new GaulModelData();
						 g.setGaulCode(vo.getCode());
						 g.setGaulLabel(vo.getLabel());
						 store.add(g);
					 }
					// System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox  prefix:"+prefix + ": AFTER store count = "+store.getCount());
						
					 if ( selectedCodeFromURL != null ){
						// System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox prefix:"+prefix + ": selectedCodeFromURL =  "+selectedCodeFromURL);
							
						 comboBox.disableEvents(true);
						 if(store.findModel("gaulCode", selectedCodeFromURL)!=null)
							 comboBox.setValue(store.findModel("gaulCode", selectedCodeFromURL));
						 else
							 comboBox.setValue(store.getAt(0));
	
						 comboBox.disableEvents(false);
						 
						 
					 } else {
					//	 System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox prefix:"+prefix + ": selectedCodeFromURL IS NULL A store count  ="+store.getCount());
						 comboBox.disableEvents(true);	
						 comboBox.setValue(store.getAt(0));
						 comboBox.disableEvents(false);
						// System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox  prefix:"+prefix + ": selectedCodeFromURL IS NULL B store count = "+store.getCount());				
					 }
					 
				//	 System.out.println("&&&&&& fillHierarchicalSelectorStoreADAMBox  prefix:"+prefix + ": FINAL store count = "+store.getCount());
					 tokensReached++;
					 System.out.println("$$$$$$$ CALL SUCCESS to findChildren ENDED : tokensReached: " + tokensReached);
				 }
				 public void onFailure(Throwable e) {
					 tokensReached++;
					 System.out.println("$$$$$$$ CALL FAILURE to findChildren ENDED : tokensReached: " + tokensReached);
					 FenixAlert.error(BabelFish.print().error(), e.getMessage());
				 }
			 });
		 } else {
			 // get all items
			 //System.out.println("$$$$ fillHierarchicalSelectorStoreADAMBox parents.contains(GLOBAL)");
			 ADAMServiceEntry.getInstance().findAll(prefix, codingSystemCode, new AsyncCallback<List<CodeVo>>() {
				 public void onSuccess(List<CodeVo> vos) {
					// System.out.println("$$$$ fillHierarchicalSelectorStoreADAMBox FIND ALL size "+vos.size());
						
					 for (CodeVo vo : vos) {
						 GaulModelData g = new GaulModelData();
						 g.setGaulCode(vo.getCode());
						 g.setGaulLabel(vo.getLabel());
						 store.add(g);
					 }

						
						
					 if ( selectedCodeFromURL != null ){
						 comboBox.disableEvents(true);
						 if(store.findModel("gaulCode", selectedCodeFromURL)!=null)
							 comboBox.setValue(store.findModel("gaulCode", selectedCodeFromURL));
						 else
							 comboBox.setValue(store.getAt(0));

						 comboBox.disableEvents(false);
					 } else {
						 comboBox.setValue(store.getAt(0));
					 }

					 tokensReached++;
					 System.out.println("$$$$$$$ CALL SUCCESS to findAll ENDED : tokensReached: " + tokensReached);
				 }
				 public void onFailure(Throwable e) {
					 tokensReached++;
					 System.out.println("$$$$$$$ CALL FAILURE to findAll ENDED : tokensReached: " + tokensReached);
					 FenixAlert.error(BabelFish.print().error(), e.getMessage());
				 }
			 });
		 }
		 comboBox.addSelectionChangedListener(comboBoxListener);
		 
	 } else
		 System.out.println("fillHierarchicalSelectorStoreADAMBox parents "+parents.size());
 }
 


	public static void fillSelectorStore(final String prefix, final ListStore<GaulModelData> store, final ComboBox<GaulModelData> adamCodesList) {
		 String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
			
		 
		 System.out.println(" fillSelectorStore: codingSystemCode "+codingSystemCode + " | "+prefix);
			
		ADAMServiceEntry.getInstance().findAll(prefix, codingSystemCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos) {
					GaulModelData g = new GaulModelData();
					g.setGaulCode(vo.getCode());
					g.setGaulLabel(vo.getLabel());
					store.add(g);
				}
				adamCodesList.setValue(store.getAt(0));
			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	
	

	public static SelectionChangedListener<GaulModelData> entityListener(final ComboBox<GaulModelData> entityList, final ListStore<GaulModelData> store, final ComboBox<GaulModelData> adamCodesList) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				String prefix = entityList.getSelection().get(0).getGaulCode();
				if (store.getCount() > 0)
					store.removeAll();
				adamCodesList.clearSelections();
				fillSelectorStore(prefix, store, adamCodesList);
				
			}
		};
	}

	public static SelectionChangedListener<GaulModelData> updateEntity(final ADAMComboMultiSelection adamComboMultiSelection, final ComboBox<GaulModelData> list, final Map<String, String> map, final Map<String, String> hierarchyMap, final String type, final String calledFrom) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				System.out.println("########### updateEntity calledFrom <<" + calledFrom + ">>" + " ADAMBoxMaker.selectedCountryFromURL = "+ADAMBoxMaker.selectedCountryFromURL +" | " +type);
				map.clear();
				List<String> parent = new ArrayList<String>();
				
				String code = list.getSelection().get(0).getGaulCode();
				String label = list.getSelection().get(0).getGaulLabel();
				
				// Dac_9999 = Agricultural/FAO Related Sub Sectors 
				if(type.equalsIgnoreCase("SECTOR") && code.contains("9999"))
					ADAMBoxMaker.faoSectorAdded = true;
				else
					ADAMBoxMaker.faoSectorAdded = false;
				
				//System.out.println("--------------------------> ADDING: " + code + " | " + label + " | " + type);
				
				if ( !code.equalsIgnoreCase("MULTI") ) {
					// clean the hierarchyMap
					if ( hierarchyMap != null ) {
						hierarchyMap.clear();
					}
					map.put(code, label);

					//Sector/OR selection results in a corresponding  re-population of Sub-sector/OR lists
					if(type.equals("SECTOR")) {		
						//1. check if a sub-sector is already selected, if so than see if it is a child of the sector, to allow the value to remain
						//pre-selected	
							boolean selectedSubSectorIsRelatedToParent = false;
							String selectedSubSector = ADAMBoxMaker.subSectorList.getValue().getGaulCode();

							//System.out.println("--------------------------> selectedSector: " + code);
							
							ADAMBoxMaker.subSectorStore.removeAll();
							ADAMBoxMaker.subSectorStore.add(ADAMBoxMaker.defaultCodes(true));

							parent.clear();
							
					  	   // For FAO Related Sectors
							if(code.contains("9999")) {									
								for(String fao: ADAMConstants.faoViewPurposes.keySet()){
									if(selectedSubSector!=null && selectedSubSector.contains(fao)) {
										selectedSubSectorIsRelatedToParent = true;
										ADAMBoxMaker.selectedSubSectorFromURL = selectedSubSector;
									    break;
									}  
								}
								
								// Fill with all the Agriculture related purpose codes
								for(String fao: ADAMConstants.faoViewPurposes.keySet()){
									parent.add("%_"+fao);
								}	
							}
							// For other sectors
							else {
								String parentCode = getCode(code);
								parent.add(parentCode);
								
								if(selectedSubSector.contains(parentCode)) {
									ADAMBoxMaker.selectedSubSectorFromURL = selectedSubSector;
									selectedSubSectorIsRelatedToParent = true;
								} else
									selectedSubSectorIsRelatedToParent = false;
							}
							
							if(selectedSubSectorIsRelatedToParent==false){
								ADAMBoxMaker.selectedSubSectorFromURL = null;
								ADAMBoxMaker.subSectorList.setValue(ADAMBoxMaker.subSectorList.getStore().getAt(0));
							}
							
							if(code.equals("GLOBAL")) {
								//System.out.println("&&&&&&&&&& UpdateEntity: Sector = Global CALL TO fillSelectorStoreADAMBox Sub_DAC ...");
								ADAMController.fillSelectorStoreADAMBox("SubDac_", ADAMBoxMaker.subSectorStore, ADAMBoxMaker.subSectorList, ADAMBoxMaker.selectedSubSectorFromURL, currentlySelectedDatasetCode, "UPDATE-ENTITY-DAC", ADAMBoxMaker.subSectorListListener);
							
							}else
								ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", ADAMBoxMaker.subSectorStore, ADAMBoxMaker.subSectorList, ADAMBoxMaker.selectedSubSectorFromURL, parent, currentlySelectedDatasetCode, ADAMBoxMaker.subSectorListListener);
					 
					} else if(type.equals("SUB-SECTOR")) {		
						
						//check if a sector is already selected, if so only when sector is GLOBAL, get all sub-sectors
						    boolean selectedSubSectorIsRelatedToParent = false;
						    
						
							String selectedSector = ADAMBoxMaker.sectorList.getValue().getGaulCode();
							//System.out.println("--------------------------> selectedSector: " + selectedSector);
							
						
							if(selectedSector=="GLOBAL") {
								ADAMBoxMaker.subSectorStore.removeAll();
								ADAMBoxMaker.subSectorStore.add(ADAMBoxMaker.defaultCodes(true));
								ADAMBoxMaker.selectedSubSectorFromURL = code;
								parent.clear();
								
								parent.add("GLOBAL");

								ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", ADAMBoxMaker.subSectorStore, ADAMBoxMaker.subSectorList, ADAMBoxMaker.selectedSubSectorFromURL, parent, currentlySelectedDatasetCode, ADAMBoxMaker.subSectorListListener);
							} /*else {
								if(selectedSector.contains("9999")) {									
									for(String fao: ADAMConstants.faoViewPurposes.keySet()){
										if(code!=null && code.contains(fao)) {
											selectedSubSectorIsRelatedToParent = true;
											ADAMBoxMaker.selectedSubSectorFromURL = code;
										    break;
										}  
									}
									
									// Fill with all the Agriculture related purpose codes
									for(String fao: ADAMConstants.faoViewPurposes.keySet()){
										parent.add("%_"+fao);
									}	
								}
								// For other sectors
								else {
									String parentCode = getCode(selectedSector);
									parent.add(parentCode);
									
									if(code.contains(parentCode)) {
										ADAMBoxMaker.selectedSubSectorFromURL = code;
										selectedSubSectorIsRelatedToParent = true;
									} else
										selectedSubSectorIsRelatedToParent = false;
								}
								
								if(selectedSubSectorIsRelatedToParent==false){
									ADAMBoxMaker.selectedSubSectorFromURL = null;
									ADAMBoxMaker.subSectorList.setValue(ADAMBoxMaker.subSectorList.getStore().getAt(0));
								}
								
								System.out.println("--------------------------> ADAMBoxMaker.selectedSubSectorFromURL: " + ADAMBoxMaker.selectedSubSectorFromURL );
								
								ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", ADAMBoxMaker.subSectorStore, ADAMBoxMaker.subSectorList, ADAMBoxMaker.selectedSubSectorFromURL, parent);
					
							}
					}
				/*	if(type.equals("SECTOR")) {
						
						System.out.println("--------------------------> Selected SUB_SECTOR : " +ADAMBoxMaker.subSectorList.getValue().getGaulCode());
						
						if(ADAMBoxMaker.subSectorList.getValue().getGaulCode()!="GLOBAL" && ADAMBoxMaker.subSectorList.getValue().getGaulCode()!="MULTI") {
							ADAMBoxMaker.selectedSubSectorFromURL = ADAMBoxMaker.subSectorList.getValue().getGaulCode();

							ADAMBoxMaker.subSectorStore.removeAll();
							ADAMBoxMaker.subSectorStore.add(ADAMBoxMaker.defaultCodes(true));

							parent.clear();

							boolean selectedSubSectorIsRelatedToParent = false;
							
							if(code.contains("9999")) {	
								
								for(String fao: ADAMConstants.faoViewPurposes.keySet()){
									if(ADAMBoxMaker.selectedSubSectorFromURL.contains(fao)) {
										selectedSubSectorIsRelatedToParent = true;
									    break;
									}  
								}
								
								for(String fao: ADAMConstants.faoViewPurposes.keySet()){
									parent.add("%_"+fao);
								}	
							}
							else {
								String parentCode = getCode(code);
								parent.add(parentCode);
								if(ADAMBoxMaker.selectedSubSectorFromURL.contains(parentCode)) {
									selectedSubSectorIsRelatedToParent = true;
								} else
									selectedSubSectorIsRelatedToParent = false;
							}
							
							if(selectedSubSectorIsRelatedToParent==false){
								ADAMBoxMaker.subSectorList.setValue(ADAMBoxMaker.subSectorList.getStore().getAt(0));
							}
							
							ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", ADAMBoxMaker.subSectorStore, ADAMBoxMaker.subSectorList, ADAMBoxMaker.selectedSubSectorFromURL, parent);
						} else if(ADAMBoxMaker.subSectorList.getValue().getGaulCode().equals("GLOBAL")){
							ADAMBoxMaker.subSectorStore.removeAll();
							ADAMBoxMaker.subSectorStore.add(ADAMBoxMaker.defaultCodes(true));

							parent.clear();

							if(code.contains("9999")) {							
								for(String fao: ADAMConstants.faoViewPurposes.keySet())
									parent.add("%_"+fao);	
							}

							else 
								parent.add(getCode(code));

							ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", ADAMBoxMaker.subSectorStore, ADAMBoxMaker.subSectorList, ADAMBoxMaker.selectedSubSectorFromURL, parent);
						}*/
					}
					if(type.equals("SO")) {		
						//1. check if a OR is already selected, if so than see if it is a child of the SO, to allow the value to remain
						//pre-selected	
						boolean selectedORIsRelatedToParent = false;
						String selectedOR = ADAMBoxMaker.orList.getValue().getGaulCode();

					     System.out.println("--------------------------> selectedOR: " + selectedOR + " selected SO = "+code);

						ADAMBoxMaker.orStore.removeAll();
						ADAMBoxMaker.orStore.add(ADAMBoxMaker.defaultCodes(true));

						//System.out.println("--------------------------> OR store: " + ADAMBoxMaker.orStore.getCount());
						
						parent.clear();


						String parentCode = getCode(code);
						parent.add(parentCode);

						if(selectedOR.contains(parentCode)) {
							ADAMBoxMaker.selectedORFromURL = selectedOR;
							selectedORIsRelatedToParent = true;
						} else
							selectedORIsRelatedToParent = false;


						if(selectedORIsRelatedToParent==false){
							ADAMBoxMaker.selectedORFromURL = null;
							ADAMBoxMaker.orList.setValue(ADAMBoxMaker.orList.getStore().getAt(0));
						}

						if(code.equals("GLOBAL")) {
							System.out.println("&&&&&&&&&& UpdateEntity: SO = Global CALL TO fillSelectorStoreADAMBox OR ...");
							ADAMController.fillSelectorStoreADAMBox("OR_", ADAMBoxMaker.orStore, ADAMBoxMaker.orList, ADAMBoxMaker.selectedORFromURL, currentlySelectedDatasetCode, "UPDATE-ENTITY-SO", ADAMBoxMaker.orListListener);
						}else
							ADAMController.fillHierarchicalSelectorStoreADAMBox("OR_", ADAMBoxMaker.orStore, ADAMBoxMaker.orList, ADAMBoxMaker.selectedORFromURL, parent, currentlySelectedDatasetCode, ADAMBoxMaker.orListListener);

					} else if(type.equals("OR")) {		
						//check if a SO is already selected, if so only when SO is GLOBAL, get all ORS
						String selectedSO = ADAMBoxMaker.soList.getValue().getGaulCode();
						//System.out.println("--------------------------> selectedSO: " + selectedSO);
						//ADAMBoxMaker.orStore.removeAll();
						//ADAMBoxMaker.orStore.add(ADAMBoxMaker.defaultCodes(true));

						
						if(selectedSO=="GLOBAL" && ADAMBoxMaker.selectedORFromURL!=null) {
							ADAMBoxMaker.orStore.removeAll();
							ADAMBoxMaker.orStore.add(ADAMBoxMaker.defaultCodes(true));

							parent.clear();

							parent.add("GLOBAL");

							ADAMController.fillHierarchicalSelectorStoreADAMBox("OR_", ADAMBoxMaker.orStore, ADAMBoxMaker.orList, ADAMBoxMaker.selectedORFromURL, parent, currentlySelectedDatasetCode, ADAMBoxMaker.orListListener);
						} 
					} /*else if(type.equals("COUNTRY")){
						System.out.println("ADAMBoxMaker.gaulStore.findModel(gaulCode = "+ADAMBoxMaker.selectedCountryFromURL+ ")" +ADAMBoxMaker.gaulStore.findModel("gaulCode", ADAMBoxMaker.selectedCountryFromURL));
						
						for(GaulModelData model: ADAMBoxMaker.gaulStore.getModels()){
							if(model.getGaulLabel().equalsIgnoreCase("Argentina"))
								System.out.println(model.getGaulLabel() + " " +model.getGaulCode());
							
							
						}
						if(ADAMBoxMaker.selectedCountryFromURL!=null){
							ADAMBoxMaker.gaulList.setValue(ADAMBoxMaker.gaulStore.findModel("gaulCode", ADAMBoxMaker.selectedCountryFromURL));
						}
					}*/
		
					callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMBoxMaker.subSectorsSelected, currentVIEW, "updateEntity", selectedTab);
				}
				else if (code.equalsIgnoreCase("MULTI")) {
					ADAMMultiSelectionController.addMultiSelectionPanel(type, adamComboMultiSelection);
				}		
			}
		};
	}
	
	
	



//	public static SelectionChangedListener<GaulModelData> updateEntityByCountry(final ComboBox<GaulModelData> donorList, final ComboBox<GaulModelData> gaulList, final ComboBox<GaulModelData> sectorList) {
//		return new SelectionChangedListener<GaulModelData>() {
//			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
//
//
//				callView(donorList, gaulList, sectorList);
//			}
//		};
//	}


	public static SelectionListener<ButtonEvent> updateEnties() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMBoxMaker.subSectorsSelected, currentVIEW, "updateEntities", selectedTab);
			}
		};
	}

	
//	public static void callView(final ComboBox<GaulModelData> donorList, final ComboBox<GaulModelData> gaulList, final ComboBox<GaulModelData> sectorList) {
	public static void callView(final Map<String, String> donorList, final Map<String, String> gaulList, final Map<String, String> sectorList, final Map<String, String> subSectorList, final ADAMCurrentVIEW view, String calledFrom, final String tab) {
		 currentVIEW = view;
		 selectedTab = tab;
		
				
		 System.out.println("!!!!!!!!!! CALL VIEW CALLED FROM <"+calledFrom+">   !!!!!!" + currentVIEW +" - "+ selectedTab + " | ADAMSwitchSelectionPanel.isBuild = "+ ADAMSwitchClassificationPanel.isBuild);

		 ADAMBoxController.resizeObjects();
		
		 //removeAllContents(false);
		 if(currentVIEW.equals(ADAMCurrentVIEW.HOME)) {
			 ADAMAnalyseDataController.analyseView = null;				
			 createHomeView();
		 } else if(currentVIEW.equals(ADAMCurrentVIEW.LINKS)) {
			 ADAMAnalyseDataController.analyseView = null;				
			 createLinksView();
		 } else {
		 
             removeAllContents(false);
	
         
			 if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) && selectedTab.equals(ADAMCurrentVIEW.BROWSE.name())){
				 ADAMAnalyseDataController.analyseView = null;
				 ADAMViewController.callADAMView();	
			 } 	 
			 else if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) && selectedTab.contains(ADAMCurrentVIEW.ANALYSE.name())){
				
				System.out.println("$$$$$$$$$$$ tokensAsync "+ tokensAsync + " | "+tokensReached);
				ADAMAnalyseDataController.createAnalyseDataView();		
				//if ( tokensAsync > 0)
				// ADAMAnalyseDataController.checkIfAllTheAsyncCallsAreReturned("ADAMVIEW-ANALYSEDATA");
					
				/* if ( tokensAsync > 0 && (tokensAsync == tokensReached))
					 ADAMAnalyseDataController.checkIfAllTheAsyncCallsAreReturned("ADAMVIEW-ANALYSEDATA");
				*/ //ADAMAnalyseDataController.createAnalyseDataView();			 
			 }
			 if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) && selectedTab.equals(ADAMCurrentVIEW.BROWSE.name())){
				 ADAMAnalyseDataController.analyseView = null;
				 ADAMFAOViewController.callFAOView();
			 }
			 else if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) && selectedTab.contains(ADAMCurrentVIEW.ANALYSE.name())){
				ADAMAnalyseDataController.createAnalyseDataView();	
				//if ( tokensAsync > 0)
					// ADAMAnalyseDataController.checkIfAllTheAsyncCallsAreReturned("FAOVIEW-ANALYSEDATA");
			 }
			/* else if(currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX)){
				 ADAMAnalyseDataController.analyseView = null;
				 HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

				 Boolean countryAdded = addCountryFilter(filters, gaulList);
			    // System.out.println("countryAded: " + countryAdded);
				 Boolean donorAdded = addDonorFilter(filters, donorList);
				// System.out.println("donorAdded: " + donorAdded);
				 Boolean sectorAdded =  addSectorFilter(filters, sectorList);
				// System.out.println("sectorAdded: " + sectorAdded);

				 System.out.println("filters: " + filters);
				DonorMatrixController.createDonorMatrixView(donorList, gaulList, filters);
				 
			 }*/ else if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)){
				 ADAMAnalyseDataController.analyseView = null;
				 HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

				 Boolean countryAdded = addCountryFilter(filters, gaulList);
				// System.out.println("countryAdded: " + countryAdded);
				 Boolean donorAdded = addDonorFilter(filters, donorList);
				// System.out.println("donorAdded: " + donorAdded);
				 Boolean sectorAdded =  addSectorFilter(filters, sectorList);
				// System.out.println("sectorAdded: " + sectorAdded);

				 System.out.println("filters: " + filters);
				 createDonorProfileView(donorList, filters);
			 }
		 }
	}

	private static void setGlobalMapInvisible() {
		ADAMMapWrapper adamMapWrapper = ADAMWrapper.getAdamMapsWrapper().get("global");
		adamMapWrapper.getPanel().setVisible(false);
	}


	/*public static void createDonorMatrixView(final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters){
		ADAMController.containsMaps = false;
		objectWindowId = objectWindow.getNext();

		addViewMenu("Resource Partner Matrix:", gaulList, donorList, null, null, null);
		
		ADAMDonorMatrixVO vo = DonorMatrixController.createDonorMatrixVO(donorList, gaulList, filters, baseDate);		
		DonorMatrixController.addDonorMatrix(vo);
	}*/
	
	private static void createHomeView(){
		ADAMController.containsMaps = false;
		objectWindowId = objectWindow.getNext();

		ADAMVisibilityController.removeViewMenuContent();
		ADAMVisibilityController.removeAnalyseViewMenuContent();
		
		showHomePage();
	}
	
	private static void createLinksView(){
		ADAMController.containsMaps = false;
		objectWindowId = objectWindow.getNext();

		ADAMVisibilityController.removeViewMenuContent();
		ADAMVisibilityController.removeAnalyseViewMenuContent();
		
		showLinksPage();
	}
	
	private static void createDonorProfileView(final Map<String, String> donorList, HashMap<String, List<String>> filters){
		ADAMController.containsMaps = false;
		objectWindowId = objectWindow.getNext();
		
		ADAMVisibilityController.removeAnalyseViewMenuContent();
		ADAMVisibilityController.removeViewMenuContent();
		ADAMVisibilityController.restoreViewMenuVisibility();
		
		
		//addViewMenu("Resource Partner Profile:", null, donorList, null);
		
		DonorProfileController.addDonorProfile(donorList);

	}
	
	public static void createDonorProfileViewFromMatrix(final ADAMDonorMatrixVO vo, final List<DonorMatrixModel> matrixModels, final Map<String, String> donorList, HashMap<String, List<String>> filters, final String donorCode, final String donorLabel){
		ADAMController.containsMaps = false;
		objectWindowId = objectWindow.getNext();

		ADAMVisibilityController.removeAnalyseViewMenuContent();
		ADAMVisibilityController.removeViewMenuContent();
		ADAMVisibilityController.restoreViewMenuVisibility();
		
		DonorProfileController.addDonorProfile(vo, matrixModels, donorList, donorCode, donorLabel);
	}

	public static Boolean addCountryFilter(final HashMap<String, List<String>> filters, final Map<String, String> gaulList) {

		Boolean added = false;
		List<String> filter = new ArrayList<String>();
		System.out.println("addCountryFilter ... ");
		
		for(String key : gaulList.keySet()) {
			String code = key;
			String label = gaulList.get(key);
			System.out.println("gaulCode: " + code + " gaulLabel: "+label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				added = true;
				code = getCode(code);
				filter.add(code);
			}
		}
		if ( !filter.isEmpty())
			ADAMQueryVOBuilder.addRecipientFilters(filters, filter);

		return added;
	}


	public static Boolean addDonorFilter(final HashMap<String, List<String>> filters, final Map<String, String> donorList) {

		Boolean added = false;
		List<String> filter = new ArrayList<String>();
		System.out.println("addDonorFilter ... ");
		for(String key : donorList.keySet()) {
			String code = key;
			String label = donorList.get(key);
			System.out.println("donorCode: " + code + " donorLabel: "+label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				added = true;
				code = getCode(code);
				filter.add(code);
			}
		}
		if ( !filter.isEmpty())
			ADAMQueryVOBuilder.addDonorFilters(filters, filter);

		return added;
	}

	public static Boolean addSectorFilter(final HashMap<String, List<String>> filters, final Map<String, String> sectorList) {

		Boolean added = false;
		List<String> filter = new ArrayList<String>();
		System.out.println("addSectorFilter ... ");
		for(String key : sectorList.keySet()) {
			String code = key;
			String label = sectorList.get(key);
			System.out.println("sectorCode: " + code + " sectorLabel: "+label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				added = true;
				//if(key.contains("FAO")){
				//	for(String fao: ADAMConstants.faoViewPurposes.keySet())
				//		filter.add(fao);
				//} else {
					code = getCode(code);
					filter.add(code);
				//}
			}
		}
		if ( !filter.isEmpty())
			ADAMQueryVOBuilder.addSectorCodeFilters(filters, filter);

		return added;

	}
	
	
	
	public static Boolean addSubSectorFilter(final HashMap<String, List<String>> filters, final Map<String, String> subSectorList) {

		Boolean added = false;
		List<String> filter = new ArrayList<String>();
		System.out.println("addSubSectorListFilter ... ");
		for(String key : subSectorList.keySet()) {
			String code = key;
			String label = subSectorList.get(key);
			System.out.println("subSectorCode: " + code + " subSectorLabel: "+label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				added = true;
				code = getSubSectorCode(code);
				System.out.println("code " + code);
				filter.add(code);
			}
		}
		if ( !filter.isEmpty())
			ADAMQueryVOBuilder.addPurposeCodeFilters(filters, filter);

		return added;

	}
	
	
	public static Boolean addChannelFilter(final HashMap<String, List<String>> filters, final Map<String, String> sectorList) {

		Boolean added = false;
		List<String> filter = new ArrayList<String>();
		System.out.println("addChannelFilter ... ");
		for(String key : sectorList.keySet()) {
			String code = key;
			String label = sectorList.get(key);
			System.out.println("channelCode: " + code + " channelLabel: "+label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				added = true;
				code = getCode(code);
				filter.add(code);
			}
		}
		if ( !filter.isEmpty())
			ADAMQueryVOBuilder.addChannelCodeFilters(filters, filter);

		return added;

	}




	public static Boolean addPurposeCodesFilter(final HashMap<String, List<String>> filters, final Map<String, String> purposeList) {

		Boolean added = false;
		List<String> filter = new ArrayList<String>();
		System.out.println("addPurposeCodesFilter ... ");
		for(String key : purposeList.keySet()) {
			String code = key;
			String label = purposeList.get(key);
			System.out.println("purposeCode: " + code + " purposeLabel: "+label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				added = true;
				code = getSubSectorCode(code);
				filter.add(code);
			}
		}
		if ( !filter.isEmpty())
			ADAMQueryVOBuilder.addPurposeCodeFilters(filters, filter);

		return added;

	}

	
	public static Map<String, String> getCleanMap(Map<String, String> map2) {
		Map<String, String> map = new HashMap<String, String>();
		for(String code : map2.keySet()) {
			map.put(getCode(code), map2.get(code));
		}
		return map;
	}


	public static LinkedHashMap<String, String> getCodes(final Map<String, String> items) {
		
		LinkedHashMap<String, String> codes = new LinkedHashMap<String, String>();
		
		for(String key : items.keySet()) {
			String code = key;
			if ( !key.equalsIgnoreCase("GLOBAL")) {
				code = getCode(code);
				codes.put(code, items.get(key));
			}
		}
		
		return codes;

	}
	
	
	public static String getCode(String code) {
		String c = code.substring(1 + code.indexOf('_'));
		while(c.contains("_")) {
			 c = c.substring(code.indexOf('_'));
		}
		return c;
	}
	
	public static String getSubSectorCode(String code) {
		String c = code.substring(1 + code.indexOf('_'));
	     while(c.contains("_")) {
			 c = c.substring(1 + c.indexOf('_'));
		}
		
		System.out.println("getSubSectorCode c = "+c);
		return c;
	}


//	public static void updateSelectionMessagesAgent() {
//		removeKeyMessages();
//
//		ADAMVisibilityController.restoreKeyMessagesVisiblity();
//		
//		String html = "<table width='650px' align='center'><tr><td class='small-content' colspan='3' align='center'><b><u> Current View </u></b></td></tr>";
//				html += "<tr>";
//				html += "<td width='225px' align='left'><b> Countries </b></td>";
//				html += "<td width='225px' align='left'><b> Resource Partners </b></td>";
//				html += "<td width='225px' align='left'><b> Sectors </b></td>";
//				html += "</tr>";
//				html += "<tr>";
//				html += "<td align='left'> " + addSelection(ADAMBoxMaker.countriesSelected, "Selected Geographic Area") + " </b></td>";
//				html += "<td align='left'> " + addSelection(ADAMBoxMaker.donorsSelected, "Selected Donors") + " </b></td>";
//				html += "<td align='left'> " + addSelection(ADAMBoxMaker.sectorsSelected, "Selected Sectors")+ " </b></td>";
//				html += "</tr>";
//				HTML table = new HTML(html);			
//				RootPanel.get("KEY_MESSAGES").add(table);
//				RootPanel.get("KEY_MESSAGES").setStyleName("keymessages border small-content");
//				
//				
//	}
//
//	public static void updateSelectionMessagesAgentDonorMatrix() {
//		removeKeyMessages();
//		
//		ADAMVisibilityController.restoreKeyMessagesVisiblity();
//		
//        String html = "<table width='650px' align='center'><tr><td class='small-content' colspan='3' align='center'><b><u> Resource Partner Matrix</u></b></td></tr>";
//		html += "<tr>";
//		html += "<td width='225px' align='left'><b>Recipient Countries</b></td>";
//		html += "<td width='225px' align='left'><b>Resource Partners</b></td>";
//		html += "</tr>";
//		html += "<tr>";
//		html += "<td align='left'> " + addSelection(ADAMBoxMaker.countriesSelected, "Selected Geographic Area").toString() + " </b></td>";
//		html += "<td align='left'> " + addSelection(ADAMBoxMaker.donorsSelected, "Selected Partners").toString() + " </b></td>";
//		html += "</tr>";
//
//		HTML table = new HTML(html);
//		RootPanel.get("KEY_MESSAGES").add(table);
//		RootPanel.get("KEY_MESSAGES").setStyleName("keymessages border small-content");
//
//	}

	private static HTML addSelection(Map<String, String> map, String title) {
		String label = new String();

		LinkedHashMap<String, String>  sortedMap = sortByValues(map);
		String fullLabel = new String();

		if ( !sortedMap.isEmpty()){
			for(String key : sortedMap.keySet()) {
				label += sortedMap.get(key) + " ";
//				fullLabel += sortedMap.get(key) + " ";
				if ( label.length() > 25 ) {
					label = label.substring(0, 24) + "...";
					break;
				}
			}

			HTML link = new HTML();
//			link.setHTML("" + label + " - show all");
			link.setHTML("<div class='link'> " + label + " - show all</div>");
//			link.setToolTip(fullLabel);
			link.addMouseOverHandler(mouseOver(sortedMap, title));
//			link.addClickHandler(showAllLink(sortedMap, title));
			return link;
		}
//		return new HTML("Global View");
		return new HTML("<div style='font-size:9px'>Global View</div>");
	}
	
	private static MouseOverHandler mouseOver(final LinkedHashMap<String, String>  sortedMap, final String title) {
		return new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent arg0) {
				//System.out.println("ON CLICK");
				Window window = new Window();
				HTML htmlTitle = new HTML("<div class='link-title'>" + title + "</div>");
				window.add(htmlTitle);
				window.setHeading(title);
				window.setHeight(450);
				window.setWidth(450);
//				window.setScrollMode(Scroll.AUTO);

				VerticalPanel panel = new VerticalPanel();
				panel.setHeight(450);
				panel.setWidth(450);
				panel.setScrollMode(Scroll.AUTO);
				for(String code : sortedMap.keySet()) {
					HTML html = new HTML();
					String label = sortedMap.get(code);
					html.setHTML("<div style='font-size:10px'>" + label + "</div>");

					panel.add(html);
				}

				window.add(panel);
				window.show();
				
			}
		};
	}

	private static ClickHandler showAllLink(final LinkedHashMap<String, String>  sortedMap, final String title) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window window = new Window();
				HTML htmlTitle = new HTML("<div class='link-title'>" + title + "</div>");
				window.add(htmlTitle);
				window.setHeading(title);
				window.setHeight(450);
				window.setWidth(450);
				window.setScrollMode(Scroll.AUTO);

				VerticalPanel panel = new VerticalPanel();
				panel.setHeight(450);
				panel.setWidth(450);
				panel.setScrollMode(Scroll.AUTO);
				
				for(String code : sortedMap.keySet()) {
					HTML html = new HTML();
					String label = sortedMap.get(code);
					html.setHTML("<div style='font-size:10px'>" + label + "</div>");


					panel.add(html);
				}

				window.add(panel);
				window.show();
			}
		};
	}




	public static ClickHandler updateCountry(final String code, final String label, final Window window) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				if ( window != null )
					window.close();

				ComboBox<GaulModelData> gaulList = ADAMBoxMaker.getGaulList();
				ListStore<GaulModelData> gaulStore = ADAMBoxMaker.getGaulStore();


				updateList(code, gaulList, gaulStore);

			}
		};
	}


	public static ClickHandler updateDonor(final String code, final String label, final Window window) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				if ( window != null )
					window.close();

				ComboBox<GaulModelData> donorList = ADAMBoxMaker.getDonorList();
				ListStore<GaulModelData> donorStore = ADAMBoxMaker.getDonorStore();

				updateList(code, donorList, donorStore);
			}
		};
	}

	private static void updateList(String code, ComboBox<GaulModelData> list, ListStore<GaulModelData>  store) {
		//System.out.println("store count: " + list.getView());
		store.clearFilters();
		List<GaulModelData> models = store.getModels();


		//System.out.println("store count: " + list.getView());

		for(int i=0; i < models.size(); i++ ) {
			String listCode = getCode(models.get(i).getGaulCode());
		//	System.out.println("checking code: " + models.get(i).getGaulCode() + " | " + code);
			if( listCode.equals(code)) {
			//	System.out.println("setting");
				// set in the list box
				list.setValue(store.getAt(i));
				break;
			}
		}

	}

	public static ClickHandler globalLOGIN(final ADAMWrapper adamWrapper) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {

				if ( FenixUser.hasAdminRole()) {
					globalAgentHome(adamWrapper);
				}
				else
					FenixAlert.info("login", "please login");
			}
		};
	}

	public static ClickHandler global(final ADAMWrapper adamWrapper) {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				/** TODO: restore drop down **/
				//restoreDropDowns And filters ;
				restoreDropDownsAndFilters();
				cleanCustomContent();
				globalAgentHome(adamWrapper);
			}
		};
	}
	
	private static void cleanCustomContent() {
		currentCustom.clear();
		currentUsedObjects.clear();
	}
	
	private static void restoreDropDownsAndFilters() {
		// country list
		ADAMBoxMaker.getGaulList().disableEvents(true);
		ADAMBoxMaker.getGaulList().setValue(ADAMBoxMaker.getGaulStore().getAt(0));
		ADAMBoxMaker.countriesSelected.clear();		
		ADAMBoxMaker.getGaulList().disableEvents(false);
		
		// donor list
		ADAMBoxMaker.getDonorList().disableEvents(true);
		ADAMBoxMaker.getDonorList().setValue(ADAMBoxMaker.getDonorStore().getAt(0));
		ADAMBoxMaker.donorsSelected.clear();		
		ADAMBoxMaker.getDonorList().disableEvents(false);
		
		// sector list
		ADAMBoxMaker.getSectorList().disableEvents(true);
		ADAMBoxMaker.getSectorList().setValue(ADAMBoxMaker.getSectorStore().getAt(0));
		ADAMBoxMaker.sectorsSelected.clear();		
		ADAMBoxMaker.getSectorList().disableEvents(false);
		
		// sub sector list
		ADAMBoxMaker.getSubSectorList().disableEvents(true);
		ADAMBoxMaker.getSubSectorList().setValue(ADAMBoxMaker.getSubSectorStore().getAt(0));
		ADAMBoxMaker.subSectorsSelected.clear();		
		ADAMBoxMaker.getSubSectorList().disableEvents(false);
	}
	
	private static void restoreAndClearDropDownsAndFilters() {
		// country list
		ADAMBoxMaker.getGaulList().disableEvents(true);
		//ADAMBoxMaker.getGaulList().setValue(ADAMBoxMaker.getGaulStore().getAt(0));
		ADAMBoxMaker.getGaulList().getStore().removeAll();		
		
		//ADAMBoxMaker.countriesSelected.clear();		
		
		ADAMBoxMaker.getGaulList().disableEvents(false);
		
		// donor list
		ADAMBoxMaker.getDonorList().disableEvents(true);
		ADAMBoxMaker.getDonorList().getStore().removeAll();		
		//ADAMBoxMaker.getDonorList().setValue(ADAMBoxMaker.getDonorStore().getAt(0));
		//ADAMBoxMaker.donorsSelected.clear();		
		ADAMBoxMaker.getDonorList().disableEvents(false);
		
		// sector list
		ADAMBoxMaker.getSectorList().disableEvents(true);
		ADAMBoxMaker.getSectorList().getStore().removeAll();		
		//ADAMBoxMaker.getSectorList().setValue(ADAMBoxMaker.getSectorStore().getAt(0));
		//ADAMBoxMaker.sectorsSelected.clear();		
		ADAMBoxMaker.getSectorList().disableEvents(false);
		
		// sub sector list
		ADAMBoxMaker.getSubSectorList().disableEvents(true);
		ADAMBoxMaker.getSubSectorList().getStore().removeAll();
		//ADAMBoxMaker.getSubSectorList().setValue(ADAMBoxMaker.getSubSectorStore().getAt(0));
		//ADAMBoxMaker.subSectorsSelected.clear();		
		ADAMBoxMaker.getSubSectorList().disableEvents(false);
	}

	public static void globalAgentHome(ADAMWrapper adamWrapper) {
		
		ADAMController.callViewAgent(ADAMCurrentVIEW.HOME,  "globalAgentHome", ADAMCurrentVIEW.HOME.name());
		//ADAMController.callViewAgent(ADAMCurrentVIEW.ADAMVIEW);
		//ADAMController.containsMaps = false;
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.HOME;

		//ADAMVisibilityController.restoreAdamViewVisibility();
		
		
		//showHomePage();
	}
	
	
	
	public static void globalAgentWithAgricultureADAM(ADAMWrapper adamWrapper) {
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		//System.out.println("CURRENT OBJECT ID: " + objectWindow);

		removeBoxes();
		//removeQuestions();
//		filters();
		addViewMenu(globalTitle, null, null, null, null, null);
		
		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");

		// test
//		ADAMQueryVO x = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.SCATTER.toString(), "Time series of Most Financed Countries (Agriculture)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), false, null);
//		ADAMQueryVO y = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.SCATTER.toString(), "Time series of Most Financed Countries (Agriculture)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), true, null);
//		x.setSecondQuery(y);
//	
//		ADAMController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), x, objectWindowId);

		String mostFinancedTitle = "";
		if(currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			mostFinancedTitle = "Most Financed SOs";
		} else {
			mostFinancedTitle = "Most Financed Sectors";
		}
		
		// TOP_LEFT
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(mostFinancedTitle, ADAMBoxContent.BAR.toString(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors("Time series of "+mostFinancedTitle,ADAMBoxContent.TIMESERIE.toString(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);
		topSectorTimeSerie.getWhereDates().put("year", baseDate);
		topSector.getSwitchResources().add(topSectorTimeSerie);
		topSectorTimeSerie.getSwitchResources().add(topSector);
		ADAMController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSector, objectWindowId);
		
		
		// BOTTOM_RIGHT
		ADAMQueryVO topSbbAgricuChart = ADAMQueryVOBuilder.mostFinancedAgriculturalSectors(ADAMBoxContent.PIE.name(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);	
		ADAMQueryVO topSbbAgricuChartSeries = ADAMQueryVOBuilder.mostFinancedAgriculturalSectors(ADAMBoxContent.TIMESERIE.name(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);
		topSbbAgricuChartSeries.getWhereDates().put("year", baseDate);
		topSbbAgricuChart.getSwitchResources().add(topSbbAgricuChartSeries);
		topSbbAgricuChartSeries.getSwitchResources().add(topSbbAgricuChart);

		
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSbbAgricuChart, objectWindowId);

//		ADAMController.addMapPredifined("global", "MAP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "START", adamWrapper);
	
		
		
		
//		// BOTTOM_LEFT
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget in Agriculture - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, new HashMap<String, List<String>>(),  crs_aggregationColumn.getGaulCode(), crs_aggregationType, true);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries (Agriculture)", new HashMap<String, List<String>>(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), true, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, true, objectWindowId);

		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.name(), "Top Ten Resource Partners (Agriculture)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, 10);	
		ADAMQueryVO topDonorTable = ADAMQueryVOBuilder.topDonors(baseDate, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), 10);
		ADAMQueryVO topDonorsSeries = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners (Agriculture)", new HashMap<String, List<String>>(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, true, timeserieLimit);
		topDonorsSeries.getWhereDates().put("year", baseDate);
		
		// switchers
		topDonorChart.getSwitchResources().add(topDonorTable);
		topDonorChart.getSwitchResources().add(topDonorsSeries);
			
		topDonorsSeries.getSwitchResources().add(topDonorChart);
//		topDonorsSeries.getSwitchResources().add(topDonorTable);
		
		topDonorTable.getSwitchResources().add(topDonorChart);
		topDonorTable.getSwitchResources().add(topDonorsSeries);
		
		
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);


		

		
//		ADAMController.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter("", baseDate, new HashMap<String, List<String>>(), ADAMBoxColors.yellow.name()), objectWindowId);

	}
	
	
	public static void globalAgentADAM(ADAMWrapper adamWrapper) {
		ADAMController.containsMaps = true;
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		//System.out.println("CURRENT OBJECT ID: " + objectWindow);

		removeBoxes();
		//removeQuestions();
//		filters();
		addViewMenu(globalTitle, null, null, null, null, null);
		
		ADAMVisibilityController.restoreAdamViewVisibility();
		
		RootPanel.get("TOP_LEFT").setStyleName("small-box content");
		RootPanel.get("TOP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_RIGHT").setStyleName("small-box content");
		RootPanel.get("MAP_LEFT").setStyleName("small-box content");

		// test
//		ADAMQueryVO x = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.SCATTER.toString(), "Time series of Most Financed Countries (Agriculture)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), false, null);
//		ADAMQueryVO y = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.SCATTER.toString(), "Time series of Most Financed Countries (Agriculture)", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), true, null);
//		x.setSecondQuery(y);
//	
//		ADAMController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), x, objectWindowId);

		
		String mostFinancedTitle = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			mostFinancedTitle = "Most Financed SOs";
		} else {
			mostFinancedTitle = "Most Financed Sectors";
		}
		
		// TOP_LEFT
		ADAMQueryVO topSector = ADAMQueryVOBuilder.mostFinancedSectors(mostFinancedTitle, ADAMBoxContent.BAR.toString(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);
		ADAMQueryVO topSectorTimeSerie = ADAMQueryVOBuilder.mostFinancedSectors("Time series of "+mostFinancedTitle,ADAMBoxContent.TIMESERIE.toString(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);
		topSector.getWhereDates().put("year", baseDate);
		topSector.getSwitchResources().add(topSectorTimeSerie);
		topSectorTimeSerie.getSwitchResources().add(topSector);
		ADAMController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorTimeSerie, objectWindowId);
		
		
		// BOTTOM_RIGHT
		ADAMQueryVO topSbbAgricuChart = ADAMQueryVOBuilder.mostFinancedSubSectors(ADAMBoxContent.PIE.name(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 10);	
		ADAMQueryVO topSbbAgricuChartSeries = ADAMQueryVOBuilder.mostFinancedSubSectors(ADAMBoxContent.TIMESERIE.name(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), timeserieLimit);
		topSbbAgricuChartSeries.getWhereDates().put("year", baseDate);
		topSbbAgricuChart.getSwitchResources().add(topSbbAgricuChartSeries);
		topSbbAgricuChartSeries.getSwitchResources().add(topSbbAgricuChart);

		
		ADAMController.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSbbAgricuChart, objectWindowId);

//		ADAMController.addMapPredifined("global", "MAP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "START", adamWrapper);
	
		
		
		
//		// BOTTOM_LEFT
		ADAMQueryVO googleMap = ADAMQueryVOBuilder.googleMapsQuery("Budget per Country - " + crs_aggregationColumn.getGaulLabel() + " ("+ADAMController.baseUnit+") ", baseDate, new HashMap<String, List<String>>(),  crs_aggregationColumn.getGaulCode(), crs_aggregationType, false);
		ADAMQueryVO countriesChart = ADAMQueryVOBuilder.mostFinancedCountries(ADAMBoxContent.TIMESERIE.toString(), "Time series of Most Financed Countries (Agriculture)", new HashMap<String, List<String>>(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel(), false, timeserieLimit);
		countriesChart.getWhereDates().put("year", baseDate);
		googleMap.getSwitchResources().add(countriesChart);
		countriesChart.getSwitchResources().add(googleMap);
		
		ADAMMapController.addGoogleMapsADAMView(googleMap, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "MAP_LEFT", true, true, objectWindowId);

		
		// TOP_RIGHT
		ADAMQueryVO topDonorChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.name(), "Top Ten Resource Partners", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMQueryVO topDonorTable = ADAMQueryVOBuilder.topDonors(baseDate, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), 10);
		ADAMQueryVO topDonorsSeries = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.TIMESERIE.toString(), "Time series of Top Resource Partners", new HashMap<String, List<String>>(), new ArrayList<String>(), ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, timeserieLimit);
		topDonorsSeries.getWhereDates().put("year", baseDate);
		
		// switchers
		topDonorChart.getSwitchResources().add(topDonorTable);
		topDonorChart.getSwitchResources().add(topDonorsSeries);
			
		topDonorsSeries.getSwitchResources().add(topDonorChart);
//		topDonorsSeries.getSwitchResources().add(topDonorTable);
		
		topDonorTable.getSwitchResources().add(topDonorChart);
		topDonorTable.getSwitchResources().add(topDonorsSeries);
		
		
		ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);


		

		
//		ADAMController.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter("", baseDate, new HashMap<String, List<String>>(), ADAMBoxColors.yellow.name()), objectWindowId);

	}
	
	
	public static void addViewMenu(String title, Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors, Map<String, String> subSectors, Map<String, String> channels ) {
		ADAMVisibilityController.removeViewMenuContent();
		ADAMVisibilityController.restoreViewMenuVisibility();
		
		if(!currentVIEW.equals(ADAMCurrentVIEW.HOME) && !currentVIEW.equals(ADAMCurrentVIEW.LINKS)) {	
			RootPanel.get("VIEWMENU_TITLE").add(ADAMViewMenuBuilder.buildTitle(title));
			RootPanel.get("VIEWMENU_CONTENT").add(ADAMViewMenuBuilder.buildDescriptionPanel(countries, donors, sectors, subSectors, channels));
//			RootPanel.get("VIEWMENU_TITLE").add(ADAMViewMenu.buildTitle(title));
//			RootPanel.get("VIEWMENU_CONTENT").add(ADAMViewMenu.buildDescriptionPanel(countries, donors, sectors, channels));
		}	
//		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) || currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX)){
//			RootPanel.get("VIEWMENU_LEFT").add(ADAMViewMenuBuilder.buildLeftToolbar(true));
////			RootPanel.get("VIEWMENU_LEFT").add(ADAMViewMenu.buildLeftToolbar(true));
//		}	
	/*	if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)){
			if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
				if (RootPanel.get("VIEWMENU_RIGHT").getWidgetCount() > 0)
					RootPanel.get("VIEWMENU_RIGHT").remove(RootPanel.get("VIEWMENU_RIGHT").getWidget(0));
			} else {
				if (RootPanel.get("VIEWMENU_RIGHT").getWidgetCount() > 0)
					RootPanel.get("VIEWMENU_RIGHT").remove(RootPanel.get("VIEWMENU_RIGHT").getWidget(0));
				
				RootPanel.get("VIEWMENU_RIGHT").add(ADAMViewMenuBuilder.buildRightPanel(true));
			}
			
//			RootPanel.get("VIEWMENU_RIGHT").add(ADAMViewMenu.buildRightPanel(true));
		}*/	
//		RootPanel.get("VIEWMENU_RIGHT").setStyleName("viewmenu-box");
		
		//if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) || currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX)){			
		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)){
			if (!ADAMDataSourceSelection.isBuild()) {
				RootPanel.get("VIEWMENU_LEFT").add(ADAMDataSourceSelection.build());
			}
			else {
				RootPanel.get("VIEWMENU_LEFT").add(ADAMDataSourceSelection.getPanel());
			}
//			RootPanel.get("VIEWMENU_RIGHT").add(ADAMViewMenu.buildRightPanel(true));
		}	
	}

	public static void filters() {
		ADAMVisibilityController.enableAllSelectors();
		removeFilters();
		//removeQuestions();

		
		if(currentVIEW.equals(ADAMCurrentVIEW.HOME) || currentVIEW.equals(ADAMCurrentVIEW.LINKS)){
			removeFilters();
			//removeQuestions();			
		}  else {
			
			RootPanel.get("MAIN_SELECTORS").setStyleName("selector_holders");
			//
			 
			//		RootPanel.get("COUNTRY_SELECTOR").setStyleName("small-yellow-box border display");

			if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//				RootPanel.get("COUNTRY_SELECTOR").setStyleName("div-country");
				
				RootPanel.get("COUNTRY_SELECTOR").add(ADAMBoxMaker.buildCountrySelector());
				RootPanel.get("DONOR_SELECTOR").add(ADAMBoxMaker.buildDonorSelector());
			}	

//			RootPanel.get("DONOR_SELECTOR").setStyleName("div-donor");
			//RootPanel.get("DONOR_SELECTOR").add(ADAMBoxMaker.buildDonorSelector());


//			if (currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
////				RootPanel.get("SWITCH_SELECTOR").setStyleName("div-sector");
//				RootPanel.get("SWITCH_SELECTOR").add( new ADAMSwitchSelectionPanel().build(currentVIEW));
//			}
			
			     
			if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){ 
				
				//System.out.println("******** filters() IF ADAM VIEW 1 *****************");
				
				//if(currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS)) {
					//Default to Agricuture related sectors
					GaulModelData model = ADAMBoxMaker.sectorList.getStore().findModel("gaulCode", "Dac_9999");
					if(model!=null)
						ADAMBoxMaker.sectorList.setValue(model);
				//}
				
				//	System.out.println("******** filters() IF ADAM VIEW 2 *****************");
					
//				RootPanel.get("SECTOR_SELECTOR").setStyleName("div-sector");
					
			    System.out.println("******** filters() ADAM VIEW  build = "+ADAMSwitchClassificationPanel.getIsBuild() + "ADAMController.currentlySelectedDatasetCode "+ ADAMController.currentlySelectedDatasetCode);
				
			    if(!ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			    	if (RootPanel.get("CLASSIFICATION").getWidgetCount() == 0 || !ADAMSwitchClassificationPanel.getIsBuild()) {
			    		//if ( !ADAMSwitchSelectionPanel.getIsBuild() ) {
			    		RootPanel.get("CLASSIFICATION").add(ADAMBoxMaker.buildClassificationSelector());	
			    	} 
			    }
				
				//System.out.println("******** filters() IF ADAM VIEW 3 *****************");
				RootPanel.get("SECTOR_SELECTOR").add(ADAMBoxMaker.buildSectorSelector());	
				//System.out.println("******** filters() IF ADAM VIEW 4 *****************");
				RootPanel.get("SUB_SECTOR_SELECTOR").add(ADAMBoxMaker.buildSubSectorSelector());	
				//System.out.println("******** filters() IF ADAM VIEW 5 *****************");
			}
           /* if(currentVIEW.name().contains(ADAMCurrentVIEW.ANALYSE.name())){ 
				
				//Default to Agricuture related sectors
					GaulModelData model = ADAMBoxMaker.sectorList.getStore().findModel("gaulCode", "Dac_9999");
					if(model!=null)
						ADAMBoxMaker.sectorList.setValue(model);
				//}
						
			    System.out.println("******** filters() ADAM VIEW  build = "+ADAMSwitchClassificationPanel.getIsBuild() + "ADAMController.currentlySelectedDatasetCode "+ ADAMController.currentlySelectedDatasetCode);
				
			    if(!ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			    	if (RootPanel.get("CLASSIFICATION").getWidgetCount() == 0 || !ADAMSwitchClassificationPanel.getIsBuild()) {
			    		//if ( !ADAMSwitchSelectionPanel.getIsBuild() ) {
			    		RootPanel.get("CLASSIFICATION").add(ADAMBoxMaker.buildClassificationSelector());	
			    	} 
			    }
				
				//System.out.println("******** filters() IF ADAM VIEW 3 *****************");
				RootPanel.get("SECTOR_SELECTOR").add(ADAMBoxMaker.buildSectorSelector());	
				//System.out.println("******** filters() IF ADAM VIEW 4 *****************");
				RootPanel.get("SUB_SECTOR_SELECTOR").add(ADAMBoxMaker.buildSubSectorSelector());	
				//System.out.println("******** filters() IF ADAM VIEW 5 *****************");
			}*/
			else if (currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
				//Default to All selected
				//ADAMBoxMaker.soList.setValue(ADAMBoxMaker.soList.getStore().getAt(0));
				
//				RootPanel.get("SO_SELECTOR").setStyleName("div-sector");
				//RootPanel.get("SO_SELECTOR").add(ADAMBoxMaker.buildSOSelector());	
				RootPanel.get("CLASSIFICATION").add(ADAMBoxMaker.buildClassificationSelector());	
				RootPanel.get("SECTOR_SELECTOR").add(ADAMBoxMaker.buildSOSelector());	
				RootPanel.get("SUB_SECTOR_SELECTOR").add(ADAMBoxMaker.buildORSelector());	

				ADAMSwitchClassificationPanel.isBuild = true;
				
			} else if (currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//				RootPanel.get("SO_SELECTOR").setStyleName("div-sector");
				//RootPanel.get("SO_SELECTOR").add(ADAMBoxMaker.buildSOSelector());	
				RootPanel.get("COUNTRY_SELECTOR").add(ADAMBoxMaker.buildDonorSelector());	
				
				ADAMSwitchClassificationPanel.isBuild = false;
			}/* else if (currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX)) {
				
				ADAMSwitchSelectionPanel.isBuild = false;
			}*/
			

			
		//	if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){ 
//				RootPanel.get("CHANNEL_SELECTOR").setStyleName("div-sector");
//				RootPanel.get("CHANNEL_SELECTOR").add(ADAMBoxMaker.buildChannelSelector());			
			//}
			

			
			
			

			if ( !ADAMQuantityColumn.getIsBuild() ) {
				if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
					
					
					
					RootPanel.get("AGGREGATION_COLUMN").setStyleName("div-aggregation");
					//RootPanel.get("AGGREGATION_COLUMN").add(new ADAMQuantityColumn().build(currentVIEW));		
					RootPanel.get("AGGREGATION_COLUMN").add(ADAMQuantityColumn.buildAggregationSelector(currentVIEW));		
					
					// added after otherwise assetion error encountered
					RootPanel.get("OTHER_SELECTORS").setStyleName("selector_holders");
				}

			}else {
				//if(currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX) || currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
			    if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {	
					removeAggregationColumn();
					RootPanel.get("CLASSIFICATION").setStyleName("");
					RootPanel.get("SECTOR_SELECTOR").setStyleName("");
					RootPanel.get("SUB_SECTOR_SELECTOR").setStyleName("");
					RootPanel.get("OTHER_SELECTORS").setStyleName("");
					
					if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
						//RootPanel.get("COUNTRY_SELECTOR").setStyleName("");
						RootPanel.get("DONOR_SELECTOR").setStyleName("");
				     }	
					
					ADAMQuantityColumn.isBuild = false;
				}
			}


			if(!ADAMReferencePeriodPanel.getIsBuild()) {
				if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
					
					
					
					//
					//RootPanel.get("REFERENCE_PERIOD").setStyleName("div-date");
					//RootPanel.get("REFERENCE_PERIOD").add(ADAMReferencePeriodPanel.buildReferencePeriod(currentVIEW));
					
					RootPanel.get("REFERENCE_PERIOD_FROM").setStyleName("div-date");
					RootPanel.get("REFERENCE_PERIOD_TO").setStyleName("div-date");
					
					RootPanel.get("REFERENCE_PERIOD_FROM").add(ADAMReferencePeriodPanel.buildFromDateSelector());
					RootPanel.get("REFERENCE_PERIOD_TO").add(ADAMReferencePeriodPanel.buildToDateSelector());
					
					// added after otherwise assetion error encountered
					RootPanel.get("DATE_SELECTORS").setStyleName("selector_holders");
				}
				

			} else {
				if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
					removeReferencePeriodFilter();
					RootPanel.get("DATE_SELECTORS").setStyleName("");
					ADAMReferencePeriodPanel.isBuild = false;
				}
			}


		 
			
			ADAMVisibilityController.restoreKeyMessagesVisiblity();
		}	
//		updateSelectionMessagesAgent();

	}
	
	
	/*public static void clearFilters() {
		removeFilters();
		removeQuestions();

		
		if(currentVIEW.equals(ADAMCurrentVIEW.HOME)){
			removeFilters();
			removeQuestions();			
		} else {
			//		RootPanel.get("COUNTRY_SELECTOR").setStyleName("small-yellow-box border display");

			if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//				RootPanel.get("COUNTRY_SELECTOR").setStyleName("div-country");
				RootPanel.get("COUNTRY_SELECTOR").add(ADAMBoxMaker.buildCountrySelector());
				RootPanel.get("DONOR_SELECTOR").add(ADAMBoxMaker.buildDonorSelector());
			}	

//			RootPanel.get("DONOR_SELECTOR").setStyleName("div-donor");
			//RootPanel.get("DONOR_SELECTOR").add(ADAMBoxMaker.buildDonorSelector());


//			if (currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
////				RootPanel.get("SWITCH_SELECTOR").setStyleName("div-sector");
//				RootPanel.get("SWITCH_SELECTOR").add( new ADAMSwitchSelectionPanel().build(currentVIEW));
//			}
			
			     
			if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){ 
				//if(currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS)) {
				//Default to Agricuture related sectors
					GaulModelData model = ADAMBoxMaker.sectorList.getStore().findModel("gaulCode", "Dac_9999");
					ADAMBoxMaker.sectorList.setValue(model);
				//}
				
				System.out.println("******** clearfilters() IF ADAM VIEW *****************");
//				RootPanel.get("SECTOR_SELECTOR").setStyleName("div-sector");
				
				RootPanel.get("CLASSIFICATION").add(ADAMBoxMaker.buildClassificationSelector());	
				RootPanel.get("SECTOR_SELECTOR").add(ADAMBoxMaker.buildSectorSelector());	
				RootPanel.get("SUB_SECTOR_SELECTOR").add(ADAMBoxMaker.buildSubSectorSelector());	
			}
			else if (currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
				//Default to All selected
				//ADAMBoxMaker.soList.setValue(ADAMBoxMaker.soList.getStore().getAt(0));
				
//				RootPanel.get("SO_SELECTOR").setStyleName("div-sector");
				//RootPanel.get("SO_SELECTOR").add(ADAMBoxMaker.buildSOSelector());	
				RootPanel.get("CLASSIFICATION").add(ADAMBoxMaker.buildClassificationSelector());	
				RootPanel.get("SECTOR_SELECTOR").add(ADAMBoxMaker.buildSOSelector());	
				RootPanel.get("SUB_SECTOR_SELECTOR").add(ADAMBoxMaker.buildORSelector());	
				
			} else if (currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//				RootPanel.get("SO_SELECTOR").setStyleName("div-sector");
				//RootPanel.get("SO_SELECTOR").add(ADAMBoxMaker.buildSOSelector());	
				RootPanel.get("COUNTRY_SELECTOR").add(ADAMBoxMaker.buildDonorSelector());	
			}
			

			
		//	if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){ 
//				RootPanel.get("CHANNEL_SELECTOR").setStyleName("div-sector");
//				RootPanel.get("CHANNEL_SELECTOR").add(ADAMBoxMaker.buildChannelSelector());			
			//}
			

			
	
			if ( !ADAMQuantityColumn.getIsBuild() ) {
				if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) || currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
					RootPanel.get("AGGREGATION_COLUMN").setStyleName("div-aggregation");
					//RootPanel.get("AGGREGATION_COLUMN").add(new ADAMQuantityColumn().build(currentVIEW));	
					RootPanel.get("AGGREGATION_COLUMN").add(ADAMQuantityColumn.buildAggregationSelector(currentVIEW));	
				}

			}else {
				if(currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX) || currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
					removeAggregationColumn();
					RootPanel.get("CLASSIFICATION").setStyleName("");
					RootPanel.get("SECTOR_SELECTOR").setStyleName("");
					RootPanel.get("SUB_SECTOR_SELECTOR").setStyleName("");
					
					if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
						//RootPanel.get("COUNTRY_SELECTOR").setStyleName("");
						RootPanel.get("DONOR_SELECTOR").setStyleName("");
				     }	
					
					ADAMQuantityColumn.isBuild = false;
				}
			}


			if(!ADAMReferencePeriodPanel.getIsBuild()) {
				if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
					RootPanel.get("REFERENCE_PERIOD").setStyleName("div-date");
					RootPanel.get("REFERENCE_PERIOD").add(ADAMReferencePeriodPanel.buildReferencePeriod(currentVIEW));
				}

			} else {
				if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
					removeReferencePeriodFilter();
					ADAMReferencePeriodPanel.isBuild = false;
				}
			}


			ADAMVisibilityController.restoreKeyMessagesVisiblity();
		}	
//		updateSelectionMessagesAgent();

	}*/


	public static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));
		return out;
	}
	

	public static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}




	/*****
	 * MAPS
	 */

	public static void checkBoxes() {
		checkWidget("TOP_LEFT");
		checkWidget("TOP_RIGHT");
		checkWidget("BOTTOM_LEFT");
		checkWidget("BOTTOM_RIGHT");
		checkWidget("CENTER");
	}


	private static void checkWidget(String position) {
		if (RootPanel.get(position).getWidgetCount() > 0){
		//	System.out.println("CHECK WIDGET position:" + position + " | " + RootPanel.get(position).getWidget(0).getTitle());
			if ( RootPanel.get(position).getWidget(0).getTitle().equalsIgnoreCase("MAP")) {
				//System.out.println("MAP (" + position + ")");
			}
			else {
				//System.out.println("NOT MAP (" + position + ")");
			}
		}
	}












	/******/

	public static void addMapPredifined(String mapId, String position, String color, String mapType, ADAMWrapper adamWrapper){
		RootPanel.get(position).setStyleName("small-box content");

//		adamWrapper.buildMapPanel(mapId, ADAMConstants.colorsHEX.get(ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT)), mapType, position);

		adamWrapper.buildMapPanel(mapId, color, mapType, position);

		
		inizializeSmallMap(position,  color, adamWrapper.getAdamMapsWrapper().get(mapId));

	}

	public static void inizializeSmallMap(final String position, final String color, ADAMMapWrapper adamMapWrapper) {

		if (RootPanel.get(position).getWidgetCount() > 0) {
			if ( RootPanel.get(position).getTitle().equalsIgnoreCase("MAP")) {
				RootPanel.get(position).getWidget(0).setVisible(false);
			}
			else
				RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		}


		RootPanel.get(position).setStyleName("small-box content");
		adamMapWrapper.setCurrentPosition(position);

		RootPanel.get(position).add(adamMapWrapper.getPanel());
		RootPanel.get(position).getWidget(0).setTitle("MAP");
	}



	public static void initializePredifinedMap(final String mapType, final ADAMMapWrapper adamMapWrapper, final String language, final String position, final String color) {
		final ADAMOLPanel adamOLPanel = adamMapWrapper.getAdamMapPanel().getAdamOLPanel();
//		final ADAMLayersHandle adamLayersHandle = adamMapWrapper.getAdamMapPanel().getAdamLayersHandle();
		final VerticalPanel infoPanel = adamMapWrapper.getInfoPanel();
		final ClientMapView mapView = adamOLPanel.getMapView();
		final MapPanel mapPanel = adamOLPanel.getMapPanel();



		MapServiceEntry.getInstance().getLayersByCodingSystem("ADAMLayers", mapType, new AsyncCallback<List<ClientGeoView>>() {
			public void onSuccess(List<ClientGeoView> cgvlist) {
				ADAMResultVO adamResultVO = new ADAMResultVO();
				adamResultVO.setBoxColor(color);
				mapView.setTitle("");
				ClientBBox bbox = null;

				int i = 0;
				for (ClientGeoView clientGeoView : cgvlist) {
					if (bbox == null)
						bbox = clientGeoView.getBBox();
					else
						bbox = bbox.union(clientGeoView.getBBox());
					LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
					vo.setLegendUrl(getLegendURL(vo));

					/** TODO: this is a workaround for the GAUL0 **/
//					if ( i == 0 || i == 1)
					if ( i == 0 )
						vo.setIsBaseLayer(true);



					if ( i == cgvlist.size() - 1) {
						infoPanel.removeAll();
						infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));

//						infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
						infoPanel.layout();
					}

					adamResultVO.addLayer(vo);
					mapView.addLayer(clientGeoView);

					i++;
				}


				mapView.setBbox(bbox);
				mapPanel.createADAMMap(mapView, 7,1000000000, 5000);
//				mapPanel.createMap(mapView);
				mapPanel.zoomToMaxExtent();


				adamMapWrapper.setAdamResultVO(adamResultVO);
				
				adamResultVO.setOutputType(ADAMBoxContent.MAP.toString());
				adamResultVO.setResourceType(ADAMBoxContent.MAP.toString());
				
				mapMap.put(position, adamMapWrapper.getAdamResultVO());
				currentUsedObjects.put(position, ADAMBoxContent.MAP);
				
				
				/*** TODO HERE IT'S TO MAKE THE JOIN TO THE MAP ***/
				
				ADAMController.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter("Amount od...auioa", baseDate, new HashMap<String, List<String>>(), ADAMBoxColors.yellow.name(), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);

			}

			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}

		});
	}

	private static String getLegendURL(LayerVO vo) {
		String geoserverURL = (vo.getWmsURL().indexOf("wms") > 0 ? vo.getWmsURL().substring(0, vo.getWmsURL().indexOf("wms")) : vo.getWmsURL());
		String layerName = vo.getLayerName();
		String layerTitle = vo.getLayerTitle();
		String styleName = vo.getStyleName();
		String url = new String();
		if ( vo.getLegendUrl() != null ) {
			url = vo.getLegendUrl();
		}
		else{
			url = geoserverURL +
					     "wms?VERSION=1.1.0&REQUEST=GetLegendGraphic&LAYER=" +
					     layerName +
					     "&STYLE=" +
					     styleName +
					     "&WIDTH=50&HEIGHT=20&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&TIMESTAMP=" +
					     0;
		}
		//System.out.println("LEGEND URL: " + url);
		return url;
	}


	/*** TODO: HANDLE THE LAYERS LIST ADAMRESULTVO **/
	public static void addLayersAgent(final String mapType, final ADAMMapWrapper adamMapWrapper, final String language) {
		final ADAMOLPanel adamOLPanel = adamMapWrapper.getAdamMapPanel().getAdamOLPanel();
//		final ADAMLayersHandle adamLayersHandle = adamMapWrapper.getAdamMapPanel().getAdamLayersHandle();
		final VerticalPanel infoPanel = adamMapWrapper.getInfoPanel();
		final ClientMapView mapView = adamOLPanel.getMapView();
		final MapPanel mapPanel = adamOLPanel.getMapPanel();


		MapServiceEntry.getInstance().getLayersByCodingSystem("ADAMLayers", mapType, new AsyncCallback<List<ClientGeoView>>() {
			public void onSuccess(List<ClientGeoView> cgvlist) {

				ClientBBox bbox = null;
				List<LayerVO> layerVOs = new ArrayList<LayerVO>();
				for (ClientGeoView clientGeoView : cgvlist) {
					if (bbox == null)
						bbox = clientGeoView.getBBox();
					else
						bbox = bbox.union(clientGeoView.getBBox());

					LayerVO vo = new LayerVO(clientGeoView.getTitle(), clientGeoView.getClientId(), clientGeoView.getLayerId(), clientGeoView.getOpacity(), clientGeoView.getLayerType().name(), clientGeoView.getLayerName());
					vo.setWmsURL(clientGeoView.getGetMapUrl());
					vo.setStyleName(clientGeoView.getStyleName());
					vo.setFenixCode(clientGeoView.getFenixCode());
					vo.setAbstractAbstract(clientGeoView.getAbstractAbstract());
					vo.setSource(clientGeoView.getSource());
					vo.setSourceContact(clientGeoView.getSourceContact());
					vo.setProvider(clientGeoView.getProvider());
					vo.setProviderContact(clientGeoView.getProviderContact());
					vo.setIsHidden(clientGeoView.isHidden());
					vo.setClientBBox(clientGeoView.getBBox());
					if ( clientGeoView.getLagendUrl() != null)
						vo.setLegendUrl(clientGeoView.getLagendUrl());
					layerVOs.add(vo);
					mapPanel.addLayer(clientGeoView);
				}

				for (int i = layerVOs.size() - 1 ; i >= 0 ; i--){
//					adamLayersHandle.addLayer(layerVOs.get(i));
					adamMapWrapper.getAdamResultVO().addLayer(layerVOs.get(i));
					if ( i == layerVOs.size() - 1) {
						infoPanel.removeAll();
						infoPanel.add(new Html("<div align='left'> <img src='" + getLegendURL(layerVOs.get(i)) + "'" + "> </div>"));
//						infoPanel.add(new Html("<div align='left'> <img src='" + layerVOs.get(i).getLegendUrl() + "'" + "> </div>"));
						infoPanel.layout();
					}
				}

			}

			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}

		});
	}


	public static SelectionListener<ButtonEvent> fullScreenMap(final ADAMMapWrapper adamMapWrapper) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				removeBoxesFromMap(adamMapWrapper.getCurrentPosition());
				ADAMVisibilityController.styleVisibilityDisplay("CENTER");
				
			//	System.out.println("fullScreenMap: " );
			//	RootPanel.get("CENTER").setStyleName("big-" + ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT) + "-box border content");

				RootPanel.get("CENTER").setStyleName("big-box content");

				RootPanel.get("CENTER").add(setFullScreenMap(adamMapWrapper));
			}
		};
	}

	public static SelectionListener<ButtonEvent> smallScreenMap(final String position, final String color, final ADAMMapWrapper adamMapWrapper) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				RootPanel.get("CENTER").setStyleName("remove-box");
				RootPanel.get(position).setStyleName("small-box content");

				restoreBoxesContent(null, "MAP");
				
				ADAMVisibilityController.styleVisibilityDisplay(position);
				ADAMVisibilityController.restoreAdamViewVisibility();
				
				RootPanel.get(position).setStyleName("small-box content");
				RootPanel.get(position).add(setSmallScreen(adamMapWrapper));
			}
		};
	}

	private static ContentPanel setFullScreenMap(ADAMMapWrapper adamMapWrapper) {
		RootPanel.get("CENTER").setStyleName("big-box content");

		adamMapWrapper.setFullScreen();
		//System.out.println("joining");

		return adamMapWrapper.getPanel();
	}

	private static ContentPanel setSmallScreen(ADAMMapWrapper adamMapWrapper) {
//		RootPanel.get("BOTTOM_LEFT").setStyleName("medium-yellow-box border  content");
		adamMapWrapper.setSmallScreen();
//		restoreCustomContent();
		return adamMapWrapper.getPanel();
	}

	private static void removeAllLayers(final ADAMMapWrapper adamMapWrapper) {
		List<Long> gvids = new ArrayList<Long>();

		for (Long gvid : adamMapWrapper.getAdamResultVO().getLayersMap().keySet()) {
			LayerVO layerVo = adamMapWrapper.getAdamResultVO().getLayersMap().get(gvid);
			if ( !layerVo.isBaseLayer())
				gvids.add(gvid);

		}
		MapPanel mapPanel = adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel();
		for(Long gvid : gvids) {
			mapPanel.removeLayer(gvid);
			adamMapWrapper.getAdamResultVO().getLayersMap().remove(gvid);
		}
	}

	public static SelectionListener<MenuEvent> exportMapAsZip(final ADAMMapWrapper adamMapWrapper, final String language) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				exportMapAsZipAgent(adamMapWrapper, language);
			}
		};
	}
	
	private static void exportMapAsZipAgent(final ADAMMapWrapper adamMapWrapper, final String language) {
		ClientMapView cmv = createClientMapView(adamMapWrapper);
		final LoadingWindow loadingWindow = new LoadingWindow(HaitiLangEntry.getInstance(language).exportMap(), BabelFish.print().pleaseWait(), HaitiLangEntry.getInstance().exporting());
		loadingWindow.showLoadingBox();

		MapServiceEntry.getInstance().exportAsZip(cmv, true, 1024, 768, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				loadingWindow.destroyLoadingBox();
				com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}
			public void onFailure(Throwable e) {
				loadingWindow.destroyLoadingBox();
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}

	private static ClientMapView createClientMapView(ADAMMapWrapper adamMapWrapper) {
		String mapDivId = adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().getMapDivId();
		ClientBBox bbox = new OLBBoxRetriever(mapDivId).getBbox();
		ClientMapView cmv = new ClientMapView();
		cmv.setBbox(bbox);
//		List<LayerVO> vos = getSortedLayers(adamMapWrapper.getAdamResultVO().getLayersMap());
		List<LayerVO> vos = getLayers(adamMapWrapper.getAdamResultVO().getLayersMap());

		for (LayerVO vo : vos) {
			ClientGeoView cgv = new ClientGeoView();
			cgv.setBBox(bbox);
			cgv.setGetMapUrl(vo.getWmsURL());
			cgv.setLayerName(vo.getLayerName());
			cgv.setStyleName(vo.getStyleName());
			cgv.setTitle(vo.getLayerTitle());
			cgv.setHidden(false);
			cgv.setFenixCode(vo.getFenixCode());
			if ( vo.getLayerID() != null)
				cgv.setLayerId(vo.getLayerID());
				
			cgv.setIsQuickPrj(vo.getIsQuickPrj());	
			cmv.addLayer(cgv);
		}
		return cmv;
	}

	private static List<LayerVO> getSortedLayers(LinkedHashMap<Long, LayerVO> layersMap) {
		List<LayerVO> vos = new ArrayList<LayerVO>();
		for(Long id : layersMap.keySet()) {
			vos.add(layersMap.get(id));
		}

		List<LayerVO> result = new ArrayList<LayerVO>();

		for(int i = vos.size() -1 ; i >= 0; i--) {
			result.add(vos.get(i));
		}
		return result;
	}

	private static List<LayerVO> getLayers(LinkedHashMap<Long, LayerVO> layersMap) {
		List<LayerVO> vos = new ArrayList<LayerVO>();
		for(Long id : layersMap.keySet()) {
			vos.add(layersMap.get(id));
		}

		List<LayerVO> result = new ArrayList<LayerVO>();

		for(int i = 0 ; i < vos.size() ; i++) {
			result.add(vos.get(i));
		}
		return result;
	}

	private static Integer getOpacity(HaitiLayersPanel haitiLayersPanel, Long id) {
		Map<Long, HorizontalPanel> layerPanelMap = haitiLayersPanel.getLayerPanelsMap();
		HorizontalPanel layerPanel = layerPanelMap.get(id);
		return ((Slider)layerPanel.getData("slider")).getValue();
	}


	public static void addJoinDatset(final String position, ADAMQueryVO qvo, final long objID) {
		RootPanel.get(position).setStyleName("small-box content");

		/** TODO call method to create the view of the dataset **/
		ADAMServiceEntry.getInstance().joinDataset(qvo, new AsyncCallback<JoinDatasetVO>() {
			public void onSuccess(JoinDatasetVO jvo) {
			//	System.out.println("addJoinDatset");

				// is it right to take the global one?
				ADAMMapWrapper adamMapWrapper = ADAMWrapper.getAdamMapsWrapper().get("global");

				if ( adamMapWrapper == null)
					System.out.println("adamMapWrapper is null");
				else
					System.out.println("adamMapWrapper is not null");
					

				addJoinedLayer(adamMapWrapper, jvo, position, objID);
			}

			public void onFailure(Throwable arg0) {

			}

		});



	}


	public static void addJoinedLayer(final ADAMMapWrapper adamMapWrapper, final JoinDatasetVO joinDatasetVO, final String position, final long objID) {
		//System.out.println("addJoinedLayer");
		MapServiceEntry.getInstance().createJoinDatasetView(joinDatasetVO,new AsyncCallback<ClientGeoView>() {
			public void onSuccess(ClientGeoView clientGeoView) {

				//System.out.println("JoinedLayer objID: " + objID + " | objectWindowId:" + objectWindowId);


				if ( objID == objectWindowId ) {
					final VerticalPanel infoPanel = adamMapWrapper.getInfoPanel();

					// TODO: delete other layers
					removeAllLayers(adamMapWrapper);

					//System.out.println("clientGeoView.getStyleName():" + clientGeoView.getStyleName());
					clientGeoView.setStyleName(clientGeoView.getStyleName().toLowerCase());
					//System.out.println("clientGeoView.getLayerName():" + clientGeoView.getLayerName());
					adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().addLayer(clientGeoView);

					// track the layer
					LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
					vo.setLegendUrl(getLegendURL(vo));
					vo.setLayerID(new Long(0));
					vo.setIsQuickPrj(true);
					

					adamMapWrapper.getAdamResultVO().addLayer(vo);

					// ADD as selected layer
					NativeOLCalls.setMapPanel(adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel());
					adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().setSelectedLayer(vo);

					// global track of the layer (to clean them)
					ADAMWrapper.addProjection(joinDatasetVO.getDatasetViewName(), joinDatasetVO.getLayerJoinView());

					RootPanel.get(position).setStyleName("small-box content");
					adamMapWrapper.setCurrentPosition(position);

					RootPanel.get(position).add(adamMapWrapper.getPanel());
					RootPanel.get(position).getWidget(0).setTitle("MAP");
					adamMapWrapper.getPanel().setVisible(true);




					infoPanel.removeAll();
					infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));

	//				infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
					infoPanel.layout();



					/** ADDING THE GAUL0  AND LABELS **/
					List<String> layersToAdd = new ArrayList<String>();
					layersToAdd.add("layer_gaul0_simple");
////					layersToAdd.add("layer_gaul0");
//
//					MapServiceEntry.getInstance().getLayersByCode(layersToAdd, new AsyncCallback<List<ClientGeoView>>() {
//						public void onSuccess(List<ClientGeoView> clientGeoViews) {
//
//							int i=0;
//							for(ClientGeoView clientGeoView : clientGeoViews) {
////								if ( i == 1)
////									clientGeoView.setStyleName("label_30733188_adm0_name_000000");
//
//
//								adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().addLayer(clientGeoView);
//
//								// track the layer
//								LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
//
//								adamMapWrapper.getAdamResultVO().addLayer(vo);
//								i++;
//							}
//
//
//						}
//
//					@Override
//					public void onFailure(Throwable arg0) {
//									// TODO Auto-generated method stub
//		
//								}
//		
//							});
				}

			}

			public void onFailure(Throwable arg0) {
				System.out.println("onFailure");
			}

		});

	}

	private static LayerVO clientGeoViewToLayerVO(ClientGeoView clientGeoView){
		LayerVO vo = new LayerVO(clientGeoView.getTitle(), clientGeoView.getClientId(), clientGeoView.getLayerId(), clientGeoView.getOpacity(), clientGeoView.getLayerType().name(), clientGeoView.getLayerName());
		vo.setWmsURL(clientGeoView.getGetMapUrl());
		vo.setStyleName(clientGeoView.getStyleName());
		vo.setFenixCode(clientGeoView.getFenixCode());
		vo.setAbstractAbstract(clientGeoView.getAbstractAbstract());
		vo.setSource(clientGeoView.getSource());
		vo.setSourceContact(clientGeoView.getSourceContact());
		vo.setProvider(clientGeoView.getProvider());
		vo.setProviderContact(clientGeoView.getProviderContact());
		vo.setIsHidden(clientGeoView.isHidden());
		vo.setClientBBox(clientGeoView.getBBox());
		if ( clientGeoView.getLagendUrl() != null)
			vo.setLegendUrl(getLegendURL(vo));
		return vo;
	}

	/*** END MAPS **/


	




	/*** REPORT **/

	public static Command callView(final ADAMCurrentVIEW view, final String selectedTab) {
		return new Command() {
			public void execute() {
				callViewAgent(view, "callView", selectedTab);
			}
		};
	};
	
	/*public static void callViewAgent(final ADAMCurrentVIEW view, String calledFrom) {
		currentVIEW = view;
		
		System.out.println(" ************** callViewAgent START: currentlySelectedDatasetCode "+currentlySelectedDatasetCode + " calledFrom <<"+calledFrom+">>");
		
		if(currentVIEW.equals(ADAMCurrentVIEW.HOME)) {
			removeAllContents(true);
			callView(null, null,null, null, view, "callViewAgent", selectedTab); 
		}
		else {
			removeAllContents(false);
			ADAMVisibilityController.removeHomeContent();
			ADAMVisibilityController.removeHomeVisibility();
			//removeSecondaryMenu(); commented out so that the Welcome login message remains visible, when switching tabs
		   
			filters();
		    callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMBoxMaker.subSectorsSelected, view, "callViewAgent", selectedTab); 
		}
		
	};*/
	
	
	public static void callViewAgent(final ADAMCurrentVIEW view, String calledFrom, final String tab ) {
		currentVIEW = view;
		selectedTab = tab;
		
		System.out.println(" ************** callViewAgent START: currentVIEW "+currentVIEW + " || <<"+selectedTab+">>");
		
		//System.out.println(" ************** callViewAgent START: currentlySelectedDatasetCode "+currentlySelectedDatasetCode + " calledFrom <<"+calledFrom+">>");
		
		if(currentVIEW.equals(ADAMCurrentVIEW.HOME) || currentVIEW.equals(ADAMCurrentVIEW.LINKS)) {
			removeAllContents(true);
			callView(null, null,null, null, view, "callViewAgent", selectedTab); 
		}
		else {
			removeAllContents(false);
			ADAMVisibilityController.removeViewMenuVisibility();
			
			//ADAMVisibilityController.removeHomeVisibility();
			//removeSecondaryMenu(); commented out so that the Welcome login message remains visible, when switching tabs
		   
			filters();
		    callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMBoxMaker.subSectorsSelected, view, "callViewAgent", selectedTab); 
		}
		
	};

	public static Command createReport(final ADAMToolbar t) {
		return new Command() {
			public void execute() {
				removeSecondaryMenu();
				RootPanel.get("SECONDARY_MENU").add(t.buildReportPanel());
			}
		};
	};


	public static SelectionListener<ButtonEvent> createReport() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createReportAgent();
			}
		};
	}
	
	public static SelectionListener<MenuEvent> createReportMenu() {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				createReportAgent();
			}
		};
	}
	
	public static void createReportAgent() {
		ADAMReportBeanVO adamReportBeanVO = new ADAMReportBeanVO();
		String referencePeriod = new String();
		
		for(String date : baseDate){
			Date d = FieldParser.parseDate(date);
			referencePeriod = referencePeriod + " " + (d.getYear() + 1900);
		}
		
		adamReportBeanVO.setRightTitle(referencePeriod);
		
	
		adamReportBeanVO.setRightTitle(referencePeriod);
		//System.out.println("currentVIEW: " + currentVIEW.toString());
		switch(currentVIEW) {
			case ADAMVIEW:
				adamReportBeanVO.setReportType("CURRENTVIEW");
				createADAMVIEWReport(adamReportBeanVO);
			case FAOVIEW:
				adamReportBeanVO.setReportType("CURRENTVIEW");
				createADAMVIEWReport(adamReportBeanVO);
			case ASKADAM:
				for(String key : questionsMap.keySet()) {
					System.out.println("key: " + key);
				}
			//case DONORMATRIX:
		}
	
	}

	public static SelectionListener<ButtonEvent> createReport(final ComboBox<GaulModelData> reportList, final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createReportAgent(reportList, entryList, adamCodesList);
			}
		};
	}

	public static void createReportAgent(final ComboBox<GaulModelData> reportList, final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList) {
		ADAMReportBeanVO adamReportBeanVO = new ADAMReportBeanVO();
		String referencePeriod = new String();
	//	System.out.println("HERE: ");
		
		for(String date : baseDate){
			Date d = FieldParser.parseDate(date);
			referencePeriod = referencePeriod + " " + (d.getYear() + 1900);
		}
		
		adamReportBeanVO.setRightTitle(referencePeriod);
		
		String reportCode = reportList.getSelection().get(0).getGaulCode();
		if( reportCode.equalsIgnoreCase("ASKADAM" )){
			createASKADAMReport(reportList, entryList, adamCodesList, adamReportBeanVO);
		} else if(reportCode.equalsIgnoreCase("DONORPROFILE" )) {
			//System.out.println("DONORPROFILE .... ");
			adamReportBeanVO.setRightTitle("");
			adamReportBeanVO.setLeftTitle("");
			createDonorProfileReport(entryList, adamCodesList, adamReportBeanVO, referencePeriod);
		} else if ( reportCode.equalsIgnoreCase("CURRENTVIEW" )) {
			adamReportBeanVO.setRightTitle(referencePeriod);
			//System.out.println("currentVIEW: " + currentVIEW.toString());
			switch(currentVIEW) {
				case ADAMVIEW:
					createADAMVIEWReport(adamReportBeanVO);
				case ASKADAM:
					for(String key : questionsMap.keySet()) {
						System.out.println("key: " + key);
					}
				//case DONORMATRIX:
			}
		}

	}
	
	private static void createASKADAMReport(final ComboBox<GaulModelData> reportList, final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList, final ADAMReportBeanVO adamReportBeanVO) {
		
		
		GaulModelData selectedEntity = entryList.getSelection().get(0);
		String entity = selectedEntity.getGaulCode();

		GaulModelData codesEntity = adamCodesList.getSelection().get(0);
		String code = codesEntity.getGaulCode().substring(1 + codesEntity.getGaulCode().indexOf('_'));
		String label = codesEntity.getGaulLabel();
		
		// title
		adamReportBeanVO.setCenterTitle(label);

		Map<String, String> codes = new HashMap<String, String>();
		codes.put(code, label);

		//System.out.println("askADAM: " + entity + " | " + code + " | " + label);

		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		if ( entity.equalsIgnoreCase("Gaul_")) {
			adamReportBeanVO.setReportType("ASKADAM_COUNTRY");
			Boolean countryAdded = addCountryFilter(filters, codes);
			//System.out.println("askADAM countryAdded: " + countryAdded + " | " + filters);
			adamReportBeanVO.setLeftTitle("Country Profile");
			

		}

		else if ( entity.equalsIgnoreCase("Donor_")) {
			adamReportBeanVO.setReportType("ASKADAM_DONOR");
			Boolean donorAdded = addDonorFilter(filters, codes);
			//System.out.println("askADAM donorAdded: " + donorAdded + " | " + filters);
			adamReportBeanVO.setLeftTitle("Resource Partner Profile");
		}
		
		LinkedHashMap<String, ADAMQueryVO> qvos = ADAMQuestionMaker.buildReportQuestions(entity, code, label, filters, baseDate, entity, objectWindowId);

		ADAMServiceEntry.getInstance().createReport(qvos, adamReportBeanVO, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");

			}
			
			public void onFailure(Throwable result) {

			}
		});
	}

	private static void createADAMVIEWReport(final ADAMReportBeanVO adamReportBeanVO) {
		adamReportBeanVO.setReportType("CURRENTVIEW");
		//for(String key : currentUsedObjects.keySet()) {
		//	System.out.println("key: " + key);
		//}
		HashMap<String, ADAMResultVO> rvos = getCurrentViewBoxes();
		
		ADAMReportController.createLeftTitle(adamReportBeanVO);
		adamReportBeanVO.setRightTitle(ADAMReportController.createRightTitle());
		ADAMReportController.getCountriesDonors(adamReportBeanVO);
		adamReportBeanVO.setResources(rvos);
		
		//for(String key : rvos.keySet()) {
		//	System.out.println("key: " + key + " |" + rvos.get(key).getOutputType() + " | " + rvos.get(key).getResourceType());
		//}
		ADAMViewMenuBuilder.showWaitingPanel("Creating PDF...");
//		ADAMViewMenu.showWaitingPanel("Creating PDF...");
//
//		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportReport(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
//		loadingWindow.showLoadingBox();
		ADAMServiceEntry.getInstance().createReport(null, adamReportBeanVO, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				//System.out.println("result: " + result);
				ADAMViewMenuBuilder.hideWaitingPanel();
//				ADAMViewMenu.hideWaitingPanel();
				com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");

			}
			
			public void onFailure(Throwable result) {
				ADAMViewMenuBuilder.hideWaitingPanel();
//				ADAMViewMenu.hideWaitingPanel();
				//System.out.println("error: " + result);
			}
		});

	}
	
	

	private static void createDonorProfileReport(final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList, final ADAMReportBeanVO adamReportBeanVO, String referenceperiod) {
		
		GaulModelData selectedEntity = entryList.getSelection().get(0);	
	    String entity = selectedEntity.getGaulCode();

		GaulModelData codesEntity = adamCodesList.getSelection().get(0);
		String code = codesEntity.getGaulCode().substring(1 + codesEntity.getGaulCode().indexOf('_'));
		String label = codesEntity.getGaulLabel();
		
		// title
		adamReportBeanVO.setCenterTitle(label);
		adamReportBeanVO.setReportType("DONORPROFILE");
		
		Map<String, String> codes = new HashMap<String, String>();
		codes.put(code, label);

		//System.out.println("createDonorProfileReport: " + entity + " | " + code + " | " + label);

		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		if (entity.equalsIgnoreCase("Donor_")) {
			Boolean donorAdded = addDonorFilter(filters, codes);
			//System.out.println("askADAM donorAdded: " + donorAdded + " | " + filters);
			//adamReportBeanVO.setLeftTitle("Resource Partner Profile");
		}

		LinkedHashMap<String, ADAMQueryVO> qvos = buildDonorProfileQueries(entity, code, label, filters, baseDate, entity, objectWindowId);
	
		ADAMServiceEntry.getInstance().createReport(qvos, adamReportBeanVO, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");

			}
			
			public void onFailure(Throwable result) {

			}
		});
	}

private static LinkedHashMap<String, ADAMQueryVO> buildDonorProfileQueries(String entity, String code, String label, HashMap<String, List<String>> filters, List<String> dates, String type, final long objID) {
	LinkedHashMap<String, ADAMQueryVO> map = new LinkedHashMap<String, ADAMQueryVO>();
	List<ADAMQueryVO> qvos = new ArrayList<ADAMQueryVO>();
	
	//ADAMQueryVO q = ADAMQueryVOBuilder.totalODADonorCommitment(code, label, dates, ADAMBoxColors.green.name());
	//q.setDescription("The chart details the total ODA commitments for " + label + ".");
	//q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
	//q.setDescription("Chart 2 ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
	//qvos.add(q);
	
	ADAMQueryVO q = ADAMQueryVOBuilder.donorProfile(code, label, ADAMBoxColors.yellow.name());
	q.setDescription("The table details the resource partner profile for " + label + ".");
	//q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
	//q.setDescription("Chart 2 ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
	//qvos.add(q);
	
	q = ADAMQueryVOBuilder.donorProcesses(code, label, ADAMBoxColors.red.name());
	q.setDescription("The table details the resource partner processes for " + label +".");
	//q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
	//q.setDescription("Chart 2 ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
	qvos.add(q);
	
		
	Integer i=0;
	for (ADAMQueryVO qvo : qvos ) {
		map.put(i.toString(), qvo);
		i++;
	}
	
	//System.out.println("buildDonorProfileQueries map: " + map);
	return map;
}
	public static SelectionListener<ButtonEvent> createReport(final String reportType) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createReportAgent(reportType);
			}
		};
	}

	private static void createReportAgent(final String reportType) {
		HashMap<String, ADAMQueryVO> qvos = null;
		if ( reportType.equals("GLOBAL_REPORT"))
			qvos = createGlobalViewADAMQueryVO();
		
		//System.out.println("create report");
		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportReport(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
		loadingWindow.showLoadingBox();
//		ADAMServiceEntry.getInstance().createReport(qvos, reportType, new AsyncCallback<String>() {
//			public void onSuccess(String result) {
//				/** this is for the prevview **/
////				loadingWindow.destroyLoadingBox();
////				com.extjs.gxt.ui.client.widget.Window window = new com.extjs.gxt.ui.client.widget.Window();
////				ContentPanel cont = new ContentPanel();
////				cont.setHeaderVisible(false);
////				cont.setLayout(new FitLayout());
////				window.setLayout(new FitLayout());
////				window.setSize(800, 600);
////				HTML content = new HTML(result);
////				cont.add(content);
////				window.add(cont);
////				window.show();
//
//				loadingWindow.destroyLoadingBox();
//				com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//
//			}
//
//			public void onFailure(Throwable arg0) {
//				loadingWindow.destroyLoadingBox();
//			}
//		});
	}


	/**
	 * this method add the query to do for the report
	 *
	 * @return
	 */
	private static HashMap<String, ADAMQueryVO> createGlobalViewADAMQueryVO() {
		HashMap<String, ADAMQueryVO> qvos = new HashMap<String, ADAMQueryVO>();
//		qvos.put("mostFinancedAgriculturalSectors", mostFinancedAgriculturalSectors());
		qvos.put("MAP_GLOBAL_REPORT", createMapStandardQueryVO("GLOBAL_REPORT"));
//		qvos.put("GLOBAL_VIEW_TABLE", topDonors());

		return qvos;
	}


	private static ADAMQueryVO createMapStandardQueryVO(String mapType) {
		ADAMQueryVO qvo = new ADAMQueryVO();
		qvo.setOutputType("MAP_PREDIFINED_REPORT");
		qvo.setCodingSystem(mapType);
		qvo.setTitle("Global Map");
		qvo.setBoxColor("");
		return qvo;
	}





	/************* BOX CONTROLLERS ****/


	public static void removeAllContents(Boolean removeFilters){
		removeBoxes();
		if ( removeFilters )
			removeFilters();
		//removeQuestions();
		removeLoginMessage();
		ADAMMultiSelectionController.removeMultiSelections();
	}

	public static void removeCenter() {
		removeWidget("CENTER");
		RootPanel.get("CENTER").setStyleName("remove-box");
	}

	public static void removeBoxes() {
		removeWidget("TOP_LEFT");
		removeWidget("TOP_RIGHT");
		removeWidget("BOTTOM_LEFT");
		removeWidget("BOTTOM_RIGHT");
		removeWidget("MAP_LEFT");
		removeWidget("MAP_RIGHT");
		removeWidget("CENTER");

		RootPanel.get("TOP_LEFT").setStyleName("remove-box");
		RootPanel.get("TOP_RIGHT").setStyleName("remove-box");
		RootPanel.get("BOTTOM_LEFT").setStyleName("remove-box");
		RootPanel.get("BOTTOM_RIGHT").setStyleName("remove-box");
		RootPanel.get("MAP_LEFT").setStyleName("remove-box");
		RootPanel.get("MAP_RIGHT").setStyleName("remove-box");
		RootPanel.get("CENTER").setStyleName("remove-box");
		
		removeCustomObjects();
		removeAnalyseObjects();
		
		ADAMVisibilityController.removeAdamViewVisibility();
		
//		removeSecondaryMenu();
	}
	
	private static void removeCustomObjects() {
		ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM_TABLE");
		ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM_VIEW_TABLE");
		ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM");
		for(int i = 0 ; i <= CUSTOMS; i ++) {
			try {
				if (RootPanel.get("CUSTOM_1" + i).getWidgetCount() > 0){
					removeWidget("CUSTOM_1" + i);
					RootPanel.get("CUSTOM_1" + i).setStyleName("remove-box");
					ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM_1" + i);
				}
			}catch (Exception e) {
			}
				
		}
		
	}
	
	public static void removeAnalyseObjects() {
		ADAMVisibilityController.styleVisibilityNoDisplay("ADAM_ANALYSE_TABLE");
		
		removeWidget("ANALYSE_TOP");
		//removeWidget("ANALYSE_MIDDLE");
		//removeWidget("ANALYSE_BOTTOM");
		//removeWidget("ANALYSE_BOTTOM_LEFT");
		//removeWidget("ANALYSE_BOTTOM_RIGHT");
		
		RootPanel.get("ANALYSE_TOP").setStyleName("remove-box");
		//RootPanel.get("ANALYSE_MIDDLE").setStyleName("remove-box");
		//RootPanel.get("ANALYSE_BOTTOM").setStyleName("remove-box");
		//RootPanel.get("ANALYSE_BOTTOM_LEFT").setStyleName("remove-box");
		//RootPanel.get("ANALYSE_BOTTOM_RIGHT").setStyleName("remove-box");
		
	}
	public static void removeAllCustomObjects() {	
		for(int i = 0 ; i <= CUSTOMS; i ++) {
			try {
				if (RootPanel.get("CUSTOM_1" + i).getWidgetCount() > 0){
					removeWidget("CUSTOM_1" + i);
					RootPanel.get("CUSTOM_1" + i).setStyleName("remove-box");
					ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM_1" + i);
				}
			}catch (Exception e) {
			}
				
		}
		
		removeWidget("CUSTOM_TABLE");
		removeWidget("CUSTOM_VIEW_TABLE");
		
	}

	public static void removeMapTitles() {
		removeMapTitlesAgent("TOP_LEFT");
		removeMapTitlesAgent("TOP_RIGHT");
		removeMapTitlesAgent("BOTTOM_LEFT");
		removeMapTitlesAgent("BOTTOM_RIGHT");
		removeMapTitlesAgent("CENTER");
		
		ADAMVisibilityController.styleVisibilityNoDisplay("TOP_LEFT");
		ADAMVisibilityController.styleVisibilityNoDisplay("TOP_RIGHT");
		ADAMVisibilityController.styleVisibilityNoDisplay("BOTTOM_LEFT");
		ADAMVisibilityController.styleVisibilityNoDisplay("BOTTOM_RIGHT");
		ADAMVisibilityController.styleVisibilityNoDisplay("CENTER");
	}

	private static void removeMapTitlesAgent(String position) {
		if (RootPanel.get(position).getWidgetCount() > 0){
			RootPanel.get(position).getWidget(0).setTitle("");
			ADAMVisibilityController.styleVisibilityNoDisplay(position);
		}
	}




	public static void removeBoxesFromMap(String currentMapPosition) {
		removeWidgetFromMap("TOP_LEFT", currentMapPosition);
		removeWidgetFromMap("TOP_RIGHT", currentMapPosition);
		removeWidgetFromMap("BOTTOM_LEFT", currentMapPosition);
		removeWidgetFromMap("BOTTOM_RIGHT", currentMapPosition);
		removeWidgetFromMap("MAP_LEFT", currentMapPosition);
		removeWidgetFromMap("MAP_RIGHT", currentMapPosition);
		removeWidgetFromMap("CENTER", currentMapPosition);

		RootPanel.get("TOP_LEFT").setStyleName("remove-box");
		RootPanel.get("TOP_RIGHT").setStyleName("remove-box");
		RootPanel.get("BOTTOM_LEFT").setStyleName("remove-box");
		RootPanel.get("BOTTOM_RIGHT").setStyleName("remove-box");
		RootPanel.get("MAP_LEFT").setStyleName("remove-box");
		RootPanel.get("MAP_RIGHT").setStyleName("remove-box");
		RootPanel.get("CENTER").setStyleName("remove-box");
		
		ADAMVisibilityController.styleVisibilityNoDisplay(currentMapPosition);
		
		removeCustomObjects();
	}

	private static void removeWidget(String position) {
		if (RootPanel.get(position).getWidgetCount() > 0){
			//System.out.println("REMOVE WIDGET: " + position + " | " + RootPanel.get(position).getWidget(0).getTitle());
			if ( RootPanel.get(position).getWidget(0).getTitle().equalsIgnoreCase("MAP")) {
				//System.out.println("setVisible(false): " + position);
				RootPanel.get(position).getWidget(0).setVisible(false);
			}
			else {

//				RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//				RootPanel.get(position).clear();
				for( int i=0 ; i < RootPanel.get(position).getWidgetCount(); i++){
					//System.out.println("remove: " + position + " | " + RootPanel.get(position).getWidget(i).getTitle() );
					RootPanel.get(position).remove(i);
				}
			}
		}
	}

	private static void removeWidgetFromMap(String position, String currentMapPosition) {
		//System.out.println("currentMapPosition: " + currentMapPosition);
		if (RootPanel.get(position).getWidgetCount() > 0 && !position.equals(currentMapPosition) ){
			//System.out.println("--->: " + position + " | " + currentMapPosition + " | " + RootPanel.get(position).getWidget(0).getTitle());
			if ( RootPanel.get(position).getWidget(0).getTitle().equalsIgnoreCase("MAP")) {
				//System.out.println("setVisible(false): " + position);
				RootPanel.get(position).getWidget(0).setVisible(false);
			}
			else {
				//System.out.println("remove: " + position);
				RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
			}
		}
		else{
//			System.out.println("do nothing: " + position);
		}
		
	}



	public static void removeSecondaryMenu() {
		if(RootPanel.get("SECONDARY_MENU").getWidgetCount() > 0)
		   RootPanel.get("SECONDARY_MENU").remove(RootPanel.get("SECONDARY_MENU").getWidget(0));
		
	}

	public static void removeLoginMessage() {
		if (RootPanel.get("loginMessage").getWidgetCount() > 0)
			RootPanel.get("loginMessage").remove(RootPanel.get("loginMessage").getWidget(0));
	}

//	public static void removeKeyMessages() {
//		if (RootPanel.get("KEY_MESSAGES").getWidgetCount() > 0)
//			RootPanel.get("KEY_MESSAGES").remove(RootPanel.get("KEY_MESSAGES").getWidget(0));
//		ADAMVisibilityController.styleVisibilityNoDisplay("KEY_MESSAGES_TABLE");
//		ADAMVisibilityController.styleVisibilityNoDisplay("KEY_MESSAGES");
//	}
	
	


	/*public static void removeQuestions() {
		ADAMVisibilityController.styleVisibilityNoDisplay("QUESTIONS");
		for (int i = 11 ; i < 11 + QUESTIONS ; i++) {
			if (RootPanel.get("QUESTION_" + i).getWidgetCount() > 0)
				RootPanel.get("QUESTION_" + i).remove(RootPanel.get("QUESTION_" + i).getWidget(0));
			RootPanel.get("QUESTION_" + i).setStyleName("");
			ADAMVisibilityController.styleVisibilityNoDisplay("QUESTION_" + i);
		}
	}*/

	public static void removeFilters() {
		System.out.println(" removeFilters() CALLED ");
		
		//RootPanel.get("MAIN_SELECTORS").setStyleName("");
		//RootPanel.get("DATE_SELECTORS").setStyleName("");
		//RootPanel.get("OTHER_SELECTORS").setStyleName("");
	
		if (RootPanel.get("COUNTRY_SELECTOR").getWidgetCount() > 0)
			RootPanel.get("COUNTRY_SELECTOR").remove(RootPanel.get("COUNTRY_SELECTOR").getWidget(0));
		if (RootPanel.get("DONOR_SELECTOR").getWidgetCount() > 0)
			RootPanel.get("DONOR_SELECTOR").remove(RootPanel.get("DONOR_SELECTOR").getWidget(0));
		if (RootPanel.get("CLASSIFICATION").getWidgetCount() > 0)
			RootPanel.get("CLASSIFICATION").remove(RootPanel.get("CLASSIFICATION").getWidget(0));		
		if (RootPanel.get("SECTOR_SELECTOR").getWidgetCount() > 0)
			RootPanel.get("SECTOR_SELECTOR").remove(RootPanel.get("SECTOR_SELECTOR").getWidget(0));		
		if (RootPanel.get("SUB_SECTOR_SELECTOR").getWidgetCount() > 0)
			RootPanel.get("SUB_SECTOR_SELECTOR").remove(RootPanel.get("SUB_SECTOR_SELECTOR").getWidget(0));		
	//	if (RootPanel.get("SO_SELECTOR").getWidgetCount() > 0)
		//	RootPanel.get("SO_SELECTOR").remove(RootPanel.get("SO_SELECTOR").getWidget(0));
	//	if (RootPanel.get("CHANNEL_SELECTOR").getWidgetCount() > 0)
		//	RootPanel.get("CHANNEL_SELECTOR").remove(RootPanel.get("CHANNEL_SELECTOR").getWidget(0));
		//if (RootPanel.get("SWITCH_SELECTOR").getWidgetCount() > 0)
			//RootPanel.get("SWITCH_SELECTOR").remove(RootPanel.get("SWITCH_SELECTOR").getWidget(0));

		
		ADAMVisibilityController.removeKeyMessagesVisiblity();

	}
	
	
	public static void removeReferencePeriodFilter() {
		
		if (RootPanel.get("REFERENCE_PERIOD_FROM").getWidgetCount() > 0)
			RootPanel.get("REFERENCE_PERIOD_FROM").remove(RootPanel.get("REFERENCE_PERIOD_FROM").getWidget(0));
		
		RootPanel.get("REFERENCE_PERIOD_FROM").setStyleName("");
		
		if (RootPanel.get("REFERENCE_PERIOD_TO").getWidgetCount() > 0)
			RootPanel.get("REFERENCE_PERIOD_TO").remove(RootPanel.get("REFERENCE_PERIOD_TO").getWidget(0));
		
		RootPanel.get("REFERENCE_PERIOD_TO").setStyleName("");
		
		/*if (RootPanel.get("REFERENCE_PERIOD").getWidgetCount() > 0)
			RootPanel.get("REFERENCE_PERIOD").remove(RootPanel.get("REFERENCE_PERIOD").getWidget(0));
		RootPanel.get("REFERENCE_PERIOD").setStyleName("");*/
		
	}
	
	public static void removeAggregationColumn() {	
		if (RootPanel.get("AGGREGATION_COLUMN").getWidgetCount() > 0)
			RootPanel.get("AGGREGATION_COLUMN").remove(RootPanel.get("AGGREGATION_COLUMN").getWidget(0));
		RootPanel.get("AGGREGATION_COLUMN").setStyleName("");
		
	}

	
	


	/*public static void restoreQuestionsContent() {
		for (String position : questionsMap.keySet()) {

			ADAMVisibilityController.styleVisibilityDisplay(position);
			
			ADAMResultVO vo = questionsMap.get(position);
			ADAMBoxContent c = ADAMBoxContent.valueOf(vo.getOutputType());
			int questionIDX = Integer.parseInt( position.substring(1 + position.indexOf('_')));
			switch (c) {
				case BAR:
					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionChartPanel((questionIDX % QUESTIONS), vo, fullScreenQuestionsChart(position, vo.getBoxColor(), vo.getTitle())));
				break;
				case PIE:
					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionChartPanel((questionIDX % QUESTIONS), vo, fullScreenQuestionsChart(position, vo.getBoxColor(), vo.getTitle())));
				break;
				case TOP_COUNTRIES_BY_DONOR:
					VerticalPanel topCPanel = ADAMTableMaker.buildSmallQuestionTable(vo);
					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, vo, ADAMTableController.fullScreenQuestionTable(position, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), vo.getTitle()), topCPanel));
				break;
				case TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE:
					VerticalPanel cp = ADAMTableMaker.buildSmallQuestionTable(vo);
					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, vo, ADAMTableController.fullScreenQuestionTable(position, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), vo.getTitle()), cp));
				break;
				case FAVOURITE_PURPOSES_QUESTIONS_VIEW:
//					ContentPanel cp = ADAMTableMaker.buildFavouritePurposesQuestionsViewGrid(vo.getTableContents(), "335px", "175px", true);
//					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, ADAMTableController.fullScreenFavouritePurposesTable(position, vo.getBoxColor(), vo.getTitle()), cp));
				break;
			}
			RootPanel.get(position).setStyleName("question-" + vo.getBoxColor() + "-box content");
		}
	}*/

	
	public static SelectionListener<ButtonEvent> exportToExcel(final ADAMResultVO vo, final Boolean isAgroupedTable) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				exportExcelTableAgent(vo, isAgroupedTable);
			}
		};
	}
	
	public static SelectionListener<MenuEvent> exportExcelTable(final ADAMResultVO vo, final Boolean isAgroupedTable) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {

				exportExcelTableAgent(vo, isAgroupedTable);

			}
		};
	}
	
	public static SelectionListener<ButtonEvent> exportExcelTableButton(final ADAMResultVO vo, final Boolean isAgroupedTable) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				exportExcelTableAgent(vo, isAgroupedTable);

			}
		};
	}
	
	

	private static void exportExcelTableAgent(final ADAMResultVO vo, Boolean isAgroupedTable) {
		System.out.println("vo.getTableHeaders(): " + vo.getTableHeaders() );
		
		String title = "";
		
		if(vo.getTitle().contains("<br/><font color='#707071'>(Click on 'i' for an explanation on the formula used)</font>")) {
			System.out.println("exportExcelTableAgent: title contains the markup ");
			title = vo.getTitle().replace("<br/><font color='#707071'>(Click on 'i' for an explanation on the formula used)</font>", " ");
	    } else {
	    	title = vo.getTitle();
	    }
		
		System.out.println("exportExcelTableAgent: title END "+title);
		
		ADAMServiceEntry.getInstance().exportExcelTable(title, vo.getTableHeaders(), vo.getTableContents(), isAgroupedTable, new AsyncCallback<String>() {

			public void onSuccess(String result) {
				com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}

			public void onFailure(Throwable arg0) {

			}
		});
	}
	
	public static SelectionListener<MenuEvent> exportChartExcel(final ADAMQueryVO qvo, final ADAMResultVO vo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				exportChartExcelAgent(qvo,vo);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> exportChartExcelListener(final ADAMQueryVO qvo, final ADAMResultVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				exportChartExcelAgent(qvo,vo);
			}
		};
	}
	
	private static void exportChartExcelAgent(final ADAMQueryVO qvo, final ADAMResultVO vo) {
		List<List<String>> table = new ArrayList<List<String>>();
		LinkedHashMap<String, Map<String, Double>> chartValues = vo.getChartValues();
		
		String title = vo.getTitle();
		
		if(!title.contains("/")){
			title.replace("/", " or ");
		} 
			
		title += " " + setExportTitle(title);
		
		
//		System.out.println("chartValues" + chartValues);
		
		List<String> headers = new ArrayList<String>();
		// TODO: quick fix for the stack (DO A SERIE switcher)
		//System.out.println("vo.getOutputType(): " + vo.getOutputType());
		if ( vo.getOutputType().equals(ADAMBoxContent.BAR_STACK_OR_DONOR.toString()) || vo.getOutputType().equals(ADAMBoxContent.BAR_STACK_DAC_DONOR.toString()) || vo.getOutputType().equals(ADAMBoxContent.TIMESERIE.toString())) {
				
			headers.clear();
			if(vo.getOutputType().equals(ADAMBoxContent.BAR_STACK_OR_DONOR.toString())){
				headers.add("FAO Organizational Result (OR)");
			} else if(vo.getOutputType().equals(ADAMBoxContent.BAR_STACK_DAC_DONOR.toString())){
				headers.add("OECD-DAC Sub-Sector Code");
			}
			
			headers.add("Resource Partner");
			headers.add(qvo.getyLabel());
			
			//vo.setTableHeaders(headers);
			
			HashMap<String, String> fullLabels= vo.getxAxisFullLabels();
			
			System.out.println("exportChartExcelAgent = fullLabels "+fullLabels);
			
			for(String key : chartValues.keySet()) {
				for(String chartKey : chartValues.get(key).keySet()) {
					List<String> row = new ArrayList<String>();
					System.out.println("exportChartExcelAgent = label "+fullLabels.get(key));
					row.add(key);
					//row.add(fullLabels.get(key));
					row.add(chartKey);
					row.add(chartValues.get(key).get(chartKey).toString());
					table.add(row);
				}
			}
		}
		// TODO: FIX FOR THE SERIES this is just without the series...
		else {
			for(String key : chartValues.keySet()) {
				for(String chartKey : chartValues.get(key).keySet()) {
					List<String> row = new ArrayList<String>();
					row.add(chartKey);
					row.add(chartValues.get(key).get(chartKey).toString());
					table.add(row);
				}
			}
		}
		
		System.out.println("exportChartExcelAgent = "+title + " | "+table);
		
		ADAMServiceEntry.getInstance().exportExcelTable(title, headers, table, true, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				com.google.gwt.user.client.Window.open("../exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}
			
			public void onFailure(Throwable arg0) {
			 arg0.getMessage();
			}
			
		});
	}
	
	private static String setExportTitle(String title){
		StringBuilder selectedItems = new StringBuilder();
		String selectedRecipients = getDescription(ADAMBoxMaker.countriesSelected);
		String selectedResourcePartners = getDescription(ADAMBoxMaker.donorsSelected);
		String selectedSOs = getDescription(ADAMBoxMaker.soSelected);
		String selectedORs = getDescription(ADAMBoxMaker.orSelected);
		String selectedSectors = getDescription(ADAMBoxMaker.sectorsSelected);
		String selectedSubSectors = getDescription(ADAMBoxMaker.subSectorsSelected);
		
		boolean added = false;
		
		if(checkLabel(selectedRecipients, title)) {
			if(added)
				selectedItems.append("; ");
			
			selectedItems.append("Recipient Countries: "+ selectedRecipients);
			added = true;		
		}	
		if(checkLabel(selectedResourcePartners, title)) {
			if(added)
				selectedItems.append("; ");
			selectedItems.append("Resource Partners: "+ selectedResourcePartners);	
			added = true;
			
		}	
		if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)) {
			if(checkLabel(selectedSOs, title)){
				if(added)
					selectedItems.append("; ");
				
				selectedItems.append(selectedSOs);
				added = true;
			}
			if(checkLabel(selectedORs, title)){
				if(added)
					selectedItems.append("; ");
				
				selectedItems.append(selectedORs);
				added = true;
			}	
		} else {
			if(checkLabel(selectedSectors, title)){
				if(added)
					selectedItems.append("; ");
				
				selectedItems.append(selectedSectors);
				added = true;
			
			}	
			if(checkLabel(selectedSubSectors, title)){
				if(added)
					selectedItems.append("; ");
				
				selectedItems.append(selectedSubSectors);
				added = true;
				
			}	
		}
		
		if(!title.contains(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel())) {
			if(added)
				selectedItems.append("; ");
			
			selectedItems.append(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel());
			added = true;
		}
		
		
		if(added) {
			selectedItems.insert(0, "(");
			selectedItems.append(")");
		}
		return selectedItems.toString();
	}
	
	
	private static String getDescription(Map<String, String> map ) {
		if ( map == null )
			return "All";
		else if ( map.isEmpty() ) {
			return "All";
		}
		
		StringBuffer text = new StringBuffer();
		int i=0;
		for(String key : map.keySet()) {
			text.append(map.get(key));
			if (i != map.size() -1)
				text.append(", ");
			i++;
		}
		return text.toString();
	}
	
	private static Boolean checkLabel(String selectedItems, String title ) {
		if(!selectedItems.equals("All") && !title.contains(selectedItems) && !selectedItems.equals("All Selected"))
		 return true;
		
		else 
			return false;
		
		
	}
	
	public static SelectionListener<MenuEvent> exportChartImage(final ADAMResultVO vo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {

				exportChartImageAgent(vo);

			}
		};
	}
	
	public static SelectionListener<ButtonEvent> exportChartImageListener(final ADAMResultVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				exportChartImageAgent(vo);

			}
		};
	}
	
	private static void exportChartImageAgent(final ADAMResultVO vo) {
		com.google.gwt.user.client.Window.open("../adamObjects/" + vo.getExportImagePath(), "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//		com.google.gwt.user.client.Window.open("../adamObjects/" + vo.getBigImagePath(), "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
	}
	
	private static void exportCurrentViewAgent(final ADAMQueryVO qvo, final ADAMResultVO vo) {
		RootPanel.get("CENTER").setStyleName("remove-box");

		//System.out.println("MAP IS USED: " + containsMaps);

 		for(String position : currentUsedObjects.keySet() ) {
 			ADAMVisibilityController.styleVisibilityDisplay(position);
 			
 			Boolean putObject = true;
			//System.out.println("restoreBoxesContent: " + position);
			if ( containsMaps ) {
				if ( position.equals("BOTTOM_LEFT") || position.equals("BOTTOM_RIGHT"))
					putObject = false;
			}
			else {
				if ( position.equals("MAP_LEFT") || position.equals("MAP_RIGHT"))
					putObject = false;
			}
			if ( putObject ) {
				ADAMBoxContent type = currentUsedObjects.get(position);
				switch(type) {
					case TABLE:
						ADAMResultVO tableVo = tableMap.get(position);
						exportExcelTable(tableVo, true);
					break;

					case CHART:
						ADAMResultVO chartVo = chartMap.get(position);
						exportChartImage(chartVo);
					break;

					case MAP:
						/** TODO: CHANGE IT **/
//						ADAMResultVO mapVo = mapMap.get(position);
						ADAMMapWrapper adamMapWrapper = ADAM.getAdamWrapper().getAdamMapsWrapper().get("global");
						if ( adamMapWrapper != null ) {
							exportMapAsZipAgent(adamMapWrapper, "EN");
						}
					break;

					case VENN_PRIORITIES:
						//System.out.println("restoreBoxesContent vennMap: " + position);
						ADAMResultVO vennVo = vennMap.get(position);
						RootPanel.get(position).setStyleName("small-box content");
						RootPanel.get(position).add(ADAMVennMaker.buildSmallPriorityVenn(vennVo.getTitle(), vennVo.getSmallImagePath(), ADAMConstants.SMALL_WIDTH, ADAMConstants.SMALL_HEIGHT, vennVo, position, ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES), ADAMVennController.fullScreenVenn(position, ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES), vennVo.getTitle())));
					break;
				}
			}

		}
	}
	

	/**** cleaning function **/

	public static void cleanLayersAndViews() {
		if ( ADAMWrapper.getDatasetProjectionViews() != null) {
			//System.out.println("cleaning projections and layers: " + ADAMWrapper.getDatasetProjectionViews());
			ADAMServiceEntry.getInstance().cleanLayersAndViews(ADAMWrapper.getDatasetProjectionViews(), new AsyncCallback() {
				public void onFailure(Throwable arg0) {
				}
				public void onSuccess(Object arg0) {
				}
			});
			for ( String key : ADAMWrapper.getDatasetProjectionViews().keySet()) {
				//System.out.println("depublish layer");
				MapServiceEntry.getInstance().depublishLayer(ADAMWrapper.getDatasetProjectionViews().get(key), new AsyncCallback() {
					public void onFailure(Throwable arg0) {
						//System.out.println("depublished layer");
					}
					public void onSuccess(Object arg0) {
					}
				});			
				//System.out.println("depublish style");
				MapServiceEntry.getInstance().depublishStyle(ADAMWrapper.getDatasetProjectionViews().get(key), new AsyncCallback() {
					public void onFailure(Throwable arg0) {
						//System.out.println("depublished style");
					}
					public void onSuccess(Object arg0) {
					}
				});
			}
		}

	}

	
	/*public static SelectionListener<ButtonEvent> refreshShowProjectsButtonEvent(final ADAMShowProjectsPanel adamShowProjectsPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				refreshShowProjectsAgent(adamShowProjectsPanel);
			}
		};
	}*/
	
	/*public static void refreshShowProjectsAgent(ADAMShowProjectsPanel adamShowProjectsPanel) {
		
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;

		Map<String, String> sectorList = new HashMap<String, String>();
		Map<String, String> purposesList = new HashMap<String, String>();
	
		Html q = new Html("<div align='center' style='font-size: 20px; color: #224466;'>Loading... <IMG SRC='adam-images/loading.gif'></div>");


		adamShowProjectsPanel.getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(q.getHtml());
		adamShowProjectsPanel.getAdamShowProjectsTable().getSummaryHtml().setHtml("");

		adamShowProjectsPanel.getPanel().layout();
		

		// check which checkbox is selected
		if (adamShowProjectsPanel.getAdamShowProjectsFilters().getFaoViewCheckBox().getValue()) {
			purposesList = ADAMConstants.faoViewPurposes;
		}
		else if ( adamShowProjectsPanel.getAdamShowProjectsFilters().getSectorsViewCheckBox().getValue()) {
			TreePanel treePanel = adamShowProjectsPanel.getAdamShowProjectsFilters().getSector().getMultiSelection().treePanel;
			if ( treePanel.getStore().getAllItems().size() != treePanel.getCheckedSelection().size()){
				sectorList = getSelectionAgent(treePanel);
			}
		}
		
		refreshProjectsAgent(adamShowProjectsPanel, donorList, gaulList, sectorList, purposesList);
		
		
	}*/
	public static Map<String, String> getSelectionAgent(final TreePanel<ListItemTree> treePanel) {
		Map<String, String> map = new HashMap<String, String>();
		List<ListItemTree> items = treePanel.getCheckedSelection();
		for (ListItemTree itemSelected : items) {
			String code = itemSelected.getCode();
			String label = itemSelected.getLabel();
			map.put(code, label);
		}
		return map;
	}
	
	/*private static void refreshProjectsAgent(ADAMShowProjectsPanel adamShowProjectsPanel, Map<String, String> donorList, Map<String, String> gaulList, Map<String, String> sectorList, Map<String, String> purposesList) {

		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		Boolean countryAdded = addCountryFilter(filters, gaulList);
		//System.out.println("countryAdded: " + countryAdded);
		Boolean donorAdded = addDonorFilter(filters, donorList);
		//System.out.println("donorAdded: " + donorAdded);
		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		//System.out.println("sectorAdded: " + sectorAdded);
		Boolean purposeAdded =  addPurposeCodesFilter(filters, purposesList);
		//System.out.println("purposesAdded: " + purposeAdded);
		
		if( !countryAdded && !donorAdded && !sectorAdded) {
			*//*** global view **//*
			// do nothing
			ADAMViewMenuBuilder.hideWaitingPanel();
//			ADAMViewMenu.hideWaitingPanel();
			FenixAlert.info("List of Projects", "The query returns too many projects");
		}
		else {
			//System.out.println("RefreshProjects PROJECTS");
			refreshProjectsAgentView(adamShowProjectsPanel, filters);
		}
		
	}*/
	

	
	/*private static void refreshProjectsAgentView(final ADAMShowProjectsPanel adamShowProjectsPanel, final HashMap<String, List<String>> filters) {
		ADAMQueryVO qSize = ADAMQueryVOBuilder.getRowsCount(baseDate, filters);
		ADAMServiceEntry.getInstance().askADAM(qSize, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				//System.out.println("vo size: " + vo.getRows());
				
				if ( vo.getRows() < 15000 ) {
					final Long rows = vo.getRows();
					ADAMQueryVO q = ADAMQueryVOBuilder.getProjects(ADAMQueryVOBuilder.getCRSSelectProjectsList(), baseDate, filters, false);
					ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
						public void onSuccess(ADAMResultVO vo) {

							
							ADAMVisibilityController.restoreProjectsVisibility();
							
							adamShowProjectsPanel.getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(vo.getTableHTML());
							adamShowProjectsPanel.getAdamShowProjectsTable().getSummaryHtml().setHtml("<div class='content'><b>Total Number of Projects: " + rows + "</b><div>");

							adamShowProjectsPanel.getPanel().layout();
							
							// reload export listener	
							adamShowProjectsPanel.getToolbar().getExcel().removeAllListeners();
							adamShowProjectsPanel.getToolbar().getExcel().addSelectionListener(ADAMController.exportExcelTable(vo, false));
						}
						public void onFailure(Throwable e) {
							ADAMViewMenuBuilder.hideWaitingPanel();
//							ADAMViewMenu.hideWaitingPanel();
//							FenixAlert.error(BabelFish.print().error(), e.getMessage());
						}
					});
				}
				else {
					ADAMViewMenuBuilder.hideWaitingPanel();
//					ADAMViewMenu.hideWaitingPanel();
					FenixAlert.info("List of Projects", "The query returns too many projects: " + vo.getRows());
				}
				
			}
			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}*/

	
	
//	private static void showProjectsAgent(Map<String, String> donorList, Map<String, String> gaulList, Map<String, String> sectorList) {
//
//		
//		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//
//		Boolean countryAdded = addCountryFilter(filters, gaulList);
//		System.out.println("countryAdded: " + countryAdded);
//		Boolean donorAdded = addDonorFilter(filters, donorList);
//		System.out.println("donorAdded: " + donorAdded);
//		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
//		System.out.println("sectorAdded: " + sectorAdded);
//		
//		if( !countryAdded && !donorAdded && !sectorAdded) {
//			/*** global view **/
//			// do nothing
//			ADAMViewMenu.hideWaitingPanel();
//			FenixAlert.info("List of Projects", "The query returns too many projects");
//		}
//		else {
//			ADAMQueryVO qSize = ADAMQueryVOBuilder.getRowsCount(baseDate, filters);
//			ADAMServiceEntry.getInstance().askADAM(qSize, new AsyncCallback<ADAMResultVO>() {
//				public void onSuccess(ADAMResultVO vo) {
//					System.out.println("vo size: " + vo.getRows());
//					
//					if ( vo.getRows() < 15000 ) {
//						final Long rows = vo.getRows();
//						ADAMQueryVO q = ADAMQueryVOBuilder.getProjects(baseDate, filters);
//						ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
//							public void onSuccess(ADAMResultVO vo) {
//								ADAMViewMenu.hideWaitingPanel();
//								
//								Html table = new Html();
//								table.setHtml(vo.getTableHTML());
//								
//								Window window = new Window();
//								window.add(table);
//								window.setHeaderVisible(true);
//								window.setHeading("Total Number of Projects: " + rows);
//								
//								window.setHeight(640);
//								window.setWidth(900);
//								window.show();
//								window.setScrollMode(Scroll.AUTO);
//								
//							}
//							public void onFailure(Throwable e) {
//								ADAMViewMenu.hideWaitingPanel();
//								FenixAlert.error(BabelFish.print().error(), e.getMessage());
//							}
//						});
//					}
//					else {
//						ADAMViewMenu.hideWaitingPanel();
//						FenixAlert.info("List of Projects", "The query returns too many projects: " + vo.getRows());
//					}
//					
//				}
//				public void onFailure(Throwable e) {
//					FenixAlert.error(BabelFish.print().error(), e.getMessage());
//				}
//			});
//		}
//		
//	}
	
	
	
	public static SelectionListener<IconButtonEvent> showInfo(final String text) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				Html html = new Html();
				if ( text != null )
					html.setHtml(text);
				else 
					html.setHtml(""); //html.setHtml("No additional Info"); 
				
				ContentPanel panel = new ContentPanel();
				panel.setHeaderVisible(false);
				panel.setHeight(300);
				panel.setWidth(490);
				panel.setScrollMode(Scroll.AUTO);

				panel.add(html);
				
				
				Window window = new Window();
				window.setHeight(280);
				window.setWidth(495);
				
			
				window.add(panel);
				window.setHeaderVisible(true);
				window.setHeading("Additional Information");
				
				window.show();
//				window.setScrollMode(Scroll.AUTO);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showReadMoreWindow(final String text) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Html html = new Html();
				if ( text != null )
					html.setHtml(text);
				else 
					html.setHtml(""); //html.setHtml("No additional Info"); 
				
				ContentPanel panel = new ContentPanel();
				panel.setHeaderVisible(false);
				panel.setHeight(300);
				panel.setWidth(490);
				panel.setScrollMode(Scroll.AUTO);

				panel.add(html);
				
				
				Window window = new Window();
				window.setHeight(280);
				window.setWidth(495);
				
			
				window.add(panel);
				window.setHeaderVisible(true);
				window.setHeading("Additional Information");
				
				window.show();
//				window.setScrollMode(Scroll.AUTO);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showReadMoreSelectionListener(final String text, final String header, final int height, final int width, final Boolean setPanelHeight) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				 openReadMoreWindow(text,header,height, width, setPanelHeight);
			}
		};
	}
	
	public static Listener<ComponentEvent> showReadMoreListener(final String text, final String header, final int height) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				openReadMoreWindow(text,header,height, 0, false);
			}
		};
	}
	
	private static void openReadMoreWindow(final String text, final String header, final int height, final int width, boolean setPanelHeight){
		Html html = new Html();
		if ( text != null )
			html.setHtml(text);
		else 
			html.setHtml(""); //html.setHtml("No Additional Information");
		
		Window window = new Window();
		
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		if(setPanelHeight) {
			panel.setHeight(520);
		}	
		
		panel.setScrollMode(Scroll.AUTO);
		
		if(width == 0) {
			panel.setWidth(680);
			window.setWidth(695);
		}
		else {
			panel.setWidth(width);
			window.setWidth(width+15);
		}	
		
		
    	panel.add(html);
			
	
		window.setHeight(height);
		//window.setScrollMode(Scroll.AUTO);
		window.add(panel);
		window.setHeaderVisible(true);
		window.setHeading(header);
		
		window.show();
	}

	public static SelectionListener<ButtonEvent> showReadMoreForUrl(final String url) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				com.google.gwt.user.client.Window.open(url, "_blank","");	
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> showInfo(final String text, final String header, final int height) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				Html html = new Html();
				if ( text != null )
					html.setHtml(text);
				else 
					html.setHtml(""); //html.setHtml("No Additional Information");
				
				ContentPanel panel = new ContentPanel();
				panel.setHeaderVisible(false);
				//panel.setHeight(590);
				panel.setWidth(680);
				panel.setScrollMode(Scroll.AUTO);

				panel.add(html);
				
				
				Window window = new Window();
				window.setWidth(695);
				window.setHeight(height);
				
				
			
				window.add(panel);
				window.setHeaderVisible(true);
				window.setHeading(header);
				
				window.show();
//				window.setScrollMode(Scroll.AUTO);
			}
		};
	}

	public static SelectionListener<MenuEvent>  changeTitleDescription(final ADAMResultVO vo) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				changeTitleDescriptionAgent("", vo.getTitle(), vo.getDescription());

			}
		};
	}
	
	public static void changeTitleDescriptionAgent(String position, String title, String description) {
		ADAMChangeInformation adam = new ADAMChangeInformation();
		adam.build(title, description);
	}
	
	
	public static SelectionListener<ButtonEvent> createFAOView(final Button button) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				//System.out.println("ADAMVisibilityController.getVisibility(FAO_TABLE): " + ADAMVisibilityController.getVisibility("FAO_TABLE"));
				
				if (ADAMVisibilityController.getVisibility("FAO_TABLE").equals("true")) {
					button.setText("SHOW FAO Implementation(OECD/DAC)");
					ADAMVisibilityController.removeFAOVisibility();
				}
				else {
					button.setText("HIDE FAO Implementation(OECD/DAC)");
					createFAOViewAgent(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected);
				}

			}
		};
	}
	
	public static SelectionListener<MenuEvent> createFAOView(final MenuItem menuItem) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				
				//System.out.println("ADAMVisibilityController.getVisibility(FAO_TABLE): " + ADAMVisibilityController.getVisibility("FAO_TABLE"));
				
			//	if (ADAMVisibilityController.getVisibility("FAO_TABLE").equals("true")) {
				//	menuItem.setText("Add FAO Implementation (OECD/DAC)");
					//ADAMVisibilityController.removeFAOVisibility();
				//}
				//else {
					//menuItem.setText("Remove FAO Implementation (OECD/DAC)");
					createFAOViewAgent(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected);
				//}

			}
		};
	}
	
	public static void createFAOViewAgent(final Map<String, String> donorList, final Map<String, String> gaulList, final Map<String, String> sectorList) {
		//System.out.println("createFAOViewAgent");

		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		Boolean countryAdded = addCountryFilter(filters, gaulList);
		//System.out.println("countryAdded: " + countryAdded);
		Boolean donorAdded = addDonorFilter(filters, donorList);
		//System.out.println("donorAdded: " + donorAdded);
		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		//System.out.println("sectorAdded: " + sectorAdded);

		//System.out.println("filters: " + filters);
		
		// ADDING FAO FILTER

		addFAOFilter(filters);

		ADAMVisibilityController.removeFAOVisibility();
		ADAMVisibilityController.restoreFAOVisibility();
			
			
		if( donorAdded && !countryAdded && !sectorAdded) {
			createDonorFAOView(filters);
		}
		else if( countryAdded && !donorAdded && !sectorAdded) {
			createFAOCountryView(filters);
		}
	
		else if( countryAdded && donorAdded && !sectorAdded) {
			createCountryDonorFAOView(filters);
		}
	
		// TODO: with the sectors
		else if( !donorAdded && !countryAdded && sectorAdded) {
			FenixAlert.info("Sector Selection", "Please do not select any sector");
		}
		
		else if( donorAdded && !countryAdded && sectorAdded) {
			FenixAlert.info("Sector Selection", "Please do not select any sector");
		}
		else if( countryAdded && !donorAdded && sectorAdded) {
			FenixAlert.info("Sector Selection", "Please do not select any sector");
		}
		else if ( countryAdded && donorAdded && sectorAdded ) {
			FenixAlert.info("Sector Selection", "Please do not select any sector");
		}
		
		
		else if( !countryAdded && !donorAdded && !sectorAdded) {
			createGlobalFAOView(filters);
		}
			
		} 
	
	

	private static void createDonorFAOView(HashMap<String, List<String>> filters ){
	
		//System.out.println("createDonorFAOView objectWindowId: " + objectWindowId );
	
		ADAMController.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewMajorRecipients("Major Recipients (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");
		
		ADAMController.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");
	}
	
	private static void createFAOCountryView(HashMap<String, List<String>> filters ){
		
		//System.out.println("createFAOCountryView objectWindowId: " + objectWindowId );
	
		ADAMController.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewMajorDonors("Top Resource Partners (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");
	
		ADAMController.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");
	}
	
	private static void createCountryDonorFAOView(HashMap<String, List<String>> filters ){
		
		//System.out.println("createCountryDonorFAOView objectWindowId: " + objectWindowId );
	

		ADAMTableController.addTable("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewCountriesAndSectors("Major Recipients/Sectors (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO)), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");
		
		ADAMController.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");
	}
	
	private static void createGlobalFAOView(HashMap<String, List<String>> filters ){
		
		//System.out.println("createCountryDonorFAOView objectWindowId: " + objectWindowId );
	
	
//		ADAMController.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), ADAMQueryVOBuilder.viewMajorRecipients("Major Recipients (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT)), objectWindowId);
		
		ADAMController.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewMajorDonors("Top Resource Partners (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");
		
		ADAMController.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO Implemented Projects)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false), objectWindowId, true, removeFAOImplementation(), "FAO Implementation");

	}
	
	private static void addFAOFilter(HashMap<String, List<String>> filters) {
		List<String> faoFilter = new ArrayList<String>();
		faoFilter.add("41301");
		filters.put("channelcode",faoFilter );
	}

	

  public static SelectionListener<MenuEvent>  switchResource(final String position, final String color, final ADAMQueryVO q) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
//				objectWindowId = objectWindow.getNext();3
			
				System.out.println("switchResource -------------------- q.getOutputType(): " + q.getResourceType());
				ADAMBoxContent type = ADAMBoxContent.valueOf(q.getResourceType());
				//System.out.println("TYPE: " + type);
				switch(type) {
				case TABLE:
					ADAMTableController.addTable(position, color, q, objectWindowId);
				break;

				case CHART:
					ADAMController.addChart(position, color, q, objectWindowId);
				break;
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					ADAMMapController.addGoogleMapsADAMView(q, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, color, position, true, true, objectWindowId);
				break;
				}
				
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent>  switchResourceListener(final String position, final String color, final ADAMQueryVO q) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
//				objectWindowId = objectWindow.getNext();3
			
				System.out.println("switchResource -------------------- q.getOutputType(): " + q.getResourceType());
				ADAMBoxContent type = ADAMBoxContent.valueOf(q.getResourceType());
				//System.out.println("TYPE: " + type);
				switch(type) {
				case TABLE:
					ADAMTableController.addTable(position, color, q, objectWindowId);
				break;

				case CHART:
					ADAMController.addChart(position, color, q, objectWindowId);
				break;
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					ADAMMapController.addGoogleMapsADAMView(q, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT, color, position, true, true, objectWindowId);
				break;
				}
				
			}
		};
	}
	
	public static void showHomePage() {
		ADAMVisibilityController.restoreHomeViewVisibility();
		ADAMHome.build();
	}
	
	public static void showLinksPage() {
		ADAMVisibilityController.restoreLinksVisibility();
		ADAMUsefulLinks.build();
	}

	public static SelectionChangedListener<DatasetModel> setCurrentDatasetSelection(final ComboBox<DatasetModel> list) {
		return new SelectionChangedListener<DatasetModel>() {
			public void selectionChanged(SelectionChangedEvent<DatasetModel> se) {
				
				String selectedDatasetCode = list.getSelection().get(0).getCode();
				
				//GOOGLE Analytics
				//GoogleAnalyticsController.trackEvent("Data Source Selection", list.getSelection().get(0).getName(), "");	
				
				if(ADAMSelectedDataset.getSelectedDataset(selectedDatasetCode)!=null) {
					currentlySelectedDatasetCode = ADAMSelectedDataset.getSelectedDataset(selectedDatasetCode);
				}
				else 
					FenixAlert.error("setSelectedDatasetToSession", "selected dataset code is undefined");
				
	
				ADAMDatasetController.callDatasetFilterAgent(currentVIEW);
				//removeFilters();
				//filters();
				
				//restoreAndClearDropDownsAndFilters();
				//filters();
				//ADAMController.callViewAgent(currentVIEW);
				
				
				//FenixAlert.info("currentlySelectedDatasetCode ", currentlySelectedDatasetCode.name());
				/*ADAMServiceEntry.getInstance().setSelectedDatasetToSession(selectedDataset, new AsyncCallback() {
					public void onSuccess(Object result) {
						ADAMServiceEntry.getInstance().getSelectedDatasetCodeFromSession(new AsyncCallback<String>() {
							public void onSuccess(String result) {
								FenixAlert.info("Session Dataset is", result);
							}
							public void onFailure(Throwable e) {
								FenixAlert.error("getSelectedDatasetCodeFromSession ERROR", e.getMessage());
							}
						});
					}
					public void onFailure(Throwable e) {
						FenixAlert.error("setSelectedDatasetToSession ERROR", e.getMessage());
					}
				});*/
	
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> closeCenter() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMController.removeCenter();
			}
		};
	}
	
	public static String getStringReferenceDate() {
		StringBuffer title = new StringBuffer();
		
		String fromDate = ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel();
		String toDate = ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel();
		
		
		//System.out.println("From date:" + fromDate);
		//System.out.println("To date:" + toDate);
		if( fromDate.equals(toDate)) {
			title.append(fromDate);
		}
		else {
			title.append(fromDate + " to " + toDate);
		}
		
		
		return title.toString();
	}
	
	public static SelectionListener<IconButtonEvent> showPrioritiesDisclaimer() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				com.google.gwt.user.client.Window.open("adam-docs/adam_priorities_disclaimer.pdf", "_blank","");
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> removeFAOImplementation() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVisibilityController.removeFAOVisibility();
			}
		};
	}
	
	public static String getCodingSystemCode(final ADAMSelectedDataset currentlySelectedDatasetCode){
        String codingSystemCode = "";
		
        //System.out.println("getCodingSystemCode:  currentlySelectedDatasetCode = '"+currentlySelectedDatasetCode+"'");
        
        
		if(currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			codingSystemCode = "ADAM_FPMIS_CODES";
		else
			codingSystemCode = "ADAM_CODES";
		
		// System.out.println("getCodingSystemCode:  codingSystemCode = "+codingSystemCode);
	        
		 
		return codingSystemCode;
	}
	
	
}