package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.classification;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.metadata.FAOSTATMetadataController;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

public class FAOSTATClassificationDomainPanel {
		
	ContentPanel panel;
	
    TreeStore<DWCodesModelData> store;  
  
    TreePanel<DWCodesModelData> tree;  

	public FAOSTATClassificationDomainPanel() {
		panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
	
		/** Tree **/
		store = new TreeStore<DWCodesModelData>();
		tree = new TreePanel<DWCodesModelData>(store);
		tree.setDisplayProperty("label");
		tree.setWidth(260);
		tree.setAutoHeight(true);
	
		
		tree.addStyleName("tree-box");
	}
	
	public ContentPanel build(FAOSTATMetadataClassification metadata) {
		addTree();
		
		FAOSTATMetadataController.getMetadataDomainsAgent(store);
		
		
		addListeners(metadata);
		
		return panel;
	}
	
	private void addListeners(final FAOSTATMetadataClassification metadata) {
		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				DWCodesModelData selectedCode = tree.getSelectionModel().getSelectedItem();
				
				DWCodesModelData parentDomain = tree.getStore().getParent(selectedCode);
				
				
				if (parentDomain!=null) {
					System.out.println("selected: " + selectedCode.getLabel() + " parent:  " +parentDomain.getLabel());
					FAOSTATMetadataController.getClassification(metadata, selectedCode, parentDomain);		
				}
				
			}
		});
		
		tree.addListener(Events.Expand, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if(tree.getSelectionModel().getSelectedItem()!=null) {
					tree.getSelectionModel().deselect(tree.getSelectionModel().getSelectedItem());	
				}	
			}
		});
	
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
