package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu.FAOSTATMenuController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.springframework.web.filter.OncePerRequestFilter;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class FAOSTATDownloadSelectorAreas {
	
	FAOSTATDownloadSelectorPanel selectorPanel;
	
	ContentPanel panel;  

	ClickHtml countries;
	
	ClickHtml areas;
	
	ClickHtml areas_fao;
	
	ClickHtml areas_all;
	
//	TabPanel panel;  

	public FAOSTATDownloadSelectorAreas() {
//		panel = new TabPanel();
		panel = new ContentPanel();  
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);
		
		selectorPanel = new FAOSTATDownloadSelectorPanel();
	} 
	
	public ContentPanel build(DWCodesModelData domainFilter, String width, String height, Boolean addSelectAll, Boolean showAreas) {

		HorizontalPanel p = new HorizontalPanel();
		
		defaultStyles();
	
		countries.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString(), true, true, width, height));
		if ( showAreas ) {
			areas.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_WORLD.toString(), true, true, width, height));
			areas_fao.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_FAO.toString(), true, true, width, height));
			areas_all.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES_AREAS_ALL.toString(), true, true, width, height));
		}

		
		if ( showAreas ) {
			p.add(countries);
			p.add(addHSpace(5));
			p.add(areas);
			p.add(addHSpace(5));
			p.add(areas_fao);
			p.add(addHSpace(5));
			p.add(areas_all);
			p.add(addHSpace(5));
			panel.add(p);
		}
		else {
			panel.add(countries);
		}

		setListners();

		panel.add(selectorPanel.build(domainFilter, null, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString(), false, addSelectAll, width, height));
		
		return panel;
	}
	
	public ContentPanel build(List<DWCodesModelData> domainFilter, String width, String height, Boolean addSelectAll, Boolean showAreas) {

		HorizontalPanel p = new HorizontalPanel();
		
		defaultStyles();
	
		countries.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString(), true, true, width, height));
		if ( showAreas ) {
			areas.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_WORLD.toString(), true, true, width, height));
			areas_fao.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_AREAS_FAO.toString(), true, true, width, height));
			areas_all.addListener(Events.OnClick, FAOSTATDownloadController.reloadFilter(selectorPanel, domainFilter, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES_AREAS_ALL.toString(), true, true, width, height));
		}

		if ( showAreas ) {
			p.add(countries);
			p.add(addHSpace(5));
			p.add(areas);
			p.add(addHSpace(5));
			p.add(areas_fao);
			p.add(addHSpace(5));
			p.add(areas_all);
			p.add(addHSpace(5));
			panel.add(p);
		}
		else {
			panel.add(countries);
		}

		setListners();

		panel.add(selectorPanel.build(domainFilter, null, DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_COUNTRIES.toString(), false, addSelectAll, width, height));
		
		return panel;
	}
	
	private void defaultStyles(){ 
		countries = new ClickHtml();
		countries.setHtml(FAOSTATLanguage.print().countries());
		countries.addStyleName("download_tab_selected");
		
		areas = new ClickHtml();
		areas.setHtml(FAOSTATLanguage.print().regions());
	
		areas.addStyleName("download_tab_unselected");
			
		areas_all = new ClickHtml();
		areas_all.setHtml(FAOSTATLanguage.print().all());
		areas_all.addStyleName("download_tab_unselected");
			
		areas_fao = new ClickHtml();
		areas_fao.setHtml(FAOSTATLanguage.print().specialGroups());
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

	public FAOSTATDownloadSelectorPanel getSelectorPanel() {
		return selectorPanel;
	}
	
	
}

