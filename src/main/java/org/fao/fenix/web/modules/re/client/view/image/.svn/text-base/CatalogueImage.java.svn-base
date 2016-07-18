package org.fao.fenix.web.modules.re.client.view.image;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.CatalogueController;
import org.fao.fenix.web.modules.re.client.control.image.CatalogueImageController;
import org.fao.fenix.web.modules.re.client.view.CatalogueSimple;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.extjs.gxt.ui.client.event.Events;

public class CatalogueImage extends CatalogueSimple {

	public CatalogueImage(ResourceExplorer resourceExplorer) {
		super(resourceExplorer);
		FenixSearchParameters fenixSearchParameters = new FenixSearchParameters();
		List<String> type = new ArrayList<String>();
		type.add(ResourceType.IMAGE);
		fenixSearchParameters.setResourceTypeList(type);
		fenixSearchParameters.setScope(BabelFish.print().scopeNode());
		fenixSearchParameters.setOrderBy("title");
		fenixSearchParameters.setOrderType("ASC");
		resourceExplorer.getSearchButtons().setFenixSearchParameters(fenixSearchParameters);
		grid.addListener(Events.CellDoubleClick, CatalogueImageController.onDoubleClick(resourceExplorer));
		CatalogueController.fillGrid(resourceExplorer, grid, fenixSearchParameters, resourceExplorer.getCataloguePager(), this);
		grid.setContextMenu(new CatalogueContextMenuImage(resourceExplorer, this).buildMenu());
	}
	
}
