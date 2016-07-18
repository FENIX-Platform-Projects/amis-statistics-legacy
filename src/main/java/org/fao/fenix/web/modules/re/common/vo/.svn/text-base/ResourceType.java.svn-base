package org.fao.fenix.web.modules.re.common.vo;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;

public class ResourceType {
	
	public final static String MAPVIEW = "MapView";
	public final static String CHARTVIEW = "ChartView";
	public final static String TABLEVIEW = "TableView";
	public final static String PROJECT = "Project";
	public final static String TEXTVIEW = "TextView";
	public final static String PATTERN = "Pattern";
	public final static String REPORT = "ReportView";
	public final static String DATASET = "Dataset";
	public final static String CORE_DATASET = "CORE_DATASET";
	public final static String CUSTOM_DATASET = "CUSTOM_DATASET";
	public final static String LAYER = "WMSMapProvider";
	public final static String LAYERSUBTYPE = "Layer";
	public final static String CODINGSYSTEM = "CodingSystem";
	public final static String DEFAULT = "Default";
	public final static String COMMUNICATION = "CommunicationResource";
	public final static String TEXTGROUP = "TextGroup";
	public final static String OLAP = "Olap";
	public final static String LATEXREPORT = "LatexReport";
	public final static String IMAGE = "Image";
	public final static String RESOURCEVIEW = "ResourceView";
	public final static String AGGREGATEDVIEW = "AggregatedView";
	
	// ask Emanuele
	public final static String INTERNAL_LAYER = "";
	public final static String EXTERNAL_LAYER = "ExternalWMSLayer";

	public static Map<String, String> resourceNameMap = new HashMap<String, String>();
	public static Map<String, String> resourceIconMap = new HashMap<String, String>();
	public static Map<String, String> resourceIconMapPath = new HashMap<String, String>();

	static {
		resourceNameMap.put(MAPVIEW, BabelFish.print().map());
		resourceNameMap.put(CHARTVIEW, BabelFish.print().chart());
		resourceNameMap.put(TABLEVIEW, BabelFish.print().table());
		resourceNameMap.put(PROJECT, BabelFish.print().projects());
		resourceNameMap.put(TEXTVIEW, BabelFish.print().text());
		resourceNameMap.put(REPORT, BabelFish.print().reports());
		resourceNameMap.put(DATASET, BabelFish.print().datasets());
		resourceNameMap.put(CORE_DATASET, BabelFish.print().datasets());
		resourceNameMap.put(CUSTOM_DATASET, BabelFish.print().datasets());
		resourceNameMap.put(LAYER, BabelFish.print().layers());
		resourceNameMap.put(CODINGSYSTEM, BabelFish.print().codingSystem());
		resourceNameMap.put(EXTERNAL_LAYER, BabelFish.print().externalLayers());
		resourceNameMap.put(TEXTGROUP, BabelFish.print().textGroup());
		resourceNameMap.put(OLAP, BabelFish.print().olap());
		resourceNameMap.put(LATEXREPORT, BabelFish.print().latexReport());
		resourceNameMap.put(IMAGE, "Image");
		resourceNameMap.put(RESOURCEVIEW, BabelFish.print().olap());
		resourceNameMap.put(AGGREGATEDVIEW, BabelFish.print().view());

		resourceIconMap.put(MAPVIEW, "map_ll_external");
		resourceIconMap.put(CHARTVIEW, "chartIcon");
		resourceIconMap.put(TABLEVIEW, "tableIcon");
		resourceIconMap.put(PROJECT, "projectIcon");
		resourceIconMap.put(TEXTVIEW, "textResourceIcon");
		resourceIconMap.put(REPORT, "reportIcon");
		resourceIconMap.put(CORE_DATASET, "datasetIcon");
		resourceIconMap.put(CUSTOM_DATASET, "datasetIcon");
		resourceIconMap.put(LAYER, "mapLayers");
		resourceIconMap.put(CODINGSYSTEM, "codingSystemIcon");
		resourceIconMap.put(EXTERNAL_LAYER, "mapLayers");
		resourceIconMap.put(DEFAULT, "defaultResourceIcon");
		resourceIconMap.put(TEXTGROUP, "textGroupIcon");
		resourceIconMap.put(OLAP, "olap");
		resourceIconMap.put(LATEXREPORT, "latex");
		resourceIconMap.put(IMAGE, "smallCamera");
		resourceIconMap.put(RESOURCEVIEW, "olap");
		resourceIconMap.put(AGGREGATEDVIEW, "dataViewIcon");
		
		resourceIconMapPath.put(MAPVIEW, "map-images/map.png");
		resourceIconMapPath.put(CHARTVIEW, "images/chart.png");
		resourceIconMapPath.put(TABLEVIEW, "images/table.png");
		resourceIconMapPath.put(PROJECT, "images/project.png");
		resourceIconMapPath.put(TEXTVIEW, "images/text.png");
		resourceIconMapPath.put(REPORT, "images/report.png");
		resourceIconMapPath.put(CORE_DATASET, "images/dataset.png");
		resourceIconMapPath.put(CUSTOM_DATASET, "images/dataset.png");
		resourceIconMapPath.put(LAYER, "re-images/map_layers.png");
		resourceIconMapPath.put(CODINGSYSTEM, "images/coding_system.png");
		resourceIconMapPath.put(EXTERNAL_LAYER, "re-images/map_layers.png");
		resourceIconMapPath.put(DEFAULT, "images/default_resource.png");
		resourceIconMapPath.put(TEXTGROUP, "images/text_group.png");
		resourceIconMapPath.put(OLAP, "images/olap.png");
		resourceIconMapPath.put(LATEXREPORT, "images/latex.png");
		resourceIconMapPath.put(IMAGE, "images/smallCamera.png");
		resourceIconMapPath.put(RESOURCEVIEW, "images/olap.png");
		resourceIconMapPath.put(AGGREGATEDVIEW, "images/views.png");
		resourceIconMapPath.put(DATASET, "images/dataset.png");
		
	}

	public static String getPresentableName(String resourceType) {
		if (!validate(resourceType))
			System.out.println("Error, " + resourceType + "  is not a valid resourceType! ");
		return (String) resourceNameMap.get(resourceType);
	}

	public static boolean validate(String type) {
		return PROJECT.equals(type)//
				|| CHARTVIEW.equals(type)//
				|| TABLEVIEW.equals(type)//
				|| PROJECT.equals(type)//
				|| TEXTVIEW.equals(type)//
				|| REPORT.equals(type)//
				|| DATASET.equals(type)//
				|| CORE_DATASET.equals(type)
				|| CUSTOM_DATASET.equals(type)
				|| CODINGSYSTEM.equals(type)//
				|| LAYER.equals(type)
				|| EXTERNAL_LAYER.equals(type)
				|| TEXTGROUP.equals(type)
				|| OLAP.equals(type)
				|| AGGREGATEDVIEW.equals(type);
	}
	

	public static ResourceType getResourceType(String name) {
		return ResourceType.getResourceType(name);
	}

}
