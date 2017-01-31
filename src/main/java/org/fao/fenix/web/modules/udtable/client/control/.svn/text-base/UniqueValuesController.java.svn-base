package org.fao.fenix.web.modules.udtable.client.control;



import org.fao.fenix.web.modules.udtable.client.UniqueValuesWindow;
import org.fao.fenix.web.modules.udtable.common.services.UDTableServiceEntry;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UniqueValuesController {

	public static SelectionListener<ButtonEvent> createGhostTables(final UniqueValuesWindow window) {
//		System.out.println("asdA");
		return new SelectionListener<ButtonEvent>() {
			
			public void componentSelected(ButtonEvent event) {

				UDTableServiceEntry.getInstance().createGhostTables(new AsyncCallback<String>() {
				
					public void onSuccess(String result){
					}

					public void onFailure(Throwable caught) {
					}
				
				});
			}
		};
	}
}
