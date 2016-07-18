package org.fao.fenix.web.modules.dataviewer.client.control.faostat.selectors;




import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.selectors.FAOSTATSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FAOSTATSelectorController {
	
//	/** TODO: quick filter to don't show some subdomain...**/
//	System.out.println("STORE COUNT: " +newSubDomainCombo.getStore().getCount());
//	List<DWCodesModelData> removeCodes = new ArrayList<DWCodesModelData>();
//	for(int i = 0; i < newSubDomainCombo.getStore().getCount(); i++) {
//		String code = newSubDomainCombo.getStore().getAt(i).getCode();
//		System.out.println("HERE: " +newSubDomainCombo.getStore().getAt(i).getCode());
//		if ( code.equalsIgnoreCase("FT") || code.equalsIgnoreCase("TM")) {
//			removeCodes.add(newSubDomainCombo.getStore().getAt(i));
//		}
//	}
//	
//	for(DWCodesModelData code : removeCodes){
//		System.out.println("removing: " + code.getCode());
//		newSubDomainCombo.getStore().remove(code);
//	}
		
	
	
	/** TODO: this is has been modified for the PRESENTATION of 16/12/2011 ...make the black list dynamic in the DB **/
	public static void getSelectors(final FAOSTATSelectorPanel panel, final ComboBox<DWCodesModelData> combo, final ListStore<DWCodesModelData> store,DWFAOSTATQueryVO qvo, final String selectionType, final Boolean addSelectAll, final DWCodesModelData modelToSetAsSelected) {
		
		System.out.println("FILTER TYPE: "+ selectionType);
		
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					panel.getPanel().removeAll();
					store.removeAll();
					
					/** TODO: blacklist for domains to don't show...**/
					DataViewerBoxContent typeOfOutput = DataViewerBoxContent.valueOf(selectionType);
					if ( typeOfOutput.equals(DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_DOMAINS_FOR_GROUP)) {
						for(DWCodesModelData topcode : vo.getCodes()) {
							if ( !topcode.getCode().equalsIgnoreCase("FT") || !topcode.getCode().equalsIgnoreCase("TM")) {
								store.add(topcode);
							}
						}
					}
					
					else {
						for(DWCodesModelData topcode : vo.getCodes()) {
							store.add(topcode);
	
						}
					}
					
					if(combo!=null && combo.getStore().getCount() > 0) {
						if(modelToSetAsSelected!=null) {
							DWCodesModelData foundModel =  combo.getStore().findModel("code", modelToSetAsSelected.getCode());
							
							if(foundModel!=null)
								combo.setValue(foundModel);		
							else
								combo.setValue(combo.getStore().getAt(0));
						}	
						else
							combo.setValue(combo.getStore().getAt(0));
			
					}
					
					
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void getSelectors(final FAOSTATSelectorPanel panel, final CheckBoxListView<DWCodesModelData> checkBoxView, final ListStore<DWCodesModelData> store,DWFAOSTATQueryVO qvo, String selectionType, final Boolean addSelectAll, final DWCodesModelData modelToSetAsSelected) {
		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(DWFAOSTATResultVO vo) {
					panel.getPanel().removeAll();
					store.removeAll();
					
					
					for(DWCodesModelData topcode : vo.getCodes()) {
						store.add(topcode);
					}
					
					
					if(checkBoxView!=null && checkBoxView.getStore().getCount() > 0) {
						
						if(modelToSetAsSelected!=null) {
							DWCodesModelData foundModel = checkBoxView.getStore().findModel("code", modelToSetAsSelected.getCode());
							
							if(foundModel!=null)
								checkBoxView.setChecked(foundModel, true);
							
							else 
								System.out.println("----------- getSelectors: checkBoxView .... foundModel IS NULL "+modelToSetAsSelected.getLabel());
							
						}	
						
					}
					
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
