package org.fao.fenix.web.modules.dataMapper.client.view;

import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataMapper.client.control.DataMapperController;

import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class DataMapperToolbar {
	
	private HorizontalPanel panel;
	
	private Button createMap;
	

	public DataMapperToolbar() {
		panel = new HorizontalPanel();
		createMap = new Button("create Map");
		createMap.setIconStyle("mapAddLayer");
		createMap.setIconAlign(IconAlign.LEFT);
	}
	
	public HorizontalPanel build(DataMapperMapPanel dataMapperMapPanel, DataMapperTab dataMapperTab) {
		panel.setSpacing(5);
		panel.add(createMap);
		createMap.addSelectionListener(DataMapperController.createMap(dataMapperMapPanel, dataMapperTab.getDataMapperRegion(), dataMapperTab.getDataMapperOptions()));
		return panel;
	}
	
	
	
	
	
	

}
