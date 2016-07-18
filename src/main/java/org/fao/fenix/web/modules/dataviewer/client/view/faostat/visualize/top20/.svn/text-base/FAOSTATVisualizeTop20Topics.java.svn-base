package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.top20;

import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeTop20Controller;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATDomainPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsInfoVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeTop20Constants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;


public class FAOSTATVisualizeTop20Topics  extends FAOSTATDomainPanel {
	
	ContentPanel panel;
	
	TreeStore<DWCodesModelData> store;  
	  
	TreePanel<DWCodesModelData> tree;  

	public FAOSTATVisualizeTop20Topics() {
		panel = new ContentPanel();

		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);

		/** Tree **/
		store = new TreeStore<DWCodesModelData>();
		tree = new TreePanel<DWCodesModelData>(store);
		
		tree.setDisplayProperty("label");
		tree.setWidth(FAOSTATVisualizeTop20Constants.TREE_PANEL_WIDTH);
		tree.expandAll();

		tree.setAutoHeight(true);

		tree.addStyleName("tree-box");
	
	
		
	}

	public ContentPanel build(FAOSTATVisualizeTop20 visualize) {
		// panel.add(addTitle(FAOSTATLanguage.print().topics()));

		addTree();

		addListeners(visualize);
		


		return panel;
	}

	private void addListeners(final FAOSTATVisualizeTop20 visualize) {
		
		tree.addListener(Events.BeforeExpand, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				checkTreeOnClick = false;
			}
		});
		
		tree.addListener(Events.BeforeCollapse, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				checkTreeOnClick = false;			
			}
		});
		
		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (checkTreeOnClick) {
					DWCodesModelData selectedCode = tree.getSelectionModel().getSelectedItem();
					DWCodesModelData parentDomain = tree.getStore().getParent(selectedCode);

					if (selectedCode != null && parentDomain == null) {
						// parent: do nothing
						currentCode = selectedCode.getCode();
						tree.setExpanded(selectedCode, true);
					} else {
						if (parentDomain != null) {
							
							FAOSTATVisualizeQuestionsInfoVO infoVO = visualize.getInfoVO().get(selectedCode.getCode());

							if ( infoVO != null ) {
								getVOs(visualize, infoVO);
							}
						}
					}
				}
				checkTreeOnClick = true;
			}
		});
	}

	public void getVOs(FAOSTATVisualizeTop20 visualize, FAOSTATVisualizeQuestionsInfoVO infoVO ) {
		
		FAOSTATVisualizeTop20Controller.buildViewsAgent(visualize, infoVO);
	}

	public Html addTitle(String title) {
		return new Html("<div class='domain-title'> " + title + "</div>");
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
