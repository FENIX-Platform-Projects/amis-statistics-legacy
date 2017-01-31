package org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize;


import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic.FAOSTATVisualizeByTopic;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic.FAOSTATVisualizeTopicsPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;



public class FAOSTATVisualizeByTopicController extends FAOSTATVisualizeController {
	

	public static void createTopics(final FAOSTATVisualizeByTopic visualization) {

		/** TODO: get VOs **/
		try{
	    	 DataViewerServiceEntry.getInstance().getFAOSTATQueryVOs(FAOSTATCurrentView.VISUALIZE_BY_TOPIC, "QUESTIONS", FAOSTATConstants.faostatLanguage, new AsyncCallback<FAOSTATVisualizeSettingsVO>() {
	    		 
					
					public void onSuccess(FAOSTATVisualizeSettingsVO vo) {
						
						
						List<FAOSTATVisualizeQuestionsVO> questions = vo.getQuestions();
						
						VerticalPanel p = new VerticalPanel();
						
						int i= 1;
						for(FAOSTATVisualizeQuestionsVO question : questions ) {
							
							System.out.println("--> " + question.getTitle());
							FAOSTATVisualizeTopicsPanel topic = new FAOSTATVisualizeTopicsPanel();
							p.add(topic.build(String.valueOf(i), question));
							
							p.add(DataViewerClientUtils.addVSpace(15));
							i++;
						} 
						
					
						visualization.getContainer().add(p);
						
						visualization.getContainer().layout();
					
					
					}
					public void onFailure(Throwable arg0) {
						
					}
				});
	    	 
	    	 //testFilters(visualization, visualization.getSettings(), model);				
			 //testGetVOS(visualization.center, visualization.getSettings(), model);	
	    	 }
	    	 catch (Exception e) {
	 			e.printStackTrace();
	 		}
		
	}
	
	
	
	
	public static Listener<ComponentEvent> buildViews(final FAOSTATVisualizeTopicsPanel topic) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				
				if ( !topic.getIsOpen() ) {
					/** TODO: change is in a nicer way...the set style seems to not work**/
					topic.getTitleHtml().setHtml("<div class='visualize_topic_title_selected'> " + topic.getQuestion().getTitle().toUpperCase() + "</div>");
//					topic.getIcon().setStyleName("arrow-up_disabled");
					topic.setIsOpen(true);
				
					// check if VOs are already built
					
					if ( topic.getQvos().isEmpty() ) {
						try{
					    	 DataViewerServiceEntry.getInstance().getFAOSTATQueryVOs(FAOSTATCurrentView.VISUALIZE_BY_TOPIC, topic.getQuestion().getFilename(), FAOSTATConstants.faostatLanguage, new AsyncCallback<FAOSTATVisualizeSettingsVO>() {
					    		 
									
									public void onSuccess(FAOSTATVisualizeSettingsVO vo) {
	
										// set the qvos	
										List<DWFAOSTATQueryVO> qvos = vo.getQvos().get("DEFAULT");
			
										// TODO: change the default key and default parameters
										/** TODO: think about setting the default parameters on server side? **/
										for(DWFAOSTATQueryVO qvo : qvos) {
											FAOSTATConstants.setFAOSTATDBSettings(qvo);
										}
										topic.setQvos(qvos);
									
										// GOOGLE stuff
										String googleCategory = FAOSTATCurrentView.VISUALIZE_BY_TOPIC.toString();
										String googleLabel = "n.a.";
										String googleAction = "Visualize by topic";
										
										FAOSTATVisualizeByDomainController.buildViews(topic.getContent(), topic.getQvos(), FAOSTATVisualizeByDomainConstants.CENTER_PANEL_WIDTH, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, FAOSTATVisualizeByDomainConstants.CENTER_PANEL_HORIZONTAL_SPACING, googleCategory, googleLabel, googleAction);

										topic.getContent().show();
										topic.getContent().layout();

									}
									public void onFailure(Throwable arg0) {
										
									}
								});

					    	 }
					    	 catch (Exception e) {
					 			e.printStackTrace();
					 		}
					}
					else {
						// the qvos are already been read from the xml build the view (TODO: don't call again the database...show the panel)
//						buildViewsAgent(topic.getContent(), topic.getQvos());
						topic.getContent().show();
					}
					
					
				}
				else {
					topic.getTitleHtml().setHtml("<div class='visualize_topic_title'> " + topic.getQuestion().getTitle() + "</div>");
//					topic.getIcon().setStyleName("arrow-down_disabled");
					topic.getContent().hide();
					topic.setIsOpen(false);
				}
			    
			}
		};
	}
	
	

	private static HorizontalPanel addHSpace(Integer width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(width);
		return p;
	}
	
	private static HorizontalPanel addVSpace(Integer height) {
		HorizontalPanel p = new HorizontalPanel();
		p.setHeight(height);
		return p;
	}

}
