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
package org.fao.fenix.web.modules.re.client.control.olap;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.MTOpener;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueContextMenuOlapController extends CatalogueContextMenuController {

	@SuppressWarnings("deprecation")
	public static SelectionListener<MenuEvent> open(final Grid<ResourceChildModel> table) {
		
		return new SelectionListener<MenuEvent>() {
			
			public void componentSelected(MenuEvent evt) {
				
				//hide the resource explorer
				REModel.getResourceExplorer().getWindow().close();
				REModel.setResourceExplorer(null);
				
				//final List<ResourceChildModel> textList = new ArrayList<ResourceChildModel>();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				for (ResourceChildModel model: selectedItems)
					open(Long.valueOf(model.getId()), model.getName(), WhoCall.USER, "");
					
			    //add to Project Manager
				PMModel.addResourceToProjectManager(selectedItems, ResourceType.OLAP);
				
				if (REModel.CLOSE){
					REModel.getResourceExplorer().setCatalogueContextMenu(null);
					REModel.getResourceExplorer().getWindow().close();
				}
						
			}
		};
	}
	
	public static void open(final Long olapID, final String olapTitle, final String caller, final String tinyMCEPanelID) {
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().information(), BabelFish.print().loading(), BabelFish.print().loading());
		try {
			OlapServiceEntry.getInstance().load(olapID, new AsyncCallback<OLAPParametersVo>() {
				
				
				public void onSuccess(OLAPParametersVo vo) {
					loading.destroyLoadingBox();
					MTOpener.load(vo, olapTitle, caller, tinyMCEPanelID);
					loading.destroyLoadingBox();
				};
				public void onFailure(Throwable E2) {
					loading.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), E2.getMessage());
					loading.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			loading.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().error(), E1.getMessage());
			loading.destroyLoadingBox();
		}
	}
	
}