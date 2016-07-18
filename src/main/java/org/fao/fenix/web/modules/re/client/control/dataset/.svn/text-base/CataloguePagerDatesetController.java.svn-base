package org.fao.fenix.web.modules.re.client.control.dataset;

import java.util.List;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.qb.client.view.QueryBuilder;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.control.chart.CatalogueChartController;
import org.fao.fenix.web.modules.re.client.control.chart.utils.AddDatasetsToChartWizard;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.table.client.view.TableWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.ui.ListBox;

public class CataloguePagerDatesetController extends CataloguePagerController {

	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				
				// We should unify the dispatching of evets inside fenix.
				// Typeresource and whocall
				// are way too intrusive. The callback is lighter and more
				// general.
				if (resourceExplorer.getCallback() != null) {
					
					resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
					// resourceExplorer.getWindow().close();
				}

				if (resourceExplorer.getCaller().equals(WhoCall.CHART)) {

					AddDatasetsToChartWizard.addDatasets(resourceExplorer, selectedItems);

				}

				else if (resourceExplorer.getCaller().equals(WhoCall.OLAP)) {
					OlapWindow w = ((ResourceExplorerDataset)resourceExplorer).getOlapWindow();
		        	   
		        	for (ResourceChildModel model  : selectedItems) {
		                String title = model.getName();
		                String id = model.getId();
		                w.getTabPanel().getSingleDatasetPanel().getDataSource().addItem(title, id);
		            }
		        	REModel.getResourceExplorer().getWindow().close();
		        	w.getTabPanel().getSingleDatasetPanel().getDataSource().setSelectedIndex(w.getTabPanel().getSingleDatasetPanel().getDataSource().getItemCount() - 1);
		        	w.getTabPanel().getSingleDatasetPanel().getAggregationFunctionPanel().setExpanded(true);
		        	updateOlapDimensions(w);
				}
				else if (resourceExplorer.getCaller().equals(WhoCall.QUERYBUILDER)){
		        	QueryBuilder qb = ((ResourceExplorerDataset)resourceExplorer).getQueryBuilder();
		        	qb.getPanelOneDatasetSelection().addDatasetsToPanel(selectedItems);
		        	  
		        	REModel.getResourceExplorer().getWindow().close();
				}
				else if (resourceExplorer.getCaller().equals(WhoCall.USER)) {

					
					for (ResourceChildModel model : selectedItems) {
						System.out.println("ID: " + model.getId());
						
						new TableWindow().showAllData(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
						//new TableWindow().build(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
					}

					// add to project manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.TABLEVIEW);

					if (REModel.CLOSE)
						REModel.getResourceExplorer().getWindow().close();

				}
				
				else if (resourceExplorer.getCaller().equals(WhoCall.CHART_RE)){
		        	System.out.println("-> " + selectedItems.get(0).getName());
		        	CatalogueChartController.associateDatasetToChart(((ResourceExplorerDataset) resourceExplorer).getChartRE(), selectedItems.get(0));
		        	REModel.getResourceExplorer().getWindow().close();	
				}
				
				else if (resourceExplorer.getCaller().equals(WhoCall.CHARTDESIGNER)){
					ChartDesignerWindow w = ((ResourceExplorerDataset)resourceExplorer).getChartDesignerWindow();
					ChartDesignerDatasourceFieldSet fs = ((ResourceExplorerDataset)resourceExplorer).getChartDesignerDatasourceFieldSet();
					w.getChartDesignerPanel().getDatasourcePanel().getModels().clear();
					for (ResourceChildModel m : selectedItems) 
						w.getChartDesignerPanel().getDatasourcePanel().getModels().add(m);
					ChartDesignerController.loadDatasetAgent(w, fs, true);
		        	resourceExplorer.getWindow().hide();
				}
			}
		};
	}

	private static void updateOlapDimensions(OlapWindow win) {
		String datasetId = win.getTabPanel().getSingleDatasetPanel().getDataSource().getValue(win.getTabPanel().getSingleDatasetPanel().getDataSource().getSelectedIndex());
		ListBox x = win.getTabPanel().getSingleDatasetPanel().getXAxis().getDimensions();
		ListBox y = win.getTabPanel().getSingleDatasetPanel().getYAxis().getDimensions();
		ListBox z = win.getTabPanel().getSingleDatasetPanel().getZAxis().getDimensions();
		ListBox w = win.getTabPanel().getSingleDatasetPanel().getWAxis().getDimensions();
		ListBox values = win.getTabPanel().getSingleDatasetPanel().getValues(); 
		OlapController.updateDimensions(datasetId, x, y, z, w, values, null, null);
	}
}
