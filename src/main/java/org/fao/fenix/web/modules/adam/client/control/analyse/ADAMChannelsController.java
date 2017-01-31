package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMTableController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class ADAMChannelsController extends ADAMController {
	
	static boolean addRemoveTableIcon = true;	
	static SelectionListener<ButtonEvent> removeListener = removeImplementingAgencies();	
			
	public static void createChannelsViewAgent() {
		 if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)){
			 ADAMChannelsDACController.createChannelsView();	
		 } 	 
		 else if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW)){
			 ADAMChannelsFAOController.createChannelsFAOView();			 
		 }
	}
	
	public static void globalAgenciesView() {
		//objectWindowId = objectWindow.getNext();
	//	currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners";
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		
		// channels/recipients
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "recipientcode";
		String secondColumnLabel2 = "recipientname";
		String title2 = "Top 30 Agencies (by Budget) & Recipient Countries";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");
	}
	
	public static ADAMQueryVO setImplementingAgenciesDescription(ADAMQueryVO q){

		String description = "" +
		"<div style='padding:20px'>" +
		"<p>Below is an outline of the various channels of delivery as reported by resource partners to the OECD-DAC. Please note that 'Unspecified' means that the resource partner did not report the channel of delivery to the OECD-DAC.</p>" +
		"<p>" +
		"    <img src='adam-docs/OECD-DAC_ImplementingAgencies.jpg' border='0'/>" +
		"</p><br/>" +
		"</div>";

		q.setDescription(description);

		return q;
	}

	   
/*
	public static SelectionListener<ButtonEvent> createChannelsView(final Button button) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				//System.out.println("ADAMVisibilityController.getVisibility(CHANNELS_TABLE): " + ADAMVisibilityController.getVisibility("CHANNELS_TABLE"));
				
				if (ADAMVisibilityController.getVisibility("CHANNELS_TABLE").equals("true")) {
					button.setText("Show Agencies...");
					ADAMVisibilityController.removeChannelsVisibility();
				}
				else {
					button.setText("Hide Agencies...");
					createChannelsViewAgent();
				}

			}
		};
	}
	
	public static SelectionListener<MenuEvent> createChannelsView(final MenuItem button) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				
				//System.out.println("ADAMVisibilityController.getVisibility(CHANNELS_TABLE): " + ADAMVisibilityController.getVisibility("CHANNELS_TABLE"));
				
				//if (ADAMVisibilityController.getVisibility("CHANNELS_TABLE").equals("true")) {
					//button.setText("Add Implementation Agencies (OECD/DAC)");
					//ADAMVisibilityController.removeChannelsVisibility();
				//}
				//else {
					//button.setText("Remove Implementation Agencies (OECD/DAC)");
				    createChannelsViewAgent();
				//}

			}
		};
	}
	*/
	public static SelectionListener<ButtonEvent> removeImplementingAgencies() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVisibilityController.removeChannelsVisibility();
			}
		};
	}
	
	
}
