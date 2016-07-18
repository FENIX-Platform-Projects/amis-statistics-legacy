package org.fao.fenix.web.modules.adam.client.control.venn.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennChannelMatrix;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennMatrix;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennRecipientsVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * 
 * Creation of the Channel (FAO) Priorities Matrix associated with the the Venn Diagram
 *
 */
public class ADAMVennChannelMatrixController extends ADAMVennController {
	
	public static SelectionListener<ButtonEvent> buildVennMatrixChannel(final ADAMVennMatrix adamVennMatrix, final ADAMResultVO vo, final Boolean showOnlyCPF,  final Boolean showStatedPriorities, final String position) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				buildVennMatrixChannelAgent(adamVennMatrix, vo, showOnlyCPF, showStatedPriorities, position);
			}
		};
	}


	public static void buildVennMatrixChannelAgent(final ADAMVennMatrix adamVennMatrix, ADAMResultVO vo, final Boolean showOnlyCPF,  final Boolean showStatedPriorities, final String position) {
		System.out.println("buildVennChannelMatrixAgent");
		

		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		//cleaned up list
		Map<String, String> codes = getCodes(gaulList);
		
		HashMap<String, String> channels = new HashMap<String, String>();
		channels.put("41301", "FAO");
		
		
		final ADAMQueryVO qvo = ADAMQueryVOBuilder.getVennMatrix(ADAMBoxContent.VENN_CHANNEL_MATRIX, "CHANNEL_KEYSOS", codes, channels, null);	
		qvo.setTitle("Countries' Agreed Priorities with FAO");
		qvo.setCurrentView(ADAMController.currentVIEW);
		
			ADAMServiceEntry.getInstance().askADAM(qvo, new AsyncCallback<ADAMResultVO>() {
				public void onSuccess(ADAMResultVO rvo) {
		
				//	adamVennMatrix.getPanel().removeAll();
					ADAMVennChannelMatrix vennMatrix = new ADAMVennChannelMatrix();
					
					setVennMatrixChannels(rvo, showOnlyCPF, showStatedPriorities);
					
					
					//vennMatrix.build(rvo, qvo);
					
					RootPanel.get(position).add(vennMatrix.build(rvo));
					
					//adamVennMatrix.getPanel().add(vennMatrix.getPanel());
					//adamVennMatrix.getPanel().layout();
					//adamVennMatrix.getWindow().layout();
				}
				public void onFailure(Throwable arg0) {
				}
			});
		
	}
	
	public static void buildVennMatrixChannelReloadAgent(ADAMVennMatrix vennMatrix, ADAMResultVO rvo, Boolean showOnlyCPF,  Boolean showStatedPriorities) {
		setVennMatrixChannels(rvo, showOnlyCPF, showStatedPriorities);
		
		vennMatrix.getMatrixPanel().removeAll();
		
		System.out.println("buildVennMatrixChannelReloadAgent()");
//		vennMatrix.getMatrixPanel().add(new Html("asioduaisudh"));
		
		vennMatrix.getMatrixPanel().add(buildVennChannelMatrixPanel(rvo));

		vennMatrix.getMatrixPanel().layout();
	}
	
	
	private static void setVennMatrixChannels(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities) {
		
		setVennMatrixChannelsTableHeaders(rvo, showOnlyCPF, showStatedPriorities);
		
		setVennMatrixChannelsTableContents(rvo, showOnlyCPF, showStatedPriorities);
				
	}
	
	private static void setVennMatrixChannelsTableHeaders(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities) { 
		
		List<String> tableHeaders = new ArrayList<String>();
		List<String> highlightedColumns = new ArrayList<String>();
		
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		
	    Map<String, String> codes = new LinkedHashMap<String, String>();
		
		if(rvo.getConvertedRecipients()!=null && !rvo.getConvertedRecipients().isEmpty()){
			codes.clear();
			codes = rvo.getConvertedRecipients();
		} else{
			codes.clear();
			codes = getCodes(gaulList);
			
			/*for(String code : gaulList.keySet()) {
				String c = getCode(code);
				codes.put(c, gaulList.get(code));
			}	*/
		}
		
		LinkedHashMap<String, String> recipients = ADAMController.sortByValues(codes);
		
		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = rvo.getVennMatrix().getRecipientsVOHM();

		//First two columns
		setVennMatrixORDACTableHeaders(tableHeaders);
		
		for(String recipient : recipients.keySet()) {
				// add if isCalculated
				if ( recipientsVOHM.containsKey(recipient)) {
					//ADAMVennRecipientsVO vo = recipientsVOHM.get(recipient);
					
					//if ( recipientsVOHM.get(recipient).getIsSF() ) {
						//if ( !showOnlyCPF ) {
						//	tableHeaders.add(recipients.get(recipient) + "<br/>(Strategic Framework)");
						//	highlightedColumns.add("Strategic Framework");
						//}
					//}
					//else {
						tableHeaders.add("FAO & Government of "+recipients.get(recipient)+ " Agreed Priorities");
						
						highlightedColumns.add("CPF");
						
						/**if (showStatedPriorities && ADAMBoxMaker.countriesSelected.size() == 1) {									
							if ( vo.getIsCalculated()!= null && vo.getIsCalculated() ) {
									tableHeaders.add(recipients.get(recipient) + "<br/> (Calculated)");		
							}		
							 else {
								tableHeaders.add("Government of "+recipients.get(recipient)+ " National Priorities");
							}
					    }**/
					//}
					
				}
				else
					tableHeaders.add(recipients.get(recipient));
		} 
		
		if(recipients.size() > 1)
			tableHeaders.add("Totals");
		
		rvo.setTableHeaders(tableHeaders);
		rvo.setHighlightedTableColumns(highlightedColumns);
	}
	
	private static void setVennMatrixChannelsTableContents(ADAMResultVO rvo, Boolean showOnlyCPF, Boolean showStatedPriorities ) { 
		
		List<List<String>> tableContents = new ArrayList<List<String>>();
		
		Integer counter = 0;
		
		/*Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		
		Map<String, String> codes = new LinkedHashMap<String, String>();
		
		for(String code : gaulList.keySet()) {
			String c = getCode(code);
			codes.put(c, gaulList.get(code));
		}
		*/
		
		Map<String, String> codes = new LinkedHashMap<String, String>();
		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		
		if(rvo.getConvertedRecipients()!=null && !rvo.getConvertedRecipients().isEmpty()){
			codes.clear();
			codes = rvo.getConvertedRecipients();
		} else{
			codes.clear();
			
			codes = getCodes(gaulList);
			
			/*for(String code : gaulList.keySet()) {
				String c = getCode(code);
				codes.put(c, gaulList.get(code));
			}	*/
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
			
			// ORs
			if(!removeDACSectorCode())
				row.add(or);
						
			// OR Description
			row.add(ors.get(or));
			
			
			// Values per country
			if ( !showOnlyCPF ) {
				for(String recipientCode : recipients.keySet()) {
					if (recipientsVOHM.containsKey(recipientCode)) {
						if (recipientsVOHM.get(recipientCode).getOrs()!=null && recipientsVOHM.get(recipientCode).getOrs().containsKey(or)) {
							Boolean addedStatedPriority = false;
							if ( showStatedPriorities ) {
							//	System.out.println("recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or): " + recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or));
								if ( recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or)) {
									StringBuffer statedPriorityBuf = new StringBuffer();
									System.out.println("recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities(): " + recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities());
									for(String statedPriority : recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities()) {
										statedPriorityBuf.append(statedPriority);
										statedPriorityBuf.append("<br/><br/>");
									}
									row.add(statedPriorityBuf.toString());
									
									
									addedStatedPriority = true;
								}
						
							}
							
							//previously else if
							if ( !addedStatedPriority ) {
								String title = vennMatrixOnHoverTitle(codes, recipients.get(recipientCode), or, ors.get(or));
							    row.add("<IMG src=\"images/accept.png\" BORDER=\"0\" title=\""+title+"\"/> ");    	
							    //row.add("<IMG src=\"images/accept.png\" BORDER=\"0\" title=\""+ors.get(or)+"\"/> ");
							}
							
							counter++;
						}
						else{
							//if  ( recipientsVOHM.get(recipientCode).getIsSF()) {
							//	row.add(" <IMG src=\"images/accept.png\" BORDER=\"0\" />  ");
							//	counter++;
							//}
							//else
								row.add(" ");
						}
					}
					else
						row.add(" ");
				}
			}
			else {
				// that one have a CPF
				for(String recipientCode : recipients.keySet()) {
					if ( !recipientsVOHM.get(recipientCode).getIsSF() ) {
						if (recipientsVOHM.containsKey(recipientCode)) {
							if (recipientsVOHM.get(recipientCode).getOrs().containsKey(or)) {
								Boolean addedStatedPriority = false;
								if ( showStatedPriorities ) {
									//System.out.println("recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or): " + recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or));
									if ( recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().containsKey(or)) {
										StringBuffer statedPriorityBuf = new StringBuffer();
										//System.out.println("recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities(): " + recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities());
										for(String statedPriority : recipientsVOHM.get(recipientCode).getStatedPrioritiesVO().get(or).getStatedPriorities()) {
											statedPriorityBuf.append(statedPriority);
											statedPriorityBuf.append("<br/><br/>");
										}
										row.add(statedPriorityBuf.toString());
										
										addedStatedPriority = true;
									}
								}
								
								if ( !addedStatedPriority ) {
									//row.add(" <IMG src=\"images/accept.png\" BORDER=\"0\" />  ");
									String title = vennMatrixOnHoverTitle(codes, recipients.get(recipientCode), or, ors.get(or));
								    row.add("<IMG src=\"images/accept.png\" BORDER=\"0\" title=\""+title+"\"/> ");    	
	
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
			
			// total
			row.add(String.valueOf(counter));
	
			tableContents.add(row);
		}

		
		// show totals		
		row = new ArrayList<String>();
		row.add("Totals");
		// ORs
		if(!removeDACSectorCode())
			row.add("");
		
		for(String recipientCode : recipients.keySet()) {
			if (recipientsVOHM.containsKey(recipientCode)) {				
				if ( !showOnlyCPF ) {
					if(!recipientsVOHM.get(recipientCode).getIsSF()){
						row.add(String.valueOf(recipientsVOHM.get(recipientCode).getOrs().size()));	
					} 
					else {
						row.add(String.valueOf(rvo.getVennMatrix().getOrs().size()));
					}	
				}
				else {
					if(!recipientsVOHM.get(recipientCode).getIsSF()){
						row.add(String.valueOf(recipientsVOHM.get(recipientCode).getOrs().size()));
				    } 
				}
			} 
		}

		row.add(String.valueOf(rvo.getVennMatrix().getOrs().size()));		
		tableContents.add(row);
		
		rvo.setTableContents(tableContents);
		
	}
	
	public static ContentPanel buildVennChannelMatrixPanel(ADAMResultVO rvo) {
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
