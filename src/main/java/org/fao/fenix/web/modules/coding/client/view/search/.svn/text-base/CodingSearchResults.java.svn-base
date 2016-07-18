package org.fao.fenix.web.modules.coding.client.view.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.client.control.search.CodingFindConvertController;
import org.fao.fenix.web.modules.coding.client.view.toolbar.CodingToolbar;
import org.fao.fenix.web.modules.coding.client.view.utils.CodingPager;
import org.fao.fenix.web.modules.coding.client.view.utils.TableHeadersBuilder;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.table.CellRenderer;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;


public class CodingSearchResults extends FenixWindow {
		
	private VerticalPanel panel;
	private Button clear;
	private Button cancel;
	private Button addItems;
	private HTML csLabel;
	private HTML csDescription;
	
	CodingSearchWindow codingSearchWindow;
	CodingSearchResults codingSearchResults;
	CodingToolbar codingToolbar;
	CodingPager codingPager;
	
	protected Table table;

	public String[] columnHeaders;
	public Map<String, CellRenderer<TableItem>> columnRenderers;
	
	
	TableHeadersBuilder tableHeadersBuilder;
	
	public Table build(CodingSearchWindow window, ListStore<DimensionItemModel> listStore, ComboBox<DimensionItemModel> combo){
		setWestProperties();
		setCenterProperties();
		getCenter().add(buildResult(true));
		enhanceAddItems(listStore, combo);
		codingSearchWindow = window;
	
		//addToolbar(toolbar());
		addCenterPartToWindow();
		format();	
		return getTable();
	}
	
	public Table build(CodingSearchWindow window){
		setWestProperties();
		setCenterProperties();
		getCenter().add(buildResult(false));
	
		//addToolbar(toolbar());
		addCenterPartToWindow();
		format();	
		return getTable();
	}
	
	public void build(Long resourceId){
		table = new Table();
		columnHeaders = new String[]{"", "", "", "", ""};
		initiateColumnRenderers();
		setWestProperties();
		setCenterProperties();
		getCenter().add(buildResult(false));
		addToolbar(toolbar());
		addCenterPartToWindow();
		CodingFindConvertController.showCodingSystem(this, resourceId);
		format();	
	}
	
	public VerticalPanel buildResult(Boolean isTableCall) {
		panel = new VerticalPanel();
		panel.setSpacing(10);
		panel.add(buildResultPanel());
		panel.add(buildPagerPanel(isTableCall));
		panel.add(buildDescriptionTextPanel());
	//	panel.add(buildButtonPanel());
		enhance();
		
		return panel;
	}
	
	private HorizontalPanel buildPagerPanel(Boolean isTableCall) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(15);
		codingPager = new CodingPager();
		panel = codingPager.build(this);
		clear = new Button(BabelFish.print().clear());
		cancel = new Button(BabelFish.print().cancel());
		addItems= new Button(BabelFish.print().addItems());
		panel.add(clear);
		panel.add(cancel);
		
		if ( isTableCall)
			panel.add(addItems);
		
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
	
	private void enhance(){
		clear.addSelectionListener(CodingFindConvertController.getClearTableListener(this));
		cancel.addSelectionListener(CodingFindConvertController.getCancelTableListener(this));
	}
	

	
	public CodingSearchResults() {
		table = new Table();
		table.setAutoWidth(true);
		columnHeaders = new String[]{"", "", "", "", ""};
		initiateColumnRenderers();
	}
	
	public void initiateColumnRenderers() {
		columnRenderers = new HashMap<String, CellRenderer<TableItem>>();
		columnRenderers.put("", null);
		columnRenderers.put("", null);
		columnRenderers.put("", null);
		columnRenderers.put("", null);
		columnRenderers.put("", null);
	}
	
//	public Table buildTable() {
//		List<TableColumn> columns = new ArrayList<TableColumn>();
//		for (int i = 0 ; i < columnHeaders.length ; i++)
//			columns.add(buildTableColumn(columnHeaders[i], columnRenderers.get(columnHeaders[i])));
//		TableColumnModel cm = new TableColumnModel(columns);
//		table = new Table(cm);
//		return table;
//	}

	
	public TableColumn buildTableColumn(String columnHeader, CellRenderer<TableItem> renderer) {
		TableColumn column = new TableColumn(columnHeader, null);
		column.setSortable(true);
		column.setResizable(true);
		//column.setMinWidth(200);
		column.setWidth(200);
		column.setAlignment(HorizontalAlignment.LEFT);
		column.setRenderer(renderer);
		return column;
	}


