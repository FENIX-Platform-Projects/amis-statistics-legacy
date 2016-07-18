package org.fao.fenix.web.modules.adam.client.view;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;

public class ADAMWrapper {

	
	private ADAMMapWrapper adamMapWrapper;
	
	static HashMap<String, ADAMMapWrapper> adamMapsWrapper;
	
	private String language;
	
	/** that keeps track of all the views and layers that are been projected, 
	 	and that have to be removed when the broswer is closed. 
	 **/ 
	// dataset_view, map_projection_view
	static HashMap<String, String> datasetProjectionViews = new HashMap<String, String>();
	
//	private HaitiFeatureInfoPanel haitiFeatureInfoPanel;	
	
	public ADAMWrapper(String language) {
		this.language = language;
		adamMapsWrapper = new HashMap<String, ADAMMapWrapper>();
//		datasetProjectionViews = new HashMap<String, String>();
	}

	
	public ContentPanel buildMapPanel(String mapId, String color, String mapType, String position) {
		adamMapWrapper = new ADAMMapWrapper(color, language);
		adamMapWrapper.buildInitializeMapPanel();
		
		ADAMController.initializePredifinedMap(mapType, adamMapWrapper, language, position, color);
		
		adamMapsWrapper.put(mapId, adamMapWrapper);
		adamMapWrapper.getPanel().setTitle("map");
		return adamMapWrapper.getPanel();
	}


	public static HashMap<String, ADAMMapWrapper> getAdamMapsWrapper() {
		return adamMapsWrapper;
	}

	public static void addProjection(String datasetView, String layerView){
		System.out.println("ADDING PROJECTION");
		datasetProjectionViews.put(datasetView, layerView);
		System.out.println("---> " + datasetProjectionViews);
	}


	public static HashMap<String, String> getDatasetProjectionViews() {
		return datasetProjectionViews;
	}

		
	
	
	
}