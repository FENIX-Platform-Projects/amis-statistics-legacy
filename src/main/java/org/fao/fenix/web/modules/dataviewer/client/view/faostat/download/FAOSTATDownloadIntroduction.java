package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class FAOSTATDownloadIntroduction {
	
	ContentPanel panel;
	
	public FAOSTATDownloadIntroduction() {
	} 

	
	public ContentPanel build(final FAOSTATDownload download) {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("padding", "0px");
		
		panel.add(addGuide(FAOSTATConstants.faostatLanguage));
		return panel;
	}
	
	
    private Html addGuide(String lang) {
		
		Html html = new Html();
		String div = "<div><img src='dataviewer-images/download-guide/guide_e2.png' border='0'/></div>";
//		String div = "<div><img src='dataviewer-images/download-guide/guide_e.png' border='0'/></div>";
		html.setHtml(div);
		
		
		return html;
	}

}
