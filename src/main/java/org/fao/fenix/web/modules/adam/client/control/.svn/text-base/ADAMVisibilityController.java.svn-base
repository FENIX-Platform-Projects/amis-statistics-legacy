package org.fao.fenix.web.modules.adam.client.control;

import org.fao.fenix.web.modules.adam.client.view.ADAMDataSourceSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMQuantityColumn;
import org.fao.fenix.web.modules.adam.client.view.ADAMSwitchClassificationPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.google.gwt.user.client.ui.RootPanel;

public class ADAMVisibilityController extends ADAMController {

	
	public static void setDefaultNoDisplayObjects() {
		System.out.println("setDefaultNoDisplayObjects()");
		for (int i=1; i < QUESTIONS; i++) {
			//System.out.println("QUESTION_1" + i);
			styleVisibilityNoDisplay("QUESTION_1" + i);
		}
		for (int i=0; i < CUSTOMS; i++) {
			System.out.println("CUSTOM_1" + i);
			styleVisibilityNoDisplay("CUSTOM_1" + i);
		}
//		styleVisibility("VIEWMENU");
		styleVisibilityNoDisplay("MULTISELECTION_TABLE");
		styleVisibilityNoDisplay("CUSTOM");
		styleVisibilityNoDisplay("CUSTOM_TABLE");
		styleVisibilityNoDisplay("CUSTOM_VIEW_TABLE");
		//styleVisibilityNoDisplay("QUESTION_TABLE");
		/*styleVisibilityNoDisplay("SUB_SELECTOR");
		styleVisibilityNoDisplay("VIEWMENU");
		styleVisibilityNoDisplay("PROJECTS");
		styleVisibilityNoDisplay("ADAM_VIEW_TABLE");*/

	
		
		removeFAOVisibility();
		removeChannelsVisibility();
		removeFAOComparativeAdvantageVisibility();
		
//		styleVisibility("TOP_LEFT");
//		styleVisibility("TOP_RIGHT");
		//		document.getElementById(d).style.display = "none";
	}
	
	
	
	 public static native String styleVisibilityNoDisplay(String id)/*-{
		var elem;
		elem = $doc.getElementById(id);
		if (elem != null) {       
//		  	alert(id + " | " + elem.style.display);

//		    elem.style.visibility = "hidden";
		    elem.style.display = "none";
		    return "none";
		        
		}
		else
		   return null;
		
		
  	}-*/;
	 
	 public static native String styleVisibilityDisplay(String id)/*-{
	     var elem;
	     elem = $doc.getElementById(id);
	     if (elem != null) {       
		//  alert(id + " | " + elem.style.display);
		 
//		    elem.style.visibility = "visible";
		    elem.style.display = "";
		    return "in-line";
		 }
	     else
	     	return null;
	
	
	}-*/; 
	 
	public static void restoreKeyMessagesVisiblity() {
		styleVisibilityDisplay("KEY_MESSAGES_TABLE");
//		styleVisibilityDisplay("KEY_MESSAGES");
		styleVisibilityDisplay("COUNTRY_SELECTOR");
		styleVisibilityDisplay("DONOR_SELECTOR");
		styleVisibilityDisplay("CLASSIFICATION");		
		styleVisibilityDisplay("SECTOR_SELECTOR");
		styleVisibilityDisplay("SUB_SECTOR_SELECTOR");
		styleVisibilityDisplay("AGGREGATION_COLUMN");
		styleVisibilityDisplay("REFERENCE_PERIOD_FROM");
		styleVisibilityDisplay("REFERENCE_PERIOD_TO");
	}
	
