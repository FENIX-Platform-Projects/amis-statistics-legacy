package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMTableController;
import org.fao.fenix.web.modules.adam.client.control.ADAMViewController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

public class ADAMChannelsDACController extends ADAMChannelsController {
	
	public static void createChannelsView() {
		
		//ADAMVisibilityController.restoreChannelsVisibility();
		
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		Map<String, String> sectorList = ADAMBoxMaker.sectorsSelected;
		Map<String, String> subSectorList = ADAMBoxMaker.subSectorsSelected;
		Map<String, String> channelList = ADAMBoxMaker.channelsSelected;
			
		Boolean countryAdded = addCountryFilter(filters, gaulList);
		//System.out.println("countryAdded: " + countryAdded);
		Boolean donorAdded = addDonorFilter(filters, donorList);
		//System.out.println("donorAdded: " + donorAdded);
		Boolean sectorAdded =  addSectorFilter(filters, sectorList);
		//System.out.println("sectorAdded: " + sectorAdded);
		Boolean subSectorAdded =  addSubSectorFilter(filters, subSectorList);
		//System.out.println("subSectorAdded: " + subSectorAdded);
		
		Boolean channelAdded =  addChannelFilter(filters, channelList);
		//System.out.println("channelAdded: " + channelAdded);
		
		
		 /** donor **/
		 
		 if( donorAdded && !countryAdded && !sectorAdded && !subSectorAdded && !channelAdded) {
			 createDonorView(filters);
		 }

		 /** country **/
		 
		 else if( countryAdded && !donorAdded && !sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountryView(filters);
		 }
		 
		 /** country/donor **/

		 else if( countryAdded && donorAdded && !sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountryDonorView(filters);
		 }

		 /** sector **/
		 
		 else if ( !donorAdded && !countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createSectorView(filters, subSectorAdded);
		 }
		 
		 /** country/sector **/
		 
		 else if (!donorAdded && countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountrySectorView(filters, subSectorAdded);
		 }
		 
		 /** donor/sector **/
		 
		 else if (donorAdded && !countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createDonorSectorView(filters, subSectorAdded);
		 }
		 
		 /** Country/donor/sector **/
		 
		 else if (donorAdded && countryAdded && sectorAdded && !subSectorAdded && !channelAdded) {
			 createCountryDonorSectorView(filters, subSectorAdded);
		 }
		 
         /** sub-sector, some views same as Sector **/	 
		 else if ( !countryAdded && !donorAdded && !sectorAdded && subSectorAdded && !channelAdded) {
			// createSubSectorView(channelList, filters);
		 }
		 // country/subsector
		 else if ( countryAdded && !donorAdded && !sectorAdded && subSectorAdded && !channelAdded) {
			// createCountrySubSectorView(gaulList, channelList, filters);
		 }
		 // donor/subsector
		 else if ( !countryAdded && donorAdded && !sectorAdded && subSectorAdded && !channelAdded) {
			 //createDonorSubSectorView(gaulList, channelList, filters);
		 }
		// sector/subsector
		 else if ( !countryAdded && !donorAdded && sectorAdded && subSectorAdded && !channelAdded) {
			 createSectorView(filters, subSectorAdded);
		 }
		// country/donor/subsector
		 else if ( countryAdded && donorAdded && !sectorAdded && subSectorAdded && !channelAdded) {
			// createCountryDonorSubSectorView(gaulList, donorList, channelList, filters);
		 }
		// country/sector/subsector
		 else if ( countryAdded && !donorAdded && sectorAdded && subSectorAdded && !channelAdded) {
			 createCountrySectorView(filters, subSectorAdded);
		 }
		// donor/sector/subsector
		 else if ( !countryAdded && donorAdded && sectorAdded && subSectorAdded && !channelAdded) {
			 createDonorSectorView(filters, subSectorAdded);
		 }
		// country/donor/sector/subsector
		 else if ( countryAdded && donorAdded && sectorAdded && subSectorAdded && !channelAdded) {
			 createCountryDonorSectorView(filters, subSectorAdded);
		 }
			 
			 
		 ////////////////////////////////	
		 // channels (impleting agencies) **/

//		 else if ( !countryAdded && !donorAdded && !sectorAdded && channelAdded ) {
//			 createChannelView(channelList, filters);
//		 }
//		 // country/channels
//		 else if ( countryAdded && !donorAdded && !sectorAdded && channelAdded ) {
//			 createCountryChannelView(gaulList, channelList, filters);
//		 }
//		 // donor/channels
//		 else if ( !countryAdded && donorAdded && !sectorAdded && channelAdded ) {
//			 createDonorChannelView(gaulList, channelList, filters);
//		 }
//		// sector/channels
//		 else if ( !countryAdded && !donorAdded && sectorAdded && channelAdded ) {
//			 createSectorChannelView(gaulList, channelList, filters);
//		 }
//		// country/donor/channels
//		 else if ( countryAdded && donorAdded && !sectorAdded && channelAdded ) {
//			 createCountryDonorChannelView(gaulList, donorList, channelList, filters);
//		 }
//		// country/sector/channels
//		 else if ( countryAdded && !donorAdded && sectorAdded && channelAdded ) {
//			 createCountrySectorChannelView(gaulList, sectorList, channelList, filters);
//		 }
//		// donor/sector/channels
//		 else if ( !countryAdded && donorAdded && sectorAdded && channelAdded ) {
//			 createDonorSectorChannelView(donorList, sectorList, channelList, filters);
//		 }
//		// country/donor/sector/channels
//		 else if ( countryAdded && donorAdded && sectorAdded && channelAdded ) {
////			 createCountryDonorSectorChannelView(gaulList, channelList, filters);
//		 }
		 
		 

		 else if( !countryAdded && !donorAdded && !sectorAdded && !subSectorAdded && !channelAdded) {
			 /*** global view **/
			 globalAgenciesView();
		 }

		 // restore custom
		// restoreCustomContent();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/** TODO: CREATE SEPARATED CALLS FOR THE DIFFERENT VIEWS **/
		
		
		/// first
		
//		String firstColumnCode = "channelcode";
//		String firstColumnLabel = "channelname";
//		
//		String secondColumnCode = "donorcode";
//		String secondColumnLabel = "donorname";
//		String title1 = "";
//		
//		
//		
//		System.out.println("HERE");
//		
//		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
//		
//		ADAMTableController.addTable("CHANNELS_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId);
//
////		/// second
////		
////		String firstColumnCode2 = "channelcode";
////		String firstColumnLabel2 = "channelname";
////		
////		String secondColumnCode2 = "recipientcode";
////		String secondColumnLabel2 = "recipientname";
////		String title2 = "";
////		
////		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
////		
////		ADAMTableController.addTable("CHANNELS_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId);
//
//		
//		/// third
//		
////		String firstColumnCode3 = "channelcode";
////		String firstColumnLabel3 = "channelname";
////		
////		String secondColumnCode3 = "dac_sector";
////		String secondColumnLabel3 = "dac_label";
////		String title3 = "";
////		
////		ADAMQueryVO qvo3 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), firstColumnCode3, firstColumnLabel3,  secondColumnCode3, secondColumnLabel3, title3, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
////		
////		ADAMTableController.addTable("CHANNELS_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo3, objectWindowId);
//
//		
//		
////		/// fourth
////		
////		String firstColumnCode3 = "recipientcode";
////		String firstColumnLabel3 = "recipientname";
////		
////		String secondColumnCode3 = "dac_sector";
////		String secondColumnLabel3 = "dac_label";
////		String title3 = "";
////		
////		ADAMQueryVO qvo3 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), firstColumnCode3, firstColumnLabel3,  secondColumnCode3, secondColumnLabel3, title3, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
////		
////		ADAMTableController.addTable("CHANNELS_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo3, objectWindowId);
//		
//		
////		/// fifth
////		
//		String firstColumnCode3 = "channelcode";
//		String firstColumnLabel3 = "channelname";
//		
//		String secondColumnCode3 = "purposecode";
//		String secondColumnLabel3 = "purposename";
//		String title3 = "";
//		
//		ADAMQueryVO qvo3 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), firstColumnCode3, firstColumnLabel3,  secondColumnCode3, secondColumnLabel3, title3, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
//		
//		ADAMTableController.addTable("CHANNELS_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo3, objectWindowId);
		
	}
	
	
	
