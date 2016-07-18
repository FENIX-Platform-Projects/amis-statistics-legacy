package org.fao.fenix.web.modules.rainfall.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.client.control.GAULExplorerController;
import org.fao.fenix.web.modules.rainfall.client.control.RainfallToolController;
import org.fao.fenix.web.modules.rainfall.client.control.ToolConfigurationController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class RainfallTool extends FenixToolBase {

	private RainfallTabPanel rainfallTabPanel;
	
	private ReminderPanel reminderPanel;
	
	private Button create;
	
	private Button cancel;
	
	private Button saveNew;
	
	private Button addToUserSelection;
	
	public RainfallTool() {
		rainfallTabPanel = new RainfallTabPanel();
	}
	
	public void build() {
		buildEastPanel();
		buildCenterPanel();
		enhance();
		format();
	}
	
	private void enhance() {
		
//		RainfallToolController.fillCountryTree(this);
		
//		rainfallTabPanel.getTreeRegionPanel().getTree().addListener(Events.SelectionChange, RainfallToolController.createRegionLeaves(this));
//		rainfallTabPanel.getTreeRegionPanel().getOracle().addKeyListener(RainfallToolController.oracleListener(this));
		
		create.addSelectionListener(RainfallToolController.createListener(this));
		
		rainfallTabPanel.getConfigurationPanel().getEdit().addSelectionListener(ToolConfigurationController.loadConfiguration(this));
		rainfallTabPanel.getConfigurationPanel().getLoad().addSelectionListener(ToolConfigurationController.loadAndCreate(this));
		rainfallTabPanel.getConfigurationPanel().getRemove().addSelectionListener(ToolConfigurationController.deleteFavourite(this));
		
//		addToUserSelection.addSelectionListener(RainfallToolController.addUserSelection(rainfallTabPanel.getTreeRegionPanel().getTree(), rainfallTabPanel.getTreeRegionPanel().getStationTree(), reminderPanel.getTree()));
		addToUserSelection.addSelectionListener(GAULExplorerController.addUserSelection(rainfallTabPanel.getGaulExplorerPanel().getTree(), reminderPanel.getTree()));
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().add(rainfallTabPanel.build());
		getCenter().setBottomComponent(buildButtonsPanel());
		addCenterPartToWindow();
		getCenter().setHeading(BabelFish.print().controller());
	}
	
	private void buildEastPanel() {
		reminderPanel = new ReminderPanel();
		ContentPanel reminder = reminderPanel.build();
		reminder.setScrollMode(Scroll.ALWAYS);
		reminder.setHeight("150px");
		fillEastPart(reminder);
		getEastData().setSize(250);
		getEast().setHeading(BabelFish.print().userSelection());
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		create = new Button(BabelFish.print().create());
		saveNew = new Button(BabelFish.print().saveUpdate(), ToolConfigurationController.saveAs(this));
		addToUserSelection = new Button("Add To User Selection");
		buttonsPanel.add(addToUserSelection);
//		buttonsPanel.add(new HTML("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"));
		buttonsPanel.add(create);
		buttonsPanel.add(saveNew);
		return buttonsPanel;
	}
	
	private void format() {
		getToolBase().setWidth("100%");
		getToolBase().setHeight("300px");
		getToolBase().setHeaderVisible(false);
	}

	public RainfallTabPanel getRainfallTabPanel() {
		return rainfallTabPanel;
	}

	public Button getCreate() {
		return create;
	}

	public Button getCancel() {
		return cancel;
	}

	public ReminderPanel getReminderPanel() {
		return reminderPanel;
	}
	
}