package org.fao.fenix.web.modules.map.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.venn.common.vo.VennGridMD;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class FeatureInfoGrid {
	
	public static ContentPanel buildGrid(List<String> tableHeaders, List<List<String>> tableContents, String width, String height) {
		ListStore<VennGridMD> store = createListStore(tableContents);
		List<ColumnConfig> configs = createColumnModel(tableHeaders);
		ColumnModel cm = new ColumnModel(configs);
		Grid<VennGridMD> grid = createGrid(store, cm);
		ContentPanel cp = createGridPanel(grid, width, height);
		return cp;
	}
	
	private static ListStore<VennGridMD> createListStore(List<List<String>> tableContents) {
		ListStore<VennGridMD> store = new ListStore<VennGridMD>();
		for (List<String> row : tableContents) {
			VennGridMD m = new VennGridMD(row);
			store.add(m);
		}

		return store;
	}
	
	private static List<ColumnConfig> createColumnModel(List<String> tableHeaders) {
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		for(int i=0 ; i < tableHeaders.size(); i++) {
			ColumnConfig column = new ColumnConfig("column" + i, tableHeaders.get(i),170);
			configs.add(column);
		}
		return configs;
		
	}
	
	private static Grid<VennGridMD> createGrid(ListStore<VennGridMD> store, ColumnModel cm) {
		Grid<VennGridMD> grid = new Grid<VennGridMD>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setLoadMask(true);
		grid.setBorders(false);
		grid.setStripeRows(true);
		return grid;
	}
	
	private static ContentPanel createGridPanel(Grid<VennGridMD> grid, String width, String height) {
		ContentPanel cp = new ContentPanel();
		cp.setScrollMode(Scroll.AUTO);
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setSize(width, height);

		cp.add(grid);
		return cp;
	}

}
