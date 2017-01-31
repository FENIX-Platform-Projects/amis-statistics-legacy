package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.view.TopDonorModelData;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGridMD;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.AggregationRenderer;
import com.extjs.gxt.ui.client.widget.grid.AggregationRowConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupSummaryView;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.NumberFormat;

public class ADAMTableMaker extends ADAMBoxMaker {
	
	  private static GridCellRenderer<VennGridMD> totalODARenderer;
	  private static final NumberFormat number = NumberFormat.getFormat("0.00");
	  /**  private final static NumberFormat decimal = NumberFormat.getDecimalFormat();
	    private final static NumberCellRenderer<Grid<VennGridMD>> numberRenderer = new NumberCellRenderer<Grid<VennGridMD>>(decimal);
	    private  static GridCellRenderer<VennGridMD> gridNumber = new GridCellRenderer<VennGridMD>() {
	    	public String render(VennGridMD model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<VennGridMD> store,
	    	Grid<VennGridMD> grid) {
	    	return numberRenderer.render(null, property, model.get(property));
	    	}
	    	};**/
	    	
	    	   

	public static ContentPanel buildGrid(ADAMResultVO vo, String width, String height) {
//		System.out.println("TABLEh: " + vo.getTableHeaders());
//		System.out.println("TABLEc: " + vo.getTableContents());
		
		if ( !vo.getGroupTable() ) {
			if(vo.getBaseModelGrid())
				 return buildGridFromModels(vo.getTableHeadersMap(), vo.getBaseModels(), vo.getGroupingTableColumn(), 1, width, height);
			else {
				/*if(vo.addTotalRow()) {
					totalODARenderer = new GridCellRenderer<VennGridMD>() {
					      public String render(VennGridMD model, String property, ColumnData config, int rowIndex, int colIndex,
					          ListStore<VennGridMD> store, Grid<VennGridMD> grid) {

					    	if(model.get(property)!=null){
					        double val = (Double)model.get(property);
					        
					          return "<span>" + number.format(val) + "</span>";
					         // return "<span>" + val + "</span>";
					    	}
					    	else
					    		return "";
					      }
					    };
					    
					return buildNormalGrid(vo.getTableHeaders(), vo.getTableContents(), width, height, vo.getTableColumnWidth(),vo.getHasSourceRow(), true);
				}
				else*/
					return buildNormalGrid(vo.getTableHeaders(), vo.getTableContents(), width, height, vo.getTableColumnWidth(),vo.getHasSourceRow(), false);
				
			}
				
		}	
		else if (vo.getGroupTable()) {
			if(vo.getBaseModelGrid())
				 return buildGroupedGridFromModels(vo.getTableHeadersMap(), vo.getBaseModels(), vo.getGroupingTableColumnId(), 1, width, height);
			else 
				return buildGroupedGrid(vo.getTableHeaders(), vo.getTableContents(), vo.getGroupingTableColumn(), 1, width, height);
		}
			
		
		return null;
	}
	
	public static ContentPanel buildNormalGrid(List<String> tableHeaders, List<List<String>> tableContents, String width, String height, String columnWidth, Boolean hasSourceRow, Boolean addTotalRow) {	
		ListStore<VennGridMD> store = createListStore(tableContents);
		List<ColumnConfig> configs;
		if(columnWidth!=null)
			configs = createColumnModel(tableHeaders, columnWidth, hasSourceRow);	
		else
			configs = createColumnModel(tableHeaders);
		
		ColumnModel cm = new ColumnModel(configs);
		cm.getColumn(0).setStyle("background-color:#D3D3D3;");//config.style += "background-color:#D3D3D3;";
		
		
		//if(addTotalRow)
			//cm = createTotalSummaryRow(configs, cm);
		
		Grid<VennGridMD> grid = createGrid(store, cm);
		
		//grid.getView().getRow(1).setAttribute("backgroundColor", "green");
		
		ContentPanel cp = createGridPanel(grid, width, height);
		return cp;
	}
	
