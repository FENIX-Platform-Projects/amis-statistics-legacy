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
package org.fao.fenix.web.modules.shared.window.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;

public class SaveAsWindow extends FenixWindow {
	
	private Button button;
	
	private TextField<String> textField;

	/**
	 * Default window to save something.
	 * 
	 * @param labelValue This label will be put close to the text field.
	 * @param defaultValue Default value for the text field.
	 * @param listener Listener for the "Save" button.
	 */
	public SaveAsWindow(String labelValue, String defaultValue, SelectionListener<ButtonEvent> listener) {
		this.buildCenterPanel(labelValue, defaultValue, listener);
		this.setSize("350px", "100px");
		this.setTitle(BabelFish.print().save());
		this.getWindow().setModal(true);
		this.show();
	}
	
	public SaveAsWindow(String labelValue, String defaultValue) {
		this.buildCenterPanel(labelValue, defaultValue);
		this.setSize("350px", "100px");
		this.setTitle(BabelFish.print().save());
		this.getWindow().setModal(true);
	}
	
	private void buildCenterPanel(String labelValue, String defaultValue, SelectionListener<ButtonEvent> listener) {
		setCenterProperties();
		getCenter().add(buildPanel(labelValue, defaultValue, listener));
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
	}
	
	private void buildCenterPanel(String labelValue, String defaultValue) {
		setCenterProperties();
		getCenter().add(buildPanel(labelValue, defaultValue));
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
	}
	
	private HorizontalPanel buildPanel(String labelValue, String defaultValue, SelectionListener<ButtonEvent> listener) {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(10);
		HTML label = new HTML("<b>" + labelValue + ": </b>");
		textField = new TextField<String>();
		textField.setValue(defaultValue);
		button = new Button(BabelFish.print().save(), listener);
		horizontalPanel.add(label);
		horizontalPanel.add(textField);
		horizontalPanel.add(button);
		return horizontalPanel;
	}
	
	private HorizontalPanel buildPanel(String labelValue, String defaultValue) {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(10);
		HTML label = new HTML("<b>" + labelValue + ": </b>");
		textField = new TextField<String>();
		textField.setValue(defaultValue);
		button = new Button(BabelFish.print().save());
		horizontalPanel.add(label);
		horizontalPanel.add(textField);
		horizontalPanel.add(button);
		return horizontalPanel;
	}

	public Button getButton() {
		return button;
	}

	public TextField<String> getTextField() {
		return textField;
	}
	
}