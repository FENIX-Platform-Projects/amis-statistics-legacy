package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.birt.chart.model.attribute.VerticalAlignment;
import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.client.view.ADAMComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMMapWrapper;
import org.fao.fenix.web.modules.adam.client.view.ADAMOLPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQuantityColumn;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMTabMenu;
import org.fao.fenix.web.modules.adam.client.view.ADAMToolbar;
import org.fao.fenix.web.modules.adam.client.view.ADAMViewMenu;
import org.fao.fenix.web.modules.adam.client.view.ADAMWrapper;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMChangeInformation;
import org.fao.fenix.web.modules.adam.client.view.dataentry.DonorProfileDataManagementWindow;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMDonorProfileMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMQuestionMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMVennMaker;
import org.fao.fenix.web.modules.adam.client.view.projects.ADAMShowProjectsPanel;
import org.fao.fenix.web.modules.adam.client.view.projects.ADAMShowProjectsTable;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxColors;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
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
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.venn.common.services.VennServiceEntry;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.CheckChangedListener;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMController_GWTSplit {

//	public static Boolean containsMaps;
//
//	public final static Map<String, ADAMResultVO> chartMap;
//
//	public final static Map<String, ADAMResultVO> tableMap;
//
//	public final static Map<String, ADAMResultVO> questionsMap;
//
//	public final static Map<String, ADAMResultVO> mapMap;
//
//	public final static Map<String, ADAMResultVO> vennMap;
//
//	public final static Map<String, ADAMBoxContent> currentUsedObjects;
//
//	public final static int QUESTIONS = 6;
//
//	public static List<String> baseDate = new ArrayList<String>();
//
//	public static String datesetCode;
//
//	public final static Map<String, ADAMResultVO> currentCustom;
//	
//	public final static Map<String, ADAMResultVO> faoMap;
//	
//	public final static int CUSTOMS = 10;
//	
//	public final static int FAO = 8;
//
//	public static String globalTitle = "Global view";
//
//	// code, label
//	public static GaulModelData crs_aggregationColumn;
//
//	// aggregationType
//	public static String crs_aggregationType;
//
//	// This is manage the objectes in the view
//	// each time is changed a view, the counter is increased and it's associated through the objectWindowID to the objectes in the view
//	//
//	// each time the object has to be added there it's checked if the objectWindowId it's the same of the object to be inserted
//	// if the view didn't change, the object is inserted
//	//
//	// TODO: the counter is not increased when there is a call to the fullScreenView and the smallScreenView of the objects
//	protected static Counter objectWindow = new Counter(100);
//
//	static long objectWindowId;
//
//
//	public static ADAMCurrentVIEW currentVIEW;
//
//	static {
//		chartMap = new HashMap<String, ADAMResultVO>();
//		tableMap = new HashMap<String, ADAMResultVO>();
//		questionsMap = new HashMap<String, ADAMResultVO>();
//		mapMap = new HashMap<String, ADAMResultVO>();
//		vennMap = new HashMap<String, ADAMResultVO>();
//		currentUsedObjects = new HashMap<String, ADAMBoxContent>();
//		currentCustom = new HashMap<String, ADAMResultVO>();
//		faoMap = new HashMap<String, ADAMResultVO>();
//		//baseDate.add("01-01-2008");
//		//baseDate.add("2008-01-01");		
//	    baseDate.add("2009-01-01");		
//		datesetCode = "ADAM_CRS";
//		containsMaps = true;
//		currentVIEW = ADAMCurrentVIEW.ADAMVIEW;
//		crs_aggregationColumn= new GaulModelData("Disbursement", "usd_disbursement");
//		crs_aggregationType = "SUM";
//	}
//
////	public String getCode(String code){
////		return code;
////	}
//	
//	public static Listener<ComponentEvent> openADAM(final String position, final ADAMCurrentVIEW view) {
//		return new Listener<ComponentEvent>() {
//			public void handleEvent(ComponentEvent ce) {
//				  GWT.runAsync(new RunAsyncCallback() {
//					  public void onSuccess() {
//						System.out.println("openADAM()");
//						ADAMController_GWTSplit.callViewAgent(view);
//						RootPanel.get(position).setStyleName("tab_menu_selected");
//						ADAMTabMenu.setDefaultStyle(position);
//					  }
//
//					  public void onFailure(Throwable caught) {
//					  }
//				  });
//			}
//		};
//	}
//	
////	  GWT.runAsync(new RunAsyncCallback() {
////		  public void onSuccess() {
////		  }
////		  public void onFailure(Throwable caught) {
////		  }
////	  });
//		 
//	
//	public static void addQuestionChart(final int questionIDX, final String entityLabel, final String color, final ADAMQueryVO q, final long objID) {
//		final String position = "QUESTION_" + questionIDX;
//		RootPanel.get(position).setStyleName("question-" + color + "-box border content");
//		RootPanel.get(position).add(ADAMQuestionMaker.buildWaitingPanel(questionIDX));
//		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
//			public void onSuccess(ADAMResultVO vo) {
//				HorizontalPanel panel = ADAMQuestionMaker.buildQuestionChartPanel(questionIDX, vo, fullScreenQuestionsChart(position, color, vo.getTitle()));
//
//				System.out.println("addQuestionChart objID: " + objID + " | " + objectWindowId);
//
//				if ( objID == objectWindowId) {
//					questionsMap.put(position, vo);
//					if (RootPanel.get(position).getWidgetCount() > 0)
//						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//					RootPanel.get(position).setStyleName("question-" + color + "-box border content");
//					RootPanel.get(position).add(panel);
//				}
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
//
//	public static void addQuestionGroupTable(final int questionIDX, final String entityLabel, final String color, final ADAMQueryVO q, final long objID) {
//		final String position = "QUESTION_" + questionIDX;
//		RootPanel.get(position).setStyleName("question-" + color + "-box border content");
//		RootPanel.get(position).add(ADAMQuestionMaker.buildWaitingPanel(questionIDX));
//		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
//			public void onSuccess(ADAMResultVO vo) {
//
//				VerticalPanel cp = ADAMTableMaker.buildSmallQuestionTable(vo);
//				HorizontalPanel panel = ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, vo, ADAMTableController.fullScreenQuestionTable(position, color, vo.getTitle()), cp);
//
//
//
//				System.out.println("addQuestionTable objID: " + objID + " | " + objectWindowId + " | " + position);
//
//				if ( objID == objectWindowId) {
//					questionsMap.put(position, vo);
//
//
//					if (RootPanel.get(position).getWidgetCount() > 0)
//						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//					RootPanel.get(position).setStyleName("question-" + color + "-box border content");
//					RootPanel.get(position).add(panel);
//				}
//
//
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
//
//	public static void addChart(final String position, final String color, final ADAMQueryVO q, final long objID) {	
//		System.out.println("COLOR: " + color);
//		RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//		if (RootPanel.get(position).getWidgetCount() > 0)
//			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
//		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
//			public void onSuccess(ADAMResultVO vo) {
//				if ( q.getSwitchResource() != null) {
//					vo.setSwitchResource(q.getSwitchResource());
//				}
//				
//				VerticalPanel chartPanel = ADAMChartMaker.buildSmallChart(vo, position, color, fullScreenChart(position, color, q.getTitle()));
//				chartPanel.setHeight(ADAMConstants.SMALL_BOX_HEIGHT);
//				chartPanel.setWidth(ADAMConstants.SMALL_BOX_WIDTH);
//				
//				System.out.println("CHART objID: " + objID + " | objectWindowId: " + objectWindowId  + " position " + position);
//					chartMap.put(position, vo);
//					currentUsedObjects.put(position, ADAMBoxContent.CHART);
//				
//				
//				if ( objID == objectWindowId ) {
//					System.out.println("CHART objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position + " ADDED ");
////					chartMap.put(position, vo);
////					currentUsedObjects.put(position, ADAMBoxContent.CHART);
//					if (RootPanel.get(position).getWidgetCount() > 0)
//						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
////					if (RootPanel.get(position).getWidgetCount() > 0)
////						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//					RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//					RootPanel.get(position).add(chartPanel);
//				}
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
//
//	public static SelectionListener<ButtonEvent> fullScreenChart(final String position, final String color, final String title) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				removeCenter();
////				ADAMVisibilityController.restoreAdamViewVisibility();
//				ADAMVisibilityController.styleVisibilityDisplay("CENTER");
////				objectWindowId = objectWindow.getNext();
//				ADAMResultVO vo = chartMap.get(position);
//				RootPanel.get("CENTER").setStyleName("big-" + color + "-box border  content");
//				RootPanel.get("CENTER").add(ADAMChartMaker.buildBigChart(vo, "CENTER", color,  smallScreenChart(position, color, title)));
//			}
//		};
//	}
//	
//	public static SelectionListener<ButtonEvent> fullScreenCustomChart(final String position, final String color, final String title) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				removeCenter();
////				removeBoxes();
//				ADAMVisibilityController.restoreAdamViewVisibility();
////				objectWindowId = objectWindow.getNext();
//				ADAMResultVO vo = currentCustom.get(position);
//				RootPanel.get("CENTER").setStyleName("big-" + color + "-box border  content");
//				RootPanel.get("CENTER").add(ADAMChartMaker.buildBigChart(vo, "CENTER", color, smallScreenChart(position, color, title)));
//			}
//		};
//	}
//
//	public static SelectionListener<ButtonEvent> fullScreenQuestionsChart(final String position, final String color, final String title) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				removeQuestions();
//				ADAMVisibilityController.restoreCenterVisibility();
//				objectWindowId = objectWindow.getNext();
//				System.out.println("POSITION IS: " + position);
//				ADAMResultVO vo = questionsMap.get(position);
//				RootPanel.get("CENTER").setStyleName("big-" + color + "-box border  content");
//				RootPanel.get("CENTER").add(ADAMChartMaker.buildBigChart(vo, "CENTER", color, smallScreenQuestionsChart(position, color, title)));
//			}
//		};
//	}
//
//	public static SelectionListener<ButtonEvent> smallScreenChart(final String position, final String color, final String title) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
////				removeBoxes();
//
//				ADAMController_GWTSplit.removeCenter();
//
////				objectWindowId = objectWindow.getNext();
////				try {
////					ADAMVisibilityController.restoreAdamViewVisibility();
////					RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
////				} 
////				catch (Exception e) {
////				}
////				restoreBoxesContent(title);
//			}
//		};
//	}
//
//	public static SelectionListener<ButtonEvent> smallScreenQuestionsChart(final String position, final String color, final String title) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				removeBoxes();
//				ADAMVisibilityController.restoreAskADAMVisibility();
//				objectWindowId = objectWindow.getNext();
//				RootPanel.get(position).setStyleName("question-" + color + "-box border content");
//				restoreQuestionsContent();
//			}
//		};
//	}
//
//
//
//	public static void restoreBoxesContent(String title) {
//		RootPanel.get("CENTER").setStyleName("remove-box");
//		ADAMVisibilityController.styleVisibilityNoDisplay("CENTER");
//
//		System.out.println("MAP IS USED: " + containsMaps);
//
// 		for(String position : currentUsedObjects.keySet() ) {
// 			ADAMVisibilityController.styleVisibilityDisplay(position);
// 			
// 			Boolean putObject = true;
//			System.out.println("restoreBoxesContent: " + position);
//			if ( containsMaps ) {
//				if ( position.equals("BOTTOM_LEFT") || position.equals("BOTTOM_RIGHT"))
//					putObject = false;
//			}
//			else {
//				if ( position.equals("MAP_LEFT") || position.equals("MAP_RIGHT"))
//					putObject = false;
//			}
//			if ( putObject ) {
//				ADAMBoxContent type = currentUsedObjects.get(position);
//				switch(type) {
//					case TABLE:
//						ADAMResultVO tableVo = tableMap.get(position);
//						ADAMBoxContent c = ADAMBoxContent.valueOf(tableVo.getOutputType());
////						RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.TABLE) + "-box border content");
//						RootPanel.get(position).setStyleName("medium-" + tableVo.getBoxColor() + "-box border content");
//						RootPanel.get(position).add(ADAMTableMaker.buildSmallTable(tableVo, position, tableVo.getBoxColor(), ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title)));
//	//					switch (c) {
//	//						case TOP_DONORS_TABLE:
//	//							RootPanel.get(position).add(ADAMTableMaker.buildSmallTable(tableVo, ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title)));
//	//						break;
//	//						case TOP_SECTORS_DONOR_VIEW_TABLE:
//	//							RootPanel.get(position).add(ADAMTableMaker.buildSmallTopSectorsDonorViewTable(tableVo, ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title)));
//	//						break;
//	//						case TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE:
//	//							RootPanel.get(position).add(ADAMTableMaker.buildSmallTopSectorsDonorViewTable(tableVo, ADAMTableController.fullScreenTable(position, tableVo.getBoxColor(), title)));
//	//						break;
//	//					}
//					break;
//
//					case CHART:
//						ADAMResultVO chartVo = chartMap.get(position);
////						RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.CHART) + "-box border content");
//						RootPanel.get(position).setStyleName("medium-" + chartVo.getBoxColor() + "-box border content");
//						RootPanel.get(position).add(ADAMChartMaker.buildSmallChart(chartVo, position, chartVo.getBoxColor(), fullScreenChart(position, chartVo.getBoxColor(), title)));
//					break;
//
//					case MAP:
//						System.out.println("restoreBoxesContent mapMap: " + position);
//						ADAMResultVO mapVo = mapMap.get(position);
//						// TODO: vo needed?
//						if ( RootPanel.get(position).getWidgetCount() > 0) {
////							RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.MAP) + "-box border content");
//							RootPanel.get(position).setStyleName("medium-" + mapVo.getBoxColor() + "-box border content");
//							RootPanel.get(position).getWidget(0).setVisible(true);
//						}
//					break;
//
//					case VENN_PRIORITIES:
//						System.out.println("restoreBoxesContent vennMap: " + position);
//						ADAMResultVO vennVo = vennMap.get(position);
////						RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.VENN) + "-box border content");
//						RootPanel.get(position).setStyleName("medium-" + vennVo.getBoxColor() + "-box border content");
//						RootPanel.get(position).add(ADAMVennMaker.buildSmallPriorityVenn(vennVo.getTitle(), vennVo.getSmallImagePath(), ADAMConstants.SMALL_WIDTH, ADAMConstants.SMALL_HEIGHT, vennVo, position, ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES),ADAMVennController.fullScreenVenn(position, vennVo.getBoxColor(), vennVo.getTitle())));
//					break;
//					
//					
//					case CUSTOM_CHART:
//						System.out.println("restoreBoxesContent customChart: " + position);
//						ADAMResultVO customChartVo = currentCustom.get(position);
//						RootPanel.get(position).setStyleName("medium-" + customChartVo.getBoxColor() + "-box border content");
//						RootPanel.get(position).add(ADAMChartMaker.buildSmallCustomChart(customChartVo, fullScreenCustomChart(position, customChartVo.getBoxColor(), title), position));
//					break;
//				}
//			}
//
//		}
//	}
//	
//	public static void restoreCustomContent() {
//		
//		Boolean displayCustom = false;
//		
//		for(int i = 0; i < CUSTOMS; i ++ ) {
//			System.out.println("restoreCustomContent(): " + "CUSTOM_1" + i );
//
//			if ( currentCustom.containsKey("CUSTOM_1" + i )) {
//				displayCustom = true;
//				
//				String position = "CUSTOM_1" + i ;
//				ADAMResultVO customChartVo = currentCustom.get(position);
//	
//				RootPanel.get(position).setStyleName("medium-" + customChartVo.getBoxColor() + "-box border content");
//				RootPanel.get(position).add(ADAMChartMaker.buildSmallCustomChart(customChartVo, fullScreenCustomChart(position , customChartVo.getBoxColor(), ""), position));
//			}
//		}
//		
//		if ( displayCustom ) {
//			ADAMVisibilityController.restoreCustomVisibility(false);
//		}
//	}
//	
//	public static HashMap<String, ADAMResultVO> getCurrentViewBoxes() {
//		HashMap<String, ADAMResultVO> currentView = new HashMap<String, ADAMResultVO>();
//		RootPanel.get("CENTER").setStyleName("remove-box");
//
//		System.out.println("MAP IS USED: " + containsMaps);
//
// 		for(String position : currentUsedObjects.keySet() ) {
// 			Boolean putObject = true;
//			System.out.println("restoreBoxesContent: " + position);
//			if ( containsMaps ) {
//				if ( position.equals("BOTTOM_LEFT") || position.equals("BOTTOM_RIGHT"))
//					putObject = false;
//			}
//			else {
//				if ( position.equals("MAP_LEFT") || position.equals("MAP_RIGHT"))
//					putObject = false;
//			}
//			if ( putObject ) {
//				ADAMBoxContent type = currentUsedObjects.get(position);
//				switch(type) {
//					case TABLE:
//						currentView.put(position, tableMap.get(position));
//					break;
//
//					case CHART:
//						currentView.put(position, chartMap.get(position));
//					break;
//
//					case MAP:
//						currentView.put(position, mapMap.get(position));
//					break;
//
//					case VENN_PRIORITIES:
//						currentView.put(position, vennMap.get(position));
//					break;
//					case CUSTOM_CHART:
//						currentView.put(position, currentCustom.get(position));			
//					break;
//					
//				}
//			}
//			
//			
//		}
// 		return currentView;
//	}
//
//
//	public static ClickHandler openDataManagement(final ADAMToolbar t) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				new DonorProfileDataManagementWindow();		
//			}
//		};
//	}
//	
//	public static ClickHandler login2(final ADAMToolbar t) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				removeSecondaryMenu();
//				Info.display("onclick", "onclick");
//				RootPanel.get("SECONDARY_MENU").add(t.buildLoginMenu());
//			}
//		};
//	}
//	
//
//	public static Command login(final ADAMToolbar t) {
//		return new Command() {
//			public void execute() {
//				removeSecondaryMenu();
//				RootPanel.get("SECONDARY_MENU").add(t.buildLoginMenu());
//
//			}
//		};
//	};
//
//
//	public static Command referencePeriod(final ADAMToolbar t) {
//		return new Command() {
//			public void execute() {
//				removeSecondaryMenu();
//				RootPanel.get("SECONDARY_MENU").add(t.buildReferencePeriod());
//			}
//		};
//	};
//
//	public static Command askADAM(final ADAMToolbar t) {
//		return new Command() {
//			public void execute() {
//				removeSecondaryMenu();
//				RootPanel.get("SECONDARY_MENU").add(t.buildAskADAMFilters());
//			}
//		};
//	};
//
//
//
//	public static SelectionListener<ButtonEvent> askADAM(final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				removeAllContents(true);
//				ADAMVisibilityController.restoreAskADAMVisibility();
//
//				currentVIEW = ADAMCurrentVIEW.ASKADAM;
//
//				objectWindowId = objectWindow.getNext();
//
//				System.out.println("askADAM objectWindowId: " + objectWindowId);
//				System.out.println("currentVIEW: " + currentVIEW);
//
//
//				GaulModelData selectedEntity = entryList.getSelection().get(0);
//				String entity = selectedEntity.getGaulCode();
//
//				GaulModelData codesEntity = adamCodesList.getSelection().get(0);
//				String code = codesEntity.getGaulCode().substring(1 + codesEntity.getGaulCode().indexOf('_'));
//				String label = codesEntity.getGaulLabel();
//
//				Map<String, String> codes = new HashMap<String, String>();
//				codes.put(code, label);
//
//				System.out.println("askADAM: " + entity + " | " + code + " | " + label);
//
//				HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//
//				if ( entity.equalsIgnoreCase("Gaul_")) {
//					Boolean countryAdded = addCountryFilter(filters, codes);
//					System.out.println("askADAM countryAdded: " + countryAdded + " | " + filters);
//				}
//
//				else if ( entity.equalsIgnoreCase("Donor_")) {
//					Boolean donorAdded = addDonorFilter(filters, codes);
//					System.out.println("askADAM donorAdded: " + donorAdded + " | " + filters);
//				}
//
//				ADAMQuestionMaker.buildQuestions(entity, code, label, filters, baseDate, entity, objectWindowId);
//			}
//		};
//	}
//
//	public static SelectionListener<ButtonEvent> changeReferencePeriod(final ComboBox<GaulModelData> fromDateList, final ComboBox<GaulModelData> toDateList) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				changeReferencePeriodAgent(fromDateList, toDateList);
//			}
//		};
//	}
//
//	public static SelectionChangedListener<GaulModelData> changeReferencePeriodListener(final ComboBox<GaulModelData> fromDateList, final ComboBox<GaulModelData> toDateList) {
//		return new SelectionChangedListener<GaulModelData>() {
//			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
//				changeReferencePeriodAgent(fromDateList, toDateList);
//			}
//		};
//	}
//	
//	public static void changeReferencePeriodAgent(final ComboBox<GaulModelData> fromDateList, final ComboBox<GaulModelData> toDateList) {
//
//				System.out.println("fromDateList.getValue().getGaulCode():" + fromDateList.getValue().getGaulCode());
//				System.out.println("toDateList.getValue().getGaulCode():" + toDateList.getValue().getGaulCode());
//
//
//
//	 			Date fromDate = FieldParser.parseDate(fromDateList.getValue().getGaulCode());
//	 			Date toDate =  FieldParser.parseDate(toDateList.getValue().getGaulCode());
//
//	 			System.out.println("ok");
//
//	 			Boolean updateView = true;
//
//	 			// error message
//	 			if ( fromDate.compareTo(toDate) > 0 ) {
//	 				updateView = false;
//	 				FenixAlert.error("Select date", ("Date selection is wrong"));
////	 				Window.alert("Date selection is wrong");
//
//	 			}
//	 			else if ( fromDate.compareTo(toDate) == 0 ) {
//	 				baseDate.clear();
//					baseDate.add(toDateList.getValue().getGaulCode());
//
//	 			}
//	 			else if ( fromDate.compareTo(toDate) < 0 ) {
//	 				baseDate.clear();
//
//	 				while( fromDate.compareTo(toDate) <= 0 ) {
//	 					System.out.println("dates: " + fromDate + " | " + toDate);
//
//	 					baseDate.add(FieldParser.formatDate(fromDate, "formatterMinusReverse"));
//
//	 					fromDate = new Date(fromDate.getYear() + 1, fromDate.getMonth(), fromDate.getDate());
//	 				}
//
//	 			}
//
//	 			System.out.println("basedate: " + baseDate);
//
//	 			if ( updateView )
//	 				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, currentVIEW);
////	 				callView(ADAMBoxMaker.getDonorList(), ADAMBoxMaker.getGaulList(), ADAMBoxMaker.getSectorList());
//
//			}
//
//
//	public static Command upload(final ADAMToolbar t) {
//		return new Command() {
//			public void execute() {
//				removeSecondaryMenu();
//				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
//					new ExcelImporterWindow().build();
//				} else {
//					FenixAlert.alert(BabelFish.print().error(), "You need to sign in before uploading a new dataset");
//				}
//			}
//		};
//	};
//
//	
//	public static ClickHandler upload2(final ADAMToolbar t) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				removeSecondaryMenu();
//				Info.display("onclick", "onclick");
//				RootPanel.get("SECONDARY_MENU").add(t.buildLoginMenu());removeSecondaryMenu();
//				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
//					new ExcelImporterWindow().build();
//				} else {
//					FenixAlert.alert(BabelFish.print().error(), "You need to sign in before uploading a new dataset");
//				}
//			}
//		};
//	}
//	public static Command uploadCS(final ADAMToolbar t) {
//		return new Command() {
//			public void execute() {
//				removeSecondaryMenu();
//				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
//					new CodeListImporterWindow().build();
//				} else {
//					FenixAlert.alert(BabelFish.print().error(), "You need to sign in before uploading a new coding system");
//				}
//			}
//		};
//	};
//
//	@SuppressWarnings("unchecked")
//	public static SelectionListener<IconButtonEvent> loginListener(final ADAMToolbar t, final HorizontalPanel secondaryMenuBar) {
//		return new SelectionListener<IconButtonEvent>() {
//			public void componentSelected(IconButtonEvent ce) {
//				TextField<String> username = (TextField<String>)secondaryMenuBar.getData("USERNAME");
//				TextField<String> password = (TextField<String>)secondaryMenuBar.getData("PASSWORD");
//				UserServiceEntry.getInstance().login(username.getValue(), password.getValue(), new AsyncCallback<LoginResponseVo>() {
//					public void onSuccess(LoginResponseVo vo) {
//						removeLoginMessage();
//						if (vo.isSucceeded()) {
//							String firstAndLastName = vo.getFenixUserVo().getFirstName() + " " + vo.getFenixUserVo().getLastName();
//							String welcomeMessage = "  " + BabelFish.print().welcome() + " " + firstAndLastName;
//							FenixUser.populateRoles(vo.getFenixUserVo());
//							RootPanel.get("loginMessage").add(new HTML(welcomeMessage));
//							RootPanel.get("loginMessage").setStyleName("link");
//						} else {
//							String message = vo.getResponseMessage();
//							if (vo.getResponseMessage().equals("UsernameNotFoundException"))
//								message = BabelFish.print().UsernameNotFoundException();
//							if (vo.getResponseMessage().equals("BadCredentialsException"))
//								message = BabelFish.print().BadCredentialsException();
//							if (vo.getResponseMessage().equals("AuthenticationException"))
//								message = BabelFish.print().AuthenticationException();
//							if (vo.getResponseMessage().equals("DisabledException"))
//								message = "Your user account is still disabled because you do not yet belong to any group. Ask your administrator to add you to a group.";
//							RootPanel.get("loginMessage").add(new HTML(message));
//							RootPanel.get("loginMessage").setStyleName("link");
//						}
//						removeSecondaryMenu();
//
//						/*******************************
//						 *
//						 *
//						 *
//						 *  TODO REMOVE IN THE FINAL VERSION!!!!
//						 *
//						 *
//						 *
//						 *  */
//
//						t.addLoginItems();
//
//						/***
//						 *  **/
//					}
//					public void onFailure(Throwable e) {
//						FenixAlert.error(BabelFish.print().error(), e.getMessage());
//					}
//				});
//			}
//		};
//	}
//	
//	public static void fillSelectorStoreADAMBox(final String prefix, final ListStore<GaulModelData> store) {
//		ADAMServiceEntry.getInstance().findAll(prefix, new AsyncCallback<List<CodeVo>>() {
//			public void onSuccess(List<CodeVo> vos) {
//				for (CodeVo vo : vos) {
//					GaulModelData g = new GaulModelData();
//					g.setGaulCode(vo.getCode());
//					g.setGaulLabel(vo.getLabel());
//					store.add(g);
//				}
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
//
//	public static void fillSelectorStore(final String prefix, final ListStore<GaulModelData> store, final ComboBox<GaulModelData> adamCodesList) {
//		ADAMServiceEntry.getInstance().findAll(prefix, new AsyncCallback<List<CodeVo>>() {
//			public void onSuccess(List<CodeVo> vos) {
//				for (CodeVo vo : vos) {
//					GaulModelData g = new GaulModelData();
//					g.setGaulCode(vo.getCode());
//					g.setGaulLabel(vo.getLabel());
//					store.add(g);
//				}
//				adamCodesList.setValue(store.getAt(0));
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
//	
//	
//
//	public static SelectionChangedListener<GaulModelData> entityListener(final ComboBox<GaulModelData> entityList, final ListStore<GaulModelData> store, final ComboBox<GaulModelData> adamCodesList) {
//		return new SelectionChangedListener<GaulModelData>() {
//			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
//				String prefix = entityList.getSelection().get(0).getGaulCode();
//				if (store.getCount() > 0)
//					store.removeAll();
//				adamCodesList.clearSelections();
//				fillSelectorStore(prefix, store, adamCodesList);
//				
//			}
//		};
//	}
//
//	public static SelectionChangedListener<GaulModelData> updateEntity(final ADAMComboMultiSelection adamComboMultiSelection, final ComboBox<GaulModelData> list, final Map<String, String> map, final String type) {
//		return new SelectionChangedListener<GaulModelData>() {
//			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
//				map.clear();
//				
//				String code = list.getSelection().get(0).getGaulCode();
//				String label = list.getSelection().get(0).getGaulLabel();
//				
//				if ( !code.equalsIgnoreCase("MULTI") ) {
//					map.put(code, label);
//					callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, currentVIEW);
//				}
//				else if (code.equalsIgnoreCase("MULTI")) {
//					if ( type.equalsIgnoreCase("COUNTRY")) {
//						addGaulMultiselectionAgent(adamComboMultiSelection);
//					}
//					else if ( type.equalsIgnoreCase("DONOR")) {
//						addDonorMultiselectionAgent(adamComboMultiSelection);
//					}
//					else if ( type.equalsIgnoreCase("SECTOR")) {
////						addSectorMultiselectionAgent(adamComboMultiSelection);
//					}
//				}
//				
//		
//					
//			}
//		};
//	}
//
//
//
////	public static SelectionChangedListener<GaulModelData> updateEntityByCountry(final ComboBox<GaulModelData> donorList, final ComboBox<GaulModelData> gaulList, final ComboBox<GaulModelData> sectorList) {
////		return new SelectionChangedListener<GaulModelData>() {
////			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
////
////
////				callView(donorList, gaulList, sectorList);
////			}
////		};
////	}
//
//
//	public static SelectionListener<ButtonEvent> updateEnties() {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, currentVIEW);
//			}
//		};
//	}
//
//	public static SelectionChangedListener<GaulModelData> updateAggregationColumnADAMView(final ComboBox<GaulModelData> aggregationColumnList) {
//			return new SelectionChangedListener<GaulModelData>() {
//				public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
//		
//					crs_aggregationColumn = aggregationColumnList.getSelection().get(0);
//
//					callViewAgent(ADAMCurrentVIEW.ADAMVIEW);
//				}
//			};
//		}
//
//
////	public static void callView(final ComboBox<GaulModelData> donorList, final ComboBox<GaulModelData> gaulList, final ComboBox<GaulModelData> sectorList) {
//	public static void callView(final Map<String, String> donorList, final Map<String, String> gaulList, final Map<String, String> sectorList, final ADAMCurrentVIEW view) {
//		 currentVIEW = view;
//		 
//		  GWT.runAsync(new RunAsyncCallback() {
//			  public void onSuccess() {
//				  
//				 ADAMBoxController.resizeObjects();
//				
//				 //removeAllContents(false);
//				 if(currentVIEW.equals(ADAMCurrentVIEW.HOME)) {
//					 createHomeView();
//				 } else {
//				 
//		             removeAllContents(false);
//					 final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//		
//					 Boolean countryAdded = addCountryFilter(filters, gaulList);
//					 System.out.println("countryAdded: " + countryAdded);
//					 Boolean donorAdded = addDonorFilter(filters, donorList);
//					 System.out.println("donorAdded: " + donorAdded);
//					 Boolean sectorAdded =  addSectorFilter(filters, sectorList);
//					 System.out.println("sectorAdded: " + sectorAdded);
//		
//					 System.out.println("filters: " + filters);
//		
//					 if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){
//		
//		
//						 ADAMVisibilityController.restoreAdamViewVisibility();
//		
//						 if( donorAdded && !countryAdded) {
//							 GWT.runAsync(new RunAsyncCallback() {
//								  public void onSuccess() {
//									  createDonorView(donorList, filters);
//								  }
//								  public void onFailure(Throwable caught) {
//							      }
//							 });
//						 }
//		
//						 else if( countryAdded && !donorAdded){
//							 GWT.runAsync(new RunAsyncCallback() {
//								  public void onSuccess() {
//									  	createCountryView(gaulList, filters);
//								  }
//								  public void onFailure(Throwable caught) {
//							      }
//							 });
//						 }
//		
//						 else if( countryAdded && donorAdded) {
//							 GWT.runAsync(new RunAsyncCallback() {
//								  public void onSuccess() {
//									  createCountryDonorView(donorList, gaulList, filters);
//								  }
//								  public void onFailure(Throwable caught) {
//							      }
//							 });
//						 }
//		
//						 else if( !countryAdded && !donorAdded) {
//							 /*** global view **/
//							 GWT.runAsync(new RunAsyncCallback() {
//								  public void onSuccess() {
//									  globalAgentADAM(ADAM.getAdamWrapper());
//								  }
//								  public void onFailure(Throwable caught) {
//							      }
//							 });
//						 }
//								  
//		
//			 // THIS IS WITH THE SECTOR FILTER			
//						 //			if( donorAdded && !countryAdded && !sectorAdded)
//						 //				createDonorView(donorList, filters);
//						 //	
//						 //			else if( countryAdded && !donorAdded && !sectorAdded){
//						 //				createCountryView(gaulList, filters);
//						 //			}
//						 //	
//						 //			else if( countryAdded && donorAdded && !sectorAdded) {
//						 //				createCountryDonorView(donorList, gaulList, filters);
//						 //			}
//						 //	
//						 //			else if( !countryAdded && !donorAdded && !sectorAdded) {
//						 //				/*** global view **/
//						 //				globalAgent(ADAM.getAdamWrapper());
//						 //			}
//		
//						 // restore custom
//						 restoreCustomContent();
//					 } 
//					 else if(currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX)){
//						 createDonorMatrixView(donorList, gaulList, filters);
//					 } else if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)){
//						 createDonorProfileView(donorList, filters);
//					 }
//				 }
//
//	          }
//
//		  public void onFailure(Throwable caught) {
//	          }
//	        });
//	   
//	}
//
//	private static void setGlobalMapInvisible() {
//		ADAMMapWrapper adamMapWrapper = ADAMWrapper.getAdamMapsWrapper().get("global");
//		adamMapWrapper.getPanel().setVisible(false);
//	}
//
//
//	private static void createDonorMatrixView(final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters){
//		ADAMController_GWTSplit.containsMaps = false;
//		objectWindowId = objectWindow.getNext();
//
//		addViewMenu("Resource Partner Matrix:", gaulList, donorList, null);
//		
//		ADAMDonorMatrixVO vo = DonorMatrixController.createDonorMatrixVO(donorList, gaulList, filters, baseDate);		
//		DonorMatrixController.addDonorMatrix(vo, baseDate);
//	}
//	
//	private static void createHomeView(){
//		ADAMController_GWTSplit.containsMaps = false;
//		objectWindowId = objectWindow.getNext();
//
//		ADAMVisibilityController.removeViewMenuContent();
//		ADAMVisibilityController.restoreViewMenuVisibility();
//		
//		showHomePage();
//	}
//	
//	private static void createDonorProfileView(final Map<String, String> donorList, HashMap<String, List<String>> filters){
//		ADAMController_GWTSplit.containsMaps = false;
//		objectWindowId = objectWindow.getNext();
//
//		ADAMVisibilityController.removeViewMenuContent();
//		ADAMVisibilityController.restoreViewMenuVisibility();
//		
//		//addViewMenu("Resource Partner Profile:", null, donorList, null);
//		
//		DonorProfileController.addDonorProfile(donorList);
//
//	}
//	
//	private static void createDonorView(final Map<String, String> map, HashMap<String, List<String>> filters ){
//		ADAMController_GWTSplit.containsMaps = true;
//		objectWindowId = objectWindow.getNext();
//
//		System.out.println("createDonorView objectWindowId: " + objectWindowId );
//
//		String label = new String();
//		List<String> codes = new ArrayList<String>();
//
//		for(String code : map.keySet()) {
//			codes.add(getCode(code));
//			label = label + " " + map.get(code);
//		}
//
//		addViewMenu("Donor View:", null, map, null);
//
//
//		// TOP_LEFT
//		ADAMQueryVO topSectorChart = ADAMQueryVOBuilder.topSectorsChart("Top Ten Sectors", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel());	
//		ADAMQueryVO topSectorTable = ADAMQueryVOBuilder.topSectorsDonorView(label, baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 5);	
//		topSectorChart.setSwitchResource(topSectorTable);
//		topSectorTable.setSwitchResource(topSectorChart);
//		ADAMController_GWTSplit.addChart("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorChart, objectWindowId);
////		ADAMTableController.addTable("TOP_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.topSectorsDonorView(label, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR)), objectWindowId);
//
//
//		// TOP_RIGHT
//		ADAMController_GWTSplit.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), ADAMQueryVOBuilder.donorViewFavouriteChannels(codes, label, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//
//		
//		// MAP_RIGHT
//		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedAgriculturalSectors(filters, baseDate, "", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel());	
//		ADAMQueryVO topAgriculturalSectorTable = ADAMQueryVOBuilder.topAgriculturalSectorsDonorView(label, baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10);
//		topAgriculturalSectorChart.setSwitchResource(topAgriculturalSectorTable);
//		topAgriculturalSectorTable.setSwitchResource(topAgriculturalSectorChart);
//		ADAMController_GWTSplit.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);
////		ADAMTableController.addTable("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.topAgriculturalSectorsDonorView(label, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10), objectWindowId);
//
//		// MAP_LEFT
//		ADAMController_GWTSplit.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter(label, baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//
//	}
//
//	private static void createCountryView(final Map<String, String> map, HashMap<String, List<String>> filters){
//		ADAMController_GWTSplit.containsMaps = false;
//
//		objectWindowId = objectWindow.getNext();
//
//		System.out.println("createCountryView objectWindowId: " + objectWindowId );
//
//		String label = new String();
//		List<String> codes = new ArrayList<String>();
//
//		for(String code : map.keySet()) {
//			String c = getCode(code);
//			codes.add(c);
//			label = label + " " + map.get(code);
//		}
//
//
////		String gaulCode = gaulList.getSelection().get(0).getGaulCode();
////		String gaulLabel = gaulList.getSelection().get(0).getGaulLabel();
////		updateKeyMessagesAgent(gaulCode, gaulLabel);
////		gaulCode = getCode(gaulCode);
//
//		addViewMenu("Country View:", map, null, null);
//
//		// TOP_LEFT
//		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.mostFinancedSectors(baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//
//		// TOP_RIGHT
//		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart("Top Ten donors", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false);	
//		ADAMQueryVO topDonorsTable = ADAMQueryVOBuilder.topDonorsFiltered(baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), 10);
//		topDonorsChart.setSwitchResource(topDonorsTable);
//		topDonorsTable.setSwitchResource(topDonorsChart);
//		ADAMController_GWTSplit.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorsChart, objectWindowId);
////		ADAMTableController.addTable("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), ADAMQueryVOBuilder.topDonorsFiltered(baseDate, filters,  ADAMColorConstants.color.get(ADAMBoxContent.DONOR)), objectWindowId);
///**
//		
//		// BOTTOM_LEFT	
////        ADAMTableController.addTable("BOTTOM_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.topAgriculturalCountryView("", baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10), objectWindowId);
//**/			
//
//		// BOTTOM_RIGHT
//		ADAMTableController.addChart("BOTTOM_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.mostFinancedAgriculturalSectors(filters, baseDate, label, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//
//
//		Map<String, String> cleanMap = getCleanMap(map);
//
//
//		HashMap<String, String> setA = new HashMap<String, String>();
//		HashMap<String, String> setB = new HashMap<String, String>();
//		HashMap<String, String> setC = new HashMap<String, String>();
//		setA.putAll(cleanMap);
//		setB.put("41301", "FAO");
////		setC.put("filter_all", "Other Donors");
//		
//		String setAtitle = "Countries";
//		
//		if ( cleanMap.size() == 1) {
//			for(String key : cleanMap.keySet())
//				setAtitle = cleanMap.get(key);
//		} 
//			
//		ADAMVennController.addPrioritiesVenn(setAtitle, "FAO", "Donors", "BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);
//
//	}
//
//
//	private static void createCountryDonorView(final Map<String, String> donorList, final Map<String, String> gaulList, HashMap<String, List<String>> filters ){
//		ADAMController_GWTSplit.containsMaps = false;
//
//		objectWindowId = objectWindow.getNext();
//
//		System.out.println("createCountryDonorView objectWindowId: " + objectWindowId );
//
//		String donorLabel = new String();
//		String gaulLabel = new String();
//		List<String> codes = new ArrayList<String>();
//
//		for(String code : donorList.keySet()) {
//			String c = getCode(code);
//			codes.add(c);
//			donorLabel = donorLabel + " " + donorList.get(code);
//		}
//
//		for(String code : gaulList.keySet()) {
//			String c = getCode(code);
//			codes.add(c);
//			gaulLabel = gaulLabel + " " + gaulList.get(code);
//		}
//
////		String donorCode = donorList.getSelection().get(0).getGaulCode();
////		String donorLabel = donorList.getSelection().get(0).getGaulLabel();
////		donorCode = getCode(donorCode);
////
////		String gaulCode = gaulList.getSelection().get(0).getGaulCode();
////		String gaulLabel = gaulList.getSelection().get(0).getGaulLabel();
////		gaulCode = getCode(gaulCode);
//
//		/*** TODO: UPDATE KEY ***/
//
//		addViewMenu("Country/Donor View:", gaulList, donorList, null);
//
//		// TOP_LEFT
//		ADAMQueryVO topSectorsChart = ADAMQueryVOBuilder.mostFinancedSectors(baseDate, filters, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel());	
//		ADAMQueryVO topSectorsTable = ADAMQueryVOBuilder.topSectorsChannelsDonorCountry(baseDate, filters, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10);
//		topSectorsChart.setSwitchResource(topSectorsTable);
//		topSectorsTable.setSwitchResource(topSectorsChart);
//		ADAMTableController.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topSectorsChart, objectWindowId);
////		ADAMTableController.addTable("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.topSectorsChannelsDonorCountry(baseDate, filters,  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR)), objectWindowId);
//
//		// TOP_RIGHT
//		ADAMController_GWTSplit.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), ADAMQueryVOBuilder.topChannelsChart("Favorite Channels", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.CHANNEL), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false), objectWindowId); 
//
//
//
//
//		// BOTTOM_RIGHT
//		ADAMQueryVO topAgriculturalSectorChart = ADAMQueryVOBuilder.mostFinancedAgriculturalSectors(filters, baseDate, donorLabel + "/"+ gaulLabel ,  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(),crs_aggregationColumn.getGaulLabel());	
//		ADAMQueryVO topAgriculturalSectorTable = ADAMQueryVOBuilder.topAgriculturalSectorsDonorCountryView(donorLabel, gaulLabel, baseDate, filters,  crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10);
//		topAgriculturalSectorChart.setSwitchResource(topAgriculturalSectorTable);
//		topAgriculturalSectorTable.setSwitchResource(topAgriculturalSectorChart);
//		ADAMTableController.addChart("BOTTOM_RIGHT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), topAgriculturalSectorChart, objectWindowId);
////		ADAMTableController.addTable("BOTTOM_RIGHT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.topAgriculturalSectorsDonorCountryView(donorLabel, gaulLabel, baseDate, filters,  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), 10), objectWindowId);
//
//
//		
//
//		
//		
//		Map<String, String> countries = getCleanMap(gaulList);
//		
//		Map<String, String> donors = getCleanMap(donorList);
//
//
//		HashMap<String, String> setA = new HashMap<String, String>();
//		HashMap<String, String> setB = new HashMap<String, String>();
//		HashMap<String, String> setC = new HashMap<String, String>();
//		setA.putAll(countries);
//		setB.put("41301", "FAO");
//		setC.putAll(donors);
//		
//		
//
//		String setAtitle = "Countries";
//		if ( countries.size() == 1) {
//			for(String key : countries.keySet())
//				setAtitle = countries.get(key);
//		} 
//		
//		String setBtitle = "Donors";
//		if ( donors.size() == 1) {
//			for(String key : donors.keySet())
//				setBtitle = donors.get(key);
//		} 
//		
//		ADAMVennController.addPrioritiesVenn(setAtitle, "FAO", setBtitle, "BOTTOM_LEFT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), setA, setB, setC, "EN", objectWindowId);
//
//	}
//
//
//
//
//	public static Boolean addCountryFilter(final HashMap<String, List<String>> filters, final Map<String, String> gaulList) {
//
//		Boolean added = false;
//		List<String> filter = new ArrayList<String>();
//
//		for(String key : gaulList.keySet()) {
//			String code = key;
//			String label = gaulList.get(key);
//			System.out.println("gaulCode " + code);
//			System.out.println("gaulLabel " + label);
//			if ( !key.equalsIgnoreCase("GLOBAL")) {
//				added = true;
//				code = getCode(code);
//				filter.add(code);
//			}
//		}
//		if ( !filter.isEmpty())
//			ADAMQueryVOBuilder.addRecipientFilters(filters, filter);
//
//		return added;
//
////		try {
////			String code = gaulList.getSelection().get(0).getGaulCode();
////			String label = gaulList.getSelection().get(0).getGaulLabel();
////			System.out.println("gaulCode " + code);
////			System.out.println("gaulLabel " + label);
////			if ( !code.equalsIgnoreCase("GLOBAL")) {
////				List<String> filter = new ArrayList<String>();
////				code = getCode(code);
////				filter.add(code);
////				ADAMQueryVOBuilder.addRecipientFilters(filters, filter);
////				return true;
////			}
////			return false;
////		} catch (Exception e) {
////			System.out.println("donor null listbox expection ");
////			return false;
////		}
//	}
//
//
//	public static Boolean addDonorFilter(final HashMap<String, List<String>> filters, final Map<String, String> donorList) {
//
//		Boolean added = false;
//		List<String> filter = new ArrayList<String>();
//
//		for(String key : donorList.keySet()) {
//			String code = key;
//			String label = donorList.get(key);
//			System.out.println("gaulCode " + code);
//			System.out.println("gaulLabel " + label);
//			if ( !key.equalsIgnoreCase("GLOBAL")) {
//				added = true;
//				code = getCode(code);
//				filter.add(code);
//			}
//		}
//		if ( !filter.isEmpty())
//			ADAMQueryVOBuilder.addDonorFilters(filters, filter);
//
//		return added;
//
////		try {
////			String code = donorList.getSelection().get(0).getGaulCode();
////			String label = donorList.getSelection().get(0).getGaulLabel();
////			System.out.println("donorCode " + code);
////			System.out.println("donorLabel " + label);
////			if ( !code.equalsIgnoreCase("GLOBAL")) {
////				List<String> filter = new ArrayList<String>();
////				code = getCode(code);
////				filter.add(code);
////
////				return true;
////			}
////			return false;
////		} catch (Exception e) {
////			System.out.println("donor null listbox expection ");
////			return false;
////		}
//	}
//
//	public static Boolean addSectorFilter(final HashMap<String, List<String>> filters, final Map<String, String> sectorList) {
//
//		Boolean added = false;
//		List<String> filter = new ArrayList<String>();
//
//		for(String key : sectorList.keySet()) {
//			String code = key;
//			String label = sectorList.get(key);
//			System.out.println("gaulCode " + code);
//			System.out.println("gaulLabel " + label);
//			if ( !key.equalsIgnoreCase("GLOBAL")) {
//				added = true;
//				code = getCode(code);
//				filter.add(code);
//			}
//		}
//		if ( !filter.isEmpty())
//			ADAMQueryVOBuilder.addSectorCodeFilters(filters, filter);
//
//		return added;
//
//	}
//
//
//	public static Boolean addPurposeCodesFilter(final HashMap<String, List<String>> filters, final Map<String, String> purposeList) {
//
//		Boolean added = false;
//		List<String> filter = new ArrayList<String>();
//
//		for(String key : purposeList.keySet()) {
//			String code = key;
//			String label = purposeList.get(key);
//			System.out.println("purposeCode " + code);
//			System.out.println("purposeLabel " + label);
//			if ( !key.equalsIgnoreCase("GLOBAL")) {
//				added = true;
//				code = getCode(code);
//				filter.add(code);
//			}
//		}
//		if ( !filter.isEmpty())
//			ADAMQueryVOBuilder.addPurposeCodeFilters(filters, filter);
//
//		return added;
//
//	}
//
//	
//	private static Map<String, String> getCleanMap(Map<String, String> map2) {
//		Map<String, String> map = new HashMap<String, String>();
//		for(String code : map2.keySet()) {
//			map.put(getCode(code), map2.get(code));
//		}
//		return map;
//	}
//
//
//	protected static String getCode(String code) {
//		String c = code.substring(1 + code.indexOf('_'));
//		while(c.contains("_")) {
//			 c = c.substring(code.indexOf('_'));
//		}
//		return c;
//	}
//
//
////	public static void updateSelectionMessagesAgent() {
////		removeKeyMessages();
////
////		ADAMVisibilityController.restoreKeyMessagesVisiblity();
////		
////		String html = "<table width='650px' align='center'><tr><td class='small-content' colspan='3' align='center'><b><u> Current View </u></b></td></tr>";
////				html += "<tr>";
////				html += "<td width='225px' align='left'><b> Countries </b></td>";
////				html += "<td width='225px' align='left'><b> Resource Partners </b></td>";
////				html += "<td width='225px' align='left'><b> Sectors </b></td>";
////				html += "</tr>";
////				html += "<tr>";
////				html += "<td align='left'> " + addSelection(ADAMBoxMaker.countriesSelected, "Selected Geographic Area") + " </b></td>";
////				html += "<td align='left'> " + addSelection(ADAMBoxMaker.donorsSelected, "Selected Donors") + " </b></td>";
////				html += "<td align='left'> " + addSelection(ADAMBoxMaker.sectorsSelected, "Selected Sectors")+ " </b></td>";
////				html += "</tr>";
////				HTML table = new HTML(html);			
////				RootPanel.get("KEY_MESSAGES").add(table);
////				RootPanel.get("KEY_MESSAGES").setStyleName("keymessages border small-content");
////				
////				
////	}
////
////	public static void updateSelectionMessagesAgentDonorMatrix() {
////		removeKeyMessages();
////		
////		ADAMVisibilityController.restoreKeyMessagesVisiblity();
////		
////        String html = "<table width='650px' align='center'><tr><td class='small-content' colspan='3' align='center'><b><u> Resource Partner Matrix</u></b></td></tr>";
////		html += "<tr>";
////		html += "<td width='225px' align='left'><b>Recipient Countries</b></td>";
////		html += "<td width='225px' align='left'><b>Resource Partners</b></td>";
////		html += "</tr>";
////		html += "<tr>";
////		html += "<td align='left'> " + addSelection(ADAMBoxMaker.countriesSelected, "Selected Geographic Area").toString() + " </b></td>";
////		html += "<td align='left'> " + addSelection(ADAMBoxMaker.donorsSelected, "Selected Partners").toString() + " </b></td>";
////		html += "</tr>";
////
////		HTML table = new HTML(html);
////		RootPanel.get("KEY_MESSAGES").add(table);
////		RootPanel.get("KEY_MESSAGES").setStyleName("keymessages border small-content");
////
////	}
//
//	private static HTML addSelection(Map<String, String> map, String title) {
//		String label = new String();
//
//		LinkedHashMap<String, String>  sortedMap = sortByValues(map);
//		String fullLabel = new String();
//
//		if ( !sortedMap.isEmpty()){
//			for(String key : sortedMap.keySet()) {
//				label += sortedMap.get(key) + " ";
////				fullLabel += sortedMap.get(key) + " ";
//				if ( label.length() > 25 ) {
//					label = label.substring(0, 24) + "...";
//					break;
//				}
//			}
//
//			HTML link = new HTML();
////			link.setHTML("" + label + " - show all");
//			link.setHTML("<div class='link'> " + label + " - show all</div>");
////			link.setToolTip(fullLabel);
//			link.addMouseOverHandler(mouseOver(sortedMap, title));
////			link.addClickHandler(showAllLink(sortedMap, title));
//			return link;
//		}
////		return new HTML("Global View");
//		return new HTML("<div style='font-size:9px'>Global View</div>");
//	}
//	
//	private static MouseOverHandler mouseOver(final LinkedHashMap<String, String>  sortedMap, final String title) {
//		return new MouseOverHandler() {
//			public void onMouseOver(MouseOverEvent arg0) {
//				System.out.println("ON CLICK");
//				Window window = new Window();
//				HTML htmlTitle = new HTML("<div class='link-title'>" + title + "</div>");
//				window.add(htmlTitle);
//				window.setHeading(title);
//				window.setHeight(450);
//				window.setWidth(450);
////				window.setScrollMode(Scroll.AUTO);
//
//				VerticalPanel panel = new VerticalPanel();
//				panel.setHeight(450);
//				panel.setWidth(450);
//				panel.setScrollMode(Scroll.AUTO);
//				for(String code : sortedMap.keySet()) {
//					HTML html = new HTML();
//					String label = sortedMap.get(code);
//					html.setHTML("<div style='font-size:10px'>" + label + "</div>");
//
//					panel.add(html);
//				}
//
//				window.add(panel);
//				window.show();
//				
//			}
//		};
//	}
//
//	private static ClickHandler showAllLink(final LinkedHashMap<String, String>  sortedMap, final String title) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				Window window = new Window();
//				HTML htmlTitle = new HTML("<div class='link-title'>" + title + "</div>");
//				window.add(htmlTitle);
//				window.setHeading(title);
//				window.setHeight(450);
//				window.setWidth(450);
//				window.setScrollMode(Scroll.AUTO);
//
//				VerticalPanel panel = new VerticalPanel();
//				panel.setHeight(450);
//				panel.setWidth(450);
//				panel.setScrollMode(Scroll.AUTO);
//				
//				for(String code : sortedMap.keySet()) {
//					HTML html = new HTML();
//					String label = sortedMap.get(code);
//					html.setHTML("<div style='font-size:10px'>" + label + "</div>");
//
//
//					panel.add(html);
//				}
//
//				window.add(panel);
//				window.show();
//			}
//		};
//	}
//
//
//
//
//	public static ClickHandler updateCountry(final String code, final String label, final Window window) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent arg0) {
//				if ( window != null )
//					window.close();
//
//				ComboBox<GaulModelData> gaulList = ADAMBoxMaker.getGaulList();
//				ListStore<GaulModelData> gaulStore = ADAMBoxMaker.getGaulStore();
//
//
//				updateList(code, gaulList, gaulStore);
//
//			}
//		};
//	}
//
//
//	public static ClickHandler updateDonor(final String code, final String label, final Window window) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent arg0) {
//				if ( window != null )
//					window.close();
//
//				ComboBox<GaulModelData> donorList = ADAMBoxMaker.getDonorList();
//				ListStore<GaulModelData> donorStore = ADAMBoxMaker.getDonorStore();
//
//				updateList(code, donorList, donorStore);
//			}
//		};
//	}
//
//	private static void updateList(String code, ComboBox<GaulModelData> list, ListStore<GaulModelData>  store) {
//		System.out.println("store count: " + list.getView());
//		store.clearFilters();
//		List<GaulModelData> models = store.getModels();
//
//
//		System.out.println("store count: " + list.getView());
//
//		for(int i=0; i < models.size(); i++ ) {
//			String listCode = getCode(models.get(i).getGaulCode());
//			System.out.println("checking code: " + models.get(i).getGaulCode() + " | " + code);
//			if( listCode.equals(code)) {
//				System.out.println("setting");
//				// set in the list box
//				list.setValue(store.getAt(i));
//				break;
//			}
//		}
//
//	}
//
//	public static ClickHandler globalLOGIN(final ADAMWrapper adamWrapper) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent arg0) {
//
//				if ( FenixUser.hasAdminRole()) {
//					globalAgentHome(adamWrapper);
//				}
//				else
//					FenixAlert.info("login", "please login");
//			}
//		};
//	}
//
//	public static ClickHandler global(final ADAMWrapper adamWrapper) {
//		return new ClickHandler() {
//			public void onClick(ClickEvent arg0) {
//				/** TODO: restore drop down **/
//				//restoreDropDowns And filters ;
//				restoreDropDownsAndFilters();
//				cleanCustomContent();
//				globalAgentHome(adamWrapper);
//			}
//		};
//	}
//	
//	private static void cleanCustomContent() {
//		currentCustom.clear();
//		currentUsedObjects.clear();
//	}
//	
//	private static void restoreDropDownsAndFilters() {
//		// country list
//		ADAMBoxMaker.getGaulList().disableEvents(true);
//		ADAMBoxMaker.getGaulList().setValue(ADAMBoxMaker.getGaulStore().getAt(0));
//		ADAMBoxMaker.countriesSelected.clear();		
//		ADAMBoxMaker.getGaulList().disableEvents(false);
//		
//		// donor list
//		ADAMBoxMaker.getDonorList().disableEvents(true);
//		ADAMBoxMaker.getDonorList().setValue(ADAMBoxMaker.getDonorStore().getAt(0));
//		ADAMBoxMaker.donorsSelected.clear();		
//		ADAMBoxMaker.getDonorList().disableEvents(false);
//		
//		// sector list
//		ADAMBoxMaker.getSectorList().disableEvents(true);
//		ADAMBoxMaker.getSectorList().setValue(ADAMBoxMaker.getSectorStore().getAt(0));
//		ADAMBoxMaker.sectorsSelected.clear();		
//		ADAMBoxMaker.getSectorList().disableEvents(false);
//	}
//
//	public static void globalAgentHome(ADAMWrapper adamWrapper) {
//		ADAMController_GWTSplit.containsMaps = false;
//		objectWindowId = objectWindow.getNext();
//		currentVIEW = ADAMCurrentVIEW.HOME;
//
//		ADAMVisibilityController.restoreAdamViewVisibility();
//		
//		RootPanel.get("CENTER").setStyleName("big-green-box border content");	 
//		VerticalPanel panel = new VerticalPanel();
//		panel.setHorizontalAlign(HorizontalAlignment.CENTER);
//		panel.add(new Html("<font size='4px'><b>A</b>griculture <b>D</b>evelopment <b>A</b>ssistance <b>M</b>apping System</font>"));
//		RootPanel.get("CENTER").add(panel);
//	}
//	
//	
//	
//	public static void globalAgentADAM(final ADAMWrapper adamWrapper) {
//		GWT.runAsync(new RunAsyncCallback() {
//			  public void onSuccess() {
//	
//				ADAMController_GWTSplit.containsMaps = true;
//				objectWindowId = objectWindow.getNext();
//				currentVIEW = ADAMCurrentVIEW.ADAMVIEW;
//		
//				System.out.println("CURRENT OBJECT ID: " + objectWindow);
//		
//				removeBoxes();
//				removeQuestions();
//				filters();
//				addViewMenu(globalTitle, null, null, null);
//				
//				ADAMVisibilityController.restoreAdamViewVisibility();
//				
//				RootPanel.get("TOP_LEFT").setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.SECTOR) + "-box border content");
//				RootPanel.get("TOP_RIGHT").setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.SECTOR) + "-box border content");
//				RootPanel.get("MAP_RIGHT").setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.SECTOR) + "-box border content");
//				RootPanel.get("MAP_LEFT").setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.SECTOR) + "-box border content");
//		
//		
//		
//				ADAMController_GWTSplit.addChart("TOP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.mostFinancedSectors(baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//				ADAMController_GWTSplit.addChart("MAP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), ADAMQueryVOBuilder.mostFinancedAgriculturalSectors(baseDate, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//				ADAMController_GWTSplit.addMapPredifined("global", "MAP_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), "START", adamWrapper);
//				
//		
//		
//				
//		
//				ADAMQueryVO topDonorChart = ADAMQueryVOBuilder.topDonorsChart("Top Ten Donors", new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false);	
//				ADAMQueryVO topDonorTable = ADAMQueryVOBuilder.topDonors(baseDate, crs_aggregationColumn.getGaulCode(), crs_aggregationType, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), 10);
//				
//				topDonorChart.setSwitchResource(topDonorTable);
//				topDonorTable.setSwitchResource(topDonorChart);
//				ADAMTableController.addChart("TOP_RIGHT", ADAMColorConstants.color.get(ADAMBoxContent.DONOR), topDonorChart, objectWindowId);
//		
//		
//		//		HashMap<String, String> setA = new HashMap<String, String>();
//		//		HashMap<String, String> setB = new HashMap<String, String>();
//		//		HashMap<String, String> setC = new HashMap<String, String>();
//		//		setA.put("A", "A");
//		//		setB.put("FAO", "FAO");
//		//		setC.put("B", "B");
//		//		ADAMVennController.addVenn("A", "FAO", "C", "BOTTOM_LEFT",  ADAMColorConstants.color.get(ADAMBoxContent.VENN), setA, setB, setC, "EN", objectWindowId);
//		
//		//		ADAMVennController.testQuery();
//				
//				
//		//		ADAMVenn venn = new ADAMVenn();
//		//		System.out.println("BUILDING VENN");
//		////		venn.build();
//		//		
//		//		RootPanel.get("MAP_LEFT").add(venn.buildSmallVenn(imagePath, vennBean)());
//		
//				
//				
//				
//		//		ADAMController.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter("", baseDate, new HashMap<String, List<String>>(), ADAMBoxColors.yellow.name()), objectWindowId);
//			  }
//			  public void onFailure(Throwable caught) {
//			  }
//		  });
//	}
//	
//	
//	public static void addViewMenu(String title, Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors ) {
//		ADAMVisibilityController.removeViewMenuContent();
//		ADAMVisibilityController.restoreViewMenuVisibility();
//		
//		if(!currentVIEW.equals(ADAMCurrentVIEW.HOME))		
//			RootPanel.get("VIEWMENU_LEFT").add(ADAMViewMenu.buildLeftPanel(title, countries, donors, sectors));
//		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW))
//			RootPanel.get("VIEWMENU_RIGHT").add(ADAMViewMenu.buildRightPanel(true));
////		RootPanel.get("VIEWMENU_RIGHT").setStyleName("viewmenu-box");
//		
//	}
//
//	public static void filters() {
//		System.out.println("ADAMVIEW: " + currentVIEW);
//		removeFilters();
//		removeQuestions();
//
//		if(currentVIEW.equals(ADAMCurrentVIEW.HOME)){
//			removeFilters();
//			removeQuestions();			
//		} else {
//			//		RootPanel.get("COUNTRY_SELECTOR").setStyleName("small-yellow-box border display");
//
//			if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//				RootPanel.get("COUNTRY_SELECTOR").setStyleName("div-country");
//				RootPanel.get("COUNTRY_SELECTOR").add(ADAMBoxMaker.buildCountrySelector());
//			}	
//
//			RootPanel.get("DONOR_SELECTOR").setStyleName("div-donor");
//			RootPanel.get("DONOR_SELECTOR").add(ADAMBoxMaker.buildDonorSelector());
//
//
//			if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){ 
//				RootPanel.get("SECTOR_SELECTOR").setStyleName("div-sector");
//				RootPanel.get("SECTOR_SELECTOR").add(ADAMBoxMaker.buildSectorSelector());			
//			}
//
//			if ( !ADAMQuantityColumn.getIsBuild() ) {
//				if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
//					RootPanel.get("AGGREGATION_COLUMN").setStyleName("div-aggregation");
//					RootPanel.get("AGGREGATION_COLUMN").add(new ADAMQuantityColumn().build(currentVIEW));				
//				}
//
//			}else {
//				if(currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX) || currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//					removeAggregationColumn();
//					RootPanel.get("SECTOR_SELECTOR").setStyleName("");
//					
//					if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES))
//						RootPanel.get("COUNTRY_SELECTOR").setStyleName("");
//					
//					ADAMQuantityColumn.isBuild = false;
//				}
//			}
//
//
//			if(!ADAMReferencePeriodPanel.getIsBuild()) {
//				if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//					RootPanel.get("REFERENCE_PERIOD").setStyleName("div-date");
//					RootPanel.get("REFERENCE_PERIOD").add(ADAMReferencePeriodPanel.buildReferencePeriod(currentVIEW));
//				}
//
//			} else {
//				if(currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
//					removeReferencePeriodFilter();
//					ADAMReferencePeriodPanel.isBuild = false;
//				}
//			}
//
//
//			ADAMVisibilityController.restoreKeyMessagesVisiblity();
//		}	
////		updateSelectionMessagesAgent();
//
//	}
//
//
//	public static TreeMap<String, String> sortByKeys(Map<String, String> in) {
//		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
//		for (String key : in.keySet())
//			out.put(key, in.get(key));
//		return out;
//	}
//
//	public static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
//		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
//		List<String> mapKeys = new ArrayList<String>(in.keySet());
//		List<String> mapValues = new ArrayList<String>(in.values());
//		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
//		Object[] sortedArray = sortedSet.toArray();
//		int size = sortedArray.length;
//		for (int i=0; i<size; i++)
//			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
//		return out;
//	}
//
//
//
//
//	/*****
//	 * MAPS
//	 */
//
//	public static void checkBoxes() {
//		checkWidget("TOP_LEFT");
//		checkWidget("TOP_RIGHT");
//		checkWidget("BOTTOM_LEFT");
//		checkWidget("BOTTOM_RIGHT");
//		checkWidget("CENTER");
//	}
//
//
//	private static void checkWidget(String position) {
//		if (RootPanel.get(position).getWidgetCount() > 0){
//			System.out.println("CHECK WIDGET position:" + position + " | " + RootPanel.get(position).getWidget(0).getTitle());
//			if ( RootPanel.get(position).getWidget(0).getTitle().equalsIgnoreCase("MAP")) {
//				System.out.println("MAP (" + position + ")");
//			}
//			else {
//				System.out.println("NOT MAP (" + position + ")");
//			}
//		}
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//	/******/
//
//	public static void addMapPredifined(final String mapId, final String position, final String color, final String mapType, final ADAMWrapper adamWrapper){
//		  GWT.runAsync(new RunAsyncCallback() {
//			  public void onSuccess() {
//				RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//		
//		//		adamWrapper.buildMapPanel(mapId, ADAMConstants.colorsHEX.get(ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT)), mapType, position);
//		
//				adamWrapper.buildMapPanel(mapId, color, mapType, position);
//		
//				
//				inizializeSmallMap(position,  color, adamWrapper.getAdamMapsWrapper().get(mapId));
//	          }
//
//		      public void onFailure(Throwable caught) {
////	            Window.alert("Hello, AJAX");
//	          }
//	        });
//	}
//
//	public static void inizializeSmallMap(final String position, final String color, ADAMMapWrapper adamMapWrapper) {
//
//		if (RootPanel.get(position).getWidgetCount() > 0) {
//			if ( RootPanel.get(position).getTitle().equalsIgnoreCase("MAP")) {
//				RootPanel.get(position).getWidget(0).setVisible(false);
//			}
//			else
//				RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//		}
//
//
//		RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//		adamMapWrapper.setCurrentPosition(position);
//
//		RootPanel.get(position).add(adamMapWrapper.getPanel());
//		RootPanel.get(position).getWidget(0).setTitle("MAP");
//	}
//
//
//
//	public static void initializePredifinedMap(final String mapType, final ADAMMapWrapper adamMapWrapper, final String language, final String position, final String color) {
//		final ADAMOLPanel adamOLPanel = adamMapWrapper.getAdamMapPanel().getAdamOLPanel();
////		final ADAMLayersHandle adamLayersHandle = adamMapWrapper.getAdamMapPanel().getAdamLayersHandle();
//		final VerticalPanel infoPanel = adamMapWrapper.getInfoPanel();
//		final ClientMapView mapView = adamOLPanel.getMapView();
//		final MapPanel mapPanel = adamOLPanel.getMapPanel();
//
//
//
//		MapServiceEntry.getInstance().getLayersByCodingSystem("ADAMLayers", mapType, new AsyncCallback<List<ClientGeoView>>() {
//			public void onSuccess(List<ClientGeoView> cgvlist) {
//				ADAMResultVO adamResultVO = new ADAMResultVO();
//				adamResultVO.setBoxColor(color);
//				mapView.setTitle("");
//				ClientBBox bbox = null;
//
//				int i = 0;
//				for (ClientGeoView clientGeoView : cgvlist) {
//					if (bbox == null)
//						bbox = clientGeoView.getBBox();
//					else
//						bbox = bbox.union(clientGeoView.getBBox());
//					LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
//					vo.setLegendUrl(getLegendURL(vo));
//
//					/** TODO: this is a workaround for the GAUL0 **/
////					if ( i == 0 || i == 1)
//					if ( i == 0 )
//						vo.setIsBaseLayer(true);
//
//
//
//					if ( i == cgvlist.size() - 1) {
//						infoPanel.removeAll();
//						infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
//
////						infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
//						infoPanel.layout();
//					}
//
//					adamResultVO.addLayer(vo);
//					mapView.addLayer(clientGeoView);
//
//					i++;
//				}
//
//
//				mapView.setBbox(bbox);
//				mapPanel.createADAMMap(mapView, 7,1000000000, 5000);
////				mapPanel.createMap(mapView);
//				mapPanel.zoomToMaxExtent();
//
//
//				adamMapWrapper.setAdamResultVO(adamResultVO);
//				
//				adamResultVO.setOutputType(ADAMBoxContent.MAP.toString());
//				adamResultVO.setResourceType(ADAMBoxContent.MAP.toString());
//				
//				mapMap.put(position, adamMapWrapper.getAdamResultVO());
//				currentUsedObjects.put(position, ADAMBoxContent.MAP);
//				
//				
//				/*** TODO HERE IT'S TO MAKE THE JOIN TO THE MAP ***/
//				
//				ADAMController_GWTSplit.addJoinDatset("MAP_LEFT", ADAMQueryVOBuilder.countriesBudgetByFilter("Amount od...auioa", baseDate, new HashMap<String, List<String>>(), ADAMBoxColors.yellow.name(), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//
//			}
//
//			public void onFailure(Throwable e) {
//				FenixAlert.error("ERROR", e.getMessage());
//			}
//
//		});
//	}
//
//	private static String getLegendURL(LayerVO vo) {
//		String geoserverURL = (vo.getWmsURL().indexOf("wms") > 0 ? vo.getWmsURL().substring(0, vo.getWmsURL().indexOf("wms")) : vo.getWmsURL());
//		String layerName = vo.getLayerName();
//		String layerTitle = vo.getLayerTitle();
//		String styleName = vo.getStyleName();
//		String url = new String();
//		if ( vo.getLegendUrl() != null ) {
//			url = vo.getLegendUrl();
//		}
//		else{
//			url = geoserverURL +
//					     "wms?VERSION=1.1.0&REQUEST=GetLegendGraphic&LAYER=" +
//					     layerName +
//					     "&STYLE=" +
//					     styleName +
//					     "&WIDTH=50&HEIGHT=20&FORMAT=image/png&BGCOLOR=0xFFFFFF&TRANSPARENT=TRUE&TIMESTAMP=" +
//					     0;
//		}
//		System.out.println("LEGEND URL: " + url);
//		return url;
//	}
//
//
//	/*** TODO: HANDLE THE LAYERS LIST ADAMRESULTVO **/
//	public static void addLayersAgent(final String mapType, final ADAMMapWrapper adamMapWrapper, final String language) {
//		final ADAMOLPanel adamOLPanel = adamMapWrapper.getAdamMapPanel().getAdamOLPanel();
////		final ADAMLayersHandle adamLayersHandle = adamMapWrapper.getAdamMapPanel().getAdamLayersHandle();
//		final VerticalPanel infoPanel = adamMapWrapper.getInfoPanel();
//		final ClientMapView mapView = adamOLPanel.getMapView();
//		final MapPanel mapPanel = adamOLPanel.getMapPanel();
//
//
//		MapServiceEntry.getInstance().getLayersByCodingSystem("ADAMLayers", mapType, new AsyncCallback<List<ClientGeoView>>() {
//			public void onSuccess(List<ClientGeoView> cgvlist) {
//
//				ClientBBox bbox = null;
//				List<LayerVO> layerVOs = new ArrayList<LayerVO>();
//				for (ClientGeoView clientGeoView : cgvlist) {
//					if (bbox == null)
//						bbox = clientGeoView.getBBox();
//					else
//						bbox = bbox.union(clientGeoView.getBBox());
//
//					LayerVO vo = new LayerVO(clientGeoView.getTitle(), clientGeoView.getClientId(), clientGeoView.getLayerId(), clientGeoView.getOpacity(), clientGeoView.getLayerType().name(), clientGeoView.getLayerName());
//					vo.setWmsURL(clientGeoView.getGetMapUrl());
//					vo.setStyleName(clientGeoView.getStyleName());
//					vo.setFenixCode(clientGeoView.getFenixCode());
//					vo.setAbstractAbstract(clientGeoView.getAbstractAbstract());
//					vo.setSource(clientGeoView.getSource());
//					vo.setSourceContact(clientGeoView.getSourceContact());
//					vo.setProvider(clientGeoView.getProvider());
//					vo.setProviderContact(clientGeoView.getProviderContact());
//					vo.setIsHidden(clientGeoView.isHidden());
//					vo.setClientBBox(clientGeoView.getBBox());
//					if ( clientGeoView.getLagendUrl() != null)
//						vo.setLegendUrl(clientGeoView.getLagendUrl());
//					layerVOs.add(vo);
//					mapPanel.addLayer(clientGeoView);
//				}
//
//				for (int i = layerVOs.size() - 1 ; i >= 0 ; i--){
////					adamLayersHandle.addLayer(layerVOs.get(i));
//					adamMapWrapper.getAdamResultVO().addLayer(layerVOs.get(i));
//					if ( i == layerVOs.size() - 1) {
//						infoPanel.removeAll();
//						infoPanel.add(new Html("<div align='left'> <img src='" + getLegendURL(layerVOs.get(i)) + "'" + "> </div>"));
////						infoPanel.add(new Html("<div align='left'> <img src='" + layerVOs.get(i).getLegendUrl() + "'" + "> </div>"));
//						infoPanel.layout();
//					}
//				}
//
//			}
//
//			public void onFailure(Throwable e) {
//				FenixAlert.error("ERROR", e.getMessage());
//			}
//
//		});
//	}
//
//
//	public static SelectionListener<ButtonEvent> fullScreenMap(final ADAMMapWrapper adamMapWrapper) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				removeBoxesFromMap(adamMapWrapper.getCurrentPosition());
//				ADAMVisibilityController.styleVisibilityDisplay("CENTER");
//				
//				System.out.println("fullScreenMap: " );
//				RootPanel.get("CENTER").setStyleName("big-" + ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT) + "-box border content");
//
//				RootPanel.get("CENTER").add(setFullScreenMap(adamMapWrapper));
//			}
//		};
//	}
//
//	public static SelectionListener<ButtonEvent> smallScreenMap(final String position, final String color, final ADAMMapWrapper adamMapWrapper) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//
//				RootPanel.get("CENTER").setStyleName("remove-box");
//				RootPanel.get(position).setStyleName("medium-" + color + "-box border content");
//
//				restoreBoxesContent("MAP");
//				
//				ADAMVisibilityController.styleVisibilityDisplay(position);
//				ADAMVisibilityController.restoreAdamViewVisibility();
//				
//				RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT) + "-box border content");
//				RootPanel.get(position).add(setSmallScreen(adamMapWrapper));
//			}
//		};
//	}
//
//	private static ContentPanel setFullScreenMap(ADAMMapWrapper adamMapWrapper) {
//		RootPanel.get("CENTER").setStyleName("big-"+ ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT)+"-box border content");
//
//		adamMapWrapper.setFullScreen();
//		System.out.println("joining");
//
//		return adamMapWrapper.getPanel();
//	}
//
//	private static ContentPanel setSmallScreen(ADAMMapWrapper adamMapWrapper) {
////		RootPanel.get("BOTTOM_LEFT").setStyleName("medium-yellow-box border  content");
//		adamMapWrapper.setSmallScreen();
////		restoreCustomContent();
//		return adamMapWrapper.getPanel();
//	}
//
//	private static void removeAllLayers(final ADAMMapWrapper adamMapWrapper) {
//		List<Long> gvids = new ArrayList<Long>();
//
//		for (Long gvid : adamMapWrapper.getAdamResultVO().getLayersMap().keySet()) {
//			LayerVO layerVo = adamMapWrapper.getAdamResultVO().getLayersMap().get(gvid);
//			if ( !layerVo.isBaseLayer())
//				gvids.add(gvid);
//
//		}
//		MapPanel mapPanel = adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel();
//		for(Long gvid : gvids) {
//			mapPanel.removeLayer(gvid);
//			adamMapWrapper.getAdamResultVO().getLayersMap().remove(gvid);
//		}
//	}
//
//	public static SelectionListener<MenuEvent> exportMapAsZip(final ADAMMapWrapper adamMapWrapper, final String language) {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
//				exportMapAsZipAgent(adamMapWrapper, language);
//			}
//		};
//	}
//	
//	private static void exportMapAsZipAgent(final ADAMMapWrapper adamMapWrapper, final String language) {
//		ClientMapView cmv = createClientMapView(adamMapWrapper);
//		final LoadingWindow loadingWindow = new LoadingWindow(HaitiLangEntry.getInstance(language).exportMap(), BabelFish.print().pleaseWait(), HaitiLangEntry.getInstance().exporting());
//		loadingWindow.showLoadingBox();
//
//		MapServiceEntry.getInstance().exportAsZip(cmv, true, 1024, 768, new AsyncCallback<String>() {
//			public void onSuccess(String result) {
//				loadingWindow.destroyLoadingBox();
//				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//			}
//			public void onFailure(Throwable e) {
//				loadingWindow.destroyLoadingBox();
//				FenixAlert.error("ERROR", e.getMessage());
//			}
//		});
//	}
//
//	private static ClientMapView createClientMapView(ADAMMapWrapper adamMapWrapper) {
//		String mapDivId = adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().getMapDivId();
//		ClientBBox bbox = new OLBBoxRetriever(mapDivId).getBbox();
//		ClientMapView cmv = new ClientMapView();
//		cmv.setBbox(bbox);
////		List<LayerVO> vos = getSortedLayers(adamMapWrapper.getAdamResultVO().getLayersMap());
//		List<LayerVO> vos = getLayers(adamMapWrapper.getAdamResultVO().getLayersMap());
//
//		for (LayerVO vo : vos) {
//			ClientGeoView cgv = new ClientGeoView();
//			cgv.setBBox(bbox);
//			cgv.setGetMapUrl(vo.getWmsURL());
//			cgv.setLayerName(vo.getLayerName());
//			cgv.setStyleName(vo.getStyleName());
//			cgv.setTitle(vo.getLayerTitle());
//			cgv.setHidden(false);
//			cgv.setFenixCode(vo.getFenixCode());
//			if ( vo.getLayerID() != null)
//				cgv.setLayerId(vo.getLayerID());
//				
//			cgv.setIsQuickPrj(vo.getIsQuickPrj());	
//			cmv.addLayer(cgv);
//		}
//		return cmv;
//	}
//
//	private static List<LayerVO> getSortedLayers(LinkedHashMap<Long, LayerVO> layersMap) {
//		List<LayerVO> vos = new ArrayList<LayerVO>();
//		for(Long id : layersMap.keySet()) {
//			vos.add(layersMap.get(id));
//		}
//
//		List<LayerVO> result = new ArrayList<LayerVO>();
//
//		for(int i = vos.size() -1 ; i >= 0; i--) {
//			result.add(vos.get(i));
//		}
//		return result;
//	}
//
//	private static List<LayerVO> getLayers(LinkedHashMap<Long, LayerVO> layersMap) {
//		List<LayerVO> vos = new ArrayList<LayerVO>();
//		for(Long id : layersMap.keySet()) {
//			vos.add(layersMap.get(id));
//		}
//
//		List<LayerVO> result = new ArrayList<LayerVO>();
//
//		for(int i = 0 ; i < vos.size() ; i++) {
//			result.add(vos.get(i));
//		}
//		return result;
//	}
//
//	private static Integer getOpacity(HaitiLayersPanel haitiLayersPanel, Long id) {
//		Map<Long, HorizontalPanel> layerPanelMap = haitiLayersPanel.getLayerPanelsMap();
//		HorizontalPanel layerPanel = layerPanelMap.get(id);
//		return ((Slider)layerPanel.getData("slider")).getValue();
//	}
//
//
//	public static void addJoinDatset(final String position, ADAMQueryVO qvo, final long objID) {
//		RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT) + "-box border content");
//
//		/** TODO call method to create the view of the dataset **/
//		ADAMServiceEntry.getInstance().joinDataset(qvo, new AsyncCallback<JoinDatasetVO>() {
//			public void onSuccess(JoinDatasetVO jvo) {
//				System.out.println("addJoinDatset");
//
//				// is it right to take the global one?
//				ADAMMapWrapper adamMapWrapper = ADAMWrapper.getAdamMapsWrapper().get("global");
//
//				if ( adamMapWrapper == null)
//					System.out.println("adamMapWrapper is null");
//				else
//					System.out.println("adamMapWrapper is not null");
//					
//
//				addJoinedLayer(adamMapWrapper, jvo, position, objID);
//			}
//
//			public void onFailure(Throwable arg0) {
//
//			}
//
//		});
//
//
//
//	}
//
//
//	public static void addJoinedLayer(final ADAMMapWrapper adamMapWrapper, final JoinDatasetVO joinDatasetVO, final String position, final long objID) {
//		System.out.println("addJoinedLayer");
//		MapServiceEntry.getInstance().createJoinDatasetView(joinDatasetVO,new AsyncCallback<ClientGeoView>() {
//			public void onSuccess(ClientGeoView clientGeoView) {
//
//				System.out.println("JoinedLayer objID: " + objID + " | objectWindowId:" + objectWindowId);
//
//
//				if ( objID == objectWindowId ) {
//					final VerticalPanel infoPanel = adamMapWrapper.getInfoPanel();
//
//					// TODO: delete other layers
//					removeAllLayers(adamMapWrapper);
//
//					System.out.println("clientGeoView.getStyleName():" + clientGeoView.getStyleName());
//					clientGeoView.setStyleName(clientGeoView.getStyleName().toLowerCase());
//					System.out.println("clientGeoView.getLayerName():" + clientGeoView.getLayerName());
//					adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().addLayer(clientGeoView);
//
//					// track the layer
//					LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
//					vo.setLegendUrl(getLegendURL(vo));
//					vo.setLayerID(new Long(0));
//					vo.setIsQuickPrj(true);
//					
//
//					adamMapWrapper.getAdamResultVO().addLayer(vo);
//
//					// ADD as selected layer
//					NativeOLCalls.setMapPanel(adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel());
//					adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().setSelectedLayer(vo);
//
//					// global track of the layer (to clean them)
//					ADAMWrapper.addProjection(joinDatasetVO.getDatasetViewName(), joinDatasetVO.getLayerJoinView());
//
//					RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT) + "-box border content");
//					adamMapWrapper.setCurrentPosition(position);
//
//					RootPanel.get(position).add(adamMapWrapper.getPanel());
//					RootPanel.get(position).getWidget(0).setTitle("MAP");
//					adamMapWrapper.getPanel().setVisible(true);
//
//
//
//
//					infoPanel.removeAll();
//					infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
//
//	//				infoPanel.add(new Html("<div align='left'> <img src='" + vo.getLegendUrl() + "'" + "> </div>"));
//					infoPanel.layout();
//
//
//
//					/** ADDING THE GAUL0  AND LABELS **/
//					List<String> layersToAdd = new ArrayList<String>();
//					layersToAdd.add("layer_gaul0_simple");
//////					layersToAdd.add("layer_gaul0");
////
////					MapServiceEntry.getInstance().getLayersByCode(layersToAdd, new AsyncCallback<List<ClientGeoView>>() {
////						public void onSuccess(List<ClientGeoView> clientGeoViews) {
////
////							int i=0;
////							for(ClientGeoView clientGeoView : clientGeoViews) {
//////								if ( i == 1)
//////									clientGeoView.setStyleName("label_30733188_adm0_name_000000");
////
////
////								adamMapWrapper.getAdamMapPanel().getAdamOLPanel().getMapPanel().addLayer(clientGeoView);
////
////								// track the layer
////								LayerVO vo = clientGeoViewToLayerVO(clientGeoView);
////
////								adamMapWrapper.getAdamResultVO().addLayer(vo);
////								i++;
////							}
////
////
////						}
////
////					@Override
////					public void onFailure(Throwable arg0) {
////									// TODO Auto-generated method stub
////		
////								}
////		
////							});
//				}
//
//			}
//
//			public void onFailure(Throwable arg0) {
//				System.out.println("onFailure");
//			}
//
//		});
//
//	}
//
//	private static LayerVO clientGeoViewToLayerVO(ClientGeoView clientGeoView){
//		LayerVO vo = new LayerVO(clientGeoView.getTitle(), clientGeoView.getClientId(), clientGeoView.getLayerId(), clientGeoView.getOpacity(), clientGeoView.getLayerType().name(), clientGeoView.getLayerName());
//		vo.setWmsURL(clientGeoView.getGetMapUrl());
//		vo.setStyleName(clientGeoView.getStyleName());
//		vo.setFenixCode(clientGeoView.getFenixCode());
//		vo.setAbstractAbstract(clientGeoView.getAbstractAbstract());
//		vo.setSource(clientGeoView.getSource());
//		vo.setSourceContact(clientGeoView.getSourceContact());
//		vo.setProvider(clientGeoView.getProvider());
//		vo.setProviderContact(clientGeoView.getProviderContact());
//		vo.setIsHidden(clientGeoView.isHidden());
//		vo.setClientBBox(clientGeoView.getBBox());
//		if ( clientGeoView.getLagendUrl() != null)
//			vo.setLegendUrl(getLegendURL(vo));
//		return vo;
//	}
//
//	/*** END MAPS **/
//
//
//	
//
//
//
//
//	/*** REPORT **/
//
//	public static Command callView(final ADAMCurrentVIEW view ) {
//		return new Command() {
//			public void execute() {
//				callViewAgent(view);
//			}
//		};
//	};
//	
//	public static void callViewAgent(final ADAMCurrentVIEW view ) {
//		currentVIEW = view;
//		
//		
//		  GWT.runAsync(new RunAsyncCallback() {
//			  public void onSuccess() {
//		
//				if(currentVIEW.equals(ADAMCurrentVIEW.HOME)) {
//					removeAllContents(true);
//					callView(null, null,null, view); 
//				}
//				else {
//					removeAllContents(false);
//					removeSecondaryMenu();
//				    filters();
//				    callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, view); 
//				}
//			  }
//			  public void onFailure(Throwable caught) {
//			  }
//		  });
//		
//	};
//
//	public static Command createReport(final ADAMToolbar t) {
//		return new Command() {
//			public void execute() {
//				removeSecondaryMenu();
//				RootPanel.get("SECONDARY_MENU").add(t.buildReportPanel());
//			}
//		};
//	};
//
//
//	public static SelectionListener<ButtonEvent> createReport() {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				createReportAgent();
//			}
//		};
//	}
//	
//	public static void createReportAgent() {
//		ADAMReportBeanVO adamReportBeanVO = new ADAMReportBeanVO();
//		String referencePeriod = new String();
//		
//		for(String date : baseDate){
//			Date d = FieldParser.parseDate(date);
//			referencePeriod = referencePeriod + " " + (d.getYear() + 1900);
//		}
//		
//		adamReportBeanVO.setRightTitle(referencePeriod);
//		
//	
//		adamReportBeanVO.setRightTitle(referencePeriod);
//		System.out.println("currentVIEW: " + currentVIEW.toString());
//		switch(currentVIEW) {
//			case ADAMVIEW:
//				adamReportBeanVO.setReportType("CURRENTVIEW");
//				createADAMVIEWReport(adamReportBeanVO, referencePeriod);
//			case ASKADAM:
//				for(String key : questionsMap.keySet()) {
//					System.out.println("key: " + key);
//				}
//			case DONORMATRIX:
//		}
//	
//	}
//
//	public static SelectionListener<ButtonEvent> createReport(final ComboBox<GaulModelData> reportList, final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				createReportAgent(reportList, entryList, adamCodesList);
//			}
//		};
//	}
//
//	public static void createReportAgent(final ComboBox<GaulModelData> reportList, final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList) {
//		ADAMReportBeanVO adamReportBeanVO = new ADAMReportBeanVO();
//		String referencePeriod = new String();
//		System.out.println("HERE: ");
//		
//		for(String date : baseDate){
//			Date d = FieldParser.parseDate(date);
//			referencePeriod = referencePeriod + " " + (d.getYear() + 1900);
//		}
//		
//		adamReportBeanVO.setRightTitle(referencePeriod);
//		
//		String reportCode = reportList.getSelection().get(0).getGaulCode();
//		if( reportCode.equalsIgnoreCase("ASKADAM" )){
//			createASKADAMReport(reportList, entryList, adamCodesList, adamReportBeanVO);
//		} else if(reportCode.equalsIgnoreCase("DONORPROFILE" )) {
//			System.out.println("DONORPROFILE .... ");
//			adamReportBeanVO.setRightTitle("");
//			adamReportBeanVO.setLeftTitle("");
//			createDonorProfileReport(entryList, adamCodesList, adamReportBeanVO, referencePeriod);
//		} else if ( reportCode.equalsIgnoreCase("CURRENTVIEW" )) {
//			adamReportBeanVO.setRightTitle(referencePeriod);
//			System.out.println("currentVIEW: " + currentVIEW.toString());
//			switch(currentVIEW) {
//				case ADAMVIEW:
//					createADAMVIEWReport(adamReportBeanVO, referencePeriod);
//				case ASKADAM:
//					for(String key : questionsMap.keySet()) {
//						System.out.println("key: " + key);
//					}
//				case DONORMATRIX:
//			}
//		}
//
//	}
//	
//	private static void createASKADAMReport(final ComboBox<GaulModelData> reportList, final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList, final ADAMReportBeanVO adamReportBeanVO) {
//		
//		
//		GaulModelData selectedEntity = entryList.getSelection().get(0);
//		String entity = selectedEntity.getGaulCode();
//
//		GaulModelData codesEntity = adamCodesList.getSelection().get(0);
//		String code = codesEntity.getGaulCode().substring(1 + codesEntity.getGaulCode().indexOf('_'));
//		String label = codesEntity.getGaulLabel();
//		
//		// title
//		adamReportBeanVO.setCenterTitle(label);
//
//		Map<String, String> codes = new HashMap<String, String>();
//		codes.put(code, label);
//
//		System.out.println("askADAM: " + entity + " | " + code + " | " + label);
//
//		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//
//		if ( entity.equalsIgnoreCase("Gaul_")) {
//			adamReportBeanVO.setReportType("ASKADAM_COUNTRY");
//			Boolean countryAdded = addCountryFilter(filters, codes);
//			System.out.println("askADAM countryAdded: " + countryAdded + " | " + filters);
//			adamReportBeanVO.setLeftTitle("Country Profile");
//			
//
//		}
//
//		else if ( entity.equalsIgnoreCase("Donor_")) {
//			adamReportBeanVO.setReportType("ASKADAM_DONOR");
//			Boolean donorAdded = addDonorFilter(filters, codes);
//			System.out.println("askADAM donorAdded: " + donorAdded + " | " + filters);
//			adamReportBeanVO.setLeftTitle("Resource Partner Profile");
//		}
//		
//		LinkedHashMap<String, ADAMQueryVO> qvos = ADAMQuestionMaker.buildReportQuestions(entity, code, label, filters, baseDate, entity, objectWindowId);
//
//		ADAMServiceEntry.getInstance().createReport(qvos, adamReportBeanVO, new AsyncCallback<String>() {
//			public void onSuccess(String result) {
//				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//
//			}
//			
//			public void onFailure(Throwable result) {
//
//			}
//		});
//	}
//
//	private static void createADAMVIEWReport(final ADAMReportBeanVO adamReportBeanVO, String referencePeriod) {
//		adamReportBeanVO.setReportType("CURRENTVIEW");
//		for(String key : currentUsedObjects.keySet()) {
//			System.out.println("key: " + key);
//		}
//		HashMap<String, ADAMResultVO> rvos = getCurrentViewBoxes();
//		
//		adamReportBeanVO.setRightTitle(referencePeriod);
//		adamReportBeanVO.setResources(rvos);
//		
//		for(String key : rvos.keySet()) {
//			System.out.println("key: " + key + " |" + rvos.get(key).getOutputType() + " | " + rvos.get(key).getResourceType());
//		}
//		ADAMViewMenu.showWaitingPanel("Creating PDF...");
////
////		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportReport(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
////		loadingWindow.showLoadingBox();
//		ADAMServiceEntry.getInstance().createReport(null, adamReportBeanVO, new AsyncCallback<String>() {
//			public void onSuccess(String result) {
//				System.out.println("result: " + result);
//				ADAMViewMenu.hideWaitingPanel();
//				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//
//			}
//			
//			public void onFailure(Throwable result) {
//				ADAMViewMenu.hideWaitingPanel();
//				System.out.println("error: " + result);
//			}
//		});
//
//	}
//
//private static void createDonorProfileReport(final ComboBox<GaulModelData> entryList, final ComboBox<GaulModelData> adamCodesList, final ADAMReportBeanVO adamReportBeanVO, String referenceperiod) {
//		
//		GaulModelData selectedEntity = entryList.getSelection().get(0);	
//	    String entity = selectedEntity.getGaulCode();
//
//		GaulModelData codesEntity = adamCodesList.getSelection().get(0);
//		String code = codesEntity.getGaulCode().substring(1 + codesEntity.getGaulCode().indexOf('_'));
//		String label = codesEntity.getGaulLabel();
//		
//		// title
//		adamReportBeanVO.setCenterTitle(label);
//		adamReportBeanVO.setReportType("DONORPROFILE");
//		
//		Map<String, String> codes = new HashMap<String, String>();
//		codes.put(code, label);
//
//		System.out.println("createDonorProfileReport: " + entity + " | " + code + " | " + label);
//
//		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//
//		if (entity.equalsIgnoreCase("Donor_")) {
//			Boolean donorAdded = addDonorFilter(filters, codes);
//			System.out.println("askADAM donorAdded: " + donorAdded + " | " + filters);
//			//adamReportBeanVO.setLeftTitle("Resource Partner Profile");
//		}
//
//		LinkedHashMap<String, ADAMQueryVO> qvos = buildDonorProfileQueries(entity, code, label, filters, baseDate, entity, objectWindowId);
//	
//		ADAMServiceEntry.getInstance().createReport(qvos, adamReportBeanVO, new AsyncCallback<String>() {
//			public void onSuccess(String result) {
//				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//
//			}
//			
//			public void onFailure(Throwable result) {
//
//			}
//		});
//	}
//
//private static LinkedHashMap<String, ADAMQueryVO> buildDonorProfileQueries(String entity, String code, String label, HashMap<String, List<String>> filters, List<String> dates, String type, final long objID) {
//	LinkedHashMap<String, ADAMQueryVO> map = new LinkedHashMap<String, ADAMQueryVO>();
//	List<ADAMQueryVO> qvos = new ArrayList<ADAMQueryVO>();
//	
//	ADAMQueryVO q = ADAMQueryVOBuilder.totalODADonorCommitment(code, label, dates, ADAMBoxColors.green.name());
//	q.setDescription("The chart details the total ODA commitments for " + label + ".");
//	//q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
//	//q.setDescription("Chart 2 ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
//	qvos.add(q);
//	
//	q = ADAMQueryVOBuilder.donorProfile(code, label, ADAMBoxColors.yellow.name());
//	q.setDescription("The table details the resource partner profile for " + label + ".");
//	//q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
//	//q.setDescription("Chart 2 ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
//	qvos.add(q);
//	
//	q = ADAMQueryVOBuilder.donorProcesses(code, label, ADAMBoxColors.red.name());
//	q.setDescription("The table details the resource partner processes for " + label +".");
//	//q.setBoxColor(ADAMColorConstants.color.get(ADAMBoxContent.DONOR));
//	//q.setDescription("Chart 2 ranks the most active resource partners in " + label + " based on total aid flows. It shows the total disbursement of the ten most active resource partners.");
//	qvos.add(q);
//	
//		
//	Integer i=0;
//	for (ADAMQueryVO qvo : qvos ) {
//		map.put(i.toString(), qvo);
//		i++;
//	}
//	
//	System.out.println("buildDonorProfileQueries map: " + map);
//	return map;
//}
//	public static SelectionListener<ButtonEvent> createReport(final String reportType) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				createReportAgent(reportType);
//			}
//		};
//	}
//
//	private static void createReportAgent(final String reportType) {
//		HashMap<String, ADAMQueryVO> qvos = null;
//		if ( reportType.equals("GLOBAL_REPORT"))
//			qvos = createGlobalViewADAMQueryVO();
//		
//		System.out.println("create report");
//		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportReport(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
//		loadingWindow.showLoadingBox();
////		ADAMServiceEntry.getInstance().createReport(qvos, reportType, new AsyncCallback<String>() {
////			public void onSuccess(String result) {
////				/** this is for the prevview **/
//////				loadingWindow.destroyLoadingBox();
//////				com.extjs.gxt.ui.client.widget.Window window = new com.extjs.gxt.ui.client.widget.Window();
//////				ContentPanel cont = new ContentPanel();
//////				cont.setHeaderVisible(false);
//////				cont.setLayout(new FitLayout());
//////				window.setLayout(new FitLayout());
//////				window.setSize(800, 600);
//////				HTML content = new HTML(result);
//////				cont.add(content);
//////				window.add(cont);
//////				window.show();
////
////				loadingWindow.destroyLoadingBox();
////				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
////
////			}
////
////			public void onFailure(Throwable arg0) {
////				loadingWindow.destroyLoadingBox();
////			}
////		});
//	}
//
//
//	/**
//	 * this method add the query to do for the report
//	 *
//	 * @return
//	 */
//	private static HashMap<String, ADAMQueryVO> createGlobalViewADAMQueryVO() {
//		HashMap<String, ADAMQueryVO> qvos = new HashMap<String, ADAMQueryVO>();
////		qvos.put("mostFinancedAgriculturalSectors", mostFinancedAgriculturalSectors());
//		qvos.put("MAP_GLOBAL_REPORT", createMapStandardQueryVO("GLOBAL_REPORT"));
////		qvos.put("GLOBAL_VIEW_TABLE", topDonors());
//
//		return qvos;
//	}
//
//
//	private static ADAMQueryVO createMapStandardQueryVO(String mapType) {
//		ADAMQueryVO qvo = new ADAMQueryVO();
//		qvo.setOutputType("MAP_PREDIFINED_REPORT");
//		qvo.setCodingSystem(mapType);
//		qvo.setTitle("Global Map");
//		qvo.setBoxColor("");
//		return qvo;
//	}
//
//
//
//
//
//	/************* BOX CONTROLLERS ****/
//
//
//	public static void removeAllContents(Boolean removeFilters){
//		removeBoxes();
//		if ( removeFilters )
//			removeFilters();
//		removeQuestions();
//		removeLoginMessage();
//		removeMultiSelections();
//	}
//
//	public static void removeCenter() {
//		removeWidget("CENTER");
//		RootPanel.get("CENTER").setStyleName("remove-box");
//	}
//
//	public static void removeBoxes() {
//		removeWidget("TOP_LEFT");
//		removeWidget("TOP_RIGHT");
//		removeWidget("BOTTOM_LEFT");
//		removeWidget("BOTTOM_RIGHT");
//		removeWidget("MAP_LEFT");
//		removeWidget("MAP_RIGHT");
//		removeWidget("CENTER");
//
//		RootPanel.get("TOP_LEFT").setStyleName("remove-box");
//		RootPanel.get("TOP_RIGHT").setStyleName("remove-box");
//		RootPanel.get("BOTTOM_LEFT").setStyleName("remove-box");
//		RootPanel.get("BOTTOM_RIGHT").setStyleName("remove-box");
//		RootPanel.get("MAP_LEFT").setStyleName("remove-box");
//		RootPanel.get("MAP_RIGHT").setStyleName("remove-box");
//		RootPanel.get("CENTER").setStyleName("remove-box");
//		
//		removeCustomObjects();
//		
//		ADAMVisibilityController.removeAdamViewVisibility();
//		
////		removeSecondaryMenu();
//	}
//	
//	private static void removeCustomObjects() {
//		ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM_TABLE");
//		ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM");
//		for(int i = 0 ; i <= CUSTOMS; i ++) {
//			try {
//				if (RootPanel.get("CUSTOM_1" + i).getWidgetCount() > 0){
//					removeWidget("CUSTOM_1" + i);
//					RootPanel.get("CUSTOM_1" + i).setStyleName("remove-box");
//					ADAMVisibilityController.styleVisibilityNoDisplay("CUSTOM_1" + i);
//				}
//			}catch (Exception e) {
//			}
//				
//		}
//		
//	}
//
//
//
//	public static void removeMapTitles() {
//		removeMapTitlesAgent("TOP_LEFT");
//		removeMapTitlesAgent("TOP_RIGHT");
//		removeMapTitlesAgent("BOTTOM_LEFT");
//		removeMapTitlesAgent("BOTTOM_RIGHT");
//		removeMapTitlesAgent("CENTER");
//		
//		ADAMVisibilityController.styleVisibilityNoDisplay("TOP_LEFT");
//		ADAMVisibilityController.styleVisibilityNoDisplay("TOP_RIGHT");
//		ADAMVisibilityController.styleVisibilityNoDisplay("BOTTOM_LEFT");
//		ADAMVisibilityController.styleVisibilityNoDisplay("BOTTOM_RIGHT");
//		ADAMVisibilityController.styleVisibilityNoDisplay("CENTER");
//	}
//
//	private static void removeMapTitlesAgent(String position) {
//		if (RootPanel.get(position).getWidgetCount() > 0){
//			RootPanel.get(position).getWidget(0).setTitle("");
//			ADAMVisibilityController.styleVisibilityNoDisplay(position);
//		}
//	}
//
//
//
//
//	public static void removeBoxesFromMap(String currentMapPosition) {
//		removeWidgetFromMap("TOP_LEFT", currentMapPosition);
//		removeWidgetFromMap("TOP_RIGHT", currentMapPosition);
//		removeWidgetFromMap("BOTTOM_LEFT", currentMapPosition);
//		removeWidgetFromMap("BOTTOM_RIGHT", currentMapPosition);
//		removeWidgetFromMap("MAP_LEFT", currentMapPosition);
//		removeWidgetFromMap("MAP_RIGHT", currentMapPosition);
//		removeWidgetFromMap("CENTER", currentMapPosition);
//
//		RootPanel.get("TOP_LEFT").setStyleName("remove-box");
//		RootPanel.get("TOP_RIGHT").setStyleName("remove-box");
//		RootPanel.get("BOTTOM_LEFT").setStyleName("remove-box");
//		RootPanel.get("BOTTOM_RIGHT").setStyleName("remove-box");
//		RootPanel.get("MAP_LEFT").setStyleName("remove-box");
//		RootPanel.get("MAP_RIGHT").setStyleName("remove-box");
//		RootPanel.get("CENTER").setStyleName("remove-box");
//		
//		ADAMVisibilityController.styleVisibilityNoDisplay(currentMapPosition);
//		
//		removeCustomObjects();
//	}
//
//	private static void removeWidget(String position) {
//		if (RootPanel.get(position).getWidgetCount() > 0){
//			System.out.println("REMOVE WIDGET: " + position + " | " + RootPanel.get(position).getWidget(0).getTitle());
//			if ( RootPanel.get(position).getWidget(0).getTitle().equalsIgnoreCase("MAP")) {
//				System.out.println("setVisible(false): " + position);
//				RootPanel.get(position).getWidget(0).setVisible(false);
//			}
//			else {
//
////				RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
////				RootPanel.get(position).clear();
//				for( int i=0 ; i < RootPanel.get(position).getWidgetCount(); i++){
//					System.out.println("remove: " + position + " | " + RootPanel.get(position).getWidget(i).getTitle() );
//					RootPanel.get(position).remove(i);
//				}
//			}
//		}
//	}
//
//	private static void removeWidgetFromMap(String position, String currentMapPosition) {
//		System.out.println("currentMapPosition: " + currentMapPosition);
//		if (RootPanel.get(position).getWidgetCount() > 0 && !position.equals(currentMapPosition) ){
//			System.out.println("--->: " + position + " | " + currentMapPosition + " | " + RootPanel.get(position).getWidget(0).getTitle());
//			if ( RootPanel.get(position).getWidget(0).getTitle().equalsIgnoreCase("MAP")) {
//				System.out.println("setVisible(false): " + position);
//				RootPanel.get(position).getWidget(0).setVisible(false);
//			}
//			else {
//				System.out.println("remove: " + position);
//				RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
//			}
//		}
//		else
//			System.out.println("do nothing: " + position);
//	}
//
//
//
//	public static void removeSecondaryMenu() {
//		if (RootPanel.get("SECONDARY_MENU").getWidgetCount() > 0)
//			RootPanel.get("SECONDARY_MENU").remove(RootPanel.get("SECONDARY_MENU").getWidget(0));
//	}
//
//	public static void removeLoginMessage() {
//		if (RootPanel.get("loginMessage").getWidgetCount() > 0)
//			RootPanel.get("loginMessage").remove(RootPanel.get("loginMessage").getWidget(0));
//	}
//
////	public static void removeKeyMessages() {
////		if (RootPanel.get("KEY_MESSAGES").getWidgetCount() > 0)
////			RootPanel.get("KEY_MESSAGES").remove(RootPanel.get("KEY_MESSAGES").getWidget(0));
////		ADAMVisibilityController.styleVisibilityNoDisplay("KEY_MESSAGES_TABLE");
////		ADAMVisibilityController.styleVisibilityNoDisplay("KEY_MESSAGES");
////	}
//	
//	
//
//
//	public static void removeQuestions() {
//		ADAMVisibilityController.styleVisibilityNoDisplay("QUESTIONS");
//		for (int i = 11 ; i < 11 + QUESTIONS ; i++) {
//			if (RootPanel.get("QUESTION_" + i).getWidgetCount() > 0)
//				RootPanel.get("QUESTION_" + i).remove(RootPanel.get("QUESTION_" + i).getWidget(0));
//			RootPanel.get("QUESTION_" + i).setStyleName("");
//			ADAMVisibilityController.styleVisibilityNoDisplay("QUESTION_" + i);
//		}
//	}
//
//	public static void removeFilters() {
////		if (RootPanel.get("KEY_MESSAGES").getWidgetCount() > 0)
////			RootPanel.get("KEY_MESSAGES").remove(RootPanel.get("KEY_MESSAGES").getWidget(0));
////		RootPanel.get("KEY_MESSAGES").setStyleName("");
//		if (RootPanel.get("COUNTRY_SELECTOR").getWidgetCount() > 0)
//			RootPanel.get("COUNTRY_SELECTOR").remove(RootPanel.get("COUNTRY_SELECTOR").getWidget(0));
////		RootPanel.get("COUNTRY_SELECTOR").setStyleName("");
//		if (RootPanel.get("DONOR_SELECTOR").getWidgetCount() > 0)
//			RootPanel.get("DONOR_SELECTOR").remove(RootPanel.get("DONOR_SELECTOR").getWidget(0));
////		RootPanel.get("DONOR_SELECTOR").setStyleName("");
//		if (RootPanel.get("SECTOR_SELECTOR").getWidgetCount() > 0)
//			RootPanel.get("SECTOR_SELECTOR").remove(RootPanel.get("SECTOR_SELECTOR").getWidget(0));
////		RootPanel.get("SECTOR_SELECTOR").setStyleName("");
////		if (RootPanel.get("REFERENCE_PERIOD").getWidgetCount() > 0)
////			RootPanel.get("REFERENCE_PERIOD").remove(RootPanel.get("REFERENCE_PERIOD").getWidget(0));
////		RootPanel.get("REFERENCE_PERIOD").setStyleName("");
//		
//		ADAMVisibilityController.removeKeyMessagesVisiblity();
//
//	}
//	
//	
//	public static void removeReferencePeriodFilter() {
//		if (RootPanel.get("REFERENCE_PERIOD").getWidgetCount() > 0)
//			RootPanel.get("REFERENCE_PERIOD").remove(RootPanel.get("REFERENCE_PERIOD").getWidget(0));
//		RootPanel.get("REFERENCE_PERIOD").setStyleName("");
//		
//	}
//	
//	public static void removeAggregationColumn() {	
//		if (RootPanel.get("AGGREGATION_COLUMN").getWidgetCount() > 0)
//			RootPanel.get("AGGREGATION_COLUMN").remove(RootPanel.get("AGGREGATION_COLUMN").getWidget(0));
//		RootPanel.get("AGGREGATION_COLUMN").setStyleName("");
//		
//	}
//
//	public static void removeMultiSelections() {
//		if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
//			RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
//		RootPanel.get("MULTISELECTION").setStyleName("");
//		
//		ADAMVisibilityController.styleVisibilityNoDisplay("MULTISELECTION_TABLE");
//		ADAMVisibilityController.styleVisibilityNoDisplay("MULTISELECTION");
//	}
//
//	
//	
//
//
//	public static void restoreQuestionsContent() {
//		for (String position : questionsMap.keySet()) {
//
//			ADAMVisibilityController.styleVisibilityDisplay(position);
//			
//			ADAMResultVO vo = questionsMap.get(position);
//			ADAMBoxContent c = ADAMBoxContent.valueOf(vo.getOutputType());
//			int questionIDX = Integer.parseInt( position.substring(1 + position.indexOf('_')));
//			switch (c) {
//				case BAR:
//					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionChartPanel((questionIDX % QUESTIONS), vo, fullScreenQuestionsChart(position, vo.getBoxColor(), vo.getTitle())));
//				break;
//				case PIE:
//					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionChartPanel((questionIDX % QUESTIONS), vo, fullScreenQuestionsChart(position, vo.getBoxColor(), vo.getTitle())));
//				break;
//				case TOP_COUNTRIES_BY_DONOR:
//					VerticalPanel topCPanel = ADAMTableMaker.buildSmallQuestionTable(vo);
//					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, vo, ADAMTableController.fullScreenQuestionTable(position, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), vo.getTitle()), topCPanel));
//				break;
//				case TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE:
//					VerticalPanel cp = ADAMTableMaker.buildSmallQuestionTable(vo);
//					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, vo, ADAMTableController.fullScreenQuestionTable(position, ADAMColorConstants.color.get(ADAMBoxContent.SECTOR), vo.getTitle()), cp));
//				break;
//				case FAVOURITE_PURPOSES_QUESTIONS_VIEW:
////					ContentPanel cp = ADAMTableMaker.buildFavouritePurposesQuestionsViewGrid(vo.getTableContents(), "335px", "175px", true);
////					RootPanel.get(position).add(ADAMQuestionMaker.buildQuestionTablePanel(questionIDX, ADAMTableController.fullScreenFavouritePurposesTable(position, vo.getBoxColor(), vo.getTitle()), cp));
//				break;
//			}
//			RootPanel.get(position).setStyleName("question-" + vo.getBoxColor() + "-box border content");
//		}
//	}
//
//
//	public static SelectionListener<MenuEvent> exportExcelTable(final ADAMResultVO vo, final Boolean isAgroupedTable) {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
//
//				exportExcelTableAgent(vo, isAgroupedTable);
//
//			}
//		};
//	}
//	
//	public static SelectionListener<ButtonEvent> exportExcelTableButton(final ADAMResultVO vo, final Boolean isAgroupedTable) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//
//				exportExcelTableAgent(vo, isAgroupedTable);
//
//			}
//		};
//	}
//	
//	
//
//	private static void exportExcelTableAgent(final ADAMResultVO vo, Boolean isAgroupedTable) {
//		System.out.println("vo.getTableHeaders(): " + vo.getTableHeaders() );
//		ADAMServiceEntry.getInstance().exportExcelTable(vo.getTitle(), vo.getTableHeaders(), vo.getTableContents(), isAgroupedTable, new AsyncCallback<String>() {
//
//			public void onSuccess(String result) {
//				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//			}
//
//			public void onFailure(Throwable arg0) {
//
//			}
//		});
//	}
//	
//	public static SelectionListener<MenuEvent> exportChartExcel(final ADAMResultVO vo) {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
//				exportChartExcelAgent(vo);
//			}
//		};
//	}
//	
//	private static void exportChartExcelAgent(final ADAMResultVO vo) {
//		List<List<String>> table = new ArrayList<List<String>>();
//		LinkedHashMap<String, Map<String, Double>> chartValues = vo.getChartValues();
//		
//		System.out.println("chartValues" + chartValues);
//		
//		// TODO: FIX FOR THE SERIES this is just without the series...
//		for(String key : chartValues.keySet()) {
//			for(String chartKey : chartValues.get(key).keySet()) {
//				List<String> row = new ArrayList<String>();
//				row.add(chartKey);
//				row.add(chartValues.get(key).get(chartKey).toString());
//				table.add(row);
//			}
//		}
//		
//		ADAMServiceEntry.getInstance().exportExcelTable("", new ArrayList<String>(), table, true, new AsyncCallback<String>() {
//			public void onSuccess(String result) {
//				com.google.gwt.user.client.Window.open("/fenix-web/exportObject/" + result, "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//			}
//			
//			public void onFailure(Throwable arg0) {
//			
//			}
//			
//		});
//	}
//	
//	public static SelectionListener<MenuEvent> exportChartImage(final ADAMResultVO vo) {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
//
//				exportChartImageAgent(vo);
//
//			}
//		};
//	}
//	
//	private static void exportChartImageAgent(final ADAMResultVO vo) {
//		com.google.gwt.user.client.Window.open("../adamObjects/" + vo.getBigImagePath(), "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//	}
//	
//	private static void exportCurrentViewAgent(final ADAMResultVO vo) {
//		RootPanel.get("CENTER").setStyleName("remove-box");
//
//		System.out.println("MAP IS USED: " + containsMaps);
//
// 		for(String position : currentUsedObjects.keySet() ) {
// 			ADAMVisibilityController.styleVisibilityDisplay(position);
// 			
// 			Boolean putObject = true;
//			System.out.println("restoreBoxesContent: " + position);
//			if ( containsMaps ) {
//				if ( position.equals("BOTTOM_LEFT") || position.equals("BOTTOM_RIGHT"))
//					putObject = false;
//			}
//			else {
//				if ( position.equals("MAP_LEFT") || position.equals("MAP_RIGHT"))
//					putObject = false;
//			}
//			if ( putObject ) {
//				ADAMBoxContent type = currentUsedObjects.get(position);
//				switch(type) {
//					case TABLE:
//						ADAMResultVO tableVo = tableMap.get(position);
//						exportExcelTable(tableVo, true);
//					break;
//
//					case CHART:
//						ADAMResultVO chartVo = chartMap.get(position);
//						exportChartImage(chartVo);
//					break;
//
//					case MAP:
//						/** TODO: CHANGE IT **/
////						ADAMResultVO mapVo = mapMap.get(position);
//						ADAMMapWrapper adamMapWrapper = ADAM.getAdamWrapper().getAdamMapsWrapper().get("global");
//						if ( adamMapWrapper != null ) {
//							exportMapAsZipAgent(adamMapWrapper, "EN");
//						}
//					break;
//
//					case VENN_PRIORITIES:
//						System.out.println("restoreBoxesContent vennMap: " + position);
//						ADAMResultVO vennVo = vennMap.get(position);
//						RootPanel.get(position).setStyleName("medium-" + ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES) + "-box border content");
//						RootPanel.get(position).add(ADAMVennMaker.buildSmallPriorityVenn(vennVo.getTitle(), vennVo.getSmallImagePath(), ADAMConstants.SMALL_WIDTH, ADAMConstants.SMALL_HEIGHT, vennVo, position, ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES), ADAMVennController.fullScreenVenn(position, ADAMColorConstants.color.get(ADAMBoxContent.VENN_PRIORITIES), vennVo.getTitle())));
//					break;
//				}
//			}
//
//		}
//	}
//	
//
//	/**** cleaning function **/
//
//	public static void cleanLayersAndViews() {
//		if ( ADAMWrapper.getDatasetProjectionViews() != null) {
//			System.out.println("cleaning projections and layers: " + ADAMWrapper.getDatasetProjectionViews());
//			ADAMServiceEntry.getInstance().cleanLayersAndViews(ADAMWrapper.getDatasetProjectionViews(), new AsyncCallback() {
//				public void onFailure(Throwable arg0) {
//				}
//				public void onSuccess(Object arg0) {
//				}
//			});
//			for ( String key : ADAMWrapper.getDatasetProjectionViews().keySet()) {
//				System.out.println("depublish layer");
//				MapServiceEntry.getInstance().depublishLayer(ADAMWrapper.getDatasetProjectionViews().get(key), new AsyncCallback() {
//					public void onFailure(Throwable arg0) {
//						System.out.println("depublished layer");
//					}
//					public void onSuccess(Object arg0) {
//					}
//				});			
//				System.out.println("depublish style");
//				MapServiceEntry.getInstance().depublishStyle(ADAMWrapper.getDatasetProjectionViews().get(key), new AsyncCallback() {
//					public void onFailure(Throwable arg0) {
//						System.out.println("depublished style");
//					}
//					public void onSuccess(Object arg0) {
//					}
//				});
//			}
//		}
//
//	}
//
//	public static SelectionListener<IconButtonEvent> addGaulMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
//		return new SelectionListener<IconButtonEvent>() {
//			public void componentSelected(IconButtonEvent ce) {
//				addGaulMultiselectionAgent(adamComboMultiSelection);
//			}
//		};
//	}
//	
//	public static void addGaulMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {
//		ADAMVisibilityController.restoreMultiselectionVisibility();
//		RootPanel.get("MULTISELECTION").setStyleName("multiselection-country-box border display content");
//		
//		if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
//			RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
//	
//
//		ADAMServiceEntry.getInstance().getCountriesList("CRSRecipients", new AsyncCallback<LinkedHashMap<CodeVo,LinkedHashMap<CodeVo,List<CodeVo>>>>() {
//			public void onSuccess(LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> result) {
//
//				TreeStore treeStore = adamComboMultiSelection.gaulTreeStore;
//				treeStore.removeAll();
//
////				System.out.println("\n\nPRINT");
//				for(CodeVo topcodeVo : result.keySet()) {
//					ListItemTree topItem = createTreeItem("Gaul_" + topcodeVo.getCode(), topcodeVo.getLabel(), "0");
//					treeStore.add(topItem, false);
////					System.out.println("- " + topcodeVo.getLabel());
//
//					for(CodeVo subCodeVo : result.get(topcodeVo).keySet()) {
////						System.out.println("-- " + subCodeVo.getLabel());
//						ListItemTree subItem = createTreeItem("Gaul_" + subCodeVo.getCode(), subCodeVo.getLabel(), "1");
//						treeStore.add(topItem, subItem, false);
//
//						if ( result.get(topcodeVo).containsKey(subCodeVo)) {
//							for(CodeVo subSubCodeVo : result.get(topcodeVo).get(subCodeVo)) {
////								System.out.println("--- " + subSubCodeVo.getLabel());
//								ListItemTree subSubItem = createTreeItem("Gaul_" + subSubCodeVo.getCode(), subSubCodeVo.getLabel(), "1");
//								treeStore.add(subItem, subSubItem, false);
//							
//							}
//						}
//					}
//				}
////				adamComboMultiSelection.gaulTreePanel.addCheckListener(checkedItem());
//				
//				 // change in node check state  
//				VerticalPanel infoPanel = new VerticalPanel();
//				infoPanel.setHeight(150);
//				infoPanel.setWidth(250);
//				infoPanel.setScrollMode(Scroll.AUTO);
//				
//				adamComboMultiSelection.gaulTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.gaulTreePanel));
//				
//				checkCountryValues(ADAMBoxMaker.countriesSelected, adamComboMultiSelection.gaulTreePanel, infoPanel);
//
//				RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildGaulRegions(infoPanel));
//				adamComboMultiSelection.panel.layout();
//
////				System.out.println("END PRINT");
//
//			}
//
//
//			public void onFailure(Throwable arg0) {
//			}
//		});
//	}
//	
//	
//	public static void addGaulMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection, final HorizontalPanel panel) {
//
//		ADAMServiceEntry.getInstance().getCountriesList("CRSRecipients", new AsyncCallback<LinkedHashMap<CodeVo,LinkedHashMap<CodeVo,List<CodeVo>>>>() {
//			public void onSuccess(LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> result) {
//
//				TreeStore treeStore = adamComboMultiSelection.gaulTreeStore;
//				treeStore.removeAll();
//
////				System.out.println("\n\nPRINT");
//				for(CodeVo topcodeVo : result.keySet()) {
//					ListItemTree topItem = createTreeItem("Gaul_" + topcodeVo.getCode(), topcodeVo.getLabel(), "0");
//					treeStore.add(topItem, false);
////					System.out.println("- " + topcodeVo.getLabel());
//
//					for(CodeVo subCodeVo : result.get(topcodeVo).keySet()) {
////						System.out.println("-- " + subCodeVo.getLabel());
//						ListItemTree subItem = createTreeItem("Gaul_" + subCodeVo.getCode(), subCodeVo.getLabel(), "1");
//						treeStore.add(topItem, subItem, false);
//
//						if ( result.get(topcodeVo).containsKey(subCodeVo)) {
//							for(CodeVo subSubCodeVo : result.get(topcodeVo).get(subCodeVo)) {
////								System.out.println("--- " + subSubCodeVo.getLabel());
//								ListItemTree subSubItem = createTreeItem("Gaul_" + subSubCodeVo.getCode(), subSubCodeVo.getLabel(), "1");
//								treeStore.add(subItem, subSubItem, false);
//							
//							}
//						}
//					}
//				}
////				adamComboMultiSelection.gaulTreePanel.addCheckListener(checkedItem());
//				
//				 // change in node check state  
//				VerticalPanel infoPanel = new VerticalPanel();
//				infoPanel.setHeight(150);
//				infoPanel.setWidth(250);
//				infoPanel.setScrollMode(Scroll.AUTO);
//				
//				adamComboMultiSelection.gaulTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.gaulTreePanel));
//				
//				checkCountryValues(ADAMBoxMaker.countriesSelected, adamComboMultiSelection.gaulTreePanel, infoPanel);
//
//				panel.add(adamComboMultiSelection.buildGaulRegions(infoPanel));
//				panel.layout();
//
////				System.out.println("END PRINT");
//
//			}
//
//
//			public void onFailure(Throwable arg0) {
//			}
//		});
//	}
//	
//	
//	private static Listener<TreePanelEvent<ListItemTree>> createPanel(final VerticalPanel infoPanel, final TreePanel<ListItemTree> treePanel) {
//		return new Listener<TreePanelEvent<ListItemTree>>() {
//			public void handleEvent(TreePanelEvent<ListItemTree> be) {  
//				infoPanel.removeAll();
//					List<ListItemTree> items = treePanel.getCheckedSelection();
//					for (ListItemTree itemSelected : items) {
//						if (treePanel.isLeaf(itemSelected)) {
//							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
//						}
//					}
//				infoPanel.layout();
//		 	} 
//		 };
//	}
//	
//
//	public static SelectionListener<IconButtonEvent> addDonorMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
//		return new SelectionListener<IconButtonEvent>() {
//			public void componentSelected(IconButtonEvent ce) {
//				addDonorMultiselectionAgent(adamComboMultiSelection);
//			}
//		};
//	}
//	
//	public static void addDonorMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {
//		ADAMVisibilityController.restoreMultiselectionVisibility();
//		if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
//			RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
//		RootPanel.get("MULTISELECTION").setStyleName("multiselection-donor-box border content");
//
//		ADAMServiceEntry.getInstance().findAll("Donor_", new AsyncCallback<List<CodeVo>>() {
//			public void onSuccess(List<CodeVo> vos) {
//
//				adamComboMultiSelection.donorTreeStore.removeAll();
//
//				for (CodeVo vo : vos) {
//					ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
//					adamComboMultiSelection.donorTreeStore.add(treeItem, false);
//
//				}
//				
//				 // change in node check state  
//				VerticalPanel infoPanel = new VerticalPanel();
//				infoPanel.setHeight(150);
//				infoPanel.setWidth(250);
//				infoPanel.setScrollMode(Scroll.AUTO);
//				
//				adamComboMultiSelection.donorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.donorTreePanel));
//
//				checkValues(ADAMBoxMaker.donorsSelected, adamComboMultiSelection.donorTreePanel, infoPanel);
//
//				RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildDonorPanel(infoPanel));
//				adamComboMultiSelection.panel.layout();
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//
//	}
//
//
//	public static SelectionListener<IconButtonEvent> addSectorMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
//		return new SelectionListener<IconButtonEvent>() {
//			public void componentSelected(IconButtonEvent ce) {
//				ADAMVisibilityController.restoreMultiselectionVisibility();
//				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
//					RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
//				RootPanel.get("MULTISELECTION").setStyleName("multiselection-sector-box border content");
//
//				ADAMServiceEntry.getInstance().findAll("Dac_", new AsyncCallback<List<CodeVo>>() {
//					public void onSuccess(List<CodeVo> vos) {
//
//						adamComboMultiSelection.sectorTreeStore.removeAll();
//
//						for (CodeVo vo : vos) {
//							List<ListItem> values = new ArrayList<ListItem>();
//
//
//							ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
//							adamComboMultiSelection.sectorTreeStore.add(treeItem, false);
//
//							
//						}
//						
//						 // change in node check state  
//						VerticalPanel infoPanel = new VerticalPanel();
//						infoPanel.setHeight(150);
//						infoPanel.setWidth(250);
//						infoPanel.setScrollMode(Scroll.AUTO);
//						
//						adamComboMultiSelection.sectorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.sectorTreePanel));
//
//
//						checkValues(ADAMBoxMaker.sectorsSelected, adamComboMultiSelection.sectorTreePanel, infoPanel);
//
//						RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildSectorPanel(infoPanel));
//						adamComboMultiSelection.panel.layout();
//
//					}
//					public void onFailure(Throwable e) {
//						FenixAlert.error(BabelFish.print().error(), e.getMessage());
//					}
//				});
//
//			}
//		};
//	}
//	
//	
//	private static void checkValues(final Map<String, String> map,  final TreePanel<ListItemTree> treePanel,  final VerticalPanel infoPanel) {
//		TreeStore<ListItemTree> items = treePanel.getStore();
//		if( map != null ) {
//			for (String code : map.keySet()) {
//				for (ListItemTree itemSelected : items.getAllItems()) {
//					if( code.equalsIgnoreCase(itemSelected.getCode())) {
//						treePanel.setChecked(itemSelected, true);
//						if (treePanel.isLeaf(itemSelected)) {
//							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
//						}
//						break;
//					}
//
//				}
//			}
//		}
//	}
//
//	private static void checkCountryValues(final Map<String, String> map,  final TreePanel<ListItemTree> treePanel, final VerticalPanel infoPanel) {
//		TreeStore<ListItemTree> items = treePanel.getStore();
//		if( map != null ) {
//			for (String code : map.keySet()) {
//				for (ListItemTree itemSelected : items.getAllItems()) {
////					System.out.println("----> " + code +" | "+ itemSelected.getCode());
//					if( itemSelected.getCode().equalsIgnoreCase(code)) {
////						System.out.println("equal " + code +" | "+ itemSelected.getCode());
//						treePanel.setChecked(itemSelected, true);					
//						
//						if (treePanel.isLeaf(itemSelected)) {
//							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
//						}
//						break;
//					}
//					
//
//				}
//			}
//		}
//	}
//
//	public static SelectionListener<ButtonEvent> closeMultiselection() {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//
//				removeMultiSelections();
//			}
//		};
//	}
//	public static SelectionListener<ButtonEvent> applyMultiselection(final TreePanel<ListItemTree> treePanel, final Map<String, String> map, final String selection) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//
//				map.clear();
//				List<ListItemTree> items = treePanel.getCheckedSelection();
//				for (ListItemTree itemSelected : items) {
//					if (treePanel.isLeaf(itemSelected)) {
//						String code = itemSelected.getCode();
//						String label = itemSelected.getLabel();
//						map.put(code, label);
//					}
//				}
//
//				if ( selection.equals("COUNTRY") ) {
//					setMultiSelectionListBox(ADAMBoxMaker.getGaulStore(), ADAMBoxMaker.getGaulList());
//				}
//				
//				else if ( selection.equals("DONOR") ) {
//					setMultiSelectionListBox(ADAMBoxMaker.getDonorStore(), ADAMBoxMaker.getDonorList());
//				}
//				
//				else if ( selection.equals("SECTOR") ) {
//					setMultiSelectionListBox(ADAMBoxMaker.getSectorStore(), ADAMBoxMaker.getSectorList());
//				}
//	
//
//				System.out.println("map: " + map);
//
//				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, currentVIEW);
//				removeMultiSelections();
//			}
//		};
//	}
//
//	
//	private static void setMultiSelectionListBox(ListStore<GaulModelData> store,  ComboBox<GaulModelData> list) {
//		list.disableEvents(true);
//		for(GaulModelData c : store.getModels() ){
//			if ( c.getGaulCode().equalsIgnoreCase("MULTI")) {
//				list.setValue(c);
//				break;
//			} 
//				
//		}
//		list.disableEvents(false);
//	
//	}
//
//
//	public static SelectionListener<ButtonEvent> applyCountryMultiselection(final TreePanel<ListItemTree> treePanel, final Map<String, String> map) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//
//				map.clear();
//				List<ListItemTree> items = treePanel.getCheckedSelection();
//				for (ListItemTree itemSelected : items) {
//					if (treePanel.isLeaf(itemSelected)) {
//						String code = itemSelected.getCode();
//						String label = itemSelected.getLabel();
//						map.put(code, label);
//					}
//				}
//
//				System.out.println("map: " + map);
//
//				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMCurrentVIEW.ADAMVIEW);
//				removeMultiSelections();
//			}
//		};
//	}
//
//
//
//
//
//	private static ListItemTree createTreeItem(String code, String fullLabel, String level) {
//		String reducedLabel = "";
//		if (fullLabel.length() > 25)
//			reducedLabel = fullLabel.substring(0, 24) + "...";
//		else
//			reducedLabel = fullLabel;
//
//		ListItemTree item = new ListItemTree(code, fullLabel);
//		item.setShortLabel(reducedLabel);
//		item.setLevel(level);
//
//		return item;
//	}
//
//	
//	
//	public static SelectionListener<ButtonEvent> refreshShowProjectsButtonEvent(final ADAMShowProjectsPanel adamShowProjectsPanel) {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				refreshShowProjectsAgent(adamShowProjectsPanel);
//			}
//		};
//	}
//	
//	public static void refreshShowProjectsAgent(ADAMShowProjectsPanel adamShowProjectsPanel) {
//		
//		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
//		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
//
//		Map<String, String> sectorList = new HashMap<String, String>();
//		Map<String, String> purposesList = new HashMap<String, String>();
//	
//		Html q = new Html("<div align='center' style='font-size: 20px; color: #224466;'>Loading... <IMG SRC='adam-images/loading.gif'></div>");
//
//
//		adamShowProjectsPanel.getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(q.getHtml());
//		adamShowProjectsPanel.getAdamShowProjectsTable().getSummaryHtml().setHtml("");
//
//		adamShowProjectsPanel.getPanel().layout();
//		
//
//		// check which checkbox is selected
//		if (adamShowProjectsPanel.getAdamShowProjectsFilters().getFaoViewCheckBox().getValue()) {
//			purposesList = ADAMConstants.faoViewPurposes;
//		}
//		else if ( adamShowProjectsPanel.getAdamShowProjectsFilters().getSectorsViewCheckBox().getValue()) {
//			TreePanel treePanel = adamShowProjectsPanel.getAdamShowProjectsFilters().getSector().getMultiSelection().treePanel;
//			if ( treePanel.getStore().getAllItems().size() != treePanel.getCheckedSelection().size()){
//				sectorList = getSelectionAgent(treePanel);
//			}
//		}
//		
//		refreshProjectsAgent(adamShowProjectsPanel, donorList, gaulList, sectorList, purposesList);
//		
//		
//	}
//	public static Map<String, String> getSelectionAgent(final TreePanel<ListItemTree> treePanel) {
//		Map<String, String> map = new HashMap<String, String>();
//		List<ListItemTree> items = treePanel.getCheckedSelection();
//		for (ListItemTree itemSelected : items) {
//			String code = itemSelected.getCode();
//			String label = itemSelected.getLabel();
//			map.put(code, label);
//		}
//		return map;
//	}
//	
//	private static void refreshProjectsAgent(ADAMShowProjectsPanel adamShowProjectsPanel, Map<String, String> donorList, Map<String, String> gaulList, Map<String, String> sectorList, Map<String, String> purposesList) {
//
//		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//
//		Boolean countryAdded = addCountryFilter(filters, gaulList);
//		System.out.println("countryAdded: " + countryAdded);
//		Boolean donorAdded = addDonorFilter(filters, donorList);
//		System.out.println("donorAdded: " + donorAdded);
//		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
//		System.out.println("sectorAdded: " + sectorAdded);
//		Boolean purposeAdded =  addPurposeCodesFilter(filters, purposesList);
//		System.out.println("purposesAdded: " + purposeAdded);
//		
//		if( !countryAdded && !donorAdded && !sectorAdded) {
//			/*** global view **/
//			// do nothing
//			ADAMViewMenu.hideWaitingPanel();
//			FenixAlert.info("List of Projects", "The query returns too many projects");
//		}
//		else {
//			System.out.println("RefreshProjects PROJECTS");
//			refreshProjectsAgentView(adamShowProjectsPanel, filters);
//		}
//		
//	}
//	
//
//	
//	private static void refreshProjectsAgentView(final ADAMShowProjectsPanel adamShowProjectsPanel, final HashMap<String, List<String>> filters) {
//		ADAMQueryVO qSize = ADAMQueryVOBuilder.getRowsCount(baseDate, filters);
//		ADAMServiceEntry.getInstance().askADAM(qSize, new AsyncCallback<ADAMResultVO>() {
//			public void onSuccess(ADAMResultVO vo) {
//				System.out.println("vo size: " + vo.getRows());
//				
//				if ( vo.getRows() < 15000 ) {
//					final Long rows = vo.getRows();
//					ADAMQueryVO q = ADAMQueryVOBuilder.getProjects(baseDate, filters);
//					ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
//						public void onSuccess(ADAMResultVO vo) {
//
//							
//							ADAMVisibilityController.restoreProjectsVisibility();
//							
//							adamShowProjectsPanel.getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(vo.getTableHTML());
//							adamShowProjectsPanel.getAdamShowProjectsTable().getSummaryHtml().setHtml("<div class='content'><b>Total Number of Projects: " + rows + "</b><div>");
//
//							adamShowProjectsPanel.getPanel().layout();
//							
//							// reload export listener	
//							adamShowProjectsPanel.getToolbar().getExcel().removeAllListeners();
//							adamShowProjectsPanel.getToolbar().getExcel().addSelectionListener(ADAMController_GWTSplit.exportExcelTable(vo, false));
//						}
//						public void onFailure(Throwable e) {
//							ADAMViewMenu.hideWaitingPanel();
//							FenixAlert.error(BabelFish.print().error(), e.getMessage());
//						}
//					});
//				}
//				else {
//					ADAMViewMenu.hideWaitingPanel();
//					FenixAlert.info("List of Projects", "The query returns too many projects: " + vo.getRows());
//				}
//				
//			}
//			public void onFailure(Throwable e) {
//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
//			}
//		});
//	}
//
//	
//	public static SelectionListener<MenuEvent> showProjects() {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
//				showProjectsFAOView();
//			}
//		};
//	}
//	
//	public static SelectionListener<ButtonEvent> showProjectsFAOButtonEvent() {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				showProjectsFAOView();
//			}
//		};
//	}
//	
//	private static void showProjectsFAOView() {
//		ADAMViewMenu.showWaitingPanel("Getting Projects");
//		
//		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
//		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
////		Map<String, String> sectorList = ADAMBoxMaker.sectorsSelected;
//		
//		
//	
//		// ADDING THE FAO FILTERS TO THE BASE CALL
//		Map<String, String> sectorList = new HashMap<String, String>();
////		for ( String key : ADAMConstants.faoViewSectors.keySet())
////			sectorList.put(key, ADAMConstants.faoViewSectors.get(key));
//		
//		Map<String, String> purposesList = ADAMConstants.faoViewPurposes;
//	
//
//		
//		showProjectsAgent(donorList, gaulList, sectorList, purposesList);
//	}
//	
//	
//	
//	private static void showProjectsAgent(Map<String, String> donorList, Map<String, String> gaulList, Map<String, String> sectorList, Map<String, String> purposesList) {
//		
//		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//
//		Boolean countryAdded = addCountryFilter(filters, gaulList);
//		System.out.println("countryAdded: " + countryAdded);
//		Boolean donorAdded = addDonorFilter(filters, donorList);
//		System.out.println("donorAdded: " + donorAdded);
//		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
//		System.out.println("sectorAdded: " + sectorAdded);
//		Boolean purposeAdded =  addPurposeCodesFilter(filters, purposesList);
//		System.out.println("purposesAdded: " + purposeAdded);
//		
//		if( !countryAdded && !donorAdded && !sectorAdded) {
//			/*** global view **/
//			// do nothing
//			ADAMViewMenu.hideWaitingPanel();
//			FenixAlert.info("List of Projects", "The query returns too many projects");
//		}
//		else {
//			System.out.println("SHOWING PROJECTS");
//			showProjectsAgentView(filters);
//		}
//		
//	}
//	
//
//	
//	private static void showProjectsAgentView(final HashMap<String, List<String>> filters) {
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
//							ADAMVisibilityController.restoreProjectsVisibility();
//								
//							ADAMShowProjectsPanel adamShowProjectsPanel = new ADAMShowProjectsPanel();
//							adamShowProjectsPanel.build(vo);
//							adamShowProjectsPanel.getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(vo.getTableHTML());
//							adamShowProjectsPanel.getAdamShowProjectsTable().getSummaryHtml().setHtml("<div class='content'><b>Total Number of Projects: " + rows + "</b><div>");
//								
//							adamShowProjectsPanel.getPanel().layout();
//							
//							if (RootPanel.get("PROJECTS_VIEW").getWidgetCount() > 0)
//								RootPanel.get("PROJECTS_VIEW").remove(RootPanel.get("PROJECTS_VIEW").getWidget(0));
//							RootPanel.get("PROJECTS_VIEW").setStyleName("big-blue-box border content");
//							RootPanel.get("PROJECTS_VIEW").add(adamShowProjectsPanel.getPanel());
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
////	private static void showProjectsAgent(Map<String, String> donorList, Map<String, String> gaulList, Map<String, String> sectorList) {
////
////		
////		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
////
////		Boolean countryAdded = addCountryFilter(filters, gaulList);
////		System.out.println("countryAdded: " + countryAdded);
////		Boolean donorAdded = addDonorFilter(filters, donorList);
////		System.out.println("donorAdded: " + donorAdded);
////		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
////		System.out.println("sectorAdded: " + sectorAdded);
////		
////		if( !countryAdded && !donorAdded && !sectorAdded) {
////			/*** global view **/
////			// do nothing
////			ADAMViewMenu.hideWaitingPanel();
////			FenixAlert.info("List of Projects", "The query returns too many projects");
////		}
////		else {
////			ADAMQueryVO qSize = ADAMQueryVOBuilder.getRowsCount(baseDate, filters);
////			ADAMServiceEntry.getInstance().askADAM(qSize, new AsyncCallback<ADAMResultVO>() {
////				public void onSuccess(ADAMResultVO vo) {
////					System.out.println("vo size: " + vo.getRows());
////					
////					if ( vo.getRows() < 15000 ) {
////						final Long rows = vo.getRows();
////						ADAMQueryVO q = ADAMQueryVOBuilder.getProjects(baseDate, filters);
////						ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
////							public void onSuccess(ADAMResultVO vo) {
////								ADAMViewMenu.hideWaitingPanel();
////								
////								Html table = new Html();
////								table.setHtml(vo.getTableHTML());
////								
////								Window window = new Window();
////								window.add(table);
////								window.setHeaderVisible(true);
////								window.setHeading("Total Number of Projects: " + rows);
////								
////								window.setHeight(640);
////								window.setWidth(900);
////								window.show();
////								window.setScrollMode(Scroll.AUTO);
////								
////							}
////							public void onFailure(Throwable e) {
////								ADAMViewMenu.hideWaitingPanel();
////								FenixAlert.error(BabelFish.print().error(), e.getMessage());
////							}
////						});
////					}
////					else {
////						ADAMViewMenu.hideWaitingPanel();
////						FenixAlert.info("List of Projects", "The query returns too many projects: " + vo.getRows());
////					}
////					
////				}
////				public void onFailure(Throwable e) {
////					FenixAlert.error(BabelFish.print().error(), e.getMessage());
////				}
////			});
////		}
////		
////	}
//	
//	
//	
//	public static SelectionListener<IconButtonEvent> showInfo(final String text) {
//		return new SelectionListener<IconButtonEvent>() {
//			public void componentSelected(IconButtonEvent ce) {
//				Html html = new Html();
//				if ( text != null )
//					html.setHtml(text);
//				else 
//					html.setHtml("No additional Info");
//				
//				ContentPanel panel = new ContentPanel();
//				VerticalPanel v = new VerticalPanel();
//				panel.setHeaderVisible(false);
//				panel.setHeight(250);
//				panel.setWidth(400);
//				panel.setScrollMode(Scroll.AUTO);
//				v.add(html);
//				panel.add(v);
//				
//				
//				Window window = new Window();
//				
//			
//				window.add(panel);
//				window.setHeaderVisible(true);
//				window.setHeading("Info");
//				
//				window.setHeight(250);
//				window.setWidth(400);
//				window.show();
////				window.setScrollMode(Scroll.AUTO);
//			}
//		};
//	}
//
//	public static SelectionListener<MenuEvent>  changeTitleDescription(final ADAMResultVO vo) {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
//				changeTitleDescriptionAgent("", vo.getTitle(), vo.getDescription());
//
//			}
//		};
//	}
//	
//	public static void changeTitleDescriptionAgent(String position, String title, String description) {
//		ADAMChangeInformation adam = new ADAMChangeInformation();
//		adam.build(title, description);
//	}
//	
//	
//	public static SelectionListener<ButtonEvent> createFAOView() {
//		return new SelectionListener<ButtonEvent>() {
//			public void componentSelected(ButtonEvent ce) {
//				createFAOViewAgent(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected);
//			}
//		};
//	}
//	
//	public static void createFAOViewAgent(final Map<String, String> donorList, final Map<String, String> gaulList, final Map<String, String> sectorList) {
//		System.out.println("createFAOViewAgent");
//
//		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
//
//		Boolean countryAdded = addCountryFilter(filters, gaulList);
//		System.out.println("countryAdded: " + countryAdded);
//		Boolean donorAdded = addDonorFilter(filters, donorList);
//		System.out.println("donorAdded: " + donorAdded);
//		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
//		System.out.println("sectorAdded: " + sectorAdded);
//
//		System.out.println("filters: " + filters);
//		
//		// ADDING FAO FILTER
//
//		addFAOFilter(filters);
//
//		ADAMVisibilityController.removeFAOVisibility();
//		ADAMVisibilityController.restoreFAOVisibility();
//			
//			
//		if( donorAdded && !countryAdded && !sectorAdded) {
//			createDonorFAOView(filters);
//		}
//		else if( countryAdded && !donorAdded && !sectorAdded) {
//			createFAOCountryView(filters);
//		}
//	
//		else if( countryAdded && donorAdded && !sectorAdded) {
//			createCountryDonorFAOView(filters);
//		}
//	
//		else if( !countryAdded && !donorAdded && !sectorAdded) {
//			createGlobalFAOView(filters);
//		}
//			
//			// restore custom
////			restoreCustomContent();
//		} 
//	
//	
//
//	private static void createDonorFAOView(HashMap<String, List<String>> filters ){
//	
//		System.out.println("createDonorFAOView objectWindowId: " + objectWindowId );
//	
//		ADAMController_GWTSplit.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewMajorRecipients("Major Recipients (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//		
//		ADAMController_GWTSplit.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false), objectWindowId);
//	}
//	
//	private static void createFAOCountryView(HashMap<String, List<String>> filters ){
//		
//		System.out.println("createFAOCountryView objectWindowId: " + objectWindowId );
//	
//		ADAMController_GWTSplit.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewMajorDonors("Major Donors (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//	
//		ADAMController_GWTSplit.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false), objectWindowId);
//	}
//	
//	private static void createCountryDonorFAOView(HashMap<String, List<String>> filters ){
//		
//		System.out.println("createCountryDonorFAOView objectWindowId: " + objectWindowId );
//	
//
////		ADAMTableController.addTable("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewCountriesAndSectors("Major Recipients/Sectors (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO)), objectWindowId);
//		
//		ADAMController_GWTSplit.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false), objectWindowId);
//	}
//	
//	private static void createGlobalFAOView(HashMap<String, List<String>> filters ){
//		
//		System.out.println("createCountryDonorFAOView objectWindowId: " + objectWindowId );
//	
//	
////		ADAMController.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), ADAMQueryVOBuilder.viewMajorRecipients("Major Recipients (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT)), objectWindowId);
//		
//		ADAMController_GWTSplit.addChart("FAO_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewMajorDonors("Major Donors (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel()), objectWindowId);
//		
//		ADAMController_GWTSplit.addChart("FAO_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), ADAMQueryVOBuilder.viewPurposes(ADAMBoxContent.PIE.name(), "Most financed Sectors (FAO)", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.FAO), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false), objectWindowId);
//
//	}
//	
//	private static void addFAOFilter(HashMap<String, List<String>> filters) {
//		List<String> faoFilter = new ArrayList<String>();
//		faoFilter.add("41301");
//		filters.put("channelcode",faoFilter );
//	}
//
//	
//
//	public static SelectionListener<MenuEvent>  switchResource(final String position, final String color, final ADAMQueryVO q) {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
////				objectWindowId = objectWindow.getNext();3
//			
//				System.out.println("q.getOutputType(): " + q.getResourceType());
//				ADAMBoxContent type = ADAMBoxContent.valueOf(q.getResourceType());
//				System.out.println("TYPE: " + type);
//				switch(type) {
//				case TABLE:
//					ADAMTableController.addTable(position, color, q, objectWindowId);
//				break;
//
//				case CHART:
//					ADAMController_GWTSplit.addChart(position, color, q, objectWindowId);
//				break;
//				}
//				
//			}
//		};
//	}
//	
//	public static void showHomePage() {
//		ADAMVisibilityController.restoreAdamViewVisibility();
//		
//		RootPanel.get("CENTER").setStyleName("big-green-box border content");	 
//		VerticalPanel panel = new VerticalPanel();
//		panel.setHorizontalAlign(HorizontalAlignment.CENTER);
//		panel.add(new Html("<font size='4px'><b>A</b>griculture <b>D</b>evelopment <b>A</b>ssistance <b>M</b>apping System</font>"));
//		RootPanel.get("CENTER").add(panel);
//	}

}