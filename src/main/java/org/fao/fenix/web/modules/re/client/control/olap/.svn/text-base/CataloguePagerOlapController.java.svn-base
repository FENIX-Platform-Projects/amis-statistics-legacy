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

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CataloguePagerController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.olap.ResourceExplorerOlap;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CataloguePagerOlapController extends CataloguePagerController {

	@SuppressWarnings("deprecation")
	public static SelectionListener<ButtonEvent> openResources(final ResourceExplorer resourceExplorer) {

		return new SelectionListener<ButtonEvent>() {

			public void componentSelected(ButtonEvent ce) {
				
				Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();

				if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
					
					for (ResourceChildModel model : selectedItems) {
						ResourceOpener.openOlapViewer(Long.valueOf(model.getId()), model.hasWritePermission());
					}

					// add to project manager
					PMModel.addResourceToProjectManager(selectedItems, ResourceType.OLAP);

				} 
				
				else if (resourceExplorer.getCaller().equals(WhoCall.REPORT)){

					// load multiple olap???
					ResourceChildModel model = selectedItems.get(0);
						final ReportViewer reportViewer = ((ResourceExplorerOlap)resourceExplorer).getReportViewer();
						final String olapId = model.getId();
						final String olapTitle = model.getName();
						final String olapCode = olapId + String.valueOf((int)(Math.random() * 100));
	
						OlapServiceEntry.getInstance().loadOlapUserSettings(Long.valueOf(olapId), new AsyncCallback<ResourceViewVO>() {
							public void onSuccess(ResourceViewVO bean) {
								final String olapHtml = bean.getOlapHTML();
								
								BirtServiceEntry.getInstance().addTableToReport(olapCode, olapId, reportViewer.getRptDesign(), reportViewer.getObjectPosition(), reportViewer.getKeyTemplate(), olapHtml, new AsyncCallback<String>(){
									
									
									public void onSuccess(String s) {
								
										// TODO add table ID and Name to the side bar
										reportViewer.getSideBar().addElementToListResource(olapCode, olapTitle, "table", reportViewer.getObjectPosition());
										
										reportViewer.getReport().setHTML(s);
									}
									
									public void onFailure(Throwable caught) {
										Info.display("Service call failed!", "Service call to {0} failed", "createReport()");
									}
									
								});
							}
							public void onFailure(Throwable arg0) {						
							}
						});
					}
				
				// close resource explorer
				resourceExplorer.getWindow().close();
			}
		};
	}
}
