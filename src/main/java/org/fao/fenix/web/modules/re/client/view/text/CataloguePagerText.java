package org.fao.fenix.web.modules.re.client.view.text;

import org.fao.fenix.web.modules.re.client.control.text.CataloguePagerTextController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;


public class CataloguePagerText extends CataloguePager{

	public CataloguePagerText(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerTextController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerTextController.deselectedResources(resourceExplorer));
	}
	
}
