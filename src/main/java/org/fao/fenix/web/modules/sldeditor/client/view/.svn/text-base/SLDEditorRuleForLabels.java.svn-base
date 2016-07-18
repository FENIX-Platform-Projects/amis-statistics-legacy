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
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SLDEditorRuleForLabels extends SLDEditorRule {

	private ListBox fontFamilyListBox;

	private ListBox fontStyleListBox;

	private TextField<String> fontSizeListBox;

	private ListBox fontWeightListBox;

	private HTML fillColor;

	private Slider fillOpacity;
	
	private CheckBox styleLabels;
	
	public SLDEditorRuleForLabels(SLDEditorLabel sldEditorLabel) {
		fontFamilyListBox = new ListBox();
		fontStyleListBox = new ListBox();
		fontSizeListBox = new TextField<String>();
		fontSizeListBox.setAllowBlank(false);
		fontWeightListBox = new ListBox();
		fillColor = new HTML("<div align='center' style='background-color: #CA1616; color: white; font-weight: bold; font-style: italic;'>#CA1616</div>");
		fillOpacity = new Slider();
		styleLabels = new CheckBox();
		this.getActionPanel().add(buildStyleLabelsPanel(sldEditorLabel));
	}
	
	public HorizontalPanel buildStyleLabelsPanel(SLDEditorLabel sldEditorLabel) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		styleLabels.setBoxLabel("Format Labels");
		styleLabels.addListener(Events.OnClick, SLDEditorController.styleLabels(this, sldEditorLabel));
		p.add(styleLabels);
		removableCheckbox.add(p);
		return p;
	}
	
	public HorizontalPanel buildFontFamilyPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fontFamilyLabel = new Html("<b>Font Family</b>");
		fontFamilyLabel.setWidth(LABEL_WIDTH);
		fontFamilyLabel.setHeight("25px");
		p.add(fontFamilyLabel);
		fontFamilyListBox.clear();
		fontFamilyListBox.addItem("Arial", "Arial");
		fontFamilyListBox.addItem("Serif", "Serif");
		fontFamilyListBox.addItem("Sans Serif", "Sans Serif");
		fontFamilyListBox.addItem("Times New Roman", "Times New Roman");
		fontFamilyListBox.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(fontFamilyListBox);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFontStylePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fontStyleLabel = new Html("<b>Font Style</b>");
		fontStyleLabel.setWidth(LABEL_WIDTH);
		fontStyleLabel.setHeight("25px");
		p.add(fontStyleLabel);
		fontStyleListBox.clear();
		fontStyleListBox.addItem("Normal", "Normal");
		fontStyleListBox.addItem("Italic", "Italic");
		fontStyleListBox.addItem("Oblique", "Oblique");
		fontStyleListBox.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(fontStyleListBox);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFontSizePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fontSizeLabel = new Html("<b>Font Size</b>");
		fontSizeLabel.setWidth(LABEL_WIDTH);
		fontSizeLabel.setHeight("25px");
		p.add(fontSizeLabel);
		fontSizeListBox.setWidth(MEDIUM_FIELD_WIDTH);
		fontSizeListBox.setEmptyText("e.g. 8");
		p.add(fontSizeListBox);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFontWeightPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fontWeightLabel = new Html("<b>Font Weight</b>");
		fontWeightLabel.setWidth(LABEL_WIDTH);
		fontWeightLabel.setHeight("25px");
		p.add(fontWeightLabel);
		fontWeightListBox.clear();
		fontWeightListBox.addItem("Normal", "Normal");
		fontWeightListBox.addItem("Bold", "bold");
		fontWeightListBox.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(fontWeightListBox);
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFillColorPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fillColorLabel = new Html("<b>Fill Color</b>");
		fillColorLabel.setWidth(LABEL_WIDTH);
		fillColorLabel.setHeight("25px");
		p.add(fillColorLabel);
		fillColor.setWidth(MEDIUM_FIELD_WIDTH);
		p.add(fillColor);
		fillColor.addClickHandler(SLDEditorController.colorPicker(fillColor));
		removablePanels.add(p);
		return p;
	}
	
	public HorizontalPanel buildFillOpacityPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html fillOpacityLabel = new Html("<b>Fill Opacity</b>");
		fillOpacityLabel.setWidth(LABEL_WIDTH);
		fillOpacityLabel.setHeight("25px");
		p.add(fillOpacityLabel);
		fillOpacity.setWidth(MEDIUM_FIELD_WIDTH);
		fillOpacity.setIncrement(1);
		fillOpacity.setValue(100);
		p.add(fillOpacity);
		removablePanels.add(p);
		return p;
	}

	public ListBox getFontFamilyListBox() {
		return fontFamilyListBox;
	}

	public ListBox getFontStyleListBox() {
		return fontStyleListBox;
	}

	public TextField<String> getFontSizeTextField() {
		return fontSizeListBox;
	}

	public ListBox getFontWeightListBox() {
		return fontWeightListBox;
	}

	public HTML getFillColor() {
		return fillColor;
	}

	public Slider getFillOpacity() {
		return fillOpacity;
	}

	public CheckBox getStyleLabels() {
		return styleLabels;
	}
	
}