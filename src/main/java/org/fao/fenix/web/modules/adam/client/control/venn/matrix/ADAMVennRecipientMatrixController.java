package org.fao.fenix.web.modules.adam.client.control.venn.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennMatrix;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennRecipientMatrix;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennRecipientsVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * 
 * Creation of the Recipient Priorities Matrices associated with the the Venn Diagram
 *
 */

public class ADAMVennRecipientMatrixController extends ADAMVennController {

	public static String NOT_LISTED_MESSAGE = "Not listed as a priority in the source document";
	
	public static void buildVennRecipientMatrixAgent(final ADAMVennMatrix adamVennMatrix, final Boolean showWindow, final String position, final String position2, final String position3) {

		System.out.println("----------- buildVennRecipientMatrixAgent 1 ----------------- ");
		
		RootPanel.get(position).setStyleName("big-box content");	 
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
		
		RootPanel.get(position).add(new ADAMLoadingPanel().buildWaitingPanel());
		
		/*RootPanel.get(position).setStyleName("small-box content");
		
		if (RootPanel.get(position).getWidgetCount() > 0)
			RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));*/
		
		
		final Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		//clean up the list
		final Map<String, String> codes = getCodes(gaulList);

		final ADAMQueryVO qvo;	

		String label = "";

		if(codes.size() == 1) {
			qvo = ADAMQueryVOBuilder.getVennMatrix(ADAMBoxContent.VENN_RECIPIENT_CHANNEL_MATRIX, "RECIPIENT_KEYSOS", codes, null, null);	
			label = codes.entrySet().iterator().next().getValue() + " Government & Agreed Priorities in Agriculture and Food Security";
		}	
		else {
			qvo = ADAMQueryVOBuilder.getVennMatrix(ADAMBoxContent.VENN_RECIPIENT_MATRIX, "RECIPIENT_KEYSOS", codes, null, null);	
			label = "Countries' Government Priorities in Agriculture and Food Security";
		}

		qvo.setTitle(label);
		qvo.setCurrentView(ADAMController.currentVIEW);

		
		
		
		
		ADAMServiceEntry.getInstance().askADAM(qvo, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO rvo) {

				ADAMVennRecipientMatrix adamVennRecipientMatrix = new ADAMVennRecipientMatrix();

				System.out.println("----------- buildVennRecipientMatrixAgent 2 ----------------- ");
				
				//set show priorities by default
				if(codes.size() == 1)
					setVennMatrixRecipientsChannel(rvo, false, true);	
				else
					setVennMatrixRecipients(rvo, false, true);	

				System.out.println("-------------------------- buildVennRecipientMatrixAgent 3 ");
				
/*
				if ( showWindow)
					adamVennRecipientMatrix.build(rvo, qvo, showWindow);
				else {*/

				//  if(adamVennMatrix.getPanel()!=null)
					//  adamVennMatrix.getPanel().removeAll();
				  
					

					System.out.println("-------------------------- buildVennRecipientMatrixAgent 4 ");
					
					
					//adamVennMatrix.getPanel().removeAll();
					//adamVennMatrix.getPanel().add(adamVennRecipientMatrix.getPanel());
					//adamVennMatrix.getPanel().layout();
					
					if (RootPanel.get(position).getWidgetCount() > 0)
						RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
					
					RootPanel.get("ANALYSE_TOP").setStyleName("big-box border  content");
					RootPanel.get(position).add(adamVennRecipientMatrix.build(rvo));
				
					if(codes.size() > 1) {
						RootPanel.get("ANALYSE_MIDDLE").setStyleName("big-box border  content");
						RootPanel.get("ANALYSE_BOTTOM").setStyleName("big-box border  content");
						// ADD AGREED PRIORITIES MATRIX TOO, if more than country selected
						ADAMVennChannelMatrixController.buildVennMatrixChannelAgent(adamVennMatrix, rvo, false, true, position2);
						// ADD DONOR PRIORITIES MATRIX TOO
						ADAMVennDonorMatrixController.buildVennDonorMatrixAgent(adamVennMatrix, rvo, position3);
					} else {
						// ADD DONOR PRIORITIES MATRIX TOO
						RootPanel.get("ANALYSE_MIDDLE").setStyleName("big-box border  content");
						ADAMVennDonorMatrixController.buildVennDonorMatrixAgent(adamVennMatrix, rvo, position2);
					}
					

					//adamVennMatrix.getWindow().layout();
				//}
			}
			public void onFailure(Throwable arg0) {
			}
		});

	}


	public static void buildVennMatrixRecipientReloadAgent(ADAMVennMatrix vennMatrix, ADAMResultVO rvo, Boolean showOnlyCPF,  Boolean showStatedPriorities) {
		setVennMatrixRecipients(rvo, showOnlyCPF, showStatedPriorities);

		vennMatrix.getMatrixPanel().removeAll();

		System.out.println("buildVennMatrixRecipientAgent()");

		vennMatrix.getMatrixPanel().add(buildVennRecipientMatrixPanel(rvo));

		vennMatrix.getMatrixPanel().layout();
	}

	// Default display when > 1 country selected 
	private static void setVennMatrixRecipients(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities) {

		setVennMatrixRecipientsTableHeaders(rvo, showOnlyCPF, showStatedPriorities);

		setVennMatrixRecipientsTableContents(rvo, showOnlyCPF, showStatedPriorities);

	}

	// Default display when 1 country is selected 
	private static void setVennMatrixRecipientsChannel(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities) {

		setVennMatrixRecipientsTableHeaders(rvo, showOnlyCPF, showStatedPriorities);

		setVennMatrixRecipientsChannelTableContents(rvo,showOnlyCPF, showStatedPriorities);

	}

	private static void setVennMatrixRecipientsTableHeaders(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities) { 

		List<String> tableHeaders = new ArrayList<String>();

		List<String> highlightedColumns = new ArrayList<String>();

		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
  
		Map<String, String> codes = new LinkedHashMap<String, String>();
		
		//System.out.println("------------------ setVennMatrixRecipientsTableHeaders rvo.getConvertedRecipients() ====  "+rvo.getConvertedRecipients() + " | "+rvo.getConvertedRecipients().isEmpty());
		
		if(rvo.getConvertedRecipients()!=null && !rvo.getConvertedRecipients().isEmpty()){
			codes.clear();
			codes = rvo.getConvertedRecipients();
		} else{
			codes.clear();
			codes = getCodes(gaulList);
		}
			
		//System.out.println("------------------ setVennMatrixRecipientsTableHeaders RECIPIENT CODES ====  "+codes);
		
		LinkedHashMap<String, String> recipients = ADAMController.sortByValues(codes);

		
		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = rvo.getVennMatrix().getRecipientsVOHM();

		setVennMatrixORDACTableHeadersWithDAC(tableHeaders);

		for(String recipient : recipients.keySet()) {
			// add if isCalculated
			if ( recipientsVOHM.containsKey(recipient)) {
				ADAMVennRecipientsVO vo = recipientsVOHM.get(recipient);

				System.out.println("&&&&&&&&&&&&&&&& IS CALCULATED "+vo.getIsCalculated());

				if ( recipientsVOHM.get(recipient).getIsCalculated()!=null && recipientsVOHM.get(recipient).getIsCalculated() ) {
					if ( !showOnlyCPF ) {
						highlightedColumns.add("Calculated");
						tableHeaders.add("Calculated Priorities for "+recipients.get(recipient));

						if (showStatedPriorities && recipients.size() == 1) {						
							tableHeaders.add("FAO & Government of "+recipients.get(recipient)+ " Agreed Priorities");
						}
					}	
				}
				else {
					highlightedColumns.add("National Priorities");
					tableHeaders.add("Government of "+recipients.get(recipient)+ " National Priorities");

					if (showStatedPriorities && recipients.size() == 1) {						
						tableHeaders.add("FAO & Government of "+recipients.get(recipient)+ " Agreed Priorities");
					}
				}	
			}
			else
				tableHeaders.add(recipients.get(recipient));
		} 

		if(recipients.size() > 1)
			tableHeaders.add("Totals");

		rvo.setTableHeaders(tableHeaders);
		rvo.setHighlightedTableColumns(highlightedColumns);



	}


	private static void setVennMatrixRecipientsTableContents(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities ) { 

		List<List<String>> tableContents = new ArrayList<List<String>>();

		Integer counter = 0;

		Map<String, String> codes = new LinkedHashMap<String, String>();
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		
		if(rvo.getConvertedRecipients()!=null && !rvo.getConvertedRecipients().isEmpty()){
			codes.clear();
			codes = rvo.getConvertedRecipients();
		} else{
			codes.clear();
			codes = getCodes(gaulList);
		}
		
		LinkedHashMap<String, String> recipients = ADAMController.sortByValues(codes);

		LinkedHashMap<String, String> ors = rvo.getVennMatrix().getOrs();

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = rvo.getVennMatrix().getRecipientsVOHM();

		//add the source as the first row if not null
		List<String> sourceRow = createVennMatrixSourceRow(rvo, showOnlyCPF, showStatedPriorities, recipients, recipientsVOHM);

		if(sourceRow!=null) {
			tableContents.add(sourceRow);
			rvo.setHasSourceRow(true);
		} else
			rvo.setHasSourceRow(false);

		List<String> row = new ArrayList<String>();

		for(String or : ors.keySet()) {

			counter = 0;
			row = new ArrayList<String>();
			
			if(!removeDACSectorCode())
				row.add(or);

			//OR Description
			row.add(ors.get(or));

			// Values per country
			if ( !showOnlyCPF ) {
				for(String recipientCode : recipients.keySet()) {
					if (recipientsVOHM.containsKey(recipientCode)) {
						if (recipientsVOHM.get(recipientCode).getOrs()!=null && recipientsVOHM.get(recipientCode).getOrs().containsKey(or)) {
							Boolean addedStatedPriority = false;
							if ( showStatedPriorities ) {
								if ( recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or)) {
									StringBuffer statedPriorityBuf = new StringBuffer();
									for(String statedPriority : recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities()) {
										statedPriorityBuf.append(statedPriority);									
										statedPriorityBuf.append("<br/><br/>");
									}
									row.add(statedPriorityBuf.toString());

									addedStatedPriority = true;
								}

							}

							if ( !addedStatedPriority ) {
								String title = vennMatrixOnHoverTitle(codes, recipients.get(recipientCode), or, ors.get(or));
							    row.add("<IMG src=\"images/accept.png\" BORDER=\"0\" title=\""+title+"\"/> ");    	
								//row.add(" <IMG src=\"images/accept.png\" BORDER=\"0\" />  ");
							}
							counter++;
						}
						else{
							row.add(" ");
						}
					}
					else
						row.add(" ");
				}
			}
			else {
				for(String recipientCode : recipients.keySet()) {
					if ( !recipientsVOHM.get(recipientCode).getIsCalculated() ) {
						if (recipientsVOHM.containsKey(recipientCode)) {
							if (recipientsVOHM.get(recipientCode).getOrs().containsKey(or)) {
								Boolean addedStatedPriority = false;
								if ( showStatedPriorities ) {
									if ( recipientsVOHM.get(recipientCode).getStatedPrioritiesVO()!= null && recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or)) {
										StringBuffer statedPriorityBuf = new StringBuffer();
										for(String statedPriority : recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities()) {
											statedPriorityBuf.append(statedPriority);
											statedPriorityBuf.append("<br/><br/>");
										}
										row.add(statedPriorityBuf.toString());

										addedStatedPriority = true;
									} else
										row.add("");
								}

								if ( !addedStatedPriority ) {
									String title = vennMatrixOnHoverTitle(codes, recipients.get(recipientCode), or, ors.get(or));
								    row.add("<IMG src=\"images/accept.png\" BORDER=\"0\" title=\""+title+"\"/> ");    	
//									row.add(" <IMG src=\"images/accept.png\" BORDER=\"0\" />  ");
								}
								
								counter++;
							}
							else {
								row.add(" ");
							}
						}
						else
							row.add(" ");
					} 
				}
			}

			row.add(String.valueOf(counter));

			tableContents.add(row);
		}


		// show totals		

		row = new ArrayList<String>();
		row.add("Totals");
		if(!removeDACSectorCode())
			row.add("");
		
		System.out.println("SELECTED NIUMBER OF RECIPIENTS ------------ "+ recipients.keySet().size());
		System.out.println("SELECTED NIUMBER OF RECIPIENTS ------------ "+ recipients);

		for(String recipientCode : recipients.keySet()) {
			if (recipientsVOHM.containsKey(recipientCode)) {
				System.out.println("IN recipientsVOHM ------------ "+ recipientCode);

				if ( !showOnlyCPF ) {
					if(recipientsVOHM.get(recipientCode).getOrs()!=null)
						row.add(String.valueOf(recipientsVOHM.get(recipientCode).getOrs().size()));
					else
						row.add(String.valueOf(0));

				}
				else {
					if ( !recipientsVOHM.get(recipientCode).getIsCalculated() ) {

						if(recipientsVOHM.get(recipientCode).getOrs()!=null)
							row.add(String.valueOf(recipientsVOHM.get(recipientCode).getOrs().size()));
						else
							row.add(String.valueOf(0));

					}
				}
			}
		}

		// total
		row.add(String.valueOf(rvo.getVennMatrix().getOrs().size()));

		tableContents.add(row);

		rvo.setTableContents(tableContents);

		System.out.println("ors.keySet() --------------- "+ors.keySet());
	}

	private static void setVennMatrixRecipientsChannelTableContents(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities ) { 

		List<List<String>> tableContents = new ArrayList<List<String>>();

		Integer counter = 0;

		Map<String, String> codes = new LinkedHashMap<String, String>();
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		
		if(rvo.getConvertedRecipients()!=null && !rvo.getConvertedRecipients().isEmpty()){
			codes.clear();
			codes = rvo.getConvertedRecipients();
		} else{
			codes.clear();
			codes = getCodes(gaulList);
		}
		
		LinkedHashMap<String, String> recipients = ADAMController.sortByValues(codes);

		LinkedHashMap<String, String> ors = rvo.getVennMatrix().getOrs();

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = rvo.getVennMatrix().getRecipientsVOHM();

		//add the source as the first row if not null
		List<String> sourceRow = createVennMatrixSourceRow(rvo, showOnlyCPF, showStatedPriorities, recipients, recipientsVOHM);

		if(sourceRow!=null) {
			tableContents.add(sourceRow);
			rvo.setHasSourceRow(true);
		} else
			rvo.setHasSourceRow(false);

		List<String> row = new ArrayList<String>();

		if(ors!=null && ors.size() > 0){
			for(String or : ors.keySet()) {

				counter = 0;
				row = new ArrayList<String>();
				
				if(!removeDACSectorCode())
					row.add(or);

				//OR Description
				row.add(ors.get(or));
				
				// Values per country
				for(String recipientCode : recipients.keySet()) {
					if (recipientsVOHM.containsKey(recipientCode)) {
						if ( showStatedPriorities ) {
							if ( recipientsVOHM.get(recipientCode).getStatedPrioritiesVO()!=null && recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or)) {
								StringBuffer statedPriorityBuf = new StringBuffer();
								StringBuffer agreedPriorityBuf = new StringBuffer();

								if(!recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities().isEmpty()){
									for(String statedPriority : recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities()) {
										statedPriorityBuf.append(statedPriority);
										statedPriorityBuf.append("<br/><br/>");
									}
									row.add(statedPriorityBuf.toString());
								} else 
									row.add(" <IMG src=\"images/decline.png\" BORDER=\"0\" title=\""+NOT_LISTED_MESSAGE+"\"/>  ");

								// add the Agreed Priorities info
								if(!recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getAgreedPriorities().isEmpty()){
									for(String agreedPriority : recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getAgreedPriorities()) {
										agreedPriorityBuf.append(agreedPriority);
										agreedPriorityBuf.append("<br/><br/>");
										//addedCpfPriority = true;
									}
									row.add(agreedPriorityBuf.toString());
								} else 
									row.add(" <IMG src=\"images/decline.png\" BORDER=\"0\" title=\""+NOT_LISTED_MESSAGE+"\"/>  ");

							}
							else
								row.add("");

						}


						if(recipientsVOHM.get(recipientCode).getIsCalculated()!=null && recipientsVOHM.get(recipientCode).getIsCalculated() && recipients.size() == 1){
							StringBuffer agreedPriorityBuf = new StringBuffer();

							if(recipientsVOHM.get(recipientCode).getStatedPrioritiesVO()!=null) {
								// add the Agreed Priorities info
								for(String agreedPriority : recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getAgreedPriorities()) {
									agreedPriorityBuf.append(agreedPriority);
									// TODO: if more than 1
									agreedPriorityBuf.append("<br/><br/>");
									//addedCpfPriority = true;
								}
								row.add(agreedPriorityBuf.toString());
							}
							else {
								row.add("");
							}

						}

						counter++;

					}
					else
						row.add("<IMG src=\"images/decline.png\" BORDER=\"0\" title=\""+NOT_LISTED_MESSAGE+"\"/> ");
				}


				tableContents.add(row);
			}

			// show totals		

			row = new ArrayList<String>();
			row.add("Totals");
			
			if(!removeDACSectorCode())
				row.add("");
			
			for(String recipientCode : recipients.keySet()) {
				if (recipientsVOHM.containsKey(recipientCode)) {
					if ( !showOnlyCPF ) {
						if(recipientsVOHM.get(recipientCode).getOrs()!=null)
							row.add(String.valueOf(recipientsVOHM.get(recipientCode).getOrs().size()));	
						else
							row.add(String.valueOf(0));

						if(recipientsVOHM.get(recipientCode).getAgreedOrs()!=null && !recipientsVOHM.get(recipientCode).getAgreedOrs().isEmpty())
							row.add(String.valueOf(recipientsVOHM.get(recipientCode).getAgreedOrs().size()));
						else
							row.add(String.valueOf(0));

					}
					else {
						if ( !recipientsVOHM.get(recipientCode).getIsCalculated() ) {
							if(recipientsVOHM.get(recipientCode).getOrs()!=null)
								row.add(String.valueOf(recipientsVOHM.get(recipientCode).getOrs().size()));	
							else
								row.add(String.valueOf(0));

							if(recipientsVOHM.get(recipientCode).getAgreedOrs()!=null && !recipientsVOHM.get(recipientCode).getAgreedOrs().isEmpty())
								row.add(String.valueOf(recipientsVOHM.get(recipientCode).getAgreedOrs().size()));
							else
								row.add(String.valueOf(0));

						}
					}
				}
			}

			// total
			if(recipients.size() > 1) {
				if(rvo.getVennMatrix().getOrs()!=null)
					row.add(String.valueOf(rvo.getVennMatrix().getOrs().size()));
				else
					row.add(String.valueOf(0));
			}

			tableContents.add(row);

		} else {
			row.add("No priorities available");
		}


		rvo.setTableContents(tableContents);

	}

	public static ContentPanel buildVennRecipientMatrixPanel(ADAMResultVO rvo) {
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		ContentPanel summaryPanel = new ContentPanel();
		summaryPanel.setScrollMode(Scroll.AUTO);				

		if(ADAMBoxMaker.countriesSelected.size() == 1)
			rvo.setTableColumnWidth("300");
		else
			rvo.setTableColumnWidth("250");

		panel.add(ADAMTableMaker.buildBigMatrix(rvo));

		return panel;
	}

}
