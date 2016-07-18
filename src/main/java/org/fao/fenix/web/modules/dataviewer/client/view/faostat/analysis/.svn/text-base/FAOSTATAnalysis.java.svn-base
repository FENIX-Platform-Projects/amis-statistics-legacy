package org.fao.fenix.web.modules.dataviewer.client.view.faostat.analysis;


import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;


public class FAOSTATAnalysis {
	


	public FAOSTATAnalysis() {
	}
	
	public void build() {
		

		
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		
		qvo.setLanguage(LocaleInfo.getCurrentLocale().getLocaleName());
		qvo.setTypeOfOutput(DataViewerBoxContent.R_GWT.toString());
		qvo.setOutput(DataViewerBoxContent.R_GWT.toString());

		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				public void onSuccess(DWFAOSTATResultVO rvo) {			
//					String iframe = "<iframe src=\"http://ldvapp07.fao.org:8030/r-gwt/gui/GUI.html?locale="+ LocaleInfo.getCurrentLocale().getLocaleName() +"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";
					String iframe = "<iframe src=\"" + rvo.getUrl() +"\"  frameBorder=\"0\" scrolling=\"no\" style=\"width: 1100px;  height: 780px;\">Your browser does not support iframes.</iframe>";

					System.out.println("iframe: " + iframe);
					Html html = new Html(iframe);
					RootPanel.get("MAIN_CONTENT").add(html);
				}
				public void onFailure(Throwable arg0) {
					
				}
			});
		} catch (FenixGWTException e) {
			e.printStackTrace();
		}
	}
}
