package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.ListBox;

public class RPanel extends ChartDesignerStepPanel {

	private VerticalPanel wrapper;
	
	private final static int SPACING = 3;
	
	private final static String WIDTH = "825px";

	private final static String FIELD_WIDTH = "650px";
	
	private final static String LABEL_WIDTH = "150px";
	
	private ListBox aggregationFunctionListBox;
	
	public RPanel(String suggestion, String width) {
		super(suggestion, width);
		wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setLayout(new FillLayout());
		aggregationFunctionListBox = new ListBox();
		this.getLayoutContainer().add(buildWrapperPanel());
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.add(buildAggregationFunctionPanel());
		return wrapper;
	}
	
	private HorizontalPanel buildAggregationFunctionPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING * 2);
		p.setBorders(true);
		p.setWidth(WIDTH);
		Html l = new Html("<div style='color: #1D4589; font-family: sans-serif; font-weight: bold;'>Aggregation Function</div>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		aggregationFunctionListBox.setWidth(FIELD_WIDTH);
		aggregationFunctionListBox.addItem("Average", "AVG");
		aggregationFunctionListBox.addItem("Sum", "SUM");
		aggregationFunctionListBox.addItem("Minimum", "MIN");
		aggregationFunctionListBox.addItem("Maximum", "MAX");
		aggregationFunctionListBox.addItem("Count", "COUNT");
		aggregationFunctionListBox.addItem("Standard Deviation", "STDDEV");
		aggregationFunctionListBox.addItem("Variance", "VARIANCE");
		p.add(aggregationFunctionListBox);
		return p;
	}

	public ListBox getAggregationFunctionListBox() {
		return aggregationFunctionListBox;
	}
	
}