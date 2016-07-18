package org.fao.fenix.web.modules.haiti.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.haiti.common.vo.WMSModelData;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RadioButton;

public class HaitiLayersPanel {

	private ContentPanel layersPanel;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private ListStore<WMSModelData> wmsStore;
	
	private ComboBox<WMSModelData> wmsList;
	
	private ListStore<LayerVO> layerStore;
	
	private ComboBox<LayerVO> layerList;
	
	private ListStore<LayerVO> mapStore;
	
	private ComboBox<LayerVO> mapList;
	
	private Button showAllButton;
	
	private Button hideAllButton;
	
	private Button removeAllButton;
	
	private Button addLayerButton;
	
	private Map<Long, LayerVO> layersMap;
	
	// Map<GVID, DBLayerID>
	private Map<Long, Long> layersDBIDMap;
	
	private Map<Long, HorizontalPanel> layerPanelsMap;
	
	private Map<String, List<LayerVO>> externalLayersCached;
	
	private final String LABEL_WIDTH = "150px";
	
	private final String COMBO_WIDTH = "375px";
	
	private final String PANEL_HEIGHT = "200px";
	
	private final String LAYER_WIDTH = "250px";
	
	private final int SPACING = 5;
	
	public HaitiLayersPanel(String language) {
		layersPanel = new ContentPanel();
		layersPanel.setScrollMode(Scroll.AUTO);
		layersPanel.setHeading(HaitiLangEntry.getInstance(language).layers());
		layersPanel.setHeaderVisible(false);
		layersPanel.setHeight(PANEL_HEIGHT);
		layersPanel.setBodyBorder(false);
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		wmsStore = new ListStore<WMSModelData>(); 
		wmsList = new ComboBox<WMSModelData>();
		wmsList.setTriggerAction(TriggerAction.ALL);
		wmsList.setStore(wmsStore);
		wmsList.setDisplayField("wmsLabel");
		layerStore = new ListStore<LayerVO>(); 
		layerList = new ComboBox<LayerVO>();
		layerList.setTriggerAction(TriggerAction.ALL);
		layerList.setStore(layerStore);
		layerList.setDisplayField("layerTitle");
		mapStore = new ListStore<LayerVO>();
		mapList = new ComboBox<LayerVO>();
		mapList.setTriggerAction(TriggerAction.ALL);
		mapList.setStore(mapStore);
		mapList.setDisplayField("layerTitle");
		layersMap = new HashMap<Long, LayerVO>();
		layersDBIDMap = new HashMap<Long, Long>();
		layerPanelsMap = new HashMap<Long, HorizontalPanel>();
		externalLayersCached = new HashMap<String, List<LayerVO>>();
	}
	
	public ContentPanel build(HaitiMapTabItem haitiMapTabItem) {
		MEController.fillGaulStore(gaulStore);
		HaitiController.fillWMSStore(wmsList, wmsStore);
		VerticalPanel gaulPanel = buildGAULPanel(true, null);
		return build(gaulPanel, null, haitiMapTabItem);
	}
	
	public ContentPanel build(String gaul0code, String language, HaitiMapTabItem haitiMapTabItem) {
		MEController.fillGaulStore(gaulList, gaulStore, gaul0code);
		HaitiController.fillWMSStore(wmsList, wmsStore);
		HaitiController.fillMapStore(gaul0code, mapList, mapStore);
		//pre-select Landuse in Map List
		VerticalPanel gaulPanel = buildGAULPanel(false, language);
		return build(gaulPanel, language, haitiMapTabItem);
	}
	
	private ContentPanel build(VerticalPanel gaulPanel, String language, HaitiMapTabItem haitiMapTabItem) {
		VerticalPanel topComponent = new VerticalPanel();
		topComponent.add(gaulPanel);
//		topComponent.add(buildWMSPanel(language));
//		topComponent.add(buildLayersPanel(language, haitiMapTabItem));
		topComponent.add(buildMapPanel(haitiMapTabItem, language));
		layersPanel.setTopComponent(topComponent);
		layersPanel.setBottomComponent(buildButtonsPanel(language, haitiMapTabItem));
		return layersPanel;
	}
	
