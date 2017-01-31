package org.fao.fenix.web.modules.re.client.view.project;

import org.fao.fenix.web.modules.re.client.control.project.CataloguePagerProjectController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerProject extends CataloguePager{
	
	public CataloguePagerProject(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerProjectController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerProjectController.deselectedResources(resourceExplorer));
	}

}
