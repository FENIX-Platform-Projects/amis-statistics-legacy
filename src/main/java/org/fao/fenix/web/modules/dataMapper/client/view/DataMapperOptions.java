package org.fao.fenix.web.modules.dataMapper.client.view;

import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.TabPanel;

public class DataMapperOptions {
	
	private VerticalPanel panel;

	
	private TextField<String> intervals;
	
	public DataMapperOptions() {
		panel = new VerticalPanel();
		panel.setSpacing(10);

		intervals = new TextField<String>();
		intervals.setWidth(150);
	}
	
	
	public VerticalPanel build() {

		
		
		panel.add(buildIntervals());
//		panel.add(value);
		
		return panel;
	}

	private HorizontalPanel buildIntervals() {
		HorizontalPanel panel = new HorizontalPanel();
		Html html = new Html("<div class='small-content'> Intervals: </div>");
		html.setWidth(250);
		
		
		intervals = new TextField<String>();
		intervals.setValue("6");
		
		panel.add(html);
		panel.add(intervals);
		
		return panel;
	}

	public TextField<String> getIntervals() {
		return intervals;
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