	private VerticalPanel buildGAULPanel(boolean enabled, String language) {
		VerticalPanel gaulPanel = new VerticalPanel();
		gaulPanel.setSpacing(SPACING);
		HTML label = new HTML(""); 
		if (enabled)
			label.setHTML("<div style='font-weight: bold; font-size: 8pt;'>" + HaitiLangEntry.getInstance(language).pleaseSelectACountry() + ":</div>");
		else
			label.setHTML("<div style='font-weight: bold; font-size: 8pt; color: gray;'>" + HaitiLangEntry.getInstance(language).pleaseSelectACountry() + " :</div>");
		
		label.setVisible(enabled);
		gaulPanel.setVisible(enabled);
		
		gaulPanel.add(label);
		gaulPanel.add(gaulList);
		gaulList.setWidth(COMBO_WIDTH);
		gaulList.setEnabled(enabled);
		
		
		
		return gaulPanel;
	}
	
	private VerticalPanel buildWMSPanel(String language) {
		VerticalPanel wmsPanel = new VerticalPanel();
		wmsPanel.setSpacing(3);
		HTML label = new HTML("<div style='color: #00008B; font-weight: bold; font-size: 8pt; '>" + HaitiLangEntry.getInstance(language).pleaseSelectAWMSServer() + ":</div>"); 
		wmsPanel.add(label);
		wmsPanel.add(wmsList);
		wmsList.addSelectionChangedListener(HaitiController.changeWms(this, language));
		wmsList.setWidth(COMBO_WIDTH);
		return wmsPanel;
	}
	
	private VerticalPanel buildMapPanel(HaitiMapTabItem haitiMapTabItem, String language) {
		VerticalPanel mapPanel = new VerticalPanel();
		mapPanel.setSpacing(SPACING);
		
		
		
		
		HTML label = new HTML("<div style='color: #00008B; font-weight: bold; font-size: 8pt;'>" + HaitiLangEntry.getInstance(language).pleaseSelectAMap() + ":</div>"); 
		mapPanel.add(label);
		mapPanel.add(mapList);
		
		mapPanel.add(new HTML("<div style='color: #1d4589; font-family: sans-serif; font-style: italic; font-size: 8pt;'>"+HaitiLangEntry.getInstance(language).featureInfoInstructions()+"</div>"));
		
		mapList.addSelectionChangedListener(HaitiController.showPredifinedMap(language, haitiMapTabItem));
		mapList.setWidth(COMBO_WIDTH);
		return mapPanel;
	}
	
	private VerticalPanel buildLayersPanel(String language, HaitiMapTabItem haitiMapTabItem) {
		VerticalPanel layersPanel = new VerticalPanel();
		HorizontalPanel hpanel = new HorizontalPanel();
		layersPanel.setSpacing(3);
		HTML label = new HTML("<div style='color: #00008B; font-weight: bold; font-size: 8pt;'>" + HaitiLangEntry.getInstance(language).pleaseSelectALayer() + ":</div>");
		HTML space = new HTML("");
		space.setWidth("20px");
		layersPanel.add(label);		
		addLayerButton = new Button(HaitiLangEntry.getInstance(language).addLayer());
		hpanel.add(layerList);
		hpanel.add(space);
		hpanel.add(addLayerButton);
		addLayerButton.addSelectionListener(HaitiController.addSelectedLayer(haitiMapTabItem, language));
		layerList.setWidth(LAYER_WIDTH);
		layersPanel.add(hpanel);
		return layersPanel;
	}
	
	public void addLayerPanel(LayerVO vo, HaitiMapTabItem haitiMapTabItem, String language, Boolean isBaseLayer) {
		layersPanel.add(buildLayerPanel(vo, haitiMapTabItem, language, isBaseLayer));
		layersPanel.getLayout().layout();
	}
	
