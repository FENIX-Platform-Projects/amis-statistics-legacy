package org.fao.fenix.web.modules.olap.client.view;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.ListBox;

public class MTFunctionPanel extends MTStepPanel {

	private VerticalPanel wrapper;
	private final static int SPACING = 3;
	private final static String WIDTH = "825px";
	private final static String FIELD_WIDTH = "650px";
	private final static String LABEL_WIDTH = "150px";
	private ListBox aggregationFunctionListBox;
	private ListBox colFunctionListBox;
	private ListBox rowFunctionListBox;
	private ListBox subRowFunctionListBox;
	private ListBox subColFunctionListBox;
	
	private String displayFunctions[]={"Average",                              
		"Total",                                
		"Minimum",                              
		"Maximum",                              
		"Count",                                
		"Standard Deviation",                   
		"Variance" 
		}      ;

	private static String functionsCoreName[]= {
		"AVG",
		 "SUM",
		   "MIN",
		   "MAX",
		 "COUNT",
		  "STDDEV",
		    "VARIANCE"};

	public MTFunctionPanel(String suggestion, String width) {
		super(suggestion, width);
		wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setLayout(new FillLayout());
		aggregationFunctionListBox = new ListBox();
		colFunctionListBox = new ListBox();
		rowFunctionListBox = new ListBox();
		subRowFunctionListBox = new ListBox();
		subColFunctionListBox = new ListBox();

		this.getLayoutContainer().add(buildWrapperPanel());
		
		this.getLayoutContainer().add(buildWrapperPanelMac(rowFunctionListBox, "Row Summary"));
		this.getLayoutContainer().add(buildWrapperPanelMac(colFunctionListBox, "Column Summary"));
		this.getLayoutContainer().add(buildWrapperPanelMac(subRowFunctionListBox, "Sub-Row Summary"));
		this.getLayoutContainer().add(buildWrapperPanelMac(subColFunctionListBox, "Sub-Column Summary"));
		
		//this.getLayoutContainer().add(buildLowerWrapperPanel());
	}

	public static int getFunctionCoreIndex(String indof) {
		int i =0;
		
		for (i = 0; i < functionsCoreName.length; i++) {
			if(indof.equals(functionsCoreName[i]))
				break;
		}
		return i;
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
		/*Html l = new Html(
				"<div style='color: #1D4589; font-family: sans-serif; font-weight: bold;'>Aggregation Function</div>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		aggregationFunctionListBox.setWidth(FIELD_WIDTH);
		pplList(aggregationFunctionListBox);
		p.add(aggregationFunctionListBox);*/
		oneFunctionSetup(p,aggregationFunctionListBox,"Aggregation Function");
		return p;
	}
	private VerticalPanel buildWrapperPanelMac( ListBox listBox,String label) {
		wrapper.setSpacing(SPACING);
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING * 2);
		p.setBorders(true);
		p.setWidth(WIDTH);
		oneFunctionSetup(p,listBox,label);
		wrapper.add(p);
		return wrapper;
	}
	
	/*private VerticalPanel buildLowerWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.add(buildFourFunctionPanel());
		return wrapper;
	}
	private HorizontalPanel buildFourFunctionPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING * 2);
		p.setBorders(true);
		p.setWidth(WIDTH);

		oneFunctionSetup(p, colFunctionListBox, "Column Summary");
		oneFunctionSetup(p, rowFunctionListBox, "Row Summary");
		oneFunctionSetup(p, subRowFunctionListBox, "Sub-Row Summary");
		oneFunctionSetup(p, subColFunctionListBox, "Sub-Column Summary");

		return p;
	}*/

	private void oneFunctionSetup(HorizontalPanel p, ListBox listBox,
			String label) {
		Html l = new Html(
				"<div style='color: #1D4589; font-family: sans-serif; font-weight: bold;'>"
						+ label + "</div>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		listBox.setWidth(FIELD_WIDTH);
		pplList(listBox);
		p.add(listBox);
	}

	private void pplList(ListBox listBox) {
		
		for (int i = 0; i < functionsCoreName.length; i++) {
			listBox.addItem(displayFunctions[i], functionsCoreName[i]);
		}
		
		/*
		listBox.addItem("Average", "AVG");
		listBox.addItem("Total", "SUM");
		listBox.addItem("Minimum", "MIN");
		listBox.addItem("Maximum", "MAX");
		listBox.addItem("Count", "COUNT");
		listBox.addItem("Standard Deviation", "STDDEV");
		listBox.addItem("Variance", "VARIANCE");*/
	}

	public ListBox getAggregationFunctionListBox() {
		return aggregationFunctionListBox;
	}

	public ListBox getColFunctionListBox() {
		return colFunctionListBox;
	}

	public ListBox getRowFunctionListBox() {
		return rowFunctionListBox;
	}

	public ListBox getSubRowFunctionListBox() {
		return subRowFunctionListBox;
	}

	public ListBox getSubColFunctionListBox() {
		return subColFunctionListBox;
	}

}