	public static ContentPanel buildGroupedGrid(List<String> tableHeaders, List<List<String>> tableContents, Integer groupingColumnIndex, Integer quantityIndex, String width, String height) {
		GroupingStore<VennGridMD> store = createGroupingStore(tableContents, groupingColumnIndex, quantityIndex);
		GroupSummaryView summary = new GroupSummaryView();  
//		summary.setForceFit(true);  
		summary.setShowGroupedColumn(false);  
		summary.setStartCollapsed(true);
		summary.setSortingEnabled(false);
		store.setGroupOnSort(false);
	
		
		summary.setSortingEnabled(true);
		
		

		List<ColumnConfig> configs = createSummaryColumnModel(tableHeaders, groupingColumnIndex, quantityIndex);
		ColumnModel cm = new ColumnModel(configs);
		Grid<VennGridMD> grid = createGrid(store, cm);
		grid.setBorders(true);  
		grid.setView(summary);  
	    grid.getView().setShowDirtyCells(false);
		ContentPanel cp = createGridPanel(grid, width, height);
	
		System.out.println("buildGroupedGrid buildgrid");
//		store.setSortField("column" + quantityIndex);
		return cp;
	}
	
	public static ContentPanel buildGridFromModels(Map<String, String> tableHeaders, List<BaseModel> models, Integer groupingColumnIndex, Integer quantityIndex, String width, String height) {
		ContentPanel cp = createGridPanel(width, height);
		if(models.size() > 0) {
			cp.removeAll();
			System.out.println("buildGridFromModels buildGrid 1 "+models.size());
			ListStore<BaseModel> store = new ListStore<BaseModel>();
			store.add(models);
			List<ColumnConfig> configs = createColumnModel(tableHeaders);
			ColumnModel cm = new ColumnModel(configs);
			Grid<BaseModel> grid = createBaseModelGrid(store, cm);
			cp.add(grid);
		} else {
			cp.removeAll();
			cp.add(new Html("<b>No data available for the selections made</b>"));
			
			System.out.println("buildGridFromModels buildGrid 2 "+models.size());
		}	
		return cp;
	}
	
	public static ContentPanel buildGroupedGridFromModels(Map<String, String> tableHeaders, List<BaseModel> models, String groupingColumnId, Integer quantityIndex, String width, String height) {
		ContentPanel cp = createGridPanel(width, height);
		if(models.size() > 0) {
			cp.removeAll();
			System.out.println("buildGridFromModels buildGrid 1 "+models.size());
			GroupingStore<BaseModel> store = new GroupingStore<BaseModel>();
			store.add(models);
			store.groupBy(groupingColumnId);
						
			GroupSummaryView summary = new GroupSummaryView();  
			summary.setShowGroupedColumn(false);  
			summary.setStartCollapsed(false);
			//summary.setSortingEnabled(false);
			store.setGroupOnSort(false);
					
			summary.setSortingEnabled(true);
			List<ColumnConfig> configs = createSummaryColumnModel(tableHeaders);
			ColumnModel cm = new ColumnModel(configs);
			
			Grid<BaseModel> grid = createBaseModelGrid(store, cm);
			grid.setView(summary);  
		    grid.getView().setShowDirtyCells(false);
		    cp.add(grid);
		} else {
			cp.removeAll();
			cp.add(new Html("<b>No data available for the selections made</b>"));
			
			System.out.println("buildGroupedGridFromModels buildGrid 2 "+models.size());
		}	
		return cp;
	}
	
	
	
	private static ListStore<VennGridMD> createListStore(List<List<String>> tableContents) {
//		NumberFormat number = NumberFormat.getFormat("0.00");
		ListStore<VennGridMD> store = new ListStore<VennGridMD>();
//		System.out.println("tableContents: " + tableContents);
		for (List<String> row : tableContents) {
//			System.out.println("content: " + row);
			VennGridMD m = new VennGridMD(row);
//			m.addColumnValue("column" + i, value);
			store.add(m);
		}
		
//		System.out.println("store.getCount: " + store.getCount());
		return store;
	}
	
	private static GroupingStore<VennGridMD> createGroupingStore(List<List<String>> tableContents, Integer groupingColumnIndex, Integer quantityIndex) {
		GroupingStore<VennGridMD> store = new GroupingStore<VennGridMD>();
		for (List<String> row : tableContents) {
//			System.out.println("content: " + row);
			VennGridMD m = new VennGridMD(row);
//			m.addColumnValue("column" + i, value);
			store.add(m);
		}
		//System.out.println("store.getCount: " + store.getCount());
		store.groupBy("column" + groupingColumnIndex);
		return store;
	}
	
