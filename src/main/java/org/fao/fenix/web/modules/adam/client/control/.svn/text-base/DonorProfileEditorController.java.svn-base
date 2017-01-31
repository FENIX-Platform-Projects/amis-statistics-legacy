package org.fao.fenix.web.modules.adam.client.control;

import org.fao.fenix.web.modules.adam.client.view.dataentry.DonorProfileDataEntryPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DonorProfileEditorController extends ADAMController{

	public static void getDonorProfile(final ContentPanel dataEntryPanel, final Window dataEntryWindow, final String donorcode, final String donorName) {
		final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loading(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();

		String selectedDataset = ADAMController.currentlySelectedDatasetCode.toString();
		
		ADAMServiceEntry.getInstance().getDonorProfileVO (donorcode, ADAMBoxMaker.donorList.getValue().getGaulLabel(), selectedDataset, new AsyncCallback<ADAMDonorProfileVO>() {
			public void onSuccess(final ADAMDonorProfileVO vo) {
				
				ADAMDonorProfileVO updatedVo = DonorProfileController.addSectionTitles(vo);
				   
				DonorProfileDataEntryPanel donorProfileDataEntryPanel = new DonorProfileDataEntryPanel();	
				dataEntryPanel.add(donorProfileDataEntryPanel.build(updatedVo));
				dataEntryPanel.setScrollMode(Scroll.ALWAYS);
				dataEntryPanel.layout();
				
				//dataEntryWindow.getLayout().layout();
				
				loadingWindow.destroyLoadingBox();


			}
			public void onFailure(Throwable caught) {
				Info.display("Retrieved Donor Profile: Failed", "Error in retrieving Donor Profile", caught.getLocalizedMessage());
				loadingWindow.destroyLoadingBox();
			}
		});


	} 

}