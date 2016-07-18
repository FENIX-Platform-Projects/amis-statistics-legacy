package org.fao.fenix.web.modules.esoko.client.view;

import org.fao.fenix.web.modules.esoko.client.control.EsokoController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

@SuppressWarnings("deprecation")
public class EsokoWindow extends FenixWindow {

	private EsokoPanel esokoPanel;
	
	private static final String WINDOW_WIDTH = "400px";
	
	private static final String WINDOW_HEIGHT = "170px";
	
	public EsokoWindow() {
		esokoPanel = new EsokoPanel();
	}
	
	public void build() {
		buildCenterPanel();
		esokoPanel.getImportButton().addSelectionListener(EsokoController.importEsokoPrices(this));
		esokoPanel.getCancelButton().addSelectionListener(EsokoController.closeWindow(this));
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(esokoPanel.build());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading(BabelFish.print().esokoPricesImporter());
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("esoko");
		getWindow().setCollapsible(false);
	}

	public EsokoPanel getEsokoPanel() {
		return esokoPanel;
	}
	
}