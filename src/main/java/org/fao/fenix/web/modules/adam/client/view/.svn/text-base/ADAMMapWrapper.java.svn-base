package org.fao.fenix.web.modules.adam.client.view;

import java.util.ArrayList;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.Widget;



public class ADAMMapWrapper {
	
	private ContentPanel panel;

	private ADAMMapPanel adamMapPanel;
	
	private String language;
	
	private ADAMResultVO adamResultVO;
	
	private String currentPosition;
	
	private String smallColor;
	
	/** this is the main panel **/
	private VerticalPanel contentPanel;
	
	/** this is the toolbar panel **/
	private HorizontalPanel toolbarPanel;
	
	/** this panel contains the openlayer and the infopanel **/
	private HorizontalPanel mapContentPanel;
	
	/** this panel infopanel **/
	private VerticalPanel infoPanel;
	
	private MenuItem pdf = new MenuItem("Export Map");
	
	public ADAMMapWrapper(String color, String language) {
		this.language = language;
		this.smallColor = color;
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.setStyleAttribute("background-color", color);
		contentPanel = new VerticalPanel();
		mapContentPanel = new HorizontalPanel();
		mapContentPanel.setSpacing(2);
		toolbarPanel = new HorizontalPanel();
		infoPanel = new VerticalPanel();
		adamResultVO = new ADAMResultVO();
		
		adamMapPanel = new ADAMMapPanel(language);
		adamMapPanel.setTitle("adamMapPanel");
		
		pdf.setIconStyle("mapExportAsImage");
		
		enancheButtons();
	}
	
	private void enancheButtons() { 
		pdf.addSelectionListener(ADAMController.exportMapAsZip(this, language));
	}
	
	public ContentPanel buildInitializeMapPanel() {
		contentPanel = new VerticalPanel();
//		contentPanel.setStyleAttribute("background-color", smallColor);
//		mapContentPanel.setStyleAttribute("background-color", smallColor);
//		toolbarPanel.setStyleAttribute("background-color", smallColor);
//		infoPanel.setStyleAttribute("background-color", smallColor);

		
		toolbarPanel.add(buildSmallToolBar("Total budget per country ("+ADAMController.baseUnit+")", ADAMController.fullScreenMap(this)));


		mapContentPanel.add(buildMapPanel(ADAMConstants.SMALL_MAP_WIDTH, ADAMConstants.SMALL_MAP_HEIGHT));
		infoPanel.add(buildSmallInfoPanel(""));
		mapContentPanel.add(infoPanel);
		
		
		contentPanel.add(toolbarPanel);
		contentPanel.add(mapContentPanel);
		
		panel.add(contentPanel);
		panel.setHeight(ADAMConstants.SMALL_BOX_HEIGHT);
		panel.setWidth(ADAMConstants.SMALL_BOX_WIDTH);
		return panel;
	}
	
	
	
	public ContentPanel buildSmallMapPanel(String title, String infoTile, String color, String width, String height, SelectionListener<ButtonEvent> objectSizeListener) {
		this.smallColor = color;
		removePanels();
		
	
//		contentPanel.setStyleAttribute("background-color", smallColor);
//		mapContentPanel.setStyleAttribute("background-color", smallColor);
//		toolbarPanel.setStyleAttribute("background-color", smallColor);
//		infoPanel.setStyleAttribute("background-color", smallColor);
		

		
		toolbarPanel.add(buildSmallToolBar(title, objectSizeListener));
		
		adamMapPanel.reSizeTool(width, height, 6);
		adamMapPanel.setTitle("adamMapPanel");
//		h.add(adamMapPanel.getToolBase());
		
		infoPanel.add(buildSmallInfoPanel(infoTile));
		mapContentPanel.add(infoPanel);
		
		
		contentPanel.add(toolbarPanel);
		contentPanel.add(mapContentPanel);
		
//		contentPanel.setHeight(ADAMConstants.SMALL_BOX_HEIGHT);
		panel.setHeight(ADAMConstants.SMALL_BOX_HEIGHT);
		panel.setWidth(ADAMConstants.SMALL_BOX_WIDTH);

		panel.layout();
		return panel;
	}
	
