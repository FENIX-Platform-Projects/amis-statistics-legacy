package org.fao.fenix.web.modules.re.client.control.tableview;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.chart.CatalogueContextMenuChart;
import org.fao.fenix.web.modules.re.client.view.tableview.ResourceExplorerTableView;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CatalogueTableViewController extends CatalogueController {

	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				onDoubleClickAction(resourceExplorer);
			}
		};
	}

	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer) {
		Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
		ResourceChildModel model = table.getSelectionModel().getSelectedItem();
		final List<ResourceChildModel> resources = new ArrayList<ResourceChildModel>();
		if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
			resources.add(model);
			System.out.println("CatalogueTableViewController: " + model.getId());
			System.out.println("CatalogueTableViewController: " + model.getLocalId());
			new TableViewWindow().build(Long.valueOf(model.getId()), Long.valueOf(model.getLocalId()), false, false);
//			PMModel.addResourceToProjectManager(resources, ResourceType.CHARTVIEW);
			resourceExplorer.getWindow().close();
		} else if (resourceExplorer.getCaller().equals(WhoCall.DESIGNER)) {
			DesignerBox designerBox = ((ResourceExplorerTableView) resourceExplorer).getDesignerBox();
			designerBox.setResourceID(Long.valueOf(model.getId()));
			designerBox.setResourceType(org.fao.fenix.web.modules.core.common.vo.ResourceType.TABLE);
			designerBox.setResourceTitle(model.getName());
			designerBox.getResourceName().setValue(model.getName());
			resourceExplorer.getWindow().close();
		}
	}

	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				if (item != null) {
					grid.setContextMenu(new CatalogueContextMenuChart(resourceExplorer, catalogue).buildMenu());
				} else {

				}
			}
		};
	}

}