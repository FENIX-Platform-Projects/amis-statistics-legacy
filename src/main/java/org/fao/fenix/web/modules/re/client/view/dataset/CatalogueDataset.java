package org.fao.fenix.web.modules.re.client.view.dataset;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.dataset.CatalogueDatasetController;
import org.fao.fenix.web.modules.re.client.view.Catalogue;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;


public class CatalogueDataset extends Catalogue{
	
	private ColumnModel datasetColumnModel; 

	public CatalogueDataset(ResourceExplorer resourceExplorer){
		
		super(resourceExplorer);

		List<ColumnConfig> datasetColConfig = getDatasetColumnConfigurations();
		
        setDatasetColumnModel(datasetColConfig);
		grid = new Grid<ResourceChildModel>(getStore(), getDatasetColumnModel());
		
		setGridProperties();
		
		//preparing the bean to search the resources
		FenixSearchParameters fenixSearchParameters=new FenixSearchParameters();
		List<String> type=new ArrayList<String>();
		type.add(ResourceType.DATASET);
		fenixSearchParameters.setResourceTypeList(type);
		fenixSearchParameters.setScope(BabelFish.print().scopeNode());
		fenixSearchParameters.setOrderBy("title");
		fenixSearchParameters.setOrderType("ASC");

		resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

		 //Add a double click listener on the table
		grid.addListener(Events.CellDoubleClick, CatalogueDatasetController.onDoubleClick(resourceExplorer));
	
		// fill the table
		CatalogueController.fillGrid(resourceExplorer, grid, fenixSearchParameters, resourceExplorer.getCataloguePager(), this);

		// context menu
		// initialize
		grid.setContextMenu(new CatalogueContextMenuDataset(resourceExplorer, this).buildMenu(null));
		//add 
		grid.addListener(Events.ContextMenu, CatalogueDatasetController.setContextMenu(grid, resourceExplorer, this));
		
		//table.setContextMenu(new CatalogueContextMenuDataset(resourceExplorer, this).buildMenu());		

	}
	
public List<ColumnConfig> getDatasetColumnConfigurations(){
	  	
    	List<ColumnConfig> columns = new ArrayList<ColumnConfig>();  

		for (TableColumn tableColumn : DATASETTABLECOLUMNS) {
    		columns.add(buildColumnConfiguration(tableColumn, BabelFish.print().getString(tableColumn.name)));
		}
    	    	
    	return columns;
    }


public ColumnModel getDatasetColumnModel() {
	return datasetColumnModel;
}

public void setDatasetColumnModel(List<ColumnConfig> columns) {
	//List<ColumnConfig> columns = getStandardColumnConfigurations();
	this.datasetColumnModel = new ColumnModel(columns);  	
}
}
