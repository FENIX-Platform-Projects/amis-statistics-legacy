package org.fao.fenix.web.modules.re.client.view.table;

import org.fao.fenix.web.modules.re.client.control.table.CataloguePagerTableController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerTable extends CataloguePager{
	
	public CataloguePagerTable(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerTableController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerTableController.deselectedResources(resourceExplorer));
	}

}
