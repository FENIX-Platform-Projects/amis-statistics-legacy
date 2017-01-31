package org.fao.fenix.web.modules.fieldclimate.client.view;

import org.fao.fenix.web.modules.fieldclimate.client.control.FieldClimateController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class FieldClimateWindow extends FenixWindow {

	private FieldClimatePanel fieldClimatePanel;
	
	private static final String WINDOW_WIDTH = "300px";
	
	private static final String WINDOW_HEIGHT = "200px";
	
	public FieldClimateWindow() {
		fieldClimatePanel = new FieldClimatePanel();
	}
	
	public void build() {
		buildCenterPanel();
		fieldClimatePanel.getImportButton().addSelectionListener(FieldClimateController.synchronize(this));
		fieldClimatePanel.getCancelButton().addSelectionListener(FieldClimateController.close(this));
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(fieldClimatePanel.build());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("FieldClimate Importer");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("fieldClimate");
		getWindow().setCollapsible(false);
	}

	public FieldClimatePanel getFieldClimatePanel() {
		return fieldClimatePanel;
	}
	
}