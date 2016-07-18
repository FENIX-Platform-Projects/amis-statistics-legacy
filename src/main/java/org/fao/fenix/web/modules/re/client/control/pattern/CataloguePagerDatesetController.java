package org.fao.fenix.web.modules.re.client.control.pattern;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CataloguePagerDatesetController extends CataloguePagerController {

	public static SelectionListener<ComponentEvent> openResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ComponentEvent>(){
			public void componentSelected(ComponentEvent ce){
				Grid<ResourceChildModel> table=resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				if (resourceExplorer.getCaller().equals(WhoCall.CHART)){
				
			     	List<List<String>> Patterns=new ArrayList<List<String>>();
					for (ResourceChildModel model: selectedItems){
						List<String> element=new ArrayList<String>();
						element.add(model.getName()); // i.e. title
						element.add(model.getId());
						Patterns.add(element);
					}
					
					//add to Project Manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.PATTERN);
						
					
//					((ResourceExplorerPattern) resourceExplorer).getChartWizard().getSelectData().addPatterns(Patterns);
					resourceExplorer.getWindow().hide();
					
				} else if (resourceExplorer.getCaller().equals(WhoCall.USER)){
					FenixAlert.alert("Message", "Under construction - Patterns");
				}
						
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> deselectedResources(final ResourceExplorer resourceExplorer){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				FenixAlert.alert("Message", "Under Construction");
						
			}
		};
	}

}