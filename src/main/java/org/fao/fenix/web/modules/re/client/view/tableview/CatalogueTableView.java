package org.fao.fenix.web.modules.re.client.view.tableview;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.tableview.CatalogueTableViewController;
import org.fao.fenix.web.modules.re.client.view.CatalogueSimple;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.Events;

public class CatalogueTableView extends CatalogueSimple {

	public CatalogueTableView(ResourceExplorer resourceExplorer) {
		super(resourceExplorer);
		FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
		List<String> type = new ArrayList<String>();
		type.add(ResourceType.TABLEVIEW);
		fenixSearchParameters.setResourceTypeList(type);
		fenixSearchParameters.setScope(BabelFish.print().scopeNode());
		fenixSearchParameters.setOrderBy("title");
		fenixSearchParameters.setOrderType("ASC");
		resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);
		grid.addListener(Events.CellDoubleClick, CatalogueTableViewController.onDoubleClick(resourceExplorer));
		CatalogueController.fillGrid(resourceExplorer, grid, fenixSearchParameters, resourceExplorer.getCataloguePager(), this);
		grid.setContextMenu(new CatalogueContextMenuTableView(resourceExplorer, this).buildMenu());
	}
	
}