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

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueContextMenuController;
import org.fao.fenix.web.modules.re.client.control.latexreport.CatalogueContextMenuLatexReportController;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.CatalogueContextMenu;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;

public class CatalogueContextMenuLatexReport extends CatalogueContextMenu {

	public CatalogueContextMenuLatexReport(ResourceExplorer explorer, Catalogue catalogue) {
		super(explorer, catalogue);
	}

	public Menu buildMenu() {

		// Open
		MenuItem open = new MenuItem(BabelFish.print().open());
		open.addSelectionListener(CatalogueContextMenuLatexReportController.open(catalogueGrid));
		contextMenu.add(open);
		contextMenu.add(new SeparatorMenuItem());
		
		// Edit
		MenuItem edit = new MenuItem(BabelFish.print().edit() + " " + BabelFish.print().report());
		edit.addSelectionListener(CatalogueContextMenuLatexReportController.edit(catalogueGrid));
		contextMenu.add(edit);
		contextMenu.add(new SeparatorMenuItem());

		// Delete
		MenuItem delete = new MenuItem(BabelFish.print().delete());
		delete.addSelectionListener(CatalogueContextMenuController.delete(catalogueGrid));
		if (FenixUser.hasUserRole()) {
			contextMenu.add(delete);
			contextMenu.add(new SeparatorMenuItem());
		}

		// View Metadata
		MenuItem metadata = new MenuItem(BabelFish.print().viewMetadata());
		metadata.addSelectionListener(CatalogueContextMenuController.viewMetadata(catalogueGrid));
		contextMenu.add(metadata);

		// set the permission with the permission manager
		addSetPermissions();

		return contextMenu;
	}
	
}