	private ContentPanel buildMapPanel(String width, String height) {
		return adamMapPanel.build(width, height);
	}

	
	private HorizontalPanel buildSmallToolBar(String title, SelectionListener<ButtonEvent> objectSizeListener) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		h1.add(addInfo(""));
		
		Html label = new Html("<div class='title-box'>" + title + "</div>");
		label.setWidth(ADAMConstants.SMALL_MENU_GAP_WIDTH);
		h1.add(label);
		
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("More...");
		more.setIconStyle("gear");

		more.setMenu(moreMenu);
	
		


		moreMenu.add(pdf);
		
//		Button excel = new Button("Export Excel");
//		excel.addSelectionListener(ADAMController.exportExcelTable(vo));
		
		

		Button full = new Button("Full Screen");
		full.setIconStyle("arrow_out");
		full.addSelectionListener(objectSizeListener);
		h1.add(more);
//		h1.add(pdf);
		h1.add(full);
		
		return h1;
	}
	
	private VerticalPanel buildSmallInfoPanel(String title) {
		VerticalPanel v = new VerticalPanel();
		v.setHorizontalAlign(HorizontalAlignment.LEFT);
		v.setVerticalAlign(VerticalAlignment.MIDDLE);
		v.setSpacing(5);
	
		Html label = new Html();
		label.setWidth(ADAMConstants.SMALL_LABEL_MAP_WIDTH);
		
		if ( title != null ) {
			label = new Html("<div class='title-box'>" + title + "</div>");
		}
		
		v.add(label);
		
		System.out.println("adamResultVO.getLayersMap().isEmpty() " + adamResultVO.getLayersMap().isEmpty());
		System.out.println("adamResultVO.getLayersMap().size() " + adamResultVO.getLayersMap().size());
		
		for(Long key : adamResultVO.getLayersMap().keySet()) {
			System.out.println("adamResultVO.getLayersMap(): " + adamResultVO.getLayersMap().get(key).getLayerName() + " | " + adamResultVO.getLayersMap().get(key).isHidden());
			if ( !adamResultVO.getLayersMap().get(key).isHidden() ) {
				v.add(new Html("<div align='left'> <img src='" + adamResultVO.getLayersMap().get(key).getLegendUrl() + "'" + "> </div>"));
				v.layout();
			}
		}


		
		
		return v;
	}
	
	public ContentPanel buildBigMapPanel(String title, String infoTile, String color, String width, String height, SelectionListener<ButtonEvent> objectSizeListener) {
		removePanels();

		toolbarPanel.add(buildBigToolBar(title, objectSizeListener));
		
		System.out.println("BIG MAP WIDTH: " + width);
		System.out.println("BIG MAP HEIGHT: " + height);
//		adamMapPanel.reSizeTool(width, height, 12);
		
		infoPanel.add(buildBigInfoPanel(infoTile));
		mapContentPanel.add(infoPanel);
		
		
		contentPanel.add(toolbarPanel);
		contentPanel.add(mapContentPanel);
		

		panel.setVisible(true);
		panel.layout();
		contentPanel.layout();
		adamMapPanel.getAdamOLPanel().getOlPanel().layout();
		
		panel.setHeight(ADAMConstants.BIG_BOX_HEIGHT);
		panel.setWidth(ADAMConstants.BIG_BOX_WIDTH);
	
		mapContentPanel.layout();
		adamMapPanel.reSizeTool(width, height, 12);
		return panel;
	}
	
	private HorizontalPanel buildBigToolBar(String title, SelectionListener<ButtonEvent> objectSizeListener) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		h1.add(addInfo(""));
	
		
		Html label = new Html("<div class='title-box'>" + title + "</div>");
		label.setWidth(ADAMConstants.BIG_LABEL_WIDTH);
		h1.add(label);
		
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("More...");
		more.setIconStyle("gear");
		more.setIconAlign(IconAlign.LEFT);

		more.setMenu(moreMenu);
	
		


		moreMenu.add(pdf);
		
//		Button excel = new Button("Export Excel");
//		excel.addSelectionListener(ADAMController.exportExcelTable(vo));
		
		

		Button full = new Button("Default View");
		full.setIconStyle("arrow_out");
		full.setIconAlign(IconAlign.LEFT);
		full.addSelectionListener(objectSizeListener);
		h1.add(more);