	private static List<ColumnConfig> createColumnModel(List<String> tableHeaders, String columnWidth, Boolean hasSourceRow) {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		for(int i=0 ; i < tableHeaders.size(); i++) {
			ColumnConfig column = new ColumnConfig();
			column.setId("column" + i);
			column.setHeader(tableHeaders.get(i));
			column.setStyle("font-weight:bold;");
			
			if(i == 0 || tableHeaders.get(i).contains("Totals")) 
				column.setWidth(150);	
			else 
				column.setWidth(Integer.valueOf(columnWidth));
			
			if(hasSourceRow){
				column.setRenderer(new GridCellRenderer<BaseModel>() {
					public String render(BaseModel model, String property, ColumnData config, int rowIndex,
							int colIndex, ListStore<BaseModel> store, Grid<BaseModel> grid) {  

						String cellProperty = model.get(property, "");

						if(colIndex == 0 || rowIndex == 0) {
							if(colIndex == 0)
								config.style += "background-color: #D3D3D3;text-align:center;font-weight:bold; ";
							if(rowIndex == 0)
								config.style += "background-color: #ADD8E6;text-align:center;font-weight:bold; "; //light blue
						}
							
						else
							config.style += "background-color: #FFFFFF;text-align:left;font-weight:normal; ";
						
						
						
						


						return cellProperty;
					}
				}	
				);
			}	
		/*	if(highlightedTableColumns!=null && !highlightedTableColumns.isEmpty()){
				for(String highlightColumn: highlightedTableColumns){
					if(tableHeaders.get(i).contains(highlightColumn))
						column.setStyle("background-color:#99BBE8;");
				}
			}*/
			
			configs.add(column);
		}
		return configs;
		
	}
	
	private static List<ColumnConfig> createColumnModel(List<String> tableHeaders) {
		
	
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		for(int i=0 ; i < tableHeaders.size(); i++) {
			if(tableHeaders.get(i).contains("USD Mil")) {
				ColumnConfig column = new ColumnConfig("column" + i,tableHeaders.get(i),110);
				column.setRenderer(totalODARenderer);
				configs.add(column);
			} else {
				ColumnConfig column = new ColumnConfig("column" + i, tableHeaders.get(i),100);
				configs.add(column);
			}
		}
		return configs;
		
	}
	
