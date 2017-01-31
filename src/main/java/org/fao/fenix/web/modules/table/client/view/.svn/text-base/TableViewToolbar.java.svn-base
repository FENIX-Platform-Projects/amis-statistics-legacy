package org.fao.fenix.web.modules.table.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.table.client.control.TableSaver;
import org.fao.fenix.web.modules.table.client.control.TableViewToolbarController;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class TableViewToolbar {

	private ToolBar toolbar;

	private IconButton iconButton;

	private boolean isDatasetEditable;

	private boolean isDatasetFlex;

	public TableViewToolbar(TableViewWindow tableWindow, Long resourceId, boolean isEditable, boolean isFlex) {
		toolbar = new ToolBar();
		isDatasetEditable = isEditable;
		isDatasetFlex = isFlex;
		appendViewIcons(toolbar, tableWindow, resourceId);
	}

	public void appendEditIcons(ToolBar toolbar, TableViewWindow tableWindow, Long resourceId) {

		// SAVE TABLE VIEW AS
		iconButton = new IconButton("saveAs");
		iconButton.setTitle("Save Table View As...");
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE_AS, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SAVE TABLE VIEW AS ADAPTER
		IconButton saveTableViewAsButton = getIconButton(FenixToolbarConstants.SAVE_AS);
		addListenerToButton(saveTableViewAsButton, null); // TODO add controller

		insertViewIcons(toolbar, tableWindow, resourceId);
	}

	public void appendViewIcons(ToolBar toolbar, TableViewWindow tableWindow, Long resourceId) {
		insertViewIcons(toolbar, tableWindow, resourceId);
	}

	private void insertViewIcons(ToolBar toolbar, TableViewWindow tableWindow, Long resourceId) {

		// SAVE TABLE VIEW
		iconButton = new IconButton("save");
		iconButton.setTitle("Save Table View");
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SAVE TABLE VIEW ADAPTER
		IconButton saveTableViewButton = getIconButton(FenixToolbarConstants.SAVE);
//		addListenerToButton(saveTableViewButton, TableSaver.);
		
		// SAVE TABLE VIEW AS
		iconButton = new IconButton("saveAs");
		iconButton.setTitle("Save Table View As...");
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE_AS, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SAVE TABLE VIEW AS ADAPTER
		IconButton saveTableViewAsButton = getIconButton(FenixToolbarConstants.SAVE_AS);
		addListenerToButton(saveTableViewAsButton, null); // TODO add controller
		
		// SEND TO PDF
		iconButton = new IconButton("sendToPdf");
		iconButton.setTitle(BabelFish.print().sendToPDF());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_PDF, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO PDF ADAPTER
		IconButton exportToPdfButton = getIconButton(FenixToolbarConstants.SEND_TO_PDF);
		addListenerToButton(exportToPdfButton, TableViewToolbarController.buildExportToSelectionListener(tableWindow, resourceId, "pdf"));

		// SEND TO EXCEL
		iconButton = new IconButton("sendToExcel");
		iconButton.setTitle(BabelFish.print().sendToExcel());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_EXCEL, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO EXCEL ADAPTER
		IconButton exportToXlsButton = getIconButton(FenixToolbarConstants.SEND_TO_EXCEL);
		addListenerToButton(exportToXlsButton, TableViewToolbarController.excelExport(tableWindow, resourceId));

		// SEND TO POWER POINT
		iconButton = new IconButton("sendToPowerPoint");
		iconButton.setTitle(BabelFish.print().sendToPowerPoint());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_POWERPOINT, iconButton);
		toolbar.add(new SeparatorToolItem());

		// SEND TO POWER POINT ADAPTER
		IconButton exportToPptButton = getIconButton(FenixToolbarConstants.SEND_TO_POWERPOINT);
		addListenerToButton(exportToPptButton, TableViewToolbarController.buildExportToSelectionListener(tableWindow, resourceId, "ppt"));
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