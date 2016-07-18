package org.fao.fenix.web.modules.table.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbar;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.table.client.control.TableToolbarController;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.menu.AdapterMenuItem;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class TableToolbar extends FenixToolbar {

	private ToolBar toolbar;

	private IconButton iconButton;

//	private ToggleToolItem toggle;
	
	private boolean isDatasetEditable;
	private boolean isDatasetFlex;
	
	private String selectedPrecision;

	public TableToolbar(TableWindow tableWindow, Long resourceId,
			boolean isEditable, boolean isFlex, String datasetTitle) {

		toolbar = new ToolBar();
		isDatasetEditable = isEditable;
		isDatasetFlex = isFlex;
		
		appendViewIcons(toolbar, tableWindow, resourceId, datasetTitle); 

	}
	
	public void appendEditIcons(ToolBar toolbar, TableWindow tableWindow, Long resourceId, String datasetTitle) {
		
		// SAVE TABLE VIEW
		iconButton = new IconButton("save");
		iconButton.setTitle("Save Table View");
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SAVE TABLE VIEW ADAPTER
		IconButton saveTableViewButton = getIconButton(FenixToolbarConstants.SAVE);
	//	addListenerToButton(saveTableViewButton, TableToolbarController.saveTableView(tableWindow, resourceId));
		
		// SAVE TABLE VIEW AS
		iconButton = new IconButton("saveAs");
		iconButton.setTitle("Save Table View As...");
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE_AS, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SAVE TABLE VIEW AS ADAPTER
		IconButton saveTableViewAsButton = getIconButton(FenixToolbarConstants.SAVE_AS);
		addListenerToButton(saveTableViewAsButton, null); // TODO add controller
		
		// SWITCH VIEW
		iconButton = new IconButton("workspaceTable");
		iconButton.setTitle(BabelFish.print().switchToViewMode());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SWITCH_VIEW, iconButton);
		toolbar.add(new SeparatorToolItem());
		
		// SWITCH VIEW ADAPTER
		IconButton switchViewButton = getIconButton(FenixToolbarConstants.SWITCH_VIEW);
		addListenerToButton(switchViewButton, TableToolbarController.switchView(resourceId, tableWindow, this,  FenixToolbarConstants.VIEW));
		
		// SAVE TABLE
		iconButton = new IconButton("save");
		iconButton.setTitle(BabelFish.print().saveDataset());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE_TABLE, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SAVE TABLE ADAPTER
		IconButton saveTableButton = getIconButton(FenixToolbarConstants.SAVE_TABLE);
		addListenerToButton(saveTableButton, TableToolbarController.validateModifiedRecords(resourceId, tableWindow));

		// RESET CHANGES
		iconButton = new IconButton("undo");
		iconButton.setTitle(BabelFish.print().resetChanges());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.RESET, iconButton);
		toolbar.add(new SeparatorToolItem());

		// RESET ADAPTER
		IconButton resetChangesButton = getIconButton(FenixToolbarConstants.RESET);
		addListenerToButton(resetChangesButton, TableToolbarController
				.resetAllChanges(resourceId, tableWindow));

		// Copy Row 
		iconButton = new IconButton("copyTableRow");
		iconButton.setTitle(BabelFish.print().copyRow());
	    toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.COPY_TABLE_ROW, iconButton);
		toolbar.add(new SeparatorToolItem());

		// Copy Row ADAPTER
		IconButton copyRowButton = getIconButton(FenixToolbarConstants.COPY_TABLE_ROW);
		addListenerToButton(copyRowButton, TableToolbarController.copyTableRowListener(resourceId, tableWindow));

		// Insert Row
		iconButton = new IconButton("insertTableRow");
		iconButton.setTitle(BabelFish.print().insertRow());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.INSERT_TABLE_ROW, iconButton);
		toolbar.add(new SeparatorToolItem());

		// Insert Row ADAPTER
		IconButton insertRowButton = getIconButton(FenixToolbarConstants.INSERT_TABLE_ROW);
		addListenerToButton(insertRowButton, TableToolbarController.insertTableRowListener(resourceId, tableWindow));

		// Delete Row
		iconButton = new IconButton("deleteTableRow");
		iconButton.setTitle(BabelFish.print().deleteRow());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.REMOVE_TABLE_ROW, iconButton);
		toolbar.add(new SeparatorToolItem());

		// Remove Row ADAPTER
		IconButton removeRowButton = getIconButton(FenixToolbarConstants.REMOVE_TABLE_ROW);
		addListenerToButton(removeRowButton, TableToolbarController.confirmDeleteListener(resourceId, tableWindow));
	
		insertViewIcons(toolbar, tableWindow, resourceId, "EDIT_MODE", datasetTitle);

	}
	
	
	
	public void appendViewIcons(ToolBar toolbar, TableWindow tableWindow, Long resourceId, String datasetTitle) {

		// SWITCH VIEW
		if(isDatasetEditable) {
			
			// SAVE TABLE VIEW
			iconButton = new IconButton("save");
			iconButton.setTitle("Save Table View");
			toolbar.add(iconButton);
			toolbar.setData(FenixToolbarConstants.SAVE, iconButton);
			toolbar.add(new SeparatorToolItem());

			// SAVE TABLE VIEW ADAPTER
			IconButton saveTableViewButton = getIconButton(FenixToolbarConstants.SAVE);
//			addListenerToButton(saveTableViewButton, TableToolbarController.saveTableView(tableWindow, resourceId));
			
			// SAVE TABLE VIEW AS
			iconButton = new IconButton("saveAs");
			iconButton.setTitle("Save Table View As...");
			toolbar.add(iconButton);
			toolbar.setData(FenixToolbarConstants.SAVE_AS, iconButton);
			toolbar.add(new SeparatorToolItem());

			// SAVE TABLE VIEW AS ADAPTER
			IconButton saveTableViewAsButton = getIconButton(FenixToolbarConstants.SAVE_AS);
			addListenerToButton(saveTableViewAsButton, null); // TODO add controller
			
			// EDIT TABLE
			iconButton = new IconButton("editTable");
			iconButton.setTitle(BabelFish.print().switchToEditMode());
			toolbar.add(iconButton);
			toolbar.setData(FenixToolbarConstants.SWITCH_VIEW, iconButton);
			toolbar.add(new SeparatorToolItem());

			// SWITCH VIEW ADAPTER
			IconButton switchViewButton = (IconButton) getIconButton(FenixToolbarConstants.SWITCH_VIEW);
			addListenerToButton(switchViewButton, TableToolbarController.switchView(resourceId, tableWindow, this, FenixToolbarConstants.EDIT));
		}
		
		insertViewIcons(toolbar, tableWindow, resourceId, null, datasetTitle);

	}
	
	private void insertViewIcons(ToolBar toolbar, TableWindow tableWindow, Long resourceId, String displayMode, String datasetTitle) {
		
		ToggleButton showFilters = new ToggleButton();  
		showFilters.setText("Open Filters");
		//showFilters.setIcon(icon);
		toolbar.setData(FenixToolbarConstants.SHOW_FILTERS, showFilters);
		showFilters.addSelectionListener(TableToolbarController.showFilters(tableWindow));
		
		//showFilters.addSelectionListener(TableToolbarController.showFilters(tableWindow));
		toolbar.insert(showFilters, 0);  
		
		// SEND TO PDF
		iconButton = new IconButton("sendToPdf");
		iconButton.setTitle(BabelFish.print().sendToPDF());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_PDF, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO PDF ADAPTER
		IconButton exportToPdfButton = getIconButton(FenixToolbarConstants.SEND_TO_PDF);
		addListenerToButton(exportToPdfButton, TableToolbarController.buildExportToSelectionListener(tableWindow, resourceId, "pdf"));

		// SEND TO EXCEL
		iconButton = new IconButton("sendToExcel");
		iconButton.setTitle(BabelFish.print().sendToExcel());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_EXCEL, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO EXCEL ADAPTER
		IconButton exportToXlsButton = getIconButton(FenixToolbarConstants.SEND_TO_EXCEL);
		addListenerToButton(exportToXlsButton, TableToolbarController.excelExport(tableWindow, resourceId));

		// SEND TO POWER POINT
		iconButton = new IconButton("sendToPowerPoint");
		iconButton.setTitle(BabelFish.print().sendToPowerPoint());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_POWERPOINT, iconButton);
		toolbar.add(new SeparatorToolItem());


		// SEND TO POWER POINT ADAPTER
		IconButton exportToPptButton = getIconButton(FenixToolbarConstants.SEND_TO_POWERPOINT);
		addListenerToButton(exportToPptButton, TableToolbarController.buildExportToSelectionListener(tableWindow, resourceId, "ppt"));
		
		
		//Menu menu = new Menu();
		
		if(displayMode!=null && !displayMode.equalsIgnoreCase("EDIT_MODE") || displayMode==null) {
			// CHANGE PRECISION
			Html label = new Html("<b>&nbsp; Set Decimal Places &nbsp;</b>");
			AdapterMenuItem htmlAdapter = new AdapterMenuItem(label);
			//toolbar.add(htmlAdapter);
			
			SimpleComboBox<String> setPrecisionCombo = new SimpleComboBox<String>();
			setPrecisionCombo.setWidth(60);
			setPrecisionCombo.setTriggerAction(TriggerAction.ALL);
			
			setPrecisionCombo.add("0");
			setPrecisionCombo.add("1");
			setPrecisionCombo.add("2");
			setPrecisionCombo.add("3");
			setPrecisionCombo.add("4");
			
			if(getSelectedPrecision()!=null)
				setPrecisionCombo.setSimpleValue(getSelectedPrecision());
			else
				setPrecisionCombo.setSimpleValue("2");
			
			AdapterMenuItem adapter = new AdapterMenuItem(setPrecisionCombo);
			toolbar.setData(FenixToolbarConstants.CHANGE_PRECISION, setPrecisionCombo);
			
			//toolbar.add(adapter);
			
			//toolbar.add(new SeparatorToolItem());
			
		}
		
		
		Button openAsButton = new Button("Open Dataset As ..");  
		Menu viewMenu = new Menu();
		MenuItem item = new MenuItem("Chart");
		item.addSelectionListener(TableToolbarController.openChart(resourceId, datasetTitle));
		item.setIconStyle("workspaceChart");
		viewMenu.add(item);
		item = new MenuItem("Multidimensional Table");
		item.addSelectionListener(TableToolbarController.openMultidimensionalTable(resourceId, datasetTitle));
		item.setIconStyle("olap");
		viewMenu.add(item);
		openAsButton.setMenu(viewMenu);  
		toolbar.add(openAsButton);  	
		
		toolbar.add(new SeparatorToolItem());
		
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public IconButton getIconButton(String key) {
		return (IconButton) toolbar.getData(key);
	}
	
	public Button getButton(String key) {
		return (Button) toolbar.getData(key);
	}
	
	public SimpleComboBox getComboBox(String key) {
		return (SimpleComboBox) toolbar.getData(key);
	}

	protected void addListenerToButton(IconButton button, SelectionListener<IconButtonEvent> listener) {
		button.addSelectionListener(listener);
	}

	public void addButtonToTheToolbar(IconButton button, String key) {
		toolbar.add(button);
		toolbar.setData(key, button);
	}

	public String getSelectedPrecision() {
		return selectedPrecision;
	}

	public void setSelectedPrecision(String selectedPrecision) {
		this.selectedPrecision = selectedPrecision;
	}
	
}