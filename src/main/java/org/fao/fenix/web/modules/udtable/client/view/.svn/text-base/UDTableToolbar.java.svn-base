package org.fao.fenix.web.modules.udtable.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbar;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.udtable.client.control.UDTableToolbarController;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class UDTableToolbar extends FenixToolbar {

	private ToolBar toolbar;

//	private ToggleToolItem toggle;
	
	private boolean isDatasetEditable;
	private boolean isDatasetFlex;

	public UDTableToolbar(UDTableWindow tableWindow, Long resourceId,
			boolean isEditable, boolean isFlex) {

		toolbar = new ToolBar();
		isDatasetEditable = isEditable;
		isDatasetFlex = isFlex;
		
		appendViewIcons(toolbar, tableWindow, resourceId); 

	}
	
	public void appendEditIcons(ToolBar toolbar, UDTableWindow tableWindow, Long resourceId) {

		IconButton iconButton;
		
		// SWITCH VIEW
		iconButton = new IconButton("workspaceTable");
		iconButton.setTitle(BabelFish.print().switchToViewMode());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SWITCH_VIEW, iconButton);
		toolbar.add(new SeparatorToolItem());
		
		// SWITCH VIEW ADAPTER
		IconButton switchViewButton = getIconButton(FenixToolbarConstants.SWITCH_VIEW);
		addListenerToButton(switchViewButton, UDTableToolbarController.switchView(resourceId, tableWindow, this,  FenixToolbarConstants.VIEW));
		
		// SAVE TABLE
		iconButton = new IconButton("save");
		iconButton.setTitle(BabelFish.print().saveDataset());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE_TABLE, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SAVE TABLE ADAPTER
		IconButton saveTableButton = getIconButton(FenixToolbarConstants.SAVE_TABLE);
		addListenerToButton(saveTableButton, UDTableToolbarController.validateModifiedRecords(resourceId, tableWindow));

		// RESET CHANGES
		iconButton = new IconButton("undo");
		iconButton.setTitle(BabelFish.print().resetChanges());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.RESET, iconButton);
		toolbar.add(new SeparatorToolItem());

		// RESET ADAPTER
		IconButton resetChangesButton = getIconButton(FenixToolbarConstants.RESET);
		addListenerToButton(resetChangesButton, UDTableToolbarController
				.resetAllChanges(resourceId, tableWindow));

		// Copy Row - inactive
		iconButton = new IconButton("copyTableRow");
		iconButton.setTitle(BabelFish.print().copyRow());
		// toolbar.add(new AdapterToolItem(iconButton));
		toolbar.setData(FenixToolbarConstants.COPY_TABLE_ROW, iconButton);
		// toolbar.add(new SeparatorToolItem());

		// Copy Row ADAPTER
		IconButton copyRowButton = getIconButton(FenixToolbarConstants.COPY_TABLE_ROW);
		addListenerToButton(copyRowButton, UDTableToolbarController.copyTableRowListener(resourceId, tableWindow));

		// Insert Row
		iconButton = new IconButton("insertTableRow");
		iconButton.setTitle(BabelFish.print().insertRow());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.INSERT_TABLE_ROW, iconButton);
		toolbar.add(new SeparatorToolItem());

		// Insert Row ADAPTER
		IconButton insertRowButton = getIconButton(FenixToolbarConstants.INSERT_TABLE_ROW);
		addListenerToButton(insertRowButton, UDTableToolbarController.insertTableRowListener(resourceId, tableWindow));

		// Delete Row
		iconButton = new IconButton("deleteTableRow");
		iconButton.setTitle(BabelFish.print().deleteRow());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.REMOVE_TABLE_ROW, iconButton);
		toolbar.add(new SeparatorToolItem());

		// Remove Row ADAPTER
		IconButton removeRowButton = getIconButton(FenixToolbarConstants.REMOVE_TABLE_ROW);
		addListenerToButton(removeRowButton, UDTableToolbarController.confirmDeleteListener(resourceId, tableWindow));
	
		insertViewIcons(toolbar, tableWindow, resourceId);
	}
	
	
	
	public void appendViewIcons(ToolBar toolbar, UDTableWindow tableWindow, Long resourceId) {

		// SWITCH VIEW
		if(isDatasetEditable && !isDatasetFlex) {
			IconButton iconButton = new IconButton("editTable");
			iconButton.setTitle(BabelFish.print().switchToEditMode());
			toolbar.add(iconButton);
			toolbar.setData(FenixToolbarConstants.SWITCH_VIEW, iconButton);
			toolbar.add(new SeparatorToolItem());

			// SWITCH VIEW ADAPTER
			IconButton switchViewButton = getIconButton(FenixToolbarConstants.SWITCH_VIEW);
			addListenerToButton(switchViewButton, UDTableToolbarController.switchView(resourceId, tableWindow, this, FenixToolbarConstants.EDIT));
		}
		
		insertViewIcons(toolbar, tableWindow, resourceId);

	}
	
	private void insertViewIcons(ToolBar toolbar, UDTableWindow tableWindow, Long resourceId) {
		IconButton iconButton;
		
		// SEND TO PDF
		iconButton = new IconButton("sendToPdf");
		iconButton.setTitle(BabelFish.print().sendToPDF());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_PDF, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO PDF ADAPTER
		IconButton exportToPdfButton = getIconButton(FenixToolbarConstants.SEND_TO_PDF);
		addListenerToButton(exportToPdfButton, UDTableToolbarController.buildExportToSelectionListener(tableWindow, resourceId, "pdf"));

		// SEND TO EXCEL
		iconButton = new IconButton("sendToExcel");
		iconButton.setTitle(BabelFish.print().sendToExcel());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_EXCEL, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO EXCEL ADAPTER
		IconButton exportToXlsButton = getIconButton(FenixToolbarConstants.SEND_TO_EXCEL);
		addListenerToButton(exportToXlsButton, UDTableToolbarController.buildExportToSelectionListener(tableWindow, resourceId, "xls"));

		// SEND TO POWER POINT
		iconButton = new IconButton("sendToPowerPoint");
		iconButton.setTitle(BabelFish.print().sendToPowerPoint());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_POWERPOINT, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO POWER POINT ADAPTER
		IconButton exportToPptButton = getIconButton(FenixToolbarConstants.SEND_TO_POWERPOINT);
		addListenerToButton(exportToPptButton, UDTableToolbarController.buildExportToSelectionListener(tableWindow, resourceId, "ppt"));
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public IconButton getIconButton(String key) {
		return (IconButton) toolbar.getData(key);
	}

	protected void addListenerToButton(IconButton button, SelectionListener<IconButtonEvent> listener) {
		button.addSelectionListener(listener);
	}

	public void addButtonToTheToolbar(IconButton button, String key) {
		toolbar.add(button);
		toolbar.setData(key, button);
	}
}
