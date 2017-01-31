package org.fao.fenix.web.modules.adam.client.view.profile;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.DonorProfileController;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class ADAMDonorProfile {
	
	private final static String BREAK = FormatUtils.BREAK;
	   
	  public ScrollPanel build(ADAMDonorProfileVO vo) {
		  
		   final FlexTable flexTable = new FlexTable();
		    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		    flexTable.addStyleName("cw-FlexTable");
		    //  flexTable.setWidth("100%");
		    flexTable.setCellSpacing(5);
		    flexTable.setCellPadding(3);
		    /**   
		
		    // Overall Priority Themes
		    cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		    flexTable.setHTML(0, 0, FormatUtils.getFormattedTitle(vo.getPriorityThemesTitle(), true)+BREAK+vo.getPriorityThemes()+BREAK);
		    cellFormatter.setColSpan(0, 0, 2);
		    

		    // FAO Strategic Objectives and Web Links
		    String title = vo.getKeySOsTitle() +"* ("+vo.getKeySOsReferenceDate()+")";
		    VerticalPanel panel = getHashMapPanel(title, vo.getKeySOs(), null, true);
		    panel.add(new Html("(* " + FormatUtils.getFormattedTitle(vo.getKeySOsDisclaimer()) +")"));
		    panel.add(new Html(FormatUtils.getFormattedTitle(vo.getExternalLinksTitle(), true)));

		    List<String> links = vo.getExternalLinks();
			  if(links!=null){
					for(String link: links){
						panel.add(new Html("<a class='profile' href='"+link+"' target='_blank'>"+link+"</a><br/>"));
		    	}
			  }
			      
		    flexTable.setWidget(0, 1, panel);
		    cellFormatter.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);

		    // Overall Priority Geographical Areas
		    addRow(flexTable, FormatUtils.getFormattedTitle(vo.getPriorityGeographicalAreasTitle(), false)+BREAK+vo.getPriorityGeographicalAreas()+BREAK);

		    // Type of Assistance Favoured - waiting for coding system, then this will processed using getHashMapPanel 
		    Map<String, String> assistances = vo.getFavouredFundingArrangements();
		    StringBuilder sb = new StringBuilder();

		    if(assistances!=null){
		    	int i= 0;
		    	for(String type: assistances.keySet()){
		    		sb.append(type);  
		    		if (i < assistances.size() - 1)
		    			sb.append(", ");

		    		i++;
		    	}

		    }

		    addRow(flexTable, FormatUtils.getFormattedTitle(vo.getFavouredFundingArrangementsTitle(), false)+BREAK+sb.toString()+BREAK);

		    // FAO Strategic Objectives and Web Links
		    addRow(flexTable, getHashMapPanel(vo.getExternalFundingForDeliveryPeriodsTitle(), vo.getExternalFundingForDeliveryPeriods(), ":", false));

		    // Recipient Countries [timeperiod] (FAO projects)
		     addRow(flexTable, getFAORegionalHashMapPanel(vo.getRegionalRecipientCountriesTitle(), vo.getRegionalRecipientCountries()));

		    flexTable.ensureDebugId("cwFlexTable");
**/
		    ScrollPanel scrollPanel = new ScrollPanel(flexTable);
		    scrollPanel.setWidth("100%");
		    scrollPanel.setHeight("390px");
		    scrollPanel.setStyleName("layouts-Scroller");

		    return scrollPanel;
	
	  } 
	  
	  
	  private void addRow(FlexTable flexTable, String label) {
		  int numRows = flexTable.getRowCount();
		  flexTable.setWidget(numRows, 0, new HTML(label));
		  flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows + 1);
	  }

	  private void addRow(FlexTable flexTable, VerticalPanel panel) {
		  int numRows = flexTable.getRowCount();
		  flexTable.setWidget(numRows, 0, panel);
		  flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows + 1);
	  }

	  private VerticalPanel getHashMapPanel(String title, Map<String, String> map, String symbol, boolean isCentered){

		  if (symbol==null)
			  symbol = "-";

		  VerticalPanel panel = new VerticalPanel();
		  panel.setSpacing(5);
		  panel.setStyleName("cw-FlexTable-buttonPanel");
		  panel.add(new Html(FormatUtils.getFormattedTitle(title, isCentered)));


		  if(map!=null){
			  for(String code: map.keySet()){
				  panel.add(new Html(FormatUtils.getFormattedTitle(code, false)+ " " +symbol+" "+ map.get(code)+ "<br/>"));
			  }
		  }

		  return panel;
	  }

	  private VerticalPanel getFAORegionalHashMapPanel(String title, Map<String, String> map){

		  String symbol = "-";

		  VerticalPanel panel = new VerticalPanel();
		  panel.setSpacing(5);
		  panel.setStyleName("cw-FlexTable-buttonPanel");
		  panel.add(new Html(FormatUtils.getFormattedTitle(title, false)));


		  if(map!=null){
			  for(String code: map.keySet()){
				    ClickHtml donor = new ClickHtml();
				    donor.setHtml("<span style='text-decoration: underline; cursor: pointer;'>"+FormatUtils.getFormattedTitle(code, false)+"</span>" + " " +symbol+" "+ map.get(code)+ "<br/>");
				    donor.setTitle(BabelFish.print().clickToOpenADAMView()+ " " + code);
				    donor.addListener(Events.OnClick, DonorProfileController.openADAMDonorViewForFAORegion(code));
				    panel.add(donor);
			  }
		  }

		  return panel;
	  }
	  
}  

