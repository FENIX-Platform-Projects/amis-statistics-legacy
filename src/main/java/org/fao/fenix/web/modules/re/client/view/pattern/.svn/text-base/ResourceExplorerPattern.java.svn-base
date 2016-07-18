package org.fao.fenix.web.modules.re.client.view.pattern;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerPattern extends ResourceExplorer{
	
	
	private ChartWizard chartWizard;
	
	public ChartWizard getChartWizard() {
		return chartWizard;
	}

	private void buildInterface(){
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.PATTERN;
		
		setSearchParameters(new SearchParametersPattern(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerPattern(this));
		setCatalogue(new CataloguePattern(this));
		
	    getWest().add(getSearchParameters().getMainCont());
		getWest().setBottomComponent(getSearchButtons().getCont());		
		addWestPartToWindow();

        //getCenter().setTopComponent(getCatalogueToolbar().getToolBar());
		getCenter().add(getCatalogue().getGrid());
		getCenter().setBottomComponent(getCataloguePager().getMainCont());
		addCenterPartToWindow();

		getCenter().setHeaderVisible(false);
		getWestData().setFloatable(true);
		
		show();
	}

	public ResourceExplorerPattern(){
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	public ResourceExplorerPattern(ChartWizard chartWizard){
		this.chartWizard=chartWizard;
		setCaller(WhoCall.CHART);
		buildInterface();
	}

}