	private static List<ColumnConfig> createColumnModel(Map<String, String> tableHeaders) {
		 System.out.println("createColumnModel tableHeaders = "+tableHeaders+"");
		 
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
        for(String key: tableHeaders.keySet()) {
        	 System.out.println("key = '"+key+"'");
        	if(key.equals("faoSector")) {
        		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),200);
        		configs.add(column);
        	} else if(key.equals("comparativeAdvantage")) {
            		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),100);
            		column.setRenderer(new GridCellRenderer<BaseModel>() {
            				      public String render(BaseModel model, String property, ColumnData config, int rowIndex,
            				          int colIndex, ListStore<BaseModel> store, Grid<BaseModel> grid) {  
            				    	  String val = (String) model.get(property);

            				    	 System.out.println("VAL = '"+val+"'");
            				    	 
            				    	  if(val.equals("Yes")) {
            				    		config.style += "background-color: #D3D3D3; ";
            				    		 config.style +=  "color: #1F9901;font-weight:bold;";
            				    	  } else if(val.equals("No")){
            				    		config.style += "background-color: #D3D3D3; ";
            				    		 config.style +=  "color: #1D4589;font-weight:normal;";
            				    	  }else {
            				    		  config.style += "background-color: #FFFFFF; ";
            				    		  config.style +=  "color: #1D4589;font-weight:normal;";
            				    	  }
            				    	   //config.style += "background-color: #D3D3D3; color: #960018; font-weight: bold;";
            				        
            				        return model.get(property);
            				      }
            				    }	
            		);
            		configs.add(column);
            	} 
        	else{
        		ColumnConfig column = new ColumnConfig(key, tableHeaders.get(key),120);
        		configs.add(column);
        	}	
			
		}
		return configs;
		
	}
	
	private static List<ColumnConfig> createSummaryColumnModel(Map<String, String> tableHeaders) {
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	
        for(String key: tableHeaders.keySet()) {
        	if(key.equals("faoSector")) {
        		SummaryColumnConfig column = new SummaryColumnConfig(key, tableHeaders.get(key),200);
        		configs.add(column);
        	} else if(key.equals("comparativeAdvantage")) {
        		    SummaryColumnConfig column = new SummaryColumnConfig(key, tableHeaders.get(key),100);
            		column.setRenderer(new GridCellRenderer<BaseModel>() {
            				      public String render(BaseModel model, String property, ColumnData config, int rowIndex,
            				          int colIndex, ListStore<BaseModel> store, Grid<BaseModel> grid) {  
            				    	  String val = (String) model.get(property);

            				    	  config.style += "background-color: #D3D3D3; ";
            				    	  
            				    	  if(val.equals("Yes"))
            				    		 config.style +=  "color: #1F9901;font-weight:bold;";
            				    	  else
            				    		 config.style +=  "color: #1D4589;font-weight:normal;";
            				    	  
            				         //config.style += "background-color: #D3D3D3; color: #960018; font-weight: bold;";
            				        
            				        return model.get(property);
            				      }
            				    }	
            		);
            		configs.add(column);
            	} 
        	else{
        		SummaryColumnConfig column = new SummaryColumnConfig(key, tableHeaders.get(key),120);
        		configs.add(column);
        	}	
			
		}
		return configs;
		
	}
	
	private static List<ColumnConfig> createSummaryColumnModel(List<String> tableHeaders, Integer groupingIndex, Integer quantityIndex) {
		
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		for(int i=0 ; i < tableHeaders.size(); i++) {
			
//			if ( i == quantityIndex ) {
//				SummaryColumnConfig<Double> column = new SummaryColumnConfig<Double>("column" + i, tableHeaders.get(i), 100);
//				configs.add(column);
//			}
//			else {
				SummaryColumnConfig column = new SummaryColumnConfig("column" + i, tableHeaders.get(i),100);
				configs.add(column);
//			}
			
		}
		return configs;
		
	}
	
	private static ContentPanel createGridPanel(Grid<VennGridMD> grid, String width, String height) {
		ContentPanel cp = new ContentPanel();
		cp.setScrollMode(Scroll.AUTO);
		cp.setBodyBorder(true);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
//		cp.setSize(width, height);
		if ( width != null )
			cp.setWidth(width);		
		if ( height != null )
			cp.setHeight(height);
		
		if ( width == null && height == null) {
			cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH), 400);  
		}
		
		cp.add(grid);
		return cp;
	}
	
	
	private static ContentPanel createGridPanel(String width, String height) {
		ContentPanel cp = new ContentPanel();
		cp.setScrollMode(Scroll.AUTO);
		cp.setBodyBorder(true);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
//		cp.setSize(width, height);
		if ( width != null )
			cp.setWidth(width);		
		if ( height != null )
			cp.setHeight(height);
		
		if ( width == null && height == null) {
			cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH), 400);  
		}
		
		return cp;
	}
	
	private static Grid<VennGridMD> createGrid(ListStore<VennGridMD> store, ColumnModel cm) {
		Grid<VennGridMD> grid = new Grid<VennGridMD>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
//		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setBorders(false);
		grid.setStripeRows(true);
		
		
		
		//grid.getView().getRow(1).getStyle().setProperty("backgroundColor", "green");
		return grid;
	}
	
	private static Grid<BaseModel> createBaseModelGrid(ListStore<BaseModel> store, ColumnModel cm) {
		Grid<BaseModel> grid = new Grid<BaseModel>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
//		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setBorders(false);
		grid.setStripeRows(true);
		return grid;
	}
	
	
	
	private static GroupingStore<VennGridMD> createFavouritePurposesGroupingStore(List<List<String>> tableContents) {
		GroupingStore<VennGridMD> store = new GroupingStore<VennGridMD>();
		for (List<String> row : tableContents) {
			VennGridMD m = new VennGridMD();
//			m.setSector(row.get(0));
//			try {
//				m.setBudget(Double.valueOf(row.get(1)));
//			} catch (NumberFormatException e) {
//				System.out.println(row.get(1) + " is NOT a number");
//			}
//			m.setDonor(row.get(2));
//			store.add(m);
		}
		store.groupBy("sector");
		return store;
	}
	
	@SuppressWarnings("unchecked")
	private static ColumnModel createGroupingColumnModel() {
		SummaryColumnConfig sector = new SummaryColumnConfig("sector", "<b>Sector</b>", 20);
		SummaryColumnConfig channel = new SummaryColumnConfig("channel", "<b>Channel</b>", 20);
		SummaryColumnConfig recipient = new SummaryColumnConfig("recipient", "<b>Recipient</b>", 20);
		SummaryColumnConfig<Double> budget = new SummaryColumnConfig<Double>("budget", "<b>Budget</b>", 20);
		budget.setSummaryFormat(NumberFormat.getDecimalFormat());
		budget.setSummaryType(new SummaryType<Double>() {
			public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
				if (v == null) {
					v = 0d;
				}
				TopDonorModelData task = (TopDonorModelData) m;
				return ((Double) v).doubleValue() + (task.getBudget());
			}

		});
		budget.setAlignment(HorizontalAlignment.RIGHT);
		budget.setRenderer(new GridCellRenderer<TopDonorModelData>() {
			public String render(TopDonorModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<TopDonorModelData> store, Grid<TopDonorModelData> grid) {
				TopDonorModelData task = (TopDonorModelData) model;
				return NumberFormat.getDecimalFormat().format(task.getBudget());
			}
		});
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(sector);
		config.add(budget);
		config.add(channel);
		config.add(recipient);
		ColumnModel cm = new ColumnModel(config);
		return cm;
	}
	
	@SuppressWarnings("unchecked")
	private static ColumnModel createFavouritePurposesGroupingColumnModel() {
		SummaryColumnConfig purpose = new SummaryColumnConfig("sector", "<b>Purpose</b>", 20);
		SummaryColumnConfig donor = new SummaryColumnConfig("donor", "<b>Donor</b>", 20);
		SummaryColumnConfig<Double> budget = new SummaryColumnConfig<Double>("budget", "<b>Budget</b>", 20);
		budget.setSummaryFormat(NumberFormat.getDecimalFormat());
		budget.setSummaryType(new SummaryType<Double>() {
			public Double render(Object v, ModelData m, String field, Map<String, Object> data) {
				if (v == null) {
					v = 0d;
				}
				TopDonorModelData task = (TopDonorModelData) m;
				return ((Double) v).doubleValue() + (task.getBudget());
			}

		});
		budget.setAlignment(HorizontalAlignment.RIGHT);
		budget.setRenderer(new GridCellRenderer<TopDonorModelData>() {
			public String render(TopDonorModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<TopDonorModelData> store, Grid<TopDonorModelData> grid) {
				TopDonorModelData task = (TopDonorModelData) model;
				return NumberFormat.getDecimalFormat().format(task.getBudget());
			}
		});
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(purpose);
		config.add(budget);
		config.add(donor);
		ColumnModel cm = new ColumnModel(config);
		return cm;
	}

	private static Grid<VennGridMD> createGroupingGrid(GroupingStore<VennGridMD> store, ColumnModel cm, boolean startCollapsed) {
		GroupSummaryView summary = new GroupSummaryView();
		summary.setForceFit(true);
		summary.setShowGroupedColumn(false);
		summary.setStartCollapsed(startCollapsed);
		Grid<VennGridMD> grid = new Grid<VennGridMD>(store, cm);
		grid.setBorders(true);
		grid.setView(summary);
		grid.getView().setShowDirtyCells(false);
		return grid;
	}
	
	public static ContentPanel buildTopSectorsDonorViewGrid(List<List<String>> tableContents, String width, String height, boolean startCollapsed) {
		GroupingStore<VennGridMD> store = createGroupingStore(tableContents, 1,1 );
		final ColumnModel cm = createGroupingColumnModel();
		Grid<VennGridMD> grid = createGroupingGrid(store, cm, startCollapsed);
		ContentPanel cp = createGridPanel(grid, width, height);
		return cp;
	}
	
	public static ContentPanel buildFavouritePurposesQuestionsViewGrid(List<List<String>> tableContents, String width, String height, boolean startCollapsed) {
		GroupingStore<VennGridMD> store = createFavouritePurposesGroupingStore(tableContents);
		final ColumnModel cm = createFavouritePurposesGroupingColumnModel();
		Grid<VennGridMD> grid = createGroupingGrid(store, cm, startCollapsed);
		ContentPanel cp = createGridPanel(grid, width, height);
		return cp;
	}
