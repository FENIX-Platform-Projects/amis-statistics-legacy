package org.fao.fenix.web.modules.wcct.client.view;

import org.fao.fenix.web.modules.wcct.client.control.WCCTController;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;


public class TableWestPanel {
	
	private ContentPanel westPanel;
	
	private VerticalPanel options;
	
	private ListBox countries;
	
	private ListBox commodities;
	
	private Button apply;
	
	private int heightPanel = 160;

	
	public TableWestPanel(){
		westPanel = new ContentPanel();
		options = new VerticalPanel();
		countries = new ListBox();
		countries.setMultipleSelect(true);
		countries.setSize("200px", "130px");
		commodities = new ListBox();
		commodities.setSize("200px", "130px");
		commodities.setMultipleSelect(true);
		apply = new Button("apply");

	}
	
	
	private void enhance(WCCTTool wcctTool) {
		apply.addSelectionListener(WCCTController.createTable(wcctTool, countries, commodities));	
	}
	
	public ContentPanel build(WCCTTool wcctTool){
		westPanel.setSize(250,390);
		westPanel.add(addOptions());
		westPanel.setBottomComponent(addButtonsPanel());
		westPanel.setBorders(true);
		westPanel.setHeaderVisible(false);
		enhance(wcctTool);
		return westPanel;
	}
	
	private VerticalPanel addOptions(){
		options.setSpacing(10);
		options.add(addGaulPanel());
		options.add(addCropPanel());
		return options;
	}
	
	private VerticalPanel addGaulPanel() {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(new Html("<b> <font size='2'>Countries</font></b>"));
		vPanel.add(countries);
		WCCTController.getCountries(countries);
		countries.addChangeListener(fillCommodities());
		
		vPanel.setHeight(heightPanel);
		return vPanel;
	}
	
	private VerticalPanel addCropPanel() {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(new Html("<b> <font size='2'>Crops</font></b>"));
		vPanel.add(commodities);
		vPanel.setHeight(heightPanel);
		return vPanel;
	}
	
	
	private HorizontalPanel addButtonsPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		panel.add(apply);
		panel.setHeight(35);
		return panel;
	}
	

	
	private ChangeListener fillCommodities() {
		ChangeListener t = new ChangeListener() {  
			public void onChange(Widget arg0) {
				System.out.println("commodity");
				WCCTController.fillCountryCommodity(countries, commodities);
			}

		};  
		return t;
	}

	

	public ListBox getCountries() {
		return countries;
	}

	public ListBox getCommodities() {
		return commodities;
	}

}
