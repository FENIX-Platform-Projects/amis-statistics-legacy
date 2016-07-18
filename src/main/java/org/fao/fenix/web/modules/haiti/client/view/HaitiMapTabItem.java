package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;

public class HaitiMapTabItem extends FenixToolBase {

	private ContentPanel controlsPanel;
	
	private HaitiLayersPanel haitiLayersPanel;
	
	private HaitiLegendPanel haitiLegendPanel;
	
	private HaitiOLPanel haitiOLPanel;
	
	private HaitiFeatureInfoPanel haitiFeatureInfoPanel;	
	
	public HaitiMapTabItem(String language) {
		haitiLayersPanel = new HaitiLayersPanel(language);
		haitiLegendPanel = new HaitiLegendPanel(language);
		haitiOLPanel = new HaitiOLPanel(0);
		haitiFeatureInfoPanel = new HaitiFeatureInfoPanel();
	}
	
	public ContentPanel build(String language, HaitiCropCalendarPanel cropCalendarPanel) {
		
		buildWestPanel(null, language);
		buildCenterPanel(language);
		buildSouthPanel(null, language);
		
		haitiLayersPanel.getGaulList().addSelectionChangedListener(HaitiController.addLayers(haitiLayersPanel.getGaulList(), this, language));
		haitiLayersPanel.getGaulList().addSelectionChangedListener(HaitiController.buildCropCalendarTab(haitiLayersPanel.getGaulList(), cropCalendarPanel, language));
		haitiLayersPanel.getRemoveAllButton().addSelectionListener(HaitiController.removeAll(haitiOLPanel.getMapPanel(), haitiLayersPanel));
		haitiLayersPanel.getShowAllButton().addSelectionListener(HaitiController.showAllLayers(haitiOLPanel.getMapPanel(), haitiLayersPanel));
		haitiLayersPanel.getHideAllButton().addSelectionListener(HaitiController.hideAllLayers(haitiOLPanel.getMapPanel(), haitiLayersPanel));
				
		format();
		
		return toolBase;
	}
	
	public ContentPanel build(String gaul0code, String width, String height, String language) {
		
		buildWestPanel(gaul0code, language);
		buildCenterPanel(language);
		buildSouthPanel(width, language);
		
		HaitiController.addLayersAgent(gaul0code, this, language);
		haitiLayersPanel.getRemoveAllButton().addSelectionListener(HaitiController.removeAll(haitiOLPanel.getMapPanel(), haitiLayersPanel));
		haitiLayersPanel.getShowAllButton().addSelectionListener(HaitiController.showAllLayers(haitiOLPanel.getMapPanel(), haitiLayersPanel));
		haitiLayersPanel.getHideAllButton().addSelectionListener(HaitiController.hideAllLayers(haitiOLPanel.getMapPanel(), haitiLayersPanel));
		
		Menu datasetScrollMenu = (Menu)haitiOLPanel.getMapPanel().getToolbar().getData("datasetScrollMenu");
		HaitiController.fillDatasetScrollerMenu(gaul0code, language, datasetScrollMenu);
				
		format(width, height);
		
		return toolBase;
	}
	
	private void buildCenterPanel(String language) {
		setCenterProperties();
		getCenter().add(haitiOLPanel.build(this, language));
		addCenterPartToWindow();
		getCenter().setHeading(HaitiLangEntry.getInstance(language).currentMapView() + ": Landuse");
	}
	
	private void buildSouthPanel(String width, String language) {
		if (width != null)
			fillSouthPart(haitiFeatureInfoPanel.build(width));
		else
			fillSouthPart(haitiFeatureInfoPanel.build("1350px"));
		getSouthData().setSize(100);
		getSouth().setHeading(HaitiLangEntry.getInstance(language).featureInfo());
		getSouth().setCollapsible(true);
	}
	
	private void buildWestPanel(String gaul0code, String language) {
		controlsPanel = new ContentPanel();
//		controlsPanel.setLayout(new AccordionLayout());
		controlsPanel.setLayout(new FitLayout());
		controlsPanel.setHeaderVisible(false);
		controlsPanel.setBodyBorder(false);
		if (gaul0code != null)
			controlsPanel.add(haitiLayersPanel.build(gaul0code, language, this));
		else
			controlsPanel.add(haitiLayersPanel.build(this));
//		controlsPanel.add(haitiLegendPanel.build());
		fillWestPart(controlsPanel);
		getWestData().setSize(400);
		getWest().setHeading(HaitiLangEntry.getInstance(language).controller());
		getWest().setCollapsible(true);
	}
	
	private void format() {
		getToolBase().setWidth("1350px");
		getToolBase().setHeight("675px");
		getToolBase().setHeaderVisible(false);
	}
	
	private void format(String width, String height) {
		if (width != null)
			getToolBase().setWidth(width);
		else
			getToolBase().setWidth("1350px");
		if (height != null)
			getToolBase().setHeight(height);
		else
			getToolBase().setHeight("675px");
		getToolBase().setHeaderVisible(false);
	}

	public HaitiLayersPanel getHaitiLayersPanel() {
		return haitiLayersPanel;
	}

	public HaitiLegendPanel getHaitiLegendPanel() {
		return haitiLegendPanel;
	}

	public HaitiOLPanel getHaitiOLPanel() {
		return haitiOLPanel;
	}
	
	public HaitiFeatureInfoPanel getHaitiFeatureInfoPanel() {
		return haitiFeatureInfoPanel;
	}
	
}