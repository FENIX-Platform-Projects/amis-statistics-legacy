package org.fao.fenix.web.modules.re.client.view.chart;

import org.fao.fenix.web.modules.re.client.control.chart.CataloguePagerChartController;
import org.fao.fenix.web.modules.re.client.view.CataloguePager;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class CataloguePagerChart extends CataloguePager{
	
	public CataloguePagerChart(ResourceExplorer resourceExplorer){
		super(resourceExplorer);
				
		this.open.addSelectionListener(CataloguePagerChartController.openResources(resourceExplorer));
		this.cancel.addSelectionListener(CataloguePagerChartController.deselectedResources(resourceExplorer));
	}

}
