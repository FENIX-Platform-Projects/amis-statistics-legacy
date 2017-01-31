package org.fao.fenix.web.modules.wcct.client.view;

import org.fao.fenix.web.modules.wcct.client.control.WCCTController;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;


public class MapWestPanel {
	
	private HorizontalPanel mapPanel;
	
	private ContentPanel westPanel;
	
	private VerticalPanel options;
	
	private VerticalPanel othersPanel;
	
	private ListBox commodities;
	
	private ListBox month;
	
	private ListBox decade;
	
	private ListBox season;
	
	private ListBox others;
	
	private Button apply;
	
	private HTML otherLabel;
	
	private WCCTTool wt;
	
	
	private int heightPanel = 55;
		
	
	public MapWestPanel(){
		mapPanel = new HorizontalPanel();
		westPanel = new ContentPanel();
		options = new VerticalPanel();
		commodities = new ListBox();
		commodities.setSize("190px", "30px");
		month = new ListBox();
		month.setSize("190px", "30px");
		decade = new ListBox();
		decade.setSize("190px", "30px");
		season = new ListBox();
		season.setSize("190px", "30px");
		others = new ListBox();
		others.setSize("190px", "30px");
		othersPanel = new VerticalPanel();
		otherLabel = new HTML();
		apply = new Button("apply");
		
	
//		WCCTController.createMap();
	}
	
	public HorizontalPanel build(WCCTTool wcctTool) {
		mapPanel.add(buildWestPanel());
		wt = wcctTool;
		enhance();
		return mapPanel;
	}
	
	private void enhance() {
//		apply.addSelectionListener(WCCTController.getMap(this, wt));
		
	}
	
	private ContentPanel buildWestPanel(){
		westPanel.setSize(250,390);
		westPanel.add(addOptions());
		westPanel.setBottomComponent(addButtonsPanel());
		westPanel.setBorders(true);
		westPanel.setHeaderVisible(false);
		return westPanel;
	}
	
	private VerticalPanel addOptions(){
		options.setSpacing(10);
		options.add(addCropsPanel());
		options.add(addMonthPanel());
		options.add(addDecadePanel());
		options.add(addSeasonPanel());
		options.add(addOthersPanel());
		return options;
	}
	
	private VerticalPanel addCropsPanel() {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(new Html("<b> <font size='2'>Crop</font></b>"));
		vPanel.add(commodities);
		WCCTController.getCommodities(commodities);
		commodities.addChangeListener(fillSeason());
		commodities.addChangeListener(fillOthers());
		vPanel.setHeight(heightPanel);
		return vPanel;
	}
	
	private VerticalPanel addMonthPanel() {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(new Html("<b> <font size='2'>Month</font></b>"));//;I18N.print().month()));
		WCCTController.fillMonthList(month);
		vPanel.add(month);
		vPanel.setHeight(heightPanel);
		return vPanel;
	}
	
	private VerticalPanel addDecadePanel() { 
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(new Html("<b> <font size='2'>Decade</font></b>"));
		vPanel.add(decade);
		WCCTController.fillDecadeList(decade);
		vPanel.setHeight(heightPanel);
		return vPanel;
	}
	
	private VerticalPanel addSeasonPanel() {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(new Html("<b> <font size='2'>Season</font></b>"));
		season.addItem("---");
		vPanel.add(season);
		vPanel.setHeight(heightPanel);
		return vPanel;
	}
	

	private VerticalPanel addOthersPanel() { 
		othersPanel.setSpacing(5);
		othersPanel.add(new Html("<b> <font size='2'>Others</font></b>"));	
		othersPanel.add(others);
		others.addItem("---");
		othersPanel.setHeight(heightPanel);
		othersPanel.hide();
		return othersPanel;
	}
	
	private HorizontalPanel addButtonsPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		panel.add(apply);
		panel.setHeight(35);
//		vPanel.setBorders(true);
		return panel;
	}
	
	
	
	private ChangeListener fillSeason() {
		ChangeListener t = new ChangeListener() {  
			public void onChange(Widget arg0) {
				System.out.println("get seasons");
				WCCTController.getSeason(commodities, season);
			}

		};  
		return t;
	}
	
	private ChangeListener fillOthers() {
		ChangeListener t = new ChangeListener() {  
			public void onChange(Widget arg0) {
				System.out.println("get others");
				WCCTController.getOthers(commodities, others, othersPanel);
			}

		};  
		return t;
	}
	

	public ListBox getMonth() {
		return month;
	}

	public ListBox getDecade() {
		return decade;
	}

	public ListBox getCommodities() {
		return commodities;
	}

	public ContentPanel getWestPanel() {
		return westPanel;
	}


	public ListBox getOthers() {
		return others;
	}

	public ListBox getSeason() {
		return season;
	}

	public VerticalPanel getOthersPanel() {
		return othersPanel;
	}

	public HTML getOtherLabel() {
		return otherLabel;
	}

}
