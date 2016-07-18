package org.fao.fenix.web.modules.re.client.view.search;

import org.fao.fenix.web.modules.re.client.control.search.CataloguePagerSearchController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerSearch extends CataloguePager{

	public CataloguePagerSearch(ResourceExplorer resourceExplorer, String resourceType){
		super(resourceExplorer);

		this.open.addSelectionListener(CataloguePagerSearchController.openResources(resourceExplorer, resourceType));
		this.cancel.addSelectionListener(CataloguePagerSearchController.deselectedResources(resourceExplorer));
	}

}
