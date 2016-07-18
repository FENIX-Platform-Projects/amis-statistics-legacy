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

import org.fao.fenix.web.modules.excelimporter.client.control.ExcelImporterController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class CodeListImporterWindow extends FenixWindow {

	private CodeListPanel codeListPanel;
	
	public CodeListImporterWindow() {
		codeListPanel = new CodeListPanel();
	}
	
	public void build() {
		buildCenterPanel();
		codeListPanel.getNextButton().addSelectionListener(ExcelImporterController.next(this));
		codeListPanel.getPrevButton().addSelectionListener(ExcelImporterController.prev(this));
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(codeListPanel.build());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize("600px", "350px");
		getWindow().setHeading(BabelFish.print().codeListUploader());
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("uploadData");
		getWindow().setCollapsible(false);
	}

	public CodeListPanel getCodeListPanel() {
		return codeListPanel;
	}
	
}