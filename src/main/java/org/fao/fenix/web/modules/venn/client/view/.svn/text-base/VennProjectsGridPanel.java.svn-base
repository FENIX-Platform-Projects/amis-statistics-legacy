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
package org.fao.fenix.web.modules.venn.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.FormatValues;
import org.fao.fenix.web.modules.venn.common.vo.VennGridMD;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GroupSummaryView;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.SummaryRenderer;
import com.extjs.gxt.ui.client.widget.grid.SummaryType;

public class VennProjectsGridPanel {

	private ContentPanel panel;
	
	private ListStore<VennGridMD> store;
	
	private GroupingStore<VennGridMD> storeSummary;
	
	private Grid<VennGridMD> grid;
	
	public VennProjectsGridPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setScrollMode(Scroll.AUTO);
		panel.setAutoHeight(true);
		panel.setAutoWidth(true);
	}
	
	public Grid<VennGridMD> build(String width, Integer gridDimensionSize, Integer gridRows) {
		List<ColumnConfig> configs = createColumnConfigurations(gridDimensionSize);
		store = new ListStore<VennGridMD>();
		ColumnModel cm = new ColumnModel(configs);
		
		grid = new Grid<VennGridMD>(store, cm);
		grid.setStyleAttribute("borderTop", "none");   
		grid.setBorders(true);
		grid.setAutoHeight(true);
		grid.setAutoWidth(true);
		
//		grid.setHeight("70px");
//		String gridWidth = getGridWidth(width);
		grid.setWidth(getGridWidth(width));
		panel.setWidth(getGridWidth(width));
//		panel.add(grid);
		
		
		setPanelHeight(gridRows);
//		setPanelWidth(gridDimensionSize);
		
//		return panel;
		return grid;
	}
	
	public Grid<VennGridMD> buildSummaryGrid(String width, Integer gridDimensionSize, Integer gridRows, Integer groupByIndex, Integer quantiIdxColumn) {
		List<ColumnConfig> configs = createSummaryColumnConfigurations(gridDimensionSize, groupByIndex, quantiIdxColumn);
		System.out.println(" configs.size(): " + configs.size());
		storeSummary = new GroupingStore<VennGridMD>();
		ColumnModel cm = new ColumnModel(configs);

		
		grid = new Grid<VennGridMD>(storeSummary, cm);
		grid.setStyleAttribute("borderTop", "none");   
		grid.setBorders(true);
		grid.setAutoHeight(true);
		grid.setAutoWidth(true);
		
//		grid.setHeight("70px");
//		String gridWidth = getGridWidth(width);
		grid.setWidth(getGridWidth(width));
		panel.setWidth(getGridWidth(width));
		panel.add(grid);
		
		GroupSummaryView summary = new GroupSummaryView();  
		summary.setForceFit(true);  
		summary.setShowGroupedColumn(false);  
		summary.setStartCollapsed(true);
		summary.setSortingEnabled(false);
		
		
		
		storeSummary.setSortField("column1");
		
		
		grid.setBorders(true);  
		grid.setView(summary);  
		grid.getView().setShowDirtyCells(false);
//		grid.setHeight(600);

//		panel.setAutoHeight(true);
//		panel.setAutoWidth(true);
//		setPanelHeight(800);
		setPanelHeight(gridRows);
//		setPanelWidth(gridDimensionSize);
		
//		return panel;
		return grid;
	}
	
	
	private void setPanelHeight(Integer gridRows){
		panel.setHeight(gridRows * 10);
	}
	
	private void setPanelWidth(Integer gridDimensionSize){
//		panel.setHeight(gridDimensionSize * 10);
		panel.setWidth(500);
	}
	
	private String getGridWidth(String width) {
		int gridWidth = Integer.parseInt(width.substring(0, width.length() - 2));
		return String.valueOf(gridWidth - 20) + "px";
	}
	
	private List<ColumnConfig> createColumnConfigurations(Integer gridDimensionSize) {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		for(int i=0 ; i < gridDimensionSize; i++) {
			ColumnConfig column = new ColumnConfig("column" + i, " - ",100);
			configs.add(column);
		}
		return configs;
	}
	
	
	private List<ColumnConfig> createSummaryColumnConfigurations(Integer gridDimensionSize, Integer groupByIndex, Integer quantityIndex) {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		Boolean summary = false;
		for(int i=0 ; i < gridDimensionSize; i++) {
			if ( i == groupByIndex )
				configs.add(intersectionSummary(i, quantityIndex));
			else if ( i == quantityIndex)
				configs.add(quantitySummary(i));
			else if ( i != groupByIndex && i != quantityIndex && !summary ) {
				summary = true;
				configs.add(buildCountColumn(i));
			}
			else
				configs.add(buildNormalColumn(i));

		}
		return configs;
	}
	
	private SummaryColumnConfig buildCountColumn(Integer i) {
		System.out.println("bulding count column: " + "column" + i);
		SummaryColumnConfig column = new SummaryColumnConfig("column" + i, " - ",100);
		column.setSummaryRenderer(new SummaryRenderer() { 
			public String render(Number value, Map<String, Number> data) {
//				System.out.println("data size: " + data.size());
//				System.out.println("data: " + data);
//				System.out.println("number of projects: " + data.get("column0"));
//				System.out.println("number of filtered projects: " + data.get("countFiltered"));
				 return data.get("column0").intValue() > 1 ? "<b>(" + data.get("column0").intValue() + " Projects)</b>" : "<b>(1 Project)</b>";  
				}  
		    });  
		return column;
	}
	
	private SummaryColumnConfig buildNormalColumn(Integer i) {
		System.out.println("bulding normal column: " + "column" + i);
		SummaryColumnConfig column = new SummaryColumnConfig("column" + i, " - ",100);
		return column;
	}
	
	private SummaryColumnConfig<Double> quantitySummary(Integer i) {
		System.out.println("bulding quantitySummary: " + "column" + i);
		SummaryColumnConfig<Double> column = new SummaryColumnConfig<Double>("column" + i, " - ", 100);  
		column.setSummaryType(SummaryType.SUM);
		column.setSummaryRenderer(new SummaryRenderer() {  
			public String render(Number value, Map<String, Number> data) {
//				System.out.println("data size: " + data.size());
//				System.out.println("data: " + data);
//				System.out.println("number of projects: " + data.get("column0"));
//				System.out.println("number of filtered projects: " + data.get("countFiltered"));
				
				try {
					Integer projectsNumber = (Integer) data.get("countFiltered");
					
					String total = FormatValues.formatValue(value.doubleValue(), 2);
					
					String avg = FormatValues.formatValue(value.doubleValue() / projectsNumber, 2);
					
					return value.doubleValue() > 0 ? "<b> Total Budget: " + total + " (mn) <br> Average " + avg + " (mn)</b>"   : "";  
				} catch (Exception e) {
					return null;
				}

				}  
		    });  
		return column;
	}
	
	private SummaryColumnConfig<Integer> intersectionSummary(Integer i, final Integer quantityIndex) {
		System.out.println("bulding donorSummary: " + "column" + i);
		SummaryColumnConfig<Integer> column = new SummaryColumnConfig<Integer>("column" + i, " - ", 100);  
		column.setSummaryType(SummaryType.COUNT);
		column.setSummaryType(new SummaryType<Integer>() {  
		 public Integer render(Object v, ModelData m, String field, Map<String, Object> data) {
		      Integer count = (Integer) data.get(field + "count");
		      if (count == null) {
		        count = new Integer(0);
		      }
		      int i = count.intValue();
		      i++;
		      data.put(field + "count", i);
		    
		      
		      Integer countFiltered = (Integer) data.get("countFiltered");
		      if (countFiltered == null) {
		    	  countFiltered = new Integer(0);
		      }
		      int j = countFiltered.intValue();
		      if (v == null) {  
		          v = 0d;  
		        }  
		        VennGridMD vennMD = (VennGridMD) m;  
		        if ( vennMD.getColumnQuantityValue(quantityIndex) != null)  	
		        	j++;
		      data.put("countFiltered", j);
		      
		      return i;
		    }
		 });
		column.setSummaryRenderer(new SummaryRenderer() { 
			public String render(Number value, Map<String, Number> data) {
				 return value.intValue() > 1 ? "(" + value.intValue() + " Projects)" : "(1 Project)";  
				}  
		    });  
		return column;
	}
	
	private Html buildReferenceLayers() {
		Html label = new Html("<div style='font-weight:bold; font-size: 10pt; background-color:#d0dded'>Layers blabla <a href='http://lprapp08.fao.org/fenix-portal/' target='_blank'> GIEWS Workstation</font></a></div>");
//		Html label = new Html("");
		return label;
	}
	

	public ListStore<VennGridMD> getStore() {
		return store;
	}

	public Grid<VennGridMD> getGrid() {
		return grid;
	}

	public GroupingStore<VennGridMD> getStoreSummary() {
		return storeSummary;
	}
	
	

}