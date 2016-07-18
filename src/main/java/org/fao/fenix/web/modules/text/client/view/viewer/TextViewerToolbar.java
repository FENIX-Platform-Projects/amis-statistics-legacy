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

package org.fao.fenix.web.modules.text.client.view.viewer;


import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.text.client.control.TextEditorController;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class TextViewerToolbar {

	ToolBar toolBar;
	TextViewer textViewer;
	boolean isEditable;

	public TextViewerToolbar(TextViewer viewer, boolean isEditable) {
		this.textViewer = viewer;
		this.isEditable = isEditable;
	}

	public ToolBar getToolbar() {

		toolBar = new ToolBar();

		IconButton iconButton;

		final String textContent = (textViewer.getTextContent().toString()); 

		iconButton = new IconButton("textEditBtn");
		iconButton.setTitle(BabelFish.print().editText());
		iconButton.addSelectionListener(TextEditorController.getTextEditor(textViewer.getTextViewId(), textViewer.getData(), textViewer.getWindow()));
		if(isEditable){
			toolBar.add(iconButton);
			toolBar.add(new SeparatorToolItem());
		}	

        // PDF button
		iconButton = new IconButton("pdfIcon");
		iconButton.setTitle(BabelFish.print().exportPDF());
		iconButton.addSelectionListener(TextEditorController.exportToPDF(textContent));
		toolBar.add(iconButton);


		// print button
		//ToolItem itemPrint = new ToolItem(Style.PUSH);
		//itemPrint.setIconStyle("mapPrintBtn");
		//itemPrint.setToolTip(strings.print());
		//toolbar.add(itemPrint);

		// return result
		return toolBar;

	}

}
