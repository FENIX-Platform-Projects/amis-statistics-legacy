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

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.RichTextArea;

public class LatexWindow extends FenixWindow {

	private RichTextArea latexArea;
	
	private LatexToolbar latexToolbar;
	
	public LatexWindow() {
		latexArea = new RichTextArea();
		latexToolbar = new LatexToolbar();
	}
	
	public void build() {
		buildCenterPanel();
		addDefaultLatexCode();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(latexArea);
		getCenter().setBodyBorder(false);
		getCenter().setTopComponent(latexToolbar.build(this));
		addCenterPartToWindow();
	}
	
	private void addDefaultLatexCode() {
		String code = "<div id='DOCUMENTCLASS'><font style='color: red;'>\\documentclass</font>{article}<br></div>" +
					  "<div id='COMMENT'><br><font style='color: blue;'>%End of Preamble, Document Starts</font><br><br></div>" +
					  "<div id='BEGIN'><font style='color: red;'>\\begin</font>{document}<br></div><br>" +
					  "<div id='USER_TEXT'><font style='color: black;'>YOUR TEXT HERE<br></font></div>" +
					  "<div id='END'><font style='color: red;'>\\end</font>{document}<br></div>";
		latexArea.setHTML(code);
	}
	
	private void format() {
		latexArea.setHeight("100%");
		latexArea.setWidth("100%");
		this.getWindow().setHeading("LaTeX Editor");
		this.getWindow().setIconStyle("latex");
		this.getWindow().setSize("700px", "500px");
	}

	public LatexToolbar getLatexToolbar() {
		return latexToolbar;
	}

	public RichTextArea getLatexArea() {
		return latexArea;
	}
	
}