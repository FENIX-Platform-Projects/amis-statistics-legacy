package org.fao.fenix.web.modules.re.client.view.tableview;

import org.fao.fenix.web.modules.re.client.control.tableview.CataloguePagerTableViewController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerTableView extends CataloguePager {

	public CataloguePagerTableView(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
		this.open.addSelectionListener(CataloguePagerTableViewController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerTableViewController.deselectedResources(resourceExplorer));
	}
	
}