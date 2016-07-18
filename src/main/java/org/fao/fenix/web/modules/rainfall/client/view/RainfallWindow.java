package org.fao.fenix.web.modules.rainfall.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.client.control.ConfigurationController;
import org.fao.fenix.web.modules.rainfall.client.control.RainfallController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class RainfallWindow extends FenixWindow {

	private RainfallTabPanel rainfallTabPanel;
	
	private ReminderPanel reminderPanel;
	
	private Button create;
	
	private Button cancel;
	
	private Button saveNew;
	
	public RainfallWindow() {
		rainfallTabPanel = new RainfallTabPanel();
	}
	
	public void build() {
		buildEastPanel();
		buildCenterPanel();
		enhance();
		format();
		show();
	}
	
	private void enhance() {
		
		RainfallController.fillCountryTree(this);
		
//		rainfallTabPanel.getTreeRegionPanel().getTree().addListener(Events.SelectionChange, RainfallController.createRegionLeaves(this));
//		rainfallTabPanel.getTreeRegionPanel().getTree().addListener(Events.CheckChange, RainfallController.addUserSelection(reminderPanel.getTree()));
//		rainfallTabPanel.getTreeRegionPanel().getStationTree().addListener(Events.CheckChange, RainfallController.addUserSelection(reminderPanel.getTree()));
//		rainfallTabPanel.getTreeRegionPanel().getStationTree().addListener(Events.SelectionChange, RainfallController.addUserSelection(reminderPanel.getTree()));
//		rainfallTabPanel.getTreeRegionPanel().getOracle().addKeyListener(RainfallController.oracleListener(this));
		
		create.addSelectionListener(RainfallController.createListener(this));
		
		rainfallTabPanel.getConfigurationPanel().getEdit().addSelectionListener(ConfigurationController.loadConfiguration(this));
		rainfallTabPanel.getConfigurationPanel().getLoad().addSelectionListener(ConfigurationController.loadAndCreate(this));
		rainfallTabPanel.getConfigurationPanel().getRemove().addSelectionListener(ConfigurationController.deleteFavourite(this));
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
		fillEastPart(reminder);
		getEastData().setSize(250);
		getEast().setHeading(BabelFish.print().userSelection());
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		create = new Button(BabelFish.print().create());
		cancel = new Button(BabelFish.print().close(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getWindow().close();
			}
		});
		saveNew = new Button(BabelFish.print().saveUpdate(), ConfigurationController.saveAs(this));
		buttonsPanel.add(create);
		buttonsPanel.add(cancel);
		buttonsPanel.add(saveNew);
		return buttonsPanel;
	}
	
	private void format() {
		setSize("1000px", "325px");
		getWindow().setHeading(BabelFish.print().rainfallTool());
		setCollapsible(true);
		getWindow().setResizable(false);
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
