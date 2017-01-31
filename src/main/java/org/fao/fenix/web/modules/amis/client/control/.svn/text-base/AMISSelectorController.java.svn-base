package org.fao.fenix.web.modules.amis.client.control;


import org.fao.fenix.web.modules.amis.client.view.home.AMISHome;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;


import com.extjs.gxt.ui.client.store.ListStore;

import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AMISSelectorController {
	
	public static void getSelectors(final AMISHome home, final ComboBox<AMISCodesModelData> combo, final ListStore<AMISCodesModelData> store, AMISQueryVO qvo, final AMISCodesModelData modelToSetAsSelected) {
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				
				@SuppressWarnings("unchecked")
				public void onSuccess(AMISResultVO vo) {
//					home.getPanel().removeAll();
					store.removeAll();
					
					
					for(AMISCodesModelData topcode : vo.getCodes()) {
						store.add(topcode);

					}
					
					if(combo!=null && combo.getStore().getCount() > 0) {
						if(modelToSetAsSelected!=null) {
							AMISCodesModelData foundModel =  combo.getStore().findModel("code", modelToSetAsSelected.getCode());
							
							if(foundModel!=null)
								combo.setValue(foundModel);		
							else
								combo.setValue(combo.getStore().getAt(0));
						}	
						else
							combo.setValue(combo.getStore().getAt(0));
			
					}
					home.getPanel().layout();
					
				}
				
				public void onFailure(Throwable arg0) {
	
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
