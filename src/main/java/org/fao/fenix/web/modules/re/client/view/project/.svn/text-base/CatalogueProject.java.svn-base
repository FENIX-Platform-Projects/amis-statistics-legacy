package org.fao.fenix.web.modules.re.client.view.project;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.project.CatalogueProjectController;
import org.fao.fenix.web.modules.re.client.control.search.CatalogueSearchController;
import org.fao.fenix.web.modules.re.client.view.CatalogueSimple;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.search.ResourceExplorerSearch;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.Events;


public class CatalogueProject extends CatalogueSimple{

	public CatalogueProject(ResourceExplorer resourceExplorer){
		super(resourceExplorer);

		//preparing the bean to search the resources
		FenixSearchParameters fenixSearchParameters=new FenixSearchParameters();
		List<String> type=new ArrayList<String>();
		type.add(ResourceType.PROJECT);
		fenixSearchParameters.setResourceTypeList(type);
		fenixSearchParameters.setScope(BabelFish.print().scopeNode());
		fenixSearchParameters.setOrderBy("title");
		fenixSearchParameters.setOrderType("ASC");

		resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

		 //Add a double click listener on the table
		if (resourceExplorer instanceof ResourceExplorerProject)
			grid.addListener(Events.CellDoubleClick, CatalogueProjectController.onDoubleClick(grid));
		else if (resourceExplorer instanceof ResourceExplorerSearch)
			grid.addListener(Events.CellDoubleClick, CatalogueSearchController.onDoubleClick(resourceExplorer));
	
		// fill the table
		CatalogueController.fillGrid(resourceExplorer, grid, fenixSearchParameters, resourceExplorer.getCataloguePager(), this);

		// add context menu
		grid.setContextMenu(new CatalogueContextMenuProject(resourceExplorer, this).buildMenu());		
	}
}
