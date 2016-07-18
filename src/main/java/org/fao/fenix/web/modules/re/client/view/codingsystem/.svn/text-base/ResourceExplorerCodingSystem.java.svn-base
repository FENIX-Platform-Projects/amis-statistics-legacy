package org.fao.fenix.web.modules.re.client.view.codingsystem;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerCodingSystem extends ResourceExplorer{
	
	public void  buildInterface(){
		
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.CODINGSYSTEM;
		
		setSearchParameters(new SearchParametersCodingSystem(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerCodingSystem(this));
		setCatalogue(new CatalogueCodingSystem(this));
		
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
	
	public ResourceExplorerCodingSystem(){
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	

}
