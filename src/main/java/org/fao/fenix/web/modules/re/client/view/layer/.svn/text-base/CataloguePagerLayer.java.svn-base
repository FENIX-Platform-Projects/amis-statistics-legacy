package org.fao.fenix.web.modules.re.client.view.layer;

import org.fao.fenix.web.modules.re.client.control.layer.CataloguePagerLayerController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerLayer extends CataloguePager{
	
	public CataloguePagerLayer(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerLayerController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerLayerController.deselectedResources(resourceExplorer));
	}

}