//		h1.add(pdf);
		h1.add(full);
		
		return h1;
	}
	
	private VerticalPanel buildBigInfoPanel(String title) {
		VerticalPanel v = new VerticalPanel();
		v.setHorizontalAlign(HorizontalAlignment.LEFT);
		v.setVerticalAlign(VerticalAlignment.MIDDLE);
		v.setSpacing(5);
	
		if ( title != null ) {
			Html label = new Html("<div class='title-box'>" + title + "</div");
			label.setWidth(ADAMConstants.BIG_LABEL_WIDTH);
			v.add(label);
		}
		
		System.out.println("adamResultVO.getLayersMap().isEmpty() " + adamResultVO.getLayersMap().isEmpty());
		System.out.println("adamResultVO.getLayersMap().size() " + adamResultVO.getLayersMap().size());
		
		for(Long key : adamResultVO.getLayersMap().keySet()) {
			System.out.println("adamResultVO.getLayersMap(): " + adamResultVO.getLayersMap().get(key).getLayerName() + " | " + adamResultVO.getLayersMap().get(key).isHidden());
			if ( !adamResultVO.getLayersMap().get(key).isHidden() ) {
				v.add(new Html("<div align='left'> <img src='" + adamResultVO.getLayersMap().get(key).getLegendUrl() + "'" + "> </div>"));
				v.layout();
			}
		}
		
//		System.out.println("adamResultVO.getGeoViews().isEmpty(): " + adamResultVO.getGeoViews().isEmpty());
//		System.out.println("adamResultVO.getGeoViews().size(): " + adamResultVO.getGeoViews().size());
//		if ( !adamResultVO.getGeoViews().isEmpty()) {
//			System.out.println("adamResultVO.getGeoViews().get(adamResultVO.getGeoViews().size() -1): " + adamResultVO.getGeoViews().get(adamResultVO.getGeoViews().size() -1));
//			Integer idx = adamResultVO.getGeoViews().size() -1;
//			System.out.println("adamResultVO.getGeoViews().get(idx).getLagendUrl() " +adamResultVO.getGeoViews().get(idx).getLagendUrl());
//			infoPanel.add(new Html("<div align='left'> <img src='" + adamResultVO.getGeoViews().get(idx).getLagendUrl() + "'" + "> </div>"));
//			infoPanel.layout();
//		}
		
		
		return v;
	}
	
	public void setFullScreen() {
		buildBigMapPanel("Total budget per country ("+ADAMController.baseUnit+")", "Legend", smallColor, ADAMConstants.BIG_MAP_WIDTH, ADAMConstants.BIG_MAP_HEIGHT, ADAMController.smallScreenMap(currentPosition, smallColor, this));
	}
	
	public void setSmallScreen() {
		buildSmallMapPanel("Total budget per country ("+ADAMController.baseUnit+")", null, smallColor, ADAMConstants.SMALL_MAP_WIDTH, ADAMConstants.SMALL_MAP_HEIGHT, ADAMController.fullScreenMap(this));
	}

	public ContentPanel getPanel() {
		return panel;
	}


	public ADAMMapPanel getAdamMapPanel() {
		return adamMapPanel;
	}


	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	private void removePanels() {
	
		toolbarPanel.removeAll();
		infoPanel.removeAll();
	}
	
	public static HorizontalPanel addInfo(String text) {
		HorizontalPanel panel = new HorizontalPanel();
		IconButton icon = new IconButton("adam_info");
		icon.setToolTip("Additional Information");
		icon.addSelectionListener(ADAMController.showInfo(text));
		panel.add(icon);
		return panel;
	}


	public ADAMResultVO getAdamResultVO() {
		return adamResultVO;
	}


	public String getSmallColor() {
		return smallColor;
	}


	public String getCurrentPosition() {
		return currentPosition;
	}

	public VerticalPanel getInfoPanel() {
		return infoPanel;
	}

	public void setAdamResultVO(ADAMResultVO adamResultVO) {
		this.adamResultVO = adamResultVO;
	}
	
	
	
	
	
}