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
package org.fao.fenix.web.modules.fsatmis.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.fsatmis.common.vo.FSATMISModelData;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public class FSATMISTablePanel {

	private Grid<FSATMISModelData> grid;
	
	private ContentPanel panel;
	
	private List<ColumnConfig> columnConfig = new ArrayList<ColumnConfig>();
	
	private ListStore<FSATMISModelData> store = new ListStore<FSATMISModelData>();
	
	private PagingToolBar pagingToolBar = new PagingToolBar(20);  //set page size here
	
	protected PagingLoader<PagingLoadResult<FSATMISModelData>> loader;
	
	private ColumnModel columnModel;
	
	public FSATMISTablePanel() {
		panel = new ContentPanel();		
		panel.setBodyBorder(true);   
		panel.setLayout(new FitLayout());  
		panel.setSize(795, 685);   
	}

	
	public ContentPanel build(FSATMISTabPanel fsatmisTabPanel) {
		columnConfig = buildColumnConfig();
		columnModel = new ColumnModel(columnConfig);
		
		grid = new Grid<FSATMISModelData>(store , columnModel);
		grid.setBorders(true);
		
		panel.add(grid);
		panel.setBottomComponent(pagingToolBar);
		return panel;
	}

	
	
	public List<ColumnConfig> buildColumnConfig() {
		List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
		columns.add(new ColumnConfig("projectName", "Project Name", 75));
		columns.add(new ColumnConfig("projectSymbol", "Project Symbol", 75));
		columns.add(new ColumnConfig("country", "Country", 75));
		columns.add(new ColumnConfig("province", "Province", 75));
		columns.add(new ColumnConfig("location", "Location", 75));
		columns.add(new ColumnConfig("donorType", "Donor Type", 75));
		columns.add(new ColumnConfig("donor", "Donor", 75));
		columns.add(new ColumnConfig("projectStatus", "Project Status", 75));
		columns.add(new ColumnConfig("fromDate", "From Date", 75));
		columns.add(new ColumnConfig("toDate", "To Date", 75));
		columns.add(new ColumnConfig("budget", "Budget", 75));
		columns.add(new ColumnConfig("currency", "Currency", 75));
		columns.add(new ColumnConfig("prioritySector", "Priority Sector", 75));
		columns.add(new ColumnConfig("priorityDescription", "Priority Description", 75));
		columns.add(new ColumnConfig("budgetType", "Budget Type", 75));
		columns.add(new ColumnConfig("foodSecurity", "Food Security", 75));
		columns.add(new ColumnConfig("implementingAgencyType", "Implementing Agency Type", 75));
		columns.add(new ColumnConfig("implementingAgency", "Implementing Agency", 75));
		columns.add(new ColumnConfig("notes", "Notes", 75));
		return columns;
	}
	
	
	public ListStore<FSATMISModelData> getListStore() {
		return store;
	}
	
	public void setListStore(ListStore<FSATMISModelData> store) {
		this.store = store;
	}
	
	public ColumnModel getColumnModel() {
		return columnModel;
	}

	public Grid<FSATMISModelData> getGrid() {
		return grid;
	}
	
	public ContentPanel getPanel() {
		return panel;
	}
	
	public PagingToolBar getPagingToolBar() {
		return pagingToolBar;
	}
	
	public void setPagingToolBar(PagingToolBar pagingToolBar) {
		this.pagingToolBar = pagingToolBar;
	}

}