	private VerticalPanel buildResultPanel() {
		
		
		
		
		VerticalPanel vPanel = new VerticalPanel();
		codingSearchResults = new CodingSearchResults();
//		table = codingSearchResults.buildTable();
		
		List<TableColumn> columns = new ArrayList<TableColumn>();
		
		TableColumn column = new TableColumn("", null);
//		column.setWidth(150);
//		column.setMinWidth(150);
//		column.setMaxWidth(150);

		columns.add(column);


		
		
		
		for (int i = 1 ; i < 6 ; i++) {
			column = new TableColumn("", null);
			column.setResizable(true);
//			column.setWidth(270);
//			column.setMinWidth(270);
//			column.setMaxWidth(270);
			
			columns.add(column);
		}
		TableColumnModel cm = new TableColumnModel(columns);
		
		table = new Table(cm);
//		table.setAutoWidth(true);
	
		
		table.addListener(Events.CellClick, fillDescription());
	    
	    
		
	
		
		// la table la setto a false almeno non mostra nulla//
		//table.setVisible(false);	
		table.setSize("510px", "430px");
//		table.setAutoWidth(true);
//		vPanel.setAutoWidth(true);
//		vPanel.setScrollMode(Scroll.AUTOX);
		vPanel.add(table);
//		vPanel.setSize("485px", "465px");
		return vPanel;
	}
	
	
	private ContentPanel buildDescriptionTextPanel() {
		ContentPanel panel = new ContentPanel();
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		csLabel = new HTML();
		csDescription = new HTML();		
		vPanel.add(csLabel);
		vPanel.add(csDescription);
		panel.setSize("510px", "50px");
		panel.setBorders(false);
		panel.setScrollMode(Scroll.AUTO);
		panel.setHeaderVisible(false);
		panel.add(vPanel);
		return panel;
	}
	
	
	
	protected void format() {
		setSize("550px", "600px");
		setAutomaticScrollBar();
		getCenter().setHeaderVisible(false);
		setTitle("<b> DCMT " + BabelFish.print().resultList()+ " </b>");
	}
	
	
	private ToolBar toolbar() {
		codingToolbar = new CodingToolbar(this);	
		ToolBar toolbar = codingToolbar.getToolbar();
		return toolbar; 
	}
	
	
	private  Listener<ComponentEvent> fillDescription(){
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
			public void handleEvent(ComponentEvent ce) { 
				Table t = (Table) ce.getComponent();
				TableItem m = t.getSelectedItem();
		      	if (t.getColumn(0).getText().equals("Code")){	      		
		      		getCsLabel().setHTML("<b>" +BabelFish.print().label() + ": </b>" + m.getValue(4) + "</b>");
		      		String description = (String) m.getValue(3);
		      		if ( !description.isEmpty())
		      			getCsDescription().setHTML("<b>" + BabelFish.print().explonatoryNote()+ ":</b> " + m.getValue(3) );
		      		else 
		      			getCsDescription().setHTML("");
		      	}
		      	else {
		      		getCsLabel().setHTML("<b>" +BabelFish.print().label() + ": </b>" + m.getValue(5) + "</b>");
		      		String description = (String) m.getValue(4);
		      		if ( !description.isEmpty())
		      			getCsDescription().setHTML("<b>" + BabelFish.print().explonatoryNote()  + ": </b> " +  m.getValue(4) );
		      		else 
		      			getCsDescription().setHTML("");
		      	}

		       
			}
		};  
		return t;
	}
	
	private void enhanceAddItems(ListStore<DimensionItemModel> listStore, ComboBox<DimensionItemModel> combo){
		addItems.addSelectionListener(CodingFindConvertController.fillTableComboBox(codingSearchWindow,this, listStore, combo));
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
	public HTML getCsLabel() {
		return csLabel;
	}
	public HTML getCsDescription() {
		return csDescription;
	}
}