	public static void removeKeyMessagesVisiblity() {
		styleVisibilityNoDisplay("KEY_MESSAGES_TABLE");
//		styleVisibilityDisplay("KEY_MESSAGES");
		styleVisibilityNoDisplay("COUNTRY_SELECTOR");
		styleVisibilityNoDisplay("DONOR_SELECTOR");
		styleVisibilityNoDisplay("CLASSIFICATION");
		styleVisibilityNoDisplay("SECTOR_SELECTOR");
		styleVisibilityNoDisplay("SUB_SECTOR_SELECTOR");
		styleVisibilityNoDisplay("AGGREGATION_COLUMN");
		styleVisibilityNoDisplay("REFERENCE_PERIOD_FROM");
		styleVisibilityNoDisplay("REFERENCE_PERIOD_TO");
	}
	
	
	public static void removeAdamViewVisibility() {
		styleVisibilityNoDisplay("ADAM_VIEW_TABLE");
		styleVisibilityNoDisplay("CENTER");
		styleVisibilityNoDisplay("TOP_LEFT");
		styleVisibilityNoDisplay("TOP_RIGHT");
		styleVisibilityNoDisplay("BOTTOM_LEFT");
		styleVisibilityNoDisplay("BOTTOM_RIGHT");
//		styleVisibilityNoDisplay("MAP_LEFT");
		styleVisibilityNoDisplay("MAP_RIGHT");
		
		// TODO: check for customs?
		removeCustomVisibility(false);
		
		// remove home visibility
		removeHomeVisibility();
		
		// remove useful links visibility
		removeLinksVisibility();
		
		// remove FAO visibility objects
		removeFAOVisibility();
		
		// remove Channels visibility objects
		removeChannelsVisibility();
		
		// remove FAO Comparative Advantage visibility objects
		removeFAOComparativeAdvantageVisibility();
		
		//remove Analyse Data View Visibility
		removeAnalyseDataVisibility();
		
		//remove Analyse Data View Menu Visibility
		removeAnalyseViewMenuVisibility();
		
	} 
	
