package org.fao.fenix.web.modules.dataMapper.client.view;

import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataMapper.client.control.DataMapperController;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class DataMapperTab {
	
	private TabPanel tabPanel;
	
	private TabItem countries;
	
	private TabItem options;
	
	private DataMapperRegion dataMapperRegion;
	
	private DataMapperOptions dataMapperOptions;
	
	public DataMapperTab() {
		tabPanel = new TabPanel();
		countries = new TabItem();
		options = new TabItem();
		dataMapperRegion = new DataMapperRegion();
		dataMapperOptions = new DataMapperOptions();
		
		
		tabPanel.setHeight(260);
		tabPanel.setWidth(1100);
	}
	
	public TabPanel build() {
		countries.setText("Select countries");
		countries.add(dataMapperRegion.build());
		
		options.setText("Options");
		options.add(dataMapperOptions.build());
		options.setEnabled(false);
		
		tabPanel.add(countries);
		tabPanel.add(options);
		return tabPanel;
	}

	public DataMapperRegion getDataMapperRegion() {
		return dataMapperRegion;
	}

	public DataMapperOptions getDataMapperOptions() {
		return dataMapperOptions;
	}
	
	
	
	
	
	

}
