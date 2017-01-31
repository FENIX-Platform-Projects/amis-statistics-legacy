package org.fao.fenix.web.modules.re.client.control.search;

import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.control.chart.CatalogueChartController;
import org.fao.fenix.web.modules.re.client.control.codingsystem.CatalogueCodingSystemController;
import org.fao.fenix.web.modules.re.client.control.dataset.CatalogueDatasetController;
import org.fao.fenix.web.modules.re.client.control.layer.CatalogueLayerController;
import org.fao.fenix.web.modules.re.client.control.map.CatalogueMapController;
import org.fao.fenix.web.modules.re.client.control.olap.CatalogueOlapController;
import org.fao.fenix.web.modules.re.client.control.project.CatalogueProjectController;
import org.fao.fenix.web.modules.re.client.control.report.CatalogueReportController;
import org.fao.fenix.web.modules.re.client.control.table.CatalogueTableController;
import org.fao.fenix.web.modules.re.client.control.text.CatalogueTextController;
import org.fao.fenix.web.modules.re.client.control.textgroup.CatalogueTextGroupController;
import org.fao.fenix.web.modules.re.client.control.view.CatalogueViewController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;

public class CatalogueSearchController extends CatalogueController{
	
	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				if (ResourceTypeSelectorController.resourceType.equals(ResourceType.PROJECT)) CatalogueProjectController.onDoubleClickAction(resourceExplorer.getCatalogue().getGrid()); 
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.DATASET)) CatalogueDatasetController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.MAPVIEW)) CatalogueMapController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.TABLEVIEW)) CatalogueTableController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.CHARTVIEW)) CatalogueChartController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.TEXTVIEW)) CatalogueTextController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.REPORT)) CatalogueReportController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.CODINGSYSTEM)) CatalogueCodingSystemController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.LAYER)) CatalogueLayerController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.TEXTGROUP)) CatalogueTextGroupController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.AGGREGATEDVIEW)) CatalogueViewController.onDoubleClickAction(resourceExplorer);
				else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.OLAP)) CatalogueOlapController.onDoubleClickAction(resourceExplorer);
			}
		};
	}

}
