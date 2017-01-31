package org.fao.fenix.web.modules.re.client.view.textgroup;

import org.fao.fenix.web.modules.re.client.control.textgroup.CataloguePagerTextGroupController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;


public class CataloguePagerTextGroup extends CataloguePager{

	public CataloguePagerTextGroup(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerTextGroupController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerTextGroupController.deselectedResources(resourceExplorer));
	}
	
}
