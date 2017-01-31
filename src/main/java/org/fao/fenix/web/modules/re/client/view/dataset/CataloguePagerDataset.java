package org.fao.fenix.web.modules.re.client.view.dataset;

import org.fao.fenix.web.modules.re.client.control.dataset.CataloguePagerDatesetController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerDataset extends CataloguePager{
	
	public CataloguePagerDataset(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerDatesetController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerDatesetController.deselectedResources(resourceExplorer));
	}

}