////////////////////////////
	public static VerticalPanel buildBigTable(ADAMResultVO vo) {
		
		VerticalPanel p = new VerticalPanel();
		VerticalPanel vp = buildTableBoxMenu(vo, ADAMConstants.BIGGER_MENU_GAP, null, false);
		
		if(vo.getTableContents().size() == 0)
			p.add(ADAMEmptyValuesPanel.build("No data available for the current selection"));
		else if(vo.getTableContents().size()== 1 && vo.getTableContents().get(0).equals("(Total)")) // Empty Totals Row
			p.add(ADAMEmptyValuesPanel.build("No data available for the current selection"));
		else {
			p.add(vp);
			p.add(buildBigMatrix(vo));
		}	
		
		//p.add(buildGrid(vo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT));
		//p.add(hp);
		
		return p;
	}
	
	private static VerticalPanel buildTableBoxMenu(ADAMResultVO vo, String labelWidth, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) { 
		VerticalPanel vPanel = new VerticalPanel();
		System.out.println("------ buildTableBoxMenu = "+ labelWidth + " | title = "+vo.getTitle());
		vPanel.add(buildBoxHeader(vo, labelWidth, objectSizeListener, isSmall));
		vPanel.add(buildTableIconsToolbar(vo));
		return vPanel;
	}
	
	private static HorizontalPanel buildTableIconsToolbar(ADAMResultVO vo) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleAttribute("padding-top", "3px");
		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
		toolBar.setBorders(false);
		panel.add(toolBar);
		
		Button exportExcel = createToolbarButton("Download Data");
		exportExcel.setIconStyle("exportDataIcon");
		exportExcel.addSelectionListener(ADAMController.exportExcelTableButton(vo, true));
		
		toolBar.add(exportExcel);
		
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			toolBar.add(new SeparatorToolItem());  
			
			if(vo.getOutputType().equals(ADAMBoxContent.VIEW_MATRIX.name()))
				toolBar.add(addReadMoreButton(vo.getDescription(), "OECD-DAC Implementing Agencies", 545, 600, true)); 
		}
		
		
		return panel;
	}
	
	///////////////////////////////////
	public static VerticalPanel buildSmallTable(ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean addRemoveTableIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) {
		VerticalPanel p = new VerticalPanel();
		p.setHeight(ADAMConstants.SMALL_BOX_HEIGHT);
		p.setWidth(ADAMConstants.SMALL_BOX_WIDTH);
		
		HorizontalPanel h1 = ADAMBoxMaker.buildTableBoxMenu(vo, ADAMConstants.SMALL_MENU_GAP_WIDTH, position, color,  objectSizeListener, true, true, addRemoveTableIcon, removeListener, removeLabel);
		HorizontalPanel h2 =  new HorizontalPanel();
		ContentPanel cp = null;
		cp = buildGrid(vo, ADAMConstants.SMALL_TABLE_CHART_WIDTH, ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		h2.add(cp);
		p.add(h1);
//		h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), true));
		p.add(h2);
		return p;
	}
	
	public static VerticalPanel buildBigTable(ADAMResultVO vo, String position, String color) {
		VerticalPanel p = new VerticalPanel();
		p.setHeight(ADAMConstants.BIG_BOX_HEIGHT);
		p.setWidth(ADAMConstants.BIG_BOX_WIDTH);
		
		HorizontalPanel h1 = ADAMBoxMaker.buildTableBoxMenu(vo, ADAMConstants.SMALL_MENU_GAP_WIDTH, position, color,  null, true, true, false, null, null);
		HorizontalPanel h2 =  new HorizontalPanel();
		ContentPanel cp = null;
		cp = buildGrid(vo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		h2.add(cp);
		p.add(h1);
//		h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), true));
		p.add(h2);
		return p;
	}
	
	
	
	public static VerticalPanel buildSmallQuestionTable(ADAMResultVO vo) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		HorizontalPanel h2 =  new HorizontalPanel();
		ContentPanel cp = null;
		cp = buildGrid(vo, ADAMConstants.QUESTION_TABLE_WIDTH, ADAMConstants.QUESTION_TABLE_HEIGHT);
		h2.add(cp);
//		h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), true));
		p.add(h2);
		return p;
	}

//	public static VerticalPanel buildSmallTopSectorsDonorViewTable(ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener) {
//		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
//		HorizontalPanel h1 =  ADAMBoxMaker.buildTableBoxMenu(vo, ADAMConstants.SMALL_LABEL_WIDTH, objectSizeListener, true);
//		HorizontalPanel h2 = new HorizontalPanel();
//		ContentPanel cp = buildTopSectorsDonorViewGrid(vo.getTableContents(), ADAMConstants.SMALL_WIDTH, ADAMConstants.SMALL_HEIGHT, true);
//		h2.add(cp);
//		p.add(h1);
//		p.add(h2);
//		return p;
//	}
	
	public static VerticalPanel buildBigTable(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
		VerticalPanel p = new VerticalPanel();
		p.setHeight(ADAMConstants.BIG_BOX_HEIGHT);
		p.setWidth(ADAMConstants.BIG_BOX_WIDTH);
		
		p.setSpacing(5);
//		HorizontalPanel h1 = buildChartBoxMenu(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, objectSizeListener, false);

		HorizontalPanel h1 = buildTableBoxMenu(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, objectSizeListener, false, true, false, null, null);
		HorizontalPanel h2 = new HorizontalPanel();
		ContentPanel cp = buildGrid(vo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT);
		h2.add(cp);
		p.add(h1);
		if ( showLinks ) {
			h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), false));
		}
		p.add(h2);
		return p;
	}
	
	public static ContentPanel buildBigTableExpandedMenu(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
		ContentPanel cp = new ContentPanel();
		cp.setCollapsible(false);  
		cp.setAnimCollapse(false);
		cp.setFrame(true); 
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setHeaderVisible(false);
		cp.setBodyBorder(false);
		
		HorizontalPanel h1 = buildTableBoxMenu(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, objectSizeListener, false, false, false, null, null);
		cp.add(h1);
		
		return cp;
	}
	
	
	
	public static ContentPanel buildBigTableExpandedGrid(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
		ContentPanel cp = new ContentPanel();
		cp.setCollapsible(false);  
		cp.setAnimCollapse(false);
		cp.setFrame(true); 
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH) - 15, 400);  
		cp.setHeaderVisible(false);
		cp.setBodyBorder(false);

		ContentPanel grid = buildGrid(vo, null, null);
	
		cp.add(grid);
		cp.layout();
		return cp;
	}
	
