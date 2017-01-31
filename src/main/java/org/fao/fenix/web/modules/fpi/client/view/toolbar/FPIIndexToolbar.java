package org.fao.fenix.web.modules.fpi.client.view.toolbar;

import org.fao.fenix.web.modules.fpi.client.control.index.FPIIndexController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FPIIndexToolbar {

	private ToolBar toolbar;

	public FPIIndexToolbar(VerticalPanel parametersPanel, Table table) {
		
		toolbar = new ToolBar();

		IconButton iconButton;
		
		// SEND TO PDF
		iconButton = new IconButton("sendToPdf");
		iconButton.setTitle(BabelFish.print().sendToPDF());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_PDF, iconButton);

		// SEND TO EXCEL
		iconButton = new IconButton("sendToExcel");
		iconButton.setTitle(BabelFish.print().sendToExcel());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_EXCEL, iconButton);

		// SEND TO POWER POINT
		iconButton = new IconButton("sendToPowerPoint");
		iconButton.setTitle(BabelFish.print().sendToPowerPoint());
		toolbar.add(iconButton);
		toolbar.setData(FenixToolbarConstants.SEND_TO_POWERPOINT, iconButton);
		
		// SEND TO PDF ADAPTER
		IconButton exportToPdfButton = getIconButton(FenixToolbarConstants.SEND_TO_PDF);
		addListenerToButton(exportToPdfButton, FPIIndexController.buildExportToSelectionListener(table, parametersPanel, "pdf"));
		
		// SEND TO POWER POINT ADAPTER
		IconButton exportToPptButton = getIconButton(FenixToolbarConstants.SEND_TO_POWERPOINT);
		addListenerToButton(exportToPptButton, FPIIndexController.buildExportToSelectionListener(table, parametersPanel, "ppt"));
		
		// SEND TO EXCEL ADAPTER
		IconButton exportToXlsButton = getIconButton(FenixToolbarConstants.SEND_TO_EXCEL);
		addListenerToButton(exportToXlsButton, FPIIndexController.buildExportToSelectionListener(table, parametersPanel, "xls"));
		
		// CHANGE INDEX PARAMETERS
		//IconButton changeIndexParameters = new IconButton("textIcon");
		//changeIndexParameters.setTitle(I18N.print().changeIndexParameters());
		//addButtonToTheToolbar(changeIndexParameters, I18N.print().changeIndexParameters());
		//addListenerToButton(getIconButton(I18N.print().changeIndexParameters()), FPIIndexController.buildChangeIndexParametersSelectionListener(parametersPanel, table));
		
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