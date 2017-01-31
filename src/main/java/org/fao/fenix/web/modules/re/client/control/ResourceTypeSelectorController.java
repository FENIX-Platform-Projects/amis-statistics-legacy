package org.fao.fenix.web.modules.re.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.chart.CatalogueChartController;
import org.fao.fenix.web.modules.re.client.control.codingsystem.CatalogueCodingSystemController;
import org.fao.fenix.web.modules.re.client.control.dataset.CatalogueDatasetController;
import org.fao.fenix.web.modules.re.client.control.image.CatalogueImageController;
import org.fao.fenix.web.modules.re.client.control.layer.CatalogueLayerController;
import org.fao.fenix.web.modules.re.client.control.map.CatalogueMapController;
import org.fao.fenix.web.modules.re.client.control.olap.CatalogueOlapController;
import org.fao.fenix.web.modules.re.client.control.project.CatalogueProjectController;
import org.fao.fenix.web.modules.re.client.control.report.CatalogueReportController;
import org.fao.fenix.web.modules.re.client.control.search.CataloguePagerSearchController;
import org.fao.fenix.web.modules.re.client.control.table.CatalogueTableController;
import org.fao.fenix.web.modules.re.client.control.text.CatalogueTextController;
import org.fao.fenix.web.modules.re.client.control.textgroup.CatalogueTextGroupController;
import org.fao.fenix.web.modules.re.client.control.view.CatalogueViewController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.chart.SearchParametersChart;
import org.fao.fenix.web.modules.re.client.view.codingsystem.CatalogueContextMenuCodingSystem;
import org.fao.fenix.web.modules.re.client.view.codingsystem.SearchParametersCodingSystem;
import org.fao.fenix.web.modules.re.client.view.dataset.CatalogueContextMenuDataset;
import org.fao.fenix.web.modules.re.client.view.dataset.SearchParametersDataset;
import org.fao.fenix.web.modules.re.client.view.image.CatalogueContextMenuImage;
import org.fao.fenix.web.modules.re.client.view.layer.SearchParametersLayer;
import org.fao.fenix.web.modules.re.client.view.map.SearchParametersMap;
import org.fao.fenix.web.modules.re.client.view.olap.SearchParametersOlap;
import org.fao.fenix.web.modules.re.client.view.project.SearchParametersProject;
import org.fao.fenix.web.modules.re.client.view.report.SearchParametersReport;
import org.fao.fenix.web.modules.re.client.view.search.ResourceTypeSelector;
import org.fao.fenix.web.modules.re.client.view.table.SearchParametersTable;
import org.fao.fenix.web.modules.re.client.view.text.SearchParametersText;
import org.fao.fenix.web.modules.re.client.view.textgroup.SearchParametersTextGroup;
import org.fao.fenix.web.modules.re.client.view.view.SearchParametersView;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.SplitButton;

public class ResourceTypeSelectorController {

	public static String resourceType = ResourceType.PROJECT;

