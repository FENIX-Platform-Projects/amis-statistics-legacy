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
package org.fao.fenix.web.modules.latex.client.view;

import org.fao.fenix.web.modules.latex.client.control.LatexController;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class LatexToolbar {

	private ToolBar toolbar;
	
	private IconButton changeDocumentClass;
	
	private IconButton addPackage;
	
	private IconButton addTitle;
	
	private IconButton addAuthor;
	
	private IconButton addDate;
	
	private IconButton addMulticols;
	
	private IconButton addSweave;
	
	private IconButton exportPDF;
	
	public LatexToolbar() {
		toolbar = new ToolBar();
	}
	
	public ToolBar build(LatexWindow w) {
		
		// change Document Class
		changeDocumentClass = new IconButton("latex");
		changeDocumentClass.setToolTip("Change Document Class");
		toolbar.add(changeDocumentClass);
		toolbar.add(new SeparatorToolItem());
		
		// add package
		addPackage = new IconButton("latex", LatexController.addPackage(w));
		addPackage.setToolTip("Add Package");
		toolbar.add(addPackage);
		toolbar.add(new SeparatorToolItem());
		
		// add title
		addTitle = new IconButton("latex", LatexController.addTitle(w));
		addTitle.setToolTip("Add Title");
		toolbar.add(addTitle);
		toolbar.add(new SeparatorToolItem());
		
		// add author
		addAuthor = new IconButton("latex", LatexController.addAuthor(w));
		addAuthor.setToolTip("Add Author");
		toolbar.add(addAuthor);
		toolbar.add(new SeparatorToolItem());
		
		// add date
		addDate = new IconButton("latex", LatexController.addDate(w));
		addDate.setToolTip("Add Date");
		toolbar.add(addDate);
		toolbar.add(new SeparatorToolItem());
		
		// add multiple columns
		addMulticols = new IconButton("latex", LatexController.addMultiCols(w));
		addMulticols.setToolTip("Add Multiple Columns");
		toolbar.add(addMulticols);
		toolbar.add(new SeparatorToolItem());
		
		// add sweave
		addSweave = new IconButton("latex", LatexController.addSweave(w));
		addSweave.setToolTip("Add Sweave code");
		toolbar.add(addSweave);
		toolbar.add(new SeparatorToolItem());
		
		// generate PDF
		exportPDF = new IconButton("sendToPdf", LatexController.exportPDF(w));
		exportPDF.setToolTip("Export PDF");
		toolbar.add(exportPDF);
		toolbar.add(new SeparatorToolItem());
		
		return toolbar;
	}
	
}