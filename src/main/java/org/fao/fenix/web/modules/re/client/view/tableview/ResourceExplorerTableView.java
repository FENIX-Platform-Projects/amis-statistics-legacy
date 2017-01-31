package org.fao.fenix.web.modules.re.client.view.tableview;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerTableView extends ResourceExplorer {

	public ResourceExplorerTableView() {
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	ReportViewer reportViewer;

	private void buildInterface() {
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType = ResourceType.TABLEVIEW;
		setSearchParameters(new SearchParametersTableView(this));
		setSearchButtons(new SearchButtons(this));
		setCataloguePager(new CataloguePagerTableView(this));
		setCatalogue(new CatalogueTableView(this));
		getWest().add(getSearchParameters().getMainCont());
		getWest().setBottomComponent(getSearchButtons().getCont());
		addWestPartToWindow();
		getCenter().add(getCatalogue().getGrid());
		getCenter().setBottomComponent(getCataloguePager().getMainCont());
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getWestData().setFloatable(true);
		show();
	}
	
	public ResourceExplorerTableView(DesignerBox designerBox){
		this.setDesignerBox(designerBox);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
}