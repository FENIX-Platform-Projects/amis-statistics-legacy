package org.fao.fenix.web.modules.haiticnsatool.client.view;


import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.haiticnsatool.client.control.HaitiCNSAController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class HaitiCNSAPanel extends FenixToolBase {
	
	private ContentPanel panel;
	
	private HaitiCNSACommandPanel commandPanel;
	
	// main map panel
	private HaitiCNSAMapPanel mapPanel;
	
	
	// other dekads map panel
	private LinkedHashMap<String, HaitiCNSAMapPanel> mapPanelDekadsHM;
	
	private HaitiCNSAChartPanel chartPanel;
	
	private HaitiCNSALegendPanel legendPanel;
	
	private String language;
	
	private String height;
	
	private String width;
	
	private String gaul0code;
	
	private int dekads;
	
	public HaitiCNSAPanel(String gaul0code, String width, String height, String language, int dekads) {
		this.language = language;
		this.height = height;
		this.width = width;
		this.gaul0code = gaul0code;
		this.dekads = dekads;
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		// map panel
		mapPanel = new HaitiCNSAMapPanel(gaul0code, width, height, language, 0);
		

		mapPanelDekadsHM = new LinkedHashMap<String, HaitiCNSAMapPanel>();
//		for(int i=1; i < decads; i++) {
//			HaitiCNSAMapPanel mapPanelDekad = new HaitiCNSAMapPanel(gaul0code, width, height, language, i);
//			mapPanelDekadsHM.put("dekad" + i, mapPanelDekad);
//		}
		
		
		commandPanel = new HaitiCNSACommandPanel();		
		chartPanel = new HaitiCNSAChartPanel();
		legendPanel = new HaitiCNSALegendPanel(gaul0code, width, height, language);
		
	}
	
	public ContentPanel build() {
		
		mapPanel.build(this);
		panel.add(commandPanel.build(language, this));			
		
		buildMapsPanel();


		panel.add(addSpace());
		panel.add(legendPanel.build(this));
		panel.add(addSpace());
		panel.add(chartPanel.build(language, width, height));
		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		format();
		initializeCNSATool();
		return panel;		
	}
	
	private void buildMapsPanel() {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(15);
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(5);
		
		horizontalPanel.add(mapPanel.getToolBase());
		
		for(int i=1; i < dekads; i++) {
			HaitiCNSAMapPanel mapPanelDekad = new HaitiCNSAMapPanel(gaul0code, width, height, language, i);
			mapPanelDekad.build(this);
			mapPanelDekadsHM.put(mapPanelDekad.getHaitiOLPanel().getMapPanel().getMapDivId(), mapPanelDekad);
			
			if ( i % 2 == 1){
				horizontalPanel.add(mapPanelDekad.getToolBase());
				verticalPanel.add(horizontalPanel);
			}
			else {
				horizontalPanel = new HorizontalPanel();
				horizontalPanel.setSpacing(5);
				horizontalPanel.add(mapPanelDekad.getToolBase());
			}

		}
//		for(String key : mapPanelDekadsHM.keySet()) {
//			mapPanelDekadsHM.get(key).build(this);
//			System.out.println("i: " + i + " | " + (i %2));
//			if ( i % 2 == 0){
//				horizontalPanel.add(mapPanelDekadsHM.get(key).getToolBase());
//				verticalPanel.add(horizontalPanel);
//			}
//			else {
//				horizontalPanel = new HorizontalPanel();
//				horizontalPanel.setSpacing(5);
//				horizontalPanel.add(mapPanelDekadsHM.get(key).getToolBase());
//			}
//			i++;
//		}
		
		panel.add(verticalPanel);
//		panel.add(mapPanel.getToolBase());
//		for(String key : mapPanelDekadsHM.keySet()) {
//			mapPanelDekadsHM.get(key).build(this);
//			panel.add(addSpace());
//			panel.add(mapPanelDekadsHM.get(key).getToolBase());
//		}
	}
	
	private void initializeCNSATool() {
		HaitiCNSAController.initializeCNSATool(gaul0code, this, language);
	}
	
	private VerticalPanel addSpace() {
		VerticalPanel v = new VerticalPanel();
		v.setHeight(10);
		return v;
	}
	
	private void format() {
		getToolBase().setHeaderVisible(false);
		getCenter().setHeaderVisible(false);	
		getCenter().setBodyBorder(false);
		getToolBase().setBodyBorder(false);
		getToolBase().setHeight(height);
		getToolBase().setWidth(width);
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public HaitiCNSACommandPanel getCommandPanel() {
		return commandPanel;
	}

	public HaitiCNSAMapPanel getMapPanel() {
		return mapPanel;
	}

	public HaitiCNSAChartPanel getChartPanel() {
		return chartPanel;
	}

	public String getLanguage() {
		return language;
	}

	public String getHeight() {
		return height;
	}

	public String getWidth() {
		return width;
	}

	public String getGaul0code() {
		return gaul0code;
	}

	public HaitiCNSALegendPanel getLegendPanel() {
		return legendPanel;
	}

	public LinkedHashMap<String, HaitiCNSAMapPanel> getMapPanelDekadsHM() {
		return mapPanelDekadsHM;
	}


	
	
}
