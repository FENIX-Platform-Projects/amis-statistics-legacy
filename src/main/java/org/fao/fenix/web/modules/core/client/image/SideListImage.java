package org.fao.fenix.web.modules.core.client.image;

import java.util.List;

import org.fao.fenix.web.modules.re.common.services.REServiceEntry;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SideListImage {
	
	VerticalPanel mainCont;
	DataList list;
	HTML infoImages;
	
	public VerticalPanel getMainCont() {
		return mainCont;
	}

	public DataList getList() {
		return list;
	}

	public SideListImage(final HTML imageContent, final ContentPanel west){
		
		mainCont = new VerticalPanel();
		mainCont.setSpacing(3);
		
		infoImages = new HTML();
		
		list = new DataList();
		list.setSelectionMode(SelectionMode.SINGLE);
		list.setBorders(false);  
//		list.setWidth(190);
//		list.setHeight(250);
		list.setScrollMode(Scroll.AUTO);
		list.addListener(Events.SelectionChange, new Listener<ComponentEvent>(){
			 public void handleEvent(ComponentEvent ce) {
				DataList l = (DataList) ce.getComponent();
				DataListItem item = l.getSelectedItem();
				imageContent.setHTML("<img src='../imageRepository/" + item.getId() + "'>");  
			}
		});
		
		REServiceEntry.getInstance().getImagesByFolder("imageRepository", new AsyncCallback<List<String>>(){
			
			public void onSuccess(List<String> result) {

				west.setHeading("Found:  " + result.size() + " images");
				
				DataListItem item;
				for (String element : result){
					item = new DataListItem();
					item.setText(element);
					item.setId(element);
					item.setIconStyle("");
					item.setChecked(true);
					list.add(item);
				}
			}

			public void onFailure(Throwable caught) {
				Info.display("getImagesByFolder", caught.getLocalizedMessage());
			}
			
		});
		
		mainCont.add(list);
		
	}

}
