package org.fao.fenix.web.modules.re.client.view.layer;

import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerLayer extends ResourceExplorer{
	
	public ResourceExplorerLayer(){
		
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.LAYER;
		
		setSearchParameters(new SearchParametersLayer(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerLayer(this));
		setCatalogue(new CatalogueLayer(this));
		
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
	
	public ResourceExplorerLayer(DesignerBox designerBox){
		
		this.setDesignerBox(designerBox);
		setCaller(WhoCall.DESIGNER);
		
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.LAYER;
		
		setSearchParameters(new SearchParametersLayer(this));
		setSearchButtons(new SearchButtons(this));
		setCataloguePager(new CataloguePagerLayer(this));
		setCatalogue(new CatalogueLayer(this));
		
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

}
