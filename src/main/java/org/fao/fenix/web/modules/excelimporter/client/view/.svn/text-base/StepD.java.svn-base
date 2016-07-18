package org.fao.fenix.web.modules.excelimporter.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.i18n.client.DateTimeFormat;

public class StepD extends ExcelImporterStepPanel {
	
	private GroupingStore<ResourceChildModel> store;
	
	private Grid<ResourceChildModel> grid;

	public StepD(String suggestion, String width) {
		super(suggestion, width);
		this.getLayoutContainer().add(buildGrid());
	}

	private ContentPanel buildGrid() {

		store = new GroupingStore<ResourceChildModel>();
		store.setMonitorChanges(true);
//		store.add(test());
		store.groupBy("categoryLabel");
		
		final CheckBoxSelectionModel<ResourceChildModel> sm = new CheckBoxSelectionModel<ResourceChildModel>();
		sm.setSelectionMode(SelectionMode.SINGLE);

		ColumnConfig title = new ColumnConfig("name", "Title", 20);
		ColumnConfig source = new ColumnConfig("source", "Source", 20);
		ColumnConfig period = new ColumnConfig("periodTypeCode", "Frequency", 20);
		ColumnConfig dateLastUpdate = new ColumnConfig("dateModified", "Last Update", 20);
		dateLastUpdate.setDateTimeFormat(DateTimeFormat.getFormat("yyyy-MM-dd")); 
		ColumnConfig category = new ColumnConfig("categoryLabel", "Category", 20);

		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(sm.getColumn());
		config.add(title);
		config.add(source);
		config.add(period);
		config.add(dateLastUpdate);
		config.add(category);

		final ColumnModel cm = new ColumnModel(config);

		GroupingView view = new GroupingView();
		view.setShowGroupedColumn(false);
		view.setForceFit(true);
		view.setGroupRenderer(new GridGroupRenderer() {
			public String render(GroupColumnData data) {
				String f = cm.getColumnById(data.field).getHeader();
				String l = data.models.size() == 1 ? "Item" : "Items";
				return f + ": " + data.group + " (" + data.models.size() + " " + l + ")";
			}
		});

		grid = new Grid<ResourceChildModel>(store, cm);
		grid.setView(view);
		grid.setBorders(true);
		grid.setSelectionModel(sm);

		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setCollapsible(false);
		panel.setFrame(false);
		panel.setBodyBorder(false);
		panel.setLayout(new FitLayout());
		panel.add(grid);
		panel.setSize("580px", "220px");

		return panel;
	}

	private List<ResourceChildModel> test() {
		List<ResourceChildModel> l = new ArrayList<ResourceChildModel>();
		ResourceChildModel r = new ResourceChildModel();
		r.setName("Wholesale Prices");
		r.setSource("FAO");
		r.setDateModified("2010-04-30");
		r.setPeriodTypeCode("Monthly");
		r.setCategoryLabel("Economics");
		l.add(r);
		r = new ResourceChildModel();
		r.setName("Rainfall");
		r.setSource("FAO");
		r.setDateModified("2010-04-30");
		r.setPeriodTypeCode("Monthly");
		r.setCategoryLabel("Environment");
		l.add(r);
		r = new ResourceChildModel();
		r.setName("NDVI");
		r.setSource("FAO");
		r.setDateModified("2010-04-30");
		r.setPeriodTypeCode("Monthly");
		r.setCategoryLabel("Environment");
		l.add(r);
		r = new ResourceChildModel();
		r.setName("Solar Radiation");
		r.setSource("FAO");
		r.setDateModified("2010-04-30");
		r.setPeriodTypeCode("Monthly");
		r.setCategoryLabel("Environment");
		l.add(r);
		r = new ResourceChildModel();
		r.setName("Retail Prices");
		r.setSource("FAO");
		r.setDateModified("2010-04-30");
		r.setPeriodTypeCode("Monthly");
		r.setCategoryLabel("Economics");
		l.add(r);
		
		r = new ResourceChildModel();
		r.setName("Retail Prices 2");
		r.setSource("FAO");
		r.setDateModified("2010-04-30");
		r.setPeriodTypeCode("Monthly");
		r.setCategoryLabel("Economics");
		l.add(r);
		l.add(r);
		l.add(r);
		l.add(r);
		
		return l;
	}

	public GroupingStore<ResourceChildModel> getStore() {
		return store;
	}

	public Grid<ResourceChildModel> getGrid() {
		return grid;
	}

}