package org.fao.fenix.web.modules.re.client.view.image;

import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerImage extends ResourceExplorer {

	public ResourceExplorerImage() {
		setCaller(WhoCall.USER);
		buildInterface();
	}

	private void buildInterface() {
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType = ResourceType.IMAGE;
		setSearchParameters(new SearchParametersImage(this));
		setSearchButtons(new SearchButtons(this));
		setCataloguePager(new CataloguePagerImage(this));
		setCatalogue(new CatalogueImage(this));
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

	public ResourceExplorerImage(DesignerBox designerBox){
		this.setDesignerBox(designerBox);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
	public ResourceExplorerImage(String tinyMCEPanelID){
		this.setTinyMCEPanelID(tinyMCEPanelID);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
}