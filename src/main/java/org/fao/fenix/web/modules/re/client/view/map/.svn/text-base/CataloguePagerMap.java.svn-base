package org.fao.fenix.web.modules.re.client.view.map;

import org.fao.fenix.web.modules.re.client.control.map.CataloguePagerMapController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerMap extends CataloguePager{
	
	public CataloguePagerMap(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerMapController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerMapController.deselectedResources(resourceExplorer));
	}

}
