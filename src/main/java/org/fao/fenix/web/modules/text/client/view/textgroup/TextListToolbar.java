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

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.text.client.control.textgroup.TextListToolbarController;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class TextListToolbar {

	public static ToolBar build(TextGroupWindow tgw, boolean isEditable)
	{
		ToolBar toolBar = new ToolBar();

		IconButton addTextButton = new IconButton("addText");
		addTextButton.setToolTip(BabelFish.print().addTextToGroup());
		addTextButton.addSelectionListener(TextListToolbarController.addText(tgw));
		toolBar.add(addTextButton);

		toolBar.add(new SeparatorToolItem());

		IconButton saveButton = new IconButton("save");
		saveButton.setToolTip(BabelFish.print().saveTextGroup());
		saveButton.addSelectionListener(TextListToolbarController.save(tgw));

		IconButton copyButton = new IconButton("copyTextGroup");
		copyButton.setToolTip(BabelFish.print().copyTextGroup());
		copyButton.addSelectionListener(TextListToolbarController.cloneTextGroup(tgw));

		//GWT.log("buildToolbar:  textgroup isEditable = "+isEditable, null);

		toolBar.add(saveButton);
		toolBar.add(new SeparatorToolItem());
		
		if(tgw.getTextGroupID()!=null){
			if(isEditable){
				toolBar.add(copyButton);
				toolBar.add(new SeparatorToolItem());
			} 

			IconButton metadataButton = new IconButton("info");
			metadataButton.setToolTip(BabelFish.print().viewMetadata());
			metadataButton.addSelectionListener(TextListToolbarController.showTextGroupMetadata(tgw.getTextGroupID(), isEditable));

			toolBar.add(metadataButton);
		}
		
		return toolBar;
	}

}
