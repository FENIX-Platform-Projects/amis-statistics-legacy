package org.fao.fenix.web.modules.re.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchParameters;
import org.fao.fenix.web.modules.re.client.view.chart.CatalogueContextMenuChart;
import org.fao.fenix.web.modules.re.client.view.chart.SearchParametersChart;
import org.fao.fenix.web.modules.re.client.view.codingsystem.SearchParametersCodingSystem;
import org.fao.fenix.web.modules.re.client.view.communication.CatalogueContextMenuCommunication;
import org.fao.fenix.web.modules.re.client.view.dataset.CatalogueContextMenuDataset;
import org.fao.fenix.web.modules.re.client.view.dataset.SearchParametersDataset;
import org.fao.fenix.web.modules.re.client.view.layer.CatalogueContextMenuLayer;
import org.fao.fenix.web.modules.re.client.view.layer.SearchParametersLayer;
import org.fao.fenix.web.modules.re.client.view.map.CatalogueContextMenuMap;
import org.fao.fenix.web.modules.re.client.view.map.SearchParametersMap;
import org.fao.fenix.web.modules.re.client.view.pattern.SearchParametersPattern;
import org.fao.fenix.web.modules.re.client.view.project.CatalogueContextMenuProject;
import org.fao.fenix.web.modules.re.client.view.project.SearchParametersProject;
import org.fao.fenix.web.modules.re.client.view.report.CatalogueContextMenuReport;
import org.fao.fenix.web.modules.re.client.view.report.SearchParametersReport;
import org.fao.fenix.web.modules.re.client.view.table.CatalogueContextMenuTable;
import org.fao.fenix.web.modules.re.client.view.table.SearchParametersTable;
import org.fao.fenix.web.modules.re.client.view.text.CatalogueContextMenuText;
import org.fao.fenix.web.modules.re.client.view.text.SearchParametersText;
import org.fao.fenix.web.modules.re.client.view.view.CatalogueContextMenuView;
import org.fao.fenix.web.modules.re.client.view.view.SearchParametersView;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class SearchButtonsController {

	private static void buildSearchParameters(ResourceExplorer resourceExplorer){

		FenixSearchParameters fenixSearchParameters=new FenixSearchParameters();
		List<String> resourceType=new ArrayList<String>();

		if (resourceExplorer.getSearchParameters() instanceof SearchParametersText)
			resourceType.add(ResourceType.TEXTVIEW);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersChart)
			resourceType.add(ResourceType.CHARTVIEW);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersDataset)
			resourceType.add(ResourceType.DATASET);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersLayer)
			resourceType.add(ResourceType.LAYER);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersMap)
			resourceType.add(ResourceType.MAPVIEW);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersPattern)
			resourceType.add(ResourceType.PATTERN);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersProject)
			resourceType.add(ResourceType.PROJECT);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersReport)
			resourceType.add(ResourceType.REPORT);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersTable)
			resourceType.add(ResourceType.TABLEVIEW);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersCodingSystem)
			resourceType.add(ResourceType.CODINGSYSTEM);
		else if (resourceExplorer.getSearchParameters() instanceof SearchParametersView)
			resourceType.add(ResourceType.AGGREGATEDVIEW);

		fenixSearchParameters.setResourceTypeList(resourceType);
		fenixSearchParameters.setOrderBy(resourceExplorer.getSearchParameters().getOrderBy().getValue(resourceExplorer.getSearchParameters().getOrderBy().getSelectedIndex()));

		if (resourceExplorer.getSearchParameters().getAscOrder().isChecked()) fenixSearchParameters.setOrderType("ASC");
		else if (resourceExplorer.getSearchParameters().getDescOrder().isChecked()) fenixSearchParameters.setOrderType("DESC");

		fenixSearchParameters.setTitle(resourceExplorer.getSearchParameters().getTitleResource());
		fenixSearchParameters.setKeyword(resourceExplorer.getSearchParameters().getKeyword());
		fenixSearchParameters.setRegion(resourceExplorer.getSearchParameters().getRegionList());
		fenixSearchParameters.setCategories(resourceExplorer.getSearchParameters().getCategoryList());


//		if (resourceExplorer.getSearchParameters() instanceof SearchParametersDataset || resourceExplorer.getSearchParameters() instanceof SearchParametersLayer){
//			fenixSearchParameters.setScope(((SearchParametersCommunication)resourceExplorer.getSearchParameters()).getLookIn());
//		} else {
			fenixSearchParameters.setScope(BabelFish.print().scopeNode());
		//}

		resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

	}

	public static SelectionListener<ButtonEvent> getResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){

				getResourcesAction(resourceExplorer);

			}
		};
	}


	public static void getResourcesAction(final ResourceExplorer resourceExplorer){
		SearchButtonsController.buildSearchParameters(resourceExplorer);
		//if (resourceExplorer instanceof ResourceExplorerProject || resourceExplorer instanceof ResourceExplorerSearch )
		//	CatalogueProjectController.updateTable(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), resourceExplorer.getSearchButtons().getFenixSearchParameters(), resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());
		// else

		//CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), resourceExplorer.getSearchButtons().getFenixSearchParameters(), resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

		CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), resourceExplorer.getSearchButtons().getFenixSearchParameters(), resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

		// change context menu according to the scope
		if (resourceExplorer.getSearchButtons().getFenixSearchParameters().getScope().equals(BabelFish.print().scopeNode())){
			if (ResourceTypeSelectorController.resourceType.equals(ResourceType.TEXTVIEW)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuText(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.PROJECT)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuProject(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.MAPVIEW)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuMap(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.TABLEVIEW)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuTable(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.CHARTVIEW)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuChart(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.REPORT)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuReport(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.DATASET)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuDataset(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.LAYER)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuLayer(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
			else if (ResourceTypeSelectorController.resourceType.equals(ResourceType.AGGREGATEDVIEW)) resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuView(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
		}
		else
			resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuCommunication(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
	}


	public static SelectionListener<ButtonEvent> cleanSearchParameters(final SearchParameters searParameters){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				searParameters.clean();
			}
		};
	}

}