/*	public static ContentPanel buildBigVennMatrix(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
		ContentPanel cp = new ContentPanel();
		cp.setCollapsible(false);  
		cp.setAnimCollapse(false);
		cp.setFrame(true); 
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH) - 15, 400);  
		cp.setHeaderVisible(false);
		cp.setBodyBorder(false);

		ContentPanel grid = buildGrid(vo, null, null);
		
		
		cp.add(grid);
		cp.layout();
		return cp;
	}*/
	
	public static HorizontalPanel buildBigMatrix(ADAMResultVO vo) {
		HorizontalPanel hp = new HorizontalPanel();
		hp.setHorizontalAlign(HorizontalAlignment.LEFT);
		//hp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH) - 15, 400);  
		hp.setSpacing(5);
		ContentPanel grid = buildGrid(vo, null, null);
		
		hp.add(grid);
		hp.layout();

		return hp;
	}
	
	
	
	private static ColumnModel createTotalSummaryRow(List<ColumnConfig> configs, ColumnModel cm){

		final AggregationRowConfig<VennGridMD> sum = new AggregationRowConfig<VennGridMD>();
		sum.setHtml("column0", "Total");
	    
		 sum.setSummaryType("column1", SummaryType.SUM);
		 sum.setRenderer("column1", new AggregationRenderer<VennGridMD>() {
			      public Object render(Number value, int colIndex, Grid<VennGridMD> grid, ListStore<VennGridMD> store) {
			    	  return "<font color='blue'>"+number.format(value.doubleValue())+"</font>"; 
			    	 
				      }
				    });

		  	/*sum.setRenderer("column1", new AggregationRenderer<VennGridMD>() {
			      public Object render(Number value, int colIndex, Grid<VennGridMD> grid, ListStore<VennGridMD> store) {
			    	  return number.format(value); 			    	 
				      }
				    });*/
		  	
	   /** for (int i = 1; i < configs.size(); i++) {
		  ColumnConfig column = configs.get(i);
		  
		  	sum.setSummaryType(column.getId(), SummaryType.SUM);
		  	sum.setRenderer(column.getId(), new AggregationRenderer<VennGridMD>() {
			      public Object render(Number value, int colIndex, Grid<VennGridMD> grid, ListStore<VennGridMD> store) {
			    	  return "<font color='blue'>"+number.format(value.doubleValue())+"</font>"; 
			    	 
				      }
				    });
		}**/
		
		cm.addAggregationRow(sum);

		return cm;
	}
	
