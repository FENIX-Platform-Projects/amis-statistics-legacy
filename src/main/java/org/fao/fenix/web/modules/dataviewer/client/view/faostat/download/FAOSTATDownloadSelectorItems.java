package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class FAOSTATDownloadSelectorItems {
	
	FAOSTATDownloadSelectorPanel selectorPanel;
	
	ContentPanel panel;  
	
	ClickHtml items;
	
	ClickHtml items_aggregations;
	
	ClickHtml all;
	
//	TabPanel panel;  

	public FAOSTATDownloadSelectorItems() {
//		panel = new TabPanel();
		panel = new ContentPanel();  
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);
		
		selectorPanel = new FAOSTATDownloadSelectorPanel();
	} 
	
	public ContentPanel build(DWCodesModelData domainFilter, String width, String height, Boolean addItemsAggregationsList, Boolean selectAll) {
		System.out.println("buildit");
		HorizontalPanel p = new HorizontalPanel();
		
		defaultStyles();

		p.add(items);
		p.add(addHSpace(5));
		items.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.DOWNLOAD_GET_ITEMS_ONLY.toString(), false, false, width, height));
		
		if ( addItemsAggregationsList ) {
			p.add(items_aggregations);
			items_aggregations.hide();
			items_aggregations.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.DOWNLOAD_GET_AGGREGATES_ONLY.toString(), true, false, width, height));
			FAOSTATDownloadController.buildTabCheck(items_aggregations, domainFilter, DataViewerBoxContent.DOWNLOAD_GET_AGGREGATES_ONLY.toString());
			p.add(addHSpace(5));
			p.add(all);
			all.hide();
			all.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.DOWNLOAD_GET_ITEMS_AND_AGGREGATES.toString(), true, false, width, height));
//			FAOSTATDownloadController.buildTabCheck(all, domainFilter, DataViewerBoxContent.DOWNLOAD_GET_ITEMS_AND_AGGREGATES.toString());
			FAOSTATDownloadController.buildTabCheck(all, domainFilter, DataViewerBoxContent.DOWNLOAD_GET_AGGREGATES_ONLY.toString());
		}
		

		



		
		
		panel.add(p);
		
		panel.add(selectorPanel.buildItems(domainFilter, null, DataViewerBoxContent.DOWNLOAD_GET_ITEMS_ONLY.toString(), false, false, selectAll, width, height));
		
		setListners();
		
		return panel;
	}

	private void defaultStyles(){ 
		items = new ClickHtml();
		items.setHtml(FAOSTATLanguage.print().items());
		items.addStyleName("download_tab_selected");
		
		items_aggregations = new ClickHtml();
		items_aggregations.setHtml(FAOSTATLanguage.print().itemsAggregated());
		items_aggregations.addStyleName("download_tab_unselected");
		
		all = new ClickHtml();
		all.setHtml(FAOSTATLanguage.print().all());
		all.addStyleName("download_tab_unselected");
	}
	
	private void setListners() {
		items_aggregations.addListener(Events.OnClick, setTab(items_aggregations));
		all.addListener(Events.OnClick, setTab(all));
		items.addListener(Events.OnClick, setTab(items));
	}
	
	private void resetStyles() {
		items.removeStyleName("download_tab_selected");
		items.removeStyleName("download_tab_unselected");
		items.addStyleName("download_tab_unselected");
		
		items_aggregations.removeStyleName("download_tab_unselected");
		items_aggregations.removeStyleName("download_tab_selected");
		items_aggregations.addStyleName("download_tab_unselected");
		
		all.removeStyleName("download_tab_unselected");
		all.removeStyleName("download_tab_selected");
		all.addStyleName("download_tab_unselected");
	} 
	

	
	
	
	public Listener<ComponentEvent> setTab(final ClickHtml html) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				resetStyles();
				
				html.removeStyleName("download_tab_unselected");
				html.addStyleName("download_tab_selected");
			}
		};
	}


	
	private HorizontalPanel addHSpace(Integer width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(width);
		return p;
	}

	public FAOSTATDownloadSelectorPanel getSelectorPanel() {
		return selectorPanel;
	}
	
	
}

