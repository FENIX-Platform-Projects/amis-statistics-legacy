package org.fao.fenix.web.modules.re.client.control.chart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueContextMenuChartController extends CatalogueContextMenuController  {

	public static SelectionListener<MenuEvent> open(final ResourceExplorer resourceExplorer){
		return new SelectionListener<MenuEvent>(){
			public void componentSelected(MenuEvent ce){
				      	
						Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
						final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
						
						//hide the resource explorer
						//REModel.getResourceExplorer().getWindow().hide();
						REModel.setResourceExplorer(null);
						
//						for (ResourceChildModel model: selectedItems){
//							new ChartViewer(model.getId());
//						}
						
						Map<Long, String> ids = new HashMap<Long, String>();
						for (ResourceChildModel m : selectedItems)
							ids.put(Long.valueOf(m.getId()), m.getName());
						ChartDesignerController.loadChart(ids, WhoCall.USER, "", null, null);
				
					//add to Project Manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.CHARTVIEW);
					
					if (REModel.CLOSE){
						//hide the context menu
						REModel.getResourceExplorer().setCatalogueContextMenu(null);
						resourceExplorer.getWindow().close();
					}
			}
		};
	}

}
