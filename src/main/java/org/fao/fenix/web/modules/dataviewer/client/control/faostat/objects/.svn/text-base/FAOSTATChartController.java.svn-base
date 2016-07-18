package org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker.FAOSTATBoxMakerChart;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FAOSTATChartController {
	
public static void addChart(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final String width, final String height) {
		
		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));

		
		// this is called to get the cached RVO (if it exist and doesn't have to be launched an async call 
		if ( qvo.getRvo() != null && !qvo.getRunAsyncCall()) {
			
			createChart(panel, qvo, qvo.getRvo(), width, height);			
		}
	
		// otherwise call the asyncall
		else {
			try {
				DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
					public void onSuccess(DWFAOSTATResultVO rvo) {
						qvo.setRvo(rvo);
						createChart(panel, qvo, rvo, width, height);
					}	
					public void onFailure(Throwable arg0) {
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void createChart(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final DWFAOSTATResultVO rvo, final String width, final String height) {
		panel.removeAll();	
		
		panel.setWidth(width);
//		panel.setHeight(FAOSTATConstants.calculateHeightWithBoxMenu(height));

		panel.add(FAOSTATBoxMakerChart.buildChart(qvo, rvo, width, height));
		panel.layout();
	}
}
