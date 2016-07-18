package org.fao.fenix.web.modules.dataMapper.client.view;

import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataMapper.client.control.DataMapperController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class DataMapperRegion {
	
	private VerticalPanel panel;
	
	private LinkedHashMap<String, DataMapperContent> countries;
	
	public DataMapperRegion(){
		panel = new VerticalPanel();
		countries = new LinkedHashMap<String, DataMapperContent>();
		
		panel.setHeight(230);
		panel.setScrollMode(Scroll.AUTO);
		panel.setSpacing(5);
//		panel.setWidth("100%");
		panel.setWidth(1100);
	}
	
	public VerticalPanel build() {
		DataMapperController.getCountries(this);
		return panel;
	}

	public VerticalPanel getPanel() {
		return panel;
	}

	public LinkedHashMap<String, DataMapperContent> getCountries() {
		return countries;
	}
	
	

}
