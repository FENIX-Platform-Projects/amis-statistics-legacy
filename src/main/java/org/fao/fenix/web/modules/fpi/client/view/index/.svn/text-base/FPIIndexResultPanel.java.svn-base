package org.fao.fenix.web.modules.fpi.client.view.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.fpi.client.view.utils.TableHeadersBuilder;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.table.CellRenderer;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.i18n.client.NumberFormat;

public class FPIIndexResultPanel {

	public Table table;

	public Map<String, CellRenderer<TableItem>> columnRenderers;
	
	public String[] columnHeaders;

	public FPIIndexResultPanel() {
		table = new Table();
		columnHeaders = new String[]{"", "", "", "", "", "", ""};
		initiateColumnRenderers();
	}
	
	public void initiateColumnRenderers() {
		columnRenderers = new HashMap<String, CellRenderer<TableItem>>();
		columnRenderers.put(BabelFish.print().date(), TableHeadersBuilder.buildDateTimeCellRenderer("mm/yyyy"));
		columnRenderers.put(BabelFish.print().grainsPriceIndex(), TableHeadersBuilder.buildNumberCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().coarseGrainsPriceIndex(), TableHeadersBuilder.buildNumberCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().cerealsPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().foodPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().oilsPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().meatPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().bovinePriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().ovinePriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().ricePriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().coarseGrainsPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().grainsPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().pigPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().poultryPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().wheatPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().dairyPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().sugarPriceIndex(), TableHeadersBuilder.buildCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().softOilsPriceIndex(), TableHeadersBuilder.buildNumberCellRenderer(NumberFormat.getFormat("0.00")));
		columnRenderers.put(BabelFish.print().edibleOilsPriceIndex(), TableHeadersBuilder.buildNumberCellRenderer(NumberFormat.getFormat("0.00")));
	}

	public Table build() {
		List<TableColumn> columns = new ArrayList<TableColumn>();
		for (int i = 0 ; i < columnHeaders.length ; i++)
			columns.add(buildTableColumn(columnHeaders[i], columnRenderers.get(columnHeaders[i])));
		TableColumnModel cm = new TableColumnModel(columns);
		table = new Table(cm);
		table.setSelectionMode(Style.SelectionMode.MULTI);
		table.setContextMenu(new FPIIndexContextMenu().build(table));
		return table;
	}
	
	public TableColumn buildTableColumn(String columnHeader, CellRenderer<TableItem> renderer) {
		TableColumn column = new TableColumn(columnHeader, 155);
		column.setSortable(true);
		column.setResizable(true);
		column.setMinWidth(200);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setRenderer(renderer);
		return column;
	}

}