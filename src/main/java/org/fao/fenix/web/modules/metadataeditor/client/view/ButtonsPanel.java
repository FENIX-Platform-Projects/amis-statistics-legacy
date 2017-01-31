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
package org.fao.fenix.web.modules.metadataeditor.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class ButtonsPanel {

	private HorizontalPanel panel = new HorizontalPanel();
	
	private Button save;
	
	private Button close;
	
	private Button getXml;
	
	private Button addColumn;
	
	public ButtonsPanel() {
		panel = new HorizontalPanel();
		save = new Button(BabelFish.print().save());
		close = new Button(BabelFish.print().close());
		getXml = new Button(BabelFish.print().getMetadataArchive());
		addColumn = new Button(BabelFish.print().addColumn());
	}
	
	public HorizontalPanel build() {
		panel.add(getXml);
		panel.add(save);
		panel.add(close);
		panel.add(addColumn);
		format();
		return panel;
	}
	
	private void format() {
		panel.setSpacing(10);
		panel.setWidth("750px");
		save.setWidth("175px");
		close.setWidth("175px");
		getXml.setWidth("175px");
		addColumn.setWidth("175px");
		addColumn.setEnabled(false);
	}
	
	public void close(final FenixWindow window) {
		close.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getWindow().close();
			};
		});
	}

	public Button getGetXml() {
		return getXml;
	}

	public Button getAddColumn() {
		return addColumn;
	}

	public Button getSave() {
		return save;
	}
	
}
