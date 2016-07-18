package org.fao.fenix.web.modules.re.client.view;

import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.re.client.control.RECallback;
import org.fao.fenix.web.modules.re.client.control.REModel;
import org.fao.fenix.web.modules.re.client.control.ResourceExplorerController;
import org.fao.fenix.web.modules.re.client.view.search.ResourceTypeSelector;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

public abstract class ResourceExplorer extends FenixWindow {

	private ResourceTypeSelector resourceTypeSelector;

	private SearchParameters searchParameters;

	private SearchButtons searchButtons;

	private Catalogue catalogue;

	private CatalogueContextMenu catalogueContextMenu;

	private CataloguePager cataloguePager;

	private CatalogueToolbar catalogueToolbar;

	private String caller;

	private RECallback callback = null;

	private PagingToolBar pagingToolBar = new PagingToolBar(500);
	
	private DesignerBox designerBox;
	
	private String tinyMCEPanelID;
	
	private String tinyMCEPanelMarkID;
	
	private String originalHTML;

	public ResourceExplorer() {
		setSize("750px", "550px");
		setCollapsible(true);
		setMaximizable(true);
		setCenterProperties();
		setWestProperties();
		REModel.setResourceExplorer(this);
		REModel.setReIsopen(true);
		getWindow().addWindowListener(ResourceExplorerController.onCloseEditor(this));
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public ResourceTypeSelector getResourceTypeSelector() {
		return resourceTypeSelector;
	}

	public void setResourceTypeSelector(ResourceTypeSelector resourceTypeSelector) {
		this.resourceTypeSelector = resourceTypeSelector;
	}

	public SearchParameters getSearchParameters() {
		return searchParameters;
	}

	public void setSearchParameters(SearchParameters searchParameters) {
		this.searchParameters = searchParameters;
	}

	public SearchButtons getSearchButtons() {
		return searchButtons;
	}

	public void setSearchButtons(SearchButtons searchButtons) {
		this.searchButtons = searchButtons;
	}

	public Catalogue getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	public CatalogueContextMenu getCatalogueContextMenu() {
		return catalogueContextMenu;
	}

	public void setCatalogueContextMenu(CatalogueContextMenu catalogueContextMenu) {
		this.catalogueContextMenu = catalogueContextMenu;
	}

	public CataloguePager getCataloguePager() {
		return cataloguePager;
	}

	public void setCataloguePager(CataloguePager cataloguePager) {
		this.cataloguePager = cataloguePager;
	}

	public PagingToolBar getPagingToolBar() {
		return pagingToolBar;
	}

	public void setPagingToolBar(PagingToolBar pagingToolBar) {
		this.pagingToolBar = pagingToolBar;
	}

	public CatalogueToolbar getCatalogueToolbar() {
		return catalogueToolbar;
	}

	public void setCatalogueToolbar(CatalogueToolbar catalogueToolbar) {
		this.catalogueToolbar = catalogueToolbar;
	}

	public RECallback getCallback() {
		return callback;
	}

	public void setCallback(RECallback callback) {
		this.callback = callback;
	}

	public DesignerBox getDesignerBox() {
		return designerBox;
	}

	public void setDesignerBox(DesignerBox designerBox) {
		this.designerBox = designerBox;
	}

	public String getTinyMCEPanelID() {
		return tinyMCEPanelID;
	}

	public void setTinyMCEPanelID(String tinyMCEPanelID) {
		this.tinyMCEPanelID = tinyMCEPanelID;
	}

	public String getTinyMCEPanelMarkID() {
		return tinyMCEPanelMarkID;
	}

	public void setTinyMCEPanelMarkID(String tinyMCEPanelMarkID) {
		this.tinyMCEPanelMarkID = tinyMCEPanelMarkID;
	}

	public String getOriginalHTML() {
		return originalHTML;
	}

	public void setOriginalHTML(String originalHTML) {
		this.originalHTML = originalHTML;
	}

}