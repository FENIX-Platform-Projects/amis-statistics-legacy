package org.fao.fenix.web.modules.re.client.control.dataset;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartController;
import org.fao.fenix.web.modules.ofcchart.client.view.OfcChartWindow;
import org.fao.fenix.web.modules.olap.client.control.MTController;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.client.view.MTDatasourceFieldSet;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.qb.client.view.QueryBuilder;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.control.chart.CatalogueChartController;
import org.fao.fenix.web.modules.re.client.control.chart.utils.AddDatasetsToChartWizard;
import org.fao.fenix.web.modules.re.client.control.chart.utils.AddDatasetsToOfcChartWizard;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.dataset.CatalogueContextMenuDataset;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.table.client.view.TableWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.ui.ListBox;

public class CatalogueDatasetController extends CatalogueController {
	
	
	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				
				onDoubleClickAction(resourceExplorer);
				
			}
		};
	}
	
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer){
		final Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
    	final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
       
        // We should unify the dispatching of events inside fenix. Typeresource and whocall 
        // are way too intrusive. The callback is more general.
        if (resourceExplorer.getCallback() != null) {
            resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
            //resourceExplorer.getWindow().close();
        }                                
        
        else if (resourceExplorer.getCaller().equals(WhoCall.CHART)){
			AddDatasetsToChartWizard.addDatasets(resourceExplorer, selectedItems);
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.CHARTDESIGNER)) {
			ChartDesignerWindow w = ((ResourceExplorerDataset)resourceExplorer).getChartDesignerWindow();
			ChartDesignerDatasourceFieldSet fs = ((ResourceExplorerDataset)resourceExplorer).getChartDesignerDatasourceFieldSet();
			w.getChartDesignerPanel().getDatasourcePanel().getModels().clear();
			for (ResourceChildModel m : selectedItems) 
				w.getChartDesignerPanel().getDatasourcePanel().getModels().add(m);
			ChartDesignerController.loadDatasetAgent(w, fs, true);
        	resourceExplorer.getWindow().hide();
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.MT)) {
        	MTWindow w = ((ResourceExplorerDataset)resourceExplorer).getMtWindow();
        	MTDatasourceFieldSet fs = ((ResourceExplorerDataset)resourceExplorer).getMtDatasourceFieldSet();
        	w.getMtPanel().getMtDatasourcePanel().getModels().clear();
			for (ResourceChildModel m : selectedItems) 
				w.getMtPanel().getMtDatasourcePanel().getModels().add(m);
			MTController.loadDatasetAgent(w, fs, null);
        	resourceExplorer.getWindow().hide();
        }
        
        else if (resourceExplorer.getCaller().equals(WhoCall.OFCCHARTWIZARD)){
			AddDatasetsToOfcChartWizard.addDatasets(resourceExplorer, selectedItems);
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.TABLE)){
        	ReportViewer rw = ((ResourceExplorerDataset)resourceExplorer).getReportViewer();
        	for (ResourceChildModel model: selectedItems) 
				new TableWindow().buildForReportWizard(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), rw, model.getName());
        	REModel.getResourceExplorer().getWindow().close();
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.OLAP)){
        	OlapWindow w = ((ResourceExplorerDataset)resourceExplorer).getOlapWindow();      	   
        	for (ResourceChildModel model  : selectedItems) {
                String title = model.getName();
                String id = model.getId();
                w.getTabPanel().getSingleDatasetPanel().getDataSource().addItem(title, id);
                w.getTabPanel().getSingleDatasetPanel().getOlapTitle().setValue(title);         
            }
        	REModel.getResourceExplorer().getWindow().close();
        	w.getTabPanel().getSingleDatasetPanel().getDataSource().setSelectedIndex(w.getTabPanel().getSingleDatasetPanel().getDataSource().getItemCount() - 1);
        	w.getTabPanel().getSingleDatasetPanel().getAggregationFunctionPanel().setExpanded(true);
        	updateOlapDimensions(w);
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.OFCCHART)){
        	OfcChartWindow w = ((ResourceExplorerDataset)resourceExplorer).getOfcChartWindow();
        
        	for (ResourceChildModel model: selectedItems) {
                String title = model.getName();
                String id = model.getId();
                w.getTabPanel().getSingleDatasetPanel().getDataSource().addItem(title, id);
            }
        	REModel.getResourceExplorer().getWindow().close();
        	updateOfcChartDimensions(w);
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.QUERYBUILDER)){
        	QueryBuilder qb = ((ResourceExplorerDataset)resourceExplorer).getQueryBuilder();
        	qb.getPanelOneDatasetSelection().addDatasetsToPanel(selectedItems);
        	  
        	REModel.getResourceExplorer().getWindow().close();
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
			
			for (ResourceChildModel model: selectedItems){
				   new TableWindow().showAllData(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
				   //new TableWindow().build(Long.valueOf(model.getId()), model.hasWritePermission(), model.isFlexDatasetType(), model.getName());
			}
			
			//add to project manager
			PMModel.addResourceToProjectManager(selectedItems, ResourceType.DATASET);
			
			if (REModel.CLOSE) REModel.getResourceExplorer().getWindow().close();	
			
		}
        
        else if (resourceExplorer.getCaller().equals(WhoCall.CHART_RE)){
//        	System.out.println("-> " + selectedItems.get(0).getName());
        	CatalogueChartController.associateDatasetToChart(((ResourceExplorerDataset) resourceExplorer).getChartRE(), selectedItems.get(0));
        	REModel.getResourceExplorer().getWindow().close();	
		}
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
	
	
	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				
				if(item!=null){
					grid.setContextMenu(new CatalogueContextMenuDataset(resourceExplorer, catalogue).buildMenu(item));	
				}
				else{ 
					System.out.println("############# CatalogueDatasetController GRID: setContextMenu selectedItem is NULL");
				}	
			}
		};
	}

	private static void updateOfcChartDimensions(OfcChartWindow win) {
		String datasetId = win.getTabPanel().getSingleDatasetPanel().getDataSource().getValue(win.getTabPanel().getSingleDatasetPanel().getDataSource().getSelectedIndex());
		ListBox x = win.getTabPanel().getSingleDatasetPanel().getXAxis().getDimensions();
		ListBox y = win.getTabPanel().getSingleDatasetPanel().getYAxis().getDimensions();
		ListBox z = win.getTabPanel().getSingleDatasetPanel().getZAxis().getDimensions();
		ListBox w = win.getTabPanel().getSingleDatasetPanel().getWAxis().getDimensions();
		ListBox values = win.getTabPanel().getSingleDatasetPanel().getValues(); 
		OfcChartController.updateDimensions(datasetId, x, y, z, w, values);
	}

}
