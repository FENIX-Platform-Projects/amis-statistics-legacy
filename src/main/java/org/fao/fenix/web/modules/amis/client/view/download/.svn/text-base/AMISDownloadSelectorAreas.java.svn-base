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

public class AMISDownloadSelectorAreas {
	
	AMISDownloadSelectorPanel selectorPanel;
	
	ContentPanel panel;  

	ClickHtml countries;
	
	ClickHtml areas;
	
	ClickHtml areas_fao;
	
	ClickHtml areas_all;
	
//	TabPanel panel;  

	public AMISDownloadSelectorAreas() {
//		panel = new TabPanel();
		panel = new ContentPanel();  
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);
		
		selectorPanel = new AMISDownloadSelectorPanel();
	} 
	
	public ContentPanel build(AMISDownload download, AMISCodesModelData domainFilter, String width, String height) {
        System.out.println("------------ AMISDownloadSelectorAreas: build !!!! ");
		HorizontalPanel p = new HorizontalPanel();
		
		defaultStyles();

        if(AMISDownloadController.isFoodBalance)
            countries.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, domainFilter, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), true, true, width, height));
        else
		    countries.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, domainFilter, AMISConstants.AMIS_COUNTRIES.toString(), true, true, width, height));

	//	areas.addListener(Events.OnClick, AMISFaostatDownloadController.reloadFilter(selectorPanel, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_AREAS_WORLD.toString(), true, true, width, height));
 	//	areas_fao.addListener(Events.OnClick, AMISFaostatDownloadController.reloadFilter(selectorPanel, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_AREAS_FAO.toString(), true, true, width, height));
	//	areas_all.addListener(Events.OnClick, AMISFaostatDownloadController.reloadFilter(selectorPanel, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_COUNTRIES_AREAS_ALL.toString(), true, true, width, height));

		p.add(countries);
		p.add(addHSpace(5));
//		p.add(areas);
//		p.add(addHSpace(5));
//		p.add(areas_fao);
//		p.add(addHSpace(5));
//		p.add(areas_all);
//		p.add(addHSpace(5));
		
		setListners();

		panel.add(p);

        if(AMISDownloadController.isFoodBalance)    {
            System.out.println("------------ AMISDownloadSelectorAreas: selectorPanel.build CALLED !!!! ");
		    panel.add(selectorPanel.build(download, domainFilter, null, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), false, true, width, height));
        }
		else {
            panel.add(selectorPanel.build(download, domainFilter, null, AMISConstants.AMIS_COUNTRIES.toString(), false, true, width, height));
        }

        return panel;
	}


    public ContentPanel build(AMISDownload download, String width, String height) {
        System.out.println("------------ AMISDownloadSelectorAreas: build !!!! ");
        HorizontalPanel p = new HorizontalPanel();

        defaultStyles();

        if(AMISDownloadController.isFoodBalance)
            countries.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), true, true, width, height));
        else
            countries.addListener(Events.OnClick, AMISDownloadController.reloadFilter(download, selectorPanel, AMISConstants.AMIS_COUNTRIES.toString(), true, true, width, height));

        //	areas.addListener(Events.OnClick, AMISFaostatDownloadController.reloadFilter(selectorPanel, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_AREAS_WORLD.toString(), true, true, width, height));
        //	areas_fao.addListener(Events.OnClick, AMISFaostatDownloadController.reloadFilter(selectorPanel, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_AREAS_FAO.toString(), true, true, width, height));
        //	areas_all.addListener(Events.OnClick, AMISFaostatDownloadController.reloadFilter(selectorPanel, domainFilter, AMISConstants.CODING_SYSTEM_FAOSTAT_COUNTRIES_AREAS_ALL.toString(), true, true, width, height));

        p.add(countries);
        p.add(addHSpace(5));
//		p.add(areas);
//		p.add(addHSpace(5));
//		p.add(areas_fao);
//		p.add(addHSpace(5));
//		p.add(areas_all);
//		p.add(addHSpace(5));

        setListners();

        panel.add(p);

        if(AMISDownloadController.isFoodBalance)    {
            System.out.println("------------ AMISDownloadSelectorAreas: selectorPanel.build CALLED !!!! ");
            panel.add(selectorPanel.build(download, "CBS", null, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), false, true, width, height));
        }
        else {
            panel.add(selectorPanel.build(download, "CBS", null, AMISConstants.AMIS_COUNTRIES.toString(), false, true, width, height));
        }

        return panel;
    }

	private void defaultStyles(){ 
		countries = new ClickHtml();
		countries.setHtml("Countries/Regions");
		countries.addStyleName("download_tab_selected");
		
		areas = new ClickHtml();
		areas.setHtml("Areas");

		areas.addStyleName("download_tab_unselected");
		
		areas_all = new ClickHtml();
		areas_all.setHtml("All");
		areas_all.addStyleName("download_tab_unselected");
		
		areas_fao = new ClickHtml();
		areas_fao.setHtml("Areas FAO");
		areas_fao.addStyleName("download_tab_unselected");
	}
	
	private void setListners() {
		
		
		countries.addListener(Events.OnClick, setTab(countries));
		areas.addListener(Events.OnClick, setTab(areas));
		areas_fao.addListener(Events.OnClick, setTab(areas_fao));
		areas_all.addListener(Events.OnClick, setTab(areas_all));
	}
	
	private void resetStyles() {
		countries.removeStyleName("download_tab_selected");
		countries.removeStyleName("download_tab_unselected");
		countries.addStyleName("download_tab_unselected");
		
		areas.removeStyleName("download_tab_unselected");
		areas.removeStyleName("download_tab_selected");
		areas.addStyleName("download_tab_unselected");

		 areas_fao.removeStyleName("download_tab_selected");
		 areas_fao.removeStyleName("download_tab_unselected");
		 areas_fao.addStyleName("download_tab_unselected");
		
		 areas_all.removeStyleName("download_tab_selected");
		 areas_all.removeStyleName("download_tab_unselected");
		 areas_all.addStyleName("download_tab_unselected");
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