	public static void removeAnalyseDataVisibility() {
		
		styleVisibilityNoDisplay("ANALYSE_TITLE");
		styleVisibilityNoDisplay("ADAM_ANALYSE_TABLE");
	/*	styleVisibilityNoDisplay("CENTER");*/
		styleVisibilityNoDisplay("ANALYSE_TOP");
		styleVisibilityNoDisplay("ANALYSE_MIDDLE");
		styleVisibilityNoDisplay("ANALYSE_BOTTOM");
		/*styleVisibilityNoDisplay("ANALYSE_MIDDLE");
		styleVisibilityNoDisplay("ANALYSE_BOTTOM");
		styleVisibilityNoDisplay("ANALYSE_BOTTOM_LEFT");
		styleVisibilityNoDisplay("ANALYSE_BOTTOM_RIGHT");*/
		
		/// remove home visibility
		removeHomeVisibility();
		
		// remove other links visibility
		removeLinksVisibility();
		
		removeAnalyseViewMenuVisibility();
		
  	} 
	
	 
	public static void restoreCenterVisibility() {
		styleVisibilityDisplay("ADAM_VIEW_TABLE");
		styleVisibilityDisplay("CENTER");
	} 
	
	
	/*public static void restoreHomeViewVisibility() {
		styleVisibilityDisplay("HOME");	
	} */
	
	
	public static void restoreHomeViewVisibility() {
		styleVisibilityDisplay("ADAM_VIEW_TABLE");
		styleVisibilityNoDisplay("CENTER");
		styleVisibilityDisplay("TOP_LEFT");
		styleVisibilityDisplay("TOP_RIGHT");
		styleVisibilityDisplay("BOTTOM_LEFT");
		styleVisibilityDisplay("BOTTOM_RIGHT");
//		styleVisibilityDisplay("MAP_LEFT");
		styleVisibilityDisplay("MAP_RIGHT");
		
		// TODO: check for customs?
		restoreCustomVisibility(false);
		
		// TODO: menu view?
		//restoreViewMenuVisibility();
		
		//styleVisibilityNoDisplay("VIEWMENU_LEFT");
		styleVisibilityDisplay("VIEWMENU_LEFT"); //dataset holder always visible
		
		//styleVisibilityNoDisplay("SUB_SELECTOR");
		// TODO: remove ask adam visibility?
		//removeAskADAMVisibility();
		
		// TODO: show projects visibility?
		removeProjectsVisibility();
	} 
	
	
	public static void restoreAdamViewVisibility() {
		styleVisibilityDisplay("ADAM_VIEW_TABLE");
		styleVisibilityDisplay("CENTER");
		styleVisibilityDisplay("TOP_LEFT");
		styleVisibilityDisplay("TOP_RIGHT");
		styleVisibilityDisplay("BOTTOM_LEFT");
		styleVisibilityDisplay("BOTTOM_RIGHT");
		styleVisibilityDisplay("MAP_LEFT");
		styleVisibilityDisplay("MAP_RIGHT");
		
		// TODO: check for customs?
		restoreCustomVisibility(false);
			
		// TODO: menu view?
		restoreViewMenuVisibility();
		
		// TODO: remove ask adam visibility?
		//removeAskADAMVisibility();
		
		// TODO: show projects visibility?
		removeProjectsVisibility();
		
		
		removeAnalyseViewMenuVisibility();
	} 
	
	
	public static void restoreAnalyseDataViewVisibility() {
		removeAdamViewVisibility();
		//removeAskADAMVisibility();
		removeProjectsVisibility();	
		
		
		styleVisibilityDisplay("ANALYSE_TITLE");
		styleVisibilityDisplay("ADAM_ANALYSE_TABLE");
		/*styleVisibilityDisplay("CENTER");*/
		styleVisibilityDisplay("ANALYSE_TOP");
		styleVisibilityDisplay("ANALYSE_MIDDLE");
		styleVisibilityDisplay("ANALYSE_BOTTOM");
		/*styleVisibilityDisplay("ANALYSE_MIDDLE");
		styleVisibilityDisplay("ANALYSE_BOTTOM");
		styleVisibilityDisplay("ANALYSE_BOTTOM_LEFT");
		styleVisibilityDisplay("ANALYSE_BOTTOM_RIGHT");*/
		
		styleVisibilityDisplay("ADAM_VIEW_TABLE");
		styleVisibilityDisplay("CENTER");
		styleVisibilityDisplay("TOP_LEFT");
		styleVisibilityDisplay("TOP_RIGHT");
		styleVisibilityDisplay("BOTTOM_LEFT");
		styleVisibilityDisplay("BOTTOM_RIGHT");
		styleVisibilityDisplay("MAP_LEFT");
		styleVisibilityDisplay("MAP_RIGHT");
		//styleVisibilityDisplay("SUB_SELECTOR"); // aggregation and timeperiod and datasource selectors container
		styleVisibilityDisplay("VIEWMENU_LEFT"); // datasource selector, always
		
	
		restoreAnalyseViewMenuVisibility();
	} 
		
