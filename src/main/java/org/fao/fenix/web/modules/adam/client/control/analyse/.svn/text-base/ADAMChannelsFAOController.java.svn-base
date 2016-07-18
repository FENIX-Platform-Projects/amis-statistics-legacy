package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMFAOViewController;
import org.fao.fenix.web.modules.adam.client.control.ADAMTableController;
import org.fao.fenix.web.modules.adam.client.control.ADAMViewController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;

public class ADAMChannelsFAOController extends ADAMChannelsController {
	
	public static void createChannelsFAOView() {
		
		//ADAMVisibilityController.restoreChannelsVisibility();
		
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		Map<String, String> soList = ADAMBoxMaker.soSelected;
		Map<String, String> orList = ADAMBoxMaker.orSelected;

		
	     HashMap<String, List<String>> filters = new HashMap<String, List<String>>();

		 Boolean countryAdded = addCountryFilter(filters, gaulList);
		 Boolean donorAdded = addDonorFilter(filters, donorList);
		
		 HashMap<String, String> sos = ADAMFAOViewController.getSO(soList);
		 Boolean soAdded = !sos.isEmpty();		 
		 
		 HashMap<String, String> ors = ADAMFAOViewController.getSO(orList);
		 Boolean orAdded = !ors.isEmpty();
		
		
		 /** donor **/
		 
		 if( donorAdded && !countryAdded && !soAdded && !orAdded ) {
			 createDonorView(filters);
		 }

		 /** country **/
		 
		 else if( countryAdded && !donorAdded && !soAdded && !orAdded  ) {
			 createCountryView(filters);
		 }
		 
		 /** country/donor **/

		 else if( countryAdded && donorAdded && !soAdded && !orAdded  ) {
			 createCountryDonorView(filters);
		 }

		 /** so **/
		 
		 else if ( !donorAdded && !countryAdded && soAdded && !orAdded ) {
			 createSOView(filters, orAdded, sos, ors);
		 }
		 
		 /** country/so **/
		 
		 else if (!donorAdded && countryAdded && soAdded && !orAdded ) {
			 createCountrySOView(filters, orAdded, sos, ors);
		 }
		 
		 /** donor/so **/
		 
		 else if (donorAdded && !countryAdded && soAdded && !orAdded ) {
			 createDonorSOView(filters, orAdded, sos, ors);
		 }
		 
		 /** Country/donor/so **/
		 
		 else if (donorAdded && countryAdded && soAdded && !orAdded ) {
			 createCountryDonorSOView(filters, orAdded, sos, ors);
		 }
		 
         /** or, same views as SO called **/
		 else if ( !countryAdded && !donorAdded && !soAdded && orAdded) {
			 createSOView(filters, orAdded, sos, ors);
			// createSubSectorView(channelList, filters);
		 }
		 // country/subsector
		 else if ( countryAdded && !donorAdded && !soAdded && orAdded ) {
			 createCountrySOView(filters, orAdded, sos, ors);
		 }
		 // donor/subsector
		 else if ( !countryAdded && donorAdded && !soAdded && orAdded ) {
			 createDonorSOView(filters, orAdded, sos, ors);
		 }
		// sector/subsector
		 else if ( !countryAdded && !donorAdded && soAdded && orAdded ) {
			 createSOView(filters, orAdded, sos, ors);
		 }
		// country/donor/subsector
		 else if ( countryAdded && donorAdded && !soAdded && orAdded ) {
			 createCountryDonorSOView(filters, orAdded, sos, ors);
		 }
		// country/sector/subsector
		 else if ( countryAdded && !donorAdded && soAdded && orAdded ) {
			 createCountrySOView(filters, orAdded, sos, ors);
		 }
		// donor/sector/subsector
		 else if ( !countryAdded && donorAdded && soAdded && orAdded ) {
			 createDonorSOView(filters, orAdded, sos, ors);
		 }
		// country/donor/sector/subsector
		 else if ( countryAdded && donorAdded && soAdded && orAdded ) {
			 createCountryDonorSOView(filters, orAdded, sos, ors);
		 }
		 
		 else if ( !donorAdded && !countryAdded && soAdded && orAdded ) {
			 createSOView(filters, orAdded, sos, ors);
		 }
		 	
		 
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
		 
		 

		 else if( !countryAdded && !donorAdded && !soAdded && !orAdded) {
			 /*** global view **/
			 globalFAOAgenciesView();
		 }

		 // restore custom
		 //restoreCustomContent();
		
	
	}
	
