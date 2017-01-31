package org.fao.fenix.web.modules.amis.client.control.objects;

import org.fao.fenix.web.modules.amis.client.view.utils.layout.LoadingPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmaker.AMISBoxMakerPivotTable;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AMISFaostatPivotTableController {
	
public static void addPivotTable(final ContentPanel panel, final AMISQueryVO qvo, final String width, final String height) {
		
		panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));

		panel.layout();
		
		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
				
		
				public void onSuccess(AMISResultVO rvo) {

					panel.removeAll();	
	
					panel.setWidth(width);
//			    	panel.setHeight(FAOSTATConstants.calculateHeightWithBoxMenu(height));
					panel.add(AMISBoxMakerPivotTable.build(qvo, rvo, width, height));
	
					panel.layout();
					
				}
				
				public void onFailure(Throwable arg0) {
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

public static void addPivotExportTable(final ContentPanel panel, final AMISQueryVO qvo, AMISResultVO rvo, final String width, final String height) {
	
	//panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));

	//panel.layout();
	
	try {
//		    	panel.setHeight(FAOSTATConstants.calculateHeightWithBoxMenu(height));
				panel.add(AMISBoxMakerPivotTable.build(qvo, rvo, width, height));

				panel.layout();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}


}
