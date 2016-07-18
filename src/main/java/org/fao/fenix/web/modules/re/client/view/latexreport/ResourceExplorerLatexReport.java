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
package org.fao.fenix.web.modules.re.client.view.latexreport;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerLatexReport extends ResourceExplorer {

	public ResourceExplorerLatexReport() {
		setCaller(WhoCall.USER);
		buildInterface();
	}

	private void buildInterface() {
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType = ResourceType.REPORT;
		setSearchParameters(new SearchParametersLatexReport(this));
		setSearchButtons(new SearchButtons(this));
		setCataloguePager(new CataloguePagerLatexReport(this));
		setCatalogue(new CatalogueLatexReport(this));
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

}