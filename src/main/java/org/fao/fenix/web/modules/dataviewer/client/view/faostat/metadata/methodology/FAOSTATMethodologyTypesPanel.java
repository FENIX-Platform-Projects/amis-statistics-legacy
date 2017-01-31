package org.fao.fenix.web.modules.dataviewer.client.view.faostat.metadata.methodology;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.metadata.FAOSTATMetadataController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;

public class FAOSTATMethodologyTypesPanel {

	ContentPanel panel;

     ListView<DWCodesModelData> listView;   
    
    ListStore<DWCodesModelData> listStore;
    
    ContentPanel message;


	public FAOSTATMethodologyTypesPanel() {
		panel = new ContentPanel();

		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
	    listStore = new ListStore<DWCodesModelData>();
	
		/** List view **/		
	    listView = new ListView<DWCodesModelData>();  
	    listView.setBorders(false);
	    listView.setDisplayProperty("label");   
	    listView.setWidth(350);
	    listView.setAutoHeight(true);
	    listView.setStore(listStore);   
	    listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    
	    
	    message = new ContentPanel();
	    message.setHeaderVisible(false);
	    message.setBodyBorder(false);
		
	}

	public ContentPanel build(FAOSTATMetadataMethodology methodology) {
		addMessage();
		addListView();
			
		FAOSTATMetadataController.getMetadataMethodologyTypesAgent(listStore, listView, methodology);

		addListeners(methodology);

		return panel;
	}

	private void addListeners(final FAOSTATMetadataMethodology methodology) {
		listView.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				DWCodesModelData selectedCode = listView.getSelectionModel().getSelectedItem();
				FAOSTATMetadataController.getMethodologyDetails(methodology, selectedCode);
			}
		});
	}
	
	public void addMessage() {
		panel.add(new Html("<div class='content_title' style='padding-bottom: 10px;'>"+FAOSTATLanguage.print().selectAMethodologyBelowToViewDetails()+"</div>"));
	}

	
	public void addListView() {
		panel.add(listView);
	}

}
