package org.fao.fenix.web.modules.dataMapper.client.view;

import java.util.LinkedHashMap;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
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

public class DataMapperMapPanel extends FenixToolBase {

	
	private DataMapperOLPanel olPanel;
	
	static LinkedHashMap<Long, LayerVO> layers;
	
	static LinkedHashMap<String, String> datasetLayerPrj;
	
	
	
	private String language;
	
	public DataMapperMapPanel(String language) {
		this.language = language;
		olPanel = new DataMapperOLPanel();
	
		toolBase.setBodyBorder(true);
		toolBase.setBorders(true);
		
		layers = new LinkedHashMap<Long, LayerVO>();
		
		datasetLayerPrj = new LinkedHashMap<String, String>();
		
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
		getCenter().add(olPanel.build(this, language));
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
		
		olPanel.getMapPanel().windowMoved();
	}

	public DataMapperOLPanel getOlPanel() {
		return olPanel;
	}

	public static LinkedHashMap<Long, LayerVO> getLayers() {
		return layers;
	}

	public static LinkedHashMap<String, String> getDatasetLayerPrj() {
		return datasetLayerPrj;
	}



	
	
	
	
	



	
}