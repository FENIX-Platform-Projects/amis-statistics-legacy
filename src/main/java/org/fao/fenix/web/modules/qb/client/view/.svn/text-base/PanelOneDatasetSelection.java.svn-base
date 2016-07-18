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

package org.fao.fenix.web.modules.qb.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.qb.client.control.PanelOneController;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public class PanelOneDatasetSelection extends LayoutContainer {

	private QueryBuilder queryBuilder;

	public QueryBuilder getQueryBuilder() {
		return queryBuilder;
	}

	public void setQueryBuilder(QueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	private Button selectDatasetButton;
	public TabItem datasetSelectionTab;

	private ContentPanel selectedDatasetGridPanel;

	private VerticalPanel selectDatasetButtonPanel;
	private HorizontalPanel infoPanel;

	//selected dataset grid configuration
	private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
	private CheckBoxSelectionModel<ResourceChildModel> sm = new CheckBoxSelectionModel<ResourceChildModel>();
	private  ListStore<ResourceChildModel> store = new ListStore<ResourceChildModel>();
	private ColumnModel cm;
	private  Grid<ResourceChildModel> grid;


	public Grid<ResourceChildModel> getGrid() {
		return grid;
	}

	public void setGrid(Grid<ResourceChildModel> grid) {
		this.grid = grid;
	}

	public PanelOneDatasetSelection(QueryBuilder queryBuilder) {
		hideToolTip();
		setStyleAttribute("padding", "15px");
		setId("screen-1");

		String introText = "<b>"+BabelFish.print().introTextPanelOne()+"</b>";
		addText(introText);
		setQueryBuilder(queryBuilder);

		//Click to Select Datasets Button
		selectDatasetButtonPanel = new VerticalPanel();
		selectDatasetButtonPanel.setSpacing(15);

		selectDatasetButton = new Button(BabelFish.print().clickToSelectDatasets());
		selectDatasetButton.addSelectionListener(PanelOneController.openResourceExplorerDataset(this.queryBuilder));

		infoPanel = new HorizontalPanel();
		infoPanel.setSpacing(5);

		IconButton infoIcon = new IconButton();
		infoIcon.setStyleName("info");

		infoPanel.add(infoIcon);
		String infoText = BabelFish.print().checkBoxInstruction();
		infoPanel.addText(infoText);


		//Content Panel to hold selected dataset grid
		selectedDatasetGridPanel = new ContentPanel();
		selectDatasetButtonPanel.setBorders(false);
		selectedDatasetGridPanel.setHeading(BabelFish.print().selectedDatasets());
		selectedDatasetGridPanel.setLayout(new FitLayout());

		//configure selected dataset grid
		initializeSelectedDatasetGrid();
	}

	private void initializeSelectedDatasetGrid(){
		configs.add(sm.getColumn());

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader(BabelFish.print().name());
		column.setWidth(200);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("view");
		column.setHeader(BabelFish.print().view());
		column.setWidth(100);
		
		//

	    GridCellRenderer<ResourceChildModel> buttonRenderer = new GridCellRenderer<ResourceChildModel>() {   
	  
	      private boolean init;   
	  
	      public Object render(ResourceChildModel model, String property, ColumnData config,
					int rowIndex, int colIndex, ListStore<ResourceChildModel> store, Grid<ResourceChildModel> grid) {   
	        if (!init) {   
	          init = true;   
	          grid.addListener(Events.ColumnResize, new Listener<GridEvent<ResourceChildModel>>() {   
	  
	            public void handleEvent(GridEvent<ResourceChildModel> be) {   
	              for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {   
	                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null  
	                    && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {   
	                  ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);   
	                }   
	              }   
	            }   
	          });   
	        }   
	  
	        Button b = new Button("Open");   
	        b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);   
	        b.setToolTip("Click to view dataset");   
	        b.addSelectionListener(PanelOneController.openTable(model));
	        
	        return b;   
	      }   
	    };   


		//
		
		
		/*column.setRenderer(new GridCellRenderer<ResourceChildModel>() {

			
			public Object render(ResourceChildModel model, String property, ColumnData config,
					int rowIndex, int colIndex, ListStore<ResourceChildModel> store, Grid<ResourceChildModel> grid) {
				Button button = new Button();
				button.setScale(ButtonScale.SMALL);
				button.setStyleName("datasetIcon");
				button.addSelectionListener(PanelOneController.openTable(model));

				return button;
			}

		});*/

		column.setRenderer(buttonRenderer);   

		configs.add(column);

		cm = new ColumnModel(configs);
		grid = new Grid<ResourceChildModel>(store, cm);
		grid.setSelectionModel(sm);
		grid.setAutoExpandColumn("name");
		grid.setBorders(true);
		grid.addPlugin(sm);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);

		selectDatasetButtonPanel.add(selectDatasetButton);

		add(selectDatasetButtonPanel);

		selectedDatasetGridPanel.add(grid);
		selectedDatasetGridPanel.setVisible(false);
		add(selectedDatasetGridPanel);

		setTitle(queryBuilder.getStepXofN()+" Select Datasets");

		//queryBuilder.showHideNavigationButtons();

	}


	public void addDatasetsToPanel(List<ResourceChildModel> datasets){
		store.add(datasets);
		sm.setSelection(store.getModels()); //set all datasets as selected

		selectDatasetButtonPanel.add(infoPanel);

		selectedDatasetGridPanel.setVisible(true);

		selectedDatasetGridPanel.getLayout().layout();
		selectDatasetButtonPanel.getLayout().layout();
	}



}