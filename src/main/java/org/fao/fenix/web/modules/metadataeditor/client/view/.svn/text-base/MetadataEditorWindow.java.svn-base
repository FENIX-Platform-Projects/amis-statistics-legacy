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

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.OptionVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;

public class MetadataEditorWindow extends FenixWindow {

	private METabPanel tabPanel;

	private ButtonsPanel buttonsPanel;

	public MetadataEditorWindow() {
		tabPanel = new METabPanel();
		buttonsPanel = new ButtonsPanel();
	}

	public void build(boolean isDataset, boolean isEditable, boolean hasResourceSpecificFields) {
		buildCenterPanel(isDataset, isEditable, hasResourceSpecificFields);
		enhance(isEditable);
		format();
		enableButtons(isDataset, isEditable);
		show();
	}

	/**
	 * This is the method called from the wizards to save complex resources
	 * (e.g. ChartView). Same parameters except the SelectionListener for the
	 * Save button. These listener can be found in the static class MESaver.
	 * 
	 * @param isDataset When it's true "Dataset Type" and "Column Definition" panels are shown.
	 * @param isEditable When it's true all the fields are enabled and editable.
	 * @param saveListener Listener for the "Save" button at the bottom of the window.
	 */
	public void build(boolean isDataset, boolean isEditable, boolean hasResourceSpecificFields, SelectionListener<ButtonEvent> saveListener) {
		buildCenterPanel(isDataset, isEditable, hasResourceSpecificFields);
		enhance(isEditable);
		format();
		enableButtons(isDataset, isEditable);
		show();
		buttonsPanel.getSave().addSelectionListener(saveListener);
		tabPanel.getTabPanel().setSelection(tabPanel.getKeys());
	}
	
	public void build(boolean isDataset, boolean isEditable, boolean hasResourceSpecificFields, SelectionListener<ButtonEvent> saveListener, List<String> resourceSpecificFields) {
		buildCenterPanel(isDataset, isEditable, hasResourceSpecificFields, resourceSpecificFields);
		enhance(isEditable);
		format();
		enableButtons(isDataset, isEditable);
		show();
		buttonsPanel.getSave().addSelectionListener(saveListener);
		tabPanel.getTabPanel().setSelection(tabPanel.getKeys());
	}
	
	private void enableButtons(boolean isDataset, boolean isEditable) {
		buttonsPanel.getGetXml().setEnabled((isDataset && isEditable));
		buttonsPanel.getSave().setEnabled(isEditable);
	}

	private void enhance(boolean isEditable) {

		tabPanel.getDatasetTypePanel().enhanceRadioButtons();

		MEController.fillGaulStore(tabPanel);
		MEController.fillDatasetTypeStore(tabPanel.getDatasetTypePanel());
		tabPanel.getDatasetTypePanel().getDbListbox().addSelectionChangedListener(MEController.datasetTypeComboBoxListener(tabPanel, isEditable));
		buttonsPanel.getGetXml().addSelectionListener(MEController.getXml(tabPanel));
//		buttonsPanel.getAddColumn().addSelectionListener(MEController.addColumn(tabPanel.getColumnDefinitionPanel(), I18N.print().header(), "", false, new ArrayList<OptionVO>(), isEditable));
		buttonsPanel.getAddColumn().addSelectionListener(MEController.addColumn(tabPanel.getColumnDefinitionPanel(), BabelFish.print().header(), "", true, new ArrayList<OptionVO>(), isEditable));
		buttonsPanel.close(this);

		tabPanel.getColumnDefinition().addListener(Events.Select, MEController.enableAddColumn(buttonsPanel.getAddColumn(), true));
		tabPanel.getDatasetType().addListener(Events.Select, MEController.enableAddColumn(buttonsPanel.getAddColumn(), false));
		tabPanel.getGeneralInfo().addListener(Events.Select, MEController.enableAddColumn(buttonsPanel.getAddColumn(), false));
		tabPanel.getKeys().addListener(Events.Select, MEController.enableAddColumn(buttonsPanel.getAddColumn(), false));
	}

	private void buildCenterPanel(boolean isDataset, boolean isEditable, boolean hasResourceSpecificFields) {
		setCenterProperties();
		getCenter().add(tabPanel.build(isDataset, isEditable, hasResourceSpecificFields));
		getCenter().setHeaderVisible(false);
		getCenter().setBottomComponent(buttonsPanel.build());
		addCenterPartToWindow();
	}
	
	private void buildCenterPanel(boolean isDataset, boolean isEditable, boolean hasResourceSpecificFields, List<String> specificFields) {
		setCenterProperties();
		getCenter().add(tabPanel.build(isDataset, isEditable, hasResourceSpecificFields, specificFields));
		getCenter().setHeaderVisible(false);
		getCenter().setBottomComponent(buttonsPanel.build());
		addCenterPartToWindow();
	}

	private void format() {
		setSize("800px", "600px");
		getWindow().setHeading(BabelFish.print().metadataEditor());
		setCollapsible(true);
		getWindow().setResizable(false);
	}

	public METabPanel getTabPanel() {
		return tabPanel;
	}

	public ButtonsPanel getButtonsPanel() {
		return buttonsPanel;
	}

}
