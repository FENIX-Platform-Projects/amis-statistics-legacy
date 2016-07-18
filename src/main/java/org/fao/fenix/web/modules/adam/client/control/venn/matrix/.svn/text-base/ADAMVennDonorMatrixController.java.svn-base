package org.fao.fenix.web.modules.adam.client.control.venn.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennDonorMatrix;
import org.fao.fenix.web.modules.adam.client.view.venn.matrix.ADAMVennMatrix;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
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
 * Creation of the Resource Partner (Donor) Priorities Matrices associated with the the Venn Diagram
 *
 */
public class ADAMVennDonorMatrixController extends ADAMVennController {

	public static ContentPanel buildVennDonorMatrixPanel(ADAMResultVO rvo) {
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		ContentPanel summaryPanel = new ContentPanel();
		summaryPanel.setScrollMode(Scroll.AUTO);				

		rvo.setGroupTable(true);
		rvo.setGroupingTableColumn(0);		

		panel.add(ADAMTableMaker.buildBigMatrix(rvo));

		return panel;
	}


	public static SelectionListener<ButtonEvent> buildVennDonorMatrix(final ADAMVennMatrix adamVennMatrix, final ADAMResultVO vo, final String position) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				buildVennDonorMatrixAgent(adamVennMatrix, vo, position);
			}
		};
	}


	public static SelectionListener<ButtonEvent> buildVennDonorMatrixListener(final ADAMVennMatrix adamVennMatrix, final ADAMResultVO vo, final String position) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				buildVennDonorMatrixAgent(adamVennMatrix, vo, position);
			}
		};
	}

	public static void buildVennDonorMatrixAgent(final ADAMVennMatrix adamVennMatrix, ADAMResultVO vo, final String position) {
		System.out.println("buildVennDonorMatrixAgent");


		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		//cleaned up list
		Map<String, String> gaulCodes = getCodes(gaulList);
		
		Map<String, String> donorList = ADAMBoxMaker.donorsSelected;
		//cleaned up list
		Map<String, String> donorCodes = getCodes(donorList);
		

		String donorPrioritiesDatasetCode = "";
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)){
			donorPrioritiesDatasetCode = "FPMIS_DONOR_KEYSOS";
		} else {
			donorPrioritiesDatasetCode = "DONOR_KEYSOS";
		}
			
		
		ADAMQueryVO qvo = ADAMQueryVOBuilder.getVennMatrix(ADAMBoxContent.VENN_DONOR_MATRIX, donorPrioritiesDatasetCode, gaulCodes, null, donorCodes);	
		
		String label = "Resource Partner Priorities for ";
		
		if(gaulCodes.size() == 1)
			label += gaulCodes.entrySet().iterator().next().getValue();
		else
			label += "Selected Countries";
		
		qvo.setTitle(label);
		qvo.setCurrentView(ADAMController.currentVIEW);

		ADAMServiceEntry.getInstance().askADAM(qvo, new AsyncCallback<ADAMResultVO>() {
			public void onSuccess(ADAMResultVO rvo) {

				System.out.println("buildVennDonorMatrixAgent getVennMatrix!!");

				System.out.println("rvo: " + rvo.getVennMatrix());

				//adamVennMatrix.getPanel().removeAll();
				ADAMVennDonorMatrix vennMatrix = new ADAMVennDonorMatrix();
				setVennMatrixDonors(rvo);

				System.out.println("setted donor matrix: ");

				//vennMatrix.build(rvo);
				
				RootPanel.get(position).add(vennMatrix.build(rvo));
				
				//adamVennMatrix.getPanel().add(vennMatrix.getPanel());
				//adamVennMatrix.getPanel().layout();
				//adamVennMatrix.getWindow().layout();
			}
			public void onFailure(Throwable arg0) {
			}
		});

	}


	private static void setVennMatrixDonors(ADAMResultVO rvo) {
		System.out.println("setVennMatrixDonors");
		setVennMatrixDonorsTableHeaders(rvo);

		setVennMatrixDonorsTableContents(rvo);

	}


	private static void setVennMatrixDonorsTableHeaders(ADAMResultVO rvo) { 
		System.out.println("setVennMatrixDonorsTableHeaders");

		List<String> tableHeaders = new ArrayList<String>();

		LinkedHashMap<String, String> donors = rvo.getVennMatrix().getDonors();

		if(ADAMBoxMaker.countriesSelected.size() > 1)
			tableHeaders.add("Recipient");

		//Following two columns
		setVennMatrixORDACTableHeaders(tableHeaders);

		for(String donor : donors.keySet()) {
			tableHeaders.add(donors.get(donor));
		} 

		rvo.setTableHeaders(tableHeaders);

	}

	private static void setVennMatrixDonorsTableContents(ADAMResultVO rvo) { 

		System.out.println("setVennMatrixDonorsTableContents");
		List<List<String>> tableContents = new ArrayList<List<String>>();


		Map<String, String> gaulList = ADAMBoxMaker.countriesSelected;
		//cleaned up list
		Map<String, String> codes = getCodes(gaulList);

		LinkedHashMap<String, String> recipients = ADAMController.sortByValues(codes);


		LinkedHashMap<String, String> donors = rvo.getVennMatrix().getDonors();

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = rvo.getVennMatrix().getRecipientsVOHM();



		// for every country get its ORs and check which donor have that OR
		for(String recipientcode : recipients.keySet()) {


			if ( recipientsVOHM.containsKey(recipientcode)) {

				if ( !recipientsVOHM.get(recipientcode).getOrs().isEmpty()) {
					// ORs
					TreeMap<String, String> ors = ADAMController.sortByKeys(recipientsVOHM.get(recipientcode).getOrs());
					//			System.out.println("donors: " +  donors.keySet());
					for( String or : ors.keySet()) {
						System.out.println("country: " + recipientcode + " OR: " + or + " VALUE = "+ors.get(or));
						List<String> row = new ArrayList<String>();

						//country
						if(codes.size() > 1)
							row.add(recipients.get(recipientcode));

						// ORs
						if(!removeDACSectorCode())
							row.add(or);

						//OR Description
						row.add(ors.get(or));

						// for every donor in the column check if exist and has that or
						for( String donorcode : donors.keySet() ) {
							//					System.out.println("donorcode: " + donorcode);
							if ( recipientsVOHM.get(recipientcode).getDonorsVO().containsKey(donorcode)) {
								//						System.out.println("exist: " + donorcode);
								if (  recipientsVOHM.get(recipientcode).getDonorsVO().get(donorcode).getOrs().containsKey(or)) {
									    String title = vennMatrixOnHoverTitle(codes, recipients.get(recipientcode), or, ors.get(or));
									    row.add("<IMG src=\"images/accept.png\" BORDER=\"0\" title=\""+title+"\"/> ");    	
								}
								else {
									row.add("");
								}
							}
							else {
								row.add("");
							}
						}

						//				System.out.println("row: " + row);
						tableContents.add(row);
					}
				}
			}
		}

		//		System.out.println("tableContents: " + tableContents);

		rvo.setTableContents(tableContents);
	}
	
	
	
}
