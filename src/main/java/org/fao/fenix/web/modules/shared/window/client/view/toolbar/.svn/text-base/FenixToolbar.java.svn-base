package org.fao.fenix.web.modules.shared.window.client.view.toolbar;

import org.fao.fenix.web.modules.lang.client.FENIXMultilanguage;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;

public abstract class FenixToolbar {

	private ToolBar toolbar;

	private IconButton iconButton;
	
	private FENIXMultilanguage toolbarLang;

	public FenixToolbar() {

		toolbar = new ToolBar();
		toolbarLang = (FENIXMultilanguage)GWT.create(FENIXMultilanguage.class);

		// SAVE
		iconButton = new IconButton("mapSaveBtn");
		iconButton.setTitle(toolbarLang.save());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE, iconButton);

		// SAVE AS
		iconButton = new IconButton("saveAs");
		iconButton.setTitle(toolbarLang.saveAs());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SAVE_AS, iconButton);
		
		// SEND TO PROJECT
		iconButton = new IconButton("sendToProject");
		iconButton.setTitle(toolbarLang.sendToProject());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_PROJECT, iconButton);
		
		// SEND TO REPORT
		iconButton = new IconButton("sendToReport");
		iconButton.setTitle(toolbarLang.sendToReport());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_REPORT, iconButton);

		// EXPORT
		iconButton = new IconButton("export");
		iconButton.setTitle(toolbarLang.export());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.EXPORT, iconButton);

		// SEND TO PDF
		iconButton = new IconButton("sendToPdf");
		iconButton.setTitle(toolbarLang.sendToPDF());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_PDF, iconButton);

		// SEND TO EXCEL
		iconButton = new IconButton("sendToExcel");
		iconButton.setTitle(toolbarLang.sendToExcel());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_EXCEL, iconButton);

		// SEND TO POWER POINT
		iconButton = new IconButton("sendToPowerPoint");
		iconButton.setTitle(toolbarLang.sendToPowerPoint());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_POWERPOINT, iconButton);

		// PRINT
		iconButton = new IconButton("print");
		iconButton.setTitle(toolbarLang.print());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.PRINT, iconButton);

		// UNDO
		iconButton = new IconButton("undo");
		iconButton.setTitle(toolbarLang.undo());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.UNDO, iconButton);

		// VIEW METADATA
		iconButton = new IconButton("viewMetadata");
		iconButton.setTitle(toolbarLang.viewMetadata());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.VIEW_METADATA, iconButton);

		// SECURITY
		iconButton = new IconButton("security");
		iconButton.setTitle(toolbarLang.security());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SECURITY, iconButton);

		// HELP
		iconButton = new IconButton("help");
		iconButton.setTitle(toolbarLang.help());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.HELP, iconButton);

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