	public static void globalFAOAgenciesView() {
		
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners";
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		qvo1.setPriorities(ADAMConstants.faoSO); // get all SOs
			
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		// channels/recipients
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "recipientcode";
		String secondColumnLabel2 = "recipientname";
		String title2 = "Top 30 Agencies (by Budget) & Recipient Countries";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		qvo2.setPriorities(ADAMConstants.faoSO);// get all SOs
		
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");
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
		qvo1.setPriorities(ADAMConstants.faoSO);// get all SOs
			
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		ADAMQueryVO topDonorsChart = ADAMQueryVOBuilder.topDonorsChart(ADAMBoxContent.BAR.toString(), "Top Ten Resource Partners", filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.DONOR), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), false, false, 10);	
		ADAMViewController.setAggregatedViewName(topDonorsChart, false);
		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Strategic Objectives for the Selected Resource Partners";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		qvo2.setPriorities(ADAMConstants.faoSO);// get all SOs
		qvo2.setFilterFrameworkType(ADAMBoxContent.FILTER_ALL_SO.name());
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
		qvo1.setPriorities(ADAMConstants.faoSO);// get all SOs
		setImplementingAgenciesDescription(qvo1);
		
		ADAMViewController.setAggregatedViewName(qvo1, false);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Strategic Objectives for the Selected Recipient Countries";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		qvo2.setPriorities(ADAMConstants.faoSO);// get all SOs
		qvo2.setFilterFrameworkType(ADAMBoxContent.FILTER_ALL_SO.name());
		qvo2.setReturnFrameworkType(ADAMBoxContent.RETURN_SO.name());
		setImplementingAgenciesDescription(qvo2);
		ADAMViewController.setAggregatedViewName(qvo2, false);
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
		qvo1.setPriorities(ADAMConstants.faoSO);// get all SOs
		qvo1.setFilterFrameworkType(ADAMBoxContent.FILTER_ALL_SO.name());
		setImplementingAgenciesDescription(qvo1);
		
		ADAMViewController.setAggregatedViewName(qvo1, false);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Strategic Objectives for the Selected Recipient Countries and Resource Partners";
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		qvo2.setPriorities(ADAMConstants.faoSO);// get all SOs
		qvo2.setFilterFrameworkType(ADAMBoxContent.FILTER_ALL_SO.name());
		qvo2.setReturnFrameworkType(ADAMBoxContent.RETURN_SO.name());
		setImplementingAgenciesDescription(qvo2);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	
	private static void createSOView(HashMap<String, List<String>> filters, boolean orAdded,  HashMap<String, String> sos, HashMap<String, String> ors){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

   
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected ";
		
		if(orAdded)
			title1 += "Organizational Results";
		else
			title1 += "Strategic Objectives";
				
		
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30, sos, ors, ADAMBoxContent.RETURN_SO.name());
		/*qvo1.setPriorities(sos); //selectedSO
		
		if(orAdded)
			qvo1.setReturnFrameworkType(ADAMBoxContent.RETURN_OR.name());
		else
			qvo1.setReturnFrameworkType(ADAMBoxContent.RETURN_SO.name());*/
		
		setImplementingAgenciesDescription(qvo1);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		// channels/recipients
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "recipientcode";
		String secondColumnLabel2 = "recipientname";
		
		String title2 = "Top 30 Agencies (by Budget) & Recipient Countries for the Selected ";
		
		if(orAdded)
			title2 += "Organizational Results";
		else
			title2 += "Strategic Objectives";
				
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
	
			
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30, sos, ors, ADAMBoxContent.RETURN_SO.name());

	/*	if(orAdded) {
			qvo2.setPriorities(ors); //selectedORs
			qvo2.setReturnFrameworkType(ADAMBoxContent.RETURN_OR.name());
		}	
		else {
			qvo2.setPriorities(sos); //selectedSOs
			qvo2.setReturnFrameworkType(ADAMBoxContent.RETURN_SO.name());
		}*/
		setImplementingAgenciesDescription(qvo2);
		
		ADAMViewController.setAggregatedViewName(qvo2, false);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	
	private static void createCountrySOView(HashMap<String, List<String>> filters, boolean orAdded, HashMap<String, String> sos, HashMap<String, String> ors){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

        // channels/donors		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected Recipient Countries and ";
		if(orAdded)
			title1 += "Organizational Results";
		else
			title1 += "Strategic Objectives";
				
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
	
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		qvo1.setPriorities(sos);
		setImplementingAgenciesDescription(qvo1);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
   
	/*	// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) and Selected ";
		
		if(orAdded)
			title1 += "Organizational Results";
		else
			title1 += "Strategic Objectives";
				
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
	
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30, sos, ors, ADAMBoxContent.RETURN_SO.name());
		ADAMViewController.setAggregatedViewName(qvo1, false);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");
*/
		
		
		// channels/purposecode
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "purposecode";
		String secondColumnLabel2 = "purposename";
		
		String title2 = "Top 30 Agencies (by Budget) & implemented Organizational Results for the Selected Recipient Countries";		
		
		if(!orAdded)
			title2 += " and Strategic Objectives";
		
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30, sos, ors, ADAMBoxContent.RETURN_OR.name());
		//qvo2.setPriorities(ors); //selectedORS
		//qvo2.setReturnFrameworkType(ADAMBoxContent.RETURN_OR.name());
		
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	
	private static void createDonorSOView(HashMap<String, List<String>> filters, boolean orAdded, HashMap<String, String> sos, HashMap<String, String> ors  ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

   
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected ";
		
		if(orAdded)
			title1 += "Organizational Results";
		else
			title1 += "Strategic Objectives";
		
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30, sos, ors, ADAMBoxContent.RETURN_SO.name());
		//qvo1.setPriorities(sos); //selectedSOs
		ADAMViewController.setAggregatedViewName(qvo1, false);
		setImplementingAgenciesDescription(qvo1);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
	    // channels/purposecode
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "purposecode";
		String secondColumnLabel2 = "purposename";
		String title2 = "Top 30 Agencies (by Budget) & implemented Organizational Results for the Selected Resource Partners ";
		if(!orAdded)
			title2 += " and Strategic Objectives";
		
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30, sos, ors, ADAMBoxContent.RETURN_OR.name());
		//qvo2.setPriorities(sos); //selectedSOs
		setImplementingAgenciesDescription(qvo2);
		ADAMViewController.setAggregatedViewName(qvo2, false);
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
	private static void createCountryDonorSOView(HashMap<String, List<String>> filters, boolean orAdded, HashMap<String, String> sos, HashMap<String, String> ors  ){
		//objectWindowId = objectWindow.getNext();
		//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

   
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners for the Selected Recipient Countries and ";
		
		if(orAdded)
			title1 += " Organizational Results";
		else
			title1 += " Strategic Objectives";
		
		title1 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		qvo1.setPriorities(sos);//selectedSOs
		setImplementingAgenciesDescription(qvo1);
		ADAMViewController.setAggregatedViewName(qvo1, false);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

		
		/*ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30,sos, ors, ADAMBoxContent.RETURN_SO.name());
		//qvo1.setPriorities(sos); //selectedSOs
		ADAMViewController.setAggregatedViewName(qvo1, false);
		ADAMTableController.addTable("ANALYSE_TOP", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");
*/
		
		
        // channels/purposecode
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "purposecode";
		String secondColumnLabel2 = "purposename";
		String title2 = "Top 30 Agencies (by Budget) & implemented Organizational Results for the Selected Resource Partners";
		
		if(orAdded)
			title2 += " and Recipient Countries";
		else
			title2 += ", Recipient Countries and Strategic Objectives";
		
		title2 += " ("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+ ")";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createFAOMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implementing Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30,sos, ors, ADAMBoxContent.RETURN_OR.name());
		//qvo2.setPriorities(sos); //selectedSOs
		ADAMViewController.setAggregatedViewName(qvo2, false);
		setImplementingAgenciesDescription(qvo2);
		
		ADAMTableController.addTable("ANALYSE_MIDDLE", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId, addRemoveTableIcon, removeListener, "Implementation Agencies");

	}
	
}
