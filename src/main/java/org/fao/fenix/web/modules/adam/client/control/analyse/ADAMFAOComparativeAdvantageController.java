package org.fao.fenix.web.modules.adam.client.control.analyse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMTableController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMComparativeAdvantageMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMColorConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;


import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMFAOComparativeAdvantageController extends ADAMController {
	
    static String referencePeriod = "";
    
    static String readMore = "<br/><font color='#707071'>(Click on 'i' for an explanation on the formula used)</font>";
	
   /* public static SelectionListener<MenuEvent> createADAMComparativeAdvantageView(final MenuItem button) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				build();
			}
	};
  }*/
/*	public static SelectionListener<MenuEvent> createADAMComparativeAdvantageView(final MenuItem button) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				System.out.println("ADAMVisibilityController.getVisibility(COMPARATIVE_ADVANTAGE_TABLE): " + ADAMVisibilityController.getVisibility("COMPARATIVE_ADVANTAGE_TABLE"));
				
				//if (ADAMVisibilityController.getVisibility("COMPARATIVE_ADVANTAGE_TABLE").equals("true")) {
					
					
					//button.setText("Show Revealed FAO Comparative Advantage");
				///	ADAMVisibilityController.removeFAOComparativeAdvantageVisibility();
				//}
				//else {
					//button.setText("Show Revealed FAO Comparative Advantage");	
					
					if(ADAMReferencePeriodPanel.fromDateList.getValue()!=null && ADAMReferencePeriodPanel.toDateList.getValue()!=null){
						
						if(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulCode().equals(ADAMReferencePeriodPanel.toDateList.getValue().getGaulCode())){
							referencePeriod =  "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel()+")";
						}	
						else {
							referencePeriod = "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - " + ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
							
						}
					}
					createADAMComparativeAdvantageAgent();
			//	}

			}
		};
	}
	
	public static SelectionListener<ButtonEvent> createADAMComparativeAdvantageView() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("ADAMVisibilityController.getVisibility(COMPARATIVE_ADVANTAGE_TABLE): " + ADAMVisibilityController.getVisibility("COMPARATIVE_ADVANTAGE_TABLE"));
				
				Button button = (Button)ce.getBoxComponent();
				
				//if (ADAMVisibilityController.getVisibility("COMPARATIVE_ADVANTAGE_TABLE").equals("true")) {
					
					
					//button.setText("Show Revealed FAO Comparative Advantage");
					//ADAMVisibilityController.removeFAOComparativeAdvantageVisibility();
				//}
				//else {
					//button.setText("Show Revealed FAO Comparative Advantage");	
					
					if(ADAMReferencePeriodPanel.fromDateList.getValue()!=null && ADAMReferencePeriodPanel.toDateList.getValue()!=null){
						
						if(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulCode().equals(ADAMReferencePeriodPanel.toDateList.getValue().getGaulCode())){
							referencePeriod =  "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel()+")";
						}	
						else {
							referencePeriod = "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - " + ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
							
						}
					}
					createADAMComparativeAdvantageAgent();
				//}

			}
		};
	}
	*/
    
/*	public static void build(){
		if(ADAMReferencePeriodPanel.fromDateList.getValue()!=null && ADAMReferencePeriodPanel.toDateList.getValue()!=null){
			
			if(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulCode().equals(ADAMReferencePeriodPanel.toDateList.getValue().getGaulCode())){
				referencePeriod =  "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel()+")";
			}	
			else {
				referencePeriod = "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - " + ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
				
			}
		}
		createADAMComparativeAdvantageAgent();
	}*/
	
	public static void buildADAMComparativeAdvantageAgent() {
		
		
       if(ADAMReferencePeriodPanel.fromDateList.getValue()!=null && ADAMReferencePeriodPanel.toDateList.getValue()!=null){
			
			if(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulCode().equals(ADAMReferencePeriodPanel.toDateList.getValue().getGaulCode())){
				referencePeriod =  "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel()+")";
			}	
			else {
				referencePeriod = "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + " - " + ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
				
			}
		}
		
		//ADAMVisibilityController.restoreFAOComparativeAdvantageVisibility();
		
		HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
				
		Boolean countryAdded = addCountryFilter(filters, gaulList);
		System.out.println("countryAdded: " + countryAdded);
		Boolean donorAdded = addDonorFilter(filters, donorList);
		System.out.println("donorAdded: " + donorAdded);
		
		createComparativeView(filters);
		 
		
		 // restore custom
		 //restoreCustomContent();
		
	}
	
	
	private static void createComparativeView(HashMap<String, List<String>> filters ){

		/*objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.ADAMVIEW;*/
		
			
		List<String> selects = new ArrayList<String>();
		//selects.add("donorcode");
		//selects.add("donorname");
		//selects.add("recipientcode");
		selects.add("recipientname");
		
		
		List<String> faoSectors = new ArrayList<String>();
		for(String code: ADAMConstants.faoViewPurposes.keySet()){
			faoSectors.add(code);
		}
		//REMOVED fao sector filter!
		//filters.put("purposecode", faoSectors);
		
		String title = "Revealed FAO Comparative Advantage By Year "+referencePeriod;
		
			
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createComparativeAdvantageMatrix(ADAMBoxContent.COMPARATIVE_ADVANTAGE.name(), ADAMBoxContent.COMPARATIVE_ADVANTAGE.name(), "FAO Sectors", selects, title, filters, baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT));
		qvo1.setAggregations(null);
	   
		if(currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW))
	    	qvo1.setIsOR(false);
		else if(currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
			qvo1.setIsOR(true);
	    
		ADAMQueryVOBuilder.setFAOComparativeAdvantageDescription(qvo1);
		
		addBigTable("ANALYSE_TOP", qvo1);
	
		
	}
	
	
	public static void addBigTable(final String position, final ADAMQueryVO q) {
		RootPanel.get(position).setStyleName("big-box content");	 
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		
		
/*		RootPanel.get(position).setStyleName("big-box content");
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));*/
		
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		ADAMServiceEntry.getInstance().askADAM(q, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO vo) {
				if ( !q.getSwitchResources().isEmpty()) {
					vo.setSwitchResources(q.getSwitchResources());
				}
				
			    vo.setGroupTable(true);
			    vo.setGroupingTableColumnId("year"); // Group By Column
			
				//VerticalPanel panel = buildBigTable(vo, q, position, color);
				VerticalPanel panel = ADAMComparativeAdvantageMaker.buildTable(vo, q);
				
				if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
				
				RootPanel.get(position).add(panel);
				
				//System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position);
				
				//tableMap.put(position, vo);
				//currentUsedObjects.put(position, ADAMBoxContent.TABLE);
				
				
				/*if ( objID == objectWindowId ) {
					System.out.println("TABLE objID: " + objID + " | objectWindowId: " + objectWindowId + " position " + position + " ADDED ");
//					tableMap.put(position, vo);
//					currentUsedObjects.put(position, ADAMBoxContent.TABLE);
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					RootPanel.get(position).setStyleName("big-box content");
					RootPanel.get(position).add(panel);
				}*/
			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}
	

	/*public static VerticalPanel buildBigTable(ADAMResultVO vo,  ADAMQueryVO qvo, String position, String color) {
		VerticalPanel p = new VerticalPanel();
		p.setHeight(ADAMConstants.BIG_BOX_HEIGHT);
		p.setWidth(ADAMConstants.BIG_BOX_WIDTH);
		
		HorizontalPanel h1 = buildTableBoxMenu(vo, qvo, ADAMConstants.SMALL_MENU_GAP_WIDTH, position, color,  null, true, true);
		HorizontalPanel h2 =  new HorizontalPanel();
		ContentPanel cp = null;
		cp = ADAMTableMaker.buildGrid(vo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		h2.add(cp);
		p.add(h1);
//		h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), true));
		p.add(h2);
		return p;
	}
	
	public static HorizontalPanel buildTableBoxMenu(ADAMResultVO vo, ADAMQueryVO qvo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		Html label;
		if (vo != null) {
				label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		if(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulCode().equals(ADAMReferencePeriodPanel.toDateList.getValue().getGaulCode())){
			label.setWidth(900);
		} else {
			label.setWidth(750);
		}
	    
		h1.add(ADAMBoxMaker.addInfo(vo.getDescription(), "Explanation note of the Revealed FAO Comparative Advantage calculations", 600));
		h1.add(label);
		h1.add(buildTableBoxMenu(vo, qvo, position, color, objectSizeListener, isSmall, closeEnabled));
	
		return h1;
	}
	
	private static HorizontalPanel buildTableBoxMenu(ADAMResultVO vo, ADAMQueryVO qvo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		*//**SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
		more.setIconStyle("gear");
		MenuItem showProjects = new MenuItem("Show Projects");
		showProjects.addSelectionListener(ADAMController.showProjects());
		showProjects.setIconStyle("tableIcon");
		
		MenuItem createResource = new MenuItem("Create Resource");
		createResource.addSelectionListener(ADAMCustomController.createCustomResource());
		createResource.setIconStyle("wand");
		
		MenuItem changeInfo = new MenuItem("Change Title/Description");
		changeInfo.addSelectionListener(ADAMCustomController.changeTitleDescription(vo));
		changeInfo.setIconStyle("textEditBtn");
		
		System.out.println("vo.getSwitchResource(): " + vo.getSwitchResources());
		if ( !vo.getSwitchResources().isEmpty() && isSmall) {
			for ( ADAMQueryVO switchVO : vo.getSwitchResources()) {
//				System.out.println("chart swtich resource button");
				
				MenuItem switchResource = new MenuItem();
				ADAMBoxContent type = ADAMBoxContent.valueOf(switchVO.getResourceType());

				switch(type) {
				case TABLE:
					switchResource.setText("See as Table");
					switchResource.setIconStyle("tableIcon");
				break;

				case CHART:
					switchResource.setText("See as " + ADAMConstants.getBoxContentLabel(switchVO.getOutputType()));
					switchResource.setIconStyle("chartIcon");
				break;
				
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					switchResource.setText("See as Map");
					switchResource.setIconStyle("mapIcon");
				break;
				}

				switchResource.addSelectionListener(ADAMCustomController.switchResource(position, color, switchVO));
				moreMenu.add(switchResource);
			}
		}
		
		more.setMenu(moreMenu);

		**//*

		Button exportExcel = new Button("Export Table to Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportToExcel(vo, true));
		
		
		
		Button viewSummary = new Button();
		viewSummary.removeAllListeners();
		
		if(qvo.getAggregations()==null){
			viewSummary.setText("View Aggregated Year Summary");
			viewSummary.addSelectionListener(buildAggregatedYearTable(vo, qvo));
		} else {
			viewSummary.setText("View Year Breakdown");
			viewSummary.addSelectionListener(buildByYearTable(vo, qvo));
		}
		
		viewSummary.setIconStyle("tableIcon");
		
		if(ADAMReferencePeriodPanel.fromDateList.getValue()!=null && ADAMReferencePeriodPanel.toDateList.getValue()!=null){	
			if(!ADAMReferencePeriodPanel.fromDateList.getValue().getGaulCode().equals(ADAMReferencePeriodPanel.toDateList.getValue().getGaulCode())){
				panel.add(viewSummary);
			}	
		}
		
		
		//Button full = new Button();
		//full.setToolTip("Go to full screen view");
		//full.setIconStyle("arrow_out");
		//if (!isSmall) {
			Button close = new Button("Close");
			close.setToolTip("Close the view");
			close.setIconStyle("delete");
			//if ( !closeEnabled ) 
				//full.setEnabled(false);
		//}
		
	
			close.addSelectionListener(closeFAOComparativeAdvantageView());
		
		//panel.add(moreMenu);

		panel.add(exportExcel);
		panel.add(close);
		
		
		return panel;
	}*/
	
	public static SelectionListener<ButtonEvent> buildByYearTable(final ADAMResultVO vo, final ADAMQueryVO qvo, final String position) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				
				//objectWindowId = objectWindow.getNext();
				//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;
				
			    if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
				
				ADAMQueryVO  updatedQVO = qvo;
				updatedQVO.setAggregations(null);
				
				qvo.setTitle("Revealed FAO Comparative Advantage by Year "+referencePeriod);
				
				//addBigTable("COMPARATIVE_ADVANTAGE_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo, objectWindowId);
				addBigTable(position, qvo);

			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> buildAggregatedYearTable(final ADAMResultVO vo, final ADAMQueryVO qvo, final String position ) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				//objectWindowId = objectWindow.getNext();
				//currentVIEW = ADAMCurrentVIEW.ADAMVIEW;
				
				//RootPanel.get(position).setStyleName("big-box content");	 
				if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
				
				ADAMQueryVO  updatedQVO = qvo;
				Map<String, String> agg = new HashMap<String, String>();
				agg.put("year", "SUM");
				updatedQVO.setAggregations(agg);
				
				qvo.setTitle("Revealed FAO Comparative Advantage Aggregated Period "+referencePeriod);
				
				//addBigTable("COMPARATIVE_ADVANTAGE_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo, objectWindowId);

				addBigTable(position, qvo);
			}
		};
	}
	
	/*private static void createCountryView(HashMap<String, List<String>> filters ){
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implenting Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMTableController.addTable("CHANNELS_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId);

		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Sectors";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implenting Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMTableController.addTable("CHANNELS_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId);

	}
	
	
	private static void createCountryDonorView(HashMap<String, List<String>> filters ){
		objectWindowId = objectWindow.getNext();
		currentVIEW = ADAMCurrentVIEW.ADAMVIEW;

		
		// channels/donors
		
		String firstColumnCode = "channelcode";
		String firstColumnLabel = "channelname";
		
		String secondColumnCode = "donorcode";
		String secondColumnLabel = "donorname";
		String title1 = "Top 30 Agencies (by Budget) & contributing Resource Partners";
		
		ADAMQueryVO qvo1 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implenting Agencies", firstColumnCode, firstColumnLabel,  secondColumnCode, secondColumnLabel, title1, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMTableController.addTable("CHANNELS_10", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo1, objectWindowId);

		
		// channels/sector
		
		String firstColumnCode2 = "channelcode";
		String firstColumnLabel2 = "channelname";
		
		String secondColumnCode2 = "dac_sector";
		String secondColumnLabel2 = "dac_label";
		String title2 = "Top 30 Agencies (by Budget) & implemented Sectors";
		
		ADAMQueryVO qvo2 = ADAMQueryVOBuilder.createMatrix(ADAMBoxContent.VIEW_MATRIX.name(), ADAMBoxContent.VIEW_MATRIX.name(), "Implenting Agencies", firstColumnCode2, firstColumnLabel2,  secondColumnCode2, secondColumnLabel2, title2, new HashMap<String, List<String>>(), baseDate, ADAMColorConstants.color.get(ADAMBoxContent.RECIPIENT), crs_aggregationColumn.getGaulCode(), crs_aggregationColumn.getGaulLabel(), 30);
		ADAMTableController.addTable("CHANNELS_11", ADAMColorConstants.color.get(ADAMBoxContent.FAO), qvo2, objectWindowId);

	}*/
	
	public static SelectionListener<ButtonEvent> closeFAOComparativeAdvantageView() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVisibilityController.removeFAOComparativeAdvantageVisibility();
			}
		};
	}

}
