package org.fao.fenix.web.modules.re.client.view.search;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class ResourceTypeSelector {

	SplitButton resources;
	Menu menu;
	ToolBar toolBar;
	ResourceExplorer resourceExplorer;

	public ToolBar getToolBar() {
		return toolBar;
	}

	public ResourceTypeSelector(ResourceExplorer resourceExplorer, String resourceType) {
		this.resourceExplorer = resourceExplorer;
		toolBar = new ToolBar();
		resources = new SplitButton(BabelFish.print().searchFor() + " " + resourceType);
		resources.setBorders(true);
		// resources.setIconStyle("reSearchButton");
		menu = new Menu();

		buildInterface();

	}

	private void buildInterface() {

		// Project
		MenuItem project = new MenuItem(BabelFish.print().projects());
		project.setIconStyle("projectIcon");
		project.addSelectionListener(ResourceTypeSelectorController.getProjects(resources, resourceExplorer));
		menu.add(project);
		menu.add(new SeparatorMenuItem());
		// Table
		// MenuItem table=new MenuItem(I18N.print().table());
		// table.setIconStyle("tableIcon");
		// table.addSelectionListener(ResourceTypeSelectorController.getTables(resources,
		// resourceExplorer));
		// menu.add(table);
		// Dataset
		MenuItem dataset = new MenuItem(BabelFish.print().datasets());
		dataset.setIconStyle("datasetIcon");
		dataset.addSelectionListener(ResourceTypeSelectorController.getDatasets(resources, resourceExplorer));
		menu.add(dataset);
		// Chart
		MenuItem chart = new MenuItem(BabelFish.print().chart());
		chart.setIconStyle("chartIcon");
		chart.addSelectionListener(ResourceTypeSelectorController.getCharts(resources, resourceExplorer));
		menu.add(chart);
		menu.add(new SeparatorMenuItem());
		// OLAP
		MenuItem olap = new MenuItem(BabelFish.print().olap());
		olap.setIconStyle("olap");
		olap.addSelectionListener(ResourceTypeSelectorController.getOlaps(resources, resourceExplorer));
		menu.add(olap);
		menu.add(new SeparatorMenuItem());
		
		// VIEW
		MenuItem view = new MenuItem(BabelFish.print().view());
		view.setIconStyle("olap");
		view.addSelectionListener(ResourceTypeSelectorController.getAggregatedViews(resources, resourceExplorer));
		//menu.add(view);
		//menu.add(new SeparatorMenuItem());
		// Map
		MenuItem map = new MenuItem(BabelFish.print().map());
		map.setIconStyle("mapIcon");
		map.addSelectionListener(ResourceTypeSelectorController.getMaps(resources, resourceExplorer));
		menu.add(map);
		// Layer
		MenuItem layer = new MenuItem(BabelFish.print().layers());
		layer.setIconStyle("layerIcon");
		layer.addSelectionListener(ResourceTypeSelectorController.getLayers(resources, resourceExplorer));
		menu.add(layer);
		menu.add(new SeparatorMenuItem());
		// Text
		MenuItem text = new MenuItem(BabelFish.print().text());
		text.setIconStyle("textResourceIcon");
		text.addSelectionListener(ResourceTypeSelectorController.getTexts(resources, resourceExplorer));
		menu.add(text);
		// Text group
		MenuItem textGroup = new MenuItem(BabelFish.print().textGroups());
		textGroup.setIconStyle("textGroupIcon");
		textGroup.addSelectionListener(ResourceTypeSelectorController.getTextGroups(resources, resourceExplorer));
		menu.add(textGroup);
		// Report
		MenuItem report = new MenuItem(BabelFish.print().reports());
		report.setIconStyle("reportIcon");
		report.addSelectionListener(ResourceTypeSelectorController.getReports(resources, resourceExplorer));
		menu.add(report);
		menu.add(new SeparatorMenuItem());
		// Coding Systems
		MenuItem codingSystem = new MenuItem(BabelFish.print().codingSystems());
		codingSystem.setIconStyle("codingSystemIcon");
		codingSystem.addSelectionListener(ResourceTypeSelectorController.getCodingSystems(resources, resourceExplorer));
		menu.add(codingSystem);
		// Image
		MenuItem image = new MenuItem("Images");
		image.setIconStyle("smallCamera");
		image.addSelectionListener(ResourceTypeSelectorController.getImages(resources, resourceExplorer));
		menu.add(image);

		// buttonBar.add(button);
		resources.setMenu(menu);
		toolBar.add(resources);

	}

}
