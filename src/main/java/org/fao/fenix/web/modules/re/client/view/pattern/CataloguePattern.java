package org.fao.fenix.web.modules.re.client.view.pattern;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.pattern.CataloguePatternController;
import org.fao.fenix.web.modules.re.client.view.CatalogueSimple;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.Events;


public class CataloguePattern extends CatalogueSimple{

	public CataloguePattern(ResourceExplorer resourceExplorer){
		
		super(resourceExplorer);

		//preparing the bean to search the resources
		FenixSearchParameters fenixSearchParameters=new FenixSearchParameters();
		List<String> type=new ArrayList<String>();
		type.add(ResourceType.PATTERN);
		fenixSearchParameters.setResourceTypeList(type);

		resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);

		 //Add a double click listener on the table
		grid.addListener(Events.CellDoubleClick, CataloguePatternController.onDoubleClick(grid));
	
		// fill the table
		CatalogueController.fillGrid(resourceExplorer, grid, fenixSearchParameters, resourceExplorer.getCataloguePager(), this);

		// add context menu
		grid.setContextMenu(new CatalogueContextMenuPattern(resourceExplorer, this).buildMenu());		

	}
}
