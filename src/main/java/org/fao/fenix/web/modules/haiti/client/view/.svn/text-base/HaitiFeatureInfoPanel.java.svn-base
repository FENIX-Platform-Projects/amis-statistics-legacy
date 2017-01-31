package org.fao.fenix.web.modules.haiti.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.haiti.common.vo.FeatureInfoModelData;
import org.fao.fenix.web.modules.map.client.view.GetFeatureInfoWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class HaitiFeatureInfoPanel {

	private ContentPanel featureInfoPanel;
	
	private ListStore<FeatureInfoModelData> store;
	
	private Grid<FeatureInfoModelData> grid;
	
	public HaitiFeatureInfoPanel() {
		featureInfoPanel = new ContentPanel();
		featureInfoPanel.setHeaderVisible(false);
//		featureInfoPanel.setHeading("Feature Info");
		featureInfoPanel.setHeight("75px");
		featureInfoPanel.setCollapsible(true);
		featureInfoPanel.setScrollMode(Scroll.AUTO);
	}
	
	public ContentPanel build(String width) {
		
		List<ColumnConfig> configs = createColumnConfigurations();
		store = new ListStore<FeatureInfoModelData>();
		ColumnModel cm = new ColumnModel(configs);
		
		grid = new Grid<FeatureInfoModelData>(store, cm);
		grid.setStyleAttribute("borderTop", "none");   
		grid.setBorders(true);
		grid.setHeight("70px");
		String gridWidth = getGridWidth(width);
		grid.setWidth(gridWidth);
//		grid.setWidth(width);
		featureInfoPanel.setWidth(width);
		
		
		featureInfoPanel.add(grid);
//		featureInfoPanel.setBottomComponent(buildReferenceLayers());
		GetFeatureInfoWindow.setGrid(grid);
		GetFeatureInfoWindow.setStore(store);
		
		return featureInfoPanel;
	}
	
	private String getGridWidth(String width) {
		int gridWidth = Integer.parseInt(width.substring(0, width.length() - 2));
		return String.valueOf(gridWidth - 20) + "px";
	}
	
	private List<ColumnConfig> createColumnConfigurations() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		for(int i=0 ; i < 30; i++) {
			ColumnConfig column = new ColumnConfig("column" + i, " - ",150);
			configs.add(column);
		}
		return configs;
	}
	
	private Html buildReferenceLayers() {
		Html label = new Html("<div style='font-weight:bold; font-size: 10pt; background-color:#d0dded'>Layers blabla <a href='http://lprapp08.fao.org/fenix-portal/' target='_blank'> GIEWS Workstation</font></a></div>");
//		Html label = new Html("");
		return label;
	}
	
}