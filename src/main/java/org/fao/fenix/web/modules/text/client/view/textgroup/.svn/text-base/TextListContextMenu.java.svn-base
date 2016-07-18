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

package org.fao.fenix.web.modules.text.client.view.textgroup;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.text.client.control.textgroup.TextMenuController;
import org.fao.fenix.web.modules.text.common.model.Text;

import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;

public class TextListContextMenu {

	protected Menu contextMenu;

	protected DataList list;
	protected DataListItem selectedItem;
	protected Text selectedModel;
	protected TextGroupWindow parent;
	
	public TextListContextMenu(TextGroupWindow window) {
		contextMenu = new Menu();
		parent = window;
	}

	public Menu build(final DataListItem item) {

		if (item != null) {
			selectedItem = item;
			selectedModel= item.getModel();
			
		//	GWT.log("createContextMenu:  text = "+selectedItem.getText()+": isEditable = "+selectedItem.getTextVO().isHasWritePermission(), null);
			
			contextMenu = build();
		}

		return contextMenu;
	}

	public Menu build() {

		contextMenu = new Menu();

		MenuItem removeText = new MenuItem();
		removeText.setText (BabelFish.print().removeTextFromGroup());
		removeText.setIconStyle("deleteObj");
		removeText.addSelectionListener(TextMenuController.removeText(selectedModel, parent));
		contextMenu.add(removeText);

		MenuItem rename = new MenuItem();
		rename.setText (BabelFish.print().rename());
		rename.setIconStyle ("rename");
		rename.addSelectionListener(TextMenuController.renameText(selectedModel, parent));
		
		MenuItem delete = new MenuItem();
		delete.setText (BabelFish.print().deleteText());
		delete.setIconStyle ("delete");
		delete.addSelectionListener(TextMenuController.deleteText(selectedModel, parent));
		
		
		if(selectedModel.getTextVO().isHasWritePermission() || FenixUser.hasAdminRole()){
			contextMenu.add(rename);
		}	

		contextMenu.add(new SeparatorMenuItem());

		if(selectedModel.getTextVO().isHasDeletePermission() || FenixUser.hasAdminRole()){
			contextMenu.add(delete);
		}	

		
		MenuItem metadata = new MenuItem();
		metadata.setText (BabelFish.print().viewMetadata());
		metadata.setIconStyle ("info");
		metadata.addSelectionListener(TextMenuController.showMetadata(selectedModel, selectedModel.getTextVO().isHasWritePermission()));
		contextMenu.add(metadata);
		
		return contextMenu;

	}

}
