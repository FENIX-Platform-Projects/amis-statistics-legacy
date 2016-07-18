/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2011, by FAO of UN under the EC-FAO Food Security
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
package org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection;




import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATDataController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareDataController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare.FAOSTATCompareClose;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare.FAOSTATCompareSelectionPanel;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.CONSTANT;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.constants.FAOSTATCompareConstants;


import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FAOSTATMultipleSelectionPanel {
	
	FAOSTATCompareSelectionPanel selectionPanel;
	
	private FieldSet fieldSet;
	
	private HorizontalPanel row_one;
	
	private HorizontalPanel row_two;
	
	private VerticalPanel wrapper;
	
	private Integer FIELD_SET_WIDTH = 550;
	
//	private static final int SPACING = 1;
	
	String domainCode;
	
	private MultipleSelectionPanel items;
	
	private MultipleSelectionPanel elements;
	
	ContentPanel itemsSelected;
	
	ContentPanel elementsSelected;

	public FAOSTATMultipleSelectionPanel() {
		fieldSet = new FieldSet();
		fieldSet.setCollapsible(true);
		fieldSet.setWidth(FIELD_SET_WIDTH);
		row_one = new HorizontalPanel();
//		row_one.setSpacing(SPACING);
		row_one.setVerticalAlign(VerticalAlignment.MIDDLE);
		row_two = new HorizontalPanel();
		row_two.setVerticalAlign(VerticalAlignment.MIDDLE);
		wrapper = new VerticalPanel();
		wrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		items = new MultipleSelectionPanel(CONSTANT.ITEM);
		elements = new MultipleSelectionPanel(CONSTANT.ELEMENT);
	}
	
	public FAOSTATMultipleSelectionPanel(FAOSTATCompareSelectionPanel selectionPanel, String title, String domainCode) {
	
		this.domainCode = domainCode;
		this.selectionPanel = selectionPanel;
		
		fieldSet = new FieldSet();
		fieldSet.setCollapsible(true);
		fieldSet.setWidth(FIELD_SET_WIDTH);

		wrapper = new VerticalPanel();
		fieldSet.setHeading(title + " [" + domainCode + "]");
		row_one = new HorizontalPanel();
//		row_one.setSpacing(SPACING);
		
		itemsSelected = new ContentPanel();
		itemsSelected.setBodyBorder(false);
		itemsSelected.setHeaderVisible(false);
		elementsSelected = new ContentPanel();
		elementsSelected.setBodyBorder(false);
		elementsSelected.setHeaderVisible(false);
		
		items = new MultipleSelectionPanel(CONSTANT.ITEM, FAOSTATCompareConstants.MULTISELECTION_PANEL_HEIGHT, FAOSTATCompareConstants.MULTISELECTION_LIST_WIDTH, FAOSTATCompareConstants.MULTISELECTION_LIST_HEIGHT, itemsSelected);
		elements = new MultipleSelectionPanel(CONSTANT.ELEMENT, FAOSTATCompareConstants.MULTISELECTION_PANEL_HEIGHT, FAOSTATCompareConstants.MULTISELECTION_LIST_WIDTH, FAOSTATCompareConstants.MULTISELECTION_LIST_HEIGHT, elementsSelected);
		
	}
	
	public void enhanceGrids(String domainCode) {
		
		FAOSTATDataController.fillGridWithFilter(items.getGrid(), CONSTANT.ITEM.toString(), domainCode);

		FAOSTATDataController.fillGridWithFilter(elements.getGrid(), CONSTANT.ELEMENT.toString(), domainCode);
		
		items.getGrid().addListener(Events.OnClick, FAOSTATCompareDataController.buildParameters(itemsSelected, items.getGrid(), FAOSTATLanguage.print().items()));
		elements.getGrid().addListener(Events.OnClick, FAOSTATCompareDataController.buildParameters(elementsSelected, elements.getGrid(), FAOSTATLanguage.print().elements()));

	}

	public FieldSet build() {
		row_one.add(items);
		row_one.add(DataViewerClientUtils.addHSpace(5));
		row_one.add(elements);
		
		wrapper.add(buildClosePanel());
		wrapper.add(row_one);
		
		wrapper.setBorders(false);
		row_one.setBorders(false);
		
		wrapper.add(itemsSelected);
		wrapper.add(elementsSelected);
		fieldSet.add(wrapper);
		
		enhanceGrids(domainCode);
		
		return fieldSet;
	}
	

	public ContentPanel buildClosePanel(){ 
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("content_chart_top_panel");

		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
			
		panelL.addStyleName("content_chart_top_panel_left");
		panelR.addStyleName("content_chart_top_panel_right");

		FAOSTATCompareClose closePanel = new FAOSTATCompareClose();
//		panelR.add(closePanel.buildCloseDomain(selectionPanel, domainCode, fieldSet));
		panelR.add(closePanel.buildCloseDomain2(selectionPanel, domainCode, fieldSet));

		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.add(toolBar);	
		return panel;
	}

	
	
	public void fillStore(ListStore<DWCodesModelData> s) {
		s.add(new DWCodesModelData("Country", FAOSTATLanguage.print().country()));
		s.add(new DWCodesModelData("Commodity", FAOSTATLanguage.print().item()));
		s.add(new DWCodesModelData("Element", FAOSTATLanguage.print().element()));
	}
	

	public MultipleSelectionPanel getFAOSTATItems() {
		return items;
	}

	public MultipleSelectionPanel getElements() {
		return elements;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public MultipleSelectionPanel getItems() {
		return items;
	}

	
}