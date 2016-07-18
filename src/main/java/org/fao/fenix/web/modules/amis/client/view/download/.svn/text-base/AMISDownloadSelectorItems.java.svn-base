package org.fao.fenix.web.modules.amis.client.view.download;

import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class AMISDownloadSelectorItems {
	
	AMISDownloadSelectorPanel selectorPanel;
	
	ContentPanel panel;  
	
	ClickHtml items;
	
	ClickHtml items_aggregations;
	
//	TabPanel panel;  

	public AMISDownloadSelectorItems() {
//		panel = new TabPanel();
		panel = new ContentPanel();  
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);
		
		selectorPanel = new AMISDownloadSelectorPanel();
	} 
	
	public ContentPanel build(AMISDownload download, AMISCodesModelData domainFilter, String width, String height) {
		System.out.println("buildit");
		HorizontalPanel p = new HorizontalPanel();
		defaultStyles();

		p.add(items);
		p.add(addHSpace(5));
		p.add(items_aggregations);
		
		items_aggregations.hide();

        if(AMISDownloadController.isFoodBalance)
            items.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, domainFilter, AMISConstants.AMIS_FOOD_BALANCE_PRODUCTS.toString(), false, true, width, height));
        else
		    items.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, domainFilter, AMISConstants.AMIS_PRODUCTS.toString(), false, false, width, height));


        items_aggregations.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_ITEM_AGGREGATIONS.toString(), true, false, width, height));
	
		

		
//		p.add(FAOSTATDownloadController.buildTab(selectorPanel, domainFilter,"Items", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM.toString(), false, false, width, height, true));
//		p.add(addHSpace(5));
//		
//		/** TODO: change it.. **/
		//AMISFaostatDownloadController.buildTabCheck(items_aggregations, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_ITEM_AGGREGATIONS.toString());
////		p.add(buildTab(domainFilter,  "Items Aggr.", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_AGGREGATIONS.toString(), true, false, width, height));
		
		panel.add(p);

        if(AMISDownloadController.isFoodBalance)
            panel.add(selectorPanel.build(download, domainFilter, null, AMISConstants.AMIS_FOOD_BALANCE_PRODUCTS.toString(), false, true, width, height));
         else
            panel.add(selectorPanel.build(download, domainFilter, null, AMISConstants.AMIS_PRODUCTS.toString(), false, false, width, height));


        setListners();
		
		return panel;
	}


    public ContentPanel build(AMISDownload download, String width, String height) {
        System.out.println("buildit");
        HorizontalPanel p = new HorizontalPanel();
        defaultStyles();

        p.add(items);
        p.add(addHSpace(5));
        p.add(items_aggregations);

        items_aggregations.hide();

        if(AMISDownloadController.isFoodBalance)
            items.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, AMISConstants.AMIS_FOOD_BALANCE_PRODUCTS.toString(), false, false, width, height));
        else
            items.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, AMISConstants.AMIS_PRODUCTS.toString(), false, false, width, height));


        items_aggregations.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, AMISConstants.CODING_SYSTEM_FAOSTAT_ITEM_AGGREGATIONS.toString(), true, false, width, height));




//		p.add(FAOSTATDownloadController.buildTab(selectorPanel, domainFilter,"Items", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM.toString(), false, false, width, height, true));
//		p.add(addHSpace(5));
//
//		/** TODO: change it.. **/
        //AMISFaostatDownloadController.buildTabCheck(items_aggregations, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_ITEM_AGGREGATIONS.toString());
////		p.add(buildTab(domainFilter,  "Items Aggr.", DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ITEM_AGGREGATIONS.toString(), true, false, width, height));

        panel.add(p);

        if(AMISDownloadController.isFoodBalance)
            panel.add(selectorPanel.build(download, "CBS", null, AMISConstants.AMIS_FOOD_BALANCE_PRODUCTS.toString(), false, true, width, height));
        else
            panel.add(selectorPanel.build(download, "CBS", null, AMISConstants.AMIS_PRODUCTS.toString(), false, false, width, height));


        setListners();

        return panel;
    }

	private void defaultStyles(){ 
		items = new ClickHtml();
		//items.setHtml("Items");
		items.setHtml("Products");
		items.addStyleName("download_tab_selected");
		
		items_aggregations = new ClickHtml();
		items_aggregations.setHtml("Items Aggr.");
		items_aggregations.addStyleName("download_tab_unselected");
	}
	
	private void setListners() {
		items_aggregations.addListener(Events.OnClick, setTab(items_aggregations));
		items.addListener(Events.OnClick, setTab(items));
	}
	
	private void resetStyles() {
		items.removeStyleName("download_tab_selected");
		items.removeStyleName("download_tab_unselected");
		items.addStyleName("download_tab_unselected");
		
		items_aggregations.removeStyleName("download_tab_unselected");
		items_aggregations.removeStyleName("download_tab_selected");
		items_aggregations.addStyleName("download_tab_unselected");
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

	public AMISDownloadSelectorPanel getSelectorPanel() {
		return selectorPanel;
	}
	
	
}

