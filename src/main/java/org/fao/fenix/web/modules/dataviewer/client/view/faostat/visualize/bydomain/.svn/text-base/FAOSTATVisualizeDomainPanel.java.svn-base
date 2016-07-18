package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATDownloadConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

public class FAOSTATVisualizeDomainPanel {
	
	public ContentPanel wrapper;
	
	ContentPanel panel;
	
    TreeStore<DWCodesModelData> store;  
  
    TreePanel<DWCodesModelData> tree;  
    
    String currentCode;

	public FAOSTATVisualizeDomainPanel() {
		
		wrapper = new ContentPanel();
		wrapper.setHeaderVisible(false);
		wrapper.setBodyBorder(false);
		wrapper.setScrollMode(Scroll.NONE);
		
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setScrollMode(Scroll.NONE);
		
		/** TODO set style **/
		
		/** Tree **/
		store = new TreeStore<DWCodesModelData>();
		tree = new TreePanel<DWCodesModelData>(store);
		tree.setDisplayProperty("label");
		tree.setWidth(FAOSTATVisualizeByDomainConstants.TREE_WIDTH);
	
		
		tree.addStyleName("tree-box");
	}
	
	public ContentPanel build(FAOSTATVisualizeByDomain visualizePanel, String defaultDomainCode) {

		wrapper.add(panel);
		
		addTree();
		
		currentCode = defaultDomainCode;
		
		// GOOGLE ANaLYTICS
		visualizePanel.setGoogleLabel(currentCode);
		
		FAOSTATVisualizeByDomainController.getDomainsAgent(visualizePanel, this, store, tree, currentCode);
		
		
		addListeners(visualizePanel);
		
		return wrapper;
	}
	
	private void addListeners(final FAOSTATVisualizeByDomain visualizePanel) {
		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				
				DWCodesModelData selectedCode = tree.getSelectionModel().getSelectedItem();
				
				DWCodesModelData parentDomain = tree.getStore().getParent(selectedCode);
				
				Boolean buildView = false;
				
//				System.out.println("currentCode: " + currentCode + " | " + selectedCode.getCode());
				if(selectedCode!=null && parentDomain == null) {
					
					tree.setExpanded(selectedCode, true);
					visualizePanel.getTitlePanel().build(selectedCode.getLabel());
					visualizePanel.getTitlePanel().getPanel().layout();
					
					if (  !selectedCode.getCode().equalsIgnoreCase(currentCode) ) {
//						System.out.println("IS PARENT: " + selectedCode.getCode());
						currentCode = selectedCode.getCode();
						buildView = true;
					}
					
				} else {
					if (parentDomain!=null) {
						if ( !selectedCode.getCode().equalsIgnoreCase(currentCode) ) {
							System.out.println("SELECTED IS CHILD: |" + selectedCode.getCode() + " parent:  " +parentDomain.getCode());
							currentCode = selectedCode.getCode();
							visualizePanel.getTitlePanel().build(parentDomain.getLabel() + " > " + selectedCode.getLabel());
							visualizePanel.getTitlePanel().getPanel().layout();
							buildView = true;
						}
					}
				}	
				
				if  ( buildView ) {
					getVOs(visualizePanel, selectedCode);
				}
				
				
			}
		});	
	}
	
	public void getVOs(FAOSTATVisualizeByDomain visualizePanel, DWCodesModelData selectedCode) {
		// GOOGLE ANALYTICS
		visualizePanel.setGoogleLabel(selectedCode.getCode());
		
		FAOSTATVisualizeByDomainController.getVOs(visualizePanel, visualizePanel.getSettings(), selectedCode);				

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

}
