package org.fao.fenix.web.modules.re.client.view.table;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerTable extends ResourceExplorer{

	ReportViewer reportViewer;
	
	public ReportViewer getReportViewer() {
		return reportViewer;
	}
	
	private void buildInterface(){
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.TABLEVIEW;
		
		setSearchParameters(new SearchParametersTable(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerTable(this));
		setCatalogue(new CatalogueTable(this));
		
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
	
	public ResourceExplorerTable(){
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	public ResourceExplorerTable(ReportViewer reportViewer){
		this.reportViewer = reportViewer;
		setCaller(WhoCall.REPORT);
		buildInterface();
	}

}
