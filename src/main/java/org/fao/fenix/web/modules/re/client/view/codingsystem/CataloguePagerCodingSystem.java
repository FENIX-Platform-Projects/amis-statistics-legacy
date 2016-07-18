package org.fao.fenix.web.modules.re.client.view.codingsystem;

import org.fao.fenix.web.modules.re.client.control.codingsystem.CataloguePagerCodingSystemController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerCodingSystem extends CataloguePager{
	
	public CataloguePagerCodingSystem(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerCodingSystemController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerCodingSystemController.deselectedResources(resourceExplorer));
	}

}
