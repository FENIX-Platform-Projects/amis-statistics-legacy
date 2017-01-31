package org.fao.fenix.web.modules.re.client.control.image;

import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;

public class CataloguePagerImageController extends CataloguePagerController {

	@SuppressWarnings("deprecation")
	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
					for (ResourceChildModel model : selectedItems) {
						ResourceOpener.openImageViewer(Long.valueOf(model.getId()), model.hasWritePermission());
					}
				}
				resourceExplorer.getWindow().close();
			}
		};
	}

}