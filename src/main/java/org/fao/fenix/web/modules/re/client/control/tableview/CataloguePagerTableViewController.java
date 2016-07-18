package org.fao.fenix.web.modules.re.client.control.tableview;

import java.util.List;

import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CataloguePagerTableViewController {

	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
					for (ResourceChildModel model : selectedItems) {
						new TableViewWindow().build(Long.valueOf(model.getId()), Long.valueOf(model.getLocalId()), false, false);
					}
//					PMModel.addResourceToProjectManager(selectedItems, ResourceType.CHARTVIEW);
					if (REModel.CLOSE)
						resourceExplorer.getWindow().close();
				}
			}

		};
	}
	
	@SuppressWarnings("deprecation")
	public static SelectionListener<ButtonEvent> deselectedResources(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				resourceExplorer.getWindow().close();
			}
		};
	}

}