//	public static VerticalPanel buildBigFavouritePurposesTable(ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener) {
//		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
//		HorizontalPanel h1 = buildTableBoxMenu(vo, "825px", objectSizeListener, false);
//		HorizontalPanel h2 = new HorizontalPanel();
//		ContentPanel cp = buildFavouritePurposesQuestionsViewGrid(vo.getTableContents(), ADAMConstants.BIG_WIDTH, ADAMConstants.BIG_HEIGHT, false);
//		h2.add(cp);
//		p.add(h1);
//		p.add(h2);
//		return p;
//	}
//
//	public static VerticalPanel buildBigTopSectorsDonorViewTable(ADAMResultVO vo, SelectionListener<ButtonEvent> objectSizeListener) {
//		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
//		HorizontalPanel h1 = buildTableBoxMenu(vo, "825px", objectSizeListener, false);
//		HorizontalPanel h2 = new HorizontalPanel();
//		ContentPanel cp = buildTopSectorsDonorViewGrid(vo.getTableContents(), ADAMConstants.BIG_WIDTH, ADAMConstants.BIG_HEIGHT, false);
//		h2.add(cp);
//		p.add(h1);
//		p.add(h2);
//		return p;
//	}
	
	
//	private List<ColumnConfig> createSummaryColumnConfigurations(Integer gridDimensionSize, Integer groupByIndex) {
//		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
//		Boolean summary = false;
//		for(int i=0 ; i < gridDimensionSize; i++) {
//			if ( i == groupByIndex )
//				configs.add(intersectionSummary(i, quantityIndex));
//			else if ( i == quantityIndex)
//				configs.add(quantitySummary(i));
//			else if ( i != groupByIndex && i != quantityIndex && !summary ) {
//				summary = true;
//				configs.add(buildCountColumn(i));
//			}
//			else
//				configs.add(buildNormalColumn(i));
//
//		}
//		return configs;
//	}
	
	
//	System.out.println("bulding donorSummary: " + "column" + i);
//	SummaryColumnConfig<Integer> column = new SummaryColumnConfig<Integer>("column" + i, " - ", 100);  
//	column.setSummaryType(SummaryType.COUNT);
//	column.setSummaryType(new SummaryType<Integer>() {  
//	 public Integer render(Object v, ModelData m, String field, Map<String, Object> data) {
//	      Integer count = (Integer) data.get(field + "count");
//	      if (count == null) {
//	        count = new Integer(0);
//	      }
//	      int i = count.intValue();
//	      i++;
//	      data.put(field + "count", i);
//	    
//	      
//	      Integer countFiltered = (Integer) data.get("countFiltered");
//	      if (countFiltered == null) {
//	    	  countFiltered = new Integer(0);
//	      }
//	      int j = countFiltered.intValue();
//	      if (v == null) {  
//	          v = 0d;  
//	        }  
//	        VennGridMD vennMD = (VennGridMD) m;  
//	        if ( vennMD.getColumnQuantityValue(quantityIndex) != null)  	
//	        	j++;
//	      data.put("countFiltered", j);
//	      
//	      return i;
//	    }
//	 });
//	column.setSummaryRenderer(new SummaryRenderer() { 
//		public String render(Number value, Map<String, Number> data) {
//			 return value.intValue() > 1 ? "(" + value.intValue() + " Projects)" : "(1 Project)";  
//			}  
//	    });  
//	return column;
//}
	
//	private List<ColumnConfig> createSummaryColumnConfigurations(Integer gridDimensionSize, Integer groupByIndex, Integer quantityIndex) {
//		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
//		Boolean summary = false;
//		for(int i=0 ; i < gridDimensionSize; i++) {
//			if ( i == groupByIndex )
//				configs.add(intersectionSummary(i, quantityIndex));
//			else if ( i == quantityIndex)
//				configs.add(quantitySummary(i));
//			else if ( i != groupByIndex && i != quantityIndex && !summary ) {
//				summary = true;
//				configs.add(buildCountColumn(i));
//			}
//			else
//				configs.add(buildNormalColumn(i));
//
//		}
//		return configs;
//	}

}