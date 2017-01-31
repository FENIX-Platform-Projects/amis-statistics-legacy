package org.fao.fenix.web.modules.coding.client.view.toolbar;

import org.fao.fenix.web.modules.coding.client.control.toolbar.CodingToolbarController;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class CodingToolbar {

	private ToolBar toolbar;


	public CodingToolbar(CodingSearchResults codingSearchResults) {
		
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
		IconButton exportToPdfButton = (IconButton) getIconButton(FenixToolbarConstants.SEND_TO_PDF);
		addListenerToButton(exportToPdfButton, CodingToolbarController.buildExportToSelectionListener(codingSearchResults, "pdf"));
		
		// SEND TO POWER POINT ADAPTER
		IconButton exportToPptButton = (IconButton) getIconButton(FenixToolbarConstants.SEND_TO_POWERPOINT);
		addListenerToButton(exportToPptButton, CodingToolbarController.buildExportToSelectionListener(codingSearchResults, "ppt"));
		
		// SEND TO EXCEL ADAPTER
		IconButton exportToXlsButton = (IconButton) getIconButton(FenixToolbarConstants.SEND_TO_EXCEL);
		addListenerToButton(exportToXlsButton, CodingToolbarController.buildExportToSelectionListener(codingSearchResults, "xls"));
		
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