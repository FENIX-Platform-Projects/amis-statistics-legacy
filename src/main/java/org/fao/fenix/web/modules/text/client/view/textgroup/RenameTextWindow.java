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
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.text.client.control.textgroup.TextListController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;

/**
 * RenameTextWindow
 * Contains edit title text field only.
 * Alternative to opening the full metadata editor.
 */
public class RenameTextWindow extends FenixWindow {

	private FormPanel formPanel;

	private TextField<String> textField;
	private FormData formData;

	public RenameTextWindow(Long textId, String originalName, TextGroupWindow tgw) {

		    formPanel = new FormPanel();
		    formPanel.setHeaderVisible(false);
		    formPanel.setFrame(true);

		    formData = new FormData("-20");

		    textField = new TextField<String>();
		    textField.setFieldLabel(BabelFish.print().rename());
		    textField.setAllowBlank(false);
		    //textField.setHideLabel(true);
		 	textField.setValue(originalName);
			formPanel.add(textField, formData);

		    Button rename = new Button(BabelFish.print().save());
		    rename.addSelectionListener(TextListController.rename(textField, textId, tgw, this));

		    Button cancel = new Button(BabelFish.print().cancel());
		    cancel.addSelectionListener(TextListController.cancel(this));

		    formPanel.addButton(rename);
		    formPanel.addButton(cancel);

		    formPanel.setButtonAlign(HorizontalAlignment.CENTER);

		    FormButtonBinding binding = new FormButtonBinding(formPanel);
		    binding.addButton(rename);
		    binding.addButton(cancel);

	}


    public void build() {

		setTitle(BabelFish.print().renameText());

		setSize(450, 150);
		setCollapsible(true);
		setMaximizable(true);

		fillCenterPart(formPanel);
		getCenter().setHeaderVisible(false);

		show();
    }


}
