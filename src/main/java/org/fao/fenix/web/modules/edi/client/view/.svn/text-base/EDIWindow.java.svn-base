package org.fao.fenix.web.modules.edi.client.view;

import org.fao.fenix.web.modules.edi.common.vo.EDISettings;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public abstract class EDIWindow extends FenixWindow {

	private EDITabPanel tabPanel;
	
	private EDIButtonsPanel buttonsPanel;
	
	private String WINDOW_WIDTH = "385px";
	
	private String WINDOW_HEIGHT = "525px";
	
	public void build(EDISettings datasource) {
		buildCenterPanel();
		format(datasource);
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(getTabPanel().build());
		getCenter().setBottomComponent(getButtonsPanel().build());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format(EDISettings datasource) {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setCollapsible(false);
		switch (datasource) {
			case FAOSTAT:
				getWindow().setIconStyle("faostat");
				getWindow().setHeading(BabelFish.print().faostatImporter());
			break;
			case ESOKO:
				getWindow().setIconStyle("esoko");
				getWindow().setHeading(BabelFish.print().esokoPricesImporter());
			break;
			case FEWSNET:
				getWindow().setIconStyle("usaid");
				getWindow().setHeading("FEWSNET Raster Importer");
			break;
		}
	}

	public EDITabPanel getTabPanel() {
		return tabPanel;
	}

	public void setTabPanel(EDITabPanel tabPanel) {
		this.tabPanel = tabPanel;
	}

	public EDIButtonsPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public void setButtonsPanel(EDIButtonsPanel buttonsPanel) {
		this.buttonsPanel = buttonsPanel;
	}

	public String getWINDOW_WIDTH() {
		return WINDOW_WIDTH;
	}

	public void setWINDOW_WIDTH(String wINDOWWIDTH) {
		WINDOW_WIDTH = wINDOWWIDTH;
	}

	public String getWINDOW_HEIGHT() {
		return WINDOW_HEIGHT;
	}

	public void setWINDOW_HEIGHT(String wINDOWHEIGHT) {
		WINDOW_HEIGHT = wINDOWHEIGHT;
	}
	
}