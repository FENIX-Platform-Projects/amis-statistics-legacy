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
package org.fao.fenix.web.modules.re.client.view.olap;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCENativeController;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;

public class ResourceExplorerOlap extends ResourceExplorer {

	public ResourceExplorerOlap() {
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	ReportViewer reportViewer;

	private void buildInterface() {
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType = ResourceType.OLAP;
		setSearchParameters(new SearchParametersOlap(this));
		setSearchButtons(new SearchButtons(this));
		setCataloguePager(new CataloguePagerOlap(this));
		setCatalogue(new CatalogueOlap(this));
		getWest().add(getSearchParameters().getMainCont());
		getWest().setBottomComponent(getSearchButtons().getCont());
		addWestPartToWindow();
		getCenter().add(getCatalogue().getGrid());
		getCenter().setBottomComponent(getCataloguePager().getMainCont());
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getWestData().setFloatable(true);
		show();
	}
	
	
	public ResourceExplorerOlap(ReportViewer reportViewer){
		this.reportViewer = reportViewer;
		setCaller(WhoCall.REPORT);
		buildInterface();
	}


	public ReportViewer getReportViewer() {
		return reportViewer;
	}

	public ResourceExplorerOlap(DesignerBox designerBox){
		this.setDesignerBox(designerBox);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
	public ResourceExplorerOlap(String tinyMCEPanelID){
		this.setTinyMCEPanelID(tinyMCEPanelID);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
	public ResourceExplorerOlap(final String tinyMCEPanelID, final String tinyMCEPanelMarkID, final String originalHTML) {
		this.setTinyMCEPanelID(tinyMCEPanelID);
		this.setTinyMCEPanelMarkID(tinyMCEPanelMarkID);
		this.setOriginalHTML(originalHTML);
		setCaller(WhoCall.REPORT);
		buildInterface();
//		getWindow().addWindowListener(new WindowListener() {
//			public void windowHide(WindowEvent we) {
//				super.windowHide(we);
//				String originalContent = TinyMCENativeController.getContent(tinyMCEPanelID);
//				String tmp = originalContent.replace(tinyMCEPanelMarkID, originalHTML);
//				TinyMCENativeController.replaceAll(tinyMCEPanelID);
//				TinyMCENativeController.setContent(tinyMCEPanelID, tmp);
//			}
//		});
	}
	
}