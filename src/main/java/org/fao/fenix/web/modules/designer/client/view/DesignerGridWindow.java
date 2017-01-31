package org.fao.fenix.web.modules.designer.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class DesignerGridWindow extends FenixWindow {

	private DesignerGridPanel designerGridPanel;
	
	private static final String PORTRAIT_WINDOW_WIDTH = "400px";
	
	private static final String PORTRAIT_WINDOW_HEIGHT = "515px";
	
	private static final String LANDSCAPE_WINDOW_WIDTH = "495px";
	
	private static final String LANDSCAPE_WINDOW_HEIGHT = "420px";
	
	private String orientation;
	
	private int offset;
	
	public DesignerGridWindow() {
		designerGridPanel = new DesignerGridPanel();
		offset = 25;
	}
	
	public void buildPortrait(int pageNumber) {
		buildCenterPanelPortrait();
		orientation = "PORTRAIT";
		formatPortrait(pageNumber);
		show();
		this.getWindow().setPagePosition(150 + offset, this.getWindow().getAbsoluteTop());
		offset += 25;
	}
	
	public void buildLandscape(int pageNumber) {
		buildCenterPanelLandscape();
		orientation = "LANDSCAPE";
		formatLandscape(pageNumber);
		show();
		this.getWindow().setPagePosition(150 + offset, this.getWindow().getAbsoluteTop());
		offset += 25;
	}
	
	private void buildCenterPanelLandscape() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(designerGridPanel.buildLandscape());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void buildCenterPanelPortrait() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(designerGridPanel.buildPortrait());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void formatLandscape(int pageNumber) {
		setSize(LANDSCAPE_WINDOW_WIDTH, LANDSCAPE_WINDOW_HEIGHT);
		getWindow().setHeading("Landscape Page #" + pageNumber);
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setCollapsible(false);
		getWindow().setIconStyle("landscape");
	}
	
	private void formatPortrait(int pageNumber) {
		setSize(PORTRAIT_WINDOW_WIDTH, PORTRAIT_WINDOW_HEIGHT);
		getWindow().setHeading("Portrait Page #" + pageNumber);
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setCollapsible(false);
		getWindow().setIconStyle("portrait");
	}

	public DesignerGridPanel getDesignerGridPanel() {
		return designerGridPanel;
	}

	public String getOrientation() {
		return orientation;
	}
	
}