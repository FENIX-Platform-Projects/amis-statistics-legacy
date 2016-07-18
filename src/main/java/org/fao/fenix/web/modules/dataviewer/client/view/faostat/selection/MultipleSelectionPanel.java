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

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATDataController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.CONSTANT;

//import org.fao.fenix.r.gwt.gui.client.control.RGWTController;
//import org.fao.fenix.r.gwt.gui.common.vo.CONSTANT;
//import org.fao.fenix.r.gwt.gui.common.vo.CodeLabelModelData;
//import org.fao.fenix.r.gwt.lang.client.FAOSTATLanguage;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.Element;


public class MultipleSelectionPanel extends LayoutContainer {

	private VerticalPanel p;
	
	private TextField<String> oracle;
	
	private Grid<DWCodesModelData> grid;
	
	private ListStore<DWCodesModelData> store;
	
	private ColumnModel columnModel;
	
	private CheckBoxSelectionModel<DWCodesModelData> selectionModel;
	
	private Integer PANEL_HEIGHT = 80;
	
	private Integer LIST_WIDTH = 140;
	
	private Integer LIST_HEIGHT = 70;
	
	private ContentPanel selectedCodes;
	
//	private final static int SPACING = 5;
	
	private CONSTANT TYPE;
	
	public MultipleSelectionPanel(CONSTANT TYPE) {
		this.setTYPE(TYPE);
		setLayout(new FillLayout());
		p = new VerticalPanel();
		p.setBorders(false);
//		p.setSpacing(SPACING);
		oracle = new TextField<String>();
		oracle.setEmptyText(suggestion());
		oracle.setWidth(this.getWIDTH());
		store = new ListStore<DWCodesModelData>();
		initColumnModel();
		initGrid();
	}
	
	public MultipleSelectionPanel(CONSTANT TYPE, Integer panelHeight, Integer listWidth, Integer listHeight, ContentPanel selectedCodes) {
		this.PANEL_HEIGHT = panelHeight;
		this.LIST_WIDTH = listWidth;
		this.LIST_HEIGHT = listHeight;
		this.selectedCodes = selectedCodes;
	
		this.setTYPE(TYPE);
		setLayout(new FillLayout());
		p = new VerticalPanel();
		p.setBorders(false);
//		p.setSpacing(SPACING);
		oracle = new TextField<String>();
		oracle.setEmptyText(suggestion());
		oracle.setWidth(this.getWIDTH());
		store = new ListStore<DWCodesModelData>();
		initColumnModel();
		initGrid();
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		p.add(grid);
		p.add(DataViewerClientUtils.addVSpace(3));
//		p.add(oracle);

		HorizontalPanel h = new HorizontalPanel();
		Button deselectAll = new Button(FAOSTATLanguage.print().deselectAll());
		deselectAll.setIconStyle("deleteObj");
		deselectAll.addSelectionListener(FAOSTATMainController.deselectAll(selectedCodes, grid));
		h.add(DataViewerClientUtils.addHSpace(3));
		h.add(deselectAll);
		p.add(h);
		
		p.setHeight(PANEL_HEIGHT);
		add(p);
		addStyleName("content_box");
		oracle.addListener(Events.OnKeyUp, FAOSTATDataController.oracleListener(grid, oracle));
	}
	
	public void initGrid() {
		grid = new Grid<DWCodesModelData>(store, columnModel);  
	    grid.setSelectionModel(selectionModel);  
	    grid.setAutoExpandColumn("label");
//	    grid.setColumnReordering(true);  
	    grid.setBorders(false);  
	    grid.addPlugin(selectionModel);  

	    grid.setHeight(LIST_HEIGHT);
	    grid.setWidth(this.getWIDTH());
	}
	
	public void initColumnModel() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		selectionModel = new CheckBoxSelectionModel<DWCodesModelData>();
		selectionModel.setSelectionMode(selectionMode());
		
		configs.add(selectionModel.getColumn());
		ColumnConfig column = new ColumnConfig();  
	    column.setId("label");  
	    column.setHeader(label());  
	    configs.add(column);  
	    columnModel = new ColumnModel(configs);  
	}
	
	public SelectionMode selectionMode() {
		switch (TYPE) {
			case DOMAIN: return SelectionMode.SINGLE;
			case COUNTRY: return SelectionMode.SIMPLE;
			case ITEM: return SelectionMode.SIMPLE;
			case ELEMENT: return SelectionMode.SIMPLE;
		}
		return SelectionMode.SINGLE;
	}
	
	public String label() {
		switch (TYPE) {
			case DOMAIN: return FAOSTATLanguage.print().domains();
			case COUNTRY: return FAOSTATLanguage.print().country();
			case ITEM: return FAOSTATLanguage.print().item();
			case ELEMENT: return FAOSTATLanguage.print().element();
		}
		return FAOSTATLanguage.print().group();
	}
	
	public String suggestion() {
		switch (TYPE) {
			case DOMAIN: return FAOSTATLanguage.print().egDomain();
			case COUNTRY: return FAOSTATLanguage.print().egCountry();
			case ITEM: return FAOSTATLanguage.print().egItem();
			case ELEMENT: return FAOSTATLanguage.print().egElement();
		}
		return FAOSTATLanguage.print().egGroup();
	}


	public CONSTANT getTYPE() {
		return TYPE;
	}

	public void setTYPE(CONSTANT tYPE) {
		TYPE = tYPE;
	}

	public Grid<DWCodesModelData> getGrid() {
		return grid;
	}

	public Integer getWIDTH() {
		return LIST_WIDTH;
	}

	public Integer getHEIGHT() {
		return LIST_HEIGHT;
	}
	
	
	
}