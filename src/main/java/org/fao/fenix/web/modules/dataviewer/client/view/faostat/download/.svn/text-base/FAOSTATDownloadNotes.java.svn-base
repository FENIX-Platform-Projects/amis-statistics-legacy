package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import java.util.HashMap;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;

public class FAOSTATDownloadNotes {
	
	ContentPanel panel;  

	public FAOSTATDownloadNotes() {
		panel = new ContentPanel();  
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);		
	} 
	
	public ContentPanel build(DWCodesModelData domainFilter) {

		panel.add(build(domainFilter, DataViewerBoxContent.TABLE.toString()));
		
		return panel;
	}
	
	
	public ContentPanel build(DWCodesModelData domainFilter, String selectionType) {

		System.out.println("FAOSTATDownloadNotes @ build(DWCodesModelData domainFilter, String selectionType)");
		
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.DOMAIN_NOTES.toString());
		qvo.setTypeOfOutput(selectionType);		
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setDomains(buildDomain(domainFilter));
		//qvo.setGetTotalAndList(isTotalAndList);
			
		System.out.println("calling FAOSTATDownloadController.getDomainNotes(this, qvo);");
		FAOSTATDownloadController.getDomainNotes(this, qvo);

		return panel;
	}

	private HashMap<String, String> buildDomain(DWCodesModelData domainFilter) {
		HashMap<String, String> domain = new HashMap<String, String>();
		domain.put(domainFilter.getCode().trim(), domainFilter.getLabel());
		
		return domain;
	}
	
	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}
	
	
}