	public static void restoreCustomVisibility(Boolean isForceTable) {
		if ( isForceTable ) {
			styleVisibilityDisplay("CUSTOM_TABLE");
			styleVisibilityDisplay("CUSTOM_VIEW_TABLE");
			styleVisibilityDisplay("CUSTOM");
			for(int i = 0; i < CUSTOMS; i ++ ) {
				styleVisibilityDisplay("CUSTOM_1" + i);
			}
		}
		else {	
			for(int i = 0; i < CUSTOMS; i ++ ) {
				//System.out.println("restoreCustomContent(): " + "CUSTOM_1" + i );
	
				if ( currentCustom.containsKey("CUSTOM_1" + i )) {
					styleVisibilityDisplay("CUSTOM_TABLE");
					styleVisibilityDisplay("CUSTOM_VIEW_TABLE");
					styleVisibilityDisplay("CUSTOM");
					styleVisibilityDisplay("CUSTOM_1" + i);
				
				}
			}
		}

	} 
	
	
	public static void removeCustomVisibility(Boolean isCustom) {
		if ( isCustom) {
			Boolean closeAll = true;
			for(int i = 0; i < CUSTOMS; i ++ ) {
				if ( !currentCustom.containsKey("CUSTOM_1" + i )) {
					styleVisibilityNoDisplay("CUSTOM_1" + i);
				}
				else
					closeAll = false;
			}
			if ( closeAll ) {
				styleVisibilityNoDisplay("CUSTOM_TABLE");
				styleVisibilityNoDisplay("CUSTOM_VIEW_TABLE");
			}	
			styleVisibilityNoDisplay("CUSTOM");
		}
		else {
			styleVisibilityNoDisplay("CUSTOM_TABLE");
			styleVisibilityNoDisplay("CUSTOM_VIEW_TABLE");
			styleVisibilityNoDisplay("CUSTOM");
			
			for(int i = 0; i < CUSTOMS; i ++ ) {
				styleVisibilityNoDisplay("CUSTOM_1" + i);
			}
		}
	} 
	
