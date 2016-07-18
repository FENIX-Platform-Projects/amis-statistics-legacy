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
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class CodeListStepB extends ExcelImporterStepPanel {
	
	private VerticalPanel wrapperPanel;
	
	private TextField<String> titleField;
	
	private TextField<String> sourceField;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private TextArea textArea;
	
	private SimpleComboBox<String> codingType;
	
	private final static String FIELD_WIDTH = "400px";
	
	public final static String LABEL_WIDTH = "150px";
	
	public CodeListStepB(String suggestion, String width) {
		super(suggestion, width);
		gaulStore = new ListStore<GaulModelData>();
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		MEController.fillGaulStore(gaulStore);
		this.getLayoutContainer().add(buildWrapperPanel());
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapperPanel = new VerticalPanel();
		wrapperPanel.setSpacing(5);
		wrapperPanel.add(buildSource());
		wrapperPanel.add(buildRegionPanel());
		wrapperPanel.add(buildTitle());
		wrapperPanel.add(buildCodingType());
		wrapperPanel.add(buildDescription());
		return wrapperPanel;
	}
	
	private HorizontalPanel buildSource() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		sourceField = new TextField<String>();
		sourceField.setWidth(FIELD_WIDTH);
		sourceField.setAllowBlank(false);
		sourceField.setAutoValidate(true);
		panel.add(createLabel(BabelFish.print().source(), LABEL_WIDTH));
		panel.add(sourceField);
		return panel;
	}
	
	private HorizontalPanel buildRegionPanel() {
		HorizontalPanel regionPanel = new HorizontalPanel();
		regionPanel.setSpacing(5);
		Html label = createLabel(BabelFish.print().metadataRegion(), LABEL_WIDTH);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setWidth(FIELD_WIDTH);
		gaulList.setAllowBlank(false);
		gaulList.setAutoValidate(true);
		regionPanel.add(label);
		regionPanel.add(gaulList);
		return regionPanel;
	}
	
	private HorizontalPanel buildTitle() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		titleField = new TextField<String>();
		titleField.setWidth(FIELD_WIDTH);
		titleField.setAllowBlank(false);
		titleField.setAutoValidate(true);
		panel.add(createLabel(BabelFish.print().title(), LABEL_WIDTH));
		panel.add(titleField);
		return panel;
	}
	
	private HorizontalPanel buildCodingType() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		codingType = new SimpleComboBox<String>();
		codingType.setWidth(FIELD_WIDTH);
		codingType.setAllowBlank(false);
		codingType.setAutoValidate(true);
		codingType.add("Commodity");
		codingType.add("Geographic");
		codingType.add("Indicator");
		panel.add(createLabel(BabelFish.print().type(), LABEL_WIDTH));
		panel.add(codingType);
		return panel;
	}
	
	private HorizontalPanel buildDescription() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		textArea = new TextArea();
		textArea.setWidth(FIELD_WIDTH);
		textArea.setAllowBlank(true);
		textArea.setAutoValidate(true);
		textArea.setHeight("50px");
		panel.add(createLabel(BabelFish.print().description(), LABEL_WIDTH));
		panel.add(textArea);
		return panel;
	}

	public VerticalPanel getWrapperPanel() {
		return wrapperPanel;
	}

	public TextField<String> getTitleField() {
		return titleField;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public SimpleComboBox<String> getCodingType() {
		return codingType;
	}

	public TextField<String> getSourceField() {
		return sourceField;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}
	
}
