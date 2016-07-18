package org.fao.fenix.web.modules.re.client.control.chart.utils;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddDatasetsToOfcChartWizard {
	
	
	public static void addDatasets(final ResourceExplorer resourceExplorer, final List<ResourceChildModel> resourceList){
		final Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
		final List<ResourceChildModel> selectedItems=table.getSelectionModel().getSelectedItems();
		
		
		final List<List<String>> datasets=new ArrayList<List<String>>();
		final int datasetSelected = (selectedItems.size()-1);
		for (int i = 0; i < selectedItems.size(); i++) {
			
			final ResourceChildModel model = selectedItems.get(i);
			final int count = i;
			
			BirtServiceEntry.getInstance().isQualitativeDataset(Long.valueOf(model.getId()), new AsyncCallback<Boolean>(){
				
				public void onSuccess(Boolean result){
					if (!result){
						resourceList.add(model);
						List<String> element=new ArrayList<String>();
						element.add(model.getName()); // i.e. title
						element.add(model.getId());
						datasets.add(element);
					} else {
						FenixAlert.alert("Warning", model.getName() + " is a qualitative dataset. You can't chart it.", "");
					}
					
					if (datasetSelected == count){
						//add to project manager
						PMModel.addResourceToProjectManager(resourceList, ResourceType.DATASET);
						
						((ResourceExplorerDataset) resourceExplorer).getOfcChartWizard().getSelectData().addDatasets(datasets);
						
					}
					
					resourceExplorer.getWindow().close();
					
				}
				
				public void onFailure(Throwable caught){
					Info.display("isQualitativeDataset", caught.getLocalizedMessage());
				}
			});
			
			
		}
	}

}
