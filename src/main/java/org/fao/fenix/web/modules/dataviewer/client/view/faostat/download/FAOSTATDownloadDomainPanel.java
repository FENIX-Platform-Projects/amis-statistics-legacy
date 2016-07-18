package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;


import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATDomainPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Window;

public class FAOSTATDownloadDomainPanel extends FAOSTATDomainPanel {

	
	public ContentPanel build(FAOSTATDownload download) {
//		panel.add(DataViewerClientUtils.addVSpace(5));
//		panel.add(addTitle(FAOSTATLanguage.print().domains()));
//		panel.add(DataViewerClientUtils.addVSpace(5));
		
		wrapper.add(DataViewerClientUtils.addVSpace(5));
		wrapper.add(addTitle(FAOSTATLanguage.print().domains()));
		wrapper.add(DataViewerClientUtils.addVSpace(5));
		
		wrapper.add(panel);

		addTree();

		FAOSTATDownloadController.getDomainsAgent(download, store, tree, null);

		addListeners(download);
		
		wrapper.layout();
		
		return wrapper;
	}
	
	private void addListeners(final FAOSTATDownload download) {

		tree.addListener(Events.BeforeExpand, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
//				System.out.println("ON BeforeExpand");
				checkTreeOnClick = false;
			}
		});
		
		tree.addListener(Events.BeforeCollapse, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
//				System.out.println("ON BeforeExpand");
				checkTreeOnClick = false;			
			}
		});

		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {


				if (checkTreeOnClick) {
					DWCodesModelData selectedCode = tree.getSelectionModel().getSelectedItem();
					DWCodesModelData parentDomain = tree.getStore().getParent(selectedCode);
					 System.out.println("currentCode: |" + currentCode + "|" +
					 selectedCode.getCode() +"|");

					GoogleAnalyticsController.trackEvent(FAOSTATCurrentView.DOWNLOAD_STANDARD.toString(), "Open Tree", selectedCode.getCode());

					if (selectedCode != null && parentDomain == null) {
						currentCode = selectedCode.getCode();
						
						tree.setExpanded(selectedCode, true);
						download.buildIntroductionPage(selectedCode);
						download.getTablePanel().getTablesPanel().removeAll();
						download.getTablePanel().getViewMorePanel().hide();
						download.getTitlePanel().getPanel().removeAll();

					} else {
						if (parentDomain != null) {

							// AQUASTAT
							if (selectedCode.getCode().equalsIgnoreCase("AQUASTAT")) {
								String lang = FAOSTATConstants.faostatToLocaleLanguages.get(FAOSTATConstants.faostatLanguage);
								if ( lang == null ) {
									lang = "en";
								}	
								Window.open("http://www.fao.org/nr/water/aquastat/data/query/index.html?lang=" + lang, "_blank", "");
							} else {
								 System.out.println("SELECTED IS CHILD: |" +selectedCode.getCode() + "| parent:  |" +parentDomain.getCode()+"|");
								currentCode = selectedCode.getCode();
								download.buildSelectors(selectedCode);
						
								download.getTablePanel().getTablesPanel().removeAll();
								download.getTablePanel().getViewMorePanel().hide();

								String date = FAOSTATDownloadController.getDate(selectedCode.getDateLastUpdate());

								download.getTitlePanel().build(parentDomain.getLabel() + " > " + selectedCode.getLabel(), date);
								download.getTitlePanel().getPanel().layout();
								

							}
						}
					}
				}
				checkTreeOnClick = true;
			}
		});
	}

	public static native String getURL(String url)/*-{
		return $wnd.open(url, 'target=_blank')
	}-*/;

	public Html addTitle(String title) {
		return new Html("<div class='download_title'> " + title + "</div>");
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

	public String getCurrentCode() {
		return currentCode;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}
	
	
	
}