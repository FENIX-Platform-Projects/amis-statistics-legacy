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

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.chart.ResourceExplorerChart;
import org.fao.fenix.web.modules.re.client.view.olap.ResourceExplorerOlap;
import org.fao.fenix.web.modules.re.client.view.text.CatalogueContextMenuText;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCENativeController;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueOlapController extends CatalogueController {

	public static Listener<BaseEvent> setContextMenu(final Grid<ResourceChildModel> grid, final ResourceExplorer resourceExplorer, final Catalogue catalogue) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				ResourceChildModel item = grid.getSelectionModel().getSelectedItem();
				if (item != null) {
					grid.setContextMenu(new CatalogueContextMenuText(resourceExplorer, catalogue).buildMenu());
				} else {
					GWT.log("CatalogueOlapController GRID: setContextMenu selectedItem is NULL", new Throwable());
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
		final List<ResourceChildModel> olapList = new ArrayList<ResourceChildModel>();
		Grid<ResourceChildModel> table = resourceExplorer.getCatalogue().getGrid();
		final ResourceChildModel model = table.getSelectionModel().getSelectedItem();
		final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
		if (resourceExplorer.getCallback() != null) {
			resourceExplorer.getCallback().callback(resourceExplorer, selectedItems);
		} else if (resourceExplorer.getCaller().equals(WhoCall.USER)) {
			CatalogueContextMenuOlapController.open(Long.valueOf(model.getId()), model.getName(), WhoCall.USER, "");
			olapList.add(model);
//			PMModel.addResourceToProjectManager(olapList, ResourceType.OLAP);
			if (REModel.CLOSE)
				resourceExplorer.getWindow().close();
		} 
		
		else if (resourceExplorer.getCaller().equals(WhoCall.DESIGNER)) {
//			 DesignerBox designerBox = ((ResourceExplorerOlap)resourceExplorer).getDesignerBox();
//			 designerBox.setResourceID(Long.valueOf(model.getId()));
//			 designerBox.setResourceType(org.fao.fenix.web.modules.core.common.vo.ResourceType.OLAP);
//			 designerBox.setResourceTitle(model.getName());
//			 designerBox.getResourceName().setValue(model.getName());
//			 resourceExplorer.getWindow().close();
			 String tinyMCEPanelID = ((ResourceExplorerOlap)resourceExplorer).getTinyMCEPanelID();
			 TinyMCEController.loadOLAP(tinyMCEPanelID, Long.valueOf(model.getId()));
			 resourceExplorer.getWindow().close();
		 }
		
		else if (resourceExplorer.getCaller().equals(WhoCall.REPORT)) {
			String tinyMCEPanelID = ((ResourceExplorerOlap)resourceExplorer).getTinyMCEPanelID();
			String tinyMCEPanelMarkID = ((ResourceExplorerOlap)resourceExplorer).getTinyMCEPanelMarkID();
			System.out.println("**********************************************************************");
			System.out.println("tinyMCEPanelID " + tinyMCEPanelID);
			System.out.println("tinyMCEPanelMarkID " + tinyMCEPanelMarkID);
			System.out.println(TinyMCENativeController.getContent(tinyMCEPanelID));
			System.out.println("**********************************************************************");
			TinyMCEController.addOLAPToTemplate(tinyMCEPanelID, Long.valueOf(model.getId()), tinyMCEPanelMarkID);
			resourceExplorer.getWindow().close();
		}
		
//		else if (resourceExplorer.getCaller().equals(WhoCall.REPORT)){
//			final ReportViewer reportViewer = ((ResourceExplorerOlap)resourceExplorer).getReportViewer();
//			final String olapId = model.getId();
//			final String olapTitle = model.getName();
//			final String olapCode = olapId + String.valueOf((int)(Math.random() * 100));
//			OlapServiceEntry.getInstance().loadOlapUserSettings(Long.valueOf(olapId), new AsyncCallback<ResourceViewVO>() {
//				public void onSuccess(ResourceViewVO bean) {
//					final String olapHtml = bean.getOlapHTML();
//					BirtServiceEntry.getInstance().addTableToReport(olapCode, olapId, reportViewer.getRptDesign(), reportViewer.getObjectPosition(), reportViewer.getKeyTemplate(), olapHtml, new AsyncCallback<String>(){
//						public void onSuccess(String s) {
//							// TODO add table ID and Name to the side bar
//							reportViewer.getSideBar().addElementToListResource(olapCode, olapTitle, "table", reportViewer.getObjectPosition());
//							reportViewer.getReport().setHTML(s);
//						}
//						
//						public void onFailure(Throwable caught) {
//							Info.display("Service call failed!", "Service call to {0} failed", "createReport()");
//						}
//					});
//				}
//				public void onFailure(Throwable arg0) {						
//				}
//			});
//		}
		resourceExplorer.getWindow().close();
	}

}