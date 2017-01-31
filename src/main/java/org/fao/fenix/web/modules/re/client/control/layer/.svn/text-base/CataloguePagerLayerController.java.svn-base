package org.fao.fenix.web.modules.re.client.control.layer;

import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.chart.CataloguePagerChartController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CataloguePagerLayerController extends CataloguePagerChartController{
	
	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				
                // We should unify the dispatching of evets inside fenix. Typeresource and whocall 
                // are way too intrusive. The callback is lighter and more general.
                if (resourceExplorer.getCallback() != null) {
                	
                    resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
                    //resourceExplorer.getWindow().close();
                } 
                else
                {                
                    
                    //add to project manager
                    PMModel.addResourceToProjectManager(selectedItems, ResourceType.LAYER);

                    resourceExplorer.getWindow().close();

                    FenixAlert.alert("Message", "Under Construction - Layer");
                }
						
			}
		};
	}
	

}
