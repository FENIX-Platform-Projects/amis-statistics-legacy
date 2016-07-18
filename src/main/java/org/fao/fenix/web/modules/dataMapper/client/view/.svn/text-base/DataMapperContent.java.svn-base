package org.fao.fenix.web.modules.dataMapper.client.view;

import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.TabPanel;

public class DataMapperContent {
	
	private HorizontalPanel panel;
	
	private GaulModelData country;
	
	private TextField<String> value;
	
	public DataMapperContent() {
		panel = new HorizontalPanel();
		country = new GaulModelData();
		value = new TextField<String>();
		value.setWidth(150);
	}
	
	
	public HorizontalPanel build(String code, String label, Double v) {
		country.setGaulCode(code);
		country.setGaulLabel(label);
		
//		if ( v != null)
//			value.setValue(v);
		
		Html html = new Html("<div class='small-content'>" + label + "</div>");
		html.setWidth(250);
		
		panel.add(html);
		panel.add(value);
		
		return panel;
	}


	public TextField<String> getValue() {
		return value;
	}


//	public TextField<Double> getValue() {
//		return value;
//	}
//
//
//	public void setValue(TextField<Double> value) {
//		this.value = value;
//	}
	
	
	
}
