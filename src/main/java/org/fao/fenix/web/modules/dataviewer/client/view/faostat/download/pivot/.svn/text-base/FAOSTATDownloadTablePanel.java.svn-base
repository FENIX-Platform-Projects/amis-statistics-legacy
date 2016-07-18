package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class FAOSTATDownloadTablePanel {
	
	LinkedHashMap<String, String> codes;
	
	FAOSTATDimensionConstant dimension;

	ContentPanel panel;
	
	ContentPanel tablesPanel;
	
	HorizontalPanel viewMorePanel;
	
	Integer currentIndex = 0;
	
	Integer maxResults = 4;
	
	DWFAOSTATQueryVO qvo;
	
	public FAOSTATDownloadTablePanel() {
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
		panel.add(DataViewerClientUtils.addVSpace(10));
		panel.add(buildViewMore());
		panel.add(DataViewerClientUtils.addVSpace(10));
		
		return panel;
	}
	
	public HorizontalPanel buildViewMore() {
		viewMorePanel  = new HorizontalPanel();
		viewMorePanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		
		IconButton icon = new IconButton("arrow-right_disabled");
		
		ClickHtml viewMore = new ClickHtml();
		viewMore.setHtml("<div class='view_more'>"+FAOSTATLanguage.print().viewMore()+"</div>");
		viewMore.setToolTip(FAOSTATLanguage.print().clickToLoadOtherTables());
		
		viewMore.addListener(Events.OnClick, FAOSTATDownloadController.viewMoreTables(this));
		
		viewMorePanel.add(DataViewerClientUtils.addHSpace(10));
		viewMorePanel.add(icon);
		viewMorePanel.add(DataViewerClientUtils.addHSpace(5));
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

	

	public FAOSTATDimensionConstant getDimension() {
		return dimension;
	}

	public void setDimension(FAOSTATDimensionConstant dimension) {
		this.dimension = dimension;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public DWFAOSTATQueryVO getQvo() {
		return qvo;
	}

	public void setQvo(DWFAOSTATQueryVO qvo) {
		this.qvo = qvo;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public HorizontalPanel getViewMorePanel() {
		return viewMorePanel;
	}
	
	
	
	
	
	
}
