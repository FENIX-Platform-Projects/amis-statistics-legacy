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
package org.fao.fenix.web.modules.excelimporter.client.view;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;

public class StepMU extends ExcelImporterStepPanel {

	private TextField<String> muField;
	
	private TextField<String> muHeader;
	
	private TextField<String> quantityHeader;
	
	private VerticalPanel wrapper;
	
	private final static String FIELD_WIDTH = "350px";
	
	public final static String LABEL_WIDTH = "200px";
	
	public StepMU(String suggestion, String width) {
		super(suggestion, width);
		this.getLayoutContainer().add(buildWrapper());
	}
	
	private VerticalPanel buildWrapper() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(5);
		wrapper.add(buildQuantityHeader());
		wrapper.add(buildMUHeader());
		wrapper.add(buildMU());
		return wrapper;
	}
	
	private HorizontalPanel buildQuantityHeader() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		quantityHeader = new TextField<String>();
		quantityHeader.setWidth(FIELD_WIDTH);
		quantityHeader.setAllowBlank(false);
		quantityHeader.setAutoValidate(true);
		quantityHeader.setEmptyText("e.g. Price");
		panel.add(createLabel("Quantity Header", LABEL_WIDTH));
		panel.add(quantityHeader);
		return panel;
	}
	
	private HorizontalPanel buildMUHeader() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		muHeader = new TextField<String>();
		muHeader.setWidth(FIELD_WIDTH);
		muHeader.setAllowBlank(false);
		muHeader.setAutoValidate(true);
		muHeader.setEmptyText("e.g. Measurement Unit");
		panel.add(createLabel("Measurement Unit Header", LABEL_WIDTH));
		panel.add(muHeader);
		return panel;
	}
	
	private HorizontalPanel buildMU() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		muField = new TextField<String>();
		muField.setWidth(FIELD_WIDTH);
		muField.setAllowBlank(false);
		muField.setAutoValidate(true);
		muField.setEmptyText("e.g. USD/kg");
		panel.add(createLabel("Value", LABEL_WIDTH));
		panel.add(muField);
		return panel;
	}
	
	public TextField<String> getMuField() {
		return muField;
	}

	public TextField<String> getMuHeader() {
		return muHeader;
	}

	public TextField<String> getQuantityHeader() {
		return quantityHeader;
	}
	
}