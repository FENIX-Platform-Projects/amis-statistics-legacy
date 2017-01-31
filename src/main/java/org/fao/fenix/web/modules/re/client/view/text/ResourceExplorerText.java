package org.fao.fenix.web.modules.re.client.view.text;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;

public class ResourceExplorerText extends ResourceExplorer{

	ReportViewer reportViewer;
	
	
	public ReportViewer getReportViewer() {
		return reportViewer;
	}
	
	
	
	private void buildInterface(){
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.TEXTVIEW;
		
		setSearchParameters(new SearchParametersText(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerText(this));
		setCatalogue(new CatalogueText(this));

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
	
	public ResourceExplorerText() {
	    setCaller(WhoCall.USER);
		buildInterface();
	}
	
	public ResourceExplorerText(TextGroupWindow window) {
	    buildInterface();
	}
	
	public ResourceExplorerText(ReportViewer reportViewer){
		this.reportViewer = reportViewer;
		setCaller(WhoCall.REPORT);
		buildInterface();
	}
	
	public ResourceExplorerText(DesignerBox designerBox) {
		super.setDesignerBox(designerBox);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
}
