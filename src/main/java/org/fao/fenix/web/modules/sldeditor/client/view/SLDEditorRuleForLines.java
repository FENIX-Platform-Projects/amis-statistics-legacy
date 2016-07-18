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
package org.fao.fenix.web.modules.sldeditor.client.view;

import org.fao.fenix.web.modules.sldeditor.client.control.SLDEditorController;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SLDEditorRuleForLines extends SLDEditorRule {

	private TextField<String> strokeWidth;

	private HTML strokeColor;

	private ListBox lineStyleListBox;

	private CheckBox formatLines;
	

	public SLDEditorRuleForLines(SLDEditorLine sldEditorLine) {
		strokeWidth = new TextField<String>();
		strokeWidth.setAllowBlank(false);
		//strokeColor = new HTML("<div align='center' style='border: 2px solid #000000; background-color: #000000; color: white; font-weight: bold; font-style: italic;'>#000000</div>");
		strokeColor = new HTML("<div align='center' style='background-color: #000000; color: white; font-weight: bold; font-style: italic;'>#000000</div>");
		formatLines = new CheckBox();
		lineStyleListBox = new ListBox();
		this.getActionPanel().add(buildFormatLinesPanel(sldEditorLine));
	}

	public HorizontalPanel buildLineStylePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lineStyleLabel = new Html("<b>Line Style</b>");
		lineStyleLabel.setWidth(LABEL_WIDTH);
		lineStyleLabel.setHeight("25px");
		p.add(lineStyleLabel);
		lineStyleListBox.clear();
		lineStyleListBox.addItem("Normal", "Normal");
		lineStyleListBox.addItem("Dotted", "bold");
		lineStyleListBox.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(lineStyleListBox);
		removablePanels.add(p);
		return p;
	}

	public HorizontalPanel buildStrokeColorPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html strokeColorLabel = new Html("<b>Stroke Color</b>");
		strokeColorLabel.setWidth(LABEL_WIDTH);
		strokeColorLabel.setHeight("25px");
		p.add(strokeColorLabel);
		strokeColor.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(strokeColor);
		strokeColor.addClickHandler(SLDEditorController.colorPicker(strokeColor));
		removablePanels.add(p);
		return p;
	}

	public HorizontalPanel buildFormatLinesPanel(SLDEditorLine sldEditorLine) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		formatLines.setBoxLabel("Format Lines");
		formatLines.addListener(Events.OnClick, SLDEditorController.formatLines(this, sldEditorLine));
		p.add(formatLines);
		removableCheckbox.add(p);
		return p;
	}

	public HorizontalPanel buildStrokeWidthPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html strokeWidthLabel = new Html("<b>Stroke Width</b>");
		strokeWidthLabel.setWidth(LABEL_WIDTH);
		strokeWidthLabel.setHeight("25px");
		p.add(strokeWidthLabel);
		strokeWidth.setWidth(MEDIUM_FIELD_WIDTH);
		strokeWidth.setEmptyText("e.g. 2");
		p.add(strokeWidth);
		removablePanels.add(p);
		return p;
	}

	public TextField<String> getStrokeWidth() {
		return strokeWidth;
	}

	public HTML getStrokeColor() {
		return strokeColor;
	}

	public CheckBox getFormatLines() {
		return formatLines;
	}

	public ListBox getLineStyleListBox() {
		return lineStyleListBox;
	}

}