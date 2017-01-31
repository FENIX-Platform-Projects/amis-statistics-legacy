package org.fao.fenix.web.modules.re.client.view.report;

import org.fao.fenix.web.modules.re.client.control.report.CataloguePagerReportController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerReport extends CataloguePager{
	
	public CataloguePagerReport(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerReportController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerReportController.deselectedResources(resourceExplorer));
	}

}
