package org.fao.fenix.web.modules.haiticnsatool.client.view;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.haiticnsatool.client.control.HaitiCNSAController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.menu.CheckMenuItem;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.user.client.ui.HTML;

public class HaitiCNSACommandPanel {
	
	private HorizontalPanel panel;
	
	private ComboBox<ListItem> combo;
	
	private ListStore<ListItem> store;
	
	private String comboBoxLength = "100px";
	
	private String labelsLength = "110px";
	
	private Map<String, Map<String, LayerVO>> layersCategoryMap;
	
//	private Map<String, LayerVO> layersMap;
	
	private CheckMenuItem ndvi;
	
	private CheckMenuItem ndvi_va;
	
	private CheckMenuItem agric_land;
	
	private CheckMenuItem haiti_gaul1;
	
	private CheckMenuItem haiti_gaul1_cities;
	
	private CheckMenuItem countryBoundaries;
	
	private Menu menu;
	
	private Button exportReport;
	
		
	public HaitiCNSACommandPanel() {
		panel = new HorizontalPanel();
		panel.setSpacing(10);
		combo = new ComboBox<ListItem>();
		store = new ListStore<ListItem>();
//		layersMap = new HashMap<String, LayerVO>();
		layersCategoryMap = new HashMap<String, Map<String,LayerVO>>();
	
	}
	
	public HorizontalPanel build(String language, HaitiCNSAPanel haitiCNSAPanel) {
		ndvi = new CheckMenuItem(HaitiLangEntry.getInstance(language).ndvi()); 
		ndvi_va = new CheckMenuItem(HaitiLangEntry.getInstance(language).ndvi_va());
		haiti_gaul1 = new CheckMenuItem(HaitiLangEntry.getInstance(language).gaul1()); 
		haiti_gaul1_cities = new CheckMenuItem(HaitiLangEntry.getInstance(language).gaul1cities());
		agric_land = new CheckMenuItem(HaitiLangEntry.getInstance(language).agro_land());  
		countryBoundaries = new CheckMenuItem(HaitiLangEntry.getInstance(language).countryBoundaries());  
		exportReport = new Button(HaitiLangEntry.getInstance(language).exportReport());
		
		panel.add(buildDates(language, haitiCNSAPanel));
		panel.add(buildLayers(language, haitiCNSAPanel));
//		panel.add(exportReport);

		enancheButton(haitiCNSAPanel);
		return panel;
	}
	
	private void enancheButton(HaitiCNSAPanel haitiCNSAPanel) {
		exportReport.addSelectionListener(HaitiCNSAController.exportReport(haitiCNSAPanel));
	}
	
	private HorizontalPanel buildDates(String language, HaitiCNSAPanel haitiCNSAPanel) {
		HorizontalPanel vpanel = new HorizontalPanel();
		HTML label = new HTML( "<b>"+ HaitiLangEntry.getInstance(language).selectAdate() +":</b>");
		label.setWidth(labelsLength);
		combo.setWidth(comboBoxLength);
		vpanel.add(label);
		vpanel.add(combo);
		combo.setStore(store);
		combo.setTypeAhead(true);
		combo.setEditable(true);
		combo.setDisplayField("name");
		combo.setEmptyText(BabelFish.print().pleaseSelect());
		combo.setAllowBlank(false);
		
		combo.setTriggerAction(TriggerAction.ALL);
		combo.addSelectionChangedListener(HaitiCNSAController.reinitializeCNSATool(combo, haitiCNSAPanel));
		return vpanel;
	}
	
	private HorizontalPanel buildLayers(String language, HaitiCNSAPanel haitiCNSAPanel) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();

		Button settingsButton = new Button(HaitiLangEntry.getInstance(language).listOflayers());
		settingsButton.setWidth(comboBoxLength);
		menu = new Menu();
	 
		ndvi_va.setItemId("ndvi_va");
		ndvi_va.addSelectionListener(HaitiCNSAController.showHideLayer(haitiCNSAPanel));
		ndvi_va.setChecked(true);  
		menu.add(ndvi_va);
		
		ndvi.setItemId("ndvi");
		ndvi.addSelectionListener(HaitiCNSAController.showHideLayer(haitiCNSAPanel));
		menu.add(ndvi); 
		
		agric_land.setItemId("agric_land");
		agric_land.addSelectionListener(HaitiCNSAController.showHideLayer(haitiCNSAPanel));
		menu.add(agric_land); 
		
		haiti_gaul1_cities.setItemId("HAITI_GAUL1_CITIES");
		haiti_gaul1_cities.addSelectionListener(HaitiCNSAController.showHideLayer(haitiCNSAPanel));
		ndvi_va.setChecked(true);  
		menu.add(haiti_gaul1_cities); 
		
		haiti_gaul1.setItemId("HAITI_GAUL1");
		haiti_gaul1.addSelectionListener(HaitiCNSAController.showHideLayer(haitiCNSAPanel));
		ndvi_va.setChecked(true);  
		menu.add(haiti_gaul1); 
		   
		countryBoundaries.setItemId("CARIBBEANS");
		countryBoundaries.disable();
		menu.add(countryBoundaries);  

		
		settingsButton.setMenu(menu);
		settingsButton.setWidth(comboBoxLength);
		buttonsPanel.add(settingsButton);
		return buttonsPanel;
	}
	
	public void refreshMenu() {
		haiti_gaul1.setChecked(true);  
		haiti_gaul1_cities.setChecked(true);  
		ndvi_va.setChecked(true);  
		ndvi.setChecked(false);  
		agric_land.setChecked(false);  

	}

	public HorizontalPanel getPanel() {
		return panel;
	}

	public ComboBox<ListItem> getCombo() {
		return combo;
	}

	public ListStore<ListItem> getStore() {
		return store;
	}

//	public Map<String, LayerVO> getLayersMap() {
//		return layersMap;
//	}

	public CheckMenuItem getNdvi() {
		return ndvi;
	}

	public CheckMenuItem getNdvi_va() {
		return ndvi_va;
	}

	public Menu getMenu() {
		return menu;
	}

	public Map<String, Map<String, LayerVO>> getLayersCategoryMap() {
		return layersCategoryMap;
	}
	
	

	

}