	public static SelectionListener<MenuEvent> getProjects(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().projects());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().projects());
				resourceType = ResourceType.PROJECT;

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().projects()));
				resourceExplorer.setSearchParameters(new SearchParametersProject(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.PROJECT);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueProjectController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.PROJECT));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

				Info.display("Click Event", "The '{0}' button was clicked.", "Projects");
			}
		};
	}

	public static SelectionListener<MenuEvent> getMaps(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().map());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().map());
				resourceType = ResourceType.MAPVIEW;

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().map()));
				resourceExplorer.setSearchParameters(new SearchParametersMap(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.MAPVIEW);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueMapController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.MAPVIEW));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

				Info.display("Click Event", "The '{0}' button was clicked.", "Maps");
			}
		};
	}

	public static SelectionListener<MenuEvent> getTables(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().table());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().table());
				resourceType = ResourceType.TABLEVIEW;

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().table()));
				resourceExplorer.setSearchParameters(new SearchParametersTable(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.TABLEVIEW);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueTableController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.TABLEVIEW));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

				Info.display("Click Event", "The '{0}' button was clicked.", "Tables");
			}
		};
	}

	public static SelectionListener<MenuEvent> getCharts(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().chart());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().chart());
				resourceType = ResourceType.CHARTVIEW;

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().chart()));
				resourceExplorer.setSearchParameters(new SearchParametersChart(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.CHARTVIEW);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueChartController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.CHARTVIEW));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

				Info.display("Click Event", "The '{0}' button was clicked.", "Charts");
			}
		};
	}

	public static SelectionListener<MenuEvent> getTexts(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().text());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().text());
				resourceType = ResourceType.TEXTVIEW;

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().text()));
				resourceExplorer.setSearchParameters(new SearchParametersText(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());

				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.TEXTVIEW);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueTextController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.TEXTVIEW));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

			}
		};
	}

	public static SelectionListener<MenuEvent> getOlaps(final SplitButton resources, final ResourceExplorer resourceExplorer) {

		return new SelectionListener<MenuEvent>() {

			public void componentSelected(MenuEvent ce) {

				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().olap());
				resourceType = ResourceType.OLAP;

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().olap()));
				resourceExplorer.setSearchParameters(new SearchParametersOlap(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());

				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.OLAP);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueOlapController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.OLAP));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());
			}
		};
	}

	public static SelectionListener<MenuEvent> getAggregatedViews(final SplitButton resources, final ResourceExplorer resourceExplorer) {

			return new SelectionListener<MenuEvent>() {

				public void componentSelected(MenuEvent ce) {

					resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().view());
					resourceType = ResourceType.AGGREGATEDVIEW;

					resourceExplorer.removeWestPartToWindow();
					resourceExplorer.setWestProperties();

					// west
					resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().view()));
					resourceExplorer.setSearchParameters(new SearchParametersView(resourceExplorer));
					resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

					resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
					resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
					resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());

					resourceExplorer.addWestPartToWindow();

					// refresh west panel
					resourceExplorer.getWindow().getLayout().layout();

					// center
					final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
					List<String> type = new ArrayList<String>();
					type.add(ResourceType.AGGREGATEDVIEW);
					fenixSearchParameters.setResourceTypeList(type);
					fenixSearchParameters.setScope(BabelFish.print().scopeNode());
					fenixSearchParameters.setOrderBy("title");
					fenixSearchParameters.setOrderType("ASC");

					resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

					// fill the table
					CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

					// add context menu
					resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueViewController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

					// change the listener in order to point the right
					// "openResources" controller
					resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
					resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.AGGREGATEDVIEW));

					resourceExplorer.getWestData().setFloatable(true);

					// Workaround otherwise gwt doesn't refresh the window
					resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());
				}
			};
		}


	public static SelectionListener<MenuEvent> getTextGroups(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().textGroup());
				resourceType = ResourceType.TEXTGROUP;

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().textGroup()));
				resourceExplorer.setSearchParameters(new SearchParametersTextGroup(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());

				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.TEXTGROUP);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueTextGroupController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.TEXTGROUP));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

			}
		};
	}

	public static SelectionListener<MenuEvent> getReports(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().reports());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().reports());
				resourceType = ResourceType.REPORT;
				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().reports()));
				resourceExplorer.setSearchParameters(new SearchParametersReport(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.REPORT);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueReportController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.REPORT));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

			}
		};
	}

	public static SelectionListener<MenuEvent> getLayers(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().mapLayers());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().mapLayers());
				resourceType = ResourceType.LAYER;
				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().mapLayers()));
				resourceExplorer.setSearchParameters(new SearchParametersLayer(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.LAYER);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueLayerController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.LAYER));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

				Info.display("Click Event", "The '{0}' button was clicked.", "Layers");
			}
		};
	}

	public static SelectionListener<MenuEvent> getDatasets(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				// resourceExplorer.setTitle(I18N.print().resourceExplorerWindowHeader()
				// + " - " + I18N.print().datasets());
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().datasets());
				resourceType = ResourceType.DATASET;
				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.DATASET);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().datasets()));
				resourceExplorer.setSearchParameters(new SearchParametersDataset(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				// initialize
				resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuDataset(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu(null));
				// build
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueDatasetController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				// resourceExplorer.setCaller(WhoCall.USER);
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.DATASET));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

				Info.display("Click Event", "The '{0}' button was clicked.", "Datasets");
			}
		};
	}

	public static SelectionListener<MenuEvent> getCodingSystems(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				resources.setText(BabelFish.print().searchFor() + " " + BabelFish.print().codingSystems());
				resourceType = ResourceType.CODINGSYSTEM;
				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.CODINGSYSTEM);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, BabelFish.print().codingSystems()));
				resourceExplorer.setSearchParameters(new SearchParametersCodingSystem(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				// initialize
				resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuCodingSystem(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
				// build
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueCodingSystemController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				// resourceExplorer.setCaller(WhoCall.USER);
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.CODINGSYSTEM));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

				Info.display("Click Event", "The '{0}' button was clicked.", "Coding Systems");
			}
		};
	}
	
	public static SelectionListener<MenuEvent> getImages(final SplitButton resources, final ResourceExplorer resourceExplorer) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				resources.setText(BabelFish.print().searchFor() + " Images");
				resourceType = ResourceType.IMAGE;
				// center
				final FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
				List<String> type = new ArrayList<String>();
				type.add(ResourceType.IMAGE);
				fenixSearchParameters.setResourceTypeList(type);
				fenixSearchParameters.setScope(BabelFish.print().scopeNode());
				fenixSearchParameters.setOrderBy("title");
				fenixSearchParameters.setOrderType("ASC");

				// west
				resourceExplorer.setResourceTypeSelector(new ResourceTypeSelector(resourceExplorer, "Images"));
				resourceExplorer.setSearchParameters(new SearchParametersCodingSystem(resourceExplorer));
				resourceExplorer.setSearchButtons(new SearchButtons(resourceExplorer));

				resourceExplorer.removeWestPartToWindow();
				resourceExplorer.setWestProperties();

				resourceExplorer.getWest().setTopComponent(resourceExplorer.getResourceTypeSelector().getToolBar());
				resourceExplorer.getWest().add(resourceExplorer.getSearchParameters().getMainCont());
				resourceExplorer.getWest().setBottomComponent(resourceExplorer.getSearchButtons().getCont());
				resourceExplorer.addWestPartToWindow();

				// refresh west panel
				resourceExplorer.getWindow().getLayout().layout();

				resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

				// fill the table
				CatalogueController.fillGrid(resourceExplorer, resourceExplorer.getCatalogue().getGrid(), fenixSearchParameters, resourceExplorer.getCataloguePager(), resourceExplorer.getCatalogue());

				// add context menu
				// initialize
				resourceExplorer.getCatalogue().getGrid().setContextMenu(new CatalogueContextMenuImage(resourceExplorer, resourceExplorer.getCatalogue()).buildMenu());
				// build
				resourceExplorer.getCatalogue().getGrid().addListener(Events.ContextMenu, CatalogueImageController.setContextMenu(resourceExplorer.getCatalogue().getGrid(), resourceExplorer, resourceExplorer.getCatalogue()));

				// change the listener in order to point the right
				// "openResources" controller
				// resourceExplorer.setCaller(WhoCall.USER);
				resourceExplorer.getCataloguePager().getOpen().removeAllListeners();
				resourceExplorer.getCataloguePager().getOpen().addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, ResourceType.IMAGE));

				resourceExplorer.getWestData().setFloatable(true);

				// Workaround otherwise gwt doesn't refresh the window
				resourceExplorer.getWindow().setWidth(resourceExplorer.getWindow().getWidth());

//				Info.display("Click Event", "The '{0}' button was clicked.", "Coding Systems");
			}
		};
	}

}
