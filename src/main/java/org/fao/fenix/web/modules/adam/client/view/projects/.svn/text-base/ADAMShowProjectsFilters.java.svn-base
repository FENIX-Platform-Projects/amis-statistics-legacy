package org.fao.fenix.web.modules.adam.client.view.projects;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomFilter;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.RootPanel;



public class ADAMShowProjectsFilters {

	VerticalPanel panel;
	
	CheckBox faoViewCheckBox;
	
	CheckBox sectorsViewCheckBox;
	
	ADAMCustomFilter sector;
	
	HorizontalPanel faoViewPanel;
	
	Button apply;
	
	public ADAMShowProjectsFilters() {
		panel = new VerticalPanel();
		panel.setSpacing(5);
		
		faoViewCheckBox = new CheckBox();
		sectorsViewCheckBox = new CheckBox();
		
		apply = new Button(BabelFish.print().apply());
		apply.setIconAlign(IconAlign.LEFT);
		apply.setIconStyle("wand");
		
		faoViewCheckBox.setValue(true);
		
		sector = new ADAMCustomFilter();

	}
	
	public VerticalPanel build() {
		panel.removeAll();
		
		panel.add(buildFaoView());
//		
		panel.add(buildSectorsView());
		
		panel.add(buildButton());
		
		return panel;
	}
	
	
	private HorizontalPanel buildFaoView(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		faoViewPanel = new HorizontalPanel();
		faoViewPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
//		faoViewPanel.setSpacing(5);

		
		panel.add(faoViewCheckBox);
		
		IconButton info = new IconButton("info");
		info.setToolTip("The OECD Development Assistance Committee (DAC) sector classifications include: Agriculture/Forestry/Fishery, Business and Other Services, Commodity Aid and General Programme Assistance, Government and Civil Society, Government and Civil Society, Health, Humanitarian Aid, Industry/Mining/Construction, Multisector/Cross-Cutting, Other Social Infrastructure and Services, Water and Sanitation.");
		faoViewPanel.add(info);
			
		Html label = new Html("<div class='small-content'>Filter Projects by FAO related Sectors and Purposes</div>");
		label.setWidth(ADAMConstants.PROJECTS_LABEL_WIDTH);
		faoViewPanel.add(label);
		
		panel.add(faoViewPanel);	
		return panel;
	}
	
	private HorizontalPanel buildSectorsView(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.TOP);
		panel.setSpacing(5);
		
		panel.add(sectorsViewCheckBox);
			
//		Html label = new Html("<div class='small-content'>Filter Projects by Sector</div>");
//		label.setWidth(ADAMConstants.PROJECTS_LABEL_WIDTH);
//		panel.add(label);
		
		panel.add(sector.build("Filter By Sector", "dac_sector", "Dac_"));
		
		sector.getPanel().setEnabled(false);
		
		return panel;
	}
	
	private HorizontalPanel buildButton(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.TOP);
		panel.setSpacing(5);
		
		panel.add(apply);
			

		return panel;
	}
	
	
	public FieldSet buildFilterFieldSet(ADAMShowProjectsPanel adamShowProjectsPanel) {	
		FieldSet filterFieldSet = new FieldSet();
		filterFieldSet.setCollapsible(true);
		filterFieldSet.setHeading("Filter");
		Integer panelWidth = Integer.valueOf(ADAMConstants.BIG_TABLE_CHART_WIDTH) - 20 ;
		filterFieldSet.setWidth(panelWidth);
		
		filterFieldSet.add(build());
		
		enancheListeners(adamShowProjectsPanel);
				
		return filterFieldSet;
	}
	
	private void enancheListeners(ADAMShowProjectsPanel adamShowProjectsPanel) {
		faoViewCheckBox.addListener(Events.Change, faoViewCheckSelectionChange());
		
		sectorsViewCheckBox.addListener(Events.Change, sectorsSelectionChange());

		//apply.addSelectionListener(ADAMController.refreshShowProjectsButtonEvent(adamShowProjectsPanel));
	}
	
	private Listener<FieldEvent> faoViewCheckSelectionChange() {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				sectorsViewCheckBox.setValue(!faoViewCheckBox.getValue());
				
				checkSelection();
			}
		};
	}
	
	private Listener<FieldEvent> sectorsSelectionChange() {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				faoViewCheckBox.setValue(!sectorsViewCheckBox.getValue());
			}
		};
	}
	
	private void checkSelection() {
		if( sectorsViewCheckBox.getValue() ) {
			sector.getPanel().setEnabled(true);
			sector.forceShowVisibility();
			faoViewPanel.setEnabled(false);	
		}
		else {
			sector.getPanel().setEnabled(false);
			sector.forceHideVisibility();
			faoViewPanel.setEnabled(true);
		}
		

		
		
	}

	public CheckBox getFaoViewCheckBox() {
		return faoViewCheckBox;
	}

	public CheckBox getSectorsViewCheckBox() {
		return sectorsViewCheckBox;
	}

	public ADAMCustomFilter getSector() {
		return sector;
	}
	
	
	
	
}
