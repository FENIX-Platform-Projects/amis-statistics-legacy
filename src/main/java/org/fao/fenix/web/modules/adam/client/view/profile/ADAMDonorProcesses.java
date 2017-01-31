package org.fao.fenix.web.modules.adam.client.view.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorChannelOfCooperationVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ADAMDonorProcesses {
	
	private final static String BREAK = FormatUtils.BREAK;
	private final static String SPACER = FormatUtils.SPACER;
	
	   
	  public ScrollPanel build(ADAMDonorProfileVO vo) {
		  
		    final FlexTable flexTable = new FlexTable();
		    flexTable.addStyleName("cw-FlexTable");
		    //  flexTable.setWidth("100%");
		    flexTable.setCellSpacing(5);
		    flexTable.setCellPadding(3);
		    
		    
		  /*  Map<String, ADAMDonorChannelOfCooperationVO> channels = vo.getChannelsOfCooperation();
		    int channelsSize = 0;
		    if(channels!=null){
		    	channelsSize = channels.size();
		    }

		    // Funding Bodies within Resource Partner
		    addFirstRow(flexTable, FormatUtils.getFormattedTitle(SPACER+vo.getFundingBodiesTitle(), false)+BREAK+vo.getFundingBodies()+BREAK, channelsSize);

		    // Channel of cooperation 		    
		    if(channels!=null){
		    	List<VerticalPanel> panels = new ArrayList<VerticalPanel>();
		    	  for(String id: channels.keySet()){
		    		  ADAMDonorChannelOfCooperationVO channel = channels.get(id);
						 
					  VerticalPanel panel = new VerticalPanel();
					  panel.setSpacing(5);
					  panel.setStyleName("cw-FlexTable-buttonPanel");
					  panel.add(new Html(FormatUtils.getFormattedTitle(vo.getChannelsOfCooperationTitle(), true)+BREAK+channel.getChannelOfCooperation()));
					  panel.add(new Html(FormatUtils.getFormattedTitle(vo.getApplicationAndNegotiationProcessTitle(), false)+BREAK+channel.getApplicationAndNegotiationProcess()));
					  panel.add(new Html(FormatUtils.getFormattedTitle(vo.getAnnualFundingCycleTitle(), false)+BREAK+channel.getAnnualFundingCycle()));
					  panel.add(new Html(FormatUtils.getFormattedTitle(vo.getSpecialCharacteristicsTitle(), false)+BREAK+channel.getSpecialCharacteristics()));
					  panel.add(new Html(FormatUtils.getFormattedTitle(vo.getBudgetRevisionPoliciesTitle(), false)+BREAK+channel.getBudgetRevisionPolicies()));
					  panel.add(new Html(FormatUtils.getFormattedTitle(vo.getAccruedInterestPoliciesTitle(), false)+BREAK+channel.getAccruedInterestPolicies()));
					  
					  panels.add(panel);
					  
			   	  }
		    	  
		    	  addRow(flexTable, panels);
					 
			  }
*/
		    flexTable.ensureDebugId("cwFlexTable");

		    ScrollPanel scrollPanel = new ScrollPanel(flexTable);
		    scrollPanel.setWidth("100%");
		    scrollPanel.setHeight("390px");
		    scrollPanel.setStyleName("layouts-Scroller");

		    return scrollPanel;
	
	  } 
	  
	  
	  private void addFirstRow(FlexTable flexTable, String label, int channelsSize) {
		  int numRows = flexTable.getRowCount();
		  flexTable.setWidget(numRows, 0, new HTML(label));
		 // flexTable.getFlexCellFormatter().setRowSpan(0, 2, numRows + 1);
		  
		  if(channelsSize > 1)
			  flexTable.getFlexCellFormatter().setColSpan(0, 0, channelsSize);
	  }

	  private void addRow(FlexTable flexTable, List<VerticalPanel> panels) {
		  int numRows = flexTable.getRowCount();
		  
		  for(int i = 0; i < panels.size(); i++)
			  flexTable.setWidget(numRows, i, panels.get(i));
		  		 			  
	  }
}  

