package org.fao.fenix.web.modules.amis.client.view.download.table;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class AMISDownloadTablePanel {
	
	LinkedHashMap<String, String> codes;
	
	AMISConstants dimension;

	ContentPanel panel;
	
	ContentPanel tablesPanel;
	
	HorizontalPanel viewMorePanel;
	
	Integer currentIndex = 0;
	
	Integer maxResults = 4;
	
	AMISQueryVO qvo;
	
	public AMISDownloadTablePanel() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		tablesPanel = new ContentPanel();
		tablesPanel.setBodyBorder(false);
		tablesPanel.setHeaderVisible(false);
		
		viewMorePanel = new HorizontalPanel();
		
		codes = new LinkedHashMap<String, String>();
	}
	
	public ContentPanel build() {
		panel.removeAll();
		tablesPanel.removeAll();
		

		panel.add(tablesPanel);
		panel.add(FormattingUtils.addVSpace(10));
		panel.add(buildViewMore());
		panel.add(FormattingUtils.addVSpace(10));
		
		return panel;
	}
	
	public HorizontalPanel buildViewMore() {
		viewMorePanel  = new HorizontalPanel();
		viewMorePanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		
		IconButton icon = new IconButton("arrow-right_disabled");
		
		ClickHtml viewMore = new ClickHtml();
		viewMore.setHtml("<div class='view_more'>View more</div>");
		viewMore.setToolTip("Click to load other tables");
		
		viewMore.addListener(Events.OnClick, AMISDownloadController.viewMoreTables(this));
		
		viewMorePanel.add(FormattingUtils.addHSpace(10));
		viewMorePanel.add(icon);
		viewMorePanel.add(FormattingUtils.addHSpace(5));
		viewMorePanel.add(viewMore);
		
		viewMorePanel.hide();
		
		return viewMorePanel;
	}
	
	public void updateIndex(Integer index) {
		currentIndex = index;
		if ( currentIndex < codes.size() ) {
			viewMorePanel.show();
		}
		else 
			viewMorePanel.hide();
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public LinkedHashMap<String, String> getCodes() {
		return codes;
	}

	public ContentPanel getTablesPanel() {
		return tablesPanel;
	}

	

	public AMISConstants getDimension() {
		return dimension;
	}

	public void setDimension(AMISConstants dimension) {
		this.dimension = dimension;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public AMISQueryVO getQvo() {
		return qvo;
	}

	public void setQvo(AMISQueryVO qvo) {
		this.qvo = qvo;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public HorizontalPanel getViewMorePanel() {
		return viewMorePanel;
	}
	
	
	
	
	
	
}