	public static void removeCustomBoxContent() {
		if (RootPanel.get("CUSTOM").getWidgetCount() > 0)
			RootPanel.get("CUSTOM").remove(RootPanel.get("CUSTOM").getWidget(0));
	} 
	
	
	//ANALYSE VIEW MENU
	public static void removeAnalyseViewMenuContent() {
		if (RootPanel.get("ANALYSE_VIEWMENU_TITLE").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_VIEWMENU_TITLE").remove(RootPanel.get("ANALYSE_VIEWMENU_TITLE").getWidget(0));
		if (RootPanel.get("ANALYSE_VIEWMENU_CONTENT").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_VIEWMENU_CONTENT").remove(RootPanel.get("ANALYSE_VIEWMENU_CONTENT").getWidget(0));
		if (RootPanel.get("ANALYSE_VIEWMENU").getWidgetCount() > 0)
			RootPanel.get("ANALYSE_VIEWMENU").remove(RootPanel.get("ANALYSE_VIEWMENU").getWidget(0));


		styleVisibilityNoDisplay("ANALYSE_VIEWMENU");

	} 

	public static void restoreAnalyseViewMenuVisibility() {
		styleVisibilityDisplay("ANALYSE_VIEWMENU");
		styleVisibilityDisplay("ANALYSE_VIEWMENU_TITLE");
		styleVisibilityDisplay("ANALYSE_VIEWMENU_CONTENT");
	} 

	public static void removeAnalyseViewMenuVisibility() {
		styleVisibilityNoDisplay("ANALYSE_VIEWMENU");
		styleVisibilityNoDisplay("ANALYSE_VIEWMENU_TITLE");
		styleVisibilityNoDisplay("ANALYSE_VIEWMENU_CONTENT");
	}
	
		// VIEW MENU
	public static void removeViewMenuContent() {
		
	if (RootPanel.get("VIEWMENU_TITLE").getWidgetCount() > 0)
			RootPanel.get("VIEWMENU_TITLE").remove(RootPanel.get("VIEWMENU_TITLE").getWidget(0));
	if (RootPanel.get("VIEWMENU_CONTENT").getWidgetCount() > 0)
			RootPanel.get("VIEWMENU_CONTENT").remove(RootPanel.get("VIEWMENU_CONTENT").getWidget(0));
		if (RootPanel.get("VIEWMENU_LEFT").getWidgetCount() > 0)
			RootPanel.get("VIEWMENU_LEFT").remove(RootPanel.get("VIEWMENU_LEFT").getWidget(0));
		if (RootPanel.get("VIEWMENU_RIGHT").getWidgetCount() > 0)
			RootPanel.get("VIEWMENU_RIGHT").remove(RootPanel.get("VIEWMENU_RIGHT").getWidget(0));

		if (RootPanel.get("VIEWMENU").getWidgetCount() > 0)
			RootPanel.get("VIEWMENU").remove(RootPanel.get("VIEWMENU").getWidget(0));
	
		
		styleVisibilityNoDisplay("VIEWMENU");

	} 
	
	public static void restoreViewMenuVisibility() {
		styleVisibilityDisplay("VIEWMENU");
		styleVisibilityDisplay("VIEWMENU_TITLE");
		styleVisibilityDisplay("VIEWMENU_CONTENT");
		//styleVisibilityDisplay("VIEWMENU_LEFT");
		styleVisibilityDisplay("VIEWMENU_RIGHT");
		//styleVisibilityDisplay("SUB_SELECTOR");
				
	} 
	
	public static void removeHomeVisibility() {
		styleVisibilityNoDisplay("HOMEVIEW");
		styleVisibilityNoDisplay("HOME");
	}
	
	public static void restoreHomeVisibility() {
		styleVisibilityDisplay("HOMEVIEW");
		styleVisibilityDisplay("HOME");
	} 
	
	public static void restoreLinksVisibility() {
		styleVisibilityDisplay("LINKSVIEW");
		styleVisibilityDisplay("LINKS");
	} 
	
	public static void removeLinksVisibility() {
		styleVisibilityNoDisplay("LINKSVIEW");
		styleVisibilityNoDisplay("LINKS");
	}
	
	public static void removeViewMenuVisibility() {
		styleVisibilityNoDisplay("VIEWMENU");
		styleVisibilityNoDisplay("VIEWMENU_TITLE");
		//styleVisibilityNoDisplay("VIEWMENU_LEFT");
		styleVisibilityNoDisplay("VIEWMENU_RIGHT");
		//styleVisibilityNoDisplay("SUB_SELECTOR");
	}
	
	
	// MULTISELCTION  
	
	public static void restoreMultiselectionVisibility() {
		styleVisibilityDisplay("MULTISELECTION_TABLE");
		styleVisibilityDisplay("MULTISELECTION");
	} 
	
	public static void removeMultiselectionVisibility() {
		styleVisibilityNoDisplay("MULTISELECTION_TABLE");
		styleVisibilityNoDisplay("MULTISELECTION");
	} 
	
	// ASK ADAM  
	 
	/*public static void restoreAskADAMVisibility() {
		styleVisibilityDisplay("QUESTION_TABLE");
		for (int i=1; i <= QUESTIONS; i++) {
			//System.out.println("QUESTION_1" + i);
			styleVisibilityDisplay("QUESTION_1" + i);
		}
	} */
	
	/*public static void removeAskADAMVisibility() {
		styleVisibilityNoDisplay("QUESTION_TABLE");
		for (int i=1; i <= QUESTIONS; i++) {
			//System.out.println("QUESTION_1" + i);
			styleVisibilityNoDisplay("QUESTION_1" + i);
		}
	} */
	
	
	
	// FAO VIEW
	public static void restoreFAOVisibility() {
		styleVisibilityDisplay("FAO_TABLE");
		
		for(int i = 0; i < FAO; i ++ ) {
			styleVisibilityDisplay("FAO_1" + i);
		}

	} 
	
	public static void removeFAOVisibility() {
		styleVisibilityNoDisplay("FAO_TABLE");
		for(int i = 0; i < FAO; i ++ ) {
			//System.out.println("removeFAOContent(): " + "FAO_1" + i );
			styleVisibilityNoDisplay("FAO_1" + i);
		}
		
		for(int i = 0; i < FAO; i ++ ) {
			currentUsedObjects.remove("FAO_1" + i );
		}
		
	} 
	
	// Channels VIEW
	public static void restoreChannelsVisibility() {
		styleVisibilityDisplay("CHANNELS_TABLE");
		
		for(int i = 0; i < CHANNELS; i ++ ) {
			styleVisibilityDisplay("CHANNELS_1" + i);
		}

	} 
	
	public static void removeChannelsVisibility() {
		styleVisibilityNoDisplay("CHANNELS_TABLE");
		for(int i = 0; i < CHANNELS; i ++ ) {
			//System.out.println("removeFAOContent(): " + "CHANNELS_1" + i );
			styleVisibilityNoDisplay("CHANNELS_1" + i);
		}
		
		for(int i = 0; i < CHANNELS; i ++ ) {
			currentUsedObjects.remove("CHANNELS_1" + i );
		}
		
	} 
	
	// FAO Comparative Advantage VIEW	
	public static void restoreFAOComparativeAdvantageVisibility() {
		styleVisibilityDisplay("COMPARATIVE_ADVANTAGE_TABLE");
		
		for(int i = 0; i < COMPARATIVE_ADVANTAGE; i ++ ) {
			styleVisibilityDisplay("COMPARATIVE_ADVANTAGE_1" + i);
		}

	} 
	
	
	public static void removeFAOComparativeAdvantageVisibility() {
		styleVisibilityNoDisplay("COMPARATIVE_ADVANTAGE_TABLE");
		for(int i = 0; i < COMPARATIVE_ADVANTAGE; i ++ ) {
			//System.out.println("removeFAOContent(): " + "COMPARATIVE_ADVANTAGE_1" + i );
			styleVisibilityNoDisplay("COMPARATIVE_ADVANTAGE_1" + i);
		}
		
		for(int i = 0; i < COMPARATIVE_ADVANTAGE; i ++ ) {
			currentUsedObjects.remove("COMPARATIVE_ADVANTAGE_1" + i );
		}
		
	} 
	
	
	// PROJECTS VIEW
	public static void restoreProjectsVisibility() {
		styleVisibilityDisplay("PROJECTS_TABLE");
		styleVisibilityDisplay("PROJECTS_TITLE");
		styleVisibilityDisplay("PROJECTS_FILTERS");
		styleVisibilityDisplay("PROJECTS_VIEW");
		styleVisibilityDisplay("PROJECTS");
	} 
	
	public static void removeProjectsVisibility() {
		styleVisibilityNoDisplay("PROJECTS_TABLE");
		styleVisibilityNoDisplay("PROJECTS_TITLE");
		styleVisibilityNoDisplay("PROJECTS_FILTERS");
		styleVisibilityNoDisplay("PROJECTS_VIEW");
		styleVisibilityNoDisplay("PROJECTS");

	} 
	
	
	 public static native String getVisibility(String id)/*-{
		var elem;
		elem = $doc.getElementById(id);
		if (elem != null) {
// 			alert(id + " | " + elem.style.display);
 			var displayStatus = elem.style.display;
 			
 			if ( displayStatus == "none" ) {
 				return "false";
 			}
 			else
 				return "true";
 			
		    return elem.style.display;
		        
		}
		else
		   return "false";

	}-*/;
	 
	 
	  public static void enableAllSelectors(){
		  
			System.out.println("**** enableAllSelectors: currentVIEW = "+currentVIEW);
			
			
			if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
				ADAMBoxMaker.sectorList.enable();
				ADAMBoxMaker.subSectorList.enable();
			}	
			else if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)){
				ADAMBoxMaker.soList.enable();
				ADAMBoxMaker.orList.enable();
			}

			ADAMBoxMaker.donorList.enable();	

			if(ADAMQuantityColumn.list!=null)
				ADAMQuantityColumn.list.enable();
			
			if(ADAMDataSourceSelection.datasetCombo!=null)
				ADAMDataSourceSelection.datasetCombo.enable();
			
			if(ADAMSwitchClassificationPanel.classificationCheckBox!=null) {
				System.out.println("**** enableAllSelectors: ADAMSwitchSelectionPanel.classificationCheckBox isEnabled = "+ADAMSwitchClassificationPanel.classificationCheckBox.isEnabled());
				
				ADAMSwitchClassificationPanel.classificationCheckBox.enable();
			}
			
		}
}
