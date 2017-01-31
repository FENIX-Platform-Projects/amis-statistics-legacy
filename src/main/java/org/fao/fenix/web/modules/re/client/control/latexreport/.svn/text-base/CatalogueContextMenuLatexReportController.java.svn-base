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

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.designer.client.view.DesignerWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.latex.common.services.LatexServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CatalogueContextMenuLatexReportController extends CatalogueContextMenuController {

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
					open(Long.valueOf(model.getId()), model.getName());
				
				REModel.getResourceExplorer().getWindow().close();
						
			}
		};
	}
	
	@SuppressWarnings("deprecation")
	public static SelectionListener<MenuEvent> edit(final Grid<ResourceChildModel> table) {
		
		return new SelectionListener<MenuEvent>() {
			
			public void componentSelected(MenuEvent evt) {
				
				//hide the resource explorer
				REModel.getResourceExplorer().getWindow().close();
				REModel.setResourceExplorer(null);
				
				//final List<ResourceChildModel> textList = new ArrayList<ResourceChildModel>();
				final List<ResourceChildModel> selectedItems = table.getSelectionModel().getSelectedItems();
				
				for (ResourceChildModel model: selectedItems) 
					edit(Long.valueOf(model.getId()), model.getName());
				
				REModel.getResourceExplorer().getWindow().close();
						
			}
		};
	}
	
	public static void open(final Long reportID, final String reportName) {
		try {
			final LoadingWindow l = new LoadingWindow("LaTeX Report", "Workstation is loading user's settings and it is generating the report with LaTeX.", "Please wait...");
			LatexServiceEntry.getInstance().exportPDF(reportID, CheckLanguage.getLanguage(), new AsyncCallback<String>() {
				public void onSuccess(String filepath) {
					l.destroyLoadingBox();
					Window.open("../exportObject/" + filepath, "_blank", "menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes");
					l.destroyLoadingBox();
				};
				public void onFailure(Throwable e) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
	}
	
	public static void edit(final Long reportID, final String reportName) {
		try {
			final LoadingWindow l = new LoadingWindow("LaTeX Report", "Workstation is loading user's settings.", "Please wait...");
			LatexServiceEntry.getInstance().load(reportID, new AsyncCallback<Map<String,List<ResourceViewVO>>>() {
				public void onSuccess(Map<String,java.util.List<ResourceViewVO>> map) {
					l.destroyLoadingBox();
					DesignerWindow w = new DesignerWindow();
					w.build(reportID, reportName, map);
					l.destroyLoadingBox();
				};
				public void onFailure(Throwable e) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
		}
	}
	
}