package org.fao.fenix.web.modules.amis.client.view.compare;

import java.util.HashMap;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.AMISFilterController;
import org.fao.fenix.web.modules.amis.client.view.compare.utils.AMISSelectorPanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISConstantsVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;

import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

public class AMISCompareSelectorMaker extends AMISSelectorPanel {

	AMISQueryVO qvo =  new AMISQueryVO();

	public AMISCompareSelectorMaker() {
		super();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		AMISConstantsVO.setLanguageSettings(qvo);

	}

   public ComboBox<AMISCodesModelData> buildComboBox(String title, String filterType, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, String sortingOrder) {
	    qvo.setTitle(title);
	    qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
	    qvo.setTypeOfOutput(filterType);
	    qvo.setSortingOrder(sortingOrder);

		qvo.setGetTotalAndList(isTotalAndList);
		//qvo.setSelectedDataset(AMISController.currentDatasetView.name());
		qvo.setSelectedDataset(null);
		return buildComboBox(qvo, boxId, addSelectAll, width, height, null);
	}

   public CheckBoxListView<AMISCodesModelData> buildCheckBoxList(AMISCompare compare, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, AMISCodesModelData modelToSetAsSelected) {

		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(selectionType);
		qvo.setDomains(getDefaultDomain());
		AMISConstantsVO.setLanguageSettings(qvo);
		qvo.setGetTotalAndList(isTotalAndList);
		//qvo.setSelectedDataset(AMISController.currentDatasetView.name());
		qvo.setSelectedDataset(null);

		return buildCheckBoxList(compare, qvo, boxId, addSelectAll, width, height, modelToSetAsSelected);
	}

   public CheckBoxListView<AMISCodesModelData> buildCheckBoxList(AMISCompare compare, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, AMISCodesModelData modelToSetAsSelected, HashMap<String, String> filterValues1,  String filterType1) {

	    qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(selectionType);
		if(filterValues1!=null && !filterValues1.isEmpty())
			AMISFilterController.addToFilters(qvo, filterValues1, filterType1, null);
		else
			AMISFilterController.clearFilter(qvo, filterType1);
		
		AMISConstantsVO.setLanguageSettings(qvo);
		qvo.setGetTotalAndList(isTotalAndList);
		//qvo.setSelectedDataset(AMISController.currentDatasetView.name());
		qvo.setSelectedDataset(null);
		
		return buildCheckBoxList(compare, qvo, boxId, addSelectAll, width, height, modelToSetAsSelected);
	}

  /** public CheckBoxListView<AMISCodesModelData> buildCheckBoxList(AMISCompare compare, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, HashMap<String, String> filterValues1,  String filterType1, HashMap<String, String> filterValues2,  String filterType2, AMISCodesModelData modelToSetAsSelected) {

	    qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(selectionType);
		if(filterValues1!=null && !filterValues1.isEmpty())
			AMISFilterController.addToFilters(qvo, filterValues1, filterType1, null);
		if(filterValues2!=null && !filterValues2.isEmpty())
			AMISFilterController.addToFilters(qvo, filterValues2, filterType2, null);
		AMISConstantsVO.setFAOSTATDBSettings(qvo);
		qvo.setGetTotalAndList(isTotalAndList);
		//qvo.setSelectedDataset(AMISController.currentDatasetView.name());
		qvo.setSelectedDataset(null);
		
		return buildCheckBoxList(qvo, boxId, addSelectAll, width, height, modelToSetAsSelected);
	}**/

	public HashMap<String, String> buildFilter(AMISCodesModelData selectedModel) {
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put(selectedModel.getCode(), selectedModel.getLabel());

		return filter;
	}

	private static HashMap<String, String> getDefaultDomain() {
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put("QC", "Crops");

		return domain;
	}
}