	private static void createDonorView(HashMap<String, List<String>> filters ){

		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		
		// channels/recipient
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "recipientcode";
		String secondColumnLabel = "recipientname";
		String title1 = "Top 30 Agencies (by Budget) & Recipient Countries for the Selected Resource Partners";
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMViewController.setAggregatedViewName(topDonorsChart, false);
		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Sectors for the Selected Resource Partners";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
 


	private static void createCountryView(HashMap<String, List<String>> filters ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected Recipient Countries";
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel() + ")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Sectors for the Selected Recipient Countries";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	
	private static void createCountryDonorView(HashMap<String, List<String>> filters ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected Recipient Countries";
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Sectors for the Selected Recipient Countries and Resource Partners";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	
	private static void createSectorView(HashMap<String, List<String>> filters, boolean subSectorAdded  ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

   
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected ";
		
		if(subSectorAdded)
			title1 += "Sub-Sectors";
		else
			title1 += "Sectors";
				
		
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		// channels/recipients
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "recipientcode";
		String secondColumnLabel2 = "recipientname";
		
		String title2 = "Top 30 Agencies (by Budget) & Recipient Countries for the Selected ";
		
		if(subSectorAdded)
			title2 += "Sub-Sectors";
		else
			title2 += "Sectors";
				
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
	
			
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	
	private static void createCountrySectorView(HashMap<String, List<String>> filters, boolean subSectorAdded ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

   
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected Recipient Countries and ";
		
		if(subSectorAdded)
			title1 += "Sub-Sectors";
		else
			title1 += "Sectors";
				
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
	
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		
		// channels/purposecode
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "purposecode";
		String secondColumnLabel2 = "purposename";
		
		String title2 = "Top 30 Agencies (by Budget) & implemented Sub-Sectors for the Selected Recipient Countries";		
		
		if(!subSectorAdded)
			title2 += " and Sectors";
		
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	
	private static void createDonorSectorView(HashMap<String, List<String>> filters, boolean subSectorAdded  ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

   
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected ";
		
		if(subSectorAdded)
			title1 += "Sub-Sectors";
		else
			title1 += "Sectors";
		
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
	    // channels/purposecode
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "purposecode";
		String secondColumnLabel2 = "purposename";
		String title2 = "Top 30 Agencies (by Budget) & implemented Sub-Sectors for the Selected Resource Partners ";
		if(!subSectorAdded)
			title2 += " and Sectors";
		
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	private static void createCountryDonorSectorView(HashMap<String, List<String>> filters, boolean subSectorAdded  ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

   
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected Recipient Countries and ";
		
		if(subSectorAdded)
			title1 += " Sub-Sectors";
		else
			title1 += " Sectors";
		
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		
        // channels/purposecode
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "purposecode";
		String secondColumnLabel2 = "purposename";
		String title2 = "Top 30 Agencies (by Budget) & implemented Sub-Sectors for the Selected Resource Partners";
		
		if(subSectorAdded)
			title2 += " and Recipient Countries";
		else
			title2 += ", Recipient Countries and Sectors";
		
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
}
