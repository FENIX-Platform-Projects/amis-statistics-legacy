package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

public class FAOSTATSearchFilter {
	

	ContentPanel panel;

	TreeStore<DWCodesModelData> store;

	TreePanel<DWCodesModelData> tree;
	
	ContentPanel showAllDomains;
	
	public FAOSTATSearchFilter() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		showAllDomains = new ContentPanel();
		showAllDomains.setHeaderVisible(false);
		showAllDomains.setBodyBorder(false);

		/** Tree **/
		store = new TreeStore<DWCodesModelData>();
		tree = new TreePanel<DWCodesModelData>(store);
		tree.setDisplayProperty("label");
		tree.setWidth(FAOSTATSearchConstants.TREE_PANEL_DOMAIN_WIDTH);
		tree.setAutoHeight(true);

		tree.addStyleName("tree-box");
		
	}

	public ContentPanel build(FAOSTATSearchClusterResults clusterResults) {
		panel.add(DataViewerClientUtils.addVSpace(5));
		panel.add(addTitle(FAOSTATLanguage.print().domains()));
		panel.add(DataViewerClientUtils.addVSpace(5));

		addTree();
		
		panel.add(buildShowAllDomains(clusterResults));
		
		panel.hide();

//		FAOSTATDownloadController.getDomainsAgent(download, store, tree, "QC");

		addListeners(clusterResults);

		return panel;
	}
	
	private HorizontalPanel buildShowAllDomains(FAOSTATSearchClusterResults clusterResults) {
	
		HorizontalPanel panel = new HorizontalPanel();
			
		ClickHtml html = new ClickHtml();
		html.setHtml("<div class='search_result_export'> "+FAOSTATLanguage.print().showAllDomains()+"</div>");
			
		html.addListener(Events.OnClick, FAOSTATSearchController.showAllDomains(clusterResults));
			
		panel.add(html);
			
		return panel;
	}
	


	private void addListeners(final FAOSTATSearchClusterResults clusterResults) {
		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
						System.out.println("the tree has been clicked");
						DWCodesModelData selectedCode = tree.getSelectionModel().getSelectedItem();

						DWCodesModelData parentDomain = tree.getStore().getParent(selectedCode);

						System.out.println("selected: "+ selectedCode.getLabel() + " parent:  " + parentDomain.getLabel());

						if (parentDomain != null) {
							
							FAOSTATSearchController.hideByDomainPanels(clusterResults, selectedCode.getCode());
						}

					}
				});

				tree.addListener(Events.Expand, new Listener<BaseEvent>() {

					@Override
					public void handleEvent(BaseEvent be) {
						if (tree.getSelectionModel().getSelectedItem() != null) {
							tree.getSelectionModel().deselect(
									tree.getSelectionModel().getSelectedItem());
						}
			}
		});

	}

	public Html addTitle(String title) {
		return new Html("<div class='download_domain_title'> " + title
				+ "</div>");
	}

	public void addTree() {
		panel.add(tree);
	}

	public TreeStore<DWCodesModelData> getStore() {
		return store;
	}

	public TreePanel<DWCodesModelData> getTree() {
		return tree;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	
}
