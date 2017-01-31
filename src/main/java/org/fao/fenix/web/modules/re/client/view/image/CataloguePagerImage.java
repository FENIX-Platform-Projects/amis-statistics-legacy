package org.fao.fenix.web.modules.re.client.view.image;

import org.fao.fenix.web.modules.re.client.control.image.CataloguePagerImageController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerImage extends CataloguePager {

	public CataloguePagerImage(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
		this.open.addSelectionListener(CataloguePagerImageController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerImageController.deselectedResources(resourceExplorer));
	}
	
}