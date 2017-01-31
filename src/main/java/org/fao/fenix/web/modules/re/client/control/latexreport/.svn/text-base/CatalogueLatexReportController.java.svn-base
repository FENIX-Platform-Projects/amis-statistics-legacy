/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.re.client.control.latexreport;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.latexreport.CatalogueContextMenuLatexReport;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;

public class CatalogueLatexReportController extends CatalogueController {

	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				if (item != null) {
					grid.setContextMenu(new CatalogueContextMenuLatexReport(resourceExplorer, catalogue).buildMenu());
				} else {
					GWT.log("CatalogueLatexReportController GRID: setContextMenu selectedItem is NULL", new Throwable());
				}
			}
		};
	}

	public static Listener<BaseEvent> onDoubleClick(final ResourceExplorer resourceExplorer) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				onDoubleClickAction(resourceExplorer);
			}
		};
	}

	@SuppressWarnings("deprecation")
	public static void onDoubleClickAction(final ResourceExplorer resourceExplorer) {
		final List<ResourceChildModel> imageList = new ArrayList<ResourceChildModel>();
		Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
		final ResourceChildModel model = table.getSelectionModel().getSelectedItem();
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
		if (resourceExplorer.getCallback() != null) {
			resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
		} else if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
//			CatalogueContextMenuLatexReportController.open(Long.valueOf(model.getId()), model.getName());
//			imageList.add(model);
			new TinyMCEReportWindow().build(Long.valueOf(model.getId()));
			resourceExplorer.getWindow().close();
		} 
		resourceExplorer.getWindow().close();
	}
	
}