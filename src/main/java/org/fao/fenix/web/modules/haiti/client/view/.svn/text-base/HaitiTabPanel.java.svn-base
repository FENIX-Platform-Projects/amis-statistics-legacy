package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class HaitiTabPanel {

	private TabPanel tabPanel;
	
	private HaitiMapTabItem haitiMapTabItem;
	
	private HaitiRSSPanel haitiRSSPanel;
	
	private HaitiCropCalendarPanel haitiCropCalendarPanel;
	
	private HaitiMapsGalleryPanel haitiMapsGalleryPanel;
	
	private TabItem mapTabItem;
	
	private TabItem rssTabItem;
	
	private TabItem cropCalendarItem;
	
	private TabItem mapsGalleryItem;
	
	public HaitiTabPanel(String language) {
		tabPanel = new TabPanel();
		mapTabItem = new TabItem(HaitiLangEntry.getInstance(language).mapView());
		haitiMapTabItem = new HaitiMapTabItem(language);
		rssTabItem = new TabItem(HaitiLangEntry.getInstance(language).newsView());
		haitiRSSPanel = new HaitiRSSPanel(); 
		cropCalendarItem = new TabItem(HaitiLangEntry.getInstance(language).cropCalendar());
		haitiCropCalendarPanel = new HaitiCropCalendarPanel(); 
		mapsGalleryItem = new TabItem(HaitiLangEntry.getInstance(language).mapsGallery());
		haitiMapsGalleryPanel = new HaitiMapsGalleryPanel(); 
	}
	
	public TabPanel build(String language) {
		mapTabItem.add(haitiMapTabItem.build(language, haitiCropCalendarPanel));
		tabPanel.add(mapTabItem);
		rssTabItem.add(haitiRSSPanel.build());
		
		cropCalendarItem.add(haitiCropCalendarPanel.build());
		mapsGalleryItem.add(haitiMapsGalleryPanel.build());
		tabPanel.add(mapsGalleryItem);
		
		tabPanel.add(cropCalendarItem);
		tabPanel.add(rssTabItem);
		tabPanel.setWidth("1100px");
		tabPanel.setHeight("650px");
		HaitiController.createNewsTabPanel(haitiRSSPanel, haitiRSSPanel.getSourceUrlMap(), null, null);
		haitiRSSPanel.getRefreshButton().addSelectionListener(HaitiController.refreshNews(haitiRSSPanel, haitiRSSPanel.getSourceUrlMap(), null, null));
		return tabPanel;
	}
	
	public TabPanel build(String gaul0code, String width, String height, String language) {
		mapTabItem.add(haitiMapTabItem.build(gaul0code, width, height, language));
		tabPanel.add(mapTabItem);
		tabPanel.setHeight(height);
		rssTabItem.add(haitiRSSPanel.build(width, height, language));
		
		cropCalendarItem.add(haitiCropCalendarPanel.build(gaul0code, width, height, language));
		mapsGalleryItem.add(haitiMapsGalleryPanel.build(gaul0code, width, height, language));
		tabPanel.add(mapsGalleryItem);
		tabPanel.add(cropCalendarItem);
		tabPanel.add(rssTabItem);
		if (width != null)
			tabPanel.setWidth(width);
		else
			tabPanel.setWidth("1100px");
		HaitiController.createNewsTabPanel(haitiRSSPanel, haitiRSSPanel.getSourceUrlMap(), width, height);
		haitiRSSPanel.getRefreshButton().addSelectionListener(HaitiController.refreshNews(haitiRSSPanel, haitiRSSPanel.getSourceUrlMap(), width, height));
		return tabPanel;
	}
	
}
