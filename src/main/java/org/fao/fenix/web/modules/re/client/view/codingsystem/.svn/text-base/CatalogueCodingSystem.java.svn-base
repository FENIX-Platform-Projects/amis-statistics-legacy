package org.fao.fenix.web.modules.re.client.view.codingsystem;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.codingsystem.CatalogueCodingSystemController;
import org.fao.fenix.web.modules.re.client.view.CatalogueSimple;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.Events;


public class CatalogueCodingSystem extends CatalogueSimple{

	public CatalogueCodingSystem(ResourceExplorer resourceExplorer){
		super(resourceExplorer);

		//preparing the bean to search the resources
		FenixSearchParameters fenixSearchParameters=new FenixSearchParameters();
		List<String> type=new ArrayList<String>();
		type.add(ResourceType.CODINGSYSTEM);
		fenixSearchParameters.setResourceTypeList(type);
		fenixSearchParameters.setScope(BabelFish.print().scopeNode());
		fenixSearchParameters.setOrderBy("title");
		fenixSearchParameters.setOrderType("ASC");

		resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

		 //Add a double click listener on the table
		grid.addListener(Events.CellDoubleClick, CatalogueCodingSystemController.onDoubleClick(resourceExplorer));
		
		// fill the table	
		CatalogueController.fillGrid(resourceExplorer, grid, fenixSearchParameters, resourceExplorer.getCataloguePager(), this);

		// add context menu
		grid.setContextMenu(new CatalogueContextMenuCodingSystem(resourceExplorer, this).buildMenu());		

	}

}
