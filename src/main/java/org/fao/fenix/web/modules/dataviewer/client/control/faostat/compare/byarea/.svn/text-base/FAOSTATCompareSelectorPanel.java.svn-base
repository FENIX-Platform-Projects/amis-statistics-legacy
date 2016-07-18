package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.selectors.FAOSTATSelectorController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeController;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.selectors.FAOSTATSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

public class FAOSTATCompareSelectorPanel extends FAOSTATSelectorPanel {
	
	DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
	
	public FAOSTATCompareSelectorPanel() {
		super();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
		FAOSTATConstants.setFAOSTATDBSettings(qvo);			
			
	}
	
	 public ComboBox<DWCodesModelData> buildComboBox(String title, HashMap<String, String> filterValues1, String filterType1, HashMap<String, String> filterValues2, String filterType2, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, DWCodesModelData modelToSetAsSelected) {
		    qvo.setTitle(title);
			qvo.setTypeOfOutput(selectionType);	
//			qvo.setResourceType(selectionType);	
			if(filterValues1!=null && !filterValues1.isEmpty())
				FAOSTATVisualizeController.addToFilters(qvo, filterValues1, filterType1, null);
			if(filterValues2!=null && !filterValues2.isEmpty())
				FAOSTATVisualizeController.addToFilters(qvo, filterValues2, filterType2, null);
			
			qvo.setGetTotalAndList(isTotalAndList);
			
			return buildComboBox(qvo, boxId, addSelectAll, width, height, modelToSetAsSelected);
		}
	 
   public ComboBox<DWCodesModelData> buildComboBox(String title, HashMap<String, String> filterValues, String filterType, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, DWCodesModelData modelToSetAsSelected) {
	    qvo.setTitle(title);
		qvo.setTypeOfOutput(selectionType);	
//		qvo.setResourceType(selectionType);	
		if(filterValues!=null && !filterValues.isEmpty()) 
			FAOSTATVisualizeController.addToFilters(qvo, filterValues, filterType, null);
		
		qvo.setGetTotalAndList(isTotalAndList);
		
		return buildComboBox(qvo, boxId, addSelectAll, width, height, modelToSetAsSelected);
	}
   

   public CheckBoxListView<DWCodesModelData> buildCheckBoxList(String title, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, DWCodesModelData modelToSetAsSelected) {
       
	    qvo.setTitle(title);
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);	
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(selectionType);	
//		qvo.setDomains(getDefaultDomain());
		FAOSTATConstants.setFAOSTATDBSettings(qvo);			
		qvo.setGetTotalAndList(isTotalAndList);
		
		return buildCheckBoxList(qvo, boxId, addSelectAll, width, height, modelToSetAsSelected);
	}
   

   public CheckBoxListView<DWCodesModelData> buildCheckBoxList(String title, String boxId, String selectionType, boolean isTotalAndList, boolean addSelectAll, String width, String height, HashMap<String, String> filterValues1,  String filterType1, HashMap<String, String> filterValues2,  String filterType2, DWCodesModelData modelToSetAsSelected) {
       
	    qvo.setTitle(title);
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);	
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(selectionType);	
		if(filterValues1!=null && !filterValues1.isEmpty())
			FAOSTATVisualizeController.addToFilters(qvo, filterValues1, filterType1, null);
		if(filterValues2!=null && !filterValues2.isEmpty())
			FAOSTATVisualizeController.addToFilters(qvo, filterValues2, filterType2, null);
		FAOSTATConstants.setFAOSTATDBSettings(qvo);			
		qvo.setGetTotalAndList(isTotalAndList);
		
		return buildCheckBoxList(qvo, boxId, addSelectAll, width, height, modelToSetAsSelected);
	}
	

	public HashMap<String, String> buildFilter(DWCodesModelData selectedModel) {
		HashMap<String, String> filter = new HashMap<String, String>();
		filter.put(selectedModel.getCode(), selectedModel.getLabel());
		
		return filter;
	}
	
//	private static HashMap<String, String> getDefaultDomain() {
//		HashMap<String, String> domain = new HashMap<String, String>();
//		domain.put("QC", "Crops");
//		
//		return domain;
//	}
}
