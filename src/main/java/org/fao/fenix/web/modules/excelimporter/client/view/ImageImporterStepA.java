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

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class ImageImporterStepA extends ExcelImporterStepPanel {
	
	private FormPanel formPanel;
	
	private FileUploadField dataFile;
	
	private final static String FIELD_WIDTH = "425px";

	public ImageImporterStepA(String suggestion, String width) {
		super(suggestion, width);
		formPanel = new FormPanel();
		dataFile = new FileUploadField();
		this.getLayoutContainer().add(buildFormPanel());
	}
	
	private FormPanel buildFormPanel() {
		formPanel.add(buildDataPanel());
		formPanel.setHeaderVisible(false);
		formPanel.setBodyBorder(false);
		return formPanel;
	}
	
	private HorizontalPanel buildDataPanel() {
		HorizontalPanel dataPanel = new HorizontalPanel();
		dataPanel.setSpacing(10);
		dataFile.setEmptyText("Image File");
		dataFile.setLabelStyle("font-weight:bold;");
		dataFile.setName("IMAGE");
		dataFile.setButtonOffset(10);
		dataFile.setWidth(FIELD_WIDTH);
		dataFile.setAllowBlank(false);
		dataFile.setAutoValidate(true);
		dataPanel.add(createLabel(BabelFish.print().file()));
		dataPanel.add(dataFile);
		return dataPanel;
	}

	public FileUploadField getDataFile() {
		return dataFile;
	}
	
}