	private HorizontalPanel buildLayerPanel(final LayerVO vo, final HaitiMapTabItem haitiMapTabItem, String language, Boolean isBaseLayer) {
		
		MapPanel mapPanel = haitiMapTabItem.getHaitiOLPanel().getMapPanel();
		HorizontalPanel layerPanel = new HorizontalPanel();
		layerPanel.setSpacing(SPACING);
		layerPanel.setBorders(true);
		
		IconButton bulletButton = new IconButton();
		bulletButton.setStyleName("");
		bulletButton.setSize("10px", "10px");
			
		
//		HTML label = new HTML("<div style='font-size: 8pt; '>" + vo.getLayerTitle() + "</div>");
//		label.setWidth(LABEL_WIDTH);
		
		
	
		IconButton showLegendButton = new IconButton("mapViewLegend");
		showLegendButton.setToolTip(HaitiLangEntry.getInstance(language).showHideLegend());
		showLegendButton.addSelectionListener(HaitiController.addLegendPanel(haitiMapTabItem, vo, layerPanel));
		showLegendButton.setBorders(true);
		
		Hyperlink label = new Hyperlink();
		label.setText(vo.getLayerTitle());
		label.setWidth(LABEL_WIDTH);
		label.addClickHandler(HaitiController.selectLayer(haitiMapTabItem, vo, layerPanel));
		layerPanel.setData("hyperlink", label);
		
		RadioButton selectedLayerButton = new RadioButton("selectedLayer", "");
		selectedLayerButton.addClickHandler(HaitiController.selectLayer(haitiMapTabItem, vo, layerPanel));

		IconButton removeButton = new IconButton("deleteObj");
		removeButton.setToolTip(HaitiLangEntry.getInstance(language).removeLayer());
		removeButton.addSelectionListener(HaitiController.removeLayer(mapPanel, vo.getOlID(), this));
//		removeButton.setBorders(true);
		
		CheckBox showHideButton = new CheckBox();
		showHideButton.setChecked(!vo.isHidden());
		showHideButton.addClickHandler(HaitiController.showHideLayer(mapPanel, vo.getOlID(), showHideButton));
		showHideButton.setTitle(HaitiLangEntry.getInstance(language).showHideLayer());
		layerPanel.setData("checkbox", showHideButton);
		
		IconButton layerUpButton = new IconButton("mapLayerUp");
		layerUpButton.setToolTip(HaitiLangEntry.getInstance(language).moveLayerUp());
		layerUpButton.addSelectionListener(HaitiController.moveLayerUp(mapPanel, vo.getOlID(), haitiMapTabItem));
//		layerUpButton.setBorders(true);
		
		IconButton layerDownButton = new IconButton("mapLayerDown");
		layerDownButton.setToolTip(HaitiLangEntry.getInstance(language).moveLayerDown());
		layerDownButton.addSelectionListener(HaitiController.moveLayerDown(mapPanel, vo.getOlID(), haitiMapTabItem));
//		layerDownButton.setBorders(true);
		
		
		
	
	//	layerPanel.add(selectedLayerButton);
//		layerPanel.add(bulletButton);
		layerPanel.add(showHideButton);
		layerPanel.add(label);
		layerPanel.add(bulletButton);
		if (isBaseLayer ) {
			bulletButton.setStyleName("baseLayer");
//			layerPanel.add(bulletButton);
		}
		layerPanel.add(showLegendButton);
		
		
		if ( isBaseLayer ) {
			bulletButton.setStyleName("baseLayer");
			bulletButton.setTitle(HaitiLangEntry.getInstance(language).baseLayer());
//			layerPanel.add(bulletButton);
			
			layerUpButton.setEnabled(false);
			layerUpButton.setStyleName("mapLayerUpDisabled");
			layerUpButton.setTitle(HaitiLangEntry.getInstance(language).baseLayerCannotBeMoved());
			layerDownButton.setEnabled(false);
			layerDownButton.setStyleName("mapLayerDownDisabled");
			layerDownButton.setTitle(HaitiLangEntry.getInstance(language).baseLayerCannotBeMoved());
			removeButton.setEnabled(false);
			removeButton.setStyleName("deleteObjDisabled");
			removeButton.setTitle(HaitiLangEntry.getInstance(language).baseLayerCannotBeRemoved());
		}
			
		layerPanel.add(layerUpButton);
		layerPanel.add(layerDownButton);
		layerPanel.add(removeButton);
			
			
		final Slider slider = new Slider();  
		slider.setWidth(75);  
		slider.setIncrement(10);  
		slider.setMaxValue(100);  
		slider.setClickToChange(true);
		slider.setValue(100);
		//slider.setMessage(slider.getValue() + "%");
		slider.addListener(Events.Change, HaitiController.changeTrasparency(mapPanel, vo.getOlID(), slider));
		slider.setTitle(HaitiLangEntry.getInstance(language).layerOpacitySlider());
		layerPanel.setData("slider", slider);
		layerPanel.add(slider);
		
		IconButton showMetadataButton = new IconButton("info");
		showMetadataButton.setToolTip(HaitiLangEntry.getInstance(language).viewMetadata());
		showMetadataButton.addSelectionListener(HaitiController.showMetadataLayer(vo, language));
//		showMetadataButton.setBorders(true);
		layerPanel.add(showMetadataButton);
		
		layerPanel.setData("layerID", String.valueOf(vo.getOlID()));
		layersMap.put(vo.getOlID(), vo);
		layersDBIDMap.put(vo.getOlID(), vo.getLayerID());
		layerPanelsMap.put(vo.getOlID(), layerPanel);
		
		return layerPanel;
	}
	
