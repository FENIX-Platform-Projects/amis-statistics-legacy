package org.fao.fenix.web.modules.re.client.view.search;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.pm.client.view.ProjectManager;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.project.CataloguePagerProject;
import org.fao.fenix.web.modules.re.client.view.project.CatalogueProject;
import org.fao.fenix.web.modules.re.client.view.project.SearchParametersProject;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerSearch extends ResourceExplorer{
	
	
    private ProjectManager projectManager;
	
	public ProjectManager getProjectManager() {
		return projectManager;
	}

	private void buildInterface(){
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.PROJECT;
		
		setSize(850, 500);
		setResourceTypeSelector(new ResourceTypeSelector(this,BabelFish.print().projects()));
		setSearchParameters(new SearchParametersProject(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerProject(this));
		setCatalogue(new CatalogueProject(this));
		
		getWest().setTopComponent(getResourceTypeSelector().getToolBar());
		getWest().add(getSearchParameters().getMainCont());
		getWest().setBottomComponent(getSearchButtons().getCont());		
		addWestPartToWindow();

		//getCenter().setTopComponent(getCatalogueToolbar().getToolBar());
		getCenter().add(getCatalogue().getGrid());	
		getCenter().setBottomComponent(getCataloguePager().getMainCont());
	
		//getCenter().setBottomComponent(getPagingToolBar());
		addCenterPartToWindow();
		
		getCenter().setHeaderVisible(false);
		getWestData().setFloatable(true);
		
		show();
		
	}

	public ResourceExplorerSearch(){
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	public ResourceExplorerSearch(ProjectManager projectManager){
		this.projectManager=projectManager;
		setCaller(WhoCall.PROJECTMANAGER);
		setSize(775, 450);
		buildInterface();
	}

}
