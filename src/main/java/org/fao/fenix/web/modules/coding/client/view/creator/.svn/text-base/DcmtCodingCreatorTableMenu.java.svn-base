package org.fao.fenix.web.modules.coding.client.view.creator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchMenu;
import org.fao.fenix.web.modules.coding.client.view.utils.CodingPager;
import org.fao.fenix.web.modules.coding.client.view.utils.TableHeadersBuilder;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.table.CellRenderer;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;





public class DcmtCodingCreatorTableMenu  {
	
	
	private VerticalPanel panel;
	private Button clear;
	private Button cancel;
	CodingSearchMenu codingSearchMenu;
	DcmtCodingCreatorTableMenu dcmtCodingCreatorTableMenu;
	CodingPager codingPager;
	
	protected Table table;

	public String[] columnHeaders;
	public Map<String, CellRenderer<TableItem>> columnRenderers;
	
	TableHeadersBuilder tableHeadersBuilder;
	
	public VerticalPanel build(){
		return buildResultPanel();
	}
	
	public VerticalPanel buildResult() {
		panel = new VerticalPanel();
		panel.setSpacing(10);
		panel.add(buildResultPanel());
	//	panel.add(buildPagerPanel());
	//	panel.add(buildButtonPanel());
		return panel;
	}
	
	private HorizontalPanel buildPagerPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(15);
	//	codingPager = new CodingPager();
	//	panel = codingPager.build(this);
		return panel;
	}
	
	private HorizontalPanel buildButtonPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(15);
		clear = new Button(BabelFish.print().clear());
		cancel = new Button(BabelFish.print().cancel());
		panel.add(clear);
		panel.add(cancel);
		panel.setWidth("450px");
		return panel;
	}
	
	
	public DcmtCodingCreatorTableMenu() {
		table = new Table();
		System.out.println("asde");
		columnHeaders = new String[]{"", ""};
		initiateColumnRenderers();
	}
	
	public void initiateColumnRenderers() {
		columnRenderers = new HashMap<String, CellRenderer<TableItem>>();
		columnRenderers.put("", null);
		columnRenderers.put("", null);
	}
	
	public Table buildTable() {
		List<TableColumn> columns = new ArrayList<TableColumn>();
		for (int i = 0 ; i < columnHeaders.length ; i++)
			columns.add(buildTableColumn(columnHeaders[i], columnRenderers.get(columnHeaders[i])));
		TableColumnModel cm = new TableColumnModel(columns);
		table = new Table(cm);
		return table;
	}

	
	public TableColumn buildTableColumn(String columnHeader, CellRenderer<TableItem> renderer) {
		TableColumn column = new TableColumn(columnHeader, null);
		column.setSortable(true);
		column.setResizable(true);
		//column.setMinWidth(200);
		column.setWidth(130);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setRenderer(renderer);
		return column;
	}


	private VerticalPanel buildResultPanel() {
		table = new Table();
		VerticalPanel vPanel = new VerticalPanel();
		//vPanel.add(table);
		dcmtCodingCreatorTableMenu = new DcmtCodingCreatorTableMenu();
		table = dcmtCodingCreatorTableMenu.buildTable();

		table.setSize("380px", "300px");
		vPanel.add(table);
		return vPanel;
	}

	
	

	
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	
	public CodingPager getCodingPager() {
		return codingPager;
	}

	public String[] getColumnHeaders() {
		return columnHeaders;
	}
	public void setColumnHeaders(String[] columnHeaders) {
		this.columnHeaders = columnHeaders;
	}
}