	private VerticalPanel buildButtonsPanel(String language, HaitiMapTabItem haitiMapTabItem) {
		VerticalPanel buttonsPanel = new VerticalPanel();
		buttonsPanel.setSpacing(SPACING);
		buttonsPanel.setBorders(true);
		showAllButton = new Button(HaitiLangEntry.getInstance(language).showAll());
		showAllButton.setMinWidth(115);
		hideAllButton = new Button(HaitiLangEntry.getInstance(language).hideAll());
		hideAllButton.setMinWidth(115);
		removeAllButton = new Button(HaitiLangEntry.getInstance(language).removeAll());
		removeAllButton.setMinWidth(115);
		
//		buttonsPanel.setHeight("100px");
		
		HorizontalPanel hz = new HorizontalPanel();
		
		
		hz.add(showAllButton);
		hz.add(hideAllButton);
		hz.add(removeAllButton);
		hz.setSpacing(10);
//		buttonsPanel.add(hz);
		
		buttonsPanel.add(buildWMSPanel(language));
		buttonsPanel.add(buildLayersPanel(language, haitiMapTabItem));
		
		buttonsPanel.add(hz);
		
		
//		buttonsPanel.add(showAllButton);
//		buttonsPanel.add(hideAllButton);
//		buttonsPanel.add(removeAllButton);
		return buttonsPanel;
	}
	
	public void addLayerPanelOnTop(LayerVO vo, HaitiMapTabItem haitiMapTabItem, String language) {
        HorizontalPanel layerPanel = buildLayerPanel(vo, haitiMapTabItem, language, false); 
        layersPanel.insert(layerPanel, 0);
        layersPanel.getLayout().layout();
    }
	

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public ContentPanel getLayersPanel() {
		return layersPanel;
	}

	public Button getRemoveAllButton() {
		return removeAllButton;
	}

	public Map<Long, LayerVO> getLayersMap() {
		return layersMap;
	}

	public Map<Long, HorizontalPanel> getLayerPanelsMap() {
		return layerPanelsMap;
	}

	public Map<Long, Long> getLayersDBIDMap() {
		return layersDBIDMap;
	}

	public Button getShowAllButton() {
		return showAllButton;
	}

	public Button getHideAllButton() {
		return hideAllButton;
	}

	public ListStore<LayerVO> getLayerStore() {
		return layerStore;
	}

	public ComboBox<LayerVO> getLayerList() {
		return layerList;
	}

	public Map<String, List<LayerVO>> getExternalLayersCached() {
		return externalLayersCached;
	}

	public ComboBox<WMSModelData> getWmsList() {
		return wmsList;
	}

	public ListStore<LayerVO> getMapStore() {
		return mapStore;
	}

	public ComboBox<LayerVO> getMapList() {
		return mapList;
	}
	
}