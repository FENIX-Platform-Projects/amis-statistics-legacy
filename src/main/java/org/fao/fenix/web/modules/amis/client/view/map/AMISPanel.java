package org.fao.fenix.web.modules.amis.client.view.map;

import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class AMISPanel extends FenixToolBase {
	
	private ContentPanel panel;
	
	// main map panel
	//private AMISMapPanel mapPanel;

	private String height;
	
	private String width;
	private String language;

	public AMISPanel(String width, String height,String language) {
		this.height = height;
		this.width = width;
		this.language = language;
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		// map panel
		//mapPanel = new AMISMapPanel(width,height,language,0);
		

	//	mapPanelDekadsHM = new LinkedHashMap<String, HaitiCNSAMapPanel>();
//		for(int i=1; i < decads; i++) {
//			HaitiCNSAMapPanel mapPanelDekad = new HaitiCNSAMapPanel(gaul0code, width, height, language, i);
//			mapPanelDekadsHM.put("dekad" + i, mapPanelDekad);
//		}
		
		
	//	commandPanel = new HaitiCNSACommandPanel();		
	//	chartPanel = new HaitiCNSAChartPanel();
	//	legendPanel = new HaitiCNSALegendPanel(gaul0code, width, height, language);
		
	}
	
	public ContentPanel build() {
		
		//mapPanel.build(this);
		//mapPanel.build();
		//panel.add(commandPanel.build(language, this));			
		
//		buildMapsPanel();
		//Just to separate
		panel.add(addSpace());
	//	panel.add(legendPanel.build(this));
	//	panel.add(addSpace());
	//	panel.add(chartPanel.build(language, width, height));
		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		format();
		//initializeCNSATool();
		return panel;		
	}
	
	private void format() {
		getToolBase().setHeaderVisible(false);
		getCenter().setHeaderVisible(false);	
		getCenter().setBodyBorder(false);
		getToolBase().setBodyBorder(false);
		getToolBase().setHeight(height);
		getToolBase().setWidth(width);
	}
	
//	private void buildMapsPanel() {
//		VerticalPanel verticalPanel = new VerticalPanel();
//		verticalPanel.setSpacing(15);
//		HorizontalPanel horizontalPanel = new HorizontalPanel();
//		horizontalPanel.setSpacing(5);
//		
//		horizontalPanel.add(mapPanel.getToolBase());
//		int dekads=6;
//		for(int i=1; i < dekads; i++) {
//			AMISMapPanel mapPanelD = new AMISMapPanel(width, height, language, i);
//			//HaitiCNSAMapPanel mapPanelDekad = new HaitiCNSAMapPanel(gaul0code, width, height, language, i);
//			//mapPanelDekad.build(this);
//			//mapPanelD.build(this);
//			mapPanelD.build();
//			//mapPanelDekadsHM.put(mapPanelDekad.getHaitiOLPanel().getMapPanel().getMapDivId(), mapPanelDekad);
//			
//			if ( i % 2 == 1){
//				//horizontalPanel.add(mapPanelDekad.getToolBase());
//				horizontalPanel.add(mapPanelD.getToolBase());
//				verticalPanel.add(horizontalPanel);
//			}
//			else {
//				horizontalPanel = new HorizontalPanel();
//				horizontalPanel.setSpacing(5);
//				//horizontalPanel.add(mapPanelDekad.getToolBase());
//				horizontalPanel.add(mapPanelD.getToolBase());
//			}
//
//		}
////		for(String key : mapPanelDekadsHM.keySet()) {
////			mapPanelDekadsHM.get(key).build(this);
////			System.out.println("i: " + i + " | " + (i %2));
////			if ( i % 2 == 0){
////				horizontalPanel.add(mapPanelDekadsHM.get(key).getToolBase());
////				verticalPanel.add(horizontalPanel);
////			}
////			else {
////				horizontalPanel = new HorizontalPanel();
////				horizontalPanel.setSpacing(5);
////				horizontalPanel.add(mapPanelDekadsHM.get(key).getToolBase());
////			}
////			i++;
////		}
//		
//		panel.add(verticalPanel);
////		panel.add(mapPanel.getToolBase());
////		for(String key : mapPanelDekadsHM.keySet()) {
////			mapPanelDekadsHM.get(key).build(this);
////			panel.add(addSpace());
////			panel.add(mapPanelDekadsHM.get(key).getToolBase());
////		}
//	}
	
	private VerticalPanel addSpace() {
		VerticalPanel v = new VerticalPanel();
		v.setHeight(10);
		return v;
	}
	
	/*public AMISMapPanel getMapPanel() {
		return mapPanel;
	}*/

	
}
