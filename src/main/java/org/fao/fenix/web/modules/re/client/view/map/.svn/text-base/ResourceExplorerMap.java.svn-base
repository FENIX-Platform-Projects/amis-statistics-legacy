package org.fao.fenix.web.modules.re.client.view.map;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerMap extends ResourceExplorer{
	
	ReportViewer reportViewer;
	
	public ReportViewer getReportViewer() {
		return reportViewer;
	}

	private void buildInterface(){
		
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.MAPVIEW;
		
		setSearchParameters(new SearchParametersMap(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerMap(this));
		setCatalogue(new CatalogueMap(this));
		
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
	
	public ResourceExplorerMap(){
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	public ResourceExplorerMap(ReportViewer reportViewer){
		this.reportViewer = reportViewer;
		setCaller(WhoCall.REPORT);
		buildInterface();
	}
	
	public ResourceExplorerMap(DesignerBox designerBox){
		this.setDesignerBox(designerBox);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}

}
