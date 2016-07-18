package org.fao.fenix.web.modules.dataMapper.client.view;

import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataMapper.client.control.DataMapperController;
import org.fao.fenix.web.modules.haiticnsatool.client.control.HaitiCNSAController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;

public class DataMapperTopToolbar {
	
	private HorizontalPanel panel;
	
	

	public DataMapperTopToolbar() {
		panel = new HorizontalPanel();
	}
	
	public HorizontalPanel build(DataMapperMapPanel dataMapperMapPanel, DataMapperTab dataMapperTab) {
		panel.setSpacing(5);
		
		Button button;


		button = new Button(HaitiLangEntry.getInstance("EN").exportAsImage());
		button.setIconStyle("mapExportAsImage");
		button.addSelectionListener(DataMapperController.exportMapAsZip(dataMapperMapPanel, "EN"));
		panel.add(button);
		
		return panel;
	}
	
	
	
	
	
	

}
