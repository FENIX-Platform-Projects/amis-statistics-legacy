package org.fao.fenix.web.modules.adam.client.view;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.map.client.view.MapPanel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;

public class ADAMMapPanel extends FenixToolBase {

	
	private ADAMOLPanel adamOLPanel;
	
	private ADAMLayersHandle adamLayersHandle;
	
	private String language;
	
	public ADAMMapPanel(String language) {
		this.language = language;
		adamOLPanel = new ADAMOLPanel();
		adamLayersHandle = new ADAMLayersHandle();
		toolBase.setBodyBorder(true);
		toolBase.setBorders(true);
		
//		toolBase.setBodyBorder(false);
	}
	
	public ContentPanel build(String width, String height) {
		buildCenterPanel("Map", language);

		format(width, height);
		
		return toolBase;
	}
	
	public ContentPanel build(String gaul0code, String width, String height, String language, SelectionListener<ButtonEvent> objectSizeListener) {
		buildCenterPanel("Map", language);
		
		format(width, height);
		
		return toolBase;
	}
	
	private void buildCenterPanel(String title, String language) {
		setCenterProperties();
		getCenter().add(adamOLPanel.build(this, language));
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getCenter().setBodyBorder(false);
	}
	
	
	private void format(String width, String height) {
		if (width != null)
			getToolBase().setWidth(width);
		else
			getToolBase().setWidth("1350px");
		if (height != null)
			getToolBase().setHeight(height);
		else
			getToolBase().setHeight("675px");
		getToolBase().setHeaderVisible(false);
	}

	public void reSizeTool(String width, String height, int numZoomLevels) {
		getToolBase().setWidth(width);
		getToolBase().setHeight(height);

		// this zoom level update doesn't work 
		//System.out.println(numZoomLevels);
		//adamOLPanel.getMapPanel().setNumZoomLevels(numZoomLevels);
		
//		adamOLPanel.getMapPanel().windowMoved();
		
		
//		try {
//			if ( adamOLPanel.getMapPanel().getSelectedLayer() != null ) {
//				LayerVO currentLayer = adamOLPanel.getMapPanel().getSelectedLayer();
//				adamOLPanel.getMapPanel().zoomToExtend();
//				adamOLPanel.getMapPanel().redrawLayer(currentLayer.getLayerID());
//			}
//		} catch (Exception e) {
//			System.out.println("ERROR ON MAP");
//			// TODO: handle exception
//		}
		
//		adamOLPanel.getMapPanel().
		getToolBase().layout();
	}
	
	

	public ADAMOLPanel getAdamOLPanel() {
		return adamOLPanel;
	}

//	public ADAMLayersHandle getAdamLayersHandle() {
//		return adamLayersHandle;
//	}

	
}