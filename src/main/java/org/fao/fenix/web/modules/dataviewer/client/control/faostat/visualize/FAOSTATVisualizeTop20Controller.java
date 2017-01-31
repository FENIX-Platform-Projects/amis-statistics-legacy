package org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic.FAOSTATVisualizeByTopic;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic.FAOSTATVisualizeTopicsPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.top20.FAOSTATVisualizeTop20;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsInfoVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeTop20Constants;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.rpc.AsyncCallback;



public class FAOSTATVisualizeTop20Controller extends FAOSTATVisualizeController {

	public static void getVOs(final FAOSTATVisualizeTop20 visualization) {
//		System.out.println("FAOSTATVisualizeTop20Controller getVOs");
		try{
	    	 DataViewerServiceEntry.getInstance().getFAOSTATQueryVOs(FAOSTATCurrentView.VISUALIZE_TOP_20, "QUESTIONS", FAOSTATConstants.faostatLanguage, new AsyncCallback<FAOSTATVisualizeSettingsVO>() {
					public void onSuccess(FAOSTATVisualizeSettingsVO vo) {
						
						List<FAOSTATVisualizeQuestionsVO> questions = vo.getQuestions();
						
						VerticalPanel p = new VerticalPanel();
	
						TreeStore<DWCodesModelData> treeStore = visualization.getTopics().getStore();
						TreePanel<DWCodesModelData> treePanel = visualization.getTopics().getTree();
						
						treePanel.disableEvents(true);
						
						treeStore.removeAll();
						visualization.getInfoVO().clear();
						
						DWCodesModelData firstQuestion = null;
						for(FAOSTATVisualizeQuestionsVO question : questions ) {						
//							System.out.println("--> " + question.getTitle());
							String questionCode = question.getFilename();
							String questionLabel = question.getTitle();
//							System.out.println("questionCode: " + questionCode);
//							System.out.println("questionLabel: " + questionLabel);
							if ( questionCode == null) {
								questionCode = questionLabel;
							}
							DWCodesModelData questionMD = new DWCodesModelData(questionCode, questionLabel);
							
							if ( firstQuestion == null ) {
								firstQuestion = questionMD;
							}
							
							treeStore.add(questionMD, true);
							
							// if there are subsets of information 
							if ( !question.getInfoVO().isEmpty() ) {
								List<DWCodesModelData> infosMD = new ArrayList<DWCodesModelData>();
								
								for(String key : question.getInfoVO().keySet()) {
									
									// filename (the one in the linkedHashMap
									String infoCode = question.getInfoVO().get(key).getFilename();
									String infoLabel = question.getInfoVO().get(key).getTitle();
									DWCodesModelData infoMD = new DWCodesModelData(infoCode, infoLabel);
									
//									System.out.println("infoCode: " + infoCode);
//									System.out.println("infoLabel: " + infoLabel);
									
									infosMD.add(infoMD);
									
									visualization.getInfoVO().put(infoCode, question.getInfoVO().get(key));
								}
								treeStore.add(questionMD, infosMD, true);
							}
							p.add(DataViewerClientUtils.addVSpace(15));
						} 

						visualization.getTopics().getPanel().layout();
						visualization.getTopics().getTree().expandAll();
						
						// default
						DWCodesModelData code = treeStore.getChild(firstQuestion, 0);
						treePanel.getSelectionModel().select(code, true);
						FAOSTATVisualizeQuestionsInfoVO infoVO = visualization.getInfoVO().get(code.getCode());
//						System.out.println("infoVO: " + infoVO.toString());

						if ( infoVO != null ) {
							buildViewsAgent(visualization, infoVO);
						}
						
						treePanel.enableEvents(true);
					}
					public void onFailure(Throwable arg0) {
						
					}
				});
	    	 }
	    	 catch (Exception e) {
	 			e.printStackTrace();
	 		}
		
	}

	
	public static Listener<ComponentEvent> buildViews(final FAOSTATVisualizeTop20 visualize, final FAOSTATVisualizeQuestionsInfoVO infoVO) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				buildViewsAgent(visualize, infoVO);
			}
		};
	}
	
	public static void buildViewsAgent(final FAOSTATVisualizeTop20 visualization, final FAOSTATVisualizeQuestionsInfoVO infoVO) {
		try {
			DataViewerServiceEntry.getInstance().getFAOSTATQueryVOs(FAOSTATCurrentView.VISUALIZE_TOP_20, infoVO.getFilename(), FAOSTATConstants.faostatLanguage, new AsyncCallback<FAOSTATVisualizeSettingsVO>() {
						public void onSuccess(FAOSTATVisualizeSettingsVO vo) {
							
							// GOOGLE ANALYTICS				
							visualization.setGoogleLabel(infoVO.getFilename());
							visualization.setGoogleAction("Visualize Rankings");
							

							List<DWFAOSTATQueryVO> qvos = vo.getQvos().get("DEFAULT");
							List<FAOSTATVisualizeFilter> filters = vo.getFilters();

							for (DWFAOSTATQueryVO qvo : qvos) {
								FAOSTATConstants.setFAOSTATDBSettings(qvo);
							}

							visualization.getSettings().setFilters(filters);
							visualization.getSettings().setQvos(vo.getQvos());

							// title
							buildTitle(visualization.getTitlePanel(), vo.getViewTitle());

							// parameters
							visualization.getParametersPanel().build(true);
							visualization.getParametersPanel().getPanel().layout();

							// filters
							// TODO: issue with the China....
//							FAOSTATVisualizeByDomainController.buildFilters(visualization, filters, new DWCodesModelData("QC", "QC"));
							FAOSTATVisualizeByDomainController.buildFilters(visualization, filters, null);

							
							// views
							FAOSTATVisualizeByDomainController.buildViews(visualization.getCenterPanel(), qvos, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_WIDTH, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, visualization.getGoogleCategory(), visualization.getGoogleLabel(), visualization.getGoogleAction());

							// checks the asyn calss
//							checkIfAllTheAsyncCallsAreReturned(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());

						}

						public void onFailure(Throwable arg0) {